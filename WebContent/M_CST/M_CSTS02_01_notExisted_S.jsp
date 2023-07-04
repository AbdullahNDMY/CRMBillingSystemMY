<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
		<link type="text/css" rel="stylesheet" href="css/m_csts02.css" />
   		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
    	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/stylesheet/tabcontent.css"/>
    	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/tabcontent.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>     	   		
   		<script type="text/javascript" src="js/m_csts02_01_r11_s.js"></script>
		<title></title>
	</head>
	<body >
		<ts:form action="/M_CSTR08BLogic">
		<table class="subHeader" cellpadding="0" cellspacing="0">
    		<tr>
    			<td><bean:message key="screen.m_cst_s.label_bank_infomation"/></td>
    		</tr>
    	</table>
    	<!-- Giro Infomation table -->
		<table class="information" cellpadding="0" cellspacing="1">
			<tr>
				<td colspan="4" style="color:red"><bean:message key="error.notExistedBankInfo"/></td>
			</tr>			
						
		</table>
		<hr class="lineBottom" size="3">
		<table class="buttonGroup" cellpadding="0" cellspacing="0">
			<tr>
				<button onclick="window.close();"><bean:message key="screen.m_cst_s.button_cancel"/></button>&nbsp;
			</tr>
		</table>
		</ts:form>
	</body>
</html:html>

