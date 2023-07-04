var QCSNO="QCSNO";
var QUONO="QUONO";
var BIFNO="BIFNO";
var BIFDN="BIFDN";
var BIFCN="BIFCN";
var INVNO="INVNO";
var DBTNO="DBTNO";
var CDTNO="CDTNO";
var NTINV="NTINV";
var RCPNO="RCPNO";
var SCPID="SCPID";
var CTMID="CTMID";
var BACNO="BACNO";
var SOANO="SOANO";
var HIDDEN = "hidden";
var UNHIDE = "unhide";
var YY_TYPE = "01";
var YYYY_TYPE = "02";
var YYYYMMDD_TYPE = "03";
var YYMMDD_TYPE = "04";
var YYY_TYPE = "07";
var YYMM_TYPE = "08";
var MM_TYPE = "09";
var RUNNING_TYPE = "05";
var PREFIX_TYPE = "06";
var MAX_CONTROLS = 10;
function submitForm(){
	//set event is view
	document.forms[0].event.value="forward_save";
	//submit form
	document.forms[0].submit();		
	//mainSubmit('save');
}
function onload(){
	var mode = document.getElementById("mode").value;
	if(mode!="READONLY"){
		lookUpElements(QCSNO);
		lookUpElements(QUONO);
		lookUpElements(BIFNO);
		lookUpElements(BIFDN);
		lookUpElements(BIFCN);
		lookUpElements(INVNO);
		lookUpElements(DBTNO);
		lookUpElements(CDTNO);
		lookUpElements(NTINV);
		lookUpElements(RCPNO);
		lookUpElements(SCPID);
		lookUpElements(CTMID);
		lookUpElements(BACNO);
		lookUpElements(SOANO);
	}
}
function lookUpElements(type){
	var status,combobox,textbox,selectedValueLen,classCbx,classTxt,posTextbox,pos,flag,resetNoComboBox,resetNoComboxClassName;
	flag=false;
	pos=-1;
	for(var i=0;i<MAX_CONTROLS;i++){
		//reset postion of the first hidden couple 
		
		//get the status 
		//when status = 0 -> unhide couple of combobox and textbox
		//status = 1 -> continue to check the state of combobox and textbox
		status = document.getElementById(type + "[" + i + "].status");
		combobox = document.getElementById(type + "[" + i + "].type_val");
		textbox = document.getElementById(type + "[" + i + "].value");
		selectedValueLen = document.getElementById(type + "[" + i + "].init_val");
		resetNoComboBox = document.getElementById(type + "[" + i + "].reset_no");
		if(status.value=="0"){
			flag=true;
			//visible combobox
			classCbx=UNHIDE;
			if(combobox.value==RUNNING_TYPE){
				resetNoComboxClassName = UNHIDE;
			} else {
				resetNoComboxClassName = HIDDEN;
			}
			//check textbox state
			if(combobox.value==PREFIX_TYPE || combobox.value==RUNNING_TYPE){
				//visible textbox in case combobox is prefix or running no
				classTxt=UNHIDE;
			}else{
				//hidden textbox
				classTxt=HIDDEN;
			}
		}else{
			//hidden combobox and textbox
			classCbx=HIDDEN;
			classTxt=HIDDEN;
			resetNoComboxClassName = HIDDEN;
			//get the position of the first hidden couple 
			if(pos==-1){
				pos=i;
			}
		}
		//set class name
		combobox.className = classCbx;
		//set class name
		textbox.className = classTxt;
		//reset no combo Box
		resetNoComboBox.className = resetNoComboxClassName;
		if(resetNoComboxClassName!=HIDDEN){
			initResetNoComboBox(type, resetNoComboBox);
		}
		//1:set label of code type
		//2:set text length of selected combobox
		selectedValueLen.value = document.getElementById(type).innerText + ";" + combobox.options[combobox.selectedIndex].text;
	}
	if(pos==-1 && flag==false)
	{
		pos=0;
	}else if(pos==-1 && flag==true){
		pos=MAX_CONTROLS;
	}
	//set position of the first hidden couple
	posTextbox = document.getElementById(type + "Pos");
	posTextbox.value = pos;
}

function initResetNoComboBox(type, resetNoComboBox){
	var existTypeArr = getExistTypeValue(type);
	//01 is exist flag
	var YY_TYPEExitFlg = getTypeValueIsInExistTypeArr(existTypeArr, YY_TYPE);
	//02 is exist flag
	var YYYY_TYPEExitFlg = getTypeValueIsInExistTypeArr(existTypeArr, YYYY_TYPE);
	//03 is exist flag
	var YYYYMMDD_TYPEExitFlg = getTypeValueIsInExistTypeArr(existTypeArr, YYYYMMDD_TYPE);
	//04 is exist flag
	var YYMMDD_TYPEExitFlg = getTypeValueIsInExistTypeArr(existTypeArr, YYMMDD_TYPE);
	//07 is exist flag
	var YYY_TYPEExitFlg = getTypeValueIsInExistTypeArr(existTypeArr, YYY_TYPE);
	//08 is exist flag
	var YYMM_TYPEExitFlg = getTypeValueIsInExistTypeArr(existTypeArr, YYMM_TYPE);
	//09 is exist flag
	var MM_TYPEExitFlg = getTypeValueIsInExistTypeArr(existTypeArr, MM_TYPE);
	
	//01,02,03,04,07,08,09 not exist
	if(!YY_TYPEExitFlg&&!YYYY_TYPEExitFlg&&!YYYYMMDD_TYPEExitFlg&&!YYMMDD_TYPEExitFlg&&!YYY_TYPEExitFlg&&!YYMM_TYPEExitFlg&&!MM_TYPEExitFlg){
		resetNoComboBox.disabled=true;
		resetNoComboBox.options[0].selected=true;
	} else{
		resetNoComboBox.disabled=false;
		var hidResetNoOption = document.getElementById("hidResetNoOption");
		var preResetNoComboBox = resetNoComboBox.value;
		resetNoComboBox.options.remove(1);
		resetNoComboBox.options.remove(1);
		//only 01 exist remove 2. Reset by Month
		if(YY_TYPEExitFlg&&!YYYY_TYPEExitFlg&&!YYYYMMDD_TYPEExitFlg&&!YYMMDD_TYPEExitFlg&&!YYY_TYPEExitFlg&&!YYMM_TYPEExitFlg&&!MM_TYPEExitFlg){
			resetNoComboBox.options.add(new Option(hidResetNoOption.options[1].text,hidResetNoOption.options[1].value));
			if(preResetNoComboBox==hidResetNoOption.options[1].value){
				resetNoComboBox.options[1].selected=true;
			}
		} else if(!YY_TYPEExitFlg&&YYYY_TYPEExitFlg&&!YYYYMMDD_TYPEExitFlg&&!YYMMDD_TYPEExitFlg&&!YYY_TYPEExitFlg&&!YYMM_TYPEExitFlg&&!MM_TYPEExitFlg){
			//only 02 exist remove 2. Reset by Month
			resetNoComboBox.options.add(new Option(hidResetNoOption.options[1].text,hidResetNoOption.options[1].value));
			if(preResetNoComboBox==hidResetNoOption.options[1].value){
				resetNoComboBox.options[1].selected=true;
			}
		} else if(!YY_TYPEExitFlg&&!YYYY_TYPEExitFlg&&!YYYYMMDD_TYPEExitFlg&&!YYMMDD_TYPEExitFlg&&YYY_TYPEExitFlg&&!YYMM_TYPEExitFlg&&!MM_TYPEExitFlg){
			//only 07 exist remove 2. Reset by Month
			resetNoComboBox.options.add(new Option(hidResetNoOption.options[1].text,hidResetNoOption.options[1].value));
			if(preResetNoComboBox==hidResetNoOption.options[1].value){
				resetNoComboBox.options[1].selected=true;
			}
		} else if(YY_TYPEExitFlg&&YYYY_TYPEExitFlg&&!YYYYMMDD_TYPEExitFlg&&!YYMMDD_TYPEExitFlg&&!YYY_TYPEExitFlg&&!YYMM_TYPEExitFlg&&!MM_TYPEExitFlg){
			//only 01 and 02 exist remove 2. Reset by Month
			resetNoComboBox.options.add(new Option(hidResetNoOption.options[1].text,hidResetNoOption.options[1].value));
			if(preResetNoComboBox==hidResetNoOption.options[1].value){
				resetNoComboBox.options[1].selected=true;
			}
		} else if(YY_TYPEExitFlg&&!YYYY_TYPEExitFlg&&!YYYYMMDD_TYPEExitFlg&&!YYMMDD_TYPEExitFlg&&YYY_TYPEExitFlg&&!YYMM_TYPEExitFlg&&!MM_TYPEExitFlg){
			//only 01 and 07 exist remove 2. Reset by Month
			resetNoComboBox.options.add(new Option(hidResetNoOption.options[1].text,hidResetNoOption.options[1].value));
			if(preResetNoComboBox==hidResetNoOption.options[1].value){
				resetNoComboBox.options[1].selected=true;
			}
		} else if(!YY_TYPEExitFlg&&YYYY_TYPEExitFlg&&!YYYYMMDD_TYPEExitFlg&&!YYMMDD_TYPEExitFlg&&YYY_TYPEExitFlg&&!YYMM_TYPEExitFlg&&!MM_TYPEExitFlg){
			//only 02 and 07 exist remove 2. Reset by Month
			resetNoComboBox.options.add(new Option(hidResetNoOption.options[1].text,hidResetNoOption.options[1].value));
			if(preResetNoComboBox==hidResetNoOption.options[1].value){
				resetNoComboBox.options[1].selected=true;
			}
		} else if(!YY_TYPEExitFlg&&!YYYY_TYPEExitFlg&&!YYYYMMDD_TYPEExitFlg&&!YYMMDD_TYPEExitFlg&&!YYY_TYPEExitFlg&&!YYMM_TYPEExitFlg&&MM_TYPEExitFlg){
			//only 09 exist remove 1. Reset by Year
			resetNoComboBox.options.add(new Option(hidResetNoOption.options[2].text,hidResetNoOption.options[2].value));
			if(preResetNoComboBox==hidResetNoOption.options[2].value){
				resetNoComboBox.options[1].selected=true;
			}
		} else {
			resetNoComboBox.options.add(new Option(hidResetNoOption.options[1].text,hidResetNoOption.options[1].value));
			resetNoComboBox.options.add(new Option(hidResetNoOption.options[2].text,hidResetNoOption.options[2].value));
			if(preResetNoComboBox==hidResetNoOption.options[1].value){
				resetNoComboBox.options[1].selected=true;
			} else if(preResetNoComboBox==hidResetNoOption.options[2].value){
				resetNoComboBox.options[2].selected=true;
			}
		}
	}
}
/**
 * get exist type value
 */
function getExistTypeValue(type){
	var status;
	var combobox;
	var existTypeArr = new Array();
	var j = 0;
	for(var i=0;i<MAX_CONTROLS;i++){
		status = document.getElementById(type + "[" + i + "].status");
		if(status.value=="0"){
			combobox = document.getElementById(type + "[" + i + "].type_val");
			existTypeArr[j] = combobox.value;
			j++;
		}
	}
	return existTypeArr;
}
function getTypeValueIsInExistTypeArr(existTypeArr, typeValue){
	if(existTypeArr!=null&&existTypeArr.length!=0) {
		for(var i=0;i<existTypeArr.length;i++){
			if(typeValue==existTypeArr[i]){
				return true;
			}
		}
	}
	return false;
}

function addCodeValue(type){
	var pos=getPosition(type);
	//check condition to allow add new
	if (pos==MAX_CONTROLS){
		return;
	}
	//visible combobox at getting position
	var combobox = document.getElementById(type + "[" + pos + "].type_val");
	combobox.className=UNHIDE;
	//clear content
	clearContent(type + "[" + pos + "].type_val",type + "[" + pos + "].value");
	//set status
	setStatus(type + "[" + pos + "].status","0");
	//increase position of the first hidden couple
	pos=parseInt(pos,10)+1;
	setPosition(type,pos);
}
function removeCodeValue(type){
	//get the position of the first hidden couple
	var pos=getPosition(type);
	//set position to remove
	pos=parseInt(pos,10)-1;
	//exit when without control to remove
	if(pos<=0){
		return;
	}
	//remove
	document.getElementById(type + "[" + pos + "].type_val").className=HIDDEN;
	document.getElementById(type + "[" + pos + "].value").className=HIDDEN;
	document.getElementById(type + "[" + pos + "].reset_no").className=HIDDEN;
	//clear content
	clearContent(type + "[" + pos + "].type_val",type + "[" + pos + "].value");
	//set status
	setStatus(type + "[" + pos + "].status","1");
	//decrease position of the first hidden couple
	setPosition(type,pos);
	
	for(var i=0;i<MAX_CONTROLS;i++){
		var status = document.getElementById(type + "[" + i + "].status");
		if(status.value=="0"){
			var typeCombobox = document.getElementById(type + "[" + i + "].type_val");
			var eachResetNoComboBox = document.getElementById(type + "[" + i + "].reset_no");
			//selected Running No.
			if(typeCombobox.value==RUNNING_TYPE){
				initResetNoComboBox(type, eachResetNoComboBox);
			}
		}
	}
}
function changeCombobox(obj,type){
	//get name of selected group 
	var name = obj.name.substr(0,obj.name.indexOf("."));
	//get textbox object
	var textbox = document.getElementById(name + ".value");
	//get reset_no combo Box object
	var resetNoComboBox = document.getElementById(name + ".reset_no");
	//store text length of selected combobox into init_val object
	var selectedValueLen = document.getElementById(name + ".init_val");
	var resetNoComboxClassName;
	//reset textbox
	textbox.value = "";
	resetNoComboBox.value="0";
	//1:set label of code type
	//2:set text length of selected combobox
	selectedValueLen.value = document.getElementById(type).innerText + ";" + obj.options[obj.selectedIndex].text;
	
	if(obj.value==RUNNING_TYPE) {
		resetNoComboxClassName = UNHIDE;
	} else {
		resetNoComboxClassName = HIDDEN;
	} 
	//reset no combo Box
	resetNoComboBox.className = resetNoComboxClassName;
	
	for(var i=0;i<MAX_CONTROLS;i++){
		var status = document.getElementById(type + "[" + i + "].status");
		if(status.value=="0"){
			var typeCombobox = document.getElementById(type + "[" + i + "].type_val");
			var eachResetNoComboBox = document.getElementById(type + "[" + i + "].reset_no");
			//selected Running No.
			if(typeCombobox.value==RUNNING_TYPE){
				initResetNoComboBox(type, eachResetNoComboBox);
				resetNoComboBox.options[0].selected = true;
			}
		}
	}
	
	if(obj.value==PREFIX_TYPE || obj.value==RUNNING_TYPE){
		//visible textbox
		textbox.className = UNHIDE;
		return;
	}
	//hidden textbox
	textbox.className = HIDDEN;
}
function clearContent(combobox,textbox){
	document.getElementById(combobox).value="";
	document.getElementById(textbox).value="";
}
function setStatus(obj,value){
	document.getElementById(obj).value = value;
}
function getPosition(type){
	var status = document.getElementById(type + "Pos").value;
	return status;
}
function setPosition(type,value){
	document.getElementById(type + "Pos").value = value; 
}