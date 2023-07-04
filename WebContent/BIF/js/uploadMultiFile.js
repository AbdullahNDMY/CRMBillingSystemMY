
function MultiUploadFile(id, max_upload, uploaded, actionId, displayId, objectName){
	this.objectName = objectName;
	this.id = id;
	this.maxUpload = max_upload;
	this.uploaded = uploaded;
	this.index = 0;
	this.actionId = actionId;
	this.displayId = displayId;
	this.leftRecord = this.maxUpload - this.uploaded;
	var objCurrentFile = null;
	
	this.makeUploadFile = function ()
	{
		if(objCurrentFile != undefined) {
			
		} else {
			this.objCurrentFile = this.addControl(id);
		}
		this.objCurrentFile.click();
		if(this.objCurrentFile.value != "") {
			var fileId = this.objCurrentFile.getAttribute("id");
			this.addDisplayValue(this.id, fileId, this.sortName(this.objCurrentFile.value), this.objCurrentFile.value);
			this.leftRecord -= 1;
			if(this.leftRecord == 0) {
				var button = document.getElementById(this.actionId);
				button.disabled = true;
				return;
			}
		} else {
			this.removeControl();
		}
	};
	
	this.addDisplayValue = function (id, fileId, displayValue, dowloadLink) {
		//add file name
		var tableContent = document.getElementById(displayId).getElementsByTagName("tbody")[0];
		var row = document.createElement("tr");
		var contentRow = document.createElement("td");
		//contentRow.innerHTML = "<a href='" + dowloadLink + "'>" + displayValue + "</a> <img src='../image/delete.gif' onclick='javascript: " + this.objectName + ".remove(&quot;" + fileId + "&quot;)' />";
		contentRow.innerHTML = this.generateLinkText(dowloadLink, displayValue, this.objectName, fileId);
		row.appendChild(contentRow);
		tableContent.appendChild(row);
	};
	
	this.generateLinkText = function(downloadLink, displayValue, name, id) {
		var alink = "<a href='" + downloadLink + "'>" + displayValue + "</a> <img src='../image/delete.gif' onclick='javascript: " + name + ".remove(&quot;" + id + "&quot;)' />";
		var parent = "<div style='overflow:hidden' alt='" + name + "'>"  + downloadLink + "</div>";
		return parent;
	};
	
	this.limitFileName = function(filename) {
		var display_name = filename;
		max_length = 50;
		if(filename.length > max_length) {
			//get name and extension
			var arr = filename.split('.');
			var first = arr[0];
			var second = arr[1];
			var remain_chars = max_length;
			//check extension
			if(second.length > 0) {
				remain_chars = remain_chars - (second.length + 1);
			}
			//calculate file name
			if (first.length > remain_chars) {
				first = first.substr(0, remain_chars);
			}
		}
	};
	
	this.addControl = function (id) {
		var fileId = id + this.index;
		var file = document.createElement("input");
		file.setAttribute("type", "file");
		file.setAttribute("name", id + "[" + this.index + "]");
		file.setAttribute("id", fileId);
		var obj = document.getElementById(id);
		if (obj != undefined) {
			obj.appendChild(file);
			this.objCurrentFile = document.getElementById(fileId);
		}
		this.index += 1;
		return this.objCurrentFile;
	};

	this.removeControl = function () {
		var obj = document.getElementById(this.id);
		obj.removeChild(this.objCurrentFile);
		this.index -= 1;
	};
	
	this.remove = function (fileId) {
		new messageBox().POPALT("remove: " + fileId);
	};
	
	this.sortName = function (fullName) {
		var shortName = fullName.match(/[^\/\\]+$/);
		return shortName;
	};
};
