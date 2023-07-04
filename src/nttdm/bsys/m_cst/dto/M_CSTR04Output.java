package nttdm.bsys.m_cst.dto;

import java.io.Serializable;

public class M_CSTR04Output implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4800001648457681913L;
	
    /**
     * duplicated PeopleSoft Acc. No message
     */
    private String peopleSoftPopupInfo = null;

	private String mode = null;
	
	private String id_cust = null;
	
	private Integer idAudit = null;

	public Integer getIdAudit() {
		return idAudit;
	}

	public void setIdAudit(Integer idAudit) {
		this.idAudit = idAudit;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getId_cust() {
		return id_cust;
	}

	public void setId_cust(String id_cust) {
		this.id_cust = id_cust;
	}

    public String getPeopleSoftPopupInfo() {
        return peopleSoftPopupInfo;
    }

    public void setPeopleSoftPopupInfo(String peopleSoftPopupInfo) {
        this.peopleSoftPopupInfo = peopleSoftPopupInfo;
    }
}
