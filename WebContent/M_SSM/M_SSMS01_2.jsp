<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-html" prefix="html"%>
<%@ taglib uri="/struts-bean" prefix="bean"%>
<%@ taglib uri="/struts-logic" prefix="logic"%>
<%@ taglib uri="/terasoluna-struts" prefix="ts"%>
<%@ taglib uri="/terasoluna" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/common.css" />
<link type="text/css" rel="stylesheet" href="css/m_ssm.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/messageBox.js"></script>
<script type="text/javascript" src="js/m_ssm.js"></script>
<title><bean:message key="screen.m_ssm01.label.title" /></title>

<bean:size id="totalColumn" collection="${_m_ssmForm.map.listServiceBasicInfo}" />

<script language="javascript" type="text/javascript">
	
</script>
</head>
<ts:body onload="init();">

<ts:form  action="/M_SSMS01_02BL" method="post" onsubmit="return checkForm();" styleId="_m_ssmForm">
	<input type="hidden" name="actionType" id="actionType" value="view" />
	<t:defineCodeList id="LIST_SERVICE_ROW"/>
   	<bean:define id="serviceRow" value="10"/>
   	<%-- 
   	<logic:iterate id="serviceRowRecord" name="LIST_SERVICE_ROW">
   		<bean:define id="serviceRow" value="${serviceRowRecord.name}"/>
   	</logic:iterate>
   	--%>
   	<input type="hidden" id="serviceRow" value='<bean:write name="serviceRow"/>'/>
	<%-- MESSAGES --%>
	<table style="width:100%;">
		<tr>
			<td id="subHeader" class="subHeader" style="width:100%;">
				<bean:message key="screen.m_ssm01.label.title" />
			</td>
		</tr>
		<tr>
			<td style="padding-top: 15px">
				<bean:message key="screen.m_ssm.serviceGroup" />
				<font color="red">*</font>&nbsp;&nbsp;:
				<html:select name="_m_ssmForm" property="svc_grp" styleClass="CashBookTextBox" onchange="selectServiceGroup();">
					<html:option value=""><bean:message key="screen.m_ssm.blankItem"/></html:option>
					<logic:iterate id="listId" name="_m_ssmForm" property="listServiceGroup">
						<html:option value="${listId.svc_grp}">${listId.svc_grp_name}</html:option>
					</logic:iterate>
				</html:select>
			</td>
		</tr>
		<tr>
			<td>
				<p>&nbsp;</p>
				<!-- MESSAGES and ERRORS -->
				<div class="error" id="error_area">
				<logic:iterate name="_m_ssmForm" property="errorMessages" id="message" indexId="index">
					<bean:message key="${message}"/>	
				</logic:iterate>
				</div>
				<div class="success" id="success_area">
				<logic:iterate name="_m_ssmForm" property="successMessages" id="message" indexId="index">	
					<bean:message key="${message}"/>
				</logic:iterate>
				</div>
			</td>
		</tr>
		<tr>
			<td>
			<logic:notEmpty name="_m_ssmForm" property="svc_grp">
				<table class="listTable" id="resultTable" cellspacing="0" cellpadding="0">
					<tr>
						<td valign="top" style="height:100%;">
							<table id="serviceTypeTable" style="width:100%;height:100%;" cellpadding="0" border="0" cellspacing="1">
								<tr>
								<logic:equal value="2" name="accessRightBean">
									<th style="width:10px;"><div class="divLink" onclick="addTableRow();"><bean:message
										key="screen.m_ssm.label.add" /></div></th>
								</logic:equal>
									<th>
										<bean:message key="screen.m_ssm.label.service_type" />
										<input type="hidden" id="labelService" value='<bean:message key="screen.m_ssm.label.service_type" />'/>
									</th>
								</tr>
							</table>
						</td>
						<td valign="top">
							<div id="labelDiv" style="width:600px;overflow:hidden;">
								<table id="labelTable" border="0" cellpadding="10" cellspacing="1" class="labelTable">
									<tr>
									<logic:iterate id="serviceBasicInfo" name="_m_ssmForm" property="listServiceBasicInfo">
										<th colspan="2" title="header">
											<bean:write name="serviceBasicInfo" property="VALUE"/>
										</th>
									</logic:iterate>
									</tr>
									<tr>
									<logic:iterate id="serviceBasicInfo" name="_m_ssmForm" property="listServiceBasicInfo">
										<td><div class="checkLabelCell"><bean:message key="screen.m_ssm.label.e" /></div></td>
										<td><div class="checkLabelCell"><bean:message key="screen.m_ssm.label.r" /></div></td>
									</logic:iterate>
									</tr>
								</table>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top">
							<div id="serviceDiv" style="height:180px;overflow:hidden;">
								<table id="serviceTable" border="0" cellpadding="10" cellspacing="1" class="serviceTable">
								<logic:iterate name="_m_ssmForm" property="listResult" id="beanItem" indexId="index">
									<tr id="row_${index}">
										<td class="serviceLabelCell" nowrap="nowrap" style="text-align:left;border-left: #efebef 1px solid;padding-left:10px;">
											<div style="text-align: left;">${_m_ssmForm.map.listSsmRows[index].serviceDesc }</div>
										</td>
									</tr>
								</logic:iterate>
								</table>
							</div>
						</td>
						<td valign="top">
							<div id="dataDiv" style="height:180px;width:600px;overflow:auto;" onscroll="scroll(this);">
								<table id="data" border="0" cellpadding="10" cellspacing="1" class="data">
								<logic:iterate name="_m_ssmForm" property="listResult" id="beanItem" indexId="index">	
									<tr id="row_${index}" title="dataRow">
										<c:forEach var="c" items="${beanItem}" varStatus="status">
											<td style="border-bottom: #efebef 1px solid;">
												<div class="checkCell">
													<img alt="" width="18px" src='${pageContext.request.contextPath}/image/${c.entryValue == "1" ? "tick.gif" : "blank.gif"}' style="height:17px" />
												</div>
											</td>
											<td style="border-bottom: #efebef 1px solid;">
												<div class="checkCell">
													<img alt="" width="18px" src='${pageContext.request.contextPath}/image/${c.reportValue == "1" ? "tick.gif" : "blank.gif"}' style="height:17px" />
												</div>
											</td>
										</c:forEach>
									</tr>
								</logic:iterate>
								</table>
							</div>
						</td>
						<td>
						</td>
					</tr>
				</table>
			</logic:notEmpty>
			</td>
		</tr>
	</table>
</ts:form>
</ts:body>

</html:html>