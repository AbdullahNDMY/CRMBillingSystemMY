function clickBillReferenceLink(billRef)
{
	//document.getElementById("transType").value=transType;
	document.getElementById("hypBankReference").value=billRef;
	//document.getElementById("viewMode").value="view";
	
	var button=	document.getElementById("forward_view"); 

	button.click();
}
function exitS02()
{
	var button=	document.getElementById("forward_view");	
	button.click();
}
function confirmSave() {
	var message;
	message  = "Warning\n";
	message += "Are you sure you want to delete?\n";
	message += "Click Yes to cancel, or No to abort the deletion.";
	var MsgBox = new messageBox();
	if (MsgBox.POPCFM(message) == MsgBox.YES_CONFIRM) {
	//var agree=confirm(message);
	//if (agree) {
		//return true ;
			var button=	document.getElementById("forward_delete"); 
			button.click();
		}
	}
function setbankfullname()
{	
	var bankfullname = "";
	var	bankname = document.getElementById("tbxBankName").value;
	var	branchname = document.getElementById("tbxBranchName").value;
	if( ( bankname != "" ) && ( branchname != "" ))		
		bankfullname = bankname + "-" + branchname;
	else 
		bankfullname = bankname + branchname;
	document.getElementById("lblBankFullName").innerHTML=bankfullname;
	document.getElementById("lblBankFullNameHidden").value=bankfullname;
}

function removeBlankSpaces()
{
	var didnoPC = document.getElementById("tbxDIDNoPC").value;
	var didnoPCopt = didnoPC.split(" ");
	var didnoPCstr = didnoPCopt.join("");
	document.getElementById("tbxDIDNoPC").value = didnoPCstr;
	
	var mobilenoPC = document.getElementById("tbxMobileNoPc").value;
	var mobilenoPCopt = mobilenoPC.split(" ");
	var mobilenoPCstr = mobilenoPCopt.join("");
	document.getElementById("tbxMobileNoPc").value = mobilenoPCstr;
	
	var contactnameBC = document.getElementById("tbxContactNameBC").value;
	var contactnameBCopt = contactnameBC.split(" ");
	var contactnameBCstr = contactnameBCopt.join("");
	document.getElementById("tbxContactNameBC").value = contactnameBCstr;
	
	var designationBC = document.getElementById("tbxDesignationBC").value;
	var designationBCopt = designationBC.split(" ");
	var designationBCstr = designationBCopt.join("");
	document.getElementById("tbxDesignationBC").value = designationBCstr;
	
	var emailBC = document.getElementById("tbxEmailBC").value;
	var emailBCopt = emailBC.split(" ");
	var emailBCstr = emailBCopt.join("");
	document.getElementById("tbxEmailBC").value = emailBCstr;
	
	var telephonenoBC = document.getElementById("tbxTelephoneNoBC").value;
	var telephonenoBCopt = telephonenoBC.split(" ");
	var telephonenoBCstr = telephonenoBCopt.join("");
	document.getElementById("tbxTelephoneNoBC").value = telephonenoBCstr;
	
	var faxnoBC = document.getElementById("tbxFaxNoBC").value;
	var faxnoBCopt = faxnoBC.split(" ");
	var faxnoBCstr = faxnoBCopt.join("");
	document.getElementById("tbxFaxNoBC").value = faxnoBCstr;
	
	var didnoBC = document.getElementById("tbxDIDNoBC").value;
	var didnoBCopt = didnoBC.split(" ");
	var didnoBCstr = didnoBCopt.join("");
	document.getElementById("tbxDIDNoBC").value = didnoBCstr;
	
	var mobilenoBC = document.getElementById("tbxMobileNoBC").value;
	var mobilenoBCopt = mobilenoBC.split(" ");
	var mobilenoBCstr = mobilenoBCopt.join("");
	document.getElementById("tbxMobileNoBC").value = mobilenoBCstr;
	
	var contactnameOC = document.getElementById("tbxContactNameOC").value;
	var contactnameOCopt = contactnameOC.split(" ");
	var contactnameOCstr = contactnameOCopt.join("");
	document.getElementById("tbxContactNameOC").value = contactnameOCstr;
	
	var designationOC = document.getElementById("tbxDesignationOC").value;
	var designationOCopt = designationOC.split(" ");
	var designationOCstr = designationOCopt.join("");
	document.getElementById("tbxDesignationOC").value = designationOCstr;
	
	var emailOC = document.getElementById("tbxEmailOC").value;
	var emailOCopt = emailOC.split(" ");
	var emailOCstr = emailOCopt.join("");
	document.getElementById("tbxEmailOC").value = emailOCstr;
	
	var telephonenoOC = document.getElementById("tbxTelephoneNoOC").value;
	var telephonenoOCopt = telephonenoOC.split(" ");
	var telephonenoOCstr = telephonenoOCopt.join("");
	document.getElementById("tbxTelephoneNoOC").value = telephonenoOCstr;
	
	var faxnoOC = document.getElementById("tbxFaxNoOC").value;
	var faxnoOCopt = faxnoOC.split(" ");
	var faxnoOCstr = faxnoOCopt.join("");
	document.getElementById("tbxFaxNoOC").value = faxnoOCstr;
	
	var didnoOC = document.getElementById("tbxDIDNoOC").value;
	var didnoOCopt = didnoOC.split(" ");
	var didnoOCstr = didnoOCopt.join("");
	document.getElementById("tbxDIDNoOC").value = didnoOCstr;
	
	var mobilenoOC = document.getElementById("tbxMobileNoOC").value;
	var mobilenoOCopt = mobilenoOC.split(" ");
	var mobilenoOCstr = mobilenoOCopt.join("");
	document.getElementById("tbxMobileNoOC").value = mobilenoOCstr;
}