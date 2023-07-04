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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
	<title>Insert title here</title>
   	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
   	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/BIF/css/bifStyle.css"/>
   	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>			
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/BIF/js/b_bifs03.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/BIF/js/multifile.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/BIF/js/alternative.js"></script>
<script type="text/javascript">
var MsgBox = new messageBox("<%=request.getContextPath()%>");
var alter = new ALT();
 	function clickDownload(id_doc){			
		document.getElementById("id_doc").value = id_doc;
		document.forms[0].action = '<%=request.getContextPath()%>/BIF/BIFDownloadBL.do';
		document.forms[0].submit();
		//reset
		document.forms[0].action = '<%=request.getContextPath()%>/BIF/B_BIFS03_06BL.do';		
	}

 	function clickExit(){
 		if (MsgBox.POPEXT() == MsgBox.YES_CONFIRM) {
			document.forms[0].action = '<%=request.getContextPath()%>/BIF/B_BIFS01BL.do';
			document.forms[0].submit();
		}
	}
		
 	function saveBIF(){
		var errorMess = "";

		if(errorMess != ""){
			document.getElementById("errms").innerHTML = errorMess;
			return false;					
		}
		return true;
 	} 
	
	function doReject() {
    	document.forms[0].hidIsSave.value = 0;
		if(isDataModify()) {
			var result = MsgBox.POPBEF();
			switch(result) {
    			case MsgBox.CANCEL_CONFIRM: return;
    			case MsgBox.NO_CONFIRM: //no
    				document.forms[0].hidIsSave.value = 0;
    			break;
    			case MsgBox.YES_CONFIRM: //yes
    			 	document.forms[0].hidIsSave.value = 1;
    			break;
    		}
   		}
		var result2 = MsgBox.POPRJT("","${bifObject.ID_REF}");
   		switch(result2) {
   			case MsgBox.CANCEL_CONFIRM: return;
   			case MsgBox.NO_CONFIRM: //no
   				document.forms[0].hidIsRevised.value = 0;
   			break;
   			case MsgBox.YES_CONFIRM: //yes
   			 	document.forms[0].hidIsRevised.value = 1;
   			break;
   		}
   		document.forms[0].hidApprStatus.value = "AS3";
		document.forms[0].hidAction.value = "reject";
		document.forms[0].submit();
	}
	
	function doApproval() {
		if(isDataModify()) {
			var result = MsgBox.POPBEF("");
	   		switch(result) {
	   			case MsgBox.CANCEL_CONFIRM: return;
	   			case MsgBox.NO_CONFIRM: //no
	   				document.forms[0].hidIsSave.value = 0;
	   			break;
	   			case MsgBox.YES_CONFIRM: //yes
	   			 	document.forms[0].hidIsSave.value = 1;
	   			break;
	   		}
   		}
   		else {
   			document.forms[0].hidIsSave.value = 1;//save by default
   		}
   		document.forms[0].hidIsRevised.value = 0;
   		document.forms[0].hidApprStatus.value = "AS2";
		document.forms[0].hidAction.value = "approval";
		document.forms[0].submit();
	}
	
	function onSave() {
		if(saveBIF()) {
			document.forms[0].hidIsSave.value = 1;
			document.forms[0].hidAction.value = "edit";
			document.forms[0].submit();
		}
	}
	
	function init() {
		var rdbIns = new Array();
		rdbIns = document.getElementsByName("rdbInstructionf");
		for(i = 0; i < rdbIns.length; i++) {
			var rdbObject = rdbIns[i]; 
			if(rdbObject.value == "${bifObject.DELIVERY}") {
				rdbObject.checked = true;
			} else {
				rdbObject.disabled = true;
			}
		}
		
		var rdbCredit = new Array();
		rdbCredit = document.getElementsByName("rdbCreditNoteType");
		for(i = 0; i < rdbCredit.length; i++) {
			var rdbObjectCredit = rdbCredit[i]; 
			if(rdbObjectCredit.value == "${bifObject.CN_TYPE}") {
				rdbObjectCredit.checked = true;
			} else {
				rdbObjectCredit.disabled = true;
			}
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
	
	function isDataModify() {
		if(document.getElementById("cboBillingOpera"))
			if(document.getElementById("cboBillingOpera_old") && document.getElementById("cboBillingOpera_old").value != document.getElementById("cboBillingOpera").value)
				return true;
		if(document.getElementById("tbxBIFReceivedD"))
			if(document.getElementById("tbxBIFReceivedD_old").value != document.getElementById("tbxBIFReceivedD").value)
				return true;
		if(document.getElementById("tbxInvoiceRefer"))
			if(document.getElementById("tbxInvoiceRefer_old").value != document.getElementById("tbxInvoiceRefer").value)
				return true;
		if(document.getElementById("tbxPrintDate"))
			if(document.getElementById("tbxPrintDate_old").value != document.getElementById("tbxPrintDate").value)
				return true;
		if(document.getElementById("tbxSignDate"))
			if(document.getElementById("tbxSignDate_old").value != document.getElementById("tbxSignDate").value)
				return true;
		if(document.getElementById("tbxApprComments"))
			if(document.getElementById("tbxApprComments_old").value != document.getElementById("tbxApprComments").value)
				return true;
		return false;
	}

</script>
</head>
<body onload="javascript:init();alter.init(document.forms[0]);" onmousemove="alter.changePosition(event);">
	<t:defineCodeList id="LIST_COUNTRY"/>
	<ts:form action="B_BIFS03_06BL">
	<html:hidden property="idRef" value="${bifObject.ID_REF}"/>
	<html:hidden property="hidAction"/>
	<html:hidden property="hidIsSave" value="0"/>
	<html:hidden property="hidIdWfApproval" value="${wfInfo.ID_WF_APPROVAL}"/>
	<html:hidden property="hidIsRevised" value="0"/>
	<html:hidden property="hidSectionNo" value="${wfInfo.SECTION_NO}"/>
	<html:hidden property="hidSectionGroup" value="${wfInfo.SECTION_GROUP}"/>
	<html:hidden property="hidApprStatus" value="${wfInfo.APPR_STATUS}"/>
	<input type="hidden" name="fileName" id="fileName"/>
	<input type="hidden" name="id_doc" id="id_doc"/>
	<table class="subHeader" cellpadding="0" cellspacing="0">
	  <tr style="">
	    <td class="Title">
	    <bean:message key="screen.b_bif.billingInstructionForm"/>
	    <bean:message key="screen.b_bif._"/>
	    <t:writeCodeValue codeList="LIST_TRANSACTIONTYPE" key="${bifObject.BIF_TYPE}"></t:writeCodeValue>
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
		  	  <td class="tdleftHeader">
		  	  <b><bean:message key="screen.b_bif.customerDetails"/></b>
		  	  </td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif.customerName"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	
		  	 	<td class = "tdright">
		  	 	
		  	 	${bifObject.CUST_NAME}</td>
		  	 </tr>
		  	 <tr><td colspan="2">&nbsp;</td></tr>
		  	 <c:forEach items="${cusAdr}" var="item">
		  	 <c:if test="${item.ADR_TYPE == bifObject.ADR_TYPE}">
		  	 	<tr id="adr${item.ADR_TYPE}">
		  	 	<td class = "tdleft">
		  	 		<t:writeCodeValue codeList="LIST_ADDRESS" key="${item.ADR_TYPE}"></t:writeCodeValue>
		  	 	</td>
			  	 	<td class = "tdright">${item.ADR_LINE1}</td>
			  	 </tr>
			  	 <tr id="adr${item.ADR_TYPE}">
			  	 	<td class = "tdleft"></td>
			  	 	<td class = "tdright">${item.ADR_LINE2}</td>
			  	 </tr>
			  	 <tr id="adr${item.ADR_TYPE}">
			  	 	<td class = "tdleft"></td>
			  	 	<td class = "tdright">${item.ADR_LINE3}</td>
			  	 </tr>
			  	 <tr id="adr${item.ADR_TYPE}">
			  	 	<td class = "tdleft"></td>
			  	 	<td class = "tdright"><c:if test="${not empty item.ZIP_CODE or not empty item.COUNTRY}">${item.ZIP_CODE},<t:writeCodeValue codeList="LIST_COUNTRY" key="${item.COUNTRY}"/></c:if></td>
			  	 </tr>
		  	 </c:if>
		  	 </c:forEach>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif.tel"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">${bifObject.TEL_NO}</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif.fax"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">${bifObject.FAX_NO}</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif.attn"/>&nbsp;<bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">
		  	 		<c:if test="${cusInfo.CUSTOMER_TYPE eq 'CORP'}">
		  	 		    <c:if test="${not empty bifObject.CONTACT_TYPE or not empty bifObject.CONTACT_NAME}">
	  	 		            [${bifObject.CONTACT_TYPE}]${bifObject.CONTACT_NAME}
	  	 		        </c:if>
	  	 		    </c:if>
		  	 	</td>
		  	 </tr>
		  	 <!-- Email(To)&(Cc) -->
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif.email_to"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td>
		  	 		<c:if test="${cusInfo.CUSTOMER_TYPE eq 'CORP'}">
		  	 			${bifObject.CONTACT_EMAIL}
		  	 		</c:if>
		  	 	</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif.email_cc"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td>
		  	 		<c:if test="${cusInfo.CUSTOMER_TYPE eq 'CORP'}">
		  	 			${bifObject.CONTACT_EMAIL_CC}
		  	 		</c:if>
		  	 	</td>
		  	 </tr>
	  	</table>
	  </td>
	  <td width="40%" style="background-color: #EAEAEA;">
	  	<table class="subHeaderInner"> 
		  	 <tr>
		  	 	<td class = "tdleft"><bean:message key="screen.b_bif.bifReference"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">${bifObject.ID_REF}
		  	 	</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "tdleft"><bean:message key="screen.b_bif.requestedDate"/>&nbsp;<bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright"><fmt:formatDate value="${bifObject.DATE_REQ}" pattern="dd/MM/yyyy"/> </td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "tdleft"><bean:message key="screen.b_bif.qscReference"/>&nbsp;<bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">${bifObject.ID_QCS}
		  	 	</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "tdleft"><bean:message key="screen.b_bif.quoReference"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">${bifObject.ID_QUO}
		  	 	</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "tdleft"><bean:message key="screen.b_bif.customerPO"/>&nbsp;<bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">${bifObject.CUST_PO}</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "tdleft"><bean:message key="screen.b_bif.consultantName"/>&nbsp;<bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">${bifObject.CONSLT_NAME}</td>
		  	 </tr>
		  	 <%--
		  	 <tr>
		  	 	<td class = "tdleft"><bean:message key="screen.b_bif.jobNo"/>&nbsp;<bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">${bifObject.JOB_NO}
		  	 	</td>
		  	 </tr>
		  	 --%>
	  	</table>
		</td>
	  <td width="18%" valign="top" class="<t:writeCodeValue codeList="COLOR_CODE" key="${bifObject.ID_STATUS }"></t:writeCodeValue>">
	  	<table class="<t:writeCodeValue codeList="COLOR_CODE" key="${bifObject.ID_STATUS }"></t:writeCodeValue>">
	  		<tr>
	  			<td align="center">
	  			 <bean:message key="screen.b_bif.status"/>
	  			</td>
	  		</tr>
	  		<tr>
	  			<td align="center">
	  			<t:writeCodeValue codeList="DOC_STATUS_LIST" key="${bifObject.ID_STATUS }"></t:writeCodeValue>
	  			</td>
	  		</tr>
	  		<tr>
	  			<td align="center">
	  			 <fmt:formatDate value="${bifObject.DATE_UPDATED}" pattern="dd/MM/yyyy"/>
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
	  <td width="20%"><bean:message key="screen.b_bif.documentdelivery"/></td>
	  <td width="15%">
	  	<bean:message key="screen.b_bif.colon"/>
	  	<c:if test="${bifObject.DELIVERY_EMAIL == 1}">
	   		<input type="checkbox" name="checkDeliveryEmail" value="1" checked  disabled="disabled">
	    </c:if>
	    <c:if test="${bifObject.DELIVERY_EMAIL != 1}">
	   		<input type="checkbox" name="checkDeliveryEmail" value="0" disabled="disabled">
	    </c:if>
	    <bean:message key="screen.b_bif.email"/>
	  </td>
	  <td width="15%"><input type="radio" name="rdbInstructionf" value="3"/><bean:message key="screen.b_bif.itConsultant"/></td>
	  <td width="15%">
	  	<input type="radio" name="rdbInstructionf" value="1"/><bean:message key="screen.b_bif.post"/>
	  </td>
	  <td width="15%"><input type="radio" name="rdbInstructionf" value="2"/><bean:message key="screen.b_bif.courier"/></td>
	  
	  <td width="20%"><input type="radio" name="rdbInstructionf" value="4"/><bean:message key="screen.b_bif.byHand"/></td>
	   <%-- <td width="20%">&nbsp;</td> --%>
	 </tr>
	 <tr>
	 	<td width="20%"><bean:message key="screen.b_bif.CreditNoteType"/></td>
	 	<td width="15%"><bean:message key="screen.b_bif.colon"/><input type="radio" name="rdbCreditNoteType" value="1"/><bean:message key="screen.b_bif.Terminaiton"/></td>
	 	<td width="20%"><input type="radio" name="rdbCreditNoteType" value="2"/><bean:message key="screen.b_bif.UpDown"/></td>
	 	<td width="15%"><input type="radio" name="rdbCreditNoteType" value="3"/><bean:message key="screen.b_bif.Discount"/></td>
	 	<td width="15%"><input type="radio" name="rdbCreditNoteType" value="4"/><bean:message key="screen.b_bif.RefundOfDeposit"/></td>
	 	<td width="15%" align="left"><input type="radio" name="rdbCreditNoteType" value="5"/><bean:message key="screen.b_bif.Compensation"/></td>
	 </tr>
	 <tr>
	 	<td>&nbsp;</td>
	 	 <td colspan="5">
	 	 	&nbsp;&nbsp;
	  		<input type="radio" name="rdbCreditNoteType" value="6"/><bean:message key="screen.b_bif.others"/>
	  		${bifObject.CN_TYPE6 }
	  </td>
	 </tr>
	 <tr>
	  <td><bean:message key="screen.b_bif.remarks"/></td>
	  <td colspan="4">
	  	<bean:message key="screen.b_bif.colon"/>${bifObject.REMARKS }
	  </td> 
	 </tr>
	</table>
	<!-- Credit term approved -->
	
	<!-- Attachment -->	
	<table cellspacing="0" width="100%" class="AttachmentTbl" style="background-color: #F7F7F4;border: 1px solid #C2C0B5;border-top: 1px solid #F7F7F4;">
	 <tr>
	 	<td class="header"><b><bean:message key="screen.b_bif.attachment"/></b></td>
	 	<td class="header"><span style="background-color: #C0C0C0"><bean:message key="screen.b_bif.AttTerminationForm"/></span>
	 	</td>	 	
	 	<td class="header"><span style="background-color: #C0C0C0"><bean:message key="screen.b_bif.AttMRTG"/></span>
	 	</td>	 	
	 	<td class="header"><span style="background-color: #C0C0C0"><bean:message key="screen.b_bif.AttChangeRequestForm"/></span>
	 	</td>	 	
	 </tr>
	 	 <tr>
	 	<td>&nbsp;</td>
	 	<td id="attTF" valign="top">
	 		<table id="attachmentTFBif" style="border:0;width:100%;font-size:15px;">
	 			<c:forEach items="${attachmentsTFBif}" var="item">
					<tr>
						<td>
							<a href="#" onclick='clickDownload("${item.id_doc}") '> ${item.filename}</a>
						</td>
						<td>								
						</td>
					</tr>
				</c:forEach>
	 		</table>
	 	</td>	 	
	 	<td id="attMR" valign="top">
	 		<table id="attachmentMRBif" style="border:0;width:100%;font-size:15px;">
	 			<c:forEach items="${attachmentsMRBif}" var="item">
					<tr>
						<td>
							<a href="#" onclick='clickDownload("${item.id_doc}") '> ${item.filename}</a>
						</td>
						<td>								
						</td>
					</tr>
				</c:forEach>
	 		</table>
	 	</td>	 	
	 	<td id="attCR" valign="top">
	 		<table id="attachmentCRBif" style="border:0;width:100%;font-size:15px;">
	 			<c:forEach items="${attachmentsCRBif}" var="item">
					<tr>
						<td>
							<a href="#" onclick='clickDownload("${item.id_doc}") '> ${item.filename}</a>
						</td>
						<td>								
						</td>
					</tr>
				</c:forEach>
	 		</table>
		</td>	 	
	 </tr>
	 <tr>
	 	<td class="header">&nbsp;</td>
	 	<td class="header"><span style="background-color: #C0C0C0"><bean:message key="screen.b_bif.AttTerminationLetter"/></span>
	 	</td>	 	
	 	<td class="header"><span style="background-color: #C0C0C0"><bean:message key="screen.b_bif.AttPurchaseOrder"/></span>
	 	</td>	 	
	 	<td class="header"><span style="background-color: #C0C0C0"><bean:message key="screen.b_bif.AttOthers"/></span>
	 	</td>	 	
	 </tr>
	 	 <tr>
	 	<td>&nbsp;</td>
	 	<td id="attTL" valign="top">
	 		<table id="attachmentTLBif" style="border:0;width:100%;font-size:15px;">
	 			<c:forEach items="${attachmentsTLBif}" var="item">
					<tr>
						<td>
							<a href="#" onclick='clickDownload("${item.id_doc}") '> ${item.filename}</a>
						</td>
						<td>								
						</td>
					</tr>
				</c:forEach>
	 		</table>
	 	</td>	 	
	 	<td id="attPO" valign="top">
	 		<table id="attachmentPOBif" style="border:0;width:100%;font-size:15px;">
	 			<c:forEach items="${attachmentsPOBif}" var="item">
					<tr>
						<td>
							<a href="#" onclick='clickDownload("${item.id_doc}") '> ${item.filename}</a>
						</td>
						<td>								
						</td>
					</tr>
				</c:forEach>
	 		</table>
	 	</td>	 	
	 	<td id="attOT" valign="top">
	 		<table id="attachmentOTBif" style="border:0;width:100%;font-size:15px;">
	 			<c:forEach items="${attachmentsOTBif}" var="item">
					<tr>
						<td>
							<a href="#" onclick='clickDownload("${item.id_doc}") '> ${item.filename}</a>
						</td>
						<td>								
						</td>
					</tr>
				</c:forEach>
	 		</table>
		</td>	 	
	 </tr>
	 <tr>
	 	<td class="header">&nbsp;</td>
	 	<td class="header"><span style="background-color: #C0C0C0"><bean:message key="screen.b_bif.AttInvoice"/></span>
	 	</td>	 	
	 	<td class="header"><span style="background-color: #C0C0C0"><bean:message key="screen.b_bif.AttAgreement"/></span>
	 	</td>	 	
	 	<td class="header">

	 	</td>	 	
	 </tr>
	 <tr>
	 	<td>&nbsp;</td>
	 	<td id="attSC" valign="top">
	 		<table id="attachmentIVBif" style="border:0;width:100%;font-size:15px;">
	 			<c:forEach items="${attachmentsIVBif}" var="item">
					<tr>
						<td>
							<a href="#" onclick='clickDownload("${item.id_doc}") '> ${item.filename}</a>
						</td>
						<td>								
						</td>
					</tr>
				</c:forEach>
	 		</table>
	 	</td>	 	
	 	<td id="attQP" valign="top">
	 		<table id="attachmentAGBif" style="border:0;width:100%;font-size:15px;">
	 			<c:forEach items="${attachmentsAGBif}" var="item">
					<tr>
						<td>
							<a href="#" onclick='clickDownload("${item.id_doc}") '> ${item.filename}</a>
						</td>
						<td>								
						</td>
					</tr>
				</c:forEach>
	 		</table>
	 	</td>	 	
	 	<td>

		</td>	 	
	 </tr>
	</table>
	
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
				 			<a href="#" onclick="openB_CPM_View('${pageContext.request.contextPath}/B_CPM/B_CPM_S05InitBL.do?customerPlan.idCustPlan=${service.idCustPlan}&customerPlan.fromScreen=BIF&customerPlan.billType=${bifObject.BIF_TYPE}')">
				 				<pre><nested:write property="itemDesc"/></pre>
				 			</a>
			 			</td>
			 			<td class="colRight" valign="top"><fmt:formatNumber value="${service.itemQty}" pattern="#,##0"/></td>
			 			<td class="colRight" valign="top"><fmt:formatNumber value="${service.itemUprice}" pattern="#,##0.00"/></td>
			 			<td class="colRight" valign="top"><fmt:formatNumber value="${service.itemAmt}" pattern="#,##0.00"/></td>
			            <td class="colCenter" valign="top"><%-- ${service.itemGst} --%>
			            	<fmt:parseNumber var = "serviceTaxAmount" type = "number" value = "${service.taxAmount}" />
			 				<c:if test="${('0'eq serviceTaxAmount)}">
					 			    	-
			 			    </c:if>
			 			    <c:if test="${('0'ne serviceTaxAmount)}">
			 			    	<fmt:formatNumber value="${service.taxAmount}" pattern="#,##0.00"/>
			 			    </c:if>
			            </td>
			         </nested:equal>
			         <%-- check without lump sum --%>
			         <nested:notEqual value="1" property="isLumpSum">
				         <td valign="top">&nbsp;</td>
				         <td colspan="2">
				 		     <a href="#" onclick="openB_CPM_View('${pageContext.request.contextPath}/B_CPM/B_CPM_S05InitBL.do?customerPlan.idCustPlan=${service.idCustPlan}&customerPlan.fromScreen=BIF&customerPlan.billType=${bifObject.BIF_TYPE}')">
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
					 			    <%-- ${subPlan.itemGst} --%>
					 			    <fmt:parseNumber var = "subPlanTaxAmount" type = "number" value = "${subPlan.taxAmount}" />
					 			    <c:if test="${'0'eq subPlanTaxAmount}">
					 			    	-
					 			    </c:if>
					 			    <c:if test="${'0'ne subPlanTaxAmount}">
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
		 	<td colspan="5" class="colRight"><b>Subtotal Taxable Services(${totalPlan.currency})</b></td> 
		    <td class="colRight"><fmt:formatNumber value="${totalPlan.subtotalTaxable}" pattern="#,##0.00"/></td>
		    <td colspan="2" class="colRight">&nbsp;</td> 
	 	</tr>
	 	<tr style="background-color: #D5E1FD;">
		 	<td colspan="5" class="colRight"><b>Subtotal Non - Taxable Services(${totalPlan.currency})</b></td> 
		    <td class="colRight"><fmt:formatNumber value="${totalPlan.subtotalNonTaxable}" pattern="#,##0.00"/></td>
		    <td colspan="2" class="colRight">&nbsp;</td> 
	 	</tr>
     </c:if>
	 <tr style="background-color: #D5E1FD;">
	 	<td colspan="2">
	     <c:if test="${bifObject.checkMultipleInOneInvoice == 1}">
	         <input type="checkbox" name="checkMultipleInOneInvoice" checked value="1" disabled="disabled">
	     </c:if>
	     <c:if test="${bifObject.checkMultipleInOneInvoice != 1}">
	         <input type="checkbox" name="checkMultipleInOneInvoice" value="0" disabled="disabled">
	     </c:if>
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
 		    <c:if test="${chkExportAmount == 1}">
	 			<input type="checkbox" name="chkExportAmount" checked value="1" disabled="disabled"/>
	 		</c:if>
	 		<c:if test="${chkExportAmount != 1}">
	 			<input type="checkbox" name="chkExportAmount" value="0" disabled="disabled"/>
	 		</c:if>
 			<bean:message key="screen.b_bif02.exportAmount"/> <t:writeCodeValue codeList="LIST_TRANSACTIONTYPE" key="${bifObject.BIF_TYPE}"></t:writeCodeValue>
 			
	 		<label style="padding-left:10%;"><b>
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
 		<td colspan="2" class="colRight"><fmt:formatNumber value="${totalPlan.exportAmount}" pattern="#,##0.00"/></td>
	    <td colspan="2" class="colRight">&nbsp;</td>
	 </tr>
	 </c:if>
	<c:if test="${empty totalPlan.exportCurrency}">
	 	<input type="hidden" name="chkExportAmount" value="0"/>
	 </c:if>
	</table>
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
		  	 	<td class = "tdright">${bifObject.REQ_USER_NAME}</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif02.date"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">
		  	 		<c:choose>
		  	 			<c:when test="${not empty bifObject.DATE_OBT_VERIFY}">
			  	 			<fmt:formatDate value="${bifObject.DATE_OBT_VERIFY}" pattern="dd/MM/yyyy"/>
			  	 		</c:when>
			  	 		<c:otherwise>
			  	 			-
			  	 		</c:otherwise>
		  	 		</c:choose>
		  	 	</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif02.comment"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright" width="85%"><textarea rows="2" cols="40" name="tbxComments" readonly="readonly" style="overflow-y:visible;height:40px;">${bifObject.USER_COMMENT}</textarea></td>
		  	 </tr>
	  	</table>
	  </td>
	  <td width="50%" style="background-color: #F5CCCC;">
	  <c:forEach items="${commentBIF}" var="item">
	  	<table class="ApproveTable"> 
	  	<COLGROUP>
	  		<COL width="10%"/>
	  		<COL width="60%"/>
	  		<COL width="30%"/>
	  	</COLGROUP> 
		  	 <tr>
		  	 	<td class = "label">&nbsp;</td>
		  	 	<td class = "label">&nbsp;</td>
		  	 	<td class = "tdright">
	  	 			<div class="<t:writeCodeValue codeList="COLOR_CODE" key="${item.APPR_STATUS }"></t:writeCodeValue>">
	  	 				<t:writeCodeValue codeList="APPROVAL_STATUS_LIST" key="${item.APPR_STATUS}"></t:writeCodeValue>
	  	 			</div>
		  	 	</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif02.name"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">${item.USER_NAME}</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif02.date"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright"><fmt:formatDate value="${item.DATE_APPR}" pattern="dd/MM/yyyy"/></td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif02.comments"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright"><textarea rows="2" cols="40" readonly="readonly" style="overflow-y:visible;height:40px;">${item.APPR_COMMENT}</textarea></td>
		  	 </tr>		  	 
	  	</table>
	  </c:forEach>
		</td>
	 </tr>
	</table>
	<!-- Prepare By -->
	
	<!-- Corporate Division -->
	<table>
		<tr><td height="0px" /></tr>
	</table>
	<table cellpadding="2" cellspacing="2" width="100%" style="border: 1px solid #C2C0B5;">
		<tr style="background-color: #EAEAEA;">
			<td class="tdleftHeader" colspan="3"><b><bean:message key="screen.b_bif.corporateDivision"/></b></td>
		</tr>
	 <tr>
	  <td width="50%">
	  </td>
	  <td width="50%" style="background-color: #F5CCCC;">
	  <c:if test="${empty commentHCD}">
	  	<table class="ApproveTable"> 
	  	<COLGROUP>
	  		<COL width="10%"/>
	  		<COL width="60%"/>
	  		<COL width="30%"/>
	  	</COLGROUP> 
		  	 <tr>
		  	 	<td class = "label">&nbsp;</td>
		  	 	<td class = "label">&nbsp;</td>
		  	 	<td class = "tdright">-</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif02.name"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">-</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif02.date"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">-</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif02.comments"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">-</td>
		  	 </tr>		  	 
	  	</table>
	  </c:if>
	  <c:forEach items="${commentHCD}" var="item">
	  <c:if test="${item.ID_WF_APPROVAL != wfInfo.ID_WF_APPROVAL}">
	  	<table class="ApproveTable"> 
	  	<COLGROUP>
	  		<COL width="10%"/>
	  		<COL width="60%"/>
	  		<COL width="30%"/>
	  	</COLGROUP> 
		  	 <tr>
		  	 	<td class = "label">&nbsp;</td>
		  	 	<td class = "label">&nbsp;</td>
		  	 	<td class = "tdright">
	  	 			<div class="<t:writeCodeValue codeList="COLOR_CODE" key="${item.APPR_STATUS }"></t:writeCodeValue>">
	  	 				<t:writeCodeValue codeList="APPROVAL_STATUS_LIST" key="${item.APPR_STATUS}"></t:writeCodeValue>
	  	 			</div>
		  	 	</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif02.name"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">${item.USER_NAME}</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif02.date"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright"><fmt:formatDate value="${item.APPR_DATE}" pattern="dd/MM/yyyy"/></td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif02.comments"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright"><textarea rows="2" cols="40" readonly="readonly" style="overflow-y:visible;height:40px;">${item.APPR_COMMENT}</textarea></td>
		  	 </tr>		  	 
	  	</table>
	  </c:if>
	  <c:if test="${item.ID_WF_APPROVAL == wfInfo.ID_WF_APPROVAL && wfInfo.SECTION_GROUP == 'HCD-CN'}">
	  	<table class="ApproveTable"> 
	  	<COLGROUP>
	  		<COL width="10%"/>
	  		<COL width="60%"/>
	  		<COL width="30%"/>
	  	</COLGROUP> 
		  	 <tr>
		  	 	<td class = "label">&nbsp;</td>
		  	 	<td class = "label">&nbsp;</td>
		  	 	<td class = "tdright">
	  	 			<div class="<t:writeCodeValue codeList="COLOR_CODE" key="${item.APPR_STATUS }"></t:writeCodeValue>">
	  	 				<t:writeCodeValue codeList="APPROVAL_STATUS_LIST" key="${item.APPR_STATUS}"></t:writeCodeValue>
	  	 			</div>
		  	 	</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif02.name"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">${userInfo.user_name}</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif02.date"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright"><fmt:formatDate value="${totalPlan.sysDate}" pattern="dd/MM/yyyy"/></td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif02.comments"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<c:if test="${wfInfo.APPR_STATUS != 'AS2'}">
		  	 	<td class = "tdright"><textarea rows="2" cols="40" name="tbxApprComments" style="overflow-y:visible;height:40px;"></textarea></td>
		  	 	</c:if>
		  	 	<c:if test="${wfInfo.APPR_STATUS == 'AS2'}">
		  	 	<td class = "tdright"><textarea rows="2" cols="40" name="tbxApprComments" style="overflow-y:visible;height:40px;"></textarea></td>
		  	 	</c:if>
		  	 </tr>
	  	</table>
	  </c:if>
	  </c:forEach>
		</td>
	 </tr>
	</table>
	<!-- Corporate Division -->
	
	<!-- Customer Relation -->
	<c:if test="${wfInfo.SECTION_GROUP == 'BOP-CN'}">
	<table>
		<tr><td height="0px" /></tr>
	</table>
	<table cellpadding="2" cellspacing="2" width="100%" style="border: 1px solid #C2C0B5;">
		<tr style="background-color: #EAEAEA;">
			<td class="tdleftHeader" colspan="3"><b><bean:message key="screen.b_bif02.cusRelaDept"/></b></td>
		</tr>
	 <tr>
	  <td width="50%" valign="top">
	  	<table class="inputInfo">
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif02.boBy"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">
		  	 		<select name="cboBillingOpera" id="cboBillingOpera">
		  	 			<option value=""><bean:message key="screen.b_bif02.selectone"/></option>
		  	 			<c:forEach items="${colsNames}" var="item">
		  	 				<c:if test="${item.ID_USER != bifObject.BIL_OPBY}">
		  	 					<option value="${item.ID_USER}">${item.USER_NAME}</option>
		  	 				</c:if>
		  	 				<c:if test="${item.ID_USER == bifObject.BIL_OPBY}">
		  	 					<option value="${item.ID_USER}" selected="selected">${item.USER_NAME}</option>
		  	 				</c:if>
		  	 				<!--  
		  	 				<c:if test="${item.ID_USER == bifObject.ID_CONSLT}">
		  	 					<option value="${item.ID_USER}" selected="selected">${item.USER_NAME}</option>
		  	 				</c:if>
		  	 				-->
		  	 			</c:forEach>
		  	 		</select>
		  	 	</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif02.bifReceiveDate"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">
		  	 		<input type="text" name="tbxBIFReceivedD" id="tbxBIFReceivedD" class="DateTextBox" readonly="readonly" value="<fmt:formatDate value="${bifObject.DATE_BIFRCV }" pattern="dd/MM/yyyy"/>"/> <t:inputCalendar for="tbxBIFReceivedD" format="dd/MM/yyyy"/>
		  	 	</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label">
		  	 		<t:writeCodeValue codeList="LIST_TRANSACTIONTYPE" key="${bifObject.BIF_TYPE}"></t:writeCodeValue> 
		  	 		<bean:message key="screen.b_bif.reference"/><bean:message key="screen.b_bif.colon"/>
		  	 	</td>
		  	 	<td class = "tdright">
		  	 		<input type="text" name="tbxInvoiceRefer" id="tbxInvoiceRefer" value="${bifObject.BIL_REFNO }" maxlength="20"/>
		  	 	</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif02.printDate"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">
		  	 		<input type="text" name="tbxPrintDate" id="tbxPrintDate" class="DateTextBox" readonly="readonly" value="<fmt:formatDate value="${bifObject.DATE_PRINTED }" pattern="dd/MM/yyyy"/>"/> <t:inputCalendar for="tbxPrintDate" format="dd/MM/yyyy"/>
		  	 	</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif02.signDate"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright"><input type="text" name="tbxSignDate" id="tbxSignDate" class="DateTextBox" readonly="readonly" value="<fmt:formatDate value="${bifObject.DATE_SIGNED }" pattern="dd/MM/yyyy"/>"/> <t:inputCalendar for="tbxSignDate" format="dd/MM/yyyy"/></td>
		  	 </tr>	
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif02.customerAcNo"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">
		  	 		<c:if test="${empty bifObject.CUST_ACC_NO}">
		  	 			<input type="text" name="tbxCustomerACNo" id="tbxCustomerACNo" maxlength="15"/>
		  	 		</c:if>
		  	 		<c:if test="${not empty bifObject.CUST_ACC_NO}">
		  	 			${bifObject.CUST_ACC_NO}
		  	 		</c:if>
		  	 	</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif02.billAmoutNo"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">
		  	 	<c:if test="${empty bifObject.ID_BILL_ACCOUNT}">
		  	 		<bean:message key="screen.b_bif._"/>
		  	 	</c:if>
		  	 	<c:if test="${not empty bifObject.ID_BILL_ACCOUNT}">
		  	 	<c:forEach items="${bifObject.ID_BILL_ACCOUNT}" var="item" varStatus="status">
		  	 	${item.ID_BILL_ACCOUNT}<c:if test="${status.last==false}">,</c:if>
		  	 	</c:forEach>
		  	 	</c:if>
		  	 	</td>
		  	 </tr>
	  	</table>
	  </td>
	  <td width="50%" style="background-color: #F5CCCC;">
	  <c:forEach items="${commentBOP}" var="item">
	  <c:if test="${item.ID_WF_APPROVAL != wfInfo.ID_WF_APPROVAL}">
	  	<table class="ApproveTable"> 
	  	<COLGROUP>
	  		<COL width="10%"/>
	  		<COL width="60%"/>
	  		<COL width="30%"/>
	  	</COLGROUP> 
		  	 <tr>
		  	 	<td class = "label">&nbsp;</td>
		  	 	<td class = "label">&nbsp;</td>
		  	 	<td class = "tdright">
	  	 			<div class="<t:writeCodeValue codeList="COLOR_CODE" key="${item.APPR_STATUS }"></t:writeCodeValue>">
	  	 				<t:writeCodeValue codeList="APPROVAL_STATUS_LIST" key="${item.APPR_STATUS}"></t:writeCodeValue>
	  	 			</div>
		  	 	</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif02.name"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">${item.USER_NAME}</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif02.date"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright"><fmt:formatDate value="${item.APPR_DATE}" pattern="dd/MM/yyyy"/></td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif02.comments"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright"><textarea rows="2" cols="40" readonly="readonly" style="overflow-y:visible;height:40px;">${item.APPR_COMMENT}</textarea></td>
		  	 </tr>		  	 
	  	</table>
	  	</c:if>
	  	<c:if test="${item.ID_WF_APPROVAL == wfInfo.ID_WF_APPROVAL}">
	  	<table class="ApproveTable"> 
	  	<COLGROUP>
	  		<COL width="10%"/>
	  		<COL width="60%"/>
	  		<COL width="30%"/>
	  	</COLGROUP> 
		  	 <tr>
		  	 	<td class = "label">&nbsp;</td>
		  	 	<td class = "label">&nbsp;</td>
		  	 	<td class = "tdright">
	  	 			<div class="<t:writeCodeValue codeList="COLOR_CODE" key="${item.APPR_STATUS }"></t:writeCodeValue>">
	  	 				<t:writeCodeValue codeList="APPROVAL_STATUS_LIST" key="${item.APPR_STATUS}"></t:writeCodeValue>
	  	 			</div>
		  	 	</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif02.name"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">${item.USER_NAME}</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif02.date"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright"><fmt:formatDate value="${totalPlan.sysDate}" pattern="dd/MM/yyyy"/></td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif02.comments"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">
		  	 		<c:if test="${wfInfo.APPR_STATUS != 'AS2'}">
		  	 	<textarea rows="2" cols="40" name="tbxApprComments" id="tbxApprComments" onkeypress="return imposeMaxLength(this, 150);" style="overflow-y:visible;height:40px;">${item.APPR_COMMENT}</textarea>
		  	 		</c:if>
		  	 		<c:if test="${wfInfo.APPR_STATUS == 'AS2'}">
		  	 	<textarea rows="2" cols="40" name="tbxApprComments" disabled="disabled" style="overflow-y:visible;height:40px;">${item.APPR_COMMENT}</textarea>
		  	 		</c:if>
		  	 	</td>
		  	 </tr>		  	 
	  	</table>
	  	</c:if>
	  </c:forEach>
		</td>
	 </tr>
	</table>
	</c:if>
	<!-- Customer Relation -->
	
	<!-- Fields check data modify -->
	<c:forEach items="${colsNames}" var="item">
		<c:if test="${item.ID_USER == bifObject.ID_CONSLT}">
			<input type="hidden" id="cboBillingOpera_old" value="${item.ID_USER}"/>
		</c:if>
	</c:forEach>
	<input type="hidden" id="tbxBIFReceivedD_old" value="${tbxBIFReceivedD}"/>
	<input type="hidden" id="tbxInvoiceRefer_old" value="${tbxInvoiceRefer}"/>
	<input type="hidden" id="tbxPrintDate_old" value="${tbxPrintDate}"/>
	<input type="hidden" id="tbxSignDate_old" value="${tbxSignDate}"/>
	<input type="hidden" id="tbxApprComments_old" value="${tbxApprComments}"/>
	<!-- Fields check data modify -->
	
	<table>
		<tr><td height="0px" /></tr>
	</table>

	<table class="buttonGroup" cellpadding="0" cellspacing="0">
  	<tr>
		<td>
			<c:if test="${wfInfo.SECTION_GROUP == 'BOP-CN'}">
				<input type="button" class="button" value="<bean:message key="screen.b_bif.save"/>" name="forward_save" onclick="javascript: onSave();" />
			</c:if>
			<c:if test="${wfInfo.APPR_STATUS != 'AS2'}">
				<c:choose>
					<c:when test="${pagAction != null && pagAction.BUTTON_APPROVAL_DISABLED}">
						<!-- button was disabled -->
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${wfInfo.SECTION_GROUP == 'BOP-CN'}">
								<input type="button" class="button1" value="<bean:message key="screen.b_bif.verify"/>" name="forward_approval" onclick="javascript: doApproval();"/>
							</c:when>
							<c:otherwise>
								<input type="button" class="button1" value="<bean:message key="screen.b_bif.approve"/>" name="forward_approval" onclick="javascript: doApproval();"/>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${pagAction != null && pagAction.BUTTON_REJECT_DISABLED}">
						<!-- button was disabled -->
					</c:when>
					<c:otherwise>
						<input type="button" class="button1" value="<bean:message key="screen.b_bif.reject"/>" name="forward_reject" onclick="javascript: doReject();"/>
					</c:otherwise>
				</c:choose>
			</c:if>
			<input type="button" class="button" value="<bean:message key="screen.b_bif.exit"/>" name="forward_exit" onclick="clickExit()" />
		</td>	
	</tr>
	</table>
	<div class="show" id="errms" style="color: red;font-style: italic;">
	</div> 
	<div class="message">
		<ts:messages id="message" message="true">
			<bean:write name="message"/>
		</ts:messages>
	</div>
	<div class="error">
		<html:messages id="message">
			<bean:write name="message"/><br/>
		</html:messages>
	</div>
	</ts:form>
</body>
</html>