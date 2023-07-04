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
 * 
 * @author gplai
 * combox change blogic
 */
public class B_CSBS02_ADBLogic extends CSBBLogic<Map<String, Object>> {

    /**
     * Payment Method combox change
     */
    private static final String CHANGE_PMT_METHOD = "pmtMethodChange";

    /**
     * Currency combox change
     */
    private static final String CHANGE_CURRENCY = "currencyChange";

    /**
     * Payer combox change
     */
    private static final String CHANGE_PAYER = "payerChange";

    /**
     * Billing Account No combox change
     */
    private static final String BILL_ACCOUNT_NO_CHANGE = "billAccNoChange";

    /**
     * Payment Method NT
     */
    private static final String PAYMENT_METHOD_NT = "NT";

    /**
     * Other Payer value -1
     */
    private static final String OTHER_PAYER_VALUE = "-1";

    /**
     * CB_AUTO_OFFSET CST
     */
    private static final String CB_AUTO_OFFSET_CST = "CST";

    /**
     * CB_AUTO_OFFSET BAC
     */
    private static final String CB_AUTO_OFFSET_BAC = "BAC";

    /**
     * @param input input data 
     * @return BLogicResult result value
     */
	public BLogicResult execute(Map<String, Object> input) {
        BLogicResult result = new BLogicResult();
        BLogicMessages messages = new BLogicMessages();
        BLogicMessages errors = new BLogicMessages();
        Map<String, Object> output = new HashMap<String, Object>();

        // input data to output data
        this.copyParam(input, output);
        // pattern
        String pattern = CommonUtils.toString(input.get("pattern"));
        // CST pattern
        if (CB_AUTO_OFFSET_CST.equals(pattern)) {
            // CST pattern combox change
            cstPatternChange(input, output);
        } else if (CB_AUTO_OFFSET_BAC.equals(pattern)) {
            // CST pattern combox change
            bacPatternChange(input, output);
        }
        // pmt method change
//        String pmtMethod = CommonUtils.toString(output.get("pmtMethod"));
//        if ("CQ".equals(pmtMethod)) {
//            output.put("labPaymentRef1", "Cheque No.");
//            output.put("labPaymentRef2", "Bank-In Slip No.");
//        } else if ("CC".equals(pmtMethod)) {
//            output.put("labPaymentRef1", "Credit Card No.");
//            output.put("labPaymentRef2", "Credit Card Exp.Date");
//        } else if ("GR".equals(pmtMethod)) {
//            output.put("labPaymentRef1", "GIRO Account No.");
//            output.put("labPaymentRef2", "Other GIRO Ref.");
//        } else {
		ApplicationContext context = ApplicationContextProvider
				.getApplicationContext();
		DBCodeListLoader dbCodeListLoader = (DBCodeListLoader) context
				.getBean("PAYMENT_REF_HEADER");
		CodeBean[] codeBeans = dbCodeListLoader.getCodeBeans();
		output.put("labPaymentRef1", codeBeans[0].getName());
		output.put("labPaymentRef2", codeBeans[1].getName());
//        }
        
		// Get all payment ref detail
		List<PaymentRefDetail> paymentRefDetails1 = this.queryDAO
				.executeForObjectList("SELECT.BSYS.BCSB.GET_ALL_PAYMENT_REF_DE1", null);
		List<PaymentRefDetail> paymentRefDetails2 = this.queryDAO
				.executeForObjectList("SELECT.BSYS.BCSB.GET_ALL_PAYMENT_REF_DE2", null);

		output.put("paymentRefDetails1", paymentRefDetails1);
		output.put("paymentRefDetails2", paymentRefDetails2);

        // get bank info
        List<LabelValueBean> cbBankAcc = queryDAO.executeForObjectList(
                "SELECT.BSYS.BCSB.SQL003", null);
        output.put("cbBankAcc", cbBankAcc);
        //
        result.setResultObject(output);
        result.setResultString("success");
        result.setErrors(errors);
        result.setMessages(messages);
        return result;
    }

    /**
     * CST pattern combox change
     * 
     * @param input
     *            input data
     * @param output
     *            output data
     */
    private void cstPatternChange(Map<String, Object> input,
            Map<String, Object> output) {
        Map<String, Object> parameter = new HashMap<String, Object>();
        // actionFlg
        String actionFlg = CommonUtils.toString(input.get("actionFlg"));
        // Payer
        String payer = CommonUtils.toString(input.get("payer"));
        // Currency
        String curCode = CommonUtils.toString(input.get("curCode"));
        // pmt method change
        String pmtMethod = CommonUtils.toString(input.get("pmtMethod"));
        if (CHANGE_PMT_METHOD.equals(actionFlg)) {
            if (CommonUtils.isEmpty(pmtMethod) || 
                    CommonUtils.isNull(pmtMethod)) {
                output.put("payer", null);
                output.put("other", null);
                output.put("cbPayer", new ArrayList<LabelValueBean>());
                output.put("debitInfos", new ArrayList<Debit_info_bean>());
            } else {
                // M_CUST info
                List<LabelValueBean> cbPayer = null;
                if (PAYMENT_METHOD_NT.equals(pmtMethod)) {
                    // RetrieveCboPayerNT
                    cbPayer = queryDAO.executeForObjectList(
                            "SELECT.BSYS.BCSB.SQL_2.1.1", null);
                    if (CommonUtils.isEmpty(cbPayer)) {
                        cbPayer = new ArrayList<LabelValueBean>();
                    } else {
                        parameter.put("curCode", curCode);
                        // get ID_CUST list
                        List<String> idCustList = getIdCustList(cbPayer);
                        parameter.put("idCustList", idCustList);
                        List<Debit_info_bean> debitInfos = 
                            queryDAO.executeForObjectList(
                                        "SELECT.BSYS.BCSB.SQL_2.4", parameter);
                        output.put("debitInfos", debitInfos);
                    }
                    // add Other Payers data
                    cbPayer.add(0, new LabelValueBean("Other Payers",
                            OTHER_PAYER_VALUE));
                } else {
                    // RetrieveCboPayer
                    cbPayer = queryDAO.executeForObjectList(
                            "SELECT.BSYS.BCSB.SQL_2.1.2", null);
                    output.put("debitInfos", new ArrayList<Debit_info_bean>());
                    output.put("other", null);
                }
                output.put("payer", null);
                // Payer Combox
                output.put("cbPayer", cbPayer);
            }
        } else if (CHANGE_PAYER.equals(actionFlg)) {
            // payer change
            if (PAYMENT_METHOD_NT.equals(pmtMethod)) {
                // set DebitInfo where pmt method is NT
                setDebitInfosNT(curCode, output);
            } else {
                if (CommonUtils.isEmpty(payer) || CommonUtils.isNull(payer)) {
                    output.put("debitInfos",
                            new ArrayList<Map<String, Object>>());
                } else {
                    parameter.put("idCust", payer);
                    parameter.put("curCode", curCode);
                    List<Debit_info_bean> debitInfos = 
                        queryDAO.executeForObjectList(
                                "SELECT.BSYS.BCSB.SQL_2.2", parameter);
                    output.put("debitInfos", debitInfos);
                }
                List<LabelValueBean> cbPayer = queryDAO.executeForObjectList("SELECT.BSYS.BCSB.SQL_2.1.2", null);
                output.put("cbPayer", cbPayer);
            }
            output.put("other", null);
        } else if (CHANGE_CURRENCY.equals(actionFlg)) {
            // currency change
            // payer change
            if (PAYMENT_METHOD_NT.equals(pmtMethod)) {
                // set DebitInfo where pmt method is NT
                setDebitInfosNT(curCode, output);
            } else {
                if ((CommonUtils.isEmpty(curCode))
                        && (CommonUtils.isEmpty(payer))) {
                    output.put("debitInfos",
                            new ArrayList<Map<String, Object>>());
                } else {
                    parameter.put("idCust", payer);
                    parameter.put("curCode", curCode);
                    List<Debit_info_bean> debitInfos = 
                        queryDAO.executeForObjectList(
                                "SELECT.BSYS.BCSB.SQL_2.2", parameter);
                    output.put("debitInfos", debitInfos);
                }
                List<LabelValueBean> cbPayer = queryDAO.executeForObjectList("SELECT.BSYS.BCSB.SQL_2.1.2", null);
                output.put("cbPayer", cbPayer);
            }
        }
    }

    /**
     * BAC pattern combox change
     * 
     * @param input
     *            input data
     * @param output
     *            output data
     */
    private void bacPatternChange(Map<String, Object> input,
            Map<String, Object> output) {
        Map<String, Object> parameter = new HashMap<String, Object>();
        // actionFlg
        String actionFlg = CommonUtils.toString(input.get("actionFlg"));
        // Payer
        String payer = CommonUtils.toString(input.get("payer"));
        // Billing Account No
        String idBillAccount = CommonUtils.toString(input.get("idBillAccount"));

        // Payer combox data
        List<LabelValueBean> cbPayer = queryDAO.executeForObjectList(
                "SELECT.BSYS.BCSB.BAC_PLAYER", null);
        output.put("cbPayer", cbPayer);
        
        // Payer combox change
        if (CHANGE_PAYER.equals(actionFlg)) {
            if (CommonUtils.isEmpty(payer) || CommonUtils.isNull(payer)) {
                // Billing Account No. combox
                output.put("cboBillAccountNo", new ArrayList<LabelValueBean>());
            } else {
                // ID_CUST
                parameter.put("idCust", payer.trim());
                // Bill Account No
                List<LabelValueBean> cboBillAccountNo = 
                    queryDAO.executeForObjectList(
                                "SELECT.BSYS.BCSB.GET_BILL_ACC_NO", parameter);
                // Billing Account No. combox data
                output.put("cboBillAccountNo", cboBillAccountNo);
            }
            // Payment Method
            output.put("pmtMethod", null);
            // Currency
            output.put("curCode", null);
            // Debit Infomation
            output.put("debitInfos", new ArrayList<Debit_info_bean>());
        } else if (BILL_ACCOUNT_NO_CHANGE.equals(actionFlg)) {
            // Billing Account No combox change
            // ID_CUST
            parameter.put("idCust", payer.trim());
            // Bill Account No
            List<LabelValueBean> cboBillAccountNo = 
                queryDAO.executeForObjectList(
                        "SELECT.BSYS.BCSB.GET_BILL_ACC_NO", parameter);
            // Billing Account No. combox data
            output.put("cboBillAccountNo", cboBillAccountNo);
            if (CommonUtils.isEmpty(idBillAccount)) {
                // Payment Method
                output.put("pmtMethod", null);
                // Currency
                output.put("curCode", null);
                // Debit Infomation
                output.put("debitInfos", new ArrayList<Debit_info_bean>());
            } else {
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
     * set DebitInfo where pmt method is NT
     * 
     * @param curCode
     *            Currency
     * @param output
     *            output data
     */
    private void setDebitInfosNT(String curCode, Map<String, Object> output) {
        // RetrieveCboPayerNT
        List<LabelValueBean> cbPayer = queryDAO.executeForObjectList(
                "SELECT.BSYS.BCSB.SQL_2.1.1", null);
        if (!CommonUtils.isEmpty(cbPayer)) {
            Map<String, Object> parameter = new HashMap<String, Object>();
            parameter.put("curCode", curCode);
            // get ID_CUST list
            List<String> idCustList = getIdCustList(cbPayer);
            parameter.put("idCustList", idCustList);
            List<Debit_info_bean> debitInfos = queryDAO.executeForObjectList(
                    "SELECT.BSYS.BCSB.SQL_2.4", parameter);
            output.put("debitInfos", debitInfos);
        }else{
            output.put("debitInfos", new ArrayList<Debit_info_bean>());
            cbPayer = new ArrayList<LabelValueBean>();
        }
        // add Other Payers data
        cbPayer.add(0, new LabelValueBean("Other Payers", OTHER_PAYER_VALUE));
        output.put("cbPayer", cbPayer);
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