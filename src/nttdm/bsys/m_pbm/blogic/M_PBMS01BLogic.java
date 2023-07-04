/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_PBM
 * SERVICE NAME   : M_PBM_S01
 * FUNCTION       : PlanBatchMappingBLogic
 * FILE NAME      : M_PBMS01BLogic.java
 * 
* Copyright © 2011 NTTDATA Corporation
*
 * History
 * 
 * 
**********************************************************/
package nttdm.bsys.m_pbm.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.m_pbm.dto.M_PBMS01Input;
import nttdm.bsys.m_pbm.dto.M_PBMS01Output;

import nttdm.bsys.m_pbm.blogic.AbstractM_PBMS01BLogic;

/**
 * M_PBMS01BLogic<br>
 * <ul>
 * <li>BusinessLogic class.
 * </ul>
 * <br>
* @author  NTTData Vietnam
* @version 1.0
 */
public class M_PBMS01BLogic extends AbstractM_PBMS01BLogic {

	/**
	 * 
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	public BLogicResult execute(M_PBMS01Input param) {
		M_PBMS01Output outputDTO = new M_PBMS01Output();
		BLogicResult result = new BLogicResult();

		// TODO
		// QueryDAO 記述例
		// SampleUVO uvo = queryDAO.executeForObject("namespace.sqlID",
		// params, SampleUVO().class);
		//
		// UpdateDAO 記述例
		// updateDAO.execute("namespace.sqlID", params);
		//
		/*{
		outputDTO.setPlan_batch(param.getPlan_batch());
		outputDTO.setMessage(param.getMessage());
		outputDTO.setId_plan_grp(param.getId_plan_grp());
		outputDTO.setCbo_code_value(param.getCbo_code_value());
		outputDTO.setCbo_uom(param.getCbo_uom());
		outputDTO.setNew_info(param.getNew_info());
		outputDTO.setId_batch_new(param.getId_batch_new());
	    outputDTO.setPlan_id_new(param.getPlan_id_new());
		outputDTO.setMode(param.getMode());
		outputDTO.setPlan_list(param.getPlan_list());
		outputDTO.setEnable_delete(param.getEnable_delete());
		outputDTO.setPlan_name(param.getPlan_name());
		outputDTO.setBatch_name(param.getPlan_name());
		outputDTO.setPlan_desc(param.getPlan_desc());		
		outputDTO.setPlan_id(param.getPlan_id());		
		outputDTO.setId_batch(param.getId_batch());
		outputDTO.setSearch_info(param.getSearch_info());		
		outputDTO.setTotal_plan(param.getTotal_plan());
		outputDTO.setIp(param.getIp());
		outputDTO.setRp(param.getRp());
		result.setResultObject(outputDTO);
		*/
		result.setResultString("success");
		return result;
	}
}