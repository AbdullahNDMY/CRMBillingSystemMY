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
		<html:hidden property="service" styleId="hiddenService"/>
		<html:hidden property="plan" styleId="hiddenPlan"/>
		<html:hidden property="planDetail" styleId="hiddenPlanDetail"/>
		<!-- #180 End -->						
		<!--/////////////////// ISP Tab Panel ////////////////////////-->
	  	<div id="iSPTab" class="B_SSM_S01_Page_Form_Tabs_TabPanel">
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
														   styleId="customerIDText"
														   name="B_SSM_S01_Page_Form" 
														   property="customerID"/>
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
														   styleId="customerNameID"
														   name="B_SSM_S01_Page_Form" 
														   property="customerName"/>
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
													<% if (custTypeStr!=null && custTypeStr.toString().length() > maxCustTypeLength) {
														
														maxCustTypeLength = custTypeStr.length();															
													} %>
												</logic:iterate>										
												<html:select style="width:<%=maxCustTypeLength%>px;" 
															 styleClass="B_SSM_S01_Page_Form_SelectBox"  
															 styleId="customerTypeID"
															 name="B_SSM_S01_Page_Form" 
															 property="customerType">
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
															 styleId="categoryID"
															 name="B_SSM_S01_Page_Form"
															 property="category"
															 onchange="activateB_SSM_S01_ISPSelectBox();"
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
												<html:select styleId="serviceID"
															 name="B_SSM_S01_Page_Form" 
															 property="service"
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
												<html:select styleId="planID"
															 name="B_SSM_S01_Page_Form" 
															 property="plan"
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
												<html:select styleId="planDetailID"
															 name="B_SSM_S01_Page_Form" 
															 property="planDetail"
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
					<!-- ISP Detail Info -->	
					<TD class="B_SSM_S01_Page_Form_Table_Col">
						<div>
							<fieldset class="B_SSM_S01_Page_Form_FieldSet" style="height:270px;">
								<legend class="B_SSM_S01_Page_Form_FieldSet_Legend">
									<bean:message key="B_SSM_S01_Page.TabPanel.ISPDetailInfoFieldSet.Legend"/>
								</legend>
								<div class="B_SSM_S01_Page_Form_FieldSet_PanelContent" style="width: 440px;">
									<TABLE width="100%">
										<!-- Access Account -->
										<TR>
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S01_Page.TabPanel.ISPDetailInfoFieldSet.AccessAccount.Text"/>	
											</TD>
											<TD>
												:&nbsp;
												<html:text  styleClass="B_SSM_S01_Page_Form_TextBox"  
															styleId="txtAccessAccountID"
															name="B_SSM_S01_Page_Form" 
															property="txtAccessAccount"/>
											</TD>
										</TR>	
									    <!-- Modem No -->
										<TR>
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S01_Page.TabPanel.ISPDetailInfoFieldSet.ModemNo.Text"/>	
											</TD>
											<TD>
												:&nbsp;
												<html:text  styleClass="B_SSM_S01_Page_Form_TextBox"  
															styleId="modemNoID"
															name="B_SSM_S01_Page_Form" 
															property="modemNo"/>
											</TD>
										</TR>	
										<!-- ADSL/DEL No -->
										<TR>
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S01_Page.TabPanel.ISPDetailInfoFieldSet.ADSL_DelNo.Text"/>	
											</TD>
											<TD>
												:&nbsp;
												<html:text  styleClass="B_SSM_S01_Page_Form_TextBox"
															styleId="aDSL_DelNoID"  
															name="B_SSM_S01_Page_Form" 
															property="aDSL_DelNo"/>
											</TD>
										</TR>
										<!-- Router No -->
										<TR>
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S01_Page.TabPanel.ISPDetailInfoFieldSet.RouterNo.Text"/>	
											</TD>
											<TD>
												:&nbsp;
												<html:text  styleClass="B_SSM_S01_Page_Form_TextBox"  
															styleId="routerNoID"
															name="B_SSM_S01_Page_Form" 
															property="routerNo"/>
											</TD>
										</TR>		
										<!-- Circuit ID -->
										<TR>
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S01_Page.TabPanel.ISPDetailInfoFieldSet.CircuitID.Text"/>	
											</TD>
											<TD>
												:&nbsp;
												<html:text  styleClass="B_SSM_S01_Page_Form_TextBox"  
															styleId="circuitIDText"
															name="B_SSM_S01_Page_Form" 
															property="circuitID"/>
											</TD>
										</TR>		
										<!-- Carrier -->
										<TR>
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S01_Page.TabPanel.ISPDetailInfoFieldSet.Carrier.Text"/>	
											</TD>
											<TD>
												:&nbsp;
												<html:select styleClass="B_SSM_S01_Page_Form_SelectBox"  
															 styleId="carrierID"
															 name="B_SSM_S01_Page_Form" 
															 property="carrier">
													<html:option value="">
														<bean:message key="B_SSM_S01_Page.BlankSelectText"/>
													</html:option>
													<html:option value="selectNullCarrierValue">
														<bean:message key="B_SSM_S01_Page.noCarrier"/>
													</html:option>
													<html:optionsCollection property="carrierCodeList"
																			name="B_SSM_S01_Page_Form" 
																		    label="supplierName" 
																		    value="supplierID"/>	
												</html:select>
											</TD>
										</TR>
										<!-- Installation Address-->
										<TR>
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S01_Page.TabPanel.ISPDetailInfoFieldSet.InstallationAddress.Text"/>	
											</TD>
											<TD>
												:&nbsp;
												<html:text  styleClass="B_SSM_S01_Page_Form_TextBox"  
															styleId="installationAddressID"
															name="B_SSM_S01_Page_Form" 
															property="installationAddress"/>
											</TD>
										</TR>
										<!-- Postal Code -->
										<TR>
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S01_Page.TabPanel.ISPDetailInfoFieldSet.PostalCode.Text"/>	
											</TD>
											<TD>
												:&nbsp;
												<html:text styleClass="B_SSM_S01_Page_Form_TextBox"  
														   styleId="postalCodeID"
														   name="B_SSM_S01_Page_Form" 
														   property="postalCode"/>
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
							<fieldset class="B_SSM_S01_Page_Form_FieldSet" style="height:270px;">
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
															styleId="subscriptionIDText"
															name="B_SSM_S01_Page_Form" 
															property="subscriptionID"/>
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
																 				styleId="draftServiceStatusID"
																 				property="draftServiceStatus" 
																 				value="PS1">
																 	<bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.DraftServiceStatus.Text"/>
																 </html:checkbox>
															 </TD>
                                                            <TD style="vertical-align: top;" nowrap="nowrap">
                                                                 <html:checkbox name="B_SSM_S01_Page_Form" 
                                                                                styleId="cancelledServiceStatusID"
                                                                                property="cancelledServiceStatus" 
                                                                                value="PS0">
                                                                    <bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.CancelledServiceStatus.Text"/>
                                                                </html:checkbox>
                                                            </TD>
                                                             <TD style="vertical-align: top;">
															     <logic:equal name="B_SSM_S01_Page_Form" property="bifIsDisplay" value="1">
																	 <html:checkbox name="B_SSM_S01_Page_Form" 
																	 				styleId="pendingApprovalServiceStatusID"
																	 				property="pendingApprovalServiceStatus" 
																	 				value="PS2">
																	 	<bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.PendingServiceStatus.Text"/>
																	 </html:checkbox>
															     </logic:equal>
                                                             </TD>
														 </TR>
														 <TR>
                                                             <TD style="vertical-align: top;">
                                                                 <html:checkbox name="B_SSM_S01_Page_Form" 
                                                                                styleId="activeServiceStatusID"
                                                                                property="activeServiceStatus" 
                                                                                value="PS3">
                                                                    <bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.ActiveServiceStatus.Text"/>
                                                                 </html:checkbox>
                                                             </TD>
														 	 <TD style="vertical-align: top;">
																 <html:checkbox name="B_SSM_S01_Page_Form" 
																 				styleId="terminatedServiceStatusID"
																 				property="terminatedServiceStatus" 
																 				value="PS7">
																 	<bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.TerminatedServiceStatus.Text"/>
																 </html:checkbox>
															 </TD>
															 <TD style="vertical-align: top;">	
																 <html:checkbox name="B_SSM_S01_Page_Form" 
																 				styleId="suspendedServiceStatusID"
																 				property="suspendedServiceStatus" 
																 				value="PS6">
																 	<bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.SuspendedServiceStatus.Text"/>
																 </html:checkbox>
															 </TD>
														 </TR>
                                                         <logic:equal name="B_SSM_S01_Page_Form" property="bifIsDisplay" value="1">
                                                            <TR> 
                                                                 <TD style="vertical-align: top;">
                                                                     <html:checkbox name="B_SSM_S01_Page_Form" 
                                                                                    styleId="rejectedServiceStatusID"
                                                                                    property="rejectedServiceStatus" 
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
														 	 styleId="billingInstructionID" 
															 name="B_SSM_S01_Page_Form" 
															 property="billingInstruction">
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
														   name="B_SSM_S01_Page_Form" 
														   property="fromServiceDate" 
														   styleId="fromServiceDateID" 
														   maxlength="10" 
														   readonly="true"
														   size="15" />												
												<t:inputCalendar  for="fromServiceDate" format="dd/MM/yyyy"/>
												-
												<html:text styleClass="B_SSM_S01_Page_Form_TextBox_Calendar" 
														   name="B_SSM_S01_Page_Form" 
														   styleId="toServiceDateID" 
														   property="toServiceDate"
														   maxlength="10" 
														   readonly="true"
														   size="15" />												
												<t:inputCalendar for="toServiceDate" format="dd/MM/yyyy"/>
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
														   name="B_SSM_S01_Page_Form" 
														   property="endFromServiceDate" 
														   styleId="endFromServiceDateID" 
														   maxlength="10" 
														   readonly="true"
														   size="15" />												
												<t:inputCalendar  for="endFromServiceDate" format="dd/MM/yyyy"/>
												-
												<html:text styleClass="B_SSM_S01_Page_Form_TextBox_Calendar" 
														   name="B_SSM_S01_Page_Form" 
														   styleId="endToServiceDateID" 
														   property="endToServiceDate"
														   maxlength="10" 
														   readonly="true"
														   size="15" />												
												<t:inputCalendar for="endToServiceDate" format="dd/MM/yyyy"/>
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
															styleId="contractTermMode_MID"
															property="contractTermMode" 
															value="M" 
															onclick="addValidatorToTextBox('timeContractTermID', 'M', 0)">
													<bean:message key="B_SSM_S01_Page.TabPanel.ContractTerm.Month.Text"/>
												 </html:radio>
												 <html:radio name="B_SSM_S01_Page_Form" 
												 			 styleId="contractTermMode_YID"
												 			 property="contractTermMode" 
												 			 value="Y"
												 			 onclick="addValidatorToTextBox('timeContractTermID', 'Y', 0)">
													<bean:message key="B_SSM_S01_Page.TabPanel.ContractTerm.Year.Text"/>
												 </html:radio>
												 &nbsp;
												 <bean:define id="pageForm" 
												 			  name="B_SSM_S01_Page_Form" 
												 			  type="jp.terasoluna.fw.web.struts.form.DynaValidatorActionFormEx"/>
												 <% 
												 	String timeContractTermStr = BLogicUtils.emptyValue(pageForm.get("timeContractTerm"), "");
												 	String termMode = BLogicUtils.emptyValue(pageForm.get("contractTermMode"), "");
												 %>
												 <input type="text" 
												 		name="timeContractTerm" 
											 			onkeypress="return checkTimeContractTerm(this);"
											 			onchange="doChangeTimeContractTerm(this);"
											 			id="timeContractTermID"
											 			class="B_SSM_S01_Page_Form_TextBox_Calendar"
											 			<%if (termMode.trim().equals("")) { %>
											 				readonly= "readonly"
											 			<%} %>
											 			value="<%=timeContractTermStr %>"/>
											 
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