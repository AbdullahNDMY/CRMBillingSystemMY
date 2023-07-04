<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="nttdm.bsys.common.fw.BillingSystemUserValueObject" %>
<%@ page import="nttdm.bsys.c_cmn001.bean.UserAccess" %>

<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
	   	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
		<link href="<%=request.getContextPath()%>/M_BNK/css/m_bnks02.css" rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/M_BNK/css/style.css" rel="stylesheet" type="text/css" />
    	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/stylesheet/tabcontent.css"/>
		<script type="text/javascript" src="js/jquery-1.js"></script>		
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
    	 <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/tabcontent.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/M_BNK/js/m_bnk.js"></script>
		<script language="javascript"">	
			function clear_text(){
				for(var i=0; i<document.forms.length; i++){
					for(var j=0; j<document.forms[i].elements.length; j++){
						if(document.forms[i].elements[j].type == 'text'){
							document.forms[i].elements[j].value='';
						}
					}	
				}
			}
			
			function confirm_save()
			{
				if(document.getElementById("tbxZipCodeRA").value== "Zip Code")
					document.getElementById("tbxZipCodeRA").value= "";
				if(document.getElementById("tbxZipCodeCA").value== "Zip Code")
					document.getElementById("tbxZipCodeCA").value= "";
				var button=	document.getElementById("forward_success"); 
				setbankfullname();				
				button.click();
			}
			function confirmExit()
			{
				var mess= new messageBox();
				var agree=mess.POPEXT();
				if (agree ==1)
				{
				var button=	document.getElementById("forward_exit"); 
				button.click();
				}		
			}	

	function setZipCode(){
		if(document.getElementById("tbxZipCodeRA").value=="")
			document.getElementById("tbxZipCodeRA").value="Zip Code";
		if(document.getElementById("tbxZipCodeCA").value=="")
			document.getElementById("tbxZipCodeCA").value="Zip Code";
	}
	function new_bank_onload()
	{
	if(document.getElementById("page_status").value == 1)
	{
		document.getElementById("page_status").value=1;
		document.getElementById("lblBankFullName").value="";
		document.getElementById("lblBankFullName").innerHTML="";
		document.getElementById("tbxBankCode").value="";
		document.getElementById("tbxBankName").value="";   
		document.getElementById("tbxBranchCode").value="";
		document.getElementById( "tbxBranchName").value=""; 
		document.getElementById( "tbxBankBICCode").value=""; 
		document.getElementById("tbxTelNo").value="";
		document.getElementById( "tbxFaxNo").value="";
		document.getElementById("tbxZipCodeRA").value="";
		document.getElementById("tbxZipCodeCA").value="";
		document.getElementById("tbxAddressLine1RA").value="";
		document.getElementById("tbxAddressLine2RA").value="";
		document.getElementById("tbxAddressLine3RA").value="";
		document.getElementById( "tbxAddressLine1CA").value="";
		document.getElementById("tbxAddressLine2CA").value="";
		document.getElementById("tbxAddressLine3CA").value="";		
		document.getElementById("cboAddressCountryRA").value="";
		document.getElementById("cboAddressCountryCA").value="";
		document.getElementById( "tbxContactNamePC").value="";
		document.getElementById("tbxDesignationPC").value="";
		document.getElementById("tbxEmailPC").value="";
		document.getElementById("tbxTelephoneNoPC").value="";
		document.getElementById("tbxDIDNoPC").value="";
		document.getElementById("tbxFaxNoPC").value="";  
		document.getElementById("tbxMobileNoPc").value="";
		document.getElementById("tbxContactNameBC").value="";
		document.getElementById("tbxDesignationBC").value="";
		document.getElementById("tbxEmailBC").value="";
		document.getElementById("tbxTelephoneNoBC").value="";
		document.getElementById( "tbxDIDNoBC").value="";
		document.getElementById("tbxFaxNoBC").value="";
		document.getElementById("tbxMobileNoBC").value="";
		document.getElementById("tbxContactNameOC").value="";
		document.getElementById("tbxDesignationOC").value="";
		document.getElementById( "tbxEmailOC").value="";
		document.getElementById("tbxTelephoneNoOC").value="";    
		document.getElementById("tbxDIDNoOC").value="";    
		document.getElementById("tbxFaxNoOC").value="";  
		document.getElementById("tbxMobileNoOC").value="";  
		}	
		document.getElementById("page_status").value=1;
		//init tab
		var contactTabs = new ddtabcontent("contactTabs");
		contactTabs.setpersist(true);
		contactTabs.setselectedClassTarget("link");
		contactTabs.init();
	}
	function set_page_status()
	{
		document.getElementById("page_status").value=2;
		
	}
		</script>
	</head>
	<ts:body onload="new_bank_onload();setbankfullname();setZipCode();">
	<%
		if(session.getAttribute("USER_VALUE_OBJECT") != null)
		 {
		 BillingSystemUserValueObject uvo=(BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT");
		 // out.print("xin chào :");
	 %>
	 	<!--<bean:write name="USER_VALUE_OBJECT" property="id_user"/>-->
		<table class="subHeader" cellpadding="0" cellspacing="0">
		  <tr style="">
		    <td class="Title"><bean:message key="screen.m_bnk.title"/></td> 
		  </tr>
		</table>		
		<ts:form action="/M_BNKS02neDSP">				
			<html:hidden name="m_bnkForm" property="hypBankReference"></html:hidden>
			<html:hidden property="lblidlogin" name="m_bnkForm" value="<%=uvo.getId_user() %>" ></html:hidden>
			<html:hidden property="lblidbank" name="m_bnkForm" value="<%=uvo.getId_user() %>" ></html:hidden>
			<table class = "generalInfo" cellpadding="0" cellspacing="0">
					<tr>
						<td class="header" colspan="5"><bean:message key="screen.m_bnk.generalinformation"/>										
						</td>
					</tr>			
					<tr height="10">
						<td class="colLeft"><bean:message key="screen.m_bnk.bankfullname" /></td>
						<td class="colmid" ><bean:message key="screen.m_bnk.colon"/></td>
						<td class="colRight" >
						<span id="lblBankFullName">
							<bean:write property="lblBankFullName" name="m_bnkForm"/>
						</span>
						<html:hidden styleId="lblBankFullNameHidden" property="lblBankFullName" name="m_bnkForm" value=""></html:hidden>					
						</td>
					</tr>
					<tr height="10">
						<td class="colLeft"><bean:message key="screen.m_bnk.bankcode"/><bean:message key="screen.m_bnk.important"/></td>
						<td class="colmid"><bean:message key="screen.m_bnk.colon"/></td>
						<td><html:text property="tbxBankCode" name="m_bnkForm" maxlength="4" ></html:text> </td>					
					</tr>
					<tr>
						<td class="colLeft"><bean:message key="screen.m_bnk.bankname"/><bean:message key="screen.m_bnk.important"/></td>
						<td><bean:message key="screen.m_bnk.colon"/></td>
						<td class="colRight" ><html:text property="tbxBankName" name="m_bnkForm" onchange="setbankfullname();" maxlength="100"></html:text></td>
					</tr>
					<tr>
						<td class="colLeft"><bean:message key="screen.m_bnk.branchcode"/><bean:message key="screen.m_bnk.important"/></td>
						<td><bean:message key="screen.m_bnk.colon"/></td>
						<td class="colRight" ><html:text property="tbxBranchCode" name="m_bnkForm" maxlength="3"></html:text></td>
					</tr>
					<tr>
						<td class="colLeft"><bean:message key="screen.m_bnk.branchname"/><bean:message key="screen.m_bnk.important"/></td>
						<td><bean:message key="screen.m_bnk.colon"/></td>
						<td class="colRight" ><html:text property="tbxBranchName" name="m_bnkForm" onchange="setbankfullname();" maxlength="100"></html:text></td>
					</tr>
					<tr>
						<td class="colLeft"><bean:message key="screen.m_bnk.bankbiccode"/><bean:message key="screen.m_bnk.important"/></td>
						<td><bean:message key="screen.m_bnk.colon"/></td>
						<td class="colRight" ><html:text property="tbxBankBICCode" name="m_bnkForm" maxlength="11"></html:text></td>
					</tr>
					<tr>
						<td class="colLeft"><bean:message key="screen.m_bnk.telno"/></td>
						<td><bean:message key="screen.m_bnk.colon"/></td>
						<td class="colRight" ><html:text property="tbxTelNo" name="m_bnkForm" maxlength="20"></html:text></td>
					</tr>
					<tr>
						<td class="colLeft"><bean:message key="screen.m_bnk.faxno"/></td>
						<td><bean:message key="screen.m_bnk.colon"/></td>
						<td class="colRight" ><html:text property="tbxFaxNo" name="m_bnkForm" maxlength="20"></html:text></td>
					</tr>
			</table>
			<table class = "addressinfo" cellpadding="0" cellspacing="0">
				<tr><td class="header" colspan="3"><bean:message key="screen.m_bnk.addresses"/></td></tr>		
					<tr><td><table border="0" width="100%" align="left" cellpadding="0" cellspacing="0">
						<tr><td><table border="0" width="978px" align="left" cellpadding="0" cellspacing="0">
							<tr><td colspan="1" width="410px" height="8px"><b><u><bean:message key="screen.m_bnk.registeredaddress"/></u></b><bean:message key="screen.m_bnk.important"/></td>
								<td colspan="1" width="80px" height="8px"></td>
								<td colspan="1" width="410px" height="8px"><b><u><bean:message key="screen.m_bnk.correspondanceaddress"/></u></b></td>
							</tr>
							<tr height="10px" align="left" valign="top"><td colspan="1" width="410px">
									<html:text property="tbxAddressLine1RA" name="m_bnkForm" style="width:410px; padding-top: 0px;" maxlength="150"></html:text>
								</td>
								<td colspan="1" width="80px"></td>
								<td colspan="1" width="410px">
									<html:text property="tbxAddressLine1CA" name="m_bnkForm" style="width:410px; padding-top: 0px;" maxlength="150"></html:text>
								</td>
							</tr>
							<tr height="10px" align="left" valign="top"><td colspan="1" width="410px">	
									<html:text property="tbxAddressLine2RA" name="m_bnkForm" style="width:410px; padding-top: 0px;" maxlength="150"></html:text>
								</td>
								<td colspan="1" width="80px"></td>
								<td colspan="1" width="410px">
									<html:text property="tbxAddressLine2CA" name="m_bnkForm" style="width:410px; padding-top: 0px;" maxlength="150"></html:text>
								</td>
							</tr>
							<tr height="10px" align="left" valign="top"><td colspan="1" width="410px">	
									<html:text property="tbxAddressLine3RA" name="m_bnkForm" style="width:410px; padding-top: 0px;" maxlength="150"></html:text>
								</td>
								<td colspan="1" width="80px"></td>
								<td colspan="1" width="410px">
									<html:text property="tbxAddressLine3CA" name="m_bnkForm" style="width:410px; padding-top: 0px;" maxlength="150"></html:text>
								</td>
							</tr>
							<tr height="10px" align="left" valign="top"><td width="410px">
									<html:text property="tbxZipCodeRA" name="m_bnkForm" onfocus="if (this.value == 'Zip Code') this.value='';" onblur="if (this.value == '') this.value='Zip Code';" style="width:49%; float:left;padding-top: 0px;" maxlength="15"/>		
									<t:defineCodeList id="LIST_COUNTRY" />	
									<html:select name="m_bnkForm" property="cboAddressCountryRA" styleClass="CashBookTextBox" style="width:49%; float:right;">
									<html:option value="" ><bean:message key="screen.m_bnk.blankitem"/></html:option>							
									<html:options collection="LIST_COUNTRY" property="id" labelProperty="name"/>
									</html:select>
								</td>
								<td width="80px"></td>
								<td width="410px">
									<html:text property="tbxZipCodeCA" name="m_bnkForm" onfocus="if (this.value == 'Zip Code') this.value='';" onblur="if (this.value == '') this.value='Zip Code';" style="width:49%; float:left;padding-top: 0px;" maxlength="15"></html:text>
									<t:defineCodeList id="LIST_COUNTRY" />	
									<html:select name="m_bnkForm" property="cboAddressCountryCA" styleClass="CashBookTextBox" style="width:49%; float:right;">
									<html:option value="" ><bean:message key="screen.m_bnk.blankitem"/></html:option>							
									<html:options collection="LIST_COUNTRY" property="id" labelProperty="name"/>
									</html:select>
								</td>
						</tr></table>
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
				<table width="90%" cellpadding="0" cellspacing="0" >
					<tr>
						<td width="15%"><bean:message key="screen.m_bnk.contactname"/><bean:message key="screen.m_bnk.important"/></td>
						<td colspan="3"> <bean:message key="screen.m_bnk.colon"/>
							<html:text property="tbxContactNamePC" name="m_bnkForm" maxlength="50"></html:text>
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.designation"/></td>
						<td colspan="3"><bean:message key="screen.m_bnk.colon"/>
							<html:text property="tbxDesignationPC" name="m_bnkForm" maxlength="50" ></html:text>
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.email"/></td>
						<td colspan="3"><bean:message key="screen.m_bnk.colon"/>
							<html:text property="tbxEmailPC" name="m_bnkForm" maxlength="50"></html:text>
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.telephoneno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>
						<html:text property="tbxTelephoneNoPC" name="m_bnkForm" maxlength="20"> </html:text>
						</td>
						<td width="15%"><bean:message key="screen.m_bnk.faxno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>
							<html:text property="tbxFaxNoPC" name="m_bnkForm" maxlength="20"></html:text>
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.didno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>
							<html:text property="tbxDIDNoPC" name="m_bnkForm" maxlength="20"></html:text>
						</td>
						<td width="15%"><bean:message key="screen.m_bnk.mobileno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>
							<html:text property="tbxMobileNoPc" name="m_bnkForm" maxlength="20"></html:text>
						</td>				
					</tr>	
				</table>
			</div>
			<div id="contact_2" class="tabcontent contact">
				<table width="90%" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<td width="15%"><bean:message key="screen.m_bnk.contactname"/></td>
						<td colspan="3"> <bean:message key="screen.m_bnk.colon"/>
							<html:text property="tbxContactNameBC" name="m_bnkForm" maxlength="50"></html:text>
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.designation"/></td>
						<td colspan="3"><bean:message key="screen.m_bnk.colon"/>
							<html:text property="tbxDesignationBC" name="m_bnkForm" maxlength="50"> </html:text>
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.email"/></td>
						<td colspan="3"><bean:message key="screen.m_bnk.colon"/>
							<html:text property="tbxEmailBC" name="m_bnkForm" maxlength="50"> </html:text>
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.telephoneno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>
						<html:text property="tbxTelephoneNoBC" name="m_bnkForm" maxlength="20"></html:text>
						</td>
						<td width="15%"><bean:message key="screen.m_bnk.faxno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>
							<html:text property="tbxFaxNoBC" name="m_bnkForm" maxlength="20"> </html:text>
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.didno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>
							<html:text property="tbxDIDNoBC" name="m_bnkForm" maxlength="20"></html:text>
						</td>
						<td width="15%"><bean:message key="screen.m_bnk.mobileno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>
							<html:text property="tbxMobileNoBC" name="m_bnkForm" maxlength="20"></html:text>
						</td>				
					</tr>					
				
				</table>
			</div>
			<div id="contact_3" class="tabcontent contact">
				<table width="90%">
					<tr>
						<td width="15%"><bean:message key="screen.m_bnk.contactname"/></td>
						<td colspan="3"> <bean:message key="screen.m_bnk.colon"/>
							<html:text property="tbxContactNameOC" name="m_bnkForm" maxlength="50"></html:text>
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.designation"/></td>
						<td colspan="3"><bean:message key="screen.m_bnk.colon"/>
							<html:text property="tbxDesignationOC" name="m_bnkForm" maxlength="50"></html:text>
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.email"/></td>
						<td colspan="3"><bean:message key="screen.m_bnk.colon"/>
							<html:text property="tbxEmailOC" name="m_bnkForm" maxlength="50"></html:text>
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.telephoneno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>
						<html:text property="tbxTelephoneNoOC" name="m_bnkForm" maxlength="20"></html:text>
						</td>
						<td width="15%"><bean:message key="screen.m_bnk.faxno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>
							<html:text property="tbxFaxNoOC" name="m_bnkForm" maxlength="20"></html:text>
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.didno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>
							<html:text property="tbxDIDNoOC" name="m_bnkForm" maxlength="20"></html:text>
						</td>
						<td width="15%"><bean:message key="screen.m_bnk.mobileno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>
							<html:text property="tbxMobileNoOC" name="m_bnkForm" maxlength="20"></html:text>
						</td>				
					</tr>					
				
				</table>
			</div>
		</div>
		</div>		
		</td>
		</tr>
		</table>			
			<hr/>
			<html:hidden name="m_bnkForm" property="page_status" />
			<input type="button" class="button" value="<bean:message key="screen.m_bnk.savebutton"/>" onclick="set_page_status();confirm_save();" />
			<input type="button" class="button" value="<bean:message key="screen.m_bnk.exitbutton"/>" onclick="confirmExit();" />
			<input type="submit" class="button" value="<bean:message key="screen.m_bnk.exitbutton"/>" name="forward_exit" onclick="clear_text();" style="visibility:hidden;" />
			<input type="submit" class="button" value="<bean:message key="screen.m_bnk.savebutton"/>" name="forward_success" onclick="set_page_status();" style="visibility:hidden;"/>
			<ts:submit value='bankrefer'  property="forward_view"  style="visibility:hidden"/>
		</ts:form>
		 <%
	 	}
	  %>
	  
	  <div class="error">
    		<ts:messages id="messages" message="true">
    			<bean:write name="messages"/>
    		</ts:messages>
       	</div>
	 <div class="error">
   <html:messages id="message">
    <bean:write name="message"/><br/>
   </html:messages>
  </div>
		</ts:body>
		</html:html>