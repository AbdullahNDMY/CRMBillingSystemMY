<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
   		<link type="text/css" rel="stylesheet" href="css/m_cdms01.css" />
   		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>		
   		<script type="text/javascript" src="js/m_cdms01.js"></script>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>		   		
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   		<title></title>
	</head>
	<body id="m" onload="initMain();onload();">
		<ts:form action="/M_CDMS01DSP">
		<input type="hidden" name="event"/>
		<t:defineCodeList id="LIST_CODEMAINT"/>
		<t:defineCodeList id="LIST_TYPE_VAL"/>
		<t:defineCodeList id="LIST_RESET_NO"/>
		<input type="hidden" id="mode" value="${_m_cdmForm.mode}"/>
		<table class="subHeader" cellpadding="0" cellspacing="0">
    		<tr>
    			<td><bean:message key="screen.m_cdm.screen_name"/></td>
    		</tr>
    	</table>
    	<!-- READONLY -->
		<logic:equal name="_m_cdmForm" property="mode" value="READONLY">
 	  	<table class="information" cellpadding="0" cellspacing="1">
    		<tr>
    			<td colspan="2" class="header"><bean:message key="screen.m_cdm.label_general_info"/></td>
    		</tr>   		         		        			
    		<!-- QCSNO -->
    		<tr>
    			<td>
    				<t:writeCodeValue key="QCSNO" codeList="LIST_CODEMAINT"></t:writeCodeValue>
    			</td>
    			<td>
    				<bean:message key="screen.m_cdm.label_colon"/>
    				<logic:present name="_m_cdmForm" property="qcsno">
	    				<logic:iterate id="qcsno" name="_m_cdmForm" property="qcsno" indexId="i">
	    					<logic:present name="_m_cdmForm" property='<%= "qcsno[" + i + "].type_val" %>'>
	    						<t:writeCodeValue name="_m_cdmForm" property='<%= "qcsno[" + i + "].type_val" %>' codeList="LIST_TYPE_VAL"></t:writeCodeValue>
	    					</logic:present>
	    					<bean:write name="_m_cdmForm" property='<%= "qcsno[" + i + "].value" %>'/>
				        </logic:iterate>    	
						<bean:write name="_m_cdmForm" property="qcsCodeValue"/>
					</logic:present>    							        
    			</td>
    		</tr>      
    		<!-- QUONO -->
    		<tr>
    			<td>
    				<t:writeCodeValue key="QUONO" codeList="LIST_CODEMAINT"></t:writeCodeValue>
    			</td>
    			<td>
    				<bean:message key="screen.m_cdm.label_colon"/>
    				<logic:present name="_m_cdmForm" property="quono">
	    				<logic:iterate id="quono" name="_m_cdmForm" property="quono" indexId="i">
	    					<logic:present name="_m_cdmForm" property='<%= "quono[" + i + "].type_val" %>'>
	    						<t:writeCodeValue name="_m_cdmForm" property='<%= "quono[" + i + "].type_val" %>' codeList="LIST_TYPE_VAL"></t:writeCodeValue>
	    					</logic:present>
	    					<bean:write name="_m_cdmForm" property='<%= "quono[" + i + "].value" %>'/>	    					    				
				        </logic:iterate>      				
						<bean:write name="_m_cdmForm" property="quoCodeValue"/>	    				
    				</logic:present>
    			</td>
    		</tr>     	
    		<!-- BIFNO -->
    		<tr>
    			<td>
    				<t:writeCodeValue key="BIFNO" codeList="LIST_CODEMAINT"></t:writeCodeValue>
    			</td>
    			<td>
    				<bean:message key="screen.m_cdm.label_colon"/>
    				<logic:present name="_m_cdmForm" property="bifno">
	    				<logic:iterate id="bifno" name="_m_cdmForm" property="bifno" indexId="i">
	    					<logic:present name="_m_cdmForm" property='<%= "bifno[" + i + "].type_val" %>'>
	    						<t:writeCodeValue name="_m_cdmForm" property='<%= "bifno[" + i + "].type_val" %>' codeList="LIST_TYPE_VAL"></t:writeCodeValue>
	    					</logic:present>
	    					<bean:write name="_m_cdmForm" property='<%= "bifno[" + i + "].value" %>'/>	    					    					    				
				        </logic:iterate>      				
						<bean:write name="_m_cdmForm" property="bifCodeValue"/>	    				
    				</logic:present>    				
    			</td>
    		</tr>  
    		<!-- BIFDN -->
    		<tr>
    			<td>
    				<t:writeCodeValue key="BIFDN" codeList="LIST_CODEMAINT"></t:writeCodeValue>
    			</td>
    			<td>
    				<bean:message key="screen.m_cdm.label_colon"/>
    				<logic:present name="_m_cdmForm" property="bifdn">
	    				<logic:iterate id="bifdn" name="_m_cdmForm" property="bifdn" indexId="i">
	    					<logic:present name="_m_cdmForm" property='<%= "bifdn[" + i + "].type_val" %>'>
	    						<t:writeCodeValue name="_m_cdmForm" property='<%= "bifdn[" + i + "].type_val" %>' codeList="LIST_TYPE_VAL"></t:writeCodeValue>
	    					</logic:present>
	    					<bean:write name="_m_cdmForm" property='<%= "bifdn[" + i + "].value" %>'/>	    					    					    				
				        </logic:iterate>      				
						<bean:write name="_m_cdmForm" property="bifdnCodeValue"/>	    				
    				</logic:present>    				
    			</td>
    		</tr>      			 
    		<!-- BIFCN -->
    		<tr>
    			<td>
    				<t:writeCodeValue key="BIFCN" codeList="LIST_CODEMAINT"></t:writeCodeValue>
    			</td>
    			<td>
    				<bean:message key="screen.m_cdm.label_colon"/>
    				<logic:present name="_m_cdmForm" property="bifcn">
	    				<logic:iterate id="bifcn" name="_m_cdmForm" property="bifcn" indexId="i">
	    					<logic:present name="_m_cdmForm" property='<%= "bifcn[" + i + "].type_val" %>'>
	    						<t:writeCodeValue name="_m_cdmForm" property='<%= "bifcn[" + i + "].type_val" %>' codeList="LIST_TYPE_VAL"></t:writeCodeValue>
	    					</logic:present>
	    					<bean:write name="_m_cdmForm" property='<%= "bifcn[" + i + "].value" %>'/>	    					    					    				
				        </logic:iterate>      				
						<bean:write name="_m_cdmForm" property="bifcnCodeValue"/>	    				
    				</logic:present>    				
    			</td>
    		</tr>
    		
    		
    		
    		
    		 			 		         		        			
    		<!-- INVNO -->
    		<tr>
    			<td>
    				<t:writeCodeValue key="INVNO" codeList="LIST_CODEMAINT"></t:writeCodeValue>
    			</td>
    			<td>
    				<bean:message key="screen.m_cdm.label_colon"/>
    				<logic:present name="_m_cdmForm" property="invno">
	    				<logic:iterate id="invno" name="_m_cdmForm" property="invno" indexId="i">
	    					<logic:present name="_m_cdmForm" property='<%= "invno[" + i + "].type_val" %>'>
	    						<t:writeCodeValue name="_m_cdmForm" property='<%= "invno[" + i + "].type_val" %>' codeList="LIST_TYPE_VAL"></t:writeCodeValue>
	    					</logic:present>
	    					<bean:write name="_m_cdmForm" property='<%= "invno[" + i + "].value" %>'/>	    					    					    					    				
				        </logic:iterate>      				
						<bean:write name="_m_cdmForm" property="invCodeValue"/>	    				
    				</logic:present>      				
    			</td>
    		</tr>    	
    		<!-- DBTNO -->
    		<tr>
    			<td>
    				<t:writeCodeValue key="DBTNO" codeList="LIST_CODEMAINT"></t:writeCodeValue>
    			</td>
    			<td>
    				<bean:message key="screen.m_cdm.label_colon"/>
    				<logic:present name="_m_cdmForm" property="dbtno">
	    				<logic:iterate id="dbtno" name="_m_cdmForm" property="dbtno" indexId="i">
	    					<logic:present name="_m_cdmForm" property='<%= "dbtno[" + i + "].type_val" %>'>
	    						<t:writeCodeValue name="_m_cdmForm" property='<%= "dbtno[" + i + "].type_val" %>' codeList="LIST_TYPE_VAL"></t:writeCodeValue>
	    					</logic:present>
	    					<bean:write name="_m_cdmForm" property='<%= "dbtno[" + i + "].value" %>'/>	    					    					    					    					    				
				        </logic:iterate>      				
						<bean:write name="_m_cdmForm" property="dbtCodeValue"/>	    				
    				</logic:present>      				
    			</td>
    		</tr>    
    		<!-- CDTNO -->
    		<tr>
    			<td>
    				<t:writeCodeValue key="CDTNO" codeList="LIST_CODEMAINT"></t:writeCodeValue>
    			</td>
    			<td>
    				<bean:message key="screen.m_cdm.label_colon"/>
    				<logic:present name="_m_cdmForm" property="cdtno">
	    				<logic:iterate id="cdtno" name="_m_cdmForm" property="cdtno" indexId="i">
	    					<logic:present name="_m_cdmForm" property='<%= "cdtno[" + i + "].type_val" %>'>
	    						<t:writeCodeValue name="_m_cdmForm" property='<%= "cdtno[" + i + "].type_val" %>' codeList="LIST_TYPE_VAL"></t:writeCodeValue>
	    					</logic:present>
	    					<bean:write name="_m_cdmForm" property='<%= "cdtno[" + i + "].value" %>'/>	    					    					    					    					    					    				
				        </logic:iterate>      				
						<bean:write name="_m_cdmForm" property="cdtCodeValue"/>	    				
    				</logic:present>     				
    			</td>
    		</tr>
    		
    		<!-- NTINV -->
    		<tr>
    			<td>
    				<t:writeCodeValue key="NTINV" codeList="LIST_CODEMAINT"></t:writeCodeValue>
    			</td>
    			<td>
    				<bean:message key="screen.m_cdm.label_colon"/>
    				<logic:present name="_m_cdmForm" property="ntinv">
	    				<logic:iterate id="ntinv" name="_m_cdmForm" property="ntinv" indexId="i">
	    					<logic:present name="_m_cdmForm" property='<%= "ntinv[" + i + "].type_val" %>'>
	    						<t:writeCodeValue name="_m_cdmForm" property='<%= "ntinv[" + i + "].type_val" %>' codeList="LIST_TYPE_VAL"></t:writeCodeValue>
	    					</logic:present>
	    					<bean:write name="_m_cdmForm" property='<%= "ntinv[" + i + "].value" %>'/>	    					    					    					    					    					    				
				        </logic:iterate>      				
						<bean:write name="_m_cdmForm" property="ntinvCodeValue"/>	    				
    				</logic:present>     				
    			</td>
    		</tr>
    		<!-- RCPNO -->
    		<tr>
    			<td>
    				<t:writeCodeValue key="RCPNO" codeList="LIST_CODEMAINT"></t:writeCodeValue>
    			</td>
    			<td>
    				<bean:message key="screen.m_cdm.label_colon"/>
    				<logic:present name="_m_cdmForm" property="rcpno">
	    				<logic:iterate id="rcpno" name="_m_cdmForm" property="rcpno" indexId="i">
	    					<logic:present name="_m_cdmForm" property='<%= "rcpno[" + i + "].type_val" %>'>
	    						<t:writeCodeValue name="_m_cdmForm" property='<%= "rcpno[" + i + "].type_val" %>' codeList="LIST_TYPE_VAL"></t:writeCodeValue>
	    					</logic:present>
	    					<bean:write name="_m_cdmForm" property='<%= "rcpno[" + i + "].value" %>'/>	    					    					    					    					    					    				
				        </logic:iterate>      				
						<bean:write name="_m_cdmForm" property="rcpCodeValue"/>	    				
    				</logic:present>     				
    			</td>
    		</tr>
       		<!-- BACNO -->
    		<tr>
    			<td>
    				<t:writeCodeValue key="BACNO" codeList="LIST_CODEMAINT"></t:writeCodeValue>
    			</td>
    			<td>
    				<bean:message key="screen.m_cdm.label_colon"/>
    				<logic:present name="_m_cdmForm" property="bacno">
	    				<logic:iterate id="bacno" name="_m_cdmForm" property="bacno" indexId="i">
	    					<logic:present name="_m_cdmForm" property='<%= "bacno[" + i + "].type_val" %>'>
	    						<t:writeCodeValue name="_m_cdmForm" property='<%= "bacno[" + i + "].type_val" %>' codeList="LIST_TYPE_VAL"></t:writeCodeValue>
	    					</logic:present>
	    					<bean:write name="_m_cdmForm" property='<%= "bacno[" + i + "].value" %>'/>
				        </logic:iterate>    	
						<bean:write name="_m_cdmForm" property="bacCodeValue"/>
					</logic:present>    							        
    			</td>
    		</tr>      
    		<!-- SCPID -->
    		<tr>
    			<td>
    				<t:writeCodeValue key="SCPID" codeList="LIST_CODEMAINT"></t:writeCodeValue>
    			</td>
    			<td>
    				<bean:message key="screen.m_cdm.label_colon"/>
    				<logic:present name="_m_cdmForm" property="scpid">
	    				<logic:iterate id="scpid" name="_m_cdmForm" property="scpid" indexId="i">
	    					<logic:present name="_m_cdmForm" property='<%= "scpid[" + i + "].type_val" %>'>
	    						<t:writeCodeValue name="_m_cdmForm" property='<%= "scpid[" + i + "].type_val" %>' codeList="LIST_TYPE_VAL"></t:writeCodeValue>
	    					</logic:present>
	    					<bean:write name="_m_cdmForm" property='<%= "scpid[" + i + "].value" %>'/>	    					    					    					    					    					    				
				        </logic:iterate>      				
						<bean:write name="_m_cdmForm" property="scpCodeValue"/>	    				
    				</logic:present>     				
    			</td>
    		</tr>
    		<!-- CTMID -->
    		<tr>
    			<td>
    				<t:writeCodeValue key="CSTID" codeList="LIST_CODEMAINT"></t:writeCodeValue>
    			</td>
    			<td>
    				<bean:message key="screen.m_cdm.label_colon"/>
    				<logic:present name="_m_cdmForm" property="ctmid">
	    				<logic:iterate id="ctmid" name="_m_cdmForm" property="ctmid" indexId="i">
	    					<logic:present name="_m_cdmForm" property='<%= "ctmid[" + i + "].type_val" %>'>
	    						<t:writeCodeValue name="_m_cdmForm" property='<%= "ctmid[" + i + "].type_val" %>' codeList="LIST_TYPE_VAL"></t:writeCodeValue>
	    					</logic:present>
	    					<bean:write name="_m_cdmForm" property='<%= "ctmid[" + i + "].value" %>'/>	    					    					    					    					    					    				
				        </logic:iterate>      				
						<bean:write name="_m_cdmForm" property="ctmCodeValue"/>	    				
    				</logic:present>     				
    			</td>
    		</tr>
    		<!-- SOANO -->
    		<tr>
    			<td>
    				<t:writeCodeValue key="SOANO" codeList="LIST_CODEMAINT"></t:writeCodeValue>
    			</td>
    			<td>
    				<bean:message key="screen.m_cdm.label_colon"/>
    				<logic:present name="_m_cdmForm" property="soano">
	    				<logic:iterate id="soano" name="_m_cdmForm" property="soano" indexId="i">
	    					<logic:present name="_m_cdmForm" property='<%= "soano[" + i + "].type_val" %>'>
	    						<t:writeCodeValue name="_m_cdmForm" property='<%= "soano[" + i + "].type_val" %>' codeList="LIST_TYPE_VAL"></t:writeCodeValue>
	    					</logic:present>
	    					<bean:write name="_m_cdmForm" property='<%= "soano[" + i + "].value" %>'/>	    					    					    					    					    					    				
				        </logic:iterate>      				
						<bean:write name="_m_cdmForm" property="soaCodeValue"/>	    				
    				</logic:present>     				
    			</td>
    		</tr>  	  			
    	</table>		
		</logic:equal>    	
    	<!-- MODIFYING -->
    	<logic:notEqual name="_m_cdmForm" property="mode" value="READONLY">
    	<div class="scrollbar">
    	<table class="information" cellpadding="0" cellspacing="1">
    		<tr nowrap="nowrap">
    			<td colspan="2" class="header"><bean:message key="screen.m_cdm.label_general_info"/></td>
    		</tr>
    		<!-- QCSNO -->
    		<tr>
    			<td nowrap="nowrap">
    				<span id="qcsno"><t:writeCodeValue key="QCSNO" codeList="LIST_CODEMAINT"></t:writeCodeValue></span>
    			</td>
    			<td id="tdQCSNO" nowrap="nowrap">
    				<bean:message key="screen.m_cdm.label_colon"/>
    				<logic:present name="_m_cdmForm" property="qcsno">
	    				<logic:iterate id="qcsno" name="_m_cdmForm" property="qcsno" indexId="i">
		    				<html:text styleClass="hidden" name="_m_cdmForm" property='<%= "qcsno[" + i + "].status" %>'/>
		    				<html:text styleClass="hidden" name="_m_cdmForm" property='<%= "qcsno[" + i + "].init_val" %>'/>
		    				<html:select name="_m_cdmForm" property='<%= "qcsno[" + i + "].type_val" %>' onchange="changeCombobox(this,'qcsno');">
					          <html:option value="" ><bean:message key="screen.m_cdm.label_blank"/></html:option>
					          <html:options collection="LIST_TYPE_VAL" property="id" labelProperty="name"/>
					        </html:select>
					        <html:text name="_m_cdmForm" property='<%= "qcsno[" + i + "].value" %>'></html:text>
					        <html:select name="_m_cdmForm" property='<%= "qcsno[" + i + "].reset_no" %>'>
					        	<html:options collection="LIST_RESET_NO" property="id" labelProperty="name"/>
					        </html:select>
				        </logic:iterate>    
	    				<a href="#" onclick="addCodeValue('qcsno');"><bean:message key="screen.m_cdm.button_add"/></a>
	    				<a href="#" onclick="removeCodeValue('qcsno');"><bean:message key="screen.m_cdm.button_remove"/></a>
						<bean:write name="_m_cdmForm" property="qcsCodeValue"/>
						<input type="hidden" name="qcsnoPos"/>
					</logic:present>    							        
    			</td>
    		</tr>      
    		<!-- QUONO -->
    		<tr>
    			<td nowrap="nowrap">
    				<span id="quono"><t:writeCodeValue key="QUONO" codeList="LIST_CODEMAINT"></t:writeCodeValue></span>
    			</td>
    			<td id="tdQCSNO" nowrap="nowrap">
    				<bean:message key="screen.m_cdm.label_colon"/>
    				<logic:present name="_m_cdmForm" property="quono">
	    				<logic:iterate id="quono" name="_m_cdmForm" property="quono" indexId="i">
		    				<html:text styleClass="hidden" name="_m_cdmForm" property='<%= "quono[" + i + "].status" %>'/>
		    				<html:text styleClass="hidden" name="_m_cdmForm" property='<%= "quono[" + i + "].init_val" %>'/>
		    				<html:select name="_m_cdmForm" property='<%= "quono[" + i + "].type_val" %>' onchange="changeCombobox(this,'quono');">
					          <html:option value="" ><bean:message key="screen.m_cdm.label_blank"/></html:option>
					          <html:options collection="LIST_TYPE_VAL" property="id" labelProperty="name"/>
					        </html:select>					
					        <html:text name="_m_cdmForm" property='<%= "quono[" + i + "].value" %>'></html:text>
					        <html:select name="_m_cdmForm" property='<%= "quono[" + i + "].reset_no" %>'>
					        	<html:options collection="LIST_RESET_NO" property="id" labelProperty="name"/>
					        </html:select>
				        </logic:iterate>  
	    				<a href="#" onclick="addCodeValue('quono');"><bean:message key="screen.m_cdm.button_add"/></a>
	    				<a href="#" onclick="removeCodeValue('quono');"><bean:message key="screen.m_cdm.button_remove"/></a>
						<bean:write name="_m_cdmForm" property="quoCodeValue"/>
						<input type="hidden" name="quonoPos"/>	    				
    				</logic:present>
    			</td>
    		</tr>     	
    		
    		
    		<!-- BIFNO -->
    		<tr>
    			<td nowrap="nowrap">
    				<span id="bifno"><t:writeCodeValue key="BIFNO" codeList="LIST_CODEMAINT"></t:writeCodeValue></span>
    			</td>
    			<td id="tdBIFNO" nowrap="nowrap">
    				<bean:message key="screen.m_cdm.label_colon"/>
    				<logic:present name="_m_cdmForm" property="bifno">
	    				<logic:iterate id="bifno" name="_m_cdmForm" property="bifno" indexId="i">
		    				<html:text styleClass="hidden" styleClass="hidden" name="_m_cdmForm" property='<%= "bifno[" + i + "].status" %>'/>
		    				<html:text styleClass="hidden" styleClass="hidden" name="_m_cdmForm" property='<%= "bifno[" + i + "].init_val" %>'/>
		    				<html:select name="_m_cdmForm" property='<%= "bifno[" + i + "].type_val" %>' onchange="changeCombobox(this,'bifno');">
					          <html:option value="" ><bean:message key="screen.m_cdm.label_blank"/></html:option>
					          <html:options collection="LIST_TYPE_VAL" property="id" labelProperty="name"/>
					        </html:select>					
					        <html:text name="_m_cdmForm" property='<%= "bifno[" + i + "].value" %>'></html:text>
					        <html:select name="_m_cdmForm" property='<%= "bifno[" + i + "].reset_no" %>'>
					        	<html:options collection="LIST_RESET_NO" property="id" labelProperty="name"/>
					        </html:select>
				        </logic:iterate>   
	    				<a href="#" onclick="addCodeValue('bifno');"><bean:message key="screen.m_cdm.button_add"/></a>
	    				<a href="#" onclick="removeCodeValue('bifno');"><bean:message key="screen.m_cdm.button_remove"/></a>
						<bean:write name="_m_cdmForm" property="bifCodeValue"/>
						<input type="hidden" name="bifnoPos"/>	    				
    				</logic:present>    				
    			</td>
    		</tr>      			 		   
    		
    		<!-- BIFDN -->
    		<tr>
    			<td nowrap="nowrap">
    				<span id="bifdn"><t:writeCodeValue key="BIFDN" codeList="LIST_CODEMAINT"></t:writeCodeValue></span>
    			</td>
    			<td id="tdBIFDN" nowrap="nowrap">
    				<bean:message key="screen.m_cdm.label_colon"/>
    				<logic:present name="_m_cdmForm" property="bifdn">
	    				<logic:iterate id="bifdn" name="_m_cdmForm" property="bifdn" indexId="i">
		    				<html:text styleClass="hidden" styleClass="hidden" name="_m_cdmForm" property='<%= "bifdn[" + i + "].status" %>'/>
		    				<html:text styleClass="hidden" styleClass="hidden" name="_m_cdmForm" property='<%= "bifdn[" + i + "].init_val" %>'/>
		    				<html:select name="_m_cdmForm" property='<%= "bifdn[" + i + "].type_val" %>' onchange="changeCombobox(this,'bifdn');">
					          <html:option value="" ><bean:message key="screen.m_cdm.label_blank"/></html:option>
					          <html:options collection="LIST_TYPE_VAL" property="id" labelProperty="name"/>
					        </html:select>					
					        <html:text name="_m_cdmForm" property='<%= "bifdn[" + i + "].value" %>'></html:text>
					        <html:select name="_m_cdmForm" property='<%= "bifdn[" + i + "].reset_no" %>'>
					        	<html:options collection="LIST_RESET_NO" property="id" labelProperty="name"/>
					        </html:select>
				        </logic:iterate>   
	    				<a href="#" onclick="addCodeValue('bifdn');"><bean:message key="screen.m_cdm.button_add"/></a>
	    				<a href="#" onclick="removeCodeValue('bifdn');"><bean:message key="screen.m_cdm.button_remove"/></a>
						<bean:write name="_m_cdmForm" property="bifdnCodeValue"/>
						<input type="hidden" name="bifdnPos"/>	    				
    				</logic:present>    				
    			</td>
    		</tr>    
    		<!-- BIFCN -->
    		<tr>
    			<td nowrap="nowrap">
    				<span id="bifcn"><t:writeCodeValue key="BIFCN" codeList="LIST_CODEMAINT"></t:writeCodeValue></span>
    			</td>
    			<td id="tdBIFCN" nowrap="nowrap">
    				<bean:message key="screen.m_cdm.label_colon"/>
    				<logic:present name="_m_cdmForm" property="bifcn">
	    				<logic:iterate id="bifcn" name="_m_cdmForm" property="bifcn" indexId="i">
		    				<html:text styleClass="hidden" styleClass="hidden" name="_m_cdmForm" property='<%= "bifcn[" + i + "].status" %>'/>
		    				<html:text styleClass="hidden" styleClass="hidden" name="_m_cdmForm" property='<%= "bifcn[" + i + "].init_val" %>'/>
		    				<html:select name="_m_cdmForm" property='<%= "bifcn[" + i + "].type_val" %>' onchange="changeCombobox(this,'bifcn');">
					          <html:option value="" ><bean:message key="screen.m_cdm.label_blank"/></html:option>
					          <html:options collection="LIST_TYPE_VAL" property="id" labelProperty="name"/>
					        </html:select>					
					        <html:text name="_m_cdmForm" property='<%= "bifcn[" + i + "].value" %>'></html:text>
					        <html:select name="_m_cdmForm" property='<%= "bifcn[" + i + "].reset_no" %>'>
					        	<html:options collection="LIST_RESET_NO" property="id" labelProperty="name"/>
					        </html:select>
				        </logic:iterate>   
	    				<a href="#" onclick="addCodeValue('bifcn');"><bean:message key="screen.m_cdm.button_add"/></a>
	    				<a href="#" onclick="removeCodeValue('bifcn');"><bean:message key="screen.m_cdm.button_remove"/></a>
						<bean:write name="_m_cdmForm" property="bifcnCodeValue"/>
						<input type="hidden" name="bifcnPos"/>	    				
    				</logic:present>    				
    			</td>
    		</tr>
    		
    		  			 		         			 		         		        			
    		<!-- INVNO -->
    		<tr>
    			<td nowrap="nowrap">
    				<span id="invno"><t:writeCodeValue key="INVNO" codeList="LIST_CODEMAINT"></t:writeCodeValue></span>
    			</td>
    			<td id="tdINVNO" nowrap="nowrap">
    				<bean:message key="screen.m_cdm.label_colon"/>
    				<logic:present name="_m_cdmForm" property="invno">
	    				<logic:iterate id="invno" name="_m_cdmForm" property="invno" indexId="i">
		    				<html:text styleClass="hidden" name="_m_cdmForm" property='<%= "invno[" + i + "].status" %>'/>
		    				<html:text styleClass="hidden" name="_m_cdmForm" property='<%= "invno[" + i + "].init_val" %>'/>
		    				<html:select name="_m_cdmForm" property='<%= "invno[" + i + "].type_val" %>' onchange="changeCombobox(this,'invno');">
					          <html:option value="" ><bean:message key="screen.m_cdm.label_blank"/></html:option>
					          <html:options collection="LIST_TYPE_VAL" property="id" labelProperty="name"/>
					        </html:select>					
					        <html:text name="_m_cdmForm" property='<%= "invno[" + i + "].value" %>'></html:text>
					        <html:select name="_m_cdmForm" property='<%= "invno[" + i + "].reset_no" %>'>
					        	<html:options collection="LIST_RESET_NO" property="id" labelProperty="name"/>
					        </html:select>
				        </logic:iterate>    
	    				<a href="#" onclick="addCodeValue('invno');"><bean:message key="screen.m_cdm.button_add"/></a>
	    				<a href="#" onclick="removeCodeValue('invno');"><bean:message key="screen.m_cdm.button_remove"/></a>
						<bean:write name="_m_cdmForm" property="invCodeValue"/>
						<input type="hidden" name="invnoPos"/>	    				
    				</logic:present>      				
    			</td>
    		</tr>    	
    		<!-- DBTNO -->
    		<tr>
    			<td nowrap="nowrap">
    				<span id="dbtno"><t:writeCodeValue key="DBTNO" codeList="LIST_CODEMAINT"></t:writeCodeValue></span>
    			</td>
    			<td id="tdDBTNO" nowrap="nowrap">
    				<bean:message key="screen.m_cdm.label_colon"/>
    				<logic:present name="_m_cdmForm" property="dbtno">
	    				<logic:iterate id="dbtno" name="_m_cdmForm" property="dbtno" indexId="i">
		    				<html:text styleClass="hidden" name="_m_cdmForm" property='<%= "dbtno[" + i + "].status" %>'/>
		    				<html:text styleClass="hidden" name="_m_cdmForm" property='<%= "dbtno[" + i + "].init_val" %>'/>
		    				<html:select name="_m_cdmForm" property='<%= "dbtno[" + i + "].type_val" %>' onchange="changeCombobox(this,'dbtno');">
					          <html:option value="" ><bean:message key="screen.m_cdm.label_blank"/></html:option>
					          <html:options collection="LIST_TYPE_VAL" property="id" labelProperty="name"/>
					        </html:select>					
					        <html:text name="_m_cdmForm" property='<%= "dbtno[" + i + "].value" %>'></html:text>
					        <html:select name="_m_cdmForm" property='<%= "dbtno[" + i + "].reset_no" %>'>
					        	<html:options collection="LIST_RESET_NO" property="id" labelProperty="name"/>
					        </html:select>
				        </logic:iterate>   
	    				<a href="#" onclick="addCodeValue('dbtno');"><bean:message key="screen.m_cdm.button_add"/></a>
	    				<a href="#" onclick="removeCodeValue('dbtno');"><bean:message key="screen.m_cdm.button_remove"/></a>
						<bean:write name="_m_cdmForm" property="dbtCodeValue"/>
						<input type="hidden" name="dbtnoPos"/>	    				
    				</logic:present>      				
    			</td>
    		</tr>    
    		<!-- CDTNO -->
    		<tr>
    			<td nowrap="nowrap">
    				<span id="cdtno"><t:writeCodeValue key="CDTNO" codeList="LIST_CODEMAINT"></t:writeCodeValue></span>
    			</td>
    			<td id="tdCDTNO" nowrap="nowrap">
    				<bean:message key="screen.m_cdm.label_colon"/>
    				<logic:present name="_m_cdmForm" property="cdtno">
	    				<logic:iterate id="cdtno" name="_m_cdmForm" property="cdtno" indexId="i">
		    				<html:text styleClass="hidden" name="_m_cdmForm" property='<%= "cdtno[" + i + "].status" %>'/>
		    				<html:text styleClass="hidden" name="_m_cdmForm" property='<%= "cdtno[" + i + "].init_val" %>'/>
		    				<html:select name="_m_cdmForm" property='<%= "cdtno[" + i + "].type_val" %>' onchange="changeCombobox(this,'cdtno');"> 
					          <html:option value="" ><bean:message key="screen.m_cdm.label_blank"/></html:option>
					          <html:options collection="LIST_TYPE_VAL" property="id" labelProperty="name"/>
					        </html:select>					
					        <html:text name="_m_cdmForm" property='<%= "cdtno[" + i + "].value" %>'></html:text>
					        <html:select name="_m_cdmForm" property='<%= "cdtno[" + i + "].reset_no" %>'>
					        	<html:options collection="LIST_RESET_NO" property="id" labelProperty="name"/>
					        </html:select>
				        </logic:iterate>  
	    				<a href="#" onclick="addCodeValue('cdtno');"><bean:message key="screen.m_cdm.button_add"/></a>
	    				<a href="#" onclick="removeCodeValue('cdtno');"><bean:message key="screen.m_cdm.button_remove"/></a>
						<bean:write name="_m_cdmForm" property="cdtCodeValue"/>
						<input type="hidden" name="cdtnoPos"/>	    				
    				</logic:present>     				
    			</td>
    		</tr>
    		
    		<!-- NTINV -->
    		<tr>
    			<td nowrap="nowrap">
    				<span id="ntinv"><t:writeCodeValue key="NTINV" codeList="LIST_CODEMAINT"></t:writeCodeValue></span>
    			</td>
    			<td id="tdNTINV" nowrap="nowrap">
    				<bean:message key="screen.m_cdm.label_colon"/>
    				<logic:present name="_m_cdmForm" property="ntinv">
	    				<logic:iterate id="ntinv" name="_m_cdmForm" property="ntinv" indexId="i">
		    				<html:text styleClass="hidden" name="_m_cdmForm" property='<%= "ntinv[" + i + "].status" %>'/>
		    				<html:text styleClass="hidden" name="_m_cdmForm" property='<%= "ntinv[" + i + "].init_val" %>'/>
		    				<html:select name="_m_cdmForm" property='<%= "ntinv[" + i + "].type_val" %>' onchange="changeCombobox(this,'ntinv');"> 
					          <html:option value="" ><bean:message key="screen.m_cdm.label_blank"/></html:option>
					          <html:options collection="LIST_TYPE_VAL" property="id" labelProperty="name"/>
					        </html:select>					
					        <html:text name="_m_cdmForm" property='<%= "ntinv[" + i + "].value" %>'></html:text>
					        <html:select name="_m_cdmForm" property='<%= "ntinv[" + i + "].reset_no" %>'>
					        	<html:options collection="LIST_RESET_NO" property="id" labelProperty="name"/>
					        </html:select>
				        </logic:iterate>  
	    				<a href="#" onclick="addCodeValue('ntinv');"><bean:message key="screen.m_cdm.button_add"/></a>
	    				<a href="#" onclick="removeCodeValue('ntinv');"><bean:message key="screen.m_cdm.button_remove"/></a>
						<bean:write name="_m_cdmForm" property="ntinvCodeValue"/>
						<input type="hidden" name="ntinvPos"/>	    				
    				</logic:present>     				
    			</td>
    		</tr>
    		
    		<!-- RCPNO -->
    		<tr>
    			<td nowrap="nowrap">
    				<span id="rcpno"><t:writeCodeValue key="RCPNO" codeList="LIST_CODEMAINT"></t:writeCodeValue></span>
    			</td>
    			<td id="tdRCPNO" nowrap="nowrap">
    				<bean:message key="screen.m_cdm.label_colon"/>
    				<logic:present name="_m_cdmForm" property="rcpno">
	    				<logic:iterate id="rcpno" name="_m_cdmForm" property="rcpno" indexId="i">
		    				<html:text styleClass="hidden" name="_m_cdmForm" property='<%= "rcpno[" + i + "].status" %>'/>
		    				<html:text styleClass="hidden" name="_m_cdmForm" property='<%= "rcpno[" + i + "].init_val" %>'/>
		    				<html:select name="_m_cdmForm" property='<%= "rcpno[" + i + "].type_val" %>' onchange="changeCombobox(this,'rcpno');"> 
					          <html:option value="" ><bean:message key="screen.m_cdm.label_blank"/></html:option>
					          <html:options collection="LIST_TYPE_VAL" property="id" labelProperty="name"/>
					        </html:select>					
					        <html:text name="_m_cdmForm" property='<%= "rcpno[" + i + "].value" %>'></html:text>
					        <html:select name="_m_cdmForm" property='<%= "rcpno[" + i + "].reset_no" %>'>
					        	<html:options collection="LIST_RESET_NO" property="id" labelProperty="name"/>
					        </html:select>
				        </logic:iterate>  
	    				<a href="#" onclick="addCodeValue('rcpno');"><bean:message key="screen.m_cdm.button_add"/></a>
	    				<a href="#" onclick="removeCodeValue('rcpno');"><bean:message key="screen.m_cdm.button_remove"/></a>
						<bean:write name="_m_cdmForm" property="rcpCodeValue"/>
						<input type="hidden" name="rcpnoPos"/>	    				
    				</logic:present>     				
    			</td>
    		</tr>
    		<!-- BACNO -->
    		<tr>
    			<td nowrap="nowrap">
    				<span id="bacno"><t:writeCodeValue key="BACNO" codeList="LIST_CODEMAINT"></t:writeCodeValue></span>
    			</td>
    			<td id="tdBACNO" nowrap="nowrap">
    				<bean:message key="screen.m_cdm.label_colon"/>
    				<logic:present name="_m_cdmForm" property="bacno">
	    				<logic:iterate id="bacno" name="_m_cdmForm" property="bacno" indexId="i">
		    				<html:text styleClass="hidden" name="_m_cdmForm" property='<%= "bacno[" + i + "].status" %>'/>
		    				<html:text styleClass="hidden" name="_m_cdmForm" property='<%= "bacno[" + i + "].init_val" %>'/>
		    				<html:select name="_m_cdmForm" property='<%= "bacno[" + i + "].type_val" %>' onchange="changeCombobox(this,'bacno');">
					          <html:option value="" ><bean:message key="screen.m_cdm.label_blank"/></html:option>
					          <html:options collection="LIST_TYPE_VAL" property="id" labelProperty="name"/>
					        </html:select>
					        <html:text name="_m_cdmForm" property='<%= "bacno[" + i + "].value" %>'></html:text>
					        <html:select name="_m_cdmForm" property='<%= "bacno[" + i + "].reset_no" %>'>
					        	<html:options collection="LIST_RESET_NO" property="id" labelProperty="name"/>
					        </html:select>
				        </logic:iterate>    
	    				<a href="#" onclick="addCodeValue('bacno');"><bean:message key="screen.m_cdm.button_add"/></a>
	    				<a href="#" onclick="removeCodeValue('bacno');"><bean:message key="screen.m_cdm.button_remove"/></a>
						<bean:write name="_m_cdmForm" property="bacCodeValue"/>
						<input type="hidden" name="bacnoPos"/>
					</logic:present>    							        
    			</td>
    		</tr>      
    		<!-- SCPID -->
    		<tr>
    			<td nowrap="nowrap">
    				<span id="scpid"><t:writeCodeValue key="SCPID" codeList="LIST_CODEMAINT"></t:writeCodeValue></span>
    			</td>
    			<td id="tdSCPID" nowrap="nowrap">
    				<bean:message key="screen.m_cdm.label_colon"/>
    				<logic:present name="_m_cdmForm" property="scpid">
	    				<logic:iterate id="scpid" name="_m_cdmForm" property="scpid" indexId="i">
		    				<html:text styleClass="hidden" name="_m_cdmForm" property='<%= "scpid[" + i + "].status" %>'/>
		    				<html:text styleClass="hidden" name="_m_cdmForm" property='<%= "scpid[" + i + "].init_val" %>'/>
		    				<html:select name="_m_cdmForm" property='<%= "scpid[" + i + "].type_val" %>' onchange="changeCombobox(this,'scpid');"> 
					          <html:option value="" ><bean:message key="screen.m_cdm.label_blank"/></html:option>
					          <html:options collection="LIST_TYPE_VAL" property="id" labelProperty="name"/>
					        </html:select>					
					        <html:text name="_m_cdmForm" property='<%= "scpid[" + i + "].value" %>'></html:text>
					        <html:select name="_m_cdmForm" property='<%= "scpid[" + i + "].reset_no" %>'>
					        	<html:options collection="LIST_RESET_NO" property="id" labelProperty="name"/>
					        </html:select>
				        </logic:iterate>  
	    				<a href="#" onclick="addCodeValue('scpid');"><bean:message key="screen.m_cdm.button_add"/></a>
	    				<a href="#" onclick="removeCodeValue('scpid');"><bean:message key="screen.m_cdm.button_remove"/></a>
						<bean:write name="_m_cdmForm" property="scpCodeValue"/>
						<input type="hidden" name="scpidPos"/>	    				
    				</logic:present>     				
    			</td>
    		</tr>
    		<!-- CTMID -->
    		<tr>
    			<td nowrap="nowrap">
    				<span id="ctmid"><t:writeCodeValue key="CSTID" codeList="LIST_CODEMAINT"></t:writeCodeValue></span>
    			</td>
    			<td id="tdCTMID" nowrap="nowrap">
    				<bean:message key="screen.m_cdm.label_colon"/>
    				<logic:present name="_m_cdmForm" property="ctmid">
	    				<logic:iterate id="ctmid" name="_m_cdmForm" property="ctmid" indexId="i">
		    				<html:text styleClass="hidden" name="_m_cdmForm" property='<%= "ctmid[" + i + "].status" %>'/>
		    				<html:text styleClass="hidden" name="_m_cdmForm" property='<%= "ctmid[" + i + "].init_val" %>'/>
		    				<html:select name="_m_cdmForm" property='<%= "ctmid[" + i + "].type_val" %>' onchange="changeCombobox(this,'ctmid');"> 
					          <html:option value="" ><bean:message key="screen.m_cdm.label_blank"/></html:option>
					          <html:options collection="LIST_TYPE_VAL" property="id" labelProperty="name"/>
					        </html:select>					
					        <html:text name="_m_cdmForm" property='<%= "ctmid[" + i + "].value" %>'></html:text>
					        <html:select name="_m_cdmForm" property='<%= "ctmid[" + i + "].reset_no" %>'>
					        	<html:options collection="LIST_RESET_NO" property="id" labelProperty="name"/>
					        </html:select>
				        </logic:iterate>  
	    				<a href="#" onclick="addCodeValue('ctmid');"><bean:message key="screen.m_cdm.button_add"/></a>
	    				<a href="#" onclick="removeCodeValue('ctmid');"><bean:message key="screen.m_cdm.button_remove"/></a>
						<bean:write name="_m_cdmForm" property="ctmCodeValue"/>
						<input type="hidden" name="ctmidPos"/>	    				
    				</logic:present>     				
    			</td>
    		</tr>
    		<!-- BACNO -->
    		<tr>
    			<td nowrap="nowrap">
    				<span id="soano"><t:writeCodeValue key="SOANO" codeList="LIST_CODEMAINT"></t:writeCodeValue></span>
    			</td>
    			<td id="tdSOANO" nowrap="nowrap">
    				<bean:message key="screen.m_cdm.label_colon"/>
    				<logic:present name="_m_cdmForm" property="soano">
	    				<logic:iterate id="soano" name="_m_cdmForm" property="soano" indexId="i">
		    				<html:text styleClass="hidden" name="_m_cdmForm" property='<%= "soano[" + i + "].status" %>'/>
		    				<html:text styleClass="hidden" name="_m_cdmForm" property='<%= "soano[" + i + "].init_val" %>'/>
		    				<html:select name="_m_cdmForm" property='<%= "soano[" + i + "].type_val" %>' onchange="changeCombobox(this,'soano');">
					          <html:option value="" ><bean:message key="screen.m_cdm.label_blank"/></html:option>
					          <html:options collection="LIST_TYPE_VAL" property="id" labelProperty="name"/>
					        </html:select>
					        <html:text name="_m_cdmForm" property='<%= "soano[" + i + "].value" %>'></html:text>
					        <html:select name="_m_cdmForm" property='<%= "soano[" + i + "].reset_no" %>'>
					        	<html:options collection="LIST_RESET_NO" property="id" labelProperty="name"/>
					        </html:select>
				        </logic:iterate>    
	    				<a href="#" onclick="addCodeValue('soano');"><bean:message key="screen.m_cdm.button_add"/></a>
	    				<a href="#" onclick="removeCodeValue('soano');"><bean:message key="screen.m_cdm.button_remove"/></a>
						<bean:write name="_m_cdmForm" property="soaCodeValue"/>
						<input type="hidden" name="soanoPos"/>
					</logic:present>    							        
    			</td>
    		</tr>
    		<tr><td colspan="2"></td></tr>  			
    	</table>
    	</div>	
    	</logic:notEqual> 
    	<hr class="lineBottom" size="3">
    	<logic:notEqual name="_m_cdmForm" property="mode" value="READONLY">
		<table class="buttonGroup" cellpadding="0" cellspacing="0">
			<tr>
				<td>
					<button onclick="submitForm();"><bean:message key="screen.m_cdm.button_save"/></button>
				</td>
			</tr>
		</table>
		</logic:notEqual>	
		<html:select name="_m_cdmForm" property="hidResetNoOption" styleId="hidResetNoOption" style="display:none">
        	<html:options collection="LIST_RESET_NO" property="id" labelProperty="name"/>
        </html:select>
		<div class="error">
		<html:messages id="message">
			<bean:write name="message"/><br/>
		</html:messages>
	</div>
	<div class="message">
		<ts:messages id="messages" message="true">
			<bean:write name="messages"/><br/>
		</ts:messages>
   	</div>
		</ts:form>   	
	</body>
</html:html>

