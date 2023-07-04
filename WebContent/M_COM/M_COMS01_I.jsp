<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@page import="java.io.OutputStream"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   		<title></title>
	</head>
	<body id="m_com" onload="initMain();">
		<ts:errors />
	 
		
			<logic:notEmpty name="_m_comForm" property="photo">
            <bean:define id="def_photo" name="_m_comForm" property="photo"/>
            <%
            
			try
			{				
				byte[] img = (byte[])def_photo; 
				response.setContentType("image/gif");
				response.getOutputStream().write(img);				
				response.getOutputStream().flush();
				response.getOutputStream().close();
				out.clear();
				out = pageContext.pushBody(); 
			}
			catch(Exception e)
			{ 
			}
			%>
			</logic:notEmpty>
		
		
	</body>
</html:html>

