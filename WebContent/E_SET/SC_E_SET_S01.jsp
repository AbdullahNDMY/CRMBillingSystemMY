<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ page import="nttdm.bsys.common.fw.BillingSystemUserValueObject" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<title><bean:message key="screen.e_set_s01.title" name="_e_mexForm" /></title>
	  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/common.css"/>
	  <link href="css/e_set.css" rel="stylesheet" type="text/css" />
	  <script type="text/javascript">
	  	CONTEXT_PATH = "${pageContext.request.contextPath}";

        function initPopupInfo() {
            var popupInfo = document.getElementById("SAPopupInfo").value;
            if(popupInfo!=""&&popupInfo!=null) {
                var msg = new messageBox("");
                var isConfirm = (msg.POPCFMStatement(popupInfo) == msg.YES_CONFIRM);
                if (isConfirm) {
                    document.getElementById("SApopupClickYesFlg").value = "1";
                    document.getElementById("forward_saRun").click();
                }
            }
        }
	  </script> 
	  <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/messageBox.js"></script>
   		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/common.js"></script>
   		<script type="text/javascript" src="js/e_set.js"></script>
	</head>
	
	<body id="e" onload="initMain();initPopupInfo();">
		<!-- check access right START -->
		<%
			String accessRight = ((nttdm.bsys.common.fw.BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT")).getUserAccessInfo("E", "E-SET").getAccess_type();
			pageContext.setAttribute("accessRightBean", accessRight);
		%>
<%-- 		<logic:notEqual value="2" name="accessRightBean"> --%>
<!-- 		<script type="text/javascript"> -->
<%-- 			window.location = '${pageContext.request.contextPath}/C_CMN001/C_CMN001S06SCR.do'; --%>
<!-- 		</script> -->
<%-- 		</logic:notEqual> --%>
		<!-- check access right END -->
		<input type="hidden" styleId="userAccess" name="userAccess" value="<%=accessRight%>" />
		<ts:form action="/RP_E_SET_S01SubmitBL">
			<t:defineCodeList id="NO_OF_MONTH" />
			<t:defineCodeList id="DAY_OF_MONTH" />
			<t:defineCodeList id="TIME_HOUR" />
			<t:defineCodeList id="TIME_MINUTE" />
			<html:hidden property="SAPopupInfo" name="_e_setForm"/>
			<html:hidden property="SApopupClickYesFlg" name="_e_setForm"/>
			<input type="hidden" id="path" name="path" value="<%=request.getContextPath()%>" />
			<h1 class="title"><bean:message key="screen.e_set_s01.title"/></h1>
			<div class="section">
			<%if(accessRight.equals("2")) {%>			
				<!-- Credit Card Alert Generation (on Expiry Date) -->
				<div class="batchE">
					<table cellpadding="0" cellspacing="0">
						<tr>
							<th colspan="3" align="left"><bean:message key="screen.e_set.00002"/></th>
						</tr>
						<tr>
							<td style="width: 60%"><bean:message key="screen.e_set.00003"/></td>
							<td>:</td>
							<td>
								<html:radio property="CCAlertMode" value="1"></html:radio> <bean:message key="screen.e_set.00004"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio property="CCAlertMode" value="0"></html:radio> <bean:message key="screen.e_set.00005"/>
							</td>
						</tr>
						<tr>
							<td><bean:message key="screen.e_set.00006"/></td>
							<td>:</td>
							<td>${_e_setForm.map.CCMode}</td>
						</tr>
						<tr>
							<td><bean:message key="screen.e_set.00008"/></td>
							<td>:</td>
							<td>
								<c:if test="${_e_setForm.map.CCAlertMode==1}">
									<html:select property="CCMonths">
										<html:option value="0">00</html:option>
										<html:options collection="NO_OF_MONTH" property="id" labelProperty="name"/>
									</html:select>
								</c:if>
								<c:if test="${_e_setForm.map.CCAlertMode!=1}">
									<html:select property="CCMonths" disabled="true">
										<html:option value=""><bean:message key="screen.e_set.00019" /></html:option>
										<html:options collection="NO_OF_MONTH" property="id" labelProperty="name"/>
									</html:select>
								</c:if>
							</td>
						</tr>
						<tr>
							<td><bean:message key="screen.e_set.00009"/></td>
							<td>:</td>
							<td>
								<c:if test="${_e_setForm.map.CCAlertMode==1}">
									<html:select property="CCDay">
										<html:option value=""><bean:message key="screen.e_set.00020" /></html:option>
										<html:options collection="DAY_OF_MONTH" property="id" labelProperty="name"/>
									</html:select>
								</c:if>
								<c:if test="${_e_setForm.map.CCAlertMode!=1}">
									<html:select property="CCDay" disabled="true">
										<html:option value=""><bean:message key="screen.e_set.00020" /></html:option>
										<html:options collection="DAY_OF_MONTH" property="id" labelProperty="name"/>
									</html:select>
								</c:if>
							</td>
						</tr>
						<tr>
							<td><bean:message key="screen.e_set.00010"/></td>
							<td>:</td>
							<td>
								<c:if test="${_e_setForm.map.CCAlertMode==1}">
									<html:select property="CCHour">
										<html:option value=""><bean:message key="screen.e_set.00021" /></html:option>
										<html:options collection="TIME_HOUR" property="id" labelProperty="name"/>
									</html:select>
									<html:select property="CCMinute">
										<html:option value=""><bean:message key="screen.e_set.00022" /></html:option>
										<html:options collection="TIME_MINUTE" property="id" labelProperty="name"/>
									</html:select>
								</c:if>
								<c:if test="${_e_setForm.map.CCAlertMode!=1}">
									<html:select property="CCHour" disabled="true">
										<html:option value=""><bean:message key="screen.e_set.00021" /></html:option>
										<html:options collection="TIME_HOUR" property="id" labelProperty="name"/>
									</html:select>
									<html:select property="CCMinute" disabled="true">
										<html:option value=""><bean:message key="screen.e_set.00022" /></html:option>
										<html:options collection="TIME_MINUTE" property="id" labelProperty="name"/>
									</html:select>
								</c:if>
							</td>
						</tr>
						<tr>
							<td valign="top">
								<bean:message key="screen.e_set.00011"/>
								<p style="margin-top:10px; margin-left:180px;">
									<a href="<%=request.getContextPath()%>/E_SET/SC_E_SET_S02BL.do?SCR_ID=ESET&FUNC_ID=CC&TITLE_HDR=<bean:message key="screen.e_set.00002"/>" style="color:#0000FF;"><bean:message key="screen.e_set.00027" /></a>
								</p>
							</td>
							<td valign="top">:</td>
							<td rowspan="2">														
								<c:if test="${_e_setForm.map.CCAlertMode==1}">
									<logic:iterate name="_e_setForm" property="CCUsers" id="ccUser">
										<p><html:select name="_e_setForm" property="CCUsers" value="${ccUser}">
											<html:option value=""><bean:message key="screen.e_set.00023" /></html:option>
											<html:optionsCollection name="_e_setForm" property="userList" value="value" label="label"/>
										</html:select>
										</p>
									</logic:iterate>
								</c:if>
								<c:if test="${_e_setForm.map.CCAlertMode!=1}">
									<logic:iterate name="_e_setForm" property="CCUsers" id="ccUser">
										<p><html:select name="_e_setForm" property="CCUsers" value="${ccUser}" disabled="true">
											<html:option value=""><bean:message key="screen.e_set.00023" /></html:option>
											<html:optionsCollection name="_e_setForm" property="userList" value="value" label="label"/>
										</html:select>
										</p>
									</logic:iterate>
								</c:if>
							</td>
						</tr>
						<tr>
							<td valign="bottom">
								<input type="submit" name="forward_ccRun" value="<bean:message key="screen.e_set.00016" />"
									class="button" <c:if test="${_e_setForm.map.CCAlertMode!=1 || '-1' eq _e_setForm.map.CCRetStatus}">disabled="disabled"</c:if>/>								
							</td>
							<td>&nbsp;</td>
						</tr>
					</table>
				</div>
				
				<!-- Service Date Alert Generation (on Due Date) -->			
				<div class="batchE2">
					<table cellpadding="0" cellspacing="0">
						<tr>
							<th colspan="3" align="left"><bean:message key="screen.e_set.00012"/></th>
						</tr>
						<tr>
							<td style="width: 60%"><bean:message key="screen.e_set.00003"/></td>
							<td>:</td>
							<td>
								<html:radio property="SDAlertMode" value="1"></html:radio> <bean:message key="screen.e_set.00004"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio property="SDAlertMode" value="0"></html:radio> <bean:message key="screen.e_set.00005"/>
							</td>
						</tr>
						<tr>
							<td><bean:message key="screen.e_set.00006"/></td>
							<td>:</td>
							<td>
								<html:radio property="SDMode" value="Daily"></html:radio> <bean:message key="screen.e_set.00025"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio property="SDMode" value="Monthly"></html:radio> <bean:message key="screen.e_set.00007"/>
							</td>
						</tr>
						<tr>
							<td><bean:message key="screen.e_set.00008"/></td>
							<td>:</td>
							<td>
								<c:if test="${_e_setForm.map.SDAlertMode==1}">
									<html:select property="SDMonths">
										<html:option value=""><bean:message key="screen.e_set.00019" /></html:option>
										<html:options collection="NO_OF_MONTH" property="id" labelProperty="name"/>
									</html:select>
								</c:if>
								<c:if test="${_e_setForm.map.SDAlertMode!=1}">
									<html:select property="SDMonths" disabled="true">
										<html:option value=""><bean:message key="screen.e_set.00019" /></html:option>
										<html:options collection="NO_OF_MONTH" property="id" labelProperty="name"/>
									</html:select>
								</c:if>
							</td>
						</tr>
						<tr>
							<td><bean:message key="screen.e_set.00009"/></td>
							<td>:</td>
							<td>
								<c:if test="${_e_setForm.map.SDAlertMode==1}">
									<html:select property="SDDay">
										<html:option value=""><bean:message key="screen.e_set.00020" /></html:option>
										<html:options collection="DAY_OF_MONTH" property="id" labelProperty="name"/>
									</html:select>
								</c:if>
								<c:if test="${_e_setForm.map.SDAlertMode!=1}">
									<html:select property="SDDay" disabled="true">
										<html:option value=""><bean:message key="screen.e_set.00020" /></html:option>
										<html:options collection="DAY_OF_MONTH" property="id" labelProperty="name"/>
									</html:select>
								</c:if>
							</td>
						</tr>
						<tr>
							<td><bean:message key="screen.e_set.00010"/></td>
							<td>:</td>
							<td>
								<c:if test="${_e_setForm.map.SDAlertMode==1}">
									<html:select property="SDHour">
										<html:option value=""><bean:message key="screen.e_set.00021" /></html:option>
										<html:options collection="TIME_HOUR" property="id" labelProperty="name"/>
									</html:select>
									<html:select property="SDMinute">
										<html:option value=""><bean:message key="screen.e_set.00022" /></html:option>
										<html:options collection="TIME_MINUTE" property="id" labelProperty="name"/>
									</html:select>
								</c:if>
								<c:if test="${_e_setForm.map.SDAlertMode!=1}">
									<html:select property="SDHour" disabled="true">
										<html:option value=""><bean:message key="screen.e_set.00021" /></html:option>
										<html:options collection="TIME_HOUR" property="id" labelProperty="name"/>
									</html:select>
									<html:select property="SDMinute" disabled="true">
										<html:option value=""><bean:message key="screen.e_set.00022" /></html:option>
										<html:options collection="TIME_MINUTE" property="id" labelProperty="name"/>
									</html:select>
								</c:if>
							</td>
						</tr>
						<tr>
							<td valign="top">
								<bean:message key="screen.e_set.00011"/>
								<p style="margin-top:10px;margin-left:180px;">
									<a href="<%=request.getContextPath()%>/E_SET/SC_E_SET_S02BL.do?SCR_ID=ESET&FUNC_ID=SD&TITLE_HDR=<bean:message key="screen.e_set.00012"/>" style="color:#0000FF;"><bean:message key="screen.e_set.00027" /></a>
								</p>
							</td>
							<td valign="top">:</td>
							<td rowspan="2">
								<c:if test="${_e_setForm.map.SDAlertMode==1}">
									<logic:iterate name="_e_setForm" property="SDUsers" id="sdUser">
										<p><html:select name="_e_setForm" property="SDUsers" value="${sdUser}">
											<html:option value=""><bean:message key="screen.e_set.00023" /></html:option>
											<html:optionsCollection name="_e_setForm" property="userList" value="value" label="label"/>
										</html:select>
										</p>
									</logic:iterate>
								</c:if>
								<c:if test="${_e_setForm.map.SDAlertMode!=1}">
									<logic:iterate name="_e_setForm" property="SDUsers" id="sdUser">
										<p><html:select name="_e_setForm" property="SDUsers" value="${sdUser}" disabled="true">
											<html:option value=""><bean:message key="screen.e_set.00023" /></html:option>
											<html:optionsCollection name="_e_setForm" property="userList" value="value" label="label"/>
										</html:select>
										</p>
									</logic:iterate>
								</c:if>
							</td>
						</tr>
						<tr>
							<td valign="bottom">								
								<input type="submit" name="forward_sdRun" value="<bean:message key="screen.e_set.00016" />"
									class="button" <c:if test="${_e_setForm.map.SDAlertMode!=1 || '-1' eq _e_setForm.map.SDRetStatus}">disabled="disabled"</c:if>/>
							</td>
							<td>&nbsp;</td>
						</tr>
					</table>
				</div>
				<div style="clear:both;"></div>
				
				<!-- Generate Billing -->
				<div class="batchE">
					<table cellpadding="0" cellspacing="0">
						<tr>
							<th colspan="3" align="left"><bean:message key="screen.e_set.00013"/></th>
						</tr>
						<tr>
							<td style="width: 60%"><bean:message key="screen.e_set.00006"/></td>
							<td>:</td>
							<td>${_e_setForm.map.GBMode}</td>
						</tr>
						<tr>
							<td><bean:message key="screen.e_set.00009"/></td>
							<td>:</td>
							<td>
								<html:select property="GBDay">
									<html:option value=""><bean:message key="screen.e_set.00020" /></html:option>
									<html:options collection="DAY_OF_MONTH" property="id" labelProperty="name"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<td><bean:message key="screen.e_set.00010"/></td>
							<td>:</td>
							<td>
								<html:select property="GBHour">
									<html:option value=""><bean:message key="screen.e_set.00021" /></html:option>
									<html:options collection="TIME_HOUR" property="id" labelProperty="name"/>
								</html:select>
								<html:select property="GBMinute">
									<html:option value=""><bean:message key="screen.e_set.00022" /></html:option>
									<html:options collection="TIME_MINUTE" property="id" labelProperty="name"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<td valign="top">
								<bean:message key="screen.e_set.00011"/>
								<p style="margin-top:10px;margin-left:180px;">
									<a href="<%=request.getContextPath()%>/E_SET/SC_E_SET_S02BL.do?SCR_ID=ESET&FUNC_ID=GB&TITLE_HDR=<bean:message key="screen.e_set.00013"/>" style="color:#0000FF;"><bean:message key="screen.e_set.00027" /></a>
								</p>
							</td>
							<td valign="top">:</td>
							<td rowspan="2">
								<logic:iterate name="_e_setForm" property="GBUsers" id="gbUser">
									<p><html:select name="_e_setForm" property="GBUsers" value="${gbUser}">
										<html:option value=""><bean:message key="screen.e_set.00023" /></html:option>
										<html:optionsCollection name="_e_setForm" property="userList" value="value" label="label"/>
									</html:select>
									</p>
								</logic:iterate>
							</td>
						</tr>
						<!-- #192 Start -->
						<tr>
							<td>Billing Option :
							<html:select name="_e_setForm" property="billingOption">
								<html:option value="0">All</html:option>
								<html:option value="1">Prepaid</html:option>
								<html:option value="2">Postpaid</html:option>
							</html:select>
							</td>
							<td>&nbsp;</td>	
						</tr>
						<!-- #192 End -->
						<tr>
							<td valign="bottom">
								<input type="submit" class="button" name="forward_gbRun" style="float: left" <c:if test="${'-1' eq _e_setForm.map.GBRetStatus}">disabled="disabled"</c:if>
									value="<bean:message key="screen.e_set.00016"/>" onclick="return checkRunDate('GBRunDateEntry', 'gbrun');"/>
								<label style="float: left;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
								<div <logic:notEqual name="_e_setForm" property="esetRunDate" value="1">disabled="disabled" style="display:none"</logic:notEqual> >
									<input type="text" name="GBRunDateEntry" id="GBRunDateEntryID" 
										maxlength="10" readonly="readonly" size="15"/>												
									<t:inputCalendar for="GBRunDateEntry" format="dd/MM/yyyy"/>
								</div>
							</td>
							<td>&nbsp;</td>
						</tr>
					</table>
				</div>
				
				<!-- Statement of Accounts -->
				<div class="batchE2">
					<table cellpadding="0" cellspacing="0">
						<tr>
							<th colspan="3" align="left"><bean:message key="screen.e_set.00014"/></th>
						</tr>
						<tr>
							<td style="width: 60%"><bean:message key="screen.e_set.00006"/></td>
							<td>:</td>
							<td>${_e_setForm.map.SAMode}</td>
						</tr>
						<tr>
							<td style="width: 60%"><bean:message key="screen.e_set.00048"/></td>
							<td>:</td>
							<td>
								<html:radio property="SAStatementMonth" value="0"></html:radio> <bean:message key="screen.e_set.00049"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio property="SAStatementMonth" value="1"></html:radio> <bean:message key="screen.e_set.00050"/>
							</td>
						</tr>
						<tr>
							<td><bean:message key="screen.e_set.00009"/></td>
							<td>:</td>
							<td>
								<html:select property="SADay">
									<html:option value=""><bean:message key="screen.e_set.00020" /></html:option>
									<html:options collection="DAY_OF_MONTH" property="id" labelProperty="name"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<td><bean:message key="screen.e_set.00010"/></td>
							<td>:</td>
							<td>
								<html:select property="SAHour">
									<html:option value=""><bean:message key="screen.e_set.00021" /></html:option>
									<html:options collection="TIME_HOUR" property="id" labelProperty="name"/>
								</html:select>
								<html:select property="SAMinute">
									<html:option value=""><bean:message key="screen.e_set.00022" /></html:option>
									<html:options collection="TIME_MINUTE" property="id" labelProperty="name"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<td valign="top">
								<bean:message key="screen.e_set.00011"/>
								<p style="margin-top:10px;margin-left:180px;">
									<a href="<%=request.getContextPath()%>/E_SET/SC_E_SET_S02BL.do?SCR_ID=ESET&FUNC_ID=SA&TITLE_HDR=<bean:message key="screen.e_set.00014"/>" style="color:#0000FF;"><bean:message key="screen.e_set.00027" /></a>
								</p>
							</td>
							<td valign="top">:</td>
							<td rowspan="2">
								<logic:iterate name="_e_setForm" property="SAUsers" id="saUser">
									<p><html:select name="_e_setForm" property="SAUsers" value="${saUser}">
										<html:option value=""><bean:message key="screen.e_set.00023" /></html:option>
										<html:optionsCollection name="_e_setForm" property="userList" value="value" label="label"/>
									</html:select>
									</p>
								</logic:iterate>
							</td>
						</tr>
						<tr>
							<td valign="bottom">
								<input type="submit" class="button" name="forward_saRun" style="float: left;" <c:if test="${'-1' eq _e_setForm.map.SARetStatus}">disabled="disabled"</c:if>
									value="<bean:message key="screen.e_set.00016"/>" onclick="return checkRunDate('SARunDateEntry', 'sarun');"/>
								<label style="float: left;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
								<div <logic:notEqual name="_e_setForm" property="esetRunDate" value="1">disabled="disabled" style="display:none"</logic:notEqual> >
									<html:text property="SARunDateEntry" name="_e_setForm" 
										maxlength="10" readonly="readonly" size="15"/>										
									<t:inputCalendar for="SARunDateEntry" format="dd/MM/yyyy"/>
								</div>
							</td>
							<td>&nbsp;</td>
						</tr>
					</table>
				</div>
				<div style="clear:both;"></div>	
			<%} else { %>
				<!-- Credit Card Alert Generation (on Expiry Date) -->
				<div class="batchE">
					<table cellpadding="0" cellspacing="0">
						<tr>
							<th colspan="3" align="left"><bean:message key="screen.e_set.00002"/></th>
						</tr>
						<tr>
							<td style="width: 60%"><bean:message key="screen.e_set.00003"/></td>
							<td>:</td>
							<td>
								<html:radio property="CCAlertMode" value="0" disabled="true"></html:radio> <bean:message key="screen.e_set.00004"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio property="CCAlertMode" value="1" disabled="true"></html:radio> <bean:message key="screen.e_set.00005"/>
							</td>
						</tr>
						<tr>
							<td><bean:message key="screen.e_set.00006"/></td>
							<td>:</td>
							<td>${_e_setForm.map.CCMode}</td>
						</tr>
						<tr>
							<td><bean:message key="screen.e_set.00008"/></td>
							<td>:</td>
							<td>
								<c:if test="${_e_setForm.map.CCAlertMode==1}">
									<html:select property="CCMonths" disabled="true">
										<html:option value=""><bean:message key="screen.e_set.00019" /></html:option>
										<html:options collection="NO_OF_MONTH" property="id" labelProperty="name"/>
									</html:select>
								</c:if>
								<c:if test="${_e_setForm.map.CCAlertMode!=1}">
									<html:select property="CCMonths" disabled="true">
										<html:option value=""><bean:message key="screen.e_set.00019" /></html:option>
										<html:options collection="NO_OF_MONTH" property="id" labelProperty="name"/>
									</html:select>
								</c:if>
							</td>
						</tr>
						<tr>
							<td><bean:message key="screen.e_set.00009"/></td>
							<td>:</td>
							<td>
								<c:if test="${_e_setForm.map.CCAlertMode==1}">
									<html:select property="CCDay" disabled="true">
										<html:option value=""><bean:message key="screen.e_set.00020" /></html:option>
										<html:options collection="DAY_OF_MONTH" property="id" labelProperty="name"/>
									</html:select>
								</c:if>
								<c:if test="${_e_setForm.map.CCAlertMode!=1}">
									<html:select property="CCDay" disabled="true">
										<html:option value=""><bean:message key="screen.e_set.00020" /></html:option>
										<html:options collection="DAY_OF_MONTH" property="id" labelProperty="name"/>
									</html:select>
								</c:if>
							</td>
						</tr>
						<tr>
							<td><bean:message key="screen.e_set.00010"/></td>
							<td>:</td>
							<td>
								<c:if test="${_e_setForm.map.CCAlertMode==1}">
									<html:select property="CCHour" disabled="true">
										<html:option value=""><bean:message key="screen.e_set.00021" /></html:option>
										<html:options collection="TIME_HOUR" property="id" labelProperty="name"/>
									</html:select>
									<html:select property="CCMinute" disabled="true">
										<html:option value=""><bean:message key="screen.e_set.00022" /></html:option>
										<html:options collection="TIME_MINUTE" property="id" labelProperty="name"/>
									</html:select>
								</c:if>
								<c:if test="${_e_setForm.map.CCAlertMode!=1}">
									<html:select property="CCHour" disabled="true">
										<html:option value=""><bean:message key="screen.e_set.00021" /></html:option>
										<html:options collection="TIME_HOUR" property="id" labelProperty="name"/>
									</html:select>
									<html:select property="CCMinute" disabled="true">
										<html:option value=""><bean:message key="screen.e_set.00022" /></html:option>
										<html:options collection="TIME_MINUTE" property="id" labelProperty="name"/>
									</html:select>
								</c:if>
							</td>
						</tr>
						<tr>
							<td valign="top">
								<bean:message key="screen.e_set.00011"/>
								<p style="margin-top:10px; margin-left:180px;">
									<a href="<%=request.getContextPath()%>/E_SET/SC_E_SET_S02BL.do?SCR_ID=ESET&FUNC_ID=CC&TITLE_HDR=<bean:message key="screen.e_set.00002"/>" style="color:#0000FF;"><bean:message key="screen.e_set.00027" /></a>
								</p>
							</td>
							<td valign="top">:</td>
							<td rowspan="2">														
								<c:if test="${_e_setForm.map.CCAlertMode==1}">
									<logic:iterate name="_e_setForm" property="CCUsers" id="ccUser">
										<p><html:select name="_e_setForm" property="CCUsers" value="${ccUser}" disabled="true">
											<html:option value=""><bean:message key="screen.e_set.00023" /></html:option>
											<html:optionsCollection name="_e_setForm" property="userList" value="value" label="label"/>
										</html:select>
										</p>
									</logic:iterate>
								</c:if>
								<c:if test="${_e_setForm.map.CCAlertMode!=1}">
									<logic:iterate name="_e_setForm" property="CCUsers" id="ccUser">
										<p><html:select name="_e_setForm" property="CCUsers" value="${ccUser}" disabled="true">
											<html:option value=""><bean:message key="screen.e_set.00023" /></html:option>
											<html:optionsCollection name="_e_setForm" property="userList" value="value" label="label"/>
										</html:select>
										</p>
									</logic:iterate>
								</c:if>
							</td>
						</tr>
						<tr>
							<td valign="bottom">
								<input type="submit" name="forward_ccRun" disabled="true" value="<bean:message key="screen.e_set.00016" />"
									class="button" <c:if test="${_e_setForm.map.CCAlertMode!=1 || '-1' eq _e_setForm.map.CCRetStatus}">disabled="disabled"</c:if>/>								
							</td>
							<td>&nbsp;</td>
						</tr>
					</table>
				</div>
				
				<!-- Service Date Alert Generation (on Due Date) -->			
				<div class="batchE2">
					<table cellpadding="0" cellspacing="0">
						<tr>
							<th colspan="3" align="left"><bean:message key="screen.e_set.00012"/></th>
						</tr>
						<tr>
							<td style="width: 60%"><bean:message key="screen.e_set.00003"/></td>
							<td>:</td>
							<td>
								<html:radio property="SDAlertMode" value="0" disabled="true"></html:radio> <bean:message key="screen.e_set.00004"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio property="SDAlertMode" value="1" disabled="true"></html:radio> <bean:message key="screen.e_set.00005"/>
							</td>
						</tr>
						<tr>
							<td><bean:message key="screen.e_set.00006"/></td>
							<td>:</td>
							<td>
								<html:radio property="SDMode" value="Daily" disabled="true"></html:radio> <bean:message key="screen.e_set.00025"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio property="SDMode" value="Monthly" disabled="true"></html:radio> <bean:message key="screen.e_set.00007"/>
							</td>
						</tr>
						<tr>
							<td><bean:message key="screen.e_set.00008"/></td>
							<td>:</td>
							<td>
								<c:if test="${_e_setForm.map.SDAlertMode==1}">
									<html:select property="SDMonths" disabled="true">
										<html:option value=""><bean:message key="screen.e_set.00019" /></html:option>
										<html:options collection="NO_OF_MONTH" property="id" labelProperty="name"/>
									</html:select>
								</c:if>
								<c:if test="${_e_setForm.map.SDAlertMode!=1}">
									<html:select property="SDMonths" disabled="true">
										<html:option value=""><bean:message key="screen.e_set.00019" /></html:option>
										<html:options collection="NO_OF_MONTH" property="id" labelProperty="name"/>
									</html:select>
								</c:if>
							</td>
						</tr>
						<tr>
							<td><bean:message key="screen.e_set.00009"/></td>
							<td>:</td>
							<td>
								<c:if test="${_e_setForm.map.SDAlertMode==1}">
									<html:select property="SDDay" disabled="true">
										<html:option value=""><bean:message key="screen.e_set.00020" /></html:option>
										<html:options collection="DAY_OF_MONTH" property="id" labelProperty="name"/>
									</html:select>
								</c:if>
								<c:if test="${_e_setForm.map.SDAlertMode!=1}">
									<html:select property="SDDay" disabled="true">
										<html:option value=""><bean:message key="screen.e_set.00020" /></html:option>
										<html:options collection="DAY_OF_MONTH" property="id" labelProperty="name"/>
									</html:select>
								</c:if>
							</td>
						</tr>
						<tr>
							<td><bean:message key="screen.e_set.00010"/></td>
							<td>:</td>
							<td>
								<c:if test="${_e_setForm.map.SDAlertMode==1}">
									<html:select property="SDHour" disabled="true">
										<html:option value=""><bean:message key="screen.e_set.00021" /></html:option>
										<html:options collection="TIME_HOUR" property="id" labelProperty="name"/>
									</html:select>
									<html:select property="SDMinute" disabled="true">
										<html:option value=""><bean:message key="screen.e_set.00022" /></html:option>
										<html:options collection="TIME_MINUTE" property="id" labelProperty="name"/>
									</html:select>
								</c:if>
								<c:if test="${_e_setForm.map.SDAlertMode!=1}">
									<html:select property="SDHour" disabled="true">
										<html:option value=""><bean:message key="screen.e_set.00021" /></html:option>
										<html:options collection="TIME_HOUR" property="id" labelProperty="name"/>
									</html:select>
									<html:select property="SDMinute" disabled="true">
										<html:option value=""><bean:message key="screen.e_set.00022" /></html:option>
										<html:options collection="TIME_MINUTE" property="id" labelProperty="name"/>
									</html:select>
								</c:if>
							</td>
						</tr>
						<tr>
							<td valign="top">
								<bean:message key="screen.e_set.00011"/>
								<p style="margin-top:10px;margin-left:180px;">
									<a href="<%=request.getContextPath()%>/E_SET/SC_E_SET_S02BL.do?SCR_ID=ESET&FUNC_ID=SD&TITLE_HDR=<bean:message key="screen.e_set.00012"/>" style="color:#0000FF;"><bean:message key="screen.e_set.00027" /></a>
								</p>
							</td>
							<td valign="top">:</td>
							<td rowspan="2">
								<c:if test="${_e_setForm.map.SDAlertMode==1}">
									<logic:iterate name="_e_setForm" property="SDUsers" id="sdUser">
										<p><html:select name="_e_setForm" property="SDUsers" value="${sdUser}" disabled="true">
											<html:option value=""><bean:message key="screen.e_set.00023" /></html:option>
											<html:optionsCollection name="_e_setForm" property="userList" value="value" label="label"/>
										</html:select>
										</p>
									</logic:iterate>
								</c:if>
								<c:if test="${_e_setForm.map.SDAlertMode!=1}">
									<logic:iterate name="_e_setForm" property="SDUsers" id="sdUser">
										<p><html:select name="_e_setForm" property="SDUsers" value="${sdUser}" disabled="true">
											<html:option value=""><bean:message key="screen.e_set.00023" /></html:option>
											<html:optionsCollection name="_e_setForm" property="userList" value="value" label="label"/>
										</html:select>
										</p>
									</logic:iterate>
								</c:if>
							</td>
						</tr>
						<tr>
							<td valign="bottom">								
								<input type="submit" name="forward_sdRun" disabled="true" value="<bean:message key="screen.e_set.00016" />"
									class="button" <c:if test="${_e_setForm.map.SDAlertMode!=1 || '-1' eq _e_setForm.map.SDRetStatus}">disabled="disabled"</c:if>/>
							</td>
							<td>&nbsp;</td>
						</tr>
					</table>
				</div>
				<div style="clear:both;"></div>
				
				<!-- Generate Billing -->
				<div class="batchE">
					<table cellpadding="0" cellspacing="0">
						<tr>
							<th colspan="3" align="left"><bean:message key="screen.e_set.00013"/></th>
						</tr>
						<tr>
							<td style="width: 60%"><bean:message key="screen.e_set.00006"/></td>
							<td>:</td>
							<td>${_e_setForm.map.GBMode}</td>
						</tr>
						<tr>
							<td><bean:message key="screen.e_set.00009"/></td>
							<td>:</td>
							<td>
								<html:select property="GBDay" disabled="true">
									<html:option value=""><bean:message key="screen.e_set.00020" /></html:option>
									<html:options collection="DAY_OF_MONTH" property="id" labelProperty="name"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<td><bean:message key="screen.e_set.00010"/></td>
							<td>:</td>
							<td>
								<html:select property="GBHour" disabled="true">
									<html:option value=""><bean:message key="screen.e_set.00021" /></html:option>
									<html:options collection="TIME_HOUR" property="id" labelProperty="name"/>
								</html:select>
								<html:select property="GBMinute" disabled="true">
									<html:option value=""><bean:message key="screen.e_set.00022" /></html:option>
									<html:options collection="TIME_MINUTE" property="id" labelProperty="name"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<td valign="top">
								<bean:message key="screen.e_set.00011"/>
								<p style="margin-top:10px;margin-left:180px;">
									<a href="<%=request.getContextPath()%>/E_SET/SC_E_SET_S02BL.do?SCR_ID=ESET&FUNC_ID=GB&TITLE_HDR=<bean:message key="screen.e_set.00013"/>" style="color:#0000FF;"><bean:message key="screen.e_set.00027" /></a>
								</p>
							</td>
							<td valign="top">:</td>
							<td rowspan="2">
								<logic:iterate name="_e_setForm" property="GBUsers" id="gbUser">
									<p><html:select name="_e_setForm" property="GBUsers" value="${gbUser}" disabled="true">
										<html:option value=""><bean:message key="screen.e_set.00023" /></html:option>
										<html:optionsCollection name="_e_setForm" property="userList" value="value" label="label"/>
									</html:select>
									</p>
								</logic:iterate>
							</td>
						</tr>
						<tr>
							<td valign="bottom">
								<input type="submit" class="button" name="forward_gbRun" disabled="true" style="float: left" <c:if test="${'-1' eq _e_setForm.map.GBRetStatus}">disabled="disabled"</c:if>
									value="<bean:message key="screen.e_set.00016"/>" onclick="return checkRunDate('GBRunDateEntry', 'gbrun');"/>
								<label style="float: left;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
								<div <logic:notEqual name="_e_setForm" property="esetRunDate" value="1">disabled="enabled" style="display:none"</logic:notEqual> >
									<input type="text" name="GBRunDateEntry" id="GBRunDateEntryID" 
										maxlength="10" readonly="readonly" size="15"/>												
									<t:inputCalendar for="GBRunDateEntry" format="dd/MM/yyyy"/>
								</div>
							</td>
							<td>&nbsp;</td>
						</tr>
					</table>
				</div>
				
				<!-- Statement of Accounts -->
				<div class="batchE2">
					<table cellpadding="0" cellspacing="0">
						<tr>
							<th colspan="3" align="left"><bean:message key="screen.e_set.00014"/></th>
						</tr>
						<tr>
							<td style="width: 60%"><bean:message key="screen.e_set.00006"/></td>
							<td>:</td>
							<td>${_e_setForm.map.SAMode}</td>
						</tr>
						<tr>
							<td style="width: 60%"><bean:message key="screen.e_set.00048"/></td>
							<td>:</td>
							<td>
								<html:radio property="SAStatementMonth" value="0" disabled="true"></html:radio> <bean:message key="screen.e_set.00049"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio property="SAStatementMonth" value="1" disabled="true"></html:radio> <bean:message key="screen.e_set.00050"/>
							</td>
						</tr>
						<tr>
							<td><bean:message key="screen.e_set.00009"/></td>
							<td>:</td>
							<td>
								<html:select property="SADay" disabled="true">
									<html:option value=""><bean:message key="screen.e_set.00020" /></html:option>
									<html:options collection="DAY_OF_MONTH" property="id" labelProperty="name"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<td><bean:message key="screen.e_set.00010"/></td>
							<td>:</td>
							<td>
								<html:select property="SAHour" disabled="true">
									<html:option value=""><bean:message key="screen.e_set.00021" /></html:option>
									<html:options collection="TIME_HOUR" property="id" labelProperty="name"/>
								</html:select>
								<html:select property="SAMinute" disabled="true">
									<html:option value=""><bean:message key="screen.e_set.00022" /></html:option>
									<html:options collection="TIME_MINUTE" property="id" labelProperty="name"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<td valign="top">
								<bean:message key="screen.e_set.00011"/>
								<p style="margin-top:10px;margin-left:180px;">
									<a href="<%=request.getContextPath()%>/E_SET/SC_E_SET_S02BL.do?SCR_ID=ESET&FUNC_ID=SA&TITLE_HDR=<bean:message key="screen.e_set.00014"/>" style="color:#0000FF;"><bean:message key="screen.e_set.00027" /></a>
								</p>
							</td>
							<td valign="top">:</td>
							<td rowspan="2">
								<logic:iterate name="_e_setForm" property="SAUsers" id="saUser">
									<p><html:select name="_e_setForm" property="SAUsers" value="${saUser}" disabled="true">
										<html:option value=""><bean:message key="screen.e_set.00023" /></html:option>
										<html:optionsCollection name="_e_setForm" property="userList" value="value" label="label"/>
									</html:select>
									</p>
								</logic:iterate>
							</td>
						</tr>
						<tr>
							<td valign="bottom">
								<input type="submit" class="button" name="forward_saRun" disabled="true" style="float: left;" <c:if test="${'-1' eq _e_setForm.map.SARetStatus}">disabled="disabled"</c:if>
									value="<bean:message key="screen.e_set.00016"/>" onclick="return checkRunDate('SARunDateEntry', 'sarun');"/>
								<label style="float: left;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
								<div <logic:notEqual name="_e_setForm" property="esetRunDate" value="1">disabled="enabled" style="display:none"</logic:notEqual> >
									<html:text property="SARunDateEntry" name="_e_setForm"
										maxlength="10" readonly="readonly" size="15"/>
									<t:inputCalendar for="SARunDateEntry" format="dd/MM/yyyy"/>
									
								</div>
							</td>
							<td>&nbsp;</td>
						</tr>
					</table>
				</div>
				<div style="clear:both;"></div>
				<%} %>
				
				<!-- Sales Summary/ Detail & Outstanding Report -->
				<%-- 
				<div class="batchE">
					<table cellpadding="0" cellspacing="0">
						<tr>
							<th colspan="3" align="left"><bean:message key="screen.e_set.00015"/></th>
						</tr>
						<tr>
							<td style="width: 60%"><bean:message key="screen.e_set.00006"/></td>
							<td>:</td>
							<td>${_e_setForm.map.SSMode}</td>
						</tr>
						<tr>
							<td><bean:message key="screen.e_set.00009"/></td>
							<td>:</td>
							<td>
								<html:select property="SSDay" disabled="true">
									<html:option value=""><bean:message key="screen.e_set.00020" /></html:option>
									<html:options collection="DAY_OF_MONTH" property="id" labelProperty="name"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<td><bean:message key="screen.e_set.00010"/></td>
							<td>:</td>
							<td>
								<html:select property="SSHour" disabled="true">
									<html:option value=""><bean:message key="screen.e_set.00021" /></html:option>
									<html:options collection="TIME_HOUR" property="id" labelProperty="name"/>
								</html:select>
								<html:select property="SSMinute" disabled="true">
									<html:option value=""><bean:message key="screen.e_set.00022" /></html:option>
									<html:options collection="TIME_MINUTE" property="id" labelProperty="name"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<td valign="top"><bean:message key="screen.e_set.00011"/></td>
							<td valign="top">:</td>
							<td rowspan="2">
								<logic:iterate name="_e_setForm" property="SSUsers" id="ssUser">
									<p><html:select name="_e_setForm" property="SSUsers" value="${ssUser}" disabled="true">
										<html:option value=""><bean:message key="screen.e_set.00023" /></html:option>
										<html:optionsCollection name="_e_setForm" property="userList" value="value" label="label"/>
									</html:select>
									</p>
								</logic:iterate>
							</td>
						</tr>
						<tr>
							<td valign="bottom">
								<input type="submit" disabled="true" class="button" name="forward_ssRun" 
									value="<bean:message key="screen.e_set.00016" />" style="float:left;"/>
								<label style="float: left;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
								<div <logic:notEqual name="_e_setForm" property="esetRunDate" value="1">disabled="disabled" style="display:none"</logic:notEqual> >
									<input type="text" name="SSRunDateEntry" id="SSRunDateEntryID" 
										maxlength="10" readonly="readonly" size="15"/>												
									<t:inputCalendar for="SSRunDateEntry" format="dd/MM/yyyy"/>
								</div>
							</td>
							<td>&nbsp;</td>
						</tr>
					</table>
				</div>
				 --%>
				
				<!-- Auto Offset Cash Book Against Invoice/ Debit Note -->
				<t:defineCodeList id="LIST_SYSTEM_BASE"/>
                <t:defineCodeList id="LIST_CUSTOMERTYPE"/>
				<bean:define id="systemBase" value=""/>
				<bean:size id="systemBaseSize" name="LIST_SYSTEM_BASE"/>
				<logic:greaterThan name="systemBaseSize" value="0">
					<bean:define id="systemBase" value="${LIST_SYSTEM_BASE[0].name}"/>
				</logic:greaterThan>
				<input type="hidden" name="systemBase" value="<bean:write name="systemBase"/>"/>
				<%if(accessRight.equals("2")) {%>   
				<logic:equal name="systemBase" value="SG">
					<div class="batchE">
						<table cellpadding="0" cellspacing="0">
							<tr>
								<th colspan="3" align="left"><bean:message key="screen.e_set.00026"/></th>
							</tr>
                            <tr>
                                <td style="width: 60%"><bean:message key="screen.e_set.00052"/></td>
                                <td>:</td>
                                <td>
                                <nested:select property="CBCustomerType" styleClass="CBCustomerType" style="width: 100%;">
                                        <option value=""><bean:message key="screen.e_set.listBox.default"/></option>
                                        <c:forEach items="${LIST_CUSTOMERTYPE}" var="item">
                                            <c:if test="${item.id ne 'BOTH'}">
                                                <html:option value="${item.id}" >${item.name}</html:option>
                                            </c:if>
                                        </c:forEach>
                                    </nested:select>
                                </td>
                            </tr>
							<tr>
								<td style="width: 60%"><bean:message key="screen.e_set.00006"/></td>
								<td>:</td>
								<td>${_e_setForm.map.CBMode}</td>
							</tr>
							<tr>
								<td><bean:message key="screen.e_set.00009"/></td>
								<td>:</td>
								<td>
									<html:select property="CBDay">
										<html:option value=""><bean:message key="screen.e_set.00020" /></html:option>
										<html:options collection="DAY_OF_MONTH" property="id" labelProperty="name"/>
									</html:select>
								</td>
							</tr>
							<tr>
								<td><bean:message key="screen.e_set.00010"/></td>
								<td>:</td>
								<td>
									<html:select property="CBHour">
										<html:option value=""><bean:message key="screen.e_set.00021" /></html:option>
										<html:options collection="TIME_HOUR" property="id" labelProperty="name"/>
									</html:select>
									<html:select property="CBMinute">
										<html:option value=""><bean:message key="screen.e_set.00022" /></html:option>
										<html:options collection="TIME_MINUTE" property="id" labelProperty="name"/>
									</html:select>
								</td>
							</tr>
							<tr>
								<td valign="top">
								    <bean:message key="screen.e_set.00011"/>
								    <p style="margin-top:10px;margin-left:180px;">
                                        <a href="<%=request.getContextPath()%>/E_SET/SC_E_SET_S02BL.do?SCR_ID=ESET&FUNC_ID=CB&TITLE_HDR=<bean:message key="screen.e_set.00026"/>" style="color:#0000FF;"><bean:message key="screen.e_set.00027" /></a>
                                    </p>
								</td>
								<td valign="top">:</td>
								<td rowspan="2">
									<logic:iterate name="_e_setForm" property="CBUsers" id="cbUser">
										<p><html:select name="_e_setForm" property="CBUsers" value="${cbUser}">
											<html:option value=""><bean:message key="screen.e_set.00023" /></html:option>
											<html:optionsCollection name="_e_setForm" property="userList" value="value" label="label"/>
										</html:select>
										</p>
									</logic:iterate>
								</td>
							</tr>
							<tr>
								<td valign="bottom">
									<input type="submit" class="button" name="forward_cbRun" <c:if test="${'-1' eq _e_setForm.map.CBRetStatus}">disabled="disabled"</c:if>
										value="<bean:message key="screen.e_set.00016" />" style="float: left;"/>
									<label style="float: left;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
									<%-- 
									<div <logic:notEqual name="_e_setForm" property="esetRunDate" value="1">disabled="disabled" style="display:none"</logic:notEqual> >
										<input type="text" name="CBRunDateEntry" id="CBRunDateEntryID" 
											maxlength="10" readonly="readonly" size="15"/>												
										<t:inputCalendar for="CBRunDateEntry" format="dd/MM/yyyy"/>
									</div>
									--%>
								</td>
								<td>&nbsp;</td>
							</tr>
						</table>
					</div>
					<div style="clear:both;"></div>
				</logic:equal>
				<%}else{%>   
				    <logic:equal name="systemBase" value="SG">
                    <div class="batchE">
                        <table cellpadding="0" cellspacing="0">
                            <tr>
                                <th colspan="3" align="left"><bean:message key="screen.e_set.00026"/></th>
                            </tr>
                            <tr>
                                <td style="width: 60%"><bean:message key="screen.e_set.00006"/></td>
                                <td>:</td>
                                <td>${_e_setForm.map.CBMode}</td>
                            </tr>
                            <tr>
                                <td><bean:message key="screen.e_set.00009"/></td>
                                <td>:</td>
                                <td>
                                    <html:select property="CBDay" disabled="true">
                                        <html:option value=""><bean:message key="screen.e_set.00020" /></html:option>
                                        <html:options collection="DAY_OF_MONTH" property="id" labelProperty="name"/>
                                    </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td><bean:message key="screen.e_set.00010"/></td>
                                <td>:</td>
                                <td>
                                    <html:select property="CBHour" disabled="true">
                                        <html:option value=""><bean:message key="screen.e_set.00021" /></html:option>
                                        <html:options collection="TIME_HOUR" property="id" labelProperty="name"/>
                                    </html:select>
                                    <html:select property="CBMinute" disabled="true">
                                        <html:option value=""><bean:message key="screen.e_set.00022" /></html:option>
                                        <html:options collection="TIME_MINUTE" property="id" labelProperty="name"/>
                                    </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td valign="top">
                                    <bean:message key="screen.e_set.00011"/>
                                    <p style="margin-top:10px;margin-left:180px;">
                                        <a href="<%=request.getContextPath()%>/E_SET/SC_E_SET_S02BL.do?SCR_ID=ESET&FUNC_ID=CB&TITLE_HDR=<bean:message key="screen.e_set.00026"/>" style="color:#0000FF;"><bean:message key="screen.e_set.00027" /></a>
                                    </p>
                                </td>
                                <td valign="top">:</td>
                                <td rowspan="2">
                                    <logic:iterate name="_e_setForm" property="CBUsers" id="cbUser">
                                        <p><html:select name="_e_setForm" property="CBUsers" value="${cbUser}" disabled="true">
                                            <html:option value=""><bean:message key="screen.e_set.00023" /></html:option>
                                            <html:optionsCollection name="_e_setForm" property="userList" value="value" label="label"/>
                                        </html:select>
                                        </p>
                                    </logic:iterate>
                                </td>
                            </tr>
                            <tr>
                                <td valign="bottom">
                                    <input type="submit" class="button" name="forward_cbRun" disabled="disabled"
                                        value="<bean:message key="screen.e_set.00016" />" style="float: left;"/>
                                    <label style="float: left;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                                    <%-- 
                                    <div <logic:notEqual name="_e_setForm" property="esetRunDate" value="1">disabled="disabled" style="display:none"</logic:notEqual> >
                                        <input type="text" name="CBRunDateEntry" id="CBRunDateEntryID" 
                                            maxlength="10" readonly="readonly" size="15"/>                                              
                                        <t:inputCalendar for="CBRunDateEntry" format="dd/MM/yyyy"/>
                                    </div>
                                    --%>
                                </td>
                                <td>&nbsp;</td>
                            </tr>
                        </table>
                    </div>
                    <div style="clear:both;"></div>
                </logic:equal>
				<%}%> 
				<!-- PeopleSoft AR Export -->
				<%-- 
				<logic:notEqual name="systemBase" value="SG">
					<div class="batchE2">
						<table cellpadding="0" cellspacing="0">
							<tr>
								<th colspan="3" align="left"><bean:message key="screen.e_set.00024"/></th>
							</tr>
							<tr>
								<td style="width: 60%"><bean:message key="screen.e_set.00006"/></td>
								<td>:</td>
								<td>${_e_setForm.map.ARMode}</td>
							</tr>
							<tr>
								<td><bean:message key="screen.e_set.00009"/></td>
								<td>:</td>
								<td>
									<html:select property="ARDay">
										<html:option value=""><bean:message key="screen.e_set.00020" /></html:option>
										<html:options collection="DAY_OF_MONTH" property="id" labelProperty="name"/>
									</html:select>
								</td>
							</tr>
							<tr>
								<td><bean:message key="screen.e_set.00010"/></td>
								<td>:</td>
								<td>
									<html:select property="ARHour">
										<html:option value=""><bean:message key="screen.e_set.00021" /></html:option>
										<html:options collection="TIME_HOUR" property="id" labelProperty="name"/>
									</html:select>
									<html:select property="ARMinute">
										<html:option value=""><bean:message key="screen.e_set.00022" /></html:option>
										<html:options collection="TIME_MINUTE" property="id" labelProperty="name"/>
									</html:select>
								</td>
							</tr>
							<tr>
								<td valign="top"><bean:message key="screen.e_set.00011"/></td>
								<td valign="top">:</td>
								<td rowspan="2">
									<logic:iterate name="_e_setForm" property="ARUsers" id="arUser">
										<p><html:select name="_e_setForm" property="ARUsers" value="${arUser}">
											<html:option value=""><bean:message key="screen.e_set.00023" /></html:option>
											<html:optionsCollection name="_e_setForm" property="userList" value="value" label="label"/>
										</html:select>
										</p>
									</logic:iterate>
								</td>
							</tr>
							<tr>
								<td valign="bottom">
									<input type="submit" class="button" name="forward_arRun" style="float: left;" <c:if test="${'-1' eq _e_setForm.map.ARRetStatus}">disabled="disabled"</c:if>
										value="<bean:message key="screen.e_set.00016"/>" onclick="return checkRunDate('ARRunDateEntry', 'arrun');"/>
									<label style="float: left;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
									<div <logic:notEqual name="_e_setForm" property="esetRunDate" value="1">disabled="disabled" style="display:none"</logic:notEqual> >
										<input type="text" name="ARRunDateEntry" id="ARRunDateEntryID" 
											maxlength="10" readonly="readonly" size="15"/>
										<t:inputCalendar for="ARRunDateEntry" format="dd/MM/yyyy"/>
									</div>
								</td>
								<td>&nbsp;</td>
							</tr>
						</table>
					</div>
				</logic:notEqual>
				--%>
				<div style="clear:both;"></div>
				
				<%-- Update Billing Account Total Amount Due --%>
				<input type="hidden" name="bACalc" value="<bean:write name="_e_setForm" property="BACalc"/>"/>
                <%if(accessRight.equals("2")) {%>
                    <logic:notEqual name="_e_setForm" property="BACalc" value="0">
                    <div class="batchE">
                        <table cellpadding="0" cellspacing="0">
                            <tr>
                                <th colspan="3" align="left"><bean:message key="screen.e_set.00051"/></th>
                            </tr>
                            <tr>
                                <td valign="top" style="width: 60%">
                                    <bean:message key="screen.e_set.00011"/>
                                    <p style="margin-top:10px;margin-left:180px;">
                                        <a href="<%=request.getContextPath()%>/E_SET/SC_E_SET_S02BL.do?SCR_ID=ESET&FUNC_ID=BA&TITLE_HDR=<bean:message key="screen.e_set.00051"/>" style="color:#0000FF;"><bean:message key="screen.e_set.00027" /></a>
                                    </p>
                                </td>
                                <td valign="top">:</td>
                                <td valign="top" rowspan="5">
                                     <logic:iterate name="_e_setForm" property="BAUsers" id="baUser">
                                        <p><html:select name="_e_setForm" property="BAUsers" value="${baUser}">
                                            <html:option value=""><bean:message key="screen.e_set.00023" /></html:option>
                                            <html:optionsCollection name="_e_setForm" property="userList" value="value" label="label"/>
                                        </html:select>
                                        </p>
                                    </logic:iterate>
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td valign="bottom">
                                    <input type="submit" class="button" name="forward_baRun" <c:if test="${'-1' eq _e_setForm.map.BARetStatus}">disabled="disabled"</c:if>
                                        value="<bean:message key="screen.e_set.00016" />" style="float: left;"/>
                                    <label style="float: left;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                                </td>
                                <td>&nbsp;</td>
                            </tr>
                        </table>
                    </div>
                    </logic:notEqual>
                <%}  else { %>
                    <logic:notEqual name="_e_setForm" property="BACalc" value="0">
                    <div class="batchE">
                        <table cellpadding="0" cellspacing="0">
                            <tr>
                                <th colspan="3" align="left"><bean:message key="screen.e_set.00051"/></th>
                            </tr>
                            <tr>
                                <td valign="top" style="width: 60%">
                                    <bean:message key="screen.e_set.00006"/>
                                    <p style="margin-top:10px;margin-left:180px;">
                                        <a href="<%=request.getContextPath()%>/E_SET/SC_E_SET_S02BL.do?SCR_ID=ESET&FUNC_ID=BA&TITLE_HDR=<bean:message key="screen.e_set.00051"/>" style="color:#0000FF;"><bean:message key="screen.e_set.00027" /></a>
                                    </p>
                                </td>
                                <td valign="top">:</td>
                                <td valign="top" rowspan="5">
                                     <logic:iterate name="_e_setForm" property="BAUsers" id="baUser">
                                        <p><html:select name="_e_setForm" property="BAUsers" value="${baUser}" disabled="true">
                                            <html:option value=""><bean:message key="screen.e_set.00023" /></html:option>
                                            <html:optionsCollection name="_e_setForm" property="userList" value="value" label="label"/>
                                        </html:select>
                                        </p>
                                    </logic:iterate>
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td valign="bottom">
                                    <input type="submit" class="button" name="forward_baRun" disabled="disabled"
                                        value="<bean:message key="screen.e_set.00016" />" style="float: left;"/>
                                    <label style="float: left;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                                </td>
                                <td>&nbsp;</td>
                            </tr>
                        </table>
                    </div>
                    </logic:notEqual>
                <%} %>
				<div style="clear:both;"></div>
			</div>
			<hr/>
			<input type="hidden" name="esetRunDateValue" value=""/>
			<input type="submit" name="forward_save" class="button" value="<bean:message key="screen.e_set.00017" />"/>
		</ts:form>

		<div class="message">
			<ts:messages id="message" message="true">
				<bean:write name="message"/><br/>
			</ts:messages>
		</div>
		<div class="error">
			<ts:messages id="message">
				<bean:write name="message"/><br/>
			</ts:messages>
		</div>
		
		<div style="visibility: hidden">
			<input type="hidden" id="msgInvalidDateID" value="<bean:message key="errors.ERR1SC063"/>"/>
			<input type="hidden" id="errors.ERR1SC063" value='<bean:message key="errors.ERR1SC063" arg0="replace"/>'/>
		</div>
	</body>
</html:html>