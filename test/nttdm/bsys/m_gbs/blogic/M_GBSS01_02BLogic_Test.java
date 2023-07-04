/**
 * @(#)M_GBSS01_02BLogic_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created Apr 10, 2012
 * 
 * Copyright (c) 2012 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.m_gbs.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.m_gbs.dto.M_GBSS01_02Input;
import nttdm.bsys.m_gbs.dto.M_GBSS01_02Output;


/**
 * @author Joel Chin Yat Leng
 *
 */
public class M_GBSS01_02BLogic_Test extends AbstractUtilTest{

	private M_GBSS01_02BLogic blogic02;
	private M_GBSS01_02Input param;
	BLogicResult result;
	
	@Override
	protected void setUpData() throws Exception {
		// init test object
		blogic02 = new M_GBSS01_02BLogic();
		blogic02.setUpdateDAO(updateDAO);
		
		param = new M_GBSS01_02Input();
		result = new BLogicResult();
	}
	
	public void testExecute() throws Exception{
		
		BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
		param.setUvo(uvo);
		param.getUvo().setId_user("joel.chin");
		
		// ---------- Tab 1 : General ----------
		// Dashboard
		param.setDispDASHBD1("1");
		param.setTbxValueDASHBD1("10");
		// User Maintenance
		param.setDispUSRMNT1("1");
		param.setTbxValueUSRMNT1("999");
		// Password Change Period
		param.setDispPWDCH1("1");
		param.setTbxValuePWDCH1("90");
		// Login Attempts 1
		param.setDispLOGINAT1("1");
		param.setTbxValueLOGINAT1("3");
		// Login Attempts 2 
		param.setDispLOGINAT2("1");
		param.setTbxValueLOGINAT2("1");
		// Row Result Display
		param.setDispRESULTROW1("1");
		param.setTbxValueRESULTROW1("10");
		// Limit For File Size Upload
		param.setDispFILESIZEUPLOAD1("1");
		param.setTbxValueFILESIZEUPLOAD1("10000000");
		// Service Row
		param.setDispSERVICEROW1("1");
		param.setTbxValueSERVICEROW1("10");
		// Display Description Length
		param.setDispDESC_LENGTH1("1");
		param.setTbxValueDESC_LENGTH1("50");
		// Batch Message
		param.setDispBATCH_MSG1("1");
		param.setTbxValueBATCH_MSG1("(BATCH_MSG for Singpost)");
		// Batch Message 2
		param.setDispBATCH_MSG2("1");
		param.setTbxValueBATCH_MSG2("(BATCH_MSG for GIRO)");
		// Batch Message 3
		param.setDispBATCH_MSG3("1");
		param.setTbxValueBATCH_MSG3("(BATCH_MSG for Citibank)");
		
		// ---------- Tab 2 : Billing ----------
		// Debter Account
		param.setDispBIL_DEBTER_ACC1("1");
		param.setTbxValueBIL_DEBTER_ACC1("110300001");
		// Government Service Tax
		param.setDispGST1("1");
		param.setTbxValueGST1("7");
		// Invoice Due Period
		param.setDispINVOICE_DUE_PERIOD1("1");
		param.setTbxValueINVOICE_DUE_PERIOD1("15");
		// Number of Time to Print Billing Document
		param.setDispNOPRINTDOC1("1");
		param.setTbxValueNOPRINTDOC1("5");
		// Billing Document Footer Display 1
		param.setDispBILL_DOC_FOOTER1("1");
		param.setTbxValueBILL_DOC_FOOTER1("Note:");
		// Billing Document Footer Display 2
		param.setDispBILL_DOC_FOOTER2("1");
		param.setTbxValueBILL_DOC_FOOTER2("1.  Please quote the Invoice No. when making payment.");
		// Billing Document Footer Display 3
		param.setDispBILL_DOC_FOOTER3("1");
		param.setTbxValueBILL_DOC_FOOTER3("2.  Due date: within 30 days from the date of issue.");
		// Billing Document Footer Display 4
		param.setDispBILL_DOC_FOOTER4("1");
		param.setTbxValueBILL_DOC_FOOTER4("3.  This is a computer generated invoice. No signature is required.");
		// Billing Document Footer Display 5
		param.setDispBILL_DOC_FOOTER5("1");
		param.setTbxValueBILL_DOC_FOOTER5("4.  Payment by remittance:");
		// Billing Document Footer Display 6
		param.setDispBILL_DOC_FOOTER6("0");
		param.setTbxValueBILL_DOC_FOOTER6("");
		// Billing Document Footer Display 7
		param.setDispBILL_DOC_FOOTER7("0");
		param.setTbxValueBILL_DOC_FOOTER7("");
		// Billing Document Footer Display 8
		param.setDispBILL_DOC_FOOTER8("0");
		param.setTbxValueBILL_DOC_FOOTER8("");
		// Billing Document Footer Display 9
		param.setDispBILL_DOC_FOOTER9("0");
		param.setTbxValueBILL_DOC_FOOTER9("");
		// Billing Document Footer Display 10
		param.setDispBILL_DOC_FOOTER10("0");
		param.setTbxValueBILL_DOC_FOOTER10("");
		// Billing Document Footer Display 11
		param.setDispBILL_DOC_FOOTER11("0");
		param.setTbxValueBILL_DOC_FOOTER11("");
		// Billing Document Footer Display 12
		param.setDispBILL_DOC_FOOTER12("0");
		param.setTbxValueBILL_DOC_FOOTER12("");
		// Billing Document Footer Display 13
		param.setDispBILL_DOC_FOOTER13("0");
		param.setTbxValueBILL_DOC_FOOTER13("");
		// Billing Document Footer Display 14
		param.setDispBILL_DOC_FOOTER14("0");
		param.setTbxValueBILL_DOC_FOOTER14("");
		// Billing Document Footer Display 15
		param.setDispBILL_DOC_FOOTER15("0");
		param.setTbxValueBILL_DOC_FOOTER15("");
		// Cash book auto offset : BAC: BILLING ACCOUNT, CST: CUSTOMER
		param.setDispCashBookAutoOffset("1");
		param.setRdbCashBookAutoOffset("BAC");
		// Batch run Date entry : 1: Enable, 0: Disable
		param.setDispBatchRunDateEntry("1");
		param.setRdbBatchRunDateEntry("1");
				
		// ---------- Tab 3 : File Path ----------
		// File Location
		param.setDispFILELOC1("1");
		param.setTbxValueFILELOC1("\\\192.168.1.3\\OfficeSystem\\UploadFile\\");
		// Batch Import - Location for SMBC Giro Collection Data file
		param.setDispBATCH_G_GIR_P021("0");
		param.setTbxValueBATCH_G_GIR_P021("");
		// Batch Import - Location for SingPost Collection Data file
		param.setDispBATCH_G_SGP_P021("1");
		param.setTbxValueBATCH_G_SGP_P021("\\\192.168.1.3\\OfficeSystem\\BATCH\\G_SGP_P02\\");
		// Batch Import - Location for IPASS file
		param.setDispBATCH_G_IPS_P011("0");
		param.setTbxValueBATCH_G_IPS_P011("");
		// Batch Import - Location for CLEAR Call file
		param.setDispBATCH_G_CLC_P011("1");
		param.setTbxValueBATCH_G_CLC_P011("\\\192.168.1.3\\OfficeSystem\\BATCH\\G_CLC_P01\\");
		// Batch Export - Location for Citibank Credit Data file
		param.setDispBATCH_G_CIT_P011("1");
		param.setTbxValueBATCH_G_CIT_P011("\\\192.168.1.3\\OfficeSystem\\BATCH\\G_CIT_P01\\");
		// Batch Export - Location for SMBC GIRO Invoice Data file
		param.setDispBATCH_G_GIR_P011("1");
		param.setTbxValueBATCH_G_GIR_P011("\\\192.168.1.3\\OfficeSystem\\BATCH\\G_GIR_P01\\");
		// Batch Export - Location for SingPost Collection Data file
		param.setDispBATCH_G_SGP_P011("1");
		param.setTbxValueBATCH_G_SGP_P011("\\\192.168.1.3\\OfficeSystem\\BATCH\\G_SGP_P01\\");
		// Batch Export - Location for AR Statement file
		param.setDispBATCH_G_RPT_AR011("1");
		param.setTbxValueBATCH_G_RPT_AR011("\\\192.168.1.3\\OfficeSystem\\BATCH\\G_RPT_AR01\\");
		// Batch Export - Location for PeopleSoft file
		param.setDispBATCH_E_EXP_F011("0");
		param.setTbxValueBATCH_E_EXP_F011("");
		
		M_GBSS01_02Output outputDTO = new M_GBSS01_02Output();
		result = blogic02.execute(param);
		//System.out.println("M_GBS_S01 : Edit Global Setting : " + result.getResultString());
		System.out.println("M_SVG_S01 : Edit / New Category : " + result.getResultString());
	}
		
}
