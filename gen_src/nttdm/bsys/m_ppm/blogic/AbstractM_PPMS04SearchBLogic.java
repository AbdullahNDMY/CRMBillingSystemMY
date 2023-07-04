/**
 * @(#)AbstractM_PPMS04SearchBLogic.java
 *
 *
 */
package nttdm.bsys.m_ppm.blogic;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.m_ppm.dto.M_PPMS04Input;

/**
 * Abstract BusinessLogic class.
 * 
 * @author NTTdata
 */
public abstract class AbstractM_PPMS04SearchBLogic implements
		BLogic<M_PPMS04Input> {
	private QueryDAO queryDAO;

	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

}
