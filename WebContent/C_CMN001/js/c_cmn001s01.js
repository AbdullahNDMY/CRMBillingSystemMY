function initPage(){
	if (window.dialogArguments == null || window.dialogArguments == undefined) {
		if(window.opener == null || window.opener == undefined) {
			parent.showMenu(false);
			parent.frame_top.location.reload(true);
			parent.frame_menu.location.reload(true);
		} else {
			sessionTimeOutShow();
		}
	} else {
		sessionTimeOutShow();
	}
}

function sessionTimeOutShow(){
	var contentPanel = document.getElementById("contentPanel");
	var errrorPanel = document.getElementById("errrorPanel");
	var c_cmn001s01 = document.getElementById("c_cmn001s01");
	contentPanel.style.display = "none";
	errrorPanel.style.display = "inline";
	c_cmn001s01.className = "popClass";
}

function closeWindow(){
	self.close();
	//iframe.location="/billingsystem/C_CMN001/C_CMN001S01SCR.do";
}

function login(){
	document.forms[0].submit();
}

function clearData(){
	document.getElementById("id_user").value = "";
	document.getElementById("password").value = "";
}