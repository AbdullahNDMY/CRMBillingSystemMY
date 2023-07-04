/**
 * Billing System
 * 
 * SUBSYSTEM NAME : Standard Price and Plan Maintenance
 * SERVICE NAME : M_PPM_S02
 * FUNCTION : DeleteBLogic
 * FILE NAME : M_PPM_S02DeleteBLogic.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 * History
 * 2011/10/21 [Duoc Nguyen] update to M_PPM_P1-11_r4_20111014.xlsx
 */

package nttdm.bsys.m_ppm.blogic;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_ppm.dto.M_PPM_S02DeleteInput;
import nttdm.bsys.m_ppm.dto.Plan;

import org.apache.struts.Globals;

/**
 * Delete BLogic<br/>
 * Delete Plan<br/>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class M_PPM_S02DeleteBLogic implements BLogic<M_PPM_S02DeleteInput> {
	private QueryDAO queryDAO;
	
	private UpdateDAO updateDAO;
		
	private static final String SAVE_ERROR_MSG = "info.ERR2SC006";
	private static final String SAVE_SUCCESS_MSG = "errors.ERR1SC023";
	
	public BLogicResult execute(M_PPM_S02DeleteInput input) {
		BLogicMessages errors = new BLogicMessages();
		BLogicResult result = new BLogicResult();		
		try{ 
			Integer idPlan = input.getIdPlan();
			Plan plan = queryDAO.executeForObject("SELECT.M_PPM.S02.HEADER", idPlan, Plan.class);
			String planName = plan.getTxtPlanName();
			/**
        	 * Audit Trail
        	 */
        	Integer idAudit = CommonUtils.auditTrailBegin(input.getUvo().getId_user(),
        		BillingSystemConstants.MODULE_M, BillingSystemConstants.SUB_MODULE_M_PPM, idPlan+":"+planName, null,
				BillingSystemConstants.AUDIT_TRAIL_DELETED);
			
        	plan.setIdLogin(input.getUvo().getId_user());
        	plan.setIdAudit(idAudit);
        	updateDAO.execute("DELETE.M_PPM.S02.HEADER_ALL", plan);
        	updateDAO.execute("DELETE.M_PPM.S02.DETAIL_ALL", plan);
        	updateDAO.execute("DELETE.M_PPM.S02.DETAIL_ALL.MAPPING", plan);
        	updateDAO.execute("DELETE.M_PPM.S02.SERVICE_ALL", plan);
        	
			CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
			BLogicMessages messages = new BLogicMessages();
			messages.add(SAVE_SUCCESS_MSG, 
					new BLogicMessage(SAVE_SUCCESS_MSG, planName));
			result.setMessages(messages);
			
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);		
			return result;  
		}
		catch(Exception ex){
			ex.printStackTrace();
			errors.add(Globals.MESSAGE_KEY,new BLogicMessage(SAVE_ERROR_MSG));
			result.setErrors(errors);
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
			return result;
		}
	}

	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}
}