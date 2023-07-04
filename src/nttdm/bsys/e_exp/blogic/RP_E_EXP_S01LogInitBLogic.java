/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : E_SET
 * SERVICE NAME   : E_SET_S03
 * FUNCTION       : Showing Log History
 * FILE NAME      : RP_E_SET_S03BLogic.java
 * 
 * Copyright © 2011 NTTDATA Corporation
 *
 * History
 * 
 * 
**********************************************************/
package nttdm.bsys.e_exp.blogic;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.e_exp.dto.RP_E_EXP_S01LogInitInput;
import nttdm.bsys.e_exp.dto.RP_E_EXP_S01LogInitOutput;

/**
 * RP_E_SET_S03BLogic<br>
 * <ul>
 * <li>A BLogic class to process displaying Batch log list
 * </ul>
 * <br>
 * @author  NTTData
 * @version 1.0
 */
public class RP_E_EXP_S01LogInitBLogic implements BLogic<RP_E_EXP_S01LogInitInput>{

    /**
     * queryDAO
     */
    protected QueryDAO queryDAO;

    /**
     * updateDAO
     */
    protected UpdateDAO updateDAO;
    
	public BLogicResult execute(RP_E_EXP_S01LogInitInput input) {
		BLogicResult result = new BLogicResult();
		RP_E_EXP_S01LogInitOutput output = new RP_E_EXP_S01LogInitOutput();
		
		String idBathc = CommonUtils.toString(input.getIdBatch());
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("ID_BATCH", idBathc);
		
		List<Map<String, Object>> logDetailList = this.queryDAO.executeForMapList("E_EXP.getLogDetailList", param);
		if (logDetailList!=null && 0<logDetailList.size()) {
		   for(Map<String, Object> map : logDetailList) {
		       Reader readerMsg = (Reader)map.get("MESSAGE");
		       String msg = CommonUtils.clobReaderToString(readerMsg);
		       map.put("MESSAGE", msg);
		   }
		}
		output.setLogsList(logDetailList);
		
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
