<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <meta http-equiv="Content-Script-Type" content="text/javascript">
	    <meta http-equiv="Content-Style-Type" content="text/css">
	    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
   		<link type="text/css" rel="stylesheet" href="css/a_adts02.css" />
   		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script> 
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
		<title></title>
	</head>
	<body id="a" onload="initMain();">
		<t:defineCodeList id="LIST_PLANSTATUS_DISPLAY" />
		<t:defineCodeList id="LIST_BILLINGSTATUS_DISPLAY" />
		<table class="subHeader" cellpadding="0" cellspacing="0">
    		<tr>
    			<td><bean:message key="screen.a_adts02.screen_name"/></td>
    		</tr>
    	</table>
		<table class="generalSplitter" cellpadding="0" cellspacing="0">
    		<tr>
    			<td></td>
    		</tr>
    	</table>      	
		<table class="generalHeader" cellpadding="0" cellspacing="0">
    		<tr>
    			<td><bean:message key="screen.a_adts02.general_info"/></td>
    		</tr>
    	</table>    	
    	<bean:define id="header" name="_a_adtForm" property="selectedAuditHeader"></bean:define>
    	<table cellpadding="0" cellspacing="0">
    		<tr>
    			<td class="formview-title"></td>
    			<td class="formview-splitter"></td>
    			<td class="formview-content"></td>
    		</tr>    	
    		<tr>
    			<td class="formview-title"><bean:message key="screen.a_adt.label_date"/></td>
    			<td class="formview-splitter"><bean:message key="screen.a_adt.label_colon"/></td>
    			<td class="formview-content"><bean:write name="header" property="createdDate"/></td>
    		</tr>
    		<tr>
    			<td class="formview-title"><bean:message key="screen.a_adt.label_module"/></td>
    			<td class="formview-splitter"><bean:message key="screen.a_adt.label_colon"/></td>
    			<td class="formview-content"><bean:write name="header" property="moduleName"/></td>
    		</tr>
    		<tr>
    			<td class="formview-title"><bean:message key="screen.a_adt.label_sub_module"/></td>
    			<td class="formview-splitter"><bean:message key="screen.a_adt.label_colon"/></td>
    			<td class="formview-content"><bean:write name="header" property="subModuleName"/></td>
    		</tr>
    		<tr>
    			<td class="formview-title"><bean:message key="screen.a_adt.label_reference"/></td>
    			<td class="formview-splitter"><bean:message key="screen.a_adt.label_colon"/></td>
    			<td class="formview-content"><bean:write name="header" property="reference"/></td>
    		</tr> 
    		<tr>
    			<td class="formview-title"><bean:message key="screen.a_adt.label_status"/></td>
    			<td class="formview-splitter"><bean:message key="screen.a_adt.label_colon"/></td>
    			<td class="formview-content">
    				<c:if test="${_a_adtForm.selectedAuditHeader.subModuleID eq 'B-CPM' || _a_adtForm.selectedAuditHeader.subModuleID eq 'E-SET'}">
    					<logic:notEmpty name="header" property="status">
							<t:writeCodeValue codeList="LIST_PLANSTATUS_DISPLAY" name="header" property="status"/>
						</logic:notEmpty>
    				</c:if>
    				<c:if test="${!(_a_adtForm.selectedAuditHeader.subModuleID eq 'B-CPM' || _a_adtForm.selectedAuditHeader.subModuleID eq 'E-SET')}">
    					<bean:write name="header" property="status"/>
    				</c:if>
    			</td>
    		</tr>   		
    		<tr>
    			<td class="formview-title"><bean:message key="screen.a_adt.label_action"/></td>
    			<td class="formview-splitter"><bean:message key="screen.a_adt.label_colon"/></td>
    			<td class="formview-content"><bean:write name="header" property="action"/></td>
    		</tr>     
    		<tr>
    			<td class="formview-title"><bean:message key="screen.a_adt.label_id_user"/></td>
    			<td class="formview-splitter"><bean:message key="screen.a_adt.label_colon"/></td>
    			<td class="formview-content"><bean:write name="header" property="userID"/>
	    			<logic:notEmpty name="header" property="userName">
	    				<bean:message key="screen.a_adt.label_splitter"/>
    					<bean:write name="header" property="userName"/>
	    			</logic:notEmpty>
    			</td>
    		</tr>       		
    		</table>    		
    		<table cellpadding="0" cellspacing="0">
	    		<tr>
	    			<td width="3%"><bean:message key="screen.a_adt.label_data"/>&nbsp;<bean:message key="screen.a_adt.label_colon"/></td>    			
	    			<td width="94%"></td>
	    			<td width="3%"></td>
	    		</tr>
	    		<tr>
		    		<td width="3%"></td>
		    		<td width="94%">
		    		<div id="auditDetailInfo" class="wrapper">
		    		<table>
							<tr class="header">
								<td class="colNo"><bean:message key="screen.a_adt.label_number"/></td>
								<td class="colTable"><bean:message key="screen.a_adt.label_table"/></td>
								<td class="colField"><bean:message key="screen.a_adt.label_field"/></td>
								<td class="colOldData"><bean:message key="screen.a_adt.label_old_data"/></td>
								<td class="colNewData"><bean:message key="screen.a_adt.label_new_data"/></td>						
							</tr>
							<logic:iterate id="detail" name="_a_adtForm" property="selectedAuditDetailList">
								<tr>
									<td><bean:write name="detail" property="auditSeq"/></td>
									<td><bean:write name="detail" property="tableName"/></td>
									<td><bean:write name="detail" property="atField"/></td>
									<logic:equal value="PASSWORD" name="detail" property="atField">
										<td>******</td>
										<td>******</td>	
									</logic:equal>
									<logic:notEqual value="PASSWORD" name="detail" property="atField">
										<c:choose>
											<c:when test="${(detail.tableName eq 'T_CUST_PLAN_H' && detail.atField eq 'PLAN_STATUS') || (detail.tableName eq 'T_CUST_PLAN_D' && detail.atField eq 'SERVICES_STATUS')}">
												<td>
													<logic:notEmpty name="detail" property="oldData">
														<t:writeCodeValue codeList="LIST_PLANSTATUS_DISPLAY" name="detail" property="oldData"/>
													</logic:notEmpty>
													&nbsp;
												</td>
												<td>
													<logic:notEmpty name="detail" property="newData">
														<t:writeCodeValue codeList="LIST_PLANSTATUS_DISPLAY" name="detail" property="newData"/>
													</logic:notEmpty>
													&nbsp;
												</td>	
											</c:when>
											<c:otherwise>
												<c:choose>
													<c:when test="${detail.tableName eq 'T_CUST_PLAN_D' && detail.atField eq 'BILLING_STATUS'}">
														<td>
															<logic:notEmpty name="detail" property="oldData">
																<t:writeCodeValue codeList="LIST_BILLINGSTATUS_DISPLAY" name="detail" property="oldData"/>
															</logic:notEmpty>
															&nbsp;
														</td>
														<td>
															<logic:notEmpty name="detail" property="newData">
																<t:writeCodeValue codeList="LIST_BILLINGSTATUS_DISPLAY" name="detail" property="newData"/>
															</logic:notEmpty>
															&nbsp;
														</td>	
													</c:when>
													<c:otherwise>
														<td><bean:write name="detail" property="oldData"/></td>
														<td><bean:write name="detail" property="newData"/></td>	
													</c:otherwise>
												</c:choose>
											</c:otherwise>
										</c:choose>
									</logic:notEqual>
								</tr>		
							</logic:iterate>
							<tr>
				    			
				    		</tr> 
					</table>
					</div>
					</td>
					<td width="3%"></td>
				</tr>
				<tr>
				<td width="3%">&nbsp;</td>
		    		<td width="94%"></td>
					<td width="3%"></td>
				</tr>												
				<tr>
					<td colspan=3>
						<ts:form action="/A_ADTS01SCR.do">
					    	<ts:submit styleClass="button" value="Exit" property="forward_search"/>		    			
					    </ts:form>
				    </td>				    
			    </tr>			    
		    </table>
	</body>
</html:html>

