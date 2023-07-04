package nttdm.bsys.a_usr.blogic;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.a_usr.dto.A_USR_S02IO;
import nttdm.bsys.a_usr.dto.User;

public class A_USR_S02_PhotoBLogic implements BLogic<A_USR_S02IO>{
	private QueryDAO queryDAO;
	private UpdateDAO updateDAO;
	
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}

	public BLogicResult execute(A_USR_S02IO input) {
		BLogicResult result = new BLogicResult();
		A_USR_S02IO output = new A_USR_S02IO();
		User user = queryDAO.executeForObject("SELECT.A_USER.GET_USER_INFO", input.getIdUser(), User.class);
		output.setUser(user);
		result.setResultObject(output);
		result.setResultString("success");
		return result;
	}

}
