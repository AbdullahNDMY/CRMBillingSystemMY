/*
 * @(#)AbstractB_CLSR01BLogic.java
 *
 *
 */
package nttdm.bsys.b_cls.blogic;

import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.b_cls.dto.B_CLSR01Input;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

/**
 * Abstract BusinessLogic class.
 * 
 * @author ss051
 */
public abstract class AbstractB_CLSR01BLogic implements BLogic<B_CLSR01Input> {

	/**
	 * queryDAO
	 */
	protected QueryDAO queryDAO;
	
	/**
	 * updateDAO
	 */
	protected UpdateDAO updateDAO;

	/**
	 * queryDAO 郢ｧ雋槫徐陟募干笘�郢ｧ�ｿｽ
	 * 
	 * @return queryDAO
	 */
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	/**
	 * queryDAO 郢ｧ螳夲ｽｨ�ｽｭ陞ｳ螢ｹ笘�郢ｧ�ｿｽ
	 * 
	 * @param queryDAO
	 *            queryDAO
	 */
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	/**
	 * updateDAO 繧貞叙蠕励☆繧�
	 * 
	 * @return updateDAO
	 */
	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	/**
	 * updateDAO 繧定ｨｭ螳壹☆繧�
	 * 
	 * @param updateDAO
	 *            updateDAO
	 */
	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}
}