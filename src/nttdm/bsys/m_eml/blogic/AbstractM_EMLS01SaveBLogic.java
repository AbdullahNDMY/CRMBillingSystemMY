package nttdm.bsys.m_eml.blogic;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.common.dao.UpdateDAOiBatisNuked;
import nttdm.bsys.m_eml.dto.M_EMLInput;

public abstract class AbstractM_EMLS01SaveBLogic implements BLogic<M_EMLInput>{
	
	/**
	 * queryDAO
	 */
	protected QueryDAO queryDAO;

	/**
	 * updateDAO
	 */
	protected UpdateDAO updateDAO;
	
	/**
	 * updateDAONuked
	 */
	protected UpdateDAOiBatisNuked updateDAONuked;

	/**
	 * @return the queryDAO
	 */
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	/**
	 * @param queryDAO the queryDAO to set
	 */
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	/**
	 * @return the updateDAO
	 */
	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	/**
	 * @param updateDAO the updateDAO to set
	 */
	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}

	/**
	 * @return the updateDAONuked
	 */
	public UpdateDAOiBatisNuked getUpdateDAONuked() {
		return updateDAONuked;
	}

	/**
	 * @param updateDAONuked the updateDAONuked to set
	 */
	public void setUpdateDAONuked(UpdateDAOiBatisNuked updateDAONuked) {
		this.updateDAONuked = updateDAONuked;
	}

}
