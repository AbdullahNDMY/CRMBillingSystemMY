/**
 * @(#)R_ACR_R01BLogic.java
 * 
 * Billing System
 * 
 * Version 1.00

 * Created 2011-8-29

 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_acr.blogic;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.r_acr.dto.R_ACR_R01Input;
import nttdm.bsys.r_acr.dto.R_ACR_R01Output;

/**
 * BusinessLogic class.
 * 
 * @author p-minzh
 */
public class R_ACR_R01BLogic extends AbstractR_ACR_R01BLogic {

    /**
     * 
     * @param param
     * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
     */
    public BLogicResult execute(R_ACR_R01Input param) {
        BLogicResult result = new BLogicResult();
        R_ACR_R01Output outputDTO = new R_ACR_R01Output();

        Map<String, Object> m = new HashMap<String, Object>();
        m.put("idUser", param.getUvo().getId_user());
        HashMap<String, Object> userAccess =
            (HashMap<String, Object>) this.queryDAO.executeForMap(
                "R_ACR.getAccessType", m);
        if (!CommonUtils.isEmpty(userAccess)) {
            outputDTO.setAccessType(userAccess.get("ACCESS_TYPE").toString());
        }
        
        // All Service Status check boxes are selected by default
        outputDTO.setTbxServiceStatus(new String[]{"PS1", "PS3", "PS6", "PS7"});
        
        try {
            BeanUtils.copyProperties(outputDTO, param);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        result.setResultObject(outputDTO);
        result.setResultString("success");
        return result;
    }
}