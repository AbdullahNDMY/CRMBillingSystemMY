<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%> 
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<html>
<head>
	<base target="_self"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
	<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
	<meta content="">
	<title>POPTER</title>
	<link type="text/css" rel="stylesheet" href="css/popup.css"/>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
   	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   	<script type="text/javascript" src="js/popup.js"></script>
</head>
<body onload="javascript:initPOPTER();" class="popup">
<ts:form action="/POPUP_COMMON_DSP">
<html:hidden property="idCustPlan" styleId="idCustPlan"/>
<html:hidden property="idService" styleId="idService"/>
<html:hidden property="actionStatus" styleId="actionStatus"/>
<html:hidden property="svc_start" styleId="svc_start"/>
<html:hidden property="minSvcPeriod" styleId="minSvcPeriod"/>
<html:hidden property="minimumServiceTo" styleId="minimumServiceTo"/>
<div id="msgConfirm" style="display: none"><bean:message key="info.ERR4SC006"/></div>
<div id="msgConfirm1" style="display: none"><bean:message key="info.ERR4SC010"/></div>
<table border="0" cellpadding="0" cellspacing="2" class="popup" style="margin-top:20px;margin-left:5px;margin-bottom:5px;margin-left:20px;">
	<tr>
		<td valign="top">
			<img src="./images/alert.png" width="32" height="32"/>
		</td>
		<td>
			Alert<br/>
			You are about to terminate a service.<br/>
			Please note that termination date cannot be earlier than<br/>
			minimum service period end if minimum term is applied.<br/>
			Please key in service end date: 
				<html:text property="terminateDate" styleId="terminateDate" style="width:70px;" onchange="checkEmpty(this);" />
				<t:inputCalendar for="terminateDate" format="dd/MM/yyyy"/><br/>
			<div style="color:red;padding-top:20px;padding-bottom:20px;" id="warningMsg">
			</div>
			Are you sure to terminate?<br/>
			Click Yes to Save, No to abort termination.<br/>
			<br/>
			<input type="button" name="btnYes" value="Yes" onclick="javascript: doTerminate();" style="width:70px;"/>
			<input type="button" name="btnNo" value="No" onclick="javascript: doPopterNo();" style="width:70px;"/>
		</td>
	</tr>
</table>
</ts:form>
<!-- message display start -->
<div style="color:#0046D5;padding-left:20px;padding-top:0px;">
	<html:messages id="message" message="true">
		<bean:write name="message"/>
	</html:messages>
</div>
<div style="color:red;padding-left:20px;padding-top:0px;">
	<html:messages id="message">
		<bean:write name="message"/><br/>
	</html:messages>
</div>

<!-- message display end -->
</body>
</html>