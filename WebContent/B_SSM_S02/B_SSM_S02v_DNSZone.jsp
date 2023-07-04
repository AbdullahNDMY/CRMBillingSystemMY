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
	<% if (B_SSM_S02e_BUtils.isDNSZoneTabEnable(infoIDArray)) { %>
		<div id="dNSZoneTab" class="B_SSM_S02_Page_Form_Tabs_TabPanel">
			<!-- DNS Zone Record Group -->
			<div class="B_SSM_S02_Page_Form_Container_FieldSet">
				<fieldset class="B_SSM_S02_Page_Form_FieldSet">
					<legend class="B_SSM_S02_Page_Form_FieldSet_Legend">
						<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.DNSZoneRecord.Legend" />			  						
					</legend>
					<div class="B_SSM_S02_Page_Form_FieldSet_PanelContent">
						<TABLE class="B_SSM_S02_Page_Form_Table">
							<TR>
								<TD class="B_SSM_S02_Page_Form_Table_Col">
									<TABLE width="100%" class="textAlignTop">
										<col width="22%">
										<col width="1%">
										<col width="77%">
										<!-- Migration Current Name Server -->
										<logic:notEmpty property="dNSZoneMigrationCurrentNameServer" name="B_SSM_S02_Page_Form">
										<TR>
											<TD>
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.DNSZoneRecord.MigrationCurrentNameServer.Text"/>
											</TD>
											<TD>
												:&nbsp;
											</TD>
											<TD style="width:740px;word-wrap: break-word;white-space : normal">
												<bean:write name="B_SSM_S02_Page_Form" property="dNSZoneMigrationCurrentNameServer"/>
											</TD>
										</TR>
										</logic:notEmpty>
										<logic:notEmpty property="dNSZoneMigrationCurrentNameServer2" name="B_SSM_S02_Page_Form">
										<TR>
											<TD>
												<logic:empty property="dNSZoneMigrationCurrentNameServer" name="B_SSM_S02_Page_Form">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.DNSZoneRecord.MigrationCurrentNameServer.Text"/>
												</logic:empty>
											</TD>
											<TD>
												<logic:empty property="dNSZoneMigrationCurrentNameServer" name="B_SSM_S02_Page_Form">
													:&nbsp;
												</logic:empty>
												<logic:notEmpty property="dNSZoneMigrationCurrentNameServer" name="B_SSM_S02_Page_Form">
													&nbsp;&nbsp;
												</logic:notEmpty>
											</TD>
											<TD style="width:740px;word-wrap: break-word;white-space : normal">
												<bean:write name="B_SSM_S02_Page_Form" 															   
														    property="dNSZoneMigrationCurrentNameServer2"/>
											</TD>
										</TR>
										</logic:notEmpty>
										<logic:notEmpty property="dNSZoneMigrationCurrentNameServer3" name="B_SSM_S02_Page_Form">
										<TR>
											<TD>
												<logic:empty property="dNSZoneMigrationCurrentNameServer" name="B_SSM_S02_Page_Form">
													<logic:empty property="dNSZoneMigrationCurrentNameServer2" name="B_SSM_S02_Page_Form">
														<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.DNSZoneRecord.MigrationCurrentNameServer.Text"/>
													</logic:empty>
												</logic:empty>
											</TD>
											<TD>
												<logic:empty property="dNSZoneMigrationCurrentNameServer" name="B_SSM_S02_Page_Form">
													<logic:empty property="dNSZoneMigrationCurrentNameServer2" name="B_SSM_S02_Page_Form">
														:&nbsp;
													</logic:empty>
												</logic:empty>
												<logic:notEmpty property="dNSZoneMigrationCurrentNameServer" name="B_SSM_S02_Page_Form">
													<logic:empty property="dNSZoneMigrationCurrentNameServer2" name="B_SSM_S02_Page_Form">
														&nbsp;&nbsp;
													</logic:empty>
												</logic:notEmpty>
												<logic:empty property="dNSZoneMigrationCurrentNameServer" name="B_SSM_S02_Page_Form">
													<logic:notEmpty property="dNSZoneMigrationCurrentNameServer2" name="B_SSM_S02_Page_Form">
														&nbsp;&nbsp;
													</logic:notEmpty>
												</logic:empty>
												<logic:notEmpty property="dNSZoneMigrationCurrentNameServer" name="B_SSM_S02_Page_Form">
													<logic:notEmpty property="dNSZoneMigrationCurrentNameServer2" name="B_SSM_S02_Page_Form">
														&nbsp;&nbsp;
													</logic:notEmpty>
												</logic:notEmpty>
											</TD>
											<TD style="width:740px;word-wrap: break-word;white-space : normal">
												<bean:write name="B_SSM_S02_Page_Form" 															   
														    property="dNSZoneMigrationCurrentNameServer3"/>
											</TD>
										</TR>
										</logic:notEmpty>
										<logic:empty property="dNSZoneMigrationCurrentNameServer" name="B_SSM_S02_Page_Form">
											<logic:empty property="dNSZoneMigrationCurrentNameServer2" name="B_SSM_S02_Page_Form">
												<logic:empty property="dNSZoneMigrationCurrentNameServer3" name="B_SSM_S02_Page_Form">
													<TR>
														<TD>
															<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.DNSZoneRecord.MigrationCurrentNameServer.Text"/>
														</TD>
														<TD>
															:&nbsp;
														</TD>
														<TD style="width:740px;word-wrap: break-word;white-space : normal">
														</TD>
													</TR>
												</logic:empty>
											</logic:empty>
										</logic:empty>
										<!-- Migration Current Register -->
										<TR>
											<TD>
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.DNSZoneRecord.MigrationCurrentRegister.Text"/>
											</TD>
											<TD>
												:&nbsp;
											</TD>
											<TD>
												<bean:write name="B_SSM_S02_Page_Form" 															   															  
														    property="dNSZoneMigrationCurrentRegister"/>
											</TD>
										</TR>											
									</TABLE>
								</TD>																		
							</TR>
							
						</TABLE>
						
						<!-- Domain table -->								
						<div style="overflow: hidden;">	
							<TABLE id="dNSZoneTableID" class="B_SSM_S02_Page_Form_Table_Detail">
								<!-- Firewall Policy Headers -->
								<TR>										
									<!-- Domain Name -->
									<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
										<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.DNSZoneRecord.DomainName.Text"/>
									</TH>
									<!-- Type (MX/A/Name) -->
									<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
										<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.DNSZoneRecord.Type_MX_A_Name.Text"/>
									</TH>
									<!-- IP Address -->
									<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
										<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.DNSZoneRecord.IPAddress.Text"/>
									</TH>
									<!-- Weight -->
									<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
										<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.DNSZoneRecord.Weight.Text"/>
									</TH>										
								</TR>	
								<!-- DNS Zone Content -->
								<logic:notEmpty name="B_SSM_S02_Page_Form" property="dNSZoneRecordList">
									<logic:iterate id="dNSZoneRecord" 
												   name="B_SSM_S02_Page_Form" 
												   property="dNSZoneRecordList">
										<TR style="text-align: left">									
											<!-- Domain Name -->
											<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
												<bean:write name="dNSZoneRecord" property="dNSZoneRecordDomainName"/>	
											</TD>
											<!-- Type (MX/A/Name) -->
											<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
												<bean:write name="dNSZoneRecord" property="dNSZoneRecordType"/>	
											</TD>
											<!-- IP Address -->
											<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
												<bean:write name="dNSZoneRecord" property="dNSZoneRecordIPAddress"/>	
											</TD>
											<!-- Weight -->
											<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
												<bean:write name="dNSZoneRecord" property="dNSZoneRecordWeight"/>	
											</TD>										
										</TR>
									</logic:iterate>
								</logic:notEmpty>								
							</TABLE>
						</div>	
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
									<textarea readonly="readonly" 
											  rows="5" cols="60">
										<bean:write name="B_SSM_S02_Page_Form" 											   
											    	property="dNSZoneMemoRemarks"/>
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