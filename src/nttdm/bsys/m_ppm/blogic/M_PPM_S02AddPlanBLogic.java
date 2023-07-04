/**
 * Billing System
 * 
 * SUBSYSTEM NAME : Standard Price and Plan Maintenance
 * SERVICE NAME : M_PPM_S02
 * FUNCTION : EditBLogic
 * FILE NAME : M_PPM_S02EditBLogic.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 * History
 */

package nttdm.bsys.m_ppm.blogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.omg.CORBA.Request;

import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_ppm.bean.Service;
import nttdm.bsys.m_ppm.bean.Vendor;
import nttdm.bsys.m_ppm.dto.M_PPM_S02EditInput;
import nttdm.bsys.m_ppm.dto.M_PPM_S02EditOutput;
import nttdm.bsys.m_ppm.dto.Plan;
import nttdm.bsys.m_ppm.dto.PlanService;
import nttdm.bsys.m_ppm.dto.PlanServiceDetail;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicResult;

/**
 * Edit BLogic<br/>
 * Get all information of plan to display for edit<br/>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class M_PPM_S02AddPlanBLogic implements BLogic<M_PPM_S02EditInput> {
    private QueryDAO queryDAO;
    
    public BLogicResult execute(M_PPM_S02EditInput input) {
        BLogicResult result = new BLogicResult();
        M_PPM_S02EditOutput output = new M_PPM_S02EditOutput();
        
        Integer idPlan = input.getIdPlan();
        Plan plan = queryDAO.executeForObject("SELECT.M_PPM.S02.HEADER", idPlan, Plan.class);
        List<Service> cboSvcLevel2List = queryDAO.executeForObjectList("SELECT.M_PPM.S02.SVC_LEVEL2", input.getCboSvcLevel1());
        List<Service> cboPlanList = queryDAO.executeForObjectList("SELECT.M_PPM.S02.SVC_LEVEL3", input.getCboSvcLevel1());
        List<Service> cboPlanDetailList = queryDAO.executeForObjectList("SELECT.M_PPM.S02.SVC_LEVEL4", input.getCboSvcLevel1());
        PlanServiceDetail detail = new PlanServiceDetail();
        List<PlanServiceDetail> details=new ArrayList<PlanServiceDetail>();
        detail.setCboPlan("");
        detail.setCboPlanDetail("");
        detail.setCboVendor("");
        details.add(detail);
        PlanService service=new PlanService();
        service.setCboSvcLevel1(input.getCboSvcLevel1());
        service.setCboSvcLevel2(input.getCboSvcLevel2());
        service.setCboSvcLevel2List(cboSvcLevel2List);
        service.setCboPlanList(cboPlanList);
        service.setCboPlanDetailList(cboPlanDetailList);
        service.setCboRateType("BA");
        //modify for #151 Start       
        //service.setCboRateType2("A20");
        Map<String, String> param = new HashMap<String, String>();
		param.put("modeType", "M");
		param.put("EU", "A");
		String rateType2 = queryDAO.executeForObject("SELECT.M_PPM.S02.RATE_TYPE2", param, String.class);
		service.setCboRateType2(rateType2);
		
		//#436: [B2-2][REQ003]NewTaxCode Start
  		String taxWord = CommonUtils.toString(queryDAO.executeForObject("SELECT.M_PPM.S02.CPM_TAX_WORD", null, String.class)).trim();
  		output.setTaxWord(taxWord);
  		String taxDefaultId = CommonUtils.toString(queryDAO.executeForObject("SELECT.M_PPM.S02.CPM_TAX_ID_DEFAULT", null, String.class)).trim();
  		output.setTaxDefaultId(taxDefaultId);
  		//#436: [B2-2][REQ003]NewTaxCode End
		
		String cboRateType2Flg = CommonUtils.toString(queryDAO.executeForObject("SELECT.M_PPM.S02.GET_IS_RATE_TYPE2", null, String.class)).trim();
		output.setCboRateType2Flg(cboRateType2Flg);
        //modify for #151 End
        service.setCboRateMode("5");
        service.setDetails(details);
        
        service.setTbxGST(taxDefaultId);

        
        List<PlanService> services=new  ArrayList<PlanService>();
        services.add(service);
        plan.setServices(services);
        plan.setIsSaveFlg(0);
        
        //get vendor list
        List<Vendor> cboVendorList = queryDAO.executeForObjectList("SELECT.M_PPM.S02.VENDOR", null);
        output.setCboVendorList(cboVendorList);

        //get category list
        List<Service> cboCategoryList = queryDAO.executeForObjectList("SELECT.M_PPM.S01.CATEGORY", null);
        output.setCboCategoryList(cboCategoryList);
        
        String ppmOptionSvc = CommonUtils.toString(queryDAO.executeForObject("SELECT.M_PPM.S02.PPM_OPTION_SVC", null, String.class)).trim();
        plan.setPpmOptionSvc(ppmOptionSvc);
        
        output.setPlan(plan);
        // return object
        result.setResultObject(output);
        
        //forward M_PPM_S06a.jsp
        result.setResultString("addplan");
        return result;
    }

    public QueryDAO getQueryDAO() {
        return queryDAO;
    }
    public void setQueryDAO(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }
}