$(document).ready(function(){
    var cboCategory = $("select[name='cboCategory']").val();
    // #180 Start
    var service = $(".searchService");
    var plan = $("select[name='cboPlan']");
    var planDetail = $("select[name='cboPlanDetail']");
    service.combobox();
    plan.combobox();
    planDetail.combobox();
    $(".custom-combobox-input").val("- Please Select One -");
    // #180 End
    resetAllSel(cboCategory);
    
    $("#divBody").css("width",screen.width-270);
});
function resetAllSel(selVal){
    if(!selVal||selVal===""){
        $("select[name='cboService']").val("");
        $("select[name='cboService']").attr("disabled",true);
        
        $("select[name='cboPlan']").val("");
        $("select[name='cboPlan']").attr("disabled",true);
        
        $("select[name='cboPlanDetail']").val("");
        $("select[name='cboPlanDetail']").attr("disabled",true);
        
        // #180 Start
        $(".custom-combobox-input").attr("disabled", true);
		$(".ui-button").attr("disabled", true);
		// #180 End
    }else{
        $("select[name='cboService']").attr("disabled",false);
        $("select[name='cboPlan']").attr("disabled",false);
        $("select[name='cboPlanDetail']").attr("disabled",false);
        
        // #180 Start
        $(".custom-combobox-input").attr("disabled", false);
		$(".ui-button").attr("disabled", false);
		
		if ($("#hiddenService").val() != "") {
			$("#combInput2").val($("#hiddenService").val());
		}
		if ($("#hiddenPlan").val() != "") {
			$("#combInput3").val($("#hiddenPlan").val());
		}
		if ($("#hiddenPlanDetail").val() != "") {
			$("#combInput4").val($("#hiddenPlanDetail").val());
		}
		// #180 End
    }
}

function initPage() {
	if (parent.frame_menu.root == "menu") {
		parent.frame_menu.root = "";
		clickReset();
	}
}
function clickNew(url) {
	document.forms[0].action = url;
	document.forms[0].submit();
}
function clickReset() {
	document.forms[0].reset();
	document.forms[0].txtPlanName.value = '';
	document.forms[0].txtPlanDescription.value = '';
	document.forms[0].cboCustomerType.value = '';
	document.forms[0].cboCategory.value = '';
	document.forms[0].cboBillCurrency.value = '';
	$(".custom-combobox-input").val("- Please Select One -");
}
function searchClick() {
    showLoadingTipWindow();
	var startIndex = document.getElementById("startIndex");
	if(startIndex!=null && startIndex!=undefined) {
		startIndex.value="0";
	}
	// #180 Start
	if ($(".searchCategory").val() != "") {
		var inputBlank = "- Please Select One -";
		if ($("#combInput2").val() != inputBlank) {
			$("#hiddenService").val($("#combInput2").val());
		}else {
			$("#hiddenService").val("");
		}
		if ($("#combInput3").val() != inputBlank) {
			$("#hiddenPlan").val($("#combInput3").val());
		}else {
			$("#hiddenPlan").val("");
		}
		if ($("#combInput4").val() != inputBlank) {
			$("#hiddenPlanDetail").val($("#combInput4").val());
		}else {
			$("#hiddenPlanDetail").val("");
		}
	}
	// #180 End
}
function changeCategory(sel){
	$(".custom-combobox-input").val( "- Please Select One -" );
    if(!sel||sel.value===""){
        $("select[name='cboService']").val("");
        $("select[name='cboService']").attr("disabled",true);
        
        $("select[name='cboPlan']").val("");
        $("select[name='cboPlan']").attr("disabled",true);
        
        $("select[name='cboPlanDetail']").val("");
        $("select[name='cboPlanDetail']").attr("disabled",true);
        // #180 Start
        $(".custom-combobox-input").attr("disabled", true);
		$(".ui-button").attr("disabled", true);
		// #180 End
    }else{
        $.ajax({
            type:"POST",
            url:$("#contextPath").val()+"/M_PPM/M_PPM_S01CategoryChange.do",
            data:{
               "cboCategory" :sel.value
            },
            dataType:"json",
            success:function(data){
                var service = $("select[name='cboService']");
                var plan = $("select[name='cboPlan']");
                var planDetail = $("select[name='cboPlanDetail']");
                /*var blankOption = $("<option value=''>- Please Select One -</option>");
                
                service.empty().append(blankOption.clone(true)).val("");
                plan.empty().append(blankOption.clone(true)).val("");
                planDetail.empty().append(blankOption.clone(true)).val("");*/

                $(data.serviceList).each(function(){
                    service.append("<option value='"+this.serviceCd+"'>"+this.serviceName+"</option>");
                });
                
                $(data.planList).each(function(){
                    plan.append("<option value='"+this.planCd+"'>"+this.planName+"</option>");
                });
                
                $(data.planDetailList).each(function(){
                    planDetail.append("<option value='"+this.planDetailCd+"'>"+this.planDetailName+"</option>");
                });
                
                // #180 Start
                $(".custom-combobox-input").attr("disabled", false);
        		$(".ui-button").attr("disabled", false);
        		// #180 End
            },
            error:function(){
                $("select[name='cboService']").val("");
                $("select[name='cboService']").attr("disabled",true);
                
                $("select[name='cboPlan']").val("");
                $("select[name='cboPlan']").attr("disabled",true);
                
                $("select[name='cboPlanDetail']").val("");
                $("select[name='cboPlanDetail']").attr("disabled",true);
                // #180 Start
                $(".custom-combobox-input").attr("disabled", true);
        		$(".ui-button").attr("disabled", true);
        		// #180 End
            }
        });
    }
}