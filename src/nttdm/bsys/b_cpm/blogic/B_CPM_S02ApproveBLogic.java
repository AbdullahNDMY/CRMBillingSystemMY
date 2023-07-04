/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : Customer Plan Management (Approve)
 * SERVICE NAME   : B_CPM_S02
 * FUNCTION       : approve draft customer plan
 * FILE NAME      : B_CPM_S02ApproveBLogic.java
 * 
 * Copyright c 2011 NTTDATA Corporation
 *
 * History
 * 2011/07/26 [Duong Nguyen] Created
 * 2011/10/12 [Duong Nguyen] Update DD change #2761
 * 2011/10/18 [Duong Nguyen] Update DD change #2761
 * 2011/10/18 [Duong Nguyen] Fix Bug #2850
 * 
**********************************************************/
package nttdm.bsys.b_cpm.blogic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_cpm.common.B_CPM_S02Util;
import nttdm.bsys.b_cpm_en.dto.CustomerPlan;
import nttdm.bsys.b_cpm_en.dto.CustomerPlanService;
import nttdm.bsys.b_cpm_en.dto.CustomerSubPlan;
import nttdm.bsys.common.bean.RAD_USERS_T;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_CPM_P01;
import nttdm.bsys.common.util.RadUserTUtil;
import nttdm.bsys.common.util.dto.G_CPM_P01Input;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;

/**
 * B_CPM_S02ApproveBLogic.class<br>
 * <ul>
 * <li>approve customer plan</li>
 * </ul>
 * <br>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class B_CPM_S02ApproveBLogic extends AbstractB_CPM_S02ApproveBLogic {

    private BLogicMessages errors;
    
	/**
	 * 
	 * @param param
	 * @return 繝薙ず繝阪せ繝ｭ繧ｸ繝・・ｽ・ｽ縺ｮ螳溯｡檎ｵ先棡縲。LogicResult繧､繝ｳ繧ｹ繧ｿ繝ｳ繧ｹ縲・
	 */
	public BLogicResult execute(HashMap<String, Object> param) {
		BLogicResult result = new BLogicResult();
		errors = new BLogicMessages();
		// TODO
		// QueryDAO 險倩ｿｰ萓・
		// SampleUVO uvo = queryDAO.executeForObject("namespace.sqlID",
		// params, SampleUVO().class);
		//
		// UpdateDAO 險倩ｿｰ萓・
		// updateDAO.execute("namespace.sqlID", params);
		//
		CustomerPlan customerPlan = (CustomerPlan)param.get("customerPlan");
		BillingSystemUserValueObject uvo = (BillingSystemUserValueObject)param.get("uvo");
		B_CPM_S02Util util = new B_CPM_S02Util(this.queryDAO, this.updateDAO);
		
		//customer plan currency fifferent M_PLAN_H currency click yes button
        String clickDifCurrencyYesFlg = CommonUtils.toString(customerPlan.getClickDifCurrencyYesFlg());
        //not click yes button
        if(CommonUtils.isEmpty(clickDifCurrencyYesFlg)) {
            //Customer Plan Currency
            String custPlanCurrency = customerPlan.getBillCurrency();
            for (CustomerPlanService service : customerPlan.getServices()) {
                for (CustomerSubPlan subPlan : service.getSubPlans()) {
                    String idPlan = CommonUtils.toString(subPlan.getIdPlan());
                    //Standard Plan Currency
                    String standardPlanCurrency = util.getStdPlanCurrency(idPlan);
                    //Customer Plan Currency different from Standard Plan Currency
                    if(!standardPlanCurrency.equals(custPlanCurrency)) {
                        customerPlan.setCustPlanMPlanCurDifFlg("1");
                        Map<String, Object> returnObject = new HashMap<String, Object>();
                        returnObject.put("customerPlan", customerPlan);
                        result.setResultObject(returnObject);
                        result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
                        return result;
                    }
                    break;
                }
            }
        }
        customerPlan.setCustPlanMPlanCurDifFlg("");
        
        Map<String, Object> mapParam = new HashMap<String, Object>();
        if (radiusCheck(customerPlan.getIdCustPlan(), util, mapParam)) {
    		/** Update DD change #2761 start **/
    		G_CPM_P01 gCpmP01 = new G_CPM_P01(this.queryDAO, this.updateDAO, 
    		        this.radiusQueryDAO, this.radiusUpdateDAO, uvo.getId_user());
    		
    		boolean isComplete = true;
    		
    		Integer idAudit = CommonUtils.auditTrailBegin(uvo.getId_user(), BillingSystemConstants.MODULE_B, 
    		        BillingSystemConstants.SUB_MODULE_B_CPM, customerPlan.getIdCustPlan(), 
                    "PS3", BillingSystemConstants.AUDIT_TRAIL_EDITED);
    		
    		//initialize input for G-CPM-P01
    		G_CPM_P01Input input = new G_CPM_P01Input();
    //            input.setBill_currency(customerPlan.getBillCurrency());
    //            input.setPayment_method(customerPlan.getPaymentMethod());
    //            input.setIs_display_exp_amt("0");
    //            String exportCurrency = CommonUtils.toString(customerPlan.getExpGrandTotal()).trim();
    //            if ("-".equals(exportCurrency)) {
    //                input.setExport_currency("");
    //            } else {
    //                input.setExport_currency(customerPlan.getExpGrandTotal());
    //            }
    //            input.setFixed_forex(customerPlan.getFixedForex());
    		input.setId_cust(customerPlan.getIdCust());
    		input.setId_cust_plan(customerPlan.getIdCustPlan());
    		input.setId_login(uvo.getId_user());
    		input.setId_audit(CommonUtils.toString(idAudit));
            input.setAccessAccount(CommonUtils.toString(mapParam.get("accessAccount")));
            input.setAccessPw(CommonUtils.toString(mapParam.get("accessPw")));
            input.setRadius((Boolean)mapParam.get("isRadius"));
            input.setUpadteT_BAC_H(false);
    		
    		//update for each service
    		for (CustomerPlanService service : customerPlan.getServices()) {
    			input.setApprove_type("S");
    			input.setId_cust_plan_grp(service.getIdCustPlanGrp());
    			isComplete = isComplete && gCpmP01.execute(input, G_CPM_P01.B_CPM_S02v);
    			if (!isComplete) {
    			    break;
    			}
    		}
    		
    		BLogicMessages msgs = new BLogicMessages();
    		if (isComplete) {
    			msgs.add(Globals.MESSAGE_KEY, new BLogicMessage("info.ERR2SC007","Approve"));
    		}
    		result.setErrors(input.getErrorMessages());
    		result.setMessages(msgs);
    		
    		CommonUtils.auditTrailEnd(idAudit);
        } else {
            result.setErrors(errors);
        }
		
		Map<String, Object> returnObject = new HashMap<String, Object>();
        returnObject.put("customerPlan", customerPlan);
        result.setResultObject(returnObject);
		/** Update DD change #2761 end **/
		result.setResultString("success");
		return result;
	}
	
	private boolean radiusCheck(String idCustPlan, B_CPM_S02Util util, Map<String, Object> mapParam) {
	    boolean result = true;
	    List<Map<String, Object>> mapPlanBatchMapping = util.getPlanBatchMappingByIdCustPlan(idCustPlan);
	    if(mapPlanBatchMapping != null && 0 < mapPlanBatchMapping.size()){
	        Map<String, Object> subScriptionInfo = util.getSubScriptionInfoByIdCustPlan(idCustPlan);
	        //ACCESS_ACCOUNT
	        String accessAccount = "";
	        //ACCESS_PW
	        String accessPw = "";
	        
	        if (subScriptionInfo != null) {
                //ACCESS_ACCOUNT
                accessAccount = CommonUtils.toString(subScriptionInfo.get("ACCESS_ACCOUNT"));
                if (CommonUtils.isEmpty(accessAccount)) {
                    errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC069", new Object[] {}));
                    return false;
                }
                String accessAccountTest = CommonUtils.toString(subScriptionInfo.get("ACCESS_ACCOUNT_TEST"));
                if ("1".equals(accessAccountTest)) {
                    errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC105", new Object[] {"Connection test is in progress. Please click Test Complete button before approval at subscription information maintenance."}));
                    return false;
                }
                RadUserTUtil radUserTUtil = new RadUserTUtil(radiusQueryDAO, radiusUpdateDAO);
                //select by PK
                RAD_USERS_T radUserT = radUserTUtil.selectByPK(accessAccount);
                if (radUserT != null) {
                    errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC067", new Object[] {}));
                    return false;
                }
            } else {
                errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC069", new Object[] {}));
                return false;
            }
	        //ACCESS_PW
            accessPw = CommonUtils.toString(subScriptionInfo.get("ACCESS_PW"));
            mapParam.put("accessAccount", accessAccount);
            mapParam.put("accessPw", accessPw);
	        mapParam.put("isRadius", true);
	    } else {
	        mapParam.put("isRadius", false);
	    }
	    return result;
	}
}