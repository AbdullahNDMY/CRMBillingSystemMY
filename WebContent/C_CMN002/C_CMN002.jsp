<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ page import="nttdm.bsys.common.fw.BillingSystemUserValueObject" %>
<%@ page import="nttdm.bsys.c_cmn001.bean.UserAccess" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html locale="true">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Content-Script-Type" content="text/javascript">
    <meta http-equiv="Content-Style-Type" content="text/css">
    <meta name="Author" content="NTT Data Vietnam">
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/C_CMN002/css/c_cmn002s01.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/C_CMN002/js/c_cmn002s01.js"></script>
    <title><bean:message key="screen.common.c_cmn001s02"/></title>
	<script type="text/javascript" language="javascript">
		var server_path = '<%=request.getContextPath()%>';
		function toggle(id){
			$('#' + id).toggle();
		}
		
	</script>
</head>

<body id="c_cmn002s01" onload="parent.showMenu(true);initMain();initPage();">
	<input type="hidden" id="menuPath" value="<%=request.getContextPath()%>/C_CMN003/C_CMN003BLogic.do">
	<ts:form action="/C_CMN002Action">
	<%
	String accType = ((nttdm.bsys.common.fw.BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT")).getUserAccessInfo("M", "M-ALT").getAccess_type();
	%>
	<bean:define id="accessType" value="<%=accType%>"></bean:define>
	<script type="text/javascript">
		confirmDelete = '<bean:message key="screen.c_cmn002.confirmDelete"/>'; 
		sendNewMsg('<bean:write name="_c_cmn002Form" property="newmsg"/>','<bean:message key="screen.c_cmn002.newNotification"/>');
	</script> 
	
	<c:if test="${_c_cmn002Form.access_type gt 0}" > 
	
	
	<table class="QCSSearchResultTableHeader">
	  <tr style="">
	    <td  class="Title" onclick="toggle('myNotification')"><bean:message key="screen.c_cmn002.notification"/></td>
	    				<%							
							if(session.getAttribute("USER_VALUE_OBJECT") != null)
						 	{
							 BillingSystemUserValueObject uvo=(BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT");
							 List<UserAccess> listuser= uvo.getUser_access();
							 String useraccess="";
							 for(int i=0; i<listuser.size();i++)
								{
									if(listuser.get(i).getId_sub_module().equals("M-ALT"))
									useraccess=listuser.get(i).getAccess_type();
								}
								
							 if(useraccess.equals("2"))
					 			{
								
						%>	
	    
	    
	    <td  class="New"><a href="javascript:void(0);" onclick="window.location = '<%=request.getContextPath()%>/M_ALT/M_ALTR01BLogic.do?click_event=1&msg_type=NOTICE&msg_type_label=1'"><bean:message key="screen.c_cmn002.new"/></a></td>
					    <%
	    						}
	    					}	
	     				%>
	  </tr>
	</table>
	<div id="myNotification">	
		<table width="200" >
		  <tr>
		    <td height="0px"></td>
		  </tr>
		</table>
		<table class="QCSSearchResultTableHeader"> 
			<tr>
				<td style="width:5%" class="header" align="center"><bean:message key="screen.c_cmn002.item"/></td>
				<td style="width:15%" class="header"><bean:message key="screen.c_cmn002.date"/></td>
				<td style="width:30%" class="header"><bean:message key="screen.c_cmn002.subject"/></td>
				<td style="width:15%" class="header"><bean:message key="screen.c_cmn002.refNo"/></td>
				<td style="width:15%" class="header"><bean:message key="screen.c_cmn002.important"/></td>
				<td style="width:10%" class="header"><bean:message key="screen.c_cmn002.dueDate"/></td>
				<td style="width:10%;text-align:left;font-weight:normal;padding-left:0px" nowrap="nowrap" class="header">
					<c:if test="${accessType eq 2}">	
						<a href="javascript:clickNotifDel('NOTICE')">Delete</a>
						<input type="checkbox" class="checkAllNotif" onclick="checkAllBox(this,'checkNotif')" />
					</c:if>				 
				</td>
			</tr> 
		</table> 
		<!-- show scrollbar for notification -->
		<div id="notif" style="height: ${_c_cmn002Form.max_row + 2}px;width:75%;
			overflow: -moz-scrollbars-vertical;overflow-x: hidden;overflow-y: auto;">
			<table class="QCSSearchResultTable">  
				<logic:present property="array_notification" name="_c_cmn002Form">
					<logic:iterate id="notification" name="_c_cmn002Form" property="array_notification">
					<tr>					
						<td class="default" style="width:5%" align="center"><bean:write name="notification" property="no"/></td>
						<td class="default" style="width:15%"><bean:write name="notification" property="display_date"/></td>
						<td class="default" style="width:30%">
						
							<a href="#" onclick="window.location = '<%=request.getContextPath()%>/M_ALT/M_ALTR01BLogic.do?click_event=0&id_msg=<bean:write name="notification" property="id_msg"/>'"><bean:write name="notification" property="subject"/></a>
					
						</td>						
						<td class="default" style="width:15%">
						<!-- 
						<a href="#" onclick="window.location = '<%=request.getContextPath()%>/Q_QCS/Q_QCSR02BLogic.do?id_ref_s01=<bean:write name="notification" property="id_ref"/>'"><bean:write name="notification" property="id_ref"/></a>
						 -->
						<a  href="javascript:onClickId_ref('<bean:write name="notification" property="id_screen"/>','<bean:write name="notification" property="id_ref"/>');"><bean:write name="notification" property="id_ref"/></a>
						</td>
						<td style="width:15%"
							class = "<logic:equal value="1" property="priority" name="notification">high</logic:equal>
							<logic:equal value="0" property="priority" name="notification">low</logic:equal>
							<logic:equal value="" property="priority" name="notification">normal</logic:equal>">
							
							<logic:equal value="1" property="priority" name="notification"><bean:message key="screen.c_cmn002.high"/><img src="<%=request.getContextPath()%>/image/ihigh.gif"/></logic:equal>
							<logic:equal value="0" property="priority" name="notification"><bean:message key="screen.c_cmn002.low"/></logic:equal> 
							<logic:equal value="" property="priority" name="notification">&nbsp;</logic:equal>
						</td>
						<td class="default" style="width:10%"><bean:write name="notification" property="end_date"/></td>
						
						<td class="default" nowrap="nowrap" style="width:10%;padding-left:47px">
							<c:if test="${accessType eq 2}">	
								<logic:notEmpty property="id_msg" name="notification">	
									<%-- <img src="<%=request.getContextPath()%>/image/delete.gif" onclick="clickLinkToDelete('<bean:write name="notification" property="id_msg"/>','NOTICE');"/> --%>
									<input type="checkbox" class="checkNotif" />
									<input type="hidden" class="notifiId" value="<bean:write name="notification" property="id_msg"/>"/>
								</logic:notEmpty>  
							</c:if> 
						</td>
						
					</tr>
					</logic:iterate>
				</logic:present>
			</table> 
		</div>
	</div>
	<table width="200" >
	  <tr>
	    <td height="6"></td>
	  </tr>
	</table>
	<table class="QCSSearchResultTableHeader">
	  <tr style="">  
	    <td class="Title" onclick="toggle('myTask')"><bean:message key="screen.c_cmn002.task"/></td>
	    <%
						if(session.getAttribute("USER_VALUE_OBJECT") != null)
						 	{
							 BillingSystemUserValueObject uvo=(BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT");
							 List<UserAccess> listuser= uvo.getUser_access();
							 String useraccess="";
							 for(int i=0; i<listuser.size();i++)
								{
									if(listuser.get(i).getId_sub_module().equals("M-ALT"))
									useraccess=listuser.get(i).getAccess_type();
								}
								
							 if(useraccess.equals("2"))
					 			{
								
							
						%>		
	    
	    
	   <td class="New"><a href="javascript:void(0);" onclick="window.location = '<%=request.getContextPath()%>/M_ALT/M_ALTR01BLogic.do?click_event=1&msg_type=TASK&msg_type_label=1'"><bean:message key="screen.c_cmn002.new"/></a></td>
	   				<%
	   					}
	   				}	
	   				
	   				 %>
	   
	   
	  </tr> 
	</table>
	<div id="myTask">
		<table width="200" >
		  <tr>
		    <td height="0px"></td>
		  </tr>
		</table>
		
		<table class="QCSSearchResultTableHeader"> 
			<tr>
				<td style="width:5%" class="header" align="center"><bean:message key="screen.c_cmn002.item"/></td>
				<td style="width:15%" class="header"><bean:message key="screen.c_cmn002.date"/></td>
				<td style="width:45%" class="header"><bean:message key="screen.c_cmn002.subject"/></td> 
				<td style="width:15%" class="header"><bean:message key="screen.c_cmn002.important"/></td>
				<td style="width:10%" class="header"><bean:message key="screen.c_cmn002.dueDate"/></td>
				<td style="width:10%" style="width:10%;text-align:left;font-weight:normal;padding-left:0px" nowrap="nowrap" class="header">
					<c:if test="${accessType eq 2}">	
						<a href="javascript:clickNotifDel('TASK')">Delete</a>
						<input type="checkbox" class="checkAllNotif" onclick="checkAllBox(this,'checkTask')" />
					</c:if>	
				</td>
			</tr>
		</table>
		<!-- show scrollbar for task -->
		<logic:equal value="1" property="task_count" name="_c_cmn002Form">
			<div id="task" style="height: <bean:write name="_c_cmn002Form" property="max_row"/>px;width:75%;
			overflow: -moz-scrollbars-vertical;
			overflow-x: hidden;
			overflow-y: scroll;">
				<table class="QCSSearchResultTable">  		
					<logic:present property="array_mytasks" name="_c_cmn002Form">
						<logic:iterate id="mytask" name="_c_cmn002Form" property="array_mytasks">
						<tr>
							<td class="default" style="width:5%" align="center"><bean:write name="mytask" property="no"/></td>
							<td class="default" style="width:15%"><bean:write name="mytask" property="display_date"/></td>
							<td class="default" style="width:45%">
							<a href="#" onclick="window.location = '<%=request.getContextPath()%>/M_ALT/M_ALTR01BLogic.do?click_event=0&id_msg=<bean:write name="mytask" property="id_msg"/>'"><bean:write name="mytask" property="subject"/></a>
							</td> 
							<td style="width:15%"
								class = "<logic:equal value="1" property="priority" name="mytask">high</logic:equal>
								<logic:equal value="0" property="priority" name="mytask">low</logic:equal>
								<logic:equal value="" property="priority" name="mytask">normal</logic:equal>
								">
								
								<logic:equal value="1" property="priority" name="mytask"><bean:message key="screen.c_cmn002.high"/><img src="<%=request.getContextPath()%>/image/ihigh.gif"/></logic:equal>
								<logic:equal value="0" property="priority" name="mytask"><bean:message key="screen.c_cmn002.low"/></logic:equal> 
								<logic:equal value="" property="priority" name="mytask">&nbsp;</logic:equal>
							</td>
							<td class="default" style="width:10%">
							<bean:write name="mytask" property="end_date"/></td>
							<td class="default" style="width:10%;padding-left:47px"> 
								<c:if test="${accessType eq 2}">	
									<logic:notEmpty property="id_msg" name="mytask">
										<%-- <img src="<%=request.getContextPath()%>/image/delete.gif" onclick="clickLinkToDelete('<bean:write name="mytask" property="id_msg"/>','TASK')"/> --%>
										<input type="checkbox" class="checkTask" />
										<input type="hidden" class="taskId" value="<bean:write name="mytask" property="id_msg"/>"/>
									</logic:notEmpty> 
								</c:if>
							</td> 
							
						</tr>
						</logic:iterate>
					</logic:present>		
				</table>
			</div>
		</logic:equal>
		
		<!-- not show scrollbar for task -->
		<logic:equal value="0" property="task_count" name="_c_cmn002Form">
			<table class="QCSSearchResultTable1">  		
				<logic:present property="array_mytasks" name="_c_cmn002Form">
					<logic:iterate id="mytask" name="_c_cmn002Form" property="array_mytasks">
					<tr>
						<td class="default" style="width:5%" align="center"><bean:write name="mytask" property="no"/></td>
						<td class="default" style="width:15%"><bean:write name="mytask" property="display_date"/></td>
						<td class="default" style="width:45%"><a href="#" onclick="window.location = '<%=request.getContextPath()%>/M_ALT/M_ALTR01BLogic.do?click_event=0&id_msg=<bean:write name="mytask" property="id_msg"/>'"><bean:write name="mytask" property="subject"/></a></td> 
						<td style="width:15%"
							class = "<logic:equal value="1" property="priority" name="mytask">high</logic:equal>
							<logic:equal value="0" property="priority" name="mytask">low</logic:equal>
							<logic:equal value="" property="priority" name="mytask">normal</logic:equal>
							">
							
							<logic:equal value="1" property="priority" name="mytask"><bean:message key="screen.c_cmn002.high"/><img src="<%=request.getContextPath()%>/image/ihigh.gif"/></logic:equal>
							<logic:equal value="0" property="priority" name="mytask"><bean:message key="screen.c_cmn002.low"/></logic:equal>
							<logic:equal value="" property="priority" name="mytask">&nbsp;</logic:equal>
						</td>
						<td class="default" style="width:10%">
						<bean:write name="mytask" property="end_date"/></td>
						<td class="default" style="width:10%;padding-left:47px"> 
							<c:if test="${accessType eq 2}">	
								<logic:notEmpty property="id_msg" name="mytask">
									<%-- <img src="<%=request.getContextPath()%>/image/delete.gif" onclick="clickLinkToDelete('<bean:write name="mytask" property="id_msg"/>','TASK')"/> --%>
									<input type="checkbox" class="checkTask" />
									<input type="hidden" class="taskId" value="<bean:write name="mytask" property="id_msg"/>"/>
								</logic:notEmpty> 
							</c:if>
						</td> 
						 
					</tr>
					</logic:iterate>
				</logic:present>		
			</table>
		</logic:equal>
	</div>
	<table width="200" >
	  <tr>
	    <td height="6"></td>
	  </tr>
	</table>
	<table class="QCSSearchResultTableHeader">
	  <tr style="">
	    <td  class="Title" onclick="toggle('mySent')"><bean:message key="screen.c_cmn002.sent"/></td>
	  </tr>
	</table>
	<div id="mySent">
		<table width="200" >
		  <tr>
		    <td height="0px"></td>
		  </tr>
		</table>
		<table class="QCSSearchResultTableHeader"> 
			<tr>
				<td style="width:5%" class="header" align="center"><bean:message key="screen.c_cmn002.item"/></td>
				<td style="width:15%" class="header"><bean:message key="screen.c_cmn002.date"/></td>
				<td style="width:30%" class="header"><bean:message key="screen.c_cmn002.subject"/></td>
				<td style="width:15%" class="header"><bean:message key="screen.c_cmn002.msgType"/></td>
				<td style="width:15%" class="header"><bean:message key="screen.c_cmn002.important"/></td>
				<td style="width:10%" class="header"><bean:message key="screen.c_cmn002.dueDate"/></td>
				<td style="width:10%;text-align:left;font-weight:normal;padding-left:0px" nowrap="nowrap"  class="header">
					<c:if test="${accessType eq 2}">	
						<a href="javascript:clickNotifDel('SENT')">Delete</a>
						<input type="checkbox" class="checkAllNotif" onclick="checkAllBox(this,'checkSent')" />
					</c:if>	
				</td>
			</tr> 
		</table> 
		<!-- not show scrollbar for sent -->
		<logic:equal value="1" property="sent_count" name="_c_cmn002Form">
			<div id="sent" style="height: <bean:write name="_c_cmn002Form" property="max_row"/>px;width:75%;
			overflow: -moz-scrollbars-vertical;
			overflow-x: hidden;
			overflow-y: scroll;">
				<table class="QCSSearchResultTable">  
					<logic:present property="array_sent" name="_c_cmn002Form">
						<logic:iterate id="sent" name="_c_cmn002Form" property="array_sent">
						<tr>
							<td class="default" style="width:5%"><bean:write name="sent" property="no"/></td>
							<td class="default" style="width:15%"><bean:write name="sent" property="date_created"/></td>
							<td class="default" style="width:30%"><a href="#" onclick="window.location = '<%=request.getContextPath()%>/M_ALT/M_ALTR01BLogic.do?click_event=0&id_msg=<bean:write name="sent" property="id_msg"/>'"><bean:write name="sent" property="subject"/></a></td>
							<td class="default" style="width:15%"><bean:write name="sent" property="msg_type"/></td>
							<td style="width:15%"
								class = "<logic:equal value="1" property="priority" name="sent">high</logic:equal>
								<logic:equal value="0" property="priority" name="sent">low</logic:equal>
								<logic:equal value="" property="priority" name="sent">normal</logic:equal>">
								
								<logic:equal value="1" property="priority" name="sent"><bean:message key="screen.c_cmn002.high"/><img src="<%=request.getContextPath()%>/image/ihigh.gif"/></logic:equal>
								<logic:equal value="0" property="priority" name="sent"><bean:message key="screen.c_cmn002.low"/></logic:equal>
								<logic:equal value="" property="priority" name="sent">&nbsp;</logic:equal>
							</td>
							<td class="default" style="width:10%"><bean:write name="sent" property="end_date"/></td>
							<td class="default" style="width:10%;padding-left:47px"> 
								<c:if test="${accessType eq 2}">	
									<logic:notEmpty property="id_msg" name="sent">
										<%-- <img src="<%=request.getContextPath()%>/image/delete.gif" onclick="clickLinkToDelete('<bean:write name="sent" property="id_msg"/>','SENT')"/> --%>
										<input type="checkbox" class="checkSent" />
										<input type="hidden" class="sentId" value="<bean:write name="sent" property="id_msg"/>"/>
									</logic:notEmpty>
								</c:if>
							</td> 			 
						</tr>
						</logic:iterate>
					</logic:present>
				</table> 
			</div>
		</logic:equal>
		
		<!-- not show scrollbar for sent -->
		<logic:equal value="0" property="sent_count" name="_c_cmn002Form">
			<table class="QCSSearchResultTable1">  
				<logic:present property="array_sent" name="_c_cmn002Form">
					<logic:iterate id="sent" name="_c_cmn002Form" property="array_sent">
					<tr>
						<td class="default" style="width:5%"><bean:write name="sent" property="no"/></td>
						<td class="default" style="width:15%"><bean:write name="sent" property="date_created"/></td>
						<td class="default" style="width:30%"><a href="#" onclick="window.location = '<%=request.getContextPath()%>/M_ALT/M_ALTR01BLogic.do?click_event=0&id_msg=<bean:write name="sent" property="id_msg"/>'"><bean:write name="sent" property="subject"/></a></td>
						<td class="default" style="width:15%"><bean:write name="sent" property="msg_type"/></td>
						<td style="width:15%"
							class = "<logic:equal value="1" property="priority" name="sent">high</logic:equal>
							<logic:equal value="0" property="priority" name="sent">low</logic:equal>
							<logic:equal value="" property="priority" name="sent">normal</logic:equal>">
							<logic:equal value="1" property="priority" name="sent"><bean:message key="screen.c_cmn002.high"/><img src="<%=request.getContextPath()%>/image/ihigh.gif"/></logic:equal>
							<logic:equal value="0" property="priority" name="sent"><bean:message key="screen.c_cmn002.low"/></logic:equal>
							<logic:equal value="" property="priority" name="sent">&nbsp;</logic:equal>
						</td>
						<td class="default" style="width:10%"><bean:write name="sent" property="end_date"/></td>
						<td class="default" style="width:10%;padding-left:47px"> 
							<c:if test="${accessType eq 2}">	
								<logic:notEmpty property="id_msg" name="sent">
									<%-- <img src="<%=request.getContextPath()%>/image/delete.gif" onclick="clickLinkToDelete('<bean:write name="sent" property="id_msg"/>','SENT')"/> --%>
									<input type="checkbox" class="checkSent" />
									<input type="hidden" class="sentId" value="<bean:write name="sent" property="id_msg"/>"/>
								</logic:notEmpty>
							</c:if>
						</td> 			 
					</tr>
					</logic:iterate>
				</logic:present>
			</table>  
		</logic:equal>
	</div>
	<input type="hidden" id="ERR1SC105" value="<bean:message key="errors.ERR1SC105" arg0="{0}" />"/>
	<input name="id_msg" type="hidden"/>
	<input name="msg_type" type="hidden"/>
	<input name="id_ref" type="hidden"/>
	<ts:submit value="Edit" property="forward_edit" style="visibility:hidden"></ts:submit>
	<ts:submit value="Delete" property="forward_delete" style="visibility:hidden"></ts:submit>
	<ts:submit value="New" property="forward_new" style="visibility:hidden"></ts:submit>
	<ts:submit value="Ref" property="forward_ref" style="visibility:hidden"></ts:submit>
	 </c:if>
	</ts:form>
	
</body>
</html:html>