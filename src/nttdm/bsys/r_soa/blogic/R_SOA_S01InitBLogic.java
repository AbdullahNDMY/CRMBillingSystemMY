/**
 * @(#)R_SOA_S01InitBLogic.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/07/02
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_soa.blogic;

import java.util.Calendar;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.r_soa.dto.R_SOA_S01InitInput;
import nttdm.bsys.r_soa.dto.R_SOA_S01InitOutput;

/**
 * @author gplai
 *
 */
public class R_SOA_S01InitBLogic extends AbstractR_SOA_S01InitBLogic {

    public BLogicResult execute(R_SOA_S01InitInput params) {
        BLogicResult result = new BLogicResult();
        R_SOA_S01InitOutput outputDTO = new R_SOA_S01InitOutput();
        outputDTO.setAccessType(CommonUtils.getAccessRight(params.getUvo(), "R-SOA"));
        
        // payment currency combo box array
        Object[] cboPaymentCurrencyArray = this.queryDAO.executeForObjectArray("R_SOA.getGrandTotal", null, String.class);
        outputDTO.setCboPaymentCurrencyArray((String[]) cboPaymentCurrencyArray);
        
        outputDTO.setCboPrintStatement("1");
        outputDTO.setChkStatementTotal("1");
        Calendar now=Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH)+1;
        outputDTO.setSysdateYYYY(CommonUtils.toString(year));
        outputDTO.setSysdateMM(CommonUtils.toString(month));
        result.setResultObject(outputDTO);
        result.setResultString("success");
        return result;
    }
}
