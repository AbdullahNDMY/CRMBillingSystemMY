<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="nttdm.bsys.common.util.CommonUtils"%>
<%@ page import="nttdm.bsys.common.fw.BillingSystemUserValueObject"%> 
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/common.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
    <link href="${pageContext.request.contextPath}/M_PBM/css/m_pbms01.css" rel="stylesheet" type="text/css" /> 
    <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/messageBox.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/common.js"></script>
    <script type="text/javascript" src="js/m_pbms01.js"></script>
    <script >
        function do_reset1(){
            document.location.href="${pageContext.request.contextPath}/M_PBM/M_PBMS01BL.do";
        }
    </script>    
    <title><bean:message key="screen.m_pbm.title"/></title>
</head>
<body id="m" onload="initMain();">
    <table class="subHeader" cellpadding="0" cellspacing="0">
        <tr>
            <td><bean:message key="screen.m_pbm.title"/></td>
        </tr>
    </table>
    <br class="lineSeperator"/>
    <t:defineCodeList id="LIST_UOM" />
    <t:defineCodeList id="LIST_CODE_VALUE" />

    <ts:form action="/M_PBMS01DSP">
        <table class = "searchCriteria" >
            <tr>
                <td><bean:message key="screen.m_pbm.batch"/><font color='red'> <bean:message key="screen.m_pbm.mandatory"/></font> :
                    <t:defineCodeList id="LIST_BATCH" />
                    <html:select styleId="cboBatchName" name="_m_pbmForm" property="id_batch" style="width:250px"  >
                        <option value="0"><bean:message key="screen.m_pbm.Empty"/></option>
                        <html:options collection="LIST_BATCH" property="id" labelProperty="name"/>
                    </html:select>
                </td>
            </tr>
        </table>
        <br class="lineSeperator"/>
        <html:button property="actSearch" onclick="do_search();" styleClass="button"><bean:message key="screen.m_pbm.search"/></html:button>
        <html:button property="actReset" onclick="do_reset1();" styleClass="button"><bean:message key="screen.m_pbm.reset"/></html:button>
        <%
            BillingSystemUserValueObject uvo = (BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT");
            String accessRight = CommonUtils.getAccessRight(uvo, "M-PBM");
            pageContext.setAttribute("accessRightBean", accessRight);
        %>
        <c:if test="${accessRightBean eq '2'}">
            <html:button property="actNew" onclick="do_new();" styleClass="button"><bean:message key="screen.m_pbm.new"/></html:button>
        </c:if>
        <ts:submit styleId="forward_search" value='search' property="forward_search" style="visibility:hidden" styleClass="button"/>
        <ts:submit styleId="forward_reset" value='reset' property="forward_reset" style="visibility:hidden" styleClass="button"/>
        <br class="lineSeperator"/>
        <br/>
        <%-- New Mode--%>
        <logic:equal value="new" name="_m_pbmForm" property="mode">
            <div id="new_view">
                <table class = "generalInfo">
                    <tr>
                        <td><bean:message key="screen.m_pbm.general"/></td>
                    </tr>
                </table>
                <div style="border: #cecece 2px solid;padding:5px;margin-top:5px;width:100%;">
                    <table id="batch_new_criteria">
                        <tr>
                            <td align="right"><bean:message key="screen.m_pbm.batch" />:</td>
                            <td><html:select styleId="cboBatchName_new" name="_m_pbmForm" property="id_batch_new"
                                    style="width:250px" onchange="onPlanChange()">
                                    <option value="0">
                                        <bean:message key="screen.m_pbm.Empty" />
                                    </option>
                                    <html:options collection="LIST_BATCH" property="id" labelProperty="name" />
                                </html:select></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td align="right"><bean:message key="screen.m_pbm.serviceName"/> :</td>
                            <td>
                                <button id="btnAddService" style="width:25px;"><img src="<%=request.getContextPath()%>/image/search.png"></button>
                                <html:hidden name="_m_pbmForm" property="plan_id_new" styleId="plan_id_new"/>
                                <label id="plan_name"><bean:write name="_m_pbmForm" property="plan_name"/></label>
                            </td>
                            <td style="padding-left:100px;"><label id="plan_desc"><bean:write name="_m_pbmForm" property="plan_desc"/></label></td>
                        </tr>
                    </table>
                    <logic:notEmpty name="_m_pbmForm" property="new_info">
                    <table id="new_table"" class="searchResult">
                    <tr class="header">
                        <td></td>
                        <td></td>
                        <td><bean:message key="screen.m_pbm.col_subPlanAndOptionName"/></td>
                        <td><bean:message key="screen.m_pbm.col_service_group"/></td>
                        <td><bean:message key="screen.m_pbm.col_rate_type"/></td>
                        <td><bean:message key="screen.m_pbm.col_mode"/></td>
                        <td style="text-align:right;padding-right:20px;"><bean:message key="screen.m_pbm.col_rate"/><bean:write name="_m_pbmForm" property="bill_currency"/><bean:message key="screen.m_pbm.col_rate1"/></td>
                        <%-- 
                        <logic:equal value="DU" name="_m_pbmForm" property="id_batch_new">
                            <td><bean:message key="screen.m_pbm.col_usage_base"/></td>
                        </logic:equal>
                        <logic:equal value="AD" name="_m_pbmForm" property="id_batch_new">
                            <td><bean:message key="screen.m_pbm.col_usage_base"/></td>
                        </logic:equal>
                        <logic:equal value="IP" name="_m_pbmForm" property="id_batch_new">
                            <td><bean:message key="screen.m_pbm.col_code"/></td>
                        </logic:equal>
                        <logic:notEqual value="DU" name="_m_pbmForm" property="id_batch_new">
                            <logic:notEqual value="AD" name="_m_pbmForm" property="id_batch_new">
                                <logic:notEqual value="IP" name="_m_pbmForm" property="id_batch_new">
                                    <td></td>
                                </logic:notEqual>
                            </logic:notEqual>
                        </logic:notEqual>
                        --%>
                    </tr>
                    <logic:iterate id="row" name="_m_pbmForm" property="new_info" indexId="row_num" >
                        <tr id="new_row_${row_num}">
                            <bean:define id="tmpBatchId" name="_m_pbmForm" property="id_batch_new"/>
                            <td class="col0">
                                <input type="checkbox" id="chkCheckExistNew_${tmpBatchId}" name="new_checkbox" 
                                    <logic:iterate id="plan_batch_map" name="_m_pbmForm" property="plan_batch">
                                        <logic:equal value="${row.ID_PLAN_GRP}" name="plan_batch_map" property="ID_PLAN_GRP">checked="checked"</logic:equal>
                                    </logic:iterate> 
                                    onclick="on_checkedChanged2('${tmpBatchId}','new_row_${row_num}', '${row.ITEM_TYPE}', '${row.RATE_TYPE}')" /></td>
                            <td class="hide"><bean:write name="row" property="ID_PLAN_GRP"/></td>
                            <td class="col1">
                                <t:writeCodeValue codeList="LIST_ITEMTYPE" key="${row.ITEM_TYPE}"></t:writeCodeValue>
                            </td>
                            <td class="col2"><bean:write name="row" property="ITEM_GRP_NAME"/></td> 
                            <td class="col3">
                                <bean:write name="row" property="SVC_GRP_NAME"/>
                            </td>
                            <td class="col4">
                                <t:writeCodeValue codeList="LIST_RATETYPE" key="${row.RATE_TYPE}"></t:writeCodeValue>
                            </td>
                            <td class="col5">
                                <t:writeCodeValue codeList="LIST_RATEMODE" key="${row.RATE_MODE}"></t:writeCodeValue>
                            </td>
                            <td class="col6">
                                <fmt:formatNumber value="${row.RATE}" pattern="#,##0.00"/>
                            </td>
                            <%-- 
                            <c:choose>
                                <c:when test="${_m_pbmForm.map.id_batch_new eq 'DU' && row.ITEM_TYPE eq 'S' && row.RATE_TYPE eq 'BA'}">
                                <td><div id="hide_col_${row_num}" class="hide" >
                                        <input class="txtUsageBase" type="text" id="txtUB" value="<bean:write name="row" property="USAGE_BASE"/>" />
                                        <html:select styleId="cboUOM" name="_m_pbmForm" property="uom" style="width:60px" >
                                            <html:options collection="LIST_UOM" property="id" labelProperty="name"/>
                                        </html:select>
                                        </div>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <c:choose>
                                        <c:when test="${_m_pbmForm.map.id_batch_new eq 'AD' && row.ITEM_TYPE eq 'S' && row.RATE_TYPE eq 'BA'}">
                                            <td><div id="hide_col_${row_num}" class="hide">
                                                <input class="txtUsageBase" type="text" id="txtUB" value="<bean:write name="row" property="USAGE_BASE"/>" />
                                                <html:select styleId="cboUOM" name="_m_pbmForm"
                                                    property="uom" style="width:60px"  >
                                                    <html:options collection="LIST_UOM" property="id" labelProperty="name"/>
                                                </html:select>
                                                </div>
                                            </td>
                                           </c:when>
                                        <c:otherwise>
                                        <c:choose>
                                                <c:when test="${_m_pbmForm.map.id_batch_new eq 'IP' && row.ITEM_TYPE eq 'O' && row.RATE_TYPE eq 'EX'}">
                                                    <td ><div id="hide_col_${row_num}" class="hide"> 
                                                        <html:select styleId="cboCodeValue" name="_m_pbmForm"
                                                            property="code_value" style="width:60px" >
                                                            <html:options collection="LIST_CODE_VALUE" property="id" labelProperty="name"/>
                                                        </html:select>
                                                        </div>
                                                    </td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td></td>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:otherwise>
                                    </c:choose>
                                </c:otherwise>
                            </c:choose>
                            --%>
                        </tr>
                    </logic:iterate>
                    <bean:define id="tmpBatchId" name="_m_pbmForm" property="id_batch_new"/>
                </table>
                <logic:equal value="MH" name="_m_pbmForm" property="id_batch_new">
                    <br/>
                    <table id="new_OptSvc_table" class="searchResult">
                        <col width="35px"/>
                        <col width="250px"/>
                        <col width=""/>
                        <tr class="header">
                            <td></td>
                            <td colspan="2"><bean:message key="screen.m_pbm.optionName"/></td>
                        </tr>
                        <%-- additional Mail account --%>
                        <tr>
                            <td class="col0">
                                <html:checkbox name="_m_pbmForm"  property="chkCheckAMA" styleId="chkCheckAMA" value="1"/>
                            </td>
                            <td><bean:message key="screen.m_pbm.col_AMA"/></td>
                            <td>
                                <html:select styleId="cboOptSvcAMA" name="_m_pbmForm" property="cboOptSvcAMA">
                                    <option value=""><bean:message key="screen.m_pbm.Empty"/></option>
                                    <html:optionsCollection name="_m_pbmForm" property="new_OptSvcinfo" label="ITEM_GRP_NAME" value="ID_PLAN_GRP"/>
                                </html:select>
                            </td>
                        </tr>
                        <%-- Mailbox(Qty) --%>
                        <tr>
                            <td class="col0">
                                <html:checkbox name="_m_pbmForm"  property="chkCheckAMQ" styleId="chkCheckAMQ" value="1"/>
                            </td>
                            <td><bean:message key="screen.m_pbm.col_AMQ"/></td>
                            <td>
                                <html:select styleId="cboOptSvcAMQ" name="_m_pbmForm" property="cboOptSvcAMQ">
                                    <option value=""><bean:message key="screen.m_pbm.Empty"/></option>
                                    <html:optionsCollection name="_m_pbmForm" property="new_OptSvcinfo" label="ITEM_GRP_NAME" value="ID_PLAN_GRP"/>
                                </html:select>
                            </td>
                        </tr>
                        <%-- Virus Scan --%>
                        <tr>
                            <td class="col0">
                                <html:checkbox name="_m_pbmForm"  property="chkCheckVRS" styleId="chkCheckVRS" value="1"/>
                            </td>
                            <td><bean:message key="screen.m_pbm.col_VRS"/></td>
                            <td>
                                <html:select styleId="cboOptSvcVRS" name="_m_pbmForm" property="cboOptSvcVRS">
                                    <option value=""><bean:message key="screen.m_pbm.Empty"/></option>
                                    <html:optionsCollection name="_m_pbmForm" property="new_OptSvcinfo" label="ITEM_GRP_NAME" value="ID_PLAN_GRP"/>
                                </html:select>
                            </td>
                        </tr>
                        <%-- Anti Spam --%>
                        <tr>
                            <td class="col0">
                                <html:checkbox name="_m_pbmForm"  property="chkCheckASP" styleId="chkCheckASP" value="1"/>
                            </td>
                            <td><bean:message key="screen.m_pbm.col_ASP"/></td>
                            <td>
                                <html:select styleId="cboOptSvcASP" name="_m_pbmForm" property="cboOptSvcASP">
                                    <option value=""><bean:message key="screen.m_pbm.Empty"/></option>
                                    <html:optionsCollection name="_m_pbmForm" property="new_OptSvcinfo" label="ITEM_GRP_NAME" value="ID_PLAN_GRP"/>
                                </html:select>
                            </td>
                        </tr>
                        <%-- Junk Management --%>
                        <tr>
                            <td class="col0">
                                <html:checkbox name="_m_pbmForm"  property="chkCheckJMG" styleId="chkCheckJMG" value="1"/>
                            </td>
                            <td><bean:message key="screen.m_pbm.col_JMG"/></td>
                            <td>
                                <html:select styleId="cboOptSvcJMG" name="_m_pbmForm" property="cboOptSvcJMG">
                                    <option value=""><bean:message key="screen.m_pbm.Empty"/></option>
                                    <html:optionsCollection name="_m_pbmForm" property="new_OptSvcinfo" label="ITEM_GRP_NAME" value="ID_PLAN_GRP"/>
                                </html:select>
                            </td>
                        </tr>
                    </table>
                </logic:equal>
                <hr style="background:#cecece;margin-top: 15px;margin-bottom:5px;"/>
                <html:button property="actSave" onclick="do_save_new('${tmpBatchId}')" styleClass="button">
                    <bean:message key="screen.m_pbm.save"/>
                </html:button>
                </logic:notEmpty>  
                </div>
            </div>
        </logic:equal>
        <%-- Search Mode --%>
        <logic:equal value="search" name="_m_pbmForm" property ="mode">
            <div id="searchResult" >
                <table class="generalInfo">
                    <tr>
                        <td><bean:message key="screen.m_pbm.general"/></td>
                    </tr>
                </table>
                <!-- page index -->
                <table id="paging" style="background-color:pink;margin-top:3px;width:100%;">
                    <tr>
                        <td align="left">
                            <bean:message key="screen.m_pbm.go"/>
                              <ts:pageLinks    id="curPageLinks"
                                    action="${pageContext.request.contextPath}/M_PBM/M_PBMS01SearchBL.do" 
                                    name="_m_pbmForm" 
                                    rowProperty="rp"
                                    indexProperty="ip"
                                    totalProperty="total_plan"
                                    forward="true"
                                    submit="true"/>
                                <!-- indexProperty="startIndex"  -->
                            <logic:present name="curPageLinks">  
                                <bean:write name="curPageLinks" filter="false"/>
                            </logic:present>
                        </td>
                        <td align="right">
                            <bean:message key="screen.m_pbm.total"/> <bean:write name="_m_pbmForm" property="total_plan"/>
                        </td>                
                    </tr>
                </table>
                    <div style="border:2px solid #cecece;padding:5px 5px 10px 5px;width: 100%">   
                        <!-- batch name plan name -->
                        <table id="batch_view_criteria" class="show" style="border-style:none;">
                            <tr>
                                <td align="right"><bean:message key="screen.m_pbm.batch"/> :</td>
                                <td><bean:write name="_m_pbmForm" property="batch_name"/> </td>
                                <td></td>
                            </tr>
                            <tr >
                                <td align="right"><bean:message key="screen.m_pbm.serviceName"/> :</td>
                                <td><bean:write name="_m_pbmForm" property="plan_name"/> </td>
                                <td style="padding-left:100px;"><bean:write name="_m_pbmForm" property="plan_desc"/> </td>                        
                            </tr>
                            
                        </table>                
                        <!--  search result -->
                        <table id="search_mode" class="searchResult">
                            <tr class="header">
                                <td></td>
                                <td></td>
                                <td><bean:message key="screen.m_pbm.col_subPlanAndOptionName"/></td>
                                <td><bean:message key="screen.m_pbm.col_service_group"/></td>
                                <td><bean:message key="screen.m_pbm.col_rate_type"/></td>
                                <td><bean:message key="screen.m_pbm.col_mode"/></td>
                                <td style="text-align:right;padding-right:20px;"><bean:message key="screen.m_pbm.col_rate"/><bean:write name="_m_pbmForm" property="bill_currency"/><bean:message key="screen.m_pbm.col_rate1"/></td>
                                <%-- 
                                <logic:equal value="DU" name="_m_pbmForm" property="id_batch">
                                    <td><bean:message key="screen.m_pbm.col_usage_base"/></td>
                                </logic:equal>
                                <logic:equal value="AD" name="_m_pbmForm" property="id_batch">
                                    <td><bean:message key="screen.m_pbm.col_usage_base"/></td>
                                </logic:equal>
                                <logic:equal value="IP" name="_m_pbmForm" property="id_batch">
                                    <td><bean:message key="screen.m_pbm.col_code"/></td>
                                </logic:equal>
                                <td/>
                                --%>
                            </tr>
                            <c:set var="id_plan_grp"/>
                            <logic:notEmpty name="_m_pbmForm" property="search_info">
                                   <logic:iterate id="row" name="_m_pbmForm" property="search_info"  >
                                       <tr>
                                           <td class="col0"><input type="checkbox" id="chkCheckExist" disabled="disabled" 
                                               <logic:iterate id="plan_batch_map" name="_m_pbmForm" property="plan_batch">
                                                   <logic:equal value="${row.ID_PLAN_GRP}" name="plan_batch_map" property="ID_PLAN_GRP">checked="checked"</logic:equal>
                                               </logic:iterate> />
                                               <logic:iterate id="plan_batch_map" name="_m_pbmForm" property="plan_batch">
                                                   <logic:equal value="${row.ID_PLAN_GRP}" name="plan_batch_map" property="ID_PLAN_GRP">
                                                       <c:set var="id_plan_grp" value="${id_plan_grp},${row.ID_PLAN_GRP}"></c:set>
                                                </logic:equal>
                                            </logic:iterate>
                                           </td>
                                           <td class="col1">
                                                <t:writeCodeValue codeList="LIST_ITEMTYPE" key="${row.ITEM_TYPE}"></t:writeCodeValue>
                                           </td>
                                           <td class="col2"><bean:write name="row" property="ITEM_GRP_NAME"/> </td>
                                           <td class="col3">
                                               <bean:write name="row" property="SVC_GRP_NAME"/>
                                           </td>
                                           <td class="col4">
                                               <t:writeCodeValue codeList="LIST_RATETYPE" key="${row.RATE_TYPE}"></t:writeCodeValue>
                                           </td>
                                           <td class="col5">
                                               <t:writeCodeValue codeList="LIST_RATEMODE" key="${row.RATE_MODE}"></t:writeCodeValue>
                                           </td>
                                           <td class="col6">
                                               <fmt:formatNumber value="${row.RATE}" pattern="#,##0.00"/>
                                           </td>
                                           <%-- 
                                           <c:choose>
                                               <c:when test="${_m_pbmForm.map.id_batch eq 'DU' && row.ITEM_TYPE eq 'S' && row.RATE_TYPE eq 'BA'}">
                                                   <td><bean:write name="row" property="USAGE_BASE"/> <t:writeCodeValue codeList="LIST_UOM" key="${row.UOM}"></t:writeCodeValue></td>
                                               </c:when>
                                               <c:otherwise>
                                                   <c:choose>
                                                       <c:when test="${_m_pbmForm.map.id_batch eq 'AD' && row.ITEM_TYPE eq 'S' && row.RATE_TYPE eq 'BA'}">
                                                           <td><bean:write name="row" property="USAGE_BASE"/> <t:writeCodeValue codeList="LIST_UOM" key="${row.UOM}"></t:writeCodeValue></td>
                                                       </c:when>
                                                       <c:otherwise>
                                                           <c:choose>
                                                               <c:when test="${_m_pbmForm.map.id_batch eq 'IP' && row.ITEM_TYPE eq 'O' && row.RATE_TYPE eq 'EX'}">
                                                                   <td><bean:write name="row" property="CODE_VALUE"/>&nbsp;</td>
                                                               </c:when>
                                                               <c:otherwise>
                                                                   <td>&nbsp;</td>
                                                               </c:otherwise>
                                                           </c:choose>
                                                       </c:otherwise>
                                                   </c:choose>
                                               </c:otherwise>
                                           </c:choose>
                                           --%>
                                       </tr>
                                   </logic:iterate>
                               </logic:notEmpty>
                        </table>
                        <logic:notEmpty name="_m_pbmForm" property="search_info">
                        <logic:equal value="MH" name="_m_pbmForm" property="id_batch">
                        <br/>
                        <table id="search_mode" class="searchResult">
                            <col width="35px"/>
                            <col width="250px"/>
                            <col width=""/>
                            <tr class="header">
                                <td></td>
                                <td colspan="2"><bean:message key="screen.m_pbm.optionName"/></td>
                            </tr>
                            <%-- additional Mail account --%>
                            <tr>
                                <td class="col0">
                                    <input type="checkbox" id="chkCheckAMA" disabled="disabled"
                                        <logic:iterate id="optSvcinfo" name="_m_pbmForm" property="search_OptSvcinfo">
                                            <logic:equal value="AMA" name="optSvcinfo" property="UOM">
                                                <logic:equal value="1" name="optSvcinfo" property="CODE_VALUE">checked="checked"</logic:equal>
                                            </logic:equal>
                                        </logic:iterate>
                                    />
                                </td>
                                <td><bean:message key="screen.m_pbm.col_AMA"/></td>
                                <td>
                                    <html:select styleId="cboOptSvcAMA" name="_m_pbmForm" property="cboOptSvcAMA" disabled="true">
                                        <option value=""><bean:message key="screen.m_pbm.Empty"/></option>
                                        <html:optionsCollection name="_m_pbmForm" property="search_OptSvcinfo" label="ITEM_GRP_NAME" value="ID_PLAN_GRP"/>
                                    </html:select>
                                </td>
                            </tr>
                            <%-- Mailbox(Qty) --%>
                            <tr>
                                <td class="col0">
                                    <input type="checkbox" id="chkCheckAMQ" disabled="disabled"
                                        <logic:iterate id="optSvcinfo" name="_m_pbmForm" property="search_OptSvcinfo">
                                            <logic:equal value="AMQ" name="optSvcinfo" property="UOM">
                                                <logic:equal value="1" name="optSvcinfo" property="CODE_VALUE">checked="checked"</logic:equal>
                                            </logic:equal>
                                        </logic:iterate>
                                    />
                                </td>
                                <td><bean:message key="screen.m_pbm.col_AMQ"/></td>
                                <td>
                                    <html:select styleId="cboOptSvcAMQ" name="_m_pbmForm" property="cboOptSvcAMQ" disabled="true">
                                        <option value=""><bean:message key="screen.m_pbm.Empty"/></option>
                                        <html:optionsCollection name="_m_pbmForm" property="search_OptSvcinfo" label="ITEM_GRP_NAME" value="ID_PLAN_GRP"/>
                                    </html:select>
                                </td>
                            </tr>
                            <%-- Virus Scan --%>
                            <tr>
                                <td class="col0">
                                    <input type="checkbox" id="chkCheckVRS" disabled="disabled"
                                        <logic:iterate id="optSvcinfo" name="_m_pbmForm" property="search_OptSvcinfo">
                                            <logic:equal value="VRS" name="optSvcinfo" property="UOM">
                                                <logic:equal value="1" name="optSvcinfo" property="CODE_VALUE">checked="checked"</logic:equal>
                                            </logic:equal>
                                        </logic:iterate>
                                    />
                                </td>
                                <td><bean:message key="screen.m_pbm.col_VRS"/></td>
                                <td>
                                    <html:select styleId="cboOptSvcVRS" name="_m_pbmForm" property="cboOptSvcVRS" disabled="true">
                                        <option value=""><bean:message key="screen.m_pbm.Empty"/></option>
                                        <html:optionsCollection name="_m_pbmForm" property="search_OptSvcinfo" label="ITEM_GRP_NAME" value="ID_PLAN_GRP"/>
                                    </html:select>
                                </td>
                            </tr>
                            <%-- Anti Spam --%>
                            <tr>
                                <td class="col0">
                                    <input type="checkbox" id="chkCheckASP" disabled="disabled"
                                        <logic:iterate id="optSvcinfo" name="_m_pbmForm" property="search_OptSvcinfo">
                                            <logic:equal value="ASP" name="optSvcinfo" property="UOM">
                                                <logic:equal value="1" name="optSvcinfo" property="CODE_VALUE">checked="checked"</logic:equal>
                                            </logic:equal>
                                        </logic:iterate>
                                    />
                                </td>
                                <td><bean:message key="screen.m_pbm.col_ASP"/></td>
                                <td>
                                    <html:select styleId="cboOptSvcASP" name="_m_pbmForm" property="cboOptSvcASP" disabled="true">
                                        <option value=""><bean:message key="screen.m_pbm.Empty"/></option>
                                        <html:optionsCollection name="_m_pbmForm" property="search_OptSvcinfo" label="ITEM_GRP_NAME" value="ID_PLAN_GRP"/>
                                    </html:select>
                                </td>
                            </tr>
                            <%-- Junk Management --%>
                            <tr>
                                <td class="col0">
                                    <input type="checkbox" id="chkCheckJMG" disabled="disabled"
                                        <logic:iterate id="optSvcinfo" name="_m_pbmForm" property="search_OptSvcinfo">
                                            <logic:equal value="JMG" name="optSvcinfo" property="UOM">
                                                <logic:equal value="1" name="optSvcinfo" property="CODE_VALUE">checked="checked"</logic:equal>
                                            </logic:equal>
                                        </logic:iterate>
                                    />
                                </td>
                                <td><bean:message key="screen.m_pbm.col_JMG"/></td>
                                <td>
                                    <html:select styleId="cboOptSvcJMG" name="_m_pbmForm" property="cboOptSvcJMG" disabled="true">
                                        <option value=""><bean:message key="screen.m_pbm.Empty"/></option>
                                        <html:optionsCollection name="_m_pbmForm" property="search_OptSvcinfo" label="ITEM_GRP_NAME" value="ID_PLAN_GRP"/>
                                    </html:select>
                                </td>
                            </tr>
                        </table>
                        </logic:equal>
                        </logic:notEmpty>
                        <html:hidden property="id_plan_grp" value="${id_plan_grp}"/>
                        <c:if test="${accessRightBean eq '2'}">
                            <hr style="background:#cecece;margin-top: 15px;margin-bottom:5px;"/>
                            <logic:notEqual value="MH" name="_m_pbmForm" property="id_batch">
                            	<html:button property="actEdit" onclick="do_edit();" styleClass="button"><bean:message key="screen.m_pbm.edit"/></html:button>
                            	<logic:equal value="0" name="_m_pbmForm" property="enable_delete">
		                            <input type="button" name="actDelete" onclick="do_delete();" class="button" value="<bean:message key="screen.m_pbm.delete"/>"/>
                            	</logic:equal>
                            </logic:notEqual>
                            <logic:equal value="MH" name="_m_pbmForm" property="id_batch">
                            	<html:button property="actEdit" onclick="do_edit();" styleClass="button"><bean:message key="screen.m_pbm.edit"/></html:button>
	                            <input type="button" name="actDelete" onclick="do_delete();" class="button" value="<bean:message key="screen.m_pbm.delete"/>"/>
                            </logic:equal>
                            <!-- <input type="button" name="actDelete" onclick="do_delete();" value="<bean:message key="screen.m_pbm.delete"/>"/> -->
                        </c:if>
                    </div>
            </div>
        </logic:equal>
        <%-- Edit mode --%>
        <logic:equal value="edit" name="_m_pbmForm" property ="mode">
            <div id="edit_mode" >
                <table class = "generalInfo">
                    <tr>
                        <td><bean:message key="screen.m_pbm.general"/></td>
                    </tr>
                </table>
                <!-- batch name plan name -->
                <div style="border: #cecece 2px solid;padding:5px;margin-top:5px;width:100%;">
                    <table id="batch_view_criteria" class="show">
                        <tr>
                            <td align="right"><bean:message key="screen.m_pbm.batch"/> :</td>
                            <td><bean:write name="_m_pbmForm" property="batch_name"/> </td>
                        </tr>
                        <tr>
                            <td align="right"><bean:message key="screen.m_pbm.serviceName"/> :</td>
                            <td><bean:write name="_m_pbmForm" property="plan_name"/> </td>
                            <td style="padding-left:100px;">
                                <bean:write name="_m_pbmForm" property="plan_desc"/> 
                                <html:hidden property="plan_id_new" value="${_m_pbmForm.map.plan_id}"/>
                            </td>
                        </tr>
                    </table>
                    
                    <!--  search result -->
                    <table id="edit_table" class="searchResult" width="100%">
                        <tr class="header">
                            <td></td>
                            <td></td>
                            <td><bean:message key="screen.m_pbm.col_subPlanAndOptionName"/></td>
                            <td><bean:message key="screen.m_pbm.col_service_group"/></td>
                            <td><bean:message key="screen.m_pbm.col_rate_type"/></td>
                            <td><bean:message key="screen.m_pbm.col_mode"/></td>
                            <td style="text-align:right;padding-right:20px;"><bean:message key="screen.m_pbm.col_rate"/><bean:write name="_m_pbmForm" property="bill_currency"/><bean:message key="screen.m_pbm.col_rate1"/></td>
                            <%-- 
                            <logic:equal value="DU" name="_m_pbmForm" property="id_batch">
                                <td><bean:message key="screen.m_pbm.col_usage_base"/></td>
                            </logic:equal>
                            <logic:equal value="AD" name="_m_pbmForm" property="id_batch">
                                <td><bean:message key="screen.m_pbm.col_usage_base"/></td>
                            </logic:equal>
                            <logic:equal value="IP" name="_m_pbmForm" property="id_batch">
                                <td><bean:message key="screen.m_pbm.col_code"/></td>
                            </logic:equal>
                            <td/>
                            --%>
                        </tr>
                        <logic:notEmpty name="_m_pbmForm" property="search_info">
                             <logic:iterate id="row" name="_m_pbmForm" property="search_info" indexId="row_num" >
                                   <tr id="edit_row_${row_num}">
                                       <td>
                                           <input class="col0" type="checkbox" id="chkCheckExist"
                                               <logic:notEqual value="MH" name="_m_pbmForm" property="id_batch">
	                                               <logic:iterate id="cust_plan_group_map" name="_m_pbmForm" property="cust_plan_group">
	                                                   <logic:equal value="${row.ID_PLAN_GRP}" name="cust_plan_group_map" property="ID_PLAN_GRP">disabled="disabled"</logic:equal>
	                                               </logic:iterate>
                                               </logic:notEqual>
                                               <logic:iterate id="plan_batch_map" name="_m_pbmForm" property="plan_batch">
                                                   <logic:equal value="${row.ID_PLAN_GRP}" name="plan_batch_map" property="ID_PLAN_GRP">checked="checked"</logic:equal>
                                               </logic:iterate> 
                                               onclick="on_checkedChanged2('${_m_pbmForm.map.id_batch}','edit_row_${row_num}', '${row.ITEM_TYPE}', '${row.RATE_TYPE}')"/> 
                                       </td>
                                       <td class="hide"><bean:write name="row" property="ID_PLAN_GRP"/></td>
                                        <td class="col1">
                                            <t:writeCodeValue codeList="LIST_ITEMTYPE" key="${row.ITEM_TYPE}"></t:writeCodeValue>
                                        </td>
                                       <td class="col2"><bean:write name="row" property="ITEM_GRP_NAME"/> </td>
                                       <td class="col3">
                                           <bean:write name="row" property="SVC_GRP_NAME"/>
                                       </td>
                                       <td class="col4">
                                           <t:writeCodeValue codeList="LIST_RATETYPE" key="${row.RATE_TYPE}"></t:writeCodeValue>
                                       </td>
                                       <td class="col5">
                                           <t:writeCodeValue codeList="LIST_RATEMODE" key="${row.RATE_MODE}"></t:writeCodeValue>
                                       </td>
                                       <td class="col6">
                                           <fmt:formatNumber value="${row.RATE}" pattern="#,##0.00"/>
                                       </td>
                                       <%-- 
                                       <td>
                                           <c:if test="${_m_pbmForm.map.id_batch eq 'DU' && row.ITEM_TYPE eq 'S' && row.RATE_TYPE eq 'BA'}">
                                            <div id="hide_col_${row_num}" 
                                                <logic:iterate id="plan_batch_map" name="_m_pbmForm" property="plan_batch">
                                                           <logic:equal value="${row.ID_PLAN_GRP}" name="plan_batch_map" property="ID_PLAN_GRP">class="show"</logic:equal>
                                                   </logic:iterate>
                                                class="hide">
                                                <input class="txtUsageBase" type="text" id="txtUB" value="<bean:write name="row" property="USAGE_BASE"/>" />
                                                <c:if test="${not empty row.UOM}">
                                                    <bean:define id="uomSelected" name="row" property="UOM"/>
                                                </c:if>
                                                <html:select styleId="cboUOM" name="_m_pbmForm"
                                                    property="uom" style="width:80px" value="${uomSelected}">
                                                    <html:options collection="LIST_UOM" property="id" labelProperty="name"/>
                                                </html:select>
                                                
                                            </div>
                                        </c:if>
                                        <c:if test="${_m_pbmForm.map.id_batch eq 'AD' && row.ITEM_TYPE eq 'S' && row.RATE_TYPE eq 'BA'}">
                                                <div id="hide_col_${row_num}"
                                                    <logic:iterate id="plan_batch_map" name="_m_pbmForm" property="plan_batch">
                                                           <logic:equal value="${row.ID_PLAN_GRP}" name="plan_batch_map" property="ID_PLAN_GRP">class="show"</logic:equal>
                                                       </logic:iterate>
                                                    class="hide">
                                                    <input  class="txtUsageBase" type="text" id="txtUB" value="<bean:write name="row" property="USAGE_BASE"/>" />
                                                    <html:select styleId="cboUOM" name="_m_pbmForm"
                                                        property="uom" style="width:80px" value="${row.UOM}" >
                                                        <html:options collection="LIST_UOM" property="id" labelProperty="name"/>
                                                    </html:select>
                                                </div>
                                        </c:if>
                                        <c:if test="${_m_pbmForm.map.id_batch eq 'IP' && row.ITEM_TYPE eq 'O' && row.RATE_TYPE eq 'EX'}">
                                            <div id="hide_col_${row_num}"
                                                <logic:iterate id="plan_batch_map" name="_m_pbmForm" property="plan_batch">
                                                           <logic:equal value="${row.ID_PLAN_GRP}" name="plan_batch_map" property="ID_PLAN_GRP">class="show"</logic:equal>
                                                       </logic:iterate>
                                                class="hide"> 
                                                <html:select styleId="cboCodeValue" name="_m_pbmForm"
                                                    property="code_value" style="width:60px" value="${row.CODE_VALUE}"  >
                                                    <html:options collection="LIST_CODE_VALUE" property="id" labelProperty="name"/>
                                                </html:select>    
                                                </div>
                                        </c:if>
                                       </td>
                                       --%>
                                   </tr>
                               </logic:iterate>
                        </logic:notEmpty>
                    </table>
                    <logic:notEmpty name="_m_pbmForm" property="search_info">
                    <logic:equal value="MH" name="_m_pbmForm" property="id_batch">
                        <c:set var="idPlanGrpExist" value="0"/>
                        <logic:iterate id="rowSubPlan" name="_m_pbmForm" property="search_info">
                            <logic:iterate id="cust_plan_group_map" name="_m_pbmForm" property="cust_plan_group">
                                <logic:equal value="${rowSubPlan.ID_PLAN_GRP}" name="cust_plan_group_map" property="ID_PLAN_GRP">
                                    <c:set var="idPlanGrpExist" value="1"/>
                                </logic:equal>
                            </logic:iterate> 
                        </logic:iterate>
                        <html:hidden property="idPlanGrpExist" value="${idPlanGrpExist}" styleId="idPlanGrpExist"/>
                    <br/>
                    <table id="edit_OptSvc_table" class="searchResult">
                        <col width="35px"/>
                        <col width="250px"/>
                        <col width=""/>
                        <tr class="header">
                            <td></td>
                            <td colspan="2"><bean:message key="screen.m_pbm.optionName"/></td>
                        </tr>
                        <%-- additional Mail account --%>
                        <tr>
                            <td class="col0">
                                <html:checkbox name="_m_pbmForm"  property="chkCheckAMA" styleId="chkCheckAMA" value="1" />
                            </td>
                            <td><bean:message key="screen.m_pbm.col_AMA"/></td>
                            <td>
                                <html:select styleId="cboOptSvcAMA" name="_m_pbmForm" property="cboOptSvcAMA">
                                    <option value=""><bean:message key="screen.m_pbm.Empty"/></option>
                                    <html:optionsCollection name="_m_pbmForm" property="search_OptSvcinfo" label="ITEM_GRP_NAME" value="ID_PLAN_GRP"/>
                                </html:select>
                            </td>
                        </tr>
                        <%-- Mailbox(Qty) --%>
                        <tr>
                            <td class="col0">
                                <html:checkbox name="_m_pbmForm"  property="chkCheckAMQ" styleId="chkCheckAMQ" value="1"/>
                            </td>
                            <td><bean:message key="screen.m_pbm.col_AMQ"/></td>
                            <td>
                                <html:select styleId="cboOptSvcAMQ" name="_m_pbmForm" property="cboOptSvcAMQ">
                                    <option value=""><bean:message key="screen.m_pbm.Empty"/></option>
                                    <html:optionsCollection name="_m_pbmForm" property="search_OptSvcinfo" label="ITEM_GRP_NAME" value="ID_PLAN_GRP"/>
                                </html:select>
                            </td>
                        </tr>
                        <%-- Virus Scan --%>
                        <tr>
                            <td class="col0">
                                <html:checkbox name="_m_pbmForm"  property="chkCheckVRS" styleId="chkCheckVRS" value="1"/>
                            </td>
                            <td><bean:message key="screen.m_pbm.col_VRS"/></td>
                            <td>
                                <html:select styleId="cboOptSvcVRS" name="_m_pbmForm" property="cboOptSvcVRS">
                                    <option value=""><bean:message key="screen.m_pbm.Empty"/></option>
                                    <html:optionsCollection name="_m_pbmForm" property="search_OptSvcinfo" label="ITEM_GRP_NAME" value="ID_PLAN_GRP"/>
                                </html:select>
                            </td>
                        </tr>
                        <%-- Anti Spam --%>
                        <tr>
                            <td class="col0">
                                <html:checkbox name="_m_pbmForm"  property="chkCheckASP" styleId="chkCheckASP" value="1"/>
                            </td>
                            <td><bean:message key="screen.m_pbm.col_ASP"/></td>
                            <td>
                                <html:select styleId="cboOptSvcASP" name="_m_pbmForm" property="cboOptSvcASP">
                                    <option value=""><bean:message key="screen.m_pbm.Empty"/></option>
                                    <html:optionsCollection name="_m_pbmForm" property="search_OptSvcinfo" label="ITEM_GRP_NAME" value="ID_PLAN_GRP"/>
                                </html:select>
                            </td>
                        </tr>
                        <%-- Junk Management --%>
                        <tr>
                            <td class="col0">
                                <html:checkbox name="_m_pbmForm"  property="chkCheckJMG" styleId="chkCheckJMG" value="1"/>
                            </td>
                            <td><bean:message key="screen.m_pbm.col_JMG"/></td>
                            <td>
                                <html:select styleId="cboOptSvcJMG" name="_m_pbmForm" property="cboOptSvcJMG">
                                    <option value=""><bean:message key="screen.m_pbm.Empty"/></option>
                                    <html:optionsCollection name="_m_pbmForm" property="search_OptSvcinfo" label="ITEM_GRP_NAME" value="ID_PLAN_GRP"/>
                                </html:select>
                            </td>
                        </tr>
                    </table>
                    </logic:equal>
                    </logic:notEmpty>
                    <hr style="background:#cecece;margin-top: 15px;margin-bottom:5px;"/>
                    <html:button property="actSave" onclick="do_save_edit('${_m_pbmForm.map.id_batch}')" styleClass="button"><bean:message key="screen.m_pbm.save"/></html:button>
                </div>
            </div>
        </logic:equal>
            <!-- edit mode -->
            <ts:submit styleId="forward_delete" value='delete' property="forward_delete" style="visibility:hidden" styleClass="button"/>
            <ts:submit styleId="forward_save" value='save' property="forward_save" style="visibility:hidden" styleClass="button"/>
            <div id="errorMsg" class="error"></div>
            <input type="hidden" id="mode" name="mode" value="search"/>
            <input type="hidden" id="new_data" name="new_data" value="<bean:write name="_m_pbmForm" property="new_data" />"/>
            <input type="hidden" id="edit_data" name="edit_data" value="<bean:write name="_m_pbmForm" property="edit_data" />"/>
            <input type="hidden" id="plan_id" name="plan_id" value="<bean:write name="_m_pbmForm" property="plan_id" />"/>
            <div id="ERR1SC038" class="hide"><font color="red" style="font-style: italic"><bean:message key="screen.m_pbm.ERR1SC038"/></font></div>
            <!-- message -->
            <div>
                <font color="blue" style="font-style: italic">
                    <logic:notEqual value="" name="_m_pbmForm" property="message">                    
                        <logic:equal value="ERR2SC003" name="_m_pbmForm" property="message" ><bean:message key="screen.m_pbm.ERR2SC003"/></logic:equal>
                        <logic:equal value="ERR2SC005" name="_m_pbmForm" property="message" ><bean:message key="screen.m_pbm.ERR2SC005"/></logic:equal>
                    </logic:notEqual>
                </font>
            </div>
            <div>
                <font color="red" style="font-style: italic">
                <logic:notEqual value="" name="_m_pbmForm" property="message">                    
                    <logic:equal value="ERR2SC004" name="_m_pbmForm" property="message" ><bean:message key="screen.m_pbm.ERR2SC004"/></logic:equal>
                    <logic:equal value="ERR2SC006" name="_m_pbmForm" property="message" ><bean:message key="screen.m_pbm.ERR2SC006"/></logic:equal>
                    <logic:equal value="ERR2SC007" name="_m_pbmForm" property="message" ><bean:message key="screen.m_pbm.ERR2SC007"/></logic:equal>
                    <logic:equal value="ERR2SC008" name="_m_pbmForm" property="message" ><bean:message key="screen.m_pbm.ERR2SC008"/></logic:equal>
                    <logic:equal value="errors.ERR1SC033" name="_m_pbmForm" property="message" ><bean:message key="errors.ERR1SC033" arg0="Sub Plan" arg1="Sub Plan"/></logic:equal>
                    <logic:equal value="errors.ERR1SC105" name="_m_pbmForm" property="message" ><bean:message key="errors.ERR1SC105" arg0="Selected standard plan is already registered."/></logic:equal>
                </logic:notEqual>
                </font>
            </div>
            <!-- message -->
        </ts:form>
        <input type="hidden" id="ERR4SC002" value="<bean:message key="screen.m_pbm.ERR4SC002"/>"  />
        <input type="hidden" id="contextPath" value="<%=request.getContextPath()%>" />
    </body>
</html:html>

