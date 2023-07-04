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
	<script type="text/javascript" src="js/r_acr.js"></script>
	<title><bean:message key="screen.r_acr.title"/></title>
</head>
<body id="r" onload="initMain();">
<ts:form action="/R_ACR_R01DSP" >
    <div style="width:1500px">
	<h1 class="title"><bean:message key="screen.r_acr.title"/></h1>
      <div class="section" style="border-top:2px solid #cecece;border-bottom:2px solid #cecece;padding:5px 20px;margin-top:-5px;">
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td align="left">
						<bean:message key="screen.r_acr.usageMonth"/>
						<span style="color: red;">*</span>
					</td>
					<td>
						<bean:message key="screen.r_acr.colon"/> 
					</td>
					<td>
						<%-- loading bill month --%>
						<t:defineCodeList id="NO_OF_MONTH"/>
				        <html:select property="cboUsageMonth" >
				          <html:option value="" ><bean:message key="screen.r_acr.select_Month"/></html:option>
				          <html:options collection="NO_OF_MONTH" property="id" labelProperty="name"/>
				        </html:select>
				        <bean:message key="screen.r_acr.slash"/><bean:message key="screen.r_acr.blank"/>
				        <bean:message key="screen.r_acr.usageYear"/><span style="color: red;">*</span>
				        <bean:message key="screen.r_acr.colon"/>
				        <html:text name="_r_acrForm" property="tbxUsageYear" styleClass="ACRYearTextBox" maxlength="4"></html:text>				
					</td>
					<td>
					    <bean:message key="screen.r_acr.blank"/>
					</td>
					<td align="left">
					   <bean:message key="screen.r_acr.serviceStartDate"/>
					</td>
					<td>
                        <bean:message key="screen.r_acr.colon"/> 
                    </td>
					<td>
					   <html:text property="tbxServiceStartFrom" name="_r_acrForm" readonly="true" style="width:70px;"/>
                        <t:inputCalendar for="tbxServiceStartFrom" format="dd/MM/yyyy"/>
                        &nbsp;<bean:message key="screen.r_acr._"/>&nbsp;
                        <html:text property="tbxServiceStartTo" name="_r_acrForm" readonly="true" style="width:70px;"/>
                        <t:inputCalendar for="tbxServiceStartTo" format="dd/MM/yyyy"/> 
					</td>
				</tr>
				<tr>
					<td align="left">
						<bean:message key="screen.r_acr.custId"/>
					</td>
					<td>
						<bean:message key="screen.r_acr.colon"/>
					</td>
					<td>
						<html:text name="_r_acrForm" property="tbxCustomerId" styleClass="ACRTextBox" maxlength="100"></html:text>
					</td>
					<td>
                        <bean:message key="screen.r_acr.blank"/>
                    </td>
                    <td align="left">
                       <bean:message key="screen.r_acr.serviceStatus"/>
                    </td>
                    <td>
                        <bean:message key="screen.r_acr.colon"/> 
                    </td>
                    <td nowrap="nowrap" align="left">
                        <nested:iterate property="tbxServiceStatus" id="hidServStatus">
                            <input type="hidden" name="hidServStatus" class="hidServStatus" value="<bean:write name="hidServStatus"/>"/>
                        </nested:iterate>
					    <table>
                           <tr>
                               <td>
	                               <nested:checkbox property="tbxServiceStatus" styleClass="searchServiceStatus" value="PS1">
	                                   <bean:message key="screen.r_acr.checkbox.draft"/>
	                               </nested:checkbox>
                               </td>
                               <td>
                                   <nested:checkbox property="tbxServiceStatus" styleClass="searchServiceStatus" value="PS3">
                                       <bean:message key="screen.r_acr.checkbox.active"/>
                                   </nested:checkbox>      
                               </td>
                               <td>
                                   <nested:checkbox property="tbxServiceStatus" styleClass="searchServiceStatus" value="PS7">
                                       <bean:message key="screen.r_acr.checkbox.terminated"/>
                                   </nested:checkbox>  
                               </td>
                               <td>
                                   <nested:checkbox property="tbxServiceStatus" styleClass="searchServiceStatus" value="PS6">
                                       <bean:message key="screen.r_acr.checkbox.suspended"/>
                                   </nested:checkbox>  
                               </td>
                           </tr>
                        </table>
				    </td>
				</tr>
				<tr>
					<td align="left">
						<bean:message key="screen.r_acr.customerName"/>
					</td>
					<td>
						<bean:message key="screen.r_acr.colon"/>
					</td>
					<td>
						<html:text name="_r_acrForm" property="tbxCustomerName" styleClass="ACRTextBox" maxlength="100"></html:text>
					</td>
					<td>
                        <bean:message key="screen.r_acr.blank"/>
                    </td>
                    <td align="left">
                        <bean:message key="screen.r_acr.currency"/>
                    </td>
                    <td>
                        <bean:message key="screen.r_acr.colon"/>
                    </td>
                    <td>
                        <%-- loading currency --%>
                        <t:defineCodeList id="LIST_CURRENCY" />
                        <html:select name="_r_acrForm" property="cboCurrency">
                            <html:option value="" ><bean:message key="screen.r_acr.label_blank"/></html:option>
                            <html:options collection="LIST_CURRENCY" property="id" labelProperty="name"/>
                        </html:select>  
                    </td>
				</tr>
				<tr>
				    <td align="left">
						<bean:message key="screen.r_acr.serviceName"/>
					</td>
					<td>
						<bean:message key="screen.r_acr.colon"/>
					</td>
					<td>
						<html:text name="_r_acrForm" property="tbxServiceName" styleClass="ACRTextBox" maxlength="300"></html:text>
					</td>
					<td>
                        <bean:message key="screen.r_acr.blank"/>
                    </td>
                    <td align="left">
                       <bean:message key="screen.r_acr.importedAmount"/>
                    </td>
                    <td>
                        <bean:message key="screen.r_acr.colon"/> 
                    </td>
                    <td>
                        <html:checkbox value="1" name="_r_acrForm" property="tbxImportedAmount"></html:checkbox>
                    </td>
				</tr>
				<tr>
				    <td align="left">
                        <bean:message key="screen.r_acr.subID"/>
                    </td>
                    <td>
                        <bean:message key="screen.r_acr.colon"/>
                    </td>
                    <td>
                        <html:text name="_r_acrForm" property="tbxSubID" styleClass="ACRTextBox" maxlength="20"></html:text>
                    </td>
					<td>
                        <bean:message key="screen.r_acr.blank"/>
                    </td>
                    <td align="left">
                       <bean:message key="screen.r_acr.unbillAmount"/>
                    </td>
                    <td>
                        <bean:message key="screen.r_acr.colon"/> 
                    </td>
                    <td>
                        <html:checkbox value="1" name="_r_acrForm" property="tbxUnbillAmount"></html:checkbox>
                    </td>
				</tr>
			</table>
		</div>
		<br/>
		
		<div>
			<html:submit property="forward_search" onclick="searchClick();showLoadingTipWindow();"><bean:message key='screen.r_acr.search'/></html:submit>
			<bs:buttonLink action="/R_ACR_R01BL" value="Reset"/>
			<html:hidden name="_r_acrForm" property="accessType"></html:hidden>
			<c:if test="${_r_acrForm.map.accessType eq '1' or _r_acrForm.map.accessType eq '2'}">
				<c:if test="${_r_acrForm.map.totalRow > 0}">
					<html:submit property="forward_export" onclick="showLoadingTipWindow()"><bean:message key="screen.r_acr.exportReport"/></html:submit>
				</c:if>
				<c:if test="${_r_acrForm.map.totalRow == 0 or _r_acrForm.map.totalRow == null}">
					<html:submit property="forward_export" disabled="true"><bean:message key="screen.r_acr.exportReport"/></html:submit>
				</c:if>
			</c:if>
		</div>
		<br/>
		<div class="section">
			<div class="group result">
				<h2><bean:message key="screen.r_acr.searchFound" /><bean:message key="screen.r_acr.colon"/> ${_r_acrForm.map.totalRow}</h2>
			</div>
			<div class="pageLink">
				<bean:message key="screen.r_acr.gotoPage"/> <bean:message key="screen.r_acr.colon"/>
				<ts:pageLinks 
	    			id="curPageLinks"
					action="${pageContext.request.contextPath}/R_ACR/R_ACR_R02BL.do" 
					name="_r_acrForm" 
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
	<div class="resultTableTd">
	<table class="resultInfo" cellpadding="0" cellspacing="0" width="100%">
	  <tr>
	    <td class="header" style="padding-left:5px;vertical-align:bottom;" width="5%"><bean:message key="screen.r_acr.usageMonthCol"/></td>
	    <td class="header" style="padding-right:5px;vertical-align:bottom;" width="10%"><bean:message key="screen.r_acr.custId"/></td>
	    <td class="header" style="padding-right:5px;vertical-align:bottom;" width="14%"><bean:message key="screen.r_acr.customerNameCol"/></td>
	    <td class="header" style="padding-right:5px;vertical-align:bottom;" width="10%"><bean:message key="screen.r_acr.subID"/></td>
	    <td class="header" style="padding-right:5px;vertical-align:bottom;" width="15%"><bean:message key="screen.r_acr.serviceNameCol"/></td>
        <td class="header" style="padding-right:5px;vertical-align:bottom;" width="6%"><bean:message key="screen.r_acr.serviceStatusCol"/></td>
        <td class="header" style="padding-right:5px;vertical-align:bottom;" width="6%"><bean:message key="screen.r_acr.serviceStartDateCol"/></td>
	    <td class="header" style="padding-right:5px;vertical-align:bottom; text-align:center;" width="4%"><bean:message key="screen.r_acr.currencyCol"/></td>
	    <td class="rightHeader" style="padding-right:5px;vertical-align:bottom;" width="8%"><bean:message key="screen.r_acr.unbillAmountCol"/></td>
        <td class="rightHeader" style="padding-right:8px;vertical-align:bottom;" width="7%"><bean:message key="screen.r_acr.importedAmountCol"/></td>
        <td class="header" style="padding-right:5px;vertical-align:bottom;" width="15%"><bean:message key="screen.r_acr.itemDesc"/></td>
	  </tr>
	  <c:forEach items="${_r_acrForm.map.listAccrualReport}" var="item" varStatus="status">
		  <tr>
		    <td class="defaultText" style="padding-left:5px">
		    	${item.DATE_REQ}
		    </td>
		    <td class="defaultText" style="padding-right:5px">
		    	${item.ID_CUST}
		    </td>
		    <td class="defaultText" style="padding-right:5px" >
		    	${item.CUST_NAME}
		    </td>
		    <td class="defaultText" style="padding-right:5px" >
                ${item.ID_SUB_INFO}
            </td>
		    <td class="defaultText" style="padding-right:5px" >
		    	${item.SVC_DESC}
		    </td>
		    <td class="defaultText" style="padding-right:5px" >
                ${item.SERVICES_STATUS}
            </td>
		    <td class="defaultText" style="padding-right:5px" >
                ${item.SVC_START}
            </td>
		    <td class="defaultText" style="padding-right:5px; text-align:center;" > 
		    	${item.BILL_CURRENCY}
		    </td>
		    <td class="defaultNumeric" style="padding-right:5px">
		        <fmt:formatNumber value="${item.TOTAL_AMOUNT}" pattern="#,##0.00"></fmt:formatNumber>
		    </td>
		    <td class="defaultNumeric" style="padding-right:8px">
		        <c:if test="${not empty item.INVOICE_TOTAL}">
                    <fmt:formatNumber value="${item.INVOICE_TOTAL}" pattern="#,##0.00"></fmt:formatNumber>
                </c:if>
                <c:if test="${empty item.INVOICE_TOTAL}">
                    -
                </c:if>
            </td>
            <td class="defaultText" style="word-wrap: break-word;white-space : normal;padding-right:5px">
                ${item.ITEM_DESC}
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