
function MultiSelector( tableId,cssClass,fileName, max ){

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
	
	/**
	 * Add a new file input element
	 */
	this.addElement = function( element ){

		// Make sure it's a file input element
		if( element.tagName == 'INPUT' && element.type == 'file' ){

			// Element name -- what number am I?
			element.name = fileName + '[' + this.id++ + "]";

			// Add reference to this object
			element.multi_selector = this;

			// What to do when a file is selected
			element.onchange = function(){

				// New file input
				var new_element = document.createElement( 'input' );
				new_element.type = 'file';
				new_element.className = cssClass;
				// Add new element
				this.parentNode.insertBefore( new_element, this );
				//document.getElementById(target).insertBefore(element);
	
				// Apply 'update' to element
				this.multi_selector.addElement( new_element );
	
				// Update list
				this.multi_selector.addListRow( this );
				element.className = 'hideInputFile';
				hideInputFile(tableId,element);
				
			};
			// If we've reached maximum number, disable input element
			if( this.max != -1 && this.count >= this.max ){
				element.disabled = true;
			};
	
			// File element counter
			this.count++;
			// Most recent element
			this.current_element = element;
		} 

	};
	
	this.addListRow = function( element ){

		var fullName = element.value;
		shortName = fullName.match(/[^\/\\]+$/);
		

		// Add it to the list
		addRow(tableId,shortName);
		
	};	
};
function hideInputFile(tableId,element){
	var tbl = document.getElementById(tableId);
	var lastRow = tbl.rows.length;	
	var row = tbl.tBodies[0].rows[lastRow - 1];
	var newCell=row.insertCell(1);
	newCell.appendChild(element);
}
function addRow(tableId,fileName){
	var lastRow;
	var tbl = document.getElementById(tableId);
	//var row0Value = tbl.tBodies[0].rows[0].cells[0];
		
	lastRow = tbl.rows.length;
	//Insert new row
	var newRow = tbl.insertRow(lastRow);
	//Insert 1st cell
	var newCell=newRow.insertCell(0);
	
	newCell.innerHTML=fileName + "&nbsp;&nbsp;" + "<img src='../image/delete.gif' onclick='removeRow(this)' />";	
}
function removeRow(obj,section_group,fileName){
	var tbl = obj.parentElement.parentElement.parentElement;
	var row = obj.parentElement.parentElement; 
	tbl.deleteRow(row.rowIndex);
	if(section_group != null){
		if(section_group == "PMR"){
			document.getElementById("deletedAttachmentPMR").value = document.getElementById("deletedAttachmentPMR").value + ";" + fileName;
		}else if(section_group == "BZR"){
			document.getElementById("deletedAttachmentBZR").value = document.getElementById("deletedAttachmentBZR").value + ";" + fileName;
		}else if(section_group == "CTC"){
			document.getElementById("deletedAttachmentCTC").value = document.getElementById("deletedAttachmentCTC").value + ";" + fileName;
		}else if(section_group == "PRI"){
			document.getElementById("deletedAttachmentPRI").value = document.getElementById("deletedAttachmentPRI").value + ";" + fileName;
		}else if(section_group == "MRG"){
			document.getElementById("deletedAttachmentMRG").value = document.getElementById("deletedAttachmentMRG").value + ";" + fileName;
		}else if(section_group == "CRC"){
			document.getElementById("deletedAttachmentCRC").value = document.getElementById("deletedAttachmentCRC").value + ";" + fileName;
		}else if(section_group == "FRX"){
			document.getElementById("deletedAttachmentFRX").value = document.getElementById("deletedAttachmentFRX").value + ";" + fileName;
		}else if(section_group == "COV"){
			document.getElementById("deletedAttachmentCOV").value = document.getElementById("deletedAttachmentCOV").value + ";" + fileName;
		}else if(section_group == "QCS"){
			document.getElementById("deletedAttachmentQCS").value = document.getElementById("deletedAttachmentQCS").value + ";" + fileName;
		}
		
	}
}