
/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_COM
 * SERVICE NAME   : M_COMS01
 * FUNCTION       : Get Data BLogic
 * FILE NAME      : M_COMS01_1getDataBLogic.java
 *
 * 
 * 
**********************************************************/
package nttdm.bsys.m_com.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.m_com.dto.M_COMS01_1getDataInput;
import nttdm.bsys.m_com.dto.M_COMS01_1getDataOutput;
import java.util.*;

import nttdm.bsys.m_com.blogic.AbstractM_COMS01_1getDataBLogic;

/**
 * BusinessLogic class.
 * 
 * @author helloworld
 */
public class M_COMS01_1getDataBLogic extends AbstractM_COMS01_1getDataBLogic {

	/**
	 * 
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	private static final int BANK_ID_POS=0;
	private static final int ID_COM_BANK_POS=1;
	private static final int ACC_NO_POS=2;
	private static final int ACC_TYPE_POS=3;
	private static final int ACC_NAME_POS=4;
	private static final int SWIFT_POS=5;
	
	private static final String ROW_DELIM=";";
	private static final String COL_DELIM=",";

	public BLogicResult execute(M_COMS01_1getDataInput param) {
		BLogicResult result = new BLogicResult();
		M_COMS01_1getDataOutput outputDTO =  new M_COMS01_1getDataOutput();
		// TODO
		// QueryDAO 記述例
		// SampleUVO uvo = queryDAO.executeForObject("namespace.sqlID",
		// params, SampleUVO().class);
		//
		// UpdateDAO 記述例
		// updateDAO.execute("namespace.sqlID", params);
		//
		
		String id_com = param.getId_com();
		String[] rows_data = param.getUpdate_data().split(ROW_DELIM);
		HashMap<String,Object> paramRow = null;
		ArrayList<HashMap<String, Object>> paramRows = new ArrayList<HashMap<String,Object>>();
		String accNo="", accName="",swiftCode="";
		
		if(rows_data.length > 0){
			for(String row:rows_data){
				String[] field = row.split(COL_DELIM);
				
				//not enough field or one of field is null continue
				if(field.length<6 ) continue;
				if(field[ACC_NO_POS].trim().equals("") || field[ACC_NAME_POS].trim().equals("") || field[SWIFT_POS].trim().equals("")) continue;
				
				//add to map 
				paramRow = new HashMap<String, Object>();
				accNo= field[ACC_NO_POS].trim().equals("") ? "0" : field[ACC_NO_POS].trim(); 
				accName= field[ACC_NAME_POS].trim().equals("") ? "0" : field[ACC_NAME_POS].trim();
				swiftCode= field[SWIFT_POS].trim().equals("") ? "0" : field[SWIFT_POS].trim();
				paramRow.put("ID_BANK",field[BANK_ID_POS].trim());
				paramRow.put("COM_SWIFT",swiftCode);
				paramRow.put("COM_ACCT_NO",accNo);
				paramRow.put("COM_ACCT_TYPE",field[ACC_TYPE_POS].trim());
				paramRow.put("COM_ACCT_NAME",accName);
				paramRow.put("id_com_bank",field[ID_COM_BANK_POS].trim());
				paramRow.put("ID_LOGIN",param.getUvo().getId_user());
				paramRow.put("ID_COM",id_com);
				//add to array list
				paramRows.add(paramRow);
			}
		}
		/*
		#ID_COM#,#ID_BANK#,#COM_SWIFT#,
		#COM_ACCT_NO#,#COM_ACCT_TYPE#,#COM_ACCT_NAME#,SYSDATE,SYSDATE,#ID_LOGIN#)		 */	
		outputDTO.setParamMap(paramRows);
		outputDTO.setId_com(id_com);
		result.setResultObject(outputDTO);
		result.setResultString("success");
		return result;
	}
}