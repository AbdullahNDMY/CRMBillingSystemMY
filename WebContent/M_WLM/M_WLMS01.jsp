<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">

<html:html locale="true">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Content-Script-Type" content="text/javascript">
    <meta http-equiv="Content-Style-Type" content="text/css">
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
    <meta name="Author" content="NTT Data Vietnam">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/stylesheet/tabcontent.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/M_WLM/css/m_wlms01.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/tabcontent.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   	<script type="text/javascript" src="<%=request.getContextPath()%>/M_WLM/js/m_wlms01.js"></script>
    <title><bean:message key="screen.m_wlm.screen_name"/></title>
</head>
<body id="m" onload="initMain();initPage();">
<ts:form action="/M_WLMS01DSP">
	<input type="hidden" name="event"/>
	<input type="hidden" id="deleteMsg" value='<bean:message key="info.ERR4SC003"/>'>
	<table class="subHeader" cellpadding="0" cellspacing="0">
    	<tr>
    		<td><bean:message key="screen.m_wlm.screen_name"/></td>
    	</tr>
    </table>
    	<table class="inputInfo" cellpadding="0" cellspacing="0">
    		<tr>
    			<td class="col1Top" width="15%"><bean:message key="screen.m_wlm.screen_id"/><bean:message key="screen.common.label_colon"/></td>
    			<td class="col2Top" width="30%">    	
    				<t:defineCodeList id="LIST_SCREEN" />
					<html:select name="_m_wlmForm" property="id_screen" styleId="id_screen" onchange="doChangeScreen(this);">
						<html:option value="" ><bean:message key="screen.common.list_blank_item"/></html:option>
						<html:options collection="LIST_SCREEN" property="id" labelProperty="name"/>
					</html:select>	
				</td>
    			<td class="col3Top" width="13%">&nbsp;</td>
    			<td class="col4Top">&nbsp;</td>
    		</tr>
	        <tr>
	        	<td class="colBottom"><br></td>
	            <td class="colBottom"><br></td>
	            <td class="colBottom"><br></td>
	            <td class="colBottom"><br></td>
	        </tr>    		
    	</table>
    	<table class="buttonGroup" cellpadding="0" cellspacing="0">
    		<tr>
    			<td>
    				<button onclick="search();"><bean:message key="screen.common.search"/></button>&nbsp;
    				<button onclick="resetPage();"><bean:message key="screen.common.reset"/></button>
    			</td>
    		</tr>
    	</table>
    		<div id="searchResult">
    <logic:present name="_m_wlmForm" property="listSectionGroup">
    
		<hr />
		<table class="information" cellpadding="0" cellspacing="0">
			<tr>
				<td colspan="3" class="header">
					<bean:message key="screen.common.generalinfo"/>
				</td>
			</tr>
			<tr>
				<td width="15%">
					<bean:message key="screen.m_wlm.screen_id"/>
				</td>
				<td width="85%">
				 	<bean:message key="screen.common.label_colon"/>&nbsp;
				 	<t:writeCodeValue codeList="LIST_SCREEN" name="_m_wlmForm" property="id_screen"></t:writeCodeValue>
				</td>		  	
			</tr>
		</table>
		<br/>
		<ul id="countrytabs" class="shadetabs">
		<logic:iterate id="sectionGroup" name="_m_wlmForm" property="listSectionGroup" indexId="i">
			<li><a href="#" rel='<bean:write name="sectionGroup" property="id_secgrp"/>'><bean:write name="sectionGroup" property="secgrp_desc"/></a></li>
		</logic:iterate>
</ul>

<div style="border:1px solid gray; width:700px; padding: 5px">

	<logic:iterate id="sectionGroup" name="_m_wlmForm" property="listSectionGroup">
		<div id='<bean:write name="sectionGroup" property="id_secgrp"/>' class="tabcontent">
			<div id='sectionsDiv<bean:write name="sectionGroup" property="id_secgrp"/>'>
			</div>
			<logic:equal name="_m_wlmForm" property="access_type" value="2">			
			<div class="buttonDiv">
				<button onclick="save('<bean:write name="sectionGroup" property="id_secgrp"/>');"><bean:message key="screen.m_wlm.save"/></button>
				<button onclick="addSection('<bean:write name="sectionGroup" property="id_secgrp"/>');"><bean:message key="screen.m_wlm.add_section"/></button>
								
			</div>
			</logic:equal>						
		</div>	
	</logic:iterate>

</div>

		


	<logic:iterate id="sectionGroup" name="_m_wlmForm" property="listSectionGroup" indexId="i">
		<script type="text/javascript">
			setSectionGroupList(<bean:write name="i" filter="true"/>, "<bean:write name="sectionGroup" property="id_secgrp" filter="true"/>");
		</script>
		<logic:iterate id="section" name="_m_wlmForm" property="listSection">
			<script type="text/javascript">
				setSectionList(<bean:write name="i" filter="true"/>,
								"<bean:write name="section" property="id_secgrp" filter="true"/>",
								"<bean:write name="section" property="section_no" filter="true"/>",
								"<bean:write name="section" property="section_desc" filter="true"/>");
			</script>
		</logic:iterate>
		<logic:iterate id="wfSection" name="_m_wlmForm" property="listWfSection">
			<script type="text/javascript">
				setWfSectionList(<bean:write name="i" filter="true"/>,
								"<bean:write name="wfSection" property="id_secgrp" filter="true"/>",
								"<bean:write name="wfSection" property="section_no" filter="true"/>",
								"<bean:write name="wfSection" property="section_desc" filter="true"/>",
								<bean:write name="wfSection" property="sequence_no" filter="true"/>);
			</script>
		</logic:iterate>
	</logic:iterate>
	</logic:present>
		</div>
	<logic:present name="_m_wlmForm" property="listWfLevel">
		<logic:iterate id="wfLevel" name="_m_wlmForm" property="listWfLevel">
			<script type="text/javascript">
				setLevelList("<bean:write name="wfLevel" property="id_secgrp" filter="true"/>",
							"<bean:write name="wfLevel" property="section_no" filter="true"/>",
							"<bean:write name="wfLevel" property="level_seq" filter="true"/>");
			</script>
		</logic:iterate>
	</logic:present>
	
	<logic:present name="_m_wlmForm" property="listWfAction">
		<logic:iterate id="wfAction" name="_m_wlmForm" property="listWfAction">
			<script type="text/javascript">
				setActionList("<bean:write name="wfAction" property="id_secgrp" filter="true"/>",
							"<bean:write name="wfAction" property="section_no" filter="true"/>",
							"<bean:write name="wfAction" property="level_seq" filter="true"/>",
							"<bean:write name="wfAction" property="id_action" filter="true"/>",
							"<bean:write name="wfAction" property="action_type" filter="true"/>",
							"<bean:write name="wfAction" property="pic" filter="true"/>",
							"<bean:write name="wfAction" property="response_expire" filter="true"/>",
							"<bean:write name="wfAction" property="condition_primary" filter="true"/>",
							"<bean:write name="wfAction" property="condition_operator" filter="true"/>",
							"<bean:write name="wfAction" property="condition_secondary" filter="true"/>");
			</script>
		</logic:iterate>
	</logic:present>
		<div id="save_success" class="hide"><span class="error"><ts:messages id="message" message="true"><bean:write name="message"/></ts:messages></span></div>
<div id="err_msg" style="color:red; font-style: italic" ></div>
<div class="areaHideStyle">
	<html:hidden name="_m_wlmForm" property="id_section_group"/>
	<html:hidden name="_m_wlmForm" property="listApSection"/>
	<html:hidden name="_m_wlmForm" property="listApAction"/>
	<html:hidden name="_m_wlmForm" property="access_type"/>
	<html:hidden name="_m_wlmForm" property="screen_desc"/>
	
</div>
	
</ts:form>

<div class="areaHideStyle">
	<html:select styleId="listUser" name="_m_wlmForm" property="id_screen">
		<html:option value="" ><bean:message key="screen.common.list_blank_item"/></html:option>
		<logic:present name="_m_wlmForm" property="listPic">
			<html:optionsCollection name="_m_wlmForm" property="listPic"/>
		</logic:present>
	</html:select>	
	<t:defineCodeList id="LIST_OPERATOR" />
	<html:select styleId="listOperator" name="_m_wlmForm" property="id_screen">
		<html:option value="" ><bean:message key="screen.common.list_blank_item"/></html:option>
		<html:options collection="LIST_OPERATOR" property="id" labelProperty="name"/>
	</html:select>	
		<t:defineCodeList id="LIST_ACTION_TYPE" />
	<html:select styleId="listActionType" name="_m_wlmForm" property="id_screen">
		<html:options collection="LIST_ACTION_TYPE" property="id" labelProperty="name"/>
	</html:select>
			<t:defineCodeList id="LIST_CON1" />
	<html:select styleId="listcon1" name="_m_wlmForm" property="id_screen">
	<html:option value="" ><bean:message key="screen.common.list_blank_item"/></html:option>
		<html:options collection="LIST_CON1" property="id" labelProperty="name"/>
	</html:select>		
</div>
</body>
</html:html>

