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
 * number formate(000000.00-->000,000.00)
 * @param value
 * @param decimalValue
 * @param splitChar
 * @returns {Number}
 */
function numberFormat(value, decimalValue, splitChar) {
	var power = 1;
	for ( var i = 0; i < decimalValue; i++) {
		power *= 10;
	}
	var newValue = parseFloat(Math.round(value * power)) / power;
	newValue = addCommas(newValue, decimalValue, ",");
	return newValue;
}

/**
 * only input Dec Number
 * @param evt
 * @returns {Boolean}
 */
function onlyDecNumbers(obj,evt) {
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
		if(48<=charCode&&57>=charCode){
			return decbit(obj);
		}
		else{
			return true;
		}
	}
}

function decbit(obj){
	var cursurPosition = -1;  
	if (obj.selectionStart) {//!IE
		cursurPosition = obj.selectionStart;
	}  
	else {//IE   
		var range = document.selection.createRange();  
		range.moveStart("character", -obj.value.length);  
		cursurPosition = range.text.length;  
	}
	var value = $.trim(obj.value);
	var arr = value.split(".");
	if(arr.length>1){
		if(arr[0].length>=4&&cursurPosition<=arr[0].length){
			return false;
		}
		if(arr[1].length>=8&&cursurPosition>arr[0].length){
			return false;
		}
	}
	else{
		if(arr[0].length>=4){
			return false;
		}
	}
	return true;
}

function validateNumber(obj){
	if (/^\.?$/.test($(obj).val()) || !/^-?\d*\.?\d*$/.test($(obj).val())) {
		new messageBox().POPALT('This field requires a Number'); 
		$(obj).val("0");
		return false;
	};
	return true;
}

function curRateChange(obj){
	var curRate = $(obj).val();
	validateNumber(obj);
	
	getExportAmount();
}

/**
 * get Export Amount value
 */
function getExportAmount(){
	var billCur = $("#billCurrency").val();
	var expCur = $("#exportCur").val();
	if(expCur!=null && expCur !=undefined) {
		if(billCur != "" && billCur != null
			&& expCur != "" && expCur != ""
			&& expCur != billCur) {
			var fixedForex = $("#fixedForex").val();
			var gstAmount = $("#gstAmount").val();
			var subTotalAmt = $("#subTotalAmt").val();
			var curRate = $("#curRate").val();
			var path = $("#rootPath").val();
			if (parseFloat(curRate)==0.0) {
				$("#curRate").val("1");
				curRate = "1";
			}
			var indexAmt = $("#indexAmt").val();
			var itemAmtString = "";
			var itemApplyGstString = "";
			for(var i = 1;i<=indexAmt;++i){
				itemAmtString = itemAmtString + $("#itemAmtHidden"+i).val()+",";
				itemApplyGstString = itemApplyGstString + $("#itemApplyGstHidden"+i).val()+",";
			}
			itemAmtString = itemAmtString.substring(0, itemAmtString.length-1);
			itemApplyGstString = itemApplyGstString.substring(0, itemApplyGstString.length-1);
			var url = path + "/B_BIL/GetExpAmtAjax.do";
			$.ajax({
		        type: 'POST',
		        url: url,
		        data: {billingCurrency : billCur,
		        	   exportCurrency  : expCur,
		        	   gstAmount       : gstAmount,
		        	   subTotalAmt     : subTotalAmt,
		        	   fixedForex      : fixedForex,
		        	   curRate         : curRate,
		        	   itemAmtString   : itemAmtString,
		        	   itemApplyGstString : itemApplyGstString
		        	   },
		        success: function(data){
		        	data = parseFloat(data).toFixed(2);
		            $("#spanExportAmount").text(numberFormat(data,2,","));
		            $("#exportAmount").val(data);
		            if($("#isDisAmt").val()== '1'){
		            	$("#numberWordLabel").html(nbrToEngWord(numberFormat(data,2,","),",",".",'S'));
		            }
		        }
		    });
		}
	}
}