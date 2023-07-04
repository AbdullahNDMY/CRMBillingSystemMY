<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jp.terasoluna.fw.web.struts.taglib.RandomUtil" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
response.setHeader("Cache-Control", "no-cache");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN">


<%@page import="org.apache.jasper.tagplugins.jstl.core.Redirect"%><html:html locale="true">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Content-Script-Type" content="text/javascript">
    <meta http-equiv="Content-Style-Type" content="text/css">
    <meta name="Author" content="NTT Data Vietnam">
    <title><bean:message key="screen.common.c_cmn001s02"/></title>
    <script type="text/javascript">
    function showMenu(isShow) {
        if(isShow) {
    		parent.document.getElementById("frame_content").cols = "218px,*";
        }
        else {
        	parent.document.getElementById("frame_content").cols = "0px,*";
        }
    }
    </script>
</head>
<frameset rows="84px,*" framespacing="0">
    <!-- Header -->
        
    <frame src="<%=request.getContextPath()%>/C_CMN001/C_CMN001S03SCR.do" name="frame_top" scrolling="no" noresize="noresize" frameborder="0" marginheight="0" marginwidth="0">
    <frameset cols="0px,*" framespacing="0" id="frame_content">
        <!-- Menu -->
        <frame name="frame_menu" src="<%=request.getContextPath()%>/C_CMN001/C_CMN001S05SCR.do" scrolling="auto" noresize="noresize" frameborder="0" marginheight="0" marginwidth="0">
        <!-- Body -->
        <logic:empty name="USER_VALUE_OBJECT" scope="session">
		
		<frame src="<%=request.getContextPath()%>/C_CMN001/C_CMN001S01SCR.do" name="frame_main" scrolling="yes" noresize="noresize" frameborder="0" marginheight="0" marginwidth="0">
						
		</logic:empty>
        
        <logic:notEmpty name="USER_VALUE_OBJECT" scope="session">
        
        <frame src="<%=request.getContextPath()%>/C_CMN002/C_CMN002BLogic.do" name="frame_main" scrolling="yes" noresize="noresize" frameborder="0" marginheight="0" marginwidth="0">
        </logic:notEmpty>
    </frameset>
</frameset>	
</html:html>