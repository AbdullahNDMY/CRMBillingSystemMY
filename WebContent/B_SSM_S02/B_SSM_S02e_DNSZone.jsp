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
	<bean:define id="infoIDArray" name="B_SSM_S02_Page_Form" property="infoIDArray" type="java.util.List<Integer>"/>
	<bean:define id="accessAccountFlag" name="B_SSM_S02_Page_Form" property="accessAccountFlag" type="java.lang.Integer"/>
	<bean:define id="accessPasswordFlag" name="B_SSM_S02_Page_Form" property="accessPasswordFlag" type="java.lang.Integer"/>
	<div id="dNSZoneTab" 
		 style='<%=(B_SSM_S02e_BUtils.isDNSZoneTabEnable(infoIDArray))? "display:block;":"display:none;"%>'
		 class="B_SSM_S02_Page_Form_Tabs_TabPanel">
		<!-- DNS Zone Record Group -->
		<div class="B_SSM_S02_Page_Form_Container_FieldSet">
			<fieldset class="B_SSM_S02_Page_Form_FieldSet">
				<legend class="B_SSM_S02_Page_Form_FieldSet_Legend">
					<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.DNSZoneRecord.Legend" />			  						
				</legend>
				<div class="B_SSM_S02_Page_Form_FieldSet_PanelContent">
					<TABLE class="B_SSM_S02_Page_Form_Table">
						<TR>
							<TD class="B_SSM_S02_Page_Form_Table_Col">
								<TABLE width="100%">
									<col width="22%">
									<col width="1%">
									<col width="77%">
									<!-- Migration Current Name Server -->
									<TR>
										<TD>
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.DNSZoneRecord.MigrationCurrentNameServer.Text"/>
										</TD>
										<TD>
											:&nbsp;
										</TD>
										<TD>
											<html:text name="B_SSM_S02_Page_Form" 
													   styleClass="B_SSM_S02_Page_Form_TextBox" 
													   maxlength="100" 
													   styleId="dNSZoneMigrationCurrentNameServerID"
													   property="dNSZoneMigrationCurrentNameServer"/>
										</TD>
									</TR>
									<TR>
										<TD>
											&nbsp;
										</TD>
										<TD>
											&nbsp;&nbsp;
										</TD>
										<TD>
											<html:text name="B_SSM_S02_Page_Form" 
													   styleClass="B_SSM_S02_Page_Form_TextBox" 
													   maxlength="100" 
													   styleId="dNSZoneMigrationCurrentNameServerID2"
													   property="dNSZoneMigrationCurrentNameServer2"/>
										</TD>
									</TR>
									<TR>
										<TD>
											&nbsp;
										</TD>
										<TD>
											&nbsp;&nbsp;
										</TD>
										<TD>
											<html:text name="B_SSM_S02_Page_Form" 
													   styleClass="B_SSM_S02_Page_Form_TextBox" 
													   maxlength="100" 
													   styleId="dNSZoneMigrationCurrentNameServerID3"
													   property="dNSZoneMigrationCurrentNameServer3"/>
										</TD>
									</TR>
									<!-- Migration Current Register -->
									<TR>
										<TD>
											<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.DNSZoneRecord.MigrationCurrentRegister.Text"/>
										</TD>
										<TD>
											:&nbsp;
										</TD>
										<TD>
											<html:text name="B_SSM_S02_Page_Form" 
													   styleClass="B_SSM_S02_Page_Form_TextBox"  
													   maxlength="100" 
													   styleId="dNSZoneMigrationCurrentRegisterID"
													   property="dNSZoneMigrationCurrentRegister"/>
										</TD>
									</TR>											
								</TABLE>
							</TD>																		
						</TR>
						
					</TABLE>
					
					<!-- Domain table -->								
					<div style="overflow: hidden;">	
						<TABLE id="dNSZoneTableID" class="B_SSM_S02_Page_Form_Table_Detail">
							<!-- Firewall Policy Headers -->
							<TR>
								<!-- Add Link -->
								<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
									<html:link href="javascript:void(0);"
											   onclick="insRowPos = getTableRowCount('dNSZoneTableID');																   													   			
											   			addComponentsToTable('dNSZoneTableID',
																				insRowPos,						    
																				getDNSZoneTableComponentPositionArray(),
																				getNewDNSZoneComponentValueArray());															
														updateDNSZoneRemoveLinks();">
										<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.MailAccounts.AddLink"/>
									</html:link>
								</TH>	
								<!-- Domain Name -->
								<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
									<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.DNSZoneRecord.DomainName.Text"/>
								</TH>
								<!-- Type (MX/A/Name) -->
								<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
									<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.DNSZoneRecord.Type_MX_A_Name.Text"/>
								</TH>
								<!-- IP Address -->
								<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
									<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.DNSZoneRecord.IPAddress.Text"/>
								</TH>
								<!-- Weight -->
								<TH class="B_SSM_S02_Page_Form_Table_Col_Header_UpVAlign">
									<bean:message key="B_SSM_S02_Page.TabPanel.FieldSet.DNSZoneRecord.Weight.Text"/>
								</TH>	
						
							</TR>	
							<!-- DNS Zone Content -->
							<logic:notEmpty name="B_SSM_S02_Page_Form" property="dNSZoneRecordList">
								<logic:iterate id="dNSZoneRecord" 
											   name="B_SSM_S02_Page_Form" 
											   property="dNSZoneRecordList"
											   indexId="index">
									<TR style="text-align: left;">
										<!-- X Button -->	
										<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">
											<a href="javascript:doDNSZoneRowRemove('${index + 1}')" 
											   class="B_SSM_S02_Page_Form_AccountMailsGroup_Href_RemoveLink">         						
      					     						<span style="font-weight: bold; font-size: 15px; color: black;">X</span> 
      					     					</a> 		
										</TD>									
										<!-- Domain Name -->
										<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">												
											<input type="text" 
												   name="dNSZoneRecordDomainNames" 
												   maxlength="100"
												   class="B_SSM_S02_Page_Form_TextBox_MailAccount"
												   value="${dNSZoneRecord['dNSZoneRecordDomainName']}"/>	
  	   											<input type="text" 
  	   												   name="dNSZoneRecordIDs" 
  	   												   style="display: none;" 
  	   												   class="B_SSM_S02_Page_Form_TextBox_MailAccount"
												   value="${dNSZoneRecord['dNSZoneRecordID']}"/>	
										</TD>
										<!-- Type (MX/A/Name) -->
										<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">													
											<input type="text" 
												   name="dNSZoneRecordTypes" 
												   maxlength="100"
												   class="B_SSM_S02_Page_Form_TextBox_MailAccount"
												   value="${dNSZoneRecord['dNSZoneRecordType']}"/>
										</TD>
										<!-- IP Address -->
										<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">													
											<input type="text" 
												   name="dNSZoneRecordIPAddresses" 
												   maxlength="30"
												   class="B_SSM_S02_Page_Form_TextBox_MailAccount"
												   value="${dNSZoneRecord['dNSZoneRecordIPAddress']}"/>
										</TD>
										<!-- Weight -->
										<TD class="B_SSM_S02_Page_Form_Table_Col_Detail">												
											<input type="text" 
												   name="dNSZoneRecordWeights" 
												   maxlength="10"
												   class="B_SSM_S02_Page_Form_TextBox_MailAccount"
												   value="${dNSZoneRecord['dNSZoneRecordWeight']}"/>
										</TD>										
									</TR>
								</logic:iterate>
							</logic:notEmpty>								
						</TABLE>
					</div>	
				</div>
			</fieldset>
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
											   styleId="dNSZoneMemoRemarksID"
											   property="dNSZoneMemoRemarks"
											   rows="5" cols="60"/>
							</div>
						</TD>
					</TR>
				</TABLE>
			</div>
		</logic:equal>
		<logic:notEqual name="B_SSM_S02_Page_Form" property="displayMemoFlg" value="1">
			<html:textarea name="B_SSM_S02_Page_Form"
						   styleId="dNSZoneMemoRemarksID"
						   property="dNSZoneMemoRemarks"
						   rows="5" cols="60" style="display:none;"/>
		</logic:notEqual>
	</div>
</body>
</html>