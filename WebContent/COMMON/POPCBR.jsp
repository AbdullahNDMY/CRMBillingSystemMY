<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%> 
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<html>
<head>
	<base target="_self"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
	<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
	<meta content="">
	<title>POPTER</title>
	<link type="text/css" rel="stylesheet" href="css/popup.css"/>
   	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   	<script type="text/javascript" src="js/popup.js"></script>
   	<script type="text/javascript">
   		function initData(){
   			//param
   			var param = window.dialogArguments;
   			//date
   			var tDate = param.tDate;
   			if(tDate!==null&&tDate!==""){
   				document.getElementById("tDate").value = tDate;
   				document.getElementById("remark").value = param.remark;
   			}else{
   				initDate("tDate");
   			}
   			document.getElementById("hidTransDatePop").value = param.hidTransDate;
   			document.getElementById("isCheckMulCharFlg").value = param.isCheckMulCharFlg;
   			//Reject button
   			if (param.type=="1") {
   				document.getElementById("labUnRejectInfo1").style.display="inline";
   				document.getElementById("labUnRejectInfo2").style.display="inline";
   				document.getElementById("labRejectInfo1").style.display="none";
   				document.getElementById("labRejectInfo2").style.display="none";
   			} else {
   				//Rejected Hyperlink
   				document.getElementById("labUnRejectInfo1").style.display="none";
   				document.getElementById("labUnRejectInfo2").style.display="none";
   				document.getElementById("labRejectInfo1").style.display="inline";
   				document.getElementById("labRejectInfo2").style.display="inline";
   			}
   		}
   		function initDate(e) {
   			var date = new Date();
   			var day = date.getDate();
   			var month = date.getMonth() + 1;
   			var year = date.getFullYear();
   			if((day+"").length===1){
   				day = "0"+day;
   			}
   			if((month+"").length===1){
   				month = "0"+month;
   			}
   			document.getElementById(e).value = (day + "/" + month + "/" + year);
   		}
   	</script>
</head>
<body class="popup" onload="initData()">
<form action="#">
<input type="hidden" name="hidTransDatePop" id="hidTransDatePop" value=""/>
<input type="hidden" name="isCheckMulCharFlg" id="isCheckMulCharFlg"/>
<table border="0" cellpadding="0" cellspacing="2" class="popup" style="margin-top:20px;margin-bottom:5px;margin-left:20px;">
	<tr>
		<td valign="top">
			<img src="./images/alert.png" width="32" height="32"/>
		</td>
		<td>
			Alert<br/>
			<label id="labUnRejectInfo1">
			You are about to reject a cash book.<br/>
			</label>
			<label id="labRejectInfo1">
			This is a rejected cashbook.Enter new rejection date and remark below.<br/>
			</label>
			Please note that rejection date cannot be earlier than cash<br/>
			book transaction date.<br/>
			Please key in rejection date:
				<input type="text" name="tDate" id="tDate" style="width:70px;" readonly="readonly" onchange="checkEmpty(this)">
				<t:inputCalendar for="tDate" format="dd/MM/yyyy"/><br/>
				Remark:<input type="text" name="remark" id="remark" style="width:215px;" maxlength="300"/><br/>
		    <label id="labUnRejectInfo2">
			Are you sure to reject?<br/>
			</label>
			<label id="labRejectInfo2">
			Are you sure to edit reject date and remark?<br/>
			</label>
			Click Yes to Save, No to abort rejection.<br/>
			<br/>
			<br/>
			<div style="color:red;">
				<label id="errorInfo"></label>
			</div>
			<br/>
			<input type="button" name="btnYes" value="Yes" onclick="javascript: doPoprejYes();" style="width:70px;"/>
			<input type="button" name="btnNo" value="No" onclick="javascript: doPoprejNo();" style="width:70px;"/>
		</td>
	</tr>
</table>
</form>
</body>
</html>