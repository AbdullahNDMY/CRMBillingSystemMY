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
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
    <script type="text/javascript">
    	function initLoad(){
    		if(parent.frame_top!=null&&parent.frame_top!=undefined) {
    			if(parent.frame_top.document.getElementById("userSessIsNullFlag") != null) {
        			if(parent.frame_top.document.getElementById("isLoginFlagAndUsrSesNull")!=null){
                		parent.frame_top.document.getElementById("isLoginFlagAndUsrSesNull").style.display="inline";
            		}
            		if (parent.frame_top.document.getElementById("loginUsedTr")!=null) {
            			parent.frame_top.document.getElementById("loginUsedTr").style.display="none";
            		}
        		}
    		}
    	}
    </script>
    <title></title>
</head>
<body onload="initLoad()">
<logic:notEmpty name="USER_VALUE_OBJECT" scope="session">
	<bean:message key="errors.ERR1SC026"/>
	<input type="hidden" value="<bean:message key="errors.ERR1SC026"/>" id="errorText"/>
</logic:notEmpty>
<logic:empty name="USER_VALUE_OBJECT" scope="session">
	<bean:message key="errors.ERR1SC091"/>
	<input type="hidden" value="<bean:message key="errors.ERR1SC091"/>" id="errorText"/>
</logic:empty>
</body>
</html:html>