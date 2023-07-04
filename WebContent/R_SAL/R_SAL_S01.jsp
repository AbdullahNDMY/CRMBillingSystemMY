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
	<link href="${pageContext.request.contextPath}/R_SAL/css/r_sal.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
	<title><bean:message key="screen.r_sal.title"/></title>
	<script type="text/javascript">
		function searchClick() {
			var startIndex = document.getElementById("startIndex");
			if(startIndex!=null && startIndex!=undefined) {
				startIndex.value="0";
			}
		}
		// #186 Start
		function exportCSV() {
			var exportLimit = $("#exportLimit").val();
			var searchCount = $("#searchCount").val();
			if (parseInt(searchCount) >= parseInt(exportLimit)) {
				var warnMsg = "Total results to be exported exceed export limit " + exportLimit;
				alert(warnMsg);
				return false;
			} else {
				showLoadingTipWindow();
			}
		}
		//#186 End
	</script>
</head>
<body id="r" onload="initMain();">
<ts:form action="/R_SAL_R01DSP" >
<div style="width:1800px;">
	<t:defineCodeList id="AFFILIATE_CODE"/>
	<t:defineCodeList id="LIST_CUSTOMERTYPE"/>
    <t:defineCodeList id="LIST_RATEMODE" />
	<!-- #186 Start -->
    <t:defineCodeList id="LIST_EXPORT_LIMIT" />
    <input id="exportWarnMsg" type="hidden" value="<bean:message key='errors.ERR1SC105' arg0='0'/>"/>
    <input id="exportLimit" type="hidden" value="<t:writeCodeValue codeList='LIST_EXPORT_LIMIT' key='R-SAL' name='export_limit'></t:writeCodeValue>"/>
    <input id="searchCount" type="hidden" value="${_r_salForm.map.totalRow}"/>
    <!-- #186 End -->
	<h1 class="title"><bean:message key="screen.r_sal.title"/></h1>
      <div class="section" style="border-top:2px solid #cecece;border-bottom:2px solid #cecece;padding:5px 20px;margin-top:-5px;">
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td align="left">
						<bean:message key="screen.r_sal.billMonth"/>
					</td>
					<td>
						<bean:message key="screen.r_sal.colon"/> 
					</td>
					<td nowrap="nowrap">
						<%-- loading bill month --%>
						<html:text property="tbxBillYearMonth" name="_r_salForm" readonly="true" style="width:70px;"/>
			            <t:inputCalendar for="tbxBillYearMonth" format="dd/MM/yyyy"/>
                        &nbsp;<bean:message key="screen.r_sal.line"/>&nbsp;
                        <html:text property="tbxBillYearMonthTo" name="_r_salForm" readonly="true" style="width:70px;"/>
			            <t:inputCalendar for="tbxBillYearMonthTo" format="dd/MM/yyyy"/>
					</td>
                    <td align="left">
                        <bean:message key="screen.r_sal.subscriptionID"/>
                    </td>
                    <td>
                        <bean:message key="screen.r_sal.colon"/> 
                    </td>
                    <td>
                        <html:text name="_r_salForm" property="tbxSubscription" styleClass="SALTextBox" maxlength="20"></html:text>
                    </td>
				</tr>
				<tr>
                    <td align="left">
                        <bean:message key="screen.r_sal.customerName"/>
                    </td>
                    <td>
                        <bean:message key="screen.r_sal.colon"/> 
                    </td>
                    <td>
                        <html:text name="_r_salForm" property="tbxCustomerName" styleClass="SALTextBox" maxlength="100"></html:text>
                    </td>
					<td align="left">
						<bean:message key="screen.r_sal.Category"/>
					</td>
					<td>
						<bean:message key="screen.r_sal.colon"/> 
					</td>
					<td>
						<html:select name="_r_salForm" property="cboCategory">
							<html:option value="" ><bean:message key="screen.r_sal.label_blank"/></html:option>
							<logic:present property="cbServiceGroups" name="_r_salForm">
								<html:optionsCollection property="cbServiceGroups" name="_r_salForm" label="label" value="value"/>
							</logic:present>
						</html:select>
					</td>
				</tr>
				<tr>
                    <td align="left">
                        <bean:message key="screen.r_sal.customerType"/>
                    </td>
                    <td>
                        <bean:message key="screen.r_sal.colon"/> 
                    </td>
                    <td>
                        <html:select property="tbxCustomerType" name="_r_salForm">
                            <option value=""><bean:message key="screen.r_sal.label_blank"/></option>
                            <c:forEach items="${LIST_CUSTOMERTYPE}" var="item">
                                <c:if test="${item.id ne 'BOTH'}">
                                    <html:option value="${item.id}" >${item.name}</html:option>
                                </c:if>
                            </c:forEach>
                        </html:select>
                    </td>
					<td align="left">
						<bean:message key="screen.r_sal.serviceName"/>
					</td>
					<td>
						<bean:message key="screen.r_sal.colon"/> 
					</td>
					<td>
						<html:text name="_r_salForm" property="tbxServiceName" styleClass="SALTextBox" maxlength="300"></html:text>
					</td>
				</tr>
				<tr>
                    <td align="left">
                        <bean:message key="screen.r_sal.affiliateCode"/>
                    </td>
                    <td>
                        <bean:message key="screen.r_sal.colon"/> 
                    </td>
                    <td>
                        <html:select name="_r_salForm" property="tbxAffiliateCod">
                          <html:option value="" ><bean:message key="screen.r_sal.label_blank"/></html:option>
                          <html:options collection="AFFILIATE_CODE" property="id" labelProperty="name"/>
                        </html:select>
                    </td>
					<td align="left">
						<bean:message key="screen.r_sal.RevenueCode"/>
					</td>
					<td>
						<bean:message key="screen.r_sal.colon"/> 
					</td>
					<td>
						<html:text name="_r_salForm" property="tbxRevenueCode" styleClass="SALTextBox" maxlength="50"></html:text>
					</td>
				</tr>
				<tr>
                    <td align="left">
                        <bean:message key="screen.r_sal.BillingDocNo"/>
                    </td>
                    <td>
                        <bean:message key="screen.r_sal.colon"/> 
                    </td>
                    <td>
                        <html:text name="_r_salForm" property="tbxInvoiceNo" styleClass="SALTextBox" maxlength="20"></html:text>
                    </td>
                    <td align="left">
                        <bean:message key="screen.r_sal.rateMode"/>
                    </td>
                    <td>
                        <bean:message key="screen.r_sal.colon"/> 
                    </td>
                    <td>
                        <html:select name="_r_salForm" property="cboRateMode">
                            <html:option value="" ><bean:message key="screen.r_sal.label_blank"/></html:option>
                            <%-- exclude Hourly and Minute --%>
                            <c:forEach items="${LIST_RATEMODE}" var="item">
                                <c:if test="${item.id ne '7' && item.id ne '8'}">
                                    <html:option value="${item.id}" >${item.name}</html:option>
                                </c:if>
                            </c:forEach>
                        </html:select>
                    </td>
				</tr>
				<tr>
					<td align="left">
						<bean:message key="screen.r_sal.currency"/>
					</td>
					<td>
						<bean:message key="screen.r_sal.colon"/> 
					</td>
					<td>
						<%-- loading currency --%>
						<t:defineCodeList id="LIST_CURRENCY" />
						<html:select name="_r_salForm" property="cboCurrency">
							<html:option value="" ><bean:message key="screen.r_sal.label_blank"/></html:option>
							<html:options collection="LIST_CURRENCY" property="id" labelProperty="name"/>
						</html:select>	
					</td>
                    <td align="left">
                        <bean:message key="screen.r_sal.serviceDateStart"/>
                    </td>
                    <td>
                        <bean:message key="screen.r_sal.colon"/> 
                    </td>
                    <td nowrap="nowrap">
                        <html:text property="serviceDateStartFrom" name="_r_salForm" readonly="true" style="width:70px;"/>
                        <t:inputCalendar for="serviceDateStartFrom" format="dd/MM/yyyy"/>
                        &nbsp;<bean:message key="screen.r_sal.line"/>&nbsp;
                        <html:text property="serviceDateStartTo" name="_r_salForm" readonly="true" style="width:70px;"/>
                        <t:inputCalendar for="serviceDateStartTo" format="dd/MM/yyyy"/>
                    </td>
				</tr>
                <tr>
                    <td align="left">
                        <bean:message key="screen.r_sal.issueType"/>
                    </td>
                    <td>
                        <bean:message key="screen.r_sal.colon"/> 
                    </td>
                    <td>
                        <span style="width:100px;">
                            <html:checkbox name="_r_salForm" property="issueTypeSingpost" value="0"><bean:message key="screen.r_sal.issueTypeSingpost"/></html:checkbox>
                        </span>
                        <span style="width:150px;">
                            <html:checkbox name="_r_salForm" property="issueTypeAuto" value="0"><bean:message key="screen.r_sal.issueTypeAuto"/></html:checkbox>
                        </span>
                        <html:checkbox name="_r_salForm" property="issueTypeManual" value="0"><bean:message key="screen.r_sal.issueTypeManual"/></html:checkbox>
                    </td>
                    <td align="left">
                        <bean:message key="screen.r_sal.serviceDateEnd"/>
                    </td>
                    <td>
                        <bean:message key="screen.r_sal.colon"/> 
                    </td>
                    <td nowrap="nowrap">
                        <html:text property="serviceDateEndFrom" name="_r_salForm" readonly="true" style="width:70px;"/>
                        <t:inputCalendar for="serviceDateEndFrom" format="dd/MM/yyyy"/>
                        &nbsp;<bean:message key="screen.r_sal.line"/>&nbsp;
                        <html:text property="serviceDateEndTo" name="_r_salForm" readonly="true" style="width:70px;"/>
                        <t:inputCalendar for="serviceDateEndTo" format="dd/MM/yyyy"/>
                    </td>
                </tr>
				<%--<tr>
					<td align="left">
						<bean:message key="screen.r_sal.paymentMethod"/>
					</td>
					<td>
						<bean:message key="screen.r_sal.colon"/> 
					</td>
					<td>
						<!-- loading payment Method -->
				        <t:defineCodeList id="LIST_PAYMENT_METHOD" />
						<html:select name="_r_salForm" property="cboPaymentMetho">
							<html:option value="" ><bean:message key="screen.r_sal.label_blank"/></html:option>
							<html:options collection="LIST_PAYMENT_METHOD" property="id" labelProperty="name"/>
						</html:select>
					</td>
				</tr>--%>
			</table>
		</div>
		<br/>
		<div>
			<html:submit property="forward_search" onclick="searchClick();showLoadingTipWindow();"><bean:message key='screen.r_sal.search'/></html:submit>
			<bs:buttonLink action="/R_SAL_R01BL" value="Reset"/>
			<c:if test="${_r_salForm.map.accessType eq '1' or _r_salForm.map.accessType eq '2'}">
				<c:if test="${_r_salForm.map.totalRow > 0}">
					<html:submit property="forward_export" onclick="return exportCSV();"><bean:message key="screen.r_sal.exportReport"/></html:submit>
				</c:if>
				<c:if test="${_r_salForm.map.totalRow == 0 or _r_salForm.map.totalRow == null}">
					<html:submit property="forward_export" disabled="true"><bean:message key="screen.r_sal.exportReport"/></html:submit>
				</c:if>
			</c:if>
		</div>
		<br/>
		<div class="section">
			<div class="group result">
				<h2><bean:message key="screen.r_sal.searchFound" /><bean:message key="screen.r_sal.colon"/>  ${_r_salForm.map.totalRow}</h2>
			</div>
			<div class="pageLink">
				<bean:message key="screen.r_sal.gotoPage"/> <bean:message key="screen.r_sal.colon"/>
				<ts:pageLinks 
	    			id="curPageLinks"
					action="${pageContext.request.contextPath}/R_SAL/R_SAL_R02BL.do" 
					name="_r_salForm" 
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
	    <td class="header" width="5%" style="padding-left: 0.05in;"><bean:message key="screen.r_sal.billMonthCol"/></td>
	    <td class="header" width="12%" style="padding-left: 0.05in;"><bean:message key="screen.r_sal.customerNameCol"/></td>
	    <td class="header" width="5%" style="padding-left: 0.05in;"><bean:message key="screen.r_sal.customerTypeCol"/></td>
	    <td class="header" width="5%" style="padding-left: 0.05in;"><bean:message key="screen.r_sal.affiliateCodeCol"/></td>
	    <td class="header" width="8%" style="padding-left: 0.05in;"><bean:message key="screen.r_sal.BillingDocNoCol"/></td>
	    <td class="header" width="5%" style="padding-left: 0.05in;"><bean:message key="screen.r_sal.subscriptionIDCol"/></td> 
	    <td class="header" width="5%" style="padding-left: 0.05in;"><bean:message key="screen.r_sal.CategoryCol"/></td>
	    <td class="header" width="10%" style="padding-left: 0.05in;"><bean:message key="screen.r_sal.serviceNameCol"/></td>
	    <td class="header" width="5%" style="padding-left: 0.05in;"><bean:message key="screen.r_sal.RevenueCodeCol"/></td>
	    <td class="header" width="6%" style="padding-left: 0.05in;"><bean:message key="screen.r_sal.issueTypeCol"/></td>
	    <td class="header" width="2%" style="text-align:center"><bean:message key="screen.r_sal.currencyCol"/></td>
	    <td class="rightHeader" width="5%" style="padding-right: 0.10in;"><bean:message key="screen.r_sal.invoiceAmtBefGstCol"/></td>
	    <td class="rightHeader" width="5%" style="padding-right: 0.10in;"><bean:message key="screen.r_sal.gstAmountCol"/></td>
	    <td class="rightHeader" width="7%" style="padding-right: 0.05in;"><bean:message key="screen.r_sal.totalInvoiceAmtAfterGstCol"/></td>
        <td class="header" width="5%" style="padding-left: 0.05in;"><bean:message key="screen.r_sal.rateMode"/></td>
        <td class="header" width="5%" style="padding-left: 0.05in;"><bean:message key="screen.r_sal.serviceStart"/></td>
        <td class="header" width="5%" style="padding-left: 0.05in;"><bean:message key="screen.r_sal.serviceEnd"/></td>
	  </tr>
	  <c:forEach items="${_r_salForm.map.listSalesReport}" var="item" varStatus="status">
		  <tr>
		    <td class="defaultText" style="padding-left: 0.05in;word-break:break-all">
		    	${item.DATE_REQ}
		    </td>
		    <td class="defaultText" style="padding-left: 0.05in;word-break:break-all">
		    	${item.CUST_NAME}
		    </td>
		    <td class="defaultText" style="padding-left: 0.05in;word-break:break-all">
		    	<t:writeCodeValue codeList="LIST_CUSTOMERTYPE" key="${item.CUSTOMER_TYPE}"/>
		    </td>
		    <td class="defaultText" style="padding-left: 0.05in;word-break:break-all">
		    	<logic:notEmpty name="item" property="AFFILIATE_CODE">
		    	<t:writeCodeValue name="item" property="AFFILIATE_CODE" codeList="AFFILIATE_CODE"></t:writeCodeValue>
		    	</logic:notEmpty>
		    </td>
		    <td class="defaultText" style="padding-left: 0.05in;word-break:break-all"> 
		    	${item.ID_REF}
		    </td>
		    
		    <td class="defaultText" style="padding-left: 0.05in;word-break:break-all"> 
		    	${item.ID_SUB_INFO}
		    </td>
		    <td class="defaultText" style="padding-left: 0.05in;word-break:break-all"> 
		    	${item.SVC_GRP_NAME}
		    </td>
		    <%-- <td class="defaultText">
		    	
		    </td>--%>
		    <td class="defaultText" style="padding-left: 0.05in;word-break:break-all"> 
		    	${item.SVC_DESC}
		    </td>
		    <td class="defaultText" style="padding-left: 0.05in;word-break:break-all"> 
		    	${item.ACC_CODE}
		    </td>
		    <td class="defaultText" style="padding-left: 0.05in;word-break:break-all"> 
		    	<c:if test="${item.IS_SINGPOST eq '1' and item.IS_MANUAL eq '0'}">
	  		        <bean:message key="screen.r_sal.issueTypeSingpost"/>
	  		    </c:if>
	  		    <c:if test="${item.IS_SINGPOST eq '0' and item.IS_MANUAL eq '0'}">
	  		        <bean:message key="screen.r_sal.issueTypeAuto"/>
	  		    </c:if>
	  		    <c:if test="${item.IS_SINGPOST eq '0' and item.IS_MANUAL eq '1'}">
	  		        <bean:message key="screen.r_sal.issueTypeManual"/>
	  		    </c:if>
		    </td>
		    <%--<td class="defaultText" >				    
			    ${item.PAY_METHOD}
		    </td>--%>
		    <td class="defaultText" style="text-align:center;">
		    	${item.BILL_CURRENCY}
		    </td>
		    <td class="defaultNumeric" style="padding-right: 0.10in;">
		    	<fmt:formatNumber value="${item.INVOICE_AMT}" pattern="#,##0.00"></fmt:formatNumber>
		    </td>
		    <td class="defaultNumeric" style="padding-right: 0.10in;">
		    	<fmt:formatNumber value="${item.GST_AMOUNT}" pattern="#,##0.00"></fmt:formatNumber>
		    </td>
		    <td class="defaultNumeric" style="padding-right: 0.05in;">
		    	<fmt:formatNumber value="${item.BILL_AMOUNT}" pattern="#,##0.00"></fmt:formatNumber>
		    </td>
            <td class="defaultText" style="padding-left: 0.05in;word-break:break-all"> 
                <t:writeCodeValue codeList="LIST_RATEMODE" key="${item.RATE_MODE}"/>
            </td>
            <td class="defaultText" style="padding-left: 0.05in;word-break:break-all"> 
               ${item.SERVICE_START}
            </td>
            <td class="defaultText" style="padding-left: 0.05in;word-break:break-all"> 
               ${item.SERVICE_END}
            </td>
		  </tr>
	   </c:forEach>
	</table>
	</div>		
	<div class="message">
		<ts:messages id="message" message="true"><bean:write name="message"/></ts:messages>
	</div>
	<div class="error">
		<html:messages id="message">
			<bean:write name="message"/><br/>
		</html:messages>
	</div>
	</div>
  </ts:form>
</body>
</html>