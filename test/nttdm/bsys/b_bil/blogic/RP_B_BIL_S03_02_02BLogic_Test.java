package nttdm.bsys.b_bil.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S03_02_02Input;
import nttdm.bsys.common.bean.T_BIL_H;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;

public class RP_B_BIL_S03_02_02BLogic_Test extends AbstractUtilTest {

	RP_B_BIL_S03_02_02BLogic logic = new RP_B_BIL_S03_02_02BLogic();
	BillingSystemUserValueObject uvo = null;
	
	@Override
	protected void setUpData() throws Exception {
		/**
		 * delete all data
		 */
		super.deleteAllData("M_GSET_D");
		super.deleteAllData("M_GSET_H");

		String[][] dataM_GSET_H ={{
			"ID_SET","SET_NAME","SET_DESC","IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN"},
			{"CB_AUTO_OFFSET","Cash Book auto offset by payment method or customer",
				"Settings for Cash Book to auto offset by payment method or customer in batch.",
				"0",TEST_DAY_TODAY,TEST_DAY_TODAY,"sysadmin"}};
			
			String[][] dataM_GSET_D ={{
				"ID_SET","SET_SEQ","SET_DESC","IS_DELETED","DATE_CREATED",
				"DATE_UPDATED","ID_LOGIN","SET_VALUE","ID_AUDIT","SET_APPLY"},
				{"CB_AUTO_OFFSET","16","Cash Book auto offset by payment method or customer","0",TEST_DAY_TODAY,
					TEST_DAY_TODAY,"USERFULL","BAC",null,"1"}};
		
		
			/**
			 * insert data
			 */
			super.insertData("M_GSET_H", dataM_GSET_H);
			super.insertData("M_GSET_D", dataM_GSET_D);
	}

	public void testExecute1() throws Exception {
		uvo = new BillingSystemUserValueObject();
		uvo.setId_user("sysadmin");
		
		RP_B_BIL_S03_02_02Input param = new RP_B_BIL_S03_02_02Input();
		T_BIL_H inputHeaderInfo = new T_BIL_H();
		
		inputHeaderInfo.setBillType("IN");
		param.setInputHeaderInfo(inputHeaderInfo);
		param.setUvo(uvo);
		//
        String[] itemNo={""};
        //
        String[] itemDesc={""};
        //
        String[] itemQty={""};
        //
        String[] itemUprice={""};
        
        param.setItemNo(itemNo);
        param.setItemDesc(itemDesc);
        param.setItemQty(itemQty);
        param.setItemUprice(itemUprice);
		
		
		logic.setQueryDAO(this.queryDAO);
		logic.setUpdateDAO(this.updateDAO);
		
		//BLogicResult result = logic.execute(param);
		
		//assertEquals("fail", result.getResultString());
	}
	
	public void testExecute5() throws Exception {
        uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        
        RP_B_BIL_S03_02_02Input param = new RP_B_BIL_S03_02_02Input();
        T_BIL_H inputHeaderInfo = new T_BIL_H();
        
        inputHeaderInfo.setBillType("CN");
        param.setInputHeaderInfo(inputHeaderInfo);
        param.setUvo(uvo);
        //
        String[] itemNo={"","1","2"};
        //
        String[] itemDesc={"","",""};
        //
        String[] itemQty={"","","2"};
        //
        String[] itemUprice={"","","2"};
        
        param.setItemNo(itemNo);
        param.setItemDesc(itemDesc);
        param.setItemQty(itemQty);
        param.setItemUprice(itemUprice);
        
        
        logic.setQueryDAO(this.queryDAO);
        logic.setUpdateDAO(this.updateDAO);
        
        //BLogicResult result = logic.execute(param);
        
        //assertEquals("fail", result.getResultString());
    }
	
	public void testExecute6() throws Exception {
        uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        
        RP_B_BIL_S03_02_02Input param = new RP_B_BIL_S03_02_02Input();
        T_BIL_H inputHeaderInfo = new T_BIL_H();
        
        inputHeaderInfo.setBillType("DN");
        param.setInputHeaderInfo(inputHeaderInfo);
        param.setUvo(uvo);
        //
        String[] itemNo={"","1","2"};
        //
        String[] itemDesc={"","",""};
        //
        String[] itemQty={"","","2"};
        //
        String[] itemUprice={"","","2"};
        
        param.setItemNo(itemNo);
        param.setItemDesc(itemDesc);
        param.setItemQty(itemQty);
        param.setItemUprice(itemUprice);
        
        
        logic.setQueryDAO(this.queryDAO);
        logic.setUpdateDAO(this.updateDAO);
        
        //BLogicResult result = logic.execute(param);
        
        //assertEquals("fail", result.getResultString());
    }
	
	public void testExecute2() throws Exception {
		//
		insertData();
		//
		insertTBilHAndDIn();
		
		uvo = new BillingSystemUserValueObject();
		uvo.setId_user("sysadmin");
		
		RP_B_BIL_S03_02_02Input param = new RP_B_BIL_S03_02_02Input();
		T_BIL_H inputHeaderInfo = new T_BIL_H();
		//
		inputHeaderInfo.setBillType("IN");
		//
		inputHeaderInfo.setIdCust("229743");
		//
		inputHeaderInfo.setAdrType("BA");
		//
		inputHeaderInfo.setDateReq("01/02/2011");
		//
		inputHeaderInfo.setBillCurrency("MYR");
		//
		inputHeaderInfo.setIdConsult("sysadmin");
		//
		inputHeaderInfo.setGstAmount("1");
		//
		inputHeaderInfo.setIsClosed("0");
		//
        inputHeaderInfo.setBillAmount("12");
		//
		Integer[] itemId={0,1,0};
		//
		String[] itemNo={"","1","2"};
		//
		String[] itemDesc={"","aa","bb"};
		//
		String[] itemQty={"","1","2"};
		//
		String[] itemUprice={"","1","2"};
		//
		String[] itemAtm={"","1","4"};
		
		param.setItemId(itemId);
		param.setItemNo(itemNo);
		param.setItemDesc(itemDesc);
		param.setItemQty(itemQty);
		param.setItemUprice(itemUprice);
		param.setItemAtm(itemAtm);
		param.setIdRef("11000773            ");
		
		
		param.setInputHeaderInfo(inputHeaderInfo);
		param.setUvo(uvo);
		
		
		logic.setQueryDAO(this.queryDAO);
		logic.setUpdateDAO(this.updateDAO);
		
		//BLogicResult result = logic.execute(param);
		
		//assertEquals("success", result.getResultString());
	}
	
	public void testExecute3() throws Exception {
		//
		insertData();
		//
		insertTBilHAndDDn();
		
		uvo = new BillingSystemUserValueObject();
		uvo.setId_user("sysadmin");
		
		RP_B_BIL_S03_02_02Input param = new RP_B_BIL_S03_02_02Input();
		T_BIL_H inputHeaderInfo = new T_BIL_H();
		//
		inputHeaderInfo.setBillType("DN");
		//
		inputHeaderInfo.setIdCust("229743");
		//
		inputHeaderInfo.setAdrType("BA");
		//
		inputHeaderInfo.setDateReq("01/02/2011");
		//
		inputHeaderInfo.setBillCurrency("MYR");
		//
		inputHeaderInfo.setIdConsult("sysadmin");
		//
		inputHeaderInfo.setGstAmount("1");
		//
		inputHeaderInfo.setIsClosed("0");
		//
        inputHeaderInfo.setBillAmount("12");
		//
		Integer[] itemId={0,1,0};
		//
		String[] itemNo={"","1","2"};
		//
		String[] itemDesc={"","aa","bb"};
		//
		String[] itemQty={"","1","2"};
		//
		String[] itemUprice={"","1","2"};
		//
		String[] itemAtm={"","1","4"};
		
		param.setItemId(itemId);
		param.setItemNo(itemNo);
		param.setItemDesc(itemDesc);
		param.setItemQty(itemQty);
		param.setItemUprice(itemUprice);
		param.setItemAtm(itemAtm);
		param.setIdRef("11000014            ");
		
		
		param.setInputHeaderInfo(inputHeaderInfo);
		param.setUvo(uvo);
		
		
		logic.setQueryDAO(this.queryDAO);
		logic.setUpdateDAO(this.updateDAO);
		
		//BLogicResult result = logic.execute(param);
		
		//assertEquals("success", result.getResultString());
	}
	
	public void testExecute4() throws Exception {
		//
		insertData();
		//
		insertTBilHAndDCn();
		
		uvo = new BillingSystemUserValueObject();
		uvo.setId_user("sysadmin");
		
		RP_B_BIL_S03_02_02Input param = new RP_B_BIL_S03_02_02Input();
		T_BIL_H inputHeaderInfo = new T_BIL_H();
		//
		inputHeaderInfo.setBillType("CN");
		//
		inputHeaderInfo.setIdCust("229743");
		//
		inputHeaderInfo.setAdrType("BA");
		//
		inputHeaderInfo.setDateReq("01/02/2011");
		//
		inputHeaderInfo.setBillCurrency("MYR");
		//
		inputHeaderInfo.setIdConsult("sysadmin");
		//
		inputHeaderInfo.setGstAmount("1");
		//
		inputHeaderInfo.setIsClosed("0");
		//
        inputHeaderInfo.setBillAmount("12");
		//
		Integer[] itemId={0,1,0};
		//
		String[] itemNo={"","1","2"};
		//
		String[] itemDesc={"","aa","bb"};
		//
		String[] itemQty={"","1","2"};
		//
		String[] itemUprice={"","1","2"};
		//
		String[] itemAtm={"","1","4"};
		
		param.setItemId(itemId);
		param.setItemNo(itemNo);
		param.setItemDesc(itemDesc);
		param.setItemQty(itemQty);
		param.setItemUprice(itemUprice);
		param.setItemAtm(itemAtm);
		param.setIdRef("20110826000006      ");
		
		
		param.setInputHeaderInfo(inputHeaderInfo);
		param.setUvo(uvo);
		
		
		logic.setQueryDAO(this.queryDAO);
		logic.setUpdateDAO(this.updateDAO);
		
		//BLogicResult result = logic.execute(param);
		
		//assertEquals("success", result.getResultString());
	}
	
	public void testExecute7() throws Exception {
        //
        insertData7();
        //
        insertTBilHAndDCn7();
        
        uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        
        RP_B_BIL_S03_02_02Input param = new RP_B_BIL_S03_02_02Input();
        T_BIL_H inputHeaderInfo = new T_BIL_H();
        //
        inputHeaderInfo.setBillType("CN");
        //
        inputHeaderInfo.setIdCust("229743");
        //
        inputHeaderInfo.setAdrType("BA");
        //
        inputHeaderInfo.setDateReq("01/02/2011");
        //
        inputHeaderInfo.setBillCurrency("MYR");
        //
        inputHeaderInfo.setIdConsult("sysadmin");
        //
        inputHeaderInfo.setGstAmount("1");
        //
        inputHeaderInfo.setIsClosed("0");
        //
        inputHeaderInfo.setBillAmount("12");
        //
        inputHeaderInfo.setBillAcc("111");
        //
        Integer[] itemId={0,1,0};
        //
        String[] itemNo={"","1","2"};
        //
        String[] itemDesc={"","aa","bb"};
        //
        String[] itemQty={"","1","2"};
        //
        String[] itemUprice={"","1","2"};
        //
        String[] itemAtm={"","1","4"};
        
        param.setItemId(itemId);
        param.setItemNo(itemNo);
        param.setItemDesc(itemDesc);
        param.setItemQty(itemQty);
        param.setItemUprice(itemUprice);
        param.setItemAtm(itemAtm);
        param.setIdRef("20110826000006      ");
        
        
        param.setInputHeaderInfo(inputHeaderInfo);
        param.setUvo(uvo);
        
        
        logic.setQueryDAO(this.queryDAO);
        logic.setUpdateDAO(this.updateDAO);
        
        //BLogicResult result = logic.execute(param);
        
        //assertEquals("success", result.getResultString());
    }
	
	public void testExecute8() throws Exception {
        //
        insertData7();
        //
        insertTBilHAndDCn7();
        
        uvo = new BillingSystemUserValueObject();
        uvo.setId_user("sysadmin");
        
        RP_B_BIL_S03_02_02Input param = new RP_B_BIL_S03_02_02Input();
        T_BIL_H inputHeaderInfo = new T_BIL_H();
        //
        inputHeaderInfo.setBillType("CN");
        //
        inputHeaderInfo.setIdCust("229743");
        //
        inputHeaderInfo.setAdrType("BA");
        //
        inputHeaderInfo.setDateReq("01/02/2011");
        //
        inputHeaderInfo.setBillCurrency("MYR");
        //
        inputHeaderInfo.setIdConsult("sysadmin");
        //
        inputHeaderInfo.setGstAmount("1");
        //
        inputHeaderInfo.setIsClosed("0");
        //
        inputHeaderInfo.setBillAmount("12");
        //
        inputHeaderInfo.setBillAcc("1");
        //
        Integer[] itemId={0,1,0};
        //
        String[] itemNo={"","1","2"};
        //
        String[] itemDesc={"","aa","bb"};
        //
        String[] itemQty={"","1","2"};
        //
        String[] itemUprice={"","1","2"};
        //
        String[] itemAtm={"","1","4"};
        
        param.setItemId(itemId);
        param.setItemNo(itemNo);
        param.setItemDesc(itemDesc);
        param.setItemQty(itemQty);
        param.setItemUprice(itemUprice);
        param.setItemAtm(itemAtm);
        param.setIdRef("20110826000006      ");
        
        
        param.setInputHeaderInfo(inputHeaderInfo);
        param.setUvo(uvo);
        
        
        logic.setQueryDAO(this.queryDAO);
        logic.setUpdateDAO(this.updateDAO);
        
        //BLogicResult result = logic.execute(param);
        
        //assertEquals("success", result.getResultString());
    }
	
	private void insertData(){
		/**
		 * delete all data
		 */
		super.deleteAllData("M_GSET_D");
		super.deleteAllData("M_GSET_H");
		super.deleteAllData("M_CUST_ADR");
		super.deleteAllData("M_CODE");
		super.deleteAllData("M_CUST");

		String[][] dataM_GSET_H ={{
			"ID_SET","SET_NAME","SET_DESC","IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN"},
			{"CB_AUTO_OFFSET","Cash Book auto offset by payment method or customer",
				"Settings for Cash Book to auto offset by payment method or customer in batch.",
				"0",TEST_DAY_TODAY,TEST_DAY_TODAY,"sysadmin"}};
			
		String[][] dataM_GSET_D ={{
				"ID_SET","SET_SEQ","SET_DESC","IS_DELETED","DATE_CREATED",
				"DATE_UPDATED","ID_LOGIN","SET_VALUE","ID_AUDIT","SET_APPLY"},
				{"CB_AUTO_OFFSET","16","Cash Book auto offset by payment method or customer","0",TEST_DAY_TODAY,
					TEST_DAY_TODAY,"USERFULL","CST",null,"1"}};
		
		String[][] dataM_GSET_H1 ={{
			"ID_SET","SET_NAME","SET_DESC","IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN"},
			{"GST","Government Service Tax","Settings for billing/invoice.","0",TEST_DAY_TODAY,TEST_DAY_TODAY,"sysadmin"}};
			
		String[][] dataM_GSET_D1 ={{
				"ID_SET","SET_SEQ","SET_DESC","IS_DELETED","DATE_CREATED",
				"DATE_UPDATED","ID_LOGIN","SET_VALUE","ID_AUDIT","SET_APPLY"},
				{"GST","1","Tax value in %","0",TEST_DAY_TODAY,TEST_DAY_TODAY,"USERFULL","5",null,"1"}};
		
		String[][] dataM_CUST_ADR ={{"ID_CUST","ADR_TYPE","ADR_LINE1","ADR_LINE2","ADR_LINE3",
			"COUNTRY","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ZIP_CODE","IS_DELETED","ID_AUDIT"},
			{"229743","BA","123 Nguyen Thi Minh Khai","District 1",
				"Ho Chi Minh City","VN",TEST_DAY_TODAY,TEST_DAY_TODAY,"USERFULL","+84","0",null}};
		
		String[][] dataM_CODE1 ={{"ID_CODE","ID_SUB_CODE","TYPE_VAL","INIT_VAL","CUR_VAL","IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
			{"INVNO","1","01","11","2011","0",TEST_DAY_TODAY,TEST_DAY_TODAY,"sysadmin",null}};
		String[][] dataM_CODE2 ={{"ID_CODE","ID_SUB_CODE","TYPE_VAL","INIT_VAL","CUR_VAL","IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
				{"INVNO","2","06","","1234","0",TEST_DAY_TODAY,TEST_DAY_TODAY,"sysadmin",null}};
		String[][] dataM_CODE3 ={{"ID_CODE","ID_SUB_CODE","TYPE_VAL","INIT_VAL","CUR_VAL","IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
				{"INVNO","3","05","000763","000772","0",TEST_DAY_TODAY,TEST_DAY_TODAY,"sysadmin",null}};
		
		
		String[][] dataM_CODE4 ={{"ID_CODE","ID_SUB_CODE","TYPE_VAL","INIT_VAL","CUR_VAL","IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
				{"DBTNO","1","01","11","2011","0",TEST_DAY_TODAY,TEST_DAY_TODAY,"sysadmin",null}};
		String[][] dataM_CODE5 ={{"ID_CODE","ID_SUB_CODE","TYPE_VAL","INIT_VAL","CUR_VAL","IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
				{"DBTNO","2","06","","ew34","0",TEST_DAY_TODAY,TEST_DAY_TODAY,"sysadmin",null}};
		String[][] dataM_CODE6 ={{"ID_CODE","ID_SUB_CODE","TYPE_VAL","INIT_VAL","CUR_VAL","IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
				{"DBTNO","3","05","000008","000013","0",TEST_DAY_TODAY,TEST_DAY_TODAY,"sysadmin",null}};
		
		String[][] dataM_CODE7 ={{"ID_CODE","ID_SUB_CODE","TYPE_VAL","INIT_VAL","CUR_VAL","IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
				{"CDTNO","1","03","20110819","2011","0",TEST_DAY_TODAY,TEST_DAY_TODAY,"sysadmin",null}};
		String[][] dataM_CODE8 ={{"ID_CODE","ID_SUB_CODE","TYPE_VAL","INIT_VAL","CUR_VAL","IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
				{"CDTNO","2","06","","77","0",TEST_DAY_TODAY,TEST_DAY_TODAY,"sysadmin",null}};
		String[][] dataM_CODE9 ={{"ID_CODE","ID_SUB_CODE","TYPE_VAL","INIT_VAL","CUR_VAL","IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
				{"CDTNO","3","05","000000","000005","0",TEST_DAY_TODAY,TEST_DAY_TODAY,"sysadmin",null}};
		
		String[][] dataM_CUST = {
                { "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO",
                        "CO_WEBSITE", "CO_EMAIL", "CO_TEL_NO", "CO_FAX_NO",
                        "INTER_COMP", "IS_EXPORTED", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE",
                        "HOME_TEL_NO", "HOME_FAX_NO", "PRINT_STATEMENT",
                        "GBOC_ACC_NO", "OTHERS_REF_NO", "CUSTOMER_TYPE",
                        "SALES_FORCE_ACC_ID", "AFFILIATE_CODE", "ID_AUDIT",
                        "MOBILE_NO" },
                { "229743", "0123456789", "Duy Duong", "0123456789",
                        "http://www.nttdata.com.vn",
                        "duong.nguyen@nttdata.com.vn", "0123456789",
                        "0123456789", "0", "0", "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "USERFULL", "", "", null, "", "", "0",
                        "0123456789", "0123456789", "CORP", "0123456789", "",
                        null, "" } };
		
		/**
		 * insert data
		 */
		super.insertData("M_GSET_H", dataM_GSET_H);
		super.insertData("M_GSET_D", dataM_GSET_D);
		
		super.insertData("M_GSET_H", dataM_GSET_H1);
		super.insertData("M_GSET_D", dataM_GSET_D1);
		
		super.insertData("M_CUST_ADR", dataM_CUST_ADR);
		
		super.insertData("M_CODE", dataM_CODE1);
		super.insertData("M_CODE", dataM_CODE2);
		super.insertData("M_CODE", dataM_CODE3);
		super.insertData("M_CODE", dataM_CODE4);
		super.insertData("M_CODE", dataM_CODE5);
		super.insertData("M_CODE", dataM_CODE6);
		super.insertData("M_CODE", dataM_CODE7);
		super.insertData("M_CODE", dataM_CODE8);
		super.insertData("M_CODE", dataM_CODE9);
		
		super.insertData("M_CUST", dataM_CUST);
	}
	
	private void insertTBilHAndDIn(){
		/**
		 * delete all data
		 */
		super.deleteAllData("T_BIL_D");
		super.deleteAllData("T_BIL_H");
		
		String[][] dataT_BIL_H ={{ "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
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
			{ "11000773            ", "IN", "1", "1", TEST_DAY_TODAY, "CQ", "229743", "BA",
			"PC", null, null, null, null, "bhchan", "30 Days",
			"MYR", "6", "0", "3500", "0", TEST_DAY_TODAY, "0", "0",
			"0", "1", "USD", "3.124", "1120.36", "50", "1",
			TEST_DAY_TODAY, "joeykan", "0",
			"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
			"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
			"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
			null } };
		
		String[][] dataT_BIL_D ={{"ID_REF","ITEM_ID","ITEM_LEVEL","ITEM_NO",
			"ITEM_DESC","ITEM_QTY","ITEM_UPRICE","ITEM_AMT","ITEM_GST",
			"APPLY_GST","IS_DISPLAY","ID_CUST_PLAN","ID_CUST_PLAN_GRP",
			"ID_CUST_PLAN_LINK","SVC_LEVEL1","SVC_LEVEL2","BILL_FROM","BILL_TO",
			"JOB_NO","IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN",
			"ID_AUDIT","ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT"},
				{"11000773            ","1","0","1         ",null,
				"4","5","20","76","1","1",null,null,null,null,null,TEST_DAY_TODAY,TEST_DAY_TODAY,"","0",
				TEST_DAY_TODAY,TEST_DAY_TODAY,"sysadmin",null,null,"0","1"}};
		
		
		super.insertData("T_BIL_H", dataT_BIL_H);
		super.insertData("T_BIL_D", dataT_BIL_D);
	}
	
	private void insertTBilHAndDDn(){
		/**
		 * delete all data
		 */
		super.deleteAllData("T_BIL_D");
		super.deleteAllData("T_BIL_H");
		
		String[][] dataT_BIL_H ={{ "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
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
			{ "11000014            ", "IN", "1", "1", TEST_DAY_TODAY, "CQ", "229743", "BA",
			"PC", null, null, null, null, "bhchan", "30 Days",
			"MYR", "6", "0", "3500", "0", TEST_DAY_TODAY, "0", "0",
			"0", "1", "USD", "3.124", "1120.36", "50", "1",
			TEST_DAY_TODAY, "joeykan", "0",
			"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
			"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
			"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
			null } };
		
		String[][] dataT_BIL_D ={{"ID_REF","ITEM_ID","ITEM_LEVEL","ITEM_NO",
			"ITEM_DESC","ITEM_QTY","ITEM_UPRICE","ITEM_AMT","ITEM_GST",
			"APPLY_GST","IS_DISPLAY","ID_CUST_PLAN","ID_CUST_PLAN_GRP",
			"ID_CUST_PLAN_LINK","SVC_LEVEL1","SVC_LEVEL2","BILL_FROM","BILL_TO",
			"JOB_NO","IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN",
			"ID_AUDIT","ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT"},
				{"11000014            ","1","0","1         ",null,
				"4","5","20","76","1","1",null,null,null,null,null,TEST_DAY_TODAY,TEST_DAY_TODAY,"","0",
				TEST_DAY_TODAY,TEST_DAY_TODAY,"sysadmin",null,null,"0","1"}};
		
		super.insertData("T_BIL_H", dataT_BIL_H);
		super.insertData("T_BIL_D", dataT_BIL_D);
	}
	
	private void insertTBilHAndDCn(){
		/**
		 * delete all data
		 */
		super.deleteAllData("T_BIL_D");
		super.deleteAllData("T_BIL_H");
		
		String[][] dataT_BIL_H ={{ "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
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
			{ "20110826000006      ", "IN", "1", "1", TEST_DAY_TODAY, "CQ", "229743", "BA",
			"PC", null, null, null, null, "bhchan", "30 Days",
			"MYR", "6", "0", "3500", "0", TEST_DAY_TODAY, "0", "0",
			"0", "1", "USD", "3.124", "1120.36", "50", "1",
			TEST_DAY_TODAY, "joeykan", "0",
			"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
			"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
			"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
			null } };
		
		String[][] dataT_BIL_D ={{"ID_REF","ITEM_ID","ITEM_LEVEL","ITEM_NO",
			"ITEM_DESC","ITEM_QTY","ITEM_UPRICE","ITEM_AMT","ITEM_GST",
			"APPLY_GST","IS_DISPLAY","ID_CUST_PLAN","ID_CUST_PLAN_GRP",
			"ID_CUST_PLAN_LINK","SVC_LEVEL1","SVC_LEVEL2","BILL_FROM","BILL_TO",
			"JOB_NO","IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN",
			"ID_AUDIT","ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT"},
				{"20110826000006      ","1","0","1         ",null,
				"4","5","20","76","1","1",null,null,null,null,null,TEST_DAY_TODAY,TEST_DAY_TODAY,"","0",
				TEST_DAY_TODAY,TEST_DAY_TODAY,"sysadmin",null,null,"0","1"}};
		
		super.insertData("T_BIL_H", dataT_BIL_H);
		super.insertData("T_BIL_D", dataT_BIL_D);
	}

	private void insertData7(){
        /**
         * delete all data
         */
        super.deleteAllData("M_GSET_D");
        super.deleteAllData("M_GSET_H");
        super.deleteAllData("M_CUST_ADR");
        super.deleteAllData("M_CODE");
        super.deleteAllData("M_CUST");
        
        String[][] dataM_GSET_H ={{
            "ID_SET","SET_NAME","SET_DESC","IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN"},
            {"CB_AUTO_OFFSET","Cash Book auto offset by payment method or customer",
                "Settings for Cash Book to auto offset by payment method or customer in batch.",
                "0",TEST_DAY_TODAY,TEST_DAY_TODAY,"sysadmin"}};
            
            String[][] dataM_GSET_D ={{
                "ID_SET","SET_SEQ","SET_DESC","IS_DELETED","DATE_CREATED",
                "DATE_UPDATED","ID_LOGIN","SET_VALUE","ID_AUDIT","SET_APPLY"},
                {"CB_AUTO_OFFSET","16","Cash Book auto offset by payment method or customer","0",TEST_DAY_TODAY,
                    TEST_DAY_TODAY,"USERFULL","BAC",null,"1"}};
            
        String[][] dataM_GSET_H1 ={{
            "ID_SET","SET_NAME","SET_DESC","IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN"},
            {"GST","Government Service Tax","Settings for billing/invoice.","0",TEST_DAY_TODAY,TEST_DAY_TODAY,"sysadmin"}};
            
        String[][] dataM_GSET_D1 ={{
                "ID_SET","SET_SEQ","SET_DESC","IS_DELETED","DATE_CREATED",
                "DATE_UPDATED","ID_LOGIN","SET_VALUE","ID_AUDIT","SET_APPLY"},
                {"GST","1","Tax value in %","0",TEST_DAY_TODAY,TEST_DAY_TODAY,"USERFULL","5",null,"1"}};
        
        String[][] dataM_CUST_ADR ={{"ID_CUST","ADR_TYPE","ADR_LINE1","ADR_LINE2","ADR_LINE3",
            "COUNTRY","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ZIP_CODE","IS_DELETED","ID_AUDIT"},
            {"229743","BA","123 Nguyen Thi Minh Khai","District 1",
                "Ho Chi Minh City","VN",TEST_DAY_TODAY,TEST_DAY_TODAY,"USERFULL","+84","0",null}};
        
        String[][] dataM_CODE1 ={{"ID_CODE","ID_SUB_CODE","TYPE_VAL","INIT_VAL","CUR_VAL","IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
            {"INVNO","1","01","11","2011","0",TEST_DAY_TODAY,TEST_DAY_TODAY,"sysadmin",null}};
        String[][] dataM_CODE2 ={{"ID_CODE","ID_SUB_CODE","TYPE_VAL","INIT_VAL","CUR_VAL","IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
                {"INVNO","2","06","","1234","0",TEST_DAY_TODAY,TEST_DAY_TODAY,"sysadmin",null}};
        String[][] dataM_CODE3 ={{"ID_CODE","ID_SUB_CODE","TYPE_VAL","INIT_VAL","CUR_VAL","IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
                {"INVNO","3","05","000763","000772","0",TEST_DAY_TODAY,TEST_DAY_TODAY,"sysadmin",null}};
        
        
        String[][] dataM_CODE4 ={{"ID_CODE","ID_SUB_CODE","TYPE_VAL","INIT_VAL","CUR_VAL","IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
                {"DBTNO","1","01","11","2011","0",TEST_DAY_TODAY,TEST_DAY_TODAY,"sysadmin",null}};
        String[][] dataM_CODE5 ={{"ID_CODE","ID_SUB_CODE","TYPE_VAL","INIT_VAL","CUR_VAL","IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
                {"DBTNO","2","06","","ew34","0",TEST_DAY_TODAY,TEST_DAY_TODAY,"sysadmin",null}};
        String[][] dataM_CODE6 ={{"ID_CODE","ID_SUB_CODE","TYPE_VAL","INIT_VAL","CUR_VAL","IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
                {"DBTNO","3","05","000008","000013","0",TEST_DAY_TODAY,TEST_DAY_TODAY,"sysadmin",null}};
        
        String[][] dataM_CODE7 ={{"ID_CODE","ID_SUB_CODE","TYPE_VAL","INIT_VAL","CUR_VAL","IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
                {"CDTNO","1","03","20110819","2011","0",TEST_DAY_TODAY,TEST_DAY_TODAY,"sysadmin",null}};
        String[][] dataM_CODE8 ={{"ID_CODE","ID_SUB_CODE","TYPE_VAL","INIT_VAL","CUR_VAL","IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
                {"CDTNO","2","06","","77","0",TEST_DAY_TODAY,TEST_DAY_TODAY,"sysadmin",null}};
        String[][] dataM_CODE9 ={{"ID_CODE","ID_SUB_CODE","TYPE_VAL","INIT_VAL","CUR_VAL","IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN","ID_AUDIT"},
                {"CDTNO","3","05","000000","000005","0",TEST_DAY_TODAY,TEST_DAY_TODAY,"sysadmin",null}};
        
        String[][] dataM_CUST = {
                { "ID_CUST", "CUST_ACC_NO", "CUST_NAME", "CO_REGNO",
                        "CO_WEBSITE", "CO_EMAIL", "CO_TEL_NO", "CO_FAX_NO",
                        "INTER_COMP", "IS_EXPORTED", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SALUTATION", "CUST_ID_NO", "CUST_BIRTH_DATE",
                        "HOME_TEL_NO", "HOME_FAX_NO", "PRINT_STATEMENT",
                        "GBOC_ACC_NO", "OTHERS_REF_NO", "CUSTOMER_TYPE",
                        "SALES_FORCE_ACC_ID", "AFFILIATE_CODE", "ID_AUDIT",
                        "MOBILE_NO" },
                { "229743", "0123456789", "Duy Duong", "0123456789",
                        "http://www.nttdata.com.vn",
                        "duong.nguyen@nttdata.com.vn", "0123456789",
                        "0123456789", "0", "0", "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "USERFULL", "", "", null, "", "", "0",
                        "0123456789", "0123456789", "1", "0123456789", "",
                        null, "" } };
        /**
         * insert data
         */
        super.insertData("M_GSET_H", dataM_GSET_H);
        super.insertData("M_GSET_D", dataM_GSET_D);
        super.insertData("M_GSET_H", dataM_GSET_H1);
        super.insertData("M_GSET_D", dataM_GSET_D1);
        
        super.insertData("M_CUST_ADR", dataM_CUST_ADR);
        
        super.insertData("M_CODE", dataM_CODE1);
        super.insertData("M_CODE", dataM_CODE2);
        super.insertData("M_CODE", dataM_CODE3);
        super.insertData("M_CODE", dataM_CODE4);
        super.insertData("M_CODE", dataM_CODE5);
        super.insertData("M_CODE", dataM_CODE6);
        super.insertData("M_CODE", dataM_CODE7);
        super.insertData("M_CODE", dataM_CODE8);
        super.insertData("M_CODE", dataM_CODE9);
        
        super.insertData("M_CUST", dataM_CUST);
    }
	
	private void insertTBilHAndDCn7(){
        /**
         * delete all data
         */
        super.deleteAllData("T_BIL_D");
        super.deleteAllData("T_BIL_H");
        
        String[][] dataT_BIL_H ={{ "ID_REF", "BILL_TYPE", "IS_MANUAL", "BILL_ACC", "DATE_REQ",
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
            { "20110826000006      ", "CN", "1", "1", TEST_DAY_TODAY, "CQ", "229743", "BA",
            "PC", null, null, null, null, "bhchan", "30 Days",
            "MYR", "6", "0", "3500", "0", TEST_DAY_TODAY, "0", "0",
            "0", "1", "USD", "3.124", "1120.36", "50", "1",
            TEST_DAY_TODAY, "joeykan", "0",
            "Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
            "2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
            "w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
            null } };
        
        String[][] dataT_BIL_D ={{"ID_REF","ITEM_ID","ITEM_LEVEL","ITEM_NO",
            "ITEM_DESC","ITEM_QTY","ITEM_UPRICE","ITEM_AMT","ITEM_GST",
            "APPLY_GST","IS_DISPLAY","ID_CUST_PLAN","ID_CUST_PLAN_GRP",
            "ID_CUST_PLAN_LINK","SVC_LEVEL1","SVC_LEVEL2","BILL_FROM","BILL_TO",
            "JOB_NO","IS_DELETED","DATE_CREATED","DATE_UPDATED","ID_LOGIN",
            "ID_AUDIT","ITEM_EXPORT_AMT","IS_DISPLAY_MIN_SVC","ITEM_CAT"},
                {"20110826000006      ","1","0","1         ",null,
                "4","5","20","76","1","1",null,null,null,null,null,TEST_DAY_TODAY,TEST_DAY_TODAY,"","0",
                TEST_DAY_TODAY,TEST_DAY_TODAY,"sysadmin",null,null,"0","1"}};
        
        super.insertData("T_BIL_H", dataT_BIL_H);
        super.insertData("T_BIL_D", dataT_BIL_D);
    }
}
