function initPage(){
	computeContractValue('contractTb');
	var pop = document.getElementById("showPopalt").value;
	pop=ReplaceAll(pop,"***","\n");
	if( pop != ""){
		new messageBox().POPALT(pop);
	}
	determineSequenceOfDisplayingGroup();
	determineApprovalStatus();
}
function clickObtainApproval(msg){
	msg=ReplaceAll(msg,"***","\n");
	document.getElementById("clickEvent").value="3";
	var MsgBox = new messageBox();
	if (MsgBox.POPCFM(msg) == MsgBox.YES_CONFIRM) {
	//if(confirm(msg)){
		document.getElementById("obt_appr_save").value="y";
	}else{
		document.getElementById("obt_appr_save").value="n";
	}
}
function clickObtainApprovalSectGrp(msg,section_group){
	msg=ReplaceAll(msg,"***","\n");
	document.getElementById("clickEvent").value="5";
	var MsgBox = new messageBox();
	if (MsgBox.POPCFM(msg) == MsgBox.YES_CONFIRM) {
	//if(confirm(msg)){
		document.getElementById("obt_appr_save").value="y";
	}else{
		document.getElementById("obt_appr_save").value="n";
	}
	document.getElementById("section_group").value =section_group;
}
function clickSave(){
	//document.getElementById("clickEvent").value= "2";
}
function clickEdit(){
	
	document.getElementById("clickEvent").value= "7";
		
}
function clickDownload(filename){
	document.getElementById('file_name').value = filename;
	document.getElementById('forward_download').click();
}
function clickDelete(msg){
	msg=ReplaceAll(msg,"***","\n");
	var MsgBox = new messageBox();
	if (MsgBox.POPCFM(msg) == MsgBox.YES_CONFIRM) {
	//if (confirm(msg)){
		document.getElementById("clickEvent").value= "4";
		return true;
	}
	return false;
}
function clickApprove(){
	document.getElementById("clickEvent").value= "12";
}
function clickReject(msg){
	document.getElementById("clickEvent").value= "13";
	msg=ReplaceAll(msg,"***","\n");
	var MsgBox = new messageBox();
	if (MsgBox.POPCFM(msg) == MsgBox.YES_CONFIRM) {
	//if(confirm(msg)){
		document.getElementById("obt_appr_save").value="y";
	}else{
		document.getElementById("obt_appr_save").value="n";
	}
}

function clickQCSApproval(){
	document.getElementById("clickEvent").value= "9";
}
function clickQCSReject(msg){
	document.getElementById("clickEvent").value= "10";
	msg=ReplaceAll(msg,"***","\n");
	var MsgBox = new messageBox();
	if (MsgBox.POPCFM(msg) == MsgBox.YES_CONFIRM) {
	//if(confirm(msg)){
		document.getElementById("obt_appr_save").value="y";
	}else{
		document.getElementById("obt_appr_save").value="n";
	}
}
function ReplaceAll(Source,stringToFind,stringToReplace){
	  var temp = Source;
	    var index = temp.indexOf(stringToFind);
	        while(index != -1){
	            temp = temp.replace(stringToFind,stringToReplace);
	            index = temp.indexOf(stringToFind);
	        }
	        return temp;
	}

function computeTotal(tableId,obj){
	var row = obj.parentElement.parentElement; 
	var nrcValue = row.cells[2].childNodes[0].value;
	var mrcValue = row.cells[3].childNodes[0].value;
	var termValue = row.cells[4].childNodes[0].value;
	                         
	var nrc = parseFloat(nrcValue);
	var mrc = parseFloat(mrcValue);
	var term = parseFloat(termValue);
	var total = 0;
	if(isNaN(mrc)){
		mrc = 0;
	}
	if(isNaN(nrc)){
		nrc = 0;
	}
	if(isNaN(term)){
		term = 0;
	}
	total = mrc * term + nrc  ;
	row.cells[5].childNodes[0].value = total;
	//Compute contract value
	computeContractValue(tableId);
}
function computeContractValue(tableId){
	var totalRows = document.getElementById(tableId).rows.length - 2;
	var tblBody = document.getElementById(tableId).tBodies[0];
	var grand = 0;
	for (var i =0 ; i< totalRows;i++){
		var row = tblBody.rows[i + 2];
		var totalValue = row.cells[5].childNodes[0].value;
		var total = parseFloat(totalValue);
		if(isNaN(total)){
			total = 0;
		}
		grand = grand + total;
	}
	document.getElementById("contractValue").value = grand;
}
function onlyNumbers(evt)
{
	var e = event || evt; // for trans-browser compatibility
	var charCode = e.which || e.keyCode;
	if (charCode == 46 || (charCode >= 48 && charCode <= 57))
		return true;

	return false;
}
function onlyInteger(evt){
	var e = event || evt; // for trans-browser compatibility
	var charCode = e.which || e.keyCode;
	if (charCode >= 48 && charCode <= 57){
		return true;
	}
	return false;
}
function addRowToTable(tableId){
	var tblBody = document.getElementById(tableId).tBodies[0];
	var clone = tblBody.rows[1].cloneNode(true);
	clone.style.display ='';
	tblBody.appendChild(clone);
}
function removeRowFromTable(tableId,obj){
	var tbl = document.getElementById(tableId);		
	var row = obj.parentElement.parentElement; 
	tbl.deleteRow(row.rowIndex);	
	//Compute contract value
	computeContractValue(tableId);
}
function CreditTermClick(obj,enabledObjName){
	if(obj.value == "3"){
		document.getElementById(enabledObjName).disabled = false;
	}else{
		document.getElementById(enabledObjName).value = "";
		document.getElementById(enabledObjName).disabled = true;		
	}
}
function determineApprovalStatus(){
	determineStatusPMR(null);
	determineStatusBZR(null);
	determineStatusCTC(null);
	determineStatusPRI(null);
	determineStatusMRG(null);
	determineStatusCRC(null);
	determineStatusFRX(null);
	determineStatusCOV(null);
}
function determineStatusPMR(obj){
	var Q011 = "Q011";
	var Q012 = "Q012";
	var compareValue="";
	if(obj == null) {
		obj = document.forms[0].elements["section_no_pmr"];		
		if(obj == null) {
			return;
		}else{
			for(var i = 0; i < obj.length; i++) {				
				if(obj[i].checked) {
					compareValue = obj[i].value;
					break;
				}
			}

		}
	}else{
		compareValue = obj.value;
	}
	
	if(compareValue == Q011){
		
		document.getElementById(Q011).className="unhidden";
		document.getElementById(Q012).className="hidden";
	}else{
		document.getElementById(Q011).className="hidden";
		document.getElementById(Q012).className="unhidden";
	}
}
function determineStatusBZR(obj){
	var Q021 = "Q021";
	var Q022 = "Q022";
	var compareValue="";
	if(obj == null) {
		obj = document.forms[0].elements["section_no_bzr"];
		if(obj == null) {
			return;
		}else{
			for(var i = 0; i < obj.length; i++) {
				if(obj[i].checked) {
					compareValue = obj[i].value;
					break;
				}
			}

		}
	}else{
		compareValue = obj.value;
	}
	if(compareValue == Q021){
		document.getElementById(Q021).className="unhidden";
		document.getElementById(Q022).className="hidden";
	}else{
		document.getElementById(Q021).className="hidden";
		document.getElementById(Q022).className="unhidden";
	}
}
function determineStatusCTC(obj){
	var Q031 = "Q031";
	var Q032 = "Q032";
	var compareValue="";
	if(obj == null) {
		obj = document.forms[0].elements["section_no_ctc"];
		if(obj == null) {
			return;
		}else{
			for(var i = 0; i < obj.length; i++) {
				if(obj[i].checked) {
					compareValue = obj[i].value;
					break;
				}
			}

		}
	}else{
		compareValue = obj.value;
	}
	if(compareValue == Q031){
		document.getElementById(Q031).className="unhidden";
		document.getElementById(Q032).className="hidden";
	}else{
		document.getElementById(Q031).className="hidden";
		document.getElementById(Q032).className="unhidden";
	}
}
function determineStatusPRI(obj){
	var Q041 = "Q041";
	var Q042 = "Q042";
	var compareValue="";
	if(obj == null) {
		obj = document.forms[0].elements["section_no_pri"];
		if(obj == null) {
			return;
		}else{
			for(var i = 0; i < obj.length; i++) {
				if(obj[i].checked) {
					compareValue = obj[i].value;
					break;
				}
			}

		}
	}else{
		compareValue = obj.value;
	}
	if(compareValue == Q041){
		document.getElementById(Q041).className="unhidden";
		document.getElementById(Q042).className="hidden";
	}else{
		document.getElementById(Q041).className="hidden";
		document.getElementById(Q042).className="unhidden";
	}
}
function determineStatusMRG(obj){
	var Q051 = "Q051";
	var Q052 = "Q052";
	var Q053 = "Q053";
	var compareValue="";
	if(obj == null) {
		obj = document.forms[0].elements["section_no_mrg"];
		if(obj == null) {
			return;
		}else{
			for(var i = 0; i < obj.length; i++) {
				if(obj[i].checked) {
					compareValue = obj[i].value;
					break;
				}
			}

		}
	}else{
		compareValue = obj.value;
	}
	if(compareValue == Q051){
		document.getElementById(Q051).className="unhidden";
		document.getElementById(Q052).className="hidden";
		document.getElementById(Q053).className="hidden";
	}else if(compareValue == Q052){
		document.getElementById(Q051).className="hidden";
		document.getElementById(Q052).className="unhidden";
		document.getElementById(Q053).className="hidden";
	}else{
		document.getElementById(Q051).className="hidden";
		document.getElementById(Q052).className="hidden";
		document.getElementById(Q053).className="unhidden";
	}
}
function determineStatusCRC(obj){
	var Q061 = "Q061";
	var Q062 = "Q062";
	var compareValue="";
	if(obj == null) {
		obj = document.forms[0].elements["section_no_crc"];
		if(obj == null) {
			return;
		}else{
			for(var i = 0; i < obj.length; i++) {
				if(obj[i].checked) {
					compareValue = obj[i].value;
					break;
				}
			}

		}
	}else{
		compareValue = obj.value;
	}
	if(compareValue == Q061){
		document.getElementById(Q061).className="unhidden";
		document.getElementById(Q062).className="hidden";
	}else{
		document.getElementById(Q061).className="hidden";
		document.getElementById(Q062).className="unhidden";
	}
}
function determineStatusFRX(obj){
	var Q071 = "Q071";
	var Q072 = "Q072";
	var Q073 = "Q073";
	var compareValue="";
	if(obj == null) {
		obj = document.forms[0].elements["section_no_frx"];
		if(obj == null) {
			return;
		}else{
			for(var i = 0; i < obj.length; i++) {
				if(obj[i].checked) {
					compareValue = obj[i].value;
					break;
				}
			}

		}
	}else{
		compareValue = obj.value;
	}
	if(compareValue == Q071){
		document.getElementById(Q071).className="unhidden";
		document.getElementById(Q072).className="hidden";
		document.getElementById(Q073).className="hidden";
	}else if(compareValue == Q072){
		document.getElementById(Q071).className="hidden";
		document.getElementById(Q072).className="unhidden";
		document.getElementById(Q073).className="hidden";
	}else{
		document.getElementById(Q071).className="hidden";
		document.getElementById(Q072).className="hidden";
		document.getElementById(Q073).className="unhidden";
	}
}
function determineStatusCOV(obj){
	var Q081 = "Q081";
	var Q082 = "Q082";
	var Q083 = "Q083";
	var compareValue="";
	if(obj == null) {
		obj = document.forms[0].elements["section_no_cov"];
		if(obj == null) {
			return;
		}else{
			for(var i = 0; i < obj.length; i++) {
				if(obj[i].checked) {
					compareValue = obj[i].value;
					break;
				}
			}

		}
	}else{
		compareValue = obj.value;
	}
	if(compareValue == Q081){
		document.getElementById(Q081).className="unhidden";
		document.getElementById(Q082).className="hidden";
		document.getElementById(Q083).className="hidden";
	}else if(compareValue == Q082){
		document.getElementById(Q081).className="hidden";
		document.getElementById(Q082).className="unhidden";
		document.getElementById(Q083).className="hidden";
	}else{
		document.getElementById(Q081).className="hidden";
		document.getElementById(Q082).className="hidden";
		document.getElementById(Q083).className="unhidden";
	}
}
function determineSequenceOfDisplayingGroup(){
	var temp = document.getElementById("seqGroup");
	var seq = 1;
	if(temp!= null){
		for(var i=0; i < temp.options.length - 1; i++){
			var div1 = document.getElementById("div" + temp.options[i].text);
			var div2 = document.getElementById("div" + temp.options[i+1].text);
			if (div1 != null && div2 != null){
				div1.insertBefore(div2);
			}
			if(document.getElementById("lblseq" + temp.options[i].text)!= null){
				document.getElementById("lblseq" + temp.options[i].text).innerHTML = seq;
			}
			if(document.getElementById("lblseq" + temp.options[i+1].text)!= null){
				document.getElementById("lblseq" + temp.options[i+1].text).innerHTML = seq +1;
			}
			seq++;		
		}
		
	}
}