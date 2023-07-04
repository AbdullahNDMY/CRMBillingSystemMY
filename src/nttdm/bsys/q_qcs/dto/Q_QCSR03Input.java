/*
 * @(#)Q_QCSR03Input.java
 *
 *
 */
package nttdm.bsys.q_qcs.dto;

import java.io.Serializable;

import org.apache.struts.upload.FormFile;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * InputDTO class.
 * 
 * @author ss051
 */
public class Q_QCSR03Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5002828352202476818L;

	private String id_ref;
	private String id_cust;
	private String id_conslt;
	private String id_quo;
	private String req_user;
	private String date_req;
	private String cust_mode;
	private String ctterm;
	private String ctterm3_day;
	private String deposit;
	private String remarks;
	private String id_status;
	private String is_closed;
	private String [] capacity ;
	private String [] nrc ;
	private String [] mrc ;
	private String [] term ;
	private String [] tariff ;
	private String [] disc ;
	private String [] svc_grp;
	private String [] total;
	private String section_no_pmr;
	private String remarks_pmr;
	private String section_no_bzr;
	private String remarks_bzr;
	private String section_no_ctc;
	private String remarks_ctc;
	private String section_no_pri;
	private String remarks_pri;
	private String section_no_mrg;
	private String remarks_mrg;
	private String section_no_crc;
	private String remarks_crc;
	private String section_no_frx;
	private String remarks_frx;
	private String value_frx;
	private String section_no_cov;
	private String remarks_cov;
	private String id_login;
	private BillingSystemUserValueObject uvo;
	private String clickEvent;
	/**
	 * 'yes' if user want to save any changes
	 * 'no' if not
	 */
	private String obt_appr_save;
	private String showPopalt;
	private String section_group;
	private FormFile[] listFileQCS;
	private FormFile[] listFilePMR;
	private FormFile[] listFileBZR;
	private FormFile[] listFileCTC;
	private FormFile[] listFilePRI;
	private FormFile[] listFileMRG;
	private FormFile[] listFileCRC;
	private FormFile[] listFileFRX;
	private FormFile[] listFileCOV;
	private String deletedAttachmentQCS;
	private String deletedAttachmentPMR;
	private String deletedAttachmentBZR;
	private String deletedAttachmentCTC;
	private String deletedAttachmentPRI;
	private String deletedAttachmentMRG;
	private String deletedAttachmentCRC;
	private String deletedAttachmentFRX;
	private String deletedAttachmentCOV;
	private String sectionGroupActive;
	private String sectionNoActive;
	private String id_wf_approval;
	private String section_no;
	
	
	public String getId_wf_approval() {
		return id_wf_approval;
	}
	public void setId_wf_approval(String id_wf_approval) {
		this.id_wf_approval = id_wf_approval;
	}
	public String getSection_no() {
		return section_no;
	}
	public void setSection_no(String section_no) {
		this.section_no = section_no;
	}
	public String getSectionNoActive() {
		return sectionNoActive;
	}
	public void setSectionNoActive(String sectionNoActive) {
		this.sectionNoActive = sectionNoActive;
	}
	public String getSectionGroupActive() {
		return sectionGroupActive;
	}
	public void setSectionGroupActive(String sectionGroupActive) {
		this.sectionGroupActive = sectionGroupActive;
	}
	public String getDeletedAttachmentQCS() {
		return deletedAttachmentQCS;
	}
	public void setDeletedAttachmentQCS(String deletedAttachmentQCS) {
		this.deletedAttachmentQCS = deletedAttachmentQCS;
	}
	public String getDeletedAttachmentPMR() {
		return deletedAttachmentPMR;
	}
	public void setDeletedAttachmentPMR(String deletedAttachmentPMR) {
		this.deletedAttachmentPMR = deletedAttachmentPMR;
	}
	public String getDeletedAttachmentBZR() {
		return deletedAttachmentBZR;
	}
	public void setDeletedAttachmentBZR(String deletedAttachmentBZR) {
		this.deletedAttachmentBZR = deletedAttachmentBZR;
	}
	public String getDeletedAttachmentCTC() {
		return deletedAttachmentCTC;
	}
	public void setDeletedAttachmentCTC(String deletedAttachmentCTC) {
		this.deletedAttachmentCTC = deletedAttachmentCTC;
	}
	public String getDeletedAttachmentPRI() {
		return deletedAttachmentPRI;
	}
	public void setDeletedAttachmentPRI(String deletedAttachmentPRI) {
		this.deletedAttachmentPRI = deletedAttachmentPRI;
	}
	public String getDeletedAttachmentMRG() {
		return deletedAttachmentMRG;
	}
	public void setDeletedAttachmentMRG(String deletedAttachmentMRG) {
		this.deletedAttachmentMRG = deletedAttachmentMRG;
	}
	public String getDeletedAttachmentCRC() {
		return deletedAttachmentCRC;
	}
	public void setDeletedAttachmentCRC(String deletedAttachmentCRC) {
		this.deletedAttachmentCRC = deletedAttachmentCRC;
	}
	public String getDeletedAttachmentFRX() {
		return deletedAttachmentFRX;
	}
	public void setDeletedAttachmentFRX(String deletedAttachmentFRX) {
		this.deletedAttachmentFRX = deletedAttachmentFRX;
	}
	public String getDeletedAttachmentCOV() {
		return deletedAttachmentCOV;
	}
	public void setDeletedAttachmentCOV(String deletedAttachmentCOV) {
		this.deletedAttachmentCOV = deletedAttachmentCOV;
	}
	public FormFile[] getListFilePMR() {
		return listFilePMR;
	}
	public void setListFilePMR(FormFile[] listFilePMR) {
		this.listFilePMR = listFilePMR;
	}
	public FormFile[] getListFileBZR() {
		return listFileBZR;
	}
	public void setListFileBZR(FormFile[] listFileBZR) {
		this.listFileBZR = listFileBZR;
	}
	public FormFile[] getListFileCTC() {
		return listFileCTC;
	}
	public void setListFileCTC(FormFile[] listFileCTC) {
		this.listFileCTC = listFileCTC;
	}
	public FormFile[] getListFilePRI() {
		return listFilePRI;
	}
	public void setListFilePRI(FormFile[] listFilePRI) {
		this.listFilePRI = listFilePRI;
	}
	public FormFile[] getListFileMRG() {
		return listFileMRG;
	}
	public void setListFileMRG(FormFile[] listFileMRG) {
		this.listFileMRG = listFileMRG;
	}
	public FormFile[] getListFileCRC() {
		return listFileCRC;
	}
	public void setListFileCRC(FormFile[] listFileCRC) {
		this.listFileCRC = listFileCRC;
	}
	public FormFile[] getListFileFRX() {
		return listFileFRX;
	}
	public void setListFileFRX(FormFile[] listFileFRX) {
		this.listFileFRX = listFileFRX;
	}
	public FormFile[] getListFileCOV() {
		return listFileCOV;
	}
	public void setListFileCOV(FormFile[] listFileCOV) {
		this.listFileCOV = listFileCOV;
	}
	public FormFile[] getListFileQCS() {
		return listFileQCS;
	}
	public void setListFileQCS(FormFile[] listFileQCS) {
		this.listFileQCS = listFileQCS;
	}
	public String getSection_group() {
		return section_group;
	}
	public void setSection_group(String section_group) {
		this.section_group = section_group;
	}
	public String getShowPopalt() {
		return showPopalt;
	}
	public void setShowPopalt(String showPopalt) {
		this.showPopalt = showPopalt;
	}
	public String getObt_appr_save() {
		return obt_appr_save;
	}
	public void setObt_appr_save(String obt_appr_save) {
		this.obt_appr_save = obt_appr_save;
	}
	
	public String getClickEvent() {
		return clickEvent;
	}

	public void setClickEvent(String clickEvent) {
		this.clickEvent = clickEvent;
	}

	public String getCust_mode() {
		return cust_mode;
	}

	public void setCust_mode(String cust_mode) {
		this.cust_mode = cust_mode;
	}
	
	public String[] getTotal() {
		return total;
	}

	public void setTotal(String[] total) {
		this.total = total;
	}

	public String[] getSvc_grp() {
		return svc_grp;
	}

	public void setSvc_grp(String[] svc_grp) {
		this.svc_grp = svc_grp;
	}

	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}

	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}

	public String getId_login() {
		return id_login;
	}

	public void setId_login(String id_login) {
		this.id_login = id_login;
	}

	public String getId_ref() {
		return id_ref;
	}

	public void setId_ref(String id_ref) {
		this.id_ref = id_ref;
	}

	public String getId_cust() {
		return id_cust;
	}

	public void setId_cust(String id_cust) {
		this.id_cust = id_cust;
	}

	public String getId_conslt() {
		return id_conslt;
	}

	public void setId_conslt(String id_conslt) {
		this.id_conslt = id_conslt;
	}

	public String getId_quo() {
		return id_quo;
	}

	public void setId_quo(String id_quo) {
		this.id_quo = id_quo;
	}

	public String getReq_user() {
		return req_user;
	}

	public void setReq_user(String req_user) {
		this.req_user = req_user;
	}

	public String getDate_req() {
		return date_req;
	}

	public void setDate_req(String date_req) {
		this.date_req = date_req;
	}

	public String getCtterm() {
		return ctterm;
	}

	public void setCtterm(String ctterm) {
		this.ctterm = ctterm;
	}

	public String getCtterm3_day() {
		return ctterm3_day;
	}

	public void setCtterm3_day(String ctterm3_day) {
		this.ctterm3_day = ctterm3_day;
	}

	public String getDeposit() {
		return deposit;
	}

	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getId_status() {
		return id_status;
	}

	
	public void setId_status(String id_status) {
		this.id_status = id_status;
	}

	public String getIs_closed() {
		return is_closed;
	}

	public void setIs_closed(String is_closed) {
		this.is_closed = is_closed;
	}

	public String[] getCapacity() {
		return capacity;
	}
	public void setCapacity(String[] capacity) {
		this.capacity = capacity;
	}
	public String[] getNrc() {
		return nrc;
	}
	public void setNrc(String[] nrc) {
		this.nrc = nrc;
	}
	public String[] getMrc() {
		return mrc;
	}
	public void setMrc(String[] mrc) {
		this.mrc = mrc;
	}
	public String[] getTerm() {
		return term;
	}
	public void setTerm(String[] term) {
		this.term = term;
	}
	public String[] getTariff() {
		return tariff;
	}
	public void setTariff(String[] tariff) {
		this.tariff = tariff;
	}
	public String[] getDisc() {
		return disc;
	}
	public void setDisc(String[] disc) {
		this.disc = disc;
	}
	

	public String getSection_no_pmr() {
		return section_no_pmr;
	}

	public void setSection_no_pmr(String section_no_pmr) {
		this.section_no_pmr = section_no_pmr;
	}

	public String getRemarks_pmr() {
		return remarks_pmr;
	}

	public void setRemarks_pmr(String remarks_pmr) {
		this.remarks_pmr = remarks_pmr;
	}

	public String getSection_no_bzr() {
		return section_no_bzr;
	}

	public void setSection_no_bzr(String section_no_bzr) {
		this.section_no_bzr = section_no_bzr;
	}

	public String getRemarks_bzr() {
		return remarks_bzr;
	}

	public void setRemarks_bzr(String remarks_bzr) {
		this.remarks_bzr = remarks_bzr;
	}

	
	public String getSection_no_ctc() {
		return section_no_ctc;
	}

	public void setSection_no_ctc(String section_no_ctc) {
		this.section_no_ctc = section_no_ctc;
	}

	public String getRemarks_ctc() {
		return remarks_ctc;
	}

	public void setRemarks_ctc(String remarks_ctc) {
		this.remarks_ctc = remarks_ctc;
	}

	
	public String getSection_no_pri() {
		return section_no_pri;
	}

	public void setSection_no_pri(String section_no_pri) {
		this.section_no_pri = section_no_pri;
	}

	public String getRemarks_pri() {
		return remarks_pri;
	}

	public void setRemarks_pri(String remarks_pri) {
		this.remarks_pri = remarks_pri;
	}

	public String getSection_no_mrg() {
		return section_no_mrg;
	}

	public void setSection_no_mrg(String section_no_mrg) {
		this.section_no_mrg = section_no_mrg;
	}

	
	public String getRemarks_mrg() {
		return remarks_mrg;
	}

	public void setRemarks_mrg(String remarks_mrg) {
		this.remarks_mrg = remarks_mrg;
	}

	public String getSection_no_crc() {
		return section_no_crc;
	}

	public void setSection_no_crc(String section_no_crc) {
		this.section_no_crc = section_no_crc;
	}

	public String getRemarks_crc() {
		return remarks_crc;
	}

	public void setRemarks_crc(String remarks_crc) {
		this.remarks_crc = remarks_crc;
	}

	public String getSection_no_frx() {
		return section_no_frx;
	}

	
	public void setSection_no_frx(String section_no_frx) {
		this.section_no_frx = section_no_frx;
	}

	public String getRemarks_frx() {
		return remarks_frx;
	}

	public void setRemarks_frx(String remarks_frx) {
		this.remarks_frx = remarks_frx;
	}

	public String getValue_frx() {
		return value_frx;
	}

	
	public void setValue_frx(String value_frx) {
		this.value_frx = value_frx;
	}

	public String getSection_no_cov() {
		return section_no_cov;
	}

	public void setSection_no_cov(String section_no_cov) {
		this.section_no_cov = section_no_cov;
	}

	
	public String getRemarks_cov() {
		return remarks_cov;
	}

	
	public void setRemarks_cov(String remarks_cov) {
		this.remarks_cov = remarks_cov;
	}

}