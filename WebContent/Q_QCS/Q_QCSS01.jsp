<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
   	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
	<link href="<%=request.getContextPath()%>/Q_QCS/css/q_qcss01.css" rel="stylesheet" type="text/css" /> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   	<script type="text/javascript" src="<%=request.getContextPath()%>/Q_QCS/js/q_qcss01.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
	<title>Insert title here</title>
</head>
<body id="q" onload="initMain();initPage()">
<ts:form action="/Q_QCSR01DSP" >
	<table class="subHeader" cellpadding="0" cellspacing="0">
	  <tr style="">
	    <td class="Title"><bean:message key="screen.q_qcs.title"/></td> 
	  </tr>
	</table> 
	<table class = "inputInfo" cellpadding="0" cellspacing="0">

		<tr>
			<td class="col1Top" width="15%"><bean:message key="screen.q_qcs.customerName"/><bean:message key="screen.q_qcs.colon"/> 
			</td>
			<td class="col2Top" width="30%"><html:text name="_q_qcsForm" property="cust_name" styleClass="QCSTextBox"></html:text>
			</td>
			<td class="col3Top" width="15%"><bean:message key="screen.q_qcs.consultantName"/><bean:message key="screen.q_qcs.colon"/>
			</td>
			<td class="col4Top" width="40%"><html:text name="_q_qcsForm" property="user_name" styleClass="QCSTextBox"></html:text>
			</td>
		</tr>
		<tr>
			<td class="colRight"><bean:message key="screen.q_qcs.qcsReference"/><bean:message key="screen.q_qcs.colon"/>
			</td>
			<td class="colLeft"><html:text name="_q_qcsForm" property="id_ref" styleClass="QCSTextBox"></html:text>
			</td>
			<td class="colRight"><bean:message key="screen.q_qcs.quoReference"/><bean:message key="screen.q_qcs.colon"/>
			</td>
			<td class="colLeft"><html:text name="_q_qcsForm" property="id_quo" styleClass="QCSTextBox"></html:text>
			</td>
		</tr>
		<tr>
			<td class="colRight"><bean:message key="screen.q_qcs.qcsDate"/><bean:message key="screen.q_qcs.colon"/>
			</td>
			<td class="colLeft" colspan="3">
				<html:text property="start_date" name="_q_qcsForm" readonly="true" styleClass="DateTextBox"/>
	                            <t:inputCalendar for="start_date" format="dd/MM/yyyy"/>&nbsp; <bean:message key="screen.q_qcs._"/>&nbsp;
	            <html:text property="end_date" name="_q_qcsForm" readonly="true" styleClass="DateTextBox"/>
	                            <t:inputCalendar for="end_date" format="dd/MM/yyyy"/> 
			</td>
		</tr>
		<tr>
		    <td class="colBottom" colspan="4">&nbsp;</td>
		</tr>
	</table>
	<table class="buttonGroup" cellpadding="0" cellspacing="0">
  	<tr>
		<td>
			<input type="submit" class="button" value="<bean:message key="screen.q_qcs.search"/>" name="forward_search"  onclick="clickSearch()" />
			<input type="button" class= "button"  value="<bean:message key="screen.q_qcs.reset"/>" onclick="clickReset()"/>
						
				<input type="submit" class="button" value="<bean:message key="screen.q_qcs.new"/>" name="forward_new" onclick="clickNew()"/>
					
			<ts:submit value="link" property="forward_link" style="visibility:hidden"/>
		</td>	
	</tr>
	</table>
	
	<html:hidden property="startIndex" name="_q_qcsForm" />
	
	<table class="searchResultNo" cellpadding="0" cellspacing="0">
	  	<tr>
			<td>
				<bean:message key="screen.q_qcs.searchFound"/> <bean:message key="screen.q_qcs.colon"/>
				<bean:write name="_q_qcsForm" property="totalCount"/>
			
			</td>	
		</tr>
	</table>  
	<table class="pageLink" cellpadding="0" cellspacing="0">
	  	<tr>
			<td>			
				<bean:message key="screen.q_qcs.gotoPage"/><bean:message key="screen.q_qcs.colon"/>
				<ts:pageLinks id="userPageLinks"
	              action="/Q_QCSR01BLogic" name="_q_qcsForm" rowProperty="row"
	              totalProperty="totalCount" indexProperty="startIndex"
	              currentPageIndex="now" totalPageCount="total"/>
	            <logic:present name="userPageLinks">  
					<bean:write name="userPageLinks" filter="false"/>
				</logic:present>	
			</td>	
		</tr>
	</table> 
	<table  class="resultInfo" cellpadding="0" cellspacing="0">
	  <tr>
	    <td class="header" width="7%"><bean:message key="screen.q_qcs.noCol"/></td>
	    <td class="header" width="10%"><bean:message key="screen.q_qcs.qcsDateCol"/></td>
	    <td class="header" width="18%"><bean:message key="screen.q_qcs.qcsReferenceCol"/></td>
	    <td class="header" width="20%"><bean:message key="screen.q_qcs.customerNameCol"/></td>
	    <td class="header" width="20%"><bean:message key="screen.q_qcs.consultantNameCol"/></td>
	    <td class="header" width="17%"><bean:message key="screen.q_qcs.quoReferenceCol"/></td>
	    <td class="header" width="8%"><bean:message key="screen.q_qcs.statusCol"/></td>
	  </tr>
	  <logic:iterate id="qcsInfo" name="_q_qcsForm" property="qcsInfos" >
		  <tr>
		    <td class="defaultNo"><bean:write name="qcsInfo" property="row_num"/></td>
		    <td class="defaultText">		    	
		    	<bean:write name="qcsInfo" property="date_req"/>
		    </td>
		    
		    <td class="defaultText">
		    	<bean:define id="def_id_ref" name="qcsInfo" property="id_ref"></bean:define>
		    	<a href="#" onclick="clickIdRefLink('<%=def_id_ref%>')" ><bean:write name="qcsInfo" property="id_ref"/></a>
		    </td>
		    <td class="defaultText">    
			    <bean:write name="qcsInfo" property="cust_name"/>
		    </td>
		    <td class="defaultText">
			    <bean:write name="qcsInfo" property="user_name"/>
		    </td>
		    <td class="defaultText">					    
			    <bean:write name="qcsInfo" property="id_quo"/>
		    </td>
		    <td class="defaultText">					    
			    <bean:write name="qcsInfo" property="id_status"/>
		    </td>
		  </tr>
	   </logic:iterate>
	</table>
	<input name="id_ref_s01" type="hidden"></input>
	<html:hidden name="_q_qcsForm" property="clickEvent"/>	 
</ts:form>
		<div class="error">
    		<ts:messages id="messages" message="true">
    			<bean:write name="messages"/>
    		</ts:messages>
    	
    	</div>  
</body>
</html>