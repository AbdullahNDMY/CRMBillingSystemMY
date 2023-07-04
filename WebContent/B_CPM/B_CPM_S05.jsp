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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
 
<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
   		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
   		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/stylesheet/tabcontent.css"/>
   		
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>	
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/tabcontent.js"></script>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/B_CPM/js/b_cpm_common.js"></script>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/B_CPM/js/b_cpm_s05.js"></script>
   		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/B_CPM/css/b_cpm.css"/>
   		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/B_CPM/css/b_cpm_s02v.css"/>
   		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/B_CPM/css/b_cpm_s02_common.css"/>
		<title></title>
	</head>
	<body onload="javascript: initLoad();">
		<!-- check access right START -->
	<%
	 	BillingSystemUserValueObject uvo = (BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT");
	 	String accessRight = CommonUtils.getAccessRight(uvo, "B-CPM");
	 	String accessRightT_BACBean = CommonUtils.getAccessRight(uvo, "B-BAC");
	 	String accessRightM_CST_BIBean = CommonUtils.getAccessRight(uvo, "M-CST-BI");
	 	String accessRightB_SSMBean = CommonUtils.getAccessRight(uvo, "B-SSM");
	 %>
	 <bean:define id="accessRightBean" value="<%= accessRight%>"/>
	 <bean:define id="accessRightT_BAC" value="<%= accessRightT_BACBean%>"/>
	 <bean:define id="accessRightM_CST_BI" value="<%= accessRightM_CST_BIBean%>"/>
	 <bean:define id="accessRightB_SSM" value="<%= accessRightB_SSMBean%>"/>
	<!-- check access right END -->
		<ts:form styleId="frmS02" action="/B_CPM_S02ViewDSP" method="POST">
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
			<t:defineCodeList id="LIST_BILLINGSTATUS"/>
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
 			<input type="hidden" name="idCustomer" value="${CUSTOMER_PLAN.idCust}"/><%-- used in case, input can't pass validator so the destination is initialize page => use it to get customer info  --%>
 			<html:hidden property="action" styleId="action" value="edit"/> 			
 			<input type="hidden" id="idService" name="idService"/>
 			<table width="100%">
 			<tr>
 				<td>
		 			<table class="subHeader" cellpadding="0" cellspacing="0">
		 				<tr>
			    			<td>
			    			<bean:message key="screen.b_cpm.s05.header"/>
			    			<nested:nest property="customerPlan">
			    			    <nested:write property="fromScreen"/>
			    			    <nested:equal value="IN" property="billType">
			    			        Invoice
			    			    </nested:equal>
			    			    <nested:equal value="DN" property="billType">
			    			        Debit Note
			    			    </nested:equal>
			    			    <nested:equal value="CN" property="billType">
			    			        Credit Note
			    			    </nested:equal>
			    			    <nested:equal value="SSM" property="billType">
			    			        Search
			    			    </nested:equal>
			    			    <nested:equal value="DET" property="billType">
			    			        Detail
			    			    </nested:equal>
			    			    <nested:equal value="BAC" property="billType">
			    			        Account
			    			    </nested:equal>
			    			</nested:nest>
			    			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    			</td>
			    		</tr>
			    	</table>
			    	<bean:define id="CUSTOMER_INFO" name="LOAD_OBJECT" property="CUSTOMER_INFO"></bean:define>
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
								&nbsp;
							</td>
						</tr>
						<tr>
							<td class="header">
								 <bean:message key="screen.b_cpm.label.custId"/>
							</td>
							<td>
								<bean:message key="screen.b_cpm.label_colon"/>
								<bean:write name="CUSTOMER_INFO" property="idCust"/>
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
									</logic:equal>
								</logic:iterate>
							</td>
						</tr>
					</table>
					<!-- New tab start -->
					<nested:nest property="customerPlan">
			 			<nested:hidden property="fromScreen" styleId="fromScreen"></nested:hidden>
			 			<nested:hidden property="billType" styleId="billType"></nested:hidden>
			 			<nested:hidden property="idCust" styleId="idCust"></nested:hidden>
			 			<nested:hidden property="idCustPlan" styleId="idCustPlan"></nested:hidden>
			 			<nested:hidden property="idSubInfo" styleId="idSubInfo"></nested:hidden>
			 			<nested:hidden property="planType" styleId="planType"></nested:hidden>
			 			<nested:hidden property="fixedForex"/>
			 			<nested:hidden property="paymentMethod"/>
			 			<nested:hidden property="expGrandTotal"/>
			 			<nested:hidden property="m_jnmDisplayFlg" styleId="m_jnmDisplayFlg"/>
			 			<nested:hidden property="custPlanMPlanCurDifFlg" styleId="custPlanMPlanCurDifFlg"/>
 						<nested:hidden property="clickDifCurrencyYesFlg" styleId="clickDifCurrencyYesFlg"/>
 						<nested:hidden property="b_ssmIsUsed"/>
 						<nested:hidden property="fixedForexFlg" styleId="fixedForexFlg"/>
 						<!-- #189 Start -->
 						<nested:hidden property="disBillingOption" styleId="disBillingOption"/>
 						<!-- #189 End -->
						<!-- Plan header start -->
						<table cellpadding="0" cellspacing="0" width="100%">
							<colgroup>
								<col width="100%"/>
							</colgroup>
							<tr>
								<td>
									<div class="planHeaderInformation">
										<table class="informationPlan" cellpadding="0" cellspacing="0" width="100%">
										<colgroup>
											<col width="5%"/>
											<col width="35%"/>
											<col width="20%"/>
											<col width="17%"/>
											<col width="23%"/>
										</colgroup>
										<tr>
											<td class="header" nowrap="nowrap"><bean:message key="screen.b_cpm.label.transactionType"/></td>
											<td nowrap="nowrap">
												<bean:message key="screen.b_cpm.label_colon"/>
												<span style="color:gray;">
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
											 </td>
											 <td class="header" style="color:gray;"><bean:message key="screen.b_cpm.label.customerPlanId"/></td>
											 <td style="color:gray;">
											 	<bean:message key="screen.b_cpm.label_colon"/>
											    <nested:write property="idCustPlan"/>
											 </td>
											 <td rowspan="4" valign="top">
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
											<td style="width:150px;" nowrap="nowrap"><bean:message key="screen.b_cpm.label.billingInstruction"/></td>
											<td>
												<bean:message key="screen.b_cpm.label_colon"/>
												<logic:iterate id="INSTRUCT" name="BILL_INSTRUCTION_LIST">
												<nested:equal property="billingInstruction" value="${INSTRUCT.id}">
												<bean:write name="INSTRUCT" property="name"/>
												</nested:equal>
												</logic:iterate>
											</td>
											<td class="header"><bean:message key="screen.b_cpm.label.applicationDate"/></td>
											<td>	
												<bean:message key="screen.b_cpm.label_colon"/><nested:write property="applicationDate"/>
											</td>
										</tr>
										<tr>
											<td nowrap="nowrap" bgcolor="#FFCCFF" ><bean:message key="screen.b_cpm.label.billingAccNo"/></td>
											<td nowrap="nowrap" style="padding-top: 0px; padding-left: 0px; padding-bottom: 0px;">	
											<span style="background-color:#FFCCFF; margin: 0px; width: 280px;padding-top: 2px; padding-bottom: 2px; padding-left: 4px;" >
												<bean:message key="screen.b_cpm.label_colon"/>
												<nested:equal value="" property="billAccNo">
												<bean:message key="screen.b_cpm.label.empty"/>
												</nested:equal>
												<nested:notEqual value="" property="billAccNo">
												<c:choose>
													<c:when test="${'1' eq accessRightT_BAC or '2' eq accessRightT_BAC}">
														<html:link action="../B_BAC/RP_B_BAC_S02_02_01BL.do?idBillAccount=${CUSTOMER_PLAN.billAccNo}&idCustPlan=${CUSTOMER_PLAN.idCustPlan}&fromPage=B_CPM_S02V">
															<nested:write property="billAccNo"/>
														</html:link>
													</c:when>
													<c:otherwise>
														<nested:write property="billAccNo"/>
													</c:otherwise>
												</c:choose>
												</nested:notEqual>
												<nested:checkbox styleId="plan_newAcc" property="newAcc" style="display:none" value="NEW" onclick="javascript: inactiveCheckbox(this);"></nested:checkbox>
											</span>
											</td>
										</tr>
										<tr>
											<td class="header" bgcolor="#FFCCFF"><bean:message key="screen.b_cpm.label.billingCurency"/></td>
									    	<td style="padding-top: 0px; padding-left: 0px; padding-bottom: 0px;">
									    	<span style="background-color:#FFCCFF; margin: 0px; width: 280px;padding-top: 2px; padding-bottom: 2px; padding-left: 4px;" >
									    		<bean:message key="screen.b_cpm.label_colon"/>
									    		<logic:iterate id="CURRENCY" name="LIST_CURRENCY">
												<nested:equal property="billCurrency" value="${CURRENCY.id}">
												<bean:write name="CURRENCY" property="name"/>
												</nested:equal>
												</logic:iterate>
												<nested:hidden  styleId="plan_billingCurrency"  property="billCurrency"/>
											</span>
									    	</td>
										</tr>
										<tr>
											<td bgcolor="#FFCCFF"><bean:message key="screen.b_cpm.label.paymentMethod"/></td>
											<td style="padding-top: 0px; padding-left: 0px; padding-bottom: 0px;">
											<span style="background-color:#FFCCFF; margin: 0px; width: 280px;padding-top: 2px; padding-bottom: 2px; padding-left: 4px;" >
												<bean:message key="screen.b_cpm.label_colon"/>
												<logic:iterate id="PAYMENT" name="LIST_PAYMENT_METHOD">
												<nested:equal property="paymentMethod" value="${PAYMENT.id}">
												<bean:write name="PAYMENT" property="name"/>
												</nested:equal>
												</logic:iterate>
											</span>
											</td>
										</tr>
										<tr>
											<td bgcolor="#FFCCFF"><bean:message key="screen.b_cpm.label.paymentCurrency"/></td>
											<td style="padding-top: 0px; padding-left: 0px; padding-bottom: 0px;">
											<span style="background-color:#FFCCFF; margin: 0px; width: 280px;padding-top: 2px; padding-bottom: 2px; padding-left: 4px;" >
												<bean:message key="screen.b_cpm.label_colon"/>
												<nested:write property="expGrandTotal"/>
											</span>
											</td>
											<td nowrap="nowrap" colspan="3">
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
											</td>
										</tr>
										<tr>
										    <nested:equal value="1" property="fixedForexFlg">
												<td bgcolor="#FFCCFF"><bean:message key="screen.b_cpm.label.fixedForex"/></td>
												<td style="padding-top: 0px; padding-left: 0px; padding-bottom: 0px;">
												<span style="background-color:#FFCCFF; margin: 0px; width: 280px;padding-top: 2px; padding-bottom: 2px; padding-left: 4px;" >
													<bean:message key="screen.b_cpm.label_colon"/>
													<nested:write property="fixedForex"/>
												</span>
												</td>
											</nested:equal>
											<nested:notEqual value="1" property="fixedForexFlg">
												<td>&nbsp;</td>
												<td>
													&nbsp;
												</td>
											</nested:notEqual>
											<td nowrap="nowrap" colspan="3">
												<nested:equal property="b_ssmIsUsed" value="1">
												    <c:choose>
													    <c:when test="${'1' eq accessRightB_SSM or '2' eq accessRightB_SSM}">
															<a href="#" onclick="javascript: popupB_CPM_S07('${pageContext.request.contextPath}/B_CPM/B_CPM_S07SCR.do?idCustPlan=${CUSTOMER_PLAN.idCustPlan}&idSubInfo=${CUSTOMER_PLAN.idSubInfo}');">
																<bean:message key="screen.b_cpm.s07.link.name" />
															</a>
														</c:when>
														<c:otherwise>
															&nbsp;
														</c:otherwise>
													</c:choose>
												</nested:equal>
												<nested:notEqual property="b_ssmIsUsed" value="1">
													&nbsp;
												</nested:notEqual>
											</td>
										</tr>
										<nested:equal value="1" property="taxTypeDisplay">
										<tr>
											<td class="header" bgcolor="#FFCCFF"><bean:message key="screen.b_cpm.label.taxtype"/></td>
									    	<td style="padding-top: 0px; padding-left: 0px; padding-bottom: 0px;">
									    	<span style="background-color:#FFCCFF; margin: 0px; width: 280px;padding-top: 2px; padding-bottom: 2px; padding-left: 4px;" >
									    		<bean:message key="screen.b_cpm.label_colon"/>
									    		<logic:iterate id="TAXTYPE" name="LIST_TAX_TYPE">
												<nested:equal property="taxType" value="${TAXTYPE.id}">
													<bean:write name="TAXTYPE" property="name"/>
												</nested:equal>
												</logic:iterate>
												<nested:hidden  styleId="taxType"  property="taxType"/>
											</span>
									    	</td>
										</tr>
										</nested:equal>
										</table>
									</div><!-- Plan header end -->
								</td>
							</tr>
						</table>
						<table id="planDetail" class="customerPlanInformation" style="background-color:white;" cellspacing="0" cellpadding="0">
						<colgroup>
							<col width="70%"/>
							<col width="10%"/>
							<col width="10%"/>
							<col width="10%"/>
						</colgroup>
							<tr>
								<td colspan="4" id="service_group" nowrap="nowrap">
									<nested:iterate id="service" property="services" indexId="i">
									<%int n=0;%>
									<div class="service_object">
									<table cellspacing="0" style="width:100%;border-top: solid 2px #8cb0f8;border-bottom: solid 2px #8cb0f8;border-left: solid 2px #8cb0f8;border-right: solid 2px #8cb0f8;">
										<colgroup>
											<col width="2%"/>
											<col width="10%"/>
											<col width="88%"/>
										</colgroup>
										<tr>
											<td colspan="2" style="width:10%;text-align:left;background-color:#8cb0f8">
												<div style="font-weight: bold;color:#000080;">
													<bean:message key="screen.b_cpm.label.service"/> ${i+1}<input type="hidden" value="${i+1}" id="serviceNo"/>
												</div>
											</td>
											<td style="width:87%;text-align:right;background-color:white;">
												<nested:hidden property="idCustPlanGrp" styleClass="idCustPlanGrp"/>
												<nested:hidden property="serviceStatus" styleClass="serviceStatus"/>
												<nested:hidden property="serviceMultiPln" styleClass="serviceMultiPln"/>
												<nested:hidden property="serviceIdPlan" styleClass="serviceIdPlan"/>
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
														<col width="25%"/>
														<col width="20%"/>
													</colgroup>
													<tr  class="serviceTable">
														<td nowrap="nowrap"><bean:message key="screen.b_cpm.header.servicePeriod"/></td>
														<td nowrap="nowrap">
															<bean:message key="screen.b_cpm.label_colon"/>
															<bean:message key="screen.b_cpm.label.from"/>
															<nested:write property="serviceDateStart"/>
															<nested:hidden property="serviceDateStart" styleClass="serviceDateStart"/>
														</td>
														<td nowrap="nowrap">
															<bean:message key="screen.b_cpm.label.to"/>
															
															<nested:write property="serviceDateEnd"/>
														</td>
														<td nowrap="nowrap"><nested:checkbox property="autoRenewal" value="1" onclick="javascript: inactiveCheckbox(this);"/><bean:message key="screen.b_cpm.label.autoRenewal"/></td>
														<td rowspan="2" colspan="2" nowrap="nowrap" valign="top" style="text-align: right">
															<div class="<t:writeCodeValue codeList="COLOR_CODE" key="${service.serviceStatus}"></t:writeCodeValue>">
																<h4><bean:message key="screen.b_cpm.label.status"/></h4>
																<h4>
																	<t:writeCodeValue codeList="LIST_PLANSTATUS" key="${service.serviceStatus}"/>
																</h4>
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
																		<col width="26.5%"/>
																		<col width="30%"/>
																		<col width="43.5%"/>
																	</colgroup>
																	<tr>
																		<td nowrap="nowrap"><nested:checkbox styleId="chkMinimumService" property="minimumService" onclick="javascript: inactiveCheckbox(this);" value="1"/></td>
																		<td nowrap="nowrap">
																			<bean:message key="screen.b_cpm.label.from"/>
																			<nested:write property="minimumServiceFrom"/>
																		</td>
																		<td nowrap="nowrap">
																			<bean:message key="screen.b_cpm.label.to"/>
																			<nested:write property="minimumServiceTo"/>
																			<nested:hidden property="minimumServiceTo" styleId="minimumServiceTo"/>
																		</td>
																	</tr>
																	<tr>
																		<td><bean:message key="screen.b_cpm.label.contractTerm"/></td>
																		<td colspan="2">
																			<span style="width:125px;">
																				<bean:message key="screen.b_cpm.label_colon"/>
																				<nested:write property="contactTermNo"/>
																				<nested:radio property="contactTerm" value="M" styleClass="contactTerm"/><bean:message key="screen.b_cpm.label.months"/>
																			</span>
																			<nested:radio property="contactTerm" value="Y" styleClass="contactTerm"/><bean:message key="screen.b_cpm.label.years"/>
																		</td>
																	</tr>
																</table>
															</fieldset>
														</td>
														<td>
														</td>
														<td>
														</td>
													</tr>
													<tr>
														<td nowrap="nowrap"><bean:message key="screen.b_cpm.label.proRateBaseOn"/></td>
														<td colspan="3" nowrap="nowrap">
															<bean:message key="screen.b_cpm.label_colon"/>
															<span style="width:110px;">
																<nested:radio property="proRateBase" styleClass="proRateBase" value="S"/><bean:message key="screen.b_cpm.button.sysdate"/>
															</span>
															<nested:radio property="proRateBase" styleClass="proRateBase" value="U"/><bean:message key="screen.b_cpm.button.userDefine"/>
															<nested:empty property="proRateBaseNo">
																-
															</nested:empty>
															<nested:notEmpty property="proRateBaseNo">
																<nested:write property="proRateBaseNo"/>
															</nested:notEmpty>
															&nbsp;<bean:message key="screen.b_cpm.label.daysPerMonth"/>
														</td>
														<td colspan="2" nowrap="nowrap">
									    					<div style="color: blue;">
															    <bean:message key="screen.b_cpm.label.billingStatus"/>
															    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="screen.b_cpm.label_colon"/>&nbsp;
															    <t:writeCodeValue codeList="LIST_BILLINGSTATUS" key="${service.billingStatus}"/>

														    </div>
														    <div style="color: blue;padding-left: 0px;text-align: left;">
															    <bean:message key="screen.b_cpm.label.CompletionDate"/>
															    &nbsp;<bean:message key="screen.b_cpm.label_colon"/>&nbsp;														   
		    											        <nested:empty property="completionDate">
			    											            --/--/----
			    											        </nested:empty>
			    											        <nested:equal value="-" property="completionDate">
				    											        	--/--/----
				    											        </nested:equal>
			    											        <nested:notEmpty property="completionDate">
			    											        	<nested:notEqual value="-" property="completionDate">
			    											        		<nested:write property="completionDate"/>
			    											        	</nested:notEqual>
			    											        </nested:notEmpty>
														    </div>
														</td>
													</tr>
													<tr>
														<td nowrap="nowrap"><bean:message key="screen.b_cpm.label.billingAmount"/></td>
														<td colspan="4" nowrap="nowrap">
															<bean:message key="screen.b_cpm.label_colon"/>
															<span style="width:110px;">
																<nested:radio property="isLumpSum" styleClass="isLumpSum" value="0"/><bean:message key="screen.b_cpm.label.itemised1"/>
															</span>
															<nested:radio property="isLumpSum" styleClass="isLumpSum" value="1"/><bean:message key="screen.b_cpm.label.lumpSum1"/>
														</td>
														<td nowrap="nowrap">
														</td>
													</tr>
													<tr>
														<td nowrap="nowrap"><bean:message key="screen.b_cpm.label.Discount"/></td>
														<td colspan="4" nowrap="nowrap" class="discountDetail">
															<bean:message key="screen.b_cpm.label_colon"/>
															<span style="width:110px;">
																<nested:radio property="isDiscountLumpSum" styleClass="isDiscountLumpSum" value="0" /><bean:message key="screen.b_cpm.label.itemised1"/>
															</span>
																<nested:radio property="isDiscountLumpSum" styleClass="isDiscountLumpSum" value="1" /><bean:message key="screen.b_cpm.label.lumpSum1"/>
														</td>
													</tr>
													<!-- #189 Start -->
													<tr id="trBillingOption">
														<td nowrap="nowrap"><bean:message key="screen.b_cpm.label.billingOption"/></td>
														<td colspan="4" nowrap="nowrap" class="billingOptionDetail">
															<bean:message key="screen.b_cpm.label_colon"/>
															<span style="width:110px;">
																	<nested:radio property="billingOption" styleClass="billingOption" value="1"/><bean:message key="screen.b_cpm.label.prepaid"/>
															</span>
															<nested:radio property="billingOption" styleClass="billingOption" value="2"/><bean:message key="screen.b_cpm.label.postpaid"/>
														</td>
													</tr>
													<!-- #189 End -->													
													<tr>
														<td colspan="6">
															<!-- Service Detail -->
															<table width="100%" cellpadding="0" cellspacing="0">
																<colgroup>
																	<col width="64%"/>
																	<col width="10%"/>
																	<col width="10%"/>
																	<col width="14%"/>
																	<col width="2%"/>
																</colgroup>
																<tr style="background-color:#8cb0f8">
																	<td class="customerPlanHeaderItem" nowrap="nowrap" style="text-align:left;"><bean:message key="screen.b_cpm.label.billingDesc"/></td>
																	<td class="customerPlanHeaderItem" nowrap="nowrap" style="text-align:right;"><bean:message key="screen.b_cpm.label.quantity"/></td>
																	<td class="customerPlanHeaderItem" nowrap="nowrap" style="text-align:right;"><bean:message key="screen.b_cpm.label.unitPrice"/></td>
																	<td class="customerPlanHeaderItem" nowrap="nowrap" style="text-align:right;"><bean:message key="screen.b_cpm.label.amount"/></td>
																	<td></td>
																</tr>
																<tr>
																	<td nowrap="nowrap" style="text-align:left;padding-left:0px;">
																		<nested:textarea property="billDesc" styleClass="billDesc" style="overflow-y:visible;height:40px;" readonly="readonly"/>
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
																				<nested:write property="custPo"/>
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
											<!-- sub plan -->
											<td colspan="3" class="subplan" style="background-color:#e7efff;">
												<nested:iterate id="subPlan" property="subPlans" indexId="j">
												<logic:equal name="subPlan" property="itemType" value="S">
												<%n=n+1;%>
												<div class="subPlan">
												<fieldset>
													<legend>
														<div class="subPlanLabel" style="font-weight: bold;color:#4876FF;">[<span id="planNo"><%=n%></span>][<bean:message key="screen.b_cpm.label.subPlan"/>]
														<logic:equal value="SP" name="CUSTOMER_PLAN" property="planType">
														[<nested:write property="planName"/>]
														[<nested:write property="planDesc"/>]
														[<nested:write property="itemGrpName"/>]
														</logic:equal>
														</div>	
													</legend>
													<nested:hidden property="idCustPlanLink" styleClass="idCustPlanLink"/>
													<nested:hidden property="itemType" styleClass="itemType"/>
													<nested:hidden property="idPlan" styleClass="idPlan"/>
													<nested:hidden property="currency" styleClass="currency"/>
													<nested:hidden property="isDisplayJobNo" styleClass="isDisplayJobNo"/>
													<nested:hidden property="svcLevel2" styleClass="svcLevel2"/>
													<table width="100%" cellpadding="0" cellspacing="0">
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
														<logic:equal value="NP" name="CUSTOMER_PLAN" property="planType">
														<tr>
															<td>
																
															</td>
															<td nowrap="nowrap"><bean:message key="screen.b_cpm.label.serviceName"/></td>
															<td nowrap="nowrap">
																<bean:message key="screen.b_cpm.label_colon"/>
																<nested:write property="planDesc" />
															</td>
															<td colspan="3">&nbsp;</td>
														</tr>
														</logic:equal>
														<tr>
															<td>&nbsp;</td>
															<td nowrap="nowrap" style="width:33%;"><bean:message key="screen.b_cpm.label.itemDescription"/></td>
															<td nowrap="nowrap">
															<logic:equal value="1" property="customerPlan.masterLocationDisplayFlg" name="_B_CPM_S02Form">
																<bean:message key="screen.b_cpm.label.masterLocation"/>
										        				<bean:message key="screen.b_cpm.label.colon"/>
										        				&nbsp;&nbsp;										        				
										        				<nested:equal property="subLocation" value="">
										        					-
										        				</nested:equal>
										        				<nested:notEqual property="subLocation" value="">
										        					<nested:write property="subLocation"/>
										        				</nested:notEqual>
										        			</logic:equal>
															</td>
															<td nowrap="nowrap" style="text-align:right;padding-right:0px">
															<logic:equal value="1" property="customerPlan.m_jnmDisplayFlg" name="_B_CPM_S02Form">
																<nested:checkbox property="isDisplayJobNo" styleClass="isDisplayJobNo" value="1" disabled="true"/><bean:message key="screen.b_cpm.label.jobNo"/>
																&nbsp;&nbsp;&nbsp;&nbsp;
																<bean:message key="screen.b_cpm.label_colon"/>
																<nested:write property="jobNo"/>
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
																<nested:textarea property="itemDesc" styleClass="itemDesc" style="overflow-y:visible;height:40px;" readonly="readonly"/>
															</td>
															<td colspan="4" valign="top">
																<table width="100%">
																	<colgroup>
																		<col width="27%">
																		<col width="30%">
																		<col width="35%">
																		<col width="5%">
																	</colgroup>
																	<tr>
																		<td valign="top" class="numberStyle"><div class="subPlanQuantity"><nested:write property="quantity"/></div></td>
																		<td valign="top" class="numberStyle"><div class="subPlanUnitPrice"><nested:write property="unitPrice"/></div></td>
																		<td valign="top" class="numberStyle"><div class="subPlanAmount"><nested:write property="amount"/></div></td>
																		<td>&nbsp;</td>
																	</tr>
																	<tr>
																		<td class="serviceDiscount"><bean:message key="screen.b_cpm.label.Discount"/><bean:message key="screen.b_cpm.label_colon"/></td>
																		<td valign="top" nowrap="nowrap" class="numberStyle"><nested:radio property="isDiscountOneTime" styleClass="isDiscountOneTime" value="O"/><bean:message key="screen.b_cpm.label.onetime"/></td>
																		<td><div class="subDiscount"><nested:write property="discamount"/></div></td>
																		<td></td>
																	</tr>
																	<tr>
																		<td></td>
																		<td valign="top" nowrap="nowrap" class="numberStyle"><nested:radio property="isDiscountOneTime" styleClass="isDiscountOneTime" value="R" /><bean:message key="screen.b_cpm.label.recurring"/></td>
																		<td></td>
																		<td></td>
																	</tr>
																</table>
															</td>
														</tr>
														<!-- sub plan header -->
														<tr>
															<td>&nbsp;</td>
															<td>
																<span class="spanToggleClick">...
																</span>
															</td>
															<td nowrap="nowrap" class="billAcountCode">
															<bean:message key="screen.b_cpm.label.billingAccCode"/>
															<bean:message key="screen.b_cpm.label_colon"/>
															</td>
															<td class="billAcountCode">
																<div style="word-break:break-all;">
																<logic:iterate id="LEVEL1" name="LOAD_OBJECT" property="SVC_LEVEL1">
																<nested:equal property="svcLevel1" value="${LEVEL1.SVC_GRP}">
																<bean:write name="LEVEL1" property="SVC_GRP_NAME"/>
																</nested:equal>
																</logic:iterate>
																&nbsp;<bean:message key="screen.b_cpm.label.empty"/>&nbsp;
																<logic:iterate id="LEVEL2" name="LOAD_OBJECT" property="SVC_LEVEL2">
																<nested:equal property="svcLevel2" value="${LEVEL2.ID_SERVICE}">
																<bean:write name="LEVEL2" property="SVC_NAME"/>
																</nested:equal>
																</logic:iterate>
																</div>
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
																<table width="100%">
																	<colgroup>
																		<col width="60%"/>
																		<col width="40%"/>
																	</colgroup>
																	<tr>
																		<td valign="top">
																			<!-- Sub plan detail group -->
																			<table class="subPlanDetail" width="100%">
																				<colgroup>
																					<col width="70%"/>
																					<col width="30%"/>
																				</colgroup>
																				<tr class="subPlanDetailheader">
																					<td>
																						<bean:message key="screen.b_cpm.label.plan"/><br>
																						<bean:message key="screen.b_cpm.label.planDetail"/>
																					</td>
																					<td>
																						<bean:message key="screen.b_cpm.label.vendor"/>
																					</td>
																				</tr>
																				<nested:iterate property="subPlanDetails" id="subPlanDetail" indexId="k">
																				<tr class="clonePlanDetail">
																					<td valign="top">
																						<nested:hidden property="svcLevel3" styleClass="svcLevel3"/>
																						<nested:hidden property="idCustPlanSvc" styleClass="idCustPlanSvc"/>
																						<div style="word-break:break-all;">
																						<logic:iterate id="LEVEL3" name="LOAD_OBJECT" property="SVC_LEVEL3">
																						<nested:equal property="svcLevel3" value="${LEVEL3.ID_SERVICE}">
																						<bean:write name="LEVEL3" property="SVC_NAME"/>
																						</nested:equal>
																						</logic:iterate><br/>
																						<logic:iterate id="LEVEL4" name="LOAD_OBJECT" property="SVC_LEVEL4">
																						<nested:equal property="svcLevel4" value="${LEVEL4.ID_SERVICE}">
																						<bean:write name="LEVEL4" property="SVC_NAME"/>
																						</nested:equal>
																						</logic:iterate>
																						</div>																									
																					</td>
																					<td valign="top">
																						<logic:iterate id="VENDOR" name="LOAD_OBJECT" property="VENDOR">
																						<nested:equal property="vendor" value="${VENDOR.ID_SUPPLIER}">
																						<bean:write name="VENDOR" property="SUPPLIER_NAME"/>
																						</nested:equal>
																						</logic:iterate><br/>
																						<div class="lineDesc" style="word-break:break-all">
																							<bean:message key="screen.b_cpm.label.lineDesc"/>
																							<bean:message key="screen.b_cpm.label.colon"/>
																							<span id="lineDescVal">
																								<bean:message key="screen.b_cpm.label.empty"/>
																							</span> 
																						</div>
																					</td>
																				</tr>
																				</nested:iterate>
																				<!--if subPlanDetails is null start-->
																				<nested:empty property="subPlanDetails">
																				<tr class="clonePlanDetail">
																					<td valign="top" class="subPlanDetailResult">
																					</td>
																					<td valign="top" class="subPlanDetailResult">
																						<br/>
																						<div class="lineDesc" style="word-break:break-all">
																							<bean:message key="screen.b_cpm.label.lineDesc"/>
																							<bean:message key="screen.b_cpm.label.colon"/>
																							<span id="lineDescVal">
																								<bean:message key="screen.b_cpm.label.empty"/>
																							</span> 
																						</div>
																					</td>
																				</tr>
																				</nested:empty>
																				<!--if subPlanDetails is null end-->
																			</table>
																		</td>
																		<td valign="top">
																			<!-- Sub plan rate group -->
																			<table width="100%" class="blankTable">
																			<colgroup>
																				<col width="30%">
																				<col width="30%">
																				<col width="40%">
																			</colgroup>
																			<tr class="subPlanDetailHeader">
																				<td>
																					<bean:message key="screen.b_cpm.label.rate"/><br>
																					<bean:message key="screen.b_cpm.label.rateType"/>
																				</td>
																				<td>
																					<bean:message key="screen.b_cpm.label.rate"/><br>
																					<bean:message key="screen.b_cpm.label.ratemode"/>
																				</td>
																				<td>
																					<bean:write name="CUSTOMER_PLAN" property="taxWord"/>
																					<bean:message key="screen.b_cpm.label_percentsymbol"/>&nbsp;
																				</td>
																			</tr>
																			<tr class="subPlanDetailResult">
																				<td rowspan="2" nowrap="nowrap">
																					<logic:iterate id="RATE_TYPE" name="LIST_RATETYPE">
																						<nested:equal property="rateType" value="${RATE_TYPE.id}">
																						<bean:write name="RATE_TYPE" property="name"/>
																						</nested:equal>
																					</logic:iterate>
																					<br/>
																					<logic:iterate id="RATE_TYPE2" name="LIST_RATETYPE2">
																						<nested:equal property="rateType2" value="${RATE_TYPE2.id}">
																						<bean:write name="RATE_TYPE2" property="name"/>
																						</nested:equal>
																					</logic:iterate>
																				</td>
																				<td nowrap="nowrap">
																					<logic:iterate id="MODE" name="MODE_LIST">
																						<nested:equal property="rateMode" value="${MODE.id}">
																						<bean:write name="MODE" property="name"/>
																						</nested:equal>
																					</logic:iterate>
																				</td>
																				<td nowrap="nowrap">
																				<logic:iterate id="GST" name="LIST_GST">
																					<nested:equal property="applyGst" value="${GST.id}">
																					<bean:write name="GST" property="name"/>
																					</nested:equal>
																				</logic:iterate>
																				</td>
																			</tr>
																			<tr class="subPlanDetailResult">
																				<td colspan="2" nowrap="nowrap" class="detailGst">
																					<div  style="font-style:italic;"><bean:message key="screen.b_cpm.label.rate"/>
																					<span class="displayCurrency">(<nested:write property="currency"/>)</span>:&nbsp;&nbsp;&nbsp;
																					<nested:write  property="rate"/>
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
														<!-- end sub plan detail -->
													</table>
												</fieldset>
												</div><!-- End Sub Plan -->
												</logic:equal>
												</nested:iterate>
											</td>
											<!-- End sub plan -->
										</tr>
										<tr>
											<!-- option service -->
											<td colspan="3" class="option_service" style="background-color:#e7efff;">
												<nested:iterate id="subPlan" property="subPlans" indexId="j">
												<logic:equal name="subPlan" property="itemType" value="O">
												<%n=n+1;%>
												<div class="subPlan">
												<fieldset>
													<legend>
														<div class="optionServiceLabel" style="font-weight: bold;color:#4876FF;">[<span id="planNo"><%=n%></span>][<bean:message key="screen.b_cpm.label.optionService"/>]
														<logic:equal value="SP" name="CUSTOMER_PLAN" property="planType">
														[<nested:write property="planName"/>]
														[<nested:write property="planDesc"/>]
														[<nested:write property="itemGrpName"/>]
														</logic:equal>
														</div>	
													</legend>
													<nested:hidden property="idCustPlanLink" styleClass="idCustPlanLink"/>
													<nested:hidden property="itemType" styleClass="itemType"/>
													<nested:hidden property="idPlan" styleClass="idPlan"/>
													<nested:hidden property="currency" styleClass="currency"/>
													<nested:hidden property="isDisplayJobNo" styleClass="isDisplayJobNo"/>
													<nested:hidden property="svcLevel2" styleClass="svcLevel2"/>
													<table width="100%" cellpadding="0" cellspacing="0">
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
														<logic:equal value="NP" name="CUSTOMER_PLAN" property="planType">
														<tr>
															<td>
																
															</td>
															<td nowrap="nowrap"><bean:message key="screen.b_cpm.label.serviceName"/></td>
															<td nowrap="nowrap">
																<bean:message key="screen.b_cpm.label_colon"/>
																<nested:write property="planDesc" />
															</td>
															<td colspan="3">&nbsp;</td>
														</tr>
														</logic:equal>
														<tr>
															<td>&nbsp;</td>
															<td nowrap="nowrap" style="width:33%;"><bean:message key="screen.b_cpm.label.itemDescription"/></td>
															<td nowrap="nowrap">
															<logic:equal value="1" property="customerPlan.masterLocationDisplayFlg" name="_B_CPM_S02Form">
																<bean:message key="screen.b_cpm.label.masterLocation"/>
										        				<bean:message key="screen.b_cpm.label.colon"/>
										        				&nbsp;&nbsp;										        				
										        				<nested:equal property="subLocation" value="">
										        					-
										        				</nested:equal>
										        				<nested:notEqual property="subLocation" value="">
										        					<nested:write property="subLocation"/>
										        				</nested:notEqual>
										        			</logic:equal>
															</td>
															<td nowrap="nowrap" style="text-align:right;padding-right:0px">
																<logic:equal value="1" property="customerPlan.m_jnmDisplayFlg" name="_B_CPM_S02Form">
																<nested:checkbox property="isDisplayJobNo" styleClass="isDisplayJobNo" value="1" disabled="true"/><bean:message key="screen.b_cpm.label.jobNo"/>
																	&nbsp;&nbsp;&nbsp;&nbsp;
																<bean:message key="screen.b_cpm.label_colon"/>
																<nested:write property="jobNo"/>
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
																<nested:textarea property="itemDesc" styleClass="itemDesc" style="overflow-y:visible;height:40px;" readonly="readonly"/>
															</td>
															<td colspan="4" valign="top">
															<table width="100%">
																<colgroup>
																	<col width="27%">
																	<col width="30%">
																	<col width="35%">
																	<col width="5%">
																</colgroup>
																<tr>
																	<td valign="top" class="numberStyle"><div class="subPlanQuantity"><nested:write property="quantity"/></div></td>
																	<td valign="top" class="numberStyle"><div class="subPlanUnitPrice"><nested:write property="unitPrice"/></div></td>
																	<td valign="top" class="numberStyle"><div class="subPlanAmount"><nested:write property="amount"/></div></td>
																	<td>&nbsp;</td>
																</tr>
																<tr>
																	<td class="serviceDiscount"><bean:message key="screen.b_cpm.label.Discount"/><bean:message key="screen.b_cpm.label_colon"/></td>
																	<td valign="top" nowrap="nowrap" class="numberStyle"><nested:radio property="isDiscountOneTime" styleClass="isDiscountOneTime" value="O"/><bean:message key="screen.b_cpm.label.onetime"/></td>
																	<td><div class="subDiscount"><nested:write property="discamount"/></div></td>
																	<td></td>
																</tr>
																<tr>
																	<td></td>
																	<td valign="top" nowrap="nowrap" class="numberStyle"><nested:radio property="isDiscountOneTime" styleClass="isDiscountOneTime" value="R" /><bean:message key="screen.b_cpm.label.recurring"/></td>
																	<td></td>
																	<td></td>
																</tr>
															</table>
															</td>
														</tr>
														<!-- sub plan header -->
														
														<tr>
															<td>&nbsp;</td>
															<td>
																<span class="spanToggleClick">...
																</span>
															</td>
															<td nowrap="nowrap" class="billAcountCode">
																<bean:message key="screen.b_cpm.label.billingAccCode"/>
																<bean:message key="screen.b_cpm.label_colon"/>
															</td>
															<td class="billAcountCode">
																<div style="word-break:break-all;">
																<logic:iterate id="LEVEL1" name="LOAD_OBJECT" property="SVC_LEVEL1">
																<nested:equal property="svcLevel1" value="${LEVEL1.SVC_GRP}">
																<bean:write name="LEVEL1" property="SVC_GRP_NAME"/>
																</nested:equal>
																</logic:iterate>
																&nbsp;<bean:message key="screen.b_cpm.label.empty"/>&nbsp;
																<logic:iterate id="LEVEL2" name="LOAD_OBJECT" property="SVC_LEVEL2">
																<nested:equal property="svcLevel2" value="${LEVEL2.ID_SERVICE}">
																<bean:write name="LEVEL2" property="SVC_NAME"/>
																</nested:equal>
																</logic:iterate>
																</div>
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
																<table width="100%">
																	<colgroup>
																		<col width="60%"/>
																		<col width="40%"/>
																	</colgroup>
																	<tr>
																		<td valign="top">
																			<!-- Sub plan detail group -->
																			<table class="subPlanDetail" width="100%">
																				<colgroup>
																					<col width="70%"/>
																					<col width="30%"/>
																				</colgroup>
																				<tr class="subPlanDetailHeader">
																					<td>
																						<bean:message key="screen.b_cpm.label.plan"/><br>
																						<bean:message key="screen.b_cpm.label.planDetail"/>
																					</td>
																					<td>
																						<bean:message key="screen.b_cpm.label.vendor"/>
																					</td>
																				</tr>
																				<nested:iterate property="subPlanDetails" id="subPlanDetail" indexId="k">
																				<tr class="clonePlanDetail">
																					<td valign="top" class="subPlanDetailResult">
																						<nested:hidden property="svcLevel3" styleClass="svcLevel3"/>
																						<nested:hidden property="idCustPlanSvc" styleClass="idCustPlanSvc"/>
																						<div style="word-break:break-all;">
																						<logic:iterate id="LEVEL3" name="LOAD_OBJECT" property="SVC_LEVEL3">
																						<nested:equal property="svcLevel3" value="${LEVEL3.ID_SERVICE}">
																						<bean:write name="LEVEL3" property="SVC_NAME"/>
																						</nested:equal>
																						</logic:iterate><br/>
																						<logic:iterate id="LEVEL4" name="LOAD_OBJECT" property="SVC_LEVEL4">
																						<nested:equal property="svcLevel4" value="${LEVEL4.ID_SERVICE}">
																						<bean:write name="LEVEL4" property="SVC_NAME"/>
																						</nested:equal>
																						</logic:iterate>
																						</div>																								
																					</td>
																					<td valign="top" class="subPlanDetailResult">
																						<logic:iterate id="VENDOR" name="LOAD_OBJECT" property="VENDOR">
																						<nested:equal property="vendor" value="${VENDOR.ID_SUPPLIER}">
																						<bean:write name="VENDOR" property="SUPPLIER_NAME"/>
																						</nested:equal>
																						</logic:iterate><br/>
																						<div class="lineDesc" style="word-break:break-all">
																							<bean:message key="screen.b_cpm.label.lineDesc"/>
																							<bean:message key="screen.b_cpm.label.colon"/>
																							<span id="lineDescVal">
																								<bean:message key="screen.b_cpm.label.empty"/>
																							</span> 
																						</div>
																					</td>
																				</tr>
																				</nested:iterate>
																				<!--if subPlanDetails is null start-->
																				<nested:empty property="subPlanDetails">
																				<tr class="clonePlanDetail">
																					<td valign="top" class="subPlanDetailResult">
																					</td>
																					<td valign="top" class="subPlanDetailResult">
																						<br/>
																						<div class="lineDesc" style="word-break:break-all">
																							<bean:message key="screen.b_cpm.label.lineDesc"/>
																							<bean:message key="screen.b_cpm.label.colon"/>
																							<span id="lineDescVal">
																								<bean:message key="screen.b_cpm.label.empty"/>
																							</span> 
																						</div>
																					</td>
																				</tr>
																				</nested:empty>
																				<!--if subPlanDetails is null end-->
																			</table>
																		</td>
																		<td valign="top">
																			<!-- Sub plan rate group -->
																			<table width="100%" class="blankTable">
																			<colgroup>
																				<col width="30%">
																				<col width="30%">
																				<col width="40%">
																			</colgroup>
																			<tr class="subPlanDetailHeader">
																				<td>
																					<bean:message key="screen.b_cpm.label.rate"/><br>
																					<bean:message key="screen.b_cpm.label.rateType"/>
																				</td>
																				<td>
																					<bean:message key="screen.b_cpm.label.rate"/><br>
																					<bean:message key="screen.b_cpm.label.ratemode"/>
																				</td>
																				<td>
																					
																					<bean:write name="CUSTOMER_PLAN" property="taxWord"/>
																					<bean:message key="screen.b_cpm.label_percentsymbol"/>&nbsp;
																				</td>
																			</tr>
																			<tr class="subPlanDetailResult">
																				<td rowspan="2" nowrap="nowrap">
																					<logic:iterate id="RATE_TYPE" name="LIST_RATETYPE">
																						<nested:equal property="rateType" value="${RATE_TYPE.id}">
																						<bean:write name="RATE_TYPE" property="name"/>
																						</nested:equal>
																					</logic:iterate>
																					<br/>
																					<logic:iterate id="RATE_TYPE2" name="LIST_RATETYPE2">
																						<nested:equal property="rateType2" value="${RATE_TYPE2.id}">
																						<bean:write name="RATE_TYPE2" property="name"/>
																						</nested:equal>
																					</logic:iterate>
																				</td>
																				<td nowrap="nowrap">
																					<logic:iterate id="MODE" name="MODE_LIST">
																						<nested:equal property="rateMode" value="${MODE.id}">
																						<bean:write name="MODE" property="name"/>
																						</nested:equal>
																					</logic:iterate>
																				</td>
																				<td nowrap="nowrap">
																					<logic:iterate id="GST" name="LIST_GST">
																						<nested:equal property="applyGst" value="${GST.id}">
																						<bean:write name="GST" property="name"/>
																						</nested:equal>
																					</logic:iterate>
																				</td>
																			</tr>
																			<tr class="subPlanDetailResult">
																				<td colspan="2" nowrap="nowrap" class="detailGst">
																					<div  style="font-style:italic;"><bean:message key="screen.b_cpm.label.rate"/>
																					<span class="displayCurrency">(<nested:write property="currency"/>)</span>:&nbsp;&nbsp;&nbsp;
																					<nested:write  property="rate"/>
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
														<!-- end sub plan detail -->
													</table>
												</fieldset>
												</div><!-- End Sub Plan -->
												</logic:equal>
												</nested:iterate>
											</td>
											<!-- End option service -->
										</tr>
									</table>
									<br/>
									</div><!-- End Service -->
									</nested:iterate>
								</td>
							</tr>
						</table>
					</nested:nest>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>
	    		<div class="bottomButtons">
		    	<button id="btnExit" onclick="javascript: exit();"><bean:message key="screen.b_cpm.button.exit"/></button>
		    	</div>
	    		</td>
	    	</tr>
	    	</table>
<!-- message display start -->
<div style="color:#0046D5;padding-left:20px;padding-top:0px;">
	<html:messages id="message" message="true">
		<bean:write name="message"/>
	</html:messages>
</div>
<div style="color:red;padding-left:20px;padding-top:0px;">
	<html:messages id="message">
		<bean:write name="message"/><br/>
	</html:messages>
</div>
<div id="svcLevelGroup" style="display: none">
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
</div>
<!-- message display end -->
		</ts:form>
<!-- message display end -->
	</body>
</html:html>