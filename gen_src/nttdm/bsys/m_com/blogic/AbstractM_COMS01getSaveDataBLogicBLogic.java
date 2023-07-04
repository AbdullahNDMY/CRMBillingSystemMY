/*
 * @(#)AbstractM_COMS01getSaveDataBLogicBLogic.java
 *
 *
 */
package nttdm.bsys.m_com.blogic;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import nttdm.bsys.m_com.dto.M_COMS01getSaveDataBLogicInput;

/**
 * Abstract BusinessLogic class.
 * 
 * @author helloworld
 */
public abstract class AbstractM_COMS01getSaveDataBLogicBLogic implements
		BLogic<M_COMS01getSaveDataBLogicInput> {
			
			/**
			 * queryDAO
			 */
			protected QueryDAO queryDAO;

			/**
			 * updateDAO
			 */
			protected UpdateDAO updateDAO;

			/**
			 * queryDAO を取得する
			 * 
			 * @return queryDAO
			 */
			public QueryDAO getQueryDAO() {
				return queryDAO;
			}

			/**
			 * queryDAO を設定する
			 * 
			 * @param queryDAO
			 *            queryDAO
			 */
			public void setQueryDAO(QueryDAO queryDAO) {
				this.queryDAO = queryDAO;
			}

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