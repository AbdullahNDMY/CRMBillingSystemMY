function changeRuntime(thiz) {
	if (thiz != null) {
		var value = thiz[thiz.selectedIndex].value;
		// scheduler
		if (value == "1") $("#scheduler").show();
		else $("#scheduler").hide();
		// manual
		if (value == "2") $("#manual").show();
		else $("#manual").hide();
	}
}

function changeScheduleStatus(thiz) {
	if (thiz.value == "0") {
		//$("#scheduler-time input").attr("disabled", "disabled");
		var objs = $("#scheduler-time input");
		objs.readonly();
		objs.resetValue();
		document.getElementById("scheduleDay").value = document.getElementById("scheduleDayDefault").value;
		document.getElementById("scheduleDay").disabled = true;
	} else {
		//$("#scheduler-time input").removeAttr("readonly");
		$("#scheduler-time input").readonly(false);
		document.getElementById("scheduleDay").disabled = false;
	}
}

function genFile(url) {
	newwindow=window.open(url,'giroGenFile','height=200,width=550, scrollbars=1, resizable=0');
	if (window.focus) {newwindow.focus()}
}

function popupMsg(url, msg){
	var newwindow = window.open(url, 'name', 'height=400,width=800,scrollbars=1');
	if (window.focus) { newwindow.focus(); }
}

(function($) {
	// ready
	$(document).ready(function() {
		changeRuntime($("select#cboRuntimes").get(0));
		$("input.resettable").resettable();
		
		$("input[name='scheduleDay']").spinbox({
			min: 1,
			max: 28
		});
		$("input[name='scheduleHour']").spinbox({
			min: 0,
			max: 23
		});
		$("input[name='scheduleMinute']").spinbox({
			min: 0,
			max: 59
		});
		$("input[name='closingMonth']").spinbox({
			min: 1,
			max: 12
		});
		$("input[name='closingYear']").spinbox({
			min: 1,
			max: 9999
		});
	});
	
})(jQuery);

function userAccessViewMode() {
	var uAccess = 1;
	var mode_sp1 = "E-MEX-SP1";
	var mode_gr1 = "E-MEX-GR1";
	var mode_ct1 = "E-MEX-CT1";
	var retStatusStr = document.getElementById("retStatusStr").value;
	if(document.getElementById("subModule").value == mode_sp1){
	    if(document.getElementById("userAccess").value == uAccess){
    		document.forms[0].btnExecute.disabled = true;
    		document.forms[0].cboRuntimes.disabled = true;
    		document.forms[0].cboDeductionDate.disabled = true;
    		document.forms[0].cboClosingMonth.disabled = true;
    		document.forms[0].cboClosingYear.disabled = true;
    		document.forms[0].btnUpdate.disabled = true;
    		document.forms[0].rdbActive.disabled = true;
    		document.forms[0].rdbInActive.disabled = true;
    		document.forms[0].cboScheduleHour.disabled = true;
    		document.forms[0].cboScheduleMinute.disabled = true;
    		document.forms[0].scheduleDayDefault.disabled = true;
		}else{
		    if (retStatusStr=='N') {
		        document.forms[0].btnExecute.disabled = true;
		    }
		}
	}
	if(document.getElementById("subModule").value == mode_gr1){
	    if(document.getElementById("userAccess").value == uAccess){
    		document.forms[0].cboRuntimes.disabled = true;
    		document.forms[0].cboBankAcc.disabled = true;
    		document.forms[0].cboDeductionDate.disabled = true;
    		document.forms[0].scheduleDayDefault.disabled = true;
    		document.forms[0].cboScheduleMinute.disabled = true;
    		document.forms[0].cboScheduleHour.disabled = true;
    		document.forms[0].btnUpdate.disabled = true;
    		document.forms[0].closingYear.disabled = true;
    		document.forms[0].closingMonth.disabled = true;
    		document.forms[0].btnExecute.disabled = true;		
		}else{
            if (retStatusStr=='N') {
                document.forms[0].btnExecute.disabled = true;
            }
        }
	}
	if(document.getElementById("subModule").value == mode_ct1){
	    if(document.getElementById("userAccess").value == uAccess){
    		document.forms[0].cboRuntimes.disabled = true;
    		document.forms[0].cboBankAcc.disabled = true;
    		document.forms[0].cboClosingYear.disabled = true;
    		document.forms[0].cboClosingMonth.disabled = true;
    		document.forms[0].btnExecute.disabled = true;
    		document.forms[0].rdbActive.disabled = true;
    		document.forms[0].rdbInActive.disabled = true;
    		document.forms[0].scheduleDayDefault.disabled = true;
    		document.forms[0].cboScheduleHour.disabled = true;
    		document.forms[0].cboScheduleMinute.disabled = true;
    		document.forms[0].btnUpdate.disabled = true;
		}else{
            if (retStatusStr=='N') {
                document.forms[0].btnExecute.disabled = true;
            }
        }
	}
}