
function initMain() {
	var calBtns = $('input[class=BlueStyle-button]');
	for ( var i = 0; i < calBtns.length; i++) {
		if (calBtns.eq(i).closest('div').attr('disabled'))
			calBtns.eq(i).attr('disabled', true);
	}
}

function checkRunDate(runDateInputName, runMode) {
	var input = $('input[name=' + runDateInputName + ']');
	var mode = runMode;
	var ERR1SC063 = document.getElementById("errors.ERR1SC063").value;
	if (input.length > 0) {
		if (!input.closest('div').attr('disabled')) {
			var d = Date.parse(input.val());
			if (isNaN(d)) {
				var MsgBox = new messageBox();				
				if(mode == 'gbrun'){
					var errorGB = ERR1SC063.replace("replace", "Generate Billing");
					MsgBox.POPALT(errorGB); //$('#msgInvalidDateID').val()
				}
				if(mode == 'sarun'){
					var errorSA = ERR1SC063.replace("replace", "Statement of Accounts");
					MsgBox.POPALT(errorSA);
				}
				if(mode == 'arrun'){
					var errorAR = ERR1SC063.replace("replace", "Peoplesoft AR Export");
					MsgBox.POPALT(errorAR);
				}
				return false;
			} else {
				if(mode != 'sarun'){
					var SARunDateEntry = document.getElementById("SARunDateEntry");
					if (SARunDateEntry!=null && SARunDateEntry!="" && SARunDateEntry!=undefined) {
						SARunDateEntry.value = "";
					}
					var SApopupClickYesFlg = document.getElementById("SApopupClickYesFlg");
					if (SApopupClickYesFlg!=null && SApopupClickYesFlg!="" && SApopupClickYesFlg!=undefined) {
						SApopupClickYesFlg.value = "";
					}
					var SAPopupInfo = document.getElementById("SAPopupInfo");
					if (SAPopupInfo!=null && SAPopupInfo!="" && SAPopupInfo!=undefined) {
						SAPopupInfo.value = "";
					}
				}
				$('input[name=esetRunDateValue]').val(input.val());
				if(mode == 'gbrun'){
					var path = $("#path").val();
					var msg = new messageBox("");
					var url = path+"/COMMON/COMMONCurrencyBL.do?runDate="+input.val()+"&r=" + Math.random();
					var result = msg.POPCUR(url);
					if(result == msg.YES_CONFIRM) {
						//submitForm("forward_gbRun");
						return true;
					}
					return false;
				}
				return true;
			}
		}
	}
	return true;
}
//Function use submit form
function submitForm(event) {
	$("input[name='event']").remove();
	var event = '<input type="hidden" name="event" value="' + event + '"/>';
	$('form').append(event);
	$('form').submit();
}