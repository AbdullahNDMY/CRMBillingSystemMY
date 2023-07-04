/**
 * @(#)RP_B_BAC_S03SearchBLogic.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/07/10
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_bac.blogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionMessages;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_bac.dto.RP_B_BAC_S03SearchInput;
import nttdm.bsys.b_bac.dto.RP_B_BAC_S03SearchOutput;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;

/**
 * @author gplai
 *
 */
public class RP_B_BAC_S03SearchBLogic extends AbstractRP_B_BAC_S03SearchBLogic {

    public BLogicResult execute(RP_B_BAC_S03SearchInput params) {
        BLogicResult result  = new BLogicResult();
        // Get display row of each screen from global setting
        BillingSystemSettings systemSetting = new BillingSystemSettings(queryDAO);
        Integer row = systemSetting.getResultRow();
        
        Integer startIndex = 0;
        try {
            startIndex = Integer.parseInt(CommonUtils.toString(params.getStartIndex()));
        } catch(Exception e) {
            startIndex = 0;
        }
        if(!CommonUtils.toString(params.getPageSearch()).equals("Y")){
            startIndex = 0;
        }
        RP_B_BAC_S03SearchOutput output = new RP_B_BAC_S03SearchOutput();
        String doSearch = CommonUtils.toString(params.getDoSearch());
        if(doSearch!=null&&"Y".equals(doSearch)){
            Map<String, Object> input = new HashMap<String, Object>();
            String tbxCustomerName = CommonUtils.toString(params.getTbxCustomerName());
            String tbxCustomerId = CommonUtils.toString(params.getTbxCustomerId());
            String tbxBillAcc = CommonUtils.toString(params.getTbxBillAcc());
            input.put("tbxCustomerName", CommonUtils.escapeSQL(tbxCustomerName));
            input.put("tbxCustomerId", CommonUtils.escapeSQL(tbxCustomerId));
            input.put("tbxBillAcc", CommonUtils.escapeSQL(tbxBillAcc));
            List<HashMap<String,Object>> searchResult = new ArrayList<HashMap<String, Object>>();
            // get total
            Integer totalCount = queryDAO.executeForObject("B_BAC.getBac_s03SearchCount", input, Integer.class);
            if (totalCount == 0) {
                startIndex = 0;
                // info.ERR2SC002
                BLogicMessages msgs = new BLogicMessages();
                BLogicMessage msg = new BLogicMessage("info.ERR2SC002", new Object());
                msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
                result.setMessages(msgs);
            } else {
                if (startIndex == null || startIndex < 0 || totalCount < startIndex) {
                    startIndex = 0;
                }
                searchResult = queryDAO.executeForObjectList("B_BAC.getBac_s03Search", input, startIndex, row);
            }
            output.setStartIndex(startIndex);
            output.setRow(row);
            output.setTotalCount(totalCount);
            output.setTbxCustomerName(tbxCustomerName);
            output.setTbxCustomerId(tbxCustomerId);
            output.setTbxBillAcc(tbxBillAcc);
            output.setCustomerBeans(searchResult);
        }
        result.setResultObject(output);
        result.setResultString("success");
        return result;
    }
}
