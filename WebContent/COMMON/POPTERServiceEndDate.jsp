<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%> 
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<html>
<head>
	<base target="_self"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
	<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
	<meta content="">
	<title>POPTER</title>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/COMMON/css/popup.css"/>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
   	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   	<script type="text/javascript" src="js/popup.js"></script>
</head>
<body class="popup" onload="serviceEndDateInit();">
<ts:form action="/POPUP_COMMON_DSP">
<t:defineCodeList id="LIST_PLANSTATUS"/>
<t:defineCodeList id="LIST_BILLINGSTATUS"/>
<t:defineCodeList id="COLOR_CODE"/>
<html:hidden name="_popupCommonForm" property="serviceNo" styleId="serviceNo"/>
<html:hidden name="_popupCommonForm" property="idCustPlan" styleId="idCustPlan"/>
<html:hidden name="_popupCommonForm" property="idCustPlanGrp" styleId="idCustPlanGrp"/>
<html:hidden name="_popupCommonForm" property="svcStartDate" styleId="svcStartDate"/>
<html:hidden name="_popupCommonForm" property="serviceStatus" styleId="serviceStatus"/>
<html:hidden name="_popupCommonForm" property="billingStatus" styleId="billingStatus"/>
<html:hidden name="_popupCommonForm" property="contractPeriodType" styleId="contractPeriodType"/>
<html:hidden name="_popupCommonForm" property="contractPeriodFrom" styleId="contractPeriodFrom"/>
<html:hidden name="_popupCommonForm" property="contractPeriodTo" styleId="contractPeriodTo"/>
<html:hidden name="_popupCommonForm" property="billFrom" styleId="billFrom"/>
<html:hidden name="_popupCommonForm" property="billTo" styleId="billTo"/>
<html:hidden name="_popupCommonForm" property="billInstruct" styleId="billInstruct"/>
<html:hidden name="_popupCommonForm" property="sysdate" styleId="sysdate"/>
<html:hidden name="_popupCommonForm" property="radiusConfirmFlg" styleId="radiusConfirmFlg"/>
<html:hidden name="_popupCommonForm" property="clickRadiusYesFlg" styleId="clickRadiusYesFlg"/>
<html:hidden name="_popupCommonForm" property="resultType" styleId="resultType"/>
<html:hidden name="_popupCommonForm" property="preServiceStatus" styleId="preServiceStatus"/>
<html:hidden name="_popupCommonForm" property="isRadius" styleId="isRadius"/>

<table border="0" cellpadding="0" cellspacing="2" class="popup" style="margin-top:20px;margin-left:5px;margin-bottom:5px;margin-left:20px;">
	<tr>
		<td valign="top">
			<img src="./images/alert.png" width="32" height="32"/>
		</td>
		<td>
		    Please note that termination date cannot be earlier than<br/>
			contract period end if contract period is applied.
		</td>
	</tr>
	<tr>
		<td colspan="2" style="padding-left:20px;">
			<table cellpadding="0" cellspacing="0" border="0" style="font-family:verdana,arial,serif,sans-serif;font-size:12;width:500px;">
			    <colgroup>
					<col width="22%"/>
					<col width="38%"/>
					<col width="40%"/>
				</colgroup>
			    <tr style="height:30px;">
			        <td style="text-align:left;background-color:#8cb0f8">
			            Service <bean:write name="_popupCommonForm" property="serviceNo"/>
			        </td>
			        <td>
			            &nbsp;
			        </td>
			        <td>
			            &nbsp;
			        </td>
			    </tr>
			    <tr style="background-color: #FFFFCC;height:40px;">
			        <td style="padding-left:2px;">
			            Service End:
			        </td>
			        <td>
			            &nbsp;
			            <input type="text" name="svcEndDate" id="svcEndDate" onPropertyChange="svcEndDateChange();" readonly="readonly" style="width:100px;" value='<bean:write name="_popupCommonForm" property="svcEndDate"/>'/>
			            <t:inputCalendar for="svcEndDate" format="dd/MM/yyyy"/>
			        </td>
			        <td>
			            <div id="svcStatusDiv" class="<t:writeCodeValue codeList="COLOR_CODE" name="_popupCommonForm" property="serviceStatus"></t:writeCodeValue>" style="height:80%;padding-top:8px;margin-top: 5px;margin-bottom: 5px;margin-right: 5px;text-align: center;vertical-align: middle;">
			                Status: <label id="lblServiceStatus"><t:writeCodeValue codeList="LIST_PLANSTATUS" name="_popupCommonForm" property="serviceStatus"/></label>
			            </div>
			        </td>
			    </tr>
			    <tr style="background-color: #FFFFCC;height:40px;">
			        <td colspan="2" style="padding-left:2px;">
			            Last Billing Period:<br/>
			            From
			            <logic:empty name="_popupCommonForm" property="billFrom"> - </logic:empty>
			            <logic:notEmpty name="_popupCommonForm" property="billFrom"> <bean:write name="_popupCommonForm" property="billFrom"/> </logic:notEmpty>
			            To
			            <logic:empty name="_popupCommonForm" property="billTo"> - </logic:empty>
			            <logic:notEmpty name="_popupCommonForm" property="billTo"> <bean:write name="_popupCommonForm" property="billTo"/></logic:notEmpty>
			        </td>
			        <td style="color:#3C92C3">
			            Billing Status: <t:writeCodeValue codeList="LIST_BILLINGSTATUS" name="_popupCommonForm" property="billingStatus"/>
			        </td>
			    </tr>
			</table>
		</td>
	</tr>
	<tr style="height:10px;">
	    <td></td><td></td>
	</tr>
	<tr>
	    <td colspan="2" style="padding-left:50px;">
	       <div style="color:red;" id="warningMsg">
		   </div>
	       Are you sure you want to Save?<br/>
	    </td>
	</tr>
	<tr>
	    <td colspan="2" style="padding-left:50px;padding-top:15px;">
	       <input type="button" name="btnYes" value="Save" onclick="return serviceEndDateSaveClick();" style="width:70px;"/>
		   <input type="button" name="btnNo" value="Cancel" onclick="javascript: doPopterNo();" style="width:70px;"/>
	    </td>
	</tr>
	<tr>
	    <td colspan="2" style="padding-left:50px;padding-top:15px;">
	        <!-- message display start -->
			<div style="color:#0046D5;padding-left:20px;padding-top:0px;">
				<html:messages id="message" message="true">
					<bean:write name="message"/>
				</html:messages>
			</div>
			<div style="color:red;padding-left:20px;padding-top:0px;" id="errorInfo">
				<html:messages id="message">
					<bean:write name="message"/><br/>
				</html:messages>
			</div>
			<!-- message display end -->
	    </td>
	</tr>
</table>
<div id="ERR1SC105_CONTRACT_PERIOD" style="display:none;"><bean:message key="errors.ERR1SC105" arg0="Service end date within contract period. Penalty {0} day(s)."/></div>
<div id="ERR1SC105_BILLING_PERIOD" style="display:none;"><bean:message key="errors.ERR1SC105" arg0="Service end date is earlier than last billing period. You may need to create Credit Note. Credit note {0} day(s)."/></div>
<div id="ERR1SC105_1" style="display:none;"><bean:message key="errors.ERR1SC105" arg0="Invalid date selected. Date must be later than service start date."/></div>
<div id="ERR1SC105_2" style="display:none;"><bean:message key="errors.ERR1SC105" arg0="Invalid service end date. One time billing instruction selected. Please enter service end date."/></div>
<div id="ERR4SC019_1" style="display:none;"><bean:message key="info.ERR4SC019" arg0="You are about to reactivate a Radius Service, are you sure to reactivate?"/></div>
<div id="ERR4SC019_2" style="display:none;"><bean:message key="info.ERR4SC019" arg0="You are about to terminate a Radius Service, are you sure to terminate?"/></div>
<div id="codeColor" style="display:none;">
    <c:forEach items="${COLOR_CODE}" var="item">
        <input type="hidden" id="${item.id}" value="${item.name}"/>
    </c:forEach>
</div>
</ts:form>
</body>
</html>