/**
 * Billing System
 * 
 * SUBSYSTEM NAME : called by B-CPM-S02n screen
 * SERVICE NAME : M_PPM_S04
 * FUNCTION : SearchBLogic
 * FILE NAME : M_PPMS04SearchBLogic.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 */

package nttdm.bsys.m_ppm.blogic;

import java.util.ArrayList;
import java.util.List;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_ppm.bean.Service;
import nttdm.bsys.m_ppm.bean.ServiceGroup;
import nttdm.bsys.m_ppm.bean.ServiceSearchResult;
import nttdm.bsys.m_ppm.dto.M_PPMS04Input;
import nttdm.bsys.m_ppm.dto.M_PPMS04Output;

/**
 * Search BLogic<br/>
 * Do search action<br/>
 * 
 * @author NTTData
 * @version 1.1
 */
public class M_PPMS04SearchBLogic extends AbstractM_PPMS04SearchBLogic {

	public BLogicResult execute(M_PPMS04Input input) {
		BLogicResult result = new BLogicResult();
		M_PPMS04Output output = new M_PPMS04Output();

		String doSearch = input.getDoSearch();
		String doGetService = input.getDoGetService();
		
		if("Y".equals(doGetService)){
			input.setCboService("");
		}
		BillingSystemSettings systemSetting = new BillingSystemSettings(
				getQueryDAO());
		Integer row = systemSetting.getResultRow();
		Integer startIndex = 0;
		try {
			startIndex = Integer.parseInt(CommonUtils.toString(input
					.getStartIndex()));
		} catch (Exception e) {
			startIndex = 0;
		}

		// get Category Group
		List<ServiceGroup> cboCategoryList = getQueryDAO().executeForObjectList("SELECT.M_PPM.S01.CATEGORY", null);
		
		String cboCategory = input.getCboCategory();
		List<Service> cboServiceList = new ArrayList<Service>();
		// get Service
		if (cboCategory != null && (!"".equals(cboCategory))) {
			
			cboServiceList = getQueryDAO().executeForObjectList("SELECT.M_PPM.S04.SERVICE", cboCategory);

		} 
		
		List<ServiceSearchResult> searchResult = new ArrayList<ServiceSearchResult>();
		if ("Y".equals(doSearch)) {
			// get total
			Integer totalCount = getQueryDAO().executeForObject("SELECT.M_PPM.S04.RESULT.TOTALCOUNT", input, Integer.class);
			
			output.setTotalCount(String.valueOf(totalCount));
			
			searchResult = getQueryDAO().executeForObjectList("SELECT.M_PPM.S04.RESULT", input, startIndex, row);
			
		}
        
		output.setRow(String.valueOf(row));
		output.setStartIndex(String.valueOf(startIndex));
		output.setTbxServiceName(input.getTbxServiceName());
		output.setTbxServiceDescr(input.getTbxServiceDescr());
		output.setCboService(input.getCboService());
		String lblCustomerType = input.getLblCustomerType();
		output.setLblCustomerType(lblCustomerType);
		if("CORP".equals(lblCustomerType)){
			output.setLblCustomerTypeShow("CORPORATE");
		}else{
			output.setLblCustomerTypeShow("CONSUMER");
		}
		output.setCboCategory(input.getCboCategory());
		output.setCboServiceList(cboServiceList);
		output.setSearchResult(searchResult);
		output.setCboCategoryList(cboCategoryList);
		result.setResultObject(output);
		result.setResultString("success");
		return result;
	}
}
