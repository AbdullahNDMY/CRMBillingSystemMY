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
	<% if (B_SSM_S02e_BUtils.isFirewallTabEnable(infoIDArray)) { %>
		<div id="firewallTab" class="B_SSM_S02_Page_Form_Tabs_TabPanel">
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
												<bean:write name="B_SSM_S02_Page_Form" 															    
														    property="firewallNo"/>
											</TD>
										</TR>
										<!-- Firewall Type -->
										<TR>
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Firewall.FirewallType.Text"/>
											</TD>
											<TD>
												:&nbsp;
												<bean:write name="B_SSM_S02_Page_Form" 															     
														     property="firewallType"/>
											</TD>
										</TR>
										<!-- Firewall Model -->
										<TR>
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Firewall.FirewallModel.Text"/>
											</TD>
											<TD>
												:&nbsp;
												<bean:write name="B_SSM_S02_Page_Form" 															     
														    property="firewallModel"/>
											</TD>
										</TR>
										<!-- Interface IP (Trusted) -->
										<TR>
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Firewall.InterfaceIP_Trusted.Text"/>
											</TD>
											<TD>
												:&nbsp;
												<bean:write name="B_SSM_S02_Page_Form" 															   
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
												<bean:write name="B_SSM_S02_Page_Form" 															  
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
												<bean:write name="B_SSM_S02_Page_Form" 															   
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
															value="1"  
															disabled="true">
													<bean:message key="B_SSM_S02_Page.Form.Yes.Text"/>
												 </html:radio>
												 <html:radio name="B_SSM_S02_Page_Form" 
												 			 styleId="firewallNAT_OFFID"
															 property="firewallNAT" 
												 			 value="0" 
															 disabled="true">
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
															value="1"  
															disabled="true">
													<bean:message key="B_SSM_S02_Page.Form.Yes.Text"/>
												 </html:radio>
												 <html:radio name="B_SSM_S02_Page_Form" 
												 			 styleId="firewallDHCP_OFFID"
															 property="firewallDHCP" 
												 			 value="0" 
															 disabled="true">
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
												<bean:write name="B_SSM_S02_Page_Form" 															   
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
												<bean:write name="B_SSM_S02_Page_Form" 															   
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
																<!-- Interface IP -->
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
																			   name="B_SSM_S02_Page_Form">
																	<TR style="text-align: left">																	
																		<!--Interface IP -->
																		<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
																			<bean:write name="interfaceIP" property="firewallInterfaceIP"/>
																		</TD>
																		<!-- Zone -->
																		<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
																			<bean:write name="interfaceIP" property="firewallInterfaceZone"/>
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
									<logic:iterate id="firewallPolicy" 
												   property="firewallPolicyList" 
												   name="B_SSM_S02_Page_Form"
												   indexId="index">
										<TR style="text-align: left">																			
											<!--Policy -->
											<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
												${index + 1}
											</TD>
											<!-- Source -->
											<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
												<bean:write name="firewallPolicy" property="firewallPolicySource"/>	
											</TD>
											<!-- Destination -->
											<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
												<bean:write name="firewallPolicy" property="firewallPolicyDestination"/>	
											</TD>
											<!-- Protocol -->
											<TD class="B_SSM_S02_Page_Form_Table_Col_Detail" style="width: 225px;">
												<textarea readonly="readonly" 
														  class="B_SSM_S02_Page_Form_View_TextArea">
													<bean:write name="firewallPolicy" property="firewallPolicyProtocol"/>	
												</textarea>
											</TD>
											<!-- Action -->
											<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
												<bean:write name="firewallPolicy" property="firewallPolicyAction"/>	
											</TD>
											<!-- Remarks -->
											<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
												<bean:write name="firewallPolicy" property="firewallPolicyRemark"/>	
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
									<textarea readonly="readonly" rows="5" cols="60">
										<bean:write name="B_SSM_S02_Page_Form" 												  
													property="firewallMemoRemarks"/>
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