<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
	<link href="<%=request.getContextPath()%>/B_CSB/css/b_csb_s03.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/B_CSB/js/b_csb_s03.js"></script>
</head>
<ts:body>
	<ts:form action="/B_CSB_S03Save">
	    <html:hidden property="actionFlg"/>
	    <html:hidden property="fromPage"/>
	    <input type="hidden" value="<%=request.getContextPath()%>" id="contextPath"/>
		<t:defineCodeList id="LIST_PAYMENT_METHOD" />
		<t:defineCodeList id="LIST_CURRENCY" />
		<t:defineCodeList id="LIST_PAYMENT_STATUS" />
		<table class="subHeader" cellpadding="0" cellspacing="0">
		  <tr>
		    <td class="Title"><bean:message key="screen.b_csb.title"/></td> 
		  </tr>
		</table> 
		<table class="generalInfo" cellpadding="0" cellspacing="0">
			<tr>
				<td class="header" width="50%">
					<bean:message key="screen.b_csb_03.customerRefund"/>
				</td>
			</tr>
		</table>
		<table class = "generalInfo" cellpadding="0" cellspacing="0">
			<nested:nest property="billAccountInfo">
			<tr>
				<td class="widthBetween tdBorderTopStyle tdBorderLeftStyle"></td>
				<td class="colLeft1 tdBorderTopStyle">
					<bean:message key="screen.b_csb.payee"/><span class="mandatory" ><bean:message key="screen.b_csb.star"/></span>
				</td>
				<td class="tdBorderTopStyle"><bean:message key="screen.b_csb.colon"/></td>
				<td class="colLeft tdBorderTopStyle tdBorderRightStyle">
                    <nested:write property="custName" ></nested:write>
                    <nested:hidden property="custName"></nested:hidden>
				</td>
				<td class="widthBetween"></td>			
				<td style="font-weight:bold;">
					<bean:message key="screen.b_csb2.customerId"/>
				</td>
				<td><bean:message key="screen.b_csb.colon"/></td>
				<td>
					<nested:write property="idCust"></nested:write>
					<nested:hidden property="idCust"></nested:hidden>
				</td>
			</tr>
			<tr>
				<td class="widthBetween tdBorderLeftStyle"></td>
				<td class="colLeft1" width="20%">
					<bean:message key="screen.b_csb2.accountno"/><span class="mandatory" ><bean:message key="screen.b_csb.star"/></span>
				</td>
				<td><bean:message key="screen.b_csb.colon"/></td>
				<td class="colLeft tdBorderRightStyle">
                    <nested:write property="idBillAccount"></nested:write>
                    <nested:hidden property="idBillAccount"></nested:hidden>
                    <html:hidden property="idBillAccount" name="b_csb_s03Form" value="${b_csb_s03Form.billAccountInfo.idBillAccount}"/>
				</td>
				<td class="widthBetween"></td>			
				<td style="font-weight:bold;">
					<bean:message key="screen.b_csb.paymentstatus"/>
				</td>
				<td><bean:message key="screen.b_csb.colon"/></td>
				<td>
					<t:writeCodeValue codeList="LIST_PAYMENT_STATUS" key="F"/>
				</td>
			</tr>
			<tr>
				<td class="widthBetween tdBorderLeftStyle"></td>
				<td class="colLeft1">
					<bean:message key="screen.b_csb.paymentmethod"/><span class="mandatory" ><bean:message key="screen.b_csb.star"/></span>
				</td>
				<td><bean:message key="screen.b_csb.colon"/></td>
				<td class="colLeft tdBorderRightStyle">
				    <nested:select property="paymentMethod">
				        <html:option value="" ><bean:message key="screen.b_csb.blankitem"/></html:option>
                        <html:options collection="LIST_PAYMENT_METHOD" property="id" labelProperty="name"/>
				    </nested:select>
				</td>
				<td class="widthBetween"></td>
				<td style="font-weight:bold;">
					<bean:message key="screen.b_csb2.receiptno"/>
				</td>
				<td><bean:message key="screen.b_csb.colon"/></td>
				<td>
					<html:text property="receiptNo" name="b_csb_s03Form" readonly="true" style="border:0; color: black; background:'#F8F8F8';"/> 
				</td>
			</tr>
			<tr>
				<td class="widthBetween tdBorderLeftStyle tdBorderBottomStyle"></td>
				<td class="colLeft1 tdBorderBottomStyle">
					<bean:message key="screen.b_csb.currency"/>
				</td>
				<td class="tdBorderBottomStyle"><bean:message key="screen.b_csb.colon"/></td>
				<td class="colLeft tdBorderBottomStyle tdBorderRightStyle">
					<t:writeCodeValue codeList="LIST_CURRENCY" key="${b_csb_s03Form.billAccountInfo.billCurrency}"/>
					<nested:hidden property="billCurrency"/>
				</td>
				<td class="widthBetween"></td>
				<td style="font-weight:bold;">
					<bean:message key="screen.b_csb.balance"/>
				</td>
				<td><bean:message key="screen.b_csb.colon"/></td>
				<td>
					<span id="balanceAmt">
					   <fmt:formatNumber value="${b_csb_s03Form.billAccountInfo.totalAmtDue}" pattern="#,##0.00"/>
					</span>
					<nested:hidden property="totalAmtDue" styleId="hiddenBalanceAmt" value="${b_csb_s03Form.billAccountInfo.totalAmtDue}"/>
				</td>
			</tr>
            </nested:nest>
			<tr>
				<td class="widthBetween"></td>
				<td class="colLeft1">
					<bean:message key="screen.b_csb.transactiondate"/><span class="mandatory" ><bean:message key="screen.b_csb.star"/></span>
				</td>
				<td><bean:message key="screen.b_csb.colon"/></td>
				<td class="colLeft">
					<html:text property="transDate" name="b_csb_s03Form" readonly="true" styleClass="DateTextBox" style="width=190px;"/>
	                <t:inputCalendar for="transDate" format="dd/MM/yyyy"/>
				</td>
			</tr>
			<tr>
				<td class="widthBetween"></td>
				<td class="colLeft1">
					<bean:message key="screen.b_csb_03.amountRefunded"/><span class="mandatory" ><bean:message key="screen.b_csb.star"/></span>
				</td>
				<td><bean:message key="screen.b_csb.colon"/></td>
				<td class="colLeft">
				<logic:equal value="new" name="b_csb_s03Form" property="actionFlg">
				    <html:text name="b_csb_s03Form"
						property="amtRefunded"
						styleClass="CommonTextBox" maxlength="18" style="width=190px;"
						onkeypress="return onlyDecNumbers(event)"
						onchange="setAmtRefund(this);" />
				</logic:equal>
				<logic:equal value="edit" name="b_csb_s03Form" property="actionFlg">
				    <html:text name="b_csb_s03Form"
                        property="amtRefunded"
                        styleClass="CommonTextBox" maxlength="18"
                        style="display:none" />
                    <fmt:formatNumber value="${b_csb_s03Form.amtRefunded}" pattern="#,##0.00"/> 
				</logic:equal>
				</td>
			</tr>
			<tr>
				<td class="widthBetween"></td>
				<td class="colLeft1"><bean:message key="screen.b_csb.bankaccount"/></td>
				<td><bean:message key="screen.b_csb.colon"/></td>
				<td class="colLeft" colspan="5">
					<html:select name="b_csb_s03Form" property="bankAcc">
						<option value=""> <bean:message key="screen.b_csb.blankitem"/> </option>
						<logic:present name="b_csb_s03Form" property="cbBankAccList">
							<html:optionsCollection name="b_csb_s03Form" property="cbBankAccList"/>
						</logic:present>
					</html:select>
				</td>
			</tr>
			
			<tr>
                <td class="widthBetween"></td>
                <td class="colLeft1">
                    <bean:message key="screen.b_csb_03.refundReference"/>
                </td>
                <td><bean:message key="screen.b_csb.colon"/></td>
                <td class="colLeft">
                    <html:text name="b_csb_s03Form" property="refundRefernece"  maxlength="20" style="width=300px;" styleClass="RemarkTextBox2"  />
                </td>
            </tr>
            
			<tr>
				<td class="widthBetween"></td>
				<td class="colLeft1">
					<bean:message key="screen.b_csb.remark"/>
				</td>
				<td><bean:message key="screen.b_csb.colon"/></td>
				<td class="colLeft">
					<html:text name="b_csb_s03Form" property="remark"  maxlength="300" style="width=300px;" styleClass="RemarkTextBox2"  />
				</td>
			</tr>
		</table>
		
		<table class = "generalInfo2" cellpadding="0" cellspacing="0">
			<tr>
				<td class="header">
					<bean:message key="screen.b_csb_03.paymentInfo"/>
				</td>
			</tr>
		</table>

		<!-- payment Information -->
		<table class="creditInfo" cellpadding="0" cellspacing="0" id="create_infor" border="1">
			<tr>
				<td class="header"><bean:message key="screen.b_csb.appliedtocol"/></td>
				<td class="header"><bean:message key="screen.b_csb.datecol"/></td>
				<td class="header"><bean:message key="screen.b_csb_03.receiptNo"/></td>
				<td class="header"><bean:message key="screen.b_csb_03.currency"/></td>
				<td class="header"><bean:message key="screen.b_csb_03.amountReceived"/></td>
				<td class="header"><bean:message key="screen.b_csb_03.balanceAmount"/></td>
				<td class="header"><bean:message key="screen.b_csb_03.refundAmount"/></td>
			</tr>

			<nested:notEmpty property="paymentInformationList">
			   <nested:iterate id="paymentInfo" property="paymentInformationList" indexId="indexid">
				<tr>
					<td class="defaultNo" align="center">
					<logic:equal value="new" name="b_csb_s03Form" property="actionFlg">
						<nested:checkbox property="appliedTo" onclick="editPaymentInfo(this,'${indexid}');" value="Y"></nested:checkbox>
					</logic:equal>
					<logic:equal value="edit" name="b_csb_s03Form" property="actionFlg">
					    <nested:checkbox property="appliedTo" disabled="true" value="Y"></nested:checkbox>
                        <nested:hidden property="appliedTo"></nested:hidden>
					</logic:equal>
					</td>
					<td class="defaultText" align="center">
						<nested:write property="receiptDate"/>
						<nested:hidden property="receiptDate"/>
					</td>
					<td class="defaultText" align="center">
						<nested:write property="receiptNo"/>
						<nested:hidden property="receiptNo"/>
					</td>
					<td class="defaultText" align="center">
						<nested:write property="currency"/>
						<nested:hidden property="currency"/>
					</td>
					<td class="defaultText" align="right">
						<fmt:formatNumber value="${paymentInfo.amountReceived}" pattern="#,##0.00"/>
						<nested:hidden property="amountReceived"/>
					</td>
					<td class="defaultText" align="right">
						<fmt:formatNumber value="${paymentInfo.balanceAmount}" pattern="#,##0.00"/>
						<nested:hidden property="balanceAmount"/>
					</td>
					<td class="defaultText" align="right">
					   <logic:equal value="new" name="b_csb_s03Form" property="actionFlg">
						   <nested:equal property="appliedTo" value="Y">
						       <nested:text property="refundAmount" style="width:90%;text-align:right" onkeypress="return onlyDecNumbers(event)" onchange="calPaymentInfo();"/>
						   </nested:equal>
	                       <nested:notEqual property="appliedTo" value="Y">
	                           <nested:text property="refundAmount" readonly="true" style="width:90%;text-align:right" onkeypress="return onlyDecNumbers(event)" onchange="calPaymentInfo();"/>
	                       </nested:notEqual>
                       </logic:equal>
						<logic:equal value="edit" name="b_csb_s03Form" property="actionFlg">
							<nested:text property="refundAmount" readonly="true" style="display:none" />
							<fmt:formatNumber value="${paymentInfo.refundAmount}" pattern="#,##0.00"/>
						</logic:equal>
                    </td>
                </tr>
			  </nested:iterate>
			</nested:notEmpty>

            <tr>
	            <td class="defaultText">&nbsp;</td>
	            <td class="defaultText">&nbsp;</td>
	            <td class="defaultText">&nbsp;</td>
	            <td class="defaultText">&nbsp;</td>
	            <td class="defaultText">&nbsp;</td>
	            <td class="defaultText">&nbsp;</td>
	            <td class="defaultText">&nbsp;</td>
            </tr>

			<tr>
				<td colspan="3" align="right"><bean:message key="screen.b_csb.totals"/></td>
				<td>&nbsp;</td>
				<td align="right">
					<span id="totalAmoutReceive">
					   <fmt:formatNumber value="${b_csb_s03Form.totalAmoutReceive}" pattern="#,##0.00"/>
					   <html:hidden property="totalAmoutReceive" name="b_csb_s03Form"/>
					</span>
				</td>
				<td align="right">
					<span id="totalBalanceAmount">
					   <fmt:formatNumber value="${b_csb_s03Form.totalBalanceAmount}" pattern="#,##0.00"/>
					   <html:hidden property="totalBalanceAmount" name="b_csb_s03Form"/>
					</span>
				</td>
				<td align="right">
					<span id="totalRefundAmount">
					   <fmt:formatNumber value="${b_csb_s03Form.totalRefundAmount}" pattern="#,##0.00"/>
					</span>
					<html:hidden property="totalRefundAmount" name="b_csb_s03Form" styleId="hidTotalRefundAmount"/>
				</td>
			</tr>
		</table>
		
		<!-- Debit Information -->
		<table class="buttonTbl" >
			<tr>
				<td>
				    <nested:notEmpty property="paymentInformationList">
					<input type="button" class="button" value="<bean:message key="screen.b_csb.savebutton"/>" onclick="confirm_save();"/>
					</nested:notEmpty>
					<input type="button" class="button" value="<bean:message key="screen.b_csb.exitbutton"/>" onclick="comfirm_exit();"/>
				</td>
			</tr>
		</table>
	</ts:form>
	<div class="error">
		<html:messages id="message">
			<bean:write name="message"/><br/>
		</html:messages>
	</div>
	<div class="message">
		<ts:messages id="messages" message="true">
			<bean:write name="messages"/><br/>
		</ts:messages>
   	</div>
</ts:body>
</html>