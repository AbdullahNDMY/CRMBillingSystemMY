/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_GBS
 * SERVICE NAME   : M_GBS_S01
 * FUNCTION       : Showing BLogic
 * FILE NAME      : M_GBSS01_02BLogic.java
 *
 * History
 * 
**********************************************************/
package nttdm.bsys.m_gbs.blogic;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_gbs.bean.GSettingBean;
import nttdm.bsys.m_gbs.bean.ParameterObject;
import nttdm.bsys.m_gbs.dto.M_GBSS01_02Input;
import nttdm.bsys.m_gbs.dto.M_GBSS01_02Output;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.Globals;

/**
 * M_GBSS01_02BLogic<br>
 * <ul>
 * <li>A BLogic class to process updating Global Settings
 * </ul>
 * <br>
* @author  NTTData Vietnam
* @version 1.0
 */
public class M_GBSS01_02BLogic extends AbstractM_GBSS01_02BLogic {

    private BLogicMessages errors;
    
	/**
	 * 
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	public BLogicResult execute(M_GBSS01_02Input param) {
		BLogicResult result = new BLogicResult();
		errors = new BLogicMessages();

		if(!inputIsMulCharCheck(param)) {
		    M_GBSS01_02Output outputDTO = new M_GBSS01_02Output();
	        try {
	            BeanUtils.copyProperties(outputDTO, param);
	        } catch (IllegalAccessException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (InvocationTargetException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
            result.setErrors(errors);
            result.setResultObject(outputDTO);
            result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
            return result;
        }
        
		// Save to database
		ParameterObject updateObjectParam = new ParameterObject();
		updateObjectParam.setIdLogin(param.getUvo().getId_user());
		
		//Compare with OldData,If the two match then do nothing Else Save the Data
		List<GSettingBean> listGlobalSettingBean = new ArrayList<GSettingBean>();
		listGlobalSettingBean = queryDAO.executeForObjectList(
                "M_GBS.selectGlobalSettings", null, 0,
                Integer.MAX_VALUE);
		 String dashbd1="";
		 String usrmnt1="";
		 String pwdch1="";
		 String loginat1="";
		 String loginat2="";
		 String resultrow1="";
		 String filesizeupload1="";
		 String servicerow1="";
		 String desclength1="";
		 String batchmsg1="";
		 String batchmsg2="";
		 String batchmsg3="";
		 String bildebteracc1="";
		 String biladvancedebteracc1="";
		 String gst1="";
		 String invoicedueperiod1="";
		 String noprintdoc1="";
		 String billdocfooter1="";
		 String billdocfooter2="";
		 String billdocfooter3="";
		 String billdocfooter4="";
		 String billdocfooter5="";
		 String billdocfooter6="";
		 String billdocfooter7="";
		 String billdocfooter8="";
		 String billdocfooter9="";
		 String billdocfooter10="";
		 String billdocfooter11="";
		 String billdocfooter12="";
		 String billdocfooter13="";
		 String billdocfooter14="";
		 String billdocfooter15="";
		 String bookautooffset="";
		 String batchrundateentry="";
		 String sharedfolder="";
		 String fileloc="";
		 String batchggirp021="";
		 String batchgsgpp021="";
		 String batchgipsp011="";
		 String batchgclcp011="";
		 String batchgcitp011="";
		 String batchggirp011="";
		 String batchgsgpp011="";
		 String batchgrptar011="";
		 String batcheexpf011="";
		 String batchgcsbp01="";
		 String batchrrevp01="";
		for (GSettingBean settingBean : listGlobalSettingBean) {
		    // Update Dashboard
            if(settingBean.getIdSet().equalsIgnoreCase("DASHBD")&&settingBean.getSetSeq()==1) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    dashbd1 = settingBean.getSetValue();
                }
            }
            // Update User Maintenance
            if(settingBean.getIdSet().equalsIgnoreCase("USRMNT")&&settingBean.getSetSeq()==1) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    usrmnt1 = settingBean.getSetValue();
                }
            }
            // Update Password Change Period
            if(settingBean.getIdSet().equalsIgnoreCase("PWDCH")&&settingBean.getSetSeq()==1) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    pwdch1 = settingBean.getSetValue();
                }
            }
            // Update Login Attempts 1
            if(settingBean.getIdSet().equalsIgnoreCase("LOGINAT")&&settingBean.getSetSeq()==1) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    loginat1 = settingBean.getSetValue();
                }
            }
            // Update Login Attempts 2
            if(settingBean.getIdSet().equalsIgnoreCase("LOGINAT")&&settingBean.getSetSeq()==2) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    loginat2 = settingBean.getSetValue();
                }
            }
            // Update Row Result Display
            if(settingBean.getIdSet().equalsIgnoreCase("RESULTROW")&&settingBean.getSetSeq()==1) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    resultrow1 = settingBean.getSetValue();
                }
            }
            // Update Limit For File Size Upload
            if(settingBean.getIdSet().equalsIgnoreCase("FILESIZEUPLOAD")&&settingBean.getSetSeq()==1) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    filesizeupload1 = settingBean.getSetValue();
                }
            }
            // Update Service Row
            if(settingBean.getIdSet().equalsIgnoreCase("SERVICEROW")&&settingBean.getSetSeq()==1) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    servicerow1 = settingBean.getSetValue();
                }
            }
            // Update Display Description Length
            if(settingBean.getIdSet().equalsIgnoreCase("DESC_LENGTH")&&settingBean.getSetSeq()==1) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    desclength1 = settingBean.getSetValue();
                }
            }
            // Update Batch Message
            if(settingBean.getIdSet().equalsIgnoreCase("BATCH_MSG")&&settingBean.getSetSeq()==1) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    batchmsg1 = settingBean.getSetValue();
                }
            }
            // Update Batch Message 2
            if(settingBean.getIdSet().equalsIgnoreCase("BATCH_MSG")&&settingBean.getSetSeq()==2) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    batchmsg2 = settingBean.getSetValue();
                }
            }
            // Update Batch Message 3
            if(settingBean.getIdSet().equalsIgnoreCase("BATCH_MSG")&&settingBean.getSetSeq()==3) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    batchmsg3 = settingBean.getSetValue();
                }
            }
            // TAB 2
            // Update Debter Account
            if(settingBean.getIdSet().equalsIgnoreCase("BIL_DEBTER_ACC")&&settingBean.getSetSeq()==1) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    bildebteracc1 = settingBean.getSetValue();
                }
            }
            
            //Update Advance Debter Account
            if(settingBean.getIdSet().equalsIgnoreCase("BIL_ADVANCE_ACC")&&settingBean.getSetSeq()==1) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    biladvancedebteracc1 = settingBean.getSetValue();
                }
            }
            // Update Government Service Tax
            if(settingBean.getIdSet().equalsIgnoreCase("GST")&&settingBean.getSetSeq()==1) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    gst1 = settingBean.getSetValue();
                }
            }
            // Update Invoice Due Period
            if(settingBean.getIdSet().equalsIgnoreCase("INVOICE_DUE_PERIOD")&&settingBean.getSetSeq()==1) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    invoicedueperiod1 = settingBean.getSetValue();
                }
            }
            // Update Number of Time to Print Billing Document
            if(settingBean.getIdSet().equalsIgnoreCase("NOPRINTDOC")&&settingBean.getSetSeq()==1) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    noprintdoc1 = settingBean.getSetValue();
                }
            }
            // Update Billing Document Footer Display 1
            if(settingBean.getIdSet().equalsIgnoreCase("BILL_DOC_FOOTER")&&settingBean.getSetSeq()==1) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    billdocfooter1 = settingBean.getSetValue();
                }
            }
            // Update Billing Document Footer Display 2
            if(settingBean.getIdSet().equalsIgnoreCase("BILL_DOC_FOOTER")&&settingBean.getSetSeq()==2) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    billdocfooter2 = settingBean.getSetValue();
                }
            }
            // Update Billing Document Footer Display 3
            if(settingBean.getIdSet().equalsIgnoreCase("BILL_DOC_FOOTER")&&settingBean.getSetSeq()==3) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    billdocfooter3 = settingBean.getSetValue();
                }
            }
            // Update Billing Document Footer Display 4
            if(settingBean.getIdSet().equalsIgnoreCase("BILL_DOC_FOOTER")&&settingBean.getSetSeq()==4) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    billdocfooter4 = settingBean.getSetValue();
                }
            }
            // Update Billing Document Footer Display 5
            if(settingBean.getIdSet().equalsIgnoreCase("BILL_DOC_FOOTER")&&settingBean.getSetSeq()==5) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    billdocfooter5 = settingBean.getSetValue();
                }
            }
            // Update Billing Document Footer Display 6
            if(settingBean.getIdSet().equalsIgnoreCase("BILL_DOC_FOOTER")&&settingBean.getSetSeq()==6) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    billdocfooter6 = settingBean.getSetValue();
                }
            }
            // Update Billing Document Footer Display 7
            if(settingBean.getIdSet().equalsIgnoreCase("BILL_DOC_FOOTER")&&settingBean.getSetSeq()==7) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    billdocfooter7 = settingBean.getSetValue();
                }
            }
            // Update Billing Document Footer Display 8
            if(settingBean.getIdSet().equalsIgnoreCase("BILL_DOC_FOOTER")&&settingBean.getSetSeq()==8) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    billdocfooter8 = settingBean.getSetValue();
                }
            }
            // Update Billing Document Footer Display 9
            if(settingBean.getIdSet().equalsIgnoreCase("BILL_DOC_FOOTER")&&settingBean.getSetSeq()==9) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    billdocfooter9 = settingBean.getSetValue();
                }
            }
            // Update Billing Document Footer Display 10  
            if(settingBean.getIdSet().equalsIgnoreCase("BILL_DOC_FOOTER")&&settingBean.getSetSeq()==10) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    billdocfooter10 = settingBean.getSetValue();
                }
            }
            // Update Billing Document Footer Display 11
            if(settingBean.getIdSet().equalsIgnoreCase("BILL_DOC_FOOTER")&&settingBean.getSetSeq()==11) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    billdocfooter11 = settingBean.getSetValue();
                }
            }
            // Update Billing Document Footer Display 12
            if(settingBean.getIdSet().equalsIgnoreCase("BILL_DOC_FOOTER")&&settingBean.getSetSeq()==12) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    billdocfooter12 = settingBean.getSetValue();
                }
            }
            // Update Billing Document Footer Display 13
            if(settingBean.getIdSet().equalsIgnoreCase("BILL_DOC_FOOTER")&&settingBean.getSetSeq()==13) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    billdocfooter13 = settingBean.getSetValue();
                }
            }
            // Update Billing Document Footer Display 14
            if(settingBean.getIdSet().equalsIgnoreCase("BILL_DOC_FOOTER")&&settingBean.getSetSeq()==14) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    billdocfooter14 = settingBean.getSetValue();
                }
            }
            // Update Billing Document Footer Display 15
            if(settingBean.getIdSet().equalsIgnoreCase("BILL_DOC_FOOTER")&&settingBean.getSetSeq()==15) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    billdocfooter15 = settingBean.getSetValue();
                }
            }
            // Update Cash book auto offset
            if(settingBean.getIdSet().equalsIgnoreCase("CB_AUTO_OFFSET")&&settingBean.getSetSeq()==1) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    bookautooffset = settingBean.getSetValue();
                }
            }
            // Update Batch run Date entry
            if(settingBean.getIdSet().equalsIgnoreCase("ESET_RUNDATE")&&settingBean.getSetSeq()==1) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    batchrundateentry = settingBean.getSetValue();
                }
            }
            // shared folder
            if(settingBean.getIdSet().equalsIgnoreCase("SHAREDFOLDER")&&settingBean.getSetSeq()==1) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    sharedfolder = settingBean.getSetValue();
                }
            }
            // File Location
            if(settingBean.getIdSet().equalsIgnoreCase("FILELOC")&&settingBean.getSetSeq()==1) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    fileloc = settingBean.getSetValue();
                }
            }
            // Update Batch Import - Location for SMBC Giro Collection Data file
            if(settingBean.getIdSet().equalsIgnoreCase("BATCH_G_GIR_P02")&&settingBean.getSetSeq()==1) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    batchggirp021 = settingBean.getSetValue();
                }
            }
            // Update Batch Import - Location for SingPost Collection Data file
            if(settingBean.getIdSet().equalsIgnoreCase("BATCH_G_SGP_P02")&&settingBean.getSetSeq()==1) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    batchgsgpp021 = settingBean.getSetValue();
                }
            }
            // Update Batch Import - Location for IPASS file
            if(settingBean.getIdSet().equalsIgnoreCase("BATCH_G_IPS_P01")&&settingBean.getSetSeq()==1) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    batchgipsp011 = settingBean.getSetValue();
                }
            }
            // Update Batch Import - Location for CLEAR Call file
            if(settingBean.getIdSet().equalsIgnoreCase("BATCH_G_CLC_P01")&&settingBean.getSetSeq()==1) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    batchgclcp011 = settingBean.getSetValue();
                }
            }
            // Update Batch Export - Location for Citibank Credit Data file
            if(settingBean.getIdSet().equalsIgnoreCase("BATCH_G_CIT_P01")&&settingBean.getSetSeq()==1) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    batchgcitp011 = settingBean.getSetValue();
                }
            }
            // Update Batch Export - Location for SMBC GIRO Invoice Data file
            if(settingBean.getIdSet().equalsIgnoreCase("BATCH_G_GIR_P01")&&settingBean.getSetSeq()==1) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    batchggirp011 = settingBean.getSetValue();
                }
            }
            // Update Batch Export - Location for SingPost Collection Data file
            if(settingBean.getIdSet().equalsIgnoreCase("BATCH_G_SGP_P01")&&settingBean.getSetSeq()==1) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    batchgsgpp011 = settingBean.getSetValue();
                }
            }
            // Update Batch Export - Location for AR Statement file
            if(settingBean.getIdSet().equalsIgnoreCase("BATCH_G_RPT_AR01")&&settingBean.getSetSeq()==1) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    batchgrptar011 = settingBean.getSetValue();
                }
            }
            // Update Batch Export - Location for PeopleSoft file
            if(settingBean.getIdSet().equalsIgnoreCase("BATCH_E_EXP_F01")&&settingBean.getSetSeq()==1) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    batcheexpf011 = settingBean.getSetValue();
                }
            }
            // Update Batch Export - Location for auto offset cashbook result file
            if(settingBean.getIdSet().equalsIgnoreCase("BATCH_G_CSB_P01")&&settingBean.getSetSeq()==1) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    batchgcsbp01 = settingBean.getSetValue();
                }
            }
            /*BATCH_E_EML_P01*/
            // Update Batch Export - Location for Email Billing Document PDF
            if(settingBean.getIdSet().equalsIgnoreCase("BATCH_E_EML_P01")&&settingBean.getSetSeq()==1) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    batchgcsbp01 = settingBean.getSetValue();
                }
            }
            /*BATCH_R_REV_P01*/
            // Update Revenue Report - Location for Revenue Report
            if(settingBean.getIdSet().equalsIgnoreCase("BATCH_R_REV_P01")&&settingBean.getSetSeq()==1) {
                if (settingBean.getSetValue() != null && !settingBean.getSetValue().equals("")) {
                    batchrrevp01 = settingBean.getSetValue();
                }
            }
        }
		
		// TAB 1
		// Update Dashboard
		if ("1".equals(param.getDispDASHBD1()))
		{
            if (dashbd1.equals(param.getTbxValueDASHBD1())==false) {
                updateObjectParam.setIdSet("DASHBD");
                updateObjectParam.setSetSeq(1);
                updateObjectParam.setSetValue(param.getTbxValueDASHBD1());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}

		// Update User Maintenance
		if ("1".equals(param.getDispUSRMNT1()))
		{
            if (usrmnt1.equals(param.getTbxValueUSRMNT1()) == false) {
                updateObjectParam.setIdSet("USRMNT");
                updateObjectParam.setSetSeq(1);
                updateObjectParam.setSetValue(param.getTbxValueUSRMNT1());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}

		// Update Password Change Period
		if ("1".equals(param.getDispPWDCH1()))
		{
            if (pwdch1.equals(param.getTbxValuePWDCH1()) == false) {
                updateObjectParam.setIdSet("PWDCH");
                updateObjectParam.setSetSeq(1);
                updateObjectParam.setSetValue(param.getTbxValuePWDCH1());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}

		// Update Login Attempts 1
		if ("1".equals(param.getDispLOGINAT1()))
		{
            if (loginat1.equals(param.getTbxValueLOGINAT1()) == false) {
                updateObjectParam.setIdSet("LOGINAT");
                updateObjectParam.setSetSeq(1);
                updateObjectParam.setSetValue(param.getTbxValueLOGINAT1());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}

		// Update Login Attempts 2
		if ("1".equals(param.getDispLOGINAT2()))
		{
            if (loginat2.equals(param.getTbxValueLOGINAT2()) == false) {
                updateObjectParam.setIdSet("LOGINAT");
                updateObjectParam.setSetSeq(2);
                updateObjectParam.setSetValue(param.getTbxValueLOGINAT2());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}

		// Update Row Result Display
		if ("1".equals(param.getDispRESULTROW1()))
		{
            if (resultrow1.equals(param.getTbxValueRESULTROW1()) == false) {
                updateObjectParam.setIdSet("RESULTROW");
                updateObjectParam.setSetSeq(1);
                updateObjectParam.setSetValue(param.getTbxValueRESULTROW1());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}

		// Update Limit For File Size Upload
		if ("1".equals(param.getDispFILESIZEUPLOAD1()))
		{
            if (filesizeupload1.equals(param.getTbxValueFILESIZEUPLOAD1()) == false) {
                updateObjectParam.setIdSet("FILESIZEUPLOAD");
                updateObjectParam.setSetSeq(1);
                updateObjectParam.setSetValue(param.getTbxValueFILESIZEUPLOAD1());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}

		// Update Service Row
		if ("1".equals(param.getDispSERVICEROW1()))
		{
            if (servicerow1.equals(param.getTbxValueSERVICEROW1()) == false) {
                updateObjectParam.setIdSet("SERVICEROW");
                updateObjectParam.setSetSeq(1);
                updateObjectParam.setSetValue(param.getTbxValueSERVICEROW1());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}

		// Update Display Description Length
		if ("1".equals(param.getDispDESC_LENGTH1()))
		{
            if (desclength1.equals(param.getTbxValueDESC_LENGTH1()) == false) {
                updateObjectParam.setIdSet("DESC_LENGTH");
                updateObjectParam.setSetSeq(1);
                updateObjectParam.setSetValue(param.getTbxValueDESC_LENGTH1());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}

		// Update Batch Message
		if ("1".equals(param.getDispBATCH_MSG1()))
		{
            if (batchmsg1.equals(param.getTbxValueBATCH_MSG1()) == false) {
                updateObjectParam.setIdSet("BATCH_MSG");
                updateObjectParam.setSetSeq(1);
                updateObjectParam.setSetValue(param.getTbxValueBATCH_MSG1());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}
		
		//add for enhancement: begin
		// Update Batch Message 2
		if ("1".equals(param.getDispBATCH_MSG2()))
		{
            if (batchmsg2.equals(param.getTbxValueBATCH_MSG2()) == false) {
                updateObjectParam.setIdSet("BATCH_MSG");
                updateObjectParam.setSetSeq(2);
                updateObjectParam.setSetValue(param.getTbxValueBATCH_MSG2());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}
		
		// Update Batch Message 3
		if ("1".equals(param.getDispBATCH_MSG3()))
		{
            if (batchmsg3.equals(param.getTbxValueBATCH_MSG3()) == false) {
                updateObjectParam.setIdSet("BATCH_MSG");
                updateObjectParam.setSetSeq(3);
                updateObjectParam.setSetValue(param.getTbxValueBATCH_MSG3());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}
		//add for enhancement: end
		
		
		// TAB 2
		// Update Debter Account
		if ("1".equals(param.getDispBIL_DEBTER_ACC1()))
		{
            if (bildebteracc1.equals(param.getTbxValueBIL_DEBTER_ACC1())==false) {
                updateObjectParam.setIdSet("BIL_DEBTER_ACC");
                updateObjectParam.setSetSeq(1);
                updateObjectParam.setSetValue(param.getTbxValueBIL_DEBTER_ACC1());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}

		// Update Advance Debter Account 
		if ("1".equals(param.getDispBIL_ADVANCE_DEBTER_ACC1()))
        {
		    if (biladvancedebteracc1.equals(param.getTbxValueBIL_ADVANCE_DEBTER_ACC1())==false) {
                updateObjectParam.setIdSet("BIL_ADVANCE_ACC");
                updateObjectParam.setSetSeq(1);
                updateObjectParam.setSetValue(param.getTbxValueBIL_ADVANCE_DEBTER_ACC1());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
        }
		// Update Government Service Tax
		if ("1".equals(param.getDispGST1()))
		{
            if (gst1.equals(param.getTbxValueGST1()) == false) {
                updateObjectParam.setIdSet("GST");
                updateObjectParam.setSetSeq(1);
                updateObjectParam.setSetValue(param.getTbxValueGST1());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}

		// Update Invoice Due Period
		if ("1".equals(param.getDispINVOICE_DUE_PERIOD1()))
		{
            if (invoicedueperiod1.equals(param.getTbxValueINVOICE_DUE_PERIOD1()) == false) {
                updateObjectParam.setIdSet("INVOICE_DUE_PERIOD");
                updateObjectParam.setSetSeq(1);
                updateObjectParam.setSetValue(param.getTbxValueINVOICE_DUE_PERIOD1());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}

		// Update Number of Time to Print Billing Document
		if ("1".equals(param.getDispNOPRINTDOC1()))
		{
            if (noprintdoc1.equals(param.getTbxValueNOPRINTDOC1()) == false) {
                updateObjectParam.setIdSet("NOPRINTDOC");
                updateObjectParam.setSetSeq(1);
                updateObjectParam.setSetValue(param.getTbxValueNOPRINTDOC1());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}
		
		// Update Billing Document Footer Display 1
		if ("1".equals(param.getDispBILL_DOC_FOOTER1()))
		{
            if (billdocfooter1.equals(param.getTbxValueBILL_DOC_FOOTER1()) == false) {
                updateObjectParam.setIdSet("BILL_DOC_FOOTER");
                updateObjectParam.setSetSeq(1);
                updateObjectParam.setSetValue(param.getTbxValueBILL_DOC_FOOTER1());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}
		
		// Update Billing Document Footer Display 2
		if ("1".equals(param.getDispBILL_DOC_FOOTER2()))
		{
            if (billdocfooter2.equals(param.getTbxValueBILL_DOC_FOOTER2()) == false) {
                updateObjectParam.setIdSet("BILL_DOC_FOOTER");
                updateObjectParam.setSetSeq(2);
                updateObjectParam.setSetValue(param.getTbxValueBILL_DOC_FOOTER2());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}
		
		// Update Billing Document Footer Display 3
		if ("1".equals(param.getDispBILL_DOC_FOOTER3()))
		{
            if (billdocfooter3.equals(param.getTbxValueBILL_DOC_FOOTER3()) == false) {
                updateObjectParam.setIdSet("BILL_DOC_FOOTER");
                updateObjectParam.setSetSeq(3);
                updateObjectParam.setSetValue(param.getTbxValueBILL_DOC_FOOTER3());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}
		
		// Update Billing Document Footer Display 4
		if ("1".equals(param.getDispBILL_DOC_FOOTER4()))
		{
            if (billdocfooter4.equals(param.getTbxValueBILL_DOC_FOOTER4()) == false) {
                updateObjectParam.setIdSet("BILL_DOC_FOOTER");
                updateObjectParam.setSetSeq(4);
                updateObjectParam.setSetValue(param.getTbxValueBILL_DOC_FOOTER4());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}
		
		// Update Billing Document Footer Display 5
		if ("1".equals(param.getDispBILL_DOC_FOOTER5()))
		{
            if (billdocfooter5.equals(param.getTbxValueBILL_DOC_FOOTER5()) == false) {
                updateObjectParam.setIdSet("BILL_DOC_FOOTER");
                updateObjectParam.setSetSeq(5);
                updateObjectParam.setSetValue(param.getTbxValueBILL_DOC_FOOTER5());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}
		
		// Update Billing Document Footer Display 6
		if ("1".equals(param.getDispBILL_DOC_FOOTER6()))
		{
            if (billdocfooter6.equals(param.getTbxValueBILL_DOC_FOOTER6()) == false) {
                updateObjectParam.setIdSet("BILL_DOC_FOOTER");
                updateObjectParam.setSetSeq(6);
                updateObjectParam.setSetValue(param.getTbxValueBILL_DOC_FOOTER6());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}
		
		// Update Billing Document Footer Display 7
		if ("1".equals(param.getDispBILL_DOC_FOOTER7()))
		{
            if (billdocfooter7.equals(param.getTbxValueBILL_DOC_FOOTER7()) == false) {
                updateObjectParam.setIdSet("BILL_DOC_FOOTER");
                updateObjectParam.setSetSeq(7);
                updateObjectParam.setSetValue(param.getTbxValueBILL_DOC_FOOTER7());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}
		
		// Update Billing Document Footer Display 8
		if ("1".equals(param.getDispBILL_DOC_FOOTER8()))
		{
            if (billdocfooter8.equals(param.getTbxValueBILL_DOC_FOOTER8()) == false) {
                updateObjectParam.setIdSet("BILL_DOC_FOOTER");
                updateObjectParam.setSetSeq(8);
                updateObjectParam.setSetValue(param.getTbxValueBILL_DOC_FOOTER8());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}
		
		// Update Billing Document Footer Display 9
		if ("1".equals(param.getDispBILL_DOC_FOOTER9()))
		{
            if (billdocfooter9.equals(param.getTbxValueBILL_DOC_FOOTER9()) == false) {
                updateObjectParam.setIdSet("BILL_DOC_FOOTER");
                updateObjectParam.setSetSeq(9);
                updateObjectParam.setSetValue(param.getTbxValueBILL_DOC_FOOTER9());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}
		
		// Update Billing Document Footer Display 10
		if ("1".equals(param.getDispBILL_DOC_FOOTER10()))
		{
            if (billdocfooter10.equals(param.getTbxValueBILL_DOC_FOOTER10()) == false) {
                updateObjectParam.setIdSet("BILL_DOC_FOOTER");
                updateObjectParam.setSetSeq(10);
                updateObjectParam.setSetValue(param.getTbxValueBILL_DOC_FOOTER10());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}
		
		// Update Billing Document Footer Display 11
		if ("1".equals(param.getDispBILL_DOC_FOOTER11()))
		{
            if (billdocfooter11.equals(param.getTbxValueBILL_DOC_FOOTER11()) == false) {
                updateObjectParam.setIdSet("BILL_DOC_FOOTER");
                updateObjectParam.setSetSeq(11);
                updateObjectParam.setSetValue(param.getTbxValueBILL_DOC_FOOTER11());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}
		
		// Update Billing Document Footer Display 12
		if ("1".equals(param.getDispBILL_DOC_FOOTER12()))
		{
            if (billdocfooter12.equals(param.getTbxValueBILL_DOC_FOOTER12()) == false) {
                updateObjectParam.setIdSet("BILL_DOC_FOOTER");
                updateObjectParam.setSetSeq(12);
                updateObjectParam.setSetValue(param.getTbxValueBILL_DOC_FOOTER12());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}
		
		// Update Billing Document Footer Display 13
		if ("1".equals(param.getDispBILL_DOC_FOOTER13()))
		{
            if (billdocfooter13.equals(param.getTbxValueBILL_DOC_FOOTER13()) == false) {
                updateObjectParam.setIdSet("BILL_DOC_FOOTER");
                updateObjectParam.setSetSeq(13);
                updateObjectParam.setSetValue(param.getTbxValueBILL_DOC_FOOTER13());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}
		
		// Update Billing Document Footer Display 14
		if ("1".equals(param.getDispBILL_DOC_FOOTER14()))
		{
            if (billdocfooter14.equals(param.getTbxValueBILL_DOC_FOOTER14()) == false) {
                updateObjectParam.setIdSet("BILL_DOC_FOOTER");
                updateObjectParam.setSetSeq(14);
                updateObjectParam.setSetValue(param.getTbxValueBILL_DOC_FOOTER14());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}
		
		// Update Billing Document Footer Display 15
		if ("1".equals(param.getDispBILL_DOC_FOOTER15()))
		{
            if (billdocfooter15.equals(param.getTbxValueBILL_DOC_FOOTER15()) == false) {
                updateObjectParam.setIdSet("BILL_DOC_FOOTER");
                updateObjectParam.setSetSeq(15);
                updateObjectParam.setSetValue(param.getTbxValueBILL_DOC_FOOTER15());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}
		
		// Update Cash book auto offset
		if ("1".equals(param.getDispCashBookAutoOffset()))
		{
            if (bookautooffset.equals(param.getRdbCashBookAutoOffset()) == false) {
                updateObjectParam.setIdSet("CB_AUTO_OFFSET");
                updateObjectParam.setSetSeq(1);
                updateObjectParam.setSetValue(param.getRdbCashBookAutoOffset());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}
		
		// Update Batch run Date entry
		if ("1".equals(param.getDispBatchRunDateEntry()))
		{
            if (batchrundateentry.equals(param.getRdbBatchRunDateEntry()) == false) {
                updateObjectParam.setIdSet("ESET_RUNDATE");
                updateObjectParam.setSetSeq(1);
                updateObjectParam.setSetValue(param.getRdbBatchRunDateEntry());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}
		
		
		// TAB 3
		// shared folder 
		if ("1".equals(param.getDispSHAREDFOLDER()))
		{
            if (sharedfolder.equals(param.getTbxValueSHAREDFOLDER()) == false) {
                updateObjectParam.setIdSet("SHAREDFOLDER");
                updateObjectParam.setSetSeq(1);
                updateObjectParam.setSetValue(param.getTbxValueSHAREDFOLDER());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}
		
		// File Location
		if ("1".equals(param.getDispFILELOC1()))
		{
            if (fileloc.equals(param.getTbxValueFILELOC1()) == false) {
                updateObjectParam.setIdSet("FILELOC");
                updateObjectParam.setSetSeq(1);
                updateObjectParam.setSetValue(param.getTbxValueFILELOC1());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}

		// Update Batch Import - Location for SMBC Giro Collection Data file
		if ("1".equals(param.getDispBATCH_G_GIR_P021()))
		{
            if (batchggirp021.equals(param.getTbxValueBATCH_G_GIR_P021()) == false) {
                updateObjectParam.setIdSet("BATCH_G_GIR_P02");
                updateObjectParam.setSetSeq(1);
                updateObjectParam.setSetValue(param.getTbxValueBATCH_G_GIR_P021());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}

		// Update Batch Import - Location for SingPost Collection Data file
		if ("1".equals(param.getDispBATCH_G_SGP_P021()))
		{
            if (batchgsgpp021.equals(param.getTbxValueBATCH_G_SGP_P021()) == false) {
                updateObjectParam.setIdSet("BATCH_G_SGP_P02");
                updateObjectParam.setSetSeq(1);
                updateObjectParam.setSetValue(param.getTbxValueBATCH_G_SGP_P021());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}
		
		// Update Batch Import - Location for IPASS file
		if ("1".equals(param.getDispBATCH_G_IPS_P011()))
		{
            if (batchgipsp011.equals(param.getTbxValueBATCH_G_IPS_P011()) == false) {
                updateObjectParam.setIdSet("BATCH_G_IPS_P01");
                updateObjectParam.setSetSeq(1);
                updateObjectParam.setSetValue(param.getTbxValueBATCH_G_IPS_P011());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}
		
		// Update Batch Import - Location for CLEAR Call file
		if ("1".equals(param.getDispBATCH_G_CLC_P011()))
		{
            if (batchgclcp011.equals(param.getTbxValueBATCH_G_CLC_P011()) == false) {
                updateObjectParam.setIdSet("BATCH_G_CLC_P01");
                updateObjectParam.setSetSeq(1);
                updateObjectParam.setSetValue(param.getTbxValueBATCH_G_CLC_P011());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}
		
		// Update Batch Export - Location for Citibank Credit Data file
		if ("1".equals(param.getDispBATCH_G_CIT_P011()))
		{
            if (batchgcitp011.equals(param.getTbxValueBATCH_G_CIT_P011()) == false) {
                updateObjectParam.setIdSet("BATCH_G_CIT_P01");
                updateObjectParam.setSetSeq(1);
                updateObjectParam.setSetValue(param.getTbxValueBATCH_G_CIT_P011());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}
		
		// Update Batch Export - Location for SMBC GIRO Invoice Data file
		if ("1".equals(param.getDispBATCH_G_GIR_P011()))
		{
            if (batchggirp011.equals(param.getTbxValueBATCH_G_GIR_P011()) == false) {
                updateObjectParam.setIdSet("BATCH_G_GIR_P01");
                updateObjectParam.setSetSeq(1);
                updateObjectParam.setSetValue(param.getTbxValueBATCH_G_GIR_P011());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}
		
		// Update Batch Export - Location for SingPost Collection Data file
		if ("1".equals(param.getDispBATCH_G_SGP_P011()))
		{
            if (batchgsgpp011.equals(param.getTbxValueBATCH_G_SGP_P011()) == false) {
                updateObjectParam.setIdSet("BATCH_G_SGP_P01");
                updateObjectParam.setSetSeq(1);
                updateObjectParam.setSetValue(param.getTbxValueBATCH_G_SGP_P011());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}
		
		// Update Batch Export - Location for AR Statement file
		if ("1".equals(param.getDispBATCH_G_RPT_AR011())) //if setApply=0, BATCH_G_RPT_AR01 is not displayed, so not update
		{
            if (batchgrptar011.equals(param.getTbxValueBATCH_G_RPT_AR011()) == false) {
                updateObjectParam.setIdSet("BATCH_G_RPT_AR01");
                updateObjectParam.setSetSeq(1);
                updateObjectParam.setSetValue(param.getTbxValueBATCH_G_RPT_AR011());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}
		
		// Update Batch Export - Location for PeopleSoft file
		if ("1".equals(param.getDispBATCH_E_EXP_F011())) //if setApply=0, BATCH_E_EXP_F011 is not displayed, so not update
		{
            if (batcheexpf011.equals(param.getTbxValueBATCH_E_EXP_F011()) == false) {
                updateObjectParam.setIdSet("BATCH_E_EXP_F01");
                updateObjectParam.setSetSeq(1);
                updateObjectParam.setSetValue(param.getTbxValueBATCH_E_EXP_F011());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
		}
		
	    // Update Batch Export - Location for auto offset cashbook result file
        if ("1".equals(param.getDispBATCH_G_CSB_P01())) 
        {
            if (batchgcsbp01.equals(param.getTbxValueBATCH_G_CSB_P01()) == false) {
                updateObjectParam.setIdSet("BATCH_G_CSB_P01");
                updateObjectParam.setSetSeq(1);
                updateObjectParam.setSetValue(param.getTbxValueBATCH_G_CSB_P01());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
        }
        /*BATCH_E_EML_P01*/
        if ("1".equals(param.getDispBATCH_E_EML_P01())) 
        {
            if (batchgcsbp01.equals(param.getTbxValueBATCH_E_EML_P01()) == false) {
                updateObjectParam.setIdSet("BATCH_E_EML_P01");
                updateObjectParam.setSetSeq(1);
                updateObjectParam.setSetValue(param.getTbxValueBATCH_E_EML_P01());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
        }
        /*BATCH_R_REV_P01*/
        if ("1".equals(param.getDispBATCH_R_REV_P01())) 
        {
            if (batchrrevp01.equals(param.getTbxValueBATCH_R_REV_P01()) == false) {
                updateObjectParam.setIdSet("BATCH_R_REV_P01");
                updateObjectParam.setSetSeq(1);
                updateObjectParam.setSetValue(param.getTbxValueBATCH_R_REV_P01());
                auditBegin(updateObjectParam);
                updateDAO.execute("M_GBS.updateGlobalSetting", updateObjectParam);
                auditEnd(updateObjectParam);
            }
        }
		
		M_GBSS01_02Output outputDTO = new M_GBSS01_02Output();
		try {
			BeanUtils.copyProperties(outputDTO, param);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Update is successed
		outputDTO.setIsUpdateSuccessed(true);
		
		result.setResultObject(outputDTO);
		result.setResultString("success");
		return result;
	}
	
	/**
	 * Audit Trail
	 */
	private void auditBegin(ParameterObject updateObjectParam) {
		Integer idAudit = CommonUtils.auditTrailBegin(updateObjectParam.getIdLogin(), 
				BillingSystemConstants.MODULE_M, BillingSystemConstants.SUB_MODULE_M_GBS, 
				updateObjectParam.getIdSet(), null, BillingSystemConstants.AUDIT_TRAIL_EDITED);
		updateObjectParam.setIdAudit(idAudit);
	}
	
	//end Audit Trail
	private void auditEnd(ParameterObject updateObjectParam) {
		CommonUtils.auditTrailEnd(updateObjectParam.getIdAudit(), true);
	}
	
	private boolean inputIsMulCharCheck(M_GBSS01_02Input param) {
        boolean isMulCharFlg = true;
        String isCheckMulCharFlg = getIsCheckMulCharFlg();
        if ("1".equals(isCheckMulCharFlg)) {
            // Batch Message
            if ("1".equals(param.getDispBATCH_MSG1())) {
                String batchMsg1 = CommonUtils.toString(param.getTbxValueBATCH_MSG1());
                if (CommonUtils.isMulChar(batchMsg1)) {
                    isMulCharFlg = false;
                    errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104", new Object[]{"Batch Message: SingPost message", CommonUtils.MUL_CHAR_STR}));
                }
            }
            
            // Batch Message 2
            if ("1".equals(param.getDispBATCH_MSG2())) {
                String batchMsg2 = CommonUtils.toString(param.getTbxValueBATCH_MSG2());
                if (CommonUtils.isMulChar(batchMsg2)) {
                    isMulCharFlg = false;
                    errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104", new Object[]{"Batch Message: GIRO message", CommonUtils.MUL_CHAR_STR}));
                }
            }
            
            // Batch Message 3
            if ("1".equals(param.getDispBATCH_MSG3())) {
                String batchMsg3 = CommonUtils.toString(param.getTbxValueBATCH_MSG3());
                if (CommonUtils.isMulChar(batchMsg3)) {
                    isMulCharFlg = false;
                    errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC104", new Object[]{"Batch Message: Citibank message", CommonUtils.MUL_CHAR_STR}));
                }
            }
        }
        return isMulCharFlg;
	}
	private String getIsCheckMulCharFlg() {
        String isCheckMulCharFlg = CommonUtils.toString(queryDAO.executeForObject("M_GBS.GET_IS_CHECK_MUL_CHAR", null, String.class)).trim();
        
        return isCheckMulCharFlg;
    }
}