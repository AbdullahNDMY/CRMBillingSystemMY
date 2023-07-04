<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
	<style type="text/css">
	#B_SSM_S01_Page_Content .B_SSM_S01_Page_Form_ContactResultPanel_DisplayResult_Table {
		border: 1px solid #CEE7FF; 
		border-left:#CEE7FF;
		border-right:#CEE7FF;
		border-bottom:#CEE7FF;
		width: 100%;
		border-collapse: collapse;
	}
	#B_SSM_S01_Page_Content .B_SSM_S01_Page_Form_ContactResultPanel_DisplayResult_Table_HeaderCol {
		background-color: #CEE7FF;
		font-size: 15px;
		font-weight: bold;	
		vertical-align: top;
		text-align: left;	
	}
	#B_SSM_S01_Page_Content .B_SSM_S01_Page_Form_ContactResultPanel_DisplayResult_Table_TrCol td{
		border-bottom:1px solid #CEE7FF; 
	}
	</style>	
</head>
<body>
	<div id="B_SSM_S01_Page_Content">
		<t:defineCodeList id="LIST_PLANSTATUS"/>
		<t:defineCodeList id="LIST_CUSTOMERTYPE"/>
		<div id="B_SSM_S01_Page_Form_ResultPanel_AddressDisplayResultPanel">
	  		<div class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult">			  			
  				<TABLE class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table">
  				    <col width="4%"/>
  				    <col width="6%"/>
  				    <col width="15%"/>
  				    <col width="8%"/>
  				    <col width="8%"/>
  				    <col width="15%"/>
  				    <col width="12%"/>
  				    <col width="20%"/>
  				    <col width="12%"/>
  					<!-- Address Result headers -->		
  					<TR class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderRow">  							  				  				
	  					<!-- Address No -->
	  					<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
	  						<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.No.Text"/> 
	  					</TH> 
	  					<!-- Address CustID -->
	  					<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
	  						<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.CustID.Text"/> 	
	  					</TH>
	  					<!-- Address CustomerName -->
	  					<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
	  						<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.CustomerName.Text"/> 
	  					</TH>
	  					<!-- Address SubID -->
	  					<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
	  						<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.SubID.Text"/> 
	  					</TH>
	  					<!-- Address Service -->
	  					<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
	  						<bean:message key="B_SSM_S01_Page.TabPanel.ServiceInfoFieldSet.Service.Text"/>
	  					</TH>
	  					<!-- Address Tel No. -->
	  					<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
	  						<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.TelNo.Text"/> 
	  					</TH>
	  					<!-- Address Fax No. -->
	  					<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
	  						<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.FaxNo.Text"/> 
	  					</TH>
	  					<!-- IT Contact Email -->
	  					<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
	  						<bean:message key="B_SSM_S01_Page.TabPanel.AddressFieldSet.ITContactEmail.Text"/> 
	  					</TH>
	  					<!-- NRIC / Passport No -->
	  					<TH class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_HeaderCol">
	  						<bean:message key="B_SSM_S01_Page.TabPanel.ContactInfoFieldSet.PassportNo.Header.Text"/> 
	  					</TH>
  					</TR>
  					<!-- Address Search result content -->
  					<logic:notEmpty name="B_SSM_S01_Page_Form" property="addressResultSet">
						<bean:define id="addressStartIndex" 
									 name="B_SSM_S01_Page_Form"
									 property="addressStartIndex"
									 type="java.lang.Integer"/>
						<%
							int addressRowNo = 1; 
							try {
								addressRowNo = addressStartIndex + 1;
								if (addressRowNo == 0) {
									addressRowNo = 1; 
								}
							} catch(Exception ex) {
								addressRowNo = 1; 
							}			  					
						%>	
  						
	  					<logic:iterate id="result" name="B_SSM_S01_Page_Form" property="addressResultSet">
							<TR>			  												  					
								<!-- Address RowNo -->
								<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
									<%= addressRowNo++ %>			  								
								</TD>	
								<!-- Address CustID -->
								<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
									<logic:notEmpty name="result" property="addressCustomerID">
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
			  													    "id_cust=" + ((HashMap<String, Object>)result).get("addressCustomerID") + '&' +
			  													    "mode=" + "READONLY" + "&" + "previous=B-SSM" + "";			    
		  								 %>				  								 
	  								 
		  								<script type="text/javascript" >						
		  								</script>  								
		  								<html:link href="<%=custIDLink %>" onclick=''>
		  									<bean:write name="result" property="addressCustomerID"/>
		  								</html:link>
									</logic:notEmpty>
									<logic:empty name="result" property="addressCustomerID">
	  									-
	  								</logic:empty>
								</TD>
								<!-- Address Customer Name -->
								<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
									<logic:notEmpty name="result" property="addressCustomerName">
										<bean:write name="result" property="addressCustomerName"/>
									</logic:notEmpty>
									<logic:empty name="result" property="addressCustomerName">
	  									-
	  								</logic:empty>
								</TD>
								<!-- Address SubID -->
								<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
									<logic:notEmpty name="result" property="addressSubscriptionID">
										<%
		  									String subIDLink =  request.getContextPath() + 
		  													    "/B_SSM_S01/B_SSM_S01_SubInfo_Displaying_Action.do?" +
		  														"customerPlanID=" + ((HashMap<String, Object>)result).get("addressCustomerPlanID") + '&' +
		  														"subscriptionID=" + ((HashMap<String, Object>)result).get("addressSubscriptionID") + '&' +
		  														"customerID=" + ((HashMap<String, Object>)result).get("addressCustomerID") + '&' +
		  														"customerName=" + ((HashMap<String, Object>)result).get("addressCustomerName") + '&' +
		  													   	"fromPopup=" + "noPopup";
		  								 %>				  								 				  								 
		  								<script type="text/javascript" >				  													  													  									
		  								</script>				  											  								
		  								<html:link href="<%=subIDLink %>" onclick=''>
											<bean:write name="result" property="addressSubscriptionID"/>
										</html:link>
									</logic:notEmpty>
									<logic:empty name="result" property="addressSubscriptionID">
	  									-
	  								</logic:empty>
								</TD>												  					
								<!-- Address Service -->
								<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
									<logic:notEqual value="0" name="accessRightBean">
										<%
		  									String serviceNameLink = request.getContextPath() + 
				  													"/B_CPM/B_CPM_S05InitBL.do?customerPlan.fromScreen=SSM&customerPlan.billType=SSM&" +
				  													"customerPlan.idCustPlan=" + ((HashMap<String, Object>)result).get("addressCustomerPlanID");
		  								 	String popUpFeatures = "toolbar=no, status=no, menubar=no,location=no, " +
				  												   "scrollbars=yes, resizable=yes, width=1100, height=770";
		  								 %>
		  								<html:link href='javascript:void(0);' 
		  										   onclick='<%= "popUpWithSpecificFeatures(\'" + serviceNameLink + "\',\'" + 
	  			    		 								 							   popUpFeatures + "\', 1100, 770);" %>'>
											<!-- <bean:write name="result" property="addressService"/>  -->
											Service
										</html:link>
									</logic:notEqual>
									<logic:empty name="result" property="addressService">
	  									-
	  								</logic:empty> 
								</TD>
								<!-- Address Cust Tel -->
								<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
								    <logic:notEmpty name="result" property="addressITContactNoTelNoList">
										<bean:define id="addressITContactNoTelNoListDef" name="result"
													 property="addressITContactNoTelNoList"
													 type="java.util.List"/>
										<% int addressITContactNoTelNoCount = 0; %>
										<logic:iterate id="addressITContactNoTelNoField" name="addressITContactNoTelNoListDef">
											<logic:empty name="addressITContactNoTelNoField">
											    -
											</logic:empty>
											<logic:notEmpty name="addressITContactNoTelNoField">
											    <bean:write name="addressITContactNoTelNoField"/>
											</logic:notEmpty>
											<% 
												if (addressITContactNoTelNoCount++ < addressITContactNoTelNoListDef.size() - 1) {
													out.print("<br/>");
												};
											 %>
										</logic:iterate>
									</logic:notEmpty>	
									<logic:empty name="result" property="addressITContactNoTelNoList">
									    -
									</logic:empty>
								</TD>	
								<!-- Address Fax No -->
								<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
								    <logic:notEmpty name="result" property="addressCustomerFaxNoList">
										<bean:define id="addressCustomerFaxNoListDef" name="result"
													 property="addressCustomerFaxNoList"
													 type="java.util.List"/>
										<% int addressCustomerFaxNoCount = 0; %>
										<logic:iterate id="addressCustomerFaxNoField" name="addressCustomerFaxNoListDef">
											<logic:empty name="addressCustomerFaxNoField">
											    -
											</logic:empty>
											<logic:notEmpty name="addressCustomerFaxNoField">
											    <bean:write name="addressCustomerFaxNoField"/>
											</logic:notEmpty>
											<% 
												if (addressCustomerFaxNoCount++ < addressCustomerFaxNoListDef.size() - 1) {
													out.print("<br/>");
												};
											 %>
										</logic:iterate>
									</logic:notEmpty>	
									<logic:empty name="result" property="addressITContactNoTelNoList">
									    -
									</logic:empty>
								</TD>
								<!-- Address Email -->
								<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
									<logic:notEmpty name="result" property="addressEmail">
										<bean:write name="result" property="addressEmail"/>
									</logic:notEmpty>
									<logic:empty name="result" property="addressEmail">
										-
									</logic:empty>
								</TD>
								<TD class="B_SSM_S01_Page_Form_ResultPanel_DisplayResult_Table_Col_UpVAlign">
									<c:choose>
										<c:when test="${'CORP' ne result.customerType}">
											<bean:write name="result" property="addressICNo"/>
										</c:when>
										<c:otherwise>
											-
										</c:otherwise>
									</c:choose>
								</TD>
							</TR>
							<tr>
								<td colspan="2">
									&nbsp;
								</td>
								<td colspan="7">
									<div>
										<table class="B_SSM_S01_Page_Form_ContactResultPanel_DisplayResult_Table">
											<col width="20%"/>
	  				    					<col width="80%"/>
											<tr class="B_SSM_S01_Page_Form_ContactResultPanel_DisplayResult_Table_TrCol">
												<TH class="B_SSM_S01_Page_Form_ContactResultPanel_DisplayResult_Table_HeaderCol">
							  						<bean:message key="B_SSM_S01_Page.TabPanel.ContactInfoFieldSet.AddressType"/> 
							  					</TH>
												<TH class="B_SSM_S01_Page_Form_ContactResultPanel_DisplayResult_Table_HeaderCol">
							  						<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.Address.Text"/> 
							  					</TH>
											</tr>
											<logic:notEmpty name="result" property="addressRA">
												<tr class="B_SSM_S01_Page_Form_ContactResultPanel_DisplayResult_Table_TrCol">
													<td>Registered Address</td>
													<td><bean:write name="result" property="addressRA"/></td>
												</tr>
											</logic:notEmpty>
											<logic:notEmpty name="result" property="addressRA">
												<tr class="B_SSM_S01_Page_Form_ContactResultPanel_DisplayResult_Table_TrCol">
													<td>Billing Address</td>
													<td><bean:write name="result" property="addressBA1"/></td>
												</tr>
											</logic:notEmpty>
											<logic:notEmpty name="result" property="addressBA2">
												<tr class="B_SSM_S01_Page_Form_ContactResultPanel_DisplayResult_Table_TrCol">
													<td>Billing Address 2</td>
													<td><bean:write name="result" property="addressBA2"/></td>
												</tr>
											</logic:notEmpty>
											<logic:notEmpty name="result" property="addressBA3">
												<tr class="B_SSM_S01_Page_Form_ContactResultPanel_DisplayResult_Table_TrCol">
													<td>Billing Address 3</td>
													<td><bean:write name="result" property="addressBA3"/></td>
												</tr>
											</logic:notEmpty>
											<logic:notEmpty name="result" property="addressBA4">
												<tr class="B_SSM_S01_Page_Form_ContactResultPanel_DisplayResult_Table_TrCol">
													<td>Billing Address 4</td>
													<td><bean:write name="result" property="addressBA4"/></td>
												</tr>
											</logic:notEmpty>
											<logic:notEmpty name="result" property="addressCA">
												<tr class="B_SSM_S01_Page_Form_ContactResultPanel_DisplayResult_Table_TrCol">
													<td>Correnspondance Address</td>
													<td><bean:write name="result" property="addressCA"/></td>
												</tr>
											</logic:notEmpty>
											<logic:notEmpty name="result" property="addressTA">
												<tr class="B_SSM_S01_Page_Form_ContactResultPanel_DisplayResult_Table_TrCol">
													<td>Technical Address</td>
													<td><bean:write name="result" property="addressTA"/></td>
												</tr>
											</logic:notEmpty>
										</table>
									</div>
								</td>
							</tr>
							<c:choose>
								<c:when test="${'CORP' eq result.customerType &&  fn:length(result.contactTypeInfoList) > 0}">
									<tr>
										<td colspan="2">
											&nbsp;
										</td>
										<td colspan="7">
											<table class="B_SSM_S01_Page_Form_ContactResultPanel_DisplayResult_Table">
												<col width="15%"/>
		  				    					<col width="20%"/>
		  				    					<col width="13%"/>
		  				    					<col width="13%"/>
		  				    					<col width="13%"/>
		  				    					<col width="26%"/>
												<tr class="B_SSM_S01_Page_Form_ContactResultPanel_DisplayResult_Table_TrCol">
													<TH class="B_SSM_S01_Page_Form_ContactResultPanel_DisplayResult_Table_HeaderCol">
								  						<bean:message key="B_SSM_S01_Page.TabPanel.ContactInfoFieldSet.ContactType"/> 
								  					</TH>
													<TH class="B_SSM_S01_Page_Form_ContactResultPanel_DisplayResult_Table_HeaderCol">
								  						<bean:message key="B_SSM_S01_Page.TabPanel.ContactInfoFieldSet.ContactName"/> 
								  					</TH>
								  					<TH class="B_SSM_S01_Page_Form_ContactResultPanel_DisplayResult_Table_HeaderCol">
								  						<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.TelNo.Text"/> 
								  					</TH>
													<TH class="B_SSM_S01_Page_Form_ContactResultPanel_DisplayResult_Table_HeaderCol">
								  						<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.FaxNo.Text"/> 
								  					</TH>
								  					<TH class="B_SSM_S01_Page_Form_ContactResultPanel_DisplayResult_Table_HeaderCol">
								  						<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.MobileNo.Text"/> 
								  					</TH>
													<TH class="B_SSM_S01_Page_Form_ContactResultPanel_DisplayResult_Table_HeaderCol">
								  						<bean:message key="B_SSM_S01_Page.Form.ResultPanel.Header.EmailToCc.Text"/> 
								  					</TH>
												</tr>
												<logic:iterate id="contactTypeInfo" name="result" property="contactTypeInfoList" indexId="id">
													<tr class="B_SSM_S01_Page_Form_ContactResultPanel_DisplayResult_Table_TrCol">
													    <logic:notEmpty name="contactTypeInfo" property="contactType">
													    	<td><bean:write name="contactTypeInfo" property="contactType"/></td>
													    </logic:notEmpty>
													    <logic:notEmpty name="contactTypeInfo" property="contactName">
													    	<td><bean:write name="contactTypeInfo" property="contactName"/></td>
													    </logic:notEmpty>
													    <logic:empty name="contactTypeInfo" property="contactName">
													    	<td>&nbsp;</td>
													    </logic:empty>
													    <logic:notEmpty name="contactTypeInfo" property="contactTelNo">
													    	<td><bean:write name="contactTypeInfo" property="contactTelNo"/></td>
													    </logic:notEmpty>
													    <logic:empty name="contactTypeInfo" property="contactTelNo">
													    	<td>&nbsp;</td>
													    </logic:empty>
													    <logic:notEmpty name="contactTypeInfo" property="contactFaxNo">
													    	<td><bean:write name="contactTypeInfo" property="contactFaxNo"/></td>
													    </logic:notEmpty>
													    <logic:empty name="contactTypeInfo" property="contactFaxNo">
													    	<td>&nbsp;</td>
													    </logic:empty>
													    <logic:notEmpty name="contactTypeInfo" property="contactMobileNo">
													    	<td><bean:write name="contactTypeInfo" property="contactMobileNo"/></td>
													    </logic:notEmpty>
													    <logic:empty name="contactTypeInfo" property="contactMobileNo">
													    	<td>&nbsp;</td>
													    </logic:empty>
													    <logic:notEmpty name="contactTypeInfo" property="contactEmailToCcList">
															<bean:define id="contactEmailToCcListDef" name="contactTypeInfo"
																		 property="contactEmailToCcList"
																		 type="java.util.List"/>
															<% int contactEmailToCcCount = 0; %>
															<td>
																<logic:iterate id="contactEmailToCcField" name="contactEmailToCcListDef">
																    <logic:empty name="contactEmailToCcField">
																	    &nbsp;
																	</logic:empty>
																	<logic:notEmpty name="contactEmailToCcField">
																	    <bean:write name="contactEmailToCcField"/>
																	</logic:notEmpty>
																	<% 
																		if (contactEmailToCcCount++ < contactEmailToCcListDef.size() - 1) {
																			out.print("<br/>");
																		};
																	 %>
																</logic:iterate>
															</td>
														</logic:notEmpty>	
														<logic:empty name="result" property="contactEmailToCcList">
														    <td>&nbsp;</td>
														</logic:empty>
													</tr>
												</logic:iterate>
											</table>
										</td>
									</tr>
								</c:when>
							</c:choose>						  					
	  					</logic:iterate>
	  				</logic:notEmpty>
  				</TABLE>			  			
	  		</div>	
	  	</div>
  	</div>
</body>
</html>