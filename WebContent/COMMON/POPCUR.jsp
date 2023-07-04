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
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
    <head>
        <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
        <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/COMMON/css/popup.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/COMMON/js/popup.js"></script>
           <title></title>
    </head>
    <ts:body styleClass="popupCur">
        <ts:form action="/POPUP_COMMON_DSP">
            <table border="0" cellpadding="0" cellspacing="0" style="margin-top:20px;margin-left:5px;margin-bottom:5px;margin-left:20px;">
                <tr>
                    <td valign="top">
                        <img src="./images/alert.png" width="32" height="32"/>
                    </td>
                    <td>
                        Please make sure that the currency exchange rate being<br/>
                        used have bean created for the month: <bean:write property="monthYear" name="_popupCommonCurForm"/>.<br/>
                        The following currency exchange rate is already available<br/>
                        on Currency Maintenance.
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>
                        <div id="tableHeader">
                            <table class="tableHeaderClass" border="0" cellpadding="0" cellspacing="0" style="width:350px;text-align:center;">
                                <col width="30%"/>
                                <col width="35%"/>
                                <col width="35%"/>
                                <tr style="background-color:#5B5BFB">
                                    <td>
                                        <bean:message key="screen.POPCUR.currencyCode"/>
                                    </td>
                                    <td style="border-left:0;">
                                        <bean:message key="screen.POPCUR.convertFrom"/>
                                    </td>
                                    <td style="border-left:0;">
                                        <bean:message key="screen.POPCUR.convertTo"/>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div id="tableData" style="height:100px;overflow-y: auto;">
                            <table class="tableDataClass" border="0" cellpadding="0" cellspacing="0" style="width:350px;text-align:center;">
                                <col width="30%"/>
                                <col width="35%"/>
                                <col width="35%"/>
                                <logic:present property="currencyList" name="_popupCommonCurForm">
                                    <logic:iterate id="currencyInfo" property="currencyList" name="_popupCommonCurForm" indexId="i">
                                        <tr style="background-color:#E8EEF7">
                                            <td style="border-top:0;">
                                                <bean:write property="CUR_CODE" name="currencyInfo"/>
                                            </td>
                                            <td style="border-left:0;border-top:0;">
                                                <bean:write property="RATE_TO" name="currencyInfo"/>
                                            </td>
                                            <td style="border-left:0;border-top:0;">
                                                <bean:write property="RATE_FROM" name="currencyInfo"/>
                                            </td>
                                        </tr>
                                    </logic:iterate>
                                </logic:present>
                            </table>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>
                        Do you still want to proceed?
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>
                        Click Yes to generate billing, No to abort process.
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>
                        <input type="button" name="btnYes" value="Yes" onclick="javascript: doYes();" style="width:70px;"/>
                        <input type="button" name="btnNo" value="No" onclick="javascript: doNo();" style="width:70px;"/>
                    </td>
                </tr>
            </table>
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
    </ts:body>
</html:html>