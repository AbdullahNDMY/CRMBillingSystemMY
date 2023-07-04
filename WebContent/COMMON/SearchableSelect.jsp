<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-html" prefix="html"%>
<%@ taglib uri="/struts-bean" prefix="bean"%>
<%@ taglib uri="/struts-logic" prefix="logic"%>
<%@ taglib uri="/terasoluna-struts" prefix="ts"%>
<%@ taglib uri="/terasoluna" prefix="t"%>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/bs" prefix="bs"%>

<html:html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
	<link type="text/css" rel="stylesheet"
		ref="<%=request.getContextPath()%>/stylesheet/common.css" />
	<link rel="stylesheet" type="text/css"
		href="<%=request.getContextPath()%>/stylesheet/tabcontent.css" />
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/javascript/common.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/javascript/tabcontent.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
	<link type="text/css" rel="stylesheet"
		href="<%=request.getContextPath()%>/stylesheet/jquery.searchableSelect.css" />
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/javascript/jquery-1.7.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/javascript/jquery-ui.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/javascript/jquery.searchableSelect.js"></script>
	<title></title>
	<style>
		.ui-autocomplete-maxheight {
   			height: 200px;
   		}
/* 		.ui-autocomplete {
    		overflow-y: auto;
    		overflow-x: hidden;
    		font-family: Calibri;
    		font-size: 1em;
  		} */
  		.custom-combobox {
   			 position: relative;
    		display: inline-block;
    		/* height:80px; */
  		}
  		.custom-combobox-toggle {
    		position: absolute;
    		top: 1px;
    		bottom: 0;
    		margin-left: -1px;
    		padding: 0;
    		width:20px;
  		}
  		.custom-combobox-input {
    		margin: 0;
    		padding-left: 0.2em;
  		}		
	</style>
</head>
	<div id="mytips" class="ui-autocomplete ui-front ui-menu ui-widget-content" style="display:none;">
		Loading.........
   </div>
</html:html>