<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">

<html:html locale="true">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Content-Script-Type" content="text/javascript">
    <meta http-equiv="Content-Style-Type" content="text/css">
    <meta name="Author" content="NTT Data Vietnam">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/C_CMN001/css/c_cmn001s03.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/C_CMN001/js/c_cmn001s03.js"></script>
    <title><bean:message key="screen.common.c_cmn001s02"/></title>
</head>
<t:defineCodeList id="LIST_SYSTEM_BASE"/>
<body id="c_cmn001s03" onload="initPage();">
<table class="tableMain" cellpadding="0px" cellspacing="0px">
	<tr>
		<td class="tdLogo" colspan="2">
			<logic:iterate id="system_base" name="LIST_SYSTEM_BASE">
    			<logic:equal name="system_base" property="name" value="MY">
        			<img src="<%=request.getContextPath()%>/image/logo_my.gif">
        		</logic:equal>
    			<logic:equal name="system_base" property="name" value="SG">
        			<img src="<%=request.getContextPath()%>/image/logo_sg.gif">
        		</logic:equal>        		
			</logic:iterate>
		</td>
		<td class="tdWelcomeUser">
			<logic:notEmpty name="USER_VALUE_OBJECT" scope="session">
        		<div id="userID">Welcome 
        			<a class="hlViewUser" href="javascript:void(0);" onclick="parent.frame_main.location = '<%=request.getContextPath()%>/A_USR/A_USR_S01DSP.do?event=forward_view&mode=view&idUser=${USER_VALUE_OBJECT.id_user}'">	        		
	        			<bean:write name="USER_VALUE_OBJECT" property="user_name" scope="session"/>
	        		</a>
        		</div>
        	</logic:notEmpty>
		</td>
	</tr>
	<logic:notEmpty name="USER_VALUE_OBJECT" scope="session">
	<tr> 
		<td class="tdMenu">
			<div class="tdMenuDiv">
				MAIN MENU
			</div>
		</td>
		<td class="tdMenu1">
			&nbsp; <span id="functionTitle"></span>
		</td>
		<td class="tdMenu2" nowrap="nowrap">
			<span style="cursor: pointer;" id="newmsg" onclick="showDashboard('<%=request.getContextPath()%>/C_CMN002/C_CMN002BLogic.do');"></span> &nbsp;
 			<span style="cursor: pointer;" onclick="showDashboard('<%=request.getContextPath()%>/C_CMN002/C_CMN002BLogic.do');"><img src="<%=request.getContextPath()%>/image/btnMenu.png">&nbsp;Dashboard</span>
			<span style="cursor: pointer;" onclick="showDashboard('<%=request.getContextPath()%>/C_CMN001/C_CMN001S02SCR.do');">&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/image/btnMenu.png">&nbsp;Logout</span>
		</td>
	</tr>
	</logic:notEmpty>
	<logic:empty name="USER_VALUE_OBJECT" scope="session">
		<tr id="loginUsedTr">
			<td colspan="3" class="tdMenu" style="width:100%;">
			&nbsp;<span id="newmsg"></span>
			</td>
		</tr>
	</logic:empty>
	<tr id="isLoginFlagAndUsrSesNull" style="display:none;"> 
		<td class="tdMenu">
			<div class="tdMenuDiv">
				MAIN MENU
			</div>
		</td>
		<td class="tdMenu1">
			&nbsp; <span id="functionTitle"></span>
		</td>
		<td class="tdMenu2" nowrap="nowrap">
			<span style="cursor: pointer;" id="newmsg" onclick="showDashboard('<%=request.getContextPath()%>/C_CMN002/C_CMN002BLogic.do');"></span> &nbsp;
 			<span style="cursor: pointer;" onclick="showDashboard('<%=request.getContextPath()%>/C_CMN002/C_CMN002BLogic.do');"><img src="<%=request.getContextPath()%>/image/btnMenu.png">&nbsp;Dashboard</span>
			<span style="cursor: pointer;" onclick="showDashboard('<%=request.getContextPath()%>/C_CMN001/C_CMN001S02SCR.do');">&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/image/btnMenu.png">&nbsp;Logout</span>
		</td>
	</tr>
</table>
<logic:empty name="USER_VALUE_OBJECT" scope="session">
	<input type="hidden" value="" id="userSessIsNullFlag"/>
</logic:empty>
</body>
</html:html>