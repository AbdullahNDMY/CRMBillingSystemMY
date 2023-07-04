<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/bs" prefix="bs" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
   	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/common.css"/>
   	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common_01.css"/>
   	<link href="${pageContext.request.contextPath}/E_EML/css/e_eml.css" rel="stylesheet" type="text/css" />
   	<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
	<script type="text/javascript">
	function clickExit() {
		window.close();
		/* var MsgBox = new messageBox();
		var hiddenMsgExitConfirmation = document.getElementById("hiddenMsgExitConfirmation").value;
		if (MsgBox.POPEXT(hiddenMsgExitConfirmation) == MsgBox.YES_CONFIRM) {
		    window.close();
		} */
	}
	function clickDownload(id_doc){			
		document.getElementById("id_doc").value = id_doc;
		document.forms[0].action = '<%=request.getContextPath()%>/E_EML/E_EMLDownloadBL.do';
		document.forms[0].submit();
		//reset
		document.forms[0].action = '<%=request.getContextPath()%>/E_EML/E_EMLS03BL.do';		
	}
	</script>
<title><bean:message key="screen.e_eml_s01.title"/></title>
</head>
<body>
<ts:form action="/E_EMLS03BL">
	<input type="hidden" name="id_doc" id="id_doc"/>
	<table class="subHeader" cellpadding="0" cellspacing="0" width="100%">
		<tr>
  			<td ><bean:message key="screen.e_eml_s02.title"/></td>
		</tr>
	</table>
	<br/>	
	<table class="searchResultNo" cellpadding="0" cellspacing="0">
	  	<tr>
			<td style="font-size:12pt;">
				<bean:message key="screen.e_eml_s01.seachFound"/> <bean:message key="screen.e_eml_s01.colon"/>
				<%-- <c:if test="${_e_eml02_Form.map.totalCount != -1}"> --%>
					<bean:write name="_e_eml02_Form" property="totalCount"/>
				<%-- </c:if> --%>
			</td>
		</tr>
	</table> 
	<table class="pageLink" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td><bean:message key="screen.e_eml_s01.gotoPage"/> <bean:message key="screen.e_eml_s01.colon"/>
				<ts:pageLinks 
		    		id="curPageLinks"
					action="${pageContext.request.contextPath}/E_EML/E_EMLS03BL.do" 
					name="_e_eml02_Form" 
					rowProperty="row"
					totalProperty="totalCount" 
					indexProperty="startIndex"
					currentPageIndex="now" 
					totalPageCount="total"
					submit="true" />
				<logic:present name="curPageLinks">
					<bean:write name="curPageLinks" filter="false"/>
				</logic:present>
			</td>
		</tr>
	</table> 
	<table  class="resultInfo" cellpadding="0" cellspacing="0" width="100%">
	  <tr>
	    <td class="header" width="5%"><bean:message key="screen.e_eml_s02.emailId"/></td>
	    <td class="header" width="5%"><bean:message key="screen.e_eml_s01.batchId"/></td>
	    <td class="header" width="14%"><bean:message key="screen.e_eml_s02.from"/></td>
	    <td class="header" width="14%"><bean:message key="screen.e_eml_s02.to"/></td>
	    <td class="header" width="14%"><bean:message key="screen.e_eml_s02.cc"/></td>
	    <td class="header" width="12%"><bean:message key="screen.e_eml_s02.subject"/></td>
	    <td class="header" width="9%"><bean:message key="screen.e_eml_s01.sent"/></td>
	    <td class="header" width="9%"><bean:message key="screen.e_eml_s02.sent2"/></td> 
	    <td class="header" width="18%"><bean:message key="screen.e_eml_s01.attachment"/></td>
	  </tr>
	  <logic:present property="emailInfo" name="_e_eml02_Form">
		  <logic:iterate id="info" name="_e_eml02_Form" property="emailInfo" >
			  <tr>
			    <td class="defaultText">		    	
			    	<bean:write name="info" property="emailId"/>
			    </td>
			    <td class="defaultText">
			    	<bean:write name="info" property="batchId"/>
			    </td>
			    <td class="defaultText">    
				    <bean:write name="info" property="emailfrom"/>
			    </td>
			     <td class="defaultText">
			     	<bean:write name="info" property="emailto"/>
			    </td>
			    <td class="defaultText">
				    <bean:write name="info" property="emailcc"/>
			    </td>
			    <td class="defaultText">	    
				  <bean:write name="info" property="subject"/>
			    </td>
			    <td class="defaultText">
			    	<bean:write name="info" property="sent"/>
			    </td>
			    <td class="defaultText">
			    	<bean:write name="info" property="sent2"/>
			    </td>
			     <td class="defaultText">
				     <logic:iterate id="docFiles" name="info" property="id_doc" >
				     	<a href="#" onclick="clickDownload('<bean:write name="docFiles" property="ID_DOC"/>') "><bean:write name="docFiles" property="FILENAME"/></a><br/>
				     </logic:iterate>
			    </td>
			  </tr>
		   </logic:iterate>
	   </logic:present>
	</table> 
	<br/>
	<input type="button" class="button" value="Exit" name="forward_exit" onclick="clickExit()" />
	<input type="hidden" id="hiddenMsgExitConfirmation"
			value="<bean:message key="info.ERR4SC001"/>" />
</ts:form>
	<div class="message">
			<ts:messages id="message" message="true">
				<bean:write name="message"/><br/>
			</ts:messages>
		</div>
		<div class="error">
			<ts:messages id="message">
				<bean:write name="message"/><br/>
			</ts:messages>
		</div>
</body>
</html>