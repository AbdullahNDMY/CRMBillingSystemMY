function disable_calendar_button(div_id)
{ 
	var children = document.getElementById(div_id).getElementsByTagName('input');	   
	children[1].disabled=true;
}
function transfer_click(ctrl)
{ 
	document.getElementById("transferAll2").disabled = !ctrl.checked;
	document.getElementById("eff_date_from").disabled = !ctrl.checked;
	document.getElementById("eff_date_to").disabled = !ctrl.checked;
	
	//set update mode
	document.getElementById("update_mode").value=ctrl.checked;
	
	//detail
	var idx = document.getElementById("detail_inf").value; 
	var children, cf, ct;
	var childrent = document.getElementById("eff_date_tod").getElementsByTagName('input');	  
	var childrenf = document.getElementById("eff_date_fromd").getElementsByTagName('input');	
	if(ctrl.checked)
	{	
		for(i=1;i<=idx;i++)
		{
			children = document.getElementById(i).getElementsByTagName('select');
			children[0].disabled = true;
			
			cf = document.getElementById("f" + i).getElementsByTagName('input');
			cf[1].disabled = true;
			ct = document.getElementById("t" + i).getElementsByTagName('input');
			ct[1].disabled = true;
		}
		//set enable, disable for calendar button  
		childrent[1].disabled=false; 	 
		childrenf[1].disabled=false;
	}
	else
	{
		for(i=1;i<=idx;i++)
		{
			children = document.getElementById(i).getElementsByTagName('select');
			children[0].disabled = false; 
			cf = document.getElementById("f" + i).getElementsByTagName('input');
			cf[1].disabled = false;
			ct = document.getElementById("t" + i).getElementsByTagName('input');
			ct[1].disabled = false;
		}
		//set enable, disable for calendar button  
		childrent[1].disabled=true; 	 
		childrenf[1].disabled=true;
	} 
}
function save_data()
{
	var chk=document.getElementById("chkTransfer");
	document.getElementById("errorDiv").setAttribute("className","hide");
	document.getElementById("messageDiv").setAttribute("className","hide");
	document.getElementById("ERR1SC013").setAttribute("className","hide");
	document.getElementById("ERR1SC005_effectFrom").setAttribute("className","hide");
	document.getElementById("ERR1SC005_effect2").setAttribute("className","hide");
	document.getElementById("ERR1SC005_transfer").setAttribute("className","hide");
	document.getElementById("update_mode").value=chk.checked; 
	if(!chk.checked)
	{
		var idx = document.getElementById("detail_inf").value;
		var scr,sec,trf,dtf,dtt;
		var content="";
		for(i=1;i<=idx;i++)
		{
			//scr_
			scr = document.getElementById("scr_" + i).innerText;			
			//sec_
			sec = document.getElementById("sec_" + i).innerText;
			//des_
			//des = document.getElementById("des_" + i).value;
			//trf_
			var children = document.getElementById(i).getElementsByTagName('select');		
			trf = children[0].options[children[0].selectedIndex].value;
			//dtf_
			dtf = document.getElementById("dtf_" + i).value;
			//dtt_
			dtt = document.getElementById("dtt_" + i).value;
			//check valid data 
			if(trf=="")
			{
				document.getElementById("ERR1SC005_transfer").setAttribute("className","show");
				document.getElementById("trf_" + i).focus();
				return;
			}
			if(dtf=="")
			{
				document.getElementById("ERR1SC005_effectFrom").setAttribute("className","show");
				document.getElementById("dtf_" + i).focus();
				return;
			}
			if(dtt=="")
			{
				document.getElementById("ERR1SC005_effect2").setAttribute("className","show");
				dtt = document.getElementById("dtt_" + i).focus();
				return;
			}
			//From date < To date
			if(getDataFormat(dtf) > getDataFormat(dtt)){
				document.getElementById("ERR1SC013").setAttribute("className","show");
				return false;
			}
			//set content
			content = content + scr + "\n" + sec + "\n" + trf + "\n" + dtf + "\n" + dtt + "\n\n";
		}
		document.getElementById("detail_inf").value=content; 
	}
	else
	{
		trf = document.getElementById("transferAll2").value;	
		//dtf_
		dtf = document.getElementById("eff_date_from").value;
		//dtt_
		dtt = document.getElementById("eff_date_to").value;
		//check valid data 
		if(trf=="")
		{
			document.getElementById("ERR1SC005_transfer").setAttribute("className","show");
			document.getElementById("ERR1SC013").setAttribute("className","hide");
			document.getElementById("transferAll2").focus();
			return;
		}
		if(dtf=="")
		{
			document.getElementById("ERR1SC005_effectFrom").setAttribute("className","show");
			document.getElementById("eff_date_from").focus();
			return;
		}
		if(dtt=="")
		{
			document.getElementById("ERR1SC005_effect2").setAttribute("className","show");
			dtt = document.getElementById("eff_date_to").focus();
			return;
		}
		//From date < To date
		if(getDataFormat(dtf) > getDataFormat(dtt)){
			document.getElementById("ERR1SC013").setAttribute("className","show");
			document.getElementById("eff_date_from").focus();
			return false;
		}
		// add input data for new screen
		var idx = document.getElementById("detail_inf").value;
		var scr,sec,trf,dtf,dtt;
		var content="";
		if(idx && idx > 0)
			for(i=1;i<=idx;i++)
			{
				//scr_
				scr = document.getElementById("scr_" + i).innerText;			
				//sec_
				sec = document.getElementById("sec_" + i).innerText;
				//des_
				//des = document.getElementById("des_" + i).value;
				//trf_
				var children = document.getElementById(i).getElementsByTagName('select');		
				trf = children[0].options[children[0].selectedIndex].value;
				//dtf_
				dtf = document.getElementById("dtf_" + i).value;
				//dtt_
				dtt = document.getElementById("dtt_" + i).value;
				//set content
				content = content + scr + "\n" + sec + "\n" + trf + "\n" + dtf + "\n" + dtt + "\n\n";
			}
		document.getElementById("detail_inf").value=content;
	}
	var button=	document.getElementById("forward_save"); 
	button.click();	
}

function getDataFormat(str)
{
	var times = str.split("/");
	return new Date(times[2],times[1],times[0]);
}

function selectRoleName(cbo)
{	
	var strRole = cbo.options[cbo.selectedIndex].value;
	var cboRole = document.getElementById("cboRole"); 
	var current = cbo; 
	while (current.tagName !="TD")
	{
		current = current.parentElement;	
		//break;
	}
	//select_TransferName(cbo);
	for(i=0;i<cboRole.options.length; i++)
	{
		if(strRole==cboRole.options[i].value)
		{
			document.getElementById("role_" + current.id).innerText = cboRole.options[i].text;			
			return;
		}
	}
	document.getElementById("role_" + current.id).innerText = "";  
}
function search_data()
{
	var cbo = document.getElementById("cboUser");
	if(cbo.selectedIndex>0)
	{
		var id_user = cbo.options[cbo.selectedIndex].value;
		var user_name = cbo.options[cbo.selectedIndex].text;
		document.getElementById("id_user").value=id_user;
		document.getElementById("user_name").value=user_name;
		var button=	document.getElementById("forward_search"); 
		button.click();
	}
	else
	{
		cbo.focus();
		document.getElementById("showRs").setAttribute("className","hide");  
		document.getElementById("ERR1SC008").setAttribute("className","show");
	}
}
/*
 * Reset search condition
 * */
function resetSearchCondition()
{
	document.getElementById("ERR1SC013").setAttribute("className","hide"); 
	document.getElementById("ERR1SC008").setAttribute("className","hide");
	var cbo = document.getElementById("cboUser");
	cbo.selectedIndex = 0;
}