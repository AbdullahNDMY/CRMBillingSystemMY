<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ page import="nttdm.bsys.common.fw.BillingSystemUserValueObject" %>
<%@page import="nttdm.bsys.common.util.CommonUtils"%> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<title><bean:message key="screen.e_mim_us2.title"/></title>
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
		<link href="${pageContext.request.contextPath}/E_MIM/css/e_mim.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
	  	<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.spinbox.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/common.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/E_MIM/js/e_mim.js"></script>
	</head>
	<body id="e" onload="initMain();">
	<%
		BillingSystemUserValueObject uvo = (BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT");
	 	String useraccess = CommonUtils.getAccessRight(uvo, "E-MIM-US2");
	%>
		<input type="hidden" styleId="userAccess" name="userAccess" value="<%=useraccess%>" />
		<input type="hidden" styleId="subModule" name="subModule" value="E-MIM-US2" />
		<ts:form action="/RP_E_MIM_US2_02BL" enctype="multipart/form-data">
		    <html:hidden name="_e_mimForm" property="retStatusStr" styleId="retStatusStr"/>
			<h1 class="title"><bean:message key="screen.e_mim_us2.title"/></h1>
			<div class="section">
				<h2><bean:message key="screen.e_mim_us2.0001"/></h2>
				<div class="group-content">
					<table cellspacing="3" cellpadding="0" style="width:600px;">
						<tr>
							<td colspan="2">
								<fieldset>
									<legend class="legend-small"><bean:message key="screen.e_mim_us2.0002"/></legend>
									<div class="space">
										<bean:message key="screen.e_mim_us2.0003"/>&nbsp;:&nbsp;
										<%--<html:select property="month" name="_e_mimForm">
											<html:optionsCollection property="listMonth" name="_e_mimForm" label="label" value="value"/>
										</html:select> --%>
										<html:text name="_e_mimForm" property="month" size="5" maxlength="2" styleId="cboMonth" styleClass="resettable">:</html:text>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<bean:message key="screen.e_mim_us2.0004"/>&nbsp;:&nbsp;
										<%--<html:select property="year" name="_e_mimForm">
											<html:optionsCollection property="listYear" name="_e_mimForm" label="label" value="value"/>
										</html:select> --%>
										<html:text name="_e_mimForm" property="year" size="5" maxlength="4" styleId="cboYear" styleClass="resettable">:</html:text>
                                        <%
                                        java.util.Calendar c1 = java.util.Calendar.getInstance();
                                        int currentMonth = c1.get(java.util.Calendar.MONTH) + 1;
                                        int currentYear = c1.get(java.util.Calendar.YEAR);
                                        %>
                                        <input type="hidden" id="currentMonth" value="<%=currentMonth%>" />
                                        <input type="hidden" id="currentYear" value="<%=currentYear%>" />
                                        <input type="hidden" id="info.ERR4SC012" value="<bean:message key="info.ERR4SC012" />" />
									</div>
								</fieldset>
							</td>
						</tr>
						<tr>
							<td>
								<bean:message key="screen.e_mim_us2.0005"/>
							</td>
							<td>
								<html:file property="formFile" size="60" style="height: 26px" styleId="txtBrowse"></html:file>
							</td>
						</tr>
						<tr>
							<td></td>
							<td>
								<input type="submit" id="btnUpload" value='<bean:message key="screen.e_mim_us2.0007"/>' onclick="return confirmYearMonth();"/>
							</td>
						</tr>
					</table>
				</div>
			</div>
<hr>		
			<div class="section">
				<h2><bean:message key="screen.e_mim_us2.0008"/></h2>
				<div class="group-content" style="width: 100%;">
					<div class="group result">
						<h2><bean:message key="screen.e_mim_us2.0009" /> ${_e_mimForm.map.totalRow}</h2>
					</div>
					<div class="pageLink"><bean:message key="screen.e_mim_us2.0010"/>
						<ts:pageLinks 
			    			id="curPageLinks"
							action="/RP_E_MIM_US2_01BL" 
							name="_e_mimForm" 
							rowProperty="row"
							totalProperty="totalRow" 
							indexProperty="startIndex"
							currentPageIndex="now" 
							totalPageCount="total"
						/>
					  <logic:present name="curPageLinks">  
							<bean:write name="curPageLinks" filter="false"/>
	                  </logic:present>
					</div>
					<table class="datatable collapse" cellpadding="0" cellspacing="0" border="0">
					  <tr>
					    <th align="center"><bean:message key="screen.e_mim_us2.0013"/></th>
					    <th align="center"><bean:message key="screen.e_mim_us2.0014"/></th>
					    <th align="center"><bean:message key="screen.e_mim_us2.0015"/></th>
					    <th align="center"><bean:message key="screen.e_mim_us2.0016"/></th>
					    <th align="center"><bean:message key="screen.e_mim_us2.0017"/></th>
					    <th align="center" class="last"><bean:message key="screen.e_mim_us2.0018"/></th>
					  </tr>
					  <c:forEach items="${_e_mimForm.map.listHistories}" var="item" varStatus="status">
					  	<tr>
						  	<td align="center">${item.ID_CLC_IMP_BATCH}</td>
						  	<td>${item.FILENAME}</td>
						  	<td align="center">${item.CLOSE_MONTHYEAR}</td>
						  	<td align="center">${item.DATE_UPLOADED}</td>
						  	<td align="center">
							  	<c:if test="${item.STATUS == 0}">
						  			<bean:message key="screen.e_mim_us2.0021" />
							  	</c:if>
							  	<c:if test="${item.STATUS == 1}">
							  		<bean:message key="screen.e_mim_us2.0022" />
							  	</c:if>
							  	<c:if test="${item.STATUS == 2}">
							  		<bean:message key="screen.e_mim_us2.0023" />
							  	</c:if>
						  	</td>
						  	<td align="center">
						  		<c:if test="${item.STATUS == 0}">
						  			<a href="<%=request.getContextPath()%>/E_MIM/RP_E_MIM_US2_DownloadBL.do?filename=${item.FILENAME}"><bean:message key="screen.e_mim_us2.0019"/></a>
						  			<bean:message key="screen.e_mim_us2.0024" />
						  			<bean:message key="screen.e_mim_us2.0020"/>
						  		</c:if>
						  		<c:if test="${item.STATUS == 1}">
						  			<bean:message key="screen.e_mim_us2.0019"/><bean:message key="screen.e_mim_us2.0024" />
						  			<a href="javascript:void(0);" onclick="popup_e_mim_us2('${pageContext.request.contextPath}', ${item.ID_CLC_IMP_BATCH});">
						  				<bean:message key="screen.e_mim_us2.0020"/>
						  			</a>
						  		</c:if>
						  		<c:if test="${item.STATUS == 2}">
						  		&nbsp;
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
			<ts:messages id="message" message="true"><bean:write name="message"/></ts:messages>
		</div>			
		<div class="error">
			<html:messages id="message">
				<bean:write name="message"/><br/>
			</html:messages>
		</div>
	</body>
</html:html>

