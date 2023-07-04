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
	    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
	    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/C_CMN001/css/c_cmn001s01.css"/>
	    <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
	    <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
	    <script type="text/javascript" src="<%=request.getContextPath()%>/C_CMN001/js/c_cmn001s01.js"></script>
	    <title><bean:message key="screen.common.f0000s01"/></title>
	    <script language="javascript">
	    function CheckKeyRequestNum(event){
			if (window.event.keyCode == 13)
			{			
				login();
			}
		}
	    </script>
	</head>	
	<body id="c_cmn001s01" onload="initMain();initPage();" onkeypress="CheckKeyRequestNum();">
		<ts:form action="/C_CMN001R01BLogic">
			<div id="contentPanel" class="contentPanel">
				<div id="welcomeHeader" class="welcomeHeader">
					<img src="<%=request.getContextPath()%>/image/welcome.png">
				</div>
				<html:hidden name="_c_cmn001Form" property="login_attempt" />
				<html:hidden name="_c_cmn001Form" property="login_time" />
				<div id="login" class="login">
			    	<table class="tblLogin">
			    		<tr>
			    			<td align="right"><bean:message key="screen.c_cmn001.user"/>:</td>
			    			<td align="left"><html:text property="id_user" tabindex="1" maxlength="15" size="20" style="width:150px;"/></td>
			    		</tr>
			    		<tr>
			    			<td align="right"><bean:message key="screen.c_cmn001.password"/>:</td>
			    			<td align="left"><html:password property="password" tabindex="2" maxlength="15" size="20" style="width:150px;"/></td>
			    		</tr>
			    		<tr><td colspan="2" style="text-align:right;"><button onclick="login();"><bean:message key="screen.c_cmn001.login"/></button><button onclick="clearData();"><bean:message key="screen.c_cmn001.reset"/></button></td></tr>
			    	</table>
			    </div>
			    <div class="error">			   		
			    	<span id="errorArea" style="text-align:left;">
			    		<ts:messages id="message" >
				    		<bean:write name="message"/><br/>
				    	</ts:messages>
			    		<ts:messages id="message" message="true"><bean:write name="message"/></ts:messages>
			    	</span>
			    </div>
			</div>
			<div id="errrorPanel" style="display:none;">
				<div style="text-align:center;">
					<table border="0" cellpadding="0" cellspacing="2">
						<tr>
							<td valign="top">
								<img src="<%=request.getContextPath()%>/image/alert.png" width="32" height="32"/>
							</td>
							<td>
								<bean:message key="errors.ERR1SC091"/>
							</td>
						</tr>
					</table>
				</div>
				<div style="text-align:center;">
					<input type="button" value="Close" onclick="closeWindow()">
				</div>
			</div>
		</ts:form>
	</body>
</html:html>