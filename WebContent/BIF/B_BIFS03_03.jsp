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
   	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
   	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/BIF/css/bifStyle.css"/>
   	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>			
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
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
			document.forms[0].action = '<%=request.getContextPath()%>/BIF/B_BIFS03_03_01BL.do';		
		}
	 	
	 	function clickExit(){
	 		if (MsgBox.POPEXT() == MsgBox.YES_CONFIRM) {
				document.forms[0].action = '<%=request.getContextPath()%>/BIF/B_BIFS01BL.do';
				document.forms[0].submit();
			}
		}
		
		function clickEdit(){
			document.forms[0].hidAction.value = "edit";
			document.forms[0].submit();
		}
		
		function doObtainApproval() {
			if (MsgBox.POPOBT("") == MsgBox.YES_CONFIRM) {
				document.forms[0].hidIsSave.value = "0";
				document.forms[0].hidAction.value = "obtain approval";
				document.forms[0].submit();	
			} else {
				document.forms[0].hidIsSave.value = "1";
				document.forms[0].hidAction.value = "obtain approval";
				document.forms[0].submit();
			}
		}
		
		function doDelete() {
			if (MsgBox.POPOBT("") == MsgBox.YES_CONFIRM) {
				document.forms[0].hidIsSave.value = "0";
				document.forms[0].hidAction.value = "delete";
				document.forms[0].submit();	
			} else {
				document.forms[0].hidIsSave.value = "1";
				document.forms[0].hidAction.value = "delete";
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
		function mbpInOneCheck(obj){
			if(obj.checked){
				obj.value = "1";
			}
			else{
				obj.value="0";
			}
		}
		function expAmtCheck(obj) {
			if(obj.checked) {
				obj.value = "1";
			}
			else {
				obj.value = "0";
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
<title>Billing Instruction Form - VIEW</title>
</head>
<body onload="javascript:init();alter.init(document.forms[0]);" onmousemove="alter.changePosition(event);">
	<t:defineCodeList id="LIST_COUNTRY"/>
	<ts:form action="B_BIFS03_03_01BL" enctype="multipart/form-data" method="POST">
	<html:hidden property="hidAction" value="edit2"/>
	<html:hidden property="hidIsSave" value="1"/>
	<html:hidden property="hidDeleteListFileTFBif"/>
	<html:hidden property="hidDeleteListFileMRBif"/>
	<html:hidden property="hidDeleteListFileCRBif"/>
	<html:hidden property="hidDeleteListFileTLBif"/>
	<html:hidden property="hidDeleteListFilePOBif"/>
	<html:hidden property="hidDeleteListFileOTBif"/>
	<html:hidden property="hidDeleteListFileIVBif"/>
	<html:hidden property="hidDeleteListFileAGBif"/>
	<input type="hidden" name="fileName" id="fileName"/>
	<input type="hidden" name="id_doc" id="id_doc"/>
	<input type="hidden" value="${refurbish}" name="refurbish" id="refurbish"/>
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
		  	 	${bifObject.CUST_NAME}
		  	 	<input type="hidden" name="custName" value="${bifObject.CUST_NAME}">
		  	 	</td>
		  	 </tr>
		  	 <tr><td colspan="2">&nbsp;</td></tr>
		  	 <c:forEach items="${cusAdr}" var="item">
		  	 <c:if test="${item.ADR_TYPE == bifObject.ADR_TYPE}">
		  	 	<tr id="adr${item.ADR_TYPE}">
		  	 	<td class = "tdleft">
		  	 		<t:writeCodeValue codeList="LIST_ADDRESS" key="${item.ADR_TYPE}"></t:writeCodeValue>
		  	 	</td>
			  	 	<td class = "tdright">${item.ADR_LINE1}
			  	 	<input type="hidden" name="addr1" value="<bean:write name="item" property="ADR_LINE1"/>"/></td>
			  	 </tr>
			  	 <tr id="adr${item.ADR_TYPE}">
			  	 	<td class = "tdleft"></td>
			  	 	<td class = "tdright">${item.ADR_LINE2}
			  	 	<input type="hidden" name="addr2" value="<bean:write name="item" property="ADR_LINE2"/>"/>
			  	 	</td>
			  	 </tr>
			  	 <tr id="adr${item.ADR_TYPE}">
			  	 	<td class = "tdleft"></td>
			  	 	<td class = "tdright">${item.ADR_LINE3}
			  	 	<input type="hidden" name="addr3" value="<bean:write name="item" property="ADR_LINE3"/>"/>
			  	 	</td>
			  	 </tr>
			  	 <tr id="adr${item.ADR_TYPE}">
			  	 	<td class = "tdleft"></td>
			  	 	<td class = "tdright"><c:if test="${not empty item.ZIP_CODE or not empty item.COUNTRY}">${item.ZIP_CODE},<t:writeCodeValue codeList="LIST_COUNTRY" key="${item.COUNTRY}"/></c:if>
			  	 	<input type="hidden" name="zipCode" value="<bean:write name="item" property="ZIP_CODE"/>"/>
			    <input type="hidden" name="country" value="<t:writeCodeValue codeList="LIST_COUNTRY" key="${item.COUNTRY}"/>"/>
				<input type="hidden" name="countryCd" value="<bean:write name="item" property="COUNTRY"/>"/>
			  	 	</td>
			  	 </tr>
		  	 </c:if>
		  	 </c:forEach>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif.tel"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">${bifObject.TEL_NO}
		  	 	<input type="hidden" name="tel" value="${bifObject.TEL_NO}">
		  	 	</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif.fax"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">${bifObject.FAX_NO}
		  	 	<input type="hidden" name="fax" value="${bifObject.FAX_NO}">
		  	 	</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif.attn"/>&nbsp;<bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">
		  	 	    <c:if test="${cusInfo.CUSTOMER_TYPE eq 'CORP'}">
		  	 	        <c:if test="${not empty bifObject.CONTACT_TYPE or not empty bifObject.CONTACT_NAME}">
	  	 		            [${bifObject.CONTACT_TYPE}]${bifObject.CONTACT_NAME}
	  	 		            <input  type="hidden" name="cboAttn" value="${bifObject.CONTACT_NAME}">
	  	 		        </c:if>
	  	 		    </c:if>
		  	 	</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "tdleft" style="text-align:right">
		  	 		<bean:message key="screen.b_bif.email_to"/><bean:message key="screen.b_bif.colon"/>
		  	 	</td>
		  	 	<td class = "tdright">
		  	 		<c:if test="${cusInfo.CUSTOMER_TYPE eq 'CORP'}">
		  	 			<label id="emailto">${bifObject.EMAIL_TO}</label>
		  	 			<input  type="hidden" name="emailToAdd" value="${bifObject.EMAIL_TO}">
		  	 		</c:if>
		  	 	</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "tdleft" style="text-align:right">
		  	 		<bean:message key="screen.b_bif.email_cc"/><bean:message key="screen.b_bif.colon"/>
		  	 	</td>
		  	 	<td class = "tdright">
		  	 		<c:if test="${cusInfo.CUSTOMER_TYPE eq 'CORP'}">
		  	 			<label id="emailcc">${bifObject.EMAIL_CC}</label>
		  	 			<input  type="hidden" name="emailCcAdd" value="${bifObject.EMAIL_CC}">
		  	 		</c:if>
		  	 		
		  	 	</td>
		  	 </tr>
	  	</table>
	  </td>
	  <td width="40%" style="background-color: #EAEAEA;">
	  	<table class="subHeaderInner"> 
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif.bifReference"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">${bifObject.ID_REF}
		  	 		<input type="hidden" value="${bifObject.ID_REF}" name="idRef"/>
		  	 		<input type="hidden" value="${bifObject.ID_CUST}" name="idCust"/>
		  	 		<input type="hidden" value="${bifObject.ID_CUST_PLAN}" name="idCustPlan"/>
		  	 		<input type="hidden" value="${bifObject.BIF_TYPE}" name="bifType"/>
		  	 	</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif.requestedDate"/>&nbsp;<bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">${bifObject.DATE_REQ}</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif.qscReference"/>&nbsp;<bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">${bifObject.ID_QCS}
		  	 	</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif.quoReference"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">${bifObject.ID_QUO}
		  	 	</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif.customerPO"/>&nbsp;<bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">${bifObject.CUST_PO}</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif.consultantName"/>&nbsp;<bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">${bifObject.CONSLT_NAME}</td>
		  	 </tr>
		  	 <%--
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif.jobNo"/>&nbsp;<bean:message key="screen.b_bif.colon"/></td>
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
	   		<input type="checkbox" name="checkDeliveryEmail" value="1" checked disabled="disabled" onclick="emailCheck(this);">
	    </c:if>
	    <c:if test="${bifObject.DELIVERY_EMAIL != 1}">
	   		<input type="checkbox" name="checkDeliveryEmail" value="0" disabled="disabled" onclick="emailCheck(this);">
	    </c:if>
        <bean:message key="screen.b_bif.email"/>
	  </td>
	  <td width="15%"><input type="radio" name="rdbInstructionf" value="3"/><bean:message key="screen.b_bif.none"/></td>
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
	 	<td width="15%"><input type="radio" name="rdbCreditNoteType" value="2"/><bean:message key="screen.b_bif.UpDown"/></td>
	 	<td width="15%"><input type="radio" name="rdbCreditNoteType" value="3"/><bean:message key="screen.b_bif.Discount"/></td>
	 	<td width="15%"><input type="radio" name="rdbCreditNoteType" value="4"/><bean:message key="screen.b_bif.RefundOfDeposit"/></td>
	 	<td width="20%" align="left"><input type="radio" name="rdbCreditNoteType" value="5"/><bean:message key="screen.b_bif.Compensation"/></td>
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
	 <colgroup>
	 	<col width="10%"/>
	 	<col width="30%"/>
	 	<col width="30%"/>
	 	<col width="30%"/>
	 </colgroup>
	 <tr>
	 	<td class="header"><b><bean:message key="screen.b_bif.attachment"/></b></td>
	 		<c:choose>
	 			<c:when test="${uploadDisplay != '2'&& bifObject.ID_STATUS =='DS3'}">
	 				<td class="header"><span style="background-color: #C0C0C0"><bean:message key="screen.b_bif.AttTerminationForm"/></span>
	 				</td>	 	
	 				<td class="header"><span style="background-color: #C0C0C0"><bean:message key="screen.b_bif.AttMRTG"/></span>
	 				</td>	 	
	 				<td class="header"><span style="background-color: #C0C0C0"><bean:message key="screen.b_bif.AttChangeRequestForm"/></span>
	 				</td>
	 			</c:when>
	 			<c:otherwise>
	 				<td class="header">
	 					<span style="background-color: #C0C0C0;float:left;"><bean:message key="screen.b_bif.AttTerminationForm"/></span>
	 					<div class="uploadBound">
		 					<input type="button" class="uploadButton" id="fakeTF" name="pseudobutton" value="<bean:message key="screen.b_bif.uploadFile"/>">
		 					<input id="bifTF_file" type="file" name="bifTF_file" class="uploadFile" />
	 					</div>
	 				</td>	 	
	 				<td class="header">
	 					<span style="background-color: #C0C0C0;float:left;"><bean:message key="screen.b_bif.AttMRTG"/></span>
	 					<div class="uploadBound">
		 					<input type="button" class="uploadButton" id="fakeMR" name="pseudobutton" value="<bean:message key="screen.b_bif.uploadFile"/>" >
		 					<input id="bifMR_file" type="file" name="bifMR_file" class="uploadFile" />
	 					</div>
	 				</td>	 	
	 				<td class="header">
	 					<span style="background-color: #C0C0C0;float:left;"><bean:message key="screen.b_bif.AttChangeRequestForm"/></span>
	 					<div class="uploadBound">
		 					<input type="button" class="uploadButton" id="fakeCR" name="pseudobutton" value="<bean:message key="screen.b_bif.uploadFile"/>" >
		 					<input id="bifCR_file" type="file" name="bifCR_file" class="uploadFile" />
	 					</div>
	 				</td>
	 			</c:otherwise>
	 		</c:choose>
	 </tr>
	 	 <tr>
	 	<td>&nbsp;</td>
	 	<td id="attTF" valign="top">
	 		<table id="attachmentTFBif" style="border:0;width:100%;font-size:15px;">
	 		</table>
	 	</td>	 	
	 	<td id="attMR" valign="top">
	 		<table id="attachmentMRBif" style="border:0;width:100%;font-size:15px;">
	 		</table>
	 	</td>	 	
	 	<td id="attCR" valign="top">
	 		<table id="attachmentCRBif" style="border:0;width:100%;font-size:15px;">
	 		</table>
		</td>
	 </tr>
			<tr>
				<td class="header">&nbsp;</td>
				<c:choose>
					<c:when test="${uploadDisplay != '2'&& bifObject.ID_STATUS =='DS3'}">
						<td class="header"><span style="background-color: #C0C0C0"><bean:message
									key="screen.b_bif.AttTerminationLetter" /> </span>
						</td>
						<td class="header"><span style="background-color: #C0C0C0"><bean:message
									key="screen.b_bif.AttPurchaseOrder" /> </span>
						</td>
						<td class="header"><span style="background-color: #C0C0C0"><bean:message
									key="screen.b_bif.AttOthers" /> </span>
						</td>
					</c:when>
					<c:otherwise>
						<td class="header"><span
							style="background-color: #C0C0C0; float: left;"><bean:message
									key="screen.b_bif.AttTerminationLetter" />
						</span>
							<div class="uploadBound">
								<input type="button" class="uploadButton" id="fakeTL"
									name="pseudobutton"
									value="<bean:message key="screen.b_bif.uploadFile"/>">
								<input id="bifTL_file" type="file" name="bifTL_file"
									class="uploadFile" />
							</div></td>
						<td class="header"><span
							style="background-color: #C0C0C0; float: left;"><bean:message
									key="screen.b_bif.AttPurchaseOrder" />
						</span>
							<div class="uploadBound">
								<input type="button" class="uploadButton" id="fakePO"
									name="pseudobutton"
									value="<bean:message key="screen.b_bif.uploadFile"/>">
								<input id="bifPO_file" type="file" name="bifPO_file"
									class="uploadFile" />
							</div></td>
						<td class="header"><span
							style="background-color: #C0C0C0; float: left;"><bean:message
									key="screen.b_bif.AttOthers" />
						</span>
							<div class="uploadBound">
								<input type="button" class="uploadButton" id="fakeOT"
									name="pseudobutton"
									value="<bean:message key="screen.b_bif.uploadFile"/>">
								<input id="bifOT_file" type="file" name="bifOT_file"
									class="uploadFile" />
							</div></td>
					</c:otherwise>
				</c:choose>

			</tr>
			<tr>
	 	<td>&nbsp;</td>
	 	<td id="attTL" valign="top">
	 		<table id="attachmentTLBif" style="border:0;width:100%;font-size:15px;">
	 		</table>
	 	</td>	 	
	 	<td id="attPO" valign="top">
	 		<table id="attachmentPOBif" style="border:0;width:100%;font-size:15px;">
	 		</table>
	 	</td>	 	
	 	<td id="attOT" valign="top">
	 		<table id="attachmentOTBif" style="border:0;width:100%;font-size:15px;">
	 		</table>
		</td>
	 </tr>
			<tr>
				<td class="header">&nbsp;</td>
				<c:choose>
					<c:when test="${uploadDisplay != '2'&& bifObject.ID_STATUS =='DS3'}">
						<td class="header"><span style="background-color: #C0C0C0"><bean:message
									key="screen.b_bif.AttInvoice" />
						</span></td>
						<td class="header"><span style="background-color: #C0C0C0"><bean:message
									key="screen.b_bif.AttAgreement" />
						</span></td>
						<td class="header"></td>
					</c:when>
					<c:otherwise>
						<td class="header"><span
							style="background-color: #C0C0C0; float: left;"><bean:message
									key="screen.b_bif.AttInvoice" />
						</span>
							<div class="uploadBound">
								<input type="button" class="uploadButton" id="fakeIV"
									name="pseudobutton"
									value="<bean:message key="screen.b_bif.uploadFile"/>">
								<input id="bifIV_file" type="file" name="bifIV_file"
									class="uploadFile" />
							</div></td>
						<td class="header"><span
							style="background-color: #C0C0C0; float: left;"><bean:message
									key="screen.b_bif.AttAgreement" />
						</span>
							<div class="uploadBound">
								<input type="button" class="uploadButton" id="fakeAG"
									name="pseudobutton"
									value="<bean:message key="screen.b_bif.uploadFile"/>">
								<input id="bifAG_file" type="file" name="bifAG_file"
									class="uploadFile" />
							</div></td>
						<td class="header"></td>
					</c:otherwise>
				</c:choose>
			</tr>
			<tr>
	 	<td>&nbsp;</td>
					<td id="attSC" valign="top">
						<table id="attachmentIVBif"
							style="border: 0; width: 100%; font-size: 15px;">
						</table></td>
					<td id="attQP" valign="top">
						<table id="attachmentAGBif"
							style="border: 0; width: 100%; font-size: 15px;">
						</table></td>
					<td></td>
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
	        <c:when test="${bifObject.IS_BAC_EXIST eq '1'}">
	            <c:if test="${bifObject.checkMultipleInOneInvoice == 1}">
	                <input type="checkbox" name="checkMultipleInOneInvoice" value="1" checked onclick="mbpInOneCheck(this);" disabled="disabled">
	            </c:if>
	            <c:if test="${bifObject.checkMultipleInOneInvoice != 1}">
	                <input type="checkbox" name="checkMultipleInOneInvoice" value="0" onclick="mbpInOneCheck(this);" disabled="disabled">
	            </c:if>
	            <input type="hidden" name="checkMultipleInOneInvoice" value="${bifObject.checkMultipleInOneInvoice}">
	        </c:when>
	        <c:otherwise>
	            <c:if test="${bifObject.checkMultipleInOneInvoice == 1}">
	                <input type="checkbox" name="checkMultipleInOneInvoice" value="1" checked onclick="mbpInOneCheck(this);">
	            </c:if>
	            <c:if test="${bifObject.checkMultipleInOneInvoice != 1}">
	                <input type="checkbox" name="checkMultipleInOneInvoice" value="0" onclick="mbpInOneCheck(this);">
	            </c:if>
	            <%-- <input type="hidden" name="checkMultipleInOneInvoice" value="${bifObject.checkMultipleInOneInvoice}"> --%>
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
	        <c:when test="${bifObject.IS_BAC_EXIST eq '1'}">
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
	 		   <%--  <input type="hidden" name="chkExportAmount" value="${chkExportAmount}"> --%>
	        </c:otherwise>
	        </c:choose>
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
	  		<tr style="background-color: #EAEAEA;">
		  	  <td class="tdleftHeader" colspan="2"><b><bean:message key="screen.b_bif02.verifiedby"/></b>
		  	  </td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif02.name"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright" style="text-align:left;">
		  	 		<c:choose>
		  	 			<c:when test="${not empty bifObject.VERIFY_BY_USER_NAME}">
			  	 			${bifObject.VERIFY_BY_USER_NAME}
			  	 		</c:when>
			  	 		<c:otherwise>
			  	 			-
			  	 		</c:otherwise>
		  	 		</c:choose>
		  	 	</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif02.date"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright" style="text-align:left;">
		  	 		<c:choose>
		  	 			<c:when test="${not empty bifObject.DATE_OBT_APPROVAL}">
			  	 			<fmt:formatDate value="${bifObject.DATE_OBT_APPROVAL}" pattern="dd/MM/yyyy"/>
			  	 		</c:when>
			  	 		<c:otherwise>
			  	 			-
			  	 		</c:otherwise>
		  	 		</c:choose>
		  	 	</td>
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
	  <td width="40%">
	  </td>
	  <td width="40%" style="background-color: #F5CCCC;">
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
	<!-- Corporate Division -->
	
	<!-- Customer Relation -->
	<table>
		<tr><td height="0px" /></tr>
	</table>
	<table cellpadding="2" cellspacing="2" width="100%" style="border: 1px solid #C2C0B5;">
		<tr style="background-color: #EAEAEA;">
			<td class="tdleftHeader" colspan="3"><b><bean:message key="screen.b_bif02.cusRelaDept"/></b></td>
		</tr>
	 <tr>
	  <td width="40%" valign="top">
	  	<table class="subHeaderInner">
	  	<colgroup>
	  		<col width="30%"/>
	  		<col width="70%"/>
	  	</colgroup>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif02.boBy"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">
		  	 	<c:if test="${empty bifObject.BIL_OPBY }">
		  	 		<bean:message key="screen.b_bif._"/>
		  	 	</c:if>
		  	 	<c:if test="${not empty bifObject.BIL_OPBY }">
		  	 		${bifObject.BIL_OPBY_NAME }
		  	 	</c:if>
		  	 	</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif02.bifReceiveDate"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">
		  	 	<c:if test="${empty bifObject.DATE_BIFRCV }">
		  	 		<bean:message key="screen.b_bif._"/>
		  	 	</c:if>
		  	 	<c:if test="${not empty bifObject.DATE_BIFRCV }">
		  	 		<fmt:formatDate value="${bifObject.DATE_BIFRCV }" pattern="dd/MM/yyyy"/>
		  	 	</c:if>
		  	 	</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label">
		  	 		<t:writeCodeValue codeList="LIST_TRANSACTIONTYPE" key="${bifObject.BIF_TYPE}"></t:writeCodeValue> 
		  	 		<bean:message key="screen.b_bif.reference"/><bean:message key="screen.b_bif.colon"/>
		  	 	</td>
		  	 	<td class = "tdright">
		  	 	<c:if test="${empty bifObject.BIL_REFNO }">
		  	 		<bean:message key="screen.b_bif._"/>
		  	 	</c:if>
		  	 	<c:if test="${not empty bifObject.BIL_REFNO }">
		  	 		${bifObject.BIL_REFNO }
		  	 	</c:if>
		  	 	</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif02.printDate"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">
		  	 	<c:if test="${empty bifObject.DATE_PRINTED }">
		  	 		<bean:message key="screen.b_bif._"/>
		  	 	</c:if>
		  	 	<c:if test="${not empty bifObject.DATE_PRINTED }">
		  	 		<fmt:formatDate value="${bifObject.DATE_PRINTED }" pattern="dd/MM/yyyy"/>
		  	 	</c:if>
		  	 	</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif02.signDate"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">
		  	 	<c:if test="${empty bifObject.DATE_SIGNED }">
		  	 		<bean:message key="screen.b_bif._"/>
		  	 	</c:if>
		  	 	<c:if test="${not empty bifObject.DATE_SIGNED }">
		  	 		<fmt:formatDate value="${bifObject.DATE_SIGNED }" pattern="dd/MM/yyyy"/>
		  	 	</c:if>
		  	 	</td>
		  	 </tr>	 
		  	 <tr>
		  	 	<td class = "label"><bean:message key="screen.b_bif02.customerAcNo"/><bean:message key="screen.b_bif.colon"/></td>
		  	 	<td class = "tdright">
		  	 		<c:if test="${empty bifObject.CUST_ACC_NO}">
		  	 			<bean:message key="screen.b_bif._"/>
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
						    ${item.ID_BILL_ACCOUNT}
				        <c:if test="${status.last==false}">,</c:if>
		  	 		</c:forEach>
		  	 		</c:if>
		  	 	</td>
		  	 </tr>
	  	</table>
	  </td>
	  <td width="40%" style="background-color: #F5CCCC;">
	  <c:forEach items="${commentBOP}" var="item">
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
	  	 				<c:choose>
	  	 					<c:when test="${item.APPR_STATUS == 'AS2'}">
	  	 						<bean:message key="screen.b_bif.verified"/>
	  	 					</c:when>
	  	 					<c:otherwise>
	  	 						<t:writeCodeValue codeList="APPROVAL_STATUS_LIST" key="${item.APPR_STATUS}"></t:writeCodeValue>
	  	 					</c:otherwise>
	  	 				</c:choose>
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
	<!-- Customer Relation -->
	
	<table>
		<tr><td height="0px" /></tr>
	</table>
	<!-- Prepare By -->
	
	<table>
		<tr><td height="0px" /></tr>
	</table>

	<table class="buttonGroup" cellpadding="0" cellspacing="0">
  	<tr>
		<td>
			<input type="submit" class="button" value="<bean:message key="screen.b_bif.save"/>" name="forward_edit"/>
			<%--
			<input type="button" class="button1" value="<bean:message key="screen.b_bif.delete"/>"  onclick="javascript: doDelete();" />
			<c:if test="${screenObject.isObtainable}">
				<c:if test="${screenObject.is_ObtainA == '0'}">
					<input type="button" class="button1" value="<bean:message key="screen.b_bif.obtainApproval"/>" name="forward_Obtain_Approval" onclick="javascript: doObtainApproval();"/>
				</c:if>
			</c:if>
			--%>
			<input type="button" class="button" value="<bean:message key="screen.b_bif.exit"/>" name="forward_exit" onclick="clickExit();" />			
		</td>	
	</tr>
	</table>
	<div class="show" id="errms" style="color: red;font-style: italic;">
	</div> 
	</ts:form>
	<bean:size collection="${attachmentsTFBif}" name="sizeTF" id="sizeTF"/>
	<bean:size collection="${attachmentsMRBif}" name="sizeMR" id="sizeMR"/>
	<bean:size collection="${attachmentsCRBif}" name="sizeCR" id="sizeCR"/>
	<bean:size collection="${attachmentsTLBif}" name="sizeTL" id="sizeTL"/>
	<bean:size collection="${attachmentsPOBif}" name="sizePO" id="sizePO"/>
	<bean:size collection="${attachmentsOTBif}" name="sizeOT" id="sizeOT"/>
	<bean:size collection="${attachmentsIVBif}" name="sizeIV" id="sizeIV"/>
	<bean:size collection="${attachmentsAGBif}" name="sizeAG" id="sizeAG"/>
	<script>
	if(document.getElementById('bifTF_file')!= null){
		var btnUpload = document.getElementById("fakeTF");
		var multi_selector = new MultiSelector( 'attachmentTFBif', 'firstUpload','listFileTFBif', 5 - ${sizeTF}, btnUpload, alter);	
		multi_selector.setMessage(MsgBox);	
		multi_selector.addElement( document.getElementById( 'bifTF_file' ) );
	}
	if(document.getElementById('bifMR_file')!= null){
		var btnUpload = document.getElementById("fakeMR");
		var multi_selector = new MultiSelector( 'attachmentMRBif', 'secondUpload','listFileMRBif', 5 - ${sizeMR}, btnUpload, alter);	
		multi_selector.setMessage(MsgBox);	
		multi_selector.addElement( document.getElementById( 'bifMR_file' ) );
	}
	if(document.getElementById('bifCR_file')!= null){
		var btnUpload = document.getElementById("fakeCR");
		var multi_selector = new MultiSelector( 'attachmentCRBif', 'thirdUpload','listFileCRBif', 5 - ${sizeCR}, btnUpload, alter);	
		multi_selector.setMessage(MsgBox);	
		multi_selector.addElement( document.getElementById( 'bifCR_file' ) );
	}
	if(document.getElementById('bifTL_file')!= null){
		var btnUpload = document.getElementById("fakeTL");
		var multi_selector = new MultiSelector( 'attachmentTLBif', 'firstUpload','listFileTLBif', 5 - ${sizeTL}, btnUpload, alter);	
		multi_selector.setMessage(MsgBox);	
		multi_selector.addElement( document.getElementById( 'bifTL_file' ) );
	}
	if(document.getElementById('bifPO_file')!= null){
		var btnUpload = document.getElementById("fakePO");
		var multi_selector = new MultiSelector( 'attachmentPOBif', 'secondUpload','listFilePOBif', 5 - ${sizePO}, btnUpload, alter);	
		multi_selector.setMessage(MsgBox);	
		multi_selector.addElement( document.getElementById( 'bifPO_file' ) );
	}
	if(document.getElementById('bifOT_file')!= null){
		var btnUpload = document.getElementById("fakeOT");
		var multi_selector = new MultiSelector( 'attachmentOTBif', 'thirdUpload','listFileOTBif', 5 - ${sizeOT}, btnUpload, alter);	
		multi_selector.setMessage(MsgBox);	
		multi_selector.addElement( document.getElementById( 'bifOT_file' ) );
	}
	if(document.getElementById('bifIV_file')!= null){
		var btnUpload = document.getElementById("fakeIV");
		var multi_selector = new MultiSelector( 'attachmentIVBif', 'firstUpload','listFileIVBif', 5 - ${sizeIV}, btnUpload, alter);	
		multi_selector.setMessage(MsgBox);	
		multi_selector.addElement( document.getElementById( 'bifIV_file' ) );
	}
	if(document.getElementById('bifAG_file')!= null){
		var btnUpload = document.getElementById("fakeAG");
		var multi_selector = new MultiSelector( 'attachmentAGBif', 'secondUpload','listFileAGBif', 5 - ${sizeAG}, btnUpload, alter);	
		multi_selector.setMessage(MsgBox);	
		multi_selector.addElement( document.getElementById( 'bifAG_file' ) );
	}
	</script>
	
	<!-- add uploaded file start -->
	<c:forEach items="${attachmentsTFBif}" var="item">
		<script type="text/javascript">
			var ref = '${bifObject.ID_STATUS}';
			var table_id = 'attachmentTFBif';
			var file_name = "${item.filename}";
			var doc_id = '${item.id_doc}';
			var uploadObject = document.getElementById("bifTF_file");
			var deleteObject = document.forms[0].hidDeleteListFileTFBif;
			if (uploadObject != undefined) {
				uploadObject.multi_selector.addServerRow(table_id, file_name, doc_id, deleteObject, ref);
			}
		</script>
	</c:forEach>
	<c:forEach items="${attachmentsMRBif}" var="item">
		<script type="text/javascript">
			var ref = '${bifObject.ID_STATUS}';
			var table_id = 'attachmentMRBif';
			var file_name = "${item.filename}";
			var doc_id = '${item.id_doc}';
			var uploadObject = document.getElementById("bifMR_file");
			var deleteObject = document.forms[0].hidDeleteListFileMRBif;
			if (uploadObject != undefined) {
				uploadObject.multi_selector.addServerRow(table_id, file_name, doc_id, deleteObject, ref);
			}
		</script>
	</c:forEach>
	<c:forEach items="${attachmentsCRBif}" var="item">
		<script type="text/javascript">
			var ref = '${bifObject.ID_STATUS}';
			var table_id = 'attachmentCRBif';
			var file_name = "${item.filename}";
			var doc_id = '${item.id_doc}';
			var uploadObject = document.getElementById("bifCR_file");
			var deleteObject = document.forms[0].hidDeleteListFileCRBif;
			if (uploadObject != undefined) {
				uploadObject.multi_selector.addServerRow(table_id, file_name, doc_id, deleteObject, ref);
			}
		</script>
	</c:forEach>
	<c:forEach items="${attachmentsTLBif}" var="item">
		<script type="text/javascript">
			var ref = '${bifObject.ID_STATUS}';
			var table_id = 'attachmentTLBif';
			var file_name = "${item.filename}";
			var doc_id = '${item.id_doc}';
			var uploadObject = document.getElementById("bifTL_file");
			var deleteObject = document.forms[0].hidDeleteListFileTLBif;
			if (uploadObject != undefined) {
				uploadObject.multi_selector.addServerRow(table_id, file_name, doc_id, deleteObject, ref);
			}
		</script>
	</c:forEach>
	<c:forEach items="${attachmentsPOBif}" var="item">
		<script type="text/javascript">
			var ref = '${bifObject.ID_STATUS}';
			var table_id = 'attachmentPOBif';
			var file_name = "${item.filename}";
			var doc_id = '${item.id_doc}';
			var uploadObject = document.getElementById("bifPO_file");
			var deleteObject = document.forms[0].hidDeleteListFilePOBif;
			if (uploadObject != undefined) {
				uploadObject.multi_selector.addServerRow(table_id, file_name, doc_id, deleteObject, ref);
			}
		</script>
	</c:forEach>
	<c:forEach items="${attachmentsOTBif}" var="item">
		<script type="text/javascript">
			var ref = '${bifObject.ID_STATUS}';
			var table_id = 'attachmentOTBif';
			var file_name = "${item.filename}";
			var doc_id = '${item.id_doc}';
			var uploadObject = document.getElementById("bifOT_file");
			var deleteObject = document.forms[0].hidDeleteListFileOTBif;
			if (uploadObject != undefined) {
				uploadObject.multi_selector.addServerRow(table_id, file_name, doc_id, deleteObject, ref);
			}
		</script>
	</c:forEach>
	<c:forEach items="${attachmentsIVBif}" var="item">
		<script type="text/javascript">
			var ref = '${bifObject.ID_STATUS}';
			var table_id = 'attachmentIVBif';
			var file_name = "${item.filename}";
			var doc_id = '${item.id_doc}';
			var uploadObject = document.getElementById("bifIV_file");
			var deleteObject = document.forms[0].hidDeleteListFileIVBif;
			if (uploadObject != undefined) {
				uploadObject.multi_selector.addServerRow(table_id, file_name, doc_id, deleteObject, ref);
			}
		</script>
	</c:forEach>
	<c:forEach items="${attachmentsAGBif}" var="item">
		<script type="text/javascript">
			var ref = '${bifObject.ID_STATUS}';
			var table_id = 'attachmentAGBif';
			var file_name = "${item.filename}";
			var doc_id = '${item.id_doc}';
			var uploadObject = document.getElementById("bifAG_file");
			var deleteObject = document.forms[0].hidDeleteListFileAGBif;
			if (uploadObject != undefined) {
				uploadObject.multi_selector.addServerRow(table_id, file_name, doc_id, deleteObject, ref);
			}
		</script>
	</c:forEach>
	<!-- add uploaded file end -->
</body>
</html>