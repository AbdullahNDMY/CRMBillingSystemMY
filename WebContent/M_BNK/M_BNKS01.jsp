<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-logic" prefix="logic" %>
<%@ taglib uri="/terasoluna-struts" prefix="ts" %>
<%@ taglib uri="/terasoluna" prefix="t" %>
<%@ page import="java.util.List" %>
<%@ page import="nttdm.bsys.common.fw.BillingSystemUserValueObject" %>
<%@ page import="nttdm.bsys.c_cmn001.bean.UserAccess" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
				document.getElementById("hypBankReference").value="";
				
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
			function S01reset() {
				document.getElementById('bank_fullname').value="";
				document.getElementById('bank_code').value="";
				document.getElementById('branch_code').value="";
				document.getElementById('bank_bic_code').value="";
				document.getElementById("checkpagetype").value=0;
				var button=	document.getElementById("forward_reset"); 
				button.click();
			}
		</script>
	</head>
	<body id="m" onload="initMain();">		
	<ts:form action="/M_BNKS01DSP">
            <h1 class="title"><bean:message key="screen.m_bnk.title"/></h1>
			<div class="section" style="border-top:2px solid #cecece;border-bottom:2px solid #cecece;padding:5px 5px;margin-top:-5px;">
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td align="right">
						<bean:message key="screen.m_bnk.bankfullname"/> :
					</td>
					<td>
						 <html:text  name="m_bnkForm" property="bank_fullname" style="width:200px;" maxlength="200"></html:text>
					</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td align="right">
						<bean:message key="screen.m_bnk.bankbiccodecol"/> :
					</td>
					<td>
						 <html:text  name="m_bnkForm" property="bank_bic_code" style="width:200px;" maxlength="11"></html:text>
					</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td align="right">
						<bean:message key="screen.m_bnk.bankcode"/> :
					</td>
					<td>
						<html:text  name="m_bnkForm" property="bank_code" style="width:200px;" maxlength="4"></html:text>
					</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td align="right">
						<bean:message key="screen.m_bnk.branchcode"/> :
					</td>
					<td>
						<html:text  name="m_bnkForm" property="branch_code" style="width:200px;"></html:text>
					</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
			</table>		
			</div>
		
			<table class="buttonGroup" cellpadding="0" cellspacing="0">
			  	<tr>
					<td>
						<input type="submit" class="button" value="<bean:message key="screen.m_bnk.searchbutton"/>" name="forward_search" onclick="setcheckpagetype();" />
						<input type="button" class= "button"  value="<bean:message key="screen.m_bnk.resetbutton"/>"  onclick="S01reset()"/>
						<%
				if(session.getAttribute("USER_VALUE_OBJECT") != null)
				 {
					 BillingSystemUserValueObject uvo=(BillingSystemUserValueObject)session.getAttribute("USER_VALUE_OBJECT");
					List<UserAccess> listuser= uvo.getUser_access();
					String useraccess="";
					for(int i=0; i<listuser.size();i++)
						{
							if(listuser.get(i).getId_sub_module().equals("M-BNK"))
							useraccess=listuser.get(i).getAccess_type();
						}
						
					if(useraccess.equals("2"))
					{	
				%>		
							
									
						<input type="submit" class="button" value="<bean:message key="screen.m_bnk.newbutton"/>" name="forward_new" />
				<%
					}
				}
				 %>
						<ts:submit value='bankrefer'  property="forward_view"  style="visibility:hidden"/>
						<ts:submit value='reset'  property="forward_reset"  style="visibility:hidden"/>
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
			              action="${pageContext.request.contextPath}/M_BNK/M_BNKS01Blogic.do?checkpagetype=1" name="m_bnkForm" rowProperty="row"
			              totalProperty="totalCount" indexProperty="startIndex"
			              currentPageIndex="now" totalPageCount="total" submit="true"/>
			            <logic:present name="bnkPageLinks">  
							<bean:write name="bnkPageLinks" filter="false"/>
						</logic:present>	
					</td>	
				</tr>
			</table> 
			
			<table  class="resultInfo" cellpadding="0" cellspacing="0">
			  <tr>
			    <td class="header" width="10%" style="text-align:left;"><bean:message key="screen.m_bnk.nocol"/></td>
			    <td class="header" width="15%" style="text-align:left;"><bean:message key="screen.m_bnk.bankidcol"/></td>
			    <td class="header" width="30%" style="text-align:left;"><bean:message key="screen.m_bnk.bankfullname"/></td>
			    <td class="header" width="15%" style="text-align:left;"><bean:message key="screen.m_bnk.bankcodecol"/></td>
			    <td class="header" width="15%" style="text-align:left;"><bean:message key="screen.m_bnk.banchcodecol"/></td>
			    <td class="header" width="15%" style="text-align:left;"><bean:message key="screen.m_bnk.bankbiccodecol"/></td>
			  </tr>			  
			  			  	
			  <logic:present property="listsearch" name="m_bnkForm">
		      <logic:iterate id="bank"   name="m_bnkForm" property="listsearch" >
			  <tr>
			  <td class="defaultNo">
			  		<bean:write name="bank" property="idnum"/>
			  </td>
			    <td class="defaultText">
			    	<bean:write name="bank" property="ID_BANK"/>
			    </td>
			    <td class="defaultText">	
			    
			    <bean:define id="idbank" name="bank" property="ID_BANK"></bean:define>
			 	<a href="#" onclick="clickBillReferenceLink('<%=idbank%>')" ><bean:write name="bank" property="BANK_FULL_NAME"/></a>
			     </td>
			    <td class="defaultText">
			    	<bean:write name="bank" property="BANK_CODE"/>
			    </td>
			    <td class="defaultText">
			    	<bean:write name="bank" property="BRANCH_CODE"/>
			    </td>
			    <td class="defaultText">
			    	<bean:write name="bank" property="BIC_CODE"/>
			    </td>
			  </tr>
		   </logic:iterate>
 		</logic:present>
			  
			  </table>
			  
	 <html:hidden name="m_bnkForm" property="hypBankReference"/>
	 <html:hidden name="m_bnkForm" property="checkpagetype"/>
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