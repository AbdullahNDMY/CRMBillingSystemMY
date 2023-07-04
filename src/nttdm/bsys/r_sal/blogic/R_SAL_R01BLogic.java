/**
 * @(#)R_SAL_R01BLogic.java
 * Billing System
 * Version 1.00
 * Created 2011-8-29
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.r_sal.blogic;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.util.LabelValueBean;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.r_sal.dto.R_SAL_R01Input;
import nttdm.bsys.r_sal.dto.R_SAL_R01Output;

/**
 * BusinessLogic class.
 * 
 * @author xycao
 */
public class R_SAL_R01BLogic extends AbstractR_SAL_R01BLogic {

    /**
     * @param param
     * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
     */
    public BLogicResult execute(R_SAL_R01Input param) {
        BLogicResult result = new BLogicResult();
        R_SAL_R01Output outputDTO = new R_SAL_R01Output();

        Map<String, Object> m = new HashMap<String, Object>();
        m.put("idUser", param.getUvo().getId_user());
        HashMap<String, Object> userAccess =
            (HashMap<String, Object>) this.queryDAO.executeForMap(
                "R_SAL.getAccessType", m);

		List<LabelValueBean> cbServiceGroups = queryDAO.executeForObjectList("SELECT.R_SAL.GET_LIST_SERVICEGROUP", null);
		outputDTO.setCbServiceGroups(cbServiceGroups);
		
        if (!CommonUtils.isEmpty(userAccess)) {
            outputDTO.setAccessType(userAccess.get("ACCESS_TYPE").toString());
        }
        try {
            BeanUtils.copyProperties(outputDTO, param);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        String initFlg = CommonUtils.toString(param.getInitFlag());
        if (CommonUtils.isEmpty(initFlg)) {
            outputDTO.setIssueTypeSingpost("0");
            outputDTO.setIssueTypeAuto("0");
            outputDTO.setIssueTypeManual("0");
        } else {
            outputDTO.setIssueTypeSingpost(param.getIssueTypeSingpost());
            outputDTO.setIssueTypeAuto(param.getIssueTypeAuto());
            outputDTO.setIssueTypeManual(param.getIssueTypeManual());
        }
        result.setResultObject(outputDTO);
        result.setResultString("success");
        return result;
    }
}