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
	<% if (B_SSM_S02e_BUtils.isCPEConfigurationTabEnable(infoIDArray)) { %>
		<div id="CPEConfigurationTab" class="B_SSM_S02_Page_Form_Tabs_TabPanel">
			<!-- CPE Group -->
			<div class="B_SSM_S02_Page_Form_Container_FieldSet">
				<fieldset class="B_SSM_S02_Page_Form_FieldSet">
					<legend class="B_SSM_S02_Page_Form_FieldSet_Legend">
						<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.CPE.Legend" />			  						
					</legend>
					<div class="B_SSM_S02_Page_Form_FieldSet_PanelContent">
						<TABLE class="B_SSM_S02_Page_Form_Table">
							<TR>
								<TD class="B_SSM_S02_Page_Form_Table_Col">
									<TABLE>
										<TR>
											<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol_UpVAlign">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.CPE.CPE.Text"/>										
											</TD>
											<!-- CPE list -->
											<TD style="vertical-align: top;">																					
												<div style="float: left; ">														
													<select class="B_SSM_S02_Page_Form_TextArea"  
														    id="CPEConfigurationNameID"
														    onchange="doSelectViewCPENameList(this);"
														    size="5">															    
														    <logic:notEmpty name="B_SSM_S02_Page_Form"
															    			property="CPEConfigurationDetailList">
															    <logic:iterate id="cPEConfigurationDetail" 
															    			   name="B_SSM_S02_Page_Form"
															    			   property="CPEConfigurationDetailList"
															    			   indexId="index">
															    	<option value="${cPEConfigurationDetail['CPEConfigurationDID']}">
															    		${cPEConfigurationDetail['CPEConfigurationName']}
															    	</option>																    		
															    </logic:iterate>
														    </logic:notEmpty>
													</select>
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
			
			<!-- CPE Config Content -->
			<logic:notEmpty name="B_SSM_S02_Page_Form" property="CPEConfigurationDetailList">
			    <logic:iterate id="cPEConfigurationDetail" name="B_SSM_S02_Page_Form"  property="CPEConfigurationDetailList" indexId="index">
					<div id="CPEConfigContent${cPEConfigurationDetail['CPEConfigurationDID']}"
					     style="display: none">
						<!-- Assigned IP Addresses & Prefix Group -->
						<%if (infoIDArray != null && infoIDArray.contains(22)) { %>
						<div class="B_SSM_S02_Page_Form_Container_FieldSet">
							<fieldset class="B_SSM_S02_Page_Form_FieldSet">
								<legend class="B_SSM_S02_Page_Form_FieldSet_Legend">
									<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.AssignedIPAddressesPrefix.Legend" />			  						
								</legend>
								<div class="B_SSM_S02_Page_Form_FieldSet_PanelContent">
									<TABLE class="B_SSM_S02_Page_Form_Table">
										<TR>
											<TD class="B_SSM_S02_Page_Form_Table_Col">
												<TABLE width="100%">
													<!-- ISP Edge Router IP Address -->
													<TR>
														<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
															<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.AssignedIPAddressesPrefix.ISPEdgeRouterIPAddress.Text"/>
														</TD>
														<TD>
															:&nbsp;
															<bean:write name="cPEConfigurationDetail" 															   
																	    property="CPEConfigurationISPEdgeRouterIPAddress"/>
														</TD>
													</TR>	
													<!-- Router WAN Interfaces IP Address -->
													<TR>
														<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
															<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.AssignedIPAddressesPrefix.RouterWANInterfacesIPAddress.Text"/>
														</TD>
														<TD>
															:&nbsp;
															<bean:write name="cPEConfigurationDetail" 															  
																	    property="CPEConfigurationRouterWANInterfacesIPAddress"/>
														</TD>
													</TR>	
													<!-- Network Address -->
													<TR>
														<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
															<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.AssignedIPAddressesPrefix.NetworkAddress.Text"/>
														</TD>
														<TD>
															:&nbsp;
															<bean:write name="cPEConfigurationDetail" 															  
																	    property="CPEConfigurationNetworkAddressID"/>
														</TD>
													</TR>	
												</TABLE>	
											</TD>								
										</TR>											
									</TABLE>													
								</div>
							</fieldset>
						</div>
						<%} %>
						
						<!-- CPE Configuration Group -->
						<%if (infoIDArray != null && infoIDArray.contains(23)) { %>
						<div class="B_SSM_S02_Page_Form_Container_FieldSet">
							<fieldset class="B_SSM_S02_Page_Form_FieldSet">
								<legend class="B_SSM_S02_Page_Form_FieldSet_Legend">
									<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.CPEConfiguration.Legend" />			  						
								</legend>
								<div class="B_SSM_S02_Page_Form_FieldSet_PanelContent">
									<TABLE class="B_SSM_S02_Page_Form_Table">
										<TR>
											<TD class="B_SSM_S02_Page_Form_Table_Col">
												<TABLE width="100%">											
													<!-- Managed CPE is Used -->
													<TR>
														<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
															<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.CPEConfiguration.ManagedCPEIsUsed.Text"/>
														</TD>
														<TD>
															:&nbsp;
															<bean:define id="cPEManagedIsUsed" 
														 			     name="cPEConfigurationDetail" 
														 			     property="CPEConfigurationManagedCPEIsUsed"
														 			     type="java.lang.String"/>
															 <input type="radio"
															 		onclick="" 
															 		disabled="disabled"
															 		<%=(cPEManagedIsUsed != null && cPEManagedIsUsed.equals("1"))?"checked":""%>
																    value="1"/>
															 <bean:message key="B_SSM_S02_Page.Form.Yes.Text"/>
															 &nbsp;
															 <input type="radio" 
																	onclick=""
																	disabled="disabled"
																	<%=(cPEManagedIsUsed != null && cPEManagedIsUsed.equals("0"))?"checked":""%>	 
																    value="0"/>
															 <bean:message key="B_SSM_S02_Page.Form.No.Text"/>
														</TD>
													</TR>	
													<!-- CPE Model Installed -->
													<TR>
														<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
															<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.CPEConfiguration.CPEModelInstalled.Text"/>
														</TD>
														<TD>
															:&nbsp;
															<bean:write name="cPEConfigurationDetail" 															   
																	    property="CPEConfigurationCPEModelInstalled"/>
														</TD>
													</TR>
													<!-- LAN -->
													<TR>
														<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
															<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.CPEConfiguration.LAN.Text"/>
														</TD>
														<TD>
															:&nbsp;
															<bean:write name="cPEConfigurationDetail" 															   
																	    property="CPEConfigurationLAN"/>
														</TD>
													</TR>
													<!-- WAN -->
													<TR>
														<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
															<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.CPEConfiguration.WAN.Text"/>
														</TD>
														<TD>
															:&nbsp;
															<bean:write name="cPEConfigurationDetail" 															  
																	    property="CPEConfigurationWAN"/>
														</TD>
													</TR>
													<!-- Serial Number -->
													<TR>
														<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
															<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.CPEConfiguration.SerialNumber.Text"/>
														</TD>
														<TD>
															:&nbsp;
															<bean:write name="cPEConfigurationDetail" 															   
																	    property="CPEConfigurationSerialNumber"/>
														</TD>
													</TR>
													<!-- Equipment & Item  -->
													<TR>
														<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
															<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.CPEConfiguration.Equipment_ItemsIncludedInManagedCPEPackage.Text"/>
														</TD>
														<TD>
															:&nbsp;
															<bean:write name="cPEConfigurationDetail" 															   	   
																	    property="CPEConfigurationEquipment_ItemsIncludedInManagedCPEPackage"/>
														</TD>
													</TR>
													<!-- Customer specific configuration on router -->
													<TR>
														<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
															<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.CPEConfiguration.CustomerSpecificConfigurationOnRouter.Text"/>
														</TD>
														<TD>
															:&nbsp;
															<bean:write name="cPEConfigurationDetail" 															   	  
																	    property="CPEConfigurationCustomerSpecificConfigurationOnRouter"/>
														</TD>
													</TR>
												</TABLE>	
											</TD>								
										</TR>							
									</TABLE>													
								</div>
							</fieldset>
						</div>
						<%} %>
					</div>
				</logic:iterate>
			</logic:notEmpty>
			
			<!-- CPE null content -->
			<div id="NullCPEConfigContent">
				<!-- Assigned IP Addresses & Prefix Group -->
				<%if (infoIDArray != null && infoIDArray.contains(22)) { %>
				<div class="B_SSM_S02_Page_Form_Container_FieldSet">
					<fieldset class="B_SSM_S02_Page_Form_FieldSet" style="height: 45px;">
						<legend class="B_SSM_S02_Page_Form_FieldSet_Legend">
							<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.AssignedIPAddressesPrefix.Legend" />			  						
						</legend>
						<div class="B_SSM_S02_Page_Form_FieldSet_PanelContent" style="display: none;">
							<TABLE class="B_SSM_S02_Page_Form_Table">
								<TR>
									<TD class="B_SSM_S02_Page_Form_Table_Col">
										<TABLE width="100%">
											<!-- ISP Edge Router IP Address -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.AssignedIPAddressesPrefix.ISPEdgeRouterIPAddress.Text"/>
												</TD>
												<TD>
													:&nbsp;
													
												</TD>
											</TR>	
											<!-- Router WAN Interfaces IP Address -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.AssignedIPAddressesPrefix.RouterWANInterfacesIPAddress.Text"/>
												</TD>
												<TD>
													:&nbsp;
													
												</TD>
											</TR>	
											<!-- Network Address -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.AssignedIPAddressesPrefix.NetworkAddress.Text"/>
												</TD>
												<TD>
													:&nbsp;
													
												</TD>
											</TR>	
										</TABLE>	
									</TD>								
								</TR>											
							</TABLE>													
						</div>
					</fieldset>
				</div>
				<%} %>
				
				<!-- CPE Configuration Group -->
				<%if (infoIDArray != null && infoIDArray.contains(23)) { %>
				<div class="B_SSM_S02_Page_Form_Container_FieldSet">
					<fieldset class="B_SSM_S02_Page_Form_FieldSet" style="height: 45px;">
						<legend class="B_SSM_S02_Page_Form_FieldSet_Legend">
							<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.CPEConfiguration.Legend" />			  						
						</legend>
						<div class="B_SSM_S02_Page_Form_FieldSet_PanelContent" style="display: none;">
							<TABLE class="B_SSM_S02_Page_Form_Table">
								<TR>
									<TD class="B_SSM_S02_Page_Form_Table_Col">
										<TABLE width="100%">											
											<!-- Managed CPE is Used -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.CPEConfiguration.ManagedCPEIsUsed.Text"/>
												</TD>
												<TD>
													:&nbsp;
													<html:radio name="B_SSM_S02_Page_Form" 
																styleId="CPEConfigurationManagedCPEIsUsedYID"
																property="CPEConfigurationManagedCPEIsUsed" 
																value="1" 
																disabled="true">
														<bean:message key="B_SSM_S02_Page.Form.Yes.Text"/>
													 </html:radio>
													 <html:radio name="B_SSM_S02_Page_Form" 
													 			 styleId="CPEConfigurationManagedCPEIsUsedNID"
																 property="CPEConfigurationManagedCPEIsUsed" 
													 			 value="0"
																 disabled="true">
														<bean:message key="B_SSM_S02_Page.Form.No.Text"/>
													 </html:radio>
												</TD>
											</TR>	
											<!-- CPE Model Installed -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.CPEConfiguration.CPEModelInstalled.Text"/>
												</TD>
												<TD>
													:&nbsp;
													
												</TD>
											</TR>
											<!-- LAN -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.CPEConfiguration.LAN.Text"/>
												</TD>
												<TD>
													:&nbsp;
													
												</TD>
											</TR>
											<!-- WAN -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.CPEConfiguration.WAN.Text"/>
												</TD>
												<TD>
													:&nbsp;
													
												</TD>
											</TR>
											<!-- Serial Number -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.CPEConfiguration.SerialNumber.Text"/>
												</TD>
												<TD>
													:&nbsp;
													
												</TD>
											</TR>
											<!-- Equipment & Item  -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.CPEConfiguration.Equipment_ItemsIncludedInManagedCPEPackage.Text"/>
												</TD>
												<TD>
													:&nbsp;
													
												</TD>
											</TR>
											<!-- Customer specific configuration on router -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.CPEConfiguration.CustomerSpecificConfigurationOnRouter.Text"/>
												</TD>
												<TD>
													:&nbsp;
													
												</TD>
											</TR>
										</TABLE>	
									</TD>								
								</TR>							
							</TABLE>													
						</div>
					</fieldset>
				</div>
				<%} %>
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
													property="CPEConfigurationMemoRemarks"/>
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