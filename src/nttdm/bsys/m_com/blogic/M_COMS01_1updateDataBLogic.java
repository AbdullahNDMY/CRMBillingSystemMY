/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_COM
 * SERVICE NAME   : M_COMS01
 * FUNCTION       : Update data BLogic
 * FILE NAME      : M_COMS01_1updateDataBLogic.java
 *
 * 
 * 
**********************************************************/
package nttdm.bsys.m_com.blogic;

import java.util.ArrayList;
import java.util.HashMap;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.m_com.dto.M_COMS01_1updateDataInput;
import nttdm.bsys.m_com.dto.M_COMS01_1updateDataOutput;

import nttdm.bsys.m_com.blogic.AbstractM_COMS01_1updateDataBLogic;

/**
 * BusinessLogic class.
 * @author NTT Data Vietnam	
 */
public class M_COMS01_1updateDataBLogic extends
		AbstractM_COMS01_1updateDataBLogic {

	/**
	 * 
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 * 
	 */
	private static final String SQL_UPDATE = "UPDATE.COM.SQLS01_1UPDATE001";
	private static final String SQL_INSERT = "INSERT.COM.SQLS01_1INSERT001";
	public BLogicResult execute(M_COMS01_1updateDataInput param) {
		BLogicResult result = new BLogicResult();
		M_COMS01_1updateDataOutput outputDTO = new M_COMS01_1updateDataOutput();
		// TODO
		// QueryDAO 記述例
		// SampleUVO uvo = queryDAO.executeForObject("namespace.sqlID",
		// params, SampleUVO().class);
		//
		// UpdateDAO 記述例
		// updateDAO.execute("namespace.sqlID", params);
		//
		/*
		 * delete all old data 
		 */
		this.updateDAO.execute("DELETE_ALL.COM.SQLS001_1DELETEDS01", null);
		ArrayList<HashMap<String, Object>> pMap = param.getParamMap();
				
		for(HashMap<String,Object> aRow:pMap){
			//String idComBank = (String)aRow.get("ID_COM_BANK");
			String idComBank ="";
			if (idComBank==null || idComBank.equals("")){
				//insert
				this.updateDAO.execute(M_COMS01_1updateDataBLogic.SQL_INSERT, aRow);
			}else{
				//update
				this.updateDAO.execute(M_COMS01_1updateDataBLogic.SQL_UPDATE, aRow);				
			}
		}
		outputDTO.setId_com(param.getId_com());
		result.setResultString("success");
		return result;
	}
}