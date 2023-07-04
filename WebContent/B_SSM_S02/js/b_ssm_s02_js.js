
// Component Code Constants
var CODE_TEXT = 0;
var CODE_TEXTBOX = 1;
var CODE_CHECKBOX = 2;		
var CODE_BUTTON = 3;

// Mail Accounts table field constants	
var FIELDPOS_MAILACCOUNTDELETED_N0 = 0;
var FIELDPOS_MAILACCOUNTDELETED_REMOVELINK = 4;	
var FIELDPOS_MAILACCOUNT_N0 = 0;
var FIELDPOS_MAILACCOUNT = 1;
var FIELDPOS_MAILPASSWORD = 2;
var FIELDPOS_POPACCOUNT = 3;
var FIELDPOS_MAILACCOUNT_MAILBOX = 5;
var FIELDPOS_MAILACCOUNT_ADDBOX = 4;
var FIELDPOS_MAILACCOUNT_VIRUSSCAN = 6;
var FIELDPOS_MAILACCOUNT_ANTISPAM = 7;	
var FIELDPOS_MAILACCOUNT_JUNKMNGT = 8;	
var FIELDPOS_MAILACCOUNT_REMOVELINK = 11;
var FIELDPOS_MAILACCOUNT_CHECKBOX = 0;
var FIELDPOS_FIREWALL_NO = 1;

// Rack Equipment table field constants	
var FIELDPOS_RACKEQUIPMENT_REMOVELINK = 0;

// Component index
var virusScanComponentID = 0;	
var antiSpamComponentID = 0;	
var junkManagementComponentID = 0;	

var cPEConfigDisplayingContentDivName = null;

var FORWARD_CHANGE_ACCESS_ACCOUNT = "forward_changeAccessAccount";
var FORWARD_CHANGE_PASSWORD = "forward_changeAccessPassword";

/**
 * Init B_SSM_S02 page
 */
function initB_SSM_S02_Page(tabID, tabPosition) {	
	initB_SSM_S02_Page_TabSet(tabID, tabPosition);
};

function changeAccessAccountClick(){
	var subscriptionID = document.getElementById("subscriptionID").value;
	var rootPath = document.getElementById("rootPath").value;
	var url = rootPath + "/COMMON/POPUP_COMMON_DSP.do";
	var queryString = "?r="+Math.random()+
	"&event=" + FORWARD_CHANGE_ACCESS_ACCOUNT + 
	"&idSubInfo=" + subscriptionID;
	url = url + queryString;
	var msg = new messageBox("");
	var result = msg.POPACC(url);
	if(result == msg.YES_CONFIRM) {
		var customerPlanID = document.getElementById("customerPlanID").value;
		var subscriptionID = document.getElementById("subscriptionID").value;
		var customerID = document.getElementById("customerID").value;
		var fromPopup = "noPopup";
		//refresh new status
		window.location = rootPath + "/B_SSM_S02/B_SSM_S02_SubInfo_Displaying_Action.do?"+
									"customerPlanID="+customerPlanID+
									"&subscriptionID="+subscriptionID+
									"&customerID="+customerID+
									"&fromPopup="+fromPopup;
	}
//	var POPUP_FEATURE_STD = "height=600,width=800,resizable=1,scrollbars=1";
//	window.open(url,'name',POPUP_FEATURE_STD);
}

function changePassword(){
	var subscriptionID = document.getElementById("subscriptionID").value;
	var rootPath = document.getElementById("rootPath").value;
	var url = rootPath + "/COMMON/POPUP_COMMON_DSP.do";
	var queryString = "?r="+Math.random()+
	"&event=" + FORWARD_CHANGE_PASSWORD + 
	"&idSubInfo=" + subscriptionID;
	url = url + queryString;
	var msg = new messageBox("");
	var result = msg.POPAPW(url);
	if(result == msg.YES_CONFIRM) {
		var customerPlanID = document.getElementById("customerPlanID").value;
		var subscriptionID = document.getElementById("subscriptionID").value;
		var customerID = document.getElementById("customerID").value;
		var fromPopup = "noPopup";
		//refresh new status
		window.location = rootPath + "/B_SSM_S02/B_SSM_S02_SubInfo_Displaying_Action.do?"+
									"customerPlanID="+customerPlanID+
									"&subscriptionID="+subscriptionID+
									"&customerID="+customerID+
									"&fromPopup="+fromPopup;
	}
//	var POPUP_FEATURE_STD = "height=600,width=800,resizable=1,scrollbars=1";
//	window.open(url,'name',POPUP_FEATURE_STD);
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
 * Init tab set of B_SSM_S02 page
 * @param tabID
 * @param tabPosition
 */
function initB_SSM_S02_Page_TabSet(tabID, tabPosition) {
	// Init tab content
	var tabs =new ddtabcontent(tabID);	
	tabs.setpersist(true);
	tabs.setselectedClassTarget("link"); 
	tabs.init();	
	tabs.expandit(tabPosition);
	extraInit();
};

function extraInit()
{
	initMaskInput("teamworkAccountNameMaskID");
	initMaskInput("teamworkAccountPasswordMaskID");
}

function initMaskInput(inputID)
{	
	var ctr = $("#"+inputID);
	if (ctr.val() == "")
	{
		ctr.css("color", "silver");
		ctr.val(ctr.attr("alt"));
	}
}

/**
 * Indicate ascii code of char
 * @param aChar
 */
function calKeyCode(charKey) {
	var character = charKey.substring(0,1);
	var code = character.charCodeAt(0);
	return code;
}

/**
 * Check number
 * @param elementID
 */
function checkNumber(elementID) {
	var element = document.getElementById(elementID);
	var strValue = element.value;
	var strLength = strValue.length;
	var inputChar = element.value.charAt((strLength) - 1);
	var charCode = calKeyCode(inputChar);
	var previousStr = element.value.substring(0, (strLength - 1));	
	  
	// Char not number
	if (charCode < 48 || charCode > 57) {
			
	element.value = previousStr; 
	return false;
	}

	return true;
}
 
/**
 * Check number
 * @param element
 */
function checkNumberFromElement(element) {
	var strValue = element.value;
	var strLength = strValue.length;
	var inputChar = element.value.charAt((strLength) - 1);
	var charCode = calKeyCode(inputChar);
	var previousStr = element.value.substring(0, (strLength - 1));	
	  
	// Char not number
	if (charCode < 48 || charCode > 57) {
		element.value = previousStr; 
		return false;
	}

	return true;
} 

/**
 * Get row count of specific table
 * @param tableID
 * @return int
 */
function getTableRowCount(tableID) {	
	var table = document.getElementById(tableID);
	if (table) {
		return table.rows.length;
	}
	return 0;
}

/**
 * Add components to specific table
 */
function addComponentsToTable(tableID,
						      insertRowPos,						    
						      componentCellPosArray,
						      componentValueArray) {
	var table = document.getElementById(tableID);	
	var colCount = getColumnCount(table.rows[0]);
    // Create new row
    var newRow = table.insertRow(insertRowPos);     
    // Insert component to row
    for (var i = 0; i < colCount; i++) {
		var isComponentCellPos = false;
		var cellPos = 0;
		var cellIndex = 0;
    	for (var j = 0; j < componentCellPosArray.length; j++) {
			cellPos = componentCellPosArray[j];
			if (cellPos == i) {				
				isComponentCellPos = true;
				cellIndex = j;
				break;
			}
		}
    	// Add component
    	var newCell = null;
    	if (isComponentCellPos == true) {
    		newCell = newRow.insertCell(i);  
    		newCell.className = "B_SSM_S02_Page_Form_Table_Col_Detail";
    		newCell.align = "left";
            newCell.innerHTML = componentValueArray[cellIndex];
    	} else {
    		newCell = newRow.insertCell(i);   
    		newCell.className = "B_SSM_S02_Page_Form_Table_Col_Detail";
    		newCell.align = "left";
    		newCell.innerHTML = '';
    	}
	} 
}

/**
 * Add components to specific table with specific align mode
 */
function addComponentsToTableWithAlignMode(tableID,
									        insertRowPos,						    
									        componentCellPosArray,
									        componentValueArray,
									        vAlignMode) {
	var table = document.getElementById(tableID);	
	var colCount = getColumnCount(table.rows[0]);
    // Create new row
    var newRow = table.insertRow(insertRowPos);     
    // Insert component to row
    for (var i = 0; i < colCount; i++) {
		var isComponentCellPos = false;
		var cellPos = 0;
		var cellIndex = 0;
    	for (var j = 0; j < componentCellPosArray.length; j++) {
			cellPos = componentCellPosArray[j];
			if (cellPos == i) {				
				isComponentCellPos = true;
				cellIndex = j;
				break;
			}
		}
    	// Add component
    	var newCell = null;
    	if (isComponentCellPos == true) {
    		newCell = newRow.insertCell(i);  
    		newCell.className = "B_SSM_S02_Page_Form_Table_Col_Detail";
    		newCell.align = "left";    		
    		newCell.vAlign = vAlignMode;
            newCell.innerHTML = componentValueArray[cellIndex];            
    	} else {
    		newCell = newRow.insertCell(i);   
    		newCell.className = "B_SSM_S02_Page_Form_Table_Col_Detail";
    		newCell.align = "left";    		
    		newCell.vAlign = vAlignMode;
    		newCell.innerHTML = '';
    	}
	} 
}

/**
 * Get count of column from specific row
 * @param row
 * @return int
 */
function getColumnCount(row) {
	if (row) {
		var colCount = 0;
		for (var i = 0; i < row.cells.length; i++) {
			var cell = row.cells[i];
			colCount += cell.colSpan;
		}		
		return colCount;
	}
	return 0;
}

/**
 * Remove row of table
 */
function removeTableRow(tableID, rowNo) {
	var table = document.getElementById(tableID);  	
	var rowCount = table.rows.length;
	table.deleteRow(rowNo >= rowCount? 
					rowCount - 1 :
					rowNo);
}

/**
 * Remove row of table
 */
function removeTableRow(tableID, rowNo, defaultRowNo) {
	var table = document.getElementById(tableID);  	
	var rowCount = table.rows.length;
	var isDone = false;
	try {
		table.deleteRow(rowNo);
		isDone = true;
	} catch(ex) {
		isDone = false;		
	}
	// If not done, process with defaultRowNo
	if (isDone == false) {
		table.deleteRow(defaultRowNo);
	}
}
/**
 *default check function 
 */
function doDefaultSetting(defaultcSet){
    if(defaultcSet.checked){
        $.ajax({
            type: "POST",
            url: "B_SSM_S02_GetDefaultSetting_Action.do",
            success:function(data){
                
                dataObj = eval("("+data+")");
                
                $("#sMTPServerNameID").val(dataObj.SMTP_SERVER_NAME);
                
                $("#domainName").val(dataObj.DOMAIN_NAME);
                
                $("#webmailURLId").val(dataObj.WEBMAIL_URL);
                
                $("#pOPServerNameID").val(dataObj.POP_SERVER_NAME);
                
            },
            error: function(XMLHttpRequest, textStatus, errorThrown){
    
            }
        });
    }else{
        $("#domainName").val($("#domainNameHiddenVal").val());
        $("#sMTPServerNameID").val($("#sMTPServerNameHiddenVal").val());
        $("#webmailURLId").val($("#webmailURLHiddenVal").val());
        $("#pOPServerNameID").val($("#pOPServerNameHiddenVal").val());
    }
}

/**
 * Display or hide panels
 * @param isVisible
 * @param panelIDs
 */
function activateHiddenPanels(isVisible, panelID) {		
	if (isVisible) {		
		if (panelID != null) {				
			panel = document.getElementById(panelID);
			if (panel) {
				panel.style.display = 'block'; 	
			}
		}
	} else {		
		if (panelID != null) {			
			panel = document.getElementById(panelID);
			if (panel) {
				panel.style.display = 'none'; 
			}
		}
	}
}
 
/**
 * Popup panel
 * @param path
 */
function popUp(path){	
	var width = 500;
    var height = 300;
    var left = Number((screen.availWidth/2) - (width/2));
    var top = Number((screen.availHeight/2) - (height/2));
    var offsetFeatures = "width=" + width + ",height=" + height +
    					 ",left=" + left + ",top=" + top +
    					 "screenX=" + left + ",screenY=" + top;
	var strFeatures= "toolbar=no, status=no, menubar=no, location=no," +
				     " scrollbars=yes, resizable=yes," + offsetFeatures;	 	
	newPanel = window.open(path, null, strFeatures); 	
}

/**
 * Popup panel
 * @param path
 * @param strFeatures
 */
function popUpWithSpecificFeatures(path, strFeatures, screenWidth, screenHeight){	
	var width = screenWidth;
    var height = screenHeight;
    var left = Number((screen.availWidth/2) - (width/2));
    var top = Number((screen.availHeight/2) - (height/2));
    var offsetFeatures = "width=" + width + ",height=" + height +
    					 ",left=" + left + ",top=" + top +
    					 "screenX=" + left + ",screenY=" + top;
	var strFeatures= strFeatures + "," + offsetFeatures;	 	
	newPanel = window.open(path, null, strFeatures);	
}

/**
 * Add server detail
 */
function addServerDetail()
{
	var serverListBox = $('#serverInfoServerID');
	var servers = serverListBox.find('option');
	var key = 1;
	if (servers.length > 0)
	{
		var ss = servers.eq(servers.length-1).text().split(' ');
		if (ss.length>1)
		{
			var k = Number(ss[1]);
			if (!isNaN(k))
				key = k+1;
		}
	}
	var alias = 'Server '+key;
	serverListBox.append('<option value = "'+alias+'">'+alias+'</option>');
	//set selectedIndex
	document.getElementById("serverInfoServerID").selectedIndex = document.getElementById("serverInfoServerID").options.length - 1;
	// call onchange() event after append
	selectServerInfo();
	
	$('#serverInfoDetailIDDiv').append('<input type="hidden" name="serverInfoDetailID" value="">');
	$('#serverInfoAliasDiv').append('<input type="hidden" name="serverInfoAlias" value="'+alias+'">');
	$('#serverInfoNameDiv').append('<input type="hidden" name="serverInfoName" value="">');
	$('#serverInfoHardwareDiv').append('<input type="hidden" name="serverInfoHardware" value="">');
	$('#serverInfoOSDiv').append('<input type="hidden" name="serverInfoOS" value="">');
	$('#serverInfoIPDiv').append('<input type="hidden" name="serverInfoIP" value="">');
	$('#serverInfoGatewayDiv').append('<input type="hidden" name="serverInfoGateway" value="">');
	$('#serverInfoSubnetMaskDiv').append('<input type="hidden" name="serverInfoSubnetMask" value="">');
	$('#serverInfoMOSVersionDiv').append('<input type="hidden" name="serverInfoMOSVersion" value="">');
	$('#serverInfoHostIDDiv').append('<input type="hidden" name="serverInfoHostID" value="">');
	$('#serverInfoSerialNoDiv').append('<input type="hidden" name="serverInfoSerialNo" value="">');
	$('#serverInfoUserLicenseDiv').append('<input type="hidden" name="serverInfoUserLicense" value="">');
	$('#serverInfoPrimaryDNSDiv').append('<input type="hidden" name="serverInfoPrimaryDNS" value="">');
	$('#serverInfoSecondaryDNSDiv').append('<input type="hidden" name="serverInfoSecondaryDNS" value="">');
	$('#serverInfoModelNoDiv').append('<input type="hidden" name="serverInfoModelNo" value="">');
	$('#serverInfoWebHostingSpaceDiv').append('<input type="hidden" name="serverInfoWebHostingSpace" value="">');
	$('#serverInfoSQLDiv').append('<input type="hidden" name="serverInfoSQL" value="">');
	$('#serverInfoSQLDBNameDiv').append('<input type="hidden" name="serverInfoSQLDBName" value="">');
	$('#serverInfoSQLSizeDiv').append('<input type="hidden" name="serverInfoSQLSize" value="">');
	$('#serverInfoSQLIDDiv').append('<input type="hidden" name="serverInfoSQLID" value="">');
	$('#serverInfoSQLDBPasswordDiv').append('<input type="hidden" name="serverInfoSQLDBPassword" value="">');
	$('#serverInfoBackupDiv').append('<input type="hidden" name="serverInfoBackup" value="">');
	$('#serverInfoBackupSizeDiv').append('<input type="hidden" name="serverInfoBackupSize" value="">');
	$('#serverInfoMonitoringDiv').append('<input type="hidden" name="serverInfoMonitoring" value="">');
	$('#serverInfoInstalledApplicationDiv').append('<textarea name="serverInfoInstalledApplication" value="" style="display:none;"/>');
}

/**
 * Remove server detail
 */
function removeServerDetail()
{	
	var index = $('option:selected', '#serverInfoServerID').index();
	if (index < 0)
		return;
	var MsgBox = new messageBox();
	if (MsgBox.POPDEL(confirmMessage) != MsgBox.YES_CONFIRM) 
		return;
	
	$('#serverInfoServerID').find('option').eq(index).remove();
	var serverInfoDetailIDField = $('#serverInfoDetailIDDiv').find('input[name="serverInfoDetailID"]').eq(index);
	var serverInfoDetailID = serverInfoDetailIDField.val();
	serverInfoDetailIDField.remove();
	$('#serverInfoAliasDiv').find('input[name="serverInfoAlias"]').eq(index).remove();
	$('#serverInfoNameDiv').find('input[name="serverInfoName"]').eq(index).remove();
	$('#serverInfoHardwareDiv').find('input[name="serverInfoHardware"]').eq(index).remove();
	$('#serverInfoOSDiv').find('input[name="serverInfoOS"]').eq(index).remove();
	$('#serverInfoIPDiv').find('input[name="serverInfoIP"]').eq(index).remove();
	$('#serverInfoGatewayDiv').find('input[name="serverInfoGateway"]').eq(index).remove();
	$('#serverInfoSubnetMaskDiv').find('input[name="serverInfoSubnetMask"]').eq(index).remove();
	$('#serverInfoMOSVersionDiv').find('input[name="serverInfoMOSVersion"]').eq(index).remove();
	$('#serverInfoHostIDDiv').find('input[name="serverInfoHostID"]').eq(index).remove();
	$('#serverInfoSerialNoDiv').find('input[name="serverInfoSerialNo"]').eq(index).remove();
	$('#serverInfoUserLicenseDiv').find('input[name="serverInfoUserLicense"]').eq(index).remove();
	$('#serverInfoPrimaryDNSDiv').find('input[name="serverInfoPrimaryDNS"]').eq(index).remove();
	$('#serverInfoSecondaryDNSDiv').find('input[name="serverInfoSecondaryDNS"]').eq(index).remove();
	$('#serverInfoModelNoDiv').find('input[name="serverInfoModelNo"]').eq(index).remove();
	$('#serverInfoWebHostingSpaceDiv').find('input[name="serverInfoWebHostingSpace"]').eq(index).remove();
	$('#serverInfoSQLDiv').find('input[name="serverInfoSQL"]').eq(index).remove();
	$('#serverInfoSQLDBNameDiv').find('input[name="serverInfoSQLDBName"]').eq(index).remove();
	$('#serverInfoSQLSizeDiv').find('input[name="serverInfoSQLSize"]').eq(index).remove();
	$('#serverInfoSQLIDDiv').find('input[name="serverInfoSQLID"]').eq(index).remove();
	$('#serverInfoSQLDBPasswordDiv').find('input[name="serverInfoSQLDBPassword"]').eq(index).remove();
	$('#serverInfoBackupDiv').find('input[name="serverInfoBackup"]').eq(index).remove();
	$('#serverInfoBackupSizeDiv').find('input[name="serverInfoBackupSize"]').eq(index).remove();
	$('#serverInfoMonitoringDiv').find('input[name="serverInfoMonitoring"]').eq(index).remove();
	$('#serverInfoInstalledApplicationDiv').find('textarea[name="serverInfoInstalledApplication"]').eq(index).remove();
	
	//if the server detail is existed in database (had an ID), put into remove div to notify removing when submitting
	if (serverInfoDetailID != '');
		$('#serverInfoDetailRemoveIDDiv').append('<input type="hidden" name="serverInfoDetailRemoveID" value="'+serverInfoDetailID+'">');
	
	var visible = 'none';
	$('#serverInfoNameDiv').css('display', visible);
	$('#serverInfoHardwareDiv').css('display', visible);
	$('#serverInfoOSDiv').css('display', visible);
	$('#serverInfoIPDiv').css('display', visible);
	$('#serverInfoGatewayDiv').css('display', visible);
	$('#serverInfoSubnetMaskDiv').css('display', visible);
	$('#serverInfoMOSVersionDiv').css('display', visible);
	$('#serverInfoHostIDDiv').css('display', visible);
	$('#serverInfoSerialNoDiv').css('display', visible);
	$('#serverInfoUserLicenseDiv').css('display', visible);
	$('#serverInfoPrimaryDNSDiv').css('display', visible);
	$('#serverInfoSecondaryDNSDiv').css('display', visible);
	$('#serverInfoModelNoDiv').css('display', visible);
	$('#serverInfoWebHostingSpaceDiv').css('display', visible);
	$('#serverInfoSQLDiv').css('display', visible);
	$('#serverInfoSQLDBNameDiv').css('display', visible);
	$('#serverInfoSQLSizeDiv').css('display', visible);
	$('#serverInfoSQLIDDiv').css('display', visible);
	$('#serverInfoSQLDBPasswordDiv').css('display', visible);
	$('#serverInfoBackupDiv').css('display', visible);
	$('#serverInfoBackupSizeDiv').css('display', visible);
	$('#serverInfoMonitoringDiv').css('display', visible);
	$('#serverInfoInstalledApplicationDiv').css('display', visible);
	
	$('input[name=btnRemoveServerDetail]').attr('disabled', true);
}

/**
 * Change server info
 */
function changeServerInfoContent(ctrl, rltCtr)
{
	var index = $('option:selected', '#serverInfoServerID').index();
	var tagName = $(ctrl).attr('tagName').toLowerCase();
	if (tagName == 'textarea')
		$('textarea[name='+rltCtr+']').eq(index).text($(ctrl).text());
	else
		$('input[name='+rltCtr+']').eq(index).val($(ctrl).val());
}

/**
 * Select server info
 */
function selectServerInfo()
{
	var index = $('option:selected', '#serverInfoServerID').index();
	var visible = 'none';
	if (index >= 0)
	{
		visible = 'table-row';
		$('input[name=btnRemoveServerDetail]').removeAttr('disabled');
	}
	customSetValue('tbxServerInfoNameID', $('#serverInfoNameDiv').find('input[name=serverInfoName]').eq(index).val());
	customSetValue('tbxServerInfoHardwareID', $('#serverInfoHardwareDiv').find('input[name=serverInfoHardware]').eq(index).val());
	customSetValue('tbxServerInfoOSID', $('#serverInfoOSDiv').find('input[name=serverInfoOS]').eq(index).val());
	customSetValue('tbxServerInfoIPID', $('#serverInfoIPDiv').find('input[name=serverInfoIP]').eq(index).val());
	customSetValue('tbxServerInfoGatewayID', $('#serverInfoGatewayDiv').find('input[name=serverInfoGateway]').eq(index).val());
	customSetValue('tbxServerInfoSubnetMaskID', $('#serverInfoSubnetMaskDiv').find('input[name=serverInfoSubnetMask]').eq(index).val());
	customSetValue('tbxServerInfoMOSVersionID', $('#serverInfoMOSVersionDiv').find('input[name=serverInfoMOSVersion]').eq(index).val());
	customSetValue('tbxServerInfoHostIDID', $('#serverInfoHostIDDiv').find('input[name=serverInfoHostID]').eq(index).val());
	customSetValue('tbxServerInfoSerialNoID', $('#serverInfoSerialNoDiv').find('input[name=serverInfoSerialNo]').eq(index).val());
	customSetValue('tbxServerInfoUserLicenseID', $('#serverInfoUserLicenseDiv').find('input[name=serverInfoUserLicense]').eq(index).val());
	customSetValue('tbxServerInfoPrimaryDNSID', $('#serverInfoPrimaryDNSDiv').find('input[name=serverInfoPrimaryDNS]').eq(index).val());
	customSetValue('tbxServerInfoSecondaryDNSID', $('#serverInfoSecondaryDNSDiv').find('input[name=serverInfoSecondaryDNS]').eq(index).val());
	customSetValue('tbxServerInfoModelNoID', $('#serverInfoModelNoDiv').find('input[name=serverInfoModelNo]').eq(index).val());
	customSetValue('tbxServerInfoWebHostingSpaceID', $('#serverInfoWebHostingSpaceDiv').find('input[name=serverInfoWebHostingSpace]').eq(index).val());
	customSetValue('rdoServerInfoSQL', $('#serverInfoSQLDiv').find('input[name=serverInfoSQL]').eq(index).val());
	customSetValue('tbxServerInfoSQLDBNameID', $('#serverInfoSQLDBNameDiv').find('input[name=serverInfoSQLDBName]').eq(index).val());
	customSetValue('tbxServerInfoSQLSizeID', $('#serverInfoSQLSizeDiv').find('input[name=serverInfoSQLSize]').eq(index).val());
	customSetValue('tbxServerInfoSQLIDID', $('#serverInfoSQLIDDiv').find('input[name=serverInfoSQLID]').eq(index).val());
	customSetValue('tbxServerInfoSQLDBPasswordID', $('#serverInfoSQLDBPasswordDiv').find('input[name=serverInfoSQLDBPassword]').eq(index).val());
	customSetValue('rdoServerInfoBackup', $('#serverInfoBackupDiv').find('input[name=serverInfoBackup]').eq(index).val());
	customSetValue('tbxServerInfoBackupSizeID', $('#serverInfoBackupSizeDiv').find('input[name=serverInfoBackupSize]').eq(index).val());
	customSetValue('rdoServerInfoMonitoring', $('#serverInfoMonitoringDiv').find('input[name=serverInfoMonitoring]').eq(index).val());
	customSetValue('txaServerInfoInstalledApplicationID',
			$('#serverInfoInstalledApplicationDiv').find('textarea[name=serverInfoInstalledApplication]').eq(index).text());
	
	$('#serverInfoNameDiv').css('display', visible);
	$('#serverInfoHardwareDiv').css('display', visible);
	$('#serverInfoOSDiv').css('display', visible);
	$('#serverInfoIPDiv').css('display', visible);
	$('#serverInfoGatewayDiv').css('display', visible);
	$('#serverInfoSubnetMaskDiv').css('display', visible);
	$('#serverInfoMOSVersionDiv').css('display', visible);
	$('#serverInfoHostIDDiv').css('display', visible);
	$('#serverInfoSerialNoDiv').css('display', visible);
	$('#serverInfoUserLicenseDiv').css('display', visible);
	$('#serverInfoPrimaryDNSDiv').css('display', visible);
	$('#serverInfoSecondaryDNSDiv').css('display', visible);
	$('#serverInfoModelNoDiv').css('display', visible);
	$('#serverInfoWebHostingSpaceDiv').css('display', visible);
	$('#serverInfoSQLDiv').css('display', visible);
	$('#serverInfoSQLDBNameDiv').css('display', visible);
	$('#serverInfoSQLSizeDiv').css('display', visible);
	$('#serverInfoSQLIDDiv').css('display', visible);
	$('#serverInfoSQLDBPasswordDiv').css('display', visible);
	$('#serverInfoBackupDiv').css('display', visible);
	$('#serverInfoBackupSizeDiv').css('display', visible);
	$('#serverInfoMonitoringDiv').css('display', visible);
	$('#serverInfoInstalledApplicationDiv').css('display', visible);	
}

/**
 * Custom set value
 */
function customSetValue(ctrId, value)
{
	if(value==null || value==undefined) {
		value = "";
	}
	var ctr = $('#'+ctrId);
	var tagName = ctr.attr('tagName').toLowerCase();
	if (tagName == 'input')
	{
		if (ctr.attr('type') == 'text')
		{
			ctr.val(value);
		}
		else if (ctr.attr('type') == 'radio')
		{
			var ctrArr = $('input[name='+ctrId+']');
			for (var i=0; i<ctrArr.length; i++)
			{
				if (ctrArr.eq(i).val() == value)
					ctrArr.eq(i).attr('checked', true);
				else
					ctrArr.eq(i).attr('checked', false);
			}
		}
	}
	else if ((tagName == 'textarea') || (tagName == 'label') || (tagName == 'div'))
	{
		ctr.text(value);
	}
}

/**
 * Protect max length
 */
function protectMaxLength(ctr, length)
{
	if (getSelText().length == 0)
		return (ctr.value.length <= length-1);
	return true;
}

/**
 * Get selected text
 */
function getSelText()
{
    var txt = '';
    if (window.getSelection)
    {
        txt = window.getSelection();
    }
    else if (document.getSelection)
    {
        txt = document.getSelection();
    }
    else if (document.selection)
    {
        txt = document.selection.createRange().text;
    }
    return txt;	
}

/**
 * Get array of component code from mail accounts table
 */
function getMailAccountsTableComponentCodeArray() {
	return [
			 CODE_TEXT, 
			 CODE_TEXTBOX,
			 CODE_TEXTBOX,
			 CODE_TEXTBOX,
			 CODE_TEXTBOX,
			 CODE_TEXTBOX,
			 CODE_CHECKBOX,
			 CODE_CHECKBOX,
			 CODE_CHECKBOX, 
			 CODE_BUTTON
		   ]; 
}

/**
 * Get array of component name from mail accounts table
 */
function getMailAccountsTableComponentNamePrefixArray() {
	return [
			 "mailAccountNo", 
			 "mailAccountEmail",
			 "mailAccountPassword",
			 "mailAccountPOP",
			 "mailAccountBox",
			 "mailAccountAddMailBox",
			 "mailAccountVirusScan",
			 "mailAccountAntiSpam",
			 "mailAccountJunkManagement", 
			 "mailAccountRemove"
		   ]; 
}

/**
 * Get array of component name from mail accounts table
 */
function createMailAccountsTableValueArray(mailAccountNo, 
										   mailAccountEmail,
										   mailAccountPassword,
									   	   mailAccountPOP,
										   mailAccountBox,
										   mailAccountAddMailBox,
										   mailAccountVirusScan,
										   mailAccountAntiSpam,
										   mailAccountJunkManagement, 
										   mailAccountRemove) {
	return [
			  mailAccountNo, 
		      mailAccountEmail,
		   	  mailAccountPassword,
	   	      mailAccountPOP,
		      mailAccountBox,
		      mailAccountAddMailBox,
		      mailAccountVirusScan,
		      mailAccountAntiSpam,
		      mailAccountJunkManagement, 
		      mailAccountRemove
		   ]; 
}

/**
 * Get array of component name from mail accounts table
 */
function getMailAccountTableComponentPositionArray() {
	return [
	          0,
			  1, 
		      2,
		   	  3,
	   	      4,
		      5,
		      6,
		      7,
		      8, 
		      11
		   ]; 
}

/**
 * Get new array of component value from mail accounts table
 */
function getNewMailAccountComponentValueArray() {
	var rowIndex = getTableRowCount('mailAccountsGroupTableID') - 1;	
	var table = document.getElementById('mailAccountsGroupTableID');
	var row = table.rows[rowIndex];		

    var mailAccountNo = ''; 
    mailAccountNo += '<input type="text" name="mailAccountIDs" value="" style="display: none;"/>';						

    var mailAccountEmail = '<textarea name="mailAccountNames" onkeypress="return checkLengthNotInputEntry(event, this, 50)"' +
	  						' onchange="customizeElementValueLength(this, 50);" class="B_SSM_S02_Page_Form_TextBox_MailAccount mailAccountNameClass"'+
	  						' style="overflow-x:hidden;overflow-y:auto;width:175px;" rows="2"/>';

    var mailAccountPassword = '<textarea name="mailAccountPasswords" onkeypress="return checkLengthNotInputEntry(event, this, 30)"' +
								' onchange="customizeElementValueLength(this, 30);" class="B_SSM_S02_Page_Form_TextBox_MailAccount"'+
								' style="overflow-x:hidden;overflow-y:auto;width:150px;" rows="2"/>';

    var mailAccountPOP = '<textarea name="mailAccountPOPServerNames" onkeypress="return checkLengthNotInputEntry(event, this, 50)"' +
						' onchange="customizeElementValueLength(this, 50);" class="B_SSM_S02_Page_Form_TextBox_MailAccount"'+
						' style="overflow-x:hidden;overflow-y:auto;width:175px;" rows="2"/>';
    
    var mailAccountAddMailBox = '<input type="text" name="mailAccountAddMailBoxSizes" value="0" ' +
								 ' onchange="valueOfInvalidNumberWithElement(this, 0); calculateMailBoxSize(' + rowIndex + ');" ' +
								 ' onkeypress="return isValidNumberOnKeyUpWithElement(this, event, null, null);" ' +
								 ' maxlength="10" ' +
								 ' class="B_SSM_S02_Page_Form_TextBox_MailBox"/>' ;

    var mailAccountBox = '<input type="text" name="mailAccountQtys" value="20" ' +
   					     ' onchange="valueOfInvalidNumberWithElement(this, 20);"' +
   					     ' onkeypress="return isValidNumberOnKeyUpWithElement(this, event, null, null);" ' +
   					     ' maxlength="10" ' +
					     'class="B_SSM_S02_Page_Form_TextBox_MailBox"/>';   

    var mailAccountVirusScan = '<input type="checkbox" name="mailAccountVirusScans" value="1" ' +
    							' onclick="updateMailAccountTotals(); setTextForCheckBox(this, \'mailAccountVirusScanText' +
    							 virusScanComponentID + '\');"' +
    							' class=""/>';
	mailAccountVirusScan += '<input type="text" name="mailAccountVirusScanTexts" ' +
    						' id="mailAccountVirusScanText' + virusScanComponentID++ +
    						'" style="display: none;"/>';
    							
    var mailAccountAntiSpam = '<input type="checkbox" name="mailAccountAntiSpams" value="1" ' +
							   ' onclick="updateMailAccountTotals(); setTextForCheckBox(this, \'mailAccountAntiSpamText' +
    						   antiSpamComponentID + '\');"' +
        					   ' class=""/>';
    mailAccountAntiSpam += '<input type="text" name="mailAccountAntiSpamTexts" ' +
    						' id="mailAccountAntiSpamText' + antiSpamComponentID++ +
    						'" style="display: none;"/>';
        					   
    var mailAccountJunkManagement = '<input type="checkbox" name="mailAccountJunkManagements" value="1" ' +
									' onclick="updateMailAccountTotals();setTextForCheckBox(this, \'mailAccountJunkManagementText' +
	        						junkManagementComponentID + '\');"' + ' class=""/>';
    mailAccountJunkManagement += '<input type="text" name="mailAccountJunkManagementTexts" ' +
								 ' id="mailAccountJunkManagementText' + junkManagementComponentID++ +
							     '" style="display: none;"/>';
		        					   
    var mailAccountRemove = '<a href="javascript:doAccountMailsRowRemove(' + rowIndex + ', this)" ' +
    						'class="B_SSM_S02_Page_Form_AccountMailsGroup_Href_RemoveLink"> ' +	        						
    						'<span style="font-weight: bold; font-size: 15px; color: black;">X</span> ' +
    						'</a> ';
	return [
			  mailAccountNo, 
		      mailAccountEmail,
		   	  mailAccountPassword,
	   	      mailAccountPOP,
	   	      mailAccountAddMailBox,
		      mailAccountBox,
		      mailAccountVirusScan,
		      mailAccountAntiSpam,
		      mailAccountJunkManagement, 
		      mailAccountRemove
		   ];
}

/**
 * Set text for check box
 */
function setTextForCheckBox(checkBox, txtID) {						
	var text = document.getElementById(txtID);
	if (checkBox.checked == true) {
		text.value = '1';	
	} else {
		text.value = '0';		
	}
}

/**
 * Calculate mail box size
 */
function  calculateMailBoxSize(rowIndex) {			
	var table = document.getElementById('mailAccountsGroupTableID');
	var mailBoxQty = table.rows[rowIndex].cells[FIELDPOS_MAILACCOUNT_ADDBOX].childNodes[0].value;
	var mailSize = 20;
	if (mailBoxQty && 
		mailBoxQty.replace(/\s/g,"") != "" &&
		isNaN(mailBoxQty) == false) {
		mailSize += mailBoxQty * 10;				
	}
	table.rows[rowIndex].cells[FIELDPOS_MAILACCOUNT_MAILBOX].childNodes[0].value = mailSize;
	// Update totals
	updateMailAccountTotals();	
}

/**
 * Calculate mail box size
 */
function  calculateMailBoxSizeWithSpecificRow(row) {					
	var mailBoxQty = row.cells[FIELDPOS_MAILACCOUNT_MAILBOX].childNodes[0].value;
	var mailSize = 20;
	if (mailBoxQty && 
		mailBoxQty.replace(/\s/g,"") != "" &&
		isNaN(mailBoxQty) == false) {
		mailSize += mailBoxQty * 10;				
	}
	row.cells[FIELDPOS_MAILACCOUNT_ADDBOX].childNodes[0].value = mailSize;
	// Update totals
	updateMailAccountTotals();	
}

/**
 * Update field 'No' of mail accounts table deleted
 */
function updateMailAccountsDeleteNoColValues() {
	var table = document.getElementById('mailAccountsDeletedGroupTableID');
	var rowCount = getTableRowCount('mailAccountsDeletedGroupTableID');			
	for (var i = 1; i < rowCount; i++) {
		var mailAccountNo = i + '';
		var mailAccountID = ''; 
		var row = table.rows[i];
		if (row == null) {
			continue;
		}
		var cell = row.cells[FIELDPOS_MAILACCOUNTDELETED_N0];
		if (cell != null) { 
			var childNodes = cell.childNodes;
			if (childNodes != null) {
				for (var j = 0; j < childNodes.length; j++) {
					var child = childNodes[j];
					if (child != null && 
						child.attributes != null &&
						child.attributes["type"] != null &&
						child.attributes["type"].value != null &&
						child.attributes["type"].value == "text") {
						mailAccountID = child.attributes["value"].value;
					}
				}
			}
			mailAccountNo += '<input type="text" name="mailAccountDeletedIDs" value="' + 
			 				 mailAccountID + '" style="display: none;"/>';						  		
			cell.innerHTML = mailAccountNo;
		}								
	}
}

/**
 * Update field 'Remove Link' of mail accounts table deleted
 */
function updateMailAccountDeletedRemoveLinkColValues() {
	var table = document.getElementById('mailAccountsDeletedGroupTableID');
	var rowCount = getTableRowCount('mailAccountsDeletedGroupTableID');
	for (var i = 1; i < rowCount; i++) {
		var mailAccountRemove = '<a href="javascript:doAccountMailsDeletedRowRemove(' + i + ')" ' +
    						     'class="B_SSM_S02_Page_Form_AccountMailsGroup_Href_RemoveLink"> ' +	        						
    						     '<span style="font-weight: bold; font-size: 15px; color: black;">X</span> ' +
    						     '</a> ';
		table.rows[i].cells[FIELDPOS_MAILACCOUNTDELETED_REMOVELINK].innerHTML = mailAccountRemove;
	}
}

/**
 * Update field 'No' of mail accounts table
 */
function updateMailAccountsNoColValues() {
	var table = document.getElementById('mailAccountsGroupTableID');
	var rowCount = getTableRowCount('mailAccountsGroupTableID');
	for (var i = 2; i < rowCount - 1; i++) {
		var mailAccountNo = i - 1 + '';
		var mailAccountID = ''; 
		var row = table.rows[i];
		if (row == null) {
			continue;
		}
		var cell = row.cells[FIELDPOS_MAILACCOUNT_N0];
		if (cell != null) { 
			var childNodes = cell.childNodes;
			if (childNodes != null) {
				for (var j = 0; j < childNodes.length; j++) {
					var child = childNodes[j];
					if (child != null && 
						child.attributes != null &&
						child.attributes["type"] != null &&
						child.attributes["type"].value != null &&
						child.attributes["type"].value == "text") {
						mailAccountID = child.attributes["value"].value;
					}
				}
			}
		}				
		mailAccountNo += '<input type="text" name="mailAccountIDs" value="' + 
						  mailAccountID + '" style="display: none;"/>';				
		table.rows[i].cells[FIELDPOS_MAILACCOUNT_N0].innerHTML = mailAccountNo;
	}
}

/**
 * Update field 'Remove Link' of mail accounts table
 */
function updateMailAccountRemoveLinkColValues() {
	var table = document.getElementById('mailAccountsGroupTableID');
	var rowCount = getTableRowCount('mailAccountsGroupTableID');
	for (var i = 2; i < rowCount - 1; i++) {
		var mailAccountRemove = '<a href="javascript:doAccountMailsRowRemove(' + i + ', this)" ' +
    						     'class="B_SSM_S02_Page_Form_AccountMailsGroup_Href_RemoveLink"> ' +	        						
    						     '<span style="font-weight: bold; font-size: 15px; color: black;">X</span> ' +
    						     '</a> ';
		table.rows[i].cells[FIELDPOS_MAILACCOUNT_REMOVELINK].innerHTML = mailAccountRemove;
	}
}

/**
 * Update mail account totals
 */
function updateMailAccountTotals() {
	var table = document.getElementById('mailAccountsGroupTableID');
	var rowCount = getTableRowCount('mailAccountsGroupTableID');
	// Update total
	var totalMailAcc = table.rows[rowCount - 1].cells[FIELDPOS_MAILACCOUNT].innerHTML = (rowCount - 3);
	if(document.getElementById("totalMaillAccount")){
		document.getElementById("totalMaillAccount").value = totalMailAcc;
	}
	
	// Update mail box
	var mailBoxTotal = 0;
	for (var i = 2; i < rowCount - 1; i++) {
		var mailBoxQty = table.rows[i].cells[FIELDPOS_MAILACCOUNT_MAILBOX].childNodes[0].value;
		if (mailBoxQty && 
			mailBoxQty.replace(/\s/g,"") != "" &&
			isNaN(mailBoxQty) == false) {
			mailBoxTotal += Number(mailBoxQty);
		}			
	}
	table.rows[rowCount - 1].cells[FIELDPOS_MAILACCOUNT_MAILBOX].innerHTML = mailBoxTotal;

	// Update add box
	var mailBoxSizeTotal = 0;
	for (var i = 2; i < rowCount - 1; i++) {
		var mailBoxSize = table.rows[i].cells[FIELDPOS_MAILACCOUNT_ADDBOX].childNodes[0].value;
		if (mailBoxSize && 
			mailBoxSize.replace(/\s/g,"") != "" &&
			isNaN(mailBoxSize) == false) {
			mailBoxSizeTotal += Number(mailBoxSize);
		}
	}
	table.rows[rowCount - 1].cells[FIELDPOS_MAILACCOUNT_ADDBOX].innerHTML = mailBoxSizeTotal;

	// Update virus scan total
	var virusScanTotal = 0;
	for (var i = 2; i < rowCount - 1; i++) {
		var row = table.rows[i];
		if (row == null) {
			continue;
		}
		var cell = row.cells[FIELDPOS_MAILACCOUNT_VIRUSSCAN];
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
						virusScanTotal += (child.checked == true) ? 1 : 0;
					}
				}
			}
		}
	}
	table.rows[rowCount - 1].cells[FIELDPOS_MAILACCOUNT_VIRUSSCAN].innerHTML = virusScanTotal;

	// Update anti spam total
	var antiSpamTotal = 0;
	for (var i = 2; i < rowCount - 1; i++) {
		var row = table.rows[i];
		if (row == null) {
			continue;
		}
		var cell = row.cells[FIELDPOS_MAILACCOUNT_ANTISPAM];
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
						antiSpamTotal += (child.checked == true) ? 1 : 0;
					}
				}
			}
		}
	}
	table.rows[rowCount - 1].cells[FIELDPOS_MAILACCOUNT_ANTISPAM].innerHTML = antiSpamTotal;

	// Update junk mngt total
	var junkTotal = 0;
	for (var i = 2; i < rowCount - 1; i++) {
		var row = table.rows[i];
		if (row == null) {
			continue;
		}
		var cell = row.cells[FIELDPOS_MAILACCOUNT_JUNKMNGT];
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
						junkTotal += (child.checked == true) ? 1 : 0;
					}
				}
			}
		}
	}
	table.rows[rowCount - 1].cells[FIELDPOS_MAILACCOUNT_JUNKMNGT].innerHTML = junkTotal;
}

/**
 * Do remove row of account mail
 */
function doAccountMailsRowRemove(rowNo, obj) {
	var MsgBox = new messageBox();
	// find mail account row by index
	var table = document.getElementById('mailAccountsGroupTableID');
    var row= table.rows(rowNo);
    // find Mail Account
    var content = document.createElement("div");
    content.innerHTML = row.innerHTML;
    var result = $(content);
    var mailAccount = result.find(".mailAccountNameClass").val();
    if (mailAccount == undefined) {
        mailAccount = "";
    }
    // get message
    var confirmMessage = document.getElementById('info.ERR4SC016').innerHTML;
    confirmMessage = confirmMessage.replace(/ACCOUNTNAME/, mailAccount);

	if (MsgBox.POPDEL(confirmMessage) == MsgBox.YES_CONFIRM) {
		removeTableRow('mailAccountsGroupTableID', rowNo);
		updateMailAccountsNoColValues();
		updateMailAccountRemoveLinkColValues();
		updateMailBoxQtyValues();
		updateMailAccountTotals();
	}
}

/**
 * Update field 'mail box qty' of mail accounts table
 */
function updateMailBoxQtyValues() {
	var table = document.getElementById('mailAccountsGroupTableID');
	var rowCount = getTableRowCount('mailAccountsGroupTableID');
	for (var i = 2; i < rowCount - 1; i++) {
		 var cellValue = table.rows[i].cells[FIELDPOS_MAILACCOUNT_MAILBOX].childNodes[0].value;
		 var mailAccountBox = '<input type="text" name="mailAccountQtys" value="' + cellValue + '" ' +
							   ' onchange="calculateMailBoxSize(' + i + ');" ' +
							   ' onkeyup="checkNumberFromElement(this);" ' +
						  	   'class="B_SSM_S02_Page_Form_TextBox_MailBox"/>';
		table.rows[i].cells[FIELDPOS_MAILACCOUNT_MAILBOX].innerHTML = mailAccountBox;
	}
}

/**
 * Check all box virus scan
 */
function checkAllMailVirusScan(){
	var isCheckAll = document.getElementById('checkAllVirusScanID').checked;
	var table = document.getElementById('mailAccountsGroupTableID');
	var rowCount = getTableRowCount('mailAccountsGroupTableID');
	for (var i = 2; i < rowCount - 1; i++) {
		var row = table.rows[i];
		if (row == null) {
			continue;
		}
		var cell = row.cells[FIELDPOS_MAILACCOUNT_VIRUSSCAN];
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
	// Update totals
	updateMailAccountTotals();
}

/**
 * Check all box anti spam
 */
function checkAllMailAntiSpam(){
	var isCheckAll = document.getElementById('checkAllAntiSpamID').checked;
	var table = document.getElementById('mailAccountsGroupTableID');
	var rowCount = getTableRowCount('mailAccountsGroupTableID');
	for (var i = 2; i < rowCount - 1; i++) {
		var row = table.rows[i];
		if (row == null) {
			continue;
		}
		var cell = row.cells[FIELDPOS_MAILACCOUNT_ANTISPAM];
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
	// Update totals
	updateMailAccountTotals();
}

/**
 * Check all box junk management
 */
function checkAllMailJunkManagement(){
	var isCheckAll = document.getElementById('checkAllJunkManagementID').checked;
	var table = document.getElementById('mailAccountsGroupTableID');
	var rowCount = getTableRowCount('mailAccountsGroupTableID');
	for (var i = 2; i < rowCount - 1; i++) {
		var row = table.rows[i];
		if (row == null) {
			continue;
		}
		var cell = row.cells[FIELDPOS_MAILACCOUNT_JUNKMNGT];
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
	// Update totals
	updateMailAccountTotals();
}
 
/**
 * Do action on checking auto mail account
 */
function doCheckAutoMailAccount(element) {
	if (element) {
		var mailAccount = document.getElementById('mailAccountID');
		var baseQty = document.getElementById('baseQtyID'); 
		var additionalOption = document.getElementById('additionalOptionID'); 
		mailAccount.disabled = element.checked? false : true;
		baseQty.disabled = element.checked? false : true;
		additionalOption.disabled = element.checked? false : true;
	}
}

/**
 * Do action on checking mail box qty
 */
function doCheckMailBoxQty(element) {
	if (element) {
		var mailBoxQty = document.getElementById('mailBoxQtyID');
		mailBoxQty.disabled = element.checked? false : true;
	}
}

/**
 * Do action on checking auto virus scan
 */
function doCheckAutoVirusScan(element) {
	if (element) {
		var optionVirusScan = document.getElementById('optionVirusScanID');
		optionVirusScan.disabled = element.checked? false : true;
	}
}

/**
 * Do action on checking auto anti spam
 */
function doCheckAutoAntiSpam(element) {
	if (element) {
		var antiSpam = document.getElementById('antiSpamID');
		antiSpam.disabled = element.checked? false : true;
	}
}

/**
* Do action on checking auto junk management
*/
function doCheckAutoJunkManagement(element) {
	if (element) {
		var junkManagement = document.getElementById('junkManagementID');
		junkManagement.disabled = element.checked? false : true;
	}
}

/**
 * Do remove row of account mail deleted
 */
function doAccountMailsDeletedRowRemove(rowNo) {
	var MsgBox = new messageBox();
	if (MsgBox.POPDEL(confirmMessage) == MsgBox.YES_CONFIRM) {
		removeTableRow('mailAccountsDeletedGroupTableID', rowNo);
		updateMailAccountsDeleteNoColValues();
		updateMailAccountDeletedRemoveLinkColValues();
	}
}

/**
 * Update field 'Remove Link' of rack equipment table 
 */
function updateRackEquipmentRemoveLinks() {
	var table = document.getElementById('rackEquipmentInfoTableID');
	var rowCount = getTableRowCount('rackEquipmentInfoTableID');
	for (var i = 1; i < rowCount; i++) {
		var rackEquipmentRemove = '<a href="javascript:doRackEquipmentRowRemove(' + i + ')" ' +
    						       'class="B_SSM_S02_Page_Form_AccountMailsGroup_Href_RemoveLink"> ' +	        						
    						       '<span style="font-weight: bold; font-size: 15px; color: black;">X</span> ' +
    						       '</a> ';
		table.rows[i].cells[FIELDPOS_RACKEQUIPMENT_REMOVELINK].innerHTML = rackEquipmentRemove;
	}
}

/**
 * Do remove row of rack equipment
 */
function doRackEquipmentRowRemove(rowNo) {
	var MsgBox = new messageBox();
	if (MsgBox.POPDEL(confirmMessage) != MsgBox.YES_CONFIRM) 
		return;
	var removeDiv = document.getElementById('rackDetailRemoveDiv');
	var table = document.getElementById('rackEquipmentInfoTableID');
	var nodes = table.rows[rowNo].cells[3].childNodes;
	var rackDetailID = '';
	for (var i=0; i<nodes.length; i++)
	{
		if ((nodes[i].attributes!=null) 
				&& (nodes[i].attributes['type']!=null && nodes[i].attributes['type'].value.toLowerCase()=='hidden') 
				&& (nodes[i].attributes['name']!=null && nodes[i].attributes['name'].value.toLowerCase()=='rackdetailid'))
		{					
			nodes[i].attributes['name'].value = 'rackDetailRemoveID';
			rackDetailID = nodes[i];
			break;
		}
	}
	removeDiv.appendChild(rackDetailID);
	removeTableRow('rackEquipmentInfoTableID', rowNo);
	updateRackEquipmentRemoveLinks();
}

/**
 * Get array of component pos from rack equipment table 
 */							   
function getRackEquipmentTableComponentPositionArray() {
	return [
			  0,
			  1, 
		      2,
		   	  3,
	   	      4
		    ]; 
}

/**
 * Get array of rack equipment component value 
 */
function getNewRackEquipmentComponentValueArray() {
	var rowIndex = getTableRowCount('rackEquipmentInfoTableID');	
	var rackEquipRemove = '<a href="javascript:doRackEquipmentRowRemove(' + rowIndex + ')" ' +
    					   'class="B_SSM_S02_Page_Form_AccountMailsGroup_Href_RemoveLink"> ' +	        						
    					   '<span style="font-weight: bold; font-size: 15px; color: black;">X</span> ' +
    					   '</a> ';					
	
	var cboEquipLocationClone = document.getElementById('cboEquipLocationClone');
    var newEquipLocationClone = cboEquipLocationClone.cloneNode(true);
    newEquipLocationClone.removeAttribute('style');
    newEquipLocationClone.setAttribute('class','B_SSM_S02_Page_Form_TextBox_EquipLocation');
    var equipSuite = '<input type="text" name="equipmentSuite" maxlength="50" class="B_SSM_S02_Page_Form_TextBox_EquipmentSuite"/>';
	var rackEquipRackNo = '<input type="text" name="rackNo" maxlength="50 class="B_SSM_S02_Page_Form_TextBox_RackNo"/>';
    rackEquipRackNo += '<input type="hidden" name="rackDetailID" value=""/>';  
    var maxPower = '<input type="text" name="maxPower" maxlength="50" onchange="changeOnlyDecNumbers(this, 0)" onkeypress="return onlyDecNumbers(event)" class="B_SSM_S02_Page_Form_TextBox_MaxPower"/>';
    
	return [
			  rackEquipRemove, 
			  newEquipLocationClone.outerHTML,
		   	  equipSuite,
		      rackEquipRackNo,
		      maxPower
		    ];
}																	
							    
/**
 * Get Firewall Interface IP Table Component Position Array
 */
function getFirewallInterfaceIPTableComponentPositionArray() {
	return [
			  0,
			  1, 
		      2
		    ]; 
}

/**
 * Get New Firewall Interface IP Component Value Array
 */
function getNewFirewallInterfaceIPComponentValueArray() {
	var rowIndex = getTableRowCount('firewallInterfaceIPTableID');	
	var firewallInterfaceIPRemove = '<a href="javascript:doFirewallInterfaceIPRowRemove(' + rowIndex + ')" ' +
	        					     'class="B_SSM_S02_Page_Form_AccountMailsGroup_Href_RemoveLink"> ' +	        						
	        					     '<span style="font-weight: bold; font-size: 15px; color: black;">X</span> ' +
	        					     '</a> ';					
    var firewallInterfaceIP = '<input type="text" name="firewallInterfaceIPs" maxlength="40" class="B_SSM_S02_Page_Form_TextBox_MailAccount"/>';
	    firewallInterfaceIP += '<input type="text" name="firewallInterfaceIPIDs" style="display:none;" value=""/>';
	    
	var firewallInterfaceIPZone = '<input type="text" name="firewallInterfaceZones" maxlength="30" class="B_SSM_S02_Page_Form_TextBox_MailBox"/>';
      
	return [
			  firewallInterfaceIPRemove, 
		      firewallInterfaceIP,
		   	  firewallInterfaceIPZone
		    ];
}														

/**
 * Update Firewall Interface Ip Remove Links
 */
function updateFirewallInterfaceIpRemoveLinks() {
	var table = document.getElementById('firewallInterfaceIPTableID');
	var rowCount = getTableRowCount('firewallInterfaceIPTableID');
	for (var i = 1; i < rowCount; i++) {
		var firewallInterfaceIPRemove = '<a href="javascript:doFirewallInterfaceIPRowRemove(' + i + ')" ' +
    						       		 'class="B_SSM_S02_Page_Form_AccountMailsGroup_Href_RemoveLink"> ' +	        						
    						         	 '<span style="font-weight: bold; font-size: 15px; color: black;">X</span> ' +
    						       	 	 '</a> ';
		table.rows[i].cells[FIELDPOS_RACKEQUIPMENT_REMOVELINK].innerHTML = firewallInterfaceIPRemove;
	}
}

/**
  * Update field 'No' of firewall policy table
  */
function updateFirewallPolicyNoColValues() {
 	var table = document.getElementById('firewallPolicyTableID');
 	var rowCount = getTableRowCount('firewallPolicyTableID');
 	for (var i = 1; i < rowCount; i++) {
 		var firewallPolicyIDCell = i + '';
 		var firewallPolicyID = ''; 
 		var row = table.rows[i];
 		if (row == null) {
 			continue;
 		}
 		var cell = row.cells[FIELDPOS_FIREWALL_NO];
 		if (cell != null) { 
 			var childNodes = cell.childNodes;
 			if (childNodes != null) {
 				for (var j = 0; j < childNodes.length; j++) {
 					var child = childNodes[j];
 					if (child != null && 
 						child.attributes != null &&
 						child.attributes["type"] != null &&
 						child.attributes["type"].value != null &&
 						child.attributes["type"].value == "text") {
 						firewallPolicyID = child.attributes["value"].value;
 					}
 				}
 			}
 		}				
 		firewallPolicyIDCell += '<input type="text" name="firewallPolicyIDs" value="' + 
 						 		firewallPolicyID + '" style="display: none;"/>';				
 		table.rows[i].cells[FIELDPOS_FIREWALL_NO].innerHTML = firewallPolicyIDCell;
 		
 	}
}

/**
 * Do remove Firewall InterfaceIP row
 */
function doFirewallInterfaceIPRowRemove(rowNo) {
	var MsgBox = new messageBox();
	if (MsgBox.POPDEL(confirmMessage) == MsgBox.YES_CONFIRM) {
		removeTableRow('firewallInterfaceIPTableID', rowNo);			
		updateFirewallInterfaceIpRemoveLinks();
		// Enable add btn
		document.getElementById('firewallInterfaceAddBtnID').disabled = false;
	}
}

/**
 * Get Firewall Policy Table Component Position Array
 */	    						   
function getFirewallPolicyTableComponentPositionArray() {
	return [
			  0,
			  1, 
		      2,
		      3,
		      4,
		      5,
		      6
		    ]; 
}

/**
 * Get New Firewall Policy Component Value Array
 */
function getNewFirewallPolicyComponentValueArray()	{
	var rowIndex = getTableRowCount('firewallPolicyTableID');	
	var firewallPolicyRemove = '<a href="javascript:doFirewallPolicyRowRemove(' + rowIndex + ')" ' +
	        					'class="B_SSM_S02_Page_Form_AccountMailsGroup_Href_RemoveLink"> ' +	        						
	        					'<span style="font-weight: bold; font-size: 15px; color: black;">X</span> ' +
	        					'</a> ';					
    var firewallPolicy = autoPolicyMessage;
    firewallPolicy += '<input type="text" name="firewallPolicyIDs" style="display:none;" value=""/>';
	var firewallPolicySource = '<input type="text" maxlength="50" name="firewallPolicySources" class="B_SSM_S02_Page_Form_TextBox_MailAccount"/>';
    var firewallPolicyDestination = '<input type="text" maxlength="50" name="firewallPolicyDestinations" class="B_SSM_S02_Page_Form_TextBox_MailAccount"/>';
    var firewallPolicyProtocol = '<textarea name="firewallPolicyProtocols" onkeypress="return checkLength(event, this, 100)"' +
		  						  ' onchange="customizeElementValueLength(this, 100);" class="B_SSM_S02_Page_Form_TextBox_MailAccount"/>';
    var firewallPolicyAction = '<input name="firewallPolicyActions" maxlength="10" type="text" class="B_SSM_S02_Page_Form_TextBox_MailAccount"/>';
    var firewallPolicyRemarks = '<input type="text" maxlength="100" name="firewallPolicyRemarks" class="B_SSM_S02_Page_Form_TextBox_MailAccount"/>';
	return [
			  firewallPolicyRemove, 
		      firewallPolicy,
		   	  firewallPolicySource,
		   	  firewallPolicyDestination,
		   	  firewallPolicyProtocol,
		   	  firewallPolicyAction,
		   	  firewallPolicyRemarks
		    ];
}													

/**
 * Update Firewall Policy Remove Links
 */
function updateFirewallPolicyRemoveLinks() {
	var table = document.getElementById('firewallPolicyTableID');
	var rowCount = getTableRowCount('firewallPolicyTableID');
	for (var i = 1; i < rowCount; i++) {
		var firewallPolicyRemove = '<a href="javascript:doFirewallPolicyRowRemove(' + i + ')" ' +
    						        'class="B_SSM_S02_Page_Form_AccountMailsGroup_Href_RemoveLink"> ' +	        						
    						        '<span style="font-weight: bold; font-size: 15px; color: black;">X</span> ' +
    						       	'</a> ';
		table.rows[i].cells[FIELDPOS_RACKEQUIPMENT_REMOVELINK].innerHTML = firewallPolicyRemove;
	}
}

/**
 * Do remove Firewall Policy row
 */
function doFirewallPolicyRowRemove(rowNo) {
	var MsgBox = new messageBox();
	if (MsgBox.POPDEL(confirmMessage) == MsgBox.YES_CONFIRM) {
		removeTableRow('firewallPolicyTableID', rowNo);			
		updateFirewallPolicyRemoveLinks();
		updateFirewallPolicyNoColValues();
	}
}

/**
 * Get DNS Zone Table Component Position Array
 */					    
function getDNSZoneTableComponentPositionArray() {
	return [
			  0,
			  1, 
		      2,
		      3,
		      4
		    ]; 
}


/**
 * Get New DNS Zone Component Value Array
 */
function getNewDNSZoneComponentValueArray() {
	var rowIndex = getTableRowCount('dNSZoneTableID');	
	var dNSZoneRemove = '<a href="javascript:doDNSZoneRowRemove(' + rowIndex + ')" ' +
					     'class="B_SSM_S02_Page_Form_AccountMailsGroup_Href_RemoveLink"> ' +	        						
					     '<span style="font-weight: bold; font-size: 15px; color: black;">X</span> ' +
					     '</a> ';					
    var dNSZoneDn = '<input type="text" maxlength="100" name="dNSZoneRecordDomainNames" class="B_SSM_S02_Page_Form_TextBox_MailAccount"/>';
	    dNSZoneDn += '<input type="text" name="dNSZoneRecordIDs" style="display: none;" class="B_SSM_S02_Page_Form_TextBox_MailAccount"/>';
	var dNSZoneType = '<input type="text" maxlength="100" name="dNSZoneRecordTypes" class="B_SSM_S02_Page_Form_TextBox_MailAccount"/>';
    var dNSZoneIPAddress = '<input type="text" maxlength="40" name="dNSZoneRecordIPAddresses" class="B_SSM_S02_Page_Form_TextBox_MailAccount"/>';
    var dNSZoneWeight = '<input type="text" maxlength="10" name="dNSZoneRecordWeights" class="B_SSM_S02_Page_Form_TextBox_MailAccount"/>';   	      
	return [
			  dNSZoneRemove, 
		      dNSZoneDn,
		   	  dNSZoneType,
		   	  dNSZoneIPAddress,
		   	  dNSZoneWeight
		    ];
}															

/**
 * Update DNS Zone Remove Links
 */
function updateDNSZoneRemoveLinks() {
	var table = document.getElementById('dNSZoneTableID');
	var rowCount = getTableRowCount('dNSZoneTableID');
	for (var i = 1; i < rowCount; i++) {
		var dNSZoneRemove = '<a href="javascript:doDNSZoneRowRemove(' + i + ')" ' +
						    'class="B_SSM_S02_Page_Form_AccountMailsGroup_Href_RemoveLink"> ' +	        						
					        '<span style="font-weight: bold; font-size: 15px; color: black;">X</span> ' +
					       	'</a> ';
		table.rows[i].cells[FIELDPOS_RACKEQUIPMENT_REMOVELINK].innerHTML = dNSZoneRemove;
	}
}

/**
 * Do remove DNSZone row
 */
function doDNSZoneRowRemove(rowNo) {
	var MsgBox = new messageBox();
	if (MsgBox.POPDEL(confirmMessage) == MsgBox.YES_CONFIRM) {
		removeTableRow('dNSZoneTableID', rowNo);			
		updateDNSZoneRemoveLinks();
	}
}

var cPEDisplayingConfigContentDivName = null;

/**
 * Do action on selecting cpe name
 */
function doSelectCPENameList(cPESelectElement) {
	if (cPESelectElement.selectedIndex != -1) {
		// Enable delete button
		document.getElementById('CPEDeleteBtnID').disabled = false;
		// Get cpe id and name
		var cPEDID = cPESelectElement.options[cPESelectElement.selectedIndex].value;	
		var cPEName = cPESelectElement.options[cPESelectElement.selectedIndex].innerHTML;	
		var id = isNumber(cPEDID) ? cPEDID : '-' + cPEName.substring(4);
		// Visible real cpe config content div				
		var cPEConfigContentDiv = document.getElementById('CPEConfigContent' + id);
		if (cPEConfigContentDiv) {
			// Hide null cpe config content div
			var cPEConfigNullContentDiv = document.getElementById('DisableCPEConfigContent');
			cPEConfigNullContentDiv.style.visibility = "hidden";
			cPEConfigNullContentDiv.style.disabled = "true";
			cPEConfigNullContentDiv.style.height = "0px";
			// Hide previous displaying cpe config content div
			var cPEDisplayingConfigContentDiv = document.getElementById(cPEDisplayingConfigContentDivName);
			if (cPEDisplayingConfigContentDiv) {					
				cPEDisplayingConfigContentDiv.style.visibility = "hidden";
				cPEDisplayingConfigContentDiv.style.height = "0px";					
			}
			// Display selected cpe config
			cPEConfigContentDiv.style.visibility = "visible";
			cPEConfigContentDiv.style.display = "block";
			cPEConfigContentDiv.style.height = "100%";
			cPEDisplayingConfigContentDivName = cPEConfigContentDiv.id;
		}
	}		
}

var cPENameIndex = 0;

/**
 * Do cpe config content with selectedIndex
 */
function getCPEConfigContent(selectedIndex) {
	var cPESelectElement = document.getElementById('CPEConfigurationNameSelectID');
	if (cPESelectElement) {
		var selectedCPEName = cPESelectElement[selectedIndex].innerHTML;
		var selectedCPEDID = cPESelectElement[selectedIndex].value;
		var selectedCPENameIndex = Number(selectedCPEName.substring(4));									
		try {
			if (isNumber(selectedCPEDID)) {
				selectedCPEDID = Number(selectedCPEDID);
			}
			else {
				selectedCPEDID = -1;
			}
		} catch (ex) {
			selectedCPEDID = -1;
		}
		var selectedCPENameIndex = Number(selectedCPEName.substring(4));
		var id = selectedCPEDID == -1?
				  '-' + selectedCPENameIndex:
				  '' + selectedCPEDID;
		var selectedCPEConfigContentDiv = document.getElementById('CPEConfigContent' + id);	
		return selectedCPEConfigContentDiv;
	}
	return null;		
}

/**
 * Set hidden cpe config content except for content with selectedIndex
 */
function setHiddenCPEConfigContentsExceptFor(selectedIndex) {
	var cPESelectElement = document.getElementById('CPEConfigurationNameSelectID');
	if (cPESelectElement) {
		for ( var i = 0; i < cPESelectElement.options.length; i++) {
			var cpeConfigContent = getCPEConfigContent(i);
			if (selectedIndex != i && cpeConfigContent) {
				cpeConfigContent.style.visibility = "hidden";
				cpeConfigContent.style.height = "0px";
			} 
		}
	}		
}

/**
 * Set visible cpe config content except for content with selectedIndex
 */
function setVisibleCPEConfigContent(selectedIndex) {
	var cPESelectElement = document.getElementById('CPEConfigurationNameSelectID');
	if (cPESelectElement) {
		for ( var i = 0; i < cPESelectElement.options.length; i++) {
			var cpeConfigContent = getCPEConfigContent(i);
			if (selectedIndex == i && cpeConfigContent) {
				cpeConfigContent.style.visibility = "visible";
				cpeConfigContent.style.display = "block";
				cpeConfigContent.style.height = "100%";
				cPEDisplayingConfigContentDivName = cpeConfigContent.id;
			} else {
				if (cpeConfigContent) {
					cpeConfigContent.style.visibility = "hidden";
					cpeConfigContent.style.height = "0px";
				}
			}
		}
	}		
}

/**
 * Set cpe selected index
 */
function setCPESelectedIndex(index) {
	var cPESelectElement = document.getElementById('CPEConfigurationNameSelectID');
	cPESelectElement.selectedIndex = index;	
}

/**
 * Do action on adding cpe name
 */
function doAddCPEConfig() {
	var cPESelectElement = document.getElementById('CPEConfigurationNameSelectID');
	if (cPESelectElement) {
		var maxCPENameIndex = 0;
		var lastCPEName = cPESelectElement.options.length > 0 ?
						  cPESelectElement[cPESelectElement.options.length - 1]:
						  null;
		if (lastCPEName) {
			lastCPEName = lastCPEName.innerHTML;								
			try {
				maxCPENameIndex = Number(lastCPEName.substring(4));
			} catch (ex) {
				maxCPENameIndex = 0;	
			}
		}
		cPENameIndex = maxCPENameIndex + 1;
		
		// Add cpe name into selectbox 
		var cPEName = 'CPE ' + cPENameIndex;
		var newCPENameOption = document.createElement('option');
		newCPENameOption.text = cPEName;
		newCPENameOption.value = '';					
		cPESelectElement.add(newCPENameOption);	
		
		// Set selected cpe name
		cPESelectElement.selectedIndex = cPESelectElement.options.length - 1;
		
		// Add cpe name and cpe dID into info data div 
		var cPEInfoDataDiv = document.getElementById('CPEConfigurationInfoDataID');
		if (cPEInfoDataDiv) {
			// Add cpe name					
			var newCPENameText = document.createElement('input');	
			newCPENameText.value = cPEName;
			newCPENameText.name = 'CPEConfigurationNames';
			newCPENameText.id = 'CPEConfigurationName' + cPENameIndex; 
			newCPENameText.style.display = "none";
			cPEInfoDataDiv.appendChild(newCPENameText);
			
			// Add cpe dID
			var newCPEDIDText = document.createElement('input');	
			newCPEDIDText.value = '';
			newCPEDIDText.name = 'CPEConfigurationDIDs';
			newCPEDIDText.id = 'CPEConfigurationDID' + cPENameIndex; 
			newCPEDIDText.style.display = "none";
			cPEInfoDataDiv.appendChild(newCPEDIDText);
			
			// Hide disable cpe config content div
			var cPEConfigDisableContentDiv = document.getElementById('DisableCPEConfigContent');
			cPEConfigDisableContentDiv.style.visibility = "hidden";
			cPEConfigDisableContentDiv.style.disabled = "true";
			cPEConfigDisableContentDiv.style.height = "0px";
			
			// Set hidden cpe config contents
			setHiddenCPEConfigContentsExceptFor(cPESelectElement.options.length - 1);
			
			// Add cpe config
			var cPEConfigNullContentDiv = document.getElementById('NullCPEConfigContent');
			var newCPEConfigNullContentDiv = cPEConfigNullContentDiv.cloneNode(true);
			newCPEConfigNullContentDiv.id = "CPEConfigContent-" + cPENameIndex;
			newCPEConfigNullContentDiv.style.visibility = "visible";
			newCPEConfigNullContentDiv.style.display = "block";
			newCPEConfigNullContentDiv.style.disabled = "false";
			newCPEConfigNullContentDiv.style.height = "100%";
			cPEDisplayingConfigContentDivName = newCPEConfigNullContentDiv.id;
			var cPEConfigContentDiv = document.getElementById('CPEConfigContent');
			cPEConfigContentDiv.appendChild(newCPEConfigNullContentDiv);
			// Enable delete button
			document.getElementById('CPEDeleteBtnID').disabled = false;
		}
	}
}

/**
 * Do action on deleting cpe name
 */
function doDeleteCPEConfig() {
	var cPESelectElement = document.getElementById('CPEConfigurationNameSelectID');
	if (cPESelectElement) {		
		if (cPESelectElement.selectedIndex != -1) {
			var MsgBox = new messageBox();
			if (MsgBox.POPDEL(confirmMessage) == MsgBox.YES_CONFIRM) {
				var selectedIndex = cPESelectElement.selectedIndex;	
				var selectedCPEName = cPESelectElement[cPESelectElement.selectedIndex].innerHTML;
				var selectedCPEDID = cPESelectElement[cPESelectElement.selectedIndex].value;
				var selectedCPENameIndex = Number(selectedCPEName.substring(4));					
				cPESelectElement.remove(cPESelectElement.selectedIndex);	
				// Remove in cpe info data div
				var cPENameText = document.getElementById('CPEConfigurationName' + selectedCPENameIndex);					
				var cPEDIDText = document.getElementById('CPEConfigurationDID' + selectedCPENameIndex);
				var cPEInfoDataDiv = document.getElementById('CPEConfigurationInfoDataID');
				if (cPEInfoDataDiv) {
					cPEInfoDataDiv.removeChild(cPENameText);
					cPEInfoDataDiv.removeChild(cPEDIDText);
				}
				// Remove cpe config
				var cPEConfigContentDiv = document.getElementById('CPEConfigContent');
				try {
					if (isNumber(selectedCPEDID)) {
						selectedCPEDID = Number(selectedCPEDID);
					}
					else {
						selectedCPEDID = -1;
					}
				} catch (ex) {
					selectedCPEDID = -1;
				}
				var selectedCPENameIndex = Number(selectedCPEName.substring(4));
				var id = selectedCPEDID == -1?
						  '-' + selectedCPENameIndex:
						  '' + selectedCPEDID;
				var selectedCPEConfigContentDiv = document.getElementById('CPEConfigContent' + id);
				cPEConfigContentDiv.removeChild(selectedCPEConfigContentDiv);
				// Display next cpe config
				var nextSelectedIndex = selectedIndex < cPESelectElement.options.length - 1?
										selectedIndex:
										cPESelectElement.options.length - 1;
				setCPESelectedIndex(nextSelectedIndex);	
				setVisibleCPEConfigContent(nextSelectedIndex);
				// Display disable cpe config with empty cpe selectbox
				if (cPESelectElement.options.length == 0) {
					var cPEConfigDisableContentDiv = document.getElementById('DisableCPEConfigContent');
					cPEConfigDisableContentDiv.style.visibility = "visible";
					cPEConfigDisableContentDiv.style.display = "block";
					cPEConfigDisableContentDiv.style.disabled = "false";
					cPEConfigDisableContentDiv.style.height = "100%";
					// Disable delete button
					document.getElementById('CPEDeleteBtnID').disabled = true;
				}
			}
		}	
	}	
}

/**
 * Set value of cpe managed is used in specific container
 */
function setCPEConfigurationManagedIsUsedsValue(container) {
	var event = window.event;
	var triggerElement = event.srcElement;
	var triggerElementValue = triggerElement.value;
	if (triggerElement.type == "radio") {
		triggerElement.checked = !triggerElement.checked;
		var elements = container.childNodes;
		if (elements) {
			for (var i = 0; i < elements.length; i++) {
				var element = elements[i];
				if (element) {
					var type = element.type;
					if (type == "hidden") {
						element.value = triggerElementValue;
					}
					if (type == "radio" && element != triggerElement) {
						if (triggerElement.checked) {
							element.checked = false;	
						}
					}
				}
			}
		}
	}
}

/**
 * Set status message
 */
function setStatusMessage(statusMsg, msgType) {			
	var statusArea = document.getElementById("statusMessagePanelID");
	// Error msg type
	if (msgType == "error") {
		statusArea.innerHTML = '<div class="Error">' + statusMsg + '</div>';
	}
	// Info msg type
	if (msgType == "info") {
		statusArea.innerHTML = '<div class="Success">' + statusMsg + '</div>';
	}
}

/**
 * Validate general tab
 */
function validateB_SSM_S02e_GeneralTab() {
	return true;
}

/**
 * Validate address tab
 */
function validateB_SSM_S02e_MailAddressTab() {
	//auto Update Quantity check
//	var chkAutoMailAccID = document.getElementById("autoMailAccID");
//	var chkAutoMailBoxQtyID = document.getElementById("autoMailBoxQtyID");
//	var chkAutoVirusScanID = document.getElementById("autoVirusScanID");
//	var chkAutoAntiSpam = document.getElementById("autoAntiSpam");
//	var chkAutoJunkManagement = document.getElementById("autoJunkManagement");
//	var mailAccountValue = ""; 
//	var additionalOptionValue = "";
//	var mailBoxQtyValue = "";
//	var optionVirusScanValue = "";
//	var optionAntiSpamValue = "";
//	var optionJunkManagementValue = "";
//	if (chkAutoMailAccID.checked) {
//		mailAccountValue = document.getElementById("mailAccountID").value;
//		additionalOptionValue = document.getElementById("additionalOptionID").value;
//	}
//	if (chkAutoMailBoxQtyID.checked) {
//		mailBoxQtyValue = document.getElementById("mailBoxQtyID").value;
//	}
//	if (chkAutoVirusScanID.checked) {
//		optionVirusScanValue = document.getElementById("optionVirusScanID").value;
//	}
//	if (chkAutoAntiSpam.checked) {
//		optionAntiSpamValue = document.getElementById("antiSpamID").value;
//	}
//	if (chkAutoJunkManagement.checked) {
//		optionJunkManagementValue = document.getElementById("junkManagementID").value;
//	}
//	// comboBox value array
//	var selectedValueArr = new Array();
//	selectedValueArr[0] = mailAccountValue;
//	selectedValueArr[1] = additionalOptionValue;
//	selectedValueArr[2] = mailBoxQtyValue;
//	selectedValueArr[3] = optionVirusScanValue;
//	selectedValueArr[4] = optionAntiSpamValue;
//	selectedValueArr[5] = optionJunkManagementValue;
//	// has duplicate data
//	var dupIndexArr = checkHasDuplData(selectedValueArr);
//	if(dupIndexArr!=null && 0 < dupIndexArr.length){
//		checkResult = false;
//		for (var i = 0; i < dupIndexArr.length; i++) {
//			if (dupIndexArr[i]==0) {
//				errorMsgTxt = errorMsgTxt + mailAccountDupMessage + "<br/>";
//			} else if (dupIndexArr[i]==1) {
//				errorMsgTxt = errorMsgTxt + addtionalOptionDupMessage + "<br/>";
//			} else if (dupIndexArr[i]==2) {
//				errorMsgTxt = errorMsgTxt + mailboxQtyDupMessage + "<br/>";
//			} else if (dupIndexArr[i]==3) {
//				errorMsgTxt = errorMsgTxt + virusScanDupMessage + "<br/>";
//			} else if (dupIndexArr[i]==4) {
//				errorMsgTxt = errorMsgTxt + antiSpanDupMessage + "<br/>";
//			} else if (dupIndexArr[i]==5) {
//				errorMsgTxt = errorMsgTxt + junkMgmtDupMessage + "<br/>";
//			}
//		}
//	}
	
	var errorMsgTxt = "";
	var checkResult = true;
	
	//Access Account check
	var accessAccountCheckFlg = document.getElementById('accessAccountCheckFlg');
	if(accessAccountCheckFlg!=null && accessAccountCheckFlg!=undefined) {
		var accessAccountID = document.getElementById('accessAccountID').value;
		if (accessAccountID=="") {
			errorMsgTxt = errorMsgTxt + accessAccountEmptyErrorMessage + "<br/>";
			checkResult = false;
		}
	}
	
	//Access Password check
	var accessPasswordCheckFlg = document.getElementById('accessPasswordCheckFlg');
	if(accessPasswordCheckFlg!=null && accessPasswordCheckFlg!=undefined) {
		var accessPasswordID = document.getElementById('accessPasswordID').value;
		if (accessPasswordID=="") {
			errorMsgTxt = errorMsgTxt + accessPasswordEmptyErrorMessage + "<br/>";
			checkResult = false;
		}
	}
	
	//Mail Account check
	var table = document.getElementById('mailAccountsGroupTableID');
	var rowCount = getTableRowCount('mailAccountsGroupTableID');
	for (var i = 2; i < rowCount - 1; i++) {
		var mailAccount = table.rows[i].cells[FIELDPOS_MAILACCOUNT].childNodes[0].value.replace(/\s/g,"");
		var mailPassword = table.rows[i].cells[FIELDPOS_MAILPASSWORD].childNodes[0].value.replace(/\s/g,"");
		var popAccount = table.rows[i].cells[FIELDPOS_POPACCOUNT].childNodes[0].value.replace(/\s/g,"");
		if(mailAccount==""&&mailPassword==""&&popAccount=="") {
			errorMsgTxt = errorMsgTxt + mailAcountEmptyErrorMessage + "<br/>";
			checkResult = false;
			break;
		}
	}
	
	if(!checkResult) {
		setStatusMessage(errorMsgTxt, "error");
		return false;
	}
	
	return true;	
}

/**
 * has duplicate data
 * @param arr
 * @returns
 */
function checkHasDuplData(arr) {
	var dupIndex = 0;
	var dupIndexArr = new Array();
	for(var i=0;i<arr.length;i++){ 
		var value1 = arr[i];
		if(value1!="") {
			for(var j=0;j<arr.length;j++){
				if(i!=j) {
					var value2 = arr[j];
					if(value2!="") {
						if(value1==value2) {
							if(!valueIsInArray(dupIndexArr, i)) {
								dupIndexArr[dupIndex] = i;
								dupIndex = dupIndex + 1;
							}
						}
					}
				}
			}
		}
	}
	return dupIndexArr;
}

function valueIsInArray(dupIndexArr, value) {
	if(dupIndexArr!=null && 0 < dupIndexArr.length){
		for (var i = 0; i < dupIndexArr.length; i++) {
			if(dupIndexArr[i]==value) {
				return true;
			}
		}
	}
	return false;
}

/**
 * Validate rack equipment
 */
function validateB_SSM_S02e_RackEquipmentTab() {
	return true;
} 

/**
 * Validate it contact tab
 */
function validateB_SSM_S02e_ITContactTab() {
	return true;
}

/**
 * Validate server info tab
 */
function validateB_SSM_S02e_ServerInfoTab() {
	return true;	
}

/**
 * Validate firewall tab
 */
function validateB_SSM_S02e_FirewallTab() {
	return true;
}

/**
 * Validate cpe config tab
 */
function validateB_SSM_S02e_CPEConfigTab() {
	return true;
} 

/**
 * Validate dns zone tab
 */
function validateB_SSM_S02e_DNSZoneTab() {
	return true;
} 

/**
 * Validate teamwork tab
 */
function validateB_SSM_S02e_TeamworkTab() {
	return true;
}		

/**
 * Validate B_SSM_S02 form
 */
function validateB_SSM_S02eForm() {			
	return validateB_SSM_S02e_GeneralTab() && 
		    validateB_SSM_S02e_MailAddressTab() &&
		    validateB_SSM_S02e_RackEquipmentTab() &&
		    validateB_SSM_S02e_ITContactTab() &&
		    validateB_SSM_S02e_ServerInfoTab() &&
		    validateB_SSM_S02e_FirewallTab() &&
		    validateB_SSM_S02e_CPEConfigTab() &&
		    validateB_SSM_S02e_DNSZoneTab() &&
		    validateB_SSM_S02e_TeamworkTab();
}
	
/**
 * Check all box mail accounts
 */
function checkAllMailAccounts(){
	var isCheckAll = document.getElementById('checkAllMailAccountsID').checked;
	var table = document.getElementById('mailAccountsGroupTableID');
	var rowCount = getTableRowCount('mailAccountsGroupTableID');
	for (var i = 2; i < rowCount - 1; i++) {
		var row = table.rows[i];
		if (row == null) {
			continue;
		}
		var cell = row.cells[FIELDPOS_MAILACCOUNT_CHECKBOX];
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
 * Check all box mail accounts deleted
 */
function checkAllMailAccountsDeleted(){
	var isCheckAll = document.getElementById('checkAllMailAccountsDeletedID').checked;
	var table = document.getElementById('mailAccountsDeletedGroupTableID');
	var rowCount = getTableRowCount('mailAccountsDeletedGroupTableID');
	for (var i = 1; i < rowCount; i++) {
		var row = table.rows[i];
		if (row == null) {
			continue;
		}
		var cell = row.cells[FIELDPOS_MAILACCOUNT_CHECKBOX];
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
 * Do action on selecting cpe name
 */
function doSelectViewCPENameList(cPESelectElement) {
	var cPEDID = cPESelectElement.options[cPESelectElement.selectedIndex].value;	
	if (cPEDID != null) {
		// Visible real cpe config content div				
		var cPEConfigContentDiv = document.getElementById('CPEConfigContent' + cPEDID);
		if (cPEConfigContentDiv) {
			// Hide null cpe config content div
			var cPEConfigNullContentDiv = document.getElementById('NullCPEConfigContent');
			cPEConfigNullContentDiv.style.visibility = "hidden";
			cPEConfigNullContentDiv.style.height = "0px";	
			// Hide previous displaying cpe config content div
			var cPEDisplayingConfigContentDiv = document.getElementById(cPEConfigDisplayingContentDivName);
			if (cPEDisplayingConfigContentDiv) {					
				cPEDisplayingConfigContentDiv.style.visibility = "hidden";
				cPEDisplayingConfigContentDiv.style.height = "0px";					
			}
			// Display expected cpe config content div
			cPEConfigContentDiv.style.visibility = "visible";
			cPEConfigContentDiv.style.display = "block";
			cPEConfigContentDiv.style.height = "100%";	
			cPEConfigDisplayingContentDivName = cPEConfigContentDiv.id;
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
 * Set mac id value
 */
function setMacIDValue(modemNoSelect) {
	if (modemNoSelect) {
		var selectedIndex = modemNoSelect.selectedIndex;	
		var selectedModemNo = modemNoSelect[selectedIndex].value;
		var macIDTxt = document.getElementById('macID');
		macIDTxt.value = macInfo[selectedModemNo]; 
	}
}

/**
 * Is print mail selected?
 */
function isPrintMailSelected(tableID, startIndex) {
	var table = document.getElementById(tableID);
	if (table) {
		var rowCount = table.rows.length;
		for (var i = startIndex; i < rowCount; i++) {
			var row = table.rows[i];
			if (row == null) {
				continue;
			}
			var cell = row.cells[FIELDPOS_MAILACCOUNT_CHECKBOX];
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
	}
	return false;
}

/**
 * Check print mail is selected?
 */
function checkPrintMailSelected() {
	 var processMode = document.getElementById('processModeField').value;
	 // Exit mode
	 if (processMode == 3) {
		 return false; 
	 }
	 // Print delete mail mode
	 if (processMode == 6) {
		var isChecked = isPrintMailSelected('mailAccountsGroupTableID', 2) ||
					     isPrintMailSelected('mailAccountsDeletedGroupTableID', 1); 
		var MsgBox = new messageBox();
		promptErrorMessage = promptErrorMessage.replace(/null*/g, "Email Address");	
		if (!isChecked) {
			MsgBox.POPALT(promptErrorMessage);
			return false;
		}
		showLoadingTipWindow();
		return true;
	}
}

/**
 * Do exit action
 * @param isPopUp
 */
function doExitAction(isPopUp) {
	var MsgBox = new messageBox();
	if (MsgBox.POPDEL(confirmExitMessage) == MsgBox.YES_CONFIRM) {
		if (isPopUp) {
			setTextField('processModeField', 3);
			self.close();
		} else {
			setTextField('processModeField', 3);
			document.forms[0].submit();
		}
	}
}
/**
 * Do exit action
 * @param isPopUp
 */
function doExitToView(url) {
	var MsgBox = new messageBox();
	if (MsgBox.POPDEL(confirmExitMessage) == MsgBox.YES_CONFIRM) {
       window.location = url;
	}
}

/**
 * Do view exit action
 * @param isPopUp
 */
function doViewExitAction(isPopUp) {
	if (isPopUp) {
		setTextField('processModeField', 3);
		self.close();
	} else {
		setTextField('processModeField', 3);
		document.forms[0].submit();
	}
}


function textboxBlur(ctr, relationID)
{
	if ($(ctr).css("color") != "silver")
	{
		$("#"+relationID).val($(ctr).val());
		if ($(ctr).val() == "")
		{
			$(ctr).css("color", "silver");
			$(ctr).val($(ctr).attr("alt"));
		}
	}
}


function textboxFocus(ctr, relationID)
{
	if ($(ctr).css("color") == "silver")
	{
		$(ctr).css("color", "black");
		$(ctr).val("");
	}
}

function changeOnlyDecNumbers(element, defaultValue){
	if(isNumeric(element.value)){
		element.value = element.value;
	} else {
		element.value = defaultValue;
	}
}

function  isNumeric(value) {
	var reg = new RegExp(/^[\+\-]?\d*?\.?\d*?$/);
	return reg.test(value);
}

/**
 * only input Dec Number
 * @param evt
 * @returns {Boolean}
 */
function onlyDecNumbers(evt) {
	var e = evt;
	if(window.event){ // IE
		var charCode = e.keyCode;
	} else if (e.which) { // Safari 4, Firefox 3.0.4
		var charCode = e.which;
	}
	if (charCode > 31 && (charCode < 48 || charCode > 57)){
		if(charCode == 46){// available for . 
			return true;
		}else{
			return false;
		}
	}else{
		return true;
	}
}

function checkMailContent() {

    var textarea = $("textarea[name='mailAccountNames']");

    var subscriptionID = $("#subscriptionID").val();
    
    for (var index = 0; index < textarea.length; ++index) {

        var value1 = textarea[index].value;

        for (var i = index + 1; i < textarea.length; ++i) {

            var value2 = textarea[i].value;

            if (value1 == value2 && value1 != "" && value2 != "") {
                var message = "Not able to register mail account ("+value2+") because mail account"+
                        " already in use subscription info:"+subscriptionID;
                setStatusMessage(message, "error");
                return false;
            }
        }
    }
    
    var mailAccount = "";

    for (var index = 0; index < textarea.length; index++) {
        mailAccount = mailAccount+"&mailAccount="+textarea[index].value;
     }    var isOk = true;
    if(mailAccount!=""){
        $.ajax({
                type: "POST",
                url: "B_SSM_S02_checkMailAccountDuplicationAction.do?"+mailAccount.substr(1),
                data:{
                    "subscriptionID":subscriptionID
                },
                async:false,
                success:function(data){
                    if(data.resultFlg==='1'){
                        isOk = false;
                        var errorMsgTxt = "Not able to register mail account ("+data.mailAccount+") because mail account"+
                        " already in use subscription info:"+data.subscriptionID;
                        setStatusMessage(errorMsgTxt, "error");
                    }else{
                        isOk=true;
                    }
                },
                error: function(XMLHttpRequest, textStatus, errorThrown){
        
                }
            });
    }

    return isOk;
}
