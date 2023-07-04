<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="/WEB-INF/bs" prefix="bs" %>
<%@page import="nttdm.bsys.common.fw.BillingSystemUserValueObject"%>
<%@page import="nttdm.bsys.common.util.CommonUtils"%> 
<%@page import="java.util.List"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
	<head>
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
		<link href="${pageContext.request.contextPath}/B_BIL/css/b_bil.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
		<title><bean:message key="screen.b_bil_s01.title"/></title>
		<script type="text/javascript">
		$(document).ready(function() {
			//onload function
			var screenWidth = window.screen.width;
			var contentDivWidth = screenWidth-270;
			// set page width
			$('#contentDiv').css("width",contentDivWidth);
		});
		var isDelBtn = false;
		var isReActiveBtn = false;
		// check which button was pressed
		document.onclick = function(whichOne){ 
			whichOne = whichOne ? whichOne : window.event;
			thisButton = whichOne.target ? whichOne.target : whichOne.srcElement;
			if (( thisButton.name ) && ( thisButton.name == 'forward_delete' )) { 
				isDelBtn = true;
			} else {
				isDelBtn = false;
			}

            // Re-Activate button clicked.
            if (( thisButton.name ) && ( thisButton.name == 'forward_reActivate' )) { 
                isReActiveBtn = true;
            } else {
                isReActiveBtn = false;
            }
		};
		function checkBeforeSubmit(){
			if(isDelBtn){
				var MsgBox = new messageBox();
				if (MsgBox.POPCFM($('#hiddenMsgDeleteConfirmation').val()) == MsgBox.YES_CONFIRM){
				
	                var isOk = true;
	                var message = "";
	                var idRef = $("input[type='hidden'][name='idRef']").val();

	                $.ajax({
	                    type: "POST",
	                    url: "checkInvoiceDeleteStatus.do",
	                    data:{
	                        "idRef":idRef
	                    },
	                    async:false,
	                    success:function(data){
	                        if(data.resultFlg =='1'){
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
	                    return true;
	                }else{
	                    MsgBox.POPALT(message);
	                    return false;
	                }
				}else
					return false;
			} else if (isReActiveBtn) {
			    var MsgBox = new messageBox();
                var ERR4SC017_info = document.getElementById("ERR4SC017_info").value;
                if (MsgBox.POPCFM(ERR4SC017_info) == MsgBox.YES_CONFIRM) {
                    return true;
                } else {
                    return false;
                }
			}else{
				return true;
			}
		}
		function openB_CPM_View(url) {
			var width = window.screen.width*90/100;
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
		function btnNewCsb(){
			var payer = document.getElementById("payer").value;
			var idBillAccount = document.getElementById("idBillAccount").value;
			document.forms[0].action = "<%=request.getContextPath()%>/B_CSB/B_CSBS02SCR.do?payer="+payer+"&idBillAccount="+escape(idBillAccount);
			document.forms[0].submit();
		}
		function btnTM(){
			var customer = document.getElementById("payer").value;
			var idBillAccount = document.getElementById("idBillAccount").value;
			var cboCustomerName = document.getElementById("custNameForTm").value;
			
			document.forms[0].action = "<%=request.getContextPath()%>/B_TRM/B_TRMS02AD.do?action=customerChange&customer="+escape(customer)+"&billAccount="+escape(idBillAccount)+"&cboCustomerName="+escape(cboCustomerName);
			document.forms[0].submit();
		}
		function printConfirm(){
			var hidBillAmount = document.getElementById("hidBillAmount").value;
			var ERR4SC014_info = document.getElementById("ERR4SC014_info").value;
			if ("0.00"==hidBillAmount) {
				var msg = new messageBox("");
				var isConfirm = (msg.POPCFM(ERR4SC014_info) == msg.YES_CONFIRM);
				if (isConfirm) {
					printAutSignConfirm();
					return true;
				} else {
					return false;
				}
			}
			printAutSignConfirm();
			return true;
		}
		//. prompt confirmation for authorised signature printing
		function printAutSignConfirm(){
			var svalue=document.getElementById("hidsysBaseVal").value;
			var ERR4SC19_info = document.getElementById("ERR4SC19_info").value;
			if ("MY02"==svalue) {
				var msg = new messageBox("");
				var isConfirm = (msg.POPCFM(ERR4SC19_info) == msg.YES_CONFIRM);
				if (isConfirm) {
					//Click Yes, pass $AutSign=No
					document.getElementById("autSign").value="No";
				} else {
					//Click No, pass $AutSign='Yes'
					document.getElementById("autSign").value="Yes";
				}
			}
		}
		function clickExit() {
			window.close();
		}
		</script>
	</head>
	<ts:body>
		<%
			BillingSystemUserValueObject uvo = (BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT");
		 	String csbAccessRight = CommonUtils.getAccessRight(uvo, "B-CSB");
		 	String tmAccessRight = CommonUtils.getAccessRight(uvo, "B-TRM");
		%>
		<bean:define id="csbAccessRightBean" value="<%=csbAccessRight %>"/>
		<bean:define id="tmAccessRightBean" value="<%=tmAccessRight %>"/>
		<ts:form action="/SC_B_BIL_S02_01DSP" onsubmit="return checkBeforeSubmit();">
		<bean:define id="LOAD_OBJECT" name="_b_bilForm" property="loadObject"></bean:define>
			<div id="contentDiv">
			<t:defineCodeList id="LIST_PAYMENT_METHOD"/>
			<h1 class="title">
				<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
					<bean:message key="screen.b_bil.creditNote"/>
				</c:if>
				<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'IN'}">
					<bean:message key="screen.b_bil.invoiceNote"/>
				</c:if>
				<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'DN'}">
					<bean:message key="screen.b_bil.debitNote"/>
				</c:if>
			</h1>
			<!-- Add #156 Start-->
			<html:hidden property="billCnAmtNegative"></html:hidden>
			<!-- Add #156 End-->
			<table class ="header1" cellpadding="0" cellspacing="0">
				<tr>
					<td class="col1Top" width="40%" valign="top">
						<table>
							<tr>
								<td width="40%">
									<font size="4px" style="headerInfo">
										<b><bean:message key="screen.b_bil.customerDetailsHeader"/>&nbsp;</b>
									</font>			
								</td>
								<td align="right">
									<bean:message key="screen.b_bil.customerID"/>
									<bean:message key="screen.b_bil.s04.colon"/>
								</td>
								<td>
									${_b_bilForm.map.headerInfo.ID_CUST}
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.customerName"/>
									<bean:message key="screen.b_bil.colon"/>
								</td>
								<td>
									${_b_bilForm.map.headerInfo.CUST_NAME}
									<input type="hidden" id="custNameForTm" value="${_b_bilForm.map.headerInfo.CUST_NAME}">
								</td>
								<td></td>
							</tr>
							<tr>
								<td align="right">
									<t:writeCodeValue codeList="LIST_ADDRESS" key="${_b_bilForm.map.headerInfo.ADR_TYPE}"/>
									<bean:message key="screen.b_bil.colon"/>
								</td>
								<td>
									${_b_bilForm.map.headerInfo.ADDRESS1}
								</td>
								<td></td>
							</tr>
							<tr>
								<td align="right">
									&nbsp;
								</td>
								<td>
									<bean:message key="screen.b_bil.addBlank"/>
									${_b_bilForm.map.headerInfo.ADDRESS2}
								</td>
								<td></td>
							</tr>
							<tr>
								<td align="right">
									&nbsp;
								</td>
								<td>
									<bean:message key="screen.b_bil.addBlank"/>
									${_b_bilForm.map.headerInfo.ADDRESS3}
								</td>
								<td></td>
							</tr>
							<tr>
								<td align="right">
									&nbsp;
								</td>
								<td>
									<bean:message key="screen.b_bil.addBlank"/>
									${_b_bilForm.map.headerInfo.ADDRESS4}
								</td>
								<td></td>
							</tr>
							<tr>
								<td align="right">	
									<bean:message key="screen.b_bil.tel"/>
									<bean:message key="screen.b_bil.colon"/>
								</td>
								<td>
									${_b_bilForm.map.headerInfo.TEL_NO}
								</td>
								<td></td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.fax"/>
									<bean:message key="screen.b_bil.colon"/>
								</td>
								<td>
									${_b_bilForm.map.headerInfo.FAX_NO}
								</td>
								<td></td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.attn"/>
									<bean:message key="screen.b_bil.colon"/>
								</td>
								<td>
									
									<c:if test="${_b_bilForm.map.headerInfo.CUSTOMER_TYPE eq 'CORP'}">
										<c:if test="${not empty _b_bilForm.map.headerInfo.CONTACT_TYPE}">
									    	[${_b_bilForm.map.headerInfo.CONTACT_TYPE}]${_b_bilForm.map.headerInfo.CONTACT_NAME}
									    </c:if>
									</c:if>
								</td>
								<td></td>
							</tr>
							<%-- <tr>
								<td align="right">
									<bean:message key="screen.b_bil.email"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									${_b_bilForm.map.headerInfo.CONTACT_EMAIL}
								</td>
							</tr> --%>
							<tr>
						  	 	<td align="right" style="text-align:right">
						  	 		<bean:message key="screen.b_bil.emailto"/>
						  	 		<bean:message key="screen.b_bil.colon"/>
						  	 	</td>
						  	 	<td >
						  	 		<label id="emailto">
						  	 			<c:forTokens items="${_b_bilForm.map.headerInfo.CONTACT_EMAIL}" delims=";" var="email" varStatus="vs">
											<c:if test="${!vs.last}">
												<c:out value="${email}"/>;<br/>
											</c:if>
											<c:if test="${vs.last}">
												<c:out value="${email}"/>
											</c:if>
										</c:forTokens>
						  	 		</label>
						  	 	</td>
						  	 	<td></td>
		  	 				</tr> 
						  	 <tr>
						  	 	<td class = "tdleft" style="text-align:right">
						  	 		<bean:message key="screen.b_bil.emailcc"/>
						  	 		<bean:message key="screen.b_bil.colon"/>
						  	 	</td>
						  	 	<td class = "tdright">
						  	 		<label id="emailcc">
						  	 			<c:forTokens items="${_b_bilForm.map.headerInfo.CONTACT_EMAIL_CC}" delims=";" var="email" varStatus="vs">
											<c:if test="${!vs.last}">
												<c:out value="${email}"/>;<br/>
											</c:if>
											<c:if test="${vs.last}">
												<c:out value="${email}"/>
											</c:if>
										</c:forTokens>
						  	 		</label>
						  	 	</td>
						  	 	<td></td>
						  	 </tr>
						</table>
					</td>
					<td class="col2Top" width="1%" style="background:white">
						&nbsp;
					</td>
					<td class="col3Top" width="39%" valign="top">
						<table>
							<tr>				
							</tr>
							<tr>
								<td align="right">
									<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
										<bean:message key="screen.b_bil.creditNote"/>
									</c:if>
									<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'IN'}">
										<bean:message key="screen.b_bil.invoiceNote"/>
									</c:if>
									<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'DN'}">
										<bean:message key="screen.b_bil.debitNote"/>
									</c:if>
									<bean:message key="screen.b_bil.date"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									${_b_bilForm.map.headerInfo.DATE_REQ_FOM}
								</td>
							</tr>
							<tr>
								<td align="right">
									<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
										<bean:message key="screen.b_bil.creditNote"/>
									</c:if>
									<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'IN'}">
										<bean:message key="screen.b_bil.invoiceNote"/>
									</c:if>
									<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'DN'}">
										<bean:message key="screen.b_bil.debitNote"/>
									</c:if>
									<bean:message key="screen.b_bil.billingNo"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									${_b_bilForm.map.headerInfo.ID_REF}
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.billingAccountNo"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									${_b_bilForm.map.headerInfo.BILL_ACC}
									
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.paymentMethod"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									<t:writeCodeValue codeList="LIST_PAYMENT_METHOD" key="${_b_bilForm.map.headerInfo.PAY_METHOD}"/>
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.qcsReference"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									${_b_bilForm.map.headerInfo.ID_QCS_TXT}
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.quoReference"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									${_b_bilForm.map.headerInfo.QUO_REF_TXT}
								</td>
							</tr>
							<tr>
								<td align="right">	
									<bean:message key="screen.b_bil.customerPO"/>
								</td>
								<td >
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									${_b_bilForm.map.headerInfo.CUST_PO_TXT}
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.acManager"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									${_b_bilForm.map.headerInfo.ID_CONSULT_NAME}
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.currency"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									${_b_bilForm.map.headerInfo.BILL_CURRENCY}
								</td>
							</tr>
							<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'IN' or _b_bilForm.map.headerInfo.BILL_TYPE eq 'DN' or _b_bilForm.map.headerInfo.BILL_TYPE eq 'NT'}">
								<tr>
									<td align="right">
										<bean:message key="screen.b_bil.term"/>
									</td>
									<td>
										<bean:message key="screen.b_bil.colon"/>&nbsp;
											${_b_bilForm.map.headerInfo.TERM}
									</td>
								</tr>
							</c:if>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.DueDate"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									${_b_bilForm.map.headerInfo.DUE_DATE_FOM}
								</td>
							</tr>
							<%-- <c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'IN' or _b_bilForm.map.headerInfo.BILL_TYPE eq 'DN'}">
								<tr>
									<td colspan="2">
										&nbsp;
									</td>
								</tr>
							</c:if> --%>
							<tr>
								<td align="right" valign="top">
									<bean:message key="screen.b_bil.deliveryby"/>
									
								</td>
								<td>
								<bean:message key="screen.b_bil.colon"/>
									<c:if test="${_b_bilForm.map.headerInfo.DELIVERY_EMAIL == 1}">
	   									<input type="checkbox" name="headerData.deliverymail" value="1" checked disabled="disabled">
	   								</c:if>
								   <c:if test="${_b_bilForm.map.headerInfo.DELIVERY_EMAIL != 1}">
								   		<input type="checkbox" name="headerData.deliverymail" value="0" disabled="disabled">
								   </c:if>
								   <bean:message key="screen.b_bil.email"/>
									<c:if test="${_b_bilForm.map.headerInfo.DELIVERY == '3'}">
									  	<input type="radio" name="headerData.delivery" value="3" checked="checked"/>
									  	<bean:message key="screen.b_bil.none"/>
								  	</c:if>
								  	<c:if test="${_b_bilForm.map.headerInfo.DELIVERY != '3'}">
									  	<input type="radio" name="headerData.delivery" value="3" disabled="disabled"/>
									  	<bean:message key="screen.b_bil.none"/>
								  	</c:if>	 
								  	<c:if test="${_b_bilForm.map.headerInfo.DELIVERY == '2'}">
									  	<input type="radio" name="headerData.delivery" value="2" checked="checked"/>
									  	<bean:message key="screen.b_bil.courier"/>
								  	</c:if>
								  	<c:if test="${_b_bilForm.map.headerInfo.DELIVERY != '2'}">
									  	<input type="radio" name="headerData.delivery" value="2" disabled="disabled"/>
									  	<bean:message key="screen.b_bil.courier"/>
								  	</c:if>
								  	<br/>
	                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<c:if test="${_b_bilForm.map.headerInfo.DELIVERY == '1'}">
									  	<input type="radio" name="headerData.delivery" value="1" checked="checked"/>
									  	<bean:message key="screen.b_bil.post"/>
								  	</c:if>
								  	<c:if test="${_b_bilForm.map.headerInfo.DELIVERY != '1'}">
									  	<input type="radio" name="headerData.delivery" value="1" disabled="disabled"/>
									  	<bean:message key="screen.b_bil.post"/>
								  	</c:if>&nbsp;
									<c:if test="${_b_bilForm.map.headerInfo.DELIVERY == '4'}">
									  	<input type="radio" name="headerData.delivery" value="4" checked="checked"/>
									  	<bean:message key="screen.b_bil.byhand"/>	  
								  	</c:if>
								  	<c:if test="${_b_bilForm.map.headerInfo.DELIVERY != '4'}">
									  	<input type="radio" name="headerData.delivery" value="4" disabled="disabled"/>
									  	<bean:message key="screen.b_bil.byhand"/>
								  	</c:if>
								</td>
								</tr>
						</table>
					</td>
					<td class="col4Top" width="1%" style="background:white">
						&nbsp;
					</td>
					<td class="col5Top" width="20%" rowspan="15" valign="top" align="center" style="background-color:white">
						<div style="background-color:#ccccff;height=50%;align: center;">
							<font class="statusBold">
								<bean:message key="screen.b_bil.status"/>
							</font>
							<br/>
							<c:if test="${_b_bilForm.map.headerInfo.IS_CLOSED eq '1'}">
								<bean:message key="screen.b_bil.closed"/>/
							</c:if>
							<c:if test="${_b_bilForm.map.headerInfo.IS_CLOSED ne '1'}">
								<bean:message key="screen.b_bil.open"/>/
							</c:if>
							<c:if test="${_b_bilForm.map.headerInfo.IS_SETTLED eq '1'}">
								<bean:message key="screen.b_bil.fullSettled"/>
							</c:if>
							<c:if test="${_b_bilForm.map.headerInfo.IS_SETTLED ne '1'}">
								<c:if test="${_b_bilForm.map.headerInfo.PAID_AMOUNT ne 0}">
									<bean:message key="screen.b_bil.partialSettled"/>
								</c:if>
								<c:if test="${_b_bilForm.map.headerInfo.PAID_AMOUNT eq 0}">
									<bean:message key="screen.b_bil.outstanding"/>
								</c:if>
							</c:if>
							<c:if test="${_b_bilForm.map.headerInfo.IS_DELETED eq '1'}">
								/<bean:message key="screen.b_bil.canceled"/>
							</c:if>
							<br>&nbsp;
							<br>&nbsp;
							<br>&nbsp;
						</div>
						<div  style="background-color:#ffcccc;height=50%;align: center;">
							<font class="preparedBy">
								<bean:message key="screen.b_bil.preparedBy"/>
							</font>
							<br/>
							<center>
								${_b_bilForm.map.headerInfo.PREPARED_BY_NAME}
							</center>
							<br/>
							<br/>	
							<font class="preparedBy">	
								<b><bean:message key="screen.b_bil.date"/></b>
							</font>
							<br/>
							<center>
								<fmt:formatDate value="${_b_bilForm.map.headerInfo.DATE_CREATED}" pattern="dd/MM/yyyy"/>
							</center>
							<br/>&nbsp;
							<!-- #194 Start -->
							<c:choose>
								<c:when test="${_b_bilForm.map.headerInfo.dispDuplicate eq '1'}">
									<ts:submit value="Duplicate" styleClass="button" property="forward_duplicate" />
									<br/>&nbsp;
								</c:when>
                                <c:otherwise>
                                    <br/>&nbsp;
                                </c:otherwise>
							</c:choose>
							<!-- #194 End -->
							<br/>&nbsp;
							<c:choose>
								<c:when test="${csbAccessRightBean eq '2' 
												&& _b_bilForm.map.headerInfo.IS_DELETED eq '0' 
												&& (_b_bilForm.map.headerInfo.BILL_TYPE eq 'IN' or _b_bilForm.map.headerInfo.BILL_TYPE eq 'DN')}">
									<html:button property="forward_newCsb" onclick="btnNewCsb()"><bean:message key="screen.b_bil.newCsb"/></html:button>
									<br/>&nbsp;
								</c:when>
								<c:otherwise>
									<br/>&nbsp;
								</c:otherwise>
							</c:choose>
							<c:choose>
                                <c:when test="${tmAccessRightBean eq '2' 
                                                && _b_bilForm.map.headerInfo.IS_DELETED eq '0' 
                                                && _b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'
                                                && _b_bilForm.map.headerInfo.PAID_AMOUNT eq 0}">
                                    <html:button property="forward_tm" onclick="btnTM()"><bean:message key="screen.b_bil.tm"/></html:button>
                                    <br/>&nbsp;
                                </c:when>
                                <c:otherwise>
                                    <br/>&nbsp;
                                </c:otherwise>
                            </c:choose>
						</div>
					</td>
				</tr>
			</table>
			<br/>
			<table cellspacing="0" cellpadding="0" style="width:100%;border-top: solid 2px #8cb0f8;border-bottom: solid 2px #8cb0f8;border-left: solid 2px #8cb0f8;border-right: solid 2px #8cb0f8;">
				<tr>
					<td>
						<table class="resultInfo1" cellspacing="0" cellpadding="0">
							<col width="6%"/>
							<col width="47%"/>
							<col width="10%"/>
							<col width="12%"/>
							<col width="15%"/>
							<col width="10%"/>
							<tr class="header">
								<td style="background-color:rgb(140,176,248)" width="6%" style="padding-left:10px;"><bean:message key="screen.b_bil.item"/></td>
								<td style="background-color:rgb(140,176,248)" width="47%"><bean:message key="screen.b_bil.billingDescription"/></td>
								<td style="background-color:rgb(140,176,248)" width="10%" style="text-align:right;"><bean:message key="screen.b_bil.quantity"/></td>
								<td style="background-color:rgb(140,176,248)" width="12%" style="text-align:right;"><bean:message key="screen.b_bil.unitPrice"/></td>
								<td style="background-color:rgb(140,176,248)" width="15%" style="text-align:right;"><bean:message key="screen.b_bil.totalAmount"/></td>
								<td style="background-color:rgb(140,176,248)" width="10%" style="padding-left:10px;">
									<c:if test="${'2' ne _b_bilForm.map.headerInfo.GSTAPPLYFLAG}">
								   		<bean:message key="screen.b_bil.GSTHeader"/>
								   	</c:if>
								   	<c:if test="${'2'eq _b_bilForm.map.headerInfo.GSTAPPLYFLAG}">
								   		<b>${taxStr}</b>
								   	</c:if>
								</td>
								
							</tr>
							<!-- Detail content -->
							<c:set var="subTotal" value="0"></c:set>
							<c:set var="taxableSubTotal" value="0"></c:set>
							<c:set var="nonTaxableSubTotal" value="0"></c:set>
							<c:set var="index" value="0"></c:set>
							<c:set var="lastItem" value=""></c:set>
							<c:set var="subDiscAmt" value="0"></c:set>
							<c:forEach items="${_b_bilForm.map.detailInfo}" var="item" varStatus="status">
								<c:choose>
									<c:when test="${item.ITEM_CAT eq '0'}">
										<c:if test="${not empty lastItem}">
											<c:if test="${lastItem.ITEM_CAT eq '1'}">
												<c:if test="${subDiscAmt != 0}">
													<tr>
														<td colspan="6">&nbsp;</td>
													</tr>
													<tr>
														<td valign="top">&nbsp;</td>
											 	        <td colspan="3">
											 	           <b><i><bean:message key="screen.b_bil.discount"/></i></b>
											 	        </td>
											 	        <td style="text-align:right;" valign="top">
											 	          <fmt:formatNumber value="${subDiscAmt}" pattern="#,##0.00"/>
											 	        </td>
											 	        <td valign="top" style="padding-left:10px;">
											 	        <c:if test="${_b_bilForm.map.gstCheck eq 'TAX_CODE'}">
															<c:choose>
															    <c:when test="${not empty lastItem.TAX_CODE}">
															  		${lastItem.TAX_CODE}
																</c:when>
																<c:otherwise>
																	&nbsp;
																</c:otherwise>
															</c:choose>
														</c:if>
														<c:if test="${_b_bilForm.map.gstCheck eq 'TAX_RATE'}">
															<c:choose>
															    <c:when test="${not empty lastItem.TAX_RATE}">
															  		${lastItem.TAX_RATE}%
																</c:when>
																<c:otherwise>
																	&nbsp;
																</c:otherwise>
															</c:choose>
														</c:if>
														</td>
										 	        </tr>
												</c:if>
												<c:choose>
													<c:when test="${lastItem.BILL_FROM_TEXT ne lastItem.BILL_TO_TEXT || lastItem.ISDISPLAY_BILLINGPERIOD eq '1'}">
														<tr>
															<td colspan="6">&nbsp;</td>
														</tr>
														<tr>
															<td>&nbsp;</td>
															<td colspan="5">
															     <bean:message key="screen.b_bil.billingPeriod"/>
														         <bean:message key="screen.b_bil.colon1"/>
														         <bean:message key="screen.b_bil.from"/>
														             ${lastItem.BILL_FROM_TEXT}
														         <bean:message key="screen.b_bil.toInfo"/>
														             ${lastItem.BILL_TO_TEXT}
															</td>
														</tr>
													</c:when>
												</c:choose>
												<tr>
													<td colspan="6">&nbsp;</td>
												</tr>
											</c:if>
										</c:if>
										<tr>
											<td>&nbsp;</td>
											<td style="width:470px;word-wrap: break-word;white-space : normal">
												<div style="color:#CD853F;">
													<pre>${item.ITEM_DESC_TEXT}</pre>
												</div>
											</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td colspan="6">&nbsp;</td>
										</tr>
										<c:set var="subDiscAmt" value="0"></c:set>
									</c:when>
									<c:otherwise>
										<c:choose> 	    	
											<c:when test="${item.ITEM_LEVEL eq '0'}">
												<c:if test="${not empty lastItem}">
													<c:if test="${lastItem.ITEM_CAT eq '1'}">
														<c:if test="${subDiscAmt != 0}">
															<tr>
																<td colspan="6">&nbsp;</td>
															</tr>
															<tr>
																<td valign="top">&nbsp;</td>
													 	        <td colspan="3">
													 	           <b><i><bean:message key="screen.b_bil.discount"/></i></b>
													 	        </td>
													 	        <td style="text-align:right;" valign="top">
													 	          <fmt:formatNumber value="${subDiscAmt}" pattern="#,##0.00"/>
													 	        </td>
													 	        <td valign="top" style="padding-left:10px;">
													 	        <c:if test="${_b_bilForm.map.gstCheck eq 'TAX_CODE'}">
																	<c:choose>
																	    <c:when test="${not empty lastItem.TAX_CODE}">
																	  		${lastItem.TAX_CODE}
																		</c:when>
																		<c:otherwise>
																			&nbsp;
																		</c:otherwise>
																	</c:choose>
																</c:if>
																<c:if test="${_b_bilForm.map.gstCheck eq 'TAX_RATE'}">
																	<c:choose>
																	    <c:when test="${not empty lastItem.TAX_RATE}">
																	  		${lastItem.TAX_RATE}%
																		</c:when>
																		<c:otherwise>
																			&nbsp;
																		</c:otherwise>
																	</c:choose>
																</c:if>
																</td>
												 	        <tr>
														</c:if>
														<c:choose>
															<c:when test="${lastItem.BILL_FROM_TEXT ne lastItem.BILL_TO_TEXT || lastItem.ISDISPLAY_BILLINGPERIOD eq '1'}">
																<tr>
																	<td colspan="6">&nbsp;</td>
																</tr>
																<tr>
																	<td>&nbsp;</td>
																	<td colspan="5">
																		<bean:message key="screen.b_bil.billingPeriod" />
																		<bean:message key="screen.b_bil.colon1" />
																		<bean:message key="screen.b_bil.from" />
														                    ${lastItem.BILL_FROM_TEXT}
														                <bean:message key="screen.b_bil.toInfo" />
														                    ${lastItem.BILL_TO_TEXT}
																	</td>
																</tr>
															</c:when>
														</c:choose>
														<tr>
															<td colspan="6">&nbsp;</td>
														</tr>
													</c:if>
												</c:if>
												<c:set var="subDiscAmt" value="0"></c:set>
												<c:if test="${not empty item.ITEM_DISC_AMT && item.ITEM_DISC_AMT != 0 && item.DISPLAY_DISC ne '0'}">
													<c:set var="subDiscAmt" value="${item.ITEM_DISC_AMT}"></c:set>
												</c:if>
												<c:if test="${not empty item.ITEM_SUBTOTAL && item.ITEM_SUBTOTAL != 0 && item.IS_DISPLAY ne '0'}">
													<c:set var="subTotal" value="${subTotal + item.ITEM_SUBTOTAL}"></c:set> 
													<c:if test="${'0'eq item.ITEM_GST}">
									 			    	<c:set var="nonTaxableSubTotal" value="${nonTaxableSubTotal + item.ITEM_SUBTOTAL}"></c:set> 
									 			    </c:if>
									 			    <c:if test="${'0'ne item.ITEM_GST}">
									 			    	<c:set var="taxableSubTotal" value="${taxableSubTotal + item.ITEM_SUBTOTAL}"></c:set> 
									 			    </c:if>
												</c:if>
												
												<tr>
													<td valign="top" style="padding-left:10px;">
														<c:if test="${not empty item.ITEM_QTY && item.ITEM_QTY != 0 && not empty item.ITEM_UPRICE && item.ITEM_UPRICE != 0 && not empty item.ITEM_AMT && item.ITEM_AMT != 0 && item.IS_DISPLAY ne '0'}">
															<c:set var="index" value="${index + 1}"></c:set>
															${index}
														</c:if>
														&nbsp;
													</td>
													<td valign="top" style="width:470px;word-wrap: break-word;white-space : normal">
														<a href="#" onclick="openB_CPM_View('${pageContext.request.contextPath}/B_CPM/B_CPM_S05InitBL.do?customerPlan.idCustPlan=${item.ID_CUST_PLAN}&customerPlan.fromScreen=BIL&customerPlan.billType=${_b_bilForm.map.headerInfo.BILL_TYPE}')">
															<div><pre>${item.ITEM_DESC_TEXT}</pre></div>
														</a>
													</td>
													<td valign="top" style="text-align:right;">
														<c:if test="${not empty item.ITEM_QTY && item.ITEM_QTY != 0 && item.IS_DISPLAY ne '0'}">
															<fmt:formatNumber value="${item.ITEM_QTY}" pattern="#,##0"/>
														</c:if>&nbsp;
													</td>
													<td valign="top" style="text-align:right;">
														<c:if test="${not empty item.ITEM_UPRICE && item.ITEM_UPRICE != 0 && item.IS_DISPLAY ne '0'}">
														<%-- 
														    <c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
																CN
															</c:if>
														--%>
															<!-- Delete #156 Start-->
															<!--<fmt:formatNumber value="${item.ITEM_UPRICE}" pattern="#,##0.00"/>-->
															<!-- Delete #156 End-->
															<!-- Add #156 Start-->
															<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
																<c:if test="${_b_bilForm.map.billCnAmtNegative eq '1'}">
																	-<fmt:formatNumber value="${item.ITEM_UPRICE}" pattern="#,##0.00"/>
																</c:if>
																<c:if test="${_b_bilForm.map.billCnAmtNegative != '1'}">
																	<fmt:formatNumber value="${item.ITEM_UPRICE}" pattern="#,##0.00"/>
																</c:if>
															</c:if>
															<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE != 'CN'}">
																<fmt:formatNumber value="${item.ITEM_UPRICE}" pattern="#,##0.00"/>
															</c:if>
															<!-- Add #156 End--> 
														</c:if>&nbsp;
													</td>
													<td valign="top" style="text-align:right;">
														<c:choose>
													    <c:when test="${not empty item.ITEM_AMT && item.ITEM_AMT != 0 && item.IS_DISPLAY ne '0'}">
													    <%-- 
															<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
																DN
															</c:if>
														--%>
															<!-- Delete #156 Start-->
															<!--<fmt:formatNumber value="${item.ITEM_AMT}" pattern="#,##0.00"/>-->
															<!-- Delete #156 End-->
															<!-- Add #156 Start-->
															<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
																<c:if test="${_b_bilForm.map.billCnAmtNegative eq '1'}">
																	-<fmt:formatNumber value="${item.ITEM_AMT}" pattern="#,##0.00"/>
																</c:if>
																<c:if test="${_b_bilForm.map.billCnAmtNegative != '1'}">
																	<fmt:formatNumber value="${item.ITEM_AMT}" pattern="#,##0.00"/>
																</c:if>
															</c:if>
															<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE != 'CN'}">
																<fmt:formatNumber value="${item.ITEM_AMT}" pattern="#,##0.00"/>
															</c:if>
															<!-- Add #156 End--> 
														</c:when>
														<c:otherwise>
														&nbsp;
														</c:otherwise>
														</c:choose>
													</td>
													<td valign="top" style="padding-left:10px;">
														<c:if test="${_b_bilForm.map.gstCheck eq 'TAX_CODE'}">
															<c:choose>
															    <c:when test="${not empty item.TAX_CODE && item.IS_DISPLAY ne '0'}">
															        <c:if test="${'1' eq item.IS_DISPLAY_JOBNO && '1' eq _b_bilForm.map.headerInfo.jobModulesDisplayFlg}">
																        <div>&nbsp;</div>
																    </c:if>
																	<%-- ${item.TAX_CODE} --%>
																	<c:if test="${'0'eq item.ITEM_GST}">
													 			    	-
													 			    </c:if>
													 			    <c:if test="${'0'ne item.ITEM_GST}">
													 			    	<fmt:formatNumber value="${item.ITEM_GST}" pattern="#,##0.00"/>
													 			    </c:if>
																</c:when>
																<c:otherwise>
																	&nbsp;
																</c:otherwise>
															</c:choose>
														</c:if>
														<c:if test="${_b_bilForm.map.gstCheck eq 'TAX_RATE'}">
															<c:choose>
															    <c:when test="${not empty item.TAX_RATE && item.IS_DISPLAY ne '0'}">
															        <c:if test="${'1' eq item.IS_DISPLAY_JOBNO && '1' eq _b_bilForm.map.headerInfo.jobModulesDisplayFlg}">
																        <div>&nbsp;</div>
																    </c:if>
																	${item.TAX_RATE}%
																</c:when>
																<c:otherwise>
																	&nbsp;
																</c:otherwise>
															</c:choose>
														</c:if>
													</td>
												</tr>
												<c:if test="${'1' eq item.IS_DISPLAY_MIN_SVC}">
													<tr>
														<td colspan="6">&nbsp;</td>
													</tr>
													<tr>
														<td>&nbsp;</td>
														<td colspan="5">
															<bean:message key="screen.b_bil.contractPeriod"/>
															<bean:message key="screen.b_bil.contractPeriodFrom"/>
															${item.MIN_SVC_FROM_TEXT}
															<bean:message key="screen.b_bil.contractPeriodTo"/>
															${item.MIN_SVC_TO_TEXT}
														</td>
													</tr>
													<tr>
														<td colspan="6">&nbsp;</td>
													</tr>
												</c:if>
											</c:when>
											<c:otherwise>
												<c:if test="${not empty item.ITEM_SUBTOTAL && item.ITEM_SUBTOTAL != 0 && item.IS_DISPLAY ne '0'}">
													<c:set var="subTotal" value="${subTotal + item.ITEM_SUBTOTAL}"></c:set>
													<c:if test="${'0'eq item.ITEM_GST}">
									 			    	<c:set var="nonTaxableSubTotal" value="${nonTaxableSubTotal + item.ITEM_SUBTOTAL}"></c:set> 
									 			    </c:if>
									 			    <c:if test="${'0'ne item.ITEM_GST}">
									 			    	<c:set var="taxableSubTotal" value="${taxableSubTotal + item.ITEM_SUBTOTAL}"></c:set> 
									 			    </c:if> 
												</c:if>
												<tr>
												   <td valign="top" style="padding-left:10px;">
												   		<c:if test="${item.IS_DISPLAY ne '0'}">
												   		    <c:if test="${'1' eq item.IS_DISPLAY_JOBNO && '1' eq _b_bilForm.map.headerInfo.jobModulesDisplayFlg}">
														        <div>&nbsp;</div>
														    </c:if>
															<c:set var="index" value="${index + 1}"></c:set>${index}
														</c:if>&nbsp;
													</td>
													<td valign="top" style="width:470px;word-wrap: break-word;white-space : normal">
														<c:if test="${'1' eq item.IS_DISPLAY_JOBNO && '1' eq _b_bilForm.map.headerInfo.jobModulesDisplayFlg}">
															<pre style="color:#CD853F;"><bean:message key="screen.b_bil.jobNoPoint"/>&nbsp;${item.JOB_NO}</pre>
														</c:if>
														<div><pre>${item.ITEM_DESC_TEXT}</pre></div>
													</td>
													<td valign="top" style="text-align:right;">
														<c:if test="${not empty item.ITEM_QTY && item.ITEM_QTY != 0 && item.IS_DISPLAY ne '0'}">
														    <c:if test="${'1' eq item.IS_DISPLAY_JOBNO && '1' eq _b_bilForm.map.headerInfo.jobModulesDisplayFlg}">
														        <div>&nbsp;</div>
														    </c:if>
														    <fmt:formatNumber value="${item.ITEM_QTY}" pattern="#,##0"/>
														</c:if>&nbsp;
													</td>
													<td valign="top" style="text-align:right;">
														<c:if test="${not empty item.ITEM_UPRICE && item.ITEM_UPRICE != 0 && item.IS_DISPLAY ne '0'}">
															<c:if test="${'1' eq item.IS_DISPLAY_JOBNO && '1' eq _b_bilForm.map.headerInfo.jobModulesDisplayFlg}">
														        <div>&nbsp;</div>
														    </c:if>
														    <%-- 
															<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
																-
															</c:if>
															--%>
															<!-- Delete #156 Start-->
															<!--<fmt:formatNumber value="${item.ITEM_UPRICE}" pattern="#,##0.00"/>-->
															<!-- Delete #156 End-->
															<!-- Add #156 Start-->
															<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
																<c:if test="${_b_bilForm.map.billCnAmtNegative eq '1'}">
																	-<fmt:formatNumber value="${item.ITEM_UPRICE}" pattern="#,##0.00"/>
																</c:if>
																<c:if test="${_b_bilForm.map.billCnAmtNegative != '1'}">
																	<fmt:formatNumber value="${item.ITEM_UPRICE}" pattern="#,##0.00"/>
																</c:if>
															</c:if>
															<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE != 'CN'}">
																<fmt:formatNumber value="${item.ITEM_UPRICE}" pattern="#,##0.00"/>
															</c:if>
															<!-- Add #156 End--> 
														</c:if>&nbsp;
													</td>
													<td valign="top" style="text-align:right;">
														<c:choose>
													    <c:when test="${not empty item.ITEM_AMT && item.ITEM_AMT != 0 && item.IS_DISPLAY ne '0'}">
															<c:if test="${'1' eq item.IS_DISPLAY_JOBNO && '1' eq _b_bilForm.map.headerInfo.jobModulesDisplayFlg}">
														        <div>&nbsp;</div>
														    </c:if>
														    <%-- 
															<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
																-
															</c:if>
															--%>
															<!-- Delete #156 Start-->
															<!--<fmt:formatNumber value="${item.ITEM_AMT}" pattern="#,##0.00"/>-->
															<!-- Delete #156 End-->
															<!-- Add #156 Start-->
															<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
																<c:if test="${_b_bilForm.map.billCnAmtNegative eq '1'}">
																	-<fmt:formatNumber value="${item.ITEM_AMT}" pattern="#,##0.00"/>
																</c:if>
																<c:if test="${_b_bilForm.map.billCnAmtNegative != '1'}">
																	<fmt:formatNumber value="${item.ITEM_AMT}" pattern="#,##0.00"/>
																</c:if>
															</c:if>
															<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE != 'CN'}">
																<fmt:formatNumber value="${item.ITEM_AMT}" pattern="#,##0.00"/>
															</c:if>
															<!-- Add #156 End--> 
														</c:when>
														<c:otherwise>
															&nbsp;
														</c:otherwise>
														</c:choose>
													</td>
													<td valign="top" style="padding-left:10px;">
														<c:if test="${_b_bilForm.map.gstCheck eq 'TAX_CODE'}">
															<c:choose>
															    <c:when test="${not empty item.TAX_CODE && item.IS_DISPLAY ne '0'}">
															        <c:if test="${'1' eq item.IS_DISPLAY_JOBNO && '1' eq _b_bilForm.map.headerInfo.jobModulesDisplayFlg}">
																        <div>&nbsp;</div>
																    </c:if>
																	<%-- ${item.TAX_CODE} --%>
																	<c:if test="${'0'eq item.ITEM_GST}">
													 			    	-
													 			    </c:if>
													 			    <c:if test="${'0'ne item.ITEM_GST}">
													 			    	<fmt:formatNumber value="${item.ITEM_GST}" pattern="#,##0.00"/>
													 			    </c:if>
																	
																</c:when>
																<c:otherwise>
																	&nbsp;
																</c:otherwise>
															</c:choose>
														</c:if>
														<c:if test="${_b_bilForm.map.gstCheck eq 'TAX_RATE'}">
															<c:choose>
															    <c:when test="${not empty item.TAX_RATE && item.IS_DISPLAY ne '0'}">
															        <c:if test="${'1' eq item.IS_DISPLAY_JOBNO && '1' eq _b_bilForm.map.headerInfo.jobModulesDisplayFlg}">
																        <div>&nbsp;</div>
																    </c:if>
																	${item.TAX_RATE}%
																</c:when>
																<c:otherwise>
																	&nbsp;
																</c:otherwise>
															</c:choose>
														</c:if>
													</td>
												</tr>
												<tr>
													<c:if test="${not empty item.ITEM_DISC_AMT && item.ITEM_DISC_AMT != 0 && item.DISPLAY_DISC ne '0'}">
														<td valign="top">&nbsp;</td>
											 	        <td colspan="3">
											 	           <b><i><bean:message key="screen.b_bil.discount"/></i></b>
											 	        </td>
											 	        <td style="text-align:right;" valign="top">
											 	          <fmt:formatNumber value="${item.ITEM_DISC_AMT}" pattern="#,##0.00"/>
											 	        </td>
											 	        <td valign="top" style="padding-left:10px;">
											 	        <c:if test="${_b_bilForm.map.gstCheck eq 'TAX_CODE'}">
															<c:choose>
															    <c:when test="${not empty item.TAX_CODE}">
															  		<%-- ${item.TAX_CODE} --%>
															  		-
																</c:when>
																<c:otherwise>
																	&nbsp;
																</c:otherwise>
															</c:choose>
														</c:if>
														<c:if test="${_b_bilForm.map.gstCheck eq 'TAX_RATE'}">
															<c:choose>
															    <c:when test="${not empty item.TAX_RATE}">
															  		${item.TAX_RATE}%
																</c:when>
																<c:otherwise>
																	&nbsp;
																</c:otherwise>
															</c:choose>
														</c:if>
														</td>
													</c:if>
				 	    						</tr>
											</c:otherwise>
										</c:choose>	
									</c:otherwise>
								</c:choose>	
								<c:set var="lastItem" value="${item}"></c:set>
							</c:forEach>
							<c:if test="${not empty lastItem}">
								<c:choose>
									<c:when test="${lastItem.ITEM_CAT eq '1'}">
										<c:if test="${subDiscAmt != 0}">
										    <tr>
												<td colspan="6">&nbsp;</td>
											</tr>
											<tr>
												<td valign="top">&nbsp;</td>
									 	        <td colspan="3">
									 	           <b><i><bean:message key="screen.b_bil.discount"/></i></b>
									 	        </td>
									 	        <td style="text-align:right;" valign="top">
									 	          <fmt:formatNumber value="${subDiscAmt}" pattern="#,##0.00"/>
									 	        </td>
									 	        <td valign="top" style="padding-left:10px;">
									 	        <c:if test="${_b_bilForm.map.gstCheck eq 'TAX_CODE'}">
													<c:choose>
													    <c:when test="${not empty lastItem.TAX_CODE}">
													  		${lastItem.TAX_CODE}
														</c:when>
														<c:otherwise>
															&nbsp;
														</c:otherwise>
													</c:choose>
												</c:if>
												<c:if test="${_b_bilForm.map.gstCheck eq 'TAX_RATE'}">
													<c:choose>
													    <c:when test="${not empty lastItem.TAX_RATE}">
													  		${lastItem.TAX_RATE}%
														</c:when>
														<c:otherwise>
															&nbsp;
														</c:otherwise>
													</c:choose>
												</c:if>
												</td>
								 	        <tr>
										</c:if>
										<c:choose>
											<c:when test="${lastItem.BILL_FROM_TEXT ne lastItem.BILL_TO_TEXT || lastItem.ISDISPLAY_BILLINGPERIOD eq '1'}">
												<tr>
													<td colspan="6">&nbsp;</td>
												</tr>
												<tr>
													<td>&nbsp;</td>
													<td colspan="4">
														<bean:message key="screen.b_bil.billingPeriod"/>
												         <bean:message key="screen.b_bil.colon1"/>
												         <bean:message key="screen.b_bil.from"/>
												             ${lastItem.BILL_FROM_TEXT}
												         <bean:message key="screen.b_bil.toInfo"/>
												             ${lastItem.BILL_TO_TEXT}
													</td>
												</tr>
											</c:when>
										</c:choose>
									</c:when>
								</c:choose>
							</c:if>
						</table>
					</td>
				</tr>
			</table>
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr><td style="height:10px"></td></tr>
			</table>
			<table width="100%" cellpadding="0" cellspacing="0">
				<col width="80%"/>
				<col width="10%"/>
				<col width="10%"/>
				<!-- End Detail content -->
				<c:if test="${'2'ne _b_bilForm.map.headerInfo.GSTAPPLYFLAG}">
				<tr style="font-weight: bold;">
			   		<td align="right" style="background-color:rgb(200,210,230)">
						<bean:message key="screen.b_bil.subTotal"/>
						${_b_bilForm.map.headerInfo.BILL_CURRENCY}
					</td>
					<td align="right" style="background-color:rgb(200,210,230)">
					 <%-- 
						<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
							-
						</c:if>
					 --%>
					 	<!-- Delete #156 Start-->
						<!--<fmt:formatNumber value="${subTotal}" pattern="#,##0.00"/>-->
						<!-- Delete #156 End--> 
						<!-- Add #156 Start-->
						<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
							<c:if test="${_b_bilForm.map.billCnAmtNegative eq '1'}">
								-<fmt:formatNumber value="${subTotal}" pattern="#,##0.00"/>
							</c:if>
							<c:if test="${_b_bilForm.map.billCnAmtNegative != '1'}">
								<fmt:formatNumber value="${subTotal}" pattern="#,##0.00"/>
							</c:if>
						</c:if>
						<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE != 'CN'}">
							<fmt:formatNumber value="${subTotal}" pattern="#,##0.00"/>
						</c:if>
						<!-- Add #156 End--> 
					</td>
					<td style="background-color:rgb(200,210,230)">
						&nbsp;
					</td>
				</tr>
				</c:if>
				<c:if test="${'2' eq _b_bilForm.map.headerInfo.GSTAPPLYFLAG}">
				<tr style="font-weight: bold;">
			   		<td align="right" style="background-color:rgb(200,210,230)">
						Subtotal Taxable Services (${_b_bilForm.map.headerInfo.BILL_CURRENCY})
					</td>
					<td align="right" style="background-color:rgb(200,210,230)">
					 <%-- 
						<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
							-
						</c:if>
					 --%>
					 	<!-- Delete #156 Start-->
						<!--<fmt:formatNumber value="${subTotal}" pattern="#,##0.00"/>-->
						<!-- Delete #156 End--> 
						<!-- Add #156 Start-->
						<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
							<c:if test="${_b_bilForm.map.billCnAmtNegative eq '1'}">
								-<fmt:formatNumber value="${taxableSubTotal}" pattern="#,##0.00"/>
							</c:if>
							<c:if test="${_b_bilForm.map.billCnAmtNegative != '1'}">
								<fmt:formatNumber value="${taxableSubTotal}" pattern="#,##0.00"/>
							</c:if>
						</c:if>
						<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE != 'CN'}">
							<fmt:formatNumber value="${taxableSubTotal}" pattern="#,##0.00"/>
						</c:if>
						<!-- Add #156 End--> 
					</td>
					<td style="background-color:rgb(200,210,230)">
						&nbsp;
					</td>
				</tr>
				<tr style="font-weight: bold;">
			   		<td align="right" style="background-color:rgb(200,210,230)">
						Subtotal Non-Taxable Services (${_b_bilForm.map.headerInfo.BILL_CURRENCY})
					</td>
					<td align="right" style="background-color:rgb(200,210,230)">
					 <%-- 
						<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
							-
						</c:if>
					 --%>
					 	<!-- Delete #156 Start-->
						<!--<fmt:formatNumber value="${subTotal}" pattern="#,##0.00"/>-->
						<!-- Delete #156 End--> 
						<!-- Add #156 Start-->
						<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
							<c:if test="${_b_bilForm.map.billCnAmtNegative eq '1'}">
								-<fmt:formatNumber value="${nonTaxableSubTotal}" pattern="#,##0.00"/>
							</c:if>
							<c:if test="${_b_bilForm.map.billCnAmtNegative != '1'}">
								<fmt:formatNumber value="${nonTaxableSubTotal}" pattern="#,##0.00"/>
							</c:if>
						</c:if>
						<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE != 'CN'}">
							<fmt:formatNumber value="${nonTaxableSubTotal}" pattern="#,##0.00"/>
						</c:if>
						<!-- Add #156 End--> 
					</td>
					<td style="background-color:rgb(200,210,230)">
						&nbsp;
					</td>
				</tr>
				</c:if>
				
				<tr style="font-weight: bold">
			   		<td align="right" style="background-color:rgb(200,210,230)">
			   			<c:if test="${'2'ne _b_bilForm.map.headerInfo.GSTAPPLYFLAG}">
						<bean:message key="screen.b_bil.gstAmount"/>	
						${_b_bilForm.map.headerInfo.BILL_CURRENCY}
						</c:if>
						
						<c:if test="${'2'eq _b_bilForm.map.headerInfo.GSTAPPLYFLAG}">
						Services Tax ${taxRate}% (${_b_bilForm.map.headerInfo.BILL_CURRENCY})
						</c:if>
					</td>
					<td align="right" style="background-color:rgb(200,210,230)">
					<%-- 
						<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
							-
						</c:if>
					--%>
						<!-- Delete #156 Start-->
						<!--<fmt:formatNumber value="${_b_bilForm.map.headerInfo.GST_AMOUNT}" pattern="#,##0.00"/>-->
						<!-- Delete #156 End-->
						<!-- Add #156 Start-->
						<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
							<c:if test="${_b_bilForm.map.billCnAmtNegative eq '1'}">
								-<fmt:formatNumber value="${_b_bilForm.map.headerInfo.GST_AMOUNT}" pattern="#,##0.00"/>
							</c:if>
							<c:if test="${_b_bilForm.map.billCnAmtNegative != '1'}">
								<fmt:formatNumber value="${_b_bilForm.map.headerInfo.GST_AMOUNT}" pattern="#,##0.00"/>
							</c:if>
						</c:if>
						<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE != 'CN'}">
							<fmt:formatNumber value="${_b_bilForm.map.headerInfo.GST_AMOUNT}" pattern="#,##0.00"/>
						</c:if>
						<!-- Add #156 End--> 
					</td>
					<td style="background-color:rgb(200,210,230)">
						&nbsp;
					</td>
				</tr>
			</table>
			<table width="100%" cellpadding="0" cellspacing="0" style="font-weight:bold">
				<tr align="center">
					<td width="40%" style="background-color:rgb(136,167,216)">
					</td>
					<td width="40%" style="background-color:rgb(136,167,216)"  align="left">
						<bean:message key="screen.b_bil.grandTotalU"/>
						&nbsp;${_b_bilForm.map.headerInfo.BILL_CURRENCY}
						&nbsp;
					</td>
					<td width="10%" align="right" style="background-color:rgb(136,167,216);font-weight:bold">
					<%-- 
						<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
							-
						</c:if>
					--%>
						<!-- Delete #156 Start-->
						<!-- <fmt:formatNumber value="${_b_bilForm.map.headerInfo.BILL_AMOUNT}" pattern="#,##0.00"/>-->
						<!-- Delete #156 End-->
						<!-- Add #156 Start-->
						<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
							<c:if test="${_b_bilForm.map.billCnAmtNegative eq '1'}">
								-<fmt:formatNumber value="${_b_bilForm.map.headerInfo.BILL_AMOUNT}" pattern="#,##0.00"/>
							</c:if>
							<c:if test="${_b_bilForm.map.billCnAmtNegative != '1'}">
								<fmt:formatNumber value="${_b_bilForm.map.headerInfo.BILL_AMOUNT}" pattern="#,##0.00"/>
							</c:if>
						</c:if>
						<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE != 'CN'}">
							<fmt:formatNumber value="${_b_bilForm.map.headerInfo.BILL_AMOUNT}" pattern="#,##0.00"/>
						</c:if>
						<!-- Add #156 End--> 
						<input type="hidden" id="hidBillAmount" value='<fmt:formatNumber value="${_b_bilForm.map.headerInfo.BILL_AMOUNT}" pattern="#,##0.00"/>'>
					</td>
					<td width="10%" style="background-color:rgb(136,167,216)">
						&nbsp;
					</td>
				</tr>
			</table>
			<c:if test="${_b_bilForm.map.headerInfo.BILL_CURRENCY ne _b_bilForm.map.headerInfo.EXPORT_CUR and '-' ne fn:trim(_b_bilForm.map.headerInfo.EXPORT_CUR)}">
				<table width="100%" cellpadding="0" cellspacing="0" style="font-weight:bold">
					<tr align="center" style="background-color:rgb(136,167,216);font-size: 17px;font-weight: bold;">
						<td width="25%" style="background-color:rgb(136,167,216)" style="font-weight:normal;text-align: left;">
							<c:if test="${_b_bilForm.map.headerInfo.IS_DISPLAY_EXP_AMT eq '1'}">
							    <input type="checkbox" disabled="disabled" checked="checked">
							</c:if>
							<c:if test="${_b_bilForm.map.headerInfo.IS_DISPLAY_EXP_AMT eq '0'}">
							    <input type="checkbox" disabled="disabled">
							</c:if>
							<bean:message key="screen.b_bil.displayAt"/>
							<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
								<bean:message key="screen.b_bil.creditNote"/>
							</c:if>
							<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'IN'}">
								<bean:message key="screen.b_bil.invoiceNote"/>
							</c:if>
							<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'DN'}">
								<bean:message key="screen.b_bil.debitNote"/>
							</c:if>
						</td>
						<td width="55%" style="background-color:rgb(136,167,216);" align="left">
						    <c:choose>
						        <c:when test="${_b_bilForm.map.headerInfo.currencyStd eq 1}">
						            <bean:message key="screen.b_bil.grandTotalU"/>
							        &nbsp;${_b_bilForm.map.headerInfo.EXPORT_CUR}
							        &nbsp;(
							        1&nbsp;${_b_bilForm.map.headerInfo.BILL_CURRENCY}
							        &nbsp;=&nbsp;
							        <c:if test="${ not empty _b_bilForm.map.headerInfo.FIXED_FOREX}">
								        <bean:message key="screen.b_bil.fixedForex"/>
							        </c:if>
							        <fmt:formatNumber value="${_b_bilForm.map.headerInfo.CUR_RATE}" pattern="#,##0.00000000"/>
						            &nbsp;${_b_bilForm.map.headerInfo.EXPORT_CUR}
							        )
							    </c:when>
							    <c:when test="${_b_bilForm.map.headerInfo.currencyStd eq 0}">
							        <c:if test="${_b_bilForm.map.headerInfo.BILL_CURRENCY eq _b_bilForm.map.headerInfo.defCurrency}">
										<bean:message key="screen.b_bil.grandTotalU"/>
										&nbsp;${_b_bilForm.map.headerInfo.EXPORT_CUR}
										&nbsp;(
										1&nbsp;${_b_bilForm.map.headerInfo.EXPORT_CUR}
										&nbsp;=&nbsp;
										<c:if test="${ not empty _b_bilForm.map.headerInfo.FIXED_FOREX}">
								            <bean:message key="screen.b_bil.fixedForex"/>
							            </c:if>
										<fmt:formatNumber value="${_b_bilForm.map.headerInfo.CUR_RATE}" pattern="#,##0.00000000"/>
										&nbsp;${_b_bilForm.map.headerInfo.BILL_CURRENCY}
										)
									</c:if>
									<c:if test="${_b_bilForm.map.headerInfo.BILL_CURRENCY ne _b_bilForm.map.headerInfo.defCurrency}">
										<bean:message key="screen.b_bil.grandTotalU"/>
										&nbsp;${_b_bilForm.map.headerInfo.EXPORT_CUR}
										&nbsp;(
										1&nbsp;${_b_bilForm.map.headerInfo.BILL_CURRENCY}
										&nbsp;=&nbsp;
										<c:if test="${not empty _b_bilForm.map.headerInfo.FIXED_FOREX}">
											<bean:message key="screen.b_bil.fixedForex"/>
										</c:if>
										<fmt:formatNumber value="${_b_bilForm.map.headerInfo.CUR_RATE}" pattern="#,##0.00000000"/>
										&nbsp;${_b_bilForm.map.headerInfo.EXPORT_CUR}
										)
									</c:if>
							    </c:when>
						        <c:otherwise></c:otherwise>
						    </c:choose>
						</td>
						<td width="10%" align="right" style="background-color:rgb(136,167,216);">
						<%--
							<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
								-
							</c:if>
						 --%>
							<fmt:formatNumber value="${_b_bilForm.map.headerInfo.EXPORT_AMOUNT}" pattern="#,##0.00"/>
						</td>
						<td width="10%" style="background-color:rgb(136,167,216)">
							&nbsp;
						</td>
					</tr>
				</table>
			</c:if>
			<div style="font-weight:bold;margin-top:3px;margin-bottom:20px;"> 
				<c:choose>
				    <c:when test="${_b_bilForm.map.headerInfo.IS_DISPLAY_EXP_AMT eq '1'}">
				        <t:defineCodeList id="LIST_CURRENCY" />
				        (
				        <t:writeCodeValue codeList="LIST_CURRENCY" key="${_b_bilForm.map.headerInfo.EXPORT_CUR}"></t:writeCodeValue>
				        <bean:message key="screen.b_bil.amount"/>:
				        ${bs:numberToWord(_b_bilForm.map.headerInfo.EXPORT_AMOUNT)}
				    </c:when>
				    <c:otherwise>
				        <t:defineCodeList id="LIST_CURRENCY" />
				        (
				        <t:writeCodeValue codeList="LIST_CURRENCY" key="${_b_bilForm.map.headerInfo.BILL_CURRENCY}"></t:writeCodeValue>
				        <bean:message key="screen.b_bil.amount"/>:
				        ${bs:numberToWord(_b_bilForm.map.headerInfo.BILL_AMOUNT)}
				    </c:otherwise>
				</c:choose>
				<bean:message key="screen.b_bil.only"/>
			</div>
			<%--
			<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'IN' 
					or _b_bilForm.map.headerInfo.BILL_TYPE eq 'DN'}">
			--%>
				<div class="footerInfo">
					<% int footerCounter = 0; %>
					<logic:iterate id="footer" name="_b_bilForm" property="footerInfo">
						<% 
							footerCounter++;
							if(footerCounter != 15){
						%>
						<logic:notEmpty name="footer" property="SET_VALUE">
								<pre><bean:write name="footer" property="SET_VALUE"/>&nbsp;</pre>
						</logic:notEmpty>
						<%	} %>
					</logic:iterate>
				</div>
			<%--
			</c:if>
			--%>
			<c:if test="${empty _b_bilForm.map.inputHeaderInfo.billType}">
			    <%--
				<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'IN' or _b_bilForm.map.headerInfo.BILL_TYPE eq 'DN'}">
				--%>
					<div class="footerInfo">
						<table width="100%" cellpadding="0" cellspacing="0" style="font-weight:bold">	
							<tr>
								<td>
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td><pre><bean:message key="screen.b_bil.bank"/> <bean:message key="screen.b_bil.colon"/>&nbsp;</pre></td>
										</tr>
										<tr> 
											<td>
												<pre>&nbsp;</pre>
											</td>
										</tr>
										<tr> 
											<td><pre><bean:message key="screen.b_bil.accountName"/> <bean:message key="screen.b_bil.colon"/>&nbsp;</pre></td>
										</tr>
										<tr> 
											<td><pre><bean:message key="screen.b_bil.accountNo"/> <bean:message key="screen.b_bil.colon"/>&nbsp;</pre></td>
										</tr>
										<bean:define id="rownum" value="${LOAD_OBJECT.rowsnum}"/>
										<%for (int i=0;i<Integer.parseInt(rownum);i++){ %>
										<tr><td><pre>&nbsp;</pre></td></tr>
										<%} %> 
										<tr> 
											<td><pre><bean:message key="screen.b_bil.swiftCode"/> <bean:message key="screen.b_bil.colon"/>&nbsp;</pre></td>
										</tr>
									</table>
								</td>
								<%int maxnum=0; %>
								<logic:iterate id="bankFooter" name="_b_bilForm" property="bankFooterInfo" indexId="num">				
								<td>
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td><pre><bean:write name="bankFooter" property="BANK_NAME"/>&nbsp;</pre></td>
										</tr>
										<tr> 
											<td><pre><bean:write name="bankFooter"  property="BRANCH_NAME"/>&nbsp;</pre></td>
										</tr>
										<tr> 
											<td><pre><bean:write name="bankFooter" property="COM_ACCT_NAME"/>&nbsp;</pre></td>
										</tr>
										<tr> 
											<td><pre><bean:write name="bankFooter" property="COM_ACCT_NO"/>&nbsp;<%--  (<bean:write name="bankFooter" property="COM_CUR"/>&nbsp;) --%></pre></td>
										</tr>
										<%List<String> accno=new ArrayList<String>(); %>
										<%maxnum=num+1;%>
										<bean:define id="rownum" value="${LOAD_OBJECT.rowsnum}"/>
										<c:if test="${num eq 0}">
											<bean:define id="accno1" property="accno1" name="LOAD_OBJECT"/>
											<%accno=(List<String>)accno1;%>
										</c:if>
										<c:if test="${num eq 1}">
											<bean:define id="accno2" property="accno2" name="LOAD_OBJECT"/>
											<%accno=(List<String>)accno2;%>
										</c:if>
										<c:if test="${num eq 2}">
											<bean:define id="accno3" property="accno3" name="LOAD_OBJECT"/>
											<%accno=(List<String>)accno3;%>
										</c:if>
											<%
												List<String> acc=(List<String>)accno;
												   if(acc.size()>0){
												       for(int j=0;j<acc.size();j++){%> 
												      <tr><td><pre><%=acc.get(j)%></pre></td></tr>
												     <% }
												} if(acc.size()==0){
												    for(int j=0;j<Integer.parseInt(rownum);j++){ %>
												    <tr><td><pre>&nbsp;</pre></td></tr>
												<%} } if(acc.size()>0&&(acc.size()<Integer.parseInt(rownum))){
												    for(int k=0;k<Integer.parseInt(rownum)-acc.size();k++){
											%>
											<tr><td><pre>&nbsp;</pre></td></tr>
											<%}} %>
										<tr> 
											<td><pre><bean:write name="bankFooter" property="COM_SWIFT"/>&nbsp;</pre></td>
										</tr>
									</table>
								</td>
								</logic:iterate>
								<%if(maxnum==1){%>
									<td width="40%">&nbsp;</td>
							    	<td width="40%">&nbsp;</td>
								<%} %> 
							</tr>
						</table>
					</div>
				<%--
				</c:if>
				--%>
			</c:if>
			<c:if test="${not empty _b_bilForm.map.inputHeaderInfo.billType}">
			    <%-- 
				<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'IN' or _b_bilForm.map.headerInfo.BILL_TYPE eq 'DN'}">
		        --%>
					<div class="footerInfo">
						<table width="100%" cellpadding="0" cellspacing="0" style="font-weight:bold">	
							<tr>
								<td>
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td><pre><bean:message key="screen.b_bil.bank"/> <bean:message key="screen.b_bil.colon"/>&nbsp;</pre></td>
										</tr>
										<tr> 
											<td>
												<pre>&nbsp;</pre>
											</td>
										</tr>
										<tr> 
											<td><pre><bean:message key="screen.b_bil.accountName"/> <bean:message key="screen.b_bil.colon"/>&nbsp;</pre></td>
										</tr>
										<tr> 
											<td><pre><bean:message key="screen.b_bil.accountNo"/> <bean:message key="screen.b_bil.colon"/>&nbsp;</pre></td>
										</tr>
										<tr> 
											<td><pre><bean:message key="screen.b_bil.swiftCode"/> <bean:message key="screen.b_bil.colon"/>&nbsp;</pre></td>
										</tr>
									</table>
								</td>
								<%int maxnum=0; %>
								<logic:iterate id="bankFooter" name="_b_bilForm" property="bankFooterInfo" indexId="num">				
								<td>
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td><pre><bean:write name="bankFooter" property="BANK_NAME"/>&nbsp;</pre></td>
										</tr>
										<tr> 
											<td><pre><bean:write name="bankFooter"  property="BRANCH_NAME"/>&nbsp;</pre></td>
										</tr>
										<tr> 
											<td><pre><bean:write name="bankFooter" property="COM_ACCT_NAME"/>&nbsp;</pre></td>
										</tr>
										<tr> 
											<td><pre><bean:write name="bankFooter" property="COM_ACCT_NO"/>&nbsp;<%--  (<bean:write name="bankFooter" property="COM_CUR"/>&nbsp;) --%></pre></td>
										</tr>
										<%List<String> accno=new ArrayList<String>(); %>
										<%maxnum=num+1;%>
										<bean:define id="rownum" value="${LOAD_OBJECT.rowsnum}"/>
										<c:if test="${num eq 0}">
											<bean:define id="accno1" property="accno1" name="LOAD_OBJECT"/>
											<%accno=(List<String>)accno1;%>
										</c:if>
										<c:if test="${num eq 1}">
											<bean:define id="accno2" property="accno2" name="LOAD_OBJECT"/>
											<%accno=(List<String>)accno2;%>
										</c:if>
										<c:if test="${num eq 2}">
											<bean:define id="accno3" property="accno3" name="LOAD_OBJECT"/>
											<%accno=(List<String>)accno3;%>
										</c:if>
											<%
												List<String> acc=(List<String>)accno;
												   if(acc.size()>0){
												       for(int j=0;j<acc.size();j++){%> 
												      <tr><td><pre><%=acc.get(j)%></pre></td></tr>
												     <% }
												} if(acc.size()==0){
												    for(int j=0;j<Integer.parseInt(rownum);j++){ %>
												    <tr><td><pre>&nbsp;</pre></td></tr>
												<%} } if(acc.size()>0&&(acc.size()<Integer.parseInt(rownum))){
												    for(int k=0;k<Integer.parseInt(rownum)-acc.size();k++){
											%>
											<tr><td><pre>&nbsp;</pre></td></tr>
											<%}} %>
										<tr> 
											<td><pre><bean:write name="bankFooter" property="COM_SWIFT"/>&nbsp;</pre></td>
										</tr>
									</table>
								</td>
								</logic:iterate>
								<%if(maxnum==1){%>
									<td width="40%">&nbsp;</td>
							    	<td width="40%">&nbsp;</td>
								<%} %>
							</tr>
						</table>
					</div>
			    <%-- 
				</c:if>
			    --%>
			</c:if>
			<hr class="lineBottom"/>
			<br/>
			<c:if test="${_b_bilForm.map.accessType eq '2' and _b_bilForm.map.headerInfo.IS_CLOSED eq '0'
							and _b_bilForm.map.headerInfo.IS_SETTLED eq '0' and _b_bilForm.map.headerInfo.PAID_AMOUNT eq 0
							and _b_bilForm.map.headerInfo.IS_DELETED eq '0'
							and _b_bilForm.map.headerInfo.fromPageFlag eq 'BIL'}">
	            <ts:submit value="Edit" styleClass="button" property="forward_edit"/>
	            <html:submit value="Delete" styleClass="button" property="forward_delete"/>
            </c:if>
            <c:if test="${_b_bilForm.map.accessType eq '2' and _b_bilForm.map.headerInfo.IS_DELETED eq '1' 
                            and _b_bilForm.map.headerInfo.fromPageFlag eq 'BIL'}">
                <html:submit value="Re-Activate" property="forward_reActivate"/>
            </c:if>
            <c:if test="${_b_bilForm.map.headerInfo.PRINT_FLAG eq '1' and _b_bilForm.map.headerInfo.IS_DELETED eq '0' and _b_bilForm.map.headerInfo.fromPageFlag ne 'EML'}">
           	    <html:submit property="forward_print" styleClass="button" onclick="return printConfirm();"><bean:message key="screen.b_bil.print"/></html:submit>
            </c:if>
            <c:if test="${_b_bilForm.map.headerInfo.fromPageFlag eq 'BIL'}">
            	<bs:buttonLink action="/RP_B_BIL_S01_02BL" value="Exit"/>
            </c:if>
            <c:if test="${_b_bilForm.map.headerInfo.fromPageFlag ne 'BIL' and _b_bilForm.map.headerInfo.fromPageFlag ne 'EML'}">
            	<html:submit property="forward_exit" styleClass="button" value="Exit"></html:submit>
            </c:if>
            <c:if test="${_b_bilForm.map.headerInfo.fromPageFlag eq 'EML'}">
            	<input type="button" class="button" value="Exit" name="forward_exit" onclick="clickExit()" />
            </c:if>
            <html:hidden property="idRef" value="${_b_bilForm.map.headerInfo.ID_REF}"/>
            <html:hidden property="mode" value="${_b_bilForm.map.mode}"/>
            <html:hidden styleId="autSign" property="autSign" />
            <input type="hidden" id="payer" value="${_b_bilForm.map.headerInfo.ID_CUST}">
            <input type="hidden" id="idBillAccount" value="${_b_bilForm.map.headerInfo.BILL_ACC}">
            <input type="hidden" id="hidsysBaseVal" value="${_b_bilForm.map.sysBaseVal}">
            <div class="error">
				<c:if test="${not empty _b_bilForm.map.lastMsg}">
					${_b_bilForm.map.lastMsg}
				</c:if>
			</div>
            <div class="message">
				<ts:messages id="message" message="true">
					<bean:write name="message"/>
				</ts:messages>
			</div>
			<div class="error">
				<ts:errors/>
			</div>
			</div>
			<input type="hidden" id="ERR4SC017_info" value='<bean:message key="info.ERR4SC017" arg0="You are about to reset the billing document to un-deleted status / Normal status"/>' />
			<input type="hidden" id="ERR4SC014_info" value='<bean:message key="info.ERR4SC014"/>'/>
			<input type="hidden" id="ERR4SC19_info" value='<bean:message key="info.ERR4SC19"/>'/>
			<input type="hidden" id="hiddenMsgDeleteConfirmation" value="<bean:message key="info.ERR4SC002"/>"/>
		</ts:form>
	</ts:body>
</html:html>