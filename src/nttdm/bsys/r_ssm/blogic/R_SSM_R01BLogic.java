/**
 * @(#)R_SSM_R01BLogic.java
 * Billing System
 * Version 1.00
 * Created 2013/10/26
 * Copyright (c) 2013 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_ssm.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.r_ssm.dto.R_SSM_R01Input;
import nttdm.bsys.r_ssm.dto.R_SSM_R01Output;

/**
 * Screen initial display.
 * 
 * @author bench
 */
public class R_SSM_R01BLogic extends AbstractR_SSM_R01BLogic {

    /*
     * (non-Javadoc)
     * 
     * @see jp.terasoluna.fw.service.thin.BLogic#execute(java.lang.Object)
     */
    public BLogicResult execute(R_SSM_R01Input input) {
        BLogicResult result = new BLogicResult();
        R_SSM_R01Output output = new R_SSM_R01Output();

        // R-SSM user access right
        String accessType = CommonUtils.getAccessRight(input.getUvo(), "R-SSM");
        output.setAccessType(accessType);

        if (CommonUtils.isEmpty(input.getInitFlg())) {
            output.setChkErrorCodeAll("0");
            output.setChkErrorCode1("1");
            output.setChkErrorCode2("2");
            output.setChkErrorCode3("3");
            output.setChkErrorCode4("4");
            output.setChkErrorCode5("5");
            output.setChkErrorCode6("6");
            output.setChkErrorCode7("7");
            output.setChkErrorCode8("8");
            output.setChkErrorCode9("9");
        } else {
            output.setChkErrorCodeAll(input.getChkErrorCodeAll());
            output.setChkErrorCode1(input.getChkErrorCode1());
            output.setChkErrorCode2(input.getChkErrorCode2());
            output.setChkErrorCode3(input.getChkErrorCode3());
            output.setChkErrorCode4(input.getChkErrorCode4());
            output.setChkErrorCode5(input.getChkErrorCode5());
            output.setChkErrorCode6(input.getChkErrorCode6());
            output.setChkErrorCode7(input.getChkErrorCode7());
            output.setChkErrorCode8(input.getChkErrorCode8());
            output.setChkErrorCode9(input.getChkErrorCode9());
        }

        result.setResultObject(output);
        result.setResultString("success");
        return result;
    }

}
