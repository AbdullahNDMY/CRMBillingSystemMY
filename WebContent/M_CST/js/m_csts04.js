function resetAct(){
	document.forms[0].tbxCustomerName.value = "";
	document.forms[0].tbxCustomerId.value = "";
	document.forms[0].doSearch.value="N";
	document.forms[0].submit();
}
function insertAct(context) {
	var selectedIdPlanGrp = $("input[name='idPlanGrp']:checked");
	
	var custID = selectedIdPlanGrp.eq(0).val();

	var sendvales = $("input[name='"+custID+"']");
	var custName = sendvales.eq(0).val();
	var custType = sendvales.eq(1).val();
	
	window.opener.setCustomerInfo(custName,custID,custType);
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