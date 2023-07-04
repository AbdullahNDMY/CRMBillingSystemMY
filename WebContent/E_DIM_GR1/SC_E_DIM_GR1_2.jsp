<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
   		<link type="text/css" rel="stylesheet" href="css/e_dim_gr1.css" />
   		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
		<title>EOD-Data Import(SMBC Giro Collection Data)</title>
	</head>
	<body id="e" onload="initMain();">
		<div class="section">
			<h2><bean:message key="screen.e_dim_gr1.label.log"/></h2>
			<div class="group-content">
				<table class="datatable collapse" cellpadding="0" cellspacing="0" border="0">
				  <tr>
				    <th align="center"><bean:message key="screen.e_dim_gr1.label.log_id"/></th>
				    <th align="center"><bean:message key="screen.e_dim_gr1.label.error_message"/></th>
				    <th align="center" class="last"><bean:message key="screen.e_dim_gr1.label.log_date"/></th>
				  </tr>
				  <logic:iterate id="resultBean" name="_e_dim_gr1_Form" property="logList">
					<tr>
						<td class="defaultNo"><bean:write  name="resultBean" property="idBatchLog"/></td>
						<td class="defaultText"><bean:write name="resultBean" property="errorMsg"/></td>
						<td class="defaultText"><bean:write name="resultBean" property="dateUpdated"/></td>
					</tr>		
				</logic:iterate>
				</table>
			</div>
		</div>
		<br/>
		<div style="text-align: center;">
			<input type="button" onclick="window.close();" value='<bean:message key="screen.e_dim_gr1.button.close"/>'/>
		</div>
	</body>
</html:html>

