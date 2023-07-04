<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="/WEB-INF/bs" prefix="bs" %>

<html>
<head>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
	<link href="${pageContext.request.contextPath}/B_BAC/css/b_bac.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   	<title><bean:message key="screen.b_bac_s01.title"/></title>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$('#cboAdrType').change(function(){
				$('#editForm').attr('action', 'RP_B_BAC_S02_03_03BL.do');
				$('#editForm').submit();
				$('#editForm').attr('action', 'SC_B_BAC_S02_03DSP.do');
			});
			$('#cboAttn').change(function(){
				$('#editForm').attr('action', 'RP_B_BAC_S02_03_03BL.do');
				$('#editForm').submit();
				$('#editForm').attr('action', 'SC_B_BAC_S02_03DSP.do');
			});
			//$('#cboPaymentMethod').change(function(){
			//	$('#editForm').attr('action', 'RP_B_BAC_S02_03_03BL.do');
			//	$('#editForm').submit();
			//	$('#editForm').attr('action', 'SC_B_BAC_S02_03DSP.do');
			//});
			
			changeCurrency("1");
			if($.trim($(".message").text())==""){
			    // init
			}else{
			    var contextPath = $("#contextPath").val();
			    var billacc = $("#billacc").val();
			    var fromPage = document.getElementById("fromPage").value;
			    var idCustPlan = document.getElementById("inputHeaderInfo.idCustPlan").value;
			    // update success 
			    window.opener.location=contextPath+"/B_BAC/RP_B_BAC_S02_02_01BL.do?idBillAccount="+billacc+"&fromPage="+fromPage+"&idCustPlan="+idCustPlan;
			    window.close();
			}
		});
		function changeCurrency(typeFlag) {
			var cboBillCurrency = $("#cboBillCurrency").val();
			if(cboBillCurrency!=null && cboBillCurrency != undefined) {
				var defCurrency = $("#defCurrency").val();
				var cboExportCurrency = $("#cboExportCurrency").val();
				removeAllExportCurrency();
				if(cboBillCurrency!=defCurrency) {
					$("#cboExportCurrency").append("<option value='"+defCurrency+"'>"+defCurrency+"</option>");
					//Init Flag
					if("1"==typeFlag) {
						var hidCboExportCurrency = $("#hidCboExportCurrency").val();
						$("#cboExportCurrency").val(hidCboExportCurrency);
					} else {
						//Change Bill Currency ComboBox
						$("#cboExportCurrency").val("");
					}
				} else {
					addAllExportCurrency();
					$("#cboExportCurrency").val(cboExportCurrency);
				}
			}
		}
		
		function removeAllExportCurrency(){
			var count=$("#cboExportCurrency option").length;
			for(var i=1;i<count;i++) {
				var value = $("#cboExportCurrency").get(0).options[1].value;
				$("#cboExportCurrency option[value='"+value+"']").remove();
			}
		}
		
		function addAllExportCurrency() {
			var count=$("#hidListGrandTotalCur option").length;
			for(var i=0;i<count;i++) {
				var value = $("#hidListGrandTotalCur").get(0).options[i].value;
				var text = $("#hidListGrandTotalCur").get(0).options[i].text;
				$("#cboExportCurrency").append("<option value='"+value+"'>"+text+"</option>");
			}
		}
		
		function clickExit(){
			var MsgBox = new messageBox();
			if (MsgBox.POPEXT($('#hiddenMsgExitConfirmation').val()) == MsgBox.YES_CONFIRM) {
			    window.close();
			}
			//var fromPage = document.getElementById("fromPage").value;
			//var idCustPlan = document.getElementById("inputHeaderInfo.idCustPlan").value;
			//if(fromPage=="B_CPM_S02V") {
				//$('#editForm').attr("action", "<%=request.getContextPath()%>/B_CPM/B_CPM_S01DSP.do?event=forward_viewCustomerPlan&customerPlan.idCustPlan="+idCustPlan);
				//$('#editForm').submit();
			//} else if(fromPage=="B_BIL_S01") {
				//$('#editForm').attr("action", "<%=request.getContextPath()%>/B_BIL/RP_B_BIL_S01_01BL.do");
				//$('#editForm').submit();
			//} else if(fromPage=="B_CSB_S01") {
				//$('#editForm').attr("action", "<%=request.getContextPath()%>/B_CSB/B_CSBR01BLogic.do");
				//$('#editForm').submit();
			//} else if(fromPage=="B_BIFS02_03"||fromPage=="B_BIFS03_04"){
				//$('#editForm').attr("action","<%=request.getContextPath()%>/BIF/B_BIFS01_01BL.do?idRef="+$("#idRef").val());
				//$('#editForm').submit();
			//} else {
				//$('#editForm').attr('action', 'RP_B_BAC_S01_01BL.do');
				//$('#editForm').submit();
			//}
		}
		function emailCheck(obj){
			if(obj.checked){
				obj.value = "1";
			}
			else{
				obj.value="0";
			}
		}
	</script>
</head>
<body id="b" onload="initMain();">
<ts:form action="/SC_B_BAC_S02_03DSP" styleId="editForm">
	<html:hidden property="fromPage" name="_b_bacForm" styleId="fromPage"/>
	<html:hidden property="idRef" name="_b_bacForm" styleId="idRef"/>
	<input type="hidden" value="${pageContext.request.contextPath}" id="contextPath"/>
	<input type="hidden" id="hiddenMsgExitConfirmation" value="<bean:message key="info.ERR4SC001"/>"/>
	<h1 class="title"><bean:message key="screen.b_bac_s01.title"/></h1>
		<div>
			<div>
				<fieldset class="noBorder fieldsetPadding">
					<table cellpadding="0" cellspacing="0" width="100%">
						<col width="15%"/><col width="35%"/><col width="15%"/><col width="35%"/>
						<tr>
							<td>
								<bean:message key="screen.b_bac.customerName"/>
							</td>
							<td>
								<bean:message key="screen.b_bac.colon"/>
								${_b_bacForm.map.custInfo.CUST_NAME}
								<html:hidden property="inputHeaderInfo.idCust" value="${_b_bacForm.map.custInfo.ID_CUST}"/>
							</td>
							<td nowrap="nowrap">
                                <bean:message key="screen.b_bac.billingAccountNo"/>
                            </td>
                            <td>
                                <bean:message key="screen.b_bac.colon"/>
                                ${not empty _b_bacForm.map.headerInfo.ID_BILL_ACCOUNT 
                                            ? _b_bacForm.map.headerInfo.ID_BILL_ACCOUNT : _b_bacForm.map.inputHeaderInfo.idBillAccount}
                                <html:hidden property="inputHeaderInfo.idBillAccount" value="${not empty _b_bacForm.map.headerInfo 
                                                                                                ? _b_bacForm.map.headerInfo.ID_BILL_ACCOUNT : _b_bacForm.map.inputHeaderInfo.idBillAccount}"/>          
                                <input type="hidden" id="billacc" value="${not empty _b_bacForm.map.headerInfo 
                                                                                                ? _b_bacForm.map.headerInfo.ID_BILL_ACCOUNT : _b_bacForm.map.inputHeaderInfo.idBillAccount}">
                            </td>
						</tr>
						<tr>
                            <td nowrap="nowrap">
                                <bean:message key="screen.b_bac_s02.customerId"/>
                            </td>
                            <td>
                                <bean:message key="screen.b_bac.colon"/>
                                ${_b_bacForm.map.custInfo.ID_CUST}
                            </td>
                        </tr>
					</table>
				</fieldset>
			</div>
			<div>
				<fieldset class="fieldsetPadding fieldsetBorder">
					<legend>
						<bean:message key="screen.b_bac.accountInfo"/>
					</legend>
					<table cellpadding="0" cellspacing="0" width="100%">
						<col width="15%"/><col width="25%"/><col width="60%"/>
						<tr>
						    <td nowrap="nowrap"><bean:message key="screen.b_bac_s02.documentDeliever"/></td>
                            <td>
                                <bean:message key="screen.b_bac.colon"/>&nbsp;
                                <html:checkbox value="1" name="_b_bacForm"  property="inputHeaderInfo.exportSingPost">
                                </html:checkbox>
                                <bean:message key="screen.b_bac_s02.singPost"/>
                                <!-- Email -->
                                <br/>&nbsp;&nbsp;&nbsp;&nbsp;
							    <html:checkbox value="1" name="_b_bacForm"  property="inputHeaderInfo.deliveryEmail">
                                </html:checkbox>
                                <bean:message key="screen.b_bac_s02.email"/>
                                <!-- Delivery -->
                                <br/>&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio property="inputHeaderInfo.delivery" styleClass="delivery" value="3" />
								<bean:message key="screen.b_bac_s02.none"/>
								<html:radio property="inputHeaderInfo.delivery" styleClass="delivery" value="1" />
								<bean:message key="screen.b_bac_s02.post"/>
								<html:radio property="inputHeaderInfo.delivery" styleClass="delivery" value="2" />
								<bean:message key="screen.b_bac_s02.courier"/>
								<html:radio property="inputHeaderInfo.delivery" styleClass="delivery" value="4" />
								<bean:message key="screen.b_bac_s02.byhand"/>	 	 
                            </td>
                            <td rowspan="6">
	                            <fieldset class="fieldsetPadding fieldsetBorder">
				                    <legend>
				                        <bean:message key="screen.b_bac.billAddr"/>
				                    </legend>
				                    <table class="inputInfo" cellpadding="0" cellspacing="0" width="100%">
				                        <col width="20%" align="right"/><col width="80%"/>
				                        <tr>
				                            <td>
				                                <html:select property="inputHeaderInfo.custAdrType" name="_b_bacForm"
				                                    value="${not empty _b_bacForm.map.headerInfo 
				                                                        ? _b_bacForm.map.headerInfo.CUST_ADR_TYPE : _b_bacForm.map.inputHeaderInfo.custAdrType}" 
				                                                        styleId="cboAdrType">
				                                    <html:option value="" ><bean:message key="screen.b_bac.blankItem"/></html:option>
				                                    <html:option value="BA">Billing Address1</html:option>
				                                    <html:option value="BA2">Billing Address2</html:option>
				                                    <html:option value="BA3">Billing Address3</html:option>
				                                    <html:option value="BA4">Billing Address4</html:option>
				                                    <html:option value="CA">Correspondence Address</html:option>
				                                    <html:option value="RA">Registered Address</html:option>
				                                </html:select><bean:message key="screen.b_bac.colon"/>
				                            </td>
				                            <td>
				                                ${_b_bacForm.map.addressInfo.address1}
				                            </td>
				                        </tr>
				                        <tr>
				                            <td>
				                                &nbsp;
				                            </td>
				                            <td>
				                                ${_b_bacForm.map.addressInfo.address2}
				                            </td>
				                        </tr>
				                        <tr>
				                            <td>
				                                &nbsp;
				                            </td>
				                            <td>
				                                ${_b_bacForm.map.addressInfo.address3}
				                            </td>
				                        </tr>
				                        <tr>
				                            <td>
				                                &nbsp;
				                            </td>
				                            <td>
				                                ${_b_bacForm.map.addressInfo.address4}
				                            </td>
				                        </tr>
				                        <tr>
				                            <td>
				                                <bean:message key="screen.b_bac.attn"/><bean:message key="screen.b_bac.colon"/>
				                            </td>
				                            <td>
				                                <c:if test="${_b_bacForm.map.custInfo.CUSTOMER_TYPE ne 'CONS'}">
				                                    <html:select property="inputHeaderInfo.contactType" name="_b_bacForm"
				                                         value="${not empty _b_bacForm.map.headerInfo 
				                                                            ? _b_bacForm.map.headerInfo.CONTACT_TYPE : _b_bacForm.map.inputHeaderInfo.contactType}" 
				                                                            styleId="cboAttn">
				                                        <html:option value="" ><bean:message key="screen.b_bac.blankItem"/></html:option>
				                                        <html:optionsCollection property="listContact" name="_b_bacForm" label="label" value="value"/>
				                                    </html:select>
				                                </c:if>
				                                <c:if test="${_b_bacForm.map.custInfo.CUSTOMER_TYPE eq 'CONS'}">
				                                    <html:select property="inputHeaderInfo.contactType" name="_b_bacForm" styleId="cboAttn" disabled="true">
				                                        <html:option value="" ><bean:message key="screen.b_bac.blankItem"/></html:option>
				                                    </html:select>
				                                    <html:hidden property="inputHeaderInfo.contactType" name="_b_bacForm"/>
				                                </c:if>
				                            </td>
				                        </tr>
				                        <tr>
				                            <td>
				                                <bean:message key="screen.b_bac.email_to"/><bean:message key="screen.b_bac.colon"/>
				                            </td>
				                            <td>
				                                <c:choose>
				                                    <c:when test="${empty _b_bacForm.map.addressInfo.email}">
				                                        <bean:message key="screen.b_bac._"/>
				                                    </c:when> 
				                                    <c:otherwise>
				                                        ${_b_bacForm.map.addressInfo.email}
				                                    </c:otherwise>
				                                </c:choose>
				                            </td>
				                        </tr>
				                        <tr>
				                            <td>
				                                <bean:message key="screen.b_bac.email_cc"/><bean:message key="screen.b_bac.colon"/>
				                            </td>
				                            <td>
				                                <c:choose>
				                                    <c:when test="${empty _b_bacForm.map.addressInfo.email_cc}">
				                                        <bean:message key="screen.b_bac._"/>
				                                    </c:when> 
				                                    <c:otherwise>
				                                        ${_b_bacForm.map.addressInfo.email_cc}
				                                    </c:otherwise>
				                                </c:choose>
				                            </td>
				                        </tr>
				                    </table>
				                </fieldset>
                            </td>
						</tr>
						<tr>
						    <%-- Payment Method --%>
							<td nowrap="nowrap"><bean:message key="screen.b_bac.paymentMethod"/></td>
							<td>
								<bean:message key="screen.b_bac.colon"/>&nbsp;
								<t:defineCodeList id="LIST_PAYMENT_METHOD" />
								<html:select property="inputHeaderInfo.paymentMethod" name="_b_bacForm"
									value="${not empty _b_bacForm.map.headerInfo 
												? _b_bacForm.map.headerInfo.PAYMENT_METHOD : _b_bacForm.map.inputHeaderInfo.paymentMethod}" 
									styleId="cboPaymentMethod" style="width:260px;">
									<html:option value="" ><bean:message key="screen.b_bac.blankItem"/></html:option>
									<c:forEach items="${LIST_PAYMENT_METHOD}" var="item">
										<c:choose>
											<c:when test="${item.id eq 'GR'}">
												<c:if test="${_b_bacForm.map.isDisplayGiro}">
													<html:option value="${item.id}" >${item.name}</html:option>
												</c:if>
											</c:when>
											<c:when test="${item.id eq 'CC'}">
												<c:if test="${_b_bacForm.map.isDisplayCC}">
													<html:option value="${item.id}" >${item.name}</html:option>
												</c:if>
											</c:when>
											<c:otherwise>
												<html:option value="${item.id}" >${item.name}</html:option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</html:select>
								<html:hidden property="isDisplayGiro" name="_b_bacForm"/>
								<html:hidden property="isDisplayCC" name="_b_bacForm"/>
								<html:hidden property="inputHeaderInfo.bacDCount" 
									value="${not empty _b_bacForm.map.headerInfo ? _b_bacForm.map.headerInfo.BAC_D_COUNT:_b_bacForm.map.inputHeaderInfo.bacDCount}" 
									name="_b_bacForm"/>
							</td>
						</tr>
						<tr>
						     <%-- Billing Currency --%>
							<td><bean:message key="screen.b_bac.billingCurrency"/></td>
							<td>
								<bean:message key="screen.b_bac.colon"/>&nbsp;
								<t:defineCodeList id="LIST_CURRENCY" />
								<c:if test="${not empty _b_bacForm.map.headerInfo ?(_b_bacForm.map.headerInfo.BAC_D_COUNT ne 0): (_b_bacForm.map.inputHeaderInfo.bacDCount ne 0)}">
									<c:forEach items="${LIST_CURRENCY}" var="item">
										<c:if test="${(not empty _b_bacForm.map.headerInfo ? _b_bacForm.map.headerInfo.BILL_CURRENCY : _b_bacForm.map.inputHeaderInfo.billCurrency) 
																		eq item.id}">
											${item.name}
										</c:if>
									</c:forEach>
									<html:hidden property="inputHeaderInfo.billCurrency" 
											value="${not empty _b_bacForm.map.headerInfo 
															? _b_bacForm.map.headerInfo.BILL_CURRENCY : _b_bacForm.map.inputHeaderInfo.billCurrency}"/>
								</c:if>
								<c:if test="${not empty _b_bacForm.map.headerInfo ?(_b_bacForm.map.headerInfo.BAC_D_COUNT eq 0): (_b_bacForm.map.inputHeaderInfo.bacDCount eq 0)}">
									<html:select property="inputHeaderInfo.billCurrency" name="_b_bacForm"
										value="${not empty _b_bacForm.map.headerInfo 
																? _b_bacForm.map.headerInfo.BILL_CURRENCY : _b_bacForm.map.inputHeaderInfo.billCurrency}" 
										styleId="cboBillCurrency" onchange="changeCurrency('2');" style="width:260px;">
										<html:option value="" ><bean:message key="screen.b_bac.blankItem"/></html:option>
										<html:optionsCollection name="LIST_CURRENCY" value="id" label="name"/>
									</html:select>
								</c:if>
							</td>
						</tr>
						<tr>
						    <%-- Export Currency --%>
							<td nowrap="nowrap">
								<bean:message key="screen.b_bac.exportCurrency"/>
							</td>
							<td>
								<bean:message key="screen.b_bac.colon"/>&nbsp;
								<c:if test="${not empty _b_bacForm.map.headerInfo ?(_b_bacForm.map.headerInfo.BAC_D_COUNT ne 0): (_b_bacForm.map.inputHeaderInfo.bacDCount ne 0)}">
									<%-- 
									<c:forEach items="${_b_bacForm.map.listGrandTotalCur}" var="item">
										<c:if test="${(not empty _b_bacForm.map.headerInfo ? _b_bacForm.map.headerInfo.EXPORT_CURRENCY : _b_bacForm.map.inputHeaderInfo.exportCurrency) 
																		eq item.value}">
											${item.label}
											<bean:define id="isInListGrandTotalCur" value="1"/>
										</c:if>
									</c:forEach>
									--%>
									${not empty _b_bacForm.map.headerInfo ? _b_bacForm.map.headerInfo.EXPORT_CURRENCY : _b_bacForm.map.inputHeaderInfo.exportCurrency}
									<html:hidden property="inputHeaderInfo.exportCurrency" 
												value="${not empty _b_bacForm.map.headerInfo 
																? _b_bacForm.map.headerInfo.EXPORT_CURRENCY : _b_bacForm.map.inputHeaderInfo.exportCurrency}"/>
								</c:if>
								<c:if test="${not empty _b_bacForm.map.headerInfo ?(_b_bacForm.map.headerInfo.BAC_D_COUNT eq 0): (_b_bacForm.map.inputHeaderInfo.bacDCount eq 0)}">
									<html:select property="inputHeaderInfo.exportCurrency" name="_b_bacForm"
										value="${not empty _b_bacForm.map.headerInfo 
																? _b_bacForm.map.headerInfo.EXPORT_CURRENCY : _b_bacForm.map.inputHeaderInfo.exportCurrency}" 
										styleId="cboExportCurrency" style="width:260px;">
										<html:option value="" ><bean:message key="screen.b_bac.blankItem"/></html:option>
										<html:optionsCollection property="listGrandTotalCur" name="_b_bacForm" label="label" value="value"/>
									</html:select>
									<input type="hidden" id="hidCboExportCurrency" value="${not empty _b_bacForm.map.headerInfo ? _b_bacForm.map.headerInfo.EXPORT_CURRENCY : _b_bacForm.map.inputHeaderInfo.exportCurrency}" />
								</c:if>
							</td>
						</tr>
                        <c:if test="${_b_bacForm.map.fixedForex eq '1'}">
						<tr>
						    <%-- Fixed Forex --%>
							<td>
								<bean:message key="screen.b_bac.fixedForex"/>
							</td>
							<td>
								<bean:message key="screen.b_bac.colon"/>&nbsp;
								<c:if test="${not empty _b_bacForm.map.headerInfo ?(_b_bacForm.map.headerInfo.BAC_D_COUNT ne 0): (_b_bacForm.map.inputHeaderInfo.bacDCount ne 0)}">
									${not empty _b_bacForm.map.headerInfo ? _b_bacForm.map.headerInfo.FIXED_FOREX: _b_bacForm.map.inputHeaderInfo.fixedForex}
									<html:hidden property="inputHeaderInfo.fixedForex" 
												value="${not empty _b_bacForm.map.headerInfo 
																? _b_bacForm.map.headerInfo.FIXED_FOREX : _b_bacForm.map.inputHeaderInfo.fixedForex}"/>
								</c:if>
								<c:if test="${not empty _b_bacForm.map.headerInfo ?(_b_bacForm.map.headerInfo.BAC_D_COUNT eq 0): (_b_bacForm.map.inputHeaderInfo.bacDCount eq 0)}">
									<html:text property="inputHeaderInfo.fixedForex" name="_b_bacForm"
										value="${not empty _b_bacForm.map.headerInfo 
																? _b_bacForm.map.headerInfo.FIXED_FOREX : _b_bacForm.map.inputHeaderInfo.fixedForex}" 
									     styleId="txtFixedForex" style="width:140px;text-align: right;">
									</html:text>
								</c:if>
							</td>
						</tr>
                        </c:if>
                        <c:if test="${_b_bacForm.map.fixedForex ne '1'}">
                        <tr>
                            <td>&nbsp;</td>
                            <td>
                            &nbsp;
                            </td>
                        </tr>
                        </c:if>
                        <tr>
                            <td>&nbsp;</td>
                            <td>
                            &nbsp;
                            </td>
                        </tr>
                        <tr>
                        	<td colspan="2">
                        		<html:checkbox value="1" name="_b_bacForm" property="inputHeaderInfo.multiBillPeriod">
                            	</html:checkbox>
                            	<bean:message key="screen.b_bac_s02.multi_billing_period"/>
                            </td>
                        </tr>
                        <tr>
                        	<td colspan="2">
                        		<html:checkbox value="1" name="_b_bacForm" property="inputHeaderInfo.isDisplayExpAmt">
                            	</html:checkbox>
                            	<bean:message key="screen.b_bac_s02.display_amount_at_invoice"/>
                            </td>
                        </tr>
					</table>
				</fieldset>
			</div>
		</div>
		<br/>
		<ts:submit value="Save" styleClass="button" property="forward_save"></ts:submit>
		<html:button property="forward_exit" styleClass="button" value="Exit" onclick="clickExit();"></html:button>
		<html:hidden name="_b_bacForm" property="fixedForex"/>
		<html:hidden property="idBillAccount" value="${not empty _b_bacForm.map.headerInfo.ID_BILL_ACCOUNT 
											? _b_bacForm.map.headerInfo.ID_BILL_ACCOUNT : _b_bacForm.map.inputHeaderInfo.idBillAccount}"/>
		<html:hidden property="idCustPlan" value="${not empty _b_bacForm.map.headerInfo? _b_bacForm.map.headerInfo.idCustPlan : _b_bacForm.map.inputHeaderInfo.idCustPlan}"/>
		<html:hidden property="inputHeaderInfo.idCustPlan" value="${not empty _b_bacForm.map.headerInfo 
																								? _b_bacForm.map.headerInfo.idCustPlan : _b_bacForm.map.inputHeaderInfo.idCustPlan}"/>
		<html:hidden styleId="defCurrency" property="inputHeaderInfo.defCurrency" value="${not empty _b_bacForm.map.headerInfo 
																						? _b_bacForm.map.headerInfo.DEF_CURRENCY : _b_bacForm.map.inputHeaderInfo.defCurrency}"/>
		<select id="hidListGrandTotalCur" style="display:none">
			<c:forEach items="${_b_bacForm.map.listGrandTotalCur}" var="item">
				<option value="${item.value}">${item.label}</option>
			</c:forEach>
		</select>
		<br/>
		<div class="message">
			<ts:messages id="message" message="true"><bean:write name="message"/></ts:messages>
		</div>
		<div class="error">
			<html:messages id="message">
				<bean:write name="message"/><br/>
			</html:messages>
		</div>
	</ts:form>
</body>
</html>
