<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-html" prefix="html"%>
<%@ taglib uri="/struts-bean" prefix="bean"%>
<%@ taglib uri="/struts-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts"%>
<%@ taglib uri="/terasoluna" prefix="t"%>
<%@page import="nttdm.bsys.common.fw.BillingSystemUserValueObject"%>
<%@page import="nttdm.bsys.common.util.CommonUtils"%> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=UTF-8">
<title><bean:message key="screen.m_gbs01.label.title" /></title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/common.css"/>
<link type="text/css" rel="stylesheet" href="css/m_gbs.css"/>
<script type="text/javascript" src="js/m_gbss01.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/common.js"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/tabcontent.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/messageBox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheet/tabcontent.css"/>
</head>
<body id="m" onload="initMain();initPage();">
<!-- check access right START -->
<%
    BillingSystemUserValueObject uvo = (BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT");
    String accessRight = CommonUtils.getAccessRight(uvo, "M-GBS");
%>
 <bean:define id="accessRightBean" value="<%=accessRight %>"/>
<logic:notEqual value="2" name="accessRightBean">
<script type="text/javascript">
    window.location = '<%=request.getContextPath()%>/C_CMN001/C_CMN001S06SCR.do';
</script>
</logic:notEqual>
<!-- check access right END -->
<ts:form  action="/M_GBSS01_02BL" method="post" styleId="_m_gbsForm">
	<input type="hidden" name="actionType" id="actionType" value="update" />
	<table class="subHeader" cellpadding="0" cellspacing="0">
		<tr>
			<td><bean:message key="screen.m_gbs01.label.title" /></td>
		</tr>
	</table>
	<p>&nbsp;</p>
    <!-- JSP Tab control -->
    	    <!-- JSP Tab control -->
		<ul id="settingTabs" class="shadetabs">
			<li><a href="#" rel="tabGeneral" > <bean:message key="screen.m_gbs01.tab.general"/></a></li>
			<li><a href="#" rel="tabBilling"><bean:message key="screen.m_gbs01.tab.billing"/></a></li>
			<li><a href="#" rel="tabFilePath"><bean:message key="screen.m_gbs01.tab.filepath"/></a></li>
		</ul>
		

		<div id="tabGeneral" class="tabcontent">
			<div style="border:1px solid gray; width:100%; margin-bottom: 1em; padding: 10px">
				<table class="contentTable">
					<bean:define id="dispDashboardGroup" value="0"></bean:define>
					<logic:equal name="_m_gbsForm" property="dispDASHBD1" value="1">
						<bean:define id="dispDashboardGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="dispDashboardGroup" value="1">
					<tr>
						<td>
							<div style="width:220px; float: left">
								<b><bean:message key="screen.m_gbs01.label.dashboard"/></b>&nbsp;<font color="red">*</font>
							</div>
							<logic:equal name="_m_gbsForm" property="dispDASHBD1" value="1">
							<div>
								<div style="float:left;width:320px">
									<bean:message key="screen.m_gbs01.label.dashboard.detail"/>&nbsp;&nbsp;&nbsp;
								</div>						
								<div style="float:left;width:1px">
								:
								</div>					
								<div>
									&nbsp;&nbsp;&nbsp;<html:text size="5" maxlength="3" property="tbxValueDASHBD1" style="text-align: right;"></html:text>							
								</div>
							</div>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispDASHBD1"/>
						</td>
					</tr>
					</logic:equal>
					
					<bean:define id="dispUserMaintenanceGroup" value="0"></bean:define>
					<logic:equal name="_m_gbsForm" property="dispUSRMNT1" value="1">
						<bean:define id="dispUserMaintenanceGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="dispUserMaintenanceGroup" value="1">
					<tr>
						<td>
							<div style="width:220px; float: left">
								<b><bean:message key="screen.m_gbs01.label.usermaintenance"/></b>&nbsp;<font color="red">*</font>
							</div>
							<logic:equal name="_m_gbsForm" property="dispUSRMNT1" value="1">
							<div>
								<div style="float:left;width:320px">
									<bean:message key="screen.m_gbs01.label.usermaintenance.detail"/>&nbsp;&nbsp;&nbsp;
								</div>						
								<div style="float:left;width:1px">
								:
								</div>					
								<div>
									&nbsp;&nbsp;&nbsp;<html:text size="5" maxlength="3" property="tbxValueUSRMNT1" style="text-align: right;"></html:text>							
								</div>
							</div>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispUSRMNT1"/>
						</td>
					</tr>
					</logic:equal>
					
					<bean:define id="dispPassChangePeriodGroup" value="0"></bean:define>
					<logic:equal name="_m_gbsForm" property="dispPWDCH1" value="1">
						<bean:define id="dispPassChangePeriodGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="dispPassChangePeriodGroup" value="1">
					<tr>
						<td>
							<div style="width:220px; float: left">
								<b><bean:message key="screen.m_gbs01.label.passwordchangeperiod"/></b>&nbsp;<font color="red">*</font>
							</div>
							<logic:equal name="_m_gbsForm" property="dispPWDCH1" value="1">
							<div>
								<div style="float:left;width:320px">
									<bean:message key="screen.m_gbs01.label.passwordchangeperiod.detail"/>&nbsp;&nbsp;&nbsp;
								</div>						
								<div style="float:left;width:1px">
								:
								</div>					
								<div>
									&nbsp;&nbsp;&nbsp;<html:text size="5" maxlength="3" property="tbxValuePWDCH1" style="text-align: right;"></html:text>							
								</div>
							</div>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispPWDCH1"/>
						</td>
					</tr>
					</logic:equal>
					
					<bean:define id="dispLoginAttemptGroup" value="0"></bean:define>
					<logic:equal name="_m_gbsForm" property="dispLOGINAT1" value="1">
						<bean:define id="dispLoginAttemptGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="_m_gbsForm" property="dispLOGINAT2" value="1">
						<bean:define id="dispLoginAttemptGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="dispLoginAttemptGroup" value="1">
					<tr>
						<td height="20px">
							<div style="width:220px; float: left;height: 100%;">
								<b><bean:message key="screen.m_gbs01.label.loginattempts"/></b>&nbsp;<font color="red">*</font>
								&nbsp;
							</div>
							<div>
								<div>
									<logic:equal name="_m_gbsForm" property="dispLOGINAT1" value="1">
									<div>
										<div style="width:320px; float: left">
											<bean:message key="screen.m_gbs01.label.loginattempts1.detail"/>&nbsp;&nbsp;&nbsp;
										</div>
										<div style="float:left;width:1px">
										:
										</div>					
										<div>
											&nbsp;&nbsp;&nbsp;<html:text size="5" maxlength="3" property="tbxValueLOGINAT1" style="text-align: right;"></html:text><br/>
										</div>
									</div>
									</logic:equal>
									<html:hidden name="_m_gbsForm" property="dispLOGINAT1"/>
									<logic:equal name="_m_gbsForm" property="dispLOGINAT2" value="1">
									<div>
										<div style="width:320px; float: left">
											<bean:message key="screen.m_gbs01.label.loginattempts2.detail"/>&nbsp;&nbsp;&nbsp;
										</div>
										<div style="float:left;width:1px">
										:
										</div>					
										<div>
											&nbsp;&nbsp;&nbsp;<html:text size="5" maxlength="3" property="tbxValueLOGINAT2" style="text-align: right;"></html:text>	
										</div>
									</div>
									</logic:equal>
									<html:hidden name="_m_gbsForm" property="dispLOGINAT2"/>
								</div>
							</div>
						</td>
					</tr>
					</logic:equal>
					
					<bean:define id="dispRowResultGroup" value="0"></bean:define>
					<logic:equal name="_m_gbsForm" property="dispRESULTROW1" value="1">
						<bean:define id="dispRowResultGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="dispRowResultGroup" value="1">
					<tr>
						<td>
							<div style="width:220px; float: left">
								<b><bean:message key="screen.m_gbs01.label.rowresultdisplay"/></b>&nbsp;<font color="red">*</font>
							</div>
							<logic:equal name="_m_gbsForm" property="dispRESULTROW1" value="1">
							<div>
								<div style="float:left;width:320px">
									<bean:message key="screen.m_gbs01.label.rowresultdisplay.detail"/>&nbsp;&nbsp;&nbsp;
								</div>						
								<div style="float:left;width:1px">
								:
								</div>					
								<div>
									&nbsp;&nbsp;&nbsp;<html:text size="5" maxlength="3" property="tbxValueRESULTROW1" style="text-align: right;"></html:text>								
								</div>
							</div>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispRESULTROW1"/>
						</td>
					</tr>
					</logic:equal>
					
					<bean:define id="dispDefCurrencyGroup" value="0"></bean:define>
					<logic:equal name="_m_gbsForm" property="dispDEF_CURRENCY1" value="1">
						<bean:define id="dispDefCurrencyGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="dispDefCurrencyGroup" value="1">
					<tr>
						<td>
							<div style="width:220px; float: left">
								<b><bean:message key="screen.m_gbs01.label.defaultcurrency"/></b>
							</div>
							<logic:equal name="_m_gbsForm" property="dispDEF_CURRENCY1" value="1">
							<div>
								<div style="float:left;width:320px">
									<bean:message key="screen.m_gbs01.label.defaultcurrency.detail"/>&nbsp;&nbsp;&nbsp;
								</div>						
								<div style="float:left;width:1px">
								:
								</div>					
								<div>
									<html:hidden name="_m_gbsForm" property="lblValueDEF_CURRENCY1"/>
									&nbsp;&nbsp;&nbsp;<bean:write name="_m_gbsForm" property="lblValueDEF_CURRENCY1"/>							
								</div>
							</div>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispDEF_CURRENCY1"/>
						</td>
					</tr>
					</logic:equal>
					
					<bean:define id="dispSystemBaseGroup" value="0"></bean:define>
					<logic:equal name="_m_gbsForm" property="dispSYSTEMBASE1" value="1">
						<bean:define id="dispSystemBaseGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="dispSystemBaseGroup" value="1">
					<tr>
						<td>
							<div style="width:220px; float: left">
								<b><bean:message key="screen.m_gbs01.label.systembase"/></b>
							</div>
							<logic:equal name="_m_gbsForm" property="dispSYSTEMBASE1" value="1">
							<div>
								<div style="float:left;width:320px">
									<bean:message key="screen.m_gbs01.label.systembase.detail"/>&nbsp;&nbsp;&nbsp;
								</div>						
								<div style="float:left;width:1px">
								:
								</div>					
								<div>
									<html:hidden name="_m_gbsForm" property="lblValueSYSTEMBASE1"/>
									&nbsp;&nbsp;&nbsp;<bean:write name="_m_gbsForm" property="lblValueSYSTEMBASE1"/>							
								</div>
							</div>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispSYSTEMBASE1"/>
						</td>
					</tr>
					</logic:equal>
					
					<bean:define id="dispFileSizeUploadGroup" value="0"></bean:define>
					<logic:equal name="_m_gbsForm" property="dispFILESIZEUPLOAD1" value="1">
						<bean:define id="dispFileSizeUploadGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="dispFileSizeUploadGroup" value="1">
					<tr>
						<td>
							<div style="width:220px; float: left">
								<b><bean:message key="screen.m_gbs01.label.limitforfilesizeupload"/></b>&nbsp;<font color="red">*</font>
							</div>
							<logic:equal name="_m_gbsForm" property="dispFILESIZEUPLOAD1" value="1">
							<div>
								<div style="float:left;width:320px">
									<bean:message key="screen.m_gbs01.label.limitforfilesizeupload.detail"/>&nbsp;&nbsp;&nbsp;
								</div>						
								<div style="float:left;width:1px">
								:
								</div>					
								<div>
									&nbsp;&nbsp;&nbsp;<html:text size="10" maxlength="10" property="tbxValueFILESIZEUPLOAD1" style="text-align: right;"/>							
								</div>
							</div>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispFILESIZEUPLOAD1"/>
						</td>
					</tr>
					</logic:equal>
					
					<bean:define id="dispServiceRowGroup" value="0"></bean:define>
					<logic:equal name="_m_gbsForm" property="dispSERVICEROW1" value="1">
						<bean:define id="dispServiceRowGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="dispServiceRowGroup" value="1">
					<tr>
						<td>
							<div style="width:220px; float: left">
								<b><bean:message key="screen.m_gbs01.label.servicerow"/></b>&nbsp;<font color="red">*</font>
							</div>
							<logic:equal name="_m_gbsForm" property="dispSERVICEROW1" value="1">
							<div>
								<div style="float:left;width:320px">
									<bean:message key="screen.m_gbs01.label.servicerow.detail"/>&nbsp;&nbsp;&nbsp;
								</div>						
								<div style="float:left;width:1px">
								:
								</div>					
								<div>
									&nbsp;&nbsp;&nbsp;<html:text size="5" maxlength="3" property="tbxValueSERVICEROW1" style="text-align: right;"/>							
								</div>
							</div>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispSERVICEROW1"/>
						</td>
					</tr>
					</logic:equal>
					
					<bean:define id="dispDescLengthGroup" value="0"></bean:define>
					<logic:equal name="_m_gbsForm" property="dispDESC_LENGTH1" value="1">
						<bean:define id="dispDescLengthGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="dispDescLengthGroup" value="1">
					<tr>
						<td>
							<div style="width:220px; float: left">
								<b><bean:message key="screen.m_gbs01.label.displaydescriptionlength"/></b>&nbsp;<font color="red">*</font>
							</div>
							<logic:equal name="_m_gbsForm" property="dispDESC_LENGTH1" value="1">
							<div>
								<div style="float:left;width:320px">
									<bean:message key="screen.m_gbs01.label.displaydescriptionlength.detail"/>&nbsp;&nbsp;&nbsp;
								</div>						
								<div style="float:left;width:1px">
								:
								</div>					
								<div>
									&nbsp;&nbsp;&nbsp;<html:text size="5" maxlength="3" property="tbxValueDESC_LENGTH1" style="text-align: right;"/>							
								</div>
							</div>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispDESC_LENGTH1"/>
						</td>
					</tr>
					</logic:equal>
					
					<bean:define id="dispBatchMsgGroup" value="0"></bean:define>
					<logic:equal name="_m_gbsForm" property="dispBATCH_MSG1" value="1">
						<bean:define id="dispBatchMsgGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="_m_gbsForm" property="dispBATCH_MSG2" value="1">
						<bean:define id="dispBatchMsgGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="_m_gbsForm" property="dispBATCH_MSG3" value="1">
						<bean:define id="dispBatchMsgGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="dispBatchMsgGroup" value="1">
					<tr>
						<td height="20px">
								<div style="width:220px; float: left;height: 100%;">
									<b><bean:message key="screen.m_gbs01.label.batchmessage"/></b>
								</div>
								<logic:equal name="_m_gbsForm" property="dispBATCH_MSG1" value="1">
								<div>
									<div style="float:left;width:160px">
										<bean:message key="screen.m_gbs01.label.batchmessage.detail"/>&nbsp;&nbsp;&nbsp;
									</div>
									<div style="float:left;width:1px">
									:
									</div>					
									<div style="width: 100%">
										&nbsp;&nbsp;&nbsp;<html:text size="80" property="tbxValueBATCH_MSG1" maxlength="150"/>							
									</div>
								</div>
								</logic:equal>
								<html:hidden name="_m_gbsForm" property="dispBATCH_MSG1"/>
							
							<logic:equal name="_m_gbsForm" property="dispBATCH_MSG2" value="1">
								<div>
									<div style="float:left;width:160px">
										<bean:message key="screen.m_gbs01.label.batchmessage.detail2"/>&nbsp;&nbsp;&nbsp;
									</div>
									<div style="float:left;width:1px">
									:
									</div>					
									<div>
										&nbsp;&nbsp;&nbsp;<html:text size="80" property="tbxValueBATCH_MSG2" maxlength="150"/>							
									</div>
								</div>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispBATCH_MSG2"/>
							<logic:equal name="_m_gbsForm" property="dispBATCH_MSG3" value="1">
								<div>
									<div style="float:left;width:160px">
										<bean:message key="screen.m_gbs01.label.batchmessage.detail3"/>&nbsp;&nbsp;&nbsp;
									</div>
									<div style="float:left;width:1px">
									:
									</div>					
									<div>
										&nbsp;&nbsp;&nbsp;<html:text size="80" property="tbxValueBATCH_MSG3" maxlength="150"/>							
									</div>
								</div>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispBATCH_MSG3"/>
						</td>
					</tr>
					</logic:equal>
				</table>
			</div>
		</div>
		<div id="tabBilling" class="tabcontent">
			<div style="border:1px solid gray; width:100%; margin-bottom: 1em; padding: 10px">
				<table class="contentTable">
					<bean:define id="dispDebterAccountGroup" value="0"></bean:define>
					<logic:equal name="_m_gbsForm" property="dispBIL_DEBTER_ACC1" value="1">
						<bean:define id="dispDebterAccountGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="dispDebterAccountGroup" value="1">
					<tr>
						<td>
							<div style="width:220px; float: left">
								<b><bean:message key="screen.m_gbs01.label.debteraccount"/></b>&nbsp;<font color="red">*</font>
							</div>
							<logic:equal name="_m_gbsForm" property="dispBIL_DEBTER_ACC1" value="1">
							<div>
								<div style="float:left;width:320px">
									<bean:message key="screen.m_gbs01.label.debteraccount.detail"/>&nbsp;&nbsp;&nbsp;
								</div>						
								<div style="float:left;width:1px">
								:
								</div>					
								<div>
									&nbsp;&nbsp;&nbsp;<html:text size="15" property="tbxValueBIL_DEBTER_ACC1" styleId="txbDebterAcc" maxlength="250"></html:text>							
								</div>
							</div>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispBIL_DEBTER_ACC1"/>
						</td>
					</tr>
					</logic:equal>
					<!--Add Advance Debtor Account Start-->
					<bean:define id="dispAdvanceDebterAccountGroup" value="0"></bean:define>
					<logic:equal name="_m_gbsForm" property="dispBIL_ADVANCE_DEBTER_ACC1" value="1">
						<bean:define id="dispAdvanceDebterAccountGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="dispAdvanceDebterAccountGroup" value="1">
						<tr>
							<td>
								<div style="width:220px; float: left">
								<b><bean:message key="screen.m_gbs01.label.advancedebteraccount"/></b>&nbsp;<font color="red">*</font>
							</div>
							<logic:equal name="_m_gbsForm" property="dispBIL_ADVANCE_DEBTER_ACC1" value="1">
								<div>
									<div style="float:left;width:320px">
										<bean:message key="screen.m_gbs01.label.advancedebteraccount.detail"/>&nbsp;&nbsp;&nbsp;
									</div>
									<div style="float:left;width:1px">
									:
									</div>
									<div>
										&nbsp;&nbsp;&nbsp;<html:text size="15" property="tbxValueBIL_ADVANCE_DEBTER_ACC1" styleId="txbAdvanceAcc" maxlength="250"></html:text>							
									</div>
								</div>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispBIL_ADVANCE_DEBTER_ACC1"/>
							</td>
						</tr>
					</logic:equal>
					<!--Add Advance Debtor Account End-->
					<bean:define id="dispGSTGroup" value="0"></bean:define>
					<logic:equal name="_m_gbsForm" property="dispGST1" value="1">
						<bean:define id="dispGSTGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="dispGSTGroup" value="1">
					<tr>
						<td>
							<div style="width:220px; float: left">
								<b><bean:message key="screen.m_gbs01.label.governmentservicetax"/></b>&nbsp;<font color="red">*</font>
							</div>
							<logic:equal name="_m_gbsForm" property="dispGST1" value="1">
							<div>
								<div style="float:left;width:320px">
									<bean:message key="screen.m_gbs01.label.governmentservicetax.detail"/>&nbsp;&nbsp;&nbsp;
								</div>						
								<div style="float:left;width:1px">
								:
								</div>					
								<div>
									&nbsp;&nbsp;&nbsp;<html:text size="5" maxlength="3" property="tbxValueGST1" style="text-align: right;"></html:text>							
								</div>
							</div>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispGST1"/>
						</td>
					</tr>
					</logic:equal>
					
					<bean:define id="dispInvoiceDuePeriodGroup" value="0"></bean:define>
					<logic:equal name="_m_gbsForm" property="dispINVOICE_DUE_PERIOD1" value="1">
						<bean:define id="dispInvoiceDuePeriodGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="dispInvoiceDuePeriodGroup" value="1">
					<tr>
						<td>
							<div style="width:220px; float: left">
								<b><bean:message key="screen.m_gbs01.label.invoicedueperiod"/></b>&nbsp;<font color="red">*</font>
							</div>
							<logic:equal name="_m_gbsForm" property="dispINVOICE_DUE_PERIOD1" value="1">
							<div>
								<div style="float:left;width:320px">
									<bean:message key="screen.m_gbs01.label.invoicedueperiod.detail"/>&nbsp;&nbsp;&nbsp;
								</div>						
								<div style="float:left;width:1px">
								:
								</div>					
								<div>
									&nbsp;&nbsp;&nbsp;<html:text size="5" maxlength="3" property="tbxValueINVOICE_DUE_PERIOD1" style="text-align: right;"></html:text>							
								</div>
							</div>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispINVOICE_DUE_PERIOD1"/>
						</td>
					</tr>
					</logic:equal>
					
					<bean:define id="dispNoPrintDocGroup" value="0"></bean:define>
					<logic:equal name="_m_gbsForm" property="dispNOPRINTDOC1" value="1">
						<bean:define id="dispNoPrintDocGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="dispNoPrintDocGroup" value="1">
					<tr>
						<td>
							<div style="width:220px; float: left;">
								<b><bean:message key="screen.m_gbs01.label.numberoftimetoprintbillingdocument"/></b>&nbsp;
							</div>
							<logic:equal name="_m_gbsForm" property="dispNOPRINTDOC1" value="1">
							<div>
								<div style="float:left;width:320px">
									<bean:message key="screen.m_gbs01.label.numberoftimetoprintbillingdocument.detail"/>&nbsp;&nbsp;&nbsp;
								</div>						
								<div style="float:left;width:1px">
								:
								</div>					
								<div>
									&nbsp;&nbsp;&nbsp;<html:text size="5" maxlength="3" property="tbxValueNOPRINTDOC1" style="text-align: right;"></html:text>								
								</div>
							</div>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispNOPRINTDOC1"/>
						</td>
					</tr>
					</logic:equal>
					
					<bean:define id="dispBillDocFooterGroup" value="0"></bean:define>
					<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER1" value="1">
						<bean:define id="dispBillDocFooterGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER2" value="1">
						<bean:define id="dispBillDocFooterGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER3" value="1">
						<bean:define id="dispBillDocFooterGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER4" value="1">
						<bean:define id="dispBillDocFooterGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER5" value="1">
						<bean:define id="dispBillDocFooterGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER6" value="1">
						<bean:define id="dispBillDocFooterGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER7" value="1">
						<bean:define id="dispBillDocFooterGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER8" value="1">
						<bean:define id="dispBillDocFooterGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER9" value="1">
						<bean:define id="dispBillDocFooterGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER10" value="1">
						<bean:define id="dispBillDocFooterGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER11" value="1">
						<bean:define id="dispBillDocFooterGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER12" value="1">
						<bean:define id="dispBillDocFooterGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER13" value="1">
						<bean:define id="dispBillDocFooterGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER14" value="1">
						<bean:define id="dispBillDocFooterGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER15" value="1">
						<bean:define id="dispBillDocFooterGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="dispBillDocFooterGroup" value="1">
					<tr>
						<td height="20px">							
							<div style="width:220px; float: left;height: 100%;">
								<div style="width:190px;"><b><bean:message key="screen.m_gbs01.label.billingdocumentfooterdisplay1header"/></b></div>								
							</div>
							<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER1" value="1">
							<div>
								<div style="float:left;width:60px">
									<bean:message key="screen.m_gbs01.label.billingdocumentfooterdisplay1"/>&nbsp;&nbsp;&nbsp;
								</div>
								<div style="float:left;width:1px">
								:
								</div>					
								<div>
									&nbsp;&nbsp;&nbsp;<html:text size="80" property="tbxValueBILL_DOC_FOOTER1" maxlength="250"/>							
								</div>
							</div>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispBILL_DOC_FOOTER1"/>
							<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER2" value="1">
							<div>
								<div style="float:left;width:60px">
									<bean:message key="screen.m_gbs01.label.billingdocumentfooterdisplay2"/>&nbsp;&nbsp;&nbsp;
								</div>
								<div style="float:left;width:1px">
								:
								</div>					
								<div>
									&nbsp;&nbsp;&nbsp;<html:text size="80" property="tbxValueBILL_DOC_FOOTER2" maxlength="250"/>							
								</div>
							</div>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispBILL_DOC_FOOTER2"/>
							<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER3" value="1">
							<div>
								<div style="float:left;width:60px">
									<bean:message key="screen.m_gbs01.label.billingdocumentfooterdisplay3"/>&nbsp;&nbsp;&nbsp;
								</div>
								<div style="float:left;width:1px">
								:
								</div>					
								<div>
									&nbsp;&nbsp;&nbsp;<html:text size="80" property="tbxValueBILL_DOC_FOOTER3" maxlength="250"/>							
								</div>
							</div>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispBILL_DOC_FOOTER3"/>
							<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER4" value="1">
							<div>
								<div style="float:left;width:60px">
									<bean:message key="screen.m_gbs01.label.billingdocumentfooterdisplay4"/>&nbsp;&nbsp;&nbsp;
								</div>
								<div style="float:left;width:1px">
								:
								</div>					
								<div>
									&nbsp;&nbsp;&nbsp;<html:text size="80" property="tbxValueBILL_DOC_FOOTER4" maxlength="250"/>							
								</div>
							</div>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispBILL_DOC_FOOTER4"/>
							<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER5" value="1">
							<div>
								<div style="float:left;width:60px">
									<bean:message key="screen.m_gbs01.label.billingdocumentfooterdisplay5"/>&nbsp;&nbsp;&nbsp;
								</div>
								<div style="float:left;width:1px">
								:
								</div>					
								<div>
									&nbsp;&nbsp;&nbsp;<html:text size="80" property="tbxValueBILL_DOC_FOOTER5" maxlength="250"/>							
								</div>
							</div>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispBILL_DOC_FOOTER5"/>
							<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER6" value="1">
							<div>
								<div style="float:left;width:60px">
									<bean:message key="screen.m_gbs01.label.billingdocumentfooterdisplay6"/>&nbsp;&nbsp;&nbsp;
								</div>
								<div style="float:left;width:1px">
								:
								</div>					
								<div>
									&nbsp;&nbsp;&nbsp;<html:text size="80" property="tbxValueBILL_DOC_FOOTER6" maxlength="250"/>							
								</div>
							</div>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispBILL_DOC_FOOTER6"/>
							<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER7" value="1">
							<div>
								<div style="float:left;width:60px">
									<bean:message key="screen.m_gbs01.label.billingdocumentfooterdisplay7"/>&nbsp;&nbsp;&nbsp;
								</div>
								<div style="float:left;width:1px">
								:
								</div>					
								<div>
									&nbsp;&nbsp;&nbsp;<html:text size="80" property="tbxValueBILL_DOC_FOOTER7" maxlength="250"/>							
								</div>
							</div>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispBILL_DOC_FOOTER7"/>
							<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER8" value="1">
							<div>
								<div style="float:left;width:60px">
									<bean:message key="screen.m_gbs01.label.billingdocumentfooterdisplay8"/>&nbsp;&nbsp;&nbsp;
								</div>
								<div style="float:left;width:1px">
								:
								</div>					
								<div>
									&nbsp;&nbsp;&nbsp;<html:text size="80" property="tbxValueBILL_DOC_FOOTER8" maxlength="250"/>							
								</div>
							</div>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispBILL_DOC_FOOTER8"/>
							<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER9" value="1">
							<div>
								<div style="float:left;width:60px">
									<bean:message key="screen.m_gbs01.label.billingdocumentfooterdisplay9"/>&nbsp;&nbsp;&nbsp;
								</div>
								<div style="float:left;width:1px">
								:
								</div>					
								<div>
									&nbsp;&nbsp;&nbsp;<html:text size="80" property="tbxValueBILL_DOC_FOOTER9" maxlength="250"/>							
								</div>
							</div>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispBILL_DOC_FOOTER9"/>
							<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER10" value="1">
							<div>
								<div style="float:left;width:60px">
									<bean:message key="screen.m_gbs01.label.billingdocumentfooterdisplay10"/>&nbsp;&nbsp;&nbsp;
								</div>
								<div style="float:left;width:1px">
								:
								</div>					
								<div>
									&nbsp;&nbsp;&nbsp;<html:text size="80" property="tbxValueBILL_DOC_FOOTER10" maxlength="250"/>							
								</div>
							</div>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispBILL_DOC_FOOTER10"/>
							<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER11" value="1">
							<div>
								<div style="float:left;width:60px">
									<bean:message key="screen.m_gbs01.label.billingdocumentfooterdisplay11"/>&nbsp;&nbsp;&nbsp;
								</div>
								<div style="float:left;width:1px">
								:
								</div>					
								<div>
									&nbsp;&nbsp;&nbsp;<html:text size="80" property="tbxValueBILL_DOC_FOOTER11" maxlength="250"/>							
								</div>
							</div>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispBILL_DOC_FOOTER11"/>
							<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER12" value="1">
							<div>
								<div style="float:left;width:60px">
									<bean:message key="screen.m_gbs01.label.billingdocumentfooterdisplay12"/>&nbsp;&nbsp;&nbsp;
								</div>
								<div style="float:left;width:1px">
								:
								</div>					
								<div>
									&nbsp;&nbsp;&nbsp;<html:text size="80" property="tbxValueBILL_DOC_FOOTER12" maxlength="250"/>							
								</div>
							</div>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispBILL_DOC_FOOTER12"/>
							<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER13" value="1">
							<div>
								<div style="float:left;width:60px">
									<bean:message key="screen.m_gbs01.label.billingdocumentfooterdisplay13"/>&nbsp;&nbsp;&nbsp;
								</div>
								<div style="float:left;width:1px">
								:
								</div>					
								<div>
									&nbsp;&nbsp;&nbsp;<html:text size="80" property="tbxValueBILL_DOC_FOOTER13" maxlength="250"/>							
								</div>
							</div>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispBILL_DOC_FOOTER13"/>
							<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER14" value="1">
							<div>
								<div style="float:left;width:60px">
									<bean:message key="screen.m_gbs01.label.billingdocumentfooterdisplay14"/>&nbsp;&nbsp;&nbsp;
								</div>
								<div style="float:left;width:1px">
								:
								</div>					
								<div>
									&nbsp;&nbsp;&nbsp;<html:text size="80" property="tbxValueBILL_DOC_FOOTER14" maxlength="250"/>							
								</div>
							</div>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispBILL_DOC_FOOTER14"/>
							<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER15" value="1">
							<div>
								<div style="float:left;width:60px">
									<bean:message key="screen.m_gbs01.label.billingdocumentfooterdisplay15"/>&nbsp;&nbsp;&nbsp;
								</div>
								<div style="float:left;width:1px">
								:
								</div>					
								<div>
									&nbsp;&nbsp;&nbsp;<html:text size="80" property="tbxValueBILL_DOC_FOOTER15" maxlength="250"/>							
								</div>
							</div>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispBILL_DOC_FOOTER15"/>							
						</td>
					</tr>
					</logic:equal>
					
					<bean:define id="dispCashbookGroup" value="0"></bean:define>
					<logic:equal name="_m_gbsForm" property="dispCashBookAutoOffset" value="1">
						<bean:define id="dispCashbookGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="dispCashbookGroup" value="1">
					<tr>
						<td>
							<div style="width:220px; float: left">
								<b><bean:message key="screen.m_gbs01.label.cashbookautooffset"/></b>
							</div>
							<logic:equal name="_m_gbsForm" property="dispCashBookAutoOffset" value="1">
							<div>
								<div>
									<html:radio property="rdbCashBookAutoOffset" value="BAC"></html:radio>
									<bean:message key="screen.m_gbs01.label.cashbookautooffset.bac"/>
									&nbsp;&nbsp;&nbsp;
									<html:radio property="rdbCashBookAutoOffset" value="CST"></html:radio>
									<bean:message key="screen.m_gbs01.label.cashbookautooffset.cst"/>&nbsp;&nbsp;&nbsp;
								</div>
							</div>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispCashBookAutoOffset"/>
						</td>
					</tr>
					</logic:equal>
					
					<bean:define id="dispBatchRunGroup" value="0"></bean:define>
					<logic:equal name="_m_gbsForm" property="dispBatchRunDateEntry" value="1">
						<bean:define id="dispBatchRunGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="dispBatchRunGroup" value="1">
					<tr>
						<td>
							<div style="width:220px; float: left">
								<b><bean:message key="screen.m_gbs01.label.batchrundateentry"/></b>
							</div>
							<logic:equal name="_m_gbsForm" property="dispBatchRunDateEntry" value="1">
							<div>
								<div>
									<html:radio property="rdbBatchRunDateEntry" value="1"></html:radio>
									<bean:message key="screen.m_gbs01.label.batchrundateentry.enable"/>
									&nbsp;&nbsp;&nbsp;
									<html:radio property="rdbBatchRunDateEntry" value="0"></html:radio>
									<bean:message key="screen.m_gbs01.label.batchrundateentry.disable"/>&nbsp;&nbsp;&nbsp;
								</div>
							</div>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispBatchRunDateEntry"/>
						</td>
					</tr>
					</logic:equal>
				</table>
			</div>
		</div>
		<div id="tabFilePath" class="tabcontent">
			<div style="border:1px solid gray; width:100%; margin-bottom: 1em; padding: 10px">
				<table class="contentTable">
					<logic:equal name="_m_gbsForm" property="dispSHAREDFOLDER" value="1">
					<tr>
						<td nowrap="nowrap" height="20px">
						    <div style="width:220px; float: left;height: 100%;">
								<b><bean:message key="screen.m_gbs01.label.sharedfolder"/></b>
							</div>
							<div>
								<div style="width:320px; float: left">
									<bean:message key="screen.m_gbs01.label.sharedfolder.detail"/>
									<i style="font-size: 12px;"><bean:message key="screen.m_gbs01.label.filepath.comment1"/></i>&nbsp;&nbsp;&nbsp;
								</div>						
								<div style="float:left;width:1px">
								:
								</div>
								<div>
									&nbsp;&nbsp;&nbsp;<html:text size="50" property="tbxValueSHAREDFOLDER" maxlength="250"></html:text>							
								</div>
							</div>
						</td>
					</tr>
					</logic:equal>
					<html:hidden name="_m_gbsForm" property="dispSHAREDFOLDER"/>
					<bean:define id="dispFileLocationGroup" value="0"></bean:define>
					<logic:equal name="_m_gbsForm" property="dispFILELOC1" value="1">
						<bean:define id="dispFileLocationGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="_m_gbsForm" property="dispBATCH_G_RPT_AR011" value="1">
						<bean:define id="dispFileLocationGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="_m_gbsForm" property="dispBATCH_E_EXP_F011" value="1">
						<bean:define id="dispFileLocationGroup" value="1"></bean:define>
					</logic:equal>
					<!-- add for ticket 572 -->
					<logic:equal name="_m_gbsForm" property="dispBATCH_G_CSB_P01" value="1">
                        <bean:define id="dispFileLocationGroup" value="1"></bean:define>
                    </logic:equal>
                    <!-- BATCH_E_EML_P01 -->
                    <logic:equal name="_m_gbsForm" property="dispBATCH_E_EML_P01" value="1">
                        <bean:define id="dispFileLocationGroup" value="1"></bean:define>
                    </logic:equal>
                    <!-- #238 - BATCH_R_REV_P01 wcbeh@20170403 -->
                    <logic:equal name="_m_gbsForm" property="dispBATCH_R_REV_P01" value="1">
                        <bean:define id="dispFileLocationGroup" value="1"></bean:define>
                    </logic:equal>
					<logic:equal name="dispFileLocationGroup" value="1">
					<tr>
						<td nowrap="nowrap" height="20px">
							<div style="width:220px; float: left;height: 100%;">
								<b><bean:message key="screen.m_gbs01.label.filelocation"/></b>
							</div>
									<logic:equal name="_m_gbsForm" property="dispFILELOC1" value="1">
									<div>
										<div style="width:320px; float: left">
											<bean:message key="screen.m_gbs01.label.filelocation.detail"/>&nbsp;&nbsp;&nbsp;
										</div>						
										<div style="float:left;width:1px">
										:
										</div>					
										<div>
											&nbsp;&nbsp;&nbsp;<html:text size="50" property="tbxValueFILELOC1" maxlength="250"></html:text>							
										</div>
									</div>
									</logic:equal>
									<html:hidden name="_m_gbsForm" property="dispFILELOC1"/>
							
									<logic:equal name="_m_gbsForm" property="dispBATCH_G_RPT_AR011" value="1">
										<div>
											<div style="width:320px; float: left">
											<bean:message key="screen.m_gbs01.label.filelocation.detail4"/>&nbsp;&nbsp;&nbsp;
											</div>
											<div style="float:left;width:1px">
											:
											</div>				
											<div>
												&nbsp;&nbsp;&nbsp;<html:text size="50" property="tbxValueBATCH_G_RPT_AR011" maxlength="250"></html:text>	
											</div>
										</div>
									</logic:equal>
									<html:hidden name="_m_gbsForm" property="dispBATCH_G_RPT_AR011"/>
									
									<logic:equal name="_m_gbsForm" property="dispBATCH_E_EXP_F011" value="1">
										<div>
											<div style="width:320px; float: left">
											<bean:message key="screen.m_gbs01.label.filelocation.detail5"/>&nbsp;&nbsp;&nbsp;
										</div>	
											<div style="float:left;width:1px">
											:
											</div>					
											<div>
												&nbsp;&nbsp;&nbsp;<html:text size="50" property="tbxValueBATCH_E_EXP_F011" maxlength="250"></html:text>	
											</div>
										</div>
									</logic:equal>
									<html:hidden name="_m_gbsForm" property="dispBATCH_E_EXP_F011"/>
									<!-- add for ticket 572 -->
									<logic:equal name="_m_gbsForm" property="dispBATCH_G_CSB_P01" value="1">
                                        <div>
                                            <div style="width:320px; float: left">
                                            <bean:message key="screen.m_gbs01.label.filelocation.detail6"/>&nbsp;&nbsp;&nbsp;
                                        </div>  
                                            <div style="float:left;width:1px">
                                            :
                                            </div>                  
                                            <div>
                                                &nbsp;&nbsp;&nbsp;<html:text size="50" property="tbxValueBATCH_G_CSB_P01" maxlength="250"></html:text> 
                                            </div>
                                        </div>
                                    </logic:equal>
                                    <html:hidden name="_m_gbsForm" property="dispBATCH_G_CSB_P01"/>
                                    <!-- BATCH_E_EML_P01 -->
                                    <logic:equal name="_m_gbsForm" property="dispBATCH_E_EML_P01" value="1">
                                        <div>
                                            <div style="width:320px; float: left">
                                            <bean:message key="screen.m_gbs01.label.filelocation.detail7"/>&nbsp;&nbsp;&nbsp;
                                        </div>  
                                            <div style="float:left;width:1px">
                                            :
                                            </div>                  
                                            <div>
                                                &nbsp;&nbsp;&nbsp;<html:text size="50" property="tbxValueBATCH_E_EML_P01" maxlength="250"></html:text> 
                                            </div>
                                        </div>
                                    </logic:equal>
                                    <html:hidden name="_m_gbsForm" property="dispBATCH_E_EML_P01"/>
                                    <!-- #238 - BATCH_R_REV_P01 wcbeh@20170403 -->
                                    <logic:equal name="_m_gbsForm" property="dispBATCH_R_REV_P01" value="1">
                                        <div>
                                            <div style="width:320px; float: left">
                                            <bean:message key="screen.m_gbs01.label.filelocation.detail8"/>&nbsp;&nbsp;&nbsp;
                                        </div>  
                                            <div style="float:left;width:1px">
                                            :
                                            </div>                  
                                            <div>
                                                &nbsp;&nbsp;&nbsp;<html:text size="50" property="tbxValueBATCH_R_REV_P01" maxlength="250"></html:text> 
                                            </div>
                                        </div>
                                    </logic:equal>
                                    <html:hidden name="_m_gbsForm" property="dispBATCH_R_REV_P01"/>
						</td>
					</tr>
					</logic:equal>
					
					<bean:define id="dispBatchImportGroup" value="0"></bean:define>
					<logic:equal name="_m_gbsForm" property="dispBATCH_G_GIR_P021" value="1">
						<bean:define id="dispBatchImportGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="_m_gbsForm" property="dispBATCH_G_SGP_P021" value="1">
						<bean:define id="dispBatchImportGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="_m_gbsForm" property="dispBATCH_G_IPS_P011" value="1">
						<bean:define id="dispBatchImportGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="_m_gbsForm" property="dispBATCH_G_CLC_P011" value="1">
						<bean:define id="dispBatchImportGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="dispBatchImportGroup" value="1">
					<tr>
						<td nowrap="nowrap" height="20px">
							<div style="width:220px; float: left;height: 100%;">
								<b><bean:message key="screen.m_gbs01.label.batchimport"/></b>&nbsp;&nbsp;
							</div>
							<div>
								<div>
									<logic:equal name="_m_gbsForm" property="dispBATCH_G_GIR_P021" value="1">
									<div>
										<div style="width:320px; float: left">
											<bean:message key="screen.m_gbs01.label.batchimport.detail1"/>&nbsp;&nbsp;&nbsp;
										</div>
										<div style="float:left;width:1px">
										:
										</div>					
										<div>
											&nbsp;&nbsp;&nbsp;<html:text size="50" property="tbxValueBATCH_G_GIR_P021" maxlength="250"></html:text><br/>
										</div>
									</div>
									</logic:equal>
									<html:hidden name="_m_gbsForm" property="dispBATCH_G_GIR_P021"/>
									<logic:equal name="_m_gbsForm" property="dispBATCH_G_SGP_P021" value="1">
									<div>
										<div style="width:320px; float: left">
											<bean:message key="screen.m_gbs01.label.batchimport.detail2"/>&nbsp;&nbsp;&nbsp;
										</div>
										<div style="float:left;width:1px">
										:
										</div>					
										<div>
											&nbsp;&nbsp;&nbsp;<html:text size="50" property="tbxValueBATCH_G_SGP_P021" maxlength="250"></html:text>	
										</div>
									</div>
									</logic:equal>
									<html:hidden name="_m_gbsForm" property="dispBATCH_G_SGP_P021"/>
									<logic:equal name="_m_gbsForm" property="dispBATCH_G_IPS_P011" value="1">
									<div>
										<div style="width:320px; float: left">
											<bean:message key="screen.m_gbs01.label.batchimport.detail3"/>&nbsp;&nbsp;&nbsp;
										</div>
										<div style="float:left;width:1px">
										:
										</div>					
										<div>
											&nbsp;&nbsp;&nbsp;<html:text size="50" property="tbxValueBATCH_G_IPS_P011" maxlength="250"></html:text>	
										</div>
									</div>
									</logic:equal>
									<html:hidden name="_m_gbsForm" property="dispBATCH_G_IPS_P011"/>
									<logic:equal name="_m_gbsForm" property="dispBATCH_G_CLC_P011" value="1">
									<div>
										<div style="width:320px; float: left">
											<bean:message key="screen.m_gbs01.label.batchimport.detail4"/>&nbsp;&nbsp;&nbsp;
										</div>
										<div style="float:left;width:1px">
										:
										</div>					
										<div>
											&nbsp;&nbsp;&nbsp;<html:text size="50" property="tbxValueBATCH_G_CLC_P011" maxlength="250"></html:text>	
										</div>
									</div>
									</logic:equal>
									<html:hidden name="_m_gbsForm" property="dispBATCH_G_CLC_P011"/>
								</div>
							</div>
						</td>
					</tr>
					</logic:equal>
					
					<bean:define id="dispBatchExportGroup" value="0"></bean:define>
					<logic:equal name="_m_gbsForm" property="dispBATCH_G_CIT_P011" value="1">
						<bean:define id="dispBatchExportGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="_m_gbsForm" property="dispBATCH_G_GIR_P011" value="1">
						<bean:define id="dispBatchExportGroup" value="1"></bean:define>
					</logic:equal>
					<logic:equal name="_m_gbsForm" property="dispBATCH_G_SGP_P011" value="1">
						<bean:define id="dispBatchExportGroup" value="1"></bean:define>
					</logic:equal>					
					<logic:equal name="dispBatchExportGroup" value="1">
					<tr>
						<td nowrap="nowrap" height="20px">
							<div style="width:220px; float: left;height: 100%;">
								<b><bean:message key="screen.m_gbs01.label.batchexport"/></b>&nbsp;
								&nbsp;
							</div>
							<div>
								<div>
									<logic:equal name="_m_gbsForm" property="dispBATCH_G_CIT_P011" value="1">
									<div>
										<div style="width:320px; float: left">
											<bean:message key="screen.m_gbs01.label.batchexport.detail1"/>&nbsp;&nbsp;&nbsp;
										</div>
										<div style="float:left;width:1px">
										:
										</div>					
										<div>
											&nbsp;&nbsp;&nbsp;<html:text size="50" property="tbxValueBATCH_G_CIT_P011" maxlength="250"></html:text><br/>
										</div>
									</div>
									</logic:equal>
									<html:hidden name="_m_gbsForm" property="dispBATCH_G_CIT_P011"/>
									<logic:equal name="_m_gbsForm" property="dispBATCH_G_GIR_P011" value="1">
									<div>
										<div style="width:320px; float: left">
											<bean:message key="screen.m_gbs01.label.batchexport.detail2"/>&nbsp;&nbsp;&nbsp;
										</div>
										<div style="float:left;width:1px">
										:
										</div>					
										<div>
											&nbsp;&nbsp;&nbsp;<html:text size="50" property="tbxValueBATCH_G_GIR_P011" maxlength="250"></html:text>	
										</div>
									</div>
									</logic:equal>
									<html:hidden name="_m_gbsForm" property="dispBATCH_G_GIR_P011"/>
									<logic:equal name="_m_gbsForm" property="dispBATCH_G_SGP_P011" value="1">
									<div>
										<div style="width:320px; float: left">
											<bean:message key="screen.m_gbs01.label.batchexport.detail3"/>&nbsp;&nbsp;&nbsp;
										</div>
										<div style="float:left;width:1px">
										:
										</div>					
										<div>
											&nbsp;&nbsp;&nbsp;<html:text size="50" property="tbxValueBATCH_G_SGP_P011" maxlength="250"></html:text>	
										</div>
									</div>
									</logic:equal>
									<html:hidden name="_m_gbsForm" property="dispBATCH_G_SGP_P011"/>									
								</div>
							</div>
						</td>
					</tr>
					</logic:equal>
				</table>
				<br/>
				<bean:message key="screen.m_gbs01.label.filepath.comment2"/>
			</div>
		</div>
	<p>
	<input type="submit" name="btnSave" value="Save" onclick="return onSaveClick();"/>
	</p>
	<input type="hidden" id="ERR4SC107" value="<bean:message key="errors.ERR1SC107" arg0="{0}" arg1="{1}"/>"/>
	<!-- MESSAGES and ERRORS -->
	<div class="error" id="error_area">
	<logic:iterate name="_m_gbsForm" property="errorMessages" id="message" indexId="index">
		<bean:message key="${message}"/>	
	</logic:iterate>
	</div>
	<div class="success" id="success_area">
	<logic:iterate name="_m_gbsForm" property="successMessages" id="message" indexId="index">	
		<bean:message key="${message}"/>
	</logic:iterate>
	</div>
	<!-- MESSAGES and ERRORS -->
	<div class="error" id="error_area">
	<ts:errors/>
	</div>
	<div class="success" id="success_area">
	</div>	
</ts:form>
</body>
</html:html>