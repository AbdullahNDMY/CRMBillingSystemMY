<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
    <style type="text/css">
      .optionDiv{
      margin-top: 3px;
      }
    </style>
    <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
       <script type="text/javascript">
           function doExit(){
              window.close();
           }
       </script>
</head>
<body style="margin-top:15px;margin-left:5px;margin-right:5px">
<ts:form action="/B_SSM_S03_Request_Process_Action.do" >
<div style="background-color: #f0f0f0;border-top: 2px solid #cacaca;border-bottom: 2px solid #cacaca;font-size: 16px;font-weight: bold;">
    <table cellpadding="0" cellspacing="0">
        <tr>
            <td><a href="##" onclick="javascript: window.history.go(-1);"><font color="blue"><bean:message key="B_SSM_S03_Page.Report.Notice.PrintReport" /></font></a></td>
            <td>&gt;&nbsp;<bean:message key="B_SSM_S03_Page.Report.Notice.Notice" /></td>
        </tr>
    </table>
</div>
<div style="margin-top:10px;margin-bottom:15px">
    <fieldset>
        <div><bean:message key="B_SSM_S03_Page.Report.Notice.Commence" /></div>
        <div class="optionDiv"><input type="radio" name="noticeMode" checked="checked" value="1"/><bean:message key="B_SSM_S03_Page.Report.Notice.NextDay" /></div>
        <div class="optionDiv"><input type="radio" name="noticeMode" value="2"/><bean:message key="B_SSM_S03_Page.Report.Notice.CurrentDay" /></div>
        <div class="optionDiv" style="white-space: nowrap;"><input type="radio" name="noticeMode" value="3"/><bean:message key="B_SSM_S03_Page.Report.Notice.NextMonth" /></div>
        
        <div style="margin-top:15px"><bean:message key="B_SSM_S03_Page.Report.Notice.serviceProvided" /></div>

        <logic:iterate name="B_SSM_S03_Page_Form" property="serviceList" id="service" indexId="index">
         <div style="margin-top:5px;margin-left:10px">
          <input type="hidden" name="selectedServiceIds[<%=index %>]" value="${service.serviceId}"/>
          <textarea name="serviceDescs[<%=index %>]" style="width:99%;overflow-y:visible;text-align:left"><bean:write name="service" property="serviceDesc"/></textarea>
         </div>
        </logic:iterate>
    </fieldset>
</div>
<bean:size name="B_SSM_S03_Page_Form" property="serviceList" id="listSize" />
    <logic:greaterThan name ="listSize" value="1">
        <div style="margin-left:35px;margin-bottom:15px;color:blue">
        <bean:message key="B_SSM_S03_Page.Report.Notice.Remark"/>
        </div>
    </logic:greaterThan>
<div>
    <input type="submit" name="btnNext" value="Next" onclick="javascript:showLoadingTipWindow();" style="width:70px;"/>
    <input type="button" name="btnExit" value="Exit" onclick="javascript: doExit();" style="width:70px;"/>
</div>
<div class="B_SSM_S03_Page_Form_Errors" id="errorInfo">
    <ts:errors/>    
</div>
<%-- hidden area --%>
    <html:hidden name="B_SSM_S03_Page_Form" property="processMode" />
    <html:hidden name="B_SSM_S03_Page_Form" property="customerName" />
    <html:hidden name="B_SSM_S03_Page_Form" property="customerID" />
    <html:hidden name="B_SSM_S03_Page_Form" property="customerPlanID" />
    <html:hidden name="B_SSM_S03_Page_Form" property="subscriptionID" />
    <html:hidden name="B_SSM_S03_Page_Form" property="logonUserID" />
    <html:hidden name="B_SSM_S03_Page_Form" property="serviceName" />
    <html:hidden name="B_SSM_S03_Page_Form" property="reportTemplatePath" />
    <html:hidden name="B_SSM_S03_Page_Form" property="reportLogoPath" />
    <html:hidden name="B_SSM_S03_Page_Form" property="reportErrorStatus" />
    <html:hidden name="B_SSM_S03_Page_Form" property="reportPath" />
    <html:hidden name="B_SSM_S03_Page_Form" property="subReportPath" />
    <html:hidden name="B_SSM_S03_Page_Form" property="addressType" />
    <html:hidden name="B_SSM_S03_Page_Form" property="customerType" />
    <html:hidden name="B_SSM_S03_Page_Form" property="accMgrPrim" />
    <logic:notEmpty name="B_SSM_S03_Page_Form" property="serviceIDs">
        <logic:iterate name="B_SSM_S03_Page_Form" property="serviceIDs" id="serviceID" indexId="index">
            <input type="hidden" name="serviceIDs[<%=index %>]" value="<%=serviceID %>">
        </logic:iterate>
    </logic:notEmpty>
    <logic:notEmpty name="B_SSM_S03_Page_Form" property="customerPlanLinkIDs">
        <logic:iterate name="B_SSM_S03_Page_Form" property="customerPlanLinkIDs" id="customerPlanLinkID" indexId="index">
            <input type="hidden" name="customerPlanLinkIDs[<%=index %>]" value="<%=customerPlanLinkID %>">
        </logic:iterate>
    </logic:notEmpty>
    <logic:notEmpty name="B_SSM_S03_Page_Form" property="selectedServices">
        <logic:iterate name="B_SSM_S03_Page_Form" property="selectedServices" id="selectedService" indexId="index">
            <input type="hidden" name="selectedServices[<%=index %>]" value="<%=selectedService %>">
        </logic:iterate>
    </logic:notEmpty>
<%-- hidden area --%>
</ts:form>
</body>
</html>