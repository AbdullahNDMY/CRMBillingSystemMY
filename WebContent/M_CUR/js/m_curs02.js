// fix trim() method for IE8
if(typeof String.prototype.trim !== 'function') {
  String.prototype.trim = function() {
    return this.replace(/^\s+|\s+$/g, ''); 
  }
}
var MODE_VIEW="view";
var MODE_EDIT="edit";
var MODE_NEW="new";
var mode=MODE_VIEW;
function init_data()
{
	//disable edit button
	document.getElementById("edit").style.display='none';
	document.getElementById("delete").style.display='none';
	//clear values
	document.getElementById("rate_to").value="";
	document.getElementById("rate_from").value="";
	mode=MODE_NEW;
}
function filter_access()
{
	document.getElementById("edit").style.display='none';
	document.getElementById("delete").style.display='none';
}
function duplicate_data() {
	document.getElementById("edit").style.display='none';
	document.getElementById("delete").style.display='none';
}
function disable_calendar_button(div_id,cur_code)
{ 
	var children = document.getElementById(div_id).getElementsByTagName('input');	   
	children[1].disabled=true;	
	document.getElementById("cboCurrency").disabled=true;
	document.getElementById("rate_from").disabled=true;
	document.getElementById("rate_to").disabled=true;
	document.getElementById("rate_date").disabled=true;
	//disable edit button
	document.getElementById("save").style.display='none';
	//set currency symbol
	var cboSymbol = document.getElementById("cboSymbol");  
	//select_TransferName(cbo);
	for(i=0;i<cboSymbol.options.length; i++)
	{
		if(cur_code==cboSymbol.options[i].value)
		{
			document.getElementById("currency_symbol").innerText = ": " + cboSymbol.options[i].text;			
			return;
		}
	}
	document.getElementById("currency_symbol").innerText = ": ";  
}
function setChangeDate()
{
	changeData="true";
}
function edit_data()
{
	document.getElementById("rate_from").disabled=false;
	document.getElementById("rate_to").disabled=false;
	document.getElementById("save").style.display='';
	document.getElementById("edit").style.display='none';
	document.getElementById("delete").style.display='none';
	document.getElementById("updated").value="1"; 
	mode=MODE_EDIT;
}
function update_data(action)
{   
	var rate_date = document.getElementById("rate_dateText").value;
	document.getElementById("rate_date").value = rate_date;
	var cur_code = document.getElementById("cboCurrency").value;
	document.getElementById("cur_code").value = cur_code;
	save_new_data(action);
}
function save_data()
{
	if(document.getElementById("updated").value != "0") 
	{
		update_data("update");
	}
	else 
	{
		save_new_data("save");
	}
}
function save_new_data(action)
{ 
	var ERR1SC005 = document.getElementById("errors.ERR1SC005").value;
	var ERR1SC006 = document.getElementById("errors.ERR1SC006").value;
	var ERR1SC012 = document.getElementById("errors.ERR1SC012").value;
	//check data
	var rate_date = document.getElementById("rate_date").value; 
	var cbo = document.getElementById("cboCurrency");
	var rate_fr = document.getElementById("rate_from").value.trim();
	var rate_to = document.getElementById("rate_to").value.trim();
	if(document.getElementById("ERR1SC006")!=undefined)
	{
		document.getElementById("ERR1SC006").setAttribute("className","hide");	
	}
	if(rate_date=="" || cbo.selectedIndex == 0 || rate_to == "" || rate_fr == "" || 
			(rate_to != "" && !isNumber(rate_to)) || (rate_fr != "" && !isNumber(rate_fr)))
	{
		var errRate_date = "";
		var errCboSelectedIndex = "";
		var errRate_to = "";
		var errRate_fr = "";
		if(rate_date=="")
			errRate_date = ERR1SC005.replace("replace", "Date") + "<br>";
		if(cbo.selectedIndex == 0)
			errCboSelectedIndex = ERR1SC005.replace("replace", "Currency") + "<br>";
		if(rate_to == "")
			errRate_to = ERR1SC005.replace("replace", "Convert to Forex") + "<br>";
		else if(!isNumber(rate_to)) 
			errRate_to = ERR1SC012.replace("replace", "Convert to Forex") + "<br>";	
		if(rate_fr == "")
			errRate_fr = ERR1SC005.replace("replace", "Convert from Forex") + "<br>";
		else if(!isNumber(rate_fr))
			errRate_fr = ERR1SC012.replace("replace", "Convert from Forex") + "<br>";
		document.getElementById("errMsg").innerHTML = errRate_date + errCboSelectedIndex + errRate_to + errRate_fr;
		return;
	}
	if(action == "save") {
		//document.getElementById("date_created").value = rate_date; 
		var button=	document.getElementById("forward_save"); 
		button.click();
	}
	if(action == "update") {
		var button=	document.getElementById("forward_edit"); 
		button.click();
	}
}
function isNumber(val) {
    var regx = /^[-+]?\d{0,4}([\.]\d{0,8})?$/;
    var valid = regx.test(val);
    if (valid) return val != 0;
    return false;
}
function delete_data(confirmDelete)
{ 
	var MsgBox = new messageBox();
	if (MsgBox.POPCFM(confirmDelete) == MsgBox.YES_CONFIRM) {
	//var cfirm = window.confirm(confirmDelete); 
	//if (cfirm) {
		//document.getElementById("date_created").value = document.getElementById("rate_date").value; 
		var button=	document.getElementById("forward_delete"); 
		button.click();
	}
}
function exit_data()
{	
	var button=	document.getElementById("forward_exit"); 
	if(mode == MODE_VIEW)
	{
		button.click();
	}else{
		var MsgBox = new messageBox();
		if (MsgBox.POPEXT(document.getElementById("hiddenMsgExitConfirmation").value) == MsgBox.YES_CONFIRM) {
			if(mode==MODE_NEW){
				button.click();
			}else if(mode==MODE_EDIT){
				document.getElementById("rate_from").disabled=true;
				document.getElementById("rate_to").disabled=true;
				document.getElementById("save").style.display='none';
				document.getElementById("edit").style.display='';
				document.getElementById("delete").style.display='';
				document.getElementById("updated").value="1"; 
				mode=MODE_VIEW;
			}
		}
		
	}	
		
	
}
function selectSymbol(cbo)
{ 
	var strSymbol = cbo.options[cbo.selectedIndex].value;
	var cboSymbol = document.getElementById("cboSymbol");  
	//select_TransferName(cbo);
	for(i=0;i<cboSymbol.options.length; i++)
	{
		if(strSymbol==cboSymbol.options[i].value)
		{
			document.getElementById("currency_symbol").innerText = ": " + cboSymbol.options[i].text;			
			return;
		}
	}
	document.getElementById("currency_symbol").innerText = ": ";  
}

function onlyDecNumbers(obj,evt) {
	var e = evt;
	if(window.event){ // IE
		var charCode = e.keyCode;
	} else if (e.which) { // Safari 4, Firefox 3.0.4
		var charCode = e.which;
	}
	if (charCode > 31 && (charCode < 48 || charCode > 57)){
		if(charCode == 46){// available for . 
			return true;
		}else{
			return false;
		}
	}else{
		if(48<=charCode&&57>=charCode){
			return decbit(obj);
		}
		else{
			return true;
		}
	}
}

function decbit(obj){
	var cursurPosition = -1;  
	if (obj.selectionStart) {//!IE
		cursurPosition = obj.selectionStart;
	}  
	else {//IE   
		var range = document.selection.createRange();  
		range.moveStart("character", -obj.value.length);  
		cursurPosition = range.text.length;  
	}
	var value = $.trim(obj.value);
	var arr = value.split(".");
	if(arr.length>1){
		if(arr[0].length>=4&&cursurPosition<=arr[0].length){
			return false;
		}
		if(arr[1].length>=8&&cursurPosition>arr[0].length){
			return false;
		}
	}
	else{
		if(arr[0].length>=4){
			return false;
		}
	}
	return true;
}