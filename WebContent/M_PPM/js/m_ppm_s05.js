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
