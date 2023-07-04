
/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_COM
 * SERVICE NAME   : M_COMS01
 * FUNCTION       : Delete data BLogic
 * FILE NAME      : M_COMS01DeleteBLogic.java
 *
 * 
 * 
**********************************************************/

package nttdm.bsys.m_com.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.m_com.dto.M_COMS01DeleteInput;
import nttdm.bsys.m_com.dto.M_COMS01DeleteOutput;
import nttdm.bsys.m_com.blogic.AbstractM_COMS01DeleteBLogic;

/**
 * BusinessLogic class.
 * @author NTT Data Vietnam	
 */
public class M_COMS01DeleteBLogic extends AbstractM_COMS01DeleteBLogic {

	/**
	 * 
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	private static final String sql_delete = "DELETE.COM.SQLDelete001";
	
	public BLogicResult execute(M_COMS01DeleteInput param) {
		BLogicResult result = new BLogicResult();

		String id_com_bank = param.getId_com_bank();
		updateDAO.execute(M_COMS01DeleteBLogic.sql_delete, id_com_bank);
		
		// TODO
		// QueryDAO 記述例
		// SampleUVO uvo = queryDAO.executeForObject("namespace.sqlID",
		// params, SampleUVO().class);
		//
		// UpdateDAO 記述例
		// updateDAO.execute("namespace.sqlID", params);
		//

		M_COMS01DeleteOutput outputDTO = new M_COMS01DeleteOutput();
		result.setResultObject(outputDTO);
		result.setResultString("success");
		return result;
	}
}