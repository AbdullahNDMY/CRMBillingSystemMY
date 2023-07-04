<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>    
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
	<script type="text/javascript" src="<%=request.getContextPath()%>/M_PPM/js/m_ppm_s02v.js"></script> 
</head>
<body >
<t:defineCodeList id="LIST_CURRENCY" />
<t:defineCodeList id="LIST_CUSTOMERTYPE" />
<t:defineCodeList id="LIST_RATEMODE" />
<t:defineCodeList id="LIST_RATETYPE" />
<ts:form action="M_PPM_S02eBL" >
	<table class="subHeader">
		<tr>
			<td><bean:message key="screen.m_ppms02.title"/></td>
		</tr>
	</table>
	<nested:nest property="plan">
		<nested:hidden property="idPlan" styleId="idPlan"/>
		<nested:hidden property="inUsed" styleId="inUsed"/>
		<table class="generalInfo" cellpadding="2" cellspacing="2" style="width:80%">
		    <col width="22%"/>
		    <col width="78%"/>
			<tr>
				<td><bean:message key="screen.m_ppms02.serviceName"/></td>
				<td>
					<bean:message key="screen.m_ppms02.colon"/>
					<nested:write property="txtPlanName"/>
				</td>
			</tr>
			<tr>
				<td><bean:message key="screen.m_ppms02.serviceDesc"/></td>
				<td>
					<bean:message key="screen.m_ppms02.colon"/>
					<nested:write property="txtPlanDescription"/>
				</td>
			</tr>
			<tr>
				<td><bean:message key="screen.m_ppms02.customertype"/></td>
				<td>
					<nested:define id="rdbCustomerType" property="rdbCustomerType"/>
					<bean:message key="screen.m_ppms02.colon"/>
					<logic:iterate id="customerType" name="LIST_CUSTOMERTYPE">
						<c:choose>
							<c:when test="${customerType.id == rdbCustomerType}">
								<nested:radio property="rdbCustomerType" value="${customerType.id}">${customerType.name}</nested:radio>
							</c:when>
							<c:otherwise>
								<nested:radio property="rdbCustomerType" value="${customerType.id}" disabled="true">${customerType.name}</nested:radio>
							</c:otherwise>
						</c:choose>
					</logic:iterate>
				</td>
			</tr>
			<tr>
				<td><bean:message key="screen.m_ppms02.billcurr"/></td>
				<td>
					<nested:define id="cboBillCurrenc" property="cboBillCurrenc"/>
					<bean:message key="screen.m_ppms02.colon"/>
					<t:writeCodeValue codeList="LIST_CURRENCY" key="${cboBillCurrenc}"/>
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
		</table>
		<%
			String accessTypeStr = ((nttdm.bsys.common.fw.BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT")).getUserAccessInfo("M", "M-PPM").getAccess_type();
			boolean authorized = accessTypeStr.equals("2");
		%>
		<bean:define id="accessType" value="<%=accessTypeStr%>"/>
		<table class="buttonGroup" cellpadding="0" cellspacing="0">
	  		<tr>
				<td>
					<c:choose>
						<c:when test="${accessType == '2'}">
						<input type="button" id="buttonAdd" value="<bean:message key="screen.m_ppms02.addsub"/>"/> 
						<input type="button" id="buttonOS" value="<bean:message key="screen.m_ppms02.addoption"/>"/>
						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
		<!-- <br/> -->
		<fieldset id="planInfo">
			<legend><bean:message key="screen.m_ppms02.standardplan"/></legend>
			<nested:iterate property="services">
				<nested:define id="type" property="type"/>
				<div class="service">
					<fieldset>
						<c:choose>
							<c:when test="${type == 'S'}"><legend><bean:message key="screen.m_ppms02.subplan"/></legend></c:when>
							<c:otherwise><legend><bean:message key="screen.m_ppms02.optionservice"/></legend></c:otherwise>
						</c:choose>
						<table cellpadding="2" cellspacing="2" class="serviceHeader">
							<tr>
								<td>
									<c:choose>
									<c:when test="${type == 'S'}"><bean:message key="screen.m_ppms02.svcName"/></c:when>
									<c:otherwise><bean:message key="screen.m_ppms02.optionName"/></c:otherwise>
									</c:choose>
								</td>
							    <td colspan="2">
							    	<bean:message key="screen.m_ppms02.colon"/>
							    	<nested:write property="tbxServiceName"/>
							    </td>
							</tr>
							<tr>
								<td><bean:message key="screen.m_ppms02.categoryAndService"/></td>
								<td>
									<bean:message key="screen.m_ppms02.colon"/>
									<nested:define id="cboSvcLevel1" property="cboSvcLevel1"/>
									<nested:hidden styleClass="cboSvcLevel1" property="cboSvcLevel1"/>
									<c:forEach items="${_m_ppmFormS02.map.cboCategoryList}" var="item">
										<c:if test="${cboSvcLevel1 == item.svcGrp}">${item.svcGrpName}</c:if>
									</c:forEach>
								</td>
								<td>&nbsp;&nbsp;&nbsp;
									<nested:define id="cboSvcLevel2" property="cboSvcLevel2"/>
									<nested:hidden styleClass="cboSvcLevel2" property="cboSvcLevel2"/>
									<nested:define id="cboSvcLevel2List" property="cboSvcLevel2List"/>
									<c:forEach items="${cboSvcLevel2List}" var="item">
										<c:if test="${cboSvcLevel2 == item.idService}">${item.svcDesc} - ${item.accCode}</c:if>
									</c:forEach>
								</td>
							</tr>
						</table>
						<table cellpadding="2" cellspacing="2" width="100%">
							<tr>
								<td valign="top">
									<table cellpadding="2" cellspacing="0" class="serviceDetail">
										<colgroup>
											<col width="5%"/><col width="65%"/><col width="30%"/>
										</colgroup>
										<tr class="header">
											<td class="add">&nbsp;</td>
											<td>
												<bean:message key="screen.m_ppms02.plan_planDetail"/>
											</td>
											<td>
												<bean:message key="screen.m_ppms02.vendor"/>
											</td>
										</tr>
										<nested:define id="cboPlanList" property="cboPlanList"/>
										<nested:define id="cboPlanDetailList" property="cboPlanDetailList"/>
										<nested:iterate property="details">
										<tr class="serviceDetailClone">
											<td class="remove">&nbsp;</td>
											<td class="planDetail">
												<nested:present property="cboPlan">
													<nested:define id="cboPlan" property="cboPlan"/>
													<c:forEach items="${cboPlanList}" var="item">
														<c:if test="${cboPlan == item.idService}">${item.svcDesc}</c:if>
													</c:forEach>
												</nested:present>
												<br/>
												<nested:present property="cboPlanDetail">
													<nested:define id="cboPlanDetail" property="cboPlanDetail"/>
													<c:forEach items="${cboPlanDetailList}" var="item">
														<c:if test="${cboPlanDetail == item.idService}">${item.svcDesc}</c:if>
													</c:forEach>
												</nested:present>&nbsp;
											</td>
											<td valign="top">
												<nested:present property="cboVendor">
													<nested:define id="cboVendor" property="cboVendor"/>
													<c:forEach items="${_m_ppmFormS02.map.cboVendorList}" var="item">
														<c:if test="${cboVendor == item.id}">${item.name}</c:if>
													</c:forEach>
												</nested:present>&nbsp;
											</td>
										</tr>
										</nested:iterate>
									</table>
								</td>
								<td valign="top" width="1px;">
									<table cellpadding="0" cellspacing="0" class="serviceInfo" style="width:100%;">
										<tr>
											<td class="headerLeft">
												<div>
													<bean:message key="screen.m_ppms02.rate"/>
													<br/>
													<bean:message key="screen.m_ppms02.type"/>
												</div>
											</td>
											<td class="headerCenter">
												<div>
													<bean:message key="screen.m_ppms02.rate"/>
													<br/>
													<bean:message key="screen.m_ppms02.mode"/>
												</div>
											</td>
											<td class="headerRight">
												<div>
													<bean:message key="screen.m_ppms02.rate"/>
													<br/>
													(<span class="serviceCurrency">${cboBillCurrenc}</span>)
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<nested:define id="cboRateType" property="cboRateType"/>
												<t:writeCodeValue codeList="LIST_RATETYPE" key="${cboRateType}"/>
											</td>
											<td>
												<nested:define id="cboRateMode" property="cboRateMode"/>
												<t:writeCodeValue codeList="LIST_RATEMODE" key="${cboRateMode}"/>
											</td>
											<td>
												<nested:define id="tbxRate" property="tbxRate"/>
												<fmt:formatNumber value="${tbxRate}" pattern="#,##0.00"/>
											</td>
										</tr>
										<tr>
											<td>
												<div style="width: 180px;">
												<nested:define id="cboRateType2Value" property="cboRateType2Value"/>
												<span>
													${cboRateType2Value}
												</span>
												</div>
											</td>
											<td colspan="2" class="gst">
												<div style="width: 250px;">
												<nested:define id="taxRateDescription" property="taxRateDescription"/>
												<nested:hidden styleClass="taxRateDescription" property="taxRateDescription"/>
												<!-- #436: [B2-2][REQ003]NewTaxCode Start -->
												${_m_ppmFormS02.map.taxWord}
												<!-- #436: [B2-2][REQ003]NewTaxCode End --> 
												<span class="lblGst">
													${taxRateDescription}				
												</span>
												</div>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</fieldset>
				</div>
			</nested:iterate>
		</fieldset>
	</nested:nest>
	<br/>
	<%
	String accessTypeStr = ((nttdm.bsys.common.fw.BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT")).getUserAccessInfo("M", "M-PPM").getAccess_type();
	boolean authorized = accessTypeStr.equals("2");
	%>
	<bean:define id="accessType" value="<%=accessTypeStr%>"/>
	<table class="buttonGroup" cellpadding="0" cellspacing="0">
	  	<tr>
			<td>
				<c:choose>
					<c:when test="${accessType == '2'}">
						<input type="button" class="button" value="<bean:message key="screen.m_ppms02.edit"/>" onclick="clickEdit('<%=request.getContextPath()%>')" />
						<input type="button" class="button" value="<bean:message key="screen.m_ppms02.delete"/>" onclick="clickDelete('<%=request.getContextPath()%>')" />
					</c:when>
					<c:otherwise>
				<!--  		<input type="button" class="button" value="<bean:message key="screen.m_ppms02.edit"/>" disabled="disabled"/>
						<input type="button" class="button" value="<bean:message key="screen.m_ppms02.delete"/>" disabled="disabled"/>
				-->		
					</c:otherwise>
				</c:choose>
				<input type="button" class="button" value="<bean:message key="screen.m_ppms02.exit"/>" onclick="clickExit('<%=request.getContextPath()%>')" />
			</td>
		</tr>
	</table>
</ts:form>
<input type="hidden" id="ERR4SC002" value="<bean:message key="info.ERR4SC002"/>"/>
<input type="hidden" id="ERR1SC054" value="<bean:message key="errors.ERR1SC054"/>"/>
<input type="hidden" id="contextPath" value="<%=request.getContextPath()%>" />
<div class="message">
	<html:messages id="message" message="true"><bean:write name="message"/></html:messages>
</div>
<div class="error">
	<html:messages id="message"><bean:write name="message"/><br/></html:messages>
</div>
</body>
</html>