/**
 * @(#)R_BAC_R01BLogic.java
 * Billing System
 * Version 1.00
 * Created 2011-8-29
 * Copyright (c) 2011 NTT DATA  All Rights Reserved.
 */
package nttdm.bsys.r_bac.blogic;

import java.util.HashMap;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.r_bac.dto.R_BAC_R01Input;
import nttdm.bsys.r_bac.dto.R_BAC_R01Output;

/**
 * R_BAC_R01BLogic.java
 * R_BAC initial logic
 * @author NTT DATA
 *
 */
public class R_BAC_R01BLogic extends AbstractR_BAC_R01BLogic{

    /**
     * 
     * @param param
     * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
     */
	public BLogicResult execute(R_BAC_R01Input input) {
		BLogicResult result = new BLogicResult();
		R_BAC_R01Output output = new R_BAC_R01Output();
		
		// to get user access and set it 
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("idUser", input.getUvo().getId_user());
        HashMap<String, Object> userAccess =
            (HashMap<String, Object>) this.queryDAO.executeForMap(
                "R_BAC.getAccessType", m);
        if (!CommonUtils.isEmpty(userAccess)) {
        	output.setAccessType(userAccess.get("ACCESS_TYPE").toString());
        }
        
        // default check
        output.setTotalamount("1");
        result.setResultObject(output);
        result.setResultString("success");
        return result;
	}
}
