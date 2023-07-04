/*
 * @(#)B_BIFS01_01BLogic.java
 *
 *
 */
package nttdm.bsys.bif.blogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts.Globals;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.bif.dto.B_BIFS01_01Input;
import nttdm.bsys.bif.dto.B_BIF_Input;

import nttdm.bsys.bif.blogic.AbstractB_BIFS01_01BLogic;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_PAG_P01;
import nttdm.bsys.common.util.G_TBL_P01;

/**
 * ビジネスロジッククラス。
 * 
 * @author duongnld
 */
public class B_BIFS01_01BLogic extends AbstractB_BIFS01_01BLogic {

	private static final String SELECT_BIF_INFO = "SELECT.B_BIF.S03_01.SQL001";
	
	static final String SAVE_ERROR_MSG = "info.ERR2SC004";
	
	private static final String CN_TYPE = "BIFCN";
	private static final String DN_TYPE = "BIFDN";
	/**
	 * 
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	public BLogicResult execute(B_BIFS01_01Input input) {
		BLogicResult result = new BLogicResult();
		BLogicMessages errors = new BLogicMessages();
		// TODO
		// QueryDAO 記述例
		// SampleUVO uvo = queryDAO.executeForObject("namespace.sqlID",
		// params, SampleUVO().class);
		//
		// UpdateDAO 記述例
		// updateDAO.execute("namespace.sqlID", params);
		//
		try{ 
			String idRef = input.getIdRef();
			String idScreen = "";
			String bifType = "";
			String type = "";
			String idUser = input.getUvo().getId_user();
			//using for B-BIF-S03
			String action = "";
			Map<String, String> param = new HashMap<String, String>();
			param.put("ID_REF", paddingRightSpace(idRef, 20));
			//get BIF Information
			Map<String, Object> bifObject = queryDAO.executeForMap(SELECT_BIF_INFO, param);
			
			//check bif type
			bifType = bifObject.get("BIF_TYPE").toString();
			if (bifType != null) {
				if (bifType.equals("CN")) {
					idScreen = CN_TYPE;
				} else {
					idScreen = DN_TYPE;
				}
			} else {
				//record is deleted by another, return to main screen
				result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
				return result;
			}
			if (bifObject.get("ID_STATUS").equals("DS3") || 
					bifObject.get("ID_STATUS").equals("DS4") ||
					bifObject.get("ID_STATUS").equals("DS5")) {
				//display screen view or close
				String status = bifObject.get("IS_CLOSED").toString();
				if (status != null) {
					if(status.equals("0")) {
						type = "v";
					} else {
						type = "x";
					}
				}
				
			} else {
				ArrayList<String> arrResult = G_TBL_P01.execute(idScreen);
				G_PAG_P01 gPagP01 = new G_PAG_P01("", idUser, idRef, idScreen, queryDAO, "B-BIF");
				try {
					type = gPagP01.getPage();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Map<String, Object> out = new HashMap<String, Object>();
			
			if (type.equals("v")) {
				action = B_BIF_Input.VIEW_ACTION;
			} else if(type.equals("x")){
				action = B_BIF_Input.VIEW_ACTION;
			} else if(type.equals("a")){
				action = B_BIF_Input.APPROVAL_ACTION;
			} else if(type.equals("s")){
				action = B_BIF_Input.SECTION_ACTION;
			} else if(type.equals("e")){
				action = B_BIF_Input.EDIT_ACTION;
			} else {
				//display error
			}
			
			//always display view screen
			action = B_BIF_Input.VIEW_ACTION;			
			out.put("idRef", paddingRightSpace(idRef, 20));
			out.put("action", action);
			result.setResultObject(out);
			result.setResultString(idScreen);
			return result;
		}
		catch(Exception ex){
			errors.add(Globals.MESSAGE_KEY,new BLogicMessage(SAVE_ERROR_MSG));
			result.setErrors(errors);
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
			return result;
		}
	}
	
	/**
     * padding right space
     * @param str
     * @param len
     * @return
     */
    private static String paddingRightSpace(String str, int len) {
        StringBuffer sb = new StringBuffer();
        str = CommonUtils.toString(str);
        sb.append(str);
        for(int i=str.length();i<len;i++) {
            sb.append(" ");
        }
        return sb.toString();
    }
}