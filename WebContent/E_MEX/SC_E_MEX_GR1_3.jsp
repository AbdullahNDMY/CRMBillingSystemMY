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
		<title><bean:message key="screen.e_mex_gr1_3.title" name="_e_mexForm" /></title>
	  <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
	  <link href="<%=request.getContextPath()%>/E_MEX/css/e_mex.css" rel="stylesheet" type="text/css" />
	  <script type="text/javascript">
	  	CONTEXT_PATH = "<%=request.getContextPath()%>";
	  </script> 
	  <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
	  <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery.spinbox.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/E_MEX/js/e_mex_gr1_3.js"></script>
	</head>
	
	<body onload="initMain();" class="giroGenFile">
		<ts:form action="/RP_E_MEX_GR1_3BL" >
			<h1 class="title"><bean:message key="screen.e_mex_gr1_3.title"/></h1>
			<table border="0">
				<tr>
					<td>
					<bean:message key="screen.e_mex_gr1.giro.date"/>:&nbsp;
					<html:hidden name="_e_mexForm" property="idBatch" />
					<html:text name="_e_mexForm" property="giroDay" size="5" maxlength="2" styleClass="resettable">:</html:text>&nbsp;
					<html:text name="_e_mexForm" property="giroMonth" size="5" maxlength="2" styleClass="resettable">:</html:text>&nbsp;
					<html:text name="_e_mexForm" property="giroYear" size="5" maxlength="4" styleClass="resettable">:</html:text>&nbsp;
					</td>
				</tr>
				<tr>
					<td>
					<bean:message key="screen.e_mex_gr1.giro.confirm"/>
					</td>
				</tr>
				<tr>
					<td>
					<div class="actionBt">
						<html:hidden name="_e_mexForm" property="forward_execute" value="1"/>
						<input type="submit" onclick="javascript:self.close()" value="Yes" size="20"/>
						<input type="button" onclick="javascript:self.close()" value="Cancel" size="20"/>
					</div>
					</td>
				</tr>
			</table>
		</ts:form>
	</body>
</html:html>