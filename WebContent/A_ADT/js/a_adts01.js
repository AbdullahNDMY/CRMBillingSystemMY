function viewAuditTrail(auditID){
	document.getElementById("selectedAuditID").value = auditID;	
	var button=	document.getElementById("forward_view");
	button.click();	
}
function resetForm() {
	$('#dateFrom').val('');
	$('#dateTo').val('');
	$('#subModuleID').val('');
	$('#reference').val('');
	$('#actionCbo').val('');
	$('#userName').val('');
}
function searchClick() {
    showLoadingTipWindow();
	var startIndex = document.getElementById("startIndex");
	if(startIndex!=null && startIndex!=undefined) {
		startIndex.value="0";
	}
}