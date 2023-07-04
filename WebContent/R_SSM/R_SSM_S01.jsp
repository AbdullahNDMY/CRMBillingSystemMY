<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<%@ taglib uri="/WEB-INF/bs" prefix="bs" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/R_SSM/css/r_ssm.css"   />
    <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/R_SSM/js/r_ssm_s01.js"></script>
    <title><bean:message key="screen.r_ssm.title"/></title>
    <script type="text/javascript">
        function searchClick() {
            var startIndex = document.getElementById("startIndex");
            if(startIndex!=null && startIndex!=undefined) {
                startIndex.value="0";
            }
        }
    </script>
</head>
<body id="r" onload="initMain();">
<ts:form action="/R_SSM_R01DSP" >
    <t:defineCodeList id="LIST_PLANSTATUS"/>
    <t:defineCodeList id="LIST_BILLINGSTATUS"/>
    <t:defineCodeList id="LIST_RATEMODE"/>
    <input type="hidden" id="hiddenContextPath" value="${pageContext.request.contextPath}" />
    <input type="hidden" id="hiddenAccessType" value="${accessType}"/>
    <input type="hidden" id="initFlg" value="${initFlg}"/>
    <input type="hidden" name="customerPlan.idCustPlan" value="" id="idCustPlan">
    <div class="pageWidth">
        <h1 class="title"><bean:message key="screen.r_ssm.title"/></h1>
        <div class="section" style="border-top:2px solid #cecece;border-bottom:2px solid #cecece;padding:5px 0px;margin-top:-5px;">
            <table cellpadding="0" cellspacing="0">
                <tr>
                    <td align="left">
                        <html:checkbox property="chkErrorCodeAll" name="_r_ssmForm" value="0" styleClass="chkErrorCodeAllStyle"></html:checkbox>
                    </td>
                    <td align="left">
                        <bean:message key="screen.r_ssm.errorCodeDesc"/>
                    </td>
                </tr>
                <tr>
                    <td align="left">
                        <html:checkbox property="chkErrorCode1" name="_r_ssmForm" value="1" styleClass="chkErrorCodeStyle"></html:checkbox>
                    </td>
                    <td align="left">
                        <bean:message key="screen.r_ssm.errorCode_1"/>
                    </td>
                </tr>
                <tr>
                    <td align="left">
                        <html:checkbox property="chkErrorCode2" name="_r_ssmForm" value="2" styleClass="chkErrorCodeStyle"></html:checkbox>
                    </td>
                    <td align="left">
                        <bean:message key="screen.r_ssm.errorCode_2"/>
                    </td>
                </tr>
                <tr>
                    <td align="left">
                        <html:checkbox property="chkErrorCode3" name="_r_ssmForm" value="3" styleClass="chkErrorCodeStyle"></html:checkbox>
                    </td>
                    <td align="left">
                        <bean:message key="screen.r_ssm.errorCode_3"/>
                    </td>
                </tr>
                <tr>
                    <td align="left">
                        <html:checkbox property="chkErrorCode4" name="_r_ssmForm" value="4" styleClass="chkErrorCodeStyle"></html:checkbox>
                    </td>
                    <td align="left">
                        <bean:message key="screen.r_ssm.errorCode_4"/>
                    </td>
                </tr>
                <tr>
                    <td align="left">
                        <html:checkbox property="chkErrorCode5" name="_r_ssmForm" value="5" styleClass="chkErrorCodeStyle"></html:checkbox>
                    </td>
                    <td align="left">
                        <bean:message key="screen.r_ssm.errorCode_5"/>
                    </td>
                </tr>
                <tr>
                    <td align="left">
                        <html:checkbox property="chkErrorCode6" name="_r_ssmForm" value="6" styleClass="chkErrorCodeStyle"></html:checkbox>
                    </td>
                    <td align="left">
                        <bean:message key="screen.r_ssm.errorCode_6"/>
                    </td>
                </tr>
                <tr>
                    <td align="left">
                        <html:checkbox property="chkErrorCode7" name="_r_ssmForm" value="7" styleClass="chkErrorCodeStyle"></html:checkbox>
                    </td>
                    <td align="left">
                        <bean:message key="screen.r_ssm.errorCode_7"/>
                    </td>
                </tr>
                <tr>
                    <td align="left">
                        <html:checkbox property="chkErrorCode8" name="_r_ssmForm" value="8" styleClass="chkErrorCodeStyle"></html:checkbox>
                    </td>
                    <td align="left">
                        <bean:message key="screen.r_ssm.errorCode_8"/>
                    </td>
                </tr>
                <tr>
                    <td align="left">
                        <html:checkbox property="chkErrorCode9" name="_r_ssmForm" value="9" styleClass="chkErrorCodeStyle"></html:checkbox>
                    </td>
                    <td align="left">
                        <bean:message key="screen.r_ssm.errorCode_9"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <br/>
    <div class="pageWidth">
        <html:submit property="forward_search" onclick="searchClick();showLoadingTipWindow();"><bean:message key='screen.r_ssm.checkStart'/></html:submit>
        <bs:buttonLink action="/R_SSM_R01BL" value="Reset"/>
        <c:if test="${_r_ssmForm.map.accessType eq '1' or _r_ssmForm.map.accessType eq '2'}">
            <c:if test="${_r_ssmForm.map.totalRow > 0}">
                <html:submit property="forward_export" onclick="showLoadingTipWindow();" ><bean:message key="screen.r_ssm.exportResult"/></html:submit>
            </c:if>
            <c:if test="${_r_ssmForm.map.totalRow == 0 or _r_ssmForm.map.totalRow == null}">
                <html:submit property="forward_export" disabled="true"><bean:message key="screen.r_ssm.exportResult"/></html:submit>
            </c:if>
        </c:if>
    </div>
    <br/>
    <div class="pageWidth">
        <div class="section">
            <div class="group result">
                <h2><bean:message key="screen.r_ssm.searchFound" /> <bean:message key="screen.r_ssm.colon"/>${_r_ssmForm.map.totalRow}</h2>
            </div>
            <div class="pageLink">
                <bean:message key="screen.r_ssm.gotoPage"/> <bean:message key="screen.r_ssm.colon"/>
                <ts:pageLinks 
                    id="curPageLinks"
                    action="${pageContext.request.contextPath}/R_SSM/R_SSM_R02BL.do" 
                    name="_r_ssmForm" 
                    rowProperty="row"
                    totalProperty="totalRow" 
                    indexProperty="startIndex"
                    currentPageIndex="now" 
                    totalPageCount="total"
                    submit="true"/>
                <logic:present name="curPageLinks">  
                    <bean:write name="curPageLinks" filter="false"/>
                </logic:present>
            </div>
        </div>
        <div style="width:100%">
        <table id="tableResultSearchPlan" class="resultInfo" cellpadding="0" cellspacing="0" width="100%">
            <colgroup>
                <col width="5%">
                <col width="10%">
                <col width="15%">
                <col width="19%">
                <col width="10%">
                <col width="10%">
                <col width="10%">
                <col width="10%">
                <col width="10%">
                <col width="1%">
            </colgroup>
            <tr class="header">
                <td class="headerLeft" nowrap="nowrap"><bean:message key="screen.r_ssm.header.no"/></td>
                <td class="headerLeft" nowrap="nowrap"><bean:message key="screen.r_ssm.header.custId"/></td>
                <td class="headerLeft" nowrap="nowrap"><bean:message key="screen.r_ssm.header.custName"/></td>
                <td class="headerLeft" nowrap="nowrap">
                    <bean:message key="screen.r_ssm.header.subId"/><br/>
                    <bean:message key="screen.r_ssm.header.desc"/>
                </td>
                <td class="headerLeft" nowrap="nowrap"><bean:message key="screen.r_ssm.header.serviceStart"/></td>
                <td class="headerLeft" nowrap="nowrap"><bean:message key="screen.r_ssm.header.serviceEnd"/></td>
                <td class="headerLeft" nowrap="nowrap"><bean:message key="screen.r_ssm.header.serviceStatus"/></td>
                <td class="headerLeft" nowrap="nowrap"><bean:message key="screen.r_ssm.header.billingStatus"/></td>
                <td class="headerLeft" nowrap="nowrap"><bean:message key="screen.r_ssm.header.errorCode"/></td>
                <td class="headerLeft" nowrap="nowrap"><bean:message key="screen.r_ssm.blank"/></td>
            </tr>
            <logic:notEmpty name="_r_ssmForm" property="searchResult">
            <logic:iterate id="plan_h" name="_r_ssmForm" property="searchResult" indexId="i">
                <tbody class="trResultSearchPlan">
                    <tr class="detail">
                        <td nowrap="nowrap" class="headerBorder">${i + 1 + _r_ssmForm.map.startIndex}</td>
                        <td nowrap="nowrap" class="headerBorder"><bean:write name="plan_h" property="ID_CUST"/>&nbsp;</td>
                        <td nowrap="nowrap" class="headerBorder"><bean:write name="plan_h" property="CUST_NAME"/>&nbsp;</td>
                        <td nowrap="nowrap" class="headerBorder"><bean:write name="plan_h" property="ID_SUB_INFO"/>&nbsp;</td>
                        <td nowrap="nowrap" class="headerBorder"></td>
                        <td nowrap="nowrap" class="headerBorder"></td>
                        <td nowrap="nowrap" class="headerBorder"></td>
                        <td nowrap="nowrap" class="headerBorder"></td>
                        <td nowrap="nowrap" class="headerBorder"></td>
                        <td nowrap="nowrap" class="headerBorder"></td>
                    </tr>
                    <!-- SUB PLAN -->
                    <logic:notEmpty name="plan_h" property="SUB_PLAN">
                    <logic:iterate id="plan_d" name="plan_h" property="SUB_PLAN" indexId="j">
                        <tr class="hasSubPlan">
                            <td></td>
                            <td></td>
                            <td></td>
                            <td colspan="1" nowrap="nowrap">
                                <span class="spanToggleClick">...</span>
                                <a href="javascript: viewCustomerPlan('<bean:write name="plan_h" property="ID_CUST_PLAN"/>')"><bean:write name="plan_d" property="BILL_DESC"/></a>
                            </td>
                            <td nowrap="nowrap" class="dateStyle">&nbsp;<bean:write name="plan_d" property="SVC_START"/>&nbsp;</td>
                            <td nowrap="nowrap" class="dateStyle">&nbsp;<bean:write name="plan_d" property="SVC_END"/>&nbsp;</td>
                            <td nowrap="nowrap">
                                <t:writeCodeValue codeList="LIST_PLANSTATUS" name="plan_d" property="SERVICES_STATUS"/>
                            </td>
                            <td nowrap="nowrap">
                                <t:writeCodeValue codeList="LIST_BILLINGSTATUS" name="plan_d" property="BILLING_STATUS"/>
                            </td>
                            <td nowrap="nowrap"><bean:write name="plan_d" property="ERROR_CODE"/>&nbsp;</td>
                            <td nowrap="nowrap"></td>
                        </tr>
                        <!-- SUB PLAN LINK -->
                        <logic:notEmpty name="plan_d" property="SUB_PLAN_LINK">
                            <tr class="spanTogglePlace">
                                <td></td>
                                <td></td>
                                <td colspan="6" style="padding:5px;">
                                    <table cellpadding="0" cellspacing="0" border="0" style="width:100%;height:100%;color: #a04c51;font-size:15px;font-family:'Calibri';border:1px solid #CEE7FF;">
                                        <tr>
                                            <td style="width:40%;padding-left:5px;background-color: #CEE7FF;"><bean:message key="screen.r_ssm.label.detail.itemDescription" /></td>
                                            <td style="width:10%;padding-left:5px;background-color: #CEE7FF;"><bean:message key="screen.r_ssm.label.detail.rateMode" /></td>
                                            <td style="width:10%;padding-left:5px;background-color: #CEE7FF;"><bean:message key="screen.r_ssm.label.detail.quantity" /></td>
                                            <td style="width:10%;padding-left:5px;background-color: #CEE7FF;"><bean:message key="screen.r_ssm.label.detail.price" /></td>
                                            <td style="width:15%;padding-left:5px;background-color: #CEE7FF;"><bean:message key="screen.r_ssm.label.detail.amount" /></td>
                                            <td nowrap="nowrap"></td>
                                        </tr>
                                        <logic:iterate id="plan_link" name="plan_d" property="SUB_PLAN_LINK">
                                            <tr>
                                                <td style="padding-left:5px;border-top:1px solid #CEE7FF;"><bean:write name="plan_link" property="ITEM_DESC"/></td>
                                                <td style="padding-left:5px;border-top:1px solid #CEE7FF;"><t:writeCodeValue codeList="LIST_RATEMODE" key="${plan_link.RATE_MODE}" /></td>
                                                <td style="padding-right:25px;text-align:right;border-top:1px solid #CEE7FF;"><fmt:formatNumber value="${plan_link.QUANTITY}" pattern="#,###,###"/></td>
                                                <td style="padding-right:25px;text-align:right;border-top:1px solid #CEE7FF;"><fmt:formatNumber value="${plan_link.UNIT_PRICE}" pattern="#,###,###.00"/></td>
                                                <td style="padding-right:60px;text-align:right;border-top:1px solid #CEE7FF;"><fmt:formatNumber value="${plan_link.TOTAL_AMOUNT}" pattern="#,###,###.00"/></td>
                                                <td nowrap="nowrap"></td>
                                            </tr>
                                        </logic:iterate>
                                    </table>
                                </td>
                                <td></td>
                                <td></td>
                            </tr>
                        </logic:notEmpty><!-- END SUB PLAN LINK -->
                    </logic:iterate><!-- END SUB PLAN -->
                    </logic:notEmpty>
                </tbody>
            </logic:iterate><!-- END PLAN -->
            </logic:notEmpty>
        </table>
        </div>
          
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
</body>
</html>