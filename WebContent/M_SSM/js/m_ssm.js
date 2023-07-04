
var isSubmitted = false;
var totalRow;
var totalColumn;
var resultTableWidth;
var serviceRow;
var previousRowCount = 0;

function init()
{
//	totalRow = $('table[class=listTable]').find('tr[title=dataRow]').length;
	totalRow = $('table[id=data]').find('tr[class=tRow]').length;
	totalColumn = $('table[class=listTable]').find('th[title=header]').length;
	resultTableWidth = 30 + 160 + 80*totalColumn;
	$('#labelDiv').css('width', $('#subHeader').attr('clientWidth')-$('#serviceTypeTable').attr('clientWidth'));
	$('#dataDiv').css('width', $('#subHeader').attr('clientWidth')-$('#serviceTypeTable').attr('clientWidth'));
	serviceRow = parseInt($('#serviceRow').val());
	if (isNaN(serviceRow))
		serviceRow = 10;
	var lower = totalRow;
	if (serviceRow < lower)
		lower = serviceRow;
	$('#serviceDiv').css('height', lower*31+20-20);
	$('#dataDiv').css('height', lower*31+20);
	
//	previousRowCount = $('table[class=listTable]').find('tr[title=dataRow]').length;
	previousRowCount = totalRow;
}

function selectServiceGroup()
{
	document.getElementById("actionType").value = "view";
	document.forms["_m_ssmForm"].submit();
}

function checkForm() {
	if(isSubmitted) return false;
	var isError = false;

	// Check mandatory for Service Type
	if(checkServiceTypeMandatory()) {
		return false;
	}
	
	// Check dupplicate for Service Type
	if(checkDupplicate()) {
		return false;
	}
	
//	totalRow = $('table[class=listTable]').find('tr[title=dataRow]').length;
	totalRow = $('table[id=data]').find('tr[class=tRow]').length;
	if ((previousRowCount<1) && (totalRow < 1))
	{
		var minRecordMessage = $('#minRecordMessage').val().replace('{0}', $('#labelService').val());
		$('#error_area').text(minRecordMessage);
		return false;
	}
	
	document.getElementById("actionType").value = "update";

	isSubmitted = true;
	return true;
}

function submitDelete(ctr, idService) {
	$('#success_area').text("");
	$('#error_area').text("");	
	
	// Confirmation message
	var MsgBox = new messageBox();
	if (MsgBox.POPCFM($('#confirmDeleteMessage').val()) == MsgBox.YES_CONFIRM) {
		document.getElementById("actionType").value = "update";
		var serviceTable = document.getElementById("serviceTable");
		var dataTable = document.getElementById("data");
		var index = $(ctr).closest('tr').attr('rowIndex');
		serviceTable.deleteRow(index);
		dataTable.deleteRow(index);
		var input = document.createElement("input");
		input.name = "deletedIdService";
		input.type = "hidden";
		input.value = idService;
		document.getElementById("deleteDiv").appendChild(input);
	}
	
//	totalRow = $('table[class=listTable]').find('tr[title=dataRow]').length;
	totalRow = $('table[id=data]').find('tr[class=tRow]').length;
	updateHeight(totalRow);
}

function addTableRow() {
	$('#success_area').text("");
	$('#error_area').text("");
	
	// Get the wagon content table
	var resultTable = document.getElementById("serviceTable");

	// Insert a new row into the table
	var elementRow = resultTable.insertRow(resultTable.rows.length);
	elementRow.id = "row_" + totalRow;
	
	// Set data to new row
	// Insert delete link cell
	var elementCol = elementRow.insertCell();
	elementCol.className += "deleteCell";
	elementCol.align = "center";
	elementCol.innerHTML = "<div style=\"cursor:hand; width:30px\" onclick=\"deleteRow(this);\"><b>X</b></div>"; 

	// Insert Service Type cell (select box)
	elementCol = elementRow.insertCell();
	elementCol.className = "serviceLabelCell";
	elementCol.align = "left";
	elementCol.style.borderBottom = "#efebef 1px solid";
	var selectBox = $('#idServiceClone').clone();
	selectBox.removeAttr('id');
	selectBox.removeAttr('style');
    elementCol.innerHTML = selectBox.attr('outerHTML');

	// Insert Entry/Report checkboxes
    resultTable = document.getElementById("data");
    elementRow = resultTable.insertRow(resultTable.rows.length);
	elementRow.className = "tRow";
	elementRow.id = "row_" + totalRow;
	for(var i = 0; i < totalColumn; i ++) {
		var elementCol1 = elementRow.insertCell();
		elementCol1.align = "center";
		elementCol1.style.borderBottom= "#efebef 1px solid";
		//Service Information coloum
		if(i==(totalColumn-1)) {
			elementCol1.innerHTML = '<div class="checkCell"><input id=\'xentry_' + totalRow + '_' + i + '\' onclick="checkER(\'entry_' + totalRow + '_' + i + '\');" name="xentry" type="checkbox" disabled="disabled"/>';
		} else {
			elementCol1.innerHTML = '<div class="checkCell"><input id=\'xentry_' + totalRow + '_' + i + '\' onclick="checkER(\'entry_' + totalRow + '_' + i + '\');" name="xentry" type="checkbox" checked="checked"/>';
		}
		elementCol1.innerHTML += '<input type="hidden" id=\'entry_' + totalRow + '_' + i + '\' name="entry" value="1"/></div>';
		elementCol1.innerHTML += '<input type="hidden" name="infoId" value="'+(i+1)+'"/>';
		elementCol1 = elementRow.insertCell();
		elementCol1.align = "center";
		elementCol1.style.borderBottom = "#efebef 1px solid";
		elementCol1.innerHTML = '<div class="checkCell"><input id=\'xreport_' + totalRow + '_' + i + '\' onclick="checkER(\'report_' + totalRow + '_' + i + '\');" name="xreport" type="checkbox" checked="checked"/>';
		elementCol1.innerHTML += '<input type="hidden" id=\'report_' + totalRow + '_' + i + '\' name="report" value="1"/></div>';
	}
	
//	totalRow = $('table[class=listTable]').find('tr[title=dataRow]').length;
	totalRow = $('table[id=data]').find('tr[class=tRow]').length;
	updateHeight(totalRow);
	var dataDivObj = document.getElementById("dataDiv");
	dataDivObj.scrollTop = dataDivObj.scrollHeight-dataDivObj.clientHeight;
	$('#serviceTable').css('top', dataDivObj.clientHeight-dataDivObj.scrollHeight);
}

function deleteRow(ctr) {
	$('#success_area').text("");
	$('#error_area').text("");
	
	var serviceTable = $('#serviceTable');
	var data = $('#data');
	var serviceTrs = serviceTable.find('tr');
	var dataTrs = data.find('tr');
	var deleteTr = $(ctr).closest('tr');
	//identify the row to delete
	var deleteIndex = deleteTr.attr('rowIndex');
	serviceTrs.eq(deleteIndex).remove();
	dataTrs.eq(deleteIndex).remove();
	
//	totalRow = $('table[class=listTable]').find('tr[title=dataRow]').length;
	totalRow = $('table[id=data]').find('tr[class=tRow]').length;
	updateHeight(totalRow);
	scroll(document.getElementById("dataDiv"));
}

function updateHeight(rowCount)
{
	var lower = rowCount;
	if (serviceRow < lower)
		lower = serviceRow;
	$('#serviceDiv').css('height', lower*31+20-20);
	$('#dataDiv').css('height', lower*31+20);
}

// When user check or uncheck report/entry
function checkER(id) {
	var checkBox = document.getElementById('x' + id);
	var hiddenField = document.getElementById(id);
	if(checkBox.checked) {
		hiddenField.value = "1";
	} else {
		hiddenField.value = "0";
	}

	// Check if this checkbox is "Entry"
	if(id.indexOf("entry") >= 0) {
		var newId = id.replace("entry_","report_");
		// If this checkbox is checked then enable "Report" checkbox and vice versa
		if(document.getElementById('x' + id).checked) {
			document.getElementById('x' + newId).disabled = "";
		} else if(document.getElementById('x' + id).checked==false){
			if(document.getElementById('x' + newId).checked){
				document.getElementById('x' + newId).checked=false;
				document.getElementById(newId).value='0';
			}
			document.getElementById('x' + newId).disabled = "disabled";
		}
	}
}

// Check mandatory for service type
function checkServiceTypeMandatory() {
	document.getElementById("error_area").innerHTML = "";
	document.getElementById("success_area").innerHTML = "";

	var selectes = $('table[class=listTable]').find('select[name=idService]');

	for(var i = 0; i < selectes.length; i++) {		
		var selectedValue = selectes.eq(i).find('option:selected').val();
		if(selectedValue == "") {
			var strMessage = $('#mandatoryMessage').val().replace('{0}', $('#labelService').val());
			document.getElementById("error_area").innerHTML +=  strMessage;
			return true;
		}
	}

	return false;
}

// Check dupplicate for Service Type
function checkDupplicate() {
	document.getElementById("error_area").innerHTML = "";
	document.getElementById("success_area").innerHTML = "";

	var selectes = $('table[class=listTable]').find('select[name=idService]');
	var inputs = $('table[class=listTable]').find('input[name=idService]');
	var bag = new Array();
	var i;
	for (i=0; i<inputs.length; i++)
		bag[bag.length] = inputs.eq(i).val();
	for (i=0; i<selectes.length; i++)
		bag[bag.length] = selectes.eq(i).find('option:selected').val();

	for(i = 0; i < bag.length-1; i++)
	{
		for(var j = i+1; j < bag.length; j++)
		{
			if (bag[i] == bag[j])
			{
				var strMessage = $('#duplicateMessage').val();
				document.getElementById("error_area").innerHTML +=  strMessage;
				return true;
			}
		}
	}
	return false;
}

function scroll(ctr)
{
	$('#serviceTable').css('top', 0-ctr.scrollTop);
	$('#labelTable').css('left', 0-ctr.scrollLeft);
}

