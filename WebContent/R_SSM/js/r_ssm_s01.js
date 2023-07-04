var FORWARD_SEARCH="forward_search";
var FORWARD_VIEW_CUSTOMER="forward_viewCustomer";
var FORWARD_VIEW_CUSTOMERPLAN="forward_viewCustomerPlan";
var FORWARD_VIEW_SUBSCRIPTION_INFO="forward_viewSubscriptionInfo";
var POPUP_FEATURE_STD = "height=600,width=800,resizable=1,scrollbars=1";

$(function(){

    $(".chkErrorCodeStyle").click(function() {
        var checkedErrorCode = $(".chkErrorCodeStyle:checked");
        //alert(listIdPlanGrp.length);
        var allErrorCode = $(".chkErrorCodeStyle");
        //alert(idPlanGrp.length);
        if(checkedErrorCode.length<allErrorCode.length){
            $(".chkErrorCodeAllStyle").attr("checked", false);
        } else {
            $(".chkErrorCodeAllStyle").attr("checked", true);
        }
    });

    $(".chkErrorCodeAllStyle").click(function() {
        if($(".chkErrorCodeAllStyle").is(':checked')){
            $(".chkErrorCodeStyle").attr('checked', true);
        }else{
            $(".chkErrorCodeStyle").attr('checked', false);
        }
    });

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

/**
 * Redirect to Customer View Screen.
 * 
 * @param idCust: Customer ID
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
 * Open SSM View Screen.
 * 
 * @param idCustPlan: Customer Plan ID
 * @param idCust: Customer ID
 * @param idSubInfo: Subscription ID
 * @return
 */
function viewSubScriptionInfo(idCustPlan, idCust, idSubInfo) {
   var url = $("#hiddenContextPath").val() + 
           "/B_SSM_S01/B_SSM_S01_SubInfo_Displaying_Action.do?" +
           "&isPopUp=" + 1 + 
           "&customerPlanID=" + idCustPlan + 
           "&subscriptionID=" + idSubInfo +
           "&customerID=" + idCust + 
           "&fromPopup=" + "appearPopup";
       
   var newWindow = window.open(url,'name',POPUP_FEATURE_STD);
   newWindow.focus();
}

/**
 * Redirect to Customer Plan View Screen.
 * 
 * @param idCustPlan
 * @return
 */
function viewCustomerPlan(idCustPlan) {
    window.location = $("#hiddenContextPath").val() + 
            "/B_CPM/B_CPM_S02ViewBL.do?customerPlan.idCustPlan=" + idCustPlan;
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
