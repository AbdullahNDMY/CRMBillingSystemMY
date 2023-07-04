<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="/WEB-INF/bs" prefix="bs" %>
<%@ page import="nttdm.bsys.common.fw.BillingSystemUserValueObject" %>
<%@ page language="java" import="java.util.*" %> 
<html>
<head>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
	<link href="${pageContext.request.contextPath}/B_BAC/css/b_bac.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   	<title><bean:message key="screen.b_bac_s01.title"/></title>
   	<script type="text/javascript">
   	$(document).ready(function() {
   		if($.trim($(".message").text())==""){
		    // init
		}else{
		    var contextPath = $("#contextPath").val();
		    var billacc = $("#idBillAccount").val();
		    var fromPage = document.getElementById("fromPage").value;
		    var idCustPlan = document.getElementById("idCustPlan").value;
		    // update success 
		    window.opener.location=contextPath+"/B_BAC/RP_B_BAC_S02_02_01BL.do?idBillAccount="+billacc+"&fromPage="+fromPage+"&idCustPlan="+idCustPlan;
		    window.close();
		}
   		var termDay = $("#termDay").val();
   		if (termDay!="0")
   		{
			$("input[name= 'inputBillReferenceInfo.term']").val(termDay + " Days");
			$("input[name= 'inputBillReferenceInfo.term']").attr("disabled", true);
   		}
   		else
   		{
   			$("input[name= 'inputBillReferenceInfo.term']").attr("disabled", false);
   		}
   	});
   	function clickExit(){
		var MsgBox = new messageBox();
		if (MsgBox.POPEXT($('#hiddenMsgExitConfirmation').val()) == MsgBox.YES_CONFIRM) {
		    window.close();
		}
   	}
   	//Check sepcial words 
    function checkInputInfo(){
    	var cusPo=$("#cusPo").val();
        /* var index=cusPo.indexOf("&"); */
        var regexp=/[&]/;
        if(regexp.test(cusPo)){
        	showMsg();
        }else{
        	$('form').submit();
        }
    }
  	//show  message
	function showMsg(){
		var message = $("#ERR4SC107").val();
		message=message.replace('{0}', "Customer PO").replace('{1}', "Customer PO");
		var MsgBox = new messageBox();
		MsgBox.POPALT(message);
		return false;
	}
  	function termDayFun(){
  		var termDay = $("#termDay").val();
  			if (termDay!="0")
  	   		{
  				if(!isNaN(termDay)){
  	   			$("input[name= 'inputBillReferenceInfo.term']").val(termDay + " Days");
  	   			$("input[name= 'inputBillReferenceInfo.term']").attr("disabled", true);}
  				if(termDay.length == "0"){
  					$("input[name= 'inputBillReferenceInfo.term']").val("");
  				}
  	   		}
  	   		else
  	   		{
  	   			$("input[name= 'inputBillReferenceInfo.term']").attr("disabled", false);
  	   			$("input[name= 'inputBillReferenceInfo.term']").val("");
  	   		}
  	}
  	function termDayValue(){
  		var termDay = $("#termDay").val();
  		if(termDay.length == "0"){
			$("input[name= 'inputBillReferenceInfo.termDay']").val("0");
		}
  	}
    </script>
</head>
<body id="b" onload="initMain();">
<ts:form action="/RP_B_BAC_S02_06_02BL" styleId="editBRefForm">
<input type="hidden" value="${pageContext.request.contextPath}" id="contextPath"/>
<html:hidden property="fromPage" name="_b_bacForm" styleId="fromPage"/>
<html:hidden property="idCustPlan" name="_b_bacForm" styleId="idCustPlan"/>
<html:hidden property="idBillAccount" name="_b_bacForm" styleId="idBillAccount"/>
<input type="hidden" id="hiddenMsgExitConfirmation" value="<bean:message key="info.ERR4SC001"/>"/>
<h1 class="title"><bean:message key="screen.b_bac_s01.title"/></h1>
<div>
	<fieldset class="fieldsetPadding fieldsetBorder">
		<legend>
			<bean:message key="screen.b_bac.billingRef"/>
		</legend>
		<table cellpadding="0" cellspacing="0" width="100%">
			<col width="20%"/><col width="60%"/><col width="20%"/>
			<%-- <tr>
				<td nowrap="nowrap"><bean:message key="screen.b_bac.reference"/></td>
				<td>
                	<bean:message key="screen.b_bac.colon"/>&nbsp;
                	<html:text name="_b_bacForm" property="inputBillReferenceInfo.idBif" style="width:200px;" maxlength="20"> 
                	</html:text>
                </td>
                <td></td>
			</tr> --%>
			<tr>
				<td nowrap="nowrap">
					<bean:message key="screen.b_bac.qcsReference"/>
				</td>
				<td>
					<bean:message key="screen.b_bac.colon"/>&nbsp;
					<html:text name="_b_bacForm" property="inputBillReferenceInfo.idQcs" style="width:200px;" maxlength="20">
					</html:text>
				</td>
				<td></td>
			</tr>
			<tr>
				<td nowrap="nowrap">
					<bean:message key="screen.b_bac.quoReference"/>
				</td>
				<td>
					<bean:message key="screen.b_bac.colon"/>&nbsp;
					<html:text name="_b_bacForm" property="inputBillReferenceInfo.idQuo" style="width:200px;" maxlength="20"> 
					</html:text>
				</td>
				<td></td>
			</tr>
			<tr>
				<td nowrap="nowrap">
					<bean:message key="screen.b_bac.customerPO"/>
				</td>
				<td>
					<bean:message key="screen.b_bac.colon"/>&nbsp;
					<c:if test="${_b_bacForm.map.inputBillReferenceInfo.custPoDisp eq 'CPM'}">
						<html:text name="_b_bacForm" property="inputBillReferenceInfo.custPo" styleId="cusPo" disabled="true" maxlength="30" style="width:200px;">
						</html:text>
						<html:hidden property="inputBillReferenceInfo.custPo" name="_b_bacForm"/>
					</c:if>
					<c:if test="${_b_bacForm.map.inputBillReferenceInfo.custPoDisp eq 'BAC'}">
						<html:text name="_b_bacForm" property="inputBillReferenceInfo.custPo" styleId="cusPo" maxlength="30" style="width:200px;">
						</html:text>
					</c:if>
				</td>
				<td></td>
			</tr>
			<tr>
				<td nowrap="nowrap">
					<bean:message key="screen.b_bac.acManager"/>
				</td>
				<td>
					<bean:message key="screen.b_bac.colon"/>&nbsp; 
				    <html:select name="_b_bacForm" property="inputBillReferenceInfo.acManager" styleClass="acManager" style="width:200px;">
						<option value=""><bean:message key="screen.b_bac.blankItem"/></option>
							<c:forEach items="${_b_bacForm.map.billRefACManagerList}" var="item">
							<c:choose>
								<c:when test="${_b_bacForm.map.inputBillReferenceInfo.acManager == item.label}">
									<option value="${item.value}" selected="selected">${item.label}</option>
								</c:when>
								<c:otherwise>
									<option value="${item.value}">${item.label}</option>
								</c:otherwise>
								</c:choose>
							</c:forEach>
					</html:select>
				</td>
				<td></td>
			</tr>
			<tr>
				<td nowrap="nowrap">
					<bean:message key="screen.b_bac.term"/>
				</td>
				<td>
					<bean:message key="screen.b_bac.colon"/>&nbsp;
					<c:if test="${_b_bacForm.map.cboPaymentMethod eq 'GR' or _b_bacForm.map.cboPaymentMethod eq 'CC'}">
						<html:text name="_b_bacForm" property="inputBillReferenceInfo.termDay" disabled="true" style="width:60px;" maxlength="3" styleId="termDay"/>
						<html:hidden property="inputBillReferenceInfo.termDay"/>
					</c:if>
					<c:if test="${_b_bacForm.map.cboPaymentMethod ne 'GR' and _b_bacForm.map.cboPaymentMethod ne 'CC'}">
						<html:text name="_b_bacForm" property="inputBillReferenceInfo.termDay" style="width:60px;" maxlength="3" styleId="termDay" onkeyup="termDayFun();this.value=this.value.replace(/\D/g,'')" onblur="termDayValue()"> 
						</html:text>
					</c:if>
					<html:text name="_b_bacForm" property="inputBillReferenceInfo.term" style="width:135px;" maxlength="15">
					</html:text>
				</td>
				<td></td>
			</tr>
		</table>
	</fieldset>
	<br/>
	<html:hidden property="idBillAccount" value="${_b_bacForm.map.idBillAccount}"/>
	<input type="hidden" id="ERR4SC107" value="<bean:message key="errors.ERR1SC107" arg0="{0}" arg1="{1}"/>"/>
	<%-- <ts:submit value="Save" styleClass="button" onclick="checkInputInfo();"></ts:submit> --%>
	<html:button property="save" styleClass="button" value="Save" onclick="checkInputInfo();"></html:button>
	<html:button property="exit" styleClass="button" value="Exit" onclick="clickExit();"></html:button>
</div>
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
</html>   	