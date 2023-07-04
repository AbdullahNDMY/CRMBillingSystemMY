/**
 * Indicate ascii code of char
 * @param aChar
 */
function calKeyCode(charKey) {
	var character = charKey.substring(0,1);
	var code = character.charCodeAt(0);
	return code;
}

/**
 * Check whether value is number
 * @param value
 * @return boolean
 */
function isNumber(value) {
	return value && 
			value.replace(/\s/g,"") != "" &&
			value.indexOf('.') < 0 &&
			value.indexOf(',') < 0 &&
			isNaN(value) == false;
}

/**
 * Check number between min and max?
 * @param value
 * @param min
 * @param max
 */
function isNumberBetween(value, min, max) {
	return isNumber(value) && 
			Number(value) >= min && 
			Number(value) <= max;
}

/**
 * Key is number?
 * @param evt
 */
function isNumberKey(evt)
{
    var charCode = (evt.which) ? evt.which : event.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57))
       return false;

    return true;
}
 
/**
 * Set caret position
 * @param oField
 * @param iCaretPos
 */
function doSetCaretPosition (oField, iCaretPos) {
	if (document.selection) { 
		oField.focus ();
		var oSel = document.selection.createRange ();
		oSel.moveStart ('character', -oField.value.length);
		oSel.moveStart ('character', iCaretPos);
		oSel.moveEnd ('character', iCaretPos - oField.value.length);
		oSel.select ();
	}
	else if (oField.selectionStart || oField.selectionStart == '0') {
		oField.selectionStart = iCaretPos;
		oField.selectionEnd = iCaretPos;
		oField.focus ();
    }
}  
	  
/**
 * Get caret position
 * @param oField
 */
function doGetCaretPosition (oField) {
	var iCaretPos = 0;
	if (document.selection) { 
		oField.focus ();
	    var oSel = document.selection.createRange();
	    oSel.moveStart ('character', oField.value? -oField.value.length : 0);
	    iCaretPos = oSel.text.length;
	}
	else if (oField.selectionStart || oField.selectionStart == '0')
       iCaretPos = oField.selectionStart;
    return (iCaretPos);
}
	  
	  
/**
 * Check number
 * @param elementID
 * @param evt
 * @param minValue
 * @param maxValue
 */
function isValidNumberOnKeyUp(elementID, evt, minValue, maxValue) {
	var element = document.getElementById(elementID);
	if (element) {
		var caretPos = doGetCaretPosition(element);
		var charCode = (evt.which) ? evt.which : event.keyCode;
		var charValue = String.fromCharCode(charCode);		
		var currentElementValue = element.value.substr(0, caretPos) + 
								  charValue +
								  element.value.substr(caretPos);
		// Filter code
		if (charCode != 37 && charCode != 38 && charCode != 39 && charCode != 40 && 
			charCode != 35 && charCode != 46 && charCode != 8 && charCode != 36) {
			if ( charCode < 47 || charCode > 57 ) {
				if ( evt.preventDefault ){
					evt.preventDefault(); 
				}
				else{
					return false; 
				}
			}
		}
		if (charValue == '.' || charValue == ',') {
			return false; 
		}
		// Filter value
		var intElementValue = Number(currentElementValue);	
		if (minValue != null && maxValue != null) {
			if (intElementValue < minValue || intElementValue > maxValue) {	
				return false;
			}
		}		
		return true;
	}
	return false;
}

 /**
  * Check number
  * @param elementID
  * @param evt
  * @param minValue
  * @param maxValue
  */
 function isValidNumberOnKeyUpWithElement(element, evt, minValue, maxValue) {
 	if (element) {
 		var caretPos = doGetCaretPosition(element);
 		var charCode = (evt.which) ? evt.which : event.keyCode;
 		var charValue = String.fromCharCode(charCode);		
 		var currentElementValue = element.value.substr(0, caretPos) + 
 								  charValue +
 								  element.value.substr(caretPos);
 		// Filter code
 		if (charCode != 37 && charCode != 38 && charCode != 39 && charCode != 40 && 
 			charCode != 35 && charCode != 46 && charCode != 8 && charCode != 36) {
 			if ( charCode < 47 || charCode > 57 ) {
 				if ( evt.preventDefault ){
 					evt.preventDefault(); 
 				}
 				else{
 					return false; 
 				}
 			}
 		}
 		if (charValue == '.' || charValue == ',') {
			return false; 
		}
 		// Filter value
 		var intElementValue = Number(currentElementValue);	
 		if (minValue != null && maxValue != null) {
 			if (intElementValue < minValue || intElementValue > maxValue) {	
 				return false;
 			}
 		}		
 		return true;
 	}
 	return false;
 }
 
/**
 * Display or hide panels
 * @param isVisible
 * @param panelIDs
 */
function activateHiddenPanels(isVisible, panelID) {		
	if (isVisible) {		
		if (panelID != null) {				
			panel = document.getElementById(panelID);
			if (panel) {
				panel.style.display = 'block'; 	
			}
		}
	} else {		
		if (panelID != null) {			
			panel = document.getElementById(panelID);
			if (panel) {
				panel.style.display = 'none'; 
			}
		}
	}
}
 
/**
 * Popup panel
 * @param path
 */
function popUp(path){	
 	var strFeatures="toolbar=no, status=yes, menubar=yes, location=no, scrollbars=yes, resizable=yes";	 	
 	newPanel = window.open(path, null, strFeatures); 	
}
 
/**
 * Popup panel
 * @param path
 * @param strFeatures
 */
function popUpWithSpecificFeatures(path, strFeatures, screenWidth, screenHeight){	
	 var width = screenWidth;
	 var height = screenHeight;
	 var left = Number((screen.availWidth/2) - (width/2));
	 var top = Number((screen.availHeight/2) - (height/2));
	 var offsetFeatures = "width=" + width + ",height=" + height +
						  ",left=" + left + ",top=" + top +
						  "screenX=" + left + ",screenY=" + top;
	 strFeatures= strFeatures + "," + offsetFeatures;	 	
	 newPanel = window.open(path, null, strFeatures);	
}

/**
 * Activate element with id 
 */
function activateElementWith(elementID, isActive) {
	var element = document.getElementById(elementID);	
	if(element) {	
		if (isActive) {			
			element.disabled = false;
		} else {			
			element.disabled = true;
			element.value='';
		}
	}
}
 
/**
 * Remove space of text areas
 */
function removeSpaceOfTextAreas() {
	// Text area
	var textAreas = document.getElementsByTagName("textarea");	
	if (textAreas) {
		for (var i = 0; i < textAreas.length; i++) {
			var textArea = textAreas[i];
			textArea.value = textArea.value.replace(/^\s*|\s*$/g,'');
		}
	}
	// Text input
	var textInputs = document.getElementsByTagName("input");	
	if (textAreas) {
		for (var i = 0; i < textInputs.length; i++) {
			var textInput = textInputs[i];
			if (textInput.type && textInput.type == "text") {
				textInput.value = textInput.value.replace(/^\s*|\s*$/g,'');
			}
		}
	}
}

/**
 * Check valid length of element
 * @param element
 * @param maxLength
 */
function checkLength(evt, element, maxLength) {
	if (element) {
		var caretPos = doGetCaretPosition(element);
		var charCode = (evt.which) ? evt.which : event.keyCode;
		var charValue = String.fromCharCode(charCode);
		var currentElementValue = element.value.substr(0, caretPos) + 
								  charValue + 
								  element.value.substr(caretPos);
		if(currentElementValue.length > maxLength) {
			return false;			
		}
		return true;
	}
	return false;
}

/**
 * Check valid length of element
 * @param element
 * @param maxLength
 */
function checkLengthNotInputEntry(evt, element, maxLength) {
	//ENTER key
	if(13==event.keyCode) {
		return false;
	}
	if (element) {
		var caretPos = doGetCaretPosition(element);
		var charCode = (evt.which) ? evt.which : event.keyCode;
		var charValue = String.fromCharCode(charCode);
		var currentElementValue = element.value.substr(0, caretPos) + 
								  charValue + 
								  element.value.substr(caretPos);
		if(currentElementValue.length > maxLength) {
			return false;			
		}
		return true;
	}
	return false;
}

/**
 * Customize length of element value
 * @param element
 * @param maxLength
 */
function customizeElementValueLength(element, maxLength) {
	if (element && element.value) {
		element.value = element.value.length > maxLength?
						element.value.substr(0, maxLength):
						element.value; 
	}
}

/**
 * Value of invalid number with specific element
 */
function valueOfInvalidNumberWithElement(element, defaultValue) {
	if (element) {
		element.value = isNumber(element.value)? element.value : defaultValue;
	}
}
 
/**
 * Add option to selectBox
 */
function addOptionToSelectBox(selectBox, text, value ){
	 var option = document.createElement("OPTION");
	 option.text = text;
	 option.value = value;
	 selectBox.options.add(option);
}

/**
 * Set values of select box
 */
function setSelectBoxValues(selectBox, values, valueID, textID) {
	if (selectBox && values) {
		for (var i=0; i < values.length; i++) {
			var value = values[i];
			addOptionToSelectBox(selectBox, value[textID], value[valueID]);
		}
	}
}

/**
 * Clear select box
 */
function clearSelectBox(selectBox)
{
   var options=selectBox.getElementsByTagName("option");
   var i;   
   for (i = 0; i < options.length; i++)
   {
	   selectBox.removeChild(options[i]);
   }
   selectBox.length = 0;
   selectBox.options.length = 0;
}
