<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/bs" prefix="bs" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <meta http-equiv="Content-Script-Type" content="text/javascript">
	    <meta http-equiv="Content-Style-Type" content="text/css">
	    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
   		<link type="text/css" rel="stylesheet" href="css/a_adts01.css" />
   		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
   		<script type="text/javascript" src="js/a_adts01.js"></script>		
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script> 
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
		<title></title>		
	</head>
	<body id="a" onload="initMain();">
	<ts:form action="/A_ADTR01DSP">
		<t:defineCodeList id="LIST_PLANSTATUS_DISPLAY" />
		<table cellpadding="0" cellspacing="0" width="100%" border="0"><tr><td>
		<html:hidden property="selectedAuditID" name="_a_adtForm" />		
		<table class="subHeader" cellpadding="0" cellspacing="0">
    		<tr>
    			<td><bean:message key="screen.a_adts01.screen_name"/></td>
    		</tr>
    	</table>
    	<table class="inputInfo" cellpadding="0" cellspacing="0">
    		<tr>
    			<td class="col1Top"><bean:message key="screen.a_adt.label_date"/><bean:message key="screen.a_adt.label_colon"/></td>
    			<td class="col2Top">
	    			<html:text name="_a_adtForm" property="dateFrom" maxlength="10" readonly="true" styleClass="BlueStyle-textbox" styleId="dateFrom"/>
	                <t:inputCalendar for="dateFrom" format="dd/MM/yyyy"/>
	                <bean:message key="screen.a_adt.label_splitter"/>
	    			<html:text name="_a_adtForm" property="dateTo" maxlength="10" readonly="true" styleClass="BlueStyle-textbox"  styleId="dateTo"/>
	                <t:inputCalendar for="dateTo" format="dd/MM/yyyy"/>                
				</td>
    			<td class="col3Top"><bean:message key="screen.a_adt.label_reference"/><bean:message key="screen.a_adt.label_colon"/></td>
    			<td class="col4Top"><html:text name="_a_adtForm" property="reference" styleClass="UserTextBox" styleId="reference"/></td>
    		</tr>
    		<tr>
    			<td class="colRight"><bean:message key="screen.a_adt.label_FunName"/><bean:message key="screen.a_adt.label_colon"/></td>
    			<td class="colLeft">
						<t:defineCodeList id="LIST_MODULE" />
						<html:select name="_a_adtForm" property="subModuleID" styleId="subModuleID">
							<html:option value="" ><bean:message key="screen.a_adt.blankitem"/></html:option>
							<html:options collection="LIST_MODULE" property="id" labelProperty="name"/>
						</html:select>    			
    			</td>
    			<td class="colRight"><bean:message key="screen.a_adt.label_action"/><bean:message key="screen.a_adt.label_colon"/></td>
    			<td class="colLeft">
						<t:defineCodeList id="LIST_AUDIT_ACTION" />
						<html:select name="_a_adtForm" property="actionCbo" styleClass="DivDeptCombobox" styleId="actionCbo">
							<html:option value="" ><bean:message key="screen.a_adt.blankitem"/></html:option>
							<html:options collection="LIST_AUDIT_ACTION" property="id" labelProperty="name"/>
						</html:select>    			
    			</td>    			
    		</tr>
    		<tr>
    			<td class="colRight"></td>
    			<td class="colLeft">						   			
    			</td>
    			<td class="colRight"><bean:message key="screen.a_adt.label_user"/><bean:message key="screen.a_adt.label_colon"/></td>
    			<td class="colLeft"><html:text name="_a_adtForm" property="userName" styleClass="UserTextBox" styleId="userName"/></td>
    		</tr>
	        <tr>
	        	<td class="colBottom"><br></td>
	            <td class="colBottom"><br></td>
	            <td class="colBottom"><br></td>
	            <td class="colBottom"><br></td>
	        </tr>    		
    	</table>
    	<table class="buttonGroup" cellpadding="0" cellspacing="0">
    		<tr>
    			<td>
					<ts:submit styleClass="button" value="Search" property="forward_search" onclick="searchClick();"/>
					<bs:buttonLink action="/A_ADTS01SCR" value="Reset"/>
					<ts:submit value="link" property="forward_view" style="visibility:hidden"/> 
			</td>
    		</tr>
    	</table>    	
    	<table class="searchResultNo" cellpadding="0" cellspacing="0">
    		<tr>
    			<td>
					<bean:message key="screen.a_adt.label_search_result"/><bean:message key="screen.a_adt.label_colon"/>
					<c:if test="${_a_adtForm.totalAuditHeader != -1}">
						<bean:write name="_a_adtForm" property="totalAuditHeader"/>
					</c:if>    				
    			</td>
    		</tr>
    	</table>
		<table class="pageLink" cellpadding="0" cellspacing="0">
			<tr>
				<td><bean:message key="screen.a_adt.label_page_link"/>
					<bean:message key="screen.a_adt.label_colon"/>
					<ts:pageLinks 
			    		id="auditPageLinks"
						action="${pageContext.request.contextPath}/A_ADT/A_ADTR01BL.do" 
						name="_a_adtForm" 
						rowProperty="rowsPerPage"
						totalProperty="totalAuditHeader" 
						indexProperty="startIndex"
						currentPageIndex="now"
						submit="true"
						/>
					<logic:present name="auditPageLinks">  
						<bean:write name="auditPageLinks" filter="false"/>
					</logic:present>
				</td>
			</tr>
		</table>
		<table class="resultInfo" cellpadding="0" cellspacing="0" >
			<tr>
				<th class="header" width="3%"><bean:message key="screen.a_adt.label_number"/></th>
				<th class="header" width="20%"><bean:message key="screen.a_adt.label_date"/></th>
				<th class="header" width="20%"><bean:message key="screen.a_adt.label_FunName"/></th>
				<th class="header" width="14%"><bean:message key="screen.a_adt.label_reference"/></th>
				<th class="header" width="11%"><bean:message key="screen.a_adt.label_status"/></th>
				<th class="header" width="8%"><bean:message key="screen.a_adt.label_action"/></th>
				<th class="header" width="17%"><bean:message key="screen.a_adt.label_user"/></th>
			</tr>
			<logic:iterate id="auditHeader" name="_a_adtForm" property="auditHeaderList">
				<tr>
					<td class="defaultNo"><bean:write name="auditHeader" property="index"/></td>
					<td class="defaultText">

						<a href="#" onclick="viewAuditTrail('<bean:write name="auditHeader" property="auditID"/>');"><bean:write name="auditHeader" property="createdDate"/></a>
					</td>
					<td class="defaultText"><bean:write name="auditHeader" property="subModuleName"/></td>
					<td class="defaultText"><bean:write name="auditHeader" property="reference"/></td>
					<td class="defaultText">
						<c:if test="${auditHeader.subModuleID eq 'B-CPM' || auditHeader.subModuleID eq 'B-SSM'}">
							<logic:notEmpty name="auditHeader" property="status">
								<t:writeCodeValue codeList="LIST_PLANSTATUS_DISPLAY" name="auditHeader" property="status"/>
							</logic:notEmpty>
							&nbsp;
						</c:if>
						<c:if test="${!(auditHeader.subModuleID eq 'B-CPM' || auditHeader.subModuleID eq 'B-SSM')}">
							<bean:write name="auditHeader" property="status"/>
						</c:if>
					</td>
					<td class="defaultText"><bean:write name="auditHeader" property="action"/></td>
					<td class="defaultText">
						<logic:empty name="auditHeader" property="userName">
							<bean:write name="auditHeader" property="userID"/>
						</logic:empty>
						<logic:notEmpty name="auditHeader" property="userName">
							<bean:write name="auditHeader" property="userName"/>
						</logic:notEmpty>
					</td>
				</tr>		
			</logic:iterate>
		</table>		
		</td></tr></table>
    </ts:form>

   	<div class="error">
        <html:messages id="message">
            <bean:write name="message"/><br/>
        </html:messages>
    </div>
    <div class="message">
        <ts:messages id="message" message="true">
            <bean:write name="message" />
        </ts:messages>
    </div>
	</body>
</html:html>

