<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.HashMap"%>
<%@page import="nttdm.bsys.b_ssm.s01.data.B_SSM_S01_FieldSet"%><html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
	
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/stylesheet/tabcontent.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/B_SSM_S04/css/b_ssm_s04_css.css"/>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/tabcontent.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/B_SSM/js/b_ssm_js.js"></script>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/B_SSM_S04/js/b_ssm_s04_js.js"></script>
	
	<script type="text/javascript">
	</script>			
</head>
<body onload="">
	<div id="B_SSM_S04_Page_Content">
		<ts:form  action="/B_SSM_S04_Request_Process_Action.do">		
			
			<!--************************************ Header ****************************************-->
			<!-- Header title -->
			<div class="B_SSM_S04_Page_Form_Header">
				<bean:message key="B_SSM_S04_Page.Header.Title"/>
			</div>
			
			<!-- Header Subscription Info -->
			 <div class="B_SSM_S04_Page_Form_HeaderSubInfo">
			 	<TABLE class="B_SSM_S04_Page_Form_HeaderSubInfo_Table">
			 		<TR>
			 			<TD class="B_SSM_S04_Page_Form_HeaderSubInfo_Table_Col">
			 				<TABLE width="100%">
						 		<TR>
						 			<TD class="B_SSM_S04_Page_Form_HeaderSubInfo_Table_Col_Sub" style="width:20%">
						 				<bean:message key="B_SSM_S04_Page.HeaderInfo.CustomerName.Text"/>
						 			</TD>
						 			<TD style="width:80%">
						 				:&nbsp;
						 				<bean:write name="B_SSM_S04_Page_Form" property="customerName"/>
						 			</TD>
						 		</TR>
						 	</TABLE>
			 			</TD>
			 		</TR>
			 		<TR>
			 			<TD class="B_SSM_S04_Page_Form_HeaderSubInfo_Table_Col">
			 				<TABLE width="100%">
						 		<TR>
						 			<TD class="B_SSM_S04_Page_Form_HeaderSubInfo_Table_Col_Sub" style="width:20%">
						 				<bean:message key="B_SSM_S04_Page.HeaderInfo.CustomerId.Text"/>
						 			</TD>
						 			<TD style="width:80%">
						 				:&nbsp;
						 				<bean:write name="B_SSM_S04_Page_Form" property="customerID"/>
						 			</TD>
						 		</TR>
						 	</TABLE>
			 			</TD>
			 		</TR>
			 		<TR>
			 			<TD class="B_SSM_S04_Page_Form_HeaderSubInfo_Table_Col">
			 				<TABLE width="100%">
						 		<TR>
						 			<TD class="B_SSM_S04_Page_Form_HeaderSubInfo_Table_Col_Sub" style="width:20%">
						 				<bean:message key="B_SSM_S04_Page.HeaderInfo.SubscriptionID.Text"/>
						 			</TD>
						 			<TD style="width:80%">
						 				:&nbsp;
						 				<bean:write name="B_SSM_S04_Page_Form" property="subscriptionID"/>
						 			</TD>
						 		</TR>
						 	</TABLE>
			 			</TD>
			 		</TR>
			 	</TABLE>
			 </div>
			
		  	<!--******************************************** Content *****************************************-->		  	
		  	<div class="B_SSM_S04_Page_Form_ResultPanel_DisplayResult">			  			
  				<TABLE class="B_SSM_S04_Page_Form_ResultPanel_DisplayResult_Table">
  					<col width="3%"/>
  					<col width="25%"/>
  					<col width="20%"/>
  					<col width="15%"/>
  					<col width="10%"/>
  					<col width="10%"/>
  					<col width="17%"/>
  					<!-- Result headers -->		
  					<TR class="B_SSM_S04_Page_Form_ResultPanel_DisplayResult_Table_HeaderRow">  							  				  				
	  					<!-- No -->
	  					<TH class="B_SSM_S04_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
	  						<bean:message key="B_SSM_S04_Page.Form.ResultPanel.Header.No.Text"/> 
	  					</TH> 
	  					<!-- Service name -->
	  					<TH class="B_SSM_S04_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
	  						<bean:message key="B_SSM_S04_Page.Form.ResultPanel.Header.ServiceName.Text"/> 	
	  					</TH>
	  					<!-- Plan Description -->
	  					<TH class="B_SSM_S04_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
	  						<bean:message key="B_SSM_S04_Page.Form.ResultPanel.Header.ServiceDescription.Text"/> 
	  					</TH>
	  					<!-- Contract Period -->
	  					<TH class="B_SSM_S04_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
	  						<bean:message key="B_SSM_S04_Page.Form.ResultPanel.Header.ContractPeriod.Text"/> 
	  					</TH>
	  					<!-- Contract Terms -->
	  					<TH class="B_SSM_S04_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
	  						<bean:message key="B_SSM_S04_Page.Form.ResultPanel.Header.ContractTerms.Text"/> 
	  					</TH>
	  					<!-- Status -->
	  					<TH class="B_SSM_S04_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
	  						<bean:message key="B_SSM_S04_Page.Form.ResultPanel.Header.Status.Text"/> 
	  					</TH>
	  					<!-- Service Completion Date -->
	  					<TH class="B_SSM_S04_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
	  						<bean:message key="B_SSM_S04_Page.Form.ResultPanel.Header.CompletionDate.Text"/> 
	  					</TH>	  					
  					</TR>
  					<!-- Result content -->
  					<logic:notEmpty name="B_SSM_S04_Page_Form" 
  									property="serviceNameList">
	  					<logic:iterate id="service"
	  								   name="B_SSM_S04_Page_Form" 
	  								   property="serviceNameList"
	  								   indexId="index">			  					
	  						<logic:equal name="service" property="dataType" value="header">
		  						<TR>												  					
									<!-- RowNo -->
									<TD class="B_SSM_S04_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
										<bean:write name="service" property="serviceNo"/>			  								
									</TD>	
									<!-- Service name -->
									<TD class="B_SSM_S04_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
										&nbsp;
									</TD>
									<!-- Plan Description -->
									<TD class="B_SSM_S04_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
										<span class="B_SSM_S04_Page_Form_ResultPanel_DisplayResult_Span_Main">
											<bean:write name="service" property="serviceDesc"/>
										</span>
									</TD>
									<!-- Contract Period -->
									<TD class="B_SSM_S04_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
										<logic:notEmpty name="service" property="contractPeriod">
											<bean:write name="service" property="contractPeriod"/>
										</logic:notEmpty>
										<logic:empty name="service" property="contractPeriod">
		  									&nbsp;
		  								</logic:empty>
									</TD>
									<!-- Contract Terms -->
									<TD class="B_SSM_S04_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
										<logic:notEmpty name="service" property="contractTerms">
											<bean:write name="service" property="contractTerms"/>
										</logic:notEmpty>
										<logic:empty name="service" property="contractTerms">
		  									&nbsp;
		  								</logic:empty>
									</TD>
									<!-- Status -->
									<TD class="B_SSM_S04_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
										<logic:notEmpty name="service" property="serviceStatus">
											<bean:write name="service" property="serviceStatus"/>
										</logic:notEmpty>
										<logic:empty name="service" property="serviceStatus">
		  									&nbsp;
		  								</logic:empty>
									</TD>
									<!-- Service Completion Date -->
									<TD class="B_SSM_S04_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
										<logic:notEmpty name="service" property="completionDate">
											<bean:write name="service" property="completionDate"/>
										</logic:notEmpty>
										<logic:empty name="service" property="completionDate">
		  									&nbsp;
		  								</logic:empty>
									</TD>
								</TR>
							</logic:equal>
							<logic:equal name="service" property="dataType" value="detail">
								<TR>
									<!-- RowNo -->
									<TD class="B_SSM_S04_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign" style="border-top: 0px solid #dcdccf;">
										&nbsp;						
									</TD>	
									<!-- Service name -->
									<TD class="B_SSM_S04_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign" 
										style="border-top: 0px solid #dcdccf;width:250px;word-wrap: break-word;white-space : normal">
										<span class="B_SSM_S04_Page_Form_ResultPanel_DisplayResult_Span_Sub">
											<bean:write name="service" property="serviceName"/>
										</span>
									</TD>
									<!-- Plan Description -->
									<TD class="B_SSM_S04_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign" style="border-top: 0px solid #dcdccf;" colspan="5">
										<span class="B_SSM_S04_Page_Form_ResultPanel_DisplayResult_Span_Sub">
											<bean:write name="service" property="serviceDesc"/>
										</span>
									</TD>
								</TR>
							</logic:equal>
						</logic:iterate>
					</logic:notEmpty>
				</TABLE>
			</div>
		  	
		  	<!--********************************** Error info ************************************************-->
		  	<div class="B_SSM_S04_Page_Form_Errors">
		  		<ts:errors/>	
		  	</div>		
		  	  	
		  	<!-- ******************************************* Buttons *****************************************-->	
		  	<div class="B_SSM_S04_Page_Form_Buttons">
			  	<!-- Exit Btn -->	
			  	<html:button property="button" 
			  				 styleId="exitBtnID"
			  				 styleClass="B_SSM_S04_Page_Form_Buttons_Text"
			  				 onclick="self.close();">
			  		<bean:message key="B_SSM_S04_Page.Form.ExitBtn.Text"/> 
			  	</html:button>	
		  	</div> 	
  		</ts:form>
  	</div>
</body>
</html>
