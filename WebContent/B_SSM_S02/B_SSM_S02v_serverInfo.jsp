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
	<% if (B_SSM_S02e_BUtils.isServerInfoTabEnable(infoIDArray)) { %>
		<div id="serverInfoTab" class="B_SSM_S02_Page_Form_Tabs_TabPanel">
			<!--Server Group -->
			<div class="B_SSM_S02_Page_Form_Container_FieldSet">
				<fieldset class="B_SSM_S02_Page_Form_FieldSet">
					<legend class="B_SSM_S02_Page_Form_FieldSet_Legend">
						<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Server.Server.Legend" />	  						
					</legend>
					<div class="B_SSM_S02_Page_Form_FieldSet_PanelContent">
						<TABLE class="B_SSM_S02_Page_Form_Table">
							<TR>
								<TD class="B_SSM_S02_Page_Form_Table_Col">
									<TABLE>
										<TR>
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol_UpVAlign">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Server.Server.Text"/>										
											</TD>
											<!-- Server list -->
											<TD style="vertical-align: top;">
												<div style="float: left; ">
													<html:select styleClass="B_SSM_S02_Page_Form_SelectBox"  
																 styleId="serverInfoServerID"	
																 size="5" value=""
																 property="serverInfoServer"
																 onchange="selectServerInfo();">
														<html:options name="B_SSM_S02_Page_Form"
																property="serverInfoAlias"/>
													</html:select>
													<div id="serverInfoDetailIDDiv" style="display: none;">
														<logic:iterate id="serverInfoDetailIDItem" name="B_SSM_S02_Page_Form" 
																	property="serverInfoDetailID">
															<html:hidden property="serverInfoDetailID" value="${serverInfoDetailIDItem}"/>
														</logic:iterate>
													</div>
												</div>
											</TD>
											<!-- Server list button -->									
											<TD style="vertical-align: middle;">
											</TD>	
										</TR>	
									</TABLE>					
								</TD>									
							</TR>
						</TABLE>
					</div>
				</fieldset>	
			</div>
			
			<!--Server Info Group -->
			<div class="B_SSM_S02_Page_Form_Container_FieldSet">
				<fieldset class="B_SSM_S02_Page_Form_FieldSet">
					<legend class="B_SSM_S02_Page_Form_FieldSet_Legend">
						<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.ServerInfo.ServerInformation.Legend" />			  						
					</legend>
					<div class="B_SSM_S02_Page_Form_FieldSet_PanelContent">
						<TABLE class="B_SSM_S02_Page_Form_Table">
							<TR>
								<TD class="B_SSM_S02_Page_Form_Table_Col">
									<TABLE width="100%">
										<!-- Name -->
										<TR id="serverInfoNameDiv" style="display: none;">
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.ServerInfo.Name.Text"/>
											</TD>
											<TD>
												:&nbsp;<label id="tbxServerInfoNameID"></label>													
												<logic:iterate id="serverInfoNameItem" name="B_SSM_S02_Page_Form" property="serverInfoName">
													<html:hidden property="serverInfoName" value="${serverInfoNameItem}"/>
												</logic:iterate>
											</TD>
										</TR>
										<!-- Hardware -->
										<TR id="serverInfoHardwareDiv" style="display: none;">
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.ServerInfo.Hardware.Text"/>
											</TD>
											<TD>
												:&nbsp;<label id="tbxServerInfoHardwareID"></label>
												<logic:iterate id="serverInfoHardwareItem" name="B_SSM_S02_Page_Form" property="serverInfoHardware">
													<html:hidden property="serverInfoHardware" value="${serverInfoHardwareItem}"/>
												</logic:iterate>
											</TD>
										</TR>
										<!-- OS -->
										<TR id="serverInfoOSDiv" style="display: none;">
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.ServerInfo.OS.Text"/>
											</TD>
											<TD>
												:&nbsp;<label id="tbxServerInfoOSID"></label>
												<logic:iterate id="serverInfoOSItem" name="B_SSM_S02_Page_Form" property="serverInfoOS">
													<html:hidden property="serverInfoOS" value="${serverInfoOSItem}"/>
												</logic:iterate>
											</TD>
										</TR>
										<!-- IP -->
										<TR id="serverInfoIPDiv" style="display: none;">
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.ServerInfo.IP.Text"/>
											</TD>
											<TD>
												:&nbsp;<label id="tbxServerInfoIPID"></label>
												<logic:iterate id="serverInfoIPItem" name="B_SSM_S02_Page_Form" property="serverInfoIP">
													<html:hidden property="serverInfoIP" value="${serverInfoIPItem}"/>
												</logic:iterate>
											</TD>
										</TR>
										<!-- Gateway -->
										<TR id="serverInfoGatewayDiv" style="display: none;">
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.ServerInfo.Gateway.Text"/>
											</TD>
											<TD>
												:&nbsp;<label id="tbxServerInfoGatewayID"></label>
												<logic:iterate id="serverInfoGatewayItem" name="B_SSM_S02_Page_Form" property="serverInfoGateway">
													<html:hidden property="serverInfoGateway" value="${serverInfoGatewayItem}"/>
												</logic:iterate>
											</TD>
										</TR>
										<!-- Subnet Mask -->
										<TR id="serverInfoSubnetMaskDiv" style="display: none;">
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.ServerInfo.SubnetMask.Text"/>
											</TD>
											<TD>
												:&nbsp;<label id="tbxServerInfoSubnetMaskID"></label>
												<logic:iterate id="serverInfoSubnetMaskItem" name="B_SSM_S02_Page_Form" property="serverInfoSubnetMask">
													<html:hidden property="serverInfoSubnetMask" value="${serverInfoSubnetMaskItem}"/>
												</logic:iterate>
											</TD>
										</TR>
										<!-- MOS Version -->
										<TR id="serverInfoMOSVersionDiv" style="display: none;">
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.ServerInfo.MOSVersion.Text"/>
											</TD>
											<TD>
												:&nbsp;<label id="tbxServerInfoMOSVersionID"></label>
												<logic:iterate id="serverInfoMOSVersionItem" name="B_SSM_S02_Page_Form" property="serverInfoMOSVersion">
													<html:hidden property="serverInfoMOSVersion" value="${serverInfoMOSVersionItem}"/>
												</logic:iterate>
											</TD>
										</TR>
										<!-- Host ID -->
										<TR  id="serverInfoHostIDDiv" style="display: none;">
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.ServerInfo.HostID.Text"/>
											</TD>
											<TD>
												:&nbsp;<label id="tbxServerInfoHostIDID"></label>
												<logic:iterate id="serverInfoHostIDItem" name="B_SSM_S02_Page_Form" property="serverInfoHostID">
													<html:hidden property="serverInfoHostID" value="${serverInfoHostIDItem}"/>
												</logic:iterate>
											</TD>
										</TR>
										<!-- Serial No -->
										<TR id="serverInfoSerialNoDiv" style="display: none;">
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.ServerInfo.SerialNo.Text"/>
											</TD>
											<TD>
												:&nbsp;<label id="tbxServerInfoSerialNoID"></label>
												<logic:iterate id="serverInfoSerialNoItem" name="B_SSM_S02_Page_Form" property="serverInfoSerialNo">
													<html:hidden property="serverInfoSerialNo" value="${serverInfoSerialNoItem}"/>
												</logic:iterate>
											</TD>
										</TR>
										<!-- User license -->
										<TR id="serverInfoUserLicenseDiv" style="display: none;">
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.ServerInfo.UserLicense.Text"/>
											</TD>
											<TD>
												:&nbsp;<label id="tbxServerInfoUserLicenseID"></label>
												<logic:iterate id="serverInfoUserLicenseItem" name="B_SSM_S02_Page_Form" property="serverInfoUserLicense">
													<html:hidden property="serverInfoUserLicense" value="${serverInfoUserLicenseItem}"/>
												</logic:iterate>
											</TD>
										</TR>
										<!-- Primary DNS -->
										<TR id="serverInfoPrimaryDNSDiv" style="display: none;">
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.ServerInfo.PrimaryDNS.Text"/>
											</TD>
											<TD>
												:&nbsp;<label id="tbxServerInfoPrimaryDNSID"></label>
												<logic:iterate id="serverInfoPrimaryDNSItem" name="B_SSM_S02_Page_Form" property="serverInfoPrimaryDNS">
													<html:hidden property="serverInfoPrimaryDNS" value="${serverInfoPrimaryDNSItem}"/>
												</logic:iterate>
											</TD>
										</TR>
										<!-- Secondary DNS -->
										<TR id="serverInfoSecondaryDNSDiv" style="display: none;">
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.ServerInfo.SecondaryDNS.Text"/>
											</TD>
											<TD>
												:&nbsp;<label id="tbxServerInfoSecondaryDNSID"></label>
												<logic:iterate id="serverInfoSecondaryDNSItem" name="B_SSM_S02_Page_Form" property="serverInfoSecondaryDNS">
													<html:hidden property="serverInfoSecondaryDNS" value="${serverInfoSecondaryDNSItem}"/>
												</logic:iterate>
											</TD>
										</TR>
									</TABLE>										
								</TD>
								<TD class="B_SSM_S02_Page_Form_Table_Col">
									<TABLE width="100%">
										<!-- Model No -->
										<TR id="serverInfoModelNoDiv" style="display: none;">
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.ServerInfo.ModelNo.Text"/>
											</TD>
											<TD>
												:&nbsp;<label id="tbxServerInfoModelNoID"></label>
												<logic:iterate id="serverInfoModelNoItem" name="B_SSM_S02_Page_Form" property="serverInfoModelNo">
													<html:hidden property="serverInfoModelNo" value="${serverInfoModelNoItem}"/>
												</logic:iterate>
											</TD>
										</TR>
										<!-- Web Hosting Space -->
										<TR id="serverInfoWebHostingSpaceDiv" style="display: none;">
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.ServerInfo.WebHostingSpace.Text"/>
											</TD>
											<TD>
												:&nbsp;<label id="tbxServerInfoWebHostingSpaceID"></label>
												<logic:iterate id="serverInfoWebHostingSpaceItem" name="B_SSM_S02_Page_Form" property="serverInfoWebHostingSpace">
													<html:hidden property="serverInfoWebHostingSpace" value="${serverInfoWebHostingSpaceItem}"/>
												</logic:iterate>
											</TD>
										</TR>
										<!-- SQL -->
										<TR id="serverInfoSQLDiv" style="display: none;">
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.ServerInfo.SQL.Text"/>
											</TD>
											<TD>
												:&nbsp;
												<input type="radio" id="rdoServerInfoSQL" name="rdoServerInfoSQL" value="1" disabled="disabled"/>
												<bean:message key="B_SSM_S02_Page.Form.Yes.Text"/>&nbsp;
												<input type="radio" name="rdoServerInfoSQL" value="0" disabled="disabled"/>
												<bean:message key="B_SSM_S02_Page.Form.No.Text"/>
												<logic:iterate id="serverInfoSQLItem" name="B_SSM_S02_Page_Form" property="serverInfoSQL">
													<html:hidden property="serverInfoSQL" value="${serverInfoSQLItem}"/>
												</logic:iterate>
											</TD>
										</TR>
										<!-- SQL DB Name -->
										<TR id="serverInfoSQLDBNameDiv" style="display: none;">
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.ServerInfo.SQLDBName.Text"/>
											</TD>
											<TD>
												:&nbsp;<label id="tbxServerInfoSQLDBNameID"></label>
												<logic:iterate id="serverInfoSQLDBNameItem" name="B_SSM_S02_Page_Form" property="serverInfoSQLDBName">
													<html:hidden property="serverInfoSQLDBName" value="${serverInfoSQLDBNameItem}"/>
												</logic:iterate>
											</TD>
										</TR>
										<!-- SQL Size -->
										<TR id="serverInfoSQLSizeDiv" style="display: none;">
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.ServerInfo.SQLSize.Text"/>
											</TD>
											<TD>
												:&nbsp;<label id="tbxServerInfoSQLSizeID"></label>
												<logic:iterate id="serverInfoSQLSizeItem" name="B_SSM_S02_Page_Form" property="serverInfoSQLSize">
													<html:hidden property="serverInfoSQLSize" value="${serverInfoSQLSizeItem}"/>
												</logic:iterate>
											</TD>
										</TR>
										<!-- SQL ID -->
										<TR id="serverInfoSQLIDDiv" style="display: none;">
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.ServerInfo.SQLID.Text"/>
											</TD>
											<TD>
												:&nbsp;<label id="tbxServerInfoSQLIDID"></label>
												<logic:iterate id="serverInfoSQLIDItem" name="B_SSM_S02_Page_Form" property="serverInfoSQLID">
													<html:hidden property="serverInfoSQLID" value="${serverInfoSQLIDItem}"/>
												</logic:iterate>
											</TD>
										</TR>
										<!-- SQL DB Password -->
										<TR id="serverInfoSQLDBPasswordDiv" style="display: none;">
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.ServerInfo.SQLDBPassword.Text"/>
											</TD>
											<TD>
												:&nbsp;<label id="tbxServerInfoSQLDBPasswordID"></label>
												<logic:iterate id="serverInfoSQLDBPasswordItem" name="B_SSM_S02_Page_Form" property="serverInfoSQLDBPassword">
													<html:hidden property="serverInfoSQLDBPassword" value="${serverInfoSQLDBPasswordItem}"/>
												</logic:iterate>
											</TD>
										</TR>
										<!-- Backup -->
										<TR id="serverInfoBackupDiv" style="display: none;">
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.ServerInfo.Backup.Text"/>
											</TD>
											<TD>													
												:&nbsp;
												<input type="radio" id="rdoServerInfoBackup" name="rdoServerInfoBackup" 
															value="1" disabled="disabled">
												<bean:message key="B_SSM_S02_Page.Form.Yes.Text"/>&nbsp;													 
												<input type="radio" name="rdoServerInfoBackup"
												 			value="0" disabled="disabled">
													<bean:message key="B_SSM_S02_Page.Form.No.Text"/>
												<logic:iterate id="serverInfoBackupItem" name="B_SSM_S02_Page_Form" property="serverInfoBackup">
													<html:hidden property="serverInfoBackup" value="${serverInfoBackupItem}"/>
												</logic:iterate>
											</TD>
										</TR>
										<!-- Backup Size -->
										<TR id="serverInfoBackupSizeDiv" style="display: none;">
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.ServerInfo.BackupSize.Text"/>
											</TD>
											<TD>
												:&nbsp;<label id="tbxServerInfoBackupSizeID"></label>
												<logic:iterate id="serverInfoBackupSizeItem" name="B_SSM_S02_Page_Form" property="serverInfoBackupSize">
													<html:hidden property="serverInfoBackupSize" value="${serverInfoBackupSizeItem}"/>
												</logic:iterate>
											</TD>
										</TR>
										<!-- Monitoring -->
										<TR id="serverInfoMonitoringDiv" style="display: none;">
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.ServerInfo.Monitoring.Text"/>
											</TD>
											<TD>
												:&nbsp;
												<input type="radio" id="rdoServerInfoMonitoring" name="rdoServerInfoMonitoring" 
															value="1" disabled="disabled">
												<bean:message key="B_SSM_S02_Page.Form.Yes.Text"/>&nbsp;
												<input type="radio" name="rdoServerInfoMonitoring"
												 			value="0" disabled="disabled">
												<bean:message key="B_SSM_S02_Page.Form.No.Text"/>
												<logic:iterate id="serverInfoMonitoringItem" name="B_SSM_S02_Page_Form" property="serverInfoMonitoring">
													<html:hidden property="serverInfoMonitoring" value="${serverInfoMonitoringItem}"/>
												</logic:iterate>
											</TD>
										</TR>
										<!-- Installed Application -->
										<TR id="serverInfoInstalledApplicationDiv" style="display: none;">
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol_UpVAlign">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.ServerInfo.InstalledApplication.Text"/>
											</TD>
											<TD style="vertical-align: bottom;">								
												<div style="float: left; 
															text-align: center; 
															vertical-align: middle;">
													:
												</div>
												<div style="float: left;width:300px;word-wrap: break-word;white-space : normal">
													&nbsp;<div id="txaServerInfoInstalledApplicationID" style="overflow:hidden;white-space: pre;width:300px;"></div>
													<logic:iterate id="serverInfoInstalledApplicationItem" name="B_SSM_S02_Page_Form" property="serverInfoInstalledApplication">
														<html:textarea property="serverInfoInstalledApplication" style="display:none;" 
															value="${serverInfoInstalledApplicationItem}"/>
													</logic:iterate>
												</div>
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
													property="serverInfoMemoRemarks"/>
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