/**
 * Billing System
 * 
 * SUBSYSTEM NAME : Standard Price and Plan Maintenance
 * SERVICE NAME : M_PPM_S02
 * FUNCTION : NewBLogic
 * FILE NAME : M_PPM_S02NewBLogic.java
 * 
 * Copyright (C) 2011 NTTDATA Corporation
 * History
 */

package nttdm.bsys.m_ppm.blogic;

import java.util.List;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_ppm.bean.Service;
import nttdm.bsys.m_ppm.bean.Vendor;
import nttdm.bsys.m_ppm.dto.M_PPM_S02NewInput;
import nttdm.bsys.m_ppm.dto.M_PPM_S02NewOutput;
import nttdm.bsys.m_ppm.dto.Plan;

import org.apache.struts.Globals;

/**
 * New BLogic<br/>
 * Initial data for new screen<br/>
 * @author  NTTData Vietnam
 * @version 1.1
 */
public class M_PPM_S02NewBLogic  implements BLogic<M_PPM_S02NewInput> {
	
	private QueryDAO queryDAO = null;
	
	private static final String SAVE_ERROR_MSG = "info.ERR2SC004";
	
	public BLogicResult execute(M_PPM_S02NewInput input) {
		BLogicMessages errors = new BLogicMessages();
		BLogicResult result = new BLogicResult();		
		try{ 
			M_PPM_S02NewOutput output = new M_PPM_S02NewOutput();
			
			List<Vendor> cboVendorList = queryDAO.executeForObjectList("SELECT.M_PPM.S02.VENDOR", null);
			output.setCboVendorList(cboVendorList);

			List<Service> cboCategoryList = queryDAO.executeForObjectList("SELECT.M_PPM.S01.CATEGORY", null);
			output.setCboCategoryList(cboCategoryList);
			
			//#436: [B2-2][REQ003]NewTaxCode Start
			String taxWord = CommonUtils.toString(queryDAO.executeForObject("SELECT.M_PPM.S02.CPM_TAX_WORD", null, String.class)).trim();
			output.setTaxWord(taxWord);
			String taxDefaultId = CommonUtils.toString(queryDAO.executeForObject("SELECT.M_PPM.S02.CPM_TAX_ID_DEFAULT", null, String.class)).trim();
			output.setTaxDefaultId(taxDefaultId);
			//#436: [B2-2][REQ003]NewTaxCode End
			
			//Default Currency
			BillingSystemSettings systemSetting = new BillingSystemSettings(queryDAO);
			Plan plan = new Plan();
			plan.setCboBillCurrenc(systemSetting.getDefCurrency());
			//#436: [B2-2][REQ003]NewTaxCode Start
			plan.setGSTApplyAllCbo(taxDefaultId);
			//#436: [B2-2][REQ003]NewTaxCode End
			plan.setModelFlg("new");
			plan.setGSTApplyAllChk("1");
			plan.setCategoryApplyAllChk("1");
			
			String ppmOptionSvc = CommonUtils.toString(queryDAO.executeForObject("SELECT.M_PPM.S02.PPM_OPTION_SVC", null, String.class)).trim();
	        plan.setPpmOptionSvc(ppmOptionSvc);
			output.setPlan(plan);
			
			String cboRateType2Flg = CommonUtils.toString(queryDAO.executeForObject("SELECT.M_PPM.S02.GET_IS_RATE_TYPE2", null, String.class)).trim();
			output.setCboRateType2Flg(cboRateType2Flg);
			
			result.setResultObject(output);			
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);		
			return result;  
		}
		catch(Exception ex){
			errors.add(Globals.MESSAGE_KEY,new BLogicMessage(SAVE_ERROR_MSG));
			result.setErrors(errors);
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
			return result;
		}
	}

	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}
}
