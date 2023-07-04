<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-html" prefix="html"%>
<%@ taglib uri="/struts-bean" prefix="bean"%>
<%@ taglib uri="/struts-logic" prefix="logic"%>
<%@ taglib uri="/terasoluna-struts" prefix="ts"%>
<%@ taglib uri="/terasoluna" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/bs" prefix="bs" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<%@page import="nttdm.bsys.common.fw.BillingSystemUserValueObject"%>
<%@page import="nttdm.bsys.common.util.CommonUtils"%>
<%@page import="java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/common.css" />
        <link type="text/css" rel="stylesheet" href="css/m_gbs.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/common.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/messageBox.js"></script>
        <script type="text/javascript" src="js/m_gbss02.js"></script>
        <title></title>
    </head>
    <body id="m" onload="initMain();init();">
        <%
            BillingSystemUserValueObject uvo = (BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT");
            String accessRight = CommonUtils.getAccessRight(uvo, "M-GBS-02");
            pageContext.setAttribute("accessRightBean", accessRight);
        %>

        <table class="subHeader" cellpadding="0" cellspacing="0" id="subHeader" style="width:100%">
            <tr>
                <td><bean:message key="screen.m_gbs02.subheader"/></td>
            </tr>
        </table>
        <ts:form  action="/M_GBS_S02DSPBL" method="post" onsubmit="" styleId="_m_gbss02Form">
            <t:defineCodeList id="LIST_GBS02_COLUMNS"/>
            <t:defineCodeList id="LIST_End_User" />
            <t:defineCodeList id="LIST_Mode_Type" />
            <t:defineCodeList id="LIST_CATEGORY" />
            <%-- <html:hidden property="listResult" styleId="listR" name="M_GBS_S02Form" value="${M_GBS_S02Form.listResult}"/> --%>
            <table border="0" cellpadding="0" cellspacing="0" width="100%">
            <tr>
                <td>
                     <div class="searchCondition">
                        <bean:message key="screen.m_gbs02.columnName"/>
                        <font color="red">*</font>: 
                        <html:select name="M_GBS_S02Form" property="columnType" styleClass="CashBookTextBox" onchange="changeSelect(this)">
                            <html:option value=""><bean:message key="screen.m_gbs02.blankItem"/></html:option>
                            <html:options collection="LIST_GBS02_COLUMNS" property="id" labelProperty="name"/>
                        </html:select>
                        <html:select name="M_GBS_S02Form" property="localcCtegory" styleId="localcCtegory" style="width:110px; display:none">
                        	<html:options collection="LIST_CATEGORY" property="id" labelProperty="name"/>
                        </html:select>
                        <input type="hidden" name="selectType" value="${M_GBS_S02Form.columnType}" id="selectType"/>
                     </div>
                </td>
            </tr>
            <tr>
                 <td nowrap="nowrap" style="padding-top:5px">
                     <ts:submit styleClass="button" property="forward_search"><bean:message key="screen.m_gbs02.button.search"/></ts:submit>
                     <bs:buttonLink action="/M_GBS_S02SCR" value="Reset"/>
                 </td>
            </tr>
            </table>
            <br/>
            <nested:notEmpty name="M_GBS_S02Form" property="preSelItem">
             <div class="resultTitle">
                <logic:equal name="M_GBS_S02Form" property="preSelItem" value="CA">
                    <bean:message key="screen.m_gbs02.resultTitle.carrier" />
                </logic:equal>
                <!--Add by MiffyAn Start-->
                <logic:equal name="M_GBS_S02Form" property="preSelItem" value="CC">
                    <bean:message key="screen.m_gbs02.resultTitle.CompanyCategory" />
                </logic:equal>
                 <logic:equal name="M_GBS_S02Form" property="preSelItem" value="CS">
                    <bean:message key="screen.m_gbs02.resultTitle.CompanySubCategory" />
                </logic:equal>
                 <logic:equal name="M_GBS_S02Form" property="preSelItem" value="CT">
                    <bean:message key="screen.m_gbs02.resultTitle.CompanyType" />
                </logic:equal>
                <!--Add by MiffyAn End-->
                <logic:equal name="M_GBS_S02Form" property="preSelItem" value="CG">
                    <bean:message key="screen.m_gbs02.resultTitle.CustomerGroup" />
                </logic:equal>
                <logic:equal name="M_GBS_S02Form" property="preSelItem" value="EU">
                    <bean:message key="screen.m_gbs02.resultTitle.EndUser" />
                </logic:equal>
                <logic:equal name="M_GBS_S02Form" property="preSelItem" value="LO">
                    <bean:message key="screen.m_gbs02.resultTitle.location" />
                </logic:equal>
                <!-- wcbeh@20160920 - Master Location -->
                <logic:equal name="M_GBS_S02Form" property="preSelItem" value="ML">
                    <bean:message key="screen.m_gbs02.resultTitle.MasterLocation" />
                </logic:equal>
                <logic:equal name="M_GBS_S02Form" property="preSelItem" value="FT">
                    <bean:message key="screen.m_gbs02.resultTitle.FirewallType" />
                </logic:equal>
                <logic:equal name="M_GBS_S02Form" property="preSelItem" value="FM">
                    <bean:message key="screen.m_gbs02.resultTitle.FirewallModel" />
                </logic:equal>
                <!--Add by MiffyAn Start-->
                <logic:equal name="M_GBS_S02Form" property="preSelItem" value="PR">
                    <bean:message key="screen.m_gbs02.resultTitle.Product" />
                </logic:equal>
                <logic:equal name="M_GBS_S02Form" property="preSelItem" value="JN">
                    <bean:message key="screen.m_gbs02.resultTitle.JournalNo" />
                </logic:equal>
                <!--Add by MiffyAn End-->
                <logic:equal name="M_GBS_S02Form" property="preSelItem" value="RT">
                    <bean:message key="screen.m_gbs02.resultTitle.RateType2" />
                </logic:equal>
                <logic:equal name="M_GBS_S02Form" property="preSelItem" value="TC">
                    <bean:message key="screen.m_gbs02.resultTitle.TaxCode" />
                </logic:equal>
             </div>
             <div class="itemDiv">
             <c:if test="${M_GBS_S02Form.preSelItem ne 'TC' && M_GBS_S02Form.preSelItem ne 'CG' && M_GBS_S02Form.preSelItem ne 'EU' && M_GBS_S02Form.preSelItem ne 'RT'
              && M_GBS_S02Form.preSelItem ne 'CC' && M_GBS_S02Form.preSelItem ne 'CS' && M_GBS_S02Form.preSelItem ne 'CT' && M_GBS_S02Form.preSelItem ne 'PR'  
              && M_GBS_S02Form.preSelItem ne 'JN'}">
             <table class="itemTable" cellspacing="0" cellpadding="0">
                <tr>
                     <th width="50px">
                        <logic:equal value="2" name="accessRightBean">
                            <a class="hlAdd" href="javascript:void(0);" onclick="addItem()"><bean:message key="screen.m_gbs02.add"/></a>
                        </logic:equal>
                        <logic:notEqual value="2" name="accessRightBean" >
                            &nbsp;
                        </logic:notEqual>
                     </th>
                     <th width="600px">
                        <bean:message key="screen.m_gbs02.value"/>
                     </th>
                </tr>
                <nested:notEmpty name="M_GBS_S02Form" property="listResult">
                <nested:iterate name="M_GBS_S02Form" property="listResult">
                    <tr>
                        <td>
                              <logic:equal value="2" name="accessRightBean">
                                <nested:equal value="N" property="isUsed">
                                    <div class="deleteX" onclick="deleteItem(this)">X</div>
                                </nested:equal>
                                <nested:notEqual value="N" property="isUsed">
                                    &nbsp;
                                </nested:notEqual>
                              </logic:equal>
                              <logic:notEqual value="2" name="accessRightBean" >
                                    &nbsp;
                              </logic:notEqual>
                              <nested:hidden property="isUsed"></nested:hidden>
                        </td>
                        <td>
                              <nested:hidden property="itemId" />
                              <logic:equal value="2" name="accessRightBean">
                                  <nested:text property="itemName" maxlength="100" style="width:100%"></nested:text>
                              </logic:equal>
                              <logic:notEqual value="2" name="accessRightBean">
                                  <nested:text property="itemName" readonly="true" maxlength="100" style="width:100%"></nested:text>
                              </logic:notEqual>
                              <input type="hidden" value='<nested:write property="itemName"/>'/>
                        </td>
                    </tr>
                </nested:iterate>
                </nested:notEmpty>
             </table>
             </c:if>
             
             <!--CustomerGroup Table Start -->
             <c:if test="${M_GBS_S02Form.preSelItem eq 'CG' || M_GBS_S02Form.preSelItem eq 'EU' || M_GBS_S02Form.preSelItem eq 'RT'
             || M_GBS_S02Form.preSelItem eq 'CC' || M_GBS_S02Form.preSelItem eq 'CS' || M_GBS_S02Form.preSelItem eq 'CT' || M_GBS_S02Form.preSelItem eq 'PR'
             || M_GBS_S02Form.preSelItem eq 'JN'}">
               <table id="customerTable" class="customerTable" cellspacing="0" cellpadding="0">
             	<tr>
                     <th width="50px">
                        <logic:equal value="2" name="accessRightBean">
                            <a class="hlAdd" href="javascript:void(0);" onclick="addCustomerItem()"><bean:message key="screen.m_gbs02.add"/></a>
                        </logic:equal>
                        <logic:notEqual value="2" name="accessRightBean" >
                            &nbsp;
                        </logic:notEqual>
                     </th>
                     <th width="100px">
                        <bean:message key="screen.m_gbs02.code"/>
                     </th>
                     <th width="500px">
                        <bean:message key="screen.m_gbs02.description"/>
                     </th>
                     <!-- Add by Jing Start -->
                     <c:if test="${M_GBS_S02Form.preSelItem eq 'RT'}">
	                     <th width="150px">
	                        <bean:message key="screen.m_gbs02.endUser"/>
	                     </th>
	                     <th width="150px">
	                        <bean:message key="screen.m_gbs02.modeType"/>
	                     </th> 
                     </c:if>
                     <!-- Add by Jing End -->     
                     <!-- Add by MiffyAn Start -->
                     <c:if test="${M_GBS_S02Form.preSelItem eq 'PR'}">
	                     <th width="100px">
	                        <bean:message key="screen.m_gbs02.category"/>
	                     </th>
                     </c:if>
                      <c:if test="${M_GBS_S02Form.preSelItem eq 'CG'}">
	                     <th width="110px">
	                        <bean:message key="screen.m_gbs02.abbreviation"/>
	                     </th>
                     </c:if>
                     <!-- Add by MiffyAn End -->            
                </tr>
             <nested:notEmpty name="M_GBS_S02Form" property="listResult">
             <nested:iterate name="M_GBS_S02Form" property="listResult">
             	<tr>
                	 <td>
                	 	<logic:equal value="2" name="accessRightBean">
                        	<nested:equal value="N" property="isUsed">
                        		<div class="deleteX" onclick="deleteCustomerItem(this)">X</div>
                        	</nested:equal>
                        	<nested:notEqual value="N" property="isUsed">
                        		&nbsp;
                        	</nested:notEqual>
                        </logic:equal>
                        <logic:notEqual value="2" name="accessRightBean" >
                        	&nbsp;
                        </logic:notEqual>
                        <nested:hidden property="isUsed"></nested:hidden>
                	 </td>
                	 <td>
                        <nested:hidden property="itemId" />
                        <logic:equal value="2" name="accessRightBean">
                        	<c:if test="${M_GBS_S02Form.preSelItem eq 'EU'  || M_GBS_S02Form.preSelItem eq 'CC' || M_GBS_S02Form.preSelItem eq 'CS' || M_GBS_S02Form.preSelItem eq 'CT'}">
                            	<nested:text property="itemCode" maxlength="1" style="width:100%"></nested:text>
                            </c:if>
                            <c:if test="${M_GBS_S02Form.preSelItem eq 'PR'}">
                            	<nested:text property="itemCode" maxlength="8" style="width:100%"></nested:text>
                            </c:if>
                            <c:if test="${M_GBS_S02Form.preSelItem ne 'EU'  && M_GBS_S02Form.preSelItem ne 'CC' && M_GBS_S02Form.preSelItem ne 'CS' 
                            && M_GBS_S02Form.preSelItem ne 'CT' && M_GBS_S02Form.preSelItem ne 'PR' && M_GBS_S02Form.preSelItem ne 'JN'}">
                            	<nested:text property="itemCode" maxlength="10" style="width:100%"></nested:text>
                            </c:if>
                            <c:if test="${M_GBS_S02Form.preSelItem eq 'JN'}">
                            	<nested:text property="itemCode" maxlength="5" style="width:100%"></nested:text>
                            </c:if>
                        </logic:equal>
                        <logic:notEqual value="2" name="accessRightBean">
                        	<c:if test="${M_GBS_S02Form.preSelItem eq 'EU'}">
                            	<nested:text property="itemCode" readonly="true" maxlength="1" style="width:100%"></nested:text>
                            </c:if>
                            <c:if test="${M_GBS_S02Form.preSelItem ne 'EU'}">
                            	<nested:text property="itemCode" readonly="true" maxlength="10" style="width:100%"></nested:text>
                            </c:if>
                        </logic:notEqual>
                        <input type="hidden" value='<nested:write property="itemCode"/>'/>
                	 </td>
                	 <td>
                        <logic:equal value="2" name="accessRightBean">
                          <c:if test="${M_GBS_S02Form.preSelItem eq 'JN'}">
                            <nested:text property="itemName" maxlength="10" style="width:100%"></nested:text>
                          </c:if>
                          <c:if test="${M_GBS_S02Form.preSelItem ne 'JN'}">
                            <nested:text property="itemName" maxlength="30" style="width:100%"></nested:text>
                          </c:if>
                        </logic:equal>
                        <logic:notEqual value="2" name="accessRightBean">
                          <c:if test="${M_GBS_S02Form.preSelItem eq 'JN'}">
                            <nested:text property="itemName" readonly="true" maxlength="10" style="width:100%"></nested:text>
                          </c:if>
                          <c:if test="${M_GBS_S02Form.preSelItem ne 'JN'}">
                            <nested:text property="itemName" readonly="true" maxlength="30" style="width:100%"></nested:text>
                          </c:if>
                        </logic:notEqual>
                        <input type="hidden" value='<nested:write property="itemName"/>'/>
                	 </td>
                	 <!-- Add by Jing Start -->
                	 <c:if test="${M_GBS_S02Form.preSelItem eq 'RT'}">
                	 	<td>
	                	 	<logic:equal value="2" name="accessRightBean">
		                	 	<nested:select property="endUser" styleId="cmbEndUser" style="width:150px;">
		                	 		<html:options collection="LIST_End_User" property="id" labelProperty="name"/>
		                	 	</nested:select>
	                        </logic:equal>
	                        <logic:notEqual value="2" name="accessRightBean">
	                            <nested:select property="endUser" styleId="cmbEndUser" style="width:150px;" disabled="true">
						 			<html:options collection="LIST_End_User" property="id" labelProperty="name"/>
						   		</nested:select>
	                        </logic:notEqual>
	                        <input type="hidden" value='<nested:write property="endUser"/>'/>
                        </td>
                        <td>
	                	 	<logic:equal value="2" name="accessRightBean">
						   		<nested:select property="modeType" styleId="cmbModeType" style="width:150px;">
						 			<html:options collection="LIST_Mode_Type" property="id" labelProperty="name"/>
						   		</nested:select>
	                            
	                        </logic:equal>
	                        <logic:notEqual value="2" name="accessRightBean">
	                            <nested:select property="modeType" styleId="cmbModeType" style="width:150px;" disabled="true">
						 			<html:options collection="LIST_Mode_Type" property="id" labelProperty="name"/>
						   		</nested:select>
	                        </logic:notEqual>
	                        <input type="hidden" value='<nested:write property="modeType"/>'/>
                        </td>
                	 </c:if>
                	 <!-- Add by Jing End -->
                	 <c:if test="${M_GBS_S02Form.preSelItem eq 'PR'}">
                	 <td>
                        <logic:equal value="2" name="accessRightBean">
		                    <nested:select property="category" styleId="cmbEndUser" style="width:110px;">
		                	<html:options collection="LIST_CATEGORY" property="id" labelProperty="name"/>
		                	</nested:select>
	                    </logic:equal>
	                    <logic:notEqual value="2" name="accessRightBean">
	                      <nested:select property="category" styleId="cmbEndUser" style="width:110px;" disabled="true">
						 	<html:options collection="LIST_CATEGORY" property="id" labelProperty="name"/>
						  </nested:select>
	                    </logic:notEqual>
	                 <input type="hidden" value='<nested:write property="category"/>'/>
                	 </td>
                	 </c:if>
                	  <c:if test="${M_GBS_S02Form.preSelItem eq 'CG'}">
                	    <td>
                        <logic:equal value="2" name="accessRightBean">
		                     <nested:text property="abbreviation" maxlength="7" style="width:100%"></nested:text>
	                    </logic:equal>
	                    <logic:notEqual value="2" name="accessRightBean">
	                        <nested:text property="abbreviation" readonly="true"  maxlength="7" style="width:100%"></nested:text>
	                    </logic:notEqual>
	                  <input type="hidden" value='<nested:write property="abbreviation"/>'/>
                	  </td>
                	 </c:if>
               	</tr>
             </nested:iterate>
             </nested:notEmpty>
              </table>
             </c:if>
             <!--CustomerGroup Table End -->
             
             <!--Tax Table Start -->
             <logic:equal name="M_GBS_S02Form" property="preSelItem" value="TC">
               <table id="taxTable" class="itemTable" cellspacing="0" cellpadding="0">
             	<tr>
                     <th width="50px">
                        <logic:equal value="2" name="accessRightBean">
                            <a class="hlAdd" href="javascript:void(0);" onclick="addTaxItem()"><bean:message key="screen.m_gbs02.add"/></a>
                        </logic:equal>
                        <logic:notEqual value="2" name="accessRightBean" >
                            &nbsp;
                        </logic:notEqual>
                     </th>
                     <th width="100px">
                        <bean:message key="screen.m_gbs02.taxCode"/>
                     </th>
                     <th width="100px">
                        <bean:message key="screen.m_gbs02.taxRate"/>
                     </th>
                     <th width="130px">
                        <bean:message key="screen.m_gbs02.accountCode"/>
                     </th>
                     <th width="210px">
                        <bean:message key="screen.m_gbs02.description"/>
                     </th>
                     <th width="240px">
                        <bean:message key="screen.m_gbs02.description2"/>
                     </th>
                </tr>
             <nested:notEmpty name="M_GBS_S02Form" property="listResult">
             <nested:iterate name="M_GBS_S02Form" property="listResult">
             	<tr>
                	 <td>
                	 	<logic:equal value="2" name="accessRightBean">
                        	<nested:equal value="N" property="isTaxUsed">
                        		<div class="deleteX" onclick="deleteTaxItem(this)">X</div>
                        	</nested:equal>
                        	<nested:notEqual value="N" property="isTaxUsed">
                        		&nbsp;
                        	</nested:notEqual>
                        </logic:equal>
                        <logic:notEqual value="2" name="accessRightBean" >
                        	&nbsp;
                        </logic:notEqual>
                        <nested:hidden property="isTaxUsed"></nested:hidden>
                	 </td>
                	 <td>
                	 	<nested:hidden property="taxId" />
                     	<logic:equal value="2" name="accessRightBean">
                     		<nested:text property="taxCode" maxlength="10"  style="width:100%"></nested:text>
                     	</logic:equal>
                     	<logic:notEqual value="2" name="accessRightBean">
                     		<nested:text property="taxCode" readonly="true" maxlength="10"  style="width:100%;text-align: left"></nested:text>
                     	</logic:notEqual>
                     	<input type="hidden" value='<nested:write property="taxCode"/>'/>
                	 </td>
                	 <td>
                	 	<logic:equal value="2" name="accessRightBean">
                     		<nested:text property="taxRate" maxlength="2" styleClass="taxrate" style="width:95%"></nested:text>
                     	</logic:equal>
                     	<logic:notEqual value="2" name="accessRightBean">
                     		<nested:text property="taxRate" readonly="true" maxlength="2" styleClass="taxrate" style="width:95%"></nested:text>
                     	</logic:notEqual>
                     	<input type="hidden" value='<nested:write property="taxRate"/>'/>
                	 </td>
                	 <!--Account Code Start-->
                	 <td>
                	 	<logic:equal value="2" name="accessRightBean">
                     		<nested:text property="accountCode" maxlength="15" styleClass="accountCode" style="width:95%"></nested:text>
                     	</logic:equal>
                     	<logic:notEqual value="2" name="accessRightBean">
                     		<nested:text property="accountCode" readonly="true" maxlength="15" styleClass="accountCode" style="width:95%"></nested:text>
                     	</logic:notEqual>
                     	<input type="hidden" value='<nested:write property="accountCode"/>'/>
                	 </td>
                	 <!--Account Code End-->
                	 <td>
                	 	<logic:equal value="2" name="accessRightBean">
                     		<nested:text property="taxDesr1" maxlength="30" style="width:100%"></nested:text>
                     	</logic:equal>
                     	<logic:notEqual value="2" name="accessRightBean">
                     		<nested:text property="taxDesr1" readonly="true" maxlength="30" style="width:100%"></nested:text>
                     	</logic:notEqual>
                     	<input type="hidden" value='<nested:write property="taxDesr1"/>'/>
                	 </td>
                	 <td>
                	 	<logic:equal value="2" name="accessRightBean">
                     		<nested:text property="taxDesr2" maxlength="300" style="width:100%"></nested:text>
                     	</logic:equal>
                     	<logic:notEqual value="2" name="accessRightBean">
                     		<nested:text property="taxDesr2" readonly="true" maxlength="300" style="width:100%"></nested:text>
                     	</logic:notEqual>
                     	<input type="hidden" value='<nested:write property="taxDesr2"/>'/>
                	 </td>
               	</tr>
             </nested:iterate>
             </nested:notEmpty>
              </table>
             </logic:equal>
             <!--Tax Table End -->
             </div>
             <br/>
             <logic:equal value="2" name="accessRightBean">
                <div>
                    <input type="submit" id="btnSave" name="forward_save" value="<bean:message key="screen.m_gbs02.button.save"/>" class="button" onclick="return submitSave()"/>
                </div>
             </logic:equal>
             </nested:notEmpty>
             <input type="hidden" id="hiddenMsgDeleteConfirmation" value="<bean:message key="info.ERR4SC002"/>"/>
             <input type="hidden" id="hiddenMsgModifyConfirmation" value='<bean:message key="info.ERR4SC019" arg0="ARG0"/>'/>
             <input type="hidden" id="ERR4SC107" value="<bean:message key="errors.ERR1SC107" arg0="{0}" arg1="{1}"/>"/>
             <html:hidden name="M_GBS_S02Form" property="preSelItem" styleId="preSelItem"/>
             <div class="error">
                <html:messages id="message">
                    <bean:write name="message"/><br/>
                </html:messages>
             </div>
             <div class="message">
                <html:messages id="message" message="true">
                    <bean:write name="message"/><br/>
                </html:messages>
             </div> 
        </ts:form>
    </body>
</html:html>