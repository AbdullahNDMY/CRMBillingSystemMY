package nttdm.bsys.common.util;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import nttdm.bsys.e_mim.dto.RP_E_MIM_US3_04Input;
import nttdm.bsys.e_mim.dto.RP_E_MIM_US3_04Output;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessages;

public class G_RAD_P01 extends AbstractGlobalProcess<RP_E_MIM_US3_04Input, RP_E_MIM_US3_04Output> {
	
	private static final String STATUS_SUCCESS = "0";
	private static final String STATUS_FAILED = "1";
	private static final String STATUS_INPROCESS = "2";
	private static final String INVOICED = "1";
	private static final String NOT_INVOICED = "0";
	private BLogicMessages errors = new BLogicMessages();
	private BLogicMessages messages = new BLogicMessages();
	private GlobalProcessResult result = null;
	private Map<String, Object> parameter;
	
	public GlobalProcessResult execute(RP_E_MIM_US3_04Input param, RP_E_MIM_US3_04Output outputDTO) {
		this.errors.clear();
		this.messages.clear();
		this.result = new GlobalProcessResult();
		this.parameter = new HashMap<String, Object>();
		parameter.put("idLogin", param.getUvo().getId_user());
		parameter.put("idBatchType", "G_RAD_P01");
		parameter.put("createdDate", new Date(Calendar.getInstance().getTimeInMillis()));
		parameter.put("updatedDate", new Date(Calendar.getInstance().getTimeInMillis()));
		parameter.put("uploadedDate", new Date(Calendar.getInstance().getTimeInMillis()));
		
		int closingMonth = Integer.parseInt(param.getMonth());
		int closingYear = Integer.parseInt(param.getYear());		
		Calendar now = Calendar.getInstance();
		now.set(Calendar.MONTH, closingMonth - 1);
		now.set(Calendar.YEAR, closingYear);
		
		String yyyy = CommonUtils.formatDate(now.getTime(), "yyyy");
		String MM = CommonUtils.formatDate(now.getTime(), "MM");
		
		// 1.0 Check latest process
		List<HashMap<String, Object>> listT_RAD_IMP_HDDESC = this.queryDAO.executeForObjectList("E_MIM.getLatestT_RAD_IMP_HD", null);
		HashMap<String, Object> latestTRadImpHdDesc = null;
		if(listT_RAD_IMP_HDDESC.size() > 0){
			latestTRadImpHdDesc = listT_RAD_IMP_HDDESC.get(0);
			parameter.put("idRadImpBatch", latestTRadImpHdDesc.get("ID_RAD_IMP_BATCH"));
			parameter.put("idBatchRefNo", latestTRadImpHdDesc.get("ID_RAD_IMP_BATCH"));
		}
		BillingSystemSettings setting = new BillingSystemSettings(queryDAO);
		// is in process
		boolean isContinue = true;
		// true: in process
		if(latestTRadImpHdDesc != null && latestTRadImpHdDesc.get("STATUS").equals(STATUS_INPROCESS)){
			// BATCH_TIME_INTERVAL
			if(CommonUtils.isTimeInterval(latestTRadImpHdDesc.get("DATE_UPDATED_CHAR").toString(), setting.getBatchTimeInterval() * 1000)){
				// 3.0 update T_CLC_IMP_HD with status=Failed (1)
				parameter.put("status", STATUS_FAILED);
				this.updateDAO.execute("E_MIM.updateStatusT_RAD_IMP_HD", parameter);
				// Call G_ADT to insert audit
				// TODO not implement
				// 4.0 record error for the previous process
				this.recordLog("errors.ERR1BT016");
			}else{
				// 2.1 prompt error message
				this.addError("errors.ERR1SC059");
				isContinue = false;
			}
		}else{//False: not in process
			isContinue = true;
		}
		if(isContinue){
			//Get the id for next insert
			Integer newIdTRadImpHd = this.queryDAO.executeForObject("E_MIM.getSEQ_T_RAD_IMP_HD", null, Integer.class);
			parameter.put("idRadImpBatch", newIdTRadImpHd);
			parameter.put("idBatchRefNo", newIdTRadImpHd);
			// 5.0 Create batch header record: insert into T_RAD_IMP_HD
			parameter.put("closeMonthYear", MM+"/"+yyyy);
			parameter.put("status", STATUS_INPROCESS);			
			this.updateDAO.execute("E_MIM.insertT_RAD_IMP_HD", parameter);
			// 6.0  check folder accessible
			boolean isAccessible = true;
			/**
			 * Radius table is third party db-table
			 * so, NOT be check, alway set to TRUE
			 */
			if(isAccessible){
				// 8.0 search session data for the selected month from AccountingLog
				parameter.put("closeMonthYear", MM+"/"+yyyy);
				List<Map<String, Object>> listAccountLog = this.queryDAO.executeForObjectList("E_MIM.getAccountLog", parameter);
				if(listAccountLog.size() > 0) {
					// 9.0 is month_year exist in T_RAD_IMP_HD and status = success (getT_RAD_IMP_HDByTime)
					parameter.put("status", STATUS_SUCCESS);
					List<HashMap<String, Object>> listT_RAD_IMP_HDByTime = this.queryDAO.executeForObjectList("E_MIM.getT_RAD_IMP_HDByTime", parameter);
					if(listT_RAD_IMP_HDByTime.size() > 0) {
						// 10.0 check any records of the month has invoiced or not
						parameter.put("isInvoiced", INVOICED);
						parameter.put("monthYear", MM +"/" + yyyy);
						List<HashMap<String, Object>> listInvoicedT_RAD_IMP_MONTLY_SUM = this.queryDAO.executeForObjectList("E_MIM.getT_RAD_IMP_MONTLY_SUMByTime", parameter);
						if(listInvoicedT_RAD_IMP_MONTLY_SUM.size() > 0){
							isContinue = false;
							// 7.0 Prompt error message to user
							// 7.1 Update T_RAD_IMP_HD and STATUS=Failed.
							parameter.put("status", STATUS_FAILED);
							this.updateDAO.execute("E_MIM.updateStatusT_RAD_IMP_HD", parameter);
							// 7.2 Record error log.
							this.addError("errors.ERR1SC050", "0");
							this.recordLog("errors.ERR1BT010", "0");
						} else {
							// 11.0 Remove all monthly usage
							updateDAO.execute("E_MIM.deleteT_RAD_IMP_MONTHLY_SUM", parameter);
						}
					}
					if(isContinue) {
						// 12.0 Query the database by grouping UserID and NASIndex
						parameter.put("monthYear", MM +"/" + yyyy);
						List<Map<String, Object>> listSumAccSTime = this.queryDAO.executeForObjectList("E_MIM.getAccountLogWithTotal", parameter);
						// 13.0 Retrieve the query result.
						// 14.0 Convert session into minutes format and insert usage summary data
						parameter.put("monthYear", MM +"/" + yyyy);
						for(Map<String, Object> sumAccSTime : listSumAccSTime) {
							parameter.put("accessAcct", sumAccSTime.get("USERID"));
							parameter.put("serviceId", sumAccSTime.get("NASINDEX"));
							parameter.put("sessionTotal", sumAccSTime.get("SESSION_TOTAL"));
							parameter.put("isInvoiced", NOT_INVOICED);
							this.updateDAO.execute("E_MIM.insertT_RAD_IMP_MONTHLY_SUM", parameter);
							
							// Call G_ADT to insert audit
							// TODO not implement
						}
						// 15.0 Update Radius Service Group.
						// 15.1 Retrieve Service Group ID from global setting. 
						List<Map<String, Object>> srvIDs = this.queryDAO.executeForObjectList("E_MIM.getM_GSET_DByID_SETAndCond", parameter);
						// 15.2 Update Radius Service Group in table T_RAD_IMP_MONTHLY_SUM.
						for(Map<String, Object> srvID : srvIDs) {
							parameter.put("svcGrp", srvID.get("SET_SEQ"));
							parameter.put("svcId", srvID.get("SET_VALUE"));
							this.updateDAO.execute("E_MIM.updateT_RAD_IMP_MONTHLY_SUM", parameter);
						}
						// 16.0 Prompt success Message
						this.addMsg("info.ERR2SC028");
						// 17.0 Update batch header table T_RAD_IMP_HD.STATUS=0
						parameter.put("status", STATUS_SUCCESS);
						this.updateDAO.execute("E_MIM.updateStatusT_RAD_IMP_HD", parameter);
					}
				}else{
					isContinue = false;
					// 7.0 Prompt error message to user
					// 7.1 Update T_RAD_IMP_HD and STATUS=Failed.
					parameter.put("status", STATUS_FAILED);
					this.updateDAO.execute("E_MIM.updateStatusT_RAD_IMP_HD", parameter);
					// 7.2 Record error log.
					this.addError("errors.ERR1SC052");
					this.recordLog("errors.ERR1BT011", "Accounting Log");
				}
			}else{
				isContinue = false;
				// 7.0 Prompt error message to user
				// 7.1 Update T_RAD_IMP_HD and STATUS=Failed.
				parameter.put("status", STATUS_FAILED);
				this.updateDAO.execute("E_MIM.updateStatusT_RAD_IMP_HD", parameter);
				// 7.2 Record error log.
				this.addError("errors.ERR1SC060", "Radius table");
				this.recordLog("errors.ERR1BT017", "Radius table");
			}
		}
		result.setErrors(errors);
		result.setMessages(messages);
		return result;
	}
	
	public void reset(){
		this.errors = new BLogicMessages();
		this.messages = new BLogicMessages();
	}
	
	private void addError(String msgID, Object... args) {
		this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(msgID, args));
	}
	
	private void addMsg(String msgID) {
		this.messages.add(ActionMessages.GLOBAL_MESSAGE, new BLogicMessage(msgID));
	}
	
	private void recordLog(String msgID, Object... args) {
		Integer idBatchLog = queryDAO.executeForObject("E_MIM.getSEQ_T_BATCH_LOG", null, Integer.class);
		parameter.put("idBatchLog", idBatchLog);
		parameter.put("errorMsg", MessageUtil.get(msgID, args));		
		this.updateDAO.execute("E_MIM.insertT_BATCH_LOG", parameter);
	}
}