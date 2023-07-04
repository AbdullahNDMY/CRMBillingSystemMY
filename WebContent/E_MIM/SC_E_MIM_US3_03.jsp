<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<title><bean:message key="screen.e_mim_us2.title"/></title>
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
		<link href="${pageContext.request.contextPath}/E_MIM/css/e_mim.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/common.js"></script>
	</head>
	<body id="e" onload="initMain();">
		<div class="section">
			<h2><bean:message key="screen.e_mim_us2_2.0001"/></h2>
			<div class="group-content">
				<table class="datatable collapse" cellpadding="0" cellspacing="0" border="0">
				  <tr>
				    <th align="center"><bean:message key="screen.e_mim_us2_2.0002"/></th>
				    <th align="center"><bean:message key="screen.e_mim_us2_2.0003"/></th>
				    <th align="center" class="last"><bean:message key="screen.e_mim_us2_2.0004"/></th>
				  </tr>
				 <c:forEach items="${_e_mimForm.map.listLog}" var="item">
				 	<td>${item.ID_BATCH_LOG}</td>
				 	<td>${item.ERROR_MSG}</td>
				 	<td>
				 		<fmt:formatDate value="${item.DATE_UPDATED}" pattern="dd/MM/yyyy"/> 
				 	</td>
				 </c:forEach>
				</table>
			</div>
		</div>
		<br/>
		<div style="text-align: center;">
			<input type="button" onclick="window.close();" value='<bean:message key="screen.e_mim_us2_2.0005"/>'/>
		</div>
	</body>
</html:html>

