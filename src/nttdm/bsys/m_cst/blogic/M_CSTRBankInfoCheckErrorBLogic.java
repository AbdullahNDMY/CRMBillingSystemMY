/**
 * @(#)M_CSTRBankInfoCheckErrorBLogic.java
 * 
 * NTTS System
 * 
 * Version 1.00
 * 
 * Created 2012/12/11
 * 
 * Copyright (c) 2012 NTTS Malaysia. All rights reserved.
 */
package nttdm.bsys.m_cst.blogic;

import java.util.List;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.m_cst.common.Util;
import nttdm.bsys.m_cst.dto.M_CSTRBankInfoCheckErrorInput;
import nttdm.bsys.m_cst.dto.M_CSTRBankInfoCheckErrorOutput;

import org.apache.commons.beanutils.BeanUtils;

/**
 * @author gplai
 *
 */
public class M_CSTRBankInfoCheckErrorBLogic implements BLogic<M_CSTRBankInfoCheckErrorInput> {
    
    private QueryDAO queryDAO;
    
    private UpdateDAO updateDAO;
    
    
    /* (non-Javadoc)
     * @see jp.terasoluna.fw.service.thin.BLogic#execute(java.lang.Object)
     */
    public BLogicResult execute(M_CSTRBankInfoCheckErrorInput params) {
        BLogicResult result = new BLogicResult();
        M_CSTRBankInfoCheckErrorOutput outputDTO = new M_CSTRBankInfoCheckErrorOutput();
        
        try {
            BeanUtils.copyProperties(outputDTO, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // get m_bank
        List bankList = queryDAO.executeForObjectList(Util.SELECT_BANK_LIST, null);
        outputDTO.setBankList(bankList);
        
        result.setResultObject(outputDTO);
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
