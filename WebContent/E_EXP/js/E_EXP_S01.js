$(document).ready(function() {
	$("input[name='closingMonth']").spinbox({
		min: 1,
		max: 12
	});
	$("input[name='closingYear']").spinbox({
		min: 1,
		max: 9999
	});
});
function popupMsg(url, msg){
	var newwindow = window.open(url, 'E_EXP_S01_Log', 'height=400,width=800,scrollbars=1');
	if (window.focus) { newwindow.focus(); }
}
function userAccessViewMode() {
	var uAccess = 1;
	//var retStatusStr = document.getElementById("retStatusStr").value;
    if(document.getElementById("userAccess").value == uAccess){
		document.forms[0].btnExecute.disabled = true;
		document.forms[0].cboClosingMonth.disabled = true;
		document.forms[0].cboClosingYear.disabled = true;
    }
}