/*
 * @(#)E_MEX_SP1BLogic.java
 *
 * Copyright 2010 NTT DATA Corporation.
 *
 */
package nttdm.bsys.e_mex.blogic;

import java.util.Calendar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.e_mex.dto.RP_E_MEX_CT1Input;
import nttdm.bsys.e_mex.dto.RP_E_MEX_GR1Output;
import jp.terasoluna.fw.service.thin.BLogicResult;

public class RP_E_MEX_CT1BLogic extends AbstractRP_E_MEX_CT1BLogic {
	public static final String ID_MODULE = "M";
	public static final String ID_SUB_MODULE = "E-MEX";
	public static final String ID_BATCH_TYPE = "G_CIT_P01";
	
	public BLogicResult execute(RP_E_MEX_CT1Input param) {
		BLogicResult result = new BLogicResult();
//	// input
//		BillingSystemUserValueObject uvo = param.getUvo();
//		String event = param.getEvent();
//		String forward_update = param.getForward_update();
//		String forward_execute = param.getForward_execute();
//		String forward_pageLinks = param.getForward_pageLinks();
//		String runtimeType = param.getRuntimeType();
//		String scheduleStatus = param.getScheduleStatus();
//		// output
//		RP_E_MEX_GR1Output outputDTO = new RP_E_MEX_GR1Output();
//		BeanUtils.copyProperties(param, outputDTO);
//		
//		// business
//		Map<String, Object> parameter = new HashMap<String, Object>();
//		parameter.put("idLogin", uvo.getId_user());
//		parameter.put("idModule", ID_MODULE);
//		parameter.put("idSubModule", ID_SUB_MODULE);
//		parameter.put("idBatchType", ID_BATCH_TYPE);
//		
//		Map<String, Object> setting = queryDAO.executeForMap("E_MEX.getSchedulerSetting", parameter);
//		if (CommonUtils.isEmpty(forward_update, forward_execute)) {
//			// Init or PageLinks
//			outputDTO.setScheduleStatus("0");
//			// init closing month
//			Calendar cal = Calendar.getInstance();
//			cal.add(Calendar.MONTH, -1);
//			outputDTO.setClosingMonth(CommonUtils.toString(cal.get(Calendar.MONTH) + 1));
//			outputDTO.setClosingYear(CommonUtils.toString(cal.get(Calendar.YEAR)));
//			// display setting on screen
//			if (setting != null) {
//				outputDTO.setScheduleStatus(CommonUtils.toString(setting.get("IS_ACTIVE")));
//				outputDTO.setScheduleDay(CommonUtils.toString(setting.get("EXEC_DAY")));
//				outputDTO.setScheduleHour(CommonUtils.toString(setting.get("EXEC_HOUR")));
//				outputDTO.setScheduleMinute(CommonUtils.toString(setting.get("EXEC_MINUTE")));
//			}
//		} else if ("1".equalsIgnoreCase(runtimeType)) { // scheduler
//			if ("0".equalsIgnoreCase(scheduleStatus)) { // activeparam.getScheduleStatus())
//				// display setting on screen
//				if (setting != null) {
//					outputDTO.setScheduleDay(CommonUtils.toString(setting.get("EXEC_DAY")));
//					outputDTO.setScheduleHour(CommonUtils.toString(setting.get("EXEC_HOUR")));
//					outputDTO.setScheduleMinute(CommonUtils.toString(setting.get("EXEC_MINUTE")));
//				}
//			}
//		}
//		
//		// get history
//		Integer countHistory = queryDAO.executeForObject("E_MEX.CT1_countHistory", parameter, Integer.class);
//		// pagination
//		BillingSystemSettings bss = new BillingSystemSettings(queryDAO);
//		int row = bss.getResultRow();
//		Integer startIndex = param.getStartIndex();
//		if (startIndex == null || startIndex < 0 || startIndex > countHistory)
//			startIndex = 0;
//		List<Map<String, Object>> historyList = queryDAO.executeForMapList("E_MEX.CT1_getHistory", parameter, startIndex, row);
//		
//		outputDTO.setRow(row);
//		outputDTO.setTotalRow(countHistory);
//		outputDTO.setStartIndex(startIndex);
//		outputDTO.setHistoryList(historyList);
//		result.setResultObject(outputDTO);
		result.setResultString("success");
		return result;
	}
}