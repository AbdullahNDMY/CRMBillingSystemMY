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
<link href="${pageContext.request.contextPath}/M_SVT/css/m_svts01.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheet/tabcontent.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/tabcontent.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/common.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/messageBox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/M_SVT/js/m_svts01.js"></script>
<title>Insert title here</title>
</head>
<body id="m">
	<table class="subHeader" cellpadding="0" cellspacing="0">
    		<tr>
    			<td><bean:message key="screen.m_svt.title"/></td>
    		</tr>
   	</table>
   	<ts:form styleId="frm_MSVTS01" action="/M_SVT01_DSP">
	   	<input type="hidden" name="pageEvent" value="postback"/>
	   	<input type="hidden" id="hiddenMsgConfirmDeletetion" value="<bean:message key="info.ERR4SC002"/>"/>
	   	<input type="hidden" id="hiddenMsgNoSelected" value='<bean:message key="errors.ERR1SC038" arg0="Category"/>'/>
	   	<input type="hidden" id="hiddenMsgMandatory" value='<bean:message key="errors.ERR1SC005" arg0="{0}"/>'/>
	   	<input type="hidden" id="hiddenMsgMinRecord" value='<bean:message key="errors.ERR1SC068"/>'/>
	   	<input type="hidden" id="ERR4SC107" value="<bean:message key="errors.ERR1SC107" arg0="{0}" arg1="{1}"/>"/>
	   	<input type="hidden" id="hiddenMsgNoCheck" value="<bean:message key="screen.m_svt.ERR1SC021" arg0="Service Item" arg1="delete"/>"/>
	   	<input type="hidden" id="hiddenFirstChoosed" value="${frm_MSVTS01.map.choosed}"/>
	   	<t:defineCodeList id="LIST_SERVICE_ROW"/>
	   	<bean:define id="serviceRow" value="10"/>
	   	<logic:iterate id="serviceRowRecord" name="LIST_SERVICE_ROW">
	   		<bean:define id="serviceRow" value="${serviceRowRecord.name}"/>
	   	</logic:iterate>
	   	<input type="hidden" id="serviceRow" value='<bean:write name="serviceRow"/>'/>
	   	<div class="searchCondition">
	   		<bean:message key="screen.m_svt.svGroup"/>
	   		<font color="red">*</font>
	   		<bean:message key="screen.m_svt.label_colon"/>
	   		<nested:select property="choosed" styleId="cmbSerivceGroup">
	   			<html:option value=""><bean:message key="screen.m_svt.listBox.default"/></html:option>
	   			<html:optionsCollection name="listServiceGroup" label="serviceGroupName" value="serviceGroup"/>
	   		</nested:select>
	   	</div>
	   	<div class="groupButton">   		
	   		<button id="btnSearch"><bean:message key="screen.m_svt.search"/></button>
	   		<button id="btnReset"><bean:message key="screen.m_svt.reset"/></button>
	   		<input type="hidden" value="${frm_MSVTS01.map.tabValue}" id="tabValue" name="tabValue"/>
	   	</div>
	   	<nested:notEmpty property="choosed">
	   		<div id="searchResult">
			   	<h4><bean:message key="screen.m_svt.generalInf"/></h4>
			   	<div id="serviceGroupName">
			   		<bean:message key="screen.m_svt.svGroup"/>
			   		<bean:message key="screen.m_svt.label_colon"/>
			   		<%-- Choosed service group name --%>
			   	</div>
			   	<ul id="serviceMaintainence" class="shadetabs">
					<li><a href="javascript:void(0);" rel="divServiceType" id="link1"><bean:message key="screen.m_svt.svType"/></a></li>
					<li><a href="javascript:void(0);" rel="divServiceItem" id="link2"><bean:message key="screen.m_svt.svItem"/></a></li>
					<li><a href="javascript:void(0);" rel="divPlanDetail" id="link3"><bean:message key="screen.m_svt.plDetail"/></a></li>
					<li>
						<span style="color:#FF0000;font:16px Calibri;margin-top:-20px;float:right;" >
							<bean:message key="screen.m_svt.descriptionAbbreviationRemark"/>
							<bean:message key="screen.m_svt.label_colon"/>
							7
						</span>
					</li>
				</ul>
				<div id="divServiceType" class="planService">
					<div class="wrapper">
				   	<table>
				   		<tr class="header">
				   			<td class="colEmpty">
				   				<a class="hlAdd" href="javascript:void(0);"><bean:message key="screen.m_svt.add"/></a>
				   				<input class="category" type="hidden" value="TYP"/>
								<input class="label" type="hidden" value='<bean:message key="screen.m_svt.svType"/>'/>
				   			</td>
				   			<td class="colServiceType">
				   				<bean:message key="screen.m_svt.svType"/>
				   				<font color="red">*</font>
				   			</td>
				   			<td class="colAccCode"><bean:message key="screen.m_svt.productCode"/></td>
				   			<td class="colUQTY" nowrap="nowrap"><bean:message key="screen.m_svt.descriptionAbbreviation"/></td>
				   			<%--
				   			<td class="colGST"><bean:message key="screen.m_svt.gst"/></td>
				   			--%>
				   		</tr>
				   		<nested:iterate property="listPlanService">
				   		<nested:equal property="serviceCategory" value="TYP">				   	
				   		<tr class="trPlanService">
				   			<td style="background-color: white;">
				   				<nested:hidden property="serviceCategory" styleClass="serviceCategory"/>
				   				<nested:hidden property="idService" styleClass="idService"/>
				   				<nested:hidden property="isUsed" styleClass="isUsed"/>
				   				<div class="hlDelete" style="width: 30px; cursor: hand;text-align: center;">
				   					<strong><bean:message key="screen.m_svt.delete"/></strong>
				   				</div>
				   			</td>
				   			<td style="background-color: white;"><nested:text property="serviceDescription" styleClass="serviceDescription" maxlength="300" style="width:365px;overflow:hidden;"/></td>
				   			<td style="background-color: white;"><nested:text property="accCode" styleClass="accCode" maxlength="30" style="width:220px;overflow:hidden;"/></td>
				   			<td style="background-color: white;"><nested:text property="descAbbr" styleClass="descAbbr" maxlength="10" style="overflow:hidden;"/></td>
				   			<%--
				   			<td style="background-color: white;"><nested:checkbox property="gst" value="1" styleClass="gst" style="width:35px;overflow:hidden;"/></td>
				   		    --%>
				   		</tr>
				   		</nested:equal>
				   		</nested:iterate>
				   		<tr class="trPlanService" title="sample">
				   			<td><input type="text"/></td>
				   		</tr>
				   	</table>
				   	</div>
			   	</div>
		
				<div id="divServiceItem" class="planService">
					<div class="wrapper">			
				   	<table>
				   		<tr class="header">
				   			<td class="colEmpty">
				   				<a class="hlAdd" href="javascript:void(0);"><bean:message key="screen.m_svt.add"/></a>
				   				<input class="category" type="hidden" value="ITM"/>
								<input class="label" type="hidden" value='<bean:message key="screen.m_svt.svItem"/>'/>
				   			</td>
				   			<td class="colServiceType">
				   				<bean:message key="screen.m_svt.svItem"/>
				   				<font color="red">*</font>
				   			</td>
				   			<td class="colAccCode"><bean:message key="screen.m_svt.productCode"/></td>
				   			<td class="colUQTY"><bean:message key="screen.m_svt.descriptionAbbreviation"/></td>
				   			<%--
				   			<td class="colGST"><bean:message key="screen.m_svt.gst"/></td>
				   			--%>
				   		</tr>
				   		<nested:iterate property="listPlanService">
				   		<nested:equal property="serviceCategory" value="ITM">
				   		<tr class="trPlanService">
				   			<td style="background-color: white;">
				   				<nested:hidden property="serviceCategory" styleClass="serviceCategory"/>
				   				<nested:hidden property="idService" styleClass="idService"/>
				   				<nested:hidden property="isUsed" styleClass="isUsed"/>
				   				<div class="hlDelete" style="width: 30px; cursor: hand;text-align: center;">
				   					<strong><bean:message key="screen.m_svt.delete"/></strong>
				   				</div>
				  			</td>
				  			<td style="background-color: white;"><nested:text property="serviceDescription" styleClass="serviceDescription" maxlength="300" style="width:365px;overflow:hidden;"/></td>
				   			<td style="background-color: white;"><nested:text property="accCode" styleClass="accCode" maxlength="30" style="width:220px;overflow:hidden;"/></td>
				   			<td style="background-color: white;"><nested:text property="descAbbr" styleClass="descAbbr" maxlength="10" style="overflow:hidden;"/></td>
				   			<%--
				   			<td style="background-color: white;"><nested:checkbox property="gst" value="1" styleClass="gst" style="width:35px;overflow:hidden;"/></td>
				   		    --%>
				   		</tr>
				   		</nested:equal>
				   		</nested:iterate>
				   		<tr class="trPlanService" title="sample">
				   			<td><input type="text"/></td>
				   		</tr>
				   	</table>
				   	</div>
			   	</div>
		   	
			   	<div id="divPlanDetail" class="planService">
				   	<div class="wrapper">			
				   	<table>
				   		<tr class="header">
				   			<td class="colEmpty">
				   				<a class="hlAdd" href="javascript:void(0);"><bean:message key="screen.m_svt.add"/></a>
				   				<input class="category" type="hidden" value="DTL"/>
								<input class="label" type="hidden" value='<bean:message key="screen.m_svt.plDetail"/>'/>
				   			</td>
				   			<td class="colServiceType">
				   				<bean:message key="screen.m_svt.plDetail"/>
				   				<font color="red">*</font>
				   			</td>
				   			<td class="colAccCode"><bean:message key="screen.m_svt.productCode"/></td>
				   			<td class="colUQTY"><bean:message key="screen.m_svt.descriptionAbbreviation"/></td>
				   			<%--
				   			<td class="colGST"><bean:message key="screen.m_svt.gst"/></td>
				   			--%>
				   		</tr>
				   		<nested:iterate property="listPlanService">
				   		<nested:equal property="serviceCategory" value="DTL">
				   		<tr class="trPlanService">
				   			<td style="background-color: white;">
				   				<nested:hidden property="serviceCategory" styleClass="serviceCategory"/>
				   				<nested:hidden property="idService" styleClass="idService"/>
				   				<nested:hidden property="isUsed" styleClass="isUsed"/>
				   				<div class="hlDelete" style="width: 30px; cursor: hand;text-align: center;">
				   					<strong><bean:message key="screen.m_svt.delete"/></strong>
				   				</div>
				  			</td>
				  			<td style="background-color: white;"><nested:text property="serviceDescription" styleClass="serviceDescription" maxlength="300" style="width:365px;overflow:hidden;"/></td>
				   			<td style="background-color: white;"><nested:text property="accCode" styleClass="accCode" maxlength="30" style="width:220px;overflow:hidden;"/></td>
				   			<td style="background-color: white;"><nested:text property="descAbbr" styleClass="descAbbr" maxlength="10" style="overflow:hidden;"/></td>
				   			<%--
				   			<td style="background-color: white;"><nested:checkbox property="gst" value="1" styleClass="gst" style="width:35px;overflow:hidden;"/></td>
				   		    --%>
				   		</tr>
				   		</nested:equal>
				   		</nested:iterate>
				   		<tr class="trPlanService"  title="sample">
				   			<td><input type="text"/></td>
				   		</tr>
				   	</table>
				   	</div>
			   	</div>
		
			   	<hr/>
			   	<div class="groupButton">
			   		<button id="btnSave"><bean:message key="screen.m_svt.save"/></button>
			   	</div>
			</div>
   		</nested:notEmpty>
   		<div class="error">
			<html:messages id="message">
				<bean:write name="message"/><br/>
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
