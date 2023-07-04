$(document).ready(function(){
	//set updateStatement disabled
	updateStatementBtnDisabled();
	
	$("#selectAll").click(function(){
		var checked_status = this.checked;
		$('input[name="selectedStatementNo"]').each(function(){
			if(checked_status){
				$(this).attr('checked', true);
			}else{
				$(this).attr('checked', false);
			}
		});
	});
});
function printClick(){
	$("#printType").val("print");
}
function printAllClick(){
	$("#printType").val("printAll");
}
function searchClick(type) {
	$("#clickBtnTypeFlg").val(type);
}

function updateStatementBtnDisabled() {
	var totalRow = $("#totalRow").val();
	var inputMM = $("#cboStatementMonth").val();
	var inputYYYY = $("#tbxStatementYear").val();
	var inputYearMonth = inputYYYY.toString()+inputMM.toString();
	var sysdateMM = $("#sysdateMM").val();
	var sysdateYYYY = $("#sysdateYYYY").val();
	var sysdateYearMonth = sysdateYYYY.toString()+sysdateMM.toString();
    if (sysdateMM == '1') {
       var preMonthSysdateYearMonth = (parseInt(sysdateYYYY,10)-1).toString()+'12';
    }else{
       var preMonthSysdateYearMonth = sysdateYYYY.toString()+(parseInt(sysdateMM,10)-1).toString(); 
    }
	if((inputYearMonth!=sysdateYearMonth && inputYearMonth!=preMonthSysdateYearMonth) || (totalRow==null || totalRow=="" || totalRow==0)) {
		$("#updateStatement").attr("disabled","true");
	} else {
		$("#updateStatement").removeAttr("disabled");
	}
}