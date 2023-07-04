var FORWARD_SEARCH = "forward_search";
var FORWARD_SAVE = "forward_save";
var previousRowCount = 0;

$(function(){
	//init tab
	if($('#hiddenFirstChoosed').val() != "") {
		var search =new ddtabcontent("serviceMaintainence");
		search.setpersist(true);
		search.setselectedClassTarget("link"); //"link" or "linkparent"
		search.init();
	}
	//init form
	$('form').submit(function(){
		//enable all field being disabled
		$('input').attr('disabled',false);
		//reIndex all PlanService
		reIndex();
	});
	//cmbServiceGroup
	$('#cmbSerivceGroup').change(function(){
		//If please select one is selected, displaying error message
		if($('#cmbSerivceGroup option:first').is(':selected')) {
			$('.error').html($('#hiddenMsgNoSelected').val());
		} else {
			//Remove error
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
	
	//-------------------  SERVICE GROUP  --------------------//
	//service group name if cmbServiceGroup has been choosed
	var serviceGroupName = $('#serviceGroupName').html() + $('#cmbSerivceGroup option:selected').html();
	$('#serviceGroupName').html(serviceGroupName);
	//-------------------  SERVICE TYPE  --------------------//
	initPlanService($('#divServiceType'));
	//-------------------  SERVICE ITEM  --------------------//
	initPlanService($('#divServiceItem'));
	//-------------------  SERVICE ITEM  --------------------//
	initPlanService($('#divPlanDetail'));
	
	$('#link1').click(function(){
		setTabValue('1');
	});
	$('#link2').click(function(){
		setTabValue('2');
	});
	$('#link3').click(function(){
		setTabValue('3');
	});
	var tabValue = $('#tabValue').val();
	if(tabValue==""){
	   tabValue="1";
	}
	tabValue = "link"+ tabValue;
	//alert("tapValue: "+tapValue);
	$('#'+tabValue).click();
	
	//btnSave
	$('#btnSave').click(function(){
		$('.error').html('');
		$('.message').html('');
		//if user have choose another service group, then restoring the first service group
		$('#cmbSerivceGroup option[value="' + $('#hiddenFirstChoosed').val() + '"]').attr('selected',true);
		if (!checkAllTab())
			return;
		var errMsgs = new Array();
		var typChk = checkTab('TYP', errMsgs);
		var itmChk = checkTab('ITM', errMsgs);
		var dtlChk = checkTab('DTL', errMsgs);
		if (!typChk || !itmChk || !dtlChk)		
		{
			var temp = "";
			for (var i=0; i<errMsgs.length; i++)
			{
				temp += "<br/>";
				temp += errMsgs[i];
			}
			$('.error').html(temp);
			return;
		}
		if(!checkSpecialWords()){
			return;
		}
		submitForm(FORWARD_SAVE);
	});
	
	var wrapperDiv = $('.wrapper');
	var serviceRow = $('#serviceRow').val();
	for (var i=0; i<wrapperDiv.length; i++)
	{
		var table = wrapperDiv.eq(i).find('table');
		var marginTop = parseInt(table.css('margin-top').replace('px',''));
		if (isNaN(marginTop))
			marginTop = 0;
		var marginBottom = parseInt(table.css('margin-bottom').replace('px',''));
		if (isNaN(marginTop))
			marginTop = 0;
		var trHeader = table.find('.header');
		var headerHeight = trHeader.attr('clientHeight');
		var trService = table.find('.trPlanService');
		var rowHeight = trService.attr('clientHeight');
		wrapperDiv.eq(i).css('max-height', marginTop+(headerHeight+2)+serviceRow*rowHeight+marginBottom+5);
		var sampleRow = table.find('tr[title=sample]');
		sampleRow.remove();
	}
	
	previousRowCount = $('.trPlanService').length;
});

function initPlanService(divPlanService) {
	var category = divPlanService.find('.category').val();
	//add
	divPlanService.find('.hlAdd').click(function(){
		onClickAdd(divPlanService,category);
	});

	//rows
	var trPlanService = divPlanService.find('.trPlanService');
	for(var i = 0;i < trPlanService.length;i++){
		if(trPlanService.eq(i).find('.isUsed').val() == "1"){
			//if service is used, so disable it
			trPlanService.eq(i).find('input').attr('disabled',true);
			trPlanService.eq(i).find('.hlDelete').click(function(){});
			trPlanService.eq(i).find('.hlDelete').attr('class','disableLink');
		} else {
			onClickDelete(trPlanService.eq(i),trPlanService.eq(i).find('.hlDelete'));
		}
	}
}

function onClickAdd(divPlanService,category) {
	$('.error').html('');
	$('.message').html('');
	var row = '<tr class="trPlanService">';
		row	+=	'<td>';
		row +=	'	<input type="hidden" class="serviceCategory" value="' + category + '">';
		row +=	'	<input type="hidden" class="idService" value="">';
		row +=	'	<input type="hidden" class="isUsed" value="0">';
		row +=  '	<div class="hlDelete" style="width: 30px; cursor: hand;text-align: center;">';
		row +=	'		<strong>X</strong>';
   		row +=	'	</div>';
		row	+=	'</td>';
		row +=	'<td><input type="text" class="serviceDescription" maxlength="300"></td>';
		row	+=	'<td><input type="text" class="accCode" maxlength="30"></td>';
		row	+=	'<td><input type="text" class="descAbbr" maxlength="10"></td>';
		//row	+=	'<td><input type="checkbox" value="1" class="gst"></td>';
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
		//if(confirm($('#hiddenMsgConfirmDeletetion').val())) {
			row.remove();
		}	
	});
}

function reIndex() {
	var planServices = $('.trPlanService');
	var count = planServices.length;
	for(var i = 0; i < count; i++) {
		var planService = planServices.eq(i);
		planService.find('.serviceCategory').attr('name','listPlanService[' + i + '].serviceCategory');
		planService.find('.idService').attr('name','listPlanService[' + i + '].idService');
		planService.find('.isUsed').attr('name','listPlanService[' + i + '].isUsed');
		planService.find('.serviceDescription').attr('name','listPlanService[' + i + '].serviceDescription');
		planService.find('.accCode').attr('name','listPlanService[' + i + '].accCode');
		planService.find('.descAbbr').attr('name','listPlanService[' + i + '].descAbbr');
		planService.find('.gst').attr('name','listPlanService[' + i + '].gst');
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

function checkTab(type, msgArray)
{
	var stone = $('input[class=category]').filter('[value='+type+']');
	var div = stone.closest('table');
	var category = div.find('input[class=label]').val();
	var serviceNames = div.find('input[class=serviceDescription]');
	
	for (var i=0; i<serviceNames.length; i++)
	{
		if (jQuery.trim(serviceNames.eq(i).val()) == "")
		{
			msgArray[msgArray.length] = $('#hiddenMsgMandatory').val().replace('{0}', category);
			return false;
		}
	}
	return true;
}
 
function checkSpecialWords(){
	var isACCHaveSpecialWordsFlg=false;
	var isDerHaveSpecialWordsFlg=false;
	var regexp=/[&]/;
	//Check Product Code 
	$('input[class=accCode]').each(function(index){
		var acccode=$(this).val();
		if(regexp.test(acccode)){
			isACCHaveSpecialWordsFlg=true;
		}
	});
	//Check Description Abbreviation 
	$('input[class=descAbbr]').each(function(index){
		var descabbr=$(this).val();
		if(regexp.test(descabbr)){
			isDerHaveSpecialWordsFlg=true;
		}
	});
	if(isACCHaveSpecialWordsFlg){
		showMessage("Product Code");
		return false;
	}
	if(isDerHaveSpecialWordsFlg){
		showMessage("Description Abbreviation");
		return false;
	}
    return true;
}
//prompt error message ERR1SC107 
function showMessage(repmessage){
	var message = $("#ERR4SC107").val();
	message=message.replace('{0}',repmessage).replace('{1}',repmessage);
	var MsgBox = new messageBox();
	MsgBox.POPALT(message);
}

function setTabValue(tabValue){
	//alert(tapValue);
	$('#tabValue').val(tabValue);
}
