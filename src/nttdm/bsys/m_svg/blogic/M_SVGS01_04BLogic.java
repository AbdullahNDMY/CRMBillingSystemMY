/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_SVG
 * SERVICE NAME   : M_SVG_S01
 * FUNCTION       : M_SVGS01_04BLogic
 * FILE NAME      : M_SVGS01_04BLogic.java
 * 
* Copyright © 2011 NTTDATA Corporation
*
 * History
 * 
 * 
**********************************************************/
package nttdm.bsys.m_svg.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.m_svg.dto.M_SVGS01_04Input;

import nttdm.bsys.m_svg.blogic.AbstractM_SVGS01_04BLogic;

/**
 * M_SVGS01_04BLogic<br>
 * <ul>
 * <li> BLogic class
 * </ul>
 * <br>
* @author  tuyenpv
* @version 1.0
 */
public class M_SVGS01_04BLogic extends AbstractM_SVGS01_04BLogic {

	/**
	 * 
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	public BLogicResult execute(M_SVGS01_04Input param) {
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