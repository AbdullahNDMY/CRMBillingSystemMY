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

<html>
<head>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
	<link href="${pageContext.request.contextPath}/B_BAC/css/b_bac.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
 	<title><bean:message key="screen.b_bac_s01.title"/></title>
 	<script type="text/javascript">
 		function initData(){
 			var newFlg = document.getElementById("newFlg").value;
 			if(""==newFlg){
 				document.getElementById("cboCustomerName").value="";
 			}
 		}

        // call back function by Customer Name Search Screen(M_CST_S04) 
        function setCustomerInfo(custName,custID,custType){
            $('input[name=txtCustomerName]').val(custName);
            $('input[name=cboCustomerName]').val(custID);
        }

        // B_BAC_S03 page return call 
        function setCustomerAndBillAccInfo(idCust,custName,idBillAcc){
        	document.getElementById("cboCustomerNameDisplay").innerText=idCust;
            document.getElementById("txtCustomerNameDisplay").innerText = custName;
            document.getElementById("cboCustomerName").value = idCust;
            document.getElementById("txtCustomerName").value = custName;
            document.getElementById("tbxBillingAccount").value = idBillAcc;
        }
        function searchClick() {
			var startIndex = document.getElementById("startIndex");
			if(startIndex!=null && startIndex!=undefined) {
				startIndex.value="0";
			}
			showLoadingTipWindow();
		}
        //open cpm05
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
        
 		$(document).ready(function(){
 	        var POPUP_FEATURE = "height=#height#,width=#width#,left=#left#,top=#top#,screenX=#left#,screenY=#top#,toolbar=no,status=no,menubar=no,location=no,resizable=yes,scrollbars=yes";
 	        
 		    // Search Customer
	 		$('#btnGetCustomerInfo').click(function(){
	 	        var contextpath = $('input[name=contextpath]').val();
	 	        var url = contextpath + "/B_BAC/RP_B_BAC_S03SearchBL.do";
	 	        var popupWidth = window.screen.width*80/100;
	 	        var popupHeight = window.screen.height*80/100;
	 	        var left = Number((screen.availWidth/2) - (popupWidth/2));
	 	        var top = Number((screen.availHeight/2) - (popupHeight/2));
	 	        var newWindow = window.open(url,'name', POPUP_FEATURE.replace("#width#",popupWidth).replace("#height#",popupHeight).replace("#left#",left).replace("#top#",top));
	 	        if (window.focus) { newWindow.focus(); }
	 	        return false;
	 	    });
 		});
 	</script>
</head>
<body id="b" onload="initMain();initData()">
<ts:form action="/SC_B_BAC_S04DSP" >
    <html:hidden property="contextpath" value="<%=request.getContextPath()%>" />
	<h1 class="title"><bean:message key="screen.b_bac_s01.title"/></h1>
		<div class="section" style="border-top:2px solid #cecece;border-bottom:2px solid #cecece;padding:5px 20px;margin-top:-5px;">
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td align="right">
						<bean:message key="screen.b_bac.customerName"/>
						<span style="color: red;">*</span>
					</td>
					<td>
                        <bean:message key="screen.b_bac.colon"/>
                    </td>
                    <td width="250px">    
                        <button id="btnGetCustomerInfo" style="width:25px;"><img src="<%=request.getContextPath()%>/image/search.png"></button>
                        <span id="txtCustomerNameDisplay"><bean:write property="txtCustomerName" name="_b_bacForm"/></span>
                        <html:hidden property="txtCustomerName" name="_b_bacForm"/>
					</td>
					<td align="right">
					   <bean:message key="screen.b_bac.customerId"/>
					</td>
					<td>
                        <bean:message key="screen.b_bac.colon"/>
                    </td>
                    <td>
                        <span id="cboCustomerNameDisplay"><bean:write property="cboCustomerName" name="_b_bacForm"/></span>
                        <html:hidden property="cboCustomerName" name="_b_bacForm"/>
                    </td>
				</tr>
				<tr>
					<td align="right">
						<bean:message key="screen.b_bac.billingAccountNo"/>
					</td>
					<td>
						<bean:message key="screen.b_bac.colon"/>
					</td>
                    <td colspan="4">
						<html:text property="tbxBillingAccount" name="_b_bacForm" size="30" maxlength="20" />
					</td>
				</tr>	
				<tr>
					<td align="right">
						<bean:message key="screen.b_bac.SubscriptionID"/>
					</td>
					<td>
						<bean:message key="screen.b_bac.colon"/>
					</td>
                    <td colspan="4">
						<html:text property="tbxSubscription" name="_b_bacForm" size="30"/>
					</td>
				</tr>
				<tr>
                    <td align="right">
                        <bean:message key="screen.b_bac_s04.status"/>
                    </td>
                    <td>
                        <bean:message key="screen.b_bac.colon"/>
                    </td>
                    <td colspan="4">
                        <html:multibox name="_b_bacForm" property="chkStatus" value="PS3">
                        </html:multibox>
                        <bean:message key="screen.b_bac_s04.status.active" />
                        <html:multibox name="_b_bacForm" property="chkStatus" value="PS7">
                        </html:multibox>
                        <bean:message key="screen.b_bac_s04.status.terminated" />
                        <html:multibox name="_b_bacForm" property="chkStatus" value="PS6">
                        </html:multibox>
                        <bean:message key="screen.b_bac_s04.status.suspended" />
                    </td>
                 </tr>
			</table>
		</div>
		<br/>
		<div>
			<html:submit property="forward_search" onclick="searchClick()"><bean:message key='screen.b_bac.search'/></html:submit>
			<bs:buttonLink action="/RP_B_BAC_S04_01BL" value="Reset"/>
			<c:if test="${_b_bacForm.map.totalRow == 0 or _b_bacForm.map.totalRow == null}">
				<html:submit property="forward_new" disabled="true"><bean:message key="screen.b_bac_s04.button.new"/></html:submit>
				<html:submit property="forward_merge" disabled="true"><bean:message key="screen.b_bac_s04.button.merge"/></html:submit>
			</c:if>
			<c:if test="${_b_bacForm.map.totalRow ne null and _b_bacForm.map.totalRow > 0}">
                <html:submit property="forward_new" ><bean:message key="screen.b_bac_s04.button.new"/></html:submit>
                <html:submit property="forward_merge" ><bean:message key="screen.b_bac_s04.button.merge"/></html:submit>
            </c:if>
		</div>
		<br/>
		<div class="section">
			<div class="group result">
				<h2><bean:message key="screen.b_bac.searchFound" /> ${_b_bacForm.map.totalRow}</h2>
			</div>
			<div class="pageLink">
				<bean:message key="screen.b_bac.gotoPage"/> <bean:message key="screen.b_bac.colon"/>
				<ts:pageLinks 
	    			id="curPageLinks"
					action="${pageContext.request.contextPath}/B_BAC/RP_B_BAC_S04_02BL.do" 
					name="_b_bacForm" 
					rowProperty="row"
					totalProperty="totalRow" 
					indexProperty="startIndex"
					currentPageIndex="now" 
					submit="true"
					totalPageCount="total"/>
				<logic:present name="curPageLinks">  
					<bean:write name="curPageLinks" filter="false"/>
				</logic:present>
			</div>
		</div>
		<div class="section">
		<table  class="resultInfo" cellpadding="5" cellspacing="" width="100%">
			<tr>
				<td class="header" width="2%">&nbsp;</td>
				<td class="header" width="3%"><bean:message key="screen.b_bac_s04.searchResult.no"/></td>
				<td class="header" width="9%" nowrap="nowrap"><bean:message key="screen.b_bac_s04.searchResult.billacc"/></td>
				<td class="header" width="25%"><bean:message key="screen.b_bac_s04.searchResult.subIdAndDesc"/></td>
				<td class="header" width="9%"><bean:message key="screen.b_bac_s04.searchResult.paymentMethod"/></td>
				<td class="header" width="7%"><bean:message key="screen.b_bac_s04.searchResult.billingCurrency"/></td>
				<td class="header" width="7%"><bean:message key="screen.b_bac_s04.searchResult.paymentCurrency"/></td>
				<td class="header" width="11%" style="text-align:right">
				<c:if test="${_b_bacForm.map.fixedForex eq '1'}"><bean:message key="screen.b_bac_s04.searchResult.ff"/></c:if>
				    <bean:message key="screen.b_bac_s04.searchResult.amount"/>
				</td>
				<td class="header" width="10%"><bean:message key="screen.b_bac_s04.searchResult.referAndStart"/></td>
				<td class="header" width="10%"><bean:message key="screen.b_bac_s04.searchResult.end"/></td>
				<td class="header" width="7%"><bean:message key="screen.b_bac_s04.searchResult.status"/></td>
			</tr>
			
			<c:forEach items="${_b_bacForm.map.listBillingAccount}" var="item" varStatus="status">
				<tr>
				    <td class="bottomLine" align="left">
                         <c:if test="${(item.PLAN_STATUS == 'PS3') or (item.PLAN_STATUS == 'PS7') }">
                             <html:multibox name="_b_bacForm" property="idKeys" value="${item.ID_CUST_PLAN}_${item.ID_BILL_ACCOUNT}"></html:multibox>
                         </c:if>
                    </td>
                    <td class="textBottomLine" align="left">
                        ${item.COUNT}   
                    </td>
                    <td class="textBottomLine" align="left">
                        <c:if test="${not empty item.ID_BILL_ACCOUNT}">
                           ${item.ID_BILL_ACCOUNT}
                        </c:if>
                    </td>
                    <td class="textBottomLine" align="left">
                       <logic:notEmpty name="item" property="ID_SUB_INFO">
	                        <a href="#" onclick="openB_CPM_View('${pageContext.request.contextPath}/B_CPM/B_CPM_S05InitBL.do?customerPlan.idCustPlan=${item.ID_CUST_PLAN}&customerPlan.fromScreen=Billing&customerPlan.billType=BAC')">
	                            ${item.ID_SUB_INFO}
	                        </a>
                        </logic:notEmpty>
                    </td>
                    <td class="textBottomLine" align="left">
                        <t:defineCodeList id="LIST_PAYMENT_METHOD" />
                        <t:writeCodeValue codeList="LIST_PAYMENT_METHOD" key="${item.PAYMENT_METHOD}"></t:writeCodeValue>
                    </td>
                    <td class="textBottomLine" align="left">
                        ${item.BILL_CURRENCY}
                    </td>
                    <td class="textBottomLine" align="left">
                        ${item.EXPORT_CURRENCY}
                    </td>
                    <td class="textBottomLine" align="right" style="padding-right:5px">
                        <c:if test="${_b_bacForm.map.fixedForex eq '1'}">
                         ${item.FIXED_FOREX}
                        </c:if>
                    </td>
                    <td class="textBottomLine" align="left">
                        <c:choose>
                            <c:when test="${empty item.ID_BIF}">
                                <bean:message key="screen.b_bac._"/>
                            </c:when> 
                            <c:otherwise>
                                ${item.ID_BIF}
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td class="textBottomLine" align="left">&nbsp;</td>
                    <td class="textBottomLine" align="left">
                         <c:choose>
                             <c:when test="${item.PLAN_STATUS eq 'PS1'}">
                                 <bean:message key="screen.b_bac.draft"/>
                             </c:when>
                             <c:when test="${item.PLAN_STATUS eq 'PS2'}">
                                 <bean:message key="screen.b_bac.approval"/>
                             </c:when>
                             <c:when test="${item.PLAN_STATUS eq 'PS3'}">
                                 <bean:message key="screen.b_bac.active"/>
                             </c:when>
                             <c:when test="${item.PLAN_STATUS eq 'PS4'}">
                                 <bean:message key="screen.b_bac.inactive"/>
                             </c:when>
                             <c:when test="${item.PLAN_STATUS eq 'PS5'}">
                                 <bean:message key="screen.b_bac.oneTime"/>
                             </c:when>
                             <c:when test="${item.PLAN_STATUS eq 'PS6'}">
                                 <bean:message key="screen.b_bac.suspended"/>
                             </c:when>
                             <c:when test="${item.PLAN_STATUS eq 'PS7'}">
                                 <bean:message key="screen.b_bac.terminated"/>
                             </c:when>
                             <c:when test="${item.PLAN_STATUS eq 'PS8'}">
                                 <bean:message key="screen.b_bac.rejected"/>
                             </c:when>
                             <c:otherwise>
                             </c:otherwise>
                         </c:choose>
                     </td>
				</tr>
				<logic:notEmpty name="item" property="CUST_PLAN_D_INFO">
				<logic:iterate id="planDetail" name="item" property="CUST_PLAN_D_INFO">
					<tr>
					    <td class="textLineNo" align="left">&nbsp;</td>
					    <td class="textLineNo" align="left">&nbsp;</td>
					    <td class="textLineNo" align="left">&nbsp;</td>
						<td class="textLineNo" align="left" style="word-wrap: break-word;white-space : normal" colspan="4">
		                    ${fn:substring(planDetail.BILL_DESC, 0, _b_bacForm.map.bss.descLength)}
		                    <c:if test="${fn:length(planDetail.BILL_DESC) gt _b_bacForm.map.bss.descLength}">
		                        <bean:message key="screen.b_bac.etc"/>
		                    </c:if>
		                </td>
		                <td class="textLineNo" align="right" style="padding-right:5px">
                            <fmt:formatNumber value="${planDetail.TOTAL_AMOUNT}" pattern="#,##0.00"></fmt:formatNumber>
                        </td>
		                <td class="textLineNo" align="left">
		                    <fmt:formatDate value="${planDetail.SVC_START}" pattern="dd/MM/yyyy"/>
		                </td>
		                <td class="textLineNo" align="left">
		                   <c:choose>
		                        <c:when test="${(empty planDetail.SVC_END) and (planDetail.AUTO_RENEW eq 0)}">
		                            <bean:message key="screen.b_bac._"/>
		                        </c:when>
		                        <c:when test="${(empty planDetail.SVC_END) and (planDetail.AUTO_RENEW eq 1)}">
		                            <bean:message key="screen.b_bac.autoRenew"/>
		                        </c:when>
		                        <c:otherwise>
		                            <fmt:formatDate value="${planDetail.SVC_END}" pattern="dd/MM/yyyy"/>
		                        </c:otherwise>
		                    </c:choose>
		                </td>
		                <td class="textLineNo" align="left">&nbsp;</td>
					</tr>
				</logic:iterate>
				</logic:notEmpty>
			</c:forEach>
		</table>
		</div>
		<html:hidden property="newFlg" value="${_b_bacForm.map.newFlg}"/>
		<div class="message">
		    <logic:equal name="_b_bacForm" property="noRecord" value="Y">
		      <bean:message key="info.ERR2SC002"/>
		    </logic:equal>
			<ts:messages id="message" message="true"><bean:write name="message"/></ts:messages>
		</div>
		<div class="error">
			<html:messages id="message">
				<bean:write name="message"/><br/>
			</html:messages>
		</div>
	</ts:form>
</body>
</html>