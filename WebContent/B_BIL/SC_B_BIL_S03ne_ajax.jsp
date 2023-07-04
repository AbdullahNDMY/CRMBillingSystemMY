<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="/WEB-INF/bs" prefix="bs" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
	<head>
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
		<link href="${pageContext.request.contextPath}/B_BIL/css/b_bil.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/B_BIL/js/B_BIL_S03.js"></script>
		<title><bean:message key="screen.b_bil_s01.title"/></title>
	</head>
	<ts:body>
		<ts:form action="/SC_B_BIL_S03_03DSP">
			<bean:define id="index" value="0"/>
			<nested:nest property="bilHeaderInfo">
				<div class="itemBillDetailList">
					<nested:present property="bilDetail">
					<nested:iterate id="service" property="bilDetail" indexId="i">
						<div class="itemBillDetail">
							<nested:hidden property="gstCheck" styleClass="gstCheck" name="_b_bilForm"/>
							<nested:hidden property="itemId" styleClass="serviceItemId"/>
							<nested:hidden property="itemCat" styleClass="serviceItemCat"/>
							<nested:hidden property="itemLevel" styleClass="serviceItemLevel"/>
							<nested:hidden property="itemNo" styleClass="serviceItemNo"/>
							<nested:hidden property="itemDesc" styleClass="serviceItemDesc"/>
							<nested:hidden property="itemQty" styleClass="serviceItemQty"/>
							<nested:hidden property="itemUprice" styleClass="serviceItemUprice"/>
							<nested:hidden property="itemAmt" styleClass="serviceItemAmt"/>
							<nested:hidden property="itemGst" styleClass="serviceItemGst"/>
							<nested:hidden property="itemExportAmt" styleClass="serviceItemExportAmt"/>
							<nested:hidden property="applyGst" styleClass="serviceApplyGst"/>
							<nested:hidden property="isDisplay" styleClass="serviceIsDisplay"/>
							<nested:hidden property="idCustPlan" styleClass="serviceIdCustPlan"/>
							<nested:hidden property="idCustPlanGrp" styleClass="serviceIdCustPlanGrp"/>
							<nested:hidden property="idCustPlanLink" styleClass="serviceIdCustPlanLink"/>
							<nested:hidden property="itemType" styleClass="serviceItemType"/>
							<nested:hidden property="svcLevel1" styleClass="serviceSvcLevel1"/>
							<nested:hidden property="svcLevel2" styleClass="serviceSvcLevel2"/>
							<nested:hidden property="billFrom" styleClass="serviceBillFrom"/>
							<nested:hidden property="billTo" styleClass="serviceBillTo"/>
							<nested:hidden property="jobNo" styleClass="serviceJobNo"/>
							<nested:hidden property="isDisplayMinSvc" styleClass="serviceIsDisplayMinSvc"/>
							<nested:hidden property="minSvcFrom" styleClass="serviceMinSvcFrom"/>
							<nested:hidden property="minSvcTo" styleClass="serviceMinSvcTo"/>
							<nested:hidden property="billFromDisplay" styleClass="serviceBillFromDisplay"/>
							<nested:hidden property="billToDisplay" styleClass="serviceBillToDisplay"/>
							<nested:hidden property="minSvcFromDisplay" styleClass="serviceMinSvcFromDisplay"/>
							<nested:hidden property="minSvcToDisplay" styleClass="serviceMinSvcToDisplay"/>
							<!-- #154 Start -->
							<nested:hidden property="itemDisc" styleClass="serviceItemDisc"/>
							<nested:hidden property="itemSubTotal" styleClass="serviceItemSubTotal"/>
							<nested:hidden property="taxCode" styleClass="serviceTaxCode"/>
							<nested:hidden property="taxRate" styleClass="serviceTaxRate"/>
							<nested:hidden property="itemExportGST" styleClass="serviceItemExportGST"/>
							<nested:hidden property="poNo" styleClass="servicePoNo"/>
							<nested:hidden property="displayDiscount" styleClass="serviceDisplayDiscount"/>
							<!-- #154 End -->
							<table cellspacing="0" cellpadding="0" style="width:100%">
								<col width="6%"/>
								<col width="5%"/>
								<col width="47%"/>
								<col width="10%"/>
								<col width="12%"/>
								<col width="15%"/>
								<col width="5%"/>
								<tr>
									<td class="fontSize" style="padding-left:10px;" valign="top">
										<span class="removeLink" onclick="removeBillItem(this)" style="cursor: pointer;">
											<img alt="" src="<%=request.getContextPath()%>/image/delete.gif">
										</span>
										&nbsp;&nbsp;&nbsp;
										<span class="editLink" onclick="editBillItem(this)" style="cursor: pointer;">
											<img alt="" src="<%=request.getContextPath()%>/image/editIcon.jpg">
										</span>
									</td>
									<td class="fontSize" style="text-align:left;">
										<div class="serviceItemIndex">
											<c:if test="${service.isDisplay ne '0'}">
												<bean:define id="index" value="${index+1}"/>${index}
											</c:if>
											&nbsp;
										</div>
									</td>
									<td class="fontSize" style="width:470px;word-wrap: break-word;white-space : normal">
										<a href="#" onclick="openB_CPM_View('${pageContext.request.contextPath}/B_CPM/B_CPM_S05InitBL.do?customerPlan.idCustPlan=${service.idCustPlan}&customerPlan.fromScreen=BIL&customerPlan.billType=${_b_bilForm.map.bilHeaderInfo.billType}')">
											<div class="divServiceDesc"><pre>${service.itemDesc}</pre></div>
										</a>
									</td>
									<td class="fontSize" style="text-align:right;word-break:break-all;">
										<div class="divServiceQty">
											<c:if test="${service.isDisplay ne '0'}"><fmt:formatNumber value="${service.itemQty}" pattern="#,##0"/></c:if>
										</div>
									</td>
									<td valign="top" class="fontSize" style="text-align:right;word-break:break-all;">
										<div class="divServiceUprice">
											<c:if test="${service.isDisplay ne '0'}">
											<%-- 
												<c:if test="${_b_bilForm.map.bilHeaderInfo.billType eq 'CN'}">
										    		-
										    	</c:if>
										    --%>
												<fmt:formatNumber value="${service.itemUprice}" pattern="#,##0.00"/>
											</c:if>
										</div>
									</td>
									<td valign="top" class="fontSize" style="text-align:right;word-break:break-all;">
										<div class="divServiceAmt">
											<c:choose>
										    <c:when test="${service.isDisplay ne '0'}">
										    <%-- 
										    	<c:if test="${_b_bilForm.map.bilHeaderInfo.billType eq 'CN'}">
										    		-
										    	</c:if>
										    --%>
												<fmt:formatNumber value="${service.itemAmt}" pattern="#,##0.00"/>
											</c:when>
											<c:otherwise>
											&nbsp;
											</c:otherwise>
											</c:choose>
										</div>
										<input type="hidden" class="itemAmtHidden" value="${service.itemAmt}">
									</td>
									<%-- #154 start --%>
									<%-- <td class="fontSize">&nbsp;</td> --%>
									<td valign="top" style="padding-left:10px;" class="fontSize">
							 	        <div class="divServiceGST">
							 	        	<c:if test="${service.itemCat ne '0'}">
									 	        <c:if test="${_b_bilForm.map.gstCheck eq 'TAX_CODE'}">
													<c:choose>
													    <c:when test="${not empty service.taxCode && service.isDisplay ne '0'}">
													  		${service.taxCode}
														</c:when>
														<c:otherwise>
															&nbsp;
														</c:otherwise>
													</c:choose>
												</c:if>
												<c:if test="${_b_bilForm.map.gstCheck eq 'TAX_RATE'}">
													<c:choose>
													    <c:when test="${not empty service.taxRate && service.isDisplay ne '0'}">
													  		${service.taxRate}%
														</c:when>
														<c:otherwise>
															&nbsp;
														</c:otherwise>
													</c:choose>
												</c:if>
											</c:if>
										</div>
									</td>
									<%-- #154 end --%>
								</tr>
								<tr class="trMinSvcFormAndTo">
									<td class="fontSize">&nbsp;</td>
									<td class="fontSize">&nbsp;</td>
									<td class="fontSize">
										<bean:message key="screen.b_bil.contractPeriod"/>
										<bean:message key="screen.b_bil.contractPeriodFrom"/>
										<span class="spanMinSvcFromDisplay">
											${service.minSvcFromDisplay}
										</span>
										<bean:message key="screen.b_bil.contractPeriodTo"/>
										<span class="spanMinSvcToDisplay">
											${service.minSvcToDisplay}
										</span>
									</td>
									<td class="fontSize">&nbsp;</td>
									<td class="fontSize">&nbsp;</td>
									<td class="fontSize">&nbsp;</td>
									<td class="fontSize">&nbsp;</td>
								</tr>
								<!-- item_Level is 1 start-->
								<tr>
									<td class="fontSize" colspan="7" style="width:100%">
										<nested:present property="subPlanBean">
										<nested:iterate id="subPlan" property="subPlanBean" indexId="j">
											<div class="subPlan">
												<nested:hidden property="itemId" styleClass="subPlanItemId"/>
												<nested:hidden property="itemCat" styleClass="subPlanItemCat"/>
												<nested:hidden property="itemLevel" styleClass="subPlanItemLevel"/>
												<nested:hidden property="itemNo" styleClass="subPlanItemNo"/>
												<nested:hidden property="itemDesc" styleClass="subPlanItemDesc"/>
												<nested:hidden property="itemQty" styleClass="subPlanItemQty"/>
												<nested:hidden property="itemUprice" styleClass="subPlanItemUprice"/>
												<nested:hidden property="itemAmt" styleClass="subPlanItemAmt"/>
												<nested:hidden property="itemGst" styleClass="subPlanItemGst"/>
												<nested:hidden property="itemExportAmt" styleClass="subPlanItemExportAmt"/>
												<nested:hidden property="applyGst" styleClass="subPlanApplyGst"/>
												<nested:hidden property="isDisplay" styleClass="subPlanIsDisplay"/>
												<nested:hidden property="idCustPlan" styleClass="subPlanIdCustPlan"/>
												<nested:hidden property="idCustPlanGrp" styleClass="subPlanIdCustPlanGrp"/>
												<nested:hidden property="idCustPlanLink" styleClass="subPlanIdCustPlanLink"/>
												<nested:hidden property="itemType" styleClass="subPlanItemType"/>
												<nested:hidden property="svcLevel1" styleClass="subPlanSvcLevel1"/>
												<nested:hidden property="svcLevel2" styleClass="subPlanSvcLevel2"/>
												<nested:hidden property="billFrom" styleClass="subPlanBillFrom"/>
												<nested:hidden property="billTo" styleClass="subPlanBillTo"/>
												<nested:hidden property="jobNo" styleClass="subPlanJobNo"/>
												<nested:hidden property="isDisplayJobNo" styleClass="subPlanIsDisplayJobNo"/>
												<nested:hidden property="isDisplayMinSvc" styleClass="subPlanIsDisplayMinSvc"/>
												<nested:hidden property="minSvcFrom" styleClass="subPlanMinSvcFrom"/>
												<nested:hidden property="minSvcTo" styleClass="subPlanMinSvcTo"/>
												<nested:hidden property="billFromDisplay" styleClass="subPlanBillFromDisplay"/>
												<nested:hidden property="billToDisplay" styleClass="subPlanBillToDisplay"/>
												<nested:hidden property="minSvcFromDisplay" styleClass="subPlanMinSvcFromDisplay"/>
												<nested:hidden property="minSvcToDisplay" styleClass="subPlanMinSvcToDisplay"/>
												<!-- #154 Start -->
												<nested:hidden property="itemDisc" styleClass="subPlanItemDisc"/>
												<nested:hidden property="itemSubTotal" styleClass="subPlanItemSubTotal"/>
												<nested:hidden property="taxCode" styleClass="subPlanTaxCode"/>
												<nested:hidden property="taxRate" styleClass="subPlanTaxRate"/>
												<nested:hidden property="itemExportGST" styleClass="subPlanItemExportGST"/>
												<nested:hidden property="poNo" styleClass="subPlanPoNo"/>
												<nested:hidden property="displayDiscount" styleClass="subPlanDisplayDiscount"/>
												<!-- #154 End -->
											
												<table cellspacing="0" cellpadding="0" style="width:100%">
													<col width="6%"/>
													<col width="5%"/>
													<col width="47%"/>
													<col width="10%"/>
													<col width="12%"/>
													<col width="15%"/>
													<col width="5%"/>
													<tr>
													   <td class="fontSize">&nbsp;</td>
													   <td valign="top" class="fontSize">
													        <div class="divSubPlanDisplayJobNo">
														        <c:if test="${'1' eq subPlan.isDisplayJobNo && '1' eq _b_bilForm.map.bilHeaderInfo.jobModulesDisplayFlg}">
														            &nbsp;
														        </c:if>
													        </div>
													   		<div class="subPlanItemIndex">
														   		<c:if test="${subPlan.isDisplay ne '0'}">
																	<bean:define id="index" value="${index+1}"/>${index}
																</c:if>&nbsp;
															</div>
														</td>
														<td valign="top" class="fontSize" style="width:470px;word-wrap: break-word;white-space : normal">
															<div style="color:#CD853F;" class="divJob">
																<c:if test="${'1' eq subPlan.isDisplayJobNo && '1' eq _b_bilForm.map.bilHeaderInfo.jobModulesDisplayFlg}">
																	<bean:message key="screen.b_bil.jobNoPoint"/>&nbsp;${subPlan.jobNo}
																</c:if>
															</div>
															<div class="divSubPlanDesc"><pre>${subPlan.itemDesc}</pre></div>
														</td>
														<td valign="top" class="fontSize" style="text-align:right;word-break:break-all;">
															<div class="divSubPlanDisplayJobNo">
														        <c:if test="${'1' eq subPlan.isDisplayJobNo && '1' eq _b_bilForm.map.bilHeaderInfo.jobModulesDisplayFlg}">
														            &nbsp;
														        </c:if>
													        </div>
															<div class="divSubPlanQty">
																<c:if test="${subPlan.isDisplay ne '0'}"><fmt:formatNumber value="${subPlan.itemQty}" pattern="#,##0"/></c:if>
															</div>
														</td>
														<td valign="top" class="fontSize" style="text-align:right;word-break:break-all;">
															<div class="divSubPlanDisplayJobNo">
														        <c:if test="${'1' eq subPlan.isDisplayJobNo && '1' eq _b_bilForm.map.bilHeaderInfo.jobModulesDisplayFlg}">
														            &nbsp;
														        </c:if>
													        </div>
															<div class="divSubPlanUprice">
																<c:if test="${subPlan.isDisplay ne '0'}">
																<%-- 
																	<c:if test="${_b_bilForm.map.bilHeaderInfo.billType eq 'CN'}">
															    		-
															    	</c:if>
															    --%>
																	<fmt:formatNumber value="${subPlan.itemUprice}" pattern="#,##0.00"/>
																</c:if>
															</div>
														</td>
														<td valign="top" class="fontSize" style="text-align:right;word-break:break-all;">
															<div class="divSubPlanDisplayJobNo">
														        <c:if test="${'1' eq subPlan.isDisplayJobNo && '1' eq _b_bilForm.map.bilHeaderInfo.jobModulesDisplayFlg}">
														            &nbsp;
														        </c:if>
													        </div>
															<div class="divSubPlanAmt">
																<c:choose>
															    <c:when test="${subPlan.isDisplay ne '0'}">
															    <%-- 
															    	<c:if test="${_b_bilForm.map.bilHeaderInfo.billType eq 'CN'}">
															    		-
															    	</c:if>
															    --%>
																	<fmt:formatNumber value="${subPlan.itemAmt}" pattern="#,##0.00"/>
																</c:when>
																<c:otherwise>
																	&nbsp;
																</c:otherwise>
																</c:choose>
															</div>
														</td>
														<td valign="top" class="fontSize" style="padding-left:10px;">
															<div class="divSubPlanDisplayJobNo">
														        <c:if test="${'1' eq subPlan.isDisplayJobNo && '1' eq _b_bilForm.map.bilHeaderInfo.jobModulesDisplayFlg}">
														            &nbsp;
														        </c:if>
													        </div>
													        <%-- #154 start --%>
													        <div class="divGst">
																<%-- <c:choose>
																    <c:when test="${'1' eq subPlan.applyGst}">
																		<bean:message key="screen.b_bil.yes"/>
																		<input type="hidden" class="itemApplyGstHidden" value="1">
																	</c:when>
																	<c:otherwise>
																		&nbsp;
																		<input type="hidden" class="itemApplyGstHidden" value="0">
																	</c:otherwise>
																</c:choose> --%>
																<c:if test="${_b_bilForm.map.gstCheck eq 'TAX_CODE'}">
																	<c:choose>
																	    <c:when test="${'1' eq subPlan.isDisplay}">
																			${subPlan.taxCode}
																			<input type="hidden" class="itemApplyGstHidden" value="1">
																		</c:when>
																		<c:otherwise>
																			&nbsp;
																			<input type="hidden" class="itemApplyGstHidden" value="0">
																		</c:otherwise>
																	</c:choose>
																</c:if>
																<c:if test="${_b_bilForm.map.gstCheck eq 'TAX_RATE'}">
																	<c:choose>
																	    <c:when test="${'1' eq subPlan.isDisplay}">
																			${subPlan.taxRate}%
																			<input type="hidden" class="itemApplyGstHidden" value="1">
																		</c:when>
																		<c:otherwise>
																			&nbsp;
																			<input type="hidden" class="itemApplyGstHidden" value="0">
																		</c:otherwise>
																	</c:choose>
																</c:if>
															</div>
															<%-- #154 end --%>
														</td>
													</tr>
													<%-- #154 start --%>
													<tr id="trSubPlanDisCount" style="display: none;" >
														<td>&nbsp;</td>
														<td>&nbsp;</td>
											 	        <td style="text-align:left;font-size:16px;">
											 	           <b><i><bean:message key="screen.b_bil.discount"/></i></b>
											 	        </td>
											 	        <td>&nbsp;</td>
														<td>&nbsp;</td>
											 	        <td style="text-align:right;font-size:16px;">
												 	        <div class="divSubPlanItemDisc">
											 	          		<fmt:formatNumber value="${subPlan.itemDisc}" pattern="#,##0.00"/>
											 	          	</div>
											 	        </td>
											 	        <td valign="top" style="padding-left:10px;" class="fontSize">
												 	        <div class="divSubPlanDisCountGST">
													 	        <c:if test="${_b_bilForm.map.gstCheck eq 'TAX_CODE'}">
																	<c:choose>
																	    <c:when test="${not empty subPlan.taxCode && subPlan.isDisplay ne '0'}">
																	  		${subPlan.taxCode}
																		</c:when>
																		<c:otherwise>
																			&nbsp;
																		</c:otherwise>
																	</c:choose>
																</c:if>
																<c:if test="${_b_bilForm.map.gstCheck eq 'TAX_RATE'}">
																	<c:choose>
																	    <c:when test="${not empty subPlan.taxRate && subPlan.isDisplay ne '0'}">
																	  		${subPlan.taxRate}%
																		</c:when>
																		<c:otherwise>
																			&nbsp;
																		</c:otherwise>
																	</c:choose>
																</c:if>
															</div>
														</td>
													</tr>
													<%-- #154 end --%>
												</table>
											</div>
										</nested:iterate>
										</nested:present>
									</td>	
								</tr>
								<%-- #154 start --%>
								<tr id="trServiceDisCount" style="display: none;" >
									<td colspan="7">
										<table width="100%">
												<col width="6%"/>
												<col width="5%"/>
												<col width="47%"/>
												<col width="10%"/>
												<col width="12%"/>
												<col width="15%"/>
												<col width="5%"/>
											<tr>
												<td colspan="7">&nbsp;</td>
											</tr>
											<tr>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<c:if test="${service.itemCat eq '1'}">
										 	        <td style="text-align:left;font-size:16px;">
										 	           <b><i><bean:message key="screen.b_bil.discount"/></i></b>
										 	        </td>
										 	        <td>&nbsp;</td>
													<td>&nbsp;</td>
										 	        <td style="text-align:right;font-size:16px;" valign="top">
											 	        <div class="divServiceItemDisc">
											 	          	<fmt:formatNumber value="${service.itemDisc}" pattern="#,##0.00"/>
											 	        </div>
										 	        </td>
										 	        <td valign="top" style="padding-left:10px;text-align:left;font-size:16px;">
											 	        <div class="divServiceDisCountGST">
											 	        	<c:if test="${service.isDisplay ne '0'}">
											 	        		<c:if test="${_b_bilForm.map.gstCheck eq 'TAX_CODE'}">
																	<c:choose>
																	    <c:when test="${not empty service.taxCode}">
																	  		${service.taxCode}
																		</c:when>
																		<c:otherwise>
																			&nbsp;
																		</c:otherwise>
																	</c:choose>
																</c:if>
																<c:if test="${_b_bilForm.map.gstCheck eq 'TAX_RATE'}">
																	<c:choose>
																	    <c:when test="${not empty service.taxRate}">
																	  		${service.taxRate}%
																		</c:when>
																		<c:otherwise>
																			&nbsp;
																		</c:otherwise>
																	</c:choose>
																</c:if>
											 	        	</c:if>
														</div>
													</td>
												</c:if>	
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="7">&nbsp;</td>
								</tr>
								<%-- #154 end --%>
								<!-- item_Level is 1 end-->
								<tr id="trBillPeriod" style="display:none;">
									<td class="fontSize">&nbsp;</td>
									<td class="fontSize">&nbsp;</td>
									<td class="fontSize">
										<bean:message key="screen.b_bil.billingPeriod"/>
										<bean:message key="screen.b_bil.colon1"/>
										<bean:message key="screen.b_bil.from"/>
										<span class="spanBillFromDisplay">
											${service.billFromDisplay}
										</span>
										<bean:message key="screen.b_bil.toInfo"/>
										<span class="spanBillToDisplay">
											${service.billToDisplay}
										</span>
									</td>
									<td class="fontSize">&nbsp;</td>
									<td class="fontSize">&nbsp;</td>
									<td class="fontSize">&nbsp;</td>
									<td class="fontSize">&nbsp;</td>
								</tr>
								<tr>
									<td class="fontSize">&nbsp;</td>
									<td class="fontSize">&nbsp;</td>
									<td class="fontSize">&nbsp;</td>
									<td class="fontSize">&nbsp;</td>
									<td class="fontSize">&nbsp;</td>
									<td class="fontSize">&nbsp;</td>
									<td class="fontSize">&nbsp;</td>
								</tr>
							</table>
						</div>
					</nested:iterate>
					</nested:present>
				</div>
			</nested:nest>
		</ts:form>
	</ts:body>
</html:html>