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
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>	
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   		<script type="text/javascript" src="js/a_usrs02.js"></script>
		<title></title>
	</head>
	<body id="a">
	<table class="subHeader">
	  <tr>
	    <td><bean:message key="screen.a_usrs01.usermaintenance"/></td>
	  </tr>
	</table>
	
	<ts:form styleId="frm_AUSRS02" action="/A_USR_S02DSP">
	<input type="hidden" id="hiddenMsgDeleteConfirmation" value="<bean:message key="info.ERR4SC002"/>"/>
	<input type="hidden" id="hiddenMode" value="${mode}"/>
	<h4><bean:message key="screen.a_usrs02.generalinfo"/></h4>
	<div id="generalInformation" class="wrapper">
		<nested:nest property="user">
		<nested:hidden property="idUser" styleId="hiddenIdUser"/>
		<div class="left">
			<table>
				<tr>
					<td width="40%"><bean:message key="screen.a_usrs02.userid"/></td>
					<td width="3%"><bean:message key="screen.a_usrs01.colon"/></td>
					<td width="57%">
						<nested:write property="idUser"/>
					</td>
				</tr>
				<tr>
					<td width="40%"><bean:message key="screen.a_usrs02.username"/></td>
					<td width="3%"><bean:message key="screen.a_usrs01.colon"/></td>
					<td width="57%">
						<nested:write property="userName"/>
					</td>
				</tr>
				<tr>
					<td width="40%"><bean:message key="screen.a_usrs02.division"/></td>
					<td width="3%"><bean:message key="screen.a_usrs01.colon"/></td>
					<td width="57%">
						<logic:iterate name="listDivision" id="division">
							<nested:equal property="idDivision" value="${division.idDivision}">
								<bean:write name="division" property="divisionName"/>
							</nested:equal>
						</logic:iterate>
					</td>
				</tr>
				<tr>
					<td width="40%"><bean:message key="screen.a_usrs02.department"/></td>
					<td width="3%"><bean:message key="screen.a_usrs01.colon"/></td>
					<td width="57%">
						<logic:iterate name="listDepartment" id="dept">
							<nested:equal property="idDepartment" value="${dept.idDepartment}">
								<bean:write name="dept" property="departmentName"/>
							</nested:equal>
						</logic:iterate>
					</td>
				</tr>
				<tr>
					<td width="40%"><bean:message key="screen.a_usrs02.role"/></td>
					<td width="3%"><bean:message key="screen.a_usrs01.colon"/></td>
					<td width="57%">
						<nested:equal property="idRole" value="-">
							-
						</nested:equal>
						<logic:iterate name="listRole" id="role">
							<nested:equal property="idRole" value="${role.idRole}">
								<bean:write name="role" property="roleName"/>
							</nested:equal>
						</logic:iterate>
					</td>
					<td class="extra notText" nowrap="nowrap">
                        <bean:message key="screen.a_usrs01.accessRight"/><bean:message key="screen.a_usrs01.colon"/>
                        <nested:radio property="accessRight" styleClass="accessRight" value="R" disabled="true"/>
                        <bean:message key="screen.a_usrs01.byRole"/>
                        <nested:radio property="accessRight" styleClass="accessRight" value="C" disabled="true"/>
                        <bean:message key="screen.a_usrs01.customized"/>
                    </td>
				</tr>
				<tr>
					<td width="40%"><bean:message key="screen.a_usrs02.telephoneno"/></td>
					<td width="3%"><bean:message key="screen.a_usrs01.colon"/></td>
					<td width="57%">
						<nested:empty property="telNo">
							-
						</nested:empty>
						<nested:notEmpty property="telNo">
							<nested:write property="telNo"/>
						</nested:notEmpty>
					</td>
					<td class="extra">
						<bean:message key="screen.a_usrs02.telextno"/>
						<bean:message key="screen.a_usrs01.colon"/>
						<nested:empty property="telExtNo">
							-
						</nested:empty>
						<nested:notEmpty property="telExtNo">
							<nested:write property="telExtNo"/>
						</nested:notEmpty>
					</td>
				</tr>
				<tr>
					<td width="40%"><bean:message key="screen.a_usrs02.did"/></td>
					<td width="3%"><bean:message key="screen.a_usrs01.colon"/></td>
					<td width="57%">
						<nested:empty property="didNo">
							-
						</nested:empty>
						<nested:notEmpty property="didNo">
							<nested:write property="didNo"/>
						</nested:notEmpty>
					</td>
				</tr>
				<tr>
					<td width="40%"><bean:message key="screen.a_usrs02.officemobile"/></td>
					<td width="3%"><bean:message key="screen.a_usrs01.colon"/></td>
					<td width="57%">
						<nested:empty property="officeMobileNo">
							-
						</nested:empty>
						<nested:notEmpty property="officeMobileNo">
							<nested:write property="officeMobileNo"/>
						</nested:notEmpty>
					</td>
				</tr>
				<tr>
					<td width="40%"><bean:message key="screen.a_usrs02.email"/></td>
					<td width="3%"><bean:message key="screen.a_usrs01.colon"/></td>
					<td width="57%">
						<nested:empty property="email">
							-
						</nested:empty>
						<nested:notEmpty property="email">
							<nested:iterate property="dispEmail">
								<nested:write property="email"/><br/>
							</nested:iterate>
						</nested:notEmpty>
					</td>
				</tr>
				<tr>
					<td width="40%"><bean:message key="screen.a_usrs02.pplsoft_acc_id"/></td>
					<td width="3%"><bean:message key="screen.a_usrs01.colon"/></td>
					<td width="57%">
						<nested:empty property="idPeopleSoftAcc">
							-
						</nested:empty>
						<nested:notEmpty property="idPeopleSoftAcc">
							<nested:write property="idPeopleSoftAcc"/>
						</nested:notEmpty>
					</td>
					<td>
						<bean:message key="screen.a_usrs02.pplsoft_dept_id"/>
						<bean:message key="screen.a_usrs01.colon"/>
						<nested:empty property="idPplSoftDept">
							-
						</nested:empty>
						<nested:notEmpty property="idPplSoftDept">
							<nested:write property="idPplSoftDept"/>
						</nested:notEmpty>
					</td>
				</tr>
			</table>
		</div>
		<div class="right">
			<img src="<%=request.getContextPath()%>/A_USR/A_USR_S02_Photo.do?idUser=<nested:write property="idUser"/>" alt="avatar"/>
			<fieldset>
				<legend><bean:message key="screen.a_usrs02.status"/></legend>
				<nested:radio property="userStatus" value="1" disabled="true"/>
				<label><bean:message key="screen.a_usrs02.activestatus"/></label>
				<nested:radio property="userStatus" value="0" disabled="true"/>
				<label><bean:message key="screen.a_usrs02.inactivestatus"/></label>
				
			</fieldset>
		</div>
		<div class="clear">
		</div>
		</nested:nest>
	</div>
	
	<hr/>
	
	<h4><bean:message key="screen.a_usrs02.useraccessinfo"/></h4>
	<div id="userAccessInformation" class="wrapper">
		<table>
			<tr class="header">
				<td class="colModule"><bean:message key="screen.a_usrs02.module"/></td>
				<td class="colSubModule"><bean:message key="screen.a_usrs02.submodule"/></td>
				<td class="colAccessType"><bean:message key="screen.a_usrs02.fullaccess"/></td>
				<td class="colAccessType"><bean:message key="screen.a_usrs02.readonly"/></td>
				<td class="colAccessType"><bean:message key="screen.a_usrs02.noaccess"/></td>
			</tr>
			<nested:iterate property="listUserAccess">
			<tr>
				<td>
					<logic:iterate name="listModule" id="module">
						<nested:equal property="idModule" value="${module.idModule}">
							<bean:write name="module" property="moduleName"/>
						</nested:equal>
					</logic:iterate>
				</td>
				<td>
					<logic:iterate name="listSubModule" id="subModule">
						<nested:equal property="idSubModule" value="${subModule.idSubModule}">
							<bean:write name="subModule" property="subModuleName"/>
						</nested:equal>
					</logic:iterate>
				</td>
				<td class="colAccessType">
					<nested:radio property="accessType" value="2" disabled="true"/>
				</td>
				<td class="colAccessType">
					<nested:radio property="accessType" value="1" disabled="true"/>
				</td>
				<td class="colAccessType">
					<nested:radio property="accessType" value="0" disabled="true"/>
				</td>
			</tr>
			</nested:iterate>
		</table>
	</div>
		<hr/>
	<div class="groupButton">
		<logic:equal name="accessType" value="2">
			<button id="btnEdit"><bean:message key="screen.a_usrs02.edit"/></button>
			<button id="btnDelete"><bean:message key="screen.a_usrs02.delete"/></button>
		</logic:equal>
		<button id="btnExit"><bean:message key="screen.a_usrs02.exit"/></button>
	</div>
	<div class="message">
		<html:messages id="message" message="true">
			<bean:write name="message"/><br/>
		</html:messages>
	</div>
	<div class="error">
		<html:messages id="error">
			<bean:write name="error"/><br/>
		</html:messages>
	</div>
	</ts:form>
	</body>
</html:html>
