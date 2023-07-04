<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
       <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
       <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/M_PPM/css/common.css"/>
    
    <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
       <script type="text/javascript" src="<%=request.getContextPath()%>/M_PPM/js/m_ppm_s04.js"></script>
    <title>Standard Plan</title>
    <script>
    function changewidth(){
        var popupWidth = window.screen.width*80/100;
        popupWidth = popupWidth - 45;
        document.documentElement.childNodes[1].innerHTML="<div style='width:"+popupWidth+"px'>"+document.documentElement.childNodes[1].innerHTML+"</div>";
        if(document.forms[0].cboCategory.selectedIndex == 0){
            document.forms[0].cboService.selectedIndex = 0;
            document.forms[0].cboService.disabled=true;
        }
    }
    </script>
</head>
<body id="m" onload="changewidth();">
<ts:form action="/M_PPMS04Search" >
<html:hidden name="_m_ppmFormS04" property="doSearch" value="Y"/>
<html:hidden name="_m_ppmFormS04" property="doGetService" value=""/>
<input type="hidden" value="<%=request.getContextPath()%>" id="rootPath"/>
<table class="subHeader">
    <tr>
        <td><bean:message key="screen.m_ppm_04.tittle"/></td>
    </tr>
</table>
<table class="inputInfo" cellpadding="0" cellspacing="0">
  <tr>
    <td class="col1Top" style="text-align: left;padding-left:10px" width="15%"><bean:message key="screen.m_ppm_04.serviceName"/> </td>
    <td class="col2Top" width="25%">
        <bean:message key="screen.m_ppm_04.colon"/>&nbsp;<html:text property="tbxServiceName" style="width:100%;" name="_m_ppmFormS04"/>
    </td>
    <td class="col3Top" style="text-align: left;padding-left:40px" width="15%"><bean:message key="screen.m_ppm_04.category"/> </td>
    <td class="col4Top" width="45%">
        <bean:message key="screen.m_ppm_04.colon"/>&nbsp;
        <html:select name="_m_ppmFormS04" property="cboCategory" styleClass="cboCategory" onchange="changeSerive(this);" style="width:50%">
            <option value=""><bean:message key="screen.m_ppms02.selectone"/></option>
            <html:optionsCollection name="_m_ppmFormS04" property="cboCategoryList" label="svcGrpName" value="svcGrp"/>
        </html:select>
    </td>
  </tr>
  <tr>
    <td style="text-align: left;padding-left:10px"><bean:message key="screen.m_ppm_04.servicedesc"/> </td>
    <td >
        <bean:message key="screen.m_ppm_04.colon"/>&nbsp;<html:text property="tbxServiceDescr" style="width:100%;" name="_m_ppmFormS04"/>   
    </td> 
    <td style="text-align: left;padding-left:40px"><bean:message key="screen.m_ppm_04.service"/> </td>
    <td >
        <bean:message key="screen.m_ppm_04.colon"/>&nbsp;&nbsp;<html:select property="cboService" name="_m_ppmFormS04" styleClass="cboService" style="width:90%">
            <html:option value=""> <bean:message key="screen.m_ppms02.selectone"/> </html:option>
            <html:optionsCollection property="cboServiceList" name="_m_ppmFormS04" value="idService" label="svcDesc"/>
        </html:select>
    </td>
  </tr>
  <logic:notEmpty name="_m_ppmFormS04" property="lblCustomerType">
  <tr>
      <td style="text-align: right;">&nbsp;</td>
    <td >&nbsp;</td> 
    <td style="text-align: left;padding-left:40px"><bean:message key="screen.m_ppm_04.customertype"/> </td>
    <td >
        <bean:message key="screen.m_ppm_04.colon"/>&nbsp;&nbsp;<bean:write name="_m_ppmFormS04" property="lblCustomerTypeShow"/>
        <html:hidden name="_m_ppmFormS04" property="lblCustomerType" />
    </td>  
  </tr>
  </logic:notEmpty>
  <tr>
    <td class="colBottom" colspan="4">&nbsp;</td>
  </tr>
</table>
<table class="buttonGroup" cellpadding="0" cellspacing="0">
    <tr>
        <td>
            <button type="submit" onclick="document.forms[0].startIndex.value = 0;"><bean:message key="screen.m_ppm_04.button.search"/> </button>        
            <button onclick="resetAct()"><bean:message key="screen.m_ppm_04.button.reset"/> </button>
            <button id="insertBtn" onclick="insertAct('<%=request.getContextPath()%>')" disabled="disabled"><bean:message key="screen.m_ppm_04.button.insert"/></button>
        </td>
    </tr>
</table>
<table class="searchResultNo" cellpadding="0" cellspacing="0">
      <tr>
        <td>
            <bean:message key="screen.m_ppm_04.searchtitle"/> <bean:message key="screen.m_ppm_04.colon"/>
            <bean:write name="_m_ppmFormS04" property="totalCount"/>        
        </td>    
    </tr>
</table>
<table class="pageLink" cellpadding="0" cellspacing="0">
      <tr>
        <td>            
            <bean:message key="screen.m_ppm_04.gotopage"/><bean:message key="screen.m_ppm_04.colon"/>&nbsp;
            <ts:pageLinks id="userPageLinks"
                action="${pageContext.request.contextPath}/M_PPM/M_PPMS04Search.do"
                name="_m_ppmFormS04" 
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
        <td class="header" style="text-align: left;padding-left:10px;" width="5%">&nbsp;</td>
        <td class="header" style="text-align: left;padding-left:5px;" width="8%"><bean:message key="screen.m_ppm_04.no"/></td>
        <td class="header" style="text-align: left;padding-left:5px;" width="27%"><bean:message key="screen.m_ppm_04.serviceName"/></td>
        <td class="header" style="text-align: left;padding-left:5px;" width="45%"><bean:message key="screen.m_ppm_04.servicedesc"/></td>
        <td class="header" style="text-align: left;padding-left:5px;" width="15%"><bean:message key="screen.m_ppm_04.billCurrency"/></td>
    </tr>
    <logic:present name="_m_ppmFormS04" property="searchResult">
    <logic:iterate id="serviceSearchResult" name="_m_ppmFormS04" property="searchResult" indexId="index">
    <tr>
        <td style="text-align:left;left-padding:10px" valign="top"><input type="radio" name="idPlanGrp" onclick="planChecked();" value="<%=index+1 %>"/>
            <input type="hidden" name="<%=index+1 %>" value="${serviceSearchResult.gdcServiceName}"/>
            <input type="hidden" name="<%=index+1 %>" value="${serviceSearchResult.idPlan}"/>
            <input type="hidden" name="<%=index+1 %>" value="${serviceSearchResult.gdcServiceDescr}"/>
        </td>
        <td style="text-align: left;padding-left:5px;" valign="top">${_m_ppmFormS04.map.startIndex+index+1}</td>
        <td style="text-align: left;padding-left:5px;word-break:break-all" valign="top"><bean:write name="serviceSearchResult" property="gdcServiceName"/></td>
        <td style="text-align: left;padding-left:5px;word-break:break-all" valign="top"><bean:write name="serviceSearchResult" property="gdcServiceDescr"/></td>
        <td style="text-align: left;padding-left:5px;word-break:break-all" valign="top"><bean:write name="serviceSearchResult" property="billCurrency"/></td>
    </tr>
    <tr>
        <td style="border-bottom: #cfcfcf 1px solid" colspan="6"></td>
    </tr>
    </logic:iterate>
    </logic:present>
</table>
</ts:form>
</body>
</html>