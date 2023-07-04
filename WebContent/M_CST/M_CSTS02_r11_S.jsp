<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-html" prefix="html"%>
<%@ taglib uri="/struts-bean" prefix="bean"%>
<%@ taglib uri="/struts-logic" prefix="logic"%>
<%@ taglib uri="/terasoluna-struts" prefix="ts"%>
<%@ taglib uri="/terasoluna" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<link type="text/css" rel="stylesheet" href="css/m_csts02.css" />
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/stylesheet/common.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/stylesheet/tabcontent.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/javascript/tabcontent.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/javascript/common.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
<script type="text/javascript" src="js/m_csts02_r11_s.js"></script>
<script type="text/javascript" src="js/actb.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
	var customarray = "";
</script>
<title></title>
</head>
<body id="m_csts02"
	onload="initMain();initPage();setZipCode();custInitPeopleSoftPopupInfo();">
	<ts:form action="/M_CSTS02DSP">
		<t:defineCodeList id="LIST_COUNTRY" />
		<t:defineCodeList id="AFFILIATE_CODE" />
		<t:defineCodeList id="COMPANY_CATEGORY" />
		<t:defineCodeList id="COMPANY_SUB_CATEGORY" />
		<t:defineCodeList id="LIST_End_User" />
		<t:defineCodeList id="LIST_COMPLANY_TYPE"></t:defineCodeList>
		<t:defineCodeList id="GET_LABEL_DESC" />
		<input type="hidden" id="hiddenContextpath"
			value="<%=request.getContextPath()%>" />
		<html:hidden styleId="hiddenEvent" name="_m_cstForm" property="event" />
		<html:hidden name="_m_cstForm" property="mode" />
		<html:hidden name="_m_cstForm" property="id_cust" />
		<html:hidden name="_m_cstForm" property="temp_cust_acc_no" />
		<html:hidden name="_m_cstForm" property="temp_cust_name" />
		<html:hidden name="_m_cstForm" property="temp_co_regno" />
		<html:hidden name="_m_cstForm" property="temp_country" />
		<html:hidden name="_m_cstForm" property="peopleSoftPopupInfo" />
		<html:hidden name="_m_cstForm" property="popupClickYesFlg" />
		<html:hidden styleId="hiddenPrevious" name="_m_cstForm"
			property="previous" />
		<html:hidden styleId="hiddenFromPopup" name="_m_cstForm"
			property="fromPopup" />
		<html:hidden styleId="affiliate_ntt_hidden" disabled="true"
			name="_m_cstForm" property="affiliate_ntt" />
		<bean:define name="_m_cstForm" property="classViewMode"
			id="classViewMode"></bean:define>
		<bean:define name="_m_cstForm" property="classNewMode"
			id="classNewMode"></bean:define>
		<html:hidden name="_m_cstForm" property="category_enableFlg" />
		<html:hidden name="_m_cstForm" property="subCategory_enableFlg" />
		<html:hidden name="_m_cstForm" property="bankInfo_enableFlg" />
		<html:hidden name="_m_cstForm" property="company_enableFlg" />
		<table class="subHeader" cellpadding="0" cellspacing="0">
			<tr>
				<td><bean:message key="screen.m_cst.screen_name" /></td>
			</tr>
		</table>

		<table class="information" cellpadding="0" cellspacing="1">
			<tr>
				<td colspan="4" class="header"><bean:message
						key="screen.m_cst.label_general_info" /></td>
			</tr>
			<tr>
				<!-- customer ID -->
				<td><bean:message key="screen.m_cst_s.label_customer_id2" />
				</td>
				<td><bean:message key="screen.m_cst.label_colon" /> <span
					id="spn_cust_id" class="<%=classViewMode%>"><bean:write
							name="_m_cstForm" property="id_cust"></bean:write></span> <span
					class="<%=classNewMode%>"><bean:write name="_m_cstForm"
							property="id_cust" /></span></td>
			</tr>
			<tr>
				<!-- customer name -->
				<td><bean:message key="screen.m_cst.label_customer_name" /> <span
					class="<%=classNewMode%>" style="color: red"><bean:message
							key="screen.m_cst.label_mandatory" /></span></td>
				<td><bean:message key="screen.m_cst.label_colon" /> <span
					id="spn_cust_name" class="<%=classViewMode%>"><bean:write
							name="_m_cstForm" property="cust_name"></bean:write></span> <span
					class="<%=classNewMode%>"><html:text name="_m_cstForm"
							property="cust_name" size="25" maxlength="100"></html:text></span></td>
				<!-- customer acc no -->
				<td><bean:message key="screen.m_cst_s.label_peoplesoft_acc_no" />
				</td>
				<td><bean:message key="screen.m_cst.label_colon" /> <span
					id="spn_cust_acc_no" class='<%=classViewMode%>'><bean:write
							name="_m_cstForm" property="cust_acc_no"></bean:write></span> <logic:equal
						name="_m_cstForm" property="mode" value="NEWMODE">
						<html:text name="_m_cstForm" property="cust_acc_no" size="25"
							maxlength="20"></html:text>
					</logic:equal> <logic:empty name="_m_cstForm" property="mode">
						<html:text name="_m_cstForm" property="cust_acc_no" size="25"
							maxlength="20"></html:text>
					</logic:empty> <logic:equal name="_m_cstForm" property="mode" value="EDITMODE">
						<logic:equal value="2" name="_m_cstForm" property="accessType">
							<logic:equal value="1" name="_m_cstForm" property="ac_sub_module">
								<html:text name="_m_cstForm" property="cust_acc_no" size="25"
									maxlength="20"></html:text>
							</logic:equal>
							<logic:notEqual value="1" name="_m_cstForm"
								property="ac_sub_module">
								<html:text disabled="true" name="_m_cstForm"
									property="cust_acc_no" size="25" maxlength="20"></html:text>
							</logic:notEqual>
						</logic:equal>
						<logic:notEqual value="2" name="_m_cstForm" property="accessType">
							<html:text disabled="true" name="_m_cstForm"
								property="cust_acc_no" size="25" maxlength="20"></html:text>
						</logic:notEqual>
					</logic:equal></td>
			</tr>
			<tr>
				<!-- company reg no -->
				<td><bean:message key="screen.m_cst.label_company_reg_no" /></td>
				<td><bean:message key="screen.m_cst.label_colon" /> <span
					id="spn_co_regno" class='<%=classViewMode%>'><bean:write
							name="_m_cstForm" property="co_regno"></bean:write></span> <span
					class='<%=classNewMode%>'><html:text name="_m_cstForm"
							property="co_regno" size="25" maxlength="15"></html:text></span></td>
				<!-- GBOC Acc No. -->
				<td><bean:message key="screen.m_cst_s.label_gboc_acc_no" /></td>
				<td><bean:message key="screen.m_cst.label_colon" /> <span
					id="spn_co_regno" class='<%=classViewMode%>'><bean:write
							name="_m_cstForm" property="gboc_acc_no"></bean:write></span> <span
					class='<%=classNewMode%>'><html:text name="_m_cstForm"
							property="gboc_acc_no" size="25" maxlength="20"></html:text></span></td>

			</tr>
			<tr>
				<!-- company website -->
				<td><bean:message key="screen.m_cst.label_company_website" /></td>
				<td><bean:message key="screen.m_cst.label_colon" /> <span
					id="spn_co_website" class='<%=classViewMode%>'><bean:write
							name="_m_cstForm" property="co_website"></bean:write></span> <span
					class='<%=classNewMode%>'><html:text name="_m_cstForm"
							property="co_website" size="25" maxlength="100"></html:text></span></td>
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
				<td><bean:message key="screen.m_cst.label_colon" /> <span
					id="spn_co_website" class='<%=classViewMode%>'><bean:write
							name="_m_cstForm" property="others_ref_no"></bean:write></span> <span
					class='<%=classNewMode%>'><html:text name="_m_cstForm"
							property="others_ref_no" size="25" maxlength="20"></html:text></span></td>
			</tr>
			<tr>
				<!-- company email -->
				<td><bean:message key="screen.m_cst.label_company_email" /></td>
				<td><bean:message key="screen.m_cst.label_colon" /> <span
					id="spn_co_email" class='<%=classViewMode%>'><bean:write
							name="_m_cstForm" property="co_email"></bean:write></span> <span
					class='<%=classNewMode%>'><html:text name="_m_cstForm"
							property="co_email" size="25" maxlength="50"></html:text></span></td>
				<!-- saleforces.com acc. no-->
				<td><bean:message key="screen.m_cst_s.label_salesforce_com_id" /></td>
				<td><bean:message key="screen.m_cst.label_colon" /> <span
					id="spn_co_email" class='<%=classViewMode%>'><bean:write
							name="_m_cstForm" property="sales_force_acc_id"></bean:write></span> <span
					class='<%=classNewMode%>'><html:text name="_m_cstForm"
							property="sales_force_acc_id" size="25" maxlength="20"></html:text></span>
				</td>

			</tr>
			<tr>
				<!-- telephone no -->
				<td><bean:message key="screen.m_cst.label_telephone_no" /></td>
				<td><bean:message key="screen.m_cst.label_colon" /> <span
					id="spn_co_tel_no" class='<%=classViewMode%>'><bean:write
							name="_m_cstForm" property="co_tel_no"></bean:write></span> <span
					class='<%=classNewMode%>'><html:text name="_m_cstForm"
							property="co_tel_no" size="25" maxlength="20"></html:text></span></td>
				<!-- Affiliate NTT -->
				<td><bean:message key="screen.m_cst_s.label_customer_group" />

				</td>
				<td><bean:message key="screen.m_cst.label_colon" /> <logic:equal
						value="READONLY" name="_m_cstForm" property="mode">
						<t:writeCodeValue name="_m_cstForm" property="affiliate_ntt"
							codeList="AFFILIATE_CODE"></t:writeCodeValue>
					</logic:equal> <span class='<%=classNewMode%>'> <html:select
							styleId="affiliate_ntt_select" name="_m_cstForm"
							property="affiliate_ntt">
							<html:option value="">
								<bean:message key="screen.m_cst.label_blank" />
							</html:option>
							<html:options collection="AFFILIATE_CODE" property="id"
								labelProperty="name" />
						</html:select>
				</span></td>

			</tr>
			<tr>
				<!-- fax no -->
				<td><bean:message key="screen.m_cst.label_fax_no" /> <!-- <span class="<%=classNewMode%>" style="color: red"><bean:message key="screen.m_cst.label_mandatory"/></span> -->
				</td>
				<td><bean:message key="screen.m_cst.label_colon" /> <span
					id="spn_co_fax_no" class='<%=classViewMode%>'><bean:write
							name="_m_cstForm" property="co_fax_no"></bean:write></span> <span
					class='<%=classNewMode%>'><html:text name="_m_cstForm"
							property="co_fax_no" size="25" maxlength="20"></html:text></span></td>
				<!-- End User -->
				<td><bean:message key="screen.m_cst_s.label_end_user" /></td>
				<td><bean:message key="screen.m_cst.label_colon" /> <span
					id="spn_end_user" class='<%=classViewMode%>'> <logic:equal
							value="READONLY" name="_m_cstForm" property="mode">
							<t:writeCodeValue name="_m_cstForm" property="end_user"
								codeList="LIST_End_User"></t:writeCodeValue>
						</logic:equal>
				</span> <span class='<%=classNewMode%>'> <html:select
							name="_m_cstForm" property="end_user">
							<html:options collection="LIST_End_User" property="id"
								labelProperty="name" />
						</html:select>
				</span></td>
			</tr>
			<tr>
				<td colspan="2"></td>
				<%-- Company Category --%>
				<td><bean:message key="screen.m_cst_s.label_company_category" />
				</td>
				<td><bean:message key="screen.m_cst.label_colon" /> <span
					id="spn_company_category" class='<%=classViewMode%>'> <logic:equal
							value="READONLY" name="_m_cstForm" property="mode">
							<logic:equal value="1" name="_m_cstForm"
								property="category_enableFlg">
								<t:writeCodeValue name="_m_cstForm" property="company_category"
									codeList="COMPANY_CATEGORY"></t:writeCodeValue>
							</logic:equal>
							<logic:notEqual value="1" name="_m_cstForm"
								property="category_enableFlg">
							-
						</logic:notEqual>
						</logic:equal>
				</span> <span class='<%=classNewMode%>'> <logic:equal value="1"
							name="_m_cstForm" property="category_enableFlg">
							<logic:equal value="J" name="_m_cstForm"
								property="company_category">
								<html:select name="_m_cstForm" property="company_category">
									<html:options collection="COMPANY_CATEGORY" property="id"
										labelProperty="name" />
								</html:select>
							</logic:equal>
							<logic:notEqual value="J" name="_m_cstForm"
								property="company_category">
								<html:select name="_m_cstForm" property="company_category"
									value="N">
									<html:options collection="COMPANY_CATEGORY" property="id"
										labelProperty="name" />
								</html:select>
							</logic:notEqual>
						</logic:equal> <logic:notEqual value="1" name="_m_cstForm"
							property="category_enableFlg">
							<logic:equal value="J" name="_m_cstForm"
								property="company_category">
								<html:select name="_m_cstForm" property="company_category"
									styleId="company_categoryList" disabled="true">
									<html:options collection="COMPANY_CATEGORY" property="id"
										labelProperty="name" />
								</html:select>
							</logic:equal>
							<logic:notEqual value="J" name="_m_cstForm"
								property="company_category">
								<html:select name="_m_cstForm" property="company_category"
									value="N" styleId="company_categoryList" disabled="true">
									<html:options collection="COMPANY_CATEGORY" property="id"
										labelProperty="name" />
								</html:select>
							</logic:notEqual>
						</logic:notEqual>
				</span></td>
			</tr>
			<tr>
				<td colspan="2"></td>
				<%-- Company Sub Category  --%>
				<td><bean:message
						key="screen.m_cst_s.label_company_sub_category" /></td>
				<td><bean:message key="screen.m_cst.label_colon" /> <span
					id="spn_company_sub_category" class='<%=classViewMode%>'>
						<logic:equal value="READONLY" name="_m_cstForm" property="mode">
							<logic:equal value="1" name="_m_cstForm"
								property="subCategory_enableFlg">
								<t:writeCodeValue name="_m_cstForm"
									property="company_sub_category" codeList="COMPANY_SUB_CATEGORY"></t:writeCodeValue>
							</logic:equal>
							<logic:notEqual value="1" name="_m_cstForm"
								property="subCategory_enableFlg">
							-
						</logic:notEqual>
						</logic:equal>
				</span> <span class='<%=classNewMode%>'> <logic:equal value="1"
							name="_m_cstForm" property="subCategory_enableFlg">
							<html:select name="_m_cstForm" property="company_sub_category">
								<html:options collection="COMPANY_SUB_CATEGORY" property="id"
									labelProperty="name" />
							</html:select>
						</logic:equal> <logic:notEqual value="1" name="_m_cstForm"
							property="subCategory_enableFlg">
							<html:select name="_m_cstForm" property="company_sub_category"
								disabled="true" styleId="company_sub_categoryList">
								<html:options collection="COMPANY_SUB_CATEGORY" property="id"
									labelProperty="name" />
							</html:select>
						</logic:notEqual>
				</span></td>
			</tr>
			<tr>
				<td colspan="2"></td>
				<%-- Company Bank Info  --%>
				<td><bean:message key="screen.m_cst_s.label_company_bank_info" />
				</td>
				<td><bean:message key="screen.m_cst.label_colon" /> <span
					id="spn_company_bank_info" class='<%=classViewMode%>'> <logic:equal
							value="READONLY" name="_m_cstForm" property="mode">
							<logic:equal value="1" name="_m_cstForm"
								property="bankInfo_enableFlg">
								<logic:iterate id="companyBankName" name="_m_cstForm"
									property="companyBankInfoList" indexId="index">
									<logic:equal value="${_m_cstForm.company_bank_info}"
										name="companyBankName" property="value">
										<bean:write name="companyBankName" property="label" />
									</logic:equal>
								</logic:iterate>
							</logic:equal>
							<logic:notEqual value="1" name="_m_cstForm"
								property="bankInfo_enableFlg">
							-
						</logic:notEqual>
						</logic:equal>
				</span> <span class='<%=classNewMode%>'> <logic:equal value="1"
							name="_m_cstForm" property="bankInfo_enableFlg">
							<html:select name="_m_cstForm" property="company_bank_info"
								styleId="companyBankInfoList">
								<html:optionsCollection property="companyBankInfoList"
									name="_m_cstForm" label="label" value="value" />
							</html:select>
						</logic:equal> <logic:notEqual value="1" name="_m_cstForm"
							property="bankInfo_enableFlg">
							<html:select name="_m_cstForm" property="company_bank_info"
								styleId="companyBankInfoList" disabled="true">
								<html:optionsCollection property="companyBankInfoList"
									name="_m_cstForm" label="label" value="value" />
							</html:select>
						</logic:notEqual>
				</span> <input type="hidden" id="companyBankInfo"
					value="${_m_cstForm.company_bank_info}" /></td>
			</tr>
			<!--Add by MiffyAn Start -->
			<tr>
				<td colspan="2"></td>
				<%-- Company Sub Category  --%>
				<td><bean:message key="screen.m_cst_s.label_company_type_info" />

				</td>
				<td><bean:message key="screen.m_cst.label_colon" /> <span
					id="spn_company_type" class='<%=classViewMode%>'> <logic:equal
							value="READONLY" name="_m_cstForm" property="mode">
							<logic:equal value="1" name="_m_cstForm"
								property="company_enableFlg">
								<t:writeCodeValue name="_m_cstForm" property="company_type"
									codeList="LIST_COMPLANY_TYPE"></t:writeCodeValue>
							</logic:equal>
							<logic:notEqual value="1" name="_m_cstForm"
								property="category_enableFlg">
							-
						</logic:notEqual>
						</logic:equal>
				</span> <span class='<%=classNewMode%>'> <logic:equal value="1"
							name="_m_cstForm" property="company_enableFlg">
							<html:select name="_m_cstForm" property="company_type">
								<html:options collection="LIST_COMPLANY_TYPE" property="id"
									labelProperty="name" />
							</html:select>
						</logic:equal> <logic:notEqual value="1" name="_m_cstForm"
							property="company_enableFlg">
							<html:select name="_m_cstForm" property="company_type"
								disabled="true" styleId="company_typeList">
								<html:options collection="LIST_COMPLANY_TYPE" property="id"
									labelProperty="name" />
							</html:select>
						</logic:notEqual>
				</span></td>
			</tr>
			<!--Add by MiffyAn End -->
			<tr>
				<!-- checkbox -->
				<td colspan="2">
					<!-- loading inter_comp --> <span id="spn_inter_comp"
					class="hidden"><bean:write name="_m_cstForm"
							property="inter_comp" /></span> <logic:equal name="_m_cstForm"
						property="mode" value="READONLY">
						<html:checkbox styleId="chk_inter_comp" disabled="true"
							name="_m_cstForm" property="inter_comp" onclick="checkChkInter()">
							<bean:message key="screen.m_cst.label_inter_company" />
						</html:checkbox>
					</logic:equal> <logic:notEqual name="_m_cstForm" property="mode" value="READONLY">
						<html:checkbox styleId="chk_inter_comp" name="_m_cstForm"
							property="inter_comp" onclick="checkChkInter()">
							<bean:message key="screen.m_cst.label_inter_company" />
						</html:checkbox>
					</logic:notEqual>
				</td>
				<%-- Account Manager (Primary) --%>
				<td><bean:message key="screen.m_cst.label_primary" /></td>
				<td><bean:message key="screen.m_cst.label_colon" /> <span
					id="spn_co_fax_no" class='<%=classViewMode%>'><bean:write
							name="_m_cstForm" property="managerPrimary"></bean:write></span> <span
					class='<%=classNewMode%>'> <input type="text" size="25"
						name="managerPrimary" class="expand" id="primary"
						autocomplete="off" value="${_m_cstForm['managerPrimary']}"
						maxlength="50" /> <script>
							var obj1 = actb(document.getElementById("primary"),
									customarray,
									"<bean:write name='_m_cstForm' property='listUser'/>");
						</script>
				</span></td>
			</tr>
			<tr>
				<!-- checkbox -->
				<td>
					<!-- loading print statement --> <span id="spn_inter_comp"
					class="hidden"><bean:write name="_m_cstForm"
							property="print_statement" /></span> <logic:equal name="_m_cstForm"
						property="mode" value="READONLY">
						<html:checkbox styleId="chk_inter_comp" disabled="true"
							name="_m_cstForm" property="print_statement">
							<bean:message key="screen.m_cst_s.label_print_statement" />
						</html:checkbox>
					</logic:equal> <logic:notEqual name="_m_cstForm" property="mode" value="READONLY">
						<html:checkbox styleId="chk_inter_comp" name="_m_cstForm"
							property="print_statement">
							<bean:message key="screen.m_cst_s.label_print_statement" />
						</html:checkbox>
					</logic:notEqual>
				</td>
				<td><logic:equal name="_m_cstForm" property="mode"
						value="READONLY">
						<logic:equal value="2" name="_m_cstForm" property="accessType">
							<logic:notEqual value="0" name="_m_cstForm"
								property="bi_sub_module">
								<a class="hyperLink"
									onclick="submitForm('bankInfo','<bean:message key="info.ERR4SC001"/>', '<%=request.getContextPath()%>');"><bean:message
										key="screen.m_cst_s.label_bank_infomation" /></a>
							</logic:notEqual>
						</logic:equal>
					</logic:equal> <logic:equal name="_m_cstForm" property="mode" value="EDITMODE">
						<logic:equal value="2" name="_m_cstForm" property="accessType">
							<logic:equal value="2" name="_m_cstForm" property="bi_sub_module">
								<a class="hyperLink"
									onclick="submitForm('bankInfo','<bean:message key="info.ERR4SC001"/>', '<%=request.getContextPath()%>');"><bean:message
										key="screen.m_cst_s.label_bank_infomation" /></a>
							</logic:equal>
						</logic:equal>
					</logic:equal> <logic:equal name="_m_cstForm" property="mode" value="NEWMODE">
						<bean:message key="screen.m_cst_s.label_bank_infomation" />
					</logic:equal> <logic:empty name="_m_cstForm" property="mode">
						<bean:message key="screen.m_cst_s.label_bank_infomation" />
					</logic:empty></td>
				<%-- Account Manager (Primary) --%>
				<td><bean:message key="screen.m_cst.label_secondary" /></td>
				<td><bean:message key="screen.m_cst.label_colon" /> <span
					id="spn_co_fax_no" class='<%=classViewMode%>'><bean:write
							name="_m_cstForm" property="managerSecondary"></bean:write></span> <span
					class='<%=classNewMode%>'> <input type="text" size="25"
						name="managerSecondary" class="expand" id="secondary"
						autocomplete="off" value="${_m_cstForm['managerSecondary']}"
						maxlength="50" /> <script>
							var obj1 = actb(document
									.getElementById("secondary"), customarray,
									"<bean:write name='_m_cstForm' property='listUser'/>");
						</script>
				</span></td>
			</tr>

		</table>

		<!-- addresses -->
		<table class="addressinfo" cellpadding="0" cellspacing="1">
			<tr>
				<td colspan="2" class="header"><bean:message
						key="screen.m_cst.label_addresses" /></td>
			</tr>
			<tr>
				<td class="mid">
					<!-- JSP Tab control --> <input type="hidden"
					id="lbl_primary_contact"
					value='<bean:message key="screen.m_cst.label_billing_contact"/>'>
					<ul id="addresstabs" class="shadetabs">
						<li><a href="#" rel="address_layout01"><bean:message
									key="screen.m_cst.label_bl_address" /></a></li>
						<li><a href="#" rel="address_layout02"><bean:message
									key="screen.m_cst.label_RCT_address" /></a></li>
					</ul>
					<div
						style="border: 1px solid gray; width: 100%; margin-bottom: 1em; padding: 10px">
						<!--Billing Address Start-->
						<div id="address_layout01" class="tabcontent">
							<!--Billing Address1&2-->
							<div id="billing_address12_layout">
								<table border="0" width="100%" align="left" cellpadding="0"
									cellspacing="0">
									<!--Billing Address 1-->
									<tr>
										<td width="100%" height="10px"><span
											class="underlineTitle"><bean:message
													key="screen.m_cst.billing_address1" /></span> <span
											class='<%=classNewMode%>' style="color: red"><bean:message
													key="screen.m_cst.label_mandatory" /></span> <logic:notEqual
												name="_m_cstForm" property="mode" value="READONLY">
												<html:checkbox styleId="chk_inter_comp" name="_m_cstForm"
													property="copy_to_cor_tech" onclick="copy2CorTech();">
													<bean:message key="screen.m_cst_s.label_copy_2_cor_tech" />
												</html:checkbox>
											</logic:notEqual></td>
									</tr>
									<tr height="10px" align="left" valign="top">
										<td><span id="spn_ba_adr_line1"
											class='<%=classViewMode%>'><bean:write
													name="_m_cstForm" property="ba_adr_line1"></bean:write></span> <span
											class='<%=classNewMode%>'><html:text
													name="_m_cstForm" property="ba_adr_line1"
													style="width:100%; padding-top: 0px;padding-bottom: 0px;"
													maxlength="150" onkeyup="copy2CorTech();"></html:text></span></td>
									</tr>
									<tr id="ba_adr_line_2" height="10px" align="left" valign="top">
										<td><span id="spn_ba_adr_line2_v"
											class='<%=classViewMode%>'><bean:write
													name="_m_cstForm" property="ba_adr_line2"></bean:write></span> <span
											class='<%=classNewMode%>'><html:text
													name="_m_cstForm" property="ba_adr_line2"
													style="width:100%; padding-top: 0px;" maxlength="150"
													onkeyup="copy2CorTech();"></html:text></span></td>
									</tr>
									<tr id="ba_adr_line_3" height="10px" align="left" valign="top">
										<td><span id="spn_ba_adr_line3_v"
											class='<%=classViewMode%>'><bean:write
													name="_m_cstForm" property="ba_adr_line3"></bean:write></span> <span
											class='<%=classNewMode%>'><html:text
													name="_m_cstForm" property="ba_adr_line3"
													style="width:100%; padding-top: 0px;" maxlength="150"
													onkeyup="copy2CorTech();"></html:text></span></td>
									</tr>
									<tr height="10px">
										<td><logic:equal name="_m_cstForm" property="mode"
												value="READONLY">
												<bean:write name="_m_cstForm" property="ba_zip_code"></bean:write>
											</logic:equal> <logic:equal name="_m_cstForm" property="mode"
												value="EDITMODE">
												<html:text property="ba_zip_code" name="_m_cstForm"
													style="width:34%; float:left;padding-top: 0px;"
													maxlength="15" onkeyup="copy2CorTech();"></html:text>
											</logic:equal> <logic:equal name="_m_cstForm" property="mode"
												value="NEWMODE">
												<html:text property="ba_zip_code" name="_m_cstForm"
													style="width:34%; float:left;padding-top: 0px;"
													maxlength="15"
													onfocus="if (this.value == 'Zip Code') this.value='';"
													onkeyup="copy2CorTech();"
													onblur="if (this.value == '') this.value='Zip Code';"></html:text>
											</logic:equal> <logic:empty name="_m_cstForm" property="mode">
												<html:text property="ba_zip_code" name="_m_cstForm"
													style="width:34%; float:left;padding-top: 0px;"
													maxlength="15"
													onfocus="if (this.value == 'Zip Code') this.value='';"
													onkeyup="copy2CorTech();"
													onblur="if (this.value == '') this.value='Zip Code';"></html:text>
											</logic:empty> <span class='<%=classViewMode%>'> <logic:equal
													name="_m_cstForm" property="mode" value="READONLY">
													<logic:notEmpty name="_m_cstForm" property="ba_country">
														<t:writeCodeValue name="_m_cstForm" property="ba_country"
															codeList="LIST_COUNTRY"></t:writeCodeValue>
													</logic:notEmpty>
												</logic:equal>
										</span> <!-- loading country id --> <span id="spn_ba_country_id"
											class="hidden"><bean:write name="_m_cstForm"
													property="ba_country_id" /></span> <span
											class='<%=classNewMode%>'> <html:select
													name="_m_cstForm" property="ba_country_id"
													onchange="copy2CorTech();" style="width:64%; float:right;">
													<html:option value="">
														<bean:message key="screen.m_cst.label_blank" />
													</html:option>
													<html:options collection="LIST_COUNTRY" property="id"
														labelProperty="name" />
												</html:select>
										</span></td>
									</tr>
									<!--Billing Address 2-->
									<tr>
										<td width="100%" height="10px"><span
											class="underlineTitle"><bean:message
													key="screen.m_cst.billing_address2" /></span></td>
									</tr>
									<tr height="10px" align="left" valign="top">
										<td><span id="spn_ba_adr2_line1"
											class='<%=classViewMode%>'><bean:write
													name="_m_cstForm" property="ba_adr2_line1"></bean:write></span> <span
											class='<%=classNewMode%>'><html:text
													name="_m_cstForm" property="ba_adr2_line1"
													style="width:100%; padding-top: 0px;padding-bottom: 0px;"
													maxlength="150" onkeyup="copy2CorTech();"></html:text></span></td>
									</tr>
									<tr id="ba_adr2_line_2" height="10px" align="left" valign="top">
										<td><span id="spn_ba_adr2_line2_v"
											class='<%=classViewMode%>'><bean:write
													name="_m_cstForm" property="ba_adr2_line2"></bean:write></span> <span
											class='<%=classNewMode%>'><html:text
													name="_m_cstForm" property="ba_adr2_line2"
													style="width:100%; padding-top: 0px;" maxlength="150"
													onkeyup="copy2CorTech();"></html:text></span></td>
									</tr>
									<tr id="ba_adr2_line_3" height="10px" align="left" valign="top">
										<td><span id="spn_ba_adr2_line3_v"
											class='<%=classViewMode%>'><bean:write
													name="_m_cstForm" property="ba_adr2_line3"></bean:write></span> <span
											class='<%=classNewMode%>'><html:text
													name="_m_cstForm" property="ba_adr2_line3"
													style="width:100%; padding-top: 0px;" maxlength="150"
													onkeyup="copy2CorTech();"></html:text></span></td>
									</tr>
									<tr height="10px">
										<td><logic:equal name="_m_cstForm" property="mode"
												value="READONLY">
												<bean:write name="_m_cstForm" property="ba_zip_code2"></bean:write>
											</logic:equal> <logic:equal name="_m_cstForm" property="mode"
												value="EDITMODE">
												<html:text property="ba_zip_code2" name="_m_cstForm"
													style="width:34%; float:left;padding-top: 0px;"
													maxlength="15" onkeyup="copy2CorTech();"></html:text>
											</logic:equal> <logic:equal name="_m_cstForm" property="mode"
												value="NEWMODE">
												<html:text property="ba_zip_code2" name="_m_cstForm"
													style="width:34%; float:left;padding-top: 0px;"
													maxlength="15"
													onfocus="if (this.value == 'Zip Code') this.value='';"
													onkeyup="copy2CorTech();"
													onblur="if (this.value == '') this.value='Zip Code';"></html:text>
											</logic:equal> <logic:empty name="_m_cstForm" property="mode">
												<html:text property="ba_zip_code2" name="_m_cstForm"
													style="width:34%; float:left;padding-top: 0px;"
													maxlength="15"
													onfocus="if (this.value == 'Zip Code') this.value='';"
													onkeyup="copy2CorTech();"
													onblur="if (this.value == '') this.value='Zip Code';"></html:text>
											</logic:empty> <span class='<%=classViewMode%>'> <logic:equal
													name="_m_cstForm" property="mode" value="READONLY">
													<logic:notEmpty name="_m_cstForm" property="ba_country2">
														<t:writeCodeValue name="_m_cstForm" property="ba_country2"
															codeList="LIST_COUNTRY"></t:writeCodeValue>
													</logic:notEmpty>
												</logic:equal>
										</span> <!-- loading country id --> <span id="spn_ba2_country_id"
											class="hidden"><bean:write name="_m_cstForm"
													property="ba_country2_id" /></span> <span
											class='<%=classNewMode%>'> <html:select
													name="_m_cstForm" property="ba_country2_id"
													onchange="copy2CorTech();" style="width:64%; float:right;">
													<html:option value="">
														<bean:message key="screen.m_cst.label_blank" />
													</html:option>
													<html:options collection="LIST_COUNTRY" property="id"
														labelProperty="name" />
												</html:select>
										</span></td>
									</tr>
								</table>
							</div>
							<!--Billing Address3&4 -->
							<div id="billing_address34_layout">
								<table border="0" width="100%" align="left" cellpadding="0"
									cellspacing="0">
									<!--Billing Address 3-->
									<tr>
										<td width="100%" height="10px"><span
											class="underlineTitle"><bean:message
													key="screen.m_cst.billing_address3" /></span></td>
									</tr>
									<tr height="10px" align="left" valign="top">
										<td><span id="spn_ba_adr3_line1"
											class='<%=classViewMode%>'><bean:write
													name="_m_cstForm" property="ba_adr3_line1"></bean:write></span> <span
											class='<%=classNewMode%>'><html:text
													name="_m_cstForm" property="ba_adr3_line1"
													style="width:100%; padding-top: 0px;padding-bottom: 0px;"
													maxlength="150" onkeyup="copy2CorTech();"></html:text></span></td>
									</tr>
									<tr id="ba_adr3_line_2" height="10px" align="left" valign="top">
										<td><span id="spn_ba_adr3_line2_v"
											class='<%=classViewMode%>'><bean:write
													name="_m_cstForm" property="ba_adr3_line2"></bean:write></span> <span
											class='<%=classNewMode%>'><html:text
													name="_m_cstForm" property="ba_adr3_line2"
													style="width:100%; padding-top: 0px;" maxlength="150"
													onkeyup="copy2CorTech();"></html:text></span></td>
									</tr>
									<tr id="ba_adr3_line_3" height="10px" align="left" valign="top">
										<td><span id="spn_ba_adr3_line3_v"
											class='<%=classViewMode%>'><bean:write
													name="_m_cstForm" property="ba_adr3_line3"></bean:write></span> <span
											class='<%=classNewMode%>'><html:text
													name="_m_cstForm" property="ba_adr3_line3"
													style="width:100%; padding-top: 0px;" maxlength="150"
													onkeyup="copy2CorTech();"></html:text></span></td>
									</tr>
									<tr height="10px">
										<td><logic:equal name="_m_cstForm" property="mode"
												value="READONLY">
												<bean:write name="_m_cstForm" property="ba_zip_code3"></bean:write>
											</logic:equal> <logic:equal name="_m_cstForm" property="mode"
												value="EDITMODE">
												<html:text property="ba_zip_code3" name="_m_cstForm"
													style="width:34%; float:left;padding-top: 0px;"
													maxlength="15" onkeyup="copy2CorTech();"></html:text>
											</logic:equal> <logic:equal name="_m_cstForm" property="mode"
												value="NEWMODE">
												<html:text property="ba_zip_code3" name="_m_cstForm"
													style="width:34%; float:left;padding-top: 0px;"
													maxlength="15"
													onfocus="if (this.value == 'Zip Code') this.value='';"
													onkeyup="copy2CorTech();"
													onblur="if (this.value == '') this.value='Zip Code';"></html:text>
											</logic:equal> <logic:empty name="_m_cstForm" property="mode">
												<html:text property="ba_zip_code3" name="_m_cstForm"
													style="width:34%; float:left;padding-top: 0px;"
													maxlength="15"
													onfocus="if (this.value == 'Zip Code') this.value='';"
													onkeyup="copy2CorTech();"
													onblur="if (this.value == '') this.value='Zip Code';"></html:text>
											</logic:empty> <span class='<%=classViewMode%>'> <logic:equal
													name="_m_cstForm" property="mode" value="READONLY">
													<logic:notEmpty name="_m_cstForm" property="ba_country3">
														<t:writeCodeValue name="_m_cstForm" property="ba_country3"
															codeList="LIST_COUNTRY"></t:writeCodeValue>
													</logic:notEmpty>
												</logic:equal>
										</span> <!-- loading country id --> <span id="spn_ba3_country_id"
											class="hidden"><bean:write name="_m_cstForm"
													property="ba_country3_id" /></span> <span
											class='<%=classNewMode%>'> <html:select
													name="_m_cstForm" property="ba_country3_id"
													onchange="copy2CorTech();" style="width:64%; float:right;">
													<html:option value="">
														<bean:message key="screen.m_cst.label_blank" />
													</html:option>
													<html:options collection="LIST_COUNTRY" property="id"
														labelProperty="name" />
												</html:select>
										</span></td>
									</tr>
									<!--Billing Address 4-->
									<tr>
										<td width="100%" height="10px"><span
											class="underlineTitle"><bean:message
													key="screen.m_cst.billing_address4" /></span></td>
									</tr>
									<tr height="10px" align="left" valign="top">
										<td><span id="spn_ba_adr4_line1"
											class='<%=classViewMode%>'><bean:write
													name="_m_cstForm" property="ba_adr4_line1"></bean:write></span> <span
											class='<%=classNewMode%>'><html:text
													name="_m_cstForm" property="ba_adr4_line1"
													style="width:100%; padding-top: 0px;padding-bottom: 0px;"
													maxlength="150" onkeyup="copy2CorTech();"></html:text></span></td>
									</tr>
									<tr id="ba_adr4_line_2" height="10px" align="left" valign="top">
										<td><span id="spn_ba_adr4_line2_v"
											class='<%=classViewMode%>'><bean:write
													name="_m_cstForm" property="ba_adr4_line2"></bean:write></span> <span
											class='<%=classNewMode%>'><html:text
													name="_m_cstForm" property="ba_adr4_line2"
													style="width:100%; padding-top: 0px;" maxlength="150"
													onkeyup="copy2CorTech();"></html:text></span></td>
									</tr>
									<tr id="ba_adr4_line_3" height="10px" align="left" valign="top">
										<td><span id="spn_ba_adr4_line3_v"
											class='<%=classViewMode%>'><bean:write
													name="_m_cstForm" property="ba_adr4_line3"></bean:write></span> <span
											class='<%=classNewMode%>'><html:text
													name="_m_cstForm" property="ba_adr4_line3"
													style="width:100%; padding-top: 0px;" maxlength="150"
													onkeyup="copy2CorTech();"></html:text></span></td>
									</tr>
									<tr height="10px">
										<td><logic:equal name="_m_cstForm" property="mode"
												value="READONLY">
												<bean:write name="_m_cstForm" property="ba_zip_code4"></bean:write>
											</logic:equal> <logic:equal name="_m_cstForm" property="mode"
												value="EDITMODE">
												<html:text property="ba_zip_code4" name="_m_cstForm"
													style="width:34%; float:left;padding-top: 0px;"
													maxlength="15" onkeyup="copy2CorTech();"></html:text>
											</logic:equal> <logic:equal name="_m_cstForm" property="mode"
												value="NEWMODE">
												<html:text property="ba_zip_code4" name="_m_cstForm"
													style="width:34%; float:left;padding-top: 0px;"
													maxlength="15"
													onfocus="if (this.value == 'Zip Code') this.value='';"
													onkeyup="copy2CorTech();"
													onblur="if (this.value == '') this.value='Zip Code';"></html:text>
											</logic:equal> <logic:empty name="_m_cstForm" property="mode">
												<html:text property="ba_zip_code4" name="_m_cstForm"
													style="width:34%; float:left;padding-top: 0px;"
													maxlength="15"
													onfocus="if (this.value == 'Zip Code') this.value='';"
													onkeyup="copy2CorTech();"
													onblur="if (this.value == '') this.value='Zip Code';"></html:text>
											</logic:empty> <span class='<%=classViewMode%>'> <logic:equal
													name="_m_cstForm" property="mode" value="READONLY">
													<logic:notEmpty name="_m_cstForm" property="ba_country4">
														<t:writeCodeValue name="_m_cstForm" property="ba_country4"
															codeList="LIST_COUNTRY"></t:writeCodeValue>
													</logic:notEmpty>
												</logic:equal>
										</span> <!-- loading country id --> <span id="spn_ba4_country_id"
											class="hidden"><bean:write name="_m_cstForm"
													property="ba_country4_id" /></span> <span
											class='<%=classNewMode%>'> <html:select
													name="_m_cstForm" property="ba_country4_id"
													onchange="copy2CorTech();" style="width:64%; float:right;">
													<html:option value="">
														<bean:message key="screen.m_cst.label_blank" />
													</html:option>
													<html:options collection="LIST_COUNTRY" property="id"
														labelProperty="name" />
												</html:select>
										</span></td>
									</tr>
								</table>
							</div>
						</div>
						<!--Billing Address End-->
						<div id="address_layout02" class="tabcontent">
							<div id="address_layout_regis">
								<table border="0" width="100%" align="left" cellpadding="0"
									cellspacing="0">
									<tr>
										<td width="100%" height="10px"><b><u> <span
													class="underlineTitle"><bean:message
															key="screen.m_cst.label_registered_address" /></span></u></b> <span
											class='<%=classNewMode%>' style="color: red"><bean:message
													key="screen.m_cst.label_mandatory" /></span></td>
									</tr>
									<tr height="10px" align="left" valign="top">
										<td><span id="spn_ra_adr_line1"
											class='<%=classViewMode%>'><bean:write
													name="_m_cstForm" property="ra_adr_line1"></bean:write></span> <span
											class='<%=classNewMode%>'><html:text
													name="_m_cstForm" property="ra_adr_line1"
													style="width:100%; padding-top: 0px;padding-bottom: 0px;"
													maxlength="150"></html:text></span></td>
									</tr>
									<tr id="ra_adr_line_2" height="10px" align="left" valign="top">
										<td><span id="spn_ra_adr_line2_v"
											class='<%=classViewMode%>'><bean:write
													name="_m_cstForm" property="ra_adr_line2"></bean:write></span> <span
											class='<%=classNewMode%>'><html:text
													name="_m_cstForm" property="ra_adr_line2"
													style="width:100%; padding-top: 0px;" maxlength="150"></html:text></span>
										</td>
									</tr>
									<tr id="ra_adr_line_3" height="10px" align="left" valign="top">
										<td><span id="spn_ra_adr_line3_v"
											class='<%=classViewMode%>'><bean:write
													name="_m_cstForm" property="ra_adr_line3"></bean:write></span> <span
											class='<%=classNewMode%>'><html:text
													name="_m_cstForm" property="ra_adr_line3"
													style="width:100%; padding-top: 0px;" maxlength="150"></html:text></span>
										</td>
									</tr>
									<tr height="10px">
										<td><logic:equal name="_m_cstForm" property="mode"
												value="READONLY">
												<bean:write name="_m_cstForm" property="ra_zip_code"></bean:write>
											</logic:equal> <logic:equal name="_m_cstForm" property="mode"
												value="EDITMODE">
												<html:text property="ra_zip_code" name="_m_cstForm"
													style="width:34%; float:left;padding-top: 0px;"
													maxlength="15"></html:text>
											</logic:equal> <logic:equal name="_m_cstForm" property="mode"
												value="NEWMODE">
												<html:text property="ra_zip_code" name="_m_cstForm"
													style="width:34%; float:left;padding-top: 0px;"
													maxlength="15"
													onfocus="if (this.value == 'Zip Code') this.value='';"
													onblur="if (this.value == '') this.value='Zip Code';"></html:text>
											</logic:equal> <logic:empty name="_m_cstForm" property="mode">
												<html:text property="ra_zip_code" name="_m_cstForm"
													style="width:34%; float:left;padding-top: 0px;"
													maxlength="15"
													onfocus="if (this.value == 'Zip Code') this.value='';"
													onblur="if (this.value == '') this.value='Zip Code';"></html:text>
											</logic:empty> <span class='<%=classViewMode%>'> <logic:equal
													name="_m_cstForm" property="mode" value="READONLY">
													<logic:notEmpty name="_m_cstForm" property="ra_country">
														<t:writeCodeValue name="_m_cstForm" property="ra_country"
															codeList="LIST_COUNTRY"></t:writeCodeValue>
													</logic:notEmpty>
												</logic:equal>
										</span> <!-- loading country id --> <span id="spn_ra_country_id"
											class="hidden"><bean:write name="_m_cstForm"
													property="ra_country_id" /></span> <span
											class='<%=classNewMode%>'> <html:select
													name="_m_cstForm" property="ra_country_id"
													style="width:64%; float:right;">
													<html:option value="">
														<bean:message key="screen.m_cst.label_blank" />
													</html:option>
													<html:options collection="LIST_COUNTRY" property="id"
														labelProperty="name" />
												</html:select>
										</span></td>
									</tr>

									<%-- <tr><td width="100%" height="10px">
								<span class="underlineTitle"><bean:message key="screen.m_cst.label_bl_address"/></span>
								<span class='<%= classNewMode%>' style="color: red"><bean:message key="screen.m_cst.label_mandatory"/></span>
								<logic:notEqual name="_m_cstForm" property="mode" value="READONLY">
									<html:checkbox styleId="chk_inter_comp" name="_m_cstForm" property="copy_to_cor_tech" onclick="copy2CorTech();"><bean:message key="screen.m_cst_s.label_copy_2_cor_tech"/></html:checkbox>
								</logic:notEqual>	
						</td></tr>
						<tr height="10px" align="left" valign="top"><td >
							<span id="spn_ba_adr_line1" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="ba_adr_line1"></bean:write></span>
							<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="ba_adr_line1" style="width:100%; padding-top: 0px;padding-bottom: 0px;" maxlength="150" onkeyup="copy2CorTech();"></html:text></span>				
						</td></tr>							
						<tr id="ba_adr_line_2" height="10px" align="left" valign="top"><td >						
							<span id="spn_ba_adr_line2_v" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="ba_adr_line2"></bean:write></span>
							<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="ba_adr_line2" style="width:100%; padding-top: 0px;" maxlength="150" onkeyup="copy2CorTech();"></html:text></span>				
						</td></tr>							
						<tr id="ba_adr_line_3" height="10px" align="left" valign="top"><td >
							<span id="spn_ba_adr_line3_v" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="ba_adr_line3"></bean:write></span>
							<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="ba_adr_line3" style="width:100%; padding-top: 0px;" maxlength="150" onkeyup="copy2CorTech();"></html:text></span>
						</td></tr>
						<tr height="10px">
							<td >
								<logic:equal name="_m_cstForm" property="mode" value="READONLY">
									<bean:write name="_m_cstForm" property="ba_zip_code"></bean:write>
								</logic:equal>
								<logic:equal name="_m_cstForm" property="mode" value="EDITMODE">
									<html:text property="ba_zip_code" name="_m_cstForm" style="width:34%; float:left;padding-top: 0px;" maxlength="15"  onkeyup="copy2CorTech();"></html:text>
								</logic:equal>
								<logic:equal name="_m_cstForm" property="mode" value="NEWMODE">
									<html:text property="ba_zip_code" name="_m_cstForm" style="width:34%; float:left;padding-top: 0px;" maxlength="15" onfocus="if (this.value == 'Zip Code') this.value='';"  onkeyup="copy2CorTech();" onblur="if (this.value == '') this.value='Zip Code';"></html:text>
								</logic:equal>
								<logic:empty name="_m_cstForm" property="mode" >
									<html:text property="ba_zip_code" name="_m_cstForm" style="width:34%; float:left;padding-top: 0px;" maxlength="15" onfocus="if (this.value == 'Zip Code') this.value='';" onkeyup="copy2CorTech();" onblur="if (this.value == '') this.value='Zip Code';"></html:text>
								</logic:empty>
								
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
								<html:select name="_m_cstForm" property="ba_country_id"  onchange="copy2CorTech();" style="width:64%; float:right;">
								  <html:option value="" ><bean:message key="screen.m_cst.label_blank"/></html:option>
								  <html:options collection="LIST_COUNTRY" property="id" labelProperty="name"/>
								</html:select>					
								</span>
							</td>
						</tr> --%>
								</table>
								<script>
									displayEmptyCells();
								</script>
							</div>
							<div id="address_layout_corr">
								<table border="0" width="100%" align="left" cellpadding="0"
									cellspacing="0">
									<tr>
										<td><span class="underlineTitle"><bean:message
													key="screen.m_cst.label_cpd_address" /></span></td>
									</tr>
									<tr>
										<td><span id="spn_ca_adr_line1"
											class='<%=classViewMode%>'><bean:write
													name="_m_cstForm" property="ca_adr_line1"></bean:write></span> <span
											class='<%=classNewMode%>'><html:text
													name="_m_cstForm" property="ca_adr_line1"
													style="width:100%; padding-top: 0px;padding-bottom: 0px;"
													maxlength="150"></html:text></span></td>
									</tr>
									<tr>
										<td><span id="spn_ca_adr_line2"
											class='<%=classViewMode%>'><bean:write
													name="_m_cstForm" property="ca_adr_line2"></bean:write></span> <span
											class='<%=classNewMode%>'><html:text
													name="_m_cstForm" property="ca_adr_line2"
													style="width:100%; padding-top: 0px;" maxlength="150"></html:text></span>
										</td>
									</tr>
									<tr>
										<td><span id="spn_ca_adr_line3"
											class='<%=classViewMode%>'><bean:write
													name="_m_cstForm" property="ca_adr_line3"></bean:write></span> <span
											class='<%=classNewMode%>'><html:text
													name="_m_cstForm" property="ca_adr_line3"
													style="width:100%; padding-top: 0px;" maxlength="150"></html:text></span>
										</td>
									</tr>
									<tr>
										<td><logic:equal name="_m_cstForm" property="mode"
												value="READONLY">
												<bean:write name="_m_cstForm" property="ca_zip_code"></bean:write>
											</logic:equal> <logic:equal name="_m_cstForm" property="mode"
												value="EDITMODE">
												<html:text property="ca_zip_code" name="_m_cstForm"
													style="width:34%; float:left;padding-top: 0px;"
													maxlength="15"></html:text>
											</logic:equal> <logic:equal name="_m_cstForm" property="mode"
												value="NEWMODE">
												<html:text property="ca_zip_code" name="_m_cstForm"
													style="width:34%; float:left;padding-top: 0px;"
													maxlength="15"
													onfocus="if (this.value == 'Zip Code') this.value='';"
													onblur="if (this.value == '') this.value='Zip Code';"></html:text>
											</logic:equal> <logic:empty name="_m_cstForm" property="mode">
												<html:text property="ca_zip_code" name="_m_cstForm"
													style="width:34%; float:left;padding-top: 0px;"
													maxlength="15"
													onfocus="if (this.value == 'Zip Code') this.value='';"
													onblur="if (this.value == '') this.value='Zip Code';"></html:text>
											</logic:empty> <span class='<%=classViewMode%>'> <logic:equal
													name="_m_cstForm" property="mode" value="READONLY">
													<logic:notEmpty name="_m_cstForm" property="ca_country">
														<t:writeCodeValue name="_m_cstForm" property="ca_country"
															codeList="LIST_COUNTRY"></t:writeCodeValue>
													</logic:notEmpty>
												</logic:equal>
										</span> <!-- loading country id --> <span id="spn_ca_country_id"
											class="hidden"><bean:write name="_m_cstForm"
													property="ca_country_id" /></span> <span
											class='<%=classNewMode%>'> <html:select
													name="_m_cstForm" property="ca_country_id"
													style="width:64%; float:right;">
													<html:option value="">
														<bean:message key="screen.m_cst.label_blank" />
													</html:option>
													<html:options collection="LIST_COUNTRY" property="id"
														labelProperty="name" />
												</html:select>
										</span></td>
									</tr>
									<tr>
										<td><span class="underlineTitle"><bean:message
													key="screen.m_cst_s.label_technical_address" /></span></td>
									</tr>
									<tr>
										<td><span id="spn_ta_adr_line1"
											class='<%=classViewMode%>'><bean:write
													name="_m_cstForm" property="ta_adr_line1"></bean:write></span> <span
											class='<%=classNewMode%>'><html:text
													name="_m_cstForm" property="ta_adr_line1"
													style="width:100%; padding-top: 0px;padding-bottom: 0px;"
													maxlength="150"></html:text></span></td>
									</tr>
									<tr>
										<td><span id="spn_ta_adr_line2"
											class='<%=classViewMode%>'><bean:write
													name="_m_cstForm" property="ta_adr_line2"></bean:write></span> <span
											class='<%=classNewMode%>'><html:text
													name="_m_cstForm" property="ta_adr_line2"
													style="width:100%; padding-top: 0px;" maxlength="150"></html:text></span>
										</td>
									</tr>
									<tr>
										<td><span id="spn_ta_adr_line3"
											class='<%=classViewMode%>'><bean:write
													name="_m_cstForm" property="ta_adr_line3"></bean:write></span> <span
											class='<%=classNewMode%>'><html:text
													name="_m_cstForm" property="ta_adr_line3"
													style="width:100%; padding-top: 0px;" maxlength="150"></html:text></span>
										</td>
									</tr>
									<tr>
										<td><logic:equal name="_m_cstForm" property="mode"
												value="READONLY">
												<bean:write name="_m_cstForm" property="ta_zip_code"></bean:write>
											</logic:equal> <logic:equal name="_m_cstForm" property="mode"
												value="EDITMODE">
												<html:text property="ta_zip_code" name="_m_cstForm"
													style="width:34%; float:left;padding-top: 0px;"
													maxlength="15"></html:text>
											</logic:equal> <logic:equal name="_m_cstForm" property="mode"
												value="NEWMODE">
												<html:text property="ta_zip_code" name="_m_cstForm"
													style="width:34%; float:left;padding-top: 0px;"
													maxlength="15"
													onfocus="if (this.value == 'Zip Code') this.value='';"
													onblur="if (this.value == '') this.value='Zip Code';"></html:text>
											</logic:equal> <logic:empty name="_m_cstForm" property="mode">
												<html:text property="ta_zip_code" name="_m_cstForm"
													style="width:34%; float:left;padding-top: 0px;"
													maxlength="15"
													onfocus="if (this.value == 'Zip Code') this.value='';"
													onblur="if (this.value == '') this.value='Zip Code';"></html:text>
											</logic:empty> <span class='<%=classViewMode%>'> <logic:equal
													name="_m_cstForm" property="mode" value="READONLY">
													<logic:notEmpty name="_m_cstForm" property="ta_country">
														<t:writeCodeValue name="_m_cstForm" property="ta_country"
															codeList="LIST_COUNTRY"></t:writeCodeValue>
													</logic:notEmpty>
												</logic:equal>
										</span> <!-- loading country id --> <span id="spn_ta_country_id"
											class="hidden"><bean:write name="_m_cstForm"
													property="ta_country_id" /></span> <span
											class='<%=classNewMode%>'> <html:select
													name="_m_cstForm" property="ta_country_id"
													style="width:64%; float:right;">
													<html:option value="">
														<bean:message key="screen.m_cst.label_blank" />
													</html:option>
													<html:options collection="LIST_COUNTRY" property="id"
														labelProperty="name" />
												</html:select>
										</span></td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</td>
			</tr>
		</table>
		<div style="background-color: #F8F8F8;">
			<br>
			<!-- JSP Tab control -->
			<input type="hidden" id="lbl_primary_contact"
				value='<bean:message key="screen.m_cst.label_billing_contact"/>'>
			<ul id="countrytabs" class="shadetabs">
				<li><a href="#" rel="idTab01"><bean:message
							key="screen.m_cst.label_billing_contact1" /></a></li>
				<li><a href="#" rel="idTab02"><bean:message
							key="screen.m_cst.label_billing_contact2" /></a></li>
				<li><a href="#" rel="idTab03"><bean:message
							key="screen.m_cst.label_billing_contact3" /></a></li>
				<li><a href="#" rel="idTab04"><bean:message
							key="screen.m_cst.label_billing_contact4" /></a></li>
				<li><a href="#" rel="idTab05"><bean:message
							key="screen.m_cst.label_primary_contact" /></a></li>
				<li><a href="#" rel="idTab06"><bean:message
							key="screen.m_cst.label_technical_contact" /></a></li>
				<li><a href="#" rel="idTab07"><bean:message
							key="screen.m_cst.label_other_contact" /></a></li>
			</ul>
			<div
				style="border: 1px solid gray; width: 100%; margin-bottom: 1em; padding: 10px">
				<div id="idTab01" class="tabcontent">
					<!-- billing contact -->
					<table class="information" cellpadding="0" cellspacing="1"
						width="100%" style="table-layout: fixed;">
						<tr>
							<td width="20%"><bean:message
									key="screen.m_cst.label_contact_name" /> <span
								class="<%=classNewMode%>" style="color: red"><bean:message
										key="screen.m_cst.label_mandatory" /></span></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td width="28%"><span id="spn_bc_contact_name"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="bc_contact_name"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="bc_contact_name" size="25"
										maxlength="50"></html:text></span></td>
							<td width="20%"><bean:message
									key="screen.m_cst.label_did_no" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td width="28%"><span id="spn_bc_did_no"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="bc_did_no"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="bc_did_no" size="25"
										maxlength="20"></html:text></span></td>
						</tr>
						<tr>
							<td><bean:message key="screen.m_cst.label_designation" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td><span id="spn_bc_designation"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="bc_designation"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="bc_designation" size="25"
										maxlength="50"></html:text></span></td>
							<td><bean:message key="screen.m_cst.label_fax_no" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td><span id="spn_bc_fax_no"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="bc_fax_no"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="bc_fax_no" size="25"
										maxlength="20"></html:text></span></td>
						</tr>
						<tr>
							<td><bean:message key="screen.m_cst.label_telephone_no" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td><span id="spn_bc_tel_no"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="bc_tel_no"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="bc_tel_no" size="25"
										maxlength="20"></html:text></span></td>
							<td><bean:message key="screen.m_cst.label_mobile_no" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td><span id="spn_bc_mobile_no"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="bc_mobile_no"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="bc_mobile_no" size="25"
										maxlength="20"></html:text></span></td>
						</tr>
						<!-- Email(To) -->
						<tr>
							<td><bean:message key="screen.m_cst.label_email_to" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td colspan="4" style="word-break: break-all;"><span
								id="spn_bc_email"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="bc_email"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:textarea
										name="_m_cstForm" property="bc_email"
										style="width:78.5%;overflow-y:visible;"></html:textarea></span></td>
						</tr>
						<!-- Email(CC) -->
						<tr>
							<td><bean:message key="screen.m_cst.label_email_cc" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td colspan="4" style="word-break: break-all;"><span
								id="spn_bc_email_cc"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="bc_email_cc"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:textarea
										name="_m_cstForm" property="bc_email_cc"
										style="width:78.5%;overflow-y:visible;"></html:textarea></span></td>
						</tr>
					</table>
				</div>
				<div id="idTab02" class="tabcontent">
					<table class="information" cellpadding="0" cellspacing="1"
						width="100%" style="table-layout: fixed;">
						<tr>
							<td width="20%"><bean:message
									key="screen.m_cst.label_contact_name" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td width="28%"><span id="spn_bc2_contact_name"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="bc2_contact_name"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="bc2_contact_name" size="25"
										maxlength="50"></html:text></span></td>
							<td width="20%"><bean:message
									key="screen.m_cst.label_did_no" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td width="28%"><span id="spn_bc2_did_no"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="bc2_did_no"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="bc2_did_no" size="25"
										maxlength="20"></html:text></span></td>
						</tr>
						<tr>
							<td><bean:message key="screen.m_cst.label_designation" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td><span id="spn_bc2_designation"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="bc2_designation"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="bc2_designation" size="25"
										maxlength="50"></html:text></span></td>
							<td><bean:message key="screen.m_cst.label_fax_no" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td><span id="spn_bc2_fax_no"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="bc2_fax_no"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="bc2_fax_no" size="25"
										maxlength="20"></html:text></span></td>
						</tr>
						<tr>
							<td><bean:message key="screen.m_cst.label_telephone_no" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td><span id="spn_bc2_tel_no"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="bc2_tel_no"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="bc2_tel_no" size="25"
										maxlength="20"></html:text></span></td>
							<td><bean:message key="screen.m_cst.label_mobile_no" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td><span id="spn_bc2_mobile_no"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="bc2_mobile_no"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="bc2_mobile_no" size="25"
										maxlength="20"></html:text></span></td>
						</tr>
						<!-- Email(To) -->
						<tr>
							<td><bean:message key="screen.m_cst.label_email_to" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td colspan="4" style="word-break: break-all;"><span
								id="spn_bc2_email_to"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="bc2_email_to"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:textarea
										name="_m_cstForm" property="bc2_email_to"
										style="width:78.5%;overflow-y:visible;"></html:textarea></span></td>
						</tr>
						<!-- Email(CC) -->
						<tr>
							<td><bean:message key="screen.m_cst.label_email_cc" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td colspan="4" style="word-break: break-all;"><span
								id="spn_bc2_email_cc"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="bc2_email_cc"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:textarea
										name="_m_cstForm" property="bc2_email_cc"
										style="width:78.5%;overflow-y:visible;"></html:textarea></span></td>
						</tr>
					</table>
				</div>
				<div id="idTab03" class="tabcontent">
					<table class="information" cellpadding="0" cellspacing="1"
						width="100%" style="table-layout: fixed;">
						<tr>
							<td width="20%"><bean:message
									key="screen.m_cst.label_contact_name" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td width="28%"><span id="spn_bc3_contact_name"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="bc3_contact_name"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="bc3_contact_name" size="25"
										maxlength="50"></html:text></span></td>
							<td width="20%"><bean:message
									key="screen.m_cst.label_did_no" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td width="28%"><span id="spn_bc3_did_no"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="bc3_did_no"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="bc3_did_no" size="25"
										maxlength="20"></html:text></span></td>
						</tr>
						<tr>
							<td><bean:message key="screen.m_cst.label_designation" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td><span id="spn_bc3_designation"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="bc3_designation"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="bc3_designation" size="25"
										maxlength="50"></html:text></span></td>
							<td><bean:message key="screen.m_cst.label_fax_no" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td><span id="spn_bc3_fax_no"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="bc3_fax_no"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="bc3_fax_no" size="25"
										maxlength="20"></html:text></span></td>
						</tr>
						<tr>
							<td><bean:message key="screen.m_cst.label_telephone_no" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td><span id="spn_bc3_tel_no"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="bc3_tel_no"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="bc3_tel_no" size="25"
										maxlength="20"></html:text></span></td>
							<td><bean:message key="screen.m_cst.label_mobile_no" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td><span id="spn_bc3_mobile_no"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="bc3_mobile_no"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="bc3_mobile_no" size="25"
										maxlength="20"></html:text></span></td>
						</tr>
						<!-- Email(To) -->
						<tr>
							<td><bean:message key="screen.m_cst.label_email_to" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td colspan="4" style="word-break: break-all;"><span
								id="spn_bc3_email_to"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="bc3_email_to"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:textarea
										name="_m_cstForm" property="bc3_email_to"
										style="width:78.5%;overflow-y:visible;"></html:textarea></span></td>
						</tr>
						<!-- Email(CC) -->
						<tr>
							<td><bean:message key="screen.m_cst.label_email_cc" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td colspan="4" style="word-break: break-all;"><span
								id="spn_bc3_email_cc"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="bc3_email_cc"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:textarea
										name="_m_cstForm" property="bc3_email_cc"
										style="width:78.5%;overflow-y:visible;"></html:textarea></span></td>
						</tr>
					</table>
				</div>
				<div id="idTab04" class="tabcontent">
					<table class="information" cellpadding="0" cellspacing="1"
						width="100%" style="table-layout: fixed;">
						<tr>
							<td width="20%"><bean:message
									key="screen.m_cst.label_contact_name" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td width="28%"><span id="spn_bc4_contact_name"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="bc4_contact_name"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="bc4_contact_name" size="25"
										maxlength="50"></html:text></span></td>
							<td width="20%"><bean:message
									key="screen.m_cst.label_did_no" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td width="28%"><span id="spn_bc4_did_no"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="bc4_did_no"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="bc4_did_no" size="25"
										maxlength="20"></html:text></span></td>
						</tr>
						<tr>
							<td><bean:message key="screen.m_cst.label_designation" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td><span id="spn_bc4_designation"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="bc4_designation"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="bc4_designation" size="25"
										maxlength="50"></html:text></span></td>
							<td><bean:message key="screen.m_cst.label_fax_no" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td><span id="spn_bc4_fax_no"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="bc4_fax_no"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="bc4_fax_no" size="25"
										maxlength="20"></html:text></span></td>
						</tr>
						<tr>
							<td><bean:message key="screen.m_cst.label_telephone_no" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td><span id="spn_bc4_tel_no"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="bc4_tel_no"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="bc4_tel_no" size="25"
										maxlength="20"></html:text></span></td>
							<td><bean:message key="screen.m_cst.label_mobile_no" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td><span id="spn_bc4_mobile_no"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="bc4_mobile_no"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="bc4_mobile_no" size="25"
										maxlength="20"></html:text></span></td>
						</tr>
						<!-- Email(To) -->
						<tr>
							<td><bean:message key="screen.m_cst.label_email_to" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td colspan="4" style="word-break: break-all;"><span
								id="spn_bc4_email_to"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="bc4_email_to"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:textarea
										name="_m_cstForm" property="bc4_email_to"
										style="width:78.5%;overflow-y:visible;"></html:textarea></span></td>
						</tr>
						<!-- Email(CC) -->
						<tr>
							<td><bean:message key="screen.m_cst.label_email_cc" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td colspan="4" style="word-break: break-all;"><span
								id="spn_bc4_email_cc"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="bc4_email_cc"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:textarea
										name="_m_cstForm" property="bc4_email_cc"
										style="width:78.5%;overflow-y:visible;"></html:textarea></span></td>
						</tr>
					</table>
				</div>
				<div id="idTab05" class="tabcontent">
					<!-- primary contact -->
					<table class="information" cellpadding="0" cellspacing="1"
						width="100%" style="table-layout: fixed;">
						<tr>
							<td width="20%"><bean:message
									key="screen.m_cst.label_contact_name" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td width="28%"><span id="spn_pc_contact_name"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="pc_contact_name"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="pc_contact_name" size="25"
										maxlength="50"></html:text></span></td>
							<td width="20%"><bean:message
									key="screen.m_cst.label_did_no" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td width="28%"><span id="spn_pc_did_no"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="pc_did_no"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="pc_did_no" size="25"
										maxlength="20"></html:text></span></td>
						</tr>
						<tr>
							<td><bean:message key="screen.m_cst.label_designation" /> <!-- <span class="<%=classNewMode%>" style="color: red"><bean:message key="screen.m_cst.label_mandatory"/></span> -->
							</td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td><span id="spn_pc_designation"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="pc_designation"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="pc_designation" size="25"
										maxlength="50"></html:text></span></td>
							<td><bean:message key="screen.m_cst.label_fax_no" /> <!-- <span class="<%=classNewMode%>" style="color: red"><bean:message key="screen.m_cst.label_mandatory"/></span> -->
							</td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td><span id="spn_pc_fax_no"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="pc_fax_no"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="pc_fax_no" size="25"
										maxlength="20"></html:text></span></td>
						</tr>
						<tr>
							<td><bean:message key="screen.m_cst.label_telephone_no" /> <!-- <span class="<%=classNewMode%>" style="color: red"><bean:message key="screen.m_cst.label_mandatory"/></span> -->
							</td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td><span id="spn_pc_tel_no"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="pc_tel_no"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="pc_tel_no" size="25"
										maxlength="20"></html:text></span></td>
							<td><bean:message key="screen.m_cst.label_mobile_no" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td><span id="spn_pc_mobile_no"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="pc_mobile_no"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="pc_mobile_no" size="25"
										maxlength="20"></html:text></span></td>
						</tr>
						<!-- Email(To) -->
						<tr>
							<td><bean:message key="screen.m_cst.label_email_to" /> <!-- <span class="<%=classNewMode%>" style="color: red"><bean:message key="screen.m_cst.label_mandatory"/></span> -->
							</td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td colspan="4" style="word-break: break-all;"><span
								id="spn_pc_email"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="pc_email"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:textarea
										name="_m_cstForm" property="pc_email"
										style="width:78.5%;overflow-y:visible;"></html:textarea></span></td>
						</tr>
						<!-- Email(CC) -->
						<tr>
							<td><bean:message key="screen.m_cst.label_email_cc" /> <!-- <span class="<%=classNewMode%>" style="color: red"><bean:message key="screen.m_cst.label_mandatory"/></span> -->
							</td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td colspan="4" style="word-break: break-all;"><span
								id="spn_pc_email_cc"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="pc_email_cc"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:textarea
										name="_m_cstForm" property="pc_email_cc"
										style="width:78.5%;overflow-y:visible;"></html:textarea></span></td>
						</tr>
					</table>
				</div>
				<div id="idTab06" class="tabcontent">
					<!-- technical contact -->
					<table class="information" cellpadding="0" cellspacing="1"
						width="100%" style="table-layout: fixed;">
						<tr>
							<td width="20%"><bean:message
									key="screen.m_cst.label_contact_name" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td width="28%"><span id="spn_tc_contact_name"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="tc_contact_name"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="tc_contact_name" size="25"
										maxlength="50"></html:text></span></td>
							<td width="20%"><bean:message
									key="screen.m_cst.label_did_no" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td width="28%"><span id="spn_tc_did_no"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="tc_did_no"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="tc_did_no" size="25"
										maxlength="20"></html:text></span></td>
						</tr>
						<tr>
							<td><bean:message key="screen.m_cst.label_designation" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td><span id="spn_tc_designation"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="tc_designation"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="tc_designation" size="25"
										maxlength="50"></html:text></span></td>
							<td><bean:message key="screen.m_cst.label_fax_no" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td><span id="spn_tc_fax_no"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="tc_fax_no"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="tc_fax_no" size="25"
										maxlength="20"></html:text></span></td>
						</tr>
						<tr>
							<td><bean:message key="screen.m_cst.label_telephone_no" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td><span id="spn_tc_tel_no"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="tc_tel_no"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="tc_tel_no" size="25"
										maxlength="20"></html:text></span></td>
							<td><bean:message key="screen.m_cst.label_mobile_no" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td><span id="spn_tc_mobile_no"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="tc_mobile_no"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="tc_mobile_no" size="25"
										maxlength="20"></html:text></span></td>
						</tr>
						<!-- Email(To) -->
						<tr>
							<td><bean:message key="screen.m_cst.label_email_to" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td colspan="4" style="word-break: break-all;"><span
								id="spn_tc_email"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="tc_email"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:textarea
										name="_m_cstForm" property="tc_email"
										style="width:78.5%;overflow-y:visible;"></html:textarea></span></td>
						</tr>
						<!-- Email(CC) -->
						<tr>
							<td><bean:message key="screen.m_cst.label_email_cc" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td colspan="4" style="word-break: break-all;"><span
								id="spn_tc_email_cc"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="tc_email_cc"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:textarea
										name="_m_cstForm" property="tc_email_cc"
										style="width:78.5%;overflow-y:visible;"></html:textarea></span></td>
						</tr>
					</table>
				</div>
				<div id="idTab07" class="tabcontent">
					<!-- other contact -->
					<table class="information" cellpadding="0" cellspacing="1"
						width="100%" style="table-layout: fixed;">
						<tr>
							<td width="20%"><bean:message
									key="screen.m_cst.label_contact_name" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td width="28%"><span id="spn_oc_contact_name"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="oc_contact_name"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="oc_contact_name" size="25"
										maxlength="50"></html:text></span></td>
							<td width="20%"><bean:message
									key="screen.m_cst.label_did_no" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td width="28%"><span id="spn_oc_did_no"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="oc_did_no"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="oc_did_no" size="25"
										maxlength="20"></html:text></span></td>
						</tr>
						<tr>
							<td><bean:message key="screen.m_cst.label_designation" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td><span id="spn_oc_designation"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="oc_designation"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="oc_designation" size="25"
										maxlength="50"></html:text></span></td>
							<td><bean:message key="screen.m_cst.label_fax_no" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td><span id="spn_oc_fax_no"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="oc_fax_no"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="oc_fax_no" size="25"
										maxlength="20"></html:text></span></td>
						</tr>
						<tr>
							<td><bean:message key="screen.m_cst.label_telephone_no" />
							</td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td><span id="spn_oc_tel_no"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="oc_tel_no"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="oc_tel_no" size="25"
										maxlength="20"></html:text></span></td>
							<td><bean:message key="screen.m_cst.label_mobile_no" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td><span id="spn_oc_mobile_no"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="oc_mobile_no"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:text
										name="_m_cstForm" property="oc_mobile_no" size="25"
										maxlength="20"></html:text></span></td>
						</tr>
						<!-- Email(To) -->
						<tr>
							<td><bean:message key="screen.m_cst.label_email_to" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td colspan="4" style="word-break: break-all;"><span
								id="spn_oc_email"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="oc_email"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:textarea
										name="_m_cstForm" property="oc_email"
										style="width:78.5%;overflow-y:visible;"></html:textarea></span></td>
						</tr>
						<!-- Email(CC) -->
						<tr>
							<td><bean:message key="screen.m_cst.label_email_cc" /></td>
							<td width="2%"><bean:message key="screen.m_cst.label_colon" /></td>
							<td colspan="4" style="word-break: break-all;"><span
								id="spn_oc_email_cc"
								class='<bean:write name="_m_cstForm" property="classViewMode"/>'><bean:write
										name="_m_cstForm" property="oc_email_cc"></bean:write></span> <span
								class='<bean:write name="_m_cstForm" property="classNewMode"/>'><html:textarea
										name="_m_cstForm" property="oc_email_cc"
										style="width:78.5%;overflow-y:visible;"></html:textarea></span></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<hr class="lineBottom" size="3">
		<table class="buttonGroup" cellpadding="0" cellspacing="0">
			<tr>

				<logic:equal name="_m_cstForm" property="mode" value="READONLY">
					<logic:equal value="2" name="_m_cstForm" property="accessType">

						<button onclick="submitForm('edit');">
							<bean:message key="screen.m_cst.button_edit" />
						</button>&nbsp;
								<button
							onclick="submitForm('delete','<bean:message key="info.ERR4SC002"/>');">
							<bean:message key="screen.m_cst.button_delete" />
						</button>&nbsp;
							
					</logic:equal>
					<!--  	
					<logic:notEqual value="2" name="_m_cstForm" property="accessType">
						<button onclick="submitForm('edit');" disabled="disabled"><bean:message key="screen.m_cst.button_edit"/></button>&nbsp;
						<button onclick="submitForm('delete','<bean:message key="info.ERR4SC002"/>');" disabled="disabled"><bean:message key="screen.m_cst.button_delete"/></button>&nbsp;					
					</logic:notEqual>
					-->
				</logic:equal>

				<logic:equal name="_m_cstForm" property="mode" value="NEWMODE">
					<button
						onclick='submitForm("save","<bean:write name='_m_cstForm' property='listUser'/>");'>
						<bean:message key="screen.m_cst.button_save" />
					</button>&nbsp;
				</logic:equal>
				<logic:empty name="_m_cstForm" property="mode">
					<button
						onclick='submitForm("save","<bean:write name='_m_cstForm' property='listUser'/>");'>
						<bean:message key="screen.m_cst.button_save" />
					</button>&nbsp;
				</logic:empty>
				<logic:equal name="_m_cstForm" property="mode" value="EDITMODE">
					<button
						onclick='submitForm("save","<bean:write name='_m_cstForm' property='listUser'/>");'>
						<bean:message key="screen.m_cst.button_save" />
					</button>&nbsp;
					<!-- <script>					
					var obj1 = actb(document.getElementById('secondary'),customarray,'<bean:write name="_m_cstForm" property="listUser"/>');					
					</script> -->
					<!--  	<button onclick="submitForm('delete','<bean:message key="info.ERR4SC002"/>');"><bean:message key="screen.m_cst.button_delete"/></button>&nbsp;  -->
				</logic:equal>
				<logic:notEqual name="_m_cstForm" property="mode" value="EDITMODE">
					<button
						onclick="submitForm('exit','<bean:message key="info.ERR4SC001"/>', '<%=request.getContextPath()%>');">
						<bean:message key="screen.m_cst.button_exit" />
					</button>
				</logic:notEqual>
				<logic:equal name="_m_cstForm" property="mode" value="EDITMODE">
					<button
						onclick="doEditExit('<bean:message key="info.ERR4SC001"/>', '<%=request.getContextPath()%>');">
						<bean:message key="screen.m_cst.button_exit" />
					</button>
				</logic:equal>
			</tr>
		</table>
	</ts:form>
	<div class="error">
		<html:messages id="message">
			<bean:write name="message" />
			<br />
		</html:messages>
	</div>
	<div class="message">
		<ts:messages id="message" message="true">
			<bean:write name="message" />
		</ts:messages>
	</div>
	<div id="message_group" style="display: none;">
		<div class="messagePrimary">
			<bean:message key="info.ERR4SC019"
				arg0="Waring&lt;br&gt;Account Manager_lt;Primary_gt;:invalid user name.&lt;br&gt;Do you want to proceed save?&lt;br&gt;Click Yes to save,No to abort save." />
		</div>
		<div class="messageSecondary">
			<bean:message key="info.ERR4SC019"
				arg0="Waring&lt;br&gt;Account Manager_lt;Secondary_gt;:invalid user name.&lt;br&gt;Do you want to proceed save?&lt;br&gt;Click Yes to save,No to abort save." />
		</div>
	</div>
</body>
</html:html>

