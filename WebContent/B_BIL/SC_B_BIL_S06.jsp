<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="/WEB-INF/bs" prefix="bs" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
	<head>
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
		<link href="${pageContext.request.contextPath}/B_BIL/css/b_bil.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/B_BIL/js/B_BIL_S06.js"></script>
   		<title></title>
	</head>
	<ts:body>
		<ts:form action="/SC_B_BIL_S06DSP">
		<t:defineCodeList id="LIST_GST"/>
			<div id="contentDiv">
				<h1 class="title"><bean:message key="screen.b_bil_s06.title"/></h1>
				<nested:nest property="bilS06Bean">
					<nested:hidden property="itemId" styleClass="serviceItemId"/>
					<nested:hidden property="jobModulesDisplayFlg" styleClass="jobModulesDisplayFlg"/>
					<nested:hidden property="gstPercent" styleClass="gstPercent"/>
					<nested:hidden property="itemUprice" styleClass="serviceItemUprice"/>
					<nested:hidden property="itemAmt" styleClass="serviceItemAmt"/>
					<nested:hidden property="itemDisc" styleClass="serviceItemDisc"/>
					<nested:hidden property="gstCheck" styleClass="gstCheck"/>
					<table cellspacing="0" cellpadding="0" style="width:100%">
						<col width="4%"/>
						<col width="15%"/>
						<col width="30%"/>
						<col width="8%"/>
						<col width="9%"/>
						<col width="12%"/>
						<col width="22%"/>
						<tr class="header" style="height:30px;">
							<td style="background-color:rgb(140,176,248)" style="text-align:left;"><bean:message key="screen.b_bil.item"/></td>
							<td style="background-color:rgb(140,176,248)" colspan="2"><bean:message key="screen.b_bil.billingDescription"/></td>
							<td style="background-color:rgb(140,176,248)" style="text-align:left;"><bean:message key="screen.b_bil.quantity"/></td>
							<td style="background-color:rgb(140,176,248)" style="text-align:left;"><bean:message key="screen.b_bil.unitPrice"/></td>
							<td style="background-color:rgb(140,176,248)" style="text-align:left;"><bean:message key="screen.b_bil.totalAmount"/></td>
							<td style="background-color:rgb(140,176,248)" style="text-align:left;padding-left:25px;"><bean:message key="screen.b_bil.GSTHeader"/></td>
						</tr>
						<tr>
							<td colspan="7" height="5px"></td>
						</tr>
						<tr style="background-color:#FFFFCC;">
							<td>&nbsp;</td>
							<td>
								<bean:message key="screen.b_bil.billingPeriod"/>
								<font class="asteriskBold"><bean:message key="screen.b_bil.asterisk"/></font>
							</td>
							<td>
								<bean:message key="screen.b_bil.colon"/>
								<nested:text property="billFrom" readonly="true" styleClass="serviceBillFrom" style="width:80px"/>
								<t:inputCalendar for="bilS06Bean.billFrom" format="dd/MM/yyyy"/>
								<bean:message key="screen.b_bil._"/>
								<nested:text property="billTo" readonly="true" styleClass="serviceBillTo" style="width:80px"/>
								<t:inputCalendar for="bilS06Bean.billTo" format="dd/MM/yyyy"/>
							</td>
							<td colspan="4">&nbsp;</td>
						</tr>
						<tr style="background-color:#FFFFCC;">
							<td>&nbsp;</td>
							<td>
								<bean:message key="screen.b_bil_s06.billAmount"/>
							</td>
							<td>
								<bean:message key="screen.b_bil.colon"/>
								<nested:radio property="isDisplay" styleClass="serviceIsDisplay" value="0" onclick="javascript: changItemmise(this);"/>
								<bean:message key="screen.b_bil_s06.itemised"/>
								<nested:radio property="isDisplay" styleClass="serviceIsDisplay" value="1" onclick="javascript: changItemmise(this);"/>
								<bean:message key="screen.b_bil_s06.lumpSum"/>
							</td>
							<td colspan="4">&nbsp;</td>
						</tr>
						<%-- #154 start --%>
						<tr style="background-color:#FFFFCC;">
							<td>&nbsp;</td>
							<td>
								<bean:message key="screen.b_bil_s06.discountAmount"/>
							</td>
							<td>
								<bean:message key="screen.b_bil.colon"/>
								<nested:radio property="displayDiscount" styleClass="serviceDisplayDiscount" value="0"/>
								<bean:message key="screen.b_bil_s06.itemised"/>
								<nested:radio property="displayDiscount" styleClass="serviceDisplayDiscount" value="1"/>
								<bean:message key="screen.b_bil_s06.lumpSum"/>
							</td>
							<td colspan="4">&nbsp;</td>
						</tr>
						<%-- #154 end --%>
						<tr style="background-color:#FFFFCC;">
							<td>&nbsp;</td>
							<td nowrap="nowrap">
								<bean:message key="screen.b_bil.billingDescription"/>
								<font class="asteriskBold"><bean:message key="screen.b_bil.asterisk"/></font>
							</td>
							<td>
								<bean:message key="screen.b_bil.colon"/>
							</td>
							<td colspan="4">&nbsp;</td>
						</tr>
						<tr style="background-color:#FFFFCC;">
							<td>&nbsp;</td>
							<td colspan="2">
								<nested:textarea property="itemDesc" styleClass="serviceItemDesc" style="width:480px;height:40px;font-size:14px;overflow-y:visible;"/>
							</td>
							<td colspan="4" valign="top">
								<table width="100%">
									<colgroup>
										<col width="12%"/>
										<col width="17%"/>
										<col width="19%"/>
										<col width="42%"/>
									</colgroup>
									<tr>
										<td nowrap="nowrap" class="serviceQuantity" style="text-align:center;">
											<fmt:formatNumber value="${_b_bilForm06.map.bilS06Bean.itemQty}" pattern="#,##0"/>
										</td>
										<td class="serviceUnitPrice" style="text-align:center;word-break:break-all;">
											<fmt:formatNumber value="${_b_bilForm06.map.bilS06Bean.itemUprice}" pattern="#,##0.00"/>
										</td>
										<td class="serviceAmt" style="text-align:center;word-break:break-all;">
											<fmt:formatNumber value="${_b_bilForm06.map.bilS06Bean.itemAmt}" pattern="#,##0.00"/>
										</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td style="text-align:left;font-weight:bold;font-style:italic;padding-left:10px;">
											<bean:message key="screen.b_bil_s06.discount"/>
											<bean:message key="screen.b_bil.colon"/>
										</td>
										<td class="serviceDisc" style="text-align:center;word-break:break-all;">
											<fmt:formatNumber value="${_b_bilForm06.map.bilS06Bean.itemDisc}" pattern="#,##0.00"/>
										</td>
										<td>&nbsp;</td>
									</tr>
								</table>
							</td>
						</tr>
						<c:if test="${_b_bilForm06.map.bilS06Bean.custPoDisplay eq 'CPM'}">
						<tr style="background-color:#FFFFCC;">
							<td>&nbsp;</td>
							<td>
								<bean:message key="screen.b_bil_s06.poNo."/>
							</td>
							<td>
								<bean:message key="screen.b_bil.colon"/>
								<nested:text property="poNo" styleClass="servicePoNo" maxlength="30" style="width:301px;"/>
							</td>
							<td colspan="4">&nbsp;</td>
						</tr>
						</c:if>
						<c:if test="${_b_bilForm06.map.bilS06Bean.custPoDisplay ne 'CPM'}">
							<nested:hidden property="poNo" styleClass="servicePoNo"/>
						</c:if>
						<tr style="background-color:#FFFFCC;">
							<td>&nbsp;</td>
							<td colspan="2">
								<fieldset>
									<legend>
										<div style="color:#4876FF;">
											<bean:message key="screen.b_bil.contractPeriod"/>
											<bean:message key="screen.b_bil.contractPeriodFrom"/>
											<nested:write property="minSvcFrom"/>
											<bean:message key="screen.b_bil.contractPeriodTo"/>
											<nested:write property="minSvcTo"/>
										</div>
									</legend>
									<div style="padding-left:8px;height:30px;">
										<bean:message key="screen.b_bil_s06.disMinPeriod"/>
										<bean:message key="screen.b_bil.colon"/>
										<nested:select property="isDisplayMinSvc" styleClass="serviceIsDisplayMinSvc">
											<html:option value="0" >No</html:option>
											<html:option value="1" >Yes</html:option>
										</nested:select>
									</div>
								</fieldset>
							</td>
							<td colspan="4">&nbsp;</td>
						</tr>
					</table>
					<table cellspacing="0" cellpadding="0">
						<tr><td style="height:5px;"></td></tr>
					</table>
					<nested:present property="subPlanBean">
						<nested:iterate id="subPlan" property="subPlanBean" indexId="i">
							<div class="subPlan">
								<table cellspacing="0" cellpadding="0" style="width:100%;background-color: #e7efff;">
									<col width="7%"/>
									<col width="15%"/>
									<col width="36%"/>
									<col width="10%"/>
									<col width="12%"/>
									<col width="10%"/>
									<col width="10%"/>
									<tr>
										<td>&nbsp;</td>
										<td>
											<bean:message key="screen.b_bil_s06.ItemDescription"/>
											<font class="asteriskBold"><bean:message key="screen.b_bil.asterisk"/></font>
										</td>
										<td>
											<bean:message key="screen.b_bil.colon"/>
										</td>
										<td colspan="4">&nbsp;</td>
									</tr>
									<tr>
										<td style="text-align:right;padding-right:17px;padding-left:16px;">${i+1}</td>
										<td colspan="2" style="text-align:left;">
											<nested:textarea property="itemDesc" styleClass="subPlanItemDesc" style="width:480px;height:40px;font-size:14px;overflow-y:visible;"/>
										</td>
										<td colspan="4" valign="top">
											<table width="100%">
												<colgroup>
													<col width="27%"/>
													<col width="27%"/>
													<col width="27%"/>
													<col width="19%"/>
												</colgroup>
												<tr>
													<td style="text-align:left;">
														<nested:text property="itemQty" styleClass="subPlanItemQty" onkeypress="return onlyIntNumbers(event)" style="width:70px;text-align:right;"/>
													</td>
													<td style="text-align:left;">
														<nested:text property="itemUprice" styleClass="subPlanItemUprice" onkeypress="return onlyDecNumbers(event)" style="width:80px;text-align:right;" maxlength="20"/>
													</td>
													<td style="text-align:left;">
														<nested:text property="itemAmt" styleClass="subPlanItemAmt" onkeypress="return onlyDecNumbers(event)" style="width:120px;text-align:right;" maxlength="20" disabled="true"/>
														<nested:hidden property="itemAmt" styleClass="subPlanHiddenItemAmt"></nested:hidden>
														
													</td>
													<td style="text-align:left;">
														<%-- <nested:hidden property="itemGst" styleClass="subPlanItemGst"/>
														<div class="subPlanApplyGst" style="display:none">
															<bean:message key="screen.b_bil.yes"/>
														</div>
														&nbsp; --%>
														<%-- <nested:text property="itemGst" styleClass="subPlanItemGst" onkeypress="return onlyDecNumbers(event)" style="width:120px;text-align:right;" maxlength="20"/> --%>
														<nested:hidden property="applyGst" styleClass="subPlanApplyGst"/>
														
														<nested:select property="itemGst" styleClass="subPlanItemGst" styleId="subPlanItemGst${i+1}" >
															<%-- <html:options collection="LIST_GST" property="id" labelProperty="name"/> --%>
															<nested:iterate property="applyGstList" id="item">
																<option value="${item.ID_TAX}" tax_code="${item.TAX_CODE}" tax_rate="${item.TAX_RATE}">${item.TD}</option>
															</nested:iterate>
								                        </nested:select>
													</td>
												</tr>
												<tr>
													<td>&nbsp;</td>
													<td style="text-align:right;font-weight:bold;font-style:italic;">
														<bean:message key="screen.b_bil_s06.discount"/>
														<bean:message key="screen.b_bil.colon"/>
													</td>
													<td class="subPlanDisc" style="text-align:center;word-break:break-all;">
														<nested:text property="itemDisc" styleClass="subPlanItemDisc" style="width:120px;text-align:right;" maxlength="20"/>
													</td>
													<td>&nbsp;</td>
												</tr>
											</table>
										</td>
									</tr>
									<c:if test="${_b_bilForm06.map.bilS06Bean.jobModulesDisplayFlg eq '1'}">
										<tr>
											<td>&nbsp;</td>
											<td colspan="2">
											    <nested:checkbox property="isDisplayJobNo" styleClass="subPlanIsDisplayJobNo"/>
												<bean:message key="screen.b_bil_s06.jobNo"/>
												<bean:message key="screen.b_bil.colon"/>
												<nested:text property="jobNo" styleClass="subPlanJobNo" maxlength="15"/>
											</td>
											<td colspan="4">&nbsp;</td>
										</tr>
									</c:if>
									<tr>
										<td colspan="7" style="height:5px;"></td>
									</tr>
								</table>
							</div>
							<table cellspacing="0" cellpadding="0">
								<tr><td style="height:5px;"></td></tr>
							</table>
						</nested:iterate>
					</nested:present>
				</nested:nest>
			</div>
			<div style="height:10px;">
		   	</div>
			<div>
				<input type="button" id="btnSave" value='<bean:message key="screen.b_bil.btnSave"/>' style="width:80px;" onclick="return save()"/>
			   	<input type="button" id="btnExit" value='<bean:message key="screen.b_bil.btnExit"/>' style="width:80px;" onclick="exit()"/>
		   	</div>
		   	<div class="message">
				<ts:messages id="message" message="true">
					<bean:write name="message"/>
				</ts:messages>
			</div>
			<div class="error">
				<html:messages id="message">
					<bean:write name="message"/><br/>
				</html:messages>
			</div>
			<div id="message_group" style="display: none;">
				<div class="messageBillPeriodFrom"><bean:message key="errors.ERR1SC005" arg0="Billing Period From"/></div>
				<div class="messageBillPeriodTo"><bean:message key="errors.ERR1SC005" arg0="Billing Period To"/></div>
				<div class="messageBillDesc"><bean:message key="errors.ERR1SC005" arg0="Billing Description"/></div>
				<div class="messageItemDesc"><bean:message key="errors.ERR1SC005" arg0="Item Desciption"/></div>
				<div class="messageItemQty"><bean:message key="errors.ERR1SC005" arg0="Quantity"/></div>
				<div class="messageItemUpprice"><bean:message key="errors.ERR1SC005" arg0="Unit Price"/></div>
				<div class="messageItemAmt"><bean:message key="errors.ERR1SC005" arg0="Total Amount"/></div>
			</div>
		</ts:form>
	</ts:body>
</html:html>