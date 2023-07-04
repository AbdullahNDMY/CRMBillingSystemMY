/**
 * @(#)E_MWF_RA1_Process.java
 *
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.e_mwf_ar1.batch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nttdm.bsys.common.bean.T_SET_BATCH;
import nttdm.bsys.common.bean.WF_TABLEBean;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_ALT_P02;
import nttdm.bsys.common.util.G_SET_P01;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

/**
 * Batch Escalation Workflow Process
 */
public class E_MWF_RA1_Process {

	/**
	 * <div>queryDAO</div>
	 */
	private QueryDAO queryDAO;
	
	/**
	 * <div>updateDAO</div>
	 */
	private UpdateDAO updateDAO;

	/**
	 * SELECT.BSYS.SQL038
	 */
	private static final String SELECT_EXPIRE_LIST = "SELECT.BSYS.SQL038";
	
	/**
	 * SELECT.BSYS.SQL039
	 */
	private static final String SELECT_NEXT_LEVEL_SEQ = "SELECT.BSYS.SQL039";
	
	/**
	 * UPDATE.BSYS.SQL013
	 */
	private static final String UPDATE_ESCALATED = "UPDATE.BSYS.SQL013";

	/**
	 * WE
	 */
	public static final String BATCH_TYPE = "WE";

	/**
     * batchId
     */
    private Integer idBatch=0;

    /**
     * idBatch を取得する
     * @return idBatch
     */
    public Integer getIdBatch() {
        return idBatch;
    }

    /**
     * batchId を設定する
     * @param idBatch
     *            idBatch
     */
    public void setIdBatch(Integer idBatch) {
        this.idBatch = idBatch;
    }
	
    private  String idUser;
    
    /**
     * idUser を取得する
     * @return idUser
     */
    public String getIdUser() {
        return idUser;
    }

    /**
     * idUser を設定する
     * @param idUser
     *            idUser
     */
    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    /**
	 * Constructor
	 * 
	 * @param queryDAO
	 *            QueryDAO
	 * @param updateDAO
	 *            UpdateDAO
	 */
	public E_MWF_RA1_Process(QueryDAO queryDAO, UpdateDAO updateDAO) {
		this.queryDAO = queryDAO;
		this.updateDAO = updateDAO;
	}

	/**
	 * <div>execute</div>
	 */
	public void execute() {
		String status = "1";
		G_SET_P01 setP01 = new G_SET_P01(this.queryDAO, this.updateDAO);
		//this.idUser=setP01.g
		T_SET_BATCH t_batch = new T_SET_BATCH();
		t_batch.setBATCH_TYPE(E_MWF_RA1_Process.BATCH_TYPE);
		t_batch.setSTATUS(status);
		int batch_id = setP01.G_SET_P01_GetIdBatch(t_batch).getBatch_id();
		this.idBatch=batch_id;
		try {
			if (batch_id != -1) {
				// processing for QCS + BIF
				Map<String, Object> params = new HashMap<String, Object>();
				List<Map<String, Object>> wf_list = this.queryDAO
						.executeForMapList(SELECT_EXPIRE_LIST, params);
				this.process(wf_list);
				t_batch.setSTATUS("2");
			} else {
				// End process
				return;
			}
		} catch (Exception e) {
			// handle exception
			t_batch.setSTATUS("3");
			t_batch.setMessage(e.getMessage());
		}
		t_batch.setBATCH_TYPE(E_MWF_RA1_Process.BATCH_TYPE);
		t_batch.setSTATUS(status);
		setP01.G_SET_P01_GetIdBatch(t_batch);
	}

	/**
	 * <div>process</div>
	 * 
	 * @param list
	 *            List<Map<String, Object>>
	 * @throws Exception
	 */
	private void process(List<Map<String, Object>> list) throws Exception {
		// return if list don't defined
		if (list == null) {
			return;
		}

		String batchUser = CommonUtils.getBatchUser();

		// process for each item in list
		for (Map<String, Object> map : list) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id_wf_approval", map.get("ID_WF_APPROVAL"));
			Map<String, Object> next_level_seq = this.queryDAO.executeForMap(
					SELECT_NEXT_LEVEL_SEQ, params);
			if (next_level_seq != null) {
				Object next_seq = next_level_seq.get("LEVEL_SEQ");
				if (next_seq != null && !next_seq.equals("")) {
					G_ALT_P02 altP02 = new G_ALT_P02(this.queryDAO,
							this.updateDAO);
					WF_TABLEBean wf = new WF_TABLEBean();
					wf.setId_ref(map.get("ID_REF").toString());
					wf.setSection_no(map.get("SECTION_NO").toString());
					// add null check because these items are nullable in
					// database
					wf.setSection_group(map.get("SECTION_GROUP") == null ? ""
							: map.get("SECTION_GROUP").toString());
					wf.setOriginal_approver(map.get("ORIGINAL_APPROVER") == null ? ""
							: map.get("ORIGINAL_APPROVER").toString());
					wf.setId_action(map.get("ID_ACTION").toString());
					wf.setRef_id_action(map.get("REF_ID_ACTION") == null ? ""
							: map.get("REF_ID_ACTION").toString());
					wf.setId_screen(map.get("ID_SCREEN") == null ? "" : map
							.get("ID_SCREEN").toString());
					wf.setId_status(map.get("ID_STATUS").toString());
					wf.setAppr_status(map.get("APPR_STATUS") == null ? "" : map
							.get("APPR_STATUS").toString());
					wf.setDate_updated1("");
					wf.setId_login3(batchUser);
					wf.setSequence_no(next_seq.toString());
					wf.setLevel_seq(next_seq.toString());
					altP02.execute(wf);

					params = new HashMap<String, Object>();
					params.put("id_wf_approval", map.get("ID_WF_APPROVAL"));
					params.put("is_escalated", "1");
					this.updateDAO.execute(UPDATE_ESCALATED, params);
				}
			}
		}
	}
}
