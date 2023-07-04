var FORWARD_SEARCH="forward_search";
var FORWARD_VIEW_SUBSCRIPTION_INFO="forward_viewSubscriptionInfo";
var FORWARD_VIEW_CUSTOMERPLAN="forward_viewCustomerPlan";
var SEARCH_BY_PAGELINK = "link";
var FORWARD_GENERATEBIF = "forward_generateBIF";

$(function(){
    //init page link
    initPageLinks(".pageLink", FORWARD_SEARCH);
    
    //Toggle place, toggle click in column description
    var hasSubPlans = $('#tableResultSearchPlan .hasSubPlan');
    //Insert bottom border for record has subplans
    //trResultSearchPlans.find('tr.hasSubPlan td').attr('style','border-bottom:1px solid #d0d0d0');
    for(var i = 0; i < hasSubPlans.length;i++) {
        var toggleClick = hasSubPlans.eq(i).find('.spanToggleClick');
        initToggle(toggleClick,hasSubPlans.eq(i));
        hideJQueryDetail(hasSubPlans.eq(i));
    }
    
    $('#btnGetCustomerInfo').click(function(){
        var contextpath = $('#hiddenContextPath').val();
        var url = contextpath + "/M_CST/M_CSTS04SearchBLogic.do";
        var popupWidth = window.screen.width*80/100;
        var popupHeight = window.screen.height*80/100;
        var left = Number((screen.availWidth/2) - (popupWidth/2));
        var top = Number((screen.availHeight/2) - (popupHeight/2));
        var newWindow = window.open(url,'B_CPM_S04', POPUP_FEATURE.replace("#width#",popupWidth).replace("#height#",popupHeight).replace("#left#",left).replace("#top#",top));
        if (window.focus) { newWindow.focus(); }
    });
    
    checkAllCheckBox();
});

function initLink(link,tablePageLink,forward) {
	//save startindex
	var startIndex = getStartIndexFromHRef(link.attr('href'));
	link.append('<input type="hidden" value="' + startIndex + '"/>');
	//remove hereft from link
	link.attr('href','JavaScript:void(0);');
	link.click(function(){
		$("#actionFlg").val("search");
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

//M_CST_S04 page selected one record and closed window
function setCustomerInfo(customerName,customerId,customerType){
    $("#customerNameDiv").html(customerName);
    $("#customerIdDiv").html(customerId);
    $("#customerIdVal").val(customerId);
    $("#customerNameVal").val(customerType);
}

function doSearch(){
    var customerIdVal=$("#customerIdVal").val();
    if(customerIdVal==null || customerIdVal=="")
        new messageBox().POPALT("Customer Name : Selection Error. Please select one.");
    else {
    	$("#totalcheck").attr("checked", false);
    	$("#actionFlg").val("search");
        if(document.getElementById("plan_startIndex").value != 0){
            document.getElementById("plan_startIndex").value = 0;
        }
        submitForm(FORWARD_SEARCH);
    }
}

function checkAll(obj){
	$(".idCustPlansChk").attr('checked', obj.checked);
}

function checkAllCheckBox(){
	var listIdPlanGrp = $(".idCustPlansChk:checked");
	//alert(listIdPlanGrp.length);
	var idPlanGrp = $(".idCustPlansChk");
	//alert(idPlanGrp.length);
	if(listIdPlanGrp.length<idPlanGrp.length){
		$("#totalcheck").attr("checked", false);
	}else if(idPlanGrp.length>0){
		$("#totalcheck").attr("checked", true);
	}
}

/**
 * 
 * @return
 */
function onClickResetGenerateBIF(){
    var idScreen = $("#idScreen").val();
    var customerId = $("#customerIdVal").val();
    window.location = $("#hiddenContextPath").val() 
                      + "/B_CPM/B_CPM_S04InitBL.do?inputSearchPlan.customerId=" + customerId
                      + "&idScreen=" + idScreen;
}

function generateBIF(){
	$("#actionFlg").val("generateBIF");
	submitForm(FORWARD_GENERATEBIF);
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