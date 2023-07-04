function confirm_save() {
    
    var refunds = $("input[type='text'][name^='paymentInformationList'][name$='refundAmount']");
    
    refunds.each(function(){
        if($.trim(this.value)==""||isNaN(this.value)){
            this.value = 0;
        }
    });
    
    var appliedToChk = $("input[type='checkbox'][name^='paymentInformationList'][name$='appliedTo']:checked");
    
    if(appliedToChk.size() == 0){
        var MsgBox = new messageBox();
        
        if (MsgBox.POPCFM("No record selected. Are you sure you want to save Cash Book?") == MsgBox.YES_CONFIRM)
            document.forms[0].submit();
        else
            return false;
        
    }else{
        document.forms[0].submit();
    }
    
}

function comfirm_exit() {
    var mess = new messageBox();
    var agree = mess.POPEXT();  
    if (agree == 1) {
        var actionType=$("input[type='hidden'][name='actionFlg']").val();
        var receiptNo=$("input[name='receiptNo']").val();
        if(actionType=='edit'){
            window.location = $("#contextPath").val()+"/B_CSB/B_CSB_S03View.do?receiptNo="+receiptNo;
        }else{
            //var billAcc = $("input[name='idBillAccount']").val();
            //var fromPage = $("input[name='fromPage']").val();
            //window.location = $("#contextPath").val()+"/B_BAC/RP_B_BAC_S02_02_01BL.do?idBillAccount="+billAcc+"&idCustPlan=''&fromPage="+fromPage;
            window.location = $("#contextPath").val()+"/B_CSB/B_CSBR01BLogic.do";
        }
    }   
}
function exitView() {
    document.forms[0].action = $("#contextPath").val()+"/B_CSB/B_CSBR01BLogic.do";
    document.forms[0].submit();
}
function confirmEdit() {
    document.forms[0].action = $("#contextPath").val()+"/B_CSB/B_CSB_S03Edit.do";
    document.forms[0].submit();
}
function confirmDelete() {
    var mess = new messageBox();
    var agree = mess.POPDEL();
    if (agree === "1") {
        document.forms[0].action = $("#contextPath").val()+"/B_CSB/B_CSB_S03Del.do";
        document.forms[0].submit();
    }
}
// key in Amt Refund
function setAmtRefund(obj) {

    // calculator the payment info
    calPaymentInfo();
}

/**
 * calculator the payment info <br>
 * refund amount
 */
function calPaymentInfo() {

    var refunds = $("input[type='text'][name^='paymentInformationList'][name$='refundAmount']");
    
    var totalRefund = 0;
    refunds.each(function(){
        
        if($.trim(this.value)==""||isNaN(this.value)){
            this.value = 0;
        }
        totalRefund = accAdd(totalRefund,Number(this.value));
    });

    var amtRefunded = $("input[name='amtRefunded']").val();

    if($.trim(amtRefunded)==""||isNaN(amtRefunded)){
        amtRefunded = 0;
    }

    var balcanceAmount = subtr(Number(amtRefunded),totalRefund);

    $("#balanceAmt").text(formatNum(balcanceAmount,2));
    $("#hiddenBalanceAmt").val(balcanceAmount);
    
    $("#totalRefundAmount").text(formatNum(totalRefund,2));
    $("#hidTotalRefundAmount").val(totalRefund);
}

function editPaymentInfo(obj,index) {
    if(obj.checked) {
        document.getElementsByName("paymentInformationList["+index+"].refundAmount")[0].readOnly = false;
        
        var balanceAmount = $("#hiddenBalanceAmt").val();
        var receiptBalanceAmount =document.getElementsByName("paymentInformationList["+index+"].balanceAmount")[0].value; 
        if(Number(balanceAmount)<=0){
            document.getElementsByName("paymentInformationList["+index+"].refundAmount")[0].value = "0";
        }else{
            if(Number(balanceAmount)<Number(receiptBalanceAmount)){
                document.getElementsByName("paymentInformationList["+index+"].refundAmount")[0].value = balanceAmount;
            }else{
                document.getElementsByName("paymentInformationList["+index+"].refundAmount")[0].value = receiptBalanceAmount;
            }
        }
    }else {
        document.getElementsByName("paymentInformationList["+index+"].refundAmount")[0].readOnly = true;
        document.getElementsByName("paymentInformationList["+index+"].refundAmount")[0].value = "0";
    }
    calPaymentInfo();
}

//only Decimal Number
function onlyDecNumbers(evt) {
    var e = evt;
    if(window.event){ // IE
        var charCode = e.keyCode;
    } else if (e.which) { // Safari 4, Firefox 3.0.4
        var charCode = e.which;
    }
    if (charCode > 31 && (charCode < 48 || charCode > 57)){
        if(charCode == 46){// available for . 
            return true;
        }else{
            return false;
        }
    }else{
        return true;
    }
}
//Addition
function accAdd(arg1, arg2) {
    var r1, r2, m;
    try {
        r1 = arg1.toString().split(".")[1].length
    } catch(e) {
        r1 = 0
    };
    try {
        r2 = arg2.toString().split(".")[1].length
    } catch(e) {
        r2 = 0
    };
    m = Math.pow(10, Math.max(r1, r2));
    return (arg1 * m + arg2 * m) / m;
}

//sub
function subtr(arg1,arg2){
    var r1, r2, m, n;
    try {
        r1 = arg1.toString().split(".")[1].length;
    } catch(e) {
        r1 = 0;
    }
    try {
        r2 = arg2.toString().split(".")[1].length;
    } catch(e) {
        r2 = 0;
    }
    m = Math.pow(10, Math.max(r1, r2));
    //last modify by deeka
    n = (r1 >= r2) ? r1 : r2;
    return ((arg1 * m - arg2 * m) / m).toFixed(n);

}
//format Number
function formatNum(s, n){
    n = n > 0 && n <= 20 ? n : 2;   
    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";   
    var l = s.split(".")[0].split("").reverse(),   
    r = s.split(".")[1];   
    t = "";   
    for(i = 0; i < l.length; i ++ ){   
      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");   
    }
    var prePointData = t.split("").reverse().join("");
    if(prePointData.length>1){
        if("-,"===prePointData.substring(0,2)){
            prePointData = "-"+prePointData.substring(2);
        }
    }
    return prePointData + "." + r;
}