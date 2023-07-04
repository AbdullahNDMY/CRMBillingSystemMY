/**
 * @(#)G_OBT_P02.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.util.HashMap;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import nttdm.bsys.common.bean.WF_TABLEBean;

/**
 * Process to Re-Obtain Approval
 * 
 * @author lixinj
 */
public class G_OBT_P02 {

	/**
	 * UPDATE_BIF_STATUS
	 */
	private final String UPDATE_BIF_STATUS = "UPDATE.BSYS.SQL012";

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
	public G_OBT_P02(QueryDAO queryDAO, UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
		this.queryDAO = queryDAO;
	}

	/**
	 * Process to Re-Obtain Approval
	 * 
	 * @param bean
	 * @throws Exception
	 */
	public void exucute(WF_TABLEBean bean) throws Exception {
		this.updateBIFStatus(bean.getId_ref(), bean.getSection_no());
		// process for G_ALT_P03
		G_ALT_P03 altP03 = new G_ALT_P03(queryDAO, updateDAO);
		bean.setMsg(MessageUtil.get(
				"info.ERR2SC017",
				new Object[] {
						bean.getId_ref().trim(),
						CommonUtils.getUserName(queryDAO, bean.getId_user(), ""),
						bean.getResponse_expire() }));
		bean.setSubject(MessageUtil.get("info.ERR2SC016", new Object[] { bean.getId_ref().trim() }));
		altP03.execute(bean);
	}

	/**
	 * Update field APPR_STATUS of table T_WF_APPROVAL
	 * 
	 * @param idRef
	 *            ID_REF
	 * @param sectionNo
	 *            SECTION_NO
	 * @return
	 */
	private boolean updateBIFStatus(String idRef, String sectionNo) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("ID_REF", idRef);
		param.put("SECTION_NO", sectionNo);
		param.put("APPR_STATUS", "AS1");
		param.put("APPR_STATUS_CONDITION", "AS3");
		param.put("IS_REVISED", "0");
		param.put("IS_DELETED", "0");
		this.updateDAO.execute(this.UPDATE_BIF_STATUS, param);
		return true;
	}
}
