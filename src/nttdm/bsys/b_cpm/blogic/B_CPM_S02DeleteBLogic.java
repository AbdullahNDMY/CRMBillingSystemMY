/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : Customer Plan Management (Delete)
 * SERVICE NAME   : B_CPM_S02
 * FUNCTION       : delete customer plan
 * FILE NAME      : B_CPM_S02DeleteBLogic.java
 * 
 * Copyright c 2011 NTTDATA Corporation
 *
 * History
 * 2011/07/26 [Duong Nguyen] Created
 * 2011/10/25 [Duong Nguyen] Fix Bug #2834
 * 
**********************************************************/
package nttdm.bsys.b_cpm.blogic;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;

import nttdm.bsys.b_cpm.blogic.AbstractB_CPM_S02DeleteBLogic;
import nttdm.bsys.b_cpm.common.B_CPM_S02Util;
import nttdm.bsys.b_cpm_en.dto.B_CPM_CONSTANT;
import nttdm.bsys.b_cpm_en.dto.CustomerPlan;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.RadUserTUtil;

/**
 * B_CPM_S02DeleteBLogic.class<br>
 * <ul>
 * <li>delete customer plan</li>
 * </ul>
 * <br>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class B_CPM_S02DeleteBLogic extends AbstractB_CPM_S02DeleteBLogic {

	/**
	 * 
	 * @param param
	 * @return ビジネスロジチE��の実行結果、BLogicResultインスタンス、E
	 */
	public BLogicResult execute(HashMap<String, Object> param) {
		BLogicResult result = new BLogicResult();
		BLogicMessages errors = new BLogicMessages();
		Map<String, Object> resultObject = new HashMap<String, Object>();

		// TODO
		// QueryDAO 記述侁E
		// SampleUVO uvo = queryDAO.executeForObject("namespace.sqlID",
		// params, SampleUVO().class);
		//
		// UpdateDAO 記述侁E
		// updateDAO.execute("namespace.sqlID", params);
		//
		CustomerPlan customerPlan = (CustomerPlan) param.get("customerPlan"); 
		BillingSystemUserValueObject uvo = (BillingSystemUserValueObject) param.get("uvo");
		String idCustPlan = customerPlan.getIdCustPlan();
		
		B_CPM_S02Util util = new B_CPM_S02Util(this.queryDAO, this.updateDAO);
		Map<String, Object> subscriptionInfoMap = util.getSubScriptionInfoByIdCustPlan(idCustPlan);
        if (subscriptionInfoMap!=null) {
            String accessAccountTest = CommonUtils.toString(subscriptionInfoMap.get("ACCESS_ACCOUNT_TEST"));
            if("1".equals(accessAccountTest)) {
                errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC105", new Object[] {"Connection test in progress. Please click Test Complete button before deletion at subscription information maintenance."}));
                resultObject.put("customerPlan", customerPlan);
                result.setErrors(errors);
                result.setResultObject(resultObject);
                result.setResultString("failure");
                return result;
            }
        }
		
		//get audit trail
		Integer idAudit = CommonUtils.auditTrailBegin(uvo.getId_user(), BillingSystemConstants.MODULE_B, BillingSystemConstants.SUB_MODULE_B_CPM, 
		        customerPlan.getIdCustPlan(), customerPlan.getPlanStatus(), BillingSystemConstants.AUDIT_TRAIL_DELETED);
	
		customerPlan.setIdAudit(idAudit.toString());
		customerPlan.setIdLogin(uvo.getId_user());
		//delete customer plan
		//delete relative CUST_PLAN_H
		util.deleteCustomerPlanH(customerPlan);
		//delete relative CUST_PLAN_TB_CPM_S02Util
		util.deleteCustomerPlanHD(customerPlan);
		//delete relative CUST_PLAN_LINK
		util.deleteCustomerPlanHLink(customerPlan);
		//delete relative CUST_PLAN_SVC
		util.deleteCustomerPlanHSVC(customerPlan);
		// delete relative T_SUBSCRIPTION_INFO
		util.deleteSubInfo(customerPlan);
		RadUserTUtil radUserTUtil = new RadUserTUtil(radiusQueryDAO,
				radiusUpdateDAO);
		String userName = String.valueOf(util.getAccessAccount(idCustPlan).get(
				"ACCESS_ACCOUNT"));
		if (util.isExistPlanGrp(idCustPlan)) {

			if (radUserTUtil.isExistUserName(userName)) {
				radUserTUtil.deleteByUserName(userName);
			}
		}
		CommonUtils.auditTrailEnd(idAudit);
        //setting message result
		BLogicMessages paramMessages= new BLogicMessages();
		paramMessages.add(Globals.MESSAGE_KEY,new BLogicMessage(B_CPM_CONSTANT.DELETE_SUCCESS_MSG));
		result.setMessages(paramMessages);
		
		result.setResultString("exit");
		return result;
	}
}