<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-html" prefix="html"%>
<%@ taglib uri="/struts-bean" prefix="bean"%>
<%@ taglib uri="/struts-logic" prefix="logic"%>
<%@ taglib uri="/terasoluna-struts" prefix="ts"%>
<%@ taglib uri="/terasoluna" prefix="t"%>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/stylesheet/common.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/javascript/common.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>
<link href="${pageContext.request.contextPath}/M_SVT/css/m_svts01.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/M_SVT/js/m_svts01_new.js"></script>
<style type="text/css">
.serviceTitle {
	background: #F8F8F8;
	border-top: 2px solid #CECECE;
	border-bottom: 2px solid #CECECE;
	color: #333399;
	font-size: 15px;
	font-weight: bold;
	margin: 10px 0 10px 0;
	padding: 3px 0 3px 5px;
	text-align: left;
	vertical-align: middle;
	width: 100%;
}
</style>
</head>
<body id="SvtNew" onload="loadService()">
	<t:defineCodeList id="PRODUCT_CODE_LIST" />
	
	<ts:form action="/M_SVTS01_NewSave">
		<input type="hidden" id="hiddenMsgExitConfirmation"
			value="<bean:message key="info.ERR4SC001"/>" />
		<input type="hidden" id="hiddenMsgConfirmDeletetion"
			value='<bean:message key="info.ERR4SC002"/>' />
		<input type="hidden" id="hiddenMsgNoSelected"
			value='<bean:message key="errors.ERR1SC005" arg0="{0}"/>' />
		<input type="hidden" id="hiddenMsgMandatory"
			value='<bean:message key="errors.ERR1SC005" arg0="{0}"/>' />
		<input type="hidden" id="hiddenMsgMinRecord"
			value='<bean:message key="errors.ERR1SC068"/>' />
		<input type="hidden" id="ERR4SC107"
			value="<bean:message key="errors.ERR1SC107" arg0="{0}" arg1="{1}"/>" />
		<input type="hidden" id="hiddenMsgNoCheck"
			value="<bean:message key="screen.m_svt.ERR1SC021" arg0="Service Item" arg1="delete"/>" />
		<html:hidden property="svcGrpName" value="${frm_MSVTS01.svcGrpName}"/>
		<html:hidden property="returnFlg" value="${frm_MSVTS01.returnFlg}" styleId="returnFlg"/>
		<html:hidden property="parameters" value="${frm_MSVTS01.parameters}" styleId="parameters"/>
		<input type="hidden" value="<%=request.getContextPath()%>" id="contextPath"/>
		<html:hidden property="title" value="${frm_MSVTS01.title}" styleId="title"/>
		<html:hidden property="editStatus" value="${frm_MSVTS01.editStatus}" styleId="editStatus"/>
		<!-- //Ver3.06 #278 [SIT] Issues found and fix: 1.M-CST, 2.M-SGV, 3.M-SVT CT 13072017 -->
		<html:hidden property="productCodeInput" value="${frm_MSVTS01.productCodeInput}" styleId="productCodeInput"/>
		<!-- //Ver3.06 #278 [SIT] Issues found and fix: 1.M-CST, 2.M-SGV, 3.M-SVT CT 13072017 -->
		<input type="hidden" id="idServiceListStr" value="${frm_MSVTS01.idServiceListStr}" />
		<div class="serviceTitle">
			<bean:message key="screen.m_svt.new_title" />
			${frm_MSVTS01.title}
		</div>

		<table style="width: 100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				 <td align="left" style="width: 60%">
				 	<c:if test="${frm_MSVTS01.editStatus eq 'new'}">
					 	<div>
					   		<bean:message key="screen.m_svt.svGroup"/><font color="red">*</font>
					   		<bean:message key="screen.m_svt.label_colon"/>
					   		<t:defineCodeList id="LIST_CATEGORY" />
					   		<html:select property="category" styleId="cmbSerivceGroup" style="width:210px;" name="frm_MSVTS01" 
					   		onchange="onCategoryChage(this)" >
					   			<html:option value=""><bean:message key="screen.m_svt.listBox.default"/></html:option>
					 			<html:options collection="LIST_CATEGORY" property="id" labelProperty="name"/>
					   		</html:select>
					   		<!-- Ver3.06 #278 [SIT] Issues found and fix: 1.M-CST, 2.M-SGV, 3.M-SVT CT 13072017-->
					   		<%-- <c:if test="${frm_MSVTS01.productCodeInput eq '1'}"> --%>
					   		<html:select property="productCode" styleId="productCodeList" styleClass="productCode" style="width:100px; display:none">
					   			<html:option value=""><bean:message key="screen.m_svt.listBox.default"/></html:option>
					 			<html:options collection="PRODUCT_CODE_LIST" property="id" labelProperty="name" styleClass="product_code"/>
					   		</html:select>
					   		<%-- </c:if> --%>
					   		<%-- <c:if test="${frm_MSVTS01.productCodeInput eq '0'}">
					   		<input type="text" value="<bean:write name="planService" property="productCode"/>" 
									Class="productCode" maxlength="300" style="width:100%;overflow:hidden;"/>
					   		</c:if> --%>
					   		<!-- Ver3.06 #278 [SIT] Issues found and fix: 1.M-CST, 2.M-SGV, 3.M-SVT CT 13072017-->
				   		</div>
			   		</c:if>
			   		<c:if test="${frm_MSVTS01.editStatus eq 'edit'}">
						<div>
							<bean:message key="screen.m_svt.svGroup" />
							<bean:message key="screen.m_svt.label_colon" />
							<%-- Choosed service group name--%>
							${frm_MSVTS01.svcGrpName}
							<html:hidden property="category" value="${frm_MSVTS01.category}"/>
						</div>
					</c:if>
		   		</td>
				<td align="left" style="width: 40%"><span
					style="color: #FF0000;font:16px;font-style:Italic;float: right;"> <bean:message
							key="screen.m_svt.descriptionAbbreviationRemark" /> <bean:message
							key="screen.m_svt.label_colon" /> 7 </span></td>
			</tr>
		</table>
		<table width="100%"  class="newService" id="tableServiceType">
			<tr class="header">
				<td class="colEmpty" align="center">
					<c:if test="${frm_MSVTS01.editStatus eq 'new'}">
						<a class="hlAdd" href="javascript:void(0);" onClick="onClickNewAdd()">
							<bean:message key="screen.m_svt.add" />
						</a> 
					</c:if>
					<input class="category" type="hidden" value="" /> 
					<input class="label" type="hidden" value='<bean:message key="screen.m_svt.svType"/>' />
				</td>
				<td class="colServiceType">
					${frm_MSVTS01.title} <font color="red">*</font>
				</td>
				<td class="colAbbr" nowrap="nowrap">
					<bean:message key="screen.m_svt.abbreviation" /> <font color="red">*</font>
				</td>
				<td class="colAccCode">
					<bean:message key="screen.m_svt.new_AccountCode" /> <font color="red">*</font>
				</td>
				<td class="colProduceCode">
					<bean:message key="screen.m_svt.produceCode" /> <font color="red">*</font>
				</td>
				<td class="colReference1">
					<bean:message key="screen.m_svt.new_Reference1" /> <font color="red">*</font>
				</td>
				<td class="colReference2">
					<bean:message key="screen.m_svt.new_Reference2" /> <font color="red">*</font>
				</td>
			</tr>

			<logic:present property="planServiceList" name="frm_MSVTS01">
				<logic:iterate id="planService" property="planServiceList" name="frm_MSVTS01">
					<tr class="trPlanService">
						<td style="background-color: white;" align="center">
							<input Class="serviceCategory" type="hidden" value="<bean:write name="planService" property="serviceCategory"/>"/>
							<input Class="idService" type="hidden" value="<bean:write name="planService" property="idService"/>"/>
							<input Class="isUsed" type="hidden" value="<bean:write name="planService" property="isUsed"/>"/>
							<c:if test="${frm_MSVTS01.editStatus eq 'new'}">
								<div class="hlDelete" style="cursor: hand; text-align: center;" onclick="deleteRow(this);">
									<strong><bean:message key="screen.m_svt.delete" /></strong>
								</div>
							</c:if>
						</td>
						<td style="background-color: white;">
							<input type="text" value="<bean:write name="planService" property="serviceDescription"/>" 
									Class="serviceDescription" maxlength="300" style="width:100%;overflow:hidden;"/>
						</td>
						<td style="background-color: white;">
							<input type="text" value="<bean:write name="planService" property="descAbbr"/>" 
									Class="descAbbr" maxlength="10" style="width:100%;overflow:hidden;"/>
						</td>
						<td style="background-color: white;">
							<input type="text" value="<bean:write name="planService" property="accCode"/>" 
									Class="accCode" maxlength="10" style="width:100%;overflow:hidden;"/>
						</td>
						<td style="background-color: white;">
							<!-- Ver3.06 #278 [SIT] Issues found and fix: 1.M-CST, 2.M-SGV, 3.M-SVT CT 13072017-->
					   		<c:if test="${frm_MSVTS01.productCodeInput eq '1'}">
					   			<html:select property="productCode" styleId="productCodeList" styleClass="productCode" style="width:100px;" name="planService">
					   			<html:option value=""><bean:message key="screen.m_svt.listBox.default"/></html:option>
					 			<html:options collection="PRODUCT_CODE_LIST" property="id" labelProperty="name" styleClass="product_code"/>
					   		</html:select>
					   		<script type="text/javascript">
					   		var selectVal="${planService.productCode}";
						   		$(".product_code").each(
						   			function(i) {
						   				if ($(this).val() != "${frm_MSVTS01.category}") {
						   					$(this).remove();
						   				} else {
						   					$(this).val($(this).text());
						   				}
						   			}
						   		);
						   		$(".productCode").val(selectVal);
					   		</script>
					   		</c:if>
					   		<c:if test="${frm_MSVTS01.productCodeInput eq '0'}">
					   		<input type="text" value="<bean:write name="planService" property="productCode"/>" 
									Class="productCode" maxlength="8" style="width:100%;overflow:hidden;"/>
					   		</c:if>
					   		<!-- Ver3.06 #278 [SIT] Issues found and fix: 1.M-CST, 2.M-SGV, 3.M-SVT CT 13072017-->
					   	</td>
						<td style="background-color: white;">
							<input type="text" value="<bean:write name="planService" property="reference1"/>" 
									Class="reference1" maxlength="10" style="width:100%;overflow:hidden;"/>
						</td>
						<td style="background-color: white;">
							<input type="text" value="<bean:write name="planService" property="reference2"/>" 
									Class="reference2" maxlength="10" style="width:100%;overflow:hidden;"/>
						</td>
					</tr>
				</logic:iterate>
			</logic:present>
		</table>
		<div class="groupButton">
			<button id="btnSave" style="" onclick="saveBtn()">
				<bean:message key="screen.m_svt.save" />
			</button>
			<html:button property="exit" styleClass="button" value="Exit"
				onclick="clickExit();"></html:button>
		</div>
		<div class="error">
			<html:messages id="message">
				<bean:write name="message" />
				<br />
			</html:messages>
		</div>
		<div class="message">
			<html:messages id="message" message="true">
				<bean:write name="message" />
				<br />
			</html:messages>
		</div>
	</ts:form>
</body>
</html:html>