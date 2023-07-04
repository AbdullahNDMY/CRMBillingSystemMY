/**
 * @(#)B_BIL_S03neCboChangeAjaxBLogic.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/05/30
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_bil.blogic;

import java.util.ArrayList;
import java.util.List;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_bil.bean.T_BIL_HeaderInfo;
import nttdm.bsys.b_bil.dto.B_BIL_S03neCboChangeAjaxInput;
import nttdm.bsys.b_bil.dto.B_BIL_S03neCboChangeAjaxOutput;
import nttdm.bsys.common.util.CommonUtils;

import org.apache.struts.util.LabelValueBean;

/**
 * @author gplai
 *
 */
public class B_BIL_S03neCboChangeAjaxBLogic extends
        AbstractB_BIL_S03neCboChangeAjaxBLogic {
    
    /**
     * @param B_BIL_S03neAddBillingItemInput
     */
    public BLogicResult execute(B_BIL_S03neCboChangeAjaxInput params) {
        BLogicResult result = new BLogicResult();
        B_BIL_S03neCboChangeAjaxOutput output = new B_BIL_S03neCboChangeAjaxOutput();
        
        T_BIL_HeaderInfo bilHeaderInfo = params.getBilHeaderInfo();
        
        String changeTypeFlag = CommonUtils.toString(bilHeaderInfo.getChangeTypeFlag());
        if ("changeCustName".equals(changeTypeFlag)){
            //Billing Account No. combo box Data
            List<LabelValueBean> listBillingAccountNo =  this.queryDAO.executeForObjectList("B_BIL.getBillingAccountNo", bilHeaderInfo);
            bilHeaderInfo.setListBillingAccountNo(listBillingAccountNo);
        } else {
            bilHeaderInfo.setListBillingAccountNo(new ArrayList<LabelValueBean>());
        }
        
        B_BIL_CommonUtil b_bilCommmonUtil = new B_BIL_CommonUtil(queryDAO, updateDAO);
        //the Combo Box change event
        b_bilCommmonUtil.comboBoxChangeEvt(bilHeaderInfo, B_BIL_CommonUtil.EVENT_TYPE_COMBOBOX_CHANGE);
        
        String defCurrency = CommonUtils.toString(queryDAO.executeForObject("B_BIL.selectDEF_CURRENCY", null, String.class));
        String currencyStd = CommonUtils.toString(queryDAO.executeForObject("B_BIL.selectCURRENCY_STD", null, String.class));
        bilHeaderInfo.setDefCurrency(defCurrency);
        bilHeaderInfo.setCurrencyStd(currencyStd);
        
        output.setBilHeaderInfo(bilHeaderInfo);
        result.setResultObject(output);
        result.setResultString("success");
        return result;
    }
   
}
