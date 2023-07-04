var FORWARD_PAGELINK = "forward_viewCustomerPlan";
var FORWARD_EDIT_PLAN = "forward_edit";
var FORWARD_DELETE_CUSTOMER_PLAN = "forward_deleteCustomerPlan";
var FORWARD_GENERATE_BIF = "forward_generateBIF";
var FORWARD_VIEW_SUBSCRIPTION_INFO = "forward_viewSubscriptionInfo";
var FORWARD_EXIT = "forward_exit";
var FORWARD_APPROVE = "forward_approve";
var FORWARD_SUSPEND = "forward_suspend";
var FORWARD_UNSUSPEND = "forward_unsuspend";
var FORWARD_TERMINATE = "forward_popupTerminate";
var FORWARD_SERVICE_END_DATE = "forward_popupServiceEndDate";
var FORWARD_UPDATE_SERVICE_STATUS = "forward_updateServiceStatus";
var FORWARD_CANCEL_AND_DRAFT = "forward_cancelAndBackToDraft";
var FORWARD_REFRESH = "forward_refresh";
var B_CPM_S02V_SCREEN = "B_CPM_S02v";

var msg = new messageBox("");

function initLoad() {
	//init tab
	initPage();
	
	initPageLinks('.tablePageLink', FORWARD_PAGELINK);
	
	//setting transaction type
	inactiveRadio("transactionType");
	
	//inactiveRadio("billAccAll");
	
	//setting service
	var services = $(".service_object");
	var disBillingOption = $("#disBillingOption").val();
	for ( var i = 0; i < services.length; i++) {
		var service = services.eq(i);
		
		//setting contract term
		inactiveRadio("contactTerm", service);
		
		//setting pro rate base
		inactiveRadio("proRateBase", service);
		
		//setting is lump sum
		inactiveRadio("isLumpSum", service);
		
		//setting is isDiscountLumpSum
		inactiveRadio("isDiscountLumpSum", service);
		
		// #189 Start
		var billingStatus = service.find(".billingStatus").val(); 
		if (disBillingOption == "1") {
			service.find("#trBillingOption").attr("style", "display:inline");
			if (billingStatus == "BS0" || billingStatus == "BS1") {
				service.find(".billingOption").attr('disabled',false);
			}else {
				service.find(".billingOption").attr('disabled',true);
			}
		}else {
			service.find("#trBillingOption").attr("style", "display:none");
		}
		//setting is billingOption
		inactiveRadio("billingOption", service);
		// #189 End
		
		//setting is isDiscountOneTime
		inactiveRadio("isDiscountOneTime", service);
		
		//service.find(".billDesc").autoTextarea({ maxHeight: 1000 });
		//service.find(".billDesc").focus();
		
		//setting readonly for bill desc
		service.find(".billDesc").attr("readonly","readonly");
		
		//calculate total amount
		//var totalQuantity = 0;
		//var totalPrice = 0;
		var totalAmount = 0;
		var totaldiscount=0;
		
		//setting sub plan
		var subPlans = service.find(".subPlan");
		for ( var j = 0; j < subPlans.length; j++) {
			var subPlan = subPlans.eq(j);
			
			//subPlan.find(".itemDesc").autoTextarea({ maxHeight: 1000 });
			//subPlan.find(".itemDesc").focus();
			
			//setting readonly for item desc
			subPlan.find(".itemDesc").attr("readonly","readonly");
			
			var gstValue = subPlan.find(".applyGst").val();
			if (gstValue == "1") {
				subPlan.find(".gstYes").show();
				subPlan.find(".gstNo").hide();
			} else {
				subPlan.find(".gstYes").hide();
				subPlan.find(".gstNo").show();
			}
			//initialize toggle
			initToggle(subPlan.find(".spanToggleClick"), subPlan);
			showJQuery(subPlan);
			
			//add total amount
			var innerText = subPlan.find("div.subPlanAmount").text();
			totalAmount += parseFloat(innerText);
			
			//add total discount amount
			var innerTextdiscount = subPlan.find("div.subDiscount").text();
			totaldiscount += parseFloat(Math.abs(innerTextdiscount));
			subPlan.find("div.subDiscount").text("-"+numberFormat(Math.abs(innerTextdiscount), 2, ","));
			
			//format for sub plan
			subPlan.find("div.subPlanAmount").text(numberFormat(innerText, 2, ","));
			innerText = subPlan.find("div.subPlanUnitPrice").text();
			//totalPrice = totalPrice + parseFloat(innerText);
			subPlan.find("div.subPlanUnitPrice").text(numberFormat(innerText, 2, ","));
			innerText = subPlan.find("div.subPlanQuantity").text();
			//totalQuantity = totalQuantity + parseFloat(innerText);
			subPlan.find("div.subPlanQuantity").text(numberFormat(innerText, 0, ","));
			//Set LineDesc Value
			setLineDescValue(subPlan);
		}
		//var displayTotalQuantity = numberFormat(totalQuantity, 0, ",");
		//var displayTotalPrice = numberFormat(totalPrice, 2, ",");
		var displayTotalAmount = numberFormat(totalAmount, 2, ",");
		var displayTotalDiscount=numberFormat(totaldiscount, 2, ",");
		
		//update total amount of service
		//var quantity = service.find("td.serviceQuantity");
		var unitPrice = service.find("td.serviceUnitPrice");
		var serviceAmount = service.find("td.serviceAmount");
		var servicediscountAmount = service.find("td.serviceDiscountDetail");
		
		//quantity.text(displayTotalQuantity);
		unitPrice.text(displayTotalAmount);
		serviceAmount.text(displayTotalAmount);
		servicediscountAmount.text("-"+displayTotalDiscount);
	}
        continueSuspend();
        
        var transType = $(".transactionType");
		for ( var i = 0; i < transType.length; i++) {
			transType.eq(i).attr("disabled", true);
	 	}
		
		$("#clickDifCurrencyYesFlg").val("");
		//check plan status to disable control
		var custPlanMPlanCurDifFlg = $("#custPlanMPlanCurDifFlg").val();
		if(custPlanMPlanCurDifFlg!=null && custPlanMPlanCurDifFlg!="") {
			var isConfirm = (msg.POPCFM($("#message_group").find(".messageCurrencyDifferent").text()) == msg.YES_CONFIRM);
			if (isConfirm) {
				$("#clickDifCurrencyYesFlg").val("1");
				$("#custPlanMPlanCurDifFlg").val("");
				$("#activeTab").val("activeplan");
				$("#action").val("approve");
				submitForm(FORWARD_APPROVE);
			}
		}
}

//set LineDesc value
function setLineDescValue(subPlan){
	//get svcLevel2 Value
	var svcIdService2=subPlan.find(".svcLevel2").val();
	var svcLevel2Value=getlineDesc(svcIdService2,"svcLevel2");
	//get svcLevel3 Value
	subPlan.find(".clonePlanDetail").each(function(index){
		var svcIdService3=$(this).find(".svcLevel3").val();
		var svcLevel3Value=getlineDesc(svcIdService3,"svcLevel3");
		var lineDesc=$(this).find("#lineDescVal");
		lineDesc.text(svcLevel2Value+"-"+svcLevel3Value);
	});
}

//get lineDesc value
function getlineDesc(svcLevelValue,classObject){
	var svcLevelVal="";
	var cloneObj =  $("#svcLevelGroup").find("." + classObject).clone();
	var optionList = cloneObj.find("option");
	for ( var i = 0; i < optionList.length; i++) {
		var option = optionList.eq(i);
		if (option.val() != "" && option.val() == svcLevelValue) {
			svcLevelVal=option.attr("descAbbr");
		}
	}
	return svcLevelVal;
}

function initPage(){
	var search =new ddtabcontent("planTabs");
	search.setpersist(true);
	search.setselectedClassTarget("link"); //"link" or "linkparent"
	search.init();
	var activeTab = $('#activeTab');
	var tabref = $('#planTabs').find('a[name="aTab"][rel="' + activeTab.val() + '"]');
	if(tabref.length > 0 ){
		search.expandtab(tabref[0]);
	}
	
	//disable tab
	var tabList = $("#planTabs").find("a[name='aTab']");
	for ( var i = 0; i < tabList.length; i++) {
		var tab = tabList.eq(i);
		if (tab.closest("li").find(".totalCount").val() == 0) {
			tab.attr("class", "disabledtab");
			tab.css("background", "#f4f3ef");
		}
	}
}

function initToggle(toggleClick, container) {
	toggleClick.click(function(){
		toggleJQuery(container);
	});
}

/**
 * 
 * @return
 */
function generateBIF() {
	//$("#action").val("generateBIF");
	//$("#idScreen").val("B_CPM_S05");
	//submitForm(FORWARD_GENERATE_BIF);
	var customerId = $("#customerId").val();
	window.location = $("#hiddenContextPath").val() 
	                  + "/B_CPM/B_CPM_S04InitBL.do?inputSearchPlan.customerId=" + customerId
	                  + "&idScreen="+B_CPM_S02V_SCREEN;
}

/**
 * 
 * @return
 */
function approvePlan() {
	if (msg.POPCFM($("#message_group").find(".messageApprove").html()) == msg.YES_CONFIRM) {
		$("#activeTab").val("activeplan");
		$("#action").val("approve");
		submitForm(FORWARD_APPROVE);
	}
}

/**
 * 
 * @return
 */
function editCustomerPlan() {
	submitForm(FORWARD_EDIT_PLAN);
}

/**
 * 
 * @param obj
 * @return
 */
function changeTab(obj) {
	var clickTab = $(obj).find(".idTab");
	if ($(obj).find(".totalCount").val() > 0) {
		$("#activeTab").val(clickTab.val());
		submitForm(FORWARD_PAGELINK);
	}
	
	//disable tab
	var tabList = $("#planTabs").find("a[name='aTab']");
	for ( var i = 0; i < tabList.length; i++) {
		var tab = tabList.eq(i);
		if (tab.find(".totalCount").val() == 0) {
			tab.attr("class", "disabledtab");
		}
	}
	
	return false;
}

function deleteCustomerPlan() {
	if (msg.POPCFM($("#message_group").find(".messageDeletePlan").html()) == msg.YES_CONFIRM) {
		submitForm(FORWARD_DELETE_CUSTOMER_PLAN);
	}
}

function exit() {
	if ($("#screen").val() == 2 && window.opener == undefined) {
		submitForm(FORWARD_EXIT);
	} else {
		window.close();
	}
}
function continueSuspend() {
    if($('input[name="accessSubmit"]').val()=="suspend"){
        var obj = $("#btnSuspend");
        if (msg.POPCFM($("#message_group").find(".messagecontinueSuspend").html()) == msg.YES_CONFIRM) {
            var service = $(obj).closest("div.service_object");
            $("#action").val("suspend");
            $("#idService").val(service.find(".idCustPlanGrp").val());
            submitForm(FORWARD_UPDATE_SERVICE_STATUS);
        }else{
        $('input[name="accessSubmit"]').val("");
        }
    }
    else if($('input[name="accessSubmit"]').val()=="unsuspend"){
        var obj = $("#btnUnsuspend");
        if (msg.POPCFM($("#message_group").find(".messagecontinueSuspend").html()) == msg.YES_CONFIRM) {
            var service = $(obj).closest("div.service_object");
            $("#action").val("unsuspend");
            $("#idService").val(service.find(".idCustPlanGrp").val());
            submitForm(FORWARD_UPDATE_SERVICE_STATUS);
        }else{
            $('input[name="accessSubmit"]').val("");
        }
    }
}

/**
 * 
 * @param obj
 * @return
 */
function Suspend(obj) {
	//show confirm message
	if (msg.POPCFM($("#message_group").find(".messageSuspend").html()) == msg.YES_CONFIRM) {
		var service = $(obj).closest("div.service_object");
		$("#action").val("suspend");
		$("#idService").val(service.find(".idCustPlanGrp").val());
		submitForm(FORWARD_UPDATE_SERVICE_STATUS);
	}
}

/**
 * 
 * @param obj
 * @return
 */
function Unsuspend(obj) {
	 if (msg.POPCFM($("#message_group").find(".messageUnsuspend").html()) == msg.YES_CONFIRM) {
		var service = $(obj).closest("div.service_object");
		$("#action").val("unsuspend");
		$("#idService").val(service.find(".idCustPlanGrp").val());
		submitForm(FORWARD_UPDATE_SERVICE_STATUS);
	}
}

function backToDraft() {
	if (msg.POPCFM($("#message_group").find(".messageDraft").html()) == msg.YES_CONFIRM) {
		$("#action").val("draft");
		submitForm(FORWARD_CANCEL_AND_DRAFT);
	}
}

function cancel(){
	if (msg.POPCFM($("#message_group").find(".messageCancel").html()) == msg.YES_CONFIRM) {
		$("#action").val("cancel");
		submitForm(FORWARD_CANCEL_AND_DRAFT);
	}
}

function Terminate(obj) {
	var service = $(obj).closest(".service_object");
	var chkMinimumService = service.find("#chkMinimumService").attr("checked");
	var minimumServiceTo = service.find("#minimumServiceTo").val();
	var minSvcPeriod = "0";
	if (chkMinimumService==true) {
		minSvcPeriod = "1";
	} else {
		minimumServiceTo = "";
	}
	
	var queryString = "&event=" + FORWARD_TERMINATE + 
	"&idCustPlan=" + $("#idCustPlan").val() +
	"&svc_start=" + service.find(".serviceDateStart").val() +
	"&idService=" + service.find(".idCustPlanGrp").val()+
	"&minSvcPeriod=" + minSvcPeriod +
	"&minimumServiceTo=" + minimumServiceTo;
	var url = getUrl(queryString);
	url = url.replace("B_CPM/B_CPM_S02ViewDSP", "COMMON/POPUP_COMMON_DSP");
	var result = msg.POPTER(url);
	if(result == msg.YES_CONFIRM) {
		//reset active tab
		$("#activeTab").val("terminatedplan");
		$("#action").val("terminate");
		//refresh new status
		submitForm(FORWARD_REFRESH);
	}
}

function svcEndDateClick(obj) {
	var service = $(obj).closest(".service_object");
	
	var queryString = "&event=" + FORWARD_SERVICE_END_DATE + 
	"&serviceNo=" + service.find("#serviceNo").val() +
	"&idCustPlanGrp=" + service.find(".idCustPlanGrp").val();
	var url = getUrl(queryString);
	url = url.replace("B_CPM/B_CPM_S02ViewDSP", "COMMON/POPUP_COMMON_DSP");
	var result = msg.POPTERServiceEndDate(url);
	if(result == msg.YES_CONFIRM) {
		//reset active tab
		$("#activeTab").val("terminatedplan");
		$("#action").val("terminate");
		//refresh new status
		submitForm(FORWARD_REFRESH);
	}
	
	//window.open(url,'name',POPUP_FEATURE_STD);
}

function viewSubscriptionInfo() {
	var queryString = "&event=" + FORWARD_VIEW_SUBSCRIPTION_INFO + 
						"&isPopUp=1" +					
						"&customerPlanID=" + $("#idCustPlan").val() + 
						"&subscriptionID=" + $("#idSubInfo").val() +
						"&customerID=" + $("#idCust").val();
	var url = getUrl(queryString);
	var newWindow = window.open(url,'name',POPUP_FEATURE_STD);
	newWindow.focus();
}

function viewReference(idRef) {
	var queryString = "&event=" + FORWARD_PAGELINK + 				
						"&customerPlan.idCustPlan=" + idRef;
	var url = getUrl(queryString);
	var newWindow = window.open(url,'name',POPUP_FEATURE_STD);
	newWindow.focus();
}

function popupBankInfo(url) {
	var url = url+"/M_CST/M_CSTR10BLogic.do?mode=READONLY&id_cust=" + $("#idCust").val();
	var width = window.screen.width*90/100;
    var height = window.screen.height*80/100;
    var left = Number((screen.availWidth/2) - (width/2));
    var top = Number((screen.availHeight/2) - (height/2));
    var offsetFeatures = "width=" + width + ",height=" + height +
    					 ",left=" + left + ",top=" + top +
    					 "screenX=" + left + ",screenY=" + top;
	var strFeatures= "toolbar=no, status=no, menubar=no,location=no,scrollbars=no, resizable=yes" + "," + offsetFeatures;	 	
	var newwindow = window.open(url, "name", strFeatures);	
	if (window.focus) { newwindow.focus(); }
}

function popupB_CPM_S07(url) {
	var width = window.screen.width*80/100;
    var height = window.screen.height*70/100;
    var left = Number((screen.availWidth/2) - (width/2));
    var top = Number((screen.availHeight/2) - (height/2));
    var offsetFeatures = "width=" + width + ",height=" + height +
    					 ",left=" + left + ",top=" + top +
    					 "screenX=" + left + ",screenY=" + top;
	var strFeatures= "toolbar=no, status=no, menubar=no,location=no,scrollbars=yes, resizable=yes" + "," + offsetFeatures;	 	
	var newwindow = window.open(url, "name", strFeatures);	
	if (window.focus) { newwindow.focus(); }
}

function reloadPage(url) {
	window.location = url;
}