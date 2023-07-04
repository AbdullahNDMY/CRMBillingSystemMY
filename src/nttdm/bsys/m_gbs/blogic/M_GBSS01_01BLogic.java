/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_GBS
 * SERVICE NAME   : M_GBS_S01
 * FUNCTION       : Showing BLogic
 * FILE NAME      : M_GBSS01_01BLogic.java
 *
 * History
 * 
**********************************************************/

package nttdm.bsys.m_gbs.blogic;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.m_gbs.bean.GSettingBean;
import nttdm.bsys.m_gbs.dto.M_GBSS01_01Input;
import nttdm.bsys.m_gbs.dto.M_GBSS01_01Output;

import org.apache.commons.beanutils.BeanUtils;

/**
 * M_GBSS01_01BLogic<br>
 * <ul>
 * <li>A BLogic class to process displaying Global Settings
 * </ul>
 * <br>
* @author  NTTData Vietnam
* @version 1.0
 */
public class M_GBSS01_01BLogic extends AbstractM_GBSS01_01BLogic {

	/**
	 * 
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	public BLogicResult execute(M_GBSS01_01Input param) {
		BLogicResult result = new BLogicResult();
		M_GBSS01_01Output output = new M_GBSS01_01Output();
		List<String> errorMessages = new ArrayList<String>();
		List<String> successMessages = new ArrayList<String>();
		List<GSettingBean> listGlobalSettingBean = new ArrayList<GSettingBean>();
		
		BillingSystemUserValueObject uvo = param.getUvoObject();
		
		String accessType = uvo.getUserAccessInfo("M", "M-GBS").getAccess_type().trim();
		
		// Get data from database if actionType is not update
		if(!param.getActionType().equalsIgnoreCase("update")) {
			listGlobalSettingBean = queryDAO.executeForObjectList(
					"M_GBS.selectGlobalSettings", null, 0,
					Integer.MAX_VALUE);
			for (GSettingBean settingBean : listGlobalSettingBean) {
				// TAB 1
				if(settingBean.getIdSet().equalsIgnoreCase("DASHBD")) {
					output.setTbxValueDASHBD1(settingBean.getSetValue());
					output.setDispDASHBD1(settingBean.getSetApply());
				}
				else if(settingBean.getIdSet().equalsIgnoreCase("USRMNT")) {
					if (settingBean.getSetSeq() == 1)
					{
						output.setTbxValueUSRMNT1(settingBean.getSetValue());
						output.setDispUSRMNT1(settingBean.getSetApply());
					}
				}
				else if(settingBean.getIdSet().equalsIgnoreCase("PWDCH")) {
					if (settingBean.getSetSeq() == 1)
					{
						output.setTbxValuePWDCH1(settingBean.getSetValue());
						output.setDispPWDCH1(settingBean.getSetApply());
					}
				}
				else if(settingBean.getIdSet().equalsIgnoreCase("LOGINAT")) {
					if (settingBean.getSetSeq() == 1)
					{
						output.setTbxValueLOGINAT1(settingBean.getSetValue());
						output.setDispLOGINAT1(settingBean.getSetApply());
					}
					else if ( settingBean.getSetSeq() == 2)
					{
						output.setTbxValueLOGINAT2(settingBean.getSetValue());
						output.setDispLOGINAT2(settingBean.getSetApply());
					}
				}
				else if(settingBean.getIdSet().equalsIgnoreCase("RESULTROW")) {
					if (settingBean.getSetSeq() == 1)
					{
						output.setTbxValueRESULTROW1(settingBean.getSetValue());
						output.setDispRESULTROW1(settingBean.getSetApply());
					}
				}
				else if(settingBean.getIdSet().equalsIgnoreCase("DEF_CURRENCY")) {
					if (settingBean.getSetSeq() == 1)
					{
						output.setLblValueDEF_CURRENCY1(settingBean.getSetValue());
						output.setDispDEF_CURRENCY1(settingBean.getSetApply());
					}
				}
				else if(settingBean.getIdSet().equalsIgnoreCase("SYSTEMBASE")) {
					if (settingBean.getSetSeq() == 1)
					{
						output.setLblValueSYSTEMBASE1(settingBean.getSetValue());
						output.setDispSYSTEMBASE1(settingBean.getSetApply());
					}
				}
				else if(settingBean.getIdSet().equalsIgnoreCase("FILESIZEUPLOAD")) {
					if ( settingBean.getSetSeq() == 1)
					{
						output.setTbxValueFILESIZEUPLOAD1(settingBean.getSetValue());
						output.setDispFILESIZEUPLOAD1(settingBean.getSetApply());
					}
				}
				else if(settingBean.getIdSet().equalsIgnoreCase("SERVICEROW")) {
					if (settingBean.getSetSeq() == 1)
					{
						output.setTbxValueSERVICEROW1(settingBean.getSetValue());
						output.setDispSERVICEROW1(settingBean.getSetApply());
					}
				}
				else if(settingBean.getIdSet().equalsIgnoreCase("DESC_LENGTH")) {
					if (settingBean.getSetSeq() == 1)
					{
						output.setTbxValueDESC_LENGTH1(settingBean.getSetValue());
						output.setDispDESC_LENGTH1(settingBean.getSetApply());
					}
				}
				else if(settingBean.getIdSet().equalsIgnoreCase("BATCH_MSG")) {
					if ( settingBean.getSetSeq() == 1)
					{
						output.setTbxValueBATCH_MSG1(settingBean.getSetValue());
						output.setDispBATCH_MSG1(settingBean.getSetApply());
					}
					else if ( settingBean.getSetSeq() == 2)
					{
						output.setTbxValueBATCH_MSG2(settingBean.getSetValue());
						output.setDispBATCH_MSG2(settingBean.getSetApply());
					}
					else if ( settingBean.getSetSeq() == 3)
					{
						output.setTbxValueBATCH_MSG3(settingBean.getSetValue());
						output.setDispBATCH_MSG3(settingBean.getSetApply());
					}
				}
				
				// TAB 2
				else if(settingBean.getIdSet().equalsIgnoreCase("BIL_DEBTER_ACC")) {
					if (settingBean.getSetSeq() == 1)
					{
						output.setTbxValueBIL_DEBTER_ACC1(settingBean.getSetValue());
						output.setDispBIL_DEBTER_ACC1(settingBean.getSetApply());
					}
				}
				//#81=>Add Advance Debtor Account 
				else if(settingBean.getIdSet().equalsIgnoreCase("BIL_ADVANCE_ACC")) {
                    if (settingBean.getSetSeq() == 1)
                    {
                        output.setTbxValueBIL_ADVANCE_DEBTER_ACC1(settingBean.getSetValue());
                        output.setDispBIL_ADVANCE_DEBTER_ACC1(settingBean.getSetApply());
                    }
                }
				else if(settingBean.getIdSet().equalsIgnoreCase("GST")) {
					if (settingBean.getSetSeq() == 1)
					{
						output.setTbxValueGST1(settingBean.getSetValue());
						output.setDispGST1(settingBean.getSetApply());
					}
				}
				else if(settingBean.getIdSet().equalsIgnoreCase("INVOICE_DUE_PERIOD")) {
					if (settingBean.getSetSeq() == 1)
					{
						output.setTbxValueINVOICE_DUE_PERIOD1(settingBean.getSetValue());
						output.setDispINVOICE_DUE_PERIOD1(settingBean.getSetApply());
					}
				}
				else if(settingBean.getIdSet().equalsIgnoreCase("NOPRINTDOC")) {
					if (settingBean.getSetSeq() == 1)
					{
						output.setTbxValueNOPRINTDOC1(settingBean.getSetValue());
						output.setDispNOPRINTDOC1(settingBean.getSetApply());
					}
				}
				else if(settingBean.getIdSet().equalsIgnoreCase("BILL_DOC_FOOTER"))
				{
					if (settingBean.getSetSeq() == 1) {
						output.setTbxValueBILL_DOC_FOOTER1(settingBean.getSetValue());
						output.setDispBILL_DOC_FOOTER1(settingBean.getSetApply());
					}
					else if(settingBean.getSetSeq() == 2) {
						output.setTbxValueBILL_DOC_FOOTER2(settingBean.getSetValue());
						output.setDispBILL_DOC_FOOTER2(settingBean.getSetApply());
					}
					else if(settingBean.getSetSeq() == 3) {
						output.setTbxValueBILL_DOC_FOOTER3(settingBean.getSetValue());
						output.setDispBILL_DOC_FOOTER3(settingBean.getSetApply());
					}
					else if(settingBean.getSetSeq() == 4) {
						output.setTbxValueBILL_DOC_FOOTER4(settingBean.getSetValue());
						output.setDispBILL_DOC_FOOTER4(settingBean.getSetApply());
					}
					else if(settingBean.getSetSeq() == 5) {
						output.setTbxValueBILL_DOC_FOOTER5(settingBean.getSetValue());
						output.setDispBILL_DOC_FOOTER5(settingBean.getSetApply());
					}
					else if(settingBean.getSetSeq() == 6) {
						output.setTbxValueBILL_DOC_FOOTER6(settingBean.getSetValue());
						output.setDispBILL_DOC_FOOTER6(settingBean.getSetApply());
					}
					else if(settingBean.getSetSeq() == 7) {
						output.setTbxValueBILL_DOC_FOOTER7(settingBean.getSetValue());
						output.setDispBILL_DOC_FOOTER7(settingBean.getSetApply());
					}
					else if(settingBean.getSetSeq() == 8) {
						output.setTbxValueBILL_DOC_FOOTER8(settingBean.getSetValue());
						output.setDispBILL_DOC_FOOTER8(settingBean.getSetApply());
					}
					else if(settingBean.getSetSeq() == 9) {
						output.setTbxValueBILL_DOC_FOOTER9(settingBean.getSetValue());
						output.setDispBILL_DOC_FOOTER9(settingBean.getSetApply());
					}
					else if(settingBean.getSetSeq() == 10) {
						output.setTbxValueBILL_DOC_FOOTER10(settingBean.getSetValue());
						output.setDispBILL_DOC_FOOTER10(settingBean.getSetApply());
					}
					else if(settingBean.getSetSeq() == 11) {
						output.setTbxValueBILL_DOC_FOOTER11(settingBean.getSetValue());
						output.setDispBILL_DOC_FOOTER11(settingBean.getSetApply());
					}
					else if(settingBean.getSetSeq() == 12) {
						output.setTbxValueBILL_DOC_FOOTER12(settingBean.getSetValue());
						output.setDispBILL_DOC_FOOTER12(settingBean.getSetApply());
					}
					else if(settingBean.getSetSeq() == 13) {
						output.setTbxValueBILL_DOC_FOOTER13(settingBean.getSetValue());
						output.setDispBILL_DOC_FOOTER13(settingBean.getSetApply());
					}
					else if(settingBean.getSetSeq() == 14) {
						output.setTbxValueBILL_DOC_FOOTER14(settingBean.getSetValue());
						output.setDispBILL_DOC_FOOTER14(settingBean.getSetApply());
					}
					else if(settingBean.getSetSeq() == 15) {
						output.setTbxValueBILL_DOC_FOOTER15(settingBean.getSetValue());
						output.setDispBILL_DOC_FOOTER15(settingBean.getSetApply());
					}
				}
				else if(settingBean.getIdSet().equalsIgnoreCase("CB_AUTO_OFFSET")) {
					if (settingBean.getSetSeq() == 1)
					{
						output.setRdbCashBookAutoOffset(settingBean.getSetValue());
						output.setDispCashBookAutoOffset(settingBean.getSetApply());
					}
				}
				else if(settingBean.getIdSet().equalsIgnoreCase("ESET_RUNDATE")) {
					if (settingBean.getSetSeq() == 1)
					{
						output.setRdbBatchRunDateEntry(settingBean.getSetValue());
						output.setDispBatchRunDateEntry(settingBean.getSetApply());
					}
				}
				
				// TAB 3
				else if (settingBean.getIdSet().equalsIgnoreCase("SHAREDFOLDER")){
					if (settingBean.getSetSeq() == 1)
					{
						output.setTbxValueSHAREDFOLDER(settingBean.getSetValue());
						output.setDispSHAREDFOLDER(settingBean.getSetApply());
					}
				}
				else if(settingBean.getIdSet().equalsIgnoreCase("FILELOC")) {
					if (settingBean.getSetSeq() == 1)
					{
						output.setTbxValueFILELOC1(settingBean.getSetValue());
						output.setDispFILELOC1(settingBean.getSetApply());
					}
				}
				//batch import
				else if(settingBean.getIdSet().equalsIgnoreCase("BATCH_G_GIR_P02")) {
					if (settingBean.getSetSeq() == 1)
					{
						output.setTbxValueBATCH_G_GIR_P021(settingBean.getSetValue());
						output.setDispBATCH_G_GIR_P021(settingBean.getSetApply());
					}
				}
				else if(settingBean.getIdSet().equalsIgnoreCase("BATCH_G_SGP_P02")) {
					if (settingBean.getSetSeq() == 1)
					{
						output.setTbxValueBATCH_G_SGP_P021(settingBean.getSetValue());
						output.setDispBATCH_G_SGP_P021(settingBean.getSetApply());
					}
				}
				else if(settingBean.getIdSet().equalsIgnoreCase("BATCH_G_IPS_P01")) {
					if (settingBean.getSetSeq() == 1)
					{
						output.setTbxValueBATCH_G_IPS_P011(settingBean.getSetValue());
						output.setDispBATCH_G_IPS_P011(settingBean.getSetApply());
					}
				}
				else if(settingBean.getIdSet().equalsIgnoreCase("BATCH_G_CLC_P01")) {
					if (settingBean.getSetSeq() == 1)
					{
						output.setTbxValueBATCH_G_CLC_P011(settingBean.getSetValue());
						output.setDispBATCH_G_CLC_P011(settingBean.getSetApply());
					}
				}
				//batch export
				else if(settingBean.getIdSet().equalsIgnoreCase("BATCH_G_CIT_P01")) {
					if (settingBean.getSetSeq() == 1)
					{
						output.setTbxValueBATCH_G_CIT_P011(settingBean.getSetValue());
						output.setDispBATCH_G_CIT_P011(settingBean.getSetApply());
					}
				}
				else if(settingBean.getIdSet().equalsIgnoreCase("BATCH_G_GIR_P01")) {
					if (settingBean.getSetSeq() == 1)
					{
						output.setTbxValueBATCH_G_GIR_P011(settingBean.getSetValue());
						output.setDispBATCH_G_GIR_P011(settingBean.getSetApply());
					}
				}
				else if(settingBean.getIdSet().equalsIgnoreCase("BATCH_G_SGP_P01")) {
					if (settingBean.getSetSeq() == 1)
					{
						output.setTbxValueBATCH_G_SGP_P011(settingBean.getSetValue());
						output.setDispBATCH_G_SGP_P011(settingBean.getSetApply());
					}
				}
				else if(settingBean.getIdSet().equalsIgnoreCase("BATCH_G_RPT_AR01")) {
					if (settingBean.getSetSeq() == 1)
					{
						output.setTbxValueBATCH_G_RPT_AR011(settingBean.getSetValue());
						output.setDispBATCH_G_RPT_AR011(settingBean.getSetApply());
					}
				}
				else if(settingBean.getIdSet().equalsIgnoreCase("BATCH_E_EXP_F01")) {
					if (settingBean.getSetSeq() == 1)
					{
						output.setTbxValueBATCH_E_EXP_F011(settingBean.getSetValue());
						output.setDispBATCH_E_EXP_F011(settingBean.getSetApply());
					}
				}
                else if(settingBean.getIdSet().equalsIgnoreCase("BATCH_G_CSB_P01")) {
                    if (settingBean.getSetSeq() == 1)
                    {
                        output.setTbxValueBATCH_G_CSB_P01(settingBean.getSetValue());
                        output.setDispBATCH_G_CSB_P01(settingBean.getSetApply());
                    }
                }
				/*BATCH_E_EML_P01*/
                else if(settingBean.getIdSet().equalsIgnoreCase("BATCH_E_EML_P01")) {
                    if (settingBean.getSetSeq() == 1)
                    {
                        output.setTbxValueBATCH_E_EML_P01(settingBean.getSetValue());
                        output.setDispBATCH_E_EML_P01(settingBean.getSetApply());
                    }
                }
				/*BATCH_R_REV_P01*/
                else if(settingBean.getIdSet().equalsIgnoreCase("BATCH_R_REV_P01")) {
                    if (settingBean.getSetSeq() == 1)
                    {
                        output.setTbxValueBATCH_R_REV_P01(settingBean.getSetValue());
                        output.setDispBATCH_R_REV_P01(settingBean.getSetApply());
                    }
                }
			}
		} else {
			// Else get from previous page
			try {
				BeanUtils.copyProperties(output, param);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		
		if(new Boolean(true).equals(param.getIsUpdateSuccessed())) {
			successMessages.add("errors.ERR2SC003");
		} else {
			//errorMessages.add("errors.ERR2SC004");
		}
		
		output.setListGlobalSettingBean(listGlobalSettingBean);
		output.setErrorMessages(errorMessages);
		output.setSuccessMessages(successMessages);
		if ("2".equals(accessType)) {
			result.setResultString("success");
		} else{
			result.setResultString("readonly");
		}
		result.setResultObject(output);
		return result;
	}
}