/*
 * @(#)B_BIFS03_02BLogic.java
 *
 *
 */
package nttdm.bsys.bif.blogic;

import java.util.HashMap;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.bif.dto.B_BIFS02Input;
import nttdm.bsys.bif.dto.B_BIFS03Input;
import nttdm.bsys.bif.dto.B_BIF_Output;

import nttdm.bsys.bif.blogic.AbstractB_BIFS03_02BLogic;

/**
 * ビジネスロジッククラス。
 * 
 * @author duongnld
 */
public class B_BIFS03_02BLogic extends AbstractB_BIFS03_02BLogic {

	//private static final String STATUS_INFO = "SELECT.B_BIF.S03_02.SQL001";
	private static final String FIRST_STATUS_INFO = "SELECT.B_BIF.S03_02.SQL001_01";
	
	/**
	 * 
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	public BLogicResult execute(B_BIFS03Input input) {
		BLogicResult result = new BLogicResult();

		// TODO
		// QueryDAO 記述例
		// SampleUVO uvo = queryDAO.executeForObject("namespace.sqlID",
		// params, SampleUVO().class);
		//
		// UpdateDAO 記述例
		// updateDAO.execute("namespace.sqlID", params);
		//
		
		B_BIF_Output out = new B_BIF_Output();
		
		String idRef = input.getIdRef();
		
		out.setIdRef(idRef);
		
		String is_ObtainV = "0";
		String is_ObtainA = "0";
		
		Map<String , Object> screenObject = new HashMap<String, Object>();
		screenObject.put("is_ObtainV", is_ObtainV);
		screenObject.put("is_ObtainA", is_ObtainA);
		out.setScreenObject(screenObject);
		
		Map<String, String> param = new HashMap<String, String>();
		param.put("ID_REF", idRef);
		param.put("ID_USER", input.getUvo().getId_user());
		param.put("sectionNo", "C000");
		Map<String, Object> statsuInfo = queryDAO.executeForMap(FIRST_STATUS_INFO, param);
		if(statsuInfo.size() != 0) {
			int not_appr_no = Integer.parseInt(statsuInfo.get("NOT_APPR_NO").toString());
			if (not_appr_no > 0) {
				out.setAction(B_BIFS02Input.EDIT2_ACTION);
				result.setResultString(B_BIFS02Input.EDIT2_ACTION);
				result.setResultObject(out);
				return result;
			}
		}
		
		out.setAction(B_BIFS03Input.EDIT_ACTION);
		result.setResultString(B_BIFS03Input.EDIT_ACTION);
		result.setResultObject(out);
		return result;
	}
}