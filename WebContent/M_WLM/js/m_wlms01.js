// Array of section group
var arrSecgrp = new Array();

var LIST_USER_KEY = "listUser";

var LIST_OPERATOR_KEY = "listOperator";

var ACTION_TYPE_KEY = "ACTIONTYPE";

var RESPONSE_KEY ="RES";

var CON1_KEY ="CON1";

var CON3_KEY ="CON3";

var SEPERATE_STRING = ":";

var SEPERATE_STRING2 = ";";

var message = "";

var deleteMessage = "";

// Current section group id
var currentSecGrpId = "";

function search(){
	var cbo = document.getElementById("id_screen");
	document.getElementById("err_msg").style.display = "none";
	if(cbo.selectedIndex>0)
	{
	document.getElementById("id_section_group").value = "";
	
	mainSubmit('search');
	}
	else
	{
		cbo.focus();
		document.getElementById("save_success").style.display = "none";
		document.getElementById("err_msg").innerHTML = "Item \"Screen ID\" : Selection Error. Please select one.";
		document.getElementById("err_msg").style.display = "block";
		document.getElementById("searchResult").style.display = "none";
	}
}

var countries;

// Set value for array of section group list
function setSectionGroupList(index, sectionGroupId){
	arrSecgrp[index] = new SectionGroup(sectionGroupId);
} 

// Set value for Section List
function setSectionList(index, sectionGroupId, sectionNo,sectionName){
	var listLength = arrSecgrp[index].listSection.length;
	var groupId = arrSecgrp[index].sectionGroupId;
	if (groupId == sectionGroupId){
		arrSecgrp[index].listSection[listLength] = 
			new Section(sectionNo,sectionName);
	}
}

// Set value for WF section
function setWfSectionList(index, sectionGroupId, sectionNo, sectionName, sequence){
	var listLength = arrSecgrp[index].listWfSection.length;
	var groupId = arrSecgrp[index].sectionGroupId;
	if (groupId == sectionGroupId){
		var sec = new Section(sectionNo,sectionName); 
		sec.sequence = sequence;
		arrSecgrp[index].listWfSection[listLength] = sec;
	}
}

// Set value for Level list
function setLevelList(sectionGroupId, sectionNo, level){
	for (var i=0; i<arrSecgrp.length; i++){
		var groupId = arrSecgrp[i].sectionGroupId;
		if (groupId == sectionGroupId){
			var sectionLength = arrSecgrp[i].listWfSection.length;
			for (var j=0; j<sectionLength; j++){
				var sectionId = arrSecgrp[i].listWfSection[j].sectionNo;
				if (sectionNo == sectionId){
					var levelLength = arrSecgrp[i].listWfSection[j].listLevel.length;
					var lev = new Level(level);
					arrSecgrp[i].listWfSection[j].listLevel[levelLength] = lev;
					return;
				}
			}
		}
	}
}

// Set level for Action List
function setActionList(sectionGroupId, sectionNo, level, actionId, actionType, pic, response, condition1, condition2, condition3){
	for (var i=0; i<arrSecgrp.length; i++){
		var groupId = arrSecgrp[i].sectionGroupId;
		if (groupId == sectionGroupId){
			var sectionLength = arrSecgrp[i].listWfSection.length;
			for (var j=0; j<sectionLength; j++){
				var sectionId = arrSecgrp[i].listWfSection[j].sectionNo;
				if (sectionNo == sectionId){
					var levelLength = arrSecgrp[i].listWfSection[j].listLevel.length;
					for (var k=0; k<levelLength; k++){
						var levelId = arrSecgrp[i].listWfSection[j].listLevel[k].levelId;
						if (levelId == level){
							var actionLength = arrSecgrp[i].listWfSection[j].listLevel[k].listAction.length;
							var action = new Action(actionId);
							
							action.actionType = actionType;
							action.pic = pic;
							action.response = response;
							action.condition1 = condition1;
							action.condition2 = condition2;
							action.condition3 = condition3;
							
							arrSecgrp[i].listWfSection[j].listLevel[k].listAction[actionLength] = action;
							return;
						}
					}
				}
			}
		}
	}
} 

// The class of Section Group
function SectionGroup(sectionGroupId){
	// Section Group Id
	this.sectionGroupId = sectionGroupId;
	// The list of Section
	this.listSection =  new Array();
	// The list of Workflow Section
	this.listWfSection =  new Array();
}

// Class Section
function Section(sectionNo, sectionName){
	this.sectionNo = sectionNo;
	this.sectionName = sectionName;
	this.sequence = 0;
	this.listLevel = new Array();
}

// Class Level
function Level(levelId){
	this.levelId = levelId;
	this.listAction = new Array();
}

// Class Action
function Action(actionId){
	this.actionId = actionId;
	this.actionType = "AA";
	this.pic = "";
	this.response = "";
	this.condition1 = "";
	this.condition2 = "";
	this.condition3 = "";
}

function save(secGrpId){
	
	var groupIndex = getSecGrpIndex(secGrpId);
	
	// Load data from screen
	loadDataFromScreen(groupIndex);
	
	message = "";
	document.getElementById("err_msg").style.display = "none";
	//document.getElementById("save_success").style.display = "none";
	if (validateData(groupIndex)){
	
		document.getElementById("id_section_group").value = secGrpId;
		var listSection = document.getElementById("listApSection");
		var listAction = document.getElementById("listApAction");
		
		listSection.value = generateSectionList(groupIndex);
		listAction.value = generateActionList(groupIndex);
		
		document.getElementById("id_section_group").value = currentSecGrpId;
		
		mainSubmit("save");
		//document.getElementById("save_success").style.display = "block";
	} else {
		document.getElementById("save_success").style.display = "none";
		document.getElementById("err_msg").innerHTML = message;
		document.getElementById("err_msg").style.display = "block";
		//alert(message);
	}
}

function generateSectionList(groupIndex){
	var str = "";
	
	for (var i=0; i < arrSecgrp[groupIndex].listWfSection.length; i++){
		str += arrSecgrp[groupIndex].listWfSection[i].sectionNo;
		str += SEPERATE_STRING;
		str += arrSecgrp[groupIndex].listWfSection[i].sequence;
		str += SEPERATE_STRING2;
	}
	
	return str;
}

function generateActionList(groupIndex){
	var str = "";
	var level = 0;
	var actionId = 0;
	for (var i=0; i<arrSecgrp[groupIndex].listWfSection.length; i++){
		for (var j=0; j<arrSecgrp[groupIndex].listWfSection[i].listLevel.length; j++){
			level = level + 1;
			for (var k=0; k<arrSecgrp[groupIndex].listWfSection[i].listLevel[j].listAction.length; k++){
				actionId = actionId + 1;
				str += arrSecgrp[groupIndex].listWfSection[i].sectionNo;
				str += SEPERATE_STRING;
				//str += (j + 1);
				str += level;
				str += SEPERATE_STRING;
				//str += (k + 1);
				str += actionId;
				str += SEPERATE_STRING;
				str += arrSecgrp[groupIndex].listWfSection[i].listLevel[j].listAction[k].actionType;
				str += SEPERATE_STRING;
				str += arrSecgrp[groupIndex].listWfSection[i].listLevel[j].listAction[k].pic;
				str += SEPERATE_STRING;
				str += arrSecgrp[groupIndex].listWfSection[i].listLevel[j].listAction[k].response;
				str += SEPERATE_STRING;
				str += arrSecgrp[groupIndex].listWfSection[i].listLevel[j].listAction[k].condition1;
				str += SEPERATE_STRING;
				str += arrSecgrp[groupIndex].listWfSection[i].listLevel[j].listAction[k].condition2;
				str += SEPERATE_STRING;
				str += arrSecgrp[groupIndex].listWfSection[i].listLevel[j].listAction[k].condition3;
				str += SEPERATE_STRING2;
			}
		}
	}
	
	return str;
}

// Initialize the page
function initPage(){
	if (document.getElementById("countrytabs") != null){
		countries=new ddtabcontent("countrytabs");
		countries.setpersist(true);
		countries.setselectedClassTarget("link"); //"link" or "linkparent"
		countries.init();
	
		// Initialize values in controls
		initValue();
	}
}

// get Section Group Index from Id
function getSecGrpIndex(SecGrpId){
	for (var i=0; i<arrSecgrp.length; i++){
		if (SecGrpId == arrSecgrp[i].sectionGroupId){
			return i;
		}
	}
	return 0;
}

// Initialize all values
function initValue(){
	
	currentSecGrpId = document.getElementById("id_section_group").value;
	
	if (currentSecGrpId == "" && arrSecgrp.length > 0){
		currentSecGrpId = arrSecgrp[0].sectionGroupId;
	}
	
	// Create Controls with data from DB
	createSecGrpDiv();
	
	for (var i=0; i < countries.tabs.length; i++){
		if (countries.tabs[i].getAttribute("rel") == currentSecGrpId){
			countries.expandtab(countries.tabs[i]);
			countries.cancelautorun(); 
		}
	}
	
	deleteMessage = document.getElementById("deleteMsg").value;
}

function createSecGrpDiv(){
	for (var i=0; i<arrSecgrp.length; i++){
		createSectionDiv(arrSecgrp[i].sectionGroupId);
		loadDataToScreen(i);
	}
}

// Create Section Div
function createSectionDiv(secGrpId){
	var currentGrpIndex = getSecGrpIndex(secGrpId);
	var strHTML = "";
	
	// If there is workflow section in DB
	var listWfLength = arrSecgrp[currentGrpIndex].listWfSection.length;
	if(listWfLength > 0){
		for (var j=0; j<listWfLength; j++){
			// Add workflow section
			 strHTML += createSectionControl(currentGrpIndex, j);
		}
	} else {
		addSection(secGrpId);		
	}
	
	var tabControl = document.getElementById("sectionsDiv"+secGrpId);
	tabControl.innerHTML = tabControl.innerHTML + strHTML;
}

// Create Level Div
function createLevelDiv(groupIndex, sectionIndex){
	var levelLength = arrSecgrp[groupIndex].listWfSection[sectionIndex].listLevel.length;
	
	var divId = gennerateId(groupIndex, sectionIndex, null, null); 
	var strHTML = "<div id=\"" + divId + "\">";
	
	for (var i=0; i<levelLength; i++){		
		strHTML += createLevelControl(groupIndex, sectionIndex, i);
	}
	
	strHTML += "</div>";
	
	return strHTML;
}

// Create Action Div
function createActionDiv(groupIndex, sectionIndex, levelIndex){
	var actionLength = arrSecgrp[groupIndex].listWfSection[sectionIndex].listLevel[levelIndex].listAction.length;
	
	var divId = gennerateId(groupIndex, sectionIndex, levelIndex, null); 
	var strHTML = "<div id=\"" + divId + "\">";
	for (var i=0; i<actionLength; i++){
		strHTML += createActionControl(groupIndex, sectionIndex, levelIndex, i);
	}
	
	strHTML += "</div>";
	
	strHTML += "<div>";
	var tempF = "addAction(" + groupIndex + "," + sectionIndex + "," + levelIndex + ");";
	if (document.getElementById("access_type").value=="2") {
		strHTML += "<button class=\"btnAction\" onclick=\"" + tempF + "\">Add Action User</button>";
	}
	strHTML += "</div>";
	return strHTML;
}

// Add new Action
function addAction(groupIndex, sectionIndex, levelIndex){
	// Add in data
	var actionLength = arrSecgrp[groupIndex].listWfSection[sectionIndex].listLevel[levelIndex].listAction.length;
	var action = new Action(actionLength + 1);
	
	arrSecgrp[groupIndex].listWfSection[sectionIndex].listLevel[levelIndex].listAction[actionLength] = action;
	
	// Add control on screen
	var divId = gennerateId(groupIndex, sectionIndex, levelIndex, null); 
	var actionDiv = document.getElementById(divId);
	
	actionDiv.innerHTML += createActionControl(groupIndex, sectionIndex, levelIndex, actionLength);
}

// Add new Level
function addLevel(groupIndex, sectionIndex){
	// Add in data
	var levelLength = arrSecgrp[groupIndex].listWfSection[sectionIndex].listLevel.length;
	var level = new Level(levelLength + 1);
	
	var action = new Action(1);
	
	level.listAction[0] = action;
	arrSecgrp[groupIndex].listWfSection[sectionIndex].listLevel[levelLength] = level;
	
	// Add control on screen
	var divId = gennerateId(groupIndex, sectionIndex, null, null); 
	var levelDiv = document.getElementById(divId);
	
	levelDiv.innerHTML += createLevelControl(groupIndex, sectionIndex, levelLength);
}

// Add New Section
function addSection(groupId){
	
	var groupIndex = getSecGrpIndex(groupId);
	
	// Add in data
	var sectionLength = arrSecgrp[groupIndex].listWfSection.length;
	var section = new Section("","");
	
	var level = new Level(1);
	var action = new Action(1);
	
	level.listAction[0] = action;
	section.listLevel[0] = level;
	arrSecgrp[groupIndex].listWfSection[sectionLength] = section;
	
	// Add control on screen
	var tabControl = document.getElementById("sectionsDiv"+groupId);
	tabControl.innerHTML += createSectionControl(groupIndex, sectionLength);
}

// Create Section Control
function createSectionControl(groupIndex, sectionIndex){
	
	var sectionName = createSectionCombobox(groupIndex, sectionIndex);
	//var sequenceValue = arrSecgrp[groupIndex].listWfSection[sectionIndex].sequence;
	var textBoxId = "SEQ" + gennerateId(groupIndex, sectionIndex, null, null); 
	var sequenceValue=createIntegerTextboxControl(textBoxId, arrSecgrp[groupIndex].listWfSection[sectionIndex].sequence,1,2);
	
	var strHTML = "<fieldset class=\"sectionGroupBox\">";
	strHTML += "<legend>Section</legend>";
	
	strHTML += "<div class=\"sectionContent\">";
	if (document.getElementById("access_type").value=="2") {
		strHTML += "<image onclick=\"deleteSection(" + groupIndex + "," + sectionIndex + ", 0);\" class=\"imageDelete\" src=\"../image/delete.gif\"/>";
	}
	strHTML += "<table cellpadding=\"0\" cellspacing=\"0\">";
	strHTML += "<tr>";
	strHTML += "<td>Section Name</td>";
	if (document.getElementById("access_type").value=="2") {
		strHTML += "<td>: " + sectionName + "</td>";
	}else{
		strHTML += "<td>: " +arrSecgrp[groupIndex].listWfSection[sectionIndex].sectionName + "</td>";
	}
	strHTML += "</tr>";
	
	strHTML += "<tr>";
	strHTML += "<td>Approval Sequence</td>";
	if (document.getElementById("access_type").value=="2") {
		strHTML += "<td>: " + sequenceValue + "</td>";
	}else{
		strHTML += "<td>: " + arrSecgrp[groupIndex].listWfSection[sectionIndex].sequence + "</td>";
	}
	strHTML += "</tr>";
	strHTML += "</table>";

	if (document.getElementById("access_type").value=="2") {
		strHTML += "<button class=\"btnLevel\" onclick=\"addLevel(" + groupIndex + "," + sectionIndex + ");\">Add Level</button>";
	}
	strHTML += createLevelDiv(groupIndex, sectionIndex);
	strHTML += "</div>";


	strHTML += "</fieldset>";
	
	return strHTML;
}

// Create Action Control
function createActionControl(groupIndex, sectionIndex, levelIndex, actionIndex){
	var act = arrSecgrp[groupIndex].listWfSection[sectionIndex].listLevel[levelIndex].listAction[actionIndex];
	
	var Id = gennerateId(groupIndex, sectionIndex, levelIndex, actionIndex);
	
	var strHTML = "<fieldset class=\"actionGroupBox\">";
	strHTML += "<legend>Action</legend>";
	
	var strId = Id;
	strHTML += "<div class=\"actionContent\">";
	if (document.getElementById("access_type").value=="2") {
		strHTML += "<image onclick=\"deleteAction(" + groupIndex + "," + sectionIndex + "," + levelIndex + "," + actionIndex + ");\" class=\"imageDeleteAction\" src=\"../image/delete.gif\"/>";
	}
	strHTML += "<table cellpadding=\"0\" cellspacing=\"0\">";
	strHTML += "<tr>";
	strHTML += "<td>Action Type</td>";
	strHTML += "<td>:&nbsp;" + createRadioControl(Id, act.actionType) + "</td>";
	strHTML += "</tr>";
	
	strId = LIST_USER_KEY + Id;
	strHTML += "<tr>";
	strHTML += "<td>PIC</td>";
	if (document.getElementById("access_type").value=="2") {
		strHTML += "<td>:&nbsp;" + createUserlist(strId) + "</td>";
	}else{
		//strHTML += "<td>:&nbsp;" + arrSecgrp[groupIndex].listWfSection[sectionIndex].listLevel[levelIndex].listAction[actionIndex].pic + "</td>";
		strHTML += "<td>:&nbsp;<label id=" + strId + "></label></td>";
	}
	strHTML += "</tr>";
	
	strId = RESPONSE_KEY + Id;
	strHTML += "<tr>";
	strHTML += "<td>Response Expire</td>";
	if (document.getElementById("access_type").value=="2") {
		strHTML += "<td>:&nbsp;" + createIntegerTextboxControl(strId, act.response, 1, 2) + "</td>";
	}else{
		strHTML += "<td>:&nbsp;" + act.response + "</td>";
	}
	strHTML += "</tr>";
	
	strId = CON1_KEY + Id;
	strHTML += "<tr>";
	strHTML += "<td>Other Condition</td>";
//	strHTML += "<td>:&nbsp;" + createTextboxControl(strId, act.condition1, 20, 50) + "</td>";
	if (document.getElementById("access_type").value=="2") {
		strHTML += "<td>:&nbsp;" + createCondition1(strId) + "</td>";
	}else{
		//strHTML += "<td>:&nbsp;" + arrSecgrp[groupIndex].listWfSection[sectionIndex].listLevel[levelIndex].listAction[actionIndex].condition1 + "</td>";
		strHTML += "<td>:&nbsp;<label id=" + strId + "></label></td>";
	}
	strHTML += "</tr>";
	
	strId = LIST_OPERATOR_KEY + Id;
	strHTML += "<tr>";
	strHTML += "<td>&nbsp;</td>";
	if (document.getElementById("access_type").value=="2") {
		strHTML += "<td>&nbsp;&nbsp;" + createOperatorList(strId) + "</td>";
	}else{
		//strHTML += "<td>&nbsp;&nbsp;" + arrSecgrp[groupIndex].listWfSection[sectionIndex].listLevel[levelIndex].listAction[actionIndex].condition2 + "</td>";
		strHTML += "<td>&nbsp;&nbsp;<label id=" + strId + "></label></td>";
	}
	strHTML += "</tr>";
	
	strId = CON3_KEY + Id;
	strHTML += "<tr>";
	strHTML += "<td>&nbsp;</td>";
	if (document.getElementById("access_type").value=="2") {
		strHTML += "<td>&nbsp;&nbsp;" + createTextboxControl(strId, act.condition3, 20, 50) + "</td>";
	}else{
		strHTML += "<td>&nbsp;&nbsp;" + act.condition3 + "</td>";
	}
	strHTML += "</tr>";
	
	strHTML += "</table>";
	strHTML += "</div>";
	strHTML += "</fieldset>";
	return strHTML;
}

// Create Level Control
function createLevelControl(groupIndex, sectionIndex, levelIndex){
	
	var strHTML = "<div class=\"levelHeader\">";
	strHTML += "Level " + (levelIndex + 1);
	if (document.getElementById("access_type").value=="2") {
		strHTML += "<image onclick=\"deleteLevel(" + groupIndex + "," + sectionIndex + "," + levelIndex + ", 0);\" class=\"imageDeleteLevel\" src=\"../image/delete.gif\"/>";	
	}
	strHTML += "</div>";
	strHTML += "<div class=\"levelContent\">";
	strHTML += createActionDiv(groupIndex, sectionIndex, levelIndex);
	strHTML += "</div>";
	
	return strHTML;
}

// Create Section Combobox
function createSectionCombobox(groupIndex, selectedSectionIndex){
	
	var selectedValue = arrSecgrp[groupIndex].listWfSection[selectedSectionIndex].sectionNo;
	var comboBoxId = "SECNAME" + gennerateId(groupIndex, selectedSectionIndex, null, null);
	var strHTML = "<select id=\"" + comboBoxId + "\">";
	
	var listLength = arrSecgrp[groupIndex].listSection.length;
	for (var j=0; j<listLength; j++){
		var optionValue = arrSecgrp[groupIndex].listSection[j].sectionNo;
		if (optionValue == selectedValue)
			strHTML += "<option selected value=\"" + optionValue + "\">";
		else
			strHTML += "<option value=\"" + optionValue + "\">";
		strHTML += arrSecgrp[groupIndex].listSection[j].sectionName;
		strHTML += "</option>";
	}
	strHTML += "</select>";
	
	return strHTML;
}

//Create Textbox Control
function createTextboxControl(strId, str, size, maxlength){
	var strHTML = "<input id=\"" + strId + "\" type=\"text\" size=\"" + size + "\" value=\"" + str + "\" maxlength=\"" + maxlength + "\">";
	strHTML += "</input>";

	return strHTML;
}

//Create IntegerTextBox Control
function createIntegerTextboxControl(strId, str, size, maxlength){
	var strHTML = "<input id=\"" + strId + "\" type=\"text\" size=\"" + size + "\" value=\"" + str + "\" maxlength=\"" + maxlength + "\" style=\"text-align: right\" >";
	strHTML += "</input>";

	return strHTML;
}

// Create Radio Control
function createRadioControl(Id, selectedValue){
	var cboActionType = document.getElementById("listActionType");
	var strId = ACTION_TYPE_KEY + Id;
	
	var strHTML = "";
	if (document.getElementById("access_type").value=="2") {
		for (var i=0; i<cboActionType.options.length; i++){
			var value = cboActionType.options[i].value;
			strHTML += "<input name=\"" + strId + "\" type=\"radio\" value=\"" + value + "\"";
			if (value == selectedValue){
				strHTML += "checked";
			}
			strHTML += ">" + cboActionType.options[i].text + "</input>";
		}
	}else{
		for (i=0; i<cboActionType.options.length; i++){
			value = cboActionType.options[i].value;
			if (value == selectedValue){
				strHTML += cboActionType.options[i].text;
			}			
		}
	}

	return strHTML;
}

// Create User List
function createUserlist(userListId){
	var strHTML = "";
	strHTML += "<select id=\"" + userListId + "\">";
	
	var listUser = document.getElementById("listUser");
	strHTML += listUser.innerHTML;

	strHTML += "</select>";

	return strHTML;
}

// Create Operator List
function createOperatorList(operatorListId){
	var strHTML = "";
	strHTML += "<select id=\"" + operatorListId + "\">";
	
	var listUser = document.getElementById("listOperator");
	strHTML += listUser.innerHTML;

	strHTML += "</select>";

	return strHTML;
}

//Create Condition1
function createCondition1(con1Id){
	var strHTML = "";
	strHTML += "<select id=\"" + con1Id + "\">";
	
	var listUser = document.getElementById("listcon1");
	strHTML += listUser.innerHTML;

	strHTML += "</select>";

	return strHTML;
}

// Generate Id for controls
function gennerateId(groupIndex, sectionIndex, levelIndex, actionIndex){
	var strId = "";
	strId += "G";
	strId += groupIndex;
	strId += "S";
	strId += sectionIndex;
	strId += "L";
	strId += levelIndex;
	strId += "A";
	strId += actionIndex;
	return strId;
}

// Delete an action
function deleteAction(groupIndex, sectionIndex, levelIndex, actionIndex){
	if (!confirmMessage(deleteMessage.replace("null", "Action User"))){
		return;
	}
	
	var actionLength = arrSecgrp[groupIndex].listWfSection[sectionIndex].listLevel[levelIndex].listAction.length;
	var act = null;
	
	for (var i=0; i<actionLength - 1; i++){
		if (i == actionIndex){
			for (var j = i+1; j<actionLength; j++){
				act = arrSecgrp[groupIndex].listWfSection[sectionIndex].listLevel[levelIndex].listAction[j];
				arrSecgrp[groupIndex].listWfSection[sectionIndex].listLevel[levelIndex].listAction[j-1] = act;
			}
		}
	}
	
	// Remove the last one
	arrSecgrp[groupIndex].listWfSection[sectionIndex].listLevel[levelIndex].listAction.length = actionLength - 1;

	// Check length of action list 
	if (actionLength == 1){
		deleteLevel(groupIndex, sectionIndex, levelIndex, 1);
	} else {
		actionLength = actionLength - 1;
		
		var divId = gennerateId(groupIndex, sectionIndex, levelIndex, null); 
		var actionDiv = document.getElementById(divId);
		actionDiv.innerHTML = "";
		
		var strHTML = "";
		for (var k=0; k<actionLength; k++){
			strHTML += createActionControl(groupIndex, sectionIndex, levelIndex, k);
		}
		
		actionDiv.innerHTML = strHTML;
		loadDataToScreen(groupIndex);
	}
}

// Delete a level
function deleteLevel(groupIndex, sectionIndex, levelIndex, deleteType){
	if (deleteType == 0){
		if (!confirmMessage(deleteMessage.replace("null", "Level"))){
			return;
		}
	}
	
	var levelLength = arrSecgrp[groupIndex].listWfSection[sectionIndex].listLevel.length;
	var lev = null;
	
	for (var i=0; i<levelLength - 1; i++){
		if (i == levelIndex){
			for (var j = i+1; j<levelLength; j++){
				lev = arrSecgrp[groupIndex].listWfSection[sectionIndex].listLevel[j];
				arrSecgrp[groupIndex].listWfSection[sectionIndex].listLevel[j-1] = lev;
			}
		}
	}
	
	// Remove the last one
	arrSecgrp[groupIndex].listWfSection[sectionIndex].listLevel.length = levelLength - 1;
	
	// Check length of action list 
	if (levelLength == 1){
		deleteSection(groupIndex, sectionIndex, 1);
	} else {
		levelLength = levelLength - 1;
		
		var divId = gennerateId(groupIndex, sectionIndex, null, null); 
		var levelDiv = document.getElementById(divId);
		levelDiv.innerHTML = "";
		
		var strHTML = "";
		for (var k=0; k<levelLength; k++){		
			strHTML += createLevelControl(groupIndex, sectionIndex, k);
		}
		
		levelDiv.innerHTML = strHTML;
		loadDataToScreen(groupIndex);
	}
}

// Delete a section
function deleteSection(groupIndex, sectionIndex, deleteType){
	if (deleteType == 0){
		if (!confirmMessage(deleteMessage.replace("null", "Section"))){
			return;
		}
	}
	
	var sectionLength = arrSecgrp[groupIndex].listWfSection.length;
	var sec = null;
	
	for (var i=0; i<sectionLength - 1; i++){
		if (i == sectionIndex){
			for (var j = i+1; j<sectionLength; j++){
				sec = arrSecgrp[groupIndex].listWfSection[j];
				arrSecgrp[groupIndex].listWfSection[j-1] = sec;
			}
		}
	}
	
	// Remove the last one
	arrSecgrp[groupIndex].listWfSection.length = sectionLength - 1;
	var sectionGroupId = arrSecgrp[groupIndex].sectionGroupId;
	
	var tabControl = document.getElementById("sectionsDiv"+sectionGroupId);
	tabControl.innerHTML = "";
	
	// Check length of action list 
	if (sectionLength == 1){
		addSection(sectionGroupId);
	} else {
		sectionLength = sectionLength - 1;

		var strHTML = "";
		
		for (var k=0; k<sectionLength;k++){
			// Add workflow section
			strHTML += createSectionControl(groupIndex, k);
		}
			
		tabControl.innerHTML = strHTML;
		loadDataToScreen(groupIndex);
	}
}

// Set selected value for comboboxes
function setValueForList(strId, value){
	var listControl = document.getElementById(strId);
	for (var i=0; i<listControl.options.length; i++){
		if (value == listControl.options[i].value){
			listControl.selectedIndex = i;
			return;
		}
	}
}

// Load data to screen
function loadDataToScreen(groupIndex){
	var Id = "";
	var strId = "";
	var value = "";
	
	for (var i=0; i<arrSecgrp[groupIndex].listWfSection.length; i++){
		for (var j=0; j<arrSecgrp[groupIndex].listWfSection[i].listLevel.length; j++){
			for (var k=0; k<arrSecgrp[groupIndex].listWfSection[i].listLevel[j].listAction.length; k++){
				Id = gennerateId(groupIndex, i, j, k); 
				
				strId = LIST_USER_KEY + Id;
				value = arrSecgrp[groupIndex].listWfSection[i].listLevel[j].listAction[k].pic;
				if (document.getElementById("access_type").value=="2") {				
					setValueForList(strId, value);
				}else{					
					var listUser = document.getElementById("listUser");
					for (var m=0; m<listUser.options.length; m++){
						if (value == listUser.options[m].value){
							var control = document.getElementById(strId);
							control.innerHTML = listUser.options[m].text;
						}
					}
				}
				
				strId = CON1_KEY + Id;
				value = arrSecgrp[groupIndex].listWfSection[i].listLevel[j].listAction[k].condition1;
				if (document.getElementById("access_type").value=="2") {				
					setValueForList(strId, value);
				}else{					
					var listCon1 = document.getElementById("listcon1");
					for (var n=0; n<listCon1.options.length; n++){
						if (value == listCon1.options[n].value){
							var control1 = document.getElementById(strId);
							if(value == ""){
								control1.innerHTML = "-";
							}else{
								control1.innerHTML = listCon1.options[n].text;
							}
						}
					}
				}
				
				strId = LIST_OPERATOR_KEY + Id;
				value = arrSecgrp[groupIndex].listWfSection[i].listLevel[j].listAction[k].condition2;
				if (document.getElementById("access_type").value=="2") {				
					setValueForList(strId, value);
				}else{					
					var listOperator = document.getElementById("listOperator");
					for (var l=0; l<listOperator.options.length; l++){
						if (value == listOperator.options[l].value){
							var control2 = document.getElementById(strId);
							if(value == ""){
								control2.innerHTML = "-";
							}else{
								control2.innerHTML = listOperator.options[l].text;
							}							
						}
					}
				}
			}
		}
	}
}

// Load data from screen
function loadDataFromScreen(groupIndex){
	var value = "";
	var Id = "";
	var strId = "";
	
	// For each section
	for (var i=0; i<arrSecgrp[groupIndex].listWfSection.length; i++){
		// Get Id
		Id = gennerateId(groupIndex, i, null, null);
		
		strId = "SECNAME" + Id;
		value = document.getElementById(strId).value;
		arrSecgrp[groupIndex].listWfSection[i].sectionNo = value;
		
		strId = "SEQ" + Id;
		value = document.getElementById(strId).value;
		arrSecgrp[groupIndex].listWfSection[i].sequence = value;
		
		
		for (var j=0; j<arrSecgrp[groupIndex].listWfSection[i].listLevel.length; j++){
			for (var k=0; k<arrSecgrp[groupIndex].listWfSection[i].listLevel[j].listAction.length; k++){
				Id = gennerateId(groupIndex, i, j, k); 
				
				strId = ACTION_TYPE_KEY + Id;
				arrSecgrp[groupIndex].listWfSection[i].listLevel[j].listAction[k].actionType = getRadioValue(strId);
				
				strId = LIST_USER_KEY + Id;
				value = document.getElementById(strId).value;
				arrSecgrp[groupIndex].listWfSection[i].listLevel[j].listAction[k].pic = value;
				
				strId = RESPONSE_KEY + Id;
				value = document.getElementById(strId).value;
				arrSecgrp[groupIndex].listWfSection[i].listLevel[j].listAction[k].response = value;
				
				strId = CON1_KEY + Id;
				value = setNullValue(document.getElementById(strId).value);
				arrSecgrp[groupIndex].listWfSection[i].listLevel[j].listAction[k].condition1 = value;
				
				strId = LIST_OPERATOR_KEY + Id;
				value = setNullValue(document.getElementById(strId).value);
				arrSecgrp[groupIndex].listWfSection[i].listLevel[j].listAction[k].condition2 = value;
			
				strId = CON3_KEY + Id;
				value = setNullValue(document.getElementById(strId).value);
				arrSecgrp[groupIndex].listWfSection[i].listLevel[j].listAction[k].condition3 = value;
			}
		}
	}
}

// Set value null
function setNullValue(value){
	if (isBlank(value))
		return " ";
	return value;
}

// Get radio value
function getRadioValue(radioName){
	var radioObj = document.forms[0].elements[radioName];
	
	if (radioObj.length == undefined)
		return "";
	
	for (var i=0; i<radioObj.length; i++){
		if (radioObj[i].checked){
			return radioObj[i].value;
		}
	}
	
	return "";
}

// Validation Function
function validateData(groupIndex){
	var value = "";
	var Id = "";
	var strId = "";
	
	// For each section
	for (var i=0; i<arrSecgrp[groupIndex].listWfSection.length; i++){
		// Get Id
		Id = gennerateId(groupIndex, i, null, null);
		
		strId = "SECNAME" + Id;
		value = document.getElementById(strId).value;
		if (isBlank(value)){
			message = "Item \"Section name\" : Mandatory Error. Please fill in the field.";
			return false;
		}
		if(i<arrSecgrp[groupIndex].listWfSection.length - 1) {
			for (var m=i+1; m<arrSecgrp[groupIndex].listWfSection.length; m++){
				// Get Id
				var nextId = gennerateId(groupIndex, m, null, null);
				
				strId = "SECNAME" + nextId;
				var nextValue = document.getElementById(strId).value;
				if (value==nextValue){
					message = "Only one Id Section per Id Group Section.";
					return false;
				}
			}			
		}

		strId = "SEQ" + Id;
		value = document.getElementById(strId).value;
		if (isBlank(value)){
			message = "Item \"Approval Sequence\" : Mandatory Error. Please fill in the field.";
			return false;
		}

		if (!isNumeric(value)){
			message = "Item \"Approval Sequence\" : Only numeric value is allowed.";
			return false;
		}
		var arrPic = new Array();
		var l = 0;
		for (var j=0; j<arrSecgrp[groupIndex].listWfSection[i].listLevel.length; j++){
			for (var k=0; k<arrSecgrp[groupIndex].listWfSection[i].listLevel[j].listAction.length; k++){
				Id = gennerateId(groupIndex, i, j, k); 
				
				strId = LIST_USER_KEY + Id;
				value = document.getElementById(strId).value;
				for (var n=0; n<arrPic.length; n++){
					if(value == arrPic[n]){
						message = "Item \"Section Name\" : Duplicate record. Please try again.";
						return false;
					}
				}
				arrPic[l] = value;
				l=l+1;
				if (isBlank(value)){
					message = "Item \"Pic\" : Mandatory Error. Please fill in the field.";
					return false;
				}
				
				strId = RESPONSE_KEY + Id;
				value = document.getElementById(strId).value;
				if (getRadioValue(ACTION_TYPE_KEY + Id) == "AA"){
					if (isBlank(value)){
						message = "Item \"Response Expire\" : Mandatory Error. Please fill in the field.";
						return false;
					}
					if (!isNumeric(value)){
						message = "Item \"Response Expire\" : Only numeric value is allowed.";
						return false;
					}
				}
				
				if (!checkCondition(Id)){
					message = "Item \"Other Condition\" : Data incomplete";
					return false;
				}
			}
		}
	}

	return true;
}

// Check 3 conditions in an action
function checkCondition(Id){
	var con1 = document.getElementById(CON1_KEY + Id).value;
	var con2 = document.getElementById(LIST_OPERATOR_KEY + Id).value;
	var con3 = document.getElementById(CON3_KEY + Id).value;
	var result = false;
	
	if (isBlank(con1) && isBlank(con2) && isBlank(con3)){		
		result = true;
	} else {
		result = false;
		
		if (!isBlank(con1) && !isBlank(con2) && !isBlank(con3)){
			result = true;
			
			if (!isAlphaNumeric(con1))
			result = false;
			
			if (!isAlphaNumeric(con3))
			result = false;
		}
	}
	
	return result;
}

// Check whether a value is blank
function isBlank(value){
	if (trim(value) == "")
		return true;
	return false;
}

// Check whether a value is numeric
function isNumeric(value){
	var regex=/^[0-9]+$/; //^[a-zA-z]+$/
	if(regex.test(value)){
		return true;
	} else {
		return false;
	}
}

//Check whether a value is alpha numeric
function isAlphaNumeric(value){
	var regex=/^[0-9A-Za-z]+$/; //^[a-zA-z]+$/
	if(regex.test(value)){
		return true;
	} else {
		return false;
	}
}

// Trim a string
function trim(str){
	return str.replace(/^\s+|\s+$/g, '');
}

// Reset page
function resetPage(){
	var listControl = document.getElementById("id_screen");
	listControl.selectedIndex = 0;
	document.getElementById("err_msg").style.display = "none";
	//document.getElementById("save_success").style.display = "none";
	//document.getElementById("message").setValue = "";
	// Clear results
	//var resultDiv = document.getElementById("searchResult");
	//if (resultDiv != null){
	//	resultDiv.innerHTML = "";
	//}
}

// Confirm message
//show confirm message 
function confirmMessage(message){
	var MsgBox = new messageBox();
	if (MsgBox.POPCFM(message) == MsgBox.YES_CONFIRM) {
	//if(confirm(message)){
		return true;
	}		
	return false;
}

function doChangeScreen(screenCbo){	
	document.getElementById("screen_desc").value = screenCbo.options[screenCbo.selectedIndex].text;
}