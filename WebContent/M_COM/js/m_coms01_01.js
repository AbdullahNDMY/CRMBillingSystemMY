$(function(){
    calcBankIndex();
    initCurrRemark();
});
function removeMsg(){
    $("div[class='message']").html("&nbsp");
    $("div[class='error']").html("&nbsp");
}
function saveBank(){
    
    $('form').submit();
}

function exitBank(){
	var MsgBox = new messageBox();
	if (MsgBox.POPEXT($('#hiddenMsgExitConfirmation').val()) == MsgBox.YES_CONFIRM) {
		var contextPath = $("#contextPath").val();
		window.location.href = contextPath+"/M_COM/M_COMS01BLogic.do";
	}
}

/**
 *open m-bank-03 to search bank
 */
function openM_BNK_S03(self){
    var contextPath = $("#contextPath").val();
    var url = contextPath+"/M_BNK/M_BNK_S03Blogic.do?index="+$(self).attr("id");
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
}

/**
 * set bank information 
 */

function bank03CallBack(index, idBank, bankFullName, bankCode, bankBranchCode) {

    $("input[name='listCompanyBank[" + index + "].idbank']").val(idBank);

    $("span[class='bankFullName[" + index + "]']").html(bankFullName);
    $("input[name='listCompanyBank[" + index + "].bankFullName']").val(bankFullName);

    $("span[class='bankCode[" + index + "]']").html(bankCode);
    $("input[name='listCompanyBank[" + index + "].bankCode']").val(bankCode);

    $("span[class='branchCode[" + index + "]']").html(bankBranchCode);
    $("input[name='listCompanyBank[" + index + "].branchCode']").val(bankBranchCode);
}

function clickRadioBtn(self){

    var father_tr = $(self).parent().parent();
    var father_table = father_tr.parent();
    if(father_table.attr("tagName")!="table"&&father_table.attr("tagName")!="TABLE"){
        father_table = father_table.parent();
    }
    father_table.find("input[type='radio']").removeAttr("checked");
    $(self).attr("checked","checked");
}

/**
 * click add company bank 
 */
function addCompBank(){

    var bank = $("#cloneBank").clone(true);
    if(bank.children().attr("tagName")!="tr"&&bank.children().attr("tagName")!="TR"){
        bank = bank.children();
    }
    $("#tableCompanyBank").append(bank.children());
    calcBankIndex();
}

/**
 *delete company bank 
 */
function delComBank(self){

    var MsgBox = new messageBox();
    if (MsgBox.POPCFM($('#hiddenMsgDeleteConfirmation').val()) == MsgBox.YES_CONFIRM) {

         var thisTR =  $(self).parent().parent();
         thisTR.next().remove();
         thisTR.remove();

         calcBankIndex();
   }
}

/**
 *click add bank account 
 */
function addBankAcc(self){

    var father_tr = $(self).parent().parent();
    var father_table = father_tr.parent();
    if(father_table.attr("tagName")!="table"&&father_table.attr("tagName")!="TABLE"){
        father_table = father_table.parent();
    }
    var name = father_tr.next().children(":eq(0)").children("input").attr("name");
    var trName = name.split(".")[0];
    var acount = $("#cloneBankAccount tr").clone(true);
    father_table.append(acount);
    calcAccIndex(trName,father_table);
    if(father_table.find("input[type='radio']:checked").size()==0){
        acount.find("input[type='radio']").attr("checked","checked");
    }
}

/**
 * delete bank account
 */

function delBankAcc(self) {
    var MsgBox = new messageBox();
    if (MsgBox.POPCFM($('#hiddenMsgDeleteConfirmation').val()) == MsgBox.YES_CONFIRM) {

        var father_tr = $(self).parent().parent();
        var father_table = father_tr.parent();
        if(father_table.attr("tagName")!="table"&&father_table.attr("tagName")!="TABLE"){
            father_table = father_table.parent();
        }
        var trName = $(self).next().attr("name").split(".")[0];

        if (father_tr.find("input[type='radio']:checked").size() != 0) {
            MsgBox.POPALT("Unable to delete default account !");
            return false;
        }

        if(father_table.find("tr").size()<=2){
            MsgBox.POPALT("Please register at least one bank account !");
            return false;
        }
        father_tr.remove();
        calcAccIndex(trName, father_table);
    }
}


/**
 * calculate company bank index
 */
function calcBankIndex(){

    var index = -1;

    $("#tableCompanyBank tr").each(function(n){
        if($(this).hasClass("companyBank")){
            index++;
            $(this).children().eq(0).children("input").attr("name","listCompanyBank["+index+"].idComBank");
            $(this).children().eq(1).children().attr("name","listCompanyBank["+index+"].displayOrder");
            $(this).children().eq(3).children("span").attr("class","bankFullName["+index+"]");
            $(this).children().eq(3).children("input:eq(0)").attr("name","listCompanyBank["+index+"].bankFullName");
            $(this).children().eq(3).children("input:eq(1)").attr("name","listCompanyBank["+index+"].idbank");
            $(this).children().eq(4).children().attr("name","listCompanyBank["+index+"].accountName");
            $(this).children().eq(5).children().attr("name","listCompanyBank["+index+"].swiftCode");
            if($(this).children().eq(2).children("button")[0]){
                $(this).children().eq(2).children("button").attr("id",index);
            }
        }
        if($(this).hasClass("bankAccount")){
            $(this).children().eq(3).children("span:eq(0)").attr("class","bankCode["+index+"]");
            $(this).children().eq(3).children("span:eq(1)").attr("class","branchCode["+index+"]");
            $(this).children().eq(3).children("input:eq(0)").attr("name","listCompanyBank["+index+"].bankCode");
            $(this).children().eq(3).children("input:eq(1)").attr("name","listCompanyBank["+index+"].branchCode");
            
            calcAccIndex("listCompanyBank["+index+"]",$(this).children().eq(4).children("table"));
        }
    });
}

/**
 *calculate bank account index 
 */
function calcAccIndex(index,table){
    var bankIndex = index+".";
    
    table.find("tr").each(function(n){
        if(n>0){
            $(this).children().eq(0).children("input").attr("name",bankIndex+"listCompanyBankAccount["+(n-1)+"].idComBank");
            $(this).children().eq(1).children("input").attr("name",bankIndex+"listCompanyBankAccount["+(n-1)+"].accountNo");
            $(this).children().eq(2).children("select").attr("name",bankIndex+"listCompanyBankAccount["+(n-1)+"].currency");
            $(this).children().eq(3).children("input").attr("name",bankIndex+"listCompanyBankAccount["+(n-1)+"].isDefault");
            $(this).children().eq(4).children("select").attr("name",bankIndex+"listCompanyBankAccount["+(n-1)+"].active");
        }
    });
}

//set when select currency, auto default currency abbreviation to remark column (MYR)
function changeCurrency(obj){
	var currency = obj.options[obj.selectedIndex].value;
	var accoutnNo=$(obj).closest("tr").eq(0).find('#accountNo');
	var regexp = /\b\([\s\S]*\)$/;
	if(!accoutnNo.val().match(regexp)){
		accoutnNo.val(accoutnNo.val()+"("+currency+")");
	}else{
		 var currencynew="("+currency+")";
		 var acc_val=accoutnNo.val();
		 var newaccoutno=acc_val.replace(regexp,currencynew);
		 accoutnNo.val(newaccoutno);
	}
}
//Init currency remark field
function initCurrRemark(){
	var tablebank=$("#tableCompanyBank");
	var acc=tablebank.find("#tableBankAccount");
	acc.each(function(n){
		if($(this).css("display")!="none"){
			var selectarry=$(this).find('#currencysel');
			selectarry.each(function(n){
				var selval=$(this).find('option:selected').val();
				var accoutnNo=$(this).closest("tr").eq(0).find('#accountNo');
				var regexp = /\b\([\s\S]*\)$/;
				if(!accoutnNo.val().match(regexp)){
					accoutnNo.val(accoutnNo.val()+"("+selval+")");
				}
			});
		}
	});
}