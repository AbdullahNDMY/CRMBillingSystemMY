/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : A_USER_S02
 * SERVICE NAME   : A_USER
 * FUNCTION       : Check data for create new user 
 * FILE NAME      : A_USER_S02_SaveBLogic.java
 * 
 * Copyright c 2011 NTTDATA Corporation
 *
 * History
 * 2011/09/13 [Hoang Duong] Update
 * 
**********************************************************/
package nttdm.bsys.a_usr.blogic;

import java.util.*;

import org.apache.struts.Globals;
import jp.terasoluna.fw.service.thin.*;
import jp.terasoluna.fw.util.PropertyUtil;
import nttdm.bsys.a_usr.dto.*;

import nttdm.bsys.a_usr.blogic.AbstractA_USR_S02_SaveBLogic;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.SecurityUtil;
import nttdm.bsys.common.util.dto.MessageString;
import nttdm.bsys.m_cst.common.Util;

/**
 * A_USR_S02_SaveBLogic.class<br>
 * <ul>
 * <li>Check data to create new user</li>
 * </ul>
 * <br>
 * @author  NTTData Vietnam
 * @version 1.1
 */

public class A_USR_S02_SaveBLogic extends AbstractA_USR_S02_SaveBLogic {
	private static final String ERR_FILE_SIZE_UPLOAD = "errors.ERR1SC027";

	public BLogicResult execute(A_USR_S02IO input) {
		BLogicResult result = new BLogicResult();
		BLogicMessages msg = new BLogicMessages();
		String messageString = "";
		String idLogin = input.getIdLogin();
		try {
			User user = input.getUser();
			user.setIdUser(user.getIdUser().trim());
			
			// check size of photo
			// Get system settings
			BillingSystemSettings systemSetting = new BillingSystemSettings(queryDAO);					
			//Get max file size (in MBytes)
			int fileSize = systemSetting.getUsrMnt();
			int maxFileSize = fileSize * 1024;
			
			byte[] photoBytes = user.getPhotoByte();
			
			if (photoBytes != null && photoBytes.length > maxFileSize) {
				BLogicMessages errors = new BLogicMessages();
				errors.add(Globals.MESSAGE_KEY, new BLogicMessage(ERR_FILE_SIZE_UPLOAD, new Object[] {fileSize, " Kb"}));
				result.setErrors(errors);
				result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
				return result;
			}
			
			List<UserAccess> listUserAccess = input.getListUserAccess();
			user.setIdLogin(idLogin);
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
					BillingSystemConstants.SUB_MODULE_A_USR, input.getUser().getIdUser() + ":"
							+ input.getUser().getUserName(), userstatus,
					BillingSystemConstants.AUDIT_TRAIL_CREATED);
			user.setIdAudit(idAudit);
			String idUser = user.getIdUser();			
			Map<String, Object> checkUser= queryDAO.executeForMap("SELECT.A_USER.GET_USER", idUser);
			if(checkUser != null){
				// set error message and the destinaton for error case
				msg.add(Globals.ERROR_KEY, new BLogicMessage(Util.ERRORS_ERR1SC006,  PropertyUtil.getProperty("screen.a_usrs01.userid")));
				messageString = MessageString.FAILURE;
				result.setErrors(msg);				
			}
			// insert new user
			else 			
            user.setPassword(SecurityUtil.md5(user.getPassword()));
			updateDAO.execute("INSERT.A_USR.USER", user);
			
            String accessRight = user.getAccessRight();
            if ("R".equals(accessRight)) {
                listUserAccess = queryDAO.executeForObjectList("SELECT.A_USER.GET_LIST_ROLEACCESS", user.getIdRole());
            }
			// insert access type for the new user
			for (int i = 0; i < listUserAccess.size(); i++) {
				UserAccess access = listUserAccess.get(i);
				access.setIdLogin(idLogin);
				access.setIdUser(idUser);
				access.setIdAudit(idAudit);
				updateDAO.execute("INSERT.A_USR.USERACCESS", access);
			}			
			CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
			messageString = MessageString.SUCCESS;
			A_USR_S02IO output = new A_USR_S02IO();
			output.setIdUser(idUser);
			result.setResultObject(output);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// set error message and the destination for error case			
			if(! msg.get().hasNext()) msg.add(Globals.MESSAGE_KEY, new BLogicMessage("info.ERR2SC004"));
			messageString = MessageString.FAILURE;
			result.setErrors(msg);
		}
		result.setResultString(messageString);
		return result;
	}
}