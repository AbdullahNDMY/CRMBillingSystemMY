/*
 * @(#)B_BIFS02_08BLogic.java
 *
 *
 */
package nttdm.bsys.bif.blogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.bif.dto.B_BIFS02Input;
import nttdm.bsys.bif.dto.B_BIFS03Input;
import nttdm.bsys.bif.dto.B_BIF_Output;
import nttdm.bsys.common.bean.RAD_USERS_T;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_CPM_P01;
import nttdm.bsys.common.util.G_WFM_P01;
import nttdm.bsys.common.util.RadUserTUtil;
import nttdm.bsys.common.util.dto.G_CPM_P01Input;

import org.apache.struts.action.ActionErrors;

/**
 * ビジネスロジッククラス。
 * 
 * @author duongnld
 */
public class B_BIFS02_08BLogic extends AbstractB_BIFS02_08BLogic {

	private static final String STATUS_INFO = "SELECT.B_BIF.S03_02.SQL001";
	private static final String UPDATE_CUST_PLAN_H_PS8 = "UPDATE.B_BIF.SQL002";
    private static final String UPDATE_CUST_PLAN_D_PS8 = "UPDATE.B_BIF.SQL003";
	private static final String UPDATE_CORPORATE_INFO = "UPDATE.B_BIF.S02.SQL001";
	private static final String UPDATE_CUSTOMER_INFO = "UPDATE.B_BIF.S02.SQL002";
	private static final String UPDATE_WF_STATUS = "UPDATE.B_BIF.S03_05.SQL001";
	private static final String GET_ID_CUST_PLAN = "SELECT.B_BIF.SQL008";
	
	private BLogicMessages errors;

	public BLogicResult execute(B_BIFS02Input input) {
		BLogicResult result = new BLogicResult();
		errors = new BLogicMessages();
		BLogicMessages messages = new BLogicMessages();
		result.setMessages(messages);
		
		B_BIF_Output out = new B_BIF_Output();
		
		List<Map<String, Object>> radiusMapList = new ArrayList<Map<String, Object>>();
		try {
		    if (input.getAction().equals(B_BIFS02Input.APPROVAL_ACTION)){
		        String id_ref = input.getIdRef();
		        //get status info
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("ID_REF", id_ref);
                //get id_cust_plan list
                List<String> id_cust_plan_list = queryDAO.executeForObjectList(GET_ID_CUST_PLAN, param);
                B_BIFUtils util = new B_BIFUtils(queryDAO, updateDAO);
                for (String id_cust_plan : id_cust_plan_list) {
                    Map<String, Object> mapParam = new HashMap<String, Object>();
                    if (!radiusCheck(id_cust_plan, util, mapParam)) {
                        out.setAction(B_BIFS02Input.APPROVAL_ACTION);
                        out.setIdRef(input.getIdRef());
                        result.setResultObject(out);
                        result.setErrors(errors);
                        result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
                        return result;
                    }
                    mapParam.put("idCustPlan", id_cust_plan);
                    radiusMapList.add(mapParam);
                }
                for(int i=0;i<radiusMapList.size();i++) {
                    Map<String, Object> map1 = radiusMapList.get(i);
                    boolean isRadius1 = (Boolean)map1.get("isRadius");
                    if(isRadius1){
                        String accessAccount1 = CommonUtils.toString(map1.get("accessAccount"));
                        for(int j=i+1;j<radiusMapList.size();j++) {
                            Map<String, Object> map2 = radiusMapList.get(j);
                            boolean isRadius2 = (Boolean)map2.get("isRadius");
                            if(isRadius2){
                                String accessAccount2 = CommonUtils.toString(map2.get("accessAccount"));
                                if(accessAccount1.equals(accessAccount2)) {
                                    errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC067", new Object[] {}));
                                    out.setAction(B_BIFS02Input.APPROVAL_ACTION);
                                    out.setIdRef(input.getIdRef());
                                    result.setResultObject(out);
                                    result.setErrors(errors);
                                    result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
                                    return result;
                                }
                            }
                        }
                    }
                }
		    }
		    
			/**
			 * Audit Trail
			 */
			Integer idAudit = CommonUtils.auditTrailBegin(input.getUvo().getId_user(), BillingSystemConstants.MODULE_B, BillingSystemConstants.SUB_MODULE_B_BIF, 
					input.getIdRef(), B_BIFUtils.getBifStatus(queryDAO, input.getIdRef()), BillingSystemConstants.AUDIT_TRAIL_EDITED);
			
			//update comment
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("IS_REVISED", input.getIsRevised());
			param.put("APPR_STATUS", input.getApprStatus());
			param.put("IS_SAVE", input.getIsSave());
			param.put("APPR_COMMENT", input.getApprComment());
			param.put("ID_WF_APPROVAL", input.getIdWfApproval());
			param.put("ID_LOGIN", input.getUvo().getId_user());
			param.put("ID_AUDIT", idAudit);
			this.updateDAO.execute(UPDATE_WF_STATUS, param);
			
			if (input.getSectionGroup().equals("BOP-IDN") && input.getIsSave().equals("1")) {
				param = new HashMap<String, Object>();
				param.put("BIL_OPBY", input.getBilOpBy());
				param.put("DATE_BIFRCV", input.getDateBifRcv());
				param.put("BIL_REFNO", input.getBilRefNo());
				param.put("DATE_PRINTED", input.getDatePrinted());
				param.put("DATE_SIGNED", input.getDateSigned());
				if (input.getChkIDC().equals("")) {
					param.put("BIL_IDC", "0");
				} else {
					param.put("BIL_IDC", input.getChkIDC());
				}
				if (input.getChkGIN().equals("")) {
					param.put("BIL_GIN", "0");
				} else {
					param.put("BIL_GIN", input.getChkGIN());
				}
				if (input.getChkSI().equals("")) {
					param.put("BIL_SI", "0");
				} else {
					param.put("BIL_SI", input.getChkSI());
				}
				if (input.getChkCorporate().equals("")) {
					param.put("BIL_COR", "0");
				} else {
					param.put("BIL_COR", input.getChkCorporate());
				}
				if (input.getChkOthers().equals("")) {
					param.put("BIL_OTH", "0");
				} else {
					param.put("BIL_OTH", input.getChkOthers());
				}

				param.put("ID_LOGIN", input.getUvo().getId_user());
				param.put("ID_REF", input.getIdRef());
				param.put("ID_AUDIT", idAudit);
				this.updateDAO.execute(UPDATE_CORPORATE_INFO, param);
				String accInput = input.getCustomerACNo();
				if (accInput != null && !accInput.equals("")) {
					param = new HashMap<String, Object>();
					param.put("CUST_ACC_NO", accInput);
					param.put("ID_CUST", input.getIdCust());
					param.put("ID_LOGIN", input.getUvo().getId_user());
					param.put("ID_AUDIT", idAudit);
					this.updateDAO.execute(UPDATE_CUSTOMER_INFO, param);
				}
			}
			
			if (input.getAction().equals(B_BIFS03Input.APPROVAL_ACTION)
					|| input.getAction().equals(B_BIFS03Input.REJECT_ACTION)) {

				String id_ref = input.getIdRef();
				
				String id_screen = B_BIFS02Input.ID_SCREEN;
				String section_no = input.getSectionNo();//statusInfo.get("SECTION_NO").toString();
				String sequence_no = "1";
				String approve_status = input.getApprStatus().toString();//statusInfo.get("APPR_STATUS").toString();
				String id_wf_approval = input.getIdWfApproval().toString();//statusInfo.get("ID_WF_APPROVAL").toString();
				String id_user = input.getUvo().getId_user();
				G_WFM_P01 gWfmP01 = new G_WFM_P01(updateDAO, queryDAO,id_ref,id_screen, 
						section_no, sequence_no, approve_status, id_wf_approval, id_user);
				gWfmP01.execute();
				
				//get status info
				param = new HashMap<String, Object>();
				param.put("ID_REF", id_ref);
				Map<String, Object> statusInfo = queryDAO.executeForMap(STATUS_INFO, param);
				
				if (statusInfo.get("ID_STATUS").equals("DS3")) {
					//get id_cust_plan list
					List<String> id_cust_plan_list = queryDAO.executeForObjectList(GET_ID_CUST_PLAN, param);
					//String newBAC = null;
					for (String id_cust_plan : id_cust_plan_list) {
						List<Map<String, Object>> custPlanInfoList = queryDAO.executeForMapList("SELECT.B_BIF.CUST_PLAN_INFO", id_cust_plan);
						Map<String, Object> custPlanInfo = custPlanInfoList.get(0);
						String idCust = CommonUtils.toString(custPlanInfo.get("ID_CUST")).trim();
						//String payMethod = CommonUtils.toString(custPlanInfo.get("PAYMENT_METHOD")).trim();
						//String billCurrency = CommonUtils.toString(custPlanInfo.get("BILL_CURRENCY")).trim();
						//String exportCurrency = CommonUtils.toString(custPlanInfo.get("EXPORT_CURRENCY")).trim();
						//String fixedForex = CommonUtils.toString(custPlanInfo.get("FIXED_FOREX")).trim();
						//String isDisplayExpAmt = CommonUtils.toString(custPlanInfo.get("IS_DISPLAY_EXP_AMT")).trim();
						
						G_CPM_P01 cpmP01 = new G_CPM_P01(this.queryDAO, this.updateDAO, 
			                    this.radiusQueryDAO, this.radiusUpdateDAO, input.getUvo().getId_user());
						//cpmP01.setNewBAC(newBAC);
						G_CPM_P01Input in = new G_CPM_P01Input();
						in.setId_cust_plan(id_cust_plan);
						//in.setBill_currency(billCurrency);
						//in.setPayment_method(payMethod);
						//in.setIs_display_exp_amt(isDisplayExpAmt);
						//in.setExport_currency(exportCurrency);
						//in.setFixed_forex(fixedForex);
						in.setId_cust(idCust);
						in.setId_bif(id_ref);
						in.setId_login(input.getUvo().getId_user());
						in.setId_audit(CommonUtils.toString(idAudit));
						for(Map<String, Object> mapParam : radiusMapList) {
                            if(id_cust_plan.equals(CommonUtils.toString(mapParam.get("idCustPlan")))) {
                                in.setAccessAccount(CommonUtils.toString(mapParam.get("accessAccount")));
                                in.setAccessPw(CommonUtils.toString(mapParam.get("accessPw")));
                                in.setRadius((Boolean)mapParam.get("isRadius"));
                                break;
                            }
                        }
						for (Map<String, Object> map : custPlanInfoList) {
						    String idCustPlanGrp = CommonUtils.toString(map.get("ID_CUST_PLAN_GRP")).trim();
						    in.setApprove_type("B");
						    in.setId_cust_plan_grp(idCustPlanGrp);
						    cpmP01.execute(in, G_CPM_P01.B_BIF_S02s);
						}
						//newBAC = cpmP01.getNewBAC();
					}
				}
				if (statusInfo.get("ID_STATUS").equals("DS4")) {
					//get id_cust_plan list
					List<String> id_cust_plan_list = queryDAO.executeForObjectList(GET_ID_CUST_PLAN, param);
					for (String id_cust_plan : id_cust_plan_list) {
						param = new HashMap<String, Object>();
						param.put("ID_CUST_PLAN", id_cust_plan);
						param.put("PLAN_STATUS", "PS8");
						param.put("ID_LOGIN", input.getUvo().getId_user());
						param.put("ID_AUDIT", idAudit);
						this.updateDAO.execute(UPDATE_CUST_PLAN_H_PS8, param);
                        this.updateDAO.execute(UPDATE_CUST_PLAN_D_PS8, param);
					}
				}
				//messages.add(Globals.MESSAGE_KEY, new BLogicMessage("infor.ERR2SC007"));
				if (input.getAction().equals(B_BIFS02Input.APPROVAL_ACTION)) {
					out.setMessageParameter(B_BIFS02Input.APPROVED_ACTION);
				}
				if (input.getAction().equals(B_BIFS02Input.REJECT_ACTION)) {
					out.setMessageParameter(B_BIFS02Input.REJECTED_ACTION);
				}
				out.setMessage("info.ERR2SC007");
			}
			CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
		} catch (Exception e) {
			messages.add(null, new BLogicMessage(e.getMessage()));
			result.setErrors(messages);
		}
		out.setAction(B_BIFS02Input.VIEW_ACTION);
		out.setIdRef(input.getIdRef());
		result.setResultObject(out);
		result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
		return result;
	}
	
	private boolean radiusCheck(String idCustPlan, B_BIFUtils util, Map<String, Object> mapParam) {
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