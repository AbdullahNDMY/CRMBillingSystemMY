

			$(document).ready(function(){
			$("a.tab").click(function () {
			$(".active").removeClass("active");
			$(this).addClass("active");
			$(".contact").slideUp("fast");
			var content_show = $(this).attr("title");
			$("#"+content_show).slideDown("fast");
			});
			});
		




function clickBillReferenceLink(billRef)
{
	//document.getElementById("transType").value=transType;
	document.getElementById("hypBankReference").value=billRef;
	//document.getElementById("viewMode").value="view";
	
	var button=	document.getElementById("forward_view"); 

	button.click();
}
function cbo_bank_change()
{
	var bankfullname=document.getElementById('cbo_bank_fullname').value;
	document.getElementById("bank_name_method").value=bankfullname;
	//alert(document.getElementById("bank_name_method").value);
	
}
function S01reset()
{
	document.getElementById('cbo_bank_fullname').value="";
	document.getElementById('bank_code').value="";
	
	
}

function exitS02()
{
	
	var button=	document.getElementById("forward_view"); 
	alert('aaaa');
	button.click();
		
}

function confirmDelete() {
	var msg;
	msg= "Are you sure you want to delete the data ? " ;
	var MsgBox = new messageBox();
	if (MsgBox.POPCFM(msg) == MsgBox.YES_CONFIRM) {
	//var agree=confirm(msg);
	//if (agree) {
		//return true ;
			
			var button=	document.getElementById("forward_delete"); 
			button.click();
	
		}
	
	}



function setbankfullname()
{	
	var bankfullname="";	
	var bankname=document.getElementById("tbxBankName").value;	
	var branchname=document.getElementById("tbxBranchName").value;	
	bankfullname=bankname+"-"+branchname;
	
	document.getElementById("lblBankFullName").value=bankfullname;
	
}