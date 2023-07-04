<%@ tag language="java" pageEncoding="utf-8" body-content="scriptless" %>

<%@ taglib uri="/struts-html" prefix="html"%>
<%@ taglib uri="/struts-logic" prefix="logic"%>

<%@ attribute name="action" required="true" %>
<%@ attribute name="value" required="true" %>
<html:link action="${action}" style="display:none"></html:link><input type="button" class="button" onclick="self.location.href=this.previousSibling.href;" value="${value}" title="${value}" />
