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
	<div id="iTContactTab" 
		 style='<%=(B_SSM_S02e_BUtils.isITContactTabEnable(infoIDArray))? "display:block;":"display:none;"%>' 
		 class="B_SSM_S02_Page_Form_Tabs_TabPanel">
		<!--Contact1 Group -->
		<div class="B_SSM_S02_Page_Form_Container_FieldSet">
			<fieldset class="B_SSM_S02_Page_Form_FieldSet">
				<legend class="B_SSM_S02_Page_Form_FieldSet_Legend">
					<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Contact1.Legend" />			  						
				</legend>
				<div class="B_SSM_S02_Page_Form_FieldSet_PanelContent">
					<TABLE class="B_SSM_S02_Page_Form_Table">
						<TR>
							<TD class="B_SSM_S02_Page_Form_Table_Col">
								<TABLE width="100%">
									<!-- Name -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Contact.Name.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<input id="contact1NameID" name="contactName" type="text"
														value="${B_SSM_S02_Page_Form.map.iTContactDetail1.contactName }"
														class="B_SSM_S02_Page_Form_TextBox" maxlength="50"/>
											<input type="hidden" name="contactType" value="C1"/>
										</TD>
									</TR>
									<!-- Designation -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Contact.Designation.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<input id="contact1DesignationID" name="contactDesignation" type="text"
														value="${B_SSM_S02_Page_Form.map.iTContactDetail1.contactDesignation }"
														class="B_SSM_S02_Page_Form_TextBox" maxlength="50"/>
										</TD>
									</TR>
								</TABLE>										
							</TD>
							<TD class="B_SSM_S02_Page_Form_Table_Col">
								<TABLE width="100%">
									<!-- Email -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Contact.Email.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<input id="contact1EmailID" name="contactEmail" type="text"
														value="${B_SSM_S02_Page_Form.map.iTContactDetail1.contactEmail }"
														class="B_SSM_S02_Page_Form_TextBox" maxlength="50"/>
										</TD>
									</TR>
									<!-- Telephone No. -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Contact.TelephoneNo.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<input id="contact1TelephoneNoID" name="contactTelephoneNo" type="text"
														value="${B_SSM_S02_Page_Form.map.iTContactDetail1.contactTelephoneNo }"
														class="B_SSM_S02_Page_Form_TextBox" maxlength="30"/>
										</TD>
									</TR>
									<!-- Fax No. -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Contact.FaxNo.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<input id="contact1FaxNoID" name="contactFaxNo" type="text"
														value="${B_SSM_S02_Page_Form.map.iTContactDetail1.contactFaxNo }"
														class="B_SSM_S02_Page_Form_TextBox" maxlength="30"/>
										</TD>
									</TR>
								</TABLE>	
							</TD>
						</TR>
					</TABLE>
				</div>
			</fieldset>	
		</div>
		
		<!--Contact2 Group -->
		<div class="B_SSM_S02_Page_Form_Container_FieldSet">
			<fieldset class="B_SSM_S02_Page_Form_FieldSet">
				<legend class="B_SSM_S02_Page_Form_FieldSet_Legend">
					<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Contact2.Legend" />			  						
				</legend>
				<div class="B_SSM_S02_Page_Form_FieldSet_PanelContent">
					<TABLE class="B_SSM_S02_Page_Form_Table">
						<TR>
							<TD class="B_SSM_S02_Page_Form_Table_Col">
								<TABLE width="100%">
									<!-- Name -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Contact.Name.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<input id="contact2NameID" name="contactName" type="text"
														value="${B_SSM_S02_Page_Form.map.iTContactDetail2.contactName }"
														class="B_SSM_S02_Page_Form_TextBox" maxlength="50"/>
											<input type="hidden" name="contactType" value="C2">
										</TD>
									</TR>
									<!-- Designation -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Contact.Designation.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<input id="contact2DesignationID" name="contactDesignation" type="text"
														value="${B_SSM_S02_Page_Form.map.iTContactDetail2.contactDesignation }"
														class="B_SSM_S02_Page_Form_TextBox" maxlength="50"/>
										</TD>
									</TR>
								</TABLE>										
							</TD>
							<TD class="B_SSM_S02_Page_Form_Table_Col">
								<TABLE width="100%">
									<!-- Email -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Contact.Email.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<input id="contact2EmailID" name="contactEmail" type="text"
														value="${B_SSM_S02_Page_Form.map.iTContactDetail2.contactEmail }"
														class="B_SSM_S02_Page_Form_TextBox" maxlength="50"/>
										</TD>
									</TR>
									<!-- Telephone No. -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Contact.TelephoneNo.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<input id="contact2TelephoneNoID" name="contactTelephoneNo" type="text"
														value="${B_SSM_S02_Page_Form.map.iTContactDetail2.contactTelephoneNo }"
														class="B_SSM_S02_Page_Form_TextBox" maxlength="30"/>
										</TD>
									</TR>
									<!-- Fax No. -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Contact.FaxNo.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<input id="contact2FaxNoID" name="contactFaxNo" type="text"
														value="${B_SSM_S02_Page_Form.map.iTContactDetail2.contactFaxNo }"
														class="B_SSM_S02_Page_Form_TextBox" maxlength="30"/>
										</TD>
									</TR>
								</TABLE>	
							</TD>
						</TR>
					</TABLE>
				</div>
			</fieldset>	
		</div>
		
		<!--Contact3 Group -->
		<div class="B_SSM_S02_Page_Form_Container_FieldSet">
			<fieldset class="B_SSM_S02_Page_Form_FieldSet">
				<legend class="B_SSM_S02_Page_Form_FieldSet_Legend">
					<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Contact3.Legend" />			  						
				</legend>
				<div class="B_SSM_S02_Page_Form_FieldSet_PanelContent">
					<TABLE class="B_SSM_S02_Page_Form_Table">
						<TR>
							<TD class="B_SSM_S02_Page_Form_Table_Col">
								<TABLE width="100%">
									<!-- Name -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Contact.Name.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<input id="contact3NameID" name="contactName" type="text"
														value="${B_SSM_S02_Page_Form.map.iTContactDetail3.contactName }"
														class="B_SSM_S02_Page_Form_TextBox" maxlength="50">
											<input type="hidden" name="contactType" value="C3">
										</TD>
									</TR>
									<!-- Designation -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Contact.Designation.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<input id="contact3DesignationID" name="contactDesignation" type="text"
														value="${B_SSM_S02_Page_Form.map.iTContactDetail3.contactDesignation }"
														class="B_SSM_S02_Page_Form_TextBox" maxlength="50">
										</TD>
									</TR>
								</TABLE>										
							</TD>
							<TD class="B_SSM_S02_Page_Form_Table_Col">
								<TABLE width="100%">
									<!-- Email -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Contact.Email.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<input id="contact3EmailID" name="contactEmail" type="text"
														value="${B_SSM_S02_Page_Form.map.iTContactDetail3.contactEmail }"
														class="B_SSM_S02_Page_Form_TextBox" maxlength="50">
										</TD>
									</TR>
									<!-- Telephone No. -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Contact.TelephoneNo.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<input id="contact3TelephoneNoID" name="contactTelephoneNo" type="text"
														value="${B_SSM_S02_Page_Form.map.iTContactDetail3.contactTelephoneNo }"
														class="B_SSM_S02_Page_Form_TextBox" maxlength="30">
										</TD>
									</TR>
									<!-- Fax No. -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Contact.FaxNo.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<input id="contact3FaxNoID" name="contactFaxNo" type="text"
														value="${B_SSM_S02_Page_Form.map.iTContactDetail3.contactFaxNo }"
														class="B_SSM_S02_Page_Form_TextBox" maxlength="30">
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
											   styleId="iTContactMemoRemarksID"
											   property="iTContactMemoRemarks"
										       rows="5" cols="60"/>
								<html:hidden name="B_SSM_S02_Page_Form" property="iTContactHeadID"/>
							</div>
						</TD>
					</TR>
				</TABLE>
			</div>
		</logic:equal>
		<logic:notEqual name="B_SSM_S02_Page_Form" property="displayMemoFlg" value="1">
			<html:textarea name="B_SSM_S02_Page_Form"
						   styleId="iTContactMemoRemarksID"
						   property="iTContactMemoRemarks"
					       rows="5" cols="60" style="display:none;"/>
			<html:hidden name="B_SSM_S02_Page_Form" property="iTContactHeadID"/>
		</logic:notEqual>
	</div>
</body>
</html>