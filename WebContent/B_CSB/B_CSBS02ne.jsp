<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
	<link href="<%=request.getContextPath()%>/B_CSB/css/b_csbs02.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
	<script language="javascript">
	function reloadPaymentByBAC(obj) {
		var pmtMethod = obj.options[obj.selectedIndex].value;
		
		var paymentRefDetailValue1 = fncGetPaymentRefDetail(pmtMethod, "paymentRefDetails1");
		var paymentRefDetailValue2  = fncGetPaymentRefDetail(pmtMethod, "paymentRefDetails2");
		
		document.getElementById("labPaymentRefDetail1").innerHTML = paymentRefDetailValue1;
		document.getElementById("labPaymentRefDetail2").innerHTML = paymentRefDetailValue2;
	}
	
	function fncGetPaymentRefDetail(pmtMethod, paymentRefDetailId){
		var paymentRefDetails = document.getElementById(paymentRefDetailId);
		for(var i=0;i<paymentRefDetails.length;i++){
			if(pmtMethod == paymentRefDetails.options[i].value){
				return paymentRefDetails.options[i].text;
			}
		}
		return "";
	}
	
	function reloadPayment(obj) {
		document.forms[0].action = "<%=request.getContextPath()%>/B_CSB/B_CSBS02AD.do?actionFlg=pmtMethodChange";
		document.forms[0].submit();
	}
	
	function reloadCurrency(obj) {
		document.forms[0].action = "<%=request.getContextPath()%>/B_CSB/B_CSBS02AD.do?actionFlg=currencyChange";
		document.forms[0].submit();
	}
	
	function reloadPayer(obj) {
		document.forms[0].action = "<%=request.getContextPath()%>/B_CSB/B_CSBS02AD.do?actionFlg=payerChange";
		document.forms[0].submit();
	}
	
	function reloadBillAccountNo(obj){
		document.forms[0].action = "<%=request.getContextPath()%>/B_CSB/B_CSBS02AD.do?actionFlg=billAccNoChange";
		document.forms[0].submit();
	}
	
	function confirm_save() {
        var numDbInfo = document.getElementById("numDbInfo").value;
        var selected = 0;
        for(var i = 0; i < numDbInfo; i++) {
            if(document.getElementById("debitInfos["+i+"].APPLIED").value == "1") {
                selected = 1;
                if(!document.getElementById("debitInfos["+i+"].AMT_PAID").value)
                    document.getElementById("debitInfos["+i+"].AMT_PAID").value = 0;
                if(!document.getElementById("debitInfos["+i+"].FOREX_LOSS").value)
                    document.getElementById("debitInfos["+i+"].FOREX_LOSS").value = 0;
                if(!document.getElementById("debitInfos["+i+"].FOREX_GAIN").value)
                    document.getElementById("debitInfos["+i+"].FOREX_GAIN").value = 0;
            }           
        }
        
        if(selected == 0){
            var MsgBox = new messageBox();
            
            if (MsgBox.POPCFM("No record selected. Are you sure you want to save Cash Book?") == MsgBox.YES_CONFIRM)
                document.forms[0].submit();
            else
                return false;
            
        }else{
            document.forms[0].submit();
        }
        
    }
	
	function comfirm_exit()	{
		var mess = new messageBox();
		var agree = mess.POPEXT();	
		var receiptNo = $("input[name='receiptNo']").val();
		if (agree == 1) {
	        if( receiptNo==undefined || receiptNo==null || receiptNo=="" ){
	            document.forms[0].action = "<%=request.getContextPath()%>/B_CSB/B_CSBR01BLogic.do";
	            document.forms[0].submit();
	        }else{
	            window.location = "<%=request.getContextPath()%>/B_CSB/B_CSBS02V.do?idRef="+receiptNo+"&action=view";
	        }
		}	
	}
	
	function setAmtReceived(obj) {
		document.getElementById("amtReceived").value = obj.value;
		calDebitInfo();
	}
	
	function editDbInfo(checkBox, index) {
		if(checkBox.checked) {
			document.getElementById("debitInfos["+index+"].APPLIED").value = "1";
			document.getElementById("debitInfos["+index+"].AMT_PAID").readOnly = false;
			document.getElementById("debitInfos["+index+"].FOREX_LOSS").readOnly = false;
			document.getElementById("debitInfos["+index+"].FOREX_GAIN").readOnly = false;
			calPaymentInfo(index);
		}
		else {
			document.getElementById("debitInfos["+index+"].APPLIED").value = "0";
			document.getElementById("debitInfos["+index+"].AMT_PAID").readOnly = true;
			document.getElementById("debitInfos["+index+"].FOREX_LOSS").readOnly = true;
			document.getElementById("debitInfos["+index+"].FOREX_GAIN").readOnly = true;
			document.getElementById("debitInfos["+index+"].AMT_PAID").value = "0";
			document.getElementById("debitInfos["+index+"].FOREX_LOSS").value = "0";
			document.getElementById("debitInfos["+index+"].FOREX_GAIN").value = "0";
		}
		calDebitInfo();
	}
	
	function calPaymentInfo(index){
		var billType = document.getElementById("debitInfos["+index+"].BILL_TYPE").value;
		//if("DN" == billType){
			var amtDue = 0;
			var balanceAmt = 0;
			var paymentAmt = 0;
			
			var amtDueValue = document.getElementById("debitInfos["+index+"].AMT_DUE").value;
			var paymentValue = document.getElementById("debitInfos["+index+"].AMT_PAID").value;
			var balanceAmtValue = document.getElementById("hiddenBalanceAmt").value;
			if(isNaN(amtDueValue)){
				amtDue = 0;
			} else {
				amtDue = Number(amtDueValue);
			}
			if(isNaN(paymentValue)){
				paymentAmt = 0;
			} else {
				paymentAmt = Number(paymentValue);
			}	
			if(isNaN(balanceAmtValue)){
				balanceAmt = 0;
			} else {
				balanceAmt = Number(balanceAmtValue);
			}

			// If is full payment, return method.
			if(amtDueValue == paymentAmt){
				return;
			}
			
			if (balanceAmt > 0) {
				var payment = 0;
				var amountReceived = accAdd(paymentAmt, balanceAmt);
				if(amtDue <= amountReceived){
					payment = amtDue;
				}else{
					payment = amountReceived;
				}
				document.getElementById("debitInfos["+index+"].AMT_PAID").value = payment;
			}
		//}
	}
	
	function fncPaymentInfoLoad(){
		var checkboxes = $("input[type='checkbox']"); 
		for(var i=0;i<checkboxes.length;i++){
			if(checkboxes[i].checked){
				calPaymentInfo(i);
				calDebitInfo();
			}
		}
	}
	
	function calDebitInfo() {
		var numDbInfo = document.getElementById("numDbInfo").value;
		var origAmt = 0;
		var amtDue = 0;
		var payment = 0;
		var forexLoss = 0;
		var forexGain = 0;
		for(var i = 0; i < numDbInfo; i++) {
			var billAmountValue = document.getElementById("debitInfos["+i+"].BILL_AMOUNT").value;
			var amtDueValue = document.getElementById("debitInfos["+i+"].AMT_DUE").value;
			var paymentValue = document.getElementById("debitInfos["+i+"].AMT_PAID").value;
			var forexLossValue = document.getElementById("debitInfos["+i+"].FOREX_LOSS").value;
			var forexGainValue = document.getElementById("debitInfos["+i+"].FOREX_GAIN").value;
			if(isNaN(billAmountValue)){
				billAmountValue = 0;
			}
			if(isNaN(amtDueValue)){
				amtDueValue = 0;
			}
			if(isNaN(paymentValue)){
				paymentValue = 0;
			}
			if(isNaN(forexLossValue)){
				forexLossValue = 0;
			}
			if(isNaN(forexGainValue)){
				forexGainValue = 0;
			}
			
			origAmt = accAdd(origAmt,Number(billAmountValue));
			amtDue = accAdd(amtDue,Number(amtDueValue));
			payment = accAdd(payment,Number(paymentValue));
			forexLoss = accAdd(forexLoss,Number(forexLossValue));
			forexGain = accAdd(forexGain,Number(forexGainValue));
		}
		document.getElementById("sumOrigAmt").innerHTML = formatNum(origAmt,2);
		document.getElementById("sumAmtDue").innerHTML = formatNum(amtDue,2);
		document.getElementById("sumPayment").innerHTML = formatNum(payment,2);
		
		var amtReceived = document.getElementById("amtReceived").value;
		if(isNaN(amtReceived)){
			amtReceived = 0;
		}
		var refundAmount = document.getElementById("refundAmount").value;
        if(isNaN(refundAmount)){
            refundAmount = 0;
        }
        amtReceived = subtr(amtReceived,refundAmount);
		var hiddenBalanceAmt = subtr(Number(amtReceived), subtr(accAdd(payment, forexLoss), forexGain));
		document.getElementById("hiddenBalanceAmt").value = hiddenBalanceAmt;
		document.getElementById("balanceAmt").innerHTML = formatNum(hiddenBalanceAmt,2);
	}
	
	function checkApplyDBState() {
		var numDbInfo = document.getElementById("numDbInfo").value;
		for(var i = 0; i < numDbInfo; i++) {
			if(document.getElementById("debitInfos["+i+"].APPLIED").value == "1") {
				document.getElementById("debitInfos["+i+"].AMT_PAID").readOnly = false;
				document.getElementById("debitInfos["+i+"].FOREX_LOSS").readOnly = false;
				document.getElementById("debitInfos["+i+"].FOREX_GAIN").readOnly = false;
			}
		}
	}
	
	/**
	 * open B_BAC_S03 page
	 */
	function openB_BAC_S03() {
		var url = "<%=request.getContextPath()%>/B_BAC/RP_B_BAC_S03SearchBL.do";
		var width = window.screen.width*80/100;
	    var height = window.screen.height*80/100;
	    var left = Number((screen.availWidth/2) - (width/2));
	    var top = Number((screen.availHeight/2) - (height/2));
	    var offsetFeatures = "width=" + width + ",height=" + height +
	    					 ",left=" + left + ",top=" + top +
	    					 "screenX=" + left + ",screenY=" + top;
		var strFeatures= "toolbar=no, status=no, menubar=no,location=no,scrollbars=yes, resizable=yes" + "," + offsetFeatures;	 	
		var newwindow = window.open(url, null, strFeatures);	
		if (window.focus) { newwindow.focus(); }
	}
	/**
	 * B_BAC_S03 page return call
	 */
	function setCustomerAndBillAccInfo(idCust,custName,idBillAcc){
		document.getElementById("payer").value = idCust;
		document.getElementById("payerName").value = custName;
		document.getElementById("idBillAccount").options.add(new Option(idBillAcc,idBillAcc));
		document.getElementById("idBillAccount").value = idBillAcc;
		reloadBillAccountNo(null);
	}
	//only Decimal Number
	function onlyDecNumbers(evt) {
		var e = evt;
		if(window.event){ // IE
			var charCode = e.keyCode;
		} else if (e.which) { // Safari 4, Firefox 3.0.4
			var charCode = e.which;
		}
		if (charCode > 31 && (charCode < 48 || charCode > 57)){
			if(charCode == 46){// available for . 
				return true;
			}else{
				return false;
			}
		}else{
			return true;
		}
	}
	//Addition
	function accAdd(arg1,arg2){ 
		var r1,r2,m; 
		try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0};
		try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0};
		m=Math.pow(10,Math.max(r1,r2));
		return (arg1*m+arg2*m)/m;
	}
	//sub
	function subtr(arg1,arg2){
	     var r1,r2,m,n;
	     try{r1=arg1.toString().split(".")[1].length;}catch(e){r1=0;}
	     try{r2=arg2.toString().split(".")[1].length;}catch(e){r2=0;}
	     m=Math.pow(10,Math.max(r1,r2));
	     //last modify by deeka
	     n=(r1>=r2)?r1:r2;
	     return ((arg1*m-arg2*m)/m).toFixed(n);
	}
	//format Number
	function formatNum(s, n){
		n = n > 0 && n <= 20 ? n : 2;   
		s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";   
	    var l = s.split(".")[0].split("").reverse(),   
	    r = s.split(".")[1];   
	    t = "";   
	    for(i = 0; i < l.length; i ++ ){   
	      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");   
	    }
	    var prePointData = t.split("").reverse().join("");
	    if(prePointData.length>1){
	    	if("-,"===prePointData.substring(0,2)){
	    		prePointData = "-"+prePointData.substring(2);
	    	}
	    }
	    return prePointData + "." + r;
    }
	function initPage(){
		var fromPageFlg = document.getElementById("fromPageFlg").value;
		if("BIL"==fromPageFlg){
			//reloadBillAccountNo(null);
		}
	}
	// open refund info window
    function openB_CSB_S04(url){
        var width = window.screen.width*80/100;
        var height = window.screen.height*40/100;
        var left = Number((screen.availWidth/2) - (width/2));
        var top = Number((screen.availHeight/2) - (height/2));
        var offsetFeatures = "width=" + width + ",height=" + height +
                             ",left=" + left + ",top=" + top +
                             "screenX=" + left + ",screenY=" + top;
        var strFeatures= "toolbar=no, status=no, menubar=no,location=no,scrollbars=yes, resizable=yes" + "," + offsetFeatures;      
        var newwindow = window.open(url, null, strFeatures);
        if (window.focus) { newwindow.focus(); }
    }
	</script>
</head>
<ts:body onload="calDebitInfo();checkApplyDBState();initPage()">
	<ts:form action="/B_CSBS02NE">
		<html:hidden property="pattern" name="b_csbForm"/>
		<html:hidden property="actionFlg" name="b_csbForm"/>
		<input type="hidden" id="fromPageFlg" value="${b_csbForm.fromPageFlg}"/>
		<t:defineCodeList id="LIST_PAYMENT_METHOD" />
		<t:defineCodeList id="LIST_CURRENCY" />
		<t:defineCodeList id="LIST_PAYMENT_STATUS" />
		<t:defineCodeList id="LIST_GST_CSB" />
		<table class="subHeader" cellpadding="0" cellspacing="0">
		  <tr>
		    <td class="Title"><bean:message key="screen.b_csb.title"/></td> 
		  </tr>
		</table> 
		<table class = "generalInfo" cellpadding="0" cellspacing="0">
			<tr>
				<td class="header" width="50%">					
					<bean:message key="screen.b_csb.customerpayment"/>												
				</td>
			</tr>
		</table>
		<table class = "generalInfo" cellpadding="0" cellspacing="0">
			<c:if test="${b_csbForm.pattern eq 'CST'}">
				<tr>
					<td class="widthBetween tdBorderTopStyle tdBorderLeftStyle"></td>
					<td class="colLeft1 tdBorderTopStyle">
						<bean:message key="screen.b_csb.paymentmethod"/><span class="mandatory" ><bean:message key="screen.b_csb.star"/></span>
					</td>
					<td class="tdBorderTopStyle"><bean:message key="screen.b_csb.colon"/></td>
					<td class="colLeft tdBorderTopStyle tdBorderRightStyle">
						<c:choose>
							<c:when test="${not empty b_csbForm.actionFlg && b_csbForm.actionFlg == 'edit'}">
								<t:writeCodeValue codeList="LIST_PAYMENT_METHOD" key="${b_csbForm.pmtMethod}"/>
								<html:hidden property="pmtMethod" name="b_csbForm"/>
							</c:when>
							<c:otherwise>
								<html:select name="b_csbForm" property="pmtMethod" onchange="reloadPayment(this);">
									<html:option value="" ><bean:message key="screen.b_csb.blankitem"/></html:option>
									<html:options collection="LIST_PAYMENT_METHOD" property="id" labelProperty="name"/>
								</html:select>
							</c:otherwise>
						</c:choose>
					</td>
					<td class="widthBetween"></td>			
					<td style="font-weight:bold;">
						<bean:message key="screen.b_csb.paymentstatus"/>
					</td>
					<td><bean:message key="screen.b_csb.colon"/></td>
					<td>
						<t:writeCodeValue codeList="LIST_PAYMENT_STATUS" key="${b_csbForm.paymentStatus}"/>
						<html:hidden property="paymentStatus" name="b_csbForm"/>
					</td>
				</tr>
				<tr>
					<td class="widthBetween tdBorderLeftStyle"></td>
					<td class="colLeft1" width="20%">
						<bean:message key="screen.b_csb.payee"/><span class="mandatory" ><bean:message key="screen.b_csb.star"/></span>
					</td>
					<td><bean:message key="screen.b_csb.colon"/></td>
					<td class="colLeft tdBorderRightStyle">
						<c:choose>
							<c:when test="${not empty b_csbForm.actionFlg && b_csbForm.actionFlg == 'edit'}">
								<logic:iterate id="p" name="b_csbForm" property="cbPayer">
									<c:if test="${b_csbForm.payer == p.value}">
										${p.label}
									</c:if>
								</logic:iterate>
								<html:hidden property="payer" name="b_csbForm"/>
							</c:when>
							<c:otherwise>
								<html:select name="b_csbForm" property="payer" style="width:300px;" onchange="reloadPayer(this);">
									<option value=""> <bean:message key="screen.b_csb.blankitem"/> </option>
									<logic:present property="cbPayer" name="b_csbForm" >
										<html:optionsCollection name="b_csbForm" property="cbPayer"/>
									</logic:present>
								</html:select>
							</c:otherwise>
						</c:choose>
					</td>
					<td class="widthBetween"></td>
					<td style="font-weight:bold;">
						<bean:message key="screen.b_csb2.receiptno"/>
					</td>
					<td><bean:message key="screen.b_csb.colon"/></td>
					<td>
						<html:text property="receiptNo" name="b_csbForm" readonly="true" style="border:0; color: black; background:'#F8F8F8';"/> 
					</td>
				</tr>
				<tr>
					<td class="widthBetween tdBorderLeftStyle"></td>
					<td class="colLeft1">
						<bean:message key="screen.b_csb2.otherpayer"/>
					</td>
					<td><bean:message key="screen.b_csb.colon"/></td>
					<td class="colLeft tdBorderRightStyle">
						<c:choose>
							<c:when test="${not empty b_csbForm.actionFlg && b_csbForm.actionFlg == 'edit'}">
								${b_csbForm.other}
								<html:hidden property="other" name="b_csbForm"/>
							</c:when>
							<c:otherwise>
								<c:if test="${b_csbForm.payer == '-1'}">
									<html:text name="b_csbForm" property="other"  maxlength="100" style="width:300px;" styleClass="RemarkTextBox2" />
								</c:if>
								<c:if test="${b_csbForm.payer != '-1'}">
									<html:text name="b_csbForm" property="other" maxlength="100" readonly="true" style="background:#EBEADB;width:300px;"/>
								</c:if>
							</c:otherwise>
						</c:choose>
					</td>
					<td class="widthBetween"></td>
					<td style="font-weight:bold;">
						<bean:message key="screen.b_csb.balance"/>
					</td>
					<td><bean:message key="screen.b_csb.colon"/></td>
					<td>
						<span id="balanceAmt"></span><input type="hidden" id="hiddenBalanceAmt"/>
					</td>
				</tr>
				<tr>
					<td class="widthBetween tdBorderLeftStyle tdBorderBottomStyle"></td>
					<td class="colLeft1 tdBorderBottomStyle">
						<bean:message key="screen.b_csb.currency"/><span class="mandatory" ><bean:message key="screen.b_csb.star"/></span>
					</td>
					<td class="tdBorderBottomStyle"><bean:message key="screen.b_csb.colon"/></td>
					<td class="colLeft tdBorderBottomStyle tdBorderRightStyle">
						<c:choose>
							<c:when test="${not empty b_csbForm.actionFlg && b_csbForm.actionFlg == 'edit'}">
								<t:writeCodeValue codeList="LIST_CURRENCY" key="${b_csbForm.curCode}"/>
								<html:hidden property="curCode" name="b_csbForm"/>
							</c:when>
							<c:otherwise>
								<html:select name="b_csbForm" property="curCode" onchange="reloadCurrency(this);" style="width:300px;">
									<html:option value="" ><bean:message key="screen.b_csb.blankitem"/></html:option>
									<html:options collection="LIST_CURRENCY" property="id" labelProperty="name"/>
								</html:select>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</c:if>
			<c:if test="${b_csbForm.pattern eq 'BAC'}">
				<tr>
					<td class="widthBetween tdBorderTopStyle tdBorderLeftStyle"></td>
					<td class="colLeft1 tdBorderTopStyle">
						<bean:message key="screen.b_csb.payee"/><span class="mandatory" ><bean:message key="screen.b_csb.star"/></span>
					</td>
					<td class="tdBorderTopStyle"><bean:message key="screen.b_csb.colon"/></td>
					<td class="colLeft tdBorderTopStyle tdBorderRightStyle">
						<c:choose>
							<c:when test="${not empty b_csbForm.actionFlg && b_csbForm.actionFlg == 'edit'}">
								<bean:write property="payerName" name="b_csbForm"/>
								<html:hidden property="payerName" name="b_csbForm"/>
							</c:when>
							<c:otherwise>
								<button id="btnGetCustomerInfo" style="width:25px;" onclick="openB_BAC_S03();"><img src="<%=request.getContextPath()%>/image/search.png"></button>
								<bean:write property="payerName" name="b_csbForm"/>
								<html:hidden property="payerName" name="b_csbForm"/>
							</c:otherwise>
						</c:choose>
					</td>
					<td class="widthBetween"></td>			
					<td style="font-weight:bold;">
						<bean:message key="screen.b_csb2.customerId"/>
					</td>
					<td><bean:message key="screen.b_csb.colon"/></td>
					<td>
						<bean:write property="payer" name="b_csbForm"/>
						<html:hidden property="payer" name="b_csbForm"/>
					</td>
				</tr>
				<tr>
					<td class="widthBetween tdBorderLeftStyle"></td>
					<td class="colLeft1" width="20%">
						<bean:message key="screen.b_csb2.accountno"/><span class="mandatory" ><bean:message key="screen.b_csb.star"/></span>
					</td>
					<td><bean:message key="screen.b_csb.colon"/></td>
					<td class="colLeft tdBorderRightStyle">
						<c:choose>
							<c:when test="${not empty b_csbForm.actionFlg && b_csbForm.actionFlg == 'edit'}">
								${b_csbForm.idBillAccount}
								<html:hidden property="idBillAccount" name="b_csbForm"/>
							</c:when>
							<c:otherwise>
								<html:select name="b_csbForm" property="idBillAccount" style="width:300px;" onchange="reloadBillAccountNo(this);">
									<option value=""> <bean:message key="screen.b_csb.blankitem"/> </option>
									<logic:present property="cboBillAccountNo" name="b_csbForm" >
										<html:optionsCollection name="b_csbForm" property="cboBillAccountNo"/>
									</logic:present>
								</html:select>
							</c:otherwise>
						</c:choose>
					</td>
					<td class="widthBetween"></td>			
					<td style="font-weight:bold;">
						<bean:message key="screen.b_csb.paymentstatus"/>
					</td>
					<td><bean:message key="screen.b_csb.colon"/></td>
					<td>
						<html:hidden property="paymentStatus" name="b_csbForm"/>
						<t:writeCodeValue codeList="LIST_PAYMENT_STATUS" key="${b_csbForm.paymentStatus}"/>
					</td>
				</tr>
				<tr>
					<td class="widthBetween tdBorderLeftStyle"></td>
					<td class="colLeft1">
						<bean:message key="screen.b_csb.paymentmethod"/><span class="mandatory" ><bean:message key="screen.b_csb.star"/></span>
					</td>
					<td><bean:message key="screen.b_csb.colon"/></td>
					<td class="colLeft tdBorderRightStyle">
					    <html:select name="b_csbForm" property="pmtMethod" onchange="reloadPaymentByBAC(this);">
							<html:option value="" ><bean:message key="screen.b_csb.blankitem"/></html:option>
							<html:options collection="LIST_PAYMENT_METHOD" property="id" labelProperty="name"/>
						</html:select>
						<html:hidden property="pmtMethod" name="b_csbForm"/>
					</td>
					<td class="widthBetween"></td>
					<td style="font-weight:bold;">
						<bean:message key="screen.b_csb2.receiptno"/>
					</td>
					<td><bean:message key="screen.b_csb.colon"/></td>
					<td>
						<html:text property="receiptNo" name="b_csbForm" readonly="true" style="border:0; color: black; background:'#F8F8F8';"/> 
					</td>
				</tr>
				<tr>
					<td class="widthBetween tdBorderLeftStyle tdBorderBottomStyle"></td>
					<td class="colLeft1 tdBorderBottomStyle">
						<bean:message key="screen.b_csb.currency"/>
					</td>
					<td class="tdBorderBottomStyle"><bean:message key="screen.b_csb.colon"/></td>
					<td class="colLeft tdBorderBottomStyle tdBorderRightStyle">
						<t:writeCodeValue codeList="LIST_CURRENCY" key="${b_csbForm.curCode}"/>
						<html:hidden property="curCode" name="b_csbForm"/>
					</td>
					<td class="widthBetween"></td>
					<td style="font-weight:bold;">
						<bean:message key="screen.b_csb.balance"/>
					</td>
					<td><bean:message key="screen.b_csb.colon"/></td>
					<td>
						<span id="balanceAmt"></span><input type="hidden" id="hiddenBalanceAmt"/>
					</td>
				</tr>
			</c:if>
			<tr>
				<td class="widthBetween"></td>
				<td class="colLeft1">
					<bean:message key="screen.b_csb.transactiondate"/><span class="mandatory" ><bean:message key="screen.b_csb.star"/></span>
				</td>
				<td><bean:message key="screen.b_csb.colon"/></td>
				<td class="colLeft">
					<html:text property="transDate" name="b_csbForm" readonly="true" styleClass="DateTextBox" style="width=190px;"/>
	                <t:inputCalendar for="transDate" format="dd/MM/yyyy"/>
				</td>
			</tr>
			<tr>
				<td class="widthBetween"></td>
				<td class="colLeft1">
					<bean:message key="screen.b_csb.amountreceived"/><span class="mandatory" ><bean:message key="screen.b_csb.star"/></span>
				</td>
				<td><bean:message key="screen.b_csb.colon"/></td>
				<td class="colLeft">
						<c:choose>
							<c:when test="${not empty b_csbForm.actionFlg && b_csbForm.actionFlg == 'edit'}">
								<fmt:formatNumber value="${b_csbForm.amtReceived}" pattern="#,##0.00"/>
								<html:hidden property="amtReceived" name="b_csbForm"/>
							</c:when>
							<c:otherwise>
								<html:text name="b_csbForm" property="amtReceived" styleClass="CommonTextBox" maxlength="18" style="width=190px;" onkeypress="return onlyDecNumbers(event)" onchange="setAmtReceived(this);" />
								<input type="hidden" id="amtReceived" value="0"/>
							</c:otherwise>
						</c:choose>
				</td>
			</tr>
			<tr>
				<td class="widthBetween"></td>
				<td class="colLeft1"><bean:message key="screen.b_csb.bankaccount"/></td>
				<td><bean:message key="screen.b_csb.colon"/></td>
				<td class="colLeft" colspan="5">
					<html:select name="b_csbForm" property="bankAcc">
						<option value=""> <bean:message key="screen.b_csb.blankitem"/> </option>
						<logic:present name="b_csbForm" property="cbBankAcc">
							<html:optionsCollection name="b_csbForm" property="cbBankAcc"/>
						</logic:present>
					</html:select>
				</td>
			</tr>
			<tr>
				<td class="widthBetween"></td>
				<td class="colLeft1">
					<bean:message key="screen.b_csb.orno"/>
				</td>
				<td><bean:message key="screen.b_csb.colon"/></td>
				<td class="colLeft">
					<html:text name="b_csbForm" property="receiptRef"  maxlength="20" styleClass="CommonTextBox" style="width=190px;"  />
				</td>
			</tr>
			<tr>
				<td class="widthBetween"></td>
				<td class="colLeft1">
					<bean:message key="screen.b_csb2.paidprevious"/>
				</td>
				<td><bean:message key="screen.b_csb.colon"/></td>
				<td class="colLeft">
					<html:select name="b_csbForm" property="paidPreInvoiceText">
						<html:options collection="LIST_GST_CSB" property="id" labelProperty="name"/>
					</html:select>
				</td>
			</tr>
			<tr>
				<td class="widthBetween"></td>
				<td class="colLeft1">
					<bean:message key="screen.b_csb2.overpaid"/>
				</td>
				<td><bean:message key="screen.b_csb.colon"/></td>
				<td class="colLeft">
					<html:select name="b_csbForm" property="overPaidText">
						<html:options collection="LIST_GST_CSB" property="id" labelProperty="name"/>
					</html:select>
				</td>
			</tr>
			<tr>
				<td class="widthBetween"></td>
				<td class="colLeft1">
					<bean:message key="screen.b_csb.remark"/>
				</td>
				<td><bean:message key="screen.b_csb.colon"/></td>
				<td class="colLeft">
					<html:text name="b_csbForm" property="remark"  maxlength="300" style="width=300px;" styleClass="RemarkTextBox2"  />
				</td>
				
			</tr>
			<tr>
				<td class="widthBetween"></td>
				<td class="colLeft1">
					<span id="labPaymentRef1">
						<bean:write name="b_csbForm" property="labPaymentRef1"/>
					</span>
					<html:hidden property="labPaymentRef1" name="b_csbForm"/>
				</td>
				<td><bean:message key="screen.b_csb.colon"/></td>
				<td colspan="5" class="colLeft">
					<html:text name="b_csbForm" property="paymentRef1"  maxlength="20" styleClass="CommonTextBox" style="width=190px;"  />
					<span id="labPaymentRefDetail1" style="color:#3A3AFD;margin-left:5px;">
						<logic:notEmpty name="b_csbForm" property="paymentRef1De">
							<bean:write name="b_csbForm" property="paymentRef1De"/>
						</logic:notEmpty>
					</span>
					<select id="paymentRefDetails1" style="display:none;">
						<c:forEach var="paymentRefDetail" items="${b_csbForm.paymentRefDetails1}" >
							<option value="${paymentRefDetail.PAYMENT_METHOD}">${paymentRefDetail.REF_DETAIL}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td class="widthBetween"></td>
				<td class="colLeft1">
					<span id="labPaymentRef2">
						<bean:write name="b_csbForm" property="labPaymentRef2"/>
					</span>
					<html:hidden property="labPaymentRef2" name="b_csbForm"/>
				</td>
				<td><bean:message key="screen.b_csb.colon"/></td>
				<td colspan="5" class="colLeft">
					<html:text name="b_csbForm" property="paymentRef2"  maxlength="20" styleClass="CommonTextBox" style="width=190px;"  />
					<span id="labPaymentRefDetail2" style="color:#3A3AFD;margin-left:5px;">
						<logic:notEmpty name="b_csbForm" property="paymentRef2De">
							<bean:write name="b_csbForm" property="paymentRef2De"/>
						</logic:notEmpty>
					</span>
					<select id="paymentRefDetails2" style="display:none;">
						<c:forEach var="paymentRefDetail" items="${b_csbForm.paymentRefDetails2}" >
							<option value="${paymentRefDetail.PAYMENT_METHOD}">${paymentRefDetail.REF_DETAIL}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td class="widthBetween"></td>
				<td class="colLeft1">
					<bean:message key="screen.b_csb.bankcharges"/>
				</td>
				<td><bean:message key="screen.b_csb.colon"/></td>
				<td class="colLeft">
					<html:text name="b_csbForm" property="bankCharge" styleClass="CommonTextBox" onkeypress="return onlyDecNumbers(event)" style="width=190px;" maxlength="18" />
				</td>
			</tr>
		</table>
		
		<table class = "generalInfo2" cellpadding="0" cellspacing="0">
			<tr>
				<td class="header">					
					<bean:message key="screen.b_csb2.debitinfomation"/>												
				</td>
				<td class="header" style="text-align: right;">
				    <logic:notEmpty name="b_csbForm" property="refundInfo">
                        <bean:define id="refundInfo" name="b_csbForm" property="refundInfo"></bean:define>
                        <logic:greaterThan value="0" name="refundInfo" property="TOTAL_NUM">
                            <bean:message key="screen.b_csb_s02.refundReceipt"/>
                            <bean:message key="screen.b_csb.colon"/>
                            <a href="#" onclick="openB_CSB_S04('${pageContext.request.contextPath}/B_CSB/B_CSB_S04SCRBL.do?receiptNo=${b_csbForm.receiptNo}')">
                            <fmt:formatNumber value="${refundInfo.REFUND_AMOUNT}" pattern="#,##0.00"/>
                            </a>
                        </logic:greaterThan>
                        <input type="hidden" value="${refundInfo.REFUND_AMOUNT}" id="refundAmount"/>
                    </logic:notEmpty>
                    <logic:empty name="b_csbForm" property="refundInfo">
                        <input type="hidden" value="0" id="refundAmount"/>
                    </logic:empty>
				</td>
			</tr>
		</table>
		<!-- Debit Information -->
		<table class="creditInfo" cellpadding="0" cellspacing="0" id="create_infor" border="1">
			<tr>
				<td class="header"><bean:message key="screen.b_csb.appliedtocol"/></td>
				<td class="header"><bean:message key="screen.b_csb.datecol"/></td>
				<td class="header"><bean:message key="screen.b_csb.debitreference"/></td>
				<td class="header"><bean:message key="screen.b_csb.currencycol"/></td>
				<td class="header"><bean:message key="screen.b_csb.origamtcol"/></td>
				<td class="header"><bean:message key="screen.b_csb.outstandingAmt"/></td>
				<td class="header"><bean:message key="screen.b_csb.paymentcol"/></td>
				<td class="header"><bean:message key="screen.b_csb.forexlosscol"/></td>
				<td class="header"><bean:message key="screen.b_csb.forexgaincol"/></td>
			</tr>	
			<bean:define id="infoCount" value="0"/>
			<nested:nest>
			<nested:present property="debitInfos">
			   <nested:iterate id="dbInfo" property="debitInfos" indexId="index">
				<tr>
					<td class="defaultNo" align="center">
						<c:if test="${dbInfo.APPLIED==1}">
							<input type="checkbox" checked="checked" onclick="editDbInfo(this, '${infoCount}');"/>
						</c:if>
						<c:if test="${dbInfo.APPLIED!=1}">
							<input type="checkbox" onclick="editDbInfo(this, '${infoCount}');"/>
						</c:if>
						<nested:hidden property="APPLIED"/>
						<nested:hidden property="BILL_TYPE"/>
					</td>
					<td class="defaultText" align="center">
						<nested:write property="DATE_REQ"/>
						<nested:hidden property="DATE_REQ"/>
					</td>
					<td class="defaultText" align="center">
						<nested:write property="ID_REF"/>
						<nested:hidden property="ID_REF"/>
					</td>
					<td class="defaultText" align="center">
						<nested:write property="BILL_CURRENCY"/>
						<nested:hidden property="BILL_CURRENCY"/>
					</td>
					<td class="defaultText" align="right">
						<c:if test="${empty dbInfo.BILL_AMOUNT}">&nbsp;</c:if>
						<fmt:formatNumber value="${dbInfo.BILL_AMOUNT}" pattern="#,##0.00"/>
						<nested:hidden property="BILL_AMOUNT"/>
					</td>
					<td class="defaultText" align="right">
						<c:if test="${empty dbInfo.AMT_DUE}">&nbsp;</c:if>
						<fmt:formatNumber value="${dbInfo.AMT_DUE}" pattern="#,##0.00"/>
						<nested:hidden property="AMT_DUE"/>
					</td>
					<td class="defaultText" align="right">
						<c:if test="${empty dbInfo.AMT_PAID}">&nbsp;</c:if>
						<c:choose>
							<c:when test="${empty dbInfo.AMT_PAID_OLD}">
								<nested:hidden property="AMT_PAID_OLD" value="${dbInfo.AMT_PAID}"/>
							</c:when>
							<c:otherwise>
								<nested:hidden property="AMT_PAID_OLD"/>
							</c:otherwise>
						</c:choose>
						<c:choose>
                            <c:when test="${dbInfo.APPLIED!=1}">
                                <nested:text property="AMT_PAID" value="0" readonly="true" style="width:100px;" onkeypress="return onlyDecNumbers(event)" onchange="calDebitInfo();"/>
                            </c:when>
                                <c:otherwise>
                            <nested:text property="AMT_PAID" readonly="true" style="width:100px;" onkeypress="return onlyDecNumbers(event)" onchange="calDebitInfo();"/>
                            </c:otherwise>  
                        </c:choose>
                    </td>
                    <td class="defaultText" align="right">
                        <c:if test="${empty dbInfo.FOREX_LOSS}">&nbsp;</c:if>
                        <c:choose>
                            <c:when test="${dbInfo.APPLIED!=1}">
                                <nested:text property="FOREX_LOSS" value="0" readonly="true" style="width:100px;" onkeypress="return onlyDecNumbers(event)" onchange="calDebitInfo();"/>
                            </c:when>
                                <c:otherwise>
                                <nested:text property="FOREX_LOSS" readonly="true" style="width:100px;" onkeypress="return onlyDecNumbers(event)" onchange="calDebitInfo();"/>
                            </c:otherwise>  
                        </c:choose>
                        
                    </td>
                    <td class="defaultText" align="right">
                        <c:if test="${empty dbInfo.FOREX_GAIN}">&nbsp;</c:if>
                            <c:choose>
                            <c:when test="${dbInfo.APPLIED!=1}">
                                <nested:text property="FOREX_GAIN" value="0" readonly="true" style="width:100px;" onkeypress="return onlyDecNumbers(event)" onchange="calDebitInfo();"/>
                            </c:when>
                                <c:otherwise>
                                    <nested:text property="FOREX_GAIN" readonly="true" style="width:100px;" onkeypress="return onlyDecNumbers(event)" onchange="calDebitInfo();"/>
                            </c:otherwise>  
                        </c:choose>
                        

                    </td>
                </tr>
			   	<bean:define id="infoCount" value="${infoCount+1}"/>
			  </nested:iterate>
			</nested:present>
			</nested:nest>
			<tr height="10">
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="3" align="right">Totals</td>
				<td>&nbsp;</td>
				<td align="right">
					<span id="sumOrigAmt">&nbsp;</span>
				</td>
				<td align="right">
					<span id="sumAmtDue">&nbsp;</span>
				</td>
				<td align="right">
					<span id="sumPayment">&nbsp;</span>
				</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</table>
		<input type="hidden" id="numDbInfo" value="${infoCount}"/>
		<!-- Debit Information -->
		<table class="buttonTbl" >
			<tr>
				<td>
					<input type="button" class="button" value="<bean:message key="screen.b_csb.savebutton"/>" onclick="confirm_save();"/>
					<input type="button" class="button" value="<bean:message key="screen.b_csb.exitbutton"/>" onclick="comfirm_exit();"/>
				</td>
			</tr>
		</table>
	</ts:form>
	<div class="error">
		<html:messages id="message">
			<bean:write name="message"/><br/>
		</html:messages>
	</div>
	<div class="message">
		<ts:messages id="messages" message="true">
			<bean:write name="messages"/><br/>
		</ts:messages>
   	</div>
</ts:body>
</html>