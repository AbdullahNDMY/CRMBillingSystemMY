package nttdm.bsys.m_atm.blogic;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.Globals;
import org.apache.struts.util.LabelValueBean;

import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.m_atm.dto.M_ATMInput;
import nttdm.bsys.m_atm.dto.M_ATMOutput;
import nttdm.bsys.m_atm.bean.M_ATMBean; 
import nttdm.bsys.m_atm.bean.User_RoleBean;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;

public class M_ATMS01SearchBlogic extends AbstractM_ATMS01BLogic{
	private static final String SELECT_SQL_Search = "SELECT.M_ATM.SQL001";
	private static final String SELECT_SQL_Role = "SELECT.M_ATM.SQL002";
	private static final String SELECT_SQL_GetUsrName = "SELECT.M_ATM.SQL003";
	private static final String SELECT_SQL_GetTfrUsrName = "SELECT.M_ATM.SQL004";
	private static final String SELECT_SQL_GetTfrUsrName2 = "SELECT.M_ATM.SQL005";
	private QueryDAO queryDAO = null;
	/**
	 * <div>SAVE_ERROR_MSG</div>
	 */
	static final String SAVE_ERROR_MSG = "info.ERR2SC004";
	/**
	 * <div>setQueryDAO</div>
	 */
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}
	/**
	 * <div>getQueryDAO</div>
	 */
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}
	/**
	 * <div>Main function</div>
	 */

	public BLogicResult execute(M_ATMInput arg0) {
		// TODO Auto-generated method stub		
		BLogicMessages errors = new BLogicMessages();
		BLogicResult result = new BLogicResult();
		try{
			//declare out put data
			M_ATMOutput m_atm = new M_ATMOutput();
			List<M_ATMBean> array = new ArrayList<M_ATMBean>();
			//Get data from query
			array = queryDAO.executeForObjectList(SELECT_SQL_Search, arg0);
			String lst_transfer = "";
			int i=0;
			for (M_ATMBean atm : array) { 
				i++;
				//get user role from db
				List<User_RoleBean> user_role = new ArrayList<User_RoleBean>();
				user_role = queryDAO.executeForObjectList(SELECT_SQL_Role,atm.getId_tfr_user());
				atm.setBean_id(Integer.toString(i));
				//set role name
				for (User_RoleBean user_RoleBean : user_role) {
					atm.setRole_name(user_RoleBean.getRole_name());
					continue;
				} 
			}
			List<LabelValueBean> arrayName = new ArrayList<LabelValueBean>();
			arrayName = queryDAO.executeForObjectList(SELECT_SQL_GetTfrUsrName2, arg0);
			//set return values
			m_atm.setDetail_inf(Integer.toString(i));
			m_atm.setId_user(arg0.getId_user());
			m_atm.setUpdate_mode("false");
			m_atm.setArrayName(arrayName);
			m_atm.setM_atm(array); 
			//return object
			result.setResultObject(m_atm);
			//return message
			result.setResultString("success");		
			return result;  
		}catch(Exception ex){
			errors.add(Globals.MESSAGE_KEY,new BLogicMessage(SAVE_ERROR_MSG));
			result.setErrors(errors);
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
			return result;
		} 
	}
}
