package nttdm.bsys.b_csb.blogic;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_csb.dto.Debit_info_bean;
import nttdm.bsys.common.bean.G_CSB_P01_CheckInput;
import nttdm.bsys.common.bean.G_CSB_P01_CheckOutput;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_CSB_P01_Check;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessages;

/**
 * 
 * @author gplai
 *
 */
public class B_CSBS02_DBLogic extends CSBBLogic<Map<String, Object>> {

    /**
     * Delete model
     */
    private static final String MODEL_DELETE = "Delete";

    /**
     * UPDATE.BSYS.BCSB.SQL_5.1
     */
    private static final String UPDATE_BSYS_BCSB_SQL_5_1 = 
        "UPDATE.BSYS.BCSB.SQL_5.1";

    /**
     * UPDATE.BSYS.BCSB.CSB_H_REJECT
     */
    private static final String UPDATE_BSYS_BCSB_CSB_H_REJECT = 
        "UPDATE.BSYS.BCSB.CSB_H_REJECT";

    /**
     * SELECT.BSYS.BCSB.SQL_GET_M_GSET_D
     */
    private static final String SELECT_BSYS_BCSB_SQL_GET_M_GSET_D = 
        "SELECT.BSYS.BCSB.SQL_GET_M_GSET_D";

    /**
     * UPDATE.BSYS.BCSB.TOTAL_AMOUNT
     */
    private static final String UPDATE_BSYS_BCSB_TOTAL_AMOUNT =
        "UPDATE.BSYS.BCSB.TOTAL_AMOUNT";

    /**
     * SELECT.BSYS.BCSB.SQL_3.2
     */
    private static final String SELECT_BSYS_BCSB_SQL_3_2 = 
        "SELECT.BSYS.BCSB.SQL_3.2";

    /**
     * CB_AUTO_OFFSET BAC
     */
    private static final String CB_AUTO_OFFSET_BAC = "BAC";

    /**
     * BLogicMessages
     */
    private BLogicMessages messages;
    
    /**
     * BLogicMessages
     */
    private BLogicMessages errors;

    /**
     * @param input inputData
     * @return BLogicResult result
     */
    public BLogicResult execute(Map<String, Object> input) {
        BLogicResult result = new BLogicResult();
        messages = new BLogicMessages();
        errors = new BLogicMessages();
        
        String typeName = "";
        // model Delete or Reject
        String model = CommonUtils.toString(input.get("model"));
        // Delete model
        if (MODEL_DELETE.equals(model)) {
            typeName = "delete";
        } else {
            typeName = "reject";
        }
        
        G_CSB_P01_Check gCsbP01Check = new G_CSB_P01_Check(queryDAO);
        G_CSB_P01_CheckInput gCsbP01CheckInput = new G_CSB_P01_CheckInput();
        gCsbP01CheckInput.setMessageParam1(typeName);
        gCsbP01CheckInput.setMessageParam2("Cash book");
        gCsbP01CheckInput.setMessageParam3("Batch Auto Offset Cash Book");
        G_CSB_P01_CheckOutput gCsbP01CheckOutput = gCsbP01Check.execute(gCsbP01CheckInput);
        boolean resultFlg = gCsbP01CheckOutput.isResultFlg();
        
        if (!resultFlg) {
            String errorMsg = gCsbP01CheckOutput.getMessageContext();
            errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(errorMsg, false));
            result.setResultString("RejectedSuccess");
        } else {
            // idRef
            String idRef = CommonUtils.toString(input.get("idRef"));
            
            Map<String, Object> cashBook = queryDAO.executeForMap("B_CSB.getDeleteOrRejectCashBook", idRef);
            String isAlterMsg="N";
            if (cashBook == null || cashBook.isEmpty()) {
                this.messages.add(ActionMessages.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC105", "This Receipt does not exist!"));
                isAlterMsg = "Y";
            } else {
                String status = cashBook.get("PMT_STATUS").toString();
                String isDelete = cashBook.get("IS_DELETED").toString();
                if ("R".equals(status)) {
                    this.messages.add(ActionMessages.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC105", "Receipt: " + idRef.trim()
                            + " has already been Rejected!"));
                    isAlterMsg = "Y";
                } else if ("1".equals(isDelete)) {
                    this.messages.add(ActionMessages.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC105", "Receipt: " + idRef.trim()
                            + " has already been Deleted!"));
                    isAlterMsg = "Y";
                }
            }
 
            if ("Y".equals(isAlterMsg)) {
                HashMap<String, Object> out = new HashMap<String, Object>();
                out.put("idRef", input.get("idRef"));
                out.put("isAlterMsg", isAlterMsg);
                result.setResultObject(out);
                result.setResultString("faild");
                result.setErrors(errors);
                result.setMessages(messages);
                return result;
            }

            // isClosed
            String isClosed = CommonUtils.toString(input.get("isClosed"));

            Map<String, Object> parameter = new HashMap<String, Object>();
            BillingSystemUserValueObject uvo = (BillingSystemUserValueObject) 
                input.get("uvo");
            /**
             * Audit Trail
             */
            //Modify Status
            String status="";
            if(isClosed.equals("0")){
                status="OPEN";
            }else{
                status="CLOSED";
            }
            Integer idAudit = CommonUtils.auditTrailBegin(uvo.getId_user(),
                    BillingSystemConstants.MODULE_B,
                    BillingSystemConstants.SUB_MODULE_B_CSB, idRef, status,
                    BillingSystemConstants.AUDIT_TRAIL_DELETED);
            // set value to parameter
            parameter.put("idRef", idRef);
            parameter.put("idLogin", uvo.getId_user());
            parameter.put("idAudit", idAudit);
            try {
                // Delete model
                if (MODEL_DELETE.equals(model)) {
                    // delete header
                    updateDAO.execute(UPDATE_BSYS_BCSB_SQL_5_1, parameter);
                } else {
                    // Reject model
                    //Remark
                    String remark = CommonUtils.toString(input.get("remark"));
                    //rejectionDate
                    String rejectionDate = CommonUtils.toString(
                         input.get("rejectionDate"));
                    //set to paramter
                    parameter.put("remark", remark);
                    parameter.put("rejectionDate", rejectionDate);
                    // Update T_CSB_H table
                    updateDAO.execute(UPDATE_BSYS_BCSB_CSB_H_REJECT, parameter);
                }

                // M_GSET_D ID_SET
                String setValue = CB_AUTO_OFFSET_BAC;
                // get M_GSET_D data
                List<Map<String, Object>> listMGsetD = queryDAO.executeForMapList(
                        SELECT_BSYS_BCSB_SQL_GET_M_GSET_D, setValue);
                if (!CommonUtils.isEmpty(listMGsetD)) {
                    // AMT_RECEIVED
                    BigDecimal amtReceived = new BigDecimal("-".concat(
                            CommonUtils.toString(input.get("amtReceived"))));
                    // ID_BILL_ACCOUNT
                    String idBillAccount = CommonUtils.toString(
                        input.get("idBillAccount"));
                    Map<String, Object> parameterTBach = 
                        new HashMap<String, Object>();
                    // ID_LOGIN
                    parameterTBach.put("idLogin", uvo.getId_user());
                    // ID_AUDIT
                    parameterTBach.put("idAudit", idAudit);
                    // amtReceived
                    parameterTBach.put("amtReceived", amtReceived);
                    // ID_BILL_ACCOUNT
                    parameterTBach.put("idBillAccount", idBillAccount.trim());
                    // Update T_BAC_H Total Amount Due
                    updateDAO.execute(UPDATE_BSYS_BCSB_TOTAL_AMOUNT, 
                        parameterTBach);
                }

                // delete details
                List<Debit_info_bean> debitInfos = queryDAO.executeForObjectList(
                        SELECT_BSYS_BCSB_SQL_3_2, parameter);
                //updateDAO.execute("UPDATE.BSYS.BCSB.SQL_4.8.1", parameter);
                for (Debit_info_bean info : debitInfos) {
                    parameter.put("idRef", CommonUtils.
                            toString(info.getID_REF()).trim());
                    parameter.put("amtPaid", info.getAMT_PAID());
                    updateDAO.execute("UPDATE.BSYS.BCSB.SQL_4.8.2", parameter);
                    // update is_settled refer to #619
                    updateDAO.execute("UPDATE.BSYS.BCSB.SETTLED", parameter);
                }
                // End Audit Trail
                CommonUtils.auditTrailEnd(idAudit);
                // Delete model
                if (MODEL_DELETE.equals(model)) {
                    this.messages.add(ActionMessages.GLOBAL_MESSAGE, 
                            new BLogicMessage("info.ERR2SC005"));
                    result.setResultString("delSuccess");
                }else{
                    // Reject model
                    this.messages.add(ActionMessages.GLOBAL_MESSAGE, 
                            new BLogicMessage("info.ERR2SC003"));
                    result.setResultString("RejectedSuccess");
                }
            } catch (Exception e) {
                // Delete model
                if (MODEL_DELETE.equals(model)) {
                    this.errors.add(ActionErrors.GLOBAL_MESSAGE, 
                            new BLogicMessage("info.ERR2SC006"));
                } else {
                    this.errors.add(ActionErrors.GLOBAL_MESSAGE, 
                            new BLogicMessage("info.ERR2SC004"));
                }
            }
        }
        HashMap<String, Object> out = new HashMap<String, Object>();
        out.put("idRef", input.get("idRef"));
        result.setResultObject(out);
        
        result.setErrors(errors);
        result.setMessages(messages);
        return result;
    }
}