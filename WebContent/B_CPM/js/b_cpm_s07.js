function checkMailAccount(obj){
	var OPT_ADDT_OPTION = document.getElementsByName('OPT_ADDT_OPTION');
	if(obj.checked){
	 	var OPT_BASE_QTY = document.getElementById('OPT_BASE_QTY');
		OPT_BASE_QTY.disabled = false;
		for(var i = 0;i<OPT_ADDT_OPTION.length;i++){
			OPT_ADDT_OPTION[i].disabled = false;
		}
	}else{
		var OPT_BASE_QTY = document.getElementById('OPT_BASE_QTY');
		OPT_BASE_QTY.disabled = true;
		for(var i = 0;i<OPT_ADDT_OPTION.length;i++){
			OPT_ADDT_OPTION[i].disabled = true;
			//OPT_ADDT_OPTION[i].checked = false;
		}
	}
}
function checkOptions(obj,strName){
	var options = document.getElementsByName(strName);
	if(obj.checked){
		for(var i = 0;i<options.length;i++){
			options[i].disabled = false;
		}
	}else{
		for(var i = 0;i<options.length;i++){
			options[i].disabled = true;
			//options[i].checked = false;
		}
	}
}
function preparebody(){
	checkMailAccount($('AUTO_MAIL_ACC'));
	checkOptions($('AUTO_MAILBOX_QTY'),'OPT_MAILBOX_QTY');
	checkOptions($('AUTO_VIRUS_SCAN'),'OPT_VIRUS_SCAN');
	checkOptions($('AUTO_ANTI_SPAM'),'OPT_ANTI_SPAM');
	checkOptions($('AUTO_JUNK_MGT'),'OPT_JUNK_MGT');
}
function changewidth(){
		var popupWidth = window.screen.width*80/100;
		popupWidth = popupWidth - 90;
	    document.documentElement.childNodes[1].innerHTML="<div style='width:"+popupWidth+"px'>"+document.documentElement.childNodes[1].innerHTML+"</div>";
//		window.moveTo((window.screen.width-popupWidth)/2, 0);
}
function $(Str){
	return document.getElementById(Str);
}
function exit(url){
	window.opener.reloadPage(url);
	window.close();
}
function closeEvt(url) {
	var loadPerentPgeFlg = document.getElementById("loadPerentPgeFlg").value;
	if (loadPerentPgeFlg!="1") {
		window.opener.reloadPage(url);
	}
}
function changeLoadPerentPgeFlg() {
	document.getElementById("loadPerentPgeFlg").value = "1";
}
function saveCheck() {
	var OPT_BASE_QTY = trim(document.getElementById("OPT_BASE_QTY").value);
	var reg="^-?\\d+$";
	var errorInfo = "";
	document.getElementById("divError").innerText = errorInfo;
	if (OPT_BASE_QTY!="") {
		var re =new RegExp(reg); 
		if(OPT_BASE_QTY.match(re)==null){
			errorInfo = errorInfo + document.getElementById("ERR1SC088").value;
		} else if(parseInt(OPT_BASE_QTY,10)<0) {
			errorInfo = errorInfo + document.getElementById("ERR1SC098").value;
		} else if(parseInt(OPT_BASE_QTY,10)==0) {
			var msg = new messageBox("");
			var isConfirm = (msg.POPCFM(document.getElementById("ERR4SC015").value) == msg.YES_CONFIRM);
			if (isConfirm) {
				changeLoadPerentPgeFlg();
				document.getElementById("OPT_BASE_QTY").disabled = false;
				return true;
			} else {
				return false;
			}
		}
	} else {
		errorInfo = errorInfo + document.getElementById("ERR1SC099").value;
	}
	document.getElementById("divError").innerText = errorInfo;
	if (errorInfo!="") {
		return false;
	} else {
		changeLoadPerentPgeFlg();
		document.getElementById("OPT_BASE_QTY").disabled = false;
		return true;
	}
}
function trim(str) {
   if (str == null) {
    return "";
   }
   return str.replace(/^\s*(.*?)[\s\n]*$/g,'$1');  
} 