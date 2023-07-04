package nttdm.bsys.m_eml.blogic;

import org.apache.struts.Globals;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_eml.dto.M_EML02Input;
import nttdm.bsys.m_eml.dto.M_EML02Output;

public class M_EMLS02SaveBLogic extends AbstractM_EMLS02SaveBLogic{

	public BLogicResult execute(M_EML02Input input) {
		BLogicResult result = new BLogicResult();
		BLogicMessages errors = new BLogicMessages();
		BLogicMessages messages = new BLogicMessages();
		M_EML02Output out = new M_EML02Output();
		input.setId_login(input.getUvo().getId_user());
		if("New".equals(input.getModelFlg())){
			Integer id_audit = CommonUtils.auditTrailBegin(input.getUvo().getId_user(), 
					BillingSystemConstants.MODULE_M, BillingSystemConstants.SUB_MODULE_M_EML, 
					null, null, BillingSystemConstants.AUDIT_TRAIL_CREATED);
			input.setId_audit(id_audit);
			String code = input.getCode();
			String codeCount = queryDAO.executeForObject("SELECT.M_EML.SQL004", code, String.class);
			if(Integer.parseInt(codeCount)>0){
				errors.add(Globals.MESSAGE_KEY, new BLogicMessage("errors.ERR1SC110", new Object[] {code}));
				result.setErrors(errors);
				result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
			}else{
				updateDAO.execute("INSERT.M_EML.SQL001", input);
				messages.add(Globals.MESSAGE_KEY, new BLogicMessage("info.ERR2SC003"));
				out.setIsSaveFlg("1");
				result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
			}
		}else{
			updateDAO.execute("UPDATE.M_EML.SQL003", input);
			out.setIsSaveFlg("1");
			messages.add(Globals.MESSAGE_KEY, new BLogicMessage("info.ERR2SC003"));
			result.setResultString("success_edit");
		}
		
		result.setResultObject(out);
		result.setMessages(messages);
		return result;
	}

}
