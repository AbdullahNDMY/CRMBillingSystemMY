/**
 * @(#)G_OBT_P01.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.util.HashMap;
import java.util.Map;
import nttdm.bsys.common.bean.WF_TABLEBean;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

/**
 * Process to generate first Approval
 * 
 * @author lixinj
 */
public class G_OBT_P01 {

	/**
	 * UPDATE_BIF_H_STATUS
	 */
	private static final String UPDATE_BIF_H_STATUS = "UPDATE.B_BIF.S03_02.SQL002";

	/**
	 * UpdateDAO
	 */
	private UpdateDAO updateDAO;
	
	/**
	 * QueryDAO
	 */
	private QueryDAO queryDAO;

	/**
	 * <div>Get updateDAO</div>
	 * 
	 * @return updateDAO
	 */
	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	/**
	 * <div>Set updateDAO</div>
	 * 
	 * @param updateDAO
	 */
	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}

	/**
	 * <div>Get QueryDAO</div>
	 * 
	 * @return the queryDAO
	 */
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	/**
	 * <div>Set QueryDAO</div>
	 * 
	 * @param queryDAO
	 *            the queryDAO 
	 */
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	/**
	 * <div>Constructor</div>
	 * 
	 * @param queryDAO
	 *            QueryDAO
	 * @param UpdateDAO
	 *            UpdateDAO
	 */
	public G_OBT_P01(QueryDAO queryDAO, UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
		this.queryDAO = queryDAO;
	}

	/**
	 * Process to generate first Approval
	 * 
	 * @param idRef
	 * @param sectionGroup
	 * @param sectionNo
	 * @param idScreen
	 * @param idLogin
	 * 
	 * @throws Exception
	 */
	public void exucute(String idRef, String sectionGroup, String sectionNo,
			String idScreen, String idLogin) throws Exception {
		// update id_status
		this.updateBIFStatus(idRef);
		
		// process for G_ALT_P01
		G_ALT_P01 altP01 = new G_ALT_P01(queryDAO, updateDAO);
		WF_TABLEBean wf_obj = new WF_TABLEBean();
		wf_obj.setId_ref(idRef);
		wf_obj.setId_screen(idScreen);
		wf_obj.setId_status("DS1");
		wf_obj.setAppr_status("AS1");
		wf_obj.setDate_updated1("");
		wf_obj.setSection_group(sectionGroup);
		wf_obj.setSection_no(sectionNo);
		wf_obj.setId_login3(idLogin);
		altP01.execute(wf_obj);
	}

	/**
	 * Update field id_status of table T_BIF_H
	 * 
	 * @param idRef ID_REF
	 */
	private void updateBIFStatus(String idRef) {
		// insert to BIF_H
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("ID_REF", idRef);
		param.put("ID_STATUS", "DS1");
		this.updateDAO.execute(UPDATE_BIF_H_STATUS, param);
	}
}
