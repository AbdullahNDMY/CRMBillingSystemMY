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
	<t:defineCodeList id="LIST_PLANSTATUS"/>
	<bean:define id="infoIDArray" name="B_SSM_S02_Page_Form" property="infoIDArray" type="java.util.List<Integer>"/>
	<% if (B_SSM_S02e_BUtils.isAddressTabEnable(infoIDArray)) { %>
		<div id="mailAddressTab" class="B_SSM_S02_Page_Form_Tabs_TabPanel">
			<!-- Mail Server Information Group -->
			<%if (infoIDArray != null && infoIDArray.contains(16)) { %>
			<div class="B_SSM_S02_Page_Form_Container_FieldSet">
				<fieldset class="B_SSM_S02_Page_Form_FieldSet">
					<legend class="B_SSM_S02_Page_Form_FieldSet_Legend">
						<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.MailServerInformation.Legend" />			  						
					</legend>
					<div class="B_SSM_S02_Page_Form_FieldSet_PanelContent">
					   <TABLE class="B_SSM_S02_Page_Form_Table">
                            <TR>
                                <TD class="B_SSM_S02_Page_Form_Table_Col">
                                    <TABLE width="100%" class="textAlignTop">
					                    <col width="39%">
                                        <col width="1%">
                                        <col width="60%">
	                                    <!-- Primary Domain Name Server -->
		                                <TR>
			                                <TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol" style="width:auto">
			                                    <bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.MailServerInformation.PrimaryDomainNameServer.Text"/>
			                                </TD>
			                                <TD>
			                                    :&nbsp;
			                                </TD>
			                                <TD>
			                                    <bean:write name="B_SSM_S02_Page_Form" property="primaryDomainNameServer"/>
			                                </TD>
			                            </TR>
		                             </TABLE>
                                </TD>
	                            <TD class="B_SSM_S02_Page_Form_Table_Col">
	                                   <TABLE width="100%" class="textAlignTop">
                                        <col width="49%">
                                        <col width="1%">
                                        <col width="50%">
                                        <!-- Secondary Domain Name Server -->
                                        <TR>
                                            <TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol" style="width:auto">
                                                <bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.MailServerInformation.SecondaryDomainNameServer.Text"/>
                                            </TD>
                                            <TD>
                                                :&nbsp;
                                            </TD>
                                            <TD>
                                                <bean:write name="B_SSM_S02_Page_Form" property="secondaryDomainNameServer"/>
                                            </TD>
                                        </TR>
                                        </TABLE>
	                            </TD>
                            </TR>
					   </TABLE>
					</div>
					<div class="B_SSM_S02_Page_Form_FieldSet_PanelContent">
						<TABLE class="B_SSM_S02_Page_Form_Table">
							<TR>
								<TD class="B_SSM_S02_Page_Form_Table_Col">
									<TABLE width="100%" class="textAlignTop">
										<col width="39%">
										<col width="1%">
										<col width="60%">
										<!-- Domain Name -->
										<TR>
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.MailServerInformation.DomainName.Text"/>
											</TD>
											<TD>
												:&nbsp;
											</TD>
											<TD>
												<bean:write name="B_SSM_S02_Page_Form" 															   
														    property="domainName"/>
											</TD>
										</TR>
										<!-- SMTP Server Name -->
										<TR>
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol" style="vertical-align: top">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.MailServerInformation.SMTPServerName.Text"/>
											</TD>
											<TD>
												:&nbsp;
											</TD>
											<TD style="width:270px;word-wrap: break-word;white-space : normal">
												<bean:write name="B_SSM_S02_Page_Form" 															   
														    property="sMTPServerName"/>
											</TD>
										</TR>
									</TABLE>
								</TD>	
								
								<TD class="B_SSM_S02_Page_Form_Table_Col">
									<TABLE width="100%" class="textAlignTop">
										<col width="39%">
										<col width="1%">
										<col width="60%">
										<!-- WebMail URL -->
										<TR>
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol" style="vertical-align: top">
                                                <bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.MailServerInformation.WebMailURL.Text"/>
                                            </TD>
                                            <TD>
                                                :&nbsp;
                                            </TD>
                                            <TD style="width:270px;word-wrap: break-word;white-space : normal">
                                                <bean:write name="B_SSM_S02_Page_Form" property="webmailURL"/>
                                            </TD>
										</TR>
										<!-- POP Server Name -->
										<TR>
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol" style="vertical-align: top">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.MailServerInformation.POPServerName.Text"/>
											</TD>
											<TD>
												:&nbsp;
											</TD>
											<TD style="width:270px;word-wrap: break-word;white-space : normal">
												<bean:write name="B_SSM_S02_Page_Form" 															  
														    property="pOPServerName"/>
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
									<%--
									<html:checkbox property="autoMailAcc"
												   styleId="autoMailAccID"
												   name="B_SSM_S02_Page_Form" 
												   disabled="true"
												   value="1">
									</html:checkbox>
									--%>
									<logic:equal value="1" name="B_SSM_S02_Page_Form" property="autoMailAcc">	
										<img border="0" src="../image/checked.jpg" alt="Checked" />
									</logic:equal>
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
								</TD>
							</TR>
							<!-- MailBox Qty -->
							<TR>
								<!-- Auto MailBox Qty -->
								<TD class="B_SSM_S02_Page_Form_Table_Col_CheckBox">
									<%-- 
									<html:checkbox property="autoMailBoxQty"
												   styleId="autoMailBoxQtyID"
												   name="B_SSM_S02_Page_Form" 
												   disabled="true"
												   value="1">
									</html:checkbox>
									--%>
									<logic:equal value="1" name="B_SSM_S02_Page_Form" property="autoMailBoxQty">	
										<img border="0" src="../image/checked.jpg" alt="Checked" />
									</logic:equal>
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
								</TD>
							</TR>
							<!-- Virus Scan -->
							<TR>
								<!-- Auto Virus Scan -->
								<TD class="B_SSM_S02_Page_Form_Table_Col_CheckBox">
									<%-- 
									<html:checkbox property="autoVirusScan"
												   styleId="autoVirusScanID"
												   name="B_SSM_S02_Page_Form" 
												   disabled="true"
												   value="1">
									</html:checkbox>
									--%>
									<logic:equal value="1" name="B_SSM_S02_Page_Form" property="autoVirusScan">	
										<img border="0" src="../image/checked.jpg" alt="Checked" />
									</logic:equal>
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
								</TD>
							</TR>
							<!-- Anti Spam -->
							<TR>
								<!-- Auto Anti Spam -->
								<TD class="B_SSM_S02_Page_Form_Table_Col_CheckBox">
									<%-- 
									<html:checkbox property="autoAntiSpam"
												   styleId="autoAntiSpamID"
												   name="B_SSM_S02_Page_Form"
												   disabled="true" 
												   value="1">
									</html:checkbox>
									--%>
									<logic:equal value="1" name="B_SSM_S02_Page_Form" property="autoAntiSpam">	
										<img border="0" src="../image/checked.jpg" alt="Checked" />
									</logic:equal>
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
								</TD>
							</TR>
							<!-- Junk Management -->
							<TR>
								<!-- Auto Junk Management -->
								<TD class="B_SSM_S02_Page_Form_Table_Col_CheckBox">
									<%-- 
									<html:checkbox property="autoJunkManagement"
												   styleId="autoJunkManagementID"
												   name="B_SSM_S02_Page_Form" 
												   disabled="true"
												   value="1">
									</html:checkbox>
									--%>
									<logic:equal value="1" name="B_SSM_S02_Page_Form" property="autoJunkManagement">	
										<img border="0" src="../image/checked.jpg" alt="Checked" />
									</logic:equal>
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
								</TD>
							</TR>
						</TABLE>	
					</div>
				</fieldset>
			</div>	
			
			<!-- Mail Accounts Group -->
			<%if (infoIDArray != null && infoIDArray.contains(17)) { %>
			<div class="B_SSM_S02_Page_Form_Container_FieldSet">
				<div style="overflow: hidden;">											
					<TABLE id="mailAccountsGroupTableID" class="B_SSM_S02_Page_Form_Table_Detail">
						<!-- Mail Account Headers -->
						<TR class="B_SSM_S02_Page_Form_Table_Row_Header">
							<!-- Add Link -->
							<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
								
							</TH>	
							<!-- No. -->
							<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
								<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.MailAccounts.No"/>
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
						</TR>
						<!-- Check All Headers -->
						<TR class="B_SSM_S02_Page_Form_Table_Row_Header">
							<!-- Add Link -->
							<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
								<input type="checkbox" 
									   id='checkAllMailAccountsID' 
									   onclick="checkAllMailAccounts();"/>		
							</TH>	
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
							<!-- Add MailBox (Qty) -->
							<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
								
							</TH>
							<!-- Mail Box (MB) -->
							<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
								
							</TH>
							<!-- Check All Virus Scan -->
							<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
								
							</TH>
							<!-- Check All Anti Spam -->
							<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
								
							</TH>
							<!-- Check All Junk Management -->
							<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
								
							</TH>
							<!-- Added Date -->
							<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
								
							</TH>
							<!-- Printed Date -->
							<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
								
							</TH>
						</TR>
						<!-- Mail Account Content -->							
						<logic:notEmpty name="B_SSM_S02_Page_Form" property="mailAccountList" >
							<logic:iterate id="mailAccount" 
										   indexId="index" 
										   name="B_SSM_S02_Page_Form"
										   property="mailAccountList">
								<TR style="text-align: left">
									<!-- Add Link -->
									<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
										<input type="checkbox" 
											   id='checkMailID<%=index.intValue() %>'
											   value="1"
											   onclick="setTextField('checkMailStatusID<%=index.intValue() %>', this.checked?1:0)"/>	
										<input type="text" 
										  	   style="display: none;" 
											   name="selectedCheckMails" 
											   id="checkMailStatusID<%=index.intValue() %>" 											  
											   onclick=''/>							
									</TD>	
									<!-- No. -->
									<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
										<%=index.intValue() + 1 %>		
										<input type="text" 
											   name="mailAccountIDs" 
											   style="display: none;"
											   value="${mailAccount['mailAccountID']}"/>			
									</TD>
									<!-- Mail Account -->
									<TD class="B_SSM_S02_Page_Form_Table_Col_Detail" style="width:170px;word-wrap: break-word;white-space : normal">
										<bean:write name="mailAccount" property="mailAccountName"/>	
									</TD>
									<!-- Mail Password -->
									<TD class="B_SSM_S02_Page_Form_Table_Col_Detail" style="width:170px;word-wrap: break-word;white-space : normal">
										<bean:write name="mailAccount" property="mailAccountPassword"/>		
									</TD>
									<!-- Pop Account -->
									<TD class="B_SSM_S02_Page_Form_Table_Col_Detail" style="width:170px;word-wrap: break-word;white-space : normal">
										<bean:write name="mailAccount" property="mailPopAccountName"/>		
									</TD>										
									<!-- Add MailBox (Qty) -->
									<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
										<bean:write name="mailAccount" property="mailAccountAddBoxSize"/>		
									</TD>
									<!-- Mail Box (MB) -->
									<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
										<bean:write name="mailAccount" property="mailAccountBoxQty"/>		
									</TD>
									<!-- Check Virus Scan -->
									<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
										<logic:equal value="1" name="mailAccount" property="mailAccountCheckVirusScan">	
											<img border="0" src="../image/checked.jpg" alt="Checked" />
										</logic:equal>	
									</TD>
									<!-- Check Anti Spam -->
									<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">								
										<logic:equal value="1" name="mailAccount" property="mailAccountCheckAntiSpam">	
											<img border="0" src="../image/checked.jpg" alt="Checked" />
										</logic:equal>											
									</TD>
									<!-- Check Junk Management -->
									<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
										<logic:equal value="1" name="mailAccount" property="mailAccountCheckJunkManagement">	
											<img border="0" src="../image/checked.jpg" alt="Checked" />
										</logic:equal>		
									</TD>
									<!-- Added Date -->
									<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
										<bean:write name="mailAccount" property="mailAccountAddedDate"/>	
									</TD>
									<!-- Printed Date -->
									<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
										<bean:write name="mailAccount" property="mailAccountPrintedDate"/>		
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
							<!-- Total -->
							<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
								<bean:write name="B_SSM_S02_Page_Form" property="mailAccountTotal"/>	
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
								&nbsp;
							</TH>
							<!-- Printed Date -->
							<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
								&nbsp;
							</TH>
						</TR>
					</TABLE>						
				</div>	
			</div>	
			<%}%>

			<!-- Deleted Mails -->
			<div class="B_SSM_S02_Page_Form_Container_FieldSet">
				<fieldset class="B_SSM_S02_Page_Form_FieldSet">
					<legend class="B_SSM_S02_Page_Form_FieldSet_Legend">
						<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.DeletedEmail.Legend" />			  						
					</legend>
					<div class="B_SSM_S02_Page_Form_FieldSet_PanelContent">
						<TABLE class="B_SSM_S02_Page_Form_Table_Detail" id="mailAccountsDeletedGroupTableID">
							<!-- Deleted Mails Headers -->
							<TR>
								<!-- Check box -->
								<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">										
									<input type="checkbox"
									       id="checkAllMailAccountsDeletedID" 
									       onclick="checkAllMailAccountsDeleted();"/>									
								</TH>
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
							</TR>
							<!-- Deleted Mails Rows -->
							<logic:notEmpty name="B_SSM_S02_Page_Form" property="mailAccountDeletedList" >
								<logic:iterate id="mailAccount" 
											   indexId="index" 
											   property="mailAccountDeletedList"
											   name="B_SSM_S02_Page_Form">
									<TR style="text-align: left">								
										<!-- Check box -->
										<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
											<input type="checkbox" 
												   id='checkDeletedMailID<%=index.intValue() %>'
												   value="1"
												   onclick="setTextField('checkDeletedMailStatusID<%=index.intValue() %>', this.checked?1:0)"/>	
											<input type="text" 
											  	   style="display: none;" 
												   name="selectedCheckDeletedMails" 
												   id="checkDeletedMailStatusID<%=index.intValue() %>" 											  
												   onclick=''/>									
										</TD>
										<!-- No. -->
										<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
											<%=index.intValue() + 1 %>	
											<input type="text" 
												   name="mailAccountDeletedIDs" 
												   style="display: none;"
												   value="${mailAccount['mailAccountID']}"/>
										</TD>
										<!-- Mail Account -->
										<TD class="B_SSM_S02_Page_Form_Table_Col_Detail" style="width:350px;word-wrap: break-word;white-space : normal">
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
									</TR>
								</logic:iterate>
							</logic:notEmpty>		
						</TABLE>
					</div>
				</fieldset>
			</div>	
			
			<!-- Print Email Addition/Deletion Letter Button -->
			<logic:equal name="B_SSM_S02_Page_Form" property="accessType" value="2">
			<div class="B_SSM_S02_Page_Form_Buttons">							
			  	<ts:submit property="button" 
			  				 styleClass="B_SSM_S02_Page_Form_Buttons_Text"
			  				 styleId="printEmailAdditionDeletionBtnID"
			  				 onclick="setTextField('processModeField', 6);">	
			  		<bean:message key="B_SSM_S02_Page.Form.printEmailAdditionDeletionLetterBtn.Text"/> 
			  	</ts:submit>	
			</div>	
			</logic:equal>
			
			<logic:equal name="B_SSM_S02_Page_Form" property="accessType" value="1">
			<div class="B_SSM_S02_Page_Form_Buttons">							
			  	<ts:submit property="button" 
			  				 styleClass="B_SSM_S02_Page_Form_Buttons_Text"
			  				 styleId="printEmailAdditionDeletionBtnID"
			  				 onclick="setTextField('processModeField', 6);">	
			  		<bean:message key="B_SSM_S02_Page.Form.printEmailAdditionDeletionLetterBtn.Text"/> 
			  	</ts:submit>	
			</div>	
			</logic:equal>
			
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
													property="mailAddressMemoRemarks"/>
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