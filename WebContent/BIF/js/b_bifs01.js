function initPage() {
	
}
function clickNew() {
	document.forms[0].forward_search.click();
}
function searchData() {
	var startIndex = document.getElementById("startIndex");
	if(startIndex!=null && startIndex!=undefined) {
		startIndex.value="0";
	}
}

function linkClick(idRef) {
	document.forms[0].idRef.value = idRef;
	document.forms[0].forward_view.click();
}
function linkBillAccountClick(billAccount,context){
	var ACTION_REF_INFO = 'B_BAC/RP_B_BAC_S02_02_01BL.do';
	var url = context +'/'+ ACTION_REF_INFO + '?idBillAccount=' + billAccount;
	var width = window.screen.width*90/100;
   var height = window.screen.height*80/100;
   var left = Number((screen.availWidth/2) - (width/2));
   var top = Number((screen.availHeight/2) - (height/2));
   var offsetFeatures = "width=" + width + ",height=" + height +
                        ",left=" + left + ",top=" + top +
                        "screenX=" + left + ",screenY=" + top;
   var strFeatures= "toolbar=no, status=no, menubar=no,location=no,scrollbars=yes, resizable=yes" + "," + offsetFeatures;      
   var newwindow = window.open(url, null, strFeatures);
   if (window.focus) { newwindow.focus(); }
}