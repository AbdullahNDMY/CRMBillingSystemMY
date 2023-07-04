<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/bs" prefix="bs" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
       <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
    <link href="<%=request.getContextPath()%>/B_TRM/css/b_trms01.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/B_TRM/js/b_trms01.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
    <title>Insert title here</title>
    <script>
        function clickCreditRefLink(creditRef) {
            document.forms[0].action = "<%=request.getContextPath()%>/B_TRM/B_TRMS02V.do?action=view&creditRef="+creditRef;
            document.forms[0].submit();
        }
        function new_data() {
            document.forms[0].action = "<%=request.getContextPath()%>/B_TRM/B_TRMS02SCR.do";
            document.forms[0].submit();
        }
        function reset_data(){
            document.getElementById("cusName").value = "";
            document.getElementById("creditReference").value = "";
            document.getElementById("debitReference").value = "";
            document.getElementById("startDate").value = "";
            document.getElementById("endDate").value = "";
        }
        function reloadModuleName() {
            isLoaded = true;
            if (window.parent && window.parent.frame_top) {
                if (parent.frame_top.isLoaded) parent.frame_top.initPage();
            }
        }
    </script>
</head>
<body id="b" onload="reloadModuleName();">
<ts:form action="/B_TRMS01BLDSP" >
<t:defineCodeList id="LIST_CURRENCY"/>
<t:defineCodeList id="LIST_CUSTOMERTYPE"/>
<div class="pageWidth">
    <h1 class="title"><bean:message key="screen.b_trm.title"/></h1>
    <div class="section" id="section">
        <table cellpadding="0" cellspacing="0" width="100%">
            <colgroup>
	            <col width="10%">
	            <col width="1%">
	            <col width="22%">
	            <col width="11%">
	            <col width="1%">
	            <col width="15%">
	            <col width="40%">
            </colgroup>
            <tr>
                <td>
                    <bean:message key="screen.b_trm.customerID"/>
                </td>
                <td>
                    <bean:message key="screen.b_trm.colon"/>
                </td>
                <td>
                    <html:text name="b_trmForm" property="cusID" maxlength="100" styleClass="textwith"></html:text>
                </td>
                <td>
                    <bean:message key="screen.b_trm.transactionDate"/>
                </td>
                <td>
                    <bean:message key="screen.b_trm.colon"/>
                </td>
                <td>
                    <html:text property="startDate" name="b_trmForm" readonly="true" style="width:70px;"/>
                                    <t:inputCalendar for="startDate" format="dd/MM/yyyy"/>&nbsp; <bean:message key="screen.b_trm._"/>&nbsp;
                    <html:text property="endDate" name="b_trmForm" readonly="true" style="width:70px;"/>
                                    <t:inputCalendar for="endDate" format="dd/MM/yyyy"/> 
                </td>
                <td></td>
            </tr>
            <tr>
                <td><bean:message key="screen.b_trm.customerName"/></td>
                <td><bean:message key="screen.b_trm.colon"/></td>
                <td><html:text name="b_trmForm" property="cusName" maxlength="100" styleClass="textwith"></html:text></td>
                <td><bean:message key="screen.b_trm.creditRef"/></td>
                <td><bean:message key="screen.b_trm.colon"/></td>
                <td class="colLeft"><html:text name="b_trmForm" property="creditReference" maxlength="20" styleClass="textwith"></html:text>
                </td>
            </tr>
            <tr>
             <!-- #263 [T11] Add customer type at inquiry screen and export result CT 06062017-->
            	<td><bean:message key="screen.b_trm.customerType"/></td>
                <td><bean:message key="screen.b_trm.colon"/></td>
                <td><html:select
                    property="customerType"
                    name="b_trmForm" style="width:200px;">
                    <option value="">
                        <bean:message key="screen.b_trm.blankItem" />
                    </option>
                    <%--<html:optionsCollection name="LIST_CUSTOMERTYPE"  label="name" value="id"/>--%>
                    <c:forEach items="${LIST_CUSTOMERTYPE}" var="item">
                        <c:if test="${item.id ne 'BOTH'}">
                            <html:option value="${item.id}">${item.name}</html:option>
                        </c:if>
                    </c:forEach>
                </html:select></td>
	            <!-- #263 [T11] Add customer type at inquiry screen and export result CT 06062017-->
               
                <td><bean:message key="screen.b_trm.debitRef"/></td>
                <td><bean:message key="screen.b_trm.colon"/></td>
                <td class="colLeft">
                    <html:text name="b_trmForm" property="debitReference" maxlength="20" styleClass="textwith"></html:text>
                </td>
            </tr>
            <tr>
            	<td><bean:message key="screen.b_trm.billAccNo"/></td>
                <td><bean:message key="screen.b_trm.colon"/></td>
                <td colspan="4">
                    <html:text name="b_trmForm" property="billAccountNo" maxlength="100" styleClass="textwith"></html:text>
                </td>
            </tr>
            <tr>
                <td><bean:message key="screen.b_trm.billCurency"/></td>
                <td><bean:message key="screen.b_trm.colon"/></td>
                <td colspan="4">
                    <html:select name="b_trmForm" property="billCurrency">
                          <html:option value="" ><bean:message key="screen.b_trm.blankItem"/></html:option>
                          <html:optionsCollection name="LIST_CURRENCY" value="id" label="name"/>
                    </html:select>
                </td>
            </tr>
        </table>
    </div>
</div>
<div class="pageWidth">
    <table class="buttonGroup" cellpadding="0" cellspacing="0">
      <tr>
        <td>
            <html:submit property="forward_search" onclick="showLoadingTipWindow();"><bean:message key="screen.b_trm.search"/></html:submit>
            <!--input type="submit" value="<bean:message key="screen.b_trm.search"/>"/>&nbsp;-->
            <bs:buttonLink action="/B_TRMS01SCR" value="Reset"/>&nbsp;
            <%
            String accessType = ((nttdm.bsys.common.fw.BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT")).getUserAccessInfo("B", "B-TRM").getAccess_type();
            if(accessType.equals("2")) {
            %>
               <button onclick="new_data();"><bean:message key="screen.b_trm.new"/></button>
               <%} %>
            <% if(accessType.equals("1") || accessType.equals("2")) { %>
               <logic:equal name="b_trmForm" property="totalRow" value="0">
                   <html:button property="export" disabled="true"><bean:message key="screen.b_trm.exportResult"/></html:button>
               </logic:equal>
               <logic:notEqual name="b_trmForm" property="totalRow" value="0">
                   <logic:empty name="b_trmForm" property="totalRow">
                       <html:button property="export" disabled="true"><bean:message key="screen.b_trm.exportResult"/></html:button>
                   </logic:empty>
                   <logic:notEmpty name="b_trmForm" property="totalRow">
                       <html:submit property="forward_export" onclick="showLoadingTipWindow();"><bean:message key="screen.b_trm.exportResult"/></html:submit>
                   </logic:notEmpty>
               </logic:notEqual>
            <% } %>
        </td>    
    </tr>
    </table>
</div>
<div class="pageWidth">
    <table class="searchResultNo" cellpadding="0" cellspacing="0">
          <tr>
            <td>
                <bean:message key="screen.b_trm.searchFound"/> <bean:message key="screen.b_trm.colon"/>
                <bean:write name="b_trmForm" property="totalRow"/>
            
            </td>    
        </tr>
    </table>  
    <table class="pageLink" cellpadding="0" cellspacing="0">
        <tr>
            <td><bean:message key="screen.b_trm.gotoPage"/> <bean:message key="screen.b_trm.colon"/>
                <ts:pageLinks 
                    id="curPageLinks"
                    action="${pageContext.request.contextPath}/B_TRM/B_TRMS01BL.do" 
                    name="b_trmForm" 
                    rowProperty="row"
                    totalProperty="totalRow" 
                    indexProperty="startIndex"
                    currentPageIndex="now" 
                    totalPageCount="total"
                    submit="true"/>
                <logic:present name="curPageLinks">
                    <bean:write name="curPageLinks" filter="false"/>
                </logic:present>
            </td>
        </tr>
    </table>
</div>
<div class="pageWidth">
    <table class="resultInfo" cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td class="header" width="4%" style="text-align:left; padding-left: 10px;"><bean:message key="screen.b_trm.noCol"/></td>
        <td class="header" width="8%" style="text-align:left; padding-right: 10px;"><bean:message key="screen.b_trm.transDate"/></td>
        <td class="header" width="10%" style="text-align:left; padding-right: 10px;"><bean:message key="screen.b_trm.custID"/></td>
        <td class="header" width="13%" style="text-align:left; padding-right: 10px;"><bean:message key="screen.b_trm.custName"/></td>
        <td class="header" width="10%" style="text-align:left; padding-right: 10px;"><bean:message key="screen.b_trm.BillAcc"/></td>
        <td class="header" width="5%" style="text-align:left; padding-right: 10px;"><bean:message key="screen.b_trm.BillCur"/></td>
        <td class="header" width="13%" style="text-align:left; padding-right: 10px;"><bean:message key="screen.b_trm.creditRef"/></td>
        <td class="header" width="8%" style="text-align:right; padding-right: 10px;"><bean:message key="screen.b_trm.oriAmt"/></td>
        <td class="header" width="8%" style="text-align:right; padding-right: 10px;"><bean:message key="screen.b_trm.creditBal"/></td>
        <td class="header" width="13%" style="text-align:left; padding-right: 10px;"><bean:message key="screen.b_trm.debitRef"/></td>
        <td class="header" width="8%" style="text-align:right; padding-right: 10px;"><bean:message key="screen.b_trm.payment"/></td>
      </tr>
      <logic:present property="searchResult" name="b_trmForm">
          <logic:iterate id="b_trm" name="b_trmForm" property="searchResult" >
              <tr>
                <td class="defaultNo" style="text-align:left; padding-left: 10px;" valign="top">
                    ${b_trm.INDEX + b_trmForm.startIndex}
                </td>
                <td class="defaultText" style="text-align:left; padding-right: 10px;" valign="top">                     
                    ${b_trm.DATE_UPDATED} 
                </td>
                <td class="defaultText" style="text-align:left; padding-right: 10px;" valign="top">    
                    ${b_trm.ID_CUST}
                </td>
                <td class="defaultText" style="text-align:left; padding-right: 10px;" valign="top">    
                    ${b_trm.CUST_NAME}
                </td>
                <td class="defaultText" style="text-align:left; padding-right: 10px;" valign="top">    
                    ${b_trm.BILL_ACC}
                </td>
                <td class="defaultText" style="text-align:left; padding-right: 10px;" valign="top">    
                    ${b_trm.BILL_CURRENCY}
                </td>
                <td class="defaultText" style="text-align:left; padding-right: 10px;" valign="top">
                       <bean:define id="def_credit_ref" name="b_trm" property="CREDIT_REF"></bean:define>
                     <a href="#" onclick="clickCreditRefLink('<%=def_credit_ref%>')" ><bean:write name="b_trm" property="CREDIT_REF"/></a>
                </td>
                <td class="defaultText" style="text-align:right; padding-right:10px;" valign="top"> 
                    ${b_trm.BILL_AMOUNT}
                </td>
                <td class="defaultText" style="text-align:right; padding-right:10px;" valign="top">
                    ${b_trm.AMT_DUE}
                </td>
                <td class="defaultText" style="text-align:left; padding-right: 10px;" valign="top">        
                    ${b_trm.DEBIT_REF}                
                </td>
                <td class="defaultText" style="text-align:right; padding-right:10px;" valign="top">
                    ${b_trm.AMT_PAID}                
                </td>
              </tr>
           </logic:iterate>
       </logic:present>
    </table>
</div>
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
</ts:form>
</body>
</html>