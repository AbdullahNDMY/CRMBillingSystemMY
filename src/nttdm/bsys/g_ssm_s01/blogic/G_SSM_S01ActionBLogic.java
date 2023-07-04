/*
 * @(#)G_SSM_S01ActionBLogic.java
 *
 *
 */
package nttdm.bsys.g_ssm_s01.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.g_ssm_s01.dto.G_SSM_S01ActionInput;

import nttdm.bsys.g_ssm_s01.blogic.AbstractG_SSM_S01ActionBLogic;

/**
 * BusinessLogic class.
 * 
 * @author khaln
 */
public class G_SSM_S01ActionBLogic extends AbstractG_SSM_S01ActionBLogic {

	/**
	 * 
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	public BLogicResult execute(G_SSM_S01ActionInput param) {
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