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
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/C_CMN003/css/c_cmn001s04.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   	<script type="text/javascript" src="<%=request.getContextPath()%>/C_CMN003/js/c_cmn001s04.js"></script>
    <title><bean:message key="screen.common.c_cmn001s02"/></title>
</head>
<body id="c_cmn001s04" onload="initPage();">
<ts:form action="/C_CMN003SCR">
	<div class="menuContainer">
		<div class="labelSubHeader">Toolbar</div>
		<logic:present property="arr_Module" name="_c_cmn003Form">
			<ul>
			<logic:iterate id="Menu" name="_c_cmn003Form" property="arr_Module" indexId="i">
				<bean:define id="parent_module" name="Menu" property="id_module"/>
				<li id="${parent_module}" class="hide">
					<a class="parentMenu" onclick="showHide('${parent_module}');"><bean:write name="Menu" property="mod_name"/></a>
					<logic:present property="arr_subModule" name="_c_cmn003Form">
						<ul>
						<logic:iterate id="SubMenu" name="_c_cmn003Form" property="arr_subModule">
							<logic:notEmpty name="SubMenu" property="path_module">
								<logic:equal name="SubMenu" property="id_module" value="${parent_module}">
									<li>
										<a class="subMenu" onclick="gotoPage('<%=request.getContextPath()%><bean:write name="SubMenu" property="path_module"/>')">
											- <bean:write name="SubMenu" property="sub_mod_name"/> 
										</a>
									</li>
								</logic:equal>
							</logic:notEmpty>
						</logic:iterate>
						</ul>
					</logic:present>
				</li>
			</logic:iterate>
			</ul>
		</logic:present>
	</div>
</ts:form>
</body>
</html:html>