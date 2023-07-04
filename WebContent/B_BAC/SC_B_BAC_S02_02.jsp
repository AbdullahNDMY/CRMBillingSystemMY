<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="/WEB-INF/bs" prefix="bs" %>
<%@page import="nttdm.bsys.common.fw.BillingSystemUserValueObject"%>
<%@page import="nttdm.bsys.common.util.CommonUtils"%> 

<html>
<head>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
	<link href="${pageContext.request.contextPath}/B_BAC/css/b_bac.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   	<title><bean:message key="screen.b_bac_s01.title"/></title>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$('#cboAdrType').change(function(){
				$('#newForm').attr('action', 'RP_B_BAC_S02_01_03BL.do');
				$('#newForm').submit();
				$('#newForm').attr('action', 'SC_B_BAC_S02_01DSP.do');
			});
			$('#cboAttn').change(function(){
				$('#newForm').attr('action', 'RP_B_BAC_S02_01_03BL.do');
				$('#newForm').submit();
				$('#newForm').attr('action', 'SC_B_BAC_S02_01DSP.do');
			});
			$('#cboPaymentMethod').change(function(){
				$('#newForm').attr('action', 'RP_B_BAC_S02_01_03BL.do');
				$('#newForm').submit();
				$('#newForm').attr('action', 'SC_B_BAC_S02_01DSP.do');
			});
		});
		function clickExit(){
			if(this.opener != undefined){
				window.close();
				return false;
			}else{
				document.getElementById("cboCustomerName").value="";
				var fromPage = document.getElementById("fromPage").value;
				var idCustPlan = document.getElementById("idCustPlan").value;
				if(fromPage=="B_CPM_S02V") {
					$('#newForm').attr("action", "<%=request.getContextPath()%>/B_CPM/B_CPM_S01DSP.do?event=forward_viewCustomerPlan&customerPlan.idCustPlan="+idCustPlan);
					$('#newForm').submit();
				} else if(fromPage=="B_BIL_S01") {
					$('#newForm').attr("action", "<%=request.getContextPath()%>/B_BIL/RP_B_BIL_S01_01BL.do");
					$('#newForm').submit();
				} else if(fromPage=="B_CSB_S01") {
					$('#newForm').attr("action", "<%=request.getContextPath()%>/B_CSB/B_CSBR01BLogic.do");
					$('#newForm').submit();
				} else if(fromPage=="B_BIFS02_03"||fromPage=="B_BIFS03_04"){
					$('#newForm').attr("action","<%=request.getContextPath()%>/BIF/B_BIFS01_01BL.do?idRef="+$("#idRef").val());
					$('#newForm').submit();
				} else {
					$('#newForm').attr('action', 'RP_B_BAC_S01_01BL.do');
					$('#newForm').submit();
					$('#newForm').attr('action', 'SC_B_BAC_S02_01DSP.do');
				}
			}
		}
        function initInfo(){
            // pop up window not allow to display action button.
            var btnGenrateBil = document.getElementById("forward_generateBilling");
            var btnEdit = document.getElementById("forward_edit");
            var btnBRefEdit = document.getElementById("forward_BRefedit");
            var btnGenerateStat = document.getElementById("forward_generateStatement");
            var btnUpdateTotalAmtDue = document.getElementById("forward_updateTotalAmtDue");
            if (btnGenrateBil != null) {
                if(this.opener != undefined){
                    btnGenrateBil.style.display = "none";
                }else{
                    btnGenrateBil.style.display = "inline";
                }
            }
            if (btnEdit != null) {
                if(this.opener != undefined){
                    btnEdit.style.display = "none";
                }else{
                    btnEdit.style.display = "inline";
                }
            }
            if (btnBRefEdit != null) {
                if(this.opener != undefined){
                	btnBRefEdit.style.display = "none";
                }else{
                	btnBRefEdit.style.display = "inline";
                }
            }
            if (btnGenerateStat != null) {
                if(this.opener != undefined){
                    btnGenerateStat.style.display = "none";
                }else{
                    btnGenerateStat.style.display = "inline";
                }
            }
            if (btnUpdateTotalAmtDue != null) {
                if(this.opener != undefined){
                    btnUpdateTotalAmtDue.style.display = "none";
                }else{
                    btnUpdateTotalAmtDue.style.display = "inline";
                }
            }
        }
		
		function clickSetType(type) {
			document.getElementById("clickBtnTypeFlg").value = type;
		}
		function generateBillingClick(type) {
			var msg = new messageBox("");
			var url = "<%=request.getContextPath()%>/COMMON/COMMONCurrencyBL.do?r=" + Math.random();
			var result = msg.POPCUR(url);
			if(result == msg.YES_CONFIRM) {
				document.getElementById("clickBtnTypeFlg").value = type;
				submitForm("forward_generateBilling");
			}
		}
		//Function use submit form
		function submitForm(event) {
			$("input[name='event']").remove();
			var event = '<input type="hidden" name="event" value="' + event + '"/>';
			$('form').append(event);
			$('form').submit();
		}
		function initPopupInfo() {
			var popupInfo = document.getElementById("popupInfo").value;
			if(popupInfo!=""&&popupInfo!=null) {
				var msg = new messageBox("");
				var isConfirm = (msg.POPCFMStatement(popupInfo) == msg.YES_CONFIRM);
				if (isConfirm) {
					document.getElementById("popupClickYesFlg").value = "1";
					document.getElementById("forward_generateStatement").click();
				}
			}
		}
		//open cpm05
        function openB_CPM_View(url) {
            var width = window.screen.width*90/100;
            var height = window.screen.height*80/100;
            var left = Number((screen.availWidth/2) - (width/2));
            var top = Number((screen.availHeight/2) - (height/2));
            var offsetFeatures = "width=" + width + ",height=" + height +
                                 ",left=" + left + ",top=" + top +
                                 "screenX=" + left + ",screenY=" + top;
            var strFeatures= "toolbar=no, status=no, menubar=no,location=no,scrollbars=yes, resizable=yes" + "," + offsetFeatures;      
            var newwindow = window.open(url, null, strFeatures);
            if (window.focus) { newwindow.focus(); }
        }
		//open edit
        function openEditPage(url) {
            
            var idBillAccount = $("input[type='hidden'][name='idBillAccount']").val();
            var idCustPlan = $("input[type='hidden'][name='idCustPlan']").val();
            var editFlg = $("input[type='hidden'][name='editFlg']").val();
            var fromPage = $("input[type='hidden'][name='fromPage']").val();
            var checkBoxDisPlayFlg = $("#checkBoxDisPlayFlg").val();
            url = url+"?idBillAccount="+idBillAccount+"&idCustPlan="+idCustPlan+"&editFlg="+editFlg+"&fromPage="+fromPage
            		+"&checkBoxDisPlayFlg="+checkBoxDisPlayFlg;

            var width = window.screen.width*80/100;
            var height = window.screen.height*60/100;
            var left = Number((screen.availWidth/2) - (width/2));
            var top = Number((screen.availHeight/2) - (height/2));
            var offsetFeatures = "width=" + width + ",height=" + height +
                                 ",left=" + left + ",top=" + top +
                                 "screenX=" + left + ",screenY=" + top;
            var strFeatures= "toolbar=no, status=no, menubar=no,location=no,scrollbars=yes, resizable=yes" + "," + offsetFeatures;      
            var newwindow = window.open(url, null, strFeatures);
            if (window.focus) { newwindow.focus(); }
        }
		
      	//open edit Billing Reference
        function openEditBrefPage(url) {
            
            var idBillAccount = $("input[type='hidden'][name='idBillAccount']").val();
            var idCustPlan = $("input[type='hidden'][name='idCustPlan']").val();
            var editFlg = $("input[type='hidden'][name='editFlg']").val();
            var fromPage = $("input[type='hidden'][name='fromPage']").val();
            var cboPaymentMethod = $("#cboPaymentMethod").val();
            url = url+"?idBillAccount="+idBillAccount+"&idCustPlan="+idCustPlan+"&editFlg="+editFlg+"&fromPage="+fromPage+"&cboPaymentMethod="+cboPaymentMethod;

            var width = window.screen.width*50/100;
            var height = window.screen.height*50/100;
            var left = Number((screen.availWidth/2) - (width/2));
            var top = Number((screen.availHeight/2) - (height/2));
            var offsetFeatures = "width=" + width + ",height=" + height +
                                 ",left=" + left + ",top=" + top +
                                 "screenX=" + left + ",screenY=" + top;
            var strFeatures= "toolbar=no, status=no, menubar=no,location=no,scrollbars=yes, resizable=yes" + "," + offsetFeatures;      
            var newwindow = window.open(url, null, strFeatures);
            if (window.focus) { newwindow.focus(); }
        }
      
		function doRefund(){
		    var idBillAccount = $("input[type='hidden'][name='idBillAccount']").val();
		    var fromPage = $("input[type='hidden'][name='fromPage']").val();
		    var url = "<%=request.getContextPath()%>/B_CSB/B_CSB_S03New.do?idBillAccount="+idBillAccount+"&fromPage="+fromPage;
		    window.location = url;
		}
		function submitLinkForm(bif, bif_type,context, state){
			var ACTION_REF_INFO = '';
			 if(bif_type == 'CN'){
				 var ACTION_REF_INFO = 'BIF/B_BIFS03_01BL.do';
			}else{
				ACTION_REF_INFO = 'BIF/B_BIFS02BL.do';
			} 
			var url = context +'/'+ ACTION_REF_INFO + '?hidAction=' + 'view' +  '&idRef=' + bif +'&pictureId=BAC_S02' +'&Action=ss';
			
			var width = window.screen.width*90/100;
            var height = window.screen.height*80/100;
            var left = Number((screen.availWidth/2) - (width/2));
            var top = Number((screen.availHeight/2) - (height/2));
            var offsetFeatures = "width=" + width + ",height=" + height +
                                 ",left=" + left + ",top=" + top +
                                 "screenX=" + left + ",screenY=" + top;
            var strFeatures= "toolbar=no, status=no, menubar=no,location=no,scrollbars=yes, resizable=yes" + "," + offsetFeatures;      
            var newwindow = window.open(url, null, strFeatures);
            if (window.focus) { newwindow.focus(); }
	
}
		
	</script>
</head>
<body id="b" onload="initMain();initInfo();initPopupInfo();">
<ts:form action="/SC_B_BAC_S02_02DSP" styleId="newForm">
    <%
		BillingSystemUserValueObject uvo = (BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT");
	 	String r_soaAccessRight = CommonUtils.getAccessRight(uvo, "R-SOA");
	 	String gernateBillingAccessRight = CommonUtils.getAccessRight(uvo, "B-BAC-GB");
	%>
	<bean:define id="r_soaAccessRightBean" value="<%=r_soaAccessRight %>"/>
	<bean:define id="gernateBillingAccessRightBean" value="<%=gernateBillingAccessRight %>"/>
	<html:hidden property="fromPage" name="_b_bacForm" styleId="fromPage"/>
	<html:hidden property="idRef" name="_b_bacForm" styleId="idRef"/>
	<input type="hidden" value="${_b_bacForm.map.headerInfo.PAYMENT_METHOD}" id="cboPaymentMethod"/>
	<input type="hidden" value="${_b_bacForm.map.checkBoxDisPlayFlg}" id="checkBoxDisPlayFlg"/>
	<h1 class="title"><bean:message key="screen.b_bac_s01.title"/></h1>
		<div>
			<div>
				<fieldset class="noBorder fieldsetPadding">
					<table cellpadding="0" cellspacing="0" width="100%">
						<col width="15%"/><col width="35%"/><col width="15%"/><col width="35%"/>
						<tr>
							<td>
								<bean:message key="screen.b_bac.customerName"/>
							</td>
							<td>
								<bean:message key="screen.b_bac.colon"/>
								${_b_bacForm.map.custInfo.CUST_NAME}
								<html:hidden property="cboCustomerName" value="${_b_bacForm.map.custInfo.ID_CUST}"/>
							</td>
							<td nowrap="nowrap">
								<bean:message key="screen.b_bac.billingAccountNo"/>
							</td>
							<td>
								<bean:message key="screen.b_bac.colon"/>
								${_b_bacForm.map.headerInfo.ID_BILL_ACCOUNT}
							</td>
						</tr>
						<tr>
							<td nowrap="nowrap">
                                <bean:message key="screen.b_bac_s02.customerId"/>
                            </td>
                            <td>
                                <bean:message key="screen.b_bac.colon"/>
                                ${_b_bacForm.map.custInfo.ID_CUST}
                            </td>
						</tr>	
					</table>
				</fieldset>
			</div>
			<div>
				    <fieldset class="fieldsetPadding fieldsetBorder">
                    <legend>
                        <bean:message key="screen.b_bac.accountInfo"/>
                    </legend>
                    <table cellpadding="0" cellspacing="0" width="100%">
                        <col width="15%"/><col width="35%"/><col width="50%"/>
                        <tr>
                            <td nowrap="nowrap" valign="top"><bean:message key="screen.b_bac_s02.documentDeliever"/></td>
                            <td valign="top">
                                <bean:message key="screen.b_bac.colon"/>&nbsp;
                                <html:checkbox value="1" name="_b_bacForm" disabled="true" property="headerInfo.IS_SINGPOST">
                                </html:checkbox>
                                <!-- #191 Start -->
                                <%-- <bean:message key="screen.b_bac_s02.singPost"/> --%>
                                ${_b_bacForm.map.headerInfo.singPostValue}
                                <!-- #191 End --
                                <!-- Email -->
                                <br/>&nbsp;&nbsp;&nbsp;&nbsp;
                                <html:checkbox value="1" name="_b_bacForm" disabled="true" property="headerInfo.DELIVERY_EMAIL">
                                </html:checkbox>
                                <bean:message key="screen.b_bac_s02.email"/>
                                <!-- Delivery -->
                                <br/>&nbsp;&nbsp;&nbsp;&nbsp;
                                <html:radio value="3" name="_b_bacForm" disabled="true" property="headerInfo.DELIVERY">
                                </html:radio>
                                <bean:message key="screen.b_bac_s02.none"/>
                                <html:radio value="1" name="_b_bacForm" disabled="true" property="headerInfo.DELIVERY">
                                </html:radio>
                                <bean:message key="screen.b_bac_s02.post"/>
                                <html:radio value="2" name="_b_bacForm" disabled="true" property="headerInfo.DELIVERY">
                                </html:radio>
                                <bean:message key="screen.b_bac_s02.courier"/>
                                <html:radio value="4" name="_b_bacForm" disabled="true" property="headerInfo.DELIVERY">
                                </html:radio>
                                <bean:message key="screen.b_bac_s02.byhand"/>
                            </td>
                            <td rowspan="6">
				                 <fieldset class="fieldsetPadding noBorder">
				                    <table class="inputInfo" cellpadding="0" cellspacing="0" width="100%">
				                        <col width="20%" align="right"/><col width="80%"/>
				                        <tr>
				                            <td nowrap="nowrap">
				                                <t:writeCodeValue codeList="LIST_ADDRESS" key="${_b_bacForm.map.headerInfo.CUST_ADR_TYPE }"></t:writeCodeValue>
				                                <bean:message key="screen.b_bac.colon"/>
				                            </td>
				                            <td>
				                                ${_b_bacForm.map.addressInfo.address1}
				                            </td>
				                        </tr>
				                        <tr>
				                            <td>
				                                &nbsp;
				                            </td>
				                            <td>
				                                ${_b_bacForm.map.addressInfo.address2}
				                            </td>
				                        </tr>
				                        <tr>
				                            <td>
				                                &nbsp;
				                            </td>
				                            <td>
				                                ${_b_bacForm.map.addressInfo.address3}
				                            </td>
				                        </tr>
				                        <tr>
				                            <td>
				                                &nbsp;
				                            </td>
				                            <td>
				                                ${_b_bacForm.map.addressInfo.address4}
				                            </td>
				                        </tr>
				                        <tr>
				                            <td>
				                                <bean:message key="screen.b_bac.attn"/><bean:message key="screen.b_bac.colon"/>
				                            </td>
				                            <td>
				                                <c:forEach items="${_b_bacForm.map.listContact}" var="item">
				                                    <c:if test="${_b_bacForm.map.headerInfo.CONTACT_TYPE eq item.value}">
				                                        ${item.label}
				                                    </c:if>
				                                </c:forEach>
				                            </td>
				                        </tr>
				                        <tr>
				                            <td>
				                                <bean:message key="screen.b_bac.email_to"/><bean:message key="screen.b_bac.colon"/>
				                            </td>
				                            <td>
				                            	<c:forTokens items="${_b_bacForm.map.addressInfo.email}" delims=";" var="email" varStatus="vs">
													<c:if test="${!vs.last}">
														<c:out value="${email}"/>;<br/>
													</c:if>
													<c:if test="${vs.last}">
														<c:out value="${email}"/>
													</c:if>
												</c:forTokens>
				                            </td>
				                        </tr>
				                        <tr>
				                            <td>
				                                <bean:message key="screen.b_bac.email_cc"/><bean:message key="screen.b_bac.colon"/>
				                            </td>
				                            <td>
				                           		<c:forTokens items="${_b_bacForm.map.addressInfo.email_cc}" delims=";" var="email" varStatus="vs">
													<c:if test="${!vs.last}">
														<c:out value="${email}"/>;<br/>
													</c:if>
													<c:if test="${vs.last}">
														<c:out value="${email}"/>
													</c:if>
												</c:forTokens>
				                            </td>
				                        </tr>
				                    </table>
				                </fieldset>
                            </td>
                        </tr>
                        <tr>
                            <td nowrap="nowrap"><bean:message key="screen.b_bac.paymentMethod"/></td>
                            <td>
                                <bean:message key="screen.b_bac.colon"/>&nbsp;
                                <t:defineCodeList id="LIST_PAYMENT_METHOD" />
                                <c:forEach items="${LIST_PAYMENT_METHOD}" var="item">
                                    <c:if test="${_b_bacForm.map.headerInfo.PAYMENT_METHOD eq item.id}">
                                        ${item.name}
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <td><bean:message key="screen.b_bac.billingCurrency"/></td>
                            <td>
                                <bean:message key="screen.b_bac.colon"/>&nbsp;
                                <t:defineCodeList id="LIST_CURRENCY" />
                                <c:forEach items="${LIST_CURRENCY}" var="item">
                                    <c:if test="${_b_bacForm.map.headerInfo.BILL_CURRENCY eq item.id}">
                                        ${item.name}
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <td nowrap="nowrap"><bean:message key="screen.b_bac.exportCurrency"/></td>
                            <td>
                                <bean:message key="screen.b_bac.colon"/>&nbsp;
                                ${_b_bacForm.map.headerInfo.EXPORT_CURRENCY}
                            </td>
                        </tr>
                        <c:if test="${_b_bacForm.map.fixedForex eq '1'}">
                        <tr>
                            <td><bean:message key="screen.b_bac.fixedForex"/></td>
                            <td>
                                <bean:message key="screen.b_bac.colon"/>&nbsp;
                                ${_b_bacForm.map.headerInfo.FIXED_FOREX}
                            </td>
                        </tr>
                        </c:if>
                        <c:if test="${_b_bacForm.map.fixedForex ne '1'}">
                        <tr>
                            <td>&nbsp;</td>
                            <td>
                            &nbsp;
                            </td>
                        </tr>
                        </c:if>
                        <%-- <tr>
                            <td>&nbsp;</td>
                            <td>
                            &nbsp;
                            </td>
                        </tr> --%>
                        <c:if test="${_b_bacForm.map.taxTypeDisplay eq '1'}">
                        <tr>
                        	<td><bean:message key="screen.b_bac.taxType"/></td>
                            <td>
                                <bean:message key="screen.b_bac.colon"/>&nbsp;
                                <t:defineCodeList id="LIST_TAX_TYPE" />
                                <c:forEach items="${LIST_TAX_TYPE}" var="item">
                                    <c:if test="${_b_bacForm.map.headerInfo.TAX_TYPE eq item.id}">
                                        ${item.name}
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        </c:if>
                        <tr>
                        	<td colspan="2">
                        		<c:if test="${_b_bacForm.map.checkBoxDisPlayFlg eq '1' 
                        					|| _b_bacForm.map.headerInfo.DIS_PERIOD_AMT eq '1'}">
	                        		<html:checkbox value="1" name="_b_bacForm" disabled="true" property="headerInfo.MULTI_BILL_PERIOD">
	                            	</html:checkbox>
	                            	<bean:message key="screen.b_bac_s02.multi_billing_period"/>
                            	</c:if>
                            </td>
                        </tr>
                        <tr>
                        	<td colspan="2">
                        		<c:if test="${_b_bacForm.map.checkBoxDisPlayFlg eq '1' 
                        					|| _b_bacForm.map.headerInfo.DIS_PERIOD_AMT eq '1'}">
	                        		<html:checkbox value="1" name="_b_bacForm" disabled="true" property="headerInfo.IS_DISPLAY_EXP_AMT">
	                            	</html:checkbox>
	                            	<bean:message key="screen.b_bac_s02.display_amount_at_invoice"/>
                            	</c:if>
                            </td>
                        </tr>
                        <tr>
                            <td align="right" colspan="3">
                                <c:if test="${_b_bacForm.map.accessType eq '2'}">
                                    <!--  <input type="button" value="Generate Billing" onclick="new messageBox().POPALT('OnClick: Proceed to G_BIL_P01.Pass list of D.ID_CUST_PLAN as parameters.')"/>-->
                                    <!-- 
                                    <c:if test="${_b_bacForm.map.isDisplayTerCnBut eq '1'}">
                                        <ts:submit value="Generate Terminate CN" property="forward_generateBilling"></ts:submit>
                                        <input type="button" value="Generate Terminate CN" onclick="new messageBox().POPALT('OnClick: Proceed to G_BIL_P01.Pass list of D.ID_CUST_PLAN as parameters which match condition for terminate CN.')"/>
                                    </c:if>
                                     -->
                                   <button value="Edit" class="button" id="forward_edit" onclick="openEditPage('${pageContext.request.contextPath}/B_BAC/RP_B_BAC_S02_03_01BL.do');">Edit</button>
                               </c:if>
                            </td>
                        </tr>
                    </table>
                </fieldset>
			</div>

		</div>
		<c:if test="${_b_bacForm.map.headerInfo.BAC_BAC_FLAG eq 'BAC'}">
			<div>
				<fieldset class="fieldsetPadding fieldsetBorder">
					<legend>
						<bean:message key="screen.b_bac.totalAmountDue"/>
					</legend>
					<table class="inputInfo" cellpadding="0" cellspacing="0">
						<col width="20%"/>
						<col width="40%"/>
						<col width="40%"/>
						<tr>
							<td>
								<bean:message key="screen.b_bac.totalAmountDue"/>
							</td>
							<td>
								<bean:message key="screen.b_bac.colon"/>&nbsp;
								<fmt:formatNumber value="${_b_bacForm.map.headerInfo.TOTAL_AMT_DUE}" pattern="#,##0.00"/>
							</td>
							<td style="text-align:right;">
								<logic:notEmpty name="_b_bacForm" property="listPlanInfo">
									<c:if test="${_b_bacForm.map.headerInfo.BAC_CALC_FLAG eq '1'}">
									<ts:submit value="Update" styleClass="button" styleId="forward_updateTotalAmtDue" property="forward_updateTotalAmtDue" onclick="clickSetType('updateTotalAmtDue');"></ts:submit>
									</c:if>
								</logic:notEmpty>
								<c:if test="${_b_bacForm.map.accessType eq '2'}">
                                     <c:if test="${_b_bacForm.map.headerInfo.TOTAL_AMT_DUE lt '0'}">
                                        <input type="button" class="button" value="Refund" onclick="doRefund();"/>
                                     </c:if>
                                 </c:if>
							</td>
						</tr>
					</table>
				</fieldset>
			</div>
		</c:if>
		<br/>
		<%-- <c:if test="${_b_bacForm.map.billRefDispCond eq '1'}"> --%>
			<div>
				<fieldset class="fieldsetPadding fieldsetBorder">
						<legend>
							<bean:message key="screen.b_bac.billingRef"/>
						</legend>
						<table class="inputInfo" cellpadding="0" cellspacing="0">
							<col width="10%"/><col width="40%"/>
							<tr>
								<td>
									<bean:message key="screen.b_bac.reference"/>
								</td>
								<td>
									<bean:message key="screen.b_bac.colon"/>&nbsp;
									
									<c:forEach items="${_b_bacForm.map.billRefInfo}" var="item" varStatus="status">
										<c:if test="${not empty item.ID_BIF}">
											${status.index ne 0 ? ';' : ''}
											<c:if test="${_b_bacForm.map.referenceDisplay eq '1'}">
												<a class="hyperLink" onclick="submitLinkForm('${item.ID_BIF}','${item.BIF_TYPE}','<%=request.getContextPath()%>');">
												${item.ID_BIF}
												</a>
											</c:if>
											<c:if test="${_b_bacForm.map.referenceDisplay eq '0'}">
												${item.ID_BIF}
											</c:if>
										</c:if>
									</c:forEach>
								</td>
							</tr>
							<tr>
								<td>
									<bean:message key="screen.b_bac.qcsReference"/>
								</td>
								<td>
									<bean:message key="screen.b_bac.colon"/>&nbsp;
									<c:forEach items="${_b_bacForm.map.billRefInfo}" var="item" varStatus="status">
										<c:if test="${not empty item.ID_QCS}">
											${status.index ne 0 ? ';' : ''}
											${item.ID_QCS}
										</c:if>
									</c:forEach>
								</td>
							</tr>
							<tr>
								<td>
									<bean:message key="screen.b_bac.quoReference"/>
								</td>
								<td>
									<bean:message key="screen.b_bac.colon"/>&nbsp;
									<c:forEach items="${_b_bacForm.map.billRefInfo}" var="item" varStatus="status">
										<c:if test="${not empty item.ID_QUO}">
											${status.index ne 0 ? ';' : ''}
											${item.ID_QUO}
										</c:if>
									</c:forEach>
								</td>
							</tr>
							<tr>
								<td>
									<bean:message key="screen.b_bac.customerPO"/>
								</td>
								<td>
									<bean:message key="screen.b_bac.colon"/>&nbsp;
									<c:forEach items="${_b_bacForm.map.billRefInfo}" var="item" varStatus="status">
										<c:if test="${not empty item.CUST_PO}">
											${status.index ne 0 ? ';' : ''}
											${item.CUST_PO}
										</c:if>
									</c:forEach>
								</td>
							</tr>
							<tr>
								<td>
									<bean:message key="screen.b_bac.acManager"/>
								</td>
								<td>
									<bean:message key="screen.b_bac.colon"/>&nbsp;
									<c:forEach items="${_b_bacForm.map.billRefInfo}" var="item" varStatus="status">
										<c:if test="${not empty item.AC_MANAGER}">
											${status.index ne 0 ? ';' : ''}
											${item.AC_MANAGER}
										</c:if>
									</c:forEach>
								</td>
							</tr>
							<%-- <tr>
								<td>
									<bean:message key="screen.b_bac.jobNo"/>
								</td>
								<td>
									<bean:message key="screen.b_bac.colon"/>&nbsp;
									<c:forEach items="${_b_bacForm.map.billRefInfo}" var="item" varStatus="status">
										<c:if test="${not empty item.JOB_NO}">
											${status.index ne 0 ? ';' : ''}
											${item.JOB_NO}
										</c:if>
									</c:forEach>
								</td>
							</tr> --%>
							<tr>
								<td>
									<bean:message key="screen.b_bac.term"/>
								</td>
								<td>
									<bean:message key="screen.b_bac.colon"/>&nbsp;
									<c:forEach items="${_b_bacForm.map.billRefInfo}" var="item" varStatus="status">
										<c:if test="${not empty item.TERM}">
											${status.index ne 0 ? ';' : ''}
											${item.TERM}
										</c:if>
									</c:forEach>
								</td>
							</tr>
							<tr>
                            <td align="right" colspan="3">
                                <c:if test="${_b_bacForm.map.accessType eq '2'}">
                                   <button value="Edit" class="button" id="forward_BRefedit" onclick="openEditBrefPage('${pageContext.request.contextPath}/B_BAC/RP_B_BAC_S02_06_01BL.do');">Edit</button>
                               </c:if>
                            </td>
                        </tr>
						</table>
				</fieldset>
			</div>
		<%-- </c:if> --%>
		<br/>
		<div class="section">
		<table class="resultInfo" cellpadding="0" cellspacing="0" width="100%">
            <col width="8%"/>
            <col width="30%"/>
            <col width="12%"/>
            <col width="10%"/>
            <col width="10%"/>
            <col width="10%"/>
            <col width="10%"/>
            <col width="15%"/>
			<tr>
				<td class="header" style="background-color:#ffccff"><bean:message key="screen.b_bac.subId"/></td>
				<td class="header" style="background-color:#ffccff"><bean:message key="screen.b_bac.planDescription"/></td>
				<td class="header" style="background-color:#ffccff"><bean:message key="screen.b_bac_s02.amount"/></td>
				<!-- V3.06 #268 [T16][G-BIL-P01] Generate Billing - Post paid issue fixing CT 20170720 -->
				<td class="header" style="background-color:#ffccff"><bean:message key="screen.b_bac_s02.billOption"/></td>
				<!-- V3.06 #268 [T16][G-BIL-P01] Generate Billing - Post paid issue fixing CT 20170720 -->
				<td class="header" style="background-color:#ffccff"><bean:message key="screen.b_bac_s02.serviceStart"/></td>
				<td class="header" style="background-color:#ffccff"><bean:message key="screen.b_bac.serviceEnd"/></td>
				<td class="header" style="background-color:#ffccff"><bean:message key="screen.b_bac.serviceStatus"/></td>
                <td class="header" style="background-color:#ffccff"><bean:message key="screen.b_bac.billingStatus"/></td>
				<td class="header" style="background-color:#ffccff"><bean:message key="screen.b_bac.lastBillingPeriod"/></td>
			</tr>
			<logic:notEmpty name="_b_bacForm" property="listPlanInfo">
			<c:forEach items="${_b_bacForm.map.listPlanInfo}" var="item" varStatus="status">
				<tr>
				<bean:define id="borderClass" value="${empty item.ID_SUB_INFO?'textLineNo': 'firstTrTopBorder'}"></bean:define>
			    <td class="<%=borderClass %>" align="left">
				    <logic:notEmpty name="item" property="ID_SUB_INFO">
					    <a href="#" onclick="openB_CPM_View('${pageContext.request.contextPath}/B_CPM/B_CPM_S05InitBL.do?customerPlan.idCustPlan=${item.ID_CUST_PLAN}&customerPlan.fromScreen=Billing&customerPlan.billType=BAC')">
		                    ${item.ID_SUB_INFO}
		                </a>
				    </logic:notEmpty>
			    </td>
	            <td class="<%=borderClass %>" align="left" style="word-wrap: break-word;white-space : normal">
                    ${fn:substring(item.BILL_DESC_DISPLAY, 0, _b_bacForm.map.bss.descLength)}
                    <c:if test="${fn:length(item.BILL_DESC_DISPLAY) gt _b_bacForm.map.bss.descLength}">
                        <bean:message key="screen.b_bac.etc"/>
                    </c:if>
                </td>
				<td class="<%=borderClass %>" align="left" style="padding-right:5px">
				    <fmt:formatNumber value="${item.TOTAL_AMOUNT}" pattern="#,##0.00"></fmt:formatNumber>
				</td>
				 <td class="<%=borderClass %>" align="left" style="word-wrap: break-word;white-space : normal">
                    <c:if test="${item.BILL_OPTION eq 1}">
                        <bean:message key="screen.b_bac_s02.billOption.prepaid"/>
                    </c:if>
                    <c:if test="${item.BILL_OPTION eq 2}">
                        <bean:message key="screen.b_bac_s02.billOption.postpaid"/>
                    </c:if>
                </td>
				<td class="<%=borderClass %>" align="left" style="padding-right:5px">
				    <fmt:formatDate value="${item.SVC_START}" pattern="dd/MM/yyyy"/>
				</td>
                <td class="<%=borderClass %>" align="left" style="padding-right:5px">
                   <c:choose>
                        <c:when test="${(empty item.SVC_END) and (item.AUTO_RENEW eq 0)}">
                            <bean:message key="screen.b_bac._"/>
                        </c:when>
                        <c:when test="${(empty item.SVC_END) and (item.AUTO_RENEW eq 1)}">
                            <bean:message key="screen.b_bac.autoRenew"/>
                        </c:when>
                        <c:otherwise>
                            <fmt:formatDate value="${item.SVC_END}" pattern="dd/MM/yyyy"/>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td class="<%=borderClass %>" align="left">
                      <c:choose>
                          <c:when test="${item.SERVICES_STATUS eq 'PS1'}">
                              <bean:message key="screen.b_bac.draft"/>
                          </c:when>
                          <c:when test="${item.SERVICES_STATUS eq 'PS2'}">
                              <bean:message key="screen.b_bac.approval"/>
                          </c:when>
                          <c:when test="${item.SERVICES_STATUS eq 'PS3'}">
                              <bean:message key="screen.b_bac.active"/>
                          </c:when>
                          <c:when test="${item.SERVICES_STATUS eq 'PS4'}">
                              <bean:message key="screen.b_bac.inactive"/>
                          </c:when>
                          <c:when test="${item.SERVICES_STATUS eq 'PS5'}">
                              <bean:message key="screen.b_bac.oneTime"/>
                          </c:when>
                          <c:when test="${item.SERVICES_STATUS eq 'PS6'}">
                              <bean:message key="screen.b_bac.suspended"/>
                          </c:when>
                          <c:when test="${item.SERVICES_STATUS eq 'PS7'}">
                              <bean:message key="screen.b_bac.terminated"/>
                          </c:when>
                          <c:when test="${item.SERVICES_STATUS eq 'PS8'}">
                              <bean:message key="screen.b_bac.rejected"/>
                          </c:when>
                          <c:otherwise>
                          </c:otherwise>
                      </c:choose>
                  </td>
                  <td class="<%=borderClass %>" align="left">
                      <c:choose>
                          <c:when test="${item.BILLING_STATUS eq 'BS0'}">
                              <bean:message key="screen.b_bac.billing_status.BS0"/>
                          </c:when>
                          <c:when test="${item.BILLING_STATUS eq 'BS1'}">
                              <bean:message key="screen.b_bac.billing_status.BS1"/>
                          </c:when>
                          <c:when test="${item.BILLING_STATUS eq 'BS2'}">
                              <bean:message key="screen.b_bac.billing_status.BS2"/>
                          </c:when>
                          <c:when test="${item.BILLING_STATUS eq 'BS3'}">
                              <bean:message key="screen.b_bac.billing_status.BS3"/>
                          </c:when>
                      </c:choose>
                  </td>
                  <td class="<%=borderClass %>" align="left" nowrap="nowrap">
                      <fmt:formatDate value="${item.BILL_FROM}" pattern="dd/MM/yyyy"/> - <fmt:formatDate value="${item.BILL_TO}" pattern="dd/MM/yyyy"/>
                  </td>
				</tr>
			</c:forEach>
			</logic:notEmpty>
		</table>
		<logic:empty name="_b_bacForm" property="listPlanInfo">
	        <div class="message" >
	            <bean:message key="info.ERR2SC002"/>
	        </div>
        </logic:empty>
		</div>
		<br/>
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%;">
		    <col width="60%"/>
		    <col width="40%"/>
		    <tr>
		        <td style="text-align:left;">
					<html:button property="forward_exit" styleClass="button" value="Exit" onclick="clickExit();"></html:button>
					<c:if test="${r_soaAccessRightBean eq '2' && 'BAC' eq _b_bacForm.map.bacType}">
						<c:if test="${_b_bacForm.map.headerInfo.BAC_D_COUNT ne 0}">
							<ts:submit value="Generate Statement" property="forward_generateStatement" styleId="forward_generateStatement" onclick="clickSetType('generateStatement');"></ts:submit>
						</c:if>
					</c:if>
		        </td>
		        <td style="text-align:right;">
		            <c:if test="${gernateBillingAccessRightBean eq '2'}">
						<c:if test="${_b_bacForm.map.headerInfo.BAC_D_COUNT ne 0}">
						    <c:if test="${_b_bacForm.map.headerInfo.BAC_D_COUNT ne 0}">
							    <button onclick="generateBillingClick('generateBilling');" id="forward_generateBilling" name="forward_generateBilling" style="width:120px;">Generate Billing</button>
							</c:if>
						</c:if>
					</c:if>
		        </td>
		    </tr>
		</table>
		<html:hidden property="idCustPlan" value="${_b_bacForm.map.headerInfo.idCustPlan}"/>
		<html:hidden property="idBillAccount" value="${_b_bacForm.map.headerInfo.ID_BILL_ACCOUNT}"/>
		<html:hidden property="editFlg" value="${_b_bacForm.map.editFlg}"/>
		<html:hidden property="clickBtnTypeFlg" name="_b_bacForm"/>
		<html:hidden property="popupInfo" name="_b_bacForm"/>
		<html:hidden property="popupClickYesFlg" name="_b_bacForm"/>
		<br/>
		<div class="message" style="width:880px;word-wrap: break-word;white-space : normal">
			<ts:messages id="message" message="true"><bean:write name="message"/></ts:messages>
		</div>
		<div class="error" style="width:880px;word-wrap: break-word;white-space : normal">
			<c:if test="${not empty _b_bacForm.map.lastMsg}">
				${_b_bacForm.map.lastMsg}
			</c:if>
		</div>
		<div class="message" style="width:880px;word-wrap: break-word;white-space : normal">
			<c:if test="${not empty _b_bacForm.map.successMsg}">
				${_b_bacForm.map.successMsg}
			</c:if>
		</div>
		<div class="error" style="width:880px;word-wrap: break-word;white-space : normal">
			<html:messages id="message">
				<bean:write name="message"/><br/>
			</html:messages>
		</div>
	</ts:form>
</body>
</html>
