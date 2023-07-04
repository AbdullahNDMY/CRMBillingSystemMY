package nttdm.bsys.m_bnk.blogic;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessages;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_bnk.bean.M_BNK_bankbean;
import nttdm.bsys.m_bnk.dto.M_BNKS02deInput;


public class M_BNKS02deBlogic extends AbstractM_BNKS02de {
	
	public BLogicResult execute(M_BNKS02deInput param) {
		BLogicResult result = new BLogicResult();
		BLogicMessages message = new BLogicMessages();
		BLogicMessages error = new BLogicMessages();
		try {
		    String idbank=param.getLblidbank();
		    M_BNK_bankbean bankbeaninfo=queryDAO.executeForObject("SELECT.M_BNK.SQL019", param, M_BNK_bankbean.class);
		    String reference=idbank+":"+bankbeaninfo.getBANK_FULL_NAME()+bankbeaninfo.getBRANCH_CODE();
			BillingSystemUserValueObject uvo = (BillingSystemUserValueObject)param.getUvo();
			Integer idAudit = CommonUtils.auditTrailBegin(uvo.getId_user(), BillingSystemConstants.MODULE_M, 
					BillingSystemConstants.SUB_MODULE_M_BNK, 
					reference, null, BillingSystemConstants.AUDIT_TRAIL_DELETED);
			param.setIdAudit(idAudit);

			if(param.getLblidlogin() == null){
				param.setLblidlogin(uvo.getId_user());
			}

			updateDAO.execute("UPDATE.M_BNK.SQL008", param);
		
			CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
			message.add(ActionMessages.GLOBAL_MESSAGE, new BLogicMessage("info.ERR2SC005"));
			result.setMessages(message);
		}
		catch (Exception ex_de)	{
			error.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("info.ERR2SC006"));
			result.setErrors(error);
		}
		result.setResultString("success");
        return result;
        
	}	

}
