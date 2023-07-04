<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
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
		<title><bean:message key="screen.e_exp_s01.title"/></title>
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
		<link href="<%=request.getContextPath()%>/E_EXP/css/e_exp.css" rel="stylesheet" type="text/css" /> 
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery.spinbox.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/E_EXP/js/E_EXP_S01.js"></script>
	</head>
	
	<body id="e" onload="initMain();">
	<%
		BillingSystemUserValueObject uvo = (BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT");
	 	String useraccess = CommonUtils.getAccessRight(uvo, "E-EXP-S01");
	%>
		<input type="hidden" id="userAccess" name="userAccess" value="<%=useraccess%>"/>
		<ts:form action="/SC_E_EXP_S01DSP" >
			<t:defineCodeList id="DAY_OF_MONTH" />
			<h1 class="title"><bean:message key="screen.e_exp_s01.title"/></h1>
			<div class="section">
				<div class="secion-content">
					
					<div id="manual" class="group">
						<h2><bean:message key="screen.e_exp_s01.manualExecute"/></h2>
						<div class="group-content">
							<fieldset>
								<legend><bean:message key="screen.e_exp_s01.executeNow" /></legend>
								<div>
								<!-- Delete #146 Start-->
								<!-- <bean:message key="screen.e_exp_s01.invoicePeriod" />&nbsp;&nbsp;&nbsp;&nbsp; 
								<bean:message key="screen.e_exp_s01.month" />&nbsp;:&nbsp;
								<html:text property="closingMonth" size="5" maxlength="2" styleId="cboClosingMonth"></html:text>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<bean:message key="screen.e_exp_s01.year" />&nbsp;:&nbsp;
								<html:text property="closingYear" size="5" maxlength="4" styleId="cboClosingYear"></html:text>-->
								<!-- Delete #146 End-->
								<!-- Add #146 Start-->
								<html:hidden property="customerTypeDispFlg" styleId="hidCustomerTypeDispFlg"></html:hidden>
								<table cellpadding="0" cellspacing="0">
									<tr>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="screen.e_exp_s01.invoicePeriod" />&nbsp;&nbsp;</td>
										<td><bean:message key="screen.e_exp_s01.month" /></td>
										<td>:&nbsp;</td>
										<td><html:text property="closingMonth" size="5" maxlength="2" styleId="cboClosingMonth"></html:text>&nbsp;&nbsp;</td>
										<td><bean:message key="screen.e_exp_s01.year" />&nbsp;:&nbsp;</td>
										<td><html:text property="closingYear" size="5" maxlength="4" styleId="cboClosingYear"></html:text></td>
									</tr>
									<tr>
										<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="screen.e_exp_s01.customerType" /></td>
										<td>:&nbsp;</td>
										<td colspan="3">
											<t:defineCodeList id="CUSTOMER_TYPE" />
											<c:if test="${e_expForm.map.customerTypeDispFlg eq 'SG01'}"> 
												<html:select property="customerType" styleId="cboCustomerType" style="width:200px">
													<html:options collection="CUSTOMER_TYPE" property="id" labelProperty="name" />
												</html:select>
											</c:if>
											<c:if test="${e_expForm.map.customerTypeDispFlg != 'SG01'}"> 
												<html:select property="customerType"  styleId="cboCustomerType" disabled="true" style="width:200px">
													<html:options collection="CUSTOMER_TYPE" property="id" labelProperty="name" />
												</html:select>
											</c:if>
										</td>
									</tr>
								</table>
								<!-- Add #146 End-->
								</div>
							</fieldset>
						</div>
						<div class="group-content">
							<input type="submit" class="button" value="<bean:message key="screen.e_exp_s01.execute"/>" name="forward_execute" id="btnExecute"/>
							<input type="button" id="refresh" value="<bean:message key="screen.e_exp_s01.refresh"/>" onclick="javascript:window.location='<%=request.getContextPath()%>/E_EXP/PR_E_EXP_S01InitBL.do';"/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="section">
				<h2><bean:message key="screen.e_exp_s01.history" /></h2>
				<div class="group-content">
					<div class="group result">
						<h2><bean:message key="screen.e_exp_s01.searchFound" />: <bean:write name="e_expForm" property="totalRow"/></h2>
					</div>
					
					<div class="pageLink" style="width: 99%;"><bean:message key="screen.e_exp_s01.gotoPage"/>:
						<ts:pageLinks 
			    		id="curPageLinks"
							action="${pageContext.request.contextPath}/E_EXP/PR_E_EXP_S01InitBL.do?typeFlg=pageLink" 
							name="e_expForm" 
							rowProperty="row"
							totalProperty="totalRow" 
							indexProperty="startIndex"
							currentPageIndex="now" 
							totalPageCount="total"
							submit="true"/>
					  <logic:present name="curPageLinks">  
							<bean:write name="curPageLinks" filter="false"/>
						</logic:present>
					</div>
					<table class="datatable collapse" cellpadding="0" cellspacing="0" border="0">
					  <tr>
					    <th align="center" style="width: 80px"><bean:message key="screen.e_exp_s01.no"/></th>
					    <th><bean:message key="screen.e_exp_s01.invoicePeriod"/></th>
					    <th align="center"><bean:message key="screen.e_exp_s01.executedDate"/></th>
					    <th align="center"><bean:message key="screen.e_exp_s01.status"/></th>
					    <th align="center" class="last"><bean:message key="screen.e_exp_s01.download"/></th>
					  </tr>
					  <c:forEach items="${e_expForm.map.listHistories}" var="history" varStatus="status">
					  <tr>
					  	<td align="center">${e_expForm.map.totalRow - (e_expForm.map.startIndex + status.index)}</td>
					  	<td align="center">${history.MONTHYEAR}&nbsp;</td>
					  	<td align="center">${history.CREATEDATE}&nbsp;</td>
					  	<c:if test="${history.STATUS == 1}">
					  		<td align="center"><bean:message key="screen.e_exp_s01.inProcess" /></td>
					  	</c:if>
					  	<c:if test="${history.STATUS == 2}">
					  		<td align="center"><bean:message key="screen.e_exp_s01.success" /></td>
					  	</c:if>
					  	<c:if test="${history.STATUS == 3}">
					  		<td align="center"><bean:message key="screen.e_exp_s01.failed" /></td>
					  	</c:if>
					  	<td align="center" class="last">
					  		<!-- Delete #146 Start-->
					  		<!-- Export File -->
					  		<!--<c:if test="${history.STATUS == 2}">
					  			<a href="<%=request.getContextPath()%>/E_EXP/RP_E_EXP_S01DownloadBL.do?filename=${history.FILENAME}&idBatch=${history.ID_BATCH}"><bean:message key="screen.e_exp_s01.uploadFile" /></a>
					  			<bean:message key="screen.e_exp_s01.log" />
					  		</c:if>
					  		<c:if test="${history.STATUS == 3}">-->
					  			<!-- Log -->
					  			<!--<bean:message key="screen.e_exp_s01.uploadFile" />
					  			<a href="javascript:popupMsg('<%=request.getContextPath()%>/E_EXP/RP_E_EXP_S01LogInitBL.do?idBatch=${history.ID_BATCH}');"><bean:message key="screen.e_exp_s01.log" /></a>
					  		</c:if>-->
					  		<!-- Delete #146 End-->
					  		<!-- Add #146 Start-->
					  		<!-- Export File -->
					  		<c:choose>
					  			<c:when test="${history.STATUS == 2 && history.FILENAME != null}">
					  				<a href="<%=request.getContextPath()%>/E_EXP/RP_E_EXP_S01DownloadBL.do?filename=${history.FILENAME}&idBatch=${history.ID_BATCH}"><bean:message key="screen.e_exp_s01.uploadFile" /></a>
					  			</c:when>
					  			<c:otherwise>
					  				<bean:message key="screen.e_exp_s01.uploadFile" />
					  			</c:otherwise>
					  		</c:choose>
					  		<!-- Log -->
					  		<c:choose>
					  			<c:when test="${history.STATUS == 2 || history.STATUS == 3}">
					  				<a href="javascript:popupMsg('<%=request.getContextPath()%>/E_EXP/RP_E_EXP_S01LogInitBL.do?idBatch=${history.ID_BATCH}');"><bean:message key="screen.e_exp_s01.log" /></a>
					  			</c:when>
					  			<c:otherwise>
					  				<bean:message key="screen.e_exp_s01.log" />
					  			</c:otherwise>
					  		</c:choose>
					  		<!-- Add #146 End-->
					  		&nbsp;
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