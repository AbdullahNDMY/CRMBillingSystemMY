
var FIELDPOS_SERVICE_CHECKBOX = 0;

var count = 0;
var requestTimer = null;
var autoStopLoadingTimer = null;
var httpRequest = null;

/**
 * Initialize B_SSM_S03 page
 */
function initB_SSM_S03_Page() {
	alertReportErrorStatus();
	var addressTypeControl = document.getElementById('addressType');
	changeAddressValue(addressTypeControl);
}

function changeAddressValue(addressTypeControl) {
	addressType = addressTypeControl.value;
	var addressInfo = addressListValues[addressType];
	if(addressInfo!=null && addressInfo!=undefined) {
		document.getElementById('divAddress1').innerText = addressInfo['ADR_LINE1'];
		document.getElementById('divAddress2').innerText = addressInfo['ADR_LINE2'];
		document.getElementById('divAddress3').innerText = addressInfo['ADR_LINE3'];
		document.getElementById('divAddress4').innerText = addressInfo['ADR_LINE4'];
	} else {
		document.getElementById('divAddress1').innerText = "";
		document.getElementById('divAddress2').innerText = "";
		document.getElementById('divAddress3').innerText = "";
		document.getElementById('divAddress4').innerText = "";
	}
}

/**
 * Set value to textfield with specific id
 * @param txtID
 * @param text
 */
function setTextField(txtID,  text) { 	
	document.getElementById(txtID).value = text;
};

/**
 * Check all services
 */
function checkAllServices(){
	var isCheckAll = document.getElementById('checkAllServicesID').checked;
	var table = document.getElementById('servicesGroupTableID');
	var rowCount = table.rows.length;
	for (var i = 0; i < rowCount; i++) {
		var row = table.rows[i];
		if (row == null) {
			continue;
		}
		var cell = row.cells[FIELDPOS_SERVICE_CHECKBOX];
		if (cell != null) { 
			var childNodes = cell.childNodes;
			if (childNodes != null) {
				for (var j = 0; j < childNodes.length; j++) {
					var child = childNodes[j];
					
					if (child != null && 
						child.attributes != null &&
						child.attributes["type"] != null &&
						child.attributes["type"].value != null &&
						child.attributes["type"].value == "checkbox") {
						child.checked = !isCheckAll;							
						child.click();
					}
				}
			}
		}			
	}
}

/**
 * Alert report error status
 */
function alertReportErrorStatus() {
	if (reportErrorStatus) {		
		var MsgBox = new messageBox();
		MsgBox.POPALT(reportErrorStatus);
	}
}

/**
 * Is service selected?
 */
function isServiceSelected() {
	var table = document.getElementById('servicesGroupTableID');
	var rowCount = table.rows.length;
	for (var i = 0; i < rowCount; i++) {
		var row = table.rows[i];
		if (row == null) {
			continue;
		}
		var cell = row.cells[FIELDPOS_SERVICE_CHECKBOX];
		if (cell != null) { 
			var childNodes = cell.childNodes;
			if (childNodes != null) {
				for (var j = 0; j < childNodes.length; j++) {
					var child = childNodes[j];
					
					if (child != null && 
						child.attributes != null &&
						child.attributes["type"] != null &&
						child.attributes["type"].value != null &&
						child.attributes["type"].value == "checkbox" &&
						child.checked) {
							return true;
					}
				}
			}
		}			
	}
	var MsgBox = new messageBox();
	promptErrorMessage = promptErrorMessage.replace(/null*/g, "Service");		
	MsgBox.POPALT(promptErrorMessage);
	
	return false;
}

function completionDateCheck(btnType) {
	var table = document.getElementById('servicesGroupTableID');
	var rowCount = table.rows.length;
	for (var i = 0; i < rowCount; i++) {
		var row = table.rows[i];
		var checkServices = $(row).find("#checkServicesID"+i);
		if(checkServices.attr("checked")==true) {
			var completionDate = $(row).find("#completionDate").val();
			if(completionDate==null || completionDate=="") {
				if(btnType=="completionBtn") {
					$("#errorInfo").text(completionBtnMessage);
				} else if(btnType=="freeFormatBtn") {
					$("#errorInfo").text(freeFormatBtnMessage);
				}
				return false;
			}
		}
	}
	return true;
}
 
/**
 * Create request object
 */
function createRequestObject() {
	var req;
	if(window.XMLHttpRequest) {
		//For Firefox, Safari, Opera
		req = new XMLHttpRequest();
	}
	else if(window.ActiveXObject){
		//For IE 5+
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	return req;
}	 	

/**
 * Send request
 */
function sendRequest(method, url) {
	if (httpRequest == null) {
		httpRequest = createRequestObject();
	}
	if(method == 'get' || method == 'GET') {
		httpRequest.open(method, url);
		httpRequest.onreadystatechange = handleResponse;
		httpRequest.send(null);
	}
}

/**
 * Handle response
 */
function handleResponse(){
	if(httpRequest.readyState == 4 && httpRequest.status == 200) {
		var response = httpRequest.responseText;
		if(response) {
			if (response == "1") {
				onFinishDownloading();
			}
		}
	}
}
 
var isAllowSendRequest = false;

/**
 * Timer processing
 */
function processingTimer() {	
	clearTimeout(requestTimer);
	if (isAllowSendRequest) {
		sendRequest('get', contextPath + '/B_SSM_S03/B_SSM_S03_Request_Process_Action.do?processMode=9');	
	} else {
		autoStopLoadingTimer = setTimeout('onFinishDownloading()', 5000);
	}
	isAllowSendRequest = true;
	requestTimer = setTimeout('processingTimer()', 2000);	
	setTimeout('refeshLoadingImage()', 200);	
}

/**
 * Refesh loading image
 */
function refeshLoadingImage() {
	var loadingImg = document.getElementById("loadingImg");
	if (loadingImg) {
		loadingImg.src = "../image/loading.gif";
	}
}

 /**
  * Validate submit
  */
function validateSubmit() {
	var isValid = isServiceSelected();
	if(isValid && $("#processModeField").val()=="6"){
	   document.forms[0].action=contextPath + '/B_SSM_S03/B_SSM_S03_01SCR.do';
	   return true; 
	}
	if (isValid) {
		onStartDownloading();
	}
	return isValid;
}

 /**
  * Activate buttons
  */
function activateButtons(isInVisible) {
	var noticeBtn = document.getElementById("noticeBtnID"); 
	noticeBtn.disabled = isInVisible;
	var completionReportBtn = document.getElementById("completionReportBtnID"); 
	completionReportBtn.disabled = isInVisible;
	var freeFormatBtn = document.getElementById("freeFormatBtnID"); 
	freeFormatBtn.disabled = isInVisible;
}

/**
 * Action on finishing download
 */
function onFinishDownloading() {
	clearTimeout(requestTimer);
	clearTimeout(autoStopLoadingTimer);
	//activateButtons(false);
	closeLoadingWindow();
	isAllowSendRequest = false;
}

/**
 * Action on start download
 * @return
 */
function onStartDownloading() {	
	// Disable buttons
	//activateButtons(true);
	// Processing
	processingTimer();
	// Show loading
	showLoadingWindow();
}
 
/**
 * IE compatibility
 */
function ieCompatibility() {
	return (!window.opera && document.compatMode && document.compatMode!="BackCompat")? 
		   document.documentElement : 
		   document.body;
}

/**
 * Show loading window
 */
function showLoadingWindow() {
	var loadingWindow = document.getElementById("loadingWindow");
	loadingWindow.style.display = "block";
	loadingWindow.style.visibility = "visible";
	 
	loadingWindow.style.width = ieCompatibility().clientWidth + "px";
	loadingWindow.style.height = ieCompatibility().clientHeight + "px";
	
	var boat = $('<table id="loadingtable" style="position:absolute;width:480px;height:96px;color:blue;z-index: 101;">'+
		    '<tr><td align="center"><img id="loadingImg" src="../image/loading.gif" style="visibility: visible;" alt="Loading..."/></td></tr>'+
		    '<tr><td align="center" style="font-weight: bold;width:100%">Your request is currently being processed.</td></tr>'+
		    '<tr><td align="center" style="font-weight: bold;width:100%">Please do not click on the menu/tool bar or click the back button.</td></tr></table>');

    boat.css("left",(ieCompatibility().clientWidth)/2 - 240 + "px");
    boat.css("top",(ieCompatibility().clientHeight)/2 - 48 + "px");
    $('body').append(boat);
	
	loadingWindow.style.left = ieCompatibility().scrollLeft + "px";
	loadingWindow.style.top = ieCompatibility().scrollTop + "px";
}

/**
 * Close loading window
 */
function closeLoadingWindow() {
	
	var loadingWindow = document.getElementById("loadingWindow");
	
	loadingWindow.style.display="none";
	
	$("#loadingtable").remove();
	
}

/**
 * Notice Button click
 */
function noticeBtnClick(){
    return checkAddress();
}

/**
 * Completion Report Button click
 */
function completionReportBtnClick(){
	var checkAccMgrPrimResult = checkAccMgrPrim();
	if(checkAccMgrPrimResult) {
		var checkAddressResult = checkAddress();
		if(checkAddressResult){
			var completionDateCheckResult = completionDateCheck('completionBtn');
			return completionDateCheckResult;
		} else {
			return checkAddressResult;
		}
	} else {
		return checkAccMgrPrimResult;
	}
}

/**
 * Free Format Button click
 */
function freeFormatBtnClick(){
	var checkAccMgrPrimResult = checkAccMgrPrim();
	if(checkAccMgrPrimResult) {
		var checkAddressResult = checkAddress();
		if(checkAddressResult){
			var completionDateCheckResult = completionDateCheck('freeFormatBtn');
			return completionDateCheckResult;
		} else {
			return checkAddressResult;
		}
	} else {
		return checkAccMgrPrimResult;
	}
}

function checkAddress(){
	var address1 = document.getElementById("divAddress1").innerText;
	var address2 = document.getElementById("divAddress2").innerText;
	var address3 = document.getElementById("divAddress3").innerText;
	if ((address1==""||address1==null)&&(address2==""||address2==null)&&(address3==""||address3==null)) {
		$("#errorInfo").text(ERR1SC093);
		return false;
	} else {
		$("#errorInfo").text("");
		return true;
	}
}

function checkAccMgrPrim() {
	var customerType = document.getElementById("customerType").value;
	if ("CORP"==customerType) {
		var accMgrPrim = document.getElementById("accMgrPrim").value;
		if (accMgrPrim=="" || accMgrPrim==null) {
			$("#errorInfo").text(ERR1SC095);
			return false;
		} else {
			$("#errorInfo").text("");
			return true;
		}
	} else {
		return true;
	}
}



