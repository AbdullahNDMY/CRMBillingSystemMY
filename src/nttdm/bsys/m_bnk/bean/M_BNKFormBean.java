package nttdm.bsys.m_bnk.bean;


import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;
import nttdm.bsys.m_bnk.bean.M_BNK_bankbean;
import java.util.List;
import nttdm.bsys.m_bnk.bean.M_BNKContactInfo;
import nttdm.bsys.m_bnk.bean.M_BNK_AdressRA;


public class M_BNKFormBean extends ValidatorActionFormEx {

	private static final long serialVersionUID = -7466401836857442359L;
	
	private String bank_fullname;
	private String bank_code;
	private String branch_code;
	private String bank_bic_code;
	
	public String getBranch_code() {
		return branch_code;
	}
	public void setBranch_code(String branch_code) {
		this.branch_code = branch_code;
	}

	private int startIndex;
	
	private int totalCount;
	private int row;
	private List<M_BNK_bankbean> listallbank;
	private String lblBankFullName;
	private String tbxBankCode;
	private String tbxBankName;
	private String tbxBankID;
	private String lblidbank;
	private String page_status;
	
	

	private String tbxBranchCode;
	private String tbxBranchName;
	private String tbxBankBICCode;
    private String tbxTelNo;
	private String tbxFaxNo;
	private String tbxAddressLine1RA;
	private String tbxAddressLine2RA;
	private String tbxAddressLine3RA;
	private String tbxAddressLine1CA;
	private String tbxAddressLine2CA;
	private String tbxAddressLine3CA;
	private int checkpagetype=0;
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	private String tbxZipCodeRA;
	private String tbxZipCodeCA;
	public int getCheckpagetype() {
		return checkpagetype;
	}
	public void setCheckpagetype(int checkpagetype) {
		this.checkpagetype = checkpagetype;
	}

	private String cboAddressCountryRA;
	private String cboAddressCountryCA;
	
	private String tbxContactNamePC;
	private String tbxDesignationPC;
	private String tbxEmailPC;
	private String tbxTelephoneNoPC;
	private String tbxDIDNoPC;
	private String tbxFaxNoPC;
	private String tbxMobileNoPc;
	
	private String tbxContactNameBC;
	private String tbxDesignationBC;
	private String tbxEmailBC;
	private String tbxTelephoneNoBC;
	private String tbxDIDNoBC;
	private String tbxFaxNoBC;
	private String tbxMobileNoBC;
	
	private String tbxContactNameOC;
	private String tbxDesignationOC;
	private String tbxEmailOC;
	private String tbxTelephoneNoOC;
	private String tbxDIDNoOC;
	private String tbxFaxNoOC;
	private String tbxMobileNoOC;
	private List<M_BNK_bankbean> listsearch;
	private String lblidlogin;
	
	public String getLblidlogin() {
		return lblidlogin;
	}
	public void setLblidlogin(String lblidlogin) {
		this.lblidlogin = lblidlogin;
	}
	public String getLblidbank() {
		return lblidbank;
	}
	public void setLblidbank(String lblidbank) {
		this.lblidbank = lblidbank;
	}

	public List<M_BNK_bankbean> getListsearch() {
		return listsearch;
	}
	public void setListsearch(List<M_BNK_bankbean> listsearch) {
		this.listsearch = listsearch;
	}
	public String getTbxBranchName() {
		return tbxBranchName;
	}
	public void setTbxBranchName(String tbxBranchName) {
		this.tbxBranchName = tbxBranchName;
	}

	public String getLblBankFullName() {
		return lblBankFullName;
	}
	public void setLblBankFullName(String lblBankFullName) {
		this.lblBankFullName = lblBankFullName;
	}
	public String getTbxBankCode() {
		return tbxBankCode;
	}
	public void setTbxBankCode(String tbxBankCode) {
		this.tbxBankCode = tbxBankCode;
	}
	public String getTbxBankID() {
		return tbxBankID;
	}
	public void setTbxBankID(String tbxBankID) {
		this.tbxBankID = tbxBankID;
	}
	public String getTbxBankName() {
		return tbxBankName;
	}
	public void setTbxBankName(String tbxBankName) {
		this.tbxBankName = tbxBankName;
	}
	public String getTbxBranchCode() {
		return tbxBranchCode;
	}
	public void setTbxBranchCode(String tbxBranchCode) {
		this.tbxBranchCode = tbxBranchCode;
	}
	public String getTbxBankBICCode() {
        return tbxBankBICCode;
    }
    public void setTbxBankBICCode(String tbxBankBICCode) {
        this.tbxBankBICCode = tbxBankBICCode;
    }
	public String getTbxTelNo() {
		return tbxTelNo;
	}
	public void setTbxTelNo(String tbxTelNo) {
		this.tbxTelNo = tbxTelNo;
	}
	public String getPage_status() {
		return page_status;
	}
	public void setPage_status(String page_status) {
		this.page_status = page_status;
	}
	public String getTbxFaxNo() {
		return tbxFaxNo;
	}
	public void setTbxFaxNo(String tbxFaxNo) {
		this.tbxFaxNo = tbxFaxNo;
	}
	public String getTbxAddressLine1RA() {
		return tbxAddressLine1RA;
	}
	public void setTbxAddressLine1RA(String tbxAddressLine1RA) {
		this.tbxAddressLine1RA = tbxAddressLine1RA;
	}
	public String getTbxAddressLine2RA() {
		return tbxAddressLine2RA;
	}
	public void setTbxAddressLine2RA(String tbxAddressLine2RA) {
		this.tbxAddressLine2RA = tbxAddressLine2RA;
	}
	public String getTbxAddressLine3RA() {
		return tbxAddressLine3RA;
	}
	public void setTbxAddressLine3RA(String tbxAddressLine3RA) {
		this.tbxAddressLine3RA = tbxAddressLine3RA;
	}
	public String getTbxAddressLine1CA() {
		return tbxAddressLine1CA;
	}
	public void setTbxAddressLine1CA(String tbxAddressLine1CA) {
		this.tbxAddressLine1CA = tbxAddressLine1CA;
	}
	public String getTbxAddressLine2CA() {
		return tbxAddressLine2CA;
	}
	public void setTbxAddressLine2CA(String tbxAddressLine2CA) {
		this.tbxAddressLine2CA = tbxAddressLine2CA;
	}
	public String getTbxAddressLine3CA() {
		return tbxAddressLine3CA;
	}
	public void setTbxAddressLine3CA(String tbxAddressLine3CA) {
		this.tbxAddressLine3CA = tbxAddressLine3CA;
	}
	public String getTbxZipCodeRA() {
		return tbxZipCodeRA;
	}
	public void setTbxZipCodeRA(String tbxZipCodeRA) {
		this.tbxZipCodeRA = tbxZipCodeRA;
	}
	public String getTbxZipCodeCA() {
		return tbxZipCodeCA;
	}
	public void setTbxZipCodeCA(String tbxZipCodeCA) {
		this.tbxZipCodeCA = tbxZipCodeCA;
	}
	public String getCboAddressCountryRA() {
		return cboAddressCountryRA;
	}
	public void setCboAddressCountryRA(String cboAddressCountryRA) {
		this.cboAddressCountryRA = cboAddressCountryRA;
	}
	
	public String getCboAddressCountryCA() {
		return cboAddressCountryCA;
	}
	public void setCboAddressCountryCA(String cboAddressCountryCA) {
		this.cboAddressCountryCA = cboAddressCountryCA;
	}
	public String getTbxContactNamePC() {
		return tbxContactNamePC;
	}
	public void setTbxContactNamePC(String tbxContactNamePC) {
		this.tbxContactNamePC = tbxContactNamePC;
	}
	public String getTbxDesignationPC() {
		return tbxDesignationPC;
	}
	public void setTbxDesignationPC(String tbxDesignationPC) {
		this.tbxDesignationPC = tbxDesignationPC;
	}
	public String getTbxEmailPC() {
		return tbxEmailPC;
	}
	public void setTbxEmailPC(String tbxEmailPC) {
		this.tbxEmailPC = tbxEmailPC;
	}
	public String getTbxTelephoneNoPC() {
		return tbxTelephoneNoPC;
	}
	public void setTbxTelephoneNoPC(String tbxTelephoneNoPC) {
		this.tbxTelephoneNoPC = tbxTelephoneNoPC;
	}
	public String getTbxDIDNoPC() {
		return tbxDIDNoPC;
	}
	public void setTbxDIDNoPC(String tbxDIDNoPC) {
		this.tbxDIDNoPC = tbxDIDNoPC;
	}
	public String getTbxFaxNoPC() {
		return tbxFaxNoPC;
	}
	public void setTbxFaxNoPC(String tbxFaxNoPC) {
		this.tbxFaxNoPC = tbxFaxNoPC;
	}
	public String getTbxMobileNoPc() {
		return tbxMobileNoPc;
	}
	public void setTbxMobileNoPc(String tbxMobileNoPc) {
		this.tbxMobileNoPc = tbxMobileNoPc;
	}
	public String getTbxContactNameBC() {
		return tbxContactNameBC;
	}
	public void setTbxContactNameBC(String tbxContactNameBC) {
		this.tbxContactNameBC = tbxContactNameBC;
	}
	public String getTbxDesignationBC() {
		return tbxDesignationBC;
	}
	public void setTbxDesignationBC(String tbxDesignationBC) {
		this.tbxDesignationBC = tbxDesignationBC;
	}
	public String getTbxEmailBC() {
		return tbxEmailBC;
	}
	public void setTbxEmailBC(String tbxEmailBC) {
		this.tbxEmailBC = tbxEmailBC;
	}
	public String getTbxTelephoneNoBC() {
		return tbxTelephoneNoBC;
	}
	public void setTbxTelephoneNoBC(String tbxTelephoneNoBC) {
		this.tbxTelephoneNoBC = tbxTelephoneNoBC;
	}
	public String getTbxDIDNoBC() {
		return tbxDIDNoBC;
	}
	public void setTbxDIDNoBC(String tbxDIDNoBC) {
		this.tbxDIDNoBC = tbxDIDNoBC;
	}
	public String getTbxFaxNoBC() {
		return tbxFaxNoBC;
	}
	public void setTbxFaxNoBC(String tbxFaxNoBC) {
		this.tbxFaxNoBC = tbxFaxNoBC;
	}
	public String getTbxMobileNoBC() {
		return tbxMobileNoBC;
	}
	public void setTbxMobileNoBC(String tbxMobileNoBC) {
		this.tbxMobileNoBC = tbxMobileNoBC;
	}
	public String getTbxContactNameOC() {
		return tbxContactNameOC;
	}
	public void setTbxContactNameOC(String tbxContactNameOC) {
		this.tbxContactNameOC = tbxContactNameOC;
	}
	public String getTbxDesignationOC() {
		return tbxDesignationOC;
	}
	public void setTbxDesignationOC(String tbxDesignationOC) {
		this.tbxDesignationOC = tbxDesignationOC;
	}
	public String getTbxEmailOC() {
		return tbxEmailOC;
	}
	public void setTbxEmailOC(String tbxEmailOC) {
		this.tbxEmailOC = tbxEmailOC;
	}
	public String getTbxTelephoneNoOC() {
		return tbxTelephoneNoOC;
	}
	public void setTbxTelephoneNoOC(String tbxTelephoneNoOC) {
		this.tbxTelephoneNoOC = tbxTelephoneNoOC;
	}
	public String getTbxDIDNoOC() {
		return tbxDIDNoOC;
	}
	public void setTbxDIDNoOC(String tbxDIDNoOC) {
		this.tbxDIDNoOC = tbxDIDNoOC;
	}
	public String getTbxFaxNoOC() {
		return tbxFaxNoOC;
	}
	public void setTbxFaxNoOC(String tbxFaxNoOC) {
		this.tbxFaxNoOC = tbxFaxNoOC;
	}
	public String getTbxMobileNoOC() {
		return tbxMobileNoOC;
	}
	public void setTbxMobileNoOC(String tbxMobileNoOC) {
		this.tbxMobileNoOC = tbxMobileNoOC;
	}

	private String hypBankReference;
	public String getHypBankReference() {
		return hypBankReference;
	}
	public void setHypBankReference(String hypBankReference) {
		this.hypBankReference = hypBankReference;
	}

	private M_BNK_AdressRA addressRA;
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
	private String  zipcodeRA;
	private String countryRA;
	private M_BNK_AdressRA addressCA;
	private String  zipcodeCA;
	private String countryCA;
	private M_BNKContactInfo contactPC;
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
	
	private M_BNK_bankbean bankbeaninfo;
	
	public M_BNK_bankbean getBankbeaninfo() {
		return bankbeaninfo;
	}
	public void setBankbeaninfo(M_BNK_bankbean bankbeaninfo) {
		this.bankbeaninfo = bankbeaninfo;
	}
	
	
	public String getZipcodeRA() {
		return zipcodeRA;
	}
	public void setZipcodeRA(String zipcodeRA) {
		this.zipcodeRA = zipcodeRA;
	}
	public String getCountryRA() {
		return countryRA;
	}
	public void setCountryRA(String countryRA) {
		this.countryRA = countryRA;
	}

	public String getZipcodeCA() {
		return zipcodeCA;
	}
	public void setZipcodeCA(String zipcodeCA) {
		this.zipcodeCA = zipcodeCA;
	}
	public String getCountryCA() {
		return countryCA;
	}
	public void setCountryCA(String countryCA) {
		this.countryCA = countryCA;
	}
	
	//private String bankcode;
	
	
	
	
	
	public String getBank_fullname() {
		return bank_fullname;
	}
	public void setBank_fullname(String bank_fullname) {
		this.bank_fullname = bank_fullname;
	}
	public String getBank_code() {
		return bank_code;
	}
	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}
	public String getBank_bic_code() {
        return bank_bic_code;
    }
    public void setBank_bic_code(String bank_bic_code) {
        this.bank_bic_code = bank_bic_code;
    }
	
	

	public List<M_BNK_bankbean> getListallbank() {
		return listallbank;
	}
	public void setListallbank(List<M_BNK_bankbean> listallbank) {
		this.listallbank = listallbank;
	}
	
    private String index;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
