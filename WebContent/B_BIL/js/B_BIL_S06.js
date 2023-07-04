$(document).ready(function() {
	
	var contentDivWidth = 1080;
	// set page width
	$('#contentDiv').css("width",contentDivWidth);
	
	//get Parent Page Values
	getParentPageValue();
	
	//var gstPercent = $(".gstPercent").val(); 
	
	//#164 start
	var serviceIsDisplay = $("input[@type=radio][class=serviceIsDisplay][checked]").val();
	if(serviceIsDisplay == '1'){
		$(".serviceDisplayDiscount").attr("checked",true);
		$(".serviceDisplayDiscount").attr("disabled",true);
	}
	//#164 end
	
	/**
	 * Quantity text box change event
	 */
	$('input[class="subPlanItemQty"]').each(function(){
		$(this).change(function(){
			var reg=/^(0|([1-9]\d*))$/;
			var itemQty = $(this).val();
			itemQty = itemQty.replace(/,/g, "");
			if(!reg.test(itemQty)){
				new messageBox().POPALT('This field requires a Number'); 
				$(this).val(0);
				$(this).focus();
			}else{
				var trEle = $(this).parent().parent();
				var itemUprice = $(trEle).find('input[class=subPlanItemUprice]').val();
				itemUprice = itemUprice.replace(/,/g, "");
				var itemAmt = accMul(itemQty,itemUprice);
				$(trEle).find('input[class="subPlanItemAmt"]').val(numberFormat(itemAmt,2,","));
				
				//itemGST
				//var itemGst = calcGst(gstPercent, itemAmt);
				//$(trEle).find('input[class=subPlanItemGst]').val(itemGst);
				var subTotal = 0;
				$('input[class="subPlanItemAmt"]').each(function(index){
					subTotal = accAdd(subTotal,parseFloat($(this).val().replace(/,/g, "")));	
				});
				$(".serviceItemUprice").val(subTotal);
				$(".serviceItemAmt").val(subTotal);
				var displayServiceAmt = numberFormat(subTotal, 2, ",");
				$(".serviceUnitPrice").html(displayServiceAmt);
				$(".serviceAmt").html(displayServiceAmt);
				
				$(this).val(numberFormat(itemQty, 0, ","));
			}
		});
	});
	
	/**
	 * Unit Price text box change event
	 */
	$('input[class="subPlanItemUprice"]').each(function(){
		$(this).change(function(){
			var itemUprice = $(this).val();
			itemUprice = itemUprice.replace(/,/g, "");
			if (/^\.?$/.test(itemUprice) || !/^-?\d*\.?\d*$/.test(itemUprice)) {
				new messageBox().POPALT('This field requires a Number'); 
				$(this).val(0);
				$(this).focus();
			}else{
				var trEle = $(this).parent().parent();
				var itemQty = $(trEle).find('input[class=subPlanItemQty]').val();
				itemQty = itemQty.replace(/,/g, "");
				var itemAmt = accMul(itemQty,itemUprice);
				$(trEle).find('input[class="subPlanItemAmt"]').val(numberFormat(itemAmt,2,","));
				//itemGST
				//var itemGst = calcGst(gstPercent, itemAmt);
				//$(trEle).find('input[class=subPlanItemGst]').val(itemGst);
				var subTotal = 0;
				$('input[class="subPlanItemAmt"]').each(function(index){
					subTotal = accAdd(subTotal,parseFloat($(this).val().replace(/,/g, "")));	
				});
				$(".serviceItemUprice").val(subTotal);
				$(".serviceItemAmt").val(subTotal);
				var displayServiceAmt = numberFormat(subTotal, 2, ",");
				$(".serviceUnitPrice").html(displayServiceAmt);
				$(".serviceAmt").html(displayServiceAmt);
				
				$(this).val(numberFormat(itemUprice, 2, ","));
			}
		});
	});
	
	/**
	 * Total Amount text box change event
	 */
	/*$('input[class="subPlanItemAmt"]').each(function(){
		$(this).change(function(){
			var itemAmt = $(this).val();
			itemAmt = itemAmt.replace(/,/g, "");
			if (/^\.?$/.test(itemAmt) || !/^-?\d*\.?\d*$/.test(itemAmt)) {
				new messageBox().POPALT('This field requires a Number'); 
				$(this).val(0);
				$(this).focus();
			}else{
				//itemGST
				//var itemGst = calcGst(gstPercent, itemAmt);
				//var trEle = $(this).parent().parent();
				//$(trEle).find('input[class=subPlanItemGst]').val(itemGst);
				var subTotal = 0;
				$('input[class="subPlanItemAmt"]').each(function(index){
					subTotal = accAdd(subTotal,parseFloat($(this).val().replace(/,/g, "")));	
				});
				$(".serviceItemUprice").val(subTotal);
				$(".serviceItemAmt").val(subTotal);
				var displayServiceAmt = numberFormat(subTotal, 2, ",");
				$(".serviceUnitPrice").html(displayServiceAmt);
				$(".serviceAmt").html(displayServiceAmt);
				
				$(this).val(numberFormat(itemAmt, 2, ","));
			}
		});
	});*/
	/**
	 * Item Discount text box change event
	 */
	$('input[class="subPlanItemDisc"]').each(function(){
		$(this).change(function(){
			var itemDisc = $(this).val();
			itemDisc = itemDisc.replace(/,/g, "");
			if (/^\.?$/.test(itemDisc) || !/^-?\d*\.?\d*$/.test(itemDisc)) {
				new messageBox().POPALT('This field requires a Number'); 
				$(this).val(0);
				$(this).focus();
			}else{
				$(this).val(discNumberFormat(itemDisc, 2, ","));
				var subTotal = 0;
				$('input[class="subPlanItemDisc"]').each(function(index){
					subTotal = accAdd(subTotal,parseFloat($(this).val().replace(/,/g, "")));	
				});
				$(".serviceItemDisc").val(subTotal);
				var displayServiceDisc = numberFormat(subTotal, 2, ",");
				$(".serviceDisc").html(displayServiceDisc);
			}
		});
	});
	$("#contentDiv").focus();
});

/**
 * get Parent Page Values
 * @param bilDetailInfos
 */
function getParentPageValue(){
	var currentItemId = $(".serviceItemId").val();
	//parent page Service object
	var serviceObj = window.opener.bilS06InitServiceCall(currentItemId);
	
	if (serviceObj==null) {
		window.close();
	} else {
		//parent page Service Item Desc
		$(".serviceItemDesc").val(serviceObj.serviceItemDesc);
		
		$(".gstCheck").val(serviceObj.gstCheck);
		$(".servicePoNo").val(serviceObj.servicePoNo);
		/*$(".serviceItemDesc").autoTextarea({ maxHeight: 1000 });*/
		//current page Sub plan
		var currentSubItemBillDetails = $(".subPlan");
		//show job modules flag 0:not display,1:display
		var jobModulesDisplayFlg = $(".jobModulesDisplayFlg").val();
		for ( var j = 0; j < currentSubItemBillDetails.length; j++) {
			var currentSubItemBillDetail = currentSubItemBillDetails.eq(j);
			//get Parent sub plan value
			var subPlanObj = window.opener.bilS06InitSubPlanCall(j);
			if(subPlanObj==null) {
				window.close();
			} else {
				var subPlanItemDesc = subPlanObj.subPlanItemDesc;
				var subPlanItemQty = subPlanObj.subPlanItemQty;
				var subPlanItemUprice = subPlanObj.subPlanItemUprice;
				var subPlanItemAmt = subPlanObj.subPlanItemAmt;
				var subPlanApplyGst = subPlanObj.subPlanApplyGst;
				var subPlanJobNo = subPlanObj.subPlanJobNo;
				var subPlanIsDisplayJobNo = subPlanObj.subPlanIsDisplayJobNo;
				var subPlanItemGst = subPlanObj.subPlanItemGst;
				var subPlanItemDisc = subPlanObj.subPlanItemDisc;
				//set parent sub plan value to current page
				currentSubItemBillDetail.find(".subPlanItemDesc").val(subPlanItemDesc);
				currentSubItemBillDetail.find(".subPlanItemDesc").attr("id", "subPlanItemDesc"+j);
				currentSubItemBillDetail.find(".subPlanItemQty").val(numberFormat(subPlanItemQty,0,","));
				currentSubItemBillDetail.find(".subPlanItemUprice").val(numberFormat(subPlanItemUprice,2,","));
				currentSubItemBillDetail.find(".subPlanItemAmt").val(numberFormat(subPlanItemAmt,2,","));
				currentSubItemBillDetail.find(".subPlanItemGst").val(subPlanItemGst);
				if (subPlanItemDisc != "0") {
					currentSubItemBillDetail.find(".subPlanItemDisc").val(numberFormat(subPlanItemDisc,2,","));
				}else {
					currentSubItemBillDetail.find(".subPlanItemDisc").val("0.00");
				}
				currentSubItemBillDetail.find(".subPlanItemGst").val(subPlanApplyGst);
				/*if(subPlanApplyGst=="1") {
					currentSubItemBillDetail.find(".subPlanApplyGst").attr("style","display:inline");
				}*/
				if(jobModulesDisplayFlg=="1") {
					currentSubItemBillDetail.find(".subPlanJobNo").val(subPlanJobNo);
					if("1"==subPlanIsDisplayJobNo) {
						currentSubItemBillDetail.find(".subPlanIsDisplayJobNo").attr("checked",'true');;
					}
				}
				
				/*var subPlanItemDescId = "#subPlanItemDesc"+j;
				$(subPlanItemDescId).autoTextarea({ maxHeight: 1000 });
				$(subPlanItemDescId).focus();*/
			}
		}
		//$(".serviceItemDesc").focus();
	}
}

/**
 * save button event
 */
function save(){
	if(validate()){
		//#164 start
		var serviceIsDisplay = $("input[@type=radio][class=serviceIsDisplay][checked]").val();
		var serviceDisplayDiscount = $("input[@type=radio][class=serviceDisplayDiscount][checked]").val();
		var message = "Service 1: Not all sub plan / option services have "
			+ "<br/>applied the same GST Tax Code.";
		if (serviceIsDisplay == '1' || serviceDisplayDiscount == '1') {
			var subPlans = $(".subPlan");
			for ( var i = 0; i < subPlans.length - 1; i++) {
				var gstValue = $("#subPlanItemGst" + (i + 1)).find("option:selected").attr("value");
				var nextGstValue = $("#subPlanItemGst" + (i + 2)).find("option:selected").attr("value");
				if (gstValue != nextGstValue) {
					var MsgBox = new messageBox();
					MsgBox.POPALT(message);
					return false;
				}
			}
		}
		//#164 end
		changeParentPageValue();
	} else {
		return false;
	}
}

/**
 * exit button event
 */
function exit(){
	window.close();
}

/**
 * change the parent page value
 */
function changeParentPageValue(){
	if(window.opener.B_BIL_S06ReturnCalled!=undefined) {
		var currentItemId = $(".serviceItemId").val();
		//Billing From
		var serviceBillFrom = $(".serviceBillFrom").val();
		//Billing To
		var serviceBillTo = $(".serviceBillTo").val();
		//is Display
		var serviceIsDisplay = $("input[@type=radio][class=serviceIsDisplay][checked]").val();
		//Billing Desc
		var serviceItemDesc = $(".serviceItemDesc").val();
		//serviceIsDisplayMinSvc
		var serviceIsDisplayMinSvc = $(".serviceIsDisplayMinSvc").val();
		//Service Uprice
		var serviceUprice = $(".serviceItemUprice").val();
		//Service Amt
		var serviceAmt = $(".serviceItemAmt").val();
		//Service itemDisc
		var serviceItemDisc = $(".serviceItemDisc").val();
		//servicePoNo
		var servicePoNo = $(".servicePoNo").val();
		//serviceDisplayDiscount
		var serviceDisplayDiscount = $("input[@type=radio][class=serviceDisplayDiscount][checked]").val();
		var enServiceBillFrom = dataFormatChange(serviceBillFrom);
		var enServiceBillTo = dataFormatChange(serviceBillTo);
		
		//service value object
		var serviceObj = new Object();
		serviceObj.itemId = currentItemId;
		serviceObj.serviceBillFrom = serviceBillFrom;
		serviceObj.serviceBillTo = serviceBillTo;
		serviceObj.enServiceBillFrom = enServiceBillFrom;
		serviceObj.enServiceBillTo = enServiceBillTo;
		serviceObj.serviceIsDisplay = serviceIsDisplay;
		serviceObj.serviceItemDesc = serviceItemDesc;
		serviceObj.serviceIsDisplayMinSvc = serviceIsDisplayMinSvc;
		serviceObj.serviceUprice = serviceUprice;
		serviceObj.serviceAmt = serviceAmt;
		serviceObj.serviceItemDisc = serviceItemDisc;
		serviceObj.servicePoNo = servicePoNo;
		serviceObj.serviceDisplayDiscount = serviceDisplayDiscount;
		//Call parent page set service
		window.opener.setServiceValue(serviceObj);
				
		//get parent page sub plan
		//var subItemBillDetails = itemBillDetail.find(".subPlan");
		//current page Sub plan
		var currentSubItemBillDetails = $(".subPlan");
		//show job modules flag 0:not display,1:display
		var jobModulesDisplayFlg = $(".jobModulesDisplayFlg").val();
		
		for ( var j = 0; j < currentSubItemBillDetails.length; j++) {
			var currentSubItemBillDetail = currentSubItemBillDetails.eq(j);
			//Item Description
			var subPlanItemDesc = currentSubItemBillDetail.find(".subPlanItemDesc").val();
			var subPlanItemQty = currentSubItemBillDetail.find(".subPlanItemQty").val();
			var subPlanItemUprice = currentSubItemBillDetail.find(".subPlanItemUprice").val();
			var subPlanItemAmt = currentSubItemBillDetail.find(".subPlanItemAmt").val();
			//var subPlanApplyGst = currentSubItemBillDetail.find(".subPlanApplyGst").val();
			var subPlanJobNo = currentSubItemBillDetail.find(".subPlanJobNo").val();
			var subPlanIsDisplayJobNo = "0";
			var subPlanIsDisplayJobNoIsChecked = currentSubItemBillDetail.find(".subPlanIsDisplayJobNo").is(':checked');
			//is Job No checkBox checked
			if (subPlanIsDisplayJobNoIsChecked) {
				subPlanIsDisplayJobNo = "1";
			}
			var subPlanItemGst = currentSubItemBillDetail.find(".subPlanItemGst").val(); 
			var subPlanTaxRate = $("#subPlanItemGst" + (j + 1)).find("option:selected").attr("tax_rate");
			var subPlanTaxCode = $("#subPlanItemGst" + (j + 1)).find("option:selected").attr("tax_code");
			var subPlanItemDisc = currentSubItemBillDetail.find(".subPlanItemDisc").val();
			//sub plan value object
			var subPlanObj = new Object();
			subPlanObj.serviceBillFrom = serviceBillFrom;
			subPlanObj.serviceBillTo = serviceBillTo;
			subPlanObj.serviceIsDisplay = serviceIsDisplay;
			subPlanObj.enServiceBillFrom = enServiceBillFrom;
			subPlanObj.enServiceBillTo = enServiceBillTo;
			subPlanObj.subPlanItemDesc = subPlanItemDesc;
			subPlanObj.subPlanItemQty = subPlanItemQty;
			subPlanObj.subPlanItemUprice = subPlanItemUprice;
			subPlanObj.subPlanItemAmt = subPlanItemAmt;
			//subPlanObj.subPlanItemGst = subPlanItemGst;
			subPlanObj.subPlanJobNo = subPlanJobNo;
			subPlanObj.subPlanIsDisplayJobNo = subPlanIsDisplayJobNo;
			subPlanObj.jobModulesDisplayFlg = jobModulesDisplayFlg;
			subPlanObj.subPlanTaxRate = subPlanTaxRate;
			subPlanObj.subPlanTaxCode = subPlanTaxCode;
			subPlanObj.subPlanApplyGst = subPlanItemGst;
			subPlanObj.subPlanItemDisc = subPlanItemDisc;
			if(serviceDisplayDiscount == "1"){
				subPlanObj.subPlanDisplayDiscount = "0";
			}else {
				subPlanObj.subPlanDisplayDiscount = "1";
			}
			subPlanObj.servicePoNo = servicePoNo;
			subPlanObj.gstCheck = $(".gstCheck").val();
			//call parent page set sub plan value
			window.opener.setSubPlanValue(j,subPlanObj);
		}
		window.opener.B_BIL_S06ReturnCalled(currentItemId);
		window.close();
	} else {
		window.close();
	}
}

/**
 * input check
 */
function validate(){
	$(".error").html("");
	var serviceBillFrom = $(".serviceBillFrom").val();
	var serviceBillTo = $(".serviceBillTo").val();
	var serviceItemDesc = $(".serviceItemDesc").val();
	var checkIsOKFlg = true;
	if(isEmpty(serviceBillFrom)) {
		checkIsOKFlg = false;
		$(".error").html($(".error").html()+"<br/>"+$("#message_group").find(".messageBillPeriodFrom").text());
	}
	if(isEmpty(serviceBillTo)) {
		checkIsOKFlg = false;
		$(".error").html($(".error").html()+"<br/>"+$("#message_group").find(".messageBillPeriodTo").text());
	}
	if(isEmpty(serviceItemDesc)) {
		checkIsOKFlg = false;
		$(".error").html($(".error").html()+"<br/>"+$("#message_group").find(".messageBillDesc").text());
	}
	var currentSubItemBillDetails = $(".subPlan");
	var subPlanIsOKFlg = true;
	//Sub plan is input check
	for ( var j = 0; j < currentSubItemBillDetails.length; j++) {
		var currentSubItemBillDetail = currentSubItemBillDetails.eq(j);
		if (subPlanIsOKFlg) {
			var subPlanItemDesc = currentSubItemBillDetail.find(".subPlanItemDesc").val();
			var subPlanItemQty = currentSubItemBillDetail.find(".subPlanItemQty").val();
			var subPlanItemUprice = currentSubItemBillDetail.find(".subPlanItemUprice").val();
			var subPlanItemAmt = currentSubItemBillDetail.find(".subPlanItemAmt").val();
			if(isEmpty(subPlanItemDesc)) {
				checkIsOKFlg = false;
				subPlanIsOKFlg = false;
				$(".error").html($(".error").html()+"<br/>"+$("#message_group").find(".messageItemDesc").text());
			}
			if(isEmpty(subPlanItemQty)) {
				checkIsOKFlg = false;
				subPlanIsOKFlg = false;
				$(".error").html($(".error").html()+"<br/>"+$("#message_group").find(".messageItemQty").text());
			}
			if(isEmpty(subPlanItemUprice)) {
				checkIsOKFlg = false;
				subPlanIsOKFlg = false;
				$(".error").html($(".error").html()+"<br/>"+$("#message_group").find(".messageItemUpprice").text());
			}
			if(isEmpty(subPlanItemAmt)) {
				checkIsOKFlg = false;
				subPlanIsOKFlg = false;
				$(".error").html($(".error").html()+"<br/>"+$("#message_group").find(".messageItemAmt").text());
			}
		}
	}
	return checkIsOKFlg;
}

/**
 * dd/mm/yyyy to dd MMM yyyy change
 * @param str
 * @returns {String}
 */
function dataFormatChange(str) {
	var dd = str.substring(0,2);
	var mm = str.substring(3,5);
	var yyyy = str.substring(6,10);
	mm = parseInt(mm,10);
	enMM = "";
	if(1==mm) {
		enMM = "Jan";
	} else if(2==mm) {
		enMM = "Feb";
	} else if(3==mm) {
		enMM = "Mar";
	} else if(4==mm) {
		enMM = "Apr";
	} else if(5==mm) {
		enMM = "May";
	} else if(6==mm) {
		enMM = "Jun";
	} else if(7==mm) {
		enMM = "Jul";
	} else if(8==mm) {
		enMM = "Aug";
	} else if(9==mm) {
		enMM = "Sep";
	} else if(10==mm) {
		enMM = "Oct";
	} else if(11==mm) {
		enMM = "Nov";
	} else if(12==mm) {
		enMM = "Dec";
	}
	return dd+"-" + enMM +"-" +yyyy;
}

/**
 * is empty check
 * @param str
 * @returns {Boolean} true:is empty,false:not empty
 */
function isEmpty(str){
	if (str==""||str==null){
		return true;
	} else {
		str=str.replace(/[ ]/g,"");
		if(str==""){
			return true;
		} else {
			return false;
		}
	}
}
/**
 * only input Dec Number
 * @param evt
 * @returns {Boolean}
 */
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

/**
 * only input int number
 * @param evt
 * @returns {Boolean}
 */
function onlyIntNumbers(evt) {
	var e = evt;
	if(window.event){ // IE
		var charCode = e.keyCode;
	} else if (e.which) { // Safari 4, Firefox 3.0.4
		var charCode = e.which;
	}
	if (charCode > 31 && (charCode < 48 || charCode > 57))
	return false;			
	return true;
}

/**
 * number formate(000000.00-->000,000.00)
 * @param value
 * @param decimalValue
 * @param splitChar
 * @returns {Number}
 */
function numberFormat(value, decimalValue, splitChar) {
	var nv = value.toString();
	var isFlg = false;
	if(nv.indexOf("-") >= 0){
		nv = nv.split('-')[1];
		isFlg = true;
	}
	
	var power = 1;
	for ( var i = 0; i < decimalValue; i++) {
		power *= 10;
	}
	var newValue = parseFloat(Math.round(nv * power)) / power;
	newValue = addCommas(newValue, decimalValue, ",");
	if(isFlg == true){
		newValue="-"+newValue;
	}
	return newValue;
}

/**
 * number formate(000000.00-->000,000.00)
 * @param value
 * @param decimalValue
 * @param splitChar
 * @returns {Number}
 */
function discNumberFormat(value, decimalValue, splitChar) {
	var nv = value.toString();
	if(nv.indexOf("-") >= 0){
		nv = nv.split('-')[1];
	}
	
	var power = 1;
	for ( var i = 0; i < decimalValue; i++) {
		power *= 10;
	}
	var newValue = parseFloat(Math.round(nv * power)) / power;
	newValue = addCommas(newValue, decimalValue, ",");
	newValue="-"+newValue;
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

/**
 * Auto Calculation of GST amount
 * @param arg1
 * @param arg2
 * @returns
 */
function calcGst(arg1,arg2){ 
	var gstCalcAmt;
	gstCalcAmt = accMul((arg1/100),arg2);
	return numberFormat(gstCalcAmt,2,",").replace(/,/g, "");
}

/**
 * Multiplication
 */
function accMul(arg1,arg2){ 
	var m=0;
	var s1=arg1.toString();
	var s2=arg2.toString(); 
	try{m+=s1.split(".")[1].length;}catch(e){};
	try{m+=s2.split(".")[1].length;}catch(e){} ;
	return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m);
}

/**
 * Addition
 * @param arg1
 * @param arg2
 * @returns {Number}
 */
function accAdd(arg1,arg2){ 
	var r1,r2,m,n; 
	try{r1=arg1.toString().split(".")[1].length;}catch(e){r1=0;};
	try{r2=arg2.toString().split(".")[1].length;}catch(e){r2=0;};
	m=Math.pow(10,Math.max(r1,r2));
	n=(r1>=r2)?r1:r2;
	return ((arg1*m+arg2*m)/m).toFixed(n);
}
//#164 start
/**
 * 
 * @param obj
 * @return
 */
function changItemmise(obj) {
	var service = $(obj).closest("div.service_object");
	// IF Billing Amount = Itemised, a. both radio button enabledb. default
	// selection: Itemised
	if($(obj).val() == '0' && $(obj).is(':checked')) {
		$(".serviceDisplayDiscount").attr("disabled",false);
	}
	// ELSE a. both radio button disabledb. default selection: Lump Suim
	else{
		$(".serviceDisplayDiscount").attr("checked",true);
		$(".serviceDisplayDiscount").attr("disabled",true);
	}
}
//#164 end