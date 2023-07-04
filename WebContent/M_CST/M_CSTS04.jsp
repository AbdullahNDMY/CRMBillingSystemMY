<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
   		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/M_CST/js/m_csts04.js"></script>
   		
		<title>Customer Search</title>
	<script>
	function changewidth(){
		var popupWidth = window.screen.width*80/100;
		popupWidth = popupWidth - 45;
	    document.documentElement.childNodes[1].innerHTML="<div style='width:"+popupWidth+"px'>"+document.documentElement.childNodes[1].innerHTML+"</div>";
	}
	</script>
	 	</head>
<body id="m_csts02" onload="changewidth();">
<ts:form action="/M_CSTS04SearchBLogic">
<html:hidden name="_m_cstForm" property="doSearch" value="Y"/>
<table class="subHeader">
	<tr>
		<td><bean:message key="screen.m_cst04.tittle"/></td>
	</tr>
</table>

<table class="information" cellpadding="0" cellspacing="0">
	<tr>
		<td align="right"><bean:message key="screen.m_cst04.label_customer_name"/></td>
		<td align="left" style="left-padding:0px"><bean:message key="screen.m_cst04.colon"/></td>
		<td>
			<html:text name="_m_cstForm" property="tbxCustomerName" size="40"/>
		</td>
		<td width="50%">&nbsp;</td>
	</tr>
	<tr>
		<td align="right"><bean:message key="screen.m_cst04.label_customer_id"/></td>
		<td align="left" style="left-padding:0px"><bean:message key="screen.m_cst04.colon"/></td>
		<td>
			<html:text name="_m_cstForm" property="tbxCustomerId" size="40"/>
		</td>
		<td width="50%">&nbsp;</td>
	</tr>
</table>
<table class="buttonGroup" cellpadding="0" cellspacing="0">
	<tr>
		<td>
			<button type="submit"><bean:message key="screen.m_cst04.button.search"/> </button>		
			<button onclick="resetAct()"><bean:message key="screen.m_cst04.button.reset"/> </button>
			<button id="insertBtn" onclick="insertAct('<%=request.getContextPath()%>')" disabled="disabled"><bean:message key="screen.m_cst04.button.insert"/></button>
		</td>
	</tr>
</table>
<table class="searchResultNo" cellpadding="0" cellspacing="0">
  	<tr>
		<td>
			<bean:message key="screen.m_cst04.lable.searchFound"/>&nbsp;<bean:message key="screen.m_cst04.colon"/>
			<bean:write name="_m_cstForm" property="totalCount"/>
		</td>	
	</tr>
</table>
<table class="pageLink" cellpadding="0" cellspacing="0">
	<tr>
		<td><bean:message key="screen.m_cst.label_page_link"/>
			<bean:message key="screen.m_cst.label_colon"/>
			<ts:pageLinks 
	    		id="curPageLinks"
				action="M_CSTS04SearchBLogic.do?pageSearch=Y" 
				name="_m_cstForm" 
				rowProperty="row"
				totalProperty="totalCount" 
				indexProperty="startIndex"
				currentPageIndex="now" 
				totalPageCount="total"
				submit="true"		
				/>
			<logic:present name="curPageLinks">
				<bean:write name="curPageLinks" filter="false"/>
			</logic:present>
		</td>
	</tr>
</table>
<table class="resultInfo" cellpadding="0" cellspacing="0">
	<tr>
		<td class="header" width="5%" style="text-align:left;padding-left:10px">&nbsp;</td>
		<td class="header" width="18%" style="text-align:left;padding-left:5px"><bean:message key="screen.m_cst04.lable.custid"/></td>
		<td class="header" width="27%" style="text-align:left;padding-left:5px"><bean:message key="screen.m_cst04.label_customer_name"/></td>
		<td class="header" width="25%" style="text-align:left;padding-left:5px"><bean:message key="screen.m_cst04.lable.peoplesoft"/></td>
		<td class="header" width="25%" style="text-align:left;padding-left:5px"><bean:message key="screen.m_cst04.lable.companyreg"/></td>
	</tr>
	<logic:notEmpty name="_m_cstForm" property="customerBeans">
	<logic:iterate id="resultBean" name="_m_cstForm" property="customerBeans">
		<tr>
			<td class="defaultText" style="text-align:left;padding-left:10px"><input type="radio" name="idPlanGrp" onclick="planChecked();" value="${resultBean.CUSTOMERID}"/>
				<input type="hidden" name="${resultBean.CUSTOMERID}" value="${resultBean.CUSTOMERNAME}"/>			
			    <input type="hidden" name="${resultBean.CUSTOMERID}" value="${resultBean.CUSTOMERTYPE}"/>		
			</td>
			<td class="defaultText" style="text-align:left;padding-left:5px"><bean:write name="resultBean" property="CUSTOMERID"/></td>
			<td class="defaultText" style="text-align:left;padding-left:5px"><bean:write name="resultBean" property="CUSTOMERNAME"/></td>
			<td class="defaultText" style="text-align:left;padding-left:5px"><bean:write name="resultBean" property="PEOPLESOFT"/></td>
			<td class="defaultText" style="text-align:left;padding-left:5px"><bean:write name="resultBean" property="COMPANYREG"/></td>
		</tr>
	</logic:iterate>
	</logic:notEmpty>
</table>
</ts:form>
</body>
</html:html>