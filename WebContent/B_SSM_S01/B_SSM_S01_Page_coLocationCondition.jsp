<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.HashMap"%>
<%@page import="nttdm.bsys.b_ssm.s01.data.B_SSM_S01_FieldSet"%>
<%@page import="nttdm.bsys.b_ssm.utility.BLogicUtils"%><html>
<%@page import="nttdm.bsys.common.fw.BillingSystemUserValueObject"%>
<%@page import="nttdm.bsys.common.util.CommonUtils"%> 
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
	<style>
		.ui-autocomplete {
	   		overflow-y: auto;
	   		overflow-x: hidden;
	   		font-family: Calibri;
	   		font-size: 0.9em;
 		}		
	</style>			
</head>
<body>
	<div id="B_SSM_S01_Page_Content">
		<t:defineCodeList id="LIST_PLANSTATUS"/>				
		<!-- #180 Start -->
		<html:hidden property="coLocationService" styleId="hiddenCoLocationService"/>
		<html:hidden property="coLocationPlan" styleId="hiddenCoLocationPlan"/>
		<html:hidden property="coLocationPlanDetail" styleId="hiddenCoLocationPlanDetail"/>
		<!-- #180 End -->						
		<!--/////////////////// Colocation Tab Panel ////////////////////////-->
	  	<div id="coLocationTab" class="B_SSM_S01_Page_Form_Tabs_TabPanel">
			<TABLE class="B_SSM_S01_Page_Form_Table">
				<TR>
					<!-- Customer Info -->
					<TD class="B_SSM_S01_Page_Form_Table_Col">
						<div>
							<fieldset class="B_SSM_S01_Page_Form_FieldSet" style="height:140px;">
								<legend class="B_SSM_S01_Page_Form_FieldSet_Legend">
									<bean:message key="B_SSM_S01_Page.TabPanel.CustomerInfoFieldSet.Legend" />			  						
								</legend>
								<div class="B_SSM_S01_Page_Form_FieldSet_PanelContent" style="width: 440px;">
									<TABLE width="100%">
										<!-- Customer ID-->
										<TR>
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S01_Page.TabPanel.CustomerInfoFieldSet.CustomerID.Text"/>	
											</TD>
											<TD>
												:&nbsp;
												<html:text styleClass="B_SSM_S01_Page_Form_TextBox" 
												 		   styleId="coLocationCustomerIDText"
														   name="B_SSM_S01_Page_Form" 
														   property="coLocationCustomerID"/>
											</TD>
										</TR>
										<!-- Customer Name -->
										<TR>
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S01_Page.TabPanel.CustomerInfoFieldSet.CustomerName.Text"/>	
											</TD>
											<TD>
												:&nbsp;
												<html:text styleClass="B_SSM_S01_Page_Form_TextBox"
														   styleId="coLocationCustomerNameID" 
														   name="B_SSM_S01_Page_Form" 
														   property="coLocationCustomerName"/>
											</TD>
										</TR>
										<!-- Customer Type -->
										<TR>
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S01_Page.TabPanel.CustomerInfoFieldSet.CustomerType.Text"/>	
											</TD>
											<TD>
												:&nbsp;
												<t:defineCodeList id="customerTypeCodeList"></t:defineCodeList>	
												
												<% int maxCustTypeLength = 0; %>
												<logic:iterate id="custType" name="customerTypeCodeList">														
													<bean:define id="custTypeStr" name="custType" property="name" type="java.lang.String"/> 
													<% 
														if (custTypeStr!=null && custTypeStr.toString().length() > maxCustTypeLength) {															
															maxCustTypeLength = custTypeStr.length();															
														} 
													%>
												</logic:iterate>										
												<html:select style="width:<%=maxCustTypeLength%>px;" 
															 styleClass="B_SSM_S01_Page_Form_SelectBox" 
															 styleId="coLocationCustomerTypeID" 
															 name="B_SSM_S01_Page_Form" 
															 property="coLocationCustomerType">
													<html:option value="">
														<bean:message key="B_SSM_S01_Page.BlankSelectText"/>
													</html:option>
													<html:optionsCollection name="customerTypeCodeList" label="name" value="id"/>
												</html:select>													
											</TD>
										</TR>
										<TR>
											<TD>
												&nbsp;	
											</TD>
											<TD>
												&nbsp;
											</TD>
										</TR>  						
									</TABLE>
								</div>
							</fieldset>
						</div>
					</TD>		  					
					<!-- Service Info -->	
					<TD class="B_SSM_S01_Page_Form_Table_Col">
						<!-- #180 Start -->
				        <jsp:include page="/COMMON/SearchableSelect.jsp" flush="true">
						    <jsp:param value="B_SSM_S01_Page_Form" name="ssmServiceGroup"/>
						</jsp:include>
						<!-- #180 End -->					
						<div>
							<fieldset class="B_SSM_S01_Page_Form_FieldSet" style="height:140px;">
								<legend class="B_SSM_S01_Page_Form_FieldSet_Legend">
									<bean:message key="B_SSM_S01_Page.TabPanel.ServiceInfoFieldSet.Legend"/>
								</legend>
								<div class="B_SSM_S01_Page_Form_FieldSet_PanelContent" style="width: 600px;">
									<TABLE width="100%">
										<!-- Category-->
										<TR>
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol" style="width:20%">
												<bean:message key="B_SSM_S01_Page.TabPanel.ServiceInfoFieldSet.Category.Text"/>	
											</TD>
											<TD style="width:80%">
												:&nbsp;																									
												<html:select styleClass="B_SSM_S01_Page_Form_SelectBox"  
															 styleId="coLocationCategoryID"
															 name="B_SSM_S01_Page_Form"
															 property="coLocationCategory"
															 onchange="activateB_SSM_S01_CoLocationSelectBox();"
															 style="width:426px">
													<html:option value="">
														<%-- <bean:message key="B_SSM_S01_Page.BlankSelectText"/> --%>
														- Please Select One -
													</html:option>
													<html:optionsCollection property="categoryCodeList"
																			name="B_SSM_S01_Page_Form" 
																		    label="serviceGroupName" 
																		    value="serviceGroupID"/>	
												</html:select>
											</TD>
										</TR>
										<!-- Service -->
										<TR>
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S01_Page.TabPanel.ServiceInfoFieldSet.Service.Text"/>	
											</TD>
											<TD>
												:&nbsp;
												<html:select styleId="coLocationServiceID"
															 name="B_SSM_S01_Page_Form" 
															 property="coLocationService"
															 styleClass="searchService"
															 style="width:426px"
															 disabled="true">
													<html:option value="">
														<%-- <bean:message key="B_SSM_S01_Page.BlankSelectText"/> --%>
													</html:option>
													<html:optionsCollection property="serviceCodeList"
																			name="B_SSM_S01_Page_Form" 
																		    label="serviceDescription" 
																		    value="serviceID"/>	
												</html:select>
											</TD>
										</TR>
										<!-- Plan -->
										<TR>
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S01_Page.TabPanel.ServiceInfoFieldSet.Plan.Text"/>	
											</TD>
											<TD>
												:&nbsp;
												<html:select styleId="coLocationPlanID"
															 name="B_SSM_S01_Page_Form" 
															 property="coLocationPlan"
															 styleClass="searchPlan"
															 style="width:426px"
															 disabled="true">
													<html:option value="">
														<%-- <bean:message key="B_SSM_S01_Page.BlankSelectText"/> --%>
													</html:option>
													<html:optionsCollection property="planCodeList"
																			name="B_SSM_S01_Page_Form" 
																		    label="serviceDescription" 
																		    value="serviceID"/>	
												</html:select>
											</TD>
										</TR>		
										<!-- Plan Detail -->
										<TR>
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S01_Page.TabPanel.ServiceInfoFieldSet.PlanDetail.Text"/>	
											</TD>
											<TD>
												:&nbsp;
												<html:select styleId="coLocationPlanDetailID"
															 name="B_SSM_S01_Page_Form" 
															 property="coLocationPlanDetail"
															 styleClass="searchPlanDetail"
															 style="width:426px"
															 disabled="true">
													<html:option value="">
														<%-- <bean:message key="B_SSM_S01_Page.BlankSelectText"/> --%>
													</html:option>
													<html:optionsCollection property="planDetailCodeList"
																			name="B_SSM_S01_Page_Form" 
																		    label="serviceDescription" 
																		    value="serviceID"/>	
												</html:select>
											</TD>
										</TR>		  						
									</TABLE>
								</div>
							</fieldset>
						</div>
					</TD>
				</TR>
				<TR>
					<!-- Colocation Detail Info -->	
					<TD class="B_SSM_S01_Page_Form_Table_Col">
						<div>
							<fieldset class="B_SSM_S01_Page_Form_FieldSet" style="height:160px;">
								<legend class="B_SSM_S01_Page_Form_FieldSet_Legend">
									<bean:message key="B_SSM_S01_Page.TabPanel.CoLocationDetailInfoFieldSet.Legend"/>
								</legend>
								<div class="B_SSM_S01_Page_Form_FieldSet_PanelContent" style="width: 440px;">
									<TABLE width="100%">
									    <!-- Equipment Location -->
										<TR>
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S01_Page.TabPanel.CoLocationDetailInfoFieldSet.EquipmentLocation.Text"/>	
											</TD>
											<TD>
												:&nbsp;
												<html:select styleClass="B_SSM_S01_Page_Form_TextBox"  
															 styleId="coLocationEquipmentLocationID"
															 name="B_SSM_S01_Page_Form" 
															 property="coLocationEquipmentLocation">
													<html:option value="">
														<bean:message key="B_SSM_S01_Page.BlankSelectText"/>
													</html:option>
													<html:optionsCollection property="equipmentLocationCodeList"
																			name="B_SSM_S01_Page_Form" 
																		    label="equipmentLocation" 
																		    value="equipmentLocationID"/>
												</html:select>
											</TD>
										</TR>
										<!-- Equipment Suite -->
										<TR>
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S01_Page.TabPanel.CoLocationDetailInfoFieldSet.EquipmentSuite.Text"/>	
											</TD>
											<TD>
												:&nbsp;
												<html:text  styleClass="B_SSM_S01_Page_Form_TextBox" 
															styleId="coLocationEquipmentSuiteID"
														    name="B_SSM_S01_Page_Form" 
														    property="coLocationEquipmentSuite"/>
											</TD>
										</TR>
										<!-- Rack No-->
										<TR>
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S01_Page.TabPanel.CoLocationDetailInfoFieldSet.RackNo.Text"/>	
											</TD>
											<TD>
												:&nbsp;
												<html:text  styleClass="B_SSM_S01_Page_Form_TextBox" 
															styleId="coLocationRackNoID" 
															name="B_SSM_S01_Page_Form" 
															property="coLocationRackNo"/>
											</TD>
										</TR>
										<%--
										<!-- Power committed -->
										<TR>
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S01_Page.TabPanel.CoLocationDetailInfoFieldSet.PowerCommitted.Text"/>	
											</TD>
											<TD>
												:&nbsp;
												<html:text  styleClass="B_SSM_S01_Page_Form_TextBox" 
															styleId="coLocationPowerCommittedID"
														    name="B_SSM_S01_Page_Form" 
														    property="coLocationPowerCommitted"/>
											</TD>
										</TR>
										--%>
										<!-- Search by detail -->
										<TR>
											<TD>
												
											</TD>
											<TD>
												 &nbsp;																									
												 <html:checkbox name="B_SSM_S01_Page_Form" 
												 				styleId="coLocationSearchByDetailsID"
												 				property="coLocationSearchByDetails" 
												 				value="true">
												 	<bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.SearchByDetails.Text"/>
												 </html:checkbox>
											</TD>
										</TR>
									</TABLE>
								</div>
							</fieldset>
						</div>
					</TD>
					<!-- Customer Plan Info -->	
					<TD class="B_SSM_S01_Page_Form_Table_Col">
						<div>
							<fieldset class="B_SSM_S01_Page_Form_FieldSet" style="height:160px;">
								<legend class="B_SSM_S01_Page_Form_FieldSet_Legend">
									<bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.Legend"/>
								</legend>
								<div class="B_SSM_S01_Page_Form_FieldSet_PanelContent" style="width: 600px;">
									<TABLE width="100%">
										<!-- Subscription ID-->
										<TR>
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.SubscriptionID.Text"/>	
											</TD>
											<TD>
												:&nbsp;
												<html:text  styleClass="B_SSM_S01_Page_Form_TextBox"  
															styleId="coLocationSubscriptionIDText"
															name="B_SSM_S01_Page_Form" 
															property="coLocationSubscriptionID"/>
											</TD>
										</TR>
										<!-- Service status -->
										<TR>
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol_UpVAlign">
												<bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.ServiceStatus.Text"/>	
											</TD>
											<TD>
												<div style="vertical-align: top; float: left; width: 1px;">
													:&nbsp;
												</div>
												<div style="vertical-align: top;">
													<TABLE>	
														<TR>	
															<TD style="vertical-align: top;">
																 <html:checkbox name="B_SSM_S01_Page_Form" 
																 				styleId="coLocationDraftServiceStatusID"
																 				property="coLocationDraftServiceStatus" 
																 				value="PS1">
																 	<bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.DraftServiceStatus.Text"/>
																 </html:checkbox>
															</TD>
                                                             <TD style="vertical-align: top;" nowrap="nowrap">
                                                                 <html:checkbox name="B_SSM_S01_Page_Form" 
                                                                                styleId="coLocationCancelledServiceStatusID"
                                                                                property="coLocationCancelledServiceStatus" 
                                                                                value="PS0">
                                                                    <bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.CancelledServiceStatus.Text"/>
                                                                 </html:checkbox>
                                                             </TD>
                                                             <TD style="vertical-align: top;">
															     <logic:equal name="B_SSM_S01_Page_Form" property="bifIsDisplay" value="1">
																	 <html:checkbox name="B_SSM_S01_Page_Form" 
																	 				styleId="coLocationPendingApprovalServiceStatusID"
																	 				property="coLocationPendingApprovalServiceStatus"
																	 				value="PS2">
																	 	<bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.PendingServiceStatus.Text"/>
																	 </html:checkbox>
															     </logic:equal>
                                                             </TD>
														 </TR>
														 <TR>
                                                             <TD style="vertical-align: top;">
                                                                 <html:checkbox name="B_SSM_S01_Page_Form" 
                                                                                styleId="coLocationActiveServiceStatusID"
                                                                                property="coLocationActiveServiceStatus" 
                                                                                value="PS3">
                                                                    <bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.ActiveServiceStatus.Text"/>
                                                                 </html:checkbox>
                                                             </TD>
														 	 <TD style="vertical-align: top;">
																 <html:checkbox   name="B_SSM_S01_Page_Form" 
																 						styleId="coLocationTerminatedServiceStatusID"
																 						property="coLocationTerminatedServiceStatus" 
																 						value="PS7">
																 	<bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.TerminatedServiceStatus.Text"/>
																 </html:checkbox>
															 </TD>
															 <TD style="vertical-align: top;">
																 <html:checkbox name="B_SSM_S01_Page_Form" 
																 				styleId="coLocationSuspendedServiceStatusID"
																 				property="coLocationSuspendedServiceStatus" 
																 				value="PS6">
																 	<bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.SuspendedServiceStatus.Text"/>
																 </html:checkbox>
															 </TD>
														 </TR>
                                                         <logic:equal name="B_SSM_S01_Page_Form" property="bifIsDisplay" value="1">
                                                            <TR>
                                                                 <TD style="vertical-align: top;">
                                                                     <html:checkbox name="B_SSM_S01_Page_Form" 
                                                                                    styleId="coLocationRejectedServiceStatusID"
                                                                                    property="coLocationRejectedServiceStatus" 
                                                                                    value="PS8">
                                                                        <bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.RejectedServiceStatus.Text"/>
                                                                     </html:checkbox>
                                                                 </TD>
                                                                 <TD></TD>
                                                                 <TD></TD>
                                                            </TR>
                                                         </logic:equal>
													 </TABLE>
												</div>
											</TD>
										</TR>
										<%--
										<!-- Billing Instruction -->
										<TR>
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.BillingInstruction.Text"/>	
											</TD>
											<TD>
												:&nbsp;
												<t:defineCodeList id="billingInstructionCodeList"></t:defineCodeList>
												<html:select styleClass="B_SSM_S01_Page_Form_SelectBox" 
												 			 styleId="coLocationBillingInstructionID"
															 name="B_SSM_S01_Page_Form" 
															 property="coLocationBillingInstruction">
													<html:option value="">
														<bean:message key="B_SSM_S01_Page.BlankSelectText"/>
													</html:option>
													<html:optionsCollection name="billingInstructionCodeList" label="name" value="id"/>
												</html:select>
											</TD>
										</TR>		
										<!-- Service date start -->
										<TR>
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.ServiceDate.Text"/>	
											</TD>
											<TD>
												:&nbsp;
												<html:text styleClass="B_SSM_S01_Page_Form_TextBox_Calendar" 
														   styleId="coLocationFromServiceDateID"  
														   name="B_SSM_S01_Page_Form" 
														   property="coLocationFromServiceDate" 
														   maxlength="10" 
														   readonly="true"
														   size="15" />												
												<t:inputCalendar for="coLocationFromServiceDate" format="dd/MM/yyyy"/>
												-
												<html:text styleClass="B_SSM_S01_Page_Form_TextBox_Calendar" 
														   name="B_SSM_S01_Page_Form" 
														   styleId="coLocationToServiceDateID" 
														   property="coLocationToServiceDate"
														   maxlength="10" 
														   readonly="true"
														   size="15" />												
												<t:inputCalendar for="coLocationToServiceDate" format="dd/MM/yyyy"/>
											</TD>
										</TR>
										<!-- Service date end -->
										<TR>
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.ServiceEndDate.Text"/>	
											</TD>
											<TD>
												:&nbsp;
												<html:text styleClass="B_SSM_S01_Page_Form_TextBox_Calendar" 
														   styleId="coLocationEndFromServiceDateID"  
														   name="B_SSM_S01_Page_Form" 
														   property="coLocationEndFromServiceDate" 
														   maxlength="10" 
														   readonly="true"
														   size="15" />												
												<t:inputCalendar for="coLocationEndFromServiceDate" format="dd/MM/yyyy"/>
												-
												<html:text styleClass="B_SSM_S01_Page_Form_TextBox_Calendar" 
														   name="B_SSM_S01_Page_Form" 
														   styleId="coLocationEndToServiceDateID" 
														   property="coLocationEndToServiceDate"
														   maxlength="10" 
														   readonly="true"
														   size="15" />												
												<t:inputCalendar for="coLocationEndToServiceDate" format="dd/MM/yyyy"/>
											</TD>
										</TR>	
										<!-- Contract term -->
										<TR>
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.ContractTerm.Text"/>	
											</TD>
											<TD>
												:&nbsp;
												<html:radio name="B_SSM_S01_Page_Form" 
															styleId="coLocationContractTermMode_MID"
															property="coLocationContractTermMode" 
															value="M" 																
															onclick="addValidatorToTextBox('coLocationTimeContractTermID', 'M', 1)">
													<bean:message key="B_SSM_S01_Page.TabPanel.ContractTerm.Month.Text"/>
												 </html:radio>
												 <html:radio name="B_SSM_S01_Page_Form" 
												 			 styleId="coLocationContractTermMode_YID"
												 			 property="coLocationContractTermMode" 
												 			 value="Y"
												 			 onclick="addValidatorToTextBox('coLocationTimeContractTermID', 'Y', 1)">
													<bean:message key="B_SSM_S01_Page.TabPanel.ContractTerm.Year.Text"/>
												 </html:radio>
												 &nbsp;
												 <bean:define id="pageForm" 
												 			  name="B_SSM_S01_Page_Form" 
												 			  type="jp.terasoluna.fw.web.struts.form.DynaValidatorActionFormEx"/>
												 <% 
												 	String coLocationTimeContractTermStr = BLogicUtils.emptyValue(pageForm.get("coLocationTimeContractTerm"), "");
													String coLocationTermMode = BLogicUtils.emptyValue(pageForm.get("coLocationContractTermMode"), ""); 
												 %>
												 <input type="text" 
												 		name="coLocationTimeContractTerm" 
											 			onkeypress="return checkCoLocationTimeContractTerm(this);"
											 			onchange="doChangeCoLocationTimeContractTerm(this);"
											 			id="coLocationTimeContractTermID"
											 			class="B_SSM_S01_Page_Form_TextBox_Calendar"
											 			<%if (coLocationTermMode.trim().equals("")) { %>
											 				readonly= "readonly"
											 			<%} %>
											 			value="<%=coLocationTimeContractTermStr %>"/>												   
											</TD>
										</TR>
										--%>
									</TABLE>
								</div>
							</fieldset>	
						</div>		  				
					</TD>
				</TR>
			</TABLE>
	  	</div>
  	</div>
</body>
</html>