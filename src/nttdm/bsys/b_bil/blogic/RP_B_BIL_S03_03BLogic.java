/*
 * @(#)RP_B_BIL_S03_03BLogic.java
 *
 *
 */
package nttdm.bsys.b_bil.blogic;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionMessages;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_bil.blogic.AbstractRP_B_BIL_S03_03BLogic;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S03_03Input;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;


/**
 * BusinessLogic class.
 * 
 * @author khungl0ng
 */
public class RP_B_BIL_S03_03BLogic extends AbstractRP_B_BIL_S03_03BLogic {

	/**
	 * 
	 * @param param
	 * @return ビジ�?スロジック�?�実行�?果�?BLogicResultインスタンス。
	 */
	public BLogicResult execute(RP_B_BIL_S03_03Input param) {
		BLogicResult result = new BLogicResult();
		Map<String, Object> inputData = new HashMap<String, Object>();
		inputData.put("idRef", param.getIdRef());
		inputData.put("idLogin", param.getUvo().getId_user());
		inputData.put("dateUpdated", new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
		
		HashMap<String, Object> preBilHData = (HashMap<String, Object>)queryDAO.
            executeForMap("B_BIL.selectPreBillAmount", param.getIdRef());
		/**
		 * Audit Trail 
		 */
		HashMap<String, Object> headerInfo = (HashMap<String, Object>) this.queryDAO.executeForMap("B_BIL.getHeaderInfo", inputData);
		
		//Modify Status
        String status="";
        if(headerInfo.get("IS_CLOSED").toString().equals("0")){
            status="OPEN";
        }
        else{
            status="CLOSED";
        }
		Integer idAudit = CommonUtils.auditTrailBegin(param.getUvo().getId_user(), BillingSystemConstants.MODULE_B, 
				BillingSystemConstants.SUB_MODULE_B_BIL, 
				param.getIdRef(),status,
				BillingSystemConstants.AUDIT_TRAIL_DELETED);		
		inputData.put("idAudit", idAudit);
		
		updateDAO.execute("B_BIL.deleteT_BIL_H", inputData);
		//update T_BAC_H info
		updateTBACH(inputData,preBilHData);
		
		//End Audit Trail
		CommonUtils.auditTrailEnd(idAudit);
		// success
		BLogicMessages msgs = new BLogicMessages();
		BLogicMessage msg = new BLogicMessage("info.ERR2SC005", new Object[]{});
		msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
		result.setMessages(msgs);
		result.setResultString("success");
		return result;
	}
	
	/**
     * update T_BAC_H TOTAL_AMT_DUE
     * 
     * @param inputData inputData
     * @param preBilHData pre T_BIL_H data
     */
    private void updateTBACH(Map<String, Object> inputData , 
            HashMap<String, Object> preBilHData){
        // get M_GSET_D info
        List<Object> mGSetDInfoList = (List<Object>) this.queryDAO.
            executeForObjectList("B_BIL.getMGSetDInfo", null);
        if (!CommonUtils.isEmpty(mGSetDInfoList) && mGSetDInfoList.contains("BAC")) {
            // BILL_TYPE
            String billType = CommonUtils.toString(preBilHData.get("BILL_TYPE"));
            // BILL_AMOUNT
            String pBillAmountStr = CommonUtils.toString(preBilHData.get("BILL_AMOUNT"));
            // BILL_ACCOUNT
            inputData.put("billAcc", preBilHData.get("BILL_ACC"));
            DecimalFormat formatter = new DecimalFormat("0.00");
            BigDecimal cBillAmount = new BigDecimal("0");
            BigDecimal pBillAmount = new BigDecimal(pBillAmountStr);
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