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
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/stylesheet/main.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/F0201/css/f0201s01.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/main.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   	<script type="text/javascript" src="<%=request.getContextPath()%>/F0201/js/f0201s01.js"></script>
    <title><bean:message key="screen.f0201s01.screen_name"/></title>
</head>
<body id="f0201s01">
	<table width="200" >
	  <tr>
	    <td height="6"></td>
	  </tr>
	</table>
	<table class="formSubHeader">
	  <tr style="">
	    <td ><strong>User Maintenance</strong></td>
	  </tr>
	</table>
	<table width="200" >
	  <tr>
	    <td height="6"></td>
	  </tr>
	</table>
	<table width="100%">
		<tr>
			<td>
				<table width="100%"  class="QCSTable">
					<tr>
						<td style="width:20%" align="right">User ID:&nbsp;&nbsp;&nbsp;</td>
						<td style="width:30%">
							<t:defineCodeList id="LIST_USER_ID" />
							<html:select name="_f0201Form" property="id_user" style="width:150px">
								<option></option>
								<html:options collection="LIST_USER_ID" property="id" labelProperty="name"/>
							</html:select>	
					    </td>
					    <td style="width:20%" align="right">Division:&nbsp;&nbsp;&nbsp;</td>
						<td style="width:30%">
							<t:defineCodeList id="LIST_DIVISION" />
							<html:select name="_f0201Form" property="id_div" style="width:150px">
								<option></option>
								<html:options collection="LIST_DIVISION" property="id" labelProperty="name"/>
							</html:select>	
					    </td>
					</tr>
					<tr>
						<td style="width:20%" align="right">User Name:&nbsp;&nbsp;&nbsp;</td>
						<td style="width:30%">
							<t:defineCodeList id="LIST_USER_NAME" />
							<html:select name="_f0201Form" property="user_name" style="width:150px">
								<option></option>
								<html:options collection="LIST_USER_NAME" property="id" labelProperty="name"/>
							</html:select>	
					    </td>
					    <td style="width:20%" align="right">Department:&nbsp;&nbsp;&nbsp;</td>
						<td style="width:30%">
							<t:defineCodeList id="LIST_DEPARTMENT" />
							<html:select name="_f0201Form" property="id_dept" style="width:150px">
								<option></option>
								<html:options collection="LIST_DEPARTMENT" property="id" labelProperty="name"/>
							</html:select>	
					    </td>
					</tr>
				</table>
			</td>			
		</tr>
		<tr>
			<td>
				<input type="button" name="forward_search" value="Search" />
				<input type="button" name="forward_reset" value="Reset" />
	        	<input type="button" name="forward_new" value="New" />
			</td>			
		</tr>
	</table>
</body>
</html:html>