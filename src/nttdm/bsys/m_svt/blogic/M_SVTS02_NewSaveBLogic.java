package nttdm.bsys.m_svt.blogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_svt.dto.M_SVT01_SearchInput;
import nttdm.bsys.m_svt.dto.M_SVT01_SearchOutput;
import nttdm.bsys.m_svt.dto.PlanService;

import org.apache.struts.Globals;

public class M_SVTS02_NewSaveBLogic extends AbstractM_SVT02_NewSaveBLogic {

	public BLogicResult execute(M_SVT01_SearchInput input) {

		BLogicResult result = new BLogicResult();
		M_SVT01_SearchOutput output = new M_SVT01_SearchOutput();
		String returnFlg = "0";
		BLogicMessages msg = new BLogicMessages();
		String idLogin = input.getUvo().getId_user();
		String serviceGroup = input.getCategory();
		List<PlanService> listPlanService = input.getPlanServiceList();
		List<PlanService> listPlanServiceCopy = new ArrayList<PlanService>(listPlanService);
		ArrayList<String> errMsgList = new ArrayList<String>();
		String idServiceListStr = "";

		try {
			// Checking for duplicate values
			for (int i = 0; i < listPlanService.size(); i++) {

				PlanService planService1 = listPlanService.get(i);
				planService1.setIdLogin(idLogin);
				planService1.setServiceGroup(serviceGroup);
				int counter = 0;
				
				HashMap<String, Object> checkData = new HashMap<String, Object>();
				if(planService1.getIdService() == null){
					planService1.setIdService("");
				}
				checkData.put("idService", planService1.getIdService());
				checkData.put("description", planService1.getServiceDescription());
				checkData.put("serviceGroup", planService1.getServiceGroup());				
				checkData.put("svcCategory", planService1.getServiceCategory());
				Integer isExistCount = queryDAO.executeForObject("SELECT.M_SVT.M_SVC_ISEXIST", checkData, Integer.class);
				if(isExistCount.intValue() > 0){
					errMsgList.add(planService1.getServiceDescription().trim());
				}else {
					if (planService1.getIdService() == null || planService1.getIdService().equals("")) {
						for (int j = 0; j < listPlanService.size(); j++) {
	
							PlanService planService2 = listPlanServiceCopy.get(j);
							if (planService1.getServiceDescription().toLowerCase().trim().equals(planService2.getServiceDescription().toLowerCase().trim())
								/*&& planService1.getAccCode().trim().equals(planService2.getAccCode().trim())
								&& planService1.getDescAbbr().trim().equals(planService2.getDescAbbr().trim())*/
								&& planService1.getGst().equals(planService2.getGst())) {
	
								counter = counter + 1;
								if (counter > 1) {
									errMsgList.add(planService2
											.getServiceDescription().trim());
								}
							}
						}
					}
				}
				
			}
			if (!errMsgList.isEmpty()) {

				HashSet<String> errMsgHashSet = new HashSet<String>(errMsgList);
				List<String> errMsgList2 = new ArrayList<String>(errMsgHashSet);

				Comparator<String> comparator = new Comparator<String>() {
					public int compare(String ps1, String ps2) {

						String dispMsg1 = ps1.trim();
						String dispMsg2 = ps2.trim();

						if (dispMsg1 != dispMsg2) {
							return dispMsg1.compareTo(dispMsg2);
						} else {
							return 0;
						}
					}
				};
				Collections.sort(errMsgList2, comparator);
				Collections.reverse(errMsgList2);

				for (Object errMsgDisp : errMsgList2) {
					msg.add(Globals.ERROR_KEY, new BLogicMessage(
							"errors.ERR1SC006", errMsgDisp));
					result.setErrors(msg);
				}
				input.setReturnFlg(returnFlg);
				result.setResultObject(input);
				if (input.getTitle().equals("Service")) {
					result.setResultString("ServiceFailure");
				} else {
					result.setResultString("PlanFailure");
				}
				return result;
			} else {
				for (int i = 0; i < listPlanService.size(); i++) {
					PlanService planService1 = listPlanService.get(i);
					planService1.setIdLogin(idLogin);
					// If it's idServices doesn't exist then insert it
					if (planService1.getIdService().equals("")) {
						insertNewPlanService(planService1);
						//#157 start
						if ("".equals(idServiceListStr)) {
							idServiceListStr = planService1.getIdService();
						}else {
							idServiceListStr = idServiceListStr + "," + planService1.getIdService();
						}
						//#157 end
					} else {
						updatePlanService(planService1,input.getTitle());
					}
				}
				returnFlg = "1";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.add(Globals.ERROR_KEY, new BLogicMessage("info.ERR2SC004"));
			result.setErrors(msg);
		}
		output.setPlanServiceList(listPlanService);
		output.setReturnFlg(returnFlg);
		//#157 start
		if ("new".equals(input.getEditStatus())) {
			output.setParameters("");
		}else {
			output.setParameters(input.getParameters());
		}
		output.setIdServiceListStr(idServiceListStr);
		//#157 end
		result.setResultObject(output);
		result.setResultString("success");

		return result;
	}

	public void updatePlanService(PlanService planService,String title) {
		Integer idAudit = writeAuditTrail1(planService,
				BillingSystemConstants.AUDIT_TRAIL_EDITED);
		if (title.equals("Service")){
			updateDAO.execute("UPDATE.M_SVT.M_SVC", planService);
		}else {
			updateDAO.execute("UPDATE.M_SVT.M_SVC_PLAN", planService);
		}
		CommonUtils.auditTrailEnd(idAudit);// End Audit Trail
	}

	public void insertNewPlanService(PlanService planService) {
		String idService = queryDAO.executeForObject(
				"SELECT.M_SVT.GET_NEW_IDSERVICE", null, String.class);
		planService.setIdService(idService);
		Integer idAudit = writeAuditTrail1(planService,
				BillingSystemConstants.AUDIT_TRAIL_CREATED);
		updateDAO.execute("INSERT.M_SVT.M_SVC", planService);
		CommonUtils.auditTrailEnd(idAudit);// End Audit Trail
	}

	private Integer writeAuditTrail1(PlanService planService, String action) {
		Integer idAudit = CommonUtils.auditTrailBegin(
				planService.getIdLogin(),
				BillingSystemConstants.MODULE_M,
				BillingSystemConstants.SUB_MODULE_M_SVT,
				planService.getServiceGroup() + ":"
						+ planService.getServiceDescription(), null, action);
		planService.setIdAudit(idAudit);
		return idAudit;
	}
}
