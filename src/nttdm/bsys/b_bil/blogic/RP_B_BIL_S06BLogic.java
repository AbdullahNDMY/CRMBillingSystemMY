/**
 * @(#)RP_B_BIL_S06BLogic.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/06/02
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_bil.blogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_bil.bean.B_BIL_S06Bean;
import nttdm.bsys.b_bil.bean.T_BIL_DBean;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S06Input;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S06Output;

/**
 * @author gplai
 *
 */
public class RP_B_BIL_S06BLogic extends AbstractRP_B_BIL_S06BLogic{

    public BLogicResult execute(RP_B_BIL_S06Input params) {
        BLogicResult result = new BLogicResult();
        RP_B_BIL_S06Output output = new RP_B_BIL_S06Output();
        
        B_BIL_S06Bean bilS06Bean = params.getBilS06Bean();
        //Job Modules is used flag
        B_BIL_CommonUtil util = new B_BIL_CommonUtil(queryDAO, updateDAO);
        String jobModulesDisplayFlg = util.getIsJNMModulesDisplayFlg();
        bilS06Bean.setJobModulesDisplayFlg(jobModulesDisplayFlg);
        //SubPlan List
        int subPlanLength = 0;
        subPlanLength = Integer.parseInt(bilS06Bean.getSubPlanLength());
        List<T_BIL_DBean> subPlanBeanList = new ArrayList<T_BIL_DBean>();
        List<Map<String, Object>> applyGstList = this.queryDAO.executeForMapList("B_BIL.getM_TAXInfo2", null);
        for (int i=0;i<subPlanLength;i++) {
            T_BIL_DBean bilDBean = new T_BIL_DBean();
            bilDBean.setApplyGstList(applyGstList);
            subPlanBeanList.add(bilDBean);
        }
        // 
        bilS06Bean.setSubPlanBean(subPlanBeanList);
        String custPoDisplay = util.getCustPoDisplay();
        bilS06Bean.setCustPoDisplay(custPoDisplay);

        output.setBilS06Bean(bilS06Bean);
        result.setResultObject(output);
        result.setResultString("success");
        return result;
    }
}
