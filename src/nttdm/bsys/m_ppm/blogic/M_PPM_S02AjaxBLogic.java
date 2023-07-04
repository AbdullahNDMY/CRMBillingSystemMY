/**
 * Billing System
 * 
 * SUBSYSTEM NAME : Standard Price and Plan Maintenance
 * SERVICE NAME : M_PPM_S02
 * FUNCTION : ActionBLogic
 * FILE NAME : M_PPM_S02AjaxBLogic.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 * History
 */

package nttdm.bsys.m_ppm.blogic;

import java.util.List;

import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_ppm.bean.Service;
import nttdm.bsys.m_ppm.dto.M_PPM_S02AjaxInput;
import nttdm.bsys.m_ppm.dto.M_PPM_S02AjaxOutput;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicResult;

/**
 * Ajax request<br/>
 * Get Service <b>Level2, Level3, Level4</b><br/>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class M_PPM_S02AjaxBLogic implements BLogic<M_PPM_S02AjaxInput> {
	private QueryDAO queryDAO;

	public BLogicResult execute(M_PPM_S02AjaxInput input) {
		BLogicResult result = new BLogicResult();
		String idService = input.getIdService();
		M_PPM_S02AjaxOutput output = new M_PPM_S02AjaxOutput();

		List<Service> cboSvcLevel2List = queryDAO.executeForObjectList("SELECT.M_PPM.S02.SVC_LEVEL2", idService);
		output.setCboSvcLevel2List(cboSvcLevel2List);
		
		List<Service> cboPlanList = queryDAO.executeForObjectList("SELECT.M_PPM.S02.SVC_LEVEL3", idService);
		output.setCboPlanList(cboPlanList);
		
		List<Service> cboPlanDetailList = queryDAO.executeForObjectList("SELECT.M_PPM.S02.SVC_LEVEL4", idService);
		output.setCboPlanDetailList(cboPlanDetailList);
		
		String ppmOptionSvc = CommonUtils.toString(queryDAO.executeForObject("SELECT.M_PPM.S02.PPM_OPTION_SVC", null, String.class)).trim();
		output.setPpmOptionSvc(ppmOptionSvc);
		
		// return object
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
