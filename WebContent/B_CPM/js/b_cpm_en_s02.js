var FORWARD_EXIT = "forward_exit";
var FORWARD_SAVEPLAN = "forward_save";
var FORWARD_ADD_POPUP = "forward_chooseStandardPlan";
var FORWARD_ADD_POPUP_M_MPP05 = "forward_chooseStandardPlanM_MPP05";
var FORWARD_LOAD_NEW_SUB_PLAN = "forward_loadNewSubPlan";
var FORWARD_LOAD_EXIST_SUB_PLAN = "forward_loadExistSubPlan";
var FORWARD_EDIT_SUBSCRIPTION_INFO = "forward_editSubscriptionInfo";
var FORWARD_GET_PLAN_INFO = "forward_getPlanInfo";

var msg = new messageBox("");

var STANDARD_PLAN_TYPE="SP";
var NON_STANDARD_PLAN_TYPE="NP";
var EXPORT_CUR_ON_LOAD_PAGE="0";
var EXPORT_CUR_NOT_ON_LOAD_PAGE="1";
var EXPORT_CUR_ADD_SUB_PLAN="2";
var globalInput=null;
var globalSel=null;


function initLoad() {
	//set tab link
	var search =new ddtabcontent("planTabs");
	search.setpersist(true);
	search.setselectedClassTarget("link"); //"link" or "linkparent"
	search.init();
	
	//setting reference plan
	changeTransactionType();
	
	//setting value for bill account
	$("#plan_billAccNo").val($("#originalBillAccNo").val());
	
	//setting minimum service period
	changeBillServiceId();
	changeCustomerPlanLevel1();
	var clickSaveFlg = $("#clickSaveFlg").val();

	if($("#action").val() == "new"){
		var newAccCheckFlg = $("#newAccCheckFlg").val();
		if ("1"==newAccCheckFlg) {
			if(clickSaveFlg!=null && clickSaveFlg != "") {
				if(!document.getElementById("plan_newAcc").checked) {
					//checkNewAccount($("#plan_newAcc")[0]);
					$("#plan_billingCurrency").attr("disabled", true);
					$("#paymentMethod").attr("disabled", true);
					$("#expGrandTotal").attr("disabled", true);
					$("#fixedForex").attr("disabled", true);
					$("#taxType").attr("disabled", true);
					//setting grand total
					displayOptionGrandTotal(EXPORT_CUR_ON_LOAD_PAGE);
				} else {
					$("#plan_billAccNo").attr("disabled", true);
					displayOptionGrandTotal(EXPORT_CUR_ON_LOAD_PAGE);
				}
			} else {
				initCheckBillAcc();
			}
		} else {
			//setting newAcc
			checkNewAccount($("#plan_newAcc")[0]);
			//setting grand total
			// #169 Start
			//displayOptionGrandTotal(EXPORT_CUR_ON_LOAD_PAGE);
			initDisplayOptionGrandTotal();
			// #169 End
		}
	} else {
		//setting newAcc
		checkNewAccount($("#plan_newAcc")[0]);
		//setting grand total
		displayOptionGrandTotal(EXPORT_CUR_ON_LOAD_PAGE);
	}
	//add one service in new case
	if (countService() <= 0) {
		newInitAddService();
	}
	
	//check plan status to disable control
	var planType = $("#planType").val();
	if ($(".planStatus").val() == "PS2" || $(".planStatus").val() == "PS3" || $(".planStatus").val() == "PS7") {
		//planType,SP:STANDARD_PLAN,NP:NON_STANDARD_PLAN
		if (NON_STANDARD_PLAN_TYPE==planType) {
			inactiveRadio("transactionType");
			inactiveRadio("billAccAll");
			
			$("#svcLevel1").attr("disabled", true);
			$("#svcLevel2").attr("disabled", true);
		}
		
		$("#plan_billingCurrency").attr("disabled", true);
		$("#taxType").attr("disabled", true);							   
		$("#paymentMethod").attr("disabled", true);
		$("#expGrandTotal").attr("disabled", true);
		var fixedForexFlg = $("#fixedForexFlg").val();
		if (fixedForexFlg=="1") {
			$(".fixedForex").attr("disabled", true);
		}
	}
	
	//if (STANDARD_PLAN_TYPE==planType) {
		//transactionTypeFlg 0:not enabled, other value enabled
		var transactionTypeFlg = $("#transactionTypeFlg").val();
		if(transactionTypeFlg=="0") {
			var transType = $(".transactionType");
			setRadioBtnDisabled(transType,true);
		} else {
			var planStatus = $(".planStatus").val();
			if (planStatus=="PS2"||planStatus=="PS7"||planStatus=="PS8") {
				var transType = $(".transactionType");
				setRadioBtnDisabled(transType,true);
			} else {
				var transType = $(".transactionType");
				setRadioBtnDisabled(transType,false);
			}
		}
	//}
	
	//setting service
	var services = $(".service_object");
	var disBillingOption = $("#disBillingOption").val();
	for ( var i = 0; i < services.length; i++) {
		var service = services.eq(i);
		
		var serviceStatus = service.find(".serviceStatus").val(); 
		var billingStatus = service.find(".billingStatus").val();
		var editactiveplanamtFlg= service.find(".EditActivePlanAMTFlg").val();
		var billingOptions = service.find(".billingOption");
		// #189 Start
		if (disBillingOption == "1") {
			service.find("#trBillingOption").attr("style", "display:inline");
			if (billingStatus == "BS0" || billingStatus == "BS1") {
				var selectVal = $("#biSelect").val();
				if ('6' == selectVal) {
					billingOptions.eq(0).attr("checked","checked");
					billingOptions.eq(1).removeAttr("checked");
					billingOptions.eq(0).click();
					billingOptions.eq(1).attr('disabled', true);
				} else {
					service.find(".billingOption").attr('disabled', false);
				}
			}else {
				billingOptions.eq(0).attr('disabled', true);
				billingOptions.eq(1).attr('disabled', true);
			}
		}else {
			service.find("#trBillingOption").attr("style", "display:none");
		}
		// #189 End
		
		//setting service start
		changeAutoRenewal(service.find(".autoRenewal")[0]);
		
		//setting minimum period
		checkServicePeriod(service.find("input.minimumService")[0]);
		
		//setting proRateBase
		var proRateBase = service.find(".proRateBase");
		changeProrateBase(proRateBase[0]);
		
		// setting isLumpSum
		var isLumpSum=service.find(".isLumpSum");
		changItemmise(isLumpSum[0]);
		
		//setting readonly for total amount
		service.find(".subPlanAmount").attr("readonly","readonly");
		
		//Terminated calculate total amount
		//var totalQuantity = 0;
		//var totalPrice = 0;
		var totalAmount = 0;
		var discountAmout=0;
		
		//service.find(".billDesc").autoTextarea({ maxHeight: 1000 });
		//service.find(".billDesc").focus();
		
		//Terminated
		if((serviceStatus == 'PS3' && 'BS3' == billingStatus) || (serviceStatus == 'PS7' && 'BS3' == billingStatus) || serviceStatus == 'PS8'){
			inactiveRadioForObj(service.find(".contactTerm"),true);
			inactiveRadioForObj(service.find(".proRateBase"),true);
			inactiveRadioForObj(service.find(".isLumpSum"),true);
			inactiveRadioForObj(service.find(".isDiscountLumpSum"),true);
			inactiveRadioForObj(service.find(".billingOption"),true);
			inactiveRadioForObj(service.find(".isDiscountOneTime"),true);
			service.find(".billDesc").attr("readonly",true);
		}
		//CUSTPO
		if (serviceStatus == "PS2" ){
			service.find(".custPo").attr("disabled",true);
		}else{
			if (billingStatus == 'BS3'){
				service.find(".custPo").attr("disabled",true);
			}
		}
		//serviceStatus == 'PS3' then Set 'Transaction type ' is  'Not Editable'
		if(serviceStatus == 'PS3'){
			setRadioBtnDisabled(transType,true);
		}
		//setting sub plan
		var subPlans = service.find(".subPlan");
		for ( var j = 0; j < subPlans.length; j++) {
			var subPlan = subPlans.eq(j);
			
			var rateMode = subPlan.find(".rateMode").val();
			//calculate total amount for service
			updateAmount(subPlan.find(".subPlanUnitPrice")[0]);
			// calculate total discount for service
			updateDiscount(subPlan.find(".subDiscount").eq(0));
			
			var gstValue = subPlan.find(".applyGst").val();
			subPlan.find(".applyGstValue").val(gstValue);
			/*if (gstValue == "1") {
				subPlan.find(".gstYes").show();
				subPlan.find(".gstNo").hide();
			} else {
				subPlan.find(".gstYes").hide();
				subPlan.find(".gstNo").show();
			}*/
			
			//initialize toggle
			initToggle(subPlan.find(".spanToggleClick"), subPlan);
			showJQuery(subPlan);
			
			//subPlan.find(".itemDesc").autoTextarea({ maxHeight: 1000 });
			//subPlan.find(".itemDesc").focus();
			
			var type = subPlan.find(".svcLevel1").attr("type");
			if ((serviceStatus == 'PS1' || serviceStatus == 'PS2' || (serviceStatus == 'PS3' && 'BS3' != billingStatus) || (serviceStatus == 'PS7' && 'BS3' != billingStatus)) && type != "hidden") {
				//setting svcLevel1 svcLevel2 
				var svcLevel1_val = subPlan.find(".svcLevel1").val();
				//displayOption(subPlan.find(".svcLevel2"), svcLevel1_val, "svcLevel2");
				//setting sub plan detail
				var subPlanDetails = subPlan.find(".clonePlanDetail");
				for ( var k = 0; k < subPlanDetails.length; k++) {
					var subPlanDetail = subPlanDetails.eq(k);
					//displayOption(subPlanDetail.find(".svcLevel3"), svcLevel1_val, "svcLevel3");
					//displayOption(subPlanDetail.find(".svcLevel4"), svcLevel1_val, "svcLevel4");
					if(subPlanDetail.find(".svcLevel3").attr("disabled") == "disabled"){
					}else{
						if(svcLevel1_val==""||svcLevel1_val==undefined||svcLevel1_val==null){
						}else{
							subPlanDetail.find(".svcLevel3").find("option[value='add']").remove();
							subPlanDetail.find(".svcLevel3").append( "<option value=\""+ "add" +"\">"+"-----add one-----"+"</option>");
						}
						subPlanDetail.find(".svcLevel3").combobox();
					}
				}
			}	
			//Terminated
			if((serviceStatus == 'PS3' && 'BS3' == billingStatus) || (serviceStatus == 'PS7' && 'BS3' == billingStatus) || serviceStatus == 'PS8'){
				subPlan.find(".itemDesc").attr("readonly",true);
				//format for sub plan
				var innerText = subPlan.find("div.subPlanAmount").text();
				totalAmount += parseFloat(innerText);
				subPlan.find("div.subPlanAmount").text(numberFormat(innerText, 2, ","));
				innerText = subPlan.find("div.subPlanUnitPrice").text();
				//totalPrice = totalPrice + parseFloat(innerText);
				subPlan.find("div.subPlanUnitPrice").text(numberFormat(innerText, 2, ","));
				innerText = subPlan.find("div.subPlanQuantity").text();
				//totalQuantity = totalQuantity + parseFloat(innerText);
				subPlan.find("div.subPlanQuantity").text(numberFormat(innerText, 0, ","));
				// format for subplan discount
				var innerTextdiscount = subPlan.find("div.subDiscount").text();
				discountAmout+= parseFloat(Math.abs(innerTextdiscount));
				subPlan.find("div.subDiscount").text("-"+numberFormat(Math.abs(innerTextdiscount), 2, ","));
			}
			if((serviceStatus == 'PS1' || serviceStatus == 'PS2' || (serviceStatus == 'PS3' && 'BS3' != billingStatus) || (serviceStatus == 'PS7' && 'BS3' != billingStatus))) {
				//Rate Mode is Once display label
				if('BS2' == billingStatus && '6' == rateMode) {
					subPlan.find(".itemDesc").attr("readonly",true);
					//format for sub plan
					var innerText = subPlan.find("div.subPlanAmountDiv").text();
					subPlan.find("div.subPlanAmountDiv").text(numberFormat(innerText, 2, ","));
					innerText = subPlan.find("div.subPlanUnitPriceDiv").text();
					//totalPrice = totalPrice + parseFloat(innerText);
					subPlan.find("div.subPlanUnitPriceDiv").text(numberFormat(innerText, 2, ","));
					innerText = subPlan.find("div.subPlanQuantityDiv").text();
					//totalQuantity = totalQuantity + parseFloat(innerText);
					subPlan.find("div.subPlanQuantityDiv").text(numberFormat(innerText, 0, ","));
					// format for subplan discount subDiscount
					var innerTextdiscount = subPlan.find("div.subDiscount").text();
					subPlan.find("div.subDiscount").text("-"+numberFormat(Math.abs(innerTextdiscount), 2, ","));
					inactiveRadioForObj(service.find(".isDiscountOneTime"),true);
				}
				var svcLevel1_val = subPlan.find(".svcLevel1").val();
				//init combobox
				var subPlanDetails = subPlan.find(".clonePlanDetail");
				for ( var k = 0; k < subPlanDetails.length; k++) {
					var subPlanDetail = subPlanDetails.eq(k);
						if(svcLevel1_val==""||svcLevel1_val==undefined||svcLevel1_val==null){
						}else{
							subPlanDetail.find(".svcLevel3").find("option[value='add']").remove();
							subPlanDetail.find(".svcLevel3").append( "<option value=\""+ "add" +"\">"+"-----add one-----"+"</option>");
						}
						subPlanDetail.find(".svcLevel3").combobox();
				}
			}
			/*//CUSTPO
			if (serviceStatus == "PS2" ){
				subPlan.find(".custPo").attr("readonly",true);
			}else{
				if (billingStatus == 'BS3'){
					subPlan.find(".custPo").attr("readonly",true);
				}
			}*/
			//In-approval
			if (serviceStatus=="PS2") {
				subPlan.find(".subPlanQuantity").attr("readonly",true);
				subPlan.find(".subPlanUnitPrice").attr("readonly",true);
				subPlan.find(".subDiscount").attr("readonly",true);
			}
			else{
				//Active
				if(serviceStatus=="PS3"){
					if(editactiveplanamtFlg=="1"){
					}else{
						subPlan.find(".subPlanQuantity").attr("readonly",true);
						subPlan.find(".subPlanUnitPrice").attr("readonly",true);
						subPlan.find(".subDiscount").attr("readonly",true);
					}
					//T_CUST_PLAN_D.BILLING_STATUS = B2 OR B3,isDiscountOneTime is disabled
					if('BS2'== billingStatus||'BS3'== billingStatus){
						inactiveRadioForObj(service.find(".isDiscountOneTime"),true);
					}
				}
			}
			//Set LineDesc Value
			setLineDescValue(subPlan);
			//Set JobNo combobox
			var jobNoSel=subPlan.find("select.jobNo");
			if(jobNoSel.attr("disabled") == "disabled"){
				//jobNoSel.append( "<option value=\""+ "add" +"\">"+"-----add one-----"+"</option>");
				jobNoSel.combobox();
				subPlan.find(".subPlanContent tr").eq(0).children().eq(3).css("padding-right",27+"px");
				subPlan.find(".subPlanContent tr").eq(0).children().eq(3).find(".custom-combobox-input").attr("disabled", true);
				subPlan.find(".subPlanContent tr").eq(0).children().eq(3).find(".ui-button").attr("disabled", true);
			}else{
				/*jobNoSel.append( "<option value=\""+ "add" +"\">"+"-----add one-----"+"</option>");*/
				jobNoSel.combobox();
				subPlan.find(".subPlanContent tr").eq(0).children().eq(3).css("padding-right",27+"px");
			}
		}

		//Terminated
		if((serviceStatus == 'PS3' && 'BS3' == billingStatus) || (serviceStatus == 'PS7' && 'BS3' == billingStatus) || serviceStatus == 'PS8'){
			//var displayTotalQuantity = numberFormat(totalQuantity, 0, ",");
			//var displayTotalPrice = numberFormat(totalPrice, 2, ",");
			var displayTotalAmount = numberFormat(totalAmount, 2, ",");
			var displayDiscountAmount = numberFormat(Math.abs(discountAmout), 2, ",");
			
			//update total amount of service
			//var quantity = service.find("td.serviceQuantity");
			var unitPrice = service.find("td.serviceUnitPrice");
			var serviceAmount = service.find("td.serviceAmount");
			var servicediscount= service.find("td.serviceDiscountDetail");
			//quantity.text(displayTotalQuantity);
			unitPrice.text(displayTotalAmount);
			serviceAmount.text(displayTotalAmount);
			servicediscount.text("-"+displayDiscountAmount);
		}
	}
	

	$("#clickDifCurrencyYesFlg").val("");
	//check plan status to disable control
	var custPlanMPlanCurDifFlg = $("#custPlanMPlanCurDifFlg").val();
	if(custPlanMPlanCurDifFlg!=null && custPlanMPlanCurDifFlg!="") {
		var isConfirm = (msg.POPCFM($('#messageCurrencyDifferent').val()) == msg.YES_CONFIRM);
		if (isConfirm) {
			$("#clickDifCurrencyYesFlg").val("1");
			//save data
			saveCustomerPlan();
		} 
		$("#custPlanMPlanCurDifFlg").val("");
	}

	$("#clickAddRadiusYesFlg").val("");
	//add radius service
	var addRadiusFlg = $("#addRadiusFlg").val();
	if(addRadiusFlg!=null && addRadiusFlg!="") {
		var isConfirm = (msg.POPCPM($("#message_group").find(".ERR4SC019_2").text(), "Add", "Cancel") == msg.YES_CONFIRM);
		if (isConfirm) {
			$("#clickDifCurrencyYesFlg").val("1");
			$("#clickAddRadiusYesFlg").val("1");
			//save data
			saveCustomerPlan();
		}
		$("#addRadiusFlg").val("");
	}
	
	$("#clickRemoveRadiusYesFlg").val("");
	//remove
	var removeRadiusFlg = $("#removeRadiusFlg").val();
	if(removeRadiusFlg!=null && removeRadiusFlg!="") {
		var isConfirm = (msg.POPCPM($("#message_group").find(".ERR4SC019_1").text(), "Remove", "Cancel") == msg.YES_CONFIRM);
		if (isConfirm) {
			$("#clickDifCurrencyYesFlg").val("1");
			$("#clickRemoveRadiusYesFlg").val("1");
			//save data
			saveCustomerPlan();
		}
		$("#removeRadiusFlg").val("");
	}
	
	var modelFlg = $("#action").val();
	if (modelFlg=="new") {
		//non standard plan
		if(NON_STANDARD_PLAN_TYPE==planType) {
			var categoryApplyAllCboHid = $("#categoryApplyAllCboHid").val();
			var serviceApplyAllCboHid = $("#serviceApplyAllCboHid").val();
			$("#categoryApplyAllCbo").val(categoryApplyAllCboHid);
			//set GST
			GSTApplyAllEvt("");
			//set Category / Service 
			categoryApplyAllCboEvt(document.getElementById("categoryApplyAllCbo"), "2", serviceApplyAllCboHid, "");
		}
		//Only New Mode,then Set JobNo combobox
		var services = $("div.service_object");
		for ( var i = 0; i < services.length; i++) {
			if(services.eq(i).css("display")=="none"){
			}
			else{
				var service = services.eq(i);
				//service.find(".jobNoAllJob").append( "<option value=\""+ "add" +"\">"+"-----add one-----"+"</option>");
				service.find(".jobNoAllJob").combobox();
			}
		}
	}
	
	if(clickSaveFlg == null || clickSaveFlg == "") {
		$("#GSTApplyAllChk").attr("checked",true);
	}
		//$("#categoryApplyAllChk").attr("checked",true);
		GSTApplyAllEvt('GSTApplyAllCboChange');
		//categoryApplyAllEvt('categoryApplyAllChkClick','categoryApplyAllChkClick');
	//Init JobNoAll	
	if(clickSaveFlg == null || clickSaveFlg == "") {
		$(".jobNoAllChk").attr("checked",true);
		if($("#action").val()=="new"){
			//set billDesc
			var billDesc= $(".billDesc");
			var jobNoAllJobVal=service.find('.jobNoAllJob option:selected').text();
			var multiPln = $("#multiPln").val();
			var planNameParam = $("#planNameParam").val();
			if(multiPln == "1"){
				billDesc.text("Job No.: "+jobNoAllJobVal);
			}else{
				billDesc.val(planNameParam);
			}
		}
	}
	//Init JobNo
	JobNoAllChkInit();
	
	//Init Master/Sub Location Checkbox
	initCheckMasterLocation();
	initCheckSubLocation();
}

function initCheckMasterLocation(){
	var services = $("div.service_object");
	for ( var i = 0; i < services.length; i++) {
		var service = services.eq(i);
		service.find('.masterLocationFlg').each(function(){
			if($(this).val() == 1 ? $(this).attr('checked', true) : $(this).attr('checked', false));				
			checkMasterLocation(this);
		});
	}
}

function initCheckSubLocation(){
	var services = $("div.service_object");
	for ( var i = 0; i < services.length; i++) {
		var service = services.eq(i);
		service.find('.masterLocationFlg').each(function(){
			if($(this).val() == '1') (service.find('.masterLocation').trigger('change'));			
		});
	}
}

// #200, #201 wcbeh@20160923 - Master Location
function checkMasterLocation(obj){
	var services = $(obj).closest("div.service_object");
	if(obj.checked){
		services.find('.masterLocation').attr('disabled',false);
		services.find('.subLocation').each(function(){
			$(this).attr('disabled',true);
		});
	}
	else{
		services.find('.masterLocation').attr('disabled', true);
		services.find('.subLocation').each(function(){
			$(this).attr('disabled',false);
		});
	}
}

//#200, #201 wcbeh@20160923 - Master Location
function changeLocation(obj){
	var services = $(obj).closest("div.service_object");
	var idx = $('option:selected',obj).index();
	services.find('.subLocation').each(function(){
		$('option', this).eq(idx).prop('selected', true);
	});
}

function giveUpFocus(){
	$(".itemDesc").blur();
	$(".billDesc").blur();
}

function initCheckBillAcc() {
	$("#plan_newAcc").attr("checked", true);
	// Enable these ....
	$("#plan_billAccNo").attr("disabled", true);
	
	var fixedForexFlg = $("#fixedForexFlg").val();
	if (fixedForexFlg=="1") {
		$("#fixedForex").val("");
		$("#fixedForex").attr("disabled", false);
	}
	
	$("#expGrandTotal").val("");
	$("#expGrandTotal").attr("disabled", false);
	
	$('#paymentMethod option:eq(1)').attr('selected','selected');
	$("#paymentMethod").attr("disabled", false);
	
	var defCurrency = $("#defCurrency").val();
	$("#plan_billingCurrency").val(defCurrency);
	
	$("#plan_billingCurrency").attr("disabled", false);
	$("#taxType").attr("disabled", false);								   
	displayOptionGrandTotal(EXPORT_CUR_NOT_ON_LOAD_PAGE);
}

function setRadioBtnDisabled(controlObj,isDisabledFlg){
	for ( var i = 0; i < controlObj.length; i++) {
	    // DoNOT disable checked radio button, otherwise, value will not post to server.
	    if(!controlObj.eq(i).is(":checked")) {
	        controlObj.eq(i).attr("disabled", isDisabledFlg);
	    }
 	}
}

function initToggle(toggleClick, container) {
	toggleClick.click(function(){
		toggleJQuery(container);
	});
}

function popM_MPP04AndAddService(){
	var customerType = $("#customerType").val();
	var queryString = "&event=" + FORWARD_GET_PLAN_INFO+"&lblCustomerType=" + customerType;
	var url = getUrl(queryString);
	var popupWidth = window.screen.width*80/100;
	var popupHeight = window.screen.height*80/100;
	//window.opener.setPlanInfo(idPlan,planName);
	//M_PPM_S04 page
	var newWindow = window.open(url,'name', POPUP_FEATURE.replace("#width#",popupWidth).replace("#height#",popupHeight));
}

//M_PPM_S04 page selected one record and closed window
function setPlanInfo(idPlan,planName){
	//add service
	var service = $("#first_service").clone();
	service.attr("id", "");
	service.css('display','');
	service.find(".serviceIdPlan").val(idPlan);
	service.find(".serviceMultiPln").val("0");
	service.find(".billDesc").val(planName);
	
	/*service.find(".billDesc").autoTextarea({ maxHeight: 1000 });
	service.find(".billDesc").autoTextarea({ minHeight: 34 });
	service.find(".billDesc").focus();*/
	$("#service_group").append(service);
	reindexServiceDisplay();
	var modelFlg = $("#action").val();
	if (modelFlg=="new") {
		//Only New Mode,then Set JobNo combobox
		service.find(".jobNoAllChk").attr("checked",true);
		//service.find(".jobNoAllJob").append( "<option value=\""+ "add" +"\">"+"-----add one-----"+"</option>");
		service.find(".jobNoAllJob").combobox();
	}
	
	displayPostpaid("#biSelect");
}

function addServiceMul(){
	//add service
	var service = $("#first_service").clone();
	service.attr("id", "");
	service.css('display','');
	service.find(".serviceIdPlan").val("");
	service.find(".serviceMultiPln").val("1");
	
	/*service.find(".billDesc").autoTextarea({ maxHeight: 1000 });
	service.find(".billDesc").autoTextarea({ minHeight: 34 });
	service.find(".billDesc").focus();*/
	
	$("#service_group").append(service);
	reindexServiceDisplay();
	var modelFlg = $("#action").val();
	if (modelFlg=="new") {
		//Only New Mode,then Set JobNo combobox
		service.find(".jobNoAllChk").attr("checked",true);
		//service.find(".jobNoAllJob").append( "<option value=\""+ "add" +"\">"+"-----add one-----"+"</option>");
		service.find(".jobNoAllJob").combobox();
	}
}

function newInitAddService(){
	var service = $("#first_service").clone();
	service.attr("id", "");
	service.css('display','');
	var multiPln = $("#multiPln").val();
	service.find(".serviceIdPlan").val($("#idPlanParam").val());
	service.find(".serviceMultiPln").val(multiPln);
	if ("0"==multiPln) {
		service.find(".billDesc").val($("#planNameParam").val());
	}
	
	$("#service_group").append(service);
	reindexServiceDisplay();
}
function addService(isConfirm) {
	var service = $("#first_service").clone();
	service.attr("id", "");
	service.css('display','');
	$("#service_group").append(service);
	reindexServiceDisplay();
}

function reindexServiceDisplay() {
	var services = $("#service_group").find(".service_object");
	//check plan status to disable control
	var planType = $("#planType").val();
	var disBillingOption = $("#disBillingOption").val();
	for ( var i = 0; i < services.length; i++) {
		var service = services.eq(i);
		if (STANDARD_PLAN_TYPE==planType) {
			service.find("#seqServiceName").eq(0).text("Service " + (i));
		} else {
			//service.find("legend").eq(0).text("Service " + (i));
			service.find("#seqServiceName").eq(0).text("Service " + (i));
		}
		service.find('input[type="radio"][name$="contactTerm"]').attr("name","services[" + i + "].contactTerm");
		service.find('input[type="radio"][name$="proRateBase"]').attr("name","services[" + i + "].proRateBase");
		service.find('input[type="radio"][name$="isLumpSum"]').attr("name","services[" + i + "].isLumpSum");
		service.find('input[type="radio"][name$="isDiscountLumpSum"]').attr("name","services[" + i + "].isDiscountLumpSum");
	}
}

function reindexPlan(service) {
		// recalc serviceplan index
	    service.find("div.subPlan").each(function(index){
	    	$(this).find("#planNo").text(index+1);
	    });
		// reindex plan radio
		var serviceIndex=service.find("#seqServiceName").eq(0).text().replace("Service ","")-1;
		
		service.find("div.subPlan").each(function(k){
			$(this).find('input[type="radio"][name$="isDiscountOneTime"]').attr("name","services[" + serviceIndex + "].subPlans["+k+"].isDiscountOneTime");
		});
			
}

function numberOfService() {
	return $("#service_group").find(".service_object").length - 1;
}

function numberOfSubPlan(service) {
	return service.find(".subPlan").length;
}

function addNewSubPlan(obj) {
	var subPlan = $("#clone_subPlan").clone();
	//add itemType
	subPlan.attr("id", "");
	subPlan.find("input[type='hidden'][name$='itemType']").val("S");
	subPlan.css('display','');
	subPlan.find("div.optionServiceLabel").css('display','none');
	subPlan.find("div.subPlanLabel").css('display','');
	subPlan.find("div.optionServiceLabel").find("#planNo").removeAttr("id");
	var applyGst = subPlan.find(".applyGst");
	applyGst.val('1');
	subPlan.find(".applyGstValue").val(gstValue);
	var rateType2=subPlan.find(".rateType2");
	rateType2.val('A20');
	
	/*subPlan.find(".itemDesc").autoTextarea({ maxHeight: 1000 });
	subPlan.find(".itemDesc").autoTextarea({ minHeight: 34 });
	subPlan.find(".itemDesc").focus();*/
	var service = $(obj).closest("div.service_object");
	var subplangroup = service.find("td.subplan");
	initToggle(subPlan.find(".spanToggleClick"), subPlan);
	showJQuery(subPlan);
	subplangroup.append(subPlan);
	reindexPlan(service);
	//Set JobNo combobox
	//subPlan.find(".jobNo").append( "<option value=\""+ "add" +"\">"+"-----add one-----"+"</option>");
	subPlan.find(".jobNo").combobox();
	subPlan.find(".subPlanContent tr").eq(0).children().eq(3).css("padding-right",27+"px");
	var modelFlg = $("#action").val();
	if(modelFlg=="new") {
		setGSTApplyAll(subPlan);
		setCategoryApplyAll(subPlan, "");
		//Init JobNo when add a new subplan
		setJobApplyAll(subPlan);
	}
	
	var billCur = $("#plan_billingCurrency").val();
	changeRateCur(billCur);
}

function addNewOptionService(obj) {
	var button = $(obj);
	var service = button.closest("div.service_object");
	var subPlan = $("#clone_subPlan").clone();
	//add itemType
	subPlan.attr("id", "");
	subPlan.find("input[type='hidden'][name$='itemType']").val("O");
	subPlan.css('display','');
	subPlan.find("div.optionServiceLabel").css('display','');
	subPlan.find("div.subPlanLabel").css('display','none');
	subPlan.find("div.subPlanLabel").find("#planNo").removeAttr("id");
	var applyGst = subPlan.find(".applyGst");
	applyGst.val('1');
	subPlan.find(".applyGstValue").val(gstValue);
	var rateType2=subPlan.find(".rateType2");
	rateType2.val('A20');
	/*subPlan.find(".itemDesc").autoTextarea({ maxHeight: 1000 });
	subPlan.find(".itemDesc").autoTextarea({ minHeight: 34 });
	subPlan.find(".itemDesc").focus();*/
	
	var optionservicegroup = service.find("td.option_service");
	//remove add button 
	subPlan.find("span.addSubPlanDetail").remove();
	//add new detail
	var cloneDetail = $("#clone_subPlanDetail"); 
	var newDetail = cloneDetail.find("tr.clonePlanDetail").clone();
	//remove delete icon in detail
	newDetail.find("span.deleteSubPlanDetail").remove();
	var detail = subPlan.find("table.subPlanDetail");
	detail.append(newDetail);
	initToggle(subPlan.find(".spanToggleClick"), subPlan);
	showJQuery(subPlan);
	optionservicegroup.append(subPlan);
	reindexPlan(service);
	//Set JobNo combobox
	//subPlan.find(".jobNo").append( "<option value=\""+ "add" +"\">"+"-----add one-----"+"</option>");
	subPlan.find(".jobNo").combobox();
	subPlan.find(".subPlanContent tr").eq(0).children().eq(3).css("padding-right",27+"px");
	var modelFlg = $("#action").val();
	if(modelFlg=="new") {
		setGSTApplyAll(subPlan);
		setCategoryApplyAll(subPlan, "");
		//Init JobNo when add a new option
		setJobApplyAll(subPlan);
	}
	
	var billCur = $("#plan_billingCurrency").val();
	changeRateCur(billCur);
	detail.find(".svcLevel3").combobox();
	setLineDescValue(subPlan);
}

function addNonStdService(){
	var service = $("#first_service").clone();
	service.attr("id", "");
	service.css('display','');
	service.find(".serviceIdPlan").val("");
	service.find(".serviceMultiPln").val("0");
	service.find(".billDesc").val("");
	
	/*service.find(".billDesc").autoTextarea({ maxHeight: 1000 });
	service.find(".billDesc").autoTextarea({ minHeight: 34 });
	service.find(".billDesc").focus();*/
	
	$("#service_group").append(service);
	reindexServiceDisplay();
	var modelFlg = $("#action").val();
	if(modelFlg=="new") {
		service.find(".jobNoAllChk").attr("checked",true);
		//service.find(".jobNoAllJob").append( "<option value=\""+ "add" +"\">"+"-----add one-----"+"</option>");
		service.find(".jobNoAllJob").combobox();
		//set billDesc
		var jobNoAllChk = service.find(".jobNoAllChk").is(':checked');
		var jobNoAllJob = service.find(".jobNoAllJob").val();
		var billDesc= service.find(".billDesc");
		var multiPln = $("#multiPln").val();
		if(jobNoAllChk){
			var jobNoAllJobVal=service.find('.jobNoAllJob option:selected').text();
			if(multiPln == "1"){
				billDesc.text("Job No.: "+jobNoAllJobVal);
			}
		}
	}
}

function removeSubPlan(obj) {
	var planStatus = $(".planStatus").val();
	var planType = $("#planType").val();
	if(("PS1"==planStatus || "PS2"==planStatus) && STANDARD_PLAN_TYPE==planType) {
		var idSubInfo = $("#idSubInfo").val();
		var idCustPlanLink = $(obj).closest("div.subPlan").find(".idCustPlanLink").val();
		if (idCustPlanLink!="" && idCustPlanLink!=null && idCustPlanLink!=undefined) {
			var path = $("#hiddenContextPath").val();
			var url = path+'/B_CPM/B_CPM_S02RemoveServiceOrSubPlanCheckAjax.do?type=subplan'
							+"&idSubInfo="+idSubInfo
							+"&id="+idCustPlanLink;
			$.ajax({
		        type: 'POST',
		        url: url,
		        success: function(result){
		        	if("failure"==result) {
		        		msg.ERROR_MESSAGE($("#message_group").find(".ERR1SC105_2").text());
		        	} else {
		        		doRemoveSubPlan(obj);
		        	}
		        }
		      });
		} else {
			doRemoveSubPlan(obj);
		}
	} else {
		doRemoveSubPlan(obj);
	}
}
function doRemoveSubPlan(obj) {
	var message = "";
	if ($(obj).closest(".subPlan").find(".itemType").val() == "S") {
		message = $("#message_group").find(".messageRemoveSubPlan").text();
	} else if ($(obj).closest(".subPlan").find(".itemType").val() == "O") {
		message = $("#message_group").find(".messageRemoveOptionService").text();
	}
	var isConfirm = (msg.POPCFM(message) == msg.YES_CONFIRM);
	if (isConfirm) {
		var button = $(obj);
		var subPlan = button.closest("div.subPlan");
		var service = subPlan.closest("div.service_object"); 
		subPlan.remove();
		calculateTotalAmount(service);
		reindexPlan(service);
	}
}

function addPlanDetail(obj) {
	//if subplanDetails is null,remove this and append a new PlanDetails
	var subPlan=$(obj).closest("div.subPlan");
	var lineDescEmpty=subPlan.find("#lineDescEmpty");
	if(lineDescEmpty.length>0){
		subPlan.find("tr.clonePlanDetail").remove();
	}
	
	var cloneDetail = $("#clone_subPlanDetail"); 
	var newDetail = cloneDetail.find("tr.clonePlanDetail").clone();
	var button = $(obj);
	var detail = button.closest("table.subPlanDetail");
	detail.append(newDetail);
	changeLevel34(newDetail.find(".svcLevel3"), "svcLevel3");
	changeLevel34(newDetail.find(".svcLevel4"), "svcLevel4");
	//Set LineDesc Value
	setLineDescValue(subPlan);
}

function deletePlanDetail(obj) {
	var button = $(obj);
	var detail = button.closest("tr.clonePlanDetail");
	detail.remove();
}

/**
 * 
 * @param obj
 * @return
 */
function removeService(obj) {
	if (countService() > 1) {
		var planStatus = $(".planStatus").val();
		var planType = $("#planType").val();
		if(("PS1"==planStatus || "PS2"==planStatus) && STANDARD_PLAN_TYPE==planType) {
			var idSubInfo = $("#idSubInfo").val();
			var idCustPlanGrp = $(obj).closest("div.service_object").find(".idCustPlanGrp").val();
			if (idCustPlanGrp!="" && idCustPlanGrp!=null && idCustPlanGrp!=undefined) {
				var path = $("#hiddenContextPath").val();
				var url = path+'/B_CPM/B_CPM_S02RemoveServiceOrSubPlanCheckAjax.do?type=service'
								+"&idSubInfo="+idSubInfo
								+"&id="+idCustPlanGrp;
				$.ajax({
			        type: 'POST',
			        url: url,
			        success: function(result){
			        	if("failure"==result) {
			        		msg.ERROR_MESSAGE($("#message_group").find(".ERR1SC105_1").text());
			        	} else {
			        		doRemoveService(obj);
			        	}
			        }
			      });
			} else {
				doRemoveService(obj);
			}
		} else {
			doRemoveService(obj);
		}
	} else {
		msg.ERROR_MESSAGE($("#message_group").find(".messageDeleteService").text());
	}
}

function doRemoveService(obj) {
	var isConfirm = (msg.POPCFM($("#message_group").find(".messageRemoveService").text()) == msg.YES_CONFIRM);
	if (isConfirm) {
		var link = $(obj);
		var service = link.closest("div.service_object");
		service.remove();
		reindexServiceDisplay();
	}
}

/**
 * 
 * @return
 */
function countService() {
	return $("#service_group").find(".service_object").length - 1;
}

/**
 * 
 * @param obj
 * @return
 */
function addSubPlanOptionService(obj) {
	var customertype = $("#customerType").val(); 
	var service = $(obj).closest("div.service_object");
	var idPlanGrpList = ""; 
	var subPlans = service.find(".subPlan");
	if(0<subPlans.length) {
		for ( var i = 0; i < subPlans.length-1; i++) {
			var subPlan = subPlans.eq(i);
			var planGrpValue = subPlan.find(".idPlanGrp").val(); 
			if (planGrpValue != "") {
				idPlanGrpList += "" + planGrpValue + ",";
			}
		}
		idPlanGrpList = idPlanGrpList + subPlans.eq(subPlans.length-1).find(".idPlanGrp").val();
	}
	//0:StandardPlan plan ,1:StandardPlan-Multi
	var serviceMultiPln = service.find(".serviceMultiPln").val();
	if ("0"==serviceMultiPln){
		var serviceStatus = service.find(".serviceStatus").val();
		var noDisplayOTC = "0";
		//service is Active
		if(serviceStatus == "PS3") {
			var serviceBacCount = service.find(".serviceBacCount").val();
			if("0"!=serviceBacCount) {
				noDisplayOTC = "1";
			}
		}
		var serviceIdPlan = service.find(".serviceIdPlan").val();
		var queryString = "&event=" + FORWARD_ADD_POPUP_M_MPP05 + 
							"&idPlan=" + serviceIdPlan + 
							"&idPlanGrpList=" + idPlanGrpList + 
							"&customerType=" + customertype +
							"&noDisplayOTC=" + noDisplayOTC;
		var url = getUrl(queryString);
		var popupWidth = window.screen.width*80/100;
		var popupHeight = window.screen.height*80/100;
		var newWindow = window.open(url,'name', POPUP_FEATURE.replace("#width#",popupWidth).replace("#height#",popupHeight));
		window.service = service;
		newWindow.service = service;
		newWindow.focus();
	} else {
		var queryString = "&event=" + FORWARD_ADD_POPUP + "&idPlanGrpList=" + idPlanGrpList + "&customerType=" + customertype;
		var url = getUrl(queryString);
		var popupWidth = window.screen.width*80/100;
		var popupHeight = window.screen.height*80/100;
		var newWindow = window.open(url,'name', POPUP_FEATURE.replace("#width#",popupWidth).replace("#height#",popupHeight));
		window.service = service;
		newWindow.service = service;
		newWindow.focus();
	}
}

/**
 * 
 * @param window
 * @param idPlanGrpList
 * @return
 */
function appendSubPlan(subWindow, idPlanGrpList) {
	 //window.open(getUrl("&event=" + FORWARD_LOAD_NEW_SUB_PLAN + "&idPlanGrpList=" + idPlanGrpList));
	 //ajax to call new sub plan group
	var service = window.service;
	var idCust = $("#idCustomer").val();
	var serviceMultiPln = service.find(".serviceMultiPln").val();
	//'new' mode,init jobAllChk & jobAllVal
	var jobNoAllChk="";
	var jobNoAllVal="";
	var modelFlg = $("#action").val();
	//set value for jobAllChk & jobAllVal
	if (modelFlg=="new") {
		var jobNoAllJobChk = service.find(".jobNoAllChk").is(':checked');
		var jobNoAllJobText=service.find('.jobNoAllJob option:selected').text();
		var jobNoAllJobVal=service.find('.jobNoAllJob option:selected').val();
		if(jobNoAllJobChk){
			jobNoAllChk="allchk";
			jobNoAllVal=jobNoAllJobVal;
		}
	}
	$.ajax({
		type: "POST",
		url: getUrl(""),
		data: {
		"event": FORWARD_LOAD_NEW_SUB_PLAN,
		"idPlanGrpList": idPlanGrpList,
		"idCust": idCust,
		"serviceMultiPln":serviceMultiPln,
		"jobNoAllChk":jobNoAllChk,
		"jobNoAllVal":jobNoAllVal
		},
		success: function(html) { 
			var content = document.createElement("div");
			content.innerHTML = html;
			var result = $(content);
			
			//add new object to sub plan group
			var subPlans = result.find(".subPlanGroup").find(".subPlan");
			var subPlanGrp = service.find("td.subplan");
			for ( var i = 0; i < subPlans.length; i++) {
				var subPlan = subPlans.eq(i);
				/*subPlan.find(".itemDesc").autoTextarea({ maxHeight: 1000 });
				subPlan.find(".itemDesc").autoTextarea({ minHeight: 34 });
				subPlan.find(".itemDesc").focus();*/
				addStarndardPlan(service,subPlan, subPlanGrp);
			}
			
			//add new option service
			var optionService = result.find(".optionServiceGroup").find(".subPlan");
			var optionServiceGrp = service.find("td.option_service");
			for ( var j = 0; j < optionService.length; j++) {
				var subPlan = optionService.eq(j);
				/*subPlan.find(".itemDesc").autoTextarea({ maxHeight: 1000 });
				subPlan.find(".itemDesc").autoTextarea({ minHeight: 34 });
				subPlan.find(".itemDesc").focus();*/
				addStarndardPlan(service,optionService.eq(j), optionServiceGrp);
			}
			
			initCheckMasterLocation();
			initCheckSubLocation();
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			//error handler
			
		}
	});
}

 /**
  * 
  * @param subPlanTemp
  * @param group
  * @return
  */
function addStarndardPlan(service,subPlanTemp, group) {
	var newSubPlan = subPlanTemp.clone();
	group.append(newSubPlan);
	
	reindexPlan(service);
	//initialize toggle
	initToggle(newSubPlan.find(".spanToggleClick"), newSubPlan);
	
	//setting currency
	displayOptionGrandTotal(EXPORT_CUR_ADD_SUB_PLAN);
	
	//re-calculate
	updateAmount(newSubPlan.find(".subPlanUnitPrice")[0]);
	updateDiscount(newSubPlan.find(".subDiscount")[0]);
	
	var gstValue = newSubPlan.find(".applyGst").val();
	newSubPlan.find(".applyGstValue").val(gstValue);
	/*if (gstValue == "1") {
		newSubPlan.find(".gstYes").show();
		newSubPlan.find(".gstNo").hide();
	} else {
		newSubPlan.find(".gstYes").hide();
		newSubPlan.find(".gstNo").show();
	}*/
	
	/*newSubPlan.find(".itemDesc").autoTextarea({ maxHeight: 1000 });
	newSubPlan.find(".itemDesc").autoTextarea({ minHeight: 34 });
	newSubPlan.find(".itemDesc").focus();*/
	
	showJQuery(newSubPlan);
	newSubPlan.find(".clonePlanDetail").each(function(item){
		$(this).find(".svcLevel3").append( "<option value=\""+ "add" +"\">"+"-----add one-----"+"</option>");
		$(this).find(".svcLevel3").combobox();
	});
	//Set JobNo combobox
	//newSubPlan.find(".jobNo").append( "<option value=\""+ "add" +"\">"+"-----add one-----"+"</option>");
	newSubPlan.find(".jobNo").combobox();
	newSubPlan.find(".subPlanContent tr").eq(0).children().eq(3).css("padding-right",27+"px");
	//init JobNo when add a new starndardPlan
	var modelFlg = $("#action").val();
	if(modelFlg=="new") {
		var jobNoAllChk = service.find(".jobNoAllChk").is(':checked');
		if(jobNoAllChk){
			//if jobNoAllChk is checked,then Set jobNo is disabled
			newSubPlan.find(".subPlanContent tr").eq(0).children().eq(3).find(".isDisplayJobNo").attr("disabled", jobNoAllChk);
			newSubPlan.find(".subPlanContent tr").eq(0).children().eq(3).find(".custom-combobox-input").attr("disabled", jobNoAllChk);
			newSubPlan.find(".subPlanContent tr").eq(0).children().eq(3).find(".ui-button").attr("disabled", jobNoAllChk);
		}
	}	

	initCheckMasterLocation();
	initCheckSubLocation();
}

//set LineDesc value
function setLineDescValue(subPlan){
	//get svcLevel2 Value
	var svcIdService2=subPlan.find(".svcLevel2").val();
	var svcLevel2Value=getlineDesc(svcIdService2,"svcLevel2");
	//var t=svcLevel2Value.lastIndexOf('-');
	//svcLevel2Value=svcLevel2Value.substring(0, t-1);
	//get svcLevel3 Value
	subPlan.find(".clonePlanDetail").each(function(item){
		var svcIdService3=$(this).find(".svcLevel3").val();
		var svcLevel3Value=getlineDesc(svcIdService3,"svcLevel3");
		var lineDesc=$(this).find("div.lineDesc");
		lineDesc.find("#lineDescVal").text(svcLevel2Value+"-"+svcLevel3Value);
	});
}

//get lineDesc value
function getlineDesc(svcLevel2Value,classObject){
	var svcLevelVal="";
	var cloneObj =  $("#svcLevelGroup").find("." + classObject).clone();
	var optionList = cloneObj.find("option");
	for ( var i = 0; i < optionList.length; i++) {
		var option = optionList.eq(i);
		if (option.val() != "" && option.val() == svcLevel2Value) {
			svcLevelVal=option.attr("descAbbr");
		}
	}
	return svcLevelVal;
}

//svcLevel3ChangeEvent
function svcLevel3ChangeEvt(obj,selectval){
	var subPlan = $(obj).closest("div.subPlan");
	var selectvalue=selectval;
	//get svcLevel2 Value
	var svcIdService2=subPlan.find(".svcLevel2").val();
	var svcLevel2Value=getlineDesc(svcIdService2,"svcLevel2");
	//var t=svcLevel2Value.lastIndexOf('-');
	//svcLevel2Value=svcLevel2Value.substring(0, t-1);
	//get svcLevel2 Value
	var svcLevel3Value=getlineDesc(selectvalue,"svcLevel3");
	//set lineDesc Value
	var subPlanDetails = $(obj).closest("tr.clonePlanDetail");
	var lineDesc=subPlanDetails.find("#lineDescVal");
	lineDesc.text(svcLevel2Value+"-"+svcLevel3Value);
}

function changeLevel2(obj){
	var subPlan = $(obj).closest("div.subPlan");
	//get svcLevel2 Value
	var svcIdService2=subPlan.find(".svcLevel2").val();
	var svcLevel2Value=getlineDesc(svcIdService2,"svcLevel2");
	//var t=svcLevel2Value.lastIndexOf('-');
	//svcLevel2Value=svcLevel2Value.substring(0, t-1);
	//get svcLevel3 Value
	subPlan.find(".clonePlanDetail").each(function(item){
		var svcIdService3=$(this).find(".svcLevel3").val();
		var svcLevel3Value=getlineDesc(svcIdService3,"svcLevel3");
		var lineDesc=$(this).find("#lineDescVal");
		lineDesc.text(svcLevel2Value+"-"+svcLevel3Value);
	});
}
/**
 * 
 * @return
 */
function viewSubscriptionInfo() {
	var planStatus = $(".planStatus").val();
	var queryString = "&event=" + FORWARD_EDIT_SUBSCRIPTION_INFO + 
						"&isPopUp=1" +					
						"&customerPlanID=" + $("#idCustPlan").val() + 
						"&subscriptionID=" + $("#idSubInfo").val() +
						"&customerID=" + $("#idCustomer").val() +
						"&processMode=1";
	if ("PS1"!=planStatus && "PS2"!=planStatus) {
		var services = $(".service_object");
		var idPlanGrpStr = "";
		for ( var i = 0; i < services.length; i++) {
			var service = services.eq(i);
			//setting sub plan
			var subPlans = service.find(".subPlan");
			for ( var j = 0; j < subPlans.length; j++) {
				var subPlan = subPlans.eq(j);
				var idPlanGrp = subPlan.find(".idPlanGrp").val();
				if(idPlanGrp!=null && idPlanGrp!="" && idPlanGrp!=undefined) {
					idPlanGrpStr = idPlanGrpStr + idPlanGrp +",";
				}
			}
		}
		if(idPlanGrpStr!=""){
			idPlanGrpStr = idPlanGrpStr.substring(0, idPlanGrpStr.length-1);
		}
		queryString = queryString + "&idPlanGrpList="+idPlanGrpStr;
	}
	var url = getUrl(queryString);
	var newWindow = window.open(url,'viewSubscription',POPUP_FEATURE_STD);
	newWindow.focus();
}
  
/**
 * 
 * @param obj
 * @return
 */
function checkServicePeriod(obj) {
	var jqueryCheckbox = $(obj);
	var parentObj = jqueryCheckbox.closest("fieldset.minimumService");
	if(obj==null ||obj==undefined) {
		//disable control
		parentObj.find("input.minimumServiceFrom").attr("disabled",true);
		parentObj.find("input.minimumServiceTo").attr("disabled",true);
		parentObj.find("input.contactTermNo").attr("disabled",true);
		parentObj.find("input.contactTerm").attr("disabled",true);
	} else {
		if (obj.checked) {
			//enable control
			parentObj.find("input.minimumServiceFrom").attr("disabled",false);
			parentObj.find("input.minimumServiceTo").attr("disabled",false);
			parentObj.find("input.contactTermNo").attr("disabled",false);
			parentObj.find("input.contactTerm").attr("disabled",false);
		} else {
			//disable control
			parentObj.find("input.minimumServiceFrom").val("");
			parentObj.find("input.minimumServiceTo").val("");
			parentObj.find("input.minimumServiceFrom").attr("disabled",true);
			parentObj.find("input.minimumServiceTo").attr("disabled",true);
			parentObj.find("input.contactTermNo").attr("disabled",true);
			parentObj.find("input.contactTerm").attr("disabled",true);
			parentObj.find("input.contactTermNo").val("");
			parentObj.find('input.contactTerm[value="Y"]').attr("checked",true);
		}
	}
}

/**
 * 
 * @return
 */
function saveCustomerPlan() {
	reIndexObject();
	reformatNumber();
	enableControl();
	if(onSaveChecking()){
		$('#clickSaveFlg').val("1");
		$(".billingOption").removeAttr("disabled");
		submitForm(FORWARD_SAVEPLAN);
	}
}

 /**
  * 
  * @return
  */
function exit() {
	if (msg.POPCFM($('#hiddenMsgExitConfirmation').val()) == msg.YES_CONFIRM) {
		if (window.opener == undefined) {
			reIndexObject();
			var idCustPlan = $("#idCustPlan").val();
			if(idCustPlan==null||idCustPlan==undefined||idCustPlan==""){
			    submitForm(FORWARD_EXIT);
			}else{
                window.location = $("#hiddenContextPath").val() + "/B_CPM/B_CPM_S02ViewBL.do?customerPlan.idCustPlan="+idCustPlan;
			}
		} else {
			window.close();
		}
	}
}

/**
 * 
 * @param obj
 * @return
 */
function checkNewAccount(obj) {
	//planType,SP:STANDARD_PLAN,NP:NON_STANDARD_PLAN
	var planType = $("#planType").val();
	 // Processing for Loading
	 if(event.type == "load"){
		 if($("#action").val() == "new"){
			 	//if no data then....
				if ( $("#plan_billAccNo").find("option").length == 0){
					$("#plan_billAccNo").attr("disabled", true);
					$("#plan_newAcc").attr("checked", true);
					if (STANDARD_PLAN_TYPE==planType) {
						var defCurrency = $("#defCurrency").val();
						$("#plan_billingCurrency").val(defCurrency);
					}
				} else {
					var clickSaveFlg = $("#clickSaveFlg").val();
					if(clickSaveFlg!=null && clickSaveFlg != "") {
						if($("#errorMessageInfo").text()!=null && $("#errorMessageInfo").text() != "") {
							if(!obj.checked) {
								//first data is selected
								changeBillingAccount();
								 
								// Disable these
								disableNewAccount(true);
							} else {
								$("#plan_billAccNo").attr("disabled", true);
							}
						}
						var custPlanMPlanCurDifFlg = $("#custPlanMPlanCurDifFlg").val();
						if(custPlanMPlanCurDifFlg!=null && custPlanMPlanCurDifFlg!="") {
							if(!obj.checked) {
								//first data is selected
								changeBillingAccount();
								 
								// Disable these
								disableNewAccount(true);
							} else {
								$("#plan_billAccNo").attr("disabled", true);
							}
						}
					} else {
						//first data is selected
						changeBillingAccount();
						 
						// Disable these
						disableNewAccount(true);
					}
				}
		}else if($("#action").val() == "edit"){
				if ( $("#plan_billAccNo").find("option").length == 0){
					$("#plan_billAccNo").attr("disabled", true);
					$("#plan_newAcc").attr("checked", true);
				}

				// check draft status
				if($(".planStatus").val() == "PS1"){
					//disable [Initial Billing Account No]
					$("#plan_billAccNo").attr("disabled", true);
					//checked [New Acc]
					obj.checked = true;
					// disable enable these ....
					$("#plan_billingCurrency").attr("disabled", false);
					$("#taxType").attr("disabled", false);					   
					$("#paymentMethod").attr("disabled", false);
					
					if($("#originalBillAccNo").val()!=""){
						$("#plan_billAccNo").val(getFormatBillAcc($("#originalBillAccNo").val(),20));
						$("#plan_billAccNo").attr("disabled", false);
						obj.checked = false;
						disableNewAccount(true);
					}
				}					
				

		}
		return;
	 }
	// Processing for on check this 
	if(obj.checked && event.type == "click"){
		// Enable these ....
		$("#plan_billAccNo").attr("disabled", true);
		
		var fixedForexFlg = $("#fixedForexFlg").val();
		if (fixedForexFlg=="1") {
			$("#fixedForex").val("");
			$("#fixedForex").attr("disabled", false);
		}
		
		$("#expGrandTotal").val("");
		$("#expGrandTotal").attr("disabled", false);
		
		$('#paymentMethod option:eq(1)').attr('selected','selected');
		$("#paymentMethod").attr("disabled", false);
		
		$("#taxType").attr("disabled", false);
		$("#taxType").val($("#taxTypeDefault").val());
		
		//if (STANDARD_PLAN_TYPE==planType) {
			var defCurrency = $("#defCurrency").val();
			$("#plan_billingCurrency").val(defCurrency);
		//} else {
		//	$("#plan_billingCurrency").val("");
		//}
		$("#plan_billingCurrency").attr("disabled", false);
		displayOptionGrandTotal(EXPORT_CUR_NOT_ON_LOAD_PAGE);
	}
	// Processing for uncheck this
	if(!obj.checked && event.type == "click"){
		$("#plan_billAccNo").attr("disabled", false);
		disableNewAccount(true);	
		
		// set originalBillAccNo hiden (to reset Billing Acc when New Acc checkbox unchecked)
		$("#originalBillAccNo").val($("#plan_billAccNo").val());
		
		changeBillingAccount();
	}
 }

 /**
  * 
  * @param status
  * @return
  */
function disableNewAccount(status) {
	$("#paymentMethod").attr("disabled", status);
	var fixedForexFlg = $("#fixedForexFlg").val();
	if (fixedForexFlg=="1") {
		$("#fixedForex").attr("disabled", status);
	}
	$("#expGrandTotal").attr("disabled", status);
	$("#plan_billingCurrency").attr("disabled", status);
	$("#taxType").attr("disabled", status);
}

/**
 * 
 * @return
 */
function changeBillingAccount() {	
	// Processing for on change this
	/*if ( ($("#plan_newAcc").attr("checked") != true && event.type == "change") || 
			($("#plan_newAcc").attr("checked") != true && event.type == "click")) {*/
		var selected_Value;
		if(event.type == "load"){
			if($("#originalBillAccNo").val() == ""){
				selected_Value = $("#plan_billAccNo").val();
			} else {
				selected_Value = $("#plan_billAccNo").val($("#originalBillAccNo").val()).val();
			}
		}else if(event.type == "change" || event.type == "click"){
			var selected_Value = $("#plan_billAccNo").val();
		}
		var optionBillAcc = $("#plan_billAccNo").find("option[value='" + selected_Value + "']"); 
		//get Payment Method
		$("#paymentMethod").val(optionBillAcc.attr("payment"));
		
		//get Billing Currency
		$("#plan_billingCurrency").val(optionBillAcc.attr("billCur"));
		
		//get Exp. Grand Total
		$("#expGrandTotal").val(optionBillAcc.attr("expCur"));
		//get Exp. Grand Total
		displayOptionGrandTotal(EXPORT_CUR_NOT_ON_LOAD_PAGE);
		
		$("#expGrandTotal").val(optionBillAcc.attr("expCur"));
		/** when billing currency == default currency -> display expGrandTotal combo box */
//				if(document.getElementById("defCurrency")){
//					if($("#defCurrency").val() == optionBillAcc.attr("billCur")){
//						$("#expGrandTotal").attr("disabled", false);
//					} else {
//						$("#expGrandTotal").attr("disabled", true);
//					}		
//				}
		
		//get Fixed Forex
		var fixedForexFlg = $("#fixedForexFlg").val();
		if (fixedForexFlg=="1") {
			$("#fixedForex").val(optionBillAcc.attr("fixed"));
		}
	/*}*/

		//Tax Type
		$("#taxType").val(optionBillAcc.attr("taxType"));
}

/**
 * 
 * @param obj
 * @return
 */
function changeAutoRenewal(obj) {
	if (obj == undefined) {
		return;
	}
	var service = $(obj).closest("div.service_object");
	if (obj.checked) {
		service.find("input.serviceDateEnd").attr('disabled',true);
		service.find("input.serviceDateEnd").val("");
	} else {
		service.find("input.serviceDateEnd").attr('disabled',false);
	}
}

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
		service.find(".isDiscountLumpSum").attr("disabled",false);
	}
	// ELSE a. both radio button disabledb. default selection: Lump Suim
	else{
		service.find(".isDiscountLumpSum").eq(1).attr("checked",true);
		service.find(".isDiscountLumpSum").attr("disabled",true);
	}
}

/**
 * 
 * @param obj
 * @return
 */
function changeProrateBase(obj) {
	var service = $(obj).closest("div.service_object");
	if($(obj).val() == 'S' && $(obj).is(':checked')) {
		service.find("input.proRateBaseNo").attr("disabled", true);
	} else {
		service.find("input.proRateBaseNo").attr("disabled", false);
	}
}
/**
 * 
 * @param obj
 * @return
 */
function gstChange(obj) {
	var subPlan = $(obj).closest("div.subPlan");
	var gst;
	var cboLevel2 = subPlan.find("select.svcLevel2");
	gst = cboLevel2.find("option[value='"+ cboLevel2.val() +"']").attr("gstValue");
	if (gst == "1") {
		//set gst to yes
		subPlan.find("input.applyGst").val("1");
		subPlan.find("label.gstYes").show();
		subPlan.find("label.gstNo").hide();
		return;
	}
	var listPlanDetail3 = subPlan.find(".svcLevel3");
	
	for ( var i = 0; i < listPlanDetail3.length; i++) {
		var cboLevel3 = listPlanDetail3.eq(i);
		if (cboLevel3.val() != "") {
			gst = cboLevel3.find("option[value='"+ cboLevel3.val() +"']").attr("gstValue");
			if (gst == "1") {
				//set gst to yes
				subPlan.find("input.applyGst").val("1");
				subPlan.find("label.gstYes").show();
				subPlan.find("label.gstNo").hide();
				return;
			}
		}
	}
	
	var listPlanDetail4 = subPlan.find(".svcLevel4");
	for ( var j = 0; j < listPlanDetail4.length; j++) {
		var cboLevel4 = listPlanDetail4.eq(j);
		if (cboLevel4.val() != "") {
			gst = cboLevel4.find("option[value='"+ cboLevel4.val() +"']").attr("gstValue");
			if (gst == "1") {
				//set gst to yes
				subPlan.find("input.applyGst").val("1");
				subPlan.find("label.gstYes").show();
				subPlan.find("label.gstNo").hide();
				return;
			}
		}
	}
	//set gst to no
	subPlan.find("input.applyGst").val("0");
	subPlan.find("label.gstYes").hide();
	subPlan.find("label.gstNo").show();
}
/**
 * 
 * @return
 */
function changeTransactionType() {
	//planType,SP:STANDARD_PLAN,NP:NON_STANDARD_PLAN
	var planType = $("#planType").val();
	if (NON_STANDARD_PLAN_TYPE==planType) {
		var transactionType = $('input[type="radio"][name$="transactionType"]');
		for(var i = 0; i < transactionType.length;i++){
			if(transactionType.eq(i).is(':checked')){
				var value = transactionType.eq(i).val(); 
				if (value != "IN") {
					$('[name$="referencePlan"]').attr('disabled',false);
				} else {
					$('[name$="referencePlan"]').attr('disabled',true);
				}
			}
		}
	}
}

/**
 * 
 * @return
 */
function changeBillServiceId() {
	//planType,SP:STANDARD_PLAN,NP:NON_STANDARD_PLAN
	var planType = $("#planType").val();
	if (NON_STANDARD_PLAN_TYPE==planType) {
		var billAccAll = $('input[type="radio"][name$="billAccAll"]');
		for(var i = 0; i < billAccAll.length;i++){
			if(billAccAll.eq(i).is(':checked')){
				var value = billAccAll.eq(i).val(); 
				if (value == "1") {
					$('#svcLevel1').attr('disabled',false);
					$('#svcLevel2').attr('disabled',false);
				} else {
					$('#svcLevel1').attr('disabled',true);
					$('#svcLevel2').attr('disabled',true);
				}
			}
		}
	}
}

/**
 * 
 * @return
 */
function changeCustomerPlanLevel1() {
	//planType,SP:STANDARD_PLAN,NP:NON_STANDARD_PLAN
	var planType = $("#planType").val();
	if (NON_STANDARD_PLAN_TYPE==planType) {
		var svcGrp = $("#svcLevel1").val();
		var level2 = $("#svcLevel2");
		//remap option for combobox
		displayOption(level2, svcGrp, "svcLevel2");
	}
}

/**
 * 
 * @param obj
 * @return
 */
function changeLevel1(obj) {
	var subPlan = $(obj[0]).closest("div.subPlan");
	var svcGrp = subPlan.find(".svcLevel1").val();
	//var svcleve3input=subPlan.find(".custom-combobox-input");
	displayOption(subPlan.find(".svcLevel2"), svcGrp, "svcLevel2");
	var subPlanDetail3 = subPlan.find(".svcLevel3");
	for ( var i = 0; i < subPlanDetail3.length; i++) {
		var detail = subPlanDetail3.eq(i);
		//var svclevedetail=svcleve3input.eq(i);
		var clonePlanDetail=detail.closest("tr.clonePlanDetail");
		var svclevedetail=clonePlanDetail.find(".custom-combobox-input");
		svclevedetail.val("-Please select one-");
		var svcwidth=displayOption(detail, svcGrp, "svcLevel3");
		svclevedetail.width(svcwidth);
	}
	var subPlanDetail4 = subPlan.find(".svcLevel4");
	for ( var j = 0; j < subPlanDetail4.length; j++) {
		var detail4 = subPlanDetail4.eq(j);
		displayOption(detail4, svcGrp, "svcLevel4");
	}
	//Set LineDesc Value
	 setLineDescValue(subPlan);
}

/**
 * 
 * @param level
 * @param name
 * @return
 */		
function changeLevel34(level, name) {
	var subPlan = $(level).closest("div.subPlan");
	var svcGrp = subPlan.find(".svcLevel1").val();
	displayOption(level, svcGrp, name);
}

/**
 * 
 * @param obj
 * @return
 */
function removeAllOption(obj) {
	obj.val("");
	var optionList = obj.find("option");
	for ( var i = 0; i < optionList.length; i++) {
		var option = optionList.eq(i);
		if (option.val() != "") {
			option.remove();
		}
	}
}
 
function emptyOption(obj) {
	obj.val("");
	var optionList = obj.find("option");
	for ( var i = 0; i < optionList.length; i++) {
		var option = optionList.eq(i);
		option.remove();
	}
}

/**
 * 
 * @param obj
 * @param svcGrp
 * @param classObject
 * @return
 */
function displayOption(obj, svcGrp, classObject) {
	var optionsize=0;
	if(classObject=="svcLevel3"){
		//save selected value
		var selectedValue = obj.val();
		removeAllOption(obj);
		//add valid option for object
		var optionList =  $("#svcLevelGroup").find(".svcLevel3").find("option[svcGrp='"+svcGrp+"']").clone();
		for ( var i = 0; i < optionList.length; i++) {
			var option = optionList.eq(i);
			obj.append(option);
			if(option.text().length>optionsize){
				optionsize=option.text().length;
			}
		}
		if(optionsize>20){
			optionsize=optionsize*6;
		}
		else{
			optionsize=126;
		}
		obj.append( "<option value=\""+ "add" +"\">"+"-----add one-----"+"</option>");
		if(svcGrp==""||svcGrp==null)
		{
			obj.find("option[value='add']").remove();
		}
		
		obj.val(selectedValue);
		obj.combobox();
	}
	else{
		//save selected value
	var selectedValue = obj.val();
		removeAllOption(obj);
		//add valid option for object
		var optionList =  $("#svcLevelGroup").find("."+classObject).find("option[svcGrp='"+svcGrp+"']").clone();
		var copyCbo = obj.clone();
		obj.replaceWith(copyCbo);
		for ( var i = 0; i < optionList.length; i++) {
			var option = optionList.eq(i);
			if (option.val() != "" && option.attr("svcGrp") == svcGrp) {
				copyCbo.append(option);
			}
		}
		copyCbo.val(selectedValue);
	}
	return optionsize;
}

function displayOptionGrandTotal(typeFlg) {
	var bilCur = $("#plan_billingCurrency").val();
	
	//save selected value
	var defCurrency = $("#defCurrency").val();
	var expGrandTotal = $("#expGrandTotal");
	var cboExportCurrency = expGrandTotal.val();
	var hidExportCurrency= $("#hidExpGrandTotal").val();
	if(cboExportCurrency==null){
		cboExportCurrency="";
	}
	if(hidExportCurrency==null){
		hidExportCurrency="";
	}
	emptyOption(expGrandTotal);

	if(bilCur!=defCurrency) {
		$("#expGrandTotal").append("<option value=''> -Please select one- </option>");
		$("#expGrandTotal").append("<option value='"+defCurrency+"'>"+defCurrency+"</option>");
		if (EXPORT_CUR_ON_LOAD_PAGE==typeFlg) {
			$("#expGrandTotal").val(hidExportCurrency);
		} else if (EXPORT_CUR_ADD_SUB_PLAN==typeFlg) {
			$("#expGrandTotal").val(cboExportCurrency);
		} else {
			$("#expGrandTotal").val(defCurrency);
		}
	} else {
		addAllExportCurrency(expGrandTotal);
		$("#expGrandTotal").val(cboExportCurrency);
	}
	
	var planType = $("#planType").val();
	if (NON_STANDARD_PLAN_TYPE==planType) {
		changeRateCur(bilCur);
	}
}

//#169 Start
function initDisplayOptionGrandTotal() {
	var bilCur = $("#plan_billingCurrency").val();
	
	//save selected value
	var defCurrency = $("#defCurrency").val();
	var expGrandTotal = $("#expGrandTotal");
	var cboExportCurrency = expGrandTotal.val();
	var hidExportCurrency= $("#hidExpGrandTotal").val();
	if(cboExportCurrency==null){
		cboExportCurrency="";
	}
	if(hidExportCurrency==null){
		hidExportCurrency="";
	}
	emptyOption(expGrandTotal);

	if(bilCur!=defCurrency) {
		$("#expGrandTotal").append("<option value=''> -Please select one- </option>");
		$("#expGrandTotal").append("<option value='"+defCurrency+"'>"+defCurrency+"</option>");
		$("#expGrandTotal").val(cboExportCurrency);
	} else {
		addAllExportCurrency(expGrandTotal);
		$("#expGrandTotal").val(cboExportCurrency);
	}
	
	var planType = $("#planType").val();
	if (NON_STANDARD_PLAN_TYPE==planType) {
		changeRateCur(bilCur);
	}
}
//#169 End

function addAllExportCurrency(expGrandTotalObj) {
	var cloneObj =  $("#svcLevelGroup").find(".expGrandTotal").clone();
	var optionList = cloneObj.find("option");
	for ( var i = 0; i < optionList.length; i++) {
		var option = optionList.eq(i);
		expGrandTotalObj.append(option);
	}
}

function changeRateCur(billCur) {
	var subplanGroup = $(".service_object").find(".subPlan");
	for(var i = 0; i < subplanGroup.length; i++) {
		var subplan = subplanGroup.eq(i);
		subplan.find(".displayCurrency").text("("+billCur+")");
	}
}
 /**
  * 
  * @param obj
  * @return
  */
function updateAmount(obj) {
	var input = $(obj).val().replace(/,/g, "");
	if(input == ''){
		obj.value=0;
		input = 0;
	}
	if(!isNaN(input)) {
		var subPlan = $(obj).closest("div.subPlan");
		var quantity = subPlan.find(".subPlanQuantity");
		var unitPrice = subPlan.find(".subPlanUnitPrice");
		var amount = subPlan.find(".subPlanAmount");
		
		var quantityRoundFirst = quantity.val().replace(/,/g, "");
		quantityRoundFirst = numberFormat(quantityRoundFirst, 0, "");
		var quantityValue = quantityRoundFirst.replace(/,/g, ""); //numberFormat(quantityValue, 0, "")
		var unitPriceRoundFirst = unitPrice.val().replace(/,/g, "");
		unitPriceRoundFirst = numberFormat(unitPriceRoundFirst, 2, "");
		var unitPriceValue = unitPriceRoundFirst.replace(/,/g, ""); //numberFormat(unitPriceValue, 2, "")
		
		if (quantityValue != "" && unitPriceValue != "" && !isNaN(quantityValue) && !isNaN(unitPriceValue)) {
			var displayAmount = numberFormat(parseFloat(quantityValue) * parseFloat(unitPriceValue), 2, ",");
			amount.val(displayAmount);
			quantity.val(numberFormat(quantityValue, 0, ","));
			unitPrice.val(numberFormat(unitPriceValue, 2, ","));
			var service = subPlan.closest("div.service_object");
			calculateTotalAmount(service);
		} else if (quantity.val() != "" && !isNaN(quantity.val())) {
			quantity.val(numberFormat(quantityValue, 0, ","));
		} else if (unitPrice.val() != "" && !isNaN(unitPrice.val())) {
			unitPrice.val(numberFormat(unitPriceValue, 2, ","));
		}
	}
}

function updateDiscount_enter(obj,evt){
	var e = evt;
	if(window.event){ // IE
		var charCode = e.keyCode;
	} else if (e.which) { // Safari 4, Firefox 3.0.4
		var charCode = e.which;
	}
	if(charCode == 13){
		var va = $.trim(obj.value);
		if(va == ''){
			obj.value = 0;
		}
		updateDiscount(obj);
		
	}
}

function updateDiscount(obj){
	var input =$(obj).val().replace(/,/g, "");
	if(input == ''){
		obj.value=0;
		input = 0;
	}
	if (!isNaN(input)) {
		var subPlan = $(obj).closest("div.subPlan");
		var discount = subPlan.find(".subDiscount");
		var discountvalue = discount.val().replace(/,/g, "");
		if (discountvalue != "") {
			var service = subPlan.closest("div.service_object");
			calculateTotalDiscount(service);
			var disvalue=parseFloat(Math.abs(discountvalue));
			var endvalue=numberFormat(disvalue, 2, ",");
			discount.val("-"+endvalue);
		}
	}
}

/**
 * 
 * @param service
 * @return
 */
function calculateTotalDiscount(service) {
	var subPlans = service.find("div.subPlan");
	var totalDiscount=0.00;
	for ( var i = 0; i < subPlans.length; i++) {
		var subPlan = subPlans.eq(i);
		var discount = subPlan.find(".subDiscount");
		if (discount.val() != "") {
			var discountvalue = discount.val().replace(/,/g, ""); 
			totalDiscount+=parseFloat(Math.abs(discountvalue));
		}
	}
	var totalAmountDisNum = numberFormat(totalDiscount, 2, ",");
	var serviceDiscount= service.find("td.serviceDiscountDetail");
	serviceDiscount.text("-"+totalAmountDisNum);
	// subdiscount.text("-"+totalAmountDisNum);
}

/**
 * 
 * @param service
 * @return
 */
function calculateTotalAmount(service) {
	var subPlans = service.find("div.subPlan");
	//var totalQuantity = 0;
	//var totalUnitPrice = 0.00;
	var totalAmount = 0.00;
	for ( var i = 0; i < subPlans.length; i++) {
		var subPlan = subPlans.eq(i);
		var quantity = subPlan.find(".subPlanQuantity");
		var unitPrice = subPlan.find(".subPlanUnitPrice");
		var amount = subPlan.find(".subPlanAmount");
		var discount=subPlan.find(".subDiscount");
		
		if (quantity.val() != "") {
			var quantityValue = quantity.val().replace(/,/g, ""); 
			//totalQuantity += parseFloat(quantityValue);
		}
		if (unitPrice.val() != "") {
			var unitPriceValue = unitPrice.val().replace(/,/g, ""); 
			//totalUnitPrice += parseFloat(unitPriceValue);
		}
		if (amount.val() != "") {
			var amountValue = amount.val().replace(/,/g, ""); 
			totalAmount += parseFloat(amountValue);
		}
	}
	//format total amount
	//var totalQuantityDisNum = numberFormat(totalQuantity, 0, ",");
	//var totalUnitPriceDisNum = numberFormat(totalUnitPrice, 2, ",");
	var totalAmountDisNum = numberFormat(totalAmount, 2, ",");
	
	//update total amount of service
	//var serviceQuantity = service.find("td.serviceQuantity");
	var serviceUnitPrice = service.find("td.serviceUnitPrice");
	var serviceAmount = service.find("td.serviceAmount");
	
	//serviceQuantity.text(totalQuantityDisNum);
	serviceUnitPrice.text(totalAmountDisNum);
	serviceAmount.text(totalAmountDisNum);
}

/**
 * 
 * @return
 */
function enableControl() {
	//check plan status to disable control
	if ($(".planStatus").val() == "PS3") {
		//planType,SP:STANDARD_PLAN,NP:NON_STANDARD_PLAN
		var planType = $("#planType").val();
		if (NON_STANDARD_PLAN_TYPE==planType) {
			$(".transactionType").attr("disabled", false);;
			$(".billAccAll").attr("disabled", false);
			//check transaction type
			if ($(".billAccAll").val() != "IV") {
				$("#svcLevel1").attr("disabled", false);
				$("#svcLevel2").attr("disabled", false);
			}
		}
	}
	//check for case new account checked or unchecked
	disableNewAccount(false);
}

/**
 * 
 * @return
 */
function reformatNumber() {
	var subPlans = $(".subPlan");
	for ( var i = 0; i < subPlans.length; i++) {
		var subPlan = subPlans.eq(i);
		var quantity = subPlan.find(".subPlanQuantity").val();
		var unitPrice = subPlan.find(".subPlanUnitPrice").val();
		var amount = subPlan.find(".subPlanAmount").val();
		var rate = subPlan.find(".rate").val();
		var discount=subPlan.find(".subDiscount").val();
		
		subPlan.find(".subPlanQuantity").val(quantity.replace(/,/g,""));
		subPlan.find(".subPlanUnitPrice").val(unitPrice.replace(/,/g,""));
		subPlan.find(".subPlanAmount").val(amount.replace(/,/g,""));
		if (rate == undefined || rate == null || rate == "") {
		}else{
			subPlan.find(".rate").val(rate.replace(/,/g,""));
		}
		subPlan.find(".subDiscount").val(discount.replace(/,/g,""));
	}
}
 
function reIndexObject() {
	var service_grp = $("#service_group");
	var services = service_grp.find("div.service_object");
	var index = 0;
	for ( var i = 0; i < services.length; i++) {
		var service = services.eq(i);
		var id = service.attr("id");
		if(id == ""||id ==undefined) {
			//rename control
			service.find('input[type="radio"][name$="isDiscountLumpSum"]').attr("disabled", false);
			service.find('input[type="text"][name$="custPo"]').attr("disabled", false);
			service.find("input.idCustPlanGrp").attr("name","customerPlan.services[" + index + "].idCustPlanGrp");
			service.find("input.serviceStatus").attr("name","customerPlan.services[" + index + "].serviceStatus");
			service.find("input.billingStatus").attr("name","customerPlan.services[" + index + "].billingStatus");
			service.find("input.serviceMultiPln").attr("name","customerPlan.services[" + index + "].serviceMultiPln");
			service.find("input.serviceIdPlan").attr("name","customerPlan.services[" + index + "].serviceIdPlan");
			service.find("input.serviceDateEnd").attr("name","customerPlan.services[" + index + "].serviceDateEnd");
			service.find('input[type="text"][name$="serviceDateStart"]').attr("name","customerPlan.services[" + index + "].serviceDateStart");
			service.find('input[type="text"][name$="serviceDateEnd"]').attr("name","customerPlan.services[" + index + "].serviceDateEnd");
			service.find('input[type="checkbox"][name$="autoRenewal"]').attr("name","customerPlan.services[" + index + "].autoRenewal");
			service.find('input[type="checkbox"][name$="minimumService"]').attr("name","customerPlan.services[" + index + "].minimumService");
			service.find('input[type="text"][name$="minimumServiceFrom"]').attr("name","customerPlan.services[" + index + "].minimumServiceFrom");
			service.find('input[type="text"][name$="minimumServiceTo"]').attr("name","customerPlan.services[" + index + "].minimumServiceTo");
			service.find('input[type="text"][name$="contactTermNo"]').attr("name","customerPlan.services[" + index + "].contactTermNo");
			service.find('input[type="radio"][name$="contactTerm"]').attr("name","customerPlan.services[" + index + "].contactTerm");
			service.find('input[type="radio"][name$="proRateBase"]').attr("name","customerPlan.services[" + index + "].proRateBase");
			service.find('input[type="text"][name$="proRateBaseNo"]').attr("name","customerPlan.services[" + index + "].proRateBaseNo");
			service.find('input[type="radio"][name$="isLumpSum"]').attr("name","customerPlan.services[" + index + "].isLumpSum");
			service.find('input[type="radio"][name$="isDiscountLumpSum"]').attr("name","customerPlan.services[" + index + "].isDiscountLumpSum");
			// #189 Start
			service.find('input[type="radio"][name$="billingOption"]').attr("disabled", false);
			service.find('input[type="radio"][name$="billingOption"]').attr("name","customerPlan.services[" + index + "].billingOption");
			// #189 End
			service.find('textarea[name$="billDesc"]').attr("name","customerPlan.services[" + index + "].billDesc");
			// custPo
			service.find('input[type="text"][name$="custPo"]').attr("name","customerPlan.services[" + index + "].custPo");
			service.find("input.serviceBacCount").attr("name","customerPlan.services[" + index + "].serviceBacCount");
			//set jobNoAllChk
			service.find('.jobNoAllChk').attr("name","customerPlan.services[" + index + "].jobNoAllChk");
			service.find('.jobNoAllJob').attr("name","customerPlan.services[" + index + "].jobNoAllJob");
			
			//wcbeh@20170104 MasterLocation Not Saved
			service.find('.masterLocationFlg').attr("name","customerPlan.services[" + index + "].masterLocationFlg");
			service.find('.masterLocation').attr("name","customerPlan.services[" + index + "].masterLocation");
			
			//rename sub plan and option service
			var subPlans = service.find("div.subPlan");
			for ( var j = 0; j < subPlans.length; j++) {
				var subPlan = subPlans.eq(j);
				subPlan.find(".svcLevel1").attr("disabled", false);
				subPlan.find(".svcLevel2").attr("disabled", false);
				subPlan.find(".applyGst").attr("disabled", false);
				subPlan.find(".jobNo").attr("disabled", false);
				subPlan.find(".isDisplayJobNo").attr("disabled", false);
				subPlan.find(".rateType2").attr("disabled", false);
				subPlan.find(".idCustPlanLink").attr("name","customerPlan.services[" + index + "].subPlans[" + j + "].idCustPlanLink");
				subPlan.find(".applyGst").attr("name","customerPlan.services[" + index + "].subPlans[" + j + "].applyGst");
				subPlan.find(".itemType").attr("name","customerPlan.services[" + index + "].subPlans[" + j + "].itemType");
				subPlan.find(".planDesc").attr("name","customerPlan.services[" + index + "].subPlans[" + j + "].planDesc");
				subPlan.find(".subLocation").attr("name","customerPlan.services[" + index + "].subPlans[" + j + "].subLocation");
				subPlan.find(".itemDesc").attr("name","customerPlan.services[" + index + "].subPlans[" + j + "].itemDesc");
				/*subPlan.find(".custPo").attr("name","customerPlan.services[" + index + "].subPlans[" + j + "].custPo");*/
				subPlan.find(".subPlanQuantity").attr("name","customerPlan.services[" + index + "].subPlans[" + j + "].quantity");
				subPlan.find(".subPlanUnitPrice").attr("name","customerPlan.services[" + index + "].subPlans[" + j + "].unitPrice");
				subPlan.find(".subPlanAmount").attr("name","customerPlan.services[" + index + "].subPlans[" + j + "].amount");
				subPlan.find(".jobNo").attr("name","customerPlan.services[" + index + "].subPlans[" + j + "].jobNo");
				subPlan.find(".isDisplayJobNo").attr("name","customerPlan.services[" + index + "].subPlans[" + j + "].isDisplayJobNo");
				subPlan.find(".svcLevel1").attr("name","customerPlan.services[" + index + "].subPlans[" + j + "].svcLevel1");
				subPlan.find(".svcLevel2").attr("name","customerPlan.services[" + index + "].subPlans[" + j + "].svcLevel2");
				subPlan.find(".rateType").attr("name","customerPlan.services[" + index + "].subPlans[" + j + "].rateType");
				subPlan.find(".rateType2").attr("name","customerPlan.services[" + index + "].subPlans[" + j + "].rateType2");
				subPlan.find(".rateMode").attr("name","customerPlan.services[" + index + "].subPlans[" + j + "].rateMode");
				subPlan.find(".rate").attr("name","customerPlan.services[" + index + "].subPlans[" + j + "].rate");
				subPlan.find(".isDiscountOneTime").attr("name","customerPlan.services[" + index + "].subPlans[n" + j + "].isDiscountOneTime");
				subPlan.find(".discamount").attr("name","customerPlan.services[" + index + "].subPlans[" + j + "].discamount");
				subPlan.find(".subDiscount").attr("name","customerPlan.services[" + index + "].subPlans[" + j + "].discamount");
				//rename for standard plan
				subPlan.find(".idPlan").attr("name","customerPlan.services[" + index + "].subPlans[" + j + "].idPlan");
				subPlan.find(".idPlanGrp").attr("name","customerPlan.services[" + index + "].subPlans[" + j + "].idPlanGrp");
				subPlan.find(".planName").attr("name","customerPlan.services[" + index + "].subPlans[" + j + "].planName");
				subPlan.find(".itemGrpName").attr("name","customerPlan.services[" + index + "].subPlans[" + j + "].itemGrpName");
				subPlan.find(".currency").attr("name","customerPlan.services[" + index + "].subPlans[" + j + "].currency");
				
				//rename plan detail
				var details = subPlan.find("tr.clonePlanDetail");
				for ( var k = 0; k < details.length; k++) {
					var detail = details.eq(k);
					detail.find(".idCustPlanSvc").attr("name","customerPlan.services[" + index + "].subPlans[" + j + "].subPlanDetails[" + k + "].idCustPlanSvc");
					detail.find('.svcLevel3').attr("name","customerPlan.services[" + index + "].subPlans[" + j + "].subPlanDetails[" + k + "].svcLevel3");
					detail.find('.vendor').attr("name","customerPlan.services[" + index + "].subPlans[" + j + "].subPlanDetails[" + k + "].vendor");
					detail.find('.svcLevel4').attr("name","customerPlan.services[" + index + "].subPlans[" + j + "].subPlanDetails[" + k + "].svcLevel4");
				}
			}
			
			for ( var j = 0; j < subPlans.length; j++) {
				var subPlan = subPlans.eq(j);
				subPlan.find(".isDiscountOneTime").attr("name","customerPlan.services[" + index + "].subPlans[" + j + "].isDiscountOneTime");
			}
			index += 1;
		} else {
			//remove clone service
			service.remove();
		}
	}
}

function onSaveChecking() {
		
		var service_grp = $("#service_group");
		var services = service_grp.find("div.service_object");
		var index = 0;
		var taxWordHidden = $("#taxWordHidden").val();
		var msg = "Service num: Not all sub plan / option services have "
				+ "<br/>applied the same GST Tax Code";
		var msg2 = "Service num: Not all sub plan / option services have "
				+ "<br/>applied the same "+taxWordHidden+" Tax Rate";
		var message = "";
		var isError = false;
		var taxType = $("#taxType").val();
		for ( var i = 0; i < services.length; i++) {
			var service = services.eq(i);
			var id = service.attr("id");
			if (id == ""||id ==undefined) {
				index += 1;
				var billingamountval = service.find('input[type="radio"][name$="isLumpSum"][checked]').val();
				var Discountamountval = service.find('input[type="radio"][name$="isDiscountLumpSum"][checked]').val();
				if (billingamountval == '1' || Discountamountval == '1') {
					var subPlans = service.find("div.subPlan");
 
					for ( var j = 0; j < subPlans.length - 1; j++) {
						/* alert("Subplan gst: "+subPlans.eq(j).find(".applyGst").val()); */
						if(taxType == 0){
							if (subPlans.eq(j).find(".applyGst").val() != subPlans.eq(
									j + 1).find(".applyGst").val()) {
								isError = true;
								if (message == "") {
									message = message + msg.replace(/num/g, index);
								} else {
									message = message + "<br/>"
											+ msg.replace(/num/g, index);
								}
								break;
							}
						}else{
							if (subPlans.eq(j).find(".applyGstValue option:selected").text() != subPlans.eq(
									j + 1).find(".applyGstValue option:selected").text()) {
								isError = true;
								if (message == "") {
									message = message + msg2.replace(/num/g, index);
								} else {
									message = message + "<br/>"
											+ msg2.replace(/num/g, index);
								}
								break;
							}
						}
					}
				}
			}
		}
		if (isError) {
			var MsgBox = new messageBox();
			MsgBox.POPALT(message);
			return false;
		} else {
			return true;
		}
}
/**
 * 
 * @param ctr
 * @param ctr2Name
 * @param format
 * @return
 */
function customCalendar(ctr, ctr2Name, format) {
	var service = $(ctr).closest("div.service_object");
	var ctr2 = service.find("input." + ctr2Name);
	if (ctr2[0] != undefined && ctr2[0] != null) {
		if (ctr2[0].disabled == false) {
			jscalendarPopUpCalendar(ctr,ctr2[0],format);
		}
	}
}

function getFormatBillAcc(billacc,len){
	var formatAcc = ""+billacc;
	while(formatAcc.length<len){
		formatAcc = formatAcc + " ";
	}
	return formatAcc;
}

function GSTApplyAllEvt(type){
	//GST ComboBox change
	if(type=="GSTApplyAllCboChange") {
		var GSTApplyAllChk = $("#GSTApplyAllChk").is(':checked');
		if (GSTApplyAllChk) {
			GSTApplyAll();
		}
	} else {
		//GST Check Box click
		if (type!="") {
			GSTApplyAll();
		} else {
			// page onload
			var GSTApplyAllChk = $("#GSTApplyAllChk").is(':checked');
			if (GSTApplyAllChk) {
				GSTApplyAll();
			}
		}
	}
}

function GSTApplyAll() {
	var subplanGroup = $(".service_object").find(".subPlan");
	for(var i = 0; i < subplanGroup.length; i++) {
		var subplan = subplanGroup.eq(i);
		setGSTApplyAll(subplan);
	}
	
//			var optionserviceGroup = $(".service_object").find(".service");
//			for(var i = 0; i < optionserviceGroup.length; i++) {
//				var optionservice = optionserviceGroup.eq(i);
//				setGSTApplyAll(optionservice);
//			}
}

function setGSTApplyAll(obj){
	var GSTApplyAllChk = $("#GSTApplyAllChk").is(':checked');
	var GSTApplyAllCbo = $("#GSTApplyAllCbo").val();
	
	var applyGst = obj.find(".applyGst");
	if(GSTApplyAllChk){
		applyGst.val(GSTApplyAllCbo);
	}
	else{
		applyGst.val('1');
	}
	if($("#GSTApplyAllChk").length>0){
		applyGst.attr("disabled", GSTApplyAllChk);
	}
}

function categoryApplyAllEvt(typeEvt, typeEvt1){
	//Apply All Service and category Combo Box change
	if(typeEvt1=="serviceApplyAllCboChange" || typeEvt1=="categoryApplyAllCboChange") {
		var categoryApplyAllChk = $("#categoryApplyAllChk").is(':checked');
		if(categoryApplyAllChk) {
			categoryApplyAll(typeEvt);
		}
	} else {
		//typeEvt1 is '' means page onload call
		if(typeEvt1!="") {
			categoryApplyAll(typeEvt);
		}
	}
}

function categoryApplyAll(typeEvt) {
	var subplanGroup = $(".service_object").find(".subPlan");
	for(var i = 0; i < subplanGroup.length; i++) {
		var subplan = subplanGroup.eq(i);
		setCategoryApplyAll(subplan, typeEvt);
	}
}
function setCategoryApplyAll(obj, typeEvt) {
	var categoryApplyAllChk = $("#categoryApplyAllChk").is(':checked');
	var categoryApplyAllCbo = $("#categoryApplyAllCbo").val();
	var serviceApplyAllCbo = $("#serviceApplyAllCbo").val();
	
	var cboSvcLevel1 = obj.find(".svcLevel1");
	var cboSvcLevel2 = obj.find(".svcLevel2");

	//not serviceApplyAllCbo comboBox change
	if("serviceApplyAllCboChange"!=typeEvt) {
		//categoryApplyAll check box click
		if ("categoryApplyAllChkClick"==typeEvt) {
			//from checked to not checked
			if(!categoryApplyAllChk) {
				cboSvcLevel2.attr("disabled", categoryApplyAllChk);
			} else {
				var cboSvcLevel1Value = obj.find(".cboSvcLevel1").val();
				//each Category not same Apply All Category
				if (categoryApplyAllCbo!=cboSvcLevel1Value) {
					changeSvcLevel1Info(obj.get(0), categoryApplyAllCbo, serviceApplyAllCbo, categoryApplyAllChk);
				} else {
					cboSvcLevel2.val(serviceApplyAllCbo);
					cboSvcLevel2.attr("disabled", categoryApplyAllChk);
				}
			}
		} else {
			//
			changeSvcLevel1Info(obj.get(0), categoryApplyAllCbo, serviceApplyAllCbo, categoryApplyAllChk);
		}
	}
	if("serviceApplyAllCboChange"==typeEvt) {
		cboSvcLevel2.val(serviceApplyAllCbo);
		cboSvcLevel2.attr("disabled", categoryApplyAllChk);
	}
	cboSvcLevel1.val(categoryApplyAllCbo);
	cboSvcLevel1.attr("disabled", categoryApplyAllChk);
	//set lineDesc
	var svcLevel2Value=getlineDesc(serviceApplyAllCbo,"svcLevel2");
	obj.find(".clonePlanDetail").each(function(item){
		var lineDesc=$(this).find("div.lineDesc");
		lineDesc.find("#lineDescVal").text(svcLevel2Value+"-");
	});
}

function JobNoAllChkEvt(type,obj){
	//Get Service
	var service=$(obj).closest("div.service_object");
	
	//Jbo ComboBox change
	if(type=="JobNoAllJboChange") {
		var jobNoAllChkObj=service.find(".jobNoAllChk");
		var jobNoAllChk = jobNoAllChkObj.is(':checked');
		if(jobNoAllChk){
			JobApplyAll(service);
		}
	}else{
		//Jbo Check Box click
		if(type!=""){
			JobApplyAll(service);
		}else{
		    // page onload
			var jobNoAllChkObj=service.find(".jobNoAllChk");
			var jobNoAllChk = jobNoAllChkObj.is(':checked');
			if(jobNoAllChk){
				JobApplyAll(service);
			}
		}
	}
	//set billDesc
	var jobNoAllChk = service.find(".jobNoAllChk").is(':checked');
	var jobNoAllJob = service.find(".jobNoAllJob").val();
	var billDesc= service.find(".billDesc");
	var jobButtonSer=service.find(".ui-button").eq(0);
	var jobNoSer = service.find(".custom-combobox-input").eq(0);
	var jobNoAllJobVal=service.find('.jobNoAllJob option:selected').text();
	var multiPln = $("#multiPln").val();
	if(multiPln == "1"){
		billDesc.text("Job No.: "+jobNoAllJobVal);
	}
	if(jobNoAllChk){
		jobButtonSer.attr("disabled",false);
		jobNoSer.attr("disabled",false);
	}else{
		jobButtonSer.attr("disabled",true);
		jobNoSer.attr("disabled",true);
	}
}

function JobApplyAll(service) {
	var subplanGroup = service.find(".subPlan");
	for(var i = 0; i < subplanGroup.length; i++) {
		var subplan = subplanGroup.eq(i);
		setJobApplyAll(subplan);
	}
}

function setJobApplyAll(subplan){
	var service=subplan.closest("div.service_object");
	var jobNoAllChk = service.find(".jobNoAllChk").is(':checked');
	var jobNoAllJobText=service.find('.jobNoAllJob option:selected').text();
	var jobNoAllJobVal=service.find('.jobNoAllJob option:selected').val();
	var jobButtonSer=service.find(".ui-button").eq(0);
	var jobNoSer = service.find(".custom-combobox-input").eq(0);
	//jobNo
	var jobNo=subplan.find(".subPlanContent tr").eq(0).children().eq(3).find(".custom-combobox-input");
	var jobbutton=subplan.find(".subPlanContent tr").eq(0).children().eq(3).find(".ui-button");
	var jobNoSel = subplan.find("select.jobNo");
	var jobChk =subplan.find(".isDisplayJobNo");
	if(jobNoAllChk){
		jobNo.val(jobNoAllJobText);
		jobNoSel.val(jobNoAllJobVal);
		//set jobNoAllChk status
		jobButtonSer.attr("disabled",false);
		jobNoSer.attr("disabled",false);
	}
	else{
		//set jobNoAllChk status
		jobButtonSer.attr("disabled",true);
		jobNoSer.attr("disabled",true);
	}
	if(service.find(".jobNoAllChk").length>0){
		//set jobNo combobox status
		jobNo.attr("disabled", jobNoAllChk);
		jobbutton.attr("disabled", jobNoAllChk);
		//set jobNo Select status
		jobChk.attr("disabled", jobNoAllChk);
		jobNoSel.attr("disabled", jobNoAllChk);
	}
	//New mode,when jobNo is not 'display',then excute combobox()
	if($("#action").val()=="new"){
		//jobNoAll not checked
		if(!jobNoAllChk){
			if(jobNoSel.css("display")=="none"){
			}else{
				//jobNo 'display !="none"' then combobox
				//jobNoSel.append( "<option value=\""+ "add" +"\">"+"-----add one-----"+"</option>");
				jobNoSel.combobox();
				subplan.find(".subPlanContent tr").eq(0).children().eq(3).css("padding-right",27+"px");
			}
		}
	}
}

function JobNoAllChkInit(){
	var services = $("div.service_object");
	for ( var i = 0; i < services.length; i++) {
		if(services.eq(i).css("display")=="none"){
		}
		else{
			var service = services.eq(i);
			var jobNoAllChk = service.find(".jobNoAllChk").is(':checked');
			if (jobNoAllChk) {
				JobApplyAll(service);
			}else{
				if($("#action").val()=="new"){
					var jobButtonSer=service.find(".ui-button").eq(0);
					var jobNoSer = service.find(".custom-combobox-input").eq(0);
					jobButtonSer.attr("disabled",true);
					jobNoSer.attr("disabled",true);
				}
			}
		}
	}
}

function categoryApplyAllCboEvt(obj, type, serviceApplyAllCboHid, typeEvt) {
	displayOption($("#serviceApplyAllCbo"), $(obj).val(), "svcLevel2");
	//type is 1:categoryApplyAllCbo combo Box change, type is 2:page onload
	if("2"==type) {
		$("#serviceApplyAllCbo").val(serviceApplyAllCboHid);
	}
	categoryApplyAllEvt('', typeEvt);
}

function changeSvcLevel1Info(obj, categoryApplyAllCbo, serviceApplyAllCbo, categoryApplyAllChk) {
	var subPlan = $(obj).closest("div.subPlan");
	var svcGrp = categoryApplyAllCbo;
	var svcLevel2Obj = subPlan.find(".svcLevel2");
	displayOption(svcLevel2Obj, svcGrp, "svcLevel2");
	var subPlanDetail3 = subPlan.find(".svcLevel3");
	for ( var i = 0; i < subPlanDetail3.length; i++) {
		var detail = subPlanDetail3.eq(i);
		displayOption(detail, svcGrp, "svcLevel3");
	}
	var subPlanDetail4 = subPlan.find(".svcLevel4");
	for ( var j = 0; j < subPlanDetail4.length; j++) {
		var detail4 = subPlanDetail4.eq(j);
		displayOption(detail4, svcGrp, "svcLevel4");
	}
	subPlan.find(".svcLevel2").val(serviceApplyAllCbo);
	subPlan.find(".svcLevel2").attr("disabled", categoryApplyAllChk);
}

function updateAmount_enter(obj,evt){
	var e = evt;
	if(window.event){ // IE
		var charCode = e.keyCode;
	} else if (e.which) { // Safari 4, Firefox 3.0.4
		var charCode = e.which;
	}
	if(charCode == 13){
		var va = $.trim(obj.value);
		if(va == ''){
			obj.value = 0;
		}
		updateAmount(obj);
	}
}

function setPlanDetailOptionInfo(idSvcLevel,key,value,svcgrpval,abbr){
	$("div.subPlan").each(function(index){
		var details=$(this).find(".clonePlanDetail");
		var svcLevel1=$(this).find('.svcLevel1 option:selected').val()||$(this).find('.svcLevel1').val();
		details.each(function(i){
			if(svcLevel1==svcgrpval){
				var svcLeve3=$(this).find(".svcLevel3");
				var option = $("<option value=\""+ key +"\" svcgrp=\""+svcgrpval+"\" gstValue=\"\" descAbbr=\""+abbr+"\" >"+value+"</option>");
				option.insertBefore(svcLeve3.find("option:eq(1)"));
			}
		});
	});
	globalSel.find("option:eq(1)").prop('selected',true);
	globalInput.val(value);
	var descAbbr=abbr;
	appendSvcLevel3ChangeEvt(globalSel,descAbbr);
	var scvLevelObj =  $("#svcLevelGroup").find("." + idSvcLevel);
	scvLevelObj.append( "<option value=\""+ key +"\" svcgrp=\""+svcgrpval+"\" gstValue=\"\" descAbbr=\""+abbr+"\" >"+value+"</option>");
}

//AppendSvcLevel3ChangeEvt
function appendSvcLevel3ChangeEvt(obj,descAbbr){
	var subPlan = $(obj).closest("div.subPlan");
	//get svcLevel2 Value
	var svcIdService2=subPlan.find(".svcLevel2").val();
	var svcLevel2Value=getlineDesc(svcIdService2,"svcLevel2");
	//set lineDesc Value
	var subPlanDetails = $(obj).closest("tr.clonePlanDetail");
	var lineDesc=subPlanDetails.find("#lineDescVal");
	lineDesc.text(svcLevel2Value+"-"+descAbbr);
}

//Get jobNoOption info from the parent screen 'M_JNMS02'
function setJobNoOptionInfo(optionval){
	var modelFlg = $("#action").val();
	// Only new mode,then Add jobNo Option
	if (modelFlg=="new") {
		$("div.service_object").each(function(index){
			var jobNoAll=$(this).find(".jobNoAllJob");
			var option = $("<option value=\""+ optionval +"\" >"+optionval+"</option>");
			option.insertBefore(jobNoAll.find("option:eq(1)"));
		});
	}
	//Add the new option in each subplan
	$("div.subPlan").each(function(index){
		var jobNo=$(this).find(".jobNo");
		var option = $("<option value=\""+ optionval +"\" >"+optionval+"</option>");
		option.insertBefore(jobNo.find("option:eq(1)"));
	});
	//set Global variable
	globalSel.find("option:eq(1)").prop('selected',true);
	globalInput.val(optionval);
	//set billDesc
	if(modelFlg=="new"){
		var service=globalSel.closest("div.service_object");
		var jobNoAllChk = service.find(".jobNoAllChk").is(':checked');
		var billDesc= service.find(".billDesc");
		var multiPln = $("#multiPln").val();
		if(jobNoAllChk){
			if(multiPln == "1"){
				billDesc.text("Job No.: "+optionval);
			}
		}
		JobApplyAll(service);
	}
}

/**
 * 
 * @param obj
 * @return
 */
function addExistingSubPlanOptionService(obj) {
	//open B_BIL_S05 page
	var idCust = $('#idCustomer').val();
	var customertype = $("#customerType").val(); 
	var service = $(obj).closest("div.service_object");
	openB_BIL_S04(idCust,"",service);
}

/**
 * open B_BIL_S04 page
 * @param idCust
 * @param cboBillAcc
 */
function openB_BIL_S04(idCust,cboBillAcc,service) {
	var path = $("#hiddenContextPath").val();
	var url = path+"/B_BIL/B_BIL_S04CustPlanSearch.do?idCust="+idCust+"&billAcc="+cboBillAcc+"&isCpm=1";
	var width = window.screen.width*80/100;;
    var height = window.screen.height*80/100;;
    var left = Number((screen.availWidth/2) - (width/2));
    var top = Number((screen.availHeight/2) - (height/2));
    var offsetFeatures = "width=" + width + ",height=" + height +
    					 ",left=" + left + ",top=" + top +
    					 "screenX=" + left + ",screenY=" + top;
	var strFeatures= "toolbar=no, status=no, menubar=no,location=no,scrollbars=yes, resizable=no" + "," + offsetFeatures;	 	
	var newWindow = window.open(url, null, strFeatures);
	window.service = service;
	newWindow.service = service;
	newWindow.focus();
}

/**
 * B_BIL_S04 page return call
 * @param idCustPlanGrps service id
 * @param idCustPlanLink sub plan id
 */
function B_BIL_S04ReturnCalled(idCustPlanGrps, idCustPlanLinks){
	//ajax to call new sub plan group
	var service = window.service;
	var idCust = $("#idCustomer").val();
	var serviceMultiPln = service.find(".serviceMultiPln").val();
	//'new' mode,init jobAllChk & jobAllVal
	var jobNoAllChk="";
	var jobNoAllVal="";
	var modelFlg = $("#action").val();
	//set value for jobAllChk & jobAllVal
	if (modelFlg=="new") {
		var jobNoAllJobChk = service.find(".jobNoAllChk").is(':checked');
		var jobNoAllJobText=service.find('.jobNoAllJob option:selected').text();
		var jobNoAllJobVal=service.find('.jobNoAllJob option:selected').val();
		if(jobNoAllJobChk){
			jobNoAllChk="allchk";
			jobNoAllVal=jobNoAllJobVal;
		}
	}
	$.ajax({
		type: "POST",
		url: getUrl(""),
		data: {
		"event": FORWARD_LOAD_EXIST_SUB_PLAN,
		"idPlanGrpList": idCustPlanGrps,
		"idCustPlanLinks":idCustPlanLinks,
		"idCust": idCust,
		"serviceMultiPln":serviceMultiPln,
		"jobNoAllChk":jobNoAllChk,
		"jobNoAllVal":jobNoAllVal
		},
		success: function(html) { 
			var content = document.createElement("div");
			content.innerHTML = html;
			var result = $(content);
			
			//add new object to sub plan group
			var subPlans = result.find(".subPlanGroup").find(".subPlan");
			var subPlanGrp = service.find("td.subplan");
			for ( var i = 0; i < subPlans.length; i++) {
				var subPlan = subPlans.eq(i);
				addStarndardPlan(service,subPlan, subPlanGrp);
			}
			
			//add new option service
			var optionService = result.find(".optionServiceGroup").find(".subPlan");
			var optionServiceGrp = service.find("td.option_service");
			for ( var j = 0; j < optionService.length; j++) {
				var subPlan = optionService.eq(j);
				addStarndardPlan(service,optionService.eq(j), optionServiceGrp);
			}			
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			//error handler
			
		}
	});
}

function changeRateType(obj){
	var thisRateType = $(obj);
	var rateMode = thisRateType.val();
	var idCust = $("#idCustomer").val();
	var path = $("#hiddenContextPath").val();
	var subPlans = $(".subPlan");
	var url = path+'/B_CPM/B_CPM_S02RateType2ChangeAjax.do?rateMode='+rateMode+"&idCust="+idCust;
	$.ajax({
        type: 'POST',
        url: url,
        success: function(result){
        	//alert(result);
        	thisRateType.closest(".subPlan").find(".rateType2").val(result);
        }
      });

}

function displayPostpaid(obj){
	var services = $(".service_object");
	var disBillingOption = $("#disBillingOption").val();
	for ( var i = 0; i < services.length; i++) {
		var service = services.eq(i);
		var billingStatus = service.find(".billingStatus").val();
		var billingOptions = service.find(".billingOption");
		if (disBillingOption == "1") {
			service.find("#trBillingOption").attr("style", "display:inline");
			if (billingStatus == "BS0" || billingStatus == "BS1") {
				var selectVal = $(obj).val();
				if ('6' == selectVal) {
					billingOptions.eq(0).attr("checked","checked");
					billingOptions.eq(1).removeAttr("checked");
					billingOptions.eq(0).click();
					billingOptions.eq(1).attr('disabled', true);
				} else {
					service.find(".billingOption").attr('disabled', false);
				}
			}else {
				billingOptions.eq(0).attr('disabled', true);
				billingOptions.eq(1).attr('disabled', true);
			}
		}else {
			service.find("#trBillingOption").attr("style", "display:none");
		}
	}
}



function onChangeGSTValue(obj){
	var service = $(obj).closest("tr.subPlanDetailResult");
	service.find("select.applyGstValue").attr('value',obj.value);
}