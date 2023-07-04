<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="/WEB-INF/bs" prefix="bs" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
	<head>
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
		<link href="${pageContext.request.contextPath}/B_BIL/css/b_bil.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/B_BIL/js/numberToWord.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/B_BIL/js/B_BIL_S02_02.js"></script>
		<title><bean:message key="screen.b_bil_s01.title"/></title>
		<script type="text/javascript">
			$(document).ready(function() {
				//onload function
				var screenWidth = window.screen.width;
				var contentDivWidth = screenWidth-270;
				// set page width
				$('#contentDiv').css("width",contentDivWidth);
				var termDay = $("input[name= 'headerData.termDays']").val();
		   		if (termDay!="0")
		   		{
		   			$("input[name= 'headerData.term']").val(termDay + " Days");
		   			$("input[name= 'headerData.term']").attr("disabled", true);
		   		}
		   		else
		   		{
		   			var bilType = "${_b_bilForm.map.headerInfo.BILL_TYPE}";
		   			if(bilType=='CN'){
		   				$("#termDays1").attr("disabled", true);
		   				$("#term1").attr("disabled", true);
		   			}else{
		   				$("input[name= 'headerData.term']").attr("disabled", false);
		   			}
		   			
		   		}

				if(navigator.userAgent.indexOf("MSIE")>0)
				{document.getElementById('dateReq').attachEvent("onpropertychange",dueDateValue); 
				} 
				else 
				{
				document.getElementById('dateReq').addEventListener("click",dueDateValue,false); 
				} 
			});
			function openB_CPM_View(url) {
				var width = window.screen.width*90/100;
			    var height = window.screen.height*80/100;
			    var left = Number((screen.availWidth/2) - (width/2));
			    var top = Number((screen.availHeight/2) - (height/2));
			    var offsetFeatures = "width=" + width + ",height=" + height +
			    					 ",left=" + left + ",top=" + top +
			    					 "screenX=" + left + ",screenY=" + top;
				var strFeatures= "toolbar=no, status=no, menubar=no,location=no,scrollbars=yes, resizable=yes" + "," + offsetFeatures;	 	
				var newwindow = window.open(url, null, strFeatures);
				if (window.focus) { newwindow.focus(); }
			}
			function onSave()
			{
				setAddressValue();
				var cusPo=$("#custPoid").val();
				if(cusPo.indexOf("&")!=-1)
				{
					showMsg();
				}else{				
					$('form').submit();
				}				 
			}
			function showMsg(){
	            var message = $("#ERR107").val();
	            message=message.replace('{0}', "Customer PO").replace('{1}', "Customer PO");
	            var MsgBox = new messageBox();
	            MsgBox.POPALT(message);
	            return false;
	      }
			
			function ClickExit(){				
				var MsgBox = new messageBox();
				if (MsgBox.POPEXT($('#hiddenMsgExitConfirmation').val()) == MsgBox.YES_CONFIRM) {
					window.location.href="<%=request.getContextPath()%>/B_BIL/RP_B_BIL_S02_01BL.do?idRef=${_b_bilForm.map.headerInfo.ID_REF}&mode=view&fromPageFlag=BIL";
			}
}
			function termDayFun(){
		  		var termDay = $("input[name= 'headerData.termDays']").val();
		  		
		  			if (termDay!="0")
			   		{
		  				if(!isNaN(termDay)){
		  					if(termDay==""){
			   					$("input[name= 'headerData.term']").val("");
			   				}else{
			   					$("input[name= 'headerData.term']").val(termDay + " Days");
			   				}
			   				$("input[name= 'headerData.term']").attr("disabled", true);
			   				if(termDay.length == "0"){
			   					$("input[name= 'headerData.term']").val("");
			   				}
			   				dueDateValue();
			   			}
		  			}
			   		else
			   		{
			   			$("input[name= 'headerData.term']").attr("disabled", false);
			   			$("input[name= 'headerData.term']").val("");
			   			dueDateValue();
			   		}
		   		
		  	}
			function dueDateValue(){
				var termDay = $("input[name= 'headerData.termDays']").val();
				var dateReq = $("input[name= 'headerData.dateReq']").val();
	   			var arys= dateReq.split('/');
	   			var y= parseInt(arys[2], 10);
			    var m=parseInt(arys[1], 10)-1;
			    var da=parseInt(arys[0], 10);
				var d = new Date(y,m,da);
				if(termDay!=""){
					d.setDate(d.getDate() + parseInt(termDay)); 
				}
				var Year=0;
			    var Month=0;
			    var Day=0;
			    Year = d.getYear();
			    Month = (d.getMonth()+1).toString();
			    Day = d.getDate();
			    CurrentDate = "";
			    if (Day >= 10 )
			    {
			        CurrentDate = CurrentDate + Day + "/";
			    }
			    else
			    {
			        CurrentDate = CurrentDate + "0" + Day + "/";
			    }
			    if (Month >= 10 )
			    {
			        CurrentDate = CurrentDate + Month + "/";
			    }
			    else
			    {
			        CurrentDate = CurrentDate + "0" + Month + "/";
			    }
			    
			    $("input[name= 'headerData.dueDate']").val(CurrentDate+Year);
			}
			function termDayValue(){
				var termDay = $("input[name= 'headerData.termDays']").val();
		  		if(termDay.length == "0"){
					$("input[name= 'headerData.termDays']").val("0");
				}
		  	}
			function changeAdr(obj){
				var selectval=obj;
				if(document.getElementById("adrBA") != undefined) {
					document.getElementById("adrBA").style.display = 'none';
				}
				
				if(document.getElementById("adrBA2") != undefined) {
					document.getElementById("adrBA2").style.display = 'none';
				}
				
				if(document.getElementById("adrBA3") != undefined) {
					document.getElementById("adrBA3").style.display = 'none';
				}
				
				if(document.getElementById("adrBA4") != undefined) {
					document.getElementById("adrBA4").style.display = 'none';
				}
				
				if(document.getElementById("adrCA") != undefined) {
					document.getElementById("adrCA").style.display = 'none';
				}
				
				if(document.getElementById("adrRA") != undefined) {
					document.getElementById("adrRA").style.display = 'none';
				}
				
				if(document.getElementById("adrTA") != undefined) {
					document.getElementById("adrTA").style.display = 'none';
				}
				
				document.getElementById("adrEmpty").style.display = 'none';
				if(document.getElementById("adr" + selectval) != undefined) {
					document.getElementById("adr" + selectval).style.display = '';
				} else {
					document.getElementById("adrEmpty").style.display = '';			
				}
				//document.forms[0].headerInfo.ADR_TYPE.value = selectval;
				
			}
			function init() {
				changeAdr('${_b_bilForm.map.headerInfo.ADR_TYPE}');
			}
			
			function setTelFax(obj){
				var selvalue=obj.options[obj.selectedIndex].value;
				var tel = obj.options[obj.selectedIndex].tel_no;
				var fax = obj.options[obj.selectedIndex].fax_no;
				var emailto = obj.options[obj.selectedIndex].email_to;
				var emailcc = obj.options[obj.selectedIndex].email_cc;
				if(selvalue==""||selvalue==null||selvalue==undefined){
				}else{
					document.getElementById("telnumber").innerHTML = tel;
					document.getElementById("faxnumber").innerHTML = fax;
					document.getElementById("emailto").innerHTML = formatEmailAdr(emailto);
					document.getElementById("emailcc").innerHTML = formatEmailAdr(emailcc);
				}
			}
			function formatEmailAdr(emailAdr){
				var emailInnerHtml = "";
				if (emailAdr.indexOf(";") >= 0) {
					var emailAdrs = emailAdr.split(";");
					for(var j = 0; j < emailAdrs.length; j++) {
						if (j != emailAdrs.length - 1) {
							emailInnerHtml += emailAdrs[j] + ";" + "<br/>";
						} else {
							emailInnerHtml += emailAdrs[j];
						}
					}
				} else {
					emailInnerHtml = emailAdr;
				}
				return emailInnerHtml;
			}
			function setAddressValue(){
				var addrSel=$("#addrSel").find("option:selected").val();
				var tb=$("#adr" + addrSel);
				var addr1=tb.find("tr").eq(0).find("td").html();
				var addr2=tb.find("tr").eq(1).find("td").html();
				var addr3=tb.find("tr").eq(2).find("td").html();
				var addr4=tb.find("tr").eq(3).find("td").html();
				var telno=$("#telnumber").text();
				var faxno=$("#faxnumber").text();
				var emailto=$("#emailto").text();
				var emailcc=$("#emailcc").text();
				$(".arrline1").val(addr1);
				$(".arrline2").val(addr2);
				$(".arrline3").val(addr3);
				$(".arrline4").val(addr4);
				$(".telNo").val(telno);
				$(".faxNo").val(faxno);
				$(".emailToAdd").val(emailto);
				$(".emailCcAdd").val(emailcc);
				if(addr4.indexOf(",")!=-1){
					var zipcode=addr4.split(",")[0];
					var country=addr4.split(",")[1];
					$(".zipCode").val(zipcode);
					$(".country").val(country);
				}
				$(".contactType").val($("#attnSelect").find("option:selected").val());
				$(".contactName").val($("#attnSelect").find("option:selected").attr("contact_name"));
			}
			function emailCheck(obj){
				if(obj.checked){
					obj.value = "1";
				}
				else{
					obj.value="0";
				}
			}
		</script>
	</head>
	<ts:body onload="javascript:init();">
		<ts:form action="/RP_B_BIL_S02_02_02BL">
			<div id="contentDiv">
			<t:defineCodeList id="LIST_PAYMENT_METHOD"/>
			<h1 class="title">
				<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
					<bean:message key="screen.b_bil.creditNote"/>
				</c:if>
				<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'IN'}">
					<bean:message key="screen.b_bil.invoiceNote"/>
				</c:if>
				<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'DN'}">
					<bean:message key="screen.b_bil.debitNote"/>
				</c:if>
			</h1>
			<!-- Add #156 Start-->
			<html:hidden property="billCnAmtNegative"></html:hidden>
			<!-- Add #156 End-->
			<table class ="header1" cellpadding="0" cellspacing="0">
				<tr>
					<td class="col1Top" width="40%" valign="top">
						<table align="left">
							<tr>
								<td>
									<font size="4px" style="headerInfo">
										<b><bean:message key="screen.b_bil.customerDetailsHeader"/>&nbsp;</b>
									</font>			
								</td>
								<td align="right">
									<bean:message key="screen.b_bil.customerID"/>
									<bean:message key="screen.b_bil.s04.colon"/>
								</td>
								<td>
									${_b_bilForm.map.headerInfo.ID_CUST}
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.customerName"/>
									<bean:message key="screen.b_bil.colon"/>
								</td>
								<td>
									${_b_bilForm.map.headerInfo.CUST_NAME}
								</td>
								<td></td>
							</tr>
							<tr>
								<td align="right" valign="top">
									<%-- <t:writeCodeValue codeList="LIST_ADDRESS" key="${_b_bilForm.map.headerInfo.ADR_TYPE}"/> --%>
									<t:defineCodeList id="LIST_ADDRESS"/>
									<html:select property="headerData.adrType" styleId="addrSel" onchange="changeAdr(this.options[this.selectedIndex].value)">
					  	 				<!-- #190 Start -->
										<%-- <c:forEach items="${LIST_ADDRESS}" var="item">
											<c:if test="${_b_bilForm.map.headerInfo.ADR_TYPE eq item.id}">
												 <option value="${item.id}" selected="selected">${item.name}</option>
											</c:if>
											<c:if test="${_b_bilForm.map.headerInfo.ADR_TYPE != item.id}">
												<option value="${item.id}">${item.name}</option>
											</c:if>
										</c:forEach> --%>
										<c:if test="${_b_bilForm.map.headerInfo.CUSTOMER_TYPE eq 'CONS'}">
											<c:forEach items="${LIST_ADDRESS}" var="item">
												<c:if test="${item.id eq 'RA' or item.id eq 'BA'}">
													<c:if test="${_b_bilForm.map.headerInfo.ADR_TYPE eq item.id}">
														<option value="${item.id}" selected="selected">${item.name}</option>
													</c:if>
													<c:if test="${_b_bilForm.map.headerInfo.ADR_TYPE ne item.id}">
														<option value="${item.id}">${item.name}</option>
													</c:if>
												</c:if>
											</c:forEach>
										</c:if>
										<c:if test="${_b_bilForm.map.headerInfo.CUSTOMER_TYPE ne 'CONS'}">
											<c:forEach items="${LIST_ADDRESS}" var="item">
												<c:if test="${_b_bilForm.map.headerInfo.ADR_TYPE eq item.id}">
													<option value="${item.id}" selected="selected">${item.name}</option>
												</c:if>
												<c:if test="${_b_bilForm.map.headerInfo.ADR_TYPE ne item.id}">
													<option value="${item.id}">${item.name}</option>
												</c:if>
											</c:forEach>
										</c:if>
										<!-- #190 End -->
					  	 		    </html:select>
								</td>
								<td>
									<html:hidden name="_b_bilForm" property="headerData.address1" styleClass="arrline1"/>
									<html:hidden name="_b_bilForm" property="headerData.address2" styleClass="arrline2"/>
									<html:hidden name="_b_bilForm" property="headerData.address3" styleClass="arrline3"/>
									<html:hidden name="_b_bilForm" property="headerData.address4" styleClass="arrline4" />
									<html:hidden name="_b_bilForm" property="headerData.telNo" styleClass="telNo"/>
									<html:hidden name="_b_bilForm" property="headerData.faxNo" styleClass="faxNo"/>
									<html:hidden name="_b_bilForm" property="headerData.zipCode" styleClass="zipCode"/>
									<html:hidden name="_b_bilForm" property="headerData.country" styleClass="country"/>
									<html:hidden name="_b_bilForm" property="headerData.emailToAdd" styleClass="emailToAdd"/>
									<html:hidden name="_b_bilForm" property="headerData.emailCcAdd" styleClass="emailCcAdd"/> 
									<html:hidden name="_b_bilForm" property="headerData.contactType" styleClass="contactType"/>
									<html:hidden name="_b_bilForm" property="headerData.contactName" styleClass="contactName"/> 
									<!--Billing Address  -->
									<logic:iterate id="item" name="_b_bilForm" property="headerInfo.cusAdr" scope="request">
		  	 						<table id='adr<bean:write name="item" property="ADR_TYPE"/>'>
		  	 							<tr>
					  	 					<td class = "tdright">
					  	 						<bean:write name="item" property="ADR_LINE1"/>
					  	 					</td>
					  	 				</tr>
									 	 <tr>
									  	 	<td class = "tdright">
									  	 		<bean:write name="item" property="ADR_LINE2"/>
									  	 	</td>
									  	 </tr>
									  	 <tr>
									  	 	<td class = "tdright">
									  	 		<bean:write name="item" property="ADR_LINE3"/>
									  	 	</td>
									  	 </tr>
									  	<tr>
									  	 	<td class = "tdright">
									  	 		<c:if test="${not empty item.ZIP_CODE or not empty item.COUNTRY}">
									  	 		<bean:write name="item" property="ZIP_CODE"/>,<t:writeCodeValue codeList="LIST_COUNTRY" key="${item.COUNTRY}"/>
									  	 		</c:if>
									  	 	</td>
									  	 </tr>
		  	 						</table>
		  	 						</logic:iterate>
						  	 		<table id="adrEmpty">
						  	 			<tr>
									  	 	<td class = "tdright">&nbsp;</td>
									  	 </tr>
									 	 <tr>
									  	 	<td class = "tdright">&nbsp;</td>
									  	 </tr>
									  	 <tr>
									  	 	<td class = "tdright">&nbsp;</td>
									  	 </tr>
									  	 <tr>
									  	 	<td class = "tdright">&nbsp;</td>
									  	 </tr>
						  	 		</table>
								</td>
								<td></td>
							</tr>
							<%-- <tr>
								<td align="right">
									&nbsp;
								</td>
								<td>
									<bean:message key="screen.b_bil.addBlank"/>
									${_b_bilForm.map.headerInfo.ADDRESS2}
									<html:hidden property="headerData.address2" value="${_b_bilForm.map.headerInfo.ADDRESS2}"/>
								</td>
							</tr>
							<tr>
								<td align="right">
									&nbsp;
								</td>
								<td>
									<bean:message key="screen.b_bil.addBlank"/>
									${_b_bilForm.map.headerInfo.ADDRESS3}
									<html:hidden property="headerData.address3" value="${_b_bilForm.map.headerInfo.ADDRESS3}"/>
								</td>
							</tr>
							<tr>
								<td align="right">
									&nbsp;
								</td>
								<td>
									<bean:message key="screen.b_bil.addBlank"/>
									${_b_bilForm.map.headerInfo.ADDRESS4}
									<html:hidden property="headerData.address4" value="${_b_bilForm.map.headerInfo.ADDRESS4}"/>
									<html:hidden property="headerData.zipCode" value="${_b_bilForm.map.headerInfo.ZIP_CODE}"/>
									<html:hidden property="headerData.country" value="${_b_bilForm.map.headerInfo.COUNTRY}"/>
								</td>
							</tr> --%>
							<tr>
								<td align="right">	
									<bean:message key="screen.b_bil.tel"/>
									<bean:message key="screen.b_bil.colon"/>
								</td>
								<td>
									<label id="telnumber">
										${_b_bilForm.map.headerInfo.TEL_NO}
									</label>
								</td>
								<td></td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.fax"/>
									<bean:message key="screen.b_bil.colon"/>
								</td>
								<td>
									<label id="faxnumber">
										${_b_bilForm.map.headerInfo.FAX_NO}
									</label>
								</td>
								<td></td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.attn"/>
									<bean:message key="screen.b_bil.colon"/>
								</td>
								<td>
									<%-- ${_b_bilForm.map.headerInfo.CONTACT_NAME}
									<html:hidden property="headerData.contactName" value="${_b_bilForm.map.headerInfo.CONTACT_NAME}"/> --%>
									<c:if test="${_b_bilForm.map.headerInfo.CUSTOMER_TYPE eq 'CORP'}">
									<html:select name="_b_bilForm" property="headerInfo.CONTACT_TYPE" onchange="setTelFax(this);" styleId="attnSelect">
						  	 			<c:forEach items="${_b_bilForm.map.headerInfo.attns}" var="item">
						  	 				<c:if test="${_b_bilForm.map.headerInfo.CONTACT_TYPE == item.CONTACT_TYPE}">
						  	 					<option value="${item.CONTACT_TYPE}" selected="selected" contact_name="${item.CONTACT_NAME_VALUE}" tel_no="${item.TEL_NO}" fax_no="${item.FAX_NO}" email_to="${item.EMAIL}" email_cc="${item.EMAIL_CC}">${item.CONTACT_NAME}</option>
						  	 				</c:if>
						  	 				<c:if test="${_b_bilForm.map.headerInfo.CONTACT_TYPE != item.CONTACT_TYPE}">
						  	 					<option value="${item.CONTACT_TYPE}" contact_name="${item.CONTACT_NAME_VALUE}" tel_no="${item.TEL_NO}" fax_no="${item.FAX_NO}" email_to="${item.EMAIL}" email_cc="${item.EMAIL_CC}">${item.CONTACT_NAME}</option>
						  	 				</c:if>
						  	 			</c:forEach>
					  	 			</html:select>
					  	 			</c:if>
					  	 			<c:if test="${_b_bilForm.map.headerInfo.CUSTOMER_TYPE eq 'CONS'}">
			  	 	    				<select name="cboAttn_temp" disabled="disabled">
			  	 							<option value=""><bean:message key="screen.b_bil.blankItem"/></option>
			  	 						</select>
			  	 					</c:if>
								</td>
								<td></td>
							</tr>
							<%-- <tr>
								<td align="right">
									<bean:message key="screen.b_bil.email"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									${_b_bilForm.map.headerInfo.CONTACT_EMAIL}
									<html:hidden property="headerData.contactEmail" value="${_b_bilForm.map.headerInfo.CONTACT_EMAIL}"/>
								</td>
							</tr> --%>
							<tr>
						  	 	<td align="right" style="text-align:right">
						  	 		<bean:message key="screen.b_bil.emailto"/>
						  	 		<bean:message key="screen.b_bil.colon"/>
						  	 	</td>
						  	 	<td>
						  	 		<label id="emailto">
						  	 		<c:if test="${_b_bilForm.map.headerInfo.CUSTOMER_TYPE eq 'CORP'}">
						  	 			<logic:iterate id="attns" name="_b_bilForm" property="headerInfo.attns">
											<logic:equal name="_b_bilForm" property="headerInfo.CONTACT_TYPE" value="${attns.CONTACT_TYPE}">
												<c:forTokens items="${attns.EMAIL}" delims=";" var="email" varStatus="vs">
													<c:if test="${!vs.last}">
														<c:out value="${email}"/>;<br/>
													</c:if>
													<c:if test="${vs.last}">
														<c:out value="${email}"/>
													</c:if>
												</c:forTokens>
												
											</logic:equal>
						  	 			</logic:iterate>
						  	 		</c:if>
						  	 		<c:if test="${_b_bilForm.map.headerInfo.CUSTOMER_TYPE eq 'CONS'}">
						  	 			${_b_bilForm.map.headerInfo.CONTACT_EMAIL}
						  	 			<html:hidden property="headerData.contactEmail" value="${_b_bilForm.map.headerInfo.CONTACT_EMAIL}"/>
						  	 		</c:if>	
						  	 		</label>
						  	 	</td>
						  	 	<td></td>
		  	 				</tr> 
						  	 <tr>
						  	 	<td class = "tdleft" style="text-align:right">
						  	 		<bean:message key="screen.b_bil.emailcc"/>
						  	 		<bean:message key="screen.b_bil.colon"/>
						  	 	</td>
						  	 	<td class = "tdright">
						  	 		<label id="emailcc">
						  	 			<logic:iterate id="attns" name="_b_bilForm" property="headerInfo.attns">
											<logic:equal name="_b_bilForm" property="headerInfo.CONTACT_TYPE" value="${attns.CONTACT_TYPE}">
												<c:forTokens items="${attns.EMAIL_CC}" delims=";" var="email" varStatus="vs">
													<c:if test="${!vs.last}">
														<c:out value="${email}"/>;<br/>
													</c:if>
													<c:if test="${vs.last}">
														<c:out value="${email}"/>
													</c:if>
												</c:forTokens>
											</logic:equal>
						  	 			</logic:iterate>
						  	 		</label>
						  	 	</td>
						  	 	<td></td>
						  	 </tr>
						</table>
					</td>
					<td class="col2Top" width="1%" style="background:white">
						&nbsp;
					</td>
					<td class="col3Top" width="39%" valign="top">
						<table>
							<tr>				
							</tr>
							<tr>
								<td align="right">
									<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
										<bean:message key="screen.b_bil.creditNote"/>
									</c:if>
									<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'IN'}">
										<bean:message key="screen.b_bil.invoiceNote"/>
									</c:if>
									<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'DN'}">
										<bean:message key="screen.b_bil.debitNote"/>
									</c:if>
									<bean:message key="screen.b_bil.date"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									<%-- <html:text property="headerData.dateReq" 
									value="${_b_bilForm.map.headerInfo.DATE_REQ_FOM}" readonly="true" onchange="dueDateValue()"/> --%>
									<input type="text" name="headerData.dateReq" id="dateReq" value="${_b_bilForm.map.headerInfo.DATE_REQ_FOM}" readonly="readonly" />
									<t:inputCalendar for="headerData.dateReq" format="dd/MM/yyyy"/>
								</td>
							</tr>
							<tr>
								<td align="right">
									<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
										<bean:message key="screen.b_bil.creditNote"/>
									</c:if>
									<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'IN'}">
										<bean:message key="screen.b_bil.invoiceNote"/>
									</c:if>
									<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'DN'}">
										<bean:message key="screen.b_bil.debitNote"/>
									</c:if>
									<bean:message key="screen.b_bil.billingNo"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									${_b_bilForm.map.headerInfo.ID_REF}
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.billingAccountNo"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									${_b_bilForm.map.headerInfo.BILL_ACC}
									
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.paymentMethod"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									<t:writeCodeValue codeList="LIST_PAYMENT_METHOD" key="${_b_bilForm.map.headerInfo.PAY_METHOD}"/>
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.qcsReference"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									<html:text property="headerData.idQcs" 
									value="${_b_bilForm.map.headerInfo.ID_QCS_TXT}" readonly="false"/>
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.quoReference"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									<html:text property="headerData.quoRef" 
									value="${_b_bilForm.map.headerInfo.QUO_REF_TXT}" readonly="false"/>
								</td>
							</tr>
							<tr>
								<td align="right">	
									<bean:message key="screen.b_bil.customerPO"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									<html:text property="headerData.custPo" styleId="custPoid" maxlength="30"
									value="${_b_bilForm.map.headerInfo.CUST_PO_TXT}" readonly="false"/>
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.acManager"/>
									
								</td>
								<td>
								    <bean:message key="screen.b_bil.colon"/>&nbsp;
								    <html:select property="headerData.idConsult" value="${_b_bilForm.map.headerInfo.ID_CONSULT_NAME}" styleId="cboAcManager">
										<html:option value="" ><bean:message key="screen.b_bil.blankItem"/></html:option>
										<html:optionsCollection name="_b_bilForm" property="headerInfo.LIST_AC_MANAGER" label="label" value="value"/>
									</html:select>
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.currency"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									${_b_bilForm.map.headerInfo.BILL_CURRENCY}
								</td>
							</tr>
							<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE ne 'CN'}">
								<tr>
									<td align="right">
										<bean:message key="screen.b_bil.term"/>
									</td>
									<td>
										<bean:message key="screen.b_bil.colon"/>&nbsp;
										<html:text property="headerData.termDays" 
									     value="${_b_bilForm.map.headerInfo.TERM_DAY}" readonly="false" style="width:40px;" maxlength="3" onkeyup="termDayFun();this.value=this.value.replace(/\D/g,'')" onblur="termDayValue()"/>
									     <html:text property="headerData.term" 
									     value="${_b_bilForm.map.headerInfo.TERM}" readonly="false" style="width:115px;" maxlength="15"/>
									</td>
								</tr>
							</c:if>
							<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
								<tr>
									<td align="right">
										<bean:message key="screen.b_bil.term"/>
									</td>
									<td>
										<bean:message key="screen.b_bil.colon"/>&nbsp;
										<input id="termDays1" value="${_b_bilForm.map.headerInfo.TERM_DAY}" disabled="disabled" style="width:40px;"/>
										<input id="term1" value="${_b_bilForm.map.headerInfo.TERM}" disabled="disabled" style="width:115px;"/>
										<%-- <html:text property="headerData.termDays" 
									     value="${_b_bilForm.map.headerInfo.TERM_DAY}" disabled="true" style="width:40px;" maxlength="3"/>
									     <html:text property="headerData.term" 
									     value="${_b_bilForm.map.headerInfo.TERM}" disabled="true" style="width:115px;" maxlength="15"/> --%>
									      <html:hidden styleId="termDays" value="${_b_bilForm.map.headerInfo.TERM_DAY}" property="headerData.termDays"/>
									      <html:hidden styleId="term" value="${_b_bilForm.map.headerInfo.TERM}" property="headerData.term"/>
									</td>
								</tr>
							</c:if>
							<tr>
								<td align="right">
									<bean:message key="screen.b_bil.DueDate"/>
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>&nbsp;
									<html:text property="headerData.dueDate" 
									     value="${_b_bilForm.map.headerInfo.DUE_DATE_FOM}" readonly="true" style="border:0;background:none;"/>
								</td>
							</tr>
							<%-- <c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'IN' or _b_bilForm.map.headerInfo.BILL_TYPE eq 'DN'}">
								<tr>
									<td colspan="2">
										&nbsp;
									</td>
								</tr>
							</c:if> --%>
							<tr>
								<td align="right" valign="top">
									<bean:message key="screen.b_bil.deliveryby"/>
									
								</td>
								<td>
									<bean:message key="screen.b_bil.colon"/>
									<c:if test="${_b_bilForm.map.headerInfo.DELIVERY_EMAIL == 1}">
	   									<input type="checkbox" name="headerData.deliverymail" value="1" checked onclick="emailCheck(this);">
	   								</c:if>
								   <c:if test="${_b_bilForm.map.headerInfo.DELIVERY_EMAIL != 1}">
								   		<input type="checkbox" name="headerData.deliverymail" value="0" onclick="emailCheck(this);">
								   </c:if>
								   <bean:message key="screen.b_bil.email"/>
									<c:if test="${_b_bilForm.map.headerInfo.DELIVERY == '3'}">
									  	<input type="radio" name="headerData.delivery" value="3" checked="checked"/>
									  	<bean:message key="screen.b_bil.none"/>
								  	</c:if>
								  	<c:if test="${_b_bilForm.map.headerInfo.DELIVERY != '3'}">
									  	<input type="radio" name="headerData.delivery" value="3"/>
									  	<bean:message key="screen.b_bil.none"/>
								  	</c:if>	 
								  	<c:if test="${_b_bilForm.map.headerInfo.DELIVERY == '2'}">
									  	<input type="radio" name="headerData.delivery" value="2" checked="checked"/>
									  	<bean:message key="screen.b_bil.courier"/>
								  	</c:if>
								  	<c:if test="${_b_bilForm.map.headerInfo.DELIVERY != '2'}">
									  	<input type="radio" name="headerData.delivery" value="2"/>
									  	<bean:message key="screen.b_bil.courier"/>
								  	</c:if>
								  	<br/>
	                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<c:if test="${_b_bilForm.map.headerInfo.DELIVERY == '1'}">
									  	<input type="radio" name="headerData.delivery" value="1" checked="checked"/>
									  	<bean:message key="screen.b_bil.post"/>
								  	</c:if>
								  	<c:if test="${_b_bilForm.map.headerInfo.DELIVERY != '1'}">
									  	<input type="radio" name="headerData.delivery" value="1"/>
									  	<bean:message key="screen.b_bil.post"/>
								  	</c:if>&nbsp;
									<c:if test="${_b_bilForm.map.headerInfo.DELIVERY == '4'}">
									  	<input type="radio" name="headerData.delivery" value="4" checked="checked"/>
									  	<bean:message key="screen.b_bil.byhand"/>	  
								  	</c:if>
								  	<c:if test="${_b_bilForm.map.headerInfo.DELIVERY != '4'}">
									  	<input type="radio" name="headerData.delivery" value="4"/>
									  	<bean:message key="screen.b_bil.byhand"/>
								  	</c:if>
								</td>	
							</tr>
						</table>
					</td>
					<td class="col4Top" width="1%" style="background:white">
						&nbsp;
					</td>
					<td class="col5Top" width="20%" rowspan="15" valign="top" align="center" style="background-color:white">
						<div style="background-color:#ccccff;height=50%;align: center;">
							<font class="statusBold">
								<bean:message key="screen.b_bil.status"/>
							</font>
							<br/>
							<c:if test="${_b_bilForm.map.headerInfo.IS_CLOSED eq '1'}">
								<bean:message key="screen.b_bil.closed"/>/
							</c:if>
							<c:if test="${_b_bilForm.map.headerInfo.IS_CLOSED ne '1'}">
								<bean:message key="screen.b_bil.open"/>/
							</c:if>
							<c:if test="${_b_bilForm.map.headerInfo.IS_SETTLED eq '1'}">
								<bean:message key="screen.b_bil.fullSettled"/>
							</c:if>
							<c:if test="${_b_bilForm.map.headerInfo.IS_SETTLED ne '1'}">
								<c:if test="${_b_bilForm.map.headerInfo.PAID_AMOUNT ne 0}">
									<bean:message key="screen.b_bil.partialSettled"/>
								</c:if>
								<c:if test="${_b_bilForm.map.headerInfo.PAID_AMOUNT eq 0}">
									<bean:message key="screen.b_bil.outstanding"/>
								</c:if>
							</c:if>
							<c:if test="${_b_bilForm.map.headerInfo.IS_DELETED eq '1'}">
								/<bean:message key="screen.b_bil.canceled"/>
							</c:if>
							<br>&nbsp;
							<br>&nbsp;
							<br>&nbsp;
						</div>
						<div  style="background-color:#ffcccc;height=50%;align: center;">
							<font class="preparedBy">
								<bean:message key="screen.b_bil.preparedBy"/>
							</font>
							<br/>
							<center>
								${_b_bilForm.map.headerInfo.PREPARED_BY_NAME}
							</center>
							<br/>
							<br/>	
							<font class="preparedBy">	
								<bean:message key="screen.b_bil.date"/>
							</font>
							<br/>
							<center>
								<fmt:formatDate value="${_b_bilForm.map.headerInfo.DATE_CREATED}" pattern="dd/MM/yyyy"/>
							</center>
							<br/>&nbsp;
							<br/>&nbsp;
							<br/>&nbsp;
							<br/>&nbsp;
						</div>
					</td>
				</tr>
			</table>
			<br/>
			<table cellspacing="0" cellpadding="0" style="width:100%;border-top: solid 2px #8cb0f8;border-bottom: solid 2px #8cb0f8;border-left: solid 2px #8cb0f8;border-right: solid 2px #8cb0f8;">
				<tr>
					<td>
						<table class="resultInfo1" cellpadding="0" cellspacing="0">
							<col width="6%"/>
							<col width="47%"/>
							<col width="10%"/>
							<col width="12%"/>
							<col width="15%"/>
							<col width="10%"/>
							<tr class="header">
								<td style="background-color:rgb(140,176,248)" width="6%" style="padding-left:10px;"><bean:message key="screen.b_bil.item"/></td>
								<td style="background-color:rgb(140,176,248)" width="47%"><bean:message key="screen.b_bil.billingDescription"/></td>
								<td style="background-color:rgb(140,176,248)" width="10%" style="text-align:right;"><bean:message key="screen.b_bil.quantity"/></td>
								<td style="background-color:rgb(140,176,248)" width="12%" style="text-align:right;"><bean:message key="screen.b_bil.unitPrice"/></td>
								<td style="background-color:rgb(140,176,248)" width="15%" style="text-align:right;"><bean:message key="screen.b_bil.totalAmount"/></td>
								<td style="background-color:rgb(140,176,248)" width="10%" style="padding-left:10px;">
									<c:if test="${'2' ne _b_bilForm.map.headerInfo.GSTAPPLYFLAG}">
								   		<bean:message key="screen.b_bil.GSTHeader"/>
								   	</c:if>
								   	<c:if test="${'2' eq _b_bilForm.map.headerInfo.GSTAPPLYFLAG}">
								   		<b>${taxStr}</b>
								   	</c:if>
								</td>
							</tr>
							<!-- Detail content -->
							<c:set var="subTotal" value="0"></c:set>
							<c:set var="taxableSubTotal" value="0"></c:set>
							<c:set var="nonTaxableSubTotal" value="0"></c:set>
							<c:set var="index" value="0"></c:set>
							<c:set var="indexSub" value="0"></c:set>
							<c:set var="lastItem" value=""></c:set>
							<c:set var="subDiscAmt" value="0"></c:set>
							<c:forEach items="${_b_bilForm.map.detailInfo}" var="item" varStatus="status">
								<c:choose>
									<c:when test="${item.ITEM_CAT eq '0'}">
										<c:if test="${not empty lastItem}">
											<c:if test="${lastItem.ITEM_CAT eq '1'}">
												<c:if test="${subDiscAmt != 0}">
													<tr>
														<td colspan="6">&nbsp;</td>
													</tr>
													<tr>
														<td valign="top">&nbsp;</td>
											 	        <td colspan="3">
											 	           <b><i><bean:message key="screen.b_bil.discount"/></i></b>
											 	        </td>
											 	        <td style="text-align:right;" valign="top">
											 	          <fmt:formatNumber value="${subDiscAmt}" pattern="#,##0.00"/>
											 	        </td>
											 	        <td valign="top" style="padding-left:10px;">
											 	        <c:if test="${_b_bilForm.map.gstCheck eq 'TAX_CODE'}">
															<c:choose>
															    <c:when test="${not empty lastItem.TAX_CODE}">
															  		${lastItem.TAX_CODE}
																</c:when>
																<c:otherwise>
																	&nbsp;
																</c:otherwise>
															</c:choose>
														</c:if>
														<c:if test="${_b_bilForm.map.gstCheck eq 'TAX_RATE'}">
															<c:choose>
															    <c:when test="${not empty lastItem.TAX_RATE}">
															  		${lastItem.TAX_RATE}%
																</c:when>
																<c:otherwise>
																	&nbsp;
																</c:otherwise>
															</c:choose>
														</c:if>
														</td>
										 	        <tr>
												</c:if>
												<c:choose>
													<c:when test="${lastItem.BILL_FROM_TEXT ne lastItem.BILL_TO_TEXT || lastItem.ISDISPLAY_BILLINGPERIOD eq '1'}">
														<tr>
															<td colspan="6">&nbsp;</td>
														</tr>
														<tr>
															<td>&nbsp;</td>
															<td colspan="5">
																<bean:message key="screen.b_bil.billingPeriod"/>
																<bean:message key="screen.b_bil.from"/>
																<bean:message key="screen.b_bil.colon1"/>
																${lastItem.BILL_FROM_TEXT}
																<bean:message key="screen.b_bil.toInfo"/>${lastItem.BILL_TO_TEXT}
															</td>
														</tr>
													</c:when>
												</c:choose>
												<tr>
													<td colspan="6">&nbsp;</td>
												</tr>
											</c:if>
										</c:if>
										<tr>
											<td>&nbsp;</td>
											<td style="width:470px;word-wrap: break-word;white-space : normal">
												<div style="color:#CD853F;">
													<pre>${item.ITEM_DESC_TEXT}</pre>
												</div>
											</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td colspan="6">&nbsp;</td>
										</tr>
										<c:set var="subDiscAmt" value="0"></c:set>
									</c:when>
									<c:otherwise>
										<c:choose> 	    	
											<c:when test="${item.ITEM_LEVEL eq '0'}">
												<c:if test="${not empty lastItem}">
													<c:if test="${lastItem.ITEM_CAT eq '1'}">
														<c:if test="${subDiscAmt != 0}">
															<tr>
																<td colspan="6">&nbsp;</td>
															</tr>
															<tr>
																<td valign="top">&nbsp;</td>
													 	        <td colspan="3">
													 	           <b><i><bean:message key="screen.b_bil.discount"/></i></b>
													 	        </td>
													 	        <td style="text-align:right;" valign="top">
													 	          <fmt:formatNumber value="${subDiscAmt}" pattern="#,##0.00"/>
													 	        </td>
													 	        <td valign="top" style="padding-left:10px;">
													 	        <c:if test="${_b_bilForm.map.gstCheck eq 'TAX_CODE'}">
																	<c:choose>
																	    <c:when test="${not empty lastItem.TAX_CODE}">
																	  		${lastItem.TAX_CODE}
																		</c:when>
																		<c:otherwise>
																			&nbsp;
																		</c:otherwise>
																	</c:choose>
																</c:if>
																<c:if test="${_b_bilForm.map.gstCheck eq 'TAX_RATE'}">
																	<c:choose>
																	    <c:when test="${not empty lastItem.TAX_RATE}">
																	  		${lastItem.TAX_RATE}%
																		</c:when>
																		<c:otherwise>
																			&nbsp;
																		</c:otherwise>
																	</c:choose>
																</c:if>
																</td>
												 	        <tr>
														</c:if>
														<c:choose>
															<c:when test="${lastItem.BILL_FROM_TEXT ne lastItem.BILL_TO_TEXT || lastItem.ISDISPLAY_BILLINGPERIOD eq '1'}">
																<tr>
																	<td colspan="6">&nbsp;</td>
																</tr>
																<tr>
																	<td>&nbsp;</td>
																	<td colspan="5">
																		<bean:message key="screen.b_bil.billingPeriod"/>
																		<bean:message key="screen.b_bil.from"/>
																		<bean:message key="screen.b_bil.colon1"/>
																		${lastItem.BILL_FROM_TEXT}
																		<bean:message key="screen.b_bil.toInfo"/>${lastItem.BILL_TO_TEXT}
																	</td>
																</tr>
															</c:when>
														</c:choose>
														<tr>
															<td colspan="6">&nbsp;</td>
														</tr>
													</c:if>
												</c:if>
												<c:set var="subDiscAmt" value="0"></c:set>
												<c:if test="${not empty item.ITEM_DISC_AMT && item.ITEM_DISC_AMT != 0 && item.DISPLAY_DISC ne '0'}">
													<c:set var="subDiscAmt" value="${item.ITEM_DISC_AMT}"></c:set>
												</c:if>
												<c:if test="${not empty item.ITEM_SUBTOTAL && item.ITEM_SUBTOTAL != 0 && item.IS_DISPLAY ne '0'}">
													<c:set var="subTotal" value="${subTotal + item.ITEM_SUBTOTAL}"></c:set>
													<c:if test="${'0'eq item.ITEM_GST}">
									 			    	<c:set var="nonTaxableSubTotal" value="${nonTaxableSubTotal + item.ITEM_SUBTOTAL}"></c:set> 
									 			    </c:if>
									 			    <c:if test="${'0'ne item.ITEM_GST}">
									 			    	<c:set var="taxableSubTotal" value="${taxableSubTotal + item.ITEM_SUBTOTAL}"></c:set> 
									 			    </c:if> 
												</c:if>
												<tr>
													<td valign="top" style="padding-left:10px;">
														<c:if test="${not empty item.ITEM_QTY && item.ITEM_QTY != 0 && not empty item.ITEM_UPRICE && item.ITEM_UPRICE != 0 && not empty item.ITEM_AMT && item.ITEM_AMT != 0 && item.IS_DISPLAY ne '0'}">
															<c:set var="index" value="${index + 1}"></c:set>
															${index}
														</c:if>
														&nbsp;
													</td>
													<td valign="top" style="width:470px;word-wrap: break-word;white-space : normal">
														<a href="#" onclick="openB_CPM_View('${pageContext.request.contextPath}/B_CPM/B_CPM_S05InitBL.do?customerPlan.idCustPlan=${item.ID_CUST_PLAN}&customerPlan.fromScreen=BIL&customerPlan.billType=${_b_bilForm.map.headerInfo.BILL_TYPE}')">
															<div><pre>${item.ITEM_DESC_TEXT}</pre></div>
														</a>
													</td>
													<td valign="top" style="text-align:right;">
														<c:if test="${not empty item.ITEM_QTY && item.ITEM_QTY != 0 && item.IS_DISPLAY ne '0'}"><fmt:formatNumber value="${item.ITEM_QTY}" pattern="#,##0"/></c:if>&nbsp;
													</td>
													<td valign="top" style="text-align:right;">
														<c:if test="${not empty item.ITEM_UPRICE && item.ITEM_UPRICE != 0 && item.IS_DISPLAY ne '0'}">
														<%-- 
															<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
																-
															</c:if>
														--%>
															<!-- Delete #156 Start-->
															<!--<fmt:formatNumber value="${item.ITEM_UPRICE}" pattern="#,##0.00"/>-->
															<!-- Delete #156 End-->
															<!-- Add #156 Start-->
															<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
																<c:if test="${_b_bilForm.map.billCnAmtNegative eq '1'}">
																	-<fmt:formatNumber value="${item.ITEM_UPRICE}" pattern="#,##0.00"/>
																</c:if>
																<c:if test="${_b_bilForm.map.billCnAmtNegative != '1'}">
																	<fmt:formatNumber value="${item.ITEM_UPRICE}" pattern="#,##0.00"/>
																</c:if>
															</c:if>
															<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE != 'CN'}">
																<fmt:formatNumber value="${item.ITEM_UPRICE}" pattern="#,##0.00"/>
															</c:if>
															<!-- Add #156 End--> 
														</c:if>&nbsp;
													</td>
													<td valign="top" style="text-align:right;">
														<c:choose>
													    <c:when test="${not empty item.ITEM_AMT && item.ITEM_AMT != 0 && item.IS_DISPLAY ne '0'}">
													    <%--
													    	<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
																-
															</c:if>
														 --%>
															<!-- Delete #156 Start-->
															<!--<fmt:formatNumber value="${item.ITEM_AMT}" pattern="#,##0.00"/>-->
															<!-- Delete #156 End-->
															<!-- Add #156 Start-->
															<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
																<c:if test="${_b_bilForm.map.billCnAmtNegative eq '1'}">
																	-<fmt:formatNumber value="${item.ITEM_AMT}" pattern="#,##0.00"/>
																</c:if>
																<c:if test="${_b_bilForm.map.billCnAmtNegative != '1'}">
																	<fmt:formatNumber value="${item.ITEM_AMT}" pattern="#,##0.00"/>
																</c:if>
															</c:if>
															<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE != 'CN'}">
																<fmt:formatNumber value="${item.ITEM_AMT}" pattern="#,##0.00"/>
															</c:if>
															<!-- Add #156 End--> 
														</c:when>
														<c:otherwise>
														&nbsp;
														</c:otherwise>
														</c:choose>
													</td>
													<td valign="top" style="padding-left:10px;">
														<c:if test="${_b_bilForm.map.gstCheck eq 'TAX_CODE'}">
															<c:choose>
															    <c:when test="${not empty item.TAX_CODE && item.IS_DISPLAY ne '0'}">
															        <c:if test="${'1' eq item.IS_DISPLAY_JOBNO && '1' eq _b_bilForm.map.headerInfo.jobModulesDisplayFlg}">
																        <div>&nbsp;</div>
																    </c:if>
																	${item.TAX_CODE}
																</c:when>
																<c:otherwise>
																	&nbsp;
																</c:otherwise>
															</c:choose>
														</c:if>
														<c:if test="${_b_bilForm.map.gstCheck eq 'TAX_RATE'}">
															<c:choose>
															    <c:when test="${not empty item.TAX_RATE && item.IS_DISPLAY ne '0'}">
															        <c:if test="${'1' eq item.IS_DISPLAY_JOBNO && '1' eq _b_bilForm.map.headerInfo.jobModulesDisplayFlg}">
																        <div>&nbsp;</div>
																    </c:if>
																	${item.TAX_RATE}%
																</c:when>
																<c:otherwise>
																	&nbsp;
																</c:otherwise>
															</c:choose>
														</c:if>
													</td>
												</tr>
												<c:if test="${'1' eq item.IS_DISPLAY_MIN_SVC}">
													<tr>
														<td colspan="6">&nbsp;</td>
													</tr>
													<tr>
														<td>&nbsp;</td>
														<td colspan="5">
															<bean:message key="screen.b_bil.contractPeriod"/>
															<bean:message key="screen.b_bil.contractPeriodFrom"/>
															${item.MIN_SVC_FROM_TEXT}
															<bean:message key="screen.b_bil.contractPeriodTo"/>
															${item.MIN_SVC_TO_TEXT}
														</td>
													</tr>
													<tr>
														<td colspan="6">&nbsp;</td>
													</tr>
												</c:if>
											</c:when>
											<c:otherwise>
												<c:if test="${not empty item.ITEM_SUBTOTAL && item.ITEM_SUBTOTAL != 0 && item.IS_DISPLAY ne '0'}">
													<c:set var="subTotal" value="${subTotal + item.ITEM_SUBTOTAL}"></c:set>
													<c:if test="${'0'eq item.ITEM_GST}">
									 			    	<c:set var="nonTaxableSubTotal" value="${nonTaxableSubTotal + item.ITEM_SUBTOTAL}"></c:set> 
									 			    </c:if>
									 			    <c:if test="${'0'ne item.ITEM_GST}">
									 			    	<c:set var="taxableSubTotal" value="${taxableSubTotal + item.ITEM_SUBTOTAL}"></c:set> 
									 			    </c:if> 
												</c:if>
												<c:set var="indexSub" value="${indexSub + 1}"></c:set>
												<tr>
												   <td valign="top" style="padding-left:10px;">
												   		<c:if test="${item.IS_DISPLAY ne '0'}">
												   		    <c:if test="${'1' eq item.IS_DISPLAY_JOBNO && '1' eq _b_bilForm.map.headerInfo.jobModulesDisplayFlg}">
														        <div>&nbsp;</div>
														    </c:if>
															<c:set var="index" value="${index + 1}"></c:set>${index}
														</c:if>&nbsp;
													</td>
													<td valign="top" style="width:470px;word-wrap: break-word;white-space : normal">
														<c:if test="${'1' eq item.IS_DISPLAY_JOBNO && '1' eq _b_bilForm.map.headerInfo.jobModulesDisplayFlg}">
															<pre style="color:#CD853F;"><bean:message key="screen.b_bil.jobNoPoint"/>&nbsp;${item.JOB_NO}</pre>
														</c:if>
														<div><pre>${item.ITEM_DESC_TEXT}</pre></div>
													</td>
													<td valign="top" style="text-align:right;">
														<c:if test="${not empty item.ITEM_QTY && item.ITEM_QTY != 0 && item.IS_DISPLAY ne '0'}">
														    <c:if test="${'1' eq item.IS_DISPLAY_JOBNO && '1' eq _b_bilForm.map.headerInfo.jobModulesDisplayFlg}">
														        <div>&nbsp;</div>
														    </c:if>
														    <fmt:formatNumber value="${item.ITEM_QTY}" pattern="#,##0"/>
														</c:if>&nbsp;
													</td>
													<td valign="top" style="text-align:right;">
														<c:if test="${not empty item.ITEM_UPRICE && item.ITEM_UPRICE != 0 && item.IS_DISPLAY ne '0'}">
															<c:if test="${'1' eq item.IS_DISPLAY_JOBNO && '1' eq _b_bilForm.map.headerInfo.jobModulesDisplayFlg}">
														        <div>&nbsp;</div>
														    </c:if>
														    <%--
															<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
																-
															</c:if>
														    --%>
															<!-- Delete #156 Start-->
															<!--<fmt:formatNumber value="${item.ITEM_UPRICE}" pattern="#,##0.00"/>-->
															<!-- Delete #156 End-->
															<!-- Add #156 Start-->
															<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
																<c:if test="${_b_bilForm.map.billCnAmtNegative eq '1'}">
																	-<fmt:formatNumber value="${item.ITEM_UPRICE}" pattern="#,##0.00"/>
																</c:if>
																<c:if test="${_b_bilForm.map.billCnAmtNegative != '1'}">
																	<fmt:formatNumber value="${item.ITEM_UPRICE}" pattern="#,##0.00"/>
																</c:if>
															</c:if>
															<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE != 'CN'}">
																<fmt:formatNumber value="${item.ITEM_UPRICE}" pattern="#,##0.00"/>
															</c:if>
															<!-- Add #156 End--> 
														</c:if>&nbsp;
													</td>
													<td valign="top" style="text-align:right;">
														<c:choose>
													    <c:when test="${not empty item.ITEM_AMT && item.ITEM_AMT != 0 && item.IS_DISPLAY ne '0'}">
															<c:if test="${'1' eq item.IS_DISPLAY_JOBNO && '1' eq _b_bilForm.map.headerInfo.jobModulesDisplayFlg}">
														        <div>&nbsp;</div>
														    </c:if>
														    <%--
															<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
																-
															</c:if>
														     --%>
															<!-- Delete #156 Start-->
															<!--<fmt:formatNumber value="${item.ITEM_AMT}" pattern="#,##0.00"/>-->
															<!-- Delete #156 End-->
															<!-- Add #156 Start-->
															<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
																<c:if test="${_b_bilForm.map.billCnAmtNegative eq '1'}">
																	-<fmt:formatNumber value="${item.ITEM_AMT}" pattern="#,##0.00"/>
																</c:if>
																<c:if test="${_b_bilForm.map.billCnAmtNegative != '1'}">
																	<fmt:formatNumber value="${item.ITEM_AMT}" pattern="#,##0.00"/>
																</c:if>
															</c:if>
															<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE != 'CN'}">
																<fmt:formatNumber value="${item.ITEM_AMT}" pattern="#,##0.00"/>
															</c:if>
															<!-- Add #156 End-->
														</c:when>
														<c:otherwise>
															&nbsp;
														</c:otherwise>
														</c:choose>
														<input type="hidden" id="itemAmtHidden${indexSub}" value="${item.ITEM_SUBTOTAL}">
													</td>
													<td valign="top" style="padding-left:10px;">
														<c:if test="${_b_bilForm.map.gstCheck eq 'TAX_CODE'}">
															<c:choose>
															    <c:when test="${not empty item.TAX_CODE && item.IS_DISPLAY ne '0'}">
															        <c:if test="${'1' eq item.IS_DISPLAY_JOBNO && '1' eq _b_bilForm.map.headerInfo.jobModulesDisplayFlg}">
																        <div>&nbsp;</div>
																    </c:if>
																	<%-- ${item.TAX_CODE} --%>
																	<c:if test="${'0'eq item.ITEM_GST}">
													 			    	-
													 			    </c:if>
													 			    <c:if test="${'0'ne item.ITEM_GST}">
													 			    	<fmt:formatNumber value="${item.ITEM_GST}" pattern="#,##0.00"/>
													 			    </c:if>

																</c:when>
																<c:otherwise>
																	&nbsp;
																</c:otherwise>
															</c:choose>
														</c:if>
														<c:if test="${_b_bilForm.map.gstCheck eq 'TAX_RATE'}">
															<c:choose>
															    <c:when test="${not empty item.TAX_RATE && item.IS_DISPLAY ne '0'}">
															        <c:if test="${'1' eq item.IS_DISPLAY_JOBNO && '1' eq _b_bilForm.map.headerInfo.jobModulesDisplayFlg}">
																       <div>&nbsp;</div>
																    </c:if>
																	${item.TAX_RATE}%
																</c:when>
																<c:otherwise>
																	&nbsp;
																</c:otherwise>
															</c:choose>
														</c:if>
														<input type="hidden" id="itemApplyGstHidden${indexSub}" value="${item.TAX_RATE}">
													</td>
												</tr>
												<tr>
													<c:if test="${not empty item.ITEM_DISC_AMT && item.ITEM_DISC_AMT != 0 && item.DISPLAY_DISC ne '0'}">
														<td valign="top">&nbsp;</td>
											 	        <td colspan="3">
											 	           <b><i><bean:message key="screen.b_bil.discount"/></i></b>
											 	        </td>
											 	        <td style="text-align:right;" valign="top">
											 	          <fmt:formatNumber value="${item.ITEM_DISC_AMT}" pattern="#,##0.00"/>
											 	        </td>
											 	        <td valign="top" style="padding-left:10px;">
											 	        	<c:if test="${_b_bilForm.map.gstCheck eq 'TAX_CODE'}">
																<c:choose>
																    <c:when test="${not empty item.TAX_CODE}">
																  		<%-- ${item.TAX_CODE} --%>
																  		-
																	</c:when>
																	<c:otherwise>
																		&nbsp;
																	</c:otherwise>
																</c:choose>
															</c:if>
															<c:if test="${_b_bilForm.map.gstCheck eq 'TAX_RATE'}">
																<c:choose>
																    <c:when test="${not empty item.TAX_RATE}">
																  		${item.TAX_RATE}%
																	</c:when>
																	<c:otherwise>
																		&nbsp;
																	</c:otherwise>
																</c:choose>
															</c:if>
														</td>
													</c:if>
				 	    						</tr>
											</c:otherwise>
										</c:choose>	
									</c:otherwise>
								</c:choose>	
								<c:set var="lastItem" value="${item}"></c:set>
							</c:forEach>
							<c:if test="${not empty lastItem}">
								<c:choose>
									<c:when test="${lastItem.ITEM_CAT eq '1'}">
										<c:if test="${subDiscAmt != 0}">
										    <tr>
												<td colspan="6">&nbsp;</td>
											</tr>
											<tr>
												<td valign="top">&nbsp;</td>
									 	        <td colspan="3">
									 	           <b><i><bean:message key="screen.b_bil.discount"/></i></b>
									 	        </td>
									 	        <td style="text-align:right;" valign="top">
									 	          <fmt:formatNumber value="${subDiscAmt}" pattern="#,##0.00"/>
									 	        </td>
									 	        <td valign="top" style="padding-left:10px;">
									 	        <c:if test="${_b_bilForm.map.gstCheck eq 'TAX_CODE'}">
													<c:choose>
													    <c:when test="${not empty lastItem.TAX_CODE}">
													  		${lastItem.TAX_CODE}
														</c:when>
														<c:otherwise>
															&nbsp;
														</c:otherwise>
													</c:choose>
												</c:if>
												<c:if test="${_b_bilForm.map.gstCheck eq 'TAX_RATE'}">
													<c:choose>
													    <c:when test="${not empty lastItem.TAX_RATE}">
													  		${lastItem.TAX_RATE}%
														</c:when>
														<c:otherwise>
															&nbsp;
														</c:otherwise>
													</c:choose>
												</c:if>
												</td>
								 	        <tr>
										</c:if>
										<c:choose>
											<c:when test="${lastItem.BILL_FROM_TEXT ne lastItem.BILL_TO_TEXT || lastItem.ISDISPLAY_BILLINGPERIOD eq '1'}">
												<tr>
													<td colspan="6">&nbsp;</td>
												</tr>
												<tr>
													<td>&nbsp;</td>
													<td colspan="4">
														<bean:message key="screen.b_bil.billingPeriod"/>
														<bean:message key="screen.b_bil.from"/>
														<bean:message key="screen.b_bil.colon1"/>
														${lastItem.BILL_FROM_TEXT}
														<bean:message key="screen.b_bil.toInfo"/>${lastItem.BILL_TO_TEXT}
													</td>
												</tr>
											</c:when>
										</c:choose>
									</c:when>
								</c:choose>
							</c:if>
						</table>
						<input type="hidden" id="indexAmt" value="${indexSub}">
					</td>
				</tr>
			</table>
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr><td style="height:10px"></td></tr>
			</table>
			<table width="100%" cellpadding="0" cellspacing="0">
				<col width="80%"/>
				<col width="10%"/>
				<col width="10%"/>
				<!-- End Detail content -->
				<c:if test="${'2'ne _b_bilForm.map.headerInfo.GSTAPPLYFLAG}">
				<tr style="font-weight: bold;">
			   		<td align="right" style="background-color:rgb(200,210,230)">
						<bean:message key="screen.b_bil.subTotal"/>
						${_b_bilForm.map.headerInfo.BILL_CURRENCY}
					</td>
					<td align="right" style="background-color:rgb(200,210,230)">
					<%--
						<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
							-
						</c:if>
					 --%>
						<!-- Delete #156 Start-->
						<!--<fmt:formatNumber value="${subTotal}" pattern="#,##0.00"/>-->
						<!-- Delete #156 End--> 
						<!-- Add #156 Start-->
						<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
							<c:if test="${_b_bilForm.map.billCnAmtNegative eq '1'}">
								-<fmt:formatNumber value="${subTotal}" pattern="#,##0.00"/>
							</c:if>
							<c:if test="${_b_bilForm.map.billCnAmtNegative != '1'}">
								<fmt:formatNumber value="${subTotal}" pattern="#,##0.00"/>
							</c:if>
						</c:if>
						<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE != 'CN'}">
							<fmt:formatNumber value="${subTotal}" pattern="#,##0.00"/>
						</c:if>
						<!-- Add #156 End--> 
					</td>
					<td style="background-color:rgb(200,210,230)">
						&nbsp;
					</td>
				</tr>
				</c:if>
				<c:if test="${'2'eq _b_bilForm.map.headerInfo.GSTAPPLYFLAG}">
				<tr style="font-weight: bold;">
			   		<td align="right" style="background-color:rgb(200,210,230)">
						Subtotal Taxable Services (${_b_bilForm.map.headerInfo.BILL_CURRENCY})
					</td>
					<td align="right" style="background-color:rgb(200,210,230)">
					 <%-- 
						<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
							-
						</c:if>
					 --%>
					 	<!-- Delete #156 Start-->
						<!--<fmt:formatNumber value="${subTotal}" pattern="#,##0.00"/>-->
						<!-- Delete #156 End--> 
						<!-- Add #156 Start-->
						<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
							<c:if test="${_b_bilForm.map.billCnAmtNegative eq '1'}">
								-<fmt:formatNumber value="${taxableSubTotal}" pattern="#,##0.00"/>
							</c:if>
							<c:if test="${_b_bilForm.map.billCnAmtNegative != '1'}">
								<fmt:formatNumber value="${taxableSubTotal}" pattern="#,##0.00"/>
							</c:if>
						</c:if>
						<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE != 'CN'}">
							<fmt:formatNumber value="${taxableSubTotal}" pattern="#,##0.00"/>
						</c:if>
						<!-- Add #156 End--> 
					</td>
					<td style="background-color:rgb(200,210,230)">
						&nbsp;
					</td>
				</tr>
				<tr style="font-weight: bold;">
			   		<td align="right" style="background-color:rgb(200,210,230)">
						Subtotal Non-Taxable Services (${_b_bilForm.map.headerInfo.BILL_CURRENCY})
					</td>
					<td align="right" style="background-color:rgb(200,210,230)">
					 <%-- 
						<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
							-
						</c:if>
					 --%>
					 	<!-- Delete #156 Start-->
						<!--<fmt:formatNumber value="${subTotal}" pattern="#,##0.00"/>-->
						<!-- Delete #156 End--> 
						<!-- Add #156 Start-->
						<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
							<c:if test="${_b_bilForm.map.billCnAmtNegative eq '1'}">
								-<fmt:formatNumber value="${nonTaxableSubTotal}" pattern="#,##0.00"/>
							</c:if>
							<c:if test="${_b_bilForm.map.billCnAmtNegative != '1'}">
								<fmt:formatNumber value="${nonTaxableSubTotal}" pattern="#,##0.00"/>
							</c:if>
						</c:if>
						<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE != 'CN'}">
							<fmt:formatNumber value="${nonTaxableSubTotal}" pattern="#,##0.00"/>
						</c:if>
						<!-- Add #156 End--> 
					</td>
					<td style="background-color:rgb(200,210,230)">
						&nbsp;
					</td>
				</tr>
				</c:if>
				<tr style="font-weight: bold">
			   		<td align="right" style="background-color:rgb(200,210,230)">
						<c:if test="${'2' ne _b_bilForm.map.headerInfo.GSTAPPLYFLAG}">
						<bean:message key="screen.b_bil.gstAmount"/>	
						${_b_bilForm.map.headerInfo.BILL_CURRENCY}
						</c:if>
						
						<c:if test="${'2'eq _b_bilForm.map.headerInfo.GSTAPPLYFLAG}">
						Services Tax ${taxRate}% (${_b_bilForm.map.headerInfo.BILL_CURRENCY})
						</c:if>
					</td>
					<td align="right" style="background-color:rgb(200,210,230)">
					<%--
						<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
							-
						</c:if>
					 --%>
						<!-- Delete #156 Start-->
						<!--<fmt:formatNumber value="${_b_bilForm.map.headerInfo.GST_AMOUNT}" pattern="#,##0.00"/>-->
						<!-- Delete #156 End-->
						<!-- Add #156 Start-->
						<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
							<c:if test="${_b_bilForm.map.billCnAmtNegative eq '1'}">
								-<fmt:formatNumber value="${_b_bilForm.map.headerInfo.GST_AMOUNT}" pattern="#,##0.00"/>
							</c:if>
							<c:if test="${_b_bilForm.map.billCnAmtNegative != '1'}">
								<fmt:formatNumber value="${_b_bilForm.map.headerInfo.GST_AMOUNT}" pattern="#,##0.00"/>
							</c:if>
						</c:if>
						<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE != 'CN'}">
							<fmt:formatNumber value="${_b_bilForm.map.headerInfo.GST_AMOUNT}" pattern="#,##0.00"/>
						</c:if>
						<!-- Add #156 End--> 
					</td>
					<td style="background-color:rgb(200,210,230)">
						&nbsp;
					</td>
			</table>
			<table width="100%" cellpadding="0" cellspacing="0" style="font-weight:bold">
				<tr align="center">
					<td width="40%" style="background-color:rgb(136,167,216)">
					</td>
					<td width="40%" style="background-color:rgb(136,167,216)"  align="left">
						<bean:message key="screen.b_bil.grandTotalU"/>
						&nbsp;${_b_bilForm.map.headerInfo.BILL_CURRENCY}
						&nbsp;
					</td>
					<td width="10%" align="right" style="background-color:rgb(136,167,216);font-weight:bold">
					<%-- 
						<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
							-
						</c:if>
					--%>
						<!-- Delete #156 Start-->
						<!-- <fmt:formatNumber value="${_b_bilForm.map.headerInfo.BILL_AMOUNT}" pattern="#,##0.00"/>-->
						<!-- Delete #156 End-->
						<!-- Add #156 Start-->
						<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
							<c:if test="${_b_bilForm.map.billCnAmtNegative eq '1'}">
								-<fmt:formatNumber value="${_b_bilForm.map.headerInfo.BILL_AMOUNT}" pattern="#,##0.00"/>
							</c:if>
							<c:if test="${_b_bilForm.map.billCnAmtNegative != '1'}">
								<fmt:formatNumber value="${_b_bilForm.map.headerInfo.BILL_AMOUNT}" pattern="#,##0.00"/>
							</c:if>
						</c:if>
						<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE != 'CN'}">
							<fmt:formatNumber value="${_b_bilForm.map.headerInfo.BILL_AMOUNT}" pattern="#,##0.00"/>
						</c:if>
						<!-- Add #156 End--> 
					</td>
					<td width="10%" style="background-color:rgb(136,167,216)">
						&nbsp;
					</td>
				</tr>
			</table>
			<c:if test="${_b_bilForm.map.headerInfo.BILL_CURRENCY ne _b_bilForm.map.headerInfo.EXPORT_CUR and '-' ne fn:trim(_b_bilForm.map.headerInfo.EXPORT_CUR)}">
				<table width="100%" cellpadding="0" cellspacing="0" style="font-weight:bold">
					<tr align="center" style="background-color:rgb(136,167,216);font-size: 17px;font-weight: bold;">
						<td width="25%" style="background-color:rgb(136,167,216)" style="font-weight:normal;text-align: left;">
							<c:if test="${_b_bilForm.map.headerInfo.IS_DISPLAY_EXP_AMT eq '1'}">
								<input type="checkbox" disabled="disabled" checked="checked">
							</c:if>
							<c:if test="${_b_bilForm.map.headerInfo.IS_DISPLAY_EXP_AMT eq '0'}">
								<input type="checkbox" disabled="disabled">
							</c:if>
							<bean:message key="screen.b_bil.displayAt"/>
							<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
								<bean:message key="screen.b_bil.creditNote"/>
							</c:if>
							<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'IN'}">
								<bean:message key="screen.b_bil.invoiceNote"/>
							</c:if>
							<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'DN'}">
								<bean:message key="screen.b_bil.debitNote"/>
							</c:if>
						</td>
						<td width="55%" style="background-color:rgb(136,167,216);" align="left">
						    <html:hidden styleId="billCurrency" value="${_b_bilForm.map.headerInfo.BILL_CURRENCY}" property="headerData.billCurrency"/>
						    <html:hidden styleId="exportCur" value="${_b_bilForm.map.headerInfo.EXPORT_CUR}" property="headerData.exportCur"/>
			                <html:hidden styleId="fixedForex" value="${_b_bilForm.map.headerInfo.FIXED_FOREX}" property="headerData.fixedForex"/>
			                <html:hidden styleId="gstAmount" value="${_b_bilForm.map.headerInfo.GST_AMOUNT}" property="headerData.gstAmount"/>
			                <html:hidden styleId="subTotalAmt" value="${subTotal}" property="headerData.subTotalAmt"/>
			                <input type="hidden" name="rootPath" id="rootPath" value="<%=request.getContextPath()%>">
			                
			                
							
							<c:choose>
						        <c:when test="${_b_bilForm.map.headerInfo.currencyStd eq 1}">
						            <bean:message key="screen.b_bil.grandTotalU"/>
							        &nbsp;${_b_bilForm.map.headerInfo.EXPORT_CUR}
							        &nbsp;(
							        1&nbsp;${_b_bilForm.map.headerInfo.BILL_CURRENCY}
							        &nbsp;=&nbsp;
							        <c:if test="${ not empty _b_bilForm.map.headerInfo.FIXED_FOREX}">
								        <bean:message key="screen.b_bil.fixedForex"/>
							        </c:if>
							        <html:text property="headerData.curRate" styleId="curRate" value="${_b_bilForm.map.headerInfo.CUR_RATE}" onkeypress="return onlyDecNumbers(this,event)" onchange="curRateChange(this)" style="text-align:right;width:100px;" maxlength="13"/>
						            &nbsp;${_b_bilForm.map.headerInfo.EXPORT_CUR}
							        )
							    </c:when>
							    <c:when test="${_b_bilForm.map.headerInfo.currencyStd eq 0}">
							        <c:if test="${_b_bilForm.map.headerInfo.BILL_CURRENCY eq _b_bilForm.map.headerInfo.defCurrency}">
										<bean:message key="screen.b_bil.grandTotalU"/>
										&nbsp;${_b_bilForm.map.headerInfo.EXPORT_CUR}
										&nbsp;(
										1&nbsp;${_b_bilForm.map.headerInfo.EXPORT_CUR}
										&nbsp;=&nbsp;
										<c:if test="${ not empty _b_bilForm.map.headerInfo.FIXED_FOREX}">
								            <bean:message key="screen.b_bil.fixedForex"/>
							            </c:if>
										<html:text property="headerData.curRate" styleId="curRate" value="${_b_bilForm.map.headerInfo.CUR_RATE}" onkeypress="return onlyDecNumbers(this,event)" onchange="curRateChange(this)" style="text-align:right;width:100px;" maxlength="13"/>
										&nbsp;${_b_bilForm.map.headerInfo.BILL_CURRENCY}
										)
									</c:if>
									<c:if test="${_b_bilForm.map.headerInfo.BILL_CURRENCY ne _b_bilForm.map.headerInfo.defCurrency}">
										<bean:message key="screen.b_bil.grandTotalU"/>
										&nbsp;${_b_bilForm.map.headerInfo.EXPORT_CUR}
										&nbsp;(
										1&nbsp;${_b_bilForm.map.headerInfo.BILL_CURRENCY}
										&nbsp;=&nbsp;
										<c:if test="${not empty _b_bilForm.map.headerInfo.FIXED_FOREX}">
											<bean:message key="screen.b_bil.fixedForex"/>
										</c:if>
										<html:text property="headerData.curRate" styleId="curRate" value="${_b_bilForm.map.headerInfo.CUR_RATE}" onkeypress="return onlyDecNumbers(this,event)" onchange="curRateChange(this)" style="text-align:right;width:100px;" maxlength="13"/>
										&nbsp;${_b_bilForm.map.headerInfo.EXPORT_CUR}
										)
									</c:if>
							    </c:when>
						        <c:otherwise></c:otherwise>
						    </c:choose>
						</td>
						<td width="10%" align="right" style="background-color:rgb(136,167,216);">
						<%-- 
						    <c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'CN'}">
								    -
							</c:if>
						--%>
						    <span id="spanExportAmount">
							    <fmt:formatNumber value="${_b_bilForm.map.headerInfo.EXPORT_AMOUNT}" pattern="#,##0.00"/>
							</span>
							<html:hidden property="headerData.exportAmount" styleId="exportAmount" value="${_b_bilForm.map.headerInfo.EXPORT_AMOUNT}"/>
						</td>
						<td width="10%" style="background-color:rgb(136,167,216)">
							&nbsp;
						</td>
					</tr>
				</table>
			</c:if>
			<div style="font-weight:bold;margin-top:3px;margin-bottom:20px;"> 
			<input type="hidden" name="isDisAmt" id="isDisAmt" value="${_b_bilForm.map.headerInfo.IS_DISPLAY_EXP_AMT}">
				<c:choose>
				    <c:when test="${_b_bilForm.map.headerInfo.IS_DISPLAY_EXP_AMT eq '1'}">
				        <t:defineCodeList id="LIST_CURRENCY" />
				        (
				        <t:writeCodeValue codeList="LIST_CURRENCY" key="${_b_bilForm.map.headerInfo.EXPORT_CUR}"></t:writeCodeValue>
				        <bean:message key="screen.b_bil.amount"/>:
				        <label id="numberWordLabel">
				            ${bs:numberToWord(_b_bilForm.map.headerInfo.EXPORT_AMOUNT)}
				        </label>			        
				    </c:when>
				    <c:otherwise>
				        <t:defineCodeList id="LIST_CURRENCY" />
				        (
				        <t:writeCodeValue codeList="LIST_CURRENCY" key="${_b_bilForm.map.headerInfo.BILL_CURRENCY}"></t:writeCodeValue>
				        <bean:message key="screen.b_bil.amount"/>:
				        <label id="numberWordLabel">
				            ${bs:numberToWord(_b_bilForm.map.headerInfo.BILL_AMOUNT)}
				        </label>
				    </c:otherwise>
				</c:choose>
				<bean:message key="screen.b_bil.only"/>
			</div>
			<%-- 
			<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'IN' 
					or _b_bilForm.map.headerInfo.BILL_TYPE eq 'DN'}">
			--%>
				<div class="footerInfo">
					<% int footerCounter = 0; %>
					<logic:iterate id="footer" name="_b_bilForm" property="footerInfo">
						<% 
							footerCounter++;
							if(footerCounter != 15){
						%>
						<logic:notEmpty name="footer" property="SET_VALUE">
							<pre><bean:write name="footer" property="SET_VALUE"/>&nbsp;</pre>
						</logic:notEmpty>
						<%	} %>
					</logic:iterate>
				</div>
			<%-- 
			</c:if>
			--%>
			<c:if test="${empty _b_bilForm.map.inputHeaderInfo.billType}">
			    <%--
				<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'IN' or _b_bilForm.map.headerInfo.BILL_TYPE eq 'DN'}">
			    --%>
					<div class="footerInfo">
						<table width="100%" cellpadding="0" cellspacing="0" style="font-weight:bold">	
							<tr>
								<td>
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td><pre><bean:message key="screen.b_bil.bank"/> <bean:message key="screen.b_bil.colon"/>&nbsp;</pre></td>
										</tr>
										<tr> 
											<td>
												<pre>&nbsp;</pre>
											</td>
										</tr>
										<tr> 
											<td><pre><bean:message key="screen.b_bil.accountName"/> <bean:message key="screen.b_bil.colon"/>&nbsp;</pre></td>
										</tr>
										<tr> 
											<td><pre><bean:message key="screen.b_bil.accountNo"/> <bean:message key="screen.b_bil.colon"/>&nbsp;</pre></td>
										</tr>
										<tr> 
											<td><pre><bean:message key="screen.b_bil.swiftCode"/> <bean:message key="screen.b_bil.colon"/>&nbsp;</pre></td>
										</tr>
									</table>
								</td>
								<logic:iterate id="bankFooter" name="_b_bilForm" property="bankFooterInfo">				
								<td>
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td><pre><bean:write name="bankFooter" property="BANK_NAME"/>&nbsp;</pre></td>
										</tr>
										<tr> 
											<td><pre><bean:write name="bankFooter"  property="BRANCH_NAME"/>&nbsp;</pre></td>
										</tr>
										<tr> 
											<td><pre><bean:write name="bankFooter" property="COM_ACCT_NAME"/>&nbsp;</pre></td>
										</tr>
										<tr> 
											<td><pre><bean:write name="bankFooter" property="COM_ACCT_NO"/>&nbsp; (<bean:write name="bankFooter" property="COM_CUR"/>&nbsp;)</pre></td>
										</tr>
										<tr> 
											<td><pre><bean:write name="bankFooter" property="COM_SWIFT"/>&nbsp;</pre></td>
										</tr>
									</table>
								</td>
								</logic:iterate>
							</tr>
						</table>
					</div>
			    <%--
				</c:if>
				--%>
			</c:if>
			<c:if test="${not empty _b_bilForm.map.inputHeaderInfo.billType}">
			    <%--
				<c:if test="${_b_bilForm.map.headerInfo.BILL_TYPE eq 'IN' or _b_bilForm.map.headerInfo.BILL_TYPE eq 'DN'}">
				--%>
					<div class="footerInfo">
						<table width="100%" cellpadding="0" cellspacing="0" style="font-weight:bold">	
							<tr>
								<td>
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td><pre><bean:message key="screen.b_bil.bank"/> <bean:message key="screen.b_bil.colon"/>&nbsp;</pre></td>
										</tr>
										<tr> 
											<td>
												<pre>&nbsp;</pre>
											</td>
										</tr>
										<tr> 
											<td><pre><bean:message key="screen.b_bil.accountName"/> <bean:message key="screen.b_bil.colon"/>&nbsp;</pre></td>
										</tr>
										<tr> 
											<td><pre><bean:message key="screen.b_bil.accountNo"/> <bean:message key="screen.b_bil.colon"/>&nbsp;</pre></td>
										</tr>
										<tr> 
											<td><pre><bean:message key="screen.b_bil.swiftCode"/> <bean:message key="screen.b_bil.colon"/>&nbsp;</pre></td>
										</tr>
									</table>
								</td>
								<logic:iterate id="bankFooter" name="_b_bilForm" property="bankFooterInfo">				
								<td>
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr>
											<td><pre><bean:write name="bankFooter" property="BANK_NAME"/>&nbsp;</pre></td>
										</tr>
										<tr> 
											<td><pre><bean:write name="bankFooter"  property="BRANCH_NAME"/>&nbsp;</pre></td>
										</tr>
										<tr> 
											<td><pre><bean:write name="bankFooter" property="COM_ACCT_NAME"/>&nbsp;</pre></td>
										</tr>
										<tr> 
											<td><pre><bean:write name="bankFooter" property="COM_ACCT_NO"/>&nbsp; (<bean:write name="bankFooter" property="COM_CUR"/>&nbsp;)</pre></td>
										</tr>
										<tr> 
											<td><pre><bean:write name="bankFooter" property="COM_SWIFT"/>&nbsp;</pre></td>
										</tr>
									</table>
								</td>
								</logic:iterate>
							</tr>
						</table>
					</div>
				<%--
				</c:if>
				--%>
			</c:if>
			<hr class="lineBottom"/>
			<br/>
            <%-- <ts:submit value="Save" styleClass="button" /> --%>
            <input type="button" value='Save' class="button" onclick="onSave()"/>
            <input type="hidden" id="hiddenMsgExitConfirmation" value="<bean:message key="info.ERR4SC001"/>"/>
            <input type="hidden" name="ERR1SC107" id="ERR107" value="<bean:message key="errors.ERR1SC107" arg0="{0}" arg1="{1}"/>">
            <input type="button" value='Exit' class="button" onclick="ClickExit()"/>
<%--             <bs:buttonLink action="/RP_B_BIL_S02_01BL.do?idRef=${_b_bilForm.map.headerInfo.ID_REF}&mode=view&fromPageFlag=BIL" value="Exit"/> --%>
            <html:hidden property="idRef"/>
            <html:hidden property="mode"/>
            <input type="hidden" name="headerData.billType" value="${_b_bilForm.map.headerInfo.BILL_TYPE}"/>
			<input type="hidden" name="headerData.isClosed" value="${_b_bilForm.map.headerInfo.IS_CLOSED}"/>
			<input type="hidden" name="headerData.idCust" value="${_b_bilForm.map.headerInfo.ID_CUST}"/>
            <div class="error">
				<c:if test="${not empty _b_bilForm.map.lastMsg}">
					${_b_bilForm.map.lastMsg}
				</c:if>
			</div>
            <div class="message">
				<ts:messages id="message" message="true">
					<bean:write name="message"/>
				</ts:messages>
			</div>
			<div class="error">
				<ts:errors/>
			</div>
			</div>
		</ts:form>
	</ts:body>
</html:html>