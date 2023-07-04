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
   		<script type="text/javascript" src="<%=request.getContextPath()%>/B_BAC/js/SC_B_BAC_S03.js"></script>
		<title></title>
	 	</head>
<body onload="changewidth();">
<ts:form action="/RP_B_BAC_S03SearchBL">
<html:hidden name="_b_bac_s03Form" property="doSearch" value="Y"/>
<table class="subHeader">
	<tr>
		<td><bean:message key="screen.b_bac_s03.title"/></td>
	</tr>
</table>

<table class="information" cellpadding="0" cellspacing="0">
	<tr>
		<td align="right"><bean:message key="screen.b_bac_s03.customerName"/></td>
		<td align="left" style="left-padding:0px"><bean:message key="screen.b_bac_s03.colon"/></td>
		<td>
			<html:text name="_b_bac_s03Form" property="tbxCustomerName" size="40"/>
		</td>
		<td align="right"><bean:message key="screen.b_bac_s03.billAcc"/></td>
		<td align="left" style="left-padding:0px"><bean:message key="screen.b_bac_s03.colon"/></td>
		<td>
			<html:text name="_b_bac_s03Form" property="tbxBillAcc" size="40"/>
		</td>
	</tr>
	<tr>
		<td align="right"><bean:message key="screen.b_bac_s03.customerId"/></td>
		<td align="left" style="left-padding:0px"><bean:message key="screen.b_bac_s03.colon"/></td>
		<td>
			<html:text name="_b_bac_s03Form" property="tbxCustomerId" size="40"/>
		</td>
		<td width="50%" colspan="3">&nbsp;</td>
	</tr>
</table>
<table class="buttonGroup" cellpadding="0" cellspacing="0">
	<tr>
		<td>
			<button type="submit"><bean:message key="screen.b_bac_s03.btnSearch"/></button>&nbsp;	
			<button onclick="resetAct()"><bean:message key="screen.b_bac_s03.btnReset"/> </button>&nbsp;
			<button id="insertBtn" onclick="insertAct('<%=request.getContextPath()%>')" disabled="disabled"><bean:message key="screen.b_bac_s03.btnInsert"/></button>
		</td>
	</tr>
</table>
<table class="searchResultNo" cellpadding="0" cellspacing="0">
  	<tr>
		<td>
			<bean:message key="screen.b_bac_s03.searchFound"/>&nbsp;<bean:message key="screen.b_bac_s03.colon"/>
			<bean:write name="_b_bac_s03Form" property="totalCount"/>
		</td>	
	</tr>
</table>
<table class="pageLink" cellpadding="0" cellspacing="0">
	<tr>
		<td><bean:message key="screen.b_bac_s03.page_link"/>
			<bean:message key="screen.b_bac_s03.label_colon"/>
			<ts:pageLinks 
	    		id="curPageLinks"
				action="RP_B_BAC_S03SearchBL.do?pageSearch=Y" 
				name="_b_bac_s03Form" 
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
		<td class="header" width="15%" style="text-align:left;padding-left:5px"><bean:message key="screen.b_bac_s03.customerId"/></td>
		<td class="header" width="27%" style="text-align:left;padding-left:5px"><bean:message key="screen.b_bac_s03.customerName"/></td>
		<td class="header" width="19%" style="text-align:left;padding-left:5px"><bean:message key="screen.b_bac_s03.peopleSofAccNo"/></td>
		<td class="header" width="19%" style="text-align:left;padding-left:5px"><bean:message key="screen.b_bac_s03.companyRegNo"/></td>
		<td class="header" width="15%" style="text-align:left;padding-left:5px"><bean:message key="screen.b_bac_s03.billAcc"/></td>
	</tr>
	<logic:notEmpty name="_b_bac_s03Form" property="customerBeans">
	<logic:iterate id="resultBean" name="_b_bac_s03Form" property="customerBeans">
		<tr>
			<td class="defaultText" style="text-align:left;padding-left:10px"><input type="radio" name="idPlanGrp" onclick="planChecked();" value="${resultBean.ID_BILL_ACCOUNT}"/>
				<input type="hidden" name="${resultBean.ID_BILL_ACCOUNT}" value="${resultBean.ID_CUST}"/>
				<input type="hidden" name="${resultBean.ID_BILL_ACCOUNT}" value="${resultBean.CUST_NAME}"/>
			</td>
			<td class="defaultText" style="text-align:left;padding-left:5px"><bean:write name="resultBean" property="ID_CUST"/></td>
			<td class="defaultText" style="text-align:left;padding-left:5px"><bean:write name="resultBean" property="CUST_NAME"/></td>
			<td class="defaultText" style="text-align:left;padding-left:5px"><bean:write name="resultBean" property="CUST_ACC_NO"/></td>
			<td class="defaultText" style="text-align:left;padding-left:5px"><bean:write name="resultBean" property="CO_REGNO"/></td>
			<td class="defaultText" style="text-align:left;padding-left:5px"><bean:write name="resultBean" property="ID_BILL_ACCOUNT"/></td>
		</tr>
	</logic:iterate>
	</logic:notEmpty>
</table>
<div class="message">
    <ts:messages id="message" message="true"><bean:write name="message"/></ts:messages>
</div>
<div class="error">
    <html:messages id="message">
        <bean:write name="message"/><br/>
    </html:messages>
</div>
</ts:form>
</body>
</html:html>