/*
 * @(#)AbstractM_WLMR02BLogic.java
 *
 *
 */
package nttdm.bsys.m_wlm.blogic;

import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.m_wlm.dto.M_WLMR02Input;
import jp.terasoluna.fw.dao.UpdateDAO;

/**
 * Abstract BusinessLogic class.
 * 
 * @author ss052
 */
public abstract class AbstractM_WLMR02BLogic implements
		BLogic<M_WLMR02Input> {

	/**
	 * updateDAO
	 */
	protected UpdateDAO updateDAO;

	/**
	 * updateDAO を取得する
	 * 
	 * @return updateDAO
	 */
	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	/**
	 * updateDAO を設定する
	 * 
	 * @param updateDAO
	 *            updateDAO
	 */
	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}

}