(function($) {
	// ready
	$(document).ready(function() {
		$("input[name='month']").spinbox({
			min: 1,
			max: 12
		});
		$("input[name='year']").spinbox({
			min: 1,
			max: 9999
		});
	});
	
})(jQuery);

function popup_e_mim_us2(context, idBatch) {
	popup(context+ '/E_MIM/RP_E_MIM_US2_03BL.do?idClcImpBatch='+idBatch);
}

function confirmYearMonth(){
	var month = 0;
	var year = 0;
	var currentMonth = 0;
	var currentYear = 0;
	
	var monthValue = document.getElementById("month").value;
	if(!isNaN(monthValue)){
		month = parseInt(monthValue);		
	}
	var yearValue = document.getElementById("year").value;
	if(!isNaN(yearValue)){
		year = parseInt(yearValue);		
	}
	var currentMonthValue = document.getElementById("currentMonth").value;
	if(!isNaN(currentMonthValue)){
		currentMonth = parseInt(currentMonthValue);		
	}
	var currentYearValue = document.getElementById("currentYear").value;
	if(!isNaN(currentYearValue)){
		currentYear = parseInt(currentYearValue);		
	}
	var message = document.getElementById("info.ERR4SC012").value;
	
	if (year > currentYear || (year == currentYear && month > currentMonth)) {
		// show message if selected month year > system date
		var MsgBox = new messageBox();
		if (MsgBox.POPCFM(document.getElementById("info.ERR4SC012").value) == MsgBox.YES_CONFIRM) {
			return true;
		}
	} else {
		return true;
	}
	return false;
}

function userAccessViewMode() {
	var uAccess = 1;
	var mode_us2 = "E-MIM-US2";
	var retStatusStr = document.getElementById("retStatusStr").value;
	if(document.getElementById("subModule").value == mode_us2){
	    if(document.getElementById("userAccess").value == uAccess){
    		document.forms[0].cboMonth.disabled = true;
    		document.forms[0].cboYear.disabled = true;
    		document.forms[0].btnUpload.disabled = true;	
    		document.forms[0].txtBrowse.disabled = true;	
		}else{
		    if(retStatusStr=='N'){
		        document.forms[0].btnUpload.disabled = true;
		    }
		}
	}
}