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
   		<script type="text/javascript" src="js/m_csts03_s.js"></script>
		<title></title>
	</head>
	<body id="m_csts02" onload="initMain();initPage();setZipCode();custInitPeopleSoftPopupInfo();">
		<ts:form action="/M_CSTS03DSP">
		<t:defineCodeList id="LIST_COUNTRY"/>
		<t:defineCodeList id="LIST_SALUTATION"/>
		<t:defineCodeList id="COMPANY_CATEGORY"/>
		<t:defineCodeList id="COMPANY_SUB_CATEGORY"/>
		<t:defineCodeList id="LIST_End_User"></t:defineCodeList>
	    <t:defineCodeList id="LIST_COMPLANY_TYPE"></t:defineCodeList>
	    <t:defineCodeList id="GET_LABEL_DESC" />
		<html:hidden name="_m_cstForm" property="event1"/>
		<html:hidden name="_m_cstForm" property="mode"/>
		<html:hidden name="_m_cstForm" property="id_cust"/>
		<html:hidden name="_m_cstForm" property="temp_cust_acc_no"/>
		<html:hidden name="_m_cstForm" property="temp_cust_name"/>
		<html:hidden name="_m_cstForm" property="temp_co_regno"/>
		<html:hidden name="_m_cstForm" property="temp_country"/>
		<html:hidden name="_m_cstForm" property="peopleSoftPopupInfo"/>
        <html:hidden name="_m_cstForm" property="popupClickYesFlg"/>
		<bean:define name="_m_cstForm" property="classViewMode" id="classViewMode"></bean:define>
		<bean:define name="_m_cstForm" property="classNewMode" id="classNewMode"></bean:define>
		<html:hidden name="_m_cstForm" property="category_enableFlg"/>
		<html:hidden name="_m_cstForm" property="subCategory_enableFlg"/>
		<html:hidden name="_m_cstForm" property="bankInfo_enableFlg"/>		
        <html:hidden name="_m_cstForm" property="company_enableFlg"/>	
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
				<td>
					<bean:message key="screen.m_cst_s.label_customer_id2"/>
				</td>
				<td>
					<bean:message key="screen.m_cst.label_colon"/>					
					<span id="spn_cust_id" class="<%= classViewMode %>"><bean:write name="_m_cstForm" property="id_cust"></bean:write></span>
					<span class="<%= classNewMode%>"><bean:write name="_m_cstForm" property="id_cust" /></span>					
				</td>
			</tr>
			<tr>
				<!-- Salutation -->
				<td>
					<bean:message key="screen.m_cst_s.label_salutation"/>
                    <span class='<%= classNewMode%>' style="color: red"><bean:message key="screen.m_cst.label_mandatory"/></span>
				</td>
				<td>
					<bean:message key="screen.m_cst.label_colon"/>
					<span class='<%= classViewMode %>'>
						<logic:equal name="_m_cstForm" property="mode" value="READONLY">
							<logic:notEmpty name="_m_cstForm" property="salutation">
								<t:writeCodeValue name="_m_cstForm" property="salutation" codeList="LIST_SALUTATION"></t:writeCodeValue>
							</logic:notEmpty>
						</logic:equal>
					</span>
					<!-- loading SALUTATION -->
					<span id="spn_ra_country_id" class="hidden"><bean:write name="_m_cstForm" property="salutation"/></span>
			        <span class='<%= classNewMode%>'>
			        <html:select name="_m_cstForm" property="salutation" >
			          <html:option value="" ><bean:message key="screen.m_cst.label_blank"/></html:option>
			          <html:options collection="LIST_SALUTATION" property="id" labelProperty="name"/>
			        </html:select>					
			        </span>				
														
				</td>
				<!-- PeopleSoft Acc No.-->	
				<td><bean:message key="screen.m_cst_s.label_peoplesoft_acc_no"/></td>
				<td>
					<bean:message key="screen.m_cst.label_colon"/>
					<span id="spn_co_tel_no" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="cust_acc_no"></bean:write></span>
					<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="cust_acc_no" size="25" maxlength="15"></html:text></span>				
				</td>				
			</tr>
			<tr>
				<!-- Cusmtomer Name-->
				<td>
					<bean:message key="screen.m_cst_s.label_customer_name"/>
					<span class='<%= classNewMode%>' style="color: red"><bean:message key="screen.m_cst.label_mandatory"/></span>
				</td>
				<td>
					<bean:message key="screen.m_cst.label_colon"/>
					<span id="spn_co_regno" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="cust_name"></bean:write></span>
					<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="cust_name" size="25" maxlength="100"></html:text></span>				
				</td>
				<!-- GBOC Acc No. -->
				<td>
					<bean:message key="screen.m_cst_s.label_gboc_acc_no"/>									
				</td>
				<td>
					<bean:message key="screen.m_cst.label_colon"/>
					<span id="spn_co_fax_no" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="gboc_acc_no"></bean:write></span>
					<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="gboc_acc_no" size="25" maxlength="20"></html:text></span>				
				</td>
			</tr>	
			<tr>
				<!-- NRIC -->
				<td>
					<bean:message key="screen.m_cst_s.label_nric"/>
					<span class='<%= classNewMode%>' style="color: red"><bean:message key="screen.m_cst.label_mandatory"/></span>
				</td>
				<td>
					<bean:message key="screen.m_cst.label_colon"/>
					<span id="spn_co_website" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="cust_id_no"></bean:write></span>
					<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="cust_id_no" size="25" maxlength="15"></html:text></span>				
				</td>
				<!-- Customer ID -->
				<!-- #594: [B1] Customer Maintenance CT 20190604 -->
				<c:forEach items="${GET_LABEL_DESC}" var="item">
					<c:choose>
						<c:when test="${not empty item.id}">
							<td>${item.id}</td>
						</c:when>
						<c:otherwise>
							<!-- Other Reference No. -->
							<td><bean:message key="screen.m_cst_s.label_ref_customer_id" /></td>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<!-- #594: [B1] Customer Maintenance CT 20190604 -->
				<td>
					<bean:message key="screen.m_cst.label_colon"/>
					<span id="spn_co_fax_no" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="others_ref_no"></bean:write></span>
					<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="others_ref_no" size="25" maxlength="20"></html:text></span>				
				</td>	
			</tr>
			<tr>
				<!-- Birth Date-->
				<td><bean:message key="screen.m_cst_s.label_birthday"/></td>
				<td>					
					<bean:message key="screen.m_cst.label_colon"/>				
					<span id="spn_co_email" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="cust_birth_date"></bean:write></span>
					<span class='<%= classNewMode%>'><html:text styleId="birthDate" name="_m_cstForm" property="tmpBirthDate" size="20" maxlength="50" disabled="true" value="${_m_cstForm.cust_birth_date}"></html:text>
					</span>
					<logic:notEqual name="_m_cstForm" property="classNewMode" value="hidden">						
						<t:inputCalendar for="tmpBirthDate" format="dd/MM/yyyy"/>
					</logic:notEqual>
					<html:hidden name="_m_cstForm" property="cust_birth_date" styleId="birthDateHidden"/>
									
				</td>
				<!-- Salesforce.com Acc ID -->
				<td>
					<bean:message key="screen.m_cst_s.label_salesforce_com_id"/>									
				</td>
				<td>
					<bean:message key="screen.m_cst.label_colon"/>
					<span id="spn_co_fax_no" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="sales_force_acc_id"></bean:write></span>
					<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="sales_force_acc_id" size="25" maxlength="20"></html:text></span>				
				</td>
			</tr>
			<tr>
				<!-- Email Address-->
				<td>
					<bean:message key="screen.m_cst_s.label_email_address"/>									
				</td>
				<td>
					<bean:message key="screen.m_cst.label_colon"/>
					<span id="spn_co_fax_no" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="co_email"></bean:write></span>
					<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="co_email" size="25" maxlength="50"></html:text></span>				
				</td>
				<!-- End User -->
				<td>
					<bean:message key="screen.m_cst_s.label_end_user"/>
				</td>
				<td>
					<bean:message key="screen.m_cst.label_colon"/>
					<span id="spn_end_user" class='<%= classViewMode %>'>
					<logic:equal value="READONLY" name="_m_cstForm" property="mode" >
					<t:writeCodeValue name="_m_cstForm" property="end_user" codeList="LIST_End_User"></t:writeCodeValue>
					</logic:equal>
					</span>					
					<span class='<%= classNewMode%>'>
						<html:select name="_m_cstForm" property="end_user">
				        	<html:options collection="LIST_End_User" property="id" labelProperty="name"/>
			        	</html:select>	
					</span>		
				</td>
			</tr>
			<tr>
				<!-- Home Phone Number-->
				<td>
					<bean:message key="screen.m_cst_s.label_home_phone_number"/>									
				</td>
				<td>
					<bean:message key="screen.m_cst.label_colon"/>
					<span id="spn_co_fax_no" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="home_tel_no"></bean:write></span>
					<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="home_tel_no" size="25" maxlength="20"></html:text></span>				
				</td>
				<!-- Company Category -->
				<td>
					<bean:message key="screen.m_cst_s.label_company_category"/>
				</td>
				<td>
					<bean:message key="screen.m_cst.label_colon"/>
					<span id="spn_company_category" class='<%= classViewMode %>'>
					<logic:equal value="READONLY" name="_m_cstForm" property="mode" >
						<logic:equal value="1" name="_m_cstForm" property="category_enableFlg" >
							<t:writeCodeValue name="_m_cstForm" property="company_category" codeList="COMPANY_CATEGORY"></t:writeCodeValue>
						</logic:equal>
						<logic:notEqual value="1" name="_m_cstForm" property="category_enableFlg" >
							-
						</logic:notEqual>
					</logic:equal>
					</span>					
					<span class='<%= classNewMode%>'>
					<logic:equal value="1" name="_m_cstForm" property="category_enableFlg" >
						<logic:equal value="J" name="_m_cstForm" property="company_category" >
							<html:select name="_m_cstForm" property="company_category">
					        	<html:options collection="COMPANY_CATEGORY" property="id" labelProperty="name"/>
				        	</html:select>	
						</logic:equal>
						<logic:notEqual value="J" name="_m_cstForm" property="company_category" >
							<html:select name="_m_cstForm" property="company_category" value="N">
					        	<html:options collection="COMPANY_CATEGORY" property="id" labelProperty="name"/>
				        	</html:select>	
						</logic:notEqual>
					</logic:equal>
					<logic:notEqual value="1" name="_m_cstForm" property="category_enableFlg" >
						<logic:equal value="J" name="_m_cstForm" property="company_category" >
							<html:select name="_m_cstForm" property="company_category" disabled="true" styleId="company_categoryList">
					        	<html:options collection="COMPANY_CATEGORY" property="id" labelProperty="name"/>
				        	</html:select>	
						</logic:equal>
						<logic:notEqual value="J" name="_m_cstForm" property="company_category" >
							<html:select name="_m_cstForm" property="company_category" value="N" disabled="true" styleId="company_categoryList">
					        	<html:options collection="COMPANY_CATEGORY" property="id" labelProperty="name"/>
				        	</html:select>	
						</logic:notEqual>
					</logic:notEqual>										
					</span>										
				</td>
			</tr>
			<tr>
				<!-- Mobile Number -->
				<td>
					<bean:message key="screen.m_cst_s.label_mobile_number"/>					
				</td>
				<td>
					<bean:message key="screen.m_cst.label_colon"/>
					<span id="spn_co_fax_no" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="mobile_no"></bean:write></span>
					<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="mobile_no" size="25" maxlength="30"></html:text></span>
				</td>
				<!-- Company sub Category -->
				<td>
					<bean:message key="screen.m_cst_s.label_company_sub_category"/>
				</td>
				<td>
					<bean:message key="screen.m_cst.label_colon"/>	
					<span id="spn_company_sub_category" class='<%= classViewMode %>'>
					<logic:equal value="READONLY" name="_m_cstForm" property="mode" >
						<logic:equal value="1" name="_m_cstForm" property="subCategory_enableFlg" >
							<t:writeCodeValue name="_m_cstForm" property="company_sub_category" codeList="COMPANY_SUB_CATEGORY"></t:writeCodeValue>
						</logic:equal>
						<logic:notEqual value="1" name="_m_cstForm" property="subCategory_enableFlg" >
							-
						</logic:notEqual>
					</logic:equal>
					</span>	
					<span class='<%= classNewMode%>'>
						<logic:equal value="1" name="_m_cstForm" property="subCategory_enableFlg" >
							<html:select name="_m_cstForm" property="company_sub_category">
					          <html:options collection="COMPANY_SUB_CATEGORY" property="id" labelProperty="name"/>
				        	</html:select>
			        	</logic:equal>
			        	<logic:notEqual value="1" name="_m_cstForm" property="subCategory_enableFlg" >
							<html:select name="_m_cstForm" property="company_sub_category" disabled="true" styleId="company_sub_categoryList">
					          <html:options collection="COMPANY_SUB_CATEGORY" property="id" labelProperty="name"/>
				        	</html:select>
			        	</logic:notEqual>					
					</span>
				</td>							
			</tr>
			<tr>
				<!-- Home Fax Number -->
				<td>
					<bean:message key="screen.m_cst_s.label_home_fax_number"/>					
				</td>
				<td>
					<bean:message key="screen.m_cst.label_colon"/>
					<span id="spn_cust_acc_no" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="home_fax_no"></bean:write></span>
					<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="home_fax_no" size="25" maxlength="20"></html:text></span>				
				</td>
				<!-- Company Bank Info -->
				<td>
					<bean:message key="screen.m_cst_s.label_company_bank_info"/>
				</td>
				<td>
					<bean:message key="screen.m_cst.label_colon"/>	
					<span id="spn_company_bank_info" class='<%= classViewMode %>'>
					<logic:equal value="READONLY" name="_m_cstForm" property="mode">
						<logic:equal value="1" name="_m_cstForm" property="bankInfo_enableFlg" >
							<logic:iterate id="companyBankName" name="_m_cstForm" property="companyBankInfoList" indexId="index">
						 		<logic:equal value="${_m_cstForm.company_bank_info}" name="companyBankName" property="value">
						 			<bean:write name="companyBankName" property="label"/>
						 		</logic:equal>
							</logic:iterate>
						</logic:equal>
						<logic:notEqual value="1" name="_m_cstForm" property="bankInfo_enableFlg" >
							-
						</logic:notEqual>
					</logic:equal>
					</span>
					<span class='<%= classNewMode%>'>
						<logic:equal value="1" name="_m_cstForm" property="bankInfo_enableFlg" >
							<html:select name="_m_cstForm" property="company_bank_info" styleId="companyBankInfoList">
					        	<html:optionsCollection property="companyBankInfoList" name="_m_cstForm" label="label" value="value"/>
				        	</html:select>
			        	</logic:equal>
			        	<logic:notEqual value="1" name="_m_cstForm" property="bankInfo_enableFlg" >
							<html:select name="_m_cstForm" property="company_bank_info" styleId="companyBankInfoList" disabled="true">
					        	<html:optionsCollection property="companyBankInfoList" name="_m_cstForm" label="label" value="value"/>
				        	</html:select>
			        	</logic:notEqual>
					</span>
					<input type="hidden" id="companyBankInfo" value="${_m_cstForm.company_bank_info}"/>
				</td>
			</tr>
			<tr>
				<!-- Office Phone Number -->
				<td>
					<bean:message key="screen.m_cst_s.label_office_phone_number"/>
				</td>
				<td>
					<bean:message key="screen.m_cst.label_colon"/>
					<span id="spn_co_regno" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="co_tel_no"></bean:write></span>
					<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="co_tel_no" size="25" maxlength="20"></html:text></span>				
				</td>
				<!--Add by MiffyAn start  -->
				<!-- company_type -->
				<td>
					<bean:message key="screen.m_cst_s.label_company_type_info"/>
				</td>
				<td>
					<bean:message key="screen.m_cst.label_colon"/>
					<span id="spn_company_type" class='<%= classViewMode %>'>
					<logic:equal value="READONLY" name="_m_cstForm" property="mode" >
						<logic:equal value="1" name="_m_cstForm" property="company_enableFlg" >
							<t:writeCodeValue name="_m_cstForm" property="company_type" codeList="LIST_COMPLANY_TYPE"></t:writeCodeValue>
						</logic:equal>
						<logic:notEqual value="1" name="_m_cstForm" property="category_enableFlg" >
							-
						</logic:notEqual>
					</logic:equal>
					</span>				
					<span class='<%= classNewMode%>'>
					<logic:equal value="1" name="_m_cstForm" property="company_enableFlg" >
						<html:select name="_m_cstForm" property="company_type" >
					        	<html:options collection="LIST_COMPLANY_TYPE" property="id" labelProperty="name"/>
				        </html:select>	
					</logic:equal>
					<logic:notEqual value="1" name="_m_cstForm" property="company_enableFlg" >
						<html:select name="_m_cstForm" property="company_type"  disabled="true" styleId="company_typeList">
					        <html:options collection="LIST_COMPLANY_TYPE" property="id" labelProperty="name"/>
				        </html:select>	
					</logic:notEqual>		
					</span>		
				</td>
				<!--Add by MiffyAn end  -->
			</tr>
			<tr>
				<!-- Office Fax Number -->
				<td><bean:message key="screen.m_cst_s.label_office_fax_number"/></td>
				<td>
					<bean:message key="screen.m_cst.label_colon"/>
					<span id="spn_co_website" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="co_fax_no"></bean:write></span>
					<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="co_fax_no" size="25" maxlength="20"></html:text></span>				
				</td>
			</tr>					
			<tr>
				<!-- checkbox -->
				<td>
					<!-- loading print statement -->
					<span id="spn_inter_comp" class="hidden"><bean:write name="_m_cstForm" property="print_statement"/></span>
					<logic:equal name="_m_cstForm" property="mode" value="READONLY">
						<html:checkbox styleId="chk_inter_comp" disabled="true" name="_m_cstForm" property="print_statement"><bean:message key="screen.m_cst_s.label_print_statement"/></html:checkbox>
					</logic:equal>
					<logic:notEqual name="_m_cstForm" property="mode" value="READONLY">
						<html:checkbox styleId="chk_inter_comp" name="_m_cstForm" property="print_statement"><bean:message key="screen.m_cst_s.label_print_statement"/></html:checkbox>
					</logic:notEqual>					
				</td>
				<td>
					<logic:equal name="_m_cstForm" property="mode" value="READONLY">
						<logic:equal value="2" name="_m_cstForm" property="accessType">
							<logic:equal value="2" name="_m_cstForm" property="bi_sub_module">
								<a class="hyperLink" onclick="submitForm('bankInfo','<bean:message key="info.ERR4SC001"/>', '<%=request.getContextPath()%>');"><bean:message key="screen.m_cst_s.label_bank_infomation"/></a>
							</logic:equal>
						</logic:equal>
					</logic:equal>
					<logic:equal name="_m_cstForm" property="mode" value="EDITMODE">
						<logic:equal value="2" name="_m_cstForm" property="accessType">
							<logic:equal value="2" name="_m_cstForm" property="bi_sub_module">
								<a class="hyperLink" onclick="submitForm('bankInfo','<bean:message key="info.ERR4SC001"/>', '<%=request.getContextPath()%>');"><bean:message key="screen.m_cst_s.label_bank_infomation"/></a> 
							</logic:equal>
						</logic:equal>
					</logic:equal>
					<logic:equal name="_m_cstForm" property="mode" value="NEWMODE">
						<logic:equal value="2" name="_m_cstForm" property="accessType">
							<logic:equal value="2" name="_m_cstForm" property="bi_sub_module">
								<a class="hyperLink" onclick="submitForm('bankInfo','<bean:message key="info.ERR4SC001"/>', '<%=request.getContextPath()%>');"><bean:message key="screen.m_cst_s.label_bank_infomation"/></a> 
							</logic:equal>
						</logic:equal>
					</logic:equal>
					<logic:empty name="_m_cstForm" property="mode" >
						<bean:message key="screen.m_cst_s.label_bank_infomation"/>
					</logic:empty>
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
					<span class='<%= classNewMode%>' style="color: red"><bean:message key="screen.m_cst.label_mandatory"/></span>
					<logic:notEqual name="_m_cstForm" property="mode" value="READONLY">
						<html:checkbox styleId="chk_inter_comp" name="_m_cstForm" property="copy_to_bill_addr" onclick="copy2BillAddr();"><bean:message key="screen.m_cst_s.label_copy_2_billing_address"/></html:checkbox>
					</logic:notEqual>			
				</td>
			</tr>
			<tr>
				<td>
					<span id="spn_ra_adr_line1" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="ra_adr_line1"></bean:write></span>
					<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="ra_adr_line1" size="50" maxlength="150" onkeyup="copy2BillAddr();"></html:text></span>
				</td>
			</tr>	
			<tr>
				<td>
					<span id="spn_ra_adr_line2" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="ra_adr_line2"></bean:write></span>
					<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="ra_adr_line2" size="50" maxlength="150" onkeyup="copy2BillAddr();"></html:text></span>				
				</td>
			</tr>
			<tr>
				<td>
					<span id="spn_ra_adr_line3" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="ra_adr_line3"></bean:write></span>
					<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="ra_adr_line3" size="50" maxlength="150" onkeyup="copy2BillAddr();"></html:text></span>
				</td>
			</tr>
			<tr>
				<td>
					<logic:equal name="_m_cstForm" property="mode" value="READONLY">
						<bean:write name="_m_cstForm" property="ra_zip_code"></bean:write>
					</logic:equal>
					<logic:equal name="_m_cstForm" property="mode" value="EDITMODE">
						<html:text property="ra_zip_code" name="_m_cstForm" size="12" maxlength="15"  onkeyup="copy2BillAddr();"></html:text>
					</logic:equal>
					<logic:equal name="_m_cstForm" property="mode" value="NEWMODE">
						<html:text property="ra_zip_code" name="_m_cstForm" size="12" maxlength="15" onfocus="if (this.value == 'Zip Code') this.value='';" onblur="if (this.value == '') this.value='Zip Code';"  onkeyup="copy2BillAddr();"></html:text>
					</logic:equal>
					<logic:empty name="_m_cstForm" property="mode" >
						<html:text property="ra_zip_code" name="_m_cstForm" size="12" maxlength="15" onfocus="if (this.value == 'Zip Code') this.value='';" onblur="if (this.value == '') this.value='Zip Code';" onkeyup="copy2BillAddr();"></html:text>
					</logic:empty>
					<span class='<%= classViewMode %>'>
						<logic:equal name="_m_cstForm" property="mode" value="READONLY">
							<logic:notEmpty name="_m_cstForm" property="ra_country_id">
								<t:writeCodeValue name="_m_cstForm" property="ra_country_id" codeList="LIST_COUNTRY"></t:writeCodeValue>
							</logic:notEmpty>
						</logic:equal>
					</span>
					<!-- loading country id -->
					<span id="spn_ra_country_id" class="hidden"><bean:write name="_m_cstForm" property="ra_country_id"/></span>
			        <span class='<%= classNewMode%>'>
			        <html:select name="_m_cstForm" property="ra_country_id" onchange="copy2BillAddr();">
			          <html:option value="" ><bean:message key="screen.m_cst.label_blank"/></html:option>
			          <html:options collection="LIST_COUNTRY" property="id" labelProperty="name"/>
			        </html:select>					
			        </span>				
				</td>
			</tr>
			<tr>
				<td class="titleLink" width="50%">
					<span class="underlineTitle"><bean:message key="screen.m_cst.label_bl_address"/></span>
					<span class='<%= classNewMode%>' style="color: red"><bean:message key="screen.m_cst.label_mandatory"/></span>					
				</td>
			</tr>
			<tr>
				<td>
					<span id="spn_ba_adr_line1" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="ba_adr_line1"></bean:write></span>
					<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="ba_adr_line1" size="50" maxlength="150"></html:text></span>				
				</td>
			</tr>
			<tr>
				<td>
					<span id="spn_ba_adr_line2" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="ba_adr_line2"></bean:write></span>
					<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="ba_adr_line2" size="50" maxlength="150"></html:text></span>				
				</td>
			</tr>
			<tr>
				<td>
					<span id="spn_ba_adr_line3" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="ba_adr_line3"></bean:write></span>
					<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="ba_adr_line3" size="50" maxlength="150"></html:text></span>				
				</td>
			</tr>
			<tr>
				<td>
					<logic:equal name="_m_cstForm" property="mode" value="READONLY">
						<bean:write name="_m_cstForm" property="ba_zip_code"></bean:write>
					</logic:equal>
					<logic:equal name="_m_cstForm" property="mode" value="EDITMODE">
						<html:text property="ba_zip_code" name="_m_cstForm" size="12" maxlength="15"  onkeyup="copy2BillAddr();"></html:text>
					</logic:equal>
					<logic:equal name="_m_cstForm" property="mode" value="NEWMODE">
						<html:text property="ba_zip_code" name="_m_cstForm" size="12" maxlength="15" onfocus="if (this.value == 'Zip Code') this.value='';" onblur="if (this.value == '') this.value='Zip Code';"  onkeyup="copy2BillAddr();"></html:text>
					</logic:equal>
					<logic:empty name="_m_cstForm" property="mode" >
						<html:text property="ba_zip_code" name="_m_cstForm" size="12" maxlength="15" onfocus="if (this.value == 'Zip Code') this.value='';" onblur="if (this.value == '') this.value='Zip Code';" onkeyup="copy2BillAddr();"></html:text>
					</logic:empty>
					<span class='<%= classViewMode %>'>
						<logic:equal name="_m_cstForm" property="mode" value="READONLY">
							<logic:notEmpty name="_m_cstForm" property="ba_country_id">
								<t:writeCodeValue name="_m_cstForm" property="ba_country_id" codeList="LIST_COUNTRY"></t:writeCodeValue>
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
			</tr>
		</table>		
		<hr class="lineBottom" size="3">
		<table class="buttonGroup" cellpadding="0" cellspacing="0">
			<tr>
				<logic:equal name="_m_cstForm" property="mode" value="READONLY">
					<logic:notEmpty name="_m_cstForm" property="accessType">
						<logic:equal value="2" name="_m_cstForm" property="accessType">
							<button onclick="submitForm('edit');"><bean:message key="screen.m_cst.button_edit"/></button>&nbsp;
							<button onclick="submitForm('delete','<bean:message key="info.ERR4SC002"/>');"><bean:message key="screen.m_cst.button_delete"/></button>&nbsp;	 				
						</logic:equal>
						<!--  
						<logic:notEqual value="2" name="_m_cstForm" property="accessType">
							<button onclick="submitForm('edit');" disabled="disabled"><bean:message key="screen.m_cst.button_edit"/></button>&nbsp;
							<button onclick="submitForm('delete','<bean:message key="info.ERR4SC002"/>');" disabled="disabled"><bean:message key="screen.m_cst.button_delete"/></button>&nbsp; 						
						</logic:notEqual>
						-->
					</logic:notEmpty>
					<logic:empty name="_m_cstForm" property="accessType">
						<button onclick="submitForm('edit');"><bean:message key="screen.m_cst.button_edit"/></button>&nbsp;
						<button onclick="submitForm('delete','<bean:message key="info.ERR4SC002"/>');"><bean:message key="screen.m_cst.button_delete"/></button>&nbsp;
					</logic:empty>
				</logic:equal>
				<logic:equal name="_m_cstForm" property="mode" value="NEWMODE">
					<button onclick="submitForm('save','','<%=request.getContextPath()%>')"/><bean:message key="screen.m_cst.button_save"/></button>					
					<!-- <button onclick="submitForm('delete','<bean:message key="info.ERR4SC002"/>');"><bean:message key="screen.m_cst.button_delete"/></button>&nbsp; -->				
				</logic:equal>
				<logic:empty name="_m_cstForm" property="mode" >
					<button onclick="submitForm('save','','<%=request.getContextPath()%>')"/><bean:message key="screen.m_cst.button_save"/></button>					
					<!-- <button onclick="submitForm('delete','<bean:message key="info.ERR4SC002"/>');"><bean:message key="screen.m_cst.button_delete"/></button>&nbsp;-->
				</logic:empty>				
				<logic:equal name="_m_cstForm" property="mode" value="EDITMODE">
					<button onclick="submitForm('save','','<%=request.getContextPath()%>')"/><bean:message key="screen.m_cst.button_save"/></button>					
			<!--  		<button onclick="submitForm('delete','<bean:message key="info.ERR4SC002"/>');"><bean:message key="screen.m_cst.button_delete"/></button>&nbsp; -->				
				</logic:equal>
				<logic:notEqual name="_m_cstForm" property="mode" value="EDITMODE">
                    <button onclick="submitForm('exit','<bean:message key="info.ERR4SC001"/>', '<%=request.getContextPath()%>');"><bean:message key="screen.m_cst.button_exit"/></button>               
                </logic:notEqual>
                <logic:equal name="_m_cstForm" property="mode" value="EDITMODE">
                    <button onclick="doEditExit('<bean:message key="info.ERR4SC001"/>', '<%=request.getContextPath()%>');"><bean:message key="screen.m_cst.button_exit"/></button>
                </logic:equal>
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

