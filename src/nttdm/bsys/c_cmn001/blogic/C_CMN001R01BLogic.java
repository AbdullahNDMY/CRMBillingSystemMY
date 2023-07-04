/**
 * Billing System
 * 
 * SUBSYSTEM NAME : Standard Price and Plan Maintenance
 * SERVICE NAME : M_PPM
 * FUNCTION : Form Bean
 * FILE NAME : PlanService.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 * History
 * 2011/09/29 [Duoc Nguyen] fix bug #2647 on portal (login attemp)
 * 2011/10/18 [Duoc Nguyen] fix bug #2790 on portal (login attemp)
 */
package nttdm.bsys.c_cmn001.blogic;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.util.PropertyUtil;
import nttdm.bsys.c_cmn001.bean.UserAccess;
import nttdm.bsys.c_cmn001.dto.C_CMN001R01Input;
import nttdm.bsys.c_cmn001.dto.C_CMN001R01Output;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.SecurityUtil;
import nttdm.bsys.util.GlobalRunningNumber;

import org.apache.struts.Globals;

/**
 * Business class to control login
 * @author Quan
 */
public class C_CMN001R01BLogic extends AbstractC_CMN001R01BLogic{

	private static final String SELECT_SQL_ID = "SELECT.C_CMN001.SQL001";
	
	private static final String SELECT_EXIST_SQL_ID = "SELECT.C_CMN001.SQL002";
	
	private static final String SELECT_USER_ACCESS_SQL_ID = "SELECT.C_CMN001.SQL003";
	
	private static final String UPDATE_SQL_ID = "UPDATE.C_CMN001.SQL001";
	
	private static final String LOGON_ERROR = "errors.ERR1SC001";
	
	private static final String DELETED_ERROR = "errors.ERR1SC001";
	
	private static final String INACTIVE_ERROR = "errors.ERR1SC024";
	
	private static final String USER_ACCOUNT = "screen.common.account";
	
	private String sessionDirectory = null;
	
	public void setSessionDirectory(String sessionDirectory) {
		this.sessionDirectory = sessionDirectory;
	}

	/**
	 * <div>Query Dao</div>
	 */
	private QueryDAO queryDAO = null;
	
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	public QueryDAO getQueryDAO() {
		return queryDAO;
	}
	
	/**
	 * <div>Update Dao</div>
	 */
	private UpdateDAO updateDAO = null;
	
	private BillingSystemUserValueObject getAuthentication(C_CMN001R01Input param) {
		
		BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
		param.setPassword(SecurityUtil.md5(param.getPassword()));
		uvo = queryDAO.executeForObject(SELECT_SQL_ID, param, 
				new BillingSystemUserValueObject().getClass());
		if(uvo != null){
			uvo.setSESSION_DIRECTORY(sessionDirectory);
			/**
			 * Audit Trail
			 */
			Integer idAudit = CommonUtils.auditTrailBegin(uvo.getId_user(), BillingSystemConstants.MODULE_A, 
					BillingSystemConstants.SUB_MODULE_A_USR, null, null, 
					BillingSystemConstants.AUDIT_TRAIL_LOGIN);
			CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
			GlobalRunningNumber run_no = new GlobalRunningNumber(queryDAO);
			uvo.setGlobal_running_number(run_no.getGlobalRunningNumber());
			List<UserAccess> user_access_list=queryDAO.executeForObjectList(SELECT_USER_ACCESS_SQL_ID, param);
			uvo.setUser_access(user_access_list);
		}
		return uvo;
	}
	
	/**
	 * Execute function
	 */

	public BLogicResult execute(C_CMN001R01Input param) {
		
		// BLogic result
		BLogicResult result = new BLogicResult();
		// Global setting value
		BillingSystemSettings bsysSetting = new BillingSystemSettings(queryDAO);
		// User value object
		BillingSystemUserValueObject uvo = getAuthentication(param);
		//Date today = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");	
		if(uvo != null){	
			// When user and password is deleted
			if (uvo.getIs_deleted().equalsIgnoreCase("1")) {
				BLogicMessages errors = new BLogicMessages();
				errors.add(Globals.ERROR_KEY, new BLogicMessage(DELETED_ERROR,PropertyUtil.getProperty(USER_ACCOUNT)));
		      	result.setErrors(errors);
		      	result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
		    	return result;
		 	}
		 	
			// When user and password is inactive
		 	if (uvo.getUser_status().equalsIgnoreCase("0")) {
		 		BLogicMessages errors = new BLogicMessages();
		 		errors.add(Globals.ERROR_KEY, new BLogicMessage(INACTIVE_ERROR,PropertyUtil.getProperty(USER_ACCOUNT)));
		     	result.setErrors(errors);
		            
		     	result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
		     	return result;
		 	}
	        	
		 	C_CMN001R01Output resultObj = new C_CMN001R01Output();
		 	// Check user need to change password
		 	uvo.setIs_need_change_password("0");
		 	try {
		 		Date lastPwdChangeDate = sdf.parse(uvo.getLast_pwd_change());
		 		final long ONE_HOUR = 60*60*1000L;
		 		long deltaDate = (Calendar.getInstance().getTimeInMillis() - lastPwdChangeDate.getTime())/(ONE_HOUR*24);
		 		long pwdDay = bsysSetting.getChangePwdDay();
		 		if (deltaDate > pwdDay) {
		 			//uvo.setId_user(param.getId_user());
		 			//uvo.setUser_name(param.getId_user());
		 			uvo.setUser_status("-1");
				 	resultObj.setUvo(uvo);
				 	result.setResultObject(resultObj);
			     
				 	result.setResultString("/A_PWD");
				 	return result;
		 		}
		 		
			} catch (Exception e) {
				System.out.print(e.toString());
			}
		 
		 	resultObj.setUvo(uvo);
		 	result.setResultObject(resultObj);
		 	result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
		 	return result;
		}else{
			if (uvo == null) {
				// When user and password is not valid
				BLogicMessages errors = new BLogicMessages();
				BLogicMessages messages = new BLogicMessages();
				
				C_CMN001R01Output resultObj = new C_CMN001R01Output();
				
				// Check login attempt in x minutes
				int maxLoginAttempt = bsysSetting.getNumberOfLogin();
				int maxLoginTime = bsysSetting.getMinuteToLogin();
				
				final long ONE_MINUTE = 60*1000L;
				try {
					Map<String, Object> mapUser = queryDAO.executeForMap(SELECT_EXIST_SQL_ID, param);
					//login with non-exist user
					if(mapUser == null) {
						resultObj.setLogin_attempt(0);
					}
					else {
						sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
						long deltaMinute = (Calendar.getInstance().getTimeInMillis() - sdf.parse(param.getLogin_time()).getTime())/ONE_MINUTE;
						if (deltaMinute <= maxLoginTime){
							if (param.getLogin_attempt() + 1 >= maxLoginAttempt){
								// Update user status 
								setUserStatusInactive(param);
								// Return error message
								errors.add(Globals.ERROR_KEY, new BLogicMessage(INACTIVE_ERROR,PropertyUtil.getProperty(USER_ACCOUNT)));
						     	result.setErrors(errors);
						     	result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
						     	return result;
							} else {
								resultObj.setLogin_attempt(param.getLogin_attempt() + 1);
							}
						} else {					
							resultObj.setLogin_attempt(1);
							resultObj.setLogin_time(CommonUtils.parseToString(Calendar.getInstance().getTime(), "dd/MM/yyyy HH:mm:ss"));
						}
					}
				}catch (Exception ex) {
					ex.printStackTrace();
				}
				messages.add(Globals.MESSAGE_KEY, new BLogicMessage(LOGON_ERROR));
				result.setMessages(messages);
				result.setResultObject(resultObj);
				result.setErrors(errors);
				result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
		        return result;
			}
		}
		return result;
}
	
	/**
	 * Update status of user
	 * @param param
	 * @return true:update successfully, otherwise:false
	 */
	private boolean setUserStatusInactive(C_CMN001R01Input param){
		Map<String, Object> mapUser = 
			queryDAO.executeForMap(SELECT_EXIST_SQL_ID, param);
		if (mapUser != null){
			updateDAO.execute(UPDATE_SQL_ID, param);
			return true;
		}
		return false;
	}

	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}
}
