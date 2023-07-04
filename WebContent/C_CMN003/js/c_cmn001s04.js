var isLoaded = false;
var menu_status = new Array();
var locationPath = "";
var root = "";

function showHide(obj) {
	var cl = document.getElementById(obj).className;
	if(cl == 'hide') {
		document.getElementById(obj).className = 'show';
	}
	else {
		document.getElementById(obj).className = 'hide';
	}
}

function gotoPage(action) {
	this.root="menu";
	var iframe = parent.frame_main;
	iframe.location = action;
	parent.frame_top.location.reload(true);
}

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

function showHideMenu(){
    var functionTitleId = "";

    functionTitleId = getFunctionTitleId();   

    if(functionTitleId == ""){
        functionTitleId = "c_cmn001s01";
    }
    
    if (functionTitleId == "c_cmn001s01") {
    	locationPath = "";
    	//document.getElementById("mainMenuPanel").className = "hide";
    } else {
    	//document.getElementById("mainMenuPanel").className = "show";
    }
    
}

function initPage(){
	isLoaded = true;
    if(parent.frame_main.isLoaded){
    	showHideMenu();
    	locationPath = parent.frame_main.getMenuPath();
    }
}