<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-html" prefix="html"%>
<%@ taglib uri="/struts-bean" prefix="bean"%>
<%@ taglib uri="/struts-logic" prefix="logic"%>
<%@ taglib uri="/terasoluna-struts" prefix="ts"%>
<%@ taglib uri="/terasoluna" prefix="t"%>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="nttdm.bsys.common.fw.BillingSystemUserValueObject"%>
<%@page import="nttdm.bsys.common.util.CommonUtils"%>
<%@ taglib uri="/WEB-INF/bs" prefix="bs"%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<link type="text/css" rel="stylesheet"
    href="<%=request.getContextPath()%>/stylesheet/common.css" />
<link rel="stylesheet" type="text/css"
    href="<%=request.getContextPath()%>/stylesheet/tabcontent.css" />
<link rel="stylesheet" type="text/css" href="css/b_cpm_s01.css" />
<link rel="stylesheet" type="text/css" href="css/b_cpm.css" />
<script type="text/javascript"
    src="<%=request.getContextPath()%>/javascript/common.js"></script>
<script type="text/javascript"
    src="<%=request.getContextPath()%>/javascript/tabcontent.js"></script>
<script type="text/javascript"
    src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
<script type="text/javascript"
    src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
<script type="text/javascript" src="js/b_cpm_common.js"></script>
<script type="text/javascript" src="js/b_cpm_s01.js"></script>
<title></title>
	<style>
		.ui-autocomplete {
    		overflow-y: auto;
    		overflow-x: hidden;
    		font-family: Calibri;
    		font-size: 1.1em;
  		}		
	</style>
</head>
<body id="b" onload="initMain();">
    <!-- check access right START -->
    <%
	 	BillingSystemUserValueObject uvo = (BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT");
	 	String accessRight = CommonUtils.getAccessRight(uvo, "B-CPM");
	 	String accessRightB_SSMBean = CommonUtils.getAccessRight(uvo, "B-SSM");
	 %>
    <bean:define id="accessRightBean" value="<%=accessRight %>" />
    <bean:define id="accessRightB_SSM"
        value="<%= accessRightB_SSMBean%>" />
    <!-- check access right END -->
    <table class="subHeader" cellpadding="0" cellspacing="0">
        <tr>
            <td><bean:message key="screen.b_cpm.header" />
            </td>
        </tr>
    </table>
    <ts:form styleId="frmS01" action="/B_CPM_S01DSP" method="POST">
        <t:defineCodeList id="LIST_PLANSTATUS" />
        <t:defineCodeList id="LIST_PAYMENT_METHOD" />
        <t:defineCodeList id="BILL_INSTRUCTION_LIST" />
        <t:defineCodeList id="LIST_TRANSACTIONTYPE" />
        <t:defineCodeList id="LIST_CURRENCY" />
        <t:defineCodeList id="LIST_CUSTOMERTYPE" />
        <t:defineCodeList id="LIST_RATEMODE" />
        <!-- #186 Start -->
        <t:defineCodeList id="LIST_EXPORT_LIMIT" />
        <input id="exportWarnMsg" type="hidden" value="<bean:message key='errors.ERR1SC105' arg0='0'/>"/>
        <!-- #186 End -->
        <input type="hidden" id="hiddenContextPath"
            value="${pageContext.request.contextPath}" />
        <input type="hidden" id="hiddenAccessType" value="${accessType}" />
        <html:hidden property="action" />
        <nested:nest property="customerPlan">
            <nested:hidden property="idCust" styleId="idCust" />
            <nested:hidden property="idCustPlan" styleId="idCustPlan" />
            <nested:hidden property="billAccNo" styleId="billAccNo" />
        </nested:nest>
        <bean:define id="LOAD_OBJECT" name="LOAD_OBJECT" scope="request"></bean:define>
        <div id="svcLevelGroup" style="display: none">
            <select class="svcLevel1">
                <option value="">
                    <bean:message key="screen.b_cpm.listBox.default" />
                </option>
                <logic:iterate id="SVC_LEVEL1" name="LOAD_OBJECT"
                    property="CATEGORY">
                    <option
                        value="<bean:write name="SVC_LEVEL1" property="SVC_GRP"/>">
                        <bean:write name="SVC_LEVEL1"
                            property="SVC_GRP_NAME" />
                    </option>
                </logic:iterate>
            </select> <select class="svcLevel2">
                <option value="">
                    <bean:message key="screen.b_cpm.listBox.default" />
                </option>
                <logic:iterate id="SVC_LEVEL2" name="LOAD_OBJECT"
                    property="SERVICE">
                    <option
                        value="<bean:write name="SVC_LEVEL2" property="ID_SERVICE"/>"
                        gstValue="<bean:write name="SVC_LEVEL2" property="GST"/>"
                        svcGrp="<bean:write name="SVC_LEVEL2" property="SVC_GRP"/>">
                        <bean:write name="SVC_LEVEL2"
                            property="SVC_NAME" />
                    </option>
                </logic:iterate>
            </select> <select class="svcLevel3">
                <option value="">
                    <bean:message key="screen.b_cpm.listBox.default" />
                </option>
                <logic:iterate id="SVC_LEVEL3" name="LOAD_OBJECT"
                    property="PLAN">
                    <option
                        value="<bean:write name="SVC_LEVEL3" property="ID_SERVICE"/>"
                        gstValue="<bean:write name="SVC_LEVEL3" property="GST"/>"
                        svcGrp="<bean:write name="SVC_LEVEL3" property="SVC_GRP"/>">
                        <bean:write name="SVC_LEVEL3"
                            property="SVC_NAME" />
                    </option>
                </logic:iterate>
            </select><br /> <select class="svcLevel4">
                <option value="">
                    <bean:message key="screen.b_cpm.listBox.default" />
                </option>
                <logic:iterate id="SVC_LEVEL4" name="LOAD_OBJECT"
                    property="PLAN_DETAIL">
                    <option
                        value="<bean:write name="SVC_LEVEL4" property="ID_SERVICE"/>"
                        gstValue="<bean:write name="SVC_LEVEL4" property="GST"/>"
                        svcGrp="<bean:write name="SVC_LEVEL4" property="SVC_GRP"/>">
                        <bean:write name="SVC_LEVEL4"
                            property="SVC_NAME" />
                    </option>
                </logic:iterate>
            </select>
        </div>
        <div class="wrapper">
            <bean:define id="searchPlanPage" name="_B_CPM_S01SearchForm"
                property="inputSearchPlan" />
            <nested:nest property="inputSearchPlan">
                <nested:hidden property="service" styleId="hiddenService"/>
				<nested:hidden property="plan" styleId="hiddenPlan"/>
				<nested:hidden property="planDetail" styleId="hiddenPlanDetail"/>
                <table width="100%" cellpadding="0" cellspacing="0">
                    <colgroup>
                        <col width="40%">
                        <col width="60%">
                    </colgroup>
                    <tr>
                        <!-- customer group -->
                        <td valign="top">
                            <fieldset>
                                <legend>
                                    <bean:message
                                        key="screen.b_cpm.group.customer" />
                                </legend>
                                <table width="100%">
                                    <colgroup>
                                        <col width="30%">
                                        <col width="40%">
                                        <col width="30%">
                                    </colgroup>
                                    <tr>
                                        <td class="label"><bean:message
                                                key="screen.b_cpm.label.custId" />
                                            <bean:message
                                                key="screen.b_cpm.label_colon" />
                                        </td>
                                        <td class="content" colspan="2"><nested:text
                                                property="searchCustomerId"
                                                styleClass="searchCustomerId"
                                                style="width: 100%" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="label"><bean:message
                                                key="screen.b_cpm.label.custName" />
                                            <bean:message
                                                key="screen.b_cpm.label_colon" />
                                        </td>
                                        <td class="content" colspan="2">
                                            <nested:text
                                                property="searchCustomerName"
                                                styleClass="searchCustomerName"
                                                style="width: 100%" /></td>
                                    </tr>
                                    <tr>
                                        <td class="label"><bean:message
                                                key="screen.b_cpm.label.custType" />
                                            <bean:message
                                                key="screen.b_cpm.label_colon" />
                                        </td>
                                        <td class="content"><nested:select
                                                property="customerType"
                                                styleClass="searchCustomerType"
                                                style="width: 100%;">
                                                <option value="">
                                                    <bean:message
                                                        key="screen.b_cpm.listBox.default" />
                                                </option>
                                                <%--
					    					<html:optionsCollection name="LIST_CUSTOMERTYPE"  label="name" value="id"/>
										 --%>
                                                <c:forEach
                                                    items="${LIST_CUSTOMERTYPE}"
                                                    var="item">
                                                    <c:if
                                                        test="${item.id ne 'BOTH'}">
                                                        <html:option
                                                            value="${item.id}">${item.name}</html:option>
                                                    </c:if>
                                                </c:forEach>
                                            </nested:select></td>
                                        <td style="text-align: right">
                                            <%-- 
								<logic:equal value="2" name="accessRightBean">
									<a href="javascript: gotoScreen('newCust');"><bean:message key="screen.b_cpm.link.newCust"/></a>
								</logic:equal>
								 --%></td>
                                    </tr>
                                </table>
                            </fieldset></td>
                        <!-- service group -->
                        <td valign="top">
	                        <!-- #180 Start -->
	                        <jsp:include page="/COMMON/SearchableSelect.jsp" flush="true">
								 <jsp:param value="inputSearchPlan" name="serviceGroup"/>
							</jsp:include>
							<!-- #180 End -->
                            <fieldset>
                                <legend>
                                    <bean:message
                                        key="screen.b_cpm.group.servcie" />
                                </legend>
                                <table width="100%">
                                    <colgroup>
                                        <col width="22%">
                                        <col width="78%">
                                    </colgroup>
                                    <tr>
                                        <td class="label"><bean:message
                                                key="screen.b_cpm.label.category" />
                                            <bean:message
                                                key="screen.b_cpm.label_colon" />
                                        </td>
                                        <td class="content"><nested:select
                                                property="category"
                                                styleClass="searchCategory"
                                                style="width: 100%"
                                                onchange="javascript: changeCategory();">
                                                <option value="">
                                                	- Please Select One - 
                                                    <%-- <bean:message
                                                        key="screen.b_cpm.listBox.default" /> --%>
                                                </option>
                                                <html:optionsCollection
                                                    name="LOAD_OBJECT"
                                                    property="CATEGORY"
                                                    label="SVC_GRP_NAME"
                                                    value="SVC_GRP" />
                                            </nested:select></td>
                                    </tr>
                                    <tr>
                                        <td class="label"><bean:message
                                                key="screen.b_cpm.label.service" />
                                            <bean:message
                                                key="screen.b_cpm.label_colon" />
                                        </td>
                                        <td class="content"><nested:select
                                                property="service"
                                                styleClass="searchService"
                                                style="width: 100%">
                                                <option value="">
                                                    <%-- <bean:message
                                                        key="screen.b_cpm.listBox.default" /> --%>
                                                </option>
                                                <html:optionsCollection
                                                    name="LOAD_OBJECT"
                                                    property="SERVICE"
                                                    label="SVC_NAME"
                                                    value="ID_SERVICE" />
                                            </nested:select></td>
                                    </tr>
                                    <tr>
                                        <td class="label"><bean:message
                                                key="screen.b_cpm.label.plan" />
                                            <bean:message
                                                key="screen.b_cpm.label_colon" />
                                        </td>
                                        <td class="content"><nested:select
                                                property="plan"
                                                styleClass="searchPlan"
                                                style="width: 100%">
                                                <option value="">
                                                    <%-- <bean:message
                                                        key="screen.b_cpm.listBox.default" /> --%>
                                                </option>
                                                <html:optionsCollection
                                                    name="LOAD_OBJECT"
                                                    property="PLAN"
                                                    label="SVC_NAME"
                                                    value="ID_SERVICE" />
                                            </nested:select></td>
                                    </tr>
                                    <tr>
                                        <td class="label"><bean:message
                                                key="screen.b_cpm.label.planDetail" />
                                            <bean:message
                                                key="screen.b_cpm.label_colon" />
                                        </td>
                                        <td class="content"><nested:select
                                                property="planDetail"
                                                styleClass="searchPlanDetail"
                                                style="width: 100%">
                                                <option value="">
                                                    <%-- <bean:message
                                                        key="screen.b_cpm.listBox.default" /> --%>
                                                </option>
                                                <html:optionsCollection
                                                    name="LOAD_OBJECT"
                                                    property="PLAN_DETAIL"
                                                    label="SVC_NAME"
                                                    value="ID_SERVICE" />
                                            </nested:select></td>
                                    </tr>
                                </table>
                            </fieldset></td>
                    </tr>
                    <!-- customer plan group -->
                    <tr>
                        <td colspan="2">
                            <fieldset>
                                <legend>
                                    <bean:message
                                        key="screen.b_cpm.group.customerplan" />
                                </legend>
                                <table width="100%">
                                    <colgroup>
                                        <col width="46%">
                                        <col width="54%">
                                    </colgroup>
                                    <tr>
                                        <td valign="top">
                                            <!-- table left -->
                                            <table width="100%">
                                                <colgroup>
                                                    <col width="30%">
                                                    <col width="70%">
                                                </colgroup>
                                                <tr>
                                                    <td class="label">
                                                        <bean:message
                                                            key="screen.b_cpm.label.subscriptionId" />
                                                        <bean:message
                                                            key="screen.b_cpm.label_colon" />
                                                    </td>
                                                    <td class="content"><nested:text
                                                            property="subscriptionId"
                                                            styleClass="searchSubscriptionId"
                                                            style="width: 100%" />
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="label">
                                                        <bean:message
                                                            key="screen.b_cpm.label.billingAccount" />
                                                        <bean:message
                                                            key="screen.b_cpm.label_colon" />
                                                    </td>
                                                    <td class="content"><nested:text
                                                            property="billingAccount"
                                                            styleClass="searchBillingAccount"
                                                            style="width: 100%" />
                                                    </td>
                                                </tr>
                                                <logic:equal value="0" name="LOAD_OBJECT" property="jobNoDisplayCount">
                                                <tr>
                                                    <td class="label">
                                                        <bean:message
                                                            key="screen.b_cpm.label.jobNo" />
                                                        <bean:message
                                                            key="screen.b_cpm.label_colon" />
                                                    </td>
                                                    <td class="content"><nested:text
                                                            property="jobNo"
                                                            styleClass="jobNo"
                                                            style="width: 100%" />
                                                    </td>
                                                </tr>
                                                </logic:equal>
                                                <!-- #185 Start -->
                                                <tr>
                                                	<td class="label">
                                                		Bill Description / Memo
                                                		<bean:message key="screen.b_cpm.label_colon" />
                                                	</td>
                                                	<td class="content">
	                                                	<nested:text property="billingDesc"
	                                                            styleClass="billingDesc"
	                                                            style="width: 100%" />
                                                	</td>
                                                </tr>
                                                <!-- #185 End -->
                                                <tr>
                                                    <td class="label">
                                                        <bean:message
                                                            key="screen.b_cpm.label.serviceStatus" />
                                                        <bean:message
                                                            key="screen.b_cpm.label_colon" />
                                                    </td>
                                                    <td class="content"
                                                        nowrap="nowrap">
                                                        <nested:iterate
                                                            property="serviceStatus"
                                                            id="hidServStatus">
                                                            <input
                                                                type="hidden"
                                                                name="hidServStatus"
                                                                class="hidServStatus"
                                                                value="<bean:write name="hidServStatus"/>" />
                                                        </nested:iterate> <logic:equal
                                                            value="1"
                                                            name="LOAD_OBJECT"
                                                            property="isBIFUsed">
                                                            <table>
                                                                <tr>
                                                                    <td>
                                                                        <nested:checkbox
                                                                            property="serviceStatus"
                                                                            styleClass="searchServiceStatus"
                                                                            value="PS1">
                                                                            <bean:message
                                                                                key="screen.b_cpm.checkbox.draft" />
                                                                        </nested:checkbox>
                                                                    </td>
                                                                    <td>
                                                                        <nested:checkbox
                                                                            property="serviceStatus"
                                                                            styleClass="searchServiceStatus"
                                                                            value="PS0">
                                                                            <bean:message
                                                                                key="screen.b_cpm.checkbox.cancelled" />
                                                                        </nested:checkbox>
                                                                    </td>
                                                                    <td>
                                                                        <nested:checkbox
                                                                            property="serviceStatus"
                                                                            styleClass="searchServiceStatus"
                                                                            value="PS2">
                                                                            <bean:message
                                                                                key="screen.b_cpm.checkbox.approval" />
                                                                        </nested:checkbox>
                                                                    </td>
                                                                    <%--
																<td nowrap="nowrap">
																	<nested:checkbox property="serviceStatus" styleClass="searchServiceStatus" value="PS5">
																	    <bean:message key="screen.b_cpm.checkbox.oneTime"/>
																	</nested:checkbox>		
																</td>
																--%>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        <nested:checkbox
                                                                            property="serviceStatus"
                                                                            styleClass="searchServiceStatus"
                                                                            value="PS3">
                                                                            <bean:message
                                                                                key="screen.b_cpm.checkbox.active" />
                                                                        </nested:checkbox>
                                                                    </td>
                                                                    <td>
                                                                        <nested:checkbox
                                                                            property="serviceStatus"
                                                                            styleClass="searchServiceStatus"
                                                                            value="PS7">
                                                                            <bean:message
                                                                                key="screen.b_cpm.checkbox.terminated" />
                                                                        </nested:checkbox>
                                                                    </td>
                                                                    <td>
                                                                        <nested:checkbox
                                                                            property="serviceStatus"
                                                                            styleClass="searchServiceStatus"
                                                                            value="PS6">
                                                                            <bean:message
                                                                                key="screen.b_cpm.checkbox.suspended" />
                                                                        </nested:checkbox>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        <nested:checkbox
                                                                            property="serviceStatus"
                                                                            styleClass="searchServiceStatus"
                                                                            value="PS8">
                                                                            <bean:message
                                                                                key="screen.b_cpm.checkbox.rejected" />
                                                                        </nested:checkbox>
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </logic:equal> <logic:notEqual
                                                            value="1"
                                                            name="LOAD_OBJECT"
                                                            property="isBIFUsed">
                                                            <table>
                                                                <tr>
                                                                    <td>
                                                                        <nested:checkbox
                                                                            property="serviceStatus"
                                                                            styleClass="searchServiceStatus"
                                                                            value="PS1">
                                                                            <bean:message
                                                                                key="screen.b_cpm.checkbox.draft" />
                                                                        </nested:checkbox>
                                                                    </td>
                                                                    <td>
                                                                        <nested:checkbox
                                                                            property="serviceStatus"
                                                                            styleClass="searchServiceStatus"
                                                                            value="PS0">
                                                                            <bean:message
                                                                                key="screen.b_cpm.checkbox.cancelled" />
                                                                        </nested:checkbox>
                                                                    </td>
                                                                    <%--
																<td nowrap="nowrap">
																	<nested:checkbox property="serviceStatus" styleClass="searchServiceStatus" value="PS5">
																	    <bean:message key="screen.b_cpm.checkbox.oneTime"/>
																	</nested:checkbox>		
																</td>
																--%>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        <nested:checkbox
                                                                            property="serviceStatus"
                                                                            styleClass="searchServiceStatus"
                                                                            value="PS3">
                                                                            <bean:message
                                                                                key="screen.b_cpm.checkbox.active" />
                                                                        </nested:checkbox>
                                                                    </td>
                                                                    <td>
                                                                        <nested:checkbox
                                                                            property="serviceStatus"
                                                                            styleClass="searchServiceStatus"
                                                                            value="PS7">
                                                                            <bean:message
                                                                                key="screen.b_cpm.checkbox.terminated" />
                                                                        </nested:checkbox>
                                                                    </td>
                                                                    <td>
                                                                        <nested:checkbox
                                                                            property="serviceStatus"
                                                                            styleClass="searchServiceStatus"
                                                                            value="PS6">
                                                                            <bean:message
                                                                                key="screen.b_cpm.checkbox.suspended" />
                                                                        </nested:checkbox>
                                                                    </td>
                                                                </tr>

                                                            </table>
                                                        </logic:notEqual></td>
                                                </tr>
                                                <tr>
                                                	<td class="label">
                                                        <bean:message
                                                            key="screen.b_cpm.label.billingStatus" />
                                                        <bean:message
                                                            key="screen.b_cpm.label_colon" />
                                                    </td>
                                                	<td class="content"
                                                        nowrap="nowrap">
                                                        <nested:iterate
                                                            property="billingStatus"
                                                            id="hidBillStatus">
                                                            <input
                                                                type="hidden"
                                                                name="hidBillStatus"
                                                                class="hidBillStatus"
                                                                value="<bean:write name="hidBillStatus"/>" />
                                                        </nested:iterate>
                                                        <table>
                                                        	<tr>
                                                        		<td>
                                                        			<nested:checkbox
                                                                            property="billingStatus"
                                                                            styleClass="searchBillingStatus"
                                                                            value="BS1">
                                                                            <bean:message
                                                                                key="screen.b_cpm.label.notstart" />
                                                                    </nested:checkbox>
                                                        		</td>
                                                        		<td>
                                                        			<nested:checkbox
                                                                            property="billingStatus"
                                                                            styleClass="searchBillingStatus"
                                                                            value="BS2">
                                                                            <bean:message
                                                                                key="screen.b_cpm.label.inprogress" />
                                                                    </nested:checkbox>
                                                        		</td>
                                                        		<td>
                                                        			<nested:checkbox
                                                                            property="billingStatus"
                                                                            styleClass="searchBillingStatus"
                                                                            value="BS3">
                                                                            <bean:message
                                                                                key="screen.b_cpm.label.completed" />
                                                                    </nested:checkbox>
                                                        		</td>
                                                        	</tr>
                                                        </table>
                                                    </td>
                                                </tr>
                                                <!-- #189 Start -->
                                                <!-- #208 Start -->
                                                <nested:equal value="1" name="LOAD_OBJECT" property="disBillingOption">
                                                <!-- #208 End -->
                                                <tr>
                                                	<td class="label">
                                                        <bean:message
                                                            key="screen.b_cpm.label.billingOption" />
                                                        <bean:message
                                                            key="screen.b_cpm.label_colon" />
                                                    </td>
                                                	<td class="content"
                                                        nowrap="nowrap">
                                                        <nested:iterate
                                                            property="billingOption"
                                                            id="hidBillingOption">
                                                            <input
                                                                type="hidden"
                                                                name="hidBillingOption"
                                                                class="hidBillingOption"
                                                                value="<bean:write name="hidBillingOption"/>" />
                                                        </nested:iterate>
                                                        <table>
                                                        	<tr>
                                                        		<td>
                                                        			<nested:checkbox
                                                                            property="billingOption"
                                                                            styleClass="searchBillingOption"
                                                                            value="1">
                                                                            <bean:message
                                                                                key="screen.b_cpm.label.prepaid" />
                                                                    </nested:checkbox>
                                                        		</td>
                                                        		<td>
                                                        			<nested:checkbox
                                                                            property="billingOption"
                                                                            styleClass="searchBillingOption"
                                                                            value="2">
                                                                            <bean:message
                                                                                key="screen.b_cpm.label.postpaid" />
                                                                    </nested:checkbox>
                                                        		</td>
                                                        	</tr>
                                                        </table>
                                                    </td>
                                                </tr>
                                                </nested:equal>                                                
                                                <!-- #189 End -->
                                            </table></td>
                                        <td>
                                            <!-- tabel right -->
                                            <table width="100%"
                                                class="custPlanSearchCond">
                                                <colgroup>
                                                    <col width="20%">
                                                    <col width="80%">
                                                </colgroup>
                                                <logic:equal
                                                    name="_B_CPM_S01SearchForm"
                                                    property="cpmTransType"
                                                    value="1">
                                                    <tr>
                                                        <td
                                                            class="label">
                                                            <bean:message
                                                                key="screen.b_cpm.label.transType" />
                                                            <bean:message
                                                                key="screen.b_cpm.label_colon" />
                                                        </td>
                                                        <td
                                                            class="content"
                                                            nowrap="nowrap">
                                                            <nested:iterate
                                                                property="transType"
                                                                id="hidTransType">
                                                                <input
                                                                    type="hidden"
                                                                    name="hidTransType"
                                                                    class="hidTransType"
                                                                    value="<bean:write name="hidTransType"/>" />
                                                            </nested:iterate>
                                                            <table style="width:100%">
                                                            <colgroup>
                                                                <col
                                                                    width="22%" />
                                                                <col
                                                                    width="24%" />
                                                                <col
                                                                    width="24%" />
                                                                <col
                                                                    width="30%" />
                                                            </colgroup>
                                                            	<tr>
                                                            		 <logic:iterate
                                                                id="status"
                                                                name="LIST_TRANSACTIONTYPE"
                                                                indexId="i">
                                                                <td nowrap="nowrap">
                                                                <%-- 3 checkboxes per line --%>
                                                                <c:if
                                                                    test="${i%3 == 0}"></c:if>
                                                                <%-- rendering checkbox --%>
                                                                <c:if test="${i ne '3'}">
                                                                <label>
                                                                    <nested:checkbox
                                                                        property="transType"
                                                                        styleClass="searchTransType"
                                                                        value="${status.id}">
                                                                        <bean:write
                                                                            name="status"
                                                                            property="name" />
                                                                    </nested:checkbox> </label>
                                                                    </c:if>
                                                                    <c:if test="${i eq '3'}">
											    		<c:if test="${LOAD_OBJECT.NonTaxInvoiceShowFlg eq '1'}">
											    				<label>
                                                                    <nested:checkbox
                                                                        property="transType"
                                                                        styleClass="searchTransType"
                                                                        value="${status.id}">
                                                                        <bean:write
                                                                            name="status"
                                                                            property="name" />
                                                                    </nested:checkbox> 
                                                            	</label>
											    			</c:if>
											    		</c:if>
											    		</td>
                                                            </logic:iterate>
                                                            	</tr>
                                                            </table>
                                                            </td>
                                                    </tr>
                                                </logic:equal>
                                                <tr>
                                                    <td class="label">
                                                        <bean:message
                                                            key="screen.b_cpm.label.billingInstruction" />
                                                        <bean:message
                                                            key="screen.b_cpm.label_colon" />
                                                    </td>
                                                    <td class="content">
                                                        <%-- <nested:select
                                                            property="billingInstruction"
                                                            styleClass="searchBillingInstruction"
                                                            style="width: 50%;margin-left: 5px">
                                                            <option
                                                                value="">
                                                                <bean:message
                                                                    key="screen.b_cpm.listBox.default" />
                                                            </option>
                                                            <html:optionsCollection
                                                                name="BILL_INSTRUCTION_LIST"
                                                                label="name"
                                                                value="id" />
                                                        </nested:select> --%>
                                                      <nested:iterate
                                                                property="billingInstructions"
                                                                id="hidBillingInstructions">
                                                                <input
                                                                    type="hidden"
                                                                    name="hidBillingInstructions"
                                                                    class="hidBillingInstructions"
                                                                    value="<bean:write name="hidBillingInstructions"/>" />
                                                            </nested:iterate>
                                                             <!-- One Time -->
                                                             <table style="width:100%">
                                                             	<colgroup>
                                                                <col
                                                                    width="22%" />
                                                                <col
                                                                    width="24%" />
                                                                <col
                                                                    width="24%" />
                                                                <col
                                                                    width="30%" />
                                                            </colgroup>
                                                             	<tr>
                                                             		<td nowrap="nowrap">
                                                             			<!-- One Time -->
                                                            			<nested:checkbox
                                                                			property="billingInstructions"
                                                                			styleClass="searchBillingInstructions"
                                                                			value="6">
                                                                			<bean:message key="screen.b_cpm.label.onetime" />
                                                            			</nested:checkbox>
                                                            		</td>
                                                             		<td nowrap="nowrap">
                                                             			<!-- Monthly -->
                                                            			<nested:checkbox
                                                                			property="billingInstructions"
                                                                			styleClass="searchBillingInstructions"
                                                                			value="5">
                                                                			<bean:message key="screen.b_cpm.label.monthly" />
                                                            			</nested:checkbox>
                                                             		</td>
                                                             		<td nowrap="nowrap">
                                                             			<!-- Bi-Monthly -->
                                                            			<nested:checkbox
                                                                			property="billingInstructions"
                                                                			styleClass="searchBillingInstructions"
                                                                			value="4">
                                                                			<bean:message key="screen.b_cpm.label.bimonthly" />
                                                            			</nested:checkbox>
                                                             		</td>
                                                             		<td></td>
                                                             	</tr>
                                                             	<tr>
                                                             		<td nowrap="nowrap">
                                                             			<!-- Quarterly -->
                                                            			<nested:checkbox
                                                                			property="billingInstructions"
                                                                			styleClass="searchBillingInstructions"
                                                                			value="3">
                                                                			<bean:message key="screen.b_cpm.label.quarterly" />
                                                            			</nested:checkbox>
                                                             		</td>
                                                             		<td nowrap="nowrap">
                                                             			<!-- Bi-Annually -->
                                                            			<nested:checkbox
                                                                			property="billingInstructions"
                                                                			styleClass="searchBillingInstructions"
                                                                			value="2">
                                                                			<bean:message key="screen.b_cpm.label.biannually" />
                                                            			</nested:checkbox>
                                                             		</td>
                                                             		<td nowrap="nowrap">
                                                             			<!-- Yearly -->
                                                            			<nested:checkbox
                                                               	 			property="billingInstructions"
                                                                			styleClass="searchBillingInstructions"
                                                                			value="1">
                                                                			<bean:message key="screen.b_cpm.label.yearly" />
                                                            			</nested:checkbox>
                                                             		</td>
                                                             		<td></td>
                                                             	</tr>
                                                             </table>
                                                        </td>
                                                </tr>
                                                <tr>
                                                    <td class="label">
                                                        <bean:message
                                                            key="screen.b_cpm.label.serviceDateStart" />
                                                        <bean:message
                                                            key="screen.b_cpm.label_colon" />
                                                    </td>
                                                    <td class="content">
                                                        <table
                                                            width="100%">
                                                            <colgroup>
                                                                <col
                                                                    width="48%" />
                                                                <col
                                                                    width="4%" />
                                                                <col
                                                                    width="48%" />
                                                            </colgroup>
                                                            <tr>
                                                                <td
                                                                    nowrap="nowrap">
                                                                    <nested:text
                                                                        property="serviceDateStartFrom"
                                                                        styleClass="searchServiceDateStartFrom"
                                                                        style="width: 90%" />
                                                                    <input
                                                                    type="button"
                                                                    onclick="javascript: customCalendar(this, 'searchServiceDateStartFrom', 'dd/MM/yyyy');"
                                                                    value=""
                                                                    class="BlueStyle-button" />
                                                                </td>
                                                                <td><bean:message
                                                                        key="screen.b_cpm.label.empty" />
                                                                </td>
                                                                <td
                                                                    nowrap="nowrap">
                                                                    <nested:text
                                                                        property="serviceDateStartTo"
                                                                        styleClass="searchServiceDateStartTo"
                                                                        style="width: 90%" />
                                                                    <input
                                                                    type="button"
                                                                    onclick="javascript: customCalendar(this, 'searchServiceDateStartTo', 'dd/MM/yyyy');"
                                                                    value=""
                                                                    class="BlueStyle-button" />
                                                                </td>
                                                            </tr>
                                                        </table></td>
                                                </tr>
                                                <tr>
                                                    <td class="label">
                                                        <bean:message
                                                            key="screen.b_cpm.label.serviceDateEnd" />
                                                        <bean:message
                                                            key="screen.b_cpm.label_colon" />
                                                    </td>
                                                    <td class="content">
                                                        <table
                                                            width="100%">
                                                            <colgroup>
                                                                <col
                                                                    width="48%" />
                                                                <col
                                                                    width="4%" />
                                                                <col
                                                                    width="48%" />
                                                            </colgroup>
                                                            <tr>
                                                                <td
                                                                    nowrap="nowrap">
                                                                    <nested:text
                                                                        property="serviceDateEndFrom"
                                                                        styleClass="searchServiceDateStartTo"
                                                                        style="width: 90%" />
                                                                    <input
                                                                    type="button"
                                                                    onclick="javascript: customCalendar(this, 'searchServiceDateStartTo', 'dd/MM/yyyy');"
                                                                    value=""
                                                                    class="BlueStyle-button" />
                                                                </td>
                                                                <td><bean:message
                                                                        key="screen.b_cpm.label.empty" />
                                                                </td>
                                                                <td
                                                                    nowrap="nowrap">
                                                                    <nested:text
                                                                        property="serviceDateEndTo"
                                                                        styleClass="searchServiceDateEndTo"
                                                                        style="width: 90%" />
                                                                    <input
                                                                    type="button"
                                                                    onclick="javascript: customCalendar(this, 'searchServiceDateEndTo', 'dd/MM/yyyy');"
                                                                    value=""
                                                                    class="BlueStyle-button" />
                                                                </td>
                                                            </tr>
                                                        </table></td>
                                                </tr>
                                                <tr>
                                                    <td class="label">
                                                        <bean:message
                                                            key="screen.b_cpm.label.contractStart" />
                                                        <bean:message
                                                            key="screen.b_cpm.label_colon" />
                                                    </td>
                                                    <td class="content">
                                                        <table
                                                            width="100%">
                                                            <colgroup>
                                                                <col
                                                                    width="48%" />
                                                                <col
                                                                    width="4%" />
                                                                <col
                                                                    width="48%" />
                                                            </colgroup>
                                                            <tr>
                                                                <td
                                                                    nowrap="nowrap">
                                                                    <nested:text
                                                                        property="contractStartFrom"
                                                                        styleClass="contractStartFrom"
                                                                        style="width: 90%" />
                                                                    <input
                                                                    type="button"
                                                                    onclick="javascript: customCalendar(this, 'contractStartFrom', 'dd/MM/yyyy');"
                                                                    value=""
                                                                    class="BlueStyle-button" />
                                                                </td>
                                                                <td><bean:message
                                                                        key="screen.b_cpm.label.empty" />
                                                                </td>
                                                                <td
                                                                    nowrap="nowrap">
                                                                    <nested:text
                                                                        property="contractStartTo"
                                                                        styleClass="contractStartTo"
                                                                        style="width: 90%" />
                                                                    <input
                                                                    type="button"
                                                                    onclick="javascript: customCalendar(this, 'contractStartTo', 'dd/MM/yyyy');"
                                                                    value=""
                                                                    class="BlueStyle-button" />
                                                                </td>
                                                            </tr>
                                                        </table></td>
                                                </tr>
                                                <tr>
                                                    <td class="label">
                                                        <bean:message
                                                            key="screen.b_cpm.label.contractEnd" />
                                                        <bean:message
                                                            key="screen.b_cpm.label_colon" />
                                                    </td>
                                                    <td class="content">
                                                        <table
                                                            width="100%">
                                                            <colgroup>
                                                                <col
                                                                    width="48%" />
                                                                <col
                                                                    width="4%" />
                                                                <col
                                                                    width="48%" />
                                                            </colgroup>
                                                            <tr>
                                                                <td
                                                                    nowrap="nowrap">
                                                                    <nested:text
                                                                        property="contractEndFrom"
                                                                        styleClass="contractEndFrom"
                                                                        style="width: 90%" />
                                                                    <input
                                                                    type="button"
                                                                    onclick="javascript: customCalendar(this, 'contractEndFrom', 'dd/MM/yyyy');"
                                                                    value=""
                                                                    class="BlueStyle-button" />
                                                                </td>
                                                                <td><bean:message
                                                                        key="screen.b_cpm.label.empty" />
                                                                </td>
                                                                <td
                                                                    nowrap="nowrap">
                                                                    <nested:text
                                                                        property="contractEndTo"
                                                                        styleClass="contractEndTo"
                                                                        style="width: 90%" />
                                                                    <input
                                                                    type="button"
                                                                    onclick="javascript: customCalendar(this, 'contractEndTo', 'dd/MM/yyyy');"
                                                                    value=""
                                                                    class="BlueStyle-button" />
                                                                </td>
                                                            </tr>
                                                        </table></td>
                                                </tr>
                                                <tr>
                                                    <td class="label">
                                                        <bean:message
                                                            key="screen.b_cpm.label.applDate" />
                                                        <bean:message
                                                            key="screen.b_cpm.label_colon" />
                                                    </td>
                                                    <td class="content">
                                                        <table
                                                            width="100%">
                                                            <colgroup>
                                                                <col
                                                                    width="48%" />
                                                                <col
                                                                    width="4%" />
                                                                <col
                                                                    width="48%" />
                                                            </colgroup>
                                                            <tr>
                                                                <td
                                                                    nowrap="nowrap">
                                                                    <nested:text
                                                                        property="applicationDateFrom"
                                                                        styleClass="applicationDateFrom"
                                                                        style="width: 90%" />
                                                                    <input
                                                                    type="button"
                                                                    onclick="javascript: customCalendar(this, 'applicationDateFrom', 'dd/MM/yyyy');"
                                                                    value=""
                                                                    class="BlueStyle-button" />
                                                                </td>
                                                                <td><bean:message
                                                                        key="screen.b_cpm.label.empty" />
                                                                </td>
                                                                <td
                                                                    nowrap="nowrap">
                                                                    <nested:text
                                                                        property="applicationDateTo"
                                                                        styleClass="applicationDateTo"
                                                                        style="width: 90%" />
                                                                    <input
                                                                    type="button"
                                                                    onclick="javascript: customCalendar(this, 'applicationDateTo', 'dd/MM/yyyy');"
                                                                    value=""
                                                                    class="BlueStyle-button" />
                                                                </td>
                                                            </tr>
                                                        </table></td>
                                                </tr>
                                                <%--
										<tr>
											<td class="label">
												<bean:message key="screen.b_cpm.label.contractTerm"/>
												<bean:message key="screen.b_cpm.label_colon"/>
											</td>
											<td class="content">
												<table>
													<COLGROUP>
														<COL width="20%"/>
														<COL width="20%"/>
														<COL width="60%"/>
													</COLGROUP>
													<tr>
														<td nowrap="nowrap">
															<nested:radio property="contractTermType" styleClass="searchContractTermType" value="M"></nested:radio>
															<bean:message key="screen.b_cpm.label.months"/>
														</td>
														<td nowrap="nowrap">
															<nested:radio property="contractTermType" styleClass="searchContractTermType" value="Y"></nested:radio>
															<bean:message key="screen.b_cpm.label.years"/>
														</td>
														<td><nested:text property="contractTerm" styleClass="searchContractTerm"/></td>
													</tr>
												</table>
											</td>
										</tr>
										--%>
                                            </table></td>
                                    </tr>
                                </table>
                            </fieldset></td>
                    </tr>
                    <!-- button group -->
                    <tr>
                        <td colspan="2"><input type="submit"
                            id="btnSearchPlan"
                            value="<bean:message key="screen.b_cpm.button.search"/>"
                            onclick="javascript: search();showLoadingTipWindow();"
                            style="width: 70px;" /> <bs:buttonLink
                                action="/B_CPM_S01LoadBL" value="Reset" />
                            <logic:equal value="2"
                                name="accessRightBean">
                                <span id="spanNewCustomerPlan"> <input
                                    type="button"
                                    id="btnNewCustomerPlan"
                                    value="<bean:message key="screen.b_cpm.button.new"/>"
                                    onclick="javascript: gotoScreen('newCustPlan','');"
                                    style="width: 70px;" /> </span>
                                <span id="exportPlan"> <nested:notEqual
                                        property="totalCount" value="0">
                                        <nested:empty
                                            property="totalCount">
                                            <input type="button"
                                                id="btnExportPlan"
                                                value="<bean:message key="screen.b_cpm.button.export"/>"
                                                onclick="javascript: exportCSV();"
                                                disabled="disabled" />
                                        </nested:empty>
                                        <nested:notEmpty
                                            property="totalCount">
                                            <input type="button"
                                                id="btnExportPlan"
                                                value="<bean:message key="screen.b_cpm.button.export"/>"
                                                onclick="exportCSV('<nested:write property="totalCount" />', '<t:writeCodeValue codeList="LIST_EXPORT_LIMIT" key="B-CPM" name="export_limit"></t:writeCodeValue>');showLoadingTipWindow();" />
                                        </nested:notEmpty>
                                    </nested:notEqual> <nested:equal property="totalCount"
                                        value="0">
                                        <input type="button"
                                            id="btnExportPlan"
                                            value="<bean:message key="screen.b_cpm.button.export"/>"
                                            onclick="javascript: exportCSV();"
                                            disabled="disabled" />
                                    </nested:equal> </span>
                            </logic:equal> <logic:equal value="1"
                                name="accessRightBean">
                                <span id="exportPlan"> <nested:notEqual
                                        property="totalCount" value="0">
                                        <nested:empty
                                            property="totalCount">
                                            <input type="button"
                                                id="btnExportPlan"
                                                value="<bean:message key="screen.b_cpm.button.export"/>"
                                                onclick="javascript: exportCSV();"
                                                disabled="disabled" />
                                        </nested:empty>
                                        <nested:notEmpty
                                            property="totalCount">
                                            <input type="button"
                                                id="btnExportPlan"
                                                value="<bean:message key="screen.b_cpm.button.export"/>"
                                                onclick="javascript: exportCSV('<nested:write property="totalCount" />', '<t:writeCodeValue codeList="LIST_EXPORT_LIMIT" key="B-CPM" name="export_limit"></t:writeCodeValue>');showLoadingTipWindow();" />
                                        </nested:notEmpty>
                                    </nested:notEqual> <nested:equal property="totalCount"
                                        value="0">
                                        <input type="button"
                                            id="btnExportPlan"
                                            value="<bean:message key="screen.b_cpm.button.export"/>"
                                            onclick="javascript: exportCSV();"
                                            disabled="disabled" />
                                    </nested:equal> </span>
                            </logic:equal></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div class="searchFound">
                                <bean:message
                                    key="screen.b_cpm.searchResult" />
                                <bean:message
                                    key="screen.b_cpm.label_colon" />
                                <nested:write property="totalCount" />
                            </div></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div id="plan_pageLink" class="pageLink">
                                <nested:hidden styleId="plan_searchBy"
                                    styleClass="hiddenSearchBy"
                                    property="searchBy" />
                                <nested:hidden styleId="plan_startIndex"
                                    styleClass="hiddenStartIndex"
                                    property="startIndex" />
                                <nested:hidden property="row" />
                                <bean:message
                                    key="screen.b_cpm.pageLink" />
                                <bean:message
                                    key="screen.b_cpm.label_colon" />
                                <ts:pageLinks id="curPageLinks"
                                    name="searchPlanPage"
                                    rowProperty="row"
                                    totalProperty="totalCount"
                                    indexProperty="startIndex"
                                    action="javascript: void(0);" />
                                <logic:present name="curPageLinks">
                                    <bean:write name="curPageLinks"
                                        filter="false" />
                                </logic:present>
                            </div></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <table id="tableResultSearchPlan"
                                class="resultInfo">
                                <colgroup>
                                    <col width="6%">
                                    <col width="6%">
                                    <col width="6%">
                                    <col width="6%">
                                    <logic:equal
                                        name="_B_CPM_S01SearchForm"
                                        property="cpmTransType"
                                        value="1">
                                        <col width="6%">
                                        <col width="7%">
                                    </logic:equal>
                                    <logic:notEqual
                                        name="_B_CPM_S01SearchForm"
                                        property="cpmTransType"
                                        value="1">
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
                                    <td class="headerLeft"
                                        nowrap="nowrap"><bean:message
                                            key="screen.b_cpm.header.no" />
                                    </td>
                                    <td class="headerLeft"
                                        nowrap="nowrap"><bean:message
                                            key="screen.b_cpm.header.custId" />
                                    </td>
                                    <td class="headerLeft"
                                        nowrap="nowrap" colspan="2"><bean:message
                                            key="screen.b_cpm.header.custname" />
                                    </td>
                                    <td class="headerLeft"
                                        nowrap="nowrap" colspan="2">
                                        <bean:message
                                            key="screen.b_cpm.header.subId" /><br>
                                        <bean:message
                                            key="screen.b_cpm.header.desc" />
                                    </td>
                                    <logic:equal
                                        name="_B_CPM_S01SearchForm"
                                        property="cpmTransType"
                                        value="1">
                                        <td class="headerLeft"
                                            nowrap="nowrap"><bean:message
                                                key="screen.b_cpm.header.trans" />
                                        </td>
                                    </logic:equal>
                                    <td class="headerLeft"
                                        nowrap="nowrap"><bean:message
                                            key="screen.b_cpm.header.bacNo" />
                                    </td>
                                    <td class="headerLeft"
                                        nowrap="nowrap"><bean:message
                                            key="screen.b_cpm.header.billInst" />
                                    </td>
                                    <td class="headerLeft"
                                        nowrap="nowrap"><bean:message
                                            key="screen.b_cpm.header.currency" />
                                    </td>
                                    <td class="headerLeft"
                                        nowrap="nowrap"><bean:message
                                            key="screen.b_cpm.header.amount" />
                                    </td>
                                    <td class="headerLeft"
                                        nowrap="nowrap"><bean:message
                                            key="screen.b_cpm.header.serviceStart" />
                                    </td>
                                    <td class="headerLeft"
                                        nowrap="nowrap"><bean:message
                                            key="screen.b_cpm.header.serviceEnd" />
                                    </td>
                                    <td class="headerLeft"
                                        nowrap="nowrap"><bean:message
                                            key="screen.b_cpm.header.status" />
                                    </td>
                                </tr>
                                <logic:iterate id="plan_h"
                                    name="_B_CPM_S01SearchForm"
                                    property="searchResult" indexId="i">
                                    <tbody class="trResultSearchPlan">
                                        <tr class="detail">
                                            <td class="headerBorder">${i
                                                + 1 +
                                                searchPlanPage.startIndex}</td>
                                            <td nowrap="nowrap"
                                                class="headerBorder"><a
                                                href="javascript: viewCustomer('<bean:write name="plan_h" property="ID_CUST"/>', '<bean:write name="plan_h" property="CUSTOMER_TYPE"/>');"><bean:write
                                                        name="plan_h"
                                                        property="ID_CUST" />
                                            </a>
                                            </td>
                                            <td nowrap="nowrap"
                                                colspan="2"
                                                class="headerBorder"><bean:write
                                                    name="plan_h"
                                                    property="CUST_NAME" />&nbsp;</td>
                                            <td nowrap="nowrap"
                                                colspan="2"
                                                class="headerBorder">
                                                <logic:equal
                                                    name="_B_CPM_S01SearchForm"
                                                    property="b_ssmIsUsed"
                                                    value="1">
                                                    <c:choose>
                                                        <c:when
                                                            test="${'1' eq accessRightB_SSM or '2' eq accessRightB_SSM}">
                                                            <a
                                                                href="javascript: viewSubScriptionInfo('<bean:write name="plan_h" property="ID_CUST_PLAN"/>', '<bean:write name="plan_h" property="ID_CUST"/>', '<bean:write name="plan_h" property="ID_SUB_INFO"/>')"><bean:write
                                                                    name="plan_h"
                                                                    property="ID_SUB_INFO" />
                                                            </a>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <bean:write
                                                                name="plan_h"
                                                                property="ID_SUB_INFO" />
                                                        </c:otherwise>
                                                    </c:choose>
                                                </logic:equal> <logic:notEqual
                                                    name="_B_CPM_S01SearchForm"
                                                    property="b_ssmIsUsed"
                                                    value="1">
                                                    <bean:write
                                                        name="plan_h"
                                                        property="ID_SUB_INFO" />
                                                </logic:notEqual></td>
                                            <logic:equal
                                                name="_B_CPM_S01SearchForm"
                                                property="cpmTransType"
                                                value="1">
                                                <td nowrap="nowrap"
                                                    class="headerBorder">
                                                    <bean:write
                                                        name="plan_h"
                                                        property="TRANSACTION_TYPE" />
                                                    <!--  <t:writeCodeValue codeList="LIST_TRANSACTIONTYPE" name="plan_h" property="TRANSACTION_TYPE"/>&nbsp; -->
                                                </td>
                                            </logic:equal>
                                            <td nowrap="nowrap"
                                                class="headerBorder">
                                                <logic:equal value="-"
                                                    name="plan_h"
                                                    property="ID_BILL_ACCOUNT">
                                                    <bean:write
                                                        name="plan_h"
                                                        property="ID_BILL_ACCOUNT" />&nbsp;
								</logic:equal> <logic:notEqual value="-" name="plan_h"
                                                    property="ID_BILL_ACCOUNT">
                                                    <a
                                                        href="javascript: viewBAC('<bean:write name="plan_h" property="ID_CUST_PLAN"/>', '<bean:write name="plan_h" property="ID_CUST"/>', '<bean:write name="plan_h" property="ID_BILL_ACCOUNT"/>');"><bean:write
                                                            name="plan_h"
                                                            property="ID_BILL_ACCOUNT" />
                                                    </a>&nbsp;
								</logic:notEqual></td>
                                            <td nowrap="nowrap"
                                                class="headerBorder">
                                                <bean:write
                                                    name="plan_h"
                                                    property="BILL_INSTRUCT" />
                                                <t:writeCodeValue
                                                    codeList="BILL_INSTRUCTION_LIST"
                                                    name="plan_h"
                                                    property="BILL_INSTRUCT" />&nbsp;
                                            </td>
                                            <td nowrap="nowrap"
                                                class="headerBorder">
                                                <bean:write
                                                    name="plan_h"
                                                    property="BILL_CURRENCY" />&nbsp;
                                            </td>
                                            <td nowrap="nowrap"
                                                class="numberStyleBold"
                                                style="border-top: 1px solid #d0d0d0;">
                                                <fmt:formatNumber
                                                    value="${plan_h.TOTAL_AMOUNT}"
                                                    pattern="#,###,###.00" />&nbsp;
                                            </td>
                                            <td nowrap="nowrap"
                                                class="headerBorder"></td>
                                            <td nowrap="nowrap"
                                                colspan="2"
                                                class="headerBorder"></td>
                                        </tr>
                                        <!-- SUB PLAN -->
                                        <logic:iterate id="plan_d"
                                            name="plan_h"
                                            property="SUB_PLAN"
                                            indexId="j">
                                            <tr class="hasSubPlan">
                                                <td></td>
                                                <td></td>
                                                <td colspan="2"></td>
                                                <logic:equal
                                                    name="_B_CPM_S01SearchForm"
                                                    property="cpmTransType"
                                                    value="1">
                                                    <td colspan="6"
                                                        nowrap="nowrap">
                                                        <span
                                                        class="spanToggleClick">...</span>
                                                        <a
                                                        href="javascript: viewCustomerPlan('<bean:write name="plan_h" property="ID_CUST_PLAN"/>')"><bean:write
                                                                name="plan_d"
                                                                property="BILL_DESC" />
                                                    </a></td>
                                                </logic:equal>
                                                <logic:notEqual
                                                    name="_B_CPM_S01SearchForm"
                                                    property="cpmTransType"
                                                    value="1">
                                                    <td colspan="5"
                                                        nowrap="nowrap">
                                                        <span
                                                        class="spanToggleClick">...</span>
                                                        <a
                                                        href="javascript: viewCustomerPlan('<bean:write name="plan_h" property="ID_CUST_PLAN"/>')"><bean:write
                                                                name="plan_d"
                                                                property="BILL_DESC" />
                                                    </a></td>
                                                </logic:notEqual>
                                                <td nowrap="nowrap"
                                                    class="numberStyle">
                                                    <fmt:formatNumber
                                                        value="${plan_d.TOTAL_AMOUNT}"
                                                        pattern="#,###,###.00" />&nbsp;
                                                </td>
                                                <td nowrap="nowrap"
                                                    class="dateStyle">&nbsp;<bean:write
                                                        name="plan_d"
                                                        property="SVC_START" />&nbsp;</td>
                                                <td nowrap="nowrap">&nbsp;<bean:write
                                                        name="plan_d"
                                                        property="SVC_END" />&nbsp;</td>
                                                <td nowrap="nowrap"
                                                    class="dateStyle">
                                                    <t:writeCodeValue
                                                        codeList="LIST_PLANSTATUS"
                                                        name="plan_d"
                                                        property="SERVICES_STATUS" />
                                                </td>
                                            </tr>
                                            <!-- SUB PLAN LINK -->
                                            <logic:notEmpty
                                                name="plan_d"
                                                property="SUB_PLAN_LINK">
                                                <tr
                                                    class="spanTogglePlace">
                                                    <td></td>
                                                    <td></td>
                                                    <logic:equal
                                                        name="_B_CPM_S01SearchForm"
                                                        property="cpmTransType"
                                                        value="1">
                                                        <td colspan="12"
                                                            style="padding: 5px;">
                                                            <table
                                                                cellpadding="0"
                                                                cellspacing="0"
                                                                border="0"
                                                                style="width: 100%; height: 100%; color: #a04c51; font-size: 15px; font-family: 'Calibri'; border: 1px solid #CEE7FF;">
                                                                <tr>
                                                                    <td
                                                                        style="width: 45%; padding-left: 5px; background-color: #CEE7FF;"><bean:message
                                                                            key="screen.b_cpm.label.detail.itemDescription" />
                                                                    </td>
                                                                    <td
                                                                        style="width: 10%; padding-left: 5px; background-color: #CEE7FF;"><bean:message
                                                                            key="screen.b_cpm.label.detail.rateType" />
                                                                    </td>
                                                                    <td
                                                                        style="width: 10%; padding-left: 5px; background-color: #CEE7FF;"><bean:message
                                                                            key="screen.b_cpm.label.detail.rateMode" />
                                                                    </td>
                                                                    <td
                                                                        style="width: 10%; padding-left: 5px; background-color: #CEE7FF;"><bean:message
                                                                            key="screen.b_cpm.label.quantity" />
                                                                    </td>
                                                                    <td
                                                                        style="width: 10%; padding-left: 5px; background-color: #CEE7FF;"><bean:message
                                                                            key="screen.b_cpm.label.price" />
                                                                    </td>
                                                                    <td
                                                                        style="width: 15%; padding-left: 5px; background-color: #CEE7FF;"><bean:message
                                                                            key="screen.b_cpm.header.amount" />
                                                                    </td>
                                                                </tr>
                                                                <logic:iterate
                                                                    id="plan_link"
                                                                    name="plan_d"
                                                                    property="SUB_PLAN_LINK">
                                                                    <tr>
                                                                        <td
                                                                            style="padding-left: 5px; border-top: 1px solid #CEE7FF;"><bean:write
                                                                                name="plan_link"
                                                                                property="ITEM_DESC" />
                                                                        </td>
                                                                        <td
                                                                            style="padding-left: 5px; border-top: 1px solid #CEE7FF;"><t:writeCodeValue
                                                                                codeList="LIST_RATETYPE"
                                                                                key="${plan_link.RATE_TYPE}" />
                                                                        </td>
                                                                        <td
                                                                            style="padding-left: 5px; border-top: 1px solid #CEE7FF;"><t:writeCodeValue
                                                                                codeList="LIST_RATEMODE"
                                                                                key="${plan_link.RATE_MODE}" />
                                                                        </td>
                                                                        <td
                                                                            style="padding-right: 25px; text-align: right; border-top: 1px solid #CEE7FF;"><fmt:formatNumber
                                                                                value="${plan_link.QUANTITY}"
                                                                                pattern="#,###,###" />
                                                                        </td>
                                                                        <td
                                                                            style="padding-right: 25px; text-align: right; border-top: 1px solid #CEE7FF;"><fmt:formatNumber
                                                                                value="${plan_link.UNIT_PRICE}"
                                                                                pattern="#,###,###.00" />
                                                                        </td>
                                                                        <td
                                                                            style="padding-right: 60px; text-align: right; border-top: 1px solid #CEE7FF;"><fmt:formatNumber
                                                                                value="${plan_link.TOTAL_AMOUNT}"
                                                                                pattern="#,###,###.00" />
                                                                        </td>
                                                                    </tr>
                                                                </logic:iterate>
                                                            </table></td>
                                                    </logic:equal>
                                                    <logic:notEqual
                                                        name="_B_CPM_S01SearchForm"
                                                        property="cpmTransType"
                                                        value="1">
                                                        <td colspan="11"
                                                            style="padding: 5px;">
                                                            <table
                                                                cellpadding="0"
                                                                cellspacing="0"
                                                                border="0"
                                                                style="width: 100%; height: 100%; color: #a04c51; font-size: 15px; font-family: 'Calibri'; border: 1px solid #CEE7FF;">
                                                                <tr>
                                                                    <td
                                                                        style="width: 45%; padding-left: 5px; background-color: #CEE7FF;"><bean:message
                                                                            key="screen.b_cpm.label.detail.itemDescription" />
                                                                    </td>
                                                                    <td
                                                                        style="width: 10%; padding-left: 5px; background-color: #CEE7FF;"><bean:message
                                                                            key="screen.b_cpm.label.detail.rateType" />
                                                                    </td>
                                                                    <td
                                                                        style="width: 10%; padding-left: 5px; background-color: #CEE7FF;"><bean:message
                                                                            key="screen.b_cpm.label.detail.rateMode" />
                                                                    </td>
                                                                    <td
                                                                        style="width: 10%; padding-left: 5px; background-color: #CEE7FF;"><bean:message
                                                                            key="screen.b_cpm.label.quantity" />
                                                                    </td>
                                                                    <td
                                                                        style="width: 10%; padding-left: 5px; background-color: #CEE7FF;"><bean:message
                                                                            key="screen.b_cpm.label.price" />
                                                                    </td>
                                                                    <td
                                                                        style="width: 15%; padding-left: 5px; background-color: #CEE7FF;"><bean:message
                                                                            key="screen.b_cpm.header.amount" />
                                                                    </td>
                                                                </tr>
                                                                <logic:iterate
                                                                    id="plan_link"
                                                                    name="plan_d"
                                                                    property="SUB_PLAN_LINK">
                                                                    <tr>
                                                                        <td
                                                                            style="padding-left: 5px; border-top: 1px solid #CEE7FF;"><bean:write
                                                                                name="plan_link"
                                                                                property="ITEM_DESC" />
                                                                        </td>
                                                                        <td
                                                                            style="padding-left: 5px; border-top: 1px solid #CEE7FF;"><t:writeCodeValue
                                                                                codeList="LIST_RATETYPE"
                                                                                key="${plan_link.RATE_TYPE}" />
                                                                        </td>
                                                                        <td
                                                                            style="padding-left: 5px; border-top: 1px solid #CEE7FF;"><t:writeCodeValue
                                                                                codeList="LIST_RATEMODE"
                                                                                key="${plan_link.RATE_MODE}" />
                                                                        </td>
                                                                        <td
                                                                            style="padding-right: 25px; text-align: right; border-top: 1px solid #CEE7FF;"><fmt:formatNumber
                                                                                value="${plan_link.QUANTITY}"
                                                                                pattern="#,###,###" />
                                                                        </td>
                                                                        <td
                                                                            style="padding-right: 25px; text-align: right; border-top: 1px solid #CEE7FF;"><fmt:formatNumber
                                                                                value="${plan_link.UNIT_PRICE}"
                                                                                pattern="#,###,###.00" />
                                                                        </td>
                                                                        <td
                                                                            style="padding-right: 60px; text-align: right; border-top: 1px solid #CEE7FF;"><fmt:formatNumber
                                                                                value="${plan_link.TOTAL_AMOUNT}"
                                                                                pattern="#,###,###.00" />
                                                                        </td>
                                                                    </tr>
                                                                </logic:iterate>
                                                            </table></td>
                                                    </logic:notEqual>
                                                </tr>
                                            </logic:notEmpty>
                                            <!-- END SUB PLAN LINK -->
                                        </logic:iterate>
                                        <!-- END SUB PLAN -->
                                    </tbody>
                                </logic:iterate>
                                <!-- END PLAN -->
                            </table></td>
                    </tr>
                </table>
            </nested:nest>
        </div>
        <div style="visibility: hidden">
            <input name="templateCalendar" />
            <t:inputCalendar for="templateCalendar" format="dd/MM/yyyy" />
        </div>
        <!-- message display start -->
        <div class="message" style="font-size:16px">
            <html:messages id="message" message="true">
                <bean:write name="message" />
            </html:messages>
        </div>
        <div class="error" style="font-size:16px">
            <html:messages id="message">
                <bean:write name="message" />
                <br />
            </html:messages>
        </div>
        <!-- message display end -->
    </ts:form>

</body>
</html:html>

