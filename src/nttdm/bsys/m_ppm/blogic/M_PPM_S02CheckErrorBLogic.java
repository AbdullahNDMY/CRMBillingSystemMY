/**
 * @(#)M_PPM_S02CheckErrorBLogic.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created 2012/07/28
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.m_ppm.blogic;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_ppm.bean.Service;
import nttdm.bsys.m_ppm.bean.Vendor;
import nttdm.bsys.m_ppm.dto.M_PPM_S02CheckErrorInput;
import nttdm.bsys.m_ppm.dto.M_PPM_S02CheckErrorOutput;
import nttdm.bsys.m_ppm.dto.Plan;
import nttdm.bsys.m_ppm.dto.PlanService;

import org.apache.commons.beanutils.BeanUtils;

/**
 * @author gplai
 *
 */
public class M_PPM_S02CheckErrorBLogic implements BLogic<M_PPM_S02CheckErrorInput> {

    private QueryDAO queryDAO;
    
    public BLogicResult execute(M_PPM_S02CheckErrorInput params) {
        BLogicResult result = new BLogicResult();
        BLogicMessages errors = new BLogicMessages();
        M_PPM_S02CheckErrorOutput output = new M_PPM_S02CheckErrorOutput();
        
        Plan plan = params.getPlan();
        List<PlanService> services = plan.getServices();
        for (PlanService service : services) {
            String svcLevel1 = CommonUtils.toString(service.getCboSvcLevel1()).trim();
            if (!CommonUtils.isEmpty(svcLevel1)) {
                List<Service> cboSvcLevel2List = queryDAO.executeForObjectList("SELECT.M_PPM.S02.SVC_LEVEL2", service.getCboSvcLevel1());
                service.setCboSvcLevel2List(cboSvcLevel2List);
                
                List<Service> cboPlanList = queryDAO.executeForObjectList("SELECT.M_PPM.S02.SVC_LEVEL3", service.getCboSvcLevel1());
                service.setCboPlanList(cboPlanList);
                
                List<Service> cboPlanDetailList = queryDAO.executeForObjectList("SELECT.M_PPM.S02.SVC_LEVEL4", service.getCboSvcLevel1());
                service.setCboPlanDetailList(cboPlanDetailList);
            } else {
                service.setCboSvcLevel2List(new ArrayList<Service>());
                service.setCboPlanList(new ArrayList<Service>());
                service.setCboPlanDetailList(new ArrayList<Service>());
            }
        }
        
        List<Vendor> cboVendorList = queryDAO.executeForObjectList("SELECT.M_PPM.S02.VENDOR", null);
        output.setCboVendorList(cboVendorList);

        List<Service> cboCategoryList = queryDAO.executeForObjectList("SELECT.M_PPM.S01.CATEGORY", null);
        output.setCboCategoryList(cboCategoryList);
        
        String ppmOptionSvc = CommonUtils.toString(queryDAO.executeForObject("SELECT.M_PPM.S02.PPM_OPTION_SVC", null, String.class)).trim();
        plan.setPpmOptionSvc(ppmOptionSvc);
        params.setPlan(plan);
        
        try {
            BeanUtils.copyProperties(output, params);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        
        result.setResultObject(output);
        result.setErrors(errors);
        result.setResultString("success");
        return result;
    }

    /**
     * @return the queryDAO
     */
    public QueryDAO getQueryDAO() {
        return queryDAO;
    }

    /**
     * @param queryDAO the queryDAO to set
     */
    public void setQueryDAO(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }
}
