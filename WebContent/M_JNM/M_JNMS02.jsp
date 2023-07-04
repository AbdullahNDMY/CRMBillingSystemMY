<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-html" prefix="html"%>
<%@ taglib uri="/struts-bean" prefix="bean"%>
<%@ taglib uri="/struts-logic" prefix="logic"%>
<%@ taglib uri="/terasoluna-struts" prefix="ts"%>
<%@ taglib uri="/terasoluna" prefix="t"%>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/stylesheet/common.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/javascript/common.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
<title><bean:message key="screen.m_jnm.title" /></title>
<script type="text/javascript">	

			function initMain(){
				var actionFrom=$(".actionFrom").val();
				if(actionFrom=="add"){
					$(".id_cust").attr("disabled", true);
				}
				var objFrm = document.forms['_m_newjnmForm'];	
				var isSuccessFlg=objFrm['isSaveFlg'].value;
				if(isSuccessFlg=='1'){	
					if(actionFrom=="add"){
						//key & text
						var jboNo=$(".jobno").val();
						//Update Parent window
						window.opener.setJobNoOptionInfo(jboNo);
					}
					window.opener.$("#refreshFlg").val("1");
					// #159 Start
					window.opener.$("#name_cust").val("");
					window.opener.$("#idCust").val($(".id_cust").val());
					window.opener.$("#job_no").val($(".jobno").val());
					// #159 End
					window.opener.$("#search").click();
					window.opener=null;     
				    window.open('','_self');     
				    window.close();  
				}
				objFlg=objFrm['msgBoxMode'].value;
				objCustname=objFrm['same_cust_name'].value;
				objCustId=objFrm['same_id_cust'].value;
				objJobNo=objFrm['job_no'].value;
				if (objFlg == '1') {					
				    var MsgBox = new messageBox();
	                var ERR4SC017_info = document.getElementById("ERR4SC017_info").value;
	                	objCustname=objCustname.substring(0,objCustname.length-1);
	                	 var resultInfo = ERR4SC017_info.replace('with', 'with ' + objCustname);
	                if (MsgBox.POPCFM(resultInfo) == MsgBox.YES_CONFIRM) {	
	                	objFrm['saveMode'].value = "1";
	                	document.forms[0].submit();
	                } 
				}
				objFrm['msgBoxMode'].value="0";				
			}
			
			/**
			Performing when click Save Button
			*/			
			function clickSave(){
				var objFrm = document.forms['_m_newjnmForm'];				
				//Set value for clickEvent as 1
				objFrm['clickEvent'].value = "1";
				objFrm['isSaveFlg'].value = "0";
				//$(".isSaveFlg").val(0);
				return true;
			}
			/**
				Performing when click Exit Button
			*/
			function clickExit() {
				var MsgBox = new messageBox();
				var hiddenMsgExitConfirmation = document.getElementById("hiddenMsgExitConfirmation").value;
				if (MsgBox.POPEXT(hiddenMsgExitConfirmation) == MsgBox.YES_CONFIRM) {
				    window.close();
				}
			}				
</script>
</head>
<body id="m" onload="initMain();">
	<ts:form action="/M_JNMR02BLogic">
		<h1 class="title">
			<bean:message key="screen.m_jnm.title" />
		</h1>
		<fieldset class="fieldsetPadding fieldsetBorder">
			<legend style="color: #4876ff;">
				<bean:message key="screen.m_jnm.addnewjobno" />
			</legend>
			<table style="width: 100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td align="right" style="width: 20%"><bean:message
							key="screen.m_jnm.customerId" /> <span style="color: red;"><bean:message
								key="screen.m_jnm.star" /> </span> &nbsp;:&nbsp;</td>
					<td align="left"><html:text property="id_cust"
							styleClass="id_cust" name="_m_newjnmForm" maxlength="6"></html:text>
						<!-- #159 Start -->
						<span style="color: blue;font-style: italic"><bean:message key="screen.m_jnm.customerLable" /></span>
						<!-- #159 End -->
					</td>
				</tr>
				<tr>
					<td align="right" style="width: 20%"><bean:message
							key="screen.m_jnm.jobNo" /> <span style="color: red;"><bean:message
								key="screen.m_jnm.star" /> </span> &nbsp;:&nbsp;</td>
					<td align="left"><html:text property="job_no"
							styleClass="jobno" name="_m_newjnmForm" maxlength="20"></html:text>
					</td>
				</tr>
			</table>
		</fieldset>
		<table class="buttonGroup" cellpadding="0" cellspacing="0">
			<tr>
				<td><input type="submit" class="button"
					value="<bean:message key="screen.m_jnm.savebutton"/>"
					name="forward_save" onclick="return clickSave();" /> <input
					type="button" class="button"
					value="<bean:message key="screen.m_jnm.exitbutton"/>"
					name="forward_exit" onclick="clickExit()" />
				</td>
			</tr>
		</table>
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
		<html:hidden name="_m_newjnmForm" property="clickEvent" />
		<html:hidden name="_m_newjnmForm" property="msgBoxMode" />
		<html:hidden name="_m_newjnmForm" property="saveMode" />
		<html:hidden name="_m_newjnmForm" property="isSaveFlg" />
		<html:hidden name="_m_newjnmForm" property="same_cust_name" />
		<html:hidden name="_m_newjnmForm" property="same_id_cust" />
		<html:hidden name="_m_newjnmForm" property="id_cust" />
		<html:hidden name="_m_newjnmForm" property="actionFrom"
			styleClass="actionFrom" />
		<input type="hidden" id="ERR4SC017_info"
			value='<bean:message key="info.ERR4SC017" arg0="Job No. duplicated with "/>' />
		<input type="hidden" id="hiddenMsgExitConfirmation"
			value="<bean:message key="info.ERR4SC001"/>" />
	</ts:form>
</body>
</html:html>

