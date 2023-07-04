<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   		<link type="text/css" rel="stylesheet" href="css/e_dim_gr1.css" />
   		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>	
		<title><bean:message key="screen.e_dim_gr1.title"/>EOD-Data Import(SMBC Giro Collection Data)</title>
		<script>
		function popup(url) {
			newwindow=window.open(url,'name','height=400,width=900, scrollbars=1, resizable=1');
			if (window.focus) {newwindow.focus()}
		}
		</script>
	</head>
	<body id="e" onload="initMain();">
		<ts:form action="/E_DIM_GR102BL" enctype="multipart/form-data" method="post">
			<h1 class="title"><bean:message key="screen.e_dim_gr1.screen_name"/></h1>
	    	<html:hidden name="_e_dim_gr1_Form" property="event" value="forward_upload"/>
	    	<html:hidden name="_e_dim_gr1_Form" property="paging" />
	    	<html:hidden name="_e_dim_gr1_Form" property="alertMsg"/>
	    	<!-- General Information table -->
	    	<div class="section" style="margin-top: 3px;">
	    		<h2><bean:message key="screen.e_dim_gr1.label.general_information"/></h2>
	    		<div class="group-content">
	    			<table cellspacing="3" cellpadding="0" width="100%">
						<tr>
							<td>
								<bean:message key="screen.e_dim_gr1.label.import_file"/>
								<bean:message key="screen.e_dim_gr1.label_colon"/>
							</td>
							<td>
								<html:file property="fileName" size="60"></html:file>
							</td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td>
								<input type="submit" value='<bean:message key="screen.e_dim_gr1.button.upload"/>'/> 
							</td>
							<td></td>
						</tr>
					</table>
	    		</div>
	    	</div>
	    	<div class="section">
	    		<h2><bean:message key="screen.e_dim_gr1.label.history"/></h2>
	    		<div class="group-content">
	    			<div class="group result">
						<h2>
						<bean:message key="screen.e_dim_gr1.label.search_found"/>
						<bean:message key="screen.e_dim_gr1.label_colon"/>
						<bean:write name="_e_dim_gr1_Form" property="totalCount"/>
						</h2>
					</div>
					<div class="pageLink"><bean:message key="screen.e_dim_gr1.label_page_link"/>:
						<ts:pageLinks 
			    			id="curPageLinks"
							action="/E_DIM_GR101BL" 
							name="_e_dim_gr1_Form" 
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
					<table class="datatable collapse" cellpadding="0" cellspacing="0">
						<tr>
							<th><bean:message  key="screen.e_dim_gr1.label.no"/></th>
							<th><bean:message key="screen.e_dim_gr1.label.file_name"/></th>
							<th><bean:message key="screen.e_dim_gr1.label.uploaded_date"/></th>
							<th><bean:message key="screen.e_dim_gr1.label.status"/></th>
							<th><bean:message key="screen.e_dim_gr1.label.download"/></th>
						</tr>
						<logic:notEmpty name="_e_dim_gr1_Form" property="girImpHdDtoList">
						<logic:iterate id="resultBean" name="_e_dim_gr1_Form" property="girImpHdDtoList" indexId="indexNum">
						<tr>
							<td align="center">
								<bean:write  name="resultBean" property="idGirImpBatch"/> 
							</td>
							<td>
								<bean:write  name="resultBean" property="fileName"/>
							</td>
							<td align="center">
								<bean:write  name="resultBean" property="dateUploaded"/>
							</td>
							<td align="center">
								<logic:equal value="0" name="resultBean" property="status">
									<bean:message key="screen.e_dim_gr1.label.success"/>
								</logic:equal>
								<logic:equal value="1"  name="resultBean" property="status">
									<bean:message key="screen.e_dim_gr1.label.fail"/>
								</logic:equal>
								<logic:equal value="2"  name="resultBean" property="status">
									<bean:message key="screen.e_dim_gr1.label.in_process"/>
								</logic:equal>
							</td>
							<td align="center">
								<!-- Import File -->
								<logic:equal value="0" name="resultBean" property="status">
									<a href="<%=request.getContextPath()%>/E_DIM_GR1/E_DIM_GR103BL.do?filename=${resultBean.fileName}"><bean:message key="screen.e_dim_gr1.label.import_file" /></a>
									<bean:message key="screen.e_dim_gr1.label.log"/>
								</logic:equal>
								<!-- Log -->
								<logic:equal value="1" name="resultBean" property="status">
									<bean:message key="screen.e_dim_gr1.label.import_file" />
									<a href="javascript:popup('${pageContext.request.contextPath}/E_DIM_GR1/E_DIM_GR1_201BL.do?idGirImpBatch=${resultBean.idGirImpBatch}');"><bean:message key="screen.e_dim_gr1.label.log"/></a>
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

