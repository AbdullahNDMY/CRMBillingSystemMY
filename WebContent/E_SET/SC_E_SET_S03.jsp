<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">

<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title></title>
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
		<link href="css/e_set.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/common.js"></script>
   		<script type="text/javascript">
   			$(document).ready(function(){
   				function fncClose(){
   					window.close();
   				}
   				$("#btnClose").bind("click", fncClose);
   			});
   		</script>
	</head>
	<body id="e">
		<!-- check access right START -->
		<%
 			String accessRight = ((nttdm.bsys.common.fw.BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT")).getUserAccessInfo("E", "E-SET").getAccess_type();
 			pageContext.setAttribute("accessRightBean", accessRight);
 		 %> 
<%-- 		<c:if test="${not(accessRightBean eq 2)}"> --%>
<!-- 			<script type="text/javascript"> -->
<%-- 				window.location = '${pageContext.request.contextPath}/C_CMN001/C_CMN001S06SCR.do'; --%>
<!--  			</script> -->
<%-- 		</c:if> --%>
		<!-- check access right END -->
		<div class="section">
			<h1 class="title"><bean:message key="screen.e_set.00043"/></h1>
			<div class="group-content">
				<table class="datatable collapse" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th><bean:message key="screen.e_set.00044"/></th>
					    <th><bean:message key="screen.e_set.00045"/></th>
					    <th class="last"><bean:message key="screen.e_set.00046"/></th>
					</tr>
					<c:forEach items="${_e_setForm.map.logDetailList}" var="item">
						<tr>
							<td style="width:10%;text-align:center;">${item.ID_BATCH_LOG}</td>
							<td style="width:70%;">${item.MESSAGE}</td>
							<td style="width:20%;">${item.CREATEDATE}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<p style="margin:50px 20px 0 20px;padding-bottom:20px;text-align:center;">
				<input type="button" id="btnClose" class="button" value="<bean:message key="screen.e_set.00047" />" />
			</p>
		</div>
		
		<div class="error">
			<html:messages id="message">
				<bean:write name="message"/><br/>
			</html:messages>
		</div>
		<div class="message">
			<ts:messages id="message" message="true"><bean:write name="message"/></ts:messages>
		</div>
	</body>
</html:html>

