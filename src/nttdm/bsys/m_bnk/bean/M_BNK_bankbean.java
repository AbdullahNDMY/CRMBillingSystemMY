package nttdm.bsys.m_bnk.bean;

import java.io.Serializable;

public class M_BNK_bankbean implements Serializable {
	
	private static final long serialVersionUID = -746640436857442359L;
	private String ID_BANK;
	public String getID_BANK() {
		return ID_BANK;
	}
	public void setID_BANK(String id_bank) {
		ID_BANK = id_bank;
	}
	public String getBANK_FULL_NAME() {
		return BANK_FULL_NAME;
	}
	public void setBANK_FULL_NAME(String bank_full_name) {
		BANK_FULL_NAME = bank_full_name;
	}
	public String getBANK_CODE() {
		return BANK_CODE;
	}
	public void setBANK_CODE(String bank_code) {
		BANK_CODE = bank_code;
	}
	public String getBANK_NAME() {
		return BANK_NAME;
	}
	public void setBANK_NAME(String bank_name) {
		BANK_NAME = bank_name;
	}
	public String getBRANCH_CODE() {
		return BRANCH_CODE;
	}
	public void setBRANCH_CODE(String branch_code) {
		BRANCH_CODE = branch_code;
	}
	public String getBRANCH_NAME() {
		return BRANCH_NAME;
	}
	public void setBRANCH_NAME(String branch_name) {
		BRANCH_NAME = branch_name;
	}
	public String getBANK_TEL_NO() {
		return BANK_TEL_NO;
	}
	public void setBANK_TEL_NO(String bank_tel_no) {
		BANK_TEL_NO = bank_tel_no;
	}
	public String getBANK_FAX_NO() {
		return BANK_FAX_NO;
	}
	public void setBANK_FAX_NO(String bank_fax_no) {
		BANK_FAX_NO = bank_fax_no;
	}
	public int getIdnum() {
		return idnum;
	}
	public void setIdnum(int idnum) {
		this.idnum = idnum;
	}
	private String BANK_FULL_NAME;
	private String BANK_CODE;
	private String BANK_NAME;
	private String BRANCH_CODE;
	private String BRANCH_NAME;
	private String BANK_TEL_NO;
	private String BANK_FAX_NO;
	private int idnum=0;
	private String BIC_CODE;
	
    public String getBIC_CODE() {
        return BIC_CODE;
    }
    public void setBIC_CODE(String bIC_CODE) {
        BIC_CODE = bIC_CODE;
    }
	
	

}
