package nttdm.bsys.m_cur.blogic;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessages;

import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_cur.dto.M_CURInput;

public class M_CURS02_EBlogic extends AbstractM_CURBLogic{
	/**
	 * <div>UpdateDAO</div>
	 */
	private UpdateDAO updateDAO = null;
	/**
	 * <div>UPDATE_SQL</div>
	 */
	private static final String UPDATE_SQL = "UPDATE.M_CUR.SQL001";
	/**
	 * <div>SAVE_ERROR_MSG</div>
	 */
	static final String SAVE_ERROR_MSG = "info.ERR2SC004";
	private static final String SAVE_SUCCESS_MSG = "info.ERR2SC003";

	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}

	/**
	 * <div>execute</div>
	 */

	public BLogicResult execute(M_CURInput arg0) {
		BLogicMessages errors = new BLogicMessages();
		BLogicResult result = new BLogicResult();	
		arg0.setId_login(arg0.getUvo().getId_user());
		try{
			/**
			 * Audit Trail
			 */
			Integer idAudit = CommonUtils.auditTrailBegin(arg0.getUvo().getId_user(), 
					BillingSystemConstants.MODULE_M, BillingSystemConstants.SUB_MODULE_M_CUR, 
					arg0.getRate_date()+":"+arg0.getCur_code(), null, 
					BillingSystemConstants.AUDIT_TRAIL_EDITED);
			arg0.setIdAudit(idAudit);
			//update data
			updateDAO.execute(UPDATE_SQL, arg0);
			CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
			BLogicMessages messages = new BLogicMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new BLogicMessage(SAVE_SUCCESS_MSG));
			result.setMessages(messages);
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
			return result;	
		}
		catch(Exception ex){
			errors.add(Globals.MESSAGE_KEY,new BLogicMessage(SAVE_ERROR_MSG));
			result.setErrors(errors);
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
			return result;
		} 
	}

}
