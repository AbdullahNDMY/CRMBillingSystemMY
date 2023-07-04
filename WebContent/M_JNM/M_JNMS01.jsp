<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-html" prefix="html"%>
<%@ taglib uri="/struts-bean" prefix="bean"%>
<%@ taglib uri="/struts-logic" prefix="logic"%>
<%@ taglib uri="/terasoluna-struts" prefix="ts"%>
<%@ taglib uri="/terasoluna" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@page import="nttdm.bsys.common.fw.BillingSystemUserValueObject"%>
<%@page import="nttdm.bsys.common.util.CommonUtils"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common_01.css"/>
<link href="<%=request.getContextPath()%>/M_JNM/css/m_jnms01.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
<title><bean:message key="screen.m_jnm.title" /></title>
<script type="text/javascript">
	
	/**
		Performing when click Search Button
	 */
	function clickSearch() {
		
		var objFrm = document.forms['_m_jnmForm'];
		//Set value for clickEvent as 1
		objFrm['clickEvent'].value = "1";
			
	}

	/**
		Performing when click Reset Button
	 */
	function clickReset() {
		var objFrm = document.forms['_m_jnmForm'];
		objFrm['name_cust'].value = '';
		objFrm['id_cust'].value = '';
		objFrm['job_no'].value = '';
		objFrm['clickEvent'].value = '';
		objFrm.submit();
	}

	/**
		Performing when click New Button
	 */
	function clickNew(url) {
		var objFrm = document.forms['_m_jnmForm'];
		objFrm['clickEvent'].value = '';

		var width = window.screen.width * 50 / 100;
		var height = window.screen.height * 35 / 100;
		var left = Number((screen.availWidth / 2) - (width / 2));
		var top = Number((screen.availHeight / 2) - (height / 2));
		var offsetFeatures = "width=" + width + ",height=" + height + ",left="
				+ left + ",top=" + top + "screenX=" + left + ",screenY=" + top;
		var strFeatures = "toolbar=no, status=no, menubar=no,location=no,scrollbars=yes, resizable=yes"
				+ "," + offsetFeatures;
		var newwindow = window.open(url, null, strFeatures);
		if (window.focus) {
			newwindow.focus();
		}
	}

	/**
		Performing when click Delete Button
	 */
	function clickDel(id_jobno) {
		var MsgBox = new messageBox();
		if (MsgBox.POPCFM("Are you sure want to delete?") == MsgBox.YES_CONFIRM) {
			//if(confirm("Are you sure want to delete?")){
			var objFrm = document.forms['_m_jnmForm'];

			//Set value for clickEvent as 2
			objFrm['clickEvent'].value = "2";

			//Set value for clickEvent as id_jobno
			objFrm['hid_id_jobno'].value = id_jobno;

			//Submit form					
			objFrm.submit();
			//clickSearch();
		}
	}
</script>
</head>
<body id="m" onload="initMain();">
	<%

            String accessRight = ((nttdm.bsys.common.fw.BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT")).getUserAccessInfo("M", "M-JNM").getAccess_type();
            pageContext.setAttribute("accessRightBean", accessRight);
        %>

	<ts:form action="/M_JNMR01BLogic">
		<h1 class="title">
			<bean:message key="screen.m_jnm.title" />
		</h1>
		<div class="section"
			style="border-top: 2px solid #cecece; border-bottom: 2px solid #cecece; padding: 10px 5px; margin-top: -5px;">
			<table class="searchInfo" cellpadding="0" cellspacing="0">
				<tr>
					<td align="right"><bean:message
							key="screen.m_jnm.customerName" />&nbsp;:&nbsp;</td>
					<td><html:text property="name_cust" name="_m_jnmForm"
							maxlength="20" styleId="name_cust"></html:text>
					</td>
				</tr>
				<tr>
					<td align="right"><bean:message key="screen.m_jnm.customerId" />&nbsp;:&nbsp;
					</td>
					<td><html:text property="id_cust" name="_m_jnmForm"
							maxlength="20" styleId="idCust"></html:text>
					</td>
				</tr>
				<tr>
					<td align="right"><bean:message key="screen.m_jnm.jobNo" />&nbsp;:&nbsp;
					</td>
					<td><html:text property="job_no" name="_m_jnmForm"
							maxlength="20" styleId="job_no"></html:text>
					</td>
				</tr>
			</table>
		</div>
		<table class="buttonGroup" cellpadding="0" cellspacing="0">
			<tr>
				<td><input type="submit" class="button"
					value="<bean:message key="screen.m_jnm.searchbutton"/>"
					name="forward_search" id="search"
					onclick="clickSearch();showLoadingTipWindow();" /> <input
					type="button" class="button"
					value="<bean:message key="screen.m_jnm.resetbutton"/>"
					onclick="clickReset()" />			
                    <logic:equal value="2" name="accessRightBean">                            
						<input type="button" class="button"
							value="<bean:message key="screen.m_jnm.newbutton"/>"
							name="forward_new" onclick="clickNew('<%=request.getContextPath()%>/M_JNM/M_JNMR02BLogic.do')" />
                    </logic:equal>
                    <logic:notEqual value="2" name="accessRightBean" >
                          &nbsp;
                	</logic:notEqual>
				</td>
			</tr>
		</table>
		<table class="searchResultNo" cellpadding="0" cellspacing="0">
			<tr>
				<td><bean:message key="screen.m_jnm.searchtitle" /> <bean:message
						key="screen.m_jnm.colon" /> <bean:write name="_m_jnmForm"
						property="totalCount" />
				</td>
			</tr>
		</table>
		<table class="pageLink" cellpadding="0" cellspacing="0">
			<tr>
				<td><bean:message key="screen.m_jnm.gotopage" />&nbsp;<bean:message
						key="screen.m_jnm.colon" />&nbsp; <ts:pageLinks
						id="userPageLinks"
						action="${pageContext.request.contextPath}/M_JNM/M_JNMR01BLogic.do"
						name="_m_jnmForm" rowProperty="rowsPerPage"
						totalProperty="totalCount" indexProperty="startIndex"
						currentPageIndex="currentPageIndex" totalPageCount="total"
						submit="true" /> <logic:present name="userPageLinks">
						<bean:write name="userPageLinks" filter="false" />
					</logic:present></td>
			</tr>
		</table>

		<table  class="resultInfo" cellpadding="0" cellspacing="0" border="0">
			<tr class="jobTitle">
				<th width="10%" align="left">&nbsp;<bean:message
						key="screen.m_jnm.no" /></th>
				<th width="15%" align="left"><bean:message
						key="screen.m_jnm.customerId" /></th>
				<th width="25%" align="left"><bean:message
						key="screen.m_jnm.customerName" /></th>
				<th width="20%" align="left"><bean:message
						key="screen.m_jnm.jobNo" /></th>
				<th width="30%">&nbsp;</th>
			</tr>
			<c:if test="${not empty _m_jnmForm.jnmInfos}">
				<logic:iterate id="mp" name="_m_jnmForm" property="jnmInfos"
					indexId="index">
					<tr style="height:25px">
						<td width="10%"><bean:write name="mp" property="id_jobno" />
						</td>
						<td width="15%"><bean:write name="mp" property="id_cust" />
						</td>
						<td width="25%"><bean:write name="mp" property="name_cust" />
						</td>
						<td width="20%"><bean:write name="mp" property="job_no" />
						</td>
						<td width="30%" align="left">							
                        <logic:equal value="2" name="accessRightBean">                            
							<logic:equal value="0" property="hid_flag" name="mp">
								<a href="javascript:clickDel('<bean:write name="mp" property="id_jobno" />')">Delete
								</a>
							</logic:equal>
                        </logic:equal>
                        <logic:notEqual value="2" name="accessRightBean" >
                            &nbsp;
                        </logic:notEqual>
                        &nbsp;</td>
					</tr>
					<tr>
						<td style="border-bottom: #cfcfcf 1px solid" colspan="5"></td>
					</tr>
				</logic:iterate>
			</c:if>
		</table>
		<br />
		<html:hidden name="_m_jnmForm" property="clickEvent" />
		<html:hidden name="_m_jnmForm" property="hid_id_cust" />
		<html:hidden name="_m_jnmForm" property="hid_id_jobno" />
		<input id="refreshFlg" name="refreshFlg" type="hidden" value="0"/>
		<div class="error">
			<html:messages id="message">
				<bean:write name="message" />
				<br />
			</html:messages>
		</div>
		<div class="message">
			<ts:messages id="messages" message="true">
				<bean:write name="messages" />
				<br />
			</ts:messages>
		</div>
	</ts:form>
</body>
</html:html>

