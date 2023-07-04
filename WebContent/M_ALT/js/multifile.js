/**
 * 
 * @param tableId
 * @param cssClass
 * @param fileName
 * @param max
 * @param alter
 * @param deleteList
 * @return
 */
function MultiSelector( tableId, cssClass, fileName, max, alter){
	this.maxLength = 100;
	this.maxLengthMessage = "File name cannot be greater than 100 characters.";
	this.maxFileSizeMessage = "File size cannot be greater than max file size";
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
				//new_element.id = 'my_file_element';
				// Add new element
				this.parentNode.insertBefore(new_element, this);
				//document.getElementById(target).insertBefore(element);
	
				// Apply 'update' to element
				this.multi_selector.addElement(new_element);
	
				// Update list
				this.multi_selector.addListRow(this, element);
				element.className = 'hideInputFile';
			};
			// If we've reached maximum number, disable input element
			if (this.max < 1) {
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
	
	this.addListRow = function(element,new_element){

		var fullName = element.value;
		shortName = fullName.match(/[^\/\\]+$/);
		
		// Add it to the list
		this.addRow(tableId,shortName,new_element);
		
	};	
	
	
	this.addRow = function(tableId,shortName,new_element) {
		var tbl = document.getElementById(tableId);
		//create div
		var d = new Date();
		var t = d.getTime();
		var divId = tableId+t;
		var divTag = document.createElement("div");
		divTag.className ="uploadDisplay";
		divTag.multi_selector = this;
		divTag.displayName = shortName;
		divTag.setAttribute('id', divId);
		
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
			var d = document.getElementById(tableId);
			var olddiv = document.getElementById(divId);
			d.removeChild(olddiv);
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
		divTag.appendChild(new_element);
		tbl.appendChild(divTag);
	};
};



	 
