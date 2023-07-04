<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ page import="nttdm.bsys.common.fw.BillingSystemUserValueObject" %>
<%@ page import="nttdm.bsys.c_cmn001.bean.UserAccess" %>
<%@ page import="java.util.List" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html:html locale="true">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Content-Script-Type" content="text/javascript">
    <meta http-equiv="Content-Style-Type" content="text/css">
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
    <meta name="Author" content="NTT Data Vietnam">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/a_pwds01.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/A_PWD/js/a_pwds01.js"></script>
    <title></title>
    
</head>
<body id="a" onload="initMain();">
<table class="subHeader">
  <tr>
    <td><bean:message key="screen.a_pwds01.changepwd"/></td>
  </tr>
</table>
<table>
  <tr>
    <td><bean:message key="screen.a_pwds01.note"/></td>
  </tr>
</table>
<ts:form action="/A_PWDS01R01BL.do">
	<table class="FormPWD">
		<tr>
			<th class="FormInputTitle"><bean:message key="screen.a_pwds01.lblUserID"/></th>
			<td class="FormColonTitle"><bean:message key="screen.a_pwds01.label_colon"/></td>
			<td class="FormInput"><bean:write name="_a_pwdForm" property="userID"/></td>
		</tr>
		<tr>
			<th class="FormInputTitle"><bean:message key="screen.a_pwds01.lblUserName"/></th>
			<td><bean:message key="screen.a_pwds01.label_colon"/></td>
			<td><bean:write name="_a_pwdForm" property="userName"/></td>
		</tr>
				<%							
							if(session.getAttribute("USER_VALUE_OBJECT") != null)
						 	{
							 BillingSystemUserValueObject uvo=(BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT");
							 List<UserAccess> listuser= uvo.getUser_access();
							 String useraccess="";
							 for(int i=0; i<listuser.size();i++)
								{
									if(listuser.get(i).getId_sub_module().equals("A-PWD"))
									useraccess=listuser.get(i).getAccess_type();
								}
								
							 if(useraccess.equals("2"))
					 			{
								
					%>	
		
		
		<tr>
			<th class="FormInputTitle"><bean:message key="screen.a_pwds01.txtOldPassword"/></th>
			<td><bean:message key="screen.a_pwds01.label_colon"/></td>
			<td><html:password name="_a_pwdForm"  property="oldPassword" styleClass="UserTextBox" maxlength="15"/></td>
		</tr>
		<tr>
			<th class="FormInputTitle"><bean:message key="screen.a_pwds01.txtNewPassword"/></th>
			<td><bean:message key="screen.a_pwds01.label_colon"/></td>
			<td><html:password name="_a_pwdForm"  property="newPassword" styleClass="UserTextBox" maxlength="15"/></td>
		</tr>
		<tr>
			<th class="FormInputTitle"><bean:message key="screen.a_pwds01.txtReEnterNewPassword"/></th>
			<td><bean:message key="screen.a_pwds01.label_colon"/></td>
			<td><html:password name="_a_pwdForm"  property="reEnterPassword" styleClass="UserTextBox" maxlength="15"/></td>
		</tr>	
	</table>
	<table border="0" cellspacing="10">
		<tr>
			<td colspan="2" align="left"><ts:submit value="Save"/></td>
		</tr>						
	</table>	
			<%
				}
				else
				{
			 %>
			 </table>
			 <%
			 }
			 }
			  %>
			 
 </ts:form>		
	<div class="error">
		<html:messages id="message">
			<bean:write name="message"/><br/>
		</html:messages>
	</div>
    <div class="error">
    	<ts:messages id="message" message="true"><bean:write name="message"/></ts:messages>
    	<span id="errorArea"></span>
    </div>
 </body>
</html:html>

