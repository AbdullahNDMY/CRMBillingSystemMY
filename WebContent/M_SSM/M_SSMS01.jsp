<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-html" prefix="html"%>
<%@ taglib uri="/struts-bean" prefix="bean"%>
<%@ taglib uri="/struts-logic" prefix="logic"%>
<%@ taglib uri="/terasoluna-struts" prefix="ts"%>
<%@ taglib uri="/terasoluna" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="nttdm.bsys.common.fw.BillingSystemUserValueObject"%>
<%@page import="nttdm.bsys.common.util.CommonUtils"%>
<%@page import="nttdm.bsys.m_ssm.bean.ServiceObjectBean"%>
<%@page import="java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/common.css" />
		<link type="text/css" rel="stylesheet" href="css/m_ssm.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/common.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/messageBox.js"></script>
		<script type="text/javascript" src="js/m_ssm.js"></script>
		<title><bean:message key="screen.m_ssm01.label.title" /></title>
		<style type="text/css">		
		</style>		
		<bean:size id="totalResult" collection="${_m_ssmForm.map.listResult}" />
		<bean:size id="totalColumn" collection="${_m_ssmForm.map.listServiceBasicInfo}" />	
	</head>
	<body id="m" onload="initMain();init();">
		<%
		 	BillingSystemUserValueObject uvo = (BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT");
		 	String accessRight = CommonUtils.getAccessRight(uvo, "M-SSM");
		 	pageContext.setAttribute("accessRightBean", accessRight);
		%>
		<logic:notEqual value="2" name="accessRightBean">
		<script type="text/javascript">
			window.location = '${pageContext.request.contextPath}/M_SSM/M_SSMS01_02BL.do';
		</script>
		</logic:notEqual>
		<!-- check access right END -->
		
		<ts:form  action="/M_SSMS01_01BL" method="post" onsubmit="return checkForm();" styleId="_m_ssmForm">
			<input type="hidden" name="actionType" id="actionType" value="view" />
			<div id="deleteDiv" style="display: none;">
			</div>
			<t:defineCodeList id="LIST_SERVICE_ROW"/>
		   	<bean:define id="serviceRow" value="10"/>
		   	<%-- 
		   	<logic:iterate id="serviceRowRecord" name="LIST_SERVICE_ROW">
		   		<bean:define id="serviceRow" value="${serviceRowRecord.name}"/>
		   	</logic:iterate>
		   	 --%>
		   	<input type="hidden" id="serviceRow" value='<bean:write name="serviceRow"/>'/>	
			<%-- MESSAGES --%>
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<td>
					<table class="subHeader" cellpadding="0" cellspacing="0" id="subHeader">
						<tr>
							<td><bean:message key="screen.m_ssm01.label.title" /></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td style="padding-top:15px;padding-bottom:35px;">
					<bean:message key="screen.m_ssm.serviceGroup"/>
					<font color="red">*</font>: 
					<html:select name="_m_ssmForm" property="svc_grp" styleClass="CashBookTextBox" onchange="selectServiceGroup();">
						<html:option value=""><bean:message key="screen.m_ssm.blankItem"/></html:option>
						<logic:iterate id="listId" name="_m_ssmForm" property="listServiceGroup">
							<html:option value="${listId.svc_grp}">${listId.svc_grp_name}</html:option>
						</logic:iterate>
					</html:select>
				</td>
			</tr>
			<tr><td>
			<logic:notEmpty name="_m_ssmForm" property="svc_grp">
				<bean:message key="screen.m_ssm.label.e" /><bean:message key="screen.m_ssm.label.e_fullname" /><bean:message key="screen.m_ssm.label.r" /><bean:message key="screen.m_ssm.label.r_fullname" />
			<table class="listTable" id="resultTable" cellspacing="0" cellpadding="0">
				<tr>
					<td valign="top" style="height:100%;">
						<table id="serviceTypeTable" style="width:100%;height:100%" cellpadding="0" border="0" cellspacing="1">
							<tr>
							<logic:equal value="2" name="accessRightBean">
								<th style="width:10px;">
									<div class="divLink" onclick="addTableRow();">
										<bean:message key="screen.m_ssm.label.add" />
									</div>
								</th>
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
								<tr style="border-width:1px;border-color:black;border-style:solid;" id="row_${index}">							
									<logic:equal value="2" name="accessRightBean">
										<td class="deleteCell">
										<div style="cursor:hand; width:30px" onclick="submitDelete(this,'${_m_ssmForm.map.listSsmRows[index].serviceId }');">
											<b>X</b>
										</div>
										</td>
									</logic:equal>
									<td class="serviceLabelCell" nowrap="nowrap" style="text-align:left;">
										<div align="right" style="text-align: left;">${_m_ssmForm.map.listSsmRows[index].serviceDesc }
											<input type="hidden" name="idService" value="${_m_ssmForm.map.listSsmRows[index].serviceId }" />
										</div>
									</td>
								</tr>
							</logic:iterate>
							</table>
						</div>
					</td>
					<td valign="top">
						<div id="dataDiv" style="height:180px;width:600px;overflow:auto;" onscroll="scroll(this);">
							<table id="data" cellpadding="10" cellspacing="1" class="data" style="border-right: #efebef 1px solid;">
							<logic:iterate name="_m_ssmForm" property="listResult" id="beanItem" indexId="index">
								<tr class="tRow" id="row_${index}">
								<c:forEach var="c" items="${beanItem}" varStatus="status">
									<td style="border-bottom: #efebef 1px solid;">
										
										<div class="checkCell">
											<logic:equal value="2" name="accessRightBean">
												<c:choose>
													<c:when test="${c.infoId eq '28'}">
														<input value="1" onclick="checkER('entry_${index}_${status.index}');" id="xentry_${index}_${status.index}" name="xentry"
														disabled="disabled" type="checkbox" />
													</c:when>
													<c:otherwise>
														<input value="1" onclick="checkER('entry_${index}_${status.index}');" id="xentry_${index}_${status.index}" name="xentry"
														<logic:equal name="c" property="entryValue" value="1">checked="checked"</logic:equal> type="checkbox" />
													</c:otherwise>
												</c:choose>
												<input type="hidden" name="entry" id="entry_${index}_${status.index}" value="${c.entryValue}" />
												<input type="hidden" name="infoId" value="${c.infoId}"/>
											</logic:equal>
											<logic:notEqual value="2" name="accessRightBean">
												<input value="1" <logic:equal name="c" property="entryValue" value="1">checked="checked"</logic:equal>
													type="checkbox" disabled="disabled"/>
											</logic:notEqual>
										</div>
									</td>
									<td style="border-bottom: #efebef 1px solid;">
										<div class="checkCell">
											<logic:equal value="2" name="accessRightBean">
												<c:choose>
													<c:when test="${c.infoId eq '28'}">
														<input value="1" id="xreport_${index}_${status.index}" name="xreport"
															onclick="checkER('report_${index}_${status.index}');" type="checkbox"
															<logic:equal name="c" property="reportValue" value="1">checked="checked"</logic:equal> />
													</c:when>
													<c:otherwise>
														<input value="1" id="xreport_${index}_${status.index}" name="xreport"
															onclick="checkER('report_${index}_${status.index}');" type="checkbox"
															<logic:equal name="c" property="entryValue" value="0">disabled="disabled"</logic:equal> 
															<logic:equal name="c" property="reportValue" value="1">checked="checked"</logic:equal> />
													</c:otherwise>
												</c:choose>
												<input type="hidden" name="report" id="report_${index}_${status.index}" value="${c.reportValue}" />
											</logic:equal>
											<logic:notEqual value="2" name="accessRightBean">
												<input value="1" <logic:equal name="c" property="reportValue" value="1">checked="checked"</logic:equal>
													disabled="disabled" type="checkbox"/>
											</logic:notEqual>
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
			<br/>
			<logic:equal value="2" name="accessRightBean">
				<div>
					<input type="hidden" name="serviceInfoQuantity" value="${totalColumn}"/>
					<input type="submit" id="btnSave" name="btnSave" value="<bean:message key="screen.m_ssm.button.save"/>"/>
				</div>
			</logic:equal>
			</logic:notEmpty>
			<p>
			&nbsp;
			</p>
			</td></tr></table>
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
		</ts:form>
	
		<select id="idServiceClone" name="idService" class="CashBookTextBox" style="display: none;">
			<option value=""><bean:message key="screen.m_ssm.blankItem"/></option>
			<logic:iterate name="_m_ssmForm" property="listServiceType" id="serviceType" indexId="index">
				<option value="${serviceType.idService}"><bean:write name="serviceType" property="typeName"/></option>
		    </logic:iterate>
	    </select>
	    
	    <div id="messageDiv">
	    	<input type="hidden" id="confirmDeleteMessage" value='<bean:message key="errors.ERR4SC002" />' />
	    	<input type="hidden" id="duplicateMessage" value='<bean:message key="errors.ERR1SC006" arg0="Service" />' />
	    	<input type="hidden" id="mandatoryMessage" value='<bean:message key="errors.ERR1SC005" arg0="{0}" />' />
	    	<input type="hidden" id="minRecordMessage" value='<bean:message key="errors.ERR1SC068"/>'/>
	    </div>
	
	</body>
</html:html>