/*
 * @(#)RP_E_MIM_US3_01BLogic.java
 *
 *
 */
package nttdm.bsys.e_mim.blogic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.e_mim.dto.RP_E_MIM_US3_01Input;
import nttdm.bsys.e_mim.dto.RP_E_MIM_US3_01Output;

import org.apache.struts.util.LabelValueBean;

public class RP_E_MIM_US3_01BLogic extends AbstractRP_E_MIM_US3_01BLogic {

	public static String ID_BATCH_TYPE_G_RAD_P01 = "G_RAD_P01";

	public BLogicResult execute(RP_E_MIM_US3_01Input param) {
		BLogicResult result = new BLogicResult();
		RP_E_MIM_US3_01Output outputDTO = new RP_E_MIM_US3_01Output();
		// Paging
        int row = 10;
        Integer startIndex = param.getStartIndex();
		// Get list history
		Integer totalHistories = queryDAO.executeForObject("E_MIM.getTotalT_RAD_IMP_HD", null, Integer.class);
		if (startIndex == null || startIndex < 0 || startIndex > totalHistories)
			startIndex = 0;
		List<HashMap<String, Object>> listHistories = queryDAO.executeForObjectList("E_MIM.getT_RAD_IMP_HD", null, startIndex, row);
		// get schedule info
		HashMap<String, Object> m = new HashMap<String, Object>();
		m.put("idBatchType", ID_BATCH_TYPE_G_RAD_P01);
		HashMap<String, Object> scheduleInfo = (HashMap<String, Object>) queryDAO.executeForMap("E_MIM.getScheduleSetting", m);
		if(scheduleInfo != null) {
			outputDTO.setSelDay(((java.math.BigDecimal) scheduleInfo.get("EXEC_DAY")).toString());
			outputDTO.setSelHour(((java.math.BigDecimal) scheduleInfo.get("EXEC_HOUR")).toString());
			outputDTO.setSelMinute(((java.math.BigDecimal) scheduleInfo.get("EXEC_MINUTE")).toString());
			outputDTO.setActiveStatus((String) scheduleInfo.get("IS_ACTIVE"));			
		}
		
		// Set paging parameter
		outputDTO.setTotalRow(totalHistories);
		outputDTO.setStartIndex(startIndex);
		outputDTO.setRow(row);
		// Set data
		outputDTO.setListHistory(listHistories);
		outputDTO.setBatchScheduleInfo(scheduleInfo);
		// init data
		initData(param, outputDTO);
		result.setResultObject(outputDTO);
		result.setResultString("success");
		return result;
	}
	
	private void initData(RP_E_MIM_US3_01Input param, RP_E_MIM_US3_01Output outputDTO){
		Calendar cal = Calendar.getInstance();
		ArrayList<LabelValueBean>  months = new ArrayList<LabelValueBean>();
		ArrayList<LabelValueBean> years = new ArrayList<LabelValueBean>();
		int currMonth = cal.get(Calendar.MONTH) + 1;
		int currYear = cal.get(Calendar.YEAR);

		if(CommonUtils.notEmpty(param.getMonth()) 
				&& CommonUtils.notEmpty(param.getYear())){
			outputDTO.setYear(param.getYear());
			outputDTO.setMonth(param.getMonth());
		}else{
			outputDTO.setMonth((currMonth - 1) + "");
			outputDTO.setYear(currYear + "");
//			if(currMonth == 1){
//				currMonth = 1;
//				outputDTO.setMonth("0" + currMonth);
//			}else{
//				if(currMonth < 10){
//					outputDTO.setMonth("0" + (currMonth - 1));
//				}else{
//					outputDTO.setMonth((currMonth - 1) + "");
//				}
//			}
		}
		
//		for(int i = 1; i <= 12; i++){
//			LabelValueBean lv = new LabelValueBean();
//			if(i < 10){
//				lv.setValue("0" + i);
//				lv.setLabel("0" + i);
//			}else{
//				lv.setValue(i + "");
//				lv.setLabel(i + "");
//			}
//			months.add(lv);
//		}
//		outputDTO.setListMonth(months);
//		
//		for(int i = currYear; i < currYear+5; i ++){
//			years.add(new LabelValueBean(i + "", i + ""));
//		}
//		outputDTO.setListYear(years);
		
	}
}