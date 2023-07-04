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
   		<script type="text/javascript" src="js/b_cpm_common.js"></script>
   		<script type="text/javascript" src="js/b_cpm_en_s02v.js"></script>
   		
   		<link rel="stylesheet" type="text/css" href="css/b_cpm.css"/>
   		<link rel="stylesheet" type="text/css" href="css/b_cpm_s02v.css"/>
   		<link rel="stylesheet" type="text/css" href="css/b_cpm_s02_common.css"/>
		<title></title>
	</head>
	<body onload="javascript: initLoad();">
		<!-- check access right START -->
	<%
	 	BillingSystemUserValueObject uvo = (BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT");
	 	String accessRight = CommonUtils.getAccessRight(uvo, "B-CPM");
	 	String accessRightE2 = CommonUtils.getAccessRight(uvo, "B-CPM-E2");
	 	String accessRightT_BACBean = CommonUtils.getAccessRight(uvo, "B-BAC");
	 	String accessRightM_CST_BIBean = CommonUtils.getAccessRight(uvo, "M-CST-BI");
	 	String accessRightB_SSMBean = CommonUtils.getAccessRight(uvo, "B-SSM");
	 %>
	 <bean:define id="accessRightBean" value="<%= accessRight%>"/>
	 <bean:define id="accessE2" value="<%= accessRightE2%>"/>
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
 			<html:hidden property="accessSubmit" name="_B_CPM_S02Form"/>
 			<html:hidden property="action" styleId="action" value="edit"/> 			
 			<input type="hidden" id="idService" name="idService"/>
 			<html:hidden property="idScreen" styleId="idScreen"/>
 			<table width="100%">
 			<tr>
 				<td>
		 			<table class="subHeader" cellpadding="0" cellspacing="0">
		 				<tr>
			    			<td>
			    			<nested:nest property="customerPlan">
			    				<nested:equal property="screen" value="2">
			    					<bean:message key="screen.b_cpm.header"/>
			    				</nested:equal>
			    				<nested:equal property="screen" value="5">
			    					<bean:message key="screen.b_cpm.s05.header"/>
			    					<bean:write name="_B_CPM_S02Form" property="idScreen"/> 
			    					<t:writeCodeValue codeList="LIST_TRANSACTIONTYPE" key="${CUSTOMER_PLAN.transactionType}" name="CUSTOMER_PLAN" property="transactionType"/>
			    				</nested:equal>
			    			</nested:nest>
			    			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    			</td>
			    		</tr>
			    	</table>
			    	<bean:define id="CUSTOMER_INFO" name="LOAD_OBJECT" property="CUSTOMER_INFO"></bean:define>
			    	<table class="customerInformation">
			    		<nested:nest property="inputSearchPlan">
							<tr>
								<td class="header">
									 <bean:message key="screen.b_cpm.label.custName"/>
								</td>
								<td>
									<bean:message key="screen.b_cpm.label_colon"/>
									<bean:write name="CUSTOMER_INFO" property="custName"/>
									<nested:hidden property="customerName" value='${CUSTOMER_INFO.custName}'/>
								</td>
								<td>
									<bean:message key="screen.b_cpm.label.filePath"/><bean:message key="screen.b_cpm.label_colon"/><a href="file:///<bean:write property="filePath" name="CUSTOMER_INFO"/>" target="_blank"><bean:write property="filePath" name="CUSTOMER_INFO"/></a>
								</td>
							</tr>
							<tr>
								<td class="header">
									 <bean:message key="screen.b_cpm.label.custId"/>
								</td>
								<td>
									<bean:message key="screen.b_cpm.label_colon"/>
									<bean:write name="CUSTOMER_INFO" property="idCust"/>
									<nested:hidden property="customerId" value='${CUSTOMER_INFO.idCust}' styleId="customerId"/>
								</td>
							</tr>
						</nested:nest>
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
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;&nbsp;&nbsp;
								<c:if test="${'1' eq accessRightM_CST_BI or '2' eq accessRightM_CST_BI}">
									<a href="#" onclick="javascript: popupBankInfo('${pageContext.request.contextPath}');">
										<bean:message key="screen.b_cpm.label_bank_infomation"/>
									</a> 
								</c:if>
							</td>
						</tr>
					</table>
					<c:if test="${accessRightBean eq '2' and CUSTOMER_PLAN.planStatus eq 'PS0'}">
					    <input type="button" value="<bean:message key="screen.b_cpm.button.backToDraft"/>" id="btnBackToDraft" onclick="javascript: backToDraft();"/>
					</c:if>
					<c:if test="${accessRightBean eq '2' and CUSTOMER_PLAN.planStatus eq 'PS1'}">
					    <input type="button" value="<bean:message key="screen.b_cpm.button.cancel"/>" id="btnCancel" onclick="javascript: cancel();"/>
					</c:if>
					<c:if test="${CUSTOMER_PLAN.planStatus == 'PS1'}">
					<logic:equal value="MY" name="CUSTOMER_INFO" property="systemBase">
						<input type="button" value="<bean:message key="screen.b_cpm.button.generateBif"/>" id="btnGenerateBIF" onclick="javascript: generateBIF();"/>
					</logic:equal>
					</c:if>
					<c:if test="${CUSTOMER_PLAN.planStatus == 'PS1' or CUSTOMER_PLAN.planStatus == 'PS2'}">
					<!-- #187 Start -->
					<%-- <logic:equal value="SG" name="CUSTOMER_INFO" property="systemBase">
						<logic:equal name="accessRightBean" value="2">
							<input type="button" value="<bean:message key="screen.b_cpm.button.approvePlan"/>" id="btnApprovePlan" onclick="javascript: approvePlan();"/>
		    			</logic:equal>
		    		</logic:equal> --%>
		    		<logic:equal value="1" name="LOAD_OBJECT" property="APPROVE_BUTTON">
						<logic:equal value="2" name="LOAD_OBJECT"  property="ACCESS_TYPE">
							<input type="button" value="<bean:message key="screen.b_cpm.button.approvePlan"/>" id="btnApprovePlan" onclick="javascript: approvePlan();"/>
		    			</logic:equal>
		    		</logic:equal>
		    		<!-- #187 End -->
		    		</c:if>
		    		<br/><br/>
					<!-- JSP Tab control -->	
					<bean:define id="parentTab" name="_B_CPM_S02Form" property="tabController"/>
		 			<nested:nest property="tabController">
		 				<nested:hidden property="pageEvent" styleId="pageEvent"/>
		 				<nested:hidden property="activeTab" styleId="activeTab"/>
		 				<nested:hidden property="startIndex" styleId="hiddenStartIndex" />
		 				<ul id="planTabs" class="shadetabs">
		 				<nested:iterate property="tabs" id="tab" indexId="i">
							<li onclick="javasctipt: changeTab(this);"><a href="javascript: void(0);" name="aTab" rel="<nested:write property="id"/>"><nested:write property="name"/></a><nested:hidden property="id" styleClass="idTab"/><nested:hidden property="startIndex" styleClass="startIndex"/><nested:hidden property="totalCount" styleClass="totalCount"/></li>
		 				</nested:iterate>
		 				</ul>
		 				<table class="tablePageLink">
							<tr>
								<td align="left">
									<bean:message key="screen.b_cpm.goToPlan"/>
									<bean:message key="screen.b_cpm.label_colon"/>
									<ts:pageLinks 
							    		id="curNewPageLinks"
										action="javascript: void(0);" 
										name="parentTab" 
										rowProperty="row"
										totalProperty="totalCount" 
										indexProperty="startIndex"		
										/>
									<logic:present name="curNewPageLinks">
										<bean:write name="curNewPageLinks" filter="false"/>
									</logic:present>
									
								</td>
								<td align="right"><bean:message key="screen.b_cpm.totalPlan"/><bean:message key="screen.b_cpm.label_colon"/>
									${parentTab.totalCount}
								</td>
							</tr>
						</table>
		 			</nested:nest>
					<!-- New tab start -->
					<nested:nest property="customerPlan">
			 			<nested:hidden property="screen" styleId="screen"></nested:hidden>
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
 						<nested:hidden property="transactionTypeFlg" styleId="transactionTypeFlg"/>
 						<nested:hidden property="b_ssmIsUsed"/>
 						<nested:hidden property="fixedForexFlg" styleId="fixedForexFlg"/>
 						<nested:hidden property="planStatus"/>
 						<nested:hidden property="billAccNo"/>
 						<!-- #189 Start -->
 						<nested:hidden property="disBillingOption" styleId="disBillingOption"/>
 						<!-- #189 End -->
					<div id="<bean:write name="parentTab" property="activeTab"/>" class="tabContent">
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
													<logic:equal name="CUSTOMER_PLAN" property="planStatus" value="PS6">
													<logic:equal name="accessRightBean" value="2">
														<button id="btnUnsuspend" onclick="javascript: Unsuspend(this);"><bean:message key="screen.b_cpm.button.unsuspend"/></button>
													</logic:equal>
													</logic:equal>
													<logic:equal name="CUSTOMER_PLAN" property="planStatus" value="PS3">
													<logic:equal name="accessRightBean" value="2">
														<button id="btnSuspend" onclick="javascript: Suspend(this);"><bean:message key="screen.b_cpm.button.suspend"/></button>
													</logic:equal>
													</logic:equal>
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
											<td nowrap="nowrap" bgcolor="#FFCCFF" style="height:100%"><bean:message key="screen.b_cpm.label.billingAccNo"/></td>
											<td nowrap="nowrap" style="padding-top: 0px; padding-left: 0px; padding-bottom: 0px;">
											<span style="background-color:#FFCCFF; margin: 0px; width: 280px;height:100%; padding-top: 2px; padding-bottom: 2px; padding-left: 4px;" >
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
											<td class="header" bgcolor="#FFCCFF"  style="height:100%"><bean:message key="screen.b_cpm.label.billingCurency"/></td>
									    	<td style="padding-top: 0px; padding-left: 0px; padding-bottom: 0px;">
									    	    <span style="background-color:#FFCCFF; margin: 0px; width: 280px; height:100%;padding-top: 2px; padding-bottom: 2px; padding-left: 4px;" >
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
											<td bgcolor="#FFCCFF" ><bean:message key="screen.b_cpm.label.paymentMethod"/></td>
											<td style="padding-top: 0px; padding-left: 0px; padding-bottom: 0px;">
											    <span style="background-color:#FFCCFF; margin: 0px; width: 280px; padding-top: 2px; padding-bottom: 2px; padding-left: 4px;" >
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
											<td bgcolor="#FFCCFF" ><bean:message key="screen.b_cpm.label.paymentCurrency"/></td>
											<td style="padding-top: 0px; padding-left: 0px; padding-bottom: 0px;">
											<span style="background-color:#FFCCFF; margin: 0px; width: 280px; padding-top: 2px; padding-bottom: 2px; padding-left: 4px;" >
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
												<td bgcolor="#FFCCFF" ><bean:message key="screen.b_cpm.label.fixedForex"/></td>
												<td style="padding-top: 0px; padding-left: 0px; padding-bottom: 0px;">
												    <span style="background-color:#FFCCFF; margin: 0px; width: 280px; padding-top: 2px; padding-bottom: 2px; padding-left: 4px;" >
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
											<td class="header" bgcolor="#FFCCFF"  style="height:100%"><bean:message key="screen.b_cpm.label.taxtype"/></td>
									    	<td style="padding-top: 0px; padding-left: 0px; padding-bottom: 0px;">
									    	    <span style="background-color:#FFCCFF; margin: 0px; width: 280px; height:100%;padding-top: 2px; padding-bottom: 2px; padding-left: 4px;" >
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
									<logic:equal value="NP" name="CUSTOMER_PLAN" property="planType">
									    <div class="planHeaderInformation">
									        <fieldset>
	              							    <legend style="color: #4876FF"><bean:message key="screen.b_cpm.applyAllTitle"/></legend>
										    	<table class="informationPlan" cellpadding="0" cellspacing="0" width="100%">
												    <col width="18%"/>
									                <col width="1%"/>
									                <col width="81%"/>
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
								                                <bean:message key="screen.b_cpm.applyAllGstAllSame"/>
								                            </nested:equal>
								                            <nested:notEqual value="0" property="allGstSameFlg">
								                                <span style="color: red;"><bean:message key="screen.b_cpm.applyAllNotSameNotAll"/></span>
								                                <bean:message key="screen.b_cpm.applyAllGstAllNotSame"/>
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
									            </table>
									        </fieldset>
									    </div>
									</logic:equal>
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
															
															<c:choose>
																<c:when test="${CUSTOMER_PLAN.planStatus ne 'PS0'
																              and CUSTOMER_PLAN.planStatus ne 'PS1'
																              and CUSTOMER_PLAN.planStatus ne 'PS2'
																              and service.serviceStatus ne 'PS6'
																              and (service.billingStatus eq 'BS1' or service.billingStatus eq 'BS2')}">
				    											    <a href="#" onclick="javascript: svcEndDateClick(this);">
				    											        <nested:empty property="serviceDateEnd">
				    											            --/--/----
				    											        </nested:empty>
				    											        <nested:equal value="-" property="serviceDateEnd">
				    											        	--/--/----
				    											        </nested:equal>
				    											        <nested:notEmpty property="serviceDateEnd">
				    											        	<nested:notEqual value="-" property="serviceDateEnd">
				    											        		<nested:write property="serviceDateEnd"/>
				    											        	</nested:notEqual>
				    											        </nested:notEmpty>
				    											    </a>
				    											</c:when>
				    											<c:otherwise>
				    											    <nested:empty property="serviceDateEnd">
			    											            --/--/----
			    											        </nested:empty>
			    											        <nested:equal value="-" property="serviceDateEnd">
			    											        	--/--/----
			    											        </nested:equal>
			    											        <nested:notEmpty property="serviceDateEnd">
			    											        	<nested:notEqual value="-" property="serviceDateEnd">
			    											        		<nested:write property="serviceDateEnd"/>
			    											        	</nested:notEqual>
			    											        </nested:notEmpty>
				    											</c:otherwise>
			    											</c:choose>
														</td>
														<td nowrap="nowrap"><nested:checkbox property="autoRenewal" value="1" onclick="javascript: inactiveCheckbox(this);"/><bean:message key="screen.b_cpm.label.autoRenewal"/></td>
														<td rowspan="2" colspan="2" nowrap="nowrap" valign="top" style="text-align: right">
															<div class="<t:writeCodeValue codeList="COLOR_CODE" key="${service.serviceStatus}"></t:writeCodeValue>">
																<h4><bean:message key="screen.b_cpm.label.status"/></h4>
																<h4>
																	<t:writeCodeValue codeList="LIST_PLANSTATUS" key="${service.serviceStatus}"/>
																</h4>
																<%--
																<nested:equal property="serviceStatus" value="PS3">
																<logic:equal name="accessRightBean" value="2">
				    												<button id="btnTerminate" onclick="javascript: Terminate(this);"><bean:message key="screen.b_cpm.button.terminate"/></button>
				    											</logic:equal>
				    											</nested:equal>
				    											<nested:equal property="serviceStatus" value="PS6">
				    											<logic:equal name="accessRightBean" value="2">
				    												<button id="btnTerminate" onclick="javascript: Terminate(this);"><bean:message key="screen.b_cpm.button.terminate"/></button>
				    											</logic:equal>
				    											</nested:equal>
				    											--%>
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
														    <div style="color: blue">
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
																	<nested:radio property="isDiscountLumpSum" styleClass="isDiscountLumpSum" value="0"/><bean:message key="screen.b_cpm.label.itemised1"/>
																</span>
																<nested:radio property="isDiscountLumpSum" styleClass="isDiscountLumpSum" value="1"/><bean:message key="screen.b_cpm.label.lumpSum1"/>
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
														<%--
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
														--%>
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
															<td nowrap="nowrap" style="text-align:left;padding-right:0px">
															<logic:equal value="1" property="customerPlan.m_jnmDisplayFlg" name="_B_CPM_S02Form">
																<nested:checkbox property="isDisplayJobNo" styleClass="isDisplayJobNo" value="1" disabled="true"/>
																<bean:message key="screen.b_cpm.label.jobNo"/>
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
																	<col width="33%">
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
																	<td valign="top" nowrap="nowrap" class="numberStyle"><nested:radio property="isDiscountOneTime" styleClass="isDiscountOneTime" value="O" /><bean:message key="screen.b_cpm.label.onetime"/></td>
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
														<%-- <logic:equal value="1" property="customerPlan.m_jnmDisplayFlg" name="_B_CPM_S02Form">
														<tr>
															<td>&nbsp;</td>
															<td nowrap="nowrap"><nested:checkbox property="isDisplayJobNo" styleClass="isDisplayJobNo" value="1" disabled="true"/><bean:message key="screen.b_cpm.label.jobNo"/></td>
															<td nowrap="nowrap" colspan="5">
																<bean:message key="screen.b_cpm.label_colon"/>
																<nested:write property="jobNo"/>
															</td>
															<td>&nbsp;</td>
														</tr>
														</logic:equal> --%>
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
																<div>
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
																<bean:write name="LEVEL2" property="svcLevel2"/>
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
																					<bean:write name="LOAD_OBJECT" property="TAX_WORD"/>
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
																				<%-- <nested:write property="rate"/> --%>
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
														<%--
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
														--%>
														<tr>
															<td>&nbsp;</td>
															<td nowrap="nowrap" style="width:33%;"><bean:message key="screen.b_cpm.label.itemDescription"/><span style="color:red"><bean:message key="screen.b_cpm.label.star"/></span></td>
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
															<td nowrap="nowrap" style="text-align:left;padding-right:0px">
															<logic:equal value="1" property="customerPlan.m_jnmDisplayFlg" name="_B_CPM_S02Form">
															<nested:checkbox property="isDisplayJobNo" styleClass="isDisplayJobNo" value="1" disabled="true"/>
																<bean:message key="screen.b_cpm.label.jobNo"/>
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
																		<col width="33%">
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
																		<td valign="top" nowrap="nowrap" class="numberStyle"><nested:radio property="isDiscountOneTime" styleClass="isDiscountOneTime" value="O" /><bean:message key="screen.b_cpm.label.onetime"/></td>
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
														<%-- <logic:equal value="1" property="customerPlan.m_jnmDisplayFlg" name="_B_CPM_S02Form">
														<tr>
															<td>&nbsp;</td>
															<td nowrap="nowrap"><nested:checkbox property="isDisplayJobNo" styleClass="isDisplayJobNo" value="1" disabled="true"/><bean:message key="screen.b_cpm.label.jobNo"/></td>
															<td nowrap="nowrap" colspan="5">
																<bean:message key="screen.b_cpm.label_colon"/>
																<nested:write property="jobNo"/>
															</td>
															<td>&nbsp;</td>
														</tr>
														</logic:equal> --%>
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
																<div>
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
																					<col width="60%"/>
																					<col width="40%"/>
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
																					<bean:write name="LOAD_OBJECT" property="TAX_WORD"/>
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
					</div><!-- New tab end -->
					</nested:nest>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>
	    		<div class="bottomButtons">
	    		<logic:equal name="accessRightBean" value="2">
		    		<c:if test="${CUSTOMER_PLAN.planStatus == 'PS1' or CUSTOMER_PLAN.planStatus == 'PS2'}">
			    		<logic:equal name="CUSTOMER_PLAN" property="screen" value="2">
				    		<button id="btnEdit" onclick="javascript: editCustomerPlan();"><bean:message key="screen.b_cpm.button.edit"/></button>
				    	</logic:equal>
			    	</c:if>
			    	<c:if test="${CUSTOMER_PLAN.planStatus == 'PS3' or CUSTOMER_PLAN.planStatus == 'PS7'}">
			    		<logic:equal name="accessE2" value="2">
			    			<logic:equal name="CUSTOMER_PLAN" property="screen" value="2">
				    			<button id="btnEdit" onclick="javascript: editCustomerPlan();"><bean:message key="screen.b_cpm.button.edit"/></button>
				    		</logic:equal>
			    		</logic:equal>
			    	</c:if>
			    	<c:if test="${CUSTOMER_PLAN.planStatus == 'PS1'}">
			    		<logic:equal name="CUSTOMER_PLAN" property="screen" value="2">
			    			<button id="btnDelete" onclick="javascript: deleteCustomerPlan();"><bean:message key="screen.b_cpm.button.delete"/></button>
			    		</logic:equal>
			    	</c:if>
		    	</logic:equal>
		    	<logic:equal name="accessRightBean" value="1">
		    		<c:if test="${CUSTOMER_PLAN.planStatus == 'PS3' or CUSTOMER_PLAN.planStatus == 'PS7'}">
		    			<logic:equal name="accessE2" value="2">
			    			<logic:equal name="CUSTOMER_PLAN" property="screen" value="2">
			    				<button id="btnEdit" onclick="javascript: editCustomerPlan();"><bean:message key="screen.b_cpm.button.edit"/></button>
			    			</logic:equal>
		    			</logic:equal>
		    		</c:if>
		    	</logic:equal>
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
<!-- message display end -->
		</ts:form>
<div id="message_group" style="display: none;">
	<div class="messagecontinueSuspend"><bean:message key="info.ERR4SC009"/></div>
	<div class="messageDeletePlan"><bean:message key="info.ERR4SC002"/></div>
	<div class="messageApprove"><bean:message key="info.ERR4SC005" arg0="approve"/></div>
	<div class="messageSuspend"><bean:message key="info.ERR4SC005" arg0="suspend"/></div>
	<div class="messageUnsuspend"><bean:message key="info.ERR4SC005" arg0="unsuspend"/></div>
	<div class="messageCurrencyDifferent"><bean:message key="info.ERR4SC013"/></div>
	<div class="messageCancel"><bean:message key="info.ERR4SC005" arg0="cancel"/></div>
	<div class="messageDraft"><bean:message key="info.ERR4SC005" arg0="back to draft"/></div>
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
	</body>
</html:html>