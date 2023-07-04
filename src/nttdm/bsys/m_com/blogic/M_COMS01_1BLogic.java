
/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_COM
 * SERVICE NAME   : M_COMS01
 * FUNCTION       : Get Data BLogic
 * FILE NAME      : M_COMS01_1BLogic.java
 *
 * 
 * 
**********************************************************/
package nttdm.bsys.m_com.blogic;

import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.m_com.dto.M_COMS01_1Input;
import nttdm.bsys.m_com.dto.M_COMS01_1Output;

import nttdm.bsys.m_com.blogic.AbstractM_COMS01_1BLogic;

/**
 * BusinessLogic class.
 * @author NTT Data Vietnam	
 */
public class M_COMS01_1BLogic extends AbstractM_COMS01_1BLogic {

	/**
	 * 
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	private String rowDelim=";";
	private String colDelim=",";
	private static final String sql_getData = "SELECT.COM.SQL007";
	private static final String sql_getBank = "SELECT.COM.SQL008";
	public BLogicResult execute(M_COMS01_1Input param) {
		BLogicResult result = new BLogicResult();
		M_COMS01_1Output outputDTO = new M_COMS01_1Output();

		// TODO
		// QueryDAO 記述例
		// SampleUVO uvo = queryDAO.executeForObject("namespace.sqlID",
		// params, SampleUVO().class);
		//
		// UpdateDAO 記述例
		// updateDAO.execute("namespace.sqlID", params);
		//a
		String id_com = param.getId_com();
		if(id_com.equals("")) id_com="1";
		String size="0";		
		List<Map<String,Object>> giro = queryDAO.executeForMapList(M_COMS01_1BLogic.sql_getData, id_com);
		if(giro!=null){
			size=String.valueOf(giro.size());
		}
		List<Map<String,Object>> bankInfo = queryDAO.executeForMapList(M_COMS01_1BLogic.sql_getBank, null);
		//build bank data, row seperated by ;
		String strRow="";
		if(bankInfo!=null){			
			for(Map<String,Object> m:bankInfo){
				strRow+= m.get("ID_BANK").toString()+colDelim+ m.get("BANK_CODE").toString()+ colDelim+ m.get("BRANCH_CODE").toString()+rowDelim;
			}
		}
		outputDTO.setBank_info_string(strRow);
		outputDTO.setBank_info(bankInfo);
		outputDTO.setGiro(giro);
		outputDTO.setGiro_size(size);
		outputDTO.setId_com(id_com);
		result.setResultObject(outputDTO);
		result.setResultString("success");
		return result;
	}
}