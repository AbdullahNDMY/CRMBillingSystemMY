<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page import="java.util.HashMap" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="nttdm.bsys.b_ssm.utility.BLogicUtils"%>
<%@page import="nttdm.bsys.b_ssm.s02.blogic.B_SSM_S02v_BUtils"%>
<%@page import="nttdm.bsys.b_ssm.s02.blogic.B_SSM_S02e_BUtils"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">		
</head>

<body>
	<bean:define id="infoIDArray" name="B_SSM_S02_Page_Form" property="infoIDArray" type="java.util.List<Integer>"/>
	<bean:define id="accessAccountFlag" name="B_SSM_S02_Page_Form" property="accessAccountFlag" type="java.lang.Integer"/>
	<bean:define id="accessPasswordFlag" name="B_SSM_S02_Page_Form" property="accessPasswordFlag" type="java.lang.Integer"/>
	<div id="teamworkTab" 
		 style='<%=(B_SSM_S02e_BUtils.isTeamworkTabEnable(infoIDArray))? "display:block;":"display:none;"%>'
		 class="B_SSM_S02_Page_Form_Tabs_TabPanel">
		<!-- Teamwork Group -->
		<div class="B_SSM_S02_Page_Form_Container_FieldSet">
			<fieldset class="B_SSM_S02_Page_Form_FieldSet">
				<legend class="B_SSM_S02_Page_Form_FieldSet_Legend">
					<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Teamwork.Legend" />			  						
				</legend>
				<div class="B_SSM_S02_Page_Form_FieldSet_PanelContent">
					<TABLE class="B_SSM_S02_Page_Form_Table">
						<TR>
							<TD class="B_SSM_S02_Page_Form_Table_Col">
								<TABLE width="100%">
									<!-- URL -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Teamwork.URL.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<html:text name="B_SSM_S02_Page_Form" maxlength="200"
													   styleClass="B_SSM_S02_Page_Form_TextBox"  
													   styleId="teamworkURLID"
													   property="teamworkURL"/>
										</TD>
									</TR>
									<!-- Admin ID -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Teamwork.AdminID.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<html:text name="B_SSM_S02_Page_Form" maxlength="30"
													   styleClass="B_SSM_S02_Page_Form_TextBox"  
													   styleId="teamworkAdminID"
													   property="teamworkAdminID"/>
										</TD>
									</TR>
									<!-- Admin Password -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Teamwork.AdminPassword.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<html:text name="B_SSM_S02_Page_Form" maxlength="30"
													   styleClass="B_SSM_S02_Page_Form_TextBox"  
													   styleId="teamworkAdminPasswordID"
													   property="teamworkAdminPassword"/>
										</TD>
									</TR>	
									<!-- Email Domain Address -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Teamwork.EmailDomainAddress.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<html:text name="B_SSM_S02_Page_Form" maxlength="100"
													   styleClass="B_SSM_S02_Page_Form_TextBox"  
													   styleId="teamworkEmailDomainAddressID"
													   property="teamworkEmailDomainAddress"/>
										</TD>
									</TR>	
									<!-- Incoming Mail Server (POP3) -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Teamwork.IncomingMailServer_POP3.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<html:text name="B_SSM_S02_Page_Form" maxlength="100"
													   styleClass="B_SSM_S02_Page_Form_TextBox"  
													   styleId="teamworkIncomingMailServer_POP3ID"
													   property="teamworkIncomingMailServer_POP3"/>
										</TD>
									</TR>	
									<!-- Incoming Mail Server (IMAP) -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Teamwork.IncomingMailServer_IMAP.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<html:text name="B_SSM_S02_Page_Form" maxlength="100"
													   styleClass="B_SSM_S02_Page_Form_TextBox"  
													   styleId="teamworkIncomingMailServer_IMAPID"
													   property="teamworkIncomingMailServer_IMAP"/>
										</TD>
									</TR>	
									<!-- Incoming Mail Server (SMTP) -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Teamwork.IncomingMailServer_SMTP.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<html:text name="B_SSM_S02_Page_Form" maxlength="100"
													   styleClass="B_SSM_S02_Page_Form_TextBox"  
													   styleId="teamworkIncomingMailServer_SMTPID"
													   property="teamworkIncomingMailServer_SMTP"/>
										</TD>
									</TR>	
									<!-- Account Name -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Teamwork.AccountName.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<input type="text" name="teamworkAccountNameMask" maxlength="30"
													   class="B_SSM_S02_Page_Form_TextBox"
													   id="teamworkAccountNameMaskID"
													   value='<bean:write name="B_SSM_S02_Page_Form" property="teamworkAccountName"/>'
													   onblur="textboxBlur(this,'teamworkAccountNameID');"
													   onfocus="textboxFocus(this,'teamworkAccountNameID');"
													   alt="(Your TeamWorks user ID)@&lt;emaildomainaddress&gt;"/>
											<html:hidden name="B_SSM_S02_Page_Form"
													   styleClass="B_SSM_S02_Page_Form_TextBox"  
													   styleId="teamworkAccountNameID"
													   property="teamworkAccountName"/>
										</TD>
									</TR>	
									<!-- Account Password -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Teamwork.AccountPassword.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<input type="text" name="teamworkAccountPasswordMask" maxlength="30"
													   class="B_SSM_S02_Page_Form_TextBox"  
													   id="teamworkAccountPasswordMaskID"
													   value='<bean:write name="B_SSM_S02_Page_Form" property="teamworkAccountPassword"/>'
													   onblur="textboxBlur(this,'teamworkAccountPasswordID');"
													   onfocus="textboxFocus(this,'teamworkAccountPasswordID');"
													   alt="(YourTeamWorks password)"/>
											<html:hidden name="B_SSM_S02_Page_Form"
													   styleClass="B_SSM_S02_Page_Form_TextBox"  
													   styleId="teamworkAccountPasswordID"
													   property="teamworkAccountPassword"/>
										</TD>
									</TR>	
																			
								</TABLE>
							</TD>																		
						</TR>							
					</TABLE>													
				</div>
			</fieldset>
		</div>
		
		<!-- Memo remarks -->
		<logic:equal name="B_SSM_S02_Page_Form" property="displayMemoFlg" value="1">
			<div class="B_SSM_S02_Page_Form_Container">
				<TABLE class="B_SSM_S02_Page_Form_Table">
					<TR>
						<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol_UpVAlign" style="width:180px;">
							<bean:message key="B_SSM_S02_Page.TabPanel.MemoRemarks.Text"/>									
						</TD>							
						<TD style="vertical-align: bottom;">								
							<div style="float: left; 
										text-align: center; 
										vertical-align: middle;">
								&nbsp;&nbsp;&nbsp;&nbsp;:
							</div>
							<div style="float: left; ">
								&nbsp;
								<html:textarea name="B_SSM_S02_Page_Form"
											   styleId="teamworkMemoRemarksID"
											   property="teamworkMemoRemarks"
									           rows="5" cols="60"/>
								<html:hidden name="B_SSM_S02_Page_Form" property="teamworkID"/>
							</div>
						</TD>
					</TR>
				</TABLE>
			</div>
		</logic:equal>
		<logic:notEqual name="B_SSM_S02_Page_Form" property="displayMemoFlg" value="1">
			<html:textarea name="B_SSM_S02_Page_Form"
						   styleId="teamworkMemoRemarksID"
						   property="teamworkMemoRemarks"
				           rows="5" cols="60" style="display:none;"/>
			<html:hidden name="B_SSM_S02_Page_Form" property="teamworkID"/>
		</logic:notEqual>
	</div>
</body>
</html>