<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@page import="java.io.OutputStream"%>  
<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   		<title></title>
	</head>
	<body>
		<logic:notEmpty name="frm_AUSRS02" property="user">
           <bean:define id="user" name="frm_AUSRS02" property="user"/>
           <%
			try {				
				nttdm.bsys.a_usr.dto.User usr = (nttdm.bsys.a_usr.dto.User) user;
				byte[] img = (byte[])usr.getPhotoByte(); 
				response.setContentType("image/gif");
				response.getOutputStream().write(img);				
				response.getOutputStream().flush();
				response.getOutputStream().close();
				out.clear();
				out = pageContext.pushBody(); 
			}
			catch(Exception e) {}
			%>
		</logic:notEmpty>
	</body>
</html:html>