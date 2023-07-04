var msgBox = new messageBox("");
function init() {
	var param = window.dialogArguments;
	if(param != undefined) {
		var message = param.message;
		//set message to display
		var content = document.getElementById("content");
		if (content != undefined) {
			content.innerHTML = message;
		}
	}
}
function initButtonValue(){
	var param = window.dialogArguments;
	if(param != undefined) {
		var param = window.dialogArguments;
		if(param != undefined) {
			var buttonValue1 = param.buttonValue1;
			var buttonValue2 = param.buttonValue2;
			var btn1 = document.getElementById("btn1");
			var btn2 = document.getElementById("btn2");
			if (btn1 != undefined) {
				btn1.value = buttonValue1;
			}
			if (btn2 != undefined) {
				btn2.value = buttonValue2;
			}
		}
	}
}

function initDay(e) {
	var date = new Date();
	var day = date.getDate();
	var month = date.getMonth() + 1;
	var year = date.getFullYear();
	document.getElementById(e).value = (day + "/" + month + "/" + year);
}
	
function doYes() {
	window.returnValue = msgBox.YES_CONFIRM;
	window.close();
}

function doNo() {
	window.returnValue = msgBox.NO_CONFIRM;
	window.close();
}

function doOk() {
	window.returnValue = msgBox.OK_CONFIRM;
	window.close();
}

function doCancel() {
	window.returnValue = msgBox.CANCEL_CONFIRM;
	window.close();
}

function doPopterYes() {
	window.returnValue = msgBox.YES_CONFIRM + ";" + document.getElementById("tDate").value;
	window.close();
}

function doPopterNo() {
	window.returnValue = msgBox.NO_CONFIRM + ";";
	window.close();
}

function checkEmpty() {
	var minSvcPeriod = $("#minSvcPeriod").val();
	if (minSvcPeriod=="1"){
		var minimumServiceTo = $("#minimumServiceTo").val();
		var terminateDate = $("#terminateDate").val();
		if(terminateDate!=null&&terminateDate!=""&&minimumServiceTo!=null&&minimumServiceTo!="") {
			if(compareDate(minimumServiceTo,terminateDate)){
				$("#warningMsg").text("Warning: Service end date within contract period");
			} else {
				$("#warningMsg").text("");
			}
		}
	}
}

function serviceEndDateInit(){
	if ($("#resultType").val() == "success") {
		window.returnValue = msgBox.OK_CONFIRM;
		window.close();
	} else if ($("#radiusConfirmFlg").val() != "") {
		//show confirm popup to generate CREDIT NOTE
		var result = "";
		if($("#radiusConfirmFlg").val()=="1") {
			result = msgBox.POPCPM($("#ERR4SC019_1").html(), "Reactivate", "Cancel");
		} else {
			result = msgBox.POPCPM($("#ERR4SC019_2").html(), "Terminate", "Cancel");
		}
		if (result == msgBox.OK_CONFIRM) {
			$("#clickRadiusYesFlg").val("1");
			$("#radiusConfirmFlg").val("");
			serviceEndDateSubmit();
		}
	}
	if($("#serviceStatus").val()=="PS7"){
		$("#svcStatusDiv").css("background-color","#dadada");
	}
}

function serviceEndDateSubmit() {
	var action = document.forms[0].action;
	document.forms[0].action = action + "&event=forward_popupServiceEndDateSave";
	document.forms[0].submit();
}

function svcEndDateChange(){
	var contractPeriodType = $("#contractPeriodType").val();
	var svcEndDate = $("#svcEndDate").val();
	var sysdate = $("#sysdate").val();
	var billTo = $("#billTo").val();
	var billInstruct = $("#billInstruct").val();
	var warningMsg = "";
	$("#warningMsg").html("");
	$("#errorInfo").html("");
	if (contractPeriodType=="1"){
		var contractPeriodTo = $("#contractPeriodTo").val();
		if(svcEndDate!=null&&svcEndDate!=""&&contractPeriodTo!=null&&contractPeriodTo!="") {
			if(compareDate(contractPeriodTo,svcEndDate)){
				warningMsg = $("#ERR1SC105_CONTRACT_PERIOD").html().replace("{0}", countTimeLength("D", svcEndDate, contractPeriodTo));
			}
		}
	}
	if(svcEndDate!=null&&svcEndDate!=""&&billTo!=null&&billTo!="") {
		if(compareDate(billTo,svcEndDate)){
			if(warningMsg!="") {
				warningMsg = warningMsg + "<br/>";
			}
			warningMsg = warningMsg + $("#ERR1SC105_BILLING_PERIOD").html().replace("{0}", countTimeLength("D", svcEndDate, billTo));
		}
	}
	if(warningMsg!="") {
		$("#warningMsg").html(warningMsg);
	}
	if ((svcEndDate==null || svcEndDate =="") && billInstruct!="6") {
		$("#lblServiceStatus").text("Active");
		$("#serviceStatus").val("PS3");
		var colorClass = $("#codeColor #PS3").val();
		$("#svcStatusDiv").attr("class", colorClass);
	} else if(compareDate(sysdate, svcEndDate)==false) {
		$("#lblServiceStatus").text("Active");
		$("#serviceStatus").val("PS3");
        var colorClass = $("#codeColor #PS3").val();
        $("#svcStatusDiv").attr("class", colorClass);
	} else if(compareDate(sysdate, svcEndDate)) {
		$("#lblServiceStatus").text("Terminated");
		$("#serviceStatus").val("PS7");
        var colorClass = $("#codeColor #PS7").val();
        $("#svcStatusDiv").attr("class", colorClass);
	}
}

function serviceEndDateSaveClick() {
	var svcStartDate = $("#svcStartDate").val();
	var svcEndDate = $("#svcEndDate").val();
	var sysdate = $("#sysdate").val();
	var billInstruct = $("#billInstruct").val();
	if(compareDate(svcStartDate, svcEndDate)) {
		$("#errorInfo").html($("#ERR1SC105_1").html());
		return false;
	}
	if((svcEndDate==null || svcEndDate =="") && billInstruct=="6") {
		$("#errorInfo").html($("#ERR1SC105_2").html());
		return false;
	}
	serviceEndDateSubmit();
	return true;
}

function POPACCClickInit(){
	if ($("#resultType").val() == "success") {
		window.returnValue = msgBox.OK_CONFIRM;
		window.close();
	}
}
function POPACCClickSave() {
	var action = document.forms[0].action;
	document.forms[0].action = action + "&event=forward_changeAccessAccountSave";
	document.forms[0].submit();
}

function POPAPWClickInit(){
	if ($("#resultType").val() == "success") {
		window.returnValue = msgBox.OK_CONFIRM;
		window.close();
	}
}
function POPAPWClickSave() {
	var action = document.forms[0].action;
	document.forms[0].action = action + "&event=forward_changeAccessPasswordSave";
	document.forms[0].submit();
}

function doPoprejYes() {
	//tDate
	var tDate = document.getElementById("tDate").value;
	//remark
	var remark = document.getElementById("remark").value;
	//hidTransDatePop
	var hidTransDatePop = document.getElementById("hidTransDatePop").value;
	var isCheckMulCharFlg = document.getElementById("isCheckMulCharFlg").value;
	var rejectDate = "Please select future date for Rejection Date than Transaction Date.";
	var errorMsg = "";
	if(tDate !== ""){
		if(duibi(hidTransDatePop,tDate)){
			errorMsg = errorMsg + rejectDate;
		}
	}
	if ("1"==isCheckMulCharFlg) {
		if (!/^[0-9a-zA-Z\r\n ~`!@#$%^&*()-_+=\\{}|;':\",.\/<>?]*$/.test(remark)) {
			var checkMulCharError = "Remark - invalid characters are used. Valid characters are as follows. <br/>1-9, a-z, A-Z, single-byte space, enter key ~ ` ! @ # $ % ^ & * ( ) _ -  + = { } [ ] | \\ : ; \" ' < > ? , . /";
			errorMsg = errorMsg + "<br/>"+ checkMulCharError;
		}
	}
	if(errorMsg!="") {
		document.getElementById('errorInfo').innerHTML = errorMsg.fontcolor("red");
		return;
	}
	window.returnValue = msgBox.YES_CONFIRM + ";" + tDate+";"+remark;
	window.close();
}

function doPoprejNo() {
	window.returnValue = msgBox.NO_CONFIRM + ";";
	window.close();
}
function doTerminate() {
	var action = document.forms[0].action;
	document.forms[0].action = action + "&event=forward_serviceTerminate";
	document.forms[0].submit();
}

function initPOPTER() {
	if ($("#actionStatus").val() == "success") {
		window.returnValue = msgBox.OK_CONFIRM;
		window.close();
	} else if ($("#actionStatus").val() == "confirm") {
		//show confirm popup to generate CREDIT NOTE
		var result = msgBox.POPCFM($("#msgConfirm").text());
		if (result == msgBox.OK_CONFIRM) {
			$("#actionStatus").val("generate");
		} else {
			$("#actionStatus").val("complete");
		}
		doTerminate();
	}else if($("#actionStatus").val() == "confirm1"){
	        msgBox.POPALT($("#msgConfirm1").text());
	        $("#actionStatus").val("generate1");
	        doTerminate();
	}
	document.getElementById('terminateDate').attachEvent('onpropertychange',checkEmpty);   
}
function duibi(a,b){
	var arr=a.split("/");
	var starttime=new Date(arr[2],arr[1]-1,arr[0]);
	var starttimes=starttime.getTime();
	
	var arrs=b.split("/");
	var lktime=new Date(arrs[2],arrs[1]-1,arrs[0]);
	var lktimes=lktime.getTime();

	if(starttimes>lktimes){
		return true;
	}else{
		return false;
	}
}

/**
 * compare date
 * @param startDt
 * @param endDt
 * @returns {Boolean} startDt>endDt:true,startDt<=endDt:false;
 */
function compareDate(startDt, endDt){
    var sArr = startDt.split('/');
    var eArr = endDt.split('/');
    var checkSdate = new Date(sArr[2],sArr[1]-1,sArr[0]);
    var checkEdate = new Date(eArr[2],eArr[1]-1,eArr[0]);
    if(checkEdate < checkSdate){
    	return true;
    }
    return false;
}
/** 
 * @param  interval Calculation Type:D is day,H is hour, M is minute,S is second,T is millisecond
 * @param  date1 start date 
 * @param  date2 end date
 * @return  
 */ 
function countTimeLength(interval, date1, date2) {  
    var objInterval = {'D' : 1000 * 60 * 60 * 24, 'H' : 1000 * 60 * 60, 'M' : 1000 * 60, 'S' : 1000, 'T' : 1};  
    interval = interval.toUpperCase();
    var date1Arr = date1.split("/");
    var date2Arr = date2.split("/");
    var dt1 = Date.parse(date1Arr[1] + "/" + date1Arr[0] + "/" + date1Arr[2]);  
    var dt2 = Date.parse(date2Arr[1] + "/" + date2Arr[0] + "/" + date2Arr[2]);  
    try{  
        return Math.abs((dt2 - dt1) / objInterval[interval]);
    }catch (e){ 
        return e.message;  
    }  
} 