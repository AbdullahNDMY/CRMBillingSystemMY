/**
 * @(#)PR_E_EXP_S01ExecuteCheckErrorBLogic.java
 * 
 * HMSB Online Service Booking System
 * 
 * Version 1.00
 * 
 * Created 2013/08/04
 * 
 * Copyright (c) 2013 Honda Malaysia. All rights reserved.
 */
package nttdm.bsys.e_exp.blogic;

import java.lang.reflect.InvocationTargetException;

import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.e_exp.dto.PR_E_EXP_S01ExecuteCheckErrorInput;
import nttdm.bsys.e_exp.dto.PR_E_EXP_S01ExecuteCheckErrorOutput;

import org.apache.commons.beanutils.BeanUtils;

/**
 * @author gplai
 *
 */
public class PR_E_EXP_S01ExecuteCheckErrorBLogic implements BLogic<PR_E_EXP_S01ExecuteCheckErrorInput> {

    public BLogicResult execute(PR_E_EXP_S01ExecuteCheckErrorInput input) {
        BLogicResult result = new BLogicResult();
        PR_E_EXP_S01ExecuteCheckErrorOutput output = new PR_E_EXP_S01ExecuteCheckErrorOutput();
        
        try {
            BeanUtils.copyProperties(output, input);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        output.setTypeFlg("execute");
        
        result.setResultObject(output);
        result.setResultString("success");
        return result;
    }
}
