package nttdm.bsys.b_bil.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S01_01Input;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S01_01Output;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;

public class RP_B_BIL_S01_01BLogic_Test extends AbstractUtilTest {
	private RP_B_BIL_S01_01BLogic logic = null;
	RP_B_BIL_S01_01Input param = null;

	@Override
	protected void setUpData() throws Exception {
		logic = new RP_B_BIL_S01_01BLogic();
		logic.setQueryDAO(queryDAO);
		logic.setUpdateDAO(updateDAO);
		param = new RP_B_BIL_S01_01Input();
		BillingSystemUserValueObject uvo = new BillingSystemUserValueObject();
		uvo.setId_user("sysadmin");
		param.setUvo(uvo);
	}

	public void testExecute() {
		this.deleteAllData("M_USER_ACCESS");
		this.deleteAllData("M_USER");
		String[][] dataM_USER = {
				{ "ID_USER", "USER_NAME", "ID_DIV", "ID_DEPT", "ID_ROLE",
						"TEL_NO", "TEL_EXTNO", "DID_NO", "OFS_MOBILE_NO",
						"EMAIL", "USER_STATUS", "PHOTO", "PASSWORD",
						"LAST_PWD_CHANGE", "IS_DELETED", "DATE_CREATED",
						"DATE_UPDATED", "ID_LOGIN", "PPLSOFT_ACC_ID",
						"ID_AUDIT" },
				{ "sysadmin", "sysadmin", "0001", "0001", "0000000001", "", "",
						"", "", "", "1", null, "default", TEST_DAY_TODAY, "0",
						TEST_DAY_TODAY, TEST_DAY_TODAY, "sysadmin", "", null } };
		super.insertData("M_USER", dataM_USER);
		String[][] dataM_USER_ACCESS = {
				{ "ID_USER", "ID_MODULE", "ID_SUB_MODULE", "ACCESS_TYPE",
						"DATE_CREATED", "DATE_UPDATED", "ID_LOGIN",
						"IS_DELETED", "ID_AUDIT" },
				{ "sysadmin", "B", "B-BIL", "2", TEST_DAY_TODAY,
						TEST_DAY_TODAY, "sysadmin", "0", null } };
		super.insertData("M_USER_ACCESS", dataM_USER_ACCESS);

		BLogicResult result = logic.execute(param);
		assertEquals("success", result.getResultString());
		RP_B_BIL_S01_01Output outputDTO = (RP_B_BIL_S01_01Output) result
				.getResultObject();
		assertEquals("2", outputDTO.getAccessType());
	}
}
