
var isLoaded = false;

// TOP title
var functionTitle = new Object();
functionTitle['c_cmn001s01'] = "LOGIN";
functionTitle['c_cmn002s01'] = "DASHBOARD";
functionTitle['cmn04'] = "ALERT & NOTIFICATION";
functionTitle['bif'] = "BILLING MANAGEMENT SYSTEM";
functionTitle['qms01'] = "QUOTATION MANAGEMENT SYSTEM";
functionTitle['m_wlms01'] = "WORKFLOW LIST MAINTENANCE";
functionTitle['m_cdms01'] = "CODE MAINTENANCE";
functionTitle['m_alts01'] = "ALERT & NOTIFICATION";
functionTitle['m_atms01'] = "AUTHORITY TRANSFER MAINTENANCE";
functionTitle['m_csts01'] = "CUSTOMER MAINTENANCE";
functionTitle['m_csts02'] = "CUSTOMER MAINTENANCE";
functionTitle['m_curs01'] = "CURRENCY MAINTENANCE";
functionTitle['m_curs02'] = "CURRENCY MAINTENANCE";
functionTitle['m_svts01'] = "SERVICE MAINTENANCE";
functionTitle['m_csts01'] = "MASTER MAINTENANCE";

functionTitle['a_adts01'] = "AUDIT TRAIL";
functionTitle['a_adts02'] = "AUDIT TRAIL";
functionTitle['a_pwds01'] = "PASSWORD MAINTENANCE";
functionTitle['a_usrs01'] = "USER MAINTENANCE";
functionTitle['a_usrs02'] = "USER MAINTENANCE";
functionTitle['a_usrs03'] = "USER MAINTENANCE";
functionTitle['b'] = "BILLING MANAGEMENT SYSTEM";
functionTitle['m'] = "MASTER MAINTENANCE";
functionTitle['q'] = "QUOTATION MGMT SYSTEM";
functionTitle['a'] = "ADMINISTRATION";
functionTitle['e'] = "BATCH MAINTENANCE";
functionTitle['r'] = "REPORT";
function getFunctionTitleId(){
    if(parent.frame_main.getScreenId == undefined) return undefined;
    if(parent.frame_top == undefined) return false; 
    
    // Main frame
    var p = parent.frame_main;
    // Screen id
    var scId = p.getScreenId();
    // functionTitleId
    var functionTitleId = "";
    // Main frame document
    var p_doc = p.document;
    
    functionTitleId = scId;
    
    return functionTitleId;
}

function setFunctionTitle(){
    var functionTitleId = "";

    functionTitleId = getFunctionTitleId();

    if(functionTitleId == ""){
        functionTitleId = "c_cmn001s01";
    }
    if(document.getElementById("functionTitle") != null)
    document.getElementById("functionTitle").innerHTML = functionTitle[functionTitleId];
}

function initPage(){
    isLoaded = true;

    if(parent.frame_main.isLoaded){
        setFunctionTitle();
        setNewMessage(parent.frame_main.numberNew);
    }
    var errorTextObj = parent.frame_main.document.getElementById("errorText");
    var userSessIsNullFlag = document.getElementById("userSessIsNullFlag");
    var errorText = "";
    if(errorTextObj != null) {
    	errorText = errorTextObj.value;
    }
    if(errorText !="" && userSessIsNullFlag != null) {
    	if (document.getElementById("loginUsedTr")!=null) {
    		document.getElementById("loginUsedTr").style.display="none";
    	}
		document.getElementById("isLoginFlagAndUsrSesNull").style.display="inline";
    }
}

function showDashboard(action){
	var iframe = parent.frame_main;
	iframe.location = action;
}
function setNewMessage(numberNew){
	
	
	
		document.getElementById("newmsg").innerHTML= numberNew;
		
		if(document.getElementById("newmsg").innerText == "undefined" || document.getElementById("newmsg").innerText == undefined)
		{
			document.getElementById("newmsg").innerHTML= "";
		
		}

}