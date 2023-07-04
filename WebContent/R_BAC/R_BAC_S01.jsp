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
<body id="r"">
<ts:form action="/R_BAC_R01DSP" >
    <div style="width:1200px">
    <html:hidden name="_r_bacForm" property="doSearch" value="N"/>
	<h1 class="title"><bean:message key="screen.r_bac.title"/></h1>
      <div class="section" style="border-top:2px solid #cecece;border-bottom:2px solid #cecece;padding:5px 20px;margin-top:-5px;">
			<table cellpadding="0" cellspacing="0">
			  <colgroup>
			     <col width="150px"/>
			     <col width="10px"/>
			     <col width="350px"/>
			     <col width="150px"/>
			     <col width="10px"/>
			  </colgroup>
				<tr>
					<td align="left">
						<bean:message key="screen.r_bac.lable.custid"/>
					</td>
					<td align="left">
						<bean:message key="screen.r_bac.lable.colon"/> 
					</td>
					<td align="left">
				        <html:text name="_r_bacForm" property="custId" style="width:230px" maxlength="20"></html:text>				
					</td>
					<td align="left">
					    <bean:message key="screen.r_bac.lable.paymentmethod"/>
					</td>
					<td align="left">
					   <bean:message key="screen.r_bac.lable.colon"/>
					</td>
					<td align="left">
					    <t:defineCodeList id="LIST_PAYMENT_METHOD"/>
				        <html:select property="paymentMethod" >
				          <html:option value="" ><bean:message key="screen.r_bac.select.default"/></html:option>
				          <html:optionsCollection name="LIST_PAYMENT_METHOD" value="id" label="name"/>
				        </html:select>
					</td>
				</tr>
				<tr>
					<td align="left">
						<bean:message key="screen.r_bac.lable.custname"/>
					</td>
					<td align="left">
						<bean:message key="screen.r_bac.lable.colon"/>
					</td>
					<td align="left">
						<html:text name="_r_bacForm" property="custName" style="width:230px" maxlength="100"></html:text>
					</td>
                    <td align="left">
                       <bean:message key="screen.r_bac.lable.billcurrency"/>
                    </td>
                    <td align="left">
                        <bean:message key="screen.r_bac.lable.colon"/> 
                    </td>
                    <td nowrap="nowrap" align="left">
                        <t:defineCodeList id="LIST_CURRENCY"/>
				        <html:select property="billCurrency" >
				          <html:option value="" ><bean:message key="screen.r_bac.select.default"/></html:option>
				          <html:optionsCollection name="LIST_CURRENCY" value="id" label="name"/>
				        </html:select>
				    </td>
				</tr>
				<tr>
					<td align="left">
						<bean:message key="screen.r_bac.lable.billacc"/>
					</td>
					<td align="left">
						<bean:message key="screen.r_bac.lable.colon"/>
					</td>
					<td align="left">
						<html:text name="_r_bacForm" property="billAcc" style="width:230px" maxlength="20"></html:text>
					</td>
                    <td align="left">
                        <bean:message key="screen.r_bac.lable.totalamount"/>
                    </td>
                    <td align="left">
                        <bean:message key="screen.r_bac.lable.colon"/>
                    </td>
                    <td align="left" nowrap="nowrap">
  						<html:checkbox name="_r_bacForm" property="totalamount" value="1"></html:checkbox>
                    </td>
				</tr>
			</table>
		</div>
		<br/>
		
		<div>
			<html:submit property="forward_search" onclick="document.forms[0].doSearch.value = 'Y';showLoadingTipWindow();"><bean:message key='screen.r_bac.button.search'/></html:submit>
			<bs:buttonLink action="/R_BAC_R01BL" value="Reset"/>
			<html:hidden name="_r_bacForm" property="accessType"></html:hidden>
			<c:if test="${_r_bacForm.map.accessType eq '1' or _r_bacForm.map.accessType eq '2'}">
				<c:if test="${_r_bacForm.map.totalRow > 0}">
					<html:submit property="forward_export" onclick="showLoadingTipWindow();"><bean:message key="screen.r_bac.button.export"/></html:submit>
				</c:if>
				<c:if test="${_r_bacForm.map.totalRow == 0 or _r_bacForm.map.totalRow == null}">
					<html:submit property="forward_export" disabled="true"><bean:message key="screen.r_bac.button.export"/></html:submit>
				</c:if>
			</c:if>
		</div>
		<br/>
		<div class="section">
			<div class="group result">
				<h2><bean:message key="screen.r_bac.header.searchfound" /><bean:message key="screen.r_bac.lable.colon"/> ${_r_bacForm.map.totalRow}</h2>
			</div>
			<div class="pageLink">
				<bean:message key="screen.r_bac.header.gotopage"/> <bean:message key="screen.r_bac.lable.colon"/>
				<ts:pageLinks 
	    			id="curPageLinks"
					action="${pageContext.request.contextPath}/R_BAC/R_BAC_R02BL.do" 
					name="_r_bacForm" 
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
	    <td class="header" style="padding-left:5px;vertical-align:top;" width="4%"><bean:message key="screen.r_bac.header.no"/></td>
	    <td class="header" style="padding-right:5px;vertical-align:top;" width="11%"><bean:message key="screen.r_bac.lable.custid"/></td>
	    <td class="header" style="padding-right:5px;vertical-align:top;" width="18%"><bean:message key="screen.r_bac.lable.custname"/></td>
	    <td class="header" style="padding-right:5px;vertical-align:top;" width="9%"><bean:message key="screen.r_bac.header.billacc"/></td>
	    <td class="header" style="padding-right:5px;vertical-align:top;" width="10%"><bean:message key="screen.r_bac.lable.paymentmethod"/></td>
        <td class="header" style="padding-right:5px;vertical-align:top;" width="7%"><bean:message key="screen.r_bac.lable.billcurrency"/></td>
        <td class="header" style="padding-right:5px;vertical-align:top;" width="10%"><bean:message key="screen.r_bac.header.totalamountA"/></td>
	    <td class="header" style="padding-right:5px;vertical-align:top; text-align:left;" width="10%"><bean:message key="screen.r_bac.header.actualvalueB"/></td> 
        <td class="header" style="padding-right:5px;vertical-align:top;" width="11%"><bean:message key="screen.r_bac.header.varianceAB"/></td>
	  </tr>
	  <c:forEach items="${_r_bacForm.map.resultList}" var="item" varStatus="status" >
		  <tr>
		    <td class="defaultText" style="padding-left:5px">
		    	${_r_bacForm.map.startIndex + status.index + 1}
		    </td>
		    <td class="defaultText" style="padding-right:5px">
		    	${item.ID_CUST}
		    </td>
		    <td class="defaultText" style="padding-right:5px" >
		    	${item.CUST_NAME}
		    </td>
		    <td class="defaultText" style="padding-right:5px" >
                ${item.ID_BILL_ACCOUNT}
            </td>
		    <td class="defaultText" style="padding-right:5px" >
		    	<t:writeCodeValue codeList="LIST_PAYMENT_METHOD" key="${item.PAYMENT_METHOD}"></t:writeCodeValue>
		    </td>
		    <td class="defaultText" style="padding-right:5px" >
		   		${item.BILL_CURRENCY}
            </td>
		    <td class="defaultText" style="padding-right:5px" >
               <fmt:formatNumber value="${item.TOTAL_AMT_DUE}" pattern="#,##0.00"></fmt:formatNumber>
            </td>
		    <td class="defaultText" style="padding-right:5px; text-align:left;" > 
		    	<fmt:formatNumber value="${item.ACTUAL_AMOUNT}" pattern="#,##0.00"></fmt:formatNumber>
		    </td>
		    <td class="defaultNumeric" style="padding-right:5px;text-align:left;">
		        <fmt:formatNumber value="${item.VARIANCE_AMOUNT}" pattern="#,##0.00"></fmt:formatNumber>
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