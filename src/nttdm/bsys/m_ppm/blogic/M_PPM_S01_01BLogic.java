/**
 * Billing System
 * 
 * SUBSYSTEM NAME : Standard Price and Plan Maintenance
 * SERVICE NAME : M_PPM_S01
 * FUNCTION : SearchBLogic
 * FILE NAME : M_PPM_S01_01BLogic.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 * History
 */
package nttdm.bsys.m_ppm.blogic;

import java.util.List;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.m_ppm.bean.ServiceGroup;
import nttdm.bsys.m_ppm.dto.M_PPM_S01_01Input;
import nttdm.bsys.m_ppm.dto.M_PPM_S01_01Output;

/**
 * Search BLogic<br/>
 * Use to initial data before search<br/>
 * Do not search<br/>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class M_PPM_S01_01BLogic implements BLogic<M_PPM_S01_01Input> {
	private QueryDAO queryDAO;
	
	public BLogicResult execute(M_PPM_S01_01Input param) {
		BLogicResult result = new BLogicResult();		
		M_PPM_S01_01Output output = new M_PPM_S01_01Output();

		// get Category Group
		List<ServiceGroup> cboCategoryList = queryDAO.executeForObjectList("SELECT.M_PPM.S01.CATEGORY", null);
		output.setCboCategoryList(cboCategoryList);

		result.setResultObject(output);
		result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);		
		return result;  
	}

	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}
}