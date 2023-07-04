<%@page import="java.math.BigDecimal"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/bs" prefix="bs" %>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
    <link href="${pageContext.request.contextPath}/R_ACR/css/r_acr.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
    <title></title>
</head>
<body id="r">
<ts:form action="/R_BAC_S02DSP" >
    <logic:equal name="_r_bacForm" property="type" value="1">
	    <h1 class="title">Billing Account - Account Ledger Checking: 1 Receipts No Details</h1>
	    <div class="resultTableTd">
	    <table class="resultInfo" cellpadding="0" cellspacing="0" width="100%">
	      <tr>
	        <td class="header" style="padding-left:5px;vertical-align:top;" width="10%">ID_BILL_ACCOUNT</td>
	        <td class="header" style="padding-right:5px;vertical-align:top;" width="10%">ID_CUST</td>
	        <td class="header" style="padding-right:5px;vertical-align:top;" width="10%">RECEIPT_NO</td>
	        <td class="header" style="padding-right:5px;vertical-align:top;" width="10%">DATE_TRANS</td>
	        <td class="header" style="padding-right:5px;vertical-align:top;" width="10%">AMT_RECEIVED</td>
	        <td class="header" style="padding-right:5px;vertical-align:top;" width="10%">BALANCE_AMT</td>
	        <td class="header" style="padding-right:5px;vertical-align:top;" width="10%">REFERENCE1</td>
	        <td class="header" style="padding-right:5px;vertical-align:top;" width="10%">REFERENCE2</td> 
	        <td class="header" style="padding-right:5px;vertical-align:top;" width="10%">PMT_STATUS</td> 
	        <td class="header" style="padding-right:5px;vertical-align:top;" width="10%">PMT_METHOD</td> 
	      </tr>
	      <%
	          BigDecimal TotalBalance = BigDecimal.ZERO;
	      %>
	      <c:forEach items="${_r_bacForm.map.resultList}" var="item" varStatus="status" >
	          <bean:define id="balanceAmt" value="${item.BALANCE_AMT}"/>
	          <%
                 TotalBalance = TotalBalance.add(new BigDecimal(balanceAmt)); 
              %>
	          <tr>
	            <td class="defaultText" style="padding-left:5px">
	                ${item.ID_BILL_ACCOUNT}
	            </td>
	            <td class="defaultText" style="padding-right:5px">
	                ${item.ID_CUST}
	            </td>
	            <td class="defaultText" style="padding-right:5px" >
	                ${item.RECEIPT_NO}
	            </td>
	            <td class="defaultText" style="padding-right:5px" >
	                ${item.DATE_TRANS}
	            </td>
	            <td class="defaultText" style="padding-right:5px" >
	                ${item.AMT_RECEIVED}
	            </td>
	            <td class="defaultText" style="padding-right:5px" >
	                ${item.BALANCE_AMT}
	            </td>
	            <td class="defaultText" style="padding-right:5px" >
	               ${item.REFERENCE1}
	            </td>
	            <td class="defaultText" style="padding-right:5px; text-align:left;" > 
	                ${item.REFERENCE2}
	            </td>
	            <td class="defaultText" style="padding-right:5px; text-align:left;" > 
	                ${item.PMT_STATUS}
	            </td>
	            <td class="defaultText" style="padding-right:5px; text-align:left;" > 
	                ${item.PMT_METHOD}
	            </td>
	          </tr>
	       </c:forEach>
		       <tr>
			       <td  colspan="5" align="right" style="font-weight:bold;padding-right:10px">
			          Total Balance :
			       </td>
			       <td  colspan="4" align="left" style="font-weight:bold;">
	                  <%=TotalBalance %>
	               </td>
		       </tr>
	    </table>
	    </div>
    </logic:equal>
    
    <logic:equal name="_r_bacForm" property="type" value="2">
        <h1 class="title">Billing Account - Account Ledger Checking: 2 Receipt Over Matched</h1>
        <div class="resultTableTd">
        <table class="resultInfo" cellpadding="0" cellspacing="0" width="100%">
          <tr>
            <td class="header" style="padding-left:5px;vertical-align:top;" width="10%">ID_BILL_ACCOUNT</td>
            <td class="header" style="padding-right:5px;vertical-align:top;" width="10%">RECEIPT_NO</td>
            <td class="header" style="padding-right:5px;vertical-align:top;" width="10%">AMT_RECEIVED</td>
            <td class="header" style="padding-right:5px;vertical-align:top;" width="10%">AMT_PAID</td>
          </tr>
          <c:forEach items="${_r_bacForm.map.resultList}" var="item" varStatus="status" >
              <tr>
                <td class="defaultText" style="padding-left:5px">
                    ${item.ID_BILL_ACCOUNT}
                </td>
                <td class="defaultText" style="padding-right:5px">
                    ${item.RECEIPT_NO}
                </td>
                <td class="defaultText" style="padding-right:5px" >
                    ${item.AMT_RECEIVED}
                </td>
                <td class="defaultText" style="padding-right:5px" >
                    ${item.AMT_PAID}
                </td>
              </tr>
           </c:forEach>
        </table>
        </div>
    </logic:equal>
    
    <logic:equal name="_r_bacForm" property="type" value="3">
        <h1 class="title">Billing Account - Account Ledger Checking: 3 Receipt Not Fully Match</h1>
        <div class="resultTableTd">
        <table class="resultInfo" cellpadding="0" cellspacing="0" width="100%">
          <tr>
            <td class="header" style="padding-left:5px;vertical-align:top;" width="10%">ID_BILL_ACCOUNT</td>
            <td class="header" style="padding-right:5px;vertical-align:top;" width="10%">RECEIPT_NO</td>
            <td class="header" style="padding-right:5px;vertical-align:top;" width="10%">AMT_RECEIVED</td>
            <td class="header" style="padding-right:5px;vertical-align:top;" width="10%">BALANCE_AMT</td>
            <td class="header" style="padding-right:5px;vertical-align:top;" width="10%">AMT_PAID</td>
          </tr>
          <%
             BigDecimal balance3 = BigDecimal.ZERO;
          %>
          <c:forEach items="${_r_bacForm.map.resultList}" var="item" varStatus="status" >
              <bean:define id="balance" value="${item.BALANCE_AMT}" />
              <%
                 balance3 = balance3.add(new BigDecimal(balance));
              %>
              <tr>
                <td class="defaultText" style="padding-left:5px">
                    ${item.ID_BILL_ACCOUNT}
                </td>
                <td class="defaultText" style="padding-right:5px">
                    ${item.RECEIPT_NO}
                </td>
                <td class="defaultText" style="padding-right:5px" >
                    ${item.AMT_RECEIVED}
                </td>
                <td class="defaultText" style="padding-right:5px" >
                    ${item.BALANCE_AMT}
                </td>
                <td class="defaultText" style="padding-right:5px" >
                    ${item.AMT_PAID}
                </td>
              </tr>
           </c:forEach>
              <tr>
                   <td  colspan="3" align="right" style="font-weight:bold;padding-right:10px">
                      Total Balance :
                   </td>
                   <td  colspan="2" align="left" style="font-weight:bold;">
                      <%=balance3 %>
                   </td>
              </tr>
        </table>
        </div>
    </logic:equal>
    
    <logic:equal name="_r_bacForm" property="type" value="4">
        <h1 class="title">Billing Account - Account Ledger Checking: 4 Invoice Over Match</h1>
        <div class="resultTableTd">
        <table class="resultInfo" cellpadding="0" cellspacing="0" width="100%">
          <tr>
            <td class="header" style="padding-left:5px;vertical-align:top;" width="10%">BILL_ACC</td>
            <td class="header" style="padding-right:5px;vertical-align:top;" width="10%">ID_REF</td>
            <td class="header" style="padding-right:5px;vertical-align:top;" width="10%">DATE_REQ</td>
            <td class="header" style="padding-right:5px;vertical-align:top;" width="10%">BILL_AMOUNT</td>
            <td class="header" style="padding-right:5px;vertical-align:top;" width="10%">PAID_AMOUNT</td>
            <td class="header" style="padding-right:5px;vertical-align:top;" width="10%">IS_SETTLED</td>
            <td class="header" style="padding-right:5px;vertical-align:top;" width="10%">AMT_PAID</td>
          </tr>
          <c:forEach items="${_r_bacForm.map.resultList}" var="item" varStatus="status" >
              <tr>
                <td class="defaultText" style="padding-left:5px">
                    ${item.BILL_ACC}
                </td>
                <td class="defaultText" style="padding-right:5px">
                    ${item.ID_REF}
                </td>
                <td class="defaultText" style="padding-right:5px">
                    ${item.DATE_REQ}
                </td>
                <td class="defaultText" style="padding-right:5px">
                    ${item.BILL_AMOUNT}
                </td>
                <td class="defaultText" style="padding-right:5px" >
                    ${item.PAID_AMOUNT}
                </td>
                <td class="defaultText" style="padding-right:5px" >
                    ${item.IS_SETTLED}
                </td>
                <td class="defaultText" style="padding-right:5px" >
                    ${item.AMT_PAID}
                </td>
              </tr>
           </c:forEach>
        </table>
        </div>
    </logic:equal>
    
    <logic:equal name="_r_bacForm" property="type" value="5">
        <h1 class="title">Billing Account - Account Ledger Checking: 5 Invoice Not Fully Match</h1>
        <div class="resultTableTd">
        <table class="resultInfo" cellpadding="0" cellspacing="0" width="100%">
          <tr>
            <td class="header" style="padding-left:5px;vertical-align:top;" width="10%">BILL_ACC</td>
            <td class="header" style="padding-right:5px;vertical-align:top;" width="10%">ID_REF</td>
            <td class="header" style="padding-right:5px;vertical-align:top;" width="10%">DATE_REQ</td>
            <td class="header" style="padding-right:5px;vertical-align:top;" width="10%">BILL_AMOUNT</td>
            <td class="header" style="padding-right:5px;vertical-align:top;" width="10%">PAID_AMOUNT</td>
            <td class="header" style="padding-right:5px;vertical-align:top;" width="10%">IS_SETTLED</td>
            <td class="header" style="padding-right:5px;vertical-align:top;" width="10%">AMT_PAID</td>
          </tr>
          <%
              BigDecimal outstanding5 = BigDecimal.ZERO;
          %>
          <c:forEach items="${_r_bacForm.map.resultList}" var="item" varStatus="status" >
              <bean:define id="billAmount" value="${item.BILL_AMOUNT}"/>
              <bean:define id="paidAmount" value="${item.PAID_AMOUNT}"/>
              <%
                 BigDecimal billamt = new BigDecimal(billAmount);
                 BigDecimal paidamt = new BigDecimal(paidAmount);
                 outstanding5 = outstanding5.add(billamt.subtract(paidamt));
              %>
              <tr>
                <td class="defaultText" style="padding-left:5px">
                    ${item.BILL_ACC}
                </td>
                <td class="defaultText" style="padding-right:5px">
                    ${item.ID_REF}
                </td>
                <td class="defaultText" style="padding-right:5px">
                    ${item.DATE_REQ}
                </td>
                <td class="defaultText" style="padding-right:5px">
                    ${item.BILL_AMOUNT}
                </td>
                <td class="defaultText" style="padding-right:5px" >
                    ${item.PAID_AMOUNT}
                </td>
                <td class="defaultText" style="padding-right:5px" >
                    ${item.IS_SETTLED}
                </td>
                <td class="defaultText" style="padding-right:5px" >
                    ${item.AMT_PAID}
                </td>
              </tr>
           </c:forEach>
           <tr>
               <td  colspan="4" align="right" style="font-weight:bold;padding-right:10px">
                  Total Outstanding :
               </td>
               <td  colspan="3" align="left" style="font-weight:bold;">
                  <%=outstanding5 %>
               </td>
           </tr>
        </table>
        </div>
    </logic:equal>
    
    <logic:equal name="_r_bacForm" property="type" value="6">
        <h1 class="title">Billing Account - Account Ledger Checking: 6 Receipt Amount Negative</h1>
        <div class="resultTableTd">
        <table class="resultInfo" cellpadding="0" cellspacing="0" width="100%">
          <tr>
            <td class="header" style="padding-left:5px;vertical-align:top;" width="10%">ID_BILL_ACCOUNT</td>
            <td class="header" style="padding-right:5px;vertical-align:top;" width="10%">RECEIPT_NO</td>
            <td class="header" style="padding-right:5px;vertical-align:top;" width="10%">DATE_TRANS</td>
            <td class="header" style="padding-right:5px;vertical-align:top;" width="10%">AMT_RECEIVED</td>
            <td class="header" style="padding-right:5px;vertical-align:top;" width="10%">BALANCE_AMT</td>
            <td class="header" style="padding-right:5px;vertical-align:top;" width="10%">REFERENCE1</td>
            <td class="header" style="padding-right:5px;vertical-align:top;" width="10%">PMT_STATUS</td>
          </tr>
          <c:forEach items="${_r_bacForm.map.resultList}" var="item" varStatus="status" >
              <tr>
                <td class="defaultText" style="padding-left:5px">
                    ${item.ID_BILL_ACCOUNT}
                </td>
                <td class="defaultText" style="padding-right:5px" >
                    ${item.RECEIPT_NO}
                </td>
                <td class="defaultText" style="padding-right:5px" >
                    ${item.DATE_TRANS}
                </td>
                <td class="defaultText" style="padding-right:5px" >
                    ${item.AMT_RECEIVED}
                </td>
                <td class="defaultText" style="padding-right:5px" >
                    ${item.BALANCE_AMT}
                </td>
                <td class="defaultText" style="padding-right:5px" >
                   ${item.REFERENCE1}
                </td>
                <td class="defaultText" style="padding-right:5px; text-align:left;" > 
                    ${item.PMT_STATUS}
                </td>
              </tr>
           </c:forEach>
        </table>
        </div>
    </logic:equal>
    <br/>
    <input type="button" value='Exit' onclick="window.close();" class="button" />
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