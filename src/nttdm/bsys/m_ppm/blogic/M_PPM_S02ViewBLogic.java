/**
 * Billing System
 * 
 * SUBSYSTEM NAME : Standard Price and Plan Maintenance
 * SERVICE NAME : M_PPM_S02
 * FUNCTION : ViewBLogic
 * FILE NAME : M_PPM_S02ViewBLogic.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 * History
 */

package nttdm.bsys.m_ppm.blogic;

import java.util.List;

import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_ppm.bean.Service;
import nttdm.bsys.m_ppm.bean.Vendor;
import nttdm.bsys.m_ppm.dto.M_PPM_S02ViewInput;
import nttdm.bsys.m_ppm.dto.M_PPM_S02ViewOutput;
import nttdm.bsys.m_ppm.dto.Plan;
import nttdm.bsys.m_ppm.dto.PlanService;
import nttdm.bsys.m_ppm.dto.PlanServiceDetail;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicResult;

/**
 * View BLogic<br/>
 * Get all information of plan to view<br/>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class M_PPM_S02ViewBLogic implements BLogic<M_PPM_S02ViewInput> {
	private QueryDAO queryDAO;

	public BLogicResult execute(M_PPM_S02ViewInput input) {
		BLogicResult result = new BLogicResult();
		M_PPM_S02ViewOutput output = new M_PPM_S02ViewOutput();
		
		Integer idPlan = input.getIdPlan();
		Plan plan = queryDAO.executeForObject("SELECT.M_PPM.S02.HEADER", idPlan, Plan.class);
		List<PlanService> services = queryDAO.executeForObjectList("SELECT.M_PPM.S02.DETAIL", idPlan);
		plan.setServices(services);
		for(PlanService service : services) {
			List<Service> cboSvcLevel2List = queryDAO.executeForObjectList("SELECT.M_PPM.S02.SVC_LEVEL2", service.getCboSvcLevel1());
			service.setCboSvcLevel2List(cboSvcLevel2List);
			
			List<Service> cboPlanList = queryDAO.executeForObjectList("SELECT.M_PPM.S02.SVC_LEVEL3", service.getCboSvcLevel1());
			service.setCboPlanList(cboPlanList);
			
			List<Service> cboPlanDetailList = queryDAO.executeForObjectList("SELECT.M_PPM.S02.SVC_LEVEL4", service.getCboSvcLevel1());
			service.setCboPlanDetailList(cboPlanDetailList);
			
			List<PlanServiceDetail> details = queryDAO.executeForObjectList("SELECT.M_PPM.S02.SERVICE", service.getIdPlanGrp());
			service.setDetails(details);
		}
		
		//get vendor list
		List<Vendor> cboVendorList = queryDAO.executeForObjectList("SELECT.M_PPM.S02.VENDOR", null);
		output.setCboVendorList(cboVendorList);

		//get category list
		List<Service> cboCategoryList = queryDAO.executeForObjectList("SELECT.M_PPM.S01.CATEGORY", null);
		output.setCboCategoryList(cboCategoryList);
		
		String allGstSameFlg = "0";
        String allCategorySameFlg = "0";
        for(int i=0;i<services.size();i++) {
            String gst1 = CommonUtils.toString(services.get(i).getTbxGST());
            for(int j=i+1;j<services.size();j++) {
                String gst2 = CommonUtils.toString(services.get(j).getTbxGST());
                if(!gst1.equals(gst2)) {
                    allGstSameFlg = "1";
                    break;
                }
            }
            if("1".equals(allGstSameFlg)) {
                break;
            }
        }
        for(int i=0;i<services.size();i++) {
            String cboSvcLevel11 = CommonUtils.toString(services.get(i).getCboSvcLevel1());
            String cboSvcLevel21 = CommonUtils.toString(services.get(i).getCboSvcLevel2());
            for(int j=i+1;j<services.size();j++) {
                String cboSvcLevel12 = CommonUtils.toString(services.get(j).getCboSvcLevel1());
                String cboSvcLevel22 = CommonUtils.toString(services.get(j).getCboSvcLevel2());
                if(!cboSvcLevel11.equals(cboSvcLevel12)) {
                    allCategorySameFlg = "1";
                    break;
                }
                if(!cboSvcLevel21.equals(cboSvcLevel22)) {
                    allCategorySameFlg = "1";
                    break;
                }
            }
            if("1".equals(allCategorySameFlg)) {
                break;
            }
        }
        plan.setAllGstSameFlg(allGstSameFlg);
        plan.setAllCategorySameFlg(allCategorySameFlg);
        
        //#436: [B2-2][REQ003]NewTaxCode Start
		String taxWord = CommonUtils.toString(queryDAO.executeForObject("SELECT.M_PPM.S02.CPM_TAX_WORD", null, String.class)).trim();
		output.setTaxWord(taxWord);
		String taxDefaultId = CommonUtils.toString(queryDAO.executeForObject("SELECT.M_PPM.S02.CPM_TAX_ID_DEFAULT", null, String.class)).trim();
		output.setTaxDefaultId(taxDefaultId);
		//#436: [B2-2][REQ003]NewTaxCode End

		output.setPlan(plan);
		// return object
		result.setResultObject(output);

		result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
		return result;
	}

	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}
}
