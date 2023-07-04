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
		<link type="text/css" rel="stylesheet" href="css/m_csts02.css" />
   		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
    	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/stylesheet/tabcontent.css"/>
    	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/tabcontent.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>     	   		
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   		<script type="text/javascript" src="js/m_csts02.js"></script>
   		<script type='text/javascript' 
        src='http://getfirebug.com/releases/lite/1.2/firebug-lite-compressed.js'></script>
		<title></title>
	</head>
	<body id="m_csts02" onload="initMain();initPage();">
		<ts:form action="/M_CSTS02DSP">
		<t:defineCodeList id="LIST_COUNTRY"/>
		<html:hidden name="_m_cstForm" property="event"/>
		<html:hidden name="_m_cstForm" property="mode"/>
		<html:hidden name="_m_cstForm" property="id_cust"/>
		<html:hidden name="_m_cstForm" property="temp_cust_acc_no"/>
		<html:hidden name="_m_cstForm" property="temp_cust_name"/>
		<html:hidden name="_m_cstForm" property="temp_co_regno"/>
		<html:hidden name="_m_cstForm" property="temp_country"/>
		
		<bean:define name="_m_cstForm" property="classViewMode" id="classViewMode"></bean:define>
		<bean:define name="_m_cstForm" property="classNewMode" id="classNewMode"></bean:define>

		<table class="subHeader" cellpadding="0" cellspacing="0">
    		<tr>
    			<td><bean:message key="screen.m_cst.screen_name"/></td>
    		</tr>
    	</table>

		<table class="information" cellpadding="0" cellspacing="1">
			<tr>
				<td colspan="4" class="header"><bean:message key="screen.m_cst.label_general_info"/></td>
			</tr>
			<tr>
				<td width="15%"></td>
				<td width="35%"></td>
				<td width="15%"></td>
				<td width="35%"></td>				
			</tr>
			<tr>
				<!-- customer name -->
				<td>
					<bean:message key="screen.m_cst.label_customer_name"/>
					<span class="mandatory"><bean:message key="screen.m_cst.label_mandatory"/></span>
				</td>
				<td>
					<bean:message key="screen.m_cst.label_colon"/>
					<span id="spn_cust_name" class="<%= classViewMode %>"><bean:write name="_m_cstForm" property="cust_name"></bean:write></span>
					<span class="<%= classNewMode%>"><html:text name="_m_cstForm" property="cust_name" size="25" maxlength="100"></html:text></span>									
				</td>
				<!-- customer acc no -->					
				<td>
					<bean:message key="screen.m_cst.label_customer_acc_no"/>
					<span class="mandatory"><bean:message key="screen.m_cst.label_mandatory"/></span>				
				</td>
				<td>
					<bean:message key="screen.m_cst.label_colon"/>
					<span id="spn_cust_acc_no" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="cust_acc_no"></bean:write></span>
					<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="cust_acc_no" size="25" maxlength="15"></html:text></span>				
				</td>				
			</tr>
			<tr>
				<!-- company reg no -->
				<td>
					<bean:message key="screen.m_cst.label_company_reg_no"/>
					<span class="mandatory"><bean:message key="screen.m_cst.label_mandatory"/></span>				
				</td>
				<td>
					<bean:message key="screen.m_cst.label_colon"/>
					<span id="spn_co_regno" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="co_regno"></bean:write></span>
					<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="co_regno" size="25" maxlength="15"></html:text></span>				
				</td>
				<td colspan="2" rowspan="6" valign="top">
					<fieldset style="width: 350px;">
						<legend><bean:message key="screen.m_cst.label_default_setting"/></legend>
						<!-- loading bill_type -->
						<span id="spn_bill_type" class="hidden"><bean:write name="_m_cstForm" property="bill_type"/></span>
						<!-- loading isp -->
						<span id="spn_isp" class="hidden"><bean:write name="_m_cstForm" property="isp"/></span>
						<!-- loading gin -->
						<span id="spn_gin" class="hidden"><bean:write name="_m_cstForm" property="gin"/></span>
						<!-- loading idc -->
						<span id="spn_idc" class="hidden"><bean:write name="_m_cstForm" property="idc"/></span>
						<!-- loading sim -->
						<span id="spn_sim" class="hidden"><bean:write name="_m_cstForm" property="sim"/></span>		
						<logic:equal name="_m_cstForm" property="mode" value="READONLY">
							<span class="checkboxes"><html:radio styleId="opt_bill_type_M" disabled="true" name="_m_cstForm" property="bill_type" value="M"><bean:message key="screen.m_cst.label_merge"/></html:radio></span> 
							<span class="checkboxes"><html:radio styleId="opt_bill_type_I" disabled="true" name="_m_cstForm" property="bill_type" value="I"><bean:message key="screen.m_cst.label_individual"/></html:radio></span>
							<br>
							<span class="checkboxes"><html:checkbox styleId="chk_isp" disabled="true" name="_m_cstForm" property="isp"><bean:message key="screen.m_cst.label_isp"/></html:checkbox></span>														
							<span class="checkboxes"><html:checkbox styleId="chk_gin" disabled="true" name="_m_cstForm" property="gin"><bean:message key="screen.m_cst.label_gin"/></html:checkbox></span>
							<br>
							<span class="checkboxes"><html:checkbox styleId="chk_idc" disabled="true" name="_m_cstForm" property="idc"><bean:message key="screen.m_cst.label_idc"/></html:checkbox></span>
							<span class="lastCheckboxes"><html:checkbox styleId="chk_sim" disabled="true" name="_m_cstForm" property="sim"><bean:message key="screen.m_cst.label_si_maintenance"/></html:checkbox></span>
						</logic:equal>		
						<logic:notEqual name="_m_cstForm" property="mode" value="READONLY">
							<span class="checkboxes"><html:radio styleId="opt_bill_type_M" name="_m_cstForm" property="bill_type" value="M" onclick="changeOption('M');"><bean:message key="screen.m_cst.label_merge"/></html:radio></span>
							<span class="checkboxes"><html:radio styleId="opt_bill_type_I" name="_m_cstForm" property="bill_type" value="I" onclick="changeOption('I');"><bean:message key="screen.m_cst.label_individual"/></html:radio></span>
							<br>
							<span class="checkboxes"><html:checkbox styleId="chk_isp" name="_m_cstForm" property="isp"><bean:message key="screen.m_cst.label_isp"/></html:checkbox></span>							
							<span class="checkboxes"><html:checkbox styleId="chk_gin" name="_m_cstForm" property="gin"><bean:message key="screen.m_cst.label_gin"/></html:checkbox></span>
							<br>							
							<span class="checkboxes"><html:checkbox styleId="chk_idc" name="_m_cstForm" property="idc"><bean:message key="screen.m_cst.label_idc"/></html:checkbox></span>
							<span class="lastCheckboxes"><html:checkbox styleId="chk_sim" name="_m_cstForm" property="sim"><bean:message key="screen.m_cst.label_si_maintenance"/></html:checkbox></span>							
						</logic:notEqual>														
					</fieldset>						
				</td>		
			</tr>	
			<tr>
				<!-- company website -->
				<td><bean:message key="screen.m_cst.label_company_website"/></td>
				<td>
					<bean:message key="screen.m_cst.label_colon"/>
					<span id="spn_co_website" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="co_website"></bean:write></span>
					<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="co_website" size="25" maxlength="100"></html:text></span>				
				</td>
				<td colspan="2"></td>				
			</tr>
			<tr>
				<!-- company email -->
				<td><bean:message key="screen.m_cst.label_company_email"/></td>
				<td>
					<bean:message key="screen.m_cst.label_colon"/>				
					<span id="spn_co_email" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="co_email"></bean:write></span>
					<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="co_email" size="25" maxlength="50"></html:text></span>				
				</td>
			</tr>	
			<tr>
				<!-- telephone no -->	
				<td><bean:message key="screen.m_cst.label_telephone_no"/></td>
				<td>
					<bean:message key="screen.m_cst.label_colon"/>
					<span id="spn_co_tel_no" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="co_tel_no"></bean:write></span>
					<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="co_tel_no" size="25" maxlength="16"></html:text></span>				
				</td>
			</tr>
			<tr>
				<!-- fax no -->
				<td>
					<bean:message key="screen.m_cst.label_fax_no"/>
					<span class="mandatory"><bean:message key="screen.m_cst.label_mandatory"/></span>				
				</td>
				<td>
					<bean:message key="screen.m_cst.label_colon"/>
					<span id="spn_co_fax_no" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="co_fax_no"></bean:write></span>
					<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="co_fax_no" size="25" maxlength="15"></html:text></span>				
				</td>
			</tr>	
			<tr>
				<!-- checkbox -->
				<td colspan="2">
					<!-- loading inter_comp -->
					<span id="spn_inter_comp" class="hidden"><bean:write name="_m_cstForm" property="inter_comp"/></span>
					<logic:equal name="_m_cstForm" property="mode" value="READONLY">
						<html:checkbox styleId="chk_inter_comp" disabled="true" name="_m_cstForm" property="inter_comp"><bean:message key="screen.m_cst.label_inter_company"/></html:checkbox>
					</logic:equal>
					<logic:notEqual name="_m_cstForm" property="mode" value="READONLY">
						<html:checkbox styleId="chk_inter_comp" name="_m_cstForm" property="inter_comp"><bean:message key="screen.m_cst.label_inter_company"/></html:checkbox>
					</logic:notEqual>					
				</td>				
			</tr>											
		</table>

		<!-- addresses -->
		<table class="information" cellpadding="0" cellspacing="1">
			<tr>
				<td colspan="2" class="header"><bean:message key="screen.m_cst.label_addresses"/></td>
			</tr>
			<tr>
				<td width="50%" class="titleLink">
					<span class="underlineTitle"><bean:message key="screen.m_cst.label_registered_address"/></span>
					<span class="mandatory"><bean:message key="screen.m_cst.label_mandatory"/></span>				
				</td>
				<td width="50%" class="titleLink">
					<span class="underlineTitle"><bean:message key="screen.m_cst.label_cpd_address"/></span>				
				</td>				
			</tr>
			<tr>
				<td>
					<span id="spn_ra_adr_line1" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="ra_adr_line1"></bean:write></span>
					<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="ra_adr_line1" size="35" maxlength="150"></html:text></span>
				</td>
				<td>
					<span id="spn_ca_adr_line1" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="ca_adr_line1"></bean:write></span>
					<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="ca_adr_line1" size="35" maxlength="150"></html:text></span>				
				</td>				
			</tr>	
			<tr>
				<td>
					<span id="spn_ra_adr_line2" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="ra_adr_line2"></bean:write></span>
					<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="ra_adr_line2" size="35" maxlength="150"></html:text></span>				
				</td>
				<td>
					<span id="spn_ca_adr_line2" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="ca_adr_line2"></bean:write></span>
					<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="ca_adr_line2" size="35" maxlength="150"></html:text></span>				
				</td>				
			</tr>
			<tr>
				<td>
					<span id="spn_ra_adr_line3" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="ra_adr_line3"></bean:write></span>
					<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="ra_adr_line3" size="35" maxlength="150"></html:text></span>
				</td>
				<td>
					<span id="spn_ca_adr_line3" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="ca_adr_line3"></bean:write></span>
					<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="ca_adr_line3" size="35" maxlength="150"></html:text></span>				
				</td>				
			</tr>
			<tr>
				<td>
					<span class='<%= classViewMode %>'>
						<logic:equal name="_m_cstForm" property="mode" value="READONLY">
							<logic:notEmpty name="_m_cstForm" property="ra_country">
								<t:writeCodeValue name="_m_cstForm" property="ra_country" codeList="LIST_COUNTRY"></t:writeCodeValue>
							</logic:notEmpty>
						</logic:equal>
					</span>
					<!-- loading country id -->
					<span id="spn_ra_country_id" class="hidden"><bean:write name="_m_cstForm" property="ra_country_id"/></span>
			        <span class='<%= classNewMode%>'>
			        <html:select name="_m_cstForm" property="ra_country_id">
			          <html:option value="" ><bean:message key="screen.m_cst.label_blank"/></html:option>
			          <html:options collection="LIST_COUNTRY" property="id" labelProperty="name"/>
			        </html:select>					
			        </span>				
				</td>
				<td>
					<span class='<%= classViewMode %>'>
						<logic:equal name="_m_cstForm" property="mode" value="READONLY">
							<logic:notEmpty name="_m_cstForm" property="ca_country">
								<t:writeCodeValue name="_m_cstForm" property="ca_country" codeList="LIST_COUNTRY"></t:writeCodeValue>
							</logic:notEmpty>
						</logic:equal>
					</span>
					<!-- loading country id -->
					<span id="spn_ca_country_id" class="hidden"><bean:write name="_m_cstForm" property="ca_country_id"/></span>
			        <span class='<%= classNewMode%>'>
			        <html:select name="_m_cstForm" property="ca_country_id">
			          <html:option value="" ><bean:message key="screen.m_cst.label_blank"/></html:option>
			          <html:options collection="LIST_COUNTRY" property="id" labelProperty="name"/>
			        </html:select>					
			        </span>					
				</td>				
			</tr>
			<tr>
				<td class="titleLink">
					<span class="underlineTitle"><bean:message key="screen.m_cst.label_bl_address"/></span>
					<span class="mandatory"><bean:message key="screen.m_cst.label_mandatory"/></span>					
				</td>
				<td>
				</td>				
			</tr>
			<tr>
				<td>
					<span id="spn_ba_adr_line1" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="ba_adr_line1"></bean:write></span>
					<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="ba_adr_line1" size="35" maxlength="150"></html:text></span>				
				</td>
				<td>
				</td>				
			</tr>
			<tr>
				<td>
					<span id="spn_ba_adr_line2" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="ba_adr_line2"></bean:write></span>
					<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="ba_adr_line2" size="35" maxlength="150"></html:text></span>				
				</td>
				<td>
				</td>				
			</tr>
			<tr>
				<td>
					<span id="spn_ba_adr_line3" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="ba_adr_line3"></bean:write></span>
					<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="ba_adr_line3" size="35" maxlength="150"></html:text></span>				
				</td>
				<td>
				</td>				
			</tr>
			<tr>
				<td>
					<span class='<%= classViewMode %>'>
						<logic:equal name="_m_cstForm" property="mode" value="READONLY">
							<logic:notEmpty name="_m_cstForm" property="ba_country">
								<t:writeCodeValue name="_m_cstForm" property="ba_country" codeList="LIST_COUNTRY"></t:writeCodeValue>
							</logic:notEmpty>
						</logic:equal>
					</span>
					<!-- loading country id -->
					<span id="spn_ba_country_id" class="hidden"><bean:write name="_m_cstForm" property="ba_country_id"/></span>								
			        <span class='<%= classNewMode%>'>
			        <html:select name="_m_cstForm" property="ba_country_id">
			          <html:option value="" ><bean:message key="screen.m_cst.label_blank"/></html:option>
			          <html:options collection="LIST_COUNTRY" property="id" labelProperty="name"/>
			        </html:select>					
			        </span>				
				</td>
				<td>
				</td>				
			</tr>														
		</table>		
		<div style="background-color: #F8F8F8;">
		<br>
	    <!-- JSP Tab control -->
	    <input type="hidden" id="lbl_primary_contact" value='<bean:message key="screen.m_cst.label_billing_contact"/>'>
		<ul id="countrytabs" class="shadetabs">
			<li><a href="#" rel="idTab01"><bean:message key="screen.m_cst.label_primary_contact"/></a></li>
			<li><a href="#" rel="idTab02"><bean:message key="screen.m_cst.label_billing_contact"/></a></li>
			<li><a href="#" rel="idTab03"><bean:message key="screen.m_cst.label_technical_contact"/></a></li>
			<li><a href="#" rel="idTab04"><bean:message key="screen.m_cst.label_other_contact"/></a></li>			
		</ul>
		<div style="border:1px solid gray; width:100%; margin-bottom: 1em; padding: 10px">
			<div id="idTab01" class="tabcontent">
				<!-- primary contact -->
				<table class="information" cellpadding="0" cellspacing="1">
					<tr>
						<td width="15%">
							<bean:message key="screen.m_cst.label_contact_name"/>
							<span class="mandatory"><bean:message key="screen.m_cst.label_mandatory"/></span>
						</td>
						<td width="85%">
							<bean:message key="screen.m_cst.label_colon"/>
							<span id="spn_pc_contact_name" class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write name="_m_cstForm" property="pc_contact_name"></bean:write></span>
							<span class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text name="_m_cstForm" property="pc_contact_name" size="25" maxlength="50"></html:text></span>										
						</td>
					</tr>
					<tr>
						<td>
							<bean:message key="screen.m_cst.label_designation"/>
							<span class="mandatory"><bean:message key="screen.m_cst.label_mandatory"/></span>
						</td>
						<td>
							<bean:message key="screen.m_cst.label_colon"/>
							<span id="spn_pc_designation" class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write name="_m_cstForm" property="pc_designation"></bean:write></span>
							<span class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text name="_m_cstForm" property="pc_designation" size="25" maxlength="50"></html:text></span>															
						</td>
					</tr>
					<tr>
						<td>
							<bean:message key="screen.m_cst.label_email"/>
							<span class="mandatory"><bean:message key="screen.m_cst.label_mandatory"/></span>
						</td>
						<td>
							<bean:message key="screen.m_cst.label_colon"/>
							<span id="spn_pc_email" class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write name="_m_cstForm" property="pc_email"></bean:write></span>
							<span class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text name="_m_cstForm" property="pc_email" size="25" maxlength="50" ></html:text></span>															
						</td>				
					</tr>
					<tr>
						<td>
							<bean:message key="screen.m_cst.label_telephone_no"/>
							<span class="mandatory"><bean:message key="screen.m_cst.label_mandatory"/></span>
						</td>
						<td>
							<bean:message key="screen.m_cst.label_colon"/>
							<span id="spn_pc_tel_no" class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write name="_m_cstForm" property="pc_tel_no"></bean:write></span>
							<span class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text name="_m_cstForm" property="pc_tel_no" size="25" maxlength="15"></html:text></span>															
						</td>					
					</tr>
					<tr>
						<td>
							<bean:message key="screen.m_cst.label_did_no"/>
						</td>
						<td>
							<bean:message key="screen.m_cst.label_colon"/>
							<span id="spn_pc_did_no" class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write name="_m_cstForm" property="pc_did_no"></bean:write></span>
							<span class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text name="_m_cstForm" property="pc_did_no" size="25" maxlength="15"></html:text></span>															
						</td>	
					</tr>
					<tr>
						<td>
							<bean:message key="screen.m_cst.label_fax_no"/>
							<span class="mandatory"><bean:message key="screen.m_cst.label_mandatory"/></span>
						</td>
						<td>
							<bean:message key="screen.m_cst.label_colon"/>
							<span id="spn_pc_fax_no" class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write name="_m_cstForm" property="pc_fax_no"></bean:write></span>
							<span class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text name="_m_cstForm" property="pc_fax_no" size="25" maxlength="15"></html:text></span>															
						</td>
					</tr>
					<tr>
						<td>
							<bean:message key="screen.m_cst.label_mobile_no"/>
						</td>
						<td>
							<bean:message key="screen.m_cst.label_colon"/>
							<span id="spn_pc_mobile_no" class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write name="_m_cstForm" property="pc_mobile_no"></bean:write></span>
							<span class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text name="_m_cstForm" property="pc_mobile_no" size="25" maxlength="15"></html:text></span>															
						</td>
					</tr>				
				</table>	  
			</div>
			<div id="idTab02" class="tabcontent">
				<!-- billing contact -->
				<table class="information" cellpadding="0" cellspacing="1">
					<tr>
						<td width="15%">
							<bean:message key="screen.m_cst.label_contact_name"/>
						</td>
						<td width="85%">
							<bean:message key="screen.m_cst.label_colon"/>
							<span id="spn_bc_contact_name" class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write name="_m_cstForm" property="bc_contact_name"></bean:write></span>
							<span class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text name="_m_cstForm" property="bc_contact_name" size="25" maxlength="50"></html:text></span>										
						</td>
					</tr>
					<tr>
						<td>
							<bean:message key="screen.m_cst.label_designation"/>
						</td>
						<td>
							<bean:message key="screen.m_cst.label_colon"/>
							<span id="spn_bc_designation" class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write name="_m_cstForm" property="bc_designation"></bean:write></span>
							<span class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text name="_m_cstForm" property="bc_designation" size="25" maxlength="50"></html:text></span>															
						</td>
					</tr>
					<tr>
						<td>
							<bean:message key="screen.m_cst.label_email"/>
						</td>
						<td>
							<bean:message key="screen.m_cst.label_colon"/>
							<span id="spn_bc_email" class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write name="_m_cstForm" property="bc_email"></bean:write></span>
							<span class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text name="_m_cstForm" property="bc_email" size="25" maxlength="50"></html:text></span>															
						</td>				
					</tr>
					<tr>
						<td>
							<bean:message key="screen.m_cst.label_telephone_no"/>
						</td>
						<td>
							<bean:message key="screen.m_cst.label_colon"/>
							<span id="spn_bc_tel_no" class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write name="_m_cstForm" property="bc_tel_no"></bean:write></span>
							<span class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text name="_m_cstForm" property="bc_tel_no" size="25" maxlength="15"></html:text></span>															
						</td>					
					</tr>
					<tr>
						<td>
							<bean:message key="screen.m_cst.label_did_no"/>
						</td>
						<td>
							<bean:message key="screen.m_cst.label_colon"/>
							<span id="spn_bc_did_no" class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write name="_m_cstForm" property="bc_did_no"></bean:write></span>
							<span class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text name="_m_cstForm" property="bc_did_no" size="25" maxlength="15"></html:text></span>															
						</td>	
					</tr>
					<tr>
						<td>
							<bean:message key="screen.m_cst.label_fax_no"/>
						</td>
						<td>
							<bean:message key="screen.m_cst.label_colon"/>
							<span id="spn_bc_fax_no" class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write name="_m_cstForm" property="bc_fax_no"></bean:write></span>
							<span class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text name="_m_cstForm" property="bc_fax_no" size="25" maxlength="15"></html:text></span>															
						</td>
					</tr>
					<tr>
						<td>
							<bean:message key="screen.m_cst.label_mobile_no"/>
						</td>
						<td>
							<bean:message key="screen.m_cst.label_colon"/>
							<span id="spn_bc_mobile_no" class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write name="_m_cstForm" property="bc_mobile_no"></bean:write></span>
							<span class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text name="_m_cstForm" property="bc_mobile_no" size="25" maxlength="15"></html:text></span>															
						</td>
					</tr>				
				</table>	   				
			</div>
			<div id="idTab03" class="tabcontent">
				<!-- technical contact -->
				<table class="information" cellpadding="0" cellspacing="1">
					<tr>
						<td width="15%">
							<bean:message key="screen.m_cst.label_contact_name"/>
						</td>
						<td width="85%">
							<bean:message key="screen.m_cst.label_colon"/>
							<span id="spn_tc_contact_name" class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write name="_m_cstForm" property="tc_contact_name"></bean:write></span>
							<span class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text name="_m_cstForm" property="tc_contact_name" size="25" maxlength="50"></html:text></span>										
						</td>
					</tr>
					<tr>
						<td>
							<bean:message key="screen.m_cst.label_designation"/>
						</td>
						<td>
							<bean:message key="screen.m_cst.label_colon"/>
							<span id="spn_tc_designation" class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write name="_m_cstForm" property="tc_designation"></bean:write></span>
							<span class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text name="_m_cstForm" property="tc_designation" size="25" maxlength="50"></html:text></span>															
						</td>
					</tr>
					<tr>
						<td>
							<bean:message key="screen.m_cst.label_email"/>
						</td>
						<td>
							<bean:message key="screen.m_cst.label_colon"/>
							<span id="spn_tc_email" class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write name="_m_cstForm" property="tc_email"></bean:write></span>
							<span class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text name="_m_cstForm" property="tc_email" size="25" maxlength="50"></html:text></span>															
						</td>				
					</tr>
					<tr>
						<td>
							<bean:message key="screen.m_cst.label_telephone_no"/>
						</td>
						<td>
							<bean:message key="screen.m_cst.label_colon"/>
							<span id="spn_tc_tel_no" class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write name="_m_cstForm" property="tc_tel_no"></bean:write></span>
							<span class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text name="_m_cstForm" property="tc_tel_no" size="25" maxlength="15"></html:text></span>															
						</td>					
					</tr>
					<tr>
						<td>
							<bean:message key="screen.m_cst.label_did_no"/>
						</td>
						<td>
							<bean:message key="screen.m_cst.label_colon"/>
							<span id="spn_tc_did_no" class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write name="_m_cstForm" property="tc_did_no"></bean:write></span>
							<span class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text name="_m_cstForm" property="tc_did_no" size="25" maxlength="15"></html:text></span>															
						</td>	
					</tr>
					<tr>
						<td>
							<bean:message key="screen.m_cst.label_fax_no"/>
						</td>
						<td>
							<bean:message key="screen.m_cst.label_colon"/>
							<span id="spn_tc_fax_no" class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write name="_m_cstForm" property="tc_fax_no"></bean:write></span>
							<span class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text name="_m_cstForm" property="tc_fax_no" size="25" maxlength="15"></html:text></span>															
						</td>
					</tr>
					<tr>
						<td>
							<bean:message key="screen.m_cst.label_mobile_no"/>
						</td>
						<td>
							<bean:message key="screen.m_cst.label_colon"/>
							<span id="spn_tc_mobile_no" class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write name="_m_cstForm" property="tc_mobile_no"></bean:write></span>
							<span class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text name="_m_cstForm" property="tc_mobile_no" size="25" maxlength="15"></html:text></span>															
						</td>
					</tr>				
				</table>	   				
			</div>
			<div id="idTab04" class="tabcontent">
				<!-- other contact -->
				<table class="information" cellpadding="0" cellspacing="1">
					<tr>
						<td>
							<bean:message key="screen.m_cst.label_contact_name"/>
						</td>
						<td>
							<bean:message key="screen.m_cst.label_colon"/>
							<span id="spn_oc_contact_name" class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write name="_m_cstForm" property="oc_contact_name"></bean:write></span>
							<span class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text name="_m_cstForm" property="oc_contact_name" size="25" maxlength="50"></html:text></span>										
						</td>
					</tr>
					<tr>
						<td width="15%">
							<bean:message key="screen.m_cst.label_designation"/>
						</td>
						<td width="85%">
							<bean:message key="screen.m_cst.label_colon"/>
							<span id="spn_oc_designation" class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write name="_m_cstForm" property="oc_designation"></bean:write></span>
							<span class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text name="_m_cstForm" property="oc_designation" size="25" maxlength="50"></html:text></span>															
						</td>
					</tr>
					<tr>
						<td>
							<bean:message key="screen.m_cst.label_email"/>
						</td>
						<td>
							<bean:message key="screen.m_cst.label_colon"/>
							<span id="spn_oc_email" class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write name="_m_cstForm" property="oc_email"></bean:write></span>
							<span class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text name="_m_cstForm" property="oc_email" size="25" maxlength="50"></html:text></span>															
						</td>				
					</tr>
					<tr>
						<td>
							<bean:message key="screen.m_cst.label_telephone_no"/>
						</td>
						<td>
							<bean:message key="screen.m_cst.label_colon"/>
							<span id="spn_oc_tel_no" class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write name="_m_cstForm" property="oc_tel_no"></bean:write></span>
							<span class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text name="_m_cstForm" property="oc_tel_no" size="25" maxlength="15"></html:text></span>															
						</td>					
					</tr>
					<tr>
						<td>
							<bean:message key="screen.m_cst.label_did_no"/>
						</td>
						<td>
							<bean:message key="screen.m_cst.label_colon"/>
							<span id="spn_oc_did_no" class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write name="_m_cstForm" property="oc_did_no"></bean:write></span>
							<span class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text name="_m_cstForm" property="oc_did_no" size="25" maxlength="15"></html:text></span>															
						</td>	
					</tr>
					<tr>
						<td>
							<bean:message key="screen.m_cst.label_fax_no"/>
						</td>
						<td>
							<bean:message key="screen.m_cst.label_colon"/>
							<span id="spn_oc_fax_no" class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write name="_m_cstForm" property="oc_fax_no"></bean:write></span>
							<span class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text name="_m_cstForm" property="oc_fax_no" size="25" maxlength="15"></html:text></span>															
						</td>
					</tr>
					<tr>
						<td>
							<bean:message key="screen.m_cst.label_mobile_no"/>
						</td>
						<td>
							<bean:message key="screen.m_cst.label_colon"/>
							<span id="spn_oc_mobile_no" class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write name="_m_cstForm" property="oc_mobile_no"></bean:write></span>
							<span class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text name="_m_cstForm" property="oc_mobile_no" size="25" maxlength="15"></html:text></span>															
						</td>
					</tr>				
				</table>					
			</div>			
		</div>  
		</div>				
		<hr class="lineBottom" size="3">
		<table class="buttonGroup" cellpadding="0" cellspacing="0">
			<tr>
				<logic:equal name="_m_cstForm" property="mode" value="READONLY">
					<button onclick="submitForm('edit');"><bean:message key="screen.m_cst.button_edit"/></button>&nbsp;
					<button onclick="submitForm('delete','<bean:message key="info.ERR4SC002"/>');"><bean:message key="screen.m_cst.button_delete"/></button>&nbsp;					
				</logic:equal>
				<logic:equal name="_m_cstForm" property="mode" value="NEWMODE">
					<button onclick="submitForm('save');"><bean:message key="screen.m_cst.button_save"/></button>&nbsp;
				</logic:equal>
				<logic:equal name="_m_cstForm" property="mode" value="EDITMODE">
					<button onclick="submitForm('save');"><bean:message key="screen.m_cst.button_save"/></button>&nbsp;
					<button onclick="submitForm('delete','<bean:message key="info.ERR4SC002"/>');"><bean:message key="screen.m_cst.button_delete"/></button>&nbsp;				
				</logic:equal>
				<button onclick="submitForm('exit','<bean:message key="info.ERR4SC001"/>');"><bean:message key="screen.m_cst.button_exit"/></button>				
			</tr>
		</table>
		</ts:form>	
	    <div class="error">
			<html:messages id="message">
				<bean:write name="message"/><br/>
			</html:messages>
		</div>
		<div class="message">
			<ts:messages id="message" message="true">
				<bean:write name="message" />
			</ts:messages>
		</div>
	</body>
</html:html>

