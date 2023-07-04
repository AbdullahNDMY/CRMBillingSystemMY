/*
 * @(#)RP_B_BIL_S01_02BLogic.java
 *
 *
 */
package nttdm.bsys.b_bil.blogic;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S01_02Input;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S01_02Output;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionMessages;

/**
 * BusinessLogic class.
 * 
 * @author khungl0ng
 */
public class RP_B_BIL_S01_02BLogic extends AbstractRP_B_BIL_S01_02BLogic {

	/**
	 * 
	 * @param param
	 * @return ビジ�?スロジック�?�実行�?果�?BLogicResultインスタンス。
	 */
	public BLogicResult execute(RP_B_BIL_S01_02Input param) {
		BLogicResult result = new BLogicResult();
		RP_B_BIL_S01_02Output outputDTO = new RP_B_BIL_S01_02Output();
		// paging info
		//get the number of row for paging
		BillingSystemSettings bss = new BillingSystemSettings(queryDAO);
		int row = bss.getResultRow();
        Integer startIndex = param.getStartIndex();
        // mapping param
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("tbxBillingReference", CommonUtils.escapeSQL(param.getTbxBillingReference()));
		//#263 [T11] Add customer type at inquiry screen and export result CT 06062017 ST
		m.put("tbxCustomerId", CommonUtils.escapeSQL(param.getTbxCustomerId()));
		m.put("cboCustomerType", CommonUtils.escapeSQL(param.getCboCustomerType()));
		m.put("cboCategory", CommonUtils.escapeSQL(param.getCboCategory()));
		//#263 [T11] Add customer type at inquiry screen and export result CT 06062017 EN
		m.put("tbxCustomerName", CommonUtils.escapeSQL(param.getTbxCustomerName()));
		m.put("cboTransactionType", param.getCboTransactionType());
		m.put("tbxBillingAccountNo", CommonUtils.escapeSQL(param.getTbxBillingAccountNo()));
		m.put("jobNo", CommonUtils.escapeSQL(param.getJobNo()));
		m.put("tbxBillingDateFrom", CommonUtils.parseToDate(param.getTbxBillingDateFrom(), "dd/MM/yyyy"));
		m.put("tbxBillingDateTo", CommonUtils.parseToDate(param.getTbxBillingDateTo(), "dd/MM/yyyy"));
		m.put("cboDocumentStatus", param.getCboDocumentStatus());
		m.put("cboDeletedStatus", param.getCboDeletedStatus());
		m.put("cboBillingCurrency", param.getCboBillingCurrency());
		
		String issueTypeAllNotChecked = "";
		String issueTypeSingpost = CommonUtils.toString(param.getIssueTypeSingpost());
        String issueTypeAuto = CommonUtils.toString(param.getIssueTypeAuto());
        String issueTypeManual = CommonUtils.toString(param.getIssueTypeManual());
		
        if (CommonUtils.isEmpty(issueTypeSingpost)
                &&CommonUtils.isEmpty(issueTypeAuto)
                &&CommonUtils.isEmpty(issueTypeManual)) {
            issueTypeAllNotChecked = "0";
        }
		m.put("issueTypeSingpost", issueTypeSingpost);
		m.put("issueTypeAuto", issueTypeAuto);
		m.put("issueTypeManual", issueTypeManual);
		m.put("issueTypeAllNotChecked", issueTypeAllNotChecked);
		
		String allNotChecked = "";
        String fullySettled = CommonUtils.toString(param.getFullySettled());
        String partiallySettled = CommonUtils.toString(param.getPartiallySettled());
        String outstanding = CommonUtils.toString(param.getOutstanding());
        if (CommonUtils.isEmpty(fullySettled)
                &&CommonUtils.isEmpty(partiallySettled)
                &&CommonUtils.isEmpty(outstanding)) {
            allNotChecked = "0";
        }
        m.put("allNotChecked", allNotChecked);
        m.put("fullySettled", fullySettled);
        m.put("partiallySettled", partiallySettled);
        m.put("outstanding", outstanding);
        //add for #143 Start
        List<String> deliveryEmailList = new ArrayList<String>();
        if(!param.getDeliveryEmail1().isEmpty()){
        	deliveryEmailList.add(param.getDeliveryEmail1());
        }
        if(!param.getDeliveryEmail2().isEmpty()){
        	deliveryEmailList.add(param.getDeliveryEmail2());
        }
        if(deliveryEmailList.size() > 0){
        	String[] deliveryEmail= (String[])deliveryEmailList.toArray(new String[deliveryEmailList.size()]);
            param.setDeliveryEmail(deliveryEmail);
        }
        
        List<String> deliveryList = new ArrayList<String>();
        if(!param.getDelivery1().isEmpty()){
        	deliveryList.add(param.getDelivery1());
        }
        if(!param.getDelivery2().isEmpty()){
        	deliveryList.add(param.getDelivery2());
        }
        if(!param.getDelivery4().isEmpty()){
        	deliveryList.add(param.getDelivery4());
        }
        if(deliveryList.size() > 0){
        	String[] delivery= (String[])deliveryList.toArray(new String[deliveryList.size()]);
            param.setDelivery(delivery);
        }
        
        //add for #143 End
        m.put("deliveryEmail", param.getDeliveryEmail());
		if (param.getDelivery() != null && param.getDelivery().length == 3) {
			m.put("delivery", new String[] { "1", "2", "3", "4" });
		} else {
			m.put("delivery", param.getDelivery());
		}
        
		// count total record
		Integer totalReport = queryDAO.executeForObject("B_BIL.countT_BIL_H", m, Integer.class);
		if (startIndex == null || startIndex < 0 || startIndex > totalReport)
			startIndex = 0;
		//#263 [T11] Add customer type at inquiry screen and export result CT 06062017 ST
		List<HashMap<String, Object>> listReport = null;
		listReport = this.queryDAO.executeForObjectList("B_BIL.getT_BIL_H", m, startIndex, row);
		
		//System.out.println("listReport 2 size> "+listReport2.size());*/
		//#263 [T11] Add customer type at inquiry screen and export result CT 06062017 EN
		//filter by category
		/*List<HashMap<String, Object>> listReport2 = new ArrayList<HashMap<String, Object>>(listReport);
		if(!"".equals(param.getCboCategory())){
			for(int i=0 ; i<listReport.size() ; i++){
				HashMap<String, Object> temp = listReport.get(i);
				for ( Map.Entry<String, Object> entry : temp.entrySet()) {
					List<String> category = new ArrayList<String>();
					String categoryString = "";
				    String key = entry.getKey();
				    Object tab = entry.getValue();
				    if(key.equals("ID_REF")){
					    Map<String, Object> a = new HashMap<String, Object>();
					    a.put("ID_REF", tab);
					    category = this.queryDAO.executeForObjectList("B_BIL.getCategory", a);
					    for(int j = 0 ; j < category.size() ; j++){
					    	if(!(category.get(j)).equals(param.getCboCategory())){
					    		listReport2.remove(i);
						    }
					    }
				    }
				}
			}
		}
		System.out.println("listReport 2 size> "+listReport2.size());*/
		try {
			BeanUtils.copyProperties(outputDTO, param);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		if(totalReport==0){
		    // info.ERR2SC002
            BLogicMessages msgs = new BLogicMessages();
            BLogicMessage msg = new BLogicMessage("info.ERR2SC002", new Object());
            msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
            result.setMessages(msgs);
		}
		outputDTO.setInitFlg("0");
		outputDTO.setAccessType(param.getAccessType());
		outputDTO.setListReport(listReport);
		outputDTO.setTotalRow(totalReport);
		outputDTO.setRow(row);
		result.setResultObject(outputDTO);
		result.setResultString("success");
		return result;
	}
	
}