/*
 * @(#)RP_B_BIL_S01_01BLogic.java
 *
 *
 */
package nttdm.bsys.b_bil.blogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.jndi.url.ldaps.ldapsURLContextFactory;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S01_01Input;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S01_01Output;
import nttdm.bsys.common.util.CommonUtils;

/**
 * BusinessLogic class.
 * 
 * @author khungl0ng
 */
public class RP_B_BIL_S01_01BLogic extends AbstractRP_B_BIL_S01_01BLogic {

    /**
     * 
     * @param param
     * @return ビジ�?スロジック�?�実行�?果�?BLogicResultインスタンス。
     */
    public BLogicResult execute(RP_B_BIL_S01_01Input param) {
        BLogicResult result = new BLogicResult();
        RP_B_BIL_S01_01Output outputDTO = new RP_B_BIL_S01_01Output();
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("idUser", param.getUvo().getId_user());
        HashMap<String, Object> userAccess = (HashMap<String, Object>) this.queryDAO.executeForMap("B_BIL.getAccessType", m);
        outputDTO.setAccessType(userAccess.get("ACCESS_TYPE").toString());

        String manualBillButtonShowFlg =  this.queryDAO.executeForObject("B_BIL.getManualBillButtonShowFlg", null, String.class);
        outputDTO.setManualBillButtonShowFlg(manualBillButtonShowFlg);

        String initFlg = CommonUtils.toString(param.getInitFlg());
        // Add #156 Start
        String billCnAmtNegative = queryDAO.executeForObject("B_BTH.getBillCnAmtNegative", null, String.class);
        // Add #156 End
        
        //if (CommonUtils.isEmpty(initFlg)) {
        if ("1".equals(initFlg)) {
            //add for #143 Start
        	outputDTO.setTbxBillingReference("");
        	//#263 [T11] Add customer type at inquiry screen and export result CT 06062017 ST
        	outputDTO.setTbxCustomerId("");
        	outputDTO.setCboCustomerType("0");
        	outputDTO.setCboCategory("0");
        	//#263 [T11] Add customer type at inquiry screen and export result CT 06062017 EN
        	outputDTO.setTbxCustomerName("");
        	outputDTO.setJobNo("");
        	outputDTO.setCboTransactionType("");
        	outputDTO.setCboBillingCurrency("");
        	outputDTO.setTbxBillingAccountNo("");
        	outputDTO.setTbxBillingDateFrom("");
        	outputDTO.setTbxBillingDateTo("");
        	outputDTO.setCboDocumentStatus("");
        	outputDTO.setRow(0);
        	outputDTO.setTotalRow(null);
        	outputDTO.setStartIndex(0);
        	List<HashMap<String,Object>> listReport = new ArrayList<HashMap<String,Object>>();
        	outputDTO.setListReport(listReport);
            outputDTO.setDelivery1("1");
            outputDTO.setDelivery2("2");
            outputDTO.setDelivery4("4");
            outputDTO.setDeliveryEmail1("1");
            outputDTO.setDeliveryEmail2("0");
            //add for #143 End
            outputDTO.setFullySettled("0");
            outputDTO.setPartiallySettled("0");
            outputDTO.setOutstanding("0");
            outputDTO.setCboDeletedStatus("0");
            outputDTO.setIssueTypeSingpost("0");
            outputDTO.setIssueTypeAuto("0");
            outputDTO.setIssueTypeManual("0");
            /*String[] deliveryEmail = new String[] { "1", "0" };
            String[] delivery = new String[] { "1", "2", "4" };
            outputDTO.setDelivery(delivery);
            outputDTO.setDeliveryEmail(deliveryEmail);*/
            // Add #156 Start
            outputDTO.setBillCnAmtNegative(billCnAmtNegative);
            // Add #156 End
        } else {
            //add for #143 Start
        	outputDTO.setTbxBillingReference(param.getTbxBillingReference());
        	//#263 [T11] Add customer type at inquiry screen and export result CT 06062017 ST
        	outputDTO.setTbxCustomerId(param.getTbxCustomerId());
        	outputDTO.setCboCustomerType(param.getCboCustomerType());
        	outputDTO.setCboCategory(param.getCboCategory());
        	//#263 [T11] Add customer type at inquiry screen and export result CT 06062017 EN
        	outputDTO.setTbxCustomerName(param.getTbxCustomerName());
        	outputDTO.setJobNo(param.getJobNo());
        	outputDTO.setCboTransactionType(param.getCboTransactionType());
        	outputDTO.setCboBillingCurrency(param.getCboBillingCurrency());
        	outputDTO.setTbxBillingAccountNo(param.getTbxBillingAccountNo());
        	outputDTO.setTbxBillingDateFrom(param.getTbxBillingDateFrom());
        	outputDTO.setTbxBillingDateTo(param.getTbxBillingDateTo());
        	outputDTO.setCboDocumentStatus(param.getCboDocumentStatus());
        	outputDTO.setTotalRow(param.getTotalRow());
        	outputDTO.setRow(param.getRow());
        	outputDTO.setStartIndex(param.getStartIndex());
        	outputDTO.setListReport(param.getListReport());
            outputDTO.setDelivery1(param.getDelivery1());
            outputDTO.setDelivery2(param.getDelivery2());
            outputDTO.setDelivery4(param.getDelivery4());
            outputDTO.setDeliveryEmail1(param.getDeliveryEmail1());
            outputDTO.setDeliveryEmail2(param.getDeliveryEmail2());
            //add for #143 End
            outputDTO.setFullySettled(param.getFullySettled());
            outputDTO.setPartiallySettled(param.getPartiallySettled());
            outputDTO.setOutstanding(param.getOutstanding());
            outputDTO.setCboDeletedStatus(param.getCboDeletedStatus());
            outputDTO.setIssueTypeSingpost(param.getIssueTypeSingpost());
            outputDTO.setIssueTypeAuto(param.getIssueTypeAuto());
            outputDTO.setIssueTypeManual(param.getIssueTypeManual());
            /*outputDTO.setDelivery(param.getDelivery());
            outputDTO.setDeliveryEmail(param.getDeliveryEmail());*/
            // Add #156 Start
            outputDTO.setBillCnAmtNegative(param.getBillCnAmtNegative());
            // Add #156 End
        }
        String nontaxinvoiceFlg=CommonUtils.toString(queryDAO.executeForObject("B_CPM.getNonTaxInvoiceShowFlg",null, String.class));
        outputDTO.setNonTaxInvoiceShowFlg(nontaxinvoiceFlg);
        
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
}