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
   		<link type="text/css" rel="stylesheet" href="css/m_svg.css" />
   		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   		<script type="text/javascript" src="js/m_svg.js"></script>
		<title><bean:message key="screen.m_svgs01.label.title"/></title>
	</head>
	<ts:body>
			<table class="subHeader" cellpadding="0" cellspacing="0">
	    		<tr>
	    			<td><bean:message key="screen.m_svgs01.label.title"/></td>
	    		</tr>
	    	</table>
			<table cellpadding="0" cellspacing="0">
	    		<tr>
	    			<td>&nbsp;</td>
	    		</tr>
	    	</table>
			<table class="information" cellpadding="0" cellspacing="0">
				<tr>
					<td class="header" width="5%"><bean:message  key="screen.m_svgs01.label.id"/></td>
					<td class="header" width="15%"><bean:message key="screen.m_svgs01.label.service_group_name"/></td>
					<!--<td class="header" width="15%"><bean:message key="screen.m_svgs01.label.accounting_code"/></td>-->
					<td class="header" width="10%"><bean:message key="screen.m_svgs01.label.origin_code"/></td>
					<td class="header" width="10%"><bean:message key="screen.m_svgs01.label.account_code"/></td>
					<td class="header" width="15%"><bean:message key="screen.m_svgs01.label.journal_no"/></td>
					<td class="header" width="10%"><bean:message key="screen.m_svgs01.label.product_code"/></td>
					<td class="header" width="35%"><bean:message key="screen.m_svgs01.label.remark"/></td>
				</tr>
				<logic:iterate id="serviceGroupBean" name="_m_svgForm" property="listServiceGroupBean" >
					<tr>
						<td><bean:write name="serviceGroupBean" property="svc_grp"/></td> 
						<td><bean:write name="serviceGroupBean" property="svc_grp_name"/></td> 
						<!--<td><bean:write name="serviceGroupBean" property="acc_code"/></td>--> 
						<%-- <td>
							<t:writeCodeValue codeList="LIST_ORIGIN_CODE" key="${serviceGroupBean.origin_code}"></t:writeCodeValue>
						</td>  --%>
						<td><bean:write name="serviceGroupBean" property="origin_code"/></td>
						<td><bean:write name="serviceGroupBean" property="acc_code"/></td>
						<td><bean:write name="serviceGroupBean" property="journal_no"/>-<bean:write name="serviceGroupBean" property="description"/></td>
						<td><bean:write name="serviceGroupBean" property="product_code"/></td>
						<td><bean:write name="serviceGroupBean" property="remark"/></td> 
					</tr>		
				</logic:iterate>
				
			</table>
	</ts:body>
</html:html>

