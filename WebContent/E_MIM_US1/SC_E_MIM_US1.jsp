<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
   		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
   		<script type="text/javascript" src="js/e_mim_us1.js"></script>
	 	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery.spinbox.js"></script>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>	
		<title><bean:message key="screen.e_mim_us1.title"/></title>
	</head>
	<body id="e" onload="onload();initMain();">
		<ts:form action="/E_MIM_US1_2Blogic" enctype="multipart/form-data" method="post">
			<h1 class="title"><bean:message key="screen.e_mim_us1.screen_name"/></h1>
	    	<html:hidden name="_e_mim_us1_Form" property="alertMsg"/>
			<div class="section">
				<h2><bean:message key="screen.e_mim_us1.label.general_information"/></h2>
				<div class="group-content">
					<table cellspacing="3" cellpadding="0" style="width:600px;">
						<tr>
							<td colspan="2">
								<fieldset>
									<legend class="legend-small"><bean:message key="screen.e_mim_us1.label.closing_month"/></legend>
									<div class="space">
										<bean:message key="screen.e_mim_us1.label.month"/>&nbsp;:&nbsp;
										<html:text name="_e_mim_us1_Form" property="closingMonth" size="5" maxlength="2"></html:text>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<bean:message key="screen.e_mim_us1.label.year"/>&nbsp;:&nbsp;
										<html:text name="_e_mim_us1_Form" property="closingYear" size="5" maxlength="4"></html:text>
									</div>
								</fieldset>
							</td>
						</tr>
						<tr>
							<td>
								<bean:message key="screen.e_mim_us1.label.import_file"/>&nbsp;:&nbsp;
							</td>
							<td>
								<html:file property="fileName" name="_e_mim_us1_Form" size="60"></html:file>
							</td>
						</tr>
						<tr>
							<td></td>
							<td>
								<input type="submit" value='<bean:message key="screen.e_mim_us1.button.upload"/>'/> 
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="section">
				<h2><bean:message key="screen.e_mim_us1.label.history"/></h2>
				<div class="group-content" style="width: 100%;">
					<div class="group result">
						<h2>
						<bean:message key="screen.e_mim_us1.label.search_found" /> 
						<bean:message key="screen.e_mim_us1.label_colon"/>
						<bean:write name="_e_mim_us1_Form" property="totalCount"/>
						</h2>
					</div>
					<div class="pageLink"><bean:message key="screen.e_mim_us1.label_page_link"/>:
						<ts:pageLinks 
			    			id="curPageLinks"
							action="/E_MIM_US1Blogic" 
							name="_e_mim_us1_Form" 
							rowProperty="row"
							totalProperty="totalCount" 
							indexProperty="startIndex"
							currentPageIndex="now" 
							totalPageCount="total"
						/>
					  <logic:present name="curPageLinks">  
							<bean:write name="curPageLinks" filter="false"/>
						</logic:present>
					</div>
					<table class="datatable collapse" cellpadding="0" cellspacing="0" border="0">
					  <tr>
					    <th align="center"><bean:message key="screen.e_mim_us1.label.no"/></th>
					    <th align="center"><bean:message key="screen.e_mim_us1.label.file_name"/></th>
					    <th align="center"><bean:message key="screen.e_mim_us1.label.closing_month"/></th>
					    <th align="center"><bean:message key="screen.e_mim_us1.label.updated_date"/></th>
					    <th align="center"><bean:message key="screen.e_mim_us1.label.status"/></th>
					    <th align="center" class="last"><bean:message key="screen.e_mim_us1.label.download"/></th>
					  </tr>
					  <logic:notEmpty name="_e_mim_us1_Form" property="ipassList">
				<logic:iterate id="resultBean" name="_e_mim_us1_Form" property="ipassList" indexId="indexNum">
					<tr>
						<td>
							<bean:write  name="resultBean" property="idIpassImpBatch"/> 
						</td>
						<td>
							<bean:write  name="resultBean" property="fileName"/>
						</td>
						<td>
							<bean:write  name="resultBean" property="closeMonthYear"/>
						</td>
						
						<td>
							<bean:write  name="resultBean" property="dateUpdated"/>
						</td>
						<td>
							<logic:equal value="0" name="resultBean" property="status"><bean:message key="screen.e_mim_us1.label.success"/></logic:equal>
							<logic:equal value="1"  name="resultBean" property="status"> <bean:message key="screen.e_mim_us1.label.fail"/></logic:equal>
							<logic:equal value="2"  name="resultBean" property="status"> <bean:message key="screen.e_mim_us1.label.inprocess"/></logic:equal>
						</td>
						<td>
						<logic:equal value="0" name="resultBean" property="status">
							<a href="<%=request.getContextPath()%>/E_MIM_US1/E_MIM_US1_3Blogic.do?csvFileName=${resultBean.fileName}"><bean:message key="screen.e_mim_us1.label.import_file"/></a>
							<bean:message key="screen.e_mim_us1.label.log"/>
						</logic:equal>
						<logic:equal value="1" name="resultBean" property="status">
							<bean:message key="screen.e_mim_us1.label.import_file"/>
							<a href="javascript:void(0);" onclick="e_mim_us1_popup('<%=request.getContextPath()%>', '<bean:write  name="resultBean" property="idIpassImpBatch"/>');"><bean:message key="screen.e_mim_us1.label.log"/></a>
						</logic:equal>
						<logic:equal value="2" name="resultBean" property="status">
							&nbsp;
						</logic:equal>
						</td>
					</tr>
				</logic:iterate>
			
			</logic:notEmpty>
					</table>
				</div>
			</div>
			
		</ts:form>
			<div class="message">
				<ts:messages id="message" message="true"><bean:write name="message"/></ts:messages>
			</div>
			<div class="error">
				<html:messages id="message">
					<bean:write name="message"/><br/>
				</html:messages>
			</div>
	</body>
</html:html>

