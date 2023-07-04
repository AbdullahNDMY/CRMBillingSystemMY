/**
 * @(#)PR_E_EXP_S01InitBLogic.java
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

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.e_exp.dto.PR_E_EXP_S01InitInput;
import nttdm.bsys.e_exp.dto.PR_E_EXP_S01InitOutput;

/**
 * @author gplai
 *
 */
public class PR_E_EXP_S01InitBLogic implements BLogic<PR_E_EXP_S01InitInput> {

    /**
     * queryDAO
     */
    private QueryDAO queryDAO;

    /**
     * updateDAO
     */
    private UpdateDAO updateDAO;
    
    public BLogicResult execute(PR_E_EXP_S01InitInput input) {
        BLogicResult result = new BLogicResult();
        PR_E_EXP_S01InitOutput output = new PR_E_EXP_S01InitOutput();
        
        String typeFlg = input.getTypeFlg();
        if("pageLink".equals(typeFlg) || "execute".equals(typeFlg)) {
            output.setClosingMonth(input.getClosingMonth());
            output.setClosingYear(input.getClosingYear());
            // Add #146 Start
            output.setCustomerTypeDispFlg(input.getCustomerTypeDispFlg());
            output.setCustomerType(input.getCustomerType());
            // Add #146 End
        } else {
            Calendar calendar = Calendar.getInstance();
            int month = calendar.get(Calendar.MONTH) + 1;
            int year = calendar.get(Calendar.YEAR);
            
            output.setClosingMonth(CommonUtils.toString(month));
            output.setClosingYear(CommonUtils.toString(year));
            // Add #146 Start
            Map<String,Object> mExportpps = this.queryDAO.executeForMap("E_EXP.getEXPORTPPS", null);
            String exportpps = CommonUtils.toString(mExportpps.get("VALUE"));
            output.setCustomerTypeDispFlg(exportpps);
            if ("SG01".equals(exportpps)) {
                output.setCustomerType("CORP");
            }
            // Add #146 End
        }
        
        BillingSystemSettings bss = new BillingSystemSettings(queryDAO);
        int row = bss.getResultRow();
        Integer startIndex = input.getStartIndex();
        Integer countHistory = queryDAO.executeForObject("E_EXP.getHistorieCount", null, Integer.class);
        if (startIndex == null || startIndex < 0 || startIndex.compareTo(countHistory) > 0)
            startIndex = 0;
        
        List<Map<String, Object>> listHistories = this.queryDAO.executeForObjectList("E_EXP.getHistories", null, startIndex, row);
        
        output.setListHistories(listHistories);
        output.setTotalRow(countHistory);
        output.setRow(row);
        output.setStartIndex(startIndex);
        
        result.setResultObject(output);
        result.setResultString("success");
        return result;
    }

    /**
     * @return the queryDAO
     */
    public QueryDAO getQueryDAO() {
        return queryDAO;
    }

    /**
     * @param queryDAO the queryDAO to set
     */
    public void setQueryDAO(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }

    /**
     * @return the updateDAO
     */
    public UpdateDAO getUpdateDAO() {
        return updateDAO;
    }

    /**
     * @param updateDAO the updateDAO to set
     */
    public void setUpdateDAO(UpdateDAO updateDAO) {
        this.updateDAO = updateDAO;
    }
}
