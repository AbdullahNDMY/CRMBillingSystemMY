<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/bs" prefix="bs" %>

<html>
<head>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
	<link href="${pageContext.request.contextPath}/B_BIL/css/b_bil.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
	<script type="text/javascript">
		function searchClick() {
			var startIndex = document.getElementById("startIndex");
			if(startIndex!=null && startIndex!=undefined) {
				startIndex.value="0";
			}
		}
		function initHtml(){
			var fullySettled = $("#fullySettled").val();
			var partiallySettled = $("#partiallySettled").val();
			var outstanding = $("#outstanding").val();
			var issueTypeSingpost = $("#issueTypeSingpost").val();
			var issueTypeAuto = $("#issueTypeAuto").val();
			var issueTypeManual = $("#issueTypeManual").val();
 			var deliveryEmail1 = $("#deliveryEmail1").val();
			var deliveryEmail2 = $("#deliveryEmail2").val();
			var delivery1 = $("#delivery1").val();
			var delivery2 = $("#delivery2").val();
			var delivery4 = $("#delivery4").val();
			if (fullySettled == "0") {
				$("#fullySettledBox").attr("checked", true);
			} else {
				$("#fullySettledBox").attr("checked", false);
			}
			if (partiallySettled == "0") {
				$("#partiallySettledBox").attr("checked", true);
			} else {
				$("#partiallySettledBox").attr("checked", false);
			}
			if (outstanding == "0") {
				$("#outstandingBox").attr("checked", true);
			} else {
				$("#outstandingBox").attr("checked", false);
			}
			if (issueTypeSingpost == "0") {
				$("#issueTypeSingpostBox").attr("checked", true);
			} else {
				$("#issueTypeSingpostBox").attr("checked", false);
			}
			if (issueTypeAuto == "0") {
				$("#issueTypeAutoBox").attr("checked", true);
			} else {
				$("#issueTypeAutoBox").attr("checked", false);
			}
			if (issueTypeManual == "0") {
				$("#issueTypeManualBox").attr("checked", true);
			} else {
				$("#issueTypeManualBox").attr("checked", false);
			}
 			if (deliveryEmail1 == "1") {
				$("#deliveryEmail1Box").attr("checked", true);
			} else {
				$("#deliveryEmail1Box").attr("checked", false);
			}
			if (deliveryEmail2 == "0") {
				$("#deliveryEmail2Box").attr("checked", true);
			} else {
				$("#deliveryEmail2Box").attr("checked", false);
			}
			if (delivery1 == "1") {
				$("#delivery1Box").attr("checked", true);
			} else {
				$("#delivery1Box").attr("checked", false);
			}
			if (delivery2 == "2") {
				$("#delivery2Box").attr("checked", true);
			} else {
				$("#delivery2Box").attr("checked", false);
			}
			if (delivery4 == "4") {
				$("#delivery4Box").attr("checked", true);
			} else {
				$("#delivery4Box").attr("checked", false);
			}
		}
		function checkOut(){
			var fullySettled = $("#fullySettled");
			var partiallySettled = $("#partiallySettled");
			var outstanding = $("#outstanding");
			var issueTypeSingpost = $("#issueTypeSingpost");
			var issueTypeAuto = $("#issueTypeAuto");
			var issueTypeManual = $("#issueTypeManual");
			var deliveryEmail1 = $("#deliveryEmail1");
			var deliveryEmail2 = $("#deliveryEmail2");
			var delivery1 = $("#delivery1");
			var delivery2 = $("#delivery2");
			var delivery4 = $("#delivery4");
			
			if ($("#fullySettledBox").attr("checked")) {
				fullySettled.val("0");
			} else {
				fullySettled.val("");
			}
			if ($("#partiallySettledBox").attr("checked")) {
				partiallySettled.val("0");
			} else {
				partiallySettled.val("");
			}
			if ($("#outstandingBox").attr("checked")) {
				outstanding.val("0");
			} else {
				outstanding.val("");
			}
			if ($("#issueTypeSingpostBox").attr("checked")) {
				issueTypeSingpost.val("0");
			} else {
				issueTypeSingpost.val("");
			}
			if ($("#issueTypeAutoBox").attr("checked")) {
				issueTypeAuto.val("0");
			} else {
				issueTypeAuto.val("");
			}
			if ($("#issueTypeManualBox").attr("checked")) {
				issueTypeManual.val("0");
			} else {
				issueTypeManual.val("");
			}
 			if ($("#deliveryEmail1Box").attr("checked")) {
				deliveryEmail1.val("1");
			} else {
				deliveryEmail1.val("");
			}
			if ($("#deliveryEmail2Box").attr("checked")) {
				deliveryEmail2.val("0");
			} else {
				deliveryEmail2.val("");
			} 
			if ($("#delivery1Box").attr("checked")) {
				delivery1.val("1");
			} else {
				delivery1.val("");
			}
			if ($("#delivery2Box").attr("checked")) {
				delivery2.val("2");
			} else {
				delivery2.val("");
			}
			if ($("#delivery4Box").attr("checked")) {
				delivery4.val("4");
			} else {
				delivery4.val("");
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
   	<title><bean:message key="screen.b_bil_s01.title"/></title>
</head>
<body id="b" onload="initMain(); initHtml();">
		<%
            String accessRight = ((nttdm.bsys.common.fw.BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT")).getUserAccessInfo("B", "B-BIL").getAccess_type();
            pageContext.setAttribute("accessRightBean", accessRight);
        %>
<ts:form action="/SC_B_BIL_S01_01DSP" >
    <%-- <html:hidden property="accessType" value="${_b_bilForm.map.accessType}"/> --%>
    <html:hidden property="initFlg" styleId="initFlg"/>
    <!-- Add #156 Start-->
	<html:hidden property="billCnAmtNegative"></html:hidden>
	<!-- Add #156 End-->
	<!-- #186 Start -->
    <t:defineCodeList id="LIST_EXPORT_LIMIT" />
    <t:defineCodeList id="LIST_CUSTOMERTYPE" />
    <input id="exportLimit" type="hidden" value="<t:writeCodeValue codeList='LIST_EXPORT_LIMIT' key='B-BIL' name='export_limit'></t:writeCodeValue>"/>
    <input id="searchCount" type="hidden" value="${_b_bilForm.map.totalRow}"/>
    <!-- #186 End -->
	<div id="contentDiv" style="width:1300px">
	<h1 class="title"><bean:message key="screen.b_bil_s01.title"/></h1>
		<div class="section" style="border-top:2px solid #cecece;border-bottom:2px solid #cecece;padding:5px 5px;margin-top:-5px;">
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td align="right">
						<bean:message key="screen.b_bil.billingRef"/>&nbsp;<bean:message key="screen.b_bil.colon"/>
					</td>
					<td>
						<html:text size="30" name="_b_bilForm" property="tbxBillingReference" maxlength="100" styleClass="QCSTextBox"></html:text>
					</td>
					<td align="right">
						<bean:message key="screen.b_bil.billingDate"/>&nbsp;<bean:message key="screen.b_bil.colon"/>
					</td>
					<td>
						<html:text property="tbxBillingDateFrom" name="_b_bilForm" readonly="true" style="width:70px;"/>
			            <t:inputCalendar for="tbxBillingDateFrom" format="dd/MM/yyyy"/>
			            &nbsp;<bean:message key="screen.b_bil._"/>&nbsp;
			            <html:text property="tbxBillingDateTo" name="_b_bilForm" readonly="true" style="width:70px;"/>
			            <t:inputCalendar for="tbxBillingDateTo" format="dd/MM/yyyy"/> 
					</td>
				</tr>
				<tr>
					<td align="right">
						<bean:message key="screen.b_bil.customerID"/>&nbsp;<bean:message key="screen.b_bil.colon"/>
					</td>
					<td>
						<html:text size="30" name="_b_bilForm" property="tbxCustomerId" maxlength="100" styleClass="QCSTextBox"></html:text>
					</td>
					<td align="right">
						<bean:message key="screen.b_bil.documentStatus"/>&nbsp;<bean:message key="screen.b_bil.colon"/>
					</td>
					<td>
						<html:select name="_b_bilForm" property="cboDocumentStatus" style="width:200px;">
							<t:defineCodeList id="CLOSESTATUS" />
							<html:option value="" ><bean:message key="screen.b_bil.blankItem"/></html:option>
							<html:options collection="CLOSESTATUS" property="id" labelProperty="name"/>
						</html:select>
					</td>
				</tr>	
				<tr>
					<td align="right">
						<bean:message key="screen.b_bil.customerName"/>&nbsp;<bean:message key="screen.b_bil.colon"/>
					</td>
					<td>
						<html:text size="30" name="_b_bilForm" property="tbxCustomerName" maxlength="100" styleClass="QCSTextBox"></html:text>
					</td>
					<td align="right">
					    <bean:message key="screen.b_bil.deletedStatus"/>&nbsp;<bean:message key="screen.b_bil.colon"/>
					</td>
					<td>
					    <html:select name="_b_bilForm" property="cboDeletedStatus" style="width:200px;">
							<html:option value="" ><bean:message key="screen.b_bil.blankItem"/></html:option>
							<html:option value="0" >Normal</html:option>
							<html:option value="1" >Deleted</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<!-- #263 [T11] Add customer type at inquiry screen and export result CT 06062017 ST-->
					<td align="right">
					<bean:message key="screen.b_bil.customerType" />&nbsp;<bean:message key="screen.b_bil.colon" />
	            	</td>
		            <td><html:select
		                    property="cboCustomerType"
		                    name="_b_bilForm" style="width:215px;">
		                    <option value="">
		                        <bean:message key="screen.b_bil.blankItem" />
		                    </option>
		                    <%--<html:optionsCollection name="LIST_CUSTOMERTYPE"  label="name" value="id"/>--%>
		                    <c:forEach items="${LIST_CUSTOMERTYPE}" var="item">
		                        <c:if test="${item.id ne 'BOTH'}">
		                            <html:option value="${item.id}">${item.name}</html:option>
		                        </c:if>
		                    </c:forEach>
		                </html:select></td>
	                <!-- #263 [T11] Add customer type at inquiry screen and export result CT 06062017 EN-->
					<td align="right">
						<bean:message key="screen.b_bil.settlementStatus"/>&nbsp;<bean:message key="screen.b_bil.colon"/>
					</td>
					<td>
					    <span style="width:103px;">
						    <%-- <html:checkbox name="_b_bilForm" property="fullySettled" value="0"><bean:message key="screen.b_bil.fullySettled"/></html:checkbox> --%>
						    <input id="fullySettledBox" type="checkbox" onclick="checkOut();"/><bean:message key="screen.b_bil.fullySettled"/>
							<html:hidden property="fullySettled" styleId="fullySettled"/>						    
						</span>
						<span style="width:150px;">
						    <%-- <html:checkbox name="_b_bilForm" property="partiallySettled" value="0"><bean:message key="screen.b_bil.partiallySettled"/></html:checkbox> --%>
						    <input id="partiallySettledBox" type="checkbox" onclick="checkOut();"/><bean:message key="screen.b_bil.partiallySettled"/>
							<html:hidden property="partiallySettled" styleId="partiallySettled"/>						    
						</span>
						<%-- <html:checkbox name="_b_bilForm" property="outstanding" value="0"><bean:message key="screen.b_bil.outstandingChk"/></html:checkbox> --%>
						    <input id="outstandingBox" type="checkbox" onclick="checkOut();"/><bean:message key="screen.b_bil.outstandingChk"/>
							<html:hidden property="outstanding" styleId="outstanding"/>						
					</td>
				</tr>
				<tr>
					<td align="right">
						<bean:message key="screen.b_bil.billingAccountNo"/>&nbsp;<bean:message key="screen.b_bil.colon"/>
					</td>
					<td>
						<html:text size="30" name="_b_bilForm" property="tbxBillingAccountNo" maxlength="100"></html:text>
					</td>
					<td align="right">
						<bean:message key="screen.b_bil.issueType"/>&nbsp;<bean:message key="screen.b_bil.colon"/>
					</td>
					<td>
					    <span style="width:103px;">
						    <%-- <html:checkbox name="_b_bilForm" property="issueTypeSingpost" value="0"><bean:message key="screen.b_bil.issueTypeSingpost"/></html:checkbox> --%>
						    <input id="issueTypeSingpostBox" type="checkbox" onclick="checkOut();"/><%-- <bean:message key="screen.b_bil.issueTypeSingpost"/> --%>
							${_b_bilForm.map.issueTypeSingpostValue}
							<html:hidden property="issueTypeSingpost" styleId="issueTypeSingpost"/>	
						</span>
						<span style="width:150px;">
						    <%-- <html:checkbox name="_b_bilForm" property="issueTypeAuto" value="0"><bean:message key="screen.b_bil.issueTypeAuto"/></html:checkbox> --%>
						    <input id="issueTypeAutoBox" type="checkbox" onclick="checkOut();"/><%-- <bean:message key="screen.b_bil.issueTypeAuto"/> --%>
							${_b_bilForm.map.issueTypeAutoValue}
							<html:hidden property="issueTypeAuto" styleId="issueTypeAuto"/>						    
						</span>
						<%-- <html:checkbox name="_b_bilForm" property="issueTypeManual" value="0"><bean:message key="screen.b_bil.issueTypeManual"/></html:checkbox> --%>
						    <input id="issueTypeManualBox" type="checkbox" onclick="checkOut();"/><%-- <bean:message key="screen.b_bil.issueTypeManual"/> --%>
							${_b_bilForm.map.issueTypeManualValue}
							<html:hidden property="issueTypeManual" styleId="issueTypeManual"/>					
					</td>
				</tr>
				<tr>
					<td align="right">
						<bean:message key="screen.b_bil.jobNo"/>&nbsp;<bean:message key="screen.b_bil.colon"/>
					</td>
					<td>
						<html:text size="30" name="_b_bilForm" property="jobNo" maxlength="100"></html:text>
					</td>
					<td align="right" valign="top">
						<bean:message key="screen.b_bil.deliveryby"/>&nbsp;<bean:message key="screen.b_bil.colon"/>
					</td>
					<td>
						&nbsp;<bean:message key="screen.b_bil.email"/>&nbsp;
						<%-- <html:multibox name="_b_bilForm" property="deliveryEmail" value="1"/> --%>
						<input id="deliveryEmail1Box" type="checkbox" onclick="checkOut();"/>
						<html:hidden property="deliveryEmail1" styleId="deliveryEmail1"/>	
						<bean:message key="screen.b_bil.emailyes"/>
						<%-- <html:multibox name="_b_bilForm" property="deliveryEmail" value="0"/> --%>
						<input id="deliveryEmail2Box" type="checkbox" onclick="checkOut();"/>
						<html:hidden property="deliveryEmail2" styleId="deliveryEmail2"/>
						<bean:message key="screen.b_bil.emailNo"/><br/>
						<!-- Post,Courier,By Hand -->
	                    <%-- <html:multibox name="_b_bilForm" property="delivery" value="1"/> --%>
	                    <input id="delivery1Box" type="checkbox" onclick="checkOut();"/>
						<html:hidden property="delivery1" styleId="delivery1"/>
				        <bean:message key="screen.b_bil.post"/>
				        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				        <%-- <html:multibox name="_b_bilForm" property="delivery" value="2"/> --%>
	                    <input id="delivery2Box" type="checkbox" onclick="checkOut();"/>
						<html:hidden property="delivery2" styleId="delivery2"/>				        
				        <bean:message key="screen.b_bil.courier"/>
				        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				        <%-- <html:multibox name="_b_bilForm" property="delivery" value="4"/> --%>
	                    <input id="delivery4Box" type="checkbox" onclick="checkOut();"/>
						<html:hidden property="delivery4" styleId="delivery4"/>				        
				        <bean:message key="screen.b_bil.byhand"/>
					</td>
				</tr>
				<tr>
					<td align="right">
					    <bean:message key="screen.b_bil.transactionType"/>&nbsp;<bean:message key="screen.b_bil.colon"/>
					</td>
					<td>
					    <html:select name="_b_bilForm" property="cboTransactionType" style="width:215px;">
							<t:defineCodeList id="TRANSACTION_TYPE" />
							<html:option value="" ><bean:message key="screen.b_bil.blankItem"/></html:option>
							<c:forEach items="${TRANSACTION_TYPE}" var="item">
									<c:if test="${item.id ne 'NT'}">
										<c:choose>
						   					<c:when test="${_b_bilForm.map.cboTransactionType==item.id}">
												<option value="${item.id}" selected="selected">${item.name}</option>
											</c:when>
											<c:otherwise>
								    			<option value="${item.id}">${item.name}</option>
											</c:otherwise>
										</c:choose>
									</c:if>
									<c:if test="${item.id eq 'NT'}">
										<c:if test="${_b_bilForm.map.nonTaxInvoiceShowFlg eq '1'}"> 
											<c:choose>
												<c:when test="${_b_bilForm.map.cboTransactionType == item.id}">
													<option value="${item.id}" selected="selected">${item.name}</option>
												</c:when>
												<c:otherwise>
								    				<option value="${item.id}">${item.name}</option>
												</c:otherwise>
											</c:choose>
										</c:if>
									</c:if>
								</c:forEach>
							<%-- <html:options collection="TRANSACTION_TYPE" property="id" labelProperty="name"/> --%>
						</html:select>
					</td>
					<td align="right">
						<bean:message key="screen.b_bil.category"/>&nbsp;<bean:message key="screen.b_bil.colon"/>
					</td>
					<td>
					<t:defineCodeList id="LIST_CATEGORY" />
					 <html:select property="cboCategory" styleId="cmbSerivceGroup" style="width:210px;" name="_b_bilForm">
						 <html:option value=""><bean:message key="screen.b_bil.listBox.default"/></html:option>
						 <html:options collection="LIST_CATEGORY" property="id" labelProperty="name"/>
					 </html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
						<bean:message key="screen.b_bil.billingCurrency"/>&nbsp;<bean:message key="screen.b_bil.colon"/>
					</td>
					<td>
						<html:select name="_b_bilForm" property="cboBillingCurrency">
							<t:defineCodeList id="LIST_CURRENCY" />
							<html:option value="" ><bean:message key="screen.b_bil.blankItem"/></html:option>
							<html:options collection="LIST_CURRENCY" property="id" labelProperty="name"/>
						</html:select>
					</td>
				</tr>
			</table>
		</div>
		<br/>
		<div>
			<html:submit property="forward_search" onclick="searchClick();showLoadingTipWindow();"><bean:message key='screen.b_bil.search'/></html:submit>
			<bs:buttonLink action="/RP_B_BIL_S01_01BL?initFlg=1" value="Reset"/>
			<c:if test="${accessRightBean eq '2' && _b_bilForm.map.manualBillButtonShowFlg eq '1'}">
				<html:submit property="forward_iv" onclick="showLoadingTipWindow();"><bean:message key="screen.b_bil.invoiceNote"/></html:submit>
				<html:submit property="forward_dn" onclick="showLoadingTipWindow();"><bean:message key="screen.b_bil.debitNote"/></html:submit>
				<html:submit property="forward_cn" onclick="showLoadingTipWindow();"><bean:message key="screen.b_bil.creditNote"/></html:submit>
			</c:if>
			<c:if test="${accessRightBean eq '1' or accessRightBean eq '2'}">
				<c:if test="${_b_bilForm.map.totalRow > 0}">
					<!-- #186 Start -->
					<html:submit property="forward_export" onclick="return exportCSV();">
						<bean:message key="screen.b_bil.btnExportReport"/></html:submit>
					<!-- #186 End -->
				</c:if>
				<c:if test="${_b_bilForm.map.totalRow == 0 or _b_bilForm.map.totalRow == null}">
					<html:submit property="forward_export" disabled="true"><bean:message key="screen.b_bil.btnExportReport"/></html:submit>
				</c:if>
			</c:if>
		</div>
		<br/>
		<div class="section">
			<div class="group result">
				<h2><bean:message key="screen.b_bil.searchFound" /> ${_b_bilForm.map.totalRow}</h2>
			</div>
			<div class="pageLink">
				<bean:message key="screen.b_bil.gotoPage"/> <bean:message key="screen.b_bil.colon"/>
				<ts:pageLinks 
	    			id="curPageLinks"
					action="${pageContext.request.contextPath}/B_BIL/RP_B_BIL_S01_02BL.do" 
					name="_b_bilForm" 
					rowProperty="row"
					totalProperty="totalRow" 
					indexProperty="startIndex"
					currentPageIndex="now" 
					totalPageCount="total"
					submit="true" />
				<logic:present name="curPageLinks">  
					<bean:write name="curPageLinks" filter="false"/>
				</logic:present>
			</div>
		</div>
		<div class="section">
		<table  class="resultInfo" cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<td class="header" width="4%"><bean:message key="screen.b_bil.noCol"/></td>
				<td class="header" width="8%"><bean:message key="screen.b_bil.billDate"/></td>
				<td class="header" width="10%"><bean:message key="screen.b_bil.billReference"/></td>
				<td class="header" width="9%" style="padding-left:10px;"><bean:message key="screen.b_bil.customerName"/></td>
				<td class="header" width="5%" style="padding-left:10px;"><bean:message key="screen.b_bil.transType"/></td>
				<td class="header" width="10%" style="padding-left:10px;"><bean:message key="screen.b_bil.billingAccNo"/></td>
				<td class="header" width="4%" style="padding-left:10px;"><bean:message key="screen.b_bil.billCur"/></td>
				<td class="header" width="8%" style="padding-right:10px;text-align:right;"><bean:message key="screen.b_bil.originalAmount"/></td>
				<td class="header" width="7%" style="padding-right:10px;text-align:right;"><bean:message key="screen.b_bil.amountDue"/></td>
				<td class="header" width="7%" style="padding-right:10px;text-align:right;"><bean:message key="screen.b_bil.outstandingAmt"/></td>
				<td class="header" width="8%" style="padding-left:10px;"><bean:message key="screen.b_bil.settlementStatus"/></td>
				<td class="header" width="6%" style="padding-left:10px;"><bean:message key="screen.b_bil.deliveryby"/></td>
				<td class="header" width="5%" style="padding-left:10px;"><bean:message key="screen.b_bil.issueType"/></td>
				<td class="header" width="5%" style="padding-left:10px;"><bean:message key="screen.b_bil.documentStatus"/></td>
				<td class="header" width="5%" style="padding-left:10px;"><bean:message key="screen.b_bil.status"/></td>
			</tr>
			<c:forEach items="${_b_bilForm.map.listReport}" var="item" varStatus="status">
				<tr>
					<td class="defaultNo" align="left">${_b_bilForm.map.startIndex + status.index + 1}</td>
					<td class="defaultText" align="left">		    	
						<fmt:formatDate value="${item.DATE_REQ}" pattern="dd/MM/yyyy"/>
					</td>
					<td class="defaultText" align="left">
						<html:link action="RP_B_BIL_S02_01BL.do?idRef=${item.ID_REF}&mode=view&fromPageFlag=BIL">${item.ID_REF}</html:link>
					</td>
					<td class="defaultText" align="left" style="padding-left:10px;">	
						<c:if test="${'' ne item.SALUTATION}">
							<t:writeCodeValue codeList="LIST_SALUTATION" key="${item.SALUTATION}"/>	
							&nbsp;${item.CUST_NAME}
						</c:if>
						<c:if test="${'' eq item.SALUTATION}">
							${item.CUST_NAME}
						</c:if>
					</td>
					<td class="defaultText" align="left" style="padding-left:10px;">		
						${item.BILL_TYPE}
					</td>
					<td class="defaultText" align="left" style="padding-left:10px;">
					   <html:link action="../B_BAC/RP_B_BAC_S02_02_01BL.do?idBillAccount=${item.BILL_ACC}&idCustPlan=''&fromPage=B_BIL_S01">
					       ${item.BILL_ACC}
					   </html:link>
					</td>
					<td class="defaultText" align="left" style="padding-left:10px;">
						${item.BILL_CURRENCY}				    
					</td> 
					<td class="defaultText" align="left" style="padding-right:10px;text-align:right;">
					      <logic:equal name="item" property="BILL_TYPE" value="CN">		
					          <!-- Add #156 Start-->
					          <c:if test="${_b_bilForm.map.billCnAmtNegative != '1'}">
					          <!-- Add #156 End-->
						          <fmt:formatNumber value="${item.BILL_AMOUNT}" pattern="#,##0.00"/>
						      <!-- Add #156 Start-->
					          </c:if>
					          <!-- Add #156 End-->
					          <!-- Add #156 Start-->
					          <c:if test="${_b_bilForm.map.billCnAmtNegative eq '1'}">
						          -<fmt:formatNumber value="${item.BILL_AMOUNT}" pattern="#,##0.00"/>
					          </c:if>
					          <!-- Add #156 End-->
						  </logic:equal>
						  <logic:notEqual name="item" property="BILL_TYPE" value="CN">     
                              <fmt:formatNumber value="${item.BILL_AMOUNT}" pattern="#,##0.00"/>
                          </logic:notEqual>
					</td>
					<td class="defaultText" align="left" style="padding-right:10px;text-align:right;">		
						<fmt:formatNumber value="${item.TOTAL_AMT_DUE}" pattern="#,##0.00"/>
					</td>
					<td class="defaultText" align="left" style="padding-right:10px;text-align:right;">	
						<logic:equal name="item" property="BILL_TYPE" value="CN">
							<!-- Add #156 Start-->
					        <c:if test="${_b_bilForm.map.billCnAmtNegative != '1'}">
					        <!-- Add #156 End-->
                                <fmt:formatNumber value="${item.OUTSTANDING_AMOUNT}" pattern="#,##0.00"/>
                            <!-- Add #156 Start-->
					        </c:if>
					        <!-- Add #156 End-->
					        <!-- Add #156 Start-->
					        <c:if test="${_b_bilForm.map.billCnAmtNegative eq '1'}">
						        -<fmt:formatNumber value="${item.OUTSTANDING_AMOUNT}" pattern="#,##0.00"/>
					        </c:if>
					        <!-- Add #156 End-->
						</logic:equal>
						<logic:notEqual name="item" property="BILL_TYPE" value="CN">
                            <fmt:formatNumber value="${item.OUTSTANDING_AMOUNT}" pattern="#,##0.00"/>
                        </logic:notEqual>
					</td>
					<td class="defaultText" align="left" style="padding-left:10px;">
						<c:if test="${item.IS_SETTLED eq '1'}">
							<bean:message key="screen.b_bil.fullSettled"/>
						</c:if>
						<c:if test="${item.IS_SETTLED ne '1'}">
							<c:if test="${item.PAID_AMOUNT ne 0}">
								<bean:message key="screen.b_bil.partialSettled"/>
							</c:if>
							<c:if test="${item.PAID_AMOUNT eq 0}">
								<bean:message key="screen.b_bil.outstanding"/>
							</c:if>
						</c:if>
					</td>
					<td class="defaultText" align="left" style="padding-left:10px;">
						<c:if test="${item.DELIVERY_EMAIL eq '1'}">
						 	<bean:message key="screen.b_bil.email"/>/
						</c:if>
						<c:if test="${item.DELIVERY eq '1'}">
							<bean:message key="screen.b_bil.post"/>
						</c:if>
						<c:if test="${item.DELIVERY eq '2'}">
							<bean:message key="screen.b_bil.courier"/>
						</c:if>
						<c:if test="${item.DELIVERY eq '4'}">
							<bean:message key="screen.b_bil.byhand"/>
						</c:if>
					</td>
					<td class="defaultText" align="left" style="padding-left:10px;">
						<!-- #191 Start -->		
						<c:if test="${item.IS_SINGPOST eq '1' and item.IS_MANUAL eq '0'}">
			  		        <%-- <bean:message key="screen.b_bil.issueTypeSingpost"/> --%>
			  		        ${_b_bilForm.map.issueTypeSingpostValue}
			  		    </c:if>
			  		    <c:if test="${item.IS_SINGPOST eq '0' and item.IS_MANUAL eq '0'}">
			  		        <%-- <bean:message key="screen.b_bil.issueTypeAuto"/> --%>
			  		        ${_b_bilForm.map.issueTypeAutoValue}
			  		    </c:if>
			  		    <c:if test="${item.IS_SINGPOST eq '0' and item.IS_MANUAL eq '1'}">
			  		        <%-- <bean:message key="screen.b_bil.issueTypeManual"/> --%>
			  		        ${_b_bilForm.map.issueTypeManualValue}
			  		    </c:if>
			  		    <!-- #191 End -->
					</td>
					<td class="defaultText" align="left" style="padding-left:10px;">		
						${item.IS_CLOSED eq '0'? 'OPEN' : 'CLOSED'}			    
					</td>
					<td class="defaultText <t:writeCodeValue codeList='COLOR_CODE' key='${item.IS_DELETED}'></t:writeCodeValue>" align="left" style="padding-left:10px;">		    	
						${item.IS_DELETED eq '1' ? 'Deleted' : 'Normal'} 
					</td>
			  </tr>
			</c:forEach>
		</table>
		</div>
		<div class="message">
			<ts:messages id="message" message="true"><bean:write name="message"/></ts:messages>
		</div>
		<div class="error">
			<ts:errors/>
		</div>
		</div>
	</ts:form>
</body>
</html>