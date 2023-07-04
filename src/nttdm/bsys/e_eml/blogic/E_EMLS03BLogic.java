package nttdm.bsys.e_eml.blogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.e_eml.bean.E_EML02Bean;
import nttdm.bsys.e_eml.dto.E_EML02Input;
import nttdm.bsys.e_eml.dto.E_EML02Output;

import org.apache.struts.action.ActionMessages;

public class E_EMLS03BLogic implements BLogic<E_EML02Input> {
	private QueryDAO queryDAO;

	public BLogicResult execute(E_EML02Input input) {
		BLogicResult result = new BLogicResult();
		E_EML02Output out = new E_EML02Output();
		// get start index for paging
		Integer startIndex = input.getStartIndex();
		// get the number of row for paging
		BillingSystemSettings bss = new BillingSystemSettings(queryDAO);
		int row = bss.getResultRow();
		List<E_EML02Bean> emailInfos = new ArrayList<E_EML02Bean>();
		// count
		int count = queryDAO.executeForObject("SELECT.E_EML.SQL004", input,
				Integer.class);
		if (count == 0) {
			// info.ERR2SC002
			BLogicMessages msgs = new BLogicMessages();
			BLogicMessage msg = new BLogicMessage("info.ERR2SC002",
					new Object());
			msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
			result.setMessages(msgs);
		} else {
			emailInfos = queryDAO.executeForObjectList("SELECT.E_EML.SQL003",
					input, startIndex, row);
			for (E_EML02Bean emailInfo : emailInfos) {
				Map<String, String> param = new HashMap<String, String>();
				param.put("id_mail", emailInfo.getEmailId());
				List<Map<String, Object>> docFilesMaps = queryDAO
						.executeForMapList("SELECT.E_EML.SQL005", param);
				emailInfo.setId_doc(docFilesMaps);
			}

			out.setTotalCount(count);
			out.setStartIndex(startIndex);
			out.setRow(row);
			out.setEmailInfo(emailInfos);
			result.setResultObject(out);
		}
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
