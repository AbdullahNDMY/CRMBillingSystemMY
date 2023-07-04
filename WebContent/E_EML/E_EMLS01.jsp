<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/bs" prefix="bs" %>
<%@page import="nttdm.bsys.common.fw.BillingSystemUserValueObject"%>
<%@page import="nttdm.bsys.common.util.CommonUtils"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
   	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/common.css"/>
   	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common_01.css"/>
   	<link href="${pageContext.request.contextPath}/E_EML/css/e_eml.css" rel="stylesheet" type="text/css" />
   	<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/E_EML/js/e_emls01.js"></script>
	<style type="text/css">
		    .resultInfo tr td{
		        padding: 7px 5px;
		    }
		    .pdfmessage {
				color : BLUE;
			}
			.border{
				border-top: solid 1px #CFCFCF;
				border-bottom: solid 1px #CFCFCF;
				border-left: solid 1px #CFCFCF;
				border-right: solid 1px #CFCFCF;
			}
			.pdfZipFileTable{
		        padding-left: 15px;
		    }
		</style>
	<script type="text/javascript">
	function clickDownload(id_doc){			
		document.getElementById("id_doc").value = id_doc;
		document.forms[0].action = '<%=request.getContextPath()%>/E_EML/E_EMLDownloadBL.do';
		document.forms[0].submit();
		//reset
		document.forms[0].action = '<%=request.getContextPath()%>/E_EML/E_EMLS02BL.do';
	}
	</script>
<title><bean:message key="screen.e_eml_s01.title"/></title>
</head>
<body id="b" onload="initMain()">
		<%
            String accessRight = ((nttdm.bsys.common.fw.BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT")).getUserAccessInfo("E", "E-EML-S01").getAccess_type();
            pageContext.setAttribute("accessRightBean", accessRight);
        %>
<ts:form action="/E_EMLS01Action">
	<input type="hidden" name="id_doc" id="id_doc"/>
	<html:hidden property="nonTaxInvoiceShowFlg" value="${_e_eml_Form.nonTaxInvoiceShowFlg}"/>
	<div id="contentDiv" style="width:1500px;">
	<h1 class="title"><bean:message key="screen.e_eml_s01.title"/></h1>
	<div class="section" style="border-top:2px solid #cecece;border-bottom:2px solid #cecece;margin-top:-5px;">
	<div style="margin-top:5px"></div>
		<table cellpadding="0" cellspacing="0" width="70%">
			<tr>
				<td align="right"><bean:message key="screen.e_eml_s01.billDocuNo"/><bean:message key="screen.e_eml_s01.colon"/> 
				</td>
				<td><html:text name="_e_eml_Form" property="billDocuNo" styleClass="QCSTextBox" style="width:210px;"></html:text>
				</td>
				<td  align="right"><bean:message key="screen.e_eml_s01.documentDate"/><bean:message key="screen.e_eml_s01.colon"/>
				</td>
				<td>
					<html:text property="start_docuDate" readonly="true" style="width:70px;"></html:text>
						<t:inputCalendar for="start_docuDate" format="dd/MM/yyyy"/>
							-
					<html:text property="end_docuDate" readonly="true" style="width:70px;"></html:text>
						<t:inputCalendar for="end_docuDate" format="dd/MM/yyyy"/>
				
				</td>
				<td></td>
			</tr>
			<tr>
				<td align="right"><bean:message key="screen.e_eml_s01.idcust"/><bean:message key="screen.e_eml_s01.colon"/> 
				</td>
				<td width="30%"><html:text name="_e_eml_Form" property="idCust" styleClass="QCSTextBox" style="width:210px;"></html:text>
				</td>
				<td align="right"><bean:message key="screen.e_eml_s01.emailSentDate"/><bean:message key="screen.e_eml_s01.colon"/>
				</td>
				<td>
					<html:text name="_e_eml_Form" property="start_emailDate" readonly="true" style="width:70px;"></html:text>
						<t:inputCalendar for="start_emailDate" format="dd/MM/yyyy"/>
							-
					<html:text name="_e_eml_Form" property="end_emailDate" readonly="true" style="width:70px;"></html:text>
						<t:inputCalendar for="end_emailDate" format="dd/MM/yyyy"/>
				</td>
				<td></td>
			</tr>
			<tr>
				<td align="right"><bean:message key="screen.e_eml_s01.customerName"/><bean:message key="screen.e_eml_s01.colon"/> 
				</td>
				<td width="30%"><html:text name="_e_eml_Form" property="customerName" styleClass="QCSTextBox" style="width:210px;"></html:text>
				</td>
				<td align="right"><bean:message key="screen.e_eml_s01.emal"/><bean:message key="screen.e_eml_s01.colon"/> 
				</td>
				<td>
					<html:multibox name="_e_eml_Form" property="deliveryEmail" value="1">
			        </html:multibox>
			        <bean:message key="screen.e_eml_s01.yes"/>
			        <html:multibox name="_e_eml_Form" property="deliveryEmail" value="0">
			        </html:multibox>
			        <bean:message key="screen.e_eml_s01.no"/>
				</td>
				<td></td>
			</tr>
			<tr>
				<td align="right"><bean:message key="screen.e_eml_s01.billAcountNo"/><bean:message key="screen.e_eml_s01.colon"/> 
				</td>
				<td width="30%"><html:text name="_e_eml_Form" property="billAcountNo" styleClass="QCSTextBox" style="width:210px;"></html:text>
				</td>
				
				<td align="right"><bean:message key="screen.e_eml_s01.peopleAccNo"/><bean:message key="screen.e_eml_s01.colon"/> 
				</td>
				<td>
					<html:multibox name="_e_eml_Form" property="peopleAccNo" value="1">
			        </html:multibox>
			        <bean:message key="screen.e_eml_s01.yes"/>
			        <html:multibox name="_e_eml_Form" property="peopleAccNo" value="0">
			        </html:multibox>
			        <bean:message key="screen.e_eml_s01.no"/>
				</td>
				<td></td>
			</tr>
			<tr>
				<td align="right"><bean:message key="screen.e_eml_s01.transaction"/><bean:message key="screen.e_eml_s01.colon"/> 
				</td>
				<td>
					<t:defineCodeList id="TRANSACTION_TYPE" />
						<html:select property="transaction" style="width:191px;" name="_e_eml_Form">
							<html:option value=""><bean:message key="screen.e_eml_s01.blankItem"/></html:option>
								<c:forEach items="${TRANSACTION_TYPE}" var="item">
									<c:if test="${item.id ne 'NT'}">
										<c:choose>
						   					<c:when test="${_e_eml_Form.transaction==item.id}">
												<option value="${item.id}" selected="selected">${item.name}</option>
											</c:when>
											<c:otherwise>
								    			<option value="${item.id}">${item.name}</option>
											</c:otherwise>
										</c:choose>
									</c:if>
									<c:if test="${item.id eq 'NT'}">
										<c:if test="${_e_eml_Form.nonTaxInvoiceShowFlg eq '1'}"> 
											<c:choose>
												<c:when test="${_e_eml_Form.transaction == item.id}">
													<option value="${item.id}" selected="selected">${item.name}</option>
												</c:when>
												<c:otherwise>
								    				<option value="${item.id}">${item.name}</option>
												</c:otherwise>
											</c:choose>
										</c:if>
									</c:if>
							</c:forEach>
					</html:select>
				</td>
				<td align="right"><bean:message key="screen.e_eml_s01.pdfGen"/><bean:message key="screen.e_eml_s01.colon"/> 
				</td>
				<td>
					<html:multibox name="_e_eml_Form" property="pdfGen" value="1">
			        </html:multibox>
			        <bean:message key="screen.e_eml_s01.yes"/>
			        <html:multibox name="_e_eml_Form" property="pdfGen" value="0">
			        </html:multibox>
			        <bean:message key="screen.e_eml_s01.no"/>
				</td>
				<td></td>
			</tr>
			<tr>
				<td align="right"><bean:message key="screen.e_eml_s01.batchId"/><bean:message key="screen.e_eml_s01.colon"/> 
				</td>
				<td width="30%"><html:text name="_e_eml_Form" property="batchId" styleClass="QCSTextBox" style="width:210px;"></html:text>
				</td>
				
				<td align="right"><bean:message key="screen.e_eml_s01.emailSend"/><bean:message key="screen.e_eml_s01.colon"/> 
				</td>
				<td>
					<html:multibox name="_e_eml_Form" property="emailSend" value="1">
			        </html:multibox>
			        <bean:message key="screen.e_eml_s01.yes"/>
			        <html:multibox name="_e_eml_Form" property="emailSend" value="0">
			        </html:multibox>
			        <bean:message key="screen.e_eml_s01.no"/>
			    </td>
				<td></td>
			</tr>
			<tr>
				<td align="right"><bean:message key="screen.e_eml_s01.pdf"/><bean:message key="screen.e_eml_s01.colon"/> 
				</td>
				<td width="30%"><html:text name="_e_eml_Form" property="pdfFilename" styleClass="QCSTextBox" style="width:210px;"></html:text>
				</td>
				<td align="right"><bean:message key="screen.e_eml_s01.sameEmail"/><bean:message key="screen.e_eml_s01.colon"/> 
				</td>
				<td>
					<html:multibox name="_e_eml_Form" property="sameEmail" value="1">
			        </html:multibox>
			        <bean:message key="screen.e_eml_s01.yes"/>
			        <html:multibox name="_e_eml_Form" property="sameEmail" value="0">
			        </html:multibox>
			        <bean:message key="screen.e_eml_s01.no"/>
				</td>
				<td></td>
			</tr>
			<tr>
			<td colspan="3">
			<div class="pdfZipFileTable">
			<table class="border">
			<tr>
			<td align="left" nowrap="nowrap"><bean:message key="screen.e_eml_s01.pdfZip"/><bean:message key="screen.e_eml_s01.colon"/> 
				</td>
				<td align="left" colspan="2">
				<div class="pdfmessage">
					 <t:defineCodeList id="LIST_PDF_ZIP" />
					 <html:select property="pdfZipFile" name="_e_eml_Form">
					 <html:options collection="LIST_PDF_ZIP" property="id" labelProperty="name"/>
					 </html:select>
					  </div>
				</td>
			</tr>
			</table>
			</div>
			</td>
				<td>
			    	<a class="hyperLink" onclick="linkBathHistoryClick('<%=request.getContextPath()%>/E_SET/SC_E_SET_S02BL.do?SCR_ID=EEML&FUNC_ID=EM&TITLE_HDR=<bean:message key="screen.e_eml_s01.passtitle"/>');">
			    	<bean:message key="screen.e_eml_s01.batchHistory"/>
			    	</a>
				</td>
			</tr>
		</table>
		<div style="margin-top:5px"></div>
		</div>
		<br/>
			<div>
				<html:submit property="forward_search" onclick="searchData();showLoadingTipWindow();"><bean:message key='screen.e_eml_s01.searchBtn'/></html:submit>
				<bs:buttonLink action="/E_EMLS01BL" value="Reset"/>
				<logic:equal value="2" name="accessRightBean"> 
				<c:if test="${_e_eml_Form.totalCount > 0}">
					<html:submit property="forward_pdf" onclick="showLoadingTipWindow();"><bean:message key="screen.e_eml_s01.genPdf"/></html:submit>
					<html:submit property="forward_pdfall" onclick="showLoadingTipWindow();"><bean:message key="screen.e_eml_s01.genPdfAll"/></html:submit>
					<html:submit property="forward_email" onclick="showLoadingTipWindow();"><bean:message key="screen.e_eml_s01.emal"/></html:submit>
					<html:submit property="forward_emailall" onclick="showLoadingTipWindow();"><bean:message key="screen.e_eml_s01.emalall"/></html:submit>	
				</c:if>
				
				<c:if test="${_e_eml_Form.totalCount == -1 or _e_eml_Form.totalCount == null}">
				 	<html:submit property="forward_pdf" disabled="true"><bean:message key="screen.e_eml_s01.genPdf"/></html:submit>
                    <html:submit property="forward_pdfall" disabled="true"><bean:message key="screen.e_eml_s01.genPdfAll"/></html:submit> 
                    <html:submit property="forward_email" disabled="true"><bean:message key="screen.e_eml_s01.emal"/></html:submit>
					<html:submit property="forward_emailall" disabled="true"><bean:message key="screen.e_eml_s01.emalall"/></html:submit>	 
				</c:if>
				</logic:equal>
			</div>
			<br/>
			<table class="searchResultNo" cellpadding="0" cellspacing="0">
	  	<tr>
			<td style="font-size:12pt;">
				<bean:message key="screen.e_eml_s01.seachFound"/> <bean:message key="screen.e_eml_s01.colon"/>
				<c:if test="${_e_eml_Form.totalCount != -1}">
					<bean:write name="_e_eml_Form" property="totalCount"/>
				</c:if>
			</td>	
		</tr>
	</table> 
		<table class="pageLink" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td><bean:message key="screen.e_eml_s01.gotoPage"/> <bean:message key="screen.e_eml_s01.colon"/>
				<ts:pageLinks 
		    		id="curPageLinks"
					action="${pageContext.request.contextPath}/E_EML/E_EMLS02BL.do" 
					name="_e_eml_Form" 
					rowProperty="row"
					totalProperty="totalCount" 
					indexProperty="startIndex"
					currentPageIndex="now" 
					totalPageCount="total"
					submit="true" />
				<logic:present name="curPageLinks">
					<bean:write name="curPageLinks" filter="false"/>
				</logic:present>
			</td>
		</tr>
	</table>
	<div class="section">
	<table  class="resultInfo" cellpadding="0" cellspacing="0" border="0">
	  <tr>
	  	<th width="3%"><input type="checkbox" name="idRefsAll" id="idRefsAll"/></th>
		<th width="8%"><bean:message key="screen.e_eml_s01.date"/></th>
		<th width="8%"><bean:message key="screen.e_eml_s01.billDocuNo"/></th>
		<th width="5%"><bean:message key="screen.e_eml_s01.idcust"/></th>
		<th width="7%"><bean:message key="screen.e_eml_s01.peopleAccNo"/></th>
		<th width="12%"><bean:message key="screen.e_eml_s01.customerName"/></th>
		<th width="2%"><bean:message key="screen.e_eml_s01.trans"/></th>
		<th width="7%"><bean:message key="screen.e_eml_s01.billaccNo"/></th>
		<th width="2%"><bean:message key="screen.e_eml_s01.emal"/></th>
		<th width="7%"><bean:message key="screen.e_eml_s01.emailTo"/></th>
		<th width="5%"><bean:message key="screen.e_eml_s01.batchId"/></th> 
		<th width="14%"><bean:message key="screen.e_eml_s01.pdf"/></th>
		<th width="5%"><bean:message key="screen.e_eml_s01.batchId"/></th> 
		<th width="7%"><bean:message key="screen.e_eml_s01.sent"/></th>
		<th width="7%"><bean:message key="screen.e_eml_s01.noOfEmail"/></th> 
	  </tr>
	  <c:set var="allIdRefs"></c:set>
	  <bean:define id="usedRef" value="0"/>
	  <logic:present property="billInfo" name="_e_eml_Form">
		  <logic:iterate id="bif" name="_e_eml_Form" property="billInfo" >
			  <tr>
			    <td class="defaultText">
				    <c:if test="${bif.email eq '1'}">
				    	<c:forEach items="${_e_eml_Form.idRefs}" var="oldRef">
							<c:if test="${oldRef==bif.billDocuNo}">
								<input type="checkbox" name="idRefs" value="${bif.billDocuNo}" checked/>											
								<bean:define id="usedRef" value="1"/>
							</c:if>
						</c:forEach>
						<c:if test="${usedRef!=1}">
							<bean:define id="usedRef" value="1"/>
					  		<html:checkbox property="idRefs" value="${bif.billDocuNo}"></html:checkbox>
						</c:if>
						<bean:define id="usedRef" value="0"/>
						<c:set var="allIdRefs" value="${allIdRefs},${bif.billDocuNo}"></c:set>
				    	<%-- <html:checkbox property="idRefs" value="${bif.billDocuNo}"></html:checkbox> --%>
				    </c:if>
			    </td>
			    <td class="defaultText">		    	
			    	<bean:write name="bif" property="datereq" format="dd/MM/yyyy"/>
			    </td>
			    <td class="defaultText">
			    	<a class="hyperLink" onclick="linkBillDocumentClick('<bean:write name="bif" property="billDocuNo"/>','<%=request.getContextPath()%>');">
			    	<bean:write name="bif" property="billDocuNo"/>
			    	</a>
			    </td>
			    <td class="defaultText">    
				    <bean:write name="bif" property="idCust"/>
			    </td>
			    <td class="defaultText">    
				    <bean:write name="bif" property="peopleAccNo"/>
			    </td>
			    <td class="defaultText">
				    <bean:write name="bif" property="customerName"/>
			    </td>
			     <td class="defaultText">
			     	<bean:write name="bif" property="transType"/>
			    </td>
			    <td class="defaultText">
				    <bean:write name="bif" property="billAccNo"/>
			    </td>
			    <td class="defaultText">	    
				  <c:choose>
				    	<c:when test="${bif.email eq '1'}">
				    		Yes
				    	</c:when>
				    	<c:otherwise>
				    		No
				    	</c:otherwise>
			    	</c:choose>
			    </td>
			    <td class="defaultText">
			    	<c:forTokens items="${bif.emailTo}" delims=";" var="email" varStatus="vs">
						<c:if test="${!vs.last}">
							<c:out value="${email}"/>;<br/>
						</c:if>
						<c:if test="${vs.last}">
							<c:out value="${email}"/>
						</c:if>
					</c:forTokens>
			    	<br/>
			    	<c:if test="${empty bif.emailCc}">
			    	-
			    	</c:if>
			    	<c:if test="${not empty bif.emailCc}">
				    	<c:forTokens items="${bif.emailCc}" delims=";" var="email" varStatus="vs">
							<c:if test="${!vs.last}">
								<c:out value="${email}"/>;<br/>
							</c:if>
							<c:if test="${vs.last}">
								<c:out value="${email}"/>
							</c:if>
						</c:forTokens>
			    	</c:if>
			    	
			    </td>
			    <td class="defaultText">
			    	<bean:write name="bif" property="batchId"/>
			    </td>
			     <td class="defaultText">    
				    <a href="#" onclick="clickDownload('<bean:write name="bif" property="id_doc"/>') "><bean:write name="bif" property="pdf"/></a>
			    </td> 
			    <td class="defaultText">  
			    	<a class="hyperLink" onclick="linkClick('batchId=<bean:write name="bif" property="emailBatchId"/>','<%=request.getContextPath()%>');">  
				    <bean:write name="bif" property="emailBatchId"/>
				    </a>
			    </td>
			    <td class="defaultText">    
				    <bean:write name="bif" property="sent"/>
			    </td>
			    <td class="defaultText">   
			    	<a class="hyperLink" onclick="linkClick('billDocuNo=<bean:write name="bif" property="billDocuNo"/>','<%=request.getContextPath()%>');">   
				    <bean:write name="bif" property="noEmail"/>
				    </a>
			    </td>
			  </tr>
		   </logic:iterate>
	   </logic:present>
	</table>
	</div>
	<html:hidden property="allIdRefs" value="${allIdRefs}"/>
	<div style="display:none;">
		<bean:define id="usedRef" value="0"/>
			<c:forEach items="${_e_eml_Form.idRefs}" var="oldRef">
				<c:forEach items="${allIdRefs}" var="all">
					<c:if test="${oldRef==all}">
						<bean:define id="usedRef" value="1"/>
					</c:if>
				</c:forEach>
				<c:choose>
					<c:when test="${usedRef==1}">
						<bean:define id="usedRef" value="0"/>
					</c:when>
					<c:otherwise>
						<input type="checkbox" name="idRefs" value="${oldRef}" checked/>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>
	</div>
</ts:form>
	<div class="message">
			<ts:messages id="message" message="true">
				<bean:write name="message"/><br/>
			</ts:messages>
		</div>
		<div class="error">
			<ts:messages id="message">
				<bean:write name="message"/><br/>
			</ts:messages>
		</div>
</body>
</html>