package nttdm.bsys.q_qcs.blogic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.struts.upload.FormFile;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import nttdm.bsys.common.bean.T_WF_APPROVAL;
import nttdm.bsys.common.bean.T_WF_DOCBean;
import nttdm.bsys.common.bean.WF_TABLEBean;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.G_ALT_P01;
import nttdm.bsys.common.util.G_ALT_P03;
import nttdm.bsys.common.util.G_CDM_P01;
//import nttdm.bsys.common.util.G_TBL_P01;
import nttdm.bsys.m_alt.bean.FileInfo;
import nttdm.bsys.q_qcs.bean.FileUploadInfo;
import nttdm.bsys.q_qcs.bean.QCSDetail;
import nttdm.bsys.q_qcs.bean.QCSHeader;
import nttdm.bsys.q_qcs.bean.QCSSection;
import nttdm.bsys.q_qcs.bean.WF_DOC;
import nttdm.bsys.q_qcs.dto.Q_QCSR03Input;

public class Q_QCSR01 {
	private Q_QCSR03Input input;
	private QueryDAO queryDAO;
	private UpdateDAO updateDAO;
	private String filePath;
	private int maxFileSize ;
	public Q_QCSR03Input getInput() {
		return input;
	}

	public void setInput(Q_QCSR03Input input) {
		this.input = input;
	}

	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}

	public Q_QCSR01(Q_QCSR03Input input, QueryDAO queryDAO, UpdateDAO updateDAO){
		this.input = input;
		this.queryDAO = queryDAO;
		this.updateDAO = updateDAO;
		//Get file path
		BillingSystemSettings systemSetting = new BillingSystemSettings(queryDAO);
		this.filePath = systemSetting.getFileLocation();
		
		//Get max file size (in MBytes)
		this.maxFileSize = systemSetting.getFileSizeUpload();
	}
	/**
	 * Execute when user click [Save] button to create new QCS
	 */
	public void executeNewQCS(){
		String id_ref = "";
		String id_login = input.getId_login();
		//Generate QCS ref no
		G_CDM_P01 cmd_p01 = new G_CDM_P01(queryDAO,updateDAO,id_login);
		try {
			id_ref = cmd_p01.getGenerateCode(Q_QCSR03BLogic.QCSNO_ID_CODE, id_login);
			input.setId_ref(id_ref);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//mode
		if(input.getCust_mode() == null){
			input.setCust_mode("0");
		}else{
			input.setCust_mode("1");
		}
		//id_status
		input.setId_status(BillingSystemConstants.DOCUMENT_STATUS_DRAFTED_VALUE);
		//Insert data to t_qcs_h table
		updateDAO.execute(Q_QCSR03BLogic.SQL_INSERT_T_QCS_H, input);
		
		//Prepare info to insert to t_qcs_d
		QCSDetail detail = new QCSDetail();
		detail.setId_ref(id_ref); 
		detail.setId_login(id_login);
		//Get max of id_svc
		Integer id_svcI = queryDAO.executeForObject(Q_QCSR03BLogic.SQL_SELECT_MAX_ID_SVC, null, Integer.class);
		int id_svc = 0;
		if (id_svcI != null) {
		    id_svc = id_svcI.intValue();
		}
		if(input.getCapacity() != null){
			for(int i=0;i<input.getCapacity().length;i++){
				String svc_grp = input.getSvc_grp()[i];
				String capacity = input.getCapacity()[i];
				String nrc = input.getNrc()[i];
				String mrc = input.getMrc()[i];
				String term = input.getTerm()[i];
				String tariff = input.getTariff()[i];
				String disc = input.getDisc()[i];
				String total = input.getTotal()[i];
				if(!capacity.equals("") || !nrc.equals("") || !mrc.equals("") || !term.equals("")
						&& !tariff.equals("") || !disc.equals("")){
					detail.setSvc_grp(svc_grp);
					detail.setCapacity(capacity);
					detail.setNrc(nrc);
					detail.setMrc(mrc);
					detail.setTerm(term);
					detail.setTariff(tariff);
					detail.setDisc(disc);
					detail.setTotal(total);
					id_svc = id_svc +1;
					detail.setId_svc(String.valueOf(id_svc));					
					updateDAO.execute(Q_QCSR03BLogic.SQL_INSERT_T_QCS_D, detail);
				}
			}
		}
		//Insert data to t_section table
		//Section group : QCS
		QCSSection section = new QCSSection();
		section.setId_ref(id_ref);
		section.setId_login(id_login);
		section.setSection_group(BillingSystemConstants.SECTION_GROUP_QCS_VALUE);
		section.setSection_no(BillingSystemConstants.SECTION_NO_QCS_VALUE);
		section.setRemarks(input.getRemarks());
		updateDAO.execute(Q_QCSR03BLogic.SQL_INSERT_T_SECTION, section);
		//Section group : PRM		
		section.setSection_group(BillingSystemConstants.SECTION_GROUP_PMR_VALUE);
		section.setSection_no(input.getSection_no_pmr());
		section.setRemarks(input.getRemarks_pmr());
		updateDAO.execute(Q_QCSR03BLogic.SQL_INSERT_T_SECTION, section);
		//Section group : BZR
		section.setSection_group(BillingSystemConstants.SECTION_GROUP_BZR_VALUE);
		section.setSection_no(input.getSection_no_bzr());
		section.setRemarks(input.getRemarks_bzr());
		updateDAO.execute(Q_QCSR03BLogic.SQL_INSERT_T_SECTION, section);
		//Section group : CTC
		section.setSection_group(BillingSystemConstants.SECTION_GROUP_CTC_VALUE);
		section.setSection_no(input.getSection_no_ctc());
		section.setRemarks(input.getRemarks_ctc());
		updateDAO.execute(Q_QCSR03BLogic.SQL_INSERT_T_SECTION, section);
		//Section group : PRI
		section.setSection_group(BillingSystemConstants.SECTION_GROUP_PRI_VALUE);
		section.setSection_no(input.getSection_no_pri());
		section.setRemarks(input.getRemarks_pri());
		updateDAO.execute(Q_QCSR03BLogic.SQL_INSERT_T_SECTION, section);
		//Section group : MRG
		section.setSection_group(BillingSystemConstants.SECTION_GROUP_MRG_VALUE);
		section.setSection_no(input.getSection_no_mrg());
		section.setRemarks(input.getRemarks_mrg());
		updateDAO.execute(Q_QCSR03BLogic.SQL_INSERT_T_SECTION, section);
		//Section group : CRC
		section.setSection_group(BillingSystemConstants.SECTION_GROUP_CRC_VALUE);
		section.setSection_no(input.getSection_no_crc());
		section.setRemarks(input.getRemarks_crc());
		updateDAO.execute(Q_QCSR03BLogic.SQL_INSERT_T_SECTION, section);
		//Section group : COV
		section.setSection_group(BillingSystemConstants.SECTION_GROUP_COV_VALUE);
		section.setSection_no(input.getSection_no_cov());
		section.setRemarks(input.getRemarks_cov());
		updateDAO.execute(Q_QCSR03BLogic.SQL_INSERT_T_SECTION, section);
		//Section group : FRX
		section.setSection_group(BillingSystemConstants.SECTION_GROUP_FRX_VALUE);
		section.setSection_no(input.getSection_no_frx());
		if(input.getSection_no_frx().equals(BillingSystemConstants.SECTION_NO_FRX_STANDARD_VALUE)){
			section.setValue(BillingSystemConstants.FRX_MYR_VALUE);
		}else{
			section.setValue(input.getValue_frx());
		}
		section.setRemarks(input.getRemarks_frx());
		updateDAO.execute(Q_QCSR03BLogic.SQL_INSERT_T_SECTION, section);
		//---Insert attachment
		this.insertAttachment(id_login, id_ref);
	}
	/**
	 * Execute when user want to update QCS
	 */
	public void executeUpdateQCS(){
		String id_ref = this.input.getId_ref();
		String id_login = input.getId_login();
		boolean qcsApproved = this.isQcsApproved(id_ref);
		if(qcsApproved == false){
			//mode
			if(input.getCust_mode() == null){
				input.setCust_mode("0");
			}else{
				input.setCust_mode("1");
			}
			
			//Update data to t_qcs_h table
			updateDAO.execute(Q_QCSR03BLogic.SQL_UPDATE_T_QCS_H, input);
			//Delete t_qcs_d
			updateDAO.execute(Q_QCSR03BLogic.SQL_DELETE_T_QCS_D, id_ref);
			//Prepare info to insert to t_qcs_d
			QCSDetail detail = new QCSDetail();
			detail.setId_ref(id_ref); 
			detail.setId_login(id_login);
			//Get max of id_svc
			Integer id_svcI = queryDAO.executeForObject(Q_QCSR03BLogic.SQL_SELECT_MAX_ID_SVC, null, Integer.class);
			int id_svc = 0;
	        if (id_svcI != null) {
	            id_svc = id_svcI.intValue();
	        }
			if(input.getCapacity() != null){
				for(int i=0;i<input.getCapacity().length;i++){
					String svc_grp = input.getSvc_grp()[i];
					String capacity = input.getCapacity()[i];
					String nrc = input.getNrc()[i];
					String mrc = input.getMrc()[i];
					String term = input.getTerm()[i];
					String tariff = input.getTariff()[i];
					String disc = input.getDisc()[i];
					String total = input.getTotal()[i];
					if(!capacity.equals("") || !nrc.equals("") || !mrc.equals("") || !term.equals("")
							&& !tariff.equals("") || !disc.equals("")){
						detail.setSvc_grp(svc_grp);
						detail.setCapacity(capacity);
						detail.setNrc(nrc);
						detail.setMrc(mrc);
						detail.setTerm(term);
						detail.setTariff(tariff);
						detail.setDisc(disc);
						detail.setTotal(total);
						id_svc = id_svc +1;
						detail.setId_svc(String.valueOf(id_svc));					
						updateDAO.execute(Q_QCSR03BLogic.SQL_INSERT_T_QCS_D, detail);
					}
				}
			}
		}
		//Update data to t_section table
		//Section group : PRM
		QCSSection section = new QCSSection();
		section.setId_ref(id_ref);
		section.setId_login(id_login);
		section.setSection_group(BillingSystemConstants.SECTION_GROUP_PMR_VALUE);
		section.setSection_no(input.getSection_no_pmr());
		section.setRemarks(input.getRemarks_pmr());
		updateDAO.execute(Q_QCSR03BLogic.SQL_UPDATE_T_SECTION, section);
		//Section group : BZR
		section.setSection_group(BillingSystemConstants.SECTION_GROUP_BZR_VALUE);
		section.setSection_no(input.getSection_no_bzr());
		section.setRemarks(input.getRemarks_bzr());
		updateDAO.execute(Q_QCSR03BLogic.SQL_UPDATE_T_SECTION, section);
		//Section group : CTC
		section.setSection_group(BillingSystemConstants.SECTION_GROUP_CTC_VALUE);
		section.setSection_no(input.getSection_no_ctc());
		section.setRemarks(input.getRemarks_ctc());
		updateDAO.execute(Q_QCSR03BLogic.SQL_UPDATE_T_SECTION, section);
		//Section group : PRI
		section.setSection_group(BillingSystemConstants.SECTION_GROUP_PRI_VALUE);
		section.setSection_no(input.getSection_no_pri());
		section.setRemarks(input.getRemarks_pri());
		updateDAO.execute(Q_QCSR03BLogic.SQL_UPDATE_T_SECTION, section);
		//Section group : MRG
		section.setSection_group(BillingSystemConstants.SECTION_GROUP_MRG_VALUE);
		section.setSection_no(input.getSection_no_mrg());
		section.setRemarks(input.getRemarks_mrg());
		updateDAO.execute(Q_QCSR03BLogic.SQL_UPDATE_T_SECTION, section);
		//Section group : CRC
		section.setSection_group(BillingSystemConstants.SECTION_GROUP_CRC_VALUE);
		section.setSection_no(input.getSection_no_crc());
		section.setRemarks(input.getRemarks_crc());
		updateDAO.execute(Q_QCSR03BLogic.SQL_UPDATE_T_SECTION, section);
		//Section group : COV
		section.setSection_group(BillingSystemConstants.SECTION_GROUP_COV_VALUE);
		section.setSection_no(input.getSection_no_cov());
		section.setRemarks(input.getRemarks_cov());
		updateDAO.execute(Q_QCSR03BLogic.SQL_UPDATE_T_SECTION, section);
		//Section group : FRX
		section.setSection_group(BillingSystemConstants.SECTION_GROUP_FRX_VALUE);
		section.setSection_no(input.getSection_no_frx());
		if(input.getSection_no_frx().equals(BillingSystemConstants.SECTION_NO_FRX_STANDARD_VALUE)){
			section.setValue(BillingSystemConstants.FRX_MYR_VALUE);
		}else{
			section.setValue(input.getValue_frx());
		}
		section.setRemarks(input.getRemarks_frx());
		updateDAO.execute(Q_QCSR03BLogic.SQL_UPDATE_T_SECTION, section);
		//Update attachment
		this.deleteAttachment();
		this.insertAttachment(id_login, id_ref);
	}
	/**
	 * Update section group in case reject
	 */
	public void executeUpdateSectionGroupToReject(){
		String deletedAttachment = "";
		FormFile[] listFile = null;
		//Update section group
		QCSSection section = new QCSSection();
		String section_group_active = this.input.getSection_group();
		section.setSection_group(section_group_active);
		section.setId_ref(this.input.getId_ref());
		section.setId_login(this.input.getUvo().getId_user());
		if(section_group_active.equals(BillingSystemConstants.SECTION_GROUP_PMR_VALUE)){
			section.setRemarks(this.input.getRemarks_pmr());
			deletedAttachment = this.input.getDeletedAttachmentPMR();
			listFile = this.input.getListFilePMR();
		}else if(section_group_active.equals(BillingSystemConstants.SECTION_GROUP_BZR_VALUE)){
			section.setRemarks(this.input.getRemarks_bzr());
			deletedAttachment = this.input.getDeletedAttachmentBZR();
			listFile = this.input.getListFileBZR();
		}else if(section_group_active.equals(BillingSystemConstants.SECTION_GROUP_CTC_VALUE)){
			section.setRemarks(this.input.getRemarks_ctc());
			deletedAttachment = this.input.getDeletedAttachmentCTC();
			listFile = this.input.getListFileCTC();
		}else if(section_group_active.equals(BillingSystemConstants.SECTION_GROUP_PRI_VALUE)){
			section.setRemarks(this.input.getRemarks_pri());
			deletedAttachment = this.input.getDeletedAttachmentPRI();
			listFile = this.input.getListFilePRI();
		}else if(section_group_active.equals(BillingSystemConstants.SECTION_GROUP_MRG_VALUE)){
			section.setRemarks(this.input.getRemarks_mrg());
			deletedAttachment = this.input.getDeletedAttachmentMRG();
			listFile = this.input.getListFileMRG();
		}else if(section_group_active.equals(BillingSystemConstants.SECTION_GROUP_CRC_VALUE)){
			section.setRemarks(this.input.getRemarks_crc());
			deletedAttachment = this.input.getDeletedAttachmentCRC();
			listFile = this.input.getListFileCRC();
		}else if(section_group_active.equals(BillingSystemConstants.SECTION_GROUP_FRX_VALUE)){
			section.setRemarks(this.input.getRemarks_frx());
			deletedAttachment = this.input.getDeletedAttachmentFRX();
			listFile = this.input.getListFileFRX();
		}else if(section_group_active.equals(BillingSystemConstants.SECTION_GROUP_COV_VALUE)){
			section.setRemarks(this.input.getRemarks_cov());
			deletedAttachment = this.input.getDeletedAttachmentCOV();
			listFile = this.input.getListFileCOV();
		}
		updateDAO.execute(Q_QCSR03BLogic.SQL_UPDATE_T_SECTION_REJECT, section);
		//Update attachment
		this.updateAttachmentSectionGroup(deletedAttachment, listFile);
	}
	/**
	 * Execute when user want to delete QCS
	 */
	public void executeDeleteQCS(){
		QCSHeader header = new QCSHeader();
		header.setId_ref(input.getId_ref());
		header.setId_login(input.getId_login());
		header.setId_status(BillingSystemConstants.DOCUMENT_STATUS_CANCELLED_VALUE);
		//Delete t_wf_doc table
		updateDAO.execute(Q_QCSR03BLogic.SQL_DELETE_T_WF_DOC, header);
		//Delete t_qcs_header
		updateDAO.execute(Q_QCSR03BLogic.SQL_DELETE_T_QCS_H, header);
	}
	/**
	 * Obtain Approval of QCS
	 * @param obt_appr_save
	 * @throws Exception
	 */
	public void executeObtainApprovalQCS() throws Exception{
		//1. Run G_OPT_P01 process
		//1.1. Run G_TBL_P01 process
		String obt_appr_save = this.input.getObt_appr_save();
		//ArrayList<String> tbl = G_TBL_P01.execute(BillingSystemConstants.ID_SCREEN_QCS);
									//
		if(obt_appr_save.equals("y")){
			//1.2. Update db (SQL1.1)
			executeUpdateQCS();
		}
		//1.3. Update id_status to DS1
		String id_ref = this.input.getId_ref();
		QCSHeader header =new QCSHeader();
		header.setId_ref(id_ref);
		header.setId_status(BillingSystemConstants.DOCUMENT_STATUS_OPEN_VALUE);
		updateDAO.execute(Q_QCSR03BLogic.SQL_UPDATE_ID_STATUS, header);
		//1.4. Call G_ALT_P01
		G_ALT_P01 g_alt_p01 = new G_ALT_P01(queryDAO,updateDAO);
		WF_TABLEBean wf_obj = new WF_TABLEBean();
		wf_obj.setId_ref(id_ref);
		wf_obj.setId_screen(BillingSystemConstants.ID_SCREEN_QCS);
		wf_obj.setAppr_status(BillingSystemConstants.APPROVAL_STATUS_PENDING_VALUE);
		wf_obj.setSection_no(BillingSystemConstants.SECTION_NO_QCS_VALUE);
		wf_obj.setSection_group("QCS");
		wf_obj.setId_status(BillingSystemConstants.DOCUMENT_STATUS_OPEN_VALUE);
		wf_obj.setId_login3(this.input.getUvo().getId_user());
		/*
		 * bean.setId_action(wf.getId_action()); 
				bean.setId_ref(wf.getId_ref());1
				bean.setSection_group(wf.getSection_group());
				bean.setSection_no(wf.getSection_no());1
				bean.setSequence_no(wf.getSequence_no());

		 * */
		g_alt_p01.execute(wf_obj);
	}
	/**
	 * Obtain Approval of other section groups
	 * @param obt_appr_save
	 * @throws Exception
	 */
	public void executeObtainApprovalSectionGroup() throws Exception{
		String obt_appr_save = this.input.getObt_appr_save();
		String id_ref = this.input.getId_ref();
		String id_login = this.input.getId_login();
		String req_user = this.input.getReq_user();
		boolean qcsApproved = this.isQcsApproved(id_ref);
		//if QCS approved, call G_OBT_P02
		if(qcsApproved){
			if(obt_appr_save.equals("y")){
				// Update section group info
				this.executeUpdateWithoutQCS();
			}
			//-----Run G_OBT_P02
			//Update T_WF_APROVAL
			T_WF_APPROVAL appr_status = new T_WF_APPROVAL();
			appr_status.setId_ref(id_ref);
			appr_status.setDate_appr(null);
			appr_status.setNew_appr_status(BillingSystemConstants.APPROVAL_STATUS_PENDING_VALUE);
			String section_no = this.getSectionNo(this.input.getSection_group());
			appr_status.setSection_no(section_no);
			appr_status.setAppr_status(BillingSystemConstants.APPROVAL_STATUS_REJECTED_VALUE);
			//Get PIC before update
			T_WF_APPROVAL wf_app = new T_WF_APPROVAL();
			wf_app.setSection_group(this.input.getSection_group());
			wf_app.setSection_no(section_no);
			wf_app.setAppr_status(BillingSystemConstants.APPROVAL_STATUS_REJECTED_VALUE);
			wf_app.setId_ref(id_ref);
			String pic = queryDAO.executeForObject(Q_QCSR03BLogic.SQL_GET_PIC, wf_app, String.class);
			
			updateDAO.execute(Q_QCSR03BLogic.SQL_UPDATE_APPR_STATUS, appr_status);
			//Run G_ALT_P03
			String msg_date = "";
			WF_TABLEBean wf = new WF_TABLEBean();
			wf.setId_ref(id_ref);
			wf.setId_login3(id_login);
			wf.setId_screen(BillingSystemConstants.ID_SCREEN_QCS);
			wf.setSubject(MessageFormat.format(Q_QCSR03BLogic.ERR2SC016, id_ref));
			wf.setPic(pic);
			
			//Get response_expire
			int response_exp = 0;
			WF_TABLEBean wf_table = new WF_TABLEBean();
			wf_table.setId_screen(BillingSystemConstants.ID_SCREEN_QCS);
			wf_table.setSection_group(this.input.getSection_group());
			wf_table.setAction_type(BillingSystemConstants.ACTION_TYPE_AA);
			wf_table.setSection_no(section_no);
			wf_table.setPic(pic);
			wf_table.setId_tfr_user(pic);
			List<String> resp = queryDAO.executeForObjectList(Q_QCSR03BLogic.SQL_GET_RESPONSE_EXPIRE, wf_table);
			if(resp != null && resp.size() > 0){
				response_exp = Integer.parseInt(resp.get(0));
			}else{
				//Check authority_transfer
				List<String> aut = queryDAO.executeForObjectList(Q_QCSR03BLogic.SQL_GET_AUTHORITY_USER, wf_table);
				if(aut != null && aut.size() > 0){
					wf_table.setPic(aut.get(0));
					resp = queryDAO.executeForObjectList(Q_QCSR03BLogic.SQL_GET_RESPONSE_EXPIRE, wf_table);
					if(resp != null && resp.size() > 0){
						response_exp = Integer.parseInt(resp.get(0));
					}
				}
			}
			SimpleDateFormat sdf = new SimpleDateFormat(BillingSystemConstants.DATE_FORMAT);
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, response_exp);
			Date date = cal.getTime();
			msg_date = sdf.format(date);
			//Set msg
			wf.setMsg(MessageFormat.format(Q_QCSR03BLogic.ERR2SC017, id_ref,req_user,msg_date));
			G_ALT_P03 g_alt_p03 = new G_ALT_P03(queryDAO,updateDAO);
			g_alt_p03.execute(wf);
			
		}else{
			//Call G_OBT_P01 process
			//Run G_TBL_P01 process
			//ArrayList<String> tbl = G_TBL_P01.execute(BillingSystemConstants.ID_SCREEN_QCS);
			
			if(obt_appr_save.equals("y")){
				//Update db (SQL1.1)
				executeUpdateQCS();
			}
			//Update id_status to DS1
			QCSHeader header =new QCSHeader();
			header.setId_ref(id_ref);
			header.setId_status(BillingSystemConstants.DOCUMENT_STATUS_OPEN_VALUE);
			updateDAO.execute(Q_QCSR03BLogic.SQL_UPDATE_ID_STATUS, header);
			
			//1.4. Call G_ALT_P01
			G_ALT_P01 g_alt_p012 = new G_ALT_P01(queryDAO,updateDAO);
			WF_TABLEBean wf_obj2 = new WF_TABLEBean();
			wf_obj2.setId_ref(id_ref);
			wf_obj2.setId_screen(BillingSystemConstants.ID_SCREEN_QCS);
			wf_obj2.setAppr_status(BillingSystemConstants.APPROVAL_STATUS_PENDING_VALUE);
			wf_obj2.setSection_no(BillingSystemConstants.SECTION_NO_QCS_VALUE);
			wf_obj2.setId_status(BillingSystemConstants.DOCUMENT_STATUS_OPEN_VALUE);
			g_alt_p012.execute(wf_obj2);
			
			//1.5. Insert data to t_wf_approval
			
			T_WF_APPROVAL t_wf_approval = new T_WF_APPROVAL();
			t_wf_approval.setId_ref(id_ref);
			t_wf_approval.setId_login(id_login);
			t_wf_approval.setAppr_status(BillingSystemConstants.APPROVAL_STATUS_PENDING_VALUE);
			t_wf_approval.setId_approver(id_login);//??????????
			t_wf_approval.setOriginal_approver(id_login);
			t_wf_approval.setId_action("11");//?????????
			String section_group = this.input.getSection_group();
			String section_no = this.getSectionNo(section_group);
			t_wf_approval.setSection_group(section_group);
			t_wf_approval.setSection_no(section_no);
			updateDAO.execute(Q_QCSR03BLogic.SQL_INSERT_T_WF_APPROVAL, t_wf_approval);
		}
	}
	/**
	 * Execute when user approve from QCS section group
	 */
	public void executeApproveSectionGroupQCS(){
		T_WF_APPROVAL t_wf_approval = new T_WF_APPROVAL();
		t_wf_approval.setAppr_status(BillingSystemConstants.APPROVAL_STATUS_APPROVED_VALUE);
		t_wf_approval.setSection_group(BillingSystemConstants.SECTION_GROUP_QUO_VALUE);
		t_wf_approval.setSection_no(BillingSystemConstants.SECTION_NO_QCS_VALUE);
		t_wf_approval.setIs_revised(BillingSystemConstants.IS_REVISED_0);
		t_wf_approval.setId_login(this.input.getId_login());
		t_wf_approval.setId_ref(this.input.getId_ref());
		t_wf_approval.setId_approver(this.input.getId_login());
		updateDAO.execute(Q_QCSR03BLogic.SQL_UPDATE_APPROVAL, t_wf_approval);
	}
	/**
	 * Execute when user approve from section group
	 */
	public void executeApproveSectionGroup(){
		
		T_WF_APPROVAL t_wf_approval = new T_WF_APPROVAL();
		t_wf_approval.setAppr_status(BillingSystemConstants.APPROVAL_STATUS_APPROVED_VALUE);
		t_wf_approval.setSection_group(this.input.getSectionGroupActive());
		t_wf_approval.setIs_revised(BillingSystemConstants.IS_REVISED_0);
		t_wf_approval.setId_login(this.input.getId_login());
		t_wf_approval.setId_ref(this.input.getId_ref());
		t_wf_approval.setId_approver(this.input.getId_login());
		t_wf_approval.setSection_no(this.input.getSectionNoActive());
		updateDAO.execute(Q_QCSR03BLogic.SQL_UPDATE_APPROVAL, t_wf_approval);
	}
	/**
	 * Execute when user reject from  section group
	 */
	public void executeRejectSectionGroup(){
		String obt_appr_save = this.input.getObt_appr_save();
		T_WF_APPROVAL t_wf_approval = new T_WF_APPROVAL();
		t_wf_approval.setAppr_status(BillingSystemConstants.APPROVAL_STATUS_REJECTED_VALUE);
		t_wf_approval.setSection_group(this.input.getSectionGroupActive());
		t_wf_approval.setSection_no(this.input.getSectionNoActive());
		if(obt_appr_save.equals("y")){
			t_wf_approval.setIs_revised(BillingSystemConstants.IS_REVISED_1);
		}else{
			t_wf_approval.setIs_revised(BillingSystemConstants.IS_REVISED_0);
		}
		t_wf_approval.setId_login(this.input.getId_login());
		t_wf_approval.setId_ref(this.input.getId_ref());
		t_wf_approval.setId_approver(this.input.getId_login());
		updateDAO.execute(Q_QCSR03BLogic.SQL_UPDATE_APPROVAL, t_wf_approval);
	}
		
	/**
	 * Execute when user reject from  QCS section group
	 */
	public void executeRejectSectionGroupQCS(){
		String obt_appr_save = this.input.getObt_appr_save();
		T_WF_APPROVAL t_wf_approval = new T_WF_APPROVAL();
		t_wf_approval.setAppr_status(BillingSystemConstants.APPROVAL_STATUS_REJECTED_VALUE);
		t_wf_approval.setSection_group(BillingSystemConstants.SECTION_GROUP_QUO_VALUE);
		t_wf_approval.setSection_no(BillingSystemConstants.SECTION_NO_QCS_VALUE);
		if(obt_appr_save.equals("y")){
			t_wf_approval.setIs_revised(BillingSystemConstants.IS_REVISED_1);
		}else{
			t_wf_approval.setIs_revised(BillingSystemConstants.IS_REVISED_0);
		}
		t_wf_approval.setId_login(this.input.getId_login());
		t_wf_approval.setId_ref(this.input.getId_ref());
		t_wf_approval.setId_approver(this.input.getId_login());
		updateDAO.execute(Q_QCSR03BLogic.SQL_UPDATE_APPROVAL, t_wf_approval);
	}
	/**
	 * Get section no of a specified section group
	 * @param section_group
	 * @return
	 */
	private String getSectionNo(String section_group){
		String section_no = "";
		if(section_group.equals(BillingSystemConstants.SECTION_GROUP_PMR_VALUE)){
			section_no = this.input.getSection_no_pmr();
		}else if (section_group.equals(BillingSystemConstants.SECTION_GROUP_BZR_VALUE)){
			section_no = this.input.getSection_no_bzr();
		}else if (section_group.equals(BillingSystemConstants.SECTION_GROUP_CTC_VALUE)){
			section_no = this.input.getSection_no_ctc();
		}else if (section_group.equals(BillingSystemConstants.SECTION_GROUP_PRI_VALUE)){
			section_no = this.input.getSection_no_pri();
		}else if (section_group.equals(BillingSystemConstants.SECTION_GROUP_MRG_VALUE)){
			section_no = this.input.getSection_no_mrg();
		}else if (section_group.equals(BillingSystemConstants.SECTION_GROUP_CRC_VALUE)){
			section_no = this.input.getSection_no_crc();
		}else if (section_group.equals(BillingSystemConstants.SECTION_GROUP_FRX_VALUE)){
			section_no = this.input.getSection_no_frx();
		}else if (section_group.equals(BillingSystemConstants.SECTION_GROUP_COV_VALUE)){
			section_no = this.input.getSection_no_cov();
		}
		return section_no;
	}
	/**
	 * Execute updating data without QCS (t_qcs_h, t_qcs_d)
	 */
	private void executeUpdateWithoutQCS(){
		String id_ref = this.input.getId_ref();
		String id_login = input.getId_login();
	
		//Update data to t_section table
		//Section group : PRM
		QCSSection section = new QCSSection();
		section.setId_ref(id_ref);
		section.setId_login(id_login);
		section.setSection_group(BillingSystemConstants.SECTION_GROUP_PMR_VALUE);
		section.setSection_no(input.getSection_no_pmr());
		section.setRemarks(input.getRemarks_pmr());
		updateDAO.execute(Q_QCSR03BLogic.SQL_UPDATE_T_SECTION, section);
		//Section group : BZR
		section.setSection_group(BillingSystemConstants.SECTION_GROUP_BZR_VALUE);
		section.setSection_no(input.getSection_no_bzr());
		section.setRemarks(input.getRemarks_bzr());
		updateDAO.execute(Q_QCSR03BLogic.SQL_UPDATE_T_SECTION, section);
		//Section group : CTC
		section.setSection_group(BillingSystemConstants.SECTION_GROUP_CTC_VALUE);
		section.setSection_no(input.getSection_no_ctc());
		section.setRemarks(input.getRemarks_ctc());
		updateDAO.execute(Q_QCSR03BLogic.SQL_UPDATE_T_SECTION, section);
		//Section group : PRI
		section.setSection_group(BillingSystemConstants.SECTION_GROUP_PRI_VALUE);
		section.setSection_no(input.getSection_no_pri());
		section.setRemarks(input.getRemarks_pri());
		updateDAO.execute(Q_QCSR03BLogic.SQL_UPDATE_T_SECTION, section);
		//Section group : MRG
		section.setSection_group(BillingSystemConstants.SECTION_GROUP_MRG_VALUE);
		section.setSection_no(input.getSection_no_mrg());
		section.setRemarks(input.getRemarks_mrg());
		updateDAO.execute(Q_QCSR03BLogic.SQL_UPDATE_T_SECTION, section);
		//Section group : CRC
		section.setSection_group(BillingSystemConstants.SECTION_GROUP_CRC_VALUE);
		section.setSection_no(input.getSection_no_crc());
		section.setRemarks(input.getRemarks_crc());
		updateDAO.execute(Q_QCSR03BLogic.SQL_UPDATE_T_SECTION, section);
		//Section group : COV
		section.setSection_group(BillingSystemConstants.SECTION_GROUP_COV_VALUE);
		section.setSection_no(input.getSection_no_cov());
		section.setRemarks(input.getRemarks_cov());
		updateDAO.execute(Q_QCSR03BLogic.SQL_UPDATE_T_SECTION, section);
		//Section group : FRX
		section.setSection_group(BillingSystemConstants.SECTION_GROUP_FRX_VALUE);
		section.setSection_no(input.getSection_no_frx());
		if(input.getSection_no_frx().equals(BillingSystemConstants.SECTION_NO_FRX_STANDARD_VALUE)){
			section.setValue(BillingSystemConstants.FRX_MYR_VALUE);
		}else{
			section.setValue(input.getValue_frx());
		}
		section.setRemarks(input.getRemarks_frx());
		updateDAO.execute(Q_QCSR03BLogic.SQL_UPDATE_T_SECTION, section);
		//Update attachment
		this.deleteAttachment();
		this.insertAttachment(id_login, id_ref);
	}
	
	/**
	 * Insert attachment
	 */
	private void insertAttachment(String id_login,String id_ref){
		//Section group QCS 		
		FileUploadInfo fileUploadInfo = new FileUploadInfo();
		this.uploadFile(this.filePath, this.maxFileSize, this.input.getListFileQCS(), fileUploadInfo);
		this.insertFileToDB(BillingSystemConstants.SECTION_GROUP_QCS_VALUE, id_login, id_ref, fileUploadInfo);
		//Section group PMR		
		this.uploadFile(this.filePath, this.maxFileSize, this.input.getListFilePMR(), fileUploadInfo);
		this.insertFileToDB(BillingSystemConstants.SECTION_GROUP_PMR_VALUE, id_login, id_ref, fileUploadInfo);
		//Section group BZR		
		this.uploadFile(this.filePath, this.maxFileSize, this.input.getListFileBZR(), fileUploadInfo);
		this.insertFileToDB(BillingSystemConstants.SECTION_GROUP_BZR_VALUE, id_login, id_ref, fileUploadInfo);
		//Section group CTC		
		this.uploadFile(this.filePath, this.maxFileSize, this.input.getListFileCTC(), fileUploadInfo);
		this.insertFileToDB(BillingSystemConstants.SECTION_GROUP_CTC_VALUE, id_login, id_ref, fileUploadInfo);
		//Section group PRI		
		this.uploadFile(this.filePath, this.maxFileSize, this.input.getListFilePRI(), fileUploadInfo);
		this.insertFileToDB(BillingSystemConstants.SECTION_GROUP_PRI_VALUE, id_login, id_ref, fileUploadInfo);
		//Section group MRG		
		this.uploadFile(this.filePath, this.maxFileSize, this.input.getListFileMRG(), fileUploadInfo);
		this.insertFileToDB(BillingSystemConstants.SECTION_GROUP_MRG_VALUE, id_login, id_ref, fileUploadInfo);
		//Section group CRC	
		this.uploadFile(this.filePath, this.maxFileSize, this.input.getListFileCRC(), fileUploadInfo);
		this.insertFileToDB(BillingSystemConstants.SECTION_GROUP_CRC_VALUE, id_login, id_ref, fileUploadInfo);
		//Section group FRX		
		this.uploadFile(this.filePath, this.maxFileSize, this.input.getListFileFRX(), fileUploadInfo);
		this.insertFileToDB(BillingSystemConstants.SECTION_GROUP_FRX_VALUE, id_login, id_ref, fileUploadInfo);
		//Section group COV		
		this.uploadFile(this.filePath, this.maxFileSize, this.input.getListFileCOV(), fileUploadInfo);
		this.insertFileToDB(BillingSystemConstants.SECTION_GROUP_COV_VALUE, id_login, id_ref, fileUploadInfo);
	}
	/**
	 * Delete attachment
	 */
	private void deleteAttachment(){
		WF_DOC wf_doc = new WF_DOC();
		wf_doc.setId_ref(this.input.getId_ref());
		wf_doc.setDoc_type(BillingSystemConstants.DOCTYPE_QUOTATION);
		if(this.input.getDeletedAttachmentQCS() != null){
			String deletedAttachmentQCS[] = this.input.getDeletedAttachmentQCS().split(";");
			for(String deletedQCS: deletedAttachmentQCS){
				if(deletedQCS!= null && !deletedQCS.equals("")){
					wf_doc.setSection_group(BillingSystemConstants.SECTION_GROUP_QCS_VALUE);
					wf_doc.setId_doc(deletedQCS);
					updateDAO.execute(Q_QCSR03BLogic.SQL_DELETE_T_WF_DOC_2, wf_doc);
				}
			}
		}
		if(this.input.getDeletedAttachmentPMR() != null){
			String deletedAttachmentPMR[] = this.input.getDeletedAttachmentPMR().split(";");
			for(String deletedPMR: deletedAttachmentPMR){
				if(deletedPMR!= null && !deletedPMR.equals("")){
					wf_doc.setSection_group(BillingSystemConstants.SECTION_GROUP_PMR_VALUE);
					wf_doc.setId_doc(deletedPMR);
					updateDAO.execute(Q_QCSR03BLogic.SQL_DELETE_T_WF_DOC_2, wf_doc);
				}
			}
		}
		if(this.input.getDeletedAttachmentBZR() != null){
			String deletedAttachmentBZR[] = this.input.getDeletedAttachmentBZR().split(";");
			for(String deletedBZR: deletedAttachmentBZR){
				if(deletedBZR!= null && !deletedBZR.equals("")){
					wf_doc.setSection_group(BillingSystemConstants.SECTION_GROUP_BZR_VALUE);
					wf_doc.setId_doc(deletedBZR);
					updateDAO.execute(Q_QCSR03BLogic.SQL_DELETE_T_WF_DOC_2, wf_doc);
				}
			}
		}
		if(this.input.getDeletedAttachmentCTC() != null){
			String deletedAttachmentCTC[] = this.input.getDeletedAttachmentCTC().split(";");
			for(String deletedCTC: deletedAttachmentCTC){
				if(deletedCTC!= null && !deletedCTC.equals("")){
					wf_doc.setSection_group(BillingSystemConstants.SECTION_GROUP_CTC_VALUE);
					wf_doc.setId_doc(deletedCTC);
					updateDAO.execute(Q_QCSR03BLogic.SQL_DELETE_T_WF_DOC_2, wf_doc);
				}
			}
		}
		if(this.input.getDeletedAttachmentPRI() != null){
			String deletedAttachmentPRI[] = this.input.getDeletedAttachmentPRI().split(";");
			for(String deletedPRI: deletedAttachmentPRI){
				if(deletedPRI!= null && !deletedPRI.equals("")){
					wf_doc.setSection_group(BillingSystemConstants.SECTION_GROUP_PRI_VALUE);
					wf_doc.setId_doc(deletedPRI);
					updateDAO.execute(Q_QCSR03BLogic.SQL_DELETE_T_WF_DOC_2, wf_doc);
				}
			}
		}
		if(this.input.getDeletedAttachmentMRG() != null){
			String deletedAttachmentMRG[] = this.input.getDeletedAttachmentMRG().split(";");
			for(String deletedMRG: deletedAttachmentMRG){
				if(deletedMRG!= null && !deletedMRG.equals("")){
					wf_doc.setSection_group(BillingSystemConstants.SECTION_GROUP_MRG_VALUE);
					wf_doc.setId_doc(deletedMRG);
					updateDAO.execute(Q_QCSR03BLogic.SQL_DELETE_T_WF_DOC_2, wf_doc);
				}
			}
		}
		if(this.input.getDeletedAttachmentCRC() != null){
			String deletedAttachmentCRC[] = this.input.getDeletedAttachmentCRC().split(";");
			for(String deletedCRC: deletedAttachmentCRC){
				if(deletedCRC!= null && !deletedCRC.equals("")){
					wf_doc.setSection_group(BillingSystemConstants.SECTION_GROUP_CRC_VALUE);
					wf_doc.setId_doc(deletedCRC);
					updateDAO.execute(Q_QCSR03BLogic.SQL_DELETE_T_WF_DOC_2, wf_doc);
				}
			}
		}
		if(this.input.getDeletedAttachmentFRX() != null){
			String deletedAttachmentFRX[] = this.input.getDeletedAttachmentFRX().split(";");
			for(String deletedFRX: deletedAttachmentFRX){
				if(deletedFRX!= null && !deletedFRX.equals("")){
					wf_doc.setSection_group(BillingSystemConstants.SECTION_GROUP_FRX_VALUE);
					wf_doc.setId_doc(deletedFRX);
					updateDAO.execute(Q_QCSR03BLogic.SQL_DELETE_T_WF_DOC_2, wf_doc);
				}
			}
		}
		if(this.input.getDeletedAttachmentCOV() != null){
			String deletedAttachmentCOV[] = this.input.getDeletedAttachmentCOV().split(";");
			for(String deletedCOV: deletedAttachmentCOV){
				if(deletedCOV!= null && !deletedCOV.equals("")){
					wf_doc.setSection_group(BillingSystemConstants.SECTION_GROUP_COV_VALUE);
					wf_doc.setId_doc(deletedCOV);
					updateDAO.execute(Q_QCSR03BLogic.SQL_DELETE_T_WF_DOC_2, wf_doc);
				}
			}
		}
	}
	//Execute update attachment in case reject
	private void updateAttachmentSectionGroup(String deletedAttachment,FormFile[] listFile){
		//Update deleted attachment
		WF_DOC wf_doc = new WF_DOC();
		wf_doc.setId_ref(this.input.getId_ref());
		wf_doc.setDoc_type(BillingSystemConstants.DOCTYPE_QUOTATION);
		wf_doc.setSection_group(this.input.getSection_group());
		if(deletedAttachment != null){
			String deletedAttachments[] = deletedAttachment.split(";");
			for(String deletedAtt: deletedAttachments){
				if(deletedAtt!= null && !deletedAtt.equals("")){
					wf_doc.setId_doc(deletedAtt);
					updateDAO.execute(Q_QCSR03BLogic.SQL_DELETE_T_WF_DOC_2, wf_doc);
				}
			}
		}
		//Update new attachment
		FileUploadInfo fileUploadInfo = new FileUploadInfo();
		this.uploadFile(this.filePath, this.maxFileSize, listFile, fileUploadInfo);
		this.insertFileToDB(this.input.getSection_group(), this.input.getUvo().getId_user(), this.input.getId_ref(), fileUploadInfo);

	}
	/**
	 * Check whether QCS is approved or not
	 * @param id_ref
	 * @return
	 */
	private boolean isQcsApproved(String id_ref){
		T_WF_APPROVAL qcs_appr = new T_WF_APPROVAL();
		qcs_appr.setId_ref(id_ref);
		qcs_appr.setAppr_status(BillingSystemConstants.APPROVAL_STATUS_APPROVED_VALUE);
		qcs_appr.setSection_no(BillingSystemConstants.SECTION_NO_QCS_VALUE);
		Integer countQcsI = queryDAO.executeForObject(Q_QCSR03BLogic.SQL_COUNT_QCS_APPR, qcs_appr, Integer.class);
		int countQcs = 0;
        if (countQcsI != null) {
            countQcs = countQcsI.intValue();
        }
		if(countQcs == 0){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * Save file to server
	 * @param filePath
	 * @param maxFileSize
	 * @param listFile
	 * @param listFileName
	 * @param listFilePath
	 */
	private void uploadFile(String filePath,int maxFileSize,FormFile [] listFile, FileUploadInfo fileUploadInfo){
		if (filePath == null || "".equals(filePath)) {         
            return;
        } else {
        	//Check path size
            pathCheck(filePath);
        }
		ArrayList<String> listFileName = new ArrayList<String>();
		ArrayList<String> listFilePath = new ArrayList<String>();
        for(FormFile fileList : listFile){
        	if (fileList != null) {
                String fileNameList = fileList.getFileName();
                int fileSizeList = fileList.getFileSize();
                if (fileNameList != null && !"".equals(fileNameList)) {
                	//valid file
                    if (0 <= fileSizeList && fileSizeList <= maxFileSize) {
                    	try{
	                        //Upload file to server
	                    	InputStream streamList = fileList.getInputStream();
	                        OutputStream bosList = new FileOutputStream(filePath  + "/" + fileList.getFileName());	                        
	                        int bytesRead = 0;
	                        byte[] buffer = new byte[8192];	                        
	                        while ((bytesRead = streamList.read(buffer, 0, 8192)) != -1) {
	                        	bosList.write(buffer, 0, bytesRead);
	                        } 
	                        //Add file name, file size, file path
	                        listFileName.add(fileNameList);       
	                        fileNameList = this.determineFileName(filePath, fileNameList);
	                        listFilePath.add(filePath + "/" + fileNameList);	                                               
	                        bosList.close();
	                        streamList.close();
	                        fileList.destroy();
                    	}catch(Exception ex){
                    		
                    	}
                    }
                }
            }
        }
        fileUploadInfo.setListFileName(listFileName);
        fileUploadInfo.setListFilePath(listFilePath);
	}
	/**
	 * Insert file info to DB
	 * @param section_group
	 * @param id_login
	 * @param id_ref
	 * @param listFileName
	 * @param listFilePath
	 */
	private void insertFileToDB(String section_group,String id_login,String id_ref,FileUploadInfo fileUploadInfo){
		 ArrayList<String> listFileName = fileUploadInfo.getListFileName();
		 ArrayList<String> listFilePath = fileUploadInfo.getListFilePath(); 
		 FileInfo fileInfo = new FileInfo();
		 fileInfo.setId_login(id_login);
		 fileInfo.setDoc_type(BillingSystemConstants.DOCTYPE_QUOTATION);
		 T_WF_DOCBean t_wf_doc = new T_WF_DOCBean();
		 t_wf_doc.setId_login(id_login);
		 t_wf_doc.setId_ref(id_ref);
		 t_wf_doc.setSection_group(section_group);
		 for(int i=0;i< listFileName.size();i++){
			 fileInfo.setFilename(listFileName.get(i));
			 fileInfo.setFilelocation(listFilePath.get(i));
			 //Insert to T_DOC table
			 updateDAO.execute(Q_QCSR03BLogic.SQL_INSERT_T_DOC, fileInfo);
			 //Get id_doc
			 String id_doc = queryDAO.executeForObject(Q_QCSR03BLogic.SQL_GET_MAX_ID_DOC, null, String.class);
			 t_wf_doc.setId_doc(id_doc);
			 //Insert to T_WL_DOC table
			 updateDAO.execute(Q_QCSR03BLogic.SQL_INSERT_T_WF_DOC, t_wf_doc);
		 }
	}
	/**
	 * Create path folder if it does not exist
	 * @param path
	 */
	private void pathCheck(String path) {
        File pfile = null;
        pfile = new File(path);
        if (!pfile.exists()) {
            pfile.mkdirs();
        }
    }
	private String determineFileName(String filePath,String fileName){
		String datePattern = "ddMMyyyyHHmmssS";
		String fName = filePath + "/" + fileName;
		File pfile = new File(fName);
		if (!pfile.exists()) {
            return fileName;
        }else{
        	SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
        	String dF = sdf.format(new Date());
        	int i = fileName.lastIndexOf(".");
        	String ext = fileName.substring(i,fileName.length());
        	String name = fileName.substring(0,i);
        	return name.concat(dF).concat(ext);
        }
	}
}
