<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
	<link href="${pageContext.request.contextPath}/B_BIL/css/b_bil_s04.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/B_BIL/js/B_BIL_S04.js"></script>
	<script>
	function changewidth(){
		var popupWidth = window.screen.width*80/100;
		popupWidth = popupWidth - 45;
	    document.documentElement.childNodes[1].innerHTML="<div style='width:"+popupWidth+"px'>"+document.documentElement.childNodes[1].innerHTML+"</div>";
	}
	</script>
</head>
<body id="b" onload="changewidth();">
<ts:form action="/B_BIL_S04CustPlanSearch" >
<%-- plan status --%>
<t:defineCodeList id="LIST_PLANSTATUS" />
<%-- rate mode --%>
<t:defineCodeList id="LIST_RATEMODE" />
<%-- rate type --%>
<t:defineCodeList id="LIST_RATETYPE" />
<%-- bill instruction list --%>
<t:defineCodeList id="BILL_INSTRUCTION_LIST" />
<html:hidden name="_b_bil_04Form" property="doSearch" value="Y"/>
<html:hidden name="_b_bil_04Form" property="idCust"/>
<html:hidden name="_b_bil_04Form" property="idCust"/>
<html:hidden name="_b_bil_04Form" property="isCpm"/>
<input type="hidden" name="ERR1SC033" id="ERR1SC033" value="<bean:message key="error.ERR1SC033"/>" />
<table class="subHeader">
	<tr>
		<td>
			<logic:equal value="1" property="isCpm" name="_b_bil_04Form">
				<bean:message key="screen.b_bil.s04.title_cpm"/>
			</logic:equal>
			<logic:notEqual value="1" property="isCpm" name="_b_bil_04Form">
				<bean:message key="screen.b_bil.s04.title"/>
			</logic:notEqual>
		</td>
	</tr>
</table>
<table class="inputInfo" cellpadding="0" cellspacing="0">
  <tr>
    <td class="col1Top" style="text-align: left;padding-left:10px" width="15%"><bean:message key="screen.b_bil.s04.custname"/> </td>
    <td class="col2Top" width="30%">
    	<bean:message key="screen.b_bil.s04.colon"/>&nbsp;<bean:write name="_b_bil_04Form" property="custName"/>
    </td>
    <td class="col3Top" style="text-align: left;padding-left:40px" width="15%"><bean:message key="screen.b_bil.s04.custId"/> </td>
    <td class="col4Top" width="40%">
        <bean:message key="screen.b_bil.s04.colon"/>&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="_b_bil_04Form" property="idCust"/>
    </td>
  </tr>
  <tr>
    <td style="text-align: left;padding-left:10px"><bean:message key="screen.b_bil.s04.billdesc"/> </td>
    <td>
    	<bean:message key="screen.b_bil.s04.colon"/>&nbsp;<html:text property="gdcBillingDescription" style="width:100%;" name="_b_bil_04Form"/>   
    </td> 	
    <td style="text-align: left;padding-left:40px" ><bean:message key="screen.b_bil.s04.serviceStatus"/></td>
    <td rowspan="3" valign="top">
          <logic:iterate name="_b_bil_04Form" property="tbxServiceStatus" id="hidServStatus">
          <input type="hidden" name="hidServStatus" class="hidServStatus" value="<bean:write name="hidServStatus"/>"/>
          </logic:iterate>
          <table class="inputInfo" cellpadding="0" cellspacing="0" style="font-size : 15px;margin-top:0.00in;margin-left:-0.04in;">
           <tr style="margin-left:0.00in;">
               <td><bean:message key="screen.b_bil.s04.colon"/></td>
	           <td>
	           	   <html:checkbox name="_b_bil_04Form" property="tbxServiceStatus" styleClass="searchServiceStatus" value="PS3">
	               <bean:message key="screen.b_bil.s04.Active"/>
	           	   </html:checkbox>
	           </td>
	           <td>
	           	   <html:checkbox name="_b_bil_04Form" property="tbxServiceStatus" styleClass="searchServiceStatus" value="PS7">
	               <bean:message key="screen.b_bil.s04.Terminated"/>
	           	   </html:checkbox>
	           </td>
	           <td>
	           	   <html:checkbox name="_b_bil_04Form" property="tbxServiceStatus" styleClass="searchServiceStatus" value="PS6">
	               <bean:message key="screen.b_bil.s04.Suspended"/>
	           	   </html:checkbox>
	           </td>
           </tr>
           <tr>
          	   <td></td>
	           <%-- <td>
	               <logic:notEqual value="0" name="_b_bil_04Form" property="isdis">
	               <html:checkbox name="_b_bil_04Form" property="tbxServiceStatus" styleClass="searchServiceStatus" value="PS2">
	               <bean:message key="screen.b_bil.s04.InApproval"/>
	           	   </html:checkbox>
	           	   </logic:notEqual>
	           </td> --%>
	           <td>
	               <logic:notEqual value="0" name="_b_bil_04Form" property="isdis">
	           	   <html:checkbox name="_b_bil_04Form" property="tbxServiceStatus" styleClass="searchServiceStatus" value="PS8">
	               <bean:message key="screen.b_bil.s04.Rejected"/>
	           	   </html:checkbox>
	           	   </logic:notEqual>
	           </td>
	           <td>
	           </td>
	           <td></td>
           </tr>
	      </table>
	</td>
  </tr>
  <tr>
    <td style="text-align: left;padding-left:10px"><bean:message key="screen.b_bil.s04.itemdesc"/> </td>
    <td>
    	<bean:message key="screen.b_bil.s04.colon"/>&nbsp;<html:text property="gdcItemDescription" style="width:100%;" name="_b_bil_04Form"/>   
    </td> 
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td style="text-align: left;padding-left:10px"><bean:message key="screen.b_bil.s04.subid"/> </td>
    <td >
    	<bean:message key="screen.b_bil.s04.colon"/>&nbsp;<html:text property="tbxSubscription" style="width:100%;" name="_b_bil_04Form"/>   
    </td> 	
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td style="text-align: left;padding-left:10px"><bean:message key="screen.b_bil.s04.jobNo"/> </td>
    <td >
    	<bean:message key="screen.b_bil.s04.colon"/>&nbsp;<html:text property="jobNo" style="width:100%;" name="_b_bil_04Form"/>   
    </td> 	
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td class="colBottom" colspan="4" style="height:5px"></td>
  </tr>
</table>
<table class="buttonGroup" cellpadding="0" cellspacing="0">
	<tr>
		<td>
			<button type="submit" onclick="document.forms[0].startIndex.value = 0;"><bean:message key="screen.b_bil.s04.search"/> </button>		
			<button onclick="resetAct()"><bean:message key="screen.b_bil.s04.reset"/> </button>
			<button id="insertBtn" onclick="insertAct('<%=request.getContextPath()%>')" disabled="disabled"><bean:message key="screen.b_bil.s04.insert"/></button>
		</td>
	</tr>
</table>
<table class="tablePageLink" cellpadding="0" cellspacing="0">
  	<tr>
		<td>			
			<bean:message key="screen.b_bil.s04.gotoplan"/><bean:message key="screen.b_bil.s04.colon"/>&nbsp;
			<ts:pageLinks id="userPageLinks"
				action="${pageContext.request.contextPath}/B_BIL/B_BIL_S04CustPlanSearch.do"
				name="_b_bil_04Form" 
				rowProperty="row" 
				totalProperty="totalCount"
				indexProperty="startIndex" 
				currentPageIndex="now"
				totalPageCount="total" 
				submit="true"/>
            <logic:present name="userPageLinks">  
				<bean:write name="userPageLinks" filter="false"/>
			</logic:present>
		</td>
		<td align="right" style="padding-right:40px;font-weight:bold">
		<bean:message key="screen.b_bil.s04.totalplan"/>&nbsp;<bean:message key="screen.b_bil.s04.colon"/>
		<bean:write name="_b_bil_04Form" property="totalCount" filter="false"/>
		</td>	
	</tr>
</table>
<table class="resultInfo search_result_table" cellpadding="0" cellspacing="0" style="margin-top:2px">
	<tr>
		<td class="header" style="text-align: left;padding-left:5px;" width="3%">
		 <input type="checkbox" id="totalcheck" name="totalcheck" onclick="checkAll(this);"/>
		</td>
		<td class="header" style="text-align: left;padding-left:5px;" width="32%" valign="bottom"><bean:message key="screen.b_bil.s04.subidbildesc"/></td>
		<td class="header" style="text-align: left;padding-left:5px;" width="10%" valign="bottom"><bean:message key="screen.b_bil.s04.transtype"/></td>
		<td class="header" style="text-align: left;padding-left:5px;" width="10%" valign="bottom"><bean:message key="screen.b_bil.s04.billinst"/></td>
		<td class="header" style="text-align: left;padding-left:5px;" width="5%" valign="bottom"><bean:message key="screen.b_bil.s04.cur"/></td>
		<td class="header" style="text-align: right;padding-right:5px;" width="10%" valign="bottom"><bean:message key="screen.b_bil.s04.Amount"/></td>
		<td class="header" style="text-align: left;padding-left:5px;" width="10%" valign="bottom"><bean:message key="screen.b_bil.s04.from"/></td>
		<td class="header" style="text-align: left;padding-left:5px;" width="10%" valign="bottom"><bean:message key="screen.b_bil.s04.to"/></td>
		<td class="header" style="text-align: left;padding-left:5px;" width="10%" valign="bottom"><bean:message key="screen.b_bil.s04.status"/></td>
	</tr>
	<tr>
	   <td>&nbsp;</td>
	   <td style="text-align: left;padding-left:5px;"><bean:write name="_b_bil_04Form" property="subID"></bean:write></td>
	   <td style="text-align: left;padding-left:5px;"><bean:write name="_b_bil_04Form" property="trans"></bean:write></td>
	   <td style="text-align: left;padding-left:5px;"><t:writeCodeValue codeList="BILL_INSTRUCTION_LIST" key="${_b_bil_04Form.map.billinst}"/></td>
	   <td style="text-align: left;padding-left:5px;"><bean:write name="_b_bil_04Form" property="cur"></bean:write></td>
	   <td style="text-align: right;padding-right:5px;"><bean:write name="_b_bil_04Form" property="TAmount" format="#,##0.00"></bean:write></td>
	   <td>&nbsp;</td>
	   <td>&nbsp;</td>
	   <td>&nbsp;</td>
	</tr>
	<logic:notEmpty name="_b_bil_04Form" property="custPlanList">
	<% int subplanid = 1; %>
	<logic:iterate id="service" name="_b_bil_04Form" property="custPlanList" indexId="index">
	<tr>
		<td>&nbsp;</td>
		<td style="text-align: left;padding-left:5px;"><font color="blue"><bean:write name="service" property="billDesc"></bean:write></font></td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td style="text-align: left;padding-left:5px;"><bean:write name="_b_bil_04Form" property="cur"></bean:write></td>
		<td style="text-align: right;padding-right:5px;"><bean:write name="service" property="SAmount" format="#,##0.00"></bean:write></td>
		<td style="text-align: left;padding-left:5px;"><bean:write name="service" property="serviceFrom"></bean:write></td>
		<td style="text-align: left;padding-left:5px;"><bean:write name="service" property="serviceTo"></bean:write></td>
		<td style="text-align: left;padding-left:5px;"><t:writeCodeValue codeList="LIST_PLANSTATUS" key="${service.status}"/></td>
	</tr>
	<logic:notEmpty name="service" property="subPlanList">
      <tr><td colspan="9" style="padding-left:4px;padding-right:10px">
      <table cellpadding="0" cellspacing="0" border="0" style="margin-bottom:2px;width:100%;height:100%;color: #a04c51;font-size:15px;font-family:'Calibri';border:1px solid #CEE7FF;">
	      <tr>
	      	<td style="padding-left:0.5px;background-color: #CEE7FF;" width="3%">&nbsp;</td>
	      	<td style="padding-left:3px;background-color: #CEE7FF;" width="40%"><bean:message key="screen.b_bil.s04.itemdesc"/></td>
	      	<td style="padding-left:5px;background-color: #CEE7FF;" width="10%"><bean:message key="screen.b_bil.s04.ratetype"/></td>
	      	<td style="padding-left:5px;background-color: #CEE7FF;" width="10%"><bean:message key="screen.b_bil.s04.ratemode"/></td>
	      	<td style="padding-right:5px;background-color: #CEE7FF;" width="10%" align="right"><bean:message key="screen.b_bil.s04.qty"/></td>
	      	<td style="padding-right:5px;background-color: #CEE7FF;" width="10%" align="right"><bean:message key="screen.b_bil.s04.uprice"/></td>
	      	<td style="padding-right:5px;background-color: #CEE7FF;" width="10%" align="right"><bean:message key="screen.b_bil.s04.Amount"/></td>
	      	<td style="padding-left:5px;background-color: #CEE7FF;" width="7%"><bean:message key="screen.b_bil.s04.gst"/></td>
	      </tr>
		  <logic:iterate id="subPlan" name="service" property="subPlanList">
		  <tr>
			 <td style="padding-left:0.5px;border-top:1px solid #CEE7FF;" width="3%"><input type="checkbox" name="idPlanGrp" onclick="planChecked();" value="<%=subplanid %>"/>
			 <input type="hidden" name="<%=subplanid %>" value="${subPlan.ID_CUST_PLAN_GRP}"/>
			 <input type="hidden" name="<%=subplanid %>" value="${subPlan.ID_CUST_PLAN_LINK}"/>
			 </td>
			 <td style="padding-left:3px;border-top:1px solid #CEE7FF;word-break:break-all;"><bean:write name="subPlan" property="ItemDesc"/></td>
			 <td style="padding-left:5px;border-top:1px solid #CEE7FF;"><t:writeCodeValue codeList="LIST_RATETYPE" key="${subPlan.RateType}"/></td>
			 <td style="padding-left:5px;border-top:1px solid #CEE7FF;"><t:writeCodeValue codeList="LIST_RATEMODE" key="${subPlan.RateMode}"/></td>
			 <td style="padding-right:5px;border-top:1px solid #CEE7FF;" align="right"><bean:write name="subPlan" property="Quantity"/></td>
			 <td style="padding-right:5px;border-top:1px solid #CEE7FF;" align="right"><bean:write name="subPlan" property="UPrice" format="#,##0.00"/></td>
			 <td style="padding-right:5px;border-top:1px solid #CEE7FF;" align="right"><bean:write name="subPlan" property="Amount" format="#,##0.00"/></td>
			 <td style="padding-left:5px;border-top:1px solid #CEE7FF;"><bean:write name="subPlan" property="GST"/>&nbsp;</td>
		  </tr>
		  <% subplanid += 1; %>
		  </logic:iterate>
      </table>
      </td></tr>
	</logic:notEmpty>
	</logic:iterate>
	</logic:notEmpty>
	<tr>
		<td style="border-bottom: #cfcfcf 1px solid;" colspan="9"></td>
	</tr>
</table>
</ts:form>
</body>
</html>