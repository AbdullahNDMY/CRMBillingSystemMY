<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="/WEB-INF/bs" prefix="bs" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
	<head>
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
		<link href="${pageContext.request.contextPath}/B_BIL/css/b_bil.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/B_BIL/js/numberToWord.js"></script>
   		<title><bean:message key="screen.b_bil_s01.title"/></title>
		<script type="text/javascript">
		var isDelBtn = false;
		// check which button was pressed
		document.onclick = function(whichOne){ 
			whichOne = whichOne ? whichOne : window.event;
			thisButton = whichOne.target ? whichOne.target : whichOne.srcElement;
			if (( thisButton.name ) && ( thisButton.name == 'forward_delete' )) 
				isDelBtn = true;
			else
				isDelBtn = false;
		}
		function checkBeforeSubmit(){
			if(isDelBtn){
				var MsgBox = new messageBox();
				if (MsgBox.POPCFM("Do you want to delete?") == MsgBox.YES_CONFIRM)
				//if(confirm('Do you want to delete?'))
					return true;
				else
					return false;
			}else{
				return true;
			}
		}
		function onlyDecNumbers(evt) {
			var e = evt
			if(window.event){ // IE
				var charCode = e.keyCode;
			} else if (e.which) { // Safari 4, Firefox 3.0.4
				var charCode = e.which
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
		function onlyIntNumbers(evt) {
			var e = evt
			if(window.event){ // IE
				var charCode = e.keyCode;
			} else if (e.which) { // Safari 4, Firefox 3.0.4
				var charCode = e.which
			}
			if (charCode > 31 && (charCode < 48 || charCode > 57))
			return false;			
			return true;
		}

		$(document).ready(function() {
			
			formatNumberInfo();
			
			$('#cboCustomerName').change(function(){
				changeToNumber();
				$('input[name="inputHeaderInfo.changeTypeFlag"]').val("changeCustName");
				$('#editForm').attr('action', 'RP_B_BIL_S03_02_03BL.do');
				$('#editForm').submit();
				$('#editForm').attr('action', 'SC_B_BIL_S03_02DSP.do');
			});
			$('#cboAdrType').change(function(){
				changeToNumber();
				$('#editForm').attr('action', 'RP_B_BIL_S03_02_03BL.do');
				$('#editForm').submit();
				$('#editForm').attr('action', 'SC_B_BIL_S03_02DSP.do');
			});
			$('#cboAttn').change(function(){
				changeToNumber();
				$('#editForm').attr('action', 'RP_B_BIL_S03_02_03BL.do');
				$('#editForm').submit();
				$('#editForm').attr('action', 'SC_B_BIL_S03_02DSP.do');
			});
			$('#cboCurrency').change(function(){
				changeToNumber();
				$('#editForm').attr('action', 'RP_B_BIL_S03_02_03BL.do');
				$('#editForm').submit();
				$('#editForm').attr('action', 'SC_B_BIL_S03_02DSP.do');
			});
			$('#addLink').click(function(){
				var sRowClone = $('#sRow').clone(true);
				$(sRowClone).removeClass('hidden');
				$('#endDetail').before(sRowClone);
			});
			
			$('input[name="itemDesc"]').each(function(){
				$(this).change(function(){
					var itemDesc = $(this).val();
					if(itemDesc == ""){
						new messageBox().POPALT('This field requires a Description'); 
						$(this).val(0);
						$(this).focus();
					};
				});			
			});
				
			$('input[name="itemQty"]').each(function(){
				$(this).change(function(){
					var reg=/^(0|([1-9]\d*))$/;
					var itemQty = $(this).val();
					itemQty = itemQty.replace(/,/g, "");
					if(!reg.test(itemQty)){
						new messageBox().POPALT('This field requires a Number'); 
						$(this).val(0);
						$(this).focus();
					}else{
						//changeToNumber();
						var trEle = $(this).parent().parent();
						var itemUprice = $(trEle).find('input[name=itemUprice]').val();
						itemUprice = itemUprice.replace(/,/g, "");
						$(trEle).find('input[name=itemAtm]').val(numberFormat(accMul(itemQty,itemUprice),2,","));
						var subTotal = 0;
						$('input[name="itemAtm"]').each(function(index){
							if(index != 0){
								subTotal = accAdd(subTotal,parseFloat($(this).val().replace(/,/g, "")));
							}		
						});
						$('#subTotal').html(numberFormat(parseFloat(subTotal),2,","));
						
						//Auto-calculate GST amount
						var gstValue = ${_b_bilForm.map.gstValue}; 
						var gstAmount = calcGst(gstValue, subTotal);
						if(isNaN(gstAmount)) {
							gstAmount = 0;
						}
						$('input[name="inputHeaderInfo.gstAmount"]').val(numberFormat(parseFloat(gstAmount),2,","));
						
						
						var grandTotal = parseFloat(accAdd(gstAmount,subTotal)) + '';
						$('#grandTotal').html(numberFormat(grandTotal,2,","));
						$('input[name="grandTotalWording"]').val(grandTotal);
						$('input[name="inputHeaderInfo.billAmount"]').val((!isNaN(parseFloat(accAdd(gstAmount , subTotal)))? parseFloat(accAdd(gstAmount , subTotal)):0));
						
						var billAmountToWord = nbrToEngWord($('#grandTotal').html(),",",".","");
						$('#billAmountToStr').html(billAmountToWord);
						
						$(this).val(numberFormat(itemQty,0,","));
						
						//$('#editForm').attr('action', 'RP_B_BIL_S03_02_03BL.do');
						//$('#editForm').submit();
						//$('#editForm').attr('action', 'SC_B_BIL_S03_02DSP.do');
					}
				});
			});
			$('input[name="itemUprice"]').each(function(){
				$(this).change(function(){
					var itemUprice = $(this).val();
					itemUprice = itemUprice.replace(/,/g, "");
					if (/^\.?$/.test($(this).val().replace(/,/g, "")) || !/^-?\d*\.?\d*$/.test($(this).val().replace(/,/g, ""))) {
						new messageBox().POPALT('This field requires a Number'); 
						$(this).val(0);
						$(this).focus();
					}else{
						//changeToNumber();
						var trEle = $(this).parent().parent();
						var itemQty = $(trEle).find('input[name=itemQty]').val().replace(/,/g, "");
						$(trEle).find('input[name=itemAtm]').val(numberFormat(accMul(itemQty,itemUprice),2,","));
						var subTotal = 0;
						$('input[name="itemAtm"]').each(function(index){
							if(index != 0){
								subTotal = accAdd(subTotal,parseFloat($(this).val().replace(/,/g, "")));
							}
						});
						$('#subTotal').html(numberFormat(parseFloat(subTotal),2,","));
						
						//Auto-calculate GST amount
						var gstValue = ${_b_bilForm.map.gstValue}; 
						var gstAmount = calcGst(gstValue, subTotal);
						if(isNaN(gstAmount)) {
							gstAmount = 0;
						}
						$('input[name="inputHeaderInfo.gstAmount"]').val(numberFormat(parseFloat(gstAmount),2,","));
						
						
						$('#grandTotal').html(numberFormat(parseFloat(accAdd(gstAmount , subTotal)),2,","));
						$('input[name="grandTotalWording"]').val(parseFloat(accAdd(gstAmount , subTotal)));
						$('input[name="inputHeaderInfo.billAmount"]').val((!isNaN(parseFloat(accAdd(gstAmount , subTotal)))? parseFloat(accAdd(gstAmount , subTotal)):0));
						
						var billAmountToWord = nbrToEngWord($('#grandTotal').html(),",",".","");
						$('#billAmountToStr').html(billAmountToWord);
						$(this).val(numberFormat(itemUprice,2,","));
						//$('#editForm').attr('action', 'RP_B_BIL_S03_02_03BL.do');
						//$('#editForm').submit();
						//$('#editForm').attr('action', 'SC_B_BIL_S03_02DSP.do');
					}
				});
			});
			$('input[name="itemAtm"]').each(function(){
				$(this).change(function(){
					if (/^\.?$/.test($(this).val().replace(/,/g, "")) || !/^-?\d*\.?\d*$/.test($(this).val().replace(/,/g, ""))) {
						new messageBox().POPALT('This field requires a Number'); 
						$(this).val(0);
						$(this).focus();
					}else{
						//changeToNumber();
						var subTotal = 0;
						$('input[name="itemAtm"]').each(function(index){
							if(index != 0){
								subTotal = accAdd(subTotal,parseFloat($(this).val().replace(/,/g, "")));	
							}
						});
						$('#subTotal').html(numberFormat(parseFloat(subTotal),2,","));
						
						//Auto-calculate GST amount
						var gstValue = ${_b_bilForm.map.gstValue}; 
						var gstAmount = calcGst(gstValue, subTotal);
						if(isNaN(gstAmount)) {
							gstAmount = 0;
						}
						$('input[name="inputHeaderInfo.gstAmount"]').val(numberFormat(parseFloat(gstAmount),2,","));
						
						$('#grandTotal').html(numberFormat(parseFloat(accAdd(gstAmount , subTotal)),2,","));
						$('input[name="grandTotalWording"]').val(parseFloat(accAdd(gstAmount , subTotal)));
						$('input[name="inputHeaderInfo.billAmount"]').val((!isNaN(parseFloat(accAdd(gstAmount , subTotal)))? parseFloat(accAdd(gstAmount , subTotal)):0));
						
						var billAmountToWord = nbrToEngWord($('#grandTotal').html(),",",".","");
						$('#billAmountToStr').html(billAmountToWord);
						$(this).val(numberFormat($(this).val().replace(/,/g, ""),2,","));
						//$('#editForm').attr('action', 'RP_B_BIL_S03_02_03BL.do');
						//$('#editForm').submit();
						//$('#editForm').attr('action', 'SC_B_BIL_S03_02DSP.do');
					}
				});
			});
			$('input[name="inputHeaderInfo.gstAmount"]').each(function(){
				$(this).change(function(){
					if (/^\.?$/.test($(this).val().replace(/,/g, "")) || !/^-?\d*\.?\d*$/.test($(this).val().replace(/,/g, ""))) {
						new messageBox().POPALT('This field requires a Number'); 
						$(this).val(0);
						$(this).focus();
					}else{
						//changeToNumber();
						var subTotal = 0;
						$('input[name="itemAtm"]').each(function(index){
							if(index != 0){
								subTotal = accAdd(subTotal,parseFloat($(this).val().replace(/,/g, "")));
							}
						});
						$('#subTotal').html(numberFormat(parseFloat(subTotal),2,","));
						
						//Auto-calculate GST amount
						var gstAmount = parseFloat($('input[name="inputHeaderInfo.gstAmount"]').val().replace(/,/g, ""));

						if(isNaN(gstAmount)) {
							gstAmount = 0;
						}
						$('#grandTotal').html(numberFormat(parseFloat(accAdd(gstAmount , subTotal)),2,","));
						$('input[name="grandTotalWording"]').val(parseFloat(accAdd(gstAmount , subTotal)));
						$('input[name="inputHeaderInfo.billAmount"]').val((!isNaN(parseFloat(accAdd(gstAmount , subTotal)))? parseFloat(accAdd(gstAmount , subTotal)):0));
						
						var billAmountToWord = nbrToEngWord($('#grandTotal').html(),",",".","");
						$('#billAmountToStr').html(billAmountToWord);
						$(this).val(numberFormat($(this).val().replace(/,/g, ""),2,","));
						//$('#editForm').attr('action', 'RP_B_BIL_S03_02_03BL.do');
						//$('#editForm').submit();
						//$('#editForm').attr('action', 'SC_B_BIL_S03_02DSP.do');
					}
				});
			});
			$('div[id="removeLink"]').each(function(){
				$(this).click(function(){
					//changeToNumber();
					$(this).parent().parent().remove();
					var subTotal = 0;
					$('input[name="itemAtm"]').each(function(index){
						if(index != 0){
							subTotal = accAdd(subTotal,parseFloat($(this).val().replace(/,/g, "")));
						}
					});
					$('#subTotal').html(numberFormat(parseFloat(subTotal),2,","));
					
					//Auto-calculate GST amount
					var gstValue = ${_b_bilForm.map.gstValue}; 
					var gstAmount = calcGst(gstValue, subTotal);						
					$('input[name="inputHeaderInfo.gstAmount"]').val(numberFormat(parseFloat(gstAmount),2,","));
					
					if(isNaN(gstAmount)) {
						gstAmount = 0;
					}
					$('#grandTotal').html(numberFormat(parseFloat(accAdd(gstAmount , subTotal)),2,","));
					$('input[name="grandTotalWording"]').val(parseFloat(accAdd(gstAmount , subTotal)));
					$('input[name="inputHeaderInfo.billAmount"]').val((!isNaN(parseFloat(accAdd(gstAmount , subTotal)))? parseFloat(accAdd(gstAmount , subTotal)):0));
					
					var billAmountToWord = nbrToEngWord($('#grandTotal').html(),",",".","");
					$('#billAmountToStr').html(billAmountToWord);
					//$('#editForm').attr('action', 'RP_B_BIL_S03_02_03BL.do');
					//$('#editForm').submit();
					//$('#editForm').attr('action', 'SC_B_BIL_S03_02DSP.do');
				});
			});
		});
		function validateForm(){
			return true;
		}
		//Multiplication
		function accMul(arg1,arg2){ 
			var m=0;
			var s1=arg1.toString();
			var s2=arg2.toString(); 
			try{m+=s1.split(".")[1].length}catch(e){};
			try{m+=s2.split(".")[1].length}catch(e){} ;
			return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m);
		}
		//Addition
		function accAdd(arg1,arg2){ 
			var r1,r2,m; 
			try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0};
			try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0};
			m=Math.pow(10,Math.max(r1,r2));
			return (arg1*m+arg2*m)/m;
		}
		//Auto Calculation of GST amount
		function calcGst(arg1,arg2){ 
			var gstCalcAmt;
			gstCalcAmt = accMul((arg1/100),arg2);
			return gstCalcAmt;
		} 
		function showMsg(){
            var message = $("#ERR107").val();
            message=message.replace('{0}', "Customer PO").replace('{1}', "Customer PO");
            var MsgBox = new messageBox();
            MsgBox.POPALT(message);
            return false;
      }
		function saveClick(){
			var cusPo=$("#custPoid").val();
			if(cusPo.indexOf("&")!=-1)
			{
				showMsg();
			}else{
				changeToNumber();
				submitForm("forward_save");
			}								
		}
		//Function use submit form
		function submitForm(event) {
			$("input[name='event']").remove();
			var event = '<input type="hidden" name="event" value="' + event + '"/>';
			$('form').append(event);
			$('form').submit();
		}
		function changeToNumber(){
			var tbody = document.getElementById("detailTableBody");
			var tbodyLen = tbody.rows.length;
			for(var i=2;i<tbodyLen-3;i++){
				var row = tbody.rows[i];
				$(row).find('input[name=itemQty]').val($(row).find('input[name=itemQty]').val().replace(/,/g, ""));
				$(row).find('input[name=itemUprice]').val($(row).find('input[name=itemUprice]').val().replace(/,/g, ""));
				$(row).find('input[name=itemAtm]').val($(row).find('input[name=itemAtm]').val().replace(/,/g, ""));
			}
			var gstValue = $('input[name="inputHeaderInfo.gstAmount"]');
			var subTotal = $('#subTotal');
			var grandTotal = $('#grandTotal');
			gstValue.val(gstValue.val().replace(/,/g, ""));
			grandTotal.html(grandTotal.html().replace(/,/g, ""));
			subTotal.html(subTotal.html().replace(/,/g, ""));
		}
		function formatNumberInfo(){
			var tbody = document.getElementById("detailTableBody");
			var tbodyLen = tbody.rows.length;
			for(var i=2;i<tbodyLen-3;i++){
				var row = tbody.rows[i];
				$(row).find('input[name=itemQty]').val(numberFormat($(row).find('input[name=itemQty]').val(),0,","));
				$(row).find('input[name=itemUprice]').val(numberFormat($(row).find('input[name=itemUprice]').val(),2,","));
				$(row).find('input[name=itemAtm]').val(numberFormat($(row).find('input[name=itemAtm]').val(),2,","));
			}
			var gstValue = $('input[name="inputHeaderInfo.gstAmount"]');
			var subTotal = $('#subTotal');
			var grandTotal = $('#grandTotal');
			gstValue.val(numberFormat(gstValue.val(),2,","));
			subTotal.html(numberFormat(subTotal.html(),2,","));
			grandTotal.html(numberFormat(grandTotal.html(),2,","));
		}
		function numberFormat(value, decimalValue, splitChar) {
			var power = 1;
			for ( var i = 0; i < decimalValue; i++) {
				power *= 10;
			}
			var newValue = parseFloat(Math.round(value * power)) / power;
			newValue = addCommas(newValue, decimalValue, ",");
			return newValue;
		}
		function addCommas(nStr, length, splitChar)
		{
			nStr += '';
			x = nStr.split('.');
			x1 = x[0] + "";
			//calculate decimal number
			x[1] = paddingDecimal(x[1], length);
			x2 = x[1].length > 1 ? '.' + x[1] : '';
			/*
			var rgx = /(\d+)(\d{3})/;
			while (rgx.test(x1)) {
				x1 = x1.replace(rgx, splitChar);
			}
			*/
			//format number
			var commaNo = parseInt((x1.length - 1)/ 3);
			var x1Temp = "";
			var count = 0;
			for ( var i = x1.length - 1; i > -1; i--) {
				if (commaNo > 0) {
					x1Temp = x1.charAt(i) + x1Temp;
					count++;
					if (count != 0 && count % 3 == 0) {
						x1Temp = splitChar + x1Temp;
						commaNo--;
					}
				} else {
					x1Temp = x1.charAt(i) + x1Temp;
				}
			}
			x1 = x1Temp;
			return (x1 + "" + x2);
		}
		/**
		 * 
		 * @param decimalNumber
		 * @param length
		 * @return
		 */
		function paddingDecimal(decimalNumber, length) {
			if (decimalNumber == undefined || length == 0) {
				decimalNumber = "";
			}
			decimalNumber = "" + decimalNumber;

			while(decimalNumber.length != length) {
				if (decimalNumber.length > length) {
					//remove end of number
					decimalNumber = decimalNumber.substring(0, decimalNumber.length - 1);
				} else {
					//add 0 after number
					decimalNumber += "0";
				}	
			}
			return decimalNumber;
		}
	</script>
	</head>
	<ts:body><!-- Edit -->
		<ts:form action="/SC_B_BIL_S03_02DSP" styleId="editForm" onsubmit="return validateForm();">
			<html:hidden property="inputHeaderInfo.changeTypeFlag" value=""/>
			<input type="hidden" name="ERR1SC107" id="ERR107" value="<bean:message key="errors.ERR1SC107" arg0="{0}" arg1="{1}"/>">
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tr><td>
			<h1 class="title">
				<c:if test="${empty _b_bilForm.map.inputHeaderInfo.billType}">
					<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
						<bean:message key="screen.b_bil.creditNote"/>
					</c:if>
					<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'IN'}">
						<bean:message key="screen.b_bil.invoiceNote"/>
					</c:if>
					<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'DN'}">
						<bean:message key="screen.b_bil.debitNote"/>
					</c:if>
					<html:hidden property="inputHeaderInfo.billType" value="${_b_bilForm.map.headerInfo.BILL_TYPE}"/>
				</c:if>
				<c:if test="${not empty _b_bilForm.map.inputHeaderInfo.billType}">
					<c:if test="${_b_bilForm.map.inputHeaderInfo.billType eq 'CN'}">
						<bean:message key="screen.b_bil.creditNote"/>
					</c:if>
					<c:if test="${_b_bilForm.map.inputHeaderInfo.billType eq 'IN'}">
						<bean:message key="screen.b_bil.invoiceNote"/>
					</c:if>
					<c:if test="${_b_bilForm.map.inputHeaderInfo.billType eq 'DN'}">
						<bean:message key="screen.b_bil.debitNote"/>
					</c:if>
					<html:hidden property="inputHeaderInfo.billType" value="${_b_bilForm.map.inputHeaderInfo.billType}"/>
				</c:if>
			</h1>
			</td></tr>
			<tr><td>
			<table class ="header1" cellpadding="0" cellspacing="0">
				<tr>
					<td class="col1Top" width="39%">
						<table>
							<tr>
								<td>
									<font size="4px" style="headerInfo">
										<b><bean:message key="screen.b_bil.customerDetailsHeader"/>&nbsp;</b>
									</font>			
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.customerName"/>
									<font class="asteriskBold"><bean:message key="screen.b_bil.asterisk"/></font>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									<html:select property="inputHeaderInfo.idCust" name="_b_bilForm" style="width:170px;"
										value="${not empty _b_bilForm.map.inputHeaderInfo.idCust ? 
													_b_bilForm.map.inputHeaderInfo.idCust : _b_bilForm.map.headerInfo.ID_CUST}" 
										styleId="cboCustomerName">
										<html:option value="" ><bean:message key="screen.b_bil.blankItem"/></html:option>
										<html:optionsCollection property="listCust" name="_b_bilForm" label="label" value="value"/>
									</html:select>
								</td>
							</tr>
							<tr>
								<td align="right">
									<html:select property="inputHeaderInfo.adrType" name="_b_bilForm" style="width:150px;"
										value="${not empty _b_bilForm.map.inputHeaderInfo.adrType ? 
													_b_bilForm.map.inputHeaderInfo.adrType : _b_bilForm.map.headerInfo.ADR_TYPE}" 
										styleId="cboAdrType">
										<html:option value="BA">Billing Address</html:option>
										<html:option value="CA">Correspondence Address</html:option>
										<html:option value="RA">Registered Address</html:option>
									</html:select>
									<font class="asteriskBold"><bean:message key="screen.b_bil.asterisk"/></font>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									${not empty _b_bilForm.map.inputHeaderInfo.address1 ? 
										_b_bilForm.map.inputHeaderInfo.address1 : _b_bilForm.map.headerInfo.ADDRESS1}
									<html:hidden property="inputHeaderInfo.address1" value="${not empty _b_bilForm.map.inputHeaderInfo.address1 ? 
										_b_bilForm.map.inputHeaderInfo.address1 : _b_bilForm.map.headerInfo.ADDRESS1}"/>
								</td>
							</tr>
							<tr>
								<td align="right">
									&nbsp;
								</td>
								<td>
									<bean:message key="screen.b_bil.addBlank"/>
									${not empty _b_bilForm.map.inputHeaderInfo.address2 ? 
										_b_bilForm.map.inputHeaderInfo.address2 : _b_bilForm.map.headerInfo.ADDRESS2}
									<html:hidden property="inputHeaderInfo.address2" value="${not empty _b_bilForm.map.inputHeaderInfo.address2 ? 
										_b_bilForm.map.inputHeaderInfo.address2 : _b_bilForm.map.headerInfo.ADDRESS2}"/>
								</td>
							</tr>
							<tr>
								<td align="right">
									&nbsp;
								</td>
								<td>
									<bean:message key="screen.b_bil.addBlank"/>
									${not empty _b_bilForm.map.inputHeaderInfo.address3 ? 
										_b_bilForm.map.inputHeaderInfo.address3 : _b_bilForm.map.headerInfo.ADDRESS3}
									<html:hidden property="inputHeaderInfo.address3" value="${not empty _b_bilForm.map.inputHeaderInfo.address3 ? 
										_b_bilForm.map.inputHeaderInfo.address3 : _b_bilForm.map.headerInfo.ADDRESS3}"/>
								</td>
							</tr>
							<tr>
								<td align="right">
									&nbsp;
								</td>
								<td>
									<bean:message key="screen.b_bil.addBlank"/>
									${not empty _b_bilForm.map.inputHeaderInfo.address4 ? 
										_b_bilForm.map.inputHeaderInfo.address4 : _b_bilForm.map.headerInfo.ADDRESS4}
									<html:hidden property="inputHeaderInfo.address4" value="${not empty _b_bilForm.map.inputHeaderInfo.address4 ? 
										_b_bilForm.map.inputHeaderInfo.address4 : _b_bilForm.map.headerInfo.ADDRESS4}"/>
								</td>
							</tr>
							<tr>
								<td align="right">	
									<bean:message key="screen.b_bil.tel"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									${not empty _b_bilForm.map.headerInfo.TEL_NO ? _b_bilForm.map.headerInfo.TEL_NO : _b_bilForm.map.inputHeaderInfo.telNo}
									<html:hidden property="inputHeaderInfo.telNo" 
										value="${not empty _b_bilForm.map.headerInfo.TEL_NO ? _b_bilForm.map.headerInfo.TEL_NO : _b_bilForm.map.inputHeaderInfo.telNo}"/>
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.fax"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									${not empty _b_bilForm.map.headerInfo.FAX_NO ? _b_bilForm.map.headerInfo.FAX_NO : _b_bilForm.map.inputHeaderInfo.faxNo}
									<html:hidden property="inputHeaderInfo.faxNo" 
										value="${not empty _b_bilForm.map.headerInfo.FAX_NO ? _b_bilForm.map.headerInfo.FAX_NO : _b_bilForm.map.inputHeaderInfo.faxNo}"/>
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.attn"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									<html:select property="inputHeaderInfo.contactType" name="_b_bilForm" style="width:170px;"
										value="${not empty _b_bilForm.map.headerInfo.CONTACT_TYPE ? 
													_b_bilForm.map.headerInfo.CONTACT_TYPE : _b_bilForm.map.inputHeaderInfo.contactType}" 
										styleId="cboAttn">
										<html:option value="" ><bean:message key="screen.b_bil.blankItem"/></html:option>
										<html:optionsCollection property="listContact" name="_b_bilForm" label="label" value="value"/>
									</html:select>
									<html:hidden property="inputHeaderInfo.contactName" value="${_b_bilForm.map.inputHeaderInfo.contactName}"/>
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.email"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									${not empty _b_bilForm.map.headerInfo.CONTACT_EMAIL 
											? _b_bilForm.map.headerInfo.CONTACT_EMAIL : _b_bilForm.map.inputHeaderInfo.contactEmail}
									<html:hidden property="inputHeaderInfo.contactEmail" 
										value="${not empty _b_bilForm.map.headerInfo.CONTACT_EMAIL 
											? _b_bilForm.map.headerInfo.CONTACT_EMAIL : _b_bilForm.map.inputHeaderInfo.contactEmail}"/>
								</td>
							</tr>
						</table>
					</td>
					<td class="col2Top" width="1%" style="background:white">
						&nbsp;
					</td>
					<td class="col3Top" width="44%">
						<table>
							<tr>
								<td colspan="2"></td>			
							</tr>
							<tr>
								<td align="right" nowrap="nowrap">
									<c:if test="${empty _b_bilForm.map.inputHeaderInfo.billType}">
										<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
											<bean:message key="screen.b_bil.creditNote"/>
										</c:if>
										<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'IN'}">
											<bean:message key="screen.b_bil.invoiceNote"/>
										</c:if>
										<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'DN'}">
											<bean:message key="screen.b_bil.debitNote"/>
										</c:if>
										<html:hidden property="inputHeaderInfo.billType" value="${_b_bilForm.map.headerInfo.BILL_TYPE}"/>
									</c:if>
									<c:if test="${not empty _b_bilForm.map.inputHeaderInfo.billType}">
										<c:if test="${_b_bilForm.map.inputHeaderInfo.billType eq 'CN'}">
											<bean:message key="screen.b_bil.creditNote"/>
										</c:if>
										<c:if test="${_b_bilForm.map.inputHeaderInfo.billType eq 'IN'}">
											<bean:message key="screen.b_bil.invoiceNote"/>
										</c:if>
										<c:if test="${_b_bilForm.map.inputHeaderInfo.billType eq 'DN'}">
											<bean:message key="screen.b_bil.debitNote"/>
										</c:if>
										<html:hidden property="inputHeaderInfo.billType" value="${_b_bilForm.map.inputHeaderInfo.billType}"/>
									</c:if>
									<bean:message key="screen.b_bil.billingNo"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									${not empty _b_bilForm.map.headerInfo.ID_REF ? 
													_b_bilForm.map.headerInfo.ID_REF : _b_bilForm.map.inputHeaderInfo.idRef}
								</td>
							</tr>
							<tr>
								<td align="right">
									<c:if test="${empty _b_bilForm.map.inputHeaderInfo.billType}">
										<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
											<bean:message key="screen.b_bil.creditNote"/>
										</c:if>
										<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'IN'}">
											<bean:message key="screen.b_bil.invoiceNote"/>
										</c:if>
										<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'DN'}">
											<bean:message key="screen.b_bil.debitNote"/>
										</c:if>
										<html:hidden property="inputHeaderInfo.billType" value="${_b_bilForm.map.headerInfo.BILL_TYPE}"/>
									</c:if>
									<c:if test="${not empty _b_bilForm.map.inputHeaderInfo.billType}">
										<c:if test="${_b_bilForm.map.inputHeaderInfo.billType eq 'CN'}">
											<bean:message key="screen.b_bil.creditNote"/>
										</c:if>
										<c:if test="${_b_bilForm.map.inputHeaderInfo.billType eq 'IN'}">
											<bean:message key="screen.b_bil.invoiceNote"/>
										</c:if>
										<c:if test="${_b_bilForm.map.inputHeaderInfo.billType eq 'DN'}">
											<bean:message key="screen.b_bil.debitNote"/>
										</c:if>
										<html:hidden property="inputHeaderInfo.billType" value="${_b_bilForm.map.inputHeaderInfo.billType}"/>
									</c:if>
									<bean:message key="screen.b_bil.date"/>
									<font class="asteriskBold"><bean:message key="screen.b_bil.asterisk"/></font>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									<html:text property="inputHeaderInfo.dateReq" value="${not empty _b_bilForm.map.headerInfo.DATE_REQ_FOM ? 
													_b_bilForm.map.headerInfo.DATE_REQ_FOM : _b_bilForm.map.inputHeaderInfo.dateReq}" readonly="true"></html:text>
									<t:inputCalendar for="inputHeaderInfo.dateReq" format="dd/MM/yyyy"/>
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.billingAccountNo"/>
									<font class="asteriskBold"><bean:message key="screen.b_bil.asterisk"/></font>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									<html:select property="inputHeaderInfo.billAcc" name="_b_bilForm"
										value="${not empty _b_bilForm.map.headerInfo.BILL_ACC ? 
													_b_bilForm.map.headerInfo.BILL_ACC : _b_bilForm.map.inputHeaderInfo.billAcc}" 
										styleId="cboBillAcc" style="width: 230px;">
										<html:option value="" ><bean:message key="screen.b_bil.blankItem"/></html:option>
										<html:optionsCollection property="listBillingAccountNo" name="_b_bilForm" label="label" value="value"/>
									</html:select>
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.qcsReference"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									<html:text property="inputHeaderInfo.idQcs" name="_b_bilForm" style="width: 230px;"
												value="${not empty _b_bilForm.map.headerInfo.ID_QCS_TXT 
																		? _b_bilForm.map.headerInfo.ID_QCS_TXT : _b_bilForm.map.inputHeaderInfo.idQcs}"/>
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.quoReference"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									<html:text property="inputHeaderInfo.quoRef" name="_b_bilForm" style="width: 230px;"
												value="${not empty _b_bilForm.map.headerInfo.QUO_REF_TXT 
																		? _b_bilForm.map.headerInfo.QUO_REF_TXT : _b_bilForm.map.inputHeaderInfo.quoRef}"/>
								</td>
							</tr>
							<tr>
								<td align="right">	
									<bean:message key="screen.b_bil.customerPO"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									<html:text property="inputHeaderInfo.custPo" styleId="custPoid" name="_b_bilForm" style="width: 230px;" maxlength="30"
												value="${not empty _b_bilForm.map.headerInfo.CUST_PO_TXT 
																		? _b_bilForm.map.headerInfo.CUST_PO_TXT : _b_bilForm.map.inputHeaderInfo.custPo}"/>
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.acManager"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									<html:select property="inputHeaderInfo.idConsult" name="_b_bilForm" style="width: 230px;"
										value="${not empty _b_bilForm.map.headerInfo.ID_CONSULT ? 
													_b_bilForm.map.headerInfo.ID_CONSULT : _b_bilForm.map.inputHeaderInfo.idConsult}" 
										styleId="cboAcManager">
										<html:option value="" ><bean:message key="screen.b_bil.blankItem"/></html:option>
										<html:optionsCollection property="listAcMan" name="_b_bilForm" label="label" value="value"/>
									</html:select>
								</td>
							</tr>
							
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.currency"/>
									<font class="asteriskBold"><bean:message key="screen.b_bil.asterisk"/></font>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;									
									<t:defineCodeList id="LIST_CURRENCY" />
									<html:select property="inputHeaderInfo.billCurrency" name="_b_bilForm" style="width: 230px;"
										value="${not empty _b_bilForm.map.headerInfo.BILL_CURRENCY ? 
													_b_bilForm.map.headerInfo.BILL_CURRENCY : _b_bilForm.map.inputHeaderInfo.billCurrency}" 
														styleId="cboCurrency">
										<html:option value="" ><bean:message key="screen.b_bil.blankItem"/></html:option>
										<html:options collection="LIST_CURRENCY" property="id" labelProperty="name"/>
									</html:select>
								</td>
							</tr>
							<c:if test="${empty _b_bilForm.map.inputHeaderInfo.billType}">
								<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'IN' or _b_bilForm.map.headerInfo.BILL_TYPE eq 'DN'}">
									<tr>
										<td align="right">
											<bean:message key="screen.b_bil.term"/>
										</td>
										<td>
											<bean:message key="screen.b_bil.colon"/>&nbsp;
											<html:text property="inputHeaderInfo.term" name="_b_bilForm" style="width: 230px;"
														value="${not empty _b_bilForm.map.headerInfo.TERM 
																		? _b_bilForm.map.headerInfo.TERM : _b_bilForm.map.inputHeaderInfo.term}"/>
										</td>
									</tr>
								</c:if>
							</c:if>
							<c:if test="${not empty _b_bilForm.map.inputHeaderInfo.billType}">
								<c:if test="${_b_bilForm.map.inputHeaderInfo.billType eq 'IN' or _b_bilForm.map.inputHeaderInfo.billType eq 'DN'}">
									<tr>
										<td align="right">
											<bean:message key="screen.b_bil.term"/>
										</td>
										<td>
											<bean:message key="screen.b_bil.colon"/>&nbsp;
											<html:text property="inputHeaderInfo.term" name="_b_bilForm" style="width: 230px;"
														value="${not empty _b_bilForm.map.headerInfo.TERM 
																		? _b_bilForm.map.headerInfo.TERM : _b_bilForm.map.inputHeaderInfo.term}"/>
										</td>
									</tr>
								</c:if>
							</c:if>
							<tr>
								<td colspan="2">
									&nbsp;
								</td>
							</tr>
						</table>
					</td>
					<td class="col4Top" width="1%" style="background:white">
						&nbsp;
					</td>
					<td class="col5Top" width="20%" rowspan="15" valign="top" align="center" style="background-color:white">
						<div style="background-color:#ccccff;height=50%;align: center;">
							<font class="statusBold">
								<bean:message key="screen.b_bil.status"/>
							</font>
							<br/>
							<c:if test="${_b_bilForm.map.mode eq 'new'}">
								<bean:message key="screen.b_bil.open"/>
							</c:if>
							<c:if test="${_b_bilForm.map.mode eq 'edit'}">
								<c:if test="${_b_bilForm.map.headerInfo.IS_CLOSED eq '1'}">
								<bean:message key="screen.b_bil.closed"/>/
								</c:if>
								<c:if test="${_b_bilForm.map.headerInfo.IS_CLOSED ne '1'}">
									<bean:message key="screen.b_bil.open"/>/
								</c:if>
								<c:if test="${_b_bilForm.map.headerInfo.IS_SETTLED eq '1'}">
									<bean:message key="screen.b_bil.fullSettled"/>
								</c:if>
								<c:if test="${_b_bilForm.map.headerInfo.IS_SETTLED ne '1'}">
									<c:if test="${_b_bilForm.map.headerInfo.PAID_AMOUNT ne 0}">
										<bean:message key="screen.b_bil.partialSettled"/>
									</c:if>
									<c:if test="${_b_bilForm.map.headerInfo.PAID_AMOUNT eq 0}">
										<bean:message key="screen.b_bil.outstanding"/>
									</c:if>
								</c:if>
								<c:if test="${_b_bilForm.map.headerInfo.IS_DELETED eq '1'}">
									/<bean:message key="screen.b_bil.canceled"/>
								</c:if>
							</c:if>
							<br>&nbsp;
							<br>&nbsp;
							<br>&nbsp;
						</div>
						<div  style="background-color:#ffcccc;height=50%;align: center;">
							<font class="preparedBy">
								<bean:message key="screen.b_bil.preparedBy"/>
							</font>
							<br/>
							<center>
								<c:if test="${_b_bilForm.map.mode eq 'new'}">
								</c:if>
								<c:if test="${_b_bilForm.map.mode eq 'edit'}">
									${not empty _b_bilForm.map.inputHeaderInfo.preparedBy ? _b_bilForm.map.inputHeaderInfo.preparedBy : _b_bilForm.map.headerInfo.PREPARED_BY_NAME}
									<html:hidden property="inputHeaderInfo.preparedBy" 
										value="${not empty _b_bilForm.map.inputHeaderInfo.preparedBy ? _b_bilForm.map.inputHeaderInfo.preparedBy : _b_bilForm.map.headerInfo.PREPARED_BY}"/>
								</c:if>
							</center>
							<br/>
							<br/>	
							<font class="preparedBy">	
								<bean:message key="screen.b_bil.date"/>
							</font>
							<br/>
							<center>
								<c:if test="${_b_bilForm.map.mode eq 'new'}">
								</c:if>
								<c:if test="${_b_bilForm.map.mode eq 'edit'}">
									${not empty _b_bilForm.map.inputHeaderInfo.dateCreated ? _b_bilForm.map.inputHeaderInfo.dateCreated : _b_bilForm.map.headerInfo.DATE_CREATED_FOM}
									<html:hidden property="inputHeaderInfo.dateCreated"	value="${not empty _b_bilForm.map.inputHeaderInfo.dateCreated ? _b_bilForm.map.inputHeaderInfo.dateCreated : _b_bilForm.map.headerInfo.DATE_CREATED_FOM}"/>
								</c:if>
							</center>
							<br/>&nbsp;
							<br/>&nbsp;
							<br/>&nbsp;
							<br/>&nbsp;
						</div>
					</td>
				</tr>
			</table>
			</td></tr>
			<tr><td>
			<table class="resultInfo" cellpadding="0" cellspacing="0" id="detailTable">
				<col width="10%"/><col width="10%"/><col width="35%"/><col width="15%"/><col width="15%"/><col width="15%"/>
				<TBODY id="detailTableBody">
					<tr class="header">
						<td style="background-color:rgb(140,176,248)" width="8%">
							<!--<bean:message key="screen.b_bil.item"/>-->
							<div id="addLink"><a href="javascript:void(0);">Add</a></div>
						</td>
						<td style="background-color:rgb(140,176,248)" width="4%"><bean:message key="screen.b_bil.item"/></td>
						<td style="background-color:rgb(140,176,248)" width="48%"><bean:message key="screen.b_bil.billingDescription"/><font class="asteriskBold"><bean:message key="screen.b_bil.asterisk"/></font></td>
						<td style="background-color:rgb(140,176,248)" width="10%"><bean:message key="screen.b_bil.quantity"/><font class="asteriskBold"><bean:message key="screen.b_bil.asterisk"/></font></td>
						<td style="background-color:rgb(140,176,248)" width="12%"><bean:message key="screen.b_bil.unitPrice"/><font class="asteriskBold"><bean:message key="screen.b_bil.asterisk"/></font></td>
						<td style="background-color:rgb(140,176,248)" width="18%"><bean:message key="screen.b_bil.totalAmount"/></td>
					</tr>
					<tr class="hidden" id="sRow">
						<td class="defaultNo" align="left">
							<div id="removeLink">
								<img alt="" src="..\image\delete.gif">
							</div>
							<html:hidden property="itemId" value=""/>
						</td>
						<td class="defaultText" align="left">		    	
							<html:text property="itemNo" value="" maxlength="10" style="width:90%"></html:text>
						</td>
						<td class="defaultText" align="left">		    	
							<html:textarea property="itemDesc" value="" rows="1" cols="50"/>
						</td> 
						<td class="defaultText" align="left">
							<html:text property="itemQty" value="0" onkeypress="return onlyIntNumbers(event)" maxlength="8"></html:text>
						</td>
						<td class="defaultText" align="left">		 
							<html:text property="itemUprice" value="0.00" onkeypress="return onlyDecNumbers(event)" maxlength="12"></html:text>   	
						</td>
						<td class="defaultText" align="left">
							<html:text property="itemAtm" value="0.00" onkeypress="return onlyDecNumbers(event)" maxlength="12"></html:text>
						</td>
					</tr>
					<!-- Detail content -->
					<c:set var="subTotal" value="0.0"></c:set>
					<c:if test="${not empty _b_bilForm.map.detailInfo}">
						<c:forEach items="${_b_bilForm.map.detailInfo}" var="item" varStatus="status">
							<tr>
								<td class="defaultNo" align="left">
									<div id="removeLink">
										<img alt="" src="..\image\delete.gif">
									</div>
									<html:hidden property="itemId" value="${item.ITEM_ID}"/>
								</td>
								<td class="defaultText" align="left">		    	
									<html:text property="itemNo" value="${item.ITEM_NO_TEXT}" maxlength="10" style="width:90%"></html:text>
								</td>
								<td class="defaultText" align="left">		    	
									<html:textarea property="itemDesc" value="${item.ITEM_DESC_TEXT}" rows="1" cols="50"/> 
								</td> 
								<td class="defaultText" align="left">
									<html:text property="itemQty" value="${item.ITEM_QTY}" onkeypress="return onlyIntNumbers(event)" maxlength="8"></html:text>
								</td>
								<td class="defaultText" align="left">		 
									<html:text property="itemUprice" value="${item.ITEM_UPRICE}" onkeypress="return onlyDecNumbers(event)" maxlength="12"></html:text>   	
								</td>
								<td class="defaultText" align="left">
									<html:text property="itemAtm" value="${item.ITEM_AMT}" onkeypress="return onlyDecNumbers(event)" maxlength="20"></html:text>
									<c:set var="subTotal" value="${subTotal + item.ITEM_AMT}"></c:set> 
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty _b_bilForm.map.detailInfo}">
						<c:forEach items="${_b_bilForm.map.itemDesc}" var="item" varStatus="status">
							<c:if test="${status.index ne 0}">
								<tr>
									<td class="defaultNo" align="left">
										${itemDesc[status.index]}
										<div id="removeLink"><img alt="" src="..\image\delete.gif"></div>
										<html:hidden property="itemId" value="${_b_bilForm.map.itemId[status.index]}"/>
									</td>
									<td class="defaultText" align="left">		    	
										<html:text property="itemNo" value="${_b_bilForm.map.itemNo[status.index]}" maxlength="10" style="width:90%"></html:text>
									</td>
									<td class="defaultText" align="left">		    	
										<html:textarea property="itemDesc" value="${_b_bilForm.map.itemDesc[status.index]}" rows="1" cols="50"/> 
									</td> 
									<td class="defaultText" align="left">
										<html:text property="itemQty" value="${_b_bilForm.map.itemQty[status.index]}" onkeypress="return onlyIntNumbers(event)" maxlength="8"></html:text>
									</td>
									<td class="defaultText" align="left">		 
										<html:text property="itemUprice" value="${_b_bilForm.map.itemUprice[status.index]}" onkeypress="return onlyDecNumbers(event)" maxlength="20"></html:text>   	
									</td>
									<td class="defaultText" align="left">
										<html:text property="itemAtm" value="${_b_bilForm.map.itemAtm[status.index]}" onkeypress="return onlyDecNumbers(event)" maxlength="20"></html:text>
										<c:set var="subTotal" value="${subTotal + _b_bilForm.map.itemAtm[status.index]}"></c:set> 
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</c:if>
					<!-- End Detail content -->
					<tr style="font-weight: bold;" id="endDetail">
				   		<td style="background-color:rgb(200,210,230)">
				   		</td>
				   		<td style="background-color:rgb(200,210,230)">
				   		</td>
				   		<td align="right" style="background-color:rgb(200,210,230)" colspan="3">
							<bean:message key="screen.b_bil.subTotal"/>
							&lt;
							${not empty _b_bilForm.map.headerInfo.BILL_CURRENCY ? _b_bilForm.map.headerInfo.BILL_CURRENCY : _b_bilForm.map.inputHeaderInfo.billCurrency}
							&gt;&nbsp;&nbsp;
						</td>
						<td align="right" style="background-color:rgb(200,210,230)">
							<span id="subTotal">${subTotal}</span>
						</td>
					</tr>
					<tr style="font-weight: bold">
						<td style="background-color:rgb(200,210,230)">
				   		</td>
				   		<td style="background-color:rgb(200,210,230)">
				   		</td>
				   		<td align="right" style="background-color:rgb(200,210,230)" colspan="3">
							&nbsp;<bean:message key="screen.b_bil.gst"/>
							&#40;
							${_b_bilForm.map.gstValue}
							&#37;&#41;&nbsp;&nbsp;
							<font class="asteriskBold"><bean:message key="screen.b_bil.asterisk"/></font>
						</td>
						<td align="right" style="background-color:rgb(200,210,230)">
							<html:text property="inputHeaderInfo.gstAmount" 
									value="${not empty _b_bilForm.map.headerInfo.GST_AMOUNT 
										? _b_bilForm.map.headerInfo.GST_AMOUNT : _b_bilForm.map.inputHeaderInfo.gstAmount}" onkeypress="return onlyDecNumbers(event)" maxlength="20"> 
							</html:text>
						</td>
					</tr>
						<tr>
						<td style="background-color:rgb(200,210,230)">
							&nbsp;
						</td>
						<td style="background-color:rgb(200,210,230)">
							&nbsp;
						</td>
						<td style="background-color:rgb(200,210,230)">
							&nbsp;
						</td>
						<td style="background-color:rgb(200,210,230)">
							&nbsp;
						</td>
						<td style="background-color:rgb(200,210,230)">
							&nbsp;
						</td>
						<td style="background-color:rgb(200,210,230)">
							&nbsp;
						</td>
					</tr>
				</TBODY>
			</table>
			</td></tr>
			<tr><td>
			<table width="100%" cellpadding="0" cellspacing="0" style="font-weight:bold">
				<tr align="center">
					<td width="40%" style="background-color:rgb(136,167,216)">
					</td>
					<td width="40%" style="background-color:rgb(136,167,216)"  align="left">
						<bean:message key="screen.b_bil.grandTotalU"/>
						&lt;
						${not empty _b_bilForm.map.headerInfo.BILL_CURRENCY ? _b_bilForm.map.headerInfo.BILL_CURRENCY : _b_bilForm.map.inputHeaderInfo.billCurrency}
						&gt;&nbsp;&nbsp;
						&nbsp;
					</td>
					<td style="background-color:rgb(136,167,216)">
						&nbsp;
					</td>
					<td style="background-color:rgb(136,167,216)">
						&nbsp;
					</td>
					<td width="20%" align="right" style="background-color:rgb(136,167,216);font-weight:bold">
						<span id="grandTotal">${subTotal + (not empty _b_bilForm.map.headerInfo.GST_AMOUNT 
										? _b_bilForm.map.headerInfo.GST_AMOUNT : _b_bilForm.map.inputHeaderInfo.gstAmount)}</span>
						<html:hidden property="grandTotalWording" value="${subTotal + (not empty _b_bilForm.map.headerInfo.GST_AMOUNT 
										? _b_bilForm.map.headerInfo.GST_AMOUNT : _b_bilForm.map.inputHeaderInfo.gstAmount)}"/>
					</td>
				</tr>
			</table>
			</td></tr>
			<tr><td>
			<div style="font-weight:bold"> 
				<t:defineCodeList id="LIST_CURRENCY" />
				(<c:forEach items="${LIST_CURRENCY}" var="item">
					<c:if test="${(not empty _b_bilForm.map.headerInfo.BILL_CURRENCY 
								? _b_bilForm.map.headerInfo.BILL_CURRENCY : _b_bilForm.map.inputHeaderInfo.billCurrency) eq item.id}">
						${item.name}
					</c:if>
				</c:forEach>
				<bean:message key="screen.b_bil.amount"/>: 
				<span id="billAmountToStr">
				${bs:numberToWord(not empty _b_bilForm.map.headerInfo.BILL_AMOUNT 
										? _b_bilForm.map.headerInfo.BILL_AMOUNT : _b_bilForm.map.inputHeaderInfo.billAmount)}
				</span>
				<html:hidden property="inputHeaderInfo.billAmount" value="${not empty _b_bilForm.map.headerInfo.BILL_AMOUNT 
										? _b_bilForm.map.headerInfo.BILL_AMOUNT : _b_bilForm.map.inputHeaderInfo.billAmount}" name="_b_bilForm"/><bean:message key="screen.b_bil.onlyNoSpace"/>
				<br/>
			</div>
			<c:if test="${empty _b_bilForm.map.inputHeaderInfo.billType}">
				<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'IN' 
						or _b_bilForm.map.headerInfo.BILL_TYPE eq 'DN'}">
					<div class="footerInfo">
						<% int footerCounter = 0; %>
						<logic:iterate id="footer" name="_b_bilForm" property="footerInfo">
							<% 
								footerCounter++;
								if(footerCounter != 15){
							%>
							<logic:notEmpty name="footer" property="SET_VALUE">
								<pre><bean:write name="footer" property="SET_VALUE"/>&nbsp;</pre>
							</logic:notEmpty>
							<%	} %>
						</logic:iterate>
					</div>
				</c:if>
			</c:if>
			<c:if test="${not empty _b_bilForm.map.inputHeaderInfo.billType}">
				<c:if test="${_b_bilForm.map.inputHeaderInfo.billType eq 'IN' 
						or _b_bilForm.map.inputHeaderInfo.billType eq 'DN'}">
					<div class="footerInfo">
						<% int footerCounter = 0; %>
						<logic:iterate id="footer" name="_b_bilForm" property="footerInfo">
							<% 
								footerCounter++;
								if(footerCounter != 15){
							%>
							<logic:notEmpty name="footer" property="SET_VALUE">
								<pre><bean:write name="footer" property="SET_VALUE"/>&nbsp;</pre>
							</logic:notEmpty>
							<%	} %>
						</logic:iterate>
					</div>
				</c:if>
			</c:if>
			<c:if test="${empty _b_bilForm.map.inputHeaderInfo.billType}">
				<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'IN' or _b_bilForm.map.headerInfo.BILL_TYPE eq 'DN'}">
					<div class="footerInfo">
						<table width="100%" cellpadding="0" cellspacing="0" style="font-weight:bold">	
							<tr>
								<td>
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td><pre><bean:message key="screen.b_bil.bank"/> <bean:message key="screen.b_bil.colon"/>&nbsp;</pre></td>
										</tr>
										<tr> 
											<td>
												<pre>&nbsp;</pre>
											</td>
										</tr>
										<tr> 
											<td><pre><bean:message key="screen.b_bil.accountName"/> <bean:message key="screen.b_bil.colon"/>&nbsp;</pre></td>
										</tr>
										<tr> 
											<td><pre><bean:message key="screen.b_bil.accountNo"/> <bean:message key="screen.b_bil.colon"/>&nbsp;</pre></td>
										</tr>
										<tr> 
											<td><pre><bean:message key="screen.b_bil.swiftCode"/> <bean:message key="screen.b_bil.colon"/>&nbsp;</pre></td>
										</tr>
									</table>
								</td>
								<logic:iterate id="bankFooter" name="_b_bilForm" property="bankFooterInfo">				
								<td>
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td><pre><bean:write name="bankFooter" property="BANK_NAME"/>&nbsp;</pre></td>
										</tr>
										<tr> 
											<td><pre><bean:write name="bankFooter"  property="BRANCH_NAME"/>&nbsp;</pre></td>
										</tr>
										<tr> 
											<td><pre><bean:write name="bankFooter" property="COM_ACCT_NAME"/>&nbsp;</pre></td>
										</tr>
										<tr> 
											<td><pre><bean:write name="bankFooter" property="COM_ACCT_NO"/>&nbsp; (<bean:write name="bankFooter" property="COM_CUR"/>&nbsp;)</pre></td>
										</tr>
										<tr> 
											<td><pre><bean:write name="bankFooter" property="COM_SWIFT"/>&nbsp;</pre></td>
										</tr>
									</table>
								</td>
								</logic:iterate>
							</tr>
						</table>
					</div>
				</c:if>
			</c:if>
			<c:if test="${not empty _b_bilForm.map.inputHeaderInfo.billType}">
				<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'IN' or _b_bilForm.map.headerInfo.BILL_TYPE eq 'DN'}">
					<div class="footerInfo">
						<table width="100%" cellpadding="0" cellspacing="0" style="font-weight:bold">	
							<tr>
								<td>
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td><pre><bean:message key="screen.b_bil.bank"/> <bean:message key="screen.b_bil.colon"/>&nbsp;</pre></td>
										</tr>
										<tr> 
											<td>
												<pre>&nbsp;</pre>
											</td>
										</tr>
										<tr> 
											<td><pre><bean:message key="screen.b_bil.accountName"/> <bean:message key="screen.b_bil.colon"/>&nbsp;</pre></td>
										</tr>
										<tr> 
											<td><pre><bean:message key="screen.b_bil.accountNo"/> <bean:message key="screen.b_bil.colon"/>&nbsp;</pre></td>
										</tr>
										<tr> 
											<td><pre><bean:message key="screen.b_bil.swiftCode"/> <bean:message key="screen.b_bil.colon"/>&nbsp;</pre></td>
										</tr>
									</table>
								</td>
								<logic:iterate id="bankFooter" name="_b_bilForm" property="bankFooterInfo">				
								<td>
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td><pre><bean:write name="bankFooter" property="BANK_NAME"/>&nbsp;</pre></td>
										</tr>
										<tr> 
											<td><pre><bean:write name="bankFooter"  property="BRANCH_NAME"/>&nbsp;</pre></td>
										</tr>
										<tr> 
											<td><pre><bean:write name="bankFooter" property="COM_ACCT_NAME"/>&nbsp;</pre></td>
										</tr>
										<tr> 
											<td><pre><bean:write name="bankFooter" property="COM_ACCT_NO"/>&nbsp; (<bean:write name="bankFooter" property="COM_CUR"/>&nbsp;)</pre></td>
										</tr>
										<tr> 
											<td><pre><bean:write name="bankFooter" property="COM_SWIFT"/>&nbsp;</pre></td>
										</tr>
									</table>
								</td>
								</logic:iterate>
							</tr>
						</table>
					</div>
				</c:if>
			</c:if>
			<hr class="lineBottom"/>
			<br/>
            <input type="button" name="forward_save" value="Save" onclick="saveClick()" class="button"/>
            <bs:buttonLink action="/RP_B_BIL_S01_01BL" value="Exit"/>
            <html:hidden property="idRef" value="${_b_bilForm.map.idRef}"/>
            <html:hidden property="inputHeaderInfo.idRef" value="${_b_bilForm.map.idRef}"/>
            <html:hidden property="inputHeaderInfo.isClosed" value="${_b_bilForm.map.headerInfo.IS_CLOSED}"/>
            <html:hidden property="mode" value="${_b_bilForm.map.mode}"/>
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
			</td></tr></table>
		</ts:form>
	</ts:body>

</html:html>