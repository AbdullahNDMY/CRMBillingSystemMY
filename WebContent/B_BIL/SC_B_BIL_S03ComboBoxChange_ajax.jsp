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
	</head>
	<ts:body>
		<ts:form action="/SC_B_BIL_S03_03DSP">
			<t:defineCodeList id="LIST_CURRENCY" />
			<t:defineCodeList id="LIST_ADDRESS"/>
			<nested:nest property="bilHeaderInfo">
				<div id="divAdrType">
					<nested:select property="adrType" style="width:150px;" styleId="cboAdrType" onchange="cboAdrTypeChange()">
						<html:option value="" ><bean:message key="screen.b_bil.blankItem"/></html:option>
						<nested:equal value="CONS" property="customerTypeFlag">
							<c:forEach items="${LIST_ADDRESS}" var="item">
								<c:if test="${item.id eq 'RA' or item.id eq 'BA'}">
									<html:option value="${item.id}" >${item.name}</html:option>
								</c:if>
							</c:forEach>
						</nested:equal>
						<nested:notEqual value="CONS" property="customerTypeFlag">
							<html:options collection="LIST_ADDRESS" property="id" labelProperty="name"/>
						</nested:notEqual>
					</nested:select>
					<font class="asteriskBold"><bean:message key="screen.b_bil.asterisk"/></font>
				</div>
				
				<div id="divAddress1">
					<bean:message key="screen.b_bil.colon"/>&nbsp;
					<nested:write property="address1"/>
					<nested:hidden property="address1" styleId="address1"/>
				</div>
				
				<div id="divAddress2">
					<bean:message key="screen.b_bil.addBlank"/>
					<nested:write property="address2"/>
					<nested:hidden property="address2" styleId="address2"/>
				</div>
				
				<div id="divAddress3">
					<bean:message key="screen.b_bil.addBlank"/>
					<nested:write property="address3"/>
					<nested:hidden property="address3" styleId="address3"/>
				</div>
				
				<div id="divAddress4">
					<bean:message key="screen.b_bil.addBlank"/>
					<nested:write property="address4"/>
					<nested:hidden property="address4" styleId="address4"/>
				</div>
				
				<div id="divTelNo">
					<bean:message key="screen.b_bil.colon"/>&nbsp;
					<nested:write property="telNo"/>
					<nested:hidden property="telNo" styleId="telNo"/>
					<nested:hidden property="custName" styleId="custName"/>
					<nested:hidden property="salutation" styleId="salutation"/>
					<nested:hidden property="customerTypeFlag" styleId="customerTypeFlag"/>
				</div>
				
				<div id="divFaxNo">
					<bean:message key="screen.b_bil.colon"/>&nbsp;
					<nested:write property="faxNo"/>
					<nested:hidden property="faxNo" styleId="faxNo"/>
				</div>
				
				<div id="divCboAttn">
					<bean:message key="screen.b_bil.colon"/>&nbsp;
					<nested:equal value="CONS" property="customerTypeFlag">
						<nested:select property="contactType" style="width:170px;" styleId="cboAttn" disabled="true" onchange="cboAttnChange()">
							<html:option value="" ><bean:message key="screen.b_bil.blankItem"/></html:option>
							<nested:optionsCollection property="listContact" label="label" value="value"/>
						</nested:select>
						<nested:hidden property="contactType" styleId="cboAttn"/>
					</nested:equal>
					<nested:notEqual value="CONS" property="customerTypeFlag">
					    <nested:select property="contactType" style="width:170px;" styleId="cboAttn" onchange="cboAttnChange()">
							<html:option value="" ><bean:message key="screen.b_bil.blankItem"/></html:option>
							<nested:optionsCollection property="listContact" label="label" value="value"/>
						</nested:select>
					</nested:notEqual>
					<nested:hidden property="contactName" styleId="contactName"/>
				</div>
				
				<div id="divContactEmail">
					<bean:message key="screen.b_bil.colon"/>&nbsp;
					<nested:write property="contactEmail"/>
					<nested:hidden property="contactEmail" styleId="contactEmail"/>
				</div>
				<div id="divContactEmailCC">
					<bean:message key="screen.b_bil.colon"/>&nbsp;
					<nested:write property="contactEmailCC"/>
					<nested:hidden property="contactEmailCC" styleId="contactEmailCC"/>
				</div>
				
				<div id="divBillAcc">
					<bean:message key="screen.b_bil.colon"/>&nbsp;
					<nested:select property="billAcc" styleId="cboBillAcc" style="width: 230px;" onchange="cboBillAccChange()">
						<html:option value="" ><bean:message key="screen.b_bil.blankItem"/></html:option>
						<nested:optionsCollection property="listBillingAccountNo" label="label" value="value"/>
					</nested:select>
				</div>
				
				<div id="divPayMethod">
					<bean:message key="screen.b_bil.colon"/>&nbsp;
					<t:writeCodeValue codeList="LIST_PAYMENT_METHOD" key="${_b_bilForm.map.bilHeaderInfo.payMethod}"/>
					<nested:hidden property="payMethod" styleId="payMethod"/>
				</div>
				
				<div id="divCboBillCurrency">
					<bean:message key="screen.b_bil.colon"/>&nbsp;		
					<nested:select property="billCurrency" style="width: 230px;" styleId="cboCurrency" onchange="cboCurrencyChange()">
						<html:option value="" ><bean:message key="screen.b_bil.blankItem"/></html:option>
						<html:options collection="LIST_CURRENCY" property="id" labelProperty="name"/>
					</nested:select>
					<nested:hidden property="billCurrency" styleId="billCurrency"/>
				</div>
				<div id="divTermDays">
					<bean:message key="screen.b_bil.colon"/>&nbsp;
					<nested:text property="termDays"  style="width:40px;" maxlength="3" onkeyup="termDayFun();this.value=this.value.replace(/\D/g,'')" onblur="termDayValue()"/>
					<nested:hidden property="termDays" styleId="termDays"/>
					<nested:text property="term" disabled="true" style="width:115px;"/>
					<nested:hidden property="term" styleId="term"/>
				</div>
				<div id="divContactDueDate">
					<bean:message key="screen.b_bil.colon"/>&nbsp;
					<nested:text property="contactDueDate" style="border:0;background:none;" readonly="true"/>
				</div>
				<div id="divDelivery">
					<bean:message key="screen.b_bil.colon"/>&nbsp;
					<c:if test="${_b_bilForm.map.bilHeaderInfo.contactDeliveryEmail == 1}">
						<input type="checkbox" name="bilHeaderInfo.contactDeliveryEmail" value="1" checked onclick="emailCheck(this);">
						<%-- <nested:checkbox property="contactDeliveryEmail" value="1" checked onclick="emailCheck(this);"></nested:checkbox> --%>
					</c:if>
				   <c:if test="${_b_bilForm.map.bilHeaderInfo.contactDeliveryEmail != 1}">
				   		<input type="checkbox" name="bilHeaderInfo.contactDeliveryEmail" value="0" onclick="emailCheck(this);">
				   </c:if>
				   <bean:message key="screen.b_bil.email"/>
					<c:if test="${_b_bilForm.map.bilHeaderInfo.contactDelivery == '3'}">
					  	<input type="radio" name="bilHeaderInfo.contactDelivery" value="3" checked="checked"/>
					  	<bean:message key="screen.b_bil.none"/>
				  	</c:if>
				  	<c:if test="${_b_bilForm.map.bilHeaderInfo.contactDelivery != '3'}">
					  	<input type="radio" name="bilHeaderInfo.contactDelivery" value="3"/>
					  	<bean:message key="screen.b_bil.none"/>
				  	</c:if>	 
				  	<c:if test="${_b_bilForm.map.bilHeaderInfo.contactDelivery == '2'}">
					  	<input type="radio" name="bilHeaderInfo.contactDelivery" value="2" checked="checked"/>
					  	<bean:message key="screen.b_bil.courier"/>
				  	</c:if>
				  	<c:if test="${_b_bilForm.map.bilHeaderInfo.contactDelivery != '2'}">
					  	<input type="radio" name="bilHeaderInfo.contactDelivery" value="2"/>
					  	<bean:message key="screen.b_bil.courier"/>
				  	</c:if>
				  	<br/>
                             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<c:if test="${_b_bilForm.map.bilHeaderInfo.contactDelivery == '1'}">
					  	<input type="radio" name="bilHeaderInfo.contactDelivery" value="1" checked="checked"/>
					  	<bean:message key="screen.b_bil.post"/>
				  	</c:if>
				  	<c:if test="${_b_bilForm.map.bilHeaderInfo.contactDelivery != '1'}">
					  	<input type="radio" name="bilHeaderInfo.contactDelivery" value="1"/>
					  	<bean:message key="screen.b_bil.post"/>
				  	</c:if>&nbsp;
					<c:if test="${_b_bilForm.map.bilHeaderInfo.contactDelivery == '4'}">
					  	<input type="radio" name="contactDbilHeaderInfo.contactDeliveryelivery" value="4" checked="checked"/>
					  	<bean:message key="screen.b_bil.byhand"/>	  
				  	</c:if>
				  	<c:if test="${_b_bilForm.map.bilHeaderInfo.contactDelivery != '4'}">
					  	<input type="radio" name="bilHeaderInfo.contactDelivery" value="4"/>
					  	<bean:message key="screen.b_bil.byhand"/>
				  	</c:if>				   
				</div>
				
				<div id="divBillCurrency">
					<nested:write property="billCurrency"/>
				</div>
				
				<div id="divBillCurrencyName">
					<t:writeCodeValue codeList="LIST_CURRENCY" key="${_b_bilForm.map.bilHeaderInfo.billCurrency}"/>
				</div>
				
				<div id="divGrandTotal">
				    <c:if test="${_b_bilForm.map.bilHeaderInfo.bacBillingCurrency ne _b_bilForm.map.bilHeaderInfo.exportCur 
			                  and '' ne fn:trim(_b_bilForm.map.bilHeaderInfo.bacBillingCurrency)
			                  and '-' ne fn:trim(_b_bilForm.map.bilHeaderInfo.exportCur) 
			                  and '' ne fn:trim(_b_bilForm.map.bilHeaderInfo.exportCur)}">
				    <table width="100%" cellpadding="0" cellspacing="0">
			            <tr align="center" style="background-color:rgb(136,167,216);font-size: 17px;font-weight: bold;">
							<td width="25%" style="background-color:rgb(136,167,216)" style="font-weight:normal;text-align: left;">
								<input type="checkbox" disabled="disabled">
								<bean:message key="screen.b_bil.displayAt"/>
								<c:if test="${_b_bilForm.map.bilHeaderInfo.billType eq 'CN'}">
									<bean:message key="screen.b_bil.creditNote"/>
								</c:if>
								<c:if test="${_b_bilForm.map.bilHeaderInfo.billType eq 'IN'}">
									<bean:message key="screen.b_bil.invoiceNote"/>
								</c:if>
								<c:if test="${_b_bilForm.map.bilHeaderInfo.billType eq 'DN'}">
									<bean:message key="screen.b_bil.debitNote"/>
								</c:if>
							</td>
							<td width="60%" style="background-color:rgb(136,167,216);" align="left">
								<nested:equal property="currencyStd" value="1">
									<bean:message key="screen.b_bil.grandTotalU"/>
									&nbsp;<nested:write property="exportCur"/>
									&nbsp;(
									1&nbsp;<nested:write property="bacBillingCurrency"/>
									&nbsp;=&nbsp;
									<c:if test="${not empty _b_bilForm.map.bilHeaderInfo.fixedForex}">
										<bean:message key="screen.b_bil.fixedForex"/>
									</c:if>
									<nested:text property="curRate" styleId="curRate"  onkeypress="return onlyDecNumbers(this,event)" onchange="curRateChange(this)" style="text-align:right;width:100px;"/>
									&nbsp;<nested:write property="exportCur"/>
									)
								</nested:equal>
								<nested:equal property="currencyStd" value="0">
									<c:if test="${_b_bilForm.map.bilHeaderInfo.billCurrency eq _b_bilForm.map.bilHeaderInfo.defCurrency}">
										<bean:message key="screen.b_bil.grandTotalU"/>
										&nbsp;<nested:write property="exportCur"/>
										&nbsp;(
										1&nbsp;<nested:write property="exportCur"/>
										&nbsp;=&nbsp;
										<c:if test="${not empty _b_bilForm.map.bilHeaderInfo.fixedForex}">
											<bean:message key="screen.b_bil.fixedForex"/>
										</c:if>
										<nested:text property="curRate" styleId="curRate"  onkeypress="return onlyDecNumbers(this,event)" onchange="curRateChange(this)" style="text-align:right;width:100px;"/>
										&nbsp;<nested:write property="bacBillingCurrency"/>
										)
									</c:if>
									<c:if test="${_b_bilForm.map.bilHeaderInfo.billCurrency ne _b_bilForm.map.bilHeaderInfo.defCurrency}">
										<bean:message key="screen.b_bil.grandTotalU"/>
										&nbsp;<nested:write property="exportCur"/>
										&nbsp;(
										1&nbsp;<nested:write property="bacBillingCurrency"/>
										&nbsp;=&nbsp;
										<c:if test="${not empty _b_bilForm.map.bilHeaderInfo.fixedForex}">
											<bean:message key="screen.b_bil.fixedForex"/>
										</c:if>
										<nested:text property="curRate" styleId="curRate"  onkeypress="return onlyDecNumbers(this,event)" onchange="curRateChange(this)" style="text-align:right;width:100px;"/>
										&nbsp;<nested:write property="exportCur"/>
										)
									</c:if>
								</nested:equal>
							</td>
							<td width="15%" align="left" style="background-color:rgb(136,167,216);">
							<%-- 
								<c:if test="${_b_bilForm.map.bilHeaderInfo.billType eq 'CN'}">
									-
								</c:if>
							--%>
								<span id="spanExportAmount">
									<fmt:formatNumber value="${_b_bilForm.map.bilHeaderInfo.exportAmount}" pattern="#,##0.00"/>
								</span>
								<nested:hidden property="exportCur" styleId="exportCur"/>
								<nested:hidden property="fixedForex" styleId="fixedForex"/>
								<nested:hidden property="curRate" styleId="curRate"/>
								<nested:hidden property="exportAmount" styleId="exportAmount"/>
							</td>
						</tr>
			        </table>
			        </c:if>
				</div>
			</nested:nest>
		</ts:form>
	</ts:body>
</html:html>