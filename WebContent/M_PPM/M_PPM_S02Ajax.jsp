<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>

<select name="plan.services[i].cboSvcLevel2" class="cboSvcLevel2" onchange="changeSvcLevel2(this)">
	<option value="" gstValue="0"><bean:message key="screen.m_ppms02.selectone"/></option>
	<c:forEach items="${_m_ppmFormS02AJAX.map.cboSvcLevel2List}" var="item">
		<option value="${item.idService}" gstValue="${item.gst}">${item.svcDesc} - ${item.accCode}</option>
	</c:forEach>	
</select>
<!--  
<c:forEach items="${_m_ppmFormS02AJAX.map.cboPlanList}" var="item">
	<input type="hidden" name="plan.services[i].cboPlanList[j].idService" class="cboPlanList_Id" value="${item.idService}"/>
	<input type="hidden" name="plan.services[i].cboPlanList[j].svcDesc" class="cboPlanList_Desc" value="${item.svcDesc}"/>
</c:forEach>
<c:forEach items="${_m_ppmFormS02AJAX.map.cboPlanDetailList}" var="item">
	<input type="hidden" name="plan.services[i].cboPlanDetailList[j].idService" class="cboPlanDetailList_Id" value="${item.idService}"/>
	<input type="hidden" name="plan.services[i].cboPlanDetailList[j].svcDesc" class="cboPlanDetailList_Desc" value="${item.svcDesc}"/>
</c:forEach>
-->
<!-- END temporary for GST -->
<div class="cboPlan_PlanDetail" style="display:none;">
    <c:if test="${_m_ppmFormS02AJAX.map.ppmOptionSvc=='1'}">
	    <select name="plan.services[i].details[j].cboPlan" class="cboPlan" onchange="changeSvcLevel3(this)">
			<option value="" gstValue="0"><bean:message key="screen.m_ppms02.selectone"/></option>
			<c:forEach items="${_m_ppmFormS02AJAX.map.cboPlanList}" var="item">
				<option value="${item.idService}" gstValue="${item.gst}">${item.svcDesc}</option>
			</c:forEach>	
		</select>
		<br/>
		<select name="plan.services[i].details[j].cboPlanDetail" class="cboPlanDetail" onchange="changeSvcLevel4(this)">
			<option value="" gstValue="0"><bean:message key="screen.m_ppms02.selectone"/></option>
			<c:forEach items="${_m_ppmFormS02AJAX.map.cboPlanDetailList}" var="item">
				<option value="${item.idService}" gstValue="${item.gst}">${item.svcDesc}</option>
			</c:forEach>	
		</select>
    </c:if>
    <c:if test="${_m_ppmFormS02AJAX.map.ppmOptionSvc!='1'}">
    	<select name="plan.services[i].details[j].cboPlan" class="cboPlan" onchange="changeSvcLevel3(this)" disabled="true">
			<option value="" gstValue="0"><bean:message key="screen.m_ppms02.selectone"/></option>
			<c:forEach items="${_m_ppmFormS02AJAX.map.cboPlanList}" var="item">
				<option value="${item.idService}" gstValue="${item.gst}">${item.svcDesc}</option>
			</c:forEach>	
		</select>
		<br/>
		<select name="plan.services[i].details[j].cboPlanDetail" class="cboPlanDetail" onchange="changeSvcLevel4(this)" disabled="true">
			<option value="" gstValue="0"><bean:message key="screen.m_ppms02.selectone"/></option>
			<c:forEach items="${_m_ppmFormS02AJAX.map.cboPlanDetailList}" var="item">
				<option value="${item.idService}" gstValue="${item.gst}">${item.svcDesc}</option>
			</c:forEach>	
		</select>
    </c:if>
</div>