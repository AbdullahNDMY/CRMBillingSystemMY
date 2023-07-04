/**
 * @(#)G_DOC_P02.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.util.ArrayList;
import java.util.List;

import nttdm.bsys.m_alt.bean.FileInfo;
import nttdm.bsys.m_alt.dto.M_ALTR01Input;
import jp.terasoluna.fw.dao.QueryDAO;

/**
 * Display attachment on page and read attachment
 * 
 * @author lixinj
 */
public class G_DOC_P02 {
	/**
	 * <div>SQL_GET_FILE_INFO</div>
	 */
	static final String SQL_GET_FILE_INFO = "SELECT.M_ALT.SQL009";
	/**
	 * <div>queryDAO</div>
	 */
	private QueryDAO queryDAO = null;

	/**
	 * <div>Get queryDAO</div>
	 * 
	 * @return QueryDAO
	 */
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	/**
	 * <div>Set queryDAO</div>
	 * 
	 * @param queryDAO
	 */
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	/**
	 * Get file name base on id_ref and id_doc
	 * 
	 * @param param
	 *            M_ALTR01Input
	 * @param queryDAO
	 *            QueryDAO
	 * */
	public List<FileInfo> getFileName(M_ALTR01Input param, QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
		List<FileInfo> fileInfos = new ArrayList<FileInfo>();
		fileInfos = queryDAO.executeForObjectList(SQL_GET_FILE_INFO,
				CommonUtils.toLong(param.getId_msg()));
		return fileInfos;
	}
}
