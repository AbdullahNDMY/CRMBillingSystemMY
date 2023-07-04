var priHigh, priMedium, priNormal;
var confirmDelete;
var numberNew = "";

function initPage(){ 
	parent.frame_top.location.reload(true);
	var menuPath = document.getElementById("menuPath").value;
	
	if (parent.frame_menu.locationPath != menuPath){
		parent.frame_menu.location = menuPath;
		parent.frame_menu.locationPath = menuPath;
	}
}

function getMenuPath(){
	var menuPath = document.getElementById("menuPath").value;
	return menuPath;
}
function sendNewMsg(newmsg, txtNewNotif)
{ 
	if(newmsg>0)
	{
		numberNew = newmsg + " " + txtNewNotif;
		parent.frame_top.location.reload(true);
	}
}
function init(hi,me,no)
{
	priHigh = hi;
	priMedium = me;
	priNormal = no;
}
function getLevel(level)
{
	switch (level) {
	case 1:
		return priHigh;
	case 2:
		return priMedium;
	default:
		return priNormal;
	}
}
function clickLinkToEdit(id_msg){ 
	document.getElementById("id_msg").value = id_msg;
	var button=	document.getElementById("forward_edit");
	button.click();
}

function clickLinkToDelete(id_msg,msg_type){  
	var MsgBox = new messageBox();
	if (MsgBox.POPDEL() == MsgBox.YES_CONFIRM) {
		document.getElementById("id_msg").value = id_msg;
		document.getElementById("msg_type").value = msg_type;
		var button = document.getElementById("forward_delete");
		button.click();
	}
}

function clickLinkToEditRefno(id_msg, id_ref){ 
	document.getElementById("id_msg").value = id_msg;
	document.getElementById("id_ref").value = id_ref;
	var button=	document.getElementById("forward_ref");
	button.click();
}

function onClickId_ref(id_screen, id_ref) {
	if(id_screen == 'QCS') {
		window.location = server_path + "/Q_QCS/Q_QCSR02BLogic.do?id_ref_s01=" + id_ref;
	} else {
		window.location = server_path + "/BIF/B_BIFS01_01BL.do?idRef=" + id_ref;
	}
}

function clickLinkToCreateNew(){ 
	var button=	document.getElementById("forward_new");
	button.click();
}

/**
 * Click All Notification CheckBox
 * @returns
 */
function checkAllBox(self,checktype){
	if(self.checked){
    	$("input[type^='checkbox'][class$=\'"+checktype+"\']").each(function () {
    		$(this).attr("checked",true);
    	});
       
    }else{
        $("input[type^='checkbox'][class$=\'"+checktype+"\']").attr("checked",false);
    }
}

/**
 * Delete all checked elements
 * @returns
 */
function clickNotifDel(type){
	var noRecordMsg= $("#ERR1SC105").val().replace('{0}', "No record(s) selected to delete");
	//init checkBox length
	var checkSize=0;
	//get the collection of checked elements
	var checkBoxList;
	//Delete type="NOTICE"
	if(type=="NOTICE"){
		checkSize =  $("input[type^='checkbox'][class$='checkNotif']:checked").length;
		checkBoxList=  $("input[type^='checkbox'][class$='checkNotif']:checked");
		document.getElementById("msg_type").value = 'NOTICE';
	}
	//Delete type='TASK'
	if(type=="TASK"){
		checkSize =  $("input[type^='checkbox'][class$='checkTask']:checked").length;
		checkBoxList= $("input[type^='checkbox'][class$='checkTask']:checked");
		document.getElementById("msg_type").value = 'TASK';
	}
	//Delete type='SENT'
	if(type=="SENT"){
		checkSize =  $("input[type^='checkbox'][class$='checkSent']:checked").length;
		checkBoxList=  $("input[type^='checkbox'][class$='checkSent']:checked");
		document.getElementById("msg_type").value = 'SENT';
	}
	//Gets the collection of all checked elements's id_msg
	var arrayMsgs="";
	//prompt delete confirmation message ERR4SC002,and excute to delete selected record(s)
	if(checkSize>0){
		var MsgBox = new messageBox();
		if (MsgBox.POPDEL() == MsgBox.YES_CONFIRM) {
			//foreach  the collection of checked elements
			checkBoxList.each(function (index) {
				var id_msg;
				//get NOTICE's id_msg
				if(type=="NOTICE"){
					id_msg=$(this).siblings(".notifiId").val();
				}
				//get TASK's id_msg
				if(type=="TASK"){
					id_msg=$(this).siblings(".taskId").val();
				}
				//get SENT's id_msg
				if(type=="SENT"){
					id_msg=$(this).siblings(".sentId").val();
				}
				arrayMsgs=arrayMsgs+id_msg+"-";
	    	});
			//execute to 'delete'
			document.getElementById("id_msg").value = arrayMsgs;
			var button = document.getElementById("forward_delete");
			button.click();
		}
	}
	else{
		//if No record(s) selected, prompt error message [ERR1SC105]{0}: No record(s) selected to delete
		var MsgBox = new messageBox();
		MsgBox.POPALT(noRecordMsg);
		return;
	}
}