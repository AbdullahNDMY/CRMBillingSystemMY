function initPage(){
	if(parent.frame_menu.root == "menu"){
		parent.frame_menu.root = "";
		clickReset();
	}
}
function clickReset(){
	document.getElementById("cust_name").value = "";
	document.getElementById("user_name").value = "";
	document.getElementById("id_ref").value = "";
	document.getElementById("id_quo").value = "";
	document.getElementById("start_date").value = "";
	document.getElementById("end_date").value = "";
}
function clickSearch(){
	document.getElementById("startIndex").value = 0;
}
function clickNew(){
	document.getElementById("clickEvent").value = "1";//New QCS
}
function clickIdRefLink(id_ref){
	document.getElementById("id_ref_s01").value = id_ref;
	var button=	document.getElementById("forward_link");
	button.click();
}