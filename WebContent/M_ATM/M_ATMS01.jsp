<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
   		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
	<link href="<%=request.getContextPath()%>/M_ATM/css/m_atms01.css" rel="stylesheet" type="text/css" /> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   	<script type="text/javascript" src="<%=request.getContextPath()%>/M_ATM/js/m_atms01.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
<title>Insert title here</title>
</head>
<body id="m" onload="initMain();">
	<ts:form action="/M_ATMS01Action" >
	<table class="subHeader" cellpadding="0" cellspacing="0">
   		<tr>
   			<td><bean:message key="screen.m_atm.title"/></td>
   		</tr>
   	</table>	

		<table class = "QCSSearchConditionTable" cellpadding="0" cellspacing="0">
			<tr>
				<td class="searchInf"><bean:message key="screen.m_atm.userName"/> 
				<t:defineCodeList id="LIST_USER_NAME" />
				<html:select name="_m_atmForm" styleId="cboUser"
					property="id_user" style="width:150px">
					<option><bean:message key="screen.m_atm.Empty"/></option>
					<html:options collection="LIST_USER_NAME" property="id" labelProperty="name"/>
				</html:select>	
				<!-- Insert combobox here -->
				</td>
			</tr>
		</table>
		    	<table class="buttonGroup" cellpadding="0" cellspacing="0">
    		<tr>
    			<td>
		<button onclick="search_data();"><bean:message key="screen.m_atm.search"/></button> &nbsp;
		<button onclick="resetSearchCondition();"><bean:message key="screen.m_atm.reset"/></button>
		    			</td>
    		</tr>
    	</table>
		<ts:submit value='search' property="forward_search" style="visibility:hidden"/> 

		<div id="showRs" <logic:empty name="_m_atmForm" property="detail_inf">class="hide" </logic:empty>>
			<table class="QCSSearchResultTable">
				<tr>
					<td colspan="2" class="header"><bean:message key="screen.m_atm.generalInf"/> 
					</td>
				</tr>
				<tr>
					<td colspan="2" class="distance">
					</td>						
				</tr>
				<tr>
					<td class="col1"><bean:message key="screen.m_atm.originalApp"/> 
					</td>
					<td class="col2">
						<bean:write name="_m_atmForm" property="user_name"/>
					</td>
				</tr> 
				<tr>
					<td class="col1">
						<input type="checkbox" id="chkTransfer" onclick="transfer_click(this);"><bean:message key="screen.m_atm.transferAll2"/></input> 					
					</td>	
					<td class="col2">
						<t:defineCodeList id="LIST_TRANSFER_NAME" />
						<logic:present property="arrayName" name="_m_atmForm">
						<html:select name="_m_atmForm" property="id_tfr_user" 
						styleId="transferAll2" style="width:150px" disabled="true">						
							<html:option value="" ><bean:message key="screen.m_atm.Empty"/></html:option>
							<html:optionsCollection property="arrayName" name="_m_atmForm" label="label" value="value"/>							
						</html:select>
						</logic:present>
					</td>
				</tr>
				<tr>
					<td class="col1">
						<bean:message key="screen.m_atm.effectAllFrom"/>
						<!-- Insert datetime control -->
					</td>	
					<td class="col2">					
						<div id="eff_date_fromd">	
							<html:text name="_m_atmForm" property="eff_date_from" styleId="eff_date_from" maxlength="10"  disabled="true" styleClass="BlueStyle-textbox"/>
		                            <t:inputCalendar for="eff_date_from" format="dd/MM/yyyy" /> 
	                    </div>
	                    <script type="text/javascript">
	                    	disable_calendar_button("eff_date_fromd");
	                    </script>
					</td>
				</tr>
				<tr>
					<td class="col1">
						<bean:message key="screen.m_atm.effectAll2"/>
						<!-- Insert datetime control -->
					</td>
					<td class="col2" >	
						<div id="eff_date_tod">			
							<html:text name="_m_atmForm" property="eff_date_to" styleId="eff_date_to" maxlength="10" disabled="true" styleClass="BlueStyle-textbox"/>
		                            <t:inputCalendar for="eff_date_to" format="dd/MM/yyyy"/>
	                    </div> 
	                    <script type="text/javascript">
	                    	disable_calendar_button("eff_date_tod");
	                    </script>
					</td>						
				</tr>
				<tr>
					<td colspan="2" class="distance">
					</td>						
				</tr>
			</table>
			<table class="QCSSearchResultTable">
				<tr>
					<td class="header1"><bean:message key="screen.m_atm.detailInf"/> 
					</td>
				</tr>
				<tr>
					<td class="distance">
					</td>						
				</tr>
				<tr>
					<td class="searchInf" >
						<!-- table -->
						<table class="SearchResultTable" cellpadding="0" cellspacing="0">
							<tr>
								<td class="col1Header" align="center"><bean:message key="screen.m_atm.screenId"/></td>
								<td class="col2Header" align="center"><bean:message key="screen.m_atm.section"/></td>
								<td class="col3Header" align="center"><bean:message key="screen.m_atm.sectionName"/></td>
								<td class="col4Header" align="center"><bean:message key="screen.m_atm.transferName"/></td>
								<td class="col5Header" align="center"><bean:message key="screen.m_atm.role"/></td>
								<td class="col6Header" align="center"><bean:message key="screen.m_atm.effectFrom"/></td>
								<td class="col7Header" align="center"><bean:message key="screen.m_atm.effect2"/></td>
							</tr>
							<logic:present property="m_atm" name="_m_atmForm">
								<logic:iterate id="atmData" name="_m_atmForm" property="m_atm">
									<tr>
										<td class="col1Header" align="center">
											<div id='scr_<bean:write name="atmData" property="bean_id"/>'>
												<bean:write name="atmData" property="id_screen"/>	
											</div>
										</td>
										<td class="col2Header" align="center">
											<div id='sec_<bean:write name="atmData" property="bean_id"/>'>
												<bean:write name="atmData" property="section_no"/>	
											</div>
										</td> 
										<td class="col3Header" align="center">
											<bean:write name="atmData" property="section_desc"/>	
										</td>
										<td class="col4Row" align="center" id='<bean:write name="atmData" property="bean_id"/>'>
										<logic:present property="arrayName" name="_m_atmForm">
											<html:select name="atmData" property="id_tfr_user" onchange="selectRoleName(this);" style="width:100%" >					
													<html:option value="" ><bean:message key="screen.m_atm.Empty"/></html:option>
													<html:optionsCollection property="arrayName" name="_m_atmForm" label="label" value="value"/>	
											</html:select>	
										</logic:present>
										</td>
										<td class="col5Row" align="center">
											<!-- Role -->
											<div id='role_<bean:write name="atmData" property="bean_id"/>'><bean:write name="atmData" property="role_name"/></div>										
											<!-- Role will be changed by selecting transfer name -->
										</td>
										<td class="col6Row" align="center" id='f<bean:write name="atmData" property="bean_id"/>'>	
											<input id='dtf_<bean:write name="atmData" property="bean_id"/>' disabled="disabled" class="txt" value='<bean:write name="atmData" property="eff_date_from"/>'
											/>		
											<input type="button" onclick="jscalendarPopUpCalendar(this,this.form.elements['dtf_<bean:write name="atmData" property="bean_id"/>'],'dd/MM/yyyy');" value="" class="BlueStyle-button"/>
										</td>
										<td class="col7Row" align="center" id='t<bean:write name="atmData" property="bean_id"/>'>
											<input id='dtt_<bean:write name="atmData" property="bean_id"/>' disabled="disabled" class="txt"	value='<bean:write name="atmData" property="eff_date_to"/>'
											/>			 						
											<input type="button" onclick="jscalendarPopUpCalendar(this,this.form.elements['dtt_<bean:write name="atmData" property="bean_id"/>'],'dd/MM/yyyy');" value="" class="BlueStyle-button"/>
										</td>
									
									</tr>
								</logic:iterate>
							</logic:present>
						</table>
					</td>
				</tr>
				<tr>
					<td class="distance">
					</td>						
				</tr>
			</table>
			<hr>
			<button onclick="save_data();"><bean:message key="screen.m_atm.save"/></button>
			<ts:submit value='save' property="forward_save" style="visibility:hidden"/> 
			<html:hidden name="_m_atmForm" property="user_name" styleId="user_name"/>
			<html:hidden name="_m_atmForm" property="id_user" styleId="id_user"/>
			<html:hidden name="_m_atmForm" property="role_list" styleId="role_list"/>
			<html:hidden name="_m_atmForm" property="update_mode" styleId="update_mode"/>
			<html:hidden name="_m_atmForm" property="detail_inf" styleId="detail_inf"/>      
			<div id="role" class="hide">
				<t:defineCodeList id="LIST_ROLE_BY_USER" />
				<html:select name="_m_atmForm" property="beanCodeListID" styleId="cboRole">
					<html:options collection="LIST_ROLE_BY_USER" property="id" labelProperty="name"/>
				</html:select>
			</div>		
			<div id="ERR1SC013" class="hide"><font color="red" style="font-style: italic"><bean:message key="screen.m_atm.ERR1SC013"/></font></div>
			<div id="ERR1SC005_transfer" class="hide"><font color="red" style="font-style: italic"><bean:message key="screen.m_atm.ERR1SC005_transfer"/></font></div>
			<div id="ERR1SC005_effectFrom" class="hide"><font color="red" style="font-style: italic"><bean:message key="screen.m_atm.ERR1SC005_effectFrom"/></font></div>
			<div id="ERR1SC005_effect2" class="hide"><font color="red" style="font-style: italic"><bean:message key="screen.m_atm.ERR1SC005_effect2"/></font></div>
		</div>
			<div id="ERR1SC008" class="hide"><font color="red" style="font-style: italic"><bean:message key="screen.m_atm.ERR1SC008"/></font></div>
		<div class="error" id="errorDiv">
			<html:messages id="message">
				<bean:write name="message"/><br/>
			</html:messages>
		</div>
		<div class="message" id="messageDiv">
			<ts:messages id="messages" message="true">
				<bean:write name="messages"/><br/>
			</ts:messages>
	   	</div>
	</ts:form>
</body>
</html>