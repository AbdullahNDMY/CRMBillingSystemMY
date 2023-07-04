<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="nttdm.bsys.common.fw.BillingSystemUserValueObject" %>
<%@ page import="nttdm.bsys.c_cmn001.bean.UserAccess" %>
<%@ page import="nttdm.bsys.m_bnk.bean.M_BNK_AdressRA" %>
<%@page import="nttdm.bsys.common.util.CommonUtils"%> 
<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
	   	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
		<link href="<%=request.getContextPath()%>/M_BNK/css/m_bnks02.css" rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/M_BNK/css/style.css" rel="stylesheet" type="text/css" />
    	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/stylesheet/tabcontent.css"/>
		 <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
		 <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
		 <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
    	 <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/tabcontent.js"></script>
   		 <script type="text/javascript" src="<%=request.getContextPath()%>/M_BNK/js/m_bnk.js"></script>
		<script language="javascript">
			function setlabel()
			{
				var index_=document.getElementById("cboAddressCountryRA").selectedIndex;
				document.getElementById('lbl_country').value=document.getElementById("cboAddressCountryRA").options[index_].text;				
				if(document.getElementById("cboAddressCountryCA") != null)
				{
				var index_ca=document.getElementById("cboAddressCountryCA").selectedIndex;
				document.getElementById('lbl_country_ca').value=document.getElementById("cboAddressCountryCA").options[index_ca].text;				
				}
				//init tab
				var contactTabs = new ddtabcontent("contactTabs");
				contactTabs.setpersist(true);
				contactTabs.setselectedClassTarget("link");
				contactTabs.init();
			}
			function confirmDelete() {	
				var MsgBox = new messageBox();
				if (MsgBox.POPCFM(document.getElementById("info.ERR4SC002").value) == MsgBox.YES_CONFIRM) {
				//if (confirm(document.getElementById("info.ERR4SC002").value)) {
					var button=	document.getElementById("forward_delete"); 
					button.click();
				}
			}
		</script>	
	</head>
	<ts:body onload="setlabel();">
	<%
		BillingSystemUserValueObject uvo = (BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT");
	 	String useraccess = CommonUtils.getAccessRight(uvo, "M-BNK");
	%>
		<input type="hidden" name="lblidlogin" value="<%=uvo.getId_user() %>" />
		<input type="hidden" id="info.ERR4SC002" value="<bean:message key="info.ERR4SC002" />" />
		<table class="subHeader" cellpadding="0" cellspacing="0">
		  <tr style="">
		    <td class="Title"><bean:message key="screen.m_bnk.title"/></td> 
		  </tr>
		</table>
		<ts:form action="/M_BNKS02vDSP" >	
			<table class = "generalInfo" cellpadding="0" cellspacing="0">
					<tr>
						<td class="header" colspan="5"><bean:message key="screen.m_bnk.generalinformation"/>									
						</td>
					</tr>
					<tr>
						<td class="colLeft"><bean:message key="screen.m_bnk.bankfullname" /></td>
						<td class="colmid" ><bean:message key="screen.m_bnk.colon"/></td>
						<td class="colRight" ><bean:write name="bankbeaninfo" property="BANK_FULL_NAME"/></td>
						<input type="hidden" name="lblidbank" value="<bean:write name="bankbeaninfo" property="ID_BANK"/>"/>
					</tr>
					<tr>
						<td class="colLeft"><bean:message key="screen.m_bnk.bankcode"/></td>
						<td class="colmid"><bean:message key="screen.m_bnk.colon"/></td>
						<td><bean:write name="bankbeaninfo" property="BANK_CODE"/></td>					
					</tr>
					<tr>
						<td class="colLeft"><bean:message key="screen.m_bnk.bankname"/></td>
						<td><bean:message key="screen.m_bnk.colon"/></td>
						<td class="colRight" ><bean:write name="bankbeaninfo" property="BANK_NAME"/></td>
					</tr>
					<tr>
						<td class="colLeft"><bean:message key="screen.m_bnk.branchcode"/></td>
						<td><bean:message key="screen.m_bnk.colon"/></td>
						<td class="colRight" ><bean:write name="bankbeaninfo" property="BRANCH_CODE"/></td>
					</tr>
					<tr>
						<td class="colLeft"><bean:message key="screen.m_bnk.branchname"/></td>
						<td><bean:message key="screen.m_bnk.colon"/></td>
						<td class="colRight" ><bean:write name="bankbeaninfo" property="BRANCH_NAME"/></td>
					</tr>
					<tr>
						<td class="colLeft"><bean:message key="screen.m_bnk.bankbiccode"/></td>
						<td><bean:message key="screen.m_bnk.colon"/></td>
						<td class="colRight" ><bean:write name="bankbeaninfo" property="BIC_CODE"/></td>
					</tr>
					<tr>
						<td class="colLeft"><bean:message key="screen.m_bnk.telno"/></td>
						<td><bean:message key="screen.m_bnk.colon"/></td>
						<td class="colRight" ><bean:write name="bankbeaninfo" property="BANK_TEL_NO"/></td>
					</tr>
					<tr>
						<td class="colLeft"><bean:message key="screen.m_bnk.faxno"/></td>
						<td><bean:message key="screen.m_bnk.colon"/></td>
						<td class="colRight" ><bean:write name="bankbeaninfo" property="BANK_FAX_NO"/></td>
					</tr>
			</table>
			<table class = "addressinfo" cellpadding="0" cellspacing="0">
				<tr><td class="header" colspan="3"><bean:message key="screen.m_bnk.addresses"/></td></tr>		
					<tr><td><table border="0" width="100%" align="left" cellpadding="0" cellspacing="0">
						<tr><td><table border="0" width="978px" align="left" cellpadding="0" cellspacing="0">
							<tr><td colspan="1" width="410px" height="8px" class="adrr"><b><u><bean:message key="screen.m_bnk.registeredaddress"/></u></b><bean:message key="screen.m_bnk.important"/></td>
								<td colspan="1" width="80px" height="8px"></td>
								<td colspan="1" width="410px" height="8px" class="adrr"><b><u><bean:message key="screen.m_bnk.correspondanceaddress"/></u></b></td>
							</tr>
							<tr height="10px" align="left" valign="top"><td colspan="1" class="adrrbd">							
								<bean:write name="addressRA" property="BK_ADR_LINE1"/>
								</td>
								<td colspan="1" width="80px"></td>
								<td colspan="1" class="adrrbd">
									<bean:write name="addressCA" property="BK_ADR_LINE1"/>
								</td>
							</tr>
							<tr height="10px" align="left" valign="top"><td colspan="1" class="adrrbd">	
								<bean:write name="addressRA" property="BK_ADR_LINE2"/>
								</td>
								<td colspan="1" width="80px"></td>
								<td colspan="1" class="adrrbd">
									<bean:write name="addressCA" property="BK_ADR_LINE2"/>
								</td>
							</tr>
							<tr height="10px" align="left" valign="top"><td colspan="1" class="adrrbd">	
								<bean:write name="addressRA" property="BK_ADR_LINE3"/>
								</td>
								<td colspan="1" width="80px"></td>
								<td colspan="1" class="adrrbd">
									<bean:write name="addressCA" property="BK_ADR_LINE3"/>
								</td>
							</tr>
							<%
								if(session.getAttribute("addressRA") != null)
		 						{
		 							M_BNK_AdressRA addrA=(M_BNK_AdressRA)session.getAttribute("addressRA");
									M_BNK_AdressRA addcA=(M_BNK_AdressRA)session.getAttribute("addressCA");
							%>	
							<tr height="10px" align="left" valign="top"><td class="adrrbd" width="410px">
								<bean:write name="addressRA" property="BK_ZIP_CODE"/>
								<html:text property="lbl_country" readonly="true"  value="  " style="border:none; background:none;"></html:text>								
								<t:defineCodeList id="LIST_COUNTRY" />	
									<html:select name="m_bnkForm" property="cboAddressCountryRA"  styleClass="CashBookTextBox" disabled="true" value="<%=addrA.getCOUNTRY()%>" style="visibility:hidden; width:1px; height:1px;" >
										<html:option value=""></html:option>								
										<html:options collection="LIST_COUNTRY" property="id" labelProperty="name"/>
									</html:select>								
								</td>
								<td width="80px"></td>
								<td width="410px" class="adrrbd">				
									<bean:write name="addressCA" property="BK_ZIP_CODE"/>
									<html:text property="lbl_country_ca" readonly="true"  value="  " style="border:none; background:none;"></html:text>								
									<t:defineCodeList id="LIST_COUNTRY" />	
										<html:select name="m_bnkForm" property="cboAddressCountryCA"  styleClass="CashBookTextBox" disabled="true" value="<%=addcA.getCOUNTRY()%>" style="visibility:hidden; width:2px; height:1px;" >
											<html:option value=""></html:option>							
											<html:options collection="LIST_COUNTRY" property="id" labelProperty="name"/>
									</html:select>
								</td>
							</tr>
							<%
								}
							%>	
							</table>
					</td></tr>
				</table>
			</td></tr>
		</table>
			
		<table class = "addressinfo" cellpadding="0" cellspacing="0">
		<tr>
			<td class="header" colspan="5"><bean:message key="screen.m_bnk.contactinformation"/>					
			</td>
					</tr>		
		<tr>	
		<td>	
		<div id="tabbed_box_1" class="tabbed_box">
		<div class="tabbed_area">
		<ul id="contactTabs" class="shadetabs">
			<li><a href="#" rel="contact_1" class="selected"><bean:message key="screen.m_bnk.primarycontact"/></a></li>
			<li><a href="#" rel="contact_2"><bean:message key="screen.m_bnk.secondarycontact"/></a></li>
			<li><a href="#" rel="contact_3"><bean:message key="screen.m_bnk.othercontact"/></a></li>
		</ul>
			<div id="contact_1" class="tabcontent contact">
				<table width="90%">
					<tr>
						<td width="15%"><bean:message key="screen.m_bnk.contactname"/></td>
						<td colspan="3"> <bean:message key="screen.m_bnk.colon"/>
							<bean:write name="contactPC" property="BK_CTC_NAME"/>
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.designation"/></td>
						<td colspan="3"><bean:message key="screen.m_bnk.colon"/>
							<bean:write name="contactPC" property="BK_CTC_DESIGNATION"/>
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.email"/></td>
						<td colspan="3"><bean:message key="screen.m_bnk.colon"/>
							<bean:write name="contactPC" property="BK_CTC_EMAIL"/>
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.telephoneno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>
							<bean:write name="contactPC" property="BK_CTC_TEL_NO"/>
						</td>
						<td width="15%"><bean:message key="screen.m_bnk.faxno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>
							<bean:write name="contactPC" property="BK_CTC_FAX_NO"/>
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.didno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>
							<bean:write name="contactPC" property="BK_CTC_DID_NO"/>
						</td>
						<td width="15%"><bean:message key="screen.m_bnk.mobileno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>
							<bean:write name="contactPC" property="BK_CTC_MOBILE_NO"/>
						</td>				
					</tr>			
				</table>
			</div>
				<%
					if(session.getAttribute("contactBC") != null)
					{		
				 %>
			<div id="contact_2" class="tabcontent contact">
				<table width="90%">
					<tr>
						<td width="15%"><bean:message key="screen.m_bnk.contactname"/></td>
						<td colspan="3"> <bean:message key="screen.m_bnk.colon"/>
							<bean:write name="contactBC" property="BK_CTC_NAME"/>
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.designation"/></td>
						<td colspan="3"><bean:message key="screen.m_bnk.colon"/>
							<bean:write name="contactBC" property="BK_CTC_DESIGNATION"/>
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.email"/></td>
						<td colspan="3"><bean:message key="screen.m_bnk.colon"/>
							<bean:write name="contactBC" property="BK_CTC_EMAIL"/>
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.telephoneno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>
							<bean:write name="contactBC" property="BK_CTC_TEL_NO"/>
						</td>
						<td width="15%"><bean:message key="screen.m_bnk.faxno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>
							<bean:write name="contactBC" property="BK_CTC_FAX_NO"/>
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.didno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>
							<bean:write name="contactBC" property="BK_CTC_DID_NO"/>
						</td>
						<td width="15%"><bean:message key="screen.m_bnk.mobileno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>
							<bean:write name="contactBC" property="BK_CTC_MOBILE_NO"/>
						</td>				
					</tr>					
				
				</table>
			</div>
			<%
				}
				else
				{
			%>
						<div id="contact_2" class="tabcontent contact">
				<table width="90%">
					<tr>
						<td width="15%"><bean:message key="screen.m_bnk.contactname"/></td>
						<td colspan="3"> <bean:message key="screen.m_bnk.colon"/>
							
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.designation"/></td>
						<td colspan="3"><bean:message key="screen.m_bnk.colon"/>		
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.email"/></td>
						<td colspan="3"><bean:message key="screen.m_bnk.colon"/>	
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.telephoneno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>
							
						</td>
						<td width="15%"><bean:message key="screen.m_bnk.faxno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>	
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.didno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>	
						</td>
						<td width="15%"><bean:message key="screen.m_bnk.mobileno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>
						</td>				
					</tr>					
				</table>
			</div>
			<%	
				}
			 		if(session.getAttribute("contactOC") != null)
			 		{
			%>	
			<div id="contact_3" class="tabcontent contact">
				<table width="90%">
					<tr>
						<td width="15%"><bean:message key="screen.m_bnk.contactname"/></td>
						<td colspan="3"> <bean:message key="screen.m_bnk.colon"/>
							<bean:write name="contactOC" property="BK_CTC_NAME"/>
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.designation"/></td>
						<td colspan="3"><bean:message key="screen.m_bnk.colon"/>
							<bean:write name="contactOC" property="BK_CTC_DESIGNATION"/>
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.email"/></td>
						<td colspan="3"><bean:message key="screen.m_bnk.colon"/>
							<bean:write name="contactOC" property="BK_CTC_EMAIL"/>
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.telephoneno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>
							<bean:write name="contactOC" property="BK_CTC_TEL_NO"/>
						</td>
						<td width="15%"><bean:message key="screen.m_bnk.faxno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>
							<bean:write name="contactOC" property="BK_CTC_FAX_NO"/>
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.didno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>
							<bean:write name="contactOC" property="BK_CTC_DID_NO"/>
						</td>
						<td width="15%"><bean:message key="screen.m_bnk.mobileno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>
							<bean:write name="contactOC" property="BK_CTC_MOBILE_NO"/>
						</td>				
					</tr>					
				
				</table>
			</div>
			<%
			}
			else 
			{
			 %>
			 <div id="contact_3" class="tabcontent contact">
				<table width="90%">
					<tr>
						<td width="15%"><bean:message key="screen.m_bnk.contactname"/></td>
						<td colspan="3"> <bean:message key="screen.m_bnk.colon"/>	
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.designation"/></td>
						<td colspan="3"><bean:message key="screen.m_bnk.colon"/>	
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.email"/></td>
						<td colspan="3"><bean:message key="screen.m_bnk.colon"/>	
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.telephoneno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>	
						</td>
						<td width="15%"><bean:message key="screen.m_bnk.faxno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>	
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.didno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>	
						</td>
						<td width="15%"><bean:message key="screen.m_bnk.mobileno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>
						</td>				
					</tr>					
				</table>
			</div>
			 <%
			 }			 
			  %>
		</div>
		</div>		
		</td>
		</tr>			
		</table>			
			<hr/>
		<%
			if(useraccess.equals("2"))
			{
		 %>
			<input type="submit" class="button" value="<bean:message key="screen.m_bnk.editbutton"/>" name="forward_edit" style="width:60px; font-size:13px;" />
			<input type="button" class="button" value="<bean:message key="screen.m_bnk.deletebutton"/>" style="width:60px; font-size:13px;" onclick="confirmDelete();"/>
			<%
			}
			 %>
			<input type="submit" class="button" value="<bean:message key="screen.m_bnk.exitbutton"/>" name="forward_exit" style="width:60px; font-size:13px;" />
			<input type="submit" class="button" value="<bean:message key="screen.m_bnk.deletebutton"/>" name="forward_delete" style="width:60px; font-size:13px;" style="visibility:hidden;" />
		</ts:form>
		<div class="message">
    		<ts:messages id="messages" message="true">
    			<bean:write name="messages"/>
    		</ts:messages>
       	</div>
		</ts:body>
		</html:html>