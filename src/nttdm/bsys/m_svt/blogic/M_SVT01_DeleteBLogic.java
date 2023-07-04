package nttdm.bsys.m_svt.blogic;

import java.util.HashMap;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.m_svt.dto.M_SVT01_SearchInput;
import nttdm.bsys.m_svt.dto.M_SVT01_SearchOutput;

public class M_SVT01_DeleteBLogic extends AbstractM_SVT01_DeleteBLogic {

	public BLogicResult execute(M_SVT01_SearchInput input) {
		BLogicResult result = new BLogicResult();
		M_SVT01_SearchOutput output = new M_SVT01_SearchOutput();
		Map<String, String> map = new HashMap<String, String>();
		String id_login = input.getUvo().getId_user();
		map.put("id_login", id_login);
		map.put("idService", input.getIdService());
		// delete
		updateDAO.execute("DELETE.M_SVT01.M_SVT01", map);
		output.setMessage("info.ERR2SC005");
		result.setResultObject(output);
		result.setResultString("success");
		return result;
	}

}
