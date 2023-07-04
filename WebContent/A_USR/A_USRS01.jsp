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
	    <link rel="stylesheet" type="text/css" href="css/a_usrs01.css"/>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>	
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script> 
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   		<script type="text/javascript" src="js/a_usrs01.js"></script>
		
		<script type="text/javascript">
           $(function() {
                document.onkeydown = function(e){
                    var ev = document.all ? window.event : e;
                    if (ev.keyCode == 13) {
                       $("#btnSearch").click();
                    }
                };
           });
        </script>
		
		<title></title>
	</head>
	<body id="a" onload="initMain();">
	<table class="subHeader">
	  <tr>
	    <td><bean:message key="screen.a_usrs01.usermaintenance"/></td>
	  </tr>
	</table>

	<ts:form styleId="frm_AUSRS01" action="/A_USR_S01DSP">
	<input type="hidden" name="searchBy" id="hiddenSearchBy"/>
	<nested:hidden property="startIndex" styleId="hiddenStartIndex"/>
	<div class="searchCondition">
	<nested:nest property="inputSearch">
	<table class="searchConditionTbl" cellpadding="0" cellspacing="0">
	<tr>	
			<td align="right"><bean:message key="screen.a_usrs01.userid"/><bean:message key="screen.a_usrs01.colon"/></td>
			<td><nested:text property="idUser" maxlength="15" styleId="txtIdUser"/></td>
			
			<td align="right"><bean:message key="screen.a_usrs01.division"/><bean:message key="screen.a_usrs01.colon"/></td>
			<td><nested:select property="idDivision" styleId="cmbDivision">
			<html:option value=""><bean:message key="screen.a_usrs01.blankitem"/></html:option>
			<html:optionsCollection name="listDivision" label="divisionName" value="idDivision"/>
			</nested:select></td>

	</tr>
	<tr>
			<td align="right"><bean:message key="screen.a_usrs01.username"/><bean:message key="screen.a_usrs01.colon"/></td>
			<td><nested:text property="userName" maxlength="30" styleId="txtUserName"/></td>
			
			<td align="right"><bean:message key="screen.a_usrs01.department"/><bean:message key="screen.a_usrs01.colon"/></td>
			<td><nested:select property="idDepartment" styleId="cmbDepartment">
			<html:option value=""><bean:message key="screen.a_usrs01.blankitem"/></html:option>
			<html:optionsCollection name="listDepartment" label="departmentName" value="idDepartment"/>
			</nested:select></td>
	</tr>
	<tr>
            <td align="right"><bean:message key="screen.a_usrs01.status"/><bean:message key="screen.a_usrs01.colon"/></td>
            <td nowrap="nowrap" class="checkboxTD">
                <nested:multibox property="status" value="1"/><bean:message key="screen.a_usrs01.active"/>
                <nested:multibox property="status" value="0"/><bean:message key="screen.a_usrs01.inactive"/>
            </td>
            <td align="right"><bean:message key="screen.a_usrs01.role"/><bean:message key="screen.a_usrs01.colon"/></td>
            <td><nested:select property="idRole" styleId="cbmRole">
            <html:option value=""><bean:message key="screen.a_usrs01.blankitem"/></html:option>
            <html:optionsCollection name="listRole" label="roleName" value="idRole"/>
            </nested:select></td>
    </tr>
    <tr>
            <td align="right"></td>
            <td></td>
            <td align="right"><bean:message key="screen.a_usrs01.accessRight"/><bean:message key="screen.a_usrs01.colon"/></td>
            <td nowrap="nowrap" class="checkboxTD">
                <nested:multibox property="accessRight" value="R"/><bean:message key="screen.a_usrs01.byRole"/>
                <nested:multibox property="accessRight" value="C"/><bean:message key="screen.a_usrs01.customized"/>
            </td>
    </tr>
	</table>
		<div class="clear">
		</div>
	</nested:nest>
	</div>
	<div class="groupButton">
		<button id="btnSearch"><bean:message key="screen.a_usrs01.search"/></button>
		<button id="btnReset"><bean:message key="screen.a_usrs01.reset"/></button>
        <logic:equal name="accessType" value="2">
            <button id="btnNew"><bean:message key="screen.a_usrs01.new"/></button>
            <button id="btnOrganization" style="width:auto"><bean:message key="screen.a_usrs01.organizationManager"/></button>
            <button id="btnRoleAccess" style="width:auto"><bean:message key="screen.a_usrs01.roleAccessRightControl"/></button>
        </logic:equal>
	</div>

		<div class="searchFound">
			<bean:message key="screen.a_usrs01.searchtitle"/>
			<bean:message key="screen.a_usrs01.colon"/>
			<nested:write property="totalCount"/>
		</div>
		<div class="pageLink">
			<bean:message key="screen.a_usrs01.gotopage"/>
			<bean:message key="screen.a_usrs01.colon"/>
			<ts:pageLinks id="curPageLinks" name="frm_AUSRS01" 
			rowProperty="row"
			totalProperty="totalCount"
			indexProperty="startIndex" action="#" /> 
			<logic:present name="curPageLinks">
				<bean:write name="curPageLinks" filter="false" />
			</logic:present>
		</div>
		<table class="result">
			<tr class="header">
				<td width="3%"><bean:message key="screen.a_usrs01.no"/></td>
				<td width="12%"><bean:message key="screen.a_usrs01.userid"/></td>
				<td width="25%"><bean:message key="screen.a_usrs01.username"/></td>
				<td width="15%"><bean:message key="screen.a_usrs01.division"/></td>
				<td width="15%"><bean:message key="screen.a_usrs01.department"/></td>
				<td width="15%"><bean:message key="screen.a_usrs01.role"/></td>
				<td width="7%"><bean:message key="screen.a_usrs01.status"/></td>
				<td width="8%"><bean:message key="screen.a_usrs01.accessRight"/></td>
			</tr>
			<logic:present name="listUser">
			<logic:iterate name="listUser" id="user">
			<tr class="record">
				<td><bean:write name="user" property="no"/></td>
				<td><a class="hlViewUser" href="javascript:void(0);"><bean:write name="user" property="idUser"/></a></td>
				<td><bean:write name="user" property="userName"/></td>
				<td>
					<logic:iterate name="listDivision" id="division">
						<logic:equal name="division" property="idDivision" value="${user.idDivision}">
							<bean:write name="division" property="divisionName"/>
						</logic:equal>
					</logic:iterate>
				</td>
				<td>
					<logic:iterate name="listDepartment" id="department">
						<logic:equal name="department" property="idDepartment" value="${user.idDepartment}">
							<bean:write name="department" property="departmentName"/>
						</logic:equal>
					</logic:iterate>
				</td>
				<td>
                      <logic:iterate name="listRole" id="role">
                            <logic:equal name="role" property="idRole" value="${user.idRole}">
                                <logic:equal name="accessType" value="2">
                                    <a class="hlViewRole" href="javascript:void(0);">
                                    <html:hidden name="role" property="idRole" value="${user.idRole}"/>
                                    <bean:write name="role" property="roleName"/></a>
                                </logic:equal>
                                <logic:notEqual name="accessType" value="2">
                                    <bean:write name="role" property="roleName"/>
                                </logic:notEqual>
                            </logic:equal>
                        </logic:iterate>
                </td>
                <td>
	                <logic:equal name="user" property="userStatus" value="1">
	                    <bean:message key="screen.a_usrs01.active"/>
	                </logic:equal>  
	                <logic:equal name="user" property="userStatus" value="0">
	                    <bean:message key="screen.a_usrs01.inactive"/>
	                </logic:equal>
                </td>
                <td>
	                <logic:equal name="user" property="accessRight" value="R">
	                    <bean:message key="screen.a_usrs01.byRole"/>
	                </logic:equal>  
	                <logic:equal name="user" property="accessRight" value="C">
	                    <bean:message key="screen.a_usrs01.customized"/>
	                </logic:equal>
                </td>
			</tr>
			</logic:iterate>
			</logic:present>
		</table>
    <input type="hidden" value="<%=request.getContextPath() %>" id="contextpath"/>
	<div class="message">
		<html:messages id="message" message="true">
			<bean:write name="message"/><br/>
		</html:messages>
	</div>
	</ts:form>
	</body>
</html:html>