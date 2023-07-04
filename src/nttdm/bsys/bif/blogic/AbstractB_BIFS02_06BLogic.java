package nttdm.bsys.bif.blogic;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.bif.dto.B_BIFS02Input;
import nttdm.bsys.common.dao.UpdateDAOiBatisNuked;

public abstract class AbstractB_BIFS02_06BLogic implements
		BLogic<B_BIFS02Input> {
	public UpdateDAO updateDAO = null;		
			
	public QueryDAO queryDAO = null;
	
	public UpdateDAOiBatisNuked updateDAONuked;

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
	
	public UpdateDAOiBatisNuked getUpdateDAONuked() {
		return updateDAONuked;
	}

	public void setUpdateDAONuked(UpdateDAOiBatisNuked updateDAONuked) {
		this.updateDAONuked = updateDAONuked;
	}
}
