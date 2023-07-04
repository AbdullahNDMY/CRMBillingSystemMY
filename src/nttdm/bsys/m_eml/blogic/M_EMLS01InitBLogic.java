package nttdm.bsys.m_eml.blogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts.Globals;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.m_eml.bean.MailModule;
import nttdm.bsys.m_eml.bean.MailTemplate;
import nttdm.bsys.m_eml.dto.M_EMLInput;
import nttdm.bsys.m_eml.dto.M_EMLOutput;

public class M_EMLS01InitBLogic extends AbstractM_EMLBLogic {

	private QueryDAO queryDAO;

	public BLogicResult execute(M_EMLInput input) {

		BLogicResult result = new BLogicResult();
		BLogicMessages messages = new BLogicMessages();
		result.setMessages(messages);
		M_EMLOutput out = new M_EMLOutput();
		List<MailModule> mailModule = new ArrayList<MailModule>();
		List<MailTemplate> mailTemplate = new ArrayList<MailTemplate>();
		Map<String, Object> serverInfo = queryDAO.executeForMap("SELECT.M_EML.SQL001",null);
		mailModule = queryDAO.executeForObjectList("SELECT.M_EML.SQL002",null);
		mailTemplate = queryDAO.executeForObjectList("SELECT.M_EML.SQL003", null);
		out.setPassword((String)serverInfo.get("MAIL_SERVER_PASSWORD"));
		out.setPortNumber((String)serverInfo.get("MAIL_SERVER_PORTNO"));
		out.setServerName((String)serverInfo.get("MAIL_SERVER_NAME"));
		out.setUserName((String)serverInfo.get("MAIL_SERVER_USERNAME"));
		out.setId_com(String.valueOf(serverInfo.get("ID_COM")));
		out.setAttachmentFileSize(Integer.parseInt(String.valueOf(serverInfo.get("ATTACHMENT_FILESIZE"))));
		out.setMailModule(mailModule);
		out.setMailTemplate(mailTemplate);
		// #252 Batch Email Billing Document: generate PDF / email CT 11052017 ST
		out.setSsltls((String)serverInfo.get("MAIL_SERVER_SSLTLS"));
		// #252 Batch Email Billing Document: generate PDF / email CT 11052017 EN
		if (input.getMessage() != null) {
			if (!input.getMessage().equals("")) {
				messages.add(Globals.MESSAGE_KEY, new BLogicMessage(input.getMessage()));
			}
		}
		result.setResultObject(out);
		result.setResultString("success");
		return result;
	}

	/**
	 * @return the queryDAO
	 */
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	/**
	 * @param queryDAO
	 *            the queryDAO to set
	 */
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

}
