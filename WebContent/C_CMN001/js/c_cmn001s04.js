var isLoaded = false;
var menu_status = new Array();
//generate sub menu
function setSubMenu(id_module,parent_module,sub_name,path)
{ 
	var ret ="";
	if(parent_module==id_module)
	{ 
		ret = '<a class="subMenu" onclick="gotoPage(\'' + path + '\')">- ' + sub_name + '</a>'; 
		var x = document.createElement('div'); 
		x.innerHTML=ret;
		document.getElementById(id_module).appendChild(x); 
	}  
}
function showHide(theid){
	if (document.getElementById){
		var switch_id = document.getElementById(theid);
	
		if (menu_status[theid] != "show"){
			switch_id.className = "show";
			menu_status[theid] = "show";
		} else {
			switch_id.className = "hide";
			menu_status[theid] = "hide";
		}
	}
}

function gotoPage(action) {
	var iframe = parent.frame_main;
	iframe.location = action;
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
    	document.getElementById("mainMenuPanel").className = "hide";
    } else {
    	document.getElementById("mainMenuPanel").className = "show";
    }
    
}

function initPage(){
	isLoaded = true;
    if(parent.frame_main.isLoaded){
    	showHideMenu();
    }
}