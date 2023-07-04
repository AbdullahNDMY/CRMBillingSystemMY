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
   	<script type="text/javascript" src="<%=request.getContextPath()%>/M_PPM/js/m_ppm_s05.js"></script>
	<title></title>
	<script>
	function changewidth(){
		var popupWidth = window.screen.width*80/100;
		popupWidth = popupWidth - 45;
	    document.documentElement.childNodes[1].innerHTML="<div style='width:"+popupWidth+"px'>"+document.documentElement.childNodes[1].innerHTML+"</div>";
	}
	</script>
</head>
<body onload="planChecked();changewidth();">
<ts:form action="/M_PPMS05Search">
<t:defineCodeList id="LIST_RATEMODE" />
<t:defineCodeList id="LIST_PLANTYPE" />
<html:hidden name="_m_ppmFormS05" property="idPlan" />
<html:hidden name="_m_ppmFormS05" property="idPlanGrpList" />
<html:hidden name="_m_ppmFormS05" property="noDisplayOTC" />
<table class="subHeader">
	<tr>
		<td><bean:message key="screen.m_ppm_05.tittle"/></td>
	</tr>
</table>
<table class="information" cellpadding="0" cellspacing="0">
	<tr>
		<td align="left" width="15%" nowrap="nowrap"><bean:message key="screen.m_ppm_05.serviceName"/></td>
		<td>
			<bean:message key="screen.m_ppm_05.colon"/><bean:write name="_m_ppmFormS05" property="lblServiceName" />
		</td>
		<td align="right" width="50%"></td>
	</tr>
	<tr>
		<td align="left" width="15%" nowrap="nowrap"><bean:message key="screen.m_ppm_05.servicedesc"/></td>
		<td>
			<bean:message key="screen.m_ppm_05.colon"/><bean:write name="_m_ppmFormS05" property="lblServiceDescr" />
		</td>
		<td align="right" width="50%"></td>
	</tr>
</table>
<table class="buttonGroup" cellpadding="0" cellspacing="0">
	<tr>
		<td>
			<button id="insertBtn" onclick="insertAct('<%=request.getContextPath()%>')" disabled="disabled"><bean:message key="screen.m_ppm_05.button.insert"/></button>
		</td>
	</tr>
</table>
<table class="searchResultNo" cellpadding="0" cellspacing="0">
  	<tr>
		<td>
			<bean:message key="screen.m_ppm_05.searchtitle"/>&nbsp;<bean:message key="screen.m_ppm_05.colon"/>
			<bean:write name="_m_ppmFormS05" property="totalCount"/>
		</td>	
	</tr>
</table>
<table class="pageLink" cellpadding="0" cellspacing="0">
  	<tr>
		<td>			
			<bean:message key="screen.m_ppm_05.gotopage"/>&nbsp;<bean:message key="screen.m_ppm_05.colon"/>&nbsp;
			<ts:pageLinks id="userPageLinks"
				action="${pageContext.request.contextPath}/M_PPM/M_PPMS05Search.do"
				name="_m_ppmFormS05" 
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
	  	<td class="header" style="padding-left:10px;width:3%">&nbsp;</td>
	    <td class="header" valign="middle" style="padding-left:5px;width:5%"><bean:message key="screen.m_ppm_05.no"/></td>
	    <td class="header" valign="middle" style="padding-left:5px;width:8%"><bean:message key="screen.m_ppm_05.category"/></td>
	    <td class="header" valign="middle" style="padding-left:5px;width:21%"><bean:message key="screen.m_ppm_05.service"/></td>
	    <td class="header" valign="middle" style="padding-left:5px;width:9%"><bean:message key="screen.m_ppm_05.type"/></td>
	    <td class="header" valign="middle" style="padding-left:5px;width:25%"><bean:message key="screen.m_ppm_05.name"/></td>
	    <td class="header" valign="middle" style="padding-left:5px;width:9%"><bean:message key="screen.m_ppm_05.ratetype"/></td>
	    <td class="header" valign="middle" style="padding-left:5px;width:10%"><bean:message key="screen.m_ppm_05.mode"/></td>
	    <td class="header" valign="middle" style="padding-left:5px;padding-right:10px;width:10%"><bean:message key="screen.m_ppm_05.rate"/>(<bean:write name="_m_ppmFormS05" property="billCurrency" />)</td>
	</tr>
	<logic:present property="idPlanGrp" name="_m_ppmFormS05">
		<div style="display:none;">
		<logic:iterate id="idGrp" property="idPlanGrp" name="_m_ppmFormS05">
			<input type="checkbox" name="idPlanGrp" value="${idGrp}" checked="checked"/>
		</logic:iterate>
		</div>
	</logic:present>
	<logic:present property="searchResult" name="_m_ppmFormS05">
	<logic:iterate id="plan" property="searchResult" name="_m_ppmFormS05" indexId="index">
	<tr>
		<td  valign="top">
		  <logic:equal name="_m_ppmFormS05" property="noDisplayOTC" value="0">
				<logic:equal name="plan" property="SELECTED" value="Y">
				<input type="checkbox" name="idPlanGrp" onclick="planChecked();" value="${plan.IDPLANGRP}" checked="checked"/>
				</logic:equal>
				<logic:equal name="plan" property="SELECTED" value="N">
				<input type="checkbox" name="idPlanGrp" onclick="planChecked();" value="${plan.IDPLANGRP}"/>
				</logic:equal>
		  </logic:equal>
          <logic:equal name="_m_ppmFormS05" property="noDisplayOTC" value="1">
            <logic:notEqual name="plan" property="RATEMODE" value="6">
                <logic:equal name="plan" property="SELECTED" value="Y">
                <input type="checkbox" name="idPlanGrp" onclick="planChecked();" value="${plan.IDPLANGRP}" checked="checked"/>
                </logic:equal>
                <logic:equal name="plan" property="SELECTED" value="N">
                <input type="checkbox" name="idPlanGrp" onclick="planChecked();" value="${plan.IDPLANGRP}"/>
                </logic:equal>
            </logic:notEqual>
          </logic:equal>
		</td>
		<td valign="top">
			${_m_ppmFormS05.startIndex+index+1}
		</td>
		<td  valign="top">
			${plan.SVCGRPNAME}
		</td>
		<td style="word-break:break-all;" valign="top">
			${plan.SVCDESC}
		</td>
		<td  valign="top">
			${plan.ITEMTYPE}
		</td>
		<td style="word-break:break-all;" valign="top">
			${plan.ITEMGRPNAME}
		</td>
		<td valign="top">
			<t:writeCodeValue codeList="LIST_RATETYPE" key="${plan.RATETYPE}"/>
		</td>
		<td nowrap="nowrap" valign="top">
			<t:writeCodeValue codeList="LIST_RATEMODE" key="${plan.RATEMODE}"/>
		</td>
		<td valign="top">
			<fmt:formatNumber value="${plan.RATE}" pattern="#,##0.00"/>
		</td>
	</tr>
	<tr>
		<td style="border-bottom: #cfcfcf 1px solid" colspan="9"></td>
	</tr>
	</logic:iterate>
	</logic:present>
</table>  
</ts:form>
</body>
</html>