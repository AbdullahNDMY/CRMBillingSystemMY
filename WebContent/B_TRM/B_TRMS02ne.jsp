<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>

<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
        <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
        <link rel="stylesheet" type="text/css" href="css/b_trms02.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/B_TRM/js/b_trms02.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
        <title>Insert title here</title>
    </head>
    <ts:body>
        <div id="bodywidth">
        <input type="hidden" id="contextPath" value="<%=request.getContextPath()%>">
        <input type="hidden" id="hiddenMsgExitConfirmation" value="<bean:message key="info.ERR4SC001"/>"/>
        <table class="subHeader">
            <tr>
                <td><bean:message key="screen.b_trm.title"/></td>
              </tr>
        </table> 
        <ts:form action="/B_TRMS02AD" method="POST">
            <input type="hidden" name="actionType" value="${b_trmForm.action}" id="actionType"/>
            <html:hidden name="b_trmForm" property="gblSetValue"/>
            <table class="information tdnowap" cellpadding="0" cellspacing="0" >
                <colgroup>
                <col width="20%">
                <col width="35%">
                <col width="15%">
                <col width="30%">
                </colgroup>
                <tr>
                    <td colspan="4" class="header">
                        <bean:message key="screen.b_trm.creditInfo"/>
                    </td>
                </tr>
                <tr>
                <td colspan="4" style="height:5px"></td>
                </tr>
                <tr>
                    <td>
                       <bean:message key="screen.b_trm.customerName"/><span class="mandatory" ><bean:message key="screen.b_trm.star"/></span>
                    </td>
                    <td style="word-wrap:break-word;">
                      <bean:message key="screen.b_trm.colon"/>&nbsp;
                      <logic:equal value="edit" name="b_trmForm" property="action">
                            <logic:iterate id="p" name="b_trmForm" property="cbCustomer">
                                <c:if test="${b_trmForm.customer == p.value}">
                                    ${p.label}
                                    <html:hidden property="cboCustomerName" name="b_trmForm" value="${p.label}"/>
                                </c:if>
                            </logic:iterate>
                     </logic:equal>
                     <logic:notEqual value="edit" name="b_trmForm" property="action">
                        <button id="btnGetCustomerInfo" style="width:25px;margin-left: 3px" onclick="getCustomerInfo('<%=request.getContextPath()%>')"><img src="<%=request.getContextPath()%>/image/search.png"></button>
                        <span id="txtCustomerNameDisplay">
	                        <logic:notEmpty name="b_trmForm" property="cboCustomerName">
	                           <bean:write name="b_trmForm" property="cboCustomerName"/>
	                        </logic:notEmpty>
                        </span>
                        <html:hidden name="b_trmForm" property="cboCustomerName" styleId="cboCustomerName"/>
                     </logic:notEqual>
                     </td>
                     <td>
                        <bean:message key="screen.b_trm.customerID" />
                     </td>
                     <td>
                        <bean:message key="screen.b_trm.colon"/>&nbsp;<span id="txtCustomerIdDisplay">
                            <logic:notEmpty name="b_trmForm" property="customer">
                               <bean:write name="b_trmForm" property="customer"/>
                            </logic:notEmpty></span>
                        <html:hidden name="b_trmForm" property="customer" styleId="customer"/>
                     </td>
                  </tr>
                  <logic:notEqual value="CST" name="b_trmForm" property="gblSetValue">
                  <tr>
                      <td>
                          <bean:message key="screen.b_trm.billAccNo"/><span class="mandatory" ><bean:message key="screen.b_trm.star"/></span>
                      </td>
                      <td>
                          <bean:message key="screen.b_trm.colon"/>&nbsp;
                          <logic:equal value="edit" name="b_trmForm" property="action">
                              <logic:iterate id="p" name="b_trmForm" property="cbBillAccount">
                                <c:if test="${b_trmForm.billAccount == p.value}">
                                    ${p.label}
                                </c:if>
                              </logic:iterate>
                              <html:hidden name="b_trmForm" property="billAccount"/>
                          </logic:equal>
                          <nested:notEqual value="edit" name="b_trmForm" property="action">
                              <nested:select name="b_trmForm" property="billAccount" styleClass="InputComboBox" onchange="loadBillAcc()" styleId="tbxBillingAccount">
                                  <html:option value="" ><bean:message key="screen.b_trm.blankItem"/></html:option>
                                  <nested:present property="cbBillAccount" name="b_trmForm">
                                      <nested:optionsCollection property="cbBillAccount" name="b_trmForm" label="label" value="value"/>
                                  </nested:present>
                               </nested:select>
                          </nested:notEqual>
                      </td>
                      <td>
                          <bean:message key="screen.b_trm.currency"/>
                      </td>
                      <td>
                          <bean:message key="screen.b_trm.colon"/>&nbsp;
                          <bean:write name="b_trmForm" property="currency"/>
                          <html:hidden name="b_trmForm" property="currency"/>
                      </td>
                  </tr>
                  </logic:notEqual>
                  <tr>
                    <td><bean:message key="screen.b_trm.transactionDate" /><span class="mandatory" ><bean:message key="screen.b_trm.star"/></span></td>
                    <td><bean:message key="screen.b_trm.colon"/>&nbsp;
                    <%--<logic:equal value="edit" name="b_trmForm" property="action">
                        <bean:write property="transationDate" name="b_trmForm" />
                        <html:hidden name="b_trmForm" property="transationDate"/>
                    </logic:equal> 
                    <logic:notEqual value="edit" name="b_trmForm" property="action">
                    <html:text property="transationDate" name="b_trmForm" readonly="true" style="width:140px;"/>
                                    <t:inputCalendar for="transationDate" format="yyyy/MM/dd"/>
                    </logic:notEqual>--%>
                    <html:text property="transationDate" name="b_trmForm" readonly="true" style="width:140px;"/>
                                    <t:inputCalendar for="transationDate" format="dd/MM/yyyy"/>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="4" style="height:10px"></td>
                  </tr>
                  <tr>
                  <td colspan="4" width="100%">
                      <fieldset style="width: 100%;">
                          <table class="information tdnowap" cellpadding="0" cellspacing="0">
                          <colgroup>
		                  <col width="20%">
		                  <col width="35%">
		                  <col width="15%">
		                  <col width="30%">
		                  </colgroup>
                          <tr>
                              <td><bean:message key="screen.b_trm.creditRef"/><span class="mandatory" ><bean:message key="screen.b_trm.star"/></span></td>
                              <td>
                                  <bean:message key="screen.b_trm.colon"/>&nbsp;
                                  <logic:equal value="edit" name="b_trmForm" property="action">
                                      <!-- 
                                      <logic:iterate id="p" name="b_trmForm" property="cbCreditRef">
                                        <c:if test="${b_trmForm.creditRef == p.value}">
                                            ${p.label}
                                        </c:if>
                                      </logic:iterate>
                                      -->
                                    <bean:write name="b_trmForm" property="creditRef"/>
                                    <html:hidden name="b_trmForm" property="creditRef"/>
                                  </logic:equal>
                                  <nested:notEqual value="edit" name="b_trmForm" property="action">
                                      <nested:select name="b_trmForm" onchange="loadCreditRef();" property="creditRef" styleClass="InputComboBox">
                                          <html:option value="" ><bean:message key="screen.b_trm.blankItem"/></html:option>
                                          <nested:present property="cbCreditRef" name="b_trmForm">
                                              <nested:optionsCollection property="cbCreditRef" name="b_trmForm" label="label" value="value"/>
                                          </nested:present>
                                      </nested:select>
                                  </nested:notEqual>
                               </td>
                         </tr>
                         <tr>
                              <td>
		                          <bean:message key="screen.b_trm.date"/>
		                      </td>
		                      <td>
		                          <bean:message key="screen.b_trm.colon"/>&nbsp;
		                          <bean:write name="b_trmForm" property="date"/>
		                          <html:hidden name="b_trmForm" property="date"/>
		                      </td>
		                      <td>
		                          <bean:message key="screen.b_trm.creditBal"/>
		                      </td>
		                      <td>
		                          <bean:message key="screen.b_trm.colon"/>&nbsp;
		                          <span id="creditBalance">
		                              <logic:notEmpty name="b_trmForm" property="creditBalance">
                                      <bean:define id="cbValue" name="b_trmForm" property="creditBalance"></bean:define>
                                      <fmt:formatNumber value="<%=(new java.math.BigDecimal(cbValue.toString()).negate()) %>" pattern="#,##0.00"/>
		                              </logic:notEmpty>
		                          </span>
		                          <html:hidden name="b_trmForm" property="creditBalance" styleId="creditBalanceHidden"/>
		                      </td>
                         </tr>
                         <tr>
		                      <td>
		                          <bean:message key="screen.b_trm.oriAmt"/>
		                      </td>
		                      <td>
		                          <bean:message key="screen.b_trm.colon"/>&nbsp;
		                          <logic:notEmpty name="b_trmForm" property="origAmt">
	                              <bean:define id="orValue" name="b_trmForm" property="origAmt"></bean:define>
                                  <fmt:formatNumber value="<%=(new java.math.BigDecimal(orValue.toString()).negate()) %>" pattern="#,##0.00"/>
		                          </logic:notEmpty>
		                          <html:hidden name="b_trmForm" property="origAmt" styleId="origAmt"/>
		                      </td>
		                  </tr>    
                          </table>
                      </fieldset>
                  </td>
                  </tr>     
            </table>
            <table class="subHeaderInfo" >
                  <tr>
                <td><bean:message key="screen.b_trm.debitInfo"/></td>
                  </tr>
            </table>
            <table  class="debitInfo">
                <tr>
                    <td class="header" width="13%">
                        <bean:message key="screen.b_trm.appliedTo"/>
                    </td>
                    <td class="header" width="13%">
                        <bean:message key="screen.b_trm.date"/>
                    </td>
                    <td class="header" width="19%">
                        <bean:message key="screen.b_trm.debitRef"/>
                    </td>
                    <td class="header" width="10%">
                        <bean:message key="screen.b_trm.currency"/>
                    </td>
                    <td class="header"width="15%">
                        <bean:message key="screen.b_trm.orgAmount"/>
                    </td>
                    <td class="header"width="15%">
                        <bean:message key="screen.b_trm.amtDue"/>
                    </td>
                    <td class="header"width="15%">
                        <bean:message key="screen.b_trm.payment"/>
                    </td>
                </tr>
                <nested:present property="debitInfos" name="b_trmForm">
                    <nested:iterate id="debitInfo" property="debitInfos" indexId="idx" >
                        <tr>            
                            <td class="colCenter">
                                <c:choose>
                                    <c:when test="${debitInfo.chkAppliedTo=='1'}">
                                        <input type="checkbox" checked="checked" onclick="changePaymentStatus(this,${idx});"/>
                                        <nested:hidden property="chkAppliedTo"  value="1"/>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="checkbox" onclick="changePaymentStatus(this,${idx});"/>
                                        <nested:hidden property="chkAppliedTo" value="0"/>
                                    </c:otherwise>
                                </c:choose>
                                <nested:hidden property="matchID"/>
                            </td>        
                            <td class="colCenter">
                                <nested:write property="gdcDateDebitRef"/>
                                <nested:hidden property="gdcDateDebitRef"/>
                            </td>
                            <td class="colCenter">
                                <nested:write property="gdcDebitReference"/>
                                <nested:hidden property="gdcDebitReference"/>
                            </td>
                            <td class="colCenter">
                                <nested:write property="gdcCurrency"/>
                                <nested:hidden property="gdcCurrency"/>
                            </td>
                            <td class="colRight">
                                <fmt:formatNumber value="${debitInfo.gdcOriginalAmountCR}" pattern="#,##0.00"/>
                                <nested:hidden property="gdcOriginalAmountCR"></nested:hidden>
                            </td>
                            <td class="colRight">
                                <fmt:formatNumber value="${debitInfo.gdcAmountDue}" pattern="#,##0.00"/>
                                <nested:hidden property="gdcAmountDue"></nested:hidden>
                            </td>
                            <td class="colRight">
                                <c:choose>
                                    <c:when test="${empty debitInfo.tbxPaymentPrev}">
                                        <nested:hidden value="${debitInfo.tbxPayment}" property="tbxPaymentPrev"/>
                                    </c:when>
                                    <c:otherwise>
                                        <nested:hidden property="tbxPaymentPrev"/>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${debitInfo.chkAppliedTo=='1'}">
                                        <nested:text property="tbxPayment" style="text-align:right" onkeypress="return onlyDecNumbers(event)" onchange="calculatedCreditBalance();"/>
                                    </c:when>
                                    <c:otherwise>
                                        <nested:text disabled="true" property="tbxPayment" style="text-align:right" onkeypress="return onlyDecNumbers(event)" onchange="calculatedCreditBalance();"/>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </nested:iterate>
                    <input type="hidden" id="numDbInfo" value="${idx}"/>
                </nested:present>
            </table>
            <table class="buttonGroup" cellpadding="0" cellspacing="0">
                  <tr>
                    <td>
                        <input type="button" class="button" value="<bean:message key="screen.b_trm.save"/>" onclick="confirm_save();" />
                        <input type="button" class="button" value="<bean:message key="screen.b_trm.exit"/>" onclick="confirm_exit(); " />
                    </td>
                </tr>
            </table>
        </ts:form>
        <div class="error">
            <html:messages id="message">
                <bean:write name="message"/><br/>
            </html:messages>
        </div>
        <div class="message">
            <ts:messages id="messages" message="true">
                <bean:write name="messages"/><br/>
            </ts:messages>
         </div>
         </div>
    </ts:body>
</html:html>
