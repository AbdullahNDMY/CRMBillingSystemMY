/**
 * @(#)RP_B_BIL_S02_02BLogic.java
 * Billing System
 * Version 1.00
 * Created 2013/10/26
 * Copyright (c) 2013 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_bil.blogic;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S02_03Output;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S02_04Input;
import nttdm.bsys.common.bean.G_CSB_P01_CheckInput;
import nttdm.bsys.common.bean.G_CSB_P01_CheckOutput;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_CSB_P01_Check;

import org.apache.struts.action.ActionMessages;

/**
 * Re-Active BusinessLogic class.
 * 
 * @author NTTDM
 */
public class RP_B_BIL_S02_04BLogic extends AbstractRP_B_BIL_S02_04BLogic {

    /**
     * Re-Active.
     * 
     * @param param
     * @return ビジネスロジックの実行結果のBLogicResultインスタンス。
     */
    public BLogicResult execute(RP_B_BIL_S02_04Input param) {
        BLogicResult result = new BLogicResult();
        RP_B_BIL_S02_03Output outputDTO = new RP_B_BIL_S02_03Output();

        HashMap<String, Object> inputData = new HashMap<String, Object>();
        inputData.put("idRef", param.getIdRef());
        inputData.put("idLogin", param.getUvo().getId_user());
        inputData.put("dateUpdated", new java.sql.Date(Calendar.getInstance().getTimeInMillis()));

        HashMap<String, Object> preBilHData = (HashMap<String, Object>) queryDAO.executeForMap(
                "B_BIL.selectPreBillAmount", param.getIdRef());

        // BILL_TYPE
        String billType = CommonUtils.toString(preBilHData.get("BILL_TYPE"));
        String typeName = "";
        if ("IN".equals(billType)) {
            // invoice INVNO
            typeName = "Invoice";
        } else if ("DN".equals(billType)) {
            // debit note DBTNO
            typeName = "Debit Note";
        } else {
            // credit note CDTNO
            typeName = "Credit Note";
        }

        G_CSB_P01_Check gCsbP01Check = new G_CSB_P01_Check(queryDAO);
        G_CSB_P01_CheckInput gCsbP01CheckInput = new G_CSB_P01_CheckInput();
        gCsbP01CheckInput.setMessageParam1("delete");
        gCsbP01CheckInput.setMessageParam2(typeName);
        gCsbP01CheckInput.setMessageParam3("Batch Auto Offset Cash Book");
        G_CSB_P01_CheckOutput gCsbP01CheckOutput = gCsbP01Check.execute(gCsbP01CheckInput);
        boolean resultFlg = gCsbP01CheckOutput.isResultFlg();

        if (!resultFlg) {
            String errorMsg = gCsbP01CheckOutput.getMessageContext();
            outputDTO.setLastMsg(errorMsg);
            outputDTO.setIdRef(param.getIdRef());
            outputDTO.setFromPageFlag("BIL");
            outputDTO.setMode("view");
            result.setResultString("fail");
        } else {
            
            Map<String, Object> debitInfo = queryDAO.executeForMap("B_BIL.getBillForCheckByIdref", param.getIdRef());

            if (debitInfo == null || debitInfo.isEmpty() || "0".equals(CommonUtils.toString(debitInfo.get("IS_DELETED")))) {
                BLogicMessages msgs = new BLogicMessages();
                BLogicMessage msg = new BLogicMessage("errors.ERR1SC105", "Invoice: " + param.getIdRef() + " has already been reActivated");
                msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
                result.setErrors(msgs);
                result.setResultString("success");
                result.setResultObject(outputDTO);
                return result;
            }
            
            // Start Audit Trail
            HashMap<String, Object> headerInfo = (HashMap<String, Object>) this.queryDAO.executeForMap(
                    "B_BIL.getHeaderInfo", inputData);
            //Modify Status
            String status="";
            if(headerInfo.get("IS_CLOSED").toString().equals("0")){
                status="OPEN";
            }
            else{
                status="CLOSED";
            }
            Integer idAudit = CommonUtils.auditTrailBegin(param.getUvo().getId_user(), BillingSystemConstants.MODULE_B,
                    BillingSystemConstants.SUB_MODULE_B_BIL, param.getIdRef(), status,
                    BillingSystemConstants.AUDIT_TRAIL_EDITED);
            inputData.put("idAudit", idAudit);

            // update T_BIL_H info
            updateDAO.execute("B_BIL.reActivateT_BIL_H", inputData);
            // update T_BAC_H info
            updateTBACH(inputData, preBilHData);

            // End Audit Trail
            CommonUtils.auditTrailEnd(idAudit);

            // success
            BLogicMessages msgs = new BLogicMessages();
            BLogicMessage msg = new BLogicMessage("info.ERR4SC019", new Object[] { "Record reactivated successfully." });
            msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
            result.setMessages(msgs);
            result.setResultString("success");
        }

        result.setResultObject(outputDTO);
        return result;
    }

    /**
     * update T_BAC_H TOTAL_AMT_DUE
     * 
     * @param inputData
     *            inputData
     * @param preBilHData
     *            pre T_BIL_H data
     */
    private void updateTBACH(HashMap<String, Object> inputData, HashMap<String, Object> preBilHData) {
        // get M_GSET_D info
        List<Object> mGSetDInfoList = (List<Object>) this.queryDAO.executeForObjectList("B_BIL.getMGSetDInfo", null);
        if (!CommonUtils.isEmpty(mGSetDInfoList) && mGSetDInfoList.contains("BAC")) {
            // BILL_TYPE
            String billType = CommonUtils.toString(preBilHData.get("BILL_TYPE"));
            // BILL_AMOUNT
            String cBillAmountStr = CommonUtils.toString(preBilHData.get("BILL_AMOUNT"));
            // BILL_ACCOUNT
            inputData.put("billAcc", preBilHData.get("BILL_ACC"));
            DecimalFormat formatter = new DecimalFormat("0.00");
            BigDecimal cBillAmount = new BigDecimal(cBillAmountStr);
            BigDecimal pBillAmount = new BigDecimal("0");
            if ("CN".equals(billType)) {
                pBillAmount = new BigDecimal("0").subtract(pBillAmount);
                cBillAmount = new BigDecimal("0").subtract(cBillAmount);
            }
            inputData.put("pBillAmount", formatter.format(pBillAmount));
            inputData.put("cBillAmount", formatter.format(cBillAmount));
            updateDAO.execute("B_BIL.updateBAC", inputData);
        }
    }
}