<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="/WEB-INF/bs" prefix="bs" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
	<head>
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
		<link href="${pageContext.request.contextPath}/B_BIL/css/b_bil.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   		<title></title>
   		<script type="text/javascript">
		$(document).ready(function(){
   			function fncValidation(){
   				var description = $("textarea[name='itemDesc']");
   				
   				if(description.val().length <= 0){
   					$("#toMandatoryHidden").css("display", "inline");
   					return false;
   				} else {
   					$("#toMandatoryHidden").css("display", "none");
   					return true;
   				}
   			}
   			
   			function fncSave(){
   				if(!fncValidation()){
   					return;
   				}
   				var idRef = $("input[name='idRef']").val();
   				var itemId = $("input[name='itemId']").val();
   				var description = $("textarea[name='itemDesc']").val();
   				
   				window.opener.setNotItemDesc(idRef, itemId, description);
   				window.close();
   			}
   			
   			$("#btnSave").bind("click", fncSave);
   			
   			function fncExit(){
   				window.close();
   			}
   			
   			$("#btnExit").bind("click", fncExit);
   		});
   		</script>
	</head>
	<ts:body>
	
		<div id="contentDiv">
			<table class="subHeader" cellpadding="0" cellspacing="0">
	    		<tr>
	    			<td><bean:message key="screen.b_bil.title3"/></td>
	    		</tr>
	    	</table>
	    	
	    	<table style="margin-top:0.05in;background-color:#8CB0F8;border-collapse:collapse;width:100%;height:30px;" cellpadding="0" cellspacing="0">
		   		<tr>
		   			<td>&nbsp;</td>
		   		</tr>
		   	</table>
		   	<table style="width:100%;border:1px solid #8CB0F8;margin-top:0.05in;background-color:#FFFFCC;" cellpadding="0" cellspacing="0">
		   		<tr>
		   			<td style="padding:20px 50px;">
		   				<table style="width:100%;" cellpadding="0" cellspacing="0">
		   					<tr>
		   						<td style="width:10%;"><bean:message key="screen.b_bil.description"/><span style="color:Red;">*</span></td>
		   						<td>:</td>
		   					</tr>
		   					<tr>
		   						<td colspan="2">
		   							<html:hidden name="_b_bilForm2" property="idRef" />
		   							<html:hidden name="_b_bilForm2" property="itemId" />
		   							<html:textarea name="_b_bilForm2" property="itemDesc" styleClass="itemDesc" style="width:480px;height:40px;font-size:14px;overflow-y:visible;" />
		   						</td>
		   					</tr>
		   				</table>
					</td>
		   		</tr>
		   	</table>
		   	
		   	<table style="width:100%;margin-top:20px;" cellpadding="0" cellspacing="0">
		   		<tr>
		   			<td>
		   				<input type="button" id="btnSave" value='<bean:message key="screen.b_bil.btnSave"/>' style="width:80px;" />
		   				<input type="button" id="btnExit" value='<bean:message key="screen.b_bil.btnExit"/>' style="width:80px;"/>
		   			</td>
		   		</tr>
		   	</table>
		   	
		   	<div id="toMandatoryHidden" style="display: none" class="error">
				<bean:message key="errors.ERR1SC005" arg0="Description"/>
			</div>	
		</div>
	</ts:body>
</html:html>