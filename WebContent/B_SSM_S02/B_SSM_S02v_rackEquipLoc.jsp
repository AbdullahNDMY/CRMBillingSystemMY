<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
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
	<bean:define id="infoIDArray" name="B_SSM_S02_Page_Form" property="infoIDArray" type="java.util.List<Integer>"/>
	<% if (B_SSM_S02e_BUtils.isRackEquipmentLocationTabEnable(infoIDArray)) { %>
		<div id="rackEquipmentLocationTab" class="B_SSM_S02_Page_Form_Tabs_TabPanel">
			<!-- Rack Equipment Group -->
			<div class="B_SSM_S02_Page_Form_Container_FieldSet">
				<div style="overflow: hidden;">											
					<TABLE id="rackEquipmentInfoTableID" class="B_SSM_S02_Page_Form_Table_Detail">
						<!-- Rack Equipment Headers -->
						<TR class="B_SSM_S02_Page_Form_Table_Row_Header">	
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
						<logic:iterate id="rackPowerDetail" name="B_SSM_S02_Page_Form" property="rackPowerDetailList" >
							<TR class="B_SSM_S02_Page_Form_Table_Row_Data">
								<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
									<logic:notEmpty name="rackPowerDetail" property="equipmentLocation">
									<t:writeCodeValue codeList="LIST_EQUIP_LOCATION" name="rackPowerDetail" property="equipmentLocation"/>
									</logic:notEmpty>
								</TD>
								<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
									<bean:write name="rackPowerDetail" property="equipmentSuite"/>
								</TD>
								<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
									<bean:write name="rackPowerDetail" property="rackNo"/>
								</TD>
								<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
									<bean:write name="rackPowerDetail" property="maxPower"/>										
								</TD>
							</TR>
						</logic:iterate>
					</TABLE>						
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
									<textarea readonly="readonly" rows="5" cols="60">
										<bean:write name="B_SSM_S02_Page_Form" 												   
													property="rackEquipmentLocationMemoRemarks"/>
									</textarea>
								</div>
							</TD>
						</TR>
					</TABLE>
				</div>
			</logic:equal>	
		</div>
		<%}%>
</body>
</html>