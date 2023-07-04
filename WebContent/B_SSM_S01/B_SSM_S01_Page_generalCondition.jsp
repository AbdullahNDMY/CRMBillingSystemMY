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
		<html:hidden property="generalService" styleId="hiddenGeneralService"/>
		<html:hidden property="generalPlan" styleId="hiddenGeneralPlan"/>
		<html:hidden property="generalPlanDetail" styleId="hiddenGeneralPlanDetail"/>
		<!-- #180 End -->			
		<!--/////////////////// General Tab Panel ////////////////////////-->
	  	<div id="generalTab" class="B_SSM_S01_Page_Form_Tabs_TabPanel">
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
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol" style="width:120px;">
												<bean:message key="B_SSM_S01_Page.TabPanel.CustomerInfoFieldSet.CustomerID.Text"/>	
											</TD>
											<TD>
												:&nbsp;
												<html:text styleClass="B_SSM_S01_Page_Form_TextBox" 
														   styleId="generalCustomerIDText"
														   name="B_SSM_S01_Page_Form" 
														   property="generalCustomerID"/>
											</TD>
										</TR>
										<!-- Customer Name -->
										<TR>
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol" style="width:120px;">
												<bean:message key="B_SSM_S01_Page.TabPanel.CustomerInfoFieldSet.CustomerName.Text"/>	
											</TD>
											<TD>
												:&nbsp;
												<html:text styleClass="B_SSM_S01_Page_Form_TextBox" 
														   styleId="generalCustomerNameID"
														   name="B_SSM_S01_Page_Form" 
														   property="generalCustomerName"/>
											</TD>
										</TR>
										<!-- Customer Type -->
										<TR>
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol" style="width:120px;">
												<bean:message key="B_SSM_S01_Page.TabPanel.CustomerInfoFieldSet.CustomerType.Text"/>	
											</TD>
											<TD>
												:&nbsp;
												<t:defineCodeList id="customerTypeCodeList"></t:defineCodeList>	
												
												<% int maxCustTypeLength = 0; %>
												<logic:iterate id="custType" name="customerTypeCodeList">														
													<bean:define id="custTypeStr" name="custType" property="name" type="java.lang.String"/> 
													<% if (custTypeStr!=null && custTypeStr.toString().length() > maxCustTypeLength) {
														
														maxCustTypeLength = custTypeStr.length();															
													} %>
												</logic:iterate>										
												<html:select style="width:<%=maxCustTypeLength%>px;" 
															 styleClass="B_SSM_S01_Page_Form_SelectBox"  
															 styleId="generalCustomerTypeID"
															 name="B_SSM_S01_Page_Form" 
															 property="generalCustomerType">
													<html:option value="">
														<bean:message key="B_SSM_S01_Page.BlankSelectText"/>
													</html:option>
													<html:optionsCollection name="customerTypeCodeList" label="name" value="id"/>
												</html:select>													
											</TD>
										</TR>			  						
									</TABLE>
								</div>
							</fieldset>
						</div>
					</TD>		  					
					<!-- Service Info -->	
					<TD class="B_SSM_S01_Page_Form_Table_Col" style="width:60%">
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
									<TABLE width="100%" style="text-align: left;">
										<!-- Category-->
										<TR>
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol" style="width:20%">
												<bean:message key="B_SSM_S01_Page.TabPanel.ServiceInfoFieldSet.Category.Text"/>	
											</TD>
											<TD style="width: 80%;">
												:&nbsp;																								
												<html:select styleClass="B_SSM_S01_Page_Form_SelectBox"  
															 styleId="generalCategoryID"
															 name="B_SSM_S01_Page_Form"
															 property="generalCategory"
															 onchange="activateB_SSM_S01_GeneralSelectBox();"
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
												<html:select styleId="generalServiceID"
															 name="B_SSM_S01_Page_Form" 
															 property="generalService"
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
												<html:select styleId="generalPlanID"
															 name="B_SSM_S01_Page_Form" 
															 property="generalPlan"
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
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol" nowrap="nowrap">
												<bean:message key="B_SSM_S01_Page.TabPanel.ServiceInfoFieldSet.PlanDetail.Text"/>	
											</TD>
											<TD>
												:&nbsp;
												<html:select styleId="generalPlanDetailID"
															 name="B_SSM_S01_Page_Form" 
															 property="generalPlanDetail"
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
				    <TD class="B_SSM_S01_Page_Form_Table_Col">
				        &nbsp;
				    </TD>
					<TD class="B_SSM_S01_Page_Form_Table_Col">
						<div>
							<fieldset class="B_SSM_S01_Page_Form_FieldSet">
								<legend class="B_SSM_S01_Page_Form_FieldSet_Legend">
									<bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.Legend"/>
								</legend>
								<div>
									<TABLE width="100%" cellpadding="0" border="0" cellspacing="0">
										<TR>
											<TD style="vertical-align: top;">
												<TABLE width="100%" cellpadding="0" border="0" cellspacing="0">
													<TR>
														<!-- Subscription ID -->
														<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol" style="width:120px;">
															<bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.SubscriptionID.Text"/>	
														</TD>
														<TD>
															:&nbsp;
															<html:text  styleClass="B_SSM_S01_Page_Form_TextBox"  
																		styleId="generalSubscriptionIDText"
																		name="B_SSM_S01_Page_Form" 
																		property="generalSubscriptionID"/>
														</TD>
													</TR>
													<%-- 
													<TR>
														<!-- Billing Account  -->
														<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol">
															<bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.billAccount.Text"/>	
														</TD>
														<TD>
															:&nbsp;
															<html:text  styleClass="B_SSM_S01_Page_Form_TextBox"  
																		styleId="generalBillingAccountText"
																		name="B_SSM_S01_Page_Form" 
																		property="generalBillingAccount"/>
														</TD>
													</TR>
													--%>
													<TR>
														<!-- Service status -->
														<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol_UpVAlign" style="width:120px;">
															<bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.ServiceStatus.Text"/>	
														</TD>
														<TD>
															 <div style="vertical-align: top; float: left; width: 1px;">
																:&nbsp;
															 </div>
															 <div style="vertical-align: top;">
																 <TABLE>
																	 <TR>
																	 	 <TD style="vertical-align: top;" nowrap="nowrap">
																			 <html:checkbox name="B_SSM_S01_Page_Form" 
																			 				styleId="generalDraftServiceStatusID"
																			 				property="generalDraftServiceStatus" 
																			 				value="PS1">
																			 	<bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.DraftServiceStatus.Text"/>
																			 </html:checkbox>
																		 </TD>
                                                                         <TD style="vertical-align: top;" nowrap="nowrap">
                                                                             <html:checkbox name="B_SSM_S01_Page_Form" 
                                                                                            styleId="generalCancelledServiceStatusID"
                                                                                            property="generalCancelledServiceStatus" 
                                                                                            value="PS0">
                                                                                <bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.CancelledServiceStatus.Text"/>
                                                                             </html:checkbox>
                                                                         </TD>
                                                                         <TD style="vertical-align: top;" nowrap="nowrap">
																		      <logic:equal name="B_SSM_S01_Page_Form" property="bifIsDisplay" value="1">
																				 <html:checkbox name="B_SSM_S01_Page_Form" 
																				 				styleId="generalPendingApprovalServiceStatusID"
																				 				property="generalPendingApprovalServiceStatus" 
																				 				value="PS2">
																				 	<bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.PendingServiceStatus.Text"/>
																				 </html:checkbox>
																		      </logic:equal>
                                                                         </TD>
																	 </TR>
																	 <TR>
                                                                         <TD style="vertical-align: top;" nowrap="nowrap">
                                                                             <html:checkbox name="B_SSM_S01_Page_Form" 
                                                                                            styleId="generalActiveServiceStatusID"
                                                                                            property="generalActiveServiceStatus" 
                                                                                            value="PS3">
                                                                                <bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.ActiveServiceStatus.Text"/>
                                                                             </html:checkbox>
                                                                         </TD>
																	 	 <TD style="vertical-align: top;" nowrap="nowrap">
																			 <html:checkbox name="B_SSM_S01_Page_Form" 
																			 				styleId="generalTerminatedServiceStatusID"
																			 				property="generalTerminatedServiceStatus" 
																			 				value="PS7">
																			 	<bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.TerminatedServiceStatus.Text"/>
																			 </html:checkbox>
																		 </TD>
																		 <TD style="vertical-align: top;" nowrap="nowrap">	
																			 <html:checkbox name="B_SSM_S01_Page_Form" 
																			 				styleId="generalSuspendedServiceStatusID"
																			 				property="generalSuspendedServiceStatus" 
																			 				value="PS6">
																			 	<bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.SuspendedServiceStatus.Text"/>
																			 </html:checkbox>
																		 </TD>
																	 </TR>
                                                                     <logic:equal name="B_SSM_S01_Page_Form" property="bifIsDisplay" value="1"> 
                                                                        <TR>
                                                                             <TD style="vertical-align: top;" nowrap="nowrap">
                                                                                 <html:checkbox name="B_SSM_S01_Page_Form" 
                                                                                                styleId="generalRejectedServiceStatusID"
                                                                                                property="generalRejectedServiceStatus" 
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
												</TABLE>
											</TD>
											<%--
											<TD style="width:53%;vertical-align: top;">
											    &nbsp;
											    
												<TABLE width="100%" cellpadding="0" border="0" cellspacing="0">
													<TR>
														<!-- Billing Instruction -->
														<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol">
															<bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.BillingInstruction.Text"/>	
														</TD>
														<TD>
															:&nbsp;
															<t:defineCodeList id="billingInstructionCodeList"></t:defineCodeList>
															<html:select styleClass="B_SSM_S01_Page_Form_SelectBox" 
																	 	 styleId="generalBillingInstructionID" 
																		 name="B_SSM_S01_Page_Form" 
																		 property="generalBillingInstruction">
																<html:option value="">
																	<bean:message key="B_SSM_S01_Page.BlankSelectText"/>
																</html:option>
																<html:optionsCollection name="billingInstructionCodeList" label="name" value="id"/>
															</html:select>
														</TD>
													</TR>
													<TR>
														<!-- Service date start -->
														<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol">
															<bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.ServiceDate.Text"/>	
														</TD>
														<TD>
															:&nbsp;
															<html:text styleClass="B_SSM_S01_Page_Form_TextBox_Calendar"   
																	   name="B_SSM_S01_Page_Form" 
																	   property="generalFromServiceDate" 
																	   styleId="generalFromServiceDateID" 
																	   maxlength="10" 
																	   readonly="true"
																	   size="15" />												
															<t:inputCalendar  for="generalFromServiceDate" format="dd/MM/yyyy"/>
															-
															<html:text styleClass="B_SSM_S01_Page_Form_TextBox_Calendar" 
																	   name="B_SSM_S01_Page_Form" 
																	   styleId="generalToServiceDateID" 
																	   property="generalToServiceDate"
																	   maxlength="10" 
																	   readonly="true"
																	   size="15" />												
															<t:inputCalendar for="generalToServiceDate" format="dd/MM/yyyy"/>
														</TD>
													</TR>
													<TR>
														<!-- Service date end -->
														<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol">
															<bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.ServiceEndDate.Text"/>	
														</TD>
														<TD>
															:&nbsp;
															<html:text styleClass="B_SSM_S01_Page_Form_TextBox_Calendar"   
																	   name="B_SSM_S01_Page_Form" 
																	   property="generalEndFromServiceDate" 
																	   styleId="generalEndFromServiceDateID" 
																	   maxlength="10" 
																	   readonly="true"
																	   size="15" />												
															<t:inputCalendar  for="generalEndFromServiceDate" format="dd/MM/yyyy"/>
															-
															<html:text styleClass="B_SSM_S01_Page_Form_TextBox_Calendar" 
																	   name="B_SSM_S01_Page_Form" 
																	   styleId="generalEndToServiceDateID" 
																	   property="generalEndToServiceDate"
																	   maxlength="10" 
																	   readonly="true"
																	   size="15" />												
															<t:inputCalendar for="generalEndToServiceDate" format="dd/MM/yyyy"/>
														</TD>
													</TR>
													<!-- Contract Term -->
													<TR>
														<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol">
															<bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.ContractTerm.Text"/>	
														</TD>
														<TD>
															:&nbsp;
															<html:radio name="B_SSM_S01_Page_Form" 
																		styleId="generalContractTermMode_MID"
																		property="generalContractTermMode" 
																		value="M" 
																		onclick="addValidatorToTextBox('generalTimeContractTermID', 'M', 0)">
																<bean:message key="B_SSM_S01_Page.TabPanel.ContractTerm.Month.Text"/>
															 </html:radio>
															 <html:radio name="B_SSM_S01_Page_Form" 
															 			 styleId="generalContractTermMode_YID"
															 			 property="generalContractTermMode" 
															 			 value="Y"
															 			 onclick="addValidatorToTextBox('generalTimeContractTermID', 'Y', 0)">
																<bean:message key="B_SSM_S01_Page.TabPanel.ContractTerm.Year.Text"/>
															 </html:radio>
															 &nbsp;
															 <bean:define id="pageForm" 
															 			  name="B_SSM_S01_Page_Form" 
															 			  type="jp.terasoluna.fw.web.struts.form.DynaValidatorActionFormEx"/>
															 <% 
															 	String generalTimeContractTermStr = BLogicUtils.emptyValue(pageForm.get("generalTimeContractTerm"), "");
																String generalTermMode = BLogicUtils.emptyValue(pageForm.get("generalContractTermMode"), "");
															 %>
															 <input type="text" 
															 		name="generalTimeContractTerm" 
														 			onkeypress="return checkGeneralTimeContractTerm(this);"
														 			onchange="doChangeGeneralTimeContractTerm(this);"
														 			id="generalTimeContractTermID"
														 			class="B_SSM_S01_Page_Form_TextBox_Calendar"
														 			<%if (generalTermMode.trim().equals("")) { %>
														 				readonly= "readonly"
														 			<%} %>
														 			value="<%=generalTimeContractTermStr %>"/>
														</TD>
													</TR>
												</TABLE>
											</TD>
											--%>
										</TR>
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