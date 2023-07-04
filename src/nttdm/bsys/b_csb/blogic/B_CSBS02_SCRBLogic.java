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
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.util.ApplicationContextProvider;

import org.apache.struts.util.LabelValueBean;
import org.springframework.context.ApplicationContext;

/**
 * B_CSB_S02 new page show init data
 * 
 * @author gplai
 * 
 */
public class B_CSBS02_SCRBLogic extends CSBBLogic<Map<String, Object>> {

    /**
     * CB_AUTO_OFFSET CST
     */
    private static final String CB_AUTO_OFFSET_CST = "CST";

    /**
     * CB_AUTO_OFFSET BAC
     */
    private static final String CB_AUTO_OFFSET_BAC = "BAC";

    /**
     * Payment Status Normal
     */
    private static final String PAYMENT_STATUS_NORMAL = "N";
    
    /**
     * Payment Method NT
     */
    private static final String PAYMENT_METHOD_NT = "NT";
    
    /**
     * Other Payer value -1
     */
    private static final String OTHER_PAYER_VALUE = "-1";

    /**
     * BLogicMessages
     */
    private BLogicMessages messages;

    /**
     * BLogicMessages
     */
    private BLogicMessages errors;

    /**
     * @param input input data
     * @return BLogicResult result value
     */
	public BLogicResult execute(Map<String, Object> input) {
        BLogicResult result = new BLogicResult();
        messages = new BLogicMessages();
        errors = new BLogicMessages();
        Map<String, Object> output = new HashMap<String, Object>();

        // reset all value
        Set<String> keys = input.keySet();
        for (String key : keys) {
            output.put(key, null);
        }

        output.put("fromPageFlg", "");
        // get bank info
        List<LabelValueBean> cbBankAcc = queryDAO.executeForObjectList(
                "SELECT.BSYS.BCSB.SQL003", null);
        output.put("cbBankAcc", cbBankAcc);

        // M_GSET_D ID_SET
        String setValue = CB_AUTO_OFFSET_CST;
        // init pattern CST or BAC
        String pattern = CB_AUTO_OFFSET_CST;
        // get M_GSET_D data
        List<Map<String, Object>> listMGsetD = queryDAO.executeForMapList(
                "SELECT.BSYS.BCSB.SQL_GET_M_GSET_D", setValue);
        // CB_AUTO_OFFSET = "CST"
        if (!CommonUtils.isEmpty(listMGsetD)) {
            pattern = CB_AUTO_OFFSET_CST;

            Map<String, Object> parameter = new HashMap<String, Object>();
            String pmtMethod = CommonUtils.toString(input.get("pmtMethod"));
            
            if (CommonUtils.isEmpty(pmtMethod)) {
                BillingSystemSettings systemSetting = new BillingSystemSettings(queryDAO);
                // set default currency
                output.put("curCode", systemSetting.getDefCurrency());
            } else {
                // Currency
                String curCode = CommonUtils.toString(input.get("curCode"));
                String payer = CommonUtils.toString(input.get("payer"));
                // M_CUST info
                List<LabelValueBean> cbPayer = null;
                if (PAYMENT_METHOD_NT.equals(pmtMethod)) {
                    // RetrieveCboPayerNT
                    cbPayer = queryDAO.executeForObjectList("SELECT.BSYS.BCSB.SQL_2.1.1", null);
                    if (CommonUtils.isEmpty(cbPayer)) {
                        cbPayer = new ArrayList<LabelValueBean>();
                    } else {
                        parameter.put("curCode", curCode);
                        // get ID_CUST list
                        List<String> idCustList = getIdCustList(cbPayer);
                        parameter.put("idCustList", idCustList);
                        List<Debit_info_bean> debitInfos = queryDAO.executeForObjectList("SELECT.BSYS.BCSB.SQL_2.4", parameter);
                        output.put("debitInfos", debitInfos);
                    }
                    // add Other Payers data
                    cbPayer.add(0, new LabelValueBean("Other Payers", OTHER_PAYER_VALUE));
                } else {
                    // RetrieveCboPayer
                    cbPayer = queryDAO.executeForObjectList("SELECT.BSYS.BCSB.SQL_2.1.2", null);
                    parameter.put("idCust", payer);
                    parameter.put("curCode", curCode);
                    List<Debit_info_bean> debitInfos = queryDAO.executeForObjectList("SELECT.BSYS.BCSB.SQL_2.2", parameter);
                    output.put("debitInfos", debitInfos);
                }
                // Payer Combox
                output.put("cbPayer", cbPayer);
                output.put("payer", payer);
                output.put("curCode", input.get("curCode"));
                output.put("pmtMethod", input.get("pmtMethod"));
            }
        } else {
            // CB_AUTO_OFFSET = "BAC"
            // M_GSET_D ID_SET
            setValue = CB_AUTO_OFFSET_BAC;
            // get M_GSET_D data
            listMGsetD = queryDAO.executeForMapList(
                    "SELECT.BSYS.BCSB.SQL_GET_M_GSET_D", setValue);
            if (!CommonUtils.isEmpty(listMGsetD)) {
                pattern = CB_AUTO_OFFSET_BAC;
            }
            String payer = CommonUtils.toString(input.get("payer"));
            String idBillAccount = CommonUtils.toString(input.get("idBillAccount"));
            //From B_BIL_S02v
            if (!CommonUtils.isEmpty(payer)&&!CommonUtils.isEmpty(idBillAccount)) {
                Map<String, Object> custInfo = queryDAO.executeForMap("SELECT.BSYS.BCSB.CUST_INFO", payer);
                String payerName = "";
                if (custInfo!=null) {
                    payerName = CommonUtils.toString(custInfo.get("CUST_NAME"));
                }
                output.put("payer", payer);
                output.put("payerName", payerName);
                output.put("idBillAccount", idBillAccount);
                output.put("fromPageFlg", "BIL");
                
                Map<String, Object> parameter = new HashMap<String, Object>();
                parameter.put("idCust", payer);
                // Bill Account No
                List<LabelValueBean> cboBillAccountNo = 
                    queryDAO.executeForObjectList(
                            "SELECT.BSYS.BCSB.GET_BILL_ACC_NO", parameter);
                // Billing Account No. combox data
                output.put("cboBillAccountNo", cboBillAccountNo);
                
                // Billing Account No. combox selected data
                parameter.put("idBillAccount", idBillAccount.trim());
                // get T_BAC_H by Billing Account No.
                Map<String, Object> bacHData = queryDAO.executeForMap(
                        "SELECT.BSYS.BCSB.BAC_H_BY_KEY", parameter);
                // Payment Method
                String pmtMe = bacHData.get("PAYMENT_METHOD").toString();
                output.put("pmtMethod", pmtMe);
                // payment Ref De
                if(null != pmtMe && !"".equals(pmtMe)){
                    Map<String, Object> param = new HashMap<String, Object>();
                    param.put("seq", "1");
                    param.put("paymentMethod", pmtMe);
                    Map<String, Object> paymentRefDe = this.queryDAO.executeForMap("SELECT.BSYS.BCSB.GET_PAYMENT_REF_DE", param);
                    output.put("paymentRef1De", paymentRefDe.get("VALUE"));
                    
                    param.put("seq", "2");
                    paymentRefDe = this.queryDAO.executeForMap("SELECT.BSYS.BCSB.GET_PAYMENT_REF_DE", param);
                    output.put("paymentRef2De", paymentRefDe.get("VALUE"));
                }
                // Currency
                output.put("curCode", bacHData.get("BILL_CURRENCY"));
                // Debit Infomation
                List<Debit_info_bean> debitInfos = 
                    queryDAO.executeForObjectList(
                            "SELECT.BSYS.BCSB.BAC_DEB_INFO", parameter);
                output.put("debitInfos", debitInfos);
            }
        }
        output.put("pattern", pattern);
        // BAC pattern
        if (CB_AUTO_OFFSET_BAC.equals(pattern)) {
            // M_CUST info
            List<LabelValueBean> cbPayer = queryDAO.executeForObjectList(
                    "SELECT.BSYS.BCSB.BAC_PLAYER", null);
            output.put("cbPayer", cbPayer);
        }

        // Payment Status
        output.put("paymentStatus", PAYMENT_STATUS_NORMAL);

        ApplicationContext context = ApplicationContextProvider
			.getApplicationContext();
        DBCodeListLoader dbCodeListLoader = (DBCodeListLoader) context.getBean("PAYMENT_REF_HEADER");
        CodeBean[] codeBeans = dbCodeListLoader.getCodeBeans();
        output.put("labPaymentRef1", codeBeans[0].getName());
        output.put("labPaymentRef2", codeBeans[1].getName());
        
		// Get all payment ref detail
		List<PaymentRefDetail> paymentRefDetails1 = this.queryDAO
				.executeForObjectList("SELECT.BSYS.BCSB.GET_ALL_PAYMENT_REF_DE1", null);
		List<PaymentRefDetail> paymentRefDetails2 = this.queryDAO
				.executeForObjectList("SELECT.BSYS.BCSB.GET_ALL_PAYMENT_REF_DE2", null);

		output.put("paymentRefDetails1", paymentRefDetails1);
		output.put("paymentRefDetails2", paymentRefDetails2);
        
        result.setResultObject(output);
        result.setResultString("success");
        result.setErrors(errors);
        result.setMessages(messages);
        return result;
    }
	
	/**
     * get ID_CUST list
     * 
     * @param listLabelValueBean
     *            ID_CUST list for combox
     * @return ID_CUST list
     */
    private List<String> getIdCustList(
            List<LabelValueBean> listLabelValueBean) {
        List<String> idCustList = new ArrayList<String>();
        for (LabelValueBean labelValueBean : listLabelValueBean) {
            idCustList.add(labelValueBean.getValue().trim());
        }
        return idCustList;
    }
}