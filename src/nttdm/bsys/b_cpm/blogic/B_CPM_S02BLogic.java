/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : Customer Plan Management (New)
 * SERVICE NAME   : B_CPM_S02
 * FUNCTION       : select to new customer plan by type 
 * FILE NAME      : B_CPM_S02BLogic.java
 * 
 * Copyright c 2011 NTTDATA Corporation
 *
 * History
 * 2011/07/26 [Duong Nguyen] Created
 * 
**********************************************************/
package nttdm.bsys.b_cpm.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nttdm.bsys.b_cpm.blogic.AbstractB_CPM_S02BLogic;
import nttdm.bsys.b_cpm_en.dto.B_CPM_CONSTANT;
import nttdm.bsys.common.util.CommonUtils;

/**
 * B_CPM_S02BLogic.class<br>
 * <ul>
 * <li>display create customer plan screen</li>
 * </ul>
 * <br>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class B_CPM_S02BLogic extends AbstractB_CPM_S02BLogic implements B_CPM_CONSTANT{

    private static final String ADD_STDPLN = "ADD_STDPLN";
    private static final String ADD_NON_STDPLN = "ADD_NON_STDPLN";
    private static final String ADD_STDPLN_MULTI = "ADD_STDPLN_MULTI";
    private static final String ENABLED_FLAG = "1";
	/**
	 * 
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	public BLogicResult execute(Map<String, Object> param) {
		BLogicResult result = new BLogicResult();
		//set result object
        Map<String, Object> resultObject = new HashMap<String, Object>();
        // used for Button btnAddStandard,btnAddStandardMul,btnAddNonStandard
		List<Map<String, Object>> listBtnDisplayFlg = queryDAO.executeForMapList(NAMESPACE + "SELECT_BUTTON_ISENABLED", null);
		if(listBtnDisplayFlg!=null && 0<listBtnDisplayFlg.size()) {
		    for(Map<String, Object> map : listBtnDisplayFlg) {
		        String idSet = CommonUtils.toString(map.get("ID_SET"));
		        String setValue = CommonUtils.toString(map.get("SET_VALUE"));
		        if (ADD_STDPLN.equals(idSet)) {
		            if (ENABLED_FLAG.equals(setValue)) {
		                resultObject.put(ADD_STDPLN, "1");
		            } else {
		                resultObject.put(ADD_STDPLN, "");
		            }
		        } else if (ADD_NON_STDPLN.equals(idSet)){
		            if (ENABLED_FLAG.equals(setValue)) {
                        resultObject.put(ADD_NON_STDPLN, "1");
                    } else {
                        resultObject.put(ADD_NON_STDPLN, "");
                    }
		        } else if (ADD_STDPLN_MULTI.equals(idSet)){
                    if (ENABLED_FLAG.equals(setValue)) {
                        resultObject.put(ADD_STDPLN_MULTI, "1");
                    } else {
                        resultObject.put(ADD_STDPLN_MULTI, "");
                    }
                }
		    }
		}
		result.setResultObject(resultObject);
		result.setResultString("success");
		return result;
	}
}