var FORWARD_SEARCH = "forward_search";
var FORWARD_SAVE = "forward_save";

$(function(){		
	$('#cmbSerivceGroup').change(function(){
		if($('#cmbSerivceGroup option:first').is(':selected')) {
			$('.error').html($('#hiddenMsgNoSelected').val());
		} else {
			$('.error').html('');
		}
	});
	//btnSearch
	$('#btnSearch').click(function(){
		//If please select one is selected, don't execute search action
		if($('#cmbSerivceGroup option:first').is(':selected')) {
			$('.error').html($('#hiddenMsgNoSelected').val());
			return;
		}
		var queryString = '&event=' + FORWARD_SEARCH + '&choosed=' + $('#cmbSerivceGroup').val();
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
		$('#cmbSerivceGroup option[value="' + $('#hiddenFirstChoosed').val() + '"]').attr('selected',true);
		submitForm(FORWARD_SAVE);
	});
	
});


function redirect(queryString) {
	var location = $('form').attr('action');
	window.location = location + queryString;
}
function submitForm(event) {	
	var hiddenEvent = '<input type="hidden" name="event" value="' + event + '">';	
	$('form').append(hiddenEvent);
	$('form').submit();
}