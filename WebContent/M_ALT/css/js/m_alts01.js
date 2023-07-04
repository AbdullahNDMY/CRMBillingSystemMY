function clickReminder(){
	var obj =document.getElementById('reminder_chk');
	if(obj!= null){
		var father = document.getElementById("colReminder");
		var children = father.getElementsByTagName("input");	
		var item = children[6];
		if(item != null){
			if(obj.checked == true) {
				item.disabled = false;
				obj.value = "1";
			}else{
				item.disabled = true;
				document.getElementById("reminder_date").value ="";
				obj.value = "0";
			}	
		}
	}
}
function clickImportance(obj){
	if(obj.checked == true) {
		document.forms[0].priority[0].disabled = false;
		document.forms[0].priority[1].disabled = false;
		obj.value = "1";
	}else{
		document.forms[0].priority[0].disabled = true;
		document.forms[0].priority[1].disabled = true;
		obj.value = "0";
	}	
}
function clickReply(){
	document.getElementById("click_event").value = "2";
}
function clickReplyToAll(){
	document.getElementById("click_event").value = "3";
}
function clickForward(){
	document.getElementById("click_event").value = "4";
}
function clickExitViewMode(nextScreen){
	window.location = nextScreen;
}
function clickExit(msgConfirm,modeType,nextScreen){
	//New mode
	if(modeType == "1"){
		if(document.getElementById("msg_type").value != document.getElementById("msg_type_hidden").value				
				|| document.getElementById("to_msg").value != ""
				|| document.getElementById("cc_msg").value != ""
				|| document.getElementById("subject").value != ""
				|| document.getElementById("start_date").value != ""
				|| document.getElementById("end_date").value != ""
				|| document.getElementById("reminder_date").value != ""
				|| document.getElementById("importance_chk").checked == true
				|| document.getElementById("reminder_chk").checked == true
				|| document.getElementById("msg").value != ""
				|| document.getElementById("attachment").value != ""
		){
			if(confirm(msgConfirm)){
				window.location = nextScreen;
			}
		}else{
			window.location = nextScreen;
		}
	}else{	
		if(document.getElementById("msg_type").value != document.getElementById("msg_type_hidden").value				
				|| document.getElementById("to_msg").value != document.getElementById("to_msg_hidden").value
				|| document.getElementById("cc_msg").value != document.getElementById("cc_msg_hidden").value
				|| document.getElementById("subject").value != document.getElementById("subject_hidden").value
				|| document.getElementById("start_date").value != document.getElementById("start_date_hidden").value
				|| document.getElementById("end_date").value != document.getElementById("end_date_hidden").value
				|| document.getElementById("reminder_date").value != document.getElementById("reminder_date_hidden").value
				|| document.getElementById("msg").value != document.getElementById("msg_hidden").value
				|| document.getElementById("reminder_chk").value != document.getElementById("reminder_chk_hidden").value
				|| document.getElementById("importance_chk").value != document.getElementById("importance_chk_hidden").value
				|| (document.forms[0].priority[0].checked && document.forms[0].priority[0].value != document.getElementById("priority_hidden").value)
				|| (document.forms[0].priority[1].checked && document.forms[0].priority[1].value != document.getElementById("priority_hidden").value)
		){
			if(confirm(msgConfirm)){
				window.location = nextScreen;
			}
		}else{
			window.location = nextScreen;
		}		
	}	
}
function clickAddAttachment(){
	addElement();
}

function addElement() {
	//Get current file index
	var objIdx = document.getElementById("fileIndex");
	var idx = parseInt(objIdx.value,10);
	//Create new element
	var inputTag=document.createElement("input");		
	inputTag.type="file";
	var tagName = "listFile[" + idx + "]";
	inputTag.name = tagName;
	
	//inputTag.setAttribute("id",tagName);
	inputTag.className="FileSizeTextBox";
	//Add new element to Div tag	
	document.getElementById('fileDivHidden').appendChild(inputTag);
	//Open file input	
	inputTag.click();
	//Check whether use choose specified file or not
	if(inputTag.value != ""){
		document.getElementById("fileIndex").value = idx + 1;
	}else{
		document.getElementById('fileDivHidden').removeChild(inputTag);
	}
	inputTag.disabled = true;
}