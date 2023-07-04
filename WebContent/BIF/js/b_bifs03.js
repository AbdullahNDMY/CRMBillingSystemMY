function imposeMaxLength(Object, MaxLen)
{
  return (Object.value.length <= MaxLen);
}

function link2CPM(path, idCustPlan) {
	window.location = path + "";
}
function setTelFax()
{
	var cboAttn = document.getElementById("cboAttn_temp");
	var selectedIndex = cboAttn.selectedIndex;
//	var text_cboAttn = cboAttn.options[selectedIndex].text;
	var val_cboAttn = cboAttn.options[selectedIndex].value;
	if(document.getElementById("cboAttn_temp").value == ""){
		var telFaxContactFlg = document.getElementById("telFaxContactFlg").value;
		if ("1"==telFaxContactFlg) {
			document.getElementById("telnumber").innerHTML = "";
			document.getElementById("tel").value = "";
			document.getElementById("faxnumber").innerHTML = "";
			document.getElementById("fax").value = "";
			document.getElementById("emailto").value = "";
			document.getElementById("emailcc").value = "";
		}
		
		document.getElementById("cboAttn").value = "";
	} else {
//		var valueSelected = document.getElementById("cboAttn_temp").value;
		for(var i = 0; i < cboAttn.options.length; i++) {
			if(cboAttn.options[i].value == val_cboAttn) {
				var tel = cboAttn.options[i].tel_no;
				var fax = cboAttn.options[i].fax_no;
				var emailto = cboAttn.options[i].email_to;
				var emailcc = cboAttn.options[i].email_cc;
				document.getElementById("telnumber").innerHTML = tel;
				document.getElementById("tel").value = tel;
				document.getElementById("faxnumber").innerHTML = fax;
				document.getElementById("fax").value = fax;
				document.getElementById("emailto").innerHTML = formatEmailAdr(emailto);
				document.getElementById("emailcc").innerHTML = formatEmailAdr(emailcc);
				var contact_name = cboAttn.options[i].contact_name;
				document.getElementById("cboAttn").value = contact_name;
				document.getElementById("emailToAdd").value = emailto;
				document.getElementById("emailCcAdd").value = emailcc;

				
				
			}
		}
//		document.getElementById("cboAttn").value = text_cboAttn;	
	}
	
}

function formatEmailAdr(emailAdr){
	var emailInnerHtml = "";
	if (emailAdr.indexOf(";") >= 0) {
		var emailAdrs = emailAdr.split(";");
		for(var j = 0; j < emailAdrs.length; j++) {
			if (j != emailAdrs.length - 1) {
				emailInnerHtml += emailAdrs[j] + ";" + "<br/>";
			} else {
				emailInnerHtml += emailAdrs[j];
			}
		}
	} else {
		emailInnerHtml = emailAdr;
	}
	return emailInnerHtml;
}