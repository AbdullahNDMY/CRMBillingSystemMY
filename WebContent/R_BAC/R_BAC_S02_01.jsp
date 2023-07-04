<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/bs" prefix="bs" %>

<html>
<head>
   	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
	<link href="${pageContext.request.contextPath}/R_ACR/css/r_acr.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
	<script type="text/javascript">
	   function resetIndex(){
	       $("input[name='startIndex']").val(0);
	       $("#startIndex#").val(0);
	   }
	   
	   function downLoadExcel(billAcc){
	       var url = "<%=request.getContextPath()%>/R_BAC/R_BAC_S02_03BL.do?billAcc="+ billAcc;
	       var formAction = $("form").eq(0).attr("action");
	       $("form").eq(0).attr("action",url);
	       $("form").eq(0).submit();
	       $("form").eq(0).attr("action",formAction);
	   }
	   
	   
       function openDetail(type,billAcc){

            var url = "<%=request.getContextPath()%>/R_BAC/R_BAC_S02_02BL.do?type=" + type + "&billAcc="+ billAcc;
            var width = window.screen.width * 90 / 100;
            var height = window.screen.height * 80 / 100;
            var left = Number((screen.availWidth / 2) - (width / 2));
            var top = Number((screen.availHeight / 2) - (height / 2));
            var offsetFeatures = "width=" + width + ",height=" + height
                    + ",left=" + left + ",top=" + top + "screenX=" + left
                    + ",screenY=" + top;
            var strFeatures = "toolbar=no, status=no, menubar=no,location=no,scrollbars=yes, resizable=yes"
                    + "," + offsetFeatures;
            var newwindow = window.open(url, null, strFeatures);
            if (window.focus) {
                newwindow.focus();
            }
        }
    </script>
	<title></title>
</head>
<body>
<ts:form action="/R_BAC_S02DSP" >
	<h1 class="title">Billing Account - Account Ledger Checking</h1>
      <div class="section" style="border-top:2px solid #cecece;border-bottom:2px solid #cecece;padding:5px 20px;margin-top:-5px;">
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td align="left">
						<bean:message key="screen.r_bac.lable.billacc"/><font color="red">*</font>
					</td>
					<td align="left">
						<bean:message key="screen.r_bac.lable.colon"/>
					</td>
					<td align="left">
						<html:text name="_r_bacForm" property="billAcc" style="width:230px" maxlength="20"></html:text>
					</td>
					<td>
					    <html:checkbox name="_r_bacForm" property="type" value="Y"></html:checkbox>Result &gt; 0
					</td>
				</tr>
			</table>
	   </div>
	   <br/>
	   <div>
			<html:submit property="forward_search" onclick="showLoadingTipWindow();resetIndex()"><bean:message key='screen.r_bac.button.search'/></html:submit>
			<bs:buttonLink action="/R_BAC_S02SCR" value="Reset"/>
	        <html:submit property="forward_export" onclick="showLoadingTipWindow();">Export Result</html:submit>
	        <html:submit property="forward_analysis1" onclick="showLoadingTipWindow();">Analysis1</html:submit>
	        <html:submit property="forward_analysis2" onclick="showLoadingTipWindow();">Analysis2</html:submit>
	        <html:submit property="forward_analysis3" onclick="showLoadingTipWindow();">Analysis3</html:submit>
		</div>
		<br/>
		<div class="section">
			<div class="group result">
				<h2><bean:message key="screen.r_bac.header.searchfound" /><bean:message key="screen.r_bac.lable.colon"/> ${_r_bacForm.map.totalRow}</h2>
			</div>
			<div class="pageLink">
				<bean:message key="screen.r_bac.header.gotopage"/> <bean:message key="screen.r_bac.lable.colon"/>
				<ts:pageLinks 
	    			id="curPageLinks"
					action="${pageContext.request.contextPath}/R_BAC/R_BAC_S02_01BL.do" 
					name="_r_bacForm" 
					rowProperty="row"
					totalProperty="totalRow" 
					indexProperty="startIndex"
					currentPageIndex="now" 
					totalPageCount="total"
					submit="true"/>
				<logic:present name="curPageLinks">  
					<bean:write name="curPageLinks" filter="false"/>
				</logic:present>
			</div>
		</div>
	<div class="resultTableTd">
	<table class="resultInfo" cellpadding="0" cellspacing="0" width="100%">
	  <tr>
	    <td class="header" style="padding-left:5px;vertical-align:top;" width="4%">No</td>
	    <td class="header" style="padding-right:5px;vertical-align:top;" width="8%">Billing<br/>Account<br/>(Ledger)</td>
	    <td class="header" style="padding-right:5px;vertical-align:top;" width="8%">Total Amount Due</td>
	    <td class="header" style="padding-right:5px;vertical-align:top;" width="12%">Latest Invoice</td>
	    <td class="header" style="padding-right:5px;vertical-align:top;" width="8%">1<br/>Receipts No<br/>Details</td>
	    <td class="header" style="padding-right:5px;vertical-align:top;" width="8%">2<br/>Receipt<br/>Over Match</td>
	    <td class="header" style="padding-right:5px;vertical-align:top;" width="8%">3<br/>Receipt Not<br/>Fully Match</td>
        <td class="header" style="padding-right:5px;vertical-align:top;" width="8%">4<br/>Invoice<br/>Over Match</td>
        <td class="header" style="padding-right:5px;vertical-align:top;" width="7%">5<br/>Invoice Not<br/>Fully Match</td>
	    <td class="header" style="padding-right:5px;vertical-align:top;" width="9%">6<br/>Amount Received&lt;<br/>Balance Amount</td> 
	  </tr>
	  <c:forEach items="${_r_bacForm.map.resultList}" var="item" varStatus="status" >
		  <tr>
		    <td class="defaultText" style="padding-left:5px">
		    	${item.index}
		    </td>
		    <td class="defaultText" style="padding-right:5px">
		    	<a href="#" onclick="downLoadExcel('${item.billacc}')">
                    ${item.billacc}
                </a>
		    </td>
		    <td class="defaultText" style="padding-left:5px">
                <fmt:formatNumber value="${item.totalAmtDue}" pattern="#,##0.00"></fmt:formatNumber>
            </td>
            <td class="defaultText" style="padding-left:5px">
                ${item.latestInvoice}
            </td>
		    <td class="defaultText" style="padding-right:5px" >
			    <a href="#" onclick="openDetail('1','${item.billacc}')">
	                ${item.receiptsNoDetailsSize}
	            </a>
		    </td>
		    <td class="defaultText" style="padding-right:5px" >
                <a href="#" onclick="openDetail('2','${item.billacc}')">
                    ${item.receiptOverMatchSize}
                </a>
            </td>
		    <td class="defaultText" style="padding-right:5px" >
		        <a href="#" onclick="openDetail('3','${item.billacc}')">
		    	  ${item.receiptNotFullyMatchSize}
                </a>
		    </td>
		    <td class="defaultText" style="padding-right:5px" >
		        <a href="#" onclick="openDetail('4','${item.billacc}')">
		   		${item.invoiceOverMatchSize}
                </a>
            </td>
		    <td class="defaultText" style="padding-right:5px" >
		        <a href="#" onclick="openDetail('5','${item.billacc}')">
                ${item.invoiceNotFullyMatchSize}
                </a>
            </td>
		    <td class="defaultText" style="padding-right:5px; text-align:left;" > 
		        <a href="#" onclick="openDetail('6','${item.billacc}')">
		    	 ${item.receiptAmountNegativeSize}
                </a>
		    </td>
		  </tr>
	   </c:forEach>
	</table>
	</div>		
	<div class="message">
		<ts:messages id="message" message="true"><bean:write name="message"/></ts:messages>
	</div>
	<div class="error">
		<html:messages id="message">
			<bean:write name="message"/><br/>
		</html:messages>
	</div>

  </ts:form>
</body>
</html>