package nttdm.bsys.b_trm.blogic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.util.LabelValueBean;

import nttdm.bsys.b_trm.bean.DebitInfoBean;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.CommonUtils;

import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;

public class B_TRMS02_VBLogic extends TRMBLogic<Map<String, Object>> {
    private BLogicMessages messages;
    private BLogicMessages errors;

    public BLogicResult execute(Map<String, Object> input) {
        BLogicResult result = new BLogicResult();
        messages = new BLogicMessages();
        errors = new BLogicMessages();
        Map<String, Object> output = new HashMap<String, Object>();
        Map<String, Object> parameter = new HashMap<String, Object>();
        BillingSystemUserValueObject uvo = (BillingSystemUserValueObject) input.get("uvo");
        parameter.put("idLogin", uvo.getId_user());
        this.copyProperties(input, output);
        this.copyProperties(input, parameter);
        
        Map<String, Object> creditInfo = queryDAO.executeForMap("SELECT.B_TRM.SQL011", parameter);
        parameter.put("customer", creditInfo.get("ID_CUST"));
        Object date = creditInfo.get("DATE_REQ");
        Object currency = creditInfo.get("BILL_CURRENCY");
        //Float origAmt = Float.parseFloat(creditInfo.get("BILL_AMOUNT").toString());
        //Float creditBalance = Float.parseFloat(creditInfo.get("AMT_DUE").toString());
        output.put("customer", creditInfo.get("ID_CUST"));
        output.put("creditRef", creditInfo.get("ID_REF"));
        output.put("date", date);
        output.put("currency", currency);
        output.put("origAmt", creditInfo.get("BILL_AMOUNT"));
        output.put("creditBalance", creditInfo.get("AMT_DUE"));
        
        String transationDate = queryDAO.executeForObject("SELECT.B_TRM.SQL021", parameter, String.class); 
        output.put("transationDate", transationDate);
        
        boolean edited = input.get("action") != null && input.get("action").equals("edit");
        String setValue = queryDAO.executeForObject("SELECT.B_TRM.IS_DISPLAY_BIL_ACC", null, String.class);        
        //get debit information
        parameter.put("creditRef", output.get("creditRef"));
        parameter.put("currency", currency);
        List<DebitInfoBean> debitInfos = null;
        if(edited) {
            if("CST".equals(setValue)){
                parameter.put("cst", setValue);
            }
            debitInfos = queryDAO.executeForObjectList("SELECT.B_TRM.SQL015", parameter);
        }
        else {
            debitInfos = queryDAO.executeForObjectList("SELECT.B_TRM.SQL010", parameter);
        }
        output.put("debitInfos", debitInfos);
        //get Billing Account No.
        // check G.SET_VALUE

        output.put("gblSetValue", setValue);
        
        String billAccount = null;
         
        if(input.get("action") != null && input.get("action").equals("view") || 
                input.get("action") != null && input.get("action").equals("edit")){
            if(!setValue.equals("CST")) {
                for(DebitInfoBean db : debitInfos) {
                    if(db.getBillAccount() != null) {
                        billAccount = db.getBillAccount();
                        if(billAccount.trim().equals("0")){
                            continue;
                        }
                        output.put("billAccount", CommonUtils.isNull((Object)billAccount) ? "" : billAccount);
                        break;
                    }
                }
            }
            if(billAccount == null){
                billAccount = "";
                output.put("billAccount", billAccount);
            }
        } else {
            if(!setValue.equals("CST")){
                if(input.get("billAccount")!=null){
                    billAccount = input.get("billAccount").toString();
                    output.put("billAccount", CommonUtils.isNull((Object)billAccount) ? "" : billAccount);
                }
            }
        }        
        //set default customer
        List<LabelValueBean> cbCustomer = queryDAO.executeForObjectList("SELECT.B_TRM.SQL013", null);
        output.put("cbCustomer", cbCustomer);
        
        //get combo credit reference
        parameter.put("customer", output.get("customer"));
        List<LabelValueBean> cbCreditRef = queryDAO.executeForObjectList("SELECT.B_TRM.SQL019", parameter);
        output.put("cbCreditRef", cbCreditRef);
        
        //get combo billing account
        List<LabelValueBean> cbBillAccount = queryDAO.executeForObjectList("SELECT.B_TRM.SQL020", parameter);
        output.put("cbBillAccount", cbBillAccount);
        
        if(edited) {
            result.setResultString("edit");
        }
        else {
            result.setResultString("view");
        }
        
        result.setResultObject(output);
        result.setErrors(errors);
        result.setMessages(messages);
        return result;
    }
}
