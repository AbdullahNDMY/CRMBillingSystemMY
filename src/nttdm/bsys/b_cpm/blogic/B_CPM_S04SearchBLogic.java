/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : Customer Plan Management Inquiry
 * SERVICE NAME   : B_CPM_S01
 * FUNCTION       : Search data from database
 * FILE NAME      : B_CPM_S01SearchBLogic.java
 * 
 * Copyright c 2011 NTTDATA Corporation
 *
 * History
 * 2011/07/26 [Duong Nguyen] Created
 * 
**********************************************************/
package nttdm.bsys.b_cpm.blogic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_cpm_en.dto.B_CPM_CONSTANT;
import nttdm.bsys.b_cpm_en.dto.InputSearchPlan;
import nttdm.bsys.common.util.CommonUtils;

import org.apache.struts.action.ActionErrors;

/**
 * B_CPM_S04SearchBLogic.class<br>
 * <ul>
 * <li>search customer plan info inquiry</li>
 * </ul>
 * <br>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class B_CPM_S04SearchBLogic extends AbstractB_CPM_S04SearchBLogic implements B_CPM_CONSTANT {

    /**
     * BLogicMessages
     */
    private BLogicMessages errors;
    
	/**
	 * 
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	public BLogicResult execute(Map<String, Object> param) {
		BLogicResult result = new BLogicResult();
		errors = new BLogicMessages();
		Map<String, Object> resultObject = new HashMap<String, Object>();
		
		String actionFlg = CommonUtils.toString(param.get("actionFlg"));
		//Generate BIF Button Click
		if("generateBIF".equals(actionFlg)) {
		    InputSearchPlan inputSearch = (InputSearchPlan)param.get("inputSearchPlan");
		    String[] idCustPlans = inputSearch.getIdCustPlans();
		    List<Map<String, Object>> tCustPlanHList = null;
		    //Check if any gdcCheckbox is checked
		    if (idCustPlans!=null && 0<idCustPlans.length) {
		        Map<String, Object> tCustPlanHSqlParam = new HashMap<String, Object>();
		        tCustPlanHSqlParam.put("idCustPlans", idCustPlans);
		        tCustPlanHList = queryDAO.executeForMapList(NAMESPACE + "B_CPM_S04_SELECT_T_CUST_H_INFO", tCustPlanHSqlParam);
		        // Check if gdcTrans selected are the same.
		        boolean transactionTypeFlg = valueIsSame(tCustPlanHList, "TRANSACTION_TYPE");
		        // Check if gdcCur selected are the same.
                boolean billCurFlg = valueIsSame(tCustPlanHList, "BILL_CURRENCY");
                // Check if EXPORT_CURRENCY selected are the same.
                boolean expCurFlg = valueIsSame(tCustPlanHList, "EXPORT_CURRENCY");
                // Check if FIXED_FOREX selected are the same.
                boolean fixedForexFlg = valueIsSame(tCustPlanHList, "FIXED_FOREX");
                // Check if ID_BILL_ACCOUNT selected are the same.
                boolean billAccFlg = valueIsSame(tCustPlanHList, "ID_BILL_ACCOUNT");
		        
                if (!transactionTypeFlg) {
                    this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC035",new Object[] {"Plan", "same Transaction Type"}));
		        }
                if (!billCurFlg) {
                    this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC035",new Object[] {"Plan", "same Billing Currency"}));
                }
                if (!expCurFlg) {
                    this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC035",new Object[] {"Plan", "same Export Currency"}));
                }
                if (!fixedForexFlg) {
                    this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC035",new Object[] {"Plan", "same Fixed Forex Rate"}));
                }
                if (!billAccFlg) {
                    this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC105",new Object[] {"Not allow to generate BIF - different billing account no. in selected customer plans. Please reassign billing account before generate BIF."}));
                }
		    } else {
		        this.errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC033",new Object[] {"Plan", "Plan"}));
		    }
		    if (this.errors.isEmpty()) {
		        String idCust = inputSearch.getCustomerId();
                String billType = CommonUtils.toString(tCustPlanHList.get(0).get("TRANSACTION_TYPE"));
                StringBuffer idCustPlanSB = new StringBuffer();
                for (int i=0;i<idCustPlans.length;i++) {
                    idCustPlanSB.append(":").append(idCustPlans[i]).append(":");
                }
                
                resultObject.put("idCust", idCust);
                resultObject.put("idCustPlan", idCustPlanSB.toString());
                resultObject.put("bifType", billType);
                if("CN".equals(billType)) {
                    result.setResultString("generateBIFSuccessCN");
                } else {
                    result.setResultString("generateBIFSuccessINAndDN");
                }
		    } else {
		        result.setResultString("generateBIFFail");
		    }
		} else {
		    //Search Button Click
	        B_CPM_S04Common b_cpm_s04Common = new B_CPM_S04Common(queryDAO);
	        //do search
	        b_cpm_s04Common.doSearch(param, resultObject);
	        
	        //Search clear checkBox checked item
	        InputSearchPlan inputSearch = (InputSearchPlan)resultObject.get("inputSearchPlan");
	        inputSearch.setIdCustPlans(null);
	        resultObject.put("inputSearchPlan", inputSearch);
	        
	        result.setResultString("success");
		}
		String nontaxinvoiceFlg=CommonUtils.toString(queryDAO.executeForObject("B_CPM.getNonTaxInvoiceShowFlg",null, String.class));
        resultObject.put("nontaxinvoiceFlg",nontaxinvoiceFlg);
		result.setErrors(errors);
		result.setResultObject(resultObject);
		return result;
	}
	
	private boolean valueIsSame(List<Map<String, Object>> list, String typeName) {
	    boolean flag = true;
	    
	    if (list!=null && 0<list.size()) {
	        for(int i=0;i<list.size();i++) {
	            Map<String, Object> map1 =list.get(i);
	            String value1 = CommonUtils.toString(map1.get(typeName)).trim();
	            for(int j=i+1;j<list.size();j++) {
	                Map<String, Object> map2 =list.get(j);
	                String value2 = CommonUtils.toString(map2.get(typeName)).trim();
	                if(!value1.equals(value2)) {
	                    return false;
	                }
	            }
	        }
	    }
	    
	    return flag;
	}
}