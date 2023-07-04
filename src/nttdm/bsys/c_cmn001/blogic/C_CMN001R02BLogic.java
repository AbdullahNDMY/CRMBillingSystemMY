/*
 * @(#)C_CMN001R02BLogic.java
 *
 *
 */
package nttdm.bsys.c_cmn001.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.c_cmn001.dto.C_CMN001R02Input;

import nttdm.bsys.c_cmn001.blogic.AbstractC_CMN001R02BLogic;

/**
 * BusinessLogic class.
 * 
 * @author ss052
 */
public class C_CMN001R02BLogic extends AbstractC_CMN001R02BLogic {

	/**
	 * 
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	public BLogicResult execute(C_CMN001R02Input param) {
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