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
   		<link type="text/css" rel="stylesheet" href="css/m_csts01.css" />
   		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   		<script type="text/javascript" src="js/m_csts01.js"></script>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>		
		<title></title>
	</head>
	<body id="m" onload="initMain();initPage();">
	<ts:form action="/M_CSTS01DSP">
		<html:hidden name="_m_cstForm" property="event"/>
		<html:hidden name="_m_cstForm" property="mode"/>
		<html:hidden name="_m_cstForm" property="startIndex"/>
		<html:hidden name="_m_cstForm" property="id_cust"/>
		<html:hidden name="_m_cstForm" property="temp_cust_acc_no"/>
		<html:hidden name="_m_cstForm" property="temp_cust_name"/>
		<html:hidden name="_m_cstForm" property="temp_co_regno"/>
		<html:hidden name="_m_cstForm" property="temp_country"/>
		
		<t:defineCodeList id="LIST_COUNTRY"/>
		<table class="subHeader" cellpadding="0" cellspacing="0">
    		<tr>
    			<td><bean:message key="screen.m_cst.screen_name"/></td>
    		</tr>
    	</table>
    	<table class="inputInfo" cellpadding="0" cellspacing="0">
    		<tr>
    			<td class="col1Top" width="15%"><bean:message key="screen.m_cst.label_customer_acc_no"/><bean:message key="screen.m_cst.label_colon"/></td>
    			<td class="col2Top" width="30%"><html:text name="_m_cstForm" property="cust_acc_no" tabindex="1"/></td>
    			<td class="col3Top" width="13%"><bean:message key="screen.m_cst.label_co_reg_no"/><bean:message key="screen.m_cst.label_colon"/></td>
    			<td class="col4Top"><html:text name="_m_cstForm" property="co_regno" tabindex="2"/></td>
    		</tr>
    		<tr>
    			<td class="colRight"><bean:message key="screen.m_cst.label_customer_name"/><bean:message key="screen.m_cst.label_colon"/></td>
    			<td class="colLeft"><html:text name="_m_cstForm" property="cust_name" tabindex="3"/></td>
    			<td class="colRight"><bean:message key="screen.m_cst.label_billing_country"/><bean:message key="screen.m_cst.label_colon"/></td>
    			<td class="colLeft"><html:text name="_m_cstForm" property="country" tabindex="4"/></td>
    		</tr>
	        <tr>
	        	<td class="colBottom"><br></td>
	            <td class="colBottom"><br></td>
	            <td class="colBottom"><br></td>
	            <td class="colBottom"><br></td>
	        </tr>    		
    	</table>
    	<table class="buttonGroup" cellpadding="0" cellspacing="0">
    		<tr>
    			<td>
    				<button onclick="submitForm('search','','');"><bean:message key="screen.m_cst.button_search"/></button>&nbsp;
    				<button onclick="submitForm('reset','','');"><bean:message key="screen.m_cst.button_reset"/></button>&nbsp;
    				<button onclick="submitForm('new','NEWMODE','');"><bean:message key="screen.m_cst.button_new"/></button>
    			</td>
    		</tr>
    	</table>
    	<table class="searchResultNo" cellpadding="0" cellspacing="0">
    		<tr>
    			<td>
					<bean:message key="screen.m_cst.label_search_result"/><bean:message key="screen.m_cst.label_colon"/><bean:write name="_m_cstForm" property="totalCount"/>    				
    			</td>
    		</tr>
    	</table>
		<table class="pageLink" cellpadding="0" cellspacing="0">
			<tr>
				<td><bean:message key="screen.m_cst.label_page_link"/>
					<bean:message key="screen.m_cst.label_colon"/>
					<ts:pageLinks 
			    		id="curPageLinks"
						action="/M_CSTR01BLogic" 
						name="_m_cstForm" 
						rowProperty="row"
						totalProperty="totalCount" 
						indexProperty="startIndex"
						currentPageIndex="now" 
						totalPageCount="total"/>
					<logic:present name="curPageLinks">
						<bean:write name="curPageLinks" filter="false"/>
					</logic:present>
				</td>
			</tr>
		</table>
		<table class="resultInfo" cellpadding="0" cellspacing="0">
			<tr>
				<td class="header" width="10%"><bean:message key="screen.m_cst.label_number"/></td>
				<td class="header" width="23%"><bean:message key="screen.m_cst.label_customer_acc_no"/></td>
				<td class="header" width="23%"><bean:message key="screen.m_cst.label_customer_name"/></td>
				<td class="header" width="22%"><bean:message key="screen.m_cst.label_co_reg_no"/></td>
				<td class="header" width="22%"><bean:message key="screen.m_cst.label_billing_country"/></td>
			</tr>
			<logic:iterate id="resultBean" name="_m_cstForm" property="cusBeans">
				<tr>
					<td class="defaultNo"><bean:write name="resultBean" property="index"/></td>
					<td class="defaultText">
						<bean:define id="id" name="resultBean" property="id_cust"></bean:define>
						<a href="#" onclick="submitForm('view','READONLY','<%=id %>');"><bean:write name="resultBean" property="cust_acc_no"/></a>
					</td>
					<td class="defaultText"><bean:write name="resultBean" property="cust_name"/></td>
					<td class="defaultText"><bean:write name="resultBean" property="co_regno"/></td>
					<td class="defaultText">
						<t:writeCodeValue name="resultBean" property="country" codeList="LIST_COUNTRY"></t:writeCodeValue>
					</td>
				</tr>		
			</logic:iterate>
		</table>
    </ts:form>	
    <div class="error">
		<html:messages id="message">
			<bean:write name="message"/><br/>
		</html:messages>
	</div>
	<div class="message">
		<ts:messages id="message" message="true">
			<bean:write name="message" />
		</ts:messages>
	</div>
	</body>
</html:html>

