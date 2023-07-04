// fix trim() method for IE8
if(typeof String.prototype.trim !== 'function') {
  String.prototype.trim = function() {
    return this.replace(/^\s+|\s+$/g, ''); 
  }
}
/**************************/
var TYPE_SUBPLAN = "S";
var TYPE_OPTION_SERVICE = "O";
var BLANK = '&nbsp;';
var ADD_TEXT = 'Add';
var ADD_LINK = '<a href="javascript:void(0);" onclick="addServiceDetail(this);">Add</a>';

function addSubPlan() {
	var e = $("#serviceClone").clone(true);
	e.find(".remove").html(BLANK);
	appendSubPlan(e);
	
	var serviceDetail = $('#serviceDetailClone').clone();
	serviceDetail.find(".remove").html("<img src='"+getContextPath()+"/image/delete.gif' onclick='removeServiceDetail(this);'/>");
	serviceDetail.removeAttr('id');
	serviceDetail.appendTo(e.find(".serviceDetail"));
	
	var modelFlg = $("#modelFlg").val();
	if(modelFlg=="new") {
		setGSTApplyAll(e);
		setCategoryApplyAll(e, "");
	}
	//modify for #151 Start
	//e.find(".cboRateType2").find("option[value='A20']").attr("selected",true);	
	var url = getContextPath()+'/M_PPM/M_PPM_S02RateType2ChangeAjax.do?rateMode=M';
	$.ajax({
        type: 'POST',
        url: url,
        success: function(result){
        	e.find(".cboRateType2").find("option[value='" + result + "']").attr("selected",true);
        }
      });
	//modify for #151 End
	e.find(".cboRateType").find("option[value='BA']").attr("selected",true);
	e.find(".cboRateMode").find("option[value='5']").attr("selected",true);
}

function addOptionService() {
	var e = $("#serviceClone").clone(true);
	appendOptionService(e);
	//add only ONE service detail
	var serviceDetail = $('#serviceDetailClone').clone();
	serviceDetail.removeAttr('id');
	serviceDetail.appendTo(e.find(".serviceDetail"));
	
	var ppmOptionSvc = $("#ppmOptionSvc").val();
	if (ppmOptionSvc!="1") {
		serviceDetail.find(".cboPlan").css("display","none");
		serviceDetail.find(".cboPlanDetail").css("display","none");
		serviceDetail.find(".cboVendor").css("display","none");
	}
	
	var modelFlg = $("#modelFlg").val();
	if(modelFlg=="new") {
		setGSTApplyAll(e);
		setCategoryApplyAll(e, "");
	}
	//modify for #151 Start
	//e.find(".cboRateType2").find("option[value='A20']").attr("selected",true);	
	var url = getContextPath()+'/M_PPM/M_PPM_S02RateType2ChangeAjax.do?rateMode=M';
	$.ajax({
        type: 'POST',
        url: url,
        success: function(result){
        	e.find(".cboRateType2").find("option[value='" + result + "']").attr("selected",true);
        }
      });
	//modify for #151 End
	e.find(".cboRateType").find("option[value='BA']").attr("selected",true);
	e.find(".cboRateMode").find("option[value='5']").attr("selected",true);
	

}

function appendSubPlan(subplan) {
	subplan.removeAttr('id');
	subplan.attr("class", "service");
	subplan.appendTo("#subplanGroup");
	subplan.find(".type").val(TYPE_SUBPLAN);
	subplan.find("legend").text($("#subplanLbl").val());
	subplan.find("input[name=removeNotification]").val($("#subplan_removeNotification").val());
	//init Add & Remove hyper link
	subplan.find(".add").html(ADD_TEXT);
	subplan.find("#typeName").html($("#subPlanName").val());
	subplan.show();
}

function appendOptionService(optionservice) {
	optionservice.removeAttr('id');
	optionservice.attr("class", "service");
	optionservice.appendTo("#optionserviceGroup");
	optionservice.find(".type").val(TYPE_OPTION_SERVICE);
	optionservice.find("legend").text($("#optionserviceLbl").val());
	optionservice.find("input[name=removeNotification]").val($("#optionservice_removeNotification").val());
	//init Add & Remove hyper link
	optionservice.find(".add").html(BLANK);
	optionservice.find(".remove").html(BLANK);
	optionservice.find("#typeName").html($("#optionName").val());
	optionservice.show();
}

function removeService(obj) {
	var msg = $(obj.parentNode).find("input[name=removeNotification]").val();
	var MsgBox = new messageBox();
	if (MsgBox.POPCFM(msg) == MsgBox.YES_CONFIRM) {
		var divr = obj.parentNode.parentNode.parentNode.parentNode;
		divr.removeChild(obj.parentNode.parentNode.parentNode);
	}
}

function removeService2(obj) {
	var ERR4SC008 = document.getElementById("ERR4SC008").value;
	var MsgBox = new messageBox();
	if (MsgBox.POPCFM(ERR4SC008) == MsgBox.YES_CONFIRM) {
		var divr = obj.parentNode.parentNode.parentNode.parentNode;
		divr.removeChild(obj.parentNode.parentNode.parentNode);
	}
}
/****************************/
function addServiceDetail(obj) {
	var tblBody = $(obj).closest("table.serviceDetail");
	var serviceDetails = $('#serviceDetailClone').clone();
	serviceDetails.removeAttr('id');
	serviceDetails.appendTo($(tblBody));
	//find and replace new details
	var serviceDiv = $(tblBody).closest("div.service");
	var detail = serviceDiv.find(".cboPlan_PlanDetail");
	if(detail.html() != null) {
		detail.attr("display", "");
		serviceDetails.find(".planDetail").html(detail.html());
		serviceDiv.find(".remove").html('<img src="'+getContextPath()+'/image/delete.gif" onclick="removeServiceDetail(this);"/>');
	}
}

function removeServiceDetail(obj) {
	var parent = obj.parentNode.parentNode.parentNode;
	parent.removeChild(obj.parentNode.parentNode);
}

function resetAllServiceDetail(tbl) {
	var serviceDiv = $(tbl).parent().parent().parent();
	var detail = serviceDiv.find(".cboPlan_PlanDetail");
	if(detail.html() != null) {
		detail.attr("display", "");
		var planDetail = serviceDiv.find(".planDetail");
		for(var i = 0; i < planDetail.length; i++) {
			planDetail.eq(i).html(detail.html());
		}
	}
}

function removeAllServiceDetail(tbl) {
	var serviceDiv = $(tbl).parent().parent().parent();
	var details = serviceDiv.find(".serviceDetailClone");
	details.remove();
}
/*******************************/
function getContextPath() {
	return $("#contextPath").val();
}

function changeCurrency(obj) {
	var currency = obj.options[obj.selectedIndex].value;
	$(".serviceCurrency").html(currency);
}

function changeSvcLevel1(obj) {
	var idService = obj.options[obj.selectedIndex].value;
	var tbl = obj.parentNode.parentNode.parentNode;
	var vObj = obj.value;
	//find the type of Service is Sub Plan or Option Service
	var serviceDiv = $(tbl).parent().parent().parent();
	var type = serviceDiv.find(".type").val();
	//cached
	var selectList_cachedObj = serviceDiv.find(".cboSvcLevel1_cached");
	var selectList_cache = selectList_cachedObj.val();
	//get message notification
	var svcChangedMsg = $("#svcChangedMsg").val();
	//check exist row of subplan
	if(type == TYPE_SUBPLAN) {
		svcChangedMsg = svcChangedMsg.replace("replace", "Sub Plan");
//		if(serviceDiv.find(".planDetail").html() != null) {
//			var MsgBox = new messageBox();
//			if (MsgBox.POPCFM(svcChangedMsg) == MsgBox.YES_CONFIRM) {
//				selectList_cachedObj.val(vObj);
//				changeSvcLevel1Ajax(idService, serviceDiv, tbl, type, vObj);
//			} else {
//				obj.value=selectList_cache;
//			}
//		} else {
//			selectList_cachedObj.val(vObj);
//			changeSvcLevel1Ajax(idService, serviceDiv, tbl, type, vObj);
//		}
		selectList_cachedObj.val(vObj);
		changeSvcLevel1Ajax(idService, serviceDiv, tbl, type, vObj);
	} else {
		var ppmOptionSvc = $("#ppmOptionSvc").val();
		if (ppmOptionSvc=="1") {
//			svcChangedMsg = svcChangedMsg.replace("replace", "Option Service");
//			if(selectList_cache != "") {
//				var MsgBox = new messageBox();
//				if (MsgBox.POPCFM(svcChangedMsg) == MsgBox.YES_CONFIRM) {
//					selectList_cachedObj.val(vObj);
//					changeSvcLevel1Ajax(idService, serviceDiv, tbl, type, vObj);
//				}
//				else {
//					obj.value=selectList_cache;
//				}
//			}
//			else {
//				selectList_cachedObj.val(vObj);
//				changeSvcLevel1Ajax(idService, serviceDiv, tbl, type, vObj);
//			}
			selectList_cachedObj.val(vObj);
			changeSvcLevel1Ajax(idService, serviceDiv, tbl, type, vObj);
		} else {
			selectList_cachedObj.val(vObj);
			changeSvcLevel1Ajax(idService, serviceDiv, tbl, type, vObj);
		}
	}
}

function changeSvcLevel1Ajax(idService, serviceDiv, tbl, type, vObj) {
	$.post(getContextPath()+'/M_PPM/M_PPM_S02AjaxBL.do?idService='+idService, function(data) {
		$(tbl).find(".cboService").html(data);
		if(type == TYPE_SUBPLAN) {
			$(tbl).find(".cboService").find(".cboPlan_PlanDetail").find(".cboPlan").attr("disabled", false);
			$(tbl).find(".cboService").find(".cboPlan_PlanDetail").find(".cboPlanDetail").attr("disabled", false);
			//removeAllServiceDetail(tbl);
			resetAllServiceDetail(tbl);
			if(vObj == "") {
				serviceDiv.find(".add").html(ADD_TEXT);
			}
			else {
				serviceDiv.find(".add").html(ADD_LINK);
			}
		}
		else {
			var ppmOptionSvc = $("#ppmOptionSvc").val();
			if (ppmOptionSvc!="1") {
				$(tbl).find(".cboService").find(".cboPlan_PlanDetail").find(".cboPlan").css('display','none');
				$(tbl).find(".cboService").find(".cboPlan_PlanDetail").find(".cboPlanDetail").css('display','none');
			}
			resetAllServiceDetail(tbl);
		}
		}
	);
}

function changeSvcLevel2(obj) {
	//calculateGST(obj);
}

function changeSvcLevel3(obj) {
	//calculateGST(obj);
}

function changeSvcLevel4(obj) {
	//calculateGST(obj);
}

function calculateGST(obj) {
	var service = $(obj).closest("div.service");
	var gst2 = false;
	var gst3 = false;
	var gst4 = false;
	var cboSvcLevel2Value = service.find(".cboSvcLevel2").val();
	var optioncboSvcLevel2 = $(".cboSvcLevel2").find("option[value='" + cboSvcLevel2Value + "']"); 
	var gst = optioncboSvcLevel2.attr("gstValue");
	if(gst == '1') {
		gst2 = true;
	}
	
	var planValue = service.find(".cboPlan").val();
	var optioncboPlan = $(".cboPlan").find("option[value='" + planValue + "']"); 
	gst = optioncboPlan.attr("gstValue");
	if(gst == '1') {
		gst3 = true;
	}
	
	var planDetailValue = service.find(".cboPlanDetail").val();
	var optioncboPlanDetail = $(".cboPlanDetail").find("option[value='" + planDetailValue + "']"); 
	gst = optioncboPlanDetail.attr("gstValue");
	if(gst == '1') {
		gst4 = true;
	}
	
//	service.find(".cboSvcLevel2 option:selected").each(function() {
//		var gst = $(this).parent().parent().find(".gst_serviceList option[value='"+$(this).val()+"']").text();
//		if(gst == '1')
//			gst2 = true;
//	});
//	service.find(".planDetail").find(".cboPlan option:selected").each(function() {
//		var gst = $(this).parent().parent().find(".gst_cboPlanList option[value='"+$(this).val()+"']").text();
//		if(gst == '1')
//			gst3 = true;
//	});
//	service.find(".planDetail").find(".cboPlanDetail option:selected").each(function() {
//		var gst = $(this).parent().parent().find(".gst_cboPlanDetailList option[value='"+$(this).val()+"']").text();
//		if(gst == '1')
//			gst4 = true;
//	});
	if(gst2 || gst3 || gst4) {
		service.find(".lblGst").html('Yes');
		service.find(".tbxGST").val('1');
	}
	else {
		service.find(".lblGst").html('No');
		service.find(".tbxGST").val('0');
	}
}
/*****************************/
function clickExit() {
    var idPlan = $("input[name='plan.idPlan']").val();
	var MsgBox = new messageBox();
	if (MsgBox.POPEXT() == MsgBox.YES_CONFIRM) {
	    if(idPlan==null||idPlan==''||idPlan=='0'){
	        location.href = getContextPath()+'/M_PPM/M_PPMS01_01BL.do';
	    }else{      
		    location.href = getContextPath()+'/M_PPM/M_PPM_S02ViewBL.do?idPlan='+ idPlan;
	    }
	}	
}

function clickSave() {
	var subplanGroup = $("#subplanGroup").find(".service");
	var optionserviceGroup = $("#optionserviceGroup").find(".service");
	if (subplanGroup.length<1 && optionserviceGroup.length<1) {
		$("#errorMsgDiv").html($("#noSubPlanOrOptionServiceMsg").html());
		return;
	}
	var allGstSameFlg = "0";
	var allCategorySameFlg = "0";
	var gstValueArr = new Array();
	var cboSvcLevel1ValueArr = new Array();
	var cboSvcLevel2ValueArr = new Array();
	var arrIndex = 0;
	for(var i=0;i<subplanGroup.length;i++) {
		var subPlan = subplanGroup.eq(i);
		subPlan.find(".tbxGST").attr("disabled", false);
		subPlan.find(".cboSvcLevel1").attr("disabled", false);
		subPlan.find(".cboSvcLevel2").attr("disabled", false);
		
		if(document.getElementById("cboRateType2Flg").value != 1){
			subPlan.find(".cboRateType2").attr("disabled", false);
		}
	    
	    var gst = subPlan.find(".tbxGST").val();
	    var cboSvcLevel1 = subPlan.find(".cboSvcLevel1").val();
        var cboSvcLevel2 = subPlan.find(".cboSvcLevel2").val();

	    gstValueArr[arrIndex] = gst;
	    cboSvcLevel1ValueArr[arrIndex] = cboSvcLevel1;
	    cboSvcLevel2ValueArr[arrIndex] = cboSvcLevel2;
	    arrIndex = arrIndex + 1;
	}
	for(var i=0;i<optionserviceGroup.length;i++) {
		var optionservice = optionserviceGroup.eq(i);
		optionservice.find(".tbxGST").attr("disabled", false);
		optionservice.find(".cboSvcLevel1").attr("disabled", false);
		optionservice.find(".cboSvcLevel2").attr("disabled", false);
		
		if(document.getElementById("cboRateType2Flg").value != 1){
			optionservice.find(".cboRateType2").attr("disabled", false);
		}
		
	    var gst = optionservice.find(".tbxGST").val();
	    var cboSvcLevel1 = optionservice.find(".cboSvcLevel1").val();
        var cboSvcLevel2 = optionservice.find(".cboSvcLevel2").val();
	    
	    gstValueArr[arrIndex] = gst;
	    cboSvcLevel1ValueArr[arrIndex] = cboSvcLevel1;
	    cboSvcLevel2ValueArr[arrIndex] = cboSvcLevel2;
	    arrIndex = arrIndex + 1;
	}
	for(var i=0;i<gstValueArr.length;i++) {
		var gst1 = gstValueArr[i];
		for(var j=i+1;j<gstValueArr.length;j++) {
			var gst2 = gstValueArr[j];
			if(gst1!=gst2) {
				allGstSameFlg = "1";
                break;
			}
		}
		if("1"==allGstSameFlg) {
            break;
        }
    }
	for(var i=0;i<cboSvcLevel1ValueArr.length;i++) {
		var cboSvcLevel11 = cboSvcLevel1ValueArr[i];
		var cboSvcLevel21 = cboSvcLevel2ValueArr[i];
		for(var j=i+1;j<cboSvcLevel1ValueArr.length;j++) {
			var cboSvcLevel12 = cboSvcLevel1ValueArr[j];
			var cboSvcLevel22 = cboSvcLevel2ValueArr[j];
			if(cboSvcLevel11!=cboSvcLevel12) {
                allCategorySameFlg = "1";
                break;
            }
            if(cboSvcLevel21!=cboSvcLevel22) {
                allCategorySameFlg = "1";
                break;
            }
		}
		if("1"==allCategorySameFlg) {
            break;
        }
    }
	//GST and Category / Service all not same
	if(allGstSameFlg=="1" && allCategorySameFlg=="1") {
		var ERR4SC017_GSTANDCategory = $("#ERR4SC017_GSTANDCategory").html();
		var MsgBox = new messageBox();
		if (MsgBox.POPCFM(ERR4SC017_GSTANDCategory) != MsgBox.YES_CONFIRM) {
			return;
		}
	} else if(allGstSameFlg=="1" && allCategorySameFlg!="1") {
		//GST not same
		var ERR4SC017_GST = $("#ERR4SC017_GST").html();
		var MsgBox = new messageBox();
		if (MsgBox.POPCFM(ERR4SC017_GST) != MsgBox.YES_CONFIRM) {
			return;
		}
	} else if(allGstSameFlg!="1" && allCategorySameFlg=="1") {
		//Category / Service all not same
		var ERR4SC017_Category = $("#ERR4SC017_Category").html();
		var MsgBox = new messageBox();
		if (MsgBox.POPCFM(ERR4SC017_Category) != MsgBox.YES_CONFIRM) {
			return;
		}
	}
	
	removeAllTemp();
	reIndex();
	// #173 Start
	$(".cboRateType2").attr("disabled", false);
	// #173 End
	$('form').submit();
	if(document.getElementById("cboRateType2Flg").value != 1){
		document.getElementById("cboRateType2").disabled = true;
	}
}

function reIndex() {
	$("#errorMsgDiv").html("");
	var subplanGroup = $("#subplanGroup").find(".service");
	var optionserviceGroup = $("#optionserviceGroup").find(".service");
	var i = 0;
	for(; i < subplanGroup.length; i++) {
		var subplan = subplanGroup.eq(i);
		reIndexService(subplan, i, 'services');
		subplan.find(".tbxGST").attr("disabled", false);
		subplan.find(".cboSvcLevel1").attr("disabled", false);
		subplan.find(".cboSvcLevel2").attr("disabled", false);
		if(document.getElementById("cboRateType2Flg").value != 1){
			subplan.find(".cboRateType2").attr("disabled", false);
		}
	}
	for(var j = 0; j < optionserviceGroup.length; j++) {
		var optionservice = optionserviceGroup.eq(j);
		reIndexService(optionserviceGroup.eq(j), (i + j), 'services');
		optionservice.find(".tbxGST").attr("disabled", false);
		optionservice.find(".cboSvcLevel1").attr("disabled", false);
		optionservice.find(".cboSvcLevel2").attr("disabled", false);
		if(document.getElementById("cboRateType2Flg").value != 1){
			optionservice.find(".cboRateType2").attr("disabled", false);
		}
	}
}

function reIndexService(service, serviceIndex, serviceType) {
	//tbxServiceName
	var tbxServiceName = service.find('.tbxServiceName');
	for(var i = 0; i < tbxServiceName.length; i++) {
		tbxServiceName.eq(i).attr("name", "plan."+serviceType+"["+serviceIndex+"].tbxServiceName");
	}
	//type
	var type = service.find('.type');
	for(var i = 0; i < type.length; i++) {
		type.eq(i).attr("name", "plan."+serviceType+"["+serviceIndex+"].type");
	}
	//inUsedHidden
	var inUsedHidden = service.find('.inUsedHidden');
	for(var i = 0; i < inUsedHidden.length; i++) {
		inUsedHidden.eq(i).attr("name", "plan."+serviceType+"["+serviceIndex+"].inUsed");
	}
	//inMapping
	var inMapping = service.find('.inMapping');
	for(var i = 0; i < inMapping.length; i++) {
		inMapping.eq(i).attr("name", "plan."+serviceType+"["+serviceIndex+"].inMapping");
	}
	//idPlanGrp
	var idPlanGrp = service.find('.idPlanGrp');
	for(var i = 0; i < idPlanGrp.length; i++) {
		idPlanGrp.eq(i).attr("name", "plan."+serviceType+"["+serviceIndex+"].idPlanGrp");
	}
	//cboSvcLevel1
	var cboSvcLevel1 = service.find('.cboSvcLevel1');
	for(var i = 0; i < cboSvcLevel1.length; i++) {
		cboSvcLevel1.eq(i).attr("name", "plan."+serviceType+"["+serviceIndex+"].cboSvcLevel1");
	}
	//cboSvcLevel2
	var cboSvcLevel2 = service.find('.cboSvcLevel2');
	for(var i = 0; i < cboSvcLevel2.length; i++) {
		cboSvcLevel2.eq(i).attr("name", "plan."+serviceType+"["+serviceIndex+"].cboSvcLevel2");
	}
	//cboRateType
	var cboRateType = service.find('.cboRateType');
	for(var i = 0; i < cboRateType.length; i++) {
		cboRateType.eq(i).attr("name", "plan."+serviceType+"["+serviceIndex+"].cboRateType");
	}
	//cboRateType2
	var cboRateType2 = service.find('.cboRateType2');
	for(var i = 0; i < cboRateType2.length; i++) {
		cboRateType2.eq(i).attr("name", "plan."+serviceType+"["+serviceIndex+"].cboRateType2");
	}
	//cboRateMode
	var cboRateMode = service.find('.cboRateMode');
	for(var i = 0; i < cboRateMode.length; i++) {
		cboRateMode.eq(i).attr("name", "plan."+serviceType+"["+serviceIndex+"].cboRateMode");
	}
	//tbxRate
	var tbxRate = service.find('.tbxRate');
	for(var i = 0; i < tbxRate.length; i++) {
		tbxRate.eq(i).attr("name", "plan."+serviceType+"["+serviceIndex+"].tbxRate");
	}
	//tbxGST
	var tbxGST = service.find('.tbxGST');
	for(var i = 0; i < tbxGST.length; i++) {
		tbxGST.eq(i).attr("name", "plan."+serviceType+"["+serviceIndex+"].tbxGST");
	}
	//cboPlanList
//	var cboPlanList_Id = service.find('.cboPlanList_Id');
//	for(var j = 0; j < cboPlanList_Id.length; j++) {
//		cboPlanList_Id.eq(j).attr("name", "plan."+serviceType+"["+serviceIndex+"].cboPlanList["+j+"].idService");
//	}
//	var cboPlanList_Desc = service.find('.cboPlanList_Desc');
//	for(var j = 0; j < cboPlanList_Desc.length; j++) {
//		cboPlanList_Desc.eq(j).attr("name", "plan."+serviceType+"["+serviceIndex+"].cboPlanList["+j+"].svcDesc");
//	}
//	//cboPlanDetailList
//	var cboPlanDetailList_Id = service.find('.cboPlanDetailList_Id');
//	for(var j = 0; j < cboPlanDetailList_Id.length; j++) {
//		cboPlanDetailList_Id.eq(j).attr("name", "plan."+serviceType+"["+serviceIndex+"].cboPlanDetailList["+j+"].idService");
//	}
//	var cboPlanDetailList_Desc = service.find('.cboPlanDetailList_Desc');
//	for(var j = 0; j < cboPlanDetailList_Desc.length; j++) {
//		cboPlanDetailList_Desc.eq(j).attr("name", "plan."+serviceType+"["+serviceIndex+"].cboPlanDetailList["+j+"].svcDesc");
//	}
	//serviceDetail
	var serviceDetail = service.find('.serviceDetail');
	for(var i = 0; i < serviceDetail.length; i++) {
		reIndexServiceDetail(serviceDetail.eq(i), serviceIndex, serviceType);
	}
}

function reIndexServiceDetail(detail, serviceIndex, serviceType) {
	//cboPlan
	var cboPlan = detail.find('.cboPlan');
	for(var j = 0; j < cboPlan.length; j++) {
		cboPlan.eq(j).attr("disabled", false);
		cboPlan.eq(j).attr("name", "plan."+serviceType+"["+serviceIndex+"].details["+j+"].cboPlan");
	}
	//cboPlanDetail
	var cboPlanDetail = detail.find('.cboPlanDetail');
	for(var j = 0; j < cboPlanDetail.length; j++) {
		cboPlanDetail.eq(j).attr("disabled", false);
		cboPlanDetail.eq(j).attr("name", "plan."+serviceType+"["+serviceIndex+"].details["+j+"].cboPlanDetail");
	}
	//idPlanSvc
	var idPlanSvc = detail.find('.idPlanSvc');
	for(var j = 0; j < idPlanSvc.length; j++) {
		idPlanSvc.eq(j).attr("name", "plan."+serviceType+"["+serviceIndex+"].details["+j+"].idPlanSvc");
	}
	//cboVendor
	var cboVendor = detail.find('.cboVendor');
	for(var j = 0; j < cboVendor.length; j++) {
		cboVendor.eq(j).attr("name", "plan."+serviceType+"["+serviceIndex+"].details["+j+"].cboVendor");
	}
}

function removeAllTemp() {
	$(".cboPlan_PlanDetail").remove();
}

/*****************************************/
function refreshServiceCallback() {
	changeCurrencyCallback();
	var modelFlg = $("#modelFlg").val();
	if (modelFlg=="new") {
		var categoryApplyAllCboHid = $("#categoryApplyAllCboHid").val();
		var serviceApplyAllCboHid = $("#serviceApplyAllCboHid").val();
		$("#categoryApplyAllCbo").val(categoryApplyAllCboHid);
		categoryApplyAllCboEvt(document.getElementById("categoryApplyAllCbo"), "2", serviceApplyAllCboHid, "");
	}
	var allService = $("#serviceTemp").find(".service");
	for(var i = 0; i < allService.length; i++) {
		loadServiceCallback(allService.eq(i));
	}
	$("#serviceTemp").remove();
}

function loadServiceCallback(service) {
	var type = service.find('.type').val();
	var modelFlg = $("#modelFlg").val();
	if (modelFlg=="new") {
		var GSTApplyAllChk = $("#GSTApplyAllChk").is(':checked');
		var categoryApplyAllChk = $("#categoryApplyAllChk").is(':checked');
		service.find(".tbxGST").attr("disabled", GSTApplyAllChk);
		service.find(".cboSvcLevel1").attr("disabled", categoryApplyAllChk);
		service.find(".cboSvcLevel2").attr("disabled", categoryApplyAllChk);
	}
	var inUsedHidden = service.find('.inUsedHidden').val();
	service.find(".cboSvcLevel1_cached").val(service.find(".cboSvcLevel1").val());
	if(type == TYPE_SUBPLAN) {
		appendSubPlan(service);
		//service.find(".cboService").html(data);
		service.find(".cboSvcLevel2").val(service.find(".cboSvcLevel2CallBack").val());
		var planDetails = service.find(".planDetail");
		for(var i = 0; i < planDetails.length; i++) {
			var planDetail = planDetails.eq(i);
			planDetail.find(".cboPlan").val(planDetail.find(".cboPlanCallBack").val());
			planDetail.find(".cboPlanDetail").val(planDetail.find(".cboPlanDetailCallBack").val());
		}
		if(inUsedHidden == 0) {
			if (service.find(".cboSvcLevel1").val() == "") {
				service.find(".add").html(ADD_TEXT);
			} else {
				service.find(".add").html(ADD_LINK);
			}
		} else {
			service.find(".add").html(BLANK);
			//service.find(".cboService").html(service.find(".cboSvcLevel2 option:selected").html() + service.find(".cboService").html());
			//service.find(".cboSvcLevel2").addClass('inUsed');
		}
//		$.post(getContextPath()+'/M_PPM/M_PPM_S02AjaxBL.do?idService='+service.find(".cboSvcLevel1").val(), function(data) {
//			service.find(".cboService").html(data);
//			service.find(".cboSvcLevel2").val(service.find(".cboSvcLevel2CallBack").val());
//			if(inUsedHidden == 0) {
//				if(service.find(".cboSvcLevel1").val() == "") {
//					service.find(".add").html(ADD_TEXT);
//				}
//				else {
//					service.find(".add").html(ADD_LINK);
//				}
//			}
//			else {
//				service.find(".add").html(BLANK);
//				service.find(".cboService").html(service.find(".cboSvcLevel2 option:selected").html() + service.find(".cboService").html());
//				service.find(".cboSvcLevel2").addClass('inUsed');
//			}
//			service.find(".planDetail").append(service.find(".gst_cboPlanList").clone());
//			service.find(".planDetail").append(service.find(".gst_cboPlanDetailList").clone());
//		});
	}
	else {
		appendOptionService(service);
		
		//service.find(".cboService").html(data);
		service.find(".cboSvcLevel2").val(service.find(".cboSvcLevel2CallBack").val());
		var planDetails = service.find(".planDetail");
		for(var i = 0; i < planDetails.length; i++) {
			var planDetail = planDetails.eq(i);
			planDetail.find(".cboPlan").val(planDetail.find(".cboPlanCallBack").val());
			planDetail.find(".cboPlanDetail").val(planDetail.find(".cboPlanDetailCallBack").val());
		}
		if(inUsedHidden != 0) {
			service.find(".add").html(BLANK);
			//service.find(".cboService").html(service.find(".cboSvcLevel2 option:selected").html() + service.find(".cboService").html());
			//service.find(".cboSvcLevel2").addClass('inUsed');
		}
//		$.post(getContextPath()+'/M_PPM/M_PPM_S02AjaxBL.do?idService='+service.find(".cboSvcLevel1").val(), function(data) {
//			service.find(".cboService").html(data);
//			service.find(".cboSvcLevel2").val(service.find(".cboSvcLevel2CallBack").val());
//			if(inUsedHidden != 0) {
//				service.find(".add").html(BLANK);
//				service.find(".cboService").html(service.find(".cboSvcLevel2 option:selected").html() + service.find(".cboService").html());
//				service.find(".cboSvcLevel2").addClass('inUsed');
//			}
//			service.find(".planDetail").append(service.find(".gst_cboPlanList").clone());
//			service.find(".planDetail").append(service.find(".gst_cboPlanDetailList").clone());
//		});
	}
}

function changeCurrencyCallback() {
	$(".cboBillCurrenc option:selected").each(function() {
		$(".serviceCurrency").html($(this).val());
	});
}
function categoryApplyAllCboEvt(obj, type, serviceApplyAllCboHid, typeEvt) {
	$.post(getContextPath()+'/M_PPM/M_PPM_S02NewAjaxGetService.do?idService='+$(obj).val(), function(data) {
		$("#serviceApplyAllCbo").empty();	
		$("#serviceApplyAllCbo").append(data);
		if("1"==type) {
			categoryApplyAllEvt('', typeEvt);
		} else {
			$("#serviceApplyAllCbo").val(serviceApplyAllCboHid);
		}
		}
	);
}
function GSTApplyAllEvt(type){
	if(type=="GSTApplyAllCboChange") {
		var GSTApplyAllChk = $("#GSTApplyAllChk").is(':checked');
		if (GSTApplyAllChk) {
			GSTApplyAll();
		}
	} else {
		GSTApplyAll();
	}
}

function GSTApplyAll() {
	var subplanGroup = $("#subplanGroup").find(".service");
	for(var i = 0; i < subplanGroup.length; i++) {
		var subplan = subplanGroup.eq(i);
		setGSTApplyAll(subplan);
	}
	
	var optionserviceGroup = $("#optionserviceGroup").find(".service");
	for(var i = 0; i < optionserviceGroup.length; i++) {
		var optionservice = optionserviceGroup.eq(i);
		setGSTApplyAll(optionservice);
	}
}

function setGSTApplyAll(obj){
	var GSTApplyAllChk = $("#GSTApplyAllChk").is(':checked');
	var GSTApplyAllCbo = $("#GSTApplyAllCbo").val();
	
	var tbxGST = obj.find(".tbxGST");
	if(GSTApplyAllChk){
		tbxGST.val(GSTApplyAllCbo);
	}else{
		//tbxGST.val('1');
		tbxGST.val(GSTApplyAllCbo);
	}
	tbxGST.attr("disabled", GSTApplyAllChk);
}
function categoryApplyAllEvt(typeEvt, typeEvt1){
	if(typeEvt1=="serviceApplyAllCboChange" || typeEvt1=="categoryApplyAllCboChange") {
		var categoryApplyAllChk = $("#categoryApplyAllChk").is(':checked');
		if(categoryApplyAllChk) {
			categoryApplyAll(typeEvt);
		}
	} else {
		categoryApplyAll(typeEvt);
	}
}
function categoryApplyAll(typeEvt) {
	var subplanGroup = $("#subplanGroup").find(".service");
	//var hasSubPlanDetail = false;
	for(var i = 0; i < subplanGroup.length; i++) {
		var subplan = subplanGroup.eq(i);
		setCategoryApplyAll(subplan, typeEvt);
		
//		if (hasSubPlanDetail) {
//			var subPlanDetail = subplan.find(".planDetail").html();
//			if (subPlanDetail!=null && subPlanDetail!="" && subPlanDetail!=undefined) {
//				hasSubPlanDetail = true;
//			}
//		}
	}
	var optionserviceGroup = $("#optionserviceGroup").find(".service");
	for(var i = 0; i < optionserviceGroup.length; i++) {
		var optionservice = optionserviceGroup.eq(i);
		setCategoryApplyAll(optionservice, typeEvt);
	}
}
function setCategoryApplyAll(obj, typeEvt) {
	var categoryApplyAllChk = $("#categoryApplyAllChk").is(':checked');
	var categoryApplyAllCbo = $("#categoryApplyAllCbo").val();
	var serviceApplyAllCbo = $("#serviceApplyAllCbo").val();
	
	var cboSvcLevel1 = obj.find(".cboSvcLevel1");
	var cboSvcLevel2 = obj.find(".cboSvcLevel2");

	if("serviceApplyAllCboChange"!=typeEvt) {
		if ("categoryApplyAllChkClick"==typeEvt) {
			//from checked to not checked
			if(!categoryApplyAllChk) {
				cboSvcLevel2.attr("disabled", categoryApplyAllChk);
			} else {
				var cboSvcLevel1Value = obj.find(".cboSvcLevel1").val();
				//each Category not same Apply All Category
				if (categoryApplyAllCbo!=cboSvcLevel1Value) {
					changeSvcLevel1Info(cboSvcLevel1.get(0), categoryApplyAllCbo, serviceApplyAllCbo, categoryApplyAllChk);
				} else {
					cboSvcLevel2.val(serviceApplyAllCbo);
					cboSvcLevel2.attr("disabled", categoryApplyAllChk);
				}
			}
		} else {
			changeSvcLevel1Info(cboSvcLevel1.get(0), categoryApplyAllCbo, serviceApplyAllCbo, categoryApplyAllChk);
		}
	}
	if("serviceApplyAllCboChange"==typeEvt) {
		cboSvcLevel2.val(serviceApplyAllCbo);
		cboSvcLevel2.attr("disabled", categoryApplyAllChk);
	}
	cboSvcLevel1.val(categoryApplyAllCbo);
	cboSvcLevel1.attr("disabled", categoryApplyAllChk);
}

function changeSvcLevel1Info(obj, categoryApplyAllCbo, serviceApplyAllCbo, categoryApplyAllChk) {
	var idService = categoryApplyAllCbo;
	var tbl = obj.parentNode.parentNode.parentNode;
	var vObj = categoryApplyAllCbo;
	//find the type of Service is Sub Plan or Option Service
	var serviceDiv = $(tbl).parent().parent().parent();
	var type = serviceDiv.find(".type").val();
	//cached
	var selectList_cachedObj = serviceDiv.find(".cboSvcLevel1_cached");
	var selectList_cache = selectList_cachedObj.val();
	
	selectList_cachedObj.val(vObj);
	
	$.post(getContextPath()+'/M_PPM/M_PPM_S02AjaxBL.do?idService='+idService, function(data) {
		$(tbl).find(".cboService").html(data);
		if(type == TYPE_SUBPLAN) {
			$(tbl).find(".cboService").find(".cboPlan_PlanDetail").find(".cboPlan").attr("disabled", false);
			$(tbl).find(".cboService").find(".cboPlan_PlanDetail").find(".cboPlanDetail").attr("disabled", false);
			//removeAllServiceDetail(tbl);
			resetAllServiceDetail(tbl);
			if(vObj == "") {
				serviceDiv.find(".add").html(ADD_TEXT);
			}
			else {
				serviceDiv.find(".add").html(ADD_LINK);
			}
		}
		else {
			var ppmOptionSvc = $("#ppmOptionSvc").val();
			if (ppmOptionSvc!="1") {
				$(tbl).find(".cboService").find(".cboPlan_PlanDetail").find(".cboPlan").css('display','none');
				$(tbl).find(".cboService").find(".cboPlan_PlanDetail").find(".cboPlanDetail").css('display','none');
			}
			resetAllServiceDetail(tbl);
		}
		$(tbl).find(".cboSvcLevel2").val(serviceApplyAllCbo);
		$(tbl).find(".cboSvcLevel2").attr("disabled", categoryApplyAllChk);
		}
	);
}