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
				$('#newForm').attr('action', 'RP_B_BAC_S02_01_03BL.do');
				$('#newForm').submit();
				$('#newForm').attr('action', 'SC_B_BAC_S02_01DSP.do');
			});
			$('#cboAttn').change(function(){
				$('#newForm').attr('action', 'RP_B_BAC_S02_01_03BL.do');
				$('#newForm').submit();
				$('#newForm').attr('action', 'SC_B_BAC_S02_01DSP.do');
			});
			$('#cboPaymentMethod').change(function(){
				$('#newForm').attr('action', 'RP_B_BAC_S02_01_03BL.do');
				$('#newForm').submit();
				$('#newForm').attr('action', 'SC_B_BAC_S02_01DSP.do');
			});
		});
		//open cpm05
        function openB_CPM_View(url) {
            var width = window.screen.width*90/100;
            var height = window.screen.height*80/100;
            var left = Number((screen.availWidth/2) - (width/2));
            var top = Number((screen.availHeight/2) - (height/2));
            var offsetFeatures = "width=" + width + ",height=" + height +
                                 ",left=" + left + ",top=" + top +
                                 "screenX=" + left + ",screenY=" + top;
            var strFeatures= "toolbar=no, status=no, menubar=no,location=no,scrollbars=yes, resizable=yes" + "," + offsetFeatures;      
            var newwindow = window.open(url, null, strFeatures);
            if (window.focus) { newwindow.focus(); }
        }
		
        function ClickExit(){
        	var MsgBox = new messageBox();
        	if (MsgBox.POPEXT($('#hiddenMsgExitConfirmation').val()) == MsgBox.YES_CONFIRM) {
        		window.location.href="<%=request.getContextPath()%>/B_BAC/RP_B_BAC_S04_01BL.do";
        	}
        }
	</script>
</head>
<body id="b" onload="initMain();">
<ts:form action="/SC_B_BAC_S02_01DSP" styleId="newForm">
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
								<html:hidden property="cboCustomerName" value="${_b_bacForm.map.custInfo.ID_CUST}"/>
							</td>
							<td nowrap="nowrap">
                                <bean:message key="screen.b_bac.billingAccountNo"/>
                            </td>
                            <td>
                                <bean:message key="screen.b_bac.colon"/>
                                <bean:message key="screen.b_bac.generatedOnSave"/>
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
						    <td nowrap="nowrap"><bean:message key="screen.b_bac_s02.invoiceDeliever"/></td>
                            <td>
                                <bean:message key="screen.b_bac.colon"/>&nbsp;
                                <html:checkbox value="1" name="_b_bacForm" property="inputHeaderInfo.exportSingPost">
                                </html:checkbox>
                                <%-- <bean:message key="screen.b_bac_s02.singPost"/> --%>
                                ${_b_bacForm.map.inputHeaderInfo.singPostValue}
                                <html:hidden property="inputHeaderInfo.singPostValue" name="_b_bacForm"/>
                                 <!-- Email -->
                                <br/>&nbsp;&nbsp;&nbsp;&nbsp;
                                <html:checkbox value="1" name="_b_bacForm" property="inputHeaderInfo.deliveryEmail">
                                </html:checkbox>
                                <bean:message key="screen.b_bac_s02.email"/>
                                <!-- Delivery -->
                                <br/>&nbsp;&nbsp;&nbsp;&nbsp;
                                <html:radio value="3" name="_b_bacForm" property="inputHeaderInfo.delivery">
                                </html:radio>
                                <bean:message key="screen.b_bac_s02.none"/>
                                <html:radio value="1" name="_b_bacForm" property="inputHeaderInfo.delivery">
                                </html:radio>
                                <bean:message key="screen.b_bac_s02.post"/>
                                <html:radio value="2" name="_b_bacForm" property="inputHeaderInfo.delivery">
                                </html:radio>
                                <bean:message key="screen.b_bac_s02.courier"/>
                                <html:radio value="4" name="_b_bacForm" property="inputHeaderInfo.delivery">
                                </html:radio>
                                <bean:message key="screen.b_bac_s02.byhand"/>
                            </td>
                            <td rowspan="7">
                                <fieldset class="fieldsetPadding fieldsetBorder" style="margin-top:-10px">
				                    <legend>
				                        <bean:message key="screen.b_bac.billAddr"/>
				                    </legend>
				                    <table class="inputInfo" cellpadding="0" cellspacing="0">
				                        <col width="20%" align="right"/><col width="40%"/>
				                        <tr>
				                            <td nowrap="nowrap">
				                                <html:select property="inputHeaderInfo.custAdrType" name="_b_bacForm"
				                                    value="${_b_bacForm.map.inputHeaderInfo.custAdrType}" styleId="cboAdrType">
				                                    <html:option value="" ><bean:message key="screen.b_bac.blankItem"/></html:option>
				                                    <html:option value="BA">Billing Address1</html:option>
				                                    <html:option value="BA2">Billing Address2</html:option>
				                                    <html:option value="BA3">Billing Address3</html:option>
				                                    <html:option value="BA4">Billing Address4</html:option>
				                                    <html:option value="CA">Correspondence Address</html:option>
				                                    <html:option value="RA">Registered Address</html:option>
				                                </html:select>
				                                <bean:message key="screen.b_bac.colon"/>
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
				                                         value="${_b_bacForm.map.inputHeaderInfo.contactType}" styleId="cboAttn">
				                                        <html:option value="" ><bean:message key="screen.b_bac.blankItem"/></html:option>
				                                        <html:optionsCollection property="listContact" name="_b_bacForm" label="label" value="value"/>
				                                    </html:select>
				                                </c:if>
				                                <c:if test="${_b_bacForm.map.custInfo.CUSTOMER_TYPE eq 'CONS'}">
				                                    <html:select property="inputHeaderInfo.contactType" name="_b_bacForm" disabled="true">
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
				                                    <c:when test="${empty _b_bacForm.map.addressInfo.email}">
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
							<td nowrap="nowrap"><bean:message key="screen.b_bac.paymentMethod"/></td>
							<td>
								<bean:message key="screen.b_bac.colon"/>
								<t:defineCodeList id="LIST_PAYMENT_METHOD" />
								<html:hidden property="isDisplayGiro" name="_b_bacForm"/>
								<html:hidden property="isDisplayCC" name="_b_bacForm"/>
								<html:select property="inputHeaderInfo.paymentMethod" name="_b_bacForm"
									value="${_b_bacForm.map.inputHeaderInfo.paymentMethod}" styleId="cboPaymentMethod">
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
									<!--<html:options collection="LIST_PAYMENT_METHOD" property="id" labelProperty="name"/>-->
								</html:select>
							</td>
						</tr>
						<tr>
							<td><bean:message key="screen.b_bac.billingCurrency"/></td>
							<td>
								<bean:message key="screen.b_bac.colon"/>&nbsp;
								<t:defineCodeList id="LIST_CURRENCY" />
								<c:forEach items="${LIST_CURRENCY}" var="item">
									<c:if test="${_b_bacForm.map.inputHeaderInfo.billCurrency eq item.id}">
										${item.name}
									</c:if>
								</c:forEach>
								<html:hidden property="inputHeaderInfo.billCurrency" value="${_b_bacForm.map.inputHeaderInfo.billCurrency}"/>
							</td>
						</tr>
						<tr>
							<td><bean:message key="screen.b_bac.exportCurrency"/></td>
							<td>
								<bean:message key="screen.b_bac.colon"/>&nbsp;
								${_b_bacForm.map.inputHeaderInfo.exportCurrency}
								<html:hidden property="inputHeaderInfo.exportCurrency" value="${_b_bacForm.map.inputHeaderInfo.exportCurrency}"/>
							</td>
						</tr>
                        <c:if test="${_b_bacForm.map.fixedForex eq '1'}">
						<tr>
							<td><bean:message key="screen.b_bac.fixedForex"/></td>
							<td>
								<bean:message key="screen.b_bac.colon"/>&nbsp;
								${_b_bacForm.map.inputHeaderInfo.fixedForex}
								<html:hidden property="inputHeaderInfo.fixedForex" value="${_b_bacForm.map.inputHeaderInfo.fixedForex}"/>
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
                        <c:if test="${_b_bacForm.map.taxTypeDisplay eq '1'}">
                        <tr>
                        	<td><bean:message key="screen.b_bac.taxType"/></td>
                            <td>
                                <bean:message key="screen.b_bac.colon"/>&nbsp;
                                <t:defineCodeList id="LIST_TAX_TYPE" />
                                <c:forEach items="${LIST_TAX_TYPE}" var="item">
                                    <c:if test="${_b_bacForm.map.inputHeaderInfo.taxType eq item.id}">
                                        ${item.name}
                                        <html:hidden property="inputHeaderInfo.taxType" value="${_b_bacForm.map.inputHeaderInfo.taxType}"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        </c:if>
                        <c:if test="${_b_bacForm.map.taxTypeDisplay ne '1'}">
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
					</table>
				</fieldset>
			</div>
		</div>
		<br/>
		<div class="section">
		<table class="resultInfo" cellpadding="0" cellspacing="0" width="100%">
            <col width="8%"/>
            <col width="30%"/>
            <col width="12%"/>
            <col width="10%"/>
            <col width="10%"/>
            <col width="10%"/>
            <col width="10%"/>
            <col width="15%"/>
            <tr>
                <td class="header" style="background-color:#ffccff"><bean:message key="screen.b_bac.subId"/></td>
                <td class="header" style="background-color:#ffccff"><bean:message key="screen.b_bac.planDescription"/></td>
                <td class="header" style="background-color:#ffccff"><bean:message key="screen.b_bac_s02.amount"/></td>
                <td class="header" style="background-color:#ffccff"><bean:message key="screen.b_bac_s02.serviceStart"/></td>
                <td class="header" style="background-color:#ffccff"><bean:message key="screen.b_bac.serviceEnd"/></td>
                <td class="header" style="background-color:#ffccff"><bean:message key="screen.b_bac.serviceStatus"/></td>
                <td class="header" style="background-color:#ffccff"><bean:message key="screen.b_bac.billingStatus"/></td>
                <td class="header" style="background-color:#ffccff"><bean:message key="screen.b_bac.lastBillingPeriod"/></td>
            </tr>
			<c:forEach items="${_b_bacForm.map.listPlanInfo}" var="item" varStatus="status">
			    <tr>
			    <bean:define id="borderClass" value="${empty item.ID_SUB_INFO?'textLineNo': 'firstTrTopBorder'}"></bean:define>
                <td class="<%=borderClass %>" align="left">
                    <logic:notEmpty name="item" property="ID_SUB_INFO">
                        <input type="checkbox" value="${item.ID_CUST_PLAN}_${item.ID_BILL_ACCOUNT}" checked="checked" class="hidden" name="idKeys"/>
                        <a href="#" onclick="openB_CPM_View('${pageContext.request.contextPath}/B_CPM/B_CPM_S05InitBL.do?customerPlan.idCustPlan=${item.ID_CUST_PLAN}&customerPlan.fromScreen=Billing&customerPlan.billType=BAC')">
                            ${item.ID_SUB_INFO}
                        </a>
                    </logic:notEmpty>
                </td>
                <td class="<%=borderClass %>" align="left" style="word-wrap: break-word;white-space : normal">
                    ${fn:substring(item.BILL_DESC_DISPLAY, 0, _b_bacForm.map.bss.descLength)}
                    <c:if test="${fn:length(item.BILL_DESC_DISPLAY) gt _b_bacForm.map.bss.descLength}">
                        <bean:message key="screen.b_bac.etc"/>
                    </c:if>
                </td>
                <td class="<%=borderClass %>" align="left" style="padding-right:5px">
                    <fmt:formatNumber value="${item.TOTAL_AMOUNT}" pattern="#,##0.00"></fmt:formatNumber>
                </td>
                <td class="<%=borderClass %>" align="left" style="padding-right:5px">
                    <fmt:formatDate value="${item.SVC_START}" pattern="dd/MM/yyyy"/>
                </td>
                <td class="<%=borderClass %>" align="left" style="padding-right:5px">
                   <c:choose>
                        <c:when test="${(empty item.SVC_END) and (item.AUTO_RENEW eq 0)}">
                            <bean:message key="screen.b_bac._"/>
                        </c:when>
                        <c:when test="${(empty item.SVC_END) and (item.AUTO_RENEW eq 1)}">
                            <bean:message key="screen.b_bac.autoRenew"/>
                        </c:when>
                        <c:otherwise>
                            <fmt:formatDate value="${item.SVC_END}" pattern="dd/MM/yyyy"/>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td class="<%=borderClass %>" align="left">
                      <c:choose>
                          <c:when test="${item.SERVICES_STATUS eq 'PS1'}">
                              <bean:message key="screen.b_bac.draft"/>
                          </c:when>
                          <c:when test="${item.SERVICES_STATUS eq 'PS2'}">
                              <bean:message key="screen.b_bac.approval"/>
                          </c:when>
                          <c:when test="${item.SERVICES_STATUS eq 'PS3'}">
                              <bean:message key="screen.b_bac.active"/>
                          </c:when>
                          <c:when test="${item.SERVICES_STATUS eq 'PS4'}">
                              <bean:message key="screen.b_bac.inactive"/>
                          </c:when>
                          <c:when test="${item.SERVICES_STATUS eq 'PS5'}">
                              <bean:message key="screen.b_bac.oneTime"/>
                          </c:when>
                          <c:when test="${item.SERVICES_STATUS eq 'PS6'}">
                              <bean:message key="screen.b_bac.suspended"/>
                          </c:when>
                          <c:when test="${item.SERVICES_STATUS eq 'PS7'}">
                              <bean:message key="screen.b_bac.terminated"/>
                          </c:when>
                          <c:when test="${item.SERVICES_STATUS eq 'PS8'}">
                              <bean:message key="screen.b_bac.rejected"/>
                          </c:when>
                          <c:otherwise>
                          </c:otherwise>
                      </c:choose>
                  </td>
                  <td class="<%=borderClass %>" align="left">
                      <c:choose>
                          <c:when test="${item.BILLING_STATUS eq 'BS0'}">
                              <bean:message key="screen.b_bac.billing_status.BS0"/>
                          </c:when>
                          <c:when test="${item.BILLING_STATUS eq 'BS1'}">
                              <bean:message key="screen.b_bac.billing_status.BS1"/>
                          </c:when>
                          <c:when test="${item.BILLING_STATUS eq 'BS2'}">
                              <bean:message key="screen.b_bac.billing_status.BS2"/>
                          </c:when>
                          <c:when test="${item.BILLING_STATUS eq 'BS3'}">
                              <bean:message key="screen.b_bac.billing_status.BS3"/>
                          </c:when>
                      </c:choose>
                  </td>
                  <td class="<%=borderClass %>" align="left" nowrap="nowrap">
                      <fmt:formatDate value="${item.BILL_FROM}" pattern="dd/MM/yyyy"/> - <fmt:formatDate value="${item.BILL_TO}" pattern="dd/MM/yyyy"/>
                  </td>
                </tr>
			</c:forEach>			
		</table>
		</div>
		<br/>
		<ts:submit value="Save" styleClass="button" property="forward_save"></ts:submit>
        <input type="hidden" id="hiddenMsgExitConfirmation" value="<bean:message key="info.ERR4SC001"/>"/>
		 <input type="button"  value='Exit' class="button" onclick="ClickExit()"/>
<%-- 		<bs:buttonLink  action="/RP_B_BAC_S04_01BL" value="Exit"/> --%>
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
