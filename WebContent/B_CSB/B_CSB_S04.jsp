<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/bs" prefix="bs" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
		<link href="<%=request.getContextPath()%>/B_CSB/css/b_csbs01.css" rel="stylesheet" type="text/css" /> 
	    <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
		<title>Insert title here</title>
		<script language="javascript">
		</script>
	<body>	
	<div>
		<ts:form action="/B_CSB_S04SCRBL">
			<html:hidden property="receiptNo"/>

            <h1 class="title"><bean:message key="screen.b_csb_s04.screen.title"/></h1>
			<table  class="resultInfo" cellpadding="0" cellspacing="0">
			  <tr>
			    <td class="header" width="2%" style="text-align:left;padding-left:2px;" valign="top"><bean:message key="screen.b_csb_s04.result.No"/></td>
			    <td class="header" width="9%" style="text-align:left;padding-left:2px;" valign="top"><bean:message key="screen.b_csb_s04.result.date"/></td>
			    <td class="header" width="12%" style="text-align:left;padding-left:2px;" valign="top"><bean:message key="screen.b_csb_s04.result.receiptNo"/></td>
			    <td class="header" width="15%" style="text-align:left;padding-left:2px;"><bean:message key="screen.b_csb_s04.result.refundReference"/></td>
			    <td class="header" width="11%" style="text-align:right;padding-right:2px;"><bean:message key="screen.b_csb_s04.result.amountRefunded"/></td>
			    <td class="header" width="9%" style="text-align:center;padding-right:0" valign="top"><bean:message key="screen.b_csb_s04.result.balance"/></td>
			    <td class="header" width="12%" style="text-align:left;padding-left:2px;"><bean:message key="screen.b_csb_s04.result.paymentMethod"/></td>
			    <td class="header" width="32%" style="text-align:left;padding-left:2px;" valign="top"><bean:message key="screen.b_csb_s04.result.bankPayee"/></td>
			  </tr>
			  <t:defineCodeList id="LIST_PAYMENT_METHOD" />
		      <logic:notEmpty property="refundInfoList" name="b_csbForm">
		      <logic:iterate id="Info"  name="b_csbForm" property="refundInfoList" >
			  <tr>
				<td class="defaultNo" align="left" style="padding-left:2px;">
					<bean:write name="Info" property="NO"/>
				</td>
				<td class="defaultText" align="left" style="padding-left:2px;">
					<bean:write name="Info" property="DATE_TRANS"/>	
				</td>
				<td class="defaultText" align="left" style="padding-left:2px;">
					<bean:write name="Info" property="RECEIPT_NO"/>
				</td>
				<td class="defaultText" align="left" style="padding-left:2px;">
				    <bean:write name="Info" property="REFERENCE2"/>
				</td>
				<td class="defaultText" style="padding-right:2px;text-align:right">
				    <bean:write name="Info" property="CUR_CODE"/>&nbsp;
				    <fmt:formatNumber value="${Info.AMT_RECEIVED}" pattern="#,##0.00"/>
				</td>
				<td class="defaultText" style="padding-right:0;text-align:center">
					<bean:write name="Info" property="CUR_CODE"/>&nbsp;
                    <fmt:formatNumber value="${Info.BALANCE_AMT}" pattern="#,##0.00"/>
				</td>
				<td class="defaultText" style="text-align:left;padding-left:2px;" >
					<t:writeCodeValue codeList="LIST_PAYMENT_METHOD" key="${Info.PMT_METHOD}"/>
				</td>
				<td class="defaultText" style="text-align:left;padding-left:2px;" >
					<logic:iterate id="p" name="b_csbForm" property="cbBankAcc">
                        <c:if test="${Info.ID_COM_BANK == p.value}">
                            ${p.label}
                        </c:if>
                    </logic:iterate>
				</td>
			</tr>
		   </logic:iterate>
 		</logic:notEmpty>
		</table>
		<br/>
		<input type="button" value="<bean:message key="screen.b_csb.exitbutton"/>" onclick="window.close();" class="button"/>
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
	</body>
</html:html>
