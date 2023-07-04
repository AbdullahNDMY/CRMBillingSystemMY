package nttdm.bsys.bif.blogic;

import java.util.HashMap;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.bif.dto.B_BIFS02Input;
import nttdm.bsys.bif.dto.B_BIF_Output;
import nttdm.bsys.common.util.BillingSystemConstants;

import org.apache.struts.Globals;

public class B_BIFS02_04BLogic extends AbstractB_BIFS02_04BLogic{
	
	static final String SAVE_ERROR_MSG = "info.ERR2SC004";
	//private static final String STATUS_INFO = "SELECT.B_BIF.S03_02.SQL001";
	private static final String FIRST_STATUS_INFO = "SELECT.B_BIF.S03_02.SQL001_01";
	
	public BLogicResult execute(B_BIFS02Input input) {
		// TODO Auto-generated method stub
		BLogicMessages errors = new BLogicMessages();
		BLogicResult result = new BLogicResult();		
		try{ 
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
			param.put("sectionNo", "B000");
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
			out.setAction(B_BIFS02Input.EDIT_ACTION);
			result.setResultString(B_BIFS02Input.EDIT_ACTION);
			result.setResultObject(out);
			return result;
		}
		catch(Exception ex){
			ex.printStackTrace();
			errors.add(Globals.MESSAGE_KEY,new BLogicMessage(SAVE_ERROR_MSG));
			result.setErrors(errors);
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
			return result;
		}
	}
	
}
