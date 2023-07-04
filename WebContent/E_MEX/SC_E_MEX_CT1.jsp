<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ page import="nttdm.bsys.common.fw.BillingSystemUserValueObject" %>
<%@page import="nttdm.bsys.common.util.CommonUtils"%> 

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<title><bean:message key="screen.e_mex_ct1.title" name="_e_mexForm" /></title>
	  <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
	  <link href="<%=request.getContextPath()%>/E_MEX/css/e_mex.css" rel="stylesheet" type="text/css" /> 
	  <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
	  <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery.spinbox.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/E_MEX/js/e_mex.js"></script>
	</head>
	
	<body id="e" onload="initMain();">
	<%
		BillingSystemUserValueObject uvo = (BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT");
	 	String useraccess = CommonUtils.getAccessRight(uvo, "E-MEX-CT1");
	%>
		<input type="hidden" styleId="userAccess" name="userAccess" value="<%=useraccess%>" />
		<input type="hidden" styleId="subModule" name="subModule" value="E-MEX-CT1" />
		<ts:form action="/RP_E_MEX_CT1SubmitBL" >
		    <html:hidden name="_e_mexForm" property="retStatusStr" styleId="retStatusStr"/>
			<t:defineCodeList id="DAY_OF_MONTH" />
			<h1 class="title"><bean:message key="screen.e_mex_ct1.title"/></h1>
			<div class="section">
				<t:defineCodeList id="LIST_RUNTIME_TYPE" />
				
				<h2><bean:message key="screen.e_mex.00001" /> <html:select property="runtimeType" styleId="cboRuntimes" onchange="return changeRuntime(this);">
					<html:options collection="LIST_RUNTIME_TYPE" property="id" labelProperty="name"/>
				</html:select></h2>
				<h2><bean:message key="screen.e_mex.00053"/></h2>
				<div class="group-content">
				<div class="group">
					<fieldset style="padding:0px 15px;">
						<legend><bean:message key="screen.e_mex.00054" /></legend>
						<table cellspacing="5" cellpadding="0" width="100%">
							<tr>
								<td nowrap="nowrap" align="right" width="28%">
									<bean:message key="screen.e_mex.00055" />:
								</td>
								<td width="72%">
									<html:select name="_e_mexForm" property="bankAcc" styleId="cboBankAcc">
										<option value=""> <bean:message key="screen.e_mex.00056"/> </option>
										<logic:present property="cbBankAcc" name="_e_mexForm">
											<html:optionsCollection name="_e_mexForm" property="cbBankAcc"/>
										</logic:present>
									</html:select>
								</td>
							</tr>
						</table>
					</fieldset>
				</div>
				</div>
				<div class="secion-content">
					<div id="scheduler" class="group">
						<h2><bean:message key="screen.e_mex.00002" /></h2>
						<div class="group-content">
							<fieldset>
								<legend><bean:message key="screen.e_mex.00026" /></legend>
								<div>
									<html:radio name="_e_mexForm" property="scheduleStatus" value="1" styleId="rdbActive" onclick="changeScheduleStatus(this);"></html:radio>
									<bean:message key="screen.e_mex.00003" />&nbsp;&nbsp;
									
									<html:radio name="_e_mexForm" property="scheduleStatus" value="0" styleId="rdbInActive" onclick="changeScheduleStatus(this);"></html:radio>
									<bean:message key="screen.e_mex.00004" />
									</div>
							</fieldset>
						</div>
						<div class="group-content" id="scheduler-time">
							<fieldset>
								<legend><bean:message key="screen.e_mex.00002" /></legend>
								<table border="0" cellpadding="0" cellspacing="5">
									<tr>
										<td colspan="2"><bean:message key="screen.e_mex.00006" />:</td>
										<td>
											<input type="hidden" name="" value='' />
											<bean:define id="scheduleDayDefault" name="_e_mexForm" property="scheduleDay"/> 
											<input type="hidden" name="scheduleDayDefault" value="<%=scheduleDayDefault %>"/>
											<c:if test="${_e_mexForm.map.scheduleStatus == 1}">
												<span id="scheduler-scheduleDay">
													<html:select name="_e_mexForm" property="scheduleDay">
														<html:option value="0"><bean:message key="screen.e_mex.00048" /></html:option>
														<html:options collection="DAY_OF_MONTH" property="id" labelProperty="name"/>
													</html:select>
												</span>
											</c:if>
											<c:if test="${_e_mexForm.map.scheduleStatus == 0}">
												<span id="scheduler-scheduleDay">
													<html:select name="_e_mexForm" property="scheduleDay" disabled="true">
														<html:option value="0"><bean:message key="screen.e_mex.00048" /></html:option>
														<html:options collection="DAY_OF_MONTH" property="id" labelProperty="name"/>
													</html:select>
												</span>
											</c:if>
											<c:if test="${_e_mexForm.map.scheduleStatus != 0 && _e_mexForm.map.scheduleStatus != 1}">
												<span id="scheduler-scheduleDay">
													<html:select name="_e_mexForm" property="scheduleDay">
														<html:option value="0"><bean:message key="screen.e_mex.00048" /></html:option>
														<html:options collection="DAY_OF_MONTH" property="id" labelProperty="name"/>
													</html:select>
												</span>
											</c:if>
										</td>
										<td>&nbsp;/ MM / YYYY</td>
									</tr>
									<tr>
										<td><bean:message key="screen.e_mex.00010" />&nbsp;:&nbsp;
											<c:if test="${_e_mexForm.map.scheduleStatus == 1}">
												<html:text name="_e_mexForm" property="scheduleHour" size="5" maxlength="2" styleId="cboScheduleHour" styleClass="resettable"></html:text>
											</c:if>
											<c:if test="${_e_mexForm.map.scheduleStatus == 0}">
												<html:text name="_e_mexForm" property="scheduleHour" size="5" maxlength="2" readonly="true" styleId="cboScheduleHour" styleClass="readonly resettable"></html:text>
											</c:if>
											<c:if test="${_e_mexForm.map.scheduleStatus != 1 && _e_mexForm.map.scheduleStatus != 0}">
												<html:text name="_e_mexForm" property="scheduleHour" size="5" maxlength="2" styleId="cboScheduleHour" styleClass="resettable"></html:text>
											</c:if>
										</td>
										<td align="right"><bean:message key="screen.e_mex.00011" />&nbsp;:&nbsp;</td>
										<td>
											<c:if test="${_e_mexForm.map.scheduleStatus == 1}">
												<html:text name="_e_mexForm" property="scheduleMinute" size="5" maxlength="2" styleId="cboScheduleMinute" styleClass="resettable"></html:text>
											</c:if>
											<c:if test="${_e_mexForm.map.scheduleStatus == 0}">
												<html:text name="_e_mexForm" property="scheduleMinute" size="5" maxlength="2" readonly="true" styleId="cboScheduleMinute" styleClass="readonly resettable"></html:text>
											</c:if>
											<c:if test="${_e_mexForm.map.scheduleStatus != 0 && _e_mexForm.map.scheduleStatus != 1}">
												<html:text name="_e_mexForm" property="scheduleMinute" size="5" maxlength="2" styleId="cboScheduleMinute" styleClass="resettable"></html:text>
											</c:if>
										</td>
										<td>&nbsp;</td>
									</tr>
								</table>
							</fieldset>
						</div>
						<div class="group-content">
							<input type="submit" class="button" value="<bean:message key="screen.e_mex.00012"/>" name="forward_update" id="btnUpdate"/>
						</div>
					</div>
					
					<div id="manual" class="group">
						<h2><bean:message key="screen.e_mex.00031" /></h2>
						<div class="group-content">
							<fieldset>
								<legend><bean:message key="screen.e_mex.00015" /></legend>
								<div>
								<bean:message key="screen.e_mex.00016" />&nbsp;&nbsp;&nbsp;&nbsp; 
								<bean:message key="screen.e_mex.00041" />&nbsp;:&nbsp;
								<html:text property="closingMonth" size="5" maxlength="2" styleId="cboClosingMonth"></html:text>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<bean:message key="screen.e_mex.00017" />&nbsp;:&nbsp;
								<html:text property="closingYear" size="5" maxlength="4" styleId="cboClosingYear"></html:text>
								</div>
							</fieldset>
						</div>
						<div class="group-content">
							<input type="submit" class="button" value="<bean:message key="screen.e_mex.00018"/>" name="forward_execute" id="btnExecute"/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="section">
				<h2><bean:message key="screen.e_mex.00019" /></h2>
				<div class="group-content">
					<div class="group result">
						<h2><bean:message key="screen.e_mex.00020" />: <bean:write name="_e_mexForm" property="totalRow"/></h2>
					</div>
					
					<div class="pageLink" style="width: 99%;"><bean:message key="screen.e_mex.00021"/>:
						<ts:pageLinks 
			    		id="curPageLinks"
							action="/SC_E_MEX_CT1SCR" 
							name="_e_mexForm" 
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
					    <th align="center" style="width: 80px"><bean:message key="screen.e_mex.00024"/></th>
					    <th><bean:message key="screen.e_mex.00016"/></th>
					    <th align="center"><bean:message key="screen.e_mex.00043"/></th>
					    <th align="center"><bean:message key="screen.e_mex.00026"/></th>
					    <th align="center" class="last"><bean:message key="screen.e_mex.00027"/></th>
					  </tr>
					  <c:forEach items="${_e_mexForm.map.historyList}" var="history">
					  <tr>
					  	<td align="center">${history.ID_CIT_EXP_BATCH}</td>
					  	<td>${history.CLOSE_MONTHYEAR}</td>
					  	<td align="center">${history.DATE_UPDATED_FMT}</td>
					  	<c:if test="${history.STATUS == 0}">
					  		<td align="center"><bean:message key="screen.e_mex.00034" /></td>
					  	</c:if>
					  	<c:if test="${history.STATUS == 1}">
					  		<td align="center"><bean:message key="screen.e_mex.00033" /></td>
					  	</c:if>
					  	<c:if test="${history.STATUS == 2}">
					  		<td align="center"><bean:message key="screen.e_mex.00040" /></td>
					  	</c:if>
					  	<td align="center" class="last">
					  		<!-- Export File -->
					  		<c:if test="${history.STATUS == 0}">
					  			<a href="<%=request.getContextPath()%>/E_MEX/RP_E_MEX_CT1DownloadBL.do?filename=${history.FILENAME}&idCitExpBatch=${history.ID_CIT_EXP_BATCH}"><bean:message key="screen.e_mex.00039" /></a>
					  			<c:if test="${history.LOGNUM == 0}">
					  				<bean:message key="screen.e_mex.00005" />
					  			</c:if>
					  			<c:if test="${history.LOGNUM > 0}">
					  				<a href="javascript:popupMsg('<%=request.getContextPath()%>/E_MEX/RP_E_MEX_CT1_2BL.do?idBatch=${history.ID_CIT_EXP_BATCH}');"><bean:message key="screen.e_mex.00005" /></a>
					  			</c:if>
					  		</c:if>
					  		<c:if test="${history.STATUS == 1}">
					  			<!-- Log -->
					  			<bean:message key="screen.e_mex.00039" />
					  			<a href="javascript:popupMsg('<%=request.getContextPath()%>/E_MEX/RP_E_MEX_CT1_2BL.do?idBatch=${history.ID_CIT_EXP_BATCH}');"><bean:message key="screen.e_mex.00005" /></a>
					  		</c:if>
					  	</td>
					  </tr>
					  </c:forEach>
					</table>
				</div>
			</div>
		</ts:form>
		<script>
		userAccessViewMode();
		</script>
			<div class="message">
				<ts:messages id="message" message="true" header="msg.header" footer="msg.footer">
					<bean:write name="message" />
				</ts:messages>
			</div>
			<div class="error">
				<html:messages id="message">
					<bean:write name="message"/><br/>
				</html:messages>
			</div>
	</body>
</html:html>