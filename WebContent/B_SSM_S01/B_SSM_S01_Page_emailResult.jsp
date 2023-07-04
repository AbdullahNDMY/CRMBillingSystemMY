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
</head>
<body>
	<div id="B_SSM_S01_Page_Content">
		<t:defineCodeList id="LIST_PLANSTATUS"/>
		<t:defineCodeList id="LIST_CUSTOMERTYPE"/>
		<!-- Email Displaying Result Panel -->			
		<div id="B_SSM_S01_Page_Form_ResultPanel_EmailDisplayResultPanel">
	  		<!-- Subscribed Email Type -->			  		  		
			<logic:equal property="emailInfoMode" name="B_SSM_S01_Page_Form"  value="emailSubscribedMode">
				<div id="B_SSM_S01_Page_Form_ResultPanel_EmailSubscribedAddressDisplayResultPanel">
					<div class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult">			  			
						<TABLE class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table">
							<!-- Subscribed Email Result headers -->		
							<TR class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderRow">  							  				  				
								<!-- Email No -->
								<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
									<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.No.Text"/> 
								</TH> 
								<!-- Email CustID -->
								<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
									<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.CustID.Text"/> 	
								</TH>
								<!-- Email CustomerName -->
								<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
									<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.CustomerName.Text"/> 
								</TH>
								<!-- Email Customer Type -->
			  					<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
			  						<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.CustomerType.Text"/> 
			  					</TH>
								<!-- Email SubID -->
								<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
									<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.SubID.Text"/> 
								</TH>
								<!-- Email Service -->
			  					<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
			  						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  					</TH>
			  					<!-- Email Subscription Status -->
			  					<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
			  						<bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.ServiceStatus.Text"/> 
			  					</TH>
								<!-- Email Domain Name -->
								<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
									<bean:message key="B_SSM_S01_Page.TabPanel.EmailInfoFieldSet.DomainName.Text"/> 
								</TH>
								<!-- Email Subscribed E-mail Address -->
								<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
									<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.SubscribedEmailAddress.Text"/> 
								</TH>			  					
							</TR>
							<!-- Email Subscribed Search result content -->
							<logic:notEmpty name="B_SSM_S01_Page_Form" property="emailResultSet">
								<bean:define id="emailStartIndex" 
											 name="B_SSM_S01_Page_Form"
											 property="emailStartIndex"
											 type="java.lang.Integer" />
								<%
									int emailSubscribedRowNo = 1; 
									try {
										emailSubscribedRowNo = emailStartIndex + 1;
										if (emailSubscribedRowNo == 0) {
											emailSubscribedRowNo = 1; 
										}
									} catch(Exception ex) {
										emailSubscribedRowNo = 1; 
									}			  					
								%>										
								<logic:iterate id="result" name="B_SSM_S01_Page_Form" property="emailResultSet">			  					
									<TR>			  												  					
										<!-- Email RowNo -->
										<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
											<%= emailSubscribedRowNo++ %>			  										
										</TD>	
										<!-- Email CustID -->
										<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
											<logic:notEmpty name="result" property="emailCustomerID">
												<%
													String customerType = String.valueOf(((HashMap<String, Object>)result).get("customerType"));
				  									String logicName = "";
				  									if ("CORP".equals(customerType)) {
				  									  	logicName = "M_CSTR02BLogic.do";
				  									} else {
				  									  	logicName = "M_CSTR07BLogic.do";
				  									}
				  									String custIDLink =  request.getContextPath() + 
				  														"/M_CST/"+logicName+"?" +
				  													    "event=" + "forward_viewCustomer" + '&' +
				  													    "id_cust=" + ((HashMap<String, Object>)result).get("emailCustomerID") + '&' +
				  													    "mode=" + "READONLY" + "&" + "previous=B-SSM" + "";		    
				  							    %>	
				  								<script type="text/javascript" >						
				  								</script>  								
				  								<html:link href="<%=custIDLink %>" onclick=''>
				  									<bean:write name="result" property="emailCustomerID"/>
				  								</html:link>
											</logic:notEmpty>
											<logic:empty name="result" property="emailCustomerID">
	  											-
	  										</logic:empty>
										</TD>
										<!-- Email Customer Name -->
										<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
											<logic:notEmpty name="result" property="emailCustomerName">
												<bean:write name="result" property="emailCustomerName"/>
											</logic:notEmpty>
											<logic:empty name="result" property="emailCustomerName">
	  											-
	  										</logic:empty>
										</TD>
										<!-- Email Customer Type -->
				  						<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
				  							<logic:notEmpty name="result" property="customerType">
				  								<t:writeCodeValue codeList="LIST_CUSTOMERTYPE" name="result" property="customerType"/>
				  							</logic:notEmpty>
				  							<logic:empty name="result" property="customerType">
				  								-
				  							</logic:empty>
				  						</TD>
										<!-- Email SubID -->
										<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
											<logic:notEmpty name="result" property="emailSubscriptionID">
												<%
				  									String subIDLink =  request.getContextPath() + 
		  													    		"/B_SSM_S01/B_SSM_S01_SubInfo_Displaying_Action.do?" +
				  														"customerPlanID=" + ((HashMap<String, Object>)result).get("emailCustomerPlanID") + '&' +
				  														"subscriptionID=" + ((HashMap<String, Object>)result).get("emailSubscriptionID") + '&' +
				  														"customerID=" + ((HashMap<String, Object>)result).get("emailCustomerID") + '&' +
				  														"customerName=" + ((HashMap<String, Object>)result).get("emailCustomerName") + '&' +
				  														"fromPopup=" + "noPopup";
				  								 %>				  								 				  								 
				  								<script type="text/javascript" >				  													  													  									
				  								</script>				  											  								
				  								<html:link href="<%=subIDLink %>" onclick=''>	
													<bean:write name="result" property="emailSubscriptionID"/>
												</html:link>
											</logic:notEmpty>
											<logic:empty name="result" property="emailSubscriptionID">
	  											-
	  										</logic:empty>
										</TD>			  						
										<!-- Email Service -->
										<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
											<!-- <logic:notEmpty name="result" property="emailService"> -->
												<logic:notEqual value="0" name="accessRightBean">
													<%
					  									String serviceNameLink =  request.getContextPath() + 
					  															  "/B_CPM/B_CPM_S05InitBL.do?customerPlan.fromScreen=SSM&customerPlan.billType=SSM&" +
							  													  "customerPlan.idCustPlan=" + ((HashMap<String, Object>)result).get("emailCustomerPlanID");
							  							String popUpFeatures = "toolbar=no, status=no, menubar=no,location=no, " +
							  												   "scrollbars=yes, resizable=yes, width=1100, height=770";
					  								 %>							
				  									<html:link href='javascript:void(0);' 
				  										  	   onclick='<%= "popUpWithSpecificFeatures(\'" + serviceNameLink + "\',\'" + 
			  			    		 								 							       popUpFeatures + "\', 1100, 770);" %>'>
															<!--   <bean:write name="result" property="emailService"/> -->
															Customer Plan
													</html:link>
												</logic:notEqual>
											<!-- </logic:notEmpty>
											<logic:empty name="result" property="emailService">
	  											-
	  										</logic:empty> -->
										</TD>
										<!-- Email Subscription Status -->
				  						<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
				  							<logic:equal value="PS7" name="result" property="PLAN_STATUS">
				  								<font style="color:red;">
				  									<t:writeCodeValue codeList="LIST_PLANSTATUS" name="result" property="PLAN_STATUS"/>
				  								</font>
				  							</logic:equal>
				  							<logic:notEqual value="PS7" name="result" property="PLAN_STATUS">
				  								<t:writeCodeValue codeList="LIST_PLANSTATUS" name="result" property="PLAN_STATUS"/>
				  							</logic:notEqual>
				  						</TD>
										<!-- Email Domain Name -->
										<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
											<logic:notEmpty name="result" property="emailDomainName">
												<bean:write name="result" property="emailDomainName"/>
											</logic:notEmpty>
											<logic:empty name="result" property="emailDomainName">
	  											-
	  										</logic:empty>
										</TD>						  						
										<!-- Subscribed Email Address -->
										<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
											<logic:notEmpty name="result" property="mailAccountInfoList">
												<bean:define id="emailSubscribedAddressTextFields" 
															 name="result"
															 property="mailAccountInfoList"
															 type="java.util.List"/>
												<% int emailSubscribedAddressCount = 0; %>
												<logic:iterate id="emailSubscribedAddressTextField" name="emailSubscribedAddressTextFields">
												    <logic:equal value="1" property="IS_DELETED" name="emailSubscribedAddressTextField">
													    <font style="color: grey;font-style: italic;"><bean:write name="emailSubscribedAddressTextField" property="MAIL_ACCOUNT"/></font>
													</logic:equal>
													<logic:notEqual value="1" property="IS_DELETED" name="emailSubscribedAddressTextField">
													    <bean:write name="emailSubscribedAddressTextField" property="MAIL_ACCOUNT"/>
													</logic:notEqual>
													<% 
														if (emailSubscribedAddressCount++ < emailSubscribedAddressTextFields.size() - 1) {
															out.print("<br/>");
														};
													%>
												</logic:iterate>
											</logic:notEmpty>
											<logic:empty name="result" property="mailAccountInfoList">
	  											-
	  										</logic:empty>
										</TD>							  					
									</TR>	
								</logic:iterate>
							</logic:notEmpty>
						</TABLE>			  			
					</div>	
				</div>
			</logic:equal> 
			
			<!-- Domain Name Email Type -->
			<logic:equal property="emailInfoMode" name="B_SSM_S01_Page_Form" value="emailDomainNameMode">
				<div id="B_SSM_S01_Page_Form_ResultPanel_EmailSubscribedAddressDisplayResultPanel">
					<div class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult">			  			
						<TABLE class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table">
							<!-- Email Domain Name Result headers -->		
							<TR class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderRow">  							  				  				
								<!-- Email No -->
								<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
									<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.No.Text"/> 
								</TH> 
								<!-- Email CustID -->
								<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
									<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.CustID.Text"/> 	
								</TH>
								<!-- Email CustomerName -->
								<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
									<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.CustomerName.Text"/> 
								</TH>
								<!-- Email Customer Type -->
			  					<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
			  						<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.CustomerType.Text"/> 
			  					</TH>
								<!-- Email SubID -->
								<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
									<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.SubID.Text"/> 
								</TH>
								<!-- Email Service -->
			  					<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
			  						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  					</TH>
			  					<!-- Email Subscription Status -->
			  					<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
			  						<bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.ServiceStatus.Text"/> 
			  					</TH>
								<!-- Email Teamwork URL -->
								<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
									<bean:message key="B_SSM_S01_Page.TabPanel.EamilInfoFieldSet.TeamworkUrl.Text"/> 
								</TH>
								<!-- Email Admin ID -->
								<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
									<bean:message key="B_SSM_S01_Page.TabPanel.EamilInfoFieldSet.AdminId.Text"/> 
								</TH>	
								<!-- Email Email Domain Address -->
								<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
									<bean:message key="B_SSM_S01_Page.TabPanel.EamilInfoFieldSet.emailDomainAdrs.Text"/> 
								</TH>
							</TR>
							<!-- Email DomainName Search result content -->
							<logic:notEmpty name="B_SSM_S01_Page_Form" property="emailResultSet">	
								<bean:define id="emailStartIndex" 
											 name="B_SSM_S01_Page_Form"
											 property="emailStartIndex"
											 type="java.lang.Integer"/>
								<%
									int emailDomainNameRowNo = 1; 
									try {
										emailDomainNameRowNo = emailStartIndex + 1;
										if (emailDomainNameRowNo == 0) {
											emailDomainNameRowNo = 1;
										}
									} catch(Exception ex) {
										emailDomainNameRowNo = 1; 
									}			  					
								%>										
								<logic:iterate id="result" name="B_SSM_S01_Page_Form" property="emailResultSet">			  					
									<TR>			  												  					
										<!-- Email RowNo -->
										<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
											<%= emailDomainNameRowNo++ %>			  										
										</TD>	
										<!-- Email CustID -->
										<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
											<logic:notEmpty name="result" property="emailCustomerID">
												<%
													String customerType = String.valueOf(((HashMap<String, Object>)result).get("customerType"));
				  									String logicName = "";
				  									if ("CORP".equals(customerType)) {
				  									  	logicName = "M_CSTR02BLogic.do";
				  									} else {
				  									  	logicName = "M_CSTR07BLogic.do";
				  									}
				  									String custIDLink =  request.getContextPath() + 
				  														"/M_CST/"+logicName+"?" +
				  													    "event=" + "forward_viewCustomer" + '&' +
				  													    "id_cust=" + ((HashMap<String, Object>)result).get("emailCustomerID") + '&' +
				  													    "mode=" + "READONLY" + "&" + "previous=B-SSM" + "";				    
				  							    %>				  								 
			  								 
				  								<script type="text/javascript" >						
				  								</script>  								
				  								<html:link href="<%=custIDLink %>" onclick=''>
				  									<bean:write name="result" property="emailCustomerID"/>
				  								</html:link>
											</logic:notEmpty>
											<logic:empty name="result" property="emailCustomerID">
	  											-
	  										</logic:empty>
										</TD>
										<!-- Email Customer Name -->
										<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
											<logic:notEmpty name="result" property="emailCustomerName">
												<bean:write name="result" property="emailCustomerName"/>
											</logic:notEmpty>
											<logic:empty name="result" property="emailCustomerName">
	  											-
	  										</logic:empty>
										</TD>
										<!-- Email Customer Type -->
				  						<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
				  							<logic:notEmpty name="result" property="customerType">
				  								<t:writeCodeValue codeList="LIST_CUSTOMERTYPE" name="result" property="customerType"/>
				  							</logic:notEmpty>
				  							<logic:empty name="result" property="customerType">
				  								-
				  							</logic:empty>
				  						</TD>
										<!-- Email SubID -->
										<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
											<logic:notEmpty name="result" property="emailSubscriptionID">
												<%
				  									String subIDLink =  request.getContextPath() + 
		  													    		"/B_SSM_S01/B_SSM_S01_SubInfo_Displaying_Action.do?" +
				  														"customerPlanID=" + ((HashMap<String, Object>)result).get("emailCustomerPlanID") + '&' +
				  														"subscriptionID=" + ((HashMap<String, Object>)result).get("emailSubscriptionID") + '&' +
				  														"customerID=" + ((HashMap<String, Object>)result).get("emailCustomerID") + '&' +
				  														"customerName=" + ((HashMap<String, Object>)result).get("emailCustomerName");
				  								 %>				  								 				  								 
				  								<script type="text/javascript" >				  													  													  									
				  								</script>				  											  								
				  								<html:link href="<%=subIDLink %>" onclick=''>	
													<bean:write name="result" property="emailSubscriptionID"/>
												</html:link>
											</logic:notEmpty>
											<logic:empty name="result" property="emailSubscriptionID">
	  											-
	  										</logic:empty>
										</TD>			  						
										<!-- Email Service -->
										<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
											<!-- <logic:notEmpty name="result" property="emailService"> -->
												<logic:notEqual value="0" name="accessRightBean">
													<%
					  									String serviceNameLink =  request.getContextPath() + 
					  															  "/B_CPM/B_CPM_S05InitBL.do?customerPlan.fromScreen=SSM&customerPlan.billType=SSM&" +
							  													  "customerPlan.idCustPlan=" + ((HashMap<String, Object>)result).get("emailCustomerPlanID");
							  							String popUpFeatures = "toolbar=no, status=no, menubar=no,location=no, " +
								  											   "scrollbars=yes, resizable=yes, width=1100, height=770";
					  								 %>
					  								<html:link href='javascript:void(0);' 
					  										   onclick='<%= "popUpWithSpecificFeatures(\'" + serviceNameLink + "\',\'" + 
				  			    		 								 							   popUpFeatures + "\', 1100, 770);" %>'>
														<!--<bean:write name="result" property="emailService"/> -->
														Customer Plan
													</html:link>
												</logic:notEqual>
											<!-- </logic:notEmpty>
											<logic:empty name="result" property="emailService">
	  											-
	  										</logic:empty>  -->
										</TD>
										<!-- Email Subscription Status -->
				  						<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
				  							<logic:equal value="PS7" name="result" property="PLAN_STATUS">
				  								<font style="color:red;">
				  									<t:writeCodeValue codeList="LIST_PLANSTATUS" name="result" property="PLAN_STATUS"/>
				  								</font>
				  							</logic:equal>
				  							<logic:notEqual value="PS7" name="result" property="PLAN_STATUS">
				  								<t:writeCodeValue codeList="LIST_PLANSTATUS" name="result" property="PLAN_STATUS"/>
				  							</logic:notEqual>
				  						</TD>
										<!-- Email Teamwork URL -->
										<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
											<logic:notEmpty name="result" property="emailTeamworkURL">
												<bean:write name="result" property="emailTeamworkURL"/>
											</logic:notEmpty>
											<logic:empty name="result" property="emailTeamworkURL">
	  											-
	  										</logic:empty>
										</TD>
										<!-- Email Teamwork Admin ID -->
										<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
											<logic:notEmpty name="result" property="emailTeamworkAdminId">
												<bean:write name="result" property="emailTeamworkAdminId"/>
											</logic:notEmpty>
											<logic:empty name="result" property="emailTeamworkAdminId">
	  											-
	  										</logic:empty>
										</TD>
										<!-- Email Teamwork Email Domain Address -->
										<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
											<logic:notEmpty name="result" property="emailTeamworkDomainAddress">
												<bean:write name="result" property="emailTeamworkDomainAddress"/>
											</logic:notEmpty>
											<logic:empty name="result" property="emailTeamworkDomainAddress">
	  											-
	  										</logic:empty>
										</TD>
									</TR>	
								</logic:iterate>						  				
							</logic:notEmpty>
						</TABLE>			  			
					</div>	
				</div>
			</logic:equal>
			<!-- IT Contact Email Type -->
			<logic:equal property="emailInfoMode" name="B_SSM_S01_Page_Form" value="emailITContactMode">
				<div id="B_SSM_S01_Page_Form_ResultPanel_EmailITContactDisplayResultPanel">
					<div class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult">			  			
						<TABLE class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table">
							<!-- IT Contact Email Result headers -->		
							<TR class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderRow">  							  				  				
								<!-- Email No -->
								<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
									<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.No.Text"/> 
								</TH> 
								<!-- Email CustID -->
								<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
									<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.CustID.Text"/> 	
								</TH>
								<!-- Email CustomerName -->
								<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
									<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.CustomerName.Text"/> 
								</TH>
								<!-- Email SubID -->
								<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
									<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.SubID.Text"/> 
								</TH>
								<!-- Email Service -->
								<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
			  						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  					</TH>
			  					<!-- CoLocation Subscription Status -->
			  					<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
			  						<bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.ServiceStatus.Text"/> 
			  					</TH>
								<!-- Email Customer Tel. -->
								<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
									<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.CustomerTel.Text"/> 
								</TH>
								<!-- Email IT Contact Email -->
								<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
									<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.ITContactMail.Text"/> 
								</TH>		
								<!-- Email IT Contact No -->
								<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
									<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.ITContactNo.Text"/> 
								</TH>		  					
							</TR>
							<!-- Email IT Contact Search result content -->
							<logic:notEmpty name="B_SSM_S01_Page_Form" property="emailResultSet">
								<bean:define id="emailStartIndex" 
											 name="B_SSM_S01_Page_Form"
											 property="emailStartIndex"
											 type="java.lang.Integer"/>
								<%
									int emailITContactRowNo = 1; 
									try {
										emailITContactRowNo = emailStartIndex + 1;
										if (emailITContactRowNo == 0) {
											emailITContactRowNo = 1; 
										}
									} catch(Exception ex) {
										emailITContactRowNo = 1; 
									}			  					
								%>	
								
								<logic:iterate id="result" name="B_SSM_S01_Page_Form" property="emailResultSet">			  					
									<TR>			  												  					
										<!-- Email RowNo -->
										<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
											<%= emailITContactRowNo++ %>			  										
										</TD>	
										<!-- Email CustID -->
										<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
											<logic:notEmpty name="result" property="emailCustomerID">
												<%
													String customerType = String.valueOf(((HashMap<String, Object>)result).get("customerType"));
				  									String logicName = "";
				  									if ("CORP".equals(customerType)) {
				  									  	logicName = "M_CSTR02BLogic.do";
				  									} else {
				  									  	logicName = "M_CSTR07BLogic.do";
				  									}
				  									String custIDLink =  request.getContextPath() + 
				  														"/M_CST/"+logicName+"?" +
				  													    "event=" + "forward_viewCustomer" + '&' +
				  													    "id_cust=" + ((HashMap<String, Object>)result).get("emailCustomerID") + '&' +
				  													    "mode=" + "READONLY" + "&" + "previous=B-SSM" + "";			    
				  							    %>				  								 
			  								 
				  								<script type="text/javascript" >						
				  								</script>  								
				  								<html:link href="<%=custIDLink %>" onclick=''>
				  									<bean:write name="result" property="emailCustomerID"/>
				  								</html:link>
											</logic:notEmpty>
											<logic:empty name="result" property="emailCustomerID">
	  											-
	  										</logic:empty>
										</TD>
										<!-- Email Customer Name -->
										<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
											<logic:notEmpty name="result" property="emailCustomerName">
												<bean:write name="result" property="emailCustomerName"/>
											</logic:notEmpty>
											<logic:empty name="result" property="emailCustomerName">
	  											-
	  										</logic:empty>
										</TD>
										<!-- Email SubID -->
										<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
											<logic:notEmpty name="result" property="emailSubscriptionID">
												<%
				  									String subIDLink =  request.getContextPath() + 
		  													    		"/B_SSM_S01/B_SSM_S01_SubInfo_Displaying_Action.do?" +
				  														"customerPlanID=" + ((HashMap<String, Object>)result).get("emailCustomerPlanID") + '&' +
				  														"subscriptionID=" + ((HashMap<String, Object>)result).get("emailSubscriptionID") + '&' +
				  														"customerID=" + ((HashMap<String, Object>)result).get("emailCustomerID") + '&' +
				  														"customerName=" + ((HashMap<String, Object>)result).get("emailCustomerName");
				  								 %>				  								 				  								 
				  								<script type="text/javascript" >				  													  													  									
				  								</script>				  											  								
				  								<html:link href="<%=subIDLink %>" onclick=''>	
													<bean:write name="result" property="emailSubscriptionID"/>
												</html:link>
											</logic:notEmpty>
											<logic:empty name="result" property="emailSubscriptionID">
	  											-
	  										</logic:empty>
										</TD>			  						
										<!-- Email Service -->
										<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
											<!-- <logic:notEmpty name="result" property="emailService"> -->
												<logic:notEqual value="0" name="accessRightBean">
													<%
					  									String serviceNameLink =  request.getContextPath() + 
					  															  "/B_CPM/B_CPM_S05InitBL.do?customerPlan.fromScreen=SSM&customerPlan.billType=SSM&" +
							  													  "customerPlan.idCustPlan=" + ((HashMap<String, Object>)result).get("emailCustomerPlanID");
					  									 String popUpFeatures = "toolbar=no, status=no, menubar=no,location=no, " +
							  												    "scrollbars=yes, resizable=yes, width=1100, height=770";
					  								 %>
					  								<html:link href='javascript:void(0);' 
					  										   onclick='<%= "popUpWithSpecificFeatures(\'" + serviceNameLink + "\',\'" + 
				  			    		 								 							   popUpFeatures + "\', 1100, 770);" %>'>
														<!-- <bean:write name="result" property="emailService"/> -->
														Customer Plan
													</html:link>
												</logic:notEqual>
											<!-- </logic:notEmpty>
											<logic:empty name="result" property="emailService">
	  											-
	  										</logic:empty> -->
										</TD>
										<!-- CoLocation Subscription Status -->
				  						<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
				  							<logic:equal value="PS7" name="result" property="PLAN_STATUS">
				  								<font style="color:red;">
				  									<t:writeCodeValue codeList="LIST_PLANSTATUS" name="result" property="PLAN_STATUS"/>
				  								</font>
				  							</logic:equal>
				  							<logic:notEqual value="PS7" name="result" property="PLAN_STATUS">
				  								<t:writeCodeValue codeList="LIST_PLANSTATUS" name="result" property="PLAN_STATUS"/>
				  							</logic:notEqual>
				  						</TD>
										<!-- Email Cust Tel -->
										<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
											<logic:notEmpty name="result" property="emailCustomerTelephone">
												<bean:write name="result" property="emailCustomerTelephone"/>
											</logic:notEmpty>
											<logic:empty name="result" property="emailCustomerTelephone">
	  											-
	  										</logic:empty>
										</TD>						  						
										<!-- Email Contact -->
										<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">						  					
											<logic:notEmpty name="result"
															property="emailITContact">
												<bean:define id="emailITContactFields" 
															 name="result"
															 property="emailITContact"
															 type="java.util.List"/>
												<% int emailITContactCount = 0; %>
												<logic:iterate id="emailITContactField" name="emailITContactFields">
													<bean:write name="emailITContactField"/>	
													<% 
														if (emailITContactCount++ < emailITContactFields.size() - 1) {
															out.print("<br/>");
														};
													 %>
												</logic:iterate>
											</logic:notEmpty>	
											<logic:empty name="result"
														 property="emailITContact">
	  											-
	  										</logic:empty>				  							
										</TD>	
										<!-- Email Contact No -->
										<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">						  							
											<logic:notEmpty name="result"
															property="emailITContactNo">
												<bean:define id="emailITContactNoFields" 
															 name="result"
															 property="emailITContactNo"
															 type="java.util.List"/>
												<% int emailITContactNoCount = 0; %>
												<logic:iterate id="emailITContactNoField" name="emailITContactNoFields">
													<bean:write name="emailITContactNoField"/>	
													<% 
														if (emailITContactNoCount++ < emailITContactNoFields.size() - 1) {
															out.print("<br/>");
														};
													 %>
												</logic:iterate>
											</logic:notEmpty>
											<logic:empty name="result"
														 property="emailITContactNo">
	  											-
	  										</logic:empty>
										</TD>							  											  					
									</TR>
								</logic:iterate>
							</logic:notEmpty>
						</TABLE>			  			
					</div>	
				</div>
			</logic:equal>				
	  	</div>
  	</div>
</body>
</html>