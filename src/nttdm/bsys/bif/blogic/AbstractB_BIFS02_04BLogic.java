package nttdm.bsys.bif.blogic;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.bif.dto.B_BIFS02Input;

public abstract class AbstractB_BIFS02_04BLogic implements
		BLogic<B_BIFS02Input> {
	public QueryDAO queryDAO = null;
	
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	
}
