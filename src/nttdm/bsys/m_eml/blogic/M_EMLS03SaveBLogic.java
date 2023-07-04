package nttdm.bsys.m_eml.blogic;

import org.apache.struts.Globals;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.m_eml.dto.M_EML02Input;
import nttdm.bsys.m_eml.dto.M_EML02Output;

public class M_EMLS03SaveBLogic extends AbstractM_EMLS03SaveBLogic{

	public BLogicResult execute(M_EML02Input input) {
		BLogicResult result = new BLogicResult();
		BLogicMessages messages = new BLogicMessages();
		M_EML02Output out = new M_EML02Output();
		input.setId_login(input.getUvo().getId_user());
		updateDAO.execute("UPDATE.M_EML.SQL003", input);
		out.setIsSaveFlg("1");
		result.setResultObject(out);
		messages.add(Globals.MESSAGE_KEY, new BLogicMessage("info.ERR2SC003"));
		result.setMessages(messages);
		result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
		return result;
	}

}
