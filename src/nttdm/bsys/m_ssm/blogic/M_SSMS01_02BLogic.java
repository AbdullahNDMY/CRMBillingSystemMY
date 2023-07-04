/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_SSM
 * SERVICE NAME   : M_SSM_S01
 * FUNCTION       : M_SSMS01_02BLogic
 * FILE NAME      : M_SSMS01_02BLogic.java
 * 
* Copyright © 2011 NTTDATA Corporation
*
 * History
 * 
 * 
**********************************************************/
package nttdm.bsys.m_ssm.blogic;

import java.util.ArrayList;
import java.util.List;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.m_ssm.dto.M_SSMS01_02Input;
import nttdm.bsys.m_ssm.dto.M_SSMS01_02Output;
import nttdm.bsys.m_ssm.bean.ServiceGroupBean;
import nttdm.bsys.m_ssm.bean.ServiceObjectBean;
import nttdm.bsys.m_ssm.bean.ServiceTypeBean;
import nttdm.bsys.m_ssm.bean.ServiceTypeItemBean;
import nttdm.bsys.m_ssm.blogic.AbstractM_SSMS01_02BLogic;

/**
 * M_SSMS01_02BLogic<br>
 * <ul>
 * <li>BusinessLogic class.
 * </ul>
 * <br>
* @author  NTTData Vietnam
* @version 1.0
 */
public class M_SSMS01_02BLogic extends AbstractM_SSMS01_02BLogic {
	private int TOTAL_COLUMN = 0;

	/**
	 * 
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	public BLogicResult execute(M_SSMS01_02Input param) {
		BLogicResult result = new BLogicResult();
		M_SSMS01_02Output output = new M_SSMS01_02Output();
		List<String> errorMessages = new ArrayList<String>();
		List<String> successMessages = new ArrayList<String>();

		List<ServiceGroupBean> listBasicInfo = queryDAO.executeForObjectList(
				"M_SSM.selectServiceBasicInfo", null);
		TOTAL_COLUMN = listBasicInfo.size();
		
		List<ServiceGroupBean> listServiceGroup = queryDAO
				.executeForObjectList("M_SSM.selectServiceGroups", null);

		// List to store all service type with Entry and Report flag of a
		// Service Group
		List<List<ServiceTypeItemBean>> listResult = new ArrayList<List<ServiceTypeItemBean>>();

		// List to store only service type name and id of a Service Group
		List<ServiceTypeBean> listServiceType = new ArrayList<ServiceTypeBean>();

		// get groupId
		String svcGrp = param.getSvc_grp();
		
		List<ServiceObjectBean> ssmRows = new ArrayList<ServiceObjectBean>();
		// Get value to display
		if (!"".equals(svcGrp)) {
			// Get all service type for selection combobox
			listServiceType = queryDAO.executeForObjectList(
					"M_SSM.selectServiceType", svcGrp, 0, Integer.MAX_VALUE);
			
			//get all SSM row of a service group			
			ssmRows = queryDAO.executeForObjectList("M_SSM.selectServicesOfSSM", svcGrp, 0, Integer.MAX_VALUE);
			
			for (int i=0; i<ssmRows.size(); i++)
			{
				// get all records from M_SSM table for a service group id
				List<ServiceTypeItemBean> listSSM = new ArrayList<ServiceTypeItemBean>();
				listSSM = queryDAO.executeForObjectList(
						"M_SSM.selectSSMFromService", ssmRows.get(i), 0,
						Integer.MAX_VALUE);
				listResult.add(listSSM);
			}
		}

		output.setErrorMessages(errorMessages);
		output.setSuccessMessages(successMessages);
		output.setListServiceGroup(listServiceGroup);
		output.setListServiceType(listServiceType);
		output.setListResult(listResult);
		output.setListSsmRows(ssmRows);
		output.setListServiceBasicInfo(listBasicInfo);
		result.setResultString("success");
		result.setResultObject(output);
		return result;
	}
}
