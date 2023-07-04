/*
 * @(#)B_BIFS02_04_01BLogic.java
 *
 *
 */
package nttdm.bsys.bif.blogic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.Globals;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.bif.dto.B_BIFS02Input;
import nttdm.bsys.bif.dto.B_BIFS03Input;
import nttdm.bsys.bif.dto.B_BIF_Output;

import nttdm.bsys.bif.blogic.AbstractB_BIFS02_04_01BLogic;
import nttdm.bsys.common.util.BillingSystemConstants;
/**
 * ビジネスロジッククラス。
 * 
 * @author duongnld
 */
public class B_BIFS02_04_01BLogic extends AbstractB_BIFS02_04_01BLogic {

	private static final String SAVE_ERROR_MSG = null;
	private static final String REJECT_STATUS_INFO = "SELECT.B_BIF.S03_02.SQL002";
	/**
	 * 
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	public BLogicResult execute(B_BIFS02Input input) {
		// TODO
		// QueryDAO 記述例
		// SampleUVO uvo = queryDAO.executeForObject("namespace.sqlID",
		// params, SampleUVO().class);
		//
		// UpdateDAO 記述例
		// updateDAO.execute("namespace.sqlID", params);
		//
		BLogicMessages errors = new BLogicMessages();
		BLogicResult result = new BLogicResult();		
		try{ 
			B_BIF_Output out = new B_BIF_Output();
			String idRef = input.getIdRef();
			out.setIdRef(idRef);
			
			String is_ObtainA = "0";

			Map<String , Object> screenObject = new HashMap<String, Object>();
			screenObject.put("is_ObtainA", is_ObtainA);
			
			//get condition to display obtain approval button
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("ID_REF", idRef);
			params.put("APPR_STATUS", "AS3");
			List<Map<String, Object>> rejectList = this.queryDAO.executeForMapList(REJECT_STATUS_INFO, params);
			if (rejectList.size() > 0) {
				screenObject.put("isObtainable", "true");
			} else {
				screenObject.put("isObtainable", "false");
			}

			out.setScreenObject(screenObject);
			
			out.setAction(B_BIFS03Input.EDIT2_ACTION);
			result.setResultString(B_BIFS03Input.EDIT2_ACTION);
			result.setResultObject(out);
			return result;
		} catch(Exception ex){
			ex.printStackTrace();
			errors.add(Globals.MESSAGE_KEY,new BLogicMessage(SAVE_ERROR_MSG));
			result.setErrors(errors);
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
			return result;
		}
	}
}