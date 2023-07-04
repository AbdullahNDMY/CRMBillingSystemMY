
var ACTION_COR_VIEW ="/M_CST/M_CSTR02BLogic.do";
var ACTION_CON_VIEW = "/M_CST/M_CSTR07BLogic.do";

function initPage(searchType){
	if(parent.frame_menu.root == "menu"){
		parent.frame_menu.root = "";
		reset();
	}
	// search tabs
	var search =new ddtabcontent("searchTabs");
	search.setpersist(true);
	search.setselectedClassTarget("link"); //"link" or "linkparent"
	search.init();
	if(searchType && searchType == 'advancedSearch')
		search.expandit(1);
	
}
//submit form when click buttons
function submitForm(type,mode,idCust, customerType, context){
	switch(type){
		//in case click search button
		case "basic search":
		    showLoadingTipWindow();
			//reset start index for paging
			if(document.forms[0].startIndex){
				document.forms[0].startIndex.value="0";
			}//reset checked on checkbox
			if(document.forms[0].clickSearchButton)
				document.forms[0].clickSearchButton.value="1";
			//mark searching values 
			document.forms[0].temp_cust_acc_no.value=document.forms[0].cust_acc_no.value;
			document.forms[0].temp_cust_name.value=document.forms[0].cust_name.value;
			document.forms[0].temp_co_regno.value=document.forms[0].co_regno.value;
			document.forms[0].temp_country.value=document.forms[0].country.value;			
			//set event is search
			document.forms[0].event.value="forward_search";
			document.forms[0].searchType.value="basicSearch";
			//submit form
			document.forms[0].submit();
			break;
		case "advanced search":
		     showLoadingTipWindow();
			//reset start index for paging
			if(document.forms[0].startIndex){
				document.forms[0].startIndex.value="0";
			}
			//reset checked on checkbox
			if(document.forms[0].clickSearchButton)
				document.forms[0].clickSearchButton.value="1";
			//set event is search
			document.forms[0].event.value="forward_search";
			document.forms[0].searchType.value="advancedSearch";
			//submit form
			document.forms[0].submit();
			break;
			
		case "basic reset":
			//reset input values
			reset(type);
			//reset start index for paging
			if(document.forms[0].startIndex){
				document.forms[0].startIndex.value="0";
			}
			//reset checked on checkbox
			if(document.forms[0].clickSearchButton) {
				document.forms[0].clickSearchButton.value="1";
			}
			document.forms[0].event.value="forward_reset";
			//submit form
			document.forms[0].submit();
			break;
		case "advanced reset":
			//reset input values
			reset(type);
			//reset start index for paging
			if(document.forms[0].startIndex){
				document.forms[0].startIndex.value="0";
			}
			//reset checked on checkbox
			if(document.forms[0].clickSearchButton) {
				document.forms[0].clickSearchButton.value="1";
			}
			document.forms[0].event.value="forward_reset";
			//submit form
			document.forms[0].submit();
			break;
		case "new corporate customer":
			//set mode
			
			document.getElementById("mode").value = mode;
			//set event is new
			document.forms[0].event.value="forward_new";
			//submit form
			document.forms[0].submit();
			break;
		case "new consumer customer":
			//set mode
			document.getElementById("mode").value = mode;
			//set event is new
			document.forms[0].event.value="forward_newConsumer";
			//set event is new
			document.forms[0].event1.value="forward_newConsumer";
			//submit form
			document.forms[0].submit();
			break;
		case "view":
			//set action is view
			if (customerType == "CORP")
				document.forms[0].action= context + ACTION_COR_VIEW;
			if (customerType == "CONS")
				document.forms[0].action =  context + ACTION_CON_VIEW;
			//set selected id customer
			var previousID = document.getElementById("id_cust").value;
			document.getElementById("id_cust").value = idCust;
			//set mode
			document.getElementById("mode").value = mode;			
			//submit form
			document.forms[0].submit();
			document.getElementById("id_cust").value = previousID;
			break;
		case "export_basic" :
		    showLoadingTipWindow();
			document.forms[0].searchType.value="basicSearch";
			//checkExport(context);
			document.forms[0].event.value="forward_export";
			document.forms[0].submit();
			document.forms[0].event.value="forward_search";
			break;
		case "export_advanced" :
		    showLoadingTipWindow();
			document.forms[0].searchType.value="advancedSearch";
			//checkExport(context);
			document.forms[0].event.value="forward_export";
			document.forms[0].submit();
			document.forms[0].event.value="forward_search";
			break;
		case "advanced view":
			//set selected id customer
			document.getElementById("id_cust").value = idCust;
			//set mode
			document.getElementById("mode").value = mode;
			//set event is view
			document.forms[0].event.value="forward_view";
			//submit form
			document.forms[0].submit();	
			
	}
}
function checkExport(context) {
	// check if existed selected checkbox
	var exitedCheckBox = false;			
	chkCheckExport = document.getElementsByTagName("input");
	for (var i=0; i<chkCheckExport.length; i++)
	{
		if ((chkCheckExport[i].type == "checkbox") && 
				(chkCheckExport[i].name == "chkCheckExport" || chkCheckExport[i].name == "chkCheckExport1")
				&& (chkCheckExport[i].checked)) {
			exitedCheckBox = true;					
		}
	}
	// give out message when no selected checkbox
	if (exitedCheckBox == false){
		new messageBox().POPALT(context);
	}else{
		// do export when have at lease 1 selected checkbox
		document.forms[0].event.value="forward_export";
		document.forms[0].submit();
		document.forms[0].event.value="forward_search";
	}
}
//reset input values
function reset(type){
	switch(type){
		case "basic reset":
			//set input value is blank 
			document.forms[0].cust_acc_no.value = "";
			//set input value is blank
			document.forms[0].co_regno.value = "";
			//set input value is blank
			document.forms[0].cust_name.value = "";
			//set input value is blank
			document.forms[0].country.value = "";			
			//set input value is blank
			document.forms[0].cust_id.value = "";			
			//set input value is blank
			document.forms[0].cust_type.value = "";			
			break;
		case "advanced reset":
			//set input value is blank 
			document.forms[0].customerAccountNo.value = "";
			//set input value is blank 
			document.forms[0].coRegNo.value = "";
			//set input value is blank 
			document.forms[0].customerName.value = "";
			//set input value is blank 
			//document.forms[0].otherReferenceNo.value = "";
			//set input value is blank 
			document.forms[0].id_cust.value = "";
			document.forms[0].address.value = "";
			//set input value is blank 
			document.forms[0].emailAddress.value = "";
			//set input value is blank 
			document.forms[0].zipCode.value = "";
			//set input value is blank 
			document.forms[0].contactNo.value = "";
			//set input value is blank 
			document.forms[0].billingCountry.value = "";
			//set input value is blank 
			document.forms[0].contactPerson.value = "";			
			//set input value is blank 
			document.forms[0].accountManager.value = "";			
	}
}
//submit when click cust id
function submitView(idCust){
	document.getElementById("id_cust").value = idCust;
	//set event is view
	document.forms[0].event.value="forward_view";
	//submit form
	document.forms[0].submit();	
}
function checkAll(){
	var chkCheckExportAll = document.getElementById("chkCheckExportAll").getAttribute("checked");
	// check all chkCheckExport when chkCheckExportAll is checked/uncheck
	var chkCheckExports = document.forms[0].chkCheckExport;
	if(chkCheckExports)
		for ( var int = 0; int < chkCheckExports.length; int++) {
			chkCheckExports[int].checked = chkCheckExportAll;
		}
}
function checkAll1(){
	var chkCheckExportAll1 = document.getElementById("chkCheckExportAll1").getAttribute("checked");
	// check all chkCheckExport when chkCheckExportAll is checked/uncheck
	var chkCheckExports1 = document.forms[0].chkCheckExport1;
	if(chkCheckExports1)
		for ( var int = 0; int < chkCheckExports1.length; int++) {
			chkCheckExports1[int].checked = chkCheckExportAll1;
		}
}