<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.HashMap"%>
<%@page import="nttdm.bsys.b_ssm.s01.data.B_SSM_S01_FieldSet"%><html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
	
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/stylesheet/tabcontent.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/B_SSM_S03/css/b_ssm_s03_css.css"/>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/tabcontent.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/B_SSM/js/b_ssm_js.js"></script>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/B_SSM_S03/js/b_ssm_s03_js.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
	
	<bean:define id="pageForm" name="B_SSM_S03_Page_Form" type="jp.terasoluna.fw.web.struts.form.DynaValidatorActionFormEx"/>
	<script type="text/javascript">
		var reportErrorStatus = '<%=
									 pageForm.getString("reportErrorStatus") != null ? 
									 pageForm.getString("reportErrorStatus") :
									  ""
								  %>';
	 	var promptErrorMessage	= '<bean:message key="errors.ERR1SC033"/>';	
	 	var completionBtnMessage = '<bean:message key="errors.ERR1SC018" arg0="Completion Report" arg1="Completion Date"/>';
	 	var freeFormatBtnMessage = '<bean:message key="errors.ERR1SC018" arg0="Free Format" arg1="Completion Date"/>';
	 	var ERR1SC093 = '<bean:message key="errors.ERR1SC093" arg0="Mailing Address"/>';
	 	var ERR1SC095 = '<bean:message key="errors.ERR1SC095"/>';
	 	var contextPath =  '<%=request.getContextPath()%>';
	 	var addressListValues = {};
	</script>			
</head>

<bean:define id="pageForm" name="B_SSM_S03_Page_Form" type="jp.terasoluna.fw.web.struts.form.DynaValidatorActionFormEx"/>
<% 	    
	// Process mode
	int scriptProcessMode;
	try {
		scriptProcessMode = Integer.parseInt((String)pageForm.get("processMode"));			
	} catch (Exception ex) {
		scriptProcessMode = 1;
	}
 %>
<body onload="initB_SSM_S03_Page();">
	<div id="B_SSM_S03_Page_Content">
		<ts:form  action="/B_SSM_S03_Request_Process_Action.do" onsubmit="return validateSubmit();">		
			<t:defineCodeList id="LIST_MAILING_ADDRESS"/>
			<html:hidden property="accMgrPrim" name="B_SSM_S03_Page_Form" styleId="accMgrPrim"/>
			<html:hidden property="customerType" name="B_SSM_S03_Page_Form" styleId="customerType"/>
			<!-- Address List Info -->
			<logic:notEmpty property="addressList" name="B_SSM_S03_Page_Form">
				<logic:iterate id="address" property="addressList" name="B_SSM_S03_Page_Form">
					<script type="text/javascript">
		  				var address1To4 = {};
		  				address1To4['ADR_LINE1'] = "${address.ADR_LINE1}";
		  				address1To4['ADR_LINE2'] = "${address.ADR_LINE2}";
		  				address1To4['ADR_LINE3'] = "${address.ADR_LINE3}";
		  				address1To4['ADR_LINE4'] = "${address.ADR_LINE4}";
		  				var adrType = "${address.ADR_TYPE}";
		  				addressListValues[adrType] = address1To4;	
		  			</script>
				</logic:iterate>
			</logic:notEmpty>
			<!--************************************ Header ****************************************-->
			<!-- Header title -->
			<div class="B_SSM_S03_Page_Form_Header">
				<bean:message key="B_SSM_S03_Page.Header.Title"/>
			</div>
			
			<!-- Hidden info fields -->
			<div id="hiddenInfoFields">
				<!-- Field indicates process mode -->
			 	<html:text styleId="processModeField" 
				 		   styleClass="B_SSM_S03_Page_Form_TextBox"  
				 		   name="B_SSM_S03_Page_Form" 
				 		   property="processMode" 
				 		   style="display: none;">
			 		
			 	</html:text>	
			 	
			 	<!-- Field indicates subscriptionID -->
				 <html:text styleId="subscriptionID" 
				 			styleClass="B_SSM_S03_Page_Form_TextBox"  
				 			name="B_SSM_S03_Page_Form" 
				 			property="subscriptionID" 
				 			style="display: none;">
				 	<bean:write name="B_SSM_S03_Page_Form" property="subscriptionID"/>
				 </html:text>
				 
				 <!-- Field indicates customerID -->
				 <html:text styleId="customerID" 
				 			styleClass="B_SSM_S03_Page_Form_TextBox"  
				 			name="B_SSM_S03_Page_Form" 
				 			property="customerID" 
				 			style="display: none;">
				 	<bean:write name="B_SSM_S03_Page_Form" property="customerID"/>
				 </html:text>
				 
				 <!-- Field indicates customerName -->
				 <html:text styleId="customerNameID" 
				 			styleClass="B_SSM_S03_Page_Form_TextBox"  
				 			name="B_SSM_S03_Page_Form" 
				 			property="customerName" 
				 			style="display: none;">
				 	<bean:write name="B_SSM_S03_Page_Form" property="customerName"/>
				 </html:text>
				 
				 <!-- Field indicates customerPlanID -->
				 <html:text styleId="customerPlanID" 
				 			styleClass="B_SSM_S03_Page_Form_TextBox"  
				 			name="B_SSM_S03_Page_Form" 
				 			property="customerPlanID" 
				 			style="display: none;">
				 	<bean:write name="B_SSM_S03_Page_Form" property="customerPlanID"/>
				 </html:text>
			 			
			</div>
			
			<!-- Header Subscription Info -->
			<div class="B_SSM_S03_Page_Form_HeaderSubInfo">
			 	<TABLE class="B_SSM_S03_Page_Form_HeaderSubInfo_Table">
			 		<TR>
			 			<TD class="B_SSM_S03_Page_Form_HeaderSubInfo_Table_Col">
			 				<TABLE>
						 		<!-- Customer name -->
						 		<TR>
						 			<TD class="B_SSM_S03_Page_Form_HeaderSubInfo_Table_Col_Sub">
						 				<bean:message key="B_SSM_S03_Page.HeaderInfo.CustomerName.Text"/>
						 			</TD>
						 			<TD>
						 				: &nbsp;
						 				<bean:write name="B_SSM_S03_Page_Form" property="customerName"/>
						 			</TD>
						 		</TR>
						 		<!-- Customer id -->
						 		<TR>
						 			<TD class="B_SSM_S03_Page_Form_HeaderSubInfo_Table_Col_Sub">
						 				<bean:message key="B_SSM_S03_Page.HeaderInfo.CustomerID.Text"/>
						 			</TD>
						 			<TD>
						 				: &nbsp;
						 				<bean:write name="B_SSM_S03_Page_Form" property="customerID"/>
						 			</TD>
						 		</TR>
						 		<!-- Subscription id -->
						 		<TR>
						 			<TD class="B_SSM_S03_Page_Form_HeaderSubInfo_Table_Col_Sub">
						 				<bean:message key="B_SSM_S03_Page.HeaderInfo.SubscriptionID.Text"/>
						 			</TD>
						 			<TD>
						 				: &nbsp;
						 				<bean:write name="B_SSM_S03_Page_Form" property="subscriptionID"/>
						 			</TD>
						 		</TR>
						 	</TABLE>
			 			</TD>
			 		</TR>
			 	</TABLE>
			 </div>
			
		  	<!--******************************************** Content *****************************************-->
		  	<div>
		  		<fieldset>
		  			<legend class="B_SSM_S03_Page_Form_FieldSet_Legend">
		  				<bean:message key="B_SSM_S03_Page.Content.mailingAddress.Legend" />
		  			</legend>
		  			<div class="width: 100%;height: 125px;">
			  			<table cellpadding="0" cellspacing="0" border="0" style="width:100%" class="textAlignTop">
			  				<col width="40%">
			  				<col width="60%">
			  				<tr>
			  					<td>
			  						<html:select property="addressType" name="B_SSM_S03_Page_Form" onchange="changeAddressValue(this)">
						    			<logic:equal property="customerType" name="B_SSM_S03_Page_Form" value="CONS">
							    			<c:forEach items="${LIST_MAILING_ADDRESS}" var="item">
												<c:if test="${item.id eq 'RA' or item.id eq 'BA'}">
													<html:option value="${item.id}" >${item.name}</html:option>
												</c:if>
											</c:forEach>
										</logic:equal>
										<logic:equal property="customerType" name="B_SSM_S03_Page_Form" value="CORP">
						    				<html:optionsCollection name="LIST_MAILING_ADDRESS" value="id" label="name"/>
						    			</logic:equal>
						    		</html:select>
			  					</td>
			  					<td>
			  						<div id="divAddress1" style="width:265px;word-wrap: break-word;white-space : normal"></div>
			  					</td>
			  				</tr>
			  				<tr>
			  					<td></td>
			  					<td>
			  						<div id="divAddress2" style="width:265px;word-wrap: break-word;white-space : normal"></div>
			  					</td>
			  				</tr>
			  				<tr>
			  					<td></td>
			  					<td>
			  						<div id="divAddress3" style="width:265px;word-wrap: break-word;white-space : normal"></div>
			  					</td>
			  				</tr>
			  				<tr>
			  					<td></td>
			  					<td>
			  						<div id="divAddress4" style="width:265px;word-wrap: break-word;white-space : normal"></div>
			  					</td>
			  				</tr>
			  			</table>
			  		</div>
		  		</fieldset>
		  	</div>		  	
		  	<div>
				<fieldset class="B_SSM_S03_Page_Form_FieldSet">
					<legend class="B_SSM_S03_Page_Form_FieldSet_Legend">
						<bean:message key="B_SSM_S03_Page.Content.Services.Legend" />			  						
					</legend>
					<!-- Service names -->
					<div class="B_SSM_S03_Page_Form_FieldSet_Content">
						
						<TABLE id="servicesGroupTableID" class="B_SSM_S03_Page_Form_Table">
							<logic:notEmpty property="serviceNameList" name="B_SSM_S03_Page_Form">
								<logic:iterate id="service"
								               property="serviceNameList" 
								               name="B_SSM_S03_Page_Form"
								               indexId="index">
									<TR>
										<!-- Checkbox -->
										<TD class="B_SSM_S03_Page_Form_Table_Col_CheckBox">
											<input type="checkbox" 
												   name="selectedServiceCheck" 
												   id="checkServicesID<%=index.intValue() %>"
												   value="1"
												   onclick="setTextField('checkServicesStatusID<%=index.intValue() %>', this.checked?1:0)"/>
											<input type="text" 
												   style="display: none;" 
												   name="selectedServices" 
												   id="checkServicesStatusID<%=index.intValue() %>" 											  
												   onclick=''/>
										    <html:hidden property="completionDate" name="service" styleId="completionDate"/>
										</TD>
										<!-- Service name -->
										<TD style="width:220px;">
												<bean:write property="serviceName" 	name="service" />
												<input type="text" 
													   style="display: none;" 
													   name="serviceIDs" 
													   value="${service['serviceID']}" />
												<input type="text" 
													   style="display: none;" 
													   name="customerPlanLinkIDs" 
													   value="" />
											  
										</TD>
										<TD>
											<bean:write property="svcStart" name="service" />
										</TD>
										<TD>
											-
										</TD>
										<TD>
											<logic:empty property="svcEnd" name="service">
												<bean:message key="B_SSM_S03_Page.HeaderInfo.autoRenewal"/>
											</logic:empty>
											<logic:notEmpty property="svcEnd" name="service">
												<bean:write property="svcEnd" name="service" />
											</logic:notEmpty>
										</TD>
									</TR>
								</logic:iterate>
							</logic:notEmpty>
						</TABLE>
					</div>
				</fieldset>			
		  	</div> 			  	
		  	  	
		  	<!-- ******************************************* Buttons *****************************************-->		
		  	<div class="B_SSM_S03_Page_Form_Buttons">		  
			  	<!-- Notice Btn -->		  	
			  	<ts:submit property="button"
			  			   styleId="noticeBtnID"
			  	 		   styleClass="B_SSM_S03_Page_Form_Buttons_Text"
			  			   onclick="setTextField('processModeField', 6);return noticeBtnClick();">
			  		<bean:message key="B_SSM_S03_Page.Form.NoticeBtn.Text"/>
			  	</ts:submit>	 	
			  	<!-- Completion Report Btn -->	
			  	<ts:submit property="button" 
			  			   styleId="completionReportBtnID"
			  			   styleClass="B_SSM_S03_Page_Form_Buttons_Text"
			  		 	   onclick="setTextField('processModeField', 7);return completionReportBtnClick();">
			  		<bean:message key="B_SSM_S03_Page.Form.CompletionReportBtn.Text"/> 
			  	</ts:submit>	
			  	<!-- Free Format Btn -->	
			  	<ts:submit property="button" 
			  			   styleId="freeFormatBtnID"
			  			   styleClass="B_SSM_S03_Page_Form_Buttons_Text"
			  			   onclick="setTextField('processModeField', 8);return freeFormatBtnClick();">	
			  		<bean:message key="B_SSM_S03_Page.Form.FreeFormatBtn.Text"/> 
			  	</ts:submit>	
			  	<!-- Exit Btn -->	
			  	<html:button property="button" 
			  				 styleId="exitBtnID"
			  				 styleClass="B_SSM_S03_Page_Form_Buttons_Text"
			  				 onclick="self.close();">
			  		<bean:message key="B_SSM_S03_Page.Form.ExitBtn.Text"/> 
			  	</html:button>	
		  	</div> 	
		  	
		  	<!--********************************** Error info ************************************************-->
		  	<div class="B_SSM_S03_Page_Form_Errors" id="errorInfo">
		  		<ts:errors/>	
		  	</div>
  		</ts:form>
  	</div>
  	
  	<!-- Loading window -->
  	<div id="loadingWindow" class="LoadingWindow">
			
	</div>

</body>
</html>