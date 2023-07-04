/**
 * @(#)R_RRR_R01BLogic.java
 * 
 * Billing System
 * 
 * Version 1.00

 * Created 2011-8-29

 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_rrr.blogic;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.r_rrr.dto.R_RRR_R01Input;
import nttdm.bsys.r_rrr.dto.R_RRR_R01Output;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.util.LabelValueBean;

/**
 * BusinessLogic class.
 * 
 * @author xycao
 */
public class R_RRR_R01BLogic extends AbstractR_RRR_R01BLogic {

    /**
     * 
     * @param param
     * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
     */
    public BLogicResult execute(R_RRR_R01Input param) {
        BLogicResult result = new BLogicResult();
        R_RRR_R01Output outputDTO = new R_RRR_R01Output();

        Map<String, Object> m = new HashMap<String, Object>();
        m.put("idUser", param.getUvo().getId_user());
        HashMap<String, Object> userAccess =
            (HashMap<String, Object>) this.queryDAO.executeForMap(
                "R_RRR.getAccessType", m);
        if (!CommonUtils.isEmpty(userAccess)) {
            outputDTO.setAccessType(userAccess.get("ACCESS_TYPE").toString());
        }
        
        // PAYMENT METHOD and PAYMENT_REF_DETAIL mapping list
        List<Map<String, Object>> paymentInfo = this.queryDAO.executeForMapList("R_RRR.getPaymentInfo", null);
		outputDTO.setPaymentInfo(paymentInfo);
		
		List<LabelValueBean> listBackInfo = this.queryDAO.executeForObjectList("R_RRR.getBankInfo", null);
		outputDTO.setListBackInfo(listBackInfo);
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