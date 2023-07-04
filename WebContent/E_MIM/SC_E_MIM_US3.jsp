<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<title><bean:message key="screen.e_mim_us3.title"/></title>
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
		<link href="${pageContext.request.contextPath}/E_MIM/css/e_mim.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
	  	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery.spinbox.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/common.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/E_MIM/js/e_mim_us3.js"></script>
	</head>
	<body id="e" onload="initMain();">
		<input type="hidden" id="activeStatusH" value="${_e_mimForm.map.activeStatus}" />
		<ts:form action="/SC_E_MIM_US3DSP" styleId="addForm">
			<h1 class="title"><bean:message key="screen.e_mim_us3.title"/></h1>
			<t:defineCodeList id="LIST_RUNTIME_TYPE" />
			<div class="section">
				<span>
					<h2><bean:message key="screen.e_mim_us3.runtime"/></h2>
						&nbsp;&nbsp;&nbsp;
						<select name="runtimeType" id="runtimeType">
							<c:forEach items="${LIST_RUNTIME_TYPE}" var="item">
								<c:if test="${(_e_mimForm.map.activeStatus + 2) == item.id}">
									<option value="${item.id }" selected="selected">${item.name }</option>
								</c:if>
								<c:if test="${(_e_mimForm.map.activeStatus + 2) != item.id}">
									<option value="${item.id }">${item.name }</option>
								</c:if>
							</c:forEach>
						</select>					
				</span>
			</div>
			<br/>
			<div class="section" id="scheduler">
				<h2><bean:message key="screen.e_mim_us3.scheduler"/></h2>
				<div class="group-content">
					<fieldset style="width:180px;">
						<legend class="legend-small">
							<bean:message key="screen.e_mim_us3.status"/>
						</legend>
						<html:radio name="_e_mimForm" property="activeStatus" value="1" onclick="changeScheduleStatus(this);"></html:radio>
						<bean:message key="screen.e_mim_us3.active"/>&nbsp;&nbsp;
						<html:radio name="_e_mimForm" property="activeStatus" value="0" onclick="changeScheduleStatus(this);"></html:radio>
						<bean:message key="screen.e_mim_us3.inactive"/>
					</fieldset>
					<br/>
					<fieldset style="width:420px;" id="scheduler-time">
						<legend class="legend-small">
							<bean:message key="screen.e_mim_us3.scheduler"/>
						</legend>
						<TABLE>
							<col width="10%"/><col width="10%"/><col width="10%"/><col width="20%"/>
							<tr>
								<td colspan="3"><bean:message key="screen.e_mim_us3.executeEveryMonth"/>&nbsp;</td>
								<td>
									<c:if test="${_e_mimForm.map.activeStatus == 0}">
										<html:text name="_e_mimForm" property="selDay" size="5" maxlength="2" readonly="true" styleClass="readonly">:</html:text>
									</c:if>
									<c:if test="${_e_mimForm.map.activeStatus == 1}">
										<html:text name="_e_mimForm" property="selDay" size="5" maxlength="2"></html:text>
									</c:if>									
									<c:if test="${_e_mimForm.map.activeStatus != 0 && _e_mimForm.map.activeStatus != 1}">
										<html:text name="_e_mimForm" property="selDay" size="5" maxlength="2"></html:text>
									</c:if>
									&nbsp;<bean:message key="screen.e_mim_us3.mmyyyy"/>
								</td>
							</tr>
							<tr>
								<td><bean:message key="screen.e_mim_us3.hour"/>&nbsp;:&nbsp;</td>
								<td>									
									<c:if test="${_e_mimForm.map.activeStatus == 0}">
										<html:text name="_e_mimForm" property="selHour" size="5" maxlength="2" readonly="true" styleClass="readonly"></html:text>
									</c:if>									
									<c:if test="${_e_mimForm.map.activeStatus == 1}">
										<html:text name="_e_mimForm" property="selHour" size="5" maxlength="2"></html:text>
									</c:if>									
									<c:if test="${_e_mimForm.map.activeStatus != 0 && _e_mimForm.map.activeStatus != 1}">
										<html:text name="_e_mimForm" property="selHour" size="5" maxlength="2"></html:text>
									</c:if>									
								</td>
								<td><bean:message key="screen.e_mim_us3.minute"/>&nbsp;:&nbsp;</td>
								<td>									
									<c:if test="${_e_mimForm.map.activeStatus == 0}">
										<html:text name="_e_mimForm" property="selMinute" size="5" maxlength="2" readonly="true" styleClass="readonly"></html:text>
									</c:if>								
									<c:if test="${_e_mimForm.map.activeStatus == 1}">
										<html:text name="_e_mimForm" property="selMinute" size="5" maxlength="2"></html:text>
									</c:if>								
									<c:if test="${_e_mimForm.map.activeStatus != 0 && _e_mimForm.map.activeStatus != 1}">
										<html:text name="_e_mimForm" property="selMinute" size="5" maxlength="2"></html:text>
									</c:if>										
								</td>
							</tr>
						</TABLE>
					</fieldset>
				<br/>
				<html:submit property="forward_update" value="Update" styleClass="button"></html:submit>
				<br/>
				</div>
			</div>
			<div class="section" id="manualExecute">
				<h2><bean:message key="screen.e_mim_us3.manualExecute"/></h2>
				<div class="group-content">
					<fieldset style="width:370px;">
						<legend class="legend-small">
							<bean:message key="screen.e_mim_us3.executeNow"/>
						</legend>
						<TABLE border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td>
									<bean:message key="screen.e_mim_us3.closingMonth"/>&nbsp;:&nbsp;
								</td>
								<td>
									<html:text name="_e_mimForm" property="month" size="5" maxlength="2"></html:text>
								</td>
								<td>
									&nbsp;&nbsp;<bean:message key="screen.e_mim_us3.year"/>&nbsp;:&nbsp;
								</td>
								<td>
									<html:text name="_e_mimForm" property="year" size="5" maxlength="4"></html:text>
								</td>
							</tr>
						</TABLE>
					</fieldset>
				<br/>
					<html:submit property="forward_execute" value="Execute" styleClass="button"></html:submit>
				<br/>
				</div>
			</div>
			
			<br/>
			<div class="section">
				<h2><bean:message key="screen.e_mim_us3.history"/></h2>
				<div class="group-content" style="width: 100%">
					<div class="group result">
						<h2>
							<bean:message key="screen.e_mim_us3.searchFound"/>
							<bean:message key="screen.e_mim_us3.colon"/>
							${_e_mimForm.map.totalRow}
						</h2>
					</div>
					<div class="pageLink">
						<bean:message key="screen.e_mim_us3.goToPage"/>
						<bean:message key="screen.e_mim_us3.colon"/>
						<ts:pageLinks 
			    			id="curPageLinks"
							action="/RP_E_MIM_US3_01BL" 
							name="_e_mimForm" 
							rowProperty="row"
							totalProperty="totalRow" 
							indexProperty="startIndex"
							currentPageIndex="now" 
							totalPageCount="total"/>
						<logic:present name="curPageLinks">  
							<bean:write name="curPageLinks" filter="false"/>
						</logic:present>
					</div>
					<table class="datatable collapse" cellpadding="0" cellspacing="0" border="0">
					  <tr>
					  	<th align="center" style="text-align: center;"><bean:message key="screen.e_mim_us3.no"/></th>
					    <th align="center" style="text-align: center;"><bean:message key="screen.e_mim_us3.closingMonth"/></th>
					    <th align="center" style="text-align: center;"><bean:message key="screen.e_mim_us3.executedDate"/></th>
					    <th align="center" style="text-align: center;"><bean:message key="screen.e_mim_us3.status"/></th>
					    <th align="center" style="text-align: center;"><bean:message key="screen.e_mim_us3.download"/></th>
					  </tr>
					  <c:forEach items="${_e_mimForm.map.listHistory}" var="item" varStatus="status">
					  	<tr>
						  	<td align="center">${item.ID_RAD_IMP_BATCH}</td>
						  	<td align="center">${item.CLOSE_MONTHYEAR}</td>
						  	<td align="center">
						  		<fmt:formatDate value="${item.DATE_UPLOADED}" pattern="dd/MM/yyyy"/> 
						  	</td>
						  	<td align="center">
						  		<c:if test="${item.STATUS == 0}">
						  			<bean:message key="screen.e_mim_us3.successful"/>
						  		</c:if>
						  		<c:if test="${item.STATUS == 1}">
						  			<bean:message key="screen.e_mim_us3.failed"/>
						  		</c:if>
						  		<c:if test="${item.STATUS == 2}">
						  			<bean:message key="screen.e_mim_us3.inprocess"/>
						  		</c:if>
						  	</td>
						  	<td align="center">
						  		&nbsp;
						  		<c:if test="${item.STATUS == 1}">
						  			<a href="javascript:popup('${pageContext.request.contextPath}/E_MIM/RP_E_MIM_US3_03BL.do?idRadImpBatch=${item.ID_RAD_IMP_BATCH}');">
						  				<bean:message key="screen.e_mim_us3.log"/>
						  			</a>
						  		</c:if>
						  	</td>
					  	</tr>
					  </c:forEach>
					</table>
				</div>
			</div>
		</ts:form>
			<div class="error">
				<html:messages id="message">
					<bean:write name="message"/><br/>
				</html:messages>
			</div>
	</body>
</html:html>

