<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="/WEB-INF/bs" prefix="bs" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<html>
<head>
   	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
	<link href="${pageContext.request.contextPath}/B_CPM/css/b_cpm_s07.css" rel="stylesheet" type="text/css" />
	<title></title>
	<script>
	function changewidth(){
		var popupWidth = window.screen.width*80/100;
		popupWidth = popupWidth - 90;
	    document.documentElement.childNodes[1].innerHTML="<div style='width:"+popupWidth+"px'>"+document.documentElement.childNodes[1].innerHTML+"</div>";
		//window.moveTo((window.screen.width-popupWidth)/2, 0);
	}
	function exit(url){
		window.opener.reloadPage(url);
		window.close();
	}
	function closeEvt(url) {
		var loadPerentPgeFlg = document.getElementById("loadPerentPgeFlg").value;
		if (loadPerentPgeFlg!="1") {
			window.opener.reloadPage(url);
		}
	}
	function changeLoadPerentPgeFlg() {
		document.getElementById("loadPerentPgeFlg").value = "1";
	}
	</script>
</head>
<body id="b" onload="changewidth();" onunload="closeEvt('${pageContext.request.contextPath}/B_CPM/B_CPM_S02ViewBL.do?customerPlan.idCustPlan=${_B_CPM_S07Form.map.idCustPlan}')">
<ts:form action="/B_CPM_S07DSP" >
	<%-- plan status --%>
	<t:defineCodeList id="LIST_PLANSTATUS" />
	<t:defineCodeList id="LIST_BILLINGSTATUS" />
	<html:hidden  name="_B_CPM_S07Form" property="idSubInfo"/>
	<html:hidden name="_B_CPM_S07Form" property="idCustPlan"/>
	<input type="hidden" id="loadPerentPgeFlg"/>
	<table class="subHeader">
		<tr>
			<td><bean:message key="screen.b_cpm.s07.title"/></td>
		</tr>
	</table>
	<br/>
	<table cellpadding="0" cellspacing="0" width="100%" >
		<colgroup>
			<col width="5%">
			<col width="95%">
		</colgroup>
		<tr bgcolor="#e7efff">
			<td>&nbsp;</td>
			<td>
				<table cellpadding="0" cellspacing="0" width="100%" >
					<colgroup>
						<col width="53%">
						<col width="11%">
						<col width="9%">
						<col width="9%">
						<col width="9%">
						<col width="9%">
					</colgroup>
					<tr>
						<td>
							<bean:message key="screen.b_cpm.s07.subscriptionId"/>&nbsp;:&nbsp;<bean:write name="_B_CPM_S07Form" property="idSubInfo"/>
						</td>
			<td align="center"><bean:message key="screen.b_cpm.s07.mailaccount" /></td>
			<td align="center"><bean:message key="screen.b_cpm.s07.mailBox" /></td>
			<td align="center"><bean:message key="screen.b_cpm.s07.virusScan" /></td>
			<td align="center"><bean:message key="screen.b_cpm.s07.antiSpam" /></td>
			<td align="center"><bean:message key="screen.b_cpm.s07.junkMgmt" /></td>
		</tr>
				</table>
			</td>
		</tr>
		<tr >
			<td style="border-bottom: 1px solid #e7efff;border-left: 1px solid #e7efff;">&nbsp;</td>
			<td style="border-bottom: 1px solid #e7efff;border-right: 1px solid #e7efff;">
				<table cellpadding="0" cellspacing="0" width="100%" >
					<colgroup>
						<col width="53%">
						<col width="11%">
						<col width="9%">
						<col width="9%">
						<col width="9%">
						<col width="9%">
					</colgroup>
					<tr>
						<td align="right"><bean:message key="screen.b_cpm.s07.total" /></td>
						<td align="center"><bean:write name="_B_CPM_S07Form" property="MAILACC" /></td>
						<td align="center"><bean:write name="_B_CPM_S07Form" property="MAILBOX_QTY" /></td>
						<td align="center"><bean:write name="_B_CPM_S07Form" property="VIRUS_SCAN" /></td>
						<td align="center"><bean:write name="_B_CPM_S07Form" property="ANTI_SPAM" /></td>
						<td align="center"><bean:write name="_B_CPM_S07Form" property="JUNK_MGT" /></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr bgcolor="#b3ffb3">
			<td style="border-top: 3px solid white">&nbsp;</td>
			<td style="border-top: 3px solid white">
				<table cellpadding="0" cellspacing="0" width="100%" >
					<colgroup>
						<col width="53%">
						<col width="11%">
						<col width="9%">
						<col width="9%">
						<col width="9%">
						<col width="9%">
					</colgroup>
					<tr>
						<td align="right"><bean:message key="screen.b_cpm.s07.autoUpdateQty" /></td>
						<td align="center"><html:checkbox name="_B_CPM_S07Form" property="AUTO_MAIL_ACC" value="1" disabled="true"/></td>
						<td align="center"><html:checkbox name="_B_CPM_S07Form" property="AUTO_MAILBOX_QTY" value="1" disabled="true"/></td>
						<td align="center"><html:checkbox name="_B_CPM_S07Form" property="AUTO_VIRUS_SCAN" value="1" disabled="true"/></td>
						<td align="center"><html:checkbox name="_B_CPM_S07Form" property="AUTO_ANTI_SPAM" value="1" disabled="true"/></td>
						<td align="center"><html:checkbox name="_B_CPM_S07Form" property="AUTO_JUNK_MGT" value="1" disabled="true"/></td>
					</tr>
				</table>
			</td>
		</tr>	
		<tr bgcolor="#b3ffb3" >
			<td style="border-top: 3px solid white;border-bottom: 3px solid white">&nbsp;</td>
			<td style="border-top: 3px solid white;border-bottom: 3px solid white">
				<table cellpadding="0" cellspacing="0" width="100%" >
					<colgroup>
						<col width="53%">
						<col width="11%">
						<col width="9%">
						<col width="9%">
						<col width="9%">
						<col width="9%">
					</colgroup>
					<tr>
						<td align="right"><bean:message key="screen.b_cpm.s07.baseQty" /></td>
						<td align="center"><bean:write name="_B_CPM_S07Form" property="OPT_BASE_QTY" /></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr bgcolor="lightgray" >
			<th class="header" style="text-align: left;border-top: 2px solid #CFCFCF;border-bottom: 2px solid #CFCFCF;border-left: 2px solid #CFCFCF" valign="top"><bean:message key="screen.b_cpm.s07.no"/></th>
			<th>
				<table cellpadding="0" cellspacing="0" width="100%" >
					<colgroup>
						<col width="28%">
						<col width="13%">
						<col width="12%">
						<col width="11%">
						<col width="9%">
						<col width="9%">
						<col width="9%">
						<col width="9%">
					</colgroup>
					<tr>
						<th class="header" style="text-align: left;padding-left:5px;border-top: 2px solid #CFCFCF;border-bottom: 2px solid #CFCFCF;" valign="top">
							<bean:message key="screen.b_cpm.s07.billingdesc"/><br/>
							<bean:message key="screen.b_cpm.s07.itemdesc"/>
						</th>
						<th class="header" style="text-align: left;padding-left:5px;border-top: 2px solid #CFCFCF;border-bottom: 2px solid #CFCFCF;" valign="top">
							<bean:message key="screen.b_cpm.s07.serviceststus"/>
						</th>
						<th class="header" style="text-align: left;padding-left:5px;border-top: 2px solid #CFCFCF;border-bottom: 2px solid #CFCFCF;" nowrap="nowrap" valign="top"><bean:message key="screen.b_cpm.s07.billingststus"/></th>
			<th class="header" style="text-align: center;padding-left:5px;border-top: 2px solid #CFCFCF;border-bottom: 2px solid #CFCFCF;" valign="top"><bean:message key="screen.b_cpm.s07.option"/></th>
			<th class="header" style="text-align: left;padding-left:5px;border-top: 2px solid #CFCFCF;border-bottom: 2px solid #CFCFCF;" >&nbsp;</th>
			<th class="header" style="text-align: left;padding-left:5px;border-top: 2px solid #CFCFCF;border-bottom: 2px solid #CFCFCF;" >&nbsp;</th>
			<th class="header" style="text-align: left;padding-left:5px;border-top: 2px solid #CFCFCF;border-bottom: 2px solid #CFCFCF;" >&nbsp;</th>
			<th class="header" style="text-align: left;padding-left:5px;border-top: 2px solid #CFCFCF;border-bottom: 2px solid #CFCFCF;border-right: 2px solid #CFCFCF;" >&nbsp;</th>
		</tr>
				</table>
			</th>
		</tr>
		<logic:present name="_B_CPM_S07Form" property="serverList">
			<logic:iterate id="service" name="_B_CPM_S07Form" property="serverList" indexId="index">
		<tr bgcolor="#F8F8F8">
					<td valign="top" style="border-left: 1px solid #CFCFCF;">
						${index+1}
					</td>
					<td style="border-right: 1px solid #CFCFCF;">
						<table cellpadding="0" cellspacing="0" width="100%">
							<colgroup>
								<col width="28%">
								<col width="13%">
								<col width="59%">
							</colgroup>
							<tr>
								<td style="word-break:break-all;">
									<bean:write name="service" property="PLAN_NAME"/>
								</td>
								<td>
									<t:writeCodeValue codeList="LIST_PLANSTATUS" key="${service.SERVICES_STATUS}"/>
								</td>
								<td>
									<t:writeCodeValue codeList="LIST_BILLINGSTATUS" key="${service.BILLING_STATUS}"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr bgcolor="#F8F8F8">
					<td valign="top" style="border-bottom: 1px solid #CFCFCF;border-left: 1px solid #CFCFCF;">&nbsp;</td>
					<td style="border-bottom: 1px solid #CFCFCF;border-right: 1px solid #CFCFCF;">
						<logic:present name="service" property="subPlanList">
							<bean:size id="subPlanLen" name="service" property="subPlanList"/>
							<logic:iterate id="subPlan" name="service" property="subPlanList" indexId="index">
								<table cellpadding="0" cellspacing="0" width="100%">
									<colgroup>
										<col width="53%">
										<col width="11%">
										<col width="9%">
										<col width="9%">
										<col width="9%">
										<col width="9%">
									</colgroup>
									<c:if test="${index != (subPlanLen - 1)}">
										<tr>
											<td colspan="3" style="border-left: 1px solid #CFCFCF;border-top: 1px solid #CFCFCF;word-break:break-all;">
												<bean:write name="subPlan" property="ITEM_GRP_NAME"/>
											</td>
											<td style="border-top: 1px solid #CFCFCF;" align="center"><html:radio name="_B_CPM_S07Form" property="OPT_ADDT_OPTION" value="${subPlan.ID_CUST_PLAN_LINK}" disabled="true"/></td>
											<td style="border-top: 1px solid #CFCFCF;" align="center"><html:radio name="_B_CPM_S07Form" property="OPT_MAILBOX_QTY" value="${subPlan.ID_CUST_PLAN_LINK}" disabled="true"/></td>
											<td style="border-top: 1px solid #CFCFCF;" align="center"><html:radio name="_B_CPM_S07Form" property="OPT_VIRUS_SCAN" value="${subPlan.ID_CUST_PLAN_LINK}" disabled="true"/></td>
											<td style="border-top: 1px solid #CFCFCF;" align="center"><html:radio name="_B_CPM_S07Form" property="OPT_ANTI_SPAM" value="${subPlan.ID_CUST_PLAN_LINK}" disabled="true"/></td>
											<td style="border-right: 1px solid #CFCFCF;border-top: 1px solid #CFCFCF;" align="center"><html:radio name="_B_CPM_S07Form" property="OPT_JUNK_MGT" value="${subPlan.ID_CUST_PLAN_LINK}" disabled="true"/></td>
		</tr>
									</c:if>
									<c:if test="${index == (subPlanLen - 1)}">
										<tr>
											<td colspan="3" style="border-left: 1px solid #CFCFCF;border-top: 1px solid #CFCFCF;border-bottom: 1px solid #CFCFCF;word-break:break-all;">
												<bean:write name="subPlan" property="ITEM_GRP_NAME"/>
											</td>
											<td style="border-top: 1px solid #CFCFCF;border-bottom: 1px solid #CFCFCF;" align="center"><html:radio name="_B_CPM_S07Form" property="OPT_ADDT_OPTION" value="${subPlan.ID_CUST_PLAN_LINK}" disabled="true"/></td>
											<td style="border-top: 1px solid #CFCFCF;border-bottom: 1px solid #CFCFCF;" align="center"><html:radio name="_B_CPM_S07Form" property="OPT_MAILBOX_QTY" value="${subPlan.ID_CUST_PLAN_LINK}" disabled="true"/></td>
											<td style="border-top: 1px solid #CFCFCF;border-bottom: 1px solid #CFCFCF;" align="center"><html:radio name="_B_CPM_S07Form" property="OPT_VIRUS_SCAN" value="${subPlan.ID_CUST_PLAN_LINK}" disabled="true"/></td>
											<td style="border-top: 1px solid #CFCFCF;border-bottom: 1px solid #CFCFCF;" align="center"><html:radio name="_B_CPM_S07Form" property="OPT_ANTI_SPAM" value="${subPlan.ID_CUST_PLAN_LINK}" disabled="true"/></td>
											<td style="border-right: 1px solid #CFCFCF;border-top: 1px solid #CFCFCF;border-bottom: 1px solid #CFCFCF;" align="center"><html:radio name="_B_CPM_S07Form" property="OPT_JUNK_MGT" value="${subPlan.ID_CUST_PLAN_LINK}" disabled="true"/></td>
										</tr>
									</c:if>
								</table>
		</logic:iterate>
		</logic:present>
					</td>
		</tr>
			</logic:iterate>
		</logic:present>
	</table>
	<table class="buttonGroup" cellpadding="0" cellspacing="0">
		<tr>
			<td><logic:equal name="_B_CPM_S07Form" property="accessType" value="2">
					<ts:submit property="forward_edit" style="width:70px" onclick="changeLoadPerentPgeFlg()"><bean:message key="screen.b_cpm.s07.button.edit"/></ts:submit>&nbsp;&nbsp;&nbsp;
				</logic:equal>
				<button onclick="exit('${pageContext.request.contextPath}/B_CPM/B_CPM_S02ViewBL.do?customerPlan.idCustPlan=${_B_CPM_S07Form.map.idCustPlan}')" style="width:70px">
				<bean:message key="screen.b_cpm.s07.button.exit"/></button>
			</td>
		</tr>
	</table>
	<div class="message">
		<ts:messages id="message" message="true">
			<bean:write name="message"/>
		</ts:messages>
	</div>
	<div class="error">
		<html:messages id="message">
			<bean:write name="message"/><br/>
		</html:messages>
	</div>
</ts:form>
</body>
</html>