/**
 * @(#)B_CSBS02_CheckErrorBLogic.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2011/09/19
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_csb.blogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.codelist.CodeBean;
import jp.terasoluna.fw.web.codelist.DBCodeListLoader;
import nttdm.bsys.b_csb.dto.Debit_info_bean;
import nttdm.bsys.b_csb.dto.PaymentRefDetail;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.util.ApplicationContextProvider;

import org.apache.struts.util.LabelValueBean;
import org.springframework.context.ApplicationContext;

/**
 * check error set value to page
 * @author gplai
 *
 */
public class B_CSBS02_CheckErrorBLogic extends CSBBLogic<Map<String, Object>> {

    /**
     * CB_AUTO_OFFSET CST
     */
    private static final String CB_AUTO_OFFSET_CST = "CST";

    /**
     * CB_AUTO_OFFSET BAC
     */
    private static final String CB_AUTO_OFFSET_BAC = "BAC";
    
    /**
     * Other Payer value -1
     */
    private static final String OTHER_PAYER_VALUE = "-1";
    
    /**
     * @param input input data 
     * @return BLogicResult result value
     */
	public BLogicResult execute(Map<String, Object> input) {
        BLogicResult result = new BLogicResult();
        BLogicMessages messages = new BLogicMessages();
        BLogicMessages errors = new BLogicMessages();
        Map<String, Object> output = new HashMap<String, Object>();
        
        // pattern BAC or CST
        String pattern = CommonUtils.toString(input.get("pattern"));
        // CST pattern
        if (CB_AUTO_OFFSET_CST.equals(pattern)) {
            // pmtMethod
            String pmtMethod = CommonUtils.toString(input.get("pmtMethod"));
            // payer
            String payer = CommonUtils.toString(input.get("payer"));
            if (CommonUtils.isEmpty(pmtMethod)) {
                input.put("debitInfos" , new ArrayList<Debit_info_bean>());
                input.put("cbPayer" , null);
            } else {
                if (!"NT".equals(pmtMethod ) && CommonUtils.isEmpty(payer)) {
                    input.put("debitInfos" , new ArrayList<Debit_info_bean>());
                }
                List<LabelValueBean> cbPayer = null;
                if ("NT".equals(pmtMethod)) {
                    cbPayer = queryDAO.executeForObjectList("SELECT.BSYS.BCSB.SQL_2.1.1", null);
                    // add Other Payers data
                    cbPayer.add(0, new LabelValueBean("Other Payers", OTHER_PAYER_VALUE));
                } else {
                    // RetrieveCboPayer
                    cbPayer = queryDAO.executeForObjectList("SELECT.BSYS.BCSB.SQL_2.1.2", null);
                }
                output.put("cbPayer", cbPayer);
            }
        }else{
            // payer
            String payer = CommonUtils.toString(input.get("payer"));
            // idBillAccount
            String idBillAccount = CommonUtils.toString(
                    input.get("idBillAccount"));
            
            if (!CommonUtils.isEmpty(payer)&&!CommonUtils.isEmpty(idBillAccount)) {
                Map<String, Object> parameter = new HashMap<String, Object>();
                parameter.put("idCust", payer);
                // Bill Account No
                List<LabelValueBean> cboBillAccountNo = 
                    queryDAO.executeForObjectList(
                            "SELECT.BSYS.BCSB.GET_BILL_ACC_NO", parameter);
                // Billing Account No. combox data
                output.put("cboBillAccountNo", cboBillAccountNo);
            }
            
            if (CommonUtils.isEmpty(payer) || 
                    CommonUtils.isEmpty(idBillAccount)) {
                input.put("debitInfos" , new ArrayList<Debit_info_bean>());
            }
            
            if (CommonUtils.isEmpty(payer)){
                input.put("cboBillAccountNo" , null);
            }
            
            // Payer combox data
            List<LabelValueBean> cbPayer = queryDAO.executeForObjectList(
                    "SELECT.BSYS.BCSB.BAC_PLAYER", null);
            output.put("cbPayer", cbPayer);
            
            String receiptNo = CommonUtils.toString(input.get("receiptNo")).trim();
            if (!CommonUtils.isEmpty(receiptNo)) {
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("idRef", paddingRightSpace(receiptNo, 20));
                Map<String, Object> refundInfo = queryDAO.executeForMap("B_CSB.getRefundTotalForReceipt", param);
                output.put("refundInfo", refundInfo);
            }

        }
        
        // input data to output data
        this.copyParam(input, output);
        // pmt method change
		ApplicationContext context = ApplicationContextProvider
				.getApplicationContext();
		DBCodeListLoader dbCodeListLoader = (DBCodeListLoader) context
				.getBean("PAYMENT_REF_HEADER");
		CodeBean[] codeBeans = dbCodeListLoader.getCodeBeans();
		output.put("labPaymentRef1", codeBeans[0].getName());
		output.put("labPaymentRef2", codeBeans[1].getName());
        
		// pmtMethod
        String pmtMethod = CommonUtils.toString(input.get("pmtMethod"));
        if(CommonUtils.isEmpty(pmtMethod)) {
            output.put("paymentRef1De", "");
            output.put("paymentRef2De", "");
        } else {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("seq", "1");
            param.put("paymentMethod", pmtMethod);
            Map<String, Object> paymentRefDe = this.queryDAO.executeForMap("SELECT.BSYS.BCSB.GET_PAYMENT_REF_DE", param);
            output.put("paymentRef1De", paymentRefDe.get("VALUE"));
            
            param.put("seq", "2");
            paymentRefDe = this.queryDAO.executeForMap("SELECT.BSYS.BCSB.GET_PAYMENT_REF_DE", param);
            output.put("paymentRef2De", paymentRefDe.get("VALUE"));
        }
        
        // get bank info
        List<LabelValueBean> cbBankAcc = queryDAO.executeForObjectList(
                "SELECT.BSYS.BCSB.SQL003", null);
        output.put("cbBankAcc", cbBankAcc);
        
        // Get all payment ref detail
        List<PaymentRefDetail> paymentRefDetails1 = this.queryDAO
                .executeForObjectList("SELECT.BSYS.BCSB.GET_ALL_PAYMENT_REF_DE1", null);
        List<PaymentRefDetail> paymentRefDetails2 = this.queryDAO
                .executeForObjectList("SELECT.BSYS.BCSB.GET_ALL_PAYMENT_REF_DE2", null);

        output.put("paymentRefDetails1", paymentRefDetails1);
        output.put("paymentRefDetails2", paymentRefDetails2);
        
        //
        result.setResultObject(output);
        result.setResultString("success");
        result.setErrors(errors);
        result.setMessages(messages);
        return result;
    }

    /**
     * input data to output data
     * 
     * @param input
     *            input data
     * @param output
     *            ouput data
     */
    private void copyParam(Map<String, Object> input, 
            Map<String, Object> output) {
        Set<String> keys = input.keySet();
        for (String key : keys) {
            output.put(key, input.get(key));
        }
    }
    
    /**
     * padding right space
     * @param str
     * @param len
     * @return
     */
    private static String paddingRightSpace(String str, int len) {
        StringBuffer sb = new StringBuffer();
        str = CommonUtils.toString(str);
        sb.append(str);
        for(int i=str.length();i<len;i++) {
            sb.append(" ");
        }
        return sb.toString();
    }
}
