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
var ACTION_BANK_INFO="/M_CST/M_CSTR10BLogic.do";
var ACTION_EXIT="/M_CST/M_CSTS02DSP.do";
var originalMode;
//init page
function initPage(){
	var countries = new ddtabcontent("countrytabs");
	countries.setpersist(true);
	countries.setselectedClassTarget("link"); //"link" or "linkparent"
	countries.init();
	//addresstab init
	var address = new ddtabcontent("addresstabs");
	address.setpersist(true);
	address.setselectedClassTarget("link"); //"link" or "linkparent"
	address.init();
	//mark mode of screen
	checkMode();	
	setCheckboxesStatus();
	checkChkInter();
	var selectVal = $("#companyBankInfo").val();
	$("#companyBankInfoList").val(parseInt(selectVal));
	
}

function setZipCode(){
	if(document.getElementById("mode").value == NEWMODE || document.getElementById("mode").value == "")
	{
		if (document.forms[0].ra_zip_code.value == ""){
			document.forms[0].ra_zip_code.value = "Zip Code";				
		}
		if (document.forms[0].ba_zip_code.value == ""){
			document.forms[0].ba_zip_code.value = "Zip Code";				
		}
		if (document.forms[0].ca_zip_code.value == ""){
			document.forms[0].ca_zip_code.value = "Zip Code";				
		}
		if (document.forms[0].ta_zip_code.value == ""){
			document.forms[0].ta_zip_code.value = "Zip Code";				
		}
	}
}
//set loading status of radio 
function setCheckboxesStatus(){
	if(document.getElementById("mode").value == READONLY){
		return;
	} 
//	var optM = document.getElementById("opt_bill_type_M");
//	var optI = document.getElementById("opt_bill_type_I");
//	if(optM.checked==true){
//		changeOption("M");
//	}else{
//		changeOption("I");
//	}
}

// display duplicated PeopleSoft Acc. No popup screen
function custInitPeopleSoftPopupInfo() {
    var popupInfo = document.forms[0].peopleSoftPopupInfo.value;
    document.forms[0].peopleSoftPopupInfo.value = "";
    document.forms[0].popupClickYesFlg.value = "";
    var mode = document.forms[0].mode.value;
    
    if(mode != "READONLY" && popupInfo != "" && popupInfo != null) {
        var msg = new messageBox("");
        var isConfirm = (msg.POPCFMPeopleSoft_CST(popupInfo) == msg.YES_CONFIRM);
        if (isConfirm) {
            document.forms[0].event.value=FORWARD_SAVE; 
            document.forms[0].popupClickYesFlg.value = "1";
            document.forms[0].submit();
        }
    }
}

//execute when user submit form
function submitForm(type,message, context){
	var mode = document.forms[0].mode.value;
	var MsgBox = new messageBox();
	switch(type){
		case "save":
			//reset mode
			//checkMode();
			if(document.getElementById("category_enableFlg").value != 1){	
				document.getElementById("company_categoryList").disabled = false;
			}
			if(document.getElementById("subCategory_enableFlg").value != 1){
				document.getElementById("company_sub_categoryList").disabled = false;
			}
			if(document.getElementById("bankInfo_enableFlg").value != 1){
				document.getElementById("companyBankInfoList").disabled = false;
			}
			if(document.getElementById("company_enableFlg").value != 1){
				document.getElementById("company_typeList").disabled = false;
			}
			if(!checkAccountManager('primary',message)){
				if (MsgBox.POPCFM($("#message_group").find(".messagePrimary").text().replace(/_/g,"&")) != MsgBox.YES_CONFIRM) {
					break;
				}
			}
			if(!checkAccountManager('secondary',message)){
				if (MsgBox.POPCFM($("#message_group").find(".messageSecondary").text().replace(/_/g,"&")) != MsgBox.YES_CONFIRM) {
					break;
				}
			}
			document.forms[0].event.value=FORWARD_SAVE;	
			// remove default value of zip code
			if (document.forms[0].ra_zip_code.value == "Zip Code"){
				document.forms[0].ra_zip_code.value = "";				
			}
			if (document.forms[0].ba_zip_code.value == "Zip Code"){
				document.forms[0].ba_zip_code.value = "";				
			}
			if (document.forms[0].ca_zip_code.value == "Zip Code"){
				document.forms[0].ca_zip_code.value = "";				
			}
			if (document.forms[0].ta_zip_code.value == "Zip Code"){
				document.forms[0].ta_zip_code.value = "";				
			}
			document.forms[0].submit();
			if(document.getElementById("category_enableFlg").value != 1){	
				document.getElementById("company_categoryList").disabled = true;
			}
			if(document.getElementById("subCategory_enableFlg").value != 1){
				document.getElementById("company_sub_categoryList").disabled = true;
			}
			if(document.getElementById("bankInfo_enableFlg").value != 1){
				document.getElementById("companyBankInfoList").disabled = true;
			}
			if(document.getElementById("company_enableFlg").value != 1){
				document.getElementById("company_typeList").disabled = false;
			}
			break;
		case "edit":
			document.getElementById("mode").value = EDITMODE;
			document.forms[0].event.value=FORWARD_EDIT;
			document.forms[0].submit();
			break;
		case "delete":
			//confirm		
			if(confirmMessage(message))
			{
				document.forms[0].event.value=FORWARD_DELETE;
				document.forms[0].submit();
			}
			break;
		case "bankInfo":
			url = context + ACTION_BANK_INFO + '?mode=' + originalMode + '&id_cust=' + document.forms[0].id_cust.value;
			popup(url);
			break;
		case "exit":
			if($('#hiddenPrevious').val() == "B_CPM") {
				window.location = $('#hiddenContextpath').val() + '/B_CPM/action1SCR.do';
				return;
			}
			if(typeof(window.opener)!= "undefined") {
				window.close();
			}
			
			
			//reset mode
			//when mode is new or edit
			if(mode==NEWMODE || mode==EDITMODE || mode ==""){
				//show confirm message
				//if(confirmMessage(message)){
				if (MsgBox.POPEXT() == MsgBox.YES_CONFIRM) {
					if(typeof(window.opener)!= "undefined") {
						window.close();
					}
					document.forms[0].event.value=FORWARD_EXIT;
					document.forms[0].action = context + ACTION_EXIT
					document.forms[0].submit();
				}					
			}else{
				//when mode is readonly
				document.forms[0].event.value=FORWARD_EXIT;
				document.forms[0].action = context + ACTION_EXIT
				document.forms[0].submit();
			}
			break;			
	}
}
//load mode of page
function checkMode(){
	var beforeEvent = document.getElementById("event");
	originalMode = document.getElementById("mode").value;
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
	//pc_email_cc
	if(!compare(document.getElementById("spn_pc_email_cc").innerText,document.forms[0].pc_email_cc.value)){
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
	//bc_email_cc
	if(!compare(document.getElementById("spn_bc_email_cc").innerText,document.forms[0].bc_email_cc.value)){
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
	//billing contact 2
	//bc2_contact_name
	if(!compare(document.getElementById("spn_bc2_contact_name").innerText,document.forms[0].bc2_contact_name.value)){
		return false;
	}
	//bc2_designation
	if(!compare(document.getElementById("spn_bc2_designation").innerText,document.forms[0].bc2_designation.value)){
		return false;
	}
	//bc2_email
	if(!compare(document.getElementById("spn_bc2_email_to").innerText,document.forms[0].bc2_email_to.value)){
		return false;
	}
	//bc2_email_cc
	if(!compare(document.getElementById("spn_bc2_email_cc").innerText,document.forms[0].bc2_email_cc.value)){
		return false;
	}
	//bc2_tel_no
	if(!compare(document.getElementById("spn_bc2_tel_no").innerText,document.forms[0].bc2_tel_no.value)){
		return false;
	}
	//bc2_did_no
	if(!compare(document.getElementById("spn_bc2_did_no").innerText,document.forms[0].bc2_did_no.value)){
		return false;
	}
	//bc2_fax_no
	if(!compare(document.getElementById("spn_bc2_fax_no").innerText,document.forms[0].bc2_fax_no.value)){
		return false;
	}
	//bc2_mobile_no
	if(!compare(document.getElementById("spn_bc2_mobile_no").innerText,document.forms[0].bc2_mobile_no.value)){
		return false;
	}
	//billing contact 3
	//bc3_contact_name
	if(!compare(document.getElementById("spn_bc3_contact_name").innerText,document.forms[0].bc3_contact_name.value)){
		return false;
	}
	//bc3_designation
	if(!compare(document.getElementById("spn_bc3_designation").innerText,document.forms[0].bc3_designation.value)){
		return false;
	}
	//bc3_email
	if(!compare(document.getElementById("spn_bc3_email_to").innerText,document.forms[0].bc3_email_to.value)){
		return false;
	}
	//bc3_email_cc
	if(!compare(document.getElementById("spn_bc3_email_cc").innerText,document.forms[0].bc3_email_cc.value)){
		return false;
	}
	//bc3_tel_no
	if(!compare(document.getElementById("spn_bc3_tel_no").innerText,document.forms[0].bc3_tel_no.value)){
		return false;
	}
	//bc3_did_no
	if(!compare(document.getElementById("spn_bc3_did_no").innerText,document.forms[0].bc3_did_no.value)){
		return false;
	}
	//bc3_fax_no
	if(!compare(document.getElementById("spn_bc3_fax_no").innerText,document.forms[0].bc3_fax_no.value)){
		return false;
	}
	//bc3_mobile_no
	if(!compare(document.getElementById("spn_bc3_mobile_no").innerText,document.forms[0].bc3_mobile_no.value)){
		return false;
	}
	//billing contact 4
	//bc4_contact_name
	if(!compare(document.getElementById("spn_bc4_contact_name").innerText,document.forms[0].bc4_contact_name.value)){
		return false;
	}
	//bc4_designation
	if(!compare(document.getElementById("spn_bc4_designation").innerText,document.forms[0].bc4_designation.value)){
		return false;
	}
	//bc4_email
	if(!compare(document.getElementById("spn_bc4_email_to").innerText,document.forms[0].bc4_email_to.value)){
		return false;
	}
	//bc4_email_cc
	if(!compare(document.getElementById("spn_bc4_email_cc").innerText,document.forms[0].bc4_email_cc.value)){
		return false;
	}
	//bc4_tel_no
	if(!compare(document.getElementById("spn_bc4_tel_no").innerText,document.forms[0].bc4_tel_no.value)){
		return false;
	}
	//bc4_did_no
	if(!compare(document.getElementById("spn_bc4_did_no").innerText,document.forms[0].bc4_did_no.value)){
		return false;
	}
	//bc4_fax_no
	if(!compare(document.getElementById("spn_bc4_fax_no").innerText,document.forms[0].bc4_fax_no.value)){
		return false;
	}
	//bc4_mobile_no
	if(!compare(document.getElementById("spn_bc4_mobile_no").innerText,document.forms[0].bc4_mobile_no.value)){
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
	//tc_email_cc
	if(!compare(document.getElementById("spn_tc_email_cc").innerText,document.forms[0].tc_email_cc.value)){
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
	//oc_email_cc
	if(!compare(document.getElementById("spn_oc_email_cc").innerText,document.forms[0].oc_email_cc.value)){
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
		return "";
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

function copy2CorTech(){
	if (document.forms[0].copy_to_cor_tech.checked == true){
		// copy Bill Address to Registered Address
		document.forms[0].ra_adr_line1.value = document.forms[0].ba_adr_line1.value;
		document.forms[0].ra_adr_line2.value = document.forms[0].ba_adr_line2.value;
		document.forms[0].ra_adr_line3.value = document.forms[0].ba_adr_line3.value;
		document.forms[0].ra_zip_code.value = document.forms[0].ba_zip_code.value;		
		document.forms[0].ra_country_id.selectedIndex = document.forms[0].ba_country_id.selectedIndex;
		// copy Bill Address to Technical Address
//		document.forms[0].ta_adr_line1.value = document.forms[0].ba_adr_line1.value;
//		document.forms[0].ta_adr_line2.value = document.forms[0].ba_adr_line2.value;
//		document.forms[0].ta_adr_line3.value = document.forms[0].ba_adr_line3.value;
//		document.forms[0].ta_zip_code.value = document.forms[0].ba_zip_code.value;
//		document.forms[0].ta_country_id.selectedIndex = document.forms[0].ba_country_id.selectedIndex;
	}
}

function popup(url)
{
	newwindow=window.open(url,'name','height=525,width=1050'); //400,900,1
	if (window.focus) {newwindow.focus()}
}
function checkChkInter(){
	// for r19
	// Affiliate NTT Enable if chkInter-Company is checked; Disable if chkInter-Company is unchecked
	affiliate_select = document.getElementById("affiliate_ntt_select");
	affiliate_hidden= document.getElementById("affiliate_ntt_hidden");
	if (document.forms[0].inter_comp.checked == true){
		affiliate_select.disabled = false;
		affiliate_hidden.disabled = true;
	}
	if (document.forms[0].inter_comp.checked == false){
		affiliate_select.disabled = true;
		affiliate_hidden.disabled = false;
	}
}
function displayEmptyCells(){
	var originalMode = document.getElementById("mode");
	ra_line2 = document.getElementById("ra_adr_line_2");
	ra_line3 = document.getElementById("ra_adr_line_3");
	ba_line2 = document.getElementById("ba_adr_line_2");
	ba_line3 = document.getElementById("ba_adr_line_3");
	
	if(originalMode.value==READONLY){
		if(document.getElementById("spn_ra_adr_line2_v").innerText == "" || document.getElementById("spn_ra_adr_line2_v").innerText == null){
			ra_line2.style.display="none";
		}
		if(document.getElementById("spn_ra_adr_line3_v").innerText == "" || document.getElementById("spn_ra_adr_line3_v").innerText == null){
			ra_line3.style.display="none";
		}
		if(document.getElementById("spn_ba_adr_line2_v").innerText == "" || document.getElementById("spn_ba_adr_line2_v").innerText == null){
			ba_line2.style.display="none";
		}
		if(document.getElementById("spn_ba_adr_line3_v").innerText == "" || document.getElementById("spn_ba_adr_line3_v").innerText == null){
			ba_line3.style.display="none";
		}
	}else{
    	ra_line2.style.display="";
    	ra_line3.style.display="";
    	ba_line2.style.display="";
    	ba_line3.style.display="";
	}
}

function doEditExit(message, context){
    var MsgBox1 = new messageBox();
    if (MsgBox1.POPEXT(message) == MsgBox1.YES_CONFIRM) {
        if(document.forms[0].mode){
            document.forms[0].mode.value='READONLY';
        }
        var previousAction = document.forms[0].action;
        document.forms[0].action = context + '/M_CST/M_CSTR02BLogic.do?event='+FORWARD_EXIT;
        document.forms[0].submit();
        document.forms[0].action = previousAction;
    }
}

function checkAccountManager(accountManagerID,listUser){
	var flag = false;
	var accountManagerValue = document.getElementById(accountManagerID).value;
	if(accountManagerValue.trim() == ""){
		flag = true;
	} else if(accountManagerValue != "" && typeof(listUser) != "undefined") {
		var ams = listUser.toLowerCase().split(";"); 
		var amv = accountManagerValue.toLowerCase().trim();
		for ( var i = 0; i < ams.length; i++) {
			if(amv == ams[i].trim()){
				flag = true;
				break;
			}
		}
	}
	return flag;
}

function checkTextAreaMaxLen(obj){
	if(obj.value.length>300){
		obj.value = obj.value.substr(0,300);
		
	}
}