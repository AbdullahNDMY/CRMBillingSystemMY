/**
 * Billing System
 * 
 * SUBSYSTEM NAME : Standard Price and Plan Maintenance
 * SERVICE NAME : M_PPM_S03
 * FUNCTION : SearchBLogic
 * FILE NAME : M_PPM_S03_01BLogic.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 * History
 */

package nttdm.bsys.m_ppm.blogic;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.m_ppm.bean.Service;
import nttdm.bsys.m_ppm.dto.M_PPM_S03_01Input;
import nttdm.bsys.m_ppm.dto.M_PPM_S03_01Output;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Search BLogic<br/>
 * Use to initial data before search<br/>
 * Do not search<br/>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class M_PPM_S03_01BLogic implements BLogic<M_PPM_S03_01Input> {
	private QueryDAO queryDAO;
	
	public BLogicResult execute(M_PPM_S03_01Input input) {
		BLogicResult result = new BLogicResult();
		M_PPM_S03_01Output output = new M_PPM_S03_01Output();

		try {
            BeanUtils.copyProperties(output, input);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
		
		List<Service> cboCategoryList = queryDAO.executeForObjectList("SELECT.M_PPM.S01.CATEGORY", null);
		output.setCboCategoryList(cboCategoryList);
		
		List<Map<String, Object>> cboServiceListFinal = new ArrayList<Map<String, Object>>(); 
		List<Map<String, Object>> cboServiceList = queryDAO.executeForMapList("SELECT.M_PPM.S01.GET_SERVICE", null);
		List<String> svcGroup = new ArrayList<String>();
		for(Map<String, Object> serviceMap : cboServiceList) {
			if(!svcGroup.contains(serviceMap.get("SVC_GRP").toString())) {
				svcGroup.add(serviceMap.get("SVC_GRP").toString());
			}
		}
		for(int i=0;i<svcGroup.size();i++) {
			Map<String, Object> temp = new HashMap<String, Object>(); 
			temp.put("ID_SERVICE", "");
			temp.put("SVC_NAME", "-Please Select One-");
			temp.put("GST", null);
			temp.put("SVC_GRP", svcGroup.get(i));
			cboServiceListFinal.add(temp);
		}
		cboServiceListFinal.addAll(cboServiceList);
		output.setCboServiceList(cboServiceListFinal);
		
		result.setResultObject(output);
		result.setResultString("success");
		return result;
	}

	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}
}