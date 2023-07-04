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
		<title><bean:message key="screen.e_mex_ct1.title" name="_e_mexForm" /></title>
	  <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
	  <link href="<%=request.getContextPath()%>/E_MEX/css/e_mex.css" rel="stylesheet" type="text/css" />
	  <script type="text/javascript">
	  	CONTEXT_PATH = "<%=request.getContextPath()%>";
	  </script> 
	  <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
	</head>
	
	<body id="SC_E_MEX_CT1_2" onload="initMain();">
		<ts:form action="/RP_E_MEX_CT1_2BL" >
			<h1 class="title"><bean:message key="screen.e_mex.00005"/></h1>
			
			<div class="section">
				<table class="datatable collapse" cellpadding="0" cellspacing="0" border="0">
					  <tr>
					    <th><bean:message key="screen.e_mex.00044"/></th>
					    <th><bean:message key="screen.e_mex.00045"/></th>
					    <th><bean:message key="screen.e_mex.00046"/></th>
					  </tr>
					  <c:forEach items="${_e_mexForm.map.logsList}" var="log">
					  <tr>
					  	<td align="center">${log.ID_BATCH_LOG}</td>
					  	<td>${log.ERROR_MSG}</td>
					  	<td align="center">${log.DATE_UPDATED_FMT}</td>
					  </tr>
					  </c:forEach>
					</table>
			</div>
			<div class="centerDiv"><input type="button" onclick="javascript: self.close ()" value="Close" /></div>
		</ts:form>
	</body>
</html:html>