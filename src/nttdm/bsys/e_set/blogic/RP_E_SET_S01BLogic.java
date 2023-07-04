/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : E_SET
 * SERVICE NAME   : E_SET_S01
 * FUNCTION       : Showing BLogic
 * FILE NAME      : RP_E_SET_S01BLogic.java
 * 
* Copyright © 2011 NTTDATA Corporation
*
 * History
 * 
 * 
**********************************************************/
package nttdm.bsys.e_set.blogic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.e_set.bean.E_SET_S03Bean;
import nttdm.bsys.e_set.dto.RP_E_SET_S01Input;

import org.apache.struts.util.LabelValueBean;

/**
 * RP_E_SET_S01BLogic<br>
 * <ul>
 * <li>A BLogic class to process displaying Batch maintenance Settings
 * </ul>
 * <br>
* @author  NTTData Vietnam
* @version 1.0
 */
public class RP_E_SET_S01BLogic extends AbstractRP_E_SET_S01BLogic {
	private static final String CC = "CC";
	private static final String SD = "SD";
	private static final String GB = "GB";
	private static final String SA = "SA";
//	private static final String SS = "SS";
	private static final String CB = "CB";
	private static final String AR = "AR";
    private static final String BA = "BA";
	private static final String FREQUENCY_MODE = "Monthly";
	private static final String SD_FREQUENCY_MODE = "Daily";
	private static final int USER_LIMIT = 5;

	public BLogicResult execute(RP_E_SET_S01Input input) {
		BLogicResult result = new BLogicResult();
		RP_E_SET_S01Input outputDTO = new RP_E_SET_S01Input();
		
		Map<String, Object> parameter = new HashMap<String, Object>();
		E_SET_S03Common e_set_s03Common = new E_SET_S03Common(queryDAO);
		//CC
		parameter.put("batchId", CC);
		Map<String, Object> batchCC = queryDAO.executeForMap("E_SET.getBatchMaintenance", parameter);
		Map<String, Object>[] userCCAlerts = queryDAO.executeForMapArray("E_SET.getBatchUserRoleDeptAlert", parameter);
		outputDTO.setCCUsers(this.getUserName(userCCAlerts));
		if(batchCC != null) {
		    E_SET_S03Bean ccE_SET_S03Bean = e_set_s03Common.checkInProcessStatus(CC);
		    outputDTO.setCCRetStatus(ccE_SET_S03Bean.getRetStatus());
		    
			if(!isEmpty(batchCC.get("ALERT_MODE")))
				outputDTO.setCCAlertMode(batchCC.get("ALERT_MODE").toString());
			if(!isEmpty(batchCC.get("FREQUENCY_MODE")))
				outputDTO.setCCMode(batchCC.get("FREQUENCY_MODE").toString());
			else
				outputDTO.setCCMode(FREQUENCY_MODE);
			if(!isEmpty(batchCC.get("NO_OF_MONTH")))
				outputDTO.setCCMonths(batchCC.get("NO_OF_MONTH").toString());
			if(!isEmpty(batchCC.get("DAY_OF_MONTH")))
				outputDTO.setCCDay(batchCC.get("DAY_OF_MONTH").toString());
			if(!isEmpty(batchCC.get("TIME_HOUR")))
				outputDTO.setCCHour(batchCC.get("TIME_HOUR").toString());
			if(!isEmpty(batchCC.get("TIME_MINUTE")))
				outputDTO.setCCMinute(batchCC.get("TIME_MINUTE").toString());
		}
		//SD
		parameter.put("batchId", SD);
		Map<String, Object> batchSD = queryDAO.executeForMap("E_SET.getBatchMaintenance", parameter);
		Map<String, Object>[] userSDAlerts = queryDAO.executeForMapArray("E_SET.getBatchUserRoleDeptAlert", parameter);
		outputDTO.setSDUsers(this.getUserName(userSDAlerts));
		if(batchSD != null) {
		    E_SET_S03Bean sdE_SET_S03Bean = e_set_s03Common.checkInProcessStatus(SD);
		    outputDTO.setSDRetStatus(sdE_SET_S03Bean.getRetStatus());
		    
			if(!isEmpty(batchSD.get("ALERT_MODE")))
				outputDTO.setSDAlertMode(batchSD.get("ALERT_MODE").toString());
			if(!isEmpty(batchSD.get("FREQUENCY_MODE")))
				outputDTO.setSDMode(batchSD.get("FREQUENCY_MODE").toString());
			else
				outputDTO.setSDMode(SD_FREQUENCY_MODE);
			if(!isEmpty(batchSD.get("NO_OF_MONTH")))
				outputDTO.setSDMonths(batchSD.get("NO_OF_MONTH").toString());
			if(!isEmpty(batchSD.get("DAY_OF_MONTH")))
				outputDTO.setSDDay(batchSD.get("DAY_OF_MONTH").toString());
			if(!isEmpty(batchSD.get("TIME_HOUR")))
				outputDTO.setSDHour(batchSD.get("TIME_HOUR").toString());
			if(!isEmpty(batchSD.get("TIME_MINUTE")))
				outputDTO.setSDMinute(batchSD.get("TIME_MINUTE").toString());
		}
		
		//GB
		parameter.put("batchId", GB);
		Map<String, Object> batchGB = queryDAO.executeForMap("E_SET.getBatchMaintenance", parameter);
		Map<String, Object>[] userGBAlerts = queryDAO.executeForMapArray("E_SET.getBatchUserRoleDeptAlert", parameter);
		outputDTO.setGBUsers(this.getUserName(userGBAlerts));
		if(batchGB != null) {
		    E_SET_S03Bean gbE_SET_S03Bean = e_set_s03Common.checkInProcessStatus(GB);
		    outputDTO.setGBRetStatus(gbE_SET_S03Bean.getRetStatus());
		    
			if(!isEmpty(batchGB.get("FREQUENCY_MODE")))
				outputDTO.setGBMode(batchGB.get("FREQUENCY_MODE").toString());
			else
				outputDTO.setGBMode(FREQUENCY_MODE);
			if(!isEmpty(batchGB.get("NO_OF_MONTH")))
				outputDTO.setGBMonths(batchGB.get("NO_OF_MONTH").toString());
			if(!isEmpty(batchGB.get("DAY_OF_MONTH")))
				outputDTO.setGBDay(batchGB.get("DAY_OF_MONTH").toString());
			if(!isEmpty(batchGB.get("TIME_HOUR")))
				outputDTO.setGBHour(batchGB.get("TIME_HOUR").toString());
			if(!isEmpty(batchGB.get("TIME_MINUTE")))
				outputDTO.setGBMinute(batchGB.get("TIME_MINUTE").toString());
		}
		
		//SA
		parameter.put("batchId", SA);
		Map<String, Object> batchSA = queryDAO.executeForMap("E_SET.getBatchMaintenance", parameter);
		Map<String, Object>[] userSAAlerts = queryDAO.executeForMapArray("E_SET.getBatchUserRoleDeptAlert", parameter);
		outputDTO.setSAUsers(this.getUserName(userSAAlerts));
		if(batchSA != null) {
		    E_SET_S03Bean saE_SET_S03Bean = e_set_s03Common.checkInProcessStatus(SA);
		    outputDTO.setSARetStatus(saE_SET_S03Bean.getRetStatus());
		    
			if(!isEmpty(batchSA.get("FREQUENCY_MODE")))
				outputDTO.setSAMode(batchSA.get("FREQUENCY_MODE").toString());
			else
				outputDTO.setSAMode(FREQUENCY_MODE);
			if(!isEmpty(batchSA.get("NO_OF_MONTH")))
				outputDTO.setSAMonths(batchSA.get("NO_OF_MONTH").toString());
			if(!isEmpty(batchSA.get("DAY_OF_MONTH")))
				outputDTO.setSADay(batchSA.get("DAY_OF_MONTH").toString());
			if(!isEmpty(batchSA.get("TIME_HOUR")))
				outputDTO.setSAHour(batchSA.get("TIME_HOUR").toString());
			if(!isEmpty(batchSA.get("TIME_MINUTE")))
				outputDTO.setSAMinute(batchSA.get("TIME_MINUTE").toString());
			String SAStatementMonth = CommonUtils.toString(input.getSAStatementMonth()).trim();
			if (CommonUtils.isEmpty(SAStatementMonth)) {
			    if (!isEmpty(batchSA.get("STATEMENT_MONTH"))) {
	                outputDTO.setSAStatementMonth(batchSA.get("STATEMENT_MONTH").toString());
	            } else {
	                outputDTO.setSAStatementMonth("0");
	            }
			} else {
			    outputDTO.setSAStatementMonth(SAStatementMonth);
			}
		}
		outputDTO.setSARunDateEntry(input.getSARunDateEntry());
        outputDTO.setSApopupClickYesFlg(input.getSApopupClickYesFlg());
        outputDTO.setSAPopupInfo(input.getSAPopupInfo());
        
//		//SS
//		parameter.put("batchId", SS);
//		Map<String, Object> batchSS = queryDAO.executeForMap("E_SET.getBatchMaintenance", parameter);
//		Map<String, Object>[] userSSAlerts = queryDAO.executeForMapArray("E_SET.getBatchUserRoleDeptAlert", parameter);
//		outputDTO.setSSUsers(this.getUserName(userSSAlerts));
//		if(batchSS != null) {
//			if(!isEmpty(batchSS.get("FREQUENCY_MODE")))
//				outputDTO.setSSMode(batchSS.get("FREQUENCY_MODE").toString());
//			else
//				outputDTO.setSSMode(FREQUENCY_MODE);
//			if(!isEmpty(batchSS.get("NO_OF_MONTH")))
//				outputDTO.setSSMonths(batchSS.get("NO_OF_MONTH").toString());
//			if(!isEmpty(batchSS.get("DAY_OF_MONTH")))
//				outputDTO.setSSDay(batchSS.get("DAY_OF_MONTH").toString());
//			if(!isEmpty(batchSS.get("TIME_HOUR")))
//				outputDTO.setSSHour(batchSS.get("TIME_HOUR").toString());
//			if(!isEmpty(batchSS.get("TIME_MINUTE")))
//				outputDTO.setSSMinute(batchSS.get("TIME_MINUTE").toString());
//		}
		//CB
		parameter.put("batchId", CB);
		Map<String, Object> batchCB = queryDAO.executeForMap("E_SET.getBatchMaintenance", parameter);
		Map<String, Object>[] userCBAlerts = queryDAO.executeForMapArray("E_SET.getBatchUserRoleDeptAlert", parameter);
		outputDTO.setCBUsers(this.getUserName(userCBAlerts));
		// Default Mode = "Consumer"
		outputDTO.setCBCustomerType("CONS");
		if(batchCB != null) {
		    E_SET_S03Bean cbE_SET_S03Bean = e_set_s03Common.checkInProcessStatus(CB);
		    outputDTO.setCBRetStatus(cbE_SET_S03Bean.getRetStatus());
		    
		    if ("0".equalsIgnoreCase(CommonUtils.toString(batchCB.get("ALERT_MODE")))) {
		        outputDTO.setCBCustomerType("CONS");
		    } else if ("1".equalsIgnoreCase(CommonUtils.toString(batchCB.get("ALERT_MODE")))) {
		        outputDTO.setCBCustomerType("CORP");
		    } else {
		        outputDTO.setCBCustomerType("");
		    }
		    
			if(!isEmpty(batchCB.get("FREQUENCY_MODE")))
				outputDTO.setCBMode(batchCB.get("FREQUENCY_MODE").toString());
			else
				outputDTO.setCBMode(FREQUENCY_MODE);
			if(!isEmpty(batchCB.get("NO_OF_MONTH")))
				outputDTO.setCBMonths(batchCB.get("NO_OF_MONTH").toString());
			if(!isEmpty(batchCB.get("DAY_OF_MONTH")))
				outputDTO.setCBDay(batchCB.get("DAY_OF_MONTH").toString());
			if(!isEmpty(batchCB.get("TIME_HOUR")))
				outputDTO.setCBHour(batchCB.get("TIME_HOUR").toString());
			if(!isEmpty(batchCB.get("TIME_MINUTE")))
				outputDTO.setCBMinute(batchCB.get("TIME_MINUTE").toString());
		}
		//AR
		parameter.put("batchId", AR);
		Map<String, Object> batchAR = queryDAO.executeForMap("E_SET.getBatchMaintenance", parameter);
		Map<String, Object>[] userARAlerts = queryDAO.executeForMapArray("E_SET.getBatchUserRoleDeptAlert", parameter);
		outputDTO.setARUsers(this.getUserName(userARAlerts));
		if(batchAR != null) {
		    E_SET_S03Bean arE_SET_S03Bean = e_set_s03Common.checkInProcessStatus(AR);
		    outputDTO.setARRetStatus(arE_SET_S03Bean.getRetStatus());
		    
			if(!isEmpty(batchAR.get("FREQUENCY_MODE")))
				outputDTO.setARMode(batchAR.get("FREQUENCY_MODE").toString());
			else
				outputDTO.setARMode(FREQUENCY_MODE);
			if(!isEmpty(batchAR.get("NO_OF_MONTH")))
				outputDTO.setARMonths(batchAR.get("NO_OF_MONTH").toString());
			if(!isEmpty(batchAR.get("DAY_OF_MONTH")))
				outputDTO.setARDay(batchAR.get("DAY_OF_MONTH").toString());
			if(!isEmpty(batchAR.get("TIME_HOUR")))
				outputDTO.setARHour(batchAR.get("TIME_HOUR").toString());
			if(!isEmpty(batchAR.get("TIME_MINUTE")))
				outputDTO.setARMinute(batchAR.get("TIME_MINUTE").toString());
		}
		
		// BA
		String baCalc = queryDAO.executeForObject("E_SET.getBAC_CALC", null, String.class);
		outputDTO.setBACalc(baCalc);
		parameter.put("batchId", BA);
		Map<String, Object>[] userBAAlerts = queryDAO.executeForMapArray("E_SET.getBatchUserRoleDeptAlert", parameter);
		outputDTO.setBAUsers(this.getUserName(userBAAlerts));
		
		E_SET_S03Bean baE_SET_S03Bean = e_set_s03Common.checkInProcessStatus(BA);
        outputDTO.setBARetStatus(baE_SET_S03Bean.getRetStatus());
		
		List<LabelValueBean> user_role_depts = queryDAO.executeForObjectList("E_SET.getAllUsersRolesDepts", null);
		String esetRunDate = queryDAO.executeForObject("E_SET.getEsetRunDate", null, String.class);
		
		outputDTO.setUserList(user_role_depts);
		outputDTO.setEsetRunDate(esetRunDate);
		result.setResultObject(outputDTO);
		result.setResultString("success");
		return result;
	}
	
	private boolean isEmpty(Object obj) {
		if(obj != null && obj.toString() != "") {
			return false;
		}
		return true;
	}
	
	private String[] getUserName(Map<String, Object>[] user) {
		String[] userIDs = new String[USER_LIMIT];
		for(int i = 0; i < userIDs.length; i++) {
			if(i < user.length)
				userIDs[i] = user[i].get("USER_ROLE_DEPT").toString();
		}
		return userIDs;
	}
}
