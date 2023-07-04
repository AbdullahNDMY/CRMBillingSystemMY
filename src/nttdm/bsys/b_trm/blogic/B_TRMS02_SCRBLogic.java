package nttdm.bsys.b_trm.blogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nttdm.bsys.b_trm.bean.DebitInfoBean;

import org.apache.struts.util.LabelValueBean;

import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;

public class B_TRMS02_SCRBLogic extends TRMBLogic<Map<String, Object>> {
	private BLogicMessages messages;
	private BLogicMessages errors;

	public BLogicResult execute(Map<String, Object> input) {
		BLogicResult result = new BLogicResult();
		messages = new BLogicMessages();
		errors = new BLogicMessages();
		Map<String, Object> output = new HashMap<String, Object>();
		// check G.SET_VALUE
		String setValue = queryDAO.executeForObject("SELECT.B_TRM.IS_DISPLAY_BIL_ACC", null, String.class);
		output.put("gblSetValue", setValue);
		//set default customer
		//List<LabelValueBean> cbCustomer = queryDAO.executeForObjectList("SELECT.B_TRM.SQL013", null);
		//output.put("cbCustomer", cbCustomer);

		result.setResultObject(output);
		result.setResultString("success");
		result.setErrors(errors);
		result.setMessages(messages);
		return result;
	}

}