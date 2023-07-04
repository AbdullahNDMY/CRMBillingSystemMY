<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>  
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="/WEB-INF/bs" prefix="bs" %>
<%@page import="nttdm.bsys.common.fw.BillingSystemUserValueObject"%>
<%@page import="nttdm.bsys.common.util.CommonUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheet/common.css"/>
<link href="${pageContext.request.contextPath}/M_SVT/css/m_svts01.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheet/tabcontent.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/messageBox.js"></script>
<script type="text/javascript">
function searchData() {
		var startIndex = document.getElementById("startIndex");
		if(startIndex!=null && startIndex!=undefined) {
			startIndex.value="0";
		}
}
function param(){
	var category = $('#cmbSerivceGroup').val();
	var accountCode = $('#accountCodeText').val();
	var type = $('#type').val();
	var productCode = $('#productCodeText').val();
	var description = $('#descriptionText').val();
	var reference1 = $('#reference1Text').val();
	var abbreviation = $('#abbreviationText').val();
	var reference2 = $('#reference2Text').val();
	var param = "category = "+category + ",accountCode = " + accountCode + ",type = " + type + ",productCode = " 
				+ productCode + ",description = " + description + ",reference1 = " + reference1 + ",abbreviation = " 
				+ abbreviation + ",reference2 = " + reference2;
	return param;
}
function OpenNew(url,title){
	var setParam = param();
	var chooesCode = $('#cmbSerivceGroup').val();
	var chooes = $('#cmbSerivceGroup option:selected').html();
	var pageMode = "new";
	url = url  + '?'+ 'editStatus=' + pageMode + '&title='+title + '&parameters='+setParam;
	openGamen(url);
}
function OpenEdit(url,title,svcGrpName,idService,serviceGroup){
    var setParam = param();
    var pageMode = "edit";
    url = url  + '?'+'&editStatus='+pageMode+'&svcGrpName='+svcGrpName+'&title='+title+'&idService='+idService + '&parameters='+setParam + '&category='+serviceGroup;
    openGamen(url);
}

function openGamen(url){
	var width = window.screen.width * 20 / 30;
    var height = window.screen.height * 35 / 80;
    var left = Number((screen.availWidth / 2) - (width / 2));
    var top = Number((screen.availHeight / 2) - (height / 2));
	var offsetFeatures = "width=" + width + ",height=" + height + ",left="
			+ left + ",top=" + top + "screenX=" + left + ",screenY=" + top;
	var strFeatures = "toolbar=no, status=no, menubar=no,location=no,scrollbars=yes, resizable=yes"
			+ "," + offsetFeatures;
	var newwindow = window.open(url, null, strFeatures);
	if (window.focus) {
		newwindow.focus();
	}
}
function clickDelete(idService){
	var MsgBox = new messageBox();
	if (MsgBox.POPCFM($('#hiddenMsgConfirmDeletetion').val()) == MsgBox.YES_CONFIRM) {
		document.getElementById("idService").value = idService;
		document.forms[0].action = '<%=request.getContextPath()%>/M_SVT/M_SVT01_Delete.do';
		document.forms[0].submit();
		//reset
		document.forms[0].action = '<%=request.getContextPath()%>/M_SVT/M_SVT01_Search.do';
	}
	
}
</script>
<title>Insert title here</title>
</head>
<body id="m">
<%
 	BillingSystemUserValueObject uvo = (BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT");
 	String accessRight = CommonUtils.getAccessRight(uvo, "M-SVT");
 	pageContext.setAttribute("accessRightBean", accessRight);
 %>
	<table class="subHeader" cellpadding="0" cellspacing="0">
    		<tr>
    			<td><bean:message key="screen.m_svt.title"/></td>
    		</tr>
   	</table>
   	<ts:form action="/M_SVT01_DSP">
   		<input type="hidden" name="idService" id="idService"/>
   		<input type="hidden" id="hiddenMsgNoSelected" value='<bean:message key="errors.ERR1SC038" arg0="Category"/>'/>
   		<input type="hidden" id="hiddenMsgConfirmDeletetion" value="<bean:message key="info.ERR4SC002"/>"/>
	   	<div class="searchCondition">
	   	<table cellpadding="0" cellspacing="0" width="70%">
	   		<tr>
		   		<td align="right">
			   		<bean:message key="screen.m_svt.svGroup"/>
			   		<bean:message key="screen.m_svt.label_colon"/>
			   	</td>
			   	<td>
			   		 <t:defineCodeList id="LIST_CATEGORY" />
					 <html:select property="category" styleId="cmbSerivceGroup" style="width:210px;" name="frm_MSVTS01">
						 <html:option value=""><bean:message key="screen.m_svt.listBox.default"/></html:option>
						 <html:options collection="LIST_CATEGORY" property="id" labelProperty="name"/>
					 </html:select>
		   		</td>
		   		<td align="right"><bean:message key="screen.m_svt.accountCode"/><bean:message key="screen.m_svt.label_colon"/> 
				</td>
				<td><html:text name="frm_MSVTS01" property="accountCode" styleClass="QCSTextBox" style="width:210px;" styleId="accountCodeText"></html:text>
				</td>
		   		
	   		</tr>
	   		<tr>
		   		<td align="right">
			   		<bean:message key="screen.m_svt.type"/>
			   		<bean:message key="screen.m_svt.label_colon"/>
			   	</td>
			   	<td>
			   		<t:defineCodeList id="LIST_SERVICE_TYPE" />
					 <html:select property="type" styleId="type" style="width:210px;" name="frm_MSVTS01">
						 <html:option value=""><bean:message key="screen.m_svt.listBox.default"/></html:option>
						 <html:options collection="LIST_SERVICE_TYPE" property="id" labelProperty="name"/>
					 </html:select>
		   		</td>
		   		<td align="right"><bean:message key="screen.m_svt.produceCode"/><bean:message key="screen.m_svt.label_colon"/> 
				</td>
				<td><html:text name="frm_MSVTS01" property="productCode" styleClass="QCSTextBox" style="width:210px;" styleId="productCodeText"></html:text>
				</td>
	   		</tr>
	   		<tr>
		   		<td align="right"><bean:message key="screen.m_svt.description"/><bean:message key="screen.m_svt.label_colon"/> 
				</td>
				<td><html:text name="frm_MSVTS01" property="description" styleClass="QCSTextBox" style="width:210px;" styleId="descriptionText"></html:text>
				</td>
		   		<td align="right"><bean:message key="screen.m_svt.reference1"/><bean:message key="screen.m_svt.label_colon"/> 
				</td>
				<td><html:text name="frm_MSVTS01" property="reference1" styleClass="QCSTextBox" style="width:210px;" styleId="reference1Text"></html:text>
				</td>
	   		</tr>
	   		<tr>
	   			<td align="right"><bean:message key="screen.m_svt.abbreviation"/><bean:message key="screen.m_svt.label_colon"/> 
				</td>
				<td><html:text name="frm_MSVTS01" property="abbreviation" styleClass="QCSTextBox" style="width:210px;" styleId="abbreviationText"></html:text>
				</td>
				<td align="right"><bean:message key="screen.m_svt.reference2"/><bean:message key="screen.m_svt.label_colon"/> 
				</td>
				<td><html:text name="frm_MSVTS01" property="reference2" styleClass="QCSTextBox" style="width:210px;" styleId="reference2Text"></html:text>
				</td>
	   		</tr>
	   	</table>
	   		
	   	</div>
	   	<div><br/></div>
	   	<div>
	   		<html:submit property="forward_search" onclick="searchData();showLoadingTipWindow();"><bean:message key='screen.m_svt.search'/></html:submit>
			<bs:buttonLink action="/m_svt_gate" value="Reset"/>
			<logic:equal value="2" name="accessRightBean">
				<input type="button" id="btnService" onclick="OpenNew('<%=request.getContextPath()%>/M_SVT/M_SVTS01_New.do','Service')" value="<bean:message key="screen.m_svt.newService"/>" />
				<input type="button" id="btnPlan" onclick="OpenNew('<%=request.getContextPath()%>/M_SVT/M_SVTS01_New.do','Plan')" value="<bean:message key="screen.m_svt.plan"/>" />
				<input type="button" id="btnPlanDetail" onclick="OpenNew('<%=request.getContextPath()%>/M_SVT/M_SVTS01_New.do','Plan Detail')" value="<bean:message key="screen.m_svt.planDetail"/>" />
			</logic:equal>
			<c:if test="${accessRightBean == '1' or accessRightBean == '2' }">
				<c:if test="${frm_MSVTS01.totalCount > 0}">
					<html:submit property="forward_export" onclick="showLoadingTipWindow();"><bean:message key='screen.m_svt.export'/></html:submit>
				</c:if>
				<c:if test="${frm_MSVTS01.totalCount == 0 or frm_MSVTS01.totalCount == null or frm_MSVTS01.totalCount == -1}">
					<html:submit property="forward_export" onclick="showLoadingTipWindow();" disabled="true"><bean:message key='screen.m_svt.export'/></html:submit>
				</c:if>
			</c:if>
	   	</div>
	   	<br/>
	   	<table class="searchResultNo" cellpadding="0" cellspacing="0">
		  	<tr>
				<td style="font-size:12pt;">
					<bean:message key="screen.m_svt.seachFound"/> <bean:message key="screen.m_svt.label_colon"/>
					<c:if test="${frm_MSVTS01.totalCount != -1}">
						<bean:write name="frm_MSVTS01" property="totalCount"/>
					</c:if>
				</td>	
			</tr>
		</table> 
		<table class="pageLink" cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<td><bean:message key="screen.m_svt.gotoPage"/> <bean:message key="screen.m_svt.label_colon"/>
					<ts:pageLinks 
			    		id="curPageLinks"
						action="${pageContext.request.contextPath}/M_SVT/M_SVT01_Search.do" 
						name="frm_MSVTS01" 
						rowProperty="row"
						totalProperty="totalCount" 
						indexProperty="startIndex"
						currentPageIndex="now" 
						totalPageCount="total"
						submit="true" />
					<logic:present name="curPageLinks">
						<bean:write name="curPageLinks" filter="false"/>
					</logic:present>
				</td>
			</tr>
		</table>
		<div class="section">
			<table  class="resultInfo" cellpadding="0" cellspacing="0" border="0">
			  <tr>
				<th width="6%"><bean:message key="screen.m_svt.no"/></th>
				<th width="10%"><bean:message key="screen.m_svt.svGroup"/></th>
				<th width="10%"><bean:message key="screen.m_svt.type"/></th>
				<th width="14%"><bean:message key="screen.m_svt.description"/></th>
				<th width="10%"><bean:message key="screen.m_svt.abbreviation"/></th>
				<th width="10%"><bean:message key="screen.m_svt.accountCode"/></th>
				<th width="10%"><bean:message key="screen.m_svt.produceCode"/></th>
				<th width="10%"><bean:message key="screen.m_svt.reference1"/></th>
				<th width="10%"><bean:message key="screen.m_svt.reference2"/></th>
				<th width="10%"></th>
			  </tr>
			  <logic:present property="listPlanService" name="frm_MSVTS01">
		  	  <logic:iterate id="planService" name="frm_MSVTS01" property="listPlanService" indexId="i">
		  	  	<tr>
			  	  	<td class="defaultText">   
			  	  	${i + 1 + frm_MSVTS01.startIndex}
			   		</td>
			   		<td class="defaultText">		    	
			    		<bean:write name="planService" property="svcGrpName"/>
			   		</td>
			   		<td class="defaultText">		    	
			    		<bean:write name="planService" property="type"/>
			   		</td>
			   		<td class="defaultText">
			   			<!-- #166 Start -->
			   			<%-- <c:choose>
			   				<c:when test="${frm_MSVTS01.mode  eq '2' and (planService.isUserX gt 0 or planService.isUserY eq 0)}">
			   				<a href="#" onclick="OpenEdit('<%=request.getContextPath()%>/M_SVT/M_SVTS01_New.do','<bean:write name="planService" property="type"/>','<bean:write name="planService" property="svcGrpName"/>','<bean:write name="planService" property="idService"/>','<bean:write name="planService" property="serviceGroup"/>')"><bean:write name="planService" property="serviceDescription"/></a>
			   				</c:when>
			   				<c:otherwise>
			   				<bean:write name="planService" property="serviceDescription"/>
			   				</c:otherwise>
			   			</c:choose> --%>
			   			<c:choose>
			   				<c:when test="${frm_MSVTS01.mode  eq '2' and accessRightBean eq '2'}">
			   					<a href="#" onclick="OpenEdit('<%=request.getContextPath()%>/M_SVT/M_SVTS01_New.do','<bean:write name="planService" property="type"/>','<bean:write name="planService" property="svcGrpName"/>','<bean:write name="planService" property="idService"/>','<bean:write name="planService" property="serviceGroup"/>')"><bean:write name="planService" property="serviceDescription"/></a>
			   				</c:when>
			   				<c:otherwise>
				   				<c:choose>
					   				<c:when test="${planService.isUserX eq 0 and planService.isUserY eq 0 and accessRightBean eq '2'}">
					   					<a href="#" onclick="OpenEdit('<%=request.getContextPath()%>/M_SVT/M_SVTS01_New.do','<bean:write name="planService" property="type"/>','<bean:write name="planService" property="svcGrpName"/>','<bean:write name="planService" property="idService"/>','<bean:write name="planService" property="serviceGroup"/>')"><bean:write name="planService" property="serviceDescription"/></a>
					   				</c:when>
					   				<c:otherwise>
					   					<bean:write name="planService" property="serviceDescription"/>
					   				</c:otherwise>
				   				</c:choose>
			   				</c:otherwise>
			   			</c:choose>
			   			<!-- #166 End -->
			   		</td>
			   		<td class="defaultText">		    	
			    		<bean:write name="planService" property="descAbbr"/>
			   		</td>
			   		<td class="defaultText">
				    		<bean:write name="planService" property="accCode"/>
			   		</td>
			   		<td class="defaultText">
				    		<bean:write name="planService" property="productCode"/>
			   		</td>
			   		<td class="defaultText">		    	
			    		<bean:write name="planService" property="reference1"/>
			   		</td>
			   		<td class="defaultText">		    	
			    		<bean:write name="planService" property="reference2"/>
			   		</td>
			   		<td class="defaultText">
			   			<c:if test="${planService.isUserX eq 0 and planService.isUserY eq 0 and accessRightBean eq '2'}">
			   			<a href="#" onclick="clickDelete('<bean:write name="planService" property="idService"/>') ">Delete</a>
			   			</c:if>	
			   		</td>
		  	  	</tr>
		  	  </logic:iterate>
		  	  </logic:present>
			</table>
		</div>
			
   	</ts:form>
	<div class="error">
		<html:messages id="message">
			<bean:write name="message"/><br/>
		</html:messages>
	</div>
   	<div class="message">
		<html:messages id="message" message="true">
			<bean:write name="message"/><br/>
		</html:messages>
	</div> 
</body>
</html>
