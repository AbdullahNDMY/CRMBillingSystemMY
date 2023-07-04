/**
 * Billing System
 * 
 * SUBSYSTEM NAME : RP_B_BTH_P01_
 * SERVICE NAME :  B_BTH_P01
 * FUNCTION : Print Billing Document
 * FILE NAME : RP_B_BTH_S01_01BLogic.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 */
package nttdm.bsys.b_bth.blogic;

import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_bth.dto.RP_B_BTH_P01_01Input;
import nttdm.bsys.b_bth.dto.RP_B_BTH_P01_01Output;
import nttdm.bsys.common.util.CommonUtils;

/**
 * BusinessLogic class.
 * 
 * @author NTT Data Vietnam	
 * Action display billing document
 * 
 */
public class RP_B_BTH_P01_01BLogic extends AbstractRP_B_BTH_S01_01BLogic {

	/**
	 * 
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	public BLogicResult execute(RP_B_BTH_P01_01Input param) {
		BLogicResult result = new BLogicResult();
		RP_B_BTH_P01_01Output outputDTO = new RP_B_BTH_P01_01Output();
		String nontaxinvoiceFlg=CommonUtils.toString(queryDAO.executeForObject("B_CPM.getNonTaxInvoiceShowFlg",null, String.class));
        // Add #156 Start
        String billCnAmtNegative = queryDAO.executeForObject("B_BTH.getBillCnAmtNegative", null, String.class);
        // Add #156 End
		outputDTO.setNonTaxInvoiceShowFlg(nontaxinvoiceFlg);
		
		String initFlg = CommonUtils.toString(param.getInitFlg());
        if (CommonUtils.isEmpty(initFlg)) {
            outputDTO.setCboDeletedStatus("0");
            outputDTO.setIssueTypeSingpost("0");
            outputDTO.setIssueTypeAuto("0");
            outputDTO.setIssueTypeManual("0");
            String[] deliveryEmail = new String[] { "1", "0" };
            String[] delivery = new String[] { "1", "2", "4" };
            outputDTO.setDelivery(delivery);
            outputDTO.setDeliveryEmail(deliveryEmail);
            // Add #156 Start
            outputDTO.setBillCnAmtNegative(billCnAmtNegative);
            // Add #156 End
        } else {
            outputDTO.setRow(param.getRow());
            outputDTO.setStartIndex(param.getStartIndex());
            outputDTO.setTotalRow(param.getTotalRow());
            outputDTO.setCboDeletedStatus(param.getCboDeletedStatus());
            outputDTO.setIssueTypeSingpost(param.getIssueTypeSingpost());
            outputDTO.setIssueTypeAuto(param.getIssueTypeAuto());
            outputDTO.setIssueTypeManual(param.getIssueTypeManual());
            outputDTO.setDelivery(param.getDelivery());
            outputDTO.setDeliveryEmail(param.getDeliveryEmail());
            // Add #156 Start
            outputDTO.setBillCnAmtNegative(param.getBillCnAmtNegative());
            // Add #156 End
        }
        // #191 Start
        List<Map<String, Object>> singPost = queryDAO.executeForMapList("B_BIL.getSinPostValue", null); 
        for (int i = 0; i < singPost.size(); i++) {
			if ("BIL01".equals(singPost.get(i).get("RESOURCE_ID").toString())) {
				outputDTO.setIssueTypeSingpostValue(singPost.get(i).get("VALUE").toString());
			}else if ("BIL02".equals(singPost.get(i).get("RESOURCE_ID").toString())) {
				outputDTO.setIssueTypeAutoValue(singPost.get(i).get("VALUE").toString());
			}else if ("BIL03".equals(singPost.get(i).get("RESOURCE_ID").toString())) {
				outputDTO.setIssueTypeManualValue(singPost.get(i).get("VALUE").toString());
			}
		}
        // #191 End
		result.setResultObject(outputDTO);
		result.setResultString("success");
		return result;
	}
	
	/*private void initData(RP_B_BTH_P01_01Input param, RP_B_BTH_P01_01Output outputDTO){
		
	}*/
}