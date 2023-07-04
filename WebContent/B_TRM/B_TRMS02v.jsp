<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>

<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
	   	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
	   	<link rel="stylesheet" type="text/css" href="css/b_trms02.css"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
		<title>Insert title here</title>
		<script>
		function confirm_edit() {
		    var MsgBox = new messageBox();
            var isOk = true;
            var message = "";
            var debitInfo = $("input[type='hidden'][name$='matchID']");
            var matchId = "";

            for (var index = 0; index < debitInfo.length; index++) {
                matchId = matchId+"&matchId="+debitInfo[index].value;
             }

            $.ajax({
                type: "POST",
                url: "checkTrmAlreadyDelete.do?"+matchId.substr(1),
                data:{
                    "creditRef":$("input[name='creditRef']").val()
                },
                async:false,
                success:function(data){
                    if(data.resultFlg==='1'){
                        isOk = false;
                        message = data.msg;
                    }else{
                        isOk=true;
                    }
                },
                error: function(XMLHttpRequest, textStatus, errorThrown){
                    isOk = false;
                    message="System Error!";
                }
            });
            if(isOk){
                document.forms[0].action = "<%=request.getContextPath()%>/B_TRM/B_TRMS02V.do?action=edit";
                document.forms[0].submit();
            }else{
                MsgBox.POPALT(message);
                $("#deleteBtn").removeAttr("onclick");
                $("#deleteBtn").unbind("click");
                $("#deleteBtn").click(function(){
                    MsgBox.POPALT(message);
                });
            }
		}
		function confirm_delete() {
			var msg = "Are you sure you want to delete?";
			var MsgBox = new messageBox();
			if (MsgBox.POPCFM(msg) == MsgBox.YES_CONFIRM) {
			    var isOk = true;
			    var message = "";
			    var debitInfo = $("input[type='hidden'][name$='matchID']");
			    var matchId = "";

			    for (var index = 0; index < debitInfo.length; index++) {
			        matchId = matchId+"&matchId="+debitInfo[index].value;
			     }

			    $.ajax({
	                type: "POST",
	                url: "checkTrmAlreadyDelete.do?"+matchId.substr(1),
	                data:{
	                    "creditRef":$("input[name='creditRef']").val()
	                },
	                async:false,
	                success:function(data){
	                    if(data.resultFlg==='1'){
	                        isOk = false;
	                        message = data.msg;
	                    }else{
	                        isOk=true;
	                    }
	                },
	                error: function(XMLHttpRequest, textStatus, errorThrown){
	                    isOk = false;
	                    message="System Error!";
	                }
	            });
			    if(isOk){
		             document.forms[0].action = "<%=request.getContextPath()%>/B_TRM/B_TRMS02D.do";
		             document.forms[0].submit();
			    }else{
			        MsgBox.POPALT(message);
			        $("#editBtn").removeAttr("onclick");
			        $("#editBtn").unbind("click");
			        $("#editBtn").click(function(){
			            MsgBox.POPALT(message);
			        });
			    }
			}
		}
		function confirm_exit() {
			document.forms[0].submit();
		}
		</script>
	</head>
	<ts:body>
		<table class="subHeader">
			<tr>
		    	<td><bean:message key="screen.b_trm.title"/></td>
		  	</tr>
		</table> 
		<ts:form action="/B_TRMS01SCR" method="POST">
            <table class="information tdnowap" cellpadding="0" cellspacing="1" >
            	<colgroup>
                <col width="20%">
                <col width="35%">
                <col width="15%">
                <col width="30%">
                </colgroup>
            	<tr>
				    <td colspan="4" class="header">
				    	<bean:message key="screen.b_trm.creditInfo"/>
				    </td>
				</tr>
                <tr>
                    <td colspan="4" style="height:5px"></td>
                </tr>
	    		<tr>
	    			<td>
				  		<bean:message key="screen.b_trm.customerName"/><span class="mandatory" ><bean:message key="screen.b_trm.star"/></span>
				  	</td>
				  	<td>
			  			<bean:message key="screen.b_trm.colon"/>&nbsp;
			  			<logic:iterate id="p" name="b_trmForm" property="cbCustomer">
							<c:if test="${b_trmForm.customer == p.value}">
								${p.label}
							</c:if>
						</logic:iterate>
				  	</td>
				  	<td>
                        <bean:message key="screen.b_trm.customerID" />
                     </td>
                     <td>
                        <bean:message key="screen.b_trm.colon"/>&nbsp;<span id="txtCustomerIdDisplay">
                            <logic:notEmpty name="b_trmForm" property="customer">
                               <bean:write name="b_trmForm" property="customer"/>
                            </logic:notEmpty></span>
                        <html:hidden name="b_trmForm" property="customer" styleId="customer"/>
                     </td>
				  </tr>
				  
				  <logic:notEqual value="CST" name="b_trmForm" property="gblSetValue">
				  <tr>
				  	<td>
				  		<bean:message key="screen.b_trm.billAccNo"/><span class="mandatory" ><bean:message key="screen.b_trm.star"/></span>
				  	</td>
				  	<td>
				  		<bean:message key="screen.b_trm.colon"/>&nbsp;
				  		<logic:iterate id="p" name="b_trmForm" property="cbBillAccount">
							<c:if test="${b_trmForm.billAccount == p.value}">
								${p.label}
							</c:if>
						</logic:iterate>
						<html:hidden name="b_trmForm" property="billAccount"/>
				  	</td>
				  	<td>
                          <bean:message key="screen.b_trm.currency"/>
                      </td>
                      <td>
                          <bean:message key="screen.b_trm.colon"/>&nbsp;
                          <bean:write name="b_trmForm" property="currency"/>
                     </td>
				  </tr>
				  </logic:notEqual>
				  <tr>
                    <td><bean:message key="screen.b_trm.transactionDate" /><span class="mandatory" ><bean:message key="screen.b_trm.star"/></span></td>
                    <td><bean:message key="screen.b_trm.colon"/>&nbsp;
                    <logic:notEmpty property="transationDate" name="b_trmForm">
                        <bean:write property="transationDate" name="b_trmForm"/>
                    </logic:notEmpty>  
                    </td>
                  </tr>
                  <tr>
                    <td colspan="4" style="height:10px"></td>
                  </tr>
				  <tr>
                  <td colspan="4" width="100%">
                      <fieldset style="width: 100%;">
                          <table class="information tdnowap" cellpadding="0" cellspacing="0">
                          <colgroup>
                          <col width="20%">
                          <col width="35%">
                          <col width="15%">
                          <col width="30%">
                          </colgroup>
		                  <tr>
		                    <td>
		                        <bean:message key="screen.b_trm.creditRef"/><span class="mandatory" ><bean:message key="screen.b_trm.star"/></span>
		                    </td>
		                    <td>
		                        <bean:message key="screen.b_trm.colon"/>&nbsp;
		                        <logic:iterate id="p" name="b_trmForm" property="cbCreditRef">
		                            <c:if test="${b_trmForm.creditRef == p.value}">
		                                ${p.label}
		                            </c:if>
		                        </logic:iterate>
		                        <html:hidden name="b_trmForm" property="creditRef"/>
		                    </td>
		                  </tr>
                          <tr>
                              <td>
                                  <bean:message key="screen.b_trm.date"/>
                              </td>
                              <td>
                                  <bean:message key="screen.b_trm.colon"/>&nbsp;
                                  <bean:write name="b_trmForm" property="date"/>
                              </td>
                              <td>
                                  <bean:message key="screen.b_trm.creditBal"/>
                              </td>
                              <td>
                                  <bean:message key="screen.b_trm.colon"/>&nbsp;
                                  <span id="creditBalance">
                                   <logic:notEmpty name="b_trmForm" property="creditBalance">
                                      <bean:define id="cbValue" name="b_trmForm" property="creditBalance"></bean:define>
                                      <fmt:formatNumber value="<%=(new java.math.BigDecimal(cbValue.toString()).negate()) %>" pattern="#,##0.00"/>
                                   </logic:notEmpty>
                                  </span>
                              </td>
                         </tr>
                         <tr>
                              <td>
                                  <bean:message key="screen.b_trm.oriAmt"/>
                              </td>
                              <td>
                                  <bean:message key="screen.b_trm.colon"/>&nbsp;
                                   <logic:notEmpty name="b_trmForm" property="origAmt">
                                  <bean:define id="orValue" name="b_trmForm" property="origAmt"></bean:define>
                                  <fmt:formatNumber value="<%=(new java.math.BigDecimal(orValue.toString()).negate()) %>" pattern="#,##0.00"/>
                                  </logic:notEmpty>
                                  <input type="hidden" id="origAmt" value="<bean:write name="b_trmForm" property="origAmt"/>"/>
                              </td>
                          </tr>    
                          </table>
                      </fieldset>
                  </td>
                  </tr>
	    	</table>
	    	<table class="subHeaderInfo" >
		  		<tr>
		    	<td><bean:message key="screen.b_trm.debitInfo"/></td>
		  		</tr>
			</table>
			<table  class="debitInfo" width="100%">
				<tr>
					<td class="header" width="13%">
						<bean:message key="screen.b_trm.appliedTo"/>
					</td>
					<td class="header" width="13%">
						<bean:message key="screen.b_trm.date"/>
					</td>
					<td class="header" width="19%">
						<bean:message key="screen.b_trm.debitRef"/>
					</td>
					<td class="header" width="10%">
						<bean:message key="screen.b_trm.currency"/>
					</td>
					<td class="header"width="15%">
						<bean:message key="screen.b_trm.orgAmount"/>
					</td>
					<td class="header"width="15%">
						<bean:message key="screen.b_trm.amtDue"/>
					</td>
					<td class="header"width="15%">
						<bean:message key="screen.b_trm.payment"/>
					</td>
				</tr>
				<nested:present property="debitInfos">
					<nested:iterate id="debitInfo" property="debitInfos" indexId="idx" >
						<tr>			
							<td class="colCenter">
								<nested:checkbox property="chkAppliedTo" disabled="true" value="1"/>
								<nested:hidden property="chkAppliedTo" />
								<nested:hidden property="matchID" />
							</td>
							<td class="colCenter">
								<nested:write property="gdcDateDebitRef" />
								<nested:hidden property="gdcDateDebitRef" />
							</td>
							<td class="colCenter">
								<nested:write property="gdcDebitReference"/>
								<nested:hidden property="gdcDebitReference" />
							</td>
							<td class="colCenter">
								<nested:write property="gdcCurrency"/>
								<nested:hidden property="gdcCurrency" />
							</td>
							<td class="colRight">
								<fmt:formatNumber value="${debitInfo.gdcOriginalAmountCR}" pattern="#,##0.00"/>
								<nested:hidden property="gdcOriginalAmountCR" />
							</td>
							<td class="colRight">
								<fmt:formatNumber value="${debitInfo.gdcAmountDue}" pattern="#,##0.00"/>
								<nested:hidden property="gdcAmountDue"/>
							</td>
							<td class="colRight">
								<fmt:formatNumber value="${debitInfo.tbxPayment}" pattern="#,##0.00"/>
								<nested:hidden property="tbxPayment"/>
								<c:choose>
                                    <c:when test="${empty debitInfo.tbxPaymentPrev}">
                                        <nested:hidden value="${debitInfo.tbxPayment}" property="tbxPaymentPrev"/>
                                    </c:when>
                                    <c:otherwise>
                                        <nested:hidden property="tbxPaymentPrev"/>
                                    </c:otherwise>
                                </c:choose>
							</td>
						</tr>
					</nested:iterate>
				</nested:present>
			</table>
	    	<table class="buttonGroup" cellpadding="0" cellspacing="0">
			  	<tr>
					<td>
						<%
						String accessType = ((nttdm.bsys.common.fw.BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT")).getUserAccessInfo("B", "B-TRM").getAccess_type();
						if(accessType.equals("2")) {
						%>
						<input type="button" class="button" value="<bean:message key="screen.b_trm.edit"/>" onclick="confirm_edit();" id="editBtn"/>
						<input type="button" class="button" value="<bean:message key="screen.b_trm.delete"/>" onclick="confirm_delete();" id="deleteBtn"/>
						<%} %>
						<input type="button" class="button" value="<bean:message key="screen.b_trm.exit"/>" onclick="confirm_exit();"/>
					</td>	
				</tr>
			</table>
		</ts:form>
		<div class="error">
			<c:if test="${not empty b_trmForm.lastMsg}">
				${b_trmForm.lastMsg}
			</c:if>
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
</html:html>

