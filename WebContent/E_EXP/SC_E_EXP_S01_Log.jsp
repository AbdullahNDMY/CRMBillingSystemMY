<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>    
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
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<title><bean:message key="screen.e_exp_s01.title"/></title>
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
		<link href="<%=request.getContextPath()%>/E_EXP/css/e_exp.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
	</head>
	
	<body onload="initMain();">
		<ts:form action="/RP_E_EXP_S01LogInitBL" >
			<h1 class="title"><bean:message key="screen.e_exp_s01_log.title"/></h1>
			
			<div class="section">
				<table class="datatable collapse" cellpadding="0" cellspacing="0" border="0">
					  <tr>
					    <th><bean:message key="screen.e_exp_s01_log.logId"/></th>
					    <th><bean:message key="screen.e_exp_s01_log.errorMsg"/></th>
					    <th><bean:message key="screen.e_exp_s01_log.logDate"/></th>
					  </tr>
					  <c:forEach items="${e_expForm.map.logsList}" var="log">
					  <tr>
					  	<td align="center">${log.ID_BATCH_LOG}</td>
					  	<td>${log.MESSAGE}</td>
					  	<td align="center">${log.CREATEDATE}</td>
					  </tr>
					  </c:forEach>
					</table>
			</div>
			<div class="centerDiv"><input type="button" onclick="javascript: self.close()" value="Close" /></div>
		</ts:form>
	</body>
</html:html>