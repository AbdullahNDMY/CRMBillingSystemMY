function changewidth(){
	var popupWidth = window.screen.width*80/100;
	popupWidth = popupWidth - 45;
    document.documentElement.childNodes[1].innerHTML="<div style='width:"+popupWidth+"px'>"+document.documentElement.childNodes[1].innerHTML+"</div>";
}
function resetAct(){
	document.forms[0].tbxCustomerName.value = "";
	document.forms[0].tbxCustomerId.value = "";
	document.forms[0].doSearch.value="N";
	document.forms[0].submit();
}
function insertAct(context) {
	var selectedIdPlanGrp = $("input[name='idPlanGrp']:checked");
	
	var idBillAcc = selectedIdPlanGrp.eq(0).val();

	var sendvales = $("input[name='"+idBillAcc+"']");
	var idCust = sendvales.eq(0).val();
	var custName = sendvales.eq(1).val();
	
	window.opener.setCustomerAndBillAccInfo(idCust,custName,idBillAcc);
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