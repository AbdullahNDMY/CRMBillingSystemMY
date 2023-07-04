<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/bs" prefix="bs" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
		<link href="<%=request.getContextPath()%>/B_CSB/css/b_csbs01.css" rel="stylesheet" type="text/css" /> 
	    <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/B_CSB/js/b_csbs01.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
		<title>Insert title here</title>
		<script language="javascript">
		function clickBillReferenceLink(receipt_Ref) {
			document.getElementById("idRef").value = receipt_Ref;
			document.forms[0].action = "<%=request.getContextPath()%>/B_CSB/B_CSBS02V.do";
			document.forms[0].submit();
		}
		function clickNew() {
			document.forms[0].action = "<%=request.getContextPath()%>/B_CSB/B_CSBS02SCR.do";
			document.forms[0].submit();
		}
		function reloadModuleName() {
			isLoaded = true;
			if (window.parent && window.parent.frame_top) {
				if (parent.frame_top.isLoaded) parent.frame_top.initPage();
			}
		}
		function searchClick() {
			var startIndex = document.getElementById("startIndex");
			if(startIndex!=null && startIndex!=undefined) {
				startIndex.value="0";
			}
		}
		// #186 Start
		function exportCSV() {
			var exportLimit = $("#exportLimit").val();
			var searchCount = $("#searchCount").val();
			if (parseInt(searchCount) >= parseInt(exportLimit)) {
				var warnMsg = "Total results to be exported exceed export limit " + exportLimit;
				alert(warnMsg);
				return false;
			} else {
				showLoadingTipWindow();
			}
		}
		//#186 End
		</script>
	<body id="b" onload="reloadModuleName();">	
	<div style="width:1180px">
		<ts:form action="/SC_B_CSB_S01DSP">
			<t:defineCodeList id="PAYMENT_REF_HEADER" />
			<t:defineCodeList id="LIST_CUSTOMERTYPE" />
			<input type="hidden" id="idRef" name="idRef"/>
			<input type="hidden" name="checkpageview" value="1"/>
			<html:hidden property="defCurCode" name="b_csbForm"/>
			<!-- #186 Start -->
		    <t:defineCodeList id="LIST_EXPORT_LIMIT" />
		    <input id="exportWarnMsg" type="hidden" value="<bean:message key='errors.ERR1SC105' arg0='0'/>"/>
		    <input id="exportLimit" type="hidden" value="<t:writeCodeValue codeList='LIST_EXPORT_LIMIT' key='B-CSB' name='export_limit'></t:writeCodeValue>"/>
		    <input id="searchCount" type="hidden" value="${b_csbForm.totalRow}"/>
		    <!-- #186 End -->
            <h1 class="title"><bean:message key="screen.b_csb.title"/></h1>
			<div class="section" style="border-top:2px solid #cecece;border-bottom:2px solid #cecece;padding:5px 5px;margin-top:-5px;">
				<table cellpadding="0" cellspacing="0">
					<tr>
					    <td align="right"><bean:message key="screen.b_csb.ornoclol"/><bean:message key="screen.b_csb.colon"/>
						</td>
						<td class="colLeft" >
							<html:text name="b_csbForm" property="receiptNo" maxlength="20" styleClass="CashBookTextBox" style="width:230px;"/>
						</td>
						<td class="tdBlankWidth"></td>
						<td align="right">
							<bean:message key="screen.b_csb.transactiondate"/><bean:message key="screen.b_csb.colon"/>
						</td>
						<td>
							<html:text property="startDate" name="b_csbForm" readonly="true" style="width:70px;"/>
		                    <t:inputCalendar for="startDate" format="dd/MM/yyyy"/>&nbsp;<bean:message key="screen.b_csb._"/>&nbsp;
		            		<html:text property="endDate" name="b_csbForm" readonly="true" style="width:70px;"/>
		                    <t:inputCalendar for="endDate" format="dd/MM/yyyy"/>
						</td>
					</tr>
					<tr>
					    <td align="right">
							<bean:message key="screen.b_csb.payee"/><bean:message key="screen.b_csb.colon"/> 
						</td>
						<td>
							<html:text name="b_csbForm" property="payer" maxlength="100" style="width:230px;"></html:text>
						</td>
						<td class="tdBlankWidth"></td>
						<td align="right"><bean:message key="screen.b_csb.bankaccount"/><bean:message key="screen.b_csb.colon"/>
						</td>
						<td class="colLeft">
							<html:select name="b_csbForm" property="bankAcc">
								<option value=""> <bean:message key="screen.b_csb.blankitem"/> </option>
								<logic:present property="cbBankAcc" name="b_csbForm">
									<html:optionsCollection name="b_csbForm" property="cbBankAcc"/>
								</logic:present>
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							<bean:message key="screen.b_csb.payerType"/><bean:message key="screen.b_csb.colon"/> 
						</td>
						<td><html:select
		                    property="payerType"
		                    name="b_csbForm" style="width:230px;">
		                    <option value="">
		                        <bean:message key="screen.b_csb.blankitem" />
		                    </option>
		                    <%--<html:optionsCollection name="LIST_CUSTOMERTYPE"  label="name" value="id"/>--%>
		                    <c:forEach items="${LIST_CUSTOMERTYPE}" var="item">
		                        <c:if test="${item.id ne 'BOTH'}">
		                            <html:option value="${item.id}">${item.name}</html:option>
		                        </c:if>
		                    </c:forEach>
		                </html:select></td>
						<td class="tdBlankWidth"></td>
						<td align="right"><t:writeCodeValue codeList="PAYMENT_REF_HEADER" key="PaymentRef1"/><bean:message key="screen.b_csb.colon"/>
						</td>
						<td class="colLeft" >
							<html:text name="b_csbForm" property="refNo" maxlength="20" styleClass="CashBookTextBox" />
						</td>
					</tr>
					<tr>
						<td align="right"><bean:message key="screen.b_csb.billaccNo"/><bean:message key="screen.b_csb.colon"/>
						</td>
						<td class="colLeft" >
							<html:text name="b_csbForm" property="billaccNo" maxlength="20" styleClass="CashBookTextBox" style="width:230px;"/>
						</td>
						<td class="tdBlankWidth"></td>
						<td align="right"><bean:message key="screen.b_csb.paymentstatus"/><bean:message key="screen.b_csb.colon"/>
						</td>
						<td class="colLeft">
							<t:defineCodeList id="LIST_PAYMENT_STATUS" />
							<html:select name="b_csbForm" property="paymentStatus" style="width:230px;">
								<html:option value="" ><bean:message key="screen.b_csb.blankitem"/></html:option>
								<html:options collection="LIST_PAYMENT_STATUS" property="id" labelProperty="name"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right"><bean:message key="screen.b_csb.paymentmethod"/><bean:message key="screen.b_csb.colon"/>
						</td>
						<td class="colLeft">
							<t:defineCodeList id="LIST_PAYMENT_METHOD" />
							<html:select name="b_csbForm" property="pmtMethod" style="width:230px;">
								<html:option value="" ><bean:message key="screen.b_csb.blankitem"/></html:option>
								<html:options collection="LIST_PAYMENT_METHOD" property="id" labelProperty="name"/>
							</html:select>
						</td>
						<td class="tdBlankWidth"></td>
						<td class="colLeft" colspan="2">
							<html:checkbox name="b_csbForm" property="filterBybalance" value="true">
								<bean:message key="screen.b_csb.filterbybalance"/>
							</html:checkbox>
						</td>
					</tr>
					<tr>
						<td align="right"><bean:message key="screen.b_csb.paymentcurrency"/><bean:message key="screen.b_csb.colon"/>
						</td>
						<td class="colLeft">
							<t:defineCodeList id="LIST_CURRENCY" />
							<html:select name="b_csbForm" property="curCode" style="width:230px;">
								<html:option value="" ><bean:message key="screen.b_csb.blankitem"/></html:option>
								<html:options collection="LIST_CURRENCY" property="id" labelProperty="name"/>
							</html:select>
						</td>
					</tr>
				</table>
			</div>
			<table class="buttonGroup" cellpadding="0" cellspacing="0">
			  	<tr>
					<td>
						<input type="submit" class="button" value="<bean:message key="screen.b_csb.searchbutton"/>" name="forward_search" onclick="searchClick();showLoadingTipWindow();"/>
						<bs:buttonLink action="/B_CSBR01BLogic" value="Reset"/>
						<%
						String accessType = ((nttdm.bsys.common.fw.BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT")).getUserAccessInfo("B", "B-CSB").getAccess_type();
						if(accessType.equals("2")) {
						%>			
						<input type="button" class="button" value="<bean:message key="screen.b_csb.newbutton"/>" onclick="clickNew()"/>
						<%} %>
						<%
						if("2".equals(accessType) || "1".equals(accessType)) {
						%>
						<c:if test="${b_csbForm.totalRow > 0}">
							<html:submit property="forward_export" onclick="return exportCSV();showLoadingTipWindow();"><bean:message key="screen.b_csb.btnExportReport"/></html:submit>
						</c:if>
						<c:if test="${b_csbForm.totalRow == 0 or b_csbForm.totalRow == null}">
							<html:submit property="forward_export" disabled="true"><bean:message key="screen.b_csb.btnExportReport"/></html:submit>
						</c:if>
						<%
						}
						%>
					</td>	
				</tr>
			</table>
			<table class="searchResultNo" cellpadding="0" cellspacing="0">
			  	<tr>
					<td>
						<bean:message key="screen.b_csb.searchfound"/> <bean:message key="screen.b_csb.colon"/>
						<bean:write name="b_csbForm" property="totalRow"/>					
					</td>	
					<td>&nbsp;</td>
					<td align="right" width="15%">
						<bean:message key="screen.b_csb.totalbalance"/>:
					</td>
					<td width="15%">						
						<fmt:formatNumber value="${b_csbForm.totalBalanceAmt}" pattern="#,##0.00"/>
					</td>
					<td align="right" width="15%">
						<bean:message key="screen.b_csb.totalamount"/>:
					</td>
					<td width="15%">
					    <logic:equal name="b_csbForm" property="paymentStatus" value="F">
						<fmt:formatNumber value="${-b_csbForm.totalAmt}" pattern="#,##0.00"/>
						</logic:equal>
						<logic:notEqual name="b_csbForm" property="paymentStatus" value="F" >
						<fmt:formatNumber value="${b_csbForm.totalAmt}" pattern="#,##0.00"/>
						</logic:notEqual>
					</td>
				</tr>
			</table>  
			<table class="pageLink" cellpadding="0" cellspacing="0">
			  	<tr>
					<td>			
						<bean:message key="screen.b_csb.gotopage"/>
											
						<ts:pageLinks id="csbPageLinks"
			              action="${pageContext.request.contextPath}/B_CSB/B_CSBR01BLogic.do?checkpageview=1" 
			              name="b_csbForm" 
			              rowProperty="row"
			              totalProperty="totalRow" indexProperty="startIndex"
			              currentPageIndex="now" totalPageCount="total"
						  submit="true"/>
			            <logic:present name="csbPageLinks">  
							<bean:write name="csbPageLinks" filter="false"/>
						</logic:present>	
					</td>	
				</tr>
			</table> 
			<table  class="resultInfo" cellpadding="0" cellspacing="0">
			  <tr>
			    <td class="header" width="1%" style="padding-left:0.05in;"><bean:message key="screen.b_csb.nocol"/></td>
			    <td class="header" width="5%" style="text-align:left;padding-left:0.05in;"><bean:message key="screen.b_csb.datecol"/></td>
			    <td class="header" width="17%" style="text-align:left;padding-left:0.05in;"><bean:message key="screen.b_csb.payeecol"/></td>
			    <td class="header" width="10%" style="text-align:left;padding-left:0.05in;"><bean:message key="screen.b_csb.billaccNo"/></td>
			    <td class="header" width="10%" style="text-align:left;padding-left:0.05in;"><bean:message key="screen.b_csb.ornoclol"/></td>
			    <td class="header" width="13%" style="text-align:left;padding-left:0.05in;"><t:writeCodeValue codeList="PAYMENT_REF_HEADER" key="PaymentRef1"/></td>
			    <td class="header" width="11%" style="text-align:right;padding-right:0.05in;padding-left:0.05in;"><bean:message key="screen.b_csb.amountreceivedcol"/></td>
			    <td class="header" width="10%" style="text-align:right;padding-right:0.05in;padding-left:0.05in;"><bean:message key="screen.b_csb.balance"/></td>
			    <td class="header" width="9%" style="text-align:left;padding-left:0.05in;"><bean:message key="screen.b_csb.pmtmethodcol"/></td>
			    <td class="header" width="14%" style="text-align:left;padding-left:0.05in;"><bean:message key="screen.b_csb.bankaccountcol"/></td>
			    <td class="header" width="5%" style="text-align:left;padding-left:0.05in;"><bean:message key="screen.b_csb.paymentstatus"/></td>
			  </tr>
			  <t:defineCodeList id="LIST_PAYMENT_METHOD" />
		   <logic:present property="csbInfos" name="b_csbForm">
		      <logic:iterate id="Info"   name="b_csbForm" property="csbInfos" >
			<tr>
				<td class="defaultNo" align="left">
					<bean:write name="Info" property="NINDEX"/>
				</td>
				<td class="defaultText" align="left" style="padding-left:0.05in;">
					<bean:write name="Info" property="DATE_TRANS"/>	
				</td>
				<td class="defaultText" align="left" style="padding-left:0.05in;">
					<bean:write name="Info" property="CUST_NAME"/>
				</td>
				<td class="defaultText" align="left" style="padding-left:0.05in;">
				    <html:link action="../B_BAC/RP_B_BAC_S02_02_01BL.do?idBillAccount=${Info.ID_BILL_ACCOUNT}&idCustPlan=''&fromPage=B_CSB_S01">
				        <bean:write name="Info" property="ID_BILL_ACCOUNT"/>
				    </html:link>
				</td>
				<td class="defaultText" align="left" style="padding-left:0.05in;">
				    <logic:equal value="F" name="Info" property="PMT_STATUS">
						<html:link action="/B_CSB_S03View.do?receiptNo=${Info.RECEIPT_NO}">
	                        <bean:write name="Info" property="RECEIPT_NO"/>
	                    </html:link>
				    </logic:equal>
				    <logic:notEqual value="F" name="Info" property="PMT_STATUS">
				        <a href="#" onclick="clickBillReferenceLink('${Info.RECEIPT_NO}')" ><bean:write name="Info" property="RECEIPT_NO"/></a>
				    </logic:notEqual>
				</td>
				<td class="defaultText" align="left" style="padding-left:0.05in;">
					<bean:write name="Info" property="REFERENCE1"/>
				</td>
				<td class="defaultText" style="text-align:right;padding-right:0.05in;padding-left:0.05in;" >
					<bean:write name="Info" property="CUR_CODE"/>&nbsp;
					<logic:equal value="F" name="Info" property="PMT_STATUS">
					   <fmt:formatNumber value="${-Info.AMT_RECEIVED}" pattern="#,##0.00"/>
					</logic:equal>
					<logic:notEqual value="F" name="Info" property="PMT_STATUS">
                       <fmt:formatNumber value="${Info.AMT_RECEIVED}" pattern="#,##0.00"/>
                    </logic:notEqual>
				</td>
				<td class="defaultText" style="text-align:right;padding-right:0.05in;padding-left:0.05in;" >
					<bean:write name="Info" property="CUR_CODE"/>&nbsp;<fmt:formatNumber value="${Info.BALANCE_AMT}" pattern="#,##0.00"/>
				</td>
				<td class="defaultText" align="left" style="padding-left:0.05in;">
					<t:writeCodeValue codeList="LIST_PAYMENT_METHOD" key="${Info.PMT_METHOD}"/>
				</td>
				<td class="defaultText" align="left" style="padding-left:0.05in;">
					<bean:write name="Info" property="BANK_ACC"/>
				</td>
				<td class="defaultText" align="left" style="padding-left:0.05in;">
					<t:writeCodeValue codeList="LIST_PAYMENT_STATUS" key="${Info.PMT_STATUS}"/>
				</td>
			</tr>
		   </logic:iterate>
 		</logic:present>
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
       	</div>
	</body>
</html:html>
