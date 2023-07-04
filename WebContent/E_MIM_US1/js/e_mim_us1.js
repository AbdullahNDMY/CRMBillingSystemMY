var ACTION_LOG="/E_MIM_US1/E_MIM_US2Blogic.do"
function e_mim_us1_popup(context, idBatch) {
	newwindow=window.open(context + ACTION_LOG + '?idIpassImpBatch=' + idBatch,'name','height=400,width=800,scrollbars=yes');
	if (window.focus) {newwindow.focus()}
}
function onload(){
	
	if ((document.forms[0].alertMsg.value != null) && (document.forms[0].alertMsg.value != "")){
		new messageBox().POPALT(document.forms[0].alertMsg.value);
	}
	var x = new Date(); 

	  with(x) 
	  { 
	    setMonth(getMonth()-1); 
	  } 

	  document.getElementById("closingMonth").value = x.getMonth() + 1;
	  document.getElementById("closingYear").value = x.getFullYear();
	  
	$("input[name='closingMonth']").spinbox({
		min: 1,
		max: 12
	});
	
	$("input[name='closingYear']").spinbox({
		min: 1800
	});	
}

