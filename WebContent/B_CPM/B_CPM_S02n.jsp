<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
   		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
   		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/stylesheet/tabcontent.css"/>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>	
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/tabcontent.js"></script>
   		<script type="text/javascript" src="js/b_cpm_common.js"></script>
   		<script type="text/javascript" src="js/b_cpm_s02.js"></script>
		<title></title>
	</head>
	<body>
		<ts:form action="/B_CPM_S02EditDSP" method="POST">
		<div id="contentDiv">
		<input type="hidden" id="customerType" name="customerType"/>
		<nested:nest property="customerPlan">
			<input type="hidden" id="hiddenMsgExitConfirmation" value="<bean:message key="info.ERR4SC001"/>"/>
			<nested:hidden property="planType" styleId="planType"/>
			<nested:hidden property="idCust" styleId="idCustomer"/>
			<nested:hidden property="idPlanParam" styleId="idPlanParam"/>
			<nested:hidden property="planNameParam" styleId="planNameParam"/>
			<nested:hidden property="multiPln" styleId="multiPln"/>
		</nested:nest>
			<table class="subHeader" cellpadding="0" cellspacing="0">
	    		<tr>
	    			<td><bean:message key="screen.b_cpm.header"/></td>
	    		</tr>
	    	</table>
	    	
	    	<table style="margin-top:0.05in;background-color:#F8F8F8;border-collapse: collapse;width:100%;" cellpadding="0" cellspacing="0">
				<tr>
					<td style="width:15%;">
						 <bean:message key="screen.b_cpm.label.custName"/>&nbsp;
						 <bean:message key="screen.b_cpm.label.colon"/>
						 <button id="btnGetCustomerInfo" style="width:25px;"><img src="<%=request.getContextPath()%>/image/search.png"></button>
					</td>
					<td style="width:40%;">
					 	<div id="customerName" style="word-break:break-all;"></div>
					</td>
					<td style="width:10%;">
					 	<bean:message key="screen.b_cpm.label.custId"/>&nbsp;
					 	<bean:message key="screen.b_cpm.label.colon"/>
					</td>
					<td style="width:30%;">
					 	<div id="customerId">-</div>
					</td>
				</tr>
			</table>
		    	<table class="buttonGroup" cellpadding="0" cellspacing="0">
		    		<tr>
		    			<td>
		    				<nested:notEmpty property="ADD_STDPLN" >
		    					<button id="btnAddStandard" style="width: 180px" disabled="disabled"><bean:message key="screen.b_cpm.button.addStandardPlan"/></button>&nbsp;
		    				</nested:notEmpty>
		    				<nested:notEmpty property="ADD_STDPLN_MULTI">
		    					<button id="btnAddStandardMul" style="width: 180px" disabled="disabled"><bean:message key="screen.b_cpm.button.addStandardPlanMul"/></button>&nbsp;
		    				</nested:notEmpty>
		    				<nested:notEmpty property="ADD_NON_STDPLN">
		    					<button id="btnAddNonStandard"style="width: 180px" disabled="disabled"><bean:message key="screen.b_cpm.button.addNonStandardPlan"/></button>&nbsp;
		    				</nested:notEmpty>
		    				<button id="btnExit"><bean:message key="screen.b_cpm.button.exit"/></button>
							<button id="btnHiddenStandard" style="display:none"></button>
		    			</td>
		    		</tr>
		    	</table>	
		    </div>
		</ts:form>
	</body>
</html:html>