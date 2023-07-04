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

<html>
<head>
   	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
	<link href="${pageContext.request.contextPath}/R_REV/css/r_rev.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
	<title><bean:message key="screen.r_rev.title"/></title>
	<script type="text/javascript">
		var FORWARD_CHANGE_CONTENT = "forward_changeContent";
		var FORWARD_DOWNLOAD = "forward_download";
		var FORWARD_FINAL = "forward_final";
		
		var isFinalClicked = 0;
		
		function initMain(){
			//Settings for Generate Report Month & Year Select Box
			if($('#currentPage').val() == '1' || $('#currentPage').val() == '2' || $('#currentPage').val() == '3'){
			preSelectMonth("reportMonth");
			populateYearSelect("reportYear");
			}	
			
			//On page load monthly revenue report, trigger search
			if($('#currentPage').val() == '2'){
				if ($('#executeMthlyRptFlg').val() != 'Y')
					$("#monthlyReportBtn").trigger('click');
			}else if($('#currentPage').val() == '3'){
				if ($('#executeMthlyRptFlg').val() != 'Y')
					$("#advanceRevenueBtn").trigger('click');
			}
		}		
		function gotoPage(idx){
			if($('#currentPage').val() == '1' || $('#currentPage').val() == '2' || $('#currentPage').val() == '3'){				
				$('#finalReportFlag').val("N");
				//Reset the Year Selection Box To Default
				$('#originalRptYear').val("");
				populateYearSelect("reportYear");
			}
			$("#currentPage").val(idx);
			
			if($('#currentPage').val() == '2' || $('#currentPage').val() == '3'){
				$('#executeMthlyRptFlg').val('N');
			}
			submitForm(FORWARD_CHANGE_CONTENT);
		}		
		function submitForm(event) {
			var hiddenEvent = '<input type="hidden" name="event" value="' + event + '"/>';
			$('form').append(hiddenEvent);
			$('form').submit();
		}
		function executeFinalReport(){
			$('#finalReportFlag').val("Y");
			$('#finalBtn').attr('disabled', true);
			$('#batchMessage').hide();
			submitForm(FORWARD_FINAL);
			setTimeout(function showMsg() {
				$('#batchMsg').show();
			}, 2000);
		}
		/*
		function executeFinalReport(){	
			
			$('#invalidMonthMsg').hide();
			$('#batchMsg').hide();
			
			//if(isFinalClicked == 0){
				
				var year1 = parseInt($('#reportYear').val());
				var year2 = parseInt($('#reportYear1').val());
				var month1 = parseInt($('#reportMonth').val());
				var month2 = parseInt($('#reportMonth1').val());
				var isError = false;
				
				//Date Validation Checking
				if(year1 != year2 || month1 != month2){
					isError = true;
				}			
				
				if(isError){
					//$('#invalidMonthMsg').show();
					//return false;
				}
				
				$('#finalReportFlag').val("Y");
				$('#batchMsg').show();
				//isFinalClicked = 1;
				
				$.ajax({
				    type : "POST",
				    url  : "R_REV_R02BL.do",
				    data : $('#revForm').serialize(),
				    async: true,
				    success : function(result){
				      //
				    },
				    error : function(xhr, errmsg) {
				    	isFinalClicked = 0;
				    }
				});			
			//}
		} */
		function setFilePath(link){
			$("#filePath").val(link);
			submitForm(FORWARD_DOWNLOAD);
		}
		function preSelectMonth(id){
			//var d = new Date();
		    //var curr_month = d.getMonth();
		    var curr_month = "";
		    
		    if($('#currentPage').val() == '1'){
		    	curr_month = $('#reportMonth1').val();
		    }else if($('#currentPage').val() == '2'){
		    	curr_month = $('#reportMonth2').val();
		    }else if($('#currentPage').val() == '3'){
		    	curr_month = $('#reportMonth3').val();
		    }

		    if(($('.originalRptMonth').val() == "" && $('#currentPage').val() == '1')||($('#currentPage').val() == '2' || $('#currentPage').val() == '3')){
				for(i = 1; i <= 12; i++){
					if(curr_month == i)
						document.getElementById(id).options[i-1].selected = true;
				}
			}
		}
		function populateYearSelect(id){
		     d = new Date();
		     curr_year = d.getFullYear();
		     
		     var rptYear = "";
		     
		     if($('#currentPage').val() == '1'){
		    	 rptYear = $('#reportYear1').val();
			    }else if($('#currentPage').val() == '2'){
			    	rptYear = $('#reportYear2').val();
			    }else if($('#currentPage').val() == '3'){
			    	rptYear = $('#reportYear3').val();
			    }
		    
		    var length = document.getElementById(id).length;
		     
		 	if($('#currentPage').val() == '1' ||$('#currentPage').val() == '2'||$('#currentPage').val() == '3'){
		 		for(i = 0; i < length; i++)
			     {
			         //document.getElementById(id).options[i] = new Option(curr_year-i,curr_year-i);
			         if($('.originalRptYear').val() == "" && document.getElementById(id).options[i].value == rptYear)
						document.getElementById(id).options[i].selected = true;
			         else if(document.getElementById(id).options[i].value == $('.originalRptYear').val())
			        	 document.getElementById(id).options[i].selected = true;
			     }
		 	}		     
		 }
		function executeMonthlyRptSearch(){
			$('#executeMthlyRptFlg').val('Y');
			
			var selectedMonth = $('#reportMonth').val();
			
			if($('#currentPage').val() == '1'){
		    	$('#reportMonth1').val(selectedMonth);
		    }else if($('#currentPage').val() == '2'){
		    	$('#reportMonth2').val(selectedMonth);
		    }else if($('#currentPage').val() == '3'){
		    	$('#reportMonth3').val(selectedMonth);
		    }
		}
		function linkBathHistoryClick(url){
			
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
	</script>
</head>
<body id="r" onload="initMain();">
<ts:form action="/R_REV_R01DSP" styleId="revForm">
    <!-- loading payment Method -->
    <t:defineCodeList id="NO_OF_MONTH" />
    <t:defineCodeList id="LIST_REVENUE_YEAR" />
    <input type="hidden" id="hiddenContextPath" value="${pageContext.request.contextPath}"/>
    <html:hidden property="financialStart" name="_r_revForm" styleId="financialStart"/>
    <html:hidden property="financialEnd" name="_r_revForm" styleId="financialEnd"/>
    <html:hidden property="closingMonth" name="_r_revForm" styleId="closingMonth"/>
	<html:hidden property="reportMonth1" name="_r_revForm" styleId="reportMonth1"/>
	<html:hidden property="reportYear1" name="_r_revForm" styleId="reportYear1"/>
	<html:hidden property="reportMonth2" name="_r_revForm" styleId="reportMonth2"/>
	<html:hidden property="reportYear2" name="_r_revForm" styleId="reportYear2"/>
	<html:hidden property="reportMonth3" name="_r_revForm" styleId="reportMonth3"/>
	<html:hidden property="reportYear3" name="_r_revForm" styleId="reportYear3"/>
    <html:hidden property="accessType" name="_r_revForm" styleId="accessType"/>
    <html:hidden property="finalReportFlag" name="_r_revForm" styleId="finalReportFlag"/>
    <html:hidden property="currentPage" name="_r_revForm" styleId="currentPage"/>
	<div class="">
		<h1 class="title"><bean:message key="screen.r_rev.title"/></h1>
    	<div class="section" style="border-top:2px solid #cecece;border-bottom:2px solid #cecece;padding:5px 20px;margin-top:-5px;">
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td style="padding:15px 0px;" align="left" width="55%" nowrap="nowrap">
						<bean:message key="screen.r_rev.financialYear"/><bean:message key="screen.r_rev.colon"/> <bean:write property="financialStart" name="_r_revForm"/> <bean:message key="screen.r_rev._"/> <bean:write property="financialEnd" name="_r_revForm"/>
					</td>
					<td align="left" nowrap="nowrap">
						<bean:message key="screen.r_rev.closingMonth"/><bean:message key="screen.r_rev.colon"/> <bean:write property="closingMonth" name="_r_revForm"/>
					</td>
				</tr>
				<tr>
					<td style="padding:15px 0px;" align="left" colspan="2">
					<c:choose>
						<c:when test="${_r_revForm.map.currentPage == 1}">
							<bean:message key="screen.r_rev.square"/><bean:message key="screen.r_rev.generateReport"/>
						</c:when>
						<c:otherwise>							
							<c:choose>
								<c:when test="${_r_revForm.map.accessType == 2}">
									<a id="reportLink" onclick="javascript:gotoPage('1');" class="reportLink" href="#"><bean:message key="screen.r_rev.square"/><bean:message key="screen.r_rev.generateReport"/></a>
								</c:when>
								<c:otherwise>
									<bean:message key="screen.r_rev.square"/><bean:message key="screen.r_rev.generateReport"/>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
						&nbsp;&nbsp;&nbsp;
					<c:choose>
						<c:when test="${_r_revForm.map.currentPage == 2}">
							<bean:message key="screen.r_rev.square"/><bean:message key="screen.r_rev.monthlyRevReport"/>
						</c:when>
						<c:otherwise>
							<a id="reportLink" class="reportLink" onclick="javascript:gotoPage('2');" href="#"><bean:message key="screen.r_rev.square"/><bean:message key="screen.r_rev.monthlyRevReport"/></a>
						</c:otherwise>
					</c:choose>
						&nbsp;&nbsp;&nbsp;
					<c:choose>
						<c:when test="${_r_revForm.map.currentPage == 3}">
							<bean:message key="screen.r_rev.square"/><bean:message key="screen.r_rev.advanceRevenue"/>
						</c:when>
						<c:otherwise>
							<a id="reportLink" class="reportLink" onclick="javascript:gotoPage('3');" href="#"><bean:message key="screen.r_rev.square"/><bean:message key="screen.r_rev.advanceRevenue"/></a>
						</c:otherwise>
					</c:choose>
					</td>
				</tr>
			</table>
		</div>
	</div>	
	<c:if test="${_r_revForm.map.currentPage == 1}">    
		<div class="">
			<div class="section">
				<div class="group result">
					<h2><bean:message key="screen.r_rev.generateReport"/></h2>
				</div>
			</div>
			<div style="width:100%">
				<table class="resultInfo" cellpadding="0" cellspacing="0" width="100%">
				  <tr>
				    <td class="" style="padding-left:30px;" width="10%"><bean:message key="screen.r_rev.report"/></td>
				    <td class="" style="padding-right: 10px;" width="15%">
				    	<bean:message key="screen.r_rev.month"/><bean:message key="screen.r_rev.colon"/>
				    	<html:select styleId="reportMonth" name="_r_revForm" property="generateReportMonth">
                          <html:options collection="NO_OF_MONTH" property="id" labelProperty="name"/>
                        </html:select>
                        <input type="hidden" id="originalRptMonth" class="originalRptMonth" value="<bean:write property="generateReportMonth" name="_r_revForm"/>"/>
				    </td>
				    <td class="" style="padding-right: 10px;" width="15%">
				    	<bean:message key="screen.r_rev.year"/><bean:message key="screen.r_rev.colon"/>
				    	<html:select styleId="reportYear" name="_r_revForm" property="generateReportYear">
                        	<html:options collection="LIST_REVENUE_YEAR" property="id" labelProperty="name"/>
                        </html:select>
                        <input type="hidden" id="originalRptYear" class="originalRptYear" value="<bean:write property="generateReportYear" name="_r_revForm"/>"/>
				    </td>
				    <td class="batchLink" style="padding-right: 10px;" width="69%">
				    	<a class="hyperLink" onclick="linkBathHistoryClick('<%=request.getContextPath()%>/E_SET/SC_E_SET_S02BL.do?SCR_ID=RREV&FUNC_ID=RR&TITLE_HDR=Generate Revenue Report');">
			    			<bean:message key="screen.r_rev.batchHistory"/>
			    		</a>
				    </td>
				  </tr>
				  <tr>
				    <td class="" style="padding-left:30px" colspan="2"></td>
				    <td colspan="2" style="padding-left: 50px;">
				   		<!-- <html:button property="forward_final" onclick="javascript:executeFinalReport();"><bean:message key='screen.r_rev.final'/></html:button> 
				    	<html:submit styleId="finalBtn" property="forward_final" onclick="javascript:executeFinalReport();"><bean:message key='screen.r_rev.final'/></html:submit>-->
				    	<input Id="finalBtn" type="button" onclick="javascript:executeFinalReport();" value="<bean:message key='screen.r_rev.final'/>"/>
				    </td>
				  </tr>
				  <tr>				  	
				    <td class="batchMsg" style="padding-left:30px; padding-bottom:20px;" colspan="4">
				    	<!--<span id="invalidMonthMsg" style="display:none;"><bean:message key="screen.r_rev.invalidReportMonth"/></span>
				    	<c:if test="${_r_revForm.map.finalReportFlag == 'Y'}">
				    		<bean:message key="screen.r_rev.batchProcessMsg"/>
				    	</c:if>-->
				    	<c:choose>
				    		<c:when test="${_r_revForm.map.finalReportFlag == 'Y'}">				    			
						    	<div id="batchMessage" class="batchMessage">
									<html:messages id="message" message="true">
										<bean:write name="message"/><br/>
									</html:messages>
								</div>
				    		</c:when>							
				    	</c:choose>
				    	<span id="batchMsg" style="display:none;"><bean:message key="screen.r_rev.batchProcessMsg"/></span>
				    	&nbsp;
				    </td>			  	
				</table>
			</div>
		</div>
	</c:if>
	<c:if test="${_r_revForm.map.currentPage == 2}">
		<html:hidden property="executeMthlyRptFlg" name="_r_revForm" styleId="executeMthlyRptFlg"/>
		<div class="">
			<div class="section">
				<div class="group result">
					<h2><bean:message key="screen.r_rev.monthlyRevReport"/></h2>
				</div>
			</div>
			<div style="width:100%">
				<table class="resultInfo" cellpadding="0" cellspacing="0" width="100%">
				  <tr>
				    <td class="" style="padding-left: 30px;" width="10%"><bean:message key="screen.r_rev.report"/></td>
				    <td class="" style="padding-right: 10px;" width="15%">
				    	<bean:message key="screen.r_rev.month"/><bean:message key="screen.r_rev.colon"/>
				    	<html:select styleId="reportMonth" name="_r_revForm" property="monthlyReportMonth">
                          <html:options collection="NO_OF_MONTH" property="id" labelProperty="name"/>
                        </html:select>
                        <input type="hidden" id="originalRptMonth" class="originalRptMonth" value="<bean:write property="monthlyReportMonth" name="_r_revForm"/>"/>
					</td>
				    <td class="" style="padding-right: 10px;" width="10%">
				    	<bean:message key="screen.r_rev.year"/><bean:message key="screen.r_rev.colon"/>
				    	<html:select styleId="reportYear" name="_r_revForm" property="monthlyReportYear">
                        	<html:options collection="LIST_REVENUE_YEAR" property="id" labelProperty="name"/>                        
                        </html:select>
                        <input type="hidden" id="originalRptYear" class="originalRptYear" value="<bean:write property="monthlyReportYear" name="_r_revForm"/>"/>
				    </td>
				    <td class="" style="padding-right: 25px;">
				    <!-- <input type="button" value="<bean:message key="screen.r_rev.search"/>"/> -->
				    	<html:submit styleId="monthlyReportBtn" property="forward_search" onclick="javascript:executeMonthlyRptSearch();"><bean:message key='screen.r_rev.search'/></html:submit>
				    	<logic:present name="reGenerateFlag">
				    		<logic:equal name="reGenerateFlag" value="Y">
				    			&nbsp;&nbsp;&nbsp;<html:submit property="forward_regenReport"><bean:message key='screen.r_rev.regenerateReport'/></html:submit>
						  	</logic:equal>
				    	</logic:present>
				    </td>
				  </tr>
				  <tr>
				    <td align="right" class="" style="padding-left:10px" colspan="4"><br/></td>
				  </tr>
				  <tr>
				    <td class="" style="padding:0px 25px" colspan="4">
				    	<logic:present name="resultList">
					    	<table class="resultInfo subresultInfo" cellpadding="0" cellspacing="0" width="100%">
							  <tr>
							    <th style="padding:10px 0px 10px 10px" width="10%"><bean:message key="screen.r_rev.month"/></th>
							    <th class="" style="padding: 10px 0px 10px 10px;"><bean:message key="screen.r_rev.download"/></th>
							    <th class="" style="padding: 10px 0px 10px 10px;"></th>
							  </tr>
							  <!-- #249 START-->
							  	<%-- <c:forEach begin="0" end="${fn:length(resultList)}" var="i" step="2">
							  		<tr>
							  			<td class="" style="padding: 10px 0px 10px 10px;" colspan="1"><c:if test="${i eq 0}"><c:out value="${resultList[0].FILEDATE}" /></c:if></td>
							  			<td class="" style="padding: 10px 0px 10px 10px;" colspan="1">
							  				<a href="<%=request.getContextPath()%>/R_REV/RP_R_REV_S01DownloadBL.do?filePath=${resultList[i].FILELOCATION}${resultList[i].FILENAME}"><c:out value="${resultList[i].FILENAME}" /></a>
							  			</td>
							  			<td class="" style="padding: 10px 0px 10px 10px;" colspan="1">
							  				<a href="<%=request.getContextPath()%>/R_REV/RP_R_REV_S01DownloadBL.do?filePath=${resultList[i+1].FILELOCATION}${resultList[i+1].FILENAME}"><c:out value="${resultList[i+1].FILENAME}" /></a>
							  			</td>
							  		</tr>
							  	</c:forEach> --%>
							  	<tr>
							  		<%-- <c:out value="${fn:length(resultList)}"/> --%>
							  		<td class="" style="padding: 10px 0px 10px 10px;" colspan="1"><c:out value="${resultList[0].FILEDATE}" /></td>
						  			<td class="" style="padding: 10px 0px 10px 10px;" colspan="1" valign="top">
						  			<fmt:formatNumber value="${(fn:length(resultList)/2)-((fn:length(resultList)/2)%1)}" type="number" var="halfList" pattern="#"/>
						  				<table>
						  					<c:forEach begin="0" end="${fn:length(resultList)}" var="i">
						  						<c:if test="${i <= halfList}">
						  							<tr><td><a href="<%=request.getContextPath()%>/R_REV/RP_R_REV_S01DownloadBL.do?filePath=${resultList[i].FILELOCATION}${resultList[i].FILENAME}"><c:out value="${resultList[i].FILENAME}" /></a></td></tr>
						  						</c:if>
						  					</c:forEach>
						  				</table>
						  			</td>
						  			<td class="" style="padding: 10px 0px 10px 10px;" colspan="1" valign="top">
						  				<table>
						  					<c:forEach begin="0" end="${fn:length(resultList)}" var="j">
						  						<c:if test="${j > halfList}">
						  							<tr><td><a href="<%=request.getContextPath()%>/R_REV/RP_R_REV_S01DownloadBL.do?filePath=${resultList[j].FILELOCATION}${resultList[j].FILENAME}"><c:out value="${resultList[j].FILENAME}" /></a></td></tr>
						  						</c:if>
						  					</c:forEach>
						  				</table>
						  			</td>
							  	</tr>
							  	<!-- #249 END-->					  
							</table>
						</logic:present>
						<html:hidden property="filePath" name="_r_revForm" styleId="filePath"/>
						<div class="message">
							<html:messages id="message" message="true">
								<bean:write name="message"/><br/>
							</html:messages>
						</div>
				    </td>
				  </tr>
				  <tr>
				    <td align="right" class="" style="padding-left:10px" colspan="4"><br/></td>
				  </tr>
				</table>
			</div>
		</div>
	</c:if>
	<c:if test="${_r_revForm.map.currentPage == 3}">
		<html:hidden property="executeMthlyRptFlg" name="_r_revForm" styleId="executeMthlyRptFlg"/>
		<div class="">
			<div class="section">
				<div class="group result">
					<h2><bean:message key="screen.r_rev.advanceRevenue"/></h2>
				</div>
			</div>
			<div style="width:100%">
				<table class="resultInfo" cellpadding="0" cellspacing="0" width="100%">
				  <tr>
				    <td class="" style="padding-left: 30px;" width="10%"><bean:message key="screen.r_rev.report"/></td>
				    <td class="" style="padding-right: 10px;" width="15%">
				    	<bean:message key="screen.r_rev.month"/><bean:message key="screen.r_rev.colon"/>
				    	<html:select styleId="reportMonth" name="_r_revForm" property="monthlyReportMonth">
                          <html:options collection="NO_OF_MONTH" property="id" labelProperty="name"/>
                        </html:select>
                        <input type="hidden" id="originalRptMonth" class="originalRptMonth" value="<bean:write property="monthlyReportMonth" name="_r_revForm"/>"/>
					</td>
				    <td class="" style="padding-right: 10px;" width="10%">
				    	<bean:message key="screen.r_rev.year"/><bean:message key="screen.r_rev.colon"/>
				    	<html:select styleId="reportYear" name="_r_revForm" property="monthlyReportYear">
                        	<html:options collection="LIST_REVENUE_YEAR" property="id" labelProperty="name"/>                        
                        </html:select>
                        <input type="hidden" id="originalRptYear" class="originalRptYear" value="<bean:write property="monthlyReportYear" name="_r_revForm"/>"/>
				    </td>
				    <td class="" style="padding-right: 25px;">
				    <!-- <input type="button" value="<bean:message key="screen.r_rev.search"/>"/> -->
				    	<html:submit styleId="advanceRevenueBtn" property="forward_search" onclick="javascript:executeMonthlyRptSearch();"><bean:message key='screen.r_rev.search'/></html:submit>
				    </td>
				  </tr>
				  <tr>
				    <td align="right" class="" style="padding-left:10px" colspan="4"><br/></td>
				  </tr>
				  <tr>
				    <td class="" style="padding:0px 25px" colspan="4">
				    	<logic:present name="resultList">
				    	<table class="resultInfo subresultInfo" cellpadding="0" cellspacing="0" width="100%">
						  <tr>
						    <th style="padding:10px 0px 10px 10px" width="10%"><bean:message key="screen.r_rev.month"/></th>
						    <th class="" style="padding: 10px 0px 10px 10px;"><bean:message key="screen.r_rev.download"/></th>
						    <th class="" style="padding: 10px 0px 10px 10px;"><bean:message key="screen.r_rev.uploadFile"/></th>
						  </tr>
						  	<logic:iterate name="resultList" id="fileList">
						  		<tr>
						  			<td class="" style="padding: 10px 0px 10px 10px;" colspan="1"><bean:write name="fileList" property="FILEDATE"/></td>
						  			<td class="" style="padding: 10px 0px 10px 10px;" colspan="1">
						  				<a href="<%=request.getContextPath()%>/R_REV/RP_R_REV_S01DownloadBL.do?filePath=<bean:write name="fileList" property="FILELOCATION"/><bean:write name="fileList" property="FILENAME"/>"><bean:write name="fileList" property="FILENAME"/></a>
						  			</td>
						  			<td>
						  				<logic:iterate name="resultList2" id="fileList2">
						  					<a href="<%=request.getContextPath()%>/R_REV/RP_R_REV_S01DownloadBL.do?filePath=<bean:write name="fileList2" property="FILELOCATION"/><bean:write name="fileList2" property="FILENAME"/>"><bean:write name="fileList2" property="FILENAME"/></a>
						  					<br/>
						  				</logic:iterate>
						  				<br/>
						  			</td>
						  		</tr>
						  	</logic:iterate>				  
						</table>
						</logic:present>
						<html:hidden property="filePath" name="_r_revForm" styleId="filePath"/>
						<div class="message">
							<html:messages id="message" message="true">
								<bean:write name="message"/><br/>
							</html:messages>
						</div>
				    </td>
				  </tr>
				  <tr>
				    <td align="right" class="" style="padding-left:10px" colspan="4"><br/></td>
				  </tr>
				</table>
			</div>
		</div>
	</c:if>
	<div class="error">
		<html:messages id="message">
			<bean:write name="message"/><br/>
		</html:messages>
	</div>
  </ts:form>
</body>
</html>