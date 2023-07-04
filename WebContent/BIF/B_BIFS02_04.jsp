<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
   	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
   	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/BIF/css/bifStyle.css"/>
   	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>			
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/BIF/js/b_bifs03.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/BIF/js/alternative.js"></script>	
	<script type="text/javascript" src="js/multifile.js"></script>
	 <script type="text/javascript">
		var MsgBox = new messageBox("<%=request.getContextPath()%>");
		var alter = new ALT();
	 	function clickDownload(id_doc){			
			//window.location.href = '<%=request.getContextPath()%>/BIF/BIFDownloadBL.do?fileName=' + filename;
			document.getElementById("id_doc").value = id_doc;
			document.forms[0].action = '<%=request.getContextPath()%>/BIF/BIFDownloadBL.do';
			document.forms[0].submit();
			//reset
			document.forms[0].action = '<%=request.getContextPath()%>/BIF/B_BIFS02_05BL.do';		
		}
	 	
	 	function clickExit(){
	 		if (MsgBox.POPEXT() == MsgBox.YES_CONFIRM) {
				document.forms[0].action = '<%=request.getContextPath()%>/BIF/B_BIFS01BL.do';
				document.forms[0].submit();
			}
		}

	 	function saveBIF(){
			var errorMess = "";
			if(document.getElementsByName("txtRequestDate")[0].value.length == 0){
				errorMess += '<bean:message key="errors.ERR1SC005" arg0="Requested Date"/> <br/>';
			}/**
			if(document.getElementsByName("txtCustomerPO")[0].value.length == 0){
				errorMess += '<bean:message key="errors.ERR1SC005" arg0="Customer PO"/> <br/>';
			}*/
			var customerType = document.getElementById("customerType").value;
		    if("CORP"==customerType) {
		    	if(document.getElementsByName("cboAttn_temp")[0].value.length == 0){
					errorMess += '<bean:message key="errors.ERR1SC005" arg0="Attn"/> <br/>';
				}
		    }
			if(document.getElementsByName("cboConsultantName")[0].value.length == 0){
				errorMess += '<bean:message key="errors.ERR1SC005" arg0="Consultant Name"/> <br/>';
			}
			//if(document.getElementsByName("cboJobNo")[0].value.length == 0){
			//	errorMess += '<bean:message key="errors.ERR1SC005" arg0="Job No"/> <br/>';
			//}
			if(errorMess != ""){
				document.getElementById("errms").innerHTML = errorMess;
			} else {
				document.getElementById("errms").innerHTML = "";
			}
			
			if(checkCusPo() || errorMess != "")
			{
				return false;
			}
			
			//remove temporary address
			if(document.getElementById("adrBA") != undefined)
				if(document.getElementById("adrBA").style.display == 'none')
					$("#adrBA").remove();
			if(document.getElementById("adrBA2") != undefined)
				if(document.getElementById("adrBA2").style.display == 'none')
					$("#adrBA2").remove();
			if(document.getElementById("adrBA3") != undefined)
				if(document.getElementById("adrBA3").style.display == 'none')
					$("#adrBA3").remove();
			if(document.getElementById("adrBA4") != undefined)
				if(document.getElementById("adrBA4").style.display == 'none')
					$("#adrBA4").remove();
			if(document.getElementById("adrCA") != undefined)
				if(document.getElementById("adrCA").style.display == 'none')
					$("#adrCA").remove();
			if(document.getElementById("adrRA") != undefined)
				if(document.getElementById("adrRA").style.display == 'none')
					$("#adrRA").remove();
			if(document.getElementById("adrTA") != undefined)
				if(document.getElementById("adrTA").style.display == 'none')
					$("#adrTA").remove();
			if(document.getElementById("adrEmpty") != undefined)
				if(document.getElementById("adrEmpty").style.display == 'none')
					$("#adrEmpty").remove();
			
			return true;
	 	}

		function doObtainVerification() {
			if (MsgBox.POPOBT("") == MsgBox.YES_CONFIRM) {
				document.forms[0].hidIsSave.value = "1";
				if(saveBIF()) {
					document.forms[0].hidAction.value = "obtain verification";
					document.forms[0].submit();	
				}
			} else {
				document.forms[0].hidIsSave.value = "0";
				document.forms[0].hidAction.value = "obtain verification";
				document.forms[0].submit();
			}
		}
		
		function doObtainApproval() {
			if (MsgBox.POPOBT("") == MsgBox.YES_CONFIRM) {
				document.forms[0].hidIsSave.value = "1";
				if(saveBIF()) {
					document.forms[0].hidAction.value = "obtain approval";
					document.forms[0].submit();	
				}
			} else {
				document.forms[0].hidIsSave.value = "0";
				document.forms[0].hidAction.value = "obtain approval";
				document.forms[0].submit();
			}
		}
		
		function onSave() {
			 if(saveBIF()) {
				document.forms[0].hidAction.value = "edit";
				document.forms[0].submit();
			} 
		}
		
		function checkCusPo(){
			var cusPo=$("#CustomerPoid").val();
			if(cusPo.indexOf("&")!=-1)
			{	
				var message = $("#ERR107").val();
				message=message.replace('{0}', "Customer PO").replace('{1}', "Customer PO");
	            var MsgBox = new messageBox();
	            MsgBox.POPALT(message);
	            return true;
			}else{
				return false;
			}
      }

		function doDelete() {
			if (MsgBox.POPDEL("") == MsgBox.YES_CONFIRM) {
				document.forms[0].hidIsSave.value = "1";
				document.forms[0].hidAction.value = "delete";
				document.forms[0].submit();
			}
		}
		
		function changeRadioCredit(value) {
			if(value == 3) {
				document.forms[0].txtOthers.style.display="";
				document.forms[0].txtOthersTady.style.display="";
				document.forms[0].txtOthers.disabled = false;
				document.forms[0].txtOthersTady.disabled = false;
			} else {
				document.forms[0].txtOthers.style.display="none";
				document.forms[0].txtOthersTady.style.display="none";
			} 
		}
		
		function termDayFun(){
			var termDay=document.forms[0].txtOthersTady.value;
			if(termDay!="0"){
				if(!isNaN(termDay)){
					document.forms[0].txtOthers.value=termDay+" Days";
					document.forms[0].txtOthers.disabled=true;
					if(termDay.length == "0"){
						document.forms[0].txtOthers.value="";
					}
				}
			}else{
				document.forms[0].txtOthers.disabled = false;
				document.forms[0].txtOthers.value="";
				}
			}
		function termDayValue(){
			var termDay=document.forms[0].txtOthersTady.value;
	  		if(termDay.length == "0"){
	  			document.forms[0].txtOthersTady.value="0";
			}
	  	}
		function openB_CPM_View(url) {
	 		var width = window.screen.width*90/100;
	 	    var height = window.screen.height*80/100;
	 	    var left = Number((screen.availWidth/2) - (width/2));
	 	    var top = Number((screen.availHeight/2) - (height/2));
	 	    var offsetFeatures = "width=" + width + ",height=" + height +
	 	    					 ",left=" + left + ",top=" + top +
	 	    					 "screenX=" + left + ",screenY=" + top;
	 		var strFeatures= "toolbar=no, status=no, menubar=no,location=no,scrollbars=yes, resizable=yes" + "," + offsetFeatures;	 	
	 		var newwindow = window.open(url, "B_BIF_S02_CPM_View", strFeatures);
	 		if (window.focus) { newwindow.focus(); }
	 	}

		/* function changeRadioCredit(value) {
			if(value == 3) {
				document.forms[0].txtOthers.disabled = false;
				document.forms[0].txtOthers.value="${bifInfo.CTTERM3_DAY}";
			} else {
				document.forms[0].txtOthers.disabled = true;
				if(value == 1){
					document.forms[0].txtOthers.value = "0";
				}else if(value == 2){
					document.forms[0].txtOthers.value = "30";
				}
			}
		} */
		
		function changeAdr(value){
			if(document.getElementById("adrBA") != undefined) {
				document.getElementById("adrBA").style.display = 'none';
			}
			
			if(document.getElementById("adrBA2") != undefined) {
				document.getElementById("adrBA2").style.display = 'none';
			}
			
			if(document.getElementById("adrBA3") != undefined) {
				document.getElementById("adrBA3").style.display = 'none';
			}
			
			if(document.getElementById("adrBA4") != undefined) {
				document.getElementById("adrBA4").style.display = 'none';
			}
			
			if(document.getElementById("adrCA") != undefined) {
				document.getElementById("adrCA").style.display = 'none';
			}
			
			if(document.getElementById("adrRA") != undefined) {
				document.getElementById("adrRA").style.display = 'none';
			}
			
			if(document.getElementById("adrTA") != undefined) {
				document.getElementById("adrTA").style.display = 'none';
			}
			
			document.getElementById("adrEmpty").style.display = 'none';
			if(document.getElementById("adr" + value) != undefined) {
				document.getElementById("adr" + value).style.display = '';
			} else {
				document.getElementById("adrEmpty").style.display = '';			
			}
			document.forms[0].cboBillingAddress.value = value;
		}
		
		function init() {
			alter.init(document.forms[0]);
			changeAdr('${bifInfo.ADR_TYPE}');
			var customerType = document.getElementById("customerType").value;
		    if("CORP"==customerType) {
		    	setTelFax();
		    }
		    var CTTERM = '${bifInfo.CTTERM}';
		    if(CTTERM != "3"){
		    	document.forms[0].txtOthers.style.display="none";
				document.forms[0].txtOthersTady.style.display="none";
		    }
		}
		function expAmtCheck(obj) {
			if(obj.checked) {
				obj.value = "1";
				setDisplayExpAmt(true);
			}
			else {
				obj.value = "0";
				setDisplayExpAmt(false);
			}
		} 
		function setDisplayExpAmt(b) {
			/*var isDisplayExpAmts = document.getElementsByTagName("label");
			for(var i = 0; i < isDisplayExpAmts.length; i++) {
				if(b)
					isDisplayExpAmts[i].style.display = "";
				else
					isDisplayExpAmts[i].style.display = "none";
			}*/
		}
		function approveAlt() {
			var messageAlt = document.getElementById("messageAlt");
			if(messageAlt && messageAlt.innerHTML) {
				var msgBox = new messageBox();
				msgBox.POPALT(messageAlt.innerHTML);
			}
		}
		function mbpInOneCheck(obj){
			if(obj.checked){
				obj.value = "1";
			}
			else{
				obj.value="0";
			}
		}
		function emailCheck(obj){
			if(obj.checked){
				obj.value = "1";
			}
			else{
				obj.value="0";
			}
		}
	 </script>
<title>Edit</title>
</head>
<body  onload="javascript:init();approveAlt();" onmousemove="alter.changePosition(event);">
	<t:defineCodeList id="LIST_COUNTRY"/>
	<ts:form action="B_BIFS02_05BL" enctype="multipart/form-data">
	<input type="hidden" value="${bifInfo.ID_REF}" name="idRef"/>
	<input type="hidden" value="${bifInfo.BIF_TYPE}" name="bifType"/>
	<html:hidden property="hidAction"/>
	<html:hidden property="hidIsSave" value="0"/>
	<html:hidden property="hidIs_ObtainA" value="${screenObject.is_ObtainA}"/>
	<html:hidden property="hidIs_ObtainV" value="${screenObject.is_ObtainV}"/>
	<input type="hidden" name="fileName" id="fileName"/>
	<input type="hidden" name="id_doc" id="id_doc"/>
	<input type="hidden" name="customerType" id="customerType" value="${cusInfo.CUSTOMER_TYPE}"/>
	<input type="hidden" name="telFaxContactFlg" id="telFaxContactFlg" value="${bifInfo.telFaxContactFlg}"/>
	<table class="subHeader" cellpadding="0" cellspacing="0">
	  <tr style="">
	    <td class="Title">
	    <bean:message key="screen.b_bif.billingInstructionForm"/>
	    <bean:message key="screen.b_bif._"/>
	    <t:writeCodeValue codeList="LIST_TRANSACTIONTYPE" key="${bifInfo.BIF_TYPE}"></t:writeCodeValue>
	    </td> 
	  </tr>
	</table>
	<table>
		<tr><td height="3px" /></tr>
	</table>
	<table cellpadding="0" cellspacing="2" width="100%" >
	 <tr>
	  <td width="40%" style="background-color: #EAEAEA;">
	  	<table class="subHeaderInner">
		  	 <tr>
		  	  <td class="tdleftHeader"><b><bean:message key="screen.b_bif.customerDetails"/></b>
		  	  </td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif.customerName"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">${cusInfo.CUST_NAME}<input type="hidden" name="custName" value="${cusInfo.CUST_NAME}"/></td>
		  	 </tr>
		  	 <tr><td colspan="2">&nbsp;</td></tr>
		  	 
		  	 <tr>
		  	 	<t:defineCodeList id="LIST_ADDRESS"/>
		  	 	<td class = "tdleft" valign="top">
		  	 		<c:choose>
			  	 		<c:when test="${bifInfo.IS_BAC_EXIST eq '1'}">
			  	 			<html:select property="cboBillingAddress" value="${bifInfo.ADR_TYPE}" disabled="true">
				  	 			<html:optionsCollection name="LIST_ADDRESS" value="id" label="name" />
				  	 		</html:select>
				  	 		<html:hidden property="cboBillingAddress" value="${bifInfo.ADR_TYPE}"/>
			  	 		</c:when>
			  	 		<c:otherwise>
			  	 			<html:select property="cboBillingAddress" onchange="changeAdr(forms[0].cboBillingAddress.value)">
				  	 			<html:optionsCollection name="LIST_ADDRESS" value="id" label="name" />
				  	 		</html:select>
			  	 		</c:otherwise>
		  	 		</c:choose>
		  	 	</td>
		  	 	<td class = "tdright">
	  	 			<logic:iterate id="item" name="cusAdr" scope="request">
		  	 		<table id='adr<bean:write name="item" property="ADR_TYPE"/>'>
		  	 			<tr>
					  	 	<td class = "tdright">
					  	 		<bean:write name="item" property="ADR_LINE1"/>
					  	 		<input type="hidden" name="addr1" value="<bean:write name="item" property="ADR_LINE1"/>"/>
					  	 	</td>
					  	 </tr>
					 	 <tr>
					  	 	<td class = "tdright">
					  	 		<bean:write name="item" property="ADR_LINE2"/>
					  	 		<input type="hidden" name="addr2" value="<bean:write name="item" property="ADR_LINE2"/>"/>
					  	 	</td>
					  	 </tr>
					  	 <tr>
					  	 	<td class = "tdright">
					  	 		<bean:write name="item" property="ADR_LINE3"/>
					  	 		<input type="hidden" name="addr3" value="<bean:write name="item" property="ADR_LINE3"/>"/>
					  	 	</td>
					  	 </tr>
					  	 <tr>
					  	 	<td class = "tdright">
					  	 		<c:if test="${not empty item.ZIP_CODE or not empty item.COUNTRY}">
					  	 		<bean:write name="item" property="ZIP_CODE"/>,<t:writeCodeValue codeList="LIST_COUNTRY" key="${item.COUNTRY}"/>
					  	 		</c:if>
					  	 		<input type="hidden" name="zipCode" value="<bean:write name="item" property="ZIP_CODE"/>"/>
					  	 		<input type="hidden" name="country" value="<t:writeCodeValue codeList="LIST_COUNTRY" key="${item.COUNTRY}"/>"/>
					  	 		<input type="hidden" name="countryCd" value="<bean:write name="item" property="COUNTRY"/>"/>
					  	 	</td>
					  	 </tr>
		  	 		</table>
		  	 		</logic:iterate>
		  	 		<table id='adrEmpty'>
		  	 			<tr>
					  	 	<td class = "tdright">&nbsp;</td>
					  	 </tr>
					 	 <tr>
					  	 	<td class = "tdright">&nbsp;</td>
					  	 </tr>
					  	 <tr>
					  	 	<td class = "tdright">&nbsp;</td>
					  	 </tr>
					  	 <tr>
					  	 	<td class = "tdright">&nbsp;</td>
					  	 </tr>
		  	 		</table>
		  	 	</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "tdleft" style="text-align:right"><bean:message key="screen.b_bif.tel"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">
		  	 	<!--  	${cusInfo.CO_TEL_NO}
		  	 		<input type="hidden" name="tel" value="${cusInfo.CO_TEL_NO}"/>  
		  	 	-->
		  	 		<c:if test="${cusInfo.CUSTOMER_TYPE eq 'CORP'}">
		  	 		    <label id="telnumber">${cusInfo.CO_TEL_NO}</label>
		  	 		    <input type="hidden" name="tel" id="tel" value="${cusInfo.CO_TEL_NO}"/>
		  	 		</c:if>
		  	 		<c:if test="${cusInfo.CUSTOMER_TYPE eq 'CONS'}">
		  	 		    ${cusInfo.HOME_TEL_NO}
		  	 		    <input type="hidden" name="tel" id="tel" value="${cusInfo.HOME_TEL_NO}"/>
		  	 		</c:if>
		  	 	</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "tdleft" style="text-align:right"><bean:message key="screen.b_bif.fax"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">
		  	 	<!--  	${cusInfo.CO_FAX_NO}
		  	 		<input type="hidden" name="fax" value="${cusInfo.CO_FAX_NO}"/>
		  	 	-->
		  	 		<c:if test="${cusInfo.CUSTOMER_TYPE eq 'CORP'}">
		  	 		    <label id="faxnumber">${cusInfo.CO_FAX_NO}</label>
		  	 		    <input type="hidden" name="fax" id="fax" value="${cusInfo.CO_FAX_NO}"/>
		  	 		</c:if>
		  	 		<c:if test="${cusInfo.CUSTOMER_TYPE eq 'CONS'}">
		  	 		    ${cusInfo.HOME_FAX_NO}
		  	 		    <input type="hidden" name="fax" id="fax" value="${cusInfo.HOME_FAX_NO}"/>
		  	 		</c:if>
		  	 	</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "tdleft" style="text-align:right"><bean:message key="screen.b_bif.attn"/>
		  	 		<c:if test="${cusInfo.CUSTOMER_TYPE eq 'CORP'}">
		  	 			<span class="mandatory"><bean:message key="screen.b_bif.label_mandatory"/></span>
		  	 		</c:if>
		  	 		<bean:message key="screen.b_bif.colon"/>
		  	 	</td>
		  	 	<td class = "tdright">
		  	 		
		  	 		<c:if test="${cusInfo.CUSTOMER_TYPE eq 'CORP'}">
			  	 		<c:choose>
				  	 		<c:when test="${bifInfo.IS_BAC_EXIST eq '1'}">
				  	 			<select name="cboAttn_temp" onchange="setTelFax();" disabled="true">
					  	 			<option value=""><bean:message key="screen.b_bif02.selectone"/></option>
					  	 			<c:forEach items="${attns}" var="item">
					  	 				<c:if test="${bifInfo.CONTACT_TYPE == item.CONTACT_TYPE}">
					  	 					<option value="${item.CONTACT_TYPE}" selected="selected" contact_name="${item.CONTACT_NAME_VALUE}" tel_no="${item.TEL_NO}" fax_no="${item.FAX_NO}" email_to="${item.EMAIL}" email_cc="${item.EMAIL_CC}">${item.CONTACT_NAME}</option>
					  	 				</c:if>
					  	 				<c:if test="${bifInfo.CONTACT_TYPE != item.CONTACT_TYPE}">
					  	 					<option value="${item.CONTACT_TYPE}" contact_name="${item.CONTACT_NAME_VALUE}" tel_no="${item.TEL_NO}" fax_no="${item.FAX_NO}" email_to="${item.EMAIL}" email_cc="${item.EMAIL_CC}">${item.CONTACT_NAME}</option>
					  	 				</c:if>
					  	 			</c:forEach>
					  	 		</select>
					  	 		<c:forEach items="${attns}" var="item2">
				  	 				<c:if test="${bifInfo.CONTACT_TYPE == item2.CONTACT_TYPE}">
				  	 					<html:hidden property="cboAttn" value="${item2.CONTACT_NAME_VALUE}"/>
				  	 				</c:if>
				  	 			</c:forEach>
					  	 		<input type="hidden" name="cboAttn_temp" value="${bifInfo.CONTACT_TYPE}"/>
				  	 		</c:when>
				  	 		<c:otherwise>
				  	 			<select name="cboAttn_temp" onchange="setTelFax();">
					  	 			<option value=""><bean:message key="screen.b_bif02.selectone"/></option>
					  	 			<c:forEach items="${attns}" var="item">
					  	 				<c:if test="${bifInfo.CONTACT_TYPE == item.CONTACT_TYPE}">
					  	 					<option value="${item.CONTACT_TYPE}" selected="selected" contact_name="${item.CONTACT_NAME_VALUE}" tel_no="${item.TEL_NO}" fax_no="${item.FAX_NO}" email_to="${item.EMAIL}" email_cc="${item.EMAIL_CC}">${item.CONTACT_NAME}</option>
					  	 				</c:if>
					  	 				<c:if test="${bifInfo.CONTACT_TYPE != item.CONTACT_TYPE}">
					  	 					<option value="${item.CONTACT_TYPE}" contact_name="${item.CONTACT_NAME_VALUE}" tel_no="${item.TEL_NO}" fax_no="${item.FAX_NO}" email_to="${item.EMAIL}" email_cc="${item.EMAIL_CC}">${item.CONTACT_NAME}</option>
					  	 				</c:if>
					  	 			</c:forEach>
					  	 		</select>
					  	 		<c:forEach items="${attns}" var="item3">
				  	 				<c:if test="${bifInfo.CONTACT_TYPE == item3.CONTACT_TYPE}">
				  	 					<html:hidden property="cboAttn" value="${item3.CONTACT_NAME_VALUE}"/>
				  	 				</c:if>
				  	 			</c:forEach>
				  	 		</c:otherwise>
			  	 		</c:choose>
		  	 		</c:if>
		  	 		<c:if test="${cusInfo.CUSTOMER_TYPE eq 'CONS'}">
			  	 	    <select name="cboAttn_temp" disabled="disabled">
			  	 			<option value=""><bean:message key="screen.b_bif02.selectone"/></option>
			  	 		</select>
			  	 	</c:if>
		  	 	</td>
		  	 </tr>
		  	 <tr>
		  	 	<td style="text-align:right">
		  	 		<bean:message key="screen.b_bif.email_to"/><bean:message key="screen.b_bif.colon"/>
		  	 	</td>
		  	 	<td>
		  	 		<c:if test="${cusInfo.CUSTOMER_TYPE eq 'CORP'}">
		  	 			<label id="emailto">
		  	 			${bifInfo.CONTACT_EMAIL}
		  	 			</label>
		  	 			<html:hidden property="emailToAdd"/>
		  	 		</c:if>
		  	 		
		  	 	</td>
		  	 </tr>
		  	 <tr>
		  	 	<td style="text-align:right">
		  	 		<bean:message key="screen.b_bif.email_cc"/><bean:message key="screen.b_bif.colon"/>
		  	 	</td>
		  	 	<td>
		  	 		<c:if test="${cusInfo.CUSTOMER_TYPE eq 'CORP'}">
		  	 			<label id="emailcc">
		  	 				${bifInfo.CONTACT_EMAIL_CC}
		  	 			</label>
		  	 			<html:hidden property="emailCcAdd" value=""/>
		  	 		</c:if>
		  	 	</td>
		  	 </tr>
	  	</table>
	  </td>
	  <td width="40%" style="background-color: #EAEAEA;">
	  	<table class="subHeaderInner"> 
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif.bifReference"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">
		  	 		${bifForm.map.idRef}
		  	 	</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif.requestedDate"/>&nbsp;<span class="mandatory"><bean:message key="screen.b_bif.label_mandatory"/></span><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright"><input type="text" name="txtRequestDate" value="<fmt:formatDate value="${bifInfo.DATE_REQ}" pattern="dd/MM/yyyy"/>" class="DateTextBox" readonly="readonly"/><t:inputCalendar for="txtRequestDate" format="dd/MM/yyyy"/></td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif.qcsReference"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">
		  	 		<html:text property="cboQCSReference" value="${bifInfo.ID_QCS}" maxlength="20"/>
		  	 	</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif.quoReference"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">
		  	 		<html:text property="cboQuoReference" value="${bifInfo.ID_QUO}" maxlength="20"/>
		  	 	</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif.customerPO"/>&nbsp;<bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright"><input id="CustomerPoid" name="txtCustomerPO" maxlength="30" value="${bifInfo.CUST_PO}"/></td>		  	 	
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif.consultantName"/>&nbsp;<span class="mandatory"><bean:message key="screen.b_bif.label_mandatory"/></span><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">
		  	 		<html:hidden property="preCboConsultantName" value="${bifInfo.ID_CONSLT}"/>
		  	 		<select name="cboConsultantName">
		  	 			<option value=""><bean:message key="screen.b_bif02.selectone"/></option>
		  	 			<c:forEach items="${colsNames}" var="item">
		  	 				<c:if test="${bifInfo.ID_CONSLT == item.ID_USER}">	
		  	 					<option value="${item.ID_USER}" selected="selected">${item.USER_NAME}</option>
		  	 				</c:if>
		  	 				<c:if test="${bifInfo.ID_CONSLT != item.ID_USER}">	
		  	 					<option value="${item.ID_USER}">${item.USER_NAME}</option>
		  	 				</c:if>
		  	 			</c:forEach>
		  	 		</select>
		  	 	</td>
		  	 </tr>
		  	 <%--
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif.jobNo"/>&nbsp;<span class="mandatory"><bean:message key="screen.b_bif.label_mandatory"/></span><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">
		  	 		<select name="cboJobNo">
		  	 			<option value=""><bean:message key="screen.b_bif02.selectone"/></option>
		  	 			<c:forEach items="${jobNos}" var="item">
		  	 				<c:if test="${bifInfo.JOB_NO == item.ID_JOBNO}">	
		  	 					<option value="${item.ID_JOBNO}" selected="selected">${item.ID_JOBNO}</option>
		  	 				</c:if>
		  	 				<c:if test="${bifInfo.JOB_NO != item.ID_JOBNO}">	
		  	 					<option value="${item.ID_JOBNO}">${item.ID_JOBNO}</option>
		  	 				</c:if>
		  	 			</c:forEach>
		  	 		</select>
		  	 	</td>
		  	 </tr>
		  	 --%>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif.newCustomer"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">
		  	 		<c:if test="${bifInfo.CUST_MODE == '1'}">
		  	 			<input type="checkbox" name="chkNewCustomer" value="1" checked="checked" />
		  	 		</c:if>
		  	 		<c:if test="${bifInfo.CUST_MODE != '1'}">
		  	 			<input type="checkbox" name="chkNewCustomer" value="1"/>
		  	 		</c:if>
		  	 	</td>
		  	 </tr> 
	  	</table>
		</td>
	  <td width="18%" valign="top" class="<t:writeCodeValue codeList="COLOR_CODE" key="${bifInfo.ID_STATUS }"></t:writeCodeValue>">
	  	<table class="<t:writeCodeValue codeList="COLOR_CODE" key="${bifInfo.ID_STATUS }"></t:writeCodeValue>">
	  		<tr>
	  			<td align="center">
	  			 <bean:message key="screen.b_bif.status"/>
	  			</td>
	  		</tr>
	  		<tr>
	  			<td align="center">
	  			<t:writeCodeValue codeList="DOC_STATUS_LIST" key="${bifInfo.ID_STATUS }"></t:writeCodeValue>
	  			</td>
	  		</tr>
	  		<tr>
	  			<td align="center">
	  			 <fmt:formatDate value="${bifInfo.DATE_UPDATED}" pattern="dd/MM/yyyy"/>
	  			</td>
	  		</tr>	  		
	  	</table>
	  </td>
	 </tr>
	</table>
	
	<!-- Credit term approved -->	
	<table>
		<tr><td height="0px" /></tr>
	</table>
	<table class="CreaditTerm" style="border: 1px solid #C2C0B5;background-color: #EAEAEA;" width="100%">
	 <tr>
	 <c:choose>
	 	<c:when test="${bifInfo.IS_BAC_EXIST eq '1'}">
	  <td><bean:message key="screen.b_bif.creaditTermsApproved"/></td>
	  <td>
	  	<bean:message key="screen.b_bif.colon"/>
	  	<c:if test="${bifInfo.CTTERM == '1'}">
	  		<input type="radio" value="1" name="rdbCreditTermAp" checked="checked" onclick="changeRadioCredit(1);"/>
	  		<bean:message key="screen.b_bif.cod"/>
	  	</c:if>
	  	<c:if test="${bifInfo.CTTERM != '1'}">
	  		<input type="radio" value="1" name="rdbCreditTermAp" onclick="changeRadioCredit(1);" disabled="disabled"/>
	  		<bean:message key="screen.b_bif.cod"/>
	  	</c:if>	  		
	  </td>
	  <td>
	  	<c:if test="${bifInfo.CTTERM == '2'}">
	  		<input type="radio" name="rdbCreditTermAp" value="2" checked="checked" onclick="changeRadioCredit(2);"/>
	  		<bean:message key="screen.b_bif.30Days"/>
	  	</c:if>
	  	<c:if test="${bifInfo.CTTERM != '2'}">
	  		<input type="radio" name="rdbCreditTermAp" value="2" onclick="changeRadioCredit(2);" disabled="disabled"/>
	  		<bean:message key="screen.b_bif.30Days"/>
	  	</c:if>
	  </td>
	  <td colspan="3">
	  	<c:if test="${bifInfo.CTTERM == '3'}">
	  		<input type="radio" name="rdbCreditTermAp" value="3" checked="checked" onclick="changeRadioCredit(3);"/>
	  		<bean:message key="screen.b_bif.others"/><bean:message key="screen.b_bif.colon"/>
	  		${bifInfo.CTTERM3_DAY}
	  		<%-- <input type="text" name="txtOthersTady" maxlength="3" value="${bifInfo.TERM_DAY}" style="width:60px;" onkeyup="termDayFun();this.value=this.value.replace(/\D/g,'')" onblur="termDayValue()" disabled="disabled"/>
	  		<input type="text" name="txtOthers" maxlength="15" value="${bifInfo.CTTERM3_DAY}" style="width:135px;" disabled="disabled"/> --%>
	  	</c:if>
	  	<c:if test="${bifInfo.CTTERM != '3'}">
	  		<input type="radio" name="rdbCreditTermAp" value="3" onclick="changeRadioCredit(3);" disabled="disabled"/>
	  		<bean:message key="screen.b_bif.others"/><bean:message key="screen.b_bif.colon"/>
	  	</c:if>
	  </td>
	  <input type="hidden" name="rdbCreditTermAp" value="${bifInfo.CTTERM}">
	  <input type="hidden" name="txtOthersTady" value="${bifInfo.TERM_DAY}">
	  <input type="hidden" name="txtOthers" value="${bifInfo.CTTERM3_DAY}">
	  </c:when>
	  <c:otherwise>
	  <td><bean:message key="screen.b_bif.creaditTermsApproved"/></td>
	  <td style="height:30px">
	  	<bean:message key="screen.b_bif.colon"/>
	  	<c:if test="${bifInfo.CTTERM == '1'}">
	  		<input type="radio" value="1" name="rdbCreditTermAp" checked="checked" onclick="changeRadioCredit(1);"/>
	  		<bean:message key="screen.b_bif.cod"/>
	  	</c:if>
	  	<c:if test="${bifInfo.CTTERM != '1'}">
	  		<input type="radio" value="1" name="rdbCreditTermAp" onclick="changeRadioCredit(1);"/>
	  		<bean:message key="screen.b_bif.cod"/>
	  	</c:if>	  		
	  </td>
	  <td style="height:30px">
	  	<c:if test="${bifInfo.CTTERM == '2'}">
	  		<input type="radio" name="rdbCreditTermAp" value="2" checked="checked" onclick="changeRadioCredit(2);"/>
	  		<bean:message key="screen.b_bif.30Days"/>
	  	</c:if>
	  	<c:if test="${bifInfo.CTTERM != '2'}">
	  		<input type="radio" name="rdbCreditTermAp" value="2" onclick="changeRadioCredit(2);"/>
	  		<bean:message key="screen.b_bif.30Days"/>
	  	</c:if>
	  </td>
	  <td colspan="3" style="height:30px">
	  	<c:if test="${bifInfo.CTTERM == '3'}">
	  		<input type="radio" name="rdbCreditTermAp" value="3" checked="checked" onclick="changeRadioCredit(3);"/>
	  		<bean:message key="screen.b_bif.others"/><bean:message key="screen.b_bif.colon"/>
	  		<input type="text" name="txtOthersTady" maxlength="3" value="${bifInfo.TERM_DAY}" style="width:60px;" onkeyup="termDayFun();this.value=this.value.replace(/\D/g,'')" onblur="termDayValue()"/>
	  		<input type="text" name="txtOthers" maxlength="15" value="${bifInfo.CTTERM3_DAY}" style="width:135px;"/>
	  	</c:if>
	  	<c:if test="${bifInfo.CTTERM != '3'}">
	  		<input type="radio" name="rdbCreditTermAp" value="3" onclick="changeRadioCredit(3);"/>
	  		<bean:message key="screen.b_bif.others"/><bean:message key="screen.b_bif.colon"/>
	  		<c:if test="${bifInfo.CTTERM == '1'}">
	  		<input type="text" name="txtOthersTady" maxlength="3" value="0" style="width:60px;" disabled="disabled" onkeyup="termDayFun();this.value=this.value.replace(/\D/g,'')" onblur="termDayValue()"/>
	  		<input type="text" name="txtOthers" maxlength="15" disabled="disabled" value="COD" style="width:60px;"/>
	  		</c:if>
	  		<c:if test="${bifInfo.CTTERM == '2'}">
	  		<input type="text" name="txtOthersTady" maxlength="3" value="30" style="width:60px;" disabled="disabled" onkeyup="termDayFun();this.value=this.value.replace(/\D/g,'')" onblur="termDayValue()"/>
	  		<input type="text" name="txtOthers" maxlength="15" disabled="disabled" value="<bean:message key="screen.b_bif.30Days"/>" style="width:60px;"/>
	  		</c:if>
	  	</c:if>
	  </td>
	  </c:otherwise>
	  </c:choose>
	 </tr>
	 <tr>
	  <c:choose>
            <c:when test="${bifInfo.IS_BAC_EXIST eq '1'}">
            	<td width="20%"><bean:message key="screen.b_bif.documentdelivery"/></td>
				<td width="15%">
					<bean:message key="screen.b_bif.colon"/>
					<c:if test="${bifInfo.DELIVERY_EMAIL == 1}">
						<input type="checkbox" name="checkDeliveryEmail" value="1" checked disabled="disabled"/>
					</c:if>
					<c:if test="${bifInfo.DELIVERY_EMAIL != 1}">
						<input type="checkbox" name="checkDeliveryEmail" value="0" disabled="disabled"/>
					</c:if>
					<bean:message key="screen.b_bif.email"/>
				</td>
				<td width="15%">
					<c:if test="${bifInfo.DELIVERY == '3'}">
						<input type="radio" name="rdbInstructionf" value="3" checked="checked"/>
						<bean:message key="screen.b_bif.none"/>
					</c:if>
					<c:if test="${bifInfo.DELIVERY != '3'}">
						<input type="radio" name="rdbInstructionf" value="3" disabled="disabled"/>
						<bean:message key="screen.b_bif.none"/>
					</c:if>	  	
				</td>
				<td width="15%">
					<c:if test="${bifInfo.DELIVERY == '1'}">
						<input type="radio" name="rdbInstructionf" value="1" checked="checked"/>
						<bean:message key="screen.b_bif.post"/>
					</c:if>
					<c:if test="${bifInfo.DELIVERY != '1'}">
						<input type="radio" name="rdbInstructionf" value="1" disabled="disabled"/>
						<bean:message key="screen.b_bif.post"/>
					</c:if>
				</td>
				<td width="15%">
					<c:if test="${bifInfo.DELIVERY == '2'}">
						<input type="radio" name="rdbInstructionf" value="2" checked="checked"/>
						<bean:message key="screen.b_bif.courier"/>
					</c:if>
					<c:if test="${bifInfo.DELIVERY != '2'}">
						<input type="radio" name="rdbInstructionf" value="2" disabled="disabled"/>
						<bean:message key="screen.b_bif.courier"/>
					</c:if>
				</td>
				<td width="20%">
					<c:if test="${bifInfo.DELIVERY == '4'}">
						<input type="radio" name="rdbInstructionf" value="4" checked="checked"/>
						<bean:message key="screen.b_bif.byHand"/>	  
					</c:if>
					<c:if test="${bifInfo.DELIVERY != '4'}">
						<input type="radio" name="rdbInstructionf" value="4" disabled="disabled"/>
						<bean:message key="screen.b_bif.byHand"/>
					</c:if>
				</td>
            </c:when>
            <c:otherwise>
				<td width="20%"><bean:message key="screen.b_bif.documentdelivery"/></td>
				<td width="15%">
					<bean:message key="screen.b_bif.colon"/>
					<c:if test="${bifInfo.DELIVERY_EMAIL == 1}">
						<input type="checkbox" name="checkDeliveryEmail" value="1" checked onclick="emailCheck(this);">
					</c:if>
					<c:if test="${bifInfo.DELIVERY_EMAIL != 1}">
						<input type="checkbox" name="checkDeliveryEmail" value="0" onclick="emailCheck(this);">
					</c:if>
					<bean:message key="screen.b_bif.email"/>
				</td>
				<td width="15%">
					<c:if test="${bifInfo.DELIVERY == '3'}">
						<input type="radio" name="rdbInstructionf" value="3" checked="checked"/>
						<bean:message key="screen.b_bif.none"/>
					</c:if>
					<c:if test="${bifInfo.DELIVERY != '3'}">
						<input type="radio" name="rdbInstructionf" value="3"/>
						<bean:message key="screen.b_bif.none"/>
					</c:if>	  	
				</td>
				<td width="15%">
					<c:if test="${bifInfo.DELIVERY == '1'}">
						<input type="radio" name="rdbInstructionf" value="1" checked="checked"/>
						<bean:message key="screen.b_bif.post"/>
					</c:if>
					<c:if test="${bifInfo.DELIVERY != '1'}">
						<input type="radio" name="rdbInstructionf" value="1"/>
						<bean:message key="screen.b_bif.post"/>
					</c:if>
				</td>
				<td width="15%">
					<c:if test="${bifInfo.DELIVERY == '2'}">
						<input type="radio" name="rdbInstructionf" value="2" checked="checked"/>
						<bean:message key="screen.b_bif.courier"/>
					</c:if>
					<c:if test="${bifInfo.DELIVERY != '2'}">
						<input type="radio" name="rdbInstructionf" value="2" />
						<bean:message key="screen.b_bif.courier"/>
					</c:if>
				</td>
				<td width="20%">
					<c:if test="${bifInfo.DELIVERY == '4'}">
						<input type="radio" name="rdbInstructionf" value="4" checked="checked"/>
						<bean:message key="screen.b_bif.byHand"/>	  
					</c:if>
					<c:if test="${bifInfo.DELIVERY != '4'}">
						<input type="radio" name="rdbInstructionf" value="4"/>
						<bean:message key="screen.b_bif.byHand"/>
					</c:if>
				</td> 
		</c:otherwise>
	</c:choose>
	 </tr>
	 <tr>
	  <td><bean:message key="screen.b_bif.remarks"/></td>
	  <td colspan="5">
	  	<bean:message key="screen.b_bif.colon"/><input type="text" name="txtRemarks" maxlength="150" size="85" value="${bifInfo.REMARKS}" />
	  </td> 
	 </tr>
	</table>
	<!-- Credit term approved -->
	
	<!-- Attachment -->	
	<table cellspacing="0" width="100%" class="AttachmentTbl" style="background-color: #F7F7F4;border: 1px solid #C2C0B5;border-top: 1px solid #F7F7F4;">
	 <colgroup>
	 	<col width="10%"/>
	 	<col width="30%"/>
	 	<col width="30%"/>
	 	<col width="30%"/>
	 </colgroup>
	 <tr>
	 	<td class="header"><b><bean:message key="screen.b_bif.attachment"/></b></td>
	 	<td class="header">
	 		<span style="background-color: #C0C0C0;float:left;"><bean:message key="screen.b_bif.signedContract"/></span>
	 		<div class="uploadBound">
		 		<input type="button" class=uploadButton id="fakeSC" name="pseudobutton" value="<bean:message key="screen.b_bif.uploadFile"/>" >
		 		<input id="bifSC_file" type="file" name="bifSC_file" class="uploadFile" />
	 		</div>
	 	</td>	 	
	 	<td class="header">
	 		<span style="background-color: #C0C0C0;float:left;"><bean:message key="screen.b_bif.attQcsPmr"/></span>
	 		<div class="uploadBound">
		 		<input type="button" class="uploadButton" id="fakeQP" name="pseudobutton" value="<bean:message key="screen.b_bif.uploadFile"/>" >
		 		<input id="bifQP_file" type="file" name="bifQP_file" class="uploadFile" />
	 		</div>
	 	</td>	 	
	 	<td class="header">
	 		<span style="background-color: #C0C0C0;float:left;"><bean:message key="screen.b_bif.AttOther"/></span>
	 		<div class="uploadBound">
		 		<input type="button" class="uploadButton" id="fakeOT" name="pseudobutton" value="<bean:message key="screen.b_bif.uploadFile"/>" >
		 		<input id="bifOT_file" type="file" name="bifOT_file" class="uploadFile" />
	 		</div>
	 	</td>	 	
	 </tr>
	 <tr>
	 	<td>&nbsp;</td>
	 	<td id="attSC" valign="top">
	 		<table id="attachmentSCBif" style="border:0;width:100%;font-size:15px;">	 		
	 		</table>
	 	</td>	 	
	 	<td id="attQP" valign="top">
	 		<table id="attachmentQPBif" style="border:0;width:100%;font-size:15px;">	 		
	 		</table>
	 	</td>	 	
	 	<td id="attOT" valign="top">
	 		<table id="attachmentOTBif" style="border:0;width:100%;font-size:15px;">	 		
	 		</table>
		</td>	 	
	 </tr>
	</table>
	<!-- Attachment -->	
	
	<!-- Plans -->
	<table>
		<tr><td height="0px" /></tr>
	</table>
	<table class="inputInfo" cellpadding="2" cellspacing="0" style="border: 1px solid #A8C0FB;" width="100%">
	 <tr style="background-color: #8CB0F8;">
	  	  <td width="5%"><b><bean:message key="screen.b_bif.item"/></b></td>
	  	  <td width="30%"><b><bean:message key="screen.b_bif.billingDescription"/></b></td>
	  	  <td width="30%" class="colCenter"><b>
	  	  <bean:message key="screen.b_bif.billingCurr"/>
	  	  <bean:message key="screen.b_bif.colon"/>
	  	  ${totalPlan.currency}
	  	  </b></td>
	  	  <td width="10%" class="colRight"><b><bean:message key="screen.b_bif.qty"/></b></td>
	  	  <td width="10%" class="colRight"><b><bean:message key="screen.b_bif.unitPrice"/></b></td>
	  	  <td width="10%" class="colRight"><b><bean:message key="screen.b_bif.amount"/></b></td>
	      <c:if test="${'0'eq taxType}">
	      <td width="10%" class="colCenter"><b><bean:message key="screen.b_bif.gst"/></b></td>
	      </c:if>
	      <c:if test="${'1'eq taxType}">
	      <td width="10%" class="colCenter"><b>${taxStr}</b></td>
	      </c:if>
	      <td width="3%">&nbsp;</td>
	 </tr>
	 <nested:nest property="customerPlan">
	     <nested:present property="serviceList">
		     <nested:iterate property="serviceList" id="service">
			     <tr>
			         <%-- check with lump sum --%>
			         <nested:equal value="1" property="isLumpSum">
			             <td valign="top"><nested:write property="itemNo"/></td>
			             <td colspan="2">
				 			<a href="#" onclick="openB_CPM_View('${pageContext.request.contextPath}/B_CPM/B_CPM_S05InitBL.do?customerPlan.idCustPlan=${service.idCustPlan}&customerPlan.fromScreen=BIF&customerPlan.billType=${bifInfo.BIF_TYPE}')">
				 				<pre><nested:write property="itemDesc"/></pre>
				 			</a>
			 			</td>
			 			<td class="colRight" valign="top"><fmt:formatNumber value="${service.itemQty}" pattern="#,##0"/></td>
			 			<td class="colRight" valign="top"><fmt:formatNumber value="${service.itemUprice}" pattern="#,##0.00"/></td>
			 			<td class="colRight" valign="top"><fmt:formatNumber value="${service.itemAmt}" pattern="#,##0.00"/></td>
			            <td class="colCenter" valign="top"><%-- ${service.itemGst} --%>
			            	<c:if test="${('0'eq service.taxAmount) || ('0.00'eq service.taxAmount)}">
					 			    	-
			 			    </c:if>
			 			    <c:if test="${('0'ne service.taxAmount) && ('0.00'ne service.taxAmount)}">
			 			    	<fmt:formatNumber value="${service.taxAmount}" pattern="#,##0.00"/>
			 			    </c:if>
			            </td>
			         </nested:equal>
			         <%-- check without lump sum --%>
			         <nested:notEqual value="1" property="isLumpSum">
				         <td valign="top">&nbsp;</td>
				         <td colspan="2">
				 		     <a href="#" onclick="openB_CPM_View('${pageContext.request.contextPath}/B_CPM/B_CPM_S05InitBL.do?customerPlan.idCustPlan=${service.idCustPlan}&customerPlan.fromScreen=BIF&customerPlan.billType=${bifInfo.BIF_TYPE}')">
				 			     <pre><nested:write property="itemDesc"/></pre>
				 			 </a>
			 			 </td>
			 			 <td class="colRight">&nbsp;</td>
			 			 <td class="colRight">&nbsp;</td>
			 			 <td class="colRight">&nbsp;</td>
		 			 </nested:notEqual>
			     </tr>
			     <nested:present property="subPlanList">
			         <nested:iterate property="subPlanList" id="subPlan">
			             <tr>
			                 <%-- check with lump sum --%>
			                 <nested:equal value="1" property="isLumpSum">
			                     <td valign="top">&nbsp;</td>
			                     <td colspan="2">
			                         <div style="color:#CD853F;" class="divJob">
			                             <c:if test="${'1'eq subPlan.isDisplayJobNo}">
											 <bean:message key="screen.b_bif.jobNoPoint"/>&nbsp;${subPlan.jobNo}
										 </c:if>
			                         </div>
			                         <div>
			                             <pre><nested:write property="itemDesc"/></pre>
			                         </div>
			                     </td>
			                     <td class="colRight">&nbsp;</td>
					 			 <td class="colRight">&nbsp;</td>
					 			 <td class="colRight">&nbsp;</td>
			                 </nested:equal>
			                 <%-- check without lump sum --%>
			                 <nested:notEqual value="1" property="isLumpSum">
			                     <td valign="top">
			                         <c:if test="${'1'eq subPlan.isDisplayJobNo}">
			                             <div>&nbsp;</div>
			                         </c:if>
			                         <nested:write property="itemNo"/>
			                     </td>
			                     <td colspan="2">
			                         <div style="color:#CD853F;" class="divJob">
			                             <c:if test="${'1'eq subPlan.isDisplayJobNo}">
											 <bean:message key="screen.b_bif.jobNoPoint"/>&nbsp;${subPlan.jobNo}
										 </c:if>
			                         </div>
			                         <div>
			                             <pre><nested:write property="itemDesc"/></pre>
			                         </div>
			                     </td>
			                     <td class="colRight" valign="top">
			                         <c:if test="${'1'eq subPlan.isDisplayJobNo}">
			                             <div>&nbsp;</div>
			                         </c:if>
			                         <fmt:formatNumber value="${subPlan.itemQty}" pattern="#,##0"/>
			                     </td>
					 			 <td class="colRight" valign="top">
					 			     <c:if test="${'1'eq subPlan.isDisplayJobNo}">
			                             <div>&nbsp;</div>
			                         </c:if>
					 			     <fmt:formatNumber value="${subPlan.itemUprice}" pattern="#,##0.00"/>
					 			 </td>
					 			 <td class="colRight" valign="top">
					 			     <c:if test="${'1'eq subPlan.isDisplayJobNo}">
			                             <div>&nbsp;</div>
			                         </c:if>
					 			     <fmt:formatNumber value="${subPlan.itemAmt}" pattern="#,##0.00"/>
					 			 </td>
					 			  <td class="colCenter" valign="top">
					 			     <c:if test="${'1'eq subPlan.isDisplayJobNo}">
			                             <div>&nbsp;</div>
			                         </c:if>
					 			    <c:if test="${('0'eq subPlan.taxAmount) || ('0.00'eq subPlan.taxAmount)}">
					 			    	-
					 			     </c:if>
					 			     <c:if test="${('0'ne subPlan.taxAmount) && ('0.00'ne subPlan.taxAmount)}">
					 			    	 <fmt:formatNumber value="${subPlan.taxAmount}" pattern="#,##0.00"/>
					 			     </c:if>
					 			 </td>
			                 </nested:notEqual>
			             </tr>
			              <tr>
			                 
				 	         <nested:equal value="0" property="discLumpSum">
				 	         <nested:notEqual value="0" property="discAmount">
				 	         <td valign="top">&nbsp;</td>
				 	         <td colspan="2">
				 	            <b><i><bean:message key="screen.b_bif.discount"/></i></b>
				 	         </td>
				 	         <td class="colRight" valign="top">
				 	         </td>
				 	         <td class="colRight" valign="top">
				 	         </td>
				 	         <td class="colRight" valign="top">
				 	           -<fmt:formatNumber value="${subPlan.discAmount}" pattern="#,##0.00"/>
				 	        </td>
				 	       	<td class="colCenter" valign="top">
				 			    <%-- ${subPlan.itemGst} --%>
				 			    -
					 		 </td>
				 	        </nested:notEqual>
				 	        </nested:equal>
				 	 </tr>
			             
			         </nested:iterate>
			        <tr>
				 	 <nested:equal value="1" property="discLumpSum">
				 	 <nested:notEqual value="0" property="itemDiscAmt">
				 	 <tr><td colspan="6">&nbsp;</td></tr>
				 	 <td>&nbsp;</td>
				 	 <td colspan="3">
				 	 <b><i><bean:message key="screen.b_bif.discount"/></i></b>
				 	 </td>
				 	 <td></td>
				 	 <td class="colRight">
				 	   -<fmt:formatNumber value="${service.itemDiscAmt}" pattern="#,##0.00"/>
				 	 </td>
				 	 <td class="colCenter" valign="top">
				 		<%-- ${subPlan.itemGst} --%>
				 		 -
					 </td>
				 	 </nested:notEqual>
				 	 </nested:equal>
				 	 </tr>
			         <tr>
				 		 <td>&nbsp;</td>
				 		 <td colspan="2">
				 		     <br/>
				 			 <bean:message key="screen.b_bif.serviceStartColon"/> <nested:write property="svcStart"/>
				 			 <c:if test="${service.autoRenew == '1'}">
				 			     (Auto Renewal)
				 			 </c:if>
				 			 <nested:notEmpty property="svcEnd">
				 			     to: <nested:write property="svcEnd"/>
				 			 </nested:notEmpty>
				 			 <br/>
				 			 <nested:equal value="1" property="isDisplayMinSvc">
				 			     <bean:message key="screen.b_bif.minServicePeriodColon"/>
				 			     <nested:write property="minSvcStart"/>&nbsp;-&nbsp;<nested:write property="minSvcEnd"/>
				 			     <br/>
				 			     <bean:message key="screen.b_bif.contractTermColon"/>
				 			     <nested:write property="contractTermNo"/>
				 			     <nested:equal value="M" property="contractTerm">
				 			         <bean:message key="screen.b_bif.contractTermTypeMonth"/>
				 			     </nested:equal>
				 			     <nested:equal value="Y" property="contractTerm">
				 			         <bean:message key="screen.b_bif.contractTermTypeYear"/>
				 			     </nested:equal>
				 			     <br/>
				 			 </nested:equal>
				 			 <br/>
				 			 <bean:message key="screen.b_bif.billInstructionColon"/>
				 			 <t:writeCodeValue codeList="BILL_INSTRUCTION_LIST" key="${service.billInstruction}"></t:writeCodeValue>
				 		 </td>
				 		 <td>&nbsp;</td>
				 		 <td>&nbsp;</td>
				 		 <td>&nbsp;</td>
				 	 </tr>
				 	 <%-- Split line --%>
				 	 <tr><td colspan="6">&nbsp;</td></tr>
			     </nested:present>
		     </nested:iterate>
	     </nested:present>
	 </nested:nest>
	 <tr>
	 	<td></td>
	 </tr>
	 <c:if test="${'1'ne taxType}">
     	<tr style="background-color: #D5E1FD;">
		 	<td colspan="5" class="colRight"><b><bean:message key="screen.b_bif.subTotal"/> ${totalPlan.currency}</b></td> 
		    <td class="colRight"><fmt:formatNumber value="${totalPlan.totalAmount}" pattern="#,##0.00"/></td>
		    <td colspan="2" class="colRight">&nbsp;</td> 
	 	</tr>
     </c:if>
     <c:if test="${'1'eq taxType}">
     	<tr style="background-color: #D5E1FD;">
		 	<td colspan="5" class="colRight"><b>Subtotal Taxable Services (${totalPlan.currency})</b></td> 
		    <td class="colRight"><fmt:formatNumber value="${totalPlan.subtotalTaxable}" pattern="#,##0.00"/></td>
		    <td colspan="2" class="colRight">&nbsp;</td> 
	 	</tr>
	 	<tr style="background-color: #D5E1FD;">
		 	<td colspan="5" class="colRight"><b>Subtotal Non-Taxable Services (${totalPlan.currency})</b></td> 
		    <td class="colRight"><fmt:formatNumber value="${totalPlan.subtotalNonTaxable}" pattern="#,##0.00"/></td>
		    <td colspan="2" class="colRight">&nbsp;</td> 
	 	</tr>
     </c:if>
	 <tr style="background-color: #D5E1FD;">
	    <td colspan="2">
	    <c:choose>
	        <c:when test="${bifInfo.IS_BAC_EXIST eq '1'}">
	            <c:if test="${bifInfo.checkMultipleInOneInvoice == 1}">
	                <input type="checkbox" name="checkMultipleInOneInvoice" value="1" checked onclick="mbpInOneCheck(this);" disabled="disabled">
	            </c:if>
	            <c:if test="${bifInfo.checkMultipleInOneInvoice != 1}">
	                <input type="checkbox" name="checkMultipleInOneInvoice" value="0" onclick="mbpInOneCheck(this);" disabled="disabled">
	            </c:if>
	            <input type="hidden" name="checkMultipleInOneInvoice" value="${bifInfo.checkMultipleInOneInvoice}">
	        </c:when>
	        <c:otherwise>
	            <c:if test="${bifInfo.checkMultipleInOneInvoice == 1}">
	                <input type="checkbox" name="checkMultipleInOneInvoice" value="1" checked onclick="mbpInOneCheck(this);">
	            </c:if>
	            <c:if test="${bifInfo.checkMultipleInOneInvoice != 1}">
	                <input type="checkbox" name="checkMultipleInOneInvoice" value="0" onclick="mbpInOneCheck(this);">
	            </c:if>
	        </c:otherwise>
	     </c:choose>
	         <bean:message key="screen.b_bif.multipleInOneInvoice"/>
	     </td>
	 	 <td colspan="3" class="colRight">
	 	     <c:if test="${'1'ne taxType}">
	 	     	<b><bean:message key="screen.b_bif.gstAmount"/> ${totalPlan.currency}</b>
	 	     </c:if>
	 	     <c:if test="${'1'eq taxType}">
	 	     	<b>Services Tax ${totalPlan.taxRate}% (${totalPlan.currency})</b>
	 	     </c:if>
	 	 </td>
	    <td class="colRight"><fmt:formatNumber value="${totalPlan.totalGst}" pattern="#,##0.00"/></td>
	    <td colspan="2" class="colRight">&nbsp;</td>
	 </tr>
	 <tr style="background-color: #A6BDE2;">
	 	<td colspan="2">&nbsp;</td>
	 	<td class="colCenter">
	 	<b><bean:message key="screen.b_bif.grandTotal"/> ${totalPlan.currency}</b>&nbsp;&nbsp;&nbsp;&nbsp;
	 	</td> 
	    <td colspan="3" class="colRight"><fmt:formatNumber value="${totalPlan.grandTotal}" pattern="#,##0.00"/></td>
	    <td colspan="2" class="colRight">&nbsp;</td>
	 </tr>
	 <c:if test="${not empty totalPlan.exportCurrency}">
	 <tr style="background-color: #A6BDE2;">
 		<td colspan="4" style="text-align:left;">
	 		<c:choose>
		        <c:when test="${bifInfo.IS_BAC_EXIST eq '1'}">
		            <c:if test="${chkExportAmount == 1}">
		 			    <input type="checkbox" name="chkExportAmount" checked value="1" onclick="expAmtCheck(this);" disabled="disabled"/>
		 		    </c:if>
		 		    <c:if test="${chkExportAmount != 1}">
		 			    <input type="checkbox" name="chkExportAmount" value="0" onclick="expAmtCheck(this);" disabled="disabled"/>
		 		    </c:if>
		 		    <input type="hidden" name="chkExportAmount" value="${chkExportAmount}">
		        </c:when>
		        <c:otherwise>
		            <c:if test="${chkExportAmount == 1}">
		 			    <input type="checkbox" name="chkExportAmount" checked value="1" onclick="expAmtCheck(this);"/>
		 		    </c:if>
		 		    <c:if test="${chkExportAmount != 1}">
		 			    <input type="checkbox" name="chkExportAmount" value="0" onclick="expAmtCheck(this);"/>
		 		    </c:if>
		        </c:otherwise>
		    </c:choose>
 			<bean:message key="screen.b_bif02.exportAmount"/> <t:writeCodeValue codeList="LIST_TRANSACTIONTYPE" key="${bifInfo.BIF_TYPE}"></t:writeCodeValue>
	 		<label style="padding-left:16%;"><b>
	 		<c:choose>
		        <c:when test="${totalPlan.currencyStd eq 1}">
		            <bean:message key="screen.b_bif.grandTotal"/>
				 		${totalPlan.exportCurrency}
				 		&nbsp;
				 		(1&nbsp;${totalPlan.currency}&nbsp;=&nbsp;
				 		<c:if test="${not empty totalPlan.fixedForex}">
						    Fixed Forex
						</c:if>
				 		${totalPlan.rateCurrency}
				 		&nbsp;${totalPlan.exportCurrency}
				 		)
			    </c:when>
			    <c:when test="${totalPlan.currencyStd eq 0}">
			        <c:if test="${totalPlan.currency eq totalPlan.defCurrency}">
						<bean:message key="screen.b_bif.grandTotal"/>
						&nbsp;${totalPlan.exportCurrency}
						&nbsp;(
						1&nbsp;${totalPlan.exportCurrency}
						&nbsp;=&nbsp;
						<c:if test="${ not empty totalPlan.fixedForex}">
				            Fixed Forex
			            </c:if>
						<fmt:formatNumber value="${totalPlan.rateCurrency}" pattern="#,##0.00000000"/>
						&nbsp;${totalPlan.currency}
						)
					</c:if>
					<c:if test="${totalPlan.currency ne totalPlan.defCurrency}">
						<bean:message key="screen.b_bif.grandTotal"/>
						&nbsp;${totalPlan.exportCurrency}
						&nbsp;(
						1&nbsp;${totalPlan.currency}
						&nbsp;=&nbsp;
						<c:if test="${not empty totalPlan.fixedForex}">
							Fixed Forex
						</c:if>
						<fmt:formatNumber value="${totalPlan.rateCurrency}" pattern="#,##0.00000000"/>
						&nbsp;${totalPlan.exportCurrency}
						)
					</c:if>
			    </c:when>
		        <c:otherwise></c:otherwise>
		    </c:choose>
	 		</b></label>
	 		</td>
 		<td colspan="2" class="colRight"><label><fmt:formatNumber value="${totalPlan.exportAmount}" pattern="#,##0.00"/></label></td>
 		<td colspan="2" class="colRight">&nbsp;</td>
	 </tr>
	 </c:if>	
	 <c:if test="${empty totalPlan.exportCurrency}">
	 	<input type="hidden" name="chkExportAmount" value="0"/>
	 </c:if> 	
	</table>

	<input type="hidden" name="currency" value="${totalPlan.currency}"/>
	<input type="hidden" name="exportCurrency" value="${totalPlan.exportCurrency}"/>
	<input type="hidden" name="rateCurrency" value="${totalPlan.rateCurrency}"/>
	<input type="hidden" name="ERR1SC107" id="ERR107" value="<bean:message key="errors.ERR1SC107" arg0="{0}" arg1="{1}"/>">
	<!-- <input type="hidden" name="rateCurrency" value="${totalPlan.fixedForex}"/> -->
	<!-- Plans -->
		
	<!-- Prepare By -->
	<table>
		<tr><td height="0px" /></tr>
	</table>
	<table cellpadding="2" cellspacing="2" width="100%" style="border: 1px solid #C2C0B5;">
	 <tr>
	  <td width="50%" valign="top">
	  	<table class="subHeaderInner">
		  	 <tr style="background-color: #EAEAEA;">
		  	  <td class="tdleftHeader" colspan="2"><b><bean:message key="screen.b_bif02.preparedby"/></b>
		  	  </td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif02.name"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">${bifInfo.REQ_USER_NAME}</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif02.date"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">
		  	 		<c:choose>
		  	 			<c:when test="${not empty bifInfo.DATE_OBT_VERIFY}">
			  	 			<fmt:formatDate value="${bifInfo.DATE_OBT_VERIFY}" pattern="dd/MM/yyyy"/>
			  	 		</c:when>
			  	 		<c:otherwise>
			  	 			-
			  	 		</c:otherwise>
		  	 		</c:choose>
		  	 	</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif02.comment"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright" width="85%"><textarea rows="2" cols="40" name="tbxComments" onkeypress="return imposeMaxLength(this, 149);" style="overflow-y:visible;height:40px;">${bifInfo.USER_COMMENT}</textarea></td>
		  	 </tr>		  	 
	  	</table>
	  </td>
	 </tr>
	</table>
	<!-- Prepare By -->
	<table class="buttonGroup" cellpadding="0" cellspacing="0">
  	<tr>
		<td>
			<input type="button" class="button" value="<bean:message key="screen.b_bif.save"/>" name="forward_save" onclick="javascript: onSave();" />
			<%--
			<input type="button" class="button1" value="<bean:message key="screen.b_bif.delete"/>"  onclick="javascript: doDelete();" />
			<c:choose>
				<c:when test="${bifInfo.ID_STATUS == 'DS0'}">
					<c:if test="${screenObject.is_ObtainV == '0'}">
						<input type="button" class="button1" value="<bean:message key="screen.b_bif.obtainVerification"/>"  onclick="javascript: doObtainVerification();" />
					</c:if>
					<c:if test="${screenObject.is_ObtainA == '0'}">
						<input type="button" class="button1" value="<bean:message key="screen.b_bif.obtainApproval"/>"  onclick="javascript: doObtainApproval();" />
					</c:if>
				</c:when>
				<c:otherwise>
					<c:if test="${bifInfo.ID_STATUS == 'DS1' and wfInfo.REJECT_STATUS == 'AS3'}">
						<c:if test="${screenObject.is_ObtainA == '0'}">
							<input type="button" class="button1" value="<bean:message key="screen.b_bif.obtainApproval"/>"  onclick="javascript: doObtainApproval();" />
						</c:if>
					</c:if>
				</c:otherwise>
			</c:choose>
			--%>
			<input type="button" class="button" value="<bean:message key="screen.b_bif.exit"/>" name="forward_exit" onclick="clickExit()" />			
		</td>	
	</tr>
</table> 
<input type="hidden" name="deletedAttachmentSC"/>
<input type="hidden" name="deletedAttachmentQP"/>
<input type="hidden" name="deletedAttachmentOT"/>
	<bean:size collection="${attachmentsSCBif}" name="sizeTF" id="sizeSC"/>
	<bean:size collection="${attachmentsQPBif}" name="sizeMR" id="sizeQP"/>
	<bean:size collection="${attachmentsOTBif}" name="sizeCR" id="sizeOT"/>
<div class="show" id="errms" style="color: red;font-style: italic;">
	</div> 
	<script>
	if(document.getElementById('bifSC_file')!= null){
		var btnUpload = document.getElementById("fakeSC");
		var multi_selector = new MultiSelector( 'attachmentSCBif', 'firstUpload','listFileSCBif', 5 - ${sizeSC}, btnUpload, alter);	
		multi_selector.setMessage(MsgBox);	
		multi_selector.addElement( document.getElementById( 'bifSC_file' ) );
	}
	if(document.getElementById('bifQP_file')!= null){
		var btnUpload = document.getElementById("fakeQP");
		var multi_selector = new MultiSelector( 'attachmentQPBif', 'secondUpload','listFileQPBif', 5 - ${sizeQP}, btnUpload, alter);	
		multi_selector.setMessage(MsgBox);	
		multi_selector.addElement( document.getElementById( 'bifQP_file' ) );
	}
	if(document.getElementById('bifOT_file')!= null){
		var btnUpload = document.getElementById("fakeOT");
		var multi_selector = new MultiSelector( 'attachmentOTBif', 'thirdUpload','listFileOTBif', 5 - ${sizeOT}, btnUpload, alter);	
		multi_selector.setMessage(MsgBox);	
		multi_selector.addElement( document.getElementById( 'bifOT_file' ) );
	}
	</script>
	<c:if test="${chkExportAmount == 1}">
		<script type="text/javascript">
			setDisplayExpAmt(true);
		</script>
	</c:if>
	<c:if test="${chkExportAmount != 1}">
		<script type="text/javascript">
			setDisplayExpAmt(false);
		</script>
	</c:if>
	<c:forEach items="${attachmentsSCBif}" var="item">
		<script type="text/javascript">
			var ref = '${bifInfo.ID_STATUS}';
			var table_id = 'attachmentSCBif';
			var file_name = "${item.filename}";
			var doc_id = '${item.id_doc}';
			var uploadObject = document.getElementById("bifSC_file");
			var deleteObject = document.forms[0].deletedAttachmentSC;
			if (uploadObject != undefined) {
				uploadObject.multi_selector.addServerRow(table_id, file_name, doc_id, deleteObject, ref);
			}
		</script>
	</c:forEach>
	<c:forEach items="${attachmentsQPBif}" var="item">
		<script type="text/javascript">
			var ref = '${bifInfo.ID_STATUS}';
			table_id = 'attachmentQPBif';
			file_name = "${item.filename}";
			doc_id = '${item.id_doc}';
			uploadObject = document.getElementById("bifQP_file");
			deleteObject = document.forms[0].deletedAttachmentQP;
			if (uploadObject != undefined) {
				uploadObject.multi_selector.addServerRow(table_id, file_name, doc_id, deleteObject, ref);
			}
		</script>
	</c:forEach>
	<c:forEach items="${attachmentsOTBif}" var="item">
		<script type="text/javascript">
			var ref = '${bifInfo.ID_STATUS}';
			table_id = 'attachmentOTBif';
			file_name = "${item.filename}";
			doc_id = '${item.id_doc}';
			uploadObject = document.getElementById("bifOT_file");
			deleteObject = document.forms[0].deletedAttachmentOT;
			if (uploadObject != undefined) {
				uploadObject.multi_selector.addServerRow(table_id, file_name, doc_id, deleteObject, ref);
			}
		</script>
	</c:forEach>
	</ts:form>
	<div style="display:none;" id="messageAlt">
		<ts:messages id="messages" message="true">
			<bean:write name="messages"/><br/>
		</ts:messages>
   	</div>
</body>
</html>