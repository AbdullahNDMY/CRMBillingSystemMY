<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>  
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheet/common.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheet/tabcontent.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/A_USR/css/a_usrs03.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/tabcontent.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/common.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/messageBox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/A_USR/js/a_usrs03.js"></script>
<title>Insert title here</title>
</head>
<body id="a">
	<table class="subHeader">
	  <tr>
	    <td><bean:message key="screen.a_usrs03.organizationmanager"/></td>
	  </tr>
	</table>	
   	<ts:form styleId="frm_AUSRS03" action="/A_USR03_DSP.do">
	   	<input type="hidden" name="pageEvent" value="postback"/>
	   	<input type="hidden" id="hiddenMsgConfirmDeletetion" value="<bean:message key="info.ERR4SC002"/>"/>	   	
	   	<input type="hidden" id="hiddenMsgNoSelected" value='<bean:message key="errors.ERR1SC005" arg0="Type"/>'/>
	   	<input type="hidden" id="hiddenMsgMinRecord" value='<bean:message key="errors.ERR1SC068"/>'/>	   	
	   	<input type="hidden" id="hiddenFirstChoosed" value="${frm_AUSRS03.map.choosed}"/>
	   	  
	   	<div class="searchCondition">
	   		<bean:message key="screen.a_user03.type"/>
	   		<font color="red">*</font>
	   		<bean:message key="screen.a_user03.label_colon"/>	   		
	   		<html:select name="frm_AUSRS03" styleId="cmbSerivceGroup" property="choosed" styleClass="USRTextBox">
				<option value="">
					<bean:message key="screen.a_usrs03.blankitem" />
				</option>
				<%-- loading LIST_ORGANIZATION_TYPE --%>
				<t:defineCodeList id="LIST_ORGANIZATION_TYPE" />
				<html:options collection="LIST_ORGANIZATION_TYPE" property="id" labelProperty="name" />
			</html:select>
	   	</div> 	 
	   	<div class="groupButton">   		
	   		<button id="btnSearch"><bean:message key="screen.a_usrs03.search"/></button>
	   		<button id="btnReset"><bean:message key="screen.a_usrs03.reset"/></button>	   		
	   	</div>
	   	<nested:notEmpty property="choosed">
	   		<div id="searchResult">
			   	<h4><bean:write property="choosed" name="frm_AUSRS03"/><br/></h4>			   
				<div id="divServiceType" class="planService">
					<div class="wrapper" style="max-height:500px;overflow-y:auto;">
				   	<table>
				   		<tr class="header">
				   			<td class="colEmpty">
				   				<a class="hlAdd" href="javascript:void(0);"><bean:message key="screen.a_usrs03.add"/></a>
								<input class="label" type="hidden" value='<bean:message key="screen.a_usrs03.id"/>'/>
				   			</td>
				   			<td class="colServiceType">
				   				<bean:message key="screen.a_usrs03.id"/>
				   			</td>
				   			<td class="colAccCode"><bean:message key="screen.a_usrs03.name"/></td>
				   		</tr>				   		
				   		<nested:iterate property="listPlanService">
					   		<tr class="trPlanService">
					   			<td style="background-color: white;">
					   				<nested:hidden property="isUsed" styleClass="isUsed"/>
					   				<div class="hlDelete" style="width: 30px; cursor: hand;text-align: center;">
					   					<strong><bean:message key="screen.a_usrs03.delete"/></strong>
					   				</div>
					   			</td>
				   				<td style="background-color: white;">
				   				    <logic:notEqual value="Role" name="frm_AUSRS03" property="choosed">
				   				         <nested:text property="id" styleClass="id" maxlength="4" style="width:95%;overflow:hidden;"/>
				   				    </logic:notEqual>
				   				    <logic:equal value="Role" name="frm_AUSRS03" property="choosed">
				   				         <nested:text property="id" styleClass="id" maxlength="10" style="width:95%;overflow:hidden;"/>
				   				    </logic:equal>
				   				         <input type="hidden" value='<nested:write property="id"/>' />
				   				</td>
					   			<td style="background-color: white;">
					   			     <nested:text property="name" styleClass="name" maxlength="14" style="width:98%;overflow:hidden;"/>
					   			     <input type="hidden" value='<nested:write property="name"/>' />
					   			</td>
					   		</tr>
				   		</nested:iterate>
				   	</table>
				   	</div>
			   	</div>		
			   	<hr/>
			   	<div class="groupButton">
			   		<button id="btnSave"><bean:message key="screen.a_usrs03.save"/></button>
			   	</div>
			</div>
   		</nested:notEmpty>

		<div id="message_group" style="display: none;">
			<div class="messageNameRevise"><bean:message key="info.ERR4SC019" arg0="Modified {0} value will immediate reflect to user maintenance {0} selected value.&lt;br&gt;Do you want to proceed save?&lt;br&gt;Click Yes to proceed save, No to abort save"/></div>
		</div>
   		<div class="error">
			<html:messages id="error">
				<bean:write name="error"/><br/>
			</html:messages>
		</div>
	   	<div class="message">
			<html:messages id="message" message="true">
				<bean:write name="message"/><br/>
			</html:messages>
		</div>
   	</ts:form>
</body>
</html>
