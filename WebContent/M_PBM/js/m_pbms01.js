	function do_search(){
		var combo = document.getElementById("cboBatchName");
		if (combo.selectedIndex > 0 ) {
			var button=	document.getElementById("forward_search");	
			document.getElementById("mode").setAttribute("value", "search");
			$('input[name=ip]').val('');
			button.click();
		}
		else{
			combo.focus();
			document.getElementById("ERR1SC038").setAttribute("className","show");
			if(document.getElementById("searchResult") != null){
				document.getElementById("searchResult").setAttribute("className","hide");
			}
		}
	}
	
	function do_reset(){	
		var button=	document.getElementById("forward_reset");
		document.getElementById("mode").setAttribute("value", "");
		button.click();	
	}
	
	function do_new()
	{	
		document.getElementById("mode").setAttribute("value", "new");
		var button=	document.getElementById("forward_search");			
		button.click();
	}
	function onPlanChange(){
		var button=	document.getElementById("forward_search");
		document.getElementById("mode").setAttribute("value", "new");
		button.click();
	}
	/*
	function do_save_new(batchID){
		var check = 0;
		var new_table = document.getElementById("new_table");
		var checkbox = new_table.getElementsByTagName("input");	
	
		var i=0	;
		var table_rows="";
		for(i=0;i<checkbox.length;++i){
		    if(checkbox[i].checked){
		        //get id_plan_grp	       
		        var row_name = "new_row_"+i;
		        if(batchID == "AD" || batchID == "DU"){
	                row_name = "new_row_"+i/2;    
	            }
		        var row = document.getElementById(row_name);
		        var td = row.getElementsByTagName("td");
		        if (batchID == "IP" ){
		        	var cboCodeValue = new_table.getElementsByTagName("select"); //store codevalue
		        	table_rows += td[1].innerHTML + "," + cboCodeValue[i].value +";";
		        }else if(batchID == "AD" || batchID == "DU"){
		        	var cbo = new_table.getElementsByTagName("select"); //store codevalue
		        	var index = i/2;
	                var txtUB = checkbox[i+1].value; // usagebase
	                table_rows += td[1].innerHTML + "," + txtUB + "," + cbo[index].value + ";";
		        }else{
		        	table_rows += td[1].innerHTML + ";";
		        }
		    }
		}
		//alert(table_rows);
		var button=	document.getElementById("forward_save");
		document.getElementById("new_data").setAttribute("value",table_rows);
		document.getElementById("mode").setAttribute("value", "new");
		if(check == true){
			button.click();   
		}else{
			
		}
		}
	}*/
	
	function do_save_new(batchID){
		var msg = '';
		var table_rows = "";
		var checkPair = '';
		var isCheckPair = false;
		var s= $('#new_table tr:gt(0)').each(function(){	
		    var s1 = $(this).find('td:nth-child(1) :input').is(':checked');
		    if(s1){
			    var aRow ='';		    
			    if(batchID != "IP"){//Dial up and ADSL
			    	aRow= $(this).find('td:nth-child(2) ').html() + ',' + $(this).find('td:nth-child(9) div input').val() + ',' + $(this).find('td:nth-child(9) div :nth-child(2)').val() ;
			    	if((jQuery.trim($(this).find('td:nth-child(3) ').html()) == 'sub') && (jQuery.trim($(this).find('td:nth-child(6) ').html()) == 'Base')){
			    		if($(this).find('td:nth-child(9) div input').val() == ''){			    			
			    			msg = 'Item "Usage Base" : Mandatory Error. Please fill in the field.';//ERR1SC005
			    			return false;
			    		}else{
			    		}
			    	}
			    }else{//iPass
			    	aRow= $(this).find('td:nth-child(2) ').html() + ',' + $(this).find('td:nth-child(9) div select').val() + ',' + $(this).find('td:nth-child(9) div :nth-child(2)').val() ;
				    if((jQuery.trim($(this).find('td:nth-child(3)').html()) == 'opt') && (jQuery.trim($(this).find('td:nth-child(6)').html()) == 'Excess')){
			    		isCheckPair = true;
			    		if($(this).find('td:nth-child(9) div select').val() != ''){
			    			checkPair += $(this).find('td:nth-child(9) div select').val();
			    		}
			    	}
			    }
			    table_rows+=aRow + ';';
		    }
		});
		if(batchID == "IP" && isCheckPair){
			if((checkPair.indexOf('1') == -1) || (checkPair.indexOf('2') == -1) || (checkPair.indexOf('3') == -1)){
				msg = "Please select atleast a combination of 1,2 and 3 for Code Value";//ERR1SC042
			}
		}
		var button=	document.getElementById("forward_save");
		document.getElementById("new_data").setAttribute("value",table_rows);
		document.getElementById("mode").setAttribute("value", "new");
		if(msg == ''){
			button.click();
		}else{
			//new messageBox().POPALT(msg);
			document.getElementById("errorMsg").innerHTML = msg;
		}
	}
	
	function do_save_edit(batchID){
		var allRow='';
		var checkPair = '';
		var uncheckFlag='';
		var msg = '';
		var isCheckPair = false;
		var s= $('#edit_table tr:gt(0)').each(function(){	
		    var s1 =  $(this).find('td:nth-child(1) :input').is(':checked');
		    var aRow ='';		    
		    if(s1){
		    	uncheckFlag=',';
		    }else{
		    	uncheckFlag='unchecked,';
		    }
		    if(batchID != "IP"){
		    	aRow= $(this).find('td:nth-child(2) ').html() + ',' + $(this).find('td:nth-child(9) div input').val() + ',' + $(this).find('td:nth-child(9) div :nth-child(2)').val() ;
		    	if((jQuery.trim($(this).find('td:nth-child(3) ').html()) == 'sub') && (jQuery.trim($(this).find('td:nth-child(6) ').html()) == 'Base')){
		    		if($(this).find('td:nth-child(9) div input').val() == ''){			    			
		    			msg = 'Item "Usage Base" : Mandatory Error. Please fill in the field.';//ERR1SC005
		    			return false;
		    		}else{
		    		}
		    	}
		    }else{
		    	aRow= $(this).find('td:nth-child(2) ').html() + ',' + $(this).find('td:nth-child(9) div select').val() + ',' + $(this).find('td:nth-child(9) div :nth-child(2)').val() ;
		    	if((jQuery.trim($(this).find('td:nth-child(3)').html()) == 'opt') && (jQuery.trim($(this).find('td:nth-child(6)').html()) == 'Excess')){
		    		isCheckPair = true;
		    		if($(this).find('td:nth-child(9) div select').val() != ''){
		    			if($(this).find('td:nth-child(1) :input').is(':checked'))
		    				checkPair += $(this).find('td:nth-child(9) div select').val();
		    		}
		    	}
		    }
            allRow+=uncheckFlag+aRow + ';';
		});
		if(batchID == "IP" && isCheckPair){
			if((checkPair.indexOf('1') == -1) || (checkPair.indexOf('2') == -1) || (checkPair.indexOf('3') == -1)){
				msg = "Please select atleast a combination of 1,2 and 3 for Code Value";//ERR1SC042
			}
		}
		var button=	document.getElementById("forward_save");
		document.getElementById("edit_data").setAttribute("value",allRow);
		document.getElementById("mode").setAttribute("value", "edit");
		if(msg == ''){
			button.click();
		}else{
			//new messageBox().POPALT(msg);
			document.getElementById("errorMsg").innerHTML = msg;
		}    
	}
	
	function do_edit()
	{
		var button=	document.getElementById("forward_search");	
		document.getElementById("mode").setAttribute("value", "edit");
		button.click();    
		
	}
	function do_delete(){
		//get message content
		var message = document.getElementById("ERR4SC002").value;
		var MsgBox = new messageBox();
		if (MsgBox.POPCFM(message) == MsgBox.YES_CONFIRM) {
		//var fRet;
		//fRet = confirm(message);
		//if (fRet ){
		    //start to delete
			var delete_button = document.getElementById("forward_delete");
			delete_button.click();
		}
	}
	
	function on_checkedChanged(batchID, table_name){
		if (batchID !="CC"){
			var new_table = document.getElementById(table_name);
			var checkbox = new_table.getElementsByTagName("input");		       
			var i=0	;	
			for(i=0;i<checkbox.length;++i){
			    if(checkbox[i].getAttribute("type")=="checkbox"){	    	
			       //get id_plan_grp
			       var col_name = "hide_col_"+i;
			       if(batchID == "AD" || batchID == "DU"){
			        	col_name = "hide_col_"+i/2;    
			       }
		           if (checkbox[i].checked){
		        	   $('#' + col_name).removeClass('hide');
		        	   $('#' + col_name).addClass('show');
		           }else{
		               $('#' + col_name).removeClass('show');
		        	   $('#' + col_name).addClass('hide');
		           }
			    }
			}
		}
	}
	function on_checkedChanged1(batchId, rowId){
		var s= $('#'+rowId+' td:nth-child(1) :input').is(':checked');
	
		//alert(s);
		//checkbox is checked show group	
		if(s){
		    if(batchId !='CC'){
		         $('#'+rowId+' td:nth-child(9) div').removeClass('hide');
		         $('#'+rowId+' td:nth-child(9) div').removeClass('show');
		    }
		}else{		
	        $('#'+rowId+' td:nth-child(9) div').removeClass('show');
		    $('#'+rowId+' td:nth-child(9) div').addClass('hide');
		}
	}
	function on_checkedChanged2(batchId, rowId, itemType, rateType){
		var s= $('#'+rowId+' td:nth-child(1) :input').is(':checked');
		if(s){
		    if((batchId =='DU' || batchId =='AD') && itemType=='S' && rateType=='BA'){
		         $('#'+rowId+' td:nth-child(9) div').removeClass('hide');
		         $('#'+rowId+' td:nth-child(9) div').removeClass('show');
		    }
		    if(batchId =='IP' && itemType=='O' && rateType=='EX'){
		         $('#'+rowId+' td:nth-child(9) div').removeClass('hide');
		         $('#'+rowId+' td:nth-child(9) div').removeClass('show');
		    }
		}else{		
	        $('#'+rowId+' td:nth-child(9) div').removeClass('show');
		    $('#'+rowId+' td:nth-child(9) div').addClass('hide');
		}
	}
$(document).ready(function(){
    var batchId = $("#cboBatchName").val();
    if(batchId!="MH") {
    var idPlanGrpExist = $("#idPlanGrpExist").val();
    if (idPlanGrpExist == "1") {
        $("#chkCheckAMA").attr("disabled",true);
        $("#cboOptSvcAMA").attr("disabled",true);
        
        $("#chkCheckAMQ").attr("disabled",true);
        $("#cboOptSvcAMQ").attr("disabled",true);
        
        $("#chkCheckVRS").attr("disabled",true);
        $("#cboOptSvcVRS").attr("disabled",true);
        
        $("#chkCheckASP").attr("disabled",true);
        $("#cboOptSvcASP").attr("disabled",true);
        
        $("#chkCheckJMG").attr("disabled",true);
        $("#cboOptSvcJMG").attr("disabled",true);
    }
    }
    
    
    $('#btnAddService').click(function(){
        var contextPath = $("#contextPath").val();
        var queryString = "/M_PPM/M_PPMS04Search.do?lblCustomerType=";
        var url = contextPath + queryString;

        var width = window.screen.width*80/100;
        var height = window.screen.height*80/100;
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

function setPlanInfo(idPlan, planName){
    $("#plan_id_new").val(idPlan);
    $("#plan_name").html(planName);
    
    onPlanChange();
}
