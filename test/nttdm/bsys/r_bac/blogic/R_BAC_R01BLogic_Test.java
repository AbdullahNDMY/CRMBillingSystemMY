package nttdm.bsys.r_bac.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.AbstractUtilTest;
import nttdm.bsys.r_bac.dto.R_BAC_R01Input;
import nttdm.bsys.r_bac.dto.R_BAC_R01Output;

public class R_BAC_R01BLogic_Test extends AbstractUtilTest{

	private R_BAC_R01BLogic logic =null;
	private R_BAC_R01Input input = null;
	BillingSystemUserValueObject uvo  = null;

	@Override
	protected void setUpData() throws Exception {
		logic = new R_BAC_R01BLogic();
		logic.setQueryDAO(queryDAO);
		input = new R_BAC_R01Input();
		
		uvo = new BillingSystemUserValueObject();
	}
	
	/**
	 * 
	 */
	public void testExecute01() {
		uvo.setId_user("sysadmin");
		input.setUvo(uvo);
		BLogicResult result = logic.execute(input);
		R_BAC_R01Output output  = (R_BAC_R01Output)result.getResultObject();
		assertEquals("1", output.getTotalamount());
		assertEquals("2", output.getAccessType());
		assertEquals("success", result.getResultString());
	}
	
	public void testExecute02() {
		uvo.setId_user("sysadminTest");
		input.setUvo(uvo);
		BLogicResult result = logic.execute(input);
		R_BAC_R01Output output  = (R_BAC_R01Output)result.getResultObject();
		assertEquals("1", output.getTotalamount());
		assertEquals(null, output.getAccessType());
		assertEquals("success", result.getResultString());
	}
}
