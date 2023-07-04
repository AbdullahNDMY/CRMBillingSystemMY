<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@page import="nttdm.bsys.common.fw.BillingSystemUserValueObject"%>
<%@page import="nttdm.bsys.common.util.CommonUtils"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
   	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/common.css"/>
   	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common_01.css"/>
   	<link href="${pageContext.request.contextPath}/M_EML/css/m_eml.css" rel="stylesheet" type="text/css" />
   	<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>   		
   	<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/messageBox.js"></script>
   	<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/common.js"></script> 
	<title>Mail Configuration Maintenance</title>
	<script type="text/javascript">
	function clickNew(url) {

		var width = window.screen.width * 80 / 100;
		var height = window.screen.height * 80 / 100;
		var left = Number((screen.availWidth / 2) - (width / 2));
		var top = Number((screen.availHeight / 2) - (height / 2));
		var offsetFeatures = "width=" + width + ",height=" + height + ",left="
				+ left + ",top=" + top + "screenX=" + left + ",screenY=" + top;
		var strFeatures = "toolbar=no, status=no, menubar=no,location=no,scrollbars=yes, resizable=yes"
				+ "," + offsetFeatures;
		var newwindow = window.open(url, null, strFeatures);
		if (window.focus) {
			newwindow.focus();
		}
	}
	function linkClick(code,context) {
		var url = context +'/'+ 'M_EML/M_EMLS01Edit.do' + '?code=' + code ;
		var width = window.screen.width * 80 / 100;
		var height = window.screen.height * 80 / 100;
		var left = Number((screen.availWidth / 2) - (width / 2));
		var top = Number((screen.availHeight / 2) - (height / 2));
		var offsetFeatures = "width=" + width + ",height=" + height + ",left="
				+ left + ",top=" + top + "screenX=" + left + ",screenY=" + top;
		var strFeatures = "toolbar=no, status=no, menubar=no,location=no,scrollbars=yes, resizable=yes"
				+ "," + offsetFeatures;
		var newwindow = window.open(url, null, strFeatures);
		if (window.focus) {
			newwindow.focus();
		}
	}
	
	function test_template(noRow) {
		var MsgBox = new messageBox();
        var isOk = true;
        var message = "";
		var code = $("input[name='code']");
		var testEmail = $("input[name='testEmail']");
		var testEmailTrim = $.trim(testEmail.val());
		if(testEmailTrim){
			if(validateEmail(testEmailTrim)){
				$.ajax({
		               type: "POST",
		               url: "M_EMLS01TestEmail.do?",
		               data:{
		                   /* "hostName":$("input[name='serverName']").val().trim(),
		                   "portNo":$("input[name='portNumber']").val().trim(),
		                   "sslTls":$("input[name='ssltls']").val().trim(),
		                   "userName":$("input[name='userName']").val().trim(),
		                   "password":$("input[name='password']").val().trim(),
		                   "fileSize":$("input[name='attachmentFileSize']").val().trim(),
		                   "templataCode":code[noRow-1].value.trim(), */
		                   "testEmail":testEmailTrim
		               },
		               async:false,
		               success:function(data){
		                   if(data.resultFlg==='0'){
		                       isOk = false;
		                       message = data.msg;
		                   }else{
		                       isOk=true;
		                       message = data.msg;
		                   }
		               },
		               error: function(XMLHttpRequest, textStatus, errorThrown){
		                   isOk = false;
		                   message="System Error!";
		               }
		           });
			    
			        MsgBox.POPALT(message);
			}
			else{
				alert('Invalid testing emaill.');
			}
		}
		else{
			alert('Please enter testing email.');
		}
		    
	}
	
	function validateEmail(email) {
	    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	    return re.test(email);
	}
	</script>
</head>
<body>
		<%
            String accessRight = ((nttdm.bsys.common.fw.BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT")).getUserAccessInfo("M", "M-EML").getAccess_type();
            pageContext.setAttribute("accessRightBean", accessRight);
        %>
	<ts:form action="/M_EMLS01Action">
	<html:hidden property="id_com" name="m_eml_Form"/>
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
		   		<td><bean:message key="screen.m_emls01.label.server.title"/></td>
		   	</tr>
		</table>
		<table class = "inputInfo" cellpadding="0" cellspacing="0">
			<logic:equal value="2" name="accessRightBean"> 
			<tr>
				<td class="col1Top" width="15%"><bean:message key="screen.m_emls01.label.servername"/><bean:message key="screen.m_eml.colon"/> 
				</td>
				<td class="col2Top" width="30%"><html:text name="m_eml_Form" property="serverName" styleClass="QCSTextBox" style="width:210px;" maxlength="50"></html:text>
				</td>
				<td class="col3Top"><bean:message key="screen.m_emls01.label.smtpname"/><bean:message key="screen.m_eml.colon"/>
				</td>
				<td class="col4Top"><html:text name="m_eml_Form" property="userName" styleClass="QCSTextBox" style="width:210px;" maxlength="50"></html:text>
				</td>
			</tr>
			<tr>
				<td class="colRight"><bean:message key="screen.m_emls01.label.portnumber"/><bean:message key="screen.m_eml.colon"/>
				</td>
				<td class="colLeft"><html:text name="m_eml_Form" property="portNumber" styleClass="QCSTextBox" style="width:210px;" maxlength="50"></html:text>
				</td>
				<td class="colRight"><bean:message key="screen.m_emls01.label.smtppass"/><bean:message key="screen.m_eml.colon"/>
				</td>
				<td class="colLeft"><html:password name="m_eml_Form" property="password" styleClass="QCSTextBox" style="width:210px;" maxlength="50"></html:password>
				</td>
			</tr>
			<tr>
				<!-- #252 Batch Email Billing Document: generate PDF / email CT 11052017 ST-->
				<td class="colRight"><bean:message key="screen.m_emls01.label.ssltls"/><bean:message key="screen.m_eml.colon"/>
				</td>
				<td class="colLeft"><html:radio property="ssltls" value="1" name="m_eml_Form"><bean:message key="screen.m_emls01.label.ssltls.yes"/></html:radio>&nbsp;&nbsp;
				<html:radio property="ssltls" value="0" name="m_eml_Form"><bean:message key="screen.m_emls01.label.ssltls.no"/></html:radio>
				</td>
				<!-- #252 Batch Email Billing Document: generate PDF / email CT 11052017 EN-->
				<td class="colRight"><bean:message key="screen.m_emls01.title.fileSize"/><bean:message key="screen.m_eml.colon"/>
				</td>
				<td class="colLeft"><html:text name="m_eml_Form" property="attachmentFileSize" styleClass="QCSTextBox" style="width:210px;" onkeyup="this.value=this.value.replace(/\D/g,'')"></html:text>
				</td>
			</tr>
			</logic:equal>
			<logic:notEqual value="2" name="accessRightBean">
			<tr>
				<td class="col1Top" width="15%"><bean:message key="screen.m_emls01.label.servername"/><bean:message key="screen.m_eml.colon"/> 
				</td>
				<td class="col2Top" width="30%"><bean:write name="m_eml_Form" property="serverName"/>
				</td>
				<td class="col3Top"><bean:message key="screen.m_emls01.label.smtpname"/><bean:message key="screen.m_eml.colon"/>
				</td>
				<td class="col4Top"><bean:write name="m_eml_Form" property="userName"/>
				</td>
			</tr>
			<tr>
				<td class="colRight"><bean:message key="screen.m_emls01.label.portnumber"/><bean:message key="screen.m_eml.colon"/>
				</td>
				<td class="colLeft"><bean:write name="m_eml_Form" property="portNumber" />
				</td>
				<td class="colRight"><bean:message key="screen.m_emls01.label.smtppass"/><bean:message key="screen.m_eml.colon"/>
				</td>
				<td class="colLeft"><bean:write name="m_eml_Form" property="password" />
				</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td class="colRight"><bean:message key="screen.m_emls01.title.fileSize"/><bean:message key="screen.m_eml.colon"/>
				</td>
				<td class="colLeft"><bean:write name="m_eml_Form" property="attachmentFileSize" ></bean:write>
				</td>
			</tr>
			</logic:notEqual> 
		</table>
		<table cellpadding="0" cellspacing="0">
	    	<tr>
	    		<td>&nbsp;</td>
	    	</tr>
	    </table>
		<table class="subHeaderInfo" cellpadding="0" cellspacing="0">
		   	<tr>
		   		<td><bean:message key="screen.m_emls01.label.template.title"/></td>
		   	</tr>
		</table>
		<table  class="resultInfo" cellpadding="0" cellspacing="0">
		<tr>
	    	<td class="header" width="2%"><bean:message key="screen.m_emls01.title.no"/></td>
	    	<td class="header" width="5%"><bean:message key="screen.m_emls01.title.module"/></td>
	    	<td class="header" width="10%"><bean:message key="screen.m_emls01.title.submodule"/></td>
	    	<td class="header" width="10%"><bean:message key="screen.m_emls01.title.temcode"/></td>
	    	<td class="header" width="18%"><bean:message key="screen.m_emls01.label.test.template"/></td>
		</tr>
			<logic:iterate id="mailmod" name="m_eml_Form" property="mailModule" indexId="index">
		  		<tr>
		  			<td class="defaultNo"><bean:write name="mailmod" property="row_num"/></td>
		  			<html:hidden property="row_num" name="mailmod" indexed="true"/>
		  			<td class="defaultText"><bean:write name="mailmod" property="module"/></td>
		  			<html:hidden property="module" name="mailmod" indexed="true"/>
		  			<td class="defaultText"><bean:write name="mailmod" property="subModule"/></td>
		  			<html:hidden property="subModule" name="mailmod" indexed="true"/>
		  			<logic:equal value="2" name="accessRightBean">
		  			<td><html:text styleClass="InputTextbox" name="mailmod" property="temCode" indexed="true" maxlength="5"/></td>
		  			</logic:equal>
		  			<logic:notEqual value="2" name="accessRightBean">
		  			<td class="defaultText"><bean:write name="mailmod" property="temCode"/></td> 
		  			</logic:notEqual>
		  			<td class="defaultText"><html:text name="m_eml_Form" property="testEmail" styleClass="QCSTextBox" style="width:210px;" maxlength="50"></html:text>&nbsp;&nbsp;
		  			<input type="button" class="templateButton" value="<bean:message key="screen.m_emls01.label.test"/>" onclick="test_template();"/>
					</td>
		  		</tr>
			</logic:iterate>
		</table>
		<table>
            <tr>
            	<logic:equal value="2" name="accessRightBean">
				<td align="left">
					<input type="submit" name="forward_save" class="button" value="<bean:message key="screen.m_emls01.label.save"/>"/>
				</td>
				</logic:equal>
			</tr>
        </table>
        <div class="message">
			<html:messages id="message" message="true">
				<bean:write name="message"/><br/>
			</html:messages>
		</div>
		<div class="error">
				<html:messages id="message">
					<bean:write name="message"/><br/>
				</html:messages>
			</div>
		<table cellpadding="0" cellspacing="0">
	    	<tr>
	    		<td>&nbsp;</td>
	    	</tr>
	    </table>
		<table class="subHeaderInfo" cellpadding="0" cellspacing="0">
		   	<tr>
		   		<td><bean:message key="screen.m_emls01.label.listing.title"/></td>
		   		<logic:equal value="2" name="accessRightBean">
		   		<td align="right"><input type="button" name="forward_new" class="button" value="<bean:message key="screen.m_emls01.label.new"/>" onclick="clickNew('<%=request.getContextPath()%>/M_EML/M_EMLS01New.do')"/>
		   		</td>
		   		</logic:equal>
		   	</tr>
		</table>
		<table  class="resultInfo" cellpadding="0" cellspacing="0">
		<tr>
	    	<td class="header" width="15%"><bean:message key="screen.m_emls01.title.no"/></td>
	    	<td class="header" width="15%"><bean:message key="screen.m_emls01.title.code"/></td>
	    	<td class="header" width="40%"><bean:message key="screen.m_emls01.title.description"/></td>
	    	<%-- <td class="header" width="12%"><bean:message key="screen.m_emls01.title.subject"/></td>
	    	<td class="header" width="12%"><bean:message key="screen.m_emls01.title.cc"/></td> --%>
		</tr>
		<logic:present name="mailTemplate">
			<logic:iterate id="mailTem" name="mailTemplate" >
		  		<tr>
		  			<td class="defaultNo"><bean:write name="mailTem" property="row_num"/></td>
		  			<td class="defaultText">
		  				<html:hidden name="mailTem" property="code"/>
						<a class="hyperLink" onclick="linkClick('<bean:write name="mailTem" property="code"/>','<%=request.getContextPath()%>');">
						<bean:write name="mailTem" property="code"/>
						</a>
		  				<%-- <a href="javascript: linkClick('<bean:write name="mailTem" property="code"/>');"><bean:write name="mailTem" property="code"/></a> --%>
		  			</td>
		  			<td class="defaultText"><bean:write name="mailTem" property="description"/></td>
		  			<%-- <td class="defaultText"><bean:write name="mailTem" property="subject"/></td>
		  			<td class="defaultText"><bean:write name="mailTem" property="alwaysCc"/></td> --%>
		  		</tr>
			</logic:iterate>
		</logic:present>
		</table>
	</ts:form>
</body>
</html>