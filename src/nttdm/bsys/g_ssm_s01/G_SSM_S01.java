/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : G_SSM
 * SERVICE NAME   : G_SSM_S01
 * FUNCTION       : CSV Report
 * FILE NAME      : G_SSM_S01.java
 * 
 * Copyright © 2011 NTTDATA Corporation
 *
 * History
 * 
 * 
**********************************************************/
package nttdm.bsys.g_ssm_s01;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.MessageResources;

import nttdm.bsys.b_ssm.s02.data.B_SSM_S02_FieldSet;
import nttdm.bsys.b_ssm.s03.b_rpt.common.AB_RPT;
import nttdm.bsys.b_ssm.s03.b_rpt.data.B_RPT_Output;
import nttdm.bsys.b_ssm.s03.b_rpt.data.ContentType;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.util.exception.ReportException;

/**
 * G_SSM_S01<br>
 * <ul>
 * <li>A class to process exporting IT Contact
 * </ul>
 * <br>
 * @author  khaln
 * @version 1.0
 */
public class G_SSM_S01 extends AB_RPT<Map<String,Object>> {

	private static final String[] CONTACT_TYPE = { "C1", "C2", "C3" };
	private static final String SPLIT = ",";
	private static final String QUOTE = "\"";
	private static final String ENDLINE = "\n";
	private MessageResources messageResource;
	private MessageResources globalMessageResource;
	
	public G_SSM_S01(QueryDAO queryDAO) {
		super(queryDAO);
		// TODO Auto-generated constructor stub
		messageResource = MessageResources.getMessageResources("G_SSM_S01-messages");
		globalMessageResource = MessageResources.getMessageResources("application-messages");
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
		for (int i=0; i<list.size(); i++)
		{
			appendLine(content, (String)list.get(i).get("CUST_NAME"));
			appendLine(content, (String)list.get(i).get("ID_SUB_INFO"));
			String idContact = (String)list.get(i).get("ID_CONTACT");
			
			List<Map<String,Object>> info = queryDAO.executeForMapList("G_SSM_S01.getITContactInfo", idContact);
						
			for (int k=0; k<CONTACT_TYPE.length; k++)
			{
				for (int j=0; j<info.size(); j++)
				{
					if (CONTACT_TYPE[k].equals(info.get(j).get("CONTACT_TYPE")))
					{
						appendLine(content, info.get(j).get("NAME"));
						appendLine(content, info.get(j).get("DESIGNATION"));
						appendLine(content, info.get(j).get("EMAIL"));
						appendLine(content, info.get(j).get("TEL_NO"));
						appendLine(content, info.get(j).get("FAX_NO"));
						break;
					}
				}
			}
			content.append(ENDLINE);
		}
	}
	
	private void appendLine(StringBuilder source, Object addition)
	{
		source.append(QUOTE);
		source.append(CommonUtils.toString(addition));
		source.append(QUOTE);
		source.append(SPLIT);
	}
	
	private void endLine(StringBuilder source, Object addition)
	{
		source.append(QUOTE);
		source.append(CommonUtils.toString(addition));
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

	/**
	 * Create IT Contact report
	 * @param params
	 * @return B_RPT_Output
	 */	
	public B_RPT_Output export(Map<String, Object> params) throws ReportException {
		// TODO Auto-generated method stub
		B_RPT_Output result = new B_RPT_Output();
		
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("arraySubID", params.get(B_SSM_S02_FieldSet.FIELD_SUBSCRIPTIONID));
		List<Map<String, Object>> list = queryDAO.executeForMapList("G_SSM_S01.getGeneralInfo", input);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String fileName = "IT_Contact_" + format.format(new Date()) + ".csv";
		result.setContentType(ContentType.OCTET_STREAM);
		result.setFileName(fileName);
		if (list.size() > 0)
		{
			StringBuilder content = new StringBuilder();
			buildHeader(content);
			buildContent(content, list);
			ByteArrayInputStream is = new ByteArrayInputStream(content.toString().getBytes());
			result.setInputStream(is);
			return result;
		}
		else
		{
			throw new ReportException(globalMessageResource.getMessage("info.ERR2SC002"));
		}
	}

}
