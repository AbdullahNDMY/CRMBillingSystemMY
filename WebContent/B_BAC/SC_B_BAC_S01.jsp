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
	<link href="${pageContext.request.contextPath}/B_BAC/css/b_bac_s01.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
 	<title><bean:message key="screen.b_bac.title"/></title>
 	<script type="text/javascript">
        function searchClick() {
			var startIndex = document.getElementById("startIndex");
			if(startIndex!=null && startIndex!=undefined) {
				startIndex.value="0";
			}
			showLoadingTipWindow();
		}
 	</script>
</head>
<body id="b" onload="initMain()">
<div style="width:1400px;margin:0">
<ts:form action="/SC_B_BAC_S01DSP" >
    <html:hidden property="contextpath" value="<%=request.getContextPath()%>" />
    <t:defineCodeList id="LIST_PAYMENT_METHOD" />
    <t:defineCodeList id="LIST_CURRENCY"/>
    <t:defineCodeList id="LIST_CUSTOMERTYPE" />
	<h1 class="title"><bean:message key="screen.b_bac.title"/></h1>
	
	<div class="section searchCriteriaDiv">
		<table cellpadding="0" cellspacing="1">
			<colgroup>
			     <col width="150px">
			     <col width="10px">
			     <col width="260px">
			     <col width="130px">
                 <col width="10px">
                 <col width="260px">
			</colgroup>
			<tr>
				<td align="right">
					<bean:message key="screen.b_bac_s01.customerId"/>
				</td>
				<td>
			        <bean:message key="screen.b_bac.colon"/>
			    </td>
			    <td>    
			        <html:text property="tbxCustomerId" name="_b_bac_s01Form" styleClass="inputtext"/>
				</td>
				<td align="right">
				   <bean:message key="screen.b_bac_s01.paymentMethod"/>
				</td>
				<td>
	                 <bean:message key="screen.b_bac.colon"/>
	             </td>
	             <td>
	                <html:select  property="cboPaymentMethod" name="_b_bac_s01Form" styleClass="inputtext">
	                   <option value=""><bean:message key="screen.b_bac.blankItem" /></option>
	                   <html:options collection="LIST_PAYMENT_METHOD" property="id" labelProperty="name"/>
	                </html:select>
	             </td>
			</tr>
			
			<tr>
			    <td align="right">
			        <bean:message key="screen.b_bac_s01.customerName"/>
			    </td>
			    <td>
			       <bean:message key="screen.b_bac.colon"/>
			    </td>
			    <td>    
			        <html:text property="tbxCustomerName" name="_b_bac_s01Form" styleClass="inputtext"/>
			    </td>
			    <td align="right">
			       <bean:message key="screen.b_bac_s01.billingCurrency"/>
			    </td>
			    <td>
			        <bean:message key="screen.b_bac.colon"/>
			    </td>
			    <td>
			        <html:select  property="cboBillingCurrency" name="_b_bac_s01Form" styleClass="inputtext">
                       <option value=""><bean:message key="screen.b_bac.blankItem" /></option>
                       <html:options collection="LIST_CURRENCY" property="id" labelProperty="name"/>
                    </html:select>
			    </td>
			</tr>
			<!-- #263 [T11] Add customer type at inquiry screen and export result CT 06062017 ST-->
			<tr>
				<td align="right"><bean:message
                    key="screen.b_bac_s01.customerType" />
                </td>
                <td>
                <bean:message
                    key="screen.b_bac.colon" />
            	</td>
	            <td align="right"><html:select
	                    property="cboCustomerType"
	                    name="_b_bac_s01Form" styleClass="inputtext">
	                    <option value="">
	                        <bean:message key="screen.b_bac.blankItem" />
	                    </option>
	                    <%--<html:optionsCollection name="LIST_CUSTOMERTYPE"  label="name" value="id"/>--%>
	                    <c:forEach items="${LIST_CUSTOMERTYPE}" var="item">
	                        <c:if test="${item.id ne 'BOTH'}">
	                            <html:option value="${item.id}">${item.name}</html:option>
	                        </c:if>
	                    </c:forEach>
	                </html:select></td>
                <!-- #263 [T11] Add customer type at inquiry screen and export result CT 06062017 EN-->
			    <td align="right">
			       <bean:message key="screen.b_bac_s01.paymentCurrency"/>
			    </td>
			    <td>
			        <bean:message key="screen.b_bac.colon"/>
			    </td>
			    <td>
                    <html:select  property="cboPaymentCurrency" name="_b_bac_s01Form" styleClass="inputtext">
                       <option value=""><bean:message key="screen.b_bac.blankItem" /></option>
                       <bean:define id="GRAND_TOTAL" property="cboPaymentCurrencyList" name="_b_bac_s01Form"/>
                       <html:options collection="GRAND_TOTAL" property="value" labelProperty="label"/>
                    </html:select>
			    </td>
			</tr>
			
			<tr>
				<td align="right">
			        <bean:message key="screen.b_bac_s01.billingAccNo"/>
			    </td>
			    <td>
			       <bean:message key="screen.b_bac.colon"/>
			    </td>
			    <td>    
			        <html:text property="tbxBillingAccountNo" name="_b_bac_s01Form" maxlength="20" styleClass="inputtext"/>
			    </td>
			    
			    <td align="right">
			       <bean:message key="screen.b_bac_s01.status"/>
			    </td>
			    <td>
			        <bean:message key="screen.b_bac.colon"/>
			    </td>
			    <td>
			        <html:multibox name="_b_bac_s01Form" property="chkStatus" value="N"/>
                    <bean:message key="screen.b_bac_s01.status.new"/>
                    <html:multibox name="_b_bac_s01Form" property="chkStatus" value="A"/>
                    <bean:message key="screen.b_bac_s01.status.active"/>
                    <html:multibox name="_b_bac_s01Form" property="chkStatus" value="B"/>
                    <bean:message key="screen.b_bac_s01.status.billFinished"/>
			    </td>
			</tr>
			
			<tr>
			    <td align="right">
			        <bean:message key="screen.b_bac_s01.documentDelieverd"/>
			    </td>
			    <td>
			       <bean:message key="screen.b_bac.colon"/>
			    </td>
			    <td rowspan ="2">
			    	<!-- #191 Start -->    
			      	<%-- <bean:message key="screen.b_bac_s01.bySingPost"/> --%>
			      	${_b_bac_s01Form.map.singPostValue}
			      	&nbsp;&nbsp;
			      	<!-- #191 End -->
			        <html:multibox name="_b_bac_s01Form" property="chkBySingPost" value="1">
			        </html:multibox>
			        <bean:message key="screen.b_bac_s01.bySingPost.yes"/>
			        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			        <html:multibox name="_b_bac_s01Form" property="chkBySingPost" value="0">
                    </html:multibox>
                    <bean:message key="screen.b_bac_s01.bySingPost.no"/><br/>
                    <!-- Email -->
                    <bean:message key="screen.b_bac_s01.email"/>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <html:multibox name="_b_bac_s01Form" property="chkEmail" value="1">
			        </html:multibox>
			        <bean:message key="screen.b_bac_s01.bySingPost.yes"/>
			        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			        <html:multibox name="_b_bac_s01Form" property="chkEmail" value="0">
                    </html:multibox>
                    <bean:message key="screen.b_bac_s01.bySingPost.no"/><br/>
                    <!-- Post,Courier,By Hand -->
                    <html:multibox name="_b_bac_s01Form" property="chkDeliveries" value="1">
			        </html:multibox>
			        <bean:message key="screen.b_bac_s01.post"/>
			        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			        <html:multibox name="_b_bac_s01Form" property="chkDeliveries" value="2">
			        </html:multibox>
			        <bean:message key="screen.b_bac_s01.courier"/>
			        <html:multibox name="_b_bac_s01Form" property="chkDeliveries" value="4">
			        </html:multibox>
			        <bean:message key="screen.b_bac_s01.byhand"/>
			    </td>
			    <%-- <td>
			        &nbsp;
			    </td> --%>
			    <td align="right">
			       <bean:message key="screen.b_bac_s01.totalAmountDue"/>
			    </td>
			    <td>
			        <bean:message key="screen.b_bac.colon"/>
			    </td>
			    <td>
			        <html:multibox name="_b_bac_s01Form" property="chkTotalAmountDue" value="A">
                    </html:multibox>=0
                    <html:multibox name="_b_bac_s01Form" property="chkTotalAmountDue" value="B">
                    </html:multibox>&lt;&gt;0
                    <html:multibox name="_b_bac_s01Form" property="chkTotalAmountDue" value="C">
                    </html:multibox>&lt;0
                    <html:multibox name="_b_bac_s01Form" property="chkTotalAmountDue" value="D">
                    </html:multibox>&gt;0
			    </td>
			</tr>
		</table>
	</div>

	<div class="buttonAera">
		<html:submit property="forward_search" onclick="searchClick()"><bean:message key='screen.b_bac.search'/></html:submit>
		<bs:buttonLink action="/RP_B_BAC_S01_01BL" value="Reset"/>
		<c:if test="${_b_bac_s01Form.map.accessType eq '2'}">
			<html:submit property="forward_reassign"><bean:message key="screen.b_bac.button.reassign"/></html:submit>
		</c:if>
		<c:if test="${_b_bac_s01Form.map.totalRow == 0 or _b_bac_s01Form.map.totalRow == null}">
		  <html:submit property="forward_exportResult" disabled="true"><bean:message key="screen.b_bac.button.exportResult"/></html:submit>
		</c:if>
		<c:if test="${_b_bac_s01Form.map.totalRow > 0}">
          <html:submit property="forward_exportResult" onclick="showLoadingTipWindow();"><bean:message key="screen.b_bac.button.exportResult"/></html:submit>
        </c:if>
        <c:choose>
             <c:when test="${_b_bac_s01Form.map.cbAutoOffset == 'BAC'}">
                 <html:submit property="forward_outstandingReport" onclick="showLoadingTipWindow();"><bean:message key="screen.b_bac.button.outstandingReport"/></html:submit>
             </c:when>
             <c:otherwise>
                <html:submit disabled="true"><bean:message key="screen.b_bac.button.outstandingReport"/></html:submit>
             </c:otherwise>
        </c:choose>
	</div>

	<div class="section">
		<div class="group result">
			<h2><bean:message key="screen.b_bac.searchFound" /> ${_b_bac_s01Form.map.totalRow}</h2>
		</div>
		<div class="pageLink">
			<bean:message key="screen.b_bac.gotoPage"/> <bean:message key="screen.b_bac.colon"/>
			<ts:pageLinks 
    			id="curPageLinks"
				action="${pageContext.request.contextPath}/B_BAC/RP_B_BAC_S01_02BL.do" 
				name="_b_bac_s01Form" 
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
	<table  class="resultInfo" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td class="header" width="2%"><bean:message key="screen.b_bac_s01.no"/></td>
			<td class="header" width="8%"><bean:message key="screen.b_bac_s01.customerId"/></td>
			<td class="header" width="15%"><bean:message key="screen.b_bac_s01.customerName"/></td>
			<td class="header" width="7%"><bean:message key="screen.b_bac_s01.billingAccNo"/></td>
			<td class="header" width="4%"><%-- <bean:message key="screen.b_bac_s01.bySingPost"/> --%>${_b_bac_s01Form.map.singPostValue}</td>
			<td class="header" width="4%"><bean:message key="screen.b_bac_s01.byEmail"/></td>
			<td class="header" width="7%"><bean:message key="screen.b_bac_s01.paymentMethod"/></td>
			<td class="header" width="5%"><bean:message key="screen.b_bac_s01.billingCurrency"/></td>
			<td class="header" width="5%"><bean:message key="screen.b_bac_s01.paymentCurrency"/></td>
			<td class="header" width="8%" style="text-align:right"><bean:message key="screen.b_bac_s01.title.totalAmountDue"/></td>
			<td class="header" width="5%"><bean:message key="screen.b_bac_s01.status"/></td>
			<td class="header" width="14%"><bean:message key="screen.b_bac_s01.attentionEmail"/></td>
			<td class="header" width="16%"><bean:message key="screen.b_bac_s01.billingAddress"/></td>
		</tr>
		
		<c:forEach items="${_b_bac_s01Form.map.searchResultList}" var="item" varStatus="status">
			<tr>
			   <td class="resultInfoTd">
			      ${item.ITEM_NO}
			   </td>
			   <td class="resultInfoTd">
                  ${item.ID_CUST}
               </td>
			   <td class="resultInfoTd">
                  ${item.CUST_NAME}
               </td>
			   <td class="resultInfoTd">
                  <c:if test="${not empty item.ID_BILL_ACCOUNT}">
                      <html:link action="/RP_B_BAC_S02_02_01BL.do?idBillAccount=${item.ID_BILL_ACCOUNT}&idCustPlan=${item.ID_CUST_PLAN}">
                      ${item.ID_BILL_ACCOUNT}
                      </html:link>
                  </c:if>
               </td>
			   <td class="resultInfoTd">
                  <c:if test="${(item.IS_SINGPOST == '1')}">
                    <bean:message key="screen.b_bac_s01.bySingPost.yes"/>
                  </c:if>
                  <c:if test="${(item.IS_SINGPOST == '0')}">
                    <bean:message key="screen.b_bac_s01.bySingPost.no"/>
                  </c:if>
               </td>
               <td class="resultInfoTd">
               	  <c:if test="${(item.DELIVERY_EMAIL == '1')}">
                    <bean:message key="screen.b_bac_s01.byEmail.yes"/>
                  </c:if>
                  <c:if test="${(item.DELIVERY_EMAIL == '0')}">
                    <bean:message key="screen.b_bac_s01.byEmail.no"/>
                  </c:if>
               </td>
			   <td class="resultInfoTd">
                  <t:writeCodeValue codeList="LIST_PAYMENT_METHOD" key="${item.PAYMENT_METHOD}"></t:writeCodeValue>
               </td>
               <td class="resultInfoTd">
                  ${item.BILL_CURRENCY}
               </td>
               <td class="resultInfoTd">
                  ${item.EXPORT_CURRENCY}
               </td>
               <td class="resultInfoTd" align="right">
                 <fmt:formatNumber value="${item.TOTAL_AMT_DUE}" pattern="#,##0.00"></fmt:formatNumber>
               </td>
               <td class="resultInfoTd">
                  <c:choose>
                    <c:when test="${item.D_COUNT eq '0'}">
                        <bean:message key="screen.b_bac_s01.status.new"/>
                    </c:when>
                    <c:otherwise>
                            <c:choose>
	                            <c:when test="${item.P_COUNT eq '0'}">
	                               <bean:message key="screen.b_bac_s01.status.billFinished"/>
	                            </c:when>
	                            <c:otherwise>
	                               <bean:message key="screen.b_bac_s01.status.active"/>
	                            </c:otherwise>
                            </c:choose>
                    </c:otherwise>
                  </c:choose>
               </td>
               <td class="resultInfoTd">
                  ${item.attention}<br/>
					<c:forTokens items="${item.email}" delims=";" var="email" varStatus="vs">
						<c:if test="${!vs.last}">
							<c:out value="${email}"/>;<br/>
						</c:if>
						<c:if test="${vs.last}">
							<c:out value="${email}"/>
						</c:if>
					</c:forTokens>
               </td>
               <td class="resultInfoTd">
                  ${item.address1}
                  <c:if test="${not empty item.address1 and not empty item.address2}">
                  ,
                  </c:if>
                  ${item.address2}<br/>
                  ${item.address3}
                  <c:if test="${not empty item.address3 and not empty item.address4}">
                  ,
                  </c:if>
                  ${item.address4}
               </td>
		  </tr>
		</c:forEach>
	</table>
	</div>
	<div class="message">
		<ts:messages id="message" message="true"><bean:write name="message"/></ts:messages>
	</div>
	<div class="error">
		<html:messages id="message">
			<bean:write name="message"/><br/>
		</html:messages>
	</div>
</ts:form>
</div>
</body>
</html>