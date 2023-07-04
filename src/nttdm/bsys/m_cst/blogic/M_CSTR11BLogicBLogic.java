/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_CST
 * SERVICE NAME   : M_CSTS11
 * FUNCTION       : Delete Customer
 * FILE NAME      : M_CSTR11BLogicBLogic.java
 *
 * * Copyright © 2011 NTTDATA Corporation
 * 
**********************************************************/
package nttdm.bsys.m_cst.blogic;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_cst.common.Util;
import nttdm.bsys.m_cst.dto.M_CSTR11BLogicInput;
import org.apache.struts.Globals;

/**
 * BusinessLogic class<br>
 * <br>
 * <ul>
 * <li>
 * <li>BusinessLogic class
 * </ul>
 * <br>
* @author  NTTData Vietnam
* @version 1.1 
 */
public class M_CSTR11BLogicBLogic extends AbstractM_CSTR11BLogicBLogic {

	public BLogicResult execute(M_CSTR11BLogicInput param) {
		//Declare blogic result
		BLogicResult result = new BLogicResult();
		//Declare error message
		BLogicMessages errors = new BLogicMessages();
		//declare message
		BLogicMessages messages = new BLogicMessages();
		try{
			//set login_id
			param.setUvo(param.getUvoObject().getId_user());
			/**
			 * Audit Trail
			 */
			Integer idAudit = CommonUtils.auditTrailBegin(param.getUvoObject().getId_user(), 
					BillingSystemConstants.MODULE_M, BillingSystemConstants.SUB_MODULE_M_CST, CommonUtils.toString(param.getId_cust())
					+ ":" + CommonUtils.toString(param.getCust_name()), null,
					BillingSystemConstants.AUDIT_TRAIL_DELETED);
			param.setIdAudit(idAudit);
			
			//delete customer
			updateDAO.execute(Util.DELETE_M_CUST, param);
			CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
			//return message
			messages.add(Globals.MESSAGE_KEY, new BLogicMessage(Util.ERRORS_ERR2SC005));
			result.setMessages(messages);	
			//set return object
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
		}catch(Exception ex){
			//set error message when catch error
			errors.add(Globals.ERROR_KEY, new BLogicMessage(Util.ERRORS_ERR2SC006));
			result.setErrors(errors);
			//set return string is failure
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
		}
		return result;
	}
}