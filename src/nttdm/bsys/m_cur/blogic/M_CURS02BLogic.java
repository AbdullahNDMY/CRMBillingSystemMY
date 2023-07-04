package nttdm.bsys.m_cur.blogic;

import org.apache.struts.Globals;

import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.m_cur.dto.M_CURInput;
import nttdm.bsys.m_cur.dto.M_CUROutput;
import nttdm.bsys.m_cur.bean.M_CURBean; 
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;

public class M_CURS02BLogic extends AbstractM_CURBLogic{
	/**
	 * <div>queryDAO</div>
	 */
	private QueryDAO queryDAO = null;
	/**
	 * <div>SAVE_ERROR_MSG</div>
	 */
	private static final String SELECT_CurrencySQL = "SELECT.M_CUR.SQL002";
	/**
	 * <div>SAVE_ERROR_MSG</div>
	 */
	static final String SAVE_ERROR_MSG = "info.ERR2SC004";
	
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
		// TODO Auto-generated method stub
		BLogicMessages errors = new BLogicMessages();
		BLogicResult result = new BLogicResult();		
		try{
			//get start index for paging
			//get the number of row for paging 
			//declare blogic result
			M_CURBean m_cur = new M_CURBean();
			m_cur = queryDAO.executeForObject(SELECT_CurrencySQL, arg0, M_CURBean.class); 
			M_CUROutput out = new M_CUROutput();
			//save search info
			out.setDatefrom(arg0.getDatefrom());
			out.setDateto(arg0.getDateto());
			out.setCur_code_search(arg0.getCur_code_search());
			out.setStartIndex(arg0.getStartIndex());
			out.setRow(arg0.getRow());
			out.setUnsearch(arg0.getUnsearch());
			//save currency info
			out.setRate_date(m_cur.getRate_date());
			out.setCur_code(m_cur.getCur_code());
			out.setRate_from(m_cur.getRate_from());
			out.setRate_to(m_cur.getRate_to());
			//return object
			result.setResultObject(out);
			//return message
			//result.setResultString("success");
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
