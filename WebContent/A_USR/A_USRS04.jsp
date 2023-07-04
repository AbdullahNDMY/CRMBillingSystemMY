<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/struts-html" prefix="html"%>
<%@ taglib uri="/struts-bean" prefix="bean"%>
<%@ taglib uri="/struts-logic" prefix="logic"%>
<%@ taglib uri="/terasoluna-struts" prefix="ts"%>
<%@ taglib uri="/terasoluna" prefix="t"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<link rel="stylesheet" type="text/css"	href="${pageContext.request.contextPath}/stylesheet/common.css" />
<link rel="stylesheet" type="text/css"	href="${pageContext.request.contextPath}/stylesheet/tabcontent.css" />
<link rel="stylesheet" type="text/css"	href="${pageContext.request.contextPath}/A_USR/css/a_usrs04.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/uploadify/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/uploadify/swfobject.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/uploadify/jquery.uploadify.v2.1.4.min.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/javascript/messageBox.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/A_USR/js/a_usrs04.js"></script>
<title>Insert title here</title>
</head>
<body id="a">
	<table class="subHeader">
		<tr>
			<td><bean:message key="screen.a_usrs04.roleaccess" />
			</td>
		</tr>
	</table>		
	<ts:form styleId="frm_AUSRS04" action="/A_USR04_DSP.do">
		<input type="hidden" name="pageEvent" value="postback" />
		<input type="hidden" id="hiddenMsgConfirmDeletetion" value="<bean:message key="info.ERR4SC002"/>" />
		<input type="hidden" id="hiddenMsgNoSelected" value='<bean:message key="errors.ERR1SC005" arg0="Role"/>' />
		<input type="hidden" id="hiddenMsgMinRecord" value='<bean:message key="errors.ERR1SC068"/>' />
		<input type="hidden" name="mode" id="hiddenMode" value="${mode}"/>
		<input type="hidden" id="hiddenFirstChoosed" value="${frm_AUSRS04.choosed}" />
		<div class="searchCondition">
			<bean:message key="screen.a_user04.role" />
			<font color="red">*</font>
			<bean:message key="screen.a_user04.label_colon" />
			<nested:select name="frm_AUSRS04" property="choosed" styleId="cmbSerivceGroup" styleClass="USRTextBox">
				<html:option value="">
					<bean:message key="screen.a_usrs04.blankitem" />
				</html:option>
				<html:optionsCollection name="listRole" label="roleName"
					value="idRole" />
			</nested:select>
		</div>
		<div class="groupButton">
			<button id="btnSearch">
				<bean:message key="screen.a_usrs04.search" />
			</button>
			<button id="btnReset">
				<bean:message key="screen.a_usrs04.reset" />
			</button>			
		</div>
		<div id="searchResult">
		<logic:notEmpty name="frm_AUSRS04" property="listRoleAccess">		
			<h4><bean:message key="screen.a_usrs04.roleaccessinfo"/></h4>
			<div id="roleAccessInformation" class="wrapper">
				<table>
					<tr class="header">
						<td class="colModule"><bean:message	key="screen.a_usrs04.module" />
						</td>
						<td class="colSubModule"><bean:message key="screen.a_usrs04.submodule" />
						</td>
						<td class="colAccessType"><bean:message	key="screen.a_usrs04.fullaccess" />
						</td>
						<td class="colAccessType"><bean:message	key="screen.a_usrs04.readonly" />
						</td>
						<td class="colAccessType"><bean:message	key="screen.a_usrs04.noaccess" />
						</td>
					</tr>
					<logic:iterate id="roleAccess" name="frm_AUSRS04" property="listRoleAccess">
						<tr>
							<td><html:hidden name="roleAccess" property="idModule" indexed="true" /> 
								<logic:iterate name="listModule" id="module">
									<c:if test="${roleAccess.idModule == module.idModule}">
										<bean:write name="module" property="moduleName" />
									</c:if>
								</logic:iterate>
							</td>
							<td><html:hidden name="roleAccess" property="idSubModule" indexed="true" /> 
								<logic:iterate name="listSubModule"
									id="subModule">
									<c:if test="${roleAccess.idSubModule == subModule.idSubModule}">
										<bean:write name="subModule" property="subModuleName" />
									</c:if>
								</logic:iterate>
							</td>
							<td class="colAccessType"><html:radio name="roleAccess" property="accessType" indexed="true" value="2" /></td>
							<td class="colAccessType"><html:radio name="roleAccess"	property="accessType" indexed="true" value="1" /></td>
							<td class="colAccessType"><html:radio name="roleAccess"	property="accessType" indexed="true" value="0" /></td>
						</tr>
					</logic:iterate>
				</table>
			</div>
			<div class="groupButton">
				<button id="btnSave">
					<bean:message key="screen.a_usrs04.save"/>
				</button>
			</div>
		</logic:notEmpty>
		</div>
		<div class="error">
			<html:messages id="message">
				<bean:write name="message" />
				<br />
			</html:messages>
		</div>
		<div class="message">
			<html:messages id="message" message="true">
				<bean:write name="message" />
				<br />
			</html:messages>
		</div>
	</ts:form>
</body>
</html>
