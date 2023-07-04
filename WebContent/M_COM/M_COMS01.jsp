<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
<%@ page session="false" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
   		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
   		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/stylesheet/tabcontent.css"/>
   		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/M_COM/css/m_coms01.css"/>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/tabcontent.js"></script>

	<script type="text/javascript" src="<%=request.getContextPath()%>/M_COM/js/m_coms01.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/M_COM/js/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   	<script type="text/javascript">
	var tablink_idname = new Array("tablink");
	var tabcontent_idname = new Array("tabcontent"); 
	var tabcount = new Array("3");
	var loadtabs = new Array("1") ; 
	var autochangemenu = 0;
	var changespeed = 3;
	var stoponhover = 0;

	function easytabs(menunr, active) {if (menunr == autochangemenu){currenttab=active;}if ((menunr == autochangemenu)&&(stoponhover==1)) {stop_autochange()} else if ((menunr == autochangemenu)&&(stoponhover==0))  {counter=0;}menunr = menunr-1;for (i=1; i <= tabcount[menunr]; i++){document.getElementById(tablink_idname[menunr]+i).className='tab'+i;document.getElementById(tabcontent_idname[menunr]+i).style.display = 'none';}document.getElementById(tablink_idname[menunr]+active).className='tab'+active+' tabactive';document.getElementById(tabcontent_idname[menunr]+active).style.display = 'block';}var timer; counter=0; var totaltabs=tabcount[autochangemenu-1];var currenttab=loadtabs[autochangemenu-1];function start_autochange(){counter=counter+1;timer=setTimeout("start_autochange()",1000);if (counter == changespeed+1) {currenttab++;if (currenttab>totaltabs) {currenttab=1}easytabs(autochangemenu,currenttab);restart_autochange();}}function restart_autochange(){clearTimeout(timer);counter=0;start_autochange();}function stop_autochange(){clearTimeout(timer);counter=0;}
	
	function myload(){	
	isLoaded = true;
	if (window.parent && window.parent.frame_top) {
		if (parent.frame_top.isLoaded) parent.frame_top.initPage();
	}
	var settings=new ddtabcontent("subcriptionTab");
	settings.setpersist(true);
	settings.setselectedClassTarget("link"); //"link" or "linkparent"
	settings.init();
	}
	function savepage()
	{
		if(validate()) {
			document.getElementById("checkpagetype").value=1;
		
			document.getElementById("tbxAddressLine3RA").disabled=false;
			document.getElementById("tbxAddressLine3CA").disabled=false;
			var bt=document.getElementById("forward_success");
			bt.click();
		}	
	}
	function getPath(input){	
	if(input.files && input.files[0]){
	return input.files[0].getAsDataURL();
	}
	return input.value || "No file selected";
	}
	
	function showPrev() {
		var imagePath = document.getElementById("file").value;		
		var imgDiv = document.getElementById("imageDiv");
		//var img_src = document.getElementById("img_src");
		var imageTag = "<img id='img_src' src='file:///" + imagePath + 
						"' width='300' height='150'" + 
						" alt='" + imagePath + "' />";
		imgDiv.innerHTML= imageTag;				
	}
	
	function refeshImage() {
		var img_src = document.getElementById("img_src");
		if (img_src) {
			var imagePath = document.getElementById("file").value;
			img_src.src = 'file:///' + imagePath;
		}
	}
	
	function validate() {
		var errorMsg = "";
		if(document.getElementById("tbxCompanyName").value == "")
			errorMsg += getError("Company Name");
		if(document.getElementById("tbxCompanyRegNo").value == "")
			errorMsg += getError("Company Reg No");
		//Registered Address
		if(document.getElementById("tbxAddressLine1RA").value == "")
			errorMsg += getError("Registered Address - Line 1");
		if(document.getElementById("tbxZipCodeRA").value == "")
			errorMsg += getError("Registered Address - Zip Code");
		if(document.getElementById("cboAddressCountryRA").value == "")
			errorMsg += getError("Registered Address - Country");
		//Correspondence Address
		if(document.getElementById("tbxAddressLine1CA").value == "")
			errorMsg += getError("Correspondence Address - Line 1");
		if(document.getElementById("tbxZipCodeCA").value == "")
			errorMsg += getError("Correspondence Address - Zip Code");
		if(document.getElementById("cboAddressCountryCA").value == "")
			errorMsg += getError("Correspondence Address - Country");
		//Sales Contact
		if(document.getElementById("tbxEmailSC").value == "")
			errorMsg += getError("Email");
		if(document.getElementById("tbxTelephoneNoSC").value == "")
			errorMsg += getError("Telephone No");
		if(document.getElementById("tbxFaxNoSC").value == "")
			errorMsg += getError("Fax No");
		if(errorMsg == "")
			return true;
		else {
			document.getElementById("scriptCheckMsg").style.display = "none";
			document.getElementById("scriptCheckError").innerHTML = errorMsg.substring(5);
			scroll(0,9999999);
			return false;
		}
	}
	function getError(field) {
		return '<br/>Item "'+field+'" : Mandatory Error. Please fill in the field.';
	}
	
	function openBankInfo(url) {
		window.location.href = url;
	}
	</script>
	<style>
	.information table {
		font-size: 12pt;
	}
	</style>
<title></title>
</head>
	<body id="m" onload="myload(); ">
		<ts:form action="/M_COMS01getSaveDataBLogic" enctype= "multipart/form-data" >
		
		<logic:equal value="n" property="mode" name="_m_comForm">
		<table class="subHeader" cellpadding="0" cellspacing="0">
	   		<tr>
	   			<td><bean:message key="screen.m_com.title"/></td>
	   		</tr>
	   	</table>
	   	
		
		<html:hidden name="_m_comForm" property="checkpagetype"/>
		<div class="message">
			<ts:messages id="message" />
		</div>
		<div id="generalInfomation" class="information">
			<table class="subHeaderInfo" cellpadding="0" cellspacing="0">
		   		<tr>
		   			<td><bean:message key="screen.m_com.general_information.header"/></td>
		   		</tr>
		   	</table>
		   	<div style="display: inline; float: left; ">
			<table>
				<tr>
					<td><bean:message key="screen.m_com.general_information.company_name"/><a style="color: red;"><bean:message key="screen.m_com.asterik"/></a></td>
					<td><bean:message key="screen.m_com.colon"/></td>
					<td><input class= "textbox_char" type="text" name="tbxCompanyName" id="tbxCompanyName" 
							value="<logic:present name="_m_comForm" property="gen_info"><bean:write name="_m_comForm" property="gen_info.COM_NAME"/></logic:present>"/></td>					 				
				</tr>
				<tr>
					<td><bean:message key="screen.m_com.general_information.company_reg_no"/><a style="color: red;"><bean:message key="screen.m_com.asterik"/></a></td>
					<td><bean:message key="screen.m_com.colon"/></td>
					<td><input class= "textbox_char" type="text" id="tbxCompanyRegNo" name="tbxCompanyRegNo" value="<logic:notEmpty name="_m_comForm" property="gen_info"><bean:write name="_m_comForm" property="gen_info.COM_REGNO"/></logic:notEmpty>" maxlength="15"/></td> 					
				</tr>
				<tr>
					<td><bean:message key="screen.m_com.general_information.company_gst_reg_no"/></td>
					<td><bean:message key="screen.m_com.colon"/></td>
					<td><input class= "textbox_char" type="text" id="tbxCompanyGstRegNo" name="tbxCompanyGstRegNo" value="<logic:notEmpty name="_m_comForm" property="gen_info"><bean:write name="_m_comForm" property="gen_info.COM_GST_REG_NO"/></logic:notEmpty>" maxlength="15"/></td> 					
				</tr>
				<tr>
					<td><bean:message key="screen.m_com.general_information.company_website"/></td>
					<td><bean:message key="screen.m_com.colon"/></td>
					<td><input  class= "textbox_char" type="text" name="tbxCompanyWebsite" id="tbxCompanyWebsite" value="<logic:notEmpty name="_m_comForm" property="gen_info"><bean:write name="_m_comForm" property="gen_info.COM_WEBSITE"/></logic:notEmpty>" /></td> 					
				</tr>
				<tr>
					<td><bean:message key="screen.m_com.general_information.telephone_no"/></td>
					<td><bean:message key="screen.m_com.colon"/></td>
					<td><input class= "textbox_num" type="text" name="tbxTelephoneNo"  id="tbxTelephoneNo"
				value="<logic:notEmpty name="_m_comForm" property="gen_info"><bean:write name="_m_comForm" property="gen_info.COM_TEL_NO"/></logic:notEmpty>" maxlength="20"/></td> 					
				</tr>
				<tr>
					<td><bean:message key="screen.m_com.general_information.fax_no"/></td>
					<td><bean:message key="screen.m_com.colon"/></td>
					<td><input class="textbox_num" type="text" name="tbxFaxNo" id="tbxFaxNo" value="<logic:notEmpty name="_m_comForm" property="gen_info"><bean:write name="_m_comForm" property="gen_info.COM_FAX_NO"/></logic:notEmpty>" maxlength="20"/></td> 					
				</tr>
				<tr>
					<td><bean:message key="screen.m_com.general_information.customer_group"/></td>
					<td><bean:message key="screen.m_com.colon"/></td>
					<td>
					<t:defineCodeList id="AFFILIATE_CODE"/>
					<html:select name="_m_comForm" property="tbxAffiliateCode" styleClass="CashBookTextBox" value="${_m_comForm.map.gen_info.AFFILIATE_CODE}" >
					<html:options collection="AFFILIATE_CODE" property="id" labelProperty="name" />					
					</html:select>
						<!-- <input  class="textbox_num" type="text"  name="tbxAffiliateCode"  id="tbxAffiliateCode" value="<logic:notEmpty name="_m_comForm" property="gen_info"><bean:write name="_m_comForm" property="gen_info.AFFILIATE_CODE"/></logic:notEmpty>" maxlength="15"/> --> 					
					</td>
				</tr>
			</table>
			</div>
			<div>
				<table><tr><td align="center">
				<div id="imageDiv" style="overflow: hidden;">
					<img src="<%=request.getContextPath()%>/M_COM/M_COMS01displayImageBlogic.do" width="300" height="150" id="img_src"/>
				</div>
				<br/>
				<div style="text-align: center;">
					<table align="center">
						<tr>
							<td>
								<div class="uploadDiv">																		
									<input type="button" class="fakeButton" value="<bean:message key="screen.m_com.button.upload_picture"/>" />
									<input type="file" id="file" name="file" class="realFile" onchange="showPrev()"/>
								</div>
							</td>
						</tr>						
					</table>
					<br/><br/>					
				</div>
				<logic:equal name="_m_comForm" property="enable_bank" value="1">
					<a href="#"  onclick="openBankInfo('<%=request.getContextPath()%>/M_COM/M_COMS01_01_InitData.do?idCompany=<bean:write name="_m_comForm" property="id_com" />');">
					<span style=""> 
						<bean:message key="screen.m_com.general_information.bank_information"/>
					</span>
					</a> 
				</logic:equal>
				<logic:equal name="_m_comForm" property="enable_bank" value="0">
					<a><bean:message key="screen.m_com.general_information.bank_information"/></a> 
				</logic:equal>		
				</td>
				</tr>
				</table>
			</div>
		</div>				
		<div id="address" style="clear:both;" class="information" >
			<table class = "subHeaderInfo" cellpadding="0" cellspacing="0">
				<tr><td class="header" colspan="3"><bean:message key="screen.m_com.address.header"/></td></tr>	
			</table>
			<table border="0" width="100%" align="left" cellpadding="0" cellspacing="0">
		   	<tr><td><table class="" border="0" width="978px" align="left" cellpadding="0" cellspacing="0">
		   		<tr>
		   			<td width="410px" height="8px"><b><u><bean:message key="screen.m_com.address.registered_address"/></u></b><a style="color: red;"><bean:message key="screen.m_com.asterik"/></a></td>
		   			<td colspan="1" width="80px" height="8px"></td>
		   			<td width="410px" height="8px"><b><u><bean:message key="screen.m_com.address.correspondence_address"/></u></b><a style="color: red;"><bean:message key="screen.m_com.asterik"/></a></td>
		   		</tr>
		   		<tr height="10px" align="left" valign="top">
		   			<td colspan="1" width="410px"><input class="textbox_char" type="text" id="tbxAddressLine1RA" name="tbxAddressLine1RA" value="<logic:notEmpty name="_m_comForm" property="reg_address"><bean:write name="_m_comForm" property="reg_address.COM_ADR_LINE1"/></logic:notEmpty>" style="width:410px; padding-top: 0px;"/></td>
		   			<td colspan="1" width="80px"></td>
		   			<td colspan="1" width="410px"><input class="textbox_char" type="text" name="tbxAddressLine1CA" id="tbxAddressLine1CA" value="<logic:notEmpty name="_m_comForm" property="corr_address"><bean:write name="_m_comForm" property="corr_address.COM_ADR_LINE1"/></logic:notEmpty>" style="width:410px; padding-top: 0px;"/></td>
		   		</tr>
		   		<tr height="10px" align="left" valign="top">
		   			<td colspan="1" width="410px"><input class="textbox_char" class="textbox_char" type="text" id="tbxAddressLine2RA"  name="tbxAddressLine2RA" value="<logic:notEmpty name="_m_comForm" property="reg_address"><bean:write name="_m_comForm" property="reg_address.COM_ADR_LINE2"/></logic:notEmpty>" style="width:410px; padding-top: 0px;"/></td>
		   			<td colspan="1" width="80px"></td>
		   			<td colspan="1" width="410px"><input class="textbox_char" type="text" id="tbxAddressLine2CA"  name="tbxAddressLine2CA" value="<logic:notEmpty name="_m_comForm" property="corr_address"><bean:write name="_m_comForm" property="corr_address.COM_ADR_LINE2"/></logic:notEmpty>" style="width:410px; padding-top: 0px;"/></td>
		   		</tr>
		   		<tr height="10px" align="left" valign="top">
		   			<td colspan="1" width="410px">
		   			<logic:equal value="0" property="addrLine3Disp" name="_m_comForm">
		   			    <input class="textbox_char" disabled="true" type="text" id="tbxAddressLine3RA" name="tbxAddressLine3RA" value="<logic:notEmpty name="_m_comForm" property="reg_address"><bean:write name="_m_comForm" property="reg_address.COM_ADR_LINE3"/></logic:notEmpty>" style="width:410px; padding-top: 0px;"/>
		   			</logic:equal>
		   			<logic:notEqual value="0" property="addrLine3Disp" name="_m_comForm">
		   			    <input class="textbox_char" type="text" id="tbxAddressLine3RA" name="tbxAddressLine3RA" value="<logic:notEmpty name="_m_comForm" property="reg_address"><bean:write name="_m_comForm" property="reg_address.COM_ADR_LINE3"/></logic:notEmpty>" style="width:410px; padding-top: 0px;"/>
		   			</logic:notEqual>
		   			</td>
		   			<td colspan="1" width="80px"></td>
		   			<td colspan="1" width="410px">
		   			<logic:equal value="0" property="addrLine3Disp" name="_m_comForm">
		   			    <input class="textbox_char" disabled="true" type="text" id="tbxAddressLine3CA" name="tbxAddressLine3CA" value="<logic:notEmpty name="_m_comForm" property="corr_address"><bean:write name="_m_comForm" property="corr_address.COM_ADR_LINE3"/></logic:notEmpty>" style="width:410px; padding-top: 0px;"/>
		   			</logic:equal>
		   			<logic:notEqual value="0" property="addrLine3Disp" name="_m_comForm">
		   			    <input class="textbox_char" type="text" id="tbxAddressLine3CA" name="tbxAddressLine3CA" value="<logic:notEmpty name="_m_comForm" property="corr_address"><bean:write name="_m_comForm" property="corr_address.COM_ADR_LINE3"/></logic:notEmpty>" style="width:410px; padding-top: 0px;"/>
		   			</logic:notEqual>
		   			</td>
		   		</tr>
		   		<tr height="10px" valign="top">				
		   			<td width="410px"><input class="textbox_char" type="text" id="tbxZipCodeRA" name="tbxZipCodeRA" value="<logic:notEmpty name="_m_comForm" property="reg_address"><bean:write name="_m_comForm" property="reg_address.ZIP_CODE"/></logic:notEmpty>" style="width:49%; float:left;padding-top: 0px;" maxlength="15"/>
		   			<t:defineCodeList id="LIST_COUNTRY" />	
		   			<bean:define id="rselected" name="_m_comForm" property="reg_address.COUNTRY" />		   			
		   			<html:select styleId="cboAddressCountryRA" property="cboAddressCountryRA" style="width:49%; float:right;" value="${rselected}" >						
						<html:options collection="LIST_COUNTRY" property="id" labelProperty="name"/>
					</html:select>					
					</td>
					<td width="80px"></td>
		   			<td width="410px"><input class="textbox_char" type="text" id="tbxZipCodeCA" name="tbxZipCodeCA" value="<logic:notEmpty name="_m_comForm" property="corr_address"><bean:write name="_m_comForm" property="corr_address.ZIP_CODE"/></logic:notEmpty>" style="width:49%; float:left;padding-top: 0px;" maxlength="15"/>
		   			<t:defineCodeList id="LIST_COUNTRY" />
		   			<bean:define id="cselected" name="_m_comForm" property="corr_address.COUNTRY" />
		   			<html:select styleId="cboAddressCountryCA" property="cboAddressCountryCA" style="width:49%; float:right;" value="${cselected}" >						
						<html:options collection="LIST_COUNTRY" property="id" labelProperty="name"/>
					</html:select></td>
		   		</tr>
		   	</table>
		 	</td></tr>
			</table>
		</div>
		<div id="contact_information" class="information">
					<table class="subHeaderInfo" cellpadding="0" cellspacing="0">
		   		<tr>
		   			<td><bean:message key="screen.m_com.contact_information.header"/></td>
		   		</tr>
		   	</table>
			<div style = "padding-left:1px">
				<ul id="subcriptionTab" class="shadetabs">
					<li><a href="#" rel="tabcontent1"> <bean:message key="screen.m_com.contact_information.sale_contact"/></a></li>
					<li><a href="#" rel="tabcontent2" ><bean:message key="screen.m_com.contact_information.technical_contact"/></a></li>
					<li><a href="#" rel="tabcontent3" ><bean:message key="screen.m_com.contact_information.other_contact"/></a></li>				
				</ul>
			</div>		

			<!--Start Tabcontent 1 -->
			<div id="tabcontent1" style="overflow: hidden;" class="tabcontent">
			<div style="border:1px solid gray; width:100%; margin-bottom: 1em; padding: 10px; overflow: hidden;">
				<table>
					<tr>
						<td><bean:message key="screen.m_com.contact_information.email"/><a style="color: red;"><bean:message key="screen.m_com.asterik"/></a></td>
						<td><bean:message key="screen.m_com.colon"/></td>
						<td><input class="textbox_char" type="text" size="25" id="tbxEmailSC" name="tbxEmailSC"  value="<logic:notEmpty name="_m_comForm" property="sale_contact"><bean:write name="_m_comForm" property="sale_contact.COM_EMAIL"/></logic:notEmpty>"/></td>
					</tr>
					<tr>
						<td><bean:message key="screen.m_com.contact_information.telephone_no"/><a style="color: red;"><bean:message key="screen.m_com.asterik"/></a></td>
						<td><bean:message key="screen.m_com.colon"/></td>
						<td><input maxlength="20" class="textbox_num" type="text" size="25" id="tbxTelephoneNoSC" name="tbxTelephoneNoSC" value="<logic:notEmpty name="_m_comForm" property="sale_contact"><bean:write name="_m_comForm" property="sale_contact.COM_TEL_NO"/></logic:notEmpty>" /></td>
					</tr>
					<tr>
						<td><bean:message key="screen.m_com.contact_information.fax_no"/><a style="color: red;"><bean:message key="screen.m_com.asterik"/></a></td>
						<td><bean:message key="screen.m_com.colon"/></td>
						<td><input maxlength="20" class="textbox_num" type="text" size="25" id="tbxFaxNoSC" name="tbxFaxNoSC" value="<logic:notEmpty name="_m_comForm" property="sale_contact"><bean:write name="_m_comForm" property="sale_contact.COM_FAX_NO"/></logic:notEmpty>"/></td>
					</tr>
				</table>
			</div>
			</div>
			<!--End Tabcontent 1-->
			
			<!--Start Tabcontent 2-->
			<div id="tabcontent2" style="overflow: hidden;" class="tabcontent">
			<div style="border:1px solid gray; width:100%; margin-bottom: 1em; padding: 10px">
				<table>
					<tr>
						<td><bean:message key="screen.m_com.contact_information.email"/></td>
						<td><bean:message key="screen.m_com.colon"/></td>
						<td><input class="textbox_char" type="text" size="25" id="tbxEmailTC" name="tbxEmailTC"  value="<logic:notEmpty name="_m_comForm" property="tech_contact"><bean:write name="_m_comForm" property="tech_contact.COM_EMAIL"/></logic:notEmpty>"  /></td>
					</tr>
					<tr>
						<td><bean:message key="screen.m_com.contact_information.telephone_no"/></td>
						<td><bean:message key="screen.m_com.colon"/></td>
						<td><input maxlength="20" class="textbox_num" type="text" size="25" id="tbxTelephoneNoTC" name="tbxTelephoneNoTC" value="<logic:notEmpty name="_m_comForm" property="tech_contact"><bean:write name="_m_comForm" property="tech_contact.COM_TEL_NO"/></logic:notEmpty>" /></td>
					</tr>
					<tr>
						<td><bean:message key="screen.m_com.contact_information.fax_no"/></td>
						<td><bean:message key="screen.m_com.colon"/></td>
						<td><input maxlength="20" class="textbox_num" type="text" size="25" id="tbxFaxNoTC" name="tbxFaxNoTC" value="<logic:notEmpty name="_m_comForm" property="tech_contact"><bean:write name="_m_comForm" property="tech_contact.COM_FAX_NO"/></logic:notEmpty>"/></td>
					</tr>
				</table>
				</div>
			</div>
			<!--End Tabcontent 2 -->
			
			<!--Start Tabcontent 3-->
			<div id="tabcontent3" style="overflow: hidden;" class="tabcontent">
			<div style="border:1px solid gray; width:100%; margin-bottom: 1em; padding: 10px">
				<table>
					<tr>
						<td><bean:message key="screen.m_com.contact_information.email"/></td>
						<td><bean:message key="screen.m_com.colon"/></td>
						<td><input class="textbox_char" type="text" size="25" id="tbxEmailOC" name="tbxEmailOC"  value="<logic:notEmpty name="_m_comForm" property="other_contact"><bean:write name="_m_comForm" property="other_contact.COM_EMAIL"/></logic:notEmpty>"  /></td>
					</tr>
					<tr>
						<td><bean:message key="screen.m_com.contact_information.telephone_no"/></td>
						<td><bean:message key="screen.m_com.colon"/></td>
						<td><input maxlength="20" class="textbox_num" type="text" size="25" id="tbxTelephoneNoOC" name="tbxTelephoneNoOC" value="<logic:notEmpty name="_m_comForm" property="other_contact"><bean:write name="_m_comForm" property="other_contact.COM_TEL_NO"/></logic:notEmpty>" /></td>
					</tr>
					<tr>
						<td><bean:message key="screen.m_com.contact_information.fax_no"/></td>
						<td><bean:message key="screen.m_com.colon"/></td>
						<td><input maxlength="20" class="textbox_num" type="text" size="25" id="tbxFaxNoOC" name="tbxFaxNoOC" value="<logic:notEmpty name="_m_comForm" property="other_contact"><bean:write name="_m_comForm" property="other_contact.COM_FAX_NO"/></logic:notEmpty>"/></td>
					</tr>
				</table>
			</div>
			</div>
			<!--End Tabcontent 3-->
			</div>
		   	
		   	<div id="additional_information" class="information">
				<table class="subHeaderInfo" cellpadding="0" cellspacing="0">
			   		<tr>
			   			<td><bean:message key="screen.m_com.additional_information.header"/></td>
			   		</tr>
			   	</table>
			   	<table>
			   		<tr>	
			   			<td colspan="6"><u><bean:message key="screen.m_com.additional_information.browser_information"/></u></td>			   			
			   		</tr>
			   		<tr>	
			   			<td><bean:message key="screen.m_com.additional_information.proxy_server_name"/></td>
			   			<td><bean:message key="screen.m_com.colon"/></td>			   			
			   			<td><input class="textbox_char" type="text" id="tbxProxyServerName" name="tbxProxyServerName" value="<logic:present name="_m_comForm" property="gen_info"><bean:write name="_m_comForm" property="gen_info.PROXSERV_NAME"/></logic:present>"/></td>
			   			<td><bean:message key="screen.m_com.additional_information.port_number"/></td>
			   			<td><bean:message key="screen.m_com.colon"/></td>			   			
			   			<td><input class="textbox_num" type="text"  id="tbxPortNumber" name="tbxPortNumber" value="<logic:present name="_m_comForm" property="gen_info"><bean:write name="_m_comForm" property="gen_info.PORT_NO"/></logic:present>" maxlength="15"/></td>
			   		</tr>
			   		<tr>
			   			<td colspan="6" />
			   		</tr>	
			   		<tr>	
			   			<td colspan="6"><u><bean:message key="screen.m_com.additional_information.mail_information"/></u></td>			   			
			   		</tr>		   			
			   		<tr>	
			   			<td><bean:message key="screen.m_com.additional_information.primary_domain_name_server"/></td>
			   			<td><bean:message key="screen.m_com.colon"/></td>			   			
			   			<td><input class="textbox_char" type="text"  id="tbxPrimaryDomainNameServer" name="tbxPrimaryDomainNameServer" value="<logic:present name="_m_comForm" property="gen_info"><bean:write name="_m_comForm" property="gen_info.PRIMDOMAIN_NO"/></logic:present>"/></td>
			   			<td><bean:message key="screen.m_com.additional_information.secondary_domain_name_server"/></td>
			   			<td><bean:message key="screen.m_com.colon"/></td>			   			
			   			<td><input class="textbox_char" type="text"  id="tbxSecondaryDomainNameServer" name="tbxSecondaryDomainNameServer" value="<logic:present name="_m_comForm" property="gen_info"><bean:write name="_m_comForm" property="gen_info.SECDOMAIN_NO"/></logic:present>"/></td>
			   		</tr>
                    
                    <tr>
                        <td><bean:message
                                key="screen.m_com.additional_information.domain_name" />
                        </td>
                        <td>
                            <bean:message key="screen.m_com.colon" />
                        </td>
                        <td>
                            <input maxlength="100" type="text" class="textbox_char" id="tbxDomainName" name="tbxDomainName" value='<bean:write name="_m_comForm" property="gen_info.DOMAIN_NAME"/>'>
                        </td>
                        <td><bean:message
                                key="screen.m_com.additional_information.webmail_URL" />
                        </td>
                        <td><bean:message key="screen.m_com.colon" />
                        </td>
                        <td>
                            <input maxlength="100" type="text" class="textbox_char" id="tbxWebmailURL" name="tbxWebmailURL" value='<bean:write name="_m_comForm" property="gen_info.WEBMAIL_URL"/>'>
                        </td>
                    </tr>
                    <tr>
                        <td><bean:message
                                key="screen.m_com.additional_information.SMTP_server_name" />
                        </td>
                        <td><bean:message key="screen.m_com.colon" />
                        </td>
                        <td>
                            <input maxlength="100" type="text" class="textbox_char" id="tbxSMTPServerName" name="tbxSMTPServerName" value='<bean:write name="_m_comForm" property="gen_info.SMTP_SERVER_NAME"/>'>
                        </td>
                        <td><bean:message
                                key="screen.m_com.additional_information.pop_server_name" />
                        </td>
                        <td><bean:message key="screen.m_com.colon" />
                        </td>
                        <td>
                            <input maxlength="100" type="text" class="textbox_char" id="tbxPopServerName" name="tbxPopServerName" value='<bean:write name="_m_comForm" property="gen_info.POP_SERVER_NAME"/>'>
                        </td>
                    </tr>
                    
			   		<tr>
			   			<td colspan="6" />
			   		</tr>	
			   		<tr>	
			   			<td colspan="6"><u><bean:message key="screen.m_com.additional_information.dial-up_telephone_information"/></u></td>			   			
			   		</tr>		   			
			   		<tr>	
			   			<td><bean:message key="screen.m_com.additional_information.default_dial-up_tel_no"/></td>
			   			<td><bean:message key="screen.m_com.colon"/></td>			   			
			   			<td><input maxlength="20" class="textbox_char" type="text"  id="tbxDefaultDialupTelNo" name="tbxDefaultDialupTelNo" value="<logic:present name="_m_comForm" property="gen_info"><bean:write name="_m_comForm" property="gen_info.DEFAULT_DIALUPTELNO"/></logic:present>"/></td>
			   						   			
			   		</tr>
			   		<tr>
			   			<td colspan="6" />
			   		</tr>	
			   		<tr>	
			   			<td colspan="6"><u><bean:message key="screen.m_com.additional_information.router_information"/></u></td>			   			
			   		</tr>		   			
			   		<tr>	
			   			<td><bean:message key="screen.m_com.additional_information.default_password"/></td>
			   			<td><bean:message key="screen.m_com.colon"/></td>
			   			<td><input maxlength="20" class="textbox_char" type="text"  id="tbxDefaultPassword" name="tbxDefaultPassword" value="<logic:present name="_m_comForm" property="gen_info"><bean:write name="_m_comForm" property="gen_info.DEFAULT_ROUTERPW"/></logic:present>"/></td>			   			
			   		</tr>
			   	</table>
		   	</div>
		<hr />
		<input type="button" value="<bean:message key="screen.m_com.button.save"/>" onclick="savepage();" style="width:70px;"/>
		<input type="submit" value="<bean:message key="screen.m_com.button.save"/>" name="forward_success"  style="visibility:hidden;" />
		<input type="hidden" name="id_com" value ="<bean:write name="_m_comForm" property="id_com" />"/>
		</logic:equal>
		<logic:equal value="v" property="mode" name="_m_comForm">
		
		<table class="subHeader" cellpadding="0" cellspacing="0">
	   		<tr>
	   			<td><bean:message key="screen.m_com.title"/></td>
	   		</tr>
	   	</table>
	   	<div class="error">
			<ts:errors />
		</div>
		<div class="message">
			<ts:messages id="message" />
		</div>
		<div id="generalInfomation" class="information">
			<table class="subHeaderInfo" cellpadding="0" cellspacing="0">
		   		<tr>
		   			<td><bean:message key="screen.m_com.general_information.header"/></td>
		   		</tr>
		   	</table>
		   	<div style="display: inline; float: left; ">
			<table>
				<tr>
					<td><bean:message key="screen.m_com.general_information.company_name"/></td>
					<td><bean:message key="screen.m_com.colon"/></td>
					<td><bean:write name="_m_comForm" property="gen_info.COM_NAME"/></td>					 				
				</tr>
				<tr>
					<td><bean:message key="screen.m_com.general_information.company_reg_no"/></td>
					<td><bean:message key="screen.m_com.colon"/></td>
					<td><bean:write name="_m_comForm" property="gen_info.COM_REGNO"/></td> 					
				</tr>
				<tr>
					<td><bean:message key="screen.m_com.general_information.company_gst_reg_no"/></td>
					<td><bean:message key="screen.m_com.colon"/></td>
					<td><bean:write name="_m_comForm" property="gen_info.COM_GST_REG_NO"/></td> 					
				</tr>
				<tr>
					<td><bean:message key="screen.m_com.general_information.company_website"/></td>
					<td><bean:message key="screen.m_com.colon"/></td>
					<td><bean:write name="_m_comForm" property="gen_info.COM_WEBSITE"/></td> 					
				</tr>
				<tr>
					<td><bean:message key="screen.m_com.general_information.telephone_no"/></td>
					<td><bean:message key="screen.m_com.colon"/></td>
					<td><bean:write name="_m_comForm" property="gen_info.COM_TEL_NO"/></td> 					
				</tr>
				<tr>
					<td><bean:message key="screen.m_com.general_information.fax_no"/></td>
					<td><bean:message key="screen.m_com.colon"/></td>
					<td><bean:write name="_m_comForm" property="gen_info.COM_FAX_NO"/></td> 					
				</tr>
				<tr>
					<td><bean:message key="screen.m_com.general_information.customer_group"/></td>
					<td><bean:message key="screen.m_com.colon"/></td>
					<td>
				
						<t:defineCodeList id="AFFILIATE_CODE" />
						<logic:iterate id="lst_code" name="AFFILIATE_CODE">
               		 	<logic:equal name="lst_code" property="id" value="${_m_comForm.map.gen_info.AFFILIATE_CODE}">
                 		<bean:write name="lst_code" property="name"/>
                		</logic:equal>
               			</logic:iterate>
					
					</td> 					
				</tr>
			</table>
			</div>
			<div>
				<table><tr><td align="center">
				<img src="<%=request.getContextPath()%>/M_COM/M_COMS01displayImageBlogic.do" width="300" height="150" />
				<br/>
				<div style="display: block; width: 100px; height: 20px; overflow: hidden;">				
				</div>				
				<logic:equal name="_m_comForm" property="enable_bank" value="1">				
				<br/><br/>	<a href="#"  onclick="openBankInfo('<%=request.getContextPath()%>/M_COM/M_COMS01_01_InitData.do?idCompany=<bean:write name="_m_comForm" property="id_com" />');">
					<bean:message key="screen.m_com.general_information.bank_information"/></a> 
				</logic:equal>
				<logic:equal name="_m_comForm" property="enable_bank" value="0">
					<a><bean:message key="screen.m_com.general_information.bank_information"/></a> 
				</logic:equal>		
				</td>
				</tr>
				</table>
			</div>
		</div>				
		<div id="address" style="clear:both;" class="information" >
			<table class="subHeaderInfo" cellpadding="0" cellspacing="0">
		   		<tr>
		   			<td colspan="3"><bean:message key="screen.m_com.address.header"/></td>
		   		</tr>
		   	</table>
		   	<table border="0" width="100%" align="left" cellpadding="0" cellspacing="0">
		   	<tr><td><table class="" border="0" width="978px" align="left" cellpadding="0" cellspacing="0">
		   		<tr>
		   			<td colspan="1" width="410px" height="8px"><b><u><bean:message key="screen.m_com.address.registered_address"/></u></b></td>
		   			<td colspan="1" width="80px" height="8px"></td>
		   			<td colspan="1" width="410px" height="8px"><b><u><bean:message key="screen.m_com.address.correspondence_address"/></u></b></td>
		   		</tr>
		   		<tr height="10px" valign="top">
		   			<td colspan="1" width="410px"><bean:write name="_m_comForm" property="reg_address.COM_ADR_LINE1"/></td>
		   			<td colspan="1" width="80px"></td>
		   			<td colspan="1" width="410px"><bean:write name="_m_comForm" property="corr_address.COM_ADR_LINE1"/></td>
		   		</tr>
		   		<tr height="10px" valign="top">
		   			<td colspan="1" width="410px"><bean:write name="_m_comForm" property="reg_address.COM_ADR_LINE2"/></td>
		   			<td colspan="1" width="80px"></td>
		   			<td colspan="1" width="410px"><bean:write name="_m_comForm" property="corr_address.COM_ADR_LINE2"/></td>
		   		</tr>
		   		<tr height="10px" valign="top">
		   			<td colspan="1" width="410px"><bean:write name="_m_comForm" property="reg_address.COM_ADR_LINE3"/></td>
		   			<td colspan="1" width="80px"></td>
		   			<td colspan="1" width="410px"><bean:write name="_m_comForm" property="corr_address.COM_ADR_LINE3"/></td>
		   		</tr>
		   		<tr height="10px" valign="top">					
		   			<td width="410px"><bean:write name="_m_comForm" property="reg_address.ZIP_CODE"/>
		   			<t:defineCodeList id="LIST_COUNTRY" />
					<logic:iterate id="pmlist" name="LIST_COUNTRY">
               		 <logic:equal name="pmlist" property="id" value="${_m_comForm.map.cboAddressCountryRA}">
                 	<bean:write name="pmlist" property="name"/>
                	</logic:equal>
               		</logic:iterate>					
					</td>
					<td width="80px"></td>
		   			<td width="410px"><bean:write name="_m_comForm" property="corr_address.ZIP_CODE"/>
		   			<bean:define id="cselected" name="_m_comForm" property="corr_address.COUNTRY" />
		   			<logic:iterate id="pmlist1" name="LIST_COUNTRY">
               		 <logic:equal name="pmlist1" property="id" value="${_m_comForm.map.cboAddressCountryCA}">
                 	<bean:write name="pmlist1" property="name"/>
                	</logic:equal>
               		</logic:iterate>
						</td>
					</tr></table>
				</td></tr>
			</table>
		</div>
		<div id="contact_information" class="information">
					<table class="subHeaderInfo" cellpadding="0" cellspacing="0">
		   		<tr>
		   			<td><bean:message key="screen.m_com.contact_information.header"/></td>
		   		</tr>
		   	</table>
			<div class="menu">
				<ul id="subcriptionTab" class="shadetabs">
					<li><a href="#"  rel ="tabcontent1"><bean:message key="screen.m_com.contact_information.sale_contact"/></a></li>
					<li><a href="#"  rel ="tabcontent2"><bean:message key="screen.m_com.contact_information.technical_contact"/></a></li>
					<li><a href="#"  rel ="tabcontent3"><bean:message key="screen.m_com.contact_information.other_contact"/></a></li>				
				</ul>
			</div>
			<!--Start Tabcontent 1 -->
			<div id="tabcontent1" class="tabcontent">
			<div style="border:1px solid gray; width:100%; margin-bottom: 1em; padding: 10px">
				<table>
					<tr>
						<td><bean:message key="screen.m_com.contact_information.email"/></td>
						<td><bean:message key="screen.m_com.colon"/></td>
						<td>
							<c:if test="${not empty _m_comForm.map.sale_contact}">	
								<bean:write name="_m_comForm" property="sale_contact.COM_EMAIL"/>
							</c:if>
						</td>
								
					</tr>
					<tr>
						<td><bean:message key="screen.m_com.contact_information.telephone_no"/></td>
						<td><bean:message key="screen.m_com.colon"/></td>
						<td>
							<c:if test="${not empty _m_comForm.map.sale_contact}">	
								<bean:write name="_m_comForm" property="sale_contact.COM_TEL_NO"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td><bean:message key="screen.m_com.contact_information.fax_no"/></td>
						<td><bean:message key="screen.m_com.colon"/></td>
						<td>
							<c:if test="${not empty _m_comForm.map.sale_contact}">	
								<bean:write name="_m_comForm" property="sale_contact.COM_FAX_NO"/>
							</c:if>
						</td>
					</tr>
				</table>
			</div>
			</div>
			<!--End Tabcontent 1-->
			
			<!--Start Tabcontent 2-->
			<div id="tabcontent2" class="tabcontent">
			<div style="border:1px solid gray; width:100%; margin-bottom: 1em; padding: 10px">
				<table>
					<tr>
						<td><bean:message key="screen.m_com.contact_information.email"/></td>
						<td><bean:message key="screen.m_com.colon"/></td>
						<td>
							<c:if test="${not empty _m_comForm.map.tech_contact}">	
								<bean:write name="_m_comForm" property="tech_contact.COM_EMAIL"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td><bean:message key="screen.m_com.contact_information.telephone_no"/></td>
						<td><bean:message key="screen.m_com.colon"/></td>
						<td>
							<c:if test="${not empty _m_comForm.map.tech_contact}">	
								<bean:write name="_m_comForm" property="tech_contact.COM_TEL_NO"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td><bean:message key="screen.m_com.contact_information.fax_no"/></td>
						<td><bean:message key="screen.m_com.colon"/></td>
						<td>
							<c:if test="${not empty _m_comForm.map.tech_contact}">	
								<bean:write name="_m_comForm" property="tech_contact.COM_FAX_NO"/>
							</c:if>
							
						</td>
					</tr>
				</table>
			</div>
			</div>
			<!--End Tabcontent 2 -->
			
			<!--Start Tabcontent 3-->
			<div id="tabcontent3" class="tabcontent">
			<div style="border:1px solid gray; width:100%; margin-bottom: 1em; padding: 10px">
				<table>
					<tr>
						<td><bean:message key="screen.m_com.contact_information.email"/></td>
						<td><bean:message key="screen.m_com.colon"/></td>
						<td>
							<c:if test="${not empty _m_comForm.map.other_contact}">	
								<bean:write name="_m_comForm" property="other_contact.COM_EMAIL"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td><bean:message key="screen.m_com.contact_information.telephone_no"/></td>
						<td><bean:message key="screen.m_com.colon"/></td>
						<td>
							<c:if test="${not empty _m_comForm.map.other_contact}">	
								<bean:write name="_m_comForm" property="other_contact.COM_TEL_NO"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td><bean:message key="screen.m_com.contact_information.fax_no"/></td>
						<td><bean:message key="screen.m_com.colon"/></td>
						<td>
							<c:if test="${not empty _m_comForm.map.other_contact}">	
								<bean:write name="_m_comForm" property="other_contact.COM_FAX_NO"/>
							</c:if>
						</td>
					</tr>
				</table>
			</div>
			</div>
			<!--End Tabcontent 3-->
			</div>
		   	
		   	<div id="additional_information" class="information">
				<table class="subHeaderInfo" cellpadding="0" cellspacing="0">
			   		<tr>
			   			<td><bean:message key="screen.m_com.additional_information.header"/></td>
			   		</tr>
			   	</table>
			   	<table>
			   		<tr>	
			   			<td colspan="6"><u><bean:message key="screen.m_com.additional_information.browser_information"/></u></td>			   			
			   		</tr>
			   		<tr>	
			   			<td><bean:message key="screen.m_com.additional_information.proxy_server_name"/></td>
			   			<td><bean:message key="screen.m_com.colon"/></td>			   			
			   			<td><bean:write name="_m_comForm" property="gen_info.PROXSERV_NAME"/></td>
			   			<td><bean:message key="screen.m_com.additional_information.port_number"/></td>
			   			<td><bean:message key="screen.m_com.colon"/></td>			   			
			   			<td><bean:write name="_m_comForm" property="gen_info.PORT_NO"/></td>
			   		</tr>
			   		<tr>
			   			<td colspan="6" />
			   		</tr>	
			   		<tr>	
			   			<td colspan="6"><u><bean:message key="screen.m_com.additional_information.mail_information"/></u></td>			   			
			   		</tr>		   			
			   		<tr>	
			   			<td><bean:message key="screen.m_com.additional_information.primary_domain_name_server"/></td>
			   			<td><bean:message key="screen.m_com.colon"/></td>			   			
			   			<td><bean:write name="_m_comForm" property="gen_info.PRIMDOMAIN_NO"/></td>
			   			<td><bean:message key="screen.m_com.additional_information.secondary_domain_name_server"/></td>
			   			<td><bean:message key="screen.m_com.colon"/></td>			   			
			   			<td><bean:write name="_m_comForm" property="gen_info.SECDOMAIN_NO"/></td>
			   		</tr>
                    
                    <tr>
                        <td><bean:message
                                key="screen.m_com.additional_information.domain_name" />
                        </td>
                        <td><bean:message key="screen.m_com.colon" />
                        </td>
                        <td><bean:write name="_m_comForm"
                                property="gen_info.DOMAIN_NAME" />
                        </td>
                        <td><bean:message
                                key="screen.m_com.additional_information.webmail_URL" />
                        </td>
                        <td><bean:message key="screen.m_com.colon" />
                        </td>
                        <td><bean:write name="_m_comForm"
                                property="gen_info.WEBMAIL_URL" />
                        </td>
                    </tr>
                    <tr>
                        <td><bean:message
                                key="screen.m_com.additional_information.SMTP_server_name" />
                        </td>
                        <td><bean:message key="screen.m_com.colon" />
                        </td>
                        <td><bean:write name="_m_comForm"
                                property="gen_info.SMTP_SERVER_NAME" />
                        </td>
                        <td><bean:message
                                key="screen.m_com.additional_information.pop_server_name" />
                        </td>
                        <td><bean:message key="screen.m_com.colon" />
                        </td>
                        <td><bean:write name="_m_comForm"
                                property="gen_info.POP_SERVER_NAME" />
                        </td>
                    </tr>
                    
			   		<tr>
			   			<td colspan="6" />
			   		</tr>	
			   		<tr>	
			   			<td colspan="6"><u><bean:message key="screen.m_com.additional_information.dial-up_telephone_information"/></u></td>			   			
			   		</tr>		   			
			   		<tr>	
			   			<td><bean:message key="screen.m_com.additional_information.default_dial-up_tel_no"/></td>
			   			<td><bean:message key="screen.m_com.colon"/></td>			   			
			   			<td><bean:write name="_m_comForm" property="gen_info.DEFAULT_DIALUPTELNO"/></td>			   			
			   		</tr>
			   		<tr>
			   			<td colspan="6" />
			   		</tr>	
			   		<tr>	
			   			<td colspan="6"><u><bean:message key="screen.m_com.additional_information.router_information"/></u></td>			   			
			   		</tr>		   			
			   		<tr>	
			   			<td><bean:message key="screen.m_com.additional_information.default_password"/></td>
			   			<td><bean:message key="screen.m_com.colon"/></td>
			   			<td><bean:write name="_m_comForm" property="gen_info.DEFAULT_ROUTERPW"/></td>			   						   			
			   		</tr>
			   	</table>
		   	</div>
		<hr />
		</logic:equal>
		<div class="error">
			<ts:messages id="messages" >
    			<bean:write name="messages"/><br/>
    		</ts:messages>
    		<div id="scriptCheckError"></div>
		</div>
		<div class="message" id="scriptCheckMsg">
    		<ts:messages id="messages" message="true">
    			<bean:write name="messages"/>
    		</ts:messages>
       	</div>  					
		</ts:form>
	</body>
</html:html>

