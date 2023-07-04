<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page import="java.util.HashMap" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="nttdm.bsys.b_ssm.utility.BLogicUtils"%>
<%@page import="nttdm.bsys.b_ssm.s02.blogic.B_SSM_S02v_BUtils"%>
<%@page import="nttdm.bsys.b_ssm.s02.blogic.B_SSM_S02e_BUtils"%>
<%@page import="nttdm.bsys.common.fw.BillingSystemUserValueObject"%>
<%@page import="nttdm.bsys.common.util.CommonUtils"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
	
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/stylesheet/tabcontent.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/B_SSM_S02/css/b_ssm_s02_css.css"/>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/tabcontent.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/B_SSM/js/b_ssm_js.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/B_SSM_S02/js/b_ssm_s02_js.js"></script>

	<bean:define id="autoPolicyText" type="java.lang.String">
		<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Firewall.AutoPolicy.Text"/>
	</bean:define>
	<bean:define id="eRR4SC002" type="java.lang.String">
		<bean:message key="info.ERR4SC002"/>
	</bean:define>
	
	<script type="text/javascript">	
		var confirmMessage = '<bean:message key="info.ERR4SC002"/>';
		var confirmExitMessage = '<bean:message key="info.ERR4SC001"/>';
		var maxLengthErrorMessage = '<bean:message key="errors.ERR1SC009"/>';
		var mailAcountEmptyErrorMessage = '<bean:message key="errors.ERR1SC096"/>';
		var mailAccountDupMessage = '<bean:message key="errors.ERR1SC006" arg0="Mail Account (Sub Plan)"/>';
		var addtionalOptionDupMessage = '<bean:message key="errors.ERR1SC006" arg0="Additional Option"/>';
		var mailboxQtyDupMessage = '<bean:message key="errors.ERR1SC006" arg0="Mailbox Qty"/>';
		var virusScanDupMessage = '<bean:message key="errors.ERR1SC006" arg0="Virus Scan"/>';
		var antiSpanDupMessage = '<bean:message key="errors.ERR1SC006" arg0="Anti Spam"/>';
		var junkMgmtDupMessage = '<bean:message key="errors.ERR1SC006" arg0="Junk Management"/>';
		var autoPolicyMessage = '<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.Firewall.AutoPolicy.Text"/>';
		var accessAccountEmptyErrorMessage = '<bean:message key="errors.ERR1SC005" arg0="Access Account"/>';
		var accessPasswordEmptyErrorMessage = '<bean:message key="errors.ERR1SC005" arg0="Access Password"/>';
		var macInfo = {};
		macInfo[""] = "";
	</script>			
</head>
<!-- Get tab position -->
<% 
	int scriptTabPosition = 0;
	int scriptProcessMode = 1;
%>
<logic:present name="B_SSM_S02_Page_Form">
	<bean:define id="pageForm" name="B_SSM_S02_Page_Form" type="jp.terasoluna.fw.web.struts.form.DynaValidatorActionFormEx"/>
	<% 
		// Tab position
		try {
			scriptTabPosition = Integer.parseInt((String)pageForm.get("tabPosition"));			
		} catch (Exception ex) {
			scriptTabPosition = 0;
		}
		// Process mode
		try {
			scriptProcessMode = Integer.parseInt((String)pageForm.get("processMode"));			
		} catch (Exception ex) {
			scriptProcessMode = 1;
		}
	 %>
</logic:present>
<body onload="initB_SSM_S02_Page('B_SSM_02_TabSet', <%=scriptTabPosition%>);
			  removeSpaceOfTextAreas();">
    <!-- check access right START -->
    <%
        BillingSystemUserValueObject uvo = (BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT");
        String accessRight = CommonUtils.getAccessRight(uvo, "B-CPM");
    %>
    <bean:define id="accessRightCPM" value="<%=accessRight %>"/>
	<bean:define id="pageForm" name="B_SSM_S02_Page_Form" type="jp.terasoluna.fw.web.struts.form.DynaValidatorActionFormEx"/>
	<div id="B_SSM_S02_Page_Content">
		<!-- khaln -->
		<t:defineCodeList id="LIST_EQUIP_MAXPOWER"/>
		<t:defineCodeList id="LIST_EQUIP_LOCATION"/>
		<t:defineCodeList id="LIST_EQUIP_SUITE"/>
		<!-- khaln end -->
		
		<ts:form  action="/B_SSM_S02_Request_Process_Action.do" onsubmit="return validateB_SSM_S02eForm();">			
			<!--******************************** Header ************************************-->
			<!-- Header title -->
			<div class="B_SSM_S02_Page_Form_Header">
				&nbsp;<bean:message key="B_SSM_S02_Page.Form.Header.Title"/>
			</div>	
			
			<!-- Hidden info fields -->
			<html:hidden property="fromPopup" value="noPopup"/>
			<bean:define id="mailAccountTotalNo" name="B_SSM_S02_Page_Form" property="mailAccountTotal"></bean:define>
			<html:hidden property="totalMaillAccount" styleId="totalMaillAccount" value="${mailAccountTotalNo}"/>	
			<html:hidden property="idPlanGrpList" name="B_SSM_S02_Page_Form"/>
			<div id="hiddenInfoFields">
				<!-- Field indicates tab position -->
				 <html:text styleId="tabPositionField" 
				 			styleClass="B_SSM_S02_Page_Form_TextBox"  
				 			name="B_SSM_S02_Page_Form" 
				 			property="tabPosition" 
				 			style="display: none;">
				 	<%=scriptTabPosition%>
				 </html:text>
				 
				 <!-- Field indicates process mode -->
				 <html:text styleId="processModeField" 
				 			styleClass="B_SSM_S02_Page_Form_TextBox"  
				 			name="B_SSM_S02_Page_Form" 
				 			property="processMode" 
				 			style="display: none;">
				 	<%=scriptProcessMode%>
				 </html:text>
				 
				 <!-- Field indicates is popup -->
				 <html:text styleId="processModeField" 
				 			styleClass="B_SSM_S02_Page_Form_TextBox"  
				 			name="B_SSM_S02_Page_Form" 
				 			property="isPopUp" 
				 			style="display: none;">
				 	<bean:write name="B_SSM_S02_Page_Form" property="isPopUp"/>
				 </html:text>
				 
				 <!-- Field indicates subscriptionID -->
				 <html:text styleId="subscriptionID" 
				 			styleClass="B_SSM_S02_Page_Form_TextBox"  
				 			name="B_SSM_S02_Page_Form" 
				 			property="subscriptionID" 
				 			style="display: none;">
				 	<bean:write name="B_SSM_S02_Page_Form" property="subscriptionID"/>
				 </html:text>
				 
				 <!-- Field indicates customerID -->
				 <html:text styleId="customerID" 
				 			styleClass="B_SSM_S02_Page_Form_TextBox"  
				 			name="B_SSM_S02_Page_Form" 
				 			property="customerID" 
				 			style="display: none;">
				 	<bean:write name="B_SSM_S02_Page_Form" property="customerID"/>
				 </html:text>
				 
				 <!-- Field indicates customerName -->
				 <html:text styleId="customerNameID" 
				 			styleClass="B_SSM_S02_Page_Form_TextBox"  
				 			name="B_SSM_S02_Page_Form" 
				 			property="customerName" 
				 			style="display: none;">
				 	<bean:write name="B_SSM_S02_Page_Form" property="customerName"/>
				 </html:text>
				 
				 <!-- Field indicates customerPlanID -->
				 <html:text styleId="customerPlanID" 
				 			styleClass="B_SSM_S02_Page_Form_TextBox"  
				 			name="B_SSM_S02_Page_Form" 
				 			property="customerPlanID" 
				 			style="display: none;">
				 	<bean:write name="B_SSM_S02_Page_Form" property="customerPlanID"/>
				 </html:text>
			 </div>
			 		 			 			 
			 <!-- Header Subscription Info -->
			 <div class="B_SSM_S02_Page_Form_HeaderSubInfo">
			 	<TABLE class="B_SSM_S02_Page_Form_HeaderSubInfo_Table textAlignTop">
			 		<col width="20%">
			 		<col width="1%">
			 		<col width="29%">
 					<col width="20%">
 					<col width="1%">
 					<col width="29%">
 					<TR>
 						<TD class="B_SSM_S02_Page_Form_HeaderSubInfo_Table_Col_Sub">
			 				<bean:message key="B_SSM_S02_Page.HeaderInfo.CustomerName.Text"/>
			 			</TD>
			 			<TD>
			 				:&nbsp;
			 			</TD>
			 			<TD style="width:270px;word-wrap: break-word;white-space : normal">
			 				<bean:write name="B_SSM_S02_Page_Form" property="customerName"/>
			 			</TD>
			 			<TD class="B_SSM_S02_Page_Form_HeaderSubInfo_Table_Col_Sub">
			 				<bean:message key="B_SSM_S02_Page.HeaderInfo.SubscriptionID.Text"/>
			 			</TD>
			 			<TD>
			 				:&nbsp;
			 			</TD>
			 			<TD>
			 				<bean:write name="B_SSM_S02_Page_Form" property="subscriptionID"/>
			 			</TD>
 					</TR>
 					<TR>
 						<TD class="B_SSM_S02_Page_Form_HeaderSubInfo_Table_Col_Sub">
			 				<bean:message key="B_SSM_S02_Page.HeaderInfo.CustomerID.Text"/>
			 			</TD>
			 			<TD>
			 				:&nbsp;
			 			</TD>
			 			<TD>
			 				<bean:write name="B_SSM_S02_Page_Form" property="customerID"/>
			 			</TD>
			 			<TD class="B_SSM_S02_Page_Form_HeaderSubInfo_Table_Col_Sub">
                            <logic:notEqual value="0" name="accessRightCPM">
			 				<a href='<%=request.getContextPath()+ 
										"/B_CPM/B_CPM_S02ViewBL.do?" +
										"customerPlan.idCustPlan=" + 
										BLogicUtils.emptyValue(pageForm.get("customerPlanID"),"")%>'>
			 					<bean:message key="B_SSM_S02_Page.HeaderInfo.CustomerPlanInformation.Text"/>
			 				</a>
                            </logic:notEqual>
			 			</TD>
 					</TR>
			 	</TABLE>
			 </div>
			
			<!--******************************************** Tabs ***************************************************-->
			<!-- Get arrayInfo -->
			<bean:define id="infoIDArray" name="B_SSM_S02_Page_Form" property="infoIDArray" type="java.util.List<Integer>"/>
			<bean:define id="accessAccountFlag" name="B_SSM_S02_Page_Form" property="accessAccountFlag" type="java.lang.Integer"/>
			<bean:define id="accessPasswordFlag" name="B_SSM_S02_Page_Form" property="accessPasswordFlag" type="java.lang.Integer"/>
			<html:hidden property="accessAccountFlag" name="B_SSM_S02_Page_Form" />
			<html:hidden property="accessPasswordFlag" name="B_SSM_S02_Page_Form" />
			<html:hidden property="accessAccountTest" name="B_SSM_S02_Page_Form" />
			<div class="B_SSM_S02_Page_Form_Tabs">
			  	<ul id="B_SSM_02_TabSet" class="shadetabs">
					
					<!-- General tab -->
					<li class="B_SSM_S02_Page_Form_Tabs_Tab_Title">
						<a href="#" 
						   rel='<%=B_SSM_S02e_BUtils.isGeneralTabEnable(infoIDArray)? 
							   "generalTab":""%>'
						   onfocus="setTextField('tabPositionField', 0);"
						   style='<%=!B_SSM_S02e_BUtils.isGeneralTabEnable(infoIDArray)? 
								 "background: #f4f3ef; color: white;":""%>'>
							<bean:message key="B_SSM_S02_Page.Tab.General.Title"/>
						</a>
					</li>
					
					<!-- Mail Address tab -->
					<li class="B_SSM_S02_Page_Form_Tabs_Tab_Title">
						<a href="#" 
						   rel='<%=B_SSM_S02e_BUtils.isAddressTabEnable(infoIDArray)? 
							   "mailAddressTab":""%>'
						   onfocus="setTextField('tabPositionField', 1);"
						   style='<%=!B_SSM_S02e_BUtils.isAddressTabEnable(infoIDArray)? 
								 "background: #f4f3ef; color: white;":""%>'>
							<bean:message key="B_SSM_S02_Page.Tab.MailAddress.Title"/>
						</a>
					</li>
					
					<!-- Rack/Equipment Location tab -->
					<li class="B_SSM_S02_Page_Form_Tabs_Tab_Title">
						<a href="#" 
						   rel='<%=B_SSM_S02e_BUtils.isRackEquipmentLocationTabEnable(infoIDArray)? 
							   "rackEquipmentLocationTab":""%>'
						   onfocus="setTextField('tabPositionField', 2);"
					       style='<%=!B_SSM_S02e_BUtils.isRackEquipmentLocationTabEnable(infoIDArray)? 
								 "background: #f4f3ef; color: white;":""%>'>
							<bean:message key="B_SSM_S02_Page.Tab.Rack_EquipmentLocation.Title"/>
						</a>
					</li>
					
					<!-- IT Contact tab -->
					<li class="B_SSM_S02_Page_Form_Tabs_Tab_Title">
						<a href="#" 
						   rel='<%=B_SSM_S02e_BUtils.isITContactTabEnable(infoIDArray)? 
							   "iTContactTab":""%>'
						   onfocus="setTextField('tabPositionField', 3);"
						   style='<%=!B_SSM_S02e_BUtils.isITContactTabEnable(infoIDArray)? 
								 "background: #f4f3ef; color: white;":""%>'>
							<bean:message key="B_SSM_S02_Page.Tab.ITContact.Title"/>
						</a>
					</li>
					
					<!-- Server Info tab -->
					<li class="B_SSM_S02_Page_Form_Tabs_Tab_Title">
						<a href="#" 
						   rel='<%=B_SSM_S02e_BUtils.isServerInfoTabEnable(infoIDArray)? 
							   "serverInfoTab":""%>'
						   onfocus="setTextField('tabPositionField', 4);"
						   style='<%=!B_SSM_S02e_BUtils.isServerInfoTabEnable(infoIDArray)? 
								 "background: #f4f3ef; color: white;":""%>'>
							<bean:message key="B_SSM_S02_Page.Tab.ServerInfo.Title"/>
						</a>
					</li>
					
					<!-- Firewall tab -->
					<li class="B_SSM_S02_Page_Form_Tabs_Tab_Title">
						<a href="#" 
						   rel='<%=B_SSM_S02e_BUtils.isFirewallTabEnable(infoIDArray)? 
							   "firewallTab":""%>'
						   onfocus="setTextField('tabPositionField', 5);"
						   style='<%=!B_SSM_S02e_BUtils.isFirewallTabEnable(infoIDArray)? 
								 "background: #f4f3ef; color: white;":""%>'>
							<bean:message key="B_SSM_S02_Page.Tab.Firewall.Title"/>
						</a>
					</li>
					
					<!-- CPE Configuration tab -->
					<li class="B_SSM_S02_Page_Form_Tabs_Tab_Title">
						<a href="#" 
						   rel='<%=B_SSM_S02e_BUtils.isCPEConfigurationTabEnable(infoIDArray)? 
							   "CPEConfigurationTab":""%>'
						   onfocus="setTextField('tabPositionField', 6);"
						   style='<%=!B_SSM_S02e_BUtils.isCPEConfigurationTabEnable(infoIDArray)? 
								 "background: #f4f3ef; color: white;":""%>'>
							<bean:message key="B_SSM_S02_Page.Tab.CPEConfiguration.Title"/>
						</a>
					</li>
					
					<!-- DNS Zone tab -->
					<li class="B_SSM_S02_Page_Form_Tabs_Tab_Title">
						<a href="#" 
						   rel='<%=B_SSM_S02e_BUtils.isDNSZoneTabEnable(infoIDArray)? 
							   "dNSZoneTab":""%>'
						   onfocus="setTextField('tabPositionField', 7);"
						   style='<%=!B_SSM_S02e_BUtils.isDNSZoneTabEnable(infoIDArray)? 
								 "background: #f4f3ef; color: white;":""%>'>
							<bean:message key="B_SSM_S02_Page.Tab.DNSZone.Title"/>
						</a>
					</li>
					
					<!-- Teamwork tab -->
					<li class="B_SSM_S02_Page_Form_Tabs_Tab_Title">
						<a href="#" 
						   onfocus="setTextField('tabPositionField', 8);"
						   rel='<%=B_SSM_S02e_BUtils.isTeamworkTabEnable(infoIDArray)? 
								 "teamworkTab":""%>'						  
						   style='<%=!B_SSM_S02e_BUtils.isTeamworkTabEnable(infoIDArray)? 
								 "background: #f4f3ef; color: white;":""%>'>
							<bean:message key="B_SSM_S02_Page.Tab.Teamwork.Title"/>
						</a>
					</li>					
			  	</ul>	
		  	</div>
			
			<!--******************************************** Tab Contents *********************************************-->
			<!--/////////////////// General Tab Panel ////////////////////////-->
			<jsp:include page="B_SSM_S02e_general.jsp" flush="true"></jsp:include>
			
			<!--/////////////////// Mail Address Tab Panel ////////////////////////-->
			<jsp:include page="B_SSM_S02e_mailAddress.jsp" flush="true"></jsp:include>
			
			<!--/////////////////// Rack Equipment Location Tab Panel ////////////////////////-->
			<jsp:include page="B_SSM_S02e_rackEquipLoc.jsp" flush="true"></jsp:include>
			
			<!--/////////////////// IT Contact Tab Panel ////////////////////////-->
			<jsp:include page="B_SSM_S02e_itContact.jsp" flush="true"></jsp:include>
			
			<!--/////////////////// Server Info Tab Panel ////////////////////////-->
			<jsp:include page="B_SSM_S02e_serverInfo.jsp" flush="true"></jsp:include>
			
			<!--/////////////////// Firewall Tab Panel ////////////////////////-->
			<jsp:include page="B_SSM_S02e_firewall.jsp" flush="true"></jsp:include>
			
			<!--/////////////////// CPE Configuration Tab Panel ////////////////////////-->
			<jsp:include page="B_SSM_S02e_CPEConfig.jsp" flush="true"></jsp:include>
			
			<!--/////////////////// DNS Zone Tab Panel ////////////////////////-->
			<jsp:include page="B_SSM_S02e_DNSZone.jsp" flush="true"></jsp:include>
			
			<!--/////////////////// Teamwork Tab Panel ////////////////////////-->
			<jsp:include page="B_SSM_S02e_teamwork.jsp" flush="true"></jsp:include>
			<div style="height:5px;"></div>
			<!-- Memo remarks -->
			<div class="B_SSM_S02_Page_Form_Container" style="background-color: #f4f3ef;border: #dcdccf 1px solid;">
				<TABLE class="B_SSM_S02_Page_Form_Table">
					<TR>
						<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol_UpVAlign" style="width:180px;">
							<bean:message key="B_SSM_S02_Page.TabPanel.CompletionReportRemark.Text"/>
						</TD>							
						<TD style="vertical-align: bottom;">								
							<div style="float: left; 
										text-align: center; 
										vertical-align: middle;">
								&nbsp;&nbsp;&nbsp;:
							</div>
							<div style="float: left; ">
								&nbsp;
								<textarea name="completionReportRemark" 
									      id="completionReportRemarkID"
									      rows="5" cols="60">
									<bean:write name="B_SSM_S02_Page_Form" property="completionReportRemark"/>
								</textarea>
								
							</div>
						</TD>
					</TR>
					<TR>
						<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol_UpVAlign" style="width:180px;">
							<bean:message key="B_SSM_S02_Page.TabPanel.MemoRemarksInteralUseOnly.Text"/>
						</TD>							
						<TD style="vertical-align: bottom;">								
							<div style="float: left; 
										text-align: center; 
										vertical-align: middle;">
								&nbsp;&nbsp;&nbsp;:
							</div>
							<div style="float: left; ">
								&nbsp;
								<textarea name="memoRemarks" 
									      id="memoRemarksID"
									      rows="5" cols="60">
									<bean:write name="B_SSM_S02_Page_Form" property="memoRemarks"/>
								</textarea>
							</div>
						</TD>
					</TR>
				</TABLE>
			</div>
		
			<!-- Buttons -->	
	  		<div class="B_SSM_S02_Page_Form_Buttons">		  
			  	<logic:equal name="B_SSM_S02_Page_Form" property="accessType" value="2">
				  	<% if (infoIDArray != null && infoIDArray.size() != 0) { %>
				  	<!-- Save Btn -->				  
				  	<ts:submit styleId="saveBtnID" 
				  			   styleClass="B_SSM_S02_Page_Form_Buttons_Text"
				  			   onclick="setTextField('processModeField', 2);return checkMailContent();">
				  		<bean:message key="B_SSM_S02_Page.Form.SaveBtn.Text"/>
				  	</ts:submit>	
				  	<%} %>	
			  	</logic:equal>
			  		   				  				  
			  	<!-- Exit Btn -->
			  	<logic:equal name="B_SSM_S02_Page_Form" property="isPopUp" value="1">
				  	<html:button property="button" 
				  			   	 styleId="exitBtnID"
				  			     styleClass="B_SSM_S02_Page_Form_Buttons_Text"
				  			     onclick="doExitAction(true);">
				  		<bean:message key="B_SSM_S02_Page.Form.ExitBtn.Text"/> 	
				  	</html:button>
			  	</logic:equal>
			  	<logic:empty name="B_SSM_S02_Page_Form" property="isPopUp">
                     <%
                        String viewURL =  request.getContextPath() + 
                                            "/B_SSM_S01/B_SSM_S01_SubInfo_Displaying_Action.do?" +
                                            "customerPlanID=" + pageForm.get("customerPlanID") + '&' +
                                            "subscriptionID=" + pageForm.get("subscriptionID") + '&' +
                                            "customerID=" + pageForm.get("customerID") + '&' +
                                            "customerName=" + pageForm.get("customerName") + '&' +
                                            "fromPopup=" + "noPopup";
                     %>
				  	<input type="button" name="button" 
				  			     id="exitBtnID"
				  			     class="B_SSM_S02_Page_Form_Buttons_Text"
				  			     onclick="doExitToView('<%= viewURL%>')" 
				  			     value="<bean:message key="B_SSM_S02_Page.Form.ExitBtn.Text"/>"/>
			  	</logic:empty>
		  	</div> 	
		  	
		  	<!--******************************** Status ************************************-->			
			 <div id="statusMessagePanelID" class="B_SSM_S02_Page_Form_Status">
				 <logic:notEmpty name="B_SSM_S02_Page_Form" property="editedStatus">
				 	<logic:equal value="success"  name="B_SSM_S02_Page_Form" property="editedStatus">
				 		<div class="Success">
				 			<bean:message key="B_SSM_S02_Page.StatusMessage.Save.Success.Text"/>	
				 		</div>	
				 	</logic:equal>
				 	<logic:equal value="fail" name="B_SSM_S02_Page_Form" property="editedStatus">
				 		<div class="Error">
				 			<bean:message key="B_SSM_S02_Page.StatusMessage.Save.Fail.Text"/>	
				 		</div>	
				 	</logic:equal>
				 </logic:notEmpty>
			 </div>							
		</ts:form>
		<div class="error">
			<span style="color: red;">
				<bean:write name="B_SSM_S02_Page_Form" property="errorMessage"/>
			</span>
		</div>
		
		<!-- KHALN -->
		<html:select property="maxPower" value="" styleId="cboMaxPowerClone" style="display:none;">
			<option value=""><bean:message key="B_SSM_S02_Page.BlankSelectText"/></option>
			<html:options collection="LIST_EQUIP_MAXPOWER" property="id" labelProperty="name" />
		</html:select>
		<html:select property="equipmentLocation" value="" styleId="cboEquipLocationClone" style="display:none;">
			<html:option value=""><bean:message key="B_SSM_S02_Page.BlankSelectText"/></html:option>
			<html:options collection="LIST_EQUIP_LOCATION" property="id" labelProperty="name"/>
		</html:select>
		<html:select property="equipmentSuite" value="" styleId="cboEquipSuiteClone" style="display:none;">
			<html:option value=""><bean:message key="B_SSM_S02_Page.BlankSelectText"/></html:option>
			<html:options collection="LIST_EQUIP_SUITE" property="id" labelProperty="name"/>
		</html:select>
	</div>
</body>
</html>