<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>       
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
   		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
   		<link type="text/css" rel="stylesheet" href="css/m_curs01.css" />
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>  
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   		<script type="text/javascript" src="js/m_curs01.js"></script>
  		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
		
		<script type="text/javascript">
           $(function() {
                document.onkeydown = function(e){
                    var ev = document.all ? window.event : e;
                    if (ev.keyCode == 13) {
                        search_data();
                    }
                };
           });
        </script>
        
		<title></title>
	</head>
<body id="m" onload="initMain();">
<%
String accessType = ((nttdm.bsys.common.fw.BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT")).getUserAccessInfo("M", "M-CUR").getAccess_type();
boolean enableNewBt = accessType.equals("2");
%>
	<ts:form action="/M_CURS01Action">  
		<!-- Currency code list -->
		<t:defineCodeList id="LIST_CURRENCY"/>
		<table class="subHeader" cellpadding="0" cellspacing="0">
    		<tr>
    			<td><bean:message key="screen.m_cur.title"/></td>
    		</tr>
    	</table>
    	<table class="inputInfo" cellpadding="0" cellspacing="0">
			<tr>
    			<td class="col1Top" width="20%"><bean:message key="screen.m_cur.dateft"/>&nbsp;&nbsp;&nbsp;</td>
    			<td class="col2Top" width="30%">
					<!-- Insert date control here -->
					<html:text name="_m_curForm" property="datefrom"  styleId="datefrom" maxlength="10" readonly="true" styleClass="BlueStyle-textbox"/>
						<t:inputCalendar for="datefrom" format="dd/MM/yyyy"/> 
					<bean:message key="screen.m_cur._"/>
		            <html:text name="_m_curForm" property="dateto" styleId="dateto" maxlength="10" readonly="true" styleClass="BlueStyle-textbox"/>
		            	<t:inputCalendar for="dateto" format="dd/MM/yyyy"/> 
					<!-- Insert date control here -->
				</td>
    			<td class="col3Top" width="20%"></td>
    			<td class="col4Top" width="30%"></td>
    		</tr>
    		<tr>
    			<td class="colRight"><bean:message key="screen.m_cur.currencyCode"/>&nbsp;&nbsp;&nbsp;</td>
    			<td class="colLeft">
    				<html:select name="_m_curForm" property="cur_code_search" styleId="cboCur_code">
			          <html:option value="" ><bean:message key="screen.m_cur.label_blank"/></html:option>
			          <html:options collection="LIST_CURRENCY" property="id" labelProperty="name"/>
			        </html:select>	
    			</td>
    			<td class="colRight"></td>
    			<td class="colLeft"></td>
    		</tr>
	        <tr>
	        	<td class="colBottom"><br></td>
	            <td class="colBottom"><br></td>
	            <td class="colBottom"><br></td>
	            <td class="colBottom"><br></td>
	        </tr>  
    	</table>
    	<table class="buttonGroup" cellpadding="0" cellspacing="0">
    		<tr>
    			<td>
    				<button onclick="search_data();"><bean:message key="screen.m_cur.search"/></button>&nbsp;
    				<button onclick="resetClick();"><bean:message key="screen.m_cur.reset"/></button>&nbsp;
    				<%if(enableNewBt) {%>
    					<button onclick="new_data();"><bean:message key="screen.m_cur.new"/></button>
    				<%} else {%>
    				<%} %>
					<ts:submit value='new' property="forward_new" style="visibility:hidden"/> 
					<ts:submit value='search' property="forward_search" style="visibility:hidden"/> 
					<ts:submit value='edit' property="forward_edit" style="visibility:hidden"/> 
					<ts:submit value='reset' property="forward_reset" style="visibility:hidden"/> 
    			</td>
    		</tr>
    	</table>	
    	<table class="searchResultNo" cellpadding="0" cellspacing="0">
    		<tr>
    			<td>
					<bean:message key="screen.m_cur.searchFound"/>
					<c:if test="${_m_curForm.totalCount != -1}">
						<bean:write name="_m_curForm" property="totalCount"/>
					</c:if>    				
    			</td>
    		</tr>
    	</table>
		<table class="pageLink" cellpadding="0" cellspacing="0">
			<tr>
				<td><bean:message key="screen.m_cur.gotoPage"/> 
					<ts:pageLinks 
			    		id="curPageLinks"
						action="/M_CURS01BL.do?clickFlg=search" 
						name="_m_curForm" 
						rowProperty="row"
						totalProperty="totalCount" 
						indexProperty="startIndex"
						currentPageIndex="now" 
						totalPageCount="total"/>
					<logic:present name="curPageLinks">
						<bean:write name="curPageLinks" filter="false"/>
					</logic:present>
				</td>
			</tr>
		</table>
		<table class="resultInfo" cellpadding="0" cellspacing="0">
			<tr>
				<td class="header2"><bean:message key="screen.m_cur.noCol"/></td>
				<td class="header2"><bean:message key="screen.m_cur.dateCol"/></td>
				<td class="header2"><bean:message key="screen.m_cur.currencyCodeCol"/></td>
				<td class="header2"><bean:message key="screen.m_cur.currencyNameCol"/></td>
				<td class="header2"><bean:message key="screen.m_cur.forexToCol"/></td> 
				<td class="header2"><bean:message key="screen.m_cur.forexFromCol"/></td> 
			</tr>
			<logic:present property="arr_m_cur" name="_m_curForm">
				<logic:iterate id="resultBean" name="_m_curForm" property="arr_m_cur" indexId="i">
					<tr>
						<td class="defaultNo"><bean:write name="resultBean" property="index"/></td>
						<td class="defaultText">							
							<a href="#" onclick="edit_date('<bean:write name="resultBean" property="rate_date"/>','<bean:write name="resultBean" property="cur_code"/>');"><bean:write name="resultBean" property="rate_date"/></a>							
						</td>
						<td class="defaultText"><bean:write name="resultBean" property="cur_code"/></td>
						<td class="defaultText" id='cur_name_<%=i%>'>
							<script type="text/javascript">
								get_currency_name('<bean:write name="resultBean" property="cur_code"/>','cur_name_<%=i%>');
							</script>
						</td> 
						<td class="defaultText"><bean:write name="resultBean" property="rate_to"/></td> 
						<td class="defaultText"><bean:write name="resultBean" property="rate_from"/></td>
					</tr>		
				</logic:iterate>
			</logic:present>
		</table>
		<html:hidden name="_m_curForm" property="unsearch" styleId="unsearch"/>
		<html:hidden name="_m_curForm" property="clickFlg" styleId="clickFlg"/>
		<logic:notEqual value="1" name="_m_curForm" property="unsearch">
			<script type="text/javascript">
				reset_data();
			</script>
		</logic:notEqual>
		<html:hidden name="_m_curForm" property="rate_date" styleId="rate_date"/>
		<html:hidden name="_m_curForm" property="cur_code" styleId="cur_code"/>
		<div id="ERR1SC013" class="hide"><font color="red" style="font-style: italic"><bean:message key="screen.m_cur.ERR1SC013"/></font></div>
    </ts:form>
    	<!--  -->
    	<div class="message">
			<html:messages id="message" message="true">
				<bean:write name="message"/>
			</html:messages>
		</div>
		<div class="error">
			<html:errors/>
		</div>
		<!--  -->
</body>
</html:html>

