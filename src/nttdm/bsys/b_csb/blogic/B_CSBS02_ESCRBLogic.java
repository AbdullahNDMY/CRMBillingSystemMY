package nttdm.bsys.b_csb.blogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.codelist.CodeBean;
import jp.terasoluna.fw.web.codelist.DBCodeListLoader;
import nttdm.bsys.b_csb.dto.Debit_info_bean;
import nttdm.bsys.b_csb.dto.PaymentRefDetail;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.util.ApplicationContextProvider;

import org.apache.struts.util.LabelValueBean;
import org.springframework.context.ApplicationContext;

/**
 * edit page init
 * @author gplai
 *
 */
public class B_CSBS02_ESCRBLogic extends CSBBLogic<Map<String, Object>> {

    /**
     * CB_AUTO_OFFSET CST
     */
    private static final String CB_AUTO_OFFSET_CST = "CST";

    /**
     * CB_AUTO_OFFSET BAC
     */
    private static final String CB_AUTO_OFFSET_BAC = "BAC";

    /**
     * Payment Method NT
     */
    private static final String PAYMENT_METHOD_NT = "NT";

    /**
     * @param input
     *            input value
     * @return BLogicResult result value
     */
    public BLogicResult execute(Map<String, Object> input) {
        BLogicResult result = new BLogicResult();
        Map<String, Object> parameter = new HashMap<String, Object>();
        Map<String, Object> output = new HashMap<String, Object>();
        BillingSystemUserValueObject uvo = (BillingSystemUserValueObject) 
            input.get("uvo");

        String idRef = CommonUtils.toString(input.get("idRef"));
        String curCode = CommonUtils.toString(input.get("curCode"));
        String payer = CommonUtils.toString(input.get("payer"));

        parameter.put("idLogin", uvo.getId_user());
        parameter.put("idRef", idRef.trim());
        parameter.put("curCode", curCode.trim());
        parameter.put("idCust", payer.trim());

        Map<String,Object> refundInfo = queryDAO.executeForMap("B_CSB.getRefundTotalForReceipt", input);
        output.put("refundInfo", refundInfo);

        // pattern
        String pattern = CommonUtils.toString(input.get("pattern"));
        // pmt method
        String pmtMethod = CommonUtils.toString(input.get("pmtMethod"));

        // get header
        Map<String, Object> cashBook = queryDAO.executeForMap(
                "SELECT.BSYS.BCSB.SQL008", parameter);
        // PAID_PRE_INVOICE Text
        output.put("paidPreInvoiceText", cashBook.get("PAID_PRE_INVOICE"));
        // PAID_PRE_INVOICE Text
        output.put("overPaidText", cashBook.get("OVER_PAID"));
        // REMARK
        output.put("remark", cashBook.get("REMARK"));
        
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
        output.put("paymentStatus", cashBook.get("PMT_STATUS"));
        output.put("paymentRef1", cashBook.get("REFERENCE1"));
        output.put("paymentRef2", cashBook.get("REFERENCE2"));
        output.put("bankCharge", CommonUtils.toString(
                cashBook.get("BANK_CHARGE")));
        output.put("isClosed", cashBook.get("IS_CLOSED"));
        output.put("idRef", cashBook.get("RECEIPT_NO"));
        // ID_BILL_ACCOUNT
        output.put("idBillAccount", cashBook.get("ID_BILL_ACCOUNT"));
        
        // bank info
        List<LabelValueBean> cbBankAcc = queryDAO.executeForObjectList(
                "SELECT.BSYS.BCSB.SQL003", null);
        output.put("cbBankAcc", cbBankAcc);
        
        // CST pattern
        if (CB_AUTO_OFFSET_CST.equals(pattern)) {
            if (PAYMENT_METHOD_NT.equals(pmtMethod)) {
                // RetrieveCboPayerNT
                List<LabelValueBean> cbPayer = queryDAO.executeForObjectList(
                        "SELECT.BSYS.BCSB.SQL_2.1.1", null);
                // payer info
                cbPayer.add(new LabelValueBean("Other Payers", "-1"));
                output.put("cbPayer", cbPayer);
                if (!CommonUtils.isEmpty(cbPayer)) {
                    // get ID_CUST list
                    List<String> idCustList = getIdCustList(cbPayer);
                    parameter.put("idCustList", idCustList);
                    // get Debit Infomation
                    List<Debit_info_bean> debitInfos = queryDAO.
                        executeForObjectList("SELECT.BSYS.BCSB.SQL_2.5",
                                    parameter);
                    output.put("debitInfos", debitInfos);
                } else {
                    output.put("debitInfos", new ArrayList<Debit_info_bean>());
                }
            } else {
                // payer info
                List<LabelValueBean> cbPayer = queryDAO.executeForObjectList(
                        "SELECT.BSYS.BCSB.SQL_2.1.2", null);
                output.put("cbPayer", cbPayer);
                // get Debit Infomation
                List<Debit_info_bean> debitInfos = 
                    queryDAO.executeForObjectList("SELECT.BSYS.BCSB.SQL_2.3",
                                parameter);
                output.put("debitInfos", debitInfos);
            }
        } else if (CB_AUTO_OFFSET_BAC.equals(pattern)) {
            // Billing Account No.
            String idBillAccount = CommonUtils.toString(
                    input.get("idBillAccount"));
            parameter.put("idBillAccount", idBillAccount.trim());
            // get Debit Infomation
            List<Debit_info_bean> debitInfos = queryDAO.executeForObjectList(
                    "SELECT.BSYS.BCSB.SQL_2.6", parameter);
            output.put("debitInfos", debitInfos);
			
			// Get all payment ref detail
			List<PaymentRefDetail> paymentRefDetails1 = this.queryDAO
					.executeForObjectList("SELECT.BSYS.BCSB.GET_ALL_PAYMENT_REF_DE1", null);
			List<PaymentRefDetail> paymentRefDetails2 = this.queryDAO
					.executeForObjectList("SELECT.BSYS.BCSB.GET_ALL_PAYMENT_REF_DE2", null);

			output.put("paymentRefDetails1", paymentRefDetails1);
			output.put("paymentRefDetails2", paymentRefDetails2);
			
			Map<String, Object> custInfo = queryDAO.executeForMap("SELECT.BSYS.BCSB.CUST_INFO", CommonUtils.toString(cashBook.get("ID_CUST")));
            String payerName = "";
            if (custInfo!=null) {
                payerName = CommonUtils.toString(custInfo.get("CUST_NAME"));
            }
            output.put("payerName", payerName);
        }
        output.put("pattern", pattern);
        
        ApplicationContext context = ApplicationContextProvider
                  .getApplicationContext();
          DBCodeListLoader dbCodeListLoader = (DBCodeListLoader) context
                  .getBean("PAYMENT_REF_HEADER");
          CodeBean[] codeBeans = dbCodeListLoader.getCodeBeans();
          output.put("labPaymentRef1", codeBeans[0].getName());
          output.put("labPaymentRef2", codeBeans[1].getName());
          
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

        output.put("actionFlg", "edit");

        result.setResultString("success");
        result.setResultObject(output);
        return result;
    }

    /**
     * get ID_CUST list
     * 
     * @param listLabelValueBean
     *            ID_CUST list for combox
     * @return ID_CUST list
     */
    private List<String> getIdCustList(List<LabelValueBean> 
        listLabelValueBean) {
        List<String> idCustList = new ArrayList<String>();
        for (LabelValueBean labelValueBean : listLabelValueBean) {
            idCustList.add(labelValueBean.getValue().trim());
        }
        return idCustList;
    }
}
