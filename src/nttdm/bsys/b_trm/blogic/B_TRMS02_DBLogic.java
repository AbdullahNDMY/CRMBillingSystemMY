package nttdm.bsys.b_trm.blogic;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_trm.bean.DebitInfoBean;
import nttdm.bsys.common.bean.G_CSB_P01_CheckInput;
import nttdm.bsys.common.bean.G_CSB_P01_CheckOutput;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_CSB_P01_Check;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessages;

public class B_TRMS02_DBLogic extends TRMBLogic<Map<String, Object>> {
	private static final String DELETE_T_TRM_D = "UPDATE.B_TRM.SQL004";
	private static final String UPDATE_PAID_AMOUNT = "UPDATE.B_TRM.SQL008";
	private static final String UPDATE_IS_SETTLED = "UPDATE.B_TRM.SQL010";
	private static final String UPDATE_TOTAL_PAYMENT = "UPDATE.B_TRM.SQL002";
	
	private BLogicMessages messages;
	private BLogicMessages errors;

	public BLogicResult execute(Map<String, Object> input) {
		BLogicResult result = new BLogicResult();
		messages = new BLogicMessages();
		errors = new BLogicMessages();
		
		G_CSB_P01_Check gCsbP01Check = new G_CSB_P01_Check(queryDAO);
        G_CSB_P01_CheckInput gCsbP01CheckInput = new G_CSB_P01_CheckInput();
        gCsbP01CheckInput.setMessageParam1("delete");
        gCsbP01CheckInput.setMessageParam2("Transaction Matching");
        gCsbP01CheckInput.setMessageParam3("Batch Auto Offset Cash Book");
        G_CSB_P01_CheckOutput gCsbP01CheckOutput = gCsbP01Check.execute(gCsbP01CheckInput);
        boolean resultFlg = gCsbP01CheckOutput.isResultFlg();
        
        if (!resultFlg) {
            String errorMsg = gCsbP01CheckOutput.getMessageContext();
            input.put("lastMsg", errorMsg);
            result.setResultString("failure");
        } else {
            Map<String, Object> parameter = new HashMap<String, Object>();
            BillingSystemUserValueObject uvo = (BillingSystemUserValueObject) input.get("uvo");
            parameter.put("idLogin", uvo.getId_user());
            this.copyProperties(input, parameter);
            try {
                List<DebitInfoBean> debitInfos = (List<DebitInfoBean>) input.get("debitInfos");
                parameter.put("creditRef", input.get("creditRef"));
                String isAlterMsg = "N";

                // get all current matchings of credit note
                List<Map<String, Object>> trms = queryDAO.executeForMapList("TRM.getMatchsOfCreditNo", input.get("creditRef"));
                if (CommonUtils.isEmpty(trms)) {
                    this.messages.add(ActionMessages.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC105", "Transaction matching for credit note: "
                            + input.get("creditRef") + " already deleted"));
                    isAlterMsg = "Y";
                }

                if ("Y".equals(isAlterMsg)) {
                    input.put("isAlterMsg", isAlterMsg);
                    input.put("action", "view");
                    result.setResultObject(input);
                    result.setResultString("deleteError");
                    result.setErrors(errors);
                    result.setMessages(messages);
                    return result;
                }
                
                BigDecimal payment = BigDecimal.ZERO;
                /**
                 * Audit Trail
                 */
                String debitRefList = "";
                for(DebitInfoBean debitInfo : debitInfos) {
                    debitRefList += ("," + debitInfo.getGdcDebitReference());
                }
                Integer idAudit = CommonUtils.auditTrailBegin(uvo.getId_user(), BillingSystemConstants.MODULE_B, 
                        BillingSystemConstants.SUB_MODULE_B_TRM, CommonUtils.toString(input.get("creditRef")) + ":"
                        + debitRefList.substring(1), null, BillingSystemConstants.AUDIT_TRAIL_DELETED);
                parameter.put("idAudit", idAudit);
                
                Map<String, Object> creditInfo = queryDAO.executeForMap("TRM.getTrmBillInfo", input.get("creditRef"));
                BigDecimal paidAmt = new BigDecimal(creditInfo.get("PAID_AMOUNT").toString());

                for (Map<String, Object> trm : trms) {
                    payment = new BigDecimal(trm.get("AMT_PAID").toString());
                    parameter.put("debitRef", trm.get("DEBIT_REF"));
                    parameter.put("matchID", trm.get("MATCH_ID"));
                    //delete T_TRM_D
                    updateDAO.execute(DELETE_T_TRM_D, parameter);
                    
                    //update T_BIL_H.PAID_AMOUND
                    parameter.put("newPaidAmt", payment.negate());
                    updateDAO.execute(UPDATE_PAID_AMOUNT, parameter);
                    
                    //update T_BIL_H.IS_SETTLED
                    updateDAO.execute(UPDATE_IS_SETTLED, parameter);
                    paidAmt = paidAmt.subtract(payment);
                }

                //update total
                parameter.put("totalPayment", paidAmt);
                updateDAO.execute(UPDATE_TOTAL_PAYMENT, parameter);
                parameter.put("debitRef", input.get("creditRef"));
                updateDAO.execute(UPDATE_IS_SETTLED, parameter);
                CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
                
                messages.add(Globals.MESSAGE_KEY, new BLogicMessage("info.ERR2SC005"));
                result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
            }
            catch(Exception e) {
                errors.add(Globals.MESSAGE_KEY, new BLogicMessage("info.ERR2SC006"));
                result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
            }
        }
		input.put("action", "view");
		result.setResultObject(input);
		result.setErrors(errors);
		result.setMessages(messages);
		return result;
	}
}
