/**
 * @(#)G_WFM_P01.java
 *
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nttdm.bsys.bif.dto.B_BIF_Input;
import nttdm.bsys.common.bean.T_WF_APPROVAL;
import nttdm.bsys.common.bean.T_WF_SECTIONBean;
import nttdm.bsys.common.bean.WF_TABLEBean;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;

/**
 * Process for WorkFLow Movement
 */
public class G_WFM_P01 {
	/**
	 * <div>id_ref</div>
	 */
	private String id_ref;
	/**
	 * <div>id_screen</div>
	 */
	private String id_screen;
	/**
	 * <div>section_no</div>
	 */
	private String section_no;
	/**
	 * <div>sequence_no</div>
	 */
	private String sequence_no;
	/**
	 * <div>approve_status</div>
	 */
	private String approve_status;
	/**
	 * <div>id_wf_approval</div>
	 */
	private String id_wf_approval;
	/**
	 * <div>id_user</div>
	 */
	private String id_user;
	/**
	 * <div>section_group</div>
	 */
	private String section_group;
	
	private static final String SQL_GET_WF_INFO = "SELECT.BSYS.SQL024";
	private static final String SQL_GET_SEQUENCE_NO = "SELECT.BSYS.SQL025";
	private static final String SQL_GET_STATUS_QCS_H = "SELECT.BSYS.SQL026";
	private static final String SQL_GET_STATUS_BIF_H = "SELECT.BSYS.SQL026_01";
	private static final String SQL_GET_NEXT_SECTION = "SELECT.BSYS.SQL029_01";
	//get multiple section have the same sequence
	private static final String SQL_GET_ALL_NEXT_SECTION = "SELECT.BSYS.SQL029_02";
	private static final String SQL_GET_NEXT_LEVEL_SEQ = "SELECT.BSYS.SQL039_01";
	private static final String INSERT_T_WF_APPROVAL = "INSERT.BSYS.SQL007";
	private static final String UPDATE_T_WF_APPROVAL_STATUS = "UPDATE.BSYS.SQL008";
	private static final String UPDATE_T_WF_APPROVAL_STATUS_AS7 = "UPDATE.BSYS.SQL009_01";
	private static final String SELECT_NOT_COMPLETE_STATUS = "SELECT.BSYS.SQL021_01";

	private static final String DS1 = "DS1";
	private static final String DS2 = "DS2";
	private static final String DS3 = "DS3";
	private static final String DS4 = "DS4";
	private static final String AS3 = "AS3";
	private static final String AS5 = "AS5";
	
	private static final String ERR2SC018 = "info.ERR2SC018";
	private static final String ERR2SC019 = "info.ERR2SC019";
	private static final String ERR2SC020 = "info.ERR2SC020";
	private static final String ERR2SC021 = "info.ERR2SC021";
	private static final String ERR2SC043 = "info.ERR2SC043";
	private static final String ERR2SC008 = "info.ERR2SC008";
	private static final String A = "A";
	
	/**
	 * <div>anyUser</div>
	 */
	private boolean anyUser = false;
	/**
	 * <div>section_no_i</div>
	 */
	private String section_no_i = "";
	private int m = 0;
	
	/**
	 * <div>isAnyUser</div>
	 */
	public boolean isAnyUser() {
		return anyUser;
	}
	/**
	 * updateDAO
	 */
	protected UpdateDAO updateDAO;
	/**
	 * <div>queryDAO</div>
	 */
	private QueryDAO queryDAO; 
	
	/**
	 * <div>setAnyUser</div>
	 * @param anyUser boolean
	 * @throws Exception
	 */
	public void setAnyUser(boolean anyUser) throws Exception {
		this.anyUser = anyUser;
		if(anyUser==false){
			//
			T_WF_APPROVAL t_wf = new T_WF_APPROVAL();
			t_wf.setId_ref(this.id_ref);
			t_wf.setAppr_status(AS5);
			t_wf.setSection_no(this.section_no_i);
			t_wf.setSection_group(this.section_group);
			t_wf.setId_login(this.id_user);
			this.updateDAO.execute(INSERT_T_WF_APPROVAL, t_wf);
			gdstloop(t_wf);
		}
	}

	/**
	 * G_WFM_P01
	 * @param updateDAO UpdateDAO
	 * @param queryDAO QueryDAO
	 * @param id_ref String
	 * @param id_screen String
	 * @param section_no String
	 * @param sequence_no String
	 * @param approve_status String
	 * @param id_wf_approval String
	 * @param id_user String
	 */
	public G_WFM_P01(UpdateDAO updateDAO,QueryDAO queryDAO, String id_ref,
			String id_screen,String section_no,String sequence_no,
			String approve_status,String id_wf_approval, String id_user){
		this.updateDAO = updateDAO;
		this.queryDAO = queryDAO;
		this.id_ref = id_ref;
		this.id_screen = id_screen;
		this.section_no = section_no;
		this.sequence_no = sequence_no;
		this.approve_status = approve_status;
		this.id_wf_approval = id_wf_approval;
		this.id_user = id_user;
	}
	
	/**
	 * <div>gdstloop</div>
	 * @param t_wf T_WF_APPROVAL
	 * @throws Exception
	 */
	private void gdstloop(T_WF_APPROVAL t_wf) throws Exception{
		G_DST_P01 dts = new G_DST_P01(this.queryDAO, this.updateDAO);
		WF_TABLEBean bean = new WF_TABLEBean();
		bean.setId_screen(this.id_screen);
		bean.setId_ref(this.id_ref);
		bean.setSection_no(this.section_no);
		bean.setSection_group(this.section_group);
		bean.setId_user(this.id_user);
		dts.execute(bean);
		
		String status = "";
		if (this.id_screen.indexOf(B_BIF_Input.ID_SCREEN) > -1) {
			//get status from bif_h
			status = this.queryDAO.executeForObject(SQL_GET_STATUS_BIF_H,this.id_ref, String.class);
		} else {
			//get status from qcs_h
			status = this.queryDAO.executeForObject(SQL_GET_STATUS_QCS_H,this.id_ref, String.class);
		}
		if(status.equals(DS1) || status.equals(DS2)){
			if(this.approve_status != null && this.approve_status.equals(AS3)) {
				G_ALT_P03 alt3 = new G_ALT_P03(this.queryDAO, this.updateDAO);
				WF_TABLEBean wf = new WF_TABLEBean();
				wf.setId_login3(this.id_user);
				wf.setId_screen(this.id_screen);
				wf.setId_ref(this.id_ref);
				wf.setAction_user(this.id_user);
				wf.setType(A);
				String req_user_name = CommonUtils.getUserName(queryDAO, this.id_user, "");
				String section_desc = CommonUtils.getSectionDesc(queryDAO, this.section_no, "");
				String apprComment = CommonUtils.getApprComment(queryDAO, this.id_ref, this.section_no, "-");
				wf.setSubject(MessageUtil.get(ERR2SC008, new Object[] {this.id_ref.trim(), req_user_name}));
				wf.setMsg(MessageUtil.get(ERR2SC043, new Object[] {this.id_ref.trim(), req_user_name, section_desc, apprComment})); 
				alt3.execute(wf);
			}
			else {
				//get next process
				T_WF_SECTIONBean current_section = new T_WF_SECTIONBean();
				current_section.setId_ref(this.id_ref); 
				current_section.setId_screen(this.id_screen);
				current_section.setSection_no(this.section_no);
				
				//check all status is approval
				List<Map<String,Object>> wfStatusList = this.queryDAO.executeForMapList(SELECT_NOT_COMPLETE_STATUS,t_wf.getId_ref());
				//if size() > 0 -> have some status's not be approval
				if (wfStatusList.size() > 0) {
					return;
				}
				
				T_WF_SECTIONBean next_Section = new T_WF_SECTIONBean();
				next_Section = this.queryDAO.executeForObject(SQL_GET_NEXT_SECTION, current_section, T_WF_SECTIONBean.class);
				
				if (next_Section != null) {
					//get all section have same next section sequence
					current_section.setSequence_no(next_Section.getSequence_no());
					
					List<T_WF_SECTIONBean> section_List;
					section_List = this.queryDAO.executeForObjectList(SQL_GET_ALL_NEXT_SECTION, current_section);
					if (section_List != null) {
						for (T_WF_SECTIONBean seq_bean : section_List) {
							this.section_no_i = seq_bean.getSection_no();
							this.section_group = seq_bean.getSection_group();
							//WF_TABLEBean wf
							G_ALT_P02 galt= new G_ALT_P02(this.queryDAO, this.updateDAO);
							galt.setG_wfm(this);
							WF_TABLEBean wf = new WF_TABLEBean();
							wf.setId_ref(this.id_ref);
							wf.setSection_no(section_no_i);
							wf.setId_screen(this.id_screen);
							wf.setId_login3(this.id_user);
							wf.setSequence_no(this.sequence_no);
							wf.setSection_group(this.section_group);
							wf.setAppr_status(this.approve_status);
			 
							//get the first level_seq of next section
							Map<String, Object> params = new HashMap<String, Object>();
							params.put("id_ref", this.id_ref);
							params.put("section_no", section_no_i);
							//set to get first level
							params.put("level_seq", -9999);
							String first_level = this.queryDAO.executeForObject(SQL_GET_NEXT_LEVEL_SEQ, params, String.class);
							if (first_level != null) {
								wf.setLevel_seq(first_level.toString());
								//call G_ALT_P02
								galt.execute(wf);
							}
						}
					}
				} else {
					//do nothing
				}
			}
		}
		else if(status.equals(DS3)){
			//ERR2SC018
			G_ALT_P03 alt3 = new G_ALT_P03(this.queryDAO, this.updateDAO);
			WF_TABLEBean wf = new WF_TABLEBean();
			wf.setId_login3(this.id_user);
			wf.setId_screen(this.id_screen);
			wf.setId_ref(this.id_ref);
			wf.setAction_user(this.id_user);
			wf.setType(A);
			wf.setSubject(MessageUtil.get(ERR2SC018, new Object[] {this.id_ref.trim()}));
			wf.setMsg(MessageUtil.get(ERR2SC019, new Object[] {this.id_ref.trim()})); 
			alt3.execute(wf);
		}
		else if(status.equals(DS4)){
			//ERR2SC020
			G_ALT_P03 alt3 = new G_ALT_P03(this.queryDAO, this.updateDAO);
			WF_TABLEBean wf = new WF_TABLEBean();
			wf.setId_login3(this.id_user);
			wf.setId_screen(this.id_screen);
			wf.setId_ref(this.id_ref);
			wf.setAction_user(this.id_user);
			wf.setType(A);
			String req_user_name = CommonUtils.getUserName(queryDAO, this.id_user, "");
			String section_desc = CommonUtils.getSectionDesc(queryDAO, this.section_no, "");
			String apprComment = CommonUtils.getApprComment(queryDAO, this.id_ref, this.section_no, "-");
			wf.setSubject(MessageUtil.get(ERR2SC020, new Object[] {this.id_ref.trim()}));
			wf.setMsg(MessageUtil.get(ERR2SC021, new Object[] {this.id_ref.trim(), req_user_name, section_desc, apprComment}));
			alt3.execute(wf);
		}
	}
	
	/**
	 * <div>execute</div>
	 * 
	 * @throws Exception
	 */
	public void execute() throws Exception{
		try{
			T_WF_APPROVAL t_wf = new T_WF_APPROVAL();
			t_wf = this.queryDAO.executeForObject(SQL_GET_WF_INFO, this.id_wf_approval, T_WF_APPROVAL.class);
			if(t_wf!=null){
				if(!t_wf.getId_approver().trim().equals(this.id_user)){
					//insert new row with same data into F table with id_approver = id_user
					t_wf.setId_approver(this.id_user);
					this.updateDAO.execute(INSERT_T_WF_APPROVAL, t_wf);
				} 
				//yes: update appr_status
				t_wf.setAppr_status(this.approve_status);
				t_wf.setId_approver(this.id_user);
				t_wf.setSection_no(this.section_no);
				t_wf.setId_ref(this.id_ref);
				
				//update data
				this.updateDAO.execute(UPDATE_T_WF_APPROVAL_STATUS, t_wf);
				//escalated user or forwarded user will not be able to approve anymore
				this.updateDAO.execute(UPDATE_T_WF_APPROVAL_STATUS_AS7, t_wf);
				//get sequence_no by max sequence
				T_WF_SECTIONBean section = new T_WF_SECTIONBean();
				section.setId_ref(this.id_ref);
				section.setSection_no(this.section_no);
				section.setId_screen(this.id_screen);
				String m_count = this.queryDAO.executeForObject(SQL_GET_SEQUENCE_NO, section, String.class);
				if(m_count == null){
					return;
				} else {
					this.m=Integer.parseInt(m_count);
				}
				//prepare to call G-DST process
				this.gdstloop(t_wf);
			}			
		}
		catch(Exception ex){
			throw ex;
		}
	}
}
