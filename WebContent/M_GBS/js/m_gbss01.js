//init page
function initPage(){
	var settings=new ddtabcontent("settingTabs");
	settings.setpersist(true);
	settings.setselectedClassTarget("link"); //"link" or "linkparent"
	settings.init();
}

//Check sepcial words 
function onSaveClick(){
	//Check Debtor Account
	var txbAdvanceAcc=$("#txbAdvanceAcc").val();
	//Check Advance Debtor Account  
	var txbDebterAcc=$("#txbDebterAcc").val();
	//Match Parten
	var regexp=/[&]/;
	var message = $("#ERR4SC107").val();
	if(regexp.test(txbDebterAcc)){
		//show  message
		message=message.replace('{0}', "Debtor Account").replace('{1}', "Debtor Account");
		var MsgBox = new messageBox();
		MsgBox.POPALT(message);
		return false;
	} 
	if(regexp.test(txbAdvanceAcc)){
		//show  message
		message=message.replace('{0}', "Advance Debtor Account").replace('{1}', "Advance Debtor Account");
		var MsgBox = new messageBox();
		MsgBox.POPALT(message);
		return false;
	} 
	$('form').submit();
	
}
