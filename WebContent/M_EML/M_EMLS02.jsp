<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<%@page import="nttdm.bsys.common.fw.BillingSystemUserValueObject"%>
<%@page import="nttdm.bsys.common.util.CommonUtils"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/common.css"/>
   	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common_01.css"/>
   	<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>   		
   	<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/messageBox.js"></script>
   	<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/common.js"></script>
	
	<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/M_EML/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/M_EML/ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/M_EML/js/textareaAutoHeight.js"></script>
    
	<title>Mail Configuration Maintenance</title>
<script type="text/javascript">
	
	function initload(){
		var ue = UE.getEditor('myEditor');
        
		var isSuccessFlg=$(".isSaveFlg").val();
		if(isSuccessFlg == "1"){
			window.opener.location="<%=request.getContextPath()%>"+"/M_EML/M_EMLS01BL.do";
			window.opener = null;     
		    window.open('','_self');
		}
		
		var remarkArea = $("#remarkArea");
		remarkArea.tah({
		    moreSpace:18,
		    animateDur:250
		});
		remarkArea.focus();
	}
	
	function clickExit() {
		var MsgBox = new messageBox();
		var hiddenMsgExitConfirmation = document.getElementById("hiddenMsgExitConfirmation").value;
		if (MsgBox.POPEXT(hiddenMsgExitConfirmation) == MsgBox.YES_CONFIRM) {
		    window.close();
		}
	}
	
	function clickSave(){
		$(".isSaveFlg").val("0");
		$(".modelFlg").val("New");
		$('form').submit();
	} 
	 
	function keyPress(ev){  
	    if(ev.keyCode==13){   
	        var range=iframeP.document.selection.createRange(); 
	        range.text="\n";  
	        range.moveStart("character", 1);  
	        range.collapse(true);
	        range.select(); 
	         return false;   
	    }  
	    return true;  
	}  

</script>
</head>
<body onload="initload()">
		<%
            String accessRight = ((nttdm.bsys.common.fw.BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT")).getUserAccessInfo("M", "M-EML").getAccess_type();
            pageContext.setAttribute("accessRightBean", accessRight);
        %>
	<ts:form action="/M_EMLS02Save">
		<html:hidden property="modelFlg" styleClass="modelFlg" name="m_eml02_Form"/>
		<html:hidden property="isSaveFlg" styleClass="isSaveFlg" name="m_eml02_Form"/>
		<table class="subHeader" cellpadding="0" cellspacing="0">
			<tr>
  				<td ><bean:message key="screen.m_emls01.label.title"/></td>
			</tr>
		</table>
		<table cellpadding="0" cellspacing="0">
	    	<tr>
	    		<td>&nbsp;</td>
	    	</tr>
	    </table>
		<table class="subHeaderInfo" cellpadding="0" cellspacing="0">
		   	<tr>
		   		<td><bean:message key="screen.m_emls02.label.tem.title"/></td>
		   	</tr>
		</table>
		
		<table class = "inputInfo" cellpadding="0" cellspacing="0">
			<tr>
				<td width="15%"><bean:message key="screen.m_emls01.title.code"/><bean:message key="screen.m_eml.colon"/> 
				</td>
				<td width="30%"><html:text name="m_eml02_Form" property="code" styleClass="QCSTextBox" style="width:100px;" maxlength="5"></html:text>
				</td>
			</tr>
			<tr>
				<td width="15%"><bean:message key="screen.m_emls01.title.description"/><bean:message key="screen.m_eml.colon"/> 
				</td>
				<td width="30%"><html:text name="m_eml02_Form" property="description" styleClass="QCSTextBox" style="width:800px;" maxlength="100"></html:text>
				</td>
			</tr>
			<tr>
				<td width="15%"><bean:message key="screen.m_emls01.title.from_display_name"/><bean:message key="screen.m_eml.colon"/> 
				</td>
				<td width="30%"><html:text name="m_eml02_Form" property="display_name" styleClass="QCSTextBox" style="width:800px;" maxlength="100"></html:text>
				</td>
			</tr>
			<tr>
				<td width="15%"><bean:message key="screen.m_emls01.title.subject"/><bean:message key="screen.m_eml.colon"/> 
				</td>
				<td width="30%"><html:text name="m_eml02_Form" property="subject" styleClass="QCSTextBox" style="width:800px;" maxlength="100"></html:text>
				</td>
			</tr>
			<tr>
				<td width="15%"><bean:message key="screen.m_emls02.label.Attach"/><bean:message key="screen.m_eml.colon"/> 
				</td>
				<td width="30%"><html:text name="m_eml02_Form" property="attachpass" styleClass="QCSTextBox" style="width:800px;" maxlength="20"></html:text>
				</td>
			</tr>
			<tr>
				<td width="15%"><bean:message key="screen.m_emls02.label.zipFilename"/><bean:message key="screen.m_eml.colon"/> 
				</td>
				<td width="30%"><html:text name="m_eml02_Form" property="zipFilename" styleClass="QCSTextBox" style="width:800px;" maxlength="50"></html:text>
				</td>
			</tr>
			<tr>
				<td width="16%"><bean:message key="screen.m_emls02.label.attachment1"/><bean:message key="screen.m_eml.colon"/> 
				</td>
				<td width="30%"><html:text name="m_eml02_Form" property="attactmen1" styleClass="QCSTextBox" style="width:800px;" maxlength="50"></html:text>
				</td>
			</tr>
			<tr>
				<td width="15%"><bean:message key="screen.m_emls02.label.attachment2"/><bean:message key="screen.m_eml.colon"/> 
				</td>
				<td width="30%"><html:text name="m_eml02_Form" property="attactmen2" styleClass="QCSTextBox" style="width:800px;" maxlength="50"></html:text>
				</td>
			</tr>
			<tr>
				<td width="15%"><bean:message key="screen.m_emls02.label.attachment3"/><bean:message key="screen.m_eml.colon"/> 
				</td>
				<td width="30%"><html:text name="m_eml02_Form" property="attactmen3" styleClass="QCSTextBox" style="width:800px;" maxlength="50"></html:text>
				</td>
			</tr>
			<tr>
				<td width="15%"><bean:message key="screen.m_emls02.label.attachment4"/><bean:message key="screen.m_eml.colon"/> 
				</td>
				<td width="30%"><html:text name="m_eml02_Form" property="attactmen4" styleClass="QCSTextBox" style="width:800px;" maxlength="50"></html:text>
				</td>
			</tr>
			<tr>
				<td width="15%"><bean:message key="screen.m_emls01.title.cc"/><bean:message key="screen.m_eml.colon"/> 
				</td>
				<td width="30%"><html:text name="m_eml02_Form" property="alwaysCc" styleClass="QCSTextBox" style="width:800px;"></html:text>
				</td>
			</tr>
			<tr>
				<td width="15%" valign="top"><bean:message key="screen.m_emls02.label.content"/><bean:message key="screen.m_eml.colon"/> 
				</td>
				<td width="100%" height="100%">
					<nested:textarea property="content" styleClass="billDesc" style="width:805px;height:300px;" rows="25" cols="90" styleId="myEditor"/>
					
				</td>
			</tr>
			<tr>
				<td width="15%" valign="top"><bean:message key="screen.m_emls02.label.remark"/><bean:message key="screen.m_eml.colon"/> 
				</td>
				<td width="30%">
					<nested:textarea property="remark" styleClass="remark" style="width:801px;overflow-y:hidden" styleId="remarkArea"/>
				</td>
			</tr>
		</table>
	
		<table class="buttonGroup" cellpadding="0" cellspacing="0">
			<tr>
				<td>
					<input type="button" class="button" value="<bean:message key="screen.m_emls01.label.save"/>" name="forward_save" onclick="clickSave()"/> 
					<input type="button" class="button" value="<bean:message key="screen.m_emls02.label.exit"/>" name="forward_exit" onclick="clickExit()"/>
				</td>
			</tr>
		</table>
		<input type="hidden" id="hiddenMsgExitConfirmation"
			value="<bean:message key="info.ERR4SC001"/>" />
	</ts:form>
	<div class="error">
		<html:messages id="message">
			<bean:write name="message" />
			<br />
		</html:messages>
	</div>
	<div class="message">
		<ts:messages id="messages" message="true">
			<bean:write name="messages" />
			<br />
		</ts:messages>
	</div>
	
</body>
</html>