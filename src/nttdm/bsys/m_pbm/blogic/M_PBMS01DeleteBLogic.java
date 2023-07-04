/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_PBM
 * SERVICE NAME   : M_PBM_S01
 * FUNCTION       : PlanBatchMappingDeleteBLogic
 * FILE NAME      : M_PBMS01DeleteBLogic.java
 * 
* Copyright © 2011 NTTDATA Corporation
*
 * History
 * 
 * 
**********************************************************/
package nttdm.bsys.m_pbm.blogic;

import java.util.HashMap;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_pbm.dto.M_PBMS01DeleteInput;
import nttdm.bsys.m_pbm.dto.M_PBMS01DeleteOutput;

/**
 * M_PBMS01DeleteBLogic<br>
 * <ul>
 * <li>BusinessLogic class.
 * </ul>
 * <br>
* @author  NTTData Vietnam
* @version 1.0
 */
public class M_PBMS01DeleteBLogic extends AbstractM_PBMS01DeleteBLogic {

	private static final String SELECT_DELETE = "DELETE.20";
	public BLogicResult execute(M_PBMS01DeleteInput param) {
		BLogicResult result = new BLogicResult();
		M_PBMS01DeleteOutput outputDTO = new M_PBMS01DeleteOutput();		

		String planId = param.getPlan_id();
		/**
		 * Audit Trail
		 */
		String id_plan_grp = param.getId_plan_grp();
		if(id_plan_grp != null)
			id_plan_grp = id_plan_grp.substring(1);
		Integer idAudit = CommonUtils.auditTrailBegin(param.getUvo().getId_user(), BillingSystemConstants.MODULE_M, 
				BillingSystemConstants.SUB_MODULE_M_PBM, id_plan_grp+":"+param.getId_batch(), null, BillingSystemConstants.AUDIT_TRAIL_DELETED);
		
		int del_result=0;
		String message="ERR2SC005";
		try{
		    Map<String, Object> sqlParam = new HashMap<String, Object>();
		    sqlParam.put("plan_id", planId);
		    sqlParam.put("batchId", param.getId_batch());
			del_result = updateDAO.execute(SELECT_DELETE, sqlParam);
		}catch(Exception ex){
			ex.printStackTrace();
			message="ERR2SC006";
		}
		CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
		outputDTO.setMessage(message);
		outputDTO.setDeleted(String.valueOf(del_result));
		outputDTO.setId_batch(param.getId_batch());
		outputDTO.setMode("search");
		//outputDTO.setPlan_id(param.getPlan_id());
		result.setResultObject(outputDTO);		
		result.setResultString("success");
		return result;
	}
}