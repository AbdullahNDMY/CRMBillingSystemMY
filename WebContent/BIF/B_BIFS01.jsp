<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="nttdm.bsys.common.fw.BillingSystemUserValueObject"%>
<%@page import="nttdm.bsys.common.util.CommonUtils"%>
<%@ taglib uri="/WEB-INF/bs" prefix="bs" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
   	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common_01.css"/>
	<link href="<%=request.getContextPath()%>/BIF/css/b_bifs01.css" rel="stylesheet" type="text/css" /> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   	<script type="text/javascript" src="<%=request.getContextPath()%>/BIF/js/b_bifs01.js"></script>
   	<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
   	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
	<title>Insert title here</title>
</head>
<script type="text/javascript">
	function gotoTest() {
		document.forms[0].action = "testSCR.do";
		document.forms[0].submit();
	}
	
function genarateBIF() {
	document.forms[0].action = '<%=request.getContextPath()%>/B_CPM/B_CPM_S04InitBL.do?inputSearchPlan.customerId=&idScreen=B_BIF_S01';
	//document.forms[1].from.value = "B-BIF-S01";
	document.forms[0].submit();
}
</script>
<body id="b" onload="initMain();initPage();">
<!-- check access right START -->
<%
 	BillingSystemUserValueObject uvo = (BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT");
 	String accessRight = CommonUtils.getAccessRight(uvo, "B-BIF");
 %>
 <bean:define id="accessRightBean" value="<%= accessRight%>"></bean:define>
<logic:notEqual value="2" name="accessRightBean">
<script type="text/javascript">
	window.location = '<%=request.getContextPath()%>/C_CMN001/C_CMN001S06SCR.do';
</script>
</logic:notEqual>
<!-- check access right END -->
<t:defineCodeList id="LIST_TRANSACTIONTYPE" />
<t:defineCodeList id="DOC_STATUS_LIST" />
<div class="main">
<ts:form action="/B_BIFS01Action" >
	<html:hidden property="idRef"/>
	<html:hidden property="from"/>
	<table class="subHeader" cellpadding="0" cellspacing="0">
	  <tr>
	    <td ><bean:message key="screen.b_bif.title"/></td> 
	  </tr>
	</table> 
	<table class = "inputInfo" cellpadding="0" cellspacing="0">

		<tr>
			<td class="col1Top" width="15%"><bean:message key="screen.b_bif.customerName"/><bean:message key="screen.b_bif.colon"/> 
			</td>
			<td class="col2Top" width="30%"><html:text name="_b_bifForm" property="cust_name" styleClass="QCSTextBox" style="width:210px;"></html:text>
			</td>
			<td class="col3Top"><bean:message key="screen.b_bif.bifDateBIF"/><bean:message key="screen.b_bif.colon"/>
			</td>
			<td class="col4Top">
				<html:text property="start_date" name="_b_bifForm" readonly="true" style="width:70px;"/>
	                            <t:inputCalendar for="start_date" format="dd/MM/yyyy"/>&nbsp; <bean:message key="screen.b_bif._"/>&nbsp;
	            <html:text property="end_date" name="_b_bifForm" readonly="true" style="width:70px;"/>
	                            <t:inputCalendar for="end_date" format="dd/MM/yyyy"/> 
			</td>
		</tr>
		<tr>
			<td class="colRight"><bean:message key="screen.b_bif.bifReference"/><bean:message key="screen.b_bif.colon"/>
			</td>
			<td class="colLeft"><html:text name="_b_bifForm" property="id_ref" styleClass="QCSTextBox" style="width:210px;"></html:text>
			</td>
			<td class="colRight"><bean:message key="screen.b_bif.type"/><bean:message key="screen.b_bif.colon"/>
			</td>
			<td class="colLeft">
				<html:select property="bif_type" styleClass="QCSTextBox" style="width:210px;">
					<option value=""><bean:message key="screen.b_bif02.selectone"/></option>
					<c:forEach items="${LIST_TRANSACTIONTYPE}" var="item">
						<c:if test="${item.id ne 'NT'}">
							<c:choose>
						   		<c:when test="${_b_bifForm.bif_type==item.id}">
									<option value="${item.id}" selected="selected">${item.name}</option>
								</c:when>
								<c:otherwise>
								    <option value="${item.id}">${item.name}</option>
								</c:otherwise>
							</c:choose>
						</c:if>
						<c:if test="${item.id eq 'NT'}">
							<c:if test="${nonTaxInvoiceShowFlg eq '1'}"> 
								<c:choose>
									<c:when test="${_b_bifForm.bif_type == item.id}">
										<option value="${item.id}" selected="selected">${item.name}</option>
									</c:when>
									<c:otherwise>
								    	<option value="${item.id}">${item.name}</option>
									</c:otherwise>
								</c:choose>
							</c:if>
						</c:if>
					</c:forEach>
					<%-- <html:options collection="LIST_TRANSACTIONTYPE" property="id" labelProperty="name"/> --%>
				</html:select>
			</td>
		</tr>
		<tr>
			<td class="colRight"><bean:message key="screen.b_bif.subscriptionID"/><bean:message key="screen.b_bif.colon"/>
			</td>
			<td class="colLeft"><html:text name="_b_bifForm" property="subscription_id" styleClass="QCSTextBox" style="width:210px;"></html:text>
			</td>
			<td class="colRight"><bean:message key="screen.b_bif.status"/><bean:message key="screen.b_bif.colon"/></td>
			<td class="colLeft">
				<html:select property="status" styleClass="QCSTextBox" style="width:210px;">
					<option value=""><bean:message key="screen.b_bif02.selectone"/></option>
					<html:options collection="DOC_STATUS_LIST" property="id" labelProperty="name"/>
				</html:select>
			</td>
		</tr>
		<tr>
			<td class="colRight"><bean:message key="screen.b_bif.billingAccount"/><bean:message key="screen.b_bif.colon"/>
			</td>
			<td class="colLeft"><html:text name="_b_bifForm" property="billing_account" styleClass="QCSTextBox" style="width:210px;"></html:text>
			</td>
			<td class="colRight" width="15%"><bean:message key="screen.b_bif.consultantName"/><bean:message key="screen.b_bif.colon"/>
			</td>
			<td class="colLeft" width="40%"><html:text name="_b_bifForm" property="user_name" styleClass="QCSTextBox" style="width:210px;"></html:text>
			</td>
		</tr>
		<tr>
			<td class="colRight"><bean:message key="screen.b_bif.jobNo"/><bean:message key="screen.b_bif.colon"/>
			</td>
			<td class="colLeft"><html:text name="_b_bifForm" property="job_no" styleClass="QCSTextBox" style="width:210px;"></html:text>
			</td>
			<td class="colRight" width="15%"><bean:message key="screen.b_bif.preparedBy"/><bean:message key="screen.b_bif.colon"/>
			</td>
			<td class="colLeft" width="40%"><html:text name="_b_bifForm" property="prepared_by" styleClass="QCSTextBox" style="width:210px;"></html:text>
			</td>
		</tr>
		<tr>
		    <td class="colBottom" colspan="4">&nbsp;</td>
		</tr>
	</table>
	<table class="buttonGroup" cellpadding="0" cellspacing="0">
  	<tr>
		<td>
			<html:submit property="forward_search" onclick="searchData();showLoadingTipWindow();"><bean:message key='screen.b_bif.search'/></html:submit>
			<bs:buttonLink action="/B_BIFS01InitBL" value="Reset"/>
			<html:button property="forward_new" onclick="javascript: genarateBIF();"><bean:message key="screen.b_bif.new"/></html:button>
			<!-- <button onclick="javascript: gotoTest();">TEST</button>&nbsp;  --> 
			<ts:submit value='forward_view' property="forward_view" style="visibility:hidden"/> 
		</td>	
	</tr>
	</table> 
	<table class="searchResultNo" cellpadding="0" cellspacing="0">
	  	<tr>
			<td style="font-size:12pt;">
				<bean:message key="screen.b_bif.searchFound"/> <bean:message key="screen.b_bif.colon"/>
				<c:if test="${_b_bifForm.totalCount != -1}">
					<bean:write name="_b_bifForm" property="totalCount"/>
				</c:if>
			</td>	
		</tr>
	</table>  
	<table class="pageLink" cellpadding="0" cellspacing="0">
		<tr>
			<td><bean:message key="screen.b_bif.gotoPage"/> <bean:message key="screen.b_bif.colon"/>
				<ts:pageLinks 
		    		id="curPageLinks"
					action="${pageContext.request.contextPath}/BIF/B_BIFS01BL.do" 
					name="_b_bifForm" 
					rowProperty="row"
					totalProperty="totalCount" 
					indexProperty="startIndex"
					currentPageIndex="now" 
					totalPageCount="total"
					submit="true" />
				<logic:present name="curPageLinks">
					<bean:write name="curPageLinks" filter="false"/>
				</logic:present>
			</td>
		</tr>
	</table> 
	<table  class="resultInfo" cellpadding="0" cellspacing="0">
	  <tr>
	    <td class="header" width="5%"><bean:message key="screen.b_bif.noCol"/></td>
	    <td class="header" width="8%"><bean:message key="screen.b_bif.bifDateBIF"/></td>
	    <td class="header" width="10%"><bean:message key="screen.b_bif.bifReference"/></td>
	    <td class="header" width="12%"><bean:message key="screen.b_bif.customerName"/></td>
	    <td class="header" width="10%"><bean:message key="screen.b_bif.billingAccount"/></td>
	    <td class="header" width="12%"><bean:message key="screen.b_bif.consultantName"/></td>
	    <td class="header" width="10%"><bean:message key="screen.b_bif.type"/></td>
	    <td class="header" width="10%"><bean:message key="screen.b_bif.status"/></td> 
	    <td class="header" width="15%"><bean:message key="screen.b_bif.pendingApprovalBy"/></td>
	    <td class="header" width="10%"><bean:message key="screen.b_bif.preparedBy"/></td> 
	  </tr>
	  <logic:present property="arr_bif" name="_b_bifForm">
		  <logic:iterate id="bif" name="_b_bifForm" property="arr_bif" >
			  <tr>
			    <td class="defaultNo"><bean:write name="bif" property="row_num"/></td>
			    <td class="defaultText">		    	
			    	<bean:write name="bif" property="date_req" format="dd/MM/yyyy"/>
			    </td>
			    <td class="defaultText">
			    	<A href="javascript: linkClick('<bean:write name="bif" property="id_ref"/>');"><bean:write name="bif" property="id_ref"/></A>
			    </td>
			    <td class="defaultText">    
				    <bean:write name="bif" property="cust_name"/>
			    </td>
			     <td class="defaultText">
			     	<c:forEach items="${bif.billing_account}" var="item" varStatus="status">
			     		<c:if test="${not empty status.current}">
			     			<A href="javascript: linkBillAccountClick('${status.current}','<%=request.getContextPath()%>');">${fn:trim(status.current)}</A><c:if test="${status.last==false}">,</c:if>
			     		</c:if>
			     		<c:if test="${empty status.current}">
			     			-
			     		</c:if>
			     	</c:forEach> 
			    </td>
			    <td class="defaultText">
				    <bean:write name="bif" property="user_name"/>
			    </td>
			    <td class="defaultText">					    
				    <t:writeCodeValue codeList="LIST_TRANSACTIONTYPE" key="${bif.bif_type}"/>
			    </td>
			    <td class="defaultText">
			    	<t:writeCodeValue codeList="DOC_STATUS_LIST" key="${bif.id_status}"/>
			    </td>
			    <td class="defaultText">
			    	<c:choose>
				    	<c:when test="${bif.id_status eq 'DS1' or bif.id_status eq 'DS2'}">
				    		<bean:write name="bif" property="id_approver_name"/>
				    	</c:when>
				    	<c:otherwise>
				    		-
				    	</c:otherwise>
			    	</c:choose>
			    </td>
			     <td class="defaultText">    
				    <bean:write name="bif" property="prepared_by"/>
			    </td> 
			  </tr>
		   </logic:iterate>
	   </logic:present>
	</table> 
</ts:form> 
</div>
	<div class="message">
		<logic:notEmpty name="message" scope="request">
			<bean:message name="message" scope="request"/>
		</logic:notEmpty>
	</div>
	 <div class="message" style="font-size:16px;font-family: 'Calibri';">
            <html:messages id="message" message="true">
                <bean:write name="message" />
            </html:messages>
        </div>
</body>
</html>