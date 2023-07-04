package nttdm.bsys.m_atm.blogic;

import org.apache.struts.Globals;

import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_alt.blogic.M_ALTR02BLogic;
import nttdm.bsys.m_atm.bean.M_ATMBean;
import nttdm.bsys.m_atm.dto.M_ATMInput; 
import nttdm.bsys.m_wlm.dto.M_WLMR02Input;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;

public class M_ATMS01SaveBLogic  extends AbstractM_ATMS01BLogic{
	/**
	 * <div>UpdateDAO</div>
	 */
	private UpdateDAO updateDAO = null;
	/**
	 * <div>SAVE_ERROR_MSG</div>
	 */
	static final String SAVE_ERROR_MSG = "info.ERR2SC004";
	/**
	 * <div>SAVE_SUCCESSFUL_MSG</div>
	 */
	static final String SAVE_SUCCESSFUL_MSG = "info.ERR2SC003";
	/**
	 * <div>UPDATE_message1: For using "Transfer All To:"</div>
	 */
	private static final String UPDATE_message1 = "UPDATE.M_ATM.SQL001";
	/**
	 * <div>UPDATE_message2: For using "Detail Information"</div>
	 */
	private static final String UPDATE_message2 = "UPDATE.M_ATM.SQL002";
	
	private static final String PATTERN_ID_NAME = "%s:%s";
	
	/**
	 * <div>setUpdateDAO</div>
	 */
	public void setUpdateDAO(UpdateDAO updateDAO) {
        this.updateDAO = updateDAO;
    }
	/**
	 * <div>Main function</div>
	 */
	public BLogicResult execute(M_ATMInput params) {		
		//check update mode 
		BLogicMessages errors = new BLogicMessages();
		BLogicMessages messages = new BLogicMessages();
		BLogicResult result = new BLogicResult();
		try{
			String mode = params.getUpdate_mode();
			
			// Audit Trail
			Integer auditID = -1;
			
			if(mode.equalsIgnoreCase("true"))
			{
				// Audit Trail
				auditID = this.writeAuditHeader(params, BillingSystemConstants.AUDIT_TRAIL_CREATED);
				params.setAuditID(auditID);
				
				//update transfer
				params.setId_login(params.getUvo().getId_user());
				updateDAO.execute(UPDATE_message1, params);
				CommonUtils.auditTrailEnd(auditID, true);//End Audit Trail
			}
			else
			{
				//Execute update		
				String detail_inf = params.getDetail_inf();
				if(detail_inf.trim().length()>0)
				{
					// Audit Trail
					if( auditID == -1 ){
						auditID = this.writeAuditHeader(params, BillingSystemConstants.AUDIT_TRAIL_EDITED);
					}
					params.setAuditID(auditID);
					
					update_detail(params);
					CommonUtils.auditTrailEnd(auditID, true);//End Audit Trail
				}
			}
			//return result to system
			messages.add(Globals.MESSAGE_KEY,new BLogicMessage(SAVE_SUCCESSFUL_MSG));
			result.setMessages(messages);
			result.setResultString("success");
	        return result;
		}catch(Exception ex){
			errors.add(Globals.MESSAGE_KEY,new BLogicMessage(SAVE_ERROR_MSG));
			result.setErrors(errors);
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
			return result;
		}
	}	
	private void update_detail(M_ATMInput params)
	{
		String detail_inf = params.getDetail_inf();
		String[] arr = detail_inf.split("\r\n\r\n");
		for (String tmp : arr) {
			String[] row = tmp.split("\r\n");
			if(row.length>2)
			{
				M_ATMBean m_atm = new M_ATMBean();
				m_atm.setId_user(params.getId_user());
				m_atm.setSection_no(row[1].trim());
				m_atm.setId_screen(row[0].trim());
				m_atm.setId_tfr_user(row[2].trim());
				m_atm.setEff_date_from(row[3].trim());
				m_atm.setEff_date_to(row[4].trim());
				m_atm.setId_login(params.getUvo().getId_user()); 
				m_atm.setAuditID(params.getAuditID());
				updateDAO.execute(UPDATE_message2, m_atm);
			}
		}
	}
	
	private String getIdScreen(M_ATMInput params) {
		String detail_inf = params.getDetail_inf();
		String[] arr = detail_inf.split("\r\n\r\n");
		String idScreen = "";
		for (String tmp : arr) {
			String[] row = tmp.split("\r\n");
			if (row.length > 2) {
				idScreen += ("," + row[0].trim());
			}
		}
		if(idScreen.equals(""))
			return idScreen;
		return idScreen.substring(1);
	}
	
	/**
	 * Write Audit Trail Header
	 * @param param
	 * @return AuditID
	 */
	private Integer writeAuditHeader(M_ATMInput param, String action){
		
		
		Integer retAuditID = CommonUtils.auditTrailBegin(param.getUvo().getId_user()
														 , BillingSystemConstants.MODULE_M
														 , BillingSystemConstants.SUB_MODULE_M_ATM
														 , String.format(PATTERN_ID_NAME, this.getIdScreen(param), param.getUser_name())
														 , null
														 , action);
		return retAuditID;
	}
}
