package nttdm.bsys.m_bnk.blogic;

import java.util.List;
import java.util.Map;

import org.apache.struts.Globals;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;

import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_bnk.dto.M_BNKS02edInput;
import nttdm.bsys.m_bnk.dto.M_BNKS02neInput;

public class M_BNKS02edBlogic extends AbstractM_BNKS02ed {

    private static final String SQL_SET_BNK_BANKINFO = "UPDATE.M_BNK.SQL001";
    private static final String SQL_UPT_BNK_BANK_ADRA = "UPDATE.M_BNK.SQL002";
    private static final String SQL_UPT_BNK_BANK_ADCA = "UPDATE.M_BNK.SQL003";
    private static final String SQL_SET_BNK_BANK_ADRA = "INSERT.M_BNK.SQL002";
    private static final String SQL_SET_BNK_BANK_ADCA = "INSERT.M_BNK.SQL003";
    private static final String SQL_SET_BNK_BANK_CTPC = "INSERT.M_BNK.SQL004";
    private static final String SQL_SET_BNK_BANK_CTBC = "INSERT.M_BNK.SQL005";
    private static final String SQL_SET_BNK_BANK_CTOC = "INSERT.M_BNK.SQL006";
    private static final String SQL_UPT_BNK_BANK_CTPC = "UPDATE.M_BNK.SQL004";
    private static final String SQL_UPT_BNK_BANK_CTBC = "UPDATE.M_BNK.SQL005";
    private static final String SQL_UPT_BNK_BANK_CTOC = "UPDATE.M_BNK.SQL006";
    private static final String SQL_UPT_BNK_BANK_CONT = "SELECT.M_BNK.SQL010";
    private static final String SQL_GET_BNK_BANK_ADDR = "SELECT.M_BNK.SQL011"; 
    
	private static final String ERR2SC003 = "errors.ERR2SC003";
	private static final String ERR2SC004 = "errors.ERR2SC004";
	
	
	public BLogicResult execute(M_BNKS02edInput param) {
		BLogicResult result = new BLogicResult();
		BLogicMessages message=new BLogicMessages();
		
        try {
            //Modify Reference And Status
            String idbank=param.getLblidbank();
            String bankfullname=param.getLblBankFullName();
            String branchcode=param.getTbxBranchCode();
            String reference=idbank+":"+bankfullname+branchcode;
            Integer idAudit = CommonUtils.auditTrailBegin(param.getUvo().getId_user(),
                    BillingSystemConstants.MODULE_M,
                    BillingSystemConstants.SUB_MODULE_M_BNK, 
                    reference, 
                    null,
                    BillingSystemConstants.AUDIT_TRAIL_EDITED);
            param.setIdAudit(idAudit);
            // Update Bank Information
            updateDAO.execute(SQL_SET_BNK_BANKINFO, param);

            M_BNKS02neInput neParam = new M_BNKS02neInput();
            neParam.setId_maxbank(param.getLblidbank());
            neParam.setIdAudit(idAudit);
            neParam.setLblidlogin(param.getLblidlogin());
            
            boolean isRAexist = false;
            boolean isCAexist = false;
            List<Map<String, Object>> addressList = queryDAO.executeForObjectList(SQL_GET_BNK_BANK_ADDR, param.getLblidbank());
            for (Map<String, Object> address : addressList) {
                if ("RA".equalsIgnoreCase(CommonUtils.toString(address.get("BK_ADR_TYPE")))) {
                    isRAexist = true;
                }
                if ("CA".equalsIgnoreCase(CommonUtils.toString(address.get("BK_ADR_TYPE")))) {
                    isCAexist = true;
                }
            }

            // if Registered Address no exist, then INSERT
            if (!isRAexist) {
                updateDAO.execute(SQL_SET_BNK_BANK_ADCA, neParam);
            }
            
            // if Correspondence Address no exist, then INSERT
            if (!isCAexist) {
                updateDAO.execute(SQL_SET_BNK_BANK_ADRA, neParam);
            }
            
            updateDAO.execute(SQL_UPT_BNK_BANK_ADCA, param);
            updateDAO.execute(SQL_UPT_BNK_BANK_ADRA, param);
            
            boolean isPCexist = false;
            boolean isBCexist = false;
            boolean isOCexist = false;
            // Get existing All Contact
            List<Map<String, Object>> contactList = queryDAO.executeForObjectList(SQL_UPT_BNK_BANK_CONT, param.getLblidbank());
            for (Map<String, Object> contact : contactList) {
                if ("PC".equalsIgnoreCase(CommonUtils.toString(contact.get("BK_CTC_TYPE")))) {
                    isPCexist = true;
                }
                if ("BC".equalsIgnoreCase(CommonUtils.toString(contact.get("BK_CTC_TYPE")))) {
                    isBCexist = true;
                }
                if ("OC".equalsIgnoreCase(CommonUtils.toString(contact.get("BK_CTC_TYPE")))) {
                    isOCexist = true;
                }
            }
            // if Primary Contact no exist, then INSERT
            if (!isPCexist) {
                updateDAO.execute(SQL_SET_BNK_BANK_CTPC, neParam);
            }
            
            // if Secondary Contact no exist, then INSERT
            if (!isBCexist) {
                updateDAO.execute(SQL_SET_BNK_BANK_CTBC, neParam);
            }

            // if Other Contact no exist, then INSERT
            if (!isOCexist) {
                updateDAO.execute(SQL_SET_BNK_BANK_CTOC, neParam);
            }
            
            updateDAO.execute(SQL_UPT_BNK_BANK_CTPC, param);
            updateDAO.execute(SQL_UPT_BNK_BANK_CTBC, param);
            updateDAO.execute(SQL_UPT_BNK_BANK_CTOC, param);
            
            CommonUtils.auditTrailEnd(idAudit);// End Audit Trail
            
            String idref = "";
            message.add(Globals.MESSAGE_KEY, new BLogicMessage(ERR2SC003, idref));
            result.setResultString("success");
        } catch (Exception ex_update) {
            String idref = "";
            message.add(Globals.MESSAGE_KEY, new BLogicMessage(ERR2SC004, idref));
            result.setResultString("error");
        }
        
        result.setMessages(message);
        return result;
    }
}
