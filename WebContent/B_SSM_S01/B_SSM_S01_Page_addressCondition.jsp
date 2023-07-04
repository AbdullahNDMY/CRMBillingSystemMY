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
 		#B_SSM_S01_Page_Content .B_SSM_S01_Page_Form_TextBoxNew {
			width: 300px;
		}		
	</style>		
</head>
<body>
	<div id="B_SSM_S01_Page_Content">
		<t:defineCodeList id="LIST_PLANSTATUS"/>
		<t:defineCodeList id="LIST_COUNTRY"/>
		<!-- #180 Start -->
		<html:hidden property="addressService" styleId="hiddenAddressService"/>
		<html:hidden property="addressPlan" styleId="hiddenAddressPlan"/>
		<html:hidden property="addressPlanDetail" styleId="hiddenAddressPlanDetail"/>
		<!-- #180 End -->		
		<!--/////////////////// Address Tab Panel ////////////////////////-->
	  	<div id="addressTab" class="B_SSM_S01_Page_Form_Tabs_TabPanel">
			<TABLE class="B_SSM_S01_Page_Form_Table">
				<TR>
					<!-- Customer Info -->
					<TD class="B_SSM_S01_Page_Form_Table_Col">
						<div>
							<fieldset class="B_SSM_S01_Page_Form_FieldSet" style="height:140px;">
								<legend class="B_SSM_S01_Page_Form_FieldSet_Legend">
									<bean:message key="B_SSM_S01_Page.TabPanel.CustomerInfoFieldSet.Legend" />			  						
								</legend>
								<div class="B_SSM_S01_Page_Form_FieldSet_PanelContent" style="width: 520px;">
									<TABLE width="100%">
										<!-- Customer ID-->
										<TR>
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S01_Page.TabPanel.CustomerInfoFieldSet.CustomerID.Text"/>	
											</TD>
											<TD>
												:&nbsp;
												<html:text styleClass="B_SSM_S01_Page_Form_TextBox" 
														   styleId="addressCustomerIDText"
														   name="B_SSM_S01_Page_Form" 
														   property="addressCustomerID"/>
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
														   styleId="addressCustomerNameID"
														   name="B_SSM_S01_Page_Form" 
														   property="addressCustomerName"/>
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
																    styleId="addressCustomerTypeID"
																    name="B_SSM_S01_Page_Form" 
																    property="addressCustomerType">
													<html:option value="">
														<bean:message key="B_SSM_S01_Page.BlankSelectText"/>
													</html:option>
													<html:optionsCollection name="customerTypeCodeList" label="name" value="id"/>
												</html:select>
											</TD>
										</TR>
										<!-- #188 Start -->
										<!-- Consumer's  NRIC / Passport No -->
										<TR>
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S01_Page.TabPanel.ContactInfoFieldSet.PassportNo.Text"/>	
											</TD>
											<TD>
												:&nbsp;
												<html:text styleClass="B_SSM_S01_Page_Form_TextBoxNew" 
														   styleId="addressICNoID"
														   name="B_SSM_S01_Page_Form" 
														   property="addressICNo"/>
											</TD>
										</TR>
										<!-- #188 End -->
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
															 styleId="addressCategoryID"
															 name="B_SSM_S01_Page_Form"
															 property="addressCategory"
															 onchange="activateB_SSM_S01_AddressSelectBox();"
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
												<html:select styleId="addressServiceID"
															 name="B_SSM_S01_Page_Form" 
															 property="addressService"
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
												<html:select styleId="addressPlanID"
															 name="B_SSM_S01_Page_Form" 
															 property="addressPlan"
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
												<html:select styleId="addressPlanDetailID"
															 name="B_SSM_S01_Page_Form" 
															 property="addressPlanDetail"
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
					<!-- #188 Start -->
				    <!-- Address Info -->
					<TD class="B_SSM_S01_Page_Form_Table_Col">
						<div>
							<fieldset class="B_SSM_S01_Page_Form_FieldSet">
								<legend class="B_SSM_S01_Page_Form_FieldSet_Legend">
									<bean:message key="B_SSM_S01_Page.TabPanel.AddressInfoFieldSet.Legend" />			  						
								</legend>
								<div class="B_SSM_S01_Page_Form_FieldSet_PanelContent" style="width: 520px;">
									<TABLE width="100%">
										<TR>
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol_UpVAlign">
												<bean:message key="B_SSM_S01_Page.TabPanel.CustomerInfoFieldSet.AddressType.Text"/>	
											</TD>
											<TD>
												<div style="vertical-align: top; float: left; width: 1px;">
													:&nbsp;
												</div>
												<div style="vertical-align: top;">
													<TABLE>
														<TR>
															<TD>
																<!-- Registered Type -->																							
																 <html:checkbox name="B_SSM_S01_Page_Form" 
																 				styleId="addressRegisteredTypeID"
																 				property="addressRegisteredType" 
																 				value="RA" >
																 	<bean:message key="B_SSM_S01_Page.TabPanel.ContactInfoFieldSet.AddressType.Registered.Check"/>
																 </html:checkbox>
															 </TD>
															 <TD>
																 <!-- Billing Address 1/2/3/4 Type -->	
																 <html:checkbox name="B_SSM_S01_Page_Form" 
																 				styleId="addressBillingTypeID"
																 				property="addressBillingType" 
																 				value="BA">
																 	<bean:message key="B_SSM_S01_Page.TabPanel.ContactInfoFieldSet.AddressType.BillingAddress.Check"/>
																 </html:checkbox>	
															 </TD>																										
														 </TR>	
														 <TR>	
														 	 <TD>											
																 <!-- Correspondence Type -->																	  
																 <html:checkbox name="B_SSM_S01_Page_Form" 
																  				 styleId="addressCorrespondenceTypeID"
																  				 property="addressCorrespondenceType" 
																  				 value="CA">
																 	<bean:message key="B_SSM_S01_Page.TabPanel.ContactInfoFieldSet.AddressType.Correspondence.Check"/>
																 </html:checkbox>
															 </TD>
															 <TD>
																 <!-- Technical Address Type -->	
																 <html:checkbox name="B_SSM_S01_Page_Form" 
																 				styleId="addressTechnicalTypeID"
																 				property="addressTechnicalType" 
																 				value="TA">
																 	<bean:message key="B_SSM_S01_Page.TabPanel.ContactInfoFieldSet.AddressType.TechnicalAddress.Check"/>
																 </html:checkbox>	
															 </TD>
														</TR>
													</TABLE>	
												</div>											 
											</TD>
										</TR>
										<!-- Address -->
										<TR>
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S01_Page.TabPanel.CustomerInfoFieldSet.Address.Text"/>	
											</TD>
											<TD>
												:&nbsp;
												<html:text  styleClass="B_SSM_S01_Page_Form_TextBoxNew" 
															styleId="addressTextID"
															name="B_SSM_S01_Page_Form" 
															property="addressText"/>
											</TD>
										</TR>
										<!-- Country -->
										<TR>
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S01_Page.TabPanel.AddressFieldSet.Country.Text"/>	
											</TD>
											<TD>
												:&nbsp;
												<html:select styleId="addressCountryID"
																    name="B_SSM_S01_Page_Form" 
																    property="addressCountry" style="width:304px;">
													<html:option value="" ><bean:message key="B_SSM_S01_Page.BlankSelectText"/></html:option>
													<html:options collection="LIST_COUNTRY" property="id" labelProperty="name"/>
												</html:select>
											</TD>
										</TR>
										<!-- Postal code -->
										<TR>
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S01_Page.TabPanel.CustomerInfoFieldSet.PostalCode.Text"/>	
											</TD>
											<TD>
												:&nbsp;
												<html:text  styleClass="B_SSM_S01_Page_Form_TextBoxNew" 
															styleId="addressPostalCodeID"
															name="B_SSM_S01_Page_Form" 
															property="addressPostalCode"/>
											</TD>
										</TR>	
									</TABLE>
								</div>
							</fieldset>
						</div>						
					</TD>
					<TD class="B_SSM_S01_Page_Form_Table_Col">
					    <!-- Customer Plan Info -->							
						<div>
							<fieldset class="B_SSM_S01_Page_Form_FieldSet">
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
															styleId="addressSubscriptionIDText" 
															name="B_SSM_S01_Page_Form" 
															property="addressSubscriptionID"/>
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
																 				styleId="addressDraftServiceStatusID"
																 				property="addressDraftServiceStatus" 
																 				value="PS1">
																 	<bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.DraftServiceStatus.Text"/>
																 </html:checkbox>
															</TD>
                                                            <TD style="vertical-align: top;" nowrap="nowrap">
                                                                 <html:checkbox name="B_SSM_S01_Page_Form" 
                                                                                styleId="addressCancelledServiceStatusID"
                                                                                property="addressCancelledServiceStatus" 
                                                                                value="PS0">
                                                                    <bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.CancelledServiceStatus.Text"/>
                                                                </html:checkbox>
                                                            </TD>
                                                            <TD style="vertical-align: top;">
															    <logic:equal name="B_SSM_S01_Page_Form" property="bifIsDisplay" value="1">
																	 <html:checkbox name="B_SSM_S01_Page_Form" 
																	 				styleId="addressPendingApprovalServiceStatusID"
																	 				property="addressPendingApprovalServiceStatus" 
																	 				value="PS2">
																	 	<bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.PendingServiceStatus.Text"/>
																	 </html:checkbox>
															     </logic:equal>
                                                            </TD>
														</TR>
														<TR>
                                                            <TD style="vertical-align: top;">
                                                                 <html:checkbox name="B_SSM_S01_Page_Form" 
                                                                                styleId="addressActiveServiceStatusID"
                                                                                property="addressActiveServiceStatus" 
                                                                                value="PS3">
                                                                    <bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.ActiveServiceStatus.Text"/>
                                                                 </html:checkbox>
                                                            </TD>
															<TD style="vertical-align: top;">
																 <html:checkbox name="B_SSM_S01_Page_Form" 
																 					  styleId="addressTerminatedServiceStatusID"
																 					  property="addressTerminatedServiceStatus" 
																 					  value="PS7">
																 	<bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.TerminatedServiceStatus.Text"/>
																 </html:checkbox>
															 </TD>
															 <TD style="vertical-align: top;">
																 <html:checkbox name="B_SSM_S01_Page_Form" 
																 				styleId="addressSuspendedServiceStatusID"
																 				property="addressSuspendedServiceStatus" 
																 				value="PS6">
																 	<bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.SuspendedServiceStatus.Text"/>
																 </html:checkbox>
															 </TD>
														 </TR>
                                                         <logic:equal name="B_SSM_S01_Page_Form" property="bifIsDisplay" value="1">
                                                            <TR>
                                                                 <TD style="vertical-align: top;">
                                                                     <html:checkbox name="B_SSM_S01_Page_Form" 
                                                                                    styleId="addressRejectedServiceStatusID"
                                                                                    property="addressRejectedServiceStatus" 
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
								</div>
							</fieldset>	
						</div>
					</TD>
				</TR>
				<TR>
					<TD class="B_SSM_S01_Page_Form_Table_Col" colspan="2">
						<div>
							<fieldset class="B_SSM_S01_Page_Form_FieldSet">
								<legend class="B_SSM_S01_Page_Form_FieldSet_Legend">
									<bean:message key="B_SSM_S01_Page.TabPanel.ContactInfoFieldSet.Legend" />			  						
								</legend>
								<TABLE width="100%">
									<!-- Contact Type -->
									<TR>
										<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol_UpVAlign" width="13%">
											<bean:message key="B_SSM_S01_Page.TabPanel.ContactInfoFieldSet.ContactType"/>	
										</TD>
										<!-- Field: Contact Type -->
										<TD valign="top" width="35%">
												<div style="vertical-align: top; float: left; width: 1px;">:&nbsp;</div>
												<TABLE>
													<TR>
														<TD>
															<!-- General Type -->																							
															 <html:checkbox name="B_SSM_S01_Page_Form" 
															 				styleId="addressContactGeneralTypeID"
															 				property="addressContactGeneralType" 
															 				value="GC" >
															 	<bean:message key="B_SSM_S01_Page.TabPanel.ContactInfoFieldSet.ContactType.General.Check"/>
															 </html:checkbox>
														 </TD>
														 <TD>
															 <!-- Billing Contact 1/2/3/4 Type -->	
															 <html:checkbox name="B_SSM_S01_Page_Form" 
															 				styleId="addressContactBillingTypeID"
															 				property="addressContactBillingType" 
															 				value="BC">
															 	<bean:message key="B_SSM_S01_Page.TabPanel.ContactInfoFieldSet.ContactType.BillingContact.Check"/>
															 </html:checkbox>	
														 </TD>																										
													 </TR>
													 <TR>
														<TD>
															<!-- Primary Type -->																							
															 <html:checkbox name="B_SSM_S01_Page_Form" 
															 				styleId="addressContactPrimaryTypeID"
															 				property="addressContactPrimaryType" 
															 				value="PC" >
															 	<bean:message key="B_SSM_S01_Page.TabPanel.ContactInfoFieldSet.ContactType.PrimaryContact.Check"/>
															 </html:checkbox>
														 </TD>
														 <TD>
															 <!-- Technical Type -->	
															 <html:checkbox name="B_SSM_S01_Page_Form" 
															 				styleId="addressContactTechnicalTypeID"
															 				property="addressContactTechnicalType" 
															 				value="TC">
															 	<bean:message key="B_SSM_S01_Page.TabPanel.ContactInfoFieldSet.ContactType.TechnicalContact.Check"/>
															 </html:checkbox>	
														 </TD>																										
													 </TR>	
													 <TR>
													 	<TD>
															 <!-- Ohter Type -->	
															 <html:checkbox name="B_SSM_S01_Page_Form" 
															 				styleId="addressContactOtherTypeID"
															 				property="addressContactOtherType" 
															 				value="OC">
															 	<bean:message key="B_SSM_S01_Page.TabPanel.ContactInfoFieldSet.ContactType.OtherContact.Check"/>
															 </html:checkbox>	
														 </TD>	
													 	 <TD>											
															 <!-- IT Contact 1/2/3 Type -->																	  
															 <html:checkbox name="B_SSM_S01_Page_Form" 
															  				 styleId="addressContactITTypeID"
															  				 property="addressContactITType" 
															  				 value="ITC">
															 	<bean:message key="B_SSM_S01_Page.TabPanel.ContactInfoFieldSet.ContactType.ITContact.Check"/>
															 </html:checkbox>
														 </TD>
													</TR>
												</TABLE>											 
										</TD>
										<!-- Field: Name ~ Email -->
										<TD width="52%" colspan="1" rowspan="2">
											<TABLE width="100%">
												<!-- Name -->
												<TR>
													<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol" width="20%">
														<bean:message key="B_SSM_S01_Page.TabPanel.ContactInfoFieldSet.Name.Text"/>	
													</TD>
													<TD width="80%">
														:&nbsp;
														<html:text  styleClass="B_SSM_S01_Page_Form_TextBoxNew" 
																	styleId="addressITContactNoNameID"
																	name="B_SSM_S01_Page_Form" 
																	property="addressITContactNoName"/>
													</TD>
												</TR>
												<!-- Telephone No. -->
												<TR>
													<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol" width="20%">
														<bean:message key="B_SSM_S01_Page.TabPanel.ContactInfoFieldSet.TelephoneNo.Text"/>	
													</TD>
													<TD width="80%">
														:&nbsp;
														<html:text  styleClass="B_SSM_S01_Page_Form_TextBoxNew" 
																	styleId="addressITContactNoTelNoID"
																	name="B_SSM_S01_Page_Form" 
																	property="addressITContactNoTelNo"/>
													</TD>
												</TR>
												<!-- Fax No. -->
												<TR>
													<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol" width="20%">
														<bean:message key="B_SSM_S01_Page.TabPanel.ContactInfoFieldSet.FaxNo.Text"/>	
													</TD>
													<TD width="80%">
														:&nbsp;
														<html:text  styleClass="B_SSM_S01_Page_Form_TextBoxNew" 
																	styleId="addressCustomerFaxNoID"
																	name="B_SSM_S01_Page_Form" 
																	property="addressCustomerFaxNo"/>
													</TD>
												</TR>
												<!-- Email -->
												<TR>
													<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol" width="20%">
														<bean:message key="B_SSM_S01_Page.TabPanel.ContactInfoFieldSet.Email.Text"/>	
													</TD>
													<TD width="80%">
														:&nbsp;
														<html:text  styleClass="B_SSM_S01_Page_Form_TextBoxNew" 
																	styleId="addressITContactNoEmailID"
																	name="B_SSM_S01_Page_Form" 
																	property="addressITContactNoEmail"/>
													</TD>
												</TR>
											</TABLE>
										</TD>
									</TR>
									<TR>
										<TD colspan="2" rowspan="1" style="color: blue;">Export result will export all columns for address type and contact type.</TD>
									</TR>
								</TABLE>
							</fieldset>
						</div>
					</TD>
				</TR>
				<!-- #188 End -->
			</TABLE>
	  	</div>
  	</div>
</body>
</html>