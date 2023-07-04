function clickEdit(context) {
	var idPlan = document.getElementById('idPlan').value;
	document.forms[0].action = context + '/M_PPM/M_PPM_S02eBL.do?idPlan=' + idPlan;
	document.forms[0].submit();
}
function clickDelete(context) {
	var inUsed = document.getElementById('inUsed').value;
	if(inUsed == 0 || inUsed == '0') {
		var MsgBox = new messageBox(context);
		if (MsgBox.POPDEL(document.getElementById("ERR4SC002").value) == MsgBox.YES_CONFIRM) {
			document.forms[0].action = context + '/M_PPM/M_PPM_S02DeleteBL.do?idPlan=' + 
				document.getElementById('idPlan').value;
			document.forms[0].submit();
		}
	}
	else {
		new messageBox().POPALT(document.getElementById("ERR1SC054").value);
	}
}
function clickExit(context) {
	document.forms[0].action = context + '/M_PPM/M_PPMS01_01BL.do';
	document.forms[0].submit();
}
//Add Plan
$(document).ready(function(){
    $('#buttonAdd').click(function(){
    	//get the first cboSvcLevel1&cboSvcLevel2's   value,then set the added plan
    	var service=$("div.service").eq(0);
    	var table =service.find(".serviceHeader");
        var cboSvcLevel1=table.find("tr").eq(1).children().eq(1).find(".cboSvcLevel1").val();
        var cboSvcLevel2=table.find("tr").eq(1).children().eq(2).find(".cboSvcLevel2").val();
		var idPlan = document.getElementById('idPlan').value;
		var cboBillCurrenc=$("#serviceCurrency").val();
        var contextPath = $("#contextPath").val();
        var queryString = "/M_PPM/M_PPM_S02AddPlanBL.do?idPlan="+idPlan+
        					"&cboSvcLevel1="+cboSvcLevel1+
        					"&cboSvcLevel2="+cboSvcLevel2;
        var url = contextPath + queryString;

        var width = window.screen.width*83/100;
        var height = window.screen.height*52/100;
        var left = Number((screen.availWidth/2) - (width/2));
        var top = Number((screen.availHeight/2) - (height/2));
        var offsetFeatures = "width=" + width + ",height=" + height +
                             ",left=" + left + ",top=" + top +
                             "screenX=" + left + ",screenY=" + top;
        var strFeatures= "toolbar=no, status=no, menubar=no,location=no,scrollbars=yes, resizable=yes" + "," + offsetFeatures;   
        var newwindow = window.open(url, null, strFeatures);   
        if (window.focus) { newwindow.focus(); }
        return false;
    });
});
//Add Option
$(document).ready(function(){
	$('#buttonOS').click(function(){
		//get the first cboSvcLevel1&cboSvcLevel2's   value,then set the added plan
		var service=$("div.service").eq(0);
		var table =service.find(".serviceHeader");
	    var cboSvcLevel1=table.find("tr").eq(1).children().eq(1).find(".cboSvcLevel1").val();
	    var cboSvcLevel2=table.find("tr").eq(1).children().eq(2).find(".cboSvcLevel2").val();
		var idPlan = document.getElementById('idPlan').value;
        var contextPath = $("#contextPath").val();
        var queryString = "/M_PPM/M_PPM_S02AddOptionBL.do?idPlan="+idPlan+
        					"&cboSvcLevel1="+cboSvcLevel1+
        					"&cboSvcLevel2="+cboSvcLevel2;
        var url = contextPath + queryString;

        var width = window.screen.width*83/100;
        var height = window.screen.height*52/100;
        var left = Number((screen.availWidth/2) - (width/2));
        var top = Number((screen.availHeight/2) - (height/2));
        var offsetFeatures = "width=" + width + ",height=" + height +
                             ",left=" + left + ",top=" + top +
                             "screenX=" + left + ",screenY=" + top;
        var strFeatures= "toolbar=no, status=no, menubar=no,location=no,scrollbars=yes, resizable=yes" + "," + offsetFeatures;   
        var newwindow = window.open(url, null, strFeatures);   
        if (window.focus) { newwindow.focus(); }
        return false;
    });
});