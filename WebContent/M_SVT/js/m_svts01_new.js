//add by Jing start
var FORWARD_SEARCH = "forward_search";
var FORWARD_SAVE = "forward_save";
var previousRowCount = 0;
var addCount=0;

function loadService() {
	if ($('#returnFlg').val() == "1") {
		var contextPath = $("#contextPath").val();
		//#157 modify start
		var parameters = $("#parameters").val();
		var idServiceListStr = $("#idServiceListStr").val();
		window.opener.location = contextPath 
			+ "/M_SVT/M_SVT01_Search.do?parameters=" + parameters + "&idServiceListStr=" + idServiceListStr + "&message=info.ERR2SC003"; 
		//#157 modify end
		window.close();
	}

	previousRowCount = $('.trPlanService').length;
}

function deleteRow(deleObj){
	var rowObj = $(deleObj).closest(".trPlanService");
	$('.error').html('');
	$('.message').html('');
	var MsgBox = new messageBox();
	if (MsgBox.POPCFM($('#hiddenMsgConfirmDeletetion').val()) == MsgBox.YES_CONFIRM) {
		rowObj.remove();
	}
}

function saveBtn() {
	$('.error').html('');
	$('.message').html('');
	//
	if ($('#cmbSerivceGroup option:first').is(':selected')) {
		$('.error').html(
				$('#hiddenMsgNoSelected').val().replace('{0}', "Category"));
		return;
	}
	if (!checkAllTab()) {
		return;
	}
	if (!CheckServiceNull()) {
		return;
	}
	if (!checkSpecialWords()) {
		return;
	}
	reIndex();
	submitForm(FORWARD_SAVE);
}

function reIndex() {
	var planServices = $('.trPlanService');
	var count = planServices.length;
	for ( var i = 0; i < count; i++) {
		var planService = planServices.eq(i);
		planService.find('.serviceCategory').attr('name',
				'planServiceList[' + i + '].serviceCategory');
		planService.find('.idService').attr('name',
				'planServiceList[' + i + '].idService');
		planService.find('.isUsed').attr('name',
				'planServiceList[' + i + '].isUsed');
		planService.find('.serviceDescription').attr('name',
				'planServiceList[' + i + '].serviceDescription');
		planService.find('.descAbbr').attr('name',
				'planServiceList[' + i + '].descAbbr');
		planService.find('.accCode').attr('name',
				'planServiceList[' + i + '].accCode');
		planService.find('.productCode').attr('name',
				'planServiceList[' + i + '].productCode');
		planService.find('.reference1').attr('name',
				'planServiceList[' + i + '].reference1');
		planService.find('.reference2').attr('name',
				'planServiceList[' + i + '].reference2');
		planService.find('.gst').attr('name', 'planServiceList[' + i + '].gst');
	}
}

// CheckServiceNull
function CheckServiceNull() {
	var planServices = $('.trPlanService');
	var count = planServices.length;
	for ( var i = 0; i < count; i++) {
		var planService = planServices.eq(i);
		if (planService.find('.serviceDescription').val() == null
				|| jQuery.trim(planService.find('.serviceDescription').val()) == "") {
			$('.error').html(
					$('#hiddenMsgNoSelected').val().replace('{0}', $('#title').val()));
			return false;
		}
		//#157 start
		if($('#title').val() == "Service" || $('#title').val() == "Plan"){
			if (planService.find('.descAbbr').val() == null
					|| jQuery.trim(planService.find('.descAbbr').val()) == ""){
				$('.error').html(
						$('#hiddenMsgNoSelected').val().replace('{0}', "Abbreviation"));
				return false;
			}
			if($('#title').val() == "Service"){
				if (planService.find('.accCode').val() == null
						|| jQuery.trim(planService.find('.accCode').val()) == ""){
					$('.error').html(
							$('#hiddenMsgNoSelected').val().replace('{0}', "Account Code"));
					return false;
				}
				if (planService.find('.productCode').val() == null
						|| jQuery.trim(planService.find('.productCode').val()) == ""){
					$('.error').html(
							$('#hiddenMsgNoSelected').val().replace('{0}', "Product Code"));
					return false;
				}
				if (planService.find('.reference1').val() == null
						|| jQuery.trim(planService.find('.reference1').val()) == ""){
					$('.error').html(
							$('#hiddenMsgNoSelected').val().replace('{0}', "Department"));
					return false;
				}
				if (planService.find('.reference2').val() == null
						|| jQuery.trim(planService.find('.reference2').val()) == ""){
					$('.error').html(
							$('#hiddenMsgNoSelected').val().replace('{0}', "Location"));
					return false;
				}
			}
		}
		//#157 end
	}
	return true;
}

// prompt error message ERR1SC107
function showMessage(repmessage) {
	var message = $("#ERR4SC107").val();
	message = message.replace('{0}', repmessage).replace('{1}', repmessage);
	var MsgBox = new messageBox();
	MsgBox.POPALT(message);
}

/**
 * Performing when click New Button
 */
function onClickNewAdd() {
	var tableService = $('#tableServiceType');
	$('.error').html('');
	$('.message').html('');
	var category;
	if ($('#title').val() == "Service") {
		category = "TYP";
	} else if ($('#title').val() == "Plan") {
		category = "ITM";
	} else {
		category = "DTL";
	}
	addCount+=1;
	var row = '<tr class="trPlanService">';
	row += '<td align="center">';
	row += '	<input type="hidden" class="serviceCategory" value="' + category
			+ '">';
	row += '	<input type="hidden" class="idService" value="">';
	row += '	<input type="hidden" class="isUsed" value="0">';
	row += '	<div class="hlDelete" style="width: 5%; cursor: hand;text-align: center;">';
	row += '		<strong>X</strong>';
	row += '	</div>';
	row += '</td>';
	//#157 start
	if ($('#title').val() != "Plan Detail") {
		row += '<td><input type="text" class="serviceDescription" maxlength="300" style="width: 100%"></td>';
		row += '<td><input type="text" class="descAbbr" maxlength="10" style="width: 100%"></td>';
	}else {
		row += '<td><input type="text" class="serviceDescription" maxlength="300" style="width: 75%"></td>';
		row += '<td  style="display:none;><input type="hidden" class="descAbbr" maxlength="10" style="width: 100%"></td>';
	}
	//#157 end
	if ($('#title').val() == "Service") {
		row += '<td><input type="text" class="accCode" maxlength="10" style="width: 100%"></td>';
		//Ver3.06 #278 [SIT] Issues found and fix: 1.M-CST, 2.M-SGV, 3.M-SVT CT 13072017
		if ($('#productCodeInput').val() == "0") {
			row += '<td><input type="text" class="productCode" maxlength="8" style="width: 100%"></td>';
		}else{
			row += '<td><select id="code'+addCount+'" class="productCode" style="width: 100%"><option>-Please select one-</option></select></td>';
		}
		//Ver3.06 #278 [SIT] Issues found and fix: 1.M-CST, 2.M-SGV, 3.M-SVT CT 13072017
		row += '<td><input type="text" class="reference1" maxlength="10" style="width: 100%"></td>';
		row += '<td><input type="text" class="reference2" maxlength="10" style="width: 100%"></td>';
	} else {
		row += '<input type="hidden" class="accCode" value="">';
		row += '<input type="hidden" class="productCode" value="">';
		row += '<input type="hidden" class="reference1" value="">';
		row += '<input type="hidden" class="reference2" value="">';
	}
	row += '</tr>';
	tableService.append(row);
	var rowDOM = tableService.find('.trPlanService:last');
	onClickDelete(rowDOM, rowDOM.find('.hlDelete'));
	//#219 start
	var category = $("#cmbSerivceGroup").val();
	$(".product_code").each(
   			function(i) {
   				if ($(this).val() == category) {
   					$("#code"+addCount).append("<option value='"+$(this).text()+"'>"+$(this).text()+"</option>");   
   				}
   			}
   		);
}
function onClickDelete(row, link) {
	link.click(function() {
				$('.error').html('');
				$('.message').html('');
				var MsgBox = new messageBox();
				if (MsgBox.POPCFM($('#hiddenMsgConfirmDeletetion').val()) == MsgBox.YES_CONFIRM) {
					row.remove();
				}
			});
}

function clickExit() {
	window.close();
}

function checkAllTab() {
	var serviceRow = $('tr[class=trPlanService]');
	if ((previousRowCount < 1) && (serviceRow.length < 1)) {
		$('.error').html($('#hiddenMsgMinRecord').val());
		return false;
	}
	return true;
}

function checkSpecialWords() {
	var isACCHaveSpecialWordsFlg = false;
	var isDerHaveSpecialWordsFlg = false;
	var isProHaveSpecialWordsFlg = false;
	var isRef1HaveSpecialWordsFlg = false;
	var isRef2HaveSpecialWordsFlg = false;
	var regexp = /[&]/;
	// Check accCode
	$('input[class=accCode]').each(function(index) {
		var acccode = $(this).val();
		if (regexp.test(acccode)) {
			isACCHaveSpecialWordsFlg = true;
		}
	});
	// Check Description Abbreviation
	$('input[class=descAbbr]').each(function(index) {
		var descabbr = $(this).val();
		if (regexp.test(descabbr)) {
			isDerHaveSpecialWordsFlg = true;
		}
	});
	// Check productCode
	$('select[class=productCode]').each(function(index) {
		var proCd = $(this).val();
		if (regexp.test(proCd)) {
			isProHaveSpecialWordsFlg = true;
		}
	});
	//Ver3.06 #278 [SIT] Issues found and fix: 1.M-CST, 2.M-SGV, 3.M-SVT CT 13072017
	$('input[class=productCode]').each(function(index) {
		var proCd = $(this).val();
		if (regexp.test(proCd)) {
			isProHaveSpecialWordsFlg = true;
		}
	});
	//Ver3.06 #278 [SIT] Issues found and fix: 1.M-CST, 2.M-SGV, 3.M-SVT CT 13072017
	// Check Reference1
	$('input[class=reference1]').each(function(index) {
		var reference1 = $(this).val();
		if (regexp.test(reference1)) {
			isRef1HaveSpecialWordsFlg = true;
		}
	});
	// Check Reference2
	$('input[class=reference2]').each(function(index) {
		var reference2 = $(this).val();
		if (regexp.test(reference2)) {
			isRef2HaveSpecialWordsFlg = true;
		}
	});
	if (isDerHaveSpecialWordsFlg) {
		showMessage("Abbreviation");
		return false;
	}
	if (isACCHaveSpecialWordsFlg) {
		showMessage("Account Code");
		return false;
	}
	if (isProHaveSpecialWordsFlg) {
		showMessage("Product Code");
		return false;
	}
	//#157 start
	if (isRef1HaveSpecialWordsFlg) {
		showMessage("Department");
		return false;
	}
	if (isRef2HaveSpecialWordsFlg) {
		showMessage("Location");
		return false;
	}
	//#157 end
	return true;
}
function submitForm(event) {
	var hiddenEvent = '<input type="hidden" name="event" value="' + event
			+ '">';
	$('form').append(hiddenEvent);
	$('form').submit();
}
// add by Jing end
// #219 start 
function onCategoryChage(obj) {
	var category = $(obj).val();
	if (category != ""){
		for (var i=1; i<=addCount;i++) {
			var selectObj = $("#code"+i);
			selectObj.empty();
			selectObj.append("<option value=''>-Please select one-</option>");   
			$(".product_code").each(
		   			function(j) {
		   				if ($(this).val() == category) {
		   					selectObj.append("<option value='"+$(this).text()+"'>"+$(this).text()+"</option>");   
		   				}
		   			}
			   	);	
		}
	}
}
//#219 end
