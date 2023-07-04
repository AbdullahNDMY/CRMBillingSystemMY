package nttdm.bsys.b_bil.blogic;

import java.util.Calendar;

import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.struts.actions.DownloadFile;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S03_04Input;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.common.util.CommonUtils;

public class RP_B_BIL_S03_04BLogic_Test extends AbstractUtilTest {

	RP_B_BIL_S03_04BLogic testObject = null;

	@Override
	protected void setUpData() throws Exception {
		// TODO Auto-generated method stub
		testObject = new RP_B_BIL_S03_04BLogic();

		/**
		 * delete all data
		 */
		super.deleteAllData("T_BIL_D");
		super.deleteAllData("T_BIL_H");
		
		super.deleteAllData("M_GSET_D");
        super.deleteAllData("M_GSET_H");
        
        String[][] dataM_GSET_D = {
                { "ID_SET", "SET_SEQ", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
                        "SET_VALUE", "ID_AUDIT", "SET_APPLY" },
                { "SYSTEMBASE", "1", "Value can be MY or SG. Certain feature will be enable/disable based on this setting.", "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "USERFULL", "MY", null, "1" } };
        String[][] dataM_GSET_H = {
                { "ID_SET", "SET_NAME", "SET_DESC", "IS_DELETED",
                        "DATE_CREATED", "DATE_UPDATED", "ID_LOGIN" },
                { "SYSTEMBASE", "System Base",
                        "Settings to define base of system to enable certain feature.", "0", TEST_DAY_TODAY,
                        TEST_DAY_TODAY, "sysadmin" } };

		String[][] dataT_BIL_H = {
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
				{ "1", "IN", "1", "1", TEST_DAY_TODAY, "CQ", "229743", "BA",
						"PC", null, null, null, null, "bhchan", "30 Days",
						"MYR", "6", "0", "3500", "0", TEST_DAY_TODAY, "0", "0",
						"0", "1", "USD", "3.124", "1120.36", "50", "1",
						TEST_DAY_TODAY, "joeykan", "0",
						"Level 13, 55 Clarence", "Sydney NSW", "", "2000 , AU",
						"2000", "ddd", "11", "", "Mr.Timothy Yap", "", "0",
						"w.h.wong", TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin",
						null } };

		/**
		 * insert data
		 */
		super.insertData("M_GSET_H", dataM_GSET_H);
		super.insertData("M_GSET_D", dataM_GSET_D);
		super.insertData("T_BIL_H", dataT_BIL_H);
	}

	public void testExecute() throws Exception {
		RP_B_BIL_S03_04Input param = new RP_B_BIL_S03_04Input();
		BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
		uvo.setId_user("sysadmin");
		uvo.setUser_name("System Admin");
		param.setIdRef("1");
		param.setUvo(uvo);

		Calendar cal = Calendar.getInstance();
		String fileName = "BatchPrint_"
				+ CommonUtils.formatDate(cal, "yyyyMMdd") + ".pdf";

		BLogicResult result = null;

		result = testObject.execute(param);
		DownloadFile downloadFile = (DownloadFile) result.getResultObject();

		//assertEquals(fileName, downloadFile.getName());

	}

}
