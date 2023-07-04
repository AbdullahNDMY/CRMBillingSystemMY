<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="/WEB-INF/bs" prefix="bs" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<html>
<head>
   	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
	<link href="${pageContext.request.contextPath}/R_SOA/css/r_soa.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/R_SOA/js/R_SOA_S01.js"></script>
	<title><bean:message key="screen.r_soa.title"/></title>
</head>
<body id="r" onload="initMain();">
	<ts:form action="/R_SOA_R01DSP" >
		<t:defineCodeList id="LIST_CUSTOMERTYPE" />
		<html:hidden name="_r_soaForm" property="accessType"/>
		<html:hidden name="_r_soaForm" property="printType" styleId="printType"/>
		<html:hidden name="_r_soaForm" property="fullStmtNo" styleId="fullStmtNo"/>
		<html:hidden name="_r_soaForm" property="clickBtnTypeFlg" styleId="clickBtnTypeFlg"/>
		<html:hidden name="_r_soaForm" property="sysdateYYYY" styleId="sysdateYYYY"/>
		<html:hidden name="_r_soaForm" property="sysdateMM" styleId="sysdateMM"/>
		<!-- search condition -->
		<html:hidden name="_r_soaForm" property="hidStatementMonth" value="${_r_soaForm.map.cboStatementMonth}"/>
		<html:hidden name="_r_soaForm" property="hidStatementYear" value="${_r_soaForm.map.tbxStatementYear}"/>
		<html:hidden name="_r_soaForm" property="hidStatementNoFrom" value="${_r_soaForm.map.tbxStatementNoFrom}"/>
		<html:hidden name="_r_soaForm" property="hidCustomerName" value="${_r_soaForm.map.tbxCustomerName}"/>
		<html:hidden name="_r_soaForm" property="hidStatementNoTo" value="${_r_soaForm.map.tbxStatementNoTo}"/>
		<html:hidden name="_r_soaForm" property="hidCustomerId" value="${_r_soaForm.map.tbxCustomerId}"/>
		<html:hidden name="_r_soaForm" property="hidBillingAccount" value="${_r_soaForm.map.tbxBillingAccount}"/>
		<html:hidden name="_r_soaForm" property="hidCustomerType" value="${_r_soaForm.map.cboCustomerType}"/>
		<html:hidden name="_r_soaForm" property="hidPrintStatement" value="${_r_soaForm.map.cboPrintStatement}"/>
		<html:hidden name="_r_soaForm" property="hidStatementTotal" value="${_r_soaForm.map.chkStatementTotal}"/>
		
		<input type="hidden" id="totalRow" value="${_r_soaForm.map.totalRow}"/>
		<div style="width:1000px;">
			<h1 class="title"><bean:message key="screen.r_soa.title"/></h1>
		      <div class="section" style="border-top:2px solid #cecece;border-bottom:2px solid #cecece;padding:5px 20px;margin-top:-5px;">
					<table cellpadding="0" cellspacing="0">
						<col width="16%"/>
						<col width="1%"/>
						<col width="36%"/>
						<col width="2%"/>
						<col width="16%"/>
						<col width="1%"/>
						<col width="28%"/>
						<tr>
							<td align="left">
								<bean:message key="screen.r_soa.statementMonth"/>
								<span style="color: red;"><bean:message key="screen.r_soa.asterisk"/></span>
							</td>
							<td>
								<bean:message key="screen.r_soa.colon"/> 
							</td>
							<td>
								<%-- loading bill month --%>
								<t:defineCodeList id="NO_OF_MONTH"/>
						        <html:select property="cboStatementMonth" styleId="cboStatementMonth" onchange="updateStatementBtnDisabled()">
						          <html:option value="" ><bean:message key="screen.r_soa.select_Month"/></html:option>
						          <html:options collection="NO_OF_MONTH" property="id" labelProperty="name"/>
						        </html:select>
						        <bean:message key="screen.r_soa.statementYear"/>
						        <span style="color: red;"><bean:message key="screen.r_soa.asterisk"/></span>
						        <bean:message key="screen.r_soa.colon"/>
						        <html:text name="_r_soaForm" property="tbxStatementYear" styleId="tbxStatementYear" styleClass="SOAYearTextBox" onchange="updateStatementBtnDisabled()" maxlength="4"></html:text>				
							</td>
							<td>&nbsp;</td>
							<td align="left">
								<bean:message key="screen.r_soa.statementNoFrom"/>
							</td>
							<td>
								<bean:message key="screen.r_soa.colon"/>
							</td>
							<td>
								<html:text name="_r_soaForm" property="tbxStatementNoFrom" styleClass="SOATextBox" maxlength="30"></html:text>
							</td>
						</tr>
						<tr>
							<td align="left">
								<bean:message key="screen.r_soa.customerName"/>
							</td>
							<td>
								<bean:message key="screen.r_soa.colon"/> 
							</td>
							<td>
								<html:text name="_r_soaForm" property="tbxCustomerName" styleClass="SOATextBox" maxlength="100"></html:text>
							</td>
							<td>&nbsp;</td>
							<td align="left">
								<bean:message key="screen.r_soa.statementNoTo"/>
							</td>
							<td>
								<bean:message key="screen.r_soa.colon"/> 
							</td>
							<td>
								<html:text name="_r_soaForm" property="tbxStatementNoTo" styleClass="SOATextBox" maxlength="30"></html:text>
							</td>
						</tr>
						<tr>
							<td align="left">
								<bean:message key="screen.r_soa.customerID"/>
							</td>
							<td>
								<bean:message key="screen.r_soa.colon"/> 
							</td>
							<td>
								<html:text name="_r_soaForm" property="tbxCustomerId" styleClass="SOATextBox" maxlength="20"></html:text>
							</td>
							<td>&nbsp;</td>
							<td align="left">
								<bean:message key="screen.r_soa.billAcount"/>
							</td>
							<td>
								<bean:message key="screen.r_soa.colon"/> 
							</td>
							<td>
								<html:text name="_r_soaForm" property="tbxBillingAccount" styleClass="SOATextBox" maxlength="20"></html:text>
							</td>
						</tr>
						<tr>
							<td align="left">
								<bean:message key="screen.r_soa.customerType"/>
							</td>
							<td>
								<bean:message key="screen.r_soa.colon"/> 
							</td>
							<td>
								<html:select name="_r_soaForm" property="cboCustomerType" styleClass="SOATextBox">
						    		<html:option value=""><bean:message key="screen.r_soa.selectone"/></html:option>
						    		<c:forEach items="${LIST_CUSTOMERTYPE}" var="item">
										<c:if test="${item.id ne 'BOTH'}">
											<html:option value="${item.id}" >${item.name}</html:option>
										</c:if>
									</c:forEach>
						    	</html:select>
							</td>
							<td>&nbsp;</td>
							<td align="left">
								<bean:message key="screen.r_soa.printStatement"/>
							</td>
							<td>
								<bean:message key="screen.r_soa.colon"/> 
							</td>
							<td>
								<html:select name="_r_soaForm" property="cboPrintStatement" styleClass="SOATextBox">
						    		<html:option value="" ><bean:message key="screen.r_soa.selectone"/></html:option>
						    		<html:option value="1"><bean:message key="screen.r_soa.selectedOnly"/></html:option>
						    		<html:option value="0"><bean:message key="screen.r_soa.notSelected"/></html:option>
						    	</html:select>
							</td>
						</tr>
					<tr>
						<td align="left"><bean:message
								key="screen.r_soa.billingCurrency" /></td>
						<td><bean:message key="screen.r_soa.colon" /></td>
						<td>
							<html:select name="_r_soaForm"
								property="cboBillingCurrency" styleClass="SOATextBox">
								<option value="">
									<bean:message key="screen.r_soa.selectone" />
								</option>
								<%-- loading bill currency --%>
								<t:defineCodeList id="LIST_CURRENCY" />
								<html:options collection="LIST_CURRENCY" property="id"
									labelProperty="name" />
							</html:select>
						</td>
						<td>&nbsp;</td>
						<td align="left"><bean:message
								key="screen.r_soa.statementTotal" /></td>
						<td><bean:message key="screen.r_soa.colon" /></td>
						<td><html:checkbox value="1" name="_r_soaForm"
								property="chkStatementTotal"></html:checkbox></td>
					</tr>
					<tr>						
						<td align="left"><bean:message
								key="screen.r_soa.paymentCurrency" /></td>
						<td><bean:message key="screen.r_soa.colon" /></td>
						<td><html:select property="cboPaymentCurrency"
								name="_r_soaForm" styleClass="SOATextBox">
								<option value="">
									<bean:message key="screen.r_soa.selectone" />
								</option>
								<%-- loading payment currency --%>									
								<c:forEach items="${_r_soaForm.map.cboPaymentCurrencyArray}" var="item" varStatus="s">
									<html:option value="${item}" >${item}</html:option>
								</c:forEach>								
							</html:select>
							<c:forEach items="${_r_soaForm.map.cboPaymentCurrencyArray}"
								var="item" varStatus="s">
								<html:hidden name="_r_soaForm"
									property="cboPaymentCurrencyArray[${s.count-1}]"
									value="${item}" />
							</c:forEach> 
						</td>
						<td align="left">&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>
				</div>
				<br/>
				<div>
					<html:submit property="forward_search" onclick="searchClick('search');showLoadingTipWindow();"><bean:message key='screen.r_soa.search'/></html:submit>
					<bs:buttonLink action="/R_SOA_S01InitBL" value="Reset"/>
					<c:if test="${_r_soaForm.map.accessType eq '1' or _r_soaForm.map.accessType eq '2'}">
						<c:if test="${_r_soaForm.map.totalRow > 0}">
							<html:submit property="forward_print" onclick="printClick();showLoadingTipWindow();"><bean:message key="screen.r_soa.print"/></html:submit>
							<html:submit property="forward_printAll" onclick="printAllClick();showLoadingTipWindow();"><bean:message key="screen.r_soa.printAll"/></html:submit>
						</c:if>
						<c:if test="${_r_soaForm.map.totalRow == 0 or _r_soaForm.map.totalRow == null}">
							<html:submit property="forward_print" disabled="true"><bean:message key="screen.r_soa.print"/></html:submit>
							<html:submit property="forward_printAll" disabled="true"><bean:message key="screen.r_soa.printAll"/></html:submit>
						</c:if>
					</c:if>
                    <c:if test="${_r_soaForm.map.accessType eq '2'}">
                        <c:if test="${_r_soaForm.map.totalRow > 0}">
                            <html:submit styleId="updateStatement" property="forward_updateStatement" onclick="searchClick('updateStatement')"><bean:message key="screen.r_sal.updateStatement"/></html:submit>
                        </c:if>
                        <c:if test="${_r_soaForm.map.totalRow == 0 or _r_soaForm.map.totalRow == null}">
                            <html:submit styleId="updateStatement" property="forward_updateStatement" disabled="true"><bean:message key="screen.r_sal.updateStatement"/></html:submit>
                        </c:if>
                    </c:if>
				</div>
				<br/>
				<div class="section">
					<div class="group result">
						<h2><bean:message key="screen.r_soa.searchFound" /><bean:message key="screen.r_soa.colon"/>${_r_soaForm.map.totalRow}</h2>
					</div>
					<div class="pageLink">
						<bean:message key="screen.r_soa.gotoPage"/> <bean:message key="screen.r_soa.colon"/>
						<ts:pageLinks 
			    			id="curPageLinks"
							action="${pageContext.request.contextPath}/R_SOA/R_SOA_S01SearchBL.do?clickBtnTypeFlg=pageClick" 
							name="_r_soaForm" 
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
				<col width="3%"/>
				<col width="10%"/>
				<col width="12%"/>
				<col width="20%"/>
				<col width="10%"/>
				<col width="13%"/>
				<col width="12%"/>
				<col width="10%"/>
				<col width="10%"/>
				<tr>
				    <td class="header" style="padding-left: 0.05in;"><input type="checkbox" name="selectAll" id="selectAll"/></td>
				    <td class="header" style="padding-left: 0.05in;"><bean:message key="screen.r_sal.date"/></td>
				    <td class="header" style="padding-left: 0.05in;"><bean:message key="screen.r_soa.customerID"/></td>
				    <td class="header" style="padding-left: 0.05in;"><bean:message key="screen.r_soa.customerName"/></td>
				    <td class="header" style="padding-left: 0.05in;"><bean:message key="screen.r_soa.customerType"/></td>
				    <td class="header" style="padding-left: 0.05in;"><bean:message key="screen.r_soa.billAcount"/></td>
				    <td class="header" style="padding-left: 0.05in;"><bean:message key="screen.r_sal.statementNo"/></td>
				    <td class="header" style="padding-left: 0.05in;"><bean:message key="screen.r_soa.billingCurrency"/></td>
				    <td class="header" style="padding-left: 0.05in;"><bean:message key="screen.r_soa.paymentCurrency"/></td>
				    <td class="rightHeader" style="padding-right: 0.05in;"><bean:message key="screen.r_sal.total"/></td>
				</tr>
				<c:set var="allStmtNo"></c:set>
				<bean:define id="isSelectedFlg" value="0"/>
				<c:forEach items="${_r_soaForm.map.listStatement}" var="item" varStatus="status">
		  			<tr>
		  				<td class="defaultText" style="padding-left: 0.05in;">
		  					<c:forEach items="${_r_soaForm.map.selectedStatementNo}" var="selectedStmtNo">
								<c:if test="${selectedStmtNo==item.ID_STMT}">
									<input type="checkbox" name="selectedStatementNo" value="${item.ID_STMT}" checked="checked"/>											
									<bean:define id="isSelectedFlg" value="1"/>
								</c:if>
							</c:forEach>
							<c:if test="${isSelectedFlg!=1}">
								<bean:define id="isSelectedFlg" value="1"/>
			  					<html:checkbox property="selectedStatementNo" value="${item.ID_STMT}"></html:checkbox>
							</c:if>
							<bean:define id="isSelectedFlg" value="0"/>
							<c:set var="allStmtNo" value="${allStmtNo},${item.ID_STMT}"></c:set>
		  				</td>
		  				<td class="defaultText" style="padding-left: 0.05in;">
		  					${item.STMT_DATE}
		  				</td>
		  				<td class="defaultText" style="padding-left: 0.05in;">
		  					${item.ID_CUST}
		  				</td>
		  				<td class="defaultText" style="padding-left: 0.05in;width:190px;word-wrap: break-word;white-space : normal">
		  					${item.CUST_NAME}
		  				</td>
		  				<td class="defaultText" style="padding-left: 0.05in;">
		  					${item.CUSTOMER_TYPE}
		  				</td>
		  				<td class="defaultText" style="padding-left: 0.05in;">
		  					${item.CUST_ACC_NO}
		  				</td>
		  				<td class="defaultText" style="padding-left: 0.05in;">
		  					${item.ID_STMT}
		  				</td>
		  				<td class="defaultText" style="padding-left: 0.05in;">
		  					${item.STMT_CURRENCY}
		  				</td>
		  				<td class="defaultText" style="padding-left: 0.05in;">
		  					${item.EXPORT_CURRENCY}
		  				</td>
		  				<td class="defaultNumeric" style="padding-right: 0.05in;">
		  					<fmt:formatNumber value="${item.STMT_TOTAL}" pattern="#,##0.00"></fmt:formatNumber>
		  				</td>
		  			</tr>
		  		</c:forEach>
			</table>
			</div>
			<!-- save old statement -->
			<div style="display:none;">
				<bean:define id="isSelectedFlg" value="0"/>
				<c:forEach items="${_r_soaForm.map.selectedStatementNo}" var="selectedStmtNo">
					<c:forEach items="${allStmtNo}" var="all">
						<c:if test="${selectedStmtNo==all}">
							<bean:define id="isSelectedFlg" value="1"/>
						</c:if>
					</c:forEach>
					<c:choose>
						<c:when test="${isSelectedFlg==1}">
							<bean:define id="isSelectedFlg" value="0"/>
						</c:when>
						<c:otherwise>
							<input type="checkbox" name="selectedStatementNo" value="${selectedStmtNo}" checked="checked"/>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>	
			<div class="message" style="width:850px;word-wrap: break-word;white-space : normal">
				<ts:messages id="message" message="true"><bean:write name="message"/></ts:messages>
			</div>
			<div class="error" style="width:850px;word-wrap: break-word;white-space : normal">
				<html:messages id="message">
					<bean:write name="message"/><br/>
				</html:messages>
			</div>
			<div class="error" style="width:850px;word-wrap: break-word;white-space : normal">
				<c:if test="${not empty _r_soaForm.map.lastMsg}">
					${_r_soaForm.map.lastMsg}
				</c:if>
			</div>
			</div>
	</ts:form>
</body>
</html>