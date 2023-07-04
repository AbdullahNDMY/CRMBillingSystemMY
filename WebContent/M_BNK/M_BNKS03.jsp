<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ page import="java.util.List" %>
<%@ page import="nttdm.bsys.common.fw.BillingSystemUserValueObject" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/stylesheet/common.css"/>
		<script type="text/javascript" src="js/jquery-1.js"></script>	
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/messageBox.js"></script>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/M_BNK/js/m_bnk.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/common.js"></script>
		<script language="javascript">
			function initMain() {
				document.getElementById("checkpagetype").value=0;
			}
			function setcheckpagetype() {
			    showLoadingTipWindow();
				document.getElementById("checkpagetype").value=1;
				var startIndex = document.getElementById("startIndex");
				if(startIndex!=null && startIndex!=undefined) {
					startIndex.value="0";
				}
			}
			function S03reset() {
				document.getElementById('bank_fullname').value="";
				document.getElementById('bank_code').value="";
				document.getElementById('branch_code').value="";
				document.getElementById("checkpagetype").value=0;
				document.forms[0].submit();
			}
			function insertAct(context) {
				var selectedId = $("input[name='idRadioSel']:checked");
				var idBillAcc = selectedId.eq(0).val();

				var sendvales = $("input[name='"+idBillAcc+"']");
				var idBank = sendvales.eq(0).val();
				var bankFullName = sendvales.eq(1).val();
				var bankCode = sendvales.eq(2).val();
				var bankBranchCode = sendvales.eq(3).val();
				
				var index = document.getElementById('index').value;
				
				window.opener.bank03CallBack(index,idBank,bankFullName,bankCode,bankBranchCode);
				window.close();
			}
			function radioChecked() {
				var radioChecked = $("input[name='idRadioSel']:checked");
				if(radioChecked.length > 0) {
					$("#insertBtn").attr("disabled", false);
				}
				else {
					$("#insertBtn").attr("disabled", true);
				}
			}
		</script>
	</head>
	<body id="m" onload="initMain();">		
	<ts:form action="/M_BNK_S03Blogic">
            <h1 class="title"><bean:message key="screen.m_bnk.bnktitle"/></h1>
			<div class="section" style="border-top:2px solid #cecece;border-bottom:2px solid #cecece;padding:5px 5px;margin-top:-5px;">
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td align="right">
						<bean:message key="screen.m_bnk.bnkfullname"/> :
					</td>
					<td>
						 <html:text  name="m_bnkForm" property="bank_fullname" style="width:200px;" maxlength="200"></html:text>
					</td>
				</tr>
				<tr>
					<td align="right">
						<bean:message key="screen.m_bnk.bnkcode"/> :
					</td>
					<td>
						<html:text  name="m_bnkForm" property="bank_code" style="width:200px;" maxlength="4"></html:text>
					</td>
				</tr>
				<tr>
					<td align="right">
						<bean:message key="screen.m_bnk.bnkbranch"/> :
					</td>
					<td>
						<html:text  name="m_bnkForm" property="branch_code" style="width:200px;" maxlength="3"></html:text>
					</td>
				</tr>
			</table>		
			</div>
		
			<table class="buttonGroup" cellpadding="0" cellspacing="0">
			  	<tr>
					<td>
						<input type="submit" class="button" value="<bean:message key="screen.m_bnk.search"/>" name="forward_search" onclick="setcheckpagetype();" />
						<input type="button" class= "button"  value="<bean:message key="screen.m_bnk.reset"/>"  onclick="S03reset()"/>
						<button id="insertBtn" onclick="insertAct('<%=request.getContextPath()%>')" disabled="disabled"><bean:message key="screen.m_bnk.insert"/></button>
					</td>	
				</tr>
			</table>			
			<table class="searchResultNo" cellpadding="0" cellspacing="0">
			  	<tr>
					<td>
						<bean:message key="screen.m_bnk.searchfound"/> <bean:message key="screen.m_bnk.colon"/>
						<c:if test="${m_bnkForm.totalCount != -1}">
							<bean:write name="m_bnkForm" property="totalCount"/>
						</c:if>
					</td>	
				</tr>
			</table>  
			
			<table class="pageLink" cellpadding="0" cellspacing="0">
			  	<tr>
					<td>			
						<bean:message key="screen.m_bnk.gotopage"/>
						<ts:pageLinks id="bnkPageLinks"
			              action="${pageContext.request.contextPath}/M_BNK/M_BNK_S03Blogic.do?checkpagetype=1" name="m_bnkForm" rowProperty="row"
			              totalProperty="totalCount" indexProperty="startIndex"
			              currentPageIndex="now" totalPageCount="total" submit="true"/>
			            <logic:present name="bnkPageLinks">  
							<bean:write name="bnkPageLinks" filter="false"/>
						</logic:present>	
					</td>	
				</tr>
			</table> 
			
			<table  class="resultInfo" cellpadding="0" cellspacing="0" width="100%" style="table-layout:fixed">
			  <tr>
			    <td class="header" width="10%" style="text-align:left;"></td>
			    <td class="header" width="15%" style="text-align:left;"><bean:message key="screen.m_bnk.bnkid"/></td>
			    <td class="header" width="35%" style="text-align:left;"><bean:message key="screen.m_bnk.bnkfullname"/></td>
			    <td class="header" width="20%" style="text-align:left;"><bean:message key="screen.m_bnk.bnkcode"/></td>
			    <td class="header" width="20%" style="text-align:left;"><bean:message key="screen.m_bnk.bnkbranch"/></td>
			  </tr>			  
			  			  	
			  <logic:present property="listsearch" name="m_bnkForm">
			      <logic:iterate id="bank"   name="m_bnkForm" property="listsearch" >
					  <tr>
					  	<td class="defaultText" style="text-align:left;padding-left:10px;word-break:break-all;">
					  		<input type="radio" name="idRadioSel" onclick="radioChecked();" value="<bean:write name="bank" property="ID_BANK"/>"/>
					  		<input type="hidden" name="${bank.ID_BANK}" value="${bank.ID_BANK}"/>
					  		<input type="hidden" name="${bank.ID_BANK}" value="${bank.BANK_FULL_NAME}"/>
					  		<input type="hidden" name="${bank.ID_BANK}" value="${bank.BANK_CODE}"/>
					  		<input type="hidden" name="${bank.ID_BANK}" value="${bank.BRANCH_CODE}"/>
						</td>
					    <td class="defaultText" style="word-break:break-all;">
					    	<bean:write name="bank" property="ID_BANK"/>
					    </td>
					    <td class="defaultText" style="word-break:break-all;">	
					 		<bean:write name="bank" property="BANK_FULL_NAME"/>
					     </td>
					    <td class="defaultText" style="word-break:break-all;">
					    	<bean:write name="bank" property="BANK_CODE"/>
					    </td>
					    <td class="defaultText" style="word-break:break-all;">
					    	<bean:write name="bank" property="BRANCH_CODE"/>
					    </td>
					  </tr>
			   	</logic:iterate>
 			</logic:present>
			  
			</table>
			  
		 <html:hidden name="m_bnkForm" property="checkpagetype"/>
		 <html:hidden name="m_bnkForm" property="index"/>
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