package nttdm.bsys.b_bil.blogic;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.b_bil.dto.RP_B_BIL_S03_02Input;
import nttdm.bsys.common.util.AbstractUtilTest;

public class RP_B_BIL_S03_02BLogic_Test extends AbstractUtilTest{
	RP_B_BIL_S03_02BLogic logic = null;
	RP_B_BIL_S03_02Input param = null;
	@Override
	protected void setUpData() throws Exception {
		logic = new RP_B_BIL_S03_02BLogic();
		param = new RP_B_BIL_S03_02Input();
	}
	public void testExecute(){
		BLogicResult result = logic.execute(param);
		assertEquals("success", result.getResultString());
	}
}
