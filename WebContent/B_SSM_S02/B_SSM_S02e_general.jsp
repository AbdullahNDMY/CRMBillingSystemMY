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
	<bean:define id="accessAccountTest" name="B_SSM_S02_Page_Form" property="accessAccountTest"/>
	<div id="generalTab" 
		 style='<%=(B_SSM_S02e_BUtils.isGeneralTabEnable(infoIDArray))? "display:block;":"display:none;"%>' 
		 class="B_SSM_S02_Page_Form_Tabs_TabPanel">
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
											<%if((infoIDArray != null && infoIDArray.contains(1)) && accessPasswordFlag ==1 && "0".equals(accessAccountTest)) { %>
												<input type="hidden" id="accessAccountCheckFlg" value="1"/>
												<span style="color: red;">*</span>
											<%} %>
										</TD>
										<TD>
											:&nbsp;
											<html:text name="B_SSM_S02_Page_Form" 
													   styleClass="B_SSM_S02_Page_Form_TextBox"  
													   maxlength="50"
													   styleId="accessAccountID"
													   disabled='<%=((infoIDArray != null && infoIDArray.contains(1)) && accessAccountFlag==1 && accessPasswordFlag ==1 && "0".equals(accessAccountTest)) ? false : true%>'
													   property="accessAccount"/>
											<%if (!((infoIDArray != null && infoIDArray.contains(1)) && accessAccountFlag==1 && accessPasswordFlag ==1 && accessAccountTest == "0")) { %>	
												<html:hidden property="accessAccount" name="B_SSM_S02_Page_Form" />	
											<%} %>	   
										</TD>
									</TR>	
									<!-- Access Password -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.RouterSettingInformation.AccessPassword.Text"/>
											<%if((infoIDArray != null && infoIDArray.contains(2)) && accessPasswordFlag ==1 && "0".equals(accessAccountTest)) { %>
												<input type="hidden" id="accessPasswordCheckFlg" value="1"/>
												<span style="color: red;">*</span>
											<%} %>
										</TD>
										<TD>
											:&nbsp;
											<html:text name="B_SSM_S02_Page_Form" 
													   styleClass="B_SSM_S02_Page_Form_TextBox"  
													   disabled='<%=((infoIDArray != null && infoIDArray.contains(1)) && accessAccountFlag==1 && accessPasswordFlag ==1 && "0".equals(accessAccountTest))? false : true%>'
													   maxlength="20"
													   styleId="accessPasswordID"
													   property="accessPassword"/>
											<%if (!((infoIDArray != null && infoIDArray.contains(1)) && accessAccountFlag==1 && accessPasswordFlag ==1 && "0".equals(accessAccountTest))) { %>	
												<html:hidden property="accessPassword" name="B_SSM_S02_Page_Form" />	
											<%} %>	   
										</TD>
									</TR>	
									<!-- Access Telephone Number -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.RouterSettingInformation.AccessTelephoneNumber.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<html:text name="B_SSM_S02_Page_Form" 
													   styleClass="B_SSM_S02_Page_Form_TextBox"  
													   disabled='<%=(infoIDArray != null && infoIDArray.contains(3))? false : true%>'
													   maxlength="20"
													   styleId="accessTelephoneNumberID"
													   property="accessTelephoneNumber"/>
											<%if (!(infoIDArray != null && infoIDArray.contains(3))) { %>	
												<html:hidden property="accessTelephoneNumber" name="B_SSM_S02_Page_Form" />	
											<%} %>	  
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
											<html:text name="B_SSM_S02_Page_Form" 
													   styleClass="B_SSM_S02_Page_Form_TextBox"  
													   disabled='<%=(infoIDArray != null && infoIDArray.contains(4))? false : true%>'
													   maxlength="50"
													   styleId="sSID"
													   property="sSID"/>
										</TD>
									</TR>
									<!-- Wep Key -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.RouterSettingInformation.WepKey.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<html:text name="B_SSM_S02_Page_Form" 
													   styleClass="B_SSM_S02_Page_Form_TextBox"  
													   disabled='<%=(infoIDArray != null && infoIDArray.contains(5))? false : true%>'
													   maxlength="50"
													   styleId="wepKeyID"
													   property="wepKey"/>
										</TD>
									</TR>	
									<!-- Router Password -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.RouterSettingInformation.RouterPassword.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<html:text name="B_SSM_S02_Page_Form" 
													   styleClass="B_SSM_S02_Page_Form_TextBox"  
													   disabled='<%=(infoIDArray != null && infoIDArray.contains(6))? false : true%>'
													   maxlength="20"
													   styleId="routerPasswordID"
													   property="routerPassword"/>
										</TD>
									</TR>	
									<!-- Router No -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.RouterSettingInformation.RouterNo.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<html:text name="B_SSM_S02_Page_Form" 
													   styleClass="B_SSM_S02_Page_Form_TextBox"  
													   disabled='<%=(infoIDArray != null && infoIDArray.contains(7))? false : true%>'
													   maxlength="50"
													   styleId="routerNoID"
													   property="routerNo"/>
										</TD>
									</TR>		
									<!-- Router Type -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.RouterSettingInformation.RouterType.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<html:text name="B_SSM_S02_Page_Form" 
													   styleClass="B_SSM_S02_Page_Form_TextBox" 
													   disabled='<%=(infoIDArray != null && infoIDArray.contains(8))? false : true%>' 
													   maxlength="50"
													   styleId="routerTypeID"
													   property="routerType"/>
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
										<TD>
											:&nbsp;
											<html:text name="B_SSM_S02_Page_Form" 
													   styleClass="B_SSM_S02_Page_Form_TextBox"													  
													   maxlength="50"
													   styleId="modemNoID"
													   property="modemNo"/>
										</TD>
									</TR>
									<!-- Mac ID -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.RouterSettingInformation.MacID.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<html:text name="B_SSM_S02_Page_Form" 
													   styleClass="B_SSM_S02_Page_Form_TextBox"													  
													   maxlength="50"
													   styleId="macID"
													   property="macID"/>															   																			
										</TD>
									</TR>		
									<!-- ADSL/Del No. -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.RouterSettingInformation.ADSL_DelNo.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<html:text name="B_SSM_S02_Page_Form" 
													   styleClass="B_SSM_S02_Page_Form_TextBox"  
													   disabled='<%=(infoIDArray != null && infoIDArray.contains(11))? false : true%>'
													   maxlength="50"
													   styleId="aDSL_DelNoID"
													   property="aDSL_DelNo"/>
										</TD>
									</TR>		
									<!-- Circuit ID -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.RouterSettingInformation.CircuitID.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<html:text name="B_SSM_S02_Page_Form" 
													   styleClass="B_SSM_S02_Page_Form_TextBox"  
													   disabled='<%=(infoIDArray != null && infoIDArray.contains(12))? false : true%>'
													   maxlength="50"
													   styleId="circuitID"
													   property="circuitID"/>
										</TD>
									</TR>	
									<!-- Carrier -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.RouterSettingInformation.Carrier.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<t:defineCodeList id="carrierCodeList">
											</t:defineCodeList>
											<html:select name="B_SSM_S02_Page_Form" 
														 styleClass="B_SSM_S02_Page_Form_SelectBox"  
														 disabled='<%=(infoIDArray != null && infoIDArray.contains(13))? false : true%>'
													     styleId="carrierID"
														 property="carrier">
												<html:option value="">
													<bean:message key="B_SSM_S02_Page.BlankSelectText"/>
												</html:option>
												<html:optionsCollection name="carrierCodeList" label="name" value="id"/>			 
											</html:select>			 																 												
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
		<div style='<%=(infoIDArray != null && infoIDArray.contains(15))? "display:block;":"display:none;"%>' 
			 class="B_SSM_S02_Page_Form_Container_FieldSet">
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
		
		<!-- Installation Address Group -->
		<div style='<%=(infoIDArray != null && infoIDArray.contains(18))? "display:block;":"display:none;"%>' 
			 class="B_SSM_S02_Page_Form_Container_FieldSet">
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
								<html:text name="B_SSM_S02_Page_Form" 
										   styleClass="B_SSM_S02_Page_Form_TextBox_InstallationAddress"  
										   maxlength="150"
										   styleId="installation1Address1ID"
										   property="installation1Address1"/>
								<br/>
								<!-- Installation 1 Address 2 -->
								&nbsp;&nbsp;
								<html:text name="B_SSM_S02_Page_Form" 
										   styleClass="B_SSM_S02_Page_Form_TextBox_InstallationAddress"  
										   maxlength="150"
										   styleId="installation1Address2ID"
										   property="installation1Address2"/>
								<br/>
								<!-- Installation 1 Address 3 -->
								&nbsp;&nbsp;
								<html:text name="B_SSM_S02_Page_Form" 
										   styleClass="B_SSM_S02_Page_Form_TextBox_InstallationAddress"  
										   maxlength="150"
										   styleId="installation1Address3ID"
										   property="installation1Address3"/>
								<br/>
								<!-- Installation 1 ZipCode -->
								&nbsp;&nbsp;
								<html:text name="B_SSM_S02_Page_Form" 	
										   styleClass="B_SSM_S02_Page_Form_TextBox_ZipCode"  											   
										   maxlength="15"
										   styleId="installation1ZipCodeID"
										   property="installation1ZipCode"/>
								<!-- Installation 1 CountryName -->	
								<t:defineCodeList id="countryCodeList">
								</t:defineCodeList>									
								<html:select name="B_SSM_S02_Page_Form" 	
											 styleClass="B_SSM_S02_Page_Form_TextBox_CountryName"  													
											 styleId="installation1CountryNameID"
											 property="installation1CountryName">
									<html:option value="">
										<bean:message key="B_SSM_S02_Page.BlankSelectText"/>
									</html:option>
									<html:optionsCollection name="countryCodeList" label="name" value="id"/>			
								</html:select>																													 
											 												
							</TD>									
							<!-- Installation Address 2 -->									
							<TD class="B_SSM_S02_Page_Form_Table_Col">
								<!-- Installation 2 Address 1 -->
								<span class="B_SSM_S02_Page_Form_Span_IntallationAddress">2</span>
								<html:text name="B_SSM_S02_Page_Form" 
										   styleClass="B_SSM_S02_Page_Form_TextBox_InstallationAddress"  
										   maxlength="150"
										   styleId="installation2Address1ID"
										   property="installation2Address1"/>
								<br/>
								<!-- Installation 2 Address 2 -->
								&nbsp;&nbsp;
								<html:text name="B_SSM_S02_Page_Form" 
										   styleClass="B_SSM_S02_Page_Form_TextBox_InstallationAddress"  
										   maxlength="150"
										   styleId="installation2Address2ID"
										   property="installation2Address2"/>
								<br/>
								<!-- Installation 2 Address 3 -->
								&nbsp;&nbsp;
								<html:text name="B_SSM_S02_Page_Form" 
										   styleClass="B_SSM_S02_Page_Form_TextBox_InstallationAddress"  
										   maxlength="150"
										   styleId="installation2Address3ID"
										   property="installation2Address3"/>
								<br/>
								<!-- Installation 2 ZipCode -->
								&nbsp;&nbsp;
								<html:text name="B_SSM_S02_Page_Form" 			
										   styleClass="B_SSM_S02_Page_Form_TextBox_ZipCode"  									   
										   maxlength="15"
										   styleId="installation2ZipCodeID"
										   property="installation2ZipCode"/>
								<!-- Installation 2 CountryName -->
								<html:select name="B_SSM_S02_Page_Form" 	
											 styleClass="B_SSM_S02_Page_Form_TextBox_CountryName"  												
											 styleId="installation2CountryNameID"
											 property="installation2CountryName">
									<html:option value="">
										<bean:message key="B_SSM_S02_Page.BlankSelectText"/>
									</html:option>
									<html:optionsCollection name="countryCodeList" label="name" value="id"/>				
								</html:select>
							</TD>
						</TR>
					</TABLE>	
				</div>
			</fieldset>
		</div>
		
		<!-- FTP Interface Information Group -->
		<div style='<%=(infoIDArray != null && infoIDArray.contains(19))? "display:block;":"display:none;"%>' 
			 class="B_SSM_S02_Page_Form_Container_FieldSet">
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
											<html:text name="B_SSM_S02_Page_Form" 
													   styleClass="B_SSM_S02_Page_Form_TextBox"  
													   maxlength="40"
													   styleId="fTP_IPAddressID"
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
											<html:text name="B_SSM_S02_Page_Form"
													   styleClass="B_SSM_S02_Page_Form_TextBox"  
													   maxlength="30"
													   styleId="fTPAccountNameID"
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
											<html:text name="B_SSM_S02_Page_Form" 
													   styleClass="B_SSM_S02_Page_Form_TextBox"  
													   maxlength="30"
													   styleId="fTPPasswordID"
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
											<html:text name="B_SSM_S02_Page_Form" 
													   styleClass="B_SSM_S02_Page_Form_TextBox"  
													   maxlength="20"
													   styleId="capacityOfWebServerID"
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
											<html:text name="B_SSM_S02_Page_Form" 
													   styleClass="B_SSM_S02_Page_Form_TextBox"  
													   maxlength="100"
													   styleId="fTPDefaultPageID"
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
											<html:text name="B_SSM_S02_Page_Form" 
													   styleClass="B_SSM_S02_Page_Form_TextBox"  
													   maxlength="100"
													   styleId="fTPURLID"
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
		
		<!-- MRTG Monitoring Group -->
		<div style='<%=(infoIDArray != null && infoIDArray.contains(21))? "display:block;":"display:none;"%>' 
			 class="B_SSM_S02_Page_Form_Container_FieldSet">
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
											<html:text name="B_SSM_S02_Page_Form" 
													   styleClass="B_SSM_S02_Page_Form_TextBox"  
													   maxlength="100"
													   styleId="mRTGURLID"
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
											<html:text name="B_SSM_S02_Page_Form" 
													   styleClass="B_SSM_S02_Page_Form_TextBox"  
													   maxlength="30"
													   styleId="mGTRID"
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
											<html:text name="B_SSM_S02_Page_Form" 
													   styleClass="B_SSM_S02_Page_Form_TextBox"  
													   maxlength="30"
													   styleId="mRTGPasswordID"
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
	</div>
</body>
</html>