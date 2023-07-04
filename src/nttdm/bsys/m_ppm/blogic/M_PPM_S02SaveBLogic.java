/**
 * Billing System
 * 
 * SUBSYSTEM NAME : Standard Price and Plan Maintenance
 * SERVICE NAME : M_PPM_S02
 * FUNCTION : SaveBLogic
 * FILE NAME : M_PPM_S02SaveBLogic.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 * History
 * 2011/10/21 [Duoc Nguyen] update to M_PPM_P1-11_r4_20111014.xlsx
 */

package nttdm.bsys.m_ppm.blogic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.bean.AuditTrailHeaderBean;
import nttdm.bsys.common.dao.UpdateDAOiBatisNuked;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_ppm.dto.M_PPM_S02SaveInput;
import nttdm.bsys.m_ppm.dto.M_PPM_S02SaveOutput;
import nttdm.bsys.m_ppm.dto.Plan;
import nttdm.bsys.m_ppm.dto.PlanService;
import nttdm.bsys.m_ppm.dto.PlanServiceDetail;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessages;

/**
 * Save BLogic<br/>
 * Save <b>new</b> plan or <b>update</b> plan<br/>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class M_PPM_S02SaveBLogic implements BLogic<M_PPM_S02SaveInput> {
	
	private QueryDAO queryDAO;

	private UpdateDAO updateDAO;
	
	private UpdateDAOiBatisNuked updateDAONuked;

	private static final String SAVE_SUCCESS_MSG = "info.ERR2SC003";
	
	private static final String SAVE_ERROR_MSG = "info.ERR2SC004";
	
	private BLogicMessages errors;

	public BLogicResult execute(M_PPM_S02SaveInput input) {
		BLogicResult result = new BLogicResult();
		errors = new BLogicMessages();
		
		try {
			Plan plan = input.getPlan();
			Integer idPlan = plan.getIdPlan();
			
			M_PPM_S02SaveOutput output = new M_PPM_S02SaveOutput();
			
			if(!inputIsMulCharCheck(plan)) {
			    output.setPlan(plan);
			    result.setErrors(errors);
			    result.setResultObject(output);
	            result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
	            return result;
			}
			/**
			 * Audit Trail
			 */
			Integer idAudit = CommonUtils.auditTrailBegin(input.getUvo()
					.getId_user(), BillingSystemConstants.MODULE_M,
					BillingSystemConstants.SUB_MODULE_M_PPM, null, null,
					idPlan == null || idPlan.intValue() == 0 ? BillingSystemConstants.AUDIT_TRAIL_CREATED : BillingSystemConstants.AUDIT_TRAIL_EDITED);
			plan.setIdAudit(idAudit);
			plan.setIdLogin(input.getUvo().getId_user());
			if(idPlan == null || idPlan == 0) {//NEW mode
				idPlan = this.updateDAONuked.insert(
						"INSERT.M_PPM.S02.HEADER", plan);

				List<PlanService> services = plan.getServices();
				for (PlanService service : services) {
					service.setIdPlan(idPlan);
					service.setIdLogin(input.getUvo().getId_user());
					service.setIdAudit(idAudit);
					Integer idPlanGrp = this.updateDAONuked.insert(
							"INSERT.M_PPM.S02.DETAIL", service);

					List<PlanServiceDetail> svcDetails = service.getDetails();
					for (PlanServiceDetail detail : svcDetails) {
                        if (CommonUtils.isEmpty(detail.getCboPlan())
                                && CommonUtils.isEmpty(detail.getCboPlanDetail())
                                && CommonUtils.isEmpty(detail.getCboVendor())) {
                            continue;
                        }
						detail.setIdPlanGrp(idPlanGrp);
						detail.setIdLogin(input.getUvo().getId_user());
						detail.setIdAudit(idAudit);
						this.updateDAONuked.insert("INSERT.M_PPM.S02.SERVICE", detail);
					}
				}
			}
			else {//EDIT mode
				this.updateDAO.execute("UPDATE.M_PPM.S02.HEADER", plan);
				
				this.updateDAO.execute("DELETE.M_PPM.S02.DETAIL", plan);
				if(plan.getServices() != null && !plan.getServices().isEmpty())
					this.updateDAO.execute("DELETE.M_PPM.S02.DETAIL.MAPPING", plan);
				List<PlanService> services = plan.getServices();
				for (PlanService service : services) {
					service.setIdPlan(idPlan);
					service.setIdLogin(input.getUvo().getId_user());
					service.setIdAudit(idAudit);
					Integer idPlanGrp = service.getIdPlanGrp();
					if(idPlanGrp == null || idPlanGrp == 0) {
						idPlanGrp = this.updateDAONuked.insert(
								"INSERT.M_PPM.S02.DETAIL", service);
					}
					else {
						this.updateDAO.execute("UPDATE.M_PPM.S02.DETAIL", service);
					}
					
					this.updateDAO.execute("DELETE.M_PPM.S02.SERVICE", service);
					List<PlanServiceDetail> svcDetails = service.getDetails();
					for (PlanServiceDetail detail : svcDetails) {
						detail.setIdPlanGrp(idPlanGrp);
						detail.setIdLogin(input.getUvo().getId_user());
						detail.setIdAudit(idAudit);
						Integer idPlanSvc = detail.getIdPlanSvc();
						if(idPlanSvc == null || idPlanSvc == 0) {
	                        if (CommonUtils.isEmpty(detail.getCboPlan())
	                                && CommonUtils.isEmpty(detail.getCboPlanDetail())
	                                && CommonUtils.isEmpty(detail.getCboVendor())) {
	                            continue;
	                        }
							idPlanSvc = this.updateDAONuked.insert(
									"INSERT.M_PPM.S02.SERVICE", detail);
						}
						else {
	                        if (CommonUtils.isEmpty(detail.getCboPlan())
	                                && CommonUtils.isEmpty(detail.getCboPlanDetail())
	                                && CommonUtils.isEmpty(detail.getCboVendor())) {
	                            Map<String, Object> param = new HashMap<String, Object>();
	                            param.put("idPlanSvc", idPlanSvc);
	                            param.put("idLogin", service.getIdLogin());
	                            param.put("idAudit", service.getIdAudit());
	                            this.updateDAO.execute("DELETE.M_PPM.S02.SERVICE.EMPTY", param);
	                            continue;
	                        }
							this.updateDAO.execute("UPDATE.M_PPM.S02.SERVICE", detail);
						}
					}
				}
			}
			
			// update reference of Audit Trail
			AuditTrailHeaderBean auditHeader = new AuditTrailHeaderBean();
			auditHeader.setIdAudit(idAudit);
			auditHeader.setReference(idPlan + ":"
					+ input.getPlan().getTxtPlanName());
			CommonUtils.auditTrailUpdate(auditHeader);
			CommonUtils.auditTrailEnd(idAudit);// End Audit Trail

			output.setIdPlan(idPlan);
			result.setResultObject(output);
			
			BLogicMessages messages = new BLogicMessages();
			messages.add(ActionMessages.GLOBAL_MESSAGE, new BLogicMessage(
					SAVE_SUCCESS_MSG));
			result.setMessages(messages);
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
			return result;
		} catch (Exception ex) {
			errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(
					SAVE_ERROR_MSG));
			result.setErrors(errors);
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
			return result;
		}
	}
	private boolean inputIsMulCharCheck(Plan plan) {
        boolean isMulCharFlg = true;
        String isCheckMulCharFlg = getIsCheckMulCharFlg();
        if ("1".equals(isCheckMulCharFlg)) {
            //Service Name
            String serviceName = plan.getTxtPlanName();
            if (CommonUtils.isMulChar(serviceName)) {
                isMulCharFlg = false;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104", new Object[]{"Service Name", CommonUtils.MUL_CHAR_STR}));
            }
            List<PlanService> planServiceList = plan.getServices();
            if (planServiceList!=null && 0<planServiceList.size()) {
                boolean subPlanFlg = true;
                boolean optionServiceFlg = true;
                for (PlanService planService : planServiceList) {
                    String subPlanName = CommonUtils.toString(planService.getTbxServiceName());
                    String type = CommonUtils.toString(planService.getType()).trim(); 
                    if (subPlanFlg || optionServiceFlg) {
                        if (CommonUtils.isMulChar(subPlanName)) {
                            isMulCharFlg = false;
                            if (!(!subPlanFlg && "S".equals(type)) && !(!optionServiceFlg && "O".equals(type))) {
                                String labelName = "";
                                if ("S".equals(type)) {
                                    subPlanFlg = false;
                                    labelName = "Sub Plan Name";
                                } else {
                                    optionServiceFlg = false;
                                    labelName = "Option Name";
                                }
                                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104", new Object[]{labelName, CommonUtils.MUL_CHAR_STR}));
                            }
                        }
                    }
                    if(!subPlanFlg && !optionServiceFlg){
                        break;
                    }
                }
            }
        }
        return isMulCharFlg;
	}
	
	private String getIsCheckMulCharFlg() {
        String isCheckMulCharFlg = CommonUtils.toString(queryDAO.executeForObject("SELECT.M_PPM.S02.GET_IS_CHECK_MUL_CHAR", null, String.class)).trim();
        
        return isCheckMulCharFlg;
    }
	
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}

	public UpdateDAOiBatisNuked getUpdateDAONuked() {
		return updateDAONuked;
	}

	public void setUpdateDAONuked(UpdateDAOiBatisNuked updateDAONuked) {
		this.updateDAONuked = updateDAONuked;
	}
}