<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="nttdm.bsys.common.fw.BillingSystemUserValueObject" %>
<%@ page import="nttdm.bsys.c_cmn001.bean.UserAccess" %>
<%@ page import="nttdm.bsys.m_bnk.bean.M_BNK_bankbean" %>
<%@ page import="nttdm.bsys.m_bnk.bean.M_BNK_AdressRA" %>
<%@ page import="nttdm.bsys.m_bnk.bean.M_BNKContactInfo" %>
<%@ page import="nttdm.bsys.m_bnk.bean.M_BNKFormBean" %>
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
	<script language="javascript">
		function clear_text() {
			for(var i=0; i<document.forms.length; i++) {
				for(var j=0; j<document.forms[i].elements.length; j++) {
					if(document.forms[i].elements[j].type == 'text') {
						document.forms[i].elements[j].value='';
					}
				}	
			}
		}
		
		function confirmDelete() {	
			var MsgBox = new messageBox();
			if (MsgBox.POPCFM(document.getElementById("info.ERR4SC002").value) == MsgBox.YES_CONFIRM) {
			//if (confirm(document.getElementById("info.ERR4SC002").value)) {
				var button=	document.getElementById("forward_delete"); 
				button.click();
			}
		}
		function confirm_save()
		{			
			if(document.getElementById("tbxZipCodeRA").value== "Zip Code")
				document.getElementById("tbxZipCodeRA").value= "";
			if(document.getElementById("tbxZipCodeCA").value== "Zip Code")
				document.getElementById("tbxZipCodeCA").value= "";
									
			var button=	document.getElementById("forward_success");
			button.click();
		}	
		function confirmExit()
		{
			var mess= new messageBox();
			var agree=mess.POPEXT();
			if (agree ==1)
			{
				var lblidlogin = $("input[type='hidden'][name='lblidlogin']").val();
				var hypBankReference = $("input[type='text'][name='tbxBankID']").val();
			    var url = "<%=request.getContextPath()%>/M_BNK/M_BNKS02vBlogic.do?lblidlogin=" + lblidlogin+"&hypBankReference="+hypBankReference;
			    window.location = url;
			    //var button=	document.getElementById("forward_exit"); 
				//button.click();
			}
		
		}			
		function initPage() {
			//init tab
			var contactTabs = new ddtabcontent("contactTabs");
			contactTabs.setpersist(true);
			contactTabs.setselectedClassTarget("link");
			contactTabs.init();
		}
		function onRefresh() {
			if(document.getElementById("tbxBankIDHidden").value!="" || document.getElementById("tbxBankIDHidden").value!=null) {
				if(document.getElementById("tbxBankIDHidden").value==document.getElementById("tbxBankID").value) {
					var bankFullName = document.getElementById("tbxBankNameHidden").value + "-" + document.getElementById("tbxBranchNameHidden").value;
					document.getElementById("lblBankFullNameHidden2").value = bankFullName;
					document.getElementById("lblBankFullName").innerHTML = document.getElementById("lblBankFullNameHidden2").value;
					document.getElementById("tbxBankCode").value = document.getElementById("tbxBankCodeHidden").value;
					document.getElementById("tbxBankName").value = document.getElementById("tbxBankNameHidden").value;					
					document.getElementById("tbxBranchCode").value = document.getElementById("tbxBranchCodeHidden").value;
					document.getElementById("tbxBranchName").value = document.getElementById("tbxBranchNameHidden").value;
					document.getElementById("tbxBankBICCode").value = document.getElementById("tbxBankBICCodeHidden").value;
					document.getElementById("tbxTelNo").value = document.getElementById("tbxTelNoHidden").value;
					document.getElementById("tbxFaxNo").value = document.getElementById("tbxFaxNoHidden").value;
					document.getElementById("tbxAddressLine1RA").value = document.getElementById("tbxAddressLine1RAHidden").value;
					document.getElementById("tbxAddressLine2RA").value = document.getElementById("tbxAddressLine2RAHidden").value;
					document.getElementById("tbxAddressLine3RA").value = document.getElementById("tbxAddressLine3RAHidden").value;
					
					if(document.getElementById("tbxZipCodeRAHidden").value == "" || document.getElementById("tbxZipCodeRAHidden").value == null) {
						document.getElementById("tbxZipCodeRA").value = "Zip Code";
					}else{
						document.getElementById("tbxZipCodeRA").value = document.getElementById("tbxZipCodeRAHidden").value;
					}					
					
					if(document.getElementById("cboAddressCountryRAHidden").value == "" || document.getElementById("cboAddressCountryRAHidden").value == null) {
						document.getElementById("cboAddressCountryRA").value = "";
					}else{
						document.getElementById("cboAddressCountryRA").value = document.getElementById("cboAddressCountryRAHidden").value;
					}	
				
					document.getElementById("tbxAddressLine1CA").value = document.getElementById("tbxAddressLine1CAHidden").value;
					document.getElementById("tbxAddressLine2CA").value = document.getElementById("tbxAddressLine2CAHidden").value;
					document.getElementById("tbxAddressLine3CA").value = document.getElementById("tbxAddressLine3CAHidden").value;
					
					if(document.getElementById("tbxZipCodeCAHidden").value == "" || document.getElementById("tbxZipCodeCAHidden").value == null) {
						document.getElementById("tbxZipCodeCA").value = "Zip Code";
					}else{
						document.getElementById("tbxZipCodeCA").value = document.getElementById("tbxZipCodeCAHidden").value;;
					}		
					
					if(document.getElementById("cboAddressCountryCAHidden").value == "" || document.getElementById("cboAddressCountryCAHidden").value == null) {
						document.getElementById("cboAddressCountryCA").value = "";
					}else{
						document.getElementById("cboAddressCountryCA").value = document.getElementById("cboAddressCountryCAHidden").value;
					}	
					
					document.getElementById("tbxContactNamePC").value = document.getElementById("tbxContactNamePCHidden").value;
					document.getElementById("tbxDesignationPC").value = document.getElementById("tbxDesignationPCHidden").value;
					document.getElementById("tbxEmailPC").value = document.getElementById("tbxEmailPCHidden").value;
					document.getElementById("tbxTelephoneNoPC").value = document.getElementById("tbxTelephoneNoPCHidden").value;
					document.getElementById("tbxFaxNoPC").value = document.getElementById("tbxFaxNoPCHidden").value;
					document.getElementById("tbxDIDNoPC").value = document.getElementById("tbxDIDNoPCHidden").value;
					document.getElementById("tbxMobileNoPc").value = document.getElementById("tbxMobileNoPcHidden").value;
					document.getElementById("tbxContactNameBC").value = document.getElementById("tbxContactNameBCHidden").value;
					document.getElementById("tbxDesignationBC").value = document.getElementById("tbxDesignationBCHidden").value;
					document.getElementById("tbxEmailBC").value = document.getElementById("tbxEmailBCHidden").value;
					document.getElementById("tbxTelephoneNoBC").value = document.getElementById("tbxTelephoneNoBCHidden").value;
					document.getElementById("tbxFaxNoBC").value = document.getElementById("tbxFaxNoBCHidden").value;
					document.getElementById("tbxDIDNoBC").value = document.getElementById("tbxDIDNoBCHidden").value;
					document.getElementById("tbxMobileNoBC").value = document.getElementById("tbxMobileNoBCHidden").value;
					document.getElementById("tbxContactNameOC").value = document.getElementById("tbxContactNameOCHidden").value;
					document.getElementById("tbxDesignationOC").value = document.getElementById("tbxDesignationOCHidden").value;
					document.getElementById("tbxEmailOC").value = document.getElementById("tbxEmailOCHidden").value;
					document.getElementById("tbxTelephoneNoOC").value = document.getElementById("tbxTelephoneNoOCHidden").value;
					document.getElementById("tbxFaxNoOC").value = document.getElementById("tbxFaxNoOCHidden").value;
					document.getElementById("tbxDIDNoOC").value = document.getElementById("tbxDIDNoOCHidden").value;
					document.getElementById("tbxMobileNoOC").value = document.getElementById("tbxMobileNoOCHidden").value;
				}
			}
		}
	</script>
	</head>
	<ts:body onload="initPage();onRefresh();">
	<%
		if(session.getAttribute("USER_VALUE_OBJECT") != null)
		 {
		 BillingSystemUserValueObject uvo=(BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT");
		 // out.print("xin chào :");
		 M_BNK_AdressRA addRA = new M_BNK_AdressRA();
		 M_BNK_AdressRA addCA = new M_BNK_AdressRA();
		 M_BNKContactInfo contPC = new M_BNKContactInfo();
		 M_BNKContactInfo contBC = new M_BNKContactInfo();
		 M_BNKContactInfo contOC = new M_BNKContactInfo();
		 M_BNK_bankbean bankinfo = new M_BNK_bankbean();	
		 M_BNKFormBean formBean = new M_BNKFormBean();
		 if(session.getAttribute("bankbeaninfo")!= null)
		 	bankinfo = (M_BNK_bankbean)session.getAttribute("bankbeaninfo");		 	
		 if(session.getAttribute("addressRA")!= null)
		 	addRA=(M_BNK_AdressRA)session.getAttribute("addressRA");
		 if(session.getAttribute("addressCA")!= null)
		 	addCA=(M_BNK_AdressRA)session.getAttribute("addressCA");		 
		 if(session.getAttribute("contactPC")!= null)
		 	contPC=(M_BNKContactInfo)session.getAttribute("contactPC");		 
		 if(session.getAttribute("contactBC")!= null)
		 	contBC=(M_BNKContactInfo)session.getAttribute("contactBC");		 
		 if(session.getAttribute("contactOC")!= null)
		 	contOC=(M_BNKContactInfo)session.getAttribute("contactOC");
	 %>
		<div class="error">
    		<ts:messages id="messages" message="true">
    			<bean:write name="messages"/>
    		</ts:messages>
       	</div>  
       	<input type="hidden" id="info.ERR4SC002" value="<bean:message key="info.ERR4SC002" />" />
		<table class="subHeader" cellpadding="0" cellspacing="0">
		  <tr style="">
		    <td class="Title"><bean:message key="screen.m_bnk.title"/></td> 
		  </tr>
		</table>
		<ts:form action="/M_BNKS02edDSP" >			
			<html:hidden property="lblidlogin" name="m_bnkForm" value="<%=uvo.getId_user() %>" ></html:hidden>
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
						<html:hidden styleId="lblBankFullNameHidden2" property="lblBankFullName" name="m_bnkForm" value=""></html:hidden>
					</tr>
					<tr height="10">
						<td class="colLeft"><bean:message key="screen.m_bnk.bankcode"/><bean:message key="screen.m_bnk.important"/></td>
						<td class="colmid"><bean:message key="screen.m_bnk.colon"/></td>
						<td><html:text property="tbxBankCode" name="m_bnkForm" value="<%=bankinfo.getBANK_CODE() %>" maxlength="4"></html:text> </td>	
						<td><html:text property="tbxBankID" name="m_bnkForm" value="<%=bankinfo.getID_BANK() %>" maxlength="5" style="visibility:hidden;"></html:text> </td>					
						<html:hidden styleId="tbxBankCodeHidden" property="tbxBankCode" name="m_bnkForm" value="<%=formBean.getBank_code() %>"/>
						<html:hidden styleId="tbxBankIDHidden" property="tbxBankID" name="m_bnkForm" value="<%=formBean.getLblidbank() %>"/>
					</tr>
					<tr>
						<td class="colLeft"><bean:message key="screen.m_bnk.bankname"/><bean:message key="screen.m_bnk.important"/></td>
						<td><bean:message key="screen.m_bnk.colon"/></td>
						<td class="colRight" ><html:text property="tbxBankName" name="m_bnkForm" value="<%=bankinfo.getBANK_NAME() %>" onchange="setbankfullname();" maxlength="50"></html:text></td>
						<html:hidden styleId="tbxBankNameHidden" property="tbxBankName" name="m_bnkForm" value="<%=formBean.getTbxBankName() %>"/>
					</tr>
					<tr>
						<td class="colLeft"><bean:message key="screen.m_bnk.branchcode"/><bean:message key="screen.m_bnk.important"/></td>
						<td><bean:message key="screen.m_bnk.colon"/></td>
						<td class="colRight" ><html:text property="tbxBranchCode" name="m_bnkForm" value="<%=bankinfo.getBRANCH_CODE() %>" maxlength="3"></html:text></td>
						<html:hidden styleId="tbxBranchCodeHidden" property="tbxBranchCode" name="m_bnkForm" value="<%=formBean.getTbxBranchCode() %>"/>
					</tr>
					<tr>
						<td class="colLeft"><bean:message key="screen.m_bnk.branchname"/><bean:message key="screen.m_bnk.important"/></td>
						<td><bean:message key="screen.m_bnk.colon"/></td>
						<td class="colRight" ><html:text property="tbxBranchName" name="m_bnkForm" value="<%=bankinfo.getBRANCH_NAME() %>" onchange="setbankfullname();" maxlength="15"></html:text></td>
						<html:hidden styleId="tbxBranchNameHidden" property="tbxBranchName" name="m_bnkForm" value="<%=formBean.getTbxBranchName() %>"/>
					</tr>
					<tr>
						<td class="colLeft"><bean:message key="screen.m_bnk.bankbiccode"/><bean:message key="screen.m_bnk.important"/></td>
						<td><bean:message key="screen.m_bnk.colon"/></td>
						<td class="colRight" ><html:text property="tbxBankBICCode" name="m_bnkForm" value="<%=bankinfo.getBIC_CODE() %>"  maxlength="11"></html:text></td>
						<td><html:hidden styleId="tbxBankBICCodeHidden" property="tbxBankBICCode" name="m_bnkForm" value="<%=formBean.getTbxBankBICCode() %>"/></td>
					</tr>
					<tr>
						<td class="colLeft"><bean:message key="screen.m_bnk.telno"/></td>
						<td><bean:message key="screen.m_bnk.colon"/></td>
						<%
						String TelNo = bankinfo.getBANK_TEL_NO();
						if(TelNo==null){%>	
							<td class="colRight" ><html:text property="tbxTelNo" name="m_bnkForm" value="" maxlength="20"></html:text></td>
						<%}else{%>
							<td class="colRight" ><html:text property="tbxTelNo" name="m_bnkForm" value="<%=TelNo%>" maxlength="20"></html:text></td>
						<%}%>
						<html:hidden styleId="tbxTelNoHidden" property="tbxTelNo" name="m_bnkForm" value="<%=formBean.getTbxTelNo() %>"/>
					</tr>
					<tr>
						<td class="colLeft"><bean:message key="screen.m_bnk.faxno"/></td>
						<td><bean:message key="screen.m_bnk.colon"/></td>
						<%
						String FaxNo = bankinfo.getBANK_FAX_NO();
						if(FaxNo==null){%>
							<td class="colRight" ><html:text property="tbxFaxNo" name="m_bnkForm" value="" maxlength="20"></html:text></td>
						<%}else{%>	
							<td class="colRight" ><html:text property="tbxFaxNo" name="m_bnkForm" value="<%=FaxNo%>" maxlength="20"></html:text></td>
						<%}%>
						<html:hidden styleId="tbxFaxNoHidden" property="tbxFaxNo" name="m_bnkForm" value="<%=formBean.getTbxFaxNo() %>"/>
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
							<%
							String AddressLine1RA = addRA.getBK_ADR_LINE1();
							if(AddressLine1RA==null){%>						
								<html:text property="tbxAddressLine1RA" name="m_bnkForm" style="width:410px; padding-top: 0px;" value="" maxlength="150"></html:text>
							<%}else{%>
								<html:text property="tbxAddressLine1RA" name="m_bnkForm" style="width:410px; padding-top: 0px;" value="<%=AddressLine1RA%>" maxlength="150"></html:text>
							<%}%>
							<html:hidden styleId="tbxAddressLine1RAHidden" property="tbxAddressLine1RA" name="m_bnkForm" value="<%=formBean.getTbxAddressLine1RA() %>"/>
								</td>
								<td colspan="1" width="80px"></td>
								<td colspan="1" width="410px">
							<%
							String AddressLine1CA = addCA.getBK_ADR_LINE1();
							if(AddressLine1CA==null){%>	
								<html:text property="tbxAddressLine1CA" name="m_bnkForm" style="width:410px; padding-top: 0px; margin-right:10px;" value="" maxlength="150"></html:text>
							<%}else{%>
								<html:text property="tbxAddressLine1CA" name="m_bnkForm" style="width:410px; padding-top: 0px; margin-right:10px;" value="<%=AddressLine1CA%>" maxlength="150"></html:text>
							<%}%>
							<html:hidden styleId="tbxAddressLine1CAHidden" property="tbxAddressLine1CA" name="m_bnkForm" value="<%=formBean.getTbxAddressLine1CA() %>"/>
								</td>
							</tr>
							<tr height="10px" align="left" valign="top"><td colspan="1" width="410px">
							<%
							String AddressLine2RA = addRA.getBK_ADR_LINE2();
							if(AddressLine2RA==null){%>	
								<html:text property="tbxAddressLine2RA" name="m_bnkForm" style="width:410px; padding-top: 0px;" value="" maxlength="150"></html:text>
							<%}else{%>
								<html:text property="tbxAddressLine2RA" name="m_bnkForm" style="width:410px; padding-top: 0px;" value="<%=AddressLine2RA%>" maxlength="150"></html:text>
							<%}%>
							<html:hidden styleId="tbxAddressLine2RAHidden" property="tbxAddressLine2RA" name="m_bnkForm" value="<%=formBean.getTbxAddressLine2RA() %>"/>
								</td>
								<td colspan="1" width="80px"></td>
								<td colspan="1" width="410px">							
							<%
							String AddressLine2CA = addCA.getBK_ADR_LINE2();
							if(AddressLine2CA==null){%>	
								<html:text property="tbxAddressLine2CA" name="m_bnkForm" style="width:410px; padding-top: 0px; margin-right:10px;" value="" maxlength="150"></html:text>
							<%}else{%>
								<html:text property="tbxAddressLine2CA" name="m_bnkForm" style="width:410px; padding-top: 0px; margin-right:10px;" value="<%=AddressLine2CA%>" maxlength="150"></html:text>
							<%}%>
							<html:hidden styleId="tbxAddressLine2CAHidden" property="tbxAddressLine2CA" name="m_bnkForm" value="<%=formBean.getTbxAddressLine2CA() %>"/>
								</td>
							</tr>
							<tr height="10px" align="left" valign="top"><td colspan="1" width="410px">
							<%
							String AddressLine3RA = addRA.getBK_ADR_LINE3();
							if(AddressLine3RA==null){%>	
								<html:text property="tbxAddressLine3RA" name="m_bnkForm" style="width:410px; padding-top: 0px;" value="" maxlength="150"></html:text>
							<%}else{%>
								<html:text property="tbxAddressLine3RA" name="m_bnkForm" style="width:410px; padding-top: 0px;" value="<%=AddressLine3RA%>" maxlength="150"></html:text>
							<%}%>
							<html:hidden styleId="tbxAddressLine3RAHidden" property="tbxAddressLine3RA" name="m_bnkForm" value="<%=formBean.getTbxAddressLine3RA() %>"/>
								</td>
								<td colspan="1" width="80px"></td>
								<td colspan="1" width="410px">
							<%
							String AddressLine3CA = addCA.getBK_ADR_LINE3();
							if(AddressLine3CA==null){%>	
								<html:text property="tbxAddressLine3CA" name="m_bnkForm" style="width:410px; padding-top: 0px; margin-right:10px;" value="" maxlength="150"></html:text>
							<%}else{%>
								<html:text property="tbxAddressLine3CA" name="m_bnkForm" style="width:410px; padding-top: 0px; margin-right:10px;" value="<%=AddressLine3CA%>" maxlength="150"></html:text>
							<%}%>
							<html:hidden styleId="tbxAddressLine3CAHidden" property="tbxAddressLine3CA" name="m_bnkForm" value="<%=formBean.getTbxAddressLine3CA() %>"/>
								</td>
							</tr>
							<tr height="10px" align="left" valign="top"><td width="410px">
									<html:text property="tbxZipCodeRA" name="m_bnkForm"  onfocus="if (this.value == 'Zip Code') this.value='';" onblur="if (this.value == '') this.value='Zip Code';" style="width:49%; float:left;padding-top: 0px;" value="<%=addRA.getBK_ZIP_CODE()%>" maxlength="15"></html:text>
									<html:hidden styleId="tbxZipCodeRAHidden" property="tbxZipCodeRA" name="m_bnkForm" value="<%=formBean.getTbxZipCodeRA() %>"/>																
									<t:defineCodeList id="LIST_COUNTRY" />	
									<html:select name="m_bnkForm" property="cboAddressCountryRA" styleClass="CashBookTextBox" style="width:49%; float:right;" value="<%=addRA.getCOUNTRY() %>">
									<html:option value="" ><bean:message key="screen.m_bnk.blankitem"/></html:option>							
									<html:options collection="LIST_COUNTRY" property="id" labelProperty="name"/>
									</html:select>
									<html:hidden styleId="cboAddressCountryRAHidden" property="cboAddressCountryRA" name="m_bnkForm" value="<%=formBean.getCboAddressCountryRA() %>"/>
								</td>
								<td width="80px"></td>
								<td width="410px">
									<%
									String ZipCodeCA = addCA.getBK_ZIP_CODE();
									if(ZipCodeCA == null){%>
										<html:text property="tbxZipCodeCA" name="m_bnkForm"  onfocus="if (this.value == 'Zip Code') this.value='';" onblur="if (this.value == '') this.value='Zip Code';" style="width:49%; float:left;padding-top: 0px;" value="Zip Code" maxlength="15"></html:text>
									<%}else{%>
										<html:text property="tbxZipCodeCA" name="m_bnkForm"  onfocus="if (this.value == 'Zip Code') this.value='';" onblur="if (this.value == '') this.value='Zip Code';" style="width:49%; float:left;padding-top: 0px;" value="<%=ZipCodeCA%>" maxlength="15"></html:text>
									<%}%>
									<html:hidden styleId="tbxZipCodeCAHidden" property="tbxZipCodeCA" name="m_bnkForm" value="<%=formBean.getTbxZipCodeCA() %>"/>
									<t:defineCodeList id="LIST_COUNTRY" />	
									<%
									String AddressCountryCA = addCA.getCOUNTRY();
									if(AddressCountryCA == null){%>
										<html:select name="m_bnkForm" property="cboAddressCountryCA" styleClass="CashBookTextBox" style="width:49%; float:right;" value="">
										<html:option value="" ><bean:message key="screen.m_bnk.blankitem"/></html:option>							
										<html:options collection="LIST_COUNTRY" property="id" labelProperty="name"/>
									</html:select>
									<%}else{%>
										<html:select name="m_bnkForm" property="cboAddressCountryCA" styleClass="CashBookTextBox" style="width:49%; float:right;" value="<%=AddressCountryCA%>">
										<html:option value="" ><bean:message key="screen.m_bnk.blankitem"/></html:option>							
										<html:options collection="LIST_COUNTRY" property="id" labelProperty="name"/>
									</html:select>
									<%}%>
									<html:hidden styleId="cboAddressCountryCAHidden" property="cboAddressCountryCA" name="m_bnkForm" value="<%=formBean.getCboAddressCountryCA() %>"/>
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
							<html:text property="tbxContactNamePC" name="m_bnkForm" value="<%=contPC.getBK_CTC_NAME()%>" maxlength="50"></html:text>
							<html:hidden styleId="tbxContactNamePCHidden" property="tbxContactNamePC" name="m_bnkForm" value="<%=formBean.getTbxContactNamePC() %>"/>
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.designation"/></td>
						<td colspan="3"><bean:message key="screen.m_bnk.colon"/>
						<%
							String designationPC = contPC.getBK_CTC_DESIGNATION();
							if(designationPC==null){%>
								<html:text property="tbxDesignationPC" name="m_bnkForm" value="" maxlength="50"></html:text>
							<%}else{%>
								<html:text property="tbxDesignationPC" name="m_bnkForm" value="<%=designationPC%>" maxlength="50"></html:text>
							<%}%>
							<html:hidden styleId="tbxDesignationPCHidden" property="tbxDesignationPC" name="m_bnkForm" value="<%=formBean.getTbxDesignationPC() %>"/>
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.email"/></td>
						<td colspan="3"><bean:message key="screen.m_bnk.colon"/>
						<%
							String emailPC = contPC.getBK_CTC_EMAIL();
							if(emailPC==null){%>
								<html:text property="tbxEmailPC" name="m_bnkForm" value="" maxlength="50"></html:text>
							<%}else{%>
								<html:text property="tbxEmailPC" name="m_bnkForm" value="<%=emailPC%>" maxlength="50"></html:text>
							<%}%>
							<html:hidden styleId="tbxEmailPCHidden" property="tbxEmailPC" name="m_bnkForm" value="<%=formBean.getTbxEmailPC() %>"/>
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.telephoneno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>
						<%
							String telephonePC = contPC.getBK_CTC_TEL_NO();
							if(telephonePC==null){%>
								<html:text property="tbxTelephoneNoPC" name="m_bnkForm" value="" maxlength="20"></html:text>
							<%}else{%>
								<html:text property="tbxTelephoneNoPC" name="m_bnkForm" value="<%=telephonePC%>" maxlength="20"> </html:text>
							<%}%>
							<html:hidden styleId="tbxTelephoneNoPCHidden" property="tbxTelephoneNoPC" name="m_bnkForm" value="<%=formBean.getTbxTelephoneNoPC() %>"/>
						</td>
						<td width="15%"><bean:message key="screen.m_bnk.faxno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>
						<%
							String faxNoPC = contPC.getBK_CTC_FAX_NO();
							if(faxNoPC==null){%>
								<html:text property="tbxFaxNoPC" name="m_bnkForm" value="" maxlength="20"></html:text>
							<%}else{%>
								<html:text property="tbxFaxNoPC" name="m_bnkForm" value="<%=faxNoPC%>" maxlength="20"></html:text>
							<%}%>
							<html:hidden styleId="tbxFaxNoPCHidden" property="tbxFaxNoPC" name="m_bnkForm" value="<%=formBean.getTbxFaxNoPC() %>"/>
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.didno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>
						<%
							String didNoPC = contPC.getBK_CTC_DID_NO();
							if(didNoPC==null){%>
								<html:text property="tbxDIDNoPC" name="m_bnkForm" value="" maxlength="20"></html:text>
							<%}else{%>
								<html:text property="tbxDIDNoPC" name="m_bnkForm" value="<%=didNoPC%>" maxlength="20"></html:text>
							<%}%>
							<html:hidden styleId="tbxDIDNoPCHidden" property="tbxDIDNoPC" name="m_bnkForm" value="<%=formBean.getTbxDIDNoPC() %>"/>
						</td>
						<td width="15%"><bean:message key="screen.m_bnk.mobileno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>
						<%
							String mobileNoPC = contPC.getBK_CTC_MOBILE_NO();
							if(mobileNoPC==null){%>
								<html:text property="tbxMobileNoPc" name="m_bnkForm" value="" maxlength="20"></html:text>
							<%}else{%>
								<html:text property="tbxMobileNoPc" name="m_bnkForm" value="<%=mobileNoPC%>" maxlength="20"></html:text>
							<%}%>
							<html:hidden styleId="tbxMobileNoPcHidden" property="tbxMobileNoPc" name="m_bnkForm" value="<%=formBean.getTbxMobileNoPc() %>"/>
						</td>				
					</tr>	
				</table>
			</div>
			<div id="contact_2" class="tabcontent contact">
				<table width="90%" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<td width="15%"><bean:message key="screen.m_bnk.contactname"/></td>
						<td colspan="3"> <bean:message key="screen.m_bnk.colon"/>
						<%
							String ContactNameBC = contBC.getBK_CTC_NAME();
							if(ContactNameBC==null){%>
								<html:text property="tbxContactNameBC" name="m_bnkForm" value="" maxlength="50"></html:text>
							<%}else{%>							
								<html:text property="tbxContactNameBC" name="m_bnkForm" value="<%=ContactNameBC%>" maxlength="50"></html:text>
							<%}%>
							<html:hidden styleId="tbxContactNameBCHidden" property="tbxContactNameBC" name="m_bnkForm" value="<%=formBean.getTbxContactNameBC() %>"/>
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.designation"/></td>
						<td colspan="3"><bean:message key="screen.m_bnk.colon"/>
						<%
							String DesignationBC = contBC.getBK_CTC_DESIGNATION();
							if(DesignationBC==null){%>
								<html:text property="tbxDesignationBC" name="m_bnkForm" value="" maxlength="50"> </html:text>
							<%}else{%>							
								<html:text property="tbxDesignationBC" name="m_bnkForm" value="<%=DesignationBC%>" maxlength="50"> </html:text>
							<%}%>
							<html:hidden styleId="tbxDesignationBCHidden" property="tbxDesignationBC" name="m_bnkForm" value="<%=formBean.getTbxDesignationBC() %>"/>
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.email"/></td>
						<td colspan="3"><bean:message key="screen.m_bnk.colon"/>
						<%
							String EmailBC = contBC.getBK_CTC_EMAIL();
							if(EmailBC==null){%>
								<html:text property="tbxEmailBC" name="m_bnkForm" value="" maxlength="50"> </html:text>
							<%}else{%>							
								<html:text property="tbxEmailBC" name="m_bnkForm" value="<%=EmailBC%>" maxlength="50"> </html:text>
							<%}%>
							<html:hidden styleId="tbxEmailBCHidden" property="tbxEmailBC" name="m_bnkForm" value="<%=formBean.getTbxEmailBC() %>"/>
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.telephoneno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>
						<%
							String TelephoneNoBC = contBC.getBK_CTC_TEL_NO();
							if(TelephoneNoBC==null){%>
								<html:text property="tbxTelephoneNoBC" name="m_bnkForm" value="" maxlength="20"></html:text>
							<%}else{%>	
								<html:text property="tbxTelephoneNoBC" name="m_bnkForm" value="<%=TelephoneNoBC%>" maxlength="20"></html:text>
							<%}%>
							<html:hidden styleId="tbxTelephoneNoBCHidden" property="tbxTelephoneNoBC" name="m_bnkForm" value="<%=formBean.getTbxTelephoneNoBC() %>"/>
						</td>
						<td width="15%"><bean:message key="screen.m_bnk.faxno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>
						<%
							String FaxNoBC = contBC.getBK_CTC_FAX_NO();
							if(FaxNoBC==null){%>
								<html:text property="tbxFaxNoBC" name="m_bnkForm" value="" maxlength="20"></html:text>
							<%}else{%>	
								<html:text property="tbxFaxNoBC" name="m_bnkForm" value="<%=FaxNoBC%>" maxlength="20"> </html:text>
							<%}%>
							<html:hidden styleId="tbxFaxNoBCHidden" property="tbxFaxNoBC" name="m_bnkForm" value="<%=formBean.getTbxFaxNoBC() %>"/>
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.didno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>
						<%
							String DIDNoBC = contBC.getBK_CTC_DID_NO();
							if(DIDNoBC==null){%>
								<html:text property="tbxDIDNoBC" name="m_bnkForm" value="" maxlength="20"></html:text>
							<%}else{%>													
								<html:text property="tbxDIDNoBC" name="m_bnkForm" value="<%=DIDNoBC%>" maxlength="20"></html:text>
							<%}%>
							<html:hidden styleId="tbxDIDNoBCHidden" property="tbxDIDNoBC" name="m_bnkForm" value="<%=formBean.getTbxDIDNoBC() %>"/>
						</td>
						<td width="15%"><bean:message key="screen.m_bnk.mobileno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>
						<%
							String MobileNoBC = contBC.getBK_CTC_MOBILE_NO();
							if(MobileNoBC==null){%>
								<html:text property="tbxMobileNoBC" name="m_bnkForm" value="" maxlength="20"></html:text>
							<%}else{%>
								<html:text property="tbxMobileNoBC" name="m_bnkForm" value="<%=MobileNoBC%>" maxlength="20"></html:text>
							<%}%>
							<html:hidden styleId="tbxMobileNoBCHidden" property="tbxMobileNoBC" name="m_bnkForm" value="<%=formBean.getTbxMobileNoBC() %>"/>
						</td>				
					</tr>					
				
				</table>
			</div>
			<div id="contact_3" class="tabcontent contact">
				<table width="90%">
					<tr>
						<td width="15%"><bean:message key="screen.m_bnk.contactname"/></td>
						<td colspan="3"> <bean:message key="screen.m_bnk.colon"/>
						<%
							String ContactNameOC = contOC.getBK_CTC_NAME();
							if(ContactNameOC==null){%>
								<html:text property="tbxContactNameOC" name="m_bnkForm" value="" maxlength="50"></html:text>
							<%}else{%>
								<html:text property="tbxContactNameOC" name="m_bnkForm" value="<%=ContactNameOC%>" maxlength="50"></html:text>
							<%}%>
							<html:hidden styleId="tbxContactNameOCHidden" property="tbxContactNameOC" name="m_bnkForm" value="<%=formBean.getTbxContactNameOC() %>"/>
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.designation"/></td>
						<td colspan="3"><bean:message key="screen.m_bnk.colon"/>
						<%
							String DesignationOC = contOC.getBK_CTC_DESIGNATION();
							if(DesignationOC==null){%>
								<html:text property="tbxDesignationOC" name="m_bnkForm" value="" maxlength="50"></html:text>
							<%}else{%>	
								<html:text property="tbxDesignationOC" name="m_bnkForm" value="<%=DesignationOC%>" maxlength="50"></html:text>
							<%}%>
							<html:hidden styleId="tbxDesignationOCHidden" property="tbxDesignationOC" name="m_bnkForm" value="<%=formBean.getTbxDesignationOC() %>"/>
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.email"/></td>
						<td colspan="3"><bean:message key="screen.m_bnk.colon"/>
						<%
							String EmailOC = contOC.getBK_CTC_EMAIL();
							if(EmailOC==null){%>
								<html:text property="tbxEmailOC" name="m_bnkForm" value="" maxlength="50"></html:text>
							<%}else{%>
								<html:text property="tbxEmailOC" name="m_bnkForm" value="<%=EmailOC%>" maxlength="50"></html:text>
							<%}%>
							<html:hidden styleId="tbxEmailOCHidden" property="tbxEmailOC" name="m_bnkForm" value="<%=formBean.getTbxEmailOC() %>"/>
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.telephoneno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>			
						<%
							String TelephoneNoOC = contOC.getBK_CTC_TEL_NO();
							if(TelephoneNoOC==null){%>
								<html:text property="tbxTelephoneNoOC" name="m_bnkForm" value="" maxlength="20"></html:text>
							<%}else{%>					
								<html:text property="tbxTelephoneNoOC" name="m_bnkForm" value="<%=TelephoneNoOC%>" maxlength="20"></html:text>
							<%}%>
							<html:hidden styleId="tbxTelephoneNoOCHidden" property="tbxTelephoneNoOC" name="m_bnkForm" value="<%=formBean.getTbxTelephoneNoOC() %>"/>
						</td>
						<td width="15%"><bean:message key="screen.m_bnk.faxno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>
						<%
							String FaxNoOC = contOC.getBK_CTC_FAX_NO();
							if(FaxNoOC==null){%>	
								<html:text property="tbxFaxNoOC" name="m_bnkForm" value="" maxlength="20"></html:text>
							<%}else{%>						
								<html:text property="tbxFaxNoOC" name="m_bnkForm" value="<%=FaxNoOC%>" maxlength="20"></html:text>
							<%}%>
							<html:hidden styleId="tbxFaxNoOCHidden" property="tbxFaxNoOC" name="m_bnkForm" value="<%=formBean.getTbxFaxNoOC() %>"/>
						</td>					
					</tr>
					<tr>
						<td><bean:message key="screen.m_bnk.didno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>
						<%
							String DIDNoOC = contOC.getBK_CTC_DID_NO();
							if(DIDNoOC==null){%>			
								<html:text property="tbxDIDNoOC" name="m_bnkForm" value="" maxlength="20"></html:text>
							<%}else{%>	
								<html:text property="tbxDIDNoOC" name="m_bnkForm" value="<%=DIDNoOC%>" maxlength="20"></html:text>
							<%}%>
							<html:hidden styleId="tbxDIDNoOCHidden" property="tbxDIDNoOC" name="m_bnkForm" value="<%=formBean.getTbxDIDNoOC() %>"/>
						</td>
						<td width="15%"><bean:message key="screen.m_bnk.mobileno"/></td>
						<td ><bean:message key="screen.m_bnk.colon"/>
						<%
							String MobileNoOC = contOC.getBK_CTC_MOBILE_NO();
							if(MobileNoOC==null){%>		
								<html:text property="tbxMobileNoOC" name="m_bnkForm" value="" maxlength="20"></html:text>
							<%}else{%>							
								<html:text property="tbxMobileNoOC" name="m_bnkForm" value="<%=MobileNoOC%>" maxlength="20"></html:text>
							<%}%>
							<html:hidden styleId="tbxMobileNoOCHidden" property="tbxMobileNoOC" name="m_bnkForm" value="<%=formBean.getTbxMobileNoOC() %>"/>
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
			<input type="button" class="button" value="<bean:message key="screen.m_bnk.savebutton"/>"  style="width:60px; font-size:13px;" onclick="confirm_save();" />
			
			<!-- <input type="button" class="button" value="<bean:message key="screen.m_bnk.deletebutton"/>"  style="width:60px; font-size:10px;" onclick="confirmDelete();"/>-->
			<input type="button" class="button" value="<bean:message key="screen.m_bnk.exitbutton"/>"  style="width:60px; font-size:13px;" onclick="confirmExit();"/>
			
			<input type="submit" class="button" value="<bean:message key="screen.m_bnk.exitbutton"/>" name="forward_exit" onclick="clear_text();" style="width:60px; font-size:13px;" style="visibility:hidden;"/>
			
			<input type="submit" class="button" value="<bean:message key="screen.m_bnk.deletebutton"/>" name="forward_delete" style="width:60px; font-size:13px;" style="visibility:hidden;" />
			<input type="submit" class="button" value="<bean:message key="screen.m_bnk.savebutton"/>" name="forward_success" style="width:60px; font-size:13px;" style="visibility:hidden;" />	
			<html:hidden name="m_bnkForm" property="page_status" value="1"/>
		</ts:form>
		 <%
	 	}
	  %>
	  <script>
		setbankfullname();
		removeBlankSpaces();
	  </script>
<!-- 	   <div class="error"> -->
<%-- 	   		<ts:ifErrors> --%>
<%-- 	    		<ts:errors/> --%>
<%-- 	    	</ts:ifErrors> --%>
<!-- 	 </div> -->
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