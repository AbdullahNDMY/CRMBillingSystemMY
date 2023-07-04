/**
 * @(#)G_RPT_P01_Test.java
 * 
 * Billing System
 * 
 * Version 1.00
 * 
 * Created Sep 6, 2011
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import nttdm.bsys.common.util.dto.G_RPT_P01_Input;
import nttdm.bsys.common.util.dto.G_RPT_P01_Output;
/**
 * @author Joel
 *
 */
public class G_RPT_P01_Test extends AbstractUtilTest {
	
	private G_RPT_P01 gRptP01 = null;
	
	private G_RPT_P01_Input param = null;
	
	private G_RPT_P01_Output outputDTO = null;
	
	@Override
	protected void setUpData() throws Exception {
		// TODO Auto-generated method stub		
	}
	
	public void testExecuteReport() throws Exception {
		
		param = new G_RPT_P01_Input();
		outputDTO = new G_RPT_P01_Output();
		
		param.setUserId("joel.chin");
		// param.setReportType("SAL");	 
	    // param.setReportType("RRR");
	     param.setReportType("AGR");
	    // param.setReportType("ACR");
	    
        List<HashMap<String,Object>> testReport = new ArrayList<HashMap<String,Object>>() ;
        HashMap<String,Object> map = new HashMap<String,Object>();

     // dummy data applicable for all 4 reports to grab 
        map = new HashMap<String,Object>();
        map.put("DATE_REQ", "08/2011");
        map.put("CUST_NAME", "NTT Data Malaysia");
        map.put("SVC_DESC", "NTTDM");
        map.put("BILL_CURRENCY", "RM");
        map.put("ITEM_AMT", "100.10");
        testReport.add(map);
    
        map = new HashMap<String,Object>();
        map.put("DATE_REQ", "09/2011");
        map.put("CUST_NAME", "NTT Data VN");
        map.put("SVC_DESC", "NTTDVN");
        map.put("BILL_CURRENCY", "USD");
        map.put("ITEM_AMT", "100.20");
        map.put("AFFILIATE_CODE", "affi");
        map.put("ID_REF", "Referral");
        map.put("ID_CUST_PLAN", "BilSys");
        map.put("IS_SINGPOST", "no");
        map.put("PAY_METHOD", "GIRO");
        map.put("INVOICE_AMT", "10.00");
        map.put("GST_AMOUNT", "20.00");
        map.put("BILL_AMOUNT", "30.00");
        map.put("DATE_TRANS", "12/2011");
        map.put("REFERENCE", "NTTDM");
        map.put("PMT_METHOD", "CITI");
        map.put("COM_ACCT_NAME", "ABC Corp");
        map.put("CUR_CODE", "JPY");
        map.put("AMT_PAID", "4000");
        map.put("OUTSTANDING_AMOUNT", "50");
        testReport.add(map);   

		param.setListAgingReport(testReport);

		gRptP01 = new G_RPT_P01(super.queryDAO, super.updateDAO, super.updateDAONuked);
		
		GlobalProcessResult gpr = gRptP01.execute(param, outputDTO);
		assertNotNull(gpr.getFile());
		assertEquals(true,gpr.getErrors().isEmpty());
	}	
	
	public void testExecuteadd1() throws Exception {
		
		param = new G_RPT_P01_Input();
		outputDTO = new G_RPT_P01_Output();
		
		param.setUserId("joel.chin");
	    param.setReportType("SAL");	 
	    // param.setReportType("RRR");
	    // param.setReportType("AGR");
	    // param.setReportType("ACR");
	    
        List<HashMap<String,Object>> testReport = new ArrayList<HashMap<String,Object>>() ;
        HashMap<String,Object> map = new HashMap<String,Object>();

     // dummy data applicable for all 4 reports to grab 
        map = new HashMap<String,Object>();
        map.put("DATE_REQ", "08/2011");
        map.put("CUST_NAME", "NTT Data Malaysia");
        map.put("SVC_DESC", "NTTDM");
        map.put("BILL_CURRENCY", "RM");
        map.put("ITEM_AMT", "100.10");
        testReport.add(map);
    
        map = new HashMap<String,Object>();
        map.put("DATE_REQ", "09/2011");
        map.put("CUST_NAME", "NTT Data VN");
        map.put("SVC_DESC", "NTTDVN");
        map.put("BILL_CURRENCY", "USD");
        map.put("ITEM_AMT", "100.20");
        map.put("AFFILIATE_CODE", "affi");
        map.put("ID_REF", "Referral");
        map.put("ID_CUST_PLAN", "BilSys");
        map.put("IS_SINGPOST", "no");
        map.put("PAY_METHOD", "GIRO");
        map.put("INVOICE_AMT", "10.00");
        map.put("GST_AMOUNT", "20.00");
        map.put("BILL_AMOUNT", "30.00");
        map.put("DATE_TRANS", "12/2011");
        map.put("REFERENCE", "NTTDM");
        map.put("PMT_METHOD", "CITI");
        map.put("COM_ACCT_NAME", "ABC Corp");
        map.put("CUR_CODE", "JPY");
        map.put("AMT_PAID", "4000");
        map.put("OUTSTANDING_AMOUNT", "50");
        testReport.add(map);   

		param.setListAgingReport(testReport);

		gRptP01 = new G_RPT_P01(super.queryDAO, super.updateDAO, super.updateDAONuked);
		
		GlobalProcessResult gpr = gRptP01.execute(param, outputDTO);
		assertNotNull(gpr.getFile());
		assertEquals(true,gpr.getErrors().isEmpty());
	}	
	
	public void testExecuteadd2() throws Exception {
		
		param = new G_RPT_P01_Input();
		outputDTO = new G_RPT_P01_Output();
		
		param.setUserId("joel.chin");
	    // param.setReportType("SAL");	 
	     param.setReportType("RRR");
	    // param.setReportType("AGR");
	    // param.setReportType("ACR");
	    
        List<HashMap<String,Object>> testReport = new ArrayList<HashMap<String,Object>>() ;
        HashMap<String,Object> map = new HashMap<String,Object>();

     // dummy data applicable for all 4 reports to grab 
        map = new HashMap<String,Object>();
        map.put("DATE_REQ", "08/2011");
        map.put("CUST_NAME", "NTT Data Malaysia");
        map.put("SVC_DESC", "NTTDM");
        map.put("BILL_CURRENCY", "RM");
        map.put("ITEM_AMT", "100.10");
        testReport.add(map);
    
        map = new HashMap<String,Object>();
        map.put("DATE_REQ", "09/2011");
        map.put("CUST_NAME", "NTT Data VN");
        map.put("SVC_DESC", "NTTDVN");
        map.put("BILL_CURRENCY", "USD");
        map.put("ITEM_AMT", "100.20");
        map.put("AFFILIATE_CODE", "affi");
        map.put("ID_REF", "Referral");
        map.put("ID_CUST_PLAN", "BilSys");
        map.put("IS_SINGPOST", "no");
        map.put("PAY_METHOD", "CQ");
        map.put("INVOICE_AMT", "10.00");
        map.put("GST_AMOUNT", "20.00");
        map.put("BILL_AMOUNT", "30.00");
        map.put("DATE_TRANS", "12/2011");
        map.put("REFERENCE", "NTTDM");
        map.put("PMT_METHOD", "CITI");
        map.put("COM_ACCT_NAME", "ABC Corp");
        map.put("CUR_CODE", "JPY");
        map.put("AMT_PAID", "4000");
        map.put("OUTSTANDING_AMOUNT", "50");
        testReport.add(map);   

		param.setListAgingReport(testReport);

		gRptP01 = new G_RPT_P01(super.queryDAO, super.updateDAO, super.updateDAONuked);
		
		GlobalProcessResult gpr = gRptP01.execute(param, outputDTO);
		assertNotNull(gpr.getFile());
		assertEquals(true,gpr.getErrors().isEmpty());
	}
	
	public void testExecuteadd3() throws Exception {
		
		param = new G_RPT_P01_Input();
		outputDTO = new G_RPT_P01_Output();
		
		param.setUserId("joel.chin");
	    // param.setReportType("SAL");	 
	    // param.setReportType("RRR");
	    // param.setReportType("AGR");
	    param.setReportType("ACR");
	    
        List<HashMap<String,Object>> testReport = new ArrayList<HashMap<String,Object>>() ;
        HashMap<String,Object> map = new HashMap<String,Object>();

     // dummy data applicable for all 4 reports to grab 
        map = new HashMap<String,Object>();
        map.put("DATE_REQ", "08/2011");
        map.put("CUST_NAME", "NTT, Data Malaysia");
        map.put("SVC_DESC", "NTTDM");
        map.put("BILL_CURRENCY", "RM");
        map.put("ITEM_AMT", "100.10");
        testReport.add(map);
    
        map = new HashMap<String,Object>();
        map.put("DATE_REQ", "09/2011");
        map.put("CUST_NAME", "NTT Data VN");
        map.put("SVC_DESC", "NTTDVN");
        map.put("BILL_CURRENCY", "USD");
        map.put("ITEM_AMT", "100.20");
        map.put("AFFILIATE_CODE", "affi");
        map.put("ID_REF", "Referral");
        map.put("ID_CUST_PLAN", "BilSys");
        map.put("IS_SINGPOST", "no");
        map.put("PAY_METHOD", "GIRO");
        map.put("INVOICE_AMT", "10.00");
        map.put("GST_AMOUNT", "20.00");
        map.put("BILL_AMOUNT", "30.00");
        map.put("DATE_TRANS", "12/2011");
        map.put("REFERENCE", "NTTDM");
        map.put("PMT_METHOD", "CITI");
        map.put("COM_ACCT_NAME", "ABC Corp");
        map.put("CUR_CODE", "JPY");
        map.put("AMT_PAID", "4000");
        map.put("OUTSTANDING_AMOUNT", "50");
        testReport.add(map);   

		param.setListAgingReport(testReport);

		gRptP01 = new G_RPT_P01(super.queryDAO, super.updateDAO, super.updateDAONuked);
		
		GlobalProcessResult gpr = gRptP01.execute(param, outputDTO);
		assertNotNull(gpr.getFile());
		assertEquals(true,gpr.getErrors().isEmpty());
	}
	
	public void testExecuteadd4() throws Exception {
		this.deleteAllData("M_GSET_D");
		param = new G_RPT_P01_Input();
		outputDTO = new G_RPT_P01_Output();
		
		param.setUserId("joel.chin");
	   
		gRptP01 = new G_RPT_P01(super.queryDAO, super.updateDAO, super.updateDAONuked);
		
		GlobalProcessResult gpr = gRptP01.execute(param, outputDTO);
		Iterator<BLogicMessage> it = gpr.getErrors().get();
		assertEquals("errors.ERR1SC052", it.next().getKey());
		assertEquals("errors.ERR1BT014", it.next().getKey());
	}
	
	public void testExecuteadd5() throws Exception {
		
		this.deleteAllData("RESOURCESMAINT");
		// 【正常系】RESOURCESMAINT
		String[][] resourcesmaintS5 = {
				{ "ID", "CATEGORY", "SEQ", "RESOURCE_ID", "VALUE", "RES_DESC",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATE", "ID_LOGIN" },

				{ "123", "RPT", "00001", "QWESG", "wicresoft", "wicresoft",
						"0", "2012-03-14", "2012-03-14", "sysadmin" } };
		
		super.insertData("RESOURCESMAINT", resourcesmaintS5);
		param = new G_RPT_P01_Input();
		outputDTO = new G_RPT_P01_Output();
		
		param.setUserId("joel.chin");
	    param.setReportType("QWE");
	    
        List<HashMap<String,Object>> testReport = new ArrayList<HashMap<String,Object>>() ;
        HashMap<String,Object> map = new HashMap<String,Object>();

     // dummy data applicable for all 4 reports to grab 
        map = new HashMap<String,Object>();
        map.put("DATE_REQ", "08/2011");
        map.put("CUST_NAME", "NTT Data Malaysia");
        map.put("SVC_DESC", "NTTDM");
        map.put("BILL_CURRENCY", "RM");
        map.put("ITEM_AMT", "100.10");
        testReport.add(map);
    
        map = new HashMap<String,Object>();
        map.put("DATE_REQ", "09/2011");
        map.put("CUST_NAME", "NTT Data VN");
        map.put("SVC_DESC", "NTTDVN");
        map.put("BILL_CURRENCY", "USD");
        map.put("ITEM_AMT", "100.20");
        map.put("AFFILIATE_CODE", "affi");
        map.put("ID_REF", "Referral");
        map.put("ID_CUST_PLAN", "BilSys");
        map.put("IS_SINGPOST", "no");
        map.put("PAY_METHOD", "GIRO");
        map.put("INVOICE_AMT", "10.00");
        map.put("GST_AMOUNT", "20.00");
        map.put("BILL_AMOUNT", "30.00");
        map.put("DATE_TRANS", "12/2011");
        map.put("REFERENCE", "NTTDM");
        map.put("PMT_METHOD", "CITI");
        map.put("COM_ACCT_NAME", "ABC Corp");
        map.put("CUR_CODE", "JPY");
        map.put("AMT_PAID", "4000");
        map.put("OUTSTANDING_AMOUNT", "50");
        testReport.add(map);   

		param.setListAgingReport(testReport);

		gRptP01 = new G_RPT_P01(super.queryDAO, super.updateDAO, super.updateDAONuked);
		
		GlobalProcessResult gpr = gRptP01.execute(param, outputDTO);
		assertNotNull(gpr.getFile());
		Iterator<BLogicMessage> it = gpr.getErrors().get();
		assertEquals("errors.ERR1BT014", it.next().getKey());
	}
	
	public void testExecuteadd6() throws Exception {
		this.deleteAllData("RESOURCESMAINT");
		param = new G_RPT_P01_Input();
		outputDTO = new G_RPT_P01_Output();
		
		param.setUserId("joel.chin");
	    param.setReportType("ABC");
		
		gRptP01 = new G_RPT_P01(super.queryDAO, super.updateDAO, super.updateDAONuked);
		
		GlobalProcessResult gpr = gRptP01.execute(param, outputDTO);
		Iterator<BLogicMessage> it = gpr.getErrors().get();
		assertEquals("errors.ERR1SC052", it.next().getKey());
		assertEquals("errors.ERR1BT014", it.next().getKey());
	}
	
	public void testExecuteadd7() throws Exception {
		
		param = new G_RPT_P01_Input();
		outputDTO = new G_RPT_P01_Output();
		
		param.setUserId("joel.chin");
	    // param.setReportType("SAL");	 
	    // param.setReportType("RRR");
	    // param.setReportType("AGR");
	    param.setReportType("ACR");
	    
		gRptP01 = new G_RPT_P01(super.queryDAO, super.updateDAO, super.updateDAONuked);
		
		GlobalProcessResult gpr = gRptP01.execute(param, outputDTO);
		assertNotNull(gpr.getFile());
		Iterator<BLogicMessage> it = gpr.getErrors().get();
		BLogicMessage bm = it.next();
		assertEquals("errors.ERR1BT014", bm.getKey());
		assertEquals("true", bm.getValues()[0].toString());
	}
}
