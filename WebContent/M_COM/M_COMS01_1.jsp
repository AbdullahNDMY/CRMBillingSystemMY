<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ page import="nttdm.bsys.common.fw.BillingSystemUserValueObject" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
        <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
        <link rel="stylesheet" type="text/css" href="css/m_coms01_01.css"/>
        <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
        <script type="text/javascript" src="js/m_coms01_01.js"></script>
        <title></title>
    </head>
    <body id="m" onclick="removeMsg()">
    <ts:form action="/M_COMS01_01_Save">
        <input type="hidden" id="contextPath" value="<%=request.getContextPath()%>" />
        <table class="subHeader" cellpadding="0" cellspacing="0">
        <tr>
            <td><bean:message key="screen.m_com.s01_1.title" />
            </td>
        </tr>
        </table>
        <t:defineCodeList id="LIST_CURRENCY"/>
        <t:defineCodeList id="LIST_DISPLAY_ORDER"/>
        <t:defineCodeList id="LIST_YES_NO"/>
        <nested:hidden property="idCompany" />

        <input type="hidden" id="hiddenMsgExitConfirmation" value="<bean:message key="info.ERR4SC001"/>"/>
        <input type="hidden" id="hiddenMsgDeleteConfirmation" value="<bean:message key="info.ERR4SC002"/>"/>

        <table id="tableCompanyBank">
            <tr class="header">
                 <td width="2%">
                 <% 
                    BillingSystemUserValueObject uvo=(BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT");
                    String accessType1 = uvo.getUserAccessInfo("M", "M-COM").getAccess_type();
                    String accessType2 = uvo.getUserAccessInfo("M", "M-COM-BI").getAccess_type();
                    if("2".equals(accessType1)&&"2".equals(accessType2)){
                 %>
                 <a href="##" onclick="addCompBank()"><bean:message key="screen.m_com.s01_1.table.col1"/></a>
                 <% }else{ %>
                    &nbsp;
                 <% } %>
                 </td>
                 <td width="3%"><bean:message key="screen.m_com.s01_1.table.col.order"/></td>
                 <td width="3%" style="padding:0">
                     <button style="width:23px;height:23px;visibility:hidden">
                        <img src="<%=request.getContextPath()%>/image/search.png">
                     </button>
                 </td>
                 <td width="35%"><bean:message key="screen.m_com.s01_1.table.col3"/></td>
                 <td width="25%"><bean:message key="screen.m_com.s01_1.table.col9"/></td>
                 <td width="32%"><bean:message key="screen.m_com.s01_1.table.co20"/></td>
            </tr>
            <nested:notEmpty property="listCompanyBank">
            <nested:iterate property="listCompanyBank">
                <tr class="companyBank">
                    <td align="center">
                        <nested:notEmpty property="idComBank">
                            &nbsp;
                        </nested:notEmpty>
	                    <nested:empty property="idComBank">
	                        <a href="javascript:void(0)" onclick="delComBank(this)"><img src="../image/delete.gif" alt="delete"/></a>
	                    </nested:empty>
	                    <nested:hidden property="idComBank"></nested:hidden>
                    </td>
                    <td>
                         <nested:select property="displayOrder">
                          <html:options collection="LIST_DISPLAY_ORDER" property="id" labelProperty="name"/>
                         </nested:select>
                    </td>
                    <td style="padding:0;padding-top:4px">
                        <nested:notEmpty property="idComBank">
                            &nbsp;
                        </nested:notEmpty>
                        <nested:empty property="idComBank">
                            <button style="width:23px;height:23px;" onclick="openM_BNK_S03(this);">
                            <img src="<%=request.getContextPath()%>/image/search.png">
                            </button>
                        </nested:empty>
                    </td>
                    <td>
                       <span><nested:write property="bankFullName"/></span>
                       <nested:hidden property="bankFullName"/>
                       <nested:hidden property="idbank"/>
                    </td>
                    <td>
                       <nested:text property="accountName" style="width:100%" maxlength="50"></nested:text>
                    </td>
                    <td>
                       <nested:text property="swiftCode" style="width:70%" maxlength="15"></nested:text>
                    </td>
                </tr>
                <tr class="bankAccount">
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td class="grayCode">
                        <bean:message key="screen.m_com.s01_1.table.col4"/><bean:message key="screen.m_com.colon"/>
                        <span><nested:write property="bankCode"/></span>
                        <nested:hidden property="bankCode"></nested:hidden>
                        <br/>
                        <bean:message key="screen.m_com.s01_1.table.col5"/><bean:message key="screen.m_com.colon"/>
                        <span><nested:write property="branchCode"/></span>
                        <nested:hidden property="branchCode"></nested:hidden>
                    </td>
                    <td colspan="2">
                        <table cellpadding="0" cellspacing="0" id="tableBankAccount">
                           <tr>
                              <td width="8%" class="subtableHeader" align="center">
                              <% 
                                 if("2".equals(accessType1)&&"2".equals(accessType2)){
                              %>
                              <a href="##" onclick="addBankAcc(this)"><bean:message key="screen.m_com.s01_1.table.col1"/></a>
                              <% }else{ %>
                                   &nbsp;
                              <% } %>
                              </td>
                              <td width="27%" class="subtableHeader"><bean:message key="screen.m_com.s01_1.table.col6"/></td>
                              <td width="45%" class="subtableHeader"><bean:message key="screen.m_com.s01_1.table.co18"/></td>
                              <td width="10%" class="subtableHeader"><bean:message key="screen.m_com.s01_1.table.col.default"/></td>
                              <td width="10%" class="subtableHeader"><bean:message key="screen.m_com.s01_1.table.col.active"/></td>
                           </tr>
                          <nested:notEmpty property="listCompanyBankAccount">
                              <nested:iterate property="listCompanyBankAccount">
                                 <tr>
                                 <td align="center">
                                     <nested:empty property="idComBank">
                                        <a href="javascript:void(0)" onclick="delBankAcc(this)"><img src="../image/delete.gif" alt="delete"/></a>
                                     </nested:empty>
                                     <nested:notEmpty property="idComBank">
                                        <nested:write property="idComBank" />
                                     </nested:notEmpty>
                                     <nested:hidden property="idComBank" />
                                 </td>
                                 <td>
                                     <nested:text property="accountNo" styleId="accountNo" style="width:100%" maxlength="50"/>
                                  </td>
                                  <td>
                                     <nested:select property="currency" styleId="currencysel" style="width:100%" onchange="changeCurrency(this)">
                                      <html:option value="" styleClass="colCurrency"><bean:message key="screen.m_com.listBox.default"/></html:option>
                                      <html:options collection="LIST_CURRENCY" property="id" labelProperty="name"/>
                                     </nested:select>
                                  </td>
                                  <td align="center">
                                     <nested:radio property="isDefault" value="1" onclick="clickRadioBtn(this)"></nested:radio>
                                  </td>
                                  <td>
                                     <nested:select property="active">
                                         <html:options collection="LIST_YES_NO" property="id" labelProperty="name"/>
                                     </nested:select>
                                  </td>
                                  </tr>
                              </nested:iterate>
                          </nested:notEmpty>
                        </table>
                    </td>
                </tr>
            </nested:iterate>
            </nested:notEmpty>
        </table>

        <table style="display:none" id="cloneBank">
             <tr class="companyBank">
                 <td align="center">
                    <a href="javascript:void(0)" onclick="delComBank(this)"><img src="../image/delete.gif" alt="delete"/></a>
                    <input type="hidden" value=""/>
                 </td>
                 <td>
                      <select name="">
	                   <logic:iterate id="type" name="LIST_DISPLAY_ORDER">
	                        <option value="${type.id}"><bean:write name="type" property="name"/></option>
	                   </logic:iterate>
                      </select>
                 </td>
                 <td style="padding:0;padding-top:4px">
                     <button style="width:23px;height:23px;" onclick="openM_BNK_S03(this);">
                        <img src="<%=request.getContextPath()%>/image/search.png">
                     </button>
                 </td>
                 <td>
                    <span></span>
                    <input type="hidden" name="" value="">
                    <input type="hidden" name="" value="">
                 </td>
                 <td>
                    <input type="text" name="" maxlength="50" value="" style="width:100%">
                 </td>
                 <td>
                    <input type="text" name="" maxlength="15" value="" style="width:70%">
                 </td>
             </tr>
             <tr class="bankAccount">
                 <td>&nbsp;</td>
                 <td>&nbsp;</td>
                 <td>&nbsp;</td>
                 <td class="grayCode">
                     <bean:message key="screen.m_com.s01_1.table.col4"/><bean:message key="screen.m_com.colon"/>
                     <span></span>
                     <input type="hidden" name="" value="">
                     <br/>
                     <bean:message key="screen.m_com.s01_1.table.col5"/><bean:message key="screen.m_com.colon"/>
                     <span></span>
                     <input type="hidden" name="" value="">
                 </td>
                 <td colspan="2">
                     <table cellpadding="0" cellspacing="0" id="tableBankAccount">
                        <tr>
                           <td width="8%" class="subtableHeader" align="center"><a href="##" onclick="addBankAcc(this)"><bean:message key="screen.m_com.s01_1.table.col1"/></a></td>
                           <td width="27%" class="subtableHeader"><bean:message key="screen.m_com.s01_1.table.col6"/></td>
                           <td width="45%" class="subtableHeader"><bean:message key="screen.m_com.s01_1.table.co18"/></td>
                           <td width="10%" class="subtableHeader"><bean:message key="screen.m_com.s01_1.table.col.default"/></td>
                           <td width="10%" class="subtableHeader"><bean:message key="screen.m_com.s01_1.table.col.active"/></td>
                        </tr>
                         <tr>
                             <td align="center">
                                 <a href="javascript:void(0)" onclick="delBankAcc(this)"><img src="../image/delete.gif" alt="delete"/></a>
                                 <input type="hidden" name="" value="">
                             </td>
                             <td>
                                 <input type="text"  id="accountNo" style="width:100%" maxlength="50"/>
                             </td>
                             <td>
                                <select name="" style="width:100%" id="currencysel" onchange="changeCurrency(this)">
			                       <option value=""><bean:message key="screen.m_com.listBox.default"/></option>
			                       <logic:iterate id="type" name="LIST_CURRENCY">
			                            <option value="${type.id}"><bean:write name="type" property="name"/></option>
			                       </logic:iterate>
                                </select>
                             </td>
                             <td align="center">
                                <input name="" type="radio" value="1" checked="checked" onclick="clickRadioBtn(this)">
                             </td>
                             <td>
                                <select name="">
			                       <logic:iterate id="type" name="LIST_YES_NO">
			                            <option value="${type.id}"><bean:write name="type" property="name"/></option>
			                       </logic:iterate>
                                </select>
                             </td>
                         </tr>
                     </table>
                 </td>
             </tr>
        </table>
        <table style="display:none" id="cloneBankAccount">
             <tr>
                 <td align="center">
                     <a href="javascript:void(0)" onclick="delBankAcc(this)"><img src="../image/delete.gif" alt="delete"/></a>
                     <input type="hidden" name="" value="">
                 </td>
                 <td>
                     <input type="text" style="width:100%" maxlength="20"/>
                 </td>
                 <td>
                    <select name="" style="width:100%">
                       <option value=""><bean:message key="screen.m_com.listBox.default"/></option>
                       <logic:iterate id="type" name="LIST_CURRENCY">
                            <option value="${type.id}"><bean:write name="type" property="name"/></option>
                       </logic:iterate>
                    </select>
                 </td>
                 <td align="center">
                    <input name="" type="radio" value="1" onclick="clickRadioBtn(this)"/>
                 </td>
                 <td>
                    <select name="">
                       <logic:iterate id="type" name="LIST_YES_NO">
                            <option value="${type.id}"><bean:write name="type" property="name"/></option>
                       </logic:iterate>
                    </select>
                 </td>
             </tr>
        </table>
        <div class="groupButton">
            <% 
                if("2".equals(accessType1)&&"2".equals(accessType2)){
            %>
            <input type="button" id="btnSave" value='<bean:message key="screen.m_com.s01_1.button.save"/>' class="button" onclick="saveBank()"/>
            <%} %>
            <input type="button" id="btnExit" value='<bean:message key="screen.m_com.s01_1.button.exit"/>' class="button" onclick="exitBank()"/>
        </div>
        <div class="message">
            <html:messages id="msg" message="true">
                <bean:write name="msg"/><br/>
            </html:messages>
        </div>
        <div class="error">
            <html:messages id="error">
                <bean:write name="error"/><br/>
            </html:messages>
        </div>
    </ts:form>
    </body>
</html>