function addNew()
{
	var count = $('#mainTable').find('input[alt=svc_grp]').length;
	var cloneRow = $('#cloneRow').clone();
	cloneRow.find('input[alt=svc_grp]').attr('name', 'serviceGroupBean['+count+'].svc_grp');
	cloneRow.find('input[alt=svc_grp_name]').attr('name', 'serviceGroupBean['+count+'].svc_grp_name');
	cloneRow.find('input[alt=origin_code]').attr('name', 'serviceGroupBean['+count+'].origin_code');
	cloneRow.find('input[alt=acc_code]').attr('name', 'serviceGroupBean['+count+'].acc_code');
	//wcbeh #231 - Category Maintenance Add Journal No
	cloneRow.find('select[title=journal_no]').attr('name', 'serviceGroupBean['+count+'].journal_no');
	cloneRow.find('input[alt=product_code]').attr('name', 'serviceGroupBean['+count+'].product_code');
	cloneRow.find('input[alt=remark]').attr('name', 'serviceGroupBean['+count+'].remark');
	cloneRow.removeAttr('id');
	$('#mainTable').append(cloneRow);
}
//Check sepcial words 
function onSaveClick(){
	var isHaveSpecialWordsFlg=false;
	var isHaveSpecialWordsFlg_originCode=false;
	var accCode="Account / Revenue Code";
	var oriCode="Origin Code";
	$("#mainTable").find("#originCode").each(function(index){
		//$(this).text(index+1);
		var cusPo=$(this).val();
		var regexp=/[&]/;
		if(regexp.test(cusPo)){
			isHaveSpecialWordsFlg_originCode=true;
		}
	});
	$("#mainTable").find("#tbxacccode").each(function(index){
		//$(this).text(index+1);
		var cusPo=$(this).val();
		var regexp=/[&]/;
		if(regexp.test(cusPo)){
			isHaveSpecialWordsFlg=true;
		}
	});
	if(isHaveSpecialWordsFlg_originCode){
		showMsg(oriCode);
		return false;
	}
	if(isHaveSpecialWordsFlg){
		showMsg(accCode);
		return false;
	}
	
}

//show  message
function showMsg(code){
	
	var message = $("#ERR4SC107").val();
	message=message.replace('{0}', code).replace('{1}', code);
	var MsgBox = new messageBox();
	MsgBox.POPALT(message);
} 