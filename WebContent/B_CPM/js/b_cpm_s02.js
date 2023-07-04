var FORWARD_EXIT = "forward_exit";
var FORWARD_ADD_STANDARD = "forward_addStandard";
var FORWARD_STANDARD = "forward_newStandard";
var FORWARD_NONSTANDARD = "forward_newNonStandard";
var FORWARD_NEWPLAN = "forward_new";
var FORWARD_CHOOSE_STANDARD_PLAN = "forward_chooseStandardPlan";
var FORWARD_GET_CUSTOMER = "forward_getCustomerInfo";
var FORWARD_GET_PLAN_INFO = "forward_getPlanInfo";
$(function(){
	//onload function
	var screenWidth = window.screen.width;
	var contentDivWidth = screenWidth-270;
	// set page width
	$('#contentDiv').css("width",contentDivWidth);
	
	//Button add standard
	$('#btnAddStandard').click(function () {
		var customerType = $("#customerType").val();
		var queryString = "&event=" + FORWARD_GET_PLAN_INFO+"&lblCustomerType=" + customerType;
		var url = getUrl(queryString);
		var popupWidth = window.screen.width*80/100;
		var popupHeight = window.screen.height*80/100;
		//window.opener.setPlanInfo(idPlan,planName);
		//M_PPM_S04 page
		var newWindow = window.open(url,'name', POPUP_FEATURE.replace("#width#",popupWidth).replace("#height#",popupHeight));
	});

	//Button add non standard
	$('#btnAddStandardMul').click(function(){
		$("#planType").val("SP");
		$("#idPlanParam").val("");
		$("#planNameParam").val("");
		$("#multiPln").val("1");
		submitForm(FORWARD_NEWPLAN);
	});
	
	//Button add non standard
	$('#btnAddNonStandard').click(function(){
		$("#planType").val("NP");
		submitForm(FORWARD_NEWPLAN);
	});
	
	//Button exit
	$('#btnExit').click(function(){
		var MsgBox = new messageBox();
    	if (MsgBox.POPEXT($('#hiddenMsgExitConfirmation').val()) == MsgBox.YES_CONFIRM) {
    		submitForm(FORWARD_EXIT);
    	}
	});	
	$('#btnHiddenStandard').click(function(){
		$("#planType").val("SP");
		submitForm(FORWARD_STANDARD);
	});
	//Button get Customer Info
	$('#btnGetCustomerInfo').click(function(){
		var queryString = "&event=" + FORWARD_GET_CUSTOMER;
		var url = getUrl(queryString);
		var popupWidth = window.screen.width*80/100;
		var popupHeight = window.screen.height*80/100;
		//window.opener.setCustomerInfo(customerName,customerId,customerType);
		//M_CST_S04 page
		var newWindow = window.open(url,'name', POPUP_FEATURE.replace("#width#",popupWidth).replace("#height#",popupHeight));
//		$("#customerName").html("Microsoft Corporation (China)abcd");
//		$("#customerId").html("21");
//		$("#idCustomer").val("21");
//		$("#customerType").val("CORP");
//		$('#btnAddStandard').attr('disabled',false);
//		$('#btnAddStandardMul').attr('disabled',false);
//		$('#btnAddNonStandard').attr('disabled',false);
	});
});

//M_CST_S04 page selected one record and closed window
function setCustomerInfo(customerName,customerId,customerType){
	$("#customerName").html(customerName);
	$("#customerId").html(customerId);
	$("#idCustomer").val(customerId);
	$("#customerType").val(customerType);
	if(document.getElementById("btnAddStandard")!=null) {
		$('#btnAddStandard').attr('disabled',false);
	}
	if(document.getElementById("btnAddStandardMul")!=null) {
		$('#btnAddStandardMul').attr('disabled',false);
	}
	if(document.getElementById("btnAddNonStandard")!=null) {
		$('#btnAddNonStandard').attr('disabled',false);
	}
}
//M_PPM_S04 page selected one record and closed window
function setPlanInfo(idPlan,planName){
	$("#planType").val("SP");
	$("#idPlanParam").val(idPlan);
	$("#planNameParam").val(planName);
	$("#multiPln").val("0");
	submitForm(FORWARD_NEWPLAN);
}
function addStandard(){
	//var queryString= '&event=' + FORWARD_CHOOSE_STANDARD_PLAN + '&mode=NEWMODE&idCustomer=' + $('#idCustomer').val();
	//popup(queryString);
	submitForm(FORWARD_STANDARD);
}


