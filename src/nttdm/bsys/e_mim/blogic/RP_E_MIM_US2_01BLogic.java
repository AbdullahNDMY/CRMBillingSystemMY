/*
 * @(#)RP_E_MIM_US2_01BLogic.java
 *
 *
 */
package nttdm.bsys.e_mim.blogic;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.apache.struts.util.LabelValueBean;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.bean.G_SET_ReturnValue;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_SET_P03;
import nttdm.bsys.e_mim.dto.RP_E_MIM_US2_01Input;
import nttdm.bsys.e_mim.dto.RP_E_MIM_US2_01Output;
import nttdm.bsys.e_mim.blogic.AbstractRP_E_MIM_US2_01BLogic;

/**
 * BusinessLogic class.
 * 
 * @author khungl0ng
 */
public class RP_E_MIM_US2_01BLogic extends AbstractRP_E_MIM_US2_01BLogic {

	/**
	 * 
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	@SuppressWarnings("unchecked")
	public BLogicResult execute(RP_E_MIM_US2_01Input param) {
		BLogicResult result = new BLogicResult();
		RP_E_MIM_US2_01Output outputDTO = new RP_E_MIM_US2_01Output();
		// Paging
        BillingSystemSettings bss = new BillingSystemSettings(queryDAO);
        int row = bss.getResultRow();
        Integer startIndex = param.getStartIndex();
		// Get list history
		Integer totalHistories = queryDAO.executeForObject("E_MIM.getTotalHistories", null, Integer.class);
		if (startIndex == null || startIndex < 0 || startIndex > totalHistories)
			startIndex = 0;
		List<HashMap> listHistories = queryDAO.executeForObjectList("E_MIM.getHistories", null, startIndex, row);
		// Set paging parameter
		
		outputDTO.setRow(row);
		outputDTO.setTotalRow(totalHistories);
		outputDTO.setStartIndex(startIndex);
		// Set data
		outputDTO.setListHistories(listHistories);
		initData(param, outputDTO);
		
		G_SET_P03 g_set_p03 = new G_SET_P03(queryDAO);
		
		try {
            G_SET_ReturnValue returnValue = g_set_p03.G_SET_P03_CheckStatus("G_CLC_P01");
            String retStatusStr = "";
            if (returnValue.getRetStatus() == -1) {
                retStatusStr = "N";
            } else {
                retStatusStr = "Y";
            }
            outputDTO.setRetStatusStr(retStatusStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
		        
		result.setResultObject(outputDTO);
		result.setResultString("success");
		return result;
	}
	
	private void initData(RP_E_MIM_US2_01Input param, RP_E_MIM_US2_01Output outputDTO){
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
			outputDTO.setYear(currYear + "");
			if(currMonth == 1){
				currMonth = 1;
				outputDTO.setMonth("0" + currMonth);
			}else{
				if(currMonth < 10){
					outputDTO.setMonth("0" + (currMonth - 1));
				}else{
					outputDTO.setMonth((currMonth - 1) + "");
				}
			}
		}
		
		for(int i = 1; i <= 12; i++){
			LabelValueBean lv = new LabelValueBean();
			if(i < 10){
				lv.setValue("0" + i);
				lv.setLabel("0" + i);
			}else{
				lv.setValue(i + "");
				lv.setLabel(i + "");
			}
			months.add(lv);
		}
		outputDTO.setListMonth(months);
		
		for(int i = currYear; i < currYear+5; i ++){
			years.add(new LabelValueBean(i + "", i + ""));
		}
		outputDTO.setListYear(years);
		
	}
}