package nttdm.bsys.b_csb.blogic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.codelist.CodeBean;
import jp.terasoluna.fw.web.codelist.DBCodeListLoader;
import nttdm.bsys.b_csb.dto.Debit_info_bean;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.util.ApplicationContextProvider;

import org.apache.struts.util.LabelValueBean;
import org.springframework.context.ApplicationContext;

/**
 * 
 * @author gplai
 *
 */
public class B_CSBS02_VBLogic extends CSBBLogic<Map<String, Object>> {

    /**
     * PAID_PRE_INVOICE 0
     */
    private static final String PAID_PRE_INVOICE_0 = "0";
    /**
     * PAID_PRE_INVOICE Yes
     */
    private static final String PAID_PRE_INVOICE_YES = "Yes";
    /**
     * PAID_PRE_INVOICE No
     */
    private static final String PAID_PRE_INVOICE_NO = "No";
    /**
     * OVER_PAID 0
     */
    private static final String OVER_PAID_0 = "0";
    /**
     * OVER_PAID Yes
     */
    private static final String OVER_PAID_YES = "Yes";
    /**
     * OVER_PAID No
     */
    private static final String OVER_PAID_NO = "No";
    /**
     * CB_AUTO_OFFSET CST
     */
    private static final String CB_AUTO_OFFSET_CST = "CST";
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
        Map<String, Object> parameter = new HashMap<String, Object>();
        Map<String, Object> output = new HashMap<String, Object>();
        BillingSystemUserValueObject uvo = (BillingSystemUserValueObject) 
            input.get("uvo");
        parameter.put("idLogin", uvo.getId_user());
        parameter.put("idRef", input.get("idRef"));

        Map<String,Object> refundInfo = queryDAO.executeForMap("B_CSB.getRefundTotalForReceipt", parameter);
        output.put("refundInfo", refundInfo);

        // get header
        Map<String, Object> cashBook = queryDAO.executeForMap(
                "SELECT.BSYS.BCSB.SQL005", parameter);
        output.put("rejecteddate", cashBook.get("REJECT_DATE_CHAR"));
        output.put("rejectdesc", cashBook.get("REJECT_DESC"));
        output.put("receiptNo", cashBook.get("RECEIPT_NO"));
        output.put("pmtMethod", cashBook.get("PMT_METHOD"));
        output.put("payer", cashBook.get("ID_CUST"));
        output.put("other", cashBook.get("OTHER_PAYER"));
        output.put("transDate", cashBook.get("DATE_TRANS_CHAR"));
        output.put("bankAcc", CommonUtils.
                toString(cashBook.get("ID_COM_BANK")));
        output.put("receiptRef", cashBook.get("RECEIPT_REF"));
        output.put("curCode", cashBook.get("CUR_CODE"));
        output.put("amtReceived", 
            cashBook.get("AMT_RECEIVED").toString().concat(""));
        output.put("remark", cashBook.get("REMARK"));
        output.put("paymentStatus", cashBook.get("PMT_STATUS"));
        output.put("paymentRef1", cashBook.get("REFERENCE1"));
        output.put("paymentRef2", cashBook.get("REFERENCE2"));
        output.put("bankCharge", CommonUtils.toString(
                cashBook.get("BANK_CHARGE")));
        output.put("isClosed", cashBook.get("IS_CLOSED"));
        output.put("idRef", cashBook.get("RECEIPT_NO"));
        // ID_BILL_ACCOUNT
        output.put("idBillAccount", cashBook.get("ID_BILL_ACCOUNT"));

        // PAID_PRE_INVOICE
        String paidPreInvoice = CommonUtils.toString(
             cashBook.get("PAID_PRE_INVOICE"));
        // PAID_PRE_INVOICE Text
        String paidPreInvoiceText = "";
        // PAID_PRE_INVOICE is 0
        if (PAID_PRE_INVOICE_0.equals(paidPreInvoice)) {
            paidPreInvoiceText = PAID_PRE_INVOICE_NO;
        } else {
            paidPreInvoiceText = PAID_PRE_INVOICE_YES;
        }
        // PAID_PRE_INVOICE Text
        output.put("paidPreInvoiceText", paidPreInvoiceText);

        // OVER_PAID
        String overPaid = CommonUtils.toString(cashBook.get("OVER_PAID"));
        // OVER_PAID Text
        String overPaidText = "";
        // OVER_PAID is 0
        if (OVER_PAID_0.equals(overPaid)) {
            overPaidText = OVER_PAID_NO;
        } else {
            overPaidText = OVER_PAID_YES;
        }
        // PAID_PRE_INVOICE Text
        output.put("overPaidText", overPaidText);

        // bank info
        List<LabelValueBean> cbBankAcc = queryDAO.executeForObjectList(
                "SELECT.BSYS.BCSB.SQL003", null);
        output.put("cbBankAcc", cbBankAcc);

        // get details
        List<Debit_info_bean> debitInfos = queryDAO.executeForObjectList(
                "SELECT.BSYS.BCSB.SQL_3.2", parameter);
        output.put("debitInfos", debitInfos);

        // M_GSET_D ID_SET
        String setValue = CB_AUTO_OFFSET_CST;
        // init pattern CST or BAC
        String pattern = CB_AUTO_OFFSET_CST;
        // get M_GSET_D data
        List<Map<String, Object>> listMGsetD = queryDAO.executeForMapList(
                "SELECT.BSYS.BCSB.SQL_GET_M_GSET_D", setValue);
        if (!CommonUtils.isEmpty(listMGsetD)) {
            pattern = CB_AUTO_OFFSET_CST;
            // payer info
            if (output.get("pmtMethod").equals("NT")) {
                List<LabelValueBean> cbPayer = queryDAO.executeForObjectList(
                        "SELECT.BSYS.BCSB.SQL_2.1.1", null);
                cbPayer.add(new LabelValueBean("Other Payers", "-1"));
                output.put("cbPayer", cbPayer);
            } else {
                List<LabelValueBean> cbPayer = queryDAO.executeForObjectList(
                        "SELECT.BSYS.BCSB.SQL_2.1.2", null);
                output.put("cbPayer", cbPayer);
            }
        } else {
            // M_GSET_D ID_SET
            setValue = CB_AUTO_OFFSET_BAC;
            // get M_GSET_D data
            listMGsetD = queryDAO.executeForMapList(
                    "SELECT.BSYS.BCSB.SQL_GET_M_GSET_D", setValue);
            if (!CommonUtils.isEmpty(listMGsetD)) {
                pattern = CB_AUTO_OFFSET_BAC;
            }
            Map<String, Object> custInfo = queryDAO.executeForMap("SELECT.BSYS.BCSB.CUST_INFO", CommonUtils.toString(cashBook.get("ID_CUST")));
            String payerName = "";
            if (custInfo!=null) {
                payerName = CommonUtils.toString(custInfo.get("CUST_NAME"));
            }
            output.put("payerName", payerName);
        }
        output.put("pattern", pattern);
        
        String pmtMethod = CommonUtils.toString(output.get("pmtMethod"));
//        if ("CQ".equals(pmtMethod)) {
//            output.put("labPaymentRef1", "Cheque No.");
//            output.put("labPaymentRef2", "Bank-In Slip No.");
//        } else if ("CC".equals(pmtMethod)) {
//            output.put("labPaymentRef1", "Credit Card No.");
//            output.put("labPaymentRef2", "Credit Card Exp.Date");
//        } else if ("GR".equals(pmtMethod)) {
//            output.put("labPaymentRef1", "GIRO Account No.");
//            output.put("labPaymentRef2", "Other GIRO Ref.");
//		} else {
		ApplicationContext context = ApplicationContextProvider
				.getApplicationContext();
		DBCodeListLoader dbCodeListLoader = (DBCodeListLoader) context
				.getBean("PAYMENT_REF_HEADER");
		CodeBean[] codeBeans = dbCodeListLoader.getCodeBeans();
		output.put("labPaymentRef1", codeBeans[0].getName());
		output.put("labPaymentRef2", codeBeans[1].getName());
//        }
        
        if(null != pmtMethod && !"".equals(pmtMethod)){
    		Map<String, Object> param = new HashMap<String, Object>();
        	param.put("seq", "1");
        	param.put("paymentMethod", pmtMethod);
        	Map<String, Object> paymentRefDe = this.queryDAO.executeForMap("SELECT.BSYS.BCSB.GET_PAYMENT_REF_DE", param);
        	output.put("paymentRef1De", paymentRefDe.get("VALUE"));
        	
        	param.put("seq", "2");
        	paymentRefDe = this.queryDAO.executeForMap("SELECT.BSYS.BCSB.GET_PAYMENT_REF_DE", param);
        	output.put("paymentRef2De", paymentRefDe.get("VALUE"));
    	}
        
        // Get all payment Ref de
        // Map<String, String> paymentRefdes1 = new HashMap<String, String>();
        // Map<String, String> paymentRefdes2 = new HashMap<String, String>();

        output.put("isCheckMulCharFlg", getIsCheckMulCharFlg());
        result.setResultString("success");
        result.setResultObject(output);
        result.setErrors(errors);
        result.setMessages(messages);
        return result;
    }
	
	private String getIsCheckMulCharFlg() {
        String isCheckMulCharFlg = CommonUtils.toString(queryDAO.executeForObject("M_GBS.GET_IS_CHECK_MUL_CHAR", null, String.class)).trim();
        
        return isCheckMulCharFlg;
    }
}
