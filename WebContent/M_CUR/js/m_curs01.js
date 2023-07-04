function get_currency_name(code, id_td)
{
	var cboSymbol = document.getElementById("cur_code_search");   
	for(i=0;i<cboSymbol.options.length; i++)
	{
		if(code==cboSymbol.options[i].value)
		{ 		 
			document.getElementById(id_td).innerText = cboSymbol.options[i].text;
			return;
		}
	} 
}
function edit_date(rate_date,cur_code)
{
	document.getElementById("cur_code").value=cur_code;
	document.getElementById("rate_date").value=rate_date;
	var button=	document.getElementById("forward_edit"); 
	button.click();	
}
function search_data()
{
	document.getElementById("ERR1SC013").setAttribute("className","hide");		 
	//dtf_
	dtf = document.getElementById("datefrom").value; 
	//dtt_
	dtt = document.getElementById("dateto").value;  

	if(getDate(dtf) > getDate(dtt)){ 
		document.getElementById("ERR1SC013").setAttribute("className","show");		
		return false;
	}
	showLoadingTipWindow();
	document.getElementById("clickFlg").value = "search";
	var button=	document.getElementById("forward_search"); 
	button.click();
}

function getDate(dE) {
	var arrDate = dE.split("/");//dd/MM/yyyy
	return  new Date(arrDate[2], arrDate[1]-1, arrDate[0]);//yyyy/MM/dd
}

function resetClick(){
	reset_data();
	var button=	document.getElementById("forward_reset"); 
	button.click();
}

function reset_data()
{
	document.getElementById("datefrom").value="";
	document.getElementById("dateto").value="";
	var cbo = document.getElementById("cur_code_search");
	cbo.selectedIndex = 0;
}
function new_data()
{
	document.getElementById("cur_code").value="";
	document.getElementById("rate_date").value="";
	var button=	document.getElementById("forward_new"); 
	button.click();
}