/**
 * 
 * @param tableId
 * @param cssClass
 * @param fileName
 * @param max
 * @param addButton
 * @param alter
 * @param deleteList
 * @return
 */
function MultiSelector( tableId, cssClass, fileName, max, addButton, alter){
	this.maxLength = 100;
	this.maxLengthMessage = "File name cannot be greater than 100 characters.";
	// Where to write the list
	this.list_target = document.getElementById(tableId);
	// How many elements?
	this.count = 0;
	// How many elements?
	this.id = 0;
	// Is there a maximum?
	if( max ){
		this.max = max;
	} else {
		this.max = -1;
	};
	
	this.addButton = addButton;
	if(this.max < 1) {
		this.addButton.disabled = true;
	}
	
	this.alter = alter; 
	this.current_element = null;
	
	this.message = null;
	this.setMessage = function (message) {
		this.message = message;
	};
	/**
	 * Add a new file input element
	 */
	this.addElement = function(element){
		
		// Make sure it's a file input element
		if( element.tagName == 'INPUT' && element.type == 'file' ){
			this.current_element = element; 
			// Element name -- what number am I?
			element.name = fileName + '[' + this.id++ + "]";

			// Add reference to this object
			element.multi_selector = this;

			// What to do when a file is selected
			element.onchange = function(){
				
				//check input file is valid or not
				var fName = "" + this.value.match(/[^\/\\]+$/);
				if(fName.length > 100) {
					new messageBox().POPALT(this.multi_selector.maxLengthMessage);
					//remove invalid input
					return;
				}
				
				// New file input
				var new_element = document.createElement('input');
				new_element.type = 'file';
				new_element.className = cssClass;
				// Add new element
				this.parentNode.insertBefore(new_element, this);
				//document.getElementById(target).insertBefore(element);
	
				// Apply 'update' to element
				this.multi_selector.addElement(new_element);
	
				// Update list
				this.multi_selector.addListRow(this);
				element.className = 'hideInputFile';
				hideInputFile(tableId,element);
				
			};
			// If we've reached maximum number, disable input element
			if (this.max < 1) {
				this.addButton.disabled = true;
				element.disabled = true;
				return;
			}
	
			// File element counter
			//this.count++;
			this.max--;
			// Most recent element
			this.current_element = element;
		} 

	};
	
	this.addListRow = function(element){

		var fullName = element.value;
		shortName = fullName.match(/[^\/\\]+$/);
		
		// Add it to the list
		this.addRow(tableId,shortName);
		
	};	
	
	
	this.addRow = function(tableId,shortName) {
		var lastRow;
		var tbl = document.getElementById(tableId);
		//var row0Value = tbl.tBodies[0].rows[0].cells[0];
			
		lastRow = tbl.rows.length;
		//Insert new row
		var newRow = tbl.insertRow(lastRow);
		//Insert 1st cell
		var newCell=newRow.insertCell(0);
		
		//newCell.innerHTML="<img src='../image/delete.gif' onclick='removeRow(this)' />" + "&nbsp;&nbsp;" + "<font color='blue'>Attachment&nbsp;;</font>&nbsp;&nbsp;" + fileName;
		//newCell.innerHTML="<div class='uploadDisplay' onmouseover=\"alter.display('" + fileName + "')\" onmouseout=\"alter.hidden();\"><img src='../image/delete.gif' onclick='removeAttach(this)' />" + "&nbsp;&nbsp;" + fileName + "</div>";
		
		//create div
		var divTag = document.createElement("div");
		divTag.className ="uploadDisplay";
		divTag.multi_selector = this;
		divTag.displayName = shortName;
		
		//define when mouse over div
		divTag.onmouseover = function() {
			if(this.multi_selector != undefined) {
				if(this.multi_selector.alter != undefined) {
					this.multi_selector.alter.display(this.displayName);
				}
			}
		};
		
		//define when mouse out div
		divTag.onmouseout = function() {
			if(this.multi_selector != undefined) {
				if(this.multi_selector.alter != undefined) {
					this.multi_selector.alter.hidden();
				}
			}
		};
		
		//create image
		var imgTag = document.createElement("img");
		imgTag.src = "../image/delete.gif";
		imgTag.multi_selector = this;
		imgTag.onclick = function () {
			var MsgBox = imgTag.multi_selector.message;
			if(MsgBox != null) {
				//show message box to confirm when delete
				if(MsgBox.POPDEL2() != MsgBox.YES_CONFIRM) {
					return;
				}
			}
			var tbl = this.parentElement.parentElement.parentElement.parentElement;
			var row = this.parentElement.parentElement.parentElement; 
			tbl.deleteRow(row.rowIndex);
			this.multi_selector.addButton.disabled = false;
			this.multi_selector.current_element.disabled = false;
			this.multi_selector.max ++;
			//remove alter
			if(this.multi_selector != undefined) {
				if (this.multi_selector.alter != undefined) {
					this.multi_selector.alter.hidden();
				}
			}
		};
		
		//create file name text object 
		var textObject = document.createTextNode(" " + shortName + ";");
		
		//add node to row
		divTag.appendChild(imgTag);
		divTag.appendChild(textObject);
		newCell.appendChild(divTag);
	};
	
	this.addServerRow = function (tableId, shortName, id_doc, deleteList, ref) {
		var tbl = document.getElementById(tableId);
		var lastRow = tbl.rows.length;
		//Insert new row
		var newRow = tbl.insertRow(lastRow);
		//Insert 1st cell
		var newCell=newRow.insertCell(0);
		
		//create div
		var divTag = document.createElement("div");
		divTag.className ="uploadDisplay";
		divTag.multi_selector = this;
		divTag.displayName = shortName;
		
		//define when mouse over div
		divTag.onmouseover = function() {
			if(this.multi_selector != undefined) {
				if(this.multi_selector.alter != undefined) {
					this.multi_selector.alter.display(this.displayName);
				}
			}
		};
		
		//define when mouse out div
		divTag.onmouseout = function() {
			if(this.multi_selector != undefined) {
				if(this.multi_selector.alter != undefined) {
					this.multi_selector.alter.hidden();
				}
			}
		};
		
		//create image
		if(ref != "DS3"){
			var imgTag = document.createElement("img");
			imgTag.src = "../image/delete.gif";
			imgTag.multi_selector = this;
			imgTag.deleteList = deleteList;
			imgTag.id_doc = id_doc;
			//imgTag.onclick = "alert('delete');";
			imgTag.onclick = function() {
				var MsgBox = imgTag.multi_selector.message;
				if(MsgBox != null) {
					//show message box to confirm when delete
					if(MsgBox.POPDEL2() != MsgBox.YES_CONFIRM) {
						return;
					}
				}
				var tbl = this.parentElement.parentElement.parentElement.parentElement;
				var row = this.parentElement.parentElement.parentElement; 
				tbl.deleteRow(row.rowIndex);
				if(this.deleteList != undefined) {
					this.deleteList.value += this.id_doc + ";";
				}
				this.multi_selector.addButton.disabled = false;
				this.multi_selector.current_element.disabled = false;
				this.multi_selector.max++;
				//remove alter
				if(this.multi_selector != undefined) {
					if (this.multi_selector.alter != undefined) {
						this.multi_selector.alter.hidden();
					}
				}
			};
			divTag.appendChild(imgTag);
			}
		
		
		//create a link object 
		var linkTag = document.createElement("a");
		linkTag.href = "javascript:clickDownload('" + id_doc + "')";
		linkTag.innerText = "  " + shortName + ";";
	
		//add node to row
		
		divTag.appendChild(linkTag);
		newCell.appendChild(divTag);
	};
};

function hideInputFile(tableId, element){
	var tbl = document.getElementById(tableId);
	var lastRow = tbl.rows.length;	
	var row = tbl.tBodies[0].rows[lastRow - 1];
	var newCell=row.insertCell(1);
	newCell.appendChild(element);
}

function removeAttach(obj, deleteList, fileName) {
	var tbl = obj.parentElement.parentElement.parentElement.parentElement;
	var row = obj.parentElement.parentElement.parentElement; 
	tbl.deleteRow(row.rowIndex);
	if(deleteList != null && deleteList != undefined) {
		deleteList.value += fileName + ";"; 
	}
}

function removeRow(obj,section_group,fileName) {
	var tbl = obj.parentElement.parentElement.parentElement.parentElement;
	var row = obj.parentElement.parentElement.parentElement; 
	tbl.deleteRow(row.rowIndex);
	if(section_group != null) {
		if(section_group == "SC") {
			document.getElementById("deletedAttachmentSC").value = document.getElementById("deletedAttachmentSC").value + ";" + fileName;
		}else if(section_group == "QP"){
			document.getElementById("deletedAttachmentQP").value = document.getElementById("deletedAttachmentQP").value + ";" + fileName;
		}else if(section_group == "OT"){
			document.getElementById("deletedAttachmentOT").value = document.getElementById("deletedAttachmentOT").value + ";" + fileName;
		}
	}
	this.max += 1;
}