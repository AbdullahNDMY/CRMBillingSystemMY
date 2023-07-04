var POPUP_FEATURE = "height=#height#,width=#width#,left=#left#,top=#top#,screenX=#left#,screenY=#top#,toolbar=no,status=no,menubar=no,location=no,resizable=yes,scrollbars=yes";
var projectContextPath = "";
window.onload = function(){
    projectContextPath = window.document.getElementById("contextPath").value;
    var obj = document.getElementById("bodywidth");
    obj.style.width=63.3+'em';
    calculatedCreditBalance();
    changeAllStatus();
};
function getCustomerInfo(contextpath){
    var url = contextpath + "/B_BAC/RP_B_BAC_S03SearchBL.do";
    var popupWidth = window.screen.width*80/100;
    var popupHeight = window.screen.height*80/100;
    var left = Number((screen.availWidth/2) - (popupWidth/2));
    var top = Number((screen.availHeight/2) - (popupHeight/2));
    var newWindow = window.open(url,'name', POPUP_FEATURE.replace("#width#",popupWidth).replace("#height#",popupHeight).replace("#left#",left).replace("#top#",top));
    if (window.focus) { newWindow.focus(); }
}
function setCustomerAndBillAccInfo(idCust,custName,idBillAcc){
    document.getElementById("txtCustomerNameDisplay").innerText = custName;
    document.getElementById("txtCustomerIdDisplay").innerText = idCust;
    document.getElementById("customer").value = idCust;
    document.getElementById("cboCustomerName").value = custName;
    if(document.getElementById("tbxBillingAccount")){
        var option=document.createElement("option");
        document.getElementById("tbxBillingAccount").appendChild(option);
        option.value=idBillAcc;
        option.text=idBillAcc;
        document.getElementById("tbxBillingAccount").value = idBillAcc;
    }
    window.loadCustomer();
}
function changeAllStatus(){
    var hasDebit = document.getElementById("numDbInfo");
    if(hasDebit) {
        var numDbInfo = hasDebit.value;
        if(numDbInfo==undefined||numDbInfo==null||numDbInfo==''){
            return;
        }

        for(var i = 0; i <= numDbInfo; i++) {
            if (document.getElementById("debitInfos[" + i + "].chkAppliedTo").value == "1") {
                document.getElementById("debitInfos[" + i + "].tbxPayment").disabled = false;
            }else{
                document.getElementById("debitInfos[" + i + "].tbxPayment").disabled = true;
                document.getElementById("debitInfos[" + i + "].tbxPayment").value = 0;
            }
        }
    }
}
function changePaymentStatus(checkBox, index) {
    if(checkBox.checked) {
        document.getElementById("debitInfos[" + index + "].chkAppliedTo").value = "1";
        document.getElementById("debitInfos[" + index + "].tbxPayment").disabled = false;
        // set default value
        var hasDebit = document.getElementById("numDbInfo");
        if(hasDebit) {
            var numDbInfo = hasDebit.value;
            var totalPayment = 0;
            for(var i = 0; i <= numDbInfo; i++) {
                if (document.getElementById("debitInfos[" + i + "].chkAppliedTo").value == "1") {
                    totalPayment = accAdd(totalPayment, Number(document.getElementById("debitInfos[" + i + "].tbxPayment").value));
                }
            }
    
            var creditBalance = subtr(document.getElementById("origAmt").value, totalPayment);
    
            var gdcAmountDue = document.getElementById("debitInfos[" + index + "].gdcAmountDue").value;

            if(isNaN(gdcAmountDue)){
                gdcAmountDue = 0;
            } else {
                gdcAmountDue = Number(gdcAmountDue);
            }
            
            var leftBalance=0;
            if(gdcAmountDue>creditBalance){
                document.getElementById("debitInfos[" + index + "].tbxPayment").value = creditBalance;
            }else{
                document.getElementById("debitInfos[" + index + "].tbxPayment").value = gdcAmountDue;
                leftBalance = subtr(creditBalance,gdcAmountDue);
            }
            leftBalance = formatNum(leftBalance,2);
            document.getElementById("creditBalance").innerHTML = showcreditBalance(leftBalance);
            if(!isNaN(creditBalance)){
                document.getElementById("creditBalanceHidden").value = leftBalance;
            }
        }
    }else {
        document.getElementById("debitInfos[" + index + "].chkAppliedTo").value = "0";
        document.getElementById("debitInfos[" + index + "].tbxPayment").disabled = true;
        document.getElementById("debitInfos[" + index + "].tbxPayment").value = 0;
    }
    calculatedCreditBalance();
}

function calculatedCreditBalance() {
    var hasDebit = document.getElementById("numDbInfo");
    if(hasDebit) {
        var numDbInfo = hasDebit.value;
        if(numDbInfo==undefined||numDbInfo==null||numDbInfo==''){
            return;
        }
        var totalPayment = 0;
        for(var i = 0; i <= numDbInfo; i++) {
            if (document.getElementById("debitInfos[" + i + "].chkAppliedTo").value == "1") {
                totalPayment = accAdd(totalPayment, Number(document.getElementById("debitInfos[" + i + "].tbxPayment").value));
            }
        }

        var creditBalance = formatNum((subtr(document.getElementById("origAmt").value, totalPayment)),2);

        document.getElementById("creditBalance").innerHTML = showcreditBalance(creditBalance);
        if(!isNaN(creditBalance)){
            document.getElementById("creditBalanceHidden").value = creditBalance;
        }
    }
}

function showcreditBalance(creditBalance){
    if("-"===creditBalance.substring(0,1)){
        return creditBalance.substring(1);
    }else if("0.00"===creditBalance){
        return creditBalance;
    }else{
        return "-"+creditBalance;
    }
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

function loadCustomer() {
    window.document.forms[0].action = projectContextPath+"/B_TRM/B_TRMS02AD.do?action=customerChange";
    window.document.forms[0].submit();
}
function loadBillAcc() {
    window.document.forms[0].action = projectContextPath+"/B_TRM/B_TRMS02AD.do?action=billAccChange";
    window.document.forms[0].submit();
}
function loadCreditRef() {
    window.document.forms[0].action = projectContextPath+"/B_TRM/B_TRMS02AD.do?action=creditRefChange";
    window.document.forms[0].submit();
}
function confirm_exit() {
	var MsgBox = new messageBox();
    if($("input[type='hidden'][name='creditRef']")[0]){
        var creditRef = $("input[type='hidden'][name='creditRef']").val();
        var url = projectContextPath+"/B_TRM/B_TRMS02V.do?creditRef="+creditRef+"&action=view";
		if (MsgBox.POPEXT($('#hiddenMsgExitConfirmation').val()) == MsgBox.YES_CONFIRM) {
			window.location=url;
        }
    }else{
    	if (MsgBox.POPEXT($('#hiddenMsgExitConfirmation').val()) == MsgBox.YES_CONFIRM) {
    		window.document.forms[0].action = projectContextPath+"/B_TRM/B_TRMS01SCR.do";
    		window.document.forms[0].submit();
    	}
    }
}
function confirm_save() {
    calculatedCreditBalance();
    var actionType = document.getElementById("actionType").value;
    window.document.forms[0].action = projectContextPath+"/B_TRM/B_TRMS02NE.do?action="+actionType;
    window.document.forms[0].submit();
}
//Addition
function accAdd(arg1,arg2){ 
	var r1,r2,m; 
	try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0};
	try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0};
	m=Math.pow(10,Math.max(r1,r2));
	return (arg1*m+arg2*m)/m;
}
//sub
function subtr(arg1,arg2){
     var r1,r2,m,n;
     try{r1=arg1.toString().split(".")[1].length;}catch(e){r1=0;}
     try{r2=arg2.toString().split(".")[1].length;}catch(e){r2=0;}
     m=Math.pow(10,Math.max(r1,r2));
     //last modify by deeka
     n=(r1>=r2)?r1:r2;
     return ((arg1*m-arg2*m)/m).toFixed(n);
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