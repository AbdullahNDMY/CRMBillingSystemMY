<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="windows-31j"%>
<%@page import="org.apache.struts.util.LabelValueBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title>B-CPM-S02-01 Ajax</title>
</head>
<body>
<ts:form styleId="frmS02" action="/B_CPM_S02EditDSP" method="POST">
<t:defineCodeList id="LIST_RATETYPE"/>
<t:defineCodeList id="LIST_RATETYPE2"/>
<t:defineCodeList id="MODE_LIST"/>
<t:defineCodeList id="LIST_GST"/>
<t:defineCodeList id="LIST_GST_VALUE"/>
<t:defineCodeList id="LIST_MASTER_LOCATION"/>
<bean:define id="LOAD_OBJECT" name="LOAD_OBJECT" scope="request"></bean:define>
<bean:define id="CUSTOMER_PLAN" name="_B_CPM_S02Form" property="customerPlan"></bean:define>
<div class="subPlanGroup">
<nested:nest property="customerPlan">
	<nested:iterate property="services" id="service" indexId="i">
		<nested:iterate property="subPlans" id="subPlan" indexId="j">
		<nested:equal name="subPlan" property="itemType" value="S">
		<div class="subPlan">
		<nested:hidden property="itemType" styleClass="itemType"/>
		<fieldset>
			<legend>
				<div class="subPlanLabel" style="font-weight: bold;color:#4876FF;">[<span id="planNo"></span>][<bean:message key="screen.b_cpm.label.subPlan"/>]
				[<nested:write property="planName"/>]
				[<nested:write property="planDesc"/>]
				[<nested:write property="itemGrpName"/>]
				</div>
			</legend>
			<span class="removeX" onclick="javascript: removeSubPlan(this);"><a href="javascript:void(0)">Remove &nbsp;<img src="../image/delete.gif"/></a></span>
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
					<td nowrap="nowrap" style="width:33%;"><bean:message key="screen.b_cpm.label.itemDescription"/><span style="color:red"><bean:message key="screen.b_cpm.label.star"/></span></td>
					<td nowrap="nowrap">
						<!-- #200, #201 wcbeh@20160921 - Sub Master Location for Type S -->
						<logic:equal value="1" property="customerPlan.masterLocationDisplayFlg" name="_B_CPM_S02Form">
						<div>
							<bean:message key="screen.b_cpm.label.masterLocation"/>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<bean:message key="screen.b_cpm.label_colon"/>
							<nested:select property="subLocation" styleClass="subLocation">
								<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
									<logic:iterate id="subLocation" name="LIST_MASTER_LOCATION">
										<option value="${subLocation.id}">${subLocation.id}</option>
									</logic:iterate>							</nested:select>
						
							<%-- <span style="width:100%;">
								<bean:message key="screen.b_cpm.label.masterLocation"/>
		        				<bean:message key="screen.b_cpm.label.colon"/>
		        				&nbsp;&nbsp;
		        				<select name="services[i].subPlans[i].subLocation" class="subLocation">
         							<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
              						<logic:iterate id="subLocation" name="LIST_MASTER_LOCATION">
										<option value="${subLocation.id}">${subLocation.id}</option>
									</logic:iterate>												
          						</select>
							</span> --%>
						</div>
						</logic:equal>
					</td>
					<td nowrap="nowrap" style="text-align:left;padding-right:0px">
					<logic:equal value="1" property="customerPlan.m_jnmDisplayFlg" name="_B_CPM_S02Form">
					<div id="jobNoDiv">
					<nested:checkbox property="isDisplayJobNo" styleClass="isDisplayJobNo" value="1"/><bean:message key="screen.b_cpm.label.jobNo"/>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<bean:message key="screen.b_cpm.label_colon"/>
						<nested:select property="jobNo" styleClass="jobNo">
							<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
							<option value="add">-----add one-----</option>
							<%-- <logic:iterate id="JOB" name="LOAD_OBJECT" property="JOB_NO">
							<option value="<bean:write name="JOB" property="JOB_NO"/>"><bean:write name="JOB" property="JOB_NO"/></option>
							</logic:iterate> --%>
							<html:optionsCollection name="LOAD_OBJECT" property="JOB_NO" label="JOB_NO" value="JOB_NO"/>
						</nested:select>
					</div>	
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
						<nested:textarea property="itemDesc" styleClass="itemDesc" style="overflow-y:visible;height:40px;"/>
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
								<td valign="top"><nested:text property="quantity" styleClass="subPlanQuantity" style="width:100%" onchange="javascript: updateAmount(this);" onkeyup="javascript: updateAmount_enter(this,event);"/></td>
								<td valign="top"><nested:text property="unitPrice" styleClass="subPlanUnitPrice" style="width:100%" onchange="javascript: updateAmount(this);" onkeyup="javascript: updateAmount_enter(this,event);"/></td>
								<td valign="top"><nested:text property="amount" styleClass="subPlanAmount" style="width:100%; readonly:true;"/></td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td class="serviceDiscount"><bean:message key="screen.b_cpm.label.Discount"/><bean:message key="screen.b_cpm.label_colon"/></td>
								<td valign="top" nowrap="nowrap" class="numberStyle"><nested:radio property="isDiscountOneTime" styleClass="isDiscountOneTime"  value="O"/><bean:message key="screen.b_cpm.label.onetime"/></td>
								<td><nested:text property="discamount" styleClass="subDiscount" style="width:100%" onchange="javascript: updateDiscount(this);" onkeyup="javascript: updateDiscount_enter(this,event);"/></td>
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
				<%-- <logic:equal value="1" property="customerPlan.m_jnmDisplayFlg" name="_B_CPM_S02Form"> --%>
				<%-- </logic:equal> --%>
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
						<div style="word-break:break-all;">
						<logic:iterate id="SVC_LEVEL1" name="LOAD_OBJECT" property="SVC_LEVEL1">
						<nested:equal property="svcLevel1" value="${SVC_LEVEL1.SVC_GRP}">
							<bean:write name="SVC_LEVEL1" property="SVC_GRP_NAME"/>
						</nested:equal>
						</logic:iterate>&nbsp;
						<bean:message key="screen.b_cpm.label.empty"/>&nbsp;
						<%-- <logic:iterate id="SVC_LEVEL2" name="LOAD_OBJECT" property="SVC_LEVEL2"> --%>
						<logic:iterate id="SVC_LEVEL2" name="Svc2List">
						<nested:equal property="svcLevel2" value="${SVC_LEVEL2.value}">
							<%-- <bean:write name="SVC_LEVEL2" property="SVC_NAME"/> --%>
							<bean:write name="SVC_LEVEL2" property="label"/>
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
								<col width="70%"/>
								<col width="30%"/>
							</colgroup>
							<tr>
								<td valign="top" style="width:50%">
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
												<div style="word-break:break-all;">
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
												<nested:select property="svcLevel3" styleClass="svcLevel3">
													<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
													<%-- <html:optionsCollection name="LOAD_OBJECT" property="SVC_LEVEL3" label="SVC_NAME" value="ID_SERVICE"/> --%>
													<html:optionsCollection name="Svc3List" label="label" value="value"/>
												</nested:select>
												<br/>
												<%-- <logic:iterate id="SVC_LEVEL4" name="LOAD_OBJECT" property="SVC_LEVEL4"> --%>
												<logic:iterate id="SVC_LEVEL4" name="Svc4List" >
												<nested:equal property="svcLevel4" value="${SVC_LEVEL4.value}">
													<bean:write name="SVC_LEVEL4" property="label"/>
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
														<%-- <bean:message key="screen.b_cpm.label.empty"/> --%>
														<nested:write property="lineDesc"/>
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
															<%-- <bean:message key="screen.b_cpm.label.empty"/> --%>
															<nested:write property="planLineDesc"/>
														</span> 
													</div>
													</td>
												</tr>
											</nested:empty>
										<!--if subPlanDetails is null end-->
									</table>
								</td>
								<td valign="top" style="width:50%">
									<!-- Sub plan rate group -->
									<table width="100%" class="blankTable">
									<colgroup>
										<col width="33%">
										<col width="33%">
										<col width="34%">
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
										<td rowspan="2">
											<nested:select property="rateType" styleClass="rateType" disabled="true" style="width:139px">
												<html:optionsCollection name="LIST_RATETYPE" label="name" value="id"/>
											</nested:select>
											<br/>
											<c:if test="${LOAD_OBJECT.RateType2Flg eq '1'}">
											<nested:select property="rateType2" styleClass="rateType2">
												<html:optionsCollection name="LIST_RATETYPE2" label="name" value="id"/>
											</nested:select>
											</c:if>
											<c:if test="${LOAD_OBJECT.RateType2Flg ne '1'}">
											<nested:select property="rateType2" styleClass="rateType2" disabled="true">
												<html:optionsCollection name="LIST_RATETYPE2" label="name" value="id"/>
											</nested:select>
											<nested:hidden property="rateType2" styleClass="rateType2"/>
											</c:if>
										</td>
										<td>											
											<nested:select property="rateMode" styleClass="rateMode" style="width:139px" onchange="changeRateType(this)">
												<html:optionsCollection name="MODE_LIST" label="name" value="id"/>
											</nested:select>
										</td>
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
					<%-- <td></td>
					<td></td>
					<td></td> --%>
					<td>&nbsp;</td>
				</tr>
				<!-- end sub plan detail -->
			</table>
		</fieldset>
		</div>
		</nested:equal>
		</nested:iterate>
	</nested:iterate>
</nested:nest>
</div>

<div class="optionServiceGroup">
<nested:nest property="customerPlan">
	<nested:iterate property="services" id="service">
		<nested:iterate property="subPlans" id="subPlan">
		<nested:equal name="subPlan" property="itemType" value="O">
		<div class="subPlan">
		<nested:hidden property="itemType" styleClass="itemType"/>
		<fieldset>
			<legend>
				<div class="OptionServiceLabel" style="font-weight: bold;color:#4876FF;">[<span id="planNo"></span>][<bean:message key="screen.b_cpm.label.optionService"/>]
				[<nested:write property="planName"/>]
				[<nested:write property="planDesc"/>]
				[<nested:write property="itemGrpName"/>]
				</div>
			</legend>
			<span class="removeX" onclick="javascript: removeSubPlan(this);"><a href="javascript:void(0)">Remove &nbsp;<img src="../image/delete.gif"/></a></span>
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
					<td nowrap="nowrap" style="width:33%;"><bean:message key="screen.b_cpm.label.itemDescription"/><span style="color:red"><bean:message key="screen.b_cpm.label.star"/></span></td>
					<td nowrap="nowrap">
						<!-- #200, #201 wcbeh@20160921 - Sub Master Location for Type O -->
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
					<nested:checkbox property="isDisplayJobNo" styleClass="isDisplayJobNo" value="1"/><bean:message key="screen.b_cpm.label.jobNo"/>
						<bean:message key="screen.b_cpm.label_colon"/>&nbsp;&nbsp;&nbsp;&nbsp;
						<nested:select property="jobNo" styleClass="jobNo">
							<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
							<option value="add">-----add one-----</option>
							<%-- <logic:iterate id="JOB" name="LOAD_OBJECT" property="JOB_NO">
							<option value="<bean:write name="JOB" property="JOB_NO"/>"><bean:write name="JOB" property="JOB_NO"/></option>
							</logic:iterate> --%>
							<html:optionsCollection name="LOAD_OBJECT" property="JOB_NO" label="JOB_NO" value="JOB_NO"/>
						</nested:select>
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
						<nested:textarea property="itemDesc" styleClass="itemDesc" style="overflow-y:visible;height:40px;"/>
					</td>
					<td colspan="4" valign="top">
					<table width="100%">
						<colgroup>
							<col width="27%">
							<col width="33%">
							<col width="40%">
							<col width="2%">
						</colgroup>
						<tr>
							<td valign="top"><nested:text property="quantity" styleClass="subPlanQuantity" style="width:100%" onchange="javascript: updateAmount(this);" onkeyup="javascript: updateAmount_enter(this,event);"/></td>
							<td valign="top"><nested:text property="unitPrice" styleClass="subPlanUnitPrice" style="width:100%" onchange="javascript: updateAmount(this);" onkeyup="javascript: updateAmount_enter(this,event);"/></td>
							<td valign="top"><nested:text property="amount" styleClass="subPlanAmount" style="width:100%; readonly:true;"/></td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td class="serviceDiscount"><bean:message key="screen.b_cpm.label.Discount"/><bean:message key="screen.b_cpm.label_colon"/></td>
							<td valign="top" nowrap="nowrap" class="numberStyle"><nested:radio property="isDiscountOneTime" styleClass="isDiscountOneTime" value="O"/><bean:message key="screen.b_cpm.label.onetime"/></td>
							<td><nested:text property="discamount" styleClass="subDiscount" style="width:100%" onchange="javascript: updateDiscount(this);" onkeyup="javascript: updateDiscount_enter(this,event);"/></td>
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
					<td nowrap="nowrap" class="billAcountCode"><bean:message key="screen.b_cpm.label.billingAccCode"/></td>
					<td nowrap="nowrap" class="billAcountCode">
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
						<bean:message key="screen.b_cpm.label_colon"/>
						<logic:iterate id="SVC_LEVEL1" name="LOAD_OBJECT" property="SVC_LEVEL1">
						<nested:equal property="svcLevel1" value="${SVC_LEVEL1.SVC_GRP}">
							<bean:write name="SVC_LEVEL1" property="SVC_GRP_NAME"/>
						</nested:equal>
						</logic:iterate>&nbsp;
						<bean:message key="screen.b_cpm.label.empty"/>&nbsp;
						<%-- <logic:iterate id="SVC_LEVEL2" name="LOAD_OBJECT" property="SVC_LEVEL2"> --%>
						<logic:iterate id="SVC_LEVEL2" name="Svc2List">
						<nested:equal property="svcLevel2" value="${SVC_LEVEL2.value}">
							<bean:write name="SVC_LEVEL2" property="label"/>
						</nested:equal>
						</logic:iterate>
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
								<col width="70%"/>
								<col width="30%"/>
							</colgroup>
							<tr>
								<td valign="top" style="width:50%">
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
												<nested:select property="svcLevel3" styleClass="svcLevel3">
													<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
													<%-- <html:optionsCollection name="LOAD_OBJECT" property="SVC_LEVEL3" label="SVC_NAME" value="ID_SERVICE"/> --%>
													<html:optionsCollection name="Svc3List" label="label" value="value"/>
												</nested:select>
												<br/>
												<%-- <logic:iterate id="SVC_LEVEL4" name="LOAD_OBJECT" property="SVC_LEVEL4"> --%>
												<logic:iterate id="SVC_LEVEL4" name="Svc4List">
												<nested:equal property="svcLevel4" value="${SVC_LEVEL4.value}">
													<bean:write name="SVC_LEVEL4" property="label"/>
												</nested:equal>
												</logic:iterate>
												<!-- </div> -->
											</td>
											<td valign="top">
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
														<%-- <bean:message key="screen.b_cpm.label.empty"/> --%>
														<nested:write property="lineDesc"/>
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
															<%-- <bean:message key="screen.b_cpm.label.empty"/> --%>
															<nested:write property="planLineDesc"/>
														</span> 
													</div>
													</td>
												</tr>
											</nested:empty>
										<!--if subPlanDetails is null end-->
									</table>
								</td>
								<td valign="top" style="width:50%">
									<!-- Sub plan rate group -->
									<table width="100%" class="blankTable">
									<colgroup>
										<col width="33%">
										<col width="33%">
										<col width="34%">
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
										<td rowspan="2">
											<nested:select property="rateType" styleClass="rateType" disabled="true" style="width:139px">
												<html:optionsCollection name="LIST_RATETYPE" label="name" value="id"/>
											</nested:select>
											<br/>
											<c:if test="${LOAD_OBJECT.RateType2Flg eq '1'}">
											<nested:select property="rateType2" styleClass="rateType2">
												<html:optionsCollection name="LIST_RATETYPE2" label="name" value="id"/>
											</nested:select>
											</c:if>
											<c:if test="${LOAD_OBJECT.RateType2Flg ne '1'}">
											<nested:select property="rateType2" styleClass="rateType2" disabled="true">
												<html:optionsCollection name="LIST_RATETYPE2" label="name" value="id"/>
											</nested:select>
											<nested:hidden property="rateType2" styleClass="rateType2"/>
											</c:if>
											
										</td>
										<td>											
											<nested:select property="rateMode" styleClass="rateMode" style="width:139px" onchange="changeRateType(this)">
												<html:optionsCollection name="MODE_LIST" label="name" value="id"/>
											</nested:select>
										</td>
										<td>
											<%-- <nested:write property="rate"/> --%>
											<nested:select property="applyGst" styleClass="applyGst" onchange="javascript: onChangeGSTValue(this);">
												<html:options collection="LIST_GST" property="id" labelProperty="name"/>
											</nested:select>
											<select name="applyGstValue" class="applyGstValue" style="display: none;">
												<logic:iterate id="gstValue" name="LIST_GST_VALUE">
												<option value="${gstValue.id}">${gstValue.name}</option>
												</logic:iterate>
											</select>
										</td>
									</tr>
									<tr class="subPlanDetailResult">
										<td colspan="2" nowrap="nowrap" class="detailGst">
											<div  style="font-style:italic;"><bean:message key="screen.b_cpm.label.rate"/>
											<span class="displayCurrency">(<nested:write property="currency"/>)</span>:
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
		</nested:equal>
		</nested:iterate>
	</nested:iterate>
</nested:nest>
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
</ts:form>
</body>
</html>