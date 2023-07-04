/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : G_SSM
 * SERVICE NAME   : G_SSM_S01
 * FUNCTION       : CSV Report
 * FILE NAME      : G_SSM_S01Action.java
 * 
 * Copyright © 2011 NTTDATA Corporation
 *
 * History
 * 
 * 
**********************************************************/
package nttdm.bsys.g_ssm_s01;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nttdm.bsys.b_ssm.s02.data.B_SSM_S02_FieldSet;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.util.MessageResources;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.web.struts.actions.BLogicAction;

/**
 * G_SSM_S01Action<br>
 * <ul>
 * <li>A BLogic class to process exporting IT Contact (calling dependently)
 * </ul>
 * <br>
 * @author  khaln
 * @version 1.0
 */
public class G_SSM_S01Action extends BLogicAction<Object> {
	
	private static final String[] CONTACT_TYPE = { "C1", "C2", "C3" };
	private static final String SPLIT = ",";
	private static final String QUOTE = "\"";
	private static final String ENDLINE = "\n";
	
	private MessageResources messageResource;
	
	/**
	 * queryDAO
	 */
	protected QueryDAO queryDAO;

	/**`
	 * queryDAO を取得する
	 * 
	 * @return queryDAO
	 */
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	/**
	 * queryDAO を設定する
	 * 
	 * @param queryDAO
	 *            queryDAO
	 */
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}
	
	public G_SSM_S01Action() {
		// TODO Auto-generated constructor stub		
	}
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			ServletRequest request, ServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return super.execute(mapping, form, request, response);
	}
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		messageResource = getResources(request);
		
		DynaActionForm dynaForm = (DynaActionForm)form;
		
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("arraySubID", request.getParameterValues(B_SSM_S02_FieldSet.FIELD_SUBSCRIPTIONID));
		List<Map<String, Object>> list = queryDAO.executeForMapList("G_SSM_S01.getITContactInfo", input);
		
		OutputStream os;
		if (list.size() > 0)
		{
			StringBuilder content = new StringBuilder();
			buildHeader(content);
			buildContent(content, list);
						
			try {
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
				String fileName = "IT_Contact_" + format.format(new Date());
				os = response.getOutputStream();
				response.reset();
			    response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + ".csv\"");
				os.write(content.toString().getBytes());
			    os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else
		{
			response.reset();
		    response.setContentType("text/html");
		    StringBuilder messageDisplay = new StringBuilder();
		    
		    //messageDisplay.append("<html><head>");
		    
		    messageDisplay.append("<script type=\"text/javascript\">");
		    //messageDisplay.append("function init() { ");
		    //messageDisplay.append("alert('"+messageResource.getMessage("info.ERR2SC002")+"');");
		    messageDisplay.append("var paramObj = new Object();");
		    messageDisplay.append("paramObj.message = '"+messageResource.getMessage("info.ERR2SC002")+"';");
		    messageDisplay.append("var screen = window.showModalDialog('../COMMON/POPALT.html', paramObj, 'dialogWidth:400px; dialogHeight:110px;status = no');");
		    //messageDisplay.append("} ");
		    messageDisplay.append("</script>");		    
		    
		    //messageDisplay.append("</head>");
		    //messageDisplay.append("<body onload=\"init();\">");
		    //messageDisplay.append("</body>");
		    //messageDisplay.append("</html>");
		    
		    response.getWriter().write(messageDisplay.toString());
		}
		
		return null;
	}
	
	private void buildHeader(StringBuilder header)
	{
		appendResourceLine(header, "csv.column.customer");
		appendResourceLine(header, "csv.column.subscription");
		appendResourceLine(header, "csv.column.name.1");
		appendResourceLine(header, "csv.column.desination.1");
		appendResourceLine(header, "csv.column.email.1");
		appendResourceLine(header, "csv.column.tel.1");
		appendResourceLine(header, "csv.column.fax.1");
		appendResourceLine(header, "csv.column.name.2");
		appendResourceLine(header, "csv.column.desination.2");
		appendResourceLine(header, "csv.column.email.2");
		appendResourceLine(header, "csv.column.tel.2");
		appendResourceLine(header, "csv.column.fax.2");
		appendResourceLine(header, "csv.column.name.3");
		appendResourceLine(header, "csv.column.desination.3");
		appendResourceLine(header, "csv.column.email.3");
		appendResourceLine(header, "csv.column.tel.3");
		endResourceLine(header, "csv.column.fax.3");
	}
	
	private void buildContent(StringBuilder content, List<Map<String, Object>> list)
	{
		String[] names = new String[CONTACT_TYPE.length];
		String[] designations = new String[CONTACT_TYPE.length];
		String[] emails = new String[CONTACT_TYPE.length];
		String[] telNos = new String[CONTACT_TYPE.length];
		String[] faxNos = new String[CONTACT_TYPE.length];
		for (int i=0; i<list.size(); i++)
		{
			appendLine(content, (String)list.get(i).get("CUST_NAME"));
			appendLine(content, (String)list.get(i).get("ID_SUB_INFO"));
			String contactType = (String)list.get(i).get("CONTACT_TYPE");
			String name = (String)list.get(i).get("NAME");
			String designation = (String)list.get(i).get("DESIGNATION");
			String email = (String)list.get(i).get("EMAIL");
			String telNo = (String)list.get(i).get("TEL_NO");
			String faxNo = (String)list.get(i).get("FAX_NO");
			String[] contactTypes1 = contactType.split(SPLIT);
			String[] names1 = name.split(SPLIT);
			String[] designations1 = designation.split(SPLIT);
			String[] emails1 = email.split(SPLIT);
			String[] telNos1 = telNo.split(SPLIT);
			String[] faxNos1 = faxNo.split(SPLIT);
			
			for (int j=0; j<CONTACT_TYPE.length; j++)
			{
				names[j] = StringUtils.EMPTY;
				designations[j] = StringUtils.EMPTY;
				emails[j] = StringUtils.EMPTY;
				telNos[j] = StringUtils.EMPTY;
				faxNos[j] = StringUtils.EMPTY;
			}
			
			for (int k=0; k<CONTACT_TYPE.length; k++)
			{
				for (int j=0; j<contactTypes1.length; j++)
				{
					if (contactTypes1[j].equals(CONTACT_TYPE[k]))
					{
						names[k] = names1[j];
						designations[k] = designations1[j];
						emails[k] = emails1[j];
						telNos[k] = telNos1[j];
						faxNos[k] = faxNos1[j];
						break;
					}
				}
			}
			for (int j=0; j<CONTACT_TYPE.length; j++)
			{
				appendLine(content, names[j]);
				appendLine(content, designations[j]);
				appendLine(content, emails[j]);
				appendLine(content, telNos[j]);
				appendLine(content, faxNos[j]);
			}
			content.append(ENDLINE);
		}
	}
	
	private void appendLine(StringBuilder source, String addition)
	{
		source.append(QUOTE);
		source.append(addition);
		source.append(QUOTE);
		source.append(SPLIT);
	}
	
	private void endLine(StringBuilder source, String addition)
	{
		source.append(QUOTE);
		source.append(addition);
		source.append(QUOTE);
		source.append(ENDLINE);
	}
	
	private void appendResourceLine(StringBuilder source, String key)
	{
		appendLine(source, messageResource.getMessage(key));
	}
	
	private void endResourceLine(StringBuilder source, String key)
	{
		endLine(source, messageResource.getMessage(key));
	}
}
