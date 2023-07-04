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
	<div id="firewallTab" 
		 style='<%=(B_SSM_S02e_BUtils.isFirewallTabEnable(infoIDArray))? "display:block;":"display:none;"%>' 
		 class="B_SSM_S02_Page_Form_Tabs_TabPanel">
		<!-- Firewall Group -->
		<div class="B_SSM_S02_Page_Form_Container_FieldSet">
			<fieldset class="B_SSM_S02_Page_Form_FieldSet">
				<legend class="B_SSM_S02_Page_Form_FieldSet_Legend">
					<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Firewall.Legend" />			  						
				</legend>
				<div class="B_SSM_S02_Page_Form_FieldSet_PanelContent">
					<TABLE class="B_SSM_S02_Page_Form_Table">
						<TR>
							<TD class="B_SSM_S02_Page_Form_Table_Col">
								<TABLE width="100%">
									<!-- Firewall No -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Firewall.FirewallNo.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<html:text property="firewallNo" name="B_SSM_S02_Page_Form"
												styleId="firewallNoID" styleClass="B_SSM_S02_Page_Form_TextBox" maxlength="100">
											</html:text>
										</TD>
									</TR>
									<!-- Firewall Type -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Firewall.FirewallType.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<t:defineCodeList id="firewallTypeCodeList">
											</t:defineCodeList>	
											<html:select name="B_SSM_S02_Page_Form" 
													     styleClass="B_SSM_S02_Page_Form_TextBox"  
													     styleId="firewallTypeID"
													     property="firewallType">
												<html:option value="">
													<bean:message key="B_SSM_S02_Page.BlankSelectText"/>	
												</html:option>
												<html:optionsCollection name="firewallTypeCodeList" label="name" value="id"/>
											</html:select>		     
										</TD>
									</TR>
									<!-- Firewall Model -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Firewall.FirewallModel.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<t:defineCodeList id="firewallModelCodeList">
											</t:defineCodeList>	
											<html:select name="B_SSM_S02_Page_Form" 
													     styleClass="B_SSM_S02_Page_Form_TextBox"  
													     styleId="firewallModelID"
													     property="firewallModel">
												<html:option value="">
													<bean:message key="B_SSM_S02_Page.BlankSelectText"/>	
												</html:option>
												<html:optionsCollection name="firewallModelCodeList" label="name" value="id"/>
											</html:select>	
										</TD>
									</TR>
									<!-- Interface IP (Trusted) -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Firewall.InterfaceIP_Trusted.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<html:text name="B_SSM_S02_Page_Form" 
													   styleClass="B_SSM_S02_Page_Form_TextBox"  
													   maxlength="40"
													   styleId="firewallInterfaceIP_TrustedID"
													   property="firewallInterfaceIP_Trusted"/>
										</TD>
									</TR>
									<!-- Interface IP (UnTrusted) -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Firewall.InterfaceIP_UnTrusted.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<html:text name="B_SSM_S02_Page_Form" 
													   styleClass="B_SSM_S02_Page_Form_TextBox"  
													   maxlength="40"
													   styleId="firewallInterfaceIP_UnTrustedID"
													   property="firewallInterfaceIP_UnTrusted"/>
										</TD>
									</TR>
									<!-- Serial Number -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Firewall.SerialNumber.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<html:text name="B_SSM_S02_Page_Form" 
													   styleClass="B_SSM_S02_Page_Form_TextBox"  
													   maxlength="100"
													   styleId="firewallSerialNumberID"
													   property="firewallSerialNumber"/>
										</TD>
									</TR>											
									<!-- NAT -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Firewall.NAT.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<html:radio name="B_SSM_S02_Page_Form" 
														styleId="firewallNAT_ONID"
														property="firewallNAT" 
														value="1" >
												<bean:message key="B_SSM_S02_Page.Form.Yes.Text"/>
											 </html:radio>
											 <html:radio name="B_SSM_S02_Page_Form" 
											 			 styleId="firewallNAT_OFFID"
														 property="firewallNAT"
											 			 value="0">
												<bean:message key="B_SSM_S02_Page.Form.No.Text"/>
											 </html:radio>
										</TD>
									</TR>
									<!-- DHCP -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Firewall.DHCP.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<html:radio name="B_SSM_S02_Page_Form" 
														styleId="firewallDHCP_ONID"
														property="firewallDHCP" 
														value="1" >
												<bean:message key="B_SSM_S02_Page.Form.Yes.Text"/>
											 </html:radio>
											 <html:radio name="B_SSM_S02_Page_Form" 
											 			 styleId="firewallDHCP_OFFID"
														 property="firewallDHCP" 
											 			 value="0">
												<bean:message key="B_SSM_S02_Page.Form.No.Text"/>
											 </html:radio>
										</TD>
									</TR>
									<!-- DHCP IP Range -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Firewall.DHCPIPRange.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<html:text name="B_SSM_S02_Page_Form" 
													   styleClass="B_SSM_S02_Page_Form_TextBox"  
													   maxlength="80"
													   styleId="firewallDHCPIPRangeID"
													   property="firewallDHCPIPRange"/>
										</TD>
									</TR>	
									<!-- DNS -->
									<TR>
										<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Firewall.DNS.Text"/>
										</TD>
										<TD>
											:&nbsp;
											<html:text name="B_SSM_S02_Page_Form" 
													   styleClass="B_SSM_S02_Page_Form_TextBox"  
													   maxlength="100"
													   styleId="firewallDNSID"
													   property="firewallDNS"/>
										</TD>
									</TR>	
								</TABLE>
							</TD>	
							
							<TD class="B_SSM_S02_Page_Form_Table_Col">
								<TABLE width="100%">											
									<TR>
										<TD>	
											<!-- Interface IP Group -->																								
											<fieldset class="B_SSM_S02_Page_Form_FieldSet">
												<legend class="B_SSM_S02_Page_Form_FieldSet_Legend">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Firewall.InterfaceIP_Max5.Legend" />			  						
												</legend>
												<div class="B_SSM_S02_Page_Form_FieldSet_PanelContent">
													<TABLE id="firewallInterfaceIPTableID" class="B_SSM_S02_Page_Form_Table_Detail">
														<!-- Firewall Interface IP Headers -->
														<TR class="B_SSM_S02_Page_Form_Table_Row_Header">
															<!-- Add Link -->
															<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
																<html:link href="javascript:void(0);"
																		   styleId="firewallInterfaceAddBtnID"
																		   onclick="insRowPos = getTableRowCount('firewallInterfaceIPTableID');	
																		   			if (insRowPos <= 5) {											   			
																			   			addComponentsToTable('firewallInterfaceIPTableID',
																										      insRowPos,						    
																										      getFirewallInterfaceIPTableComponentPositionArray(),
																										      getNewFirewallInterfaceIPComponentValueArray());															
																						updateFirewallInterfaceIpRemoveLinks();
																					} else {
																						this.disabled = true;
																					}">
																	<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.MailAccounts.AddLink"/>
																</html:link>
															</TH>	
															<!--Interface IP -->
															<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
																<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Firewall.InterfaceIP"/>
															</TH>
															<!-- Zone -->
															<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
																<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Firewall.Zone"/>
															</TH>																	
														</TR>
														<!--Interface IP Content -->
														<logic:notEmpty property="firewallInterfaceIPList" 
																	    name="B_SSM_S02_Page_Form">
															<logic:iterate id="interfaceIP" 
																		   property="firewallInterfaceIPList" 
																		   name="B_SSM_S02_Page_Form"
																		   indexId="index">
																<TR style="text-align: left;">	
																	<!-- X Button -->	
																	<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
																		<a href="javascript:doFirewallInterfaceIPRowRemove('${index + 1}')" 
										        					     	class="B_SSM_S02_Page_Form_AccountMailsGroup_Href_RemoveLink">         						
										        					     	<span style="font-weight: bold; font-size: 15px; color: black;">X</span> 
										        					    </a>	
																	</TD>															
																	<!--Interface IP -->
																	<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
																		<input type="text" 
																			   name="firewallInterfaceIPIDs" 
																			   style="display:none;"
																			   value="${interfaceIP['firewallInterfaceIPID']}"/>
																		<input type="text" 
																			   name="firewallInterfaceIPs" 
																			   maxlength="40"
																			   class="B_SSM_S02_Page_Form_TextBox_MailAccount"
																			   value="${interfaceIP['firewallInterfaceIP']}"/> 
																		  	      	       
																	</TD>
																	<!-- Zone -->
																	<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">																			
  	    																	<input type="text" 
  	    																		   name="firewallInterfaceZones" 
  	    																		   maxlength="30"
  	    																		   class="B_SSM_S02_Page_Form_TextBox_MailBox"
																			   value="${interfaceIP['firewallInterfaceZone']}"/>
																	</TD>
																</TR>
															</logic:iterate>
														</logic:notEmpty>								
													</TABLE>			
												</div>
											</fieldset>
										</TD>
									</TR>											
								</TABLE>
							</TD>	
						</TR>
						
					</TABLE>
					
					<!-- Firewall Policy Group -->								
					<div style="overflow: hidden;">	
						<TABLE id="firewallPolicyTableID" class="B_SSM_S02_Page_Form_Table_Detail">
							<!-- Firewall Policy Headers -->
							<TR>
								<!-- Add Link -->
								<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
									<html:link href="javascript:void(0);"
											   onclick="insRowPos = getTableRowCount('firewallPolicyTableID');																   													   			
											   			addComponentsToTableWithAlignMode('firewallPolicyTableID',
																						   insRowPos,						    
																						    getFirewallPolicyTableComponentPositionArray(),
																						    getNewFirewallPolicyComponentValueArray(),
																						    'top');															
														updateFirewallPolicyRemoveLinks();
														updateFirewallPolicyNoColValues();">
										<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.MailAccounts.AddLink"/>
									</html:link>
								</TH>	
								<!--Policy -->
								<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
									<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Firewall.Policy.Text"/>
								</TH>
								<!-- Source -->
								<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
									<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Firewall.Source.Text"/>
								</TH>
								<!-- Destination -->
								<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
									<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Firewall.Destination.Text"/>
								</TH>
								<!-- Protocol -->
								<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
									<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Firewall.Protocol.Text"/>
								</TH>
								<!-- Action -->
								<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
									<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Firewall.Action.Text"/>
								</TH>
								<!-- Remarks -->
								<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
									<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Firewall.Remarks.Text"/>
								</TH>
							</TR>	
							<!-- Firewall Policy Content -->
							<logic:notEmpty property="firewallPolicyList" 
										    name="B_SSM_S02_Page_Form">
								<logic:iterate id="policy" 
											   property="firewallPolicyList" 
											   name="B_SSM_S02_Page_Form"
											   indexId="index">											
									<TR style="text-align: left; vertical-align: top;">																			
										<!-- X Button -->
										<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
											<a href="javascript:doFirewallPolicyRowRemove(' ${index + 1} ')" 
	        					     		   class="B_SSM_S02_Page_Form_AccountMailsGroup_Href_RemoveLink">        						
	        					     			<span style="font-weight: bold; font-size: 15px; color: black;">X</span>
	        					    		</a> 
										</TD>
										<!--Policy -->
										<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
											${index + 1}	
											<input type="text" 
												   name="firewallPolicyIDs" 
												   style="display:none;"
												   value="${policy['firewallPolicyID']}"/>
										</TD>
										<!-- Source -->
										<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">													
											<input type="text" 
												   name="firewallPolicySources" 
												   class="B_SSM_S02_Page_Form_TextBox_MailAccount"
												   maxlength="50"
												   value="${policy['firewallPolicySource']} "/>
										</TD>
										<!-- Destination -->
										<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">												
											<input type="text" 
												   name="firewallPolicyDestinations" 
												   class="B_SSM_S02_Page_Form_TextBox_MailAccount"
												   maxlength="50"
												   value="${policy['firewallPolicyDestination']} "/>
										</TD>
										<!-- Protocol -->
										<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">													
											<textarea class="B_SSM_S02_Page_Form_TextBox_MailAccount"
													  name="firewallPolicyProtocols"
													  onkeypress="return checkLength(event, this, 100)"
													  onchange="customizeElementValueLength(this, 100);">
												<bean:write name="policy" property="firewallPolicyProtocol"/>
											</textarea>	  	  
										</TD>
										<!-- Action -->
										<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">													
											<input type="text" 
												   class="B_SSM_S02_Page_Form_TextBox_MailAccount"
												   name="firewallPolicyActions" 
												   maxlength="10"
												   value="${policy['firewallPolicyAction']}"/>	
										</TD>
										<!-- Remarks -->
										<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">													
											<input type="text" 
												   class="B_SSM_S02_Page_Form_TextBox_MailAccount"
												   name="firewallPolicyRemarks" 
												   maxlength="100"
												   value="${policy['firewallPolicyRemark']}"/>	
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
								<textarea id="firewallMemoRemarksID"
									   	   name="firewallMemoRemarks" rows="5" cols="60">	
									<bean:write name="B_SSM_S02_Page_Form" property="firewallMemoRemarks"/>
								</textarea>					   
							</div>
						</TD>
					</TR>
				</TABLE>
			</div>
		</logic:equal>
		<logic:notEqual name="B_SSM_S02_Page_Form" property="displayMemoFlg" value="1">
			<textarea id="firewallMemoRemarksID"
					  name="firewallMemoRemarks" rows="5" cols="60" style="display:none;">	
				<bean:write name="B_SSM_S02_Page_Form" property="firewallMemoRemarks"/>
			</textarea>	
		</logic:notEqual>
	</div>
</body>
</html>