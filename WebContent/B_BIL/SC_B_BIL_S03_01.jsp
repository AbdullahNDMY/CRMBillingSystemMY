<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="/WEB-INF/bs" prefix="bs" %>

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
		var isDelBtn = false;
		// check which button was pressed
		document.onclick = function(whichOne){ 
			whichOne = whichOne ? whichOne : window.event;
			thisButton = whichOne.target ? whichOne.target : whichOne.srcElement;
			if (( thisButton.name ) && ( thisButton.name == 'forward_delete' )) 
				isDelBtn = true;
			else
				isDelBtn = false;
		}
		function checkBeforeSubmit(){
			if(isDelBtn){
				var MsgBox = new messageBox();
				if (MsgBox.POPCFM("Do you want to delete?") == MsgBox.YES_CONFIRM)
				//if(confirm("Do you want to delete?"))
					return true;
				else
					return false;
			}else{
				return true;
			}
		}
		</script>
	</head>
	<ts:body>
		<ts:form action="/SC_B_BIL_S03_01DSP" enctype="multipart/form-data" onsubmit="return checkBeforeSubmit();">
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
			<table class ="header1" cellpadding="0" cellspacing="0">
				<tr>
					<td class="col1Top" width="39%">
						<table>
							<tr>
								<td>
									<font size="4px" style="headerInfo">
										<b><bean:message key="screen.b_bil.customerDetailsHeader"/>&nbsp;</b>
									</font>			
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.customerName"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									${_b_bilForm.map.headerInfo.CUST_NAME}
								</td>
							</tr>
							<tr>
								<td align="right">
								    <c:if test="${_b_bilForm.map.headerInfo.ADR_TYPE eq 'BA'}">
										<bean:message key="screen.b_bil.billingAddress"/>
									</c:if>
									<c:if test="${_b_bilForm.map.headerInfo.ADR_TYPE eq 'CA'}">
										<bean:message key="screen.b_bil.correspondenceAddress"/>
									</c:if>
									<c:if test="${_b_bilForm.map.headerInfo.ADR_TYPE eq 'RA'}">
										<bean:message key="screen.b_bil.registeredAddress"/>
									</c:if>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									${_b_bilForm.map.headerInfo.ADDRESS1}
								</td>
							</tr>
							<tr>
								<td align="right">
									&nbsp;
								</td>
								<td>
									<bean:message key="screen.b_bil.addBlank"/>
									${_b_bilForm.map.headerInfo.ADDRESS2}
								</td>
							</tr>
							<tr>
								<td align="right">
									&nbsp;
								</td>
								<td>
									<bean:message key="screen.b_bil.addBlank"/>
									${_b_bilForm.map.headerInfo.ADDRESS3}
								</td>
							</tr>
							<tr>
								<td align="right">
									&nbsp;
								</td>
								<td>
									<bean:message key="screen.b_bil.addBlank"/>
									${_b_bilForm.map.headerInfo.ADDRESS4}
								</td>
							</tr>
							<tr>
								<td align="right">	
									<bean:message key="screen.b_bil.tel"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									${_b_bilForm.map.headerInfo.TEL_NO}
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.fax"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									${_b_bilForm.map.headerInfo.FAX_NO}
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.attn"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									${_b_bilForm.map.headerInfo.CONTACT_NAME}
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.email"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									${_b_bilForm.map.headerInfo.CONTACT_EMAIL}
								</td>
							</tr>
						</table>
					</td>
					<td class="col2Top" width="1%" style="background:white">
						&nbsp;
					</td>
					<td class="col3Top" width="39%">
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
									<bean:message key="screen.b_bil.billingNo"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									${_b_bilForm.map.headerInfo.ID_REF}
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
									<bean:message key="screen.b_bil.date"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									${_b_bilForm.map.headerInfo.DATE_REQ_FOM}
								</td>
							</tr>
							<tr>
								<td>
									<bean:message key="screen.b_bil.billingAccountNo"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									${_b_bilForm.map.headerInfo.BILL_ACC}
									
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
								<td>
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
							<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'IN' or _b_bilForm.map.headerInfo.BILL_TYPE eq 'DN'}">
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
							<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'IN' or _b_bilForm.map.headerInfo.BILL_TYPE eq 'DN'}">
								<tr>
									<td colspan="2">
										&nbsp;
									</td>
								</tr>
							</c:if>
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
							<font class=preparedBy>
								<bean:message key="screen.b_bil.date"/>
							</font>
							<br/>
							<center>
								<fmt:formatDate value="${_b_bilForm.map.headerInfo.DATE_CREATED}" pattern="dd/MM/yyyy"/>
							</center>
							<br/>&nbsp;
							<br/>&nbsp;
							<br/>&nbsp;
							<br/>&nbsp;
						</div>
					</td>
				</tr>
			</table>
			<br/>
			<table class="resultInfo" cellpadding="0" cellspacing="0">
				<col width="10%"/><col width="45%"/><col width="15%"/><col width="15%"/><col width="15%"/>
				<tr class="header">
					<td style="background-color:rgb(140,176,248)" width="6%"><bean:message key="screen.b_bil.item"/></td>
					<td style="background-color:rgb(140,176,248)" width="54%"><bean:message key="screen.b_bil.billingDescription"/></td>
					<td style="background-color:rgb(140,176,248)" width="10%"><bean:message key="screen.b_bil.quantity"/></td>
					<td style="background-color:rgb(140,176,248)" width="12%"><bean:message key="screen.b_bil.unitPrice"/></td>
					<td style="background-color:rgb(140,176,248)" width="18%"><bean:message key="screen.b_bil.totalAmount"/></td>
				</tr>
				<!-- Detail content -->
				<c:set var="subTotal" value="0"></c:set>
				<c:set var="index" value="0"></c:set>
				<c:forEach items="${_b_bilForm.map.detailInfo}" var="item" varStatus="status">
					<tr>
					   <td valign="top">
					   		<c:if test="${not empty item.ITEM_NO_TEXT && item.IS_DISPLAY ne '0'}">
								${item.ITEM_NO_TEXT}
							</c:if>
						</td>
						<td>
							<pre>${item.ITEM_DESC_TEXT}</pre>
						</td>
						<td valign="top">
							<c:if test="${not empty item.ITEM_QTY && item.IS_DISPLAY ne '0'}"><fmt:formatNumber value="${item.ITEM_QTY}" pattern="#,##0"/></c:if>&nbsp;
						</td>
						<td valign="top">
							<c:if test="${not empty item.ITEM_UPRICE  && item.IS_DISPLAY ne '0'}"><fmt:formatNumber value="${item.ITEM_UPRICE}" pattern="#,##0.00"/></c:if>&nbsp;
						</td>
						<td valign="top">
							<c:if test="${not empty item.ITEM_AMT && item.IS_DISPLAY ne '0'}">
								<fmt:formatNumber value="${item.ITEM_AMT}" pattern="#,##0.00"/>
								<c:set var="subTotal" value="${subTotal + item.ITEM_AMT}"></c:set> 
							</c:if>
						</td>
					</tr>
				</c:forEach>
				<!-- End Detail content -->
				<tr style="font-weight: bold;">
			   		<td style="background-color:rgb(200,210,230)">
			   		</td>
			   		<td style="background-color:rgb(200,210,230)">
			   		</td>
			   		<td align="right" style="background-color:rgb(200,210,230)" colspan="2">
						<bean:message key="screen.b_bil.subTotal"/>
						&lt;
						${_b_bilForm.map.headerInfo.BILL_CURRENCY}
						&gt;&nbsp;&nbsp;
					</td>
					<td align="right" style="background-color:rgb(200,210,230)">
						<fmt:formatNumber value="${subTotal}" pattern="#,##0.00"/>
					</td>
				</tr>
				<tr style="font-weight: bold">
					<td style="background-color:rgb(200,210,230)">
			   		</td>
			   		<td style="background-color:rgb(200,210,230)">
			   		</td>
			   		<td align="right" style="background-color:rgb(200,210,230)" colspan="2">
						&nbsp;<bean:message key="screen.b_bil.gst"/>
						&#40;
						${_b_bilForm.map.headerInfo.GST_PERCENT}
						&#37;&#41;&nbsp;&nbsp;
					</td>
					<td align="right" style="background-color:rgb(200,210,230)">
						<fmt:formatNumber value="${_b_bilForm.map.headerInfo.GST_AMOUNT}" pattern="#,##0.00"/>
					</td>
				</tr>
					<tr>
					<td style="background-color:rgb(200,210,230)">
						&nbsp;
					</td>
					<td style="background-color:rgb(200,210,230)">
						&nbsp;
					</td>
					<td style="background-color:rgb(200,210,230)">
						&nbsp;
					</td>
					<td style="background-color:rgb(200,210,230)">
						&nbsp;
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
					<td style="background-color:rgb(136,167,216)">
						&nbsp;
					</td>
					<td style="background-color:rgb(136,167,216)">
						&nbsp;
					</td>
					<td width="20%" align="right" style="background-color:rgb(136,167,216);font-weight:bold">
						<fmt:formatNumber value="${_b_bilForm.map.headerInfo.BILL_AMOUNT}" pattern="#,##0.00"/>
					</td>
				</tr>
			</table>
			<div style="font-weight:bold"> 
				<t:defineCodeList id="LIST_CURRENCY" />
				(<c:forEach items="${LIST_CURRENCY}" var="item">
					<c:if test="${_b_bilForm.map.headerInfo.BILL_CURRENCY eq item.id}">
						${item.name}
					</c:if>
				</c:forEach>
				<bean:message key="screen.b_bil.amount"/>: 
				${bs:numberToWord(_b_bilForm.map.headerInfo.BILL_AMOUNT)}<bean:message key="screen.b_bil.only"/>
				<br/>
			</div>
			<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'IN' 
					or _b_bilForm.map.headerInfo.BILL_TYPE eq 'DN'}">
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
			</c:if>
			<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'IN' or _b_bilForm.map.headerInfo.BILL_TYPE eq 'DN'}">
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
							<logic:iterate id="bankFooter" name="_b_bilForm" property="bankFooterInfo">				
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
										<td><pre><bean:write name="bankFooter" property="COM_ACCT_NO"/>&nbsp; (<bean:write name="bankFooter" property="COM_CUR"/>&nbsp;)</pre></td>
									</tr>
									<tr> 
										<td><pre><bean:write name="bankFooter" property="COM_SWIFT"/>&nbsp;</pre></td>
									</tr>
								</table>
							</td>
							</logic:iterate>
						</tr>
					</table>
				</div>
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
            <c:if test="${_b_bilForm.map.headerInfo.PRINT_FLAG eq '1' and _b_bilForm.map.headerInfo.IS_DELETED eq '0'}">
            	<html:submit property="forward_print" styleClass="button"><bean:message key="screen.b_bil.print"/></html:submit>
            </c:if>
            <c:if test="${_b_bilForm.map.headerInfo.fromPageFlag eq 'BIL'}">
            	<bs:buttonLink action="/RP_B_BIL_S01_01BL" value="Exit"/>
            </c:if>
            <c:if test="${_b_bilForm.map.headerInfo.fromPageFlag ne 'BIL'}">
            	<html:submit property="forward_exit" styleClass="button" value="Exit"></html:submit>
            </c:if>
            <html:hidden property="idRef" value="${_b_bilForm.map.headerInfo.ID_REF}"/>
            <html:hidden property="mode" value="${_b_bilForm.map.headerInfo.mode}"/>
            <div class="message">
				<ts:messages id="message" message="true">
					<bean:write name="message"/>
				</ts:messages>
			</div>
			<div class="error">
				<ts:errors/>
			</div>
		</ts:form>
	</ts:body>
</html:html>