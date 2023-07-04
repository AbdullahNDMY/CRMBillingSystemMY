package nttdm.bsys.a_usr.blogic;

import org.apache.struts.Globals;

import jp.terasoluna.fw.service.thin.*;
import nttdm.bsys.a_usr.dto.*;
import java.util.*;
import nttdm.bsys.a_usr.blogic.AbstractA_USR_S02_DeleteBLogic;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.dto.MessageString;

public class A_USR_S02_DeleteBLogic extends AbstractA_USR_S02_DeleteBLogic {

	public BLogicResult execute(A_USR_S02IO param) {
		BLogicResult result = new BLogicResult();
		BLogicMessages msg = new BLogicMessages();
		String messageString = "";
		String idUser = param.getIdUser();
		String idLogin = param.getIdLogin();
		try{
			/**
			 * Audit Trail
			 */
			Map<String, Object> user = queryDAO.executeForMap("SELECT.A_USER.GET_USER", idUser);
			//Modify "M_AUDIT_TRAIL_H.STATUS":1,M_USER.USER_STATUS = 1　"Active" 2,M_USER.USER_STATUS= 0　"Inactive"
			String userstatus="";
			if("1".equals(CommonUtils.toString(user.get("USER_STATUS")))){
			    userstatus="Active";
			}else{
			    userstatus="Inactive";
			}
			 
			Integer idAudit = CommonUtils.auditTrailBegin(idLogin, BillingSystemConstants.MODULE_A, 
					BillingSystemConstants.SUB_MODULE_A_USR,
					idUser + ":" + CommonUtils.toString(user.get("USER_NAME")),
					userstatus,
					BillingSystemConstants.AUDIT_TRAIL_DELETED);
			
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("idUser", idUser);
			map.put("idLogin", idLogin);
			map.put("idAudit", idAudit);
			
			updateDAO.execute("UPDATE.DELETE.A_USR.USER", map);
			CommonUtils.auditTrailEnd(idAudit);//End Audit Trail			
			//set success message and the destinaton for success case
			msg.add(Globals.MESSAGE_KEY, new BLogicMessage("info.ERR2SC005"));
			messageString = MessageString.SUCCESS;
			result.setMessages(msg);
		}
		catch (Exception e){
			System.out.println(e.getMessage());
			//set error message and the destinaton for error case
			msg.add(Globals.MESSAGE_KEY, new BLogicMessage("info.ERR2SC006"));
			messageString = MessageString.FAILURE;
			result.setErrors(msg);
		}
		
		result.setResultString(messageString);
		return result;
	}
}