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
		<!-- General Displaying Result Panel -->
	  	<div id="B_SSM_S01_Page_Form_ResultPanel_GeneralDisplayResultPanel">
	  		<div class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult">
	  			<TABLE class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table">
  					<!-- General Result headers -->		
  					<TR class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderRow">  							  				  				
	  					<!-- General No -->							
	  					<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
	  						<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.No.Text"/> 
	  					</TH> 
	  					<!-- General CustID -->
	  					<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
	  						<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.CustID.Text"/> 	
	  					</TH>
	  					<!-- General CustomerName -->
	  					<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
	  						<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.CustomerName.Text"/> 
	  					</TH>
	  					<!-- General Customer Type -->
	  					<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
	  						<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.CustomerType.Text"/> 
	  					</TH>
	  					<!-- General SubID -->
	  					<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
	  						<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.SubID.Text"/> 
	  					</TH>
	  					<!-- General Service -->
	  					<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
	  						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  					</TH>
	  					<!-- Subscription Status -->
	  					<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
	  						<bean:message key="B_SSM_S01_Page.TabPanel.CustomerPlanInfoFieldSet.ServiceStatus.Text"/> 
	  					</TH>
  					</TR>
  					<!-- General Search result content -->
  					<logic:notEmpty name="B_SSM_S01_Page_Form" property="generalResultSet">
		  				<logic:iterate id="result" name="B_SSM_S01_Page_Form" property="generalResultSet" indexId="index">			  					
		  					<TR>			  					
		  						<!-- General No -->
		  						<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
		  							<logic:notEmpty name="result" property="rowNo">
		  								<bean:write name="result" property="rowNo"/>		
		  							</logic:notEmpty>
		  							<logic:empty name="result" property="rowNo">
		  								&nbsp;
		  							</logic:empty>	  						
		  						</TD>
		  						<!-- General CustID -->
		  						<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
		  							<logic:notEmpty name="result" property="CUSTOMERID">
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
		  													    "id_cust=" + ((HashMap<String, Object>)result).get("CUSTOMERID") + '&' +
		  													    "mode=" + "READONLY" + "&" + "previous=B-SSM" + "";				    
		  							    %>				 
		  								<script type="text/javascript" >						
		  								</script> 	
		  								<html:link href="<%=custIDLink %>" onclick=''>
		  									<bean:write name="result" property="CUSTOMERID"/>
		  								</html:link>
		  							</logic:notEmpty>
		  							<logic:empty name="result" property="CUSTOMERID">
		  								-
		  							</logic:empty>
		  						</TD>
		  						<!-- General CustomerName -->
		  						<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
		  							<logic:notEmpty name="result" property="CUSTOMERNAME">
		  								<bean:write name="result" property="CUSTOMERNAME"/>
		  							</logic:notEmpty>
		  							<logic:empty name="result" property="CUSTOMERNAME">
		  								-
		  							</logic:empty>
		  						</TD>
		  						<!-- General Customer Type -->
		  						<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
		  							<logic:notEmpty name="result" property="customerType">
		  								<t:writeCodeValue codeList="LIST_CUSTOMERTYPE" name="result" property="customerType"/>
		  							</logic:notEmpty>
		  							<logic:empty name="result" property="customerType">
		  								-
		  							</logic:empty>
		  						</TD>
		  						<!-- General SubID -->
		  						<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
		  							<logic:notEmpty name="result" property="SUBSCRIPTIONID">
		  								<%
		  									String subIDLink =  request.getContextPath() + 
		  													    "/B_SSM_S01/B_SSM_S01_SubInfo_Displaying_Action.do?" +
		  														"customerPlanID=" + ((HashMap<String, Object>)result).get("CUSTOMERPLANID") + '&' +
		  														"subscriptionID=" + ((HashMap<String, Object>)result).get("SUBSCRIPTIONID") + '&' +
		  														"customerID=" + ((HashMap<String, Object>)result).get("CUSTOMERID") + '&' +
		  														"customerName=" + ((HashMap<String, Object>)result).get("CUSTOMERNAME") + '&' +
		  														"fromPopup=" + "noPopup";
		  								 %>				  								 
		  								 
		  								<script type="text/javascript" >						
		  								</script>  								
		  								<html:link href="<%=subIDLink %>" onclick=''>
		  									<bean:write name="result" property="SUBSCRIPTIONID"/>
		  								</html:link>
		  							</logic:notEmpty>
		  							<logic:empty name="result" property="SUBSCRIPTIONID">
		  								-
		  							</logic:empty>
		  						</TD>
		  						<!-- General Service -->
		  						<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign"> 
		  							<!-- <logic:notEmpty name="result" property="SERVICE"> -->
		  								<logic:notEqual value="0" name="accessRightBean">
			  								<%
			  									String serviceNameLink =  request.getContextPath() + 
			  															 "/B_CPM/B_CPM_S05InitBL.do?customerPlan.fromScreen=SSM&customerPlan.billType=SSM&" +
					  													  "customerPlan.idCustPlan=" + ((HashMap<String, Object>)result).get("CUSTOMERPLANID");
					  							String popUpFeatures = "toolbar=no, status=no, menubar=no,location=no, " +
					  												   "scrollbars=yes, resizable=yes, width=1100, height=770";
			  								 %>
			  								<html:link href='javascript:void(0);' 
			  										   onclick='<%= "popUpWithSpecificFeatures(\'" + serviceNameLink + "\',\'" + 
		  			    		 								 							   popUpFeatures + "\', 1100, 770);" %>'>
			  										Customer Plan
			  								</html:link>
			  							</logic:notEqual>
			  						<!-- </logic:notEmpty>
		  							<logic:empty name="result" property="SERVICE">
		  								-
		  							</logic:empty> -->
		  						</TD>
		  						<!-- General Subscription Status -->
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
		  					</TR>
		  				</logic:iterate>
	  				</logic:notEmpty>
  				</TABLE>
	  		</div>
	  	</div>
  	</div>
</body>
</html>