
/*
function initPage(){
	
	onchangePaymentMethod();
}
function clickEdit(){
	document.getElementById("clickEvent").value = "2";
}

function onchangePaymentMethod(){
	var obj = document.getElementById("pmt_method");
	if(obj != null && obj.value == "CQ"){
		document.getElementById("bankCheque").style.display  = "block";
	}else{
		document.getElementById("bankCheque").style.display  = "none";
	}
}
*/

function savepage(billRef)
{
	//document.getElementById("transType").value=transType;
	document.getElementById("checkpagetype").value=billRef;
	//document.getElementById("viewMode").value="view";
	
	var button=	document.getElementById("forward_save"); 

	button.click();
}

