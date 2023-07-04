/*
 * @(#)RP_E_MIM_US3_02BLogic.java
 *
 *
 */
package nttdm.bsys.e_mim.blogic;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.e_mim.dto.RP_E_MIM_US3_02Input;

import org.apache.struts.action.ActionMessages;

public class RP_E_MIM_US3_02BLogic extends AbstractRP_E_MIM_US3_02BLogic {
	public static String ID_BATCH_TYPE_G_RAD_P01 = "G_RAD_P01";

	public BLogicResult execute(RP_E_MIM_US3_02Input param) {
		BLogicResult result = new BLogicResult();
		//RP_E_MIM_US3_02Output outputDTO = new RP_E_MIM_US3_02Output();
		// get param to map
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("execDay", CommonUtils.isEmpty(param.getSelDay()) ? 1 : param.getSelDay());
		parameter.put("execHour", CommonUtils.isEmpty(param.getSelHour()) ? 0 : param.getSelHour());
		parameter.put("execMinute", CommonUtils.isEmpty(param.getSelMinute()) ? 0 : param.getSelMinute());
		parameter.put("isActive", CommonUtils.isEmpty(param.getActiveStatus()) ? 0 : param.getActiveStatus());
		parameter.put("idLogin",  param.getUvo().getId_user());
		parameter.put("idBatchType", ID_BATCH_TYPE_G_RAD_P01);
		
		Map<String, Object> settings = queryDAO.executeForMap("E_MIM.getScheduleSetting", parameter);
		if(settings == null) {
			updateDAO.execute("E_MIM.addSchedule", parameter);
		}
		else {
			updateDAO.execute("E_MIM.updateSchedule", parameter);
		}
		
		
//		HashMap<String, Object> inputData = new HashMap<String, Object>();
//		inputData.put("execDay", param.getSelDay());
//		inputData.put("execHour", param.getSelHour());
//		inputData.put("execMinute", param.getSelMinute());
//		inputData.put("isActive", param.getActiveStatus());
//		inputData.put("updatedDate", new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
//		inputData.put("idLogin",  param.getUvo().getId_user());
//		inputData.put("idBatchType", ID_BATCH_TYPE_G_RAD_P01);
//		updateDAO.execute("E_MIM.updateSchedule", inputData);
		// success message
		BLogicMessages msgs = new BLogicMessages();
		BLogicMessage msg = new BLogicMessage("info.ERR2SC003", new Object[]{});
		msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
		result.setMessages(msgs);
		result.setResultString("success");
		return result;
	}
}