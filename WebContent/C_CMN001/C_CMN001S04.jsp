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
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/C_CMN001/css/c_cmn001s04.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   	<script type="text/javascript" src="<%=request.getContextPath()%>/C_CMN001/js/c_cmn001s04.js"></script>
    <title><bean:message key="screen.common.c_cmn001s02"/></title>
</head>
<body id="c_cmn001s04" onload="initPage();">
<div id="mainMenuPanel" class="mainMenuPanel" >
	<table id="menu_content" class="menuContainer">
    	<tr><td id="labelToolbar" class="labelSubHeader">Toolbar</td></tr>
       	<tr>
			<td id="menuItem" class="menuItems">
 					<a class="parentMenu">Audit Trail</a>
 					<a class="parentMenu" onclick="showHide('menuAdministration');">Administration</a>
 						<div id="menuAdministration" class="hide"> 
 							<a class="subMenu" onclick="gotoPage('<%=request.getContextPath()%>/A_USR/A_USRS01SCR.do')">- User Maintenance</a>
 							<a class="subMenu">- User Access Control</a>
 							<a class="subMenu" onclick="gotoPage('<%=request.getContextPath()%>/A_PWD/A_PWDS01R02BL.do')">- Change Password</a>
 							<a class="subMenu" onclick="gotoPage('<%=request.getContextPath()%>/A_ADT/A_ADTS01SCR.do')">- Audit Trail</a>
 						</div>
 					<a class="parentMenu" onclick="showHide('menuMasterMaintenance');">Master Maintenance</a>
 						<div id="menuMasterMaintenance" class="hide">
 							<a class="subMenu" onclick="gotoPage('<%=request.getContextPath()%>/M_CST/M_CSTS01SCR.do')">- Customer Maintenance</a>
 							<a class="subMenu">- Plan Maintenance</a>
 							<a class="subMenu" onclick="gotoPage('<%=request.getContextPath()%>/M_WLM/M_WLMR01BLogic.do')">- Workflow List Maintenance</a>
 							<a class="subMenu" onclick="gotoPage('<%=request.getContextPath()%>/M_ATM/M_ATMS01SCR.do')">- Approval Authority Transfer</a>
 							<a class="subMenu" onclick="gotoPage('<%=request.getContextPath()%>/M_CDM/M_CDMR01BLogic.do')">- Code Maintenance</a>
 							<a class="subMenu">- Currency Maintenance</a>
 							<a class="subMenu">- Alert &amp; Notification</a>
 							<a class="subMenu" onclick="gotoPage('<%=request.getContextPath()%>/M_SVT/M_SVTS01.do')">- Service Maintenance</a>
 						</div>
 					<a class="parentMenu" onclick="showHide('menuBatchMaintenance');">Batch Maintenance</a>
 						<div id="menuBatchMaintenance" class="hide">
 							<a class="subMenu">- Export Billing Data</a>
 							<a class="subMenu">- Export Collection Data</a>
 							<a class="subMenu">- AR Reminder Generation</a>
 							<a class="subMenu">- AR Reports Generation</a>
 						</div>
 					<a class="parentMenu" onclick="showHide('menuQuotation');">Quotation Mgmt System</a>
 						<div id="menuQuotation" class="hide">
 							<a class="subMenu" onclick="gotoPage('<%=request.getContextPath()%>/Q_QCS/Q_QCSS01SCR.do')">- Quotation Control Sheet</a>
 						</div>
 					<a class="parentMenu" onclick="showHide('menuBilling');">Billing Mgmt System</a>
 						<div id="menuBilling" class="hide">
 							<a class="subMenu">- Billing Instruction Formádasd</a>
 							<a class="subMenu">- Billing Documents</a>
 							<a class="subMenu" onclick="gotoPage('<%=request.getContextPath()%>/C_CMN003/C_CMN003BLogic.do')">- Collection</a>
 							<a class="subMenu">- Statement of Accounts</a>
 							<a class="subMenu" onclick="gotoPage('<%=request.getContextPath()%>/B_CSB/B_CSBS01SCR.do')">- Cash Book</a>
 						</div>
			</td>
		</tr>
	</table>
</div>
</body>
</html:html>