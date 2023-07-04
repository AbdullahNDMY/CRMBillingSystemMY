<%@page import="java.util.ArrayList"%>
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
<%@page import="org.apache.struts.util.LabelValueBean"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
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
	<t:defineCodeList id="LIST_GST_VALUE"/>
	<t:defineCodeList id="LIST_BILLINGSTATUS"/>
	<t:defineCodeList id="LIST_MASTER_LOCATION"/>
	<bean:define id="CUSTOMER_PLAN" name="_B_CPM_S02Form" property="customerPlan"></bean:define>
	<bean:define id="CUSTOMER_PLAN" name="_B_CPM_S02Form" property="customerPlan"></bean:define>
	<bean:define id="i" value="${param.i}"/>
	<!--Be used for JobNo when the action ='new' -->
	<html:hidden property="action" styleId="action" value="${action}"/>
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
					<div id="seqServiceName" style="font-weight: bold;color:#000080;">
						<bean:message key="screen.b_cpm.label.service"/> ${i+1}
					</div>
				</td>
				<td style="width:87%;text-align:right;background-color:white;">
					<logic:notEqual value="PS2" name="CUSTOMER_PLAN" property="planStatus">
						<c:if test="${'BS0' eq service.billingStatus or 'BS1' eq service.billingStatus}">
							 <c:choose> 
							 	<c:when test="${service.serviceStatus eq 'PS3' and 'BS1' eq service.billingStatus}"><!--Active Plan  -->
							 		<c:if test="${'1'eq LOAD_OBJECT.EditActivePlanAMTFlg}"><!-- L.SERVICE_STATUS=BS1 AND X = 0 then disable and hide button-->
							 			<span class="removeX" style="cursor: pointer;" onclick="javascript:removeService(this);">Remove &nbsp;<img src="../image/delete.gif"/></span>
							 		</c:if>
							 	</c:when>
							 	<c:otherwise>
							 		<span class="removeX" style="cursor: pointer;" onclick="javascript:removeService(this);">Remove &nbsp;<img src="../image/delete.gif"/></span>
							 	</c:otherwise>
							 </c:choose>
						</c:if>
					</logic:notEqual>
					<nested:hidden property="idCustPlanGrp" styleClass="idCustPlanGrp"/>
					<nested:hidden property="serviceStatus" styleClass="serviceStatus"/>
					<nested:hidden property="serviceIdPlan" styleClass="serviceIdPlan"/>
					<nested:hidden property="serviceMultiPln" styleClass="serviceMultiPln"/>
					<nested:hidden property="serviceBacCount" styleClass="serviceBacCount"/>
					<nested:hidden property="billingStatus" styleClass="billingStatus"/>
					<nested:hidden name="LOAD_OBJECT" property="EditActivePlanAMTFlg" styleClass="EditActivePlanAMTFlg"/>
				</td>
			</tr>
			<tr style="background-color:#ffffcc;">
				<td></td>
				<td colspan="2">
					<table width="100%" class="blankTable">
						<colgroup>
							<col width="15%"/>
							<col width="25%"/>
							<col width="15%"/>
							<col width="25%"/>
							<col width="20%"/>
						</colgroup>
						<tr class="serviceTable">
							<td nowrap="nowrap"><bean:message key="screen.b_cpm.header.servicePeriod"/><span style="color:red"><bean:message key="screen.b_cpm.label.star"/></span></td>
							<td nowrap="nowrap">
								<bean:message key="screen.b_cpm.label_colon"/>
								<bean:message key="screen.b_cpm.label.from"/>
								<c:choose>
									<c:when test="${service.serviceStatus == 'PS1' || service.serviceStatus == 'PS2' || (service.serviceStatus == 'PS3' && 'BS1' eq service.billingStatus) || (service.serviceStatus == 'PS7' && 'BS1' eq service.billingStatus)}">
										<nested:text property="serviceDateStart" styleClass="serviceDateStart" readonly="true"/>
										<input type="button" onclick="javascript: customCalendar(this, 'serviceDateStart', 'dd/MM/yyyy');" value="" class="BlueStyle-button" id="startFromBtn"/>
									</c:when>
									<c:otherwise>
										<nested:write property="serviceDateStart"/>
										<nested:hidden property="serviceDateStart" styleClass="serviceDateStart"/>
									</c:otherwise>
								</c:choose>
							</td>
							<td nowrap="nowrap">
								<bean:message key="screen.b_cpm.label.to"/>
								<c:if test="${'PS1' eq service.serviceStatus || 'PS2' eq service.serviceStatus}">
									<nested:text property="serviceDateEnd" styleClass="serviceDateEnd" readonly="true"/>
									<input type="button" onclick="javascript: customCalendar(this, 'serviceDateEnd', 'dd/MM/yyyy');" value="" class="BlueStyle-button" />
								</c:if>
								<c:if test="${'PS1' ne service.serviceStatus && 'PS2' ne service.serviceStatus}">
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
							        <nested:hidden property="serviceDateEnd" styleClass="serviceDateEnd"/>
								</c:if>
							</td>
							<td nowrap="nowrap">
								<c:if test="${'PS1' eq service.serviceStatus || 'PS2' eq service.serviceStatus}">
									<nested:checkbox property="autoRenewal" styleClass="autoRenewal" value="1" onclick="javascript: changeAutoRenewal(this);"/>
									<bean:message key="screen.b_cpm.label.autoRenewal"/>
								</c:if>
								<c:if test="${'PS1' ne service.serviceStatus && 'PS2' ne service.serviceStatus}">
									<nested:checkbox property="autoRenewal" styleClass="autoRenewal" value="1" onclick="javascript: inactiveCheckbox(this);"/>
									<bean:message key="screen.b_cpm.label.autoRenewal"/>
								</c:if>
							</td>
							<td rowspan="2" colspan="2" nowrap="nowrap" valign="top" style="text-align: right;">
								<div class="<t:writeCodeValue codeList="COLOR_CODE" key="${service.serviceStatus}"></t:writeCodeValue>" style="width:70%;">
									<h4><bean:message key="screen.b_cpm.label.status"/></h4>
									<h4>
										<t:writeCodeValue codeList="LIST_PLANSTATUS" key="${service.serviceStatus}"/>
									</h4>
								</div>
								<div style="color: blue;padding-left: 71px;padding-top: 20px;text-align: left;width: 285px;">
								    <bean:message key="screen.b_cpm.label.billingStatus"/>
								    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="screen.b_cpm.label_colon"/>&nbsp;
								    <t:writeCodeValue codeList="LIST_BILLINGSTATUS" key="${service.billingStatus}"/>
							    </div>
							    <div style="color: blue">
								    &nbsp;<bean:message key="screen.b_cpm.label.CompletionDate"/>
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
											<col width="20%"/>
											<col width="30%"/>
											<col width="50%"/>
										</colgroup>
										<tr>
											<td nowrap="nowrap"><nested:checkbox property="minimumService" styleClass="minimumService" onclick="javascript: checkServicePeriod(this);" value="1"/></td>
											<td nowrap="nowrap">
												<bean:message key="screen.b_cpm.label.from"/>
												<nested:text property="minimumServiceFrom" styleClass="minimumServiceFrom" readonly="readonly"/>
												<input type="button" onclick="javascript: customCalendar(this, 'minimumServiceFrom', 'dd/MM/yyyy');" value="" class="BlueStyle-button"/>
											</td>																
											<td nowrap="nowrap">
												<bean:message key="screen.b_cpm.label.to"/>
												<nested:text property="minimumServiceTo" styleClass="minimumServiceTo" readonly="readonly"/>
												<input type="button" onclick="javascript: customCalendar(this, 'minimumServiceTo', 'dd/MM/yyyy');" value="" class="BlueStyle-button"/>
											</td>
										</tr>
										<tr>
											<td nowrap="nowrap"><bean:message key="screen.b_cpm.label.contractTerm"/></td>
											<td colspan="2" nowrap="nowrap">
												<bean:message key="screen.b_cpm.label_colon"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<nested:text property="contactTermNo" styleClass="contactTermNo"/>
												<nested:radio property="contactTerm" value="M" styleClass="contactTerm"/><bean:message key="screen.b_cpm.label.months"/>
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
									<nested:radio property="proRateBase" styleClass="proRateBase" value="S" onclick="javascript: changeProrateBase(this);"/><bean:message key="screen.b_cpm.button.sysdate"/>
								</span>
								<nested:radio property="proRateBase" styleClass="proRateBase" value="U" onclick="javascript: changeProrateBase(this);"/><bean:message key="screen.b_cpm.button.userDefine"/>
								<nested:text property="proRateBaseNo" styleClass="proRateBaseNo"/><bean:message key="screen.b_cpm.label.daysPerMonth"/>
							</td>
						</tr>
						<tr>
							<td nowrap="nowrap"><bean:message key="screen.b_cpm.label.billingAmount"/></td>
							<td colspan="2" nowrap="nowrap">
								<bean:message key="screen.b_cpm.label_colon"/>
								<span style="width:110px;">
									<nested:radio property="isLumpSum" styleClass="isLumpSum" value="0" onclick="javascript:changItemmise(this);"/><bean:message key="screen.b_cpm.label.itemised1"/>
								</span>
								<nested:radio property="isLumpSum" styleClass="isLumpSum" value="1"  onclick="javascript:changItemmise(this);"/><bean:message key="screen.b_cpm.label.lumpSum1"/>
							</td>
							<td nowrap="nowrap">
								<c:if test="${'1'eq LOAD_OBJECT.AddExistingPlanflg}">
									<logic:equal name="CUSTOMER_PLAN" property="planType" value="SP">
										<c:if test="${'BS0' eq service.billingStatus or 'BS1' eq service.billingStatus}">
											<c:choose> 
								 				<c:when test="${service.serviceStatus eq 'PS3' and 'BS1' eq service.billingStatus}">
								 					<c:if test="${'1'eq LOAD_OBJECT.EditActivePlanAMTFlg}">
								 						<input type="button" style="width:264px;" value="<bean:message key="screen.b_cpm.button.addExistingSubPlanOptSrv"/>" onclick="javascript: addExistingSubPlanOptionService(this);"/>
								 					</c:if>
								 				</c:when>
								 				<c:otherwise>
								 					<input type="button" style="width:264px;" value="<bean:message key="screen.b_cpm.button.addExistingSubPlanOptSrv"/>" onclick="javascript: addExistingSubPlanOptionService(this);"/>
								 				</c:otherwise>
											</c:choose>
										</c:if>
									</logic:equal>
								</c:if>								
							</td>
							<td nowrap="nowrap">
								<!-- button add sub service -->
								<logic:notEqual value="PS2" name="CUSTOMER_PLAN" property="planStatus">
									<logic:equal name="CUSTOMER_PLAN" property="planType" value="NP">
										<c:if test="${'BS0' eq service.billingStatus or 'BS1' eq service.billingStatus}">
											<c:choose> 
							 					<c:when test="${service.serviceStatus eq 'PS3' and 'BS1' eq service.billingStatus}">
							 						<c:if test="${'1'eq LOAD_OBJECT.EditActivePlanAMTFlg}">
							 							<input type="button" value="<bean:message key="screen.b_cpm.button.addSubPlan"/>" onclick="javascript: addNewSubPlan(this);"/>
														<input type="button" value="<bean:message key="screen.b_cpm.button.addOptionService"/>" onclick="javascript: addNewOptionService(this);"/>
							 						</c:if>
							 					</c:when>
							 					<c:otherwise>
							 						<input type="button" value="<bean:message key="screen.b_cpm.button.addSubPlan"/>" onclick="javascript: addNewSubPlan(this);"/>
													<input type="button" value="<bean:message key="screen.b_cpm.button.addOptionService"/>" onclick="javascript: addNewOptionService(this);"/>
							 					</c:otherwise>
											</c:choose>
										</c:if>
									</logic:equal>
									<logic:equal name="CUSTOMER_PLAN" property="planType" value="SP">
										<c:if test="${'BS0' eq service.billingStatus or 'BS1' eq service.billingStatus}">
											<c:choose> 
							 					<c:when test="${service.serviceStatus eq 'PS3' and 'BS1' eq service.billingStatus}">
							 						<c:if test="${'1'eq LOAD_OBJECT.EditActivePlanAMTFlg}">
							 							<input type="button" value="<bean:message key="screen.b_cpm.button.addSubPlanOptSrv"/>" onclick="javascript: addSubPlanOptionService(this);"/>
							 						</c:if>
							 					</c:when>
							 					<c:otherwise>
							 						<input type="button" value="<bean:message key="screen.b_cpm.button.addSubPlanOptSrv"/>" onclick="javascript: addSubPlanOptionService(this);"/>
							 					</c:otherwise>
											</c:choose>
										</c:if>
									</logic:equal>
								</logic:notEqual>
							</td>
						</tr>
						<tr></tr>
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
										<c:if test="${action ne 'new'}">
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
										</c:if>	
										<c:if test="${action eq 'new'}">
										<!-- wcbeh@20160921 - Master Location for active(new) -->
											<td class="customerPlanHeaderItem" nowrap="nowrap" style="text-align:left;">
												<div>
													<span style="width:24%">
														<bean:message key="screen.b_cpm.label.billingDesc"/>
													</span>
													<span>
													<logic:equal value="1" property="masterLocationDisplayFlg" name="CUSTOMER_PLAN">
														<nested:hidden property="masterLocationFlg"></nested:hidden>
														<nested:equal property="masterLocationFlg" value="1">
															<nested:checkbox property="masterLocationFlg" value="1" styleClass="masterLocationFlg" onclick="javascript: checkMasterLocation(this);"/>
														</nested:equal>
														<nested:notEqual property="masterLocationFlg" value="1">
															<nested:checkbox property="masterLocationFlg" styleClass="masterLocationFlg" onclick="javascript: checkMasterLocation(this);"/>
														</nested:notEqual>
														<bean:message key="screen.b_cpm.label.masterLocation"/>
								        				<bean:message key="screen.b_cpm.label.colon"/>
								        				&nbsp;&nbsp;
								        				<nested:select property="masterLocation" styleClass="masterLocation" onchange="javascript: changeLocation(this);">
																<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
																<html:optionsCollection name="LIST_MASTER_LOCATION" label="id" value="id"/>
														</nested:select> 
													</logic:equal>
													</span>
													<logic:equal value="1" property="m_jnmDisplayFlg" name="CUSTOMER_PLAN">
														<span style="text-align:right;font-weight:normal;padding-right:14px;width:39%">
								        					<nested:checkbox  property="jobNoAllChk" styleClass="jobNoAllChk" onclick="JobNoAllChkEvt('JobNoAllChkClick',this)"/>
								        						<bean:message key="screen.b_cpm.label.jobNo"/>.
								        						<bean:message key="screen.b_cpm.label.colon"/>
								        						&nbsp;&nbsp;
								        					<nested:select property="jobNoAllJob" styleClass="jobNoAllJob" onchange="JobNoAllChkEvt('JobNoAllJboChange',this)">
																	<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
																	<option value="add">-----add one-----</option>
																	<html:optionsCollection name="LOAD_OBJECT" property="JOB_NO" label="JOB_NO" value="JOB_NO"/>
								        					</nested:select>
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
										<td  nowrap="nowrap" style="text-align:left;padding-left:0px;">
											<nested:textarea property="billDesc" styleClass="billDesc" style="overflow-y:visible;height:40px;"/>
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
					<logic:equal name="CUSTOMER_PLAN" property="planType" value="NP">
					<%n=n+1;%>
					<div class="subPlan">
					<fieldset>
						<legend>
							<div class="subPlanLabel" style="font-weight: bold;color:#4876FF;">[<span id="planNo"><%=n%></span>][<bean:message key="screen.b_cpm.label.subPlan"/>]</div>
						</legend>
						<logic:notEqual value="PS2" name="CUSTOMER_PLAN" property="planStatus">
							<c:if test="${'BS0' eq service.billingStatus or 'BS1' eq service.billingStatus}">
								<c:choose>
									<c:when test="${service.serviceStatus eq 'PS3' and 'BS1' eq service.billingStatus}">
										<c:if test="${LOAD_OBJECT.EditActivePlanAMTFlg eq '1'}">
											<span class="removeX" style="cursor: pointer;" onclick="javascript:removeService(this);">Remove &nbsp;<img src="../image/delete.gif"/></span>
										</c:if>
									</c:when>
									<c:otherwise>
										<span class="removeX" onclick="javascript: removeSubPlan(this);"><a href="javascript:void(0)">Remove &nbsp;<img src="../image/delete.gif"/></a></span>
									</c:otherwise>
								</c:choose>
							</c:if>
						</logic:notEqual>
						<nested:hidden property="idCustPlanLink" styleClass="idCustPlanLink"/>
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
									<nested:hidden property="itemType" styleClass="itemType"/>
								</td>
								<td nowrap="nowrap"><bean:message key="screen.b_cpm.label.serviceName"/><span style="color:red"><bean:message key="screen.b_cpm.label.star"/></span></td>
								<td nowrap="nowrap">
									<bean:message key="screen.b_cpm.label_colon"/>
									<nested:text property="planDesc" styleClass="planDesc" style="width: 95%" maxlength="150"/>
								</td>
								<td colspan="3">&nbsp;</td>
							</tr>
							--%>
							<tr>
								<td><nested:hidden property="itemType" styleClass="itemType"/></td>
								<td nowrap="nowrap" style="width:33%;"><bean:message key="screen.b_cpm.label.itemDescription"/><span style="color:red">
								<bean:message key="screen.b_cpm.label.star"/></span></td>
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
								<c:choose>
								    <c:when test="${'BS2' eq service.billingStatus and '6' eq subPlan.rateMode}">
									    <nested:checkbox property="isDisplayJobNo" styleClass="isDisplayJobNo" value="1" disabled="true"/>
									    <nested:hidden property="isDisplayJobNo"></nested:hidden>
								    </c:when>
								    <c:otherwise>
								    	<nested:checkbox property="isDisplayJobNo" styleClass="isDisplayJobNo" value="1"/>
								    </c:otherwise>
								</c:choose>
								    <bean:message key="screen.b_cpm.label.jobNo"/>&nbsp;&nbsp;&nbsp;&nbsp;
									<bean:message key="screen.b_cpm.label_colon"/>
									<c:choose>
								        <c:when test="${'BS2' eq service.billingStatus and '6' eq subPlan.rateMode}">
											<nested:select property="jobNo" styleClass="jobNo" disabled="true">
												<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
												<option value="add">-----add one-----</option>
												<html:optionsCollection name="LOAD_OBJECT" property="JOB_NO" label="JOB_NO" value="JOB_NO"/>
											</nested:select>
											<nested:hidden property="jobNo"></nested:hidden>
										</c:when>
										<c:otherwise>
										    <nested:select property="jobNo" styleClass="jobNo">
												<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
												<option value="add">-----add one-----</option>
												<html:optionsCollection name="LOAD_OBJECT" property="JOB_NO" label="JOB_NO" value="JOB_NO"/>
											</nested:select>
										</c:otherwise>
								    </c:choose>
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
									<c:choose>
									    <c:when test="${'BS2' eq service.billingStatus and '6' eq subPlan.rateMode}">
											<nested:textarea property="itemDesc" styleClass="itemDesc" style="overflow-y:visible;height:40px;" readonly="true"/>
										</c:when>
										<c:otherwise>
											<nested:textarea property="itemDesc" styleClass="itemDesc" style="overflow-y:visible;height:40px;"/>
										</c:otherwise>
									</c:choose>
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
											<c:choose>
								    		<c:when test="${'BS2' eq service.billingStatus and '6' eq subPlan.rateMode}">
											<td valign="top" class="numberStyle">
												<div class="subPlanQuantityDiv">
													<nested:write property="quantity"/>
												</div>
											<nested:hidden property="quantity" styleClass="subPlanQuantity"/>
											</td>
											<td valign="top" class="numberStyle">
												<div class="subPlanUnitPriceDiv">
													<nested:write property="unitPrice"/>
												</div>
											<nested:hidden property="unitPrice" styleClass="subPlanUnitPrice"/>
											</td>
											<td valign="top" class="numberStyle">
												<div class="subPlanAmountDiv">
													<nested:write property="amount"/>
												</div>
											<nested:hidden property="amount" styleClass="subPlanAmount"/>
											</td>
										</c:when>
										<c:otherwise>
											<td valign="top">
												<nested:text property="quantity" styleClass="subPlanQuantity" style="width:100%" onchange="javascript: updateAmount(this);" onkeyup="javascript: updateAmount_enter(this,event);"/>
											</td>
											<td valign="top">
												<nested:text property="unitPrice" styleClass="subPlanUnitPrice" style="width:100%" onchange="javascript: updateAmount(this);" onkeyup="javascript: updateAmount_enter(this,event);"/>
											</td>
											<td valign="top">
												<nested:text property="amount" styleClass="subPlanAmount" style="width:100%; readonly:true;"/>
											</td>
										</c:otherwise>
										</c:choose>
							    		<td>&nbsp;</td>
										</tr>
										<tr>
											<td class="serviceDiscount"><bean:message key="screen.b_cpm.label.Discount"/><bean:message key="screen.b_cpm.label_colon"/></td>
											<td valign="top" nowrap="nowrap" class="numberStyle">
												<nested:radio property="isDiscountOneTime" styleClass="isDiscountOneTime" value="O" /><bean:message key="screen.b_cpm.label.onetime"/></td>
											<c:choose>
								    		<c:when test="${'BS2' eq service.billingStatus and '6' eq subPlan.rateMode}">
											<td valign="top" class="numberStyle">
												<nested:hidden property="discamount" styleClass="subDiscount"/>
												<div class="subDiscount"><nested:write property="discamount"/></div>
											</td>
											</c:when>
											<c:otherwise>
												<td valign="top"><nested:text property="discamount" styleClass="subDiscount" style="width:100%" onchange="javascript: updateDiscount(this);" onkeyup="javascript: updateDiscount_enter(this,event);"/></td>
											</c:otherwise>
											</c:choose>
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
							<logic:notEqual value="1" property="customerPlan.m_jnmDisplayFlg" name="_B_CPM_S02Form">
							    <nested:hidden property="isDisplayJobNo" styleClass="isDisplayJobNo"/>
							    <nested:hidden property="jobNo" styleClass="jobNo"/>
							</logic:notEqual>
						</table>
						<table width="100%">
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
								<td nowrap="nowrap" class="billAcountCode">
								    <bean:message key="screen.b_cpm.label.billingAccCode"/>
								    <span style="color:red"><bean:message key="screen.b_cpm.label.star"/></span>
								</td>
								<td nowrap="nowrap">
								    <bean:message key="screen.b_cpm.label_colon"/>
								    <!-- Define svcLevel1&svcLevel2 Start-->
									<%String svc1=""; %>
									<nested:present property="svcLevel1">
										<nested:define id="svcLev1" property="svcLevel1" ></nested:define>
										<%svc1=svcLev1.toString();%>
									</nested:present> 
									<nested:present property="MLABELVALUEBEAN2" name="LOAD_OBJECT">
								    	<nested:define id="svcLev2" property="MLABELVALUEBEAN2" name="LOAD_OBJECT"></nested:define>
											<%
												Map<String, List<LabelValueBean>> resutlMap1=(Map<String, List<LabelValueBean>>)svcLev2;
												List<LabelValueBean> resutlListSvc2=resutlMap1.get(svc1);
												if(resutlListSvc2==null){
												    resutlListSvc2=new ArrayList<LabelValueBean>();
												}
												pageContext.setAttribute("Svc2List",resutlListSvc2,pageContext.SESSION_SCOPE);
												
											%>
									</nested:present>
									<!-- Define svcLevel1&svcLevel2 End-->
								    <c:choose>
								        <c:when test="${'BS2' eq service.billingStatus and '6' eq subPlan.rateMode}">
											<nested:select property="svcLevel1" styleClass="svcLevel1"  onchange="javascript: changeLevel1(this);" disabled="true">
												<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
												<html:optionsCollection name="LOAD_OBJECT" property="SVC_LEVEL1" label="SVC_GRP_NAME" value="SVC_GRP"/>
											</nested:select>
											<nested:hidden property="svcLevel1"></nested:hidden>
											<nested:select property="svcLevel2" styleClass="svcLevel2" onchange="javascript: changeLevel2(this);" disabled="true">
												<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
												<%-- <html:optionsCollection name="LOAD_OBJECT" property="SVC_LEVEL2" label="SVC_NAME" value="ID_SERVICE"/> --%>
												<html:optionsCollection name="Svc2List" label="label" value="value"/>
											</nested:select>
											<nested:hidden property="svcLevel2"></nested:hidden>
										</c:when>
										<c:otherwise>
									    	<nested:select property="svcLevel1" styleClass="svcLevel1"  onchange="javascript: changeLevel1(this);">
												<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
												<html:optionsCollection name="LOAD_OBJECT" property="SVC_LEVEL1" label="SVC_GRP_NAME" value="SVC_GRP"/>
											</nested:select>
											<nested:select property="svcLevel2" styleClass="svcLevel2" onchange="javascript: changeLevel2(this);">
												<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
												<%-- <html:optionsCollection name="LOAD_OBJECT" property="SVC_LEVEL2" label="SVC_NAME" value="ID_SERVICE"/> --%>
												<html:optionsCollection name="Svc2List" label="label" value="value"/>
											</nested:select>
										</c:otherwise>
									</c:choose>
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
											<col width="70%"/>
											<col width="30%"/>
										</colgroup>
										<tr>
											<td valign="top">
												<!-- Sub plan detail group -->
												<table width="100%" class="subPlanDetail">
													<colgroup>
														<col width="10%"/>
														<col width="60%"/>
														<col width="30%"/>
													</colgroup>
													<tr class="subPlanDetailHeader">
														<td>
														<c:if test="${service.serviceStatus ne 'PS3'}">
															<span onclick="javascript: addPlanDetail(this);" class="addSubPlanDetail"><a href="javascript:void(0)"><bean:message key="screen.b_cpm.label.add"/></a></span>
														</c:if>
														</td>
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
														<td>
														<c:if test="${service.serviceStatus ne 'PS3'}">
															<span onclick="javascript: deletePlanDetail(this);" class="deleteSubPlanDetail">
															<img class="imgDeleteOption" src="../image/delete.gif"/>
															</span>&nbsp;
														</c:if>
														</td>
														<td valign="top">
															<nested:hidden property="idCustPlanSvc" styleClass="idCustPlanSvc"/>
															<nested:present property="MLABELVALUEBEAN3" name="LOAD_OBJECT">
																<nested:define id="svcLev3" property="MLABELVALUEBEAN3" name="LOAD_OBJECT"></nested:define>
																	<%
																		Map<String, List<LabelValueBean>> resutlMap2=(Map<String, List<LabelValueBean>>)svcLev3;
																		List<LabelValueBean> resutlListSvc3=resutlMap2.get(svc1);
																		if(resutlListSvc3==null){
																		    resutlListSvc3=new ArrayList<LabelValueBean>();
																		}
																		pageContext.setAttribute("Svc3List",resutlListSvc3);
																	%>
															</nested:present> 
															<nested:present property="MLABELVALUEBEAN4" name="LOAD_OBJECT">
																<nested:define id="svcLev4" property="MLABELVALUEBEAN4" name="LOAD_OBJECT"></nested:define>
																	<%
																		Map<String, List<LabelValueBean>> resutlMap3=(Map<String, List<LabelValueBean>>)svcLev4;
																		List<LabelValueBean> resutlListSvc4=resutlMap3.get(svc1);
																		if(resutlListSvc4==null){
																		    resutlListSvc4=new ArrayList<LabelValueBean>();
																		}
																		pageContext.setAttribute("Svc4List",resutlListSvc4);
																	%>
															</nested:present> 
															<c:choose>
								        						<c:when test="${'BS2' eq service.billingStatus and '6' eq subPlan.rateMode}">
																	<nested:select property="svcLevel3" styleClass="svcLevel3">
																		<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
																		<%-- html:optionsCollection name="LOAD_OBJECT" property="SVC_LEVEL3" label="SVC_NAME" value="ID_SERVICE"/> --%>
																		<html:optionsCollection name="Svc3List" label="label" value="value"/>
																	</nested:select><br/>
																	<nested:hidden property="svcLevel3"></nested:hidden>
																	<nested:select property="svcLevel4" styleClass="svcLevel4" disabled="true">
																		<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
																		<%-- <html:optionsCollection name="LOAD_OBJECT" property="SVC_LEVEL4" label="SVC_NAME" value="ID_SERVICE"/> --%>
																		<html:optionsCollection name="Svc4List" label="label" value="value"/>
																	</nested:select>
																	<nested:hidden property="svcLevel4"></nested:hidden>
																</c:when>
																<c:otherwise> 
																    <nested:select property="svcLevel3" styleClass="svcLevel3">
																		<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
																		<%-- <html:optionsCollection name="LOAD_OBJECT" property="SVC_LEVEL3" label="SVC_NAME" value="ID_SERVICE"/> --%>
																		<html:optionsCollection name="Svc3List" label="label" value="value"/>
																	</nested:select><br/>
																	<nested:select property="svcLevel4" styleClass="svcLevel4" >
																		<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
																		<%-- <html:optionsCollection name="LOAD_OBJECT" property="SVC_LEVEL4" label="SVC_NAME" value="ID_SERVICE"/> --%>
																		<html:optionsCollection name="Svc4List" label="label" value="value"/>
																	</nested:select>
																</c:otherwise>
															</c:choose>
														</td>
														<td valign="top">
														    <c:choose>
								        						<c:when test="${'BS2' eq service.billingStatus and '6' eq subPlan.rateMode}">
																	<nested:select property="vendor" styleClass="vendor" disabled="true">
																		<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
																		<html:optionsCollection name="LOAD_OBJECT" property="VENDOR" label="SUPPLIER_NAME" value="ID_SUPPLIER"/>
																	</nested:select>
																	<nested:hidden property="vendor"></nested:hidden>
																</c:when>
																<c:otherwise>
																    <nested:select property="vendor" styleClass="vendor">
																		<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
																		<html:optionsCollection name="LOAD_OBJECT" property="VENDOR" label="SUPPLIER_NAME" value="ID_SUPPLIER"/>
																	</nested:select>
																</c:otherwise>
															</c:choose>
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
															</td>
															<td valign="top"class="subPlanDetailResult">
																<br/>
																<div class="lineDesc" id="lineDescEmpty" style="word-break:break-all">
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
														<bean:message key="screen.b_cpm.label_percentsymbol"/>&nbsp;
													</td>
												</tr>
												<tr class="subPlanDetailResult">
												<c:choose>
								        			<c:when test="${'BS2' eq service.billingStatus and '6' eq subPlan.rateMode}">
														<td rowspan="2">
															<nested:select property="rateType" styleClass="rateType" disabled="true" style="width:139px">
																<%-- <option value=""><bean:message key="screen.b_cpm.listBox.default"/></option> --%>
																<html:optionsCollection name="LIST_RATETYPE" label="name" value="id"/>
															</nested:select>
															<nested:hidden property="rateType"></nested:hidden>
															<c:if test="${LOAD_OBJECT.RateType2Flg eq '1'}">
															<nested:select property="rateType2" styleClass="rateType2">
																<html:optionsCollection name="LIST_RATETYPE2" label="name" value="id"/>
															</nested:select>
															</c:if>
															<c:if test="${LOAD_OBJECT.RateType2Flg ne '1'}">
															<nested:select property="rateType2" styleClass="rateType2" disabled="true">
																<html:optionsCollection name="LIST_RATETYPE2" label="name" value="id"/>
															</nested:select>
															</c:if>
															
														</td>
														<td>
															<nested:select property="rateMode" styleClass="rateMode" disabled="true" style="width:139px" onchange="changeRateType(this)">
																<%-- <option value=""><bean:message key="screen.b_cpm.listBox.default"/></option> --%>
																<html:optionsCollection name="MODE_LIST" label="name" value="id"/>
															</nested:select>
															<nested:hidden property="rateMode"></nested:hidden>
														</td>
														<td>
															<nested:select property="applyGst" styleClass="applyGst" disabled="true" onchange="javascript: onChangeGSTValue(this);">
													  		 <html:options collection="LIST_GST" property="id" labelProperty="name"/>
															</nested:select>
															<nested:hidden property="applyGst"></nested:hidden>
															<select name="applyGstValue" class="applyGstValue" style="display: none;">
																<logic:iterate id="gstValue" name="LIST_GST_VALUE">
																<option value="${gstValue.id}">${gstValue.name}</option>
																</logic:iterate>
															</select>
														</td>
													</c:when>
													<c:otherwise>
													    <td rowspan="2">
															<nested:select property="rateType" styleClass="rateType" style="width:139px">
																<%-- <option value=""><bean:message key="screen.b_cpm.listBox.default"/></option> --%>
																<html:optionsCollection name="LIST_RATETYPE" label="name" value="id"/>
															</nested:select>
															<c:if test="${LOAD_OBJECT.RateType2Flg eq '1'}">
																<nested:select property="rateType2" styleClass="rateType2">
																	<html:optionsCollection name="LIST_RATETYPE2" label="name" value="id"/>
																</nested:select>
															</c:if>
															<c:if test="${LOAD_OBJECT.RateType2Flg ne '1'}">
																<nested:select property="rateType2" styleClass="rateType2" disabled="true">
																	<html:optionsCollection name="LIST_RATETYPE2" label="name" value="id"/>
																</nested:select>
															</c:if>
															
														</td>
														<!-- In progress 'Rate Mode' is notEditable Start-->
														<c:choose>
															<c:when test="${'BS2' eq service.billingStatus}">
																<td>
																	<nested:select property="rateMode" styleClass="rateMode" disabled="true" style="width:139px" onchange="changeRateType(this)">
																		<html:optionsCollection name="MODE_LIST" label="name" value="id"/>
																	</nested:select>
																	<nested:hidden property="rateMode"></nested:hidden>
																</td>
															</c:when>
															<c:otherwise>
																<td>
																	<nested:select property="rateMode" styleClass="rateMode" style="width:139px" onchange="changeRateType(this)">
																		<html:optionsCollection name="MODE_LIST" label="name" value="id"/>
																	</nested:select>
																</td>
															</c:otherwise>
														</c:choose>
														<!-- In progress 'Rate Mode' is notEditable End-->
														<td>
														<nested:select property="applyGst" styleClass="applyGst"  onchange="javascript: onChangeGSTValue(this);">
													  		 <html:options collection="LIST_GST" property="id" labelProperty="name"/>
														</nested:select>
														<select name="applyGstValue" class="applyGstValue" style="display: none;">
															<logic:iterate id="gstValue" name="LIST_GST_VALUE">
															<option value="${gstValue.id}">${gstValue.name}</option>
															</logic:iterate>
														</select>
														</td>
													</c:otherwise>
												</c:choose>
												</tr>
												<tr class="subPlanDetailResult">
													<td colspan="2" nowrap="nowrap" class="detailGst">
														<c:choose>
								        					<c:when test="${'BS2' eq service.billingStatus and '6' eq subPlan.rateMode}">
															<div style="font-style:italic;"><bean:message key="screen.b_cpm.label.rate"/>
															<span class="displayCurrency">(<nested:write property="currency"/>)</span>:
															<nested:text property="rate" style="width: 100px" styleClass="rate" readonly="true"/>
															</div>
									                            <nested:hidden property="rate"></nested:hidden>
									                        </c:when>
									                        <c:otherwise>
									                         <div  style="font-style:italic;"><bean:message key="screen.b_cpm.label.rate"/>
															<span class="displayCurrency">(<nested:write property="currency"/>)</span>:
															<nested:text property="rate" style="width: 100px" styleClass="rate"/>
															</div>
														
									                        </c:otherwise>
							                            </c:choose>
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
					</div>
					</logic:equal><!-- end check for non standard plan -->
					<!-- edit standard plan start -->
					<logic:equal name="CUSTOMER_PLAN" property="planType" value="SP">
					<%n=n+1;%>
					<div class="subPlan">
					<fieldset>
						<legend>
							<div class="subPlanLabel" style="font-weight: bold;color:#4876FF;">[<span id="planNo"><%=n%></span>][<bean:message key="screen.b_cpm.label.subPlan"/>]
							[<nested:write property="planName"/>]
							[<nested:write property="planDesc"/>]
							[<nested:write property="itemGrpName"/>]
							</div>
						</legend>
						<logic:notEqual value="PS2" name="CUSTOMER_PLAN" property="planStatus">
							<c:if test="${'BS0' eq service.billingStatus or 'BS1' eq service.billingStatus}">
								<c:choose>
									<c:when test="${service.serviceStatus eq 'PS3' and 'BS1' eq service.billingStatus}">
										<c:if test="${LOAD_OBJECT.EditActivePlanAMTFlg eq '1'}">
											<span class="removeX" onclick="javascript: removeSubPlan(this);"><a href="javascript:void(0)">Remove &nbsp;<img src="../image/delete.gif"/></a></span>
										</c:if>
									</c:when>
									<c:otherwise>
											<span class="removeX" onclick="javascript: removeSubPlan(this);"><a href="javascript:void(0)">Remove &nbsp;<img src="../image/delete.gif"/></a></span>
									</c:otherwise>
								</c:choose>
							</c:if>
						</logic:notEqual>
						<nested:hidden property="idCustPlanLink" styleClass="idCustPlanLink"/>
						<nested:hidden property="idPlan" styleClass="idPlan"/>
						<nested:hidden property="idPlanGrp" styleClass="idPlanGrp"/>
						<nested:hidden property="planName" styleClass="planName"/>
						<nested:hidden property="planDesc" styleClass="planDesc"/>
						<nested:hidden property="itemGrpName" styleClass="itemGrpName"/>
						<nested:hidden property="itemType" styleClass="itemType"/>
						<nested:hidden property="svcLevel1" styleClass="svcLevel1"/>
						<input type="hidden" name="svcLevel2" value="<nested:write property="svcLevel2"/>" class="svcLevel2"/>
						<nested:hidden property="rateType" styleClass="rateType"/>
						<%-- <nested:hidden property="rateMode" styleClass="rateMode"/> --%>
						<nested:hidden property="rate" styleClass="rate"/>
						<%-- <nested:hidden property="applyGst" styleClass="applyGst"/> --%>
						<nested:hidden property="currency" styleClass="currency"/>
						
						<table width="100%" class="subPlanContent" cellpadding="0" cellspacing="0">
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
							<tr>
								<td>&nbsp;</td>
								<td  nowrap="nowrap" style="width:33%;"><bean:message key="screen.b_cpm.label.itemDescription"/><span style="color:red"><bean:message key="screen.b_cpm.label.star"/></span></td>
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
										<c:choose>
									    <c:when test="${'BS2' eq service.billingStatus and '6' eq subPlan.rateMode}">
											<nested:checkbox property="isDisplayJobNo" styleClass="isDisplayJobNo" value="1" disabled="true"/>
											<nested:hidden property="isDisplayJobNo"></nested:hidden>
										</c:when>
										<c:otherwise>
											<nested:checkbox property="isDisplayJobNo" styleClass="isDisplayJobNo" value="1"/>
										</c:otherwise>
									</c:choose>
										<bean:message key="screen.b_cpm.label.jobNo"/>&nbsp;&nbsp;&nbsp;&nbsp;
										<bean:message key="screen.b_cpm.label_colon"/>
									<c:choose>
									    <c:when test="${'BS2' eq service.billingStatus and '6' eq subPlan.rateMode}">
											<nested:select property="jobNo" styleClass="jobNo" disabled="true">
												<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
												<option value="add">-----add one-----</option>
												<html:optionsCollection name="LOAD_OBJECT" property="JOB_NO" label="JOB_NO" value="JOB_NO"/>
											</nested:select>
											<nested:hidden property="jobNo"></nested:hidden>
										</c:when>
										<c:otherwise>
											<nested:select property="jobNo" styleClass="jobNo">
												<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
												<option value="add">-----add one-----</option>
												<html:optionsCollection name="LOAD_OBJECT" property="JOB_NO" label="JOB_NO" value="JOB_NO"/>
											</nested:select>
										</c:otherwise>
									</c:choose>
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
									<c:choose>
									    <c:when test="${'BS2' eq service.billingStatus and '6' eq subPlan.rateMode}">
											<nested:textarea property="itemDesc" styleClass="itemDesc" style="overflow-y:visible;height:40px;" readonly="true"/>
										</c:when>
										<c:otherwise>
											<nested:textarea property="itemDesc" styleClass="itemDesc" style="overflow-y:visible;height:40px;"/>
										</c:otherwise>
									</c:choose>
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
											<c:choose>
								    		<c:when test="${'BS2' eq service.billingStatus and '6' eq subPlan.rateMode}">
												<td valign="top" class="numberStyle">
													<div class="subPlanQuantityDiv">
														<nested:write property="quantity"/>
													</div>
													<nested:hidden property="quantity" styleClass="subPlanQuantity"/>
												</td>
												<td valign="top" class="numberStyle">
													<div class="subPlanUnitPriceDiv">
														<nested:write property="unitPrice"/>
													</div>
													<nested:hidden property="unitPrice" styleClass="subPlanUnitPrice"/>
												</td>
												<td valign="top" class="numberStyle">
													<div class="subPlanAmountDiv">
														<nested:write property="amount"/>
													</div>
													<nested:hidden property="amount" styleClass="subPlanAmount"/>
												</td>
											</c:when>
											<c:otherwise>
												<td valign="top">
													<nested:text property="quantity" styleClass="subPlanQuantity" style="width:100%" onchange="javascript: updateAmount(this);" onkeyup="javascript: updateAmount_enter(this,event);"/>
												</td>
												<td valign="top">
													<nested:text property="unitPrice" styleClass="subPlanUnitPrice" style="width:100%" onchange="javascript: updateAmount(this);" onkeyup="javascript: updateAmount_enter(this,event);"/>
												</td>
												<td valign="top">
													<nested:text property="amount" styleClass="subPlanAmount" style="width:100%; readonly:true;"/>
												</td>
											</c:otherwise>
											</c:choose>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td class="serviceDiscount"><bean:message key="screen.b_cpm.label.Discount"/><bean:message key="screen.b_cpm.label_colon"/></td>
											<td valign="top" nowrap="nowrap" class="numberStyle"><nested:radio property="isDiscountOneTime" styleClass="isDiscountOneTime" value="O" /><bean:message key="screen.b_cpm.label.onetime"/></td>
											<c:choose>
								 			<c:when test="${'BS2' eq service.billingStatus and '6' eq subPlan.rateMode}">
												<td valign="top" class="numberStyle">
													<nested:hidden property="discamount" styleClass="subDiscount"/>
													<div class="subDiscount"><nested:write property="discamount"/></div>
												</td>
											</c:when>
											<c:otherwise>
												<td valign="top"><nested:text property="discamount" styleClass="subDiscount" style="width:100%" onchange="javascript: updateDiscount(this);" onkeyup="javascript: updateDiscount_enter(this,event);"/></td>
											</c:otherwise>
											</c:choose>
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
							<logic:notEqual value="1" property="customerPlan.m_jnmDisplayFlg" name="_B_CPM_S02Form">
							    <nested:hidden property="isDisplayJobNo" styleClass="isDisplayJobNo"/>
							    <nested:hidden property="jobNo" styleClass="jobNo"/>
							</logic:notEqual>
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
									<!-- Define svcLevel1&svcLevel2 Start-->
									<%String svc1=""; %>
									<nested:present property="svcLevel1">
										<nested:define id="svcLev1" property="svcLevel1" ></nested:define>
										<%svc1=svcLev1.toString();%>
									</nested:present> 
									<!--  Define svcLevel1&svcLevel2 End -->
									<div style="word-break:break-all;">
									<logic:iterate id="SVC_LEVEL1" name="LOAD_OBJECT" property="SVC_LEVEL1">
									<nested:equal property="svcLevel1" value="${SVC_LEVEL1.SVC_GRP}">
										<bean:write name="SVC_LEVEL1" property="SVC_GRP_NAME"/>
									</nested:equal>
									</logic:iterate>&nbsp;
									<bean:message key="screen.b_cpm.label.empty"/>&nbsp;
									<logic:iterate id="SVC_LEVEL2" name="LOAD_OBJECT" property="SVC_LEVEL2">
									<nested:equal property="svcLevel2" value="${SVC_LEVEL2.ID_SERVICE}">
										<bean:write name="SVC_LEVEL2" property="SVC_NAME"/>
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
									<table>
										<colgroup>
											<col width="70%"/>
											<col width="30%"/>
										</colgroup>
										<tr>
											<td valign="top">
												<!-- Sub plan detail group -->
												<table class="subPlanDetail" style="width: 100%">
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
													<nested:iterate property="subPlanDetails" indexId="k">
													<tr class="clonePlanDetail">
														<td valign="top">
															<nested:hidden property="idCustPlanSvc" styleClass="idCustPlanSvc"/>
															<%-- <nested:hidden property="svcLevel3" styleClass="svcLevel3"/> --%>
															<nested:hidden property="svcLevel4" styleClass="svcLevel4"/>
															<nested:hidden property="vendor" styleClass="vendor"/>
															<!-- <div style="word-break:break-all;"> -->
															<%-- <logic:iterate id="SVC_LEVEL3" name="LOAD_OBJECT" property="SVC_LEVEL3">
															<nested:equal property="svcLevel3" value="${SVC_LEVEL3.ID_SERVICE}">
																<bean:write name="SVC_LEVEL3" property="SVC_NAME"/>
															</nested:equal>
															</logic:iterate> --%>
															<nested:present property="MLABELVALUEBEAN3" name="LOAD_OBJECT">
																<nested:define id="svcLev3" property="MLABELVALUEBEAN3" name="LOAD_OBJECT"></nested:define>
																	<%
																		Map<String, List<LabelValueBean>> resutlMap2=(Map<String, List<LabelValueBean>>)svcLev3;
																		List<LabelValueBean> resutlListSvc3=resutlMap2.get(svc1);
																		if(resutlListSvc3==null){
																		    resutlListSvc3=new ArrayList<LabelValueBean>();
																		}
																		pageContext.setAttribute("Svc3List",resutlListSvc3);
																	%>
															</nested:present> 
															<nested:select property="svcLevel3" styleClass="svcLevel3">
																<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
																	<html:optionsCollection name="Svc3List" label="label" value="value"/>
															</nested:select>
															<br/>
															<logic:iterate id="SVC_LEVEL4" name="LOAD_OBJECT" property="SVC_LEVEL4">
															<nested:equal property="svcLevel4" value="${SVC_LEVEL4.ID_SERVICE}">
																<bean:write name="SVC_LEVEL4" property="SVC_NAME"/>
															</nested:equal>
															</logic:iterate>
															<!-- </div> -->
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
														<bean:message key="screen.b_cpm.label_percentsymbol"/>&nbsp;
													</td>
												</tr>
												<tr class="subPlanDetailResult">
												<c:choose>
								        			<c:when test="${'BS2' eq service.billingStatus and '6' eq subPlan.rateMode}">
													<td rowspan="2">
														<nested:select property="rateType" styleClass="rateType" disabled="true" style="width:139px">
															<%-- <option value=""><bean:message key="screen.b_cpm.listBox.default"/></option> --%>
															<html:optionsCollection name="LIST_RATETYPE" label="name" value="id"/>
														</nested:select>
														<c:if test="${LOAD_OBJECT.RateType2Flg eq '1'}">
															<nested:select property="rateType2" styleClass="rateType2">
															<html:optionsCollection name="LIST_RATETYPE2" label="name" value="id"/>
														</nested:select>
														</c:if>
														<c:if test="${LOAD_OBJECT.RateType2Flg ne '1'}">
															<nested:select property="rateType2" styleClass="rateType2" disabled="true">
																<html:optionsCollection name="LIST_RATETYPE2" label="name" value="id"/>
															</nested:select>
														</c:if>
														
													</td>
													<td>											
														<nested:select property="rateMode" styleClass="rateMode" disabled="true" style="width:139px" onchange="changeRateType(this)">
															<%-- <option value=""><bean:message key="screen.b_cpm.listBox.default"/></option> --%>
															<html:optionsCollection name="MODE_LIST" label="name" value="id"/>
														</nested:select>
														<nested:hidden property="rateMode" styleClass="rateMode"/>
													</td>
													<td>
													<nested:select property="applyGst" styleClass="applyGst" disabled="true" onchange="javascript: onChangeGSTValue(this);">
													  	<html:options collection="LIST_GST" property="id" labelProperty="name"/>
													</nested:select>
													<nested:hidden property="applyGst" styleClass="applyGst"/>
													<select name="applyGstValue" class="applyGstValue" style="display: none;">
														<logic:iterate id="gstValue" name="LIST_GST_VALUE">
														<option value="${gstValue.id}">${gstValue.name}</option>
														</logic:iterate>
													</select>
													</td>
													</c:when>
													<c:otherwise>
													<td rowspan="2">
														<nested:select property="rateType" styleClass="rateType" disabled="true" style="width:139px">
															<%-- <option value=""><bean:message key="screen.b_cpm.listBox.default"/></option> --%>
															<html:optionsCollection name="LIST_RATETYPE" label="name" value="id"/>
														</nested:select>
														<c:if test="${LOAD_OBJECT.RateType2Flg eq '1'}">
															<nested:select property="rateType2" styleClass="rateType2">
																<html:optionsCollection name="LIST_RATETYPE2" label="name" value="id"/>
															</nested:select>
														</c:if>
														<c:if test="${LOAD_OBJECT.RateType2Flg ne '1'}">
															<nested:select property="rateType2" styleClass="rateType2" disabled="true">
																<html:optionsCollection name="LIST_RATETYPE2" label="name" value="id"/>
															</nested:select>
														</c:if>
														
													</td>
													<!-- In progress ,'Rate Mode' is notEditable Start-->
														<c:choose>
															<c:when test="${'BS2' eq service.billingStatus}">
																<td>
																	<nested:select property="rateMode" styleClass="rateMode" disabled="true" style="width:139px" onchange="changeRateType(this)">
																		<html:optionsCollection name="MODE_LIST" label="name" value="id"/>
																	</nested:select>
																	<nested:hidden property="rateMode"></nested:hidden>
																</td>
															</c:when>
															<c:otherwise>
																<td>
																	<nested:select property="rateMode" styleClass="rateMode" style="width:139px" onchange="changeRateType(this)">
																		<html:optionsCollection name="MODE_LIST" label="name" value="id"/>
																	</nested:select>
																</td>
															</c:otherwise>
														</c:choose>
													<!-- In progress, 'Rate Mode' is notEditable End-->
													<td>
														<nested:select property="applyGst" styleClass="applyGst" onchange="javascript: onChangeGSTValue(this);">
													  		<html:options collection="LIST_GST" property="id" labelProperty="name"/>
														</nested:select>
														<select name="applyGstValue" class="applyGstValue" style="display: none;">
															<logic:iterate id="gstValue" name="LIST_GST_VALUE">
															<option value="${gstValue.id}">${gstValue.name}</option>
															</logic:iterate>
														</select>
													</c:otherwise>
												</c:choose>	
												</tr>
												<tr  class="subPlanDetailResult">
													<td colspan="2" nowrap="nowrap" class="detailGst">
														<div  style="font-style:italic;"><bean:message key="screen.b_cpm.label.rate"/>
														<span class="displayCurrency">(<nested:write property="currency"/>)</span>:&nbsp;&nbsp;&nbsp;
														<nested:write property="rate"/>
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
					</div>
					</logic:equal>
					<!-- edit standard plan end -->
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
					<logic:equal name="CUSTOMER_PLAN" property="planType" value="NP">
					<%n=n+1;%>
					<div class="subPlan">
					<fieldset>
						<legend>
							<div class="optionServiceLabel" style="font-weight: bold;color:#4876FF;">[<span id="planNo"><%=n%></span>][<bean:message key="screen.b_cpm.label.optionService"/>]</div>
						</legend>
						<logic:notEqual value="PS2" name="CUSTOMER_PLAN" property="planStatus">
							<c:if test="${'BS0' eq service.billingStatus or 'BS1' eq service.billingStatus}">
								<c:choose>
									<c:when test="${service.serviceStatus eq 'PS3' and 'BS1' eq service.billingStatus}">
										<c:if test="${LOAD_OBJECT.EditActivePlanAMTFlg eq '1'}">
											<span class="removeX" onclick="javascript: removeSubPlan(this);"><a href="javascript:void(0)">Remove &nbsp;<img src="../image/delete.gif"/></a></span>
										</c:if>
									</c:when>
									<c:otherwise>
										<span class="removeX" onclick="javascript: removeSubPlan(this);"><a href="javascript:void(0)">Remove &nbsp;<img src="../image/delete.gif"/></a></span>
									</c:otherwise>
								</c:choose>
							</c:if>
						</logic:notEqual>
						<nested:hidden property="idCustPlanLink" styleClass="idCustPlanLink"/>
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
									<nested:hidden property="itemType" styleClass="itemType"/>
								</td>
								<td nowrap="nowrap"><bean:message key="screen.b_cpm.label.serviceName"/><span style="color:red"><bean:message key="screen.b_cpm.label.star"/></span></td>
								<td nowrap="nowrap">
									<bean:message key="screen.b_cpm.label_colon"/>
									<nested:text property="planDesc" styleClass="planDesc" style="width: 95%"/>
								</td>
								<td colspan="3">&nbsp;</td>
							</tr>
							--%>
							<tr>
								<td><nested:hidden property="itemType" styleClass="itemType"/></td>
								<td nowrap="nowrap" style="width:33%;"><bean:message key="screen.b_cpm.label.itemDescription"/><span style="color:red"><bean:message key="screen.b_cpm.label.star"/></span></td>
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
										<c:choose>
									    <c:when test="${'BS2' eq service.billingStatus and '6' eq subPlan.rateMode}">
											<nested:checkbox property="isDisplayJobNo" styleClass="isDisplayJobNo" value="1" disabled="true"/>
										    <nested:hidden property="isDisplayJobNo"></nested:hidden>
										</c:when>
										<c:otherwise>
										    <nested:checkbox property="isDisplayJobNo" styleClass="isDisplayJobNo" value="1"/>
										</c:otherwise>
							    	</c:choose>
								<bean:message key="screen.b_cpm.label.jobNo"/>&nbsp;&nbsp;&nbsp;&nbsp;
								<bean:message key="screen.b_cpm.label_colon"/>
									<c:choose>
									    <c:when test="${'BS2' eq service.billingStatus and '6' eq subPlan.rateMode}">
											<nested:select property="jobNo" styleClass="jobNo" disabled="true">
												<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
												<option value="add">-----add one-----</option>
												<html:optionsCollection name="LOAD_OBJECT" property="JOB_NO" label="JOB_NO" value="JOB_NO"/>
											</nested:select>
											<nested:hidden property="jobNo"></nested:hidden>
										</c:when>
										<c:otherwise>
											<nested:select property="jobNo" styleClass="jobNo">
												<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
												<option value="add">-----add one-----</option>
												<html:optionsCollection name="LOAD_OBJECT" property="JOB_NO" label="JOB_NO" value="JOB_NO"/>
											</nested:select>
										</c:otherwise>
									</c:choose>
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
									<c:choose>
									    <c:when test="${'BS2' eq service.billingStatus and '6' eq subPlan.rateMode}">
											<nested:textarea property="itemDesc" styleClass="itemDesc" style="overflow-y:visible;height:40px;" readonly="true"/>
										</c:when>
										<c:otherwise>
											<nested:textarea property="itemDesc" styleClass="itemDesc" style="overflow-y:visible;height:40px;"/>
										</c:otherwise>
									</c:choose>
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
											<c:choose>
								    		<c:when test="${'BS2' eq service.billingStatus and '6' eq subPlan.rateMode}">
												<td valign="top" class="numberStyle">
													<div class="subPlanQuantityDiv">
														<nested:write property="quantity"/>
													</div>
													<nested:hidden property="quantity" styleClass="subPlanQuantity"/>
												</td>
												<td valign="top" class="numberStyle">
													<div class="subPlanUnitPriceDiv">
														<nested:write property="unitPrice"/>
													</div>
													<nested:hidden property="unitPrice" styleClass="subPlanUnitPrice"/>
												</td>
												<td valign="top" class="numberStyle">
													<div class="subPlanAmountDiv">
														<nested:write property="amount"/>
													</div>
													<nested:hidden property="amount" styleClass="subPlanAmount"/>
												</td>
											</c:when>
											<c:otherwise>
												<td valign="top">
													<nested:text property="quantity" styleClass="subPlanQuantity" style="width:100%" onchange="javascript: updateAmount(this);" onkeyup="javascript: updateAmount_enter(this,event);"/>
												</td>
												<td valign="top">
													<nested:text property="unitPrice" styleClass="subPlanUnitPrice" style="width:100%" onchange="javascript: updateAmount(this);" onkeyup="javascript: updateAmount_enter(this,event);"/>
												</td>
												<td valign="top">
													<nested:text property="amount" styleClass="subPlanAmount" style="width:100%; readonly:true;"/>
												</td>
											</c:otherwise>
										</c:choose>
										<td>&nbsp;</td>
										</tr>
										<tr>
											<td class="serviceDiscount"><bean:message key="screen.b_cpm.label.Discount"/><bean:message key="screen.b_cpm.label_colon"/></td>
											<td valign="top" nowrap="nowrap" class="numberStyle"><nested:radio property="isDiscountOneTime" styleClass="isDiscountOneTime" value="O"/><bean:message key="screen.b_cpm.label.onetime"/></td>
											<c:choose>
								    		<c:when test="${'BS2' eq service.billingStatus and '6' eq subPlan.rateMode}">
												<td  valign="top" class="numberStyle">
													<nested:hidden property="discamount" styleClass="subDiscount"/>
													<div class="subDiscount"><nested:write property="discamount"/></div>
												</td>
											</c:when>
											<c:otherwise>
												<td valign="top" ><nested:text property="discamount" styleClass="subDiscount" style="width:100%" onchange="javascript: updateDiscount(this);" onkeyup="javascript: updateDiscount_enter(this,event);"/></td>
											</c:otherwise>
											</c:choose>
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
							<logic:notEqual value="1" property="customerPlan.m_jnmDisplayFlg" name="_B_CPM_S02Form">
							    <nested:hidden property="isDisplayJobNo" styleClass="isDisplayJobNo"/>
							    <nested:hidden property="jobNo" styleClass="jobNo"/>
							</logic:notEqual>
						</table>
						<table width="100%">
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
								<td nowrap="nowrap" class="billAcountCode">
								    <bean:message key="screen.b_cpm.label.billingAccCode"/><span style="color:red">
								    <bean:message key="screen.b_cpm.label.star"/></span>
								</td>
								<td nowrap="nowrap">
									<bean:message key="screen.b_cpm.label_colon"/>
									<!-- Define svcLevel1&svcLevel2 Start-->
									<%String svc1=""; %>
									<nested:present property="svcLevel1">
										<nested:define id="svcLev1" property="svcLevel1" ></nested:define>
										<%svc1=svcLev1.toString();%>
									</nested:present> 
									<nested:present property="MLABELVALUEBEAN2" name="LOAD_OBJECT">
								    	<nested:define id="svcLev2" property="MLABELVALUEBEAN2" name="LOAD_OBJECT"></nested:define>
											<%
												Map<String, List<LabelValueBean>> resutlMap=(Map<String, List<LabelValueBean>>)svcLev2;
												List<LabelValueBean> resutlListSvc2=resutlMap.get(svc1);
												if(resutlListSvc2==null){
												    resutlListSvc2=new ArrayList<LabelValueBean>();
												}
												pageContext.setAttribute("Svc2List",resutlListSvc2,pageContext.SESSION_SCOPE);
											%>
									</nested:present> 
									<!-- Define svcLevel1&svcLevel2 End-->
									<c:choose>
									    <c:when test="${'BS2' eq service.billingStatus and '6' eq subPlan.rateMode}">
											<nested:select property="svcLevel1" styleClass="svcLevel1"  onchange="javascript: changeLevel1(this);" disabled="true">
												<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
												<html:optionsCollection name="LOAD_OBJECT" property="SVC_LEVEL1" label="SVC_GRP_NAME" value="SVC_GRP"/>
											</nested:select>
											<nested:hidden property="svcLevel1"></nested:hidden>
											<nested:select property="svcLevel2" styleClass="svcLevel2" onchange="javascript: changeLevel2(this);" disabled="true">
												<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
												<%-- <html:optionsCollection name="LOAD_OBJECT" property="SVC_LEVEL2" label="SVC_NAME" value="ID_SERVICE"/> --%>
												<html:optionsCollection name="Svc2List" label="label" value="value"/>
											</nested:select>
											<nested:hidden property="svcLevel2"></nested:hidden>
										</c:when>
										<c:otherwise>
										    <nested:select property="svcLevel1" styleClass="svcLevel1"  onchange="javascript: changeLevel1(this);">
												<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
												<html:optionsCollection name="LOAD_OBJECT" property="SVC_LEVEL1" label="SVC_GRP_NAME" value="SVC_GRP"/>
											</nested:select>
											<nested:select property="svcLevel2" styleClass="svcLevel2" onchange="javascript: changeLevel2(this);">
												<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
												<%-- <html:optionsCollection name="LOAD_OBJECT" property="SVC_LEVEL2" label="SVC_NAME" value="ID_SERVICE"/> --%>
												<html:optionsCollection name="Svc2List" label="label" value="value"/>
											</nested:select>
										</c:otherwise>
									</c:choose>
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
												<table width="100%"  class="subPlanDetail">
													<colgroup>
														<col width="10%"/>
														<col width="60%"/>
														<col width="30%"/>
													</colgroup>
													<tr class="subPlanDetailHeader">
														<td>																										
														</td>
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
														<td>
															&nbsp;
														</td>
														<td valign="top">
															<nested:hidden property="idCustPlanSvc" styleClass="idCustPlanSvc"/>
															<nested:present property="MLABELVALUEBEAN3" name="LOAD_OBJECT">
																<nested:define id="svcLev3" property="MLABELVALUEBEAN3" name="LOAD_OBJECT"></nested:define>
																	<%
																		Map<String, List<LabelValueBean>> resutlMap=(Map<String, List<LabelValueBean>>)svcLev3;
																		List<LabelValueBean> resutlListSvc3=resutlMap.get(svc1);
																		if(resutlListSvc3==null){
																		    resutlListSvc3=new ArrayList<LabelValueBean>();
																		}
																		pageContext.setAttribute("Svc3List",resutlListSvc3);
																	%>
															</nested:present> 
															<nested:present property="MLABELVALUEBEAN4" name="LOAD_OBJECT">
																<nested:define id="svcLev4" property="MLABELVALUEBEAN4" name="LOAD_OBJECT"></nested:define>
																	<%
																		Map<String, List<LabelValueBean>> resutlMap=(Map<String, List<LabelValueBean>>)svcLev4;
																		List<LabelValueBean> resutlListSvc4=resutlMap.get(svc1);
																		if(resutlListSvc4==null){
																		    resutlListSvc4=new ArrayList<LabelValueBean>();
																		}
																		pageContext.setAttribute("Svc4List",resutlListSvc4);
																	%>
															</nested:present> 
															<c:choose>
									    						<c:when test="${'BS2' eq service.billingStatus and '6' eq subPlan.rateMode}">
																	<nested:select property="svcLevel3" styleClass="svcLevel3">
																		<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
																		<%-- <html:optionsCollection name="LOAD_OBJECT" property="SVC_LEVEL3" label="SVC_NAME" value="ID_SERVICE"/> --%>
																		<html:optionsCollection name="Svc3List" label="label" value="value"/>
																	</nested:select><br/>
																	<nested:hidden property="svcLevel3"></nested:hidden>
																	<nested:select property="svcLevel4" styleClass="svcLevel4" disabled="true">
																		<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
																		<%-- <html:optionsCollection name="LOAD_OBJECT" property="SVC_LEVEL4" label="SVC_NAME" value="ID_SERVICE"/> --%>
																		<html:optionsCollection name="Svc4List" label="label" value="value"/>
																	</nested:select>
																	<nested:hidden property="svcLevel4"></nested:hidden>
																</c:when>
															    <c:otherwise>
															    	<nested:select property="svcLevel3" styleClass="svcLevel3" >
																		<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
																		<%-- <html:optionsCollection name="LOAD_OBJECT" property="SVC_LEVEL3" label="SVC_NAME" value="ID_SERVICE"/> --%>
																		<html:optionsCollection name="Svc3List" label="label" value="value"/>
																	</nested:select><br/>
																	<nested:select property="svcLevel4" styleClass="svcLevel4" >
																		<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
																		<%-- <html:optionsCollection name="LOAD_OBJECT" property="SVC_LEVEL4" label="SVC_NAME" value="ID_SERVICE"/> --%>
																		<html:optionsCollection name="Svc4List" label="label" value="value"/>
																	</nested:select>
															    </c:otherwise>
															</c:choose>
														</td>
														<td valign="top">
														<c:choose>
									    						<c:when test="${'BS2' eq service.billingStatus and '6' eq subPlan.rateMode}">
																	<nested:select property="vendor" styleClass="vendor" disabled="true">
																		<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
																		<html:optionsCollection name="LOAD_OBJECT" property="VENDOR" label="SUPPLIER_NAME" value="ID_SUPPLIER"/>
																	</nested:select>
																	<nested:hidden property="vendor"></nested:hidden>
																</c:when>
																<c:otherwise>
																	<nested:select property="vendor" styleClass="vendor">
																		<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
																		<html:optionsCollection name="LOAD_OBJECT" property="VENDOR" label="SUPPLIER_NAME" value="ID_SUPPLIER"/>
																	</nested:select>
																</c:otherwise>
															</c:choose>
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
														<bean:message key="screen.b_cpm.label_percentsymbol"/>&nbsp;
													</td>
												</tr>
												<tr class="subPlanDetailResult">
													<c:choose>
										    			<c:when test="${'BS2' eq service.billingStatus and '6' eq subPlan.rateMode}">
															<td rowspan="2">
																<nested:select property="rateType" styleClass="rateType" disabled="true" style="width:139px">
																	<%-- <option value=""><bean:message key="screen.b_cpm.listBox.default"/></option> --%>
																	<html:optionsCollection name="LIST_RATETYPE" label="name" value="id"/>
																</nested:select>
																<nested:hidden property="rateType"></nested:hidden>
																<c:if test="${LOAD_OBJECT.RateType2Flg eq '1'}">
																	<nested:select property="rateType2" styleClass="rateType2">
																		<html:optionsCollection name="LIST_RATETYPE2" label="name" value="id"/>
																	</nested:select>
																</c:if>
																<c:if test="${LOAD_OBJECT.RateType2Flg ne '1'}">
																	<nested:select property="rateType2" styleClass="rateType2" disabled="true">
																		<html:optionsCollection name="LIST_RATETYPE2" label="name" value="id"/>
																	</nested:select>
																</c:if>
																
															</td>
															<td>
																<nested:select property="rateMode" styleClass="rateMode" disabled="true" style="width:139px" onchange="changeRateType(this)">
																	<%-- <option value=""><bean:message key="screen.b_cpm.listBox.default"/></option> --%>
																	<html:optionsCollection name="MODE_LIST" label="name" value="id"/>
																</nested:select>
																<nested:hidden property="rateMode"></nested:hidden>
															</td>
															<td>
																<%-- <nested:text property="rate" style="width: 100px" styleClass="rate" readonly="true"/> --%>
																<nested:select property="applyGst" styleClass="applyGst" disabled="true" onchange="javascript: onChangeGSTValue(this);">
													  		 		<html:options collection="LIST_GST" property="id" labelProperty="name"/>
																</nested:select>
																<nested:hidden property="applyGst"></nested:hidden>
																<select name="applyGstValue" class="applyGstValue" style="display: none;">
																	<logic:iterate id="gstValue" name="LIST_GST_VALUE">
																	<option value="${gstValue.id}">${gstValue.name}</option>
																	</logic:iterate>
																</select>
															</td>
														</c:when>
														<c:otherwise>
															<td rowspan="2">
																<nested:select property="rateType" styleClass="rateType" style="width:139px">
																	<%-- <option value=""><bean:message key="screen.b_cpm.listBox.default"/></option> --%>
																	<html:optionsCollection name="LIST_RATETYPE" label="name" value="id"/>
																</nested:select>
																<c:if test="${LOAD_OBJECT.RateType2Flg eq '1'}">
																	<nested:select property="rateType2" styleClass="rateType2">
																		<html:optionsCollection name="LIST_RATETYPE2" label="name" value="id"/>
																	</nested:select>
																</c:if>
																<c:if test="${LOAD_OBJECT.RateType2Flg ne '1'}">
																	<nested:select property="rateType2" styleClass="rateType2" disabled="true">
																		<html:optionsCollection name="LIST_RATETYPE2" label="name" value="id"/>
																	</nested:select>
																</c:if>
																
															</td>
															<!-- In progress ,'Rate Mode' is notEditable Start-->
														<c:choose>
															<c:when test="${'BS2' eq service.billingStatus}">
																<td>
																	<nested:select property="rateMode" styleClass="rateMode" disabled="true" style="width:139px" onchange="changeRateType(this)">
																		<html:optionsCollection name="MODE_LIST" label="name" value="id"/>
																	</nested:select>
																	<nested:hidden property="rateMode"></nested:hidden>
																</td>
															</c:when>
															<c:otherwise>
																<td>
																	<nested:select property="rateMode" styleClass="rateMode" style="width:139px" onchange="changeRateType(this)">
																		<html:optionsCollection name="MODE_LIST" label="name" value="id"/>
																	</nested:select>
																</td>
															</c:otherwise>
														</c:choose>
														<!-- In progress, 'Rate Mode' is notEditable End-->
															<td>
																<%-- <nested:text property="rate" style="width: 100px" styleClass="rate"/> --%>
																<nested:select property="applyGst" styleClass="applyGst" onchange="javascript: onChangeGSTValue(this);">
													  			 	<html:options collection="LIST_GST" property="id" labelProperty="name"/>
																</nested:select>
																<select name="applyGstValue" class="applyGstValue" style="display: none;">
																	<logic:iterate id="gstValue" name="LIST_GST_VALUE">
																	<option value="${gstValue.id}">${gstValue.name}</option>
																	</logic:iterate>
																</select>
															</td>
														</c:otherwise>
													</c:choose>
												</tr>
												<tr class="subPlanDetailResult">
													<td colspan="2"  nowrap="nowrap" class="detailGst">
														<c:choose>
											    			<c:when test="${'BS2' eq service.billingStatus and '6' eq subPlan.rateMode}">
																<div  style="font-style:italic;width:100%;text-overflow:ellipsis;overflow:hidden;white-space:nowrap;">
																<bean:message key="screen.b_cpm.label.rate"/>
																<span class="displayCurrency">(<nested:write property="currency"/>)</span>:
																<nested:text property="rate" style="width: 100px" styleClass="rate" readonly="true"/>
																</div>
								                            </c:when>
								                            <c:otherwise>
								                                <div  style="font-style:italic;width:100%;text-overflow:ellipsis;overflow:hidden;white-space:nowrap;">
								                                <bean:message key="screen.b_cpm.label.rate"/>
																<span class="displayCurrency">(<nested:write property="currency"/>)</span>:
																<nested:text property="rate" style="width: 100px" styleClass="rate" />
																</div>
																
								                            </c:otherwise>
							                            </c:choose>
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
					</div>
					</logic:equal><!-- end of non-standard plan -->
					<!-- edit standard plan start -->
					<logic:equal name="CUSTOMER_PLAN" property="planType" value="SP">
					<%n=n+1;%>
					<div class="subPlan">
					<fieldset>
						<legend>
							<div class="opyionServiceLabel" style="font-weight: bold;color:#4876FF;">[<span id="planNo"><%=n%></span>][<bean:message key="screen.b_cpm.label.optionService"/>]
							[<nested:write property="planName"/>]
							[<nested:write property="planDesc"/>]
							[<nested:write property="itemGrpName"/>]
							</div>
						</legend>
						<logic:notEqual value="PS2" name="CUSTOMER_PLAN" property="planStatus">
							<c:if test="${'BS0' eq service.billingStatus or 'BS1' eq service.billingStatus}">
								<c:choose>
									<c:when test="${service.serviceStatus eq 'PS3' and 'BS1' eq service.billingStatus}">
										<c:if test="${LOAD_OBJECT.EditActivePlanAMTFlg eq '1'}">
											<span class="removeX" onclick="javascript: removeSubPlan(this);"><a href="javascript:void(0)">Remove &nbsp;<img src="../image/delete.gif"/></a></span>
										</c:if>
									</c:when>
									<c:otherwise>
										<span class="removeX" onclick="javascript: removeSubPlan(this);"><a href="javascript:void(0)">Remove &nbsp;<img src="../image/delete.gif"/></a></span>
									</c:otherwise>
								</c:choose>
							</c:if>
						</logic:notEqual>
						<nested:hidden property="idCustPlanLink" styleClass="idCustPlanLink"/>
						<nested:hidden property="idPlan" styleClass="idPlan"/>
						<nested:hidden property="idPlanGrp" styleClass="idPlanGrp"/>
						<nested:hidden property="planName" styleClass="planName"/>
						<nested:hidden property="planDesc" styleClass="planDesc"/>
						<nested:hidden property="itemGrpName" styleClass="itemGrpName"/>
						<nested:hidden property="itemType" styleClass="itemType"/>
						<nested:hidden property="svcLevel1" styleClass="svcLevel1"/>
						<nested:hidden property="svcLevel2" styleClass="svcLevel2"/>
						<nested:hidden property="rateType" styleClass="rateType"/>
						<%-- <nested:hidden property="rateMode" styleClass="rateMode"/>--%>
						<nested:hidden property="rate" styleClass="rate"/>
						<%-- <nested:hidden property="applyGst" styleClass="applyGst"/> --%>
						<nested:hidden property="currency" styleClass="currency"/>
						
						<table width="100%" class="subPlanContent" cellpadding="0" cellspacing="0">
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
							<tr>
								<td>&nbsp;</td>
								<td nowrap="nowrap" style="width:33%;"><bean:message key="screen.b_cpm.label.itemDescription"/><span style="color:red"><bean:message key="screen.b_cpm.label.star"/></span></td>
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
									<%-- <logic:equal value="1" property="customerPlan.m_jnmDisplayFlg" name="_B_CPM_S02Form">
										<nested:checkbox property="isDisplayJobNo" styleClass="isDisplayJobNo" value="1"/> <bean:message key="screen.b_cpm.label.jobNo"/>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<bean:message key="screen.b_cpm.label_colon"/>
										<nested:select property="jobNo" styleClass="jobNo">
										<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
										<html:optionsCollection name="LOAD_OBJECT" property="JOB_NO" label="JOB_NO" value="JOB_NO"/>
									</nested:select>
									</logic:equal> --%>
									<logic:equal value="1" property="customerPlan.m_jnmDisplayFlg" name="_B_CPM_S02Form">
										<c:choose>
									    <c:when test="${'BS2' eq service.billingStatus and '6' eq subPlan.rateMode}">
											<nested:checkbox property="isDisplayJobNo" styleClass="isDisplayJobNo" value="1" disabled="true"/>
											<nested:hidden property="isDisplayJobNo"></nested:hidden>
										</c:when>
										<c:otherwise>
											<nested:checkbox property="isDisplayJobNo" styleClass="isDisplayJobNo" value="1"/>
										</c:otherwise>
									</c:choose>
										<bean:message key="screen.b_cpm.label.jobNo"/>&nbsp;&nbsp;&nbsp;&nbsp;
										<bean:message key="screen.b_cpm.label_colon"/>
									<c:choose>
									    <c:when test="${'BS2' eq service.billingStatus and '6' eq subPlan.rateMode}">
											<nested:select property="jobNo" styleClass="jobNo" disabled="true">
												<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
												<option value="add">-----add one-----</option>
												<html:optionsCollection name="LOAD_OBJECT" property="JOB_NO" label="JOB_NO" value="JOB_NO"/>
											</nested:select>
											<nested:hidden property="jobNo"></nested:hidden>
										</c:when>
										<c:otherwise>
											<nested:select property="jobNo" styleClass="jobNo">
												<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
												<option value="add">-----add one-----</option>
												<html:optionsCollection name="LOAD_OBJECT" property="JOB_NO" label="JOB_NO" value="JOB_NO"/>
											</nested:select>
										</c:otherwise>
									</c:choose>
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
									<c:choose>
									    <c:when test="${'BS2' eq service.billingStatus and '6' eq subPlan.rateMode}">
											<nested:textarea property="itemDesc" styleClass="itemDesc" style="overflow-y:visible;height:40px;" readonly="true"/>
										</c:when>
										<c:otherwise>
											<nested:textarea property="itemDesc" styleClass="itemDesc" style="overflow-y:visible;height:40px;"/>
										</c:otherwise>
									</c:choose>
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
											<c:choose>
								    		<c:when test="${'BS2' eq service.billingStatus and '6' eq subPlan.rateMode}">
												<td valign="top" class="numberStyle">
													<div class="subPlanQuantityDiv">
														<nested:write property="quantity"/>
													</div>
													<nested:hidden property="quantity" styleClass="subPlanQuantity"/>
												</td>
												<td valign="top" class="numberStyle">
													<div class="subPlanUnitPriceDiv">
														<nested:write property="unitPrice"/>
													</div>
													<nested:hidden property="unitPrice" styleClass="subPlanUnitPrice"/>
												</td>
												<td valign="top" class="numberStyle">
													<div class="subPlanAmountDiv">
														<nested:write property="amount"/>
													</div>
													<nested:hidden property="amount" styleClass="subPlanAmount"/>
												</td>
											</c:when>
											<c:otherwise>
												<td valign="top">
													<nested:text property="quantity" styleClass="subPlanQuantity" style="width:100%" onchange="javascript: updateAmount(this);" onkeyup="javascript: updateAmount_enter(this,event);"/>
												</td>
												<td valign="top">
													<nested:text property="unitPrice" styleClass="subPlanUnitPrice" style="width:100%" onchange="javascript: updateAmount(this);" onkeyup="javascript: updateAmount_enter(this,event);"/>
												</td>
												<td valign="top">
													<nested:text property="amount" styleClass="subPlanAmount" style="width:100%; readonly:true;"/>
												</td>
											</c:otherwise>
										</c:choose>
										<td>&nbsp;</td>
										</tr>
										<tr>
											<td class="serviceDiscount"><bean:message key="screen.b_cpm.label.Discount"/><bean:message key="screen.b_cpm.label_colon"/></td>
											<td valign="top" nowrap="nowrap" class="numberStyle"><nested:radio property="isDiscountOneTime" styleClass="isDiscountOneTime" value="O"/><bean:message key="screen.b_cpm.label.onetime"/></td>
											<c:choose>
								    		<c:when test="${'BS2' eq service.billingStatus and '6' eq subPlan.rateMode}">
												<td valign="top" class="numberStyle">
												<nested:hidden property="discamount" styleClass="subDiscount"/>
												<div class="subDiscount"><nested:write property="discamount"/></div>
												</td>
											</c:when>
											<c:otherwise>
												<td valign="top"><nested:text property="discamount" styleClass="subDiscount" style="width:100%" onchange="javascript: updateDiscount(this);" onkeyup="javascript: updateDiscount_enter(this,event);"/></td>
											</c:otherwise>
											</c:choose>
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
							<logic:notEqual value="1" property="customerPlan.m_jnmDisplayFlg" name="_B_CPM_S02Form">
							    <nested:hidden property="isDisplayJobNo" styleClass="isDisplayJobNo"/>
							    <nested:hidden property="jobNo" styleClass="jobNo"/>
							</logic:notEqual>
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
								<!-- Define svcLevel1&svcLevel2 Start-->
									<%String svc1=""; %>
									<nested:present property="svcLevel1">
										<nested:define id="svcLev1" property="svcLevel1" ></nested:define>
								  <%svc1=svcLev1.toString();%>
									</nested:present> 
								<!--  Define svcLevel1&svcLevel2 End -->
									<div style="word-break:break-all;">
									<logic:iterate id="SVC_LEVEL1" name="LOAD_OBJECT" property="SVC_LEVEL1">
									<nested:equal property="svcLevel1" value="${SVC_LEVEL1.SVC_GRP}">
										<bean:write name="SVC_LEVEL1" property="SVC_GRP_NAME"/>
									</nested:equal>
									</logic:iterate>&nbsp;
									<bean:message key="screen.b_cpm.label.empty"/>&nbsp;
									<logic:iterate id="SVC_LEVEL2" name="LOAD_OBJECT" property="SVC_LEVEL2">
									<nested:equal property="svcLevel2" value="${SVC_LEVEL2.ID_SERVICE}">
										<bean:write name="SVC_LEVEL2" property="SVC_NAME"/>
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
									<table>
										<colgroup>
											<col width="70%"/>
											<col width="30%"/>
										</colgroup>
										<tr>
											<td valign="top">
												<!-- Sub plan detail group -->
												<table class="subPlanDetail" style="width: 100%">
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
													<nested:iterate property="subPlanDetails" indexId="k">
													<tr class="clonePlanDetail">
														<td valign="top">
															<nested:hidden property="idCustPlanSvc" styleClass="idCustPlanSvc"/>
															<%-- <nested:hidden property="svcLevel3" styleClass="svcLevel3"/> --%>
															<nested:hidden property="svcLevel4" styleClass="svcLevel4"/>
															<nested:hidden property="vendor" styleClass="vendor"/>
															<!-- <div style="word-break:break-all;"> -->
															<%-- <logic:iterate id="SVC_LEVEL3" name="LOAD_OBJECT" property="SVC_LEVEL3">
															<nested:equal property="svcLevel3" value="${SVC_LEVEL3.ID_SERVICE}">
																<bean:write name="SVC_LEVEL3" property="SVC_NAME"/>
															</nested:equal>
															</logic:iterate> --%>
															<nested:present property="MLABELVALUEBEAN3" name="LOAD_OBJECT">
																<nested:define id="svcLev3" property="MLABELVALUEBEAN3" name="LOAD_OBJECT"></nested:define>
																	<%
																		Map<String, List<LabelValueBean>> resutlMap2=(Map<String, List<LabelValueBean>>)svcLev3;
																		List<LabelValueBean> resutlListSvc3=resutlMap2.get(svc1);
																		if(resutlListSvc3==null){
																		    resutlListSvc3=new ArrayList<LabelValueBean>();
																		}
																		pageContext.setAttribute("Svc3List",resutlListSvc3);
																	%>
															</nested:present> 
															<nested:select property="svcLevel3" styleClass="svcLevel3">
																<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
																<html:optionsCollection name="Svc3List" label="label" value="value"/>
															</nested:select>
															<br/>
															<logic:iterate id="SVC_LEVEL4" name="LOAD_OBJECT" property="SVC_LEVEL4">
															<nested:equal property="svcLevel4" value="${SVC_LEVEL4.ID_SERVICE}">
																<bean:write name="SVC_LEVEL4" property="SVC_NAME"/>
															</nested:equal>
															</logic:iterate>
															<!-- </div> -->
														</td>
														<td valign="top" >
															<logic:iterate id="VENDOR" name="LOAD_OBJECT" property="VENDOR">
															<nested:equal property="vendor" value="${VENDOR.ID_SUPPLIER}">
																<bean:write name="VENDOR" property="SUPPLIER_NAME"/>
															</nested:equal>
															</logic:iterate>
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
														<bean:message key="screen.b_cpm.label_percentsymbol"/>&nbsp;
													</td>
												</tr>
												<tr class="subPlanDetailResult">
												<c:choose>
								        			<c:when test="${'BS2' eq service.billingStatus and '6' eq subPlan.rateMode}">
													<td rowspan="2">
														<nested:select property="rateType" styleClass="rateType" disabled="true" style="width:139px">
															<%-- <option value=""><bean:message key="screen.b_cpm.listBox.default"/></option> --%>
															<html:optionsCollection name="LIST_RATETYPE" label="name" value="id"/>
														</nested:select>
														<c:if test="${LOAD_OBJECT.RateType2Flg eq '1'}">
															<nested:select property="rateType2" styleClass="rateType2">
																<html:optionsCollection name="LIST_RATETYPE2" label="name" value="id"/>
															</nested:select>
														</c:if>
														<c:if test="${LOAD_OBJECT.RateType2Flg ne '1'}">
															<nested:select property="rateType2" styleClass="rateType2" disabled="true">
																<html:optionsCollection name="LIST_RATETYPE2" label="name" value="id"/>
															</nested:select>
														</c:if>
														
													</td>
													<td>											
														<nested:select property="rateMode" styleClass="rateMode" disabled="true" style="width:139px" onchange="changeRateType(this)">
															<%-- <option value=""><bean:message key="screen.b_cpm.listBox.default"/></option> --%>
															<html:optionsCollection name="MODE_LIST" label="name" value="id"/>
														</nested:select>
														<nested:hidden property="rateMode" styleClass="rateMode"/>
													</td>
													<td>
														<nested:select property="applyGst" styleClass="applyGst" disabled="true" onchange="javascript: onChangeGSTValue(this);">
													  		 <html:options collection="LIST_GST" property="id" labelProperty="name"/>
														</nested:select>
														<nested:hidden property="applyGst" styleClass="applyGst"/>
														<select name="applyGstValue" class="applyGstValue" style="display: none;">
															<logic:iterate id="gstValue" name="LIST_GST_VALUE">
															<option value="${gstValue.id}">${gstValue.name}</option>
															</logic:iterate>
														</select>
													</td>
													</c:when>
													<c:otherwise>
													<td rowspan="2">
														<nested:select property="rateType" styleClass="rateType" disabled="true" style="width:139px">
															<%-- <option value=""><bean:message key="screen.b_cpm.listBox.default"/></option> --%>
															<html:optionsCollection name="LIST_RATETYPE" label="name" value="id"/>
														</nested:select>
														<c:if test="${LOAD_OBJECT.RateType2Flg eq '1'}">
															<nested:select property="rateType2" styleClass="rateType2">
																<html:optionsCollection name="LIST_RATETYPE2" label="name" value="id"/>
															</nested:select>
														</c:if>
														<c:if test="${LOAD_OBJECT.RateType2Flg ne '1'}">
															<nested:select property="rateType2" styleClass="rateType2" disabled="true">
																<html:optionsCollection name="LIST_RATETYPE2" label="name" value="id"/>
															</nested:select>
														</c:if>
														
													</td>
													<!-- In progress ,'Rate Mode' is notEditable Start-->
														<c:choose>
															<c:when test="${'BS2' eq service.billingStatus}">
																<td>
																	<nested:select property="rateMode" styleClass="rateMode" disabled="true" style="width:139px" onchange="changeRateType(this)">
																		<html:optionsCollection name="MODE_LIST" label="name" value="id"/>
																	</nested:select>
																	<nested:hidden property="rateMode"></nested:hidden>
																</td>
															</c:when>
															<c:otherwise>
																<td>
																	<nested:select property="rateMode" styleClass="rateMode" style="width:139px" onchange="changeRateType(this)">
																		<html:optionsCollection name="MODE_LIST" label="name" value="id"/>
																	</nested:select>
																</td>
															</c:otherwise>
														</c:choose>
													<!-- In progress, 'Rate Mode' is notEditable End-->
													<td>
														<nested:select property="applyGst" styleClass="applyGst" onchange="javascript: onChangeGSTValue(this);">
													  		 <html:options collection="LIST_GST" property="id" labelProperty="name"/>
														</nested:select>
														<select name="applyGstValue" class="applyGstValue" style="display: none;">
															<logic:iterate id="gstValue" name="LIST_GST_VALUE">
															<option value="${gstValue.id}">${gstValue.name}</option>
															</logic:iterate>
														</select>
													</td>
													</c:otherwise>
													</c:choose>
												</tr>
												<tr  class="subPlanDetailResult">
													<td colspan="2" nowrap="nowrap" class="detailGst">
														<div  style="font-style:italic;"><bean:message key="screen.b_cpm.label.rate"/>
														<span class="displayCurrency">(<nested:write property="currency"/>)</span>:&nbsp;&nbsp;&nbsp;
														<nested:write property="rate"/>
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
					</div>
					</logic:equal>
					<!-- edit standard plan end -->
					</logic:equal>
					</nested:iterate>
				</td>
				<!-- End option service -->
			</tr>
		</table>
		<br/>
	</div>
</html:html>