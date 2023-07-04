<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
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
	<% if (B_SSM_S02e_BUtils.isITContactTabEnable(infoIDArray)) { %>
		<div id="iTContactTab" class="B_SSM_S02_Page_Form_Tabs_TabPanel">
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
												:&nbsp;${B_SSM_S02_Page_Form.map.iTContactDetail1.contactName }
											</TD>
										</TR>
										<!-- Designation -->
										<TR>
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Contact.Designation.Text"/>
											</TD>
											<TD>
												:&nbsp;${B_SSM_S02_Page_Form.map.iTContactDetail1.contactDesignation }
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
												:&nbsp;${B_SSM_S02_Page_Form.map.iTContactDetail1.contactEmail }
											</TD>
										</TR>
										<!-- Telephone No. -->
										<TR>
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Contact.TelephoneNo.Text"/>
											</TD>
											<TD>
												:&nbsp;${B_SSM_S02_Page_Form.map.iTContactDetail1.contactTelephoneNo }
											</TD>
										</TR>
										<!-- Fax No. -->
										<TR>
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Contact.FaxNo.Text"/>
											</TD>
											<TD>
												:&nbsp;${B_SSM_S02_Page_Form.map.iTContactDetail1.contactFaxNo }
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
												:&nbsp;${B_SSM_S02_Page_Form.map.iTContactDetail2.contactName }
											</TD>
										</TR>
										<!-- Designation -->
										<TR>
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Contact.Designation.Text"/>
											</TD>
											<TD>
												:&nbsp;${B_SSM_S02_Page_Form.map.iTContactDetail2.contactDesignation }
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
												:&nbsp;${B_SSM_S02_Page_Form.map.iTContactDetail2.contactEmail }
											</TD>
										</TR>
										<!-- Telephone No. -->
										<TR>
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Contact.TelephoneNo.Text"/>
											</TD>
											<TD>
												:&nbsp;${B_SSM_S02_Page_Form.map.iTContactDetail2.contactTelephoneNo }
											</TD>
										</TR>
										<!-- Fax No. -->
										<TR>
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Contact.FaxNo.Text"/>
											</TD>
											<TD>
												:&nbsp;${B_SSM_S02_Page_Form.map.iTContactDetail2.contactFaxNo }
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
												:&nbsp;${B_SSM_S02_Page_Form.map.iTContactDetail3.contactName }
											</TD>
										</TR>
										<!-- Designation -->
										<TR>
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Contact.Designation.Text"/>
											</TD>
											<TD>
												:&nbsp;${B_SSM_S02_Page_Form.map.iTContactDetail3.contactDesignation }
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
												:&nbsp;${B_SSM_S02_Page_Form.map.iTContactDetail3.contactEmail }
											</TD>
										</TR>
										<!-- Telephone No. -->
										<TR>
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Contact.TelephoneNo.Text"/>
											</TD>
											<TD>
												:&nbsp;${B_SSM_S02_Page_Form.map.iTContactDetail3.contactTelephoneNo }
											</TD>
										</TR>
										<!-- Fax No. -->
										<TR>
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Contact.FaxNo.Text"/>
											</TD>
											<TD>
												:&nbsp;${B_SSM_S02_Page_Form.map.iTContactDetail3.contactFaxNo }
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
									<textarea readonly="readonly" rows="5" cols="60">
										<bean:write name="B_SSM_S02_Page_Form" 												   
													property="iTContactMemoRemarks"/>
									</textarea>
								</div>
							</TD>
						</TR>
					</TABLE>
				</div>
			</logic:equal>	
		</div>
		<%}%>
</body>
</html>