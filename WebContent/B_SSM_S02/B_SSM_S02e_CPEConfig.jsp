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
	<div id="CPEConfigurationTab" 
		 style='<%=(B_SSM_S02e_BUtils.isCPEConfigurationTabEnable(infoIDArray))? "display:block;":"display:none;"%>' 
		 class="B_SSM_S02_Page_Form_Tabs_TabPanel">
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
													    id="CPEConfigurationNameSelectID"
													    onchange="doSelectCPENameList(this);"
													    size="6">															    
													    <logic:notEmpty name="B_SSM_S02_Page_Form"
														    			property="CPEConfigurationDetailList">
														    <logic:iterate id="cPEConfigurationDetail" 
														    			   name="B_SSM_S02_Page_Form"
														    			   property="CPEConfigurationDetailList"
														    			   indexId="index">
														    	<option value="${cPEConfigurationDetail['CPEConfigurationDID']}">
														    		<bean:write name="cPEConfigurationDetail" property="CPEConfigurationName"/>
														    	</option>																    		
														    </logic:iterate>
													    </logic:notEmpty>
												</select>
											</div>
										</TD>
										<!-- CPE list button -->									
										<TD style="vertical-align: middle;">
											<!-- Add button -->
											<html:button property="button" 
														 styleId="CPEAddBtnID"
														 style="width: 75px; height: 25px;"
														 onclick="doAddCPEConfig();">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Server.AddButton.Text"/>		
											</html:button>
											<br/>
											<!-- Delete button -->
											<html:button property="button" 
														 styleId="CPEDeleteBtnID"
													     style="width: 75px; height: 25px;" 
													     disabled="true"
													     onclick="doDeleteCPEConfig();">
												<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Server.DeleteButton.Text"/>		
											</html:button>
											<div id="CPEConfigurationInfoDataID"> 
												<logic:notEmpty name="B_SSM_S02_Page_Form" 
																property="CPEConfigurationDetailList">
		    										<logic:iterate id="cPEConfigurationDetail" 
		    													   name="B_SSM_S02_Page_Form"  
		    													   property="CPEConfigurationDetailList" 
		    													   indexId="index">	
		    											<bean:define id="cPEName" 
		    														 name="cPEConfigurationDetail" 
		    														 property="CPEConfigurationName"
		    														 type="java.lang.String">
		    											</bean:define>
		    											
		    											<bean:define id="cPEDID" 
		    														 name="cPEConfigurationDetail" 
		    														 property="CPEConfigurationDID"
		    														 type="java.lang.String">
		    											</bean:define>	
		    											
		    											<input type="text" 
															   style="display:none;"
															   name="CPEConfigurationNames" 
															   id="CPEConfigurationName<%=cPEName.substring(4)%>"
															   value="${cPEConfigurationDetail['CPEConfigurationName']}"/>
														
														<input type="text" 
															   style="display:none;"
															   name="CPEConfigurationDIDs" 
															   id="CPEConfigurationDID<%=cPEName.substring(4)%>"
															   value="${cPEConfigurationDetail['CPEConfigurationDID']}"/>		   
		    										</logic:iterate>
		    									</logic:notEmpty>			   
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
		<div id="CPEConfigContent">
			<!-- Null CPE Content -->
			<div id="NullCPEConfigContent" style="display: none;">
				<!-- Assigned IP Addresses & Prefix Group -->
				<div style='<%=(infoIDArray != null && infoIDArray.contains(22))? "display:block;":"display:none;"%> '
					 class="B_SSM_S02_Page_Form_Container_FieldSet">
					<fieldset class="B_SSM_S02_Page_Form_FieldSet">
						<legend class="B_SSM_S02_Page_Form_FieldSet_Legend">
							<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.AssignedIPAddressesPrefix.Legend" />			  						
						</legend>
						<div class="B_SSM_S02_Page_Form_FieldSet_PanelContent" >
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
													<input type="text" 
														   class="B_SSM_S02_Page_Form_TextBox"
														   maxlength="30"
														   name="CPEConfigurationISPEdgeRouterIPAddresses" 
														   value=""/>	
												</TD>
											</TR>	
											<!-- Router WAN Interfaces IP Address -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.AssignedIPAddressesPrefix.RouterWANInterfacesIPAddress.Text"/>
												</TD>
												<TD>
													:&nbsp;																
													<input type="text" 
														   class="B_SSM_S02_Page_Form_TextBox"
														   maxlength="30"
														   name="CPEConfigurationRouterWANInterfacesIPAddresses" 
														   value=""/>	
												</TD>
											</TR>	
											<!-- Network Address -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.AssignedIPAddressesPrefix.NetworkAddress.Text"/>
												</TD>
												<TD>
													:&nbsp;																
													<input type="text" 
														   class="B_SSM_S02_Page_Form_TextBox"
														   maxlength="30"
														   name="CPEConfigurationNetworkAddressIDs" 
														   value=""/>	
												</TD>
											</TR>	
										</TABLE>	
									</TD>								
								</TR>											
							</TABLE>													
						</div>
					</fieldset>
				</div>	
				
				<!-- CPE Configuration Group -->
				<div class="B_SSM_S02_Page_Form_Container_FieldSet" style='<%=(infoIDArray != null && infoIDArray.contains(23))? "display:block;":"display:none;"%> '>
					<fieldset class="B_SSM_S02_Page_Form_FieldSet">
						<legend class="B_SSM_S02_Page_Form_FieldSet_Legend">
							<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.CPEConfiguration.Legend" />			  						
						</legend>
						<div class="B_SSM_S02_Page_Form_FieldSet_PanelContent" >
							<TABLE class="B_SSM_S02_Page_Form_Table">
								<TR>
									<TD class="B_SSM_S02_Page_Form_Table_Col">
										<TABLE width="100%">											
											<!-- Managed CPE is Used -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.CPEConfiguration.ManagedCPEIsUsed.Text"/>
												</TD>
												<TD style="vertical-align: top;">
													<div onclick='setCPEConfigurationManagedIsUsedsValue(this);'>																
														 :&nbsp;
														 <input type="radio"
														 		onclick="" 
															    value="1"/>
														 <bean:message key="B_SSM_S02_Page.Form.Yes.Text"/>
														 &nbsp;
														 <input type="radio" 
																onclick="" 
															    value="0"/>
														 <bean:message key="B_SSM_S02_Page.Form.No.Text"/>
														 <input type="hidden"
															    name="CPEConfigurationManagedCPEIsUseds" 
															    value=""/>
													 </div>	    
												</TD>
											</TR>	
											<!-- CPE Model Installed -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.CPEConfiguration.CPEModelInstalled.Text"/>
												</TD>
												<TD>
													:&nbsp;																
													<input type="text" 
														   class="B_SSM_S02_Page_Form_TextBox"
														   maxlength="100"
														   name="CPEConfigurationCPEModelInstalleds" 
														   value=""/>	
												</TD>
											</TR>
											<!-- LAN -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.CPEConfiguration.LAN.Text"/>
												</TD>
												<TD>
													:&nbsp;																
													<input type="text" 
														   class="B_SSM_S02_Page_Form_TextBox"
														   maxlength="30"
														   name="CPEConfigurationLANs" 
														   value=""/>		   
												</TD>
											</TR>
											<!-- WAN -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.CPEConfiguration.WAN.Text"/>
												</TD>
												<TD>
													:&nbsp;																
													<input type="text" 
														   class="B_SSM_S02_Page_Form_TextBox"
														   maxlength="30"
														   name="CPEConfigurationWANs" 
														   value=""/>	   
												</TD>
											</TR>
											<!-- Serial Number -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.CPEConfiguration.SerialNumber.Text"/>
												</TD>
												<TD>
													:&nbsp;																
													<input type="text" 
														   class="B_SSM_S02_Page_Form_TextBox"
														   maxlength="100"
														   name="CPEConfigurationSerialNumbers" 
														   value=""/>	
												</TD>
											</TR>
											<!-- Equipment & Item  -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol_UpVAlign">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.CPEConfiguration.Equipment_ItemsIncludedInManagedCPEPackage.Text"/>
												</TD>
												<TD>
													:&nbsp;																
													<textarea  class="B_SSM_S02_Page_Form_TextBox"
															   onkeypress="return checkLength(event, this, 500)"
										   					   onchange="customizeElementValueLength(this, 500);"
														   	   name="CPEConfigurationEquipment_ItemsIncludedInManagedCPEPackages" >
													</textarea>
												</TD>
											</TR>
											<!-- Customer specific configuration on router -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol_UpVAlign">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.CPEConfiguration.CustomerSpecificConfigurationOnRouter.Text"/>
												</TD>
												<TD>
													:&nbsp;																
													<textarea  class="B_SSM_S02_Page_Form_TextBox"
															   onkeypress="return checkLength(event, this, 500)"
										   					   onchange="customizeElementValueLength(this, 500);"
														   	   name="CPEConfigurationCustomerSpecificConfigurationOnRouters" >
													</textarea>		       
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
		
			<!-- Disable CPE Content -->
			<div id="DisableCPEConfigContent" >
				<!-- Assigned IP Addresses & Prefix Group -->
				<div style='<%=(infoIDArray != null && infoIDArray.contains(22))? "display:block;":"display:none;"%>' 
					 class="B_SSM_S02_Page_Form_Container_FieldSet">
					<fieldset class="B_SSM_S02_Page_Form_FieldSet"  style="height: 45px;" >
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
													<input type="text" 
														   class="B_SSM_S02_Page_Form_TextBox"
														   disabled="disabled"
														   value=""/>	
												</TD>
											</TR>	
											<!-- Router WAN Interfaces IP Address -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.AssignedIPAddressesPrefix.RouterWANInterfacesIPAddress.Text"/>
												</TD>
												<TD>
													:&nbsp;																
													<input type="text" 
														   class="B_SSM_S02_Page_Form_TextBox"
														   disabled="disabled"
														   value=""/>	
												</TD>
											</TR>	
											<!-- Network Address -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.AssignedIPAddressesPrefix.NetworkAddress.Text"/>
												</TD>
												<TD>
													:&nbsp;																
													<input type="text" 
														   class="B_SSM_S02_Page_Form_TextBox"
														   disabled="disabled"
														   value=""/>	
												</TD>
											</TR>	
										</TABLE>	
									</TD>								
								</TR>											
							</TABLE>													
						</div>
					</fieldset>
				</div>
				
				<!-- CPE Configuration Group -->
				<div class="B_SSM_S02_Page_Form_Container_FieldSet" style='<%=(infoIDArray != null && infoIDArray.contains(23))? "display:block;":"display:none;"%>'>
					<fieldset class="B_SSM_S02_Page_Form_FieldSet"  style="height: 45px;">
						<legend class="B_SSM_S02_Page_Form_FieldSet_Legend">
							<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.CPEConfiguration.Legend" />			  						
						</legend>
						<div class="B_SSM_S02_Page_Form_FieldSet_PanelContent"  style="display: none;" >
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
													 <input type="radio" 
														    disabled="disabled"
														    value="1"/>
													 <bean:message key="B_SSM_S02_Page.Form.Yes.Text"/>
													 &nbsp;
													 <input type="radio" 
														    disabled="disabled"
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
													<input type="text" 
														   class="B_SSM_S02_Page_Form_TextBox"
														   disabled="disabled"
														   value=""/>	
												</TD>
											</TR>
											<!-- LAN -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.CPEConfiguration.LAN.Text"/>
												</TD>
												<TD>
													:&nbsp;																
													<input type="text" 
														   class="B_SSM_S02_Page_Form_TextBox"
														   disabled="disabled"
														   value=""/>		   
												</TD>
											</TR>
											<!-- WAN -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.CPEConfiguration.WAN.Text"/>
												</TD>
												<TD>
													:&nbsp;																
													<input type="text" 
														   class="B_SSM_S02_Page_Form_TextBox"
														   disabled="disabled"
														   value=""/>	   
												</TD>
											</TR>
											<!-- Serial Number -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.CPEConfiguration.SerialNumber.Text"/>
												</TD>
												<TD>
													:&nbsp;																
													<input type="text" 
														   class="B_SSM_S02_Page_Form_TextBox"
														   disabled="disabled"
														   value=""/>	
												</TD>
											</TR>
											<!-- Equipment & Item  -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol_UpVAlign">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.CPEConfiguration.Equipment_ItemsIncludedInManagedCPEPackage.Text"/>
												</TD>
												<TD>
													:&nbsp;																
													<textarea  class="B_SSM_S02_Page_Form_TextBox"
														       disabled="disabled">	
														
													</textarea>
												</TD>
											</TR>
											<!-- Customer specific configuration on router -->
											<TR>
												<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol_UpVAlign">
													<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.CPEConfiguration.CustomerSpecificConfigurationOnRouter.Text"/>
												</TD>
												<TD>
													:&nbsp;																
													<textarea  class="B_SSM_S02_Page_Form_TextBox"
														       disabled="disabled" >	
														
													</textarea>		       
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
			
			<!-- Valued CPE Config Content -->
			<logic:notEmpty name="B_SSM_S02_Page_Form" property="CPEConfigurationDetailList">
			    <logic:iterate id="cPEConfigurationDetail" name="B_SSM_S02_Page_Form"  property="CPEConfigurationDetailList" indexId="index">
					<div id="CPEConfigContent${cPEConfigurationDetail['CPEConfigurationDID']}"
					     style="display: none">
						<!-- Assigned IP Addresses & Prefix Group -->
						<div style='<%=(infoIDArray != null && infoIDArray.contains(22))? "display:block;":"display:none;"%>' 
							 class="B_SSM_S02_Page_Form_Container_FieldSet">
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
															<input type="text" 
																   class="B_SSM_S02_Page_Form_TextBox"
																   maxlength="30"
																   name="CPEConfigurationISPEdgeRouterIPAddresses" 
																   value="${cPEConfigurationDetail['CPEConfigurationISPEdgeRouterIPAddress']}"/>	
														</TD>
													</TR>	
													<!-- Router WAN Interfaces IP Address -->
													<TR>
														<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
															<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.AssignedIPAddressesPrefix.RouterWANInterfacesIPAddress.Text"/>
														</TD>
														<TD>
															:&nbsp;																
															<input type="text" 
																   class="B_SSM_S02_Page_Form_TextBox"
																   maxlength="30"
																   name="CPEConfigurationRouterWANInterfacesIPAddresses" 
																   value="${cPEConfigurationDetail['CPEConfigurationRouterWANInterfacesIPAddress']}"/>			   
														</TD>
													</TR>	
													<!-- Network Address -->
													<TR>
														<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
															<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.AssignedIPAddressesPrefix.NetworkAddress.Text"/>
														</TD>
														<TD>
															:&nbsp;																
															<input type="text" 
																   class="B_SSM_S02_Page_Form_TextBox"
																   maxlength="30"
																   name="CPEConfigurationNetworkAddressIDs" 
																   value="${cPEConfigurationDetail['CPEConfigurationNetworkAddressID']}"/>				   
														</TD>
													</TR>	
												</TABLE>	
											</TD>								
										</TR>											
									</TABLE>													
								</div>
							</fieldset>
						</div>
						
						<!-- CPE Configuration Group -->
						<div class="B_SSM_S02_Page_Form_Container_FieldSet" style='<%=(infoIDArray != null && infoIDArray.contains(23))? "display:block;":"display:none;"%>' >
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
														<TD style="vertical-align: top;">
															<div onclick='setCPEConfigurationManagedIsUsedsValue(this);'>																
																 :&nbsp;
																 <bean:define id="cPEManagedIsUsed" 
																 			  name="cPEConfigurationDetail" 
																 			  property="CPEConfigurationManagedCPEIsUsed"
																 			  type="java.lang.String"/>
																 <input type="radio"
																 		onclick="" 
																 		<%=(cPEManagedIsUsed != null && cPEManagedIsUsed.equals("1"))?"checked":""%>
																	    value="1"/>
																 <bean:message key="B_SSM_S02_Page.Form.Yes.Text"/>
																 &nbsp;
																 <input type="radio" 
																		onclick=""
																		<%=(cPEManagedIsUsed != null && cPEManagedIsUsed.equals("0"))?"checked":""%>	 
																	    value="0"/>
																 <bean:message key="B_SSM_S02_Page.Form.No.Text"/>
																 <input type="hidden"
																	    name="CPEConfigurationManagedCPEIsUseds" 
																	    value="${cPEConfigurationDetail['CPEConfigurationManagedCPEIsUsed']}"/>
															 </div>	    
														</TD>
													</TR>	
													<!-- CPE Model Installed -->
													<TR>
														<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
															<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.CPEConfiguration.CPEModelInstalled.Text"/>
														</TD>
														<TD>
															:&nbsp;																
															<input type="text" 
																   class="B_SSM_S02_Page_Form_TextBox"
																   maxlength="100"
																   name="CPEConfigurationCPEModelInstalleds" 
																   value="${cPEConfigurationDetail['CPEConfigurationCPEModelInstalled']}"/>		   
														</TD>
													</TR>
													<!-- LAN -->
													<TR>
														<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
															<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.CPEConfiguration.LAN.Text"/>
														</TD>
														<TD>
															:&nbsp;																
															<input type="text" 
																   class="B_SSM_S02_Page_Form_TextBox"
																   maxlength="30"
																   name="CPEConfigurationLANs" 
																   value="${cPEConfigurationDetail['CPEConfigurationLAN']}"/>		   
														</TD>
													</TR>
													<!-- WAN -->
													<TR>
														<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
															<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.CPEConfiguration.WAN.Text"/>
														</TD>
														<TD>
															:&nbsp;																
															<input type="text" 
																   class="B_SSM_S02_Page_Form_TextBox"
																   maxlength="30"
																   name="CPEConfigurationWANs" 
																   value="${cPEConfigurationDetail['CPEConfigurationWAN']}"/>		   
														</TD>
													</TR>
													<!-- Serial Number -->
													<TR>
														<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol">
															<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.CPEConfiguration.SerialNumber.Text"/>
														</TD>
														<TD>
															:&nbsp;																
															<input type="text" 
																   class="B_SSM_S02_Page_Form_TextBox"
																   maxlength="100"
																   name="CPEConfigurationSerialNumbers" 
																   value="${cPEConfigurationDetail['CPEConfigurationSerialNumber']}"/>	
														</TD>
													</TR>
													<!-- Equipment & Item  -->
													<TR>
														<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol_UpVAlign">
															<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.CPEConfiguration.Equipment_ItemsIncludedInManagedCPEPackage.Text"/>
														</TD>
														<TD>
															:&nbsp;																
															<textarea  class="B_SSM_S02_Page_Form_TextBox" 
																	   onkeypress="return checkLength(event, this, 500)"
										   					   		   onchange="customizeElementValueLength(this, 500);"
																   	   name="CPEConfigurationEquipment_ItemsIncludedInManagedCPEPackages" >	
																<bean:write name="cPEConfigurationDetail" 
																			property="CPEConfigurationEquipment_ItemsIncludedInManagedCPEPackage"/>
															</textarea>
														</TD>
													</TR>
													<!-- Customer specific configuration on router -->
													<TR>
														<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol_UpVAlign">
															<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.CPEConfiguration.CustomerSpecificConfigurationOnRouter.Text"/>
														</TD>
														<TD>
															:&nbsp;																
															<textarea  class="B_SSM_S02_Page_Form_TextBox"
																	   onkeypress="return checkLength(event, this, 500)"
												   					   onchange="customizeElementValueLength(this, 500);"
																   	   name="CPEConfigurationCustomerSpecificConfigurationOnRouters"  >	
																<bean:write name="cPEConfigurationDetail" property="CPEConfigurationCustomerSpecificConfigurationOnRouter"/>
															</textarea>		       
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
				</logic:iterate>
			</logic:notEmpty>
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
								<textarea id="CPEConfigurationMemoRemarksID"
									   	   name="CPEConfigurationMemoRemarks"
									   	   rows="5" cols="60">	
									<bean:write name="B_SSM_S02_Page_Form" property="CPEConfigurationMemoRemarks"/>
								</textarea>				   
							</div>
						</TD>
					</TR>
				</TABLE>
			</div>
		</logic:equal>
		<logic:notEqual name="B_SSM_S02_Page_Form" property="displayMemoFlg" value="1">
			<textarea id="CPEConfigurationMemoRemarksID"
				   	   name="CPEConfigurationMemoRemarks"
				   	   rows="5" cols="60" style="display:none">	
				<bean:write name="B_SSM_S02_Page_Form" property="CPEConfigurationMemoRemarks"/>
			</textarea>	
		</logic:notEqual>
	</div>
</body>
</html>