<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
   	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
   	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/M_PPM/css/common.css"/>
   	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/M_PPM/css/m_ppm_s02.css"/>						
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/M_PPM/js/m_ppm_s02ne.js"></script> 
	<script type="text/javascript">
		function changeRateType(obj){
			var thisRateType = $(obj);
			var rateMode = thisRateType.val();
			var path = $("#contextPath").val();
			var subPlans = $(".service");
			var url = path+'/M_PPM/M_PPM_S02RateType2ChangeAjax.do?rateMode='+rateMode;
			$.ajax({
		        type: 'POST',
		        url: url,
		        success: function(result){
		        	thisRateType.closest(".service").find(".cboRateType2").val(result);
		        }
		      });
		} 
	</script>
<title></title>
</head>
<body onload="refreshServiceCallback();">
<t:defineCodeList id="LIST_CURRENCY" />
<t:defineCodeList id="LIST_CUSTOMERTYPE" />
<t:defineCodeList id="LIST_RATEMODE" />
<t:defineCodeList id="LIST_RATETYPE" />
<t:defineCodeList id="LIST_GST" />
<t:defineCodeList id="LIST_RATETYPE2" />
<table border="0" cellspacing="0" cellpadding="0" width="100%">
<tr><td>
<table class="subHeader">
  <tr>
    <td><bean:message key="screen.m_ppms02.title"/></td>
  </tr>
</table>
</td>
<tr><td>
	<ts:form action="M_PPM_S02SaveBL">
	<nested:nest property="plan">
	<nested:hidden property="idPlan"/>
	<nested:hidden property="modelFlg" styleId="modelFlg"/>
	<nested:hidden property="ppmOptionSvc" styleId="ppmOptionSvc"/>
	<nested:define id="ppmOptionSvcDef" property="ppmOptionSvc"/>
	<nested:hidden property="cboRateType2Flg" name="_m_ppmFormS02"/>
	<table class="generalInfo" cellpadding="2" cellspacing="2">
	<col width="22%"/>
	<col width="78%"/>
  <tr>
    <td><bean:message key="screen.m_ppms02.serviceName"/>&nbsp;<span class="mandatory"><bean:message key="screen.m_ppms02.label_mandatory"/></span></td>
    <td>
    	<bean:message key="screen.m_ppms02.colon"/>
    	<nested:text property="txtPlanName" maxlength="300" size="100"/>
    </td>
  </tr>
  <tr>
    <td><bean:message key="screen.m_ppms02.serviceDesc"/>&nbsp;<span class="mandatory"><bean:message key="screen.m_ppms02.label_mandatory"/></span></td>
    <td>
    	<bean:message key="screen.m_ppms02.colon"/>
    	<nested:text property="txtPlanDescription" maxlength="150" size="100"/>
    </td>        	
  </tr>
  <tr>
  	<td><bean:message key="screen.m_ppms02.customertype"/></td>
    <td>
    	<bean:message key="screen.m_ppms02.colon"/>
		<nested:empty property="rdbCustomerType">
			<logic:iterate id="customerType" name="LIST_CUSTOMERTYPE">
				<c:choose>
					<c:when test="${customerType.id == 'BOTH'}">
						<input type="radio" name="plan.rdbCustomerType" value="${customerType.id}" checked="checked"/>${customerType.name}
					</c:when>
					<c:otherwise>
						<nested:radio property="rdbCustomerType" value="${customerType.id}">${customerType.name}</nested:radio>
					</c:otherwise>
				</c:choose>
			</logic:iterate>
		</nested:empty>
		<nested:notEmpty property="rdbCustomerType">
	    	<logic:iterate id="customerType" name="LIST_CUSTOMERTYPE">
	    		<nested:radio property="rdbCustomerType" value="${customerType.id}">${customerType.name}</nested:radio>
	    	</logic:iterate>
		</nested:notEmpty>
    </td>
  </tr>
  <tr>
    <td><bean:message key="screen.m_ppms02.billcurr"/>&nbsp;<span class="mandatory"><bean:message key="screen.m_ppms02.label_mandatory"/></span></td>
    <td>
    	<bean:message key="screen.m_ppms02.colon"/>
    	<nested:select property="cboBillCurrenc" styleClass="cboBillCurrenc" onchange="changeCurrency(this)">
    		<option value=""><bean:message key="screen.m_ppms02.selectone"/></option>
    		<html:options collection="LIST_CURRENCY" property="id" labelProperty="name"/>
    	</nested:select>
    </td>   
  </tr>
  <tr>
      <td colspan="2">
          <fieldset>
              <legend style="color: #4876FF"><bean:message key="screen.m_ppms02.applyAllTitle"/></legend>
              <table cellpadding="0" cellspacing="0" border="0" style="width:100%">
                  <col width="22%"/>
                  <col width="1%"/>
                  <col width="77%"/>
                  <nested:equal value="new" property="modelFlg">
	                  <tr>
	                      <td>
	                          <nested:checkbox property="GSTApplyAllChk" styleId="GSTApplyAllChk" value="1" onclick="GSTApplyAllEvt('GSTApplyAllChkClick')"/>
	                          <!-- #436: [B2-2][REQ003]NewTaxCode Start -->
	                          <%-- <bean:message key="screen.m_ppms02.applyAllGst"/> --%>
	                          ${_m_ppmFormS02.map.taxWord}
	                          <!-- #436: [B2-2][REQ003]NewTaxCode End -->
	                      </td>
	                      <td>
	                          <bean:message key="screen.m_ppms02.applyAllColon"/>
	                      </td>
	                      <td>
	                          <nested:select property="GSTApplyAllCbo" styleId="GSTApplyAllCbo" onchange="GSTApplyAllEvt('GSTApplyAllCboChange')">
	                              <html:options collection="LIST_GST" property="id" labelProperty="name"/>
	                          </nested:select>
	                      </td>
	                  </tr>
	                  <tr>
	                      <td>
	                          <nested:checkbox property="categoryApplyAllChk" styleId="categoryApplyAllChk" value="1" onclick="categoryApplyAllEvt('categoryApplyAllChkClick','categoryApplyAllChkClick')"/>
	                          <bean:message key="screen.m_ppms02.applyAllCategory"/>
	                      </td>
	                      <td>
	                          <bean:message key="screen.m_ppms02.applyAllColon"/>
	                      </td>
	                      <td>
	                          <input type="hidden" id="categoryApplyAllCboHid" value='<nested:write property="categoryApplyAllCbo"/>'>
	                          <input type="hidden" id="serviceApplyAllCboHid" value='<nested:write property="serviceApplyAllCbo"/>'>
	                          <nested:select property="categoryApplyAllCbo" styleId="categoryApplyAllCbo" onchange="categoryApplyAllCboEvt(this, '1', '','categoryApplyAllCboChange')">
	                              <option value=""><bean:message key="screen.m_ppms02.selectone"/></option>
	                              <c:forEach items="${_m_ppmFormS02.map.cboCategoryList}" var="item">
								      <option value="${item.svcGrp}">${item.svcGrpName}</option>
								  </c:forEach>
	                          </nested:select>
	                          <nested:select property="serviceApplyAllCbo" styleId="serviceApplyAllCbo" onchange="categoryApplyAllEvt('serviceApplyAllCboChange','serviceApplyAllCboChange')">
	                              <option value=""><bean:message key="screen.m_ppms02.selectone"/></option>
	                          </nested:select>
	                      </td>
	                  </tr>
                  </nested:equal>
                  <nested:notEqual value="new" property="modelFlg">
                      <tr>
	                      <td>
	                          <!-- #436: [B2-2][REQ003]NewTaxCode Start -->
	                          <%-- <bean:message key="screen.m_ppms02.applyAllGst"/> --%>
	                          ${_m_ppmFormS02.map.taxWord}
	                          <!-- #436: [B2-2][REQ003]NewTaxCode End -->
	                      </td>
	                      <td>
	                          <bean:message key="screen.m_ppms02.applyAllColon"/>
	                      </td>
	                      <td>
	                          <nested:hidden property="allGstSameFlg"/>
	                          <nested:equal value="0" property="allGstSameFlg">
	                              <bean:message key="screen.m_ppms02.applyAllGstAllSame" arg0="${_m_ppmFormS02.map.taxWord}" />
	                          </nested:equal>
	                          <nested:notEqual value="0" property="allGstSameFlg">
	                              <span style="color: red;"><bean:message key="screen.m_ppms02.applyAllNotSameNotAll"/></span>
	                              <bean:message key="screen.m_ppms02.applyAllGstAllNotSame" arg0="${_m_ppmFormS02.map.taxWord}" />
	                          </nested:notEqual>
	                      </td>
	                  </tr>
	                  <tr>
	                      <td>
	                          <bean:message key="screen.m_ppms02.applyAllCategory"/>
	                      </td>
	                      <td>
	                          <bean:message key="screen.m_ppms02.applyAllColon"/>
	                      </td>
	                      <td>
	                          <nested:hidden property="allCategorySameFlg"/>
	                          <nested:equal value="0" property="allCategorySameFlg">
	                              <bean:message key="screen.m_ppms02.applyAllCategoryAllSame"/>
	                          </nested:equal>
	                          <nested:notEqual value="0" property="allCategorySameFlg">
	                              <span style="color: red;"><bean:message key="screen.m_ppms02.applyAllNotSameNotAll"/></span>
	                              <bean:message key="screen.m_ppms02.applyAllCategoryAllNotSame"/>
	                          </nested:notEqual>
	                      </td>
	                  </tr>
                  </nested:notEqual>
              </table>
          </fieldset>
      </td>
  </tr>
  <tr>
    <td class="colBottom" colspan="2">&nbsp;</td>
  </tr>
</table>
<nested:equal value="new" property="modelFlg">
<table class="buttonGroup" cellpadding="0" cellspacing="0">
  	<tr>
		<td>			
			<input type="button" value="<bean:message key="screen.m_ppms02.addsub"/>" onclick="addSubPlan()"/>							
			<input type="button" id="buttonOS" value="<bean:message key="screen.m_ppms02.addoption"/>" onclick="addOptionService()"/>
		</td>	
	</tr>
</table>
</nested:equal>
<fieldset id="planInfo">
	<legend><bean:message key="screen.m_ppms02.standardplan"/></legend>
	<div id="subplanGroup"></div>
	<div id="optionserviceGroup"></div>
	<div id="serviceTemp" style="display:none;">
		<nested:iterate property="services">
			<div class="service">
				<fieldset>
					<legend></legend>
					<div style="position:relative;">
						<input type="hidden" name="removeNotification"/>
						<nested:equal property="inMapping" value="0">
							<div onclick="removeService(this);" style="position:absolute;top:-22px;right:10px;background:#fff;cursor:default;">
								Remove <img src="<%=request.getContextPath()%>/image/delete.gif"/>
							</div>
						</nested:equal>
						<nested:notEqual property="inMapping" value="0">
							<div onclick="removeService2(this);" style="position:absolute;top:-22px;right:10px;background:#fff;cursor:default;">
								Remove <img src="<%=request.getContextPath()%>/image/delete.gif"/>
							</div>
						</nested:notEqual>
					</div>
					<nested:hidden property="inUsed" styleClass="inUsedHidden"/>
					<nested:hidden property="type" styleClass="type"/>
					<nested:define id="typeDef" property="type"/>
					<nested:hidden property="idPlanGrp" styleClass="idPlanGrp"/>
					<table cellpadding="2" cellspacing="2" class="serviceHeader">
						<tr>
							<td><span id="typeName"></span>&nbsp;<span class="mandatory"><bean:message key="screen.m_ppms02.label_mandatory"/></span></td>
						    <td colspan="2">
						    	<bean:message key="screen.m_ppms02.colon"/>
						    	<nested:text property="tbxServiceName" styleClass="tbxServiceName" maxlength="300" size="100"/>
						    </td>
						</tr>
						<tr>
							<td width="20%"><bean:message key="screen.m_ppms02.categoryAndService"/>&nbsp;<span class="mandatory"><bean:message key="screen.m_ppms02.label_mandatory"/></span></td>
						    <td width="20%">
						    	<input type="hidden" class="cboSvcLevel1_cached" value=""/>
						    	<nested:define id="cboSvcLevel1" property="cboSvcLevel1"/>
						    	<bean:message key="screen.m_ppms02.colon"/>
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
								<nested:define id="cboSvcLevel2" property="cboSvcLevel2"/>
								<nested:define id="cboSvcLevel2List" property="cboSvcLevel2List"/>
								<nested:define id="cboPlanList" property="cboPlanList"/>
								<nested:define id="cboPlanDetailList" property="cboPlanDetailList"/>
								<input type="hidden" value="${cboSvcLevel2}" class="cboSvcLevel2CallBack"/>
								<div class="cboService">
									<nested:select property="cboSvcLevel2" styleClass="cboSvcLevel2" onchange="changeSvcLevel2(this)">
										<option value="" gstValue="0"><bean:message key="screen.m_ppms02.selectone"/></option>
										<c:forEach items="${cboSvcLevel2List}" var="item">
											<option value="${item.idService}" gstValue="${item.gst}">${item.svcDesc} - ${item.accCode}</option>
										</c:forEach>
									</nested:select>
									<div class="cboPlan_PlanDetail" style="display:none;">
									    <select name="plan.services[i].details[j].cboPlan" class="cboPlan" onchange="changeSvcLevel3(this)">
											<option value="" gstValue="0"><bean:message key="screen.m_ppms02.selectone"/></option>
											<c:forEach items="${cboPlanList}" var="item">
												<option value="${item.idService}" gstValue="${item.gst}">${item.svcDesc}</option>
											</c:forEach>	
										</select>
										<br/>
										<select name="plan.services[i].details[j].cboPlanDetail" class="cboPlanDetail" onchange="changeSvcLevel4(this)">
											<option value="" gstValue="0"><bean:message key="screen.m_ppms02.selectone"/></option>
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
									<nested:iterate property="details">
									<tr class="serviceDetailClone">
										<td class="remove"><img src="<%=request.getContextPath()%>/image/delete.gif" onclick="removeServiceDetail(this);"/></td>
										<td class="planDetail">
											<input type="hidden" value="<nested:write property="cboPlan"/>" class="cboPlanCallBack"/>
											<input type="hidden" value="<nested:write property="cboPlanDetail"/>" class="cboPlanDetailCallBack"/>
											<nested:select property="cboPlan" styleClass="cboPlan" onchange="changeSvcLevel3(this)" style="${typeDef=='O' && ppmOptionSvcDef != '1'?'display:none':''}">
												<option value="" gstValue="0"><bean:message key="screen.m_ppms02.selectone"/></option>
												<c:forEach items="${cboPlanList}" var="item">
													<option value="${item.idService}" gstValue="${item.gst}">${item.svcDesc}</option>
												</c:forEach>
											</nested:select>
											<br/>
											<nested:select property="cboPlanDetail" styleClass="cboPlanDetail" onchange="changeSvcLevel4(this)" style="${typeDef=='O' && ppmOptionSvcDef != '1'?'display:none':''}">
												<option value="" gstValue="0"><bean:message key="screen.m_ppms02.selectone"/></option>
												<c:forEach items="${cboPlanDetailList}" var="item">
													<option value="${item.idService}" gstValue="${item.gst}">${item.svcDesc}</option>
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
												(<span class="serviceCurrency"></span>)
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
										    	<nested:select property="cboRateType2" styleClass="cboRateType2" styleId="cboRateType2">
										    		<html:optionsCollection name="LIST_RATETYPE2" value="id" label="name"/>
										    	</nested:select>
									    	</c:if>
									    	<c:if test="${_m_ppmFormS02.map.cboRateType2Flg ne '1'}">
										    	<nested:select property="cboRateType2" styleClass="cboRateType2" styleId="cboRateType2" disabled="true">
										    		<html:optionsCollection name="LIST_RATETYPE2" value="id" label="name"/>
										    	</nested:select>
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
				</fieldset>
			</div>
		</nested:iterate>
	</div>
</fieldset>
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
	</td>
	</tr>
</table>
<!-- BEGIN clone element -->
<div id="serviceClone" style="display:none;">
	<fieldset>
		<legend></legend>
		<div style="position:relative;">
			<input type="hidden" name="removeNotification"/>
			<div onclick="removeService(this);" style="position:absolute;top:-22px;right:10px;background:#fff;cursor:default;">
				Remove <img src="<%=request.getContextPath()%>/image/delete.gif"/>
			</div>
		</div>
		<input type="hidden"" name="plan.services[i].type" class="type"/>
		<table cellpadding="2" cellspacing="2" class="serviceHeader">
			<tr>
				<td><span id="typeName"></span>&nbsp;<span class="mandatory"><bean:message key="screen.m_ppms02.label_mandatory"/></span></td>
			    <td colspan="2">
			    	<bean:message key="screen.m_ppms02.colon"/>
			    	<input type="text" name="plan.services[i].tbxServiceName" class="tbxServiceName" maxlength="300" size="100" />
			    </td>
			</tr>
			<tr>
				<td width="20%"><bean:message key="screen.m_ppms02.categoryAndService"/>&nbsp;<span class="mandatory"><bean:message key="screen.m_ppms02.label_mandatory"/></span></td>
			    <td width="20%">
			    	<input type="hidden" class="cboSvcLevel1_cached" value=""/>
			    	<bean:message key="screen.m_ppms02.colon"/>
			    	<select name="plan.services[i].cboSvcLevel1" class="cboSvcLevel1" onchange="changeSvcLevel1(this)">
						<option value=""><bean:message key="screen.m_ppms02.selectone"/></option>
						<c:forEach items="${_m_ppmFormS02.map.cboCategoryList}" var="item">
							<option value="${item.svcGrp}">${item.svcGrpName}</option>
						</c:forEach>	
					</select>
				</td>
				<td>
					<div class="cboService">
						<select name="plan.services[i].cboSvcLevel2" class="cboSvcLevel2">
							<option value=""><bean:message key="screen.m_ppms02.selectone"/></option>
						</select>
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
									(<span class="serviceCurrency"></span>)
								</div>
							</td>
						</tr>
						<tr>
							<td>
						    	<select name="plan.services[i].cboRateType" class="cboRateType">
						    		<logic:iterate id="item_r" name="LIST_RATETYPE">
										<option value="${item_r.id}">${item_r.name}</option>
									</logic:iterate>
						    	</select>
							</td>
							<td>
								<select name="plan.services[i].cboRateMode" class="cboRateMode" onchange="changeRateType(this)">
									<logic:iterate id="item_r" name="LIST_RATEMODE">
										<option value="${item_r.id}">${item_r.name}</option>
									</logic:iterate>
								</select>
							</td>
							<td>
								<input type="text" name="plan.services[i].tbxRate" class="tbxRate" size="16" maxlength="16" />
							</td>
						</tr>
						<tr>
							<td>
								<c:if test="${_m_ppmFormS02.map.cboRateType2Flg eq '1'}">
							    	<select name="plan.services[i].cboRateType2" class="cboRateType2" id="cboRateType2">
							    		<logic:iterate id="item_r" name="LIST_RATETYPE2">
											<option value="${item_r.id}">${item_r.name}</option>
										</logic:iterate>
							    	</select>
							    </c:if>
							    <c:if test="${_m_ppmFormS02.map.cboRateType2Flg ne '1'}">
							    	<select name="plan.services[i].cboRateType2" class="cboRateType2" Id="cboRateType2" disabled="disabled">
							    		<logic:iterate id="item_r" name="LIST_RATETYPE2">
											<option value="${item_r.id}">${item_r.name}</option>
										</logic:iterate>
							    	</select>
							    </c:if>
							</td>
							<td colspan="3" class="gst">
								<!-- #436: [B2-2][REQ003]NewTaxCode Start -->
		                        ${_m_ppmFormS02.map.taxWord}
		                        <!-- #436: [B2-2][REQ003]NewTaxCode End -->
								
								<select name="plan.services[i].tbxGST" class="tbxGST">
						    		<logic:iterate id="tbxGST" name="LIST_GST">
										<option value="${tbxGST.id}">${tbxGST.name}</option>
									</logic:iterate>
						    	</select>
							</td>
						</tr>			
					</table>
				</td>
			</tr>
		</table>
	</fieldset>
</div>
<div style="display:none;">
	<table>
		<tr id="serviceDetailClone" class="serviceDetailClone">
			<td class="remove"></td>
			<td class="planDetail">
			    <select name="plan.services[i].details[j].cboPlan" class="cboPlan" onchange="changeSvcLevel3(this)">
					<option value=""><bean:message key="screen.m_ppms02.selectone"/></option>
				</select>
				<br/>
				<select name="plan.services[i].details[j].cboPlanDetail" class="cboPlanDetail" onchange="changeSvcLevel4(this)">
					<option value=""><bean:message key="screen.m_ppms02.selectone"/></option>
				</select>
			</td>
			<td valign="top">
				<select name="plan.services[i].details[j].cboVendor" class="cboVendor">
					<option value=""><bean:message key="screen.m_ppms02.selectone"/></option>
					<c:forEach items="${_m_ppmFormS02.map.cboVendorList}" var="item">
						<option value="${item.id}">${item.name}</option>
					</c:forEach>	
				</select>
			</td>
		</tr>
	</table>
</div>
<!-- END clone element -->
<!-- BEGIN temporary message -->
<input type="hidden" value="<%=request.getContextPath()%>" name="context" />
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