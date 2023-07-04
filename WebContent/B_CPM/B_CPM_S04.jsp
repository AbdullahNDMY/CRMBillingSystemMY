<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="nttdm.bsys.common.fw.BillingSystemUserValueObject"%>
<%@page import="nttdm.bsys.common.util.CommonUtils"%> 
<%
 	BillingSystemUserValueObject uvo = (BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT");
 	String accessRight = CommonUtils.getAccessRight(uvo, "B-CPM");
 	String accessRightB_SSM = CommonUtils.getAccessRight(uvo, "B-SSM");
%>
<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
   		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/common.css"/>
   		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheet/tabcontent.css"/>
   		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/B_CPM/css/b_cpm_s01.css"/>
   		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/B_CPM/css/b_cpm.css"/>
   		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/common.js"></script>	
   		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/tabcontent.js"></script>
   		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
   		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/messageBox.js"></script>
   		<script type="text/javascript" src="${pageContext.request.contextPath}/B_CPM/js/b_cpm_common.js"></script>
   		<script type="text/javascript" src="${pageContext.request.contextPath}/B_CPM/js/B_CPM_S04.js"></script>
   		<title></title>
	</head>
	<body id="b">
		<!-- check access right START -->
	 <bean:define id="accessRightBean" value="<%=accessRight %>"/>
	 <bean:define id="accessRightB_SSMBean" value="<%= accessRightB_SSM %>"/>
	<!-- check access right END -->
	<table class="subHeader" cellpadding="0" cellspacing="0">
		<tr>
   			<td><bean:message key="screen.b_cpm.s04.header"/></td>
   		</tr>
   	</table>
	<ts:form styleId="frmS01" action="/B_CPM_S04DSP" method="POST">
		<t:defineCodeList id="LIST_PLANSTATUS"/>
		<t:defineCodeList id="LIST_TRANSACTIONTYPE"/>
		<t:defineCodeList id="LIST_CURRENCY"/>
		<input type="hidden" id="hiddenContextPath" value="${pageContext.request.contextPath}" />
		<input type="hidden" id="hiddenAccessType" value="${accessType}"/>
		<html:hidden property="idScreen" styleId="idScreen"/>
		<html:hidden property="actionFlg" styleId="actionFlg"/>
		<div class="wrapper">
		<bean:define id="searchPlanPage" name="_B_CPM_S04SearchForm" property="inputSearchPlan"/>
		<bean:define id="nonTaxFlg" name="_B_CPM_S04SearchForm" property="nontaxinvoiceFlg"/>
		<nested:nest property="customerPlan">
			<nested:hidden property="idCustPlan"  styleId="idCustPlan"/>
		</nested:nest>
		<nested:nest property="inputSearchPlan">
		<table width="100%"  cellpadding="0" cellspacing="0">
			<!-- #543 Begin -->
			<colgroup>
				<col width="15%">
				<col width="2%">
				<col width="33%">
				<col width="15%">
				<col width="2%">
				<col width="33%">
			</colgroup>
			<tr>
				 <%--
					<td class="numberStyle"><bean:message key="screen.b_cpm.label.custName"/><bean:message key="screen.b_cpm.label_colon"/></td>
					<td>
						<logic:equal name="_B_CPM_S04SearchForm" property="idScreen" value="B_CPM_S02">
						<nested:hidden property="customerId"/>
						<logic:iterate id="ITEM" name="LOAD_OBJECT" property="CUST_LIST">
						<nested:define id="customerId" property="customerId"/>
						<logic:equal value="${customerId}" name="ITEM" property="ID_CUST">
						<bean:write name="ITEM" property="CUST_NAME"/>
						</logic:equal>
						</logic:iterate>
						</logic:equal>
						
						<logic:equal name="_B_CPM_S04SearchForm" property="idScreen" value="B_CPM_S05">
						<nested:select property="customerId" styleClass="customerId">
						<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
						<html:optionsCollection name="LOAD_OBJECT" property="CUST_LIST" label="CUST_NAME" value="ID_CUST"/>
						</nested:select>
						</logic:equal>
					</td>
				--%>
				<td class="textStyle"><bean:message key="screen.b_cpm.label.custName"/><span style="color: red;">*</span></td>
				<td><bean:message key="screen.b_cpm.label_colon"/></td>
				<td>
					<table width="100%"  cellpadding="0" cellspacing="0" >
						<tr>
							<td><button id="btnGetCustomerInfo" style="width:25px;"><img src="<%=request.getContextPath()%>/image/search.png"></button></td>
							<td>
								<div id="customerNameDiv"  style="word-break:break-all;">
								    <nested:empty property="customerName">
								        -
								    </nested:empty>
								    <nested:notEmpty property="customerName">
								        <nested:write property="customerName"/>
								    </nested:notEmpty>
								</div>
							</td>
							<nested:hidden property="customerName" styleId="customerNameVal" />
						</tr>
					</table>
				</td>
				<td class="textStyle"><bean:message key="screen.b_cpm.label.custId"/></td>
				<td><bean:message key="screen.b_cpm.label.colon"/></td>
				
				<td>
					<div id="customerIdDiv">
						<nested:empty property="customerId">
					        -
					    </nested:empty>
					    <nested:notEmpty property="customerId">
					        <nested:write property="customerId"/>
					    </nested:notEmpty>
					</div>
					<nested:hidden property="customerId" styleId="customerIdVal"/>
				</td>
			</tr>
			<tr>
				<td class="textStyle"><bean:message key="screen.b_cpm.label.transType"/></td>
				<td><bean:message key="screen.b_cpm.label_colon"/>
				<td>
				<nested:present property="tranType">
					<nested:define id="tranType" property="tranType"/>
				</nested:present>
				<nested:select property="tranType" styleClass="tranType">
					<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
					<c:forEach items="${LIST_TRANSACTIONTYPE}" var="item">
						<c:if test="${item.id ne 'NT'}">
							<c:choose>
						   		<c:when test="${tranType==item.id}">
									<option value="${item.id}" selected="selected">${item.name}</option>
								</c:when>
								<c:otherwise>
								    <option value="${item.id}">${item.name}</option>
								</c:otherwise>
							</c:choose>
						</c:if>
						<c:if test="${item.id eq 'NT'}">
							<c:if test="${nonTaxFlg eq '1'}"> 
								<c:choose>
									<c:when test="${tranType == item.id}">
										<option value="${item.id}" selected="selected">${item.name}</option>
									</c:when>
									<c:otherwise>
								    	<option value="${item.id}">${item.name}</option>
									</c:otherwise>
								</c:choose>
							</c:if>
						</c:if>
					</c:forEach>
					<%-- <html:optionsCollection name="LIST_TRANSACTIONTYPE" label="name" value="id"/> --%>
					</nested:select>
				</td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td class="textStyle"><bean:message key="screen.b_cpm.label.billingCurency"/></td>
				<td><bean:message key="screen.b_cpm.label_colon"/></td>
				<td>
					<nested:select property="billCurrency" styleClass="billCurrency">
					<option value=""><bean:message key="screen.b_cpm.listBox.default"/></option>
					<html:optionsCollection name="LIST_CURRENCY" label="name" value="id"/>
					</nested:select>
				</td>
				<td></td>
				<td></td>
			</tr>
			<!-- button group -->
		</table>
		<table width="100%"  cellpadding="0" cellspacing="0">
			<colgroup>
				<col width="20%">
				<col width="80%">
			</colgroup> <!-- #543 End -->
			<tr>
				<td colspan="2">
					<input type="button" id="btnSearchPlan" value="<bean:message key="screen.b_cpm.button.search"/>" onclick="javascript: doSearch();"/>
					<input type="button" id="btnResetPlan" value="<bean:message key="screen.b_cpm.button.reset"/>" onclick="javascript: onClickResetGenerateBIF();"/>
			    	<logic:equal value="2" name="accessRightBean">
			    	<span id="btnGenerate">
			    		<nested:notEqual property="totalCount" value="0">
			    		<nested:empty property="totalCount" >
			    		<input type="button" id="btnGenerate" value="<bean:message key="screen.b_cpm.button.generateBif"/>" disabled="disabled"/>
			    		</nested:empty>
			    		<nested:notEmpty property="totalCount">
			    		<input type="button" id="btnGenerate" value="<bean:message key="screen.b_cpm.button.generateBif"/>" onclick="javascript: generateBIF();"/>
			    		</nested:notEmpty>
			    		</nested:notEqual>
			    		<nested:equal property="totalCount" value="0">
			    		<input type="button" id="btnGenerate" value="<bean:message key="screen.b_cpm.button.generateBif"/>" disabled="disabled"/>
			    		</nested:equal>
			    	</span>
			    	</logic:equal>
				</td>
			</tr>
			<tr>
				<td colspan="2">
				<div class="searchFound">
					<bean:message key="screen.b_cpm.searchResult" />
					<bean:message key="screen.b_cpm.label_colon" />
					<nested:write property="totalCount" />
				</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
				<div id="plan_pageLink" class="pageLink">
					<nested:hidden styleId="plan_searchBy" styleClass="hiddenSearchBy" property="searchBy"/>
					<nested:hidden styleId="plan_startIndex" styleClass="hiddenStartIndex" property="startIndex"/>
					<nested:hidden property="row"/>
					<bean:message key="screen.b_cpm.pageLink" /> 
					<bean:message key="screen.b_cpm.label_colon" /> 
					<ts:pageLinks id="curPageLinks" 
						name="searchPlanPage"
						rowProperty="row"
						totalProperty="totalCount"
						indexProperty="startIndex" action="javascript: void(0);"/> 
					<logic:present name="curPageLinks">
						<bean:write name="curPageLinks" filter="false" />
					</logic:present>
				</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
				<table id="tableResultSearchPlan" class="resultInfo">
				<colgroup>
				    <col width="4%">
					<col width="5%">
					<col width="5%">
					<col width="5%">
					<col width="5%">
					<logic:equal name="_B_CPM_S04SearchForm" property="cpmTransType" value="1">
						<col width="6%">
						<col width="7%">
					</logic:equal>
					<logic:notEqual name="_B_CPM_S04SearchForm" property="cpmTransType" value="1">
						<col width="13%">
					</logic:notEqual>
					<col width="7%">
					<col width="7%">
					<col width="7%">
					<col width="7%">
					<col width="7%">
					<col width="7%">
					<col width="7%">
					<col width="7%">
					<col width="7%">
				</colgroup>
				<tr class="header">
				    <td class="headerLeft" nowrap="nowrap"><input type="checkbox" id="totalcheck" name="totalcheck" onclick="checkAll(this);"/></td>
					<td class="headerLeft" nowrap="nowrap"><bean:message key="screen.b_cpm.header.no" /></td>
					<td class="headerLeft" nowrap="nowrap"><bean:message key="screen.b_cpm.header.custId" /></td>
					<td class="headerLeft" nowrap="nowrap" colspan="2"><bean:message key="screen.b_cpm.header.custname" /></td>
					<td class="headerLeft" nowrap="nowrap" colspan="2">
						<bean:message key="screen.b_cpm.header.subId" /><br>
						<bean:message key="screen.b_cpm.header.desc" />
					</td>
					<logic:equal name="_B_CPM_S04SearchForm" property="cpmTransType" value="1">
						<td class="headerLeft" nowrap="nowrap"><bean:message key="screen.b_cpm.header.trans" /></td>
					</logic:equal>
					<td class="headerLeft" nowrap="nowrap"><bean:message key="screen.b_cpm.header.bacNo" /></td>
					<td class="headerLeft" nowrap="nowrap"><bean:message key="screen.b_cpm.header.billInst" /></td>
					<td class="headerLeft" nowrap="nowrap"><bean:message key="screen.b_cpm.header.currency" /></td>
					<td class="headerLeft" nowrap="nowrap"><bean:message key="screen.b_cpm.header.amount" /></td>
					<td class="headerLeft" nowrap="nowrap"><bean:message key="screen.b_cpm.header.serviceStart" /></td>
					<td class="headerLeft" nowrap="nowrap"><bean:message key="screen.b_cpm.header.serviceEnd" /></td>
					<td class="headerLeft" nowrap="nowrap"><bean:message key="screen.b_cpm.header.status" /></td>
				</tr>
				<logic:iterate id="plan_h" name="_B_CPM_S04SearchForm" property="searchResult" indexId="i">
					<tbody class="trResultSearchPlan">
						<tr class="detail">
						    <td class="headerBorder"><nested:multibox property="idCustPlans" value="${plan_h.ID_CUST_PLAN}" styleClass="idCustPlansChk" onclick="checkAllCheckBox();"/></td>
							<td class="headerBorder">${i + 1 + searchPlanPage.startIndex}</td>
							<td nowrap="nowrap" class="headerBorder"><a href="javascript: viewCustomer('<bean:write name="plan_h" property="ID_CUST"/>', '<bean:write name="plan_h" property="CUSTOMER_TYPE"/>');"><bean:write name="plan_h" property="ID_CUST"/></a></td>
							<td nowrap="nowrap" colspan="2" class="headerBorder"><bean:write name="plan_h" property="CUST_NAME"/>&nbsp;</td>
							<td nowrap="nowrap" colspan="2" class="headerBorder">
							    <c:choose>
									<c:when test="${'1' eq accessRightB_SSMBean or '2' eq accessRightB_SSMBean}">
						    			<a href="javascript: viewSubScriptionInfo('<bean:write name="plan_h" property="ID_CUST_PLAN"/>', '<bean:write name="plan_h" property="ID_CUST"/>', '<bean:write name="plan_h" property="ID_SUB_INFO"/>')"><bean:write name="plan_h" property="ID_SUB_INFO"/></a>
						    		</c:when>
						    		<c:otherwise>
						    		    <bean:write name="plan_h" property="ID_SUB_INFO"/>
						    		</c:otherwise>
							    </c:choose>
							</td>
							<logic:equal name="_B_CPM_S04SearchForm" property="cpmTransType" value="1">
								<td nowrap="nowrap" class="headerBorder">
									<bean:write name="plan_h" property="TRANSACTION_TYPE"/>
								<!--  <t:writeCodeValue codeList="LIST_TRANSACTIONTYPE" name="plan_h" property="TRANSACTION_TYPE"/>&nbsp; -->
								</td>
							</logic:equal>
							<td nowrap="nowrap" class="headerBorder">
								<logic:equal value="-" name="plan_h" property="ID_BILL_ACCOUNT">
									<bean:write name="plan_h" property="ID_BILL_ACCOUNT"/>&nbsp;
								</logic:equal>
								<logic:notEqual value="-" name="plan_h" property="ID_BILL_ACCOUNT">
									<a href="javascript: viewBAC('<bean:write name="plan_h" property="ID_CUST_PLAN"/>', '<bean:write name="plan_h" property="ID_CUST"/>', '<bean:write name="plan_h" property="ID_BILL_ACCOUNT"/>');"><bean:write name="plan_h" property="ID_BILL_ACCOUNT"/></a>&nbsp;
								</logic:notEqual>
							</td>
							<td nowrap="nowrap" class="headerBorder">
								<bean:write name="plan_h" property="BILL_INSTRUCT"/>
								<t:writeCodeValue codeList="BILL_INSTRUCTION_LIST" name="plan_h" property="BILL_INSTRUCT"/>&nbsp;
							</td>
							<td nowrap="nowrap" class="headerBorder">
								<bean:write name="plan_h" property="BILL_CURRENCY"/>&nbsp;
							</td>
							<td nowrap="nowrap"  class="numberStyleBold" style="border-top:1px solid #d0d0d0;">
								<fmt:formatNumber value="${plan_h.TOTAL_AMOUNT}" pattern="#,###,###.00"/>&nbsp;
							</td>
							<td nowrap="nowrap" class="headerBorder"></td>
							<td nowrap="nowrap" colspan="2" class="headerBorder"></td>
						</tr>
						<!-- SUB PLAN -->
						<logic:iterate id="plan_d" name="plan_h" property="SUB_PLAN" indexId="j">
							<tr class="hasSubPlan">
							    <td></td>
								<td></td>
								<td></td>
								<td colspan="2"></td>
								<logic:equal name="_B_CPM_S04SearchForm" property="cpmTransType" value="1">
									<td colspan="6" nowrap="nowrap">
										<span class="spanToggleClick">...</span>
										<a href="javascript: viewCustomerPlan('<bean:write name="plan_h" property="ID_CUST_PLAN"/>')"><bean:write name="plan_d" property="BILL_DESC"/></a>
									</td>
								</logic:equal>
								<logic:notEqual name="_B_CPM_S04SearchForm" property="cpmTransType" value="1">
									<td colspan="5" nowrap="nowrap">
										<span class="spanToggleClick">...</span>
										<a href="javascript: viewCustomerPlan('<bean:write name="plan_h" property="ID_CUST_PLAN"/>')"><bean:write name="plan_d" property="BILL_DESC"/></a>
									</td>
								</logic:notEqual>
								<td nowrap="nowrap" class="numberStyle">
									<fmt:formatNumber value="${plan_d.TOTAL_AMOUNT}" pattern="#,###,###.00"/>&nbsp;
								</td>
								<td nowrap="nowrap" class="dateStyle">&nbsp;<bean:write name="plan_d" property="SVC_START"/>&nbsp;</td>
								<td nowrap="nowrap">&nbsp;<bean:write name="plan_d" property="SVC_END"/>&nbsp;</td>
								<td nowrap="nowrap" class="dateStyle">
									<t:writeCodeValue codeList="LIST_PLANSTATUS" name="plan_d" property="SERVICES_STATUS"/>
								</td>
							</tr>
							<!-- SUB PLAN LINK -->
							<logic:notEmpty name="plan_d" property="SUB_PLAN_LINK">
								<tr class="spanTogglePlace">
								    <td></td>
									<td></td>
									<td></td>
									<logic:equal name="_B_CPM_S04SearchForm" property="cpmTransType" value="1">
											<td colspan="12" style="padding:5px;">
												<table cellpadding="0" cellspacing="0" border="0" style="width:100%;height:100%;color: #a04c51;font-size:15px;font-family:'Calibri';border:1px solid #CEE7FF;">
													<tr>
														<td style="width:45%;padding-left:5px;background-color: #CEE7FF;"><bean:message key="screen.b_cpm.label.detail.itemDescription" /></td>
														<td style="width:10%;padding-left:5px;background-color: #CEE7FF;"><bean:message key="screen.b_cpm.label.detail.rateType" /></td>
														<td style="width:10%;padding-left:5px;background-color: #CEE7FF;"><bean:message key="screen.b_cpm.label.detail.rateMode" /></td>
														<td style="width:10%;padding-left:5px;background-color: #CEE7FF;"><bean:message key="screen.b_cpm.label.quantity" /></td>
														<td style="width:10%;padding-left:5px;background-color: #CEE7FF;"><bean:message key="screen.b_cpm.label.price" /></td>
														<td style="width:15%;padding-left:5px;background-color: #CEE7FF;"><bean:message key="screen.b_cpm.header.amount" /></td>
													</tr>
													<logic:iterate id="plan_link" name="plan_d" property="SUB_PLAN_LINK">
														<tr>
															<td style="padding-left:5px;border-top:1px solid #CEE7FF;"><bean:write name="plan_link" property="ITEM_DESC"/></td>
															<td style="padding-left:5px;border-top:1px solid #CEE7FF;"><t:writeCodeValue codeList="LIST_RATETYPE" key="${plan_link.RATE_TYPE}" /></td>
															<td style="padding-left:5px;border-top:1px solid #CEE7FF;"><t:writeCodeValue codeList="LIST_RATEMODE" key="${plan_link.RATE_MODE}" /></td>
															<td style="padding-right:25px;text-align:right;border-top:1px solid #CEE7FF;"><fmt:formatNumber value="${plan_link.QUANTITY}" pattern="#,###,###"/></td>
															<td style="padding-right:25px;text-align:right;border-top:1px solid #CEE7FF;"><fmt:formatNumber value="${plan_link.UNIT_PRICE}" pattern="#,###,###.00"/></td>
															<td style="padding-right:60px;text-align:right;border-top:1px solid #CEE7FF;"><fmt:formatNumber value="${plan_link.TOTAL_AMOUNT}" pattern="#,###,###.00"/></td>
														</tr>
													</logic:iterate>
												</table>
											</td>
									</logic:equal>
									<logic:notEqual name="_B_CPM_S04SearchForm" property="cpmTransType" value="1">
											<td colspan="11" style="padding:5px;">
												<table cellpadding="0" cellspacing="0" border="0" style="width:100%;height:100%;color: #a04c51;font-size:15px;font-family:'Calibri';border:1px solid #CEE7FF;">
													<tr>
														<td style="width:45%;padding-left:5px;background-color: #CEE7FF;"><bean:message key="screen.b_cpm.label.detail.itemDescription" /></td>
														<td style="width:10%;padding-left:5px;background-color: #CEE7FF;"><bean:message key="screen.b_cpm.label.detail.rateType" /></td>
														<td style="width:10%;padding-left:5px;background-color: #CEE7FF;"><bean:message key="screen.b_cpm.label.detail.rateMode" /></td>
														<td style="width:10%;padding-left:5px;background-color: #CEE7FF;"><bean:message key="screen.b_cpm.label.quantity" /></td>
														<td style="width:10%;padding-left:5px;background-color: #CEE7FF;"><bean:message key="screen.b_cpm.label.price" /></td>
														<td style="width:15%;padding-left:5px;background-color: #CEE7FF;"><bean:message key="screen.b_cpm.header.amount" /></td>
													</tr>
													<logic:iterate id="plan_link" name="plan_d" property="SUB_PLAN_LINK">
														<tr>
															<td style="padding-left:5px;border-top:1px solid #CEE7FF;"><bean:write name="plan_link" property="ITEM_DESC"/></td>
															<td style="padding-left:5px;border-top:1px solid #CEE7FF;"><t:writeCodeValue codeList="LIST_RATETYPE" key="${plan_link.RATE_TYPE}" /></td>
															<td style="padding-left:5px;border-top:1px solid #CEE7FF;"><t:writeCodeValue codeList="LIST_RATEMODE" key="${plan_link.RATE_MODE}" /></td>
															<td style="padding-right:25px;text-align:right;border-top:1px solid #CEE7FF;"><fmt:formatNumber value="${plan_link.QUANTITY}" pattern="#,###,###"/></td>
															<td style="padding-right:25px;text-align:right;border-top:1px solid #CEE7FF;"><fmt:formatNumber value="${plan_link.UNIT_PRICE}" pattern="#,###,###.00"/></td>
															<td style="padding-right:60px;text-align:right;border-top:1px solid #CEE7FF;"><fmt:formatNumber value="${plan_link.TOTAL_AMOUNT}" pattern="#,###,###.00"/></td>
														</tr>
													</logic:iterate>
												</table>
											</td>
									</logic:notEqual>
								</tr>
							</logic:notEmpty><!-- END SUB PLAN LINK -->
						</logic:iterate><!-- END SUB PLAN -->
					</tbody>
				</logic:iterate><!-- END PLAN -->
				</table>
				</td>
			</tr>
		</table>
		</nested:nest>
		</div>
		<div class="message">
			<ts:messages id="message" message="true">
				<bean:write name="message"/>
			</ts:messages>
		</div>
		<div class="error">
			<html:messages id="message">
				<bean:write name="message"/><br/>
			</html:messages>
		</div>
    </ts:form>
	</body>
</html:html>

