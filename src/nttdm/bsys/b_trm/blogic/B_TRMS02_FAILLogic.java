package nttdm.bsys.b_trm.blogic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts.util.LabelValueBean;

import nttdm.bsys.common.util.CommonUtils;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;

public class B_TRMS02_FAILLogic extends TRMBLogic<Map<String, Object>>{
	
	public BLogicResult execute(Map<String, Object> input) {
		BLogicResult result = new BLogicResult();
		BLogicMessages messages = new BLogicMessages();
		BLogicMessages errors = new BLogicMessages();

		//set default customer
		if(CommonUtils.isEmpty(input.get("cbCustomer"))){
			List<LabelValueBean> cbCustomer = queryDAO.executeForObjectList("SELECT.B_TRM.SQL013", null);
			input.put("cbCustomer", cbCustomer);
		}

		Map<String, Object> output = new HashMap<String, Object>();
		Map<String, Object> parameter = new HashMap<String, Object>();
		this.copyProperties(input, output);
		this.copyProperties(input, parameter);

		if(CommonUtils.isEmpty(output.get("customer"))) {
			output.put("cbCreditRef", null);
			output.put("cbBillAccount", null);
			output.put("date", null);
			output.put("currency", null);
			output.put("origAmt", null);
			output.put("creditBalance", null);
			output.put("debitInfos", null);
			if("edit".equals(output.get("action"))){
				output.put("action", null);
			}
		}
		// check G.SET_VALUE
        String setValue = queryDAO.executeForObject("SELECT.B_TRM.IS_DISPLAY_BIL_ACC", null, String.class);
        output.put("gblSetValue", setValue);
        if (!"CST".equals(setValue)) {
            List<LabelValueBean> cbBillAccount = queryDAO.executeForObjectList("SELECT.B_TRM.SQL020", parameter);
            output.put("cbBillAccount", cbBillAccount);
        }
        parameter.put("billAcc", CommonUtils.toString(input.get("billAccount")));
        List<LabelValueBean> cbCreditRef = queryDAO.executeForObjectList("SELECT.B_TRM.SQL016", parameter);

        String creditRef = CommonUtils.toString(parameter.get("creditRef"));
        boolean contain = false;
        for (LabelValueBean bean : cbCreditRef) {
            if (creditRef.equals(bean.getValue())) {
                contain = true;
                break;
            }
        }
        if (!contain && !CommonUtils.isEmpty(creditRef)) {
            LabelValueBean b = new LabelValueBean();
            b.setLabel(creditRef);
            b.setValue(creditRef);
            cbCreditRef.add(b);
        }

        output.put("cbCreditRef", cbCreditRef);
        
		result.setResultString("success");
		result.setResultObject(output);
		result.setErrors(errors);
		result.setMessages(messages);
		return result;
	}
	
	protected void copyProperties(Map<String, Object> src, Map<String, Object> des) {
		Set<String> keys = src.keySet();
		for(String key : keys)
			des.put(key, src.get(key));
	}

}
