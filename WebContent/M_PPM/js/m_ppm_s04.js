function resetAct(){
	document.forms[0].tbxServiceName.value = "";
	document.forms[0].tbxServiceDescr.value = "";
	document.forms[0].cboCategory.selectedIndex = 0;
	document.forms[0].cboService.selectedIndex = 0;
	document.forms[0].cboService.disabled=true;
	document.forms[0].doSearch.value='N';
	document.forms[0].startIndex.value = 0;
	document.forms[0].submit();
}
function insertAct(context) {
	var selectedIdPlanGrp = $("input[name='idPlanGrp']:checked");
	
	var index = selectedIdPlanGrp.eq(0).val();

	var sendvales = $("input[name='"+index+"']");
	var planName = sendvales.eq(0).val();
	var idPlan = sendvales.eq(1).val();
	
	window.opener.setPlanInfo(idPlan,planName);
	window.close();
}
function planChecked() {
	var listIdPlanGrp = $("input[name='idPlanGrp']:checked");
	if(listIdPlanGrp.length > 0) {
		$("#insertBtn").attr("disabled", false);
	}
	else {
		$("#insertBtn").attr("disabled", true);
	}
}
function changeSerive(obj){
	if(obj.selectedIndex==0){
		document.forms[0].cboService.selectedIndex = 0;
		document.forms[0].cboService.disabled=true;
	}else{
		var path = $("#rootPath").val();
		var url = path+'/M_PPM/M_PPM_S04GetServiceAjax.do?cboCategory='+$(".cboCategory").val();
		$.ajax({
            type: 'POST',
            url: url,
            success: function(listService){
         	   $(".cboService").html(listService);
         	   document.forms[0].cboService.disabled=false;
         	   $(".cboService").attr("style","width:90%");
            }
          });
		//document.forms[0].doGetService.value="Y";
		//document.forms[0].startIndex.value = 0;
		//document.forms[0].submit();
	}
}