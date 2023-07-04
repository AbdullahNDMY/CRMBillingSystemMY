<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
   		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
		<title><bean:message key="screen.c_cmn004.label.title"/></title>
	</head>
	<ts:body>
			<table class="subHeader" cellpadding="0" cellspacing="0">
	    		<tr>
	    			<td><bean:message key="screen.c_cmn004.label.title"/></td>
	    		</tr>
	    	</table>
	    	<BR>
			<table class="resultInfo" cellpadding="0" cellspacing="0">
				<tr>
					<td><bean:message key="screen.c_cmn004.label.inform"/></td>
				</tr>
				
			</table>
	</ts:body>
</html:html>

