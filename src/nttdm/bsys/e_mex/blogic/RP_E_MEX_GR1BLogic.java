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
import nttdm.bsys.e_mex.dto.RP_E_MEX_GR1Input;
import nttdm.bsys.e_mex.dto.RP_E_MEX_GR1Output;
import jp.terasoluna.fw.service.thin.BLogicResult;

/**
 * @author thinhtv
 */
public class RP_E_MEX_GR1BLogic extends AbstractRP_E_MEX_GR1BLogic {
	public static final String ID_MODULE = "M";
	public static final String ID_SUB_MODULE = "E-MEX";
	public static final String ID_BATCH_TYPE  = "G_GIR_P01";
	
	public BLogicResult execute(RP_E_MEX_GR1Input param) {
		BLogicResult result = new BLogicResult();
		// input
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
//				outputDTO.setGiroDay(CommonUtils.toString(setting.get("GIRO_VALUE_DAY")));
//				outputDTO.setGiroMonth(CommonUtils.toString(setting.get("GIRO_VALUE_MONTH")));
//				outputDTO.setGiroYear(CommonUtils.toString(setting.get("GIRO_VALUE_YEAR")));
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
//		Integer countHistory = queryDAO.executeForObject("E_MEX.GR1_countHistory", parameter, Integer.class);
//		// pagination
//		BillingSystemSettings bss = new BillingSystemSettings(queryDAO);
//		int row = bss.getResultRow();
//		Integer startIndex = param.getStartIndex();
//		if (startIndex == null || startIndex < 0 || startIndex > countHistory)
//			startIndex = 0;
//		List<Map<String, Object>> historyList = queryDAO.executeForMapList("E_MEX.GR1_getHistory", parameter, startIndex, row);
//		
//		outputDTO.setRow(row);
//		outputDTO.setTotalRow(countHistory);
//		outputDTO.setStartIndex(startIndex);
//		outputDTO.setHistoryList(historyList);
//		result.setResultObject(outputDTO);
		result.setResultString("success");
		return result;
		
		/*
		BLogicResult result = new BLogicResult();
		RP_E_MEX_GR1Output outputDTO = new RP_E_MEX_GR1Output();
		// validation
		BLogicMessages errors = new BLogicMessages();
		BLogicMessages messages = new BLogicMessages();
		
		// input
		BillingSystemUserValueObject uvo = param.getUvo();
		String scheduleStatus = param.getScheduleStatus();
		String scheduleDay = param.getScheduleDay();
		String scheduleHour = param.getScheduleHour();
		String scheduleMinute = param.getScheduleMinute();
		String giroDay = param.getGiroDay();
		String giroMonth = param.getGiroMonth();
		String giroYear = param.getGiroYear();
		
		String forward_update = param.getForward_update();
		String forward_execute = param.getForward_execute();
		String forward_pageLinks = param.getForward_pageLinks();
		
		if (CommonUtils.anyNotEmpty(forward_update, forward_execute)) {
			errors = this.validate(param, outputDTO);
		}
		
		BillingSystemSettings bss = new BillingSystemSettings(queryDAO);
		int row = bss.getResultRow();
		Integer startIndex = param.getStartIndex();
		
		// output
		BeanUtils.copyProperties(param, outputDTO);
		if (CommonUtils.isEmpty(scheduleStatus)) {
			scheduleStatus = "0";
			outputDTO.setScheduleStatus(scheduleStatus);
		}
		// business
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("idLogin", uvo.getId_user());
		parameter.put("idModule", ID_MODULE);
		parameter.put("idSubModule", ID_SUB_MODULE);
		parameter.put("idBatchType", ID_BATCH_TYPE);
		
		Map<String, Object> setting = queryDAO.executeForMap("E_MEX.getSchedulerSetting", parameter);
		if (CommonUtils.isEmpty(forward_update, forward_execute)) {
			// Init or PageLinks
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, -1);
			// init closing month
			outputDTO.setClosingMonth(CommonUtils.toString(cal.get(Calendar.MONTH) + 1));
			outputDTO.setClosingYear(CommonUtils.toString(cal.get(Calendar.YEAR)));
			// display setting on screen
			if (setting != null) {
				outputDTO.setScheduleStatus(CommonUtils.toString(setting.get("IS_ACTIVE")));
				outputDTO.setScheduleDay(CommonUtils.toString(setting.get("EXEC_DAY")));
				outputDTO.setScheduleHour(CommonUtils.toString(setting.get("EXEC_HOUR")));
				outputDTO.setScheduleMinute(CommonUtils.toString(setting.get("EXEC_MINUTE")));
			}
		} else if (CommonUtils.notEmpty(forward_update)) {
			// Update
			parameter.put("isActive", scheduleStatus);
			parameter.put("execDay", CommonUtils.toInteger(scheduleDay));
			parameter.put("execHour", CommonUtils.toInteger(scheduleHour));
			parameter.put("execMinute", CommonUtils.toInteger(scheduleMinute));
			parameter.put("giroDay", CommonUtils.toInteger(giroDay));
			parameter.put("giroMonth", CommonUtils.toInteger(giroMonth));
			parameter.put("giroYear", CommonUtils.toInteger(giroYear));
			// update setting
			if (setting == null) {
				updateDAO.execute("E_MEX.addSchedulerSetting", parameter);
			} else {
				updateDAO.execute("E_MEX.updateSchedulerSetting", parameter);
			}
		} else if (CommonUtils.notEmpty(forward_execute)) {
			// Execute
			ApplicationContext context = ApplicationContextProvider.getApplicationContext();
			G_GIR_P01 girP01 = (G_GIR_P01)context.getBean("G_GIR_P01");
			girP01.execute(param, outputDTO)
						.appendErrors(errors)
						.appendMessages(messages);
		}
		
		// get history
		Integer countHistory = queryDAO.executeForObject("E_MEX.GR1_countHistory", parameter, Integer.class);
		if (startIndex == null || startIndex < 0 || startIndex > countHistory)
			startIndex = 0;
		
		List<Map<String, Object>> historyList = queryDAO.executeForMapList("E_MEX.GR1_getHistory", parameter, startIndex, row);
		
		outputDTO.setRow(row);
		outputDTO.setStartIndex(startIndex);
		outputDTO.setTotalRow(countHistory);
		outputDTO.setHistoryList(historyList);
		result.setErrors(errors);
		result.setMessages(messages);
		result.setResultObject(outputDTO);
		result.setResultString("success");
		return result;
		*/
	}
	
}