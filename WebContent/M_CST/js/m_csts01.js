
function initPage(){
	
	if(parent.frame_menu.root == "menu"){
		parent.frame_menu.root = "";
		reset();
	}
	// search tabs
	var search =new ddtabcontent("searchTabs");
	search.setpersist(true);
	search.setselectedClassTarget("link"); //"link" or "linkparent"
	search.init();
	
}
//submit form when click buttons
function submitForm(type,mode,idCust){
	switch(type){
		//in case click search button
		case "search":
			//reset start index for paging
			document.forms[0].startIndex.value="0";
			//mark searching values 
			document.forms[0].temp_cust_acc_no.value=document.forms[0].cust_acc_no.value;
			document.forms[0].temp_cust_name.value=document.forms[0].cust_name.value;
			document.forms[0].temp_co_regno.value=document.forms[0].co_regno.value;
			document.forms[0].temp_country.value=document.forms[0].country.value;			
			//set event is search
			document.forms[0].event.value="forward_search";
			//submit form
			document.forms[0].submit();
			break;
		case "reset":
			//reset input values
			reset();
			break;
		case "new":
			//set mode
			document.getElementById("mode").value = mode;
			//set event is new
			document.forms[0].event.value="forward_new";
			//submit form
			document.forms[0].submit();
			break;
		case "view":
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
//reset input values
function reset(){
	//set input value is blank 
	document.forms[0].cust_acc_no.value = "";
	//set input value is blank
	document.forms[0].co_regno.value = "";
	//set input value is blank
	document.forms[0].cust_name.value = "";
	//set input value is blank
	document.forms[0].country.value = "";
}
//submit when click cust id
function submitView(idCust){
	document.getElementById("id_cust").value = idCust;
	//set event is view
	document.forms[0].event.value="forward_view";
	//submit form
	document.forms[0].submit();	
}