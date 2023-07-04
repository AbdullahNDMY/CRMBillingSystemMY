<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">

<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title><bean:message key="screen.e_set.00028"/> <bean:write name="_e_setForm" property="TITLE_HDR" /></title>
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
		<link href="css/e_set.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/common.js"></script>
   		<script type="text/javascript">
   			$(document).ready(function(){
   				function fncExit(){
   					var pictureId = $("#srcid").val();
   					if(pictureId == 'EEML' || pictureId == 'RREV'){
   						window.close();
   					}else{
   						var url = '${pageContext.request.contextPath}/E_SET/SC_E_SET_S01SCR.do';
   					}
   					window.location.href = url;
   				}
   				$("#btnExit").bind("click", fncExit);
   			});
   			
   			function fncLogLink(ID_BATCH){
				var POPUP_FEATURE = "height=#height#,width=#width#, scrollbars=yes";
	   			var popupWidth = 800;
	   			var popupHeight = 400;
	   			var url = '${pageContext.request.contextPath}/E_SET/SC_E_SET_S03BL.do?ID_BATCH=' + ID_BATCH;
	   			
	   			window.open(url, "newWindow", POPUP_FEATURE.replace("#width#",popupWidth).replace("#height#",popupHeight));
			}
   		</script>
	</head>
	<body id="e">
		<!-- check access right START -->
		<%
			String accessRight = ((nttdm.bsys.common.fw.BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT")).getUserAccessInfo("E", "E-SET").getAccess_type();
			pageContext.setAttribute("accessRightBean", accessRight);
		 %>
<%-- 		<c:if test="${not(accessRightBean eq 1 or accessRightBean eq 2)}"> --%>
<!-- 			<script type="text/javascript"> -->
<%-- 				window.location = '${pageContext.request.contextPath}/C_CMN001/C_CMN001S06SCR.do'; --%>
<!-- 			</script> -->
<%-- 		</c:if> --%>
		<!-- check access right END -->
		<html:hidden name="_e_setForm" property="SCR_ID" styleId="srcid"/>
		<html:hidden name="_e_setForm" property="FUNC_ID" />
		<html:hidden name="_e_setForm" property="TITLE_HDR" />
		<h1 class="title"><bean:message key="screen.e_set.00028"/> <bean:write name="_e_setForm" property="TITLE_HDR" /></h1>
		<div class="section">
			<%-- <h2><bean:message key="screen.e_set.00029"/></h2>
			<input onclick='location.reload()' type="button" value="refresh" class="button"/> --%>
			<table class="subHeaderInfo" cellpadding="0" cellspacing="0">
		   	<tr>
		   		<td><bean:message key="screen.e_set.00029"/></td>
		   		<td align="right"><input onclick='location.reload()' type="button" value="<bean:message key="screen.e_set.00053"/>" class="button"/>&nbsp;&nbsp;
		   		</td>
		   	</tr>
		</table>
			
			<div class="group-content">
				<div class="group result">
					<h2><bean:message key="screen.e_set.00030"/> : ${_e_setForm.map.totalRow}</h2>
				</div>
				<div class="pageLink" style="width: 99%;"><bean:message key="screen.e_set.00031"/>:
					<ts:pageLinks 
			    		id="curPageLinks"
						action="/SC_E_SET_S02BL.do?SCR_ID=${_e_setForm.map.SCR_ID}&FUNC_ID=${_e_setForm.map.FUNC_ID}&TITLE_HDR=${_e_setForm.map.TITLE_HDR}" 
						name="_e_setForm" 
						rowProperty="row"
						totalProperty="totalRow" 
						indexProperty="startIndex"
						currentPageIndex="now" 
						totalPageCount="total"
					/>
					<logic:present name="curPageLinks">  
						<bean:write name="curPageLinks" filter="false"/>
					</logic:present>
				</div>
				<table class="datatable collapse" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th><bean:message key="screen.e_set.00032"/></th>
					    <th><bean:message key="screen.e_set.00033"/></th>
					    <th><bean:message key="screen.e_set.00034"/></th>
					    <th><bean:message key="screen.e_set.00035"/></th>
					    <th class="last"><bean:message key="screen.e_set.00036"/></th>
					</tr>
					<c:forEach items="${_e_setForm.map.listHistories}" var="item">
						<tr>
							<td style="width:10%;text-align:center;">${item.ID_BATCH}</td>
							<c:choose>
								<c:when test="${not empty item.FILENAME}">
									<td style="width:45%;">${item.FILENAME}</td>
								</c:when>
								<c:otherwise>
									<td style="width:45%;">&nbsp;</td>
								</c:otherwise>
							</c:choose>
							<td style="width:20%;">${item.CREATEDATE}</td>
							<c:choose>
								<c:when test="${1 eq item.STATUS}">
									<td style="width:10%;text-align:center;"><bean:message key="screen.e_set.00038"/></td>
								</c:when>
								<c:when test="${2 eq item.STATUS}">
									<td style="width:10%;text-align:center;"><bean:message key="screen.e_set.00039"/></td>
								</c:when>
								<c:when test="${3 eq item.STATUS}">
									<td style="width:10%;text-align:center;"><bean:message key="screen.e_set.00040"/></td>
								</c:when>
								<c:otherwise>
									<td style="width:10%;text-align:center;">&nbsp;</td>
								</c:otherwise>
							</c:choose>
							<td style="width:15%;">
								<a href="#" onclick="return fncLogLink(${item.ID_BATCH});" style="float:left;margin-left:10px;"><bean:message key="screen.e_set.00041"/></a>
								<c:if test="${('CB' eq _e_setForm.map.FUNC_ID or 'CC' eq _e_setForm.map.FUNC_ID) and accessRightBean gt 0}">
									<c:if test="${not empty item.FILENAME}">
										<a href="<%=request.getContextPath()%>/E_SET/SC_E_SET_S02DownloadBL.do?filename=${item.FILENAME}&FUNC_ID=${_e_setForm.map.FUNC_ID}" style="float:right;margin-right:10px;"><bean:message key="screen.e_set.00042"/></a>
									</c:if>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<p style="margin:50px 20px 0 20px;padding-bottom:20px;">
				<input type="button" id="btnExit" class="button" value="<bean:message key="screen.e_set.00037" />" />
			</p>
		</div>
		
		<div class="error">
			<html:messages id="message">
				<bean:write name="message"/><br/>
			</html:messages>
		</div>
		<div class="message">
			<ts:messages id="message" message="true"><bean:write name="message"/></ts:messages>
		</div>
	</body>
</html:html>

