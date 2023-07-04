$(function(){

		//setting check box
		settingCheckbox($(".searchServiceStatus"), $(".hidServStatus"));
		
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
	});
function resetAct(){
	document.forms[0].gdcBillingDescription.value = "";
	document.forms[0].gdcItemDescription.value = "";
	document.forms[0].tbxSubscription.value = "";
	document.forms[0].doSearch.value='N';
	document.forms[0].submit();
}
function insertAct(context) {
	var selectedIdPlanGrp = $("input[name='idPlanGrp']:checked");
	var promptErrorMessage	= $("#ERR1SC033").val();
	if(selectedIdPlanGrp.length==0){
		alert(promptErrorMessage);
		return false;
	}
	var idCustPlanGrps="";
	var idCustPlanLinks="";
	for(var i = 0; i < selectedIdPlanGrp.length; i++) {
		var index = selectedIdPlanGrp.eq(i).val();
		var sendvales = $("input[name='"+index+"']");
		var idCustPlanGrp = sendvales.eq(0).val();
		var idCustPlanLink = sendvales.eq(1).val();
		idCustPlanGrps += idCustPlanGrp+",";
		idCustPlanLinks += idCustPlanLink + ",";
	}
	window.opener.B_BIL_S04ReturnCalled(idCustPlanGrps,idCustPlanLinks);
	window.close();
}
function planChecked() {
	var listIdPlanGrp = $("input[name='idPlanGrp']:checked");
	//alert(listIdPlanGrp.length);
	var idPlanGrp = $("input[name='idPlanGrp']");
	//alert(idPlanGrp.length);
	if(listIdPlanGrp.length<idPlanGrp.length){
		$("#totalcheck").attr("checked", false);
	}else if(idPlanGrp.length>0){
		$("#totalcheck").attr("checked", true);
	}
	if(listIdPlanGrp.length > 0) {
		$("#insertBtn").attr("disabled", false);
	}
	else {
		$("#insertBtn").attr("disabled", true);
	}
}
function checkAll(obj){
	if(obj.checked){
		$("input[name='idPlanGrp']").attr('checked', true);
	}else{
		$("input[name='idPlanGrp']").attr('checked', false);
	}
	planChecked();
}