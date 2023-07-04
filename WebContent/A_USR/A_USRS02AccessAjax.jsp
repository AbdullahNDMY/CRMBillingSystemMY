<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
   		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
	    <link rel="stylesheet" type="text/css" href="css/a_usrs02.css"/>
		<title></title>
	</head>
	<body id="a">
	<ts:form styleId="frm_AUSRS02" action="/A_USR_S02DSP">
	<div id="userAccessInformation" class="wrapper">
		<table>
			<tr class="header">
				<td class="colModule"><bean:message key="screen.a_usrs02.module"/></td>
				<td class="colSubModule"><bean:message key="screen.a_usrs02.submodule"/></td>
				<td class="colAccessType"><bean:message key="screen.a_usrs02.fullaccess"/></td>
				<td class="colAccessType"><bean:message key="screen.a_usrs02.readonly"/></td>
				<td class="colAccessType"><bean:message key="screen.a_usrs02.noaccess"/></td>
			</tr>
			<logic:iterate id="userAccess" name="frm_AUSRS02" property="listUserAccess">
			<tr>
				<td>
					<html:hidden name="userAccess" property="idModule" indexed="true"/>
					<logic:iterate name="listModule" id="module">
						<c:if test="${userAccess.idModule == module.idModule}">
							<bean:write name="module" property="moduleName"/>
						</c:if>
					</logic:iterate>
				</td>
				<td>
					<html:hidden name="userAccess" property="idSubModule" indexed="true"/>
					<logic:iterate name="listSubModule" id="subModule">
						<c:if test="${userAccess.idSubModule == subModule.idSubModule}">
							<bean:write name="subModule" property="subModuleName"/>
						</c:if>
					</logic:iterate>
				</td>
				<td class="colAccessType">
					<html:radio name="userAccess" property="accessType" indexed="true" value="2"/>
				</td>
				<td class="colAccessType">
					<html:radio name="userAccess" property="accessType" indexed="true" value="1"/>
				</td>
				<td class="colAccessType">
					<html:radio name="userAccess" property="accessType" indexed="true" value="0"/>
				</td>
			</tr>
			</logic:iterate>
		</table>
	</div>
	</ts:form>
	</body>
</html:html>
