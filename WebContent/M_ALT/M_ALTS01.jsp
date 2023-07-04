<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page
	import="nttdm.bsys.m_alt.bean.FileInfo"%>
<html:html >
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">       
    <meta name="Author" content="NTT Data Vietnam">
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/m_alts01.css"/>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   	<script type="text/javascript" src="js/m_alts01.js"></script>
    <script type="text/javascript" src="js/actb.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="js/jquery.textarea-expander.js"></script>
	<script src="js/multifile.js"></script>
    <title></title>
	<script>
	// fix trim() method for IE8
	if(typeof String.prototype.trim !== 'function') {
	  String.prototype.trim = function() {
	    return this.replace(/^\s+|\s+$/g, ''); 
	  }
	}
	var customarray="";
	function msg_load()
	{		
		if(document.getElementById("msg_type") != null)
			{
			document.getElementById("msg_type").enabled= false;
			var message_type=document.getElementById("msg_type").value;				
				if(message_type == "TASK")
				{		
					//document.getElementById("forward_send").value='<bean:message key="screen.m_alts01.save"/>';
					if(document.getElementById("forward_send"))	
						document.getElementById("forward_send").style.display="none";
					if(document.getElementById("forward_save"))
						document.getElementById("forward_save").style.display="";
					document.getElementById("to_msg").disabled = true;
					document.getElementById("panel_to_cc1").style.visibility="hidden";
					document.getElementById("panel_to_cc2").style.visibility="hidden";
					document.getElementById("panel_to_cc3").style.visibility="hidden";
					document.getElementById("panel_to_cc4").style.visibility="hidden";		
								
				}
				else if(message_type == "NOTICE")
				{
					if(document.getElementById("forward_send"))
						document.getElementById("forward_send").value='<bean:message key="screen.m_alts01.send"/>';
					document.getElementById("to_msg").enabled = true;
					document.getElementById("cc_msg").enabled = true;
					document.getElementById("to_msg").disabled = false;
					document.getElementById("cc_msg").disabled = false;		
					document.getElementById("panel_to_cc1").style.visibility="visible";
					document.getElementById("panel_to_cc2").style.visibility="visible";
					document.getElementById("panel_to_cc3").style.visibility="visible";
					document.getElementById("panel_to_cc4").style.visibility="visible";
				}
								
			
			}
		else if(document.getElementById("id_msg") == null) 
		{	
			document.getElementById("msg_type").enabled= true;
			if(document.getElementById("msg_type") != null)
			{	
				document.getElementById("msg_type").disabled= false;
				
				var message_type=document.getElementById("msg_type").value;				
				if(message_type == "TASK")
				{		
				
					
					document.getElementById("to_msg").disabled = true;
					document.getElementById("panel_to_cc1").style.visibility="hidden";
					document.getElementById("panel_to_cc2").style.visibility="hidden";
					document.getElementById("panel_to_cc3").style.visibility="hidden";
					document.getElementById("panel_to_cc4").style.visibility="hidden";		
								
				}
				else if(message_type == "NOTICE")
				{
					document.getElementById("to_msg").enabled = true;
					document.getElementById("cc_msg").enabled = true;
					document.getElementById("to_msg").disabled = false;
					document.getElementById("cc_msg").disabled = false;		
					document.getElementById("panel_to_cc1").style.visibility="visible";
					document.getElementById("panel_to_cc2").style.visibility="visible";
					document.getElementById("panel_to_cc3").style.visibility="visible";
					document.getElementById("panel_to_cc4").style.visibility="visible";
				}
			}
		}	
	}
	
	function lst_mss_change()
	{
		var message_type=document.getElementById("msg_type").value;	
			
				if(message_type == "TASK")
				{		
				
					//document.getElementById("forward_send").value='<bean:message key="screen.m_alts01.save"/>';		
					if(document.getElementById("forward_send"))
						document.getElementById("forward_send").style.display="none";
					if(document.getElementById("forward_save"))
						document.getElementById("forward_save").style.display="";			
					document.getElementById("to_msg").disabled = true;
					document.getElementById("panel_to_cc1").style.visibility="hidden";
					document.getElementById("panel_to_cc2").style.visibility="hidden";
					document.getElementById("panel_to_cc3").style.visibility="hidden";
					document.getElementById("panel_to_cc4").style.visibility="hidden";
					
								
				}
				else if(message_type == "NOTICE")
				{
					if(document.getElementById("forward_send"))
						document.getElementById("forward_send").value='<bean:message key="screen.m_alts01.send"/>';
					document.getElementById("to_msg").enabled = true;
					document.getElementById("cc_msg").enabled = true;
					document.getElementById("to_msg").disabled = false;
					document.getElementById("cc_msg").disabled = false;		
					document.getElementById("panel_to_cc1").style.visibility="visible";
					document.getElementById("panel_to_cc2").style.visibility="visible";
					document.getElementById("panel_to_cc3").style.visibility="visible";
					document.getElementById("panel_to_cc4").style.visibility="visible";
				}
	
	}
	
	
	function set_forward()
	{
			var forward=document.getElementById("screen_mode_forward").value;
			if(forward == "1")
			{
				//alert("set forward");
			
				var currentTime = new Date();
					var month = currentTime.getMonth() + 1;
					var day = currentTime.getDate();
					var year = currentTime.getFullYear();					
					var datestart= day + "/" + month + "/" + year;
				//document.getElementById("start_date").value=datestart;
				//document.getElementById("end_date").value=datestart;
				//alert(datestart);
				
				
			
			}
	}
	function removeAttach(idDoc) {
		var d = document.getElementById("attachment");
		var olddiv = document.getElementById(idDoc);
		d.removeChild(olddiv);
	}
	function sendValidator() {
		var valid = true;
		
		// check To, CC mandatory
		var to_msg = document.getElementById("to_msg").value;
		var cc_msg = document.getElementById("cc_msg").value;
		if(to_msg.trim() == "" && cc_msg.trim() == "") {
			document.getElementById("toMandatoryHidden").style.display = "";
			valid = false;
		}
		else {
			document.getElementById("toMandatoryHidden").style.display = "none";
		}
				
		return saveValidator() && valid;
	}
	function saveValidator() {
		var valid = true;
		
		// check Subject mandatory
		if(document.getElementById("subject").value.trim() == "") {
			document.getElementById("subjectMandatoryHidden").style.display = "";
			valid = false;
		}
		else {
			document.getElementById("subjectMandatoryHidden").style.display = "none";
		}
		
		// check StartDate, EndDate mandatory
		var start_date = document.getElementById("start_date");
		var end_date = document.getElementById("end_date");
		if(start_date.value.trim() != "" && end_date.value.trim() != "") {
			if(getDate(start_date) > getDate(end_date)) {
				document.getElementById("dateMandatoryHidden").style.display = "";
				valid = false;
			}
			else {
				document.getElementById("dateMandatoryHidden").style.display = "none";
			}
		}
		return valid;
	}
	
	function getDate(dE) {
		var arrDate = dE.value.split("/");//dd/MM/yyyy
		return  new Date(arrDate[2], arrDate[1]-1, arrDate[0]);//yyyy/MM/dd
	}
	
	
	/**
	 * Remove space of text areas
	 */
	function removeSpaceOfTextAreas() {
		// Text area
		var textAreas = document.getElementsByTagName("textarea");	
		if (textAreas) {
			for (var i = 0; i < textAreas.length; i++) {
				var textArea = textAreas[i];
				textArea.value = textArea.value.replace(/^\s*|\s*$/g,'');
			}
		}
		// Text input
		var textInputs = document.getElementsByTagName("input");	
		if (textAreas) {
			for (var i = 0; i < textInputs.length; i++) {
				var textInput = textInputs[i];
				if (textInput.type && textInput.type == "text") {
					textInput.value = textInput.value.replace(/^\s*|\s*$/g,'');
				}
			}
		}
	}
	
	</script>
    
</head>

<body id="m" onload="initMain();
					 clickReminder();
					 msg_load();
					 set_forward();
					 removeSpaceOfTextAreas();">
	<ts:form styleId="_downLoadForm" action="/M_ALT01DSP" enctype="multipart/form-data" method="POST" > 
		<table class="subHeader" cellpadding="0" cellspacing="0">
		  <tr style="">
		    <td class="Title"><bean:message key="screen.m_alts01.title"/></td> 
		  </tr>
		</table>
		<t:defineCodeList id="LIST_NOTIFICATION" />
		<logic:equal value="0" name="_m_altForm" property="screen_mod">
			<table class="inputEmailInfo"  >
				<tr>
					<td class="col1Top">
						<bean:message key="screen.m_alts01.messagetype"/><bean:message key="screen.common.label_colon"/>
					</td>
					<td class="col2Top">
					<logic:empty name="_m_altForm" property="id_msg">
						<c:choose>
							<c:when test="${_m_altForm.msg_type_label==1}">
								<t:writeCodeValue name="_m_altForm" key="${_m_altForm.msg_type}" codeList="LIST_NOTIFICATION" scope="request"/>
								<html:hidden name="_m_altForm" property="msg_type"/>
							</c:when>
							<c:otherwise>
								<html:select name="_m_altForm" property="msg_type" styleClass="MessageTypeCombobox" onchange="lst_mss_change();">
									<html:options collection="LIST_NOTIFICATION" property="id" labelProperty="name"/>
								</html:select>
							</c:otherwise>
						</c:choose>
					</logic:empty>
					<logic:notEmpty name="_m_altForm" property="id_msg">
						<html:hidden name="_m_altForm" property="msg_type"/>
						<t:writeCodeValue name="_m_altForm" key="${_m_altForm.msg_type}" codeList="LIST_NOTIFICATION" scope="request"/>
					</logic:notEmpty>
						
					</td>
				</tr>
			
				<tr>
			
					<td class="colLeft">
						<span id="panel_to_cc1">
						<bean:message key="screen.m_alts01.to"/><bean:message key="screen.common.label_colon"/>
						</span>
					</td>
					<td class="colRight">
					<span id="panel_to_cc2">	
						<input type="text" 
							   style="width:100%;font-family: Tahoma;"  
							   name="to_msg" 
							   class="expand" 
							   id="tb" 
							   autocomplete="off"
							   value="${_m_altForm['to_msg']}"
							   maxlength="500" />
						
						<script>					
						var obj = actb(document.getElementById('tb'),customarray,'<bean:write name="_m_altForm" property="lst_user"/>');
						</script>
					</span>	
					</td>
				</tr>
				<tr>
					<td class="colLeft">
					<span id="panel_to_cc3">
						<bean:message key="screen.m_alts01.cc"/><bean:message key="screen.common.label_colon"/>
						</span>
					</td>
					<td class="colRight">
						<span id="panel_to_cc4">											
						<input type="text" 
							   style="width:100%;font-family: Tahoma;"  
							   name="cc_msg" 
							   class="expand" 
							   id="cc" 
							   autocomplete="off"
							   value="${_m_altForm['cc_msg']}"
							   maxlength="500" />
						<script>					
						var obj1 = actb(document.getElementById('cc'),customarray,'<bean:write name="_m_altForm" property="lst_user"/>');					
						</script>
						</span>
					</td>
				</tr>
			
				<tr>
					<td class="colLeft" valign="top" align="right">	
						<div style="float: right;">			
					   		<bean:message key="screen.common.label_colon"/>								   
						</div>
						<div id="listFileDiv" style="overflow: hidden; width: 70px;float: right;">										
						  	<input id="my_file_element" 
						  		   type="file" 
						  		   size="5"
						  		   class="realFile"/>	
						  	<html:button property="button" 
										 style="" 
										 styleId="attachBtn"
										 styleClass="fakeButton"
										 onclick="">
						  		<bean:message key="screen.m_alts01.attachment"/>
						  	</html:button>
						  	
					    </div>	
					    
					</td>
					<td class="colRight">
						<div id="attachment" style="border:0;width:100%;">
							<logic:iterate name="_m_altForm" id="fileInfo" property="fileInfos">
								<div class="uploadDisplay" id="<bean:write name="fileInfo" property="id_doc"/>">
									<img src="../image/delete.gif" onclick="removeAttach('<bean:write name="fileInfo" property="id_doc"/>');"/>
									<a href="javascript:void(0);" onclick="clickDownload('<bean:write name="fileInfo" property="id_doc"/>') "> <bean:write name="fileInfo" property="filename"/>;</a>
									<input type="hidden" name="listFileIdOld" value="<bean:write name="fileInfo" property="id_doc"/>"/>
								</div>
							</logic:iterate> 		
	 					</div>
					</td>
				</tr>
				<tr>
					<td class="colLeft">
						<bean:message key="screen.m_alts01.subject"/><bean:message key="screen.common.label_colon"/>
					</td>
					<td class="colRight">
						<html:text property="subject" name="_m_altForm" styleClass="FullTextBox" maxlength="250"/>
					</td>
				</tr>
				<tr>
					<td class="colLeft">
						<bean:message key="screen.m_alts01.startdate"/><bean:message key="screen.common.label_colon"/>
					</td>
					<td class="colRight" id="colReminder">
						<html:text property="start_date" name="_m_altForm" readonly="true" styleClass="DateTextBox"/>
						<t:inputCalendar for ="start_date" format="dd/MM/yyyy"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<bean:message key="screen.m_alts01.enddate"/><bean:message key="screen.common.label_colon"/>
						<html:text property="end_date" name="_m_altForm" readonly="true" styleClass="DateTextBox"/>
						<t:inputCalendar for ="end_date" format="dd/MM/yyyy"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;					
					</td>
				</tr>
				<tr>
					<td class="colLeft">
						<logic:equal value="0" property="importance_chk" name="_m_altForm">
							<input type="checkbox" name="importance_chk" value="<bean:write name="_m_altForm" property="importance_chk"/>" onclick="clickImportance(this)"/>
						</logic:equal>
						<logic:equal value="1" property="importance_chk" name="_m_altForm">
							<input type="checkbox" name="importance_chk" value="<bean:write name="_m_altForm" property="importance_chk"/>" checked="checked" onclick="clickImportance(this)"/>
						</logic:equal>
						<bean:message key="screen.m_alts01.importance"/><bean:message key="screen.common.label_colon"/>
					</td>
					<td class="colRight">
						<logic:equal value="0" property="importance_chk" name="_m_altForm">
							<html:radio property="priority" disabled="true" style="" value="1" name="_m_altForm"><bean:message key="screen.m_alts01.highimportance"/></html:radio>
							&nbsp;&nbsp;
							<html:radio property="priority" disabled="true" value="0" name="_m_altForm"><bean:message key="screen.m_alts01.lowimportance"/></html:radio>						
						</logic:equal>
						<logic:equal value="1" property="importance_chk" name="_m_altForm">
							<html:radio property="priority" value="1" name="_m_altForm"><bean:message key="screen.m_alts01.highimportance"/> </html:radio>
							&nbsp;&nbsp;
							<html:radio property="priority" value="0" name="_m_altForm"><bean:message key="screen.m_alts01.lowimportance"/> </html:radio>						
						</logic:equal>
						
					</td>
				</tr>
				<tr>
					<td class="colLeft">
						&nbsp;
					</td>
					<td class="colRight">
						<html:textarea property="msg" name="_m_altForm" styleClass="MessageTextArea" style="overflow-y:visible;"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="colBottom">
						&nbsp;
					</td>
				</tr>
			</table>
			<html:hidden name="_m_altForm" property="file_id"/>
			<input type="submit" class="button" name="forward_download" value="downloadFile" style="display: none"/>
			<br>
			<logic:equal value="2" name="_m_altForm" property="user_access">
			<input type="submit" class="button" name="forward_send" value="<bean:message key="screen.m_alts01.send"/>" onclick="return sendValidator();"/>
			<input type="submit" class="button" style="display:none;" name="forward_save" value="<bean:message key="screen.m_alts01.save"/>" onclick="return saveValidator();"/>
			</logic:equal>
			<input type="button" class="button" name="forward_exit" value="<bean:message key="screen.m_alts01.exit"/>" onclick="clickExit('<bean:message key="info.ERR4SC001"/>','<bean:write name="_m_altForm" property="click_event"/>','<%=request.getContextPath()%>/C_CMN002/C_CMN002BLogic.do')"/>
			<script>	
				$(document).ready(function(){
					var multi_selector = new MultiSelector('attachment', 'realFile','listFile', 100, null, null);	
					multi_selector.addElement(document.getElementById( 'my_file_element' ));
				});
			</script>
		</logic:equal>
		<logic:equal value="1" name="_m_altForm" property="screen_mod">
			<table class="inputEmailInfo">
				<tr>
					<td class="col1Top">
						<bean:message key="screen.m_alts01.messagetype"/><bean:message key="screen.common.label_colon"/>
					</td>
					<td class="col2Top">
						<t:writeCodeValue name="_m_altForm" key="${_m_altForm.msg_type}" codeList="LIST_NOTIFICATION" scope="request"/>
					</td>
				</tr>
				<tr>
					<td class="colLeft" valign="top">
						<bean:message key="screen.m_alts01.from"/><bean:message key="screen.common.label_colon"/>
					</td>
					<td class="colRight" valign="bottom">
						<bean:write property="creator_name" name="_m_altForm"/>
					</td>								
				</tr>
				<tr>
					<td class="colLeft">
						<bean:message key="screen.m_alts01.to"/><bean:message key="screen.common.label_colon"/>
					</td>
					<td class="colRight">					
						<bean:write property="to_msg_name" name="_m_altForm"/>
					</td>
				</tr>
				<tr>
					<td class="colLeft">
						<bean:message key="screen.m_alts01.cc"/><bean:message key="screen.common.label_colon"/>
					</td>
					<td class="colRight">
						<bean:write property="cc_msg_name" name="_m_altForm"/>
					</td>
				</tr>
				<tr>
					<td class="colLeft">
						<button disabled="disabled" ><bean:message key="screen.m_alts01.attachment"/></button><bean:message key="screen.common.label_colon"/>
					</td>			
					<td class="colRight">
						<logic:iterate name="_m_altForm" id="fileInfo" property="fileInfos">
							<a href="javascript:void(0);" onclick="clickDownload('<bean:write name="fileInfo" property="id_doc"/>') "> <bean:write name="fileInfo" property="filename"/></a>
						</logic:iterate>
					</td>
				</tr>
				<tr>
					<td class="colLeft" style="vertical-align: top;">
						<bean:message key="screen.m_alts01.subject"/><bean:message key="screen.common.label_colon"/>
					</td>
					<td class="colRight" style="vertical-align: bottom;">
						<bean:write name="_m_altForm" property="subject"/>				
					</td>
				</tr>
				<tr>
					<td class="colLeft">
						<bean:message key="screen.m_alts01.startdate"/><bean:message key="screen.common.label_colon"/>
					</td>
					<td class="colRight" id="colReminder">
						<label class ="DateTextBox"><bean:write name="_m_altForm" property="start_date"/></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					
						<bean:message key="screen.m_alts01.enddate"/><bean:message key="screen.common.label_colon"/>
						<label class ="DateTextBox"><bean:write name="_m_altForm" property="end_date"/></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
					<td class="colLeft">
						<logic:equal value="1" property="importance_chk" name="_m_altForm">						
						
						<input type="checkbox" 	property="importance_chk" name="_m_altForm" disabled="disabled" checked="checked"/>&nbsp;	
						<bean:message key="screen.m_alts01.importance"/><bean:message key="screen.common.label_colon"/>
						</logic:equal>
						<logic:equal value="0" property="importance_chk" name="_m_altForm">						
						<html:checkbox property="importance_chk" name="_m_altForm" disabled="true"/>&nbsp;				
						<bean:message key="screen.m_alts01.importance"/><bean:message key="screen.common.label_colon"/>
						</logic:equal>
						
						
					</td>
					<td class="colRight">
						<logic:equal value="1" name="_m_altForm" property="priority">
							<bean:message key="screen.m_alts01.highimportance"/>
						</logic:equal>
						<logic:equal value="0" name="_m_altForm" property="priority">
							<bean:message key="screen.m_alts01.lowimportance"/>
						</logic:equal>				
					</td>
				</tr>
				<tr>
					<td class="colLeft">
						&nbsp;
					</td>
					<td class="colRight">						
						<textarea readonly="readonly" 
								  style="background-color: #f4f3ef;
								  		 width: 590px; 
								  		 height: 100px;overflow-y:visible;">
							<bean:write property="msg" name="_m_altForm"/>
						</textarea>	
					</td>
				</tr>
				<tr>
					<td colspan="2" class="colBottom">
						&nbsp;
					</td>
				</tr>
			</table>
			<br>
		<logic:equal value="NOTICE" name="_m_altForm" property="msg_type">
			<logic:equal value="2" name="_m_altForm" property="user_access">
				<input type="submit" class="button" name="forward_reply" value="<bean:message key="screen.m_alts01.reply"/>" onclick="clickReply()"/>
				<input type="submit"  name="forward_replyAll" value="<bean:message key="screen.m_alts01.replytoall"/>" onclick="clickReplyToAll()"/>
				<input type="submit" class="button" name="forward_forwd" value="<bean:message key="screen.m_alts01.forward"/>" onclick="clickForward()"/>
				<input type="submit" class="button" value="<bean:message key="screen.m_alts01.delete"/>" name="forward_delete" onclick=" return clickDelete('<bean:message key="info.ERR4SC002"/>')"/>
			</logic:equal>
			<input type="button" class="button" name="forward_exit" value="<bean:message key="screen.m_alts01.exit"/>" onclick="clickExitViewMode('<%=request.getContextPath()%>/C_CMN002/C_CMN002BLogic.do')"/>
			<input type="submit" class="button" name="forward_download" value="donloadFile" style="display: none"/>
			<html:hidden name="_m_altForm" property="file_id"/>
		</logic:equal>
		<logic:equal value="TASK" name="_m_altForm" property="msg_type">		
			<logic:equal value="2" name="_m_altForm" property="user_access">			
				<input type="submit" class="button" name="forward_edit" value="<bean:message key="screen.m_alts01.edit"/>" onclick="clickEdit()"/>
				<input type="submit" class="button" value="<bean:message key="screen.m_alts01.delete"/>" name="forward_delete" onclick=" return clickDelete('<bean:message key="info.ERR4SC002"/>')"/>
			</logic:equal>
			<input type="button" class="button" name="forward_exit" value="<bean:message key="screen.m_alts01.exit"/>" onclick="clickExitViewMode('<%=request.getContextPath()%>/C_CMN002/C_CMN002BLogic.do')"/>
			<input type="submit" class="button" name="forward_download" value="donloadFile" style="display: none"/>
			<html:hidden name="_m_altForm" property="file_id"/>
		
		</logic:equal>
		</logic:equal>
		<html:hidden name="_m_altForm" property="click_event"/>
		<html:hidden name="_m_altForm" property="msg_type_hidden"/>
		<html:hidden name="_m_altForm" property="to_msg_hidden"/>
		<html:hidden name="_m_altForm" property="cc_msg_hidden"/>
		<html:hidden name="_m_altForm" property="subject_hidden"/>
		<html:hidden name="_m_altForm" property="start_date_hidden"/>
		<html:hidden name="_m_altForm" property="end_date_hidden"/>
		<html:hidden name="_m_altForm" property="reminder_date_hidden"/>
		<html:hidden name="_m_altForm" property="reminder_chk_hidden"/>
		<html:hidden name="_m_altForm" property="importance_chk_hidden"/>
		<html:hidden name="_m_altForm" property="priority_hidden"/>
		<html:hidden name="_m_altForm" property="msg_hidden"/>
		<html:hidden name="_m_altForm" property="id_msg"/>		
		<html:hidden name="_m_altForm" property="screen_mode_forward"/>
		<div id="fileDivHidden" style="display: none"></div>
		<div id="toMandatoryHidden" style="display: none" class="error">
			<bean:message key="errors.ERR1SC005" arg0="To"/>
		</div>
		<div id="subjectMandatoryHidden" style="display: none" class="error">
			<bean:message key="errors.ERR1SC005" arg0="Subject"/>
		</div>
		<div id="dateMandatoryHidden" style="display: none" class="error">
			<bean:message key="errors.ERR1SC013" arg0="Date" arg1='"Start Date"' arg2='"End Date"'/>
		</div>
		<div class="error">
			<html:messages id="message">
				<bean:write name="message"/><br/>
			</html:messages>
		</div>
		<div class="message">
			<ts:messages id="messages" message="true">
				<bean:write name="messages"/><br/>
			</ts:messages>
	   	</div>
	</ts:form>
</body>
</html:html>