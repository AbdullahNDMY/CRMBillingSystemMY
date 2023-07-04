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
	    <link href="/uploadify/uploadify.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/uploadify/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/uploadify/swfobject.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/uploadify/jquery.uploadify.v2.1.4.min.js"></script>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>	
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   		<script type="text/javascript" src="js/a_usrs02.js"></script>
   		<script type="text/javascript">
   		$(function(){
   		     changeAccessType();
	         $(".accessRight").click(changeAccessType);
   		});
   		</script>
		<title></title>
	</head>
	<body id="a">
	<table class="subHeader">
	  <tr>
	    <td><bean:message key="screen.a_usrs01.usermaintenance"/></td>
	  </tr>
	</table>
	<ts:form styleId="frm_AUSRS02" action="/A_USR_S02DSP">
	<input type="hidden" id="hiddenContextPath" value="<%=request.getContextPath()%>"/>
	<input type="hidden" name="mode" id="hiddenMode" value="${mode}"/>
	<input type="hidden" name="pageEvent" value="postback"/>
	<input type="hidden" id="hiddenMsgDeleteConfirmation" value="<bean:message key="info.ERR4SC002"/>"/>
	<input type="hidden" id="hiddenMsgExitConfirmation" value="<bean:message key="info.ERR4SC001"/>"/>
	<input type="hidden" id="ERR4SC107" value="<bean:message key="errors.ERR1SC107" arg0="{0}" arg1="{1}"/>"/>
	<input type="hidden" id="sessionDirectory" value="${SESSION_DIRECTORY}"/>
	<h4><bean:message key="screen.a_usrs02.generalinfo"/></h4>
	<div id="generalInformation" class="wrapper">
		<nested:nest property="user">
		<div class="left">
			<table>
				<tr>
					<td class="header"><bean:message key="screen.a_usrs02.userid"/><span class="mandantory"><bean:message key="screen.a_usrs02.star"/></span></td>
					<td class="value" colspan="2">
						<bean:message key="screen.a_usrs01.colon"/>
						<logic:equal name="mode" value="new">
							<nested:text property="idUser" maxlength="15"/>
						</logic:equal>
						<logic:equal name="mode" value="edit">
							<nested:hidden property="idUser" styleId="hiddenIdUser"/>
							<nested:write property="idUser"/>
						</logic:equal>
						
					</td>
				</tr>
				<tr>
					<td class="header"><bean:message key="screen.a_usrs02.username"/><span class="mandantory"><bean:message key="screen.a_usrs02.star"/></span></td>
					<td class="value">
						<bean:message key="screen.a_usrs01.colon"/>
						<nested:text property="userName" maxlength="30"/>
					</td>
				</tr>		
				<tr>
					<td class="header"><bean:message key="screen.a_usrs02.password"/><span class="mandantory"><bean:message key="screen.a_usrs02.star"/></span></td>
					<td class="value" colspan="2">
						<bean:message key="screen.a_usrs01.colon"/>
						<nested:password property="password" maxlength="15" onfocus="cls()" onblur="res()"/>
					</td>
				</tr>
				<tr>
					<td class="header"><bean:message key="screen.a_usrs02.repassword"/><span class="mandantory"><bean:message key="screen.a_usrs02.star"/></span></td>
					<td class="value" colspan="2">
						<bean:message key="screen.a_usrs01.colon"/>
						<nested:password property="reEnterPassword" maxlength="15" onfocus="cls()" onblur="res()"/>
					</td>
				</tr>
			
				<tr>
					<td class="header"><bean:message key="screen.a_usrs02.division"/><span class="mandantory"><bean:message key="screen.a_usrs02.star"/></span></td>
					<td class="value" colspan="2">
						<bean:message key="screen.a_usrs01.colon"/>
						<nested:select property="idDivision">
							<html:option value=""><bean:message key="screen.a_usrs01.blankitem"/></html:option>
							<html:optionsCollection name="listDivision" label="divisionName" value="idDivision"/>
						</nested:select>
					</td>
				</tr>
				<tr>
					<td class="header"><bean:message key="screen.a_usrs02.department"/><span class="mandantory"><bean:message key="screen.a_usrs02.star"/></span></td>
					<td class="value" colspan="2">
						<bean:message key="screen.a_usrs01.colon"/>
						<nested:select property="idDepartment">
							<html:option value=""><bean:message key="screen.a_usrs01.blankitem"/></html:option>
							<html:optionsCollection name="listDepartment" label="departmentName" value="idDepartment"/>
						</nested:select>
					</td>
				</tr>
				<tr>
					<td class="header"><bean:message key="screen.a_usrs02.role"/><span class="mandantory"><bean:message key="screen.a_usrs02.star"/></span></td>
					<td class="value">
						<bean:message key="screen.a_usrs01.colon"/>
						<nested:select property="idRole" styleId="idRole" onchange="retriveAccess(this)">
							<html:option value=""><bean:message key="screen.a_usrs01.blankitem"/></html:option>
							<html:optionsCollection name="listRole" label="roleName" value="idRole"/>
						</nested:select>
						<nested:hidden property="idRole" styleId="idRoleHidden"/>
					</td>
					<td class="extra notText" nowrap="nowrap">
                        <bean:message key="screen.a_usrs01.accessRight"/><bean:message key="screen.a_usrs01.colon"/>
                        <nested:radio property="accessRight" styleClass="accessRight" value="R"/>
                        <bean:message key="screen.a_usrs01.byRole"/>
                        <nested:radio property="accessRight" styleClass="accessRight" value="C"/>
                        <bean:message key="screen.a_usrs01.customized"/>
                    </td>
				</tr>
				<tr>
					<td class="header"><bean:message key="screen.a_usrs02.telephoneno"/></td>
					<td class="value">
						<bean:message key="screen.a_usrs01.colon"/>
						<nested:text property="telNo" maxlength="15"/>
					</td>
					<td class="extra">
						<bean:message key="screen.a_usrs02.telextno"/>
						<bean:message key="screen.a_usrs01.colon"/>
						<nested:text property="telExtNo" maxlength="5"/>
					</td>
				</tr>
				<tr>
					<td class="header"><bean:message key="screen.a_usrs02.did"/></td>
					<td class="value" colspan="2">
						<bean:message key="screen.a_usrs01.colon"/>
						<nested:text property="didNo" maxlength="15"/>
					</td>
				</tr>
				<tr>
					<td class="header"><bean:message key="screen.a_usrs02.officemobile"/></td>
					<td class="value" colspan="2">
						<bean:message key="screen.a_usrs01.colon"/>
						<nested:text property="officeMobileNo" maxlength="15"/>
					</td>
				</tr>
				<tr>
					<td class="header"><bean:message key="screen.a_usrs02.email"/></td>
					<td class="value" colspan="2">
						<bean:message key="screen.a_usrs01.colon"/>
						<nested:text property="email" maxlength="100"/>
					</td>
				</tr>
				<tr>
					<td class="header"><bean:message key="screen.a_usrs02.pplsoft_acc_id"/></td>
					<td class="value">
						<bean:message key="screen.a_usrs01.colon"/>
						<nested:text property="idPeopleSoftAcc" styleId="idPeopleSoftAcc" maxlength="8"/>
					</td>
					<td  class="pelsoftdept">
						<bean:message key="screen.a_usrs02.pplsoft_dept_id"/>
						<bean:message key="screen.a_usrs01.colon"/>
						<nested:text property="idPplSoftDept" styleId="idPplSoftDept" maxlength="10"/>
					</td>
				</tr>
			</table>
		</div>
		<div class="right" style="position:relative">
			<img src="<%=request.getContextPath()%>/A_USR/A_USR_S02_Photo.do?idUser=<nested:write property="idUser"/>" alt="avatar"/>
			<div style="display:block;width:80px;height:25px;position:relative;margin-top:10px;">
				<span id="btnUpload" style="position:absolute;top:0px;left:0px;width:80px;height:25px;"></span>
				<input type="button" style="position:absolute;top:0px;left:0px;width:80px;height:25px;z-index:-1;" value="<bean:message key="screen.a_usrs02.upload"/>" />
				<nested:hidden property="photoPath" styleId="photoPath"/>
			</div>
			<fieldset>
				<legend><bean:message key="screen.a_usrs02.status"/></legend>
				<nested:radio property="userStatus" value="1"/>
				<label><bean:message key="screen.a_usrs02.activestatus"/></label>
				<nested:radio property="userStatus" value="0"/>
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
		<hr/>
	<div class="groupButton">
		<button id="btnSave"><bean:message key="screen.a_usrs02.save"/></button>
		<logic:equal name="mode" value="edit">
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
	<div style="display:none">
		<div id="hiddenQueue">
		</div>
	</div>
	</body>
</html:html>
