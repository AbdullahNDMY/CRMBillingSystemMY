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
		document.forms[0].priority[0].checked = true;
		document.forms[0].priority[0].value = "1";
	}else{
		document.forms[0].priority[0].disabled = true;
		document.forms[0].priority[1].disabled = true;
		obj.value = "0";
	}	
}
function clickDelete(msg){
	var MsgBox = new messageBox();
	if (MsgBox.POPCFM(msg) == MsgBox.YES_CONFIRM) {
	//if (confirm(msg)){
		return true;
	}
	return false;	
}
function clickReply(){
	document.getElementById("click_event").value = "2";
}
function clickEdit(){
	document.getElementById("click_event").value = "5";
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
	/*
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
	/*
	 * 
	 */
	window.location = nextScreen;
}
function clickDownload(fileId){
	document.getElementById('file_id').value = fileId;
	document.getElementById('forward_download').click();
}
function clickAddAttachment(){
	//addNewInputFile();
	document.getElementById("my_file_element").click();
}
function addNewInputFile(){
	//Get current file index
	var idxObj = document.createElement("fileIndex");
	//Set new file index
	var idx = parseInt(idxObj,10) + 1;
	//Create new element
	var newTag = document.createElement("input");
	newTag.setAttribute("type","file");
	newTag.setAttribute("name","listFile[" + idx + "]");
	//Add new element
	var mytext=document.createTextNode('what');

	document.createElement('fileDivHidden').appendChild(mytext);

}
function addElement() {
	  var ni = document.getElementById('fileDivHidden');
	 
	  var newdiv = document.createElement('div');
	  var divIdName = 'myDiv';
	  newdiv.setAttribute('id',divIdName);
	  newdiv.innerHTML = 'Element Number has been added! <a href=\'#\' onclick=\'removeElement('+divIdName+')\'>Remove the div "'+divIdName+'"</a>';
	  ni.appendChild(newdiv);
	}