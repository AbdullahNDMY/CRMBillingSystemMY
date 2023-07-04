var FORWARD_SEARCH = "forward_search";
var FORWARD_SAVE = "forward_save";
var previousRowCount = 0;

$(function(){

	var MsgBox = new messageBox();
	initPlanService($('#divServiceType'));
	//init form
	$('form').submit(function(){
		$('input').attr('disabled',false);
		reIndex();
	});
	//btnSearch
	$('#btnSearch').click(function(){
		//If please select one is selected, don't execute search action
		if($("#cmbSerivceGroup").val()=="") {
			$(".error").html($("#hiddenMsgNoSelected").val());
			return;
		}
		var queryString = '&event=' + FORWARD_SEARCH + '&choosed=' + $("#cmbSerivceGroup").val();
		redirect(queryString);
	});
	//btnReset
	$('#btnReset').click(function(){
		$('#cmbSerivceGroup option:first').attr('selected',true);
		var searchResult = $('#searchResult');
		if (searchResult.length > 0)
			$('#searchResult').remove();
		$('div.error').html('');
		$('div.message').html('');
	});
	
	//btnSave
	$('#btnSave').click(function(){
		$('.error').html('');
		$('.message').html('');
		//if user have choose another service group, then restoring the first service group
		$('#cmbSerivceGroup option[value="' + $('#hiddenFirstChoosed').val() + '"]').attr('selected',true);
		if (!checkAllTab())
			return;

		var isMessage = false;
        var selectedType = $('#cmbSerivceGroup').val();
		$(".trPlanService").each(function(){

		    var newId = $(this).find('.id');
		    var newName = $(this).find('.name');
            var oldId = newId.next().val();
		    var oldName = newName.next().val();
            if(oldName){
                if(newId.val()!= oldId || newName.val() != oldName){
                    isMessage = true;
                    return false;
                }
            }
		});

        if(isMessage){
            var msg = $("#message_group").find(".messageNameRevise").text().replace(/\{0\}*/g,selectedType);
            if (MsgBox.POPCFM(msg) == MsgBox.YES_CONFIRM) {
                
            }else {
                return false;
            }
        }
        submitForm(FORWARD_SAVE);
	});

	previousRowCount = $('.trPlanService').length;
});

function initPlanService(divPlanService) {
	//add
	divPlanService.find('.hlAdd').click(function(){
		onClickAdd(divPlanService);
	});
	//rows
	divPlanService.find('.trPlanService').each(function(){
		if($(this).find('.isUsed').val() == "1"){
			$(this).find('.id').attr('disabled',true);
			$(this).find('.hlDelete').hide();
		}else{
			onClickDelete($(this),$(this).find('.hlDelete'));
		}
	});
}

function onClickAdd(divPlanService) {
	$('.error').html('');
	$('.message').html('');
	var row = '<tr class="trPlanService">';
		row	+=	'<td>';
		row +=	'	<input type="hidden" class="isUsed" value="0">';
		row +=  '	<div class="hlDelete" style="width: 30px; cursor: hand;text-align: center;">';
		row +=	'		<strong>X</strong>';
   		row +=	'	</div>';
		row	+=	'</td>';
		if($('#hiddenFirstChoosed').val() == "Role"){
			row +=	'<td><input type="text" class="id" maxlength="10" style="width:95%;overflow:hidden;"></td>';
		} else {
			row +=	'<td><input type="text" class="id" maxlength="4" style="width:95%;overflow:hidden;"></td>';
		}		
		row	+=	'<td><input type="text" class="name" maxlength="14" style="width:98%;overflow:hidden;"></td>';
		row	+=	'</tr>';	
	divPlanService.find('table').append(row);
	var rowDOM = divPlanService.find('table .trPlanService:last');
	onClickDelete(rowDOM,rowDOM.find('.hlDelete'));
}
function onClickDelete(row,link) {
	link.click(function(){
		$('.error').html('');
		$('.message').html('');
		var MsgBox = new messageBox();
		if (MsgBox.POPCFM($('#hiddenMsgConfirmDeletetion').val()) == MsgBox.YES_CONFIRM) {
			row.remove();
		}	
	});
}

function reIndex() {
	var planServices = $('.trPlanService');
	var count = planServices.length;
	for(var i = 0; i < count; i++) {
		var planService = planServices.eq(i);		
		planService.find('.isUsed').attr('name','listPlanService[' + i + '].isUsed');
		planService.find('.id').attr('name','listPlanService[' + i + '].id');
		planService.find('.name').attr('name','listPlanService[' + i + '].name');
	}
}
function redirect(queryString) {
	var location = $('form').attr('action');
	window.location = location + queryString;
}
function submitForm(event) {
	var hiddenEvent = '<input type="hidden" name="event" value="' + event + '">';
	$('form').append(hiddenEvent);
	$('form').submit();
}

function checkAllTab()
{
	var serviceRow = $('tr[class=trPlanService]');
	if ((previousRowCount<1) && (serviceRow.length < 1))
	{
		$('.error').html($('#hiddenMsgMinRecord').val());
		return false;
	}
	return true;
}
