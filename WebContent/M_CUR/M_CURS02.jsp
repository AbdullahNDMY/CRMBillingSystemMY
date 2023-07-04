<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
   	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
	<link href="<%=request.getContextPath()%>/M_CUR/css/m_curs02.css" rel="stylesheet" type="text/css" /> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/M_CUR/js/m_curs02.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>	
<title>Insert title here</title>
</head>
<body id="m_curs02" onload="initMain();">
<%
String accessType = ((nttdm.bsys.common.fw.BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT")).getUserAccessInfo("M", "M-CUR").getAccess_type();
boolean fullAccess = accessType.equals("2");
%>
	<table class="QCSSearchConditionTable">
	  <tr style="">
	    <td class="Title"><bean:message key="screen.m_cur.title"/></td> 
	  </tr>
	</table>
	<table>
		<tr><td height="3px" /></tr>
	</table> 
	<ts:form action="/M_CURS02Action" >
		<!-- Currency code list -->
		<t:defineCodeList id="LIST_CURRENCY"/> 
		<t:defineCodeList id="LIST_CURRENCY_SYMBOL"/>  
		<table class="QCSSearchResultTable">
			<tr>
				<td colspan="2" class="header"><bean:message key="screen.m_cur.generalInf"/></td>
			</tr>
			<tr>
				<td class="normalcol1" width="15%">
					<bean:message key="screen.m_cur.dateCol"/>
					<span style="color: red"><bean:message key="screen.m_cur.label_mandatory"/></span>
				</td> 
				<td class="normalcol2" id="rate_dateCol"><bean:message key="screen.m_cur.colon"/>
					<html:text name="_m_curForm" property="rate_date" maxlength="10" readonly="true" styleClass="BlueStyle-textbox" styleId="rate_dateText" onchange="setChangeDate();"/>
                            <t:inputCalendar for="rate_dateText" format="dd/MM/yyyy" /> 
				</td>
			</tr>
			<tr>
				<td class="normalcol1">
					<bean:message key="screen.m_cur.currency"/>
					<span style="color: red"><bean:message key="screen.m_cur.label_mandatory"/></span>
				</td> 
				<td class="normalcol2"><bean:message key="screen.m_cur.colon"/>
    				<html:select name="_m_curForm" property="cur_code" styleId="cboCurrency" onchange="selectSymbol(this);setChangeDate();">
			          <html:option value="" ><bean:message key="screen.m_cur.label_blank"/></html:option>
			          <html:options collection="LIST_CURRENCY" property="id" labelProperty="name"/>
			        </html:select>	
				</td>
			</tr>
			<tr>
				<td class="normalcol1">
					<bean:message key="screen.m_cur.currencySymbol"/>
				</td> 
				<td class="normalcol2">					
					<div id="currency_symbol"> <bean:message key="screen.m_cur.colon"/></div>
				</td>
			</tr>
			<tr>
				<td class="normalcol1">
					<bean:message key="screen.m_cur.toForex"/>
					<span style="color: red"><bean:message key="screen.m_cur.label_mandatory"/></span>
				</td> 
				<td class="normalcol2"><bean:message key="screen.m_cur.colon"/> 
					<html:text property="rate_to" maxlength="13" name="_m_curForm" onchange="setChangeDate();" onkeypress="return onlyDecNumbers(this,event)"></html:text>
				</td>
			</tr>
			<tr>
				<td class="normalcol1">
					<bean:message key="screen.m_cur.frForex"/>
					<span style="color: red"><bean:message key="screen.m_cur.label_mandatory"/></span>
				</td> 
				<td class="normalcol2"><bean:message key="screen.m_cur.colon"/> 
					<html:text property="rate_from" name="_m_curForm" maxlength="13" onchange="setChangeDate();" onkeypress="return onlyDecNumbers(this,event)"></html:text>
				</td>
			</tr> 
		</table>
		<table class="QCSSearchResultTable">
			<tr>
				<td colspan="2" class="footer"></td>
			</tr>
		</table>
		<table>
			<tr><td height="3px" /></tr>
		</table> 
		<input type="hidden" id="hiddenMsgExitConfirmation" value="<bean:message key="info.ERR4SC001"/>"/>
		<html:hidden property="date_created" name="_m_curForm"/>
		<html:hidden property="cur_code" name="_m_curForm"/>
		<html:hidden property="rate_date" name="_m_curForm"/>
		<html:hidden property="datefrom" name="_m_curForm"/> 
		<html:hidden property="dateto" name="_m_curForm"/> 
		<html:hidden property="cur_code_search" name="_m_curForm"/> 
		<html:hidden property="startIndex" name="_m_curForm"/>  
		<html:hidden property="row" name="_m_curForm"/>  
		<html:hidden name="_m_curForm" property="unsearch" styleId="unsearch"/>
		<html:hidden property="updated" styleId="updated" value="0"/>
		<button onclick="save_data();" id="save"><bean:message key="screen.m_cur.save"/></button> &nbsp;
		<button onclick="edit_data();" id="edit"><bean:message key="screen.m_cur.edit"/></button> &nbsp;
		<button onclick="delete_data('<bean:message key="info.ERR4SC002"/>');" id="delete"><bean:message key="screen.m_cur.delete"/></button> &nbsp;
		<button onclick="exit_data();"><bean:message key="screen.m_cur.exit"/></button> &nbsp;
		<ts:submit value='save' property="forward_save" style="visibility:hidden"/> 
		<ts:submit value='edit' property="forward_edit" style="visibility:hidden"/> 
		<ts:submit value='delete' property="forward_delete" style="visibility:hidden"/> 
		<ts:submit value='exit' property="forward_exit" style="visibility:hidden"/> 
		<input type="hidden" id="errors.ERR1SC005" value='<bean:message key="errors.ERR1SC005" arg0="replace"/>'/>
		<input type="hidden" id="errors.ERR1SC006" value='<bean:message key="errors.ERR1SC006" arg0="replace"/>'/>
		<input type="hidden" id="errors.ERR1SC012" value='<bean:message key="errors.ERR1SC012" arg0="replace"/>'/>
		<div id="errMsg" style="font-style: italic;color:red;" ></div>
		<div id="role" class="hide"> 
			<html:select name="_m_curForm" property="cur_code" styleId="cboSymbol">
				<html:options collection="LIST_CURRENCY_SYMBOL" property="id" labelProperty="name"/>
			</html:select>
		</div> 
		<logic:empty name="_m_curForm" property="index">
	        <logic:notEmpty name="_m_curForm" property="cur_code">
		        <script type="text/javascript">
	             	disable_calendar_button("rate_dateCol",'<bean:write name="_m_curForm" property="cur_code"/>');
	             </script>
	        </logic:notEmpty>
        </logic:empty> 
        <logic:equal value="-1" name="_m_curForm" property="index">
        	<div id="ERR1SC006" class="show"><font color="red" style="font-style: italic"><bean:message key="screen.m_cur.ERR1SC006"/></font></div>
        </logic:equal>
        <logic:empty name="_m_curForm" property="cur_code">
	        <script type="text/javascript">
             	init_data();
             </script>
        </logic:empty>
        <c:if test="${ _m_curForm.duplicate }">
        	<script type="text/javascript">
             	duplicate_data();
             </script>
        </c:if>
        <%
        if(!fullAccess) {
        %>
        	<script type="text/javascript">
             	filter_access();
             </script>
        <%} %>
	</ts:form>	
</body>
</html>