/*
 * @(#)RP_B_BIL_S03_02BLogic.java
 *
 *
 */
package nttdm.bsys.b_bil.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_bil.blogic.AbstractRP_B_BIL_S03_02BLogic;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S03_02Input;


/**
 * BusinessLogic class.
 * 
 * @author khungl0ng
 */
public class RP_B_BIL_S03_02BLogic extends AbstractRP_B_BIL_S03_02BLogic {

	/**
	 * 
	 * @param param
	 * @return ビジ�?スロジック�?�実行�?果�?BLogicResultインスタンス。
	 */
	public BLogicResult execute(RP_B_BIL_S03_02Input param) {
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