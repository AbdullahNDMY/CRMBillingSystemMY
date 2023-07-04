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
	
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/stylesheet/tabcontent.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/B_SSM_S01/css/b_ssm_s01_css.css"/>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/tabcontent.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/B_SSM/js/b_ssm_js.js"></script>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/B_SSM_S01/js/b_ssm_s01_js.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
	
	<bean:define id="blankSelectText" type="java.lang.String">
		<bean:message key="B_SSM_S01_Page.BlankSelectText"/>
	</bean:define>
	<%String blankStr = blankSelectText; %>
	
	<script type="text/javascript">			
		var BLANK_STRING = '<%=blankStr %>';		
		var serviceValues = {};
		var planValues = {};
		var planDetailValues = {};
		var initialValues = {};
		var blankSet = new Array();
		var blankOption = {};
		blankOption['value'] = "";
		blankOption['text'] = BLANK_STRING;
		blankSet.push(blankOption);
		serviceValues[""] = blankSet;	
		planValues[""] = blankSet;
		planDetailValues[""] = blankSet;
	</script>			
</head>
<!-- Get tab position -->
<% 
	int scriptTabPosition = 0;
	int scriptProcessMode = 0;
%>

<logic:present name="B_SSM_S01_Page_Form" >
	<bean:define id="pageForm" name="B_SSM_S01_Page_Form" type="jp.terasoluna.fw.web.struts.form.DynaValidatorActionFormEx"/>
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
			scriptProcessMode = 0;
		}
	 %>
</logic:present>

	<%
		BillingSystemUserValueObject uvo = (BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT");
	 	String accessRight = CommonUtils.getAccessRight(uvo, "B-CPM");
	%>
<bean:define id="accessRightBean" value="<%=accessRight %>"/>
<body id="b" onload="initMain();;initB_SSM_S01_Page('B_SSM_01_TabSet', <%=scriptTabPosition%>); 
			  activateB_SSM_S01Fields();">
	<div id="B_SSM_S01_Page_Content">
		<ts:form  action="/B_SSM_S01_Request_Process_Action.do" styleId="searchForm">	
			<t:defineCodeList id="LIST_PLANSTATUS"/>				
			<!--************************************ Header ****************************************-->
			<div class="B_SSM_S01_Page_Form_Header">
				<bean:message key="B_SSM_S01_Page.Form.Header.Title"/>
			</div>
			
			<!-- Field indicates tab position -->
			 <html:text styleId="tabPositionField" name="B_SSM_S01_Page_Form" property="tabPosition" style="display: none;">
			 	<%=scriptTabPosition%>
			 </html:text>
			 
			 <!-- Field indicates processing mode -->
			 <html:text styleId="processModeField" name="B_SSM_S01_Page_Form" property="processMode" style="display: none;">
			 	<%=scriptProcessMode%>	
			 </html:text>	
			 
			 <!-- Field indicates whether search starts -->
			 <html:text styleId="hasSearchStartedField" name="B_SSM_S01_Page_Form" property="hasSearchStarted" style="display: none;">			 	
			 </html:text>	
			 
			 <!-- Disable template calendar -->
		 	 <div id="DisableTemplateCalendar">
				<t:inputCalendar for="" format="dd/MM/yyyy"/>
			 </div>				 				 	
		 			
			<!--******************************************** Tabs ***************************************************-->
			<% 
				boolean isGeneralResultSetNull = true;
				boolean isResultSetNull = true;
				boolean isCoLocationResultSetNull = true;
				boolean isEmailResultSetNull = true;
				boolean isAddressResultSetNull = true;
				boolean isAllowExport = false;
			%>
			<logic:notEmpty name="B_SSM_S01_Page_Form" property="generalResultSet" >
		  		<% isGeneralResultSetNull = false; %>	
		  	</logic:notEmpty>
		  	<logic:notEmpty name="B_SSM_S01_Page_Form" property="resultSet" >
		  		<% isResultSetNull = false; %>	
		  	</logic:notEmpty>
		  	<logic:notEmpty name="B_SSM_S01_Page_Form" property="coLocationResultSet" >
		  		<% isCoLocationResultSetNull = false; %>	
		  	</logic:notEmpty>
		  	<logic:notEmpty name="B_SSM_S01_Page_Form" property="emailResultSet" >
		  		<% isEmailResultSetNull = false; %>	
		  	</logic:notEmpty>
		  	<logic:notEmpty name="B_SSM_S01_Page_Form" property="addressResultSet" >
		  		<% isAddressResultSetNull = false; %>	
		  	</logic:notEmpty>
		  	<logic:equal name="B_SSM_S01_Page_Form" property="accessType" value="2">
			  	<% isAllowExport = true; %>	
			</logic:equal>
			<div class="B_SSM_S01_Page_Form_Tabs">
			  	<ul id="B_SSM_01_TabSet" class="shadetabs">
					<!-- General tab -->
					<li class="B_SSM_S01_Page_Form_Tabs_Tab_Title">
						<a href="#" rel="generalTab" onfocus="setTextField('tabPositionField', 0);
														  activateB_SSM_S01_ResultPanelFields();
														  activateExportBtn(<%=isGeneralResultSetNull%>, <%=isAllowExport%>);">
							<bean:message key="B_SSM_S01_Page.Tab.General.Title"/>
						</a>
					</li>
					
					<!-- ISP tab -->
					<li class="B_SSM_S01_Page_Form_Tabs_Tab_Title">
						<a href="#" rel="iSPTab" onfocus="setTextField('tabPositionField', 1);
														  activateB_SSM_S01_ResultPanelFields();
														  activateExportBtn(<%=isResultSetNull%>, <%=isAllowExport%>);">
							<bean:message key="B_SSM_S01_Page.Tab.ISP.Title"/>
						</a>
					</li>
					
					<!-- Co-location tab -->
					<li class="B_SSM_S01_Page_Form_Tabs_Tab_Title">
					<a href="#" rel="coLocationTab" onfocus="setTextField('tabPositionField', '2');
															 activateB_SSM_S01_ResultPanelFields();
															 activateExportBtn(<%=isCoLocationResultSetNull%>, <%=isAllowExport%>);">
							<bean:message key="B_SSM_S01_Page.Tab.CoLocation.Title"/>
						</a>
					</li>
					
					<!-- Email tab -->
					<li class="B_SSM_S01_Page_Form_Tabs_Tab_Title">
						<a href="#" rel="emailTab" onfocus="setTextField('tabPositionField', '3');
														    activateB_SSM_S01_ResultPanelFields();
														    activateExportBtn(<%=isEmailResultSetNull%>, <%=isAllowExport%>);">
							<bean:message key="B_SSM_S01_Page.Tab.Email.Title"/>
						</a>
					</li>
					
					<!-- Address tab -->
					<li class="B_SSM_S01_Page_Form_Tabs_Tab_Title">
						<a href="#" rel="addressTab" onfocus="setTextField('tabPositionField', '4');
															  activateB_SSM_S01_ResultPanelFields();
															  activateExportBtn(<%=isAddressResultSetNull%>, <%=isAllowExport%>);">														      
							<bean:message key="B_SSM_S01_Page.Tab.Address.Title"/>
						</a>
					</li>
											
			  	</ul>
		  	</div>
		  	
		  	<!--******************************************** Pre handling contents *********************************************-->
		  	<script type="text/javascript">
			  	initialValues['generalService'] = "${param.generalService}";	
		  		initialValues['generalPlan'] = "${param.generalPlan}";
		  		initialValues['generalPlanDetail'] = "${param.generalPlanDetail}";
		  		initialValues['service'] = "${param.service}";	
		  		initialValues['plan'] = "${param.plan}";	
		  		initialValues['planDetail'] = "${param.planDetail}";	
		  		initialValues['coLocationService'] = "${param.coLocationService}";	
		  		initialValues['coLocationPlan'] = "${param.coLocationPlan}";
		  		initialValues['coLocationPlanDetail'] = "${param.coLocationPlanDetail}";
		  		initialValues['emailService'] = "${param.emailService}";
		  		initialValues['emailPlan'] = "${param.emailPlan}";
		  		initialValues['emailPlanDetail'] = "${param.emailPlanDetail}";
		  		initialValues['addressService'] = "${param.addressService}";	
		  		initialValues['addressPlan'] = "${param.addressPlan}";
		  		initialValues['addressPlanDetail'] = "${param.addressPlanDetail}";	  		
		  	</script>
		  	<logic:notEmpty property="categoryCodeList" name="B_SSM_S01_Page_Form">
		  		<logic:iterate id="category" property="categoryCodeList" name="B_SSM_S01_Page_Form">
		  			<script type="text/javascript">
		  				var serviceSet = new Array();
		  				var planSet = new Array();
		  				var planDetailSet = new Array();
		  				var blankOption = {};
		  				blankOption['value'] = "";
		  				blankOption['text'] = BLANK_STRING;
		  				/* serviceSet.push(blankOption);
		  				planSet.push(blankOption);
		  				planDetailSet.push(blankOption); */
		  			</script>
		  			<logic:iterate id="service" property="serviceCodeList" name="B_SSM_S01_Page_Form">			  			
			  			<logic:equal property="serviceGroupID" name="category" value="${service['serviceGroupID']}">
				  			<script type="text/javascript">
				  				var service = {};
				  				service['value'] = "${service['serviceID']}";
				  				service['text'] = "${service['serviceDescription']}";
				  				serviceSet.push(service);
				  			</script>	
			  			</logic:equal>
		  			</logic:iterate>
		  			<logic:iterate id="plan" property="planCodeList" name="B_SSM_S01_Page_Form">			  			
			  			<logic:equal property="serviceGroupID" name="category" value="${plan['serviceGroupID']}">
				  			<script type="text/javascript">
				  				var plan = {};				  				
				  				plan['value'] = "${plan['serviceID']}";
				  				plan['text'] = "${plan['serviceDescription']}";
				  				planSet.push(plan);
				  			</script>	
			  			</logic:equal>
		  			</logic:iterate>
		  			<logic:iterate id="planDetail" property="planDetailCodeList" name="B_SSM_S01_Page_Form">			  			
			  			<logic:equal property="serviceGroupID" name="category" value="${planDetail['serviceGroupID']}">
				  			<script type="text/javascript">
				  				var planDetail = {};				  				
				  				planDetail['value'] = "${planDetail['serviceID']}";
				  				planDetail['text'] = "${planDetail['serviceDescription']}";
				  				planDetailSet.push(planDetail);
				  			</script>	
			  			</logic:equal>
		  			</logic:iterate>
		  			<script type="text/javascript">
		  				serviceValues[${category['serviceGroupID']}] = serviceSet;	
		  				planValues[${category['serviceGroupID']}] = planSet;
		  				planDetailValues[${category['serviceGroupID']}] = planDetailSet;	
		  			</script>	
		  		</logic:iterate>
		  	</logic:notEmpty>
		  	
		  	<!--******************************************** Tab Contents *********************************************-->
		  	<!--/////////////////// General Tab Panel ////////////////////////-->
		  	<jsp:include page="B_SSM_S01_Page_generalCondition.jsp" flush="true"></jsp:include>
		  	
		  	<!--/////////////////// ISP Tab Panel ////////////////////////-->
		  	<jsp:include page="B_SSM_S01_Page_ISPCondition.jsp" flush="true"></jsp:include>
		  	
		  	<!--/////////////////// Colocation Tab Panel ////////////////////////-->
		  	<jsp:include page="B_SSM_S01_Page_coLocationCondition.jsp" flush="true"></jsp:include>
		  	
		  	<!--/////////////////// Email Tab Panel ////////////////////////-->
		  	<jsp:include page="B_SSM_S01_Page_emailCondition.jsp" flush="true"></jsp:include>
		  	
		  	<!--/////////////////// Address Tab Panel ////////////////////////-->
		  	<jsp:include page="B_SSM_S01_Page_addressCondition.jsp" flush="true"></jsp:include>
		  	
		  	<!--********************************** Error info *************************************
		  	<div class="B_SSM_S01_Page_Form_Errors">
		  		<ts:errors/>	
		  	</div>		-->
		  	  	
		  	<!-- ******************************************* Buttons ***************************************** -->
		  	<div class="B_SSM_S01_Page_Form_Buttons">		  
			  	<!-- Search Btn -->		  	
			  	<ts:submit styleId="searchBtnID"
			  	 		   styleClass="B_SSM_S01_Page_Form_Buttons_Text"
			  	 		   onclick="setTextField('processModeField', 0);showLoadingTipWindow();">
			  		<bean:message key="B_SSM_S01_Page.Form.SearchBtn.Text"/>
			  	</ts:submit>	 	
			  	<!-- Reset Btn -->	
			  	<html:button property="button" 
			  				 styleId="resetBtnID"
			  				 styleClass="B_SSM_S01_Page_Form_Buttons_Text"
			  				 onclick="resetClick('${pageContext.request.contextPath}',getTabPosition());">	
			  		<bean:message key="B_SSM_S01_Page.Form.ResetBtn.Text"/> 
			  	</html:button>
			  	<!-- Export Result Btn -->				  	
			  	<% 
			  		boolean isAllowDisplay = false;
				  	if (scriptTabPosition == 0 && !isGeneralResultSetNull) {
			  			isAllowDisplay = true;		
			  		}
			  		if (scriptTabPosition == 1 && !isResultSetNull) {
			  			isAllowDisplay = true;		
			  		}
				  	if (scriptTabPosition == 2 && !isCoLocationResultSetNull) {
				  		isAllowDisplay = true;
			  		}
				  	if (scriptTabPosition == 3 && !isEmailResultSetNull) {
				  		isAllowDisplay = true;
			  		}
				  	if (scriptTabPosition == 4 && !isAddressResultSetNull) {
				  		isAllowDisplay = true;	
			  		}
			  	%>
				<c:if test="${B_SSM_S01_Page_Form.map.accessType eq '1' or B_SSM_S01_Page_Form.map.accessType eq '2'}">
					<logic:equal name="B_SSM_S01_Page_Form" property="exportBtnIsDisplay" value="1">
					  	<ts:submit property="button" 				  			   	  			
					  			   styleClass="B_SSM_S01_Page_Form_Buttons_Text"
					  			   styleId="exportBtnID"
					  			   disabled= '<%=!isAllowDisplay%>'
					  			   onclick="setTextField('processModeField', 1);showLoadingTipWindow();">
					  		<bean:message key="B_SSM_S01_Page.Form.ExportResultBtn.Text"/> 
					  	</ts:submit>
				  	</logic:equal>
                </c:if>
		  	</div> 	
		  	
		  	<!-- ************************************* Result Panel ****************************************** -->
		  	<div class="B_SSM_S01_Page_Form_ResultPanel">	
		  		<!-- ////////////////////// Result Info ////////////////////-->
		  		<!-- General Result Info -->
		  		<div  id="B_SSM_S01_Page_Form_ResultPanel_GeneralResultInfo"
		  			  class="B_SSM_S01_Page_Form_ResultPanel_ResultInfo">
		  			<span class="B_SSM_S01_Page_Form_ResultPanel_ResultInfo_Title">
		  				<bean:message key="B_SSM_S01_Page.Form.ResultPanel.SearchFoundText"/> 	
		  			</span>	
		  			<logic:notEmpty name="B_SSM_S01_Page_Form" property="generalResultSet" >
		  				<bean:write name="B_SSM_S01_Page_Form" property="generalTotalRow"/>
		  			</logic:notEmpty>
		  			<logic:empty name="B_SSM_S01_Page_Form" property="generalResultSet" >
		  				<logic:equal name="B_SSM_S01_Page_Form" property="hasSearchStarted" value="1">
		  					<logic:equal name="B_SSM_S01_Page_Form" property="tabPosition" value="0">
		  						<bean:message key="B_SSM_S01_Page.Form.ResultPanel.NoOfResultText"/>
		  					</logic:equal>
		  					<logic:equal name="B_SSM_S01_Page_Form" property="tabPosition" value="">
		  						<bean:message key="B_SSM_S01_Page.Form.ResultPanel.NoOfResultText"/>
		  					</logic:equal>
		  				</logic:equal>
		  			</logic:empty>			 
		  		</div>
		  		
		  		<!-- ISP Result Info -->
		  		<div  id="B_SSM_S01_Page_Form_ResultPanel_ISPResultInfo"
		  			  class="B_SSM_S01_Page_Form_ResultPanel_ResultInfo">
		  			<span class="B_SSM_S01_Page_Form_ResultPanel_ResultInfo_Title">
		  				<bean:message key="B_SSM_S01_Page.Form.ResultPanel.SearchFoundText"/> 	
		  			</span>	
		  			<logic:notEmpty name="B_SSM_S01_Page_Form" property="resultSet" >
		  				<bean:write name="B_SSM_S01_Page_Form" property="totalRow"/>
		  			</logic:notEmpty>
		  			<logic:empty name="B_SSM_S01_Page_Form" property="resultSet" >
		  				<logic:equal name="B_SSM_S01_Page_Form" property="hasSearchStarted" value="1">
		  					<logic:equal name="B_SSM_S01_Page_Form" property="tabPosition" value="1">
		  						<bean:message key="B_SSM_S01_Page.Form.ResultPanel.NoOfResultText"/>
		  					</logic:equal>
		  				</logic:equal>
		  			</logic:empty>			 
		  		</div>
		  		
		  		<!-- CoLocation Result Info -->
		  		<div  id="B_SSM_S01_Page_Form_ResultPanel_CoLocationResultInfo"
		  			  class="B_SSM_S01_Page_Form_ResultPanel_ResultInfo">
		  			<span class="B_SSM_S01_Page_Form_ResultPanel_ResultInfo_Title">
		  				<bean:message key="B_SSM_S01_Page.Form.ResultPanel.SearchFoundText"/> 	
		  			</span>	
		  			<logic:notEmpty name="B_SSM_S01_Page_Form" property="coLocationResultSet" >
		  				<bean:write name="B_SSM_S01_Page_Form" property="coLocationTotalRow"/>
		  			</logic:notEmpty>
		  			<logic:empty name="B_SSM_S01_Page_Form" property="coLocationResultSet" >
		  				<logic:equal name="B_SSM_S01_Page_Form" property="hasSearchStarted" value="1">
		  					<logic:equal name="B_SSM_S01_Page_Form" property="tabPosition" value="2">
		  						<bean:message key="B_SSM_S01_Page.Form.ResultPanel.NoOfResultText"/>
		  					</logic:equal>
		  				</logic:equal>
		  			</logic:empty>			 
		  		</div>	
		  		
		  		<!-- Email Result Info -->
		  		<div  id="B_SSM_S01_Page_Form_ResultPanel_EmailResultInfo"
		  			  class="B_SSM_S01_Page_Form_ResultPanel_ResultInfo">
		  			<span class="B_SSM_S01_Page_Form_ResultPanel_ResultInfo_Title">
		  				<bean:message key="B_SSM_S01_Page.Form.ResultPanel.SearchFoundText"/> 	
		  			</span>	
		  			<logic:notEmpty name="B_SSM_S01_Page_Form" property="emailResultSet" >
		  				<bean:write name="B_SSM_S01_Page_Form" property="emailTotalRow"/>
		  			</logic:notEmpty>
		  			<logic:empty name="B_SSM_S01_Page_Form" property="emailResultSet" >
		  				<logic:equal name="B_SSM_S01_Page_Form" property="hasSearchStarted" value="1">
		  					<logic:equal name="B_SSM_S01_Page_Form" property="tabPosition" value="3">
		  						<bean:message key="B_SSM_S01_Page.Form.ResultPanel.NoOfResultText"/>
		  					</logic:equal>
		  				</logic:equal>
		  			</logic:empty>			 
		  		</div>
		  		
		  		<!-- Address Result Info -->
		  		<div  id="B_SSM_S01_Page_Form_ResultPanel_AddressResultInfo"
		  			  class="B_SSM_S01_Page_Form_ResultPanel_ResultInfo">
		  			<span class="B_SSM_S01_Page_Form_ResultPanel_ResultInfo_Title">
		  				<bean:message key="B_SSM_S01_Page.Form.ResultPanel.SearchFoundText"/> 	
		  			</span>	
		  			<logic:notEmpty name="B_SSM_S01_Page_Form" property="addressResultSet" >
		  				<bean:write name="B_SSM_S01_Page_Form" property="addressTotalRow"/>
		  			</logic:notEmpty>
		  			<logic:empty name="B_SSM_S01_Page_Form" property="addressResultSet" >
		  				<logic:equal name="B_SSM_S01_Page_Form" property="hasSearchStarted" value="1">
		  					<logic:equal name="B_SSM_S01_Page_Form" property="tabPosition" value="4">
		  						<bean:message key="B_SSM_S01_Page.Form.ResultPanel.NoOfResultText"/>
		  					</logic:equal>
		  				</logic:equal>
		  			</logic:empty>			 
		  		</div>
		  		
		  		<!-- ////////////////////////// Page links ////////////////////-->
		  		<!-- General Page Links -->
		  		<div id="B_SSM_S01_Page_Form_ResultPanel_GeneralResultPageLinks"
		  			 class="pageLink">
		  			<bean:message key="B_SSM_S01_Page.Form.ResultPanel.GoToPageText"/> 
		  			<ts:pageLinks rowProperty="generalRow"
								  action="/B_SSM_S01_PageList_Process_Action.do?processMode=3&hasSearchStarted=1&tabPosition=0"
								  name="B_SSM_S01_Page_Form"
							      totalProperty="generalTotalRow" 
							      indexProperty="generalStartIndex" 
								  currentPageIndex="generalNow" 
								  totalPageCount="generalTotalPage"/>	
								 						
		  		</div>
		  		
		  		<!-- ISP Page Links -->
		  		<div id="B_SSM_S01_Page_Form_ResultPanel_ISPResultPageLinks"
		  			 class="pageLink">
		  			<bean:message key="B_SSM_S01_Page.Form.ResultPanel.GoToPageText"/> 
		  			<ts:pageLinks rowProperty="row"
								  action="/B_SSM_S01_PageList_Process_Action.do?processMode=3&hasSearchStarted=1&tabPosition=1"
								  name="B_SSM_S01_Page_Form"
							      totalProperty="totalRow" 
							      indexProperty="startIndex" 
								  currentPageIndex="now" 
								  totalPageCount="totalPage"/>	
								 						
		  		</div>
		  		<!-- CoLocation Page Links -->
		  		<div id="B_SSM_S01_Page_Form_ResultPanel_CoLocationResultPageLinks"
		  			 class="pageLink">
		  			<bean:message key="B_SSM_S01_Page.Form.ResultPanel.GoToPageText"/> 
		  			<ts:pageLinks rowProperty="coLocationRow"
								  action="/B_SSM_S01_PageList_Process_Action.do?processMode=3&hasSearchStarted=1&tabPosition=2"
								  name="B_SSM_S01_Page_Form"
							      totalProperty="coLocationTotalRow" 
							      indexProperty="coLocationStartIndex"
								  currentPageIndex="coLocationNow" 
								  totalPageCount="coLocationTotalPage"/>	
								 						
		  		</div>
		  		<!-- Email Page Links -->
		  		<div id="B_SSM_S01_Page_Form_ResultPanel_EmailResultPageLinks"
		  			 class="pageLink">
		  			<bean:message key="B_SSM_S01_Page.Form.ResultPanel.GoToPageText"/> 
		  			<ts:pageLinks rowProperty="emailRow"
								  action="/B_SSM_S01_PageList_Process_Action.do?processMode=3&hasSearchStarted=1&tabPosition=3"
								  name="B_SSM_S01_Page_Form"
							      totalProperty="emailTotalRow" 
							      indexProperty="emailStartIndex"
								  currentPageIndex="emailNow" 
								  totalPageCount="emailTotalPage"/>	
								 						
		  		</div>
		  		<!-- Address Page Links -->
		  		<div id="B_SSM_S01_Page_Form_ResultPanel_AddressResultPageLinks"
		  			 class="pageLink">
		  			<bean:message key="B_SSM_S01_Page.Form.ResultPanel.GoToPageText"/> 
		  			<ts:pageLinks rowProperty="addressRow"
								  action="/B_SSM_S01_PageList_Process_Action.do?processMode=3&hasSearchStarted=1&tabPosition=4"
								  name="B_SSM_S01_Page_Form"
							      totalProperty="addressTotalRow" 
							      indexProperty="addressStartIndex" 
								  currentPageIndex="addressNow" 
								  totalPageCount="addressTotalPage"/>	
								 						
		  		</div>
		  		
		  		<!--////////////////////////////////////// Results displayed //////////////////////////////////////////-->
		  		<!-- General Displaying Result Panel -->
		  		<jsp:include page="B_SSM_S01_Page_generalResult.jsp" flush="true"></jsp:include>
			  		
		  		<!-- ISP Displaying Result Panel -->
		  		<jsp:include page="B_SSM_S01_Page_ISPResult.jsp" flush="true"></jsp:include>
			  	
			  	<!-- CoLocation Displaying Result Panel -->
		  		<jsp:include page="B_SSM_S01_Page_coLocationResult.jsp" flush="true"></jsp:include>
			  	
			  	<!-- Email Displaying Result Panel -->
			  	<jsp:include page="B_SSM_S01_Page_emailResult.jsp" flush="true"></jsp:include>
			  	
			  	<!-- Address Displaying Result Panel -->
		  		<jsp:include page="B_SSM_S01_Page_addressResult.jsp" flush="true"></jsp:include>
		  	</div>
		  	
		  	<div class="error">
		        <html:messages id="message">
		            <bean:write name="message"/><br/>
		        </html:messages>
		    </div>
		    <div class="message">
		        <ts:messages id="message" message="true">
		            <bean:write name="message" />
		        </ts:messages>
		    </div>
  		</ts:form>
  	</div>
</body>
</html>