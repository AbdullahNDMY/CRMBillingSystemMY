function messageBox(serverPath) {
	
	this.serverPath = serverPath;
	this.YES_CONFIRM = "1";
	this.NO_CONFIRM = "0";
	this.CANCEL_CONFIRM = "-1";
	this.OK_CONFIRM = "1";
	this.fearture = "dialogWidth:400px; dialogHeight:120px;status = no";
	this.feartureStatement = "dialogWidth:450px; dialogHeight:140px;status = no";
	
	this.POPCAN = function (message) {
		var returnValue;
		if(!message) {
			message  = "Warning</br>";
			message += "Are you sure you want to cancel this transaction?</br>";
			message += "Click Yes to cancel, or No to abort the cancellation.";
		}
		return this.POPCFM(message);
	};
	
	this.POPDEL2 = function () {
		var message = "Click Yes to delete, or No to abort the deletion.";
		return this.POPDEL(message);
	};

	this.POPDEL = function (message) {
		var returnValue;
		if(!message) {
			message  = "Warning</br>";
			message += "Are you sure you want to delete?</br>";
			message += "Click Yes to delete, No to abort the deletion.";
		}
		return this.POPCFM(message);
	};

	this.POPEXT = function (message) {
		var returnValue;
		if(!message) {
			message  = "Warning</br>";
			message += "Are you sure you want to exit? Changes will not be saved.</br>";
			message += "Click Yes to exit, No to abort the exit.";
		}
		return this.POPCFM(message);
	};

	this.POPOBT = function (message) {
		var returnValue;
		if(!message) {
			message  = "Warning</br>";
			message += "Do you want to save the changes before you proceed.</br>";
			message += "Click Yes to Save, No to proceed.";
		}
		return this.POPCFM(message);
	};

	this.POPALT= function (message) {
		if(!serverPath)
			serverPath = "..";
		var returnValue;
		if(!message) {
			message  = "Alert</br>";
			message += "Do you want to save the changes before you proceed.</br>";
		}
		var paramObj = new Object();
		paramObj.message = message;
		var screen = window.showModalDialog(serverPath + "/COMMON/POPALT.html", paramObj, this.fearture);
		if(screen != undefined) {
			returnValue = screen;
		} else {
			returnValue = this.NO_CONFIRM;
		}
		return returnValue;
	};

	this.POPBEF = function (message) {
		if(!serverPath)
			serverPath = "..";
		var returnValue;
		if(!message) {
			message  = "Warning</br>";
			message += "Do you want to save the changes before you proceed?</br>";
			message += "Click Yes to Save, No to proceed.";
		}
		//display dialog
		var paramObj = new Object();
		paramObj.message = message;
		var screen = window.showModalDialog(serverPath + "/COMMON/POPBEF.html", paramObj, this.fearture);
		if(screen != undefined) {
			returnValue = screen;
		} else {
			returnValue = this.CANCEL_CONFIRM;
		}
		return returnValue;
	};

	this.POPRJT = function (message, id_ref) {
		if(!serverPath)
			serverPath = "..";
		var returnValue;
		if(!message) {
			message  = "Warning</br>";
			message += "Should requester revise the " + id_ref + "?</br>";
			message += "Click Yes to REJECT, No to request more attachment or minor change (except quantity, unit price and billing account).";
		}
		//display dialog
		var paramObj = new Object();
		paramObj.message = message;
		var screen = window.showModalDialog(serverPath + "/COMMON/POPRJT.html", paramObj, this.feartureStatement);
		if(screen != undefined) {
			returnValue = screen;
		} else {
			returnValue = this.CANCEL_CONFIRM;
		}
		return returnValue;
	};

	this.POPCFM = function (message) {
		if(!serverPath)
			serverPath = "..";
		var returnValue;
		var paramObj = new Object();
		paramObj.message = message;
		var screen = window.showModalDialog(serverPath + "/COMMON/POPCFM.html", paramObj, this.fearture);
		if(screen != undefined) {
			returnValue = screen;
		} else {
			returnValue = this.NO_CONFIRM;
		}
		return returnValue;
	};
	
	this.POPCPM = function (message, buttonValue1, buttonValue2) {
		if(!serverPath)
			serverPath = "..";
		var returnValue;
		var paramObj = new Object();
		paramObj.message = message;
		paramObj.buttonValue1 = buttonValue1;
		paramObj.buttonValue2 = buttonValue2;
		var screen = window.showModalDialog(serverPath + "/COMMON/POPCPM.html", paramObj, this.fearture);
		if(screen != undefined) {
			returnValue = screen;
		} else {
			returnValue = this.NO_CONFIRM;
		}
		return returnValue;
	};
	
	this.POPCFMStatement = function (message) {
		if(!serverPath)
			serverPath = "..";
		var returnValue;
		var paramObj = new Object();
		paramObj.message = message;
		var screen = window.showModalDialog(serverPath + "/COMMON/POPCFM.html", paramObj, this.feartureStatement);
		if(screen != undefined) {
			returnValue = screen;
		} else {
			returnValue = this.NO_CONFIRM;
		}
		return returnValue;
	};
	
	this.POPCFMStatement_CST = function (message) {
		if(!serverPath)
			serverPath = "..";
		var returnValue;
		var paramObj = new Object();
		paramObj.message = message;
		var screen = window.showModalDialog(serverPath + "/COMMON/POPCFM_CST.html", paramObj, this.feartureStatement);
		if(screen != undefined) {
			returnValue = screen;
		} else {
			returnValue = this.NO_CONFIRM;
		}
		return returnValue;
	};

   this.POPCFMPeopleSoft_CST = function (message) {
        if(!serverPath)
            serverPath = "..";
        var returnValue;
        var paramObj = new Object();
        paramObj.message = message;
        var items = message.split("<br/>");
        var fearture = "dialogWidth:450px; status = no;";
        if (items.length > 5) {
            var height = 140 + (items.length - 5) * 15;
            fearture = fearture + " dialogHeight:"+ height + "px; ";
        } else {
            fearture = fearture + " dialogHeight:140px; ";
        }
        
        var screen = window.showModalDialog(serverPath + "/COMMON/POPCFM.html", paramObj, fearture);
        if(screen != undefined) {
            returnValue = screen;
        } else {
            returnValue = this.NO_CONFIRM;
        }
        return returnValue;
    };
    
	this.POPTERServiceEndDate = function(url) {
		var returnValue;
		var paramObj = new Object();
		var screen = window.showModalDialog(url, paramObj, "dialogWidth:600px; dialogHeight:350px;status = no");
		if(screen != undefined) {
			returnValue = screen;
		} else {
			returnValue = this.NO_CONFIRM;
		}
		return returnValue;
	};
	
	this.POPACC = function(url) {
		var returnValue;
		var paramObj = new Object();
		var screen = window.showModalDialog(url, paramObj, "dialogWidth:580px; dialogHeight:300px;status = no");
		if(screen != undefined) {
			returnValue = screen;
		} else {
			returnValue = this.NO_CONFIRM;
		}
		return returnValue;
	};
	
	this.POPAPW = function(url) {
		var returnValue;
		var paramObj = new Object();
		var screen = window.showModalDialog(url, paramObj, "dialogWidth:580px; dialogHeight:300px;status = no");
		if(screen != undefined) {
			returnValue = screen;
		} else {
			returnValue = this.NO_CONFIRM;
		}
		return returnValue;
	};
	
	this.POPTER = function(url) {
		var returnValue;
		var paramObj = new Object();
		var screen = window.showModalDialog(url, paramObj, "dialogWidth:570px; dialogHeight:290px;status = no");
		if(screen != undefined) {
			returnValue = screen;
		} else {
			returnValue = this.NO_CONFIRM;
		}
		return returnValue;
	};
	
	this.ERROR_MESSAGE = function(message) {
		if(!serverPath)
			serverPath = "..";
		var returnValue;
		var paramObj = new Object();
		paramObj.message = message;
		var screen = window.showModalDialog(serverPath + "/COMMON/POPERR.html", paramObj, this.fearture);
		if(screen != undefined) {
			returnValue = screen;
		} else {
			returnValue = this.NO_CONFIRM;
		}
		return returnValue;
	};
	
	this.formatMessage = function() {
		
	};
	
	this.POPREJ = function(rejectionDate,remark) {
		if(!serverPath)
			serverPath = "..";
		var returnValue;
		var paramObj = new Object();
		paramObj.tDate = rejectionDate;
		paramObj.remark = remark;
		var screen = window.showModalDialog(serverPath + "/COMMON/POPCBR.jsp", paramObj, "dialogWidth:550px; dialogHeight:310px;status = no");
		if(screen != undefined) {
			returnValue = screen;
		} else {
			returnValue = this.NO_CONFIRM;
		}
		return returnValue;
	};
	this.POPCBR = function(rejectionDate,remark,hidTransDate, type, isCheckMulCharFlg) {
		if(!serverPath)
			serverPath = "..";
		var returnValue;
		var paramObj = new Object();
		paramObj.tDate = rejectionDate;
		paramObj.remark = remark;
		paramObj.hidTransDate = hidTransDate;
		paramObj.type = type;
		paramObj.isCheckMulCharFlg = isCheckMulCharFlg;
		var screen = window.showModalDialog(serverPath + "/COMMON/POPCBR.jsp", paramObj, "dialogWidth:550px; dialogHeight:370px;status = no");
		if(screen != undefined) {
			returnValue = screen;
		} else {
			returnValue = this.NO_CONFIRM;
		}
		return returnValue;
	};
	this.POPCUR = function(url) {
		var returnValue;
		var paramObj = new Object();
		var screen = window.showModalDialog(url, paramObj, "dialogWidth:570px; dialogHeight:350px;status = no");
		if(screen != undefined) {
			returnValue = screen;
		} else {
			returnValue = this.NO_CONFIRM;
		}
		return returnValue;
	};
}
