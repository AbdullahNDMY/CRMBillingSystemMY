var newwindow;
var NEWMODE="NEWMODE";
var EDITMODE="EDITMODE";
var READONLY="READONLY";
var FORWARD_NEW="forward_new";
var FORWARD_EDIT="forward_edit";
var FORWARD_VIEW="forward_view";
var FORWARD_SAVE="forward_save";
var FORWARD_DELETE="forward_delete";
var FORWARD_EXIT="forward_exit";
var FORWARD_BANK_INFO="forward_bankInfo";
var ACTION_BANK_INFO="/M_CST/M_CSTR10BLogic.do"; //display customer bank-info
var ACTION_SAVE = "/M_CST/M_CSTR08BLogic.do"; //display saving
//init page
function initPage(){
	var countries=new ddtabcontent("countrytabs");
	countries.setpersist(true);
	countries.setselectedClassTarget("link"); //"link" or "linkparent"
	countries.init();
	//mark mode of screen
	checkMode();	
	setCheckboxesStatus();
}
function initMain(){
	initCNAndSecurityNo();
	//loadBankFullName(null);
	initPopupInfo();
}

function initCNAndSecurityNo(){
	var creditCardNumber = document.getElementById("creditCardNumber").value;
	var securityNo = document.getElementById("securityNo").value;
	if(creditCardNumber!==""&&creditCardNumber!==null){
		var changeCreditCardNumber = changeToMask(creditCardNumber,4);
		document.getElementById("labCreditCardNumber").innerHTML = changeCreditCardNumber;
		document.getElementById("creditCardNumberInput").value = changeCreditCardNumber;
	}
	if(securityNo!==""&&securityNo!==null){
		var changeSecurityNo = changeToMask(securityNo,0);
		document.getElementById("labSecurityNo").innerHTML = changeSecurityNo;
		document.getElementById("securityNoInput").value = changeSecurityNo;
	}
}

function initPopupInfo() {
	var classModeFlg = document.getElementById("classModeFlg").value;
	if("hidden"!=classModeFlg) {
		var acctNumberMsg = document.getElementById("acctNumberMsg").value;
		var creditCardNumberMsg = document.getElementById("creditCardNumberMsg").value;
		if((acctNumberMsg!=""&&acctNumberMsg!=null) || (creditCardNumberMsg!=""&&creditCardNumberMsg!=null)) {
			var msg = new messageBox("");
			if ((acctNumberMsg!=""&&acctNumberMsg!=null) && (creditCardNumberMsg!=""&&creditCardNumberMsg!=null)) {
				var isConfirmAcctNumber = (msg.POPCFMStatement_CST(acctNumberMsg) == msg.YES_CONFIRM);
				if (isConfirmAcctNumber) {
					var isConfirmCreditCardNumber = (msg.POPCFMStatement_CST(creditCardNumberMsg) == msg.YES_CONFIRM);
					if (isConfirmCreditCardNumber) {
						document.getElementById("popupClickYesFlg").value = "1";
						document.getElementById("btnSave").click();
					}
				}
			} else if ((acctNumberMsg!=""&&acctNumberMsg!=null) && (creditCardNumberMsg==""||creditCardNumberMsg==null)) {
				var isConfirmAcctNumber = (msg.POPCFMStatement_CST(acctNumberMsg) == msg.YES_CONFIRM);
				if (isConfirmAcctNumber) {
					document.getElementById("popupClickYesFlg").value = "1";
					document.getElementById("btnSave").click();
				}
			} else if ((acctNumberMsg==""||acctNumberMsg==null) && (creditCardNumberMsg!=""&&creditCardNumberMsg!=null)) {
				var isConfirmCreditCardNumber = (msg.POPCFMStatement_CST(creditCardNumberMsg) == msg.YES_CONFIRM);
				if (isConfirmCreditCardNumber) {
					document.getElementById("popupClickYesFlg").value = "1";
					document.getElementById("btnSave").click();
				}
			}
		}
	}
}

function changeCreditCardNumber(){
	document.getElementById("creditCardNumber").value = document.getElementById("creditCardNumberInput").value;
}

function blurCreditCardNumber(){
	var creditCardNumber = document.getElementById("creditCardNumberInput").value;
	document.getElementById("creditCardNumber").value = creditCardNumber;
	if(creditCardNumber!==""&&creditCardNumber!==null){
		document.getElementById("creditCardNumberInput").value = changeToMask(creditCardNumber,0);
	}
}

function focusCreditCardNumber(){
	document.getElementById("creditCardNumberInput").value = document.getElementById("creditCardNumber").value;
}


function changeSecurityNo(){
	document.getElementById("securityNo").value = document.getElementById("securityNoInput").value;
}

function blurSecurityNo(){
	var securityNo = document.getElementById("securityNoInput").value;
	document.getElementById("securityNo").value = securityNo;
	if(securityNo!==""&&securityNo!==null){
		document.getElementById("securityNoInput").value = changeToMask(securityNo,0);
	}
}

function focusSecurityNo(){
	document.getElementById("securityNoInput").value = document.getElementById("securityNo").value;
}

function changeToMask(str,lastStrLen){
	var reStr = str;
	if(str.length>lastStrLen){
		var strBeforeLastStrLen = str.substring(0,str.length-lastStrLen);
		var maskingStr="";
		for(var i=0;i<strBeforeLastStrLen.length;i++){
			maskingStr = maskingStr + "*";
		}
		reStr = maskingStr+str.substring(str.length-lastStrLen),str.length;
	}
	return reStr;
}

//set loading status of radio 
function setCheckboxesStatus(){
	if(document.getElementById("mode").value == READONLY){
		return;
	}
	var optM = document.getElementById("opt_bill_type_M");
	var optI = document.getElementById("opt_bill_type_I");
	if(optM.checked==true){
		changeOption("M");
	}else{
		changeOption("I");
	}
}
//execute when user submit form
function submitForm(type,message, context, state){
	switch(type){
		case "save":
			//reset mode
//			document.forms[0].action = context + ACTION_SAVE;
			document.getElementById("acctNumberMsg").value = "";
			document.getElementById("creditCardNumberMsg").value = "";
			document.forms[0].submit();
			break;
		case "cancel":
			document.getElementById("mode").value = EDITMODE;
			document.forms[0].event.value = FORWARD_EDIT;
			document.forms[0].submit();
			break;
		case "bankInfo":
			url = context + ACTION_BANK_INFO + '?mode=' + EDITMODE + '&id_cust=' + document.forms[0].id_cust.value;
			document.forms[0].action = url + "&state=" + state;
			document.forms[0].submit();
			break;
	}
}

function popup(url)
{
	newwindow=window.open(url,'name','height=525,width=1050');
	if (window.focus) {newwindow.focus();}
}

function randomize()
{
	var curDate = new Date();
	var curMonth = curDate.getMonth() + 1;
	var curDay = curDate.getDate();
	var curYear = curDate.getFullYear();
	var curHours = curDate.getHours();
	var curMinutes = curDate.getMinutes();
	var curMortem = "";
	
	if (curHours > 11) {
		curMortem = "12345";
	} else {
		curMortem = "67890";
	}
	
	return (curYear + curMonth + curDay + curHours + curMinutes + curMortem);
}

//load mode of page
function checkMode(){
	var beforeEvent = document.getElementById("event");
	if(beforeEvent.value==FORWARD_NEW){
		document.getElementById("mode").value = NEWMODE;
	}
	else if(beforeEvent.value==FORWARD_EDIT){
		document.getElementById("mode").value = EDITMODE;
	}
	else if(beforeEvent.value==FORWARD_VIEW){
		document.getElementById("mode").value = READONLY;
	}	
}
//show confirm message 
function confirmMessage(message){
	var MsgBox = new messageBox();
	if (MsgBox.POPCFM(message) == MsgBox.YES_CONFIRM) {
	//if(confirm(message)){
		return true;
	}		
	return false;
}
//check changes of screen
function noChangesOfScreen(){
	//customer name
	if(!compare(document.getElementById("spn_cust_name").innerText,document.forms[0].cust_name.value)){
		return false;
	}
	//company reg no
	if(!compare(document.getElementById("spn_co_regno").innerText,document.forms[0].co_regno.value)){
		return false;
	}
	//company website
	if(!compare(document.getElementById("spn_co_website").innerText,document.forms[0].co_website.value)){
		return false;
	}
	//company email
	if(!compare(document.getElementById("spn_co_email").innerText,document.forms[0].co_email.value)){
		return false;
	}
	//telephone no
	if(!compare(document.getElementById("spn_co_tel_no").innerText,document.forms[0].co_tel_no.value)){
		return false;
	}
	//fax no
	if(!compare(document.getElementById("spn_co_fax_no").innerText,document.forms[0].co_fax_no.value)){
		return false;
	}
	//inter_comp
	if(!compare(document.getElementById("spn_inter_comp").innerText,convertChecked(document.forms[0].chk_inter_comp.checked))){
		return false;
	}
	//cust_acc_no	
	if(!compare(document.getElementById("spn_cust_acc_no").innerText,document.forms[0].cust_acc_no.value)){
		return false;
	}
	//bill type
	if(!parseOptionValue()){
		return false;
	}
	//isp
	if(!compare(document.getElementById("spn_isp").innerText,convertChecked(document.forms[0].chk_isp.checked))){
		return false;
	}
	//gin
	if(!compare(document.getElementById("spn_gin").innerText,convertChecked(document.forms[0].chk_gin.checked))){
		return false;
	}
	//idc
	if(!compare(document.getElementById("spn_idc").innerText,convertChecked(document.forms[0].chk_idc.checked))){
		return false;
	}
	//sim
	if(!compare(document.getElementById("spn_sim").innerText,convertChecked(document.forms[0].chk_sim.checked))){
		return false;
	}
	//ra_adr_line1
	if(!compare(document.getElementById("spn_ra_adr_line1").innerText,document.forms[0].ra_adr_line1.value)){
		return false;
	}
	//ra_adr_line2
	if(!compare(document.getElementById("spn_ra_adr_line2").innerText,document.forms[0].ra_adr_line2.value)){
		return false;
	}
	//ra_adr_line3
	if(!compare(document.getElementById("spn_ra_adr_line3").innerText,document.forms[0].ra_adr_line3.value)){
		return false;
	}
	//ra_country_id
	if(!compare(document.getElementById("spn_ra_country_id").innerText,document.forms[0].ra_country_id.value)){
		return false;
	}
	//ca_adr_line1
	if(!compare(document.getElementById("spn_ca_adr_line1").innerText,document.forms[0].ca_adr_line1.value)){
		return false;
	}
	//ca_adr_line2
	if(!compare(document.getElementById("spn_ca_adr_line2").innerText,document.forms[0].ca_adr_line2.value)){
		return false;
	}
	//ca_adr_line3
	if(!compare(document.getElementById("spn_ca_adr_line3").innerText,document.forms[0].ca_adr_line3.value)){
		return false;
	}
	//ca_country_id
	if(!compare(document.getElementById("spn_ca_country_id").innerText,document.forms[0].ca_country_id.value)){
		return false;
	}
	//ba_adr_line1
	if(!compare(document.getElementById("spn_ba_adr_line1").innerText,document.forms[0].ba_adr_line1.value)){
		return false;
	}
	//ba_adr_line2
	if(!compare(document.getElementById("spn_ba_adr_line2").innerText,document.forms[0].ba_adr_line2.value)){
		return false;
	}
	//ba_adr_line3
	if(!compare(document.getElementById("spn_ba_adr_line3").innerText,document.forms[0].ba_adr_line3.value)){
		return false;
	}
	//ba_country_id
	if(!compare(document.getElementById("spn_ba_country_id").innerText,document.forms[0].ba_country_id.value)){
		return false;
	}
	//pc_contact_name
	if(!compare(document.getElementById("spn_pc_contact_name").innerText,document.forms[0].pc_contact_name.value)){
		return false;
	}
	//pc_designation
	if(!compare(document.getElementById("spn_pc_designation").innerText,document.forms[0].pc_designation.value)){
		return false;
	}
	//pc_email
	if(!compare(document.getElementById("spn_pc_email").innerText,document.forms[0].pc_email.value)){
		return false;
	}
	//pc_tel_no
	if(!compare(document.getElementById("spn_pc_tel_no").innerText,document.forms[0].pc_tel_no.value)){
		return false;
	}
	//pc_did_no
	if(!compare(document.getElementById("spn_pc_did_no").innerText,document.forms[0].pc_did_no.value)){
		return false;
	}
	//pc_fax_no
	if(!compare(document.getElementById("spn_pc_fax_no").innerText,document.forms[0].pc_fax_no.value)){
		return false;
	}
	//pc_mobile_no
	if(!compare(document.getElementById("spn_pc_mobile_no").innerText,document.forms[0].pc_mobile_no.value)){
		return false;
	}
	//bc_contact_name
	if(!compare(document.getElementById("spn_bc_contact_name").innerText,document.forms[0].bc_contact_name.value)){
		return false;
	}
	//bc_designation
	if(!compare(document.getElementById("spn_bc_designation").innerText,document.forms[0].bc_designation.value)){
		return false;
	}
	//bc_email
	if(!compare(document.getElementById("spn_bc_email").innerText,document.forms[0].bc_email.value)){
		return false;
	}
	//bc_tel_no
	if(!compare(document.getElementById("spn_bc_tel_no").innerText,document.forms[0].bc_tel_no.value)){
		return false;
	}
	//bc_did_no
	if(!compare(document.getElementById("spn_bc_did_no").innerText,document.forms[0].bc_did_no.value)){
		return false;
	}
	//bc_fax_no
	if(!compare(document.getElementById("spn_bc_fax_no").innerText,document.forms[0].bc_fax_no.value)){
		return false;
	}
	//bc_mobile_no
	if(!compare(document.getElementById("spn_bc_mobile_no").innerText,document.forms[0].bc_mobile_no.value)){
		return false;
	}	
	//tc_contact_name
	if(!compare(document.getElementById("spn_tc_contact_name").innerText,document.forms[0].tc_contact_name.value)){
		return false;
	}
	//tc_designation
	if(!compare(document.getElementById("spn_tc_designation").innerText,document.forms[0].tc_designation.value)){
		return false;
	}
	//tc_email
	if(!compare(document.getElementById("spn_tc_email").innerText,document.forms[0].tc_email.value)){
		return false;
	}
	//tc_tel_no
	if(!compare(document.getElementById("spn_tc_tel_no").innerText,document.forms[0].tc_tel_no.value)){
		return false;
	}
	//tc_did_no
	if(!compare(document.getElementById("spn_tc_did_no").innerText,document.forms[0].tc_did_no.value)){
		return false;
	}
	//tc_fax_no
	if(!compare(document.getElementById("spn_tc_fax_no").innerText,document.forms[0].tc_fax_no.value)){
		return false;
	}
	//tc_mobile_no
	if(!compare(document.getElementById("spn_tc_mobile_no").innerText,document.forms[0].tc_mobile_no.value)){
		return false;
	}	
	//oc_contact_name
	if(!compare(document.getElementById("spn_oc_contact_name").innerText,document.forms[0].oc_contact_name.value)){
		return false;
	}
	//oc_designation
	if(!compare(document.getElementById("spn_oc_designation").innerText,document.forms[0].oc_designation.value)){
		return false;
	}
	//oc_email
	if(!compare(document.getElementById("spn_oc_email").innerText,document.forms[0].oc_email.value)){
		return false;
	}
	//oc_tel_no
	if(!compare(document.getElementById("spn_oc_tel_no").innerText,document.forms[0].oc_tel_no.value)){
		return false;
	}
	//oc_did_no
	if(!compare(document.getElementById("spn_oc_did_no").innerText,document.forms[0].oc_did_no.value)){
		return false;
	}
	//oc_fax_no
	if(!compare(document.getElementById("spn_oc_fax_no").innerText,document.forms[0].oc_fax_no.value)){
		return false;
	}
	//oc_mobile_no
	if(!compare(document.getElementById("spn_oc_mobile_no").innerText,document.forms[0].oc_mobile_no.value)){
		return false;
	}		
	return true;
}
//compare 2 values 
function compare(value1,value2){
	//in case value1 = value2 -> return true
	if(value1==value2){
		return true;
	}
	return false;
} 
//compare values between loading values and inputed values
function parseOptionValue(){
	var optM = document.getElementById("opt_bill_type_M").checked;
	var optI = document.getElementById("opt_bill_type_I").checked;
	var loadingOpt = document.getElementById("spn_bill_type").innerText;
	if(loadingOpt=="M" && String(optM)=="true"){
		return true;
	}
	if(loadingOpt=="I" && String(optI)=="true"){
		return true;
	}
	return false;
}
//parse string when checkbox is checked
function convertChecked(checked){
	if(checked == true){
		return "on";
	}else{
		return "off";
	}
}
//enable/disable checkboxes when select/unselect radio
function changeOption(type){
	if(type=="M"){
		//enable checkboxes
		document.forms[0].chk_isp.disabled = false;
		document.forms[0].chk_gin.disabled = false;
		document.forms[0].chk_idc.disabled = false; 
		document.forms[0].chk_sim.disabled = false; 
		return;
	}
	//uncheck checkboxes
	document.forms[0].chk_isp.checked = false;
	document.forms[0].chk_gin.checked = false;
	document.forms[0].chk_idc.checked = false; 
	document.forms[0].chk_sim.checked = false; 		
	//disable checkboxes
	document.forms[0].chk_isp.disabled = true;
	document.forms[0].chk_gin.disabled = true;
	document.forms[0].chk_idc.disabled = true; 
	document.forms[0].chk_sim.disabled = true; 	
}
function loadBankFullName(obj){
	if(obj != null && obj.value == "")
	{
		document.forms[0].cbBankCode.disabled = false;
		document.forms[0].cbBranchCode.disabled = false;
	}
	var bankSelect = document.forms[0].bank.selectedIndex;
	if ((document.forms[0].hasBankInfo.value == 'true') || (document.getElementById("mode").value == 'EDITMODE')){
		// set bankCode
		document.forms[0].cbBankCode.value = document.forms[0].bankCode[bankSelect].value;
		//set branchCode
		document.forms[0].cbBranchCode.value = document.forms[0].branchCode[bankSelect].value;
		if(document.forms[0].cbBankCode.value != "" && document.forms[0].cbBranchCode.value !="" && document.forms[0].bank.value !=""){
			document.forms[0].cbBankCode.disabled = true;
			document.forms[0].cbBranchCode.disabled = true;
		}
	}
}

function loadBankBranchCode() {
	var bankCode = document.forms[0].cbBankCode.value;
	var branchCode = document.forms[0].cbBranchCode.value;
	var code = (""+bankCode+ branchCode).replace(/ /g,"");
	if(bankCode!="" && branchCode!=""){
		var sendvales = $("input[name='"+code+"']");
		document.forms[0].bank.value = sendvales.eq(0).val()==undefined?"":sendvales.eq(0).val();
		document.getElementById("bank_full_name").innerHTML=sendvales.eq(1).val()==undefined?"":sendvales.eq(1).val();
		document.getElementById("bank_bic_code").innerHTML=sendvales.eq(2).val()==undefined?"":sendvales.eq(2).val();
	}else{
		document.forms[0].bank.value="";
		document.getElementById("bank_full_name").innerHTML="";
		document.getElementById("bank_bic_code").innerHTML="";
	}
}

function ClickExit(){	
	var MsgBox = new messageBox();
	if (MsgBox.POPEXT($('#hiddenMsgExitConfirmation').val()) == MsgBox.YES_CONFIRM) {
		window.close();
	}
}
