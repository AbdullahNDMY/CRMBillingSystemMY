<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/bs" prefix="bs" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<title><bean:message key="screen.b_bth_s01.title"/></title>
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
		<link href="${pageContext.request.contextPath}/B_BTH/css/b_bth.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   		<style type="text/css">
		    .resultInfo tr td{
		        padding: 7px 5px;
		    }
		    .printOptionTable{
		        padding-left: 47px;
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
		</style>
   		<script type="text/javascript" language="javascript">
			$(document).ready(function(){
				$("#idRefsAll").click(function(){
					var checked_status = this.checked;
					$('input[name="idRefs"]').each(function(){
						if(checked_status){
							$(this).attr('checked', true);
						}else{
							$(this).attr('checked', false);
						}
					});
				});
			});
			function searchClick() {
				var checkAlls = document.getElementsByName("idRefs");
				for (var i=0;i<checkAlls.length;i++){
					if(checkAlls[i].checked){
						checkAlls[i].checked=false;
					}
				}
				var startIndex = document.getElementById("startIndex");
				if(startIndex!=null && startIndex!=undefined) {
					startIndex.value="0";
				}
			}
			//.prompt confirmation for authorised signature printing
			function printAutSignConfirm(printstyle){
				var allSize=0;
				$('input[name="idRefs"]').each(function(){
					var checked_status=$(this).attr('checked');
					if(checked_status){
						allSize=allSize+1;
					}
				});
				// #186 Start
				var exportLimit = $("#exportLimit").val();
				var searchCount = $("#searchCount").val();
				var warnMsg = "Total results to be exported exceed export limit " + exportLimit;
				//click button 'print'
				if(printstyle=='print'){
					if(allSize>0){
						if (allSize >= parseInt(exportLimit)) {
							alert(warnMsg);
							return false;
						} else {
							showLoadingTipWindow();
							showMsg();
						}
					}
				}
				//click button 'printAll'
				if(printstyle=='printall'){
					if (parseInt(searchCount) >= parseInt(exportLimit)) {
						alert(warnMsg);
						return false;
					} else {
						showLoadingTipWindow();
						showMsg();
					}
				}
				// #186 End
			}
			//show  message
			function showMsg(){
				var svalue=document.getElementById("hidsysBaseVal").value;
				var ERR4SC19_info = document.getElementById("ERR4SC19_info").value;
				if ("MY02"==svalue) {
					var msg = new messageBox("");
					var isConfirm = (msg.POPCFM(ERR4SC19_info) == msg.YES_CONFIRM);
					if (isConfirm) {
						//Click Yes, pass $AutSign=No
						document.getElementById("autSign").value="No";
					} else{
						//Click No, pass $AutSign=Yes
						document.getElementById("autSign").value="Yes";
					}
				}
			}
		</script>
		<style type="text/css">
		    .resultInfo tr td{
		        padding: 7px 5px;
		    }
		</style>
	</head>
	<body id="b" onload="initMain();">
		<ts:form action="/SC_B_BTH_P01DSP" enctype="multipart/form-data">
		<!-- Add #156 Start-->
		<html:hidden property="billCnAmtNegative"></html:hidden>
		<!-- Add #156 End-->
		<!-- #186 Start -->
	    <t:defineCodeList id="LIST_EXPORT_LIMIT" />
	    <!-- #263 [T11] Add customer type at inquiry screen and export result CT 06062017 ST-->
	    <t:defineCodeList id="LIST_CUSTOMERTYPE" />
	    <!-- #263 [T11] Add customer type at inquiry screen and export result CT 06062017 EN-->
	    <input id="exportLimit" type="hidden" value="<t:writeCodeValue codeList='LIST_EXPORT_LIMIT' key='B-BTH' name='export_limit'></t:writeCodeValue>"/>
	    <input id="searchCount" type="hidden" value="${_b_bthForm.map.totalRow}"/>
	    <!-- #186 End -->
		<div id="contentDiv" style="width:1300px">
			<h1 class="title"><bean:message key="screen.b_bth_s01.title"/></h1>
			<div class="section" style="border-top:2px solid #cecece;border-bottom:2px solid #cecece;margin-top:-5px;">
				<table cellspacing="5" cellpadding="5">
				    <tr>
				        <td align="right"><bean:message key="screen.b_bth_s01.docno"/> :</td>
						<td><html:text property="documentNo" style="width:191px;"></html:text></td>
						<td align="right"><bean:message key="screen.b_bth_s01.0004"/> :</td>
						<td>
							<html:text property="fromDate" readonly="true" style="width:70px;"></html:text>
							<t:inputCalendar for="fromDate" format="dd/MM/yyyy"/>
							-
							<html:text property="toDate" readonly="true" style="width:70px;"></html:text>
							<t:inputCalendar for="toDate" format="dd/MM/yyyy"/>
						</td>
				    </tr>
				    <tr>
						<!-- #263 [T11] Add customer type at inquiry screen and export result CT 08062017 ST-->
						<td align="right"><bean:message key="screen.b_bth_s01.00028"/> :</td>
						<td><html:text property="tbxCustomerId" style="width:191px;"></html:text></td>
						<!-- #263 [T11] Add customer type at inquiry screen and export result CT 08062017 EN-->
						<td align="right">
						    <bean:message key="screen.b_bth_s01.deletedStatus"/>&nbsp;:
						</td>
						<td>
						    <html:select property="cboDeletedStatus" style="width:191px;">
								<html:option value="" ><bean:message key="screen.b_bth_s01.0027"/></html:option>
								<html:option value="0" >Normal</html:option>
								<html:option value="1" >Deleted</html:option>
							</html:select>
						</td>
				    </tr>
				    <tr>
						<td align="right"><bean:message key="screen.b_bth_s01.0001"/> :</td>
						<td><html:text property="customerName" style="width:191px;"></html:text> </td>
						<td align="right">
							<bean:message key="screen.b_bth_s01.issueType"/>&nbsp;:
						</td>
						<td>
						    <span style="width:100px;">
							    <html:checkbox property="issueTypeSingpost" value="0">
								    <%-- <bean:message key="screen.b_bth_s01.issueTypeSingpost"/> --%>
								    ${_b_bthForm.map.issueTypeSingpostValue}
							    </html:checkbox>
							</span>
							<span style="width:150px;">
							    <html:checkbox property="issueTypeAuto" value="0">
								    <%-- <bean:message key="screen.b_bth_s01.issueTypeAuto"/> --%>
								    ${_b_bthForm.map.issueTypeAutoValue}
							    </html:checkbox>
							</span>
							<html:checkbox property="issueTypeManual" value="0">
								<%-- <bean:message key="screen.b_bth_s01.issueTypeManual"/> --%>
								${_b_bthForm.map.issueTypeManualValue}
							</html:checkbox>
						</td>
				    </tr>
				    
					<tr>
	            	<!-- #263 [T11] Add customer type at inquiry screen and export result CT 08062017 ST-->
		            <td align="right">
						<bean:message key="screen.b_bth_s01.00029" />&nbsp;:
	            	</td>
		            <td><html:select
		                    property="cboCustomerType"
		                    style="width:190px;">
		                    <option value="">
		                        <bean:message key="screen.b_bth_s01.0027" />
		                    </option>
		                    <%--<html:optionsCollection name="LIST_CUSTOMERTYPE"  label="name" value="id"/>--%>
		                    <c:forEach items="${LIST_CUSTOMERTYPE}" var="item">
		                        <c:if test="${item.id ne 'BOTH'}">
		                            <html:option value="${item.id}">${item.name}</html:option>
		                        </c:if>
		                    </c:forEach>
		                </html:select></td>
		                <!-- #263 [T11] Add customer type at inquiry screen and export result CT 08062017 EN-->
						<td align="right">
							<bean:message key="screen.b_bth_s01.docdeliveryby"/>&nbsp;:
						</td>
						<td>
						   <bean:message key="screen.b_bth_s01.email"/>&nbsp;&nbsp;
						<html:multibox  property="deliveryEmail" value="1" />
							<bean:message key="screen.b_bth_s01.emailyes"/>
						<html:multibox property="deliveryEmail" value="0" />
							<bean:message key="screen.b_bth_s01.emailno"/><br/>
						</td> 
						
					</tr>
					<tr>
						<td align="right"><bean:message key="screen.b_bth_s01.0006"/> :</td>
						<td><html:text property="billingAccount" style="width:191px;"></html:text></td>
                        <td align="right"></td>
						<td>
                        	<!-- Post,Courier,By Hand -->
		                    <html:multibox property="delivery" value="1">
					        </html:multibox>
					        <bean:message key="screen.b_bth_s01.post"/>
					        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					        <html:multibox property="delivery" value="2">
					        </html:multibox>
					        <bean:message key="screen.b_bth_s01.courier"/>
					        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					        <html:multibox property="delivery" value="4">
					        </html:multibox>
					        <bean:message key="screen.b_bth_s01.byhand"/>
                        </td>
					</tr> 
                      <tr>  
                        <td align="right"><bean:message key="screen.b_bth_s01.0002"/> :</td>
						<td>
						    <t:defineCodeList id="TRANSACTION_TYPE" />
							<html:select property="billingType" style="width:191px;">
								<html:option value=""><bean:message key="screen.b_bth_s01.0027"/></html:option>
								<c:forEach items="${TRANSACTION_TYPE}" var="item">
									<c:if test="${item.id ne 'NT'}">
										<c:choose>
						   					<c:when test="${_b_bthForm.map.billingType==item.id}">
												<option value="${item.id}" selected="selected">${item.name}</option>
											</c:when>
											<c:otherwise>
								    			<option value="${item.id}">${item.name}</option>
											</c:otherwise>
										</c:choose>
									</c:if>
									<c:if test="${item.id eq 'NT'}">
										<c:if test="${_b_bthForm.map.nonTaxInvoiceShowFlg eq '1'}"> 
											<c:choose>
												<c:when test="${_b_bthForm.map.billingType == item.id}">
													<option value="${item.id}" selected="selected">${item.name}</option>
												</c:when>
												<c:otherwise>
								    				<option value="${item.id}">${item.name}</option>
												</c:otherwise>
											</c:choose>
										</c:if>
									</c:if>
								</c:forEach>
								<%-- <html:options collection="TRANSACTION_TYPE" property="id" labelProperty="name"/> --%>
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right"><bean:message key="screen.b_bth_s01.billingcurrency"/> :</td>
                        <td colspan="2">
                            <t:defineCodeList id="LIST_CURRENCY" />
                            <html:select property="cboBillingCurrency">
	                            <html:option value="" ><bean:message key="screen.b_bth_s01.blankItem"/></html:option>
	                            <html:options collection="LIST_CURRENCY" property="id" labelProperty="name"/>
                            </html:select>
                        </td>
					</tr>
					<tr>
						<td colspan="3">
						<div class="printOptionTable">
						<table>
							<tr>
								<td colspan="2" align="right" class="border"><bean:message key="screen.b_bth_s01.printoption"/> : &nbsp;
						       		<t:defineCodeList id="LIST_PRINT_OPTION" />
					            	<c:forEach items="${LIST_PRINT_OPTION}" var="item">
					                <html:select property="cboPrintOption" value="${item.id}">
					                <html:option value="0" ><bean:message key="screen.b_bth_s01.printoption0"/></html:option>
					                <html:option value="1" ><bean:message key="screen.b_bth_s01.printoption1"/></html:option>
					                </html:select>
					                </c:forEach>&nbsp;&nbsp;&nbsp;
					            </td>
							</tr>
						</table>
						</div>
						</td>
					</tr>
				</table>
			</div>
			<br/>
			<div>
				<%
				String accType = ((nttdm.bsys.common.fw.BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT")).getUserAccessInfo("B", "B-BTH").getAccess_type();
				%>
				<bean:define id="accessType" value="<%=accType%>"></bean:define>
				<html:submit property="forward_search" onclick="searchClick();showLoadingTipWindow();"><bean:message key='screen.b_bth_s01.0007'/></html:submit>
				<bs:buttonLink action="/RP_B_BTH_P01_01BL" value="Reset"/>
				<c:if test="${accessType==1 or accessType==2}">
                    <c:if test="${_b_bthForm.map.totalRow > 0}">
					   <html:submit property="forward_print" onclick="return printAutSignConfirm('print');showLoadingTipWindow();"><bean:message key="screen.b_bth_s01.0009"/></html:submit>
					   <html:submit property="forward_printall" onclick="return printAutSignConfirm('printall');showLoadingTipWindow();"><bean:message key="screen.b_bth_s01.0010"/></html:submit>	
                    </c:if>
                    <c:if test="${_b_bthForm.map.totalRow == 0 or _b_bthForm.map.totalRow == null}">
                        <html:submit property="forward_print" disabled="true"><bean:message key="screen.b_bth_s01.0009"/></html:submit>
                        <html:submit property="forward_printall" disabled="true"><bean:message key="screen.b_bth_s01.0010"/></html:submit>  
                    </c:if>
				</c:if>
			</div>
			<br/>
			<div class="section">
				<div class="group result">
					<h2><bean:message key="screen.b_bth_s01.0011" /> : ${_b_bthForm.map.totalRow}</h2>
				</div>
				<div class="pageLink"><bean:message key="screen.b_bth_s01.0012"/>:
					<ts:pageLinks 
		    			id="curPageLinks"
						action="${pageContext.request.contextPath}/B_BTH/RP_B_BTH_P01_02BL.do" 
						name="_b_bthForm" 
						rowProperty="row"
						totalProperty="totalRow" 
						indexProperty="startIndex"
						currentPageIndex="now" 
						totalPageCount="total"
						submit="true"
					/>
					<logic:present name="curPageLinks">  
						<bean:write name="curPageLinks" filter="false"/>
					</logic:present>
				</div>
			</div>
			<div class="section">
				<table class="resultInfo" cellpadding="0" cellspacing="0" border="0">
					  <tr>
					  	<th><input type="checkbox" name="idRefsAll" id="idRefsAll"/></th>
					    <th><bean:message key="screen.b_bth_s01.0015"/></th>
					    <th><bean:message key="screen.b_bth_s01.0017"/></th>
					    <th><bean:message key="screen.b_bth_s01.0018"/></th>
					    <th><bean:message key="screen.b_bth_s01.0016"/></th>
					    <th><bean:message key="screen.b_bth_s01.0019"/></th>
					    <th><bean:message key="screen.b_bth_s01.0020"/></th>
					    <th><bean:message key="screen.b_bth_s01.0021"/></th>
					     <th><bean:message key="screen.b_bth_s01.deliveryby"/></th>
					    <th><bean:message key="screen.b_bth_s01.issueType"/></th>
					    <th><bean:message key="screen.b_bth_s01.deletedStatus"/></th>
					    <th><bean:message key="screen.b_bth_s01.0022"/></th>
					    <th><bean:message key="screen.b_bth_s01.0023"/></th>
					  </tr>
					  <c:set var="allIdRefs"></c:set>
					  <bean:define id="usedRef" value="0"/>
					  <c:forEach items="${_b_bthForm.map.listPrintHistories}" var="item" varStatus="status">
					  	<tr>
					  		<td class="defaultText">
					  			<c:if test="${item.CHK_APPLY eq 0}">
					  				<c:forEach items="${_b_bthForm.map.idRefs}" var="oldRef">
										<c:if test="${oldRef==item.ID_REF}">
											<input type="checkbox" name="idRefs" value="${item.ID_REF}" checked/>											
											<bean:define id="usedRef" value="1"/>
										</c:if>
									</c:forEach>
									<c:if test="${usedRef!=1}">
										<bean:define id="usedRef" value="1"/>
					  					<html:checkbox property="idRefs" value="${item.ID_REF}"></html:checkbox>
									</c:if>
									<bean:define id="usedRef" value="0"/>
					  				<c:set var="allIdRefs" value="${allIdRefs},${item.ID_REF}"></c:set>
					  			</c:if>
					  		</td>
					  		<td class="defaultText"><fmt:formatDate value="${item.DATE_REQ}" pattern="dd/MM/yyyy"/></td>
					  		<td class="defaultText">
					  			&nbsp;
					  			<a href="${pageContext.request.contextPath}/B_BIL/RP_B_BIL_S02_01BL.do?idRef=${item.ID_REF}&mode=view">${item.ID_REF}</a>
					  		</td>
					  		<td class="defaultText">${item.CUST_NAME}&nbsp;</td>
					  		<td class="defaultText">${item.BILL_TYPE}&nbsp;</td>
					  		<td class="defaultText">${item.BILL_ACC}&nbsp;</td>
					  		<td class="defaultText">${item.BILL_CURRENCY}&nbsp;</td>
					  		<td class="defaultText">
					  			<!-- Delete #156 Start-->
					  			<!--<fmt:formatNumber value="${item.BILL_AMOUNT}" pattern="#,##0.00"/>&nbsp;-->
					  			<!-- Delete #156 End-->
					  			<!-- Add #156 Start-->
					  			<c:if test="${item.BILL_TYPE eq 'CN'}">
					  				<c:if test="${_b_bthForm.map.billCnAmtNegative eq '1'}">
					  					-<fmt:formatNumber value="${item.BILL_AMOUNT}" pattern="#,##0.00"/>&nbsp;
					  				</c:if>
					  				<c:if test="${_b_bthForm.map.billCnAmtNegative != '1'}">
					  					<fmt:formatNumber value="${item.BILL_AMOUNT}" pattern="#,##0.00"/>&nbsp;
					  				</c:if>
					  			</c:if>
					  			<c:if test="${item.BILL_TYPE != 'CN'}">
					  				<fmt:formatNumber value="${item.BILL_AMOUNT}" pattern="#,##0.00"/>&nbsp;
					  			</c:if>
					  			<!-- Add #156 End--> 
					  		</td>
					  		<td class="defaultText">
						  		<c:if test="${item.DELIVERY_EMAIL eq '1'}">
							 		<bean:message key="screen.b_bth_s01.email"/>/
								</c:if>
								<c:if test="${item.DELIVERY eq '1'}">
									<bean:message key="screen.b_bth_s01.post"/>
								</c:if>
								<c:if test="${item.DELIVERY eq '2'}">
									<bean:message key="screen.b_bth_s01.courier"/>
								</c:if>
								<c:if test="${item.DELIVERY eq '4'}">
									<bean:message key="screen.b_bth_s01.byhand"/>
								</c:if>
							</td>
					  		<td class="defaultText">
					  		    <!-- #191 Start -->		
								<c:if test="${item.IS_SINGPOST eq '1' and item.IS_MANUAL eq '0'}">
					  		        <%-- <bean:message key="screen.b_bth_s01.issueTypeSingpost"/> --%>
					  		        ${_b_bthForm.map.issueTypeSingpostValue}
					  		    </c:if>
					  		    <c:if test="${item.IS_SINGPOST eq '0' and item.IS_MANUAL eq '0'}">
					  		        <%-- <bean:message key="screen.b_bth_s01.issueTypeAuto"/> --%>
					  		        ${_b_bthForm.map.issueTypeAutoValue}
					  		    </c:if>
					  		    <c:if test="${item.IS_SINGPOST eq '0' and item.IS_MANUAL eq '1'}">
					  		        <%-- <bean:message key="screen.b_bth_s01.issueTypeManual"/> --%>
					  		        ${_b_bthForm.map.issueTypeManualValue}
					  		    </c:if>
					  		    &nbsp;
					  		    <!-- #191 End -->
					  		</td>
					  		<td class="defaultText">${item.IS_DELETED eq '1' ? 'Deleted' : 'Normal'} &nbsp;</td>
					  		<td class="defaultText">${item.NO_PRINTED}&nbsp;</td>
					  		<td class="defaultText">${item.USER_PRINTED}&nbsp;</td>
					  	</tr>
					  </c:forEach>
				</table>
				<html:hidden property="allIdRefs" value="${allIdRefs}"/>
				<html:hidden styleId="autSign" property="autSign" />
				<input type="hidden" id="hidsysBaseVal" value="${_b_bthForm.map.sysBaseVal}">
				<!-- save old idRefs -->
				<div style="display:none;">
					<bean:define id="usedRef" value="0"/>
					<c:forEach items="${_b_bthForm.map.idRefs}" var="oldRef">
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
		</div>
		<input type="hidden" id="ERR4SC19_info" value='<bean:message key="info.ERR4SC19"/>'/>
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
</html:html>