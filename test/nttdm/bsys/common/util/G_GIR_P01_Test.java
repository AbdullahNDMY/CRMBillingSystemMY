/**
 * @(#)G_GIR_P01_Test.java
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

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.e_mex.dto.RP_E_MEX_GR1SubmitInput;
import nttdm.bsys.e_mex.dto.RP_E_MEX_GR1SubmitOutput;

/**
 * @author Joel
 * 
 */
public class G_GIR_P01_Test extends AbstractUtilTest {

	private G_GIR_P01 gGirP01 = null;

	private RP_E_MEX_GR1SubmitInput param = null;

	private RP_E_MEX_GR1SubmitOutput outputDTO = null;

	@Override
	protected void setUpData() throws Exception {
		param = new RP_E_MEX_GR1SubmitInput();
		outputDTO = new RP_E_MEX_GR1SubmitOutput();
		BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
		param.setUvo(uvo);
		param.getUvo().setId_user("sysadmin");
		param.setBankAcc("1818");
		param.setScr(true);
		param.setDeductionDate("29");
		
		Calendar c1 = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
		param.setClosingMonth(sdf1.format(c1.getTime()));
		param.setClosingYear(sdf.format(c1.getTime()));
	}

	/**
	 * auditId = null; and scr = true
	 * 
	 * @throws Exception
	 */
	public void testG_GIR_P01_01() throws Exception {

		// this.deleteAllData("T_GIR_EXP_HD");
		//
		// gGirP01 = new G_GIR_P01(super.queryDAO, super.updateDAO,
		// super.updateDAONuked);
		// gGirP01.setAuditStatus("123456789012345678901");
		// GlobalProcessResult gpr = gGirP01.execute(param, outputDTO);
		//
		// Map<String, Object> T_GIR_EXP_HD =
		// queryDAO.executeForMap("select.e_exp_f02.t_gir_exp_hd.001", "");

	}

	/**
	 * date format exception
	 * 
	 * @throws Exception
	 */
	public void testG_GIR_P01_02() throws Exception {
		
	 gGirP01 = new G_GIR_P01(super.queryDAO, super.updateDAO,super.updateDAONuked);
	 param.setDeductionDate("");
	 GlobalProcessResult gpr = gGirP01.execute(param, outputDTO);
	 assertNotNull(gpr.getErrors());
	 assertNotNull(gpr.getMessages());
	}
	
	/**
	 * last batch is in process and in time BATCH_TIME_INTERVAL
	 */
	public void testG_GIR_P01_03() {

		Calendar c1 = Calendar.getInstance();
		c1.add(Calendar.DATE, 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM/yyyy");
		String updateDate = sdf.format(c1.getTime());
		
		this.deleteAllData("T_GIR_EXP_HD");
		this.deleteAllData("T_BATCH_LOG");
		
		String[][] dataGirExpHd = {
				{ "ID_GIR_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
						"DATE_UPLOADED", "STATUS", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", },
				{ "223", "INVOICE_092012_224.txt", "07/2011",
						"2011-08-22 00:00:00", "2", "2011-08-22 00:00:00",
						updateDate, "sysadmin", } };
		super.insertData("T_GIR_EXP_HD", dataGirExpHd);
		
		gGirP01 = new G_GIR_P01(queryDAO, updateDAO, updateDAONuked);
		param.setScr(false);
		gGirP01.execute(param, outputDTO);
		Map<String, Object> T_GIR_EXP_HD = queryDAO.executeForMap("select.e_exp_f02.t_gir_exp_hd.002", "");
		
		assertEquals(T_GIR_EXP_HD.get("ID_LOGIN").toString(), "sysadmin");
		assertEquals(T_GIR_EXP_HD.get("FILENAME").toString(), "NOT AVAILABLE");
		assertEquals(T_GIR_EXP_HD.get("STATUS").toString(), "1");
		Calendar c2 = Calendar.getInstance();
		assertEquals(T_GIR_EXP_HD.get("CLOSE_MONTHYEAR").toString(), sdf1.format(c2.getTime()));	
		
		Map<String, Object> T_BATCH_LOG = queryDAO.executeForMap("select.e_exp_f02.t_batch_log.001", "");
		
		assertEquals("sysadmin", T_BATCH_LOG.get("ID_LOGIN").toString());
		assertEquals(MessageUtil.get("errors.ERR1BT020", ""), T_BATCH_LOG.get("ERROR_MSG").toString());
	}
	
	/**
	 * last batch is in process and over time 
	 */
	public void testG_GIR_P01_04() {

		Calendar c1 = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String updateDate = sdf.format(c1.getTime());
		
		this.deleteAllData("T_GIR_EXP_HD");
		this.deleteAllData("T_BATCH_LOG");
		
		String[][] dataGirExpHd = {
				{ "ID_GIR_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
						"DATE_UPLOADED", "STATUS", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", },
				{ "223", "INVOICE_092012_224.txt", "07/2011",
						"2011-08-22 00:00:00", "2", "2011-08-22 00:00:00",
						updateDate, "sysadmin", } };
		super.insertData("T_GIR_EXP_HD", dataGirExpHd);
		
		gGirP01 = new G_GIR_P01(queryDAO, updateDAO, updateDAONuked);
		param.setScr(false);
		gGirP01.execute(param, outputDTO);
		String idbatch = "223";
		Map<String, Object> T_GIR_EXP_HD = queryDAO.executeForMap("select.e_exp_f02.t_gir_exp_hd.003", idbatch);
		
		assertEquals(T_GIR_EXP_HD.get("ID_LOGIN").toString(), "sysadmin");
		assertEquals(T_GIR_EXP_HD.get("FILENAME").toString(), "INVOICE_092012_224.txt");
		assertEquals(T_GIR_EXP_HD.get("STATUS").toString(), "1");
		assertEquals(T_GIR_EXP_HD.get("CLOSE_MONTHYEAR").toString(),"07/2011");	
		
		Map<String, Object> T_BATCH_LOG = queryDAO.executeForMap("select.e_exp_f02.t_batch_log.002", idbatch);
		
		assertEquals("sysadmin", T_BATCH_LOG.get("ID_LOGIN").toString());
		assertEquals(MessageUtil.get("errors.ERR1BT016", ""), T_BATCH_LOG.get("ERROR_MSG").toString());
	}
	
	/**
	 *  there is no batch in process  and export path not exist
	 */
	public void testG_GIR_P01_05() {

		String[][] m_gset_dS1 = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },
				{
						"BATCH_G_GIR_P02",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "sysadmin", "", "50929", "0" }};
		String[][] m_gset_hS1 = {
				{ "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED", "ID_LOGIN" },

				{
						"BATCH_G_GIR_P02",
						"Cash Book auto offset by payment method or customer",
						"Settings for Cash Book to auto offset by payment method or customer in batch.",
						"0", "sysadmin" } };
		this.deleteAllData("M_GSET_D");
		this.deleteAllData("M_GSET_H");
		this.insertData("M_GSET_H", m_gset_hS1);
		this.insertData("M_GSET_D", m_gset_dS1);
		
		this.deleteAllData("T_GIR_EXP_HD");
		this.deleteAllData("T_BATCH_LOG");
		
		gGirP01 = new G_GIR_P01(queryDAO, updateDAO, updateDAONuked);
		param.setScr(true);
		GlobalProcessResult  gpr = gGirP01.execute(param, outputDTO);
		
		String idbatch = "223";
		Map<String, Object> T_GIR_EXP_HD = queryDAO.executeForMap("select.e_exp_f02.t_gir_exp_hd.002", idbatch);
		
		assertEquals(T_GIR_EXP_HD.get("ID_LOGIN").toString(), "sysadmin");
		assertEquals(T_GIR_EXP_HD.get("FILENAME").toString(), "NOT AVAILABLE");
		assertEquals(T_GIR_EXP_HD.get("STATUS").toString(), "1");
		Calendar c2 = Calendar.getInstance();
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM/yyyy");
		assertEquals(T_GIR_EXP_HD.get("CLOSE_MONTHYEAR").toString(),sdf1.format(c2.getTime()));	
		
		Map<String, Object> T_BATCH_LOG = queryDAO.executeForMap("select.e_exp_f02.t_batch_log.001", idbatch);
		
		assertEquals("sysadmin", T_BATCH_LOG.get("ID_LOGIN").toString());
		assertEquals(MessageUtil.get("errors.ERR1SC075", ""), T_BATCH_LOG.get("ERROR_MSG").toString());
		
		assertEquals("errors.ERR1SC075", gpr.getErrors().get().next().getKey());
	}
	
	/**
	 *  there is no batch in process  
	 *  export folder is not accessible
	 */
	public void testG_GIR_P01_06() {

		String[][] m_gset_dS1 = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },
				{
						"BATCH_G_GIR_P01",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "sysadmin", "C:\\OfficeSystem\\BATCH\\G_GIR_P02_test\\", "50929", "0" }};
		String[][] m_gset_hS1 = {
				{ "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED", "ID_LOGIN" },

				{
						"BATCH_G_GIR_P01",
						"Cash Book auto offset by payment method or customer",
						"Settings for Cash Book to auto offset by payment method or customer in batch.",
						"0", "sysadmin" } };
		this.deleteAllData("M_GSET_D");
		this.deleteAllData("M_GSET_H");
		this.insertData("M_GSET_H", m_gset_hS1);
		this.insertData("M_GSET_D", m_gset_dS1);
		
		this.deleteAllData("T_GIR_EXP_HD");
		this.deleteAllData("T_BATCH_LOG");
		
		gGirP01 = new G_GIR_P01(queryDAO, updateDAO, updateDAONuked);
		param.setScr(true);
		GlobalProcessResult  gpr = gGirP01.execute(param, outputDTO);
		
		String idbatch = "223";
		Map<String, Object> T_GIR_EXP_HD = queryDAO.executeForMap("select.e_exp_f02.t_gir_exp_hd.002", idbatch);
		
		assertEquals(T_GIR_EXP_HD.get("ID_LOGIN").toString(), "sysadmin");
		assertEquals(T_GIR_EXP_HD.get("FILENAME").toString(), "NOT AVAILABLE");
		assertEquals(T_GIR_EXP_HD.get("STATUS").toString(), "1");
		Calendar c2 = Calendar.getInstance();
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM/yyyy");
		assertEquals(T_GIR_EXP_HD.get("CLOSE_MONTHYEAR").toString(),sdf1.format(c2.getTime()));	
		
		Map<String, Object> T_BATCH_LOG = queryDAO.executeForMap("select.e_exp_f02.t_batch_log.001", idbatch);
		
		assertEquals("sysadmin", T_BATCH_LOG.get("ID_LOGIN").toString());
		assertEquals(MessageUtil.get("errors.ERR1SC076", ""), T_BATCH_LOG.get("ERROR_MSG").toString());
		
		assertEquals("errors.ERR1SC076", gpr.getErrors().get().next().getKey());
	}
	
	/**
	 *  there is no batch in process  
	 *  export folder is not accessible
	 */
	public void testG_GIR_P01_07() {

//		String[][] m_gset_dS1 = {
//				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "ID_LOGIN",
//						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },
//				{
//						"BATCH_G_GIR_P01",
//						"1",
//						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
//						"0", "sysadmin", "C:\\WINDOWS\\system32\\ping.exe", "50929", "0" }};
//		String[][] m_gset_hS1 = {
//				{ "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED", "ID_LOGIN" },
//
//				{
//						"BATCH_G_GIR_P01",
//						"Cash Book auto offset by payment method or customer",
//						"Settings for Cash Book to auto offset by payment method or customer in batch.",
//						"0", "sysadmin" } };
//		this.deleteAllData("M_GSET_D");
//		this.deleteAllData("M_GSET_H");
//		this.insertData("M_GSET_H", m_gset_hS1);
//		this.insertData("M_GSET_D", m_gset_dS1);
//		
//		this.deleteAllData("T_GIR_EXP_HD");
//		this.deleteAllData("T_BATCH_LOG");
//		
//		gGirP01 = new G_GIR_P01(queryDAO, updateDAO, updateDAONuked);
//		param.setScr(true);
//		GlobalProcessResult  gpr = gGirP01.execute(param, outputDTO);
//		
//		String idbatch = "223";
//		Map<String, Object> T_GIR_EXP_HD = queryDAO.executeForMap("select.e_exp_f02.t_gir_exp_hd.002", idbatch);
//		
//		assertEquals(T_GIR_EXP_HD.get("ID_LOGIN").toString(), "sysadmin");
//		assertEquals(T_GIR_EXP_HD.get("FILENAME").toString(), "NOT AVAILABLE");
//		assertEquals(T_GIR_EXP_HD.get("STATUS").toString(), "1");
//		Calendar c2 = Calendar.getInstance();
//		SimpleDateFormat sdf1 = new SimpleDateFormat("MM/yyyy");
//		assertEquals(T_GIR_EXP_HD.get("CLOSE_MONTHYEAR").toString(),sdf1.format(c2.getTime()));	
//		
//		Map<String, Object> T_BATCH_LOG = queryDAO.executeForMap("select.e_exp_f02.t_batch_log.001", idbatch);
//		
//		assertEquals("sysadmin", T_BATCH_LOG.get("ID_LOGIN").toString());
//		assertEquals(MessageUtil.get("errors.ERR1SC076", ""), T_BATCH_LOG.get("ERROR_MSG").toString());
//		
//		assertEquals("errors.ERR1SC076", gpr.getErrors().get().next().getKey());
	}
	
	/**
	 * 
	 */
	public void testG_GIR_P01_08() {

//		Calendar c1 = Calendar.getInstance();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM/yyyy");
		//String updateDate = sdf.format(c1.getTime());
		String[][] m_gset_dS1 = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },
				{
						"BATCH_G_GIR_P01",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "sysadmin", "C:\\OfficeSystem\\BATCH\\G_GIR_P02\\", "50929", "0" }};
		String[][] m_gset_hS1 = {
				{ "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED", "ID_LOGIN" },

				{
						"BATCH_G_GIR_P01",
						"Cash Book auto offset by payment method or customer",
						"Settings for Cash Book to auto offset by payment method or customer in batch.",
						"0", "sysadmin" } };
		this.deleteAllData("M_GSET_D");
		this.deleteAllData("M_GSET_H");
		this.insertData("M_GSET_H", m_gset_hS1);
		this.insertData("M_GSET_D", m_gset_dS1);
		
		this.deleteAllData("T_GIR_EXP_HD");
		this.deleteAllData("T_BATCH_LOG");
		
//		String[][] dataGirExpHd = {
//				{ "ID_GIR_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
//						"DATE_UPLOADED", "STATUS", "DATE_CREATED",
//						"DATE_UPDATED", "ID_LOGIN", },
//				{ "223", "INVOICE_092012_224.txt", "07/2011",
//						"2011-08-22 00:00:00", "0", "2011-08-22 00:00:00",
//						updateDate, "sysadmin", } };
//		super.insertData("T_GIR_EXP_HD", dataGirExpHd);
		
		
		String filename = "GIROdata_" + param.getClosingMonth() + param.getClosingYear().substring(2) + ".txt";
		
		File testFile = new File("C:\\OfficeSystem\\BATCH\\G_GIR_P02\\"+filename);
		try {
			if(!testFile.exists()){
				testFile.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		gGirP01 = new G_GIR_P01(queryDAO, updateDAO, updateDAONuked);
		param.setScr(true);
		GlobalProcessResult gpr =gGirP01.execute(param, outputDTO);
		

		Map<String, Object> T_GIR_EXP_HD = queryDAO.executeForMap("select.e_exp_f02.t_gir_exp_hd.002", "");
		
		assertEquals(T_GIR_EXP_HD.get("ID_LOGIN").toString(), "sysadmin");
		assertEquals(T_GIR_EXP_HD.get("FILENAME").toString(), "NOT AVAILABLE");
		assertEquals(T_GIR_EXP_HD.get("STATUS").toString(), "1");
		Calendar c2 = Calendar.getInstance();
		assertEquals(T_GIR_EXP_HD.get("CLOSE_MONTHYEAR").toString(), sdf1.format(c2.getTime()));	
		
		Map<String, Object> T_BATCH_LOG = queryDAO.executeForMap("select.e_exp_f02.t_batch_log.001", "");
		
		assertEquals("sysadmin", T_BATCH_LOG.get("ID_LOGIN").toString());
		assertEquals(MessageUtil.get("errors.ERR1SC077", ""), T_BATCH_LOG.get("ERROR_MSG").toString());
		
		assertEquals("errors.ERR1SC077", gpr.getErrors().get().next().getKey());
	}
	
	/**
	 * 
	 */
	public void testG_GIR_P01_09() {
		String filename = "GIROdata_" + param.getClosingMonth() + param.getClosingYear().substring(2) + ".txt";
		Calendar c1 = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String updateDate = sdf.format(c1.getTime());
		String[][] m_gset_dS1 = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },
				{
						"BATCH_G_GIR_P01",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "sysadmin", "C:\\OfficeSystem\\BATCH\\G_GIR_P01\\", "50929", "0" }};
		String[][] m_gset_hS1 = {
				{ "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED", "ID_LOGIN" },

				{
						"BATCH_G_GIR_P01",
						"Cash Book auto offset by payment method or customer",
						"Settings for Cash Book to auto offset by payment method or customer in batch.",
						"0", "sysadmin" } };
		this.deleteAllData("M_GSET_D");
		this.deleteAllData("M_GSET_H");
		this.insertData("M_GSET_H", m_gset_hS1);
		this.insertData("M_GSET_D", m_gset_dS1);
		
		this.deleteAllData("T_BAC_D");
		this.deleteAllData("T_BAC_H");
		this.deleteAllData("T_GIR_EXP_HD");
		this.deleteAllData("T_BATCH_LOG");
		
		String[][] dataGirExpHd = {
				{ "ID_GIR_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
						"DATE_UPLOADED", "STATUS", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", },
				{ "223", filename, "07/2011",
						"2011-08-22 00:00:00", "0", "2011-08-22 00:00:00",
						updateDate, "sysadmin", } };
		super.insertData("T_GIR_EXP_HD", dataGirExpHd);
		
		File newFile1 = new File("C:\\OfficeSystem\\BATCH\\G_GIR_P01\\GIROdata_" + param.getClosingMonth() + param.getClosingYear().substring(2) + "_223.txt");
		if(newFile1.exists()){
			newFile1.delete();
		}
		File testFile = new File("C:\\OfficeSystem\\BATCH\\G_GIR_P01\\"+filename);
		try {
			if(!testFile.exists()){
				testFile.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		gGirP01 = new G_GIR_P01(queryDAO, updateDAO, updateDAONuked);
		param.setScr(false);
		gGirP01.execute(param, outputDTO);
		
		File resFile = new File("C:\\OfficeSystem\\BATCH\\G_GIR_P01\\"+filename);
		assertEquals(false, resFile.exists());
		File newFile = new File("C:\\OfficeSystem\\BATCH\\G_GIR_P01\\GIROdata_" + param.getClosingMonth() + param.getClosingYear().substring(2) + "_223.txt");
		assertEquals(true, newFile.exists());
		
		String idbatch = "223";
		Map<String, Object> T_GIR_EXP_HD = queryDAO.executeForMap("select.e_exp_f02.t_gir_exp_hd.003", idbatch);
		
		assertEquals(T_GIR_EXP_HD.get("ID_LOGIN").toString(), "sysadmin");
		assertEquals(T_GIR_EXP_HD.get("FILENAME").toString(), "GIROdata_" + param.getClosingMonth() + param.getClosingYear().substring(2) + "_223.txt");
		assertEquals(T_GIR_EXP_HD.get("STATUS").toString(), "0");
		assertEquals(T_GIR_EXP_HD.get("CLOSE_MONTHYEAR").toString(), "07/2011");
		
		Map<String, Object> T_BATCH_LOG = queryDAO.executeForMap("select.e_exp_f02.t_batch_log.001", "");
		
		assertEquals("sysadmin", T_BATCH_LOG.get("ID_LOGIN").toString());
		assertEquals(MessageUtil.get("errors.ERR1SC064", ""), T_BATCH_LOG.get("ERROR_MSG").toString());
	}
	
	/**
	 * 
	 */
	public void testG_GIR_P01_10() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String filename = "GIROdata_" + param.getClosingMonth() + param.getClosingYear().substring(2) + ".txt";
		Calendar c1 = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String updateDate = sdf.format(c1.getTime());
		String[][] m_gset_dS1 = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },
				{
						"BATCH_G_GIR_P01",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "sysadmin", "C:\\OfficeSystem\\BATCH\\G_GIR_P01\\", "50929", "0" }};
		String[][] m_gset_hS1 = {
				{ "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED", "ID_LOGIN" },

				{
						"BATCH_G_GIR_P01",
						"Cash Book auto offset by payment method or customer",
						"Settings for Cash Book to auto offset by payment method or customer in batch.",
						"0", "sysadmin" } };
		this.deleteAllData("M_GSET_D");
		this.deleteAllData("M_GSET_H");
		this.insertData("M_GSET_H", m_gset_hS1);
		this.insertData("M_GSET_D", m_gset_dS1);
		
		this.deleteAllData("T_GIR_EXP_HD");
		this.deleteAllData("T_BATCH_LOG");
		
		String[][] dataGirExpHd = {
				{ "ID_GIR_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
						"DATE_UPLOADED", "STATUS", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", },
				{ "223", filename, "07/2011",
						"2011-08-22 00:00:00", "0", "2011-08-22 00:00:00",
						updateDate, "sysadmin", } };
		super.insertData("T_GIR_EXP_HD", dataGirExpHd);
		
		this.deleteAllData("T_BAC_D");
		this.deleteAllData("T_BAC_H");
		this.deleteAllData("T_BIL_D");
		this.deleteAllData("T_BIL_H");
		this.deleteAllData("T_CSB_D");
		this.deleteAllData("T_CSB_H");
		
		String[][] testDataTBatchLog5 = {
				{ "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
						"BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
						"EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "FIXED_FOREX", "IS_SINGPOST",
						"TOTAL_AMT_DUE" },
				{ "2", "229743", "GR", "MYR", "0", "", "BA", "PC", "0",
						"2011-1-18  12:43:28", "2011-1-18  12:43:28", "DM",
						"2", "1", "1000" },
				{ "3", "229743", "GR", "MYR", "0", "", "BA", "PC", "0",
							"2011-1-18  12:43:28", "2011-1-18  12:43:28", "DM",
							"2", "1", "1000" }};
		super.insertData("T_BAC_H", testDataTBatchLog5);
		
		String[][] tBilHData = {
				{ "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
						"PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
						"ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
						"TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
						"BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
						"IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
						"IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
						"EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
						"DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
						"ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
						"ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
						"CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
						"PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT" },
				{ "1", "IN", "0", "2", format.format(new Date()), "CQ",
						"229743", "BA", "PC", null, null, null, null, "bhchan",
						"30 Days", "MYR", "6", "0", "3500", "3000",
						TEST_DAY_TODAY, "2", "0", "0", "1", "USD", "3.124",
						"1120.36", "50", "1", TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						AUDIT_VALUE },
				{ "2", "DN", "0", "2", format.format(new Date()), "CQ",
					"229743", "BA", "PC", null, null, null, null, "bhchan",
					"30 Days", "MYR", "6", "0", "500", "0",
					TEST_DAY_TODAY, "2", "0", "0", "1", "USD", "3.124",
					"1120.36", "50", "1", TEST_DAY_TODAY, "joeykan", "0",
					"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
					"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
					"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
					AUDIT_VALUE },
				{ "4", "DN", "0", "3", format.format(new Date()), "CQ",
						"229743", "BA", "PC", null, null, null, null, "bhchan",
						"30 Days", "MYR", "6", "0", "1500", "1500",
						TEST_DAY_TODAY, "2", "0", "0", "1", "USD", "3.124",
						"1120.36", "50", "1", TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						AUDIT_VALUE },
				{ "3", "DN", "1", "3", format.format(new Date()), "CQ",
						"229743", "BA", "PC", null, null, null, null, "bhchan",
						"30 Days", "MYR", "6", "0", "1500", "500",
						TEST_DAY_TODAY, "2", "0", "0", "1", "USD", "3.124",
						"1120.36", "50", "1", TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						AUDIT_VALUE }};
		super.insertData("T_BIL_H", tBilHData);
		
		String[][] dataT_CSB_H = {
				{ "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
						"PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
						"REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
						"IS_CLOSED", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
						"BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
						"ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
						"REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
				{ "111238              ", "229743", "141", "", "NT",
						"2012-04-12", "4", "MYR", "REMARK", "N", "Cheque No.",
						"Bank-In Slip No.", "0", "0", "2011-07-07",
						"2011-07-07", "BIF001", "2331", "3211", "0", null, "2",
						"0", "0", "2011-08-30", "1111", "0" } };
		this.insertData("T_CSB_H", dataT_CSB_H);
		
		String[][] dataM_BANK = {
				{"ID_BANK","BANK_FULL_NAME","BANK_CODE","BANK_NAME",
					"BRANCH_CODE","BRANCH_NAME","BANK_TEL_NO","BANK_FAX_NO",
					"IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
				{"380","Bank Money-XYZ","0123","Bank Money",
						"12","XYZ","","",
						"0",TEST_DAY_TODAY,TEST_DAY_TODAY,"USERFULL",null}};
		super.deleteAllData("M_BANK");
		super.insertData("M_BANK", dataM_BANK);
		
		// insert data to totalGiro > 0
		String[][] mCustBankinfoData = {
				{ "ID_CUST", "ID_GIRO_BANK", "GIRO_ACCT_NO", "GIRO_ACCT_NAME",
						"CCARD_TYPE", "CCARD_ACCT_NO", "CCARD_NO",
						"CCARD_HOLDER_NAME", "CCARD_EXPIRED_DATE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SWIFT_CODE", "CCARD_SECURITY_NO", "IS_DELETED",
						"NO_REFERENCE", "ID_AUDIT" },
				{ "229743", "380", "123-1,23", "GR-Sin,gapore", "VISA", "123-123",
						"1234-1234-1234-1234", "CC-Singapore", "2011-12-01",
						"2011-02-25 10:57:49", "2011-02-25 10:57:49",
						"USERFULL", "12345", "123", "0", "RE3444444444", null } };
		super.deleteAllData("M_CUST_BANKINFO");
		super.insertData("M_CUST_BANKINFO", mCustBankinfoData);
		

		String[][] dataM_COM_BANKINFO = {
				{"ID_COM_BANK","ID_COM","ID_BANK","COM_SWIFT",
					"COM_ACCT_NO","COM_ACCT_TYPE","COM_ACCT_NAME",
					"DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT","COM_CUR"},
				{"0","11","398","",
						"11111","1","8888",
						TEST_DAY_TODAY,TEST_DAY_TODAY,"admin",null,"DZD"}};
		super.deleteAllData("M_COM_BANKINFO");
		super.insertData("M_COM_BANKINFO", dataM_COM_BANKINFO);
		
		gGirP01 = new G_GIR_P01(queryDAO, updateDAO, updateDAONuked);
		param.setScr(true);
		param.setBankAcc("0");
		gGirP01.execute(param, outputDTO);
			
		String idbatch = "223";
		Map<String, Object> T_GIR_EXP_HD = queryDAO.executeForMap("select.e_exp_f02.t_gir_exp_hd.002", idbatch);
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM/yyyy");
		Calendar c2 = Calendar.getInstance();
		assertEquals(T_GIR_EXP_HD.get("ID_LOGIN").toString(), "sysadmin");
		assertEquals(T_GIR_EXP_HD.get("FILENAME").toString(), "GIROdata_" + param.getClosingMonth() + param.getClosingYear().substring(2) + ".txt");
		assertEquals(T_GIR_EXP_HD.get("STATUS").toString(), "0");
		assertEquals(T_GIR_EXP_HD.get("CLOSE_MONTHYEAR").toString(), sdf1.format(c2.getTime()));
		
		File resFile = new File("C:\\OfficeSystem\\BATCH\\G_GIR_P01\\"+filename);
		assertEquals(true, resFile.exists());
	}
	
	/**
	 * 
	 */
	public void testG_GIR_P01_11() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String filename = "GIROdata_" + param.getClosingMonth() + param.getClosingYear().substring(2) + ".txt";
		Calendar c1 = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String updateDate = sdf.format(c1.getTime());
		String[][] m_gset_dS1 = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },
				{
						"BATCH_G_GIR_P01",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "sysadmin", "C:\\OfficeSystem\\BATCH\\G_GIR_P01\\", "50929", "0" }};
		String[][] m_gset_hS1 = {
				{ "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED", "ID_LOGIN" },

				{
						"BATCH_G_GIR_P01",
						"Cash Book auto offset by payment method or customer",
						"Settings for Cash Book to auto offset by payment method or customer in batch.",
						"0", "sysadmin" } };
		this.deleteAllData("M_GSET_D");
		this.deleteAllData("M_GSET_H");
		this.insertData("M_GSET_H", m_gset_hS1);
		this.insertData("M_GSET_D", m_gset_dS1);
		
		this.deleteAllData("T_GIR_EXP_HD");
		this.deleteAllData("T_BATCH_LOG");
		
		String[][] dataGirExpHd = {
				{ "ID_GIR_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
						"DATE_UPLOADED", "STATUS", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", },
				{ "223", filename, "07/2011",
						"2011-08-22 00:00:00", "0", "2011-08-22 00:00:00",
						updateDate, "sysadmin", } };
		super.insertData("T_GIR_EXP_HD", dataGirExpHd);
		
		this.deleteAllData("T_BAC_D");
		this.deleteAllData("T_BAC_H");
		this.deleteAllData("T_BIL_D");
		this.deleteAllData("T_BIL_H");
		this.deleteAllData("T_CSB_D");
		this.deleteAllData("T_CSB_H");
		
		String[][] testDataTBatchLog5 = {
				{ "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
						"BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
						"EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "FIXED_FOREX", "IS_SINGPOST",
						"TOTAL_AMT_DUE" },
				{ "2", "229743", "GR", "MYR", "0", "", "BA", "PC", "0",
						"2011-1-18  12:43:28", "2011-1-18  12:43:28", "DM",
						"2", "1", "1000" },
				{ "3", "229743", "GR", "MYR", "0", "", "BA", "PC", "0",
							"2011-1-18  12:43:28", "2011-1-18  12:43:28", "DM",
							"2", "1", "1000" }};
		super.insertData("T_BAC_H", testDataTBatchLog5);
		
		String[][] tBilHData = {
				{ "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
						"PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
						"ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
						"TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
						"BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
						"IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
						"IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
						"EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
						"DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
						"ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
						"ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
						"CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
						"PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT" },
				{ "1", "IN", "0", "2", format.format(new Date()), "CQ",
						"229743", "BA", "PC", null, null, null, null, "bhchan",
						"30 Days", "MYR", "6", "0", "3500", "3000",
						TEST_DAY_TODAY, "2", "0", "0", "1", "USD", "3.124",
						"1120.36", "50", "1", TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						AUDIT_VALUE },
				{ "2", "DN", "0", "2", format.format(new Date()), "CQ",
					"229743", "BA", "PC", null, null, null, null, "bhchan",
					"30 Days", "MYR", "6", "0", "500", "0",
					TEST_DAY_TODAY, "2", "0", "0", "1", "USD", "3.124",
					"1120.36", "50", "1", TEST_DAY_TODAY, "joeykan", "0",
					"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
					"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
					"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
					AUDIT_VALUE },
				{ "4", "DN", "0", "3", format.format(new Date()), "CQ",
						"229743", "BA", "PC", null, null, null, null, "bhchan",
						"30 Days", "MYR", "6", "0", "1500", "1500",
						TEST_DAY_TODAY, "2", "0", "0", "1", "USD", "3.124",
						"1120.36", "50", "1", TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						AUDIT_VALUE },
				{ "3", "DN", "1", "3", format.format(new Date()), "CQ",
						"229743", "BA", "PC", null, null, null, null, "bhchan",
						"30 Days", "MYR", "6", "0", "1500", "500",
						TEST_DAY_TODAY, "2", "0", "0", "1", "USD", "3.124",
						"1120.36", "50", "1", TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						AUDIT_VALUE }};
		super.insertData("T_BIL_H", tBilHData);
		
		String[][] dataT_CSB_H = {
				{ "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
						"PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
						"REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
						"IS_CLOSED", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
						"BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
						"ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
						"REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
				{ "111238              ", "229743", "141", "", "NT",
						"2012-04-12", "4", "MYR", "REMARK", "N", "Cheque No.",
						"Bank-In Slip No.", "0", "0", "2011-07-07",
						"2011-07-07", "BIF001", "2331", "3211", "0", null, "2",
						"0", "0", "2011-08-30", "1111", "0" } };
		this.insertData("T_CSB_H", dataT_CSB_H);
		
		String[][] dataM_BANK = {
				{"ID_BANK","BANK_FULL_NAME","BANK_CODE","BANK_NAME",
					"BRANCH_CODE","BRANCH_NAME","BANK_TEL_NO","BANK_FAX_NO",
					"IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
				{"380","Bank Money-XYZ","0123","Bank Money",
						"12","XYZ","","",
						"0",TEST_DAY_TODAY,TEST_DAY_TODAY,"USERFULL",null}};
		super.deleteAllData("M_BANK");
		super.insertData("M_BANK", dataM_BANK);
		
		// insert data to totalGiro > 0
		String[][] mCustBankinfoData = {
				{ "ID_CUST", "ID_GIRO_BANK", "GIRO_ACCT_NO", "GIRO_ACCT_NAME",
						"CCARD_TYPE", "CCARD_ACCT_NO", "CCARD_NO",
						"CCARD_HOLDER_NAME", "CCARD_EXPIRED_DATE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SWIFT_CODE", "CCARD_SECURITY_NO", "IS_DELETED",
						"NO_REFERENCE", "ID_AUDIT" },
				{ "229743", "380", "", "GR-Singapore", "VISA", "123-123",
						"1234-1234-1234-1234", "CC-Singapore", "2011-12-01",
						"2011-02-25 10:57:49", "2011-02-25 10:57:49",
						"USERFULL", "12345", "123", "0", "RE3444444444", null } };
		super.deleteAllData("M_CUST_BANKINFO");
		super.insertData("M_CUST_BANKINFO", mCustBankinfoData);
		

		String[][] dataM_COM_BANKINFO = {
				{"ID_COM_BANK","ID_COM","ID_BANK","COM_SWIFT",
					"COM_ACCT_NO","COM_ACCT_TYPE","COM_ACCT_NAME",
					"DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT","COM_CUR"},
				{"0","11","398","",
						"11111","1","8888",
						TEST_DAY_TODAY,TEST_DAY_TODAY,"admin",null,"DZD"}};
		super.deleteAllData("M_COM_BANKINFO");
		super.insertData("M_COM_BANKINFO", dataM_COM_BANKINFO);
		
		gGirP01 = new G_GIR_P01(queryDAO, updateDAO, updateDAONuked);
		param.setScr(true);
		param.setBankAcc("0");
		GlobalProcessResult gpr = gGirP01.execute(param, outputDTO);
		assertEquals("errors.ERR1SC071", gpr.getErrors().get().next().getKey());
	}
	/**
	 * 
	 */
	public void testG_GIR_P01_12() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String filename = "GIROdata_" + param.getClosingMonth() + param.getClosingYear().substring(2) + ".txt";
		Calendar c1 = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String updateDate = sdf.format(c1.getTime());
		String[][] m_gset_dS1 = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },
				{
						"BATCH_G_GIR_P01",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "sysadmin", "C:\\OfficeSystem\\BATCH\\G_GIR_P01\\", "50929", "0" }};
		String[][] m_gset_hS1 = {
				{ "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED", "ID_LOGIN" },

				{
						"BATCH_G_GIR_P01",
						"Cash Book auto offset by payment method or customer",
						"Settings for Cash Book to auto offset by payment method or customer in batch.",
						"0", "sysadmin" } };
		this.deleteAllData("M_GSET_D");
		this.deleteAllData("M_GSET_H");
		this.insertData("M_GSET_H", m_gset_hS1);
		this.insertData("M_GSET_D", m_gset_dS1);
		
		this.deleteAllData("T_GIR_EXP_HD");
		this.deleteAllData("T_BATCH_LOG");
		
		String[][] dataGirExpHd = {
				{ "ID_GIR_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
						"DATE_UPLOADED", "STATUS", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", },
				{ "223", filename, "07/2011",
						"2011-08-22 00:00:00", "0", "2011-08-22 00:00:00",
						updateDate, "sysadmin", } };
		super.insertData("T_GIR_EXP_HD", dataGirExpHd);
		
		this.deleteAllData("T_BAC_D");
		this.deleteAllData("T_BAC_H");
		this.deleteAllData("T_BIL_D");
		this.deleteAllData("T_BIL_H");
		this.deleteAllData("T_CSB_D");
		this.deleteAllData("T_CSB_H");
		
		String[][] testDataTBatchLog5 = {
				{ "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
						"BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
						"EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "FIXED_FOREX", "IS_SINGPOST",
						"TOTAL_AMT_DUE" },
				{ "2", "229743", "GR", "MYR", "0", "", "BA", "PC", "0",
						"2011-1-18  12:43:28", "2011-1-18  12:43:28", "DM",
						"2", "1", "1000" },
				{ "3", "229743", "GR", "MYR", "0", "", "BA", "PC", "0",
							"2011-1-18  12:43:28", "2011-1-18  12:43:28", "DM",
							"2", "1", "1000" }};
		super.insertData("T_BAC_H", testDataTBatchLog5);
		
		String[][] tBilHData = {
				{ "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
						"PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
						"ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
						"TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
						"BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
						"IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
						"IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
						"EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
						"DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
						"ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
						"ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
						"CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
						"PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT" },
				{ "1", "IN", "0", "2", format.format(new Date()), "CQ",
						"229743", "BA", "PC", null, null, null, null, "bhchan",
						"30 Days", "MYR", "6", "0", "3500", "3000",
						TEST_DAY_TODAY, "2", "0", "0", "1", "USD", "3.124",
						"1120.36", "50", "1", TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						AUDIT_VALUE },
				{ "2", "DN", "0", "2", format.format(new Date()), "CQ",
					"229743", "BA", "PC", null, null, null, null, "bhchan",
					"30 Days", "MYR", "6", "0", "500", "0",
					TEST_DAY_TODAY, "2", "0", "0", "1", "USD", "3.124",
					"1120.36", "50", "1", TEST_DAY_TODAY, "joeykan", "0",
					"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
					"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
					"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
					AUDIT_VALUE },
				{ "4", "DN", "0", "3", format.format(new Date()), "CQ",
						"229743", "BA", "PC", null, null, null, null, "bhchan",
						"30 Days", "MYR", "6", "0", "1500", "1500",
						TEST_DAY_TODAY, "2", "0", "0", "1", "USD", "3.124",
						"1120.36", "50", "1", TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						AUDIT_VALUE },
				{ "3", "DN", "1", "3", format.format(new Date()), "CQ",
						"229743", "BA", "PC", null, null, null, null, "bhchan",
						"30 Days", "MYR", "6", "0", "1500", "500",
						TEST_DAY_TODAY, "2", "0", "0", "1", "USD", "3.124",
						"1120.36", "50", "1", TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						AUDIT_VALUE }};
		super.insertData("T_BIL_H", tBilHData);
		
		String[][] dataT_CSB_H = {
				{ "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
						"PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
						"REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
						"IS_CLOSED", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
						"BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
						"ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
						"REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
				{ "111238              ", "229743", "141", "", "NT",
						"2012-04-12", "4", "MYR", "REMARK", "N", "Cheque No.",
						"Bank-In Slip No.", "0", "0", "2011-07-07",
						"2011-07-07", "BIF001", "2331", "3211", "0", null, "2",
						"0", "0", "2011-08-30", "1111", "0" } };
		this.insertData("T_CSB_H", dataT_CSB_H);
		
		String[][] dataM_BANK = {
				{"ID_BANK","BANK_FULL_NAME","BANK_CODE","BANK_NAME",
					"BRANCH_CODE","BRANCH_NAME","BANK_TEL_NO","BANK_FAX_NO",
					"IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
				{"380","Bank Money-XYZ","0123","Bank Money",
						"12","XYZ","","",
						"0",TEST_DAY_TODAY,TEST_DAY_TODAY,"USERFULL",null}};
		super.deleteAllData("M_BANK");
		super.insertData("M_BANK", dataM_BANK);
		
		// insert data to totalGiro > 0
		String[][] mCustBankinfoData = {
				{ "ID_CUST", "ID_GIRO_BANK", "GIRO_ACCT_NO", "GIRO_ACCT_NAME",
						"CCARD_TYPE", "CCARD_ACCT_NO", "CCARD_NO",
						"CCARD_HOLDER_NAME", "CCARD_EXPIRED_DATE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SWIFT_CODE", "CCARD_SECURITY_NO", "IS_DELETED",
						"NO_REFERENCE", "ID_AUDIT" },
				{ "229743", "380", "123-321", "", "VISA", "123-123",
						"1234-1234-1234-1234", "CC-Singapore", "2011-12-01",
						"2011-02-25 10:57:49", "2011-02-25 10:57:49",
						"USERFULL", "12345", "123", "0", "RE3444444444", null } };
		super.deleteAllData("M_CUST_BANKINFO");
		super.insertData("M_CUST_BANKINFO", mCustBankinfoData);
		

		String[][] dataM_COM_BANKINFO = {
				{"ID_COM_BANK","ID_COM","ID_BANK","COM_SWIFT",
					"COM_ACCT_NO","COM_ACCT_TYPE","COM_ACCT_NAME",
					"DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT","COM_CUR"},
				{"0","11","398","",
						"11111","1","8888",
						TEST_DAY_TODAY,TEST_DAY_TODAY,"admin",null,"DZD"}};
		super.deleteAllData("M_COM_BANKINFO");
		super.insertData("M_COM_BANKINFO", dataM_COM_BANKINFO);
		
		gGirP01 = new G_GIR_P01(queryDAO, updateDAO, updateDAONuked);
		param.setScr(true);
		param.setBankAcc("0");
		GlobalProcessResult gpr = gGirP01.execute(param, outputDTO);
		assertEquals("errors.ERR1SC071", gpr.getErrors().get().next().getKey());
	}
	
	/**
	 * 
	 */
	public void testG_GIR_P01_13() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String filename = "GIROdata_" + param.getClosingMonth() + param.getClosingYear().substring(2) + ".txt";
		Calendar c1 = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String updateDate = sdf.format(c1.getTime());
		String[][] m_gset_dS1 = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },
				{
						"BATCH_G_GIR_P01",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "sysadmin", "C:\\OfficeSystem\\BATCH\\G_GIR_P01\\", "50929", "0" }};
		String[][] m_gset_hS1 = {
				{ "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED", "ID_LOGIN" },

				{
						"BATCH_G_GIR_P01",
						"Cash Book auto offset by payment method or customer",
						"Settings for Cash Book to auto offset by payment method or customer in batch.",
						"0", "sysadmin" } };
		this.deleteAllData("M_GSET_D");
		this.deleteAllData("M_GSET_H");
		this.insertData("M_GSET_H", m_gset_hS1);
		this.insertData("M_GSET_D", m_gset_dS1);
		
		this.deleteAllData("T_GIR_EXP_HD");
		this.deleteAllData("T_BATCH_LOG");
		
		String[][] dataGirExpHd = {
				{ "ID_GIR_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
						"DATE_UPLOADED", "STATUS", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", },
				{ "223", filename, "07/2011",
						"2011-08-22 00:00:00", "0", "2011-08-22 00:00:00",
						updateDate, "sysadmin", } };
		super.insertData("T_GIR_EXP_HD", dataGirExpHd);
		
		this.deleteAllData("T_BAC_D");
		this.deleteAllData("T_BAC_H");
		this.deleteAllData("T_BIL_D");
		this.deleteAllData("T_BIL_H");
		this.deleteAllData("T_CSB_D");
		this.deleteAllData("T_CSB_H");
		
		String[][] testDataTBatchLog5 = {
				{ "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
						"BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
						"EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "FIXED_FOREX", "IS_SINGPOST",
						"TOTAL_AMT_DUE" },
				{ "2", "229743", "GR", "MYR", "0", "", "BA", "PC", "0",
						"2011-1-18  12:43:28", "2011-1-18  12:43:28", "DM",
						"2", "1", "1000" },
				{ "3", "229743", "GR", "MYR", "0", "", "BA", "PC", "0",
							"2011-1-18  12:43:28", "2011-1-18  12:43:28", "DM",
							"2", "1", "1000" }};
		super.insertData("T_BAC_H", testDataTBatchLog5);
		
		String[][] tBilHData = {
				{ "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
						"PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
						"ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
						"TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
						"BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
						"IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
						"IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
						"EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
						"DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
						"ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
						"ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
						"CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
						"PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT" },
				{ "1", "IN", "0", "2", format.format(new Date()), "CQ",
						"229743", "BA", "PC", null, null, null, null, "bhchan",
						"30 Days", "MYR", "6", "0", "3500", "3000",
						TEST_DAY_TODAY, "2", "0", "0", "1", "USD", "3.124",
						"1120.36", "50", "1", TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						AUDIT_VALUE },
				{ "2", "DN", "0", "2", format.format(new Date()), "CQ",
					"229743", "BA", "PC", null, null, null, null, "bhchan",
					"30 Days", "MYR", "6", "0", "500", "0",
					TEST_DAY_TODAY, "2", "0", "0", "1", "USD", "3.124",
					"1120.36", "50", "1", TEST_DAY_TODAY, "joeykan", "0",
					"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
					"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
					"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
					AUDIT_VALUE },
				{ "4", "DN", "0", "3", format.format(new Date()), "CQ",
						"229743", "BA", "PC", null, null, null, null, "bhchan",
						"30 Days", "MYR", "6", "0", "1500", "1500",
						TEST_DAY_TODAY, "2", "0", "0", "1", "USD", "3.124",
						"1120.36", "50", "1", TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						AUDIT_VALUE },
				{ "3", "DN", "1", "3", format.format(new Date()), "CQ",
						"229743", "BA", "PC", null, null, null, null, "bhchan",
						"30 Days", "MYR", "6", "0", "1500", "500",
						TEST_DAY_TODAY, "2", "0", "0", "1", "USD", "3.124",
						"1120.36", "50", "1", TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						AUDIT_VALUE }};
		super.insertData("T_BIL_H", tBilHData);
		
		String[][] dataT_CSB_H = {
				{ "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
						"PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
						"REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
						"IS_CLOSED", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
						"BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
						"ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
						"REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
				{ "111238              ", "229743", "141", "", "NT",
						"2012-04-12", "4", "MYR", "REMARK", "N", "Cheque No.",
						"Bank-In Slip No.", "0", "0", "2011-07-07",
						"2011-07-07", "BIF001", "2331", "3211", "0", null, "2",
						"0", "0", "2011-08-30", "1111", "0" } };
		this.insertData("T_CSB_H", dataT_CSB_H);
		
		String[][] dataM_BANK = {
				{"ID_BANK","BANK_FULL_NAME","BANK_CODE","BANK_NAME",
					"BRANCH_CODE","BRANCH_NAME","BANK_TEL_NO","BANK_FAX_NO",
					"IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
				{"380","Bank Money-XYZ","0123","Bank Money",
						"12","XYZ","","",
						"0",TEST_DAY_TODAY,TEST_DAY_TODAY,"USERFULL",null}};
		super.deleteAllData("M_BANK");
		super.insertData("M_BANK", dataM_BANK);
		
		// insert data to totalGiro > 0
		String[][] mCustBankinfoData = {
				{ "ID_CUST", "ID_GIRO_BANK", "GIRO_ACCT_NO", "GIRO_ACCT_NAME",
						"CCARD_TYPE", "CCARD_ACCT_NO", "CCARD_NO",
						"CCARD_HOLDER_NAME", "CCARD_EXPIRED_DATE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SWIFT_CODE", "CCARD_SECURITY_NO", "IS_DELETED",
						"NO_REFERENCE", "ID_AUDIT" },
				{ "229743", "380", "123-321", "123123123", "VISA", "123-123",
						"1234-1234-1234-1234", "CC-Singapore", "2011-12-01",
						"2011-02-25 10:57:49", "2011-02-25 10:57:49",
						"USERFULL", "12345", "123", "0", "", null } };
		super.deleteAllData("M_CUST_BANKINFO");
		super.insertData("M_CUST_BANKINFO", mCustBankinfoData);
		

		String[][] dataM_COM_BANKINFO = {
				{"ID_COM_BANK","ID_COM","ID_BANK","COM_SWIFT",
					"COM_ACCT_NO","COM_ACCT_TYPE","COM_ACCT_NAME",
					"DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT","COM_CUR"},
				{"0","11","398","",
						"11111","1","8888",
						TEST_DAY_TODAY,TEST_DAY_TODAY,"admin",null,"DZD"}};
		super.deleteAllData("M_COM_BANKINFO");
		super.insertData("M_COM_BANKINFO", dataM_COM_BANKINFO);
		
		gGirP01 = new G_GIR_P01(queryDAO, updateDAO, updateDAONuked);
		param.setScr(false);
		param.setBankAcc("0");
		GlobalProcessResult gpr = gGirP01.execute(param, outputDTO);
		assertEquals(true, gpr.getErrors().isEmpty());
		
		String idbatch = "223";
		Map<String, Object> T_GIR_EXP_HD = queryDAO.executeForMap("select.e_exp_f02.t_gir_exp_hd.002", idbatch);
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM/yyyy");
		Calendar c2 = Calendar.getInstance();
		assertEquals(T_GIR_EXP_HD.get("ID_LOGIN").toString(), "sysadmin");
		assertEquals(T_GIR_EXP_HD.get("FILENAME").toString(), "GIROdata_" + param.getClosingMonth() + param.getClosingYear().substring(2) + ".txt");
		assertEquals(T_GIR_EXP_HD.get("STATUS").toString(), "1");
		assertEquals(T_GIR_EXP_HD.get("CLOSE_MONTHYEAR").toString(), sdf1.format(c2.getTime()));
	
	}
	
	/**
	 * 
	 */
	public void testG_GIR_P01_14() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String filename = "GIROdata_" + param.getClosingMonth() + param.getClosingYear().substring(2) + ".txt";
		Calendar c1 = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String updateDate = sdf.format(c1.getTime());
		String[][] m_gset_dS1 = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },
				{
						"BATCH_G_GIR_P01",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "sysadmin", "C:\\OfficeSystem\\BATCH\\G_GIR_P01\\", "50929", "0" }};
		String[][] m_gset_hS1 = {
				{ "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED", "ID_LOGIN" },

				{
						"BATCH_G_GIR_P01",
						"Cash Book auto offset by payment method or customer",
						"Settings for Cash Book to auto offset by payment method or customer in batch.",
						"0", "sysadmin" } };
		this.deleteAllData("M_GSET_D");
		this.deleteAllData("M_GSET_H");
		this.insertData("M_GSET_H", m_gset_hS1);
		this.insertData("M_GSET_D", m_gset_dS1);
		
		this.deleteAllData("T_GIR_EXP_HD");
		this.deleteAllData("T_BATCH_LOG");
		
		String[][] dataGirExpHd = {
				{ "ID_GIR_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
						"DATE_UPLOADED", "STATUS", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", },
				{ "223", filename, "07/2011",
						"2011-08-22 00:00:00", "0", "2011-08-22 00:00:00",
						updateDate, "sysadmin", } };
		super.insertData("T_GIR_EXP_HD", dataGirExpHd);
		
		this.deleteAllData("T_BAC_D");
		this.deleteAllData("T_BAC_H");
		this.deleteAllData("T_BIL_D");
		this.deleteAllData("T_BIL_H");
		this.deleteAllData("T_CSB_D");
		this.deleteAllData("T_CSB_H");
		
		String[][] testDataTBatchLog5 = {
				{ "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
						"BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
						"EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "FIXED_FOREX", "IS_SINGPOST",
						"TOTAL_AMT_DUE" },
				{ "2", "229743", "GR", "MYR", "0", "", "BA", "PC", "0",
						"2011-1-18  12:43:28", "2011-1-18  12:43:28", "DM",
						"2", "1", "1000" },
				{ "3", "229743", "GR", "MYR", "0", "", "BA", "PC", "0",
							"2011-1-18  12:43:28", "2011-1-18  12:43:28", "DM",
							"2", "1", "1000" }};
		super.insertData("T_BAC_H", testDataTBatchLog5);
		
		String[][] tBilHData = {
				{ "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
						"PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
						"ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
						"TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
						"BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
						"IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
						"IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
						"EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
						"DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
						"ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
						"ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
						"CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
						"PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT" },
				{ "1", "IN", "0", "2", format.format(new Date()), "CQ",
						"229743", "BA", "PC", null, null, null, null, "bhchan",
						"30 Days", "MYR", "6", "0", "3500", "3000",
						TEST_DAY_TODAY, "2", "0", "0", "1", "USD", "3.124",
						"1120.36", "50", "1", TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						AUDIT_VALUE },
				{ "2", "DN", "0", "2", format.format(new Date()), "CQ",
					"229743", "BA", "PC", null, null, null, null, "bhchan",
					"30 Days", "MYR", "6", "0", "500", "0",
					TEST_DAY_TODAY, "2", "0", "0", "1", "USD", "3.124",
					"1120.36", "50", "1", TEST_DAY_TODAY, "joeykan", "0",
					"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
					"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
					"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
					AUDIT_VALUE },
				{ "4", "DN", "0", "3", format.format(new Date()), "CQ",
						"229743", "BA", "PC", null, null, null, null, "bhchan",
						"30 Days", "MYR", "6", "0", "1500", "1500",
						TEST_DAY_TODAY, "2", "0", "0", "1", "USD", "3.124",
						"1120.36", "50", "1", TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						AUDIT_VALUE },
				{ "3", "DN", "1", "3", format.format(new Date()), "CQ",
						"229743", "BA", "PC", null, null, null, null, "bhchan",
						"30 Days", "MYR", "6", "0", "1500", "500",
						TEST_DAY_TODAY, "2", "0", "0", "1", "USD", "3.124",
						"1120.36", "50", "1", TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						AUDIT_VALUE }};
		super.insertData("T_BIL_H", tBilHData);
		
		String[][] dataT_CSB_H = {
				{ "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
						"PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
						"REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
						"IS_CLOSED", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
						"BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
						"ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
						"REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
				{ "111238              ", "229743", "141", "", "NT",
						"2012-04-12", "4", "MYR", "REMARK", "N", "Cheque No.",
						"Bank-In Slip No.", "0", "0", "2011-07-07",
						"2011-07-07", "BIF001", "2331", "3211", "0", null, "2",
						"0", "0", "2011-08-30", "1111", "0" } };
		this.insertData("T_CSB_H", dataT_CSB_H);
		
		String[][] dataM_BANK = {
				{"ID_BANK","BANK_FULL_NAME","BANK_CODE","BANK_NAME",
					"BRANCH_CODE","BRANCH_NAME","BANK_TEL_NO","BANK_FAX_NO",
					"IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
				{"380","Bank Money-XYZ","0123","Bank Money",
						"12","XYZ","","",
						"0",TEST_DAY_TODAY,TEST_DAY_TODAY,"USERFULL",null}};
		super.deleteAllData("M_BANK");
		super.insertData("M_BANK", dataM_BANK);
		
		// insert data to totalGiro > 0
		String[][] mCustBankinfoData = {
				{ "ID_CUST", "ID_GIRO_BANK", "GIRO_ACCT_NO", "GIRO_ACCT_NAME",
						"CCARD_TYPE", "CCARD_ACCT_NO", "CCARD_NO",
						"CCARD_HOLDER_NAME", "CCARD_EXPIRED_DATE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SWIFT_CODE", "CCARD_SECURITY_NO", "IS_DELETED",
						"NO_REFERENCE", "ID_AUDIT" },
				{ "229743", "380", "123-321", "123123123", "VISA", "123-123",
						"1234-1234-1234-1234", "CC-Singapore", "2011-12-01",
						"2011-02-25 10:57:49", "2011-02-25 10:57:49",
						"USERFULL", "12345", "123", "0", "Wsdasdasas", null } };
		super.deleteAllData("M_CUST_BANKINFO");
		super.insertData("M_CUST_BANKINFO", mCustBankinfoData);
		

		String[][] dataM_COM_BANKINFO = {
				{"ID_COM_BANK","ID_COM","ID_BANK","COM_SWIFT",
					"COM_ACCT_NO","COM_ACCT_TYPE","COM_ACCT_NAME",
					"DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT","COM_CUR"},
				{"0","11","398","",
						"11111","1","8888",
						TEST_DAY_TODAY,TEST_DAY_TODAY,"admin",null,"DZD"}};
		super.deleteAllData("M_COM_BANKINFO");
		super.insertData("M_COM_BANKINFO", dataM_COM_BANKINFO);
		
		gGirP01 = new G_GIR_P01(queryDAO, updateDAO, updateDAONuked);
		param.setScr(false);
		param.setBankAcc("1");
		GlobalProcessResult gpr = gGirP01.execute(param, outputDTO);
		assertEquals(true, gpr.getErrors().isEmpty());
		
		gGirP01 = new G_GIR_P01(queryDAO, updateDAO, updateDAONuked);
		param.setScr(true);
		param.setBankAcc("1");
		gpr = gGirP01.execute(param, outputDTO);
		assertEquals("errors.ERR1SC073", gpr.getErrors().get().next().getKey());
	}
	
	/**
	 * 
	 */
	public void testG_GIR_P01_15() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String filename = "GIROdata_" + param.getClosingMonth() + param.getClosingYear().substring(2) + ".txt";
		Calendar c1 = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String updateDate = sdf.format(c1.getTime());
		String[][] m_gset_dS1 = {
				{ "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED", "ID_LOGIN",
						"SET_VALUE", "ID_AUDIT", "SET_APPLY" },
				{
						"BATCH_G_GIR_P01",
						"1",
						"Cash Book auto offset by billing account or customer - BAC: BILLING ACCOUNT, CST: CUSTOMER",
						"0", "sysadmin", "C:\\OfficeSystem\\BATCH\\G_GIR_P01\\", "50929", "0" }};
		String[][] m_gset_hS1 = {
				{ "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED", "ID_LOGIN" },

				{
						"BATCH_G_GIR_P01",
						"Cash Book auto offset by payment method or customer",
						"Settings for Cash Book to auto offset by payment method or customer in batch.",
						"0", "sysadmin" } };
		this.deleteAllData("M_GSET_D");
		this.deleteAllData("M_GSET_H");
		this.insertData("M_GSET_H", m_gset_hS1);
		this.insertData("M_GSET_D", m_gset_dS1);
		
		this.deleteAllData("T_GIR_EXP_HD");
		this.deleteAllData("T_BATCH_LOG");
		
		String[][] dataGirExpHd = {
				{ "ID_GIR_EXP_BATCH", "FILENAME", "CLOSE_MONTHYEAR",
						"DATE_UPLOADED", "STATUS", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", },
				{ "223", filename, "07/2011",
						"2011-08-22 00:00:00", "0", "2011-08-22 00:00:00",
						updateDate, "sysadmin", } };
		super.insertData("T_GIR_EXP_HD", dataGirExpHd);
		
		this.deleteAllData("T_BAC_D");
		this.deleteAllData("T_BAC_H");
		this.deleteAllData("T_BIL_D");
		this.deleteAllData("T_BIL_H");
		this.deleteAllData("T_CSB_D");
		this.deleteAllData("T_CSB_H");
		
		String[][] testDataTBatchLog5 = {
				{ "ID_BILL_ACCOUNT", "ID_CUST", "PAYMENT_METHOD",
						"BILL_CURRENCY", "IS_DISPLAY_EXP_AMT",
						"EXPORT_CURRENCY", "CUST_ADR_TYPE", "CONTACT_TYPE",
						"IS_DELETED", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "FIXED_FOREX", "IS_SINGPOST",
						"TOTAL_AMT_DUE" },
				{ "2", "229743", "GR", "MYR", "0", "", "BA", "PC", "0",
						"2011-1-18  12:43:28", "2011-1-18  12:43:28", "DM",
						"2", "1", "1000" },
				{ "3", "229743", "GR", "MYR", "0", "", "BA", "PC", "0",
							"2011-1-18  12:43:28", "2011-1-18  12:43:28", "DM",
							"2", "1", "1000" }};
		super.insertData("T_BAC_H", testDataTBatchLog5);
		
		String[][] tBilHData = {
				{ "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
						"PAY_METHOD", "ID_CUST", "ADR_TYPE", "CONTACT_TYPE",
						"ID_BIF", "ID_QCS", "QUO_REF", "CUST_PO", "ID_CONSULT",
						"TERM", "BILL_CURRENCY", "GST_PERCENT", "GST_AMOUNT",
						"BILL_AMOUNT", "PAID_AMOUNT", "LAST_PAYMENT_DATE",
						"IS_SETTLED", "IS_SINGPOST", "IS_EXPORT",
						"IS_DISPLAY_EXP_AMT", "EXPORT_CUR", "CUR_RATE",
						"EXPORT_AMOUNT", "FIXED_FOREX", "NO_PRINTED",
						"DATE_PRINTED", "USER_PRINTED", "IS_CLOSED",
						"ADDRESS1", "ADDRESS2", "ADDRESS3", "ADDRESS4",
						"ZIP_CODE", "COUNTRY", "TEL_NO", "FAX_NO",
						"CONTACT_NAME", "CONTACT_EMAIL", "IS_DELETED",
						"PREPARED_BY", "DATE_CREATED", "DATE_UPDATED",
						"ID_LOGIN", "ID_AUDIT" },
				{ "1", "IN", "0", "2", format.format(new Date()), "CQ",
						"229743", "BA", "PC", null, null, null, null, "bhchan",
						"30 Days", "MYR", "6", "0", "3500", "3000",
						TEST_DAY_TODAY, "2", "0", "0", "1", "USD", "3.124",
						"1120.36", "50", "1", TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						AUDIT_VALUE },
				{ "2", "DN", "0", "2", format.format(new Date()), "CQ",
					"229743", "BA", "PC", null, null, null, null, "bhchan",
					"30 Days", "MYR", "6", "0", "500", "0",
					TEST_DAY_TODAY, "2", "0", "0", "1", "USD", "3.124",
					"1120.36", "50", "1", TEST_DAY_TODAY, "joeykan", "0",
					"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
					"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
					"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
					AUDIT_VALUE },
				{ "4", "DN", "0", "3", format.format(new Date()), "CQ",
						"229743", "BA", "PC", null, null, null, null, "bhchan",
						"30 Days", "MYR", "6", "0", "1500", "1500",
						TEST_DAY_TODAY, "2", "0", "0", "1", "USD", "3.124",
						"1120.36", "50", "1", TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						AUDIT_VALUE },
				{ "3", "DN", "1", "3", format.format(new Date()), "CQ",
						"229743", "BA", "PC", null, null, null, null, "bhchan",
						"30 Days", "MYR", "6", "0", "1500", "500",
						TEST_DAY_TODAY, "2", "0", "0", "1", "USD", "3.124",
						"1120.36", "50", "1", TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						AUDIT_VALUE }};
		super.insertData("T_BIL_H", tBilHData);
		
		String[][] dataT_CSB_H = {
				{ "RECEIPT_NO", "ID_CUST", "ID_COM_BANK", "OTHER_PAYER",
						"PMT_METHOD", "DATE_TRANS", "RECEIPT_REF", "CUR_CODE",
						"REMARK", "PMT_STATUS", "REFERENCE1", "REFERENCE2",
						"IS_CLOSED", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "AMT_RECEIVED",
						"BANK_CHARGE", "BALANCE_AMT", "ID_AUDIT",
						"ID_BILL_ACCOUNT", "IS_EXPORT", "PAID_PRE_INVOICE",
						"REJECT_DATE", "REJECT_DESC", "OVER_PAID" },
				{ "111238              ", "229743", "141", "", "NT",
						"2012-04-12", "4", "MYR", "REMARK", "N", "Cheque No.",
						"Bank-In Slip No.", "0", "0", "2011-07-07",
						"2011-07-07", "BIF001", "2331", "3211", "0", null, "2",
						"0", "0", "2011-08-30", "1111", "0" } };
		this.insertData("T_CSB_H", dataT_CSB_H);
		
		String[][] dataM_BANK = {
				{"ID_BANK","BANK_FULL_NAME","BANK_CODE","BANK_NAME",
					"BRANCH_CODE","BRANCH_NAME","BANK_TEL_NO","BANK_FAX_NO",
					"IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
				{"380","Bank Money-XYZ","0123","Bank Money",
						"12","XYZ","","",
						"0",TEST_DAY_TODAY,TEST_DAY_TODAY,"USERFULL",null}};
		super.deleteAllData("M_BANK");
		super.insertData("M_BANK", dataM_BANK);
		
		// insert data to totalGiro > 0
		String[][] mCustBankinfoData = {
				{ "ID_CUST", "ID_GIRO_BANK", "GIRO_ACCT_NO", "GIRO_ACCT_NAME",
						"CCARD_TYPE", "CCARD_ACCT_NO", "CCARD_NO",
						"CCARD_HOLDER_NAME", "CCARD_EXPIRED_DATE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"SWIFT_CODE", "CCARD_SECURITY_NO", "IS_DELETED",
						"NO_REFERENCE", "ID_AUDIT" },
				{ "11111", "380", "123-321", "123123123", "VISA", "123-123",
						"1234-1234-1234-1234", "CC-Singapore", "2011-12-01",
						"2011-02-25 10:57:49", "2011-02-25 10:57:49",
						"USERFULL", "12345", "123", "0", "Wsdasdasas", null } };
		super.deleteAllData("M_CUST_BANKINFO");
		super.insertData("M_CUST_BANKINFO", mCustBankinfoData);
		

		String[][] dataM_COM_BANKINFO = {
				{"ID_COM_BANK","ID_COM","ID_BANK","COM_SWIFT",
					"COM_ACCT_NO","COM_ACCT_TYPE","COM_ACCT_NAME",
					"DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT","COM_CUR"},
				{"0","11","398","",
						"11111","1","8888",
						TEST_DAY_TODAY,TEST_DAY_TODAY,"admin",null,"DZD"}};
		super.deleteAllData("M_COM_BANKINFO");
		super.insertData("M_COM_BANKINFO", dataM_COM_BANKINFO);
		
		gGirP01 = new G_GIR_P01(queryDAO, updateDAO, updateDAONuked);
		param.setScr(true);
		param.setBankAcc("1");
		GlobalProcessResult gpr = gGirP01.execute(param, outputDTO);
		assertEquals("errors.ERR1SC071", gpr.getErrors().get().next().getKey());
	}
}
