<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
   		<link type="text/css" rel="stylesheet" href="css/m_csts01.css" />
   		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
   		<script type="text/javascript" src="js/m_csts01_r11_s.js"></script>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>	
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/tabcontent.js"></script>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.4.2.js"></script>
   		<script type="text/javascript" src="js/actb.js"></script>
   		<script type="text/javascript" src="js/common.js"></script>
   		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/stylesheet/tabcontent.css"/>
		<title></title>
		<script type="text/javascript">
		   $(function() {
				document.onkeydown = function(e){
					var ev = document.all ? window.event : e;
					if (ev.keyCode == 13) {
						var bid = document.activeElement.id;
						if(typeof(bid) == "undefined" || bid == null || bid == "" || bid != "account"){
							var $selectedTab = $('#searchTabs li').find('.selected');
							var selected = $selectedTab[0].rel;
							if (selected != null && selected == 'tabBasicSearch') {
								submitForm('basic search','','');
							} else {
								submitForm('advanced search','','');
							}
						}
					}
				};
		   });
		   
	   	 var customarray="";	   		
		</script>
	</head>
	<body id="m_csts01" onload="initMain();initPage('${_m_cstForm.searchType}');">
	<ts:form action="/M_CSTS01DSP" method="POST">
		<html:hidden name="_m_cstForm" property="event"/>
		<html:hidden name="_m_cstForm" property="event1"/>
		<html:hidden name="_m_cstForm" property="mode"/>
		<html:hidden name="_m_cstForm" property="clickSearchButton" value="0"/>
		
		<html:hidden name="_m_cstForm" property="temp_cust_acc_no"/>
		<html:hidden name="_m_cstForm" property="temp_cust_name"/>
		<html:hidden name="_m_cstForm" property="temp_co_regno"/>
		<html:hidden name="_m_cstForm" property="temp_country"/>
		<html:hidden name="_m_cstForm" property="searchType"/>
		<html:hidden name="_m_cstForm" property="fromPopup" value="noPopup"/>
		
		<t:defineCodeList id="LIST_COUNTRY"/>
		<t:defineCodeList id="LIST_SYSTEM_BASE"/>
        <t:defineCodeList id="LIST_CUSTOMERTYPE"/>
		<table class="subHeader" cellpadding="0" cellspacing="0">
    		<tr>
    			<td><bean:message key="screen.m_cst.screen_name"/></td>
    		</tr>
    	</table>
		
		<div style="background-color: #F8F8F8;">
		<br>		
	    <!-- JSP Tab control -->
		<ul id="searchTabs" class="shadetabs">
			<li><a href="#" rel="tabBasicSearch" > <bean:message key="screen.m_cst_s.label_basic_search"/></a></li>
			<li><a href="#" rel="tabAdvancedSearch" ><bean:message key="screen.m_cst_s.label_advanced_search"/></a></li>
		</ul>
		
		
			<div id="tabBasicSearch" class="tabcontent">
				<!-- Basic Search -->
				<div style="border:1px solid gray; width:100%; margin-bottom: 1em; padding: 10px">
		    	<table class="information" cellpadding="0" cellspacing="0">
		    		<tr>
		    			<td align="right"><bean:message key="screen.m_cst.label_customer_name"/><bean:message key="screen.m_cst.label_colon"/></td>
		    			<td align="left"><html:text styleId="tbxCustomerName1" name="_m_cstForm" property="cust_name"/></td>
		    			<td align="right" width="30%"><bean:message key="screen.m_cst.label_co_reg_no"/><bean:message key="screen.m_cst.label_colon"/></td>
		    			<td align="left"><html:text styleId="tbxCo.Reg.No1" name="_m_cstForm" property="co_regno"/></td>
		    		</tr>
		    		<tr>
		    			<td align="right" width="30%"><bean:message key="screen.m_cst_s.label_peoplesoft_acc_no"/><bean:message key="screen.m_cst.label_colon"/></td>
		    			<td align="left" width="20%"><html:text  styleId="tbxCustomerAccNoBasic1" name="_m_cstForm" property="cust_acc_no"/></td>
		    			<td align="right" width="30%"><bean:message key="screen.m_cst.label_billing_country"/><bean:message key="screen.m_cst.label_colon"/></td>	
			   			<td>
				   			<logic:present name="_m_cstForm" property="country">
				   				<bean:define id="rselected" name="_m_cstForm" property="country" />	
				   			</logic:present>
				   			<logic:notPresent name="_m_cstForm" property="country">
				   				<bean:define id="rselected" value=""/>
				   			</logic:notPresent>
				   			<html:select styleId="tbxBillingCountry1" name="_m_cstForm" property="country"  value="${rselected}" >
				   				<option value=""><bean:message key="screen.m_cst.listBox.default"/></option>
								<html:options collection="LIST_COUNTRY" property="id" labelProperty="name"/>
							</html:select>
						</td>
						
		    		</tr>
		    		<tr>
		    		    <td align="right" width="30%"><bean:message key="screen.m_cst.label_customer_id"/><bean:message key="screen.m_cst.label_colon"/></td>
                        <td align="left"><html:text styleId="tbxCo.Reg.No1" name="_m_cstForm" property="cust_id"/></td>
                        <td align="right" width="30%"><bean:message key="screen.m_cst.label_customer_type"/><bean:message key="screen.m_cst.label_colon"/></td>
                        <td align="left" width="20%">
                            <nested:select property="cust_type" styleClass="searchCustomerType">
                                <option value=""><bean:message key="screen.m_cst.listBox.default"/></option>
                                <c:forEach items="${LIST_CUSTOMERTYPE}" var="item">
                                    <c:if test="${item.id ne 'BOTH'}">
                                        <html:option value="${item.id}" >${item.name}</html:option>
                                    </c:if>
                                </c:forEach>
                            </nested:select>
                        </td>
                    </tr>
		    	</table>
		    	</div>
		    	<table class="buttonGroup" cellpadding="0" cellspacing="0">
		    		<tr>
		    			<td>
		    				<button id="ActSearch1" onclick="submitForm('basic search','','');"><bean:message key="screen.m_cst.button_search"/></button>&nbsp;
		    				<button id="ActReset1" onclick="submitForm('basic reset','','');"><bean:message key="screen.m_cst.button_reset"/></button>&nbsp;
		    				<logic:equal value="2" name="_m_cstForm" property="accessType">
		    					<button id="ActNewCorporateCustomer1" onclick="submitForm('new corporate customer','','');" style="width: 180px"><bean:message key="screen.m_cst_s.button_new_corporate_cust"/></button>
		    					<button id="ActNewConsumerCustomer1" onclick="submitForm('new consumer customer','','');" style="width: 180px"><bean:message key="screen.m_cst_s.button_new_consumer_cust"/></button>
		    				</logic:equal>
		    				<c:if test="${_m_cstForm.displayExport == '1'}">
		    				  <button id="ActExport" onclick="submitForm('export_basic','','','','<bean:message key="error.ERR1SC052"/>');"><bean:message key="screen.m_cst_s.button_export"/></button>
		    				</c:if>
                            <c:if test="${_m_cstForm.displayExport == '0'}">
                              <button id="ActExport" onclick="submitForm('export_basic','','','','<bean:message key="error.ERR1SC052"/>');" disabled="disabled"><bean:message key="screen.m_cst_s.button_export"/></button>
                            </c:if>
		    			</td>
		    		</tr>
		    	</table>    	
    	<table class="searchResultNo" cellpadding="0" cellspacing="0">
    		<tr>
    			<td>
					<bean:message key="screen.m_cst.label_search_result"/><bean:message key="screen.m_cst.label_colon"/>
					<c:if test="${_m_cstForm.totalCount != -1}">
						<bean:write name="_m_cstForm" property="totalCount"/>
					</c:if>    				
    			</td>
    		</tr>
    	</table>
		<table class="pageLink" cellpadding="0" cellspacing="0">
			<tr>
				<td><bean:message key="screen.m_cst.label_page_link"/>
					<bean:message key="screen.m_cst.label_colon"/>
					<ts:pageLinks
			    		id="curPageLinks"
						action="M_CSTS01DSP.do" 
						name="_m_cstForm" 
						rowProperty="row"
						totalProperty="totalCount" 
						indexProperty="startIndex"
						currentPageIndex="now" 
						totalPageCount="total"
						submit="true"		
						/>
					<logic:present name="curPageLinks">
						<bean:write name="curPageLinks" filter="false"/>
					</logic:present>
				</td>
			</tr>
		</table>
		<table class="resultInfo" cellpadding="0" cellspacing="0">
			<tr>
			     <%-- 
				<td class="header"><input type="checkbox" style="width:35px;"  id="chkCheckExportAll1"  name="chkCheckExportAll1" onclick="checkAll1()"/></td>
				--%>
				<td class="headerLeft" width="5%">&nbsp;<bean:message  key="screen.m_cst.label_number"/></td>
				<td class="headerLeft" width="8%"><bean:message key="screen.m_cst_s.label_customer_id"/></td>
				<td class="headerLeft" width="27%"><bean:message key="screen.m_cst.label_customer_name"/></td>
				<td class="headerLeft" width="15%"><bean:message key="screen.m_cst.label_customer_type"/></td>
				<td class="headerLeft" width="15%"><bean:message key="screen.m_cst_s.label_peoplesoft_acc_no"/></td>
				<td class="headerLeft" width="15%"><bean:message key="screen.m_cst.label_co_reg_no"/></td>
				<td class="headerLeft" width="15%"><bean:message key="screen.m_cst.label_billing_country"/></td>
			</tr>
			<%--
			 <logic:present name="_m_cstForm" property="chkCheckExport1">
				<logic:iterate id="chk"  name="_m_cstForm" property="chkCheckExport1">
					<input type="hidden" name="chkCheckExport1" value="${chk}" checked="checked" />
				</logic:iterate>
			 </logic:present>
			--%>
			<logic:iterate id="resultBean" name="_m_cstForm" property="cusBeans">
				<tr>
				    <%--
					<td class="defaultText">
						<c:choose>
							<c:when test="${resultBean.checked}">
								<input type="checkbox" checked="checked" style="width:35px;" name="chkCheckExport1" id="chkCheckExport1" value="<bean:write  name="resultBean" property="id_cust"/>" />
							</c:when>
							<c:otherwise>
								<input type="checkbox" style="width:35px;" name="chkCheckExport1" id="chkCheckExport1" value="<bean:write  name="resultBean" property="id_cust"/>" />
							</c:otherwise>
						</c:choose>
					</td>			
					--%>
					<td class="defaultNo"><bean:write  name="resultBean" property="index"/></td>
					<td class="defaultText"><bean:write name="resultBean" property="id_cust"/></td>
					<!-- <td class="defaultText">						
						<bean:define id="id" name="resultBean" property="id_cust"></bean:define>						
						<bean:write name="resultBean" property="cust_name"/>
					</td>
					--> 					
					<td class="defaultText">						
						<bean:define id="id" name="resultBean" property="id_cust"></bean:define>						
						<a href="#" onclick="submitForm('view','READONLY','<%=id %>','<bean:write name="resultBean" property="customerType"/>', '<%=request.getContextPath()%>');"><bean:write name="resultBean" property="cust_name"/></a>
					</td> 	
					<td class="defaultText"><t:writeCodeValue codeList="LIST_CUSTOMERTYPE" key="${resultBean.customerType}"/></td>				
					<td class="defaultText"><bean:write name="resultBean" property="cust_acc_no"/></td>
					<td class="defaultText"><bean:write name="resultBean" property="co_regno"/></td>
					<td class="defaultText">
						<logic:notEmpty name="resultBean" property="country">
							<t:writeCodeValue name="resultBean" property="country" codeList="LIST_COUNTRY"></t:writeCodeValue>
						</logic:notEmpty>
					</td>
				</tr>		
			</logic:iterate>
		</table>
		    </div>
		    
				
			<div id="tabAdvancedSearch" class="tabcontent">
				<!-- Advanced Search -->
				<div style="border:1px solid gray; width:100%; margin-bottom: 1em; padding: 10px">
		    	<table class="information" cellpadding="0" cellspacing="0">
		    		<tr>
		    			<td align="right" ><bean:message key="screen.m_cst.label_customer_name"/><bean:message key="screen.m_cst.label_colon"/></td>
		    			<td align="left"><html:text styleId="tbxCustomerName" name="_m_cstForm" property="customerName" /></td>
		    			<td align="right" width="30%"><bean:message key="screen.m_cst.label_co_reg_no"/><bean:message key="screen.m_cst.label_colon"/></td>
		    			<td align="left"><html:text styleId="tbxCo.Reg.No" name="_m_cstForm" property="coRegNo"/></td>
		    		</tr>		    		
		    		<tr>
		    			<td align="right" width="30%"><bean:message key="screen.m_cst_s.label_peoplesoft_acc_no"/><bean:message key="screen.m_cst.label_colon"/></td>
		    			<td align="left" width="20%"><html:text styleId="tbxCustomerAccNo" name="_m_cstForm" property="customerAccountNo"/></td>
		    			<td align="right" ><bean:message key="screen.m_cst_s.label_customer_id2"/><bean:message key="screen.m_cst.label_colon"/></td>
		    			<td align="left"><html:text styleId="tbxCustomerID" name="_m_cstForm" property="id_cust"/></td>
		    		</tr>
		    		<tr>
		    			<td align="right" ><bean:message key="screen.m_cst_s.label_address"/><bean:message key="screen.m_cst.label_colon"/></td>
		    			<td align="left"><html:text styleId="tbxAddress" name="_m_cstForm" property="address"/></td>
		    			<td align="right" ><bean:message key="screen.m_cst_s.label_email_address"/><bean:message key="screen.m_cst.label_colon"/></td>
		    			<td align="left"><html:text styleId="tbxE-mailAddress" name="_m_cstForm" property="emailAddress"/></td>
		    		</tr>
		    		<tr>
		    			<td align="right" ><bean:message key="screen.m_cst_s.label_zip_code"/><bean:message key="screen.m_cst.label_colon"/></td>
		    			<td align="left"><html:text styleId="tbxZipCode" name="_m_cstForm" property="zipCode"/></td>
		    			<td align="right" ><bean:message key="screen.m_cst_s.label_contact_no"/><bean:message key="screen.m_cst.label_colon"/></td>
		    			<td align="left"><html:text styleId="tbxContactNo." name="_m_cstForm" property="contactNo"/></td>
		    		</tr>
		    		<tr>
		    			<td align="right" width="30%"><bean:message key="screen.m_cst_s.label_billing_country"/><bean:message key="screen.m_cst.label_colon"/></td>		    			
		    			<td>
				   			<logic:present name="_m_cstForm" property="billingCountry">
				   				<bean:define id="rselected" name="_m_cstForm" property="billingCountry" />	
				   			</logic:present>
				   			<logic:notPresent name="_m_cstForm" property="billingCountry">
				   				<bean:define id="rselected" value=""/>
				   			</logic:notPresent>
				   			<html:select styleId="tbxBillingCountry1" name="_m_cstForm" property="billingCountry" value="${rselected}" >
				   				<option value=""><bean:message key="screen.m_cst.listBox.default"/></option>						
								<html:options collection="LIST_COUNTRY" property="id" labelProperty="name"/>
							</html:select>						
						</td>		    			
		    			<td align="right" ><bean:message key="screen.m_cst_s.label_contact_person"/><bean:message key="screen.m_cst.label_colon"/></td>
		    			<td align="left"><html:text styleId="tbxContactPerson" name="_m_cstForm" property="contactPerson"/></td>
		    		</tr>
                    <tr>
                        <td align="right" ><bean:message key="screen.m_cst_s.label_account_manager"/><bean:message key="screen.m_cst.label_colon"/></td>
                        <td align="left">
                            <logic:present name="_m_cstForm" property="accountManager">
                                <bean:define id="rselected" name="_m_cstForm" property="accountManager" />  
                            </logic:present>
                            <%-- <logic:notPresent name="_m_cstForm" property="accountManager">
                                <bean:define id="rselected" value=""/>
                            </logic:notPresent>
                            <html:select styleId="lstAccountManager" name="_m_cstForm" property="accountManager" value="${rselected}" >
                                <option value=""><bean:message key="screen.m_cst.listBox.default"/></option>
                                 <c:forEach items="${_m_cstForm.accountManagerList}" var="item">
                                        <html:option value="${item.ACC_MGR_ID}" >${item.ACC_MGR_NAME}</html:option>
                                </c:forEach>
                                
                                <html:optionsCollection name="_m_cstForm" property="accountManagerList" label="ACC_MGR_NAME" value="ACC_MGR_ID"/>
                               
                            </html:select> --%>
                            <input type="text" size="25"
							   name="accountManager"
							   class="expand" 
							   id="account" 
							   autocomplete="off"
							   value="${_m_cstForm['accountManager']}"
							   maxlength="50" />
							<script>							
							var obj1 = actb(document.getElementById("account"),customarray,"<bean:write name='_m_cstForm' property='accountManagerString'/>");
							</script>  
                        </td>
                        <td align="right" ></td>
                        <td align="left"></td>
                    </tr>
		    	</table>
		    	</div>
		    	<table class="buttonGroup" cellpadding="0" cellspacing="0">
		    		<tr>
		    			<td>
		    				<button id="ActSearch" onclick="submitForm('advanced search','','');"><bean:message key="screen.m_cst.button_search"/></button>&nbsp;
		    				<button id="ActReset" onclick="submitForm('advanced reset','','');"><bean:message key="screen.m_cst.button_reset"/></button>&nbsp;
		    				<logic:equal value="2" name="_m_cstForm" property="accessType">		    				
		    					<button id="ActNewCorporateCustomer" onclick="submitForm('new corporate customer','NEWMODE','');" style="width: 180px"><bean:message key="screen.m_cst_s.button_new_corporate_cust"/></button>
		    					<button id="ActNewConsumerCustomer" onclick="submitForm('new consumer customer','NEWMODE','');" style="width: 180px"><bean:message key="screen.m_cst_s.button_new_consumer_cust"/></button>
		    				</logic:equal>
                            <c:if test="${_m_cstForm.displayExport == '1'}">
		    				<button id="ActExport" onclick="submitForm('export_advanced','','','','<bean:message key="error.ERR1SC052"/>');"><bean:message key="screen.m_cst_s.button_export"/></button>
		    				</c:if>
                            <c:if test="${_m_cstForm.displayExport == '0'}">
                            <button id="ActExport" onclick="submitForm('export_advanced','','','','<bean:message key="error.ERR1SC052"/>');" disabled="disabled"><bean:message key="screen.m_cst_s.button_export"/></button>
                            </c:if>
		    			</td>
		    		</tr>
		    	</table>	
    	<table class="searchResultNo" cellpadding="0" cellspacing="0">
    		<tr>
    			<td>
					<bean:message key="screen.m_cst.label_search_result"/><bean:message key="screen.m_cst.label_colon"/>
					<c:if test="${_m_cstForm.totalCount != -1}">
						<bean:write name="_m_cstForm" property="totalCount"/>
					</c:if>    				
    			</td>
    		</tr>
    	</table>
		<table class="pageLink" cellpadding="0" cellspacing="0">
			<tr>
				<td><bean:message key="screen.m_cst.label_page_link"/>
					<bean:message key="screen.m_cst.label_colon"/>
					<ts:pageLinks 
			    		id="curPageLinks"
						action="M_CSTS01DSP.do" 
						name="_m_cstForm" 
						rowProperty="row"
						totalProperty="totalCount" 
						indexProperty="startIndex"
						currentPageIndex="now" 
						totalPageCount="total"
						submit="true"		
						/>
					<logic:present name="curPageLinks">
						<bean:write name="curPageLinks" filter="false"/>
					</logic:present>
				</td>
			</tr>
		</table>
		<table class="resultInfo" cellpadding="0" cellspacing="0">
			<tr>
			     <%--
				<td class="header"><input type="checkbox" style="width:35px;" id="chkCheckExportAll"  name="chkCheckExportAll" onclick="checkAll()"/></td>
				--%>
				<td class="headerLeft" width="5%">&nbsp;<bean:message  key="screen.m_cst.label_number"/></td>
				<td class="headerLeft" width="8%"><bean:message key="screen.m_cst_s.label_customer_id"/></td>
				<td class="headerLeft" width="25%"><bean:message key="screen.m_cst.label_customer_name"/></td>
				<td class="headerLeft" width="17%"><bean:message key="screen.m_cst_s.label_peoplesoft_acc_no"/></td>
				<td class="headerLeft" width="15%"><bean:message key="screen.m_cst.label_co_reg_no"/></td>
				<td class="headerLeft" width="15%"><bean:message key="screen.m_cst.label_billing_country"/></td>
				<td class="headerLeft" width="15%"><bean:message key="screen.m_cst_s.label_account_manager"/></td>
			</tr>
			
			<%--
			<logic:present name="_m_cstForm" property="chkCheckExport">
				<logic:iterate id="chk"  name="_m_cstForm" property="chkCheckExport">
					<input type="hidden" name="chkCheckExport" value="${chk}" checked="checked"/>
				</logic:iterate>
			</logic:present> 
			 --%>
			<logic:iterate id="resultBean" name="_m_cstForm" property="cusBeans">
				<tr>
				<%--
				<td class="defaultText">
					  	<c:choose>
							<c:when test="${resultBean.checked}">
								<input type="checkbox" checked="checked" style="width:35px;" name="chkCheckExport" id="chkCheckExport" value="<bean:write  name="resultBean" property="id_cust"/>" />
							</c:when>
							<c:otherwise>
								<input type="checkbox" style="width:35px;" name="chkCheckExport" id="chkCheckExport" value="<bean:write  name="resultBean" property="id_cust"/>" />
							</c:otherwise>
						</c:choose>
						
				<!--  	<input type="checkbox" name="chkCheckExport" style="width:35px;"  id="chkCheckExport" value="<bean:write  name="resultBean" property="id_cust"/>" />  -->
					</td>
					--%>
					<td class="defaultNo">
						<bean:write  name="resultBean" property="index"/>
					</td>
					<td class="defaultText">
						<bean:write name="resultBean" property="id_cust"/>
						<input type="hidden" name="cust_acc_nos" value="<bean:write name="resultBean" property="cust_acc_no"/>"/>
					</td>
					<td class="defaultText">						 						
						<bean:define id="id" name="resultBean" property="id_cust"></bean:define>						
						<!-- <bean:write name="resultBean" property="cust_name"/>-->						
						<bean:define id="id" name="resultBean" property="id_cust" ></bean:define>
						<a href="#" onclick="submitForm('view','READONLY','<%=id %>','<bean:write name="resultBean" property="customerType"/>','<%=request.getContextPath()%>' );"><bean:write name="resultBean" property="cust_name"/></a>
						<input type="hidden" name="cust_names" value="<bean:write name="resultBean" property="cust_name"/>"/>						 
					</td>
					<td class="defaultText">
						<bean:write name="resultBean" property="cust_acc_no"/>
					</td>
					<td class="defaultText">
						<bean:write name="resultBean" property="co_regno"/>
						<input type="hidden" name="co_regnos" value="<bean:write name="resultBean" property="co_regno"/>"/>
					</td>
					<td class="defaultText">
						<logic:notEmpty name="resultBean" property="country">
							<t:writeCodeValue name="resultBean" property="country" codeList="LIST_COUNTRY"></t:writeCodeValue>
						</logic:notEmpty>
					</td>
					<td class="defaultText">
                        <bean:write name="resultBean" property="gbcAccountManager"/>
                    </td>
				</tr>		
			</logic:iterate>
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
		<ts:messages id="message" message="true">
			<bean:write name="message" />
		</ts:messages>
	</div>
	</body>
</html:html>

