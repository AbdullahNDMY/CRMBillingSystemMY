<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/bs" prefix="bs" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
		<link href="<%=request.getContextPath()%>/B_CLS/css/b_clss01.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   		<title>Closing inquiry</title>
		<script type="text/javascript">
			/**
				Performing when click Search Button
			*/
			function clickSearch(){
				
				/*var objFrm = document.forms['_b_clsForm'];
				var id_cust = objFrm['id_cust'].value;
				
				if(id_cust == ''){				
					new messageBox().POPALT("Item Customer Name : Selection Error. Please select one.");
					return false;
				}
				
				//Set value for clickEvent as 0
				objFrm['clickEvent'].value = "0";*/				
				
				return true;
			}
			
			/**
				Performing when click Reset Button
			*/
			function clickReset(){
				var objFrm = document.forms['_b_clsForm'];
				objFrm['moduleId'].value = '';
				objFrm['status'].value = '';
				objFrm['month'].value = '';
				objFrm['year'].value = '';
			}
			
			function clickActionMode(mode, idYearMonth, moduleId){
				var MsgBox = new messageBox();
				var msg;
				if(mode == 'open')
					msg = document.getElementById("ERR4SC007_OPEN").value;
				else if(mode == 'close')
					msg = document.getElementById("ERR4SC007_CLOSE").value;
				if (MsgBox.POPCFM(msg) == MsgBox.YES_CONFIRM) {
					var objFrm = document.forms['_b_clsForm'];
					var txtField = document.getElementById('clickEvent');
					txtField.value = mode + "," + idYearMonth + "," + moduleId;
					objFrm.submit();
				}
			}
						
		</script>
	</head>
	<body id="b" onload="initMain();">			
		<ts:form action="/B_CLSR01BLogic" >
			<input type="hidden" id="ERR4SC007_OPEN" value="<bean:message key="info.ERR4SC007" arg0="Open"/>"/>
			<input type="hidden" id="ERR4SC007_CLOSE" value="<bean:message key="info.ERR4SC007" arg0="Close"/>"/>
            <h1 class="title"><bean:message key="screen.b_cls.title"/></h1>
			<div class="section" style="border-top:2px solid #cecece;border-bottom:2px solid #cecece;padding:5px 5px;margin-top:-5px;">
				<table cellpadding="0" cellspacing="0">
					<tr>
						<td align="right">
							<bean:message key="screen.b_cls.documentType"/><bean:message key="screen.b_cls.colon"/> 
						</td>					
						<td>												 
							<t:defineCodeList id="CLOSEDOCTYPE" />
							<html:select name="_b_clsForm" property="moduleId" style="width:200px;">
								<html:option value="" ><bean:message key="screen.b_cls.blankitem"/></html:option>
								<html:options collection="CLOSEDOCTYPE" property="id" labelProperty="name"/>
							</html:select>						
						</td>	
						<td align="right">
							<bean:message key="screen.b_cls.monthYear"/><bean:message key="screen.b_cls.colon"/> 
						</td>
						<td>
							<html:text property="month" name="_b_clsForm" size="1" maxlength="2"></html:text>/					
							<html:text property="year" name="_b_clsForm" size="2" maxlength="4"></html:text>
						</td>
					</tr>
					<tr>
						<td align="right"><bean:message key="screen.b_cls.status"/><bean:message key="screen.b_cls.colon"/> 
						</td>					
						<td>												 
							<t:defineCodeList id="CLOSESTATUS" />
							<html:select name="_b_clsForm" property="status"  style="width:200px;">
								<html:option value="" ><bean:message key="screen.b_cls.blankitem"/></html:option>
								<html:options collection="CLOSESTATUS" property="id" labelProperty="name"/>
							</html:select>						
						</td>
					</tr>	
				</table>
			</div>
			<div style="margin-top:10px;">
				<html:submit onclick="showLoadingTipWindow();"><bean:message key='screen.b_cls.searchbutton'/></html:submit>
				<!--<html:reset onclick="clickReset();"><bean:message key="screen.b_cls.resetbutton"/></html:reset>-->
				<bs:buttonLink value="Reset" action="/B_CLSS01SCR.do"></bs:buttonLink>
			</div>
			<table class="generalInfo" cellpadding="0" cellspacing="0">
			  	<tr>
					<td>
						<bean:message key="screen.b_cls.searchFound"/>
						<bean:message key="screen.b_cls.colon"/>
						${_b_clsForm.totalCount}
					</td>	
				</tr>
			</table>	
			<table class="pageLink" cellpadding="0" cellspacing="0">
			  	<tr>
					<td>			
						<bean:message key="screen.b_cls.goToPage"/> :
						<ts:pageLinks id="clsPageLinks" action="/B_CLSR01BLogic" name="_b_clsForm" rowProperty="row"
			             			totalProperty="totalCount" indexProperty="startIndex" currentPageIndex="now" totalPageCount="total"/>
			            <logic:present name="clsPageLinks">  
							<bean:write name="clsPageLinks" filter="false"/>
						</logic:present>	
					</td>	
				</tr>
			</table>
			<logic:greaterThan value="0" property="totalCount" name="_b_clsForm">
				<table class="resultCls" cellpadding="0" cellspacing="0">
					<COL width="10%"/><COL width="20%"/><COL width="20%"/>
					<COL width="20%"/><COL width="10%"/><COL width="10%"/><COL width="10%"/>
				  <tr>
				    <th><bean:message key="screen.b_cls.no"/></th>
				    <th><bean:message key="screen.b_cls.mY"/></th>
				    <th><bean:message key="screen.b_cls.documentType"/></th>
				    <th><bean:message key="screen.b_cls.totalDocument"/></th>
				    <th><bean:message key="screen.b_cls.outStanding"/></th>
				    <th><bean:message key="screen.b_cls.status"/></th>			    
				    <th><bean:message key="screen.b_cls.action"/></th>
				  </tr>
				<c:forEach items="${_b_clsForm.clsInfos}" varStatus="status" var="item">
					<c:if test="${not empty item.index}">
						<tr><td colspan="7" style="background:#cfcfcf;height:2px;"></td></tr>
					</c:if>
					<tr>
						<td>${item.index}</td>
						<td>
							${item.idYearMonthDisplay}
						</td>
						<td>
							${item.moduleId}
						</td>
						<td>${item.totalDoc}</td>
						<td>${item.totalOutDoc}</td>
						<td>
							<c:if test="${item.isClosed eq 0}">
								<bean:message key="screen.b_cls.open"/>&nbsp;&nbsp;&nbsp;&nbsp;
							</c:if>
							<c:if test="${item.isClosed eq 1}">
								<bean:message key="screen.b_cls.closed"/>&nbsp;&nbsp;&nbsp;&nbsp;
							</c:if>
						</td>				    
						<td width="10%">
							<c:if test="${_b_clsForm.accessType eq 2}">
								<c:if test="${item.totalDoc != 0 and item.isClosed eq 1}">
									<a href="javascript:clickActionMode('open', '${item.idYearMonth}', '${item.moduleId}')" ><bean:message key="screen.b_cls.open"/> </a>
									&nbsp;
								</c:if>
								<c:if test="${item.totalOutDoc != 0}">
									<a href="javascript:clickActionMode('close', '${item.idYearMonth}', '${item.moduleId}')" ><bean:message key="screen.b_cls.close"/> </a>
								</c:if>
							</c:if>
						</td>
					</tr>
				</c:forEach>
				 </table> 	
			 </logic:greaterThan>
			<html:hidden name="_b_clsForm" property="clickEvent" styleId="clickEvent" 
							value="${_b_clsForm.clickEvent eq '' ? '' : _b_clsForm.clickEvent}" />
			<div class="message">
				<ts:messages id="message" message="true"><bean:write name="message"/></ts:messages>
			</div>
			<div class="error">
		   		<ts:messages id="message" >
		    		<bean:write name="message"/><br/>
		    	</ts:messages>
		    </div>
		</ts:form>
	</body>
</html:html>
