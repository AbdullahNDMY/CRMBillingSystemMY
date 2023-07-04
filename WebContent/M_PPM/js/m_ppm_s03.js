function resetAct(){
	document.forms[0].tbxPlanName.value = "";
	document.forms[0].tbxPlanDescript.value = "";
	document.forms[0].tbxServiceName.value = "";
	document.forms[0].cboService.value = "";
	document.forms[0].hidService.value = "";
	document.forms[0].cboType.selectedIndex = 0;
	document.forms[0].cboRateMode.selectedIndex = 0;
	document.forms[0].cboCategory.selectedIndex = 0;
	var startIndex = document.getElementById("startIndex");
	if(startIndex!=null && startIndex!=undefined) {
		startIndex.value="0";
	}
	document.forms[0].doSearch.value = 'N';
	document.forms[0].submit();
}

function insertAct(context) {
	var listIdPlanGrp = "";
	var selectedIdPlanGrp = $("input[name='idPlanGrp']:checked");
	for(var i = 0; i < selectedIdPlanGrp.length; i++) {
		listIdPlanGrp += "" + selectedIdPlanGrp.eq(i).val() +",";
	}
	window.opener.appendSubPlan(window, listIdPlanGrp);
	window.close();
}
	
function planChecked() {
	var listIdPlanGrp = $("input[name='idPlanGrp']:checked");
	if(listIdPlanGrp.length > 0) {
		$("#insertBtn").attr("disabled", false);
	}
	else {
		$("#insertBtn").attr("disabled", true);
	}
}

function initSearch() {
	$("input[name='idPlanGrp']").attr('checked', false);
}

function cboCategoryChange(obj) {
	//displayOption($("#cboService"), $(obj).val(), "serviceClone");
	//wcbeh@20161206 #211 Service Combobox
	changeCategory();
}

function searchClick() {
	var startIndex = document.getElementById("startIndex");
	if(startIndex!=null && startIndex!=undefined) {
		startIndex.value="0";
	}
}

function initService() {
	var cboCategory = $("#cboCategory").val();
	var hidService = $("#hidService").val();
	
	if(cboCategory!=null && cboCategory!="") {
		displayOption($("#cboService"), cboCategory, "serviceClone");
	}
	$("#cboService").val(hidService);	
	
	//wcbeh@20161106 - Service Combobox
	$(".cboService").combobox();
	changeCategory();
	/*if ($("#hidService").val() != "") {
		$("#combInput4").val($("#hidService").val());
	}*/
}

function changeCategory(){
	$(".custom-combobox-input").val("-Please Select One-");
	var value = $("#cboCategory").val();

	if(value!=""){
		$(".custom-combobox-input").attr("disabled", false);
		$(".ui-button").attr("disabled", false);
		
		var objLevel = $(".cboService");
		var selectedValue = objLevel.val();
		displayServiceOption(objLevel, value, "serviceClone");
		objLevel = $(".cboService");
		objLevel.val(selectedValue);
		objLevel.attr("disabled", false);
		$('.ui-autocomplete-input').css('width','270px');
		//wcbeh@20161229 - #211 Make Clear Default Value When Clicked
		if ($("#hidService").val() == ""){
			$(".custom-combobox-input").val("");
			$(".custom-combobox-input").blur();
		}
	}
	else{
		$(".cboService").attr("disabled", true);
		$(".custom-combobox-input").attr("disabled", true);
		$(".ui-button").attr("disabled", true);
		$('.ui-autocomplete-input').css('width','128px');
	}
}

function displayOption(obj, svcGrp, classObject) {
	//save selected value
	var selectedValue = obj.val();
	removeAllOption(obj);
	//add valid option for object
	var cloneObj =  $("#" + classObject).clone();
	var copyCbo = obj.clone();
	obj.replaceWith(copyCbo);
	var optionList = cloneObj.find("option");
	for ( var i = 0; i < optionList.length; i++) {
		var option = optionList.eq(i);
		if (option.val() != "" && option.attr("svcGrp") == svcGrp) {
			copyCbo.append(option);
		}
	}
	copyCbo.val(selectedValue);
}

//wcbeh@20161206 #211 - Service Combobox (js clone from b_cpm_s01.js)
function displayServiceOption(obj, svcGrp, classObject) {
 	//save selected value
	var selectedValue = obj.val();
	removeAllOption(obj);
	//add valid option for object
	var cloneObj =  $("#" + classObject).clone();
	
	var optionList = cloneObj.find("option");
	for ( var i = 0; i < optionList.length; i++) {
		var option = optionList.eq(i);
		if (option.attr("svcGrp") == svcGrp) {
			obj.append(option);
			
			if (option.val() == selectedValue) {
				$("#combInput4").val(option[0].innerText);
			}
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
	for ( var i = 0; i < optionList.length; i++) {
		var option = optionList.eq(i);
		//if (option.val() != "") {
			option.remove();
		//}
	}
}
