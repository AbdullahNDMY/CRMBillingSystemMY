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
	<link href="${pageContext.request.contextPath}/R_AGR/css/r_agr.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
	<title><bean:message key="screen.r_agr.title"/></title>
	<script type="text/javascript">
		function searchClick() {
			var startIndex = document.getElementById("startIndex");
			if(startIndex!=null && startIndex!=undefined) {
				startIndex.value="0";
			}
		}
	</script>
</head>
<body id="r" onload="initMain();">
<ts:form action="/R_AGR_R01DSP" >
	<div class="pageWidth">
	<t:defineCodeList id="AFFILIATE_CODE"/>
	<h1 class="title"><bean:message key="screen.r_agr.title"/></h1>
      <div class="section" style="border-top:2px solid #cecece;border-bottom:2px solid #cecece;padding:5px 20px;margin-top:-5px;">
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td align="left">
						<bean:message key="screen.r_agr.billMonth"/>
					</td>
					<td>
						<bean:message key="screen.r_agr.colon"/> 
					</td>
					<td nowrap="nowrap" colspan="5">
						<bean:message key="screen.r_agr.from"/>
						<%-- loading bill month --%>
						<t:defineCodeList id="NO_OF_MONTH"/>
				        <html:select property="cboBillMonth" >
				          <html:option value="" ><bean:message key="screen.r_agr.select_Month"/></html:option>
				          <html:options collection="NO_OF_MONTH" property="id" labelProperty="name"/>
				        </html:select>
				        <html:text name="_r_agrForm" property="tbxBillYear" styleClass="AGRYearTextBox" maxlength="4"></html:text>
				        
				        <bean:message key="screen.r_agr.blank"/>
				        <bean:message key="screen.r_agr.line"/>
				        <bean:message key="screen.r_agr.to"/>
				        &nbsp;
				        <html:select property="cboBillMonthTo" >
				          <html:option value="" ><bean:message key="screen.r_agr.select_Month"/></html:option>
				          <html:options collection="NO_OF_MONTH" property="id" labelProperty="name"/>
				        </html:select>
				        <html:text name="_r_agrForm" property="tbxBillYearTo" styleClass="AGRYearTextBox" maxlength="4"></html:text>
					</td>
				</tr>
				<tr>
					<td align="left">
						<bean:message key="screen.r_agr.customerName"/>
					</td>
					<td>
						<bean:message key="screen.r_agr.colon"/> 
					</td>
					<td>
						<html:text name="_r_agrForm" property="tbxCustomerName" styleClass="AGRTextBox" maxlength="100"></html:text>
					</td>
					<td width="20px"/>
					<td align="left">
						<bean:message key="screen.r_agr.paymentMethod"/>
					</td>
					<td>
						<bean:message key="screen.r_agr.colon"/> 
					</td>
					<td>
						<!-- loading payment Method -->
				        <t:defineCodeList id="LIST_PAYMENT_METHOD" />
						<html:select name="_r_agrForm" property="cboPaymentMetho">
							<html:option value="" ><bean:message key="screen.r_agr.label_blank"/></html:option>
							<html:options collection="LIST_PAYMENT_METHOD" property="id" labelProperty="name"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="left">
						<bean:message key="screen.r_agr.affiliateCode"/>
					</td>
					<td>
						<bean:message key="screen.r_agr.colon"/> 
					</td>
					<td>
						<html:select name="_r_agrForm" property="tbxAffiliateCod">
							<html:option value="" ><bean:message key="screen.r_agr.label_blank"/></html:option>
							<html:options collection="AFFILIATE_CODE" property="id" labelProperty="name"/>
						</html:select>
					</td>
					<td width="20px"/>
					<td align="left">
						<bean:message key="screen.r_agr.currency"/>
					</td>
					<td>
						<bean:message key="screen.r_agr.colon"/> 
					</td>
					<td>
						<%-- loading currency --%>
						<t:defineCodeList id="LIST_CURRENCY" />
						<html:select name="_r_agrForm" property="cboCurrency">
							<html:option value="" ><bean:message key="screen.r_agr.label_blank"/></html:option>
							<html:options collection="LIST_CURRENCY" property="id" labelProperty="name"/>
						</html:select>	
					</td>
				</tr>
				<tr>
					<td align="left">
						<bean:message key="screen.r_agr.billingAccount"/>
					</td>
					<td>
						<bean:message key="screen.r_agr.colon"/> 
					</td>
					<td colspan="5">
						<html:text name="_r_agrForm" property="tbxBillingAccout" styleClass="AGRTextBox" maxlength="20"></html:text>
					</td>
				</tr>
				<tr>
					<td align="left">
						<bean:message key="screen.r_agr.billDocumentNo"/>
					</td>
					<td>
						<bean:message key="screen.r_agr.colon"/> 
					</td>
					<td colspan="5">
						<html:text name="_r_agrForm" property="tbxBillDocumentNo" styleClass="AGRTextBox" maxlength="20"></html:text>
					</td>
				</tr>
			</table>
		</div>
		</div>
		<br/>
		
		<div class="pageWidth">
			<html:submit property="forward_search" onclick="searchClick();showLoadingTipWindow();"><bean:message key='screen.r_agr.search'/></html:submit>
			<bs:buttonLink action="/R_AGR_R01BL" value="Reset"/>
			<c:if test="${_r_agrForm.map.accessType eq '1' or _r_agrForm.map.accessType eq '2'}">
				<c:if test="${_r_agrForm.map.totalRow > 0}">
					<html:submit property="forward_export" onclick="showLoadingTipWindow()"><bean:message key="screen.r_agr.exportReport"/></html:submit>
				</c:if>
				<c:if test="${_r_agrForm.map.totalRow == 0 or _r_agrForm.map.totalRow == null}">
					<html:submit property="forward_export" disabled="true"><bean:message key="screen.r_agr.exportReport"/></html:submit>
				</c:if>
			</c:if>
		</div>
		<br/>
		<div class="pageWidth">
		<div class="section">
			<div class="group result">
				<h2><bean:message key="screen.r_agr.searchFound" /><bean:message key="screen.r_agr.colon"/>  ${_r_agrForm.map.totalRow}</h2>
			</div>
			<div class="pageLink">
				<bean:message key="screen.r_agr.gotoPage"/> <bean:message key="screen.r_agr.colon"/>
				<ts:pageLinks 
	    			id="curPageLinks"
					action="${pageContext.request.contextPath}/R_AGR/R_AGR_R02BL.do" 
					name="_r_agrForm" 
					rowProperty="row"
					totalProperty="totalRow" 
					indexProperty="startIndex"
					currentPageIndex="now" 
					totalPageCount="total"
					submit="true"/>
				<logic:present name="curPageLinks">  
					<bean:write name="curPageLinks" filter="false"/>
				</logic:present>
			</div>
		</div>
	<div>
	<table class="resultInfo" cellpadding="0" cellspacing="0" width="100%">
	  <tr>
	    <td class="header" style="padding-left:10px" width="10%"><bean:message key="screen.r_agr.billMonthCol"/></td>
	    <td class="header" style="padding-right: 10px;" width="18%"><bean:message key="screen.r_agr.customerNameCol"/></td>
	    <td class="header" style="padding-right: 10px;" width="8%" ><bean:message key="screen.r_agr.affiliateCodeCol"/></td>
	    <td class="header" style="padding-right: 10px;" width="11%"><bean:message key="screen.r_agr.billingAccountCol"/></td>
	    <td class="header" style="padding-right: 10px;" width="14%"><bean:message key="screen.r_agr.invoiceNoCol"/></td>
	    <td class="header" width="6%" style="text-align:center; padding-right: 10px;"><bean:message key="screen.r_agr.currencyCol"/></td>
	    <td class="rightHeader" style="padding-right: 10px;" width="10%"><bean:message key="screen.r_agr.invoiceAmountCol"/></td>
	    <td class="rightHeader" style="padding-right: 10px;" width="10%"><bean:message key="screen.r_agr.outstandingAmountCol"/></td>
	    <td class="header" style="padding-right: 10px;" width="12%"><bean:message key="screen.r_agr.paymentMethodCol"/></td>
	    <td class="header" width="1%"><bean:message key="screen.r_agr.blank"/></td>
	  </tr>
	  <c:forEach items="${_r_agrForm.map.listAgingReport}" var="item" varStatus="status">
		  <tr>
		    <td class="defaultText" style="padding-left:10px">
		    	${item.DATE_REQ}
		    </td>
		    <td class="defaultText" style="padding-right: 10px;">
		    	${item.CUST_NAME}
		    </td>  
		    <td class="defaultText" style="padding-right: 10px;word-break:break-all;">
		    	<logic:notEmpty name="item" property="AFFILIATE_CODE">
		    	<t:writeCodeValue name="item" property="AFFILIATE_CODE" codeList="AFFILIATE_CODE"></t:writeCodeValue>
		    	</logic:notEmpty>
		    </td>
		    <td class="defaultText" style="padding-right: 10px;"> 
		    	${item.BILL_ACC}
		    </td>
		    <td class="defaultText" style="padding-right: 10px;"> 
		    	${item.ID_REF}
		    </td>
		    <td class="defaultText" style="text-align:center;">
		    	${item.BILL_CURRENCY}
		    </td>
		    <td class="defaultNumeric" style="padding-right: 10px;">
		    	<fmt:formatNumber value="${item.BILL_AMOUNT}" pattern="#,##0.00"></fmt:formatNumber>
		    </td>
		    <td class="defaultNumeric" style="padding-right: 10px;">
		    	<fmt:formatNumber value="${item.OUTSTANDING_AMOUNT}" pattern="#,##0.00"></fmt:formatNumber>
		    </td>
		    <td class="defaultText" style="padding-right: 10px;">
		    	<t:writeCodeValue codeList="LIST_PAYMENT_METHOD" key="${item.PAY_METHOD}"></t:writeCodeValue>
		    </td>
		    <td class="defaultText"></td>
		  </tr>
	   </c:forEach>
	</table>
	</div>	
	</div>	
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