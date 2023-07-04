<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ page import="nttdm.bsys.common.fw.BillingSystemUserValueObject" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">

<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title><bean:message key="screen.e_dim_sp1.title"/></title>
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
		<link href="${pageContext.request.contextPath}/E_DIM/css/e_dim.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/common.js"></script>
	</head>
	<body id="e" onload="initMain();">
		<%
			String accessRight = ((nttdm.bsys.common.fw.BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT")).getUserAccessInfo("E", "E-DIM-SP1").getAccess_type();
		 %>
		<input type="hidden" styleId="userAccess" name="userAccess" value="<%=accessRight%>" />
		<input type="hidden" styleId="subModule" name="subModule" value="E-DIM-SP1" />
		<ts:form action="/RP_E_DIM_SP1_02BL" enctype="multipart/form-data">
			<h1 class="title"><bean:message key="screen.e_dim_sp1.title"/></h1>
			<div class="section">
				<h2><bean:message key="screen.e_dim_sp1.0001"/></h2>
				<div class="group-content" >
				<div class="group">
				    <fieldset style="padding:0px 15px;">
						<legend><bean:message key="screen.e_dim_sp1.0024" /></legend>
						<table cellspacing="5" cellpadding="0" width="100%">
							<tr>
								<td nowrap="nowrap" align="right" width="28%">
									<bean:message key="screen.e_dim_sp1.0025" /><span style="color: red;"><bean:message key="screen.e_dim_sp1.0026" /></span><bean:message key="screen.e_dim_sp1.0027" />
								</td>
								<td width="72%">
								<%if(accessRight.equals("2")) {%>
									<html:select name="_e_dimForm" property="bankAcc">
										<option value=""> <bean:message key="screen.e_dim_sp1.blankitem"/> </option>
										<logic:present property="cbBankAcc" name="_e_dimForm">
											<html:optionsCollection name="_e_dimForm" property="cbBankAcc"/>
										</logic:present>
									</html:select>
								<%} else { %>
									<html:select name="_e_dimForm" property="bankAcc" disabled="true">
										<option value=""> <bean:message key="screen.e_dim_sp1.blankitem"/> </option>
										<logic:present property="cbBankAcc" name="_e_dimForm">
											<html:optionsCollection name="_e_dimForm" property="cbBankAcc"/>
										</logic:present>
									</html:select>
								<%} %>
								</td>
							</tr>
						</table>
					</fieldset>
				</div>
				</div>
				<div class="group-content">
					<table cellspacing="5" cellpadding="0" width="100%">
						<tr>
							<td nowrap="nowrap" align="right">
								<bean:message key="screen.e_dim_sp1.0005"/><span style="color: red;"><bean:message key="screen.e_dim_sp1.0026" /></span><bean:message key="screen.e_dim_sp1.0027" />
							</td>
							<td>
							<%if(accessRight.equals("2")) {%>
								<html:file property="formFile" size="60" style="height:23px;"></html:file>
							<%} else { %>
								<html:file property="formFile" size="60" style="height:23px;" disabled="true"></html:file>
							<%} %>
							</td>
						</tr>
						<tr>
							<td></td>
							<td>
							<%if(accessRight.equals("2")) {%>
							     <logic:equal value="Y" name="_e_dimForm" property="retStatusStr">
							         <input type="submit" style="height:23px;" value='<bean:message key="screen.e_dim_sp1.0007"/>'/> 
							     </logic:equal>
								 <logic:equal value="N" name="_e_dimForm" property="retStatusStr">
								     <input type="button" style="height:23px;" value='<bean:message key="screen.e_dim_sp1.0007"/>' disabled="disabled"/> 
								 </logic:equal>
							<%} else { %>
								 <input type="button" style="height:23px;" value='<bean:message key="screen.e_dim_sp1.0007"/>' disabled="disabled"/> 
							<%} %>
							</td>
						</tr>
					</table>
				</div>
			</div>
			
			<div class="section">
				<h2><bean:message key="screen.e_dim_sp1.0008"/></h2>
				<div class="group-content">
					<div class="group result">
						<h2><bean:message key="screen.e_dim_sp1.0009" /> : ${_e_dimForm.map.totalRow}</h2>
					</div>
					<div class="pageLink" style="width: 99%;"><bean:message key="screen.e_dim_sp1.0010"/>:
						<ts:pageLinks 
			    			id="curPageLinks"
							action="/RP_E_DIM_SP1_01BL" 
							name="_e_dimForm" 
							rowProperty="row"
							totalProperty="totalRow" 
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
					    <th align="center"><bean:message key="screen.e_dim_sp1.0013"/></th>
					    <th align="center"><bean:message key="screen.e_dim_sp1.0014"/></th>
					    <th align="center"><bean:message key="screen.e_dim_sp1.0016"/></th>
					    <th align="center"><bean:message key="screen.e_dim_sp1.0017"/></th>
					    <th align="center" class="last"><bean:message key="screen.e_dim_sp1.0018"/></th>
					  </tr>
					  <c:forEach items="${_e_dimForm.map.listHistories}" var="item" varStatus="status">
					  	<tr>
						  	<td align="center">${item.ID_SGP_IMP_BATCH}</td>
						  	<td>${item.FILENAME}</td>
						  	<td align="center">${item.DATE_UPDATED_FMT}</td>
						  	<td align="center">
						  		<c:if test="${item.STATUS == 0}">
						  			<bean:message key="screen.e_dim_sp1.0021"/>
						  		</c:if>
						  		<c:if test="${item.STATUS == 1}">
						  			<bean:message key="screen.e_dim_sp1.0022"/>
						  		</c:if>
						  		<c:if test="${item.STATUS == 2}">
						  			<bean:message key="screen.e_dim_sp1.0023"/>
						  		</c:if>
						  	</td>
						  	<td align="center">
						  		<!-- Import File -->
						  		<c:if test="${item.STATUS == 0}">
						  			<a href="<%=request.getContextPath()%>/E_DIM/RP_E_DIM_SP1DownloadBL.do?filename=${item.FILENAME}"><bean:message key="screen.e_dim_sp1.0019" /></a>
						  			&nbsp;&nbsp;&nbsp;
						  			<c:if test="${item.ERROR_COUNT == 0}">
						  				<bean:message key="screen.e_dim_sp1.0020"/>
						  			</c:if>
						  			<c:if test="${item.ERROR_COUNT != 0}">
						  				<a href="javascript:popup('${pageContext.request.contextPath}/E_DIM/RP_E_DIM_SP1_03BL.do?idSgpImpBatch=${item.ID_SGP_IMP_BATCH}', 'SC_E_DIM_SP1');"><bean:message key="screen.e_dim_sp1.0020"/></a>
						  			</c:if>
						  		</c:if>
						  		<!-- Log -->
						  		<c:if test="${item.STATUS == 1}">
						  			<bean:message key="screen.e_dim_sp1.0019" />
						  			&nbsp;&nbsp;&nbsp;
						  			<a href="javascript:popup('${pageContext.request.contextPath}/E_DIM/RP_E_DIM_SP1_03BL.do?idSgpImpBatch=${item.ID_SGP_IMP_BATCH}', 'SC_E_DIM_SP1');"><bean:message key="screen.e_dim_sp1.0020"/></a>
						  		</c:if>
						  		<c:if test="${item.STATUS == 2}">
						  		 &nbsp;
						  		</c:if>
						  	</td>
					  	</tr>
					  </c:forEach>
					</table>
				</div>
			</div>
		</ts:form>
			<div class="error">
				<html:messages id="message">
					<bean:write name="message"/><br/>
				</html:messages>
			</div>
			<div class="message">
				<ts:messages id="message" message="true"><bean:write name="message"/></ts:messages>
			</div>
	</body>
</html:html>

