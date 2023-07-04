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
	<div id="mailAddressTab" 
		 style='<%=(B_SSM_S02e_BUtils.isAddressTabEnable(infoIDArray))? "display:block;":"display:none;"%>' 
		 class="B_SSM_S02_Page_Form_Tabs_TabPanel">
		<!-- Mail Server Information Group -->
		<div style='<%=(infoIDArray != null && infoIDArray.contains(16))? "display:block;":"display:none;"%>' 
			 class="B_SSM_S02_Page_Form_Container_FieldSet">
			<fieldset class="B_SSM_S02_Page_Form_FieldSet">
				<legend class="B_SSM_S02_Page_Form_FieldSet_Legend">
					<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.MailServerInformation.Legend" />			  						
				</legend>
				<div class="B_SSM_S02_Page_Form_FieldSet_PanelContent">
					<TABLE class="B_SSM_S02_Page_Form_Table">
	                        <TR>
	                            <TD class="B_SSM_S02_Page_Form_Table_Col">
	                                <TABLE width="100%">
					                    <!-- Primary Domain Name Server -->
	                                    <TR>
	                                        <TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
	                                            <bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.MailServerInformation.PrimaryDomainNameServer.Text"/>
	                                        </TD>
	                                        <TD>
	                                            :&nbsp;
	                                            <bean:write name="B_SSM_S02_Page_Form" property="primaryDomainNameServer"/>
	                                        </TD>
	                                    </TR>
					                </TABLE>
					            </TD>
					            <TD class="B_SSM_S02_Page_Form_Table_Col">
					                <TABLE width="100%">
					                <!-- Secondary Domain Name Server -->
                                    <TR>
                                        <TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
                                            <bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.MailServerInformation.SecondaryDomainNameServer.Text"/>
                                        </TD>
                                        <TD>
                                            :&nbsp;
                                            <bean:write name="B_SSM_S02_Page_Form" property="secondaryDomainNameServer"/>
                                        </TD>
                                    </TR>
                                    </TABLE>
					            </TD>
					        </TR>
					</TABLE>
				</div>
				<div class="B_SSM_S02_Page_Form_Container_FieldSet">
				<fieldset class="B_SSM_S02_Page_Form_FieldSet">
	                <legend class="B_SSM_S02_Page_Form_FieldSet_Legend">
                        <input type="checkbox" onclick="doDefaultSetting(this)"/><bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.MailServerInformation.defaultset.Legend" />
	                </legend>
					<div class="B_SSM_S02_Page_Form_FieldSet_PanelContent">
						<TABLE class="B_SSM_S02_Page_Form_Table">
							<TR>
								<TD class="B_SSM_S02_Page_Form_Table_Col">
									<TABLE width="100%">
										<!-- Domain Name -->
										<TR>
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.MailServerInformation.DomainName.Text"/>
											</TD>
											<TD>
												:&nbsp;
												<html:text name="B_SSM_S02_Page_Form" 
														   styleClass="B_SSM_S02_Page_Form_TextBox"  
														   maxlength="100"
														   styleId="domainName"
														   property="domainName"/>
											    <html:hidden name="B_SSM_S02_Page_Form" property="domainName" styleId="domainNameHiddenVal" />
											</TD>
										</TR>
										<!-- SMTP Server Name -->
										<TR>
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.MailServerInformation.SMTPServerName.Text"/>
											</TD>
											<TD>
												:&nbsp;
												<html:text name="B_SSM_S02_Page_Form" 
														   styleClass="B_SSM_S02_Page_Form_TextBox"  
														   maxlength="100"
														   styleId="sMTPServerNameID"
														   property="sMTPServerName"/>
												<html:hidden name="B_SSM_S02_Page_Form" property="sMTPServerName" styleId="sMTPServerNameHiddenVal" />
											</TD>
										</TR>
									</TABLE>
								</TD>	
								
								<TD class="B_SSM_S02_Page_Form_Table_Col">
									<TABLE width="100%">
										<!-- WebMail URL -->
										<TR>
                                            <TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
                                                <bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.MailServerInformation.WebMailURL.Text"/>
                                            </TD>
                                            <TD>
                                                :&nbsp;
                                                <html:text name="B_SSM_S02_Page_Form" 
                                                           styleClass="B_SSM_S02_Page_Form_TextBox"  
                                                           maxlength="100"
                                                           styleId="webmailURLId"
                                                           property="webmailURL"/>
                                                 <html:hidden name="B_SSM_S02_Page_Form" property="webmailURL" styleId="webmailURLHiddenVal" />
                                            </TD>
										</TR>
										<!-- POP Server Name -->
										<TR>
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.MailServerInformation.POPServerName.Text"/>
											</TD>
											<TD>
												:&nbsp;
												<html:text name="B_SSM_S02_Page_Form" 
														   styleClass="B_SSM_S02_Page_Form_TextBox"  
														   maxlength="100"
														   styleId="pOPServerNameID"
														   property="pOPServerName"/>
												<html:hidden name="B_SSM_S02_Page_Form" property="pOPServerName" styleId="pOPServerNameHiddenVal" />
											</TD>
										</TR>
									</TABLE>
								</TD>	
							</TR>
						</TABLE>	
					</div>
				</fieldset>
				</div>
			</fieldset>
		</div>
		
		<!-- Auto Update Quantity Group -->
		<div class="B_SSM_S02_Page_Form_Container_FieldSet">
			<fieldset class="B_SSM_S02_Page_Form_FieldSet">
				<legend class="B_SSM_S02_Page_Form_FieldSet_Legend">
					<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.AutoUpdateQuantity.Legend" />			  						
				</legend>
				<div class="B_SSM_S02_Page_Form_FieldSet_PanelContent">
					<TABLE class="B_SSM_S02_Page_Form_Table">
						<!-- Mail Account -->
						<TR>
							<!-- Auto Mail Account -->
							<TD class="B_SSM_S02_Page_Form_Table_Col_CheckBox" style="vertical-align: middle;">
								<logic:equal value="1" name="B_SSM_S02_Page_Form" property="autoMailAcc">	
									<img border="0" src="../image/checked.jpg" alt="Checked" />
								</logic:equal>
								<html:checkbox property="autoMailAcc"
											   styleId="autoMailAccID"
											   name="B_SSM_S02_Page_Form" 
											   onclick="doCheckAutoMailAccount(this);"
											   value="1" style="display:none">
								</html:checkbox>
							</TD>
							<!-- Mail Account Labels -->
							<TD class="B_SSM_S02_Page_Form_Table_Col_AutoUpdateQuantity_Title_UpVAlign" colspan="2">
								<TABLE cellpadding="0" cellspacing="0" border="0">
									<TR>
										<TD style="background: #87CEFA;width: 160px;vertical-align:middle;text-align:center;">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.AutoUpdateQuantity.AdditionalOption"/>
										</TD>
										<TD style="width:300px;word-wrap: break-word;white-space : normal;vertical-align:middle;">
											<bean:write property="additionalOptionPlanName" name="B_SSM_S02_Page_Form" />
										</TD>
										<TD style="padding-left:10px;width:300px;word-wrap: break-word;white-space : normal;vertical-align:middle;">
											<bean:write property="additionalOptionPlanGrpName" name="B_SSM_S02_Page_Form" />
										</TD>
										<TD style="padding-left:10px;width:150px;word-wrap: break-word;white-space : normal;vertical-align:middle;">
											<t:writeCodeValue codeList="LIST_PLANSTATUS" name="B_SSM_S02_Page_Form" property="additionalOptionServiceStatus"/>
										</TD>
									</TR>
									<TR>
										<TD style="background: #87CEFA;width: 160px;vertical-align:top;text-align:center;">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.AutoUpdateQuantity.BaseQty"/>
										</TD>
										<TD colspan="3" style="vertical-align:top;">
											<bean:write property="baseQty" name="B_SSM_S02_Page_Form" />
										</TD>
									</TR>
								</TABLE>
								<html:hidden property="mailAccount" name="B_SSM_S02_Page_Form"/>
								<html:hidden property="baseQty" name="B_SSM_S02_Page_Form"/>
								<html:hidden property="additionalOption" name="B_SSM_S02_Page_Form"/>
							</TD>
						</TR>
						<!-- MailBox Qty -->
						<TR>
							<!-- Auto MailBox Qty -->
							<TD class="B_SSM_S02_Page_Form_Table_Col_CheckBox">
								<logic:equal value="1" name="B_SSM_S02_Page_Form" property="autoMailBoxQty">	
									<img border="0" src="../image/checked.jpg" alt="Checked" />
								</logic:equal>
								<html:checkbox property="autoMailBoxQty"
											   styleId="autoMailBoxQtyID"
											   onclick="doCheckMailBoxQty(this);"
											   name="B_SSM_S02_Page_Form" 
											   value="1" style="display:none;">
								</html:checkbox>
							</TD>
							<!-- MailBox Qty Labels -->
							<TD colspan="2">
								<TABLE cellpadding="0" cellspacing="0" border="0">
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_Col_AutoUpdateQuantity_Title" style="vertical-align:top;text-align:center;">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.AutoUpdateQuantity.MailBoxQty"/>
										</TD>
										<TD style="width:300px;word-wrap: break-word;white-space : normal;vertical-align:top;">
											<bean:write property="mailBoxQtyPlanName" name="B_SSM_S02_Page_Form" />
										</TD>
										<TD style="padding-left:10px;width:300px;word-wrap: break-word;white-space : normal;vertical-align:top;">
											<bean:write property="mailBoxQtyPlanGrpName" name="B_SSM_S02_Page_Form" />
										</TD>
										<TD style="padding-left:10px;width:150px;word-wrap: break-word;white-space : normal;vertical-align:top;">
											<t:writeCodeValue codeList="LIST_PLANSTATUS" name="B_SSM_S02_Page_Form" property="mailBoxQtyServiceStatus"/>
										</TD>
									</TR>
								</TABLE>
								<html:hidden property="mailBoxQty" name="B_SSM_S02_Page_Form"/>
							</TD>
						</TR>
						<!-- Virus Scan -->
						<TR>
							<!-- Auto Virus Scan -->
							<TD class="B_SSM_S02_Page_Form_Table_Col_CheckBox">
								<logic:equal value="1" name="B_SSM_S02_Page_Form" property="autoVirusScan">	
									<img border="0" src="../image/checked.jpg" alt="Checked" />
								</logic:equal>
								<html:checkbox property="autoVirusScan"
											   styleId="autoVirusScanID"
											   name="B_SSM_S02_Page_Form"
											   onclick="doCheckAutoVirusScan(this);" 
											   value="1" style="display:none;">
								</html:checkbox>
							</TD>
							<!-- Virus Scan Labels -->
							<TD colspan="2">
								<TABLE cellpadding="0" cellspacing="0" border="0">
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_Col_AutoUpdateQuantity_Title" style="vertical-align:top;text-align:center;">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.AutoUpdateQuantity.VirusScan"/>
										</TD>
										<TD style="width:300px;word-wrap: break-word;white-space : normal;vertical-align:top;">
											<bean:write property="virusScanPlanName" name="B_SSM_S02_Page_Form" />
										</TD>
										<TD style="padding-left:10px;width:300px;word-wrap: break-word;white-space : normal;vertical-align:top;">
											<bean:write property="virusScanPlanGrpName" name="B_SSM_S02_Page_Form" />
										</TD>
										<TD style="padding-left:10px;width:150px;word-wrap: break-word;white-space : normal;vertical-align:top;">
											<t:writeCodeValue codeList="LIST_PLANSTATUS" name="B_SSM_S02_Page_Form" property="virusScanServiceStatus"/>
										</TD>
									</TR>
								</TABLE>
								<html:hidden property="optionVirusScan" name="B_SSM_S02_Page_Form"/>								
							</TD>
						</TR>
						<!-- Anti Spam -->
						<TR>
							<!-- Auto Anti Spam -->
							<TD class="B_SSM_S02_Page_Form_Table_Col_CheckBox">
								<logic:equal value="1" name="B_SSM_S02_Page_Form" property="autoAntiSpam">	
									<img border="0" src="../image/checked.jpg" alt="Checked" />
								</logic:equal>
								<html:checkbox property="autoAntiSpam"
											   styleId="autoAntiSpamID"
											   onclick="doCheckAutoAntiSpam(this);" 
											   name="B_SSM_S02_Page_Form" 
											   value="1" style="display:none;">
								</html:checkbox>
							</TD>
							<!-- Anti Spam Labels -->
							<TD colspan="2">
								<TABLE cellpadding="0" cellspacing="0" border="0">
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_Col_AutoUpdateQuantity_Title" style="vertical-align:top;text-align:center;">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.AutoUpdateQuantity.AntiSpam"/>
										</TD>
										<TD style="width:300px;word-wrap: break-word;white-space : normal;vertical-align:top;">
											<bean:write property="antiSpamPlanName" name="B_SSM_S02_Page_Form" />
										</TD>
										<TD style="padding-left:10px;width:300px;word-wrap: break-word;white-space : normal;vertical-align:top;">
											<bean:write property="antiSpamPlanGrpName" name="B_SSM_S02_Page_Form" />
										</TD>
										<TD style="padding-left:10px;width:150px;word-wrap: break-word;white-space : normal;vertical-align:top;">
											<t:writeCodeValue codeList="LIST_PLANSTATUS" name="B_SSM_S02_Page_Form" property="antiSpamServiceStatus"/>
										</TD>
									</TR>
								</TABLE>
								<html:hidden property="optionAntiSpam" name="B_SSM_S02_Page_Form"/>								
							</TD>
						</TR>
						<!-- Junk Management -->
						<TR>
							<!-- Auto Junk Management -->
							<TD class="B_SSM_S02_Page_Form_Table_Col_CheckBox">
								<logic:equal value="1" name="B_SSM_S02_Page_Form" property="autoJunkManagement">	
									<img border="0" src="../image/checked.jpg" alt="Checked" />
								</logic:equal>
								<html:checkbox property="autoJunkManagement"
											   styleId="autoJunkManagementID"
											   onclick="doCheckAutoJunkManagement(this);" 
											   name="B_SSM_S02_Page_Form" 
											   value="1" style="display:none;">
								</html:checkbox>
							</TD>
							<!-- Junk Management Labels -->
							<TD colspan="2">
								<TABLE cellpadding="0" cellspacing="0" border="0">
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_Col_AutoUpdateQuantity_Title" style="vertical-align:top;text-align:center;">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.AutoUpdateQuantity.JunkManagement"/>
										</TD>
										<TD style="width:300px;word-wrap: break-word;white-space : normal;vertical-align:top;">
											<bean:write property="junkManagementPlanName" name="B_SSM_S02_Page_Form" />
										</TD>
										<TD style="padding-left:10px;width:300px;word-wrap: break-word;white-space : normal;vertical-align:top;">
											<bean:write property="junkManagementPlanGrpName" name="B_SSM_S02_Page_Form" />
										</TD>
										<TD style="padding-left:10px;width:150px;word-wrap: break-word;white-space : normal;vertical-align:top;">
											<t:writeCodeValue codeList="LIST_PLANSTATUS" name="B_SSM_S02_Page_Form" property="junkManagementServiceStatus"/>
										</TD>
									</TR>
								</TABLE>
								<html:hidden property="optionJunkManagement" name="B_SSM_S02_Page_Form"/>									
							</TD>
						</TR>
					</TABLE>	
				</div>
			</fieldset>
		</div>	
		
		<!-- Mail Accounts Group -->
		<div style='<%=(infoIDArray != null && infoIDArray.contains(17))? "display:block;":"display:none;"%>' 
			 class="B_SSM_S02_Page_Form_Container_FieldSet">
			<div style="overflow: hidden;">					
				<span id="info.ERR4SC016" style="display: none;"><bean:message key="info.ERR4SC016" arg0="ACCOUNTNAME"/></span>
					<TABLE id="mailAccountsGroupTableID" class="B_SSM_S02_Page_Form_Table_Detail">
						<!-- Mail Account Headers -->
						<TR class="B_SSM_S02_Page_Form_Table_Row_Header">	
							<!-- No. -->
							<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
								<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.MailAccounts.No"/>
								<br/>
								<html:link href="javascript:void(0);"
										   onclick="var insRowPos = getTableRowCount('mailAccountsGroupTableID') - 1 ;												   			
										   			addComponentsToTable('mailAccountsGroupTableID',
																	      insRowPos,						    
																	      getMailAccountTableComponentPositionArray(),
																	      getNewMailAccountComponentValueArray());
													updateMailAccountsNoColValues();
													updateMailAccountRemoveLinkColValues();
													updateMailAccountTotals();">
									<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.MailAccounts.AddLink"/>
								</html:link>
							</TH>
							<!-- Mail Account -->
							<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
								<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.MailAccounts.MailAccount"/>
							</TH>
							<!-- Mail Password -->
							<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
								<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.MailAccounts.MailPassword"/>
							</TH>
							<!-- Pop Account -->
							<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
								<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.MailAccounts.POPAccount"/>
							</TH>									
							<!-- Add MailBox (Qty) -->
							<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
								<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.MailAccounts.AddMailBoxQty"/>
							</TH>
							<!-- Mail Box (MB) -->
							<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
								<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.MailAccounts.MailBox_MB"/>
							</TH>
							<!-- Virus Scan -->
							<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
								<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.MailAccounts.VirusScan"/>
							</TH>
							<!-- Anti Spam -->
							<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
								<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.MailAccounts.AntiSpam"/>
							</TH>
							<!-- Junk Management -->
							<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
								<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.MailAccounts.JunkManagement"/>
							</TH>
							<!-- Added Date -->
							<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
								<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.MailAccounts.AddedDate"/>
							</TH>
							<!-- Printed Date -->
							<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
								<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.MailAccounts.PrintedDate"/>
							</TH>
							<!-- X Button -->
							<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
								
							</TH>
						</TR>
						<!-- Check All Headers -->
						<TR class="B_SSM_S02_Page_Form_Table_Row_Header">
							<!-- No. -->
							<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
								
							</TH>
							<!-- Mail Account -->
							<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
								
							</TH>
							<!-- Mail Password -->
							<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
								
							</TH>
							<!-- Pop Account -->
							<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
								
							</TH>																		
							<!-- Mail Box (MB) -->
							<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
								
							</TH>
							<!-- Add MailBox (Qty) -->
							<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
								
							</TH>
							<!-- Check All Virus Scan -->
							<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
								<html:checkbox property="checkAllVirusScan"
											   styleId="checkAllVirusScanID"
											   onclick="checkAllMailVirusScan();"
											   name="B_SSM_S02_Page_Form">
								</html:checkbox>
							</TH>
							<!-- Check All Anti Spam -->
							<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
								<html:checkbox property="checkAllAntiSpam"
											   styleId="checkAllAntiSpamID"
											   onclick="checkAllMailAntiSpam();"
											   name="B_SSM_S02_Page_Form">
								</html:checkbox>
							</TH>
							<!-- Check All Junk Management -->
							<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
								<html:checkbox property="checkAllJunkManagement"
											   styleId="checkAllJunkManagementID"
											   onclick="checkAllMailJunkManagement();"
											   name="B_SSM_S02_Page_Form">
								</html:checkbox>
							</TH>
							<!-- Added Date -->
							<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
								
							</TH>
							<!-- Printed Date -->
							<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
								
							</TH>
							<!-- X Button -->
							<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
								
							</TH>
						</TR>
						<!-- Mail Account Content -->							
						<logic:notEmpty name="B_SSM_S02_Page_Form" property="mailAccountList" >
							<logic:iterate id="mailAccount" 
										   indexId="index" 
										   name="B_SSM_S02_Page_Form"
										   property="mailAccountList">
								<TR style="text-align: left;">	
									<!-- No. -->
									<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
										<%=index.intValue() + 1 %>
										<input type="text" 
											   name="mailAccountIDs" 
											   style="display: none;"
											   value="${mailAccount['mailAccountID']}"/>		
													
									</TD>
									<!-- Mail Account -->
									<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
									    <textarea class="B_SSM_S02_Page_Form_TextBox_MailAccount mailAccountNameClass"
													  name="mailAccountNames"
													  onkeypress="return checkLengthNotInputEntry(event, this, 50)"
													  onchange="customizeElementValueLength(this, 50);" rows="2"
													  style= "overflow-x:hidden; overflow-y:auto;width:175px;">
										   ${mailAccount['mailAccountName']}
									    </textarea>	
									</TD>
									<!-- Mail Password -->
									<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
										<textarea name="mailAccountPasswords"
													  onkeypress="return checkLengthNotInputEntry(event, this, 30)"
													  onchange="customizeElementValueLength(this, 30);" rows="2"
													  style= "overflow-x:hidden; overflow-y:auto;width:150px;">
										   ${mailAccount['mailAccountPassword']}
									    </textarea>
									</TD>
									<!-- Pop Account -->
									<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
										<textarea class="B_SSM_S02_Page_Form_TextBox_MailAccount"
													  name="mailAccountPOPServerNames"
													  onkeypress="return checkLengthNotInputEntry(event, this, 50)"
													  onchange="customizeElementValueLength(this, 50);" rows="2"
													  style= "overflow-x:hidden; overflow-y:auto;width:175px;">
										   ${mailAccount['mailPopAccountName']}
									    </textarea>
									</TD>
									<!-- Add MailBox (Qty) -->
									<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
										<input type="text" 
											   name="mailAccountAddMailBoxSizes" 	
											   maxlength="10"												   
											   class="B_SSM_S02_Page_Form_TextBox_MailBox"
											   value="${mailAccount['mailAccountAddBoxSize']}"
											   onchange="valueOfInvalidNumberWithElement(this, 0);
											   			calculateMailBoxSize(${index + 2});"
											   onkeypress="return isValidNumberOnKeyUpWithElement(this, event, null, null);"/>
			
									</TD>
									<!-- Mail Box (MB) -->
									<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
										<input type="text" 
											   name="mailAccountQtys" 
											   maxlength="10"
											   class="B_SSM_S02_Page_Form_TextBox_MailBox"
											   value="${mailAccount['mailAccountBoxQty']}"
											   onchange="valueOfInvalidNumberWithElement(this, 20);"
											   onkeypress="return isValidNumberOnKeyUpWithElement(this, event, null, null);"/>	
									</TD>											
									<!-- Check Virus Scan -->
									<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
										<script type="text/javascript">
											virusScanComponentID = <%=index.intValue()%>;
										</script>												
										<logic:equal value="1" name="mailAccount" property="mailAccountCheckVirusScan">																																								
											<input type="checkbox" 
												   name="mailAccountVirusScans" 
												   checked 
												   value="1"
												   onclick='updateMailAccountTotals();
												   			<%= "setTextForCheckBox(this," + 
													   			"\"mailAccountVirusScanText" + 
													   			index.toString() + "\");"
													   		%>'/>
											<input type="text" 
											 	   name="mailAccountVirusScanTexts"  
											 	   value="1"
				    							   id="mailAccountVirusScanText<%=index.intValue() %>"
				    						       style="display: none;"/>							    											    							   	
										</logic:equal>	
										<logic:notEqual value="1" name="mailAccount" property="mailAccountCheckVirusScan">														
											<input type="checkbox" 
												   name="mailAccountVirusScans" 														   
												   value="1"
												   onclick='updateMailAccountTotals();
												   			<%= "setTextForCheckBox(this," + 
													   			"\"mailAccountVirusScanText" + 
													   			index.toString() + "\");"
													   		%>'/>
											<input type="text" 
											 	   name="mailAccountVirusScanTexts"  
				    							   id="mailAccountVirusScanText<%=index.intValue() %>"
				    							   value="0"
				    						       style="display: none;"/>							    							
										</logic:notEqual>
										<script type="text/javascript">
											virusScanComponentID++;	
										</script>	
									</TD>
									<!-- Check Anti Spam -->
									<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
										<script type="text/javascript">
											antiSpamComponentID = <%=index.intValue()%>;	
										</script>																																
										<logic:equal value="1" name="mailAccount" property="mailAccountCheckAntiSpam">	
											<input  type="checkbox" 
													name="mailAccountAntiSpams" 
													checked 
													value="1"
													onclick='updateMailAccountTotals();
												   			<%= "setTextForCheckBox(this," + 
													   			"\"mailAccountAntiSpamText" + 
													   			index.toString() + "\");"
													   		%>'/>
											<input type="text" 
											 	   name="mailAccountAntiSpamTexts"  
											 	   value="1"
				    							   id="mailAccountAntiSpamText<%=index.intValue() %>"
				    						       style="display: none;"/>	
													   		
										</logic:equal>	
										<logic:notEqual value="1" name="mailAccount" property="mailAccountCheckAntiSpam">	
											<input  type="checkbox" 
													name="mailAccountAntiSpams" 
													value="1"
													onclick='updateMailAccountTotals();
												   			<%= "setTextForCheckBox(this," + 
													   			"\"mailAccountAntiSpamText" + 
													   			index.toString() + "\");"
													   		%>'/>
											<input type="text" 
											 	   name="mailAccountAntiSpamTexts"  
											 	   value="0"
				    							   id="mailAccountAntiSpamText<%=index.intValue() %>"
				    						       style="display: none;"/>	
														   		 
										</logic:notEqual>	
										<script type="text/javascript">
											antiSpamComponentID++;	
										</script>										
									</TD>
									<!-- Check Junk Management -->
									<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
										<script type="text/javascript">
											junkManagementComponentID = <%=index.intValue()%>;	
										</script>																				
										<logic:equal value="1" name="mailAccount" property="mailAccountCheckJunkManagement">	
											<input type="checkbox" 
												   name="mailAccountJunkManagements" 
												   checked 
												   value="1"
												   onclick='updateMailAccountTotals();
												   			<%= "setTextForCheckBox(this," + 
													   			"\"mailAccountJunkManagementText" + 
													   			index.toString() + "\");"
													   		%>'/>
											<input type="text" 
											 	   name="mailAccountJunkManagementTexts"  
											 	   value="1"
				    							   id="mailAccountJunkManagementText<%=index.intValue() %>"
				    						       style="display: none;"/>	
										      		
										</logic:equal>	
										<logic:notEqual value="1" name="mailAccount" property="mailAccountCheckJunkManagement">	
											<input  type="checkbox" 
												    name="mailAccountJunkManagements"
												    value="1"
												    onclick='updateMailAccountTotals();
												   			<%= "setTextForCheckBox(this," + 
													   			"\"mailAccountJunkManagementText" + 
													   			index.toString() + "\");"
													   		%>'/>
											<input type="text" 
											 	   name="mailAccountJunkManagementTexts"  
											 	   value="0"
				    							   id="mailAccountJunkManagementText<%=index.intValue() %>"
				    						       style="display: none;"/>	
											 		   		
										</logic:notEqual>	
										<script type="text/javascript">
											junkManagementComponentID++;	
										</script>	
									</TD>
									<!-- Added Date -->
									<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
										<bean:write name="mailAccount" property="mailAccountAddedDate"/>		
									</TD>
									<!-- Printed Date -->
									<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
										<bean:write name="mailAccount" property="mailAccountPrintedDate"/>			
									</TD>
									<!-- X Button -->
									<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
										<a href='javascript:doAccountMailsRowRemove(<%=index.intValue() + 2%>, this)'
			        					   class="B_SSM_S02_Page_Form_AccountMailsGroup_Href_RemoveLink">         						
			        						<span style="font-weight: bold; font-size: 15px; color: black;">X</span> 
			        					</a> 							
									</TD>
								</TR>
							</logic:iterate>
						</logic:notEmpty>
						
					<!-- Mail Account Footer -->
					<TR class="B_SSM_S02_Page_Form_Table_Row_Header">
						<!-- Total Title -->
						<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">										
							<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.MailAccounts.Total"/>					
						</TH>	
						<!-- Mail Account -->
						<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
							<bean:write name="B_SSM_S02_Page_Form" property="mailAccountTotal"/>
						</TH>
						<!-- Mail Password -->
						<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
							
						</TH>
						<!-- Pop Account -->
						<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
							
						</TH>								
						<!-- Total Add MailBox (Qty) -->
						<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
							<bean:write name="B_SSM_S02_Page_Form" property="mailAccountMailBoxQtyTotal"/>			
						</TH>
						<!-- Total Mail Box (MB) -->
						<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
							<bean:write name="B_SSM_S02_Page_Form" property="mailAccountAddBoxSizeTotal"/>		
						</TH>
						<!-- Total Virus Scan -->
						<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
							<bean:write name="B_SSM_S02_Page_Form" property="mailAccountVirusScanTotal"/>		
						</TH>
						<!-- Total Anti Spam -->
						<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
							<bean:write name="B_SSM_S02_Page_Form" property="mailAccountAntiSpamTotal"/>		
						</TH>
						<!-- Total Junk Management -->
						<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
							<bean:write name="B_SSM_S02_Page_Form" property="mailAccountJunkManagementTotal"/>		
						</TH>
						<!-- Added Date -->
						<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
							
						</TH>
						<!-- Printed Date -->
						<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
							
						</TH>
						<!-- X Button -->
						<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
							
						</TH>
					</TR>
					</TABLE>						
			</div>	
		</div>	
		
		<!-- Deleted Mails -->
		<div class="B_SSM_S02_Page_Form_Container_FieldSet">
			<fieldset class="B_SSM_S02_Page_Form_FieldSet">
				<legend class="B_SSM_S02_Page_Form_FieldSet_Legend">
					<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.DeletedEmail.Legend" />			  						
				</legend>
				<div class="B_SSM_S02_Page_Form_FieldSet_PanelContent">
					<TABLE id="mailAccountsDeletedGroupTableID" class="B_SSM_S02_Page_Form_Table_Detail">
						<!-- Deleted Mails Headers -->
						<TR>
							<!-- No. -->
							<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
								<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.DeletedMails.No"/>	
							</TH>
							<!-- Mail Account -->
							<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
								<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.DeletedMails.MailAccount"/>	
							</TH>
							<!-- Deleted Date -->
							<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
								<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.DeletedMails.DeletedDate"/>	
							</TH>
							<!-- Printed Date -->
							<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
								<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.DeletedMails.PrintedDate"/>	
							</TH>
							<!-- X Button -->
							<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
									
							</TH>
						</TR>
						<!-- Deleted Mails Rows -->
						<logic:notEmpty name="B_SSM_S02_Page_Form" property="mailAccountDeletedList" >
							<logic:iterate id="mailAccount" 
										   indexId="index" 
										   property="mailAccountDeletedList"
										   name="B_SSM_S02_Page_Form">
								<TR style="text-align: left;">
									<!-- No. -->
									<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
										<%=index.intValue() + 1 %>
										<input type="text" 
											   name="mailAccountDeletedIDs" 
											   style="display: none;"
											   value="${mailAccount['mailAccountID']}"/>		
									</TD>
									<!-- Mail Account -->
									<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
										<bean:write name="mailAccount" property="mailAccountDeleted"/>	
									</TD>
									<!-- Deleted Date -->
									<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
										<bean:write name="mailAccount" property="mailAccountDeletedDate"/>		
									</TD>
									<!-- Printed Date -->
									<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
										<bean:write name="mailAccount" property="mailAccountPrintedDate"/>		
									</TD>
									<!-- X Button -->
									<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
										<a href="javascript:void(0);" onclick="doAccountMailsDeletedRowRemove(<%=index.intValue() + 1 %>)"
			        					   class="B_SSM_S02_Page_Form_AccountMailsGroup_Href_RemoveLink">         						
			        						<span style="font-weight: bold; font-size: 15px; color: black;">X</span> 
			        					</a>		
									</TD>
								</TR>
							</logic:iterate>
						</logic:notEmpty>
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
								<textarea name="mailAddressMemoRemarks"
									      id="mailAddressMemoRemarksID"
									      rows="5" cols="60">
									<bean:write name="B_SSM_S02_Page_Form" property="mailAddressMemoRemarks"/>
								</textarea>			   
							</div>
						</TD>
					</TR>
				</TABLE>
			</div>
		</logic:equal>
		<logic:notEqual name="B_SSM_S02_Page_Form" property="displayMemoFlg" value="1">
			<textarea name="mailAddressMemoRemarks"
				      id="mailAddressMemoRemarksID"
				      rows="5" cols="60" style="display:none;">
				<bean:write name="B_SSM_S02_Page_Form" property="mailAddressMemoRemarks"/>
			</textarea>	
		</logic:notEqual>
	</div>
</body>
</html>