<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>

<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
	   	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
		<link href="<%=request.getContextPath()%>/Q_QCS/css/q_qcss02.css" rel="stylesheet" type="text/css" /> 
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/Q_QCS/js/q_qcss02.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
		<script type="text/javascript" src="js/multifile.js"></script>
		<title>Insert title here</title>
	</head>
	<ts:body onload="initPage()">
		<t:defineCodeList id="LIST_USER_NAME" />
		<table class="subHeader" cellpadding="0" cellspacing="0">
		  <tr style="">
		    <td class="Title"><bean:message key="screen.q_qcs.title"/></td> 
		  </tr>
		</table>
		<ts:form action="/Q_QCSR02DSP" enctype="multipart/form-data" method="POST" >	
			<!-- General Information -->
			<logic:equal name="_q_qcsForm" property="enableQCS" value="1">
				<table class = "generalInfo" cellpadding="0" cellspacing="0">
					<tr>
						<td class="header" colspan="5"><bean:message key="screen.q_qcs02.generalInfo"/>					
						</td>
					</tr>
					<tr>
						<td width="20%"><bean:message key="screen.q_qcs02.idConsultant"/>
						</td>
						<td width="20%"><bean:message key="screen.q_qcs.colon"/>					
							<html:select name="_q_qcsForm" property="id_conslt" styleClass="ListBox">
								<html:option value="" ><bean:message key="screen.q_qcs02.blankitem"/></html:option>
								<html:options collection="LIST_USER_NAME" property="id" labelProperty="name"/>
							</html:select>	
						</td>
						<td width="20%"><bean:message key="screen.q_qcs02.requestDate"/>
						</td>
						<td width="20%"><bean:message key="screen.q_qcs.colon"/>
							<html:text property="date_req" name="_q_qcsForm" readonly="true" styleClass="DateTextBox"/>
							<t:inputCalendar for="date_req" format="dd/MM/yyyy"/>
						</td>
						<td width="20%" rowspan="3" class="<bean:write name="_q_qcsForm" property="docStatusCSS"/>">
							<bean:write name="_q_qcsForm" property="docStatus"/>
							<br>
							<logic:equal name="_q_qcsForm" property="enableObtainApproval" value="1">
								<input type="submit" class="ObtainApprovalButton" value="<bean:message key="screen.q_qcs02.obtainApproval"/>" name="forward_obt_appr" onclick="clickObtainApproval('<bean:message key="POPBEF"/>')" />
							</logic:equal>
							<logic:equal name="_q_qcsForm" property="enableObtainApproval" value="0">
								<input type="submit" class="ObtainApprovalButton" value="<bean:message key="screen.q_qcs02.obtainApproval"/>" name="forward_obt_appr" onclick="clickObtainApproval('<bean:message key="POPBEF"/>')" disabled="disabled"/>
							</logic:equal>
						</td>
					</tr>
					<tr>
						<td width="20%"><bean:message key="screen.q_qcs02.qcsReference"/>
						</td>
						<td width="20%"><bean:message key="screen.q_qcs.colon"/>
							<bean:write name="_q_qcsForm" property="id_ref"/>
						</td>
						<td width="20%"><bean:message key="screen.q_qcs02.requestedby"/>
						</td>
						<td width="20%"><bean:message key="screen.q_qcs.colon"/>
							<html:select name="_q_qcsForm" property="req_user" styleClass="ListBox">
								<html:option value="" ><bean:message key="screen.q_qcs02.blankitem"/></html:option>
								<html:options collection="LIST_USER_NAME" property="id" labelProperty="name"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<td width="20%"><bean:message key="screen.q_qcs02.quotationReference"/>
						</td>
						<td width="20%" colspan="3"><bean:message key="screen.q_qcs.colon"/>
							<bean:write name="_q_qcsForm" property="id_quo"/>
						</td>
					</tr>
				</table>
			</logic:equal>
			<logic:equal name="_q_qcsForm" property="enableQCS" value="0">
				<table class = "generalInfo" cellpadding="0" cellspacing="0">
					<tr>
						<td class="header" colspan="5"><bean:message key="screen.q_qcs02.generalInfo"/>					
						</td>
					</tr>
					<tr>
						<td width="20%"><bean:message key="screen.q_qcs02.idConsultant"/>
						</td>
						<td width="20%"><bean:message key="screen.q_qcs.colon"/>					
							<html:select name="_q_qcsForm" property="id_conslt" styleClass="ListBox"  disabled="true">
								<html:option value="" ><bean:message key="screen.q_qcs02.blankitem"/></html:option>
								<html:options collection="LIST_USER_NAME" property="id" labelProperty="name"/>
							</html:select>	
						</td>
						<td width="20%"><bean:message key="screen.q_qcs02.requestDate"/>
						</td>
						<td width="20%"><bean:message key="screen.q_qcs.colon"/>
							<html:text property="date_req" name="_q_qcsForm" readonly="true" styleClass="DateTextBox" disabled="true"/>
							<t:inputCalendar for="date_req" format="dd/MM/yyyy" />
						</td>
						<td width="20%" rowspan="3" class="<bean:write name="_q_qcsForm" property="docStatusCSS"/>">
							<bean:write name="_q_qcsForm" property="docStatus"/>
							<br>						
							<input type="submit" class="ObtainApprovalButton" value="<bean:message key="screen.q_qcs02.obtainApproval"/>" name="forward_obt_appr" onclick="clickObtainApproval('<bean:message key="POPBEF"/>')" disabled="disabled"/>							
						</td>
					</tr>
					<tr>
						<td width="20%"><bean:message key="screen.q_qcs02.qcsReference"/>
						</td>
						<td width="20%"><bean:message key="screen.q_qcs.colon"/>
							<bean:write name="_q_qcsForm" property="id_ref"/>
						</td>
						<td width="20%"><bean:message key="screen.q_qcs02.requestedby"/>
						</td>
						<td width="20%"><bean:message key="screen.q_qcs.colon"/>
							<html:select name="_q_qcsForm" property="req_user" styleClass="ListBox" disabled="true">
								<html:option value="" ><bean:message key="screen.q_qcs02.blankitem"/></html:option>
								<html:options collection="LIST_USER_NAME" property="id" labelProperty="name"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<td width="20%"><bean:message key="screen.q_qcs02.quotationReference"/>
						</td>
						<td width="20%" colspan="3"><bean:message key="screen.q_qcs.colon"/>
							<bean:write name="_q_qcsForm" property="id_quo"/>
						</td>
					</tr>
				</table>
			</logic:equal>
			<!-- Quotation Information -->
			<logic:equal name="_q_qcsForm" property="enableQCS" value="1">
				<table  class = "generalInfo" cellpadding="0" cellspacing="0">
					<tr>
						<td class="header" colspan="5"><bean:message key="screen.q_qcs02.quotationInfo"/>					
						</td>
					</tr>
					<tr>
						<td width="20%"><bean:message key="screen.q_qcs02.customerName"/>
						</td>
						<td width="40%" ><bean:message key="screen.q_qcs.colon"/>
							<t:defineCodeList id="LIST_CUSTOMER" />					
							<html:select name="_q_qcsForm" property="id_cust" styleClass="CustomerListBox">
								<html:option value="" ><bean:message key="screen.q_qcs02.blankitem"/></html:option>
								<html:options collection="LIST_CUSTOMER" property="id" labelProperty="name"/>
							</html:select>	
						</td>
						<td width="20%">
							<html:checkbox  name="_q_qcsForm" property="cust_mode" />
							<bean:message key="screen.q_qcs02.newCustomer"/>
						</td>
						<td width="20%" rowspan="5" align="center" >
							<font size="4px"> 
								<bean:message key="screen.q_qcs02.agreedby"/>
							</font>
							<br>
							<bean:message key="screen.q_qcs02.headofsaledivision"/>
							<br>							
							<logic:iterate name="_q_qcsForm" id="apprQCS" property="listApprQCS">
								<div style="border: none" align="center" class="<bean:write name="apprQCS" property="appr_status_css"/>">
									<bean:write name="apprQCS" property="appr_status"/>
									<br>
									<bean:write name="apprQCS" property="pic"/>
									<br>
									<bean:write name="apprQCS" property="date_appr"/>
									<br>
								</div>								
							</logic:iterate>
							<br>
							<br>
							<logic:equal name="_q_qcsForm" property="enableApproveQCS" value="1">
								<input type="submit" class="button" value="<bean:message key="screen.q_qcs02.approve"/>" name="forward_qcs_appr" onclick="clickQCSApproval()" />
								<input type="submit" class="button" value="<bean:message key="screen.q_qcs02.reject"/>" name="forward_qcs_reject" onclick="clickQCSReject('<bean:message key="POPBEF"/>')" />
							</logic:equal>
							<logic:equal name="_q_qcsForm" property="enableApproveQCS" value="0">
								<input type="submit" class="button" value="<bean:message key="screen.q_qcs02.approve"/>" name="forward_qcs_appr" onclick="clickQCSApproval()" disabled="disabled" />
								<input type="submit" class="button" value="<bean:message key="screen.q_qcs02.reject"/>" name="forward_qcs_reject" onclick="clickQCSReject('<bean:message key="POPBEF"/>')" disabled="disabled" />
							</logic:equal>
						</td>
					</tr>
					<tr>
						<td ><bean:message key="screen.q_qcs02.creditTerm"/>
						</td>
						<td   colspan="2">
							<table style="border:0;width:80%;font-size:15px;"  >
								<tr>
									<td width="25%">
										<bean:message key="screen.q_qcs.colon"/>
										<html:radio name="_q_qcsForm" property="ctterm" value="1" onclick="CreditTermClick(this,'ctterm3_day')"/>
										<bean:message key="screen.q_qcs02.cod"/>
									</td>
									<td width="25%">
										<html:radio name="_q_qcsForm" property="ctterm" value="2" onclick="CreditTermClick(this,'ctterm3_day')"/>
										<bean:message key="screen.q_qcs02.30days"/>
									</td>
									<td width="50%">
										<html:radio name="_q_qcsForm" property="ctterm" value="3" onclick="CreditTermClick(this,'ctterm3_day')"/>
										<bean:message key="screen.q_qcs02.otherPls"/>
										<logic:equal name="_q_qcsForm" property="ctterm" value="3">
											<html:text name="_q_qcsForm" property="ctterm3_day" styleClass="OtherTextBox" onkeypress="return onlyInteger();"/>
										</logic:equal>
										<logic:notEqual name="_q_qcsForm" property="ctterm" value="3">
											<html:text name="_q_qcsForm" property="ctterm3_day" styleClass="OtherTextBox" onkeypress="return onlyInteger();" disabled="true"/>
										</logic:notEqual>
										<bean:message key="screen.q_qcs02.days"/>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td ><bean:message key="screen.q_qcs02.deposit"/>
						</td>
						<td colspan="2">
							<table style="border:0;width:80%;font-size:15px;">
								<tr>
									<td width="25%">
										<bean:message key="screen.q_qcs.colon"/>
										<html:radio name="_q_qcsForm" property="deposit" value="3"/>
										<bean:message key="screen.q_qcs02.2months"/>
									</td >
									<td width="25%">
										<html:radio name="_q_qcsForm" property="deposit" value="2"/>
										<bean:message key="screen.q_qcs02.1month"/>
									</td>
									<td width="50%">
										<html:radio name="_q_qcsForm" property="deposit" value="1"/>
										<bean:message key="screen.q_qcs02.waived"/>
									</td>
								</tr>
							</table>
							
						</td>
					</tr>
					<tr>
						<td style="vertical-align:top;">
							<bean:message key="screen.q_qcs02.attachment"/>
						</td>
						<td colspan="2">
							<table  style="border:0;width:100%;font-size:15px;">
								<tr>
									<td width="2%" valign="top">
										<bean:message key="screen.q_qcs.colon"/>
									</td>									
									<td>
											<table id="attachmentQCS" style="border:0;width:100%;font-size:15px;">
												<logic:iterate name="_q_qcsForm" id="fileInfoQCS" property="attachmentQCS">
													<tr>
														<td>
															<a href="#" onclick="clickDownload('<bean:write name="fileInfoQCS" property="filename"/>') "> <bean:write name="fileInfoQCS" property="filename"/></a>
															<img src='../image/delete.gif' onclick="removeRow(this,'QCS','<bean:write name="fileInfoQCS" property="id_doc"/>')" />	
															
														</td>
														<td>								
														</td>
													</tr>
												</logic:iterate>
											</table>							
									</td>									
								</tr>
								
							</table>
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;
						</td>
						<td colspan="2">
							&nbsp;&nbsp;<input type="button" class="fakeFileQCS" name="pseudobutton" value="<bean:message key="screen.q_qcs02.uploadFile"/>" >
							<input id="qcs_file"  type="file" name="file_1" class="realFileQCS" />
						</td>
					</tr>
					<tr>
						<td>
							<bean:message key="screen.q_qcs02.remarkComment"/>
						</td>
						<td colspan="3">
							<bean:message key="screen.q_qcs.colon"/>
							<html:text name="_q_qcsForm" property="remarks" styleClass="RemarkTextBox"/>
						</td>
					</tr>
				</table>
				
			</logic:equal>
			<logic:equal name="_q_qcsForm" property="enableQCS" value="0">
				<table class = "generalInfo" cellpadding="0" cellspacing="0">
					<tr>
						<td class="header" colspan="5"><bean:message key="screen.q_qcs02.quotationInfo"/>					
						</td>
					</tr>
					<tr>
						<td width="20%"><bean:message key="screen.q_qcs02.customerName"/>
						</td>
						<td width="40%" ><bean:message key="screen.q_qcs.colon"/>
							<t:defineCodeList id="LIST_CUSTOMER" />					
							<html:select name="_q_qcsForm" property="id_cust" styleClass="CustomerListBox" disabled="true">
								<html:option value="" ><bean:message key="screen.q_qcs02.blankitem"/></html:option>
								<html:options collection="LIST_CUSTOMER" property="id" labelProperty="name"/>
							</html:select>	
						</td>
						<td width="20%">
							<html:checkbox  name="_q_qcsForm" property="cust_mode" disabled="true"/>
							<bean:message key="screen.q_qcs02.newCustomer"/>
						</td>
						<td width="20%" rowspan="5" align="center">
							<font size="4px"> 
								<bean:message key="screen.q_qcs02.agreedby"/>
							</font>
							<br>
							<bean:message key="screen.q_qcs02.headofsaledivision"/>
							<br>	
							<logic:iterate name="_q_qcsForm" id="apprQCS" property="listApprQCS">
								<div align="center" class="<bean:write name="apprQCS" property="appr_status_css"/>">
									<bean:write name="apprQCS" property="appr_status"/>
									<br>
									<bean:write name="apprQCS" property="pic"/>
									<br>
									<bean:write name="apprQCS" property="date_appr"/>
									<br>
								</div>								
							</logic:iterate>	
							<br>										
							<input type="submit" class="button" value="<bean:message key="screen.q_qcs02.approve"/>" name="forward_qcs_appr" onclick="clickQCSApproval('<bean:message key="POPBEF"/>')" disabled="disabled" />
							<input type="submit" class="button" value="<bean:message key="screen.q_qcs02.reject"/>" name="forward_qcs_reject" onclick="clickQCSReject('<bean:message key="POPBEF"/>')" disabled="disabled" />							
						</td>
					</tr>
					<tr>
						<td ><bean:message key="screen.q_qcs02.creditTerm"/>
						</td>
						<td   colspan="2">
							<table style="border:0;width:80%;font-size:15px;"  >
								<tr>
									<td width="25%">
										<bean:message key="screen.q_qcs.colon"/>
										<html:radio name="_q_qcsForm" property="ctterm" value="1" onclick="CreditTermClick(this,'ctterm3_day')" disabled="true"/>
										<bean:message key="screen.q_qcs02.cod"/>
									</td>
									<td width="25%">
										<html:radio name="_q_qcsForm" property="ctterm" value="2" onclick="CreditTermClick(this,'ctterm3_day')" disabled="true"/>
										<bean:message key="screen.q_qcs02.30days"/>
									</td>
									<td width="50%">
										<html:radio name="_q_qcsForm" property="ctterm" value="3" onclick="CreditTermClick(this,'ctterm3_day')" disabled="true"/>
										<bean:message key="screen.q_qcs02.otherPls"/>
										<html:text name="_q_qcsForm" property="ctterm3_day" styleClass="OtherTextBox" onkeypress="return onlyInteger();" disabled="true"/>
										<bean:message key="screen.q_qcs02.days"/>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td ><bean:message key="screen.q_qcs02.deposit"/>
						</td>
						<td colspan="2">
							<table style="border:0;width:80%;font-size:15px;">
								<tr>
									<td width="25%">
										<bean:message key="screen.q_qcs.colon"/>
										<html:radio name="_q_qcsForm" property="deposit" value="3" disabled="true"/>
										<bean:message key="screen.q_qcs02.2months"/>
									</td >
									<td width="25%">
										<html:radio name="_q_qcsForm" property="deposit" value="2" disabled="true"/>
										<bean:message key="screen.q_qcs02.1month"/>
									</td>
									<td width="50%">
										<html:radio name="_q_qcsForm" property="deposit" value="1" disabled="true"/>
										<bean:message key="screen.q_qcs02.waived"/>
									</td>
								</tr>
							</table>
							
						</td>
					</tr>
					<tr>
						<td>
							<bean:message key="screen.q_qcs02.attachment"/>
						</td>
						<td colspan="2">
							<table style="border:0;width:100%;font-size:15px;">
								<tr>
									<td width="2%" valign="top">
										<bean:message key="screen.q_qcs.colon"/>
									</td>									
									<td>
											<table id="attachmentQCS" style="border:0;width:100%;font-size:15px;">
												<logic:iterate name="_q_qcsForm" id="fileInfoQCS" property="attachmentQCS">
													<tr>
														<td>
															<a href="#" onclick="clickDownload('<bean:write name="fileInfoQCS" property="filename"/>') "> <bean:write name="fileInfoQCS" property="filename"/></a>
															<img src='../image/delete.gif' onclick="removeRow(this,'QCS','<bean:write name="fileInfoQCS" property="id_doc"/>')" />	
															
														</td>
														<td>								
														</td>
													</tr>
												</logic:iterate>
											</table>							
									</td>	
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;
						</td>
						<td colspan="2">
							&nbsp;&nbsp;<input type="button" class="fakeFileQcs" name="pseudobutton" value="<bean:message key="screen.q_qcs02.uploadFile"/>" disabled="disabled" >
							<input id="qcs_file"  type="file" name="file_1" class="realFileQcs"  style="display:none"/>
						</td>
					</tr>
					<tr>
						<td>
							<bean:message key="screen.q_qcs02.remarkComment"/>
						</td>
						<td colspan="3">
							<bean:message key="screen.q_qcs.colon"/>
							<html:text name="_q_qcsForm" property="remarks" styleClass="RemarkTextBox" disabled="true"/>
						</td>
					</tr>
				</table>
			</logic:equal>
			<!-- Contract table -->
			<table id="contractTb" cellpadding="0" cellspacing="0" class="contractInfo">
				<tr>
					<td width="10%" class="header">
						<bean:message key="screen.q_qcs02.serviceGroup"/>
					</td>
					<td width="20%" class="header">
						<bean:message key="screen.q_qcs02.capacity"/>
					</td>
					<td width="10%" class="header">
						<bean:message key="screen.q_qcs02.nrc"/>
					</td>
					<td width="10%" class="header">
						<bean:message key="screen.q_qcs02.mrc"/>
					</td>
					<td width="10%" class="header">
						<bean:message key="screen.q_qcs02.term"/>
					</td>
					<td width="10%" class="header">
						<bean:message key="screen.q_qcs02.total"/>
					</td>
					<td width="10%" class="header">
						<bean:message key="screen.q_qcs02.tariffLImit"/>
					</td>
					<td width="10%" class="header">
						<bean:message key="screen.q_qcs02.discount"/>
					</td>
					<td width="5%" class="header">
						&nbsp;
					</td>
					<td width="5%" >
						<logic:equal name="_q_qcsForm" property="enableQCS" value="1">
							<a href="#" onclick="addRowToTable('contractTb')"><bean:message key="screen.q_qcs02.add"/></a>
						</logic:equal>
						<logic:equal name="_q_qcsForm" property="enableQCS" value="0">
							<a href="#" ><bean:message key="screen.q_qcs02.add"/></a>
						</logic:equal>
					</td>
				</tr>
				<tr style="display:none">
					<td class="colLeft">
						<t:defineCodeList id="LIST_SERVICE_GROUP" />
						<html:select name="_q_qcsForm" property="svc_grp" >
							<html:options collection="LIST_SERVICE_GROUP" property="id" labelProperty="name"/>
						</html:select>
					</td>
					<td class="colLeft">
						<html:text name="_q_qcsForm"  property="capacity" styleClass="ContractInnerLeftTextbox"/>
					</td>
					<td class="colRight">
						<html:text name="_q_qcsForm"  property="nrc" styleClass="ContractInnerRightTextbox" onkeypress="return onlyNumbers();" onkeyup="computeTotal('contractTb',this)"/>
					</td>
					<td class="colRight">
						<html:text name="_q_qcsForm"  property="mrc" styleClass="ContractInnerRightTextbox" onkeypress="return onlyNumbers();" onkeyup="computeTotal('contractTb',this)"/>
					</td>
					<td class="colLeft">
						<html:text name="_q_qcsForm"  property="term" styleClass="ContractInnerLeftTextbox" onkeypress="return onlyNumbers();" onkeyup="computeTotal('contractTb',this)"/>
					</td>
					<td class="colRight">
						<html:text name="_q_qcsForm"  property="total" styleClass="ContractInnerRightTextbox"   readonly="true"/>
					</td>
					<td class="colRight">
						<html:text name="_q_qcsForm"  property="tariff" styleClass="ContractInnerRightTextbox" onkeypress="return onlyNumbers();"/>
					</td>
					<td class="colRight">
						<html:text name="_q_qcsForm"  property="disc" styleClass="ContractInnerRightTextbox" onkeypress="return onlyNumbers();"/>
					</td>
					<td class="colCenter">
						<img src="<%=request.getContextPath()%>/image/delete.gif" onclick="removeRowFromTable('contractTb',this)"/>
					</td>
					<td >
						&nbsp;
					</td>
				</tr>
				<logic:equal name="_q_qcsForm" property="enableQCS" value="1">
					<logic:iterate id="qcs" name="_q_qcsForm" property="qcsDetail" >
						<tr>
							<td class="colLeft">
								<t:defineCodeList id="LIST_SERVICE_GROUP" />
								<html:select name="_q_qcsForm" property="svc_grp" >
									<html:options collection="LIST_SERVICE_GROUP" property="id" labelProperty="name"/>
								</html:select>
							</td>
							<td class="colLeft">
								<html:text name="qcs"  property="capacity" styleClass="ContractInnerLeftTextbox"/>
							</td>
							<td class="colRight">
								<html:text name="qcs"  property="nrc" styleClass="ContractInnerRightTextbox" onkeypress="return onlyNumbers();" onkeyup="computeTotal('contractTb',this)"/>
							</td>
							<td class="colRight">
								<html:text name="qcs"  property="mrc" styleClass="ContractInnerRightTextbox" onkeypress="return onlyNumbers();" onkeyup="computeTotal('contractTb',this)"/>
							</td>
							<td class="colLeft">
								<html:text name="qcs"  property="term" styleClass="ContractInnerLeftTextbox" onkeypress="return onlyNumbers();" onkeyup="computeTotal('contractTb',this)"/>
							</td>
							<td class="colRight">
								<html:text name="qcs"  property="total" styleClass="ContractInnerRightTextbox"  readonly="true"/>
							</td>
							<td class="colRight">
								<html:text name="qcs"  property="tariff" styleClass="ContractInnerRightTextbox" onkeypress="return onlyNumbers();"/>
							</td>
							<td class="colRight">
								<html:text name="qcs"  property="disc" styleClass="ContractInnerRightTextbox" onkeypress="return onlyNumbers();"/>
							</td>
							<td class="colCenter">
								<img src="<%=request.getContextPath()%>/image/delete.gif" onclick="removeRowFromTable('contractTb',this)"/>
							</td>
							<td >
								&nbsp;
							</td>
						</tr>
					</logic:iterate>
				</logic:equal>
				<logic:equal name="_q_qcsForm" property="enableQCS" value="0">
					<logic:iterate id="qcs" name="_q_qcsForm" property="qcsDetail" >
						<tr>
							<td class="colLeft">
								<t:defineCodeList id="LIST_SERVICE_GROUP" />
								<html:select name="_q_qcsForm" property="svc_grp" >
									<html:options collection="LIST_SERVICE_GROUP" property="id" labelProperty="name"/>
								</html:select>
							</td>
							<td class="colLeft">
								<html:text name="qcs"  property="capacity" styleClass="ContractInnerLeftTextbox" readonly="true"/>
							</td>
							<td class="colRight">
								<html:text name="qcs"  property="nrc" styleClass="ContractInnerRightTextbox" readonly="true" onkeypress="return onlyNumbers();" onkeyup="computeTotal('contractTb',this)"/>
							</td>
							<td class="colRight">
								<html:text name="qcs"  property="mrc" styleClass="ContractInnerRightTextbox" readonly="true" onkeypress="return onlyNumbers();" onkeyup="computeTotal('contractTb',this)"/>
							</td>
							<td class="colLeft">
								<html:text name="qcs"  property="term" styleClass="ContractInnerLeftTextbox" readonly="true" onkeypress="return onlyNumbers();" onkeyup="computeTotal('contractTb',this)"/>
							</td>
							<td class="colRight">
								<html:text name="qcs"  property="total" styleClass="ContractInnerRightTextbox"  readonly="true"/>
							</td>
							<td class="colRight">
								<html:text name="qcs"  property="tariff" styleClass="ContractInnerRightTextbox" readonly="true" onkeypress="return onlyNumbers();"/>
							</td>
							<td class="colRight">
								<html:text name="qcs"  property="disc" styleClass="ContractInnerRightTextbox" readonly="true" onkeypress="return onlyNumbers();"/>
							</td>
							<td class="colCenter">
								<img src="<%=request.getContextPath()%>/image/delete.gif" />
							</td>
							<td >
								&nbsp;
							</td>
						</tr>
					</logic:iterate>
				</logic:equal>
			</table>
			<!-- Contract value table -->
			<table cellpadding="0" cellspacing="0" class="contractValue">
				<tr>			
					<td width="10%" ></td>
					<td width="20%" ></td>
					<td width="10%" ></td>
					<td width="20%" class="colRightNoBorder"><bean:message key="screen.q_qcs02.contractValue"/></td>
					<td width="10%" class="colRight"><input type="text" name="contractValue" class="ContractInnerRightTextbox" readonly="readonly"/></td>
					<td width="10%" ></td>
					<td width="10%" ></td>
					<td width="5%" ></td>
					<td width="5%" ></td>
				</tr>			
			</table>
			<!-- Session header -->
			<table cellpadding="0" cellspacing="0" class="sessionHeader">
				<tr>			
					<td width="25%" class="standardCol"><bean:message key="screen.q_qcs02.standardItems"/></td>
					<td width="35%" class="nonStandardCol"><bean:message key="screen.q_qcs02.nonStandardItems"/></td>
					<td width="20%" class="concurrenceCol"><bean:message key="screen.q_qcs02.concurrence"/></td>
					<td width="20%" class="statusCol"><bean:message key="screen.q_qcs02.status"/></td>
				</tr>			
			</table>
			
			<!-- Session PMR -->
			<div id="divPMR">
			<logic:equal name="_q_qcsForm" property="enablePMR" value="1">
				<table cellpadding="0" cellspacing="0" class="sessionDetail">
					<tr>			
						<td width="25%" class="standardCol">
							<label id="lblseqPMR"></label>
							<bean:message key="screen.q_qcs02.1pmr"/><br>
							<logic:equal name="_q_qcsForm" property="disableSectionNo" value="true">
								<html:radio name="_q_qcsForm" property="section_no_pmr" value="Q011" onclick="determineStatusPMR(this)" disabled="true" />
							</logic:equal>
							<logic:equal name="_q_qcsForm" property="disableSectionNo" value="false">
								<html:radio name="_q_qcsForm" property="section_no_pmr" value="Q011" onclick="determineStatusPMR(this)" disabled="false" />
							</logic:equal>
							<bean:message key="screen.q_qcs02.sinotincluded"/>
						</td>
						<td width="35%" class="nonStandardCol">
							<br>
							<logic:equal name="_q_qcsForm" property="disableSectionNo" value="true">
								<html:radio name="_q_qcsForm" property="section_no_pmr" value="Q012" onclick="determineStatusPMR(this)" disabled="true" />
							</logic:equal>
							<logic:equal name="_q_qcsForm" property="disableSectionNo" value="false">
								<html:radio name="_q_qcsForm" property="section_no_pmr" value="Q012" onclick="determineStatusPMR(this)" disabled="false" />
							</logic:equal>
							<bean:message key="screen.q_qcs02.siincluded"/>
						</td>
						<td width="20%" class="concurrenceCol"><br><bean:message key="screen.q_qcs02.projectmanagement"/></td>
						<td width="20%" class="statusCol">
							<div id="Q011">
								<logic:iterate name="_q_qcsForm" id="apprPMR1" property="listApprPMR1">
								<div style="border: none" class="<bean:write name="apprPMR1" property="appr_status_css"/>">
									<center><bean:write name="apprPMR1" property="appr_status"/></center>
									<br>
									<bean:message key="screen.q_qcs02.approvalName"/><bean:write name="apprPMR1" property="pic"/>
									<br>
									<bean:message key="screen.q_qcs02.approvalDate"/><bean:write name="apprPMR1" property="date_appr"/>
								</div>
								<br>								
								</logic:iterate>								
							</div>
							<div id="Q012">
								<logic:iterate name="_q_qcsForm" id="apprPMR2" property="listApprPMR2">
								<div style="border: none" class="<bean:write name="apprPMR2" property="appr_status_css"/>">
									<center><bean:write name="apprPMR2" property="appr_status"/></center>
									<br>
									<bean:message key="screen.q_qcs02.approvalName"/><bean:write name="apprPMR2" property="pic"/>
									<br>
									<bean:message key="screen.q_qcs02.approvalDate"/><bean:write name="apprPMR2" property="date_appr"/>
								</div>
								<br>								
								</logic:iterate>								
							</div>
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<table style="border:0;width:100%;font-size:15px;">
								<tr>
									<td style="vertical-align:top;" width="5%">
											<bean:message key="screen.q_qcs02.attachment"/><bean:message key="screen.q_qcs.colon"/>
									</td>
									<td>
										<table id="attachmentPMR" style="border:0;width:100%;font-size:15px;">
											<logic:iterate name="_q_qcsForm" id="fileInfoPMR" property="attachmentPMR">
												<tr>
													<td>
														<a href="#" onclick="clickDownload('<bean:write name="fileInfoPMR" property="filename"/>') "> <bean:write name="fileInfoPMR" property="filename"/></a>
														<img src='../image/delete.gif' onclick="removeRow(this,'PMR','<bean:write name="fileInfoPMR" property="id_doc"/>')" />											
													</td>
													<td>								
													</td>
												</tr>
											</logic:iterate>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<bean:message key="screen.q_qcs02.remarkComment"/>
							<html:text name="_q_qcsForm" property="remarks_pmr" styleClass="SessionRemarkTextBox"/>
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<logic:equal name="_q_qcsForm" property="enableRejectApprove" value="1">
								<input type="submit" class= "button"  value="<bean:message key="screen.q_qcs02.approve"/>" name="forward_section_appr" onclick="clickApprove()" />
								<input type="submit" class= "button"  value="<bean:message key="screen.q_qcs02.reject"/>" name="forward_section_reject" onclick="clickReject('<bean:message key="POPBEF"/>')"/>
							</logic:equal>
							<logic:equal name="_q_qcsForm" property="enableRejectApprove" value="0">
								<input type="button" class= "button"  value="<bean:message key="screen.q_qcs02.approve"/>" disabled="disabled" />
								<input type="button" class= "button"  value="<bean:message key="screen.q_qcs02.reject"/>" disabled="disabled"/>
							</logic:equal>
							
							<input type="button" class="fakeFileQCS" name="pseudobutton" value="<bean:message key="screen.q_qcs02.uploadFile"/>" >
							<input id="pmr_file"  type="file" name="file_1" class="realFileSectGrp" />
							
							<logic:equal name="_q_qcsForm" property="enableObtainApprovalPMR" value="1">
								<input type="submit"  class="ObtainApprovalButton" value="<bean:message key="screen.q_qcs02.obtainApproval"/>" name="forward_obt_appr_sectgrp" onclick="clickObtainApprovalSectGrp('<bean:message key="POPBEF"/>','PMR')"/>
							</logic:equal>
							<logic:equal name="_q_qcsForm" property="enableObtainApprovalPMR" value="0">
								<input type="submit"  class="ObtainApprovalButton" value="<bean:message key="screen.q_qcs02.obtainApproval"/>" name="forward_obt_appr_sectgrp" disabled="disabled" onclick="clickObtainApprovalSectGrp('<bean:message key="POPBEF"/>','PMR')"/>
							</logic:equal>
						</td>
					</tr>				
				</table>
			</logic:equal>
			<logic:equal name="_q_qcsForm" property="enablePMR" value="0">
				<table cellpadding="0" cellspacing="0" class="sessionDetail">
					<tr>			
						<td width="25%" class="standardCol">
							<label id="lblseqPMR"></label>
							<bean:message key="screen.q_qcs02.1pmr"/><br>
							<html:radio name="_q_qcsForm" property="section_no_pmr" value="Q011" disabled="true" onclick="determineStatusPMR(this)"/>
							<bean:message key="screen.q_qcs02.sinotincluded"/>
						</td>
						<td width="35%" class="nonStandardCol">
							<br>
							<html:radio name="_q_qcsForm" property="section_no_pmr" value="Q012" disabled="true" onclick="determineStatusPMR(this)"/>
							<bean:message key="screen.q_qcs02.siincluded"/>
						</td>
						<td width="20%" class="concurrenceCol"><br><bean:message key="screen.q_qcs02.projectmanagement"/></td>
						<td width="20%" class="statusCol">
							<div id="Q011">
								<logic:iterate name="_q_qcsForm" id="apprPMR1" property="listApprPMR1">
								<div style="border: none" class="<bean:write name="apprPMR1" property="appr_status_css"/>">
									<center><bean:write name="apprPMR1" property="appr_status"/></center>
									<br>
									<bean:message key="screen.q_qcs02.approvalName"/><bean:write name="apprPMR1" property="pic"/>
									<br>
									<bean:message key="screen.q_qcs02.approvalDate"/><bean:write name="apprPMR1" property="date_appr"/>
								</div>
								<br>								
								</logic:iterate>								
							</div>
							<div id="Q012">
								<logic:iterate name="_q_qcsForm" id="apprPMR2" property="listApprPMR2">
								<div style="border: none" class="<bean:write name="apprPMR2" property="appr_status_css"/>">
									<center><bean:write name="apprPMR2" property="appr_status"/></center>
									<br>
									<bean:message key="screen.q_qcs02.approvalName"/><bean:write name="apprPMR2" property="pic"/>
									<br>
									<bean:message key="screen.q_qcs02.approvalDate"/><bean:write name="apprPMR2" property="date_appr"/>
								</div>
								<br>								
								</logic:iterate>								
							</div>
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<table style="border:0;width:100%;font-size:15px;">
								<tr>
									<td style="vertical-align:top;" width="5%">
											<bean:message key="screen.q_qcs02.attachment"/><bean:message key="screen.q_qcs.colon"/>
									</td>
									<td>
										<table id="attachmentPMR" style="border:0;width:100%;font-size:15px;">
											<logic:iterate name="_q_qcsForm" id="fileInfoPMR" property="attachmentPMR">
												<tr>
													<td>
														<a href="#" onclick="clickDownload('<bean:write name="fileInfoPMR" property="filename"/>') "> <bean:write name="fileInfoPMR" property="filename"/></a>											
													</td>
												<td>								
												</td>
											</tr>
										</logic:iterate>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<bean:message key="screen.q_qcs02.remarkComment"/>
							<html:text name="_q_qcsForm" property="remarks_pmr" styleClass="SessionRemarkTextBox" disabled="true"/>
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<input type="button" class= "button"  value="<bean:message key="screen.q_qcs02.approve"/>" disabled="disabled" />
							<input type="button" class= "button"  value="<bean:message key="screen.q_qcs02.reject"/>" disabled="disabled"/>							
							<input type="button"   value="<bean:message key="screen.q_qcs02.uploadFile"/>" disabled="disabled"/>
							<input id="pmr_file"  type="file" name="file_1" class="realFileSectGrp"  style="display:none"/>					
							<input type="button"  class="ObtainApprovalButton" value="<bean:message key="screen.q_qcs02.obtainApproval"/>" disabled="disabled" onclick="clickObtainApprovalSectGrp('<bean:message key="POPBEF"/>','PMR')"/>						
						</td>
					</tr>				
				</table>
			</logic:equal>
			</div>
			<!-- Session BZR -->
			<div id="divBZR">
			<logic:equal name="_q_qcsForm" property="enableBZR" value="1">
				<table cellpadding="0" cellspacing="0" class="sessionDetail">
					<tr>			
						<td width="25%" class="standardCol">
							<label id="lblseqBZR"></label>
							<bean:message key="screen.q_qcs02.2businessrisk"/><br>
							<logic:equal name="_q_qcsForm" property="disableSectionNo" value="true">
								<html:radio name="_q_qcsForm" property="section_no_bzr" value="Q021" onclick="determineStatusBZR(this)" disabled="true" />
							</logic:equal>
							<logic:equal name="_q_qcsForm" property="disableSectionNo" value="false">
								<html:radio name="_q_qcsForm" property="section_no_bzr" value="Q021" onclick="determineStatusBZR(this)" disabled="false" />
							</logic:equal>
							
							<bean:message key="screen.q_qcs02.standardProduct"/>
						</td>
						<td width="35%" class="nonStandardCol">
							<br>
							<logic:equal name="_q_qcsForm" property="disableSectionNo" value="true">
								<html:radio name="_q_qcsForm" property="section_no_bzr" value="Q022" onclick="determineStatusBZR(this)" disabled="true" />
							</logic:equal>
							<logic:equal name="_q_qcsForm" property="disableSectionNo" value="false">
								<html:radio name="_q_qcsForm" property="section_no_bzr" value="Q022" onclick="determineStatusBZR(this)" disabled="false" />
							</logic:equal>
							
							<bean:message key="screen.q_qcs02.notstandardproduct"/>
							<br>&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="screen.q_qcs02.notstandardproduct1"/>
							<br>&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="screen.q_qcs02.notstandardproduct2"/>
							<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="screen.q_qcs02.notstandardproduct3"/>
							<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="screen.q_qcs02.notstandardproduct4"/>
							<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="screen.q_qcs02.notstandardproduct5"/>
						</td>
						<td width="20%" class="concurrenceCol"><br><bean:message key="screen.q_qcs02.headofproduct"/></td>
						<td width="20%" class="statusCol">
							<div id="Q021">
								<logic:iterate name="_q_qcsForm" id="apprBZR1" property="listApprBZR1">
								<div style="border: none" class="<bean:write name="apprBZR1" property="appr_status_css"/>">
									<center><bean:write name="apprBZR1" property="appr_status"/></center>
									<br>
									<bean:message key="screen.q_qcs02.approvalName"/><bean:write name="apprBZR1" property="pic"/>
									<br>
									<bean:message key="screen.q_qcs02.approvalDate"/><bean:write name="apprBZR1" property="date_appr"/>
								</div>
								<br>								
								</logic:iterate>								
							</div>
							<div id="Q022">
								<logic:iterate name="_q_qcsForm" id="apprBZR2" property="listApprBZR2">
								<div style="border: none"  class="<bean:write name="apprBZR2" property="appr_status_css"/>">
									<center><bean:write name="apprBZR2" property="appr_status"/></center>
									<br>
									<bean:message key="screen.q_qcs02.approvalName"/><bean:write name="apprBZR2" property="pic"/>
									<br>
									<bean:message key="screen.q_qcs02.approvalDate"/><bean:write name="apprBZR2" property="date_appr"/>
								</div>
								<br>								
								</logic:iterate>								
							</div>						
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<table style="border:0;width:100%;font-size:15px;">
								<tr>
									<td style="vertical-align:top;" width="5%">
											<bean:message key="screen.q_qcs02.attachment"/><bean:message key="screen.q_qcs.colon"/>
									</td>
									<td>
										<table id="attachmentBZR" style="border:0;width:100%;font-size:15px;">
											<logic:iterate name="_q_qcsForm" id="fileInfoBZR" property="attachmentBZR">
												<tr>
													<td>
														<a href="#" onclick="clickDownload('<bean:write name="fileInfoBZR" property="filename"/>') "> <bean:write name="fileInfoBZR" property="filename"/></a>
														<img src='../image/delete.gif' onclick="removeRow(this,'BZR','<bean:write name="fileInfoBZR" property="id_doc"/>')" />										
													</td>
													<td>								
													</td>
												</tr>
											</logic:iterate>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<bean:message key="screen.q_qcs02.remarkComment"/>
							<html:text name="_q_qcsForm" property="remarks_bzr" styleClass="SessionRemarkTextBox"/>
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<logic:equal name="_q_qcsForm" property="enableRejectApprove" value="1">
								<input type="submit" class= "button"  value="<bean:message key="screen.q_qcs02.approve"/>" name="forward_section_appr" onclick="clickApprove()"/>
								<input type="submit" class= "button"  value="<bean:message key="screen.q_qcs02.reject"/>" name="forward_section_reject" onclick="clickReject('<bean:message key="POPBEF"/>')"/>
							</logic:equal>
							<logic:equal name="_q_qcsForm" property="enableRejectApprove" value="0">
								<input type="button" class= "button"  value="<bean:message key="screen.q_qcs02.approve"/>" disabled="disabled" />
								<input type="button" class= "button"  value="<bean:message key="screen.q_qcs02.reject"/>" disabled="disabled"/>
							</logic:equal>	
												
							<input type="button" class="fakeFileQCS" name="pseudobutton" value="<bean:message key="screen.q_qcs02.uploadFile"/>" >
							<input id="bzr_file"  type="file" name="file_1" class="realFileSectGrp" />

							<logic:equal name="_q_qcsForm" property="enableObtainApprovalBZR" value="1">
								<input type="submit"  class="ObtainApprovalButton" value="<bean:message key="screen.q_qcs02.obtainApproval"/>"  name="forward_obt_appr_sectgrp"  onclick="clickObtainApprovalSectGrp('<bean:message key="POPBEF"/>','BZR')"/>
							</logic:equal>
							<logic:equal name="_q_qcsForm" property="enableObtainApprovalBZR" value="0">
								<input type="submit"  class="ObtainApprovalButton" value="<bean:message key="screen.q_qcs02.obtainApproval"/>"  name="forward_obt_appr_sectgrp" disabled="disabled" onclick="clickObtainApprovalSectGrp('<bean:message key="POPBEF"/>','BZR')"/>
							</logic:equal>
						</td>
					</tr>				
				</table>
			</logic:equal>
			<logic:equal name="_q_qcsForm" property="enableBZR" value="0">
				<table cellpadding="0" cellspacing="0" class="sessionDetail">
					<tr>			
						<td width="25%" class="standardCol">
							<label id="lblseqBZR"></label>
							<bean:message key="screen.q_qcs02.2businessrisk"/><br>
							<html:radio name="_q_qcsForm" property="section_no_bzr" value="Q021" disabled="true" onclick="determineStatusBZR(this)"/>
							<bean:message key="screen.q_qcs02.standardProduct"/>
						</td>
						<td width="35%" class="nonStandardCol">
							<br>
							<html:radio name="_q_qcsForm" property="section_no_bzr" value="Q022" disabled="true" onclick="determineStatusBZR(this)"/>
							<bean:message key="screen.q_qcs02.notstandardproduct"/>
							<br>&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="screen.q_qcs02.notstandardproduct1"/>
							<br>&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="screen.q_qcs02.notstandardproduct2"/>
							<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="screen.q_qcs02.notstandardproduct3"/>
							<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="screen.q_qcs02.notstandardproduct4"/>
							<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="screen.q_qcs02.notstandardproduct5"/>
						</td>
						<td width="20%" class="concurrenceCol"><br><bean:message key="screen.q_qcs02.headofproduct"/></td>
						<td width="20%" class="statusCol">
							<div id="Q021">
								<logic:iterate name="_q_qcsForm" id="apprBZR1" property="listApprBZR1">
								<div style="border: none" class="<bean:write name="apprBZR1" property="appr_status_css"/>">
									<center><bean:write name="apprBZR1" property="appr_status"/></center>
									<br>
									<bean:message key="screen.q_qcs02.approvalName"/><bean:write name="apprBZR1" property="pic"/>
									<br>
									<bean:message key="screen.q_qcs02.approvalDate"/><bean:write name="apprBZR1" property="date_appr"/>
								</div>
								<br>								
								</logic:iterate>								
							</div>
							<div id="Q022">
								<logic:iterate name="_q_qcsForm" id="apprBZR2" property="listApprBZR2">
								<div style="border: none"  class="<bean:write name="apprBZR2" property="appr_status_css"/>">
									<center><bean:write name="apprBZR2" property="appr_status"/></center>
									<br>
									<bean:message key="screen.q_qcs02.approvalName"/><bean:write name="apprBZR2" property="pic"/>
									<br>
									<bean:message key="screen.q_qcs02.approvalDate"/><bean:write name="apprBZR2" property="date_appr"/>
								</div>
								<br>								
								</logic:iterate>								
							</div>
						
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<table style="border:0;width:100%;font-size:15px;">
								<tr>
									<td style="vertical-align:top;" width="5%">
											<bean:message key="screen.q_qcs02.attachment"/><bean:message key="screen.q_qcs.colon"/>
									</td>
									<td>
										<table id="attachmentBZR" style="border:0;width:100%;font-size:15px;">
											<logic:iterate name="_q_qcsForm" id="fileInfoBZR" property="attachmentBZR">
												<tr>
													<td>
														<a href="#" onclick="clickDownload('<bean:write name="fileInfoBZR" property="filename"/>') "> <bean:write name="fileInfoBZR" property="filename"/></a>																								
													</td>
													<td>								
													</td>
												</tr>
											</logic:iterate>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<bean:message key="screen.q_qcs02.remarkComment"/>
							<html:text name="_q_qcsForm" property="remarks_bzr" styleClass="SessionRemarkTextBox" disabled="true"/>
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<input type="button" class= "button"  value="<bean:message key="screen.q_qcs02.approve"/>" disabled="disabled" />
							<input type="button" class= "button"  value="<bean:message key="screen.q_qcs02.reject"/>" disabled="disabled"/>
							<input type="button"   value="<bean:message key="screen.q_qcs02.uploadFile"/>" disabled="disabled"/>
							<input id="bzr_file"  type="file" name="file_1" class="realFileSectGrp"  style="display:none"/>
							<input type="button"  class="ObtainApprovalButton" value="<bean:message key="screen.q_qcs02.obtainApproval"/>" disabled="disabled" onclick="clickObtainApprovalSectGrp('<bean:message key="POPBEF"/>','BZR')"/>
						</td>
					</tr>				
				</table>
			</logic:equal>
			</div>
			
			<!-- Session CTC -->
			<div id="divCTC">
			<logic:equal name="_q_qcsForm" property="enableCTC" value="1">
				<table cellpadding="0" cellspacing="0" class="sessionDetail">
					<tr>			
						<td width="25%" class="standardCol">
							<label id="lblseqCTC"></label>
							<bean:message key="screen.q_qcs02.3contract"/><br>
							<logic:equal name="_q_qcsForm" property="disableSectionNo" value="true">
								<html:radio name="_q_qcsForm" property="section_no_ctc" value="Q031" onclick="determineStatusCTC(this)" disabled="true" />
							</logic:equal>
							<logic:equal name="_q_qcsForm" property="disableSectionNo" value="false">
								<html:radio name="_q_qcsForm" property="section_no_ctc" value="Q031" onclick="determineStatusCTC(this)" disabled="false" />
							</logic:equal>
							
							<bean:message key="screen.q_qcs02.standardContract"/>
						</td>
						<td width="35%" class="nonStandardCol">
							<br>
							<logic:equal name="_q_qcsForm" property="disableSectionNo" value="true">
								<html:radio name="_q_qcsForm" property="section_no_ctc" value="Q032" onclick="determineStatusCTC(this)" disabled="true" />
							</logic:equal>
							<logic:equal name="_q_qcsForm" property="disableSectionNo" value="false">
								<html:radio name="_q_qcsForm" property="section_no_ctc" value="Q032" onclick="determineStatusCTC(this)" disabled="false" />
							</logic:equal>
							
							<bean:message key="screen.q_qcs02.notstandardContract"/>
							<br>&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="screen.q_qcs02.notstandardContract1"/>
						</td>
						<td width="20%" class="concurrenceCol"><br><bean:message key="screen.q_qcs02.headoflegaldept"/></td>
						<td width="20%" class="statusCol">
							<div id="Q031">
								<logic:iterate name="_q_qcsForm" id="apprCTC1" property="listApprCTC1">
								<div style="border: none" class="<bean:write name="apprCTC1" property="appr_status_css"/>">
									<center><bean:write name="apprCTC1" property="appr_status"/></center>
									<br>
									<bean:message key="screen.q_qcs02.approvalName"/><bean:write name="apprCTC1" property="pic"/>
									<br>
									<bean:message key="screen.q_qcs02.approvalDate"/><bean:write name="apprCTC1" property="date_appr"/>
								</div>
								<br>								
								</logic:iterate>								
							</div>
							<div id="Q032">
								<logic:iterate name="_q_qcsForm" id="apprCTC2" property="listApprCTC2">
								<div style="border: none"  class="<bean:write name="apprCTC2" property="appr_status_css"/>">
									<center><bean:write name="apprCTC2" property="appr_status"/></center>
									<br>
									<bean:message key="screen.q_qcs02.approvalName"/><bean:write name="apprCTC2" property="pic"/>
									<br>
									<bean:message key="screen.q_qcs02.approvalDate"/><bean:write name="apprCTC2" property="date_appr"/>
								</div>
								<br>								
								</logic:iterate>								
							</div>	
						
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<table style="border:0;width:100%;font-size:15px;">
								<tr>
									<td style="vertical-align:top;" width="5%">
											<bean:message key="screen.q_qcs02.attachment"/><bean:message key="screen.q_qcs.colon"/>
									</td>
									<td>
										<table id="attachmentCTC" style="border:0;width:100%;font-size:15px;">
											<logic:iterate name="_q_qcsForm" id="fileInfoCTC" property="attachmentCTC">
												<tr>
													<td>
														<a href="#" onclick="clickDownload('<bean:write name="fileInfoCTC" property="filename"/>') "> <bean:write name="fileInfoCTC" property="filename"/></a>	
														<img src='../image/delete.gif' onclick="removeRow(this,'CTC','<bean:write name="fileInfoCTC" property="id_doc"/>')" />									
													</td>
													<td>								
													</td>
												</tr>
											</logic:iterate>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<bean:message key="screen.q_qcs02.remarkComment"/>
							<html:text name="_q_qcsForm" property="remarks_ctc" styleClass="SessionRemarkTextBox"/>
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<logic:equal name="_q_qcsForm" property="enableRejectApprove" value="1">
								<input type="submit" class= "button"  value="<bean:message key="screen.q_qcs02.approve"/>" name="forward_section_appr" onclick="clickApprove()"/>
								<input type="submit" class= "button"  value="<bean:message key="screen.q_qcs02.reject"/>" name="forward_section_reject" onclick="clickReject('<bean:message key="POPBEF"/>')"/>
							</logic:equal>
							<logic:equal name="_q_qcsForm" property="enableRejectApprove" value="0">
								<input type="button" class= "button"  value="<bean:message key="screen.q_qcs02.approve"/>" disabled="disabled" />
								<input type="button" class= "button"  value="<bean:message key="screen.q_qcs02.reject"/>" disabled="disabled"/>
							</logic:equal>						
							<input type="button" class="fakeFileQCS" name="pseudobutton" value="<bean:message key="screen.q_qcs02.uploadFile"/>" >
							<input id="ctc_file"  type="file" name="file_1" class="realFileSectGrp" />

							<logic:equal name="_q_qcsForm" property="enableObtainApprovalCTC" value="1">
								<input type="submit"  class="ObtainApprovalButton" value="<bean:message key="screen.q_qcs02.obtainApproval"/>" name="forward_obt_appr_sectgrp"  onclick="clickObtainApprovalSectGrp('<bean:message key="POPBEF"/>','CTC')"/>
							</logic:equal>
							<logic:equal name="_q_qcsForm" property="enableObtainApprovalCTC" value="0">
								<input type="submit"  class="ObtainApprovalButton" value="<bean:message key="screen.q_qcs02.obtainApproval"/>" name="forward_obt_appr_sectgrp"  disabled="disabled" onclick="clickObtainApprovalSectGrp('<bean:message key="POPBEF"/>','CTC')"/>
							</logic:equal>
						</td>
					</tr>				
				</table>
			</logic:equal>
			<logic:equal name="_q_qcsForm" property="enableCTC" value="0">
				<table cellpadding="0" cellspacing="0" class="sessionDetail">
					<tr>			
						<td width="25%" class="standardCol">
							<label id="lblseqCTC"></label>
							<bean:message key="screen.q_qcs02.3contract"/><br>
							&nbsp;&nbsp;<html:radio name="_q_qcsForm" property="section_no_ctc" value="Q031" disabled="true" onclick="determineStatusCTC(this)"/>
							<bean:message key="screen.q_qcs02.standardContract"/>
						</td>
						<td width="35%" class="nonStandardCol">
							<br>
							<html:radio name="_q_qcsForm" property="section_no_ctc" value="Q032" disabled="true" onclick="determineStatusCTC(this)"/>
							<bean:message key="screen.q_qcs02.notstandardContract"/>
							<br>&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="screen.q_qcs02.notstandardContract1"/>
						</td>
						<td width="20%" class="concurrenceCol"><br><bean:message key="screen.q_qcs02.headoflegaldept"/></td>
						<td width="20%" class="statusCol">
							<div id="Q031">
								<logic:iterate name="_q_qcsForm" id="apprCTC1" property="listApprCTC1">
								<div style="border: none" class="<bean:write name="apprCTC1" property="appr_status_css"/>">
									<center><bean:write name="apprCTC1" property="appr_status"/></center>
									<br>
									<bean:message key="screen.q_qcs02.approvalName"/><bean:write name="apprCTC1" property="pic"/>
									<br>
									<bean:message key="screen.q_qcs02.approvalDate"/><bean:write name="apprCTC1" property="date_appr"/>
								</div>
								<br>								
								</logic:iterate>								
							</div>
							<div id="Q032">
								<logic:iterate name="_q_qcsForm" id="apprCTC2" property="listApprCTC2">
								<div style="border: none"  class="<bean:write name="apprCTC2" property="appr_status_css"/>">
									<center><bean:write name="apprCTC2" property="appr_status"/></center>
									<br>
									<bean:message key="screen.q_qcs02.approvalName"/><bean:write name="apprCTC2" property="pic"/>
									<br>
									<bean:message key="screen.q_qcs02.approvalDate"/><bean:write name="apprCTC2" property="date_appr"/>
								</div>
								<br>								
								</logic:iterate>								
							</div>	
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<table style="border:0;width:100%;font-size:15px;">
								<tr>
									<td style="vertical-align:top;" width="5%">
											<bean:message key="screen.q_qcs02.attachment"/><bean:message key="screen.q_qcs.colon"/>
									</td>
									<td>
										<table id="attachmentCTC" style="border:0;width:100%;font-size:15px;">
											<logic:iterate name="_q_qcsForm" id="fileInfoCTC" property="attachmentCTC">
												<tr>
													<td>
														<a href="#" onclick="clickDownload('<bean:write name="fileInfoCTC" property="filename"/>') "> <bean:write name="fileInfoCTC" property="filename"/></a>	
																							
													</td>
													<td>								
													</td>
												</tr>
											</logic:iterate>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<bean:message key="screen.q_qcs02.remarkComment"/>
							<html:text name="_q_qcsForm" property="remarks_ctc" styleClass="SessionRemarkTextBox" disabled="true"/>
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">						
							<input type="button" class= "button"  value="<bean:message key="screen.q_qcs02.approve"/>" disabled="disabled" />
							<input type="button" class= "button"  value="<bean:message key="screen.q_qcs02.reject"/>" disabled="disabled"/>												
							<input type="button"   value="<bean:message key="screen.q_qcs02.uploadFile"/>" disabled="disabled"/>
							<input id="ctc_file"  type="file" name="file_1" class="realFileSectGrp"  style="display:none"/>
							<input type="button"  class="ObtainApprovalButton" value="<bean:message key="screen.q_qcs02.obtainApproval"/>" disabled="disabled" onclick="clickObtainApprovalSectGrp('<bean:message key="POPBEF"/>','CTC')"/>							
						</td>
					</tr>				
				</table>
			</logic:equal>
			</div>
			<!-- Session PRI -->
			<div id="divPRI">
			<logic:equal name="_q_qcsForm" property="enablePRI" value="1">
				<table cellpadding="0" cellspacing="0" class="sessionDetail">
					<tr>			
						<td width="25%" class="standardCol">
							<label id="lblseqPRI"></label>
							<bean:message key="screen.q_qcs02.4pricing"/><br>
							<logic:equal name="_q_qcsForm" property="disableSectionNo" value="true">
								<html:radio name="_q_qcsForm" property="section_no_pri" value="Q041" onclick="determineStatusPRI(this)" disabled="true" />
							</logic:equal>
							<logic:equal name="_q_qcsForm" property="disableSectionNo" value="false">
								<html:radio name="_q_qcsForm" property="section_no_pri" value="Q041" onclick="determineStatusPRI(this)" disabled="false" />
							</logic:equal>
							
							<bean:message key="screen.q_qcs02.standardPricing"/>
						</td>
						<td width="35%" class="nonStandardCol">
							<br>
							<logic:equal name="_q_qcsForm" property="disableSectionNo" value="true">
								<html:radio name="_q_qcsForm" property="section_no_pri" value="Q042" onclick="determineStatusPRI(this)" disabled="true" />
							</logic:equal>
							<logic:equal name="_q_qcsForm" property="disableSectionNo" value="false">
								<html:radio name="_q_qcsForm" property="section_no_pri" value="Q042" onclick="determineStatusPRI(this)" disabled="false" />
							</logic:equal>
							
							<bean:message key="screen.q_qcs02.belowTariffLimit"/>
							<br>&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="screen.q_qcs02.belowTariffLimit1"/>
							<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="screen.q_qcs02.belowTariffLimit2"/>
							<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="screen.q_qcs02.belowTariffLimit3"/>
							<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="screen.q_qcs02.belowTariffLimit4"/>
						</td>
						<td width="20%" class="concurrenceCol"><br><bean:message key="screen.q_qcs02.headofproduct"/></td>
						<td width="20%" class="statusCol">
							<div id="Q041">
								<logic:iterate name="_q_qcsForm" id="apprPRI1" property="listApprPRI1">
								<div style="border: none" class="<bean:write name="apprPRI1" property="appr_status_css"/>">
									<center><bean:write name="apprPRI1" property="appr_status"/></center>
									<br>
									<bean:message key="screen.q_qcs02.approvalName"/><bean:write name="apprPRI1" property="pic"/>
									<br>
									<bean:message key="screen.q_qcs02.approvalDate"/><bean:write name="apprPRI1" property="date_appr"/>
								</div>
								<br>								
								</logic:iterate>								
							</div>
							<div id="Q042">
								<logic:iterate name="_q_qcsForm" id="apprPRI2" property="listApprPRI2">
								<div style="border: none"  class="<bean:write name="apprPRI2" property="appr_status_css"/>">
									<center><bean:write name="apprPRI2" property="appr_status"/></center>
									<br>
									<bean:message key="screen.q_qcs02.approvalName"/><bean:write name="apprPRI2" property="pic"/>
									<br>
									<bean:message key="screen.q_qcs02.approvalDate"/><bean:write name="apprPRI2" property="date_appr"/>
								</div>
								<br>								
								</logic:iterate>								
							</div>	
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<table style="border:0;width:100%;font-size:15px;">
								<tr>
									<td style="vertical-align:top;" width="5%">
											<bean:message key="screen.q_qcs02.attachment"/><bean:message key="screen.q_qcs.colon"/>
									</td>
									<td>
										<table id="attachmentPRI" style="border:0;width:100%;font-size:15px;">
											<logic:iterate name="_q_qcsForm" id="fileInfoPRI" property="attachmentPRI">
												<tr>
													<td>
														<a href="#" onclick="clickDownload('<bean:write name="fileInfoPRI" property="filename"/>') "> <bean:write name="fileInfoPRI" property="filename"/></a>	
														<img src='../image/delete.gif' onclick="removeRow(this,'PRI','<bean:write name="fileInfoPRI" property="id_doc"/>')" />									
													</td>
													<td>								
													</td>
												</tr>
											</logic:iterate>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<bean:message key="screen.q_qcs02.remarkComment"/>
							<html:text name="_q_qcsForm" property="remarks_pri" styleClass="SessionRemarkTextBox"/>
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<logic:equal name="_q_qcsForm" property="enableRejectApprove" value="1">
								<input type="submit" class= "button"  value="<bean:message key="screen.q_qcs02.approve"/>" name="forward_section_appr" onclick="clickApprove()"/>
								<input type="submit" class= "button"  value="<bean:message key="screen.q_qcs02.reject"/>" name="forward_section_reject" onclick="clickReject('<bean:message key="POPBEF"/>')"/>
							</logic:equal>
							<logic:equal name="_q_qcsForm" property="enableRejectApprove" value="0">
								<input type="button" class= "button"  value="<bean:message key="screen.q_qcs02.approve"/>" disabled="disabled" />
								<input type="button" class= "button"  value="<bean:message key="screen.q_qcs02.reject"/>" disabled="disabled"/>
							</logic:equal>						
							<input type="button" class="fakeFileQCS" name="pseudobutton" value="<bean:message key="screen.q_qcs02.uploadFile"/>" >
							<input id="pri_file"  type="file" name="file_1" class="realFileSectGrp" />

							<logic:equal name="_q_qcsForm" property="enableObtainApprovalPRI" value="1">
								<input type="submit"  class="ObtainApprovalButton" value="<bean:message key="screen.q_qcs02.obtainApproval"/>"  name="forward_obt_appr_sectgrp" onclick="clickObtainApprovalSectGrp('<bean:message key="POPBEF"/>','PRI')"/>
							</logic:equal>
							<logic:equal name="_q_qcsForm" property="enableObtainApprovalPRI" value="0">
								<input type="submit"  class="ObtainApprovalButton" value="<bean:message key="screen.q_qcs02.obtainApproval"/>"  name="forward_obt_appr_sectgrp" disabled="disabled" onclick="clickObtainApprovalSectGrp('<bean:message key="POPBEF"/>','PRI')"/>
							</logic:equal>
						</td>
					</tr>				
				</table>
			</logic:equal>
			<logic:equal name="_q_qcsForm" property="enablePRI" value="0">
				<table cellpadding="0" cellspacing="0" class="sessionDetail">
					<tr>			
						<td width="25%" class="standardCol">
							<label id="lblseqPRI"></label>
							<bean:message key="screen.q_qcs02.4pricing"/><br>
							&nbsp;&nbsp;<html:radio name="_q_qcsForm" property="section_no_pri" value="Q041" disabled="true" onclick="determineStatusPRI(this)"/>
							<bean:message key="screen.q_qcs02.standardPricing"/>
						</td>
						<td width="35%" class="nonStandardCol">
							<br>
							<html:radio name="_q_qcsForm" property="section_no_pri" value="Q042" disabled="true" onclick="determineStatusPRI(this)"/>
							<bean:message key="screen.q_qcs02.belowTariffLimit"/>
							<br>&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="screen.q_qcs02.belowTariffLimit1"/>
							<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="screen.q_qcs02.belowTariffLimit2"/>
							<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="screen.q_qcs02.belowTariffLimit3"/>
							<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="screen.q_qcs02.belowTariffLimit4"/>
						</td>
						<td width="20%" class="concurrenceCol"><br><bean:message key="screen.q_qcs02.headofproduct"/></td>
						<td width="20%" class="statusCol">
							<div id="Q041">
								<logic:iterate name="_q_qcsForm" id="apprPRI1" property="listApprPRI1">
								<div style="border: none" class="<bean:write name="apprPRI1" property="appr_status_css"/>">
									<center><bean:write name="apprPRI1" property="appr_status"/></center>
									<br>
									<bean:message key="screen.q_qcs02.approvalName"/><bean:write name="apprPRI1" property="pic"/>
									<br>
									<bean:message key="screen.q_qcs02.approvalDate"/><bean:write name="apprPRI1" property="date_appr"/>
								</div>
								<br>								
								</logic:iterate>								
							</div>
							<div id="Q042">
								<logic:iterate name="_q_qcsForm" id="apprPRI2" property="listApprPRI2">
								<div style="border: none"  class="<bean:write name="apprPRI2" property="appr_status_css"/>">
									<center><bean:write name="apprPRI2" property="appr_status"/></center>
									<br>
									<bean:message key="screen.q_qcs02.approvalName"/><bean:write name="apprPRI2" property="pic"/>
									<br>
									<bean:message key="screen.q_qcs02.approvalDate"/><bean:write name="apprPRI2" property="date_appr"/>
								</div>
								<br>								
								</logic:iterate>								
							</div>	
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<table style="border:0;width:100%;font-size:15px;">
								<tr>
									<td style="vertical-align:top;" width="5%">
											<bean:message key="screen.q_qcs02.attachment"/><bean:message key="screen.q_qcs.colon"/>
									</td>
									<td>
										<table id="attachmentPRI" style="border:0;width:100%;font-size:15px;">
											<logic:iterate name="_q_qcsForm" id="fileInfoPRI" property="attachmentPRI">
												<tr>
													<td>
														<a href="#" onclick="clickDownload('<bean:write name="fileInfoPRI" property="filename"/>') "> <bean:write name="fileInfoPRI" property="filename"/></a>	
																							
													</td>
													<td>								
													</td>
												</tr>
											</logic:iterate>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<bean:message key="screen.q_qcs02.remarkComment"/>
							<html:text name="_q_qcsForm" property="remarks_pri" styleClass="SessionRemarkTextBox" disabled="true"/>
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">						
							<input type="button" class= "button"  value="<bean:message key="screen.q_qcs02.approve"/>" disabled="disabled" />
							<input type="button" class= "button"  value="<bean:message key="screen.q_qcs02.reject"/>" disabled="disabled"/>		
							<input type="button"   value="<bean:message key="screen.q_qcs02.uploadFile"/>" disabled="disabled"/>	
							<input id="pri_file"  type="file" name="file_1" class="realFileSectGrp"  style="display:none"/>						
							<input type="button"  class="ObtainApprovalButton" value="<bean:message key="screen.q_qcs02.obtainApproval"/>" disabled="disabled" onclick="clickObtainApprovalSectGrp('<bean:message key="POPBEF"/>','PRI')"/>							
						</td>
					</tr>				
				</table>
			</logic:equal>
			</div>
			<!-- Session MRG -->
			<div id="divMRG">
			<logic:equal name="_q_qcsForm" property="enableMRG" value="1">
				<table cellpadding="0" cellspacing="0" class="sessionDetail">
					<tr>			
						<td  rowspan="2" width="25%" class="standardCol">
							<label id="lblseqMRG"></label>
							<bean:message key="screen.q_qcs02.5margin"/><br>
							<logic:equal name="_q_qcsForm" property="disableSectionNo" value="true">
								<html:radio name="_q_qcsForm" property="section_no_mrg" value="Q051" onclick="determineStatusMRG(this)" disabled="true" />
							</logic:equal>
							<logic:equal name="_q_qcsForm" property="disableSectionNo" value="false">
								<html:radio name="_q_qcsForm" property="section_no_mrg" value="Q051" onclick="determineStatusMRG(this)" disabled="false" />
							</logic:equal>
							
							<bean:message key="screen.q_qcs02.standardMargin"/>
						</td>
						<td width="35%" class="nonStandardCol" style="vertical-align:middle;">
							<logic:equal name="_q_qcsForm" property="disableSectionNo" value="true">
								<html:radio name="_q_qcsForm" property="section_no_mrg" value="Q052" onclick="determineStatusMRG(this)" disabled="true" />
							</logic:equal>
							<logic:equal name="_q_qcsForm" property="disableSectionNo" value="false">
								<html:radio name="_q_qcsForm" property="section_no_mrg" value="Q052" onclick="determineStatusMRG(this)" disabled="false" />
							</logic:equal>
							
							<bean:message key="screen.q_qcs02.nonstandardMargin1"/>
						</td>
						<td width="20%" class="concurrenceCol" style="vertical-align:middle;"><bean:message key="screen.q_qcs02.coo"/></td>
						<td rowspan="2" width="20%" class="statusCol">
							<div id="Q051">
								<logic:iterate name="_q_qcsForm" id="apprMRG1" property="listApprMRG1">
								<div style="border: none" class="<bean:write name="apprMRG1" property="appr_status_css"/>">
									<center><bean:write name="apprMRG1" property="appr_status"/></center>
									<br>
									<bean:message key="screen.q_qcs02.approvalName"/><bean:write name="apprMRG1" property="pic"/>
									<br>
									<bean:message key="screen.q_qcs02.approvalDate"/><bean:write name="apprMRG1" property="date_appr"/>
								</div>
								<br>								
								</logic:iterate>								
							</div>
							<div id="Q052">
								<logic:iterate name="_q_qcsForm" id="apprMRG2" property="listApprMRG2">
								<div style="border: none"  class="<bean:write name="apprMRG2" property="appr_status_css"/>">
									<center><bean:write name="apprMRG2" property="appr_status"/></center>
									<br>
									<bean:message key="screen.q_qcs02.approvalName"/><bean:write name="apprMRG2" property="pic"/>
									<br>
									<bean:message key="screen.q_qcs02.approvalDate"/><bean:write name="apprMRG2" property="date_appr"/>
								</div>
								<br>								
								</logic:iterate>								
							</div>	
							<div id="Q053">
								<logic:iterate name="_q_qcsForm" id="apprMRG3" property="listApprMRG3">
								<div style="border: none"  class="<bean:write name="apprMRG3" property="appr_status_css"/>">
									<center><bean:write name="apprMRG3" property="appr_status"/></center>
									<br>
									<bean:message key="screen.q_qcs02.approvalName"/><bean:write name="apprMRG3" property="pic"/>
									<br>
									<bean:message key="screen.q_qcs02.approvalDate"/><bean:write name="apprMRG3" property="date_appr"/>
								</div>
								<br>								
								</logic:iterate>								
							</div>	
						</td>
					</tr>
					<tr>			
						<td width="35%" class="nonStandardCol" style="vertical-align:middle;">
							<logic:equal name="_q_qcsForm" property="disableSectionNo" value="true">
								<html:radio name="_q_qcsForm" property="section_no_mrg" value="Q053" onclick="determineStatusMRG(this)" disabled="true" />
							</logic:equal>
							<logic:equal name="_q_qcsForm" property="disableSectionNo" value="false">
								<html:radio name="_q_qcsForm" property="section_no_mrg" value="Q053" onclick="determineStatusMRG(this)" disabled="false" />
							</logic:equal>
							
							<bean:message key="screen.q_qcs02.nonstandardMargin2"/>
						</td>
						<td width="20%" class="concurrenceCol" style="vertical-align:middle;"><bean:message key="screen.q_qcs02.ceo"/></td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<table style="border:0;width:100%;font-size:15px;">
								<tr>
									<td style="vertical-align:top;" width="5%">
											<bean:message key="screen.q_qcs02.attachment"/><bean:message key="screen.q_qcs.colon"/>
									</td>
									<td>
										<table id="attachmentMRG" style="border:0;width:100%;font-size:15px;">
											<logic:iterate name="_q_qcsForm" id="fileInfoMRG" property="attachmentMRG">
												<tr>
													<td>
														<a href="#" onclick="clickDownload('<bean:write name="fileInfoMRG" property="filename"/>') "> <bean:write name="fileInfoMRG" property="filename"/></a>	
														<img src='../image/delete.gif' onclick="removeRow(this,'MRG','<bean:write name="fileInfoMRG" property="id_doc"/>')" />									
													</td>
													<td>								
													</td>
												</tr>
											</logic:iterate>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<bean:message key="screen.q_qcs02.remarkComment"/>
							<html:text name="_q_qcsForm" property="remarks_mrg" styleClass="SessionRemarkTextBox"/>
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<logic:equal name="_q_qcsForm" property="enableRejectApprove" value="1">
								<input type="submit" class= "button"  value="<bean:message key="screen.q_qcs02.approve"/>" name="forward_section_appr" onclick="clickApprove()"/>
								<input type="submit" class= "button"  value="<bean:message key="screen.q_qcs02.reject"/>" name="forward_section_reject" onclick="clickReject('<bean:message key="POPBEF"/>')"/>
							</logic:equal>
							<logic:equal name="_q_qcsForm" property="enableRejectApprove" value="0">
								<input type="button" class= "button"  value="<bean:message key="screen.q_qcs02.approve"/>" disabled="disabled" />
								<input type="button" class= "button"  value="<bean:message key="screen.q_qcs02.reject"/>" disabled="disabled"/>
							</logic:equal>						
							<input type="button" class="fakeFileQCS" name="pseudobutton" value="<bean:message key="screen.q_qcs02.uploadFile"/>" >
							<input id="mrg_file"  type="file" name="file_1" class="realFileSectGrp" />
							
							<logic:equal name="_q_qcsForm" property="enableObtainApprovalMRG" value="1">
								<input type="submit"  class="ObtainApprovalButton" value="<bean:message key="screen.q_qcs02.obtainApproval"/>"  name="forward_obt_appr_sectgrp" onclick="clickObtainApprovalSectGrp('<bean:message key="POPBEF"/>','MRG')"/>
							</logic:equal>
							<logic:equal name="_q_qcsForm" property="enableObtainApprovalMRG" value="0">
								<input type="submit"  class="ObtainApprovalButton" value="<bean:message key="screen.q_qcs02.obtainApproval"/>" disabled="disabled"  name="forward_obt_appr_sectgrp" onclick="clickObtainApprovalSectGrp('<bean:message key="POPBEF"/>','MRG')"/>
							</logic:equal>
						</td>
					</tr>				
				</table>
			</logic:equal>
			<logic:equal name="_q_qcsForm" property="enableMRG" value="0">
				<table cellpadding="0" cellspacing="0" class="sessionDetail">
					<tr>			
						<td  rowspan="2" width="25%" class="standardCol">
							<label id="lblseqMRG"></label>
							<bean:message key="screen.q_qcs02.5margin"/><br>
							&nbsp;&nbsp;<html:radio name="_q_qcsForm" property="section_no_mrg" value="Q051" disabled="true" onclick="determineStatusMRG(this)"/>
							<bean:message key="screen.q_qcs02.standardMargin"/>
						</td>
						<td width="35%" class="nonStandardCol" style="vertical-align:middle;">
							<html:radio name="_q_qcsForm" property="section_no_mrg" value="Q052" disabled="true" onclick="determineStatusMRG(this)"/>
							<bean:message key="screen.q_qcs02.nonstandardMargin1"/>
						</td>
						<td width="20%" class="concurrenceCol" style="vertical-align:middle;"><bean:message key="screen.q_qcs02.coo"/></td>
						<td rowspan="2" width="20%" class="statusCol">
							<div id="Q051">
								<logic:iterate name="_q_qcsForm" id="apprMRG1" property="listApprMRG1">
								<div style="border: none" class="<bean:write name="apprMRG1" property="appr_status_css"/>">
									<center><bean:write name="apprMRG1" property="appr_status"/></center>
									<br>
									<bean:message key="screen.q_qcs02.approvalName"/><bean:write name="apprMRG1" property="pic"/>
									<br>
									<bean:message key="screen.q_qcs02.approvalDate"/><bean:write name="apprMRG1" property="date_appr"/>
								</div>
								<br>								
								</logic:iterate>								
							</div>
							<div id="Q052">
								<logic:iterate name="_q_qcsForm" id="apprMRG2" property="listApprMRG2">
								<div style="border: none"  class="<bean:write name="apprMRG2" property="appr_status_css"/>">
									<center><bean:write name="apprMRG2" property="appr_status"/></center>
									<br>
									<bean:message key="screen.q_qcs02.approvalName"/><bean:write name="apprMRG2" property="pic"/>
									<br>
									<bean:message key="screen.q_qcs02.approvalDate"/><bean:write name="apprMRG2" property="date_appr"/>
								</div>
								<br>								
								</logic:iterate>								
							</div>	
							<div id="Q053">
								<logic:iterate name="_q_qcsForm" id="apprMRG3" property="listApprMRG3">
								<div style="border: none" class="<bean:write name="apprMRG3" property="appr_status_css"/>">
									<center><bean:write name="apprMRG3" property="appr_status"/></center>
									<br>
									<bean:message key="screen.q_qcs02.approvalName"/><bean:write name="apprMRG3" property="pic"/>
									<br>
									<bean:message key="screen.q_qcs02.approvalDate"/><bean:write name="apprMRG3" property="date_appr"/>
								</div>
								<br>								
								</logic:iterate>								
							</div>	
						</td>
					</tr>
					<tr>			
						<td width="35%" class="nonStandardCol" style="vertical-align:middle;">
							<html:radio name="_q_qcsForm" property="section_no_mrg" value="Q053" disabled="true" onclick="determineStatusMRG(this)"/>
							<bean:message key="screen.q_qcs02.nonstandardMargin2"/>
						</td>
						<td width="20%" class="concurrenceCol" style="vertical-align:middle;"><bean:message key="screen.q_qcs02.ceo"/></td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<table style="border:0;width:100%;font-size:15px;">
								<tr>
									<td style="vertical-align:top;" width="5%">
											<bean:message key="screen.q_qcs02.attachment"/><bean:message key="screen.q_qcs.colon"/>
									</td>
									<td>
										<table id="attachmentMRG" style="border:0;width:100%;font-size:15px;">
											<logic:iterate name="_q_qcsForm" id="fileInfoMRG" property="attachmentMRG">
												<tr>
													<td>
														<a href="#" onclick="clickDownload('<bean:write name="fileInfoMRG" property="filename"/>') "> <bean:write name="fileInfoMRG" property="filename"/></a>	
																							
													</td>
													<td>								
													</td>
												</tr>
											</logic:iterate>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<bean:message key="screen.q_qcs02.remarkComment"/>
							<html:text name="_q_qcsForm" property="remarks_mrg" styleClass="SessionRemarkTextBox" disabled="true"/>
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<input type="button" class= "button"  value="<bean:message key="screen.q_qcs02.approve"/>" disabled="disabled" />
							<input type="button" class= "button"  value="<bean:message key="screen.q_qcs02.reject"/>" disabled="disabled"/>											
							<input type="button"   value="<bean:message key="screen.q_qcs02.uploadFile"/>" disabled="disabled"/>
							<input id="mrg_file"  type="file" name="file_1" class="realFileSectGrp"  style="display:none"/>
							<input type="button"  class="ObtainApprovalButton" value="<bean:message key="screen.q_qcs02.obtainApproval"/>" disabled="disabled" onclick="clickObtainApprovalSectGrp('<bean:message key="POPBEF"/>','MRG')"/>							
						</td>
					</tr>				
				</table>
			</logic:equal>
			</div>
			<!-- Session CRC -->
			<div id="divCRC">
			<logic:equal name="_q_qcsForm" property="enableCRC" value="1">
				<table cellpadding="0" cellspacing="0" class="sessionDetail">
					<tr>			
						<td width="25%" class="standardCol">
							<label id="lblseqCRC"></label>
							<bean:message key="screen.q_qcs02.6creditControl"/><br>
							<logic:equal name="_q_qcsForm" property="disableSectionNo" value="true">
								<html:radio name="_q_qcsForm" property="section_no_crc" value="Q061" onclick="determineStatusCRC(this)" disabled="true" />
							</logic:equal>
							<logic:equal name="_q_qcsForm" property="disableSectionNo" value="false">
								<html:radio name="_q_qcsForm" property="section_no_crc" value="Q061" onclick="determineStatusCRC(this)" disabled="false" />
							</logic:equal>
							
							<bean:message key="screen.q_qcs02.standardTerm"/>
						</td>
						<td width="35%" class="nonStandardCol">
							<br>
							<logic:equal name="_q_qcsForm" property="disableSectionNo" value="true">
								<html:radio name="_q_qcsForm" property="section_no_crc" value="Q062" onclick="determineStatusCRC(this)" disabled="true" />
							</logic:equal>
							<logic:equal name="_q_qcsForm" property="disableSectionNo" value="false">
								<html:radio name="_q_qcsForm" property="section_no_crc" value="Q062" onclick="determineStatusCRC(this)" disabled="false" />
							</logic:equal>
							
							<bean:message key="screen.q_qcs02.exemption"/>
						</td>
						<td width="20%" class="concurrenceCol"><br><bean:message key="screen.q_qcs02.headofcorporatediv"/></td>
						<td width="20%" class="statusCol">
							<div id="Q061">
								<logic:iterate name="_q_qcsForm" id="apprCRC1" property="listApprCRC1">
								<div style="border: none" class="<bean:write name="apprCRC1" property="appr_status_css"/>">
									<center><bean:write name="apprCRC1" property="appr_status"/></center>
									<br>
									<bean:message key="screen.q_qcs02.approvalName"/><bean:write name="apprCRC1" property="pic"/>
									<br>
									<bean:message key="screen.q_qcs02.approvalDate"/><bean:write name="apprCRC1" property="date_appr"/>
								</div>
								<br>								
								</logic:iterate>								
							</div>
							<div id="Q062">
								<logic:iterate name="_q_qcsForm" id="apprCRC2" property="listApprCRC2">
								<div style="border: none"  class="<bean:write name="apprCRC2" property="appr_status_css"/>">
									<center><bean:write name="apprCRC2" property="appr_status"/></center>
									<br>
									<bean:message key="screen.q_qcs02.approvalName"/><bean:write name="apprCRC2" property="pic"/>
									<br>
									<bean:message key="screen.q_qcs02.approvalDate"/><bean:write name="apprCRC2" property="date_appr"/>
								</div>
								<br>								
								</logic:iterate>								
							</div>	
							
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<table style="border:0;width:100%;font-size:15px;">
								<tr>
									<td style="vertical-align:top;" width="5%">
											<bean:message key="screen.q_qcs02.attachment"/><bean:message key="screen.q_qcs.colon"/>
									</td>
									<td>
										<table id="attachmentCRC" style="border:0;width:100%;font-size:15px;">
											<logic:iterate name="_q_qcsForm" id="fileInfoCRC" property="attachmentCRC">
												<tr>
													<td>
														<a href="#" onclick="clickDownload('<bean:write name="fileInfoCRC" property="filename"/>') "> <bean:write name="fileInfoCRC" property="filename"/></a>	
														<img src='../image/delete.gif' onclick="removeRow(this,'CRC','<bean:write name="fileInfoCRC" property="id_doc"/>')" />									
													</td>
													<td>								
													</td>
												</tr>
											</logic:iterate>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<bean:message key="screen.q_qcs02.remarkComment"/>
							<html:text name="_q_qcsForm" property="remarks_crc" styleClass="SessionRemarkTextBox"/>
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<logic:equal name="_q_qcsForm" property="enableRejectApprove" value="1">
								<input type="submit" class= "button"  value="<bean:message key="screen.q_qcs02.approve"/>" name="forward_section_appr" onclick="clickApprove()"/>
								<input type="submit" class= "button"  value="<bean:message key="screen.q_qcs02.reject"/>" name="forward_section_reject" onclick="clickReject('<bean:message key="POPBEF"/>')"/>
							</logic:equal>
							<logic:equal name="_q_qcsForm" property="enableRejectApprove" value="0">
								<input type="button" class= "button"  value="<bean:message key="screen.q_qcs02.approve"/>" disabled="disabled" />
								<input type="button" class= "button"  value="<bean:message key="screen.q_qcs02.reject"/>" disabled="disabled"/>
							</logic:equal>						
							<input type="button" class="fakeFileQCS" name="pseudobutton" value="<bean:message key="screen.q_qcs02.uploadFile"/>" >
							<input id="crc_file"  type="file" name="file_1" class="realFileSectGrp" />
							
							<logic:equal name="_q_qcsForm" property="enableObtainApprovalCRC" value="1">
								<input type="submit"  class="ObtainApprovalButton" value="<bean:message key="screen.q_qcs02.obtainApproval"/>"  name="forward_obt_appr_sectgrp" onclick="clickObtainApprovalSectGrp('<bean:message key="POPBEF"/>','CRC')"/>
							</logic:equal>
							<logic:equal name="_q_qcsForm" property="enableObtainApprovalCRC" value="0">
								<input type="submit"  class="ObtainApprovalButton" value="<bean:message key="screen.q_qcs02.obtainApproval"/>" disabled="disabled"  name="forward_obt_appr_sectgrp" onclick="clickObtainApprovalSectGrp('<bean:message key="POPBEF"/>','CRC')"/>
							</logic:equal>
						</td>
					</tr>				
				</table>
			</logic:equal>
			<logic:equal name="_q_qcsForm" property="enableCRC" value="0">
				<table cellpadding="0" cellspacing="0" class="sessionDetail">
					<tr>			
						<td width="25%" class="standardCol">
							<label id="lblseqCRC"></label>
							<bean:message key="screen.q_qcs02.6creditControl"/><br>
							<html:radio name="_q_qcsForm" property="section_no_crc" value="Q061" disabled="true" onclick="determineStatusCRC(this)"/>
							<bean:message key="screen.q_qcs02.standardTerm"/>
						</td>
						<td width="35%" class="nonStandardCol">
							<br>
							<html:radio name="_q_qcsForm" property="section_no_crc" value="Q062" disabled="true"  onclick="determineStatusCRC(this)"/>
							<bean:message key="screen.q_qcs02.exemption"/>
						</td>
						<td width="20%" class="concurrenceCol"><br><bean:message key="screen.q_qcs02.headofcorporatediv"/></td>
						<td width="20%" class="statusCol">						
							<div id="Q061">
								<logic:iterate name="_q_qcsForm" id="apprCRC1" property="listApprCRC1">
								<div style="border: none" class="<bean:write name="apprCRC1" property="appr_status_css"/>">
									<center><bean:write name="apprCRC1" property="appr_status"/></center>
									<br>
									<bean:message key="screen.q_qcs02.approvalName"/><bean:write name="apprCRC1" property="pic"/>
									<br>
									<bean:message key="screen.q_qcs02.approvalDate"/><bean:write name="apprCRC1" property="date_appr"/>
								</div>
								<br>								
								</logic:iterate>								
							</div>
							<div id="Q062">
								<logic:iterate name="_q_qcsForm" id="apprCRC2" property="listApprCRC2">
								<div style="border: none" class="<bean:write name="apprCRC2" property="appr_status_css"/>">
									<center><bean:write name="apprCRC2" property="appr_status"/></center>
									<br>
									<bean:message key="screen.q_qcs02.approvalName"/><bean:write name="apprCRC2" property="pic"/>
									<br>
									<bean:message key="screen.q_qcs02.approvalDate"/><bean:write name="apprCRC2" property="date_appr"/>
								</div>
								<br>								
								</logic:iterate>								
							</div>	
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<table style="border:0;width:100%;font-size:15px;">
								<tr>
									<td style="vertical-align:top;" width="5%">
											<bean:message key="screen.q_qcs02.attachment"/><bean:message key="screen.q_qcs.colon"/>
									</td>
									<td>
										<table id="attachmentCRC" style="border:0;width:100%;font-size:15px;">
											<logic:iterate name="_q_qcsForm" id="fileInfoCRC" property="attachmentCRC">
												<tr>
													<td>
														<a href="#" onclick="clickDownload('<bean:write name="fileInfoCRC" property="filename"/>') "> <bean:write name="fileInfoCRC" property="filename"/></a>	
																							
													</td>
													<td>								
													</td>
												</tr>
											</logic:iterate>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<bean:message key="screen.q_qcs02.remarkComment"/>
							<html:text name="_q_qcsForm" property="remarks_crc" styleClass="SessionRemarkTextBox" disabled="true" />
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">							
							<input type="button" class= "button"  value="<bean:message key="screen.q_qcs02.approve"/>" disabled="disabled" />
							<input type="button" class= "button"  value="<bean:message key="screen.q_qcs02.reject"/>" disabled="disabled"/>												
							<input type="button"   value="<bean:message key="screen.q_qcs02.uploadFile"/>" disabled="disabled"/>
							<input id="crc_file"  type="file" name="file_1" class="realFileSectGrp"  style="display:none"/>						
							<input type="button"  class="ObtainApprovalButton" value="<bean:message key="screen.q_qcs02.obtainApproval"/>" disabled="disabled" onclick="clickObtainApprovalSectGrp('<bean:message key="POPBEF"/>','CRC')"/>							
						</td>
					</tr>				
				</table>
			</logic:equal>
			</div>
			<!-- Session FRX -->
			<div id="divFRX">
			<logic:equal name="_q_qcsForm" property="enableFRX" value="1">
				<table cellpadding="0" cellspacing="0" class="sessionDetail">
					<tr>			
						<td rowspan="3" width="25%" class="standardCol">
							<label id="lblseqFRX"></label>
							<bean:message key="screen.q_qcs02.7Forex"/><br>
							<logic:equal name="_q_qcsForm" property="disableSectionNo" value="true">
								<html:radio name="_q_qcsForm" property="section_no_frx" value="Q071" onclick="determineStatusFRX(this)" disabled="true" />
							</logic:equal>
							<logic:equal name="_q_qcsForm" property="disableSectionNo" value="false">
								<html:radio name="_q_qcsForm" property="section_no_frx" value="Q071" onclick="determineStatusFRX(this)" disabled="false" />
							</logic:equal>
							
							<bean:message key="screen.q_qcs02.myr"/>
							<br><bean:message key="screen.q_qcs02.myr1"/>
							<br><bean:message key="screen.q_qcs02.myr2"/>
						</td>
						<td width="35%" class="nonStandardNoBorderCol">
							<logic:equal name="_q_qcsForm" property="disableSectionNo" value="true">
								<html:radio name="_q_qcsForm" property="value_frx" value="USD" disabled="true"/>
								<bean:message key="screen.q_qcs02.usd"/>
								&nbsp;&nbsp;<html:radio name="_q_qcsForm" property="value_frx" value="JPY" disabled="true" />
								<bean:message key="screen.q_qcs02.jpy"/>
								&nbsp;&nbsp;<html:radio name="_q_qcsForm" property="value_frx" value="SGD" disabled="true" />
								<bean:message key="screen.q_qcs02.sgd"/>
								&nbsp;&nbsp;<html:radio name="_q_qcsForm" property="value_frx" value="GBP" disabled="true" />
								<bean:message key="screen.q_qcs02.gbp"/>
								&nbsp;&nbsp;<html:radio name="_q_qcsForm" property="value_frx" value="EURO" disabled="true" />
								<bean:message key="screen.q_qcs02.euro"/>
							</logic:equal>
							<logic:equal name="_q_qcsForm" property="disableSectionNo" value="false">
								<html:radio name="_q_qcsForm" property="value_frx" value="USD" />
								<bean:message key="screen.q_qcs02.usd"/>
								&nbsp;&nbsp;<html:radio name="_q_qcsForm" property="value_frx" value="JPY" />
								<bean:message key="screen.q_qcs02.jpy"/>
								&nbsp;&nbsp;<html:radio name="_q_qcsForm" property="value_frx" value="SGD" />
								<bean:message key="screen.q_qcs02.sgd"/>
								&nbsp;&nbsp;<html:radio name="_q_qcsForm" property="value_frx" value="GBP" />
								<bean:message key="screen.q_qcs02.gbp"/>
								&nbsp;&nbsp;<html:radio name="_q_qcsForm" property="value_frx" value="EURO" />
								<bean:message key="screen.q_qcs02.euro"/>
							</logic:equal>
						</td>
						<td width="20%" class="concurrenceNoBorderCol"></td>
						<td width="20%" class="concurrenceNoBorderCol"></td>
					</tr>
					<tr>			
						<td class="nonStandardCol" >
							<logic:equal name="_q_qcsForm" property="disableSectionNo" value="true">
								<html:radio name="_q_qcsForm" property="section_no_frx" value="Q072" onclick="determineStatusFRX(this)" disabled="true" />
							</logic:equal>
							<logic:equal name="_q_qcsForm" property="disableSectionNo" value="false">
								<html:radio name="_q_qcsForm" property="section_no_frx" value="Q072" onclick="determineStatusFRX(this)" disabled="false" />
							</logic:equal>
							
							<bean:message key="screen.q_qcs02.nonstandardForex1"/>
						</td>
						<td class="statusConcurrenceCol"  colspan="2">
							<bean:message key="screen.q_qcs02.nonstandardForex4"/>
							<br>
							<bean:message key="screen.q_qcs02.nonstandardForex5"/>
						</td>
					</tr>
					<tr>			
						<td class="nonStandardCol" >
							<logic:equal name="_q_qcsForm" property="disableSectionNo" value="true">
								<html:radio name="_q_qcsForm" property="section_no_frx" value="Q073" onclick="determineStatusFRX(this)" disabled="true" />
							</logic:equal>
							<logic:equal name="_q_qcsForm" property="disableSectionNo" value="false">
								<html:radio name="_q_qcsForm" property="section_no_frx" value="Q073" onclick="determineStatusFRX(this)" disabled="false" />
							</logic:equal>
							
							<bean:message key="screen.q_qcs02.nonstandardForex2"/>
							<br>
							&nbsp;&nbsp;<bean:message key="screen.q_qcs02.nonstandardForex3"/>
						</td>
						<td class="concurrenceCol" >
							<br>
							<bean:message key="screen.q_qcs02.headofacdept"/>
						</td>
						<td class="statusCol">
							<div id="Q071">
								<logic:iterate name="_q_qcsForm" id="apprFRX1" property="listApprFRX1">
								<div style="border: none" class="<bean:write name="apprFRX1" property="appr_status_css"/>">
									<center><bean:write name="apprFRX1" property="appr_status"/></center>
									<br>
									<bean:message key="screen.q_qcs02.approvalName"/><bean:write name="apprFRX1" property="pic"/>
									<br>
									<bean:message key="screen.q_qcs02.approvalDate"/><bean:write name="apprFRX1" property="date_appr"/>
								</div>
								<br>								
								</logic:iterate>								
							</div>
							<div id="Q072">
								<logic:iterate name="_q_qcsForm" id="apprFRX2" property="listApprFRX2">
								<div style="border: none" class="<bean:write name="apprFRX2" property="appr_status_css"/>">
									<center><bean:write name="apprFRX2" property="appr_status"/></center>
									<br>
									<bean:message key="screen.q_qcs02.approvalName"/><bean:write name="apprFRX2" property="pic"/>
									<br>
									<bean:message key="screen.q_qcs02.approvalDate"/><bean:write name="apprFRX2" property="date_appr"/>
								</div>
								<br>								
								</logic:iterate>								
							</div>	
							<div id="Q073">
								<logic:iterate name="_q_qcsForm" id="apprFRX3" property="listApprFRX3">
								<div style="border: none" class="<bean:write name="apprFRX3" property="appr_status_css"/>">
									<center><bean:write name="apprFRX3" property="appr_status"/></center>
									<br>
									<bean:message key="screen.q_qcs02.approvalName"/><bean:write name="apprFRX3" property="pic"/>
									<br>
									<bean:message key="screen.q_qcs02.approvalDate"/><bean:write name="apprFRX3" property="date_appr"/>
								</div>
								<br>								
								</logic:iterate>								
							</div>	
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<table style="border:0;width:100%;font-size:15px;">
								<tr>
									<td style="vertical-align:top;" width="5%">
											<bean:message key="screen.q_qcs02.attachment"/><bean:message key="screen.q_qcs.colon"/>
									</td>
									<td>
										<table id="attachmentFRX" style="border:0;width:100%;font-size:15px;">
											<logic:iterate name="_q_qcsForm" id="fileInfoFRX" property="attachmentFRX">
												<tr>
													<td>
														<a href="#" onclick="clickDownload('<bean:write name="fileInfoFRX" property="filename"/>') "> <bean:write name="fileInfoFRX" property="filename"/></a>	
														<img src='../image/delete.gif' onclick="removeRow(this,'FRX','<bean:write name="fileInfoFRX" property="id_doc"/>')" />									
													</td>
													<td>								
													</td>
												</tr>
											</logic:iterate>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<bean:message key="screen.q_qcs02.remarkComment"/>
							<html:text name="_q_qcsForm" property="remarks_frx" styleClass="SessionRemarkTextBox"/>
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<logic:equal name="_q_qcsForm" property="enableRejectApprove" value="1">
								<input type="submit" class= "button"  value="<bean:message key="screen.q_qcs02.approve"/>" name="forward_section_appr" onclick="clickApprove()"/>
								<input type="submit" class= "button"  value="<bean:message key="screen.q_qcs02.reject"/>" name="forward_section_reject" onclick="clickReject('<bean:message key="POPBEF"/>')"/>
							</logic:equal>
							<logic:equal name="_q_qcsForm" property="enableRejectApprove" value="0">
								<input type="button" class= "button"  value="<bean:message key="screen.q_qcs02.approve"/>" disabled="disabled" />
								<input type="button" class= "button"  value="<bean:message key="screen.q_qcs02.reject"/>" disabled="disabled"/>
							</logic:equal>						
							<input type="button" class="fakeFileQCS" name="pseudobutton" value="<bean:message key="screen.q_qcs02.uploadFile"/>" >
							<input id="frx_file"  type="file" name="file_1" class="realFileSectGrp" />
							
							<logic:equal name="_q_qcsForm" property="enableObtainApprovalFRX" value="1">
								<input type="submit"  class="ObtainApprovalButton" value="<bean:message key="screen.q_qcs02.obtainApproval"/>"  name="forward_obt_appr_sectgrp" onclick="clickObtainApprovalSectGrp('<bean:message key="POPBEF"/>','FRX')"/>
							</logic:equal>
							<logic:equal name="_q_qcsForm" property="enableObtainApprovalFRX" value="0">
								<input type="submit"  class="ObtainApprovalButton" value="<bean:message key="screen.q_qcs02.obtainApproval"/>" disabled="disabled"  name="forward_obt_appr_sectgrp" onclick="clickObtainApprovalSectGrp('<bean:message key="POPBEF"/>','FRX')"/>
							</logic:equal>
						</td>
					</tr>				
				</table>
			</logic:equal>
			<logic:equal name="_q_qcsForm" property="enableFRX" value="0">
				<table cellpadding="0" cellspacing="0" class="sessionDetail">
					<tr>			
						<td rowspan="3" width="25%" class="standardCol">
							<label id="lblseqFRX"></label>
							<bean:message key="screen.q_qcs02.7Forex"/><br>
							<html:radio name="_q_qcsForm" property="section_no_frx" value="Q071" disabled="true" onclick="determineStatusFRX(this)"/>
							<bean:message key="screen.q_qcs02.myr"/>
							<br><bean:message key="screen.q_qcs02.myr1"/>
							<br><bean:message key="screen.q_qcs02.myr2"/>
						</td>
						<td width="35%" class="nonStandardNoBorderCol">
							<html:radio name="_q_qcsForm" property="value_frx" value="USD" disabled="true"/>
							<bean:message key="screen.q_qcs02.usd"/>
							&nbsp;&nbsp;<html:radio name="_q_qcsForm" property="value_frx" value="JPY" disabled="true"/>
							<bean:message key="screen.q_qcs02.jpy"/>
							&nbsp;&nbsp;<html:radio name="_q_qcsForm" property="value_frx" value="SGD" disabled="true"/>
							<bean:message key="screen.q_qcs02.sgd"/>
							&nbsp;&nbsp;<html:radio name="_q_qcsForm" property="value_frx" value="GBP" disabled="true"/>
							<bean:message key="screen.q_qcs02.gbp"/>
							&nbsp;&nbsp;<html:radio name="_q_qcsForm" property="value_frx" value="EURO" disabled="true"/>
							<bean:message key="screen.q_qcs02.euro"/>
						</td>
						<td width="20%" class="concurrenceNoBorderCol"></td>
						<td width="20%" class="concurrenceNoBorderCol"></td>
					</tr>
					<tr>			
						<td class="nonStandardCol" >
							<html:radio name="_q_qcsForm" property="section_no_frx" value="Q072" disabled="true" onclick="determineStatusFRX(this)"/>
							<bean:message key="screen.q_qcs02.nonstandardForex1"/>
						</td>
						<td class="statusConcurrenceCol"  colspan="2">
							<bean:message key="screen.q_qcs02.nonstandardForex4"/>
							<br>
							<bean:message key="screen.q_qcs02.nonstandardForex5"/>
						</td>
					</tr>
					<tr>			
						<td class="nonStandardCol" >
							<html:radio name="_q_qcsForm" property="section_no_frx" value="Q073" disabled="true" onclick="determineStatusFRX(this)"/>
							<bean:message key="screen.q_qcs02.nonstandardForex2"/>
							<br>
							&nbsp;&nbsp;<bean:message key="screen.q_qcs02.nonstandardForex3"/>
						</td>
						<td class="concurrenceCol" >
							<br>
							<bean:message key="screen.q_qcs02.headofacdept"/>
						</td>
						<td class="statusCol">
							<div id="Q071">
								<logic:iterate name="_q_qcsForm" id="apprFRX1" property="listApprFRX1">
								<div style="border: none" class="<bean:write name="apprFRX1" property="appr_status_css"/>">
									<center><bean:write name="apprFRX1" property="appr_status"/></center>
									<br>
									<bean:message key="screen.q_qcs02.approvalName"/><bean:write name="apprFRX1" property="pic"/>
									<br>
									<bean:message key="screen.q_qcs02.approvalDate"/><bean:write name="apprFRX1" property="date_appr"/>
								</div>
								<br>								
								</logic:iterate>								
							</div>
							<div id="Q072">
								<logic:iterate name="_q_qcsForm" id="apprFRX2" property="listApprFRX2">
								<div style="border: none"  class="<bean:write name="apprFRX2" property="appr_status_css"/>">
									<center><bean:write name="apprFRX2" property="appr_status"/></center>
									<br>
									<bean:message key="screen.q_qcs02.approvalName"/><bean:write name="apprFRX2" property="pic"/>
									<br>
									<bean:message key="screen.q_qcs02.approvalDate"/><bean:write name="apprFRX2" property="date_appr"/>
								</div>
								<br>								
								</logic:iterate>								
							</div>	
							<div id="Q073">
								<logic:iterate name="_q_qcsForm" id="apprFRX3" property="listApprFRX3">
								<div style="border: none"  class="<bean:write name="apprFRX3" property="appr_status_css"/>">
									<center><bean:write name="apprFRX3" property="appr_status"/></center>
									<br>
									<bean:message key="screen.q_qcs02.approvalName"/><bean:write name="apprFRX3" property="pic"/>
									<br>
									<bean:message key="screen.q_qcs02.approvalDate"/><bean:write name="apprFRX3" property="date_appr"/>
								</div>
								<br>								
								</logic:iterate>								
							</div>	
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<table style="border:0;width:100%;font-size:15px;">
								<tr>
									<td style="vertical-align:top;" width="5%">
											<bean:message key="screen.q_qcs02.attachment"/><bean:message key="screen.q_qcs.colon"/>
									</td>
									<td>
										<table id="attachmentFRX" style="border:0;width:100%;font-size:15px;">
											<logic:iterate name="_q_qcsForm" id="fileInfoFRX" property="attachmentFRX">
												<tr>
													<td>
														<a href="#" onclick="clickDownload('<bean:write name="fileInfoFRX" property="filename"/>') "> <bean:write name="fileInfoFRX" property="filename"/></a>	
																							
													</td>
													<td>								
													</td>
												</tr>
											</logic:iterate>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<bean:message key="screen.q_qcs02.remarkComment"/>
							<html:text name="_q_qcsForm" property="remarks_frx" styleClass="SessionRemarkTextBox" disabled="true"/>
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">							
							<input type="button" class= "button"  value="<bean:message key="screen.q_qcs02.approve"/>" disabled="disabled" />
							<input type="button" class= "button"  value="<bean:message key="screen.q_qcs02.reject"/>" disabled="disabled"/>						
							<input type="button"   value="<bean:message key="screen.q_qcs02.uploadFile"/>" disabled="disabled"/>
							<input id="frx_file"  type="file" name="file_1" class="realFileSectGrp"  style="display:none"/>					
							<input type="button"  class="ObtainApprovalButton" value="<bean:message key="screen.q_qcs02.obtainApproval"/>" disabled="disabled" onclick="clickObtainApprovalSectGrp('<bean:message key="POPBEF"/>','FRX')"/>							
						</td>
					</tr>				
				</table>
			</logic:equal>
			</div>
			<!-- Session COV -->
			<div id="divCOV">
			<logic:equal name="_q_qcsForm" property="enableCOV" value="1">
				<table cellpadding="0" cellspacing="0" class="sessionDetail">
					<tr>			
						<td  rowspan="2" width="25%" class="standardCol">
							<label id="lblseqCOV"></label>
							<bean:message key="screen.q_qcs02.8contractvalue"/><br>
							<logic:equal name="_q_qcsForm" property="disableSectionNo" value="true">
								<html:radio name="_q_qcsForm" property="section_no_cov" value="Q081" onclick="determineStatusCOV(this)" disabled="true" />
							</logic:equal>
							<logic:equal name="_q_qcsForm" property="disableSectionNo" value="false">
								<html:radio name="_q_qcsForm" property="section_no_cov" value="Q081" onclick="determineStatusCOV(this)" disabled="false" />
							</logic:equal>
							
							<bean:message key="screen.q_qcs02.standardContractValue"/>
						</td>
						<td width="35%" class="nonStandardCol" style="vertical-align:middle;">
							<logic:equal name="_q_qcsForm" property="disableSectionNo" value="true">
								<html:radio name="_q_qcsForm" property="section_no_cov" value="Q082" onclick="determineStatusCOV(this)" disabled="true" />
							</logic:equal>
							<logic:equal name="_q_qcsForm" property="disableSectionNo" value="false">
								<html:radio name="_q_qcsForm" property="section_no_cov" value="Q082" onclick="determineStatusCOV(this)" disabled="false" />
							</logic:equal>
							
							<bean:message key="screen.q_qcs02.nonstandardContractValue1"/>
						</td>
						<td width="20%" class="concurrenceCol" style="vertical-align:middle;"><bean:message key="screen.q_qcs02.coo"/></td>
						<td rowspan="2" width="20%" class="statusCol">
							<div id="Q081">
								<logic:iterate name="_q_qcsForm" id="apprCOV1" property="listApprCOV1">
								<div style="border: none" class="<bean:write name="apprCOV1" property="appr_status_css"/>">
									<center><bean:write name="apprCOV1" property="appr_status"/></center>
									<br>
									<bean:message key="screen.q_qcs02.approvalName"/><bean:write name="apprCOV1" property="pic"/>
									<br>
									<bean:message key="screen.q_qcs02.approvalDate"/><bean:write name="apprCOV1" property="date_appr"/>
								</div>
								<br>								
								</logic:iterate>								
							</div>
							<div id="Q082">
								<logic:iterate name="_q_qcsForm" id="apprCOV2" property="listApprCOV2">
								<div style="border: none" class="<bean:write name="apprCOV2" property="appr_status_css"/>">
									<center><bean:write name="apprCOV2" property="appr_status"/></center>
									<br>
									<bean:message key="screen.q_qcs02.approvalName"/><bean:write name="apprCOV2" property="pic"/>
									<br>
									<bean:message key="screen.q_qcs02.approvalDate"/><bean:write name="apprCOV2" property="date_appr"/>
								</div>
								<br>								
								</logic:iterate>								
							</div>	
							<div id="Q083">
								<logic:iterate name="_q_qcsForm" id="apprCOV3" property="listApprCOV3">
								<div style="border: none" class="<bean:write name="apprCOV3" property="appr_status_css"/>">
									<center><bean:write name="apprCOV3" property="appr_status"/></center>
									<br>
									<bean:message key="screen.q_qcs02.approvalName"/><bean:write name="apprCOV3" property="pic"/>
									<br>
									<bean:message key="screen.q_qcs02.approvalDate"/><bean:write name="apprCOV3" property="date_appr"/>
								</div>
								<br>								
								</logic:iterate>								
							</div>	
						</td>
					</tr>
					<tr>			
						<td width="35%" class="nonStandardCol" style="vertical-align:middle;">
							<logic:equal name="_q_qcsForm" property="disableSectionNo" value="true">
								<html:radio name="_q_qcsForm" property="section_no_cov" value="Q083" onclick="determineStatusCOV(this)" disabled="true" />
							</logic:equal>
							<logic:equal name="_q_qcsForm" property="disableSectionNo" value="false">
								<html:radio name="_q_qcsForm" property="section_no_cov" value="Q083" onclick="determineStatusCOV(this)" disabled="false" />
							</logic:equal>
							
							<bean:message key="screen.q_qcs02.nonstandardContractValue2"/>
						</td>
						<td width="20%" class="concurrenceCol" style="vertical-align:middle;"><bean:message key="screen.q_qcs02.ceo"/></td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<table style="border:0;width:100%;font-size:15px;">
								<tr>
									<td style="vertical-align:top;" width="5%">
											<bean:message key="screen.q_qcs02.attachment"/><bean:message key="screen.q_qcs.colon"/>
									</td>
									<td>
										<table id="attachmentCOV" style="border:0;width:100%;font-size:15px;">
											<logic:iterate name="_q_qcsForm" id="fileInfoCOV" property="attachmentCOV">
												<tr>
													<td>
														<a href="#" onclick="clickDownload('<bean:write name="fileInfoCOV" property="filename"/>') "> <bean:write name="fileInfoCOV" property="filename"/></a>	
														<img src='../image/delete.gif' onclick="removeRow(this,'COV','<bean:write name="fileInfoCOV" property="id_doc"/>')" />									
													</td>
													<td>								
													</td>
												</tr>
											</logic:iterate>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<bean:message key="screen.q_qcs02.remarkComment"/>
							<html:text name="_q_qcsForm" property="remarks_cov" styleClass="SessionRemarkTextBox"/>
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<logic:equal name="_q_qcsForm" property="enableRejectApprove" value="1">
								<input type="submit" class= "button"  value="<bean:message key="screen.q_qcs02.approve"/>" name="forward_section_appr" onclick="clickApprove()"/>
								<input type="submit" class= "button"  value="<bean:message key="screen.q_qcs02.reject"/>" name="forward_section_reject" onclick="clickReject('<bean:message key="POPBEF"/>')"/>
							</logic:equal>
							<logic:equal name="_q_qcsForm" property="enableRejectApprove" value="0">
								<input type="button" class= "button"  value="<bean:message key="screen.q_qcs02.approve"/>" disabled="disabled" />
								<input type="button" class= "button"  value="<bean:message key="screen.q_qcs02.reject"/>" disabled="disabled"/>
							</logic:equal>						
							<input type="button" class="fakeFileQCS" name="pseudobutton" value="<bean:message key="screen.q_qcs02.uploadFile"/>" >
							<input id="cov_file"  type="file" name="file_1" class="realFileSectGrp" />
							
							<logic:equal name="_q_qcsForm" property="enableObtainApprovalCOV" value="1">
								<input type="submit"  class="ObtainApprovalButton" value="<bean:message key="screen.q_qcs02.obtainApproval"/>"  name="forward_obt_appr_sectgrp" onclick="clickObtainApprovalSectGrp('<bean:message key="POPBEF"/>','COV')"/>
							</logic:equal>
							<logic:equal name="_q_qcsForm" property="enableObtainApprovalCOV" value="0">
								<input type="submit"  class="ObtainApprovalButton" value="<bean:message key="screen.q_qcs02.obtainApproval"/>" disabled="disabled"  name="forward_obt_appr_sectgrp" onclick="clickObtainApprovalSectGrp('<bean:message key="POPBEF"/>','COV')"/>
							</logic:equal>
						</td>
					</tr>				
				</table>
			</logic:equal>
			<logic:equal name="_q_qcsForm" property="enableCOV" value="0">
				<table cellpadding="0" cellspacing="0" class="sessionDetail">
					<tr>			
						<td  rowspan="2" width="25%" class="standardCol">
							<label id="lblseqCOV"></label>
							<bean:message key="screen.q_qcs02.8contractvalue"/><br>
							&nbsp;&nbsp;<html:radio name="_q_qcsForm" property="section_no_cov" value="Q081" disabled="true" onclick="determineStatusCOV(this)"/>
							<bean:message key="screen.q_qcs02.standardContractValue"/>
						</td>
						<td width="35%" class="nonStandardCol" style="vertical-align:middle;">
							<html:radio name="_q_qcsForm" property="section_no_cov" value="Q082" disabled="true" onclick="determineStatusCOV(this)"/>
							<bean:message key="screen.q_qcs02.nonstandardContractValue1"/>
						</td>
						<td width="20%" class="concurrenceCol" style="vertical-align:middle;"><bean:message key="screen.q_qcs02.coo"/></td>
						<td rowspan="2" width="20%" class="statusCol">
							<div id="Q081">
								<logic:iterate name="_q_qcsForm" id="apprCOV1" property="listApprCOV1">
								<div style="border: none" class="<bean:write name="apprCOV1" property="appr_status_css"/>">
									<center><bean:write name="apprCOV1" property="appr_status"/></center>
									<br>
									<bean:message key="screen.q_qcs02.approvalName"/><bean:write name="apprCOV1" property="pic"/>
									<br>
									<bean:message key="screen.q_qcs02.approvalDate"/><bean:write name="apprCOV1" property="date_appr"/>
								</div>
								<br>								
								</logic:iterate>								
							</div>
							<div id="Q082">
								<logic:iterate name="_q_qcsForm" id="apprCOV2" property="listApprCOV2">
								<div style="border: none" class="<bean:write name="apprCOV2" property="appr_status_css"/>">
									<center><bean:write name="apprCOV2" property="appr_status"/></center>
									<br>
									<bean:message key="screen.q_qcs02.approvalName"/><bean:write name="apprCOV2" property="pic"/>
									<br>
									<bean:message key="screen.q_qcs02.approvalDate"/><bean:write name="apprCOV2" property="date_appr"/>
								</div>
								<br>								
								</logic:iterate>								
							</div>	
							<div id="Q083">
								<logic:iterate name="_q_qcsForm" id="apprCOV3" property="listApprCOV3">
								<div style="border: none" class="<bean:write name="apprCOV3" property="appr_status_css"/>">
									<center><bean:write name="apprCOV3" property="appr_status"/></center>
									<br>
									<bean:message key="screen.q_qcs02.approvalName"/><bean:write name="apprCOV3" property="pic"/>
									<br>
									<bean:message key="screen.q_qcs02.approvalDate"/><bean:write name="apprCOV3" property="date_appr"/>
								</div>
								<br>								
								</logic:iterate>								
							</div>	
						</td>
					</tr>
					<tr>			
						<td width="35%" class="nonStandardCol" style="vertical-align:middle;">
							<html:radio name="_q_qcsForm" property="section_no_cov" value="Q083" disabled="true" onclick="determineStatusCOV(this)"/>
							<bean:message key="screen.q_qcs02.nonstandardContractValue2"/>
						</td>
						<td width="20%" class="concurrenceCol" style="vertical-align:middle;"><bean:message key="screen.q_qcs02.ceo"/></td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<table style="border:0;width:100%;font-size:15px;">
								<tr>
									<td style="vertical-align:top;" width="5%">
											<bean:message key="screen.q_qcs02.attachment"/><bean:message key="screen.q_qcs.colon"/>
									</td>
									<td>
										<table id="attachmentCOV" style="border:0;width:100%;font-size:15px;">
											<logic:iterate name="_q_qcsForm" id="fileInfoCOV" property="attachmentCOV">
												<tr>
													<td>
														<a href="#" onclick="clickDownload('<bean:write name="fileInfoCOV" property="filename"/>') "> <bean:write name="fileInfoCOV" property="filename"/></a>	
																							
													</td>
													<td>								
													</td>
												</tr>
											</logic:iterate>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">
							<bean:message key="screen.q_qcs02.remarkComment"/>
							<html:text name="_q_qcsForm" property="remarks_cov" styleClass="SessionRemarkTextBox" disabled="true"/>
						</td>
					</tr>
					<tr>			
						<td class="otherCols" colspan="4">							
							<input type="button" class= "button"  value="<bean:message key="screen.q_qcs02.approve"/>" disabled="disabled" />
							<input type="button" class= "button"  value="<bean:message key="screen.q_qcs02.reject"/>" disabled="disabled"/>						
							<input type="button"   value="<bean:message key="screen.q_qcs02.uploadFile"/>" disabled="disabled"/>
							<input id="cov_file"  type="file" name="file_1" class="realFileSectGrp"  style="display:none"/>						
							<input type="button"  class="ObtainApprovalButton" value="<bean:message key="screen.q_qcs02.obtainApproval"/>" disabled="disabled" onclick="clickObtainApprovalSectGrp('<bean:message key="POPBEF"/>','COV')"/>							
						</td>
					</tr>				
				</table>
			</logic:equal>
			</div>
			<table class="buttonGroup" cellpadding="0" cellspacing="0">
			  	<tr>
					<td>
						<logic:equal name="_q_qcsForm" property="enableSave" value="1">
							<input type="submit" class="button" value="<bean:message key="screen.q_qcs02.save"/>" name="forward_save"  onclick="clickSave()" />
						</logic:equal>
						<logic:equal name="_q_qcsForm" property="enableSave" value="0">
							<input type="submit" class="button" value="<bean:message key="screen.q_qcs02.save"/>" name="forward_save"  onclick="clickSave()" disabled="disabled" />
						</logic:equal>
						<logic:equal name="_q_qcsForm" property="enableEdit" value="1">
							<input type="submit" class= "button"  value="<bean:message key="screen.q_qcs02.edit"/>" name="forward_edit" onclick="clickEdit()"/>
						</logic:equal>
						<logic:equal name="_q_qcsForm" property="enableEdit" value="0">
							<input type="submit" class= "button"  value="<bean:message key="screen.q_qcs02.edit"/>" name="forward_edit" disabled="disabled" onclick="clickEdit()"/>
						</logic:equal>
						<logic:equal name="_q_qcsForm" property="enableDelete" value="1">
							<input type="submit" class="button" value="<bean:message key="screen.q_qcs02.delete"/>" name="forward_delete" onclick="return clickDelete('<bean:message key="POPDEL"/>')"/>
						</logic:equal>					
						<logic:equal name="_q_qcsForm" property="enableDelete" value="0">
							<input type="submit" class="button" value="<bean:message key="screen.q_qcs02.delete"/>" name="forward_delete" onclick="return clickDelete('<bean:message key="POPDEL"/>')" disabled="disabled"/>
						</logic:equal>	
						<input type="submit" class="button" value="<bean:message key="screen.q_qcs02.exit"/>" name="forward_exit" />
					</td>	
				</tr>
			</table>
			<div class="error">
	    		<ts:messages id="messages" message="true">
	    			<bean:write name="messages"/>
	    		</ts:messages>
		    	<ts:ifErrors>
		    		<ts:errors />
		    	</ts:ifErrors>
    		</div> 
			<input type="hidden" name="deletedAttachmentQCS"/>
			<input type="hidden" name="deletedAttachmentPMR"/>
			<input type="hidden" name="deletedAttachmentBZR"/>
			<input type="hidden" name="deletedAttachmentCTC"/>
			<input type="hidden" name="deletedAttachmentPRI"/>
			<input type="hidden" name="deletedAttachmentMRG"/>
			<input type="hidden" name="deletedAttachmentCRC"/>
			<input type="hidden" name="deletedAttachmentFRX"/>
			<input type="hidden" name="deletedAttachmentCOV"/>
		
			<html:hidden name="_q_qcsForm" property="file_name"/>
			<html:hidden name="_q_qcsForm" property="obt_appr_save"/>
			<html:hidden name="_q_qcsForm" property="clickEvent"/>
			<html:hidden name="_q_qcsForm" property="showPopalt"/>
			<html:hidden name="_q_qcsForm" property="section_group"/>
			<html:hidden name="_q_qcsForm" property="section_no"/>
			<html:hidden name="_q_qcsForm" property="id_wf_approval"/>
			<input type="submit" class="button" name="forward_download" value="downloadFile" style="display: none"/>
			<select name="seqGroup" id="seqGroup" style="display:none">
				<logic:iterate name="_q_qcsForm" id="sequenceGroup" property="sequenceGroups">
					<option value="<bean:write name="sequenceGroup" property="sequence_no"/>">
						<bean:write name="sequenceGroup" property="section_group"/>	
					</option>
				</logic:iterate>
			</select>		
		</ts:form>
		<script>	
				if(document.getElementById('qcs_file')!= null){
					var multi_selector = new MultiSelector( 'attachmentQCS', 'realFileQCS','listFileQCS', 100 );	
					multi_selector.addElement( document.getElementById( 'qcs_file' ) );
				}
				if(document.getElementById('pmr_file')!= null){
					var pmr_multi_selector = new MultiSelector( 'attachmentPMR', 'realFileSectGrp','listFilePMR', 100 );	
					pmr_multi_selector.addElement( document.getElementById( 'pmr_file' ) );
				}
				if(document.getElementById('bzr_file')!= null){
					var bzr_multi_selector = new MultiSelector( 'attachmentBZR', 'realFileSectGrp','listFileBZR', 100 );	
					bzr_multi_selector.addElement( document.getElementById( 'bzr_file' ) );
				}
				if(document.getElementById('ctc_file')!= null){
					var ctc_multi_selector = new MultiSelector( 'attachmentCTC', 'realFileSectGrp','listFileCTC', 100 );	
					ctc_multi_selector.addElement( document.getElementById( 'ctc_file' ) );
				}
				if(document.getElementById('pri_file')!= null){
					var pri_multi_selector = new MultiSelector( 'attachmentPRI', 'realFileSectGrp','listFilePRI', 100 );	
					pri_multi_selector.addElement( document.getElementById( 'pri_file' ) );
				}
				if(document.getElementById('mrg_file')!= null){
					var mrg_multi_selector = new MultiSelector( 'attachmentMRG', 'realFileSectGrp','listFileMRG', 100 );	
					mrg_multi_selector.addElement( document.getElementById( 'mrg_file' ) );
				}
				if(document.getElementById('crc_file')!= null){
					var crc_multi_selector = new MultiSelector( 'attachmentCRC', 'realFileSectGrp','listFileCRC', 100 );	
					crc_multi_selector.addElement( document.getElementById( 'crc_file' ) );
				}
				if(document.getElementById('frx_file')!= null){
					var frx_multi_selector = new MultiSelector( 'attachmentFRX', 'realFileSectGrp','listFileFRX', 100 );	
					frx_multi_selector.addElement( document.getElementById( 'frx_file' ) );
				}
				if(document.getElementById('cov_file')!= null){
					var cov_multi_selector = new MultiSelector( 'attachmentCOV', 'realFileSectGrp','listFileCOV', 100 );	
					cov_multi_selector.addElement( document.getElementById( 'cov_file' ) );
				}
		</script>
	</ts:body>
</html:html>

