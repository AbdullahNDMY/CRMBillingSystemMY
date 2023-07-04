package nttdm.bsys.m_bnk.dto;

import java.io.Serializable;
import java.util.List;

import nttdm.bsys.m_bnk.bean.M_BNKFormBean;
import nttdm.bsys.m_bnk.bean.M_BNK_bankbean;
import nttdm.bsys.m_bnk.bean.M_BNKContactInfo;
import nttdm.bsys.m_bnk.bean.M_BNK_AdressRA;


public class M_BNKS02vOutput implements Serializable {
	
	
	private static final long serialVersionUID = 8631444323760841623L;
	
	private M_BNK_bankbean bankbeaninfo;
	private M_BNK_AdressRA addressRA;
	private M_BNK_AdressRA addressCA;
	private M_BNKContactInfo contactPC;
	private M_BNKFormBean m_bnkForm;
	public M_BNKContactInfo getContactPC() {
		return contactPC;
	}
	public void setContactPC(M_BNKContactInfo contactPC) {
		this.contactPC = contactPC;
	}
	public M_BNKContactInfo getContactBC() {
		return contactBC;
	}
	public void setContactBC(M_BNKContactInfo contactBC) {
		this.contactBC = contactBC;
	}
	public M_BNKContactInfo getContactOC() {
		return contactOC;
	}
	public void setContactOC(M_BNKContactInfo contactOC) {
		this.contactOC = contactOC;
	}
	private M_BNKContactInfo contactBC;
	private M_BNKContactInfo contactOC;
	
	public M_BNK_AdressRA getAddressRA() {
		return addressRA;
	}
	public void setAddressRA(M_BNK_AdressRA addressRA) {
		this.addressRA = addressRA;
	}
	public M_BNK_AdressRA getAddressCA() {
		return addressCA;
	}
	public void setAddressCA(M_BNK_AdressRA addressCA) {
		this.addressCA = addressCA;
	}
	
	
	public M_BNK_bankbean getBankbeaninfo() {
		return bankbeaninfo;
	}
	public void setBankbeaninfo(M_BNK_bankbean bankbeaninfo) {
		this.bankbeaninfo = bankbeaninfo;
	}
	
	public M_BNKFormBean getM_bnkForm() {
        return m_bnkForm;
    }
    public void setM_bnkForm(M_BNKFormBean m_bnkForm) {
        this.m_bnkForm = m_bnkForm;
    }
	
	
	

}
