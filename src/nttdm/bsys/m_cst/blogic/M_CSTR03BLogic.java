/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_CST
 * SERVICE NAME   : M_CST03
 * FUNCTION       : DELETE_M_CUST
 * FILE NAME      : M_CSTR03BLogic.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
**********************************************************/
package nttdm.bsys.m_cst.blogic;

import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_cst.dto.M_CSTR03Input;
import org.apache.struts.Globals;

/**
 * BusinessLogic class.
 * 
 * @author NTT Data VietNam
 */
public class M_CSTR03BLogic extends AbstractM_CSTR03BLogic{
	
	/**
	 * <div>updateDAO class</div> 
	 */
	private UpdateDAO updateDAO=null;
	
	/**
	 * <div>DELETE_M_CUST</div> 
	 */
	private static final String DELETE_M_CUST = "SELECT.M_CST.SQL013";
	
	/**
	 * <div>ERRORS_ERR2SC006</div>
	 */
	private static final String ERRORS_ERR2SC006 = "info.ERR2SC006";
	/**
	 * <div>Error message</div>
	 */
	private static final String ERRORS_ERR2SC005 = "info.ERR2SC005";
	
	public BLogicResult execute(M_CSTR03Input param) {
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
			Integer idAudit = CommonUtils.auditTrailBegin(param.getUvoObject().getId_user(), BillingSystemConstants.MODULE_M, BillingSystemConstants.SUB_MODULE_M_CST, 
					CommonUtils.toString(param.getId_cust())+":"+CommonUtils.toString(param.getCust_name()), 
					null, BillingSystemConstants.AUDIT_TRAIL_DELETED);
			param.setIdAudit(idAudit);
			
			//delete customer
			updateDAO.execute(M_CSTR03BLogic.DELETE_M_CUST, param);
			CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
			//return message
			messages.add(Globals.MESSAGE_KEY, new BLogicMessage(M_CSTR03BLogic.ERRORS_ERR2SC005));
			result.setMessages(messages);	
			//set return object
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
		}catch(Exception ex){
			//set error message when catch error
			errors.add(Globals.ERROR_KEY, new BLogicMessage(M_CSTR03BLogic.ERRORS_ERR2SC006));
			result.setErrors(errors);
			//set return string is failure
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
		}
		return result;
	}
	
	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}
	
	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}
}