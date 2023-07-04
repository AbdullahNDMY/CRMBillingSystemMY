<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
   	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
   	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/M_PPM/css/m_ppm_s03.css"/>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   	<script type="text/javascript" src="<%=request.getContextPath()%>/M_PPM/js/m_ppm_s03.js"></script>
	<title>Insert Customer Plan</title>
	<script>
	function changewidth(){
		var popupWidth = window.screen.width*80/100;
		popupWidth = popupWidth - 45;
	    document.documentElement.childNodes[1].innerHTML="<div style='width:"+popupWidth+"px'>"+document.documentElement.childNodes[1].innerHTML+"</div>";
	}
	</script>
	<style>
	/* wcbeh@20161206 - #211 Service Combobox */
		.ui-autocomplete {
    		overflow-y: auto;
    		overflow-x: hidden;
    		font-family: Calibri;
    		font-size:0.9em;
  		}
  		.ui-button{
  			margin-top:-8px;
  		}
	</style>
</head>
<body onload="planChecked();changewidth();initService();">
<ts:form action="/M_PPM_S03_02BL" onsubmit="initSearch();">
<t:defineCodeList id="LIST_RATETYPE" />
<t:defineCodeList id="LIST_RATEMODE" />
<t:defineCodeList id="LIST_PLANTYPE" />
<html:hidden property="customerType" value="${_m_ppmFormS03.map.customerType}"/>
<html:hidden property="doSearch" value="Y"/>
<table class="subHeader">
	<tr>
		<td><bean:message key="screen.m_ppms03.tittle"/></td>
	</tr>
</table>
<table class="information" cellpadding="0" cellspacing="0">
    <col width="20%"/>
    <col width="30%"/>
    <col width="20%"/>
    <col width="30%"/>
	<tr>
		<td align="right"><bean:message key="screen.m_ppms03.lable.planName"/>&nbsp;<bean:message key="screen.m_ppms01.colon"/></td>
		<td>
			<html:text property="tbxPlanName" style="width:100%"/>
		</td>
		<td align="right"><bean:message key="screen.m_ppms03.lable.type"/>&nbsp;<bean:message key="screen.m_ppms01.colon"/></td>
		<td >
			<html:select property="cboType">
				<html:option value=""><bean:message key="screen.m_ppms03.lable.selectedDefault"/></html:option>
				<html:optionsCollection name="LIST_PLANTYPE" value="id" label="name"/>
			</html:select>
		</td>
	</tr>
	<tr>
		<td align="right"><bean:message key="screen.m_ppms03.lable.planDescription"/>&nbsp;<bean:message key="screen.m_ppms01.colon"/></td>
		<td>
			<html:text property="tbxPlanDescript" style="width:100%"/>
		</td>
		<td align="right"><bean:message key="screen.m_ppms03.lable.ratemode"/>&nbsp;<bean:message key="screen.m_ppms01.colon"/></td>
		<td>
			<html:select property="cboRateMode">
				<html:option value=""><bean:message key="screen.m_ppms03.lable.selectedDefault"/></html:option>
				<html:optionsCollection name="LIST_RATEMODE" value="id" label="name"/>
			</html:select>
		</td>
	</tr>
	<tr>
		<td align="right"><bean:message key="screen.m_ppms03.lable.serviceName"/>&nbsp;<bean:message key="screen.m_ppms01.colon"/></td>
		<td>
			<html:text property="tbxServiceName" style="width:100%"/>
		</td>
		<td align="right"><bean:message key="screen.m_ppms03.lable.category"/>&nbsp;<bean:message key="screen.m_ppms01.colon"/></td>
		<td>
			<bean:define id="cboCategory" value="${_m_ppmFormS03.map.cboCategory}"/>
			<html:select property="cboCategory" styleId="cboCategory" onchange="cboCategoryChange(this)">
				<html:option value=""><bean:message key="screen.m_ppms03.lable.selectedDefault"/></html:option>
				<c:forEach items="${_m_ppmFormS03.map.cboCategoryList}" var="item">
					<c:choose>
						<c:when test="${cboCategory == item.svcGrp}">
							<option value="${item.svcGrp}" selected="selected">${item.svcGrpName}</option>
						</c:when>
						<c:otherwise>
							<option value="${item.svcGrp}">${item.svcGrpName}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</html:select>
		</td>
	</tr>
	<tr>
		<td align="right">
		    &nbsp;
		</td>
		<td>
			&nbsp;
		</td>
		<td align="right"><bean:message key="screen.m_ppms03.lable.service"/>&nbsp;<bean:message key="screen.m_ppms01.colon"/></td>
		<td>
			<!-- wcbeh@20161205 #211 Change to Combobox editable -->
			<jsp:include page="/COMMON/SearchableSelect.jsp" flush="true">
				 <jsp:param value="inputSearchPlan" name="serviceGroup"/>
			</jsp:include>
		    <input type="hidden" id="hidService" name="hidService" value="${_m_ppmFormS03.map.cboService}"/>
			<select name="cboService" class="cboService" id="cboService">
			</select>
			<!-- <html:select property="cboService" styleId="cboService">
			    <html:option value=""><bean:message key="screen.m_ppms03.lable.selectedDefault"/></html:option>
			</html:select> -->
		</td>
	</tr>
</table>
<table class="buttonGroup" cellpadding="0" cellspacing="0">
	<tr>
		<td>
			<button type="submit" onclick="searchClick();"><bean:message key="screen.m_ppms03.button.search"/> </button>		
			<button onclick="resetAct()"><bean:message key="screen.m_ppms03.button.reset"/> </button>
			<button id="insertBtn" onclick="insertAct('<%=request.getContextPath()%>')" disabled="disabled"><bean:message key="screen.m_ppms03.button.insert"/> </button>
		</td>
	</tr>
</table>
<table class="searchResultNo" cellpadding="0" cellspacing="0">
  	<tr>
		<td>
			<bean:message key="screen.m_ppms03.lable.searchFound"/> <bean:message key="screen.m_ppms01.colon"/>
			<bean:write name="_m_ppmFormS03" property="totalCount"/>
		</td>	
	</tr>
</table>
<table class="pageLink" cellpadding="0" cellspacing="0">
  	<tr>
		<td>			
			<bean:message key="screen.m_ppms03.lable.goToPage"/>&nbsp;<bean:message key="screen.m_ppms01.colon"/>&nbsp;
			<ts:pageLinks id="userPageLinks"
				action="${pageContext.request.contextPath}/M_PPM/M_PPM_S03_02BL.do"
				name="_m_ppmFormS03" 
				rowProperty="row" 
				totalProperty="totalCount"
				indexProperty="startIndex" 
				currentPageIndex="now"
				totalPageCount="total" 
				submit="true"/>
            <logic:present name="userPageLinks">  
				<bean:write name="userPageLinks" filter="false"/>
			</logic:present>	
		</td>	
	</tr>
</table>
<table  class="resultInfo search_result_table" cellpadding="0" cellspacing="0">
	<tr>
	  	<td class="header" style="width:2%">&nbsp;</td>
	    <td class="header" valign="bottom" style="width:3%"><bean:message key="screen.m_ppms03.lable.id"/></td>
	    <td class="header" valign="bottom" style="padding-left:10px;width:7%"><bean:message key="screen.m_ppms03.lable.cat"/></td>
	    <td class="header" valign="bottom" style="padding-left:5px;width:20%"><bean:message key="screen.m_ppms03.lable.service"/></td>
	    <td class="header" valign="bottom" style="padding-left:10px;width:23%"><bean:message key="screen.m_ppms03.lable.serviceNameDescription"/></td>
	    <td class="header" valign="bottom" style="padding-left:10px;width:9%"><bean:message key="screen.m_ppms03.lable.type"/></td>
	    <td class="header" valign="bottom" style="padding-left:10px;width:13%"><bean:message key="screen.m_ppms03.lable.serviceNametitle"/></td>
	    <td class="header" valign="bottom" style="padding-left:5px;width:5%"><bean:message key="screen.m_ppms03.lable.rateType"/></td>
	    <td class="header" valign="bottom" style="padding-left:5px;width:8%"><bean:message key="screen.m_ppms03.lable.rateMode"/></td>
	    <td class="header" valign="bottom" colspan="2" style="padding-left:10px;width:10%;padding-right:0.05in;"><bean:message key="screen.m_ppms03.lable.rate"/></td>
	</tr>
	<logic:present property="idPlanGrp" name="_m_ppmFormS03">
		<div style="display:none;">
		<logic:iterate id="idGrp" property="idPlanGrp" name="_m_ppmFormS03">
			<input type="checkbox" name="idPlanGrp" value="${idGrp}" checked="checked"/>
		</logic:iterate>
		</div>
	</logic:present>
	<logic:present property="searchResult" name="_m_ppmFormS03">
	<logic:iterate id="plan" property="searchResult" name="_m_ppmFormS03">
	<tr>
		<td style="width:2%" valign="top">
			<c:choose>
				<c:when test="${plan.selected}"><input type="checkbox" name="idPlanGrp" onclick="planChecked();" value="${plan.idPlanGrp}" checked="checked"/></c:when>
				<c:otherwise><input type="checkbox" name="idPlanGrp" onclick="planChecked();" value="${plan.idPlanGrp}"/></c:otherwise>
			</c:choose>
		</td>
		<td style="width:3%" valign="top">
			${plan.idPlanGrp}
		</td>
		<td style="padding-left:10px;width:7%" valign="top">
			${plan.cat}
		</td>
		<td style="padding-left:5px;width:20%;word-break:break-all;" valign="top">
			${plan.bill}
		</td>
		<td style="padding-left:10px;width:23%;word-break:break-all;" valign="top">
			${plan.planName}
		</td>
		<td style="padding-left:10px;width:9%" nowrap="nowrap" valign="top">
			<t:writeCodeValue codeList="LIST_PLANTYPE" key="${plan.type}"/>
		</td>
		<td style="padding-left:10px;width:13%" valign="top">
			${plan.serviceName}
		</td>
		<td style="padding-left:5px;width:5%" valign="top">
			<t:writeCodeValue codeList="LIST_RATETYPE" key="${plan.rateType}"/>
		</td>
		<td style="padding-left:5px;width:8%" nowrap="nowrap" valign="top">
			<t:writeCodeValue codeList="LIST_RATEMODE" key="${plan.rateMode}"/>
		</td>
		<td valign="top">
			${plan.currency}
		</td>
		<td align="right" style="padding-right:0.05in;" valign="top">
			<fmt:formatNumber value="${plan.rate}" pattern="#,##0.00"/>
		</td>
	</tr>
	<tr>
		<td colspan="3">&nbsp;</td>
		<td>${plan.accountCode}</td>
		<td>${plan.descriptionLimit}</td>
		<td colspan="6">&nbsp;</td>
	</tr>
	<tr>
		<td style="border-bottom: #cfcfcf 1px solid" colspan="11"></td>
	</tr>
	</logic:iterate>
	</logic:present>
</table>

<select id="serviceClone" style="display:none">
	<option value=""><bean:message key="screen.m_ppms03.lable.selectedDefault"/></option>
	<logic:iterate id="service" name="_m_ppmFormS03" property="cboServiceList">
		<option value="<bean:write name="service" property="ID_SERVICE"/>"
		        svcGrp="<bean:write name="service" property="SVC_GRP"/>">
		        <bean:write name="service" property="SVC_NAME"/>
		</option>
	</logic:iterate>
</select>
</ts:form>
</body>
</html>