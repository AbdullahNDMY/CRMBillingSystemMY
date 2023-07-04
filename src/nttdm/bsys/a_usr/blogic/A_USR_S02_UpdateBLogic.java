package nttdm.bsys.a_usr.blogic;

import java.util.*;

import org.apache.struts.Globals;

import jp.terasoluna.fw.service.thin.*;
import nttdm.bsys.a_usr.dto.*;

import nttdm.bsys.a_usr.blogic.AbstractA_USR_S02_UpdateBLogic;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.SecurityUtil;
import nttdm.bsys.common.util.dto.MessageString;

public class A_USR_S02_UpdateBLogic extends AbstractA_USR_S02_UpdateBLogic {
	private static final String ERR_IMAGE_FILE_SIZE_UPLOAD = "errors.ERR1SC027";

	public BLogicResult execute(A_USR_S02IO input) {
		BLogicResult result = new BLogicResult();
		BLogicMessages msg = new BLogicMessages();
		String messageString = "";
		String idLogin = input.getIdLogin();
		try {
			User user = input.getUser();
			
			// check size of photo
			// Get system settings
			BillingSystemSettings systemSetting = new BillingSystemSettings(queryDAO);					
			//Get max file size (in KBytes)
			int fileSize = systemSetting.getUsrMnt();
			int maxFileSize = fileSize * 1024;
			
			byte[] photoBytes = user.getPhotoByte();
			
			if (photoBytes != null && photoBytes.length > maxFileSize) {
				BLogicMessages errors = new BLogicMessages();
				errors.add(Globals.MESSAGE_KEY, new BLogicMessage(ERR_IMAGE_FILE_SIZE_UPLOAD, new Object[] {fileSize, " Kb"}));
				result.setErrors(errors);
				result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
				return result;
			}
			
			/**
			 * Audit Trail
			 */
			//Modify "M_AUDIT_TRAIL_H.STATUS":1,M_USER.USER_STATUS = 1　"Active" 2,M_USER.USER_STATUS= 0　"Inactive"
			String userstatus="";
            if("1".equals(input.getUser().getUserStatus())){
                userstatus="Active";
            }else {
                userstatus="Inactive";
            }
			Integer idAudit = CommonUtils.auditTrailBegin(idLogin, BillingSystemConstants.MODULE_A,
					BillingSystemConstants.SUB_MODULE_A_USR, user.getIdUser() + ":" + user.getUserName(),
					userstatus,
					BillingSystemConstants.AUDIT_TRAIL_EDITED);
			user.setIdAudit(idAudit);
			
			List<UserAccess> listUserAccess = input.getListUserAccess();
			user.setIdLogin(idLogin);
			//update User information
			if("BILLINGSYSTEM".equals(user.getPassword())){
			user.setPassword(null);
			}else{
			user.setPassword(SecurityUtil.md5(user.getPassword()));
			}
			updateDAO.execute("UPDATE.A_USR.USER", user);
			
            String accessRight = user.getAccessRight();
            if ("R".equals(accessRight)) {
                listUserAccess = queryDAO.executeForObjectList("SELECT.A_USER.GET_LIST_ROLEACCESS", user.getIdRole());
            }
			// update user access
			String idUser = user.getIdUser();
			for(int i = 0; i < listUserAccess.size();i++){
				UserAccess access = listUserAccess.get(i);
				access.setIdLogin(idLogin);
				access.setIdUser(idUser);
				access.setIdAudit(idAudit);
				String count = queryDAO.executeForObject("SELECT.A_USR.USERACCESS.checknull", access, String.class);
				if("0".equals(count)){
					updateDAO.execute("INSERT.A_USR.USERACCESS.checknull", access);
				}else{
					updateDAO.execute("UPDATE.A_USR.USERACCESS", access);
				}
			}
			CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
			messageString = MessageString.SUCCESS;
			A_USR_S02IO output = new A_USR_S02IO();
			output.setIdUser(idUser);
			result.setResultObject(output);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// set error message and the destinaton for error case
			msg.add(Globals.MESSAGE_KEY, new BLogicMessage("info.ERR2SC004"));
			messageString = MessageString.FAILURE;
			result.setErrors(msg);
		}
		result.setResultString(messageString);
		return result;
	}
}