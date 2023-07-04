
// fix trim() method for IE8
if(typeof String.prototype.trim !== 'function') {
  String.prototype.trim = function() {
    return this.replace(/^\s+|\s+$/g, '');
  }
}
/**************************/
var TYPE_OPTION_SERVICE = "O";
var BLANK = '&nbsp;';
var ADD_TEXT = 'Add';
var ADD_LINK = '<a href="javascript:void(0);" onclick="addServiceDetail(this);">Add</a>';

function initload(){
	$('#serviceClone').find("#type").val(TYPE_OPTION_SERVICE);
	/*var width = document.body.scrollWidth;
	$("#planInfo").css("width",width-70);
    $(".subHeader").css("width",width-65);*/
	var isSuccessFlg=$(".isSaveFlg").val();
	if(isSuccessFlg==1){
		var idPlan = document.getElementById('idPlan').value;
		window.opener.location=getContextPath() + '/M_PPM/M_PPM_S02ViewBL.do?idPlan='+idPlan ;
		window.opener=null;     
	    window.open('','_self');     
	    window.close(); 
	}

	var allGstSameFlg=$("#allGstSameFlg").val();
	var allCategorySameFlg=$("#allCategorySameFlg").val();
	if(allGstSameFlg=="1"||allCategorySameFlg=="1"){
		checkAllPlanSame();
	}
}

/****************************/
function addServiceDetail(obj) {
	var tblBody = $(obj).closest("table.serviceDetail");
	var serviceDetails = $('#serviceDetailClone').clone();
	serviceDetails.removeAttr('id');
	serviceDetails.appendTo($(tblBody));
    serviceDetails.find(".remove").html("<img src='"+getContextPath()+"/image/delete.gif' onclick='removeServiceDetail(this);'/>");
}

function removeServiceDetail(obj) {
	var parent = obj.parentNode.parentNode.parentNode;
	parent.removeChild(obj.parentNode.parentNode);
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
	var vObj = obj.value;
	changeSvcLevel1Ajax(idService);
}

function changeSvcLevel1Ajax(idService) {
	$.post(getContextPath()+'/M_PPM/M_PPM_S02AjaxBL.do?idService='+idService, function(data) {
		$('#serviceClone').find(".cboService").html(data);
		var detail = $('#serviceClone').find(".cboPlan_PlanDetail");
		if(detail.html() != null) {
			var planDetail = $('.serviceDetailClone').find(".planDetail");
			for(var i = 0; i < planDetail.length; i++) {
				planDetail.eq(i).html(detail.html());
			}
		}
		$('.planDetail').find('.cboPlan').attr("disabled",false);
		$('.planDetail').find('.cboPlanDetail').attr("disabled",false);
		}
	);
}

/*****************************/
function clickExit() {
    var idPlan = $("input[name='plan.idPlan']").val();
	var MsgBox = new messageBox();
	if (MsgBox.POPEXT() == MsgBox.YES_CONFIRM) {
		window.close();
	}	
}

function clickSave() {
	if(document.getElementById("cboRateType2Flg").value != 1){
		document.getElementById("cboRateType2").disabled = false;
	}
	removeAllTemp();
	reIndex();
	$(".isSaveFlg").val(0);
	$('form').submit();
	if(document.getElementById("cboRateType2Flg").value != 1){
		document.getElementById("cboRateType2").disabled = true;
	}
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
function reIndex() {
	$("#errorMsgDiv").html("");
	var subplan= $("#serviceClone");
	var i = 0;
	reIndexService(subplan, i, 'services');
}

function reIndexService(service, serviceIndex, serviceType) {
	//inUsedHidden
	var inUsedHidden = service.find('.inUsedHidden');
	for(var i = 0; i < inUsedHidden.length; i++) {
		inUsedHidden.eq(i).attr("name", "plan."+serviceType+"["+serviceIndex+"].inUsedHidden");
	}
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
	//alert(service.find('.cboService').html());
	//cboRateType
	var cboRateType = service.find('.cboRateType');
	for(var i = 0; i < cboRateType.length; i++) {
		cboRateType.eq(i).attr("name", "plan."+serviceType+"["+serviceIndex+"].cboRateType");
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
	//cboVendor
	var cboVendor = detail.find('.cboVendor');
	for(var j = 0; j < cboVendor.length; j++) {
		cboVendor.eq(j).attr("name", "plan."+serviceType+"["+serviceIndex+"].details["+j+"].cboVendor");
	}
}

function removeAllTemp() {
	$(".cboPlan_PlanDetail").remove();
	$("#temptable").remove();
}


function checkAllPlanSame(){
	var allGstSameFlg=$("#allGstSameFlg").val();
	var allCategorySameFlg=$("#allCategorySameFlg").val();
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
	$("#isHaveCheckFlg").val("1");
	clickSave(); 
}