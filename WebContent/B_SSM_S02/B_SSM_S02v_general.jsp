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
	<html:hidden property="accessAccountFlag" name="B_SSM_S02_Page_Form" />
	<html:hidden property="accessAccountTest" name="B_SSM_S02_Page_Form" />
	<bean:define id="accessAccountFlag" name="B_SSM_S02_Page_Form" property="accessAccountFlag" type="java.lang.Integer"/>
	<% if (B_SSM_S02e_BUtils.isGeneralTabEnable(infoIDArray)) { %>
			<div id="generalTab" class="B_SSM_S02_Page_Form_Tabs_TabPanel">
				<!-- Router Setting Information Group -->
				<div class="B_SSM_S02_Page_Form_Container_FieldSet">
					<fieldset class="B_SSM_S02_Page_Form_FieldSet">
						<legend class="B_SSM_S02_Page_Form_FieldSet_Legend">
							<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.RouterSettingInformation.Legend" />			  						
						</legend>
						<div class="B_SSM_S02_Page_Form_FieldSet_PanelContent">
							<TABLE class="B_SSM_S02_Page_Form_Table">
								<TR>
									<TD class="B_SSM_S02_Page_Form_Table_Col">
										<TABLE style="width:100%;">
											<!-- Access Account  -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.RouterSettingInformation.AccessAccount.Text"/>
												</TD>
												<TD>
													:&nbsp;													
													<%if (infoIDArray != null && infoIDArray.contains(1)) { %>
														<bean:write name="B_SSM_S02_Page_Form" 
																    property="accessAccount"/>
													<%} else {
															out.print("-");
														}
													%>
													<html:hidden property="accessAccount" styleId="accessAccount" name="B_SSM_S02_Page_Form"/>
													<html:hidden property="accessPassword" styleId="accessPassword" name="B_SSM_S02_Page_Form"/>
												</TD>
												<TD>
													<logic:equal value="2" property="accessType" name="B_SSM_S02_Page_Form">
														<c:if test="${B_SSM_S02_Page_Form.map.planStatus eq 'PS1' || B_SSM_S02_Page_Form.map.planStatus eq 'PS2'}">
															<logic:equal value="1" property="isRadiusFlag" name="B_SSM_S02_Page_Form">
																<logic:equal value="0" property="accessAccountTest" name="B_SSM_S02_Page_Form">
																	<ts:submit styleId="testStartBtnID" 
															  			   styleClass="B_SSM_S02_Page_Form_Buttons_Text"
															  			   onclick="setTextField('processModeField', 7);">
															  			<bean:message key="B_SSM_S02_Page.Form.TestStartBtn.Text"/>
															  		</ts:submit>
															  	</logic:equal>
															  	<logic:equal value="1" property="accessAccountTest" name="B_SSM_S02_Page_Form">
															  		<ts:submit styleId="testCompleteBtnID" 
															  			   styleClass="B_SSM_S02_Page_Form_Buttons_Text"
															  			   onclick="setTextField('processModeField', 8);">
															  			<bean:message key="B_SSM_S02_Page.Form.TestCompleteBtn.Text"/>
															  		</ts:submit>
															  	</logic:equal>
															</logic:equal>
														</c:if>
														<logic:equal value="PS3" property="planStatus" name="B_SSM_S02_Page_Form">
															<logic:equal value="1" property="isActiveRadiusFlag" name="B_SSM_S02_Page_Form">
																<c:if test="${accessAccountFlag==0}">
														  			<button class="B_SSM_S02_Page_Form_Buttons_Text" style="width:180px;" id="changeAccessAccountBtnID" onclick="javascript: changeAccessAccountClick(this);"><bean:message key="B_SSM_S02_Page.Form.ChangeAccessAcountBtn.Text"/></button>
																</c:if>
															</logic:equal>
														</logic:equal>
													</logic:equal>
												</TD>
											</TR>	
											<!-- Access Password -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.RouterSettingInformation.AccessPassword.Text"/>
												</TD>
												<TD>
													:&nbsp;
													<%if (infoIDArray != null && infoIDArray.contains(2)) { %>
														<bean:write name="B_SSM_S02_Page_Form" 															  
															    	property="accessPassword"/>
													<%} else {
															out.print("-");
														}
													 %>
												</TD>
												<TD>
													<logic:equal value="2" property="accessType" name="B_SSM_S02_Page_Form">
														<logic:equal value="PS3" property="planStatus" name="B_SSM_S02_Page_Form">
															<logic:equal value="1" property="isActiveRadiusFlag" name="B_SSM_S02_Page_Form">
																<c:if test="${accessAccountFlag==0}">
														  			<button class="B_SSM_S02_Page_Form_Buttons_Text" style="width:180px;" id="changePasswordBtnID" onclick="javascript: changePassword(this);"><bean:message key="B_SSM_S02_Page.Form.ChangePasswordBtn.Text"/></button>
																</c:if>
															</logic:equal>
														</logic:equal>
													</logic:equal>
												</TD>
											</TR>	
											<!-- Access Telephone Number -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.RouterSettingInformation.AccessTelephoneNumber.Text"/>
												</TD>
												<TD colspan="2">
													:&nbsp;
													<%if (infoIDArray != null && infoIDArray.contains(3)) { %>
														<bean:write name="B_SSM_S02_Page_Form" 															   
															    	property="accessTelephoneNumber"/>
													<%} else {
															out.print("-");
														}
													%>
												</TD>
											</TR>					
										</TABLE>
									</TD>									
								</TR>
								
								<TR>
									<TD class="B_SSM_S02_Page_Form_Table_Col">
										<TABLE width="100%">
											<!-- SSID -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.RouterSettingInformation.SSID.Text"/>
												</TD>
												<TD>
													:&nbsp;
													<%if (infoIDArray != null && infoIDArray.contains(4)) { %>
														<bean:write name="B_SSM_S02_Page_Form" 															   
															        property="sSID"/>
													<%} else {
															out.print("-");
														}
													%>
												</TD>
											</TR>
											<!-- Wep Key -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.RouterSettingInformation.WepKey.Text"/>
												</TD>
												<TD>
													:&nbsp;
													<%if (infoIDArray != null && infoIDArray.contains(5)) { %>
														<bean:write name="B_SSM_S02_Page_Form" 															   
															        property="wepKey"/>
													<%} else {
															out.print("-");
														}
													%>
												</TD>
											</TR>	
											<!-- Router Password -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.RouterSettingInformation.RouterPassword.Text"/>
												</TD>
												<TD>
													:&nbsp;
													<%if (infoIDArray != null && infoIDArray.contains(6)) { %>
														<bean:write name="B_SSM_S02_Page_Form" 															   
															        property="routerPassword"/>
													<%} else {
															out.print("-");
														}
													%>
												</TD>
											</TR>	
											<!-- Router No -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.RouterSettingInformation.RouterNo.Text"/>
												</TD>
												<TD>
													:&nbsp;
													<%if (infoIDArray != null && infoIDArray.contains(7)) { %>
														<bean:write name="B_SSM_S02_Page_Form" 															  
															        property="routerNo"/>
													<%} else {
															out.print("-");
														}
													%>
												</TD>
											</TR>		
											<!-- Router Type -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.RouterSettingInformation.RouterType.Text"/>
												</TD>
												<TD>
													:&nbsp;
													<%if (infoIDArray != null && infoIDArray.contains(8)) { %>
														<bean:write name="B_SSM_S02_Page_Form" 															  
															        property="routerType"/>
													<%} else {
															out.print("-");
														}
													%>
												</TD>
											</TR>							
										</TABLE>	
									</TD>
									
									<TD class="B_SSM_S02_Page_Form_Table_Col">
										<TABLE width="100%">
											<!-- Modem No. -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.RouterSettingInformation.ModemNo.Text"/>
												</TD>
												<TD style="width:350px;word-wrap: break-word;white-space : normal">
													
													<%if (infoIDArray != null && infoIDArray.contains(9)) { %>
														:&nbsp;<bean:write name="B_SSM_S02_Page_Form" 															     
																	property="modemNo"/>
													<%} else {
													    out.print(":&nbsp;-");
														}
													%>
												</TD>
											</TR>
											<!-- Mac ID -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.RouterSettingInformation.MacID.Text"/>
												</TD>
												<TD style="width:350px;word-wrap: break-word;white-space : normal">												
													<%if (infoIDArray != null && infoIDArray.contains(10)) { %>
														:&nbsp;<bean:write name="B_SSM_S02_Page_Form" property="macID"/>
													<%} else {
															out.print(":&nbsp;-");
														}
													%>																					
												</TD>
											</TR>		
											<!-- ADSL/Del No. -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.RouterSettingInformation.ADSL_DelNo.Text"/>
												</TD>
												<TD style="width:350px;word-wrap: break-word;white-space : normal">
													<%if (infoIDArray != null && infoIDArray.contains(11)) { %>
														:&nbsp;<bean:write name="B_SSM_S02_Page_Form" 															   
															    	property="aDSL_DelNo"/>
													<%} else {
															out.print(":&nbsp;-");
														}
													%>	
												</TD>
											</TR>		
											<!-- Circuit ID -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.RouterSettingInformation.CircuitID.Text"/>
												</TD>
												<TD style="width:350px;word-wrap: break-word;white-space : normal">
													<%if (infoIDArray != null && infoIDArray.contains(12)) { %>
														:&nbsp;<bean:write name="B_SSM_S02_Page_Form" 															   
															        property="circuitID"/>
													<%} else {
															out.print(":&nbsp;-");
														}
													%>
												</TD>
											</TR>	
											<!-- Carrier -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.RouterSettingInformation.Carrier.Text"/>
												</TD>
												<TD style="width:350px;word-wrap: break-word;white-space : normal">
													<%if (infoIDArray != null && infoIDArray.contains(13)) { %>
														:&nbsp;<bean:write name="B_SSM_S02_Page_Form" 																 
																	property="carrier"/>
													<%} else {
															out.print(":&nbsp;-");
														}
													%>
												</TD>
											</TR>						
										</TABLE>
									</TD>
								</TR>								
							</TABLE>							
						</div>
					</fieldset>
				</div>		
							
				<!-- Browser Information Group -->
				<%if (infoIDArray != null && infoIDArray.contains(15)) { %>
				<div class="B_SSM_S02_Page_Form_Container_FieldSet">
					<fieldset class="B_SSM_S02_Page_Form_FieldSet">
						<legend class="B_SSM_S02_Page_Form_FieldSet_Legend">
							<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.BrowserInformation.Legend" />			  						
						</legend>
						<div class="B_SSM_S02_Page_Form_FieldSet_PanelContent">
							<TABLE class="B_SSM_S02_Page_Form_Table">
								<TR>
									<TD class="B_SSM_S02_Page_Form_Table_Col">
										<TABLE width="100%">
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.BrowserInformation.ProxyServerName.Text"/>
												</TD>
												<TD>
													:&nbsp;													
													<bean:write name="B_SSM_S02_Page_Form" property="proxyServerName"/>
												</TD>
											</TR>
										</TABLE>										
									</TD>
									<TD class="B_SSM_S02_Page_Form_Table_Col">
										<TABLE width="100%">
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.BrowserInformation.ProxyNumber.Text"/>
												</TD>
												<TD>
													:&nbsp;													
													<bean:write name="B_SSM_S02_Page_Form" property="proxyNumber"/>
												</TD>
											</TR>
										</TABLE>	
									</TD>
								</TR>
							</TABLE>
						</div>
					</fieldset>
				</div>
				<%}%>	
				
				<!-- Installation Address Group -->
				<%if (infoIDArray != null && infoIDArray.contains(18)) { %>
				<div class="B_SSM_S02_Page_Form_Container_FieldSet">
					<fieldset class="B_SSM_S02_Page_Form_FieldSet">
						<legend class="B_SSM_S02_Page_Form_FieldSet_Legend">
							<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.InstallationAddress.Legend" />			  						
						</legend>
						<div class="B_SSM_S02_Page_Form_FieldSet_PanelContent">
							<TABLE class="B_SSM_S02_Page_Form_Table">
								<TR>									
									<!-- Installation Address 1 -->									
									<TD class="B_SSM_S02_Page_Form_Table_Col">
										<!-- Installation 1 Address 1 -->
										<span class="B_SSM_S02_Page_Form_Span_IntallationAddress">1</span>
										<bean:write name="B_SSM_S02_Page_Form" 												   
												    property="installation1Address1"/>
										<br/>
										<!-- Installation 1 Address 2 -->
										&nbsp;&nbsp;
										<bean:write name="B_SSM_S02_Page_Form" 												   
												    property="installation1Address2"/>
										<br/>
										<!-- Installation 1 Address 3 -->
										&nbsp;&nbsp;
										<bean:write name="B_SSM_S02_Page_Form" 												   
												    property="installation1Address3"/>
										<br/>
										<!-- Installation 1 ZipCode -->
										&nbsp;&nbsp;
										<bean:write name="B_SSM_S02_Page_Form" 													  
												   property="installation1ZipCode"/>
										<!-- Installation 1 CountryName -->										
										<bean:write name="B_SSM_S02_Page_Form" 														
													property="installation1CountryName"/>
									</TD>									
									<!-- Installation Address 2 -->									
									<TD class="B_SSM_S02_Page_Form_Table_Col">
										<!-- Installation 2 Address 1 -->
										<span class="B_SSM_S02_Page_Form_Span_IntallationAddress">2</span>
										<bean:write name="B_SSM_S02_Page_Form" 												  
												    property="installation2Address1"/>
										<br/>
										<!-- Installation 2 Address 2 -->
										&nbsp;&nbsp;
										<bean:write name="B_SSM_S02_Page_Form" 												   
												    property="installation2Address2"/>
										<br/>
										<!-- Installation 2 Address 3 -->
										&nbsp;&nbsp;
										<bean:write name="B_SSM_S02_Page_Form" 												   
												    property="installation2Address3"/>
										<br/>
										<!-- Installation 2 ZipCode -->
										&nbsp;&nbsp;
										<bean:write name="B_SSM_S02_Page_Form" 															   
												    property="installation2ZipCode"/>
										<!-- Installation 2 CountryName -->
										<bean:write name="B_SSM_S02_Page_Form" 														 
													property="installation2CountryName"/>
									</TD>
								</TR>
							</TABLE>	
						</div>
					</fieldset>
				</div>	
				<%}%>

				<!-- FTP Interface Information Group -->
				<%if (infoIDArray != null && infoIDArray.contains(19)) { %>
				<div class="B_SSM_S02_Page_Form_Container_FieldSet">
					<fieldset class="B_SSM_S02_Page_Form_FieldSet">
						<legend class="B_SSM_S02_Page_Form_FieldSet_Legend">
							<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.FTPSettingInformation.Legend" />			  						
						</legend>
						<div class="B_SSM_S02_Page_Form_FieldSet_PanelContent">
							<TABLE class="B_SSM_S02_Page_Form_Table">
								<TR>
									<TD class="B_SSM_S02_Page_Form_Table_Col">
										<TABLE width="100%">
											<!-- FTP IP Address -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.FTPInterfaceInformation.FTP_IPAddress.Text"/>
												</TD>
												<TD>
													:&nbsp;
													<bean:write name="B_SSM_S02_Page_Form" 															  
															    property="fTP_IPAddress"/>
												</TD>
											</TR>
											<!-- FTP Account Name -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.FTPInterfaceInformation.FTPAccountName.Text"/>
												</TD>
												<TD>
													:&nbsp;
													<bean:write name="B_SSM_S02_Page_Form"															  
															   property="fTPAccountName"/>
												</TD>
											</TR>
											<!-- FTP Password -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.FTPInterfaceInformation.FTPPassword.Text"/>
												</TD>
												<TD>
													:&nbsp;
													<bean:write name="B_SSM_S02_Page_Form" 															   
															    property="fTPPassword"/>
												</TD>
											</TR>
											<!-- Capacity of Web Server -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.FTPInterfaceInformation.CapacityOfWebServer.Text"/>
												</TD>
												<TD>
													:&nbsp;
													<bean:write name="B_SSM_S02_Page_Form" 															   
															    property="capacityOfWebServer"/>
												</TD>
											</TR>
											<!-- Default Page -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.FTPInterfaceInformation.DefaultPage.Text"/>
												</TD>
												<TD>
													:&nbsp;
													<bean:write name="B_SSM_S02_Page_Form" 															   
															    property="fTPDefaultPage"/>
												</TD>
											</TR>
											<!-- URL -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.FTPInterfaceInformation.URL.Text"/>
												</TD>
												<TD>
													:&nbsp;
													<bean:write name="B_SSM_S02_Page_Form" 															   
															   property="fTPURL"/>
												</TD>
											</TR>
										</TABLE>	
									</TD>									
								</TR>
							</TABLE>	
						</div>
					</fieldset>
				</div>
				<%}%>
	
				<!-- MRTG Monitoring Group -->
				<%if (infoIDArray != null && infoIDArray.contains(21)) { %>
				<div class="B_SSM_S02_Page_Form_Container_FieldSet">
					<fieldset class="B_SSM_S02_Page_Form_FieldSet">
						<legend class="B_SSM_S02_Page_Form_FieldSet_Legend">
							<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.MRTGMonitoring.Legend" />			  						
						</legend>
						<div class="B_SSM_S02_Page_Form_FieldSet_PanelContent">
							<TABLE class="B_SSM_S02_Page_Form_Table">
								<TR>
									<TD class="B_SSM_S02_Page_Form_Table_Col">
										<TABLE width="100%">
											<!-- URL -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.MRTGMonitoring.URL.Text" />		
												</TD>
												<TD>
													:&nbsp;
													<bean:write name="B_SSM_S02_Page_Form" 															  
															    property="mRTGURL"/>
												</TD>
											</TR>
											<!-- ID -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.MRTGMonitoring.ID.Text" />		
												</TD>
												<TD>
													:&nbsp;
													<bean:write name="B_SSM_S02_Page_Form" 															   
															   property="mGTRID"/>
												</TD>
											</TR>
											<!-- Password -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.MRTGMonitoring.Password.Text" />		
												</TD>
												<TD>
													:&nbsp;
													<bean:write name="B_SSM_S02_Page_Form" 															   
															    property="mRTGPassword"/>
												</TD>
											</TR>
										</TABLE>
									</TD>
								</TR>
							</TABLE>
						</div>
					</fieldset>
				</div>	
				<%}%>
			</div>
			<%}%>
</body>
</html>