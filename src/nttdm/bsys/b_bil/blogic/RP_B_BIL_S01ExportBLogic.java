/**
 * @(#)RP_B_BIL_S01ExportBLogic.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2013/03/01
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.b_bil.blogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.struts.actions.DownloadFile;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S01ExportInput;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_RPT_P01;
import nttdm.bsys.common.util.GlobalProcessResult;
import nttdm.bsys.common.util.dto.G_RPT_P01_Input;
import nttdm.bsys.common.util.dto.G_RPT_P01_Output;
import nttdm.bsys.util.ApplicationContextProvider;

import org.springframework.context.ApplicationContext;


/**
 * @author gplai
 *
 */
public class RP_B_BIL_S01ExportBLogic extends AbstractRP_B_BIL_S01ExportBLogic {

    /* (non-Javadoc)
     * @see jp.terasoluna.fw.service.thin.BLogic#execute(java.lang.Object)
     */
    public BLogicResult execute(RP_B_BIL_S01ExportInput param) {
        BLogicResult result = new BLogicResult();
        // paging info
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
        m.put("tbxBillingDateFrom", CommonUtils.parseToDate(param.getTbxBillingDateFrom(), "dd/MM/yyyy"));
        m.put("tbxBillingDateTo", CommonUtils.parseToDate(param.getTbxBillingDateTo(), "dd/MM/yyyy"));
        m.put("cboDocumentStatus", param.getCboDocumentStatus());
        m.put("cboDeletedStatus", param.getCboDeletedStatus());
        m.put("cboBillingCurrency", param.getCboBillingCurrency());
        // #251 B-BIL-S01 Export Result Not filter by Job No. CT 08052017 ST
        m.put("jobNo", CommonUtils.escapeSQL(param.getJobNo()));
        // #251 B-BIL-S01 Export Result Not filter by Job No. CT 08052017 EN
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
        //Search delivery by email no filter in export CT 15 Aug 2017
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
      //Search delivery by email no filter in export CT 15 Aug 2017
        m.put("deliveryEmail", param.getDeliveryEmail());
		if (param.getDelivery().length == 3) {
			m.put("delivery", new String[] { "1", "2", "3", "4" });
		} else {
			m.put("delivery", param.getDelivery());
		}
        
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
        
        String isOracle12c = this.queryDAO.executeForObject("B_BIL.GET_ORACLE_VERSION", null,String.class);
        if(isOracle12c != null && "1".equals(isOracle12c)){
        	m.put("isOracle12c", isOracle12c);
        }
        
        List<HashMap<String, Object>> listReport = this.queryDAO.executeForObjectList("B_BIL.getT_BIL_H_EXPORT", m);
        //List<HashMap<String, Object>> listReport2 = new ArrayList<HashMap<String,Object>>(listReport);
        //HashMap<String, String> temp3 = new HashMap<String, String>();
        
		/*for(int i=0 ; i<listReport.size() ; i++){
			HashMap<String, Object> temp = new HashMap<String, Object>(listReport.get(i));
			System.out.println(temp.get("ID_REF"));
			Map<String, Object> a = new HashMap<String, Object>();
		    a.put("ID_REF", temp.get("ID_REF"));
		    List<String> category = this.queryDAO.executeForObjectList("B_BIL.getCategory", a);
		    listReport.get(i).put("Category", concatCategory(category));
			for (Iterator<Entry<String, Object>> it = temp.entrySet().iterator();it.hasNext();) {
				Entry<String, Object> temp2 = it.next();
				String key = temp2.getKey();
			    Object tab = temp2.getValue();
			    if(key.equals("ID_REF")){
				    Map<String, Object> a = new HashMap<String, Object>();
				    a.put("ID_REF", tab);
				    List<String> category = this.queryDAO.executeForObjectList("B_BIL.getCategory", a);
				    listReport.get(i).put("Category", concatCategory(category));
				    break;
				    //temp3.put((String) tab, concatCategory(category));
			    }
			}
		}*/
		
		
        // Call G_RPT_P01
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        G_RPT_P01 gRptP01 = (G_RPT_P01) context.getBean("G_RPT_P01");
        G_RPT_P01_Output gRptP01Output = new G_RPT_P01_Output();
        G_RPT_P01_Input gRptP01Input = new G_RPT_P01_Input();
        gRptP01Input.setListAgingReport(listReport);
        gRptP01Input.setReportType("BIL");
        // The method execute is called G_RPT_P01
        GlobalProcessResult gRptResult = gRptP01.execute(gRptP01Input, gRptP01Output);
        // error info
        result.setErrors(gRptResult.getErrors());
        // message info
        result.setMessages(gRptResult.getMessages());
        // Set download file
        DownloadFile file = new DownloadFile(gRptResult.getFile());
        result.setResultObject(file);
        return result;
    }
    
    public String concatCategory(List<String> category){
		String categoryString = "";
		for(int j = 0 ; j < category.size() ; j++){
	    	categoryString += category.get(j) + "/";
	    }
		categoryString = categoryString.substring(0, categoryString.length()-1);
		return categoryString;
	}
}
