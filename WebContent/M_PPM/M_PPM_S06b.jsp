<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>	
   	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/M_PPM/css/common.css"/>	
   	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/M_PPM/css/m_ppm_s02.css"/>			
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/M_PPM/js/m_ppm_s06b.js"></script>
	<script type="text/javascript">
		function changeRateType(obj){
			var thisRateType = $(obj);
			var rateMode = thisRateType.val();
			var path = $("#contextPath").val();
			var subPlans = $(".serviceInfo");
			var url = path+'/M_PPM/M_PPM_S02RateType2ChangeAjax.do?rateMode='+rateMode;
			$.ajax({
		        type: 'POST',
		        url: url,
		        success: function(result){
		        	thisRateType.closest(".serviceInfo").find(".cboRateType2").val(result);
		        }
		      });
		} 
	</script>
<title></title>
<style>
 	#serviceClone{
 		background-color:#F8F8F8;
 		width:100%
 	}
 	.serviceDetail td {
	border-top: 0px solid #CEE7FF;
	}
</style>
</head>
<body onload="initload()"> 
<t:defineCodeList id="LIST_CURRENCY" />
<t:defineCodeList id="LIST_CUSTOMERTYPE" />
<t:defineCodeList id="LIST_RATEMODE" />
<t:defineCodeList id="LIST_RATETYPE" />
<t:defineCodeList id="LIST_GST" />
<t:defineCodeList id="LIST_RATETYPE2" />
<table border="0" cellspacing="0" cellpadding="0" width="100%">
<tr><td>
<ts:form action="M_PPM_S02SavePlanBL">
 	<html:hidden property="allGstSameFlg" styleId="allGstSameFlg"/>
	<html:hidden property="allCategorySameFlg" styleId="allCategorySameFlg"/>
	<html:hidden property="isHaveCheckFlg" styleId="isHaveCheckFlg"/>
	<nested:nest property="plan">
	<nested:hidden property="idPlan" styleId="idPlan"/>
	<nested:hidden property="cboBillCurrenc"/>
	<nested:present property="cboBillCurrenc">
		<nested:define id="cboBillCurrenc" property="cboBillCurrenc"/>
	</nested:present>
	<nested:hidden property="modelFlg" styleId="modelFlg"/>
	<nested:hidden property="isSaveFlg" styleClass="isSaveFlg"/>
	<nested:hidden property="ppmOptionSvc" styleId="ppmOptionSvc"/>
	<nested:define id="ppmOptionSvcDef" property="ppmOptionSvc"/> 
	<nested:hidden property="cboRateType2Flg" name="_m_ppmFormS02"/>
<br/>
<table class="subHeader">
  <tr>
    <td><bean:message key="screen.m_ppms02.title"/></td>
  </tr>
</table>
<br/>
<div id="serviceClone">
<nested:iterate property="services">
	<fieldset id="planInfo">
		<legend style="color: #4876FF"><bean:message key="screen.m_ppms02.optionservice"/></legend>
		<nested:hidden property="inUsed" styleClass="inUsedHidden"/>
		<nested:hidden property="type" styleId="type"/>
		<nested:hidden property="idPlanGrp" styleClass="idPlanGrp"/>
		<div style="padding-left:27px;width:100%;">
		<table cellpadding="2" cellspacing="2" class="serviceHeader">
			<tr>
				<td><span id="typeName"><bean:message key="screen.m_ppms02.optionName"/></span>&nbsp;<span class="mandatory"><bean:message key="screen.m_ppms02.label_mandatory"/></span></td>
			    <td colspan="2">
			    	<bean:message key="screen.m_ppms02.colon"/>
			        <nested:text property="tbxServiceName" styleClass="tbxServiceName" maxlength="300" size="100"/>
			    </td>
			</tr>
			<tr>
				<td width="20%"><bean:message key="screen.m_ppms02.categoryAndService"/>&nbsp;<span class="mandatory"><bean:message key="screen.m_ppms02.label_mandatory"/></span></td>
			    <td width="20%">
			    	<input type="hidden" class="cboSvcLevel1_cached" value=""/>
			    	<bean:message key="screen.m_ppms02.colon"/>
			    	<nested:present property="cboSvcLevel1">
					 	<nested:define id="cboSvcLevel1" property="cboSvcLevel1"/>
					 </nested:present>
					 
					<nested:select property="cboSvcLevel1" styleClass="cboSvcLevel1" onchange="changeSvcLevel1(this)">
						<option value=""><bean:message key="screen.m_ppms02.selectone"/></option>
							<c:forEach items="${_m_ppmFormS02.map.cboCategoryList}" var="item">
								<c:choose>
								<c:when test="${cboSvcLevel1 == item.svcGrp}">
									<option value="${item.svcGrp}" selected="selected">${item.svcGrpName}</option>
								</c:when>
								<c:otherwise>
									<option value="${item.svcGrp}">${item.svcGrpName}</option>
								</c:otherwise>
								</c:choose>
							</c:forEach>
					</nested:select>
				</td>
				<td>
				<nested:present property="cboSvcLevel2List">
					 <nested:define id="cboSvcLevel2List" property="cboSvcLevel2List"/>
				</nested:present>
				<nested:present property="cboSvcLevel2">
					<nested:define id="cboSvcLevel2" property="cboSvcLevel2"/>
				</nested:present>
				 <div class="cboService">
					<nested:select property="cboSvcLevel2" styleClass="cboSvcLevel2" onchange="changeSvcLevel2(this)">
						<option value="" gstValue="0"><bean:message key="screen.m_ppms02.selectone"/></option>
							<c:forEach items="${cboSvcLevel2List}" var="item">
								<c:choose>
								<c:when test="${cboSvcLevel2 == item.idService}">
									<option value="${item.idService}" selected="selected">${item.svcDesc} - ${item.accCode}</option>
								</c:when>
								<c:otherwise>
									<option value="${item.idService}">${item.svcDesc} - ${item.accCode}</option>
								</c:otherwise>
								</c:choose>
							</c:forEach>
					</nested:select>
					<div class="cboPlan_PlanDetail" style="display: none;">
						<select name="plan.services[i].details[j].cboPlan" class="cboPlan" onchange="changeSvcLevel3(this)">
							<option value="" gstValue="0"><bean:message key="screen.m_ppms02.selectone" /></option>
							<c:forEach items="${cboPlanList}" var="item">
							<option value="${item.idService}" gstValue="${item.gst}">${item.svcDesc}</option>
							</c:forEach>
							</select> <br /> <select name="plan.services[i].details[j].cboPlanDetail" class="cboPlanDetail" onchange="changeSvcLevel4(this)">
							<option value="" gstValue="0"><bean:message key="screen.m_ppms02.selectone" /></option>
							<c:forEach items="${cboPlanDetailList}" var="item">
								<option value="${item.idService}" gstValue="${item.gst}">${item.svcDesc}</option>
							</c:forEach>
						</select>
					</div>
				</div> 
			   </td>
			</tr>
		</table>
		<table cellpadding="2" cellspacing="2" width="100%">
			<tr>
				<td valign="top">
					<table cellpadding="2" cellspacing="0" class="serviceDetail">
						<tr class="header">
							<td class="add"></td>
							<td>
								<bean:message key="screen.m_ppms02.plan_planDetail"/>
							</td>
							<td>
								<bean:message key="screen.m_ppms02.vendor"/>
							</td>								
						</tr>
						<!-- add service detail here -->
						<nested:present property="cboPlanList">
					 		<nested:define id="cboPlanList" property="cboPlanList"/>
						</nested:present>
						<nested:present property="cboPlanDetailList">
					 		<nested:define id="cboPlanDetailList" property="cboPlanDetailList"/>
						</nested:present>
						<nested:iterate property="details">
						<tr class="serviceDetailClone">
							<td class="remove"><%-- <img src="<%=request.getContextPath()%>/image/delete.gif" onclick="removeServiceDetail(this);"/> --%></td>
							<td class="planDetail">
								<input type="hidden" value="<nested:write property="cboPlan"/>" class="cboPlanCallBack"/>
								<input type="hidden" value="<nested:write property="cboPlanDetail"/>" class="cboPlanDetailCallBack"/>
								<nested:present property="cboPlan">
									<nested:define id="cboPlan" property="cboPlan"/>
								</nested:present> 
								<nested:select property="cboPlan" styleClass="cboPlan" onchange="changeSvcLevel3(this)" style="${typeDef=='O' && ppmOptionSvcDef != '1'?'display:none':''}">
									<option value="" gstValue="0"><bean:message key="screen.m_ppms02.selectone"/></option>
											<c:forEach items="${cboPlanList}" var="item">
												<%-- <option value="${item.idService}" gstValue="${item.gst}">${item.svcDesc}</option> --%>
												<c:choose>
												<c:when test="${cboPlan == item.idService}">
													<option value="${item.idService}" selected="selected">${item.svcDesc}</option>
												</c:when>
												<c:otherwise>
													<option value="${item.idService}">${item.svcDesc}</option>
												</c:otherwise>
												</c:choose>
											</c:forEach>
								</nested:select>
								<br/>
								<nested:present property="cboPlanDetail">
									<nested:define id="cboPlanDetail" property="cboPlanDetail"/>
								</nested:present> 
								<nested:select property="cboPlanDetail" styleClass="cboPlanDetail" onchange="changeSvcLevel4(this)" style="${typeDef=='O' && ppmOptionSvcDef != '1'?'display:none':''}">
									<option value="" gstValue="0"><bean:message key="screen.m_ppms02.selectone"/></option>
										<c:forEach items="${cboPlanDetailList}" var="item">
											<%-- <option value="${item.idService}" gstValue="${item.gst}">${item.svcDesc}</option> --%>
											<c:choose>
												<c:when test="${cboPlanDetail == item.idService}">
													<option value="${item.idService}" selected="selected">${item.svcDesc}</option>
												</c:when>
												<c:otherwise>
													<option value="${item.idService}">${item.svcDesc}</option>
												</c:otherwise>
												</c:choose>
										</c:forEach>
								</nested:select>
								<nested:hidden property="idPlanSvc" styleClass="idPlanSvc"/>
							</td>
							<td valign="top">
								<nested:present property="cboVendor">
						    	<nested:define id="cboVendor" property="cboVendor"/>
								<nested:select property="cboVendor" styleClass="cboVendor" style="${typeDef=='O' && ppmOptionSvcDef != '1'?'display:none':''}">
									<option value=""><bean:message key="screen.m_ppms02.selectone"/></option>
										<c:forEach items="${_m_ppmFormS02.map.cboVendorList}" var="item">
											<c:choose>
											<c:when test="${cboVendor == item.id}">
												<option value="${item.id}" selected="selected">${item.name}</option>
											</c:when>
											<c:otherwise>
												<option value="${item.id}">${item.name}</option>
											</c:otherwise>
											</c:choose>
											</c:forEach>
								</nested:select>
								</nested:present>
								<nested:notPresent property="cboVendor">
									<nested:select property="cboVendor" styleClass="cboVendor" style="${typeDef=='O' && ppmOptionSvcDef != '1'?'display:none':''}">
										<option value=""><bean:message key="screen.m_ppms02.selectone"/></option>
											<c:forEach items="${_m_ppmFormS02.map.cboVendorList}" var="item">
												<option value="${item.id}">${item.name}</option>
											</c:forEach>
									</nested:select>
								</nested:notPresent>
							</td>
						</tr>
						</nested:iterate>
					</table>
				</td>
				<td valign="top" width="1px;">
					<table cellpadding="0" cellspacing="0" class="serviceInfo">
						<tr>
							<td class="headerLeft">
								<div>
									<bean:message key="screen.m_ppms02.rate"/>&nbsp;
									<span class="mandatory"><bean:message key="screen.m_ppms02.label_mandatory"/></span>
									<br/>
									<bean:message key="screen.m_ppms02.type"/>
								</div>
							</td>
							<td class="headerCenter">
								<div>
									<bean:message key="screen.m_ppms02.rate"/>&nbsp;
									<span class="mandatory"><bean:message key="screen.m_ppms02.label_mandatory"/></span>
									<br/>
									<bean:message key="screen.m_ppms02.mode"/>
								</div>
							</td>
							<td class="headerRight">
								<div>
									<bean:message key="screen.m_ppms02.rate"/>&nbsp;
									<span class="mandatory"><bean:message key="screen.m_ppms02.label_mandatory"/></span>
									<br/>
									(<span class="serviceCurrency">${cboBillCurrenc}</span>)
								</div>
							</td>
						</tr>
						<tr>
							<td>
						    	<nested:select property="cboRateType" styleClass="cboRateType">
									 <html:optionsCollection name="LIST_RATETYPE" value="id" label="name"/>
								</nested:select>
							</td>
							<td>
								<nested:select property="cboRateMode" styleClass="cboRateMode" onchange="changeRateType(this)">
										<html:optionsCollection name="LIST_RATEMODE" value="id" label="name"/>
								</nested:select>
							</td>
							<td>
								<nested:text property="tbxRate" styleClass="tbxRate" size="16" maxlength="16"/>
							</td>
						</tr>
						<tr>
							<td>
								<c:if test="${_m_ppmFormS02.map.cboRateType2Flg eq '1'}">
							    	<nested:select property="cboRateType2" styleClass="cboRateType2">
										 <html:optionsCollection name="LIST_RATETYPE2" value="id" label="name"/>
									</nested:select>
								</c:if>
								<c:if test="${_m_ppmFormS02.map.cboRateType2Flg ne '1'}">
							    	<nested:select property="cboRateType2" styleClass="cboRateType2" styleId="cboRateType2" disabled="true">
										 <html:optionsCollection name="LIST_RATETYPE2" value="id" label="name"/>
									</nested:select>
									<nested:hidden property="cboRateType2"></nested:hidden>
								</c:if>
							</td>
							<td colspan="2" class="gst">
								<!-- #436: [B2-2][REQ003]NewTaxCode Start -->
								${_m_ppmFormS02.map.taxWord}
								<!-- #436: [B2-2][REQ003]NewTaxCode End --> 
								<nested:select property="tbxGST" styleClass="tbxGST">
				                    <html:options collection="LIST_GST" property="id" labelProperty="name"/>
				                 </nested:select>
							</td>
						</tr>			
					</table>
				</td>
			</tr>
		</table>
	</div>
	</fieldset>
	</nested:iterate>
	</div>
<br/>
<table class="buttonGroup" cellpadding="0" cellspacing="0">
  	<tr>
		<td>
			<input type="button" class="button" value="<bean:message key="screen.m_ppms02.save"/>" name="forward_save" onclick="clickSave();"/>
			<input type="button" class="button" value="<bean:message key="screen.m_ppms02.exit"/>" name="forward_exit" onclick="clickExit()" />			
		</td>
	</tr>
</table> 
<div style="color:#0046D5;padding-left:20px;padding-top:0px;">
	<html:messages id="message" message="true">
		<bean:write name="message"/>
	</html:messages>
</div>
<div style="color:red;padding-left:20px;padding-top:0px;" id="errorMsgDiv">
	<html:messages id="message">
		<bean:write name="message"/><br/>
	</html:messages>
</div>
</nested:nest>
</ts:form>
</td></tr>
</table>
<!-- BEGIN temporary message -->
<input type="hidden" id="contextPath" value="<%=request.getContextPath()%>"/>
<input type="hidden" id="svcChangedMsg" value='<bean:message key="info.ERR4SC004" arg0="replace"/>'/>
<input type="hidden" id="contextPath" value="<%=request.getContextPath()%>"/>
<input type="hidden" id="subplanLbl" value="<bean:message key="screen.m_ppms02.subplan"/>"/>
<input type="hidden" id="optionserviceLbl" value="<bean:message key="screen.m_ppms02.optionservice"/>"/>
<input type="hidden" id="subplan_removeNotification" value="<bean:message key="subPlan.info.ERR4SC003"/>"/>
<input type="hidden" id="optionservice_removeNotification" value="<bean:message key="optionService.info.ERR4SC003"/>"/>
<input type="hidden" id="svcChangedMsg" value='<bean:message key="info.ERR4SC004" arg0="replace"/>'/>
<input type="hidden" id="ERR4SC008" value="<bean:message key="info.ERR4SC008"/>"/>
<input type="hidden" id="subPlanName" value="<bean:message key="screen.m_ppms02.svcName"/>"/>
<input type="hidden" id="optionName" value="<bean:message key="screen.m_ppms02.optionName"/>"/>
<span style="display:none;" id="noSubPlanOrOptionServiceMsg"><bean:message key="screen.m_ppms02.noSubPlanOrOptionService"/></span>
<span style="display:none;" id="ERR4SC017_GST"><bean:message key="info.ERR4SC017" arg0="All Sub plan / Option's ${_m_ppmFormS02.map.taxWord} settings are not same"/></span>
<span style="display:none;" id="ERR4SC017_Category"><bean:message key="info.ERR4SC017" arg0="All Sub plan / Option's Category / Service settings are not same"/></span>
<span style="display:none;" id="ERR4SC017_GSTANDCategory"><bean:message key="info.ERR4SC017" arg0="All Sub plan / Option's ${_m_ppmFormS02.map.taxWord} settings and Category / Service settings are not same"/></span>
<!-- END temporary message -->
</body>
</html>