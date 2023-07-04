<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
   		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/common.css"/>
   		<link type="text/css" rel="stylesheet" href="css/m_svg.css" />
   		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.js"></script>   		
   		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/messageBox.js"></script>
   		<script type="text/javascript" src="js/m_svg.js"></script>
   		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/common.js"></script> 
		<title><bean:message key="screen.m_svgs01.label.title"/></title>
	</head>
	<body id="m" onload="initMain();">
		<%-- <t:defineCodeList id="LIST_ORIGIN_CODE" /> --%>
		<t:defineCodeList id="LIST_JOURNAL_NO" />
		
		<ts:form action="/M_SVGS01_03BL">
			<table class="subHeader" cellpadding="0" cellspacing="0">
	    		<tr>
	    			<td><bean:message key="screen.m_svgs01.label.title"/></td>
	    		</tr>
	    	</table>
			<table cellpadding="0" cellspacing="0">
	    		<tr>
	    			<td>&nbsp;</td>
	    		</tr>
	    	</table>
	    	
			<table class="information" cellpadding="0" cellspacing="0" id="mainTable">
				<col width="5%"/><col width="5%"/><col width="20%"/>
				<col width="10%"/><col width="16%"/><col width="10%"/><col width="35%"/>
				<tr>
					<td class="header" >
						<a class="hyperLink" href="#" onclick="return addNew();">
							<bean:message  key="screen.m_svgs01.label.add_link"/>
						</a>
					</td>
					<td class="header"><bean:message key="screen.m_svgs01.label.id"/></td>
					<td class="header"><bean:message key="screen.m_svgs01.label.service_group_name"/></td>
					<!--<td class="header"><bean:message key="screen.m_svgs01.label.accounting_code"/></td>-->
					<td class="header"><bean:message key="screen.m_svgs01.label.origin_code"/></td>
					<td class="header"><bean:message key="screen.m_svgs01.label.account_code"/></td>
					<td class="header"><bean:message key="screen.m_svgs01.label.journal_no"/></td>
					<td class="header"><bean:message key="screen.m_svgs01.label.product_code"/></td>
					<td class="header"><bean:message key="screen.m_svgs01.label.remark"/></td>
				</tr>
				<logic:iterate id="serviceGroupBean" name="_m_svgForm" property="listServiceGroupBean" indexId="index">
				<!-- #246 >> Category Is Used Not Allowed To Edit wcbeh@20170404 -->
					<!-- Case 'Y' - If Category Is Not Used -->
					<logic:equal value="Y" name="serviceGroupBean" property="is_used">
						<tr>
							<td></td>
							<td>
								${serviceGroupBean.svc_grp}
								<html:hidden name="serviceGroupBean" property="svc_grp" indexed="true" alt="svc_grp"/> 
								<html:hidden name="serviceGroupBean" property="is_used" indexed="true"/>
							</td>
							<td>
								<bean:write name="serviceGroupBean" property="svc_grp_name"/>
								<html:hidden name="serviceGroupBean" property="svc_grp_name" indexed="true"/>
							</td>
							<td><html:text styleClass="InputTextbox" styleId="originCode" name="serviceGroupBean" property="origin_code" indexed="true" maxlength="10"/></td> 
							<td><html:text styleClass="InputTextbox" styleId="tbxacccode" name="serviceGroupBean" property="acc_code" indexed="true" maxlength="15" size="5"/></td>						 
							<td>
								<html:select property="serviceGroupBean[${index}].journal_no" styleClass="cbojournalno">
		                            <option value=""><bean:message key="screen.m_svgs01.info.selectone"/></option>
		                            <html:options collection="LIST_JOURNAL_NO" property="id" labelProperty="name"/>
	                        	</html:select> 
							</td>
							<td><html:text styleClass="InputTextbox" styleId="tbxproductcode" name="serviceGroupBean" property="product_code" indexed="true" maxlength="3"/></td>
							<td><html:text styleClass="InputTextbox" name="serviceGroupBean" property="remark" indexed="true" maxlength="50"/></td> 
						</tr>
					</logic:equal>
					<!-- Case 'N' - If Category Is In Used -->
					<logic:notEqual value="Y" name="serviceGroupBean" property="is_used">
						<tr>
							<td></td>
							<td>
								${serviceGroupBean.svc_grp}
								<html:hidden name="serviceGroupBean" property="svc_grp" indexed="true" alt="svc_grp"/>
								<html:hidden name="serviceGroupBean" property="is_used" indexed="true"/>  
							</td> 
							<td><html:text styleClass="InputTextbox" name="serviceGroupBean" property="svc_grp_name" indexed="true" maxlength="20"/></td> 
							<!--<td><html:text styleClass="InputTextbox" name="serviceGroupBean" property="acc_code" indexed="true" maxlength="20"/></td>--> 
							<%-- <td>
					    	<html:select styleClass="InputTextbox" name="serviceGroupBean" property="origin_code" indexed="true">
					    		<option value=""><bean:message key="screen.m_svgs01.info.selectone"/></option>
								<html:options collection="LIST_ORIGIN_CODE" property="id" labelProperty="name"/>
							</html:select>						
							</td> --%>
							<td><html:text styleClass="InputTextbox" styleId="originCode" name="serviceGroupBean" property="origin_code" indexed="true" maxlength="10"/></td> 
							<td><html:text styleClass="InputTextbox" styleId="tbxacccode" name="serviceGroupBean" property="acc_code" indexed="true" maxlength="15" size="5"/></td>						 
							<td>
								<html:select property="serviceGroupBean[${index}].journal_no" styleClass="cbojournalno">
		                            <option value=""><bean:message key="screen.m_svgs01.info.selectone"/></option>
		                            <html:options collection="LIST_JOURNAL_NO" property="id" labelProperty="name"/>
	                        	</html:select> 
							</td>
							<td><html:text styleClass="InputTextbox" styleId="tbxproductcode" name="serviceGroupBean" property="product_code" indexed="true" maxlength="3"/></td>
							<td><html:text styleClass="InputTextbox" name="serviceGroupBean" property="remark" indexed="true" maxlength="50"/></td> 
						</tr>
					</logic:notEqual>
				<!-- #246 << Category Is Used Not Allowed To Edit wcbeh@20170404 -->
				</logic:iterate>
			</table>
            <table>
            	<tr>
					<td align="left">
						<html:hidden name="_m_svgForm" property="action"  indexed="false" />  
						<input type="submit" name="forward_save" class="button" value="<bean:message key="screen.m_svgs01.label.save" />" onclick="return onSaveClick();"/>
					</td>
				</tr>
            </table>
            <input type="hidden" id="ERR4SC107" value="<bean:message key="errors.ERR1SC107" arg0="{0}" arg1="{1}"/>"/>
            <div class="message">
				<ts:messages id="message" message="true"><bean:write name="message"/></ts:messages>
			</div>
			<div class="error">
				<html:messages id="message">
					<bean:write name="message"/><br/>
				</html:messages>
			</div>
		</ts:form>
		<table id="cloneTable" style="display: none;">
			<tr id="cloneRow">
				<td/>
				<td>
					<input type="hidden" alt="svc_grp">
				</td> 
				<td>
					<input type="text" class="InputTextbox" alt="svc_grp_name" maxlength="20">
				</td> 
				<%-- <td>
					<select title="origin_code" class="InputTextbox" >
						<option value=""><bean:message key="screen.m_svgs01.info.selectone"/></option>
						<logic:iterate id="originCode" name="LIST_ORIGIN_CODE">
							<option value="${originCode.id }">
								<bean:write name="originCode" property="name"/>
							</option>
						</logic:iterate>
					</select>
				</td>  --%>
				<td>
					<input type="text" class="InputTextbox"  id="originCode" alt="origin_code" maxlength="10"/>
				</td>
				<td>
					<input type="text" class="InputTextbox"  id="tbxacccode" alt="acc_code" maxlength="15"/>
				</td>
				<td>
					<select title="journal_no" class="InputTextbox" >
						<option value=""><bean:message key="screen.m_svgs01.info.selectone"/></option>
						<logic:iterate id="journalList" name="LIST_JOURNAL_NO">
							<option value="${journalList.id }">
								${journalList.name }
							</option>
						</logic:iterate>
					</select>
				</td>
				<td>
					<input type="text" class="InputTextbox"  id="tbxproductcode" alt="product_code" maxlength="3"/>
				</td>
				<td>
					<input type="text" class="InputTextbox" alt="remark" maxlength="50"/>
				</td> 
			</tr>
		</table>
	</body>
</html:html>

