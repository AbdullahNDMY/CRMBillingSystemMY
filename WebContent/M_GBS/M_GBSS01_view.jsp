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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheet/tabcontent.css"/>
<style type="text/css"> 
table tr td { 
vertical-align:top;
}
</style> 

</head>
<body id="m" onload="initMain();initPage();">
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
									&nbsp;&nbsp;&nbsp;<bean:write name="_m_gbsForm" property="tbxValueDASHBD1"></bean:write>							
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
						<td >
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
									&nbsp;&nbsp;&nbsp;<bean:write name="_m_gbsForm" property="tbxValueUSRMNT1"></bean:write>							
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
						<td >
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
									&nbsp;&nbsp;&nbsp;<bean:write name="_m_gbsForm" property="tbxValuePWDCH1"></bean:write>							
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
											&nbsp;&nbsp;&nbsp;<bean:write name="_m_gbsForm" property="tbxValueLOGINAT1"></bean:write><br/>
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
											&nbsp;&nbsp;&nbsp;<bean:write name="_m_gbsForm" property="tbxValueLOGINAT2"></bean:write>	
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
									&nbsp;&nbsp;&nbsp;<bean:write name="_m_gbsForm" property="tbxValueRESULTROW1" ></bean:write>								
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
									&nbsp;&nbsp;&nbsp;<bean:write name="_m_gbsForm" property="tbxValueFILESIZEUPLOAD1" />							
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
									&nbsp;&nbsp;&nbsp;<bean:write name="_m_gbsForm" property="tbxValueSERVICEROW1"/>							
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
									&nbsp;&nbsp;&nbsp;<bean:write name="_m_gbsForm" property="tbxValueDESC_LENGTH1"/>							
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
					    <td>
					    <div style="width:220px; float: left;height: 100%;">
							<b><bean:message key="screen.m_gbs01.label.batchmessage"/></b>
					    </div>
						<div style="float: left;height: 100%;">
						    <table width="100%">
						    <colgroup>
						    <col width="160px"/>
						    <col width="1px"/>
						    <col width="560px"/>
						    </colgroup>
						    <logic:equal name="_m_gbsForm" property="dispBATCH_MSG1" value="1">
						    <tr>
						    <td style="float:left;width:160px" valign="top">
								<bean:message key="screen.m_gbs01.label.batchmessage.detail"/>&nbsp;&nbsp;&nbsp;
						    </td>
						    <td style="float:left;width:1px" valign="top">
								:
						    </td>
						    <td valign="top" style="word-wrap: break-word;white-space : normal">
								<bean:write name="_m_gbsForm" property="tbxValueBATCH_MSG1" />							
						    </td>
						    </tr>
						    </logic:equal>
						     <logic:equal name="_m_gbsForm" property="dispBATCH_MSG2" value="1">
								<tr>
								<td valign="top">
									<bean:message key="screen.m_gbs01.label.batchmessage.detail2"/>&nbsp;&nbsp;&nbsp;
								</td>
								<td valign="top">	
									:
								</td>
								<td valign="top" style="word-wrap: break-word;white-space : normal">
								     <bean:write name="_m_gbsForm" property="tbxValueBATCH_MSG2" />					
								</td>
								</tr>
							</logic:equal>
							<logic:equal name="_m_gbsForm" property="dispBATCH_MSG3" value="1">
								<tr>
								<td valign="top">
									<bean:message key="screen.m_gbs01.label.batchmessage.detail3"/>&nbsp;&nbsp;&nbsp;
								</td>
								<td valign="top">	
									:
							     </td>
							     <td valign="top" style="word-wrap: break-word;white-space : normal">				
									<bean:write name="_m_gbsForm" property="tbxValueBATCH_MSG3" />							
								</td>
								</tr>
							</logic:equal>
							</table>
						</div>
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
									&nbsp;&nbsp;&nbsp;<bean:write name="_m_gbsForm" property="tbxValueBIL_DEBTER_ACC1" ></bean:write>							
								</div>
							</div>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispBIL_DEBTER_ACC1"/>
						</td>
					</tr>
					</logic:equal>
					
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
									&nbsp;&nbsp;&nbsp;<bean:write name="_m_gbsForm" property="tbxValueGST1" ></bean:write>							
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
									&nbsp;&nbsp;&nbsp;<bean:write name="_m_gbsForm" property="tbxValueINVOICE_DUE_PERIOD1" ></bean:write>							
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
									&nbsp;&nbsp;&nbsp;<bean:write name="_m_gbsForm" property="tbxValueNOPRINTDOC1"></bean:write>								
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
						<td>							
							<div style="width:220px; float: left;height: 100%;">
								<div style="width:190px;"><b><bean:message key="screen.m_gbs01.label.billingdocumentfooterdisplay1header"/></b></div>								
							</div>
							<div>
							<table>
							<colgroup>
							 <col width="60px"/>
							 <col width="1px"/>
							 <col width="650px"/>
							</colgroup>
							<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER1" value="1">
							<tr>
							    <td style="float:left;width:60px" valign="top">
									<bean:message key="screen.m_gbs01.label.billingdocumentfooterdisplay1"/>
								</td>
								<td style="float:left;width:1px" valign="top">
								:
								</td>					
								<td style="word-wrap: break-word;white-space : normal;" valign="top" >
									<bean:write name="_m_gbsForm" property="tbxValueBILL_DOC_FOOTER1" />							
								</td>
							</tr>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispBILL_DOC_FOOTER1"/>
							<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER2" value="1">
							<tr>
								<td style="float:left;width:60px" valign="top">
									<bean:message key="screen.m_gbs01.label.billingdocumentfooterdisplay2"/>
								</td>
								<td style="float:left;width:1px" valign="top">
								:
								</td>					
								<td style="word-wrap: break-word;white-space : normal;" valign="top" >
									<bean:write name="_m_gbsForm" property="tbxValueBILL_DOC_FOOTER2" />							
								</td>
							</tr>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispBILL_DOC_FOOTER2"/>
							<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER3" value="1">
							<tr>
								<td style="float:left;width:60px" valign="top">
									<bean:message key="screen.m_gbs01.label.billingdocumentfooterdisplay3"/>
								</td>
								<td style="float:left;width:1px" valign="top">
								:
								</td>					
								<td style="word-wrap: break-word;white-space : normal" valign="top">
									<bean:write name="_m_gbsForm" property="tbxValueBILL_DOC_FOOTER3" />							
								</td>
							</tr>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispBILL_DOC_FOOTER3"/>
							<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER4" value="1">
							<tr>
								<td style="float:left;width:60px" valign="top">
									<bean:message key="screen.m_gbs01.label.billingdocumentfooterdisplay4"/>
								</td>
								<td style="float:left;width:1px" valign="top">
								:
								</td>					
								<td style="word-wrap: break-word;white-space : normal" valign="top">
									<bean:write name="_m_gbsForm" property="tbxValueBILL_DOC_FOOTER4" />							
								</td>
							</tr>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispBILL_DOC_FOOTER4"/>
							<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER5" value="1">
							<tr>
								<td style="float:left;width:60px" valign="top">
									<bean:message key="screen.m_gbs01.label.billingdocumentfooterdisplay5"/>
								</td>
								<td style="float:left;width:1px" valign="top">
								:
								</td>					
								<td style="word-wrap: break-word;white-space : normal" valign="top">
									<bean:write name="_m_gbsForm" property="tbxValueBILL_DOC_FOOTER5" />							
								</td>
							</tr>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispBILL_DOC_FOOTER5"/>
							<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER6" value="1">
							<tr>
								<td style="float:left;width:60px" valign="top">
									<bean:message key="screen.m_gbs01.label.billingdocumentfooterdisplay6"/>
								</td>
								<td style="float:left;width:1px" valign="top">
								:
								</td>					
								<td style="word-wrap: break-word;white-space : normal" valign="top">
									<bean:write name="_m_gbsForm" property="tbxValueBILL_DOC_FOOTER6" />							
								</td>
							</tr>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispBILL_DOC_FOOTER6"/>
							<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER7" value="1">
							<tr>
								<td style="float:left;width:60px" valign="top">
									<bean:message key="screen.m_gbs01.label.billingdocumentfooterdisplay7"/>
								</td>
								<td style="float:left;width:1px" valign="top">
								:
								</td>					
								<td style="word-wrap: break-word;white-space : normal" valign="top">
									<bean:write name="_m_gbsForm" property="tbxValueBILL_DOC_FOOTER7" />							
								</td>
							</tr>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispBILL_DOC_FOOTER7"/>
							<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER8" value="1">
							<tr>
								<td style="float:left;width:60px" valign="top">
									<bean:message key="screen.m_gbs01.label.billingdocumentfooterdisplay8"/>
								</td>
								<td style="float:left;width:1px" valign="top">
								:
								</td>					
								<td style="word-wrap: break-word;white-space : normal" valign="top">
									<bean:write name="_m_gbsForm" property="tbxValueBILL_DOC_FOOTER8" />							
								</td>
							</tr>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispBILL_DOC_FOOTER8"/>
							<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER9" value="1">
							<tr>
								<td style="float:left;width:60px" valign="top">
									<bean:message key="screen.m_gbs01.label.billingdocumentfooterdisplay9"/>
								</td>
								<td style="float:left;width:1px" valign="top">
								:
								</td>					
								<td style="word-wrap: break-word;white-space : normal" valign="top">
									<bean:write name="_m_gbsForm" property="tbxValueBILL_DOC_FOOTER9" />							
								</td>
							</tr>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispBILL_DOC_FOOTER9"/>
							<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER10" value="1">
							<tr>
								<td style="float:left;width:60px" valign="top">
									<bean:message key="screen.m_gbs01.label.billingdocumentfooterdisplay10"/>
								</td>
								<td style="float:left;width:1px" valign="top">
								:
								</td>					
								<td style="word-wrap: break-word;white-space : normal" valign="top">
									<bean:write name="_m_gbsForm" property="tbxValueBILL_DOC_FOOTER10" />							
								</td>
							</tr>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispBILL_DOC_FOOTER10"/>
							<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER11" value="1">
							<tr>
								<td style="float:left;width:60px" valign="top">
									<bean:message key="screen.m_gbs01.label.billingdocumentfooterdisplay11"/>
								</td>
								<td style="float:left;width:1px" valign="top">
								:
								</td>					
								<td style="word-wrap: break-word;white-space : normal" valign="top">
									<bean:write name="_m_gbsForm" property="tbxValueBILL_DOC_FOOTER11" />							
								</td>
							</tr>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispBILL_DOC_FOOTER11"/>
							<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER12" value="1">
							<tr>
								<td style="float:left;width:60px" valign="top">
									<bean:message key="screen.m_gbs01.label.billingdocumentfooterdisplay12"/>
								</td>
								<td style="float:left;width:1px" valign="top">
								:
								</td>					
								<td style="word-wrap: break-word;white-space : normal" valign="top">
									<bean:write name="_m_gbsForm" property="tbxValueBILL_DOC_FOOTER12" />							
								</td>
							</tr>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispBILL_DOC_FOOTER12"/>
							<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER13" value="1">
							<tr>
								<td style="float:left;width:60px" valign="top">
									<bean:message key="screen.m_gbs01.label.billingdocumentfooterdisplay13"/>
								</td>
								<td style="float:left;width:1px" valign="top">
								:
								</td>					
								<td style="word-wrap: break-word;white-space : normal" valign="top">
									<bean:write name="_m_gbsForm" property="tbxValueBILL_DOC_FOOTER13" />							
								</td>
							</tr>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispBILL_DOC_FOOTER13"/>
							<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER14" value="1">
							<tr>
								<td style="float:left;width:60px" valign="top">
									<bean:message key="screen.m_gbs01.label.billingdocumentfooterdisplay14"/>
								</td>
								<td style="float:left;width:1px" valign="top">
								:
								</td>					
								<td style="word-wrap: break-word;white-space : normal" valign="top">
									<bean:write name="_m_gbsForm" property="tbxValueBILL_DOC_FOOTER14" />							
								</td>
							</tr>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispBILL_DOC_FOOTER14"/>
							<logic:equal name="_m_gbsForm" property="dispBILL_DOC_FOOTER15" value="1">
							<tr>
								<td style="float:left;width:60px" valign="top">
									<bean:message key="screen.m_gbs01.label.billingdocumentfooterdisplay15"/>
								</td>
								<td style="float:left;width:1px" valign="top">
								:
								</td>					
								<td style="word-wrap: break-word;white-space : normal" valign="top">
									<bean:write name="_m_gbsForm" property="tbxValueBILL_DOC_FOOTER15" />							
								</td>
							</tr>
							</logic:equal>
							<html:hidden name="_m_gbsForm" property="dispBILL_DOC_FOOTER15"/>	
							</table>
							</div>
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
								<div style="color: gray">
									<span style="width:130px; float: left"><html:radio property="rdbCashBookAutoOffset" value="BAC" disabled="true"></html:radio>
									<bean:message key="screen.m_gbs01.label.cashbookautooffset.bac"/>
									</span>
									&nbsp;&nbsp;&nbsp;
									<span>
									<html:radio property="rdbCashBookAutoOffset" value="CST" disabled="true"></html:radio>
									<bean:message key="screen.m_gbs01.label.cashbookautooffset.cst"/>
									</span>
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
								<div style="color: gray">
									<span style="width:130px; float: left"><html:radio property="rdbBatchRunDateEntry" value="1" disabled="true"></html:radio>
									<bean:message key="screen.m_gbs01.label.batchrundateentry.enable"/>
									</span>
									&nbsp;&nbsp;&nbsp;
									<span>
									<html:radio property="rdbBatchRunDateEntry" value="0" disabled="true"></html:radio>
									<bean:message key="screen.m_gbs01.label.batchrundateentry.disable"/>
									</span>
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
				<table class="contentTable" cellspacing="0" style="border-top: solid 2px #CCCCCC; border-bottom: solid 2px #CCCCCC;border-left: solid 2px #CCCCCC; border-right: solid 2px #CCCCCC;">
					<logic:equal name="_m_gbsForm" property="dispSHAREDFOLDER" value="1">
					<tr>
						<td style="width:220px; height: 100%; border-bottom: solid 2px #CCCCCC;">
							<b><bean:message key="screen.m_gbs01.label.sharedfolder"/></b>
					    </td>
					   	<td style="padding-left:0px;width:741px; border-bottom: solid 2px #CCCCCC;">		
					    <table cellpadding="0" cellspacing="0" border="0" style="width;100%;height:100%;margin-left:0px">
						    <tr>
							    <td style="width:315px;">
									<bean:message key="screen.m_gbs01.label.sharedfolder.detail"/>
									<i style="font-size: 12px;"><bean:message key="screen.m_gbs01.label.filepath.comment1"/></i>
								</td>
								<td style="width:1px;padding-left:0px;" align="left">
								:
								</td>
								<td style="word-wrap: break-word;white-space : normal;width:420px">
									<bean:write name="_m_gbsForm" property="tbxValueSHAREDFOLDER" ></bean:write>
								</td>
							</tr>
					    </table>
						</td>
					</tr>
					</logic:equal>
					
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
						<td style="width:220px; height: 100%; border-right:  0px; border-bottom: solid 2px #CCCCCC;">
							<b><bean:message key="screen.m_gbs01.label.filelocation"/></b>
					    </td>
					    <td style="padding-left:0px;width:741px; border-bottom: solid 2px #CCCCCC;">		
							<table cellpadding="0" cellspacing="0" border="0" style="width;100%;height:100%;margin-left:0px;">
							<logic:equal name="_m_gbsForm" property="dispFILELOC1" value="1">
							<tr>
								<td style="width:320px;">
									<bean:message key="screen.m_gbs01.label.filelocation.detail"/>
								</td>						
								<td style="width:1px;padding-left:0px;" align="left">
								:
								</td>					
								<td style="word-wrap: break-word;white-space : normal;width:420px">
									<bean:write name="_m_gbsForm" property="tbxValueFILELOC1" ></bean:write>						
								</td>
							</tr>
							</logic:equal>
							<logic:equal name="_m_gbsForm" property="dispBATCH_G_RPT_AR011" value="1">
								<tr>
									<td style="width:320px; ">
									<bean:message key="screen.m_gbs01.label.filelocation.detail4"/>
									</td>
									<td style="width:1px;padding-left:0px;" align="left">
									:
									</td>				
									<td style="word-wrap: break-word;white-space : normal;width:420px">
										<bean:write name="_m_gbsForm" property="tbxValueBATCH_G_RPT_AR011" ></bean:write>
									</td>
								</tr>
							</logic:equal>
							<logic:equal name="_m_gbsForm" property="dispBATCH_E_EXP_F011" value="1">
								<tr>
									<td style="width:320px;">
									<bean:message key="screen.m_gbs01.label.filelocation.detail5"/>
								    </td>	
									<td style="width:1px;padding-left:0px;" align="left">
									:
									</td>			
									<td style="word-wrap: break-word;white-space : normal;width:420px">
										<bean:write name="_m_gbsForm" property="tbxValueBATCH_E_EXP_F011" ></bean:write>
									</td>
								</tr>
							</logic:equal>
							<logic:equal name="_m_gbsForm" property="dispBATCH_G_CSB_P01" value="1">
								<tr>
									<td style="width:320px;">
									<bean:message key="screen.m_gbs01.label.filelocation.detail7"/>
								    </td>	
									<td style="width:1px;padding-left:0px;" align="left">
									:
									</td>			
									<td style="word-wrap: break-word;white-space : normal;width:420px">
										<bean:write name="_m_gbsForm" property="tbxValueBATCH_G_CSB_P01" ></bean:write>
									</td>
								</tr>
							</logic:equal>
							<!-- BATCH_E_EML_P01 -->
							<logic:equal name="_m_gbsForm" property="dispBATCH_E_EML_P01" value="1">
								<tr>
									<td style="width:320px;">
									<bean:message key="screen.m_gbs01.label.filelocation.detail6"/>
								    </td>	
									<td style="width:1px;padding-left:0px;" align="left">
									:
									</td>			
									<td style="word-wrap: break-word;white-space : normal;width:420px">
										<bean:write name="_m_gbsForm" property="tbxValueBATCH_E_EML_P01" ></bean:write>
									</td>
								</tr>
							</logic:equal>
							<!-- #238 - BATCH_R_REV_P01 wcbeh@20170403 -->
							<logic:equal name="_m_gbsForm" property="dispBATCH_R_REV_P01" value="1">
								<tr>
									<td style="width:320px;">
									<bean:message key="screen.m_gbs01.label.filelocation.detail8"/>
								    </td>	
									<td style="width:1px;padding-left:0px;" align="left">
									:
									</td>			
									<td style="word-wrap: break-word;white-space : normal;width:420px">
										<bean:write name="_m_gbsForm" property="tbxValueBATCH_R_REV_P01" ></bean:write>
									</td>
								</tr>
							</logic:equal>
							</table>
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
						<td style="width:220px; height: 100%; border-bottom: solid 2px #CCCCCC;">
							<b><bean:message key="screen.m_gbs01.label.batchimport"/></b>&nbsp;&nbsp;
						</td>
						<td style="padding-left:0px;width:741px; border-bottom: solid 2px #CCCCCC;">		
							<table cellpadding="0" cellspacing="0" border="0" style="width;100%;height:100%;margin-left:0px">	
								<logic:equal name="_m_gbsForm" property="dispBATCH_G_GIR_P021" value="1">
								<tr>
									<td style="width:320px;">
										<bean:message key="screen.m_gbs01.label.batchimport.detail1"/>
									</td>
									<td style="width:1px;padding-left:0px;" align="left">
									:
									</td>					
									<td style="word-wrap: break-word;white-space : normal;width:420px">
										<bean:write name="_m_gbsForm" property="tbxValueBATCH_G_GIR_P021"></bean:write>
									</td>
								</tr>
								</logic:equal>
								
								<logic:equal name="_m_gbsForm" property="dispBATCH_G_SGP_P021" value="1">
								<tr>
									<td style="width:320px;">
										<bean:message key="screen.m_gbs01.label.batchimport.detail2"/>
									</td>
									<td style="width:1px;padding-left:0px;" align="left">
									:
									</td>					
									<td style="word-wrap: break-word;white-space : normal;width:420px">
										<bean:write name="_m_gbsForm" property="tbxValueBATCH_G_SGP_P021" ></bean:write>
									</td>
								</tr>
								</logic:equal>
								
								<logic:equal name="_m_gbsForm" property="dispBATCH_G_IPS_P011" value="1">
								<tr>
									<td style="width:320px;">
										<bean:message key="screen.m_gbs01.label.batchimport.detail3"/>
									</td>
									<td style="width:1px;padding-left:0px;" align="left">
									:
									</td>					
									<td style="word-wrap: break-word;white-space : normal;width:420px">
										<bean:write name="_m_gbsForm" property="tbxValueBATCH_G_IPS_P011" ></bean:write>
									</td>
								</tr>
								</logic:equal>
								
								<logic:equal name="_m_gbsForm" property="dispBATCH_G_CLC_P011" value="1">
								<tr>
									<td style="width:320px; ">
										<bean:message key="screen.m_gbs01.label.batchimport.detail4"/>
									</td>
									<td style="width:1px;padding-left:0px;" align="left">
									:
									</td>					
									<td style="word-wrap: break-word;white-space : normal;width:420px">
										<bean:write name="_m_gbsForm" property="tbxValueBATCH_G_CLC_P011" ></bean:write>
									</td>
								 </tr>
								 </logic:equal>
							</table>
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
						<td style="width:220px; height: 100%;">
							<b><bean:message key="screen.m_gbs01.label.batchexport"/></b>
						</td>
						<td style="padding-left:0px;width:741px">		
							<table cellpadding="0" cellspacing="0" border="0" style="width;100%;height:100%;margin-left:0px">	
								<logic:equal name="_m_gbsForm" property="dispBATCH_G_CIT_P011" value="1">
								<tr>
									<td style="width:320px; ">
										<bean:message key="screen.m_gbs01.label.batchexport.detail1"/>
									</td>
									<td style="width:1px;padding-left:0px;" align="left">
									:
									</td>					
									<td style="word-wrap: break-word;white-space : normal;width:420px">
										<bean:write name="_m_gbsForm" property="tbxValueBATCH_G_CIT_P011"></bean:write>
									</td>
								</tr>
								</logic:equal>
									
								<logic:equal name="_m_gbsForm" property="dispBATCH_G_GIR_P011" value="1">
								<tr>
									<td style="width:320px; ">
										<bean:message key="screen.m_gbs01.label.batchexport.detail2"/>
									</td>
									<td style="width:1px;padding-left:0px;" align="left">
									:
									</td>					
									<td style="word-wrap: break-word;white-space : normal;width:420px">
										<bean:write name="_m_gbsForm" property="tbxValueBATCH_G_GIR_P011" ></bean:write>
									</td>
								</tr>
								</logic:equal>
									
								<logic:equal name="_m_gbsForm" property="dispBATCH_G_SGP_P011" value="1">
								<tr>
									<td style="width:320px; ">
										<bean:message key="screen.m_gbs01.label.batchexport.detail3"/>
									</td>
									<td style="width:1px;padding-left:0px;" align="left">
									:
									</td>					
									<td style="word-wrap: break-word;white-space : normal;width:420px">
										<bean:write name="_m_gbsForm" property="tbxValueBATCH_G_SGP_P011"></bean:write>	
									</td>
								</tr>
								</logic:equal>

							</table>
						</td>
					</tr>
					</logic:equal>
				</table>
				<br/>
				<bean:message key="screen.m_gbs01.label.filepath.comment2"/>
			</div>
		</div>
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