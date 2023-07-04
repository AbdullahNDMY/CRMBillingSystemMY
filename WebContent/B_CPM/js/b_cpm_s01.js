var FORWARD_SEARCH="forward_search";
var FORWARD_EXPORT="forward_exportCSV";
var FORWARD_NEW_CUSTOMER="forward_newCustomer";
var FORWARD_VIEW_CUSTOMER="forward_viewCustomer";
var FORWARD_NEW_CUSTOMERPLAN="forward_newCustomerPlan";
var FORWARD_VIEW_CUSTOMERPLAN="forward_viewCustomerPlan";
var FORWARD_VIEW_SUBSCRIPTION_INFO="forward_viewSubscriptionInfo";
var FORWARD_VIEW_BILLING_ACOUNT="forward_viewBillingAccount";
var SEARCH_BY_BUTTON = "button";
var SEARCH_BY_PAGELINK = "link";

$(function(){
	//init page link
	initPageLinks(".pageLink", FORWARD_SEARCH);
	
	//setting check box
	settingCheckbox($(".searchServiceStatus"), $(".hidServStatus"));
	settingCheckbox($(".searchTransType"), $(".hidTransType"));
	settingCheckbox($(".searchBillingStatus"), $(".hidBillStatus"));
	settingCheckbox($(".searchBillingInstructions"), $(".hidBillingInstructions"));
	// #189 Start
	settingCheckbox($(".searchBillingOption"), $(".hidBillingOption"));
	// #189 End
	
	//setting date control
	$(".searchServiceDateStartFrom").attr("readonly", "readonly");
	$(".searchServiceDateStartTo").attr("readonly", "readonly");
	$(".searchServiceDateEndFrom").attr("readonly", "readonly");
	$(".searchServiceDateEndTo").attr("readonly", "readonly");
	
	//update cbo status
	// #180 Start
	$(".searchService").combobox();
    $(".searchPlan").combobox();
    $(".searchPlanDetail").combobox();
    
	changeCategory();
	if ($("#hiddenService").val() != "") {
		$("#combInput2").val($("#hiddenService").val());
	}
	if ($("#hiddenPlan").val() != "") {
		$("#combInput3").val($("#hiddenPlan").val());
	}
	if ($("#hiddenPlanDetail").val() != "") {
		$("#combInput4").val($("#hiddenPlanDetail").val());
	}
	// #180 End
	
	//Toggle place, toggle click in column description
	var hasSubPlans = $('#tableResultSearchPlan .hasSubPlan');
	//Insert bottom border for record has subplans
	//trResultSearchPlans.find('tr.hasSubPlan td').attr('style','border-bottom:1px solid #d0d0d0');
	for(var i = 0; i < hasSubPlans.length;i++) {
		var toggleClick = hasSubPlans.eq(i).find('.spanToggleClick');
		initToggle(toggleClick,hasSubPlans.eq(i));
		hideJQueryDetail(hasSubPlans.eq(i));
	}
});

function initLink(link,tablePageLink,forward) {
	//save startindex
	var startIndex = getStartIndexFromHRef(link.attr('href'));
	link.append('<input type="hidden" value="' + startIndex + '"/>');
	//remove hereft from link
	link.attr('href','JavaScript:void(0);');
	link.click(function(){
		var startIndex = link.find('input[type="hidden"]').val();
		tablePageLink.find('.hiddenStartIndex').val(startIndex);
		tablePageLink.find('.hiddenSearchBy').val(SEARCH_BY_PAGELINK);
		submitForm(forward);
	});
}

/**
 * 
 * @param href
 * @return
 */
function getStartIndexFromHRef(href) {
	var last = parseInt(href.lastIndexOf('=')) + 1;
	var length = href.length;
	var startIndex = href.substr(last,length - last);
	startIndex = jQuery.trim(startIndex);
	return startIndex;
}

/**
 * 
 * @param toggleClick
 * @param container
 * @return
 */
function initToggle(toggleClick, container) {
	toggleClick.click(function(){
		toggleJQueryDetail(container);
		if(container.find('.spanTogglePlace').css('display') == 'none') { 
			//container.find('tr.hasSubPlan td').attr('style','border-bottom:1px solid #d0d0d0');
		} else { 											
			//Remove bottom border when subplans are showed
			container.find('tr.hasSubPlan td').attr('style','border-bottom:0px');
		}
	});

}

/**
 * 
 * @param parent
 * @return
 */
function showJQueryDetail(parent){
	var imgExpend = '<img src="' + $('#hiddenContextPath').val()  + '/image/expend.PNG"/>';
	//hide the togglePlace
	var item = parent.next();
	while(item.attr('class') == "spanTogglePlace") {
		item.css('display','');
		item = item.next();
	}
	//parent.find('.spanTogglePlace').css('display','');
	//insert image to clickPlace
	$(imgExpend).prependTo(parent.find('.spanToggleClick'));
}

/**
 * 
 * @param parent
 * @return
 */
function hideJQueryDetail(parent){
	var imgColapse = '<img src="' + $('#hiddenContextPath').val()  + '/image/colapse.PNG"/>';
	//hide the togglePlace
	var item = parent.next();
	while(item.attr('class') == "spanTogglePlace") {
		item.css('display','none');
		item = item.next();
	}
	//insert image to clickPlace
	$(imgColapse).prependTo(parent.find('.spanToggleClick'));
}

/**
 * 
 * @param parent
 * @return
 */
function toggleJQueryDetail(parent){
	//Remove the existing image which will be changed after toggle
	
	parent.find('.spanToggleClick img').remove();
	if(parent.next('.spanTogglePlace').css('display') == 'none') { 
		//if togglePlace is being hiden then show it
		showJQueryDetail(parent);
	} else { 												
		//if togglePlace is being showed then hide it
		hideJQueryDetail(parent);
	}
}

/**
 * 
 * @param controlList
 * @param valueList
 * @return
 */
function settingCheckbox(controlList, valueList) {
	for ( var i = 0; i < controlList.length; i++) {
		var control = controlList.eq(i);
		for ( var j = 0; j < valueList.length; j++) {
			if (control.val() == valueList.eq(j).val()) {
				control.attr("checked", true);
				break;
			}
		}
	}
}

//onClick for button reset plan information
function onClickResetPlan(){
	
	$(".searchCustomerId").val("");
	$(".searchCustomerName").val("");
	$(".searchCustomerType").val("");
	
	$(".searchCategory").val("");
	$(".searchService").val("");
	$(".searchPlan").val("");
	$(".searchPlanDetail").val("");
	
	$(".searchSubscriptionId").val("");
	$(".searchBillingAccount").val("");
	$(".searchBillingInstruction").val("");
	$(".searchServiceDateStartFrom").val("");
	$(".searchServiceDateStartTo").val("");
	$(".searchServiceDateEndFrom").val("");
	$(".searchServiceDateEndTo").val("");
	$(".contractStartFrom").val("");
	$(".contractStartTo").val("");
	$(".contractEndFrom").val("");
	$(".contractEndTo").val("");
	$(".applicationDateFrom").val("");
	$(".applicationDateTo").val("");
	//$(".searchContractTermType[value='M']").attr('checked',true);
	//$(".searchContractTerm").val("");
	
	var serviceStatus = $(".searchServiceStatus");
	for(var i = 0; i < serviceStatus.length; i++) {
		serviceStatus.eq(i).attr('checked',false);
	}
	
	var transType = $(".searchTransType");
	for(var j = 0; j < transType.length; j++) {
		transType.eq(j).attr('checked',false);
	}
	
	var billingStatus = $(".searchBillingStatus");
	for(var j = 0; j < billingStatus.length; j++) {
		billingStatus.eq(j).attr('checked',false);
	}
	
	// #189 Start
	var billingOption = $(".searchBillingOption");
	for(var j = 0; j < billingOption.length; j++) {
		billingOption.eq(j).attr('checked',false);
	}
	// #189 End
	
	var billingInstructions = $(".searchBillingInstructions");
	for(var j = 0; j < billingInstructions.length; j++) {
		billingInstructions.eq(j).attr('checked',false);
	}
	
	//test B-CPM-S05
	//window.open("/billingsystem/B_CPM/B_CPM_S05DSP.do?customerPlan.idCustPlan=62&idScreen=BIF&event=forward_viewCustomerPlan");
}

/**
 * 
 * @return
 */
function onClickResetGenerateBIF(){
	$(".customerId").val("");
	$(".tranType").val("");
	$(".billCurrency").val("");
}

 /**
  * 
  * @return
  */
// #186 Start
function exportCSV(searchCount, exportLimit) {
	if (parseInt(searchCount) >= parseInt(exportLimit)) {
		var warnMsg = "Total results to be exported exceed export limit " + exportLimit;
		alert(warnMsg);
	} else {
		submitForm(FORWARD_EXPORT);
	}
}
//#186 End
/**
 * 
 * @return
 */
function search() {
	if(document.getElementById("plan_startIndex").value != 0){
		document.getElementById("plan_startIndex").value = 0;
	}
	// #180 Start
	if ($(".searchCategory").val() != "") {
		var inputBlank = "- Please Select One -";
		if ($("#combInput2").val() != inputBlank) {
			$("#hiddenService").val($("#combInput2").val());
		}else {
			$("#hiddenService").val("");
		}
		if ($("#combInput3").val() != inputBlank) {
			$("#hiddenPlan").val($("#combInput3").val());
		}else {
			$("#hiddenPlan").val("");
		}
		if ($("#combInput4").val() != inputBlank) {
			$("#hiddenPlanDetail").val($("#combInput4").val());
		}else {
			$("#hiddenPlanDetail").val("");
		}
	}
	// #180 End
	submitForm(FORWARD_SEARCH);
}

/**
 * 
 * @param idCustPlan
 * @return
 */
function viewCustomerPlan(idCustPlan) {
	$("#idCustPlan").val(idCustPlan);
	submitForm(FORWARD_VIEW_CUSTOMERPLAN);
}

 /**
  * 
  * @param idCustPlan
  * @param idCust
  * @param idSubInfo
  * @return
  */
function viewSubScriptionInfo(idCustPlan, idCust, idSubInfo) {
	var queryString = "&event=" + FORWARD_VIEW_SUBSCRIPTION_INFO + 
						"&isPopUp=" + 1 + 
						"&customerPlanID=" + idCustPlan + 
						"&subscriptionID=" + idSubInfo +
						"&customerID=" + idCust + 
						"&fromPopup=" + "appearPopup";
	var url = getUrl(queryString);
	var newWindow = window.open(url,'name',POPUP_FEATURE_STD);
	newWindow.focus();
}

/**
 * 
 * @param idCust
 * @return
 */
function viewCustomer(idCust, customerType) {
	
	var logicName = "";
	if (customerType == "CORP") {
	  	logicName = "M_CSTR02BLogic.do";
	}
	else if (customerType == "CONS"){
	  	logicName = "M_CSTR07BLogic.do";
	}
	window.location = $("#hiddenContextPath").val() + "/M_CST/" + logicName + "?" + 
						"&id_cust=" + idCust +
						"&mode=" + "READONLY" +
						"&previous=" + "B-CPM" + "";
}
			  								 

/**
 * 
 * @param idCustPlan
 * @param idCust
 * @param bacNo
 * @return
 */
function viewBAC(idCustPlan, idCust, bacNo) {
	var queryString = $("#hiddenContextPath").val() + "/B_BAC/RP_B_BAC_S02_02_01BL.do?" + 
						"&idCustPlan=" + idCustPlan +
						"&idBillAccount=" + bacNo +
						"&idCust=" + idCust;
	var newWindow = window.open(queryString,'name',POPUP_FEATURE_STD);
	newWindow.focus();
}

/**
 * 
 * @param type
 * @param key
 * @return
 */
function gotoScreen(type, key) {
	switch(type) {
		case "newCust": 
			window.location = $("#hiddenContextPath").val() + "/M_CST/M_CSTR02BLogic.do?mode=NEWMODE";
			break;
		case "newCustPlan":
			submitForm(FORWARD_NEW_CUSTOMERPLAN);
			break;
		case "viewCustPlan":
			submitForm(FORWARD_VIEW_CUSTOMERPLAN);
			break;
		case "billAcc": break;
		case "plan": break;
		case "viewCustomer":
			//popup view customer

			break;
	}
}

/**
 * 
 * @return
 */
function changeCategory() {
	$(".custom-combobox-input").val("- Please Select One -");
	var value = $(".searchCategory").val();
	if (value != "") {
		// #180 Start
		$(".custom-combobox-input").attr("disabled", false);
		$(".ui-button").attr("disabled", false);
		// #180 End
		var objLevel = $(".searchService");
		var selectedValue = objLevel.val();
		displayOption(objLevel, value, "svcLevel2");
		objLevel = $(".searchService");
		objLevel.val(selectedValue);
		objLevel.attr("disabled", false);
		
		objLevel = $(".searchPlan");
		selectedValue = objLevel.val();
		displayOption(objLevel, value, "svcLevel3");
		objLevel = $(".searchPlan");
		objLevel.val(selectedValue);
		objLevel.attr("disabled", false);
		
		objLevel = $(".searchPlanDetail");
		selectedValue = objLevel.val();
		displayOption(objLevel, value, "svcLevel4");
		objLevel = $(".searchPlanDetail");
		objLevel.val(selectedValue);
		objLevel.attr("disabled", false);
		
	} else {
		$(".searchService").attr("disabled", true);
		$(".searchPlan").attr("disabled", true);
		$(".searchPlanDetail").attr("disabled", true);
		// #180 Start
		$(".custom-combobox-input").attr("disabled", true);
		$(".ui-button").attr("disabled", true);
		// #180 End
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
	var ctr2 = $(ctr).closest("td").find("input." + ctr2Name);
	if (ctr2[0] != undefined && ctr2[0] != null) {
		// #208 Start
		//if (ctr2.attr("disabled") == false) {
		if (ctr2[0].disabled == false) {
		// #208 End	
			jscalendarPopUpCalendar(ctr,ctr2[0],format);
		}
	}
}
 
/**
 * 
 * @param obj
 * @return
 */
function removeAllOption(obj) {
 	obj.val("");
 	var optionList = obj.find("option");
 	// #180 Start
	for ( var i = 1; i < optionList.length; i++) {
	// #180 End
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
 	//save selected value
	var selectedValue = obj.val();
	removeAllOption(obj);
	//add valid option for object
	var cloneObj =  $("#svcLevelGroup").find("." + classObject).clone();
	// #180 Start
	//var copyCbo = obj.clone();
	//obj.replaceWith(copyCbo);
	// #180 End
	var optionList = cloneObj.find("option");
	for ( var i = 0; i < optionList.length; i++) {
		var option = optionList.eq(i);
		if (option.val() != "" && option.attr("svcGrp") == svcGrp) {
			obj.append(option);
			
			// #180 Start
			if (option.val() == selectedValue) {
				if (classObject == "svcLevel2") {
					$("#combInput2").val(option[0].innerText);
				}else if (classObject == "svcLevel3") {
					$("#combInput3").val(option[0].innerText);
				}else {
					$("#combInput4").val(option[0].innerText);
				}
			}
			// #180 End
	 	}
 	}
}