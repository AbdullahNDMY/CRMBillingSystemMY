<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%> 
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<html>
<head>
	<base target="_self"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
	<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
	<meta content="">
	<title>POPTER</title>
	<link type="text/css" rel="stylesheet" href="css/popup.css"/>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
   	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   	<script type="text/javascript" src="js/popup.js"></script>
</head>
<body class="popup" onload="POPACCClickInit();">
<ts:form action="/POPUP_COMMON_DSP.do">
<html:hidden name="_popupCommonForm" property="idSubInfo" styleId="idSubInfo"/>
<html:hidden name="_popupCommonForm" property="preAccessAccount" styleId="preAccessAccount"/>
<html:hidden name="_popupCommonForm" property="resultType" styleId="resultType"/>

<table border="0" cellpadding="0" cellspacing="2" class="popup" style="margin-top:20px;margin-left:5px;margin-bottom:5px;margin-left:20px;">
	<tr>
		<td valign="top">
			<img src="./images/alert.png" width="32" height="32"/>
		</td>
		<td>
		    Please note that old access account will be expired and new access account<br/>
			will immediately become valid once you click Save button.
		</td>
	</tr>
	<tr>
		<td colspan="2" style="padding-left:20px;">
			<table cellpadding="0" cellspacing="0" border="0" style="font-family:verdana,arial,serif,sans-serif;font-size:12;width:400px;">
			    <colgroup>
					<col width="40%"/>
					<col width="60%"/>
				</colgroup>
			    <tr style="background-color: #FFFFCC;height:40px;">
			        <td style="padding-left:2px;">
			            New Access Account<font style="color:red">*</font>:
			        </td>
			        <td>
			            <html:text name="_popupCommonForm" property="accessAccount" styleId="accessAccount" maxlength="50"/>
			        </td>
			    </tr>
			    <tr style="background-color: #FFFFCC;height:40px;">
			        <td style="padding-left:2px;">
			            New Access Password<font style="color:red">*</font>:
			        </td>
			        <td>
			            <html:text name="_popupCommonForm" property="accessPw" styleId="accessPw" maxlength="20"/>
			        </td>
			    </tr>
			</table>
		</td>
	</tr>
	<tr style="height:10px;">
	    <td></td><td></td>
	</tr>
	<tr>
	    <td colspan="2" style="padding-left:50px;">
	       <div style="color:red;" id="warningMsg">
		   </div>
	       Are you sure you want to Save?<br/>
	    </td>
	</tr>
	<tr>
	    <td colspan="2" style="padding-left:50px;padding-top:15px;">
	       <html:submit property="forward_changeAccessAccountSave" value="Save" style="width:70px;"/>
		   <input type="button" name="btnNo" value="Cancel" onclick="javascript: doPopterNo();" style="width:70px;"/>
	    </td>
	</tr>
	<tr>
	    <td colspan="2" style="padding-left:50px;padding-top:15px;">
	        <!-- message display start -->
			<div style="color:#0046D5;padding-left:20px;padding-top:0px;">
				<html:messages id="message" message="true">
					<bean:write name="message"/>
				</html:messages>
			</div>
			<div style="color:red;padding-left:20px;padding-top:0px;" id="errorInfo">
				<html:messages id="message">
					<bean:write name="message"/><br/>
				</html:messages>
			</div>
			<!-- message display end -->
	    </td>
	</tr>
</table>
<div id="ERR1SC105_CONTRACT_PERIOD" style="display:none;"><bean:message key="errors.ERR1SC105" arg0="Date selected is within contract period."/></div>
<div id="ERR1SC105_BILLING_PERIOD" style="display:none;"><bean:message key="errors.ERR1SC105" arg0="Date selected is within last billing period. You may need to create Credit Note."/></div>
<div id="ERR1SC105_1" style="display:none;"><bean:message key="errors.ERR1SC105" arg0="Invalid date selected. Date must be later than service start date."/></div>
<div id="ERR1SC105_2" style="display:none;"><bean:message key="errors.ERR1SC105" arg0="Invalid service end date. One time billing instruction selected. Please enter service end date."/></div>
<div id="ERR4SC019_1" style="display:none;"><bean:message key="info.ERR4SC019" arg0="You are about to reactivate a Radius Services, are you sure to reactivate?Yes[Reactivate],No[Cancel]"/></div>
<div id="ERR4SC019_2" style="display:none;"><bean:message key="info.ERR4SC019" arg0="You are about to terminate a Radius Services, are you sure to terminate?Yes[Terminate],No[Cancel]"/></div>

</ts:form>
</body>
</html>