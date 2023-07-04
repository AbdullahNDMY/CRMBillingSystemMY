package nttdm.bsys.common.util;

import nttdm.bsys.common.dao.UpdateDAOiBatisNuked;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

public abstract class AbstractGlobalProcess<I, O> {
	
	protected UpdateDAO updateDAO;
	protected QueryDAO queryDAO;
	protected UpdateDAOiBatisNuked updateDAONuked;
	
	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}
	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}
	
	public UpdateDAOiBatisNuked getUpdateDAONuked() {
		return updateDAONuked;
	}
	public void setUpdateDAONuked(UpdateDAOiBatisNuked updateDAONuked) {
		this.updateDAONuked = updateDAONuked;
	}
	public abstract GlobalProcessResult execute(I param, O outputDTO);
}
