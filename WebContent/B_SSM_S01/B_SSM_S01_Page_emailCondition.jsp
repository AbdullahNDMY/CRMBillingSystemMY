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
		<html:hidden property="emailService" styleId="hiddenEmailService"/>
		<html:hidden property="emailPlan" styleId="hiddenEmailPlan"/>
		<html:hidden property="emailPlanDetail" styleId="hiddenEmailPlanDetail"/>
		<!-- #180 End -->						
		<!--/////////////////// Email Tab Panel ////////////////////////-->
	  	<div id="emailTab" class="B_SSM_S01_Page_Form_Tabs_TabPanel">
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
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol" style="width: 180px;">
												<bean:message key="B_SSM_S01_Page.TabPanel.CustomerInfoFieldSet.CustomerID.Text"/>	
											</TD>
											<TD>
												:&nbsp;
												<html:text styleClass="B_SSM_S01_Page_Form_TextBox" 
														   styleId="emailCustomerIDText"
														   name="B_SSM_S01_Page_Form" 
														   property="emailCustomerID"/>
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
														   styleId="emailCustomerNameID"
														   name="B_SSM_S01_Page_Form" 
														   property="emailCustomerName"/>
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
															 styleId="emailCustomerTypeID" 
															 name="B_SSM_S01_Page_Form" 
															 property="emailCustomerType">
													<html:option value="">
														<bean:message key="B_SSM_S01_Page.BlankSelectText"/>
													</html:option>
													<html:optionsCollection name="customerTypeCodeList" label="name" value="id"/>
												</html:select>													
											</TD>
										</TR>
										<%--
										<!-- Customer Telephone -->
										<TR>
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S01_Page.TabPanel.CustomerInfoFieldSet.CustomerTelephone.Text"/>	
											</TD>
											<TD>
												:&nbsp;
												<html:text  styleClass="B_SSM_S01_Page_Form_TextBox" 
															styleId="emailCustomerTelephoneID"
															name="B_SSM_S01_Page_Form" 
															property="emailCustomerTelephone"/>
											</TD>
										</TR>	
										--%>		  						
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
															 styleId="emailCategoryID"
															 name="B_SSM_S01_Page_Form"
															 property="emailCategory"
															 onchange="activateB_SSM_S01_EmailSelectBox();"
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
												<html:select styleId="emailServiceID"
															 name="B_SSM_S01_Page_Form" 
															 property="emailService"
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
												<html:select styleId="emailPlanID"
															 name="B_SSM_S01_Page_Form" 
															 property="emailPlan"
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
												<html:select styleId="emailPlanDetailID"
															 name="B_SSM_S01_Page_Form" 
															 property="emailPlanDetail"
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
					<!-- Email Info -->	
					<TD class="B_SSM_S01_Page_Form_Table_Col">
						<div>
							<fieldset class="B_SSM_S01_Page_Form_FieldSet">
								<legend class="B_SSM_S01_Page_Form_FieldSet_Legend">
									<bean:message key="B_SSM_S01_Page.TabPanel.EmailInfoFieldSet.Legend"/>
								</legend>
								<div class="B_SSM_S01_Page_Form_FieldSet_PanelContent" style="width: 440px;">
									<TABLE width="100%">
									
									    <!-- Email mode -->
									    <TR>										    	
									    	<TD>
									    		<!-- Subscribed mode -->
										    	<html:radio name="B_SSM_S01_Page_Form" 
										    				styleId="emailSubscribedInfoModeID"
															property="emailInfoMode" 
															value="emailSubscribedMode" 
															onclick="activateEmailSubscribedModeFields();">
													<bean:message key="B_SSM_S01_Page.TabPanel.EmailInfoFieldSet.EmailInfo.Text"/>												
												 </html:radio>
											</TD>
											
											<TD style="vertical-align: top;">
												<!-- Teamwork -->
												 <html:radio name="B_SSM_S01_Page_Form" 
												 			 styleId="emailTeamworkInfoModeID"
												 			 property="emailInfoMode" 
												 			 value="emailDomainNameMode"
												 			 onclick="activateTeamworkModeFields();"> 
													<bean:message key="B_SSM_S01_Page.TabPanel.EmailInfoFieldSet.Teamwork.Text"/>
												 </html:radio>
												 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												 <html:checkbox name="B_SSM_S01_Page_Form" 
												 				styleId="detetedEmailID"
												 				property="detetedEmail" 
												 				value="0">
												 	<bean:message key="B_SSM_S01_Page.TabPanel.EamilInfoFieldSet.deletedEmail.Text"/>
												 </html:checkbox>
												 <%-- 
												 &nbsp;									
												 <!-- IT contact mode -->												 
												 <html:radio name="B_SSM_S01_Page_Form" 
												 			 styleId="emailITContactInfoModeID"
												 			 property="emailInfoMode" 
												 			 value="emailITContactMode"
												 			 onclick="activateEmailITContactModeFields();">
													<bean:message key="B_SSM_S01_Page.TabPanel.EmailInfoFieldSet.ITContact.Text"/>
												 </html:radio>
												 --%>
											 </TD>
									    </TR>
									    <!-- Domain Name Text-->
										<TR>
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S01_Page.TabPanel.EmailInfoFieldSet.DomainName.Text"/>	
											</TD>
											<TD>
												:&nbsp;
												<html:text styleClass="B_SSM_S01_Page_Form_TextBox" 
														   styleId="emailDomainNameTextID" 
														   disabled="true"
														   name="B_SSM_S01_Page_Form" 
														   property="emailDomainNameText"/>
											</TD>
										</TR>
									    <!-- Subscribed Email Address Text-->
										<TR>
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol" style="width: 180px;">
												<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.SubscribedEmailAddress.Text"/>	
											</TD>
											<TD>
												:&nbsp;
												<html:text  styleClass="B_SSM_S01_Page_Form_TextBox"  
															styleId="emailSubscribedAddressTextID"
															name="B_SSM_S01_Page_Form" 
															disabled="true"
															property="emailSubscribedAddressText"/>
											</TD>
										</TR>
										<!-- Teamwork URL -->
										<TR>
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S01_Page.TabPanel.EamilInfoFieldSet.TeamworkUrl.Text"/>	
											</TD>
											<TD>
												:&nbsp;
												<html:text styleClass="B_SSM_S01_Page_Form_TextBox"  
														   styleId="emailTeamworkUrlTextID"
														   disabled="true"
														   name="B_SSM_S01_Page_Form" 
														   property="emailTeamworkUrlText"/>
											</TD>
										</TR>
										<!-- Teamwork Admin ID -->
										<TR>
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S01_Page.TabPanel.EamilInfoFieldSet.AdminId.Text"/>	
											</TD>
											<TD>
												:&nbsp;
												<html:text  styleClass="B_SSM_S01_Page_Form_TextBox"  
															styleId="emailTeamworkAdminIDTextID"
															disabled="true"
															name="B_SSM_S01_Page_Form" 
															property="emailTeamworkAdminIDText"/>
											</TD>
										</TR>
										<!-- Teamwork Email Domain Address -->
										<TR>
											<TD class="B_SSM_S01_Page_Form_Table_InfoKeyCol">
												<bean:message key="B_SSM_S01_Page.TabPanel.EamilInfoFieldSet.emailDomainAdrs.Text"/>	
											</TD>
											<TD>
												:&nbsp;
												<html:text  styleClass="B_SSM_S01_Page_Form_TextBox"  
															styleId="emailTeamworkAdressTextID"
															disabled="true"
															name="B_SSM_S01_Page_Form" 
															property="emailTeamworkAdressText"/>
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
															styleId="emailSubscriptionIDText" 
															name="B_SSM_S01_Page_Form" 
															property="emailSubscriptionID"/>
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
																 				styleId="emailDraftServiceStatusID"
																 				property="emailDraftServiceStatus" 
																 				value="PS1">
																 	<bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.DraftServiceStatus.Text"/>
																 </html:checkbox>
															</TD>
                                                             <TD style="vertical-align: top;" nowrap="nowrap">
                                                                 <html:checkbox name="B_SSM_S01_Page_Form" 
                                                                                styleId="emailCancelledServiceStatusID"
                                                                                property="emailCancelledServiceStatus" 
                                                                                value="PS0">
                                                                    <bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.CancelledServiceStatus.Text"/>
                                                                 </html:checkbox>
                                                             </TD>
                                                             <TD style="vertical-align: top;">
															     <logic:equal name="B_SSM_S01_Page_Form" property="bifIsDisplay" value="1">
																	 <html:checkbox name="B_SSM_S01_Page_Form" 
																	 				styleId="emailPendingApprovalServiceStatusID"
																	 				property="emailPendingApprovalServiceStatus" 
																	 				value="PS2">
																	 	<bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.PendingServiceStatus.Text"/>
																	 </html:checkbox>
															     </logic:equal>
                                                             </TD>
														</TR>
														<TR>
                                                             <TD style="vertical-align: top;">
                                                                 <html:checkbox name="B_SSM_S01_Page_Form" 
                                                                                styleId="emailActiveServiceStatusID"
                                                                                property="emailActiveServiceStatus" 
                                                                                value="PS3">
                                                                    <bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.ActiveServiceStatus.Text"/>
                                                                 </html:checkbox>
                                                             </TD>
															<TD style="vertical-align: top;">
																 <html:checkbox name="B_SSM_S01_Page_Form" 
																 				styleId="emailTerminatedServiceStatusID"
																 				property="emailTerminatedServiceStatus" 
																 				value="PS7">
																 	<bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.TerminatedServiceStatus.Text"/>
																 </html:checkbox>
														 	</TD>
															 <TD style="vertical-align: top;">
																 <html:checkbox name="B_SSM_S01_Page_Form" 
																 				styleId="emailSuspendedServiceStatusID"
																 				property="emailSuspendedServiceStatus" 
																 				value="PS6">
																 	<bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.SuspendedServiceStatus.Text"/>
																 </html:checkbox>
															 </TD>
														 </TR>
                                                         <logic:equal name="B_SSM_S01_Page_Form" property="bifIsDisplay" value="1">
                                                            <TR>
                                                                <TD style="vertical-align: top;">
                                                                     <html:checkbox name="B_SSM_S01_Page_Form" 
                                                                                    styleId="emailRejectedServiceStatusID"
                                                                                    property="emailRejectedServiceStatus" 
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
			</TABLE>
	  	</div>
  	</div>
</body>
</html>