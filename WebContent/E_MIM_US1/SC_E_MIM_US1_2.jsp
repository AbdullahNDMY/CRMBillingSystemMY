<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
   		<link type="text/css" rel="stylesheet" href="css/e_mim_us1.css" />
   		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   		<script type="text/javascript" src="js/e_mim_us1_2.js"></script>
		<title><bean:message key="screen.e_mim_us1.screen_name"/></title>
	</head>
	<body id="e">
		<ts:form action="/E_MIM_US2Blogic">
			<table class="subHeader" cellpadding="0" cellspacing="0">
	    		<tr>
	    			<td><bean:message key="screen.e_mim_us1.screen_name"/></td>
	    		</tr>
	    	</table>
	    	
			<table class="information" cellpadding="0" cellspacing="1">
				<tr>
					<td colspan="4" class="header"><bean:message key="screen.e_mim_us1.label.log"/></td>
				</tr>
			</table>	
	    	<br/>
	    	
			<table class="resultInfo" cellpadding="0" cellspacing="0">
				<tr>
					<td class="header"><bean:message  key="screen.e_mim_us1.label.log_id"/></td>
					<td class="header"><bean:message key="screen.e_mim_us1.label.error_message"/></td>
					<td class="header"><bean:message key="screen.e_mim_us1.label.log_date"/></td>
				</tr>
				<logic:iterate id="resultBean" name="_e_mim_us1_Form" property="logList">
					<tr>
						<td class="defaultNo"><bean:write  name="resultBean" property="idBatchLog"/></td>
						<td class="defaultText"><bean:write name="resultBean" property="errorMsg"/></td>
						<td class="defaultText"><bean:write name="resultBean" property="dateUpdated"/></td>
					</tr>		
				</logic:iterate>
				
			</table>
            <br/>
            <br/>
            <br/>
            <br/>
            <table width="100%">
            	<tr>
					<td align="center">
						<button onclick="window.close();"><bean:message key="screen.e_mim_us1.button.close"/> </button>
					</td>
				</tr>
            	
            </table>
		</ts:form>
	</body>
</html:html>

