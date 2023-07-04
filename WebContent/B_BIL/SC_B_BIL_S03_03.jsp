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
		<script type="text/javascript" src="<%=request.getContextPath()%>/B_BIL/js/numberToWord.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/B_BIL/js/B_BIL_S03.js"></script>
		<title><bean:message key="screen.b_bil_s01.title"/></title>
		<script type="text/javascript">
		$(document).ready(function() {
			//onload function
			var screenWidth = window.screen.width;
			var contentDivWidth = screenWidth-270;
			// set page width
			$('#contentDiv').css("width",contentDivWidth);
			var termDays = $("input[name= 'bilHeaderInfo.termDays']").val();
	   		if (termDays!="0")
	   		{
	   			$("input[name= 'bilHeaderInfo.term']").val(termDays + " Days");
	   			$("input[name= 'bilHeaderInfo.term']").attr("disabled", true);
	   		}
	   		else
	   		{
	   			var bilType = "${_b_bilForm.map.bilHeaderInfo.billType}";
	   			if(bilType=='CN'){
	   				$("#termDays1").attr("disabled", true);
	   				$("#term1").attr("disabled", true);
	   			}else{
	   				$("input[name= 'bilHeaderInfo.term']").attr("disabled", false);
	   			}
	   			
	   		}

			if(navigator.userAgent.indexOf("MSIE")>0)
			{document.getElementById('dateReq').attachEvent("onpropertychange",dueDateValue); 
			} 
			else 
			{
			document.getElementById('dateReq').addEventListener("click",dueDateValue,false); 
			} 
		});
		function onSave()
		{
			var cusPo=$("#custPoid").val();
			if(cusPo.indexOf("&")!=-1)
			{
				showMsg();
			}else{				
				save();
				submitForm("forward_save");
			}					
		}
		function showMsg(){
            var message = $("#ERR107").val();
            message=message.replace('{0}', "Customer PO").replace('{1}', "Customer PO");
            var MsgBox = new messageBox();
            MsgBox.POPALT(message);
            return false;
        }
		//Function use submit form
		function submitForm(event) {
			$("input[name='event']").remove();
			var event = '<input type="hidden" name="event" value="' + event + '"/>';
			$('form').append(event);
			$('form').submit();
		}
		function emailCheck(obj){
			if(obj.checked){
				obj.value = "1";
			}
			else{
				obj.value="0";
			}
		}
		function termDayFun(){
	  		var termDays = $("input[name= 'bilHeaderInfo.termDays']").val();
  			if (termDays!="0")
	   		{
  				if(!isNaN(termDays)){
  					if(termDays==""){
	   					$("input[name= 'bilHeaderInfo.term']").val("");
	   				}else{
	   					$("input[name= 'bilHeaderInfo.term']").val(termDays + " Days");
	   				}
	   				$("input[name= 'bilHeaderInfo.term']").attr("disabled", true);
	   				if(termDays.length == "0"){
	   					$("input[name= 'bilHeaderInfo.term']").val("");
	   				}
	   				dueDateValue();
	   			}
  			}
	   		else
	   		{
	   			$("input[name= 'bilHeaderInfo.term']").attr("disabled", false);
	   			$("input[name= 'bilHeaderInfo.term']").val("");
	   			dueDateValue();
	   		}
	  	}
		
		function termDayValue(){
			var termDays = $("input[name= 'bilHeaderInfo.termDays']").val();
	  		if(termDays.length == "0"){
				$("input[name= 'bilHeaderInfo.termDays']").val("0");
			}
	  	}
		function dueDateValue(){
			var termDays = $("input[name= 'bilHeaderInfo.termDays']").val();
			var dateReq = $("input[name= 'bilHeaderInfo.dateReq']").val();
   			var arys= dateReq.split('/');
   			var y= parseInt(arys[2], 10);
		    var m=parseInt(arys[1], 10)-1;
		    var da=parseInt(arys[0], 10);
			var d = new Date(y,m,da);
			if(termDays!=""){
				d.setDate(d.getDate() + parseInt(termDays)); 
			}
			var Year=0;
		    var Month=0;
		    var Day=0;
		    Year = d.getYear();
		    Month = (d.getMonth()+1).toString();
		    Day = d.getDate();
		    CurrentDate = "";
		    if (Day >= 10 )
		    {
		        CurrentDate = CurrentDate + Day + "/";
		    }
		    else
		    {
		        CurrentDate = CurrentDate + "0" + Day + "/";
		    }
		    if (Month >= 10 )
		    {
		        CurrentDate = CurrentDate + Month + "/";
		    }
		    else
		    {
		        CurrentDate = CurrentDate + "0" + Month + "/";
		    }
		    if(dateReq != ""){
		    	$("input[name= 'bilHeaderInfo.contactDueDate']").val(CurrentDate+Year);
		    }else{
		    	$("input[name= 'bilHeaderInfo.contactDueDate']").val("");
		    }
		}
		</script>
	</head>
	<ts:body>
		<ts:form action="/SC_B_BIL_S03_03DSP" styleId="editForm">
			<div id="contentDiv">
			<t:defineCodeList id="LIST_ADDRESS"/>
			<t:defineCodeList id="LIST_CURRENCY" />
			<t:defineCodeList id="LIST_SALUTATION" />
			<nested:nest property="bilHeaderInfo">
			<nested:hidden property="changeTypeFlag" styleId="changeTypeFlag"/>
			<nested:hidden property="currencyEnabledFlg" styleId="currencyEnabledFlg"/>
			<nested:hidden property="mode" styleId="mode"/>
			<nested:hidden property="isClosed" styleId="isClosed"/>
			<nested:hidden property="isSettled" styleId="isSettled"/>
			<nested:hidden property="isDeleted" styleId="isDeleted"/>
			<nested:hidden property="paidAmount" styleId="paidAmount"/>
			<nested:hidden property="jobModulesDisplayFlg" styleId="jobModulesDisplayFlg"/>
			<input type="hidden" id="hiddenMsgExitConfirmation" value="<bean:message key="info.ERR4SC001"/>"/>
			<nested:hidden property="defCurrency" styleId="defCurrency"/>
			<nested:hidden property="currencyStd" styleId="currencyStd"/>
			<input type="hidden" value="<%=request.getContextPath()%>" id="rootPath"/>
			<input type="hidden" value='<bean:message key="screen.b_bil.jobNoPoint"/>' id="jobTitleName"/>
			<input type="hidden" value="${_b_bilForm.map.bilHeaderInfo.gstPercent}" id="hidGstPercent"/>
			<input type="hidden" value="${_b_bilForm.map.bilHeaderInfo.idRef}" id="hidIdRef"/>
			<!-- #174 Start -->
			<input type="hidden" value="${_b_bilForm.map.bilHeaderInfo.billingPeriod}" id="billingPeriod"/>
			<!-- #174 End -->
			<input type="hidden" name="ERR1SC107" id="ERR107" value="<bean:message key="errors.ERR1SC107" arg0="{0}" arg1="{1}"/>">	
			<nested:hidden property="gstCheck" styleClass="gstCheck" name="_b_bilForm"/>		
			<nested:hidden property="gstPercent" styleId="gstPercent"/>
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tr><td>
			<h1 class="title">
				<c:if test="${_b_bilForm.map.bilHeaderInfo.billType eq 'CN'}">
					<bean:message key="screen.b_bil.creditNote"/>
				</c:if>
				<c:if test="${_b_bilForm.map.bilHeaderInfo.billType eq 'IN'}">
					<bean:message key="screen.b_bil.invoiceNote"/>
				</c:if>
				<c:if test="${_b_bilForm.map.bilHeaderInfo.billType eq 'DN'}">
					<bean:message key="screen.b_bil.debitNote"/>
				</c:if>
				<nested:hidden property="billType" styleId="billType"/>
			</h1>
			</td></tr>
			<tr><td>
			<!-- Add #156 Start-->
			<html:hidden property="billCnAmtNegative" styleId="billCnAmtNegative"></html:hidden>
			<!-- Add #156 End-->
			<table class ="header1" cellpadding="0" cellspacing="0">
				<tr>
					<td class="col1Top" width="39%" valign="top">
						<table>
							<tr>
								<td colspan="2">
									<table cellpadding="0" cellspacing="0" border="0" width="100%">
										<tr>
											<td width="40%" style="vertical-align: top;">
												<font size="4px" style="headerInfo">
													<b><bean:message key="screen.b_bil.customerDetailsHeader"/>&nbsp;</b>
												</font>
											</td>
											<td width="30%" style="vertical-align: top;">
												<bean:message key="screen.b_bil.customerId"/>
												<bean:message key="screen.b_bil.colon"/>
											</td>
											<td width="30%" style="width:100px;word-wrap: break-word;white-space : normal">
												<span id="spanCustomerId">
													<nested:write property="idCust"/>
												</span>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td align="right" style="vertical-align: top;">
									<bean:message key="screen.b_bil.customerName"/>
									<font class="asteriskBold"><bean:message key="screen.b_bil.asterisk"/></font>
								</td>
								<td>
									<table cellpadding="0" cellspacing="0" border="0" width="100%">
										<tr>
											<td style="width:25px;" style="vertical-align: top;">
												<button id="btnGetCustomerInfo" style="width:25px;" onclick="openB_BAC_S03();"><img src="<%=request.getContextPath()%>/image/search.png"></button>
											</td>
											<td style="vertical-align: top;" style="width:170px;word-wrap: break-word;white-space : normal">
												<span id="spanCustomerName">
													<t:writeCodeValue codeList="LIST_SALUTATION" key="${_b_bilForm.map.bilHeaderInfo.salutation}"/>
													<nested:write property="custName"/>
												</span>
												<nested:hidden property="idCust" styleId="cboCustomerName"/>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td align="right" id="tdAdrType" nowrap="nowrap">
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
								</td>
								<td id="tdAddress1">
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									<nested:write property="address1"/>
									<nested:hidden property="address1" styleId="address1"/>
								</td>
							</tr>
							<tr>
								<td align="right">
									&nbsp;
								</td>
								<td id="tdAddress2">
									<bean:message key="screen.b_bil.addBlank"/>
									<nested:write property="address2"/>
									<nested:hidden property="address2" styleId="address2"/>
								</td>
							</tr>
							<tr>
								<td align="right">
									&nbsp;
								</td>
								<td id="tdAddress3">
									<bean:message key="screen.b_bil.addBlank"/>
									<nested:write property="address3"/>
									<nested:hidden property="address3" styleId="address3"/>
								</td>
							</tr>
							<tr>
								<td align="right">
									&nbsp;
								</td>
								<td id="tdAddress4">
									<bean:message key="screen.b_bil.addBlank"/>
									<nested:write property="address4"/>
									<nested:hidden property="address4" styleId="address4"/>
								</td>
							</tr>
							<tr>
								<td align="right">	
									<bean:message key="screen.b_bil.tel"/>
								</td>
								<td id="tdTelNo">
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									<nested:write property="telNo"/>
									<nested:hidden property="telNo" styleId="telNo"/>
									<nested:hidden property="custName" styleId="custName"/>
									<nested:hidden property="salutation" styleId="salutation"/>
									<nested:hidden property="customerTypeFlag" styleId="customerTypeFlag"/>
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.fax"/>
								</td>
								<td id="tdFaxNo">
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									<nested:write property="faxNo"/>
									<nested:hidden property="faxNo" styleId="faxNo"/>
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.attn"/>
								</td>
								<td id="tdCboAttn">
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
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.emailto"/>
								</td>
								<td id="tdContactEmail">
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									<nested:write property="contactEmail"/>
									<nested:hidden property="contactEmail" styleId="contactEmail"/>
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.emailcc"/>
								</td>
								<td id="tdContactEmailCC">
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									<nested:write property="contactEmailCC"/>
									<nested:hidden property="contactEmailCC" styleId="contactEmailCC"/>
								</td>
							</tr>
						</table>
					</td>
					<td class="col2Top" width="1%" style="background:white">
						&nbsp;
					</td>
					<td class="col3Top" width="44%" valign="top">
						<table>
							<tr>
								<td colspan="2"></td>			
							</tr>
							<tr>
								<td align="right" nowrap="nowrap">
									<c:if test="${_b_bilForm.map.bilHeaderInfo.billType eq 'CN'}">
										<bean:message key="screen.b_bil.creditNote"/>
									</c:if>
									<c:if test="${_b_bilForm.map.bilHeaderInfo.billType eq 'IN'}">
										<bean:message key="screen.b_bil.invoiceNote"/>
									</c:if>
									<c:if test="${_b_bilForm.map.bilHeaderInfo.billType eq 'DN'}">
										<bean:message key="screen.b_bil.debitNote"/>
									</c:if>
									<bean:message key="screen.b_bil.billingNo"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									<c:if test="${_b_bilForm.map.bilHeaderInfo.mode eq 'new'}">
										(Generated on Save)
									</c:if>
									<c:if test="${_b_bilForm.map.bilHeaderInfo.mode eq 'edit'}">
										<nested:write property="idRef"/>
									</c:if>
									<nested:hidden property="idRef" styleId="idRef"/>
								</td>
							</tr>
							<tr>
								<td align="right">
									<c:if test="${_b_bilForm.map.bilHeaderInfo.billType eq 'CN'}">
										<bean:message key="screen.b_bil.creditNote"/>
									</c:if>
									<c:if test="${_b_bilForm.map.bilHeaderInfo.billType eq 'IN'}">
										<bean:message key="screen.b_bil.invoiceNote"/>
									</c:if>
									<c:if test="${_b_bilForm.map.bilHeaderInfo.billType eq 'DN'}">
										<bean:message key="screen.b_bil.debitNote"/>
									</c:if>
									<bean:message key="screen.b_bil.date"/>
									<font class="asteriskBold"><bean:message key="screen.b_bil.asterisk"/></font>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									<nested:text property="dateReq" readonly="true" styleId="dateReq"></nested:text>
									<t:inputCalendar for="bilHeaderInfo.dateReq" format="dd/MM/yyyy"/>
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.billingAccountNo"/>
									<font class="asteriskBold"><bean:message key="screen.b_bil.asterisk"/></font>
								</td>
								<td id="tdBillAcc">
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									<nested:select property="billAcc" styleId="cboBillAcc" style="width: 230px;" onchange="cboBillAccChange()">
										<html:option value="" ><bean:message key="screen.b_bil.blankItem"/></html:option>
										<nested:optionsCollection property="listBillingAccountNo" label="label" value="value"/>
									</nested:select>
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.paymentMethod"/>
								</td>
								<td id="tdPayMethod">
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									<t:writeCodeValue codeList="LIST_PAYMENT_METHOD" key="${_b_bilForm.map.bilHeaderInfo.payMethod}"/>
									<nested:hidden property="payMethod" styleId="payMethod"/>
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.qcsReference"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									<nested:text property="idQcs" styleId="idQcs" style="width: 230px;"/>
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.quoReference"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									<nested:text property="quoRef" styleId="quoRef" style="width: 230px;"/>
								</td>
							</tr>
							<tr>
								<td align="right">	
									<bean:message key="screen.b_bil.customerPO"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									<nested:text property="custPo" styleId="custPoid" style="width: 230px;" maxlength="30"/>
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.acManager"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									<nested:select property="idConsult" style="width: 230px;" styleId="cboAcManager">
										<html:option value="" ><bean:message key="screen.b_bil.blankItem"/></html:option>
										<nested:optionsCollection property="listAcMan" label="label" value="value"/>
									</nested:select>
								</td>
							</tr>
							
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.currency"/>
									<font class="asteriskBold"><bean:message key="screen.b_bil.asterisk"/></font>
								</td>
								<td id="tdCboBillCurrency">
									<bean:message key="screen.b_bil.colon"/>&nbsp;		
									<nested:select property="billCurrency" style="width: 230px;" styleId="cboCurrency" onchange="cboCurrencyChange()">
										<html:option value="" ><bean:message key="screen.b_bil.blankItem"/></html:option>
										<html:options collection="LIST_CURRENCY" property="id" labelProperty="name"/>
									</nested:select>
									<nested:hidden property="billCurrency" styleId="billCurrency"/>
								</td>
							</tr>
<%-- 							<c:if test="${_b_bilForm.map.bilHeaderInfo.billType eq 'IN' or _b_bilForm.map.bilHeaderInfo.billType eq 'DN'}">
								<tr>
									<td align="right">
										<bean:message key="screen.b_bil.term"/>
									</td>
									<td>
										<bean:message key="screen.b_bil.colon"/>&nbsp;
										<nested:text property="term" styleId="term" style="width: 230px;"/>
									</td>
								</tr>
							</c:if> --%>
							<c:if test="${_b_bilForm.map.bilHeaderInfo.billType ne 'CN'}">
								<tr>
									<td align="right">
										<bean:message key="screen.b_bil.term"/>
									</td>
									<td id="tdTermDays">
										<bean:message key="screen.b_bil.colon"/>&nbsp;
										<html:text property="bilHeaderInfo.termDays" styleId="tdTermDays"
									     value="${_b_bilForm.map.bilHeaderInfo.termDays}" readonly="false" style="width:40px;" maxlength="3" onkeyup="termDayFun();this.value=this.value.replace(/\D/g,'')" onblur="termDayValue()"/>
									     <html:text property="bilHeaderInfo.term" styleId="tdTerm"
									     value="${_b_bilForm.map.bilHeaderInfo.term}" readonly="false" style="width:115px;" maxlength="15"/>
									</td>
								</tr>
							</c:if>
							<c:if test="${_b_bilForm.map.bilHeaderInfo.billType eq 'CN'}">
								<tr>
									<td align="right">
										<bean:message key="screen.b_bil.term"/>
									</td>
									<td>
										<bean:message key="screen.b_bil.colon"/>&nbsp;
										<input id="termDays1" value="${_b_bilForm.map.bilHeaderInfo.termDays}" disabled="disabled" style="width:40px;"/>
										<input id="term1" value="${_b_bilForm.map.bilHeaderInfo.term}" disabled="disabled" style="width:115px;"/>
								        <html:hidden styleId="termDays" value="${_b_bilForm.map.bilHeaderInfo.termDays}" property="bilHeaderInfo.termDays"/>
								        <html:hidden styleId="term" value="${_b_bilForm.map.bilHeaderInfo.term}" property="bilHeaderInfo.term"/>
									</td>
								</tr>
							</c:if>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.DueDate"/>
								</td>
								<td id="tdContactDueDate">
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									<html:text property="bilHeaderInfo.contactDueDate" 
									     value="${_b_bilForm.map.bilHeaderInfo.contactDueDate}" readonly="true" style="border:0;background:none;"/>
								</td>
							</tr>
							<tr>
								<td align="right" valign="top">
									<bean:message key="screen.b_bil.deliveryby"/>
								</td>
								<td id="tdDelivery">
									<bean:message key="screen.b_bil.colon"/>
									<c:if test="${_b_bilForm.map.bilHeaderInfo.contactDeliveryEmail == 1}">
	   									<input type="checkbox" name="bilHeaderInfo.contactDeliveryEmail" value="1" checked onclick="emailCheck(this);">
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
	                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
								</td>
							</tr>
							<tr>
								<td colspan="2">
									&nbsp;
								</td>
							</tr>
						</table>
					</td>
					<td class="col4Top" width="1%" style="background:white">
						&nbsp;
					</td>
					<td class="col5Top" width="20%" rowspan="15" valign="top" align="center" style="background-color:white">
						<div style="background-color:#ccccff;height=50%;align: center;">
							<font class="statusBold">
								<bean:message key="screen.b_bil.status"/>
							</font>
							<br/>
							<c:if test="${_b_bilForm.map.bilHeaderInfo.mode eq 'new'}">
								<bean:message key="screen.b_bil.open"/>
							</c:if>
							<c:if test="${_b_bilForm.map.bilHeaderInfo.mode eq 'edit'}">
								<c:if test="${_b_bilForm.map.bilHeaderInfo.isClosed eq '1'}">
									<bean:message key="screen.b_bil.closed"/>/
								</c:if>
								<c:if test="${_b_bilForm.map.bilHeaderInfo.isClosed ne '1'}">
									<bean:message key="screen.b_bil.open"/>/
								</c:if>
								<c:if test="${_b_bilForm.map.bilHeaderInfo.isSettled eq '1'}">
									<bean:message key="screen.b_bil.fullSettled"/>
								</c:if>
								<c:if test="${_b_bilForm.map.bilHeaderInfo.isSettled ne '1'}">
									<c:if test="${_b_bilForm.map.bilHeaderInfo.paidAmount ne 0}">
										<bean:message key="screen.b_bil.partialSettled"/>
									</c:if>
									<c:if test="${_b_bilForm.map.bilHeaderInfo.paidAmount eq 0}">
										<bean:message key="screen.b_bil.outstanding"/>
									</c:if>
								</c:if>
								<c:if test="${_b_bilForm.map.bilHeaderInfo.isDeleted eq '1'}">
									/<bean:message key="screen.b_bil.canceled"/>
								</c:if>
							</c:if>
							<br>&nbsp;
							<br>&nbsp;
							<br>&nbsp;
						</div>
						<div  style="background-color:#ffcccc;height=50%;align: center;">
							<font class="preparedBy">
								<bean:message key="screen.b_bil.preparedBy"/>
							</font>
							<br/>
							<center>
								<nested:write property="preparedByName"/>
								<nested:hidden property="preparedBy" styleId="preparedBy"/>
								<nested:hidden property="preparedByName" styleId="preparedBy"/>
							</center>
							<br/>
							<br/>	
							<font class="preparedBy">	
								<bean:message key="screen.b_bil.date"/>
							</font>
							<br/>
							<center>
								<nested:write property="dateCreated"/>
								<nested:hidden property="dateCreated" styleId="dateCreated"/>
							</center>
							<br/>&nbsp;
							<br/>&nbsp;
							<br/>&nbsp;
							<br/>&nbsp;
							<br/>&nbsp;
						</div>
					</td>
				</tr>
			</table>
			</td></tr>
			<tr><td>
			<table cellspacing="0" cellpadding="0" style="width:100%;border-top: solid 2px #8cb0f8;border-bottom: solid 2px #8cb0f8;border-left: solid 2px #8cb0f8;border-right: solid 2px #8cb0f8;">
				<tr>
					<td>
						<table class="resultInfo1" cellspacing="0" cellpadding="0" id="detailTable">
							<col width="6%"/>
							<col width="5%"/>
							<col width="47%"/>
							<col width="10%"/>
							<col width="12%"/>
							<col width="15%"/>
							<col width="5%"/>
							<tr style="height:40px;">
								<td colspan="7" style="background-color:#FFFFCC;padding-left:10px;">
									<button id="btnAddDescription" onclick="javascript: addDesc();" style="width:130px;">
										<bean:message key="screen.b_bil.addDesc"/>
									</button>
									&nbsp;
									<button id="btnAddCustPlan" onclick="javascript: addCustPlan();" style="width:200px">
										<bean:message key="screen.b_bil.addItemCustPlan"/>
									</button>
								</td>
							</tr>
							<TBODY id="detailTableBody">
								<tr class="header">
									<td style="background-color:rgb(140,176,248)">
									</td>
									<td style="background-color:rgb(140,176,248)"><bean:message key="screen.b_bil.item"/></td>
									<td style="background-color:rgb(140,176,248)"><bean:message key="screen.b_bil.billingDescription"/></td>
									<td style="background-color:rgb(140,176,248)" style="text-align:right;"><bean:message key="screen.b_bil.quantity"/></td>
									<td style="background-color:rgb(140,176,248)" style="text-align:right;"><bean:message key="screen.b_bil.unitPrice"/></td>
									<td style="background-color:rgb(140,176,248)" style="text-align:right;"><bean:message key="screen.b_bil.totalAmount"/></td>
									<td style="background-color:rgb(140,176,248)" style="padding-left:10px;"><bean:message key="screen.b_bil.GSTHeader"/></td>
								</tr>
								<!-- Detail content -->
								<tr>
									<td colspan="7" id="bilDetail_group">
										<bean:define id="index" value="0"/>
										<bean:define id="subTotalAmt" value="0"/>
										<nested:present property="bilDetail">
										<nested:iterate id="service" property="bilDetail" indexId="i">
											<div class="itemBillDetail">
												<nested:hidden property="itemId" styleClass="serviceItemId"/>
												<nested:hidden property="itemCat" styleClass="serviceItemCat"/>
												<nested:hidden property="itemLevel" styleClass="serviceItemLevel"/>
												<nested:hidden property="itemNo" styleClass="serviceItemNo"/>
												<nested:hidden property="itemDesc" styleClass="serviceItemDesc"/>
												<nested:hidden property="itemQty" styleClass="serviceItemQty"/>
												<nested:hidden property="itemUprice" styleClass="serviceItemUprice"/>
												<nested:hidden property="itemAmt" styleClass="serviceItemAmt"/>
												<nested:hidden property="itemGst" styleClass="serviceItemGst"/>
												<nested:hidden property="itemExportAmt" styleClass="serviceItemExportAmt"/>
												<nested:hidden property="applyGst" styleClass="serviceApplyGst"/>
												<nested:hidden property="isDisplay" styleClass="serviceIsDisplay"/>
												<nested:hidden property="idCustPlan" styleClass="serviceIdCustPlan"/>
												<nested:hidden property="idCustPlanGrp" styleClass="serviceIdCustPlanGrp"/>
												<nested:hidden property="idCustPlanLink" styleClass="serviceIdCustPlanLink"/>
												<nested:hidden property="itemType" styleClass="serviceItemType"/>
												<nested:hidden property="svcLevel1" styleClass="serviceSvcLevel1"/>
												<nested:hidden property="svcLevel2" styleClass="serviceSvcLevel2"/>
												<nested:hidden property="billFrom" styleClass="serviceBillFrom"/>
												<nested:hidden property="billTo" styleClass="serviceBillTo"/>
												<nested:hidden property="jobNo" styleClass="serviceJobNo"/>
												<nested:hidden property="isDisplayMinSvc" styleClass="serviceIsDisplayMinSvc"/>
												<nested:hidden property="minSvcFrom" styleClass="serviceMinSvcFrom"/>
												<nested:hidden property="minSvcTo" styleClass="serviceMinSvcTo"/>
												<nested:hidden property="billFromDisplay" styleClass="serviceBillFromDisplay"/>
												<nested:hidden property="billToDisplay" styleClass="serviceBillToDisplay"/>
												<nested:hidden property="minSvcFromDisplay" styleClass="serviceMinSvcFromDisplay"/>
												<nested:hidden property="minSvcToDisplay" styleClass="serviceMinSvcToDisplay"/>
												<!-- #154 Start -->
												<nested:hidden property="itemDisc" styleClass="serviceItemDisc"/>
												<nested:hidden property="itemSubTotal" styleClass="serviceItemSubTotal"/>
												<nested:hidden property="taxCode" styleClass="serviceTaxCode"/>
												<nested:hidden property="taxRate" styleClass="serviceTaxRate"/>
												<nested:hidden property="itemExportGST" styleClass="serviceItemExportGST"/>
												<nested:hidden property="poNo" styleClass="servicePoNo"/>
												<nested:hidden property="displayDiscount" styleClass="serviceDisplayDiscount"/>
												<!-- #154 End -->					
												<table cellspacing="0" cellpadding="0" style="width:100%">
													<col width="6%"/>
													<col width="5%"/>
													<col width="47%"/>
													<col width="10%"/>
													<col width="12%"/>
													<col width="15%"/>
													<col width="5%"/>
													<tr>
														<td class="fontSize" style="padding-left:10px;" valign="top">
															<span class="removeLink" onclick="removeBillItem(this)" style="cursor: pointer;">
																<img alt="" src="<%=request.getContextPath()%>/image/delete.gif">
															</span>
															&nbsp;&nbsp;&nbsp;
															<span class="editLink" onclick="editBillItem(this)" style="cursor: pointer;">
																<img alt="" src="<%=request.getContextPath()%>/image/editIcon.jpg">
															</span>
														</td>
														<td class="fontSize" style="text-align:left;">
															<div class="serviceItemIndex">
																<c:if test="${service.itemCat ne '0' && service.isDisplay ne '0'}">
																	<bean:define id="index" value="${index+1}"/>${index}
																</c:if>
																&nbsp;
															</div>
														</td>
														<td class="fontSize" style="width:470px;word-wrap: break-word;white-space : normal">
															<c:if test="${service.itemCat ne '0'}">
																<a href="#" onclick="openB_CPM_View('${pageContext.request.contextPath}/B_CPM/B_CPM_S05InitBL.do?customerPlan.idCustPlan=${service.idCustPlan}&customerPlan.fromScreen=BIL&customerPlan.billType=${_b_bilForm.map.bilHeaderInfo.billType}')">
																	<div class="divServiceDesc"><pre>${service.itemDesc}</pre></div>
																</a>
															</c:if>
															<c:if test="${service.itemCat eq '0'}">
																<div class="notItemBillDesc" style="color:#CD853F;"><pre>${service.itemDesc}<pre></div>
															</c:if>
														</td>
														<td class="fontSize" style="text-align:right;word-break:break-all;">
															<div class="divServiceQty">
																<c:if test="${service.isDisplay ne '0'}"><fmt:formatNumber value="${service.itemQty}" pattern="#,##0"/></c:if>
															</div>
														</td>
														<td valign="top" class="fontSize" style="text-align:right;word-break:break-all;">
															<div class="divServiceUprice">
																<c:if test="${service.itemCat ne '0' && service.isDisplay ne '0'}">
																<%-- 
																	<c:if test="${_b_bilForm.map.bilHeaderInfo.billType eq 'CN'}">
															    		-
															    	</c:if>
															    --%>
																	<!-- Delete #156 Start-->
																	<!--<fmt:formatNumber value="${service.itemUprice}" pattern="#,##0.00"/>-->
																	<!-- Delete #156 End-->
																	<!-- Add #156 Start-->
																	<c:if test="${_b_bilForm.map.bilHeaderInfo.billType eq 'CN'}">
																		<c:if test="${_b_bilForm.map.billCnAmtNegative eq '1'}">
																			-<fmt:formatNumber value="${service.itemUprice}" pattern="#,##0.00"/>
																		</c:if>
																		<c:if test="${_b_bilForm.map.billCnAmtNegative != '1'}">
																			<fmt:formatNumber value="${service.itemUprice}" pattern="#,##0.00"/>
																		</c:if>
																	</c:if>
																	<c:if test="${_b_bilForm.map.bilHeaderInfo.billType != 'CN'}">
																		<fmt:formatNumber value="${service.itemUprice}" pattern="#,##0.00"/>
																	</c:if>
																	<!-- Add #156 End-->
																</c:if>
															</div>
														</td>
														<td valign="top" class="fontSize" style="text-align:right;word-break:break-all;">
															<div class="divServiceAmt">
																<c:if test="${service.isDisplay ne '0' && not empty service.itemSubTotal}">
																	<bean:define id="subTotalAmt" value="${subTotalAmt+service.itemSubTotal}"/>
																</c:if>
																<c:choose>
															    <c:when test="${service.itemCat ne '0' && service.isDisplay ne '0'}">
															    <%-- 
															    	<c:if test="${_b_bilForm.map.bilHeaderInfo.billType eq 'CN'}">
															    		-
															    	</c:if>
															    --%>
																	<!-- Delete #156 Start-->
																	<!--<fmt:formatNumber value="${service.itemAmt}" pattern="#,##0.00"/>-->
																	<!-- Delete #156 End-->
																	<!-- Add #156 Start-->
																	<c:if test="${_b_bilForm.map.bilHeaderInfo.billType eq 'CN'}">
																		<c:if test="${_b_bilForm.map.billCnAmtNegative eq '1'}">
																			-<fmt:formatNumber value="${service.itemAmt}" pattern="#,##0.00"/>
																		</c:if>
																		<c:if test="${_b_bilForm.map.billCnAmtNegative != '1'}">
																			<fmt:formatNumber value="${service.itemAmt}" pattern="#,##0.00"/>
																		</c:if>
																	</c:if>
																	<c:if test="${_b_bilForm.map.bilHeaderInfo.billType != 'CN'}">
																		<fmt:formatNumber value="${service.itemAmt}" pattern="#,##0.00"/>
																	</c:if>
																	<!-- Add #156 End-->
																</c:when>
																<c:otherwise>
																&nbsp;
																</c:otherwise>
																</c:choose>
															</div>
														</td>
														<!-- #154 start -->
														<%-- <td class="fontSize">&nbsp;</td> --%>
														<td valign="top" style="padding-left:10px;" class="fontSize">
												 	        <div class="divServiceGST">
												 	        	<c:if test="${service.itemCat ne '0'}">
														 	        <c:if test="${_b_bilForm.map.gstCheck eq 'TAX_CODE'}">
																		<c:choose>
																		    <c:when test="${not empty service.taxCode}">
																		  		${service.taxCode}
																			</c:when>
																			<c:otherwise>
																				&nbsp;
																			</c:otherwise>
																		</c:choose>
																	</c:if>
																	<c:if test="${_b_bilForm.map.gstCheck eq 'TAX_RATE'}">
																		<c:choose>
																		    <c:when test="${not empty service.taxRate}">
																		  		${service.taxRate}%
																			</c:when>
																			<c:otherwise>
																				&nbsp;
																			</c:otherwise>
																		</c:choose>
																	</c:if>
																</c:if>
															</div>
														</td>
														<!-- #154 end -->
													</tr>
													<tr class="trMinSvcFormAndTo">
														<td class="fontSize">&nbsp;</td>
														<td class="fontSize">&nbsp;</td>
														<td class="fontSize">
															<bean:message key="screen.b_bil.contractPeriod"/>
															<bean:message key="screen.b_bil.contractPeriodFrom"/>
															<span class="spanMinSvcFromDisplay">
																${service.minSvcFromDisplay}
															</span>
															<bean:message key="screen.b_bil.contractPeriodTo"/>
															<span class="spanMinSvcToDisplay">
																${service.minSvcToDisplay}
															</span>
														</td>
														<td class="fontSize">&nbsp;</td>
														<td class="fontSize">&nbsp;</td>
														<td class="fontSize">&nbsp;</td>
														<td class="fontSize">&nbsp;</td>
													</tr>
													<!-- item_Level is 1 start-->
													<tr>
														<td class="fontSize" colspan="7" style="width:100%">
															<nested:present property="subPlanBean">
															<nested:iterate id="subPlan" property="subPlanBean" indexId="j">
																<div class="subPlan">
																	<nested:hidden property="itemId" styleClass="subPlanItemId"/>
																	<nested:hidden property="itemCat" styleClass="subPlanItemCat"/>
																	<nested:hidden property="itemLevel" styleClass="subPlanItemLevel"/>
																	<nested:hidden property="itemNo" styleClass="subPlanItemNo"/>
																	<nested:hidden property="itemDesc" styleClass="subPlanItemDesc"/>
																	<nested:hidden property="itemQty" styleClass="subPlanItemQty"/>
																	<nested:hidden property="itemUprice" styleClass="subPlanItemUprice"/>
																	<nested:hidden property="itemAmt" styleClass="subPlanItemAmt"/>
																	<nested:hidden property="itemGst" styleClass="subPlanItemGst"/>
																	<nested:hidden property="itemExportAmt" styleClass="subPlanItemExportAmt"/>
																	<nested:hidden property="applyGst" styleClass="subPlanApplyGst"/>
																	<nested:hidden property="isDisplay" styleClass="subPlanIsDisplay"/>
																	<nested:hidden property="idCustPlan" styleClass="subPlanIdCustPlan"/>
																	<nested:hidden property="idCustPlanGrp" styleClass="subPlanIdCustPlanGrp"/>
																	<nested:hidden property="idCustPlanLink" styleClass="subPlanIdCustPlanLink"/>
																	<nested:hidden property="itemType" styleClass="subPlanItemType"/>
																	<nested:hidden property="svcLevel1" styleClass="subPlanSvcLevel1"/>
																	<nested:hidden property="svcLevel2" styleClass="subPlanSvcLevel2"/>
																	<nested:hidden property="billFrom" styleClass="subPlanBillFrom"/>
																	<nested:hidden property="billTo" styleClass="subPlanBillTo"/>
																	<nested:hidden property="jobNo" styleClass="subPlanJobNo"/>
																	<nested:hidden property="isDisplayJobNo" styleClass="subPlanIsDisplayJobNo"/>
																	<nested:hidden property="isDisplayMinSvc" styleClass="subPlanIsDisplayMinSvc"/>
																	<nested:hidden property="minSvcFrom" styleClass="subPlanMinSvcFrom"/>
																	<nested:hidden property="minSvcTo" styleClass="subPlanMinSvcTo"/>
																	<nested:hidden property="billFromDisplay" styleClass="subPlanBillFromDisplay"/>
																	<nested:hidden property="billToDisplay" styleClass="subPlanBillToDisplay"/>
																	<nested:hidden property="minSvcFromDisplay" styleClass="subPlanMinSvcFromDisplay"/>
																	<nested:hidden property="minSvcToDisplay" styleClass="subPlanMinSvcToDisplay"/>
																	<%-- #154 start --%>
																	<nested:hidden property="itemDisc" styleClass="subPlanItemDisc"/>
																	<nested:hidden property="itemSubTotal" styleClass="subPlanItemSubTotal"/>
																	<nested:hidden property="taxCode" styleClass="subPlanTaxCode"/>
																	<nested:hidden property="taxRate" styleClass="subPlanTaxRate"/>
																	<nested:hidden property="itemExportGST" styleClass="subPlanItemExportGST"/>
																	<nested:hidden property="poNo" styleClass="subPlanPoNo"/>
																	<nested:hidden property="displayDiscount" styleClass="subPlanDisplayDiscount"/>
																	<%-- #154 end --%>
																	<table cellspacing="0" cellpadding="0" style="width:100%">
																		<col width="6%"/>
																		<col width="5%"/>
																		<col width="47%"/>
																		<col width="10%"/>
																		<col width="12%"/>
																		<col width="15%"/>
																		<col width="5%"/>
																		<tr>
																		   <td class="fontSize">&nbsp;</td>
																		   <td valign="top" class="fontSize">
																		        <div class="divSubPlanDisplayJobNo">
																			        <c:if test="${'1' eq subPlan.isDisplayJobNo && '1' eq _b_bilForm.map.bilHeaderInfo.jobModulesDisplayFlg}">
																		   		    	&nbsp;
																		   		    </c:if>
																	   		    </div>
																		   		<div class="subPlanItemIndex">
																			   		<c:if test="${subPlan.isDisplay ne '0'}">
																						<bean:define id="index" value="${index+1}"/>${index}
																					</c:if>&nbsp;
																				</div>
																			</td>
																			<td valign="top" class="fontSize" style="width:470px;word-wrap: break-word;white-space : normal">
																				<div style="color:#CD853F;" class="divJob">
																					<c:if test="${'1' eq subPlan.isDisplayJobNo && '1' eq _b_bilForm.map.bilHeaderInfo.jobModulesDisplayFlg}">
																						<bean:message key="screen.b_bil.jobNoPoint"/>&nbsp;${subPlan.jobNo}
																					</c:if>
																				</div>
																				<div class="divSubPlanDesc"><pre>${subPlan.itemDesc}</pre></div>
																			</td>
																			<td valign="top" class="fontSize" style="text-align:right;word-break:break-all;">
																				<div class="divSubPlanDisplayJobNo">
																			        <c:if test="${'1' eq subPlan.isDisplayJobNo && '1' eq _b_bilForm.map.bilHeaderInfo.jobModulesDisplayFlg}">
																		   		    	&nbsp;
																		   		    </c:if>
																	   		    </div>
																				<div class="divSubPlanQty">
																					<c:if test="${subPlan.isDisplay ne '0'}"><fmt:formatNumber value="${subPlan.itemQty}" pattern="#,##0"/></c:if>
																				</div>
																			</td>
																			<td valign="top" class="fontSize" style="text-align:right;word-break:break-all;">
																				<div class="divSubPlanDisplayJobNo">
																			        <c:if test="${'1' eq subPlan.isDisplayJobNo && '1' eq _b_bilForm.map.bilHeaderInfo.jobModulesDisplayFlg}">
																		   		    	&nbsp;
																		   		    </c:if>
																	   		    </div>
																				<div class="divSubPlanUprice">
																					<c:if test="${subPlan.isDisplay ne '0'}">
																					<%-- 
																						<c:if test="${_b_bilForm.map.bilHeaderInfo.billType eq 'CN'}">
																				    		-
																				    	</c:if>
																				    --%>
																						<!-- Delete #156 Start-->
																						<!--<fmt:formatNumber value="${subPlan.itemUprice}" pattern="#,##0.00"/>-->
																						<!-- Delete #156 End-->
																						<!-- Add #156 Start-->
																						<c:if test="${_b_bilForm.map.bilHeaderInfo.billType eq 'CN'}">
																							<c:if test="${_b_bilForm.map.billCnAmtNegative eq '1'}">
																								-<fmt:formatNumber value="${subPlan.itemUprice}" pattern="#,##0.00"/>
																							</c:if>
																							<c:if test="${_b_bilForm.map.billCnAmtNegative != '1'}">
																								<fmt:formatNumber value="${subPlan.itemUprice}" pattern="#,##0.00"/>
																							</c:if>
																						</c:if>
																						<c:if test="${_b_bilForm.map.bilHeaderInfo.billType != 'CN'}">
																							<fmt:formatNumber value="${subPlan.itemUprice}" pattern="#,##0.00"/>
																						</c:if>
																						<!-- Add #156 End-->
																					</c:if>
																				</div>
																			</td>
																			<td valign="top" class="fontSize" style="text-align:right;word-break:break-all;">
																				<div class="divSubPlanDisplayJobNo">
																			        <c:if test="${'1' eq subPlan.isDisplayJobNo && '1' eq _b_bilForm.map.bilHeaderInfo.jobModulesDisplayFlg}">
																		   		    	&nbsp;
																		   		    </c:if>
																	   		    </div>
																				<div class="divSubPlanAmt">
																					<c:if test="${subPlan.isDisplay ne '0' && not empty subPlan.itemSubTotal}">
																						<bean:define id="subTotalAmt" value="${subTotalAmt+subPlan.itemSubTotal}"/>
																					</c:if>
																					<c:choose>
																				    <c:when test="${subPlan.isDisplay ne '0'}">
																				    <%-- 
																				    	<c:if test="${_b_bilForm.map.bilHeaderInfo.billType eq 'CN'}">
																				    		-
																				    	</c:if>
																				    --%>
																						<!-- Delete #156 Start-->
																						<!--<fmt:formatNumber value="${subPlan.itemAmt}" pattern="#,##0.00"/>-->
																						<!-- Delete #156 End-->
																						<!-- Add #156 Start-->
																						<c:if test="${_b_bilForm.map.bilHeaderInfo.billType eq 'CN'}">
																							<c:if test="${_b_bilForm.map.billCnAmtNegative eq '1'}">
																								-<fmt:formatNumber value="${subPlan.itemAmt}" pattern="#,##0.00"/>
																							</c:if>
																							<c:if test="${_b_bilForm.map.billCnAmtNegative != '1'}">
																								<fmt:formatNumber value="${subPlan.itemAmt}" pattern="#,##0.00"/>
																							</c:if>
																						</c:if>
																						<c:if test="${_b_bilForm.map.bilHeaderInfo.billType != 'CN'}">
																							<fmt:formatNumber value="${subPlan.itemAmt}" pattern="#,##0.00"/>
																						</c:if>
																						<!-- Add #156 End-->
																					</c:when>
																					<c:otherwise>
																						&nbsp;
																					</c:otherwise>
																					</c:choose>
																				</div>
																			</td>
																			<td valign="top" class="fontSize" style="padding-left:10px;">
																				<div class="divSubPlanDisplayJobNo">
																			        <c:if test="${'1' eq subPlan.isDisplayJobNo && '1' eq _b_bilForm.map.bilHeaderInfo.jobModulesDisplayFlg}">
																		   		    	&nbsp;
																		   		    </c:if>
																	   		    </div>
																	   		    <%-- #154 start --%>
																	   		    <%-- <div>
																				<c:choose>
																				    <c:when test="${'1' eq subPlan.applyGst}">
																						<bean:message key="screen.b_bil.yes"/>
																					</c:when>
																					<c:otherwise>
																						&nbsp;
																					</c:otherwise>
																				</c:choose>
																				</div> --%>
																	   		    <div class="divGst">
																		   		    <c:if test="${_b_bilForm.map.gstCheck eq 'TAX_CODE'}">
																						<c:choose>
																						    <c:when test="${'1' eq subPlan.isDisplay}">
																								${subPlan.taxCode}
																							</c:when>
																							<c:otherwise>
																								&nbsp;
																							</c:otherwise>
																						</c:choose>
																					</c:if>
																					<c:if test="${_b_bilForm.map.gstCheck eq 'TAX_RATE'}">
																						<c:choose>
																						    <c:when test="${'1' eq subPlan.isDisplay}">
																								${subPlan.taxRate}%
																							</c:when>
																							<c:otherwise>
																								&nbsp;
																							</c:otherwise>
																						</c:choose>
																					</c:if>
																				</div>
																				<%-- #154 end --%>
																			</td>
																		</tr>
																		<%-- #154 start --%>
																		<tr id="trSubPlanDisCount" style="display: none;" >
																			<td>&nbsp;</td>
																			<td>&nbsp;</td>
																 	        <td style="text-align:left;font-size:16px;">
																 	           <b><i><bean:message key="screen.b_bil.discount"/></i></b>
																 	        </td>
																 	        <td>&nbsp;</td>
																			<td>&nbsp;</td>
																 	        <td style="text-align:right;font-size:16px;">
																	 	        <div class="divSubPlanItemDisc">
																	 	        	<fmt:formatNumber value="${subPlan.itemDisc}" pattern="#,##0.00"/>
																	 	        </div>
																 	        </td>
																 	        <td valign="top" style="padding-left:10px;" class="fontSize">
																	 	        <div class="divSubPlanDisCountGST">
																		 	        <c:if test="${_b_bilForm.map.gstCheck eq 'TAX_CODE'}">
																						<c:choose>
																						    <c:when test="${not empty subPlan.taxCode && subPlan.isDisplay ne '0'}">
																						  		${subPlan.taxCode}
																							</c:when>
																							<c:otherwise>
																								&nbsp;
																							</c:otherwise>
																						</c:choose>
																					</c:if>
																					<c:if test="${_b_bilForm.map.gstCheck eq 'TAX_RATE'}">
																						<c:choose>
																						    <c:when test="${not empty subPlan.taxRate && subPlan.isDisplay ne '0'}">
																						  		${subPlan.taxRate}%
																							</c:when>
																							<c:otherwise>
																								&nbsp;
																							</c:otherwise>
																						</c:choose>
																					</c:if>
																				</div>
																			</td>
																		</tr>
																		<!-- #154 end -->
																	</table>
																</div>
															</nested:iterate>
															</nested:present>
														</td>
													</tr>
													<%-- #154 start --%>
													<tr id="trServiceDisCount" style="display: none;" >
														<td colspan="7">
															<table width="100%">
																	<col width="6%"/>
																	<col width="5%"/>
																	<col width="47%"/>
																	<col width="10%"/>
																	<col width="12%"/>
																	<col width="15%"/>
																	<col width="5%"/>
																<tr>
																	<td colspan="7">&nbsp;</td>
																</tr>
																<tr>
																	<td>&nbsp;</td>
																	<td>&nbsp;</td>
																	<c:if test="${service.itemCat eq '1'}">
															 	        <td style="text-align:left;font-size:16px;">
															 	           <b><i><bean:message key="screen.b_bil.discount"/></i></b>
															 	        </td>
															 	        <td>&nbsp;</td>
															 	        <td>&nbsp;</td>
															 	        <td style="text-align:right;font-size:16px;" valign="top" class="fontSize">
																 	        <div class="divServiceItemDisc">
																 	          	<fmt:formatNumber value="${service.itemDisc}" pattern="#,##0.00"/>
																 	        </div>
															 	        </td>
															 	        <td valign="top" style="padding-left:10px;text-align:left;font-size:16px;">
																 	        <div class="divServiceDisCountGST">
																 	        	<c:if test="${service.isDisplay ne '0'}">
																 	        		<c:if test="${_b_bilForm.map.gstCheck eq 'TAX_CODE'}">
																						<c:choose>
																						    <c:when test="${not empty service.taxCode}">
																						  		${service.taxCode}
																							</c:when>
																							<c:otherwise>
																								&nbsp;
																							</c:otherwise>
																						</c:choose>
																					</c:if>
																					<c:if test="${_b_bilForm.map.gstCheck eq 'TAX_RATE'}">
																						<c:choose>
																						    <c:when test="${not empty service.taxRate}">
																						  		${service.taxRate}%
																							</c:when>
																							<c:otherwise>
																								&nbsp;
																							</c:otherwise>
																						</c:choose>
																					</c:if>
																 	        	</c:if>
																			</div>
																		</td>
																	</c:if>	
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td colspan="7">&nbsp;</td>
													</tr>
													<%-- #154 end --%>
													<tr>
													<!-- item_Level is 1 end-->
													<c:if test="${service.itemCat ne '0'}">
														<tr id="trBillPeriod" style='${((service.billFromDisplay ne service.billToDisplay && "1" ne _b_bilForm.map.bilHeaderInfo.isDuplicate) || ("1" ne _b_bilForm.map.bilHeaderInfo.isDuplicate && "1" eq _b_bilForm.map.bilHeaderInfo.billingPeriod))? "":"display:none;"}'>
															<td class="fontSize">&nbsp;</td>
															<td class="fontSize">&nbsp;</td>
															<td class="fontSize">
																<bean:message key="screen.b_bil.billingPeriod"/>
																<bean:message key="screen.b_bil.colon1"/>
																<bean:message key="screen.b_bil.from"/>
																<span class="spanBillFromDisplay">
																	${service.billFromDisplay}
																</span>
																<bean:message key="screen.b_bil.toInfo"/>
																<span class="spanBillToDisplay">
																	${service.billToDisplay}
																</span>
															</td>
															<td class="fontSize">&nbsp;</td>
															<td class="fontSize">&nbsp;</td>
															<td class="fontSize">&nbsp;</td>
															<td class="fontSize">&nbsp;</td>
														</tr>
													</c:if>
													</tr>
													<tr>
														<td colspan="7" class="fontSize">&nbsp;
														</td>
													</tr>
												</table>
											</div>
										</nested:iterate>
										</nested:present>
									</td>
								</tr>
								<!-- End Detail content -->
							</TBODY>
						</table>
					</td>
				</tr>
			</table>
			</td>
			</tr>
			<tr>
				<td>
					<table width="100%" cellpadding="0" cellspacing="0" >
						<col width="85%"/>
						<col width="10%"/>
						<col width="5%"/>
						<tr style="font-weight: bold;">
					   		<td align="right" style="background-color:rgb(200,210,230)">
								<bean:message key="screen.b_bil.subTotal"/>
								<%-- &lt;
								<span id="spanSubTotalBillCurrency">
									<nested:write property="billCurrency"/>
								</span>
								&gt;&nbsp;&nbsp; --%>
								<span id="spanSubTotalBillCurrency">
									<nested:write property="billCurrency"/>
								</span>
								&nbsp;&nbsp;
							</td>
							<td align="right" style="background-color:rgb(200,210,230)">
							<%-- 
								<c:if test="${_b_bilForm.map.bilHeaderInfo.billType eq 'CN'}">
						    		-
						    	</c:if>
						    --%>
								<span id="subTotal">
									<!-- Delete #156 Start-->
									<!--<fmt:formatNumber value="${subTotalAmt}" pattern="#,##0.00"/>-->
									<!-- Delete #156 End--> 
									<!-- Add #156 Start-->
									<c:if test="${_b_bilForm.map.bilHeaderInfo.billType eq 'CN'}">
										<c:if test="${_b_bilForm.map.billCnAmtNegative eq '1'}">
											-<fmt:formatNumber value="${subTotalAmt}" pattern="#,##0.00"/>
										</c:if>
										<c:if test="${_b_bilForm.map.billCnAmtNegative != '1'}">
											<fmt:formatNumber value="${subTotalAmt}" pattern="#,##0.00"/>
										</c:if>
									</c:if>
									<c:if test="${_b_bilForm.map.bilHeaderInfo.billType != 'CN'}">
										<fmt:formatNumber value="${subTotalAmt}" pattern="#,##0.00"/>
									</c:if>
									<!-- Add #156 End--> 
								</span>
								<nested:hidden property="subTotalAmt" styleId="subTotalAmt" value="${subTotalAmt}"/>
							</td>
							<td style="background-color:rgb(200,210,230)">
								&nbsp;
							</td>
						</tr>
						<tr style="font-weight: bold">
					   		<td align="right" style="background-color:rgb(200,210,230)">
								<bean:message key="screen.b_bil.gstAmount"/>
								<%-- <span id="spanGstPercent">
									<nested:write property="gstPercent"/>
								</span>
								<nested:hidden property="gstPercent" styleId="gstPercent"/> --%>
								<span id="spanSubTotalBillCurrency2">
									<nested:write property="billCurrency"/>
								</span>&nbsp;&nbsp;
							</td>
							<td align="right" style="background-color:rgb(200,210,230)">
							<%-- 
								<c:if test="${_b_bilForm.map.bilHeaderInfo.billType eq 'CN'}">
						    		-
						    	</c:if>
						    --%>
								<span id="gstAmountDisplay">
									<!-- Delete #156 Start-->
									<!--<fmt:formatNumber value="${_b_bilForm.map.bilHeaderInfo.gstAmount}" pattern="#,##0.00"/>-->
									<!-- Delete #156 End--> 
									<!-- Add #156 Start-->
									<c:if test="${_b_bilForm.map.bilHeaderInfo.billType eq 'CN'}">
										<c:if test="${_b_bilForm.map.billCnAmtNegative eq '1'}">
											-<fmt:formatNumber value="${_b_bilForm.map.bilHeaderInfo.gstAmount}" pattern="#,##0.00"/>
										</c:if>
										<c:if test="${_b_bilForm.map.billCnAmtNegative != '1'}">
											<fmt:formatNumber value="${_b_bilForm.map.bilHeaderInfo.gstAmount}" pattern="#,##0.00"/>
										</c:if>
									</c:if>
									<c:if test="${_b_bilForm.map.bilHeaderInfo.billType != 'CN'}">
										<fmt:formatNumber value="${_b_bilForm.map.bilHeaderInfo.gstAmount}" pattern="#,##0.00"/>
									</c:if>
									<!-- Add #156 End--> 
								</span>
								<nested:hidden property="gstAmount" styleId="gstAmount"/>
							</td>
							<td style="background-color:rgb(200,210,230)">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td style="background-color:rgb(200,210,230)">
								&nbsp;
							</td>
							<td style="background-color:rgb(200,210,230)">
								&nbsp;
							</td>
							<td style="background-color:rgb(200,210,230)">
								&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
			<td>
			<table width="100%" cellpadding="0" cellspacing="0" style="font-weight:bold">
				<tr align="center">
					<td width="40%" style="background-color:rgb(136,167,216)">
					</td>
					<td width="40%" style="background-color:rgb(136,167,216)"  align="left">
						<bean:message key="screen.b_bil.grandTotalU"/>
						&lt;
						<span id="spanGrandTotalUBillCurrency">
							<nested:write property="billCurrency"/>
						</span>
						&gt;&nbsp;&nbsp;
						&nbsp;
					</td>
					<td width="5%" style="background-color:rgb(136,167,216)">
						&nbsp;
					</td>
					<td width="10%" align="right" style="background-color:rgb(136,167,216);font-weight:bold">
					<%-- 
						<c:if test="${_b_bilForm.map.bilHeaderInfo.billType eq 'CN'}">
				    		-
				    	</c:if>
				    --%>
						<span id="billAmountDisplay">
							<!-- Delete #156 Start-->
							<!--<fmt:formatNumber value="${_b_bilForm.map.bilHeaderInfo.billAmount}" pattern="#,##0.00"/>-->
							<!-- Delete #156 End--> 
							<!-- Add #156 Start-->
							<c:if test="${_b_bilForm.map.bilHeaderInfo.billType eq 'CN'}">
								<c:if test="${_b_bilForm.map.billCnAmtNegative eq '1'}">
									-<fmt:formatNumber value="${_b_bilForm.map.bilHeaderInfo.billAmount}" pattern="#,##0.00"/>
								</c:if>
								<c:if test="${_b_bilForm.map.billCnAmtNegative != '1'}">
									<fmt:formatNumber value="${_b_bilForm.map.bilHeaderInfo.billAmount}" pattern="#,##0.00"/>
								</c:if>
							</c:if>
							<c:if test="${_b_bilForm.map.bilHeaderInfo.billType != 'CN'}">
								<fmt:formatNumber value="${_b_bilForm.map.bilHeaderInfo.billAmount}" pattern="#,##0.00"/>
							</c:if>
							<!-- Add #156 End--> 
						</span>
						<nested:hidden property="billAmount" styleId="billAmount"/>
					</td>
					<td width="5%" style="background-color:rgb(136,167,216)">
						&nbsp;
					</td>
				</tr>
			</table>
			<span id="spanGrandTotal">
			    <c:if test="${_b_bilForm.map.bilHeaderInfo.billCurrency ne _b_bilForm.map.bilHeaderInfo.exportCur 
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
									1&nbsp;<nested:write property="billCurrency"/>
									&nbsp;=&nbsp;
									<c:if test="${not empty _b_bilForm.map.bilHeaderInfo.fixedForex}">
										<bean:message key="screen.b_bil.fixedForex"/>
									</c:if>
									<nested:text property="curRate" styleId="curRate" onkeypress="return onlyDecNumbers(this,event)" onchange="curRateChange(this)" style="text-align:right;width:100px;"/>
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
										<nested:text property="curRate" styleId="curRate" onkeypress="return onlyDecNumbers(this,event)" onchange="curRateChange(this)" style="text-align:right;width:100px;"/>
										&nbsp;<nested:write property="billCurrency"/>
										)
									</c:if>
									<c:if test="${_b_bilForm.map.bilHeaderInfo.billCurrency ne _b_bilForm.map.bilHeaderInfo.defCurrency}">
										<bean:message key="screen.b_bil.grandTotalU"/>
										&nbsp;<nested:write property="exportCur"/>
										&nbsp;(
										1&nbsp;<nested:write property="billCurrency"/>
										&nbsp;=&nbsp;
										<c:if test="${not empty _b_bilForm.map.bilHeaderInfo.fixedForex}">
											<bean:message key="screen.b_bil.fixedForex"/>
										</c:if>
										<nested:text property="curRate" styleId="curRate" onkeypress="return onlyDecNumbers(this,event)" onchange="curRateChange(this)" style="text-align:right;width:100px;"/>
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
	        </span>
			</td></tr>
			<tr><td>
			<div style="font-weight:bold">
				(<span id="spanBillAmountBillCurrency">
				<t:writeCodeValue codeList="LIST_CURRENCY" key="${_b_bilForm.map.bilHeaderInfo.billCurrency}"/>
				</span>
				<bean:message key="screen.b_bil.amount"/>: 
				<span id="billAmountToStr">
				${bs:numberToWord(_b_bilForm.map.bilHeaderInfo.billAmount )}
				</span><bean:message key="screen.b_bil.onlyNoSpace"/>
				<br/>
			</div>
			<%-- 
			<c:if test="${_b_bilForm.map.bilHeaderInfo.billType eq 'IN' 
					or _b_bilForm.map.bilHeaderInfo.billType eq 'DN'}">
			--%>
				<div class="footerInfo">
					<% int footerCounter = 0; %>
					<nested:iterate id="footer" property="footerInfo">
						<% 
							footerCounter++;
							if(footerCounter != 15){
						%>
						<nested:notEmpty name="footer" property="SET_VALUE">
							<pre><bean:write name="footer" property="SET_VALUE"/>&nbsp;</pre>
						</nested:notEmpty>
						<%	} %>
					</nested:iterate>
				</div>
			<%--
			</c:if>
			--%>
			<%--
			<c:if test="${_b_bilForm.map.bilHeaderInfo.billType eq 'IN' or _b_bilForm.map.bilHeaderInfo.billType eq 'DN'}">
			--%>
				<div class="footerInfo">
					<table width="100%" cellpadding="0" cellspacing="0" style="font-weight:bold">	
						<tr>
							<td>
								<table width="100%" cellpadding="0" cellspacing="0">
									<tr>
										<td><pre><bean:message key="screen.b_bil.bank"/> <bean:message key="screen.b_bil.colon"/>&nbsp;</pre></td>
									</tr>
									<tr> 
										<td>
											<pre>&nbsp;</pre>
										</td>
									</tr>
									<tr> 
										<td><pre><bean:message key="screen.b_bil.accountName"/> <bean:message key="screen.b_bil.colon"/>&nbsp;</pre></td>
									</tr>
									<tr> 
										<td><pre><bean:message key="screen.b_bil.accountNo"/> <bean:message key="screen.b_bil.colon"/>&nbsp;</pre></td>
									</tr>
									<tr> 
										<td><pre><bean:message key="screen.b_bil.swiftCode"/> <bean:message key="screen.b_bil.colon"/>&nbsp;</pre></td>
									</tr>
								</table>
							</td>
							<nested:iterate id="bankFooter" property="bankFooterInfo">				
							<td>
								<table width="100%" cellpadding="0" cellspacing="0">
									<tr>
										<td><pre><nested:write name="bankFooter" property="BANK_NAME"/>&nbsp;</pre></td>
									</tr>
									<tr> 
										<td><pre><nested:write name="bankFooter"  property="BRANCH_NAME"/>&nbsp;</pre></td>
									</tr>
									<tr> 
										<td><pre><nested:write name="bankFooter" property="COM_ACCT_NAME"/>&nbsp;</pre></td>
									</tr>
									<tr> 
										<td><pre><nested:write name="bankFooter" property="COM_ACCT_NO"/>&nbsp; (<bean:write name="bankFooter" property="COM_CUR"/>&nbsp;)</pre></td>
									</tr>
									<tr> 
										<td><pre><nested:write name="bankFooter" property="COM_SWIFT"/>&nbsp;</pre></td>
									</tr>
								</table>
							</td>
							</nested:iterate>
						</tr>
					</table>
				</div>
			<%--
			</c:if>
			--%>
			<hr class="lineBottom"/>
			<br/>
            <input type="button" name="forward_save" value="Save" onclick="onSave()" class="button"/>
            <c:if test="${_b_bilForm.map.bilHeaderInfo.mode eq 'new'}">
           		<input type="button"  id="newExit" value='Exit' class="button" onclick="ClickExit()"/>
<%--                 <bs:buttonLink action="/RP_B_BIL_S01_01BL" value="Exit1"/> --%>
            </c:if>
            <c:if test="${_b_bilForm.map.bilHeaderInfo.mode eq 'edit'}">
            
            <input type="button"  id="editExit" value='Exit' class="button" onclick="ClickExit()"/>
<%--                 <bs:buttonLink action="/RP_B_BIL_S02_01BL.do?idRef=${_b_bilForm.map.bilHeaderInfo.idRef}&mode=view&fromPageFlag=BIL" value="Exit"/> --%>
            </c:if>
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
			</td></tr></table>
			</nested:nest>
			</div>
			
			<!-- Non Item Billing -->
			<div id="idItemBillDetail" class="itemBillDetail" style="display:none;">
				<table cellspacing="0" cellpadding="0" style="width:100%">
					<col width="11%"/>
					<col width="47%"/>
					<col width="42%"/>
					<tr>
						<td style="padding-left:10px;" class="fontSize" valign="top">
							<span class="removeLink" onclick="removeBillItem(this)" style="cursor: pointer;">
								<img alt="" src="<%=request.getContextPath()%>/image/delete.gif">
							</span>
							&nbsp;&nbsp;&nbsp;
							<span class="editLink" onclick="editBillItem(this)" style="cursor: pointer;">
								<img alt="" src="<%=request.getContextPath()%>/image/editIcon.jpg">
							</span>
						</td>
						<td class="fontSize" style="width:470px;word-wrap: break-word;white-space : normal">
							<div class="notItemBillDesc" style="font-size:16px;color:#CD853F;"></div>
							<input type="hidden" class="serviceItemDesc" name="serviceItemDesc[]"/>
							<input type="hidden" class="serviceItemCat" name="serviceItemCat[]"/>
							<input type="hidden" class="serviceItemId" name="serviceItemId[]"/>
							<input type="hidden" class="serviceIsDisplay" name="serviceIsDisplay[]"/>
						</td>
						<td class="fontSize">&nbsp;</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>
			</div>
			
			<div id="message_group" style="display: none;">
				<div class="messageRemoveBillItem"><bean:message key="info.ERR4SC003" arg0="Billing Item"/></div>
				<div class="messageSelectBillAcc"><bean:message key="errors.ERR1SC038" arg0="Billing Account No."/></div>
			</div>
		</ts:form>
	</ts:body>
</html:html>