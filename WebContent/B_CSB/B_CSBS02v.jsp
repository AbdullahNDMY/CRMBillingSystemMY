<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
	<link href="<%=request.getContextPath()%>/B_CSB/css/b_csbs02.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
	<script language="javascript">
	function calDebitInfo() {
		var numDbInfo = document.getElementById("numDbInfo").value;
		var origAmt = 0;
		var amtDue = 0;
		var payment = 0;
		var forexLoss = 0;
		var forexGain = 0;
		for(var i = 0; i < numDbInfo; i++) {
			origAmt = accAdd(origAmt,Number(document.getElementById("dbInfo["+i+"].BILL_AMOUNT").value));
			amtDue = accAdd(amtDue,Number(document.getElementById("dbInfo["+i+"].AMT_DUE").value));
			payment = accAdd(payment,Number(document.getElementById("dbInfo["+i+"].AMT_PAID").value));
			forexLoss = accAdd(forexLoss,Number(document.getElementById("dbInfo["+i+"].FOREX_LOSS").value));
			forexGain = accAdd(forexGain,Number(document.getElementById("dbInfo["+i+"].FOREX_GAIN").value));
		}
		document.getElementById("sumOrigAmt").innerHTML = formatNum(origAmt,2);
		document.getElementById("sumAmtDue").innerHTML = formatNum(amtDue,2);
		document.getElementById("sumPayment").innerHTML = formatNum(payment,2);
		
		var amtReceived = document.getElementById("amtReceived").value;
		var refundAmount = document.getElementById("refundAmount").value;
        if(isNaN(refundAmount)){
            refundAmount = 0;
        }
        amtReceived = subtr(amtReceived,refundAmount);
		document.getElementById("balanceAmt").innerHTML = formatNum(subtr(Number(amtReceived), subtr(accAdd(payment, forexLoss), forexGain)), 2);
	}
	
	function confirmDelete() {	
		var mess = new messageBox();
		var agree = mess.POPDEL(document.getElementById("hiddenMsgDelConfirmation").value);
		if (agree === "1") {
			document.getElementById("model").value = "Delete";
			document.forms[0].action = "<%=request.getContextPath()%>/B_CSB/B_CSBS02D.do";
			document.forms[0].submit();
		}
	}
	
	function confirmReject(type) {	
		//rejectionDate
		var rejectionDate = document.getElementById("rejectionDate").value;
		//remark
		var remark = document.getElementById("remark").value;
		var hidTransDate = document.getElementById("hidTransDate").value;
		var isCheckMulCharFlg = document.getElementById("isCheckMulCharFlg").value;
		var mess = new messageBox();
		var returnValue = mess.POPCBR(rejectionDate,remark,hidTransDate, type, isCheckMulCharFlg).split(";");
		if (returnValue[0] === mess.YES_CONFIRM) {
			document.getElementById("model").value = "Reject";
			document.getElementById("rejectionDate").value = returnValue[1];
			document.getElementById("remark").value = returnValue[2];
			document.forms[0].action = "<%=request.getContextPath()%>/B_CSB/B_CSBS02D.do";
			document.forms[0].submit();
		}
	}
	
	function confirmEdit() {
		document.forms[0].action = "<%=request.getContextPath()%>/B_CSB/B_CSBS02E_SCR.do";
		document.forms[0].submit();
	}
	
	function comfirm_exit()	{
		//var mess = new messageBox();
		//var agree = mess.POPEXT();	
		//if (agree == 1) {
			document.forms[0].action = "<%=request.getContextPath()%>/B_CSB/B_CSBR01BLogic.do";
			document.forms[0].submit();
		//}	
	}
	//Addition
	function accAdd(arg1,arg2){ 
		var r1,r2,m,n; 
		try{r1=arg1.toString().split(".")[1].length;}catch(e){r1=0;};
		try{r2=arg2.toString().split(".")[1].length;}catch(e){r2=0;};
		m=Math.pow(10,Math.max(r1,r2));
		n=(r1>=r2)?r1:r2;
		return ((arg1*m+arg2*m)/m).toFixed(n);
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
	// open refund info window
	function openB_CSB_S04(url){
	    var width = window.screen.width*80/100;
        var height = window.screen.height*40/100;
        var left = Number((screen.availWidth/2) - (width/2));
        var top = Number((screen.availHeight/2) - (height/2));
        var offsetFeatures = "width=" + width + ",height=" + height +
                             ",left=" + left + ",top=" + top +
                             "screenX=" + left + ",screenY=" + top;
        var strFeatures= "toolbar=no, status=no, menubar=no,location=no,scrollbars=yes, resizable=yes" + "," + offsetFeatures;      
        var newwindow = window.open(url, null, strFeatures);
        if (window.focus) { newwindow.focus(); }
	}

	function chkAlertMsg(){
	   var isAlterMsg = document.getElementById("isAlterMsg").value;
	   if('Y'==isAlterMsg){
	       var msBox = new messageBox();
	       var message = document.getElementById("alertMessage").innerText;
	       document.getElementById("alertMessage").innerHTML="";
	       msBox.POPALT(message);
	   }
	}
	</script>
</head>
<ts:body onload="calDebitInfo();chkAlertMsg();">
	<ts:form action="/B_CSBS01SCR">
		<t:defineCodeList id="LIST_PAYMENT_METHOD" />
		<t:defineCodeList id="LIST_CURRENCY" />
		<t:defineCodeList id="LIST_PAYMENT_STATUS" />
		<input type="hidden" name="idRef" id="idRef" value="${b_csbForm.idRef}"/>
		<input type="hidden" name="isClosed" id="idRef" value="${b_csbForm.isClosed}"/>
		<input type="hidden" name="rejectionDate" id="rejectionDate" value="${b_csbForm.rejecteddate}"/>
		<input type="hidden" name="remark" id="remark" value="${b_csbForm.rejectdesc}"/>
		<input type="hidden" name="model" id="model" value=""/>
		<input type="hidden" id="idBillAccount" value="${b_csbForm.idBillAccount}"/>
		
		<input type="hidden" id="isAlterMsg" value="${b_csbForm.isAlterMsg}"/>
		<html:hidden property="curCode" name="b_csbForm"/>
		<html:hidden property="payer" name="b_csbForm"/>
		<html:hidden property="pattern" name="b_csbForm"/>
		<html:hidden property="pmtMethod" name="b_csbForm"/>
		<html:hidden property="idBillAccount" name="b_csbForm"/>
		<html:hidden property="amtReceived" name="b_csbForm"/>
		<html:hidden property="isCheckMulCharFlg" name="b_csbForm" styleId="isCheckMulCharFlg"/>
		<table class="subHeader" cellpadding="0" cellspacing="0">
		  <tr>
		    <td class="Title"><bean:message key="screen.b_csb.title"/></td> 
		  </tr>
		</table> 
		<table class = "generalInfo" cellpadding="0" cellspacing="0">
			<tr>
				<td class="header" width="50%">					
					<bean:message key="screen.b_csb.customerpayment"/>												
				</td>
			</tr>
		</table>
		<table class = "generalInfo" cellpadding="0" cellspacing="0">
		    <c:if test="${b_csbForm.pattern eq 'CST'}">
				<tr>
					<td class="widthBetween tdBorderTopStyle tdBorderLeftStyle"></td>
					<td class="colLeft1 tdBorderTopStyle">
						<bean:message key="screen.b_csb.paymentmethod"/>
					</td>
					<td class="tdBorderTopStyle"><bean:message key="screen.b_csb.colon"/></td>
					<td class="colLeft tdBorderTopStyle tdBorderRightStyle">
						<t:writeCodeValue codeList="LIST_PAYMENT_METHOD" key="${b_csbForm.pmtMethod}"/>
					</td>
					<td class="widthBetween"></td>				
					<td style="font-weight:bold;">
						<bean:message key="screen.b_csb.paymentstatus"/>
					</td>
					<td><bean:message key="screen.b_csb.colon"/></td>
					<td>
						<t:writeCodeValue codeList="LIST_PAYMENT_STATUS" key="${b_csbForm.paymentStatus}"/>
					</td>
				</tr>
				<tr>
					<td class="widthBetween tdBorderLeftStyle"></td>
					<td class="colLeft1" width="20%">
						<bean:message key="screen.b_csb.payee"/>
					</td>
					<td><bean:message key="screen.b_csb.colon"/></td>
					<td class="colLeft tdBorderRightStyle">
						<logic:iterate id="p" name="b_csbForm" property="cbPayer">
							<c:if test="${b_csbForm.payer == p.value}">
								${p.label}
							</c:if>
						</logic:iterate>
					</td>
					<td class="widthBetween"></td>	
					<td style="font-weight:bold;">
						<bean:message key="screen.b_csb2.receiptno"/>
					</td>
					<td><bean:message key="screen.b_csb.colon"/></td>
					<td>
						${b_csbForm.receiptNo}
					</td>
				</tr>
				<tr>
					<td class="widthBetween tdBorderLeftStyle"></td>
					<td class="colLeft1">
						<bean:message key="screen.b_csb2.otherpayer"/>
					</td>
					<td><bean:message key="screen.b_csb.colon"/></td>
					<td class="colLeft tdBorderRightStyle">
						${b_csbForm.other}
					</td>
					<td class="widthBetween"></td>	
					<td style="font-weight:bold;">
						<bean:message key="screen.b_csb.balance"/>
					</td>
					<td><bean:message key="screen.b_csb.colon"/></td>
					<td>
						<span id="balanceAmt"></span>
					</td>
				</tr>
				<tr>
					<td class="widthBetween tdBorderLeftStyle tdBorderBottomStyle"></td>
					<td class="colLeft1 tdBorderBottomStyle">
						<bean:message key="screen.b_csb.currency"/>
					</td>
					<td class="tdBorderBottomStyle"><bean:message key="screen.b_csb.colon"/></td>
					<td class="colLeft tdBorderBottomStyle tdBorderRightStyle">
						<t:writeCodeValue codeList="LIST_CURRENCY" key="${b_csbForm.curCode}"/>
					</td>
					<logic:equal name="b_csbForm" property="paymentStatus" value="R">
					<td class="widthBetween"></td>	
					<td style="font-weight:bold;">
						<bean:message key="screen.b_csb.rejecteddate"/>
					</td>
					<td><bean:message key="screen.b_csb.colon"/></td>
					<td>
						${b_csbForm.rejecteddate}
					</td>
					</logic:equal>
				</tr>
			</c:if>
			<c:if test="${b_csbForm.pattern eq 'BAC'}">
				<tr>
					<td class="widthBetween tdBorderTopStyle tdBorderLeftStyle"></td>
					<td class="colLeft1 tdBorderTopStyle" width="20%">
						<bean:message key="screen.b_csb.payee"/>
					</td>
					<td class="tdBorderTopStyle"><bean:message key="screen.b_csb.colon"/></td>
					<td class="colLeft tdBorderTopStyle tdBorderRightStyle">
						<bean:write property="payerName" name="b_csbForm"/>
					</td>	
					<td class="widthBetween"></td>			
					<td style="font-weight:bold;">
						<bean:message key="screen.b_csb2.customerId"/>
					</td>
					<td><bean:message key="screen.b_csb.colon"/></td>
					<td>
						<bean:write property="payer" name="b_csbForm"/>
					</td>
				</tr>
				<tr>
					<td class="widthBetween tdBorderLeftStyle"></td>
					<td class="colLeft1" width="20%">
						<bean:message key="screen.b_csb2.accountno"/>
					</td>
					<td><bean:message key="screen.b_csb.colon"/></td>
					<td class="colLeft tdBorderRightStyle">
						${b_csbForm.idBillAccount}
					</td>
					<td class="widthBetween"></td>			
					<td style="font-weight:bold;">
						<bean:message key="screen.b_csb.paymentstatus"/>
					</td>
					<td><bean:message key="screen.b_csb.colon"/></td>
					<td>
						<t:writeCodeValue codeList="LIST_PAYMENT_STATUS" key="${b_csbForm.paymentStatus}"/>
					</td>
				</tr>
				<tr>
					<td class="widthBetween tdBorderLeftStyle"></td>
					<td class="colLeft1">
						<bean:message key="screen.b_csb.paymentmethod"/>
					</td>
					<td><bean:message key="screen.b_csb.colon"/></td>
					<td class="colLeft tdBorderRightStyle">
						<t:writeCodeValue codeList="LIST_PAYMENT_METHOD" key="${b_csbForm.pmtMethod}"/>
					</td>
					<td class="widthBetween"></td>	
					<td style="font-weight:bold;">
						<bean:message key="screen.b_csb2.receiptno"/>
					</td>
					<td><bean:message key="screen.b_csb.colon"/></td>
					<td>
						${b_csbForm.receiptNo}
					</td>
				</tr>
				<tr>
					<td class="widthBetween tdBorderLeftStyle tdBorderBottomStyle"></td>
					<td class="colLeft1 tdBorderBottomStyle">
						<bean:message key="screen.b_csb.currency"/>
					</td>
					<td class="tdBorderBottomStyle"><bean:message key="screen.b_csb.colon"/></td>
					<td class="colLeft tdBorderBottomStyle tdBorderRightStyle">
						<t:writeCodeValue codeList="LIST_CURRENCY" key="${b_csbForm.curCode}"/>
					</td>
					<td class="widthBetween"></td>	
					<td style="font-weight:bold;">
						<bean:message key="screen.b_csb.balance"/>
					</td>
					<td><bean:message key="screen.b_csb.colon"/></td>
					<td>
						<span id="balanceAmt"></span>
					</td>
					<%-- 
					<logic:equal name="b_csbForm" property="paymentStatus" value="R">
					<td class="widthBetween"></td>	
					<td style="font-weight:bold;">
						<bean:message key="screen.b_csb.rejecteddate"/>
					</td>
					<td><bean:message key="screen.b_csb.colon"/></td>
					<td>
						${b_csbForm.rejecteddate}
					</td>
					</logic:equal>
					--%>
				</tr>
			</c:if>
			<tr>
				<td class="widthBetween"></td>
				<td class="colLeft1">
					<bean:message key="screen.b_csb.transactiondate"/>
				</td>
				<td><bean:message key="screen.b_csb.colon"/></td>
				<td class="colLeft">
					${b_csbForm.transDate}
					<input type="hidden" name="hidTransDate" id="hidTransDate" value="${b_csbForm.transDate}"/>
				</td>
				<td></td>
                <logic:equal name="b_csbForm" property="paymentStatus" value="R">
                    <td class="group" colspan="3" rowspan="4">
                        <fieldset>
                            
                            <%
                            String accessType1 = ((nttdm.bsys.common.fw.BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT")).getUserAccessInfo("B", "B-CSB").getAccess_type();
							if(accessType1.equals("2")) {
							%>
							<c:choose>
							    <c:when test="${not empty b_csbForm.isClosed and b_csbForm.isClosed == '0' and b_csbForm.paymentStatus=='R'}">
							        <legend>
							            <a href="#" onclick="confirmReject('2');"><bean:message key="screen.b_csb2.rejected"/></a>
							        </legend>
							    </c:when>
							    <c:otherwise>
							        <legend style="color: blue;">
									    <bean:message key="screen.b_csb2.rejected"/>
		                            </legend>
							    </c:otherwise>
							</c:choose>
							<%} else {%>
							<legend style="color: blue;">
							    <bean:message key="screen.b_csb2.rejected"/>
                            </legend>
							<%} %>
                            <table cellpadding="0" cellspacing="0" style="width:100%;height:100%;font-size:15px;">
                                <tr>
                                    <td style="width:50px;"><bean:message key="screen.b_csb2.rejectedDate"/></td>
                                    <td style="width:20px;"><bean:message key="screen.b_csb.colon"/></td>
                                    <td>${b_csbForm.rejecteddate}</td>
                                </tr>
                                <tr>
                                    <td><bean:message key="screen.b_csb2.rejectedRemark"/></td>
                                    <td><bean:message key="screen.b_csb.colon"/></td>
                                    <td>${b_csbForm.rejectdesc}</td>
                                </tr>
                            </table>
                        </fieldset>
                    </td>
                </logic:equal>
			</tr>
			<tr>
				<td class="widthBetween"></td>
				<td class="colLeft1">
					<bean:message key="screen.b_csb.amountreceived"/>
				</td>
				<td><bean:message key="screen.b_csb.colon"/></td>
				<td class="colLeft">
					<fmt:formatNumber value="${b_csbForm.amtReceived}" pattern="#,##0.00"/>
					<input type="hidden" id="amtReceived" value="${b_csbForm.amtReceived}"/>
				</td>
				<td></td>
			</tr>
			<tr>
				<td class="widthBetween"></td>
				<td class="colLeft1"><bean:message key="screen.b_csb.bankaccount"/></td>
				<td><bean:message key="screen.b_csb.colon"/></td>
				<td class="colLeft" colspan="5">
					<logic:iterate id="p" name="b_csbForm" property="cbBankAcc">
						<c:if test="${b_csbForm.bankAcc == p.value}">
							${p.label}
						</c:if>
					</logic:iterate>
				</td>
			</tr>
			<tr>
				<td class="widthBetween"></td>
				<td class="colLeft1">
					<bean:message key="screen.b_csb.orno"/>
				</td>
				<td><bean:message key="screen.b_csb.colon"/></td>
				<td class="colLeft">
					${b_csbForm.receiptRef}
				</td>
				<td></td>
			</tr>
			<tr>
				<td class="widthBetween"></td>
				<td class="colLeft1">
					<bean:message key="screen.b_csb2.paidprevious"/>
				</td>
				<td><bean:message key="screen.b_csb.colon"/></td>
				<td class="colLeft">
					${b_csbForm.paidPreInvoiceText}
				</td>
			</tr>
			<tr>
				<td class="widthBetween"></td>
				<td class="colLeft1">
					<bean:message key="screen.b_csb2.overpaid"/>
				</td>
				<td><bean:message key="screen.b_csb.colon"/></td>
				<td class="colLeft">
					${b_csbForm.overPaidText}
				</td>
			</tr>
			<tr>
				<td class="widthBetween"></td>
				<td class="colLeft1">
					<bean:message key="screen.b_csb.remark"/>
				</td>
				<td><bean:message key="screen.b_csb.colon"/></td>
				<td class="colLeft">
					${b_csbForm.remark}
				</td>
			</tr>
			<tr>
				<td class="widthBetween"></td>
				<td class="colLeft1">
					<bean:write name="b_csbForm" property="labPaymentRef1"/>
				</td>
				<td><bean:message key="screen.b_csb.colon"/></td>
				<td class="colLeft" rowspan="2" colspan="5">
					<table cellpadding="0" cellspacing="0" style="width:100%;height:100%;min-width:300px;font-family:'Calibri';font-size:16px;">
						<tr>
							<td style="width:190px">
								<span style="float:left;">
									${b_csbForm.paymentRef1}
								</span>
							</td>
							<td>
								<span style="color:#3A3AFD;margin-left:5px;">
									<logic:notEmpty name="b_csbForm" property="paymentRef1De">
										<bean:write name="b_csbForm" property="paymentRef1De"/>
									</logic:notEmpty>
								</span>
							</td>
						</tr>
						<tr>
							<td style="width:190px">
								<span style="float:left;">
									${b_csbForm.paymentRef2}
								</span>
							</td>
							<td>
								<span style="color:#3A3AFD;margin-left:5px;">
									<logic:notEmpty name="b_csbForm" property="paymentRef2De">
										<bean:write name="b_csbForm" property="paymentRef2De"/>
									</logic:notEmpty>
								</span>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class="widthBetween"></td>
				<td class="colLeft1">
					<bean:write name="b_csbForm" property="labPaymentRef2"/>
					
				</td>
				<td><bean:message key="screen.b_csb.colon"/></td>
			</tr>
			<tr>
				<td class="widthBetween"></td>
				<td class="colLeft1">
					<bean:message key="screen.b_csb.bankcharges"/>
				</td>
				<td><bean:message key="screen.b_csb.colon"/></td>
				<td class="colLeft">
					<fmt:formatNumber value="${b_csbForm.bankCharge}" pattern="#,##0.00"/>
				</td>
			</tr>
		</table>
		
		<table class = "generalInfo2" cellpadding="0" cellspacing="0">
			<tr>
				<td class="header">					
					<bean:message key="screen.b_csb2.debitinfomation"/>												
				</td>
				<td class="header" style="text-align: right;">
				    <logic:notEmpty name="b_csbForm" property="refundInfo">
                        <bean:define id="refundInfo" name="b_csbForm" property="refundInfo"></bean:define>
                        <logic:greaterThan value="0" name="refundInfo" property="TOTAL_NUM">
                            <bean:message key="screen.b_csb_s02.refundReceipt"/>
                            <bean:message key="screen.b_csb.colon"/>
                            <a href="#" onclick="openB_CSB_S04('${pageContext.request.contextPath}/B_CSB/B_CSB_S04SCRBL.do?receiptNo=${b_csbForm.receiptNo}')">
                            <fmt:formatNumber value="${refundInfo.REFUND_AMOUNT}" pattern="#,##0.00"/>
                            </a>
                        </logic:greaterThan>
                        <input type="hidden" value="${refundInfo.REFUND_AMOUNT}" id="refundAmount"/>
                    </logic:notEmpty>
                    <logic:empty name="b_csbForm" property="refundInfo">
                        <input type="hidden" value="0" id="refundAmount"/>
                    </logic:empty>
				</td>
			</tr>
		</table>
		<!-- Debit Information -->
		<table class="creditInfo" cellpadding="0" cellspacing="0" id="create_infor" border="1" >
			<tr>
				<td class="header"><bean:message key="screen.b_csb.appliedtocol"/></td>
				<td class="header"><bean:message key="screen.b_csb.datecol"/></td>
				<td class="header"><bean:message key="screen.b_csb.debitreference"/></td>
				<td class="header"><bean:message key="screen.b_csb.currencycol"/></td>
				<td class="header"><bean:message key="screen.b_csb.origamtcol"/></td>
				<td class="header"><bean:message key="screen.b_csb.outstandingAmt"/></td>
				<td class="header"><bean:message key="screen.b_csb.paymentcol"/></td>
				<td class="header"><bean:message key="screen.b_csb.forexlosscol"/></td>
				<td class="header"><bean:message key="screen.b_csb.forexgaincol"/></td>
			</tr>	
			<bean:define id="infoCount" value="0"/>
			<logic:present property="debitInfos" name="b_csbForm" >
			   <logic:iterate id="dbInfo" name="b_csbForm" property="debitInfos" indexId="index">
			   	<bean:define id="infoCount" value="${infoCount+1}"/>
				<tr>
					<td class="defaultNo" align="center">
						<html:checkbox name="dbInfo" property="APPLIED" disabled="true" value="1" indexed="true" />	
					</td>
					<td class="defaultText" align="center">
						<bean:write name="dbInfo" property="DATE_REQ"/>
					</td>
					<td class="defaultText" align="center">
						<bean:write name="dbInfo" property="ID_REF"/>
					</td>
					<td class="defaultText" align="center">
						<bean:write name="dbInfo" property="BILL_CURRENCY"/>
					</td>
					<td class="defaultText" align="right">
						<c:if test="${empty dbInfo.BILL_AMOUNT}">&nbsp;</c:if>
						<fmt:formatNumber value="${dbInfo.BILL_AMOUNT}" pattern="#,##0.00"/>
						<html:hidden name="dbInfo" property="BILL_AMOUNT" indexed="true"/>
					</td>
					<td class="defaultText" align="right">
						<c:if test="${empty dbInfo.AMT_DUE}">&nbsp;</c:if>
						<fmt:formatNumber value="${dbInfo.AMT_DUE}" pattern="#,##0.00"/>
						<html:hidden name="dbInfo" property="AMT_DUE" indexed="true"/>
					</td>
					<td class="defaultText" align="right">
						<c:if test="${empty dbInfo.AMT_PAID}">&nbsp;</c:if>
						<fmt:formatNumber value="${dbInfo.AMT_PAID}" pattern="#,##0.00"/>
						<html:hidden name="dbInfo" property="AMT_PAID" indexed="true"/>
					</td>
					<td class="defaultText" align="right">
						<c:if test="${empty dbInfo.FOREX_LOSS}">&nbsp;</c:if>
						<fmt:formatNumber value="${dbInfo.FOREX_LOSS}" pattern="#,##0.00"/>
						<html:hidden name="dbInfo" property="FOREX_LOSS" indexed="true"/>
					</td>
					<td class="defaultText" align="right">
						<c:if test="${empty dbInfo.FOREX_GAIN}">&nbsp;</c:if>
						<fmt:formatNumber value="${dbInfo.FOREX_GAIN}" pattern="#,##0.00"/>
						<html:hidden name="dbInfo" property="FOREX_GAIN" indexed="true"/>
					</td>
				</tr>
			  </logic:iterate>
			</logic:present>
			<tr height="10">
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="3" align="right">Totals</td>
				<td>&nbsp;</td>
				<td align="right">
					<span id="sumOrigAmt">&nbsp;</span>
				</td>
				<td align="right">
					<span id="sumAmtDue">&nbsp;</span>
				</td>
				<td align="right">
					<span id="sumPayment">&nbsp;</span>
				</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</table>
		<input type="hidden" id="numDbInfo" value="${infoCount}"/>
		<input type="hidden" id="hiddenMsgDelConfirmation" value="<bean:message key="info.ERR4SC002"/>"/>
		<!-- Debit Information -->
		<table class="buttonTbl" >
			<tr>
				<td>
					<%
					String accessType = ((nttdm.bsys.common.fw.BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT")).getUserAccessInfo("B", "B-CSB").getAccess_type();
					if(accessType.equals("2")) {
					%>
					<c:if test="${not empty b_csbForm.isClosed and b_csbForm.isClosed == '0' and b_csbForm.paymentStatus=='N'}">
						<input type="button" class="button" value="<bean:message key="screen.b_csb.editbutton"/>" onclick="confirmEdit();"/>
						<logic:equal value="0" name="refundInfo" property="TOTAL_NUM">
						<input type="button" class="button" value="<bean:message key="screen.b_csb.deletebutton"/>" onclick="confirmDelete();"/>
						<input type="button" class="button" value="<bean:message key="screen.b_csb2.reject"/>" onclick="confirmReject('1');"/>
						</logic:equal>
					</c:if>
					<%} %>										
						<input type="button" class= "button"  value="<bean:message key="screen.b_csb.exitbutton"/>" onclick="comfirm_exit();"/>
					</td>
			</tr>
		</table>
	</ts:form>
	<div class="error">
		<html:messages id="message">
			<bean:write name="message"/><br/>
		</html:messages>
	</div>
	<div class="message" id="alertMessage">
 		<ts:messages id="messages" message="true">
 			<bean:write name="messages"/><br/>
 		</ts:messages>
    	</div>
</ts:body>
</html>