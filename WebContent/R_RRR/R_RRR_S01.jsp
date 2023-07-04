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
	<link href="${pageContext.request.contextPath}/R_RRR/css/r_rrr.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
	<title><bean:message key="screen.r_rrr.title"/></title>
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
<ts:form action="/R_RRR_R01DSP" >
    <!-- loading payment Method -->
    <t:defineCodeList id="LIST_PAYMENT_METHOD" />
    <t:defineCodeList id="PAYMENT_REF_HEADER" />
    <t:defineCodeList id="AFFILIATE_CODE"/>
    <!-- #186 Start -->
    <t:defineCodeList id="LIST_EXPORT_LIMIT" />
    <input id="exportWarnMsg" type="hidden" value="<bean:message key='errors.ERR1SC105' arg0='0'/>"/>
    <input id="exportLimit" type="hidden" value="<t:writeCodeValue codeList='LIST_EXPORT_LIMIT' key='R-RRR' name='export_limit'></t:writeCodeValue>"/>
    <input id="searchCount" type="hidden" value="${_r_rrrForm.map.totalRow}"/>
    <!-- #186 End -->
	<div class="pageWidth">
		<h1 class="title"><bean:message key="screen.r_rrr.title"/></h1>
    	<div class="section" style="border-top:2px solid #cecece;border-bottom:2px solid #cecece;padding:5px 20px;margin-top:-5px;">
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td align="left">
						<bean:message key="screen.r_rrr.paidDate"/>
					</td>
					<td>
						<bean:message key="screen.r_rrr.colon"/> 
					</td>
					<td nowrap="nowrap">
					   <html:text property="tbxPaidDateFrom" name="_r_rrrForm" readonly="true" style="width:70px;"/>
                        <t:inputCalendar for="tbxPaidDateFrom" format="dd/MM/yyyy"/>
                        &nbsp;<bean:message key="screen.r_rrr._"/>&nbsp;
                        <html:text property="tbxPaidDateTo" name="_r_rrrForm" readonly="true" style="width:70px;"/>
                        <t:inputCalendar for="tbxPaidDateTo" format="dd/MM/yyyy"/>
					</td>
					<td rowspan="9" valign="top" style="padding-left:10px; padding-top:1px;">
					    <fieldset class="fieldsetPadding fieldsetBorder">
	                        <legend>Legend</legend>
	                        <table cellpadding="0" cellspacing="0" style="width:100%;color:#333333;border:solid #CFCFCF 1px">
			                    <tr>
			                        <th style="width:40%;background-color:#D0DCEE;border-bottom:2px solid #cecece;">Payment Method</th>
			                        <th style="width:60%;background-color:#D0DCEE;border-bottom:2px solid #cecece;"><t:writeCodeValue codeList="PAYMENT_REF_HEADER" key="PaymentRef2"/></th>
			                    </tr>
                                <c:forEach items="${LIST_PAYMENT_METHOD}" var="item">
			                        <tr>
			                             <td>${item.name}</td>
			                             <td>
			                                 <c:forEach items="${_r_rrrForm.map.paymentInfo}" var="item2">
			                                     <c:if test="${item2.PAYMENTMETHOD eq item.id}">
			                                         ${item2.PAYMENTREF}
			                                     </c:if>
			                                 </c:forEach> 
			                             </td>
			                        </tr>
			                    </c:forEach>
	                        </table>
                        </fieldset>
					</td>
				</tr>
				<tr>
					<td align="left">
						<bean:message key="screen.r_rrr.customerName"/>
					</td>
					<td>
						<bean:message key="screen.r_rrr.colon"/> 
					</td>
					<td>
						<html:text name="_r_rrrForm" property="tbxCustomerName" styleClass="RRRTextBox" maxlength="100" ></html:text>
					</td>
				</tr>
				<tr>
					<td align="left">
						<bean:message key="screen.r_rrr.affiliateCode"/>
					</td>
					<td>
						<bean:message key="screen.r_rrr.colon"/> 
					</td>
					<td>
						<html:select name="_r_rrrForm" property="tbxAffiliateCod">
							<html:option value="" ><bean:message key="screen.r_rrr.label_blank"/></html:option>
							<html:options collection="AFFILIATE_CODE" property="id" labelProperty="name"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="left">
						<bean:message key="screen.r_rrr.billDocument"/>
					</td>
					<td>
						<bean:message key="screen.r_rrr.colon"/> 
					</td>
					<td>
						<html:text name="_r_rrrForm" property="tbxBillDocument" styleClass="RRRTextBox" maxlength="20"></html:text>
					</td>
				</tr>
				<tr>
					<td align="left">
					    <!-- <bean:message key="screen.r_rrr.chequeNo"/> -->
						<t:writeCodeValue codeList="PAYMENT_REF_HEADER" key="PaymentRef2"/>
					</td>
					<td>
						<bean:message key="screen.r_rrr.colon"/> 
					</td>
					<td>
						<html:text name="_r_rrrForm" property="tbxChequeNo" styleClass="RRRTextBox" maxlength="20"></html:text>
					</td>
				</tr>
				<tr>
					<td align="left">
						<bean:message key="screen.r_rrr.receiptNo"/>
					</td>
					<td>
						<bean:message key="screen.r_rrr.colon"/> 
					</td>
					<td>
						<html:text name="_r_rrrForm" property="tbxReceiptNo" styleClass="RRRTextBox" maxlength="20"></html:text>
					</td>
				</tr>
				<tr>
					<td align="left">
						<bean:message key="screen.r_rrr.bankInName"/>
					</td>
					<td>
						<bean:message key="screen.r_rrr.colon"/> 
					</td>
					<td>
						<%-- loading Bank-In Name --%>
                        <html:select name="_r_rrrForm" property="tbxBankInName">
							<html:option value="" ><bean:message key="screen.r_rrr.label_blank"/></html:option>
							<html:optionsCollection property="listBackInfo" label="label" value="value"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="left">
						<bean:message key="screen.r_rrr.paymentMethod"/>
					</td>
					<td>
						<bean:message key="screen.r_rrr.colon"/> 
					</td>
					<td>
						<html:select name="_r_rrrForm" property="cboPaymentMetho">
							<html:option value="" ><bean:message key="screen.r_rrr.label_blank"/></html:option>
							<html:options collection="LIST_PAYMENT_METHOD" property="id" labelProperty="name"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="left">
						<bean:message key="screen.r_rrr.currency"/>
					</td>
					<td>
						<bean:message key="screen.r_rrr.colon"/> 
					</td>
					<td>
						<%-- loading currency --%>
						<t:defineCodeList id="LIST_CURRENCY" />
						<html:select name="_r_rrrForm" property="cboCurrency">
							<html:option value="" ><bean:message key="screen.r_rrr.label_blank"/></html:option>
							<html:options collection="LIST_CURRENCY" property="id" labelProperty="name"/>
						</html:select>	
					</td>
				</tr>
			</table>
		</div>
	</div>
	<br/>
	<div class="pageWidth">
		<html:submit property="forward_search" onclick="searchClick();showLoadingTipWindow();"><bean:message key='screen.r_rrr.search'/></html:submit>
		<bs:buttonLink action="/R_RRR_R01BL" value="Reset"/>
		<c:if test="${_r_rrrForm.map.accessType eq '1' or _r_rrrForm.map.accessType eq '2'}">
			<c:if test="${_r_rrrForm.map.totalRow > 0}">
				<html:submit property="forward_export" onclick="return exportCSV();"><bean:message key="screen.r_rrr.exportReport"/></html:submit>
			</c:if>
			<c:if test="${_r_rrrForm.map.totalRow == 0 or _r_rrrForm.map.totalRow == null}">
				<html:submit property="forward_export" disabled="true"><bean:message key="screen.r_rrr.exportReport"/></html:submit>
			</c:if>
		</c:if>
	</div>
	<br/>
	<div class="pageWidth">
		<div class="section">
			<div class="group result">
				<h2><bean:message key="screen.r_rrr.searchFound" /> <bean:message key="screen.r_rrr.colon"/>${_r_rrrForm.map.totalRow}</h2>
			</div>
			<div class="pageLink">
				<bean:message key="screen.r_rrr.gotoPage"/> <bean:message key="screen.r_rrr.colon"/>
				<ts:pageLinks 
	    			id="curPageLinks"
					action="${pageContext.request.contextPath}/R_RRR/R_RRR_R02BL.do" 
					name="_r_rrrForm" 
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
	<div style="width:100%">
	<table class="resultInfo" cellpadding="0" cellspacing="0" width="100%">
	  <tr>
	    <td class="header" style="padding-left:10px" width="7%"><bean:message key="screen.r_rrr.paidDateCol"/></td>
	    <td class="header" style="padding-right: 10px;" width="14%"><bean:message key="screen.r_rrr.customerNameCol"/></td>
	    <td class="header" style="padding-right: 10px;" width="6%"><bean:message key="screen.r_rrr.affiliateCodeCol"/></td>
	    <td class="header" style="padding-right: 10px;" width="10%"><bean:message key="screen.r_rrr.billDocument"/></td>
	    <td class="header" style="padding-right: 10px;" width="7%"><t:writeCodeValue codeList="PAYMENT_REF_HEADER" key="PaymentRef2"/></td>
	    <td class="header" style="padding-right: 10px;" width="9%"><bean:message key="screen.r_rrr.receiptNo"/></td>
	    <td class="header" style="padding-right: 10px;" width="10%"><bean:message key="screen.r_rrr.bankInNameCol"/></td>
	    <td class="header" style="padding-right: 10px;" width="9%"><bean:message key="screen.r_rrr.paymentMethodCol"/></td>
	    <td class="header" width="9%" style="text-align:center; padding-right: 10px;"><bean:message key="screen.r_rrr.currencyCol"/></td>
	    <td class="rightHeader" style="padding-right: 10px;" width="9%"><bean:message key="screen.r_rrr.invoiceAmountCol"/></td>
	    <td class="rightHeader" style="padding-right: 10px;" width="9%"><bean:message key="screen.r_rrr.paidAmountCol"/></td>
	    <td class="header" width="1%"><bean:message key="screen.r_rrr.blank"/></td>
	  </tr>
	  <c:forEach items="${_r_rrrForm.map.listRefundReport}" var="item" varStatus="status">
		  <tr>
		    <td class="defaultText" style="padding-left:10px">
		    	${item.DATE_TRANS}
		    </td>
		    <td class="defaultText" style="padding-right: 10px;">
		    	${item.CUST_NAME}
		    </td>  
		    <td class="defaultText" style="padding-right: 10px;word-break:break-all">
		    	<logic:notEmpty name="item" property="AFFILIATE_CODE">
		    	<t:writeCodeValue name="item" property="AFFILIATE_CODE" codeList="AFFILIATE_CODE"></t:writeCodeValue>
		    	</logic:notEmpty>
		    </td>
		    <td class="defaultText" style="padding-right: 10px;"> 
		    	${item.ID_REF}
		    </td>
		    <td class="defaultText" style="padding-right: 10px;">
		    	${item.REFERENCE2}
		    </td>
		    <td class="defaultText" style="padding-right: 10px;">
		    	${item.RECEIPT_NO}
		    </td>
		    <td class="defaultText" style="padding-right: 10px;">	
		    	${item.COM_ACCT_NAME}
		    </td>
		    <td class="defaultText" style="padding-right: 10px;">
		    	<t:writeCodeValue codeList="LIST_PAYMENT_METHOD" key="${item.PMT_METHOD}"></t:writeCodeValue>
		    </td>
		    <td class="defaultText" style="text-align:center; padding-right: 10px;">
			    ${item.CUR_CODE}
		    </td>
		    <td class="defaultNumeric" style="padding-right: 10px;">
		        <logic:equal value="N" name="item" property="PMT_STATUS">
		    	     <fmt:formatNumber value="${item.BILL_AMOUNT}" pattern="#,##0.00"></fmt:formatNumber>
		    	</logic:equal>
		    	<logic:equal value="F" name="item" property="PMT_STATUS">
                    <fmt:formatNumber value="${-item.REFUND_AMT_RECEIVED}" pattern="#,##0.00"></fmt:formatNumber>
                </logic:equal>
		    </td>
		    <td class="defaultNumeric" style="padding-right: 10px;">
		          <logic:equal value="F" name="item" property="PMT_STATUS">
		             <fmt:formatNumber value="${-item.AMT_PAID}" pattern="#,##0.00"></fmt:formatNumber>
		          </logic:equal>
		          <logic:equal value="N" name="item" property="PMT_STATUS">
		             <fmt:formatNumber value="${item.AMT_PAID}" pattern="#,##0.00"></fmt:formatNumber>
                  </logic:equal>
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