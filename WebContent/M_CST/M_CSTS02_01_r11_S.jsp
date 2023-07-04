<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@page import="nttdm.bsys.common.fw.BillingSystemUserValueObject"%>
<%@page import="nttdm.bsys.common.util.CommonUtils"%> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Pragma-directive" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Directive" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<link type="text/css" rel="stylesheet" href="css/m_csts02.css" />
   		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
    	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/stylesheet/tabcontent.css"/>
    	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/tabcontent.js"></script>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>     	   		
   		<script type="text/javascript" src="js/m_csts02_01_r11_s.js"></script>
		<title></title>
	</head>
	<body id="m_csts02" onload="initMain(); window.resizeTo(1050,525)">
		<%
		 	BillingSystemUserValueObject uvo = (BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT");
		 	String accessRightM_CST_BIBean = CommonUtils.getAccessRight(uvo, "M-CST-BI");
		 %>
		<bean:define id="accessRightM_CST_BI" value="<%= accessRightM_CST_BIBean%>"/>
		<ts:form action="/M_CSTR08BLogic">
		<t:defineCodeList id="LIST_BANK"/>
		<t:defineCodeList id="LIST_CREDIT_CARD_TYPE"/>
		<t:defineCodeList id="NO_OF_MONTH"/>
		
		<bean:define name="_m_cstForm" property="classViewMode" id="classViewMode"></bean:define>
		<bean:define name="_m_cstForm" property="classNewMode" id="classNewMode"></bean:define>
		<bean:define name="_m_cstForm" property="currentYear" id="currentYear"></bean:define>
		<input type="hidden" id="hiddenMsgExitConfirmation" value="<bean:message key="info.ERR4SC001"/>"/>
		<html:hidden name="_m_cstForm" property="currentYear"/>
		<html:hidden name="_m_cstForm" property="id_cust"/>
		<html:hidden name="_m_cstForm" property="mode"/>
		<html:hidden name="_m_cstForm" property="expiredDate"/>
		<html:hidden name="_m_cstForm" property="hasBankInfo"/>
		<html:hidden name="_m_cstForm" property="bank"/>
		<html:hidden name="_m_cstForm" property="acctNumberMsg"/>
		<html:hidden name="_m_cstForm" property="creditCardNumberMsg"/>
		<html:hidden name="_m_cstForm" property="popupClickYesFlg"/>
		<input type="hidden" id="classModeFlg" name="classModeFlg" value="${classNewMode}"/>
		<logic:notEmpty name="_m_cstForm" property="bankList">
			
			<!-- fix in case has ONE bank element -->
			<html:hidden name="bankElement" property="idBank" value=""/>
			<html:hidden name="bankElement" property="bankFullName" value=""/>
			<html:hidden name="bankElement" property="bankBicCode" value=""/>
			<html:hidden name="bankElement" property="bankCode" value=""/>
			<html:hidden name="bankElement" property="branchCode" value=""/>
			
			<logic:iterate name="_m_cstForm" property="bankList" id="bankElement">
				<html:hidden name="bankElement" property="idBank" value="${bankElement.idBank}"/>
				<html:hidden name="bankElement" property="bankFullName" value="${bankElement.bankFullName}"/>
				<html:hidden name="bankElement" property="bankBicCode" value="${bankElement.bankBicCode}"/>				
				<html:hidden name="bankElement" property="bankCode" value="${bankElement.bankCode}"/>
				<html:hidden name="bankElement" property="branchCode" value="${bankElement.branchCode}"/>
				<bean:define id="bankCode1" name="bankElement" property="bankCode"></bean:define>
				<bean:define id="branchCode1" name="bankElement" property="branchCode"></bean:define>
				<input type="hidden" name="<%=""+bankCode1 + branchCode1 %>" value="${bankElement.idBank}">
				<input type="hidden" name="<%=""+bankCode1 + branchCode1 %>" value="${bankElement.bankFullName}">
				<input type="hidden" name="<%=""+bankCode1 + branchCode1 %>" value="${bankElement.bankBicCode}">
			</logic:iterate>
		</logic:notEmpty>
		
		<table border="0" height="250px" width="1000px" cellpadding="0" cellspacing="0">
		<tr height="10px" width="999px"><td><table class="subHeader" cellpadding="0" cellspacing="0">
    		<tr>
    			<td><bean:message key="screen.m_cst_s.label_bank_infomation"/></td>
    		</tr>
    	</table></td></tr>
    	<!-- Giro Infomation table -->
    	<tr height="211px" width="999px"><td><table class="information" border="0" height="210px" width="998px" align="left" cellpadding="0" cellspacing="1">
		<tr height="10px" width="998px"><td colspan="7" class="header">
			<bean:message key="screen.m_cst_s.label_giro_infomation"/></td>
		</tr>		
		<tr class="" align="left" valign="top" height="8px" width="998px">
		   <td colspan="1" width="150px">
			<!-- Bank -->
				<bean:message key="screen.m_cst_s.label_bank_full_name"/>
			</td>	
			<td colspan="1" width="3px">
				<bean:message key="screen.m_cst.label_colon"/>	
			</td>		
			<td colspan="1" width="290px">
				<span class='<%= classViewMode %>'>				
					<bean:write name="_m_cstForm" property="bankFullName" />
				</span>
				<!-- loading bank -->
		 	 	<span class='<%= classNewMode%>' id="bank_full_name">
		 	 	    <bean:write name="_m_cstForm" property="bankFullName" />
		 	 	</span>				
			</td>	
			<td colspan="1" width="11px"></td>
		    <!-- swift code. -->
			<td colspan="1" width="200px">
				<bean:message key="screen.m_cst_s.label_swift_code"/>
			</td>
			<td colspan="1" width="3px">
				<bean:message key="screen.m_cst.label_colon"/>	
			</td>
			<td colspan="1" width="340px">
				<span id="spn_co_regno" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="swiftCode"></bean:write></span>
				<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="swiftCode" size="25" maxlength="20"></html:text></span>				
			</td>			
		</tr>
		<tr class="" align="left" valign="top" height="8px" width="998px">
			<td colspan="1" width="150px">
			<!-- Bank BIC Code -->
				<bean:message key="screen.m_cst_s.label_bank_bic_code"/>
			</td>	
			<td colspan="1" width="3px">
				<bean:message key="screen.m_cst.label_colon"/>	
			</td>				
			<td colspan="1" width="290px">
				<span class='<%= classViewMode %>'>
					<bean:write name="_m_cstForm" property="bankBicCode" />
				</span>
				<!-- loading bank -->
		   	    <span class='<%= classNewMode%>' id="bank_bic_code">
		   	        <bean:write name="_m_cstForm" property="bankBicCode" />
		   		</span>		
			</td>
			<td colspan="1" width="11px"></td>
		<!-- Acct Number. -->
			<td colspan="1" width="200px">
				<bean:message key="screen.m_cst_s.label_bank_acct_number"/>
			</td>
			<td colspan="1" width="3px">
				<bean:message key="screen.m_cst.label_colon"/>	
			</td>
			<td colspan="1" width="340px">
				<span id="spn_co_regno" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="acctNumber"></bean:write></span>
				<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="acctNumber" size="50" maxlength="34"></html:text></span>				
			</td>
		</tr>				
		<tr class="" align="left" valign="top" height="8px" width="998px">
		    <td colspan="1" width="150px">
			<!-- Bank Code -->
				<bean:message key="screen.m_cst_s.label_bank_code"/>
			</td>
			<td colspan="1" width="3px">
				<bean:message key="screen.m_cst.label_colon"/>	
			</td>
			<td colspan="1" width="290px">
				<span class='<%= classViewMode %>'>
					<bean:write name="_m_cstForm" property="cbBankCode"/>
				</span>
			<!-- loading bank -->
				<span class='<%= classNewMode%>'>
			    	<html:text name="_m_cstForm" property="cbBankCode" size="25" maxlength="10" onblur="loadBankBranchCode()"/>		
			    </span>			
			</td>
			<td colspan="1" width="11px"></td>
		<!-- Acct Name. -->
			<td colspan="1" width="200px">
				<bean:message key="screen.m_cst_s.label_acct_name"/>
			</td>
			<td colspan="1" width="3px">
				<bean:message key="screen.m_cst.label_colon"/>	
			</td>
			<td colspan="1" width="340px" style="word-wrap:break-word">
				<span id="spn_co_regno" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="acctName"></bean:write></span>
				<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="acctName" size="50" maxlength="140"></html:text></span>				
			</td>
		</tr>		
		<tr class="" align="left" valign="top" height="8px" width="998px">
			 <td colspan="1" width="150px">
			<!-- Branch Name -->
				<bean:message key="screen.m_cst_s.label_branch_code"/>
			</td>
			<td colspan="1" width="3px">
				<bean:message key="screen.m_cst.label_colon"/>	
			</td>
			<td colspan="1" width="290px">
				<span class='<%= classViewMode %>'>
					<bean:write name="_m_cstForm" property="cbBranchCode"/>
				</span>
			<!-- loading bank -->
				<span class='<%= classNewMode%>'>
			    	<html:text name="_m_cstForm" property="cbBranchCode" size="25" maxlength="10" onblur="loadBankBranchCode()"/>				        
			    </span>			
			</td>
			<td colspan="1" width="11px"></td>
			<td colspan="1" width="200px">
				<bean:message key="screen.m_cst_s.label_reference_number"/>
			</td>
			<td colspan="1" width="3px">
				<bean:message key="screen.m_cst.label_colon"/>	
			</td>							
			<td colspan="1" width="340px">				
				<span id="spn_co_ref" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="refNum"></bean:write></span>
				<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="refNum" size="50" maxlength="35"></html:text></span>
			</td>
		</tr>
					
		<!-- Credit card table -->
		<tr height="10px" width="998px"><td colspan="7" class="header">
				<bean:message key="screen.m_cst_s.label_credit_card_information"/></td>
		</tr>
	<!-- Card Type -->
		<tr class="" align="left" valign="top" height="8px" width="998px">
			<td colspan="1" width="150px">
				<bean:message key="screen.m_cst_s.label_card_type"/>
			</td>
			<td colspan="1" width="3px">
				<bean:message key="screen.m_cst.label_colon"/>	
			</td>					
			<td colspan="1" width="290px">
				<span class='<%= classViewMode %>'>
					<logic:equal name="_m_cstForm" property="mode" value="READONLY">
						<logic:notEmpty name="_m_cstForm" property="cardType">
							<t:writeCodeValue name="_m_cstForm" property="cardType" codeList="LIST_CREDIT_CARD_TYPE"></t:writeCodeValue>
						</logic:notEmpty>
					</logic:equal>
				</span>
			<!-- loading bank -->
				<span id="spn_ra_country_id" class="hidden"><bean:write name="_m_cstForm" property="cardType"/></span>
	       	 	<span class='<%= classNewMode%>'>
		   	 		<html:select name="_m_cstForm" property="cardType" >
	          			<html:option value="" ><bean:message key="screen.m_cst.label_blank"/></html:option>
	          			<html:options collection="LIST_CREDIT_CARD_TYPE" property="id" labelProperty="name"/>
		       		</html:select>					
	        	</span>					
	    	</td>
	    	<td colspan="1" width="11px"></td>
		<!-- Holder Name. -->
			<td colspan="1" width="200px">
				<bean:message key="screen.m_cst_s.label_holder_name"/>
			</td>
			<td colspan="1" width="3px">
				<bean:message key="screen.m_cst.label_colon"/>	
			</td>
			<td colspan="1" width="340px">
				<span id="spn_co_regno" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="holderName"></bean:write></span>
				<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="holderName" size="25" maxlength="50"></html:text></span>				
			</td>
		</tr>	
		<tr class="" align="left" valign="top" height="8px" width="998px">
			<td colspan="1" width="150px">
			<!-- Acct Number -->
				<bean:message key="screen.m_cst_s.label_acct_number"/>
			</td>
			<td colspan="1" width="3px">
				<bean:message key="screen.m_cst.label_colon"/>	
			</td>
			<td colspan="1" width="290px">
				<span id="spn_co_regno" class='<%= classViewMode %>'><bean:write name="_m_cstForm" property="acctNumberCredit"></bean:write></span>
				<span class='<%= classNewMode%>'><html:text name="_m_cstForm" property="acctNumberCredit" size="25" maxlength="20"></html:text></span>				
			</td>
			<td colspan="1" width="11px"></td>
		<!-- Expired Date -->
			<td colspan="1" width="200px">
				<bean:message key="screen.m_cst_s.label_expired_date"/>
			</td>
			<td colspan="1" width="3px">
				<bean:message key="screen.m_cst.label_colon"/>	
			</td>
			<td colspan="1" width="340px">
			<!-- expired date month -->
				<logic:equal name="_m_cstForm" property="mode" value="READONLY">
					<logic:notEmpty name="_m_cstForm" property="expiredDate">
						<bean:write name="_m_cstForm" property="expiredDate"/>
					</logic:notEmpty>
				</logic:equal>
			<!-- loading expired date month -->
	       		<span class='<%= classNewMode%>'>
	        		<html:select name="_m_cstForm" property="expiredDateMonth" >
	          			<html:option value="" ><bean:message key="screen.m_cst.label_blank"/></html:option>
	          			<html:options collection="NO_OF_MONTH" property="id" labelProperty="name"/>
		       		</html:select>					
	        	</span>		
			<!-- loading expired date year-->
	        	<span class='<%= classNewMode%>'>
	        		<html:select name="_m_cstForm" property="expiredDateYear" >
		       			<html:option value="" ><bean:message key="screen.m_cst.label_blank"/></html:option>
		       			<html:option value="<%=  currentYear.toString()%>" ><%=  currentYear%></html:option>
		       			<html:option value="<%=  String.valueOf(Integer.parseInt(currentYear.toString()) + 1)%>" ><%=  Integer.parseInt(currentYear.toString()) + 1 %></html:option>
		       			<html:option value="<%=  String.valueOf(Integer.parseInt(currentYear.toString()) + 2)%>" ><%=  Integer.parseInt(currentYear.toString()) + 2 %></html:option>
			          	<html:option value="<%=  String.valueOf(Integer.parseInt(currentYear.toString()) + 3)%>" ><%=  Integer.parseInt(currentYear.toString()) + 3 %></html:option>
			          	<html:option value="<%=  String.valueOf(Integer.parseInt(currentYear.toString()) + 4)%>" ><%=  Integer.parseInt(currentYear.toString()) + 4 %></html:option>
			          	<html:option value="<%=  String.valueOf(Integer.parseInt(currentYear.toString()) + 5)%>" ><%=  Integer.parseInt(currentYear.toString()) + 5 %></html:option>
			          	<html:option value="<%=  String.valueOf(Integer.parseInt(currentYear.toString()) + 6)%>" ><%=  Integer.parseInt(currentYear.toString()) + 6 %></html:option>
			          	<html:option value="<%=  String.valueOf(Integer.parseInt(currentYear.toString()) + 7)%>" ><%=  Integer.parseInt(currentYear.toString()) + 7 %></html:option>
			          	<html:option value="<%=  String.valueOf(Integer.parseInt(currentYear.toString()) + 8)%>" ><%=  Integer.parseInt(currentYear.toString()) + 8 %></html:option>
			          	<html:option value="<%=  String.valueOf(Integer.parseInt(currentYear.toString()) + 9)%>" ><%=  Integer.parseInt(currentYear.toString()) + 9 %></html:option>
			          	<html:option value="<%=  String.valueOf(Integer.parseInt(currentYear.toString()) + 10)%>" ><%=  Integer.parseInt(currentYear.toString()) + 10 %></html:option>
			        </html:select>					
			    </span>	
			</td>
		</tr>	
		<tr class="" align="left" valign="top" height="8px" width="998px">
			<td colspan="1" width="150px">
			<!-- Credit card Number -->
				<bean:message key="screen.m_cst_s.label_credit_card_number"/>
			</td>
			<td colspan="1" width="3px">
				<bean:message key="screen.m_cst.label_colon"/>	
			</td>						
			<td colspan="1" width="290px">
				<html:hidden name="_m_cstForm" property="creditCardNumber"/>
				<span id="spn_co_regno" class='<%= classViewMode %>'>
					<label id="labCreditCardNumber"></label>
				</span>
				<span class='<%= classNewMode%>'>
					<input type="text" name="creditCardNumberInput" id="creditCardNumberInput" value="${_m_cstForm.creditCardNumber}" size="25" maxlength="20" onchange="changeCreditCardNumber()" onblur="blurCreditCardNumber()" onfocus="focusCreditCardNumber()"/>
				</span>
			</td>
			<td colspan="1" width="11px"></td>				
			<td colspan="1" width="200px"></td><td colspan="1" width="3px"></td>
			<td colspan="1" width="340px"></td>				
		</tr>
		<tr class="" align="left" valign="top" height="8px" width="998px">
			<td colspan="1" width="150px">
			<!-- Security No. -->	
                                <bean:message key="screen.m_cst_s.label_security_no"/>
			</td>
			<td colspan="1" width="3px">
				<bean:message key="screen.m_cst.label_colon"/>
			</td>
			<td colspan="1" width="290px">
				<html:hidden name="_m_cstForm" property="securityNo"/>					
				<span id="spn_co_regno" class='<%= classViewMode %>'>
					<label id="labSecurityNo"></label>
				</span>
				<span class='<%= classNewMode%>'>
					<input type="text" name="securityNoInput" id="securityNoInput" value="${_m_cstForm.securityNo}" size="25" maxlength="4" onchange="changeSecurityNo()" onblur="blurSecurityNo()" onfocus="focusSecurityNo()"/>
				</span>
			</td>
			<td colspan="1" width="11px"></td>
			<td colspan="1" width="200px"></td><td colspan="1" width="3px"></td>
			<td colspan="1" width="340px"></td>	
		</tr></table>	
	</td></tr>
	</table>
		
		<hr class="lineBottom" size="3">
		<table class="buttonGroup" cellpadding="0" cellspacing="0">
			<tr><td>		
			<span class='<%= classViewMode%>'>	
<%-- 				<logic:equal name="_m_cstForm" property="mode" value="READONLY"> --%>
				<c:if test="${'2' eq accessRightM_CST_BI}">
					<button onclick="submitForm('bankInfo','', '<%=request.getContextPath()%>','')"><bean:message key="screen.m_cst_s.button_edit"/></button>&nbsp;
				</c:if>
<%-- 				</logic:equal>	 --%>
				<button onclick="window.close();"><bean:message key="screen.m_cst_s.button_cancel"/></button>&nbsp;
			</span></td>
				<td><span class='<%= classNewMode%>'>	
<%-- 				<logic:notEqual name="_m_cstForm" property="mode" value="READONLY"> --%>
					<button onclick="submitForm('save','','<%= request.getContextPath() %>','')" id="btnSave"><bean:message key="screen.m_cst_s.button_save"/></button>&nbsp;
<%-- 				</logic:notEqual> --%>
				<button onclick="ClickExit()"><bean:message key="screen.m_cst_s.button_cancel"/></button>&nbsp;
			</span></td></tr>
		</table>
		</ts:form>
	    <div class="error">
			<html:messages id="message">
				<bean:write name="message"/><br/>
			</html:messages>
		</div>
		<div class="message">
			<ts:messages id="message" message="true">
				<bean:write name="message" />
			</ts:messages>
		</div>
	</body>
</html:html>
