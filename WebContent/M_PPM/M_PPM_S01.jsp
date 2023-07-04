<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/bs" prefix="bs" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
   	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
   	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/M_PPM/css/common.css"/>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   	<script type="text/javascript" src="<%=request.getContextPath()%>/M_PPM/js/m_ppm_s01.js"></script>
	<title></title>
	<style>
		.ui-autocomplete {
    		overflow-y: auto;
    		overflow-x: hidden;
    		font-family: Calibri;
    		font-size: 0.9em;
  		}		
	</style>
</head>

<body id="m" onload="initMain();initPage();">
<div style="min-width:1010px;" id="divBody">
<%
String accessType = ((nttdm.bsys.common.fw.BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT")).getUserAccessInfo("M", "M-PPM").getAccess_type();
boolean authorized = accessType.equals("2");
%>
<t:defineCodeList id="LIST_CURRENCY" />
<t:defineCodeList id="LIST_CUSTOMERTYPE" />
<t:defineCodeList id="LIST_SERVICE_GROUP" />
<table class="subHeader">
	<tr>
		<td><bean:message key="screen.m_ppms01.title"/></td>
	</tr>
</table>
<ts:form action="/M_PPMS01DSP" >
<!-- #180 Start -->
<html:hidden property="cboService" styleId="hiddenService"/>
<html:hidden property="cboPlan" styleId="hiddenPlan"/>
<html:hidden property="cboPlanDetail" styleId="hiddenPlanDetail"/>
<!-- #180 End -->
<table class="searchCriteriaTable" cellpadding="0" cellspacing="0">
  <tr>
    <td class="col1Top" width="20%"><bean:message key="screen.m_ppms01.serviceName"/> <bean:message key="screen.m_ppms01.colon"/>&nbsp;</td>
    <td class="col2Top" width="30%">
    	<html:text property="txtPlanName" style="width:100%;" maxlength="300"/>
    </td>
    <td rowspan="5" class="col3Top" width="50%">
		<!-- #180 Start -->
        <jsp:include page="/COMMON/SearchableSelect.jsp" flush="true">
		    <jsp:param value="_m_ppmFormS01" name="ppmServiceGroup"/>
		</jsp:include>
		<!-- #180 End -->
        <fieldset style="width:100%">
            <legend style="color:blue"><bean:message key="screen.m_ppms01.serviceInformation"/></legend>
                <table cellpadding="0" cellspacing="0" style="width:100%">
                <tr>
                    <td style="text-align: right;width:25%" nowrap="nowrap"><bean:message key="screen.m_ppms01.category"/> <bean:message key="screen.m_ppms01.colon"/>&nbsp;</td>
	                <td style="width:75%">
		                <html:select property="cboCategory" name="_m_ppmFormS01" styleClass="searchCategory" style="width:100%" value="${_m_ppmFormS01.map.cboCategory}" onchange="changeCategory(this)">
		                    <html:option value=""> <%-- <bean:message key="screen.m_ppms02.selectone"/> --%> 
		                    	- Please Select One -
		                    </html:option>
		                    <html:optionsCollection property="cboCategoryList" name="_m_ppmFormS01" value="svcGrp" label="svcGrpName" filter="false"/>
		                </html:select>
	                </td></tr>
	                
                <tr>
                    <td style="text-align: right;"><bean:message key="screen.m_ppms01.service"/> <bean:message key="screen.m_ppms01.colon"/>&nbsp;</td>
                    <td>
                        <html:select property="cboService" name="_m_ppmFormS01" styleClass="searchService" style="width:100%" disabled="true">
                            <html:option value=""> <%-- <bean:message key="screen.m_ppms02.selectone"/> --%> </html:option>
                            <logic:notEmpty name="_m_ppmFormS01" property="cboServiceList">
                                <html:optionsCollection property="cboServiceList" name="_m_ppmFormS01" value="serviceCd" label="serviceName" filter="false"/>
                            </logic:notEmpty>
                        </html:select>
                    </td></tr>

                <tr>
                    <td style="text-align: right;"><bean:message key="screen.m_ppms01.plan"/> <bean:message key="screen.m_ppms01.colon"/>&nbsp;</td>
                    <td>
                        <html:select property="cboPlan" name="_m_ppmFormS01" styleClass="searchPlan" style="width:100%" disabled="true">
                            <html:option value=""> <%-- <bean:message key="screen.m_ppms02.selectone"/> --%> </html:option>
                            <logic:notEmpty property="cboPlanList" name="_m_ppmFormS01">
                                <html:optionsCollection property="cboPlanList" name="_m_ppmFormS01" value="planCd" label="planName" filter="false"/>
                            </logic:notEmpty>
                        </html:select>
                    </td></tr>

                <tr>
                    <td style="text-align: right;"><bean:message key="screen.m_ppms01.plandetail"/> <bean:message key="screen.m_ppms01.colon"/>&nbsp;</td>
                    <td>
                        <html:select property="cboPlanDetail" name="_m_ppmFormS01" styleClass="searchPlanDetail" style="width:100%" disabled="true">
                            <html:option value=""> <%-- <bean:message key="screen.m_ppms02.selectone"/> --%> </html:option>
                            <logic:notEmpty property="cboPlanDetailList" name="_m_ppmFormS01">
                                <html:optionsCollection property="cboPlanDetailList" name="_m_ppmFormS01" value="planDetailCd" label="planDetailName" filter="false"/>
                            </logic:notEmpty>
                        </html:select>
                    </td></tr>
                    
                </table>
        </fieldset>
    </td>
  </tr>
  
  <tr>
    <td style="text-align: right;"><bean:message key="screen.m_ppms01.servicedesc"/> <bean:message key="screen.m_ppms01.colon"/>&nbsp;</td>
    <td >
    	<html:text property="txtPlanDescription" style="width:100%;" maxlength="150"/>   
    </td>  
  </tr>
  
  
  <tr>
    <td style="text-align: right;"><bean:message key="screen.m_ppms01.subPlanOptionName"/> <bean:message key="screen.m_ppms01.colon"/>&nbsp;</td>
    <td >
        <html:text property="txtSubPlanOptionName" style="width:100%;" maxlength="300"/>   
    </td> 
  </tr>
  
  <tr>
	   <td style="text-align: right;"><bean:message key="screen.m_ppms01.customertype"/> <bean:message key="screen.m_ppms01.colon"/>&nbsp;</td>
	   <td>
	        <html:select name="_m_ppmFormS01" property="cboCustomerType" value="${_m_ppmFormS01.map.cboCustomerType}">
	            <option value=""><bean:message key="screen.m_ppms02.selectone"/></option>
	            <html:optionsCollection name="LIST_CUSTOMERTYPE" label="name" value="id"/>
	        </html:select>  
	  </td>
  </tr>
  
  <tr>
	  <td style="text-align: right;"><bean:message key="screen.m_ppms01.currence"/> <bean:message key="screen.m_ppms01.colon"/>&nbsp;</td>
	    <td >
	        <html:select name="_m_ppmFormS01" property="cboBillCurrency" value="${_m_ppmFormS01.map.cboBillCurrency}" style="width:100%">
	            <option value=""><bean:message key="screen.m_ppms02.selectone"/></option>
	            <html:options collection="LIST_CURRENCY" property="id" labelProperty="name"/>
	        </html:select>      
	  </td>
  </tr>
  
  <tr>
    <td class="colBottom" colspan="4">&nbsp;</td>
  </tr>
</table>
<table class="buttonGroup" cellpadding="0" cellspacing="0">
  	<tr>
		<td>
			<input type="submit" class="button" value="<bean:message key="screen.m_ppms01.search"/>" name="forward_search" onclick="searchClick();"/>
			<bs:buttonLink action="/M_PPMS01_01BL" value="Reset"/>
			<%if(authorized) { %>
			<input type="submit" class="button" value="<bean:message key="screen.m_ppms01.new"/>" name="forward_new" onclick="clickNew('<%=request.getContextPath()%>/M_PPM/M_PPM_S02nBL.do')"/>
			<%} else { %>
		<!--  	<input type="submit" class="button" disabled="disabled" value="<bean:message key="screen.m_ppms01.new"/>"/> -->
			<%} %>
			<% if("1".equals(accessType)||"2".equals(accessType)){ %>
                <c:if test="${_m_ppmFormS01.map.totalCount > 0}">
                    <html:submit property="forward_export" onclick="showLoadingTipWindow();"><bean:message key="screen.m_ppms01.button.export"/></html:submit>
                </c:if>
                <c:if test="${_m_ppmFormS01.map.totalCount == 0 or _m_ppmFormS01.map.totalCount == null}">
                    <html:button property="forward_export" disabled="true"><bean:message key="screen.m_ppms01.button.export"/></html:button>
                </c:if>
			<%} %>
			<ts:submit value="link" property="forward_link" style="visibility:hidden"/>
		</td>	
	</tr>
</table>     

<table class="searchResultNo" cellpadding="0" cellspacing="0">
  	<tr>
		<td>
			<bean:message key="screen.m_ppms01.searchtitle"/> <bean:message key="screen.m_ppms01.colon"/>
			<bean:write name="_m_ppmFormS01" property="totalCount"/>		
		</td>	
	</tr>
</table>
<table class="pageLink" cellpadding="0" cellspacing="0">
  	<tr>
		<td>			
			<bean:message key="screen.m_ppms01.gotopage"/>&nbsp;<bean:message key="screen.m_ppms01.colon"/>&nbsp;
			<ts:pageLinks id="userPageLinks"
				action="${pageContext.request.contextPath}/M_PPM/M_PPMS01_02BL.do"
				name="_m_ppmFormS01" 
				rowProperty="row" 
				totalProperty="totalCount"
				indexProperty="startIndex" 
				currentPageIndex="now"
				totalPageCount="total" 
				submit="true"/>
            <logic:present name="userPageLinks">  
				<bean:write name="userPageLinks" filter="false"/>
			</logic:present>
		</td>	
	</tr>
</table>  
<table class="resultInfo search_result_table" cellpadding="0" cellspacing="0">
	<tr>
		<td class="header" style="text-align: left;" width="5%"><bean:message key="screen.m_ppms01.no"/></td>
		<td class="header" style="text-align: left;" width="13%"><bean:message key="screen.m_ppms01.custtype"/></td>
		<td class="header" style="text-align: left;" width="35%"><bean:message key="screen.m_ppms01.serviceName"/></td>
		<td class="header" style="text-align: left;" width="35%"><bean:message key="screen.m_ppms01.servicedesc"/></td>
		<td class="header" style="text-align: left;" width="12%"><bean:message key="screen.m_ppms01.currence"/></td>
	</tr>
	<logic:present name="_m_ppmFormS01" property="searchResult">
	<logic:iterate id="plan" name="_m_ppmFormS01" property="searchResult">
	<tr>
		<td>${plan.no}</td>
		<td><t:writeCodeValue codeList="LIST_CUSTOMERTYPE" key="${plan.customerType}"/></td>
		<td><a href="<%=request.getContextPath()%>/M_PPM/M_PPM_S02ViewBL.do?idPlan=${plan.idPlan}">${plan.planName}</a></td>
		<td>${plan.descriptionLimit}</td>
		<td>${plan.currency}</td>
	</tr>
	<tr>
		<td style="border-bottom: #cfcfcf 1px solid" colspan="6"></td>
	</tr>
	</logic:iterate>
	</logic:present>
</table>
<input type="hidden" id="contextPath" value="<%=request.getContextPath()%>"/>
</ts:form>
</div>
<div class="message">
	<html:messages id="message" message="true">
		<bean:write name="message"/>
	</html:messages>
</div>
<div class="error">
	<html:messages id="message">
		<bean:write name="message"/><br/>
	</html:messages>
</div>
</body>
</html>