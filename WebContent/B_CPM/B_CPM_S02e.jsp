<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<%@page import="nttdm.bsys.common.fw.BillingSystemUserValueObject"%>
<%@page import="nttdm.bsys.common.util.CommonUtils"%> 
<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
   		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
   		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/stylesheet/tabcontent.css"/>
   		
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>	
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/tabcontent.js"></script>
		<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script> --%>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>	
   		<script type="text/javascript" src="js/b_cpm_common.js"></script>
   		<script type="text/javascript" src="js/b_cpm_en_s02.js"></script>
   		<link rel="stylesheet" type="text/css" href="css/b_cpm_s02_common.css"/>
   		<link rel="stylesheet" type="text/css" href="css/b_cpm.css"/>
   		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/jquery-ui.css"/>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.7.js"></script>
   		<script type="text/javascript"src="<%=request.getContextPath()%>/javascript/jquery-ui.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/autoCompleteCbo.js"></script>
		<title></title>
		<style>
		.ui-autocomplete-maxheight {
   			height: 200px;
   		}
		.ui-autocomplete {
    		overflow-y: auto;
    		overflow-x: hidden;
    		font-family: Calibri;
    		font-size: 1.1em;
  		}
  		.custom-combobox {
   			 position: relative;
    		display: inline-block;
    		/* height:80px; */
  		}
  		.custom-combobox-toggle {
    		position: absolute;
    		top: 0;
    		bottom: 0;
    		margin-left: -1px;
    		padding: 0;
  		}
  		.custom-combobox-input {
    		margin: 0;
    		padding-left: 0.2em;
  		}
	</style>
	</head>
	<body onload="javascript: initLoad()" class="main">
	<!-- check access right START -->
	<%
		BillingSystemUserValueObject uvo = (BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT");
	 	String accessRight = CommonUtils.getAccessRight(uvo, "B-CPM");
	 	String accessRightB_SSMBean = CommonUtils.getAccessRight(uvo, "B-SSM");
	%>
	 <bean:define id="accessRightBean" value="<%=accessRight %>"/>
	 <bean:define id="accessRightB_SSM" value="<%= accessRightB_SSMBean%>"/>
	<logic:equal value="0" name="accessRightBean">
	<script type="text/javascript">
	window.location = '<%=request.getContextPath()%>/C_CMN001/C_CMN001S06SCR.do';
	</script>
	
	</logic:equal>
	<!-- check access right END -->
		<ts:form styleId="frmS02" action="/B_CPM_S02EditDSP" method="POST">
			<t:defineCodeList id="LIST_CUSTOMERTYPE"/>
			<t:defineCodeList id="LIST_PLANSTATUS"/>
			<t:defineCodeList id="LIST_PAYMENT_METHOD"/>
			<t:defineCodeList id="BILL_INSTRUCTION_LIST"/>
			<t:defineCodeList id="LIST_TRANSACTIONTYPE"/>
			<t:defineCodeList id="LIST_CURRENCY"/>
			<t:defineCodeList id="LIST_RATETYPE"/>
			<t:defineCodeList id="LIST_RATETYPE2"/>
			<t:defineCodeList id="MODE_LIST"/>
			<t:defineCodeList id="LIST_GST"/>
			<t:defineCodeList id="LIST_GST_VALUE"/>
			<t:defineCodeList id="LIST_BILLINGSTATUS"/>
			<t:defineCodeList id="LIST_MASTER_LOCATION"/>
			<t:defineCodeList id="LIST_TAX_TYPE"/>
			<bean:define id="LOAD_OBJECT" name="LOAD_OBJECT" scope="request"></bean:define>
			<bean:define id="CUSTOMER_PLAN" name="_B_CPM_S02Form" property="customerPlan"></bean:define>
			<input type="hidden" id="hiddenContextPath" value="${pageContext.request.contextPath}"/>
			<input type="hidden" id="hiddenIsDisplayExportSingPost" value="${displayItems[14]}"/>
			<input type="hidden" id="hiddenDefaultMsgForListBox" value="<bean:message key="screen.b_cpm.listBox.default"/>"/>
			<input type="hidden" id="hiddenListBillingAccNoLength" value="${fn:length(listBillingAccNo)}"/>
 			<input type="hidden" id="hiddenMsgExitConfirmation" value="<bean:message key="info.ERR4SC001"/>"/>
 			<input type="hidden" id="hiddenMsgDeleteSubPlan" value="<bean:message key="subPlan.info.ERR4SC003"/>"/>
 			<input type="hidden" id="hiddenMsgDeleteOptionService" value="<bean:message key="optionService.info.ERR4SC003"/>"/>
 			<input type="hidden" id="hiddenMsgChangeServiceGroup" value='<bean:message key="info.ERR4SC004" arg0="replace"/>'/>
 			<input type="hidden" name="idCustomer" value="${customerPlan.idCust}"/><%-- used in case, input can't pass validator so the destination is initialize page => use it to get customer info  --%>
 			<input type="hidden" name="pageEvent" value="postback"/>
 			<html:hidden property="action" styleId="action" value="${action}"/>
 			
 			<nested:nest property="customerPlan">
 			<nested:hidden property="idCust" styleId="idCustomer"></nested:hidden>
 			<nested:hidden property="idCustPlan" styleId="idCustPlan"></nested:hidden>
 			<nested:hidden property="idSubInfo" styleId="idSubInfo"></nested:hidden>
 			<nested:hidden property="planType" styleId="planType"></nested:hidden>
 			
 			<nested:hidden property="idPlanParam" styleId="idPlanParam"></nested:hidden>
 			<nested:hidden property="planNameParam" styleId="planNameParam"></nested:hidden>
 			<nested:hidden property="multiPln" styleId="multiPln"></nested:hidden>
 			<nested:hidden property="transactionTypeFlg" styleId="transactionTypeFlg"></nested:hidden>
 			<nested:hidden property="addStdPln" styleId="addStdPln"></nested:hidden>
 			<nested:hidden property="addNonStdPln" styleId="addNonStdPln"></nested:hidden>
 			<nested:hidden property="addStdPlnMul" styleId="addStdPlnMul"></nested:hidden>
 			<nested:hidden property="clickSaveFlg" styleId="clickSaveFlg"></nested:hidden>
 			<nested:hidden property="m_jnmDisplayFlg" styleId="m_jnmDisplayFlg"/>
 			<nested:hidden property="masterLocationDisplayFlg" styleId="masterLocationDisplayFlg"/>
 			<nested:hidden property="custPlanMPlanCurDifFlg" styleId="custPlanMPlanCurDifFlg"/>
 			<nested:hidden property="clickDifCurrencyYesFlg" styleId="clickDifCurrencyYesFlg"/>
 			<nested:hidden property="addRadiusFlg" styleId="addRadiusFlg"/>
 			<nested:hidden property="clickAddRadiusYesFlg" styleId="clickAddRadiusYesFlg"/>
 			<nested:hidden property="removeRadiusFlg" styleId="removeRadiusFlg"/>
 			<nested:hidden property="clickRemoveRadiusYesFlg" styleId="clickRemoveRadiusYesFlg"/>
 			<nested:hidden property="b_ssmIsUsed" styleId="b_ssmIsUsed"/>
 			<nested:hidden property="fixedForexFlg" styleId="fixedForexFlg"/>
 			<nested:hidden property="newAccCheckFlg" styleId="newAccCheckFlg"/>
 			<nested:hidden property="disBillingOption" styleId="disBillingOption"/>
 			<nested:hidden property="taxTypeDisplay" styleId="taxTypeDisplay"/>
 			<nested:hidden property="taxTypeDefault" styleId="taxTypeDefault"/>
 			<table width="100%">
 			<tr>
 				<td>
		 			<table class="subHeader" cellpadding="0" cellspacing="0">
		 				<tr>
			    			<td><bean:message key="screen.b_cpm.header"/></td>
			    		</tr>
			    	</table>
			    	<bean:define id="CUSTOMER_INFO" name="LOAD_OBJECT" property="CUSTOMER_INFO"></bean:define>
			    	<input type="hidden" name="defCurrency" id="defCurrency" value="<bean:write name="CUSTOMER_INFO" property="defCurrency"/>"/>
			    	<table class="customerInformation">
						<tr>
							<td class="header">
								 <bean:message key="screen.b_cpm.label.custName"/>
							</td>
							<td>
								<bean:message key="screen.b_cpm.label_colon"/>
								<bean:write name="CUSTOMER_INFO" property="custName"/>
							</td>
							<td>
								<bean:message key="screen.b_cpm.label.filePath"/><bean:message key="screen.b_cpm.label_colon"/><a href="<bean:write property="filePath" name="CUSTOMER_INFO"/>" target="_blank"><bean:write property="filePath" name="CUSTOMER_INFO"/></a>
							</td>
						</tr>
						<tr>
							<td class="header">
								 <bean:message key="screen.b_cpm.label.custId"/>
							</td>
							<td>
								<bean:message key="screen.b_cpm.label_colon"/>
								<nested:write property="idCust"/>
							</td>
						</tr>	
						<tr>
							<td class="header">
								 <bean:message key="screen.b_cpm.label.custType"/>
							</td>
							<td>
								<bean:message key="screen.b_cpm.label_colon"/>
								<logic:iterate id="customerType" name="LIST_CUSTOMERTYPE">	
									<logic:equal name="CUSTOMER_INFO" property="custType" value="${customerType.id}">
										<bean:write name="customerType" property="name"/>
										<input type ="hidden" id = "customerType" value="<bean:write name="customerType" property="id"/>" />
									</logic:equal>
								</logic:iterate>
							</td>
						</tr>
					</table>
					<!-- JSP Tab control -->	
				    <br/>
					<bean:define id="parentTab" name="_B_CPM_S02Form" property="tabController"/>
		 				<ul id="planTabs" class="shadetabs">
		 				<logic:iterate name="parentTab" property="tabs" id="tab" indexId="i">
							<li onclick="javascript: void(0);">
							<a href="javascript: void(0);" name="aTab" rel="<bean:write name="tab" property="id"/>">
								<bean:write name="tab" property="name"/>
							</a>
							</li>
		 				</logic:iterate>
		 				</ul>
					<!-- New tab start -->
					<div id="<bean:write name="parentTab" property="activeTab"/>" class="tabContent">
						<table cellpadding="0" cellspacing="0" width="100%">
							<colgroup>
								<col width="100%"/>
							</colgroup>
							<tr>
								<td>
									<!-- Plan header start -->
									<div class="planHeaderInformation">
										<table class="informationPlan" cellpadding="0" cellspacing="0" width="100%">
										<colgroup>
											<col width="5%"/>
											<col width="35%"/>
											<col width="18%"/>
											<col width="19%"/>
											<col width="23%"/>
										</colgroup>
										<tr>
											<td class="header" nowrap="nowrap"><bean:message key="screen.b_cpm.label.transactionType"/></td>
											<td nowrap="nowrap">
												<bean:message key="screen.b_cpm.label_colon"/>
												<nested:equal value="0" property="transactionTypeFlg">
												    <span style="color:gray;">
												</nested:equal>
												<nested:notEqual value="0" property="transactionTypeFlg">
												    <span>
												</nested:notEqual>
												<logic:iterate id="transactionType" name="LIST_TRANSACTIONTYPE" indexId="index">
											    	<c:if test="${index ne '3'}">
											    	<nested:radio property="transactionType" styleClass="transactionType" value="${transactionType.id}">
											    		<bean:write name="transactionType" property="name"/> 
											    	</nested:radio>
											    	</c:if>
											    	<c:if test="${index eq '3'}">
											    		<c:if test="${LOAD_OBJECT.NonTaxInvoiceShowFlg eq '1'}">
											    			<nested:radio property="transactionType" styleClass="transactionType" value="${transactionType.id}">
											    		    <bean:write name="transactionType" property="name"/> 
											    	        </nested:radio>
											    		</c:if>
											    	</c:if>
											    </logic:iterate>
											    </span>
											    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											 </td>
											 <td class="header" style="color:gray;"><bean:message key="screen.b_cpm.label.customerPlanId"/></td>
											 <td style="color:gray;">
											 	<bean:message key="screen.b_cpm.label_colon"/>
											 	<nested:empty property="idCustPlan">
											    (<bean:message key="screen.b_cpm.label.subcriptionId.default"/>)
											    </nested:empty>
											    <nested:notEmpty>
											    <nested:write property="idCustPlan"/>
											    </nested:notEmpty>
											 </td>
											 <td rowspan="4" valign="top">
											 	<nested:hidden property="planStatus" styleClass="planStatus"/>
											 	<nested:define property="planStatus" id="planStatus"/>
												<div class="<t:writeCodeValue codeList="COLOR_CODE" name="planStatus"></t:writeCodeValue>" style="padding:5px 6px 5px 0px;float:right;width:100%;margin:0 0 0 0">
													<h4><bean:message key="screen.b_cpm.label.status"/></h4>
													<h4>
														<t:writeCodeValue codeList="LIST_PLANSTATUS" name="planStatus"/>
													</h4>
												</div>
											 </td>
										</tr>
										<tr>
											<td nowrap="nowrap" style="width:150px;"><bean:message key="screen.b_cpm.label.billingInstruction"/><span style="color:red"><bean:message key="screen.b_cpm.label.star"/></span></td>
											<td>
												<bean:message key="screen.b_cpm.label_colon"/>
									    		<nested:select styleId="biSelect" property="billingInstruction" styleClass="billingInstruction" style="width:260px;" onchange="displayPostpaid(this)">
									    			<html:option value=""><bean:message key="screen.b_cpm.listBox.default"/></html:option>
									    			<html:optionsCollection name="BILL_INSTRUCTION_LIST" value="id" label="name"/>
									    		</nested:select>
											</td>
											
											<td class="header"><bean:message key="screen.b_cpm.label.applicationDate"/></td>
											<td>	
												<bean:message key="screen.b_cpm.label_colon"/><nested:write property="applicationDate"/>
											</td>
										</tr>
										<tr>
											<td nowrap="nowrap" bgcolor="#FFCCFF"><bean:message key="screen.b_cpm.label.billingAccNo"/><span style="color:red"><bean:message key="screen.b_cpm.label.star"/></span></td>
											<logic:equal name="LOAD_OBJECT" property="CHECK_BILLING_ACCOUNT" value="0">
												<td nowrap="nowrap" bgcolor="#FFCCFF">	
											</logic:equal>
											<logic:notEqual name="LOAD_OBJECT" property="CHECK_BILLING_ACCOUNT" value="0">
												<td style="padding-top: 0px; padding-left: 0px; padding-bottom: 0px;">
												<span style="background-color:#FFCCFF; margin: 0px; width: 280px; padding-top: 2px; padding-bottom: 2px; padding-left: 4px;" >
											</logic:notEqual>
												<bean:message key="screen.b_cpm.label_colon"/>
												<logic:equal name="LOAD_OBJECT" property="CHECK_BILLING_ACCOUNT" value="0">
												<nested:select styleId="plan_billAccNo" property="billAccNo" onchange="javascript: changeBillingAccount();" style="width:260px;">
													 <logic:iterate id="item" name="LOAD_OBJECT" property="BILLING_ACCOUNT">
													 <option value="<bean:write name="item" property="ID_BILL_ACCOUNT"/>"
													 		expCur="<bean:write name="item" property="EXPORT_CURRENCY"/>"
													 		billCur="<bean:write name="item" property="BILL_CURRENCY"/>"
													 		payment="<bean:write name="item" property="PAYMENT_METHOD"/>"
													 		fixed="<bean:write name="item" property="FIXED_FOREX"/>"
													 		bacDCount="<bean:write name="item" property="COUNT_BAC_D"/>"
													 		taxType="<bean:write name="item" property="TAX_TYPE"/>">
													 		<bean:write name="item" property="ID_BILL_ACCOUNT"/></option>
													 </logic:iterate>
												</nested:select>
												<input type="hidden" id="originalBillAccNo" class="originalBillAccNo" value="<nested:write property="billAccNo"/>"/>
												</logic:equal>
												<logic:notEqual name="LOAD_OBJECT" property="CHECK_BILLING_ACCOUNT" value="0">
												<nested:hidden styleId="plan_billAccNo" property="billAccNo"/>
												<nested:equal value="" property="billAccNo">
												<bean:message key="screen.b_cpm.label.empty"/>
												<nested:hidden property="billAccNo"/>
												</nested:equal>
												<nested:notEqual value="" property="billAccNo">
												<nested:write property="billAccNo"/>
												<nested:hidden property="billAccNo"/>
												</nested:notEqual>
												<nested:hidden styleId="plan_newAcc" property="newAcc"/>
												</logic:notEqual>
												<logic:equal name="LOAD_OBJECT" property="CHECK_BILLING_ACCOUNT" value="0">
													<nested:checkbox styleId="plan_newAcc" property="newAcc" value="NEW" onclick="javascript: checkNewAccount(this);"><bean:message key="screen.b_cpm.label.newAcc"/></nested:checkbox>
												</logic:equal>
												<logic:notEqual name="LOAD_OBJECT" property="CHECK_BILLING_ACCOUNT" value="0">
													</span>
												</logic:notEqual>	
											</td>
											<td nowrap="nowrap">
											</td>
										</tr>
										<tr>
												<td bgcolor="#FFCCFF"><bean:message key="screen.b_cpm.label.billingCurency"/><span style="color:red"><bean:message key="screen.b_cpm.label.star"/></span></td>
												<logic:equal name="LOAD_OBJECT" property="CHECK_BILLING_ACCOUNT" value="0">
													<td bgcolor="#FFCCFF">	
												</logic:equal>
												<logic:notEqual name="LOAD_OBJECT" property="CHECK_BILLING_ACCOUNT" value="0">
													<td style="padding-top: 0px; padding-left: 0px; padding-bottom: 0px;">
												<span style="background-color:#FFCCFF; margin: 0px; width: 280px; padding-top: 2px; padding-bottom: 2px; padding-left: 4px;" >
												</logic:notEqual>
									    		<bean:message key="screen.b_cpm.label_colon"/>
								    			<nested:select  styleId="plan_billingCurrency"  property="billCurrency" style="width:260px" onchange="javascript: displayOptionGrandTotal('1');">
								    				<html:option value=""><bean:message key="screen.b_cpm.listBox.default"/> </html:option>
													<html:optionsCollection name="LIST_CURRENCY" value="id" label="name"/>
								    			</nested:select>
									    		<logic:notEqual name="LOAD_OBJECT" property="CHECK_BILLING_ACCOUNT" value="0">
													</span>
												</logic:notEqual>
									    	</td>
										</tr>
										<tr>
												<td bgcolor="#FFCCFF"><bean:message key="screen.b_cpm.label.paymentMethod"/><span style="color:red"><bean:message key="screen.b_cpm.label.star"/></span></td>
												<logic:equal name="LOAD_OBJECT" property="CHECK_BILLING_ACCOUNT" value="0">
													<td bgcolor="#FFCCFF">	
												</logic:equal>
												<logic:notEqual name="LOAD_OBJECT" property="CHECK_BILLING_ACCOUNT" value="0">
													<td style="padding-top: 0px; padding-left: 0px; padding-bottom: 0px;">
												<span style="background-color:#FFCCFF; margin: 0px; width: 280px; padding-top: 2px; padding-bottom: 2px; padding-left: 4px;" >
												</logic:notEqual>
												<bean:message key="screen.b_cpm.label_colon"/>
												<nested:select property="paymentMethod" styleId="paymentMethod" style="width:260px;">
									    			<html:option value=""><bean:message key="screen.b_cpm.listBox.default"/></html:option>
									    			<!-- common && added to fix 2934 -->
									    			<!-- <html:optionsCollection name="LIST_PAYMENT_METHOD" value="id" label="name"/>  -->
									    			<html:optionsCollection label="VALUE" value="RESOURCE_ID" property="PAYMENT_METHOD" name="LOAD_OBJECT"/>
									    		</nested:select>
									    		<logic:notEqual name="LOAD_OBJECT" property="CHECK_BILLING_ACCOUNT" value="0">
													</span>
											 	</logic:notEqual>
											</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td> <!-- wcbeh@20161201 #210 add in PS7 checking for add service button -->
												<logic:notEqual value="PS2" name="CUSTOMER_PLAN" property="planStatus">
													<c:choose>
														<c:when test="${CUSTOMER_PLAN.planStatus eq 'PS3' or CUSTOMER_PLAN.planStatus eq 'PS7'}">
															<c:if test="${'1'eq LOAD_OBJECT.EditActivePlanAMTFlg}">
																<nested:notEmpty property="addStdPln">
																	<logic:equal name="CUSTOMER_PLAN" property="planType" value="SP">
																		<button id="btnAddService" onclick="javascript: popM_MPP04AndAddService();" style="width:100%"><bean:message key="screen.b_cpm.button.addServices"/></button>
																	</logic:equal>
																</nested:notEmpty>
																<logic:equal name="CUSTOMER_PLAN" property="planType" value="NP">
																	<button id="btnAddService" onclick="javascript: addNonStdService();" style="width:100%"><bean:message key="screen.b_cpm.button.addServices"/></button>
																</logic:equal>
															</c:if>
														</c:when>
														<c:otherwise>
															<nested:notEmpty property="addStdPln">
																<logic:equal name="CUSTOMER_PLAN" property="planType" value="SP">
																	<button id="btnAddService" onclick="javascript: popM_MPP04AndAddService();" style="width:100%"><bean:message key="screen.b_cpm.button.addServices"/></button>
																</logic:equal>
															</nested:notEmpty>
															<logic:equal name="CUSTOMER_PLAN" property="planType" value="NP">
																<button id="btnAddService" onclick="javascript: addNonStdService();" style="width:100%"><bean:message key="screen.b_cpm.button.addServices"/></button>
															</logic:equal>
														</c:otherwise>
													</c:choose>
												</logic:notEqual>
												<!-- 
												<logic:notEqual value="PS2" name="CUSTOMER_PLAN" property="planStatus">													
													<logic:equal value="PS3" name="CUSTOMER_PLAN" property="planStatus">
														<c:if test="${'1'eq LOAD_OBJECT.EditActivePlanAMTFlg}">
															<nested:notEmpty property="addStdPln">
																<logic:equal name="CUSTOMER_PLAN" property="planType" value="SP">
																	<button id="btnAddService" onclick="javascript: popM_MPP04AndAddService();" style="width:100%"><bean:message key="screen.b_cpm.button.addServices"/></button>
																</logic:equal>
															</nested:notEmpty>
															<logic:equal name="CUSTOMER_PLAN" property="planType" value="NP">
																<button id="btnAddService" onclick="javascript: addNonStdService();" style="width:100%"><bean:message key="screen.b_cpm.button.addServices"/></button>
															</logic:equal>
														</c:if>
													</logic:equal>
													<logic:notEqual value="PS3" name="CUSTOMER_PLAN" property="planStatus">
												    	<nested:notEmpty property="addStdPln">
															<logic:equal name="CUSTOMER_PLAN" property="planType" value="SP">
																<button id="btnAddService" onclick="javascript: popM_MPP04AndAddService();" style="width:100%"><bean:message key="screen.b_cpm.button.addServices"/></button>
															</logic:equal>
														</nested:notEmpty>
														<logic:equal name="CUSTOMER_PLAN" property="planType" value="NP">
															<button id="btnAddService" onclick="javascript: addNonStdService();" style="width:100%"><bean:message key="screen.b_cpm.button.addServices"/></button>
														</logic:equal>
													</logic:notEqual>
												</logic:notEqual>
												-->
											</td>
										</tr>
										<tr>
												<td bgcolor="#FFCCFF"><bean:message key="screen.b_cpm.label.paymentCurrency"/></td>
												<logic:equal name="LOAD_OBJECT" property="CHECK_BILLING_ACCOUNT" value="0">
													<td bgcolor="#FFCCFF">	
												</logic:equal>
												<logic:notEqual name="LOAD_OBJECT" property="CHECK_BILLING_ACCOUNT" value="0">
													<td style="padding-top: 0px; padding-left: 0px; padding-bottom: 0px;">
												<span style="background-color:#FFCCFF; margin: 0px; width: 280px; padding-top: 2px; padding-bottom: 2px; padding-left: 4px;" >
												</logic:notEqual>
												<bean:message key="screen.b_cpm.label_colon"/>
												<nested:select property="expGrandTotal" styleId="expGrandTotal" style="width:260px;">
									    			<html:option value=""><bean:message key="screen.b_cpm.listBox.default"/></html:option>
									    			<html:optionsCollection property="GRAND_TOTAL" name="LOAD_OBJECT" value="CUR_CODE" label="CUR_CODE"/>
									    		</nested:select>
									    		<input type="hidden" value='<nested:write property="expGrandTotal"/>' id="hidExpGrandTotal">
									    		<logic:notEqual name="LOAD_OBJECT" property="CHECK_BILLING_ACCOUNT" value="0">
													</span>
											 	</logic:notEqual>
											</td>
											<%-- <td nowrap="nowrap" colspan="2">
												<c:if test="${action eq 'new'}">
											        <span class="disableLink" style="text-decoration:none">
														<bean:message key="screen.b_cpm.label.subcriptionInfo" />
														<bean:message key="screen.b_cpm.label_colon"/>
														<bean:message key="screen.b_cpm.label.auto"/>
													</span>
											    </c:if>
											    <c:if test="${action ne 'new'}">
												    <nested:equal property="b_ssmIsUsed" value="1">
												        <c:choose>
															<c:when test="${'1' eq accessRightB_SSM or '2' eq accessRightB_SSM}">
																<a href="javascript: viewSubscriptionInfo();">
																	<bean:message key="screen.b_cpm.label.subcriptionInfo" />
																	<bean:message key="screen.b_cpm.label_colon"/>
																	<nested:write property="idSubInfo"/>
																</a>
															</c:when>
															<c:otherwise>
																<bean:message key="screen.b_cpm.label.subcriptionInfo" />
																<bean:message key="screen.b_cpm.label_colon"/>
																<nested:write property="idSubInfo"/>
															</c:otherwise>
														</c:choose>
													</nested:equal>
													<nested:notEqual property="b_ssmIsUsed" value="1">
													    <bean:message key="screen.b_cpm.label.subcriptionInfo" />
														<bean:message key="screen.b_cpm.label_colon"/>
														<nested:write property="idSubInfo"/>
													</nested:notEqual>
												</c:if>
											</td>
											<td>
												<nested:notEmpty property="addStdPlnMul">
													
													<logic:notEqual value="PS3" name="CUSTOMER_PLAN" property="planStatus">
													</logic:notEqual>
													
													<logic:notEqual value="PS2" name="CUSTOMER_PLAN" property="planStatus">
														<logic:equal value="PS3" name="CUSTOMER_PLAN" property="planStatus">
															<c:if test="${'1'eq LOAD_OBJECT.EditActivePlanAMTFlg}">
																<button id="btnAddServiceMul" onclick="javascript: addServiceMul();" style="width:100%"><bean:message key="screen.b_cpm.button.addServicesMul"/></button>
															</c:if>
														</logic:equal>
														<!-- wcbeh@2016xxxx fixing in progress -->
														<!--<logic:equal value="PS7" name="CUSTOMER_PLAN" property="planStatus">
															<c:if test="${'1'eq LOAD_OBJECT.EditActivePlanAMTFlg}">
																<button id="btnAddServiceMul" onclick="javascript: addServiceMul();" style="width:100%"><bean:message key="screen.b_cpm.button.addServicesMul"/></button>
															</c:if>
														</logic:equal>-->
														<logic:notEqual value="PS3" name="CUSTOMER_PLAN" property="planStatus">
															<button id="btnAddServiceMul" onclick="javascript: addServiceMul();" style="width:100%"><bean:message key="screen.b_cpm.button.addServicesMul"/></button>
														</logic:notEqual>
													</logic:notEqual>
												</nested:notEmpty>
											</td>
										</tr> --%>
										<nested:equal value="1" property="fixedForexFlg">
										<tr>
												<td bgcolor="#FFCCFF"><bean:message key="screen.b_cpm.label.fixedForex"/></td>
												<logic:equal name="LOAD_OBJECT" property="CHECK_BILLING_ACCOUNT" value="0">
													<td bgcolor="#FFCCFF">	
												</logic:equal>
												<logic:notEqual name="LOAD_OBJECT" property="CHECK_BILLING_ACCOUNT" value="0">
													<td style="padding-top: 0px; padding-left: 0px; padding-bottom: 0px;">
												<span style="background-color:#FFCCFF; margin: 0px; width: 280px; padding-top: 2px; padding-bottom: 2px; padding-left: 4px;" >
												</logic:notEqual>
												<bean:message key="screen.b_cpm.label_colon"/>
												<nested:text property="fixedForex" styleClass="fixedForex" styleId="fixedForex" style="width:140px;"/>
												<logic:notEqual name="LOAD_OBJECT" property="CHECK_BILLING_ACCOUNT" value="0">
													</span>
											 	</logic:notEqual>
											</td>
											<td></td>
											<td></td>
										</tr>
										</nested:equal>
										<nested:notEqual value="1" property="fixedForexFlg">
										    <nested:hidden property="fixedForex"/>
										</nested:notEqual>
										<nested:equal value="1" property="taxTypeDisplay">
										<tr>
											<td bgcolor="#FFCCFF"><bean:message key="screen.b_cpm.label.taxtype"/></td>
												<logic:equal name="LOAD_OBJECT" property="CHECK_BILLING_ACCOUNT" value="0">
													<td bgcolor="#FFCCFF">	
												</logic:equal>
												<logic:notEqual name="LOAD_OBJECT" property="CHECK_BILLING_ACCOUNT" value="0">
													<td style="padding-top: 0px; padding-left: 0px; padding-bottom: 0px;">
												<span style="background-color:#FFCCFF; margin: 0px; width: 280px; padding-top: 2px; padding-bottom: 2px; padding-left: 4px;" >
												</logic:notEqual>
												<bean:message key="screen.b_cpm.label_colon"/>
												<nested:select property="taxType" styleId="taxType" style="width:260px;">
									    			<html:optionsCollection name="LIST_TAX_TYPE" value="id" label="name"/>
									    		</nested:select>
									    		<input type="hidden" value='<nested:write property="taxType"/>' id="hidTaxType">
									    		<logic:notEqual name="LOAD_OBJECT" property="CHECK_BILLING_ACCOUNT" value="0">
													</span>
											 	</logic:notEqual>
											</td>
											<td nowrap="nowrap" colspan="2">
												<c:if test="${action eq 'new'}">
											        <span class="disableLink" style="text-decoration:none">
														<bean:message key="screen.b_cpm.label.subcriptionInfo" />
														<bean:message key="screen.b_cpm.label_colon"/>
														<bean:message key="screen.b_cpm.label.auto"/>
													</span>
											    </c:if>
											    <c:if test="${action ne 'new'}">
												    <nested:equal property="b_ssmIsUsed" value="1">
												        <c:choose>
															<c:when test="${'1' eq accessRightB_SSM or '2' eq accessRightB_SSM}">
																<a href="javascript: viewSubscriptionInfo();">
																	<bean:message key="screen.b_cpm.label.subcriptionInfo" />
																	<bean:message key="screen.b_cpm.label_colon"/>
																	<nested:write property="idSubInfo"/>
																</a>
															</c:when>
															<c:otherwise>
																<bean:message key="screen.b_cpm.label.subcriptionInfo" />
																<bean:message key="screen.b_cpm.label_colon"/>
																<nested:write property="idSubInfo"/>
															</c:otherwise>
														</c:choose>
													</nested:equal>
													<nested:notEqual property="b_ssmIsUsed" value="1">
													    <bean:message key="screen.b_cpm.label.subcriptionInfo" />
														<bean:message key="screen.b_cpm.label_colon"/>
														<nested:write property="idSubInfo"/>
													</nested:notEqual>
												</c:if>
											</td>
											<td>
												<nested:notEmpty property="addStdPlnMul">
													<%--
													<logic:notEqual value="PS3" name="CUSTOMER_PLAN" property="planStatus">
													</logic:notEqual>
													--%>
													<logic:notEqual value="PS2" name="CUSTOMER_PLAN" property="planStatus">
														<logic:equal value="PS3" name="CUSTOMER_PLAN" property="planStatus">
															<c:if test="${'1'eq LOAD_OBJECT.EditActivePlanAMTFlg}">
																<button id="btnAddServiceMul" onclick="javascript: addServiceMul();" style="width:100%"><bean:message key="screen.b_cpm.button.addServicesMul"/></button>
															</c:if>
														</logic:equal>
														<!-- wcbeh@2016xxxx fixing in progress -->
														<!--<logic:equal value="PS7" name="CUSTOMER_PLAN" property="planStatus">
															<c:if test="${'1'eq LOAD_OBJECT.EditActivePlanAMTFlg}">
																<button id="btnAddServiceMul" onclick="javascript: addServiceMul();" style="width:100%"><bean:message key="screen.b_cpm.button.addServicesMul"/></button>
															</c:if>
														</logic:equal>-->
														<logic:notEqual value="PS3" name="CUSTOMER_PLAN" property="planStatus">
															<button id="btnAddServiceMul" onclick="javascript: addServiceMul();" style="width:100%"><bean:message key="screen.b_cpm.button.addServicesMul"/></button>
														</logic:notEqual>
													</logic:notEqual>
												</nested:notEmpty>
											</td>
										</tr>
										</nested:equal>
										<nested:notEqual value="1" property="taxTypeDisplay">
											<nested:hidden property="taxType"/>
										</nested:notEqual>
										</table>
									</div><!-- Plan header end -->
									<%-- Apply All --%>
									<logic:equal name="CUSTOMER_PLAN" property="planType" value="NP">
									<div class="planHeaderInformation">
									    <fieldset>
              							    <legend style="color: #4876FF"><bean:message key="screen.b_cpm.applyAllTitle"/></legend>
											<table class="informationPlan" cellpadding="0" cellspacing="0" width="100%">
											    <col width="18%"/>
								                <col width="1%"/>
								                <col width="81%"/>
								                <nested:empty property="idCustPlan">
								                    <tr>
								                        <td>
								                            <nested:checkbox property="GSTApplyAllChk" styleId="GSTApplyAllChk" value="1" onclick="GSTApplyAllEvt('GSTApplyAllChkClick')"/>
								                            <bean:message key="screen.b_cpm.applyAllGst"/>
								                        </td>
								                        <td>
								                            <bean:message key="screen.b_cpm.applyAllColon"/>
								                        </td>
								                        <td>
								                            <nested:select property="GSTApplyAllCbo" styleId="GSTApplyAllCbo" onchange="GSTApplyAllEvt('GSTApplyAllCboChange')">
								                                <html:options collection="LIST_GST" property="id" labelProperty="name"/>
								                            </nested:select>
								                        </td>
								                    </tr>
								                    <tr>
								                        <td>
								                            <nested:checkbox property="categoryApplyAllChk" styleId="categoryApplyAllChk" value="1" onclick="categoryApplyAllEvt('categoryApplyAllChkClick','categoryApplyAllChkClick')"/>
								                            <bean:message key="screen.b_cpm.applyAllCategory"/>
								                        </td>
								                        <td>
								                            <bean:message key="screen.b_cpm.applyAllColon"/>
								                        </td>
								                        <td>
								                            <input type="hidden" id="categoryApplyAllCboHid" value='<nested:write property="categoryApplyAllCbo"/>'>
								                            <input type="hidden" id="serviceApplyAllCboHid" value='<nested:write property="serviceApplyAllCbo"/>'>
								                            <nested:select property="categoryApplyAllCbo" styleId="categoryApplyAllCbo" onchange="categoryApplyAllCboEvt(this, '1', '','categoryApplyAllCboChange')">
								                                <option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
								                                <logic:iterate id="SVC_LEVEL1" name="LOAD_OBJECT" property="SVC_LEVEL1">
																    <option value="<bean:write name="SVC_LEVEL1" property="SVC_GRP"/>"><bean:write name="SVC_LEVEL1" property="SVC_GRP_NAME"/></option>
																</logic:iterate>
								                            </nested:select>
								                            <nested:select property="serviceApplyAllCbo" styleId="serviceApplyAllCbo" onchange="categoryApplyAllEvt('serviceApplyAllCboChange','serviceApplyAllCboChange')">
								                                <option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
								                            </nested:select>
								                        </td>
								                    </tr>
								                </nested:empty>
								                <nested:notEmpty property="idCustPlan">
								                    <tr>
								                        <td>
								                            <bean:message key="screen.b_cpm.applyAllGst"/>
								                        </td>
								                        <td>
								                            <bean:message key="screen.b_cpm.applyAllColon"/>
								                        </td>
								                        <td>
								                            <nested:hidden property="allGstSameFlg"/>
								                            <nested:equal value="0" property="allGstSameFlg">
								                                <bean:message key="screen.b_cpm.applyAllGstAllSame" />
								                            </nested:equal>
								                            <nested:notEqual value="0" property="allGstSameFlg">
								                                <span style="color: red;"><bean:message key="screen.b_cpm.applyAllNotSameNotAll"/></span>
								                                <bean:message key="screen.b_cpm.applyAllGstAllNotSame" />
								                            </nested:notEqual>
								                        </td>
								                    </tr>
								                    <tr>
								                        <td>
								                            <bean:message key="screen.b_cpm.applyAllCategory"/>
								                        </td>
								                        <td>
								                            <bean:message key="screen.b_cpm.applyAllColon"/>
								                        </td>
								                        <td>
								                            <nested:hidden property="allCategorySameFlg"/>
								                            <nested:equal value="0" property="allCategorySameFlg">
								                                <bean:message key="screen.b_cpm.applyAllCategoryAllSame"/>
								                            </nested:equal>
								                            <nested:notEqual value="0" property="allCategorySameFlg">
								                                <span style="color: red;"><bean:message key="screen.b_cpm.applyAllNotSameNotAll"/></span>
								                                <bean:message key="screen.b_cpm.applyAllCategoryAllNotSame"/>
								                            </nested:notEqual>
								                        </td>
								                    </tr>
								                </nested:notEmpty>
											</table>
										</fieldset>
									</div>
									</logic:equal>
								</td>
							</tr>
						</table>
						<!-- Billing instruction -->
						<table id="planDetail" class="customerPlanInformation" style="background-color:white;">
						<colgroup>
							<col width="70%"/>
							<col width="10%"/>
							<col width="10%"/>
							<col width="10%"/>
						</colgroup>
							<tr>
								<td colspan="4" id="service_group">
									<!-- Service -->
									<div id="first_service" style="display: none" class="service_object">
										<table cellspacing="0" style="width:100%;border-top: solid 2px #8cb0f8;border-bottom: solid 2px #8cb0f8;border-left: solid 2px #8cb0f8;border-right: solid 2px #8cb0f8;">
											<colgroup>
												<col width="2%"/>
												<col width="10%"/>
												<col width="88%"/>
											</colgroup>
											<tr>
												<td colspan="2" style="width:10%;text-align:left;background-color:#8cb0f8">
													<div id="seqServiceName" style="font-weight: bold;color:#000080;">
														<bean:message key="screen.b_cpm.label.service"/>
													</div>
												</td>
												<td style="width:87%;text-align:right;background-color:white;">
													<logic:notEqual value="PS2" name="CUSTOMER_PLAN" property="planStatus">
														<span class="removeX" style="cursor: pointer;" onclick="javascript:removeService(this);">Remove &nbsp;<img src="../image/delete.gif"/></span>
													</logic:notEqual>
													<input type="hidden" name="idCustPlanGrp" class="idCustPlanGrp"/>
													<input type="hidden" name="serviceStatus" class="serviceStatus" value="PS1"/>
													<input type="hidden" name="serviceIdPlan" class="serviceIdPlan"/>
													<input type="hidden" name="serviceMultiPln" class="serviceMultiPln"/>
													<input type="hidden" name="billingStatus" class="billingStatus" value="BS0"/>
												</td>
											</tr>
											<tr style="background-color:#ffffcc;">
												<td></td>
												<td colspan="2">
													<!-- Service Header -->
													<table width="100%" class="blankTable">
														<colgroup>
															<col width="15%"/>
															<col width="25%"/>
															<col width="15%"/>
															<col width="20%"/>
															<col width="25%"/>
														</colgroup>
														<tr class="serviceTable">
															<td nowrap="nowrap"><bean:message key="screen.b_cpm.header.servicePeriod"/><span style="color:red"><bean:message key="screen.b_cpm.label.star"/></span></td>
															<td nowrap="nowrap">
																<bean:message key="screen.b_cpm.label_colon"/>
																<bean:message key="screen.b_cpm.label.from"/>
																<input type="text" name="services[i].serviceDateStart" class="serviceDateStart" readonly="readonly" maxlength="10">
																<input type="button" onclick="javascript: customCalendar(this, 'serviceDateStart', 'dd/MM/yyyy');" value="" class="BlueStyle-button"/>
															</td>
															<td nowrap="nowrap">
																<bean:message key="screen.b_cpm.label.to"/>
																<input type="text" name="services[i].serviceDateEnd" class="serviceDateEnd" disabled="disabled" readonly="readonly" maxlength="10">
																<input type="button" onclick="javascript: customCalendar(this, 'serviceDateEnd', 'dd/MM/yyyy');" value="" class="BlueStyle-button" />
															</td>
															<td nowrap="nowrap"><input type="checkbox" name="services[i].autoRenewal" class="autoRenewal" checked="checked" value="1" onclick="javascript: changeAutoRenewal(this);"><bean:message key="screen.b_cpm.label.autoRenewal"/></td>
															<td rowspan="2" nowrap="nowrap" colspan="2" valign="top" style="text-align: right;">
																<div class="<t:writeCodeValue codeList="COLOR_CODE" key="PS1"></t:writeCodeValue>" style="width:70%;">
																	<h4><bean:message key="screen.b_cpm.label.status"/></h4>
																	<h4>
																		<t:writeCodeValue codeList="LIST_PLANSTATUS" key="PS1"/>
																	</h4>
																</div>
																<div style="color: blue;padding-left: 80px;padding-top: 20px;text-align: left;">
																    <bean:message key="screen.b_cpm.label.billingStatus"/>
																    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="screen.b_cpm.label_colon"/>&nbsp;
																    <t:writeCodeValue codeList="LIST_BILLINGSTATUS" key="BS0"/>
															    </div>
															    <div style="color: blue;padding-left: 80px;text-align: left;">
															    <bean:message key="screen.b_cpm.label.CompletionDate"/>
															    &nbsp;<bean:message key="screen.b_cpm.label_colon"/>&nbsp;
															   --/--/----
															    </div>
															</td>
														</tr>
														<tr class="serviceTable">
															<td colspan="3">
																<fieldset class="minimumService">
																	<legend>
																		<div style="color:#4876FF;"><bean:message key="screen.b_cpm.label.minimumService"/></div>
																	</legend>
																	<table width="100%">
																		<colgroup>
																			<col width="20%"/>
																			<col width="30%"/>
																			<col width="50%"/>
																		</colgroup>
																		<tr>
																			<td nowrap="nowrap"><input type="checkbox" name="services[i].minimumService" class="minimumService" onclick="javascript: checkServicePeriod(this);" value="1"></td>
																			<td nowrap="nowrap">
																				<bean:message key="screen.b_cpm.label.from"/>
																				<input type="text" name="services[i].minimumServiceFrom" class="minimumServiceFrom" readonly="readonly" maxlength="10"/>
																				<input type="button" onclick="javascript: customCalendar(this, 'minimumServiceFrom', 'dd/MM/yyyy');" value="" class="BlueStyle-button" />
																			</td>
																			<td nowrap="nowrap">
																				<bean:message key="screen.b_cpm.label.to"/>
																				<input type="text" name="services[i].minimumServiceTo" class="minimumServiceTo" readonly="readonly" maxlength="10"/>
																				<input type="button" onclick="javascript: customCalendar(this, 'minimumServiceTo', 'dd/MM/yyyy');" value="" class="BlueStyle-button" />
																			</td>
																		</tr>
																		<tr>
																			<td nowrap="nowrap"><bean:message key="screen.b_cpm.label.contractTerm"/></td>
																			<td nowrap="nowrap" colspan="2">
																				<bean:message key="screen.b_cpm.label_colon"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																				<input type="text" name="services[i].contactTermNo" class="contactTermNo"/>
																				<input type="radio" name="services[i].contactTerm" value="M" class="contactTerm"/><bean:message key="screen.b_cpm.label.months"/>
																				<input type="radio" name="services[i].contactTerm" value="Y" class="contactTerm" checked="checked"/><bean:message key="screen.b_cpm.label.years"/>
																			</td>
																		</tr>
																	</table>
																</fieldset>
															</td>
															<td>
															</td>
														</tr>
														<tr>
															<td nowrap="nowrap"><bean:message key="screen.b_cpm.label.proRateBaseOn"/></td>
															<td colspan="5" nowrap="nowrap">
																<bean:message key="screen.b_cpm.label_colon"/>
																<span style="width:110px;">
																	<input type="radio" name="services[i].proRateBase" class="proRateBase" value="S" checked="checked" onclick="javascript: changeProrateBase(this);"/><bean:message key="screen.b_cpm.button.sysdate"/>
																</span>
																<input type="radio" name="services[i].proRateBase" class="proRateBase" value="U"  onclick="javascript: changeProrateBase(this);"/><bean:message key="screen.b_cpm.button.userDefine"/>
																<input type="text" name="services[i].proRateBaseNo" disabled="disabled" class="proRateBaseNo"/><bean:message key="screen.b_cpm.label.daysPerMonth"/>
															</td>
														</tr>
														<tr>
															<td nowrap="nowrap"><bean:message key="screen.b_cpm.label.billingAmount"/></td>
															<td colspan="2" nowrap="nowrap">
																<bean:message key="screen.b_cpm.label_colon"/>
																<span style="width:110px;">
																	<input type="radio" name="isLumpSum" class="isLumpSum" value="0" checked="checked" onclick="javascript: changItemmise(this);"/><bean:message key="screen.b_cpm.label.itemised1"/>
																</span>
																<input type="radio" name="isLumpSum" class="isLumpSum" value="1"  onclick="javascript: changItemmise(this);"/><bean:message key="screen.b_cpm.label.lumpSum1"/>
															</td>
															<td nowrap="nowrap">
																<c:if test="${'1'eq LOAD_OBJECT.AddExistingPlanflg}">
																	<logic:notEqual value="PS2" name="CUSTOMER_PLAN" property="planStatus">
																		<logic:equal name="CUSTOMER_PLAN" property="planType" value="SP">
																			<input type="button" style="width:264px;" value="<bean:message key="screen.b_cpm.button.addExistingSubPlanOptSrv"/>" onclick="javascript: addExistingSubPlanOptionService(this);"/>
																		</logic:equal>
																	</logic:notEqual>
																</c:if>
															</td>
															<td nowrap="nowrap">
																<!-- button add sub service -->
																	<logic:notEqual value="PS2" name="CUSTOMER_PLAN" property="planStatus">
																		<logic:equal name="CUSTOMER_PLAN" property="planType" value="NP">
																			<input type="button" value="<bean:message key="screen.b_cpm.button.addSubPlan"/>" onclick="javascript: addNewSubPlan(this);"/>
																			<input type="button" value="<bean:message key="screen.b_cpm.button.addOptionService"/>" onclick="javascript: addNewOptionService(this);"/>
																		</logic:equal>
																		<logic:equal name="CUSTOMER_PLAN" property="planType" value="SP">
																			<input type="button" value="<bean:message key="screen.b_cpm.button.addSubPlanOptSrv"/>" onclick="javascript: addSubPlanOptionService(this);"/>
																		</logic:equal>
																	</logic:notEqual>
															</td>
														</tr>
														<tr>
															<td nowrap="nowrap"><bean:message key="screen.b_cpm.label.Discount"/></td>
															<td colspan="4" nowrap="nowrap" class="discountDetail">
																<bean:message key="screen.b_cpm.label_colon"/>
																<span style="width:110px;">
																	<input type="radio" name="isDiscountLumpSum" class="isDiscountLumpSum" value="0" checked="checked"/><bean:message key="screen.b_cpm.label.itemised1"/>
																</span>
																<input type="radio" name="isDiscountLumpSum" class="isDiscountLumpSum" value="1" /><bean:message key="screen.b_cpm.label.lumpSum1"/>
															</td>
														</tr>
														<!-- #189 Start -->
														<tr id="trBillingOption">
															<td nowrap="nowrap"><bean:message key="screen.b_cpm.label.billingOption"/></td>
															<td colspan="4" nowrap="nowrap" class="billingOptionDetail">
																<bean:message key="screen.b_cpm.label_colon"/>
																<span style="width:110px;">
																	<input type="radio" name="billingOption" class="billingOption" value="1" checked="checked"/><bean:message key="screen.b_cpm.label.prepaid"/>
																</span>
																<input type="radio" name="billingOption" class="billingOption" value="2"/><bean:message key="screen.b_cpm.label.postpaid"/>
															</td>
														</tr>
														<!-- #189 End -->
														<tr>
															<td colspan="6">
																<table width="100%" cellpadding="0" cellspacing="0">
																	<colgroup>
																		<col width="63%"/>
																		<col width="10%"/>
																		<col width="10%"/>
																		<col width="15%"/>
																		<col width="2%"/>
																	</colgroup>
																	<tr style="background-color:#8cb0f8">
																	<c:if test="${action ne 'new'}">
																		<td class="customerPlanHeaderItem" nowrap="nowrap" style="text-align:left;">
																			<div>
																				<span style="width:24%">
																					<bean:message key="screen.b_cpm.label.billingDesc"/>
																				</span>
																				<span>
																				<!--<logic:equal value="1" property="customerPlan.masterLocationDisplayFlg" name="_B_CPM_S02Form">
																					<input type="checkbox" name="masterLocationFlg" class="masterLocationFlg" value="1" checked="checked" onclick="javascript: checkMasterLocation(this);"/>
																					<bean:message key="screen.b_cpm.label.masterLocation"/>
															        				<bean:message key="screen.b_cpm.label.colon"/>
															        				&nbsp;&nbsp;
																					<select name="masterLocation" class="masterLocation" onchange="javascript: changeLocation(this);">
								                           									<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
								                                							 <logic:iterate id="masterLocation" name="LIST_MASTER_LOCATION">
																								<option value="${masterLocation.id}">${masterLocation.id}</option>
																							</logic:iterate>												
								                            						</select>
								                            					</logic:equal>   -->                  								
															        			</span>
													        				</div>
																		</td>
																	</c:if>	
																	<c:if test="${action eq 'new'}">
																		<td class="customerPlanHeaderItem" nowrap="nowrap" style="text-align:left;">
																			<div>
																				<span style="width:24%;">
																					<bean:message key="screen.b_cpm.label.billingDesc"/>
																				</span>
																				<span>
																				<logic:equal value="1" property="masterLocationDisplayFlg" name="CUSTOMER_PLAN">
																					<input type="checkbox" name="masterLocationFlg" value="1" class="masterLocationFlg" onclick="javascript: checkMasterLocation(this);"/>
																					<bean:message key="screen.b_cpm.label.masterLocation"/>
															        				<bean:message key="screen.b_cpm.label.colon"/>
															        				&nbsp;&nbsp;
															        				<select name="masterLocation" class="masterLocation" onchange="javascript: changeLocation(this);">
								                           									<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
								                                							 <logic:iterate id="masterLocation" name="LIST_MASTER_LOCATION">
																								<option value="${masterLocation.id}">${masterLocation.id}</option>
																							</logic:iterate>												
								                            						</select>
															        				<!-- <nested:select property="masterLocation" styleClass="masterLocation" onchange="javascript: changeLocation(this);">
																							<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
																							<logic:iterate id="masterLocation" name="LIST_MASTER_LOCATION">
																								<option value="${masterLocation.id}">${masterLocation.id}</option>
																							</logic:iterate>
																					</nested:select> -->
																					<!-- <select name="masterLocation" class="masterLocation" onchange="javascript: changeLocation(this);">
								                           									<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
								                                							 <logic:iterate id="masterLocation" name="LIST_MASTER_LOCATION">
																								<option value="${masterLocation.id}">${masterLocation.id}</option>
																							</logic:iterate>												
								                            						</select> -->
								                            					</logic:equal>                    								
															        			</span>
																				<logic:equal value="1" property="m_jnmDisplayFlg" name="CUSTOMER_PLAN">
																					<span style="text-align:right;font-weight:normal;padding-right:14px;width:39%">
								                            							<input type="checkbox" name="jobNoAllChk" class="jobNoAllChk" onclick="JobNoAllChkEvt('JobNoAllChkClick',this)"/>
								                           								<bean:message key="screen.b_cpm.label.jobNo"/>.
								                           								<bean:message key="screen.b_cpm.label.colon"/>
								                           								&nbsp;&nbsp;
								                           								<select name="jobNoAllJob" class="jobNoAllJob" onchange="JobNoAllChkEvt('JobNoAllJboChange',this)">
								                           									<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
								                           									<option value="add">-----add one-----</option>
								                                							 <logic:iterate id="JOB" name="LOAD_OBJECT" property="JOB_NO">
																								<option value="<bean:write name="JOB" property="JOB_NO"/>"><bean:write name="JOB" property="JOB_NO"/></option>
																							</logic:iterate>
								                            							</select>
								                            						</span>
								                            					</logic:equal>
								                            				</div>
																		</td>
																		</c:if>	
																		<td class="customerPlanHeaderItem" nowrap="nowrap" style="text-align:right;"><bean:message key="screen.b_cpm.label.quantity"/></td>
																		<td class="customerPlanHeaderItem" nowrap="nowrap" style="text-align:right;"><bean:message key="screen.b_cpm.label.unitPrice"/></td>
																		<td class="customerPlanHeaderItem" nowrap="nowrap" style="text-align:right;"><bean:message key="screen.b_cpm.label.amount"/></td>
																		<td></td>
																	</tr>
																	<tr>
																		<td nowrap="nowrap" style="text-align:left;padding-left:0px;">
																			<textarea name="billDesc" class="billDesc" style="overflow-y:visible;height:40px;" rows="2"></textarea>
																		</td>
																		<td colspan="4" valign="top">
										 									<table width="100%">
										 										<colgroup>
																					<col width="27%"/>
																					<col width="27%"/>
																					<col width="43%"/>
																					<col width="3%"/>
																				</colgroup>
										 										<tr>
										 											<td nowrap="nowrap" class="serviceQuantity" valign="top">1</td>
																					<td nowrap="nowrap" class="serviceUnitPrice" valign="top">0.00</td>
																					<td nowrap="nowrap" class="serviceAmount" valign="top">0.00</td>
																					<td></td>
										 										</tr>
										 										<tr>
																					<td ></td>
																					<td nowrap="nowrap" valign="top" class="serviceDiscount"><bean:message key="screen.b_cpm.label.discount"/><bean:message key="screen.b_cpm.label_discountcolon"/></td>
																					<td nowrap="nowrap" valign="top" class="serviceDiscountDetail">-0.00</td>
																					<td></td>
																				</tr>
										 									</table>
																		</td>
																	</tr>
																	<tr>
																		<td>
																			<c:if test="${LOAD_OBJECT.CustPoDisplay eq 'CPM'}">
																				<bean:message key="screen.b_cpm.label.custPo"/>
																				<bean:message key="screen.b_cpm.label_colon"/>
																				<input type="text" name="services[i].custPo" class="custPo" style="width:89%" maxlength="30"/>
																			</c:if>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td colspan="3" class="subplan" style="background-color:#e7efff;">
												<!-- subplan Detail -->
												</td>
											</tr>
											<tr>
												<td colspan="3" class="option_service" style="background-color:#e7efff;">
													<!-- option_service Detail -->
												</td>
											</tr>
										</table>
										<br/>
									</div><!-- End Service -->
									<nested:iterate id="service" property="services" indexId="i">
									    <%
									        request.setAttribute("service", service); 
									    %>
									    <c:if test="${service.serviceStatus == 'PS1' || service.serviceStatus == 'PS2' || (service.serviceStatus == 'PS3' && 'BS3' ne service.billingStatus) || (service.serviceStatus == 'PS7' && 'BS3' ne service.billingStatus)}">
											<jsp:include page="B_CPM_S02e_PS1AndPS3.jsp" flush="true">
											    <jsp:param value="${i}" name="i"/>
											</jsp:include>
										</c:if>
									    <c:if test="${(service.serviceStatus == 'PS3' && 'BS3' eq service.billingStatus) || (service.serviceStatus == 'PS7' && 'BS3' eq service.billingStatus) || service.serviceStatus == 'PS8'}">
										    <jsp:include page="B_CPM_S02e_NotPS1AndPS3.jsp" flush="true">
										        <jsp:param value="${i}" name="i"/>
										    </jsp:include>
										   <!-- End Service -->
										</c:if>
									</nested:iterate>
								</td>
							</tr>
						</table>
					</div><!-- New tab end -->
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>
	    		<div class="bottomButtons">
		    		<button id="btnSave" onclick="javascript: saveCustomerPlan();"><bean:message key="screen.b_cpm.button.save"/></button>
		    		<button id="btnExit" onclick="javascript: exit();"><bean:message key="screen.b_cpm.button.exit"/></button>
		    	</div>
	    		</td>
	    	</tr>
	    	</table>
			</nested:nest>
<!-- message display start -->
<div style="color:#0046D5;padding-left:20px;padding-top:0px;">
	<html:messages id="message" message="true">
		<bean:write name="message"/>
	</html:messages>
</div>
<div style="color:red;padding-left:20px;padding-top:0px;" id="errorMessageInfo">
	<html:messages id="message">
		<bean:write name="message"/><br/>
	</html:messages>
</div>
<!-- message display end -->
			<div style="visibility: hidden">
			<input name="templateCalendar"/>
			<t:inputCalendar for="templateCalendar" format="dd/MM/yyyy"/>
			</div>
		</ts:form>
		<!-- clone  group start -->
		<div id="clone_subPlan" style="display: none" class="subPlan">
		<fieldset>
			<legend>
				<div class="subPlanLabel" style="font-weight: bold;color:#4876FF;">[<span id="planNo"></span>][<bean:message key="screen.b_cpm.label.subPlan"/>]</div>
				<div class="optionServiceLabel" style="font-weight: bold;color:#4876FF;" >[<span id="planNo"></span>][<bean:message key="screen.b_cpm.label.optionService"/>]</div>
			</legend>
			<logic:notEqual value="PS2" name="CUSTOMER_PLAN" property="planStatus">
				<span class="removeX" onclick="javascript: removeSubPlan(this);"><a href="javascript:void(0)">Remove &nbsp;<img src="../image/delete.gif"/></a></span>
			</logic:notEqual>
			<input type="hidden" name="idCustPlanLink" class="idCustPlanLink"/>
			<table width="100%" class="subPlanContent">
				<colgroup>
					<col width="2%">
					<col width="10%">
					<col width="10%">
					<col width="40%">
					<col width="10%">
					<col width="12%">
					<col width="14%">
					<col width="2%">
				</colgroup>
				<%--
				<tr>
					<td>
						<input type="hidden" name="services[i].subPlans[i].itemType" class="itemType"/>
					</td>
					<td nowrap="nowrap"><bean:message key="screen.b_cpm.label.serviceName"/><span style="color:red"><bean:message key="screen.b_cpm.label.star"/></span></td>
					<td nowrap="nowrap">
						<bean:message key="screen.b_cpm.label_colon"/>
						<input type="text" name="services[i].subPlans[i].planDesc" style="width: 95%" class="planDesc"/>
					</td>
					<td colspan="3">&nbsp;</td>
				</tr>
				--%>
				<tr>
					<td>
					    <input type="hidden" name="services[i].subPlans[i].itemType" class="itemType"/>
					</td>
					<td nowrap="nowrap" style="width:33%"><bean:message key="screen.b_cpm.label.itemDescription"/><span style="color:red"><bean:message key="screen.b_cpm.label.star"/></span></td>
					<td nowrap="nowrap">
						<!-- #200, #201 wcbeh@20160921 - Sub Master Location for NP -->
						<logic:equal value="1" property="customerPlan.masterLocationDisplayFlg" name="_B_CPM_S02Form">
						<div>
							<span style="width:100%;">
								<bean:message key="screen.b_cpm.label.masterLocation"/>
		        				<bean:message key="screen.b_cpm.label.colon"/>
		        				&nbsp;&nbsp;
		        				<select name="services[i].subPlans[i].subLocation" class="subLocation">
         							<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
              						<logic:iterate id="subLocation" name="LIST_MASTER_LOCATION">
										<option value="${subLocation.id}">${subLocation.id}</option>
									</logic:iterate>												
          						</select>
							</span>
						</div>
						</logic:equal>
					</td>
					<td nowrap="nowrap" style="text-align:left;padding-right:0px">
					<logic:equal value="1" property="customerPlan.m_jnmDisplayFlg" name="_B_CPM_S02Form">
					<input type="checkbox" name="services[i].subPlans[i].isDisplayJobNo" class="isDisplayJobNo" value="1"/>
					<bean:message key="screen.b_cpm.label.jobNo"/>&nbsp;&nbsp;&nbsp;&nbsp;
						<bean:message key="screen.b_cpm.label_colon"/>
						<select name="services[i].subPlans[i].jobNo" class="jobNo">
							<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
							<option value="add">-----add one-----</option>
							<logic:iterate id="JOB" name="LOAD_OBJECT" property="JOB_NO">
							<option value="<bean:write name="JOB" property="JOB_NO"/>"><bean:write name="JOB" property="JOB_NO"/></option>
							</logic:iterate>
						</select>
						</logic:equal>
					</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="3" valign="top">
						<textarea name="services[i].subPlans[i].itemDesc" class="itemDesc" style="overflow-y:visible;height:40px;" rows="2"></textarea>
					</td>
					<td colspan="4" valign="top">
						<table width="100%">
							<colgroup>
								<col width="27%">
								<col width="33%">
								<col width="38%">
								<col width="2%">
							</colgroup>
							<tr>
								<td valign="top" class="numberStyle"><input type="text" name="services[i].subPlans[i].quantity" class="subPlanQuantity" onchange="javascript: updateAmount(this);" onkeyup="javascript: updateAmount_enter(this,event);"/></td>
								<td valign="top" class="numberStyle"><input type="text" name="services[i].subPlans[i].unitPrice" class="subPlanUnitPrice" onchange="javascript: updateAmount(this);" onkeyup="javascript: updateAmount_enter(this,event);"/></td>
								<td valign="top" class="numberStyle"><input type="text" name="services[i].subPlans[i].amount" class="subPlanAmount" style="width:100%" readonly="readonly"/></td>
				    			<td>&nbsp;</td>
							</tr>
							<tr>
								<td class="serviceDiscount"><bean:message key="screen.b_cpm.label.Discount"/><bean:message key="screen.b_cpm.label_colon"/></td>
								<td valign="top" nowrap="nowrap" class="numberStyle"><input type="radio" name="isDiscountOneTime" class="isDiscountOneTime" value="O" checked="checked"/><bean:message key="screen.b_cpm.label.onetime"/></td>
								<td><input type="text" name="services[i].subPlans[i].discamount" class="subDiscount"  style="width:100%" onchange="javascript: updateDiscount(this);" onkeyup="javascript: updateDiscount_enter(this,event);"/></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td valign="top" nowrap="nowrap" class="numberStyle"><input type="radio" name="isDiscountOneTime" class="isDiscountOneTime" value="R" /><bean:message key="screen.b_cpm.label.recurring"/></td>
								<td></td>
								<td></td>
							</tr>
						</table>
					</td>
				</tr>
				<!-- sub plan header -->
				<!-- end sub plan detail -->
			</table>
			
			<table width="100%" class="subPlanContent">
			    <colgroup>
					<col width="2%">
					<col width="10%">
					<col width="10%">
					<col width="40%">
					<col width="10%">
					<col width="11%">
					<col width="15%">
					<col width="2%">
				</colgroup>
			    <tr>
				    <td>&nbsp;</td>
					<td>
						<span class="spanToggleClick">...
						</span>
					</td>
					<td nowrap="nowrap"><bean:message key="screen.b_cpm.label.billingAccCode"/><span style="color:red"><bean:message key="screen.b_cpm.label.star"/></span></td>
					<td nowrap="nowrap">
						<bean:message key="screen.b_cpm.label_colon"/>
						<select name="services[i].subPlans[i].svcLevel1" class="svcLevel1" onchange="javascript: changeLevel1(this);">
							<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
							<logic:iterate id="SVC_LEVEL1" name="LOAD_OBJECT" property="SVC_LEVEL1">
							<option value="<bean:write name="SVC_LEVEL1" property="SVC_GRP"/>"><bean:write name="SVC_LEVEL1" property="SVC_GRP_NAME"/></option>
							</logic:iterate>
						</select>
						<select name="services[i].subPlans[i].svcLevel2" class="svcLevel2" onchange="javascript: changeLevel2(this);">
							<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
						</select>
					</td>
					<td></td>
					<td></td>
					<td></td>
					<td>&nbsp;</td>
				</tr>
				<!-- sub plan detail -->
				<tr class="spanTogglePlace">
				    <td>&nbsp;</td>
					<td colspan="6">
						<!-- Add detail for sub plan -->
						<table>
							<colgroup>
								<col width="70%"/>
								<col width="30%"/>
							</colgroup>
							<tr>
								<td valign="top">
									<!-- Sub plan detail group -->
									<table class="subPlanDetail">
										<colgroup>
											<col width="10%"/>
											<col width="60%"/>
											<col width="30%"/>
										</colgroup>
										<tr class="subPlanDetailHeader">
											<td>
												<span onclick="javascript: addPlanDetail(this);" class="addSubPlanDetail"><a href="javascript:void(0)"><bean:message key="screen.b_cpm.label.add"/></a></span>
											</td>
											<td>
												<bean:message key="screen.b_cpm.label.plan"/><br>
												<bean:message key="screen.b_cpm.label.planDetail"/>
											</td>
											<td>
												<bean:message key="screen.b_cpm.label.vendor"/>
											</td>
										</tr>
									</table>
								</td>
								<td valign="top">
									<!-- Sub plan rate group -->
									<table width="100%" class="blankTable">
									<colgroup>
										<col width="33%">
										<col width="33%">
										<col width="34%">
									</colgroup>
									<tr class="subPlanDetailHeader">
										<td>
											<bean:message key="screen.b_cpm.label.rate"/>
											<bean:message key="screen.b_cpm.label.rateType"/>
										</td>
										<td>
											<bean:message key="screen.b_cpm.label.rate"/>
											<bean:message key="screen.b_cpm.label.ratemode"/>
										</td>
										<td nowrap="nowrap">
											
											<bean:write name="CUSTOMER_PLAN" property="taxWord"/>
											<input type="hidden" id="taxWordHidden" value="<bean:write name="CUSTOMER_PLAN" property="taxWord"/>"/>
											<bean:message key="screen.b_cpm.label_percentsymbol"/>&nbsp;
										</td>
									</tr>
									<tr class="subPlanDetailResult">
										<td rowspan="2">
											<select name="rateType" class="rateType">
												<%-- <option value=""><bean:message key="screen.b_cpm.listBox.default"/></option> --%>
												<logic:iterate id="rateType" name="LIST_RATETYPE">
												<option value="${rateType.id}">${rateType.name}</option>
												</logic:iterate>
											</select>
											<select name="rateType2" class="rateType2">
												<logic:iterate id="rateType2" name="LIST_RATETYPE2">
												<option value="${rateType2.id}">${rateType2.name}</option>
												</logic:iterate>
											</select>
										</td>
										<td>
											<select name="rateMode" class="rateMode">
												<%-- <option value=""><bean:message key="screen.b_cpm.listBox.default"/></option> --%>
												<logic:iterate id="rateMode" name="MODE_LIST">
												<option value="${rateMode.id}">${rateMode.name}</option>
												</logic:iterate>
											</select>
										</td>
										<td>
											<select name="applyGst" class="applyGst" onChange="onChangeGSTValue(this);">
												<logic:iterate id="gst" name="LIST_GST">
												<option value="${gst.id}">${gst.name}</option>
												</logic:iterate>
											</select>
											<select name="applyGstValue" class="applyGstValue" style="display: none;">
												<logic:iterate id="gstValue" name="LIST_GST_VALUE">
												<option value="${gstValue.id}">${gstValue.name}</option>
												</logic:iterate>
											</select>
										</td>
									</tr>
									<tr class=subPlanDetailResult>
										<td colspan="2" style="white-space:nowrap" class="detailGst">
										<div  style="font-style:italic;">
										<bean:message key="screen.b_cpm.label.rate"/>
										<bean:message key="screen.b_cpm.label.rateCur"/>
										<span class="displayCurrency"></span>:
										<input type="text" name="rate" style="width: 100px" class="rate">
										</div>
										</td>
									</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
					<td>&nbsp;</td>
				</tr>
			</table>
		</fieldset>
		</div>
		<div id="clone_subPlanDetail" style="display: none">
		<table>
			<tr class="clonePlanDetail">
				<td>
					<span onclick="javascript: deletePlanDetail(this);" class="deleteSubPlanDetail">
					<img class="imgDeleteOption" src="../image/delete.gif"/>
					</span>&nbsp;
				</td>
				<td valign="top">
					<input type="hidden" name="idCustPlanSvc" class="idCustPlanSvc"/>
					<select name="svcLevel3" class="svcLevel3">
						<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
					</select><br/>
					<select name="svcLevel4" class="svcLevel4">
						<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
					</select>
				</td>
				<td valign="top">
					<select name="vendor" class="vendor">
						<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
						<logic:iterate id="VENDOR" name="LOAD_OBJECT" property="VENDOR">
						<option value="<bean:write name="VENDOR" property="ID_SUPPLIER"/>"><bean:write name="VENDOR" property="SUPPLIER_NAME"/></option>
						</logic:iterate>
					</select><br/>
					<div class="lineDesc" style="word-break:break-all">
						<bean:message key="screen.b_cpm.label.lineDesc"/>
						<bean:message key="screen.b_cpm.label.colon"/>
						<span id="lineDescVal">
							<bean:message key="screen.b_cpm.label.empty"/>
						</span> 
					</div>
				</td>
			</tr>
		</table>
		</div>
		
		<div id="svcLevelGroup" style="display: none">
			<select class="svcLevel1">
				<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
				<logic:iterate id="SVC_LEVEL1" name="LOAD_OBJECT" property="SVC_LEVEL1">
				<option value="<bean:write name="SVC_LEVEL1" property="SVC_GRP"/>"><bean:write name="SVC_LEVEL1" property="SVC_GRP_NAME"/></option>
				</logic:iterate>
			</select>
			<select class="svcLevel2">
				<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
				<logic:iterate id="SVC_LEVEL2" name="LOAD_OBJECT" property="SVC_LEVEL2">
				<option value="<bean:write name="SVC_LEVEL2" property="ID_SERVICE"/>"
				gstValue="<bean:write name="SVC_LEVEL2" property="GST"/>"
				descAbbr="<bean:write name="SVC_LEVEL2" property="DESC_ABBR"/>"
				svcGrp="<bean:write name="SVC_LEVEL2" property="SVC_GRP"/>"><bean:write name="SVC_LEVEL2" property="SVC_NAME"/></option>
				</logic:iterate>
			</select>
			<select class="svcLevel3">
				<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
				<logic:iterate id="SVC_LEVEL3" name="LOAD_OBJECT" property="SVC_LEVEL3">
				<option value="<bean:write name="SVC_LEVEL3" property="ID_SERVICE"/>"
					gstValue="<bean:write name="SVC_LEVEL3" property="GST"/>"
					descAbbr="<bean:write name="SVC_LEVEL3" property="DESC_ABBR"/>"
					svcGrp="<bean:write name="SVC_LEVEL3" property="SVC_GRP"/>"><bean:write name="SVC_LEVEL3" property="SVC_NAME"/></option>
				</logic:iterate>
			</select><br/>
			<select class="svcLevel4">
				<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
				<logic:iterate id="SVC_LEVEL4" name="LOAD_OBJECT" property="SVC_LEVEL4">
				<option value="<bean:write name="SVC_LEVEL4" property="ID_SERVICE"/>"
					gstValue="<bean:write name="SVC_LEVEL4" property="GST"/>"
					svcGrp="<bean:write name="SVC_LEVEL4" property="SVC_GRP"/>"><bean:write name="SVC_LEVEL4" property="SVC_NAME"/></option>
				</logic:iterate>
			</select>
			<select class="expGrandTotal">
				<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
				<logic:iterate id="GRAND_TOTAL" property="GRAND_TOTAL" name="LOAD_OBJECT">
			    	<option value="<bean:write name="GRAND_TOTAL" property="CUR_CODE"/>"><bean:write name="GRAND_TOTAL" property="CUR_CODE"/></option>
			    </logic:iterate>
			</select>
		</div> 
		<!-- clone  group end -->
		<div id="message_group" style="display: none;">
			<div class="messageDeleteService"><bean:message key="errors.ERR1SC062"/></div>
			<div class="messageApprove"><bean:message key="info.ERR4SC005" arg0="approve"/></div>
			<div class="messageSuspend"><bean:message key="info.ERR4SC005" arg0="suspend"/></div>
			<div class="messageUnsuspend"><bean:message key="info.ERR4SC005" arg0="unsuspend"/></div>		
			<div class="messageRemoveService"><bean:message key="info.ERR4SC003" arg0="Service"/></div>
			<div class="messageRemoveSubPlan"><bean:message key="info.ERR4SC003" arg0="Sub Plan"/></div>
			<div class="messageRemoveOptionService"><bean:message key="info.ERR4SC003" arg0="Option Service"/></div>
			<input type="hidden" id="messageCurrencyDifferent" value="<bean:message key="info.ERR4SC013"/>"/>
			<div class="ERR4SC019_1" style="display:none;"><bean:message key="info.ERR4SC019" arg0="You are about to remove Radius Services, are you sure to remove?"/></div>
			<div class="ERR4SC019_2" style="display:none;"><bean:message key="info.ERR4SC019" arg0="You are about to add Radius Services, are you sure to add?"/></div>
			<div class="ERR1SC105_1" style="display:none;"><bean:message key="errors.ERR1SC105" arg0="Connection test in progress. Please click Test Complete button before deletion at subscription information maintenance."/></div>
			<div class="ERR1SC105_2" style="display:none;"><bean:message key="errors.ERR1SC105" arg0="Connection test in progress. Please click Test Complete button before remove sub plan at subscription information maintenance."/></div>
		</div>
		
	<div id="mytips" class="ui-autocomplete ui-front ui-menu ui-widget-content" style="display:none;">
		Loading.........
   </div>  
	</body>
</html:html>