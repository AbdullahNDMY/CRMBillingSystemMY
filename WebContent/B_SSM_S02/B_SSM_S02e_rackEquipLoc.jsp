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
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">		
</head>

<body>
	<t:defineCodeList id="LIST_EQUIP_MAXPOWER"/>
	<t:defineCodeList id="LIST_EQUIP_LOCATION"/>
	<t:defineCodeList id="LIST_EQUIP_SUITE"/>
	<bean:define id="infoIDArray" name="B_SSM_S02_Page_Form" property="infoIDArray" type="java.util.List<Integer>"/>
	<bean:define id="accessAccountFlag" name="B_SSM_S02_Page_Form" property="accessAccountFlag" type="java.lang.Integer"/>
	<bean:define id="accessPasswordFlag" name="B_SSM_S02_Page_Form" property="accessPasswordFlag" type="java.lang.Integer"/>
	<div id="rackEquipmentLocationTab" 
		 style='<%=(B_SSM_S02e_BUtils.isRackEquipmentLocationTabEnable(infoIDArray))? "display:block;":"display:none;"%>' 
		 class="B_SSM_S02_Page_Form_Tabs_TabPanel">
		<!-- Rack Equipment Group -->
		<div class="B_SSM_S02_Page_Form_Container_FieldSet">
			<div style="overflow: hidden;">											
				<TABLE id="rackEquipmentInfoTableID" class="B_SSM_S02_Page_Form_Table_Detail">
					<!-- Rack Equipment Headers -->
					<TR class="B_SSM_S02_Page_Form_Table_Row_Header">
						<!-- Add Link -->
						<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
							<html:link href="javascript:void(0);"
									   onclick="insRowPos = getTableRowCount('rackEquipmentInfoTableID');												   			
									   			addComponentsToTable('rackEquipmentInfoTableID',
																      insRowPos,						    
																      getRackEquipmentTableComponentPositionArray(),
																      getNewRackEquipmentComponentValueArray());															
												updateRackEquipmentRemoveLinks();">
								<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.MailAccounts.AddLink"/>
							</html:link>
						</TH>	
						<!-- Location -->
						<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
							<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.RackEquipment.EquipmentLocation"/>
						</TH>
						<!-- Suite/Cage -->
						<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
							<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.RackEquipment.EquipmentSuite"/>
						</TH>
						<!-- Rack No. -->
						<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
							<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.RackEquipment.RackNo"/>
						</TH>
						<!-- Power Committed (kW) -->
						<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
							<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.RackEquipment.MaxPower"/>
						</TH>
					</TR>
					<!-- Rack Equipment Content -->
					<!-- khaln: Rack Power details -->
					<logic:iterate id="rackPowerDetail" name="B_SSM_S02_Page_Form" property="rackPowerDetailList" indexId="index">
						<TR style="text-align: left;">
							<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
								<a href="javascript:doRackEquipmentRowRemove('${index+1}')"
       						       class="B_SSM_S02_Page_Form_AccountMailsGroup_Href_RemoveLink">	        						
       						        <span style="font-weight: bold; font-size: 15px; color: black;">X</span>
       						    </a>	        						    
							</TD>
							<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
								<html:select name="rackPowerDetail" property="equipmentLocation" styleClass="B_SSM_S02_Page_Form_TextBox_EquipmentLocation">
									<html:option value=""><bean:message key="B_SSM_S02_Page.BlankSelectText"/></html:option>
									<html:options collection="LIST_EQUIP_LOCATION" property="id" labelProperty="name"/>
								</html:select>
							</TD>
							<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
								<html:text name="rackPowerDetail" property="equipmentSuite" maxlength="50" 
									styleClass="B_SSM_S02_Page_Form_TextBox_EquipmentSuite"/>
							</TD>
							<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
								<html:text name="rackPowerDetail" property="rackNo" maxlength="50" 
									styleClass="B_SSM_S02_Page_Form_TextBox_RackNo"/>
								<html:hidden name="rackPowerDetail" property="rackDetailID"/>
							</TD>
							<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
								<html:text name="rackPowerDetail" property="maxPower" maxlength="50" onchange="changeOnlyDecNumbers(this, 0)" onkeypress="return onlyDecNumbers(event)"
									styleClass="B_SSM_S02_Page_Form_TextBox_MaxPower"/>
							</TD>
						</TR>
					</logic:iterate>
				</TABLE>
				<!-- bag of removed rack detail ids -->
				<div id="rackDetailRemoveDiv" style="display: none">
				</div>
			</div>	
		</div>
		
		<!-- Memo remarks -->
		<logic:equal name="B_SSM_S02_Page_Form" property="displayMemoFlg" value="1">
			<div class="B_SSM_S02_Page_Form_Container">
				<TABLE class="B_SSM_S02_Page_Form_Table">
					<TR>
						<TD class="B_SSM_S02_Page_Form_Table_InfoKeyCol_UpVAlign" style="width:180px;">
							<bean:message key="B_SSM_S02_Page.TabPanel.MemoRemarks.Text"/>									
						</TD>							
						<TD style="vertical-align: bottom;">								
							<div style="float: left; 
										text-align: center; 
										vertical-align: middle;">
								&nbsp;&nbsp;&nbsp;&nbsp;:
							</div>
							<div style="float: left; ">
								&nbsp;
								<html:textarea name="B_SSM_S02_Page_Form"
											   styleId="rackEquipmentLocationMemoRemarksID"
											   property="rackEquipmentLocationMemoRemarks"
									      	   rows="5" cols="60"/>
								<html:hidden name="B_SSM_S02_Page_Form" property="rackHeadID"/>
							</div>
						</TD>
					</TR>
				</TABLE>
			</div>
		</logic:equal>
		<logic:notEqual name="B_SSM_S02_Page_Form" property="displayMemoFlg" value="1">
			<html:textarea name="B_SSM_S02_Page_Form"
						   styleId="rackEquipmentLocationMemoRemarksID"
						   property="rackEquipmentLocationMemoRemarks"
				      	   rows="5" cols="60" style="display:none;"/>
			<html:hidden name="B_SSM_S02_Page_Form" property="rackHeadID"/>
		</logic:notEqual>
	</div>
</body>
</html>