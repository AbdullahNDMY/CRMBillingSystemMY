var isDelBtn = false;
// check which button was pressed
document.onclick = function(whichOne){ 
	whichOne = whichOne ? whichOne : window.event;
	thisButton = whichOne.target ? whichOne.target : whichOne.srcElement;
	if (( thisButton.name ) && ( thisButton.name == 'forward_delete' )) 
		isDelBtn = true;
	else
		isDelBtn = false;
}
$(document).ready(function() {
	// runtime type change
	runtimeTypeChange(true);
	$('#runtimeType').change(function(){
		runtimeTypeChange(false);
	});
	// radio status change
	activeStatusChange();
	$('input[name="activeStatus"]').click(function(){
		activeStatusChange();
	});
	function runtimeTypeChange(check){
		if(check) {
			if($('#activeStatusH').val() == '0') {// In-Active
				$("#scheduler").hide();
				$("#manualExecute").show();
			}
			else {// Active
				$("#scheduler").show();
				$("#manualExecute").hide();
			}
		}
		else {
			if($('#runtimeType').val() == '1'){
				$("#scheduler").show();
			}
			else {
				$("#scheduler").hide();
			}
			if($('#runtimeType').val() == '2'){
				$("#manualExecute").show();
			}
			else {
				$("#manualExecute").hide();
			}
		}
	}
	function activeStatusChange(){
		var activeStatus = $('input[name="activeStatus"]:checked').val();
		if(activeStatus == 0){// in-active
			$('#selDay').attr('disabled', 'disabled');
			$('#selHour').attr('disabled', 'disabled');
			$('#selMinute').attr('disabled', 'disabled');
		}else{// active
			$('#selDay').removeAttr('disabled');
			$('#selHour').removeAttr('disabled');
			$('#selMinute').removeAttr('disabled');
		}
	}
	$("input[name='selDay']").spinbox({
		min: 1,
		max: 28
	});
	$("input[name='selHour']").spinbox({
		min: 0,
		max: 23
	});
	$("input[name='selMinute']").spinbox({
		min: 0,
		max: 59
	});
	$("input[name='month']").spinbox({
		min: 1,
		max: 12
	});
	$("input[name='year']").spinbox({
		min: 1,
		max: 9999
	});
});
function changeScheduleStatus(thiz) {
	if (thiz.value == "0") {
		var objs = $("#scheduler-time input");
		objs.readonly();
		objs.resetValue();
	} else {
		$("#scheduler-time input").readonly(false);
	}
}