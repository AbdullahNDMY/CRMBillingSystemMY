/**
 * @(#)B_SSM_S04e_InitBlogic.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/05/23
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_ssm.s04.blogic;

import java.util.HashMap;

import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_ssm.utility.BLogicUtils;

/**
 * @author gplai
 *
 */
public class B_SSM_S04e_InitBlogic extends B_SSM_S04BLogic<HashMap<String, Object>> {

    /**
     * @param input Map<String, Object>
     * @return BLogicResult
     */
    public BLogicResult execute(HashMap<String, Object> input) {
        BLogicResult result = new BLogicResult();
        BLogicMessages messages = new BLogicMessages();
        BLogicMessages errors = new BLogicMessages();
        HashMap<String, Object> output = new HashMap<String, Object>();
        
        // Copy first properties to output
        BLogicUtils.copyProperties(output, input);  
        
        // Map data
        B_SSM_S04_BUtils.mappingViewData(queryDAO, input, output);
        
        result.setResultObject(output);
        result.setResultString("success");
        result.setErrors(errors);
        result.setMessages(messages);
        return result;
    }
}
