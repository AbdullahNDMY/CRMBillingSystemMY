/*
 * @(#)M_ALTR04BLogic.java
 *
 *
 */
package nttdm.bsys.m_alt.blogic;

import org.apache.struts.Globals;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.m_alt.dto.M_ALTR04Input;

import nttdm.bsys.m_alt.blogic.AbstractM_ALTR04BLogic;

/**
 * BusinessLogic class.
 * 
 * @author ss051
 */
public class M_ALTR04BLogic extends AbstractM_ALTR04BLogic {

	static final String SQL_DELETE_M_ALT_H = "UPDATE.M_ALT.SQL010";
	static final String SQL_DELETE_M_ALT_D = "UPDATE.M_ALT.SQL011";
	static final String DELETE_SUCCESSFUL_MSG = "info.ERR2SC005";
	static final String DELETE_ERROR_MSG = "info.ERR2SC006";
	
	public BLogicResult execute(M_ALTR04Input param) {
		BLogicResult result = new BLogicResult();
		BLogicMessages errors = new BLogicMessages();
		BLogicMessages messages = new BLogicMessages();
		try{
			param.setId_user(param.getUvo().getId_user());	
			updateDAO.execute(SQL_DELETE_M_ALT_D, param);
			Integer detailCount = queryDAO.executeForObject("SELECT.M_ALT.SQL011", param, Integer.class);
			if(detailCount <= 0)		
				updateDAO.execute(SQL_DELETE_M_ALT_H, param);
			
			messages.add(Globals.MESSAGE_KEY,new BLogicMessage(M_ALTR04BLogic.DELETE_SUCCESSFUL_MSG));
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
			result.setMessages(messages);
			return result;
			
		}catch(Exception ex){
			errors.add(Globals.MESSAGE_KEY,new BLogicMessage(M_ALTR04BLogic.DELETE_ERROR_MSG));
			result.setErrors(errors);
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
			return result;
		}
		
	}
}