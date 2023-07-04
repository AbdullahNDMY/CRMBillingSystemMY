/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : E_SET
 * SERVICE NAME   : E_SET_S03
 * FUNCTION       : Showing Log History
 * FILE NAME      : RP_E_SET_S03BLogic.java
 * 
 * Copyright © 2011 NTTDATA Corporation
 *
 * History
 * 
 * 
**********************************************************/
package nttdm.bsys.e_set.blogic;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.CommonUtils;

/**
 * RP_E_SET_S03BLogic<br>
 * <ul>
 * <li>A BLogic class to process displaying Batch log list
 * </ul>
 * <br>
 * @author  NTTData
 * @version 1.0
 */
public class RP_E_SET_S03BLogic extends AbstractRP_E_SET_S03BLogic {

	public BLogicResult execute(Map<String, Object> input) {
		BLogicResult result = new BLogicResult();
		Map<String, Object> output = new HashMap<String, Object>();
		
		String ID_BATCH = CommonUtils.toString(input.get("ID_BATCH"));
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("ID_BATCH", ID_BATCH);
		
		List<Map<String, Object>> logDetailList = this.getQueryDAO()
				.executeForMapList("E_SET.getLogDetailList", param);
		if (logDetailList!=null && 0<logDetailList.size()) {
		   for(Map<String, Object> map : logDetailList) {
		       Reader readerMsg = (Reader)map.get("MESSAGE");
		       String msg = CommonUtils.clobReaderToString(readerMsg);
		       map.put("MESSAGE", msg);
		   }
		}
		output.put("logDetailList", logDetailList);
		
		result.setResultObject(output);
		result.setResultString("success");
		return result;
	}
}
