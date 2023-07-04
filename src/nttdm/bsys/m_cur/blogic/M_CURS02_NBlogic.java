package nttdm.bsys.m_cur.blogic;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessages;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_cur.bean.M_CURBean;
import nttdm.bsys.m_cur.dto.M_CURInput;

public class M_CURS02_NBlogic extends AbstractM_CURBLogic{
	/**
	 * <div>UpdateDAO</div>
	 */
	private UpdateDAO updateDAO = null;
	/**
	 * <div>queryDAO</div>
	 */
	private QueryDAO queryDAO = null; 
	/**
	 * <div>UPDATE_SQL</div>
	 */
	private static final String UPDATE_SQL = "INSERT.M_CUR.SQL001";
	/**
	 * <div>SELECT_DUPLICATE</div>
	 */
	private static final String SELECT_DUPLICATE = "SELECT.M_CUR.SQL004";
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

	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	/**
	 * <div>execute</div>
	 */
	
	public BLogicResult execute(M_CURInput arg0) {
		BLogicMessages errors = new BLogicMessages();
		BLogicResult result = new BLogicResult();		
		try{
			//check duplicate data 
			if(getTotalCount(arg0)==0)
			{
				/**
				 * Audit Trail
				 */
				Integer idAudit = CommonUtils.auditTrailBegin(arg0.getUvo().getId_user(), 
						BillingSystemConstants.MODULE_M, BillingSystemConstants.SUB_MODULE_M_CUR, 
						arg0.getRate_date()+":"+arg0.getCur_code(), null, 
						BillingSystemConstants.AUDIT_TRAIL_CREATED);
				arg0.setIdAudit(idAudit);
				arg0.setId_login(arg0.getUvo().getId_user());
				updateDAO.execute(UPDATE_SQL, arg0);
				CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
				BLogicMessages messages = new BLogicMessages();
				messages.add(ActionMessages.GLOBAL_MESSAGE, new BLogicMessage(SAVE_SUCCESS_MSG));
				result.setMessages(messages);
				result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
			}
			else
			{
				//duplicate data 
				M_CURBean m_cur = new M_CURBean();
				m_cur.setCur_code(arg0.getCur_code());
				m_cur.setRate_date(arg0.getRate_date());
				m_cur.setRate_from(arg0.getRate_from());
				m_cur.setRate_to(arg0.getRate_to());
				m_cur.setIndex("-1");
				//save search data
				m_cur.setCur_code_search(arg0.getCur_code_search());
				m_cur.setDatefrom(arg0.getDatefrom());
				m_cur.setDateto(arg0.getDateto());
				m_cur.setRow(arg0.getRow());
				m_cur.setStartIndex(arg0.getStartIndex());
				m_cur.setDuplicate(true);
				result.setResultObject(m_cur);
				result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_DUPLICATE);
			}
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
	 * <div>getTotalCount</div>
	 */
	private Integer getTotalCount(M_CURInput arg0){
		//count total items from searching result 
		String count = queryDAO.executeForObject(SELECT_DUPLICATE, arg0, String.class);
		//when count is null
		if (count == null || "".equals(count)) {
			//set count=0
			count = "0";
		}
		//return
		return Integer.parseInt(count);
	}

}
