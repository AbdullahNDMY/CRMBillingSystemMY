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
		<!-- CoLocation Displaying Result Panel -->
		<div id="B_SSM_S01_Page_Form_ResultPanel_CoLocationDisplayResultPanel">
	  		<div class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult">			  			
  				<TABLE class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table">
  					<!-- CoLocation Result headers -->		
  					<TR class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderRow">  							  				  				
	  					<!-- CoLocation No -->
	  					<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
	  						<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.No.Text"/> 
	  					</TH> 
	  					<!-- CoLocation CustID -->
	  					<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
	  						<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.CustID.Text"/> 	
	  					</TH>
	  					<!-- CoLocation CustomerName -->
	  					<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
	  						<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.CustomerName.Text"/> 
	  					</TH>
	  					<!-- CoLocation Customer Type -->
	  					<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
	  						<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.CustomerType.Text"/> 
	  					</TH>
	  					<!-- CoLocation SubID -->
	  					<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
	  						<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.SubID.Text"/> 
	  					</TH>
	  					<!-- CoLocation Service -->
	  					<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
	  						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  					</TH>
	  					<!-- CoLocation Subscription Status -->
	  					<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
	  						<bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.ServiceStatus.Text"/> 
	  					</TH>
	  					<!-- CoLocation Equipment Location -->
	  					<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
	  						<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.EquipmentLocation.Text"/> 
	  					</TH>
	  					<!-- CoLocation Equipment Suite -->
	  					<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
	  						<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.EquipmentSuite.Text"/> 
	  					</TH>
	  					<!-- CoLocation Rack No -->
	  					<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
	  						<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.RackNo.Text"/> 
	  					</TH>
	  					<logic:notEqual name="B_SSM_S01_Page_Form" property="coLocationSearchByDetails" value="true">
		  					<!-- CoLocation Total Rack No -->
		  					<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
		  						<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.TotalNoRack.Text"/> 
		  					</TH>
	  					</logic:notEqual>
	  					<!-- CoLocation Power committed -->
	  					<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
	  						<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.PowerCommitted.Text"/> 
	  					</TH>
  					</TR>
  					<!-- CoLocation Search result content -->
  					<logic:notEmpty name="B_SSM_S01_Page_Form" property="coLocationResultSet">
	  					<bean:define id="coLocationStartIndex" 
	  								 name="B_SSM_S01_Page_Form"
									 property="coLocationStartIndex"
	  								 type="java.lang.Integer"/>
	  					<%
	  						int coLocationRowNo = 1; 
	  						try {
	  							coLocationRowNo = coLocationStartIndex + 1;
	  							if (coLocationRowNo == 0) {
	  								coLocationRowNo = 1;
	  							}
	  						} catch(Exception ex) {
	  							coLocationRowNo = 1; 
	  						}			  					
	  					%>
		  				<logic:iterate id="result" name="B_SSM_S01_Page_Form" property="coLocationResultSet">
			  				<TR>			  					
			  					<!-- CoLocation Sum Search -->
			  					<logic:notEqual name="B_SSM_S01_Page_Form" property="coLocationSearchByDetails" value="true">
			  						<!-- CoLocation RowNo -->
			  						<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
			  							<%= coLocationRowNo++ %>			  									
			  						</TD>	
			  						<!-- CoLocation CustID -->
			  						<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
			  							<logic:notEmpty name="result" property="coLocationCustomerID">
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
			  													    "id_cust=" + ((HashMap<String, Object>)result).get("coLocationCustomerID") + '&' +
			  													    "mode=" + "READONLY" + "&" + "previous=B-SSM" + "";				    
			  							    %>				  								 
		  								 
			  								<script type="text/javascript" >						
			  								</script>  								
			  								<html:link href="<%=custIDLink %>" onclick=''>
		  										<bean:write name="result" property="coLocationCustomerID"/>
		  									</html:link>
				  						</logic:notEmpty>
			  							<logic:empty name="result" property="coLocationCustomerID">
	  										-
	  									</logic:empty>
			  						</TD>
			  						<!-- CoLocation Customer Name -->
			  						<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
			  							<logic:notEmpty name="result" property="coLocationCustomerName">
			  								<bean:write name="result" property="coLocationCustomerName"/>
			  							</logic:notEmpty>
			  							<logic:empty name="result" property="coLocationCustomerName">
	  										-
	  									</logic:empty>
			  						</TD>
			  						<!-- CoLocation Customer Type -->
			  						<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
			  							<logic:notEmpty name="result" property="customerType">
			  								<t:writeCodeValue codeList="LIST_CUSTOMERTYPE" name="result" property="customerType"/>
			  							</logic:notEmpty>
			  							<logic:empty name="result" property="customerType">
	  										-
	  									</logic:empty>
			  						</TD>
			  						<!-- CoLocation SubID -->
			  						<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
			  							<logic:notEmpty name="result" property="coLocationSubscriptionID">					  							
			  							<%
		  									String subIDLink =  request.getContextPath() + 
		  													    "/B_SSM_S01/B_SSM_S01_SubInfo_Displaying_Action.do?" +
		  														"customerPlanID=" + ((HashMap<String, Object>)result).get("coLocationCustomerPlanID") + '&' +
		  														"subscriptionID=" + ((HashMap<String, Object>)result).get("coLocationSubscriptionID") + '&' +
		  														"customerID=" + ((HashMap<String, Object>)result).get("coLocationCustomerID") + '&' +
		  														"customerName=" + ((HashMap<String, Object>)result).get("coLocationCustomerName") + '&' +
		  													  	"fromPopup=" + "noPopup";
		  													    
		  								 %>				  								 				  								 
		  								<script type="text/javascript" >				  													  													  									
		  								</script>				  											  								
		  								<html:link href="<%=subIDLink %>" onclick=''>					  								
			  								<bean:write name="result" property="coLocationSubscriptionID"/>
			  							</html:link>
			  							</logic:notEmpty>
			  							<logic:empty name="result" property="coLocationSubscriptionID">
	  										-
	  									</logic:empty>
			  						</TD>			  						
			  						<!-- CoLocation Service -->
			  						<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
			  							<!-- <logic:notEmpty name="result" property="coLocationService"> -->
			  							<logic:notEqual value="0" name="accessRightBean">
			  								<%
			  									String serviceNameLink = request.getContextPath() + 
			  															"/B_CPM/B_CPM_S05InitBL.do?customerPlan.fromScreen=SSM&customerPlan.billType=SSM&" +
					  													"customerPlan.idCustPlan=" + ((HashMap<String, Object>)result).get("coLocationCustomerPlanID");
				  								 String popUpFeatures = "toolbar=no, status=no, menubar=no,location=no, " +
					  												    "scrollbars=yes, resizable=yes, width=1100, height=770";
			  								 %> 								
			  								<html:link href='javascript:void(0);' 
			  										   onclick='<%= "popUpWithSpecificFeatures(\'" + serviceNameLink + "\',\'" + 
		  			    		 								 							   popUpFeatures + "\', 1100, 770);" %>'>
					  								 <!--<bean:write name="result" property="coLocationService"/>-->  
					  								Customer Plan
					  						</html:link>
					  					</logic:notEqual>	
			  							<!-- </logic:notEmpty>
			  							<logic:empty name="result" property="coLocationService">
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
			  						<!-- CoLocation Equipment Location -->
			  						<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
			  							<logic:notEmpty name="result" property="coLocationEquipmentLocation">
			  								<bean:write name="result" property="coLocationEquipmentLocation"/>
			  							</logic:notEmpty>
			  							<logic:empty name="result" property="coLocationEquipmentLocation">
	  										-
	  									</logic:empty>
			  						</TD>
			  						<!-- CoLocation Equipment Suite -->
			  						<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
			  							<logic:notEmpty name="result" property="coLocationEquipmentSuite">
			  								<bean:write name="result" property="coLocationEquipmentSuite"/>
			  							</logic:notEmpty>
			  							<logic:empty name="result" property="coLocationEquipmentSuite">
	  										-
	  									</logic:empty>
			  						</TD>
			  						<!-- CoLocation Rack No -->
			  						<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
			  							<logic:notEmpty  name="result" property="coLocationRackNo">
			  								<bean:write name="result" property="coLocationRackNo"/>
			  							</logic:notEmpty>
			  							<logic:empty  name="result" property="coLocationRackNo">
	  										-
	  									</logic:empty>
			  						</TD>
			  						<!-- CoLocation Total No. Rack -->
			  						<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
			  							<logic:notEmpty name="result" property="coLocationTotalRackNo">
			  								<bean:write name="result" property="coLocationTotalRackNo"/>
			  							</logic:notEmpty>
			  							<logic:empty name="result" property="coLocationTotalRackNo">
	  										-
	  									</logic:empty>
			  						</TD>
			  						<!-- CoLocation Power Committed -->
			  						<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
			  							<logic:notEmpty name="result" property="coLocationPowerCommitted">
			  								<bean:write name="result" property="coLocationPowerCommitted"/>
			  							</logic:notEmpty>
			  							<logic:empty name="result" property="coLocationPowerCommitted">
	  										-
	  									</logic:empty>
			  						</TD>
			  					</logic:notEqual>
			  					
			  					<!-- CoLocation Detail Search -->
			  					<logic:equal name="B_SSM_S01_Page_Form" property="coLocationSearchByDetails" value="true">
			  						<!-- CoLocation RowNo -->
			  						<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
			  							<%= coLocationRowNo++ %>			  									
			  						</TD>	
			  						<!-- CoLocation CustID -->
			  						<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
			  							<logic:notEmpty name="result" property="coLocationCustomerID">
			  								<%
				  								String customerType = String.valueOf(((HashMap<String, Object>)result).get("customerType"));
			  									String logicName = "";
			  									if ("CORP".equals(customerType)) {
			  									  	logicName = "M_CSTR02BLogic.do";
			  									} else {
			  									  	logicName = "M_CSTR07BLogic.do";
			  									}
			  									String custIDLink = request.getContextPath() + 
			  														"/M_CST/"+logicName+"?" +
			  													    "event=" + "forward_viewCustomer" + '&' +
			  													    "id_cust=" + ((HashMap<String, Object>)result).get("coLocationCustomerID") + '&' +
			  													    "mode=" + "READONLY" + "&" + "previous=B-SSM" + "";				    
			  							    %>				  								 
		  								 
			  								<script type="text/javascript" >						
			  								</script>  								
			  								<html:link href="<%=custIDLink %>" onclick=''>
		  										<bean:write name="result" property="coLocationCustomerID"/>
		  									</html:link>
			  							</logic:notEmpty>
			  							<logic:empty name="result" property="coLocationCustomerID">
	  										-
	  									</logic:empty>
			  						</TD>
			  						<!-- CoLocation Customer Name -->
			  						<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
			  							<logic:notEmpty name="result" property="coLocationCustomerName">
			  								<bean:write name="result" property="coLocationCustomerName"/>
			  							</logic:notEmpty>
			  							<logic:empty name="result" property="coLocationCustomerName">
	  										-
	  									</logic:empty>
			  						</TD>
			  						<!-- CoLocation Customer Type -->
			  						<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
			  							<logic:notEmpty name="result" property="customerType">
			  								<t:writeCodeValue codeList="LIST_CUSTOMERTYPE" name="result" property="customerType"/>
			  							</logic:notEmpty>
			  							<logic:empty name="result" property="customerType">
	  										-
	  									</logic:empty>
			  						</TD>
			  						<!-- CoLocation SubID -->
			  						<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
			  							<logic:notEmpty name="result" property="coLocationSubscriptionID">					  												  							
				  							<%
			  									String subIDLink =  request.getContextPath() + 
		  													        "/B_SSM_S01/B_SSM_S01_SubInfo_Displaying_Action.do?" +
			  														"customerPlanID=" + ((HashMap<String, Object>)result).get("coLocationCustomerPlanID") + '&' +
			  														"subscriptionID=" + ((HashMap<String, Object>)result).get("coLocationSubscriptionID") + '&' +
			  														"customerID=" + ((HashMap<String, Object>)result).get("coLocationCustomerID") + '&' +
			  														"customerName=" + ((HashMap<String, Object>)result).get("coLocationCustomerName") + '&' +
			  														"fromPopup=" + "noPopup";
			  								 %>				  								 				  								 
			  								<script type="text/javascript" >				  													  													  									
			  								</script>				  											  								
			  								<html:link href="<%=subIDLink %>" onclick=''>	
			  									<bean:write name="result" property="coLocationSubscriptionID"/>
			  								</html:link>
			  							</logic:notEmpty>
			  							<logic:empty name="result" property="coLocationSubscriptionID">
	  										-
	  									</logic:empty>
			  						</TD>			  						
			  						<!-- CoLocation Service -->
			  						<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
			  							<!-- <logic:notEmpty name="result" property="coLocationService"> -->
			  								<logic:notEqual value="0" name="accessRightBean">
				  								<%
				  									String serviceNameLink = request.getContextPath() + 
				  															"/B_CPM/B_CPM_S05InitBL.do?customerPlan.fromScreen=SSM&customerPlan.billType=SSM&" +
																			"customerPlan.idCustPlan=" + ((HashMap<String, Object>)result).get("coLocationCustomerPlanID");
				  									 String popUpFeatures = "toolbar=no, status=no, menubar=no,location=no, " +
					  												   		"scrollbars=yes, resizable=yes, width=1100, height=770";
			  								 	%>							
				  								<html:link href='javascript:void(0);' 
				  										   onclick='<%= "popUpWithSpecificFeatures(\'" + serviceNameLink + "\',\'" + 
			  			    		 								 							   popUpFeatures + "\', 1100, 770);" %>'>
					  								<!--<bean:write name="result" property="coLocationService"/>-->
					  								Customer Plan
					  							</html:link>
				  							</logic:notEqual>
			  							<!-- </logic:notEmpty>
			  							<logic:empty name="result" property="coLocationService">
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
			  						<!-- CoLocation Equipment Location -->
			  						<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
			  							<logic:notEmpty  name="result"
			  										 	 property="coLocationEquipmentLocation">
				  							<bean:define id="coLocationEquipmentLocationFields" 
				  										 name="result"
				  										 property="coLocationEquipmentLocation"
				  										 type="java.util.List"/>
				  							<% int coLocationEquipmentLocationCount = 0; %>
				  							<logic:iterate id="coLocationEquipmentLocationField" name="coLocationEquipmentLocationFields">
				  								<bean:write name="coLocationEquipmentLocationField"/>
				  								&nbsp;	
				  								<% 
				  									if (coLocationEquipmentLocationCount++ < coLocationEquipmentLocationFields.size() - 1) {
				  										out.print("<br/>");
				  									};
				  								 %>
				  							</logic:iterate>
			  							</logic:notEmpty>
	  									<logic:empty  name="result"
			  										  property="coLocationEquipmentLocation">  
	  										-
	  									</logic:empty>
			  						</TD>
			  						<!-- CoLocation Equipment Suite -->
			  						<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
			  							<logic:notEmpty name="result"
			  											property="coLocationEquipmentSuite">
				  							<bean:define id="coLocationEquipmentSuiteFields" 
				  										 name="result"
				  										 property="coLocationEquipmentSuite"
				  										 type="java.util.List"/>
				  							<% int coLocationEquipmentSuiteCount = 0; %>
				  							<logic:iterate id="coLocationEquipmentSuiteField" name="coLocationEquipmentSuiteFields">
				  								<bean:write name="coLocationEquipmentSuiteField"/>
				  								&nbsp;	
				  								<% 
				  									if (coLocationEquipmentSuiteCount++ < coLocationEquipmentSuiteFields.size() - 1) {
				  										out.print("<br/>");
				  									};
				  								 %>
				  							</logic:iterate>
			  							</logic:notEmpty>
			  							<logic:empty name="result"
			  										 property="coLocationEquipmentSuite">
	  										-
	  									</logic:empty>
			  						</TD>
			  						<!-- CoLocation Rack No -->
			  						<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
			  							<logic:notEmpty name="result"
			  										 	property="coLocationRackNo">
				  							<bean:define id="coLocationRackNoFields" 
				  										 name="result"
				  										 property="coLocationRackNo"
				  										 type="java.util.List"/>
				  							<% int coLocationRackNoCount = 0; %>
				  							<logic:iterate id="coLocationRackNoField" name="coLocationRackNoFields">
				  								<bean:write name="coLocationRackNoField"/>
				  								&nbsp;	
				  								<% 
				  									if (coLocationRackNoCount++ < coLocationRackNoFields.size() - 1) {
				  										out.print("<br/>");
				  									};
				  								 %>
				  							</logic:iterate>
			  							</logic:notEmpty>
			  							<logic:empty name="result"
			  										 property="coLocationRackNo">
	  										-
	  									</logic:empty>
			  						</TD>
			  						<%--
			  						<!-- CoLocation Total No. Rack -->
			  						<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
			  							&nbsp;
			  						</TD>
			  						--%>
			  						<!-- CoLocation Power Committed -->
			  						<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
			  							<logic:notEmpty name="result"
			  											property="coLocationPowerCommitted">
				  							<bean:define id="coLocationPowerCommittedFields" 
				  										 name="result"
				  										 property="coLocationPowerCommitted"
				  										 type="java.util.List"/>
				  							<% int coLocationPowerCommittedCount = 0; %>
				  							<logic:iterate id="coLocationPowerCommittedField" name="coLocationPowerCommittedFields">						  							
				  								<bean:write name="coLocationPowerCommittedField"/>
				  								<% 
				  									if (coLocationPowerCommittedCount++ < coLocationPowerCommittedFields.size() - 1) {
				  										out.print("<br/>");
				  									};
				  								 %>
				  							</logic:iterate>
			  							</logic:notEmpty>
			  							<logic:empty name="result"
			  											property="coLocationPowerCommitted">
	  										-
	  									</logic:empty>
			  						</TD>
			  					</logic:equal>
			  				</TR>
		  				</logic:iterate>	
	  				</logic:notEmpty>
  				</TABLE>			  			
	  		</div>	
	  	</div>
  	</div>
</body>
</html>