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
		<title></title>
	</head>
	<t:defineCodeList id="LIST_RATETYPE"/>
	<t:defineCodeList id="LIST_RATETYPE2"/>
	<t:defineCodeList id="MODE_LIST"/>
	<t:defineCodeList id="LIST_GST"/>
	<t:defineCodeList id="LIST_BILLINGSTATUS"/>
	<t:defineCodeList id="LIST_MASTER_LOCATION"/>
	<bean:define id="CUSTOMER_PLAN" name="_B_CPM_S02Form" property="customerPlan"></bean:define>
	<bean:define id="i" value="${param.i}"/>
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
						<bean:message key="screen.b_cpm.label.service"/> ${i+1}
					</div>
				</td>
				<td style="width:87%;text-align:right;background-color:white;">
					<nested:hidden property="idCustPlanGrp" styleClass="idCustPlanGrp"/>
					<nested:hidden property="serviceStatus" styleClass="serviceStatus"/>
					<nested:hidden property="serviceDateStart" styleClass="serviceDateStart"/>
					<nested:hidden property="serviceDateEnd" styleClass="serviceDateEnd"/>
					<nested:hidden property="minimumServiceFrom" styleClass="minimumServiceFrom"/>
					<nested:hidden property="minimumServiceTo" styleClass="minimumServiceTo"/>
					<nested:hidden property="contactTermNo" styleClass="contactTermNo"/>
					<nested:hidden property="proRateBaseNo" styleClass="proRateBaseNo"/>
					<nested:hidden property="serviceIdPlan" styleClass="serviceIdPlan"/>
					<nested:hidden property="serviceMultiPln" styleClass="serviceMultiPln"/>
					<nested:hidden property="serviceBacCount" styleClass="serviceBacCount"/>
					<nested:hidden property="billingStatus" styleClass="billingStatus"/>
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
									<%--
									<nested:equal property="serviceStatus" value="PS6">
									<button id="btnUnsuspend" onclick="javascript: Unsuspend(this);"><bean:message key="screen.b_cpm.button.unsuspend"/></button>
									</nested:equal>
									<nested:equal property="serviceStatus" value="PS3">
									<button id="btnSuspend" onclick="javascript: Suspend(this);"><bean:message key="screen.b_cpm.button.suspend"/></button>
									</nested:equal>
									<nested:equal property="serviceStatus" value="PS3">
									<button id="btnTerminate" onclick="javascript: Terminate(this);"><bean:message key="screen.b_cpm.button.terminate"/></button>
									</nested:equal>
									<nested:equal property="serviceStatus" value="PS6">
									<button id="btnTerminate" onclick="javascript: Terminate(this);"><bean:message key="screen.b_cpm.button.terminate"/></button>
									</nested:equal>
									--%>
								</div>
								<div style="color: blue;padding-top: 20px;text-align: left;">
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
						<tr class="serviceTable">
							<td colspan="3">
								<fieldset class="minimumService">
									<legend>
										<div style="color:#4876FF;"><bean:message key="screen.b_cpm.label.minimumService"/></div>
									</legend>
									<table width="100%">
										<colgroup>
											<col width="18%"/>
											<col width="30%"/>
											<col width="52%"/>
										</colgroup>
										<tr>
											<td nowrap="nowrap"><nested:checkbox property="minimumService" onclick="javascript: inactiveCheckbox(this);" value="1"/></td>
											<td nowrap="nowrap">
												<bean:message key="screen.b_cpm.label.from"/>
												<nested:write property="minimumServiceFrom"/>
											</td>
											<td nowrap="nowrap">
												<bean:message key="screen.b_cpm.label.to"/>
												<nested:write property="minimumServiceTo"/>
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
						</tr>
						<tr>
							<td nowrap="nowrap"><bean:message key="screen.b_cpm.label.proRateBaseOn"/></td>
							<td colspan="5" nowrap="nowrap">
								<bean:message key="screen.b_cpm.label_colon"/>
								<span style="width:110px;">
									<nested:radio property="proRateBase" styleClass="proRateBase" value="S"/><bean:message key="screen.b_cpm.button.sysdate"/>
								</span>
								<nested:radio property="proRateBase" styleClass="proRateBase" value="U"/><bean:message key="screen.b_cpm.button.userDefine"/>
								<nested:write property="proRateBaseNo"/>&nbsp;<bean:message key="screen.b_cpm.label.daysPerMonth"/>
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
										<col width="63%"/>
										<col width="10%"/>
										<col width="10%"/>
										<col width="15%"/>
										<col width="2%"/>
									</colgroup>
									<tr style="background-color:#8cb0f8">
										<!-- wcbeh@20160921 - Master Location for active(not new) -->
										<td class="customerPlanHeaderItem" nowrap="nowrap" style="text-align:left;">
											<div>
												<span style="width:24%">
													<bean:message key="screen.b_cpm.label.billingDesc"/>
												</span>
												<span>
												<!--<logic:equal value="1" property="customerPlan.masterLocationDisplayFlg" name="_B_CPM_S02Form">
													<nested:checkbox property="masterLocationFlg" value="${service.masterLocationFlg}" styleClass="masterLocationFlg" onclick="javascript: checkMasterLocation(this);"/>
													<bean:message key="screen.b_cpm.label.masterLocation"/>
							        				<bean:message key="screen.b_cpm.label.colon"/>
							        				&nbsp;&nbsp;
							        				<nested:select property="masterLocation" styleClass="masterLocation" onchange="javascript: changeLocation(this);">
															<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
															<html:optionsCollection name="LIST_MASTER_LOCATION" label="id" value="id"/>
													</nested:select>  
												</logic:equal>   -->                   								
								        		</span>
						        			</div>
										</td>
										<td class="customerPlanHeaderItem" nowrap="nowrap" style="text-align:right;"><bean:message key="screen.b_cpm.label.quantity"/></td>
										<td class="customerPlanHeaderItem" nowrap="nowrap" style="text-align:right;"><bean:message key="screen.b_cpm.label.unitPrice"/></td>
										<td class="customerPlanHeaderItem" nowrap="nowrap" style="text-align:right;"><bean:message key="screen.b_cpm.label.amount"/></td>
										<td></td>
									</tr>
									<tr>
										<td nowrap="nowrap" style="text-align:left;padding-left:0px">
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
												<nested:text property="custPo" styleClass="custPo" style="width:89%" maxlength="30"></nested:text>
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
							<div class="subPlanLabel" style="font-weight: bold;color:#4876FF;">
							[<span id="planNo"><%=n%></span>][<bean:message key="screen.b_cpm.label.subPlan"/>]
							<logic:equal name="CUSTOMER_PLAN" property="planType" value="SP">
							[<nested:write property="planName"/>]
							[<nested:write property="planDesc"/>]
							[<nested:write property="itemGrpName"/>]
							</logic:equal>
							</div>																		
						</legend>
						<nested:hidden property="idCustPlanLink" styleClass="idCustPlanLink"/>
						<nested:hidden property="itemType" styleClass="itemType"/>
						<nested:hidden property="quantity" styleClass="subPlanQuantity"/>
						<nested:hidden property="unitPrice" styleClass="subPlanUnitPrice"/>
						<nested:hidden property="amount" styleClass="subPlanAmount"/>
						<nested:hidden property="jobNo" styleClass="jobNo"/>
						<nested:hidden property="isDisplayJobNo" styleClass="isDisplayJobNo"/>
						<nested:hidden property="svcLevel1" styleClass="svcLevel1"/>
						<nested:hidden property="svcLevel2" styleClass="svcLevel2"/>
						<nested:hidden property="rateType" styleClass="rateType"/>
						<nested:hidden property="rateType2" styleClass="rateType2"/>
						<nested:hidden property="rateMode" styleClass="rateMode"/>
						<nested:hidden property="rate" styleClass="rate"/>
						<nested:hidden property="idPlan" styleClass="idPlan"/>
						<nested:hidden property="planName" styleClass="planName"/>
						<nested:hidden property="planDesc" styleClass="planDesc"/>
						<nested:hidden property="idPlanGrp" styleClass="idPlanGrp"/>
						<nested:hidden property="itemGrpName" styleClass="itemGrpName"/>
						<nested:hidden property="currency" styleClass="currency"/>
						<nested:hidden property="discamount" styleClass="discamount"/>
						<nested:hidden property="applyGst" styleClass="applyGst"/>
						<table width="100%">
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
									<!-- #200, #201 wcbeh@20160921 - Sub Master Location for NP -->
									<logic:equal value="1" property="customerPlan.masterLocationDisplayFlg" name="_B_CPM_S02Form">
									<div>
										<span style="width:100%;">
											<bean:message key="screen.b_cpm.label.masterLocation"/>
					        				<bean:message key="screen.b_cpm.label.colon"/>
					        				&nbsp;&nbsp;										        				
					        				<nested:equal property="subLocation" value="">
					        					-
					        				</nested:equal>
					        				<nested:notEqual property="subLocation" value="">
					        					<nested:write property="subLocation"/>
					        				</nested:notEqual>
					        				<!--<nested:select property="subLocation" styleClass="subLocation">
													<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
													<html:optionsCollection name="LIST_MASTER_LOCATION" label="id" value="id"/>													
											</nested:select> -->
										</span>
									</div>
									</logic:equal>
								</td>
								<td nowrap="nowrap" style="text-align:left;padding-right:0px">
								<logic:equal value="1" property="customerPlan.m_jnmDisplayFlg" name="_B_CPM_S02Form">
									<nested:checkbox property="isDisplayJobNo" styleClass="isDisplayJobNo" value="1" disabled="true"/><bean:message key="screen.b_cpm.label.jobNo"/>
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
											<col width="38%">
											<col width="2%">
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
									<table style="width:100%">
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
															<nested:hidden property="idCustPlanSvc" styleClass="idCustPlanSvc"/>
															<nested:hidden property="svcLevel3" styleClass="svcLevel3"/>
															<nested:hidden property="svcLevel4" styleClass="svcLevel4"/>
															<nested:hidden property="vendor" styleClass="vendor"/>
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
														<td valign="top" >
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
													<td nowrap="nowrap">
														
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
														<c:if test="${LOAD_OBJECT.RateType2Flg eq '1'}">
														<logic:iterate id="RATE_TYPE2" name="LIST_RATETYPE2">
															<nested:equal property="rateType2" value="${RATE_TYPE2.id}">
															<bean:write name="RATE_TYPE2" property="name"/>
															</nested:equal>
														</logic:iterate>
														</c:if>
														<c:if test="${LOAD_OBJECT.RateType2Flg ne '1'}">
														<logic:iterate id="RATE_TYPE2" name="LIST_RATETYPE2">
															<nested:equal property="rateType2" value="${RATE_TYPE2.id}">
															<bean:write name="RATE_TYPE2" property="name"/>
															</nested:equal>
														</logic:iterate>
														</c:if>
														
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
				<td colspan="3" class="options_ervice" style="background-color:#e7efff;">
					<nested:iterate id="subPlan" property="subPlans" indexId="j">
					<logic:equal name="subPlan" property="itemType" value="O">
					<%n=n+1;%>
					<div class="subPlan">
					<fieldset>
						<legend>
							<div class="optionServiceLabel" style="font-weight: bold;color:#4876FF;">
							[<span id="planNo"><%=n%></span>][<bean:message key="screen.b_cpm.label.optionService"/>]
							<logic:equal name="CUSTOMER_PLAN" property="planType" value="SP">
							[<nested:write property="planName"/>]
							[<nested:write property="planDesc"/>]
							[<nested:write property="itemGrpName"/>]
							</logic:equal>
							</div>
						</legend>
						<nested:hidden property="idCustPlanLink" styleClass="idCustPlanLink"/>
						<nested:hidden property="itemType" styleClass="itemType"/>
						<nested:hidden property="quantity" styleClass="subPlanQuantity"/>
						<nested:hidden property="unitPrice" styleClass="subPlanUnitPrice"/>
						<nested:hidden property="amount" styleClass="subPlanAmount"/>
						<nested:hidden property="jobNo" styleClass="jobNo"/>
						<nested:hidden property="isDisplayJobNo" styleClass="isDisplayJobNo"/>
						<nested:hidden property="svcLevel1" styleClass="svcLevel1"/>
						<nested:hidden property="svcLevel2" styleClass="svcLevel2"/>
						<nested:hidden property="rateType" styleClass="rateType"/>
						<nested:hidden property="rateType2" styleClass="rateType2"/>
						<nested:hidden property="rateMode" styleClass="rateMode"/>
						<nested:hidden property="rate" styleClass="rate"/>
						<nested:hidden property="idPlan" styleClass="idPlan"/>
						<nested:hidden property="planName" styleClass="planName"/>
						<nested:hidden property="planDesc" styleClass="planDesc"/>
						<nested:hidden property="idPlanGrp" styleClass="idPlanGrp"/>
						<nested:hidden property="itemGrpName" styleClass="itemGrpName"/>
						<nested:hidden property="currency" styleClass="currency"/>
						<nested:hidden property="discamount" styleClass="discamount"/>
						<nested:hidden property="applyGst" styleClass="applyGst"/>
						<table width="100%">
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
									<!-- #200, #201 wcbeh@20160921 - Sub Master Location for NP -->
									<logic:equal value="1" property="customerPlan.masterLocationDisplayFlg" name="_B_CPM_S02Form">
									<div>
										<span style="width:100%;">
											<bean:message key="screen.b_cpm.label.masterLocation"/>
					        				<bean:message key="screen.b_cpm.label.colon"/>
					        				&nbsp;&nbsp;
					        				<nested:select property="subLocation" styleClass="subLocation">
													<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
													<html:optionsCollection name="LIST_MASTER_LOCATION" label="id" value="id"/>													
											</nested:select>
										</span>
									</div>
									</logic:equal>
								</td>
								<td nowrap="nowrap" style="text-align:left;padding-right:0px">
								<logic:equal value="1" property="customerPlan.m_jnmDisplayFlg" name="_B_CPM_S02Form">
									<nested:checkbox property="isDisplayJobNo" styleClass="isDisplayJobNo" value="1" disabled="true"/><bean:message key="screen.b_cpm.label.jobNo"/>
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
											<col width="38%">
											<col width="2%">
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
															<nested:hidden property="idCustPlanSvc" styleClass="idCustPlanSvc"/>
															<nested:hidden property="svcLevel3" styleClass="svcLevel3"/>
															<nested:hidden property="svcLevel4" styleClass="svcLevel4"/>
															<nested:hidden property="vendor" styleClass="vendor"/>
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
													<td nowrap="nowrap">
														
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
														<c:if test="${LOAD_OBJECT.RateType2Flg eq '1'}">
														<logic:iterate id="RATE_TYPE2" name="LIST_RATETYPE2">
															<nested:equal property="rateType2" value="${RATE_TYPE2.id}">
															<bean:write name="RATE_TYPE2" property="name"/>
															</nested:equal>
														</logic:iterate>
														</c:if>
														<c:if test="${LOAD_OBJECT.RateType2Flg ne '1'}">
														<logic:iterate id="RATE_TYPE2" name="LIST_RATETYPE2">
															<nested:equal property="rateType2" value="${RATE_TYPE2.id}">
															<bean:write name="RATE_TYPE2" property="name"/>
															</nested:equal>
														</logic:iterate>
														</c:if>
														
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
													<td colspan="2"  nowrap="nowrap" class="detailGst" >
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
		</div>
</html:html>