/*
 * @(#)RP_B_BAC_S02_06_01BLogic.java
 *
 *
 */
package nttdm.bsys.b_bac.blogic;


import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.LabelValueBean;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_bac.dto.RP_B_BAC_S02_06_02Input;
import nttdm.bsys.b_bac.dto.RP_B_BAC_S02_06_02Output;
import nttdm.bsys.common.bean.T_BAC_D;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;

/*
**
* BusinessLogic class of B-BAC Edit init.
* 
* @author khungl0ng
*/
public class RP_B_BAC_S02_06_02BLogic extends AbstractRP_B_BAC_S02_06_02BLogic {
    
    private BLogicMessages msgs;
    private BLogicMessages errors;
    
    /**
     * B-BAC Billing Reference Edit init.
     * 
     * @param param
     * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
     */
    public BLogicResult execute(RP_B_BAC_S02_06_02Input param) {
        BLogicResult result = new BLogicResult();
        msgs = new BLogicMessages();
        errors = new BLogicMessages();
        RP_B_BAC_S02_06_02Output outputDTO = new RP_B_BAC_S02_06_02Output();
        // get idBillAccount
        String idBillAccount = param.getIdBillAccount();
        //get idCustPlan
        String idCustPlan = param.getIdCustPlan();
        //get frompage
        String frompage = param.getFromPage();
        
        String trem = param.getInputBillReferenceInfo().getTerm();
        String tremDay = param.getInputBillReferenceInfo().getTermDay();
        T_BAC_D inputBillReference = param.getInputBillReferenceInfo();
        if(("".equals(trem)||trem == null)&&!"0".equals(tremDay)){
        	inputBillReference.setTerm(tremDay + " Days");
        }else if(("".equals(trem)||trem == null)&&"0".equals(tremDay)){
        	inputBillReference.setTerm("");
        }
        if (check(param)) {
            // AuditTrailBegin
            Integer idAudit = CommonUtils.auditTrailBegin(param.getUvo().getId_user(), BillingSystemConstants.MODULE_B,
                    BillingSystemConstants.SUB_MODULE_B_BAC, idBillAccount, null, BillingSystemConstants.AUDIT_TRAIL_EDITED);

            // get inputBillReferenceInfo
            HashMap<String, Object> inputData = new HashMap<String, Object>();
            inputData.put("idBillAccount", idBillAccount);
            inputData.put("idBif", param.getInputBillReferenceInfo().getIdBif());
            inputData.put("idQcs", param.getInputBillReferenceInfo().getIdQcs());
            inputData.put("idQuo", param.getInputBillReferenceInfo().getIdQuo());
            inputData.put("custPo", param.getInputBillReferenceInfo().getCustPo());
            inputData.put("acManager", param.getInputBillReferenceInfo().getAcManager());
            inputData.put("term", param.getInputBillReferenceInfo().getTerm());
            inputData.put("termDay", param.getInputBillReferenceInfo().getTermDay());
            inputData.put("idLogin", param.getUvo().getId_user());
            // update Table "T_BAC_D"
            updateDAO.execute("B_BAC.updateBillingReference", inputData);

            // Set DropdownList AC_Manager
            List<LabelValueBean> billRefACManagerList = param.getBillRefACManagerList();
            // Set inputBillReferenceInfo
            T_BAC_D inputBillReferenceInfo = new T_BAC_D();
            inputBillReferenceInfo = param.getInputBillReferenceInfo();

            outputDTO.setIdBillAccount(idBillAccount);
            outputDTO.setBillRefACManagerList(billRefACManagerList);
            outputDTO.setInputBillReferenceInfo(inputBillReferenceInfo);
            outputDTO.setFromPage(frompage);
            outputDTO.setIdCustPlan(idCustPlan);
            result.setResultObject(outputDTO);

            // End Audit Trail
            CommonUtils.auditTrailEnd(idAudit);

            BLogicMessage msg = new BLogicMessage("info.ERR2SC003", new Object[] {});
            msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
            result.setResultString("success");
        }else {
            try {
                BeanUtils.copyProperties(outputDTO, param);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            result.setResultString("fail");
        }
        result.setErrors(errors);
        result.setMessages(msgs);
        return result;
    }
    
    /**
     * Chenk input infp
     * @param param
     * @return checkResutl
     */
    private boolean check(RP_B_BAC_S02_06_02Input param) {
        boolean checkResutl = true;
        // Reference maxlength=20 
        /*if (param.getInputBillReferenceInfo().getIdBif().trim().length() > 20){
            errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.maxlength", new Object[] { "Reference", 20 }));
            checkResutl = false;
        }*/
        // QCS Reference maxlength=20 
        if (param.getInputBillReferenceInfo().getIdQcs().trim().length() > 20) {
            errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.maxlength", new Object[] { "QCS Reference", 20 }));
            checkResutl = false;
        }
        // Quo Reference maxlength=20 
        if (param.getInputBillReferenceInfo().getIdQuo().trim().length() > 20) {
            errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.maxlength", new Object[] { "Quo Reference", 20 }));
            checkResutl = false;
        }
        // Customer PO maxlength=30
        if (param.getInputBillReferenceInfo().getCustPo().trim().length() > 30) {
            errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.maxlength", new Object[] { "Customer PO", 30 }));
            checkResutl = false;
        }
        // A/C Manager maxlength=15
        if (param.getInputBillReferenceInfo().getAcManager().trim().length() > 15) {
            errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.maxlength", new Object[] { "A/C Manager", 15 }));
            checkResutl = false;
        }
        // Term maxlength=15
        if (param.getInputBillReferenceInfo().getTerm().trim().length() > 15) {
            errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.maxlength", new Object[] { "Term", 15 }));
            checkResutl = false;
        }
        if (!Pattern.matches("^\\d{0,9}", param.getInputBillReferenceInfo().getTermDay())) {
            errors.add(Globals.ERROR_KEY, new BLogicMessage(
                    "errors.ERR1SC012", new Object[] {"Term_DAY"}));
            checkResutl = false;
        }
        if (param.getInputBillReferenceInfo().getTermDay().trim().length() > 3) {
            errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.maxlength", new Object[] { "Term_DAY", 3 }));
            checkResutl = false;
        }
        return checkResutl;
    }
}