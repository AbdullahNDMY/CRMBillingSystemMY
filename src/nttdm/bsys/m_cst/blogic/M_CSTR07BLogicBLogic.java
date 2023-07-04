
/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_CST
 * SERVICE NAME   : M_CST07
 * FUNCTION       : Set result
 * FILE NAME      : M_CSTR07BLogicBLogic.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
**********************************************************/
package nttdm.bsys.m_cst.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.m_cst.dto.M_CSTR07BLogicInput;

/**
 * BusinessLogic class.
 * 
 * @author NTT Data VietNam
 */
public class M_CSTR07BLogicBLogic extends AbstractM_CSTR07BLogicBLogic {

	/**
	 * 
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	public BLogicResult execute(M_CSTR07BLogicInput param) {
		BLogicResult result = new BLogicResult();

		// TODO
		// QueryDAO 記述例
		// SampleUVO uvo = queryDAO.executeForObject("namespace.sqlID",
		// params, SampleUVO().class);
		//
		// UpdateDAO 記述例
		// updateDAO.execute("namespace.sqlID", params);
		//

		result.setResultString("success");
		return result;
	}
}