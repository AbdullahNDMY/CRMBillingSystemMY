package nttdm.bsys.m_eml.blogic;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.Globals;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.m_eml.bean.MailModule;
import nttdm.bsys.m_eml.dto.M_EMLInput;
import nttdm.bsys.m_eml.dto.M_EMLOutput;

public class M_EMLS01SaveBLogic extends AbstractM_EMLS01SaveBLogic{

	public BLogicResult execute(M_EMLInput input) {
		BLogicResult result = new BLogicResult();
		BLogicMessages errors = new BLogicMessages();
		/*Integer id_audit = CommonUtils.auditTrailBegin(input.getUvo().getId_user(), 
				BillingSystemConstants.MODULE_M, BillingSystemConstants.SUB_MODULE_M_EML, 
				null, null, BillingSystemConstants.AUDIT_TRAIL_CREATED);*/
		M_EMLOutput out = new M_EMLOutput();
		/*input.setId_audit(id_audit);*/
		List<MailModule> mailModuleList = new ArrayList<MailModule>();
		mailModuleList = input.getMailModule();
		for(MailModule mailMod : mailModuleList){
			/*mailMod.setId_audit(id_audit);*/
			String codeCount = queryDAO.executeForObject("SELECT.M_EML.SQL004", mailMod.getTemCode(), String.class);
			if(Integer.parseInt(codeCount)>0){
				mailMod.setId_login(input.getUvo().getId_user());
				updateDAO.execute("UPDATE.M_EML.SQL002", mailMod);
			}else{
				errors.add(Globals.MESSAGE_KEY, new BLogicMessage("errors.ERR1SC109", new Object[] {mailMod.getTemCode()}));
				result.setErrors(errors);
				result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
				return result;
			}
			
		}
		input.setId_login(input.getUvo().getId_user());
		updateDAO.execute("UPDATE.M_EML.SQL001", input);
		out.setMessage("info.ERR2SC003");
		result.setResultObject(out);
		result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
		return result;
	}

}
