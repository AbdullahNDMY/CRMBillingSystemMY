var isSubmitted = false;
var totalRow;
var totalColumn;
var resultTableWidth;
var serviceRow;
var previousRowCount = 0;

function init()
{
//	totalRow = $('table[class=listTable]').find('tr[title=dataRow]').length;
	totalRow = $('table[id=data]').find('tr[class=tRow]').length;
	totalColumn = $('table[class=listTable]').find('th[title=header]').length;
	resultTableWidth = 30 + 160 + 80*totalColumn;
	$('#labelDiv').css('width', $('#subHeader').attr('clientWidth')-$('#serviceTypeTable').attr('clientWidth'));
	$('#dataDiv').css('width', $('#subHeader').attr('clientWidth')-$('#serviceTypeTable').attr('clientWidth'));
	serviceRow = parseInt($('#serviceRow').val());
	if (isNaN(serviceRow))
		serviceRow = 10;
	var lower = totalRow;
	if (serviceRow < lower)
		lower = serviceRow;
	$('#serviceDiv').css('height', lower*31+20-20);
	$('#dataDiv').css('height', lower*31+20);
	
//	previousRowCount = $('table[class=listTable]').find('tr[title=dataRow]').length;
	previousRowCount = totalRow;
}

function addItem(){
    $('.error').html('');
    $('.message').html('');

    var table = $(".itemTable").children("tbody");
    if(!table[0]){
        table = $(".itemTable");
    }
    
    var row = '<tr>';
        row +=  '<td><div class="deleteX" onclick="deleteItem(this)">X</div></td>';
        row +=  '<td><input type="hidden" name="" value=""/>'+'<input type="text" name="" maxlength="100" value="" style="width:100%" /></td>';
        row +=  '</tr>';
    table.append(row);
    reCalcIndex();
}

function addTaxItem(){
	$('.error').html('');
    $('.message').html('');
    var table = $("#taxTable").children("tbody");
    if(!table[0]){
        table = $("#taxTable");
    }
    var row = '<tr>';
    	row +=  '<td><div class="deleteX" onclick="deleteTaxItem(this)">X</div></td>';
    	row +=  '<td><input type="hidden" name="" value=""/>'+'<input type="text" name="" maxlength="10" value="" style="width:100%;text-align: left" /></td>';
    	row +=  '<td><input type="hidden" name="" value=""/>'+'<input type="text" name=""  value="0" style="width:95%" Class="taxrate" /></td>';
    	row +=  '<td><input type="hidden" name="" value=""/>'+'<input type="text" name=""  value="" style="width:95%" Class="accountCode" /></td>';
    	row +=  '<td><input type="hidden" name="" value=""/>'+'<input type="text" name="" maxlength="30" value="" style="width:100%" /></td>';
    	row +=  '<td><input type="hidden" name="" value=""/>'+'<input type="text" name="" maxlength="300" value="" style="width:100%" /></td>';
    	row +=  '</tr>';
    table.append(row);
    reCalcTaxIndex();
}

function addCustomerItem(){	
	$('.error').html('');
    $('.message').html('');
    var table = $("#customerTable").children("tbody");
    if(!table[0]){
        table = $("#customerTable");
    }
    var row = '<tr>';
    	row +=  '<td><div class="deleteX" onclick="deleteCustomerItem(this)">X</div></td>';
    	if($('.CashBookTextBox').val() == "EU" ||$('.CashBookTextBox').val() == "CC" |$('.CashBookTextBox').val() == "CS" |$('.CashBookTextBox').val() == "CT" ){
    		row +=  '<td><input type="hidden" name="" value=""/>'+'<input type="text" name="" maxlength="1" value="" style="width:100%;text-align: left" /></td>';
    	}else if($('.CashBookTextBox').val() == "PR"){
    		row +=  '<td><input type="hidden" name="" value=""/>'+'<input type="text" name="" maxlength="8" value="" style="width:100%;text-align: left" /></td>';
		}else if($('.CashBookTextBox').val() == "JN"){
    		row +=  '<td><input type="hidden" name="" value=""/>'+'<input type="text" name="" maxlength="5" value="" style="width:100%;text-align: left" /></td>';
		}else {
			row +=  '<td><input type="hidden" name="" value=""/>'+'<input type="text" name="" maxlength="10" value="" style="width:100%;text-align: left" /></td>';
		}
    	if($('.CashBookTextBox').val() == "JN"){
       	    row +=  '<td><input type="hidden" name="" value=""/>'+'<input type="text" name=""  maxlength="10" value="" style="width:100%" /></td>';
    	}else
    	{
    		row +=  '<td><input type="hidden" name="" value=""/>'+'<input type="text" name=""  maxlength="30" value="" style="width:100%" /></td>';
    	}
    	/*Add by Jing Start*/
    	if($('.CashBookTextBox').val() == "RT"){
    		row +=  '<td><select name="" id="cmbEndUser" style="width:150px;">' + $('#cmbEndUser').html() + '</select></td>';
        	row +=  '<td><select name="" id="cmbModeType" style="width:150px;">' + $('#cmbModeType').html() + '</select></td>';			  		
    	}
    	/*Add by Jing End*/
    	
    	/*Add by MiffyAn Start*/
    	if($('.CashBookTextBox').val() == "PR"){
    		row +=  '<td><select name="" id="category" style="width:110px;">' + $('#localcCtegory').html() + '</select></td>';
    	}
    	if($('.CashBookTextBox').val() == "CG"){
    		row += '<td><input type="hidden" name="" value=""/>'+'<input type="text" name=""  maxlength="7" value="" style="width:100%" /></td>';
    	}
    	/*Add by MiffyAn End*/
    	row +=  '</tr>';                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
	table.append(row);
	reCalcCustomerIndex();
}

function deleteItem(self){
    $('.error').html('');
    $('.message').html('');

    var MsgBox = new messageBox();
    if (MsgBox.POPDEL($('#hiddenMsgDeleteConfirmation').val()) == MsgBox.YES_CONFIRM) {
        $(self).parent().parent().remove();
    }
    reCalcIndex();
}

function deleteCustomerItem(self){
    $('.error').html('');
    $('.message').html('');

    var MsgBox = new messageBox();
    if (MsgBox.POPDEL($('#hiddenMsgDeleteConfirmation').val()) == MsgBox.YES_CONFIRM) {
        $(self).parent().parent().remove();
    }
    reCalcCustomerIndex();
}

function deleteTaxItem(self){
    $('.error').html('');
    $('.message').html('');

    var MsgBox = new messageBox();
    if (MsgBox.POPDEL($('#hiddenMsgDeleteConfirmation').val()) == MsgBox.YES_CONFIRM) {
        $(self).parent().parent().remove();
    }
    reCalcTaxIndex();
}

function reCalcIndex(){
    var index=-2;
    $(".itemTable tr").each(function(n){
        index++;
        if(index>=0){
            $(this).children().eq(0).children("input[type='hidden']").attr("name","listResult["+index+"].isUsed");
            $(this).children().eq(1).children("input[type='hidden']").eq(0).attr("name","listResult["+index+"].itemId");
            $(this).children().eq(1).children("input[type='text']").attr("name","listResult["+index+"].itemName");
        }
    });
}

function reCalcTaxIndex(){
	var index=-2;
    $("#taxTable tr").each(function(n){
        index++;
        if(index>=0){
            $(this).children().eq(0).children("input[type='hidden']").attr("name","listResult["+index+"].isTaxUsed");
            $(this).children().eq(1).children("input[type='hidden']").eq(0).attr("name","listResult["+index+"].taxId");
            $(this).children().eq(1).children("input[type='text']").attr("name","listResult["+index+"].taxCode");
            $(this).children().eq(2).children("input[type='text']").attr("name","listResult["+index+"].taxRate");
            $(this).children().eq(3).children("input[type='text']").attr("name","listResult["+index+"].accountCode");
            $(this).children().eq(4).children("input[type='text']").attr("name","listResult["+index+"].taxDesr1");
            $(this).children().eq(5).children("input[type='text']").attr("name","listResult["+index+"].taxDesr2");
        }
    });
}

function reCalcCustomerIndex(){
	var index=-2;
    $("#customerTable tr").each(function(n){
        index++;
        if(index>=0){
            $(this).children().eq(0).children("input[type='hidden']").attr("name","listResult["+index+"].isUsed");
            $(this).children().eq(1).children("input[type='hidden']").eq(0).attr("name","listResult["+index+"].itemId");
            $(this).children().eq(1).children("input[type='text']").attr("name","listResult["+index+"].itemCode");
            $(this).children().eq(2).children("input[type='text']").attr("name","listResult["+index+"].itemName");
            /*Add by Jing Start*/
            if($("#preSelItem").val() == "RT"){
            	$(this).children().eq(3).children("select").attr("name","listResult["+index+"].endUser");
            	$(this).children().eq(4).children("select").attr("name","listResult["+index+"].modeType");
            }
            if($("#preSelItem").val() == "PR"){
            	$(this).children().eq(3).children("select").attr("name","listResult["+index+"].category");
            }
            if($("#preSelItem").val() == "CG"){
            	$(this).children().eq(3).children("input[type='text']").attr("name","listResult["+index+"].abbreviation");
            }
            /*Add by Jing End*/
        }
    });
}

function submitSave(){
    //
    //var columnType=$("select[name='columnType']").val();
    var columnType=$("#preSelItem").val();
    if(columnType){
       var confirmMsg = $("#hiddenMsgModifyConfirmation").val();
       var msg = "Modified CNAME's value will immediate reflect to subscription information CNAME selected value."
                +"<br/>Do you want to proceed save?"
                +"<br/>Click Yes to proceed save, No to abort save";

        var isError = false;
        $(".itemTable tr").each(function(index){
            if(index>0){
                var itemId = $(this).children().eq(1).children("input[type='hidden']").eq(0).val();
                var itemNamePre = $(this).children().eq(1).children("input[type='hidden']").eq(1).val();
                var itemName = $(this).children().eq(1).children("input[type='text']").val();
                if(itemId){
                    if(itemName==itemNamePre){
                    }else{
                        isError = true;
                        return false;
                    }
                }
            }
        });
        
        $("#customerTable tr").each(function(index){
            if(index>0){
                var itemId = $(this).children().eq(1).children("input[type='hidden']").eq(0).val();
                var itemCodePre = $(this).children().eq(1).children("input[type='hidden']").eq(1).val();
                var itemCode = $(this).children().eq(1).children("input[type='text']").val();
                var itemNamePre = $(this).children().eq(2).children("input[type='hidden']").val();
                var itemName = $(this).children().eq(2).children("input[type='text']").val();
                if(columnType=="RT"){
                	 var itemEndUserPre = $(this).children().eq(3).children("input[type='hidden']").val();
                     var itemEndUser = $(this).children().eq(3).children("select").val();
                     var itemModeTypePre = $(this).children().eq(4).children("input[type='hidden']").val();
                     var itemModeType = $(this).children().eq(4).children("select").val();
                }
                if(itemId){
                	if(columnType=="CG" || columnType == "EU"){
                		if(itemCode==itemCodePre && itemName==itemNamePre){
                        }else{
                            isError = true;
                            return false;
                        }
                	}
                    if(columnType=="RT"){
                    	if(itemCode==itemCodePre && itemName==itemNamePre && itemEndUser==itemEndUserPre && itemModeType==itemModeTypePre){
                        }else{
                            isError = true;
                            return false;
                        }
                    }
                }
            }
        });
        
        var messagestr="";
        $("#taxTable tr").each(function(index){
            if(index>0){
                var taxId = $(this).children().eq(1).children("input[type='hidden']").eq(0).val();
                var taxCodePre = $(this).children().eq(1).children("input[type='hidden']").eq(1).val();
                var taxCode = $(this).children().eq(1).children("input[type='text']").val();
                var taxRatePre= $(this).children().eq(2).children("input[type='hidden']").val();
                var taxRate=$(this).children().eq(2).children("input[type='text']").val();
                var accountCodePre= $(this).children().eq(3).children("input[type='hidden']").val();
                var accountCode=$(this).children().eq(3).children("input[type='text']").val();
                var descriptionPre=$(this).children().eq(4).children("input[type='hidden']").val();
                var description=$(this).children().eq(4).children("input[type='text']").val();
                var description2Pre=$(this).children().eq(5).children("input[type='hidden']").val();
                var description2=$(this).children().eq(5).children("input[type='text']").val();
                if(taxId){
                	//Check Tax Code
                    if(taxCode==taxCodePre){
                    }else{
                    	messagestr+="Tax Code";
                        isError = true;
                    }
                    //Check Tax Rate
                    if(taxRate==taxRatePre){
                    }else{
                    	messagestr+="Tax Rate";
                        isError = true;
                    }
                    //Check Tax Rate
                    if(accountCode==accountCodePre){
                    }else{
                    	messagestr+="Account Code";
                        isError = true;
                    }
                    //Check Description
                    if(description==descriptionPre){
                    }else{
                    	messagestr+="Description";
                        isError = true;
                    }
                    //Check Description2
                    if(description2==description2Pre){
                    }else{
                    	messagestr+="Description2";
                        isError = true;
                    }
                    if(messagestr =="" ||undefined || null){
                    }
                    else{
                    	return false;
                    }
                }
            }
        });
        
        if((columnType=='CA')&&isError){
            var message = msg.replace(/CNAME/g,"Carrier");
            message = confirmMsg.replace(/ARG0/g,message);
            var MsgBox = new messageBox();
            if (MsgBox.POPCFM(message) == MsgBox.YES_CONFIRM) {
            }else{
                return false;
            }
        }else if((columnType=='CG')&&isError){
            var message = msg.replace(/CNAME/g,"Customer Group / Affiliate");
            message = confirmMsg.replace(/ARG0/g,message);
            var MsgBox = new messageBox();
            if (MsgBox.POPCFM(message) == MsgBox.YES_CONFIRM) {
            }else{
                return false;
            }
        }else if((columnType=='EU')&&isError){
            var message = msg.replace(/CNAME/g,"End User");
            message = confirmMsg.replace(/ARG0/g,message);
            var MsgBox = new messageBox();
            if (MsgBox.POPCFM(message) == MsgBox.YES_CONFIRM) {
            }else{
                return false;
            }
        }else if((columnType=='LO')&&isError){
            var message = msg.replace(/CNAME/g,"Location");
            message = confirmMsg.replace(/ARG0/g,message);
            var MsgBox = new messageBox();
            if (MsgBox.POPCFM(message) == MsgBox.YES_CONFIRM) {
            }else{
                return false;
            }
        }else if((columnType=='FT')&&isError){
           var message = msg.replace(/CNAME/g,"Firewall Type");
            message = confirmMsg.replace(/ARG0/g,message);
            var MsgBox = new messageBox();
            if (MsgBox.POPCFM(message) == MsgBox.YES_CONFIRM) {
            }else{
                return false;
            }
        }else if((columnType=='FM')&&isError){
           var message = msg.replace(/CNAME/g,"Firewall Model");
            message = confirmMsg.replace(/ARG0/g,message);
            var MsgBox = new messageBox();
            if (MsgBox.POPCFM(message) == MsgBox.YES_CONFIRM) {
            }else{
                return false;
            }
        }else if((columnType=='RT')&&isError){
            var message = msg.replace(/CNAME/g,"Rate Type 2");
            message = confirmMsg.replace(/ARG0/g,message);
            var MsgBox = new messageBox();
            if (MsgBox.POPCFM(message) == MsgBox.YES_CONFIRM) {
            }else{
                return false;
            }
        }
        else if((columnType=='TC')&&isError){
            var message = msg.replace(/CNAME/g,"Tax Code").replace("subscription information","Customer Plan Maintenance / Billing Documents");
             message = confirmMsg.replace(/ARG0/g,message);
             var MsgBox = new messageBox();
             if (MsgBox.POPCFM(message) == MsgBox.YES_CONFIRM) {
             }else{
                 return false;
             }
         }
    }
    //tbxCode checking, if contain "&" prompt error message ERR1SC107
    if(!checkSpecialWords()){
    	return false;
    }
    return true;
}

function checkSpecialWords(){
	var isHaveSpecialWordFlg=false;
	var regexp=/[&]/;
	 var columnType=$("#preSelItem").val();
	 var message = $("#ERR4SC107").val();
	 
	//Check Rate Type 2  Or Customer Group / Affiliate Or End User
	 if(columnType=="RT" || columnType=="CG" || columnType=="EU" || columnType=="CC" || columnType=="CS" || columnType=="CT" || columnType=="PR" || columnType=="JN"){
		 $("#customerTable tr").each(function(index){
	            if(index>0){
	            	var itemCode = $(this).children().eq(1).children("input[type='text']").val();	
	            	if(regexp.test(itemCode)){
	            		isHaveSpecialWordFlg=true;
	        		}
	            }
		 });
	 }
	if(isHaveSpecialWordFlg){
		message=message.replace('{0}',"Code").replace('{1}',"Code");
		var MsgBox = new messageBox();
		MsgBox.POPALT(message);
		return false;
	}
    return true;
}

function changeSelect(self){
    $('.error').html('');
    $('.message').html('');
}