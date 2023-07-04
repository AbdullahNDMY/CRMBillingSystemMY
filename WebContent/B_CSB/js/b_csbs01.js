function initPage(){
	if(parent.frame_menu.root == "menu"){
		parent.frame_menu.root = "";
		clickReset();
	}
}
function clickSearch(){
	document.getElementById("startIndex").value = 0;
}
function clickReset(){
	document.getElementById("cust_name").value = "";
	document.getElementById("start_date").value = "";
	document.getElementById("end_date").value = "";
	document.getElementById("month").value = "";
	document.getElementById("year").value = "";
	document.getElementById("ref_no").value = "";
	if(document.getElementById("pmt_method") != null){
		document.getElementById("pmt_method").selectedIndex = 0;
	}
}



function clickBillReferenceLink(receipt_Ref)
{
	//document.getElementById("transType").value=transType;
	document.getElementById("clickEvent").value=receipt_Ref;
	document.getElementById("idRef").value = receipt_Ref;
	//document.getElementById("viewMode").value="view";
		var button=	document.getElementById("forward_view"); 
	button.click();
}


function clickNew(){
	document.getElementById("clickEvent").value = "1";
}
function onlyNumbers(evt)
{
	var e = event || evt;
	var charCode = e.which || e.keyCode;
	if (charCode >= 48 && charCode <= 57)
		return true;
	return false;
}
