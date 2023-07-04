/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : Customer Plan Management B-CPM-S02 (Save)
 * SERVICE NAME   : B_CPM_S02
 * FUNCTION       : Process for save customer plan
 * FILE NAME      : B_CPM_S02SaveBLogic.java
 * 
 * Copyright c 2011 NTTDATA Corporation
 *
 * History
 * 2011/07/26 [Duong Nguyen] Created
 * 2011/10/24 [Duong Nguyen] Fix Bug #2823
 * 
**********************************************************/
package nttdm.bsys.b_cpm.blogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.exception.SystemException;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_cpm.common.B_CPM_S02Util;
import nttdm.bsys.b_cpm_en.dto.B_CPM_CONSTANT;
import nttdm.bsys.b_cpm_en.dto.B_CPM_Common;
import nttdm.bsys.b_cpm_en.dto.CustomerPlan;
import nttdm.bsys.b_cpm_en.dto.CustomerPlanService;
import nttdm.bsys.b_cpm_en.dto.CustomerSubPlan;
import nttdm.bsys.b_cpm_en.dto.CustomerSubPlanDetail;
import nttdm.bsys.common.bean.RAD_USERS_T;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_CDM_P01;
import nttdm.bsys.common.util.G_CPM_P01;
import nttdm.bsys.common.util.RadUserTUtil;
import nttdm.bsys.common.util.dto.G_CPM_P01Input;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;

/**
 * B_CPM_S02SaveBLogic.class<br>
 * <ul>
 * <li>process save customer plan</li>
 * </ul>
 * <br>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class B_CPM_S02SaveBLogic extends AbstractB_CPM_S02SaveBLogic {
	
    private BLogicMessages errors;
    
	 /**
	 * 
	 * @param param
	 * ビジネスロジチE�E�E�E�E�E�E�E��E�E�E�E�E�E�E�の実行結果、BLogicResultインスタンス、E
	 */
	public BLogicResult execute(HashMap<String, Object> param) {
				
		BLogicResult result = new BLogicResult();
		errors = new BLogicMessages();
		
		// TODO
		// QueryDAO 記述侁E
		// SampleUVO uvo = queryDAO.executeForObject("namespace.sqlID",
		// params, SampleUVO().class);
		//
		// UpdateDAO 記述侁E
		// updateDAO.execute("namespace.sqlID", params);
		//
		//save data to database
		CustomerPlan customerPlan = (CustomerPlan)param.get("customerPlan");
		BillingSystemUserValueObject uvo =  (BillingSystemUserValueObject)param.get("uvo");
		
		try {
		    B_CPM_S02Util util = new B_CPM_S02Util(this.queryDAO, this.updateDAO);
		    String planType = CommonUtils.toString(customerPlan.getPlanType());
		    
		    String billingInstruction = customerPlan.getBillingInstruction();
            //One Time
            if ("6".equals(billingInstruction)) {
                for (CustomerPlanService service : customerPlan.getServices()) {
                    String serviceDateEnd = CommonUtils.toString(service.getServiceDateEnd()).trim();
                    if (CommonUtils.isEmpty(serviceDateEnd)) {
                        errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC105", new Object[]{"Invalid service end date. One time billing instruction selected. Please enter service end date"}));
                        result.setErrors(errors);
                        Map<String, Object> returnObject = new HashMap<String, Object>();
                        returnObject.put("customerPlan", customerPlan);
                        returnObject.put("action", param.get("action"));
                        result.setResultObject(returnObject);
                        result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
                        return result;
                    }
                }
            }
		    
		    String m_jnmDisplayFlg = customerPlan.getM_jnmDisplayFlg();
            if ("1".equals(m_jnmDisplayFlg)) {
                for (CustomerPlanService service : customerPlan.getServices()) {
                    for (CustomerSubPlan subPlan : service.getSubPlans()) {
                        String jobNo = CommonUtils.toString(subPlan.getJobNo());
                        if (CommonUtils.isEmpty(jobNo)) {
                            errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC005", new Object[]{"Job No"}));
                            result.setErrors(errors);
                            Map<String, Object> returnObject = new HashMap<String, Object>();
                            returnObject.put("customerPlan", customerPlan);
                            returnObject.put("action", param.get("action"));
                            result.setResultObject(returnObject);
                            result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
                            return result;
                        }
                    }
                }
            }
		    
		    if (!mailHostingCheck(customerPlan, util)) {
		        result.setErrors(errors);
                Map<String, Object> returnObject = new HashMap<String, Object>();
                returnObject.put("customerPlan", customerPlan);
                returnObject.put("action", param.get("action"));
                result.setResultObject(returnObject);
                result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
                return result;
		    }
		    
		    if(!inputIsMulCharCheck(customerPlan, util)) {
                result.setErrors(errors);
                Map<String, Object> returnObject = new HashMap<String, Object>();
                returnObject.put("customerPlan", customerPlan);
                returnObject.put("action", param.get("action"));
                result.setResultObject(returnObject);
                result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
                return result;
            }
		    //standard plan check
		    if(B_CPM_S02Util.STANDARD_PLAN_TYPE.equals(planType)) {
		        //customer plan currency fifferent M_PLAN_H currency click yes button
	            String clickDifCurrencyYesFlg = CommonUtils.toString(customerPlan.getClickDifCurrencyYesFlg());
	            //not click yes button
	            if(CommonUtils.isEmpty(clickDifCurrencyYesFlg)) {
	                //Customer Plan Currency
	                String custPlanCurrency = customerPlan.getBillCurrency();
	                for (CustomerPlanService service : customerPlan.getServices()) {
	                    for (CustomerSubPlan subPlan : service.getSubPlans()) {
	                        //String idPlan = CommonUtils.toString(subPlan.getIdPlan());
	                        //Standard Plan Currency
	                        String standardPlanCurrency = subPlan.getCurrency();
	                        //Customer Plan Currency different from Standard Plan Currency
	                        if(!standardPlanCurrency.equals(custPlanCurrency)) {
	                            customerPlan.setCustPlanMPlanCurDifFlg("1");
	                            Map<String, Object> returnObject = new HashMap<String, Object>();
	                            returnObject.put("customerPlan", customerPlan);
	                            returnObject.put("action", param.get("action"));
	                            result.setResultObject(returnObject);
	                            result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
	                            return result;
	                        }
	                        break;
	                    }
	                }
	            }
	            customerPlan.setCustPlanMPlanCurDifFlg("");
		    }
		    
		  //add active service check
            //List<CustomerPlanService> activeOrTimAddServiceList = new ArrayList<CustomerPlanService>();
            List<String> newIdPlanGrpList = new ArrayList<String>();
            Map<String, Object> preRadiusMap = null;
            //edit flag
            if (customerPlan.getIdCustPlan() != null && !"".equals(customerPlan.getIdCustPlan())) {
                String planStatus = customerPlan.getPlanStatus();
                List<CustomerPlanService> newServiceList = new ArrayList<CustomerPlanService>();
                //not Draft and in-approval
                if(!"PS1".equals(planStatus) && !"PS2".equals(planStatus)){
                    //before update Radius infomation
                    preRadiusMap = getIsCustPlanRadiusInfo(CommonUtils.toString(customerPlan.getIdCustPlan()).trim(), util);
                }
                //not Draft and in-approval
                if(!"PS1".equals(planStatus) && !"PS2".equals(planStatus)){
                    for (CustomerPlanService service : customerPlan.getServices()) {
                        if (!service.getServiceStatus().equals("PS1")
                                &&!service.getServiceStatus().equals("PS2")
                                &&!service.getServiceStatus().equals("PS3")
                                &&!service.getServiceStatus().equals("PS7")) {
                            continue;
                        }
                        String idCustPlanGrp = CommonUtils.toString(service.getIdCustPlanGrp()).trim();
                        //Active or Terminated newly Service
                        if(!"PS1".equals(planStatus) && !"PS2".equals(planStatus) && CommonUtils.isEmpty(idCustPlanGrp)) {
                            for (CustomerSubPlan subPlan : service.getSubPlans()) {
                                newIdPlanGrpList.add(subPlan.getIdPlanGrp());
                            }
                        } else if(!"PS1".equals(planStatus) && !"PS2".equals(planStatus) && !CommonUtils.isEmpty(idCustPlanGrp)) {
                            for (CustomerSubPlan subPlan : service.getSubPlans()) {
                                String idCustPlanLink = CommonUtils.toString(subPlan.getIdCustPlanLink()).trim();
                                if (CommonUtils.isEmpty(idCustPlanLink)) {
                                    newIdPlanGrpList.add(subPlan.getIdPlanGrp());
                                }
                            }
                        }
                        //Active or Terminated newly Service
                        if(!"PS1".equals(planStatus) && !"PS2".equals(planStatus) && "PS1".equals(service.getServiceStatus())) {
                            newServiceList.add(service);
                        }
                    }
                    if (!beforeUpdateRadiusCheck(preRadiusMap)) {
                        result.setErrors(errors);
                        Map<String, Object> returnObject = new HashMap<String, Object>();
                        returnObject.put("customerPlan", customerPlan);
                        returnObject.put("action", param.get("action"));
                        result.setResultObject(returnObject);
                        result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
                        return result;
                    }
                    if(newIdPlanGrpList!=null && 0<newIdPlanGrpList.size()) {
                        if (!newServiceRadiusCheck(customerPlan.getIdCustPlan(), util, newIdPlanGrpList)) {
                            result.setErrors(errors);
                            Map<String, Object> returnObject = new HashMap<String, Object>();
                            returnObject.put("customerPlan", customerPlan);
                            returnObject.put("action", param.get("action"));
                            result.setResultObject(returnObject);
                            result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
                            return result;
                        }
                    }
                    
                    List<String> existIdPlanGrpList = new ArrayList<String>();
                    List<String> existIdCustPlanLinkList = new ArrayList<String>();
                    for (CustomerPlanService service : customerPlan.getServices()) {
                        for (CustomerSubPlan subPlan : service.getSubPlans()) {
                            String idPlanGrp = CommonUtils.toString(subPlan.getIdPlanGrp());
                            String idCustPlanLink = CommonUtils.toString(subPlan.getIdCustPlanLink());
                            if(!CommonUtils.isEmpty(idCustPlanLink)) {
                            if(!CommonUtils.isEmpty(idPlanGrp)) {
                                    existIdPlanGrpList.add(idPlanGrp);
                                }
                                existIdCustPlanLinkList.add(idCustPlanLink);
                            }
                        }
                    }
                    String clickAddRadiusYesFlg = CommonUtils.toString(customerPlan.getClickAddRadiusYesFlg());
                    String clickRemoveRadiusYesFlg = CommonUtils.toString(customerPlan.getClickRemoveRadiusYesFlg());
                    
                    if(CommonUtils.isEmpty(clickAddRadiusYesFlg) && CommonUtils.isEmpty(clickRemoveRadiusYesFlg)) {
                            boolean isRadius = false;
                        if(newIdPlanGrpList!=null && 0<newIdPlanGrpList.size()) {
                            // not Terminated or add new Service
                            if(!("PS7".equals(planStatus) && newServiceList.size()==0)) {
                                List<Map<String, Object>> newIdPlanGrpMapPlanBatchMapping = util.getPlanBatchMappingByIdPlanGrp(newIdPlanGrpList);
                                if(newIdPlanGrpMapPlanBatchMapping!=null && 0<newIdPlanGrpMapPlanBatchMapping.size()) {
                                    isRadius = true;
                                }
                            }
                        }
                        if(isRadius==false) {
                            if((existIdPlanGrpList!=null && 0<existIdPlanGrpList.size()) && (existIdCustPlanLinkList!=null && 0<existIdCustPlanLinkList.size())) {
                                List<Map<String, Object>> mapPlanBatchMapping = util.getPlanBatchMappingByExistIdPlanGrp(existIdPlanGrpList, existIdCustPlanLinkList, customerPlan.getIdCustPlan());
                                if(mapPlanBatchMapping!=null && 0<mapPlanBatchMapping.size()) {
                                    isRadius = true;
                                }
                            }
                        }
                        boolean preIsRadisu = (Boolean)preRadiusMap.get("isRadius");
                            //add radius service
                            if(!preIsRadisu && isRadius) {
                                customerPlan.setAddRadiusFlg("1");
                                Map<String, Object> returnObject = new HashMap<String, Object>();
                                returnObject.put("customerPlan", customerPlan);
                                returnObject.put("action", param.get("action"));
                                result.setResultObject(returnObject);
                                result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
                                return result;
                            }
                            //remove radius service
                            if(preIsRadisu && !isRadius) {
                                customerPlan.setRemoveRadiusFlg("1");
                                Map<String, Object> returnObject = new HashMap<String, Object>();
                                returnObject.put("customerPlan", customerPlan);
                                returnObject.put("action", param.get("action"));
                                result.setResultObject(returnObject);
                                result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
                                return result;
                            }
                        }
                    customerPlan.setAddRadiusFlg("");
                    customerPlan.setRemoveRadiusFlg("");
                }
            }
            
		    //get new id_plan
            String idCustPlan = "";
            String idSubInfo = "";
		    if (CommonUtils.isEmpty(CommonUtils.toString(customerPlan.getIdCustPlan()).trim())) {
		        //idCustPlan = util.getNewIdCustPlanH();
		        //insert new subscription info
                G_CDM_P01 gCDMP01 = new G_CDM_P01(this.queryDAO, this.updateDAO, uvo.getId_user());
                try {
                    idSubInfo = gCDMP01.getGenerateCode(B_CPM_CONSTANT.SUBSCRIPTION_CODE, uvo.getId_user());
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    throw new SystemException(e);
                }
                idCustPlan = CommonUtils.toString(new Long(idSubInfo));
		    } else {
		        idCustPlan = CommonUtils.toString(customerPlan.getIdCustPlan()).trim();
		    }
		    customerPlan = B_CPM_Common.restructureCustomerPlan(customerPlan, uvo, idCustPlan);

			if (customerPlan.getIdCustPlan() != null && !customerPlan.getIdCustPlan().equals("")) {
				//check plan and batch mapping
				Map<String, Object> errorMessage = this.checkPlanAnfBatchMapping(customerPlan, util);
				if (errorMessage == null) {
				    String planStatus = customerPlan.getPlanStatus();
				    if ("PS1".equals(planStatus)) {
				        String idBillAcc = CommonUtils.toString(customerPlan.getBillAccNo());
	                    if ("NEW".equalsIgnoreCase(idBillAcc)) {
	                        // generate code for billing account, only for NEW plan
	                        G_CDM_P01 gCdmP01 = new G_CDM_P01(this.queryDAO, this.updateDAO, customerPlan.getIdLogin());
	                        String newBillAcc = gCdmP01.getGenerateCode("BACNO", customerPlan.getIdLogin());
	                        customerPlan.setBillAccNo(newBillAcc);
	                        Map<String, Object> mapBAC_H = setBAC_H_Info(customerPlan, util);
	                        //insert T_BAC_H
	                        util.insertBAC_H(mapBAC_H);
	                    } 
	                    //else {
	                    //    int bacDCount = util.getBAC_D_CountByIdBacNo(idBillAcc);
	                    //    if(bacDCount<=0) {
	                    //        Map<String, Object> mapBAC_H = setBAC_H_Info(customerPlan, util);
	                    //        util.updateBAC_H(mapBAC_H);
	                    //    }
	                    //}
				    }
				    
					//update customer plan
					util.updateCustomerPlanH(customerPlan);
					List<String> existIdList = new ArrayList<String>();
					List<CustomerPlanService> activeOrTimAddServiceList = new ArrayList<CustomerPlanService>();
					//update service
					for (CustomerPlanService service : customerPlan.getServices()) {
						if (!service.getServiceStatus().equals("PS1")
						        &&!service.getServiceStatus().equals("PS2")
                                &&!service.getServiceStatus().equals("PS3")
                                &&!service.getServiceStatus().equals("PS7")) {
							existIdList.add(service.getIdCustPlanGrp());
							continue;
						}
						service.setIdCustPlan(customerPlan.getIdCustPlan());
						if (service.getIdCustPlanGrp() != null && !service.getIdCustPlanGrp().equals("")) {
							//update service
							util.updateCustomerPlanD(service);
						} else {
							//new service
							String idCustPlanGrp = util.getNewIdCustPlanD();
							service.setIdCustPlanGrp(idCustPlanGrp);
							util.insertCustomerPlanD(service);
						}
						existIdList.add(service.getIdCustPlanGrp());
						//update sub plan
						List<String> subPlanExistIdList = new ArrayList<String>();
						
						//update sub plan and option service
						for (CustomerSubPlan subPlan : service.getSubPlans()) {
							subPlan.setIdCustPlanGrp(service.getIdCustPlanGrp());
							subPlan.setCustPo(service.getCustPo());
							String isDisplayJobNo = CommonUtils.toString(subPlan.getIsDisplayJobNo());
                            if (CommonUtils.isEmpty(isDisplayJobNo)) {
                                isDisplayJobNo = "0";
                            }
                            // Set 'DISC_AMOUNT' as Absolute value
                            float discamountabs=Math.abs(Float.parseFloat(subPlan.getDiscamount()));
                            subPlan.setDiscamount("-"+discamountabs);
                            subPlan.setIsDisplayJobNo(isDisplayJobNo);
                            
                            // Set 'Location' #200, #201 wcbeh@20160921 - Master Location
                            if(CommonUtils.isEmpty(subPlan.getSubLocation())){
                            	subPlan.setSubLocation(service.getMasterLocation());
                            }
                            
							if (subPlan.getIdCustPlanLink() != null && !subPlan.getIdCustPlanLink().equals("")) {
								if(util.hasIdCustPlanLink(subPlan)){
									//update sub plan
									util.updateCustomerPlanLink(subPlan);
								} else {
									String idCustPlanLink = util.getNewIdCustPlanLink();
									subPlan.setIdCustPlanLink(idCustPlanLink);
									
									//insert sub plan
									util.insertCustomerPlanLink(subPlan);
								}

							} else {
								String idCustPlanLink = util.getNewIdCustPlanLink();
								subPlan.setIdCustPlanLink(idCustPlanLink);
								
								//insert sub plan
								util.insertCustomerPlanLink(subPlan);
							}
							subPlanExistIdList.add(subPlan.getIdCustPlanLink());
							
							List<String> subPlanDetailExistIdList = new ArrayList<String>();
							
							//update sub plan detail
							for (CustomerSubPlanDetail subPlanDetail : subPlan.getSubPlanDetails()) {
								subPlanDetail.setIdCustPlanLink(subPlan.getIdCustPlanLink());
								if (util.hasIdCustPlanSvc(subPlan.getIdCustPlanLink())) {
									//update sub plan detail
									util.updateCustomerPlanSVC(subPlanDetail);
								} else {
									String idCustPlanSvc = util.getNewIdCustPlanSvc();
									subPlanDetail.setIdCustPlanSvc(idCustPlanSvc);
									//insert sub plan detail
									util.insertCustomerPlanSVC2(subPlanDetail);
								}
								subPlanDetailExistIdList.add(subPlanDetail.getIdCustPlanSvc());
							}
							//delete sub plan detail
							util.deleteCustomerPlanSVC(subPlanDetailExistIdList, subPlan.getIdAudit(), subPlan.getIdCustPlanLink());
						}
						//delete not exist sub plan
						util.deleteCustomerPlanLink(subPlanExistIdList, service.getIdAudit(), service.getIdCustPlanGrp());
						util.deleteCustomerPlanLinkSVC(subPlanExistIdList, service.getIdAudit(), service.getIdCustPlanGrp());
						//Active or Terminated newly Service
						if(!"PS1".equals(planStatus) && !"PS2".equals(planStatus) && "PS1".equals(service.getServiceStatus())) {
						    activeOrTimAddServiceList.add(service);
						}
					}
					//delete not exist service
					util.deleteCustomerPlanD(existIdList, customerPlan.getIdAudit(), customerPlan.getIdCustPlan());
					util.deleteCustomerPlanDLink(existIdList, customerPlan.getIdAudit(), customerPlan.getIdCustPlan());
					util.deleteCustomerPlanDSVC(existIdList, customerPlan.getIdAudit(), customerPlan.getIdCustPlan());
					
					util.deleteT_BAC_D(existIdList, customerPlan.getIdAudit(), customerPlan.getIdCustPlan());
					
					//not Draft and in-approval
					if(!"PS1".equals(planStatus) && !"PS2".equals(planStatus)){
					    //before update radius flag
	                    boolean preIsRadisu = (Boolean)preRadiusMap.get("isRadius");
	                    String preAccessAccount = CommonUtils.toString(preRadiusMap.get("accessAccount"));
	                    
	                    Map<String, Object> radiusMap = getIsCustPlanRadiusInfo(idCustPlan, util);
	                    boolean isRadisu = (Boolean)radiusMap.get("isRadius");
	                    String accessAccount = CommonUtils.toString(radiusMap.get("accessAccount"));
	                    String accessPw = CommonUtils.toString(radiusMap.get("accessPw"));
    					//Approvel the newly Service
    					if (0<activeOrTimAddServiceList.size()) {
	                        
    					    G_CPM_P01 gCpmP01 = new G_CPM_P01(this.queryDAO, this.updateDAO, 
    				                this.radiusQueryDAO, this.radiusUpdateDAO, uvo.getId_user());
    					    //initialize input for G-CPM-P01
    				        G_CPM_P01Input input = new G_CPM_P01Input();
    //	                      input.setBill_currency(customerPlan.getBillCurrency());
    //	                      input.setPayment_method(customerPlan.getPaymentMethod());
    //	                      input.setIs_display_exp_amt("0");
    //	                      String exportCurrency = CommonUtils.toString(customerPlan.getExpGrandTotal()).trim();
    //	                      if ("-".equals(exportCurrency)) {
    //	                          input.setExport_currency("");
    //	                      } else {
    //	                          input.setExport_currency(exportCurrency);
    //	                      }
    //	                      input.setExport_currency(exportCurrency);
    //	                      input.setFixed_forex(customerPlan.getFixedForex());
    				        input.setId_cust(customerPlan.getIdCust());
    				        input.setId_cust_plan(customerPlan.getIdCustPlan());
    				        input.setId_login(uvo.getId_user());
    				        input.setId_audit(customerPlan.getIdAudit());
    				        
	                        input.setAccessAccount(accessAccount);
	                        input.setAccessPw(accessPw);
	                        if (preIsRadisu && isRadisu) {
	                            input.setRadius(false);
	                        } else {
	                            input.setRadius(isRadisu);
	                        }
	                        input.setUpadteT_BAC_H(false);
    	                        
	                        List<String> newIdCustPlanGrpList = new ArrayList<String>();
    					    for(CustomerPlanService newlyService : activeOrTimAddServiceList) {
    					        input.setApprove_type("S");
    				            input.setId_cust_plan_grp(newlyService.getIdCustPlanGrp());
    				            gCpmP01.execute(input, G_CPM_P01.B_CPM_S02v);
	                            
	                            newIdCustPlanGrpList.add(newlyService.getIdCustPlanGrp());
	                        }
	                        
	                        //previous not radius customer plan , exist service add radius subplan
                            if(preIsRadisu==false && isRadisu==true) {
                                RadUserTUtil radUserTUtil = new RadUserTUtil(radiusQueryDAO, radiusUpdateDAO);
                                if(!radUserTUtil.isExistUserName(accessAccount)) {
                                    RAD_USERS_T radUserT = new RAD_USERS_T();
                                    radUserT.setUserName(accessAccount);
                                    radUserT.setPassword(accessPw);
                                    radUserT.setExpireDate("1970-01-01 09:00:00");
                                    radUserT.setCreditTime("-1");
                                    radUserT.setStatus("0");
                                    radUserT.setPlanId("0");
                                    radUserT.setAuthMethod("0");
                                    radUserT.setAttrbuteRule("0");
                                    radUserT.setModifyCounter("0");
                                    radUserTUtil.insert(radUserT);
    	                    }
                            }
	                        
	                        Map<String, Object> sqlParam = new HashMap<String, Object>();
	                        sqlParam.put("idAudit", customerPlan.getIdAudit());
	                        sqlParam.put("status", "PS3");
	                        util.updateMAuditTrailH(sqlParam);
	                        
	                        sqlParam = new HashMap<String, Object>();
	                        sqlParam.put("idAudit", customerPlan.getIdAudit());
	                        sqlParam.put("tableName", "T_CUST_PLAN_D");
	                        sqlParam.put("atField", "SERVICES_STATUS");
	                        sqlParam.put("newData", "PS3");
	                        util.updateMAuditTrailD(sqlParam);
	                        
	                        if("PS7".equals(planStatus)) {
	                            sqlParam = new HashMap<String, Object>();
	                            sqlParam.put("idAudit", customerPlan.getIdAudit());
	                            sqlParam.put("tableName", "T_CUST_PLAN_H");
	                            sqlParam.put("atField", "PLAN_STATUS");
	                            sqlParam.put("oldData", "PS7");
	                            sqlParam.put("newData", "PS3");
	                            util.insertMAuditTrailD(sqlParam);
	                        }
	                    } else {
	                        //previous not radius customer plan , exist service add radius subplan
	                        if(preIsRadisu==false && isRadisu==true) {
	                            RadUserTUtil radUserTUtil = new RadUserTUtil(radiusQueryDAO, radiusUpdateDAO);
	                            RAD_USERS_T radUserT = new RAD_USERS_T();
	                            radUserT.setUserName(accessAccount);
	                            radUserT.setPassword(accessPw);
	                            radUserT.setExpireDate("1970-01-01 09:00:00");
	                            radUserT.setCreditTime("-1");
	                            radUserT.setStatus("0");
	                            radUserT.setPlanId("0");
	                            radUserT.setAuthMethod("0");
	                            radUserT.setAttrbuteRule("0");
	                            radUserT.setModifyCounter("0");
	                            radUserTUtil.insert(radUserT);
	                        }
	                    }
	                    //is should remove radius
	                    isRemoveRadius(preIsRadisu, isRadisu, preAccessAccount);
					}
				} else {
					//show error message
					if(errorMessage.get("ERROR_KEY").equals("errors.ERR1SC022")){
						errors.add(Globals.MESSAGE_KEY,new BLogicMessage(errorMessage.get("ERROR_KEY").toString(), errorMessage.get("ARG0"),errorMessage.get("ARG1")));
					} else {
						errors.add(Globals.MESSAGE_KEY,new BLogicMessage(errorMessage.get("ERROR_KEY").toString(), errorMessage.get("PARAM").toString()));
					}
					result.setErrors(errors);
					result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
					return result;
				} //show error message for check plan and batch mapping
			} else {
				customerPlan.setIdCustPlan(idCustPlan);
				
				//check plan and batch mapping
				Map<String, Object> errorMessage = this.checkPlanAnfBatchMapping(customerPlan, util);
				if (errorMessage == null) {
				    String idBillAcc = CommonUtils.toString(customerPlan.getBillAccNo());
				    if ("NEW".equalsIgnoreCase(idBillAcc)) {
				        // generate code for billing account, only for NEW plan
		                G_CDM_P01 gCdmP01 = new G_CDM_P01(this.queryDAO, this.updateDAO, customerPlan.getIdLogin());
		                String newBillAcc = gCdmP01.getGenerateCode("BACNO", customerPlan.getIdLogin());
		                customerPlan.setBillAccNo(newBillAcc);
		                Map<String, Object> mapBAC_H = setBAC_H_Info(customerPlan, util);
	                    //insert T_BAC_H
	                    util.insertBAC_H(mapBAC_H);
				    }
				    
					//insert customer plan
					util.insertCustomerPlanH(customerPlan);
					
					customerPlan.setIdSubInfo(idSubInfo);
					util.insertSubscriptionInfo(customerPlan);
					
					//insert service list
					for (CustomerPlanService service : customerPlan.getServices()) {
						//get new service id
						String idCustPlanGrp = util.getNewIdCustPlanD();
						service.setIdCustPlanGrp(idCustPlanGrp);
						service.setIdCustPlan(customerPlan.getIdCustPlan());
						if(CommonUtils.isEmpty(service.getMasterLocation()) && !CommonUtils.isEmpty(customerPlan.getMasterLocation()))
							service.setMasterLocation(customerPlan.getMasterLocation());
						util.insertCustomerPlanD(service);
						
						//insert sub plan and option service
						for (CustomerSubPlan subPlan : service.getSubPlans()) {
							//get new sub plan id
							String idCustPlanLink = util.getNewIdCustPlanLink();
							subPlan.setIdCustPlanLink(idCustPlanLink);
							subPlan.setIdCustPlanGrp(service.getIdCustPlanGrp());
							//custPo
							subPlan.setCustPo(service.getCustPo());
							String isDisplayJobNo = CommonUtils.toString(subPlan.getIsDisplayJobNo());
                            if (CommonUtils.isEmpty(isDisplayJobNo)) {
                                isDisplayJobNo = "0";
                            }
                            subPlan.setIsDisplayJobNo(isDisplayJobNo);
                            // Set 'DISC_AMOUNT' as Absolute value
                            float discamountabs=Math.abs(Float.parseFloat(subPlan.getDiscamount()));
                            subPlan.setDiscamount("-"+discamountabs);
                            
                            // Set 'Location' #200, #201 wcbeh@20161121 - Master Location
                            if(CommonUtils.isEmpty(subPlan.getSubLocation())){
                            	subPlan.setSubLocation(service.getMasterLocation());
                            }
                            
							util.insertCustomerPlanLink(subPlan);
							
							//insert sub plan detail
							for (CustomerSubPlanDetail subPlanDetail : subPlan.getSubPlanDetails()) {
								subPlanDetail.setIdCustPlanLink(idCustPlanLink);
								util.insertCustomerPlanSVC(subPlanDetail);
							}
						}
					}
				} else {
					//show error message
					if(errorMessage.get("ERROR_KEY").equals("errors.ERR1SC022")){
						errors.add(Globals.MESSAGE_KEY,new BLogicMessage(errorMessage.get("ERROR_KEY").toString(), errorMessage.get("ARG0"),errorMessage.get("ARG1")));
					} else {
						errors.add(Globals.MESSAGE_KEY,new BLogicMessage(errorMessage.get("ERROR_KEY").toString(), errorMessage.get("PARAM").toString()));
					}
					result.setErrors(errors);
					result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
					return result;
				} //show error message for check plan and batch mapping
			}
			
			Map<String, Object> resultObject = new HashMap<String, Object>();
			resultObject.put("customerPlan", customerPlan);
			
			result.setResultObject(resultObject);
			
			//setting message result
			BLogicMessages paramMessages= new BLogicMessages();
			paramMessages.add(Globals.MESSAGE_KEY,new BLogicMessage(B_CPM_CONSTANT.SAVE_SUCCESS_MSG));
			result.setMessages(paramMessages);
			
			//forward to view screen
			result.setResultString("success");
			return result;
		} catch(Exception ex){
			ex.printStackTrace();
			errors.add(Globals.MESSAGE_KEY,new BLogicMessage(B_CPM_CONSTANT.SAVE_ERROR_MSG));
			result.setErrors(errors);
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
			return result;
		}
	}
	
	/**
	 * 
	 * @param customerPlan
	 * @return
	 */
	private Map<String, Object> checkPlanAnfBatchMapping(CustomerPlan customerPlan, B_CPM_S02Util util) {
		Map<String, Object> result = null;
		Map<String, Object> param = new HashMap<String, Object>();
		//get list id plan and id plan group
		List<String> idPlanList = new ArrayList<String>();
		idPlanList.add("0");
		List<String> idPlanGrpList = new ArrayList<String>();
		idPlanGrpList.add("0");
		
		for (CustomerPlanService service : customerPlan.getServices()) {
			for (CustomerSubPlan subPlan : service.getSubPlans()) {
				idPlanList.add(subPlan.getIdPlan());
				idPlanGrpList.add(subPlan.getIdPlanGrp());
			}
		}
		
		param.put("idPlanList", idPlanList.toArray());
		param.put("idPlanGrpList", idPlanGrpList.toArray());
		List<Map<String, Object>> itemList = util.getPlanBatchMapping(param);
		if (itemList.size() > 0) {
			int noCC = 0;// the number of service of Clear call
			for (Map<String, Object> item : itemList) {
				String batchName = "";
				if (item.get("BATCH_ID").equals("CC")) {
					noCC++;
					batchName = "Clear Call";
				} else {
				    continue;
				}
//				else if (item.get("BATCH_ID").equals("DU")) {
//					batchName = "Dial-Up";
//				} else if (item.get("BATCH_ID").equals("AD")) {
//					batchName = "ADSL";
//				} else {
//					batchName = "Ipass";
//				}
				int n = countItemListByName(itemList, "ID_PLAN_GRP", item.get("ID_PLAN_GRP"), "S", null);
				if (n == 1) {
					if (item.get("BATCH_ID").equals("DU") || item.get("BATCH_ID").equals("AD")) {
						int a = countItemListByName(itemList, "ID_PLAN_GRP", item.get("ID_PLAN_GRP"), "O", null);
						if (a != 1) {
							result = new HashMap<String, Object>();
							result.put("PARAM", batchName);
							result.put("ERROR_KEY", "errors.ERR1SC044");
						}
					} else if (item.get("BATCH_ID").equals("IP")) {
						int a = countItemListByName(itemList, "ID_PLAN_GRP", item.get("ID_PLAN_GRP"), "O", "1");
						int b = countItemListByName(itemList, "ID_PLAN_GRP", item.get("ID_PLAN_GRP"), "O", "2");
						int c = countItemListByName(itemList, "ID_PLAN_GRP", item.get("ID_PLAN_GRP"), "O", "3");
						if (a != 1 || b != 1 || c != 1) {
							result = new HashMap<String, Object>();
							result.put("PARAM", batchName);
							result.put("ERROR_KEY", "errors.ERR1SC044");
						}
					}
				} else {
					result = new HashMap<String, Object>();
					result.put("PARAM", batchName);
					result.put("ERROR_KEY", "errors.ERR1SC043");
				}
			}
			if(noCC >= 2){
				result = new HashMap<String, Object>();
				result.put("ARG0", "only one Clear call service to be");
				result.put("ARG1", "one subplan group");
				result.put("ERROR_KEY", "errors.ERR1SC022");
			}
		}
		return result;
	}
	
	private Map<String, Object> setBAC_H_Info(CustomerPlan customerPlan, B_CPM_S02Util util) {
	    String exportCurrency = CommonUtils.toString(customerPlan.getExpGrandTotal()).trim();
        if ("-".equals(exportCurrency)) {
            exportCurrency = "";
        }
        String customerType = util.getCustomerType(customerPlan.getIdCust());
        String contactType = "";
        if("CORP".equals(customerType)){
            List<Map<String, Object>> custAtcInfoList = util.getCustAtcInfo(customerPlan.getIdCust());
            if (0 < custAtcInfoList.size()) {
                contactType = "BC";
            } else {
                contactType = "PC";
            }
        } else {
            contactType = "  ";
        }
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("id_bill_account", customerPlan.getBillAccNo());
	    map.put("id_cust", customerPlan.getIdCust());
	    map.put("payment_method", customerPlan.getPaymentMethod());
	    map.put("bill_currency", customerPlan.getBillCurrency());
	    map.put("is_display_exp_amt", "0");
	    map.put("export_currency", exportCurrency);
	    map.put("cust_adr_type", "BA");
	    map.put("contact_type", contactType);
	    map.put("id_login", customerPlan.getIdLogin());
	    map.put("fixed_forex", customerPlan.getFixedForex());
	    map.put("idAudit", customerPlan.getIdAudit());
	    map.put("tax_type", customerPlan.getTaxType());
	    return map;
	}
	
	/**
	 * 
	 * @param itemList
	 * @param name
	 * @return
	 */
	private int countItemListByName(List<Map<String, Object>> itemList, String name, Object value, String itemType, String codeValue) {
		int count = 0;
		for (Map<String, Object> item : itemList) {
			if (item.get(name).equals(value) && item.get("ITEM_TYPE").equals(itemType)) {
				if (codeValue != null) {
					if (item.get("CODE_VALUE").equals(codeValue)) {
						
					}
				} else {
					count++;
				}
			}
		}
		return count;
	}
	
	private boolean beforeUpdateRadiusCheck(Map<String, Object> preMapRadius) {
	    boolean preIsRadisu = (Boolean)preMapRadius.get("isRadius");
	    RadUserTUtil radUserTUtil = new RadUserTUtil(radiusQueryDAO, radiusUpdateDAO);
	    String accessAccount = CommonUtils.toString(preMapRadius.get("accessAccount"));
	    //select by PK
        RAD_USERS_T radUserT = null;
	    if (!CommonUtils.isEmpty(accessAccount)) {
	        radUserT = radUserTUtil.selectByPK(accessAccount);
	    }
	    //before update is Radius
        if (preIsRadisu) {
            //radius data not found
            if(radUserT==null) {
                errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC106", new Object[] {}));
                return false;
            }
        } else {
            if (radUserT != null) {
                errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC067", new Object[] {}));
                return false;
            }
        }
        return true;
	}
	
	private boolean newServiceRadiusCheck(String idCustPlan, B_CPM_S02Util util, List<String> idPlanGrpList) {
        boolean result = true;
        List<Map<String, Object>> mapPlanBatchMapping = util.getPlanBatchMappingByIdPlanGrp(idPlanGrpList);
        if(mapPlanBatchMapping != null && 0 < mapPlanBatchMapping.size()){
            Map<String, Object> subScriptionInfo = util.getSubScriptionInfoByIdCustPlan(idCustPlan);
            //ACCESS_ACCOUNT
            String accessAccount = "";
            
            if (subScriptionInfo != null) {
                //ACCESS_ACCOUNT
                accessAccount = CommonUtils.toString(subScriptionInfo.get("ACCESS_ACCOUNT"));
                if (CommonUtils.isEmpty(accessAccount)) {
                    errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC105", new Object[] {"Access Account and/or Access Password for Subscription Information does not exist. Please create before proceed to save."}));
                    return false;
                }
            } else {
                errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC105", new Object[] {"Access Account and/or Access Password for Subscription Information does not exist. Please create before proceed to save."}));
                return false;
            }
        }
        return result;
    }
	
	private Map<String, Object> getIsCustPlanRadiusInfo(String idCustPlan, B_CPM_S02Util util) {
        Map<String, Object> mapParam = new HashMap<String, Object>();
        List<Map<String, Object>> mapPlanBatchMapping = util.getPlanBatchMappingByIdCustPlanActiveService(idCustPlan);
        if(mapPlanBatchMapping != null && 0 < mapPlanBatchMapping.size()){
            Map<String, Object> subScriptionInfo = util.getSubScriptionInfoByIdCustPlan(idCustPlan);
            //ACCESS_ACCOUNT
            String accessAccount = "";
            //ACCESS_PW
            String accessPw = "";
            
            if (subScriptionInfo != null) {
                //ACCESS_ACCOUNT
                accessAccount = CommonUtils.toString(subScriptionInfo.get("ACCESS_ACCOUNT"));
                //ACCESS_PW
                accessPw = CommonUtils.toString(subScriptionInfo.get("ACCESS_PW"));
            }
            mapParam.put("accessAccount", accessAccount);
            mapParam.put("accessPw", accessPw);
            mapParam.put("isRadius", true);
        } else {
            Map<String, Object> subScriptionInfo = util.getSubScriptionInfoByIdCustPlan(idCustPlan);
            //ACCESS_ACCOUNT
            String accessAccount = "";
            //ACCESS_PW
            String accessPw = "";
            
            if (subScriptionInfo != null) {
                //ACCESS_ACCOUNT
                accessAccount = CommonUtils.toString(subScriptionInfo.get("ACCESS_ACCOUNT"));
                //ACCESS_PW
                accessPw = CommonUtils.toString(subScriptionInfo.get("ACCESS_PW"));
            }
            mapParam.put("accessAccount", accessAccount);
            mapParam.put("accessPw", accessPw);
            mapParam.put("isRadius", false);
        }
        return mapParam;
    }
	
	private void isRemoveRadius(boolean preIsRadius, boolean isRadius, String accessAccount) {
	    //before upadte is radius ,updated is not radius
	    if(preIsRadius==true && isRadius==false) {
	        if (!CommonUtils.isEmpty(accessAccount)) {
	            RadUserTUtil radUserTUtil = new RadUserTUtil(radiusQueryDAO, radiusUpdateDAO);
	            //select by PK
	            RAD_USERS_T radUserT = radUserTUtil.selectByPK(accessAccount);
	            if (radUserT != null) {
	                radUserTUtil.deleteByUserName(accessAccount);
	            }
	        }
	    }
	}
	
	private boolean mailHostingCheck(CustomerPlan customerPlan, B_CPM_S02Util util) {
	    List<String> subPlan_idPlanGrpList = new ArrayList<String>();
	    List<String> optionService_idPlanGrpList = new ArrayList<String>();
	    List<String> idPlanList = new ArrayList<String>();
	    for(CustomerPlanService service: customerPlan.getServices()){
	        for(CustomerSubPlan subPlan : service.getSubPlans()) {
	            //'S' is subplan, 'O' is option service
	            String itemType = CommonUtils.toString(subPlan.getItemType());
	            String idPlan = CommonUtils.toString(subPlan.getIdPlan());
	            String idPlanGrp = CommonUtils.toString(subPlan.getIdPlanGrp());
	            if (!CommonUtils.isEmpty(idPlan) && !idPlanList.contains(idPlan)) {
	                idPlanList.add(idPlan);
	            }
	            if (!CommonUtils.isEmpty(idPlanGrp)) {
	                if ("S".equals(itemType)) {
	                    subPlan_idPlanGrpList.add(idPlanGrp);
	                } else if ("O".equals(itemType)) {
	                    optionService_idPlanGrpList.add(idPlanGrp);
                    }
	            }
	        }
	    }
	    if(subPlan_idPlanGrpList!=null && 0<subPlan_idPlanGrpList.size()) {
	        boolean isSubPlanMailHosting = util.isSubPlanMailHosting(subPlan_idPlanGrpList);
	        if (isSubPlanMailHosting) {
	            boolean amaErrorFlg = false;
	            boolean amqErrorFlg = false;
	            boolean vrsErrorFlg = false;
	            boolean aspErrorFlg = false;
	            boolean jmgErrorFlg = false;
	            for(String idPlan : idPlanList) {
	                List<Map<String, Object>> optionServiceList = util.getOptionServiceMailHosting(idPlan);
	                if (optionServiceList!=null && 0<optionServiceList.size()) {
	                    for(Map<String, Object> map : optionServiceList) {
	                        String idPlanGrp = CommonUtils.toString(map.get("ID_PLAN_GRP"));
	                        String uomType = CommonUtils.toString(map.get("UOM"));
	                        if(!optionService_idPlanGrpList.contains(idPlanGrp)) {
	                            if("AMA".equals(uomType)) {
	                                if (!amaErrorFlg) {
	                                    amaErrorFlg = true;
	                                    errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC105", new Object[] {"Additional mail account option is not selected. Please assign Additional mail account option service."}));
	                                }
	                            } else if("AMQ".equals(uomType)) {
	                                if (!amqErrorFlg) {
                                        amqErrorFlg = true;
                                        errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC105", new Object[] {"Additional mailbox (qty) option is not selected. Please assign Additional mailbox (qty) option service."}));
                                    }
	                            } else if("VRS".equals(uomType)) {
	                                if (!vrsErrorFlg) {
	                                    vrsErrorFlg = true;
                                        errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC105", new Object[] {"Virus scan option is not selected. Please assign Virus scan option service."}));
                                    }
	                            } else if("ASP".equals(uomType)) {
	                                if (!aspErrorFlg) {
	                                    aspErrorFlg = true;
                                        errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC105", new Object[] {"Anti Spam option is not selected. Please assign Anti Spam option service."}));
                                    }
	                            } else if("JMG".equals(uomType)) {
	                                if (!jmgErrorFlg) {
	                                    jmgErrorFlg = true;
                                        errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC105", new Object[] {"Junk Management option is not selected. Please assign Junk Management option service."}));
                                    }
	                            }
	                        }
	                    }
	                }
	            }
	            
	            if(amaErrorFlg || amqErrorFlg || vrsErrorFlg || aspErrorFlg || jmgErrorFlg){
	                return false;
	            }
	        }
	    }
	    
	    return true;
	}
	
	private boolean inputIsMulCharCheck(CustomerPlan customerPlan, B_CPM_S02Util util) {
        boolean isMulCharFlg = true;
        String isCheckMulCharFlg = util.getIsCheckMulCharFlg();
        if ("1".equals(isCheckMulCharFlg)) {
            boolean serviceFlag = true;
            boolean subPlanFlag = true;
            List<CustomerPlanService> serviceList = customerPlan.getServices();
            if (serviceList!=null && 0<serviceList.size()) {
                for (CustomerPlanService service : serviceList) {
                    if (serviceFlag) {
                        String billDesc = CommonUtils.toString(service.getBillDesc());
                        if (CommonUtils.isMulChar(billDesc)) {
                            isMulCharFlg = false;
                            serviceFlag = false;
                            errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104", new Object[]{"Billing Description", CommonUtils.MUL_CHAR_STR}));
                        }
                    }
                    if (subPlanFlag) {
                        List<CustomerSubPlan> subPlanList = service.getSubPlans();
                        if (subPlanList!=null && 0<subPlanList.size()) {
                            for (CustomerSubPlan subPlan : subPlanList) {
                                String itemDesc = CommonUtils.toString(subPlan.getItemDesc());
                                if (CommonUtils.isMulChar(itemDesc)) {
                                    isMulCharFlg = false;
                                    subPlanFlag = false;
                                    errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104", new Object[]{"Item Description", CommonUtils.MUL_CHAR_STR}));
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return isMulCharFlg;
    }
}