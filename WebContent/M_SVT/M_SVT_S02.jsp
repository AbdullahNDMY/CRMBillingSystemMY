<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheet/common.css"/>
   	<link href="${pageContext.request.contextPath}/M_SVT/css/m_svts01.css" rel="stylesheet" type="text/css" />	
	<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/common.js"></script> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/messageBox.js"></script>
	<title></title>
	<style></style>
<script type="text/javascript">
	function clickExit(){
		var MsgBox = new messageBox();
		if (MsgBox.POPEXT($('#hiddenMsgExitConfirmation').val()) == MsgBox.YES_CONFIRM) {
	    	window.close();
		}
	}
	function onSaveClick(){
		//Description Abbreviation checking, if contain "&" prompt error message ERR1SC107
		var abbrval=$("#abbrevation").val();
		var cusPo=$(this).val();
		var regexp=/[&]/;
		var message = $("#ERR4SC107").val();
		if(regexp.test(abbrval)){
			message=message.replace('{0}', "Description Abbreviation").replace('{1}', "Description Abbreviation");
			var MsgBox = new messageBox();
			MsgBox.POPALT(message);
			return false;
		}
	 	$('form').submit();
	}
	function insertAct() {
		var plan = $("#plan").val();
		var abbr = $("#abbrevation").val();
		var optionKey= $(".idService").val();
		var svcgrp=$("#svcLevel1Val").val();
		var classObjName="svcLevel3";
		window.opener.setPlanDetailOptionInfo(classObjName,optionKey,plan,svcgrp,abbr);
	}
	function initMain(){
		var isSuccessFlg=$(".isSaveFlg").val();
		if(isSuccessFlg==1){
			insertAct();
			window.opener=null;     
		    window.open('','_self');     
		    window.close();

		}
	}
</script>
</head>
<body id="b" onload="initMain();">
<ts:form action="M_SVTS02_SavePlanDetailOptionBL" styleId="AddDetailForm">
<input type="hidden" value="${pageContext.request.contextPath}" id="contextPath"/>
<input type="hidden" id="ERR4SC107" value="<bean:message key="errors.ERR1SC107" arg0="{0}" arg1="{1}"/>"/>
<input type="hidden" id="hiddenMsgExitConfirmation" value="<bean:message key="info.ERR4SC001"/>"/>
<html:hidden property="svcLevel1Val" name="frm_MSVTS01" styleId="svcLevel1Val"></html:hidden>
<html:hidden property="lblCategory" name="frm_MSVTS01" ></html:hidden>
<html:hidden property="isSaveFlg" name="frm_MSVTS01" styleClass="isSaveFlg"/>
<html:hidden property="idService" name="frm_MSVTS01" styleClass="idService"/>
<table class="subHeader" cellpadding="0" cellspacing="0">
   		<tr>
   			<td><bean:message key="screen.m_svt.title"/></td>
   		</tr>
	</table>
<div class="subCategory">
<table cellpadding="0" cellspacing="0" width="100%">
	<colgroup>
		<col width="10%"/>
		<col width="60%"/>
		<col width="30%"/>
	</colgroup>
	<tr>
		<td nowrap="nowrap"><bean:message key="screen.m_svt.svGroup"/>
			<bean:message key="screen.m_svt.label_colon"/>
		</td>
		<td class="colCategory"> 
			<bean:write property="lblCategory" name="frm_MSVTS01"/>
		</td>
		<td nowrap="nowrap" style="color:#FF0000;font:16px Calibri;margin-top:-20px;float:right;" >
			<bean:message key="screen.m_svt.descriptionAbbreviationRemark"/>
			<bean:message key="screen.m_svt.label_colon"/>
			7
		</td>
	</tr>
</table>
</div>
<!-- <div id="divServiceType" class="planService"> -->
	<div class="addPlanService">
		<table >
			<colgroup>
				<col width="75%" />
				<col width="25%" />
			</colgroup>
			<tr class="header">
				<td class="colPlan"><bean:message key="screen.m_svt.svItem"/> <font color="red">*</font></td>
				<td class="colDescAbbrSI"><bean:message key="screen.m_svt.descriptionAbbreviation" /></td>
			</tr>
			<tr>
				<td><html:text property="txtPlan" name="frm_MSVTS01" styleId="plan" maxlength="300"/></td>
				<td><html:text property="tbxDescAbbrSI" name="frm_MSVTS01" styleId="abbrevation" maxlength="10"/></td>
					</tr>
		</table>
	</div>
<!-- </div> -->

<div class="groupButton">
	<html:button property="Add" styleClass="button" value="Save" onclick="onSaveClick();"></html:button>
	<html:button property="exit" styleClass="button" value="Exit" onclick="clickExit();"></html:button>
</div>
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
</ts:form>
</body>
</html>




















