package nttdm.bsys.e_eml.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

public class E_EML03Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -307395157194264214L;

	/**
     * 
     */
	private String[] idRefs;
	private String moduleId;
	private String autSign = "";
	private String pdfZipFile;
	private List<Map<String, Object>> billDocList;
	
	/**
     * 
     */
	private BillingSystemUserValueObject uvo;

	/**
	 * @return the idRefs
	 */
	public String[] getIdRefs() {
		return idRefs;
	}

	/**
	 * @param idRefs the idRefs to set
	 */
	public void setIdRefs(String[] idRefs) {
		this.idRefs = idRefs;
	}

	/**
	 * @return the uvo
	 */
	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}

	/**
	 * @param uvo the uvo to set
	 */
	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}

	/**
	 * @return the moduleId
	 */
	public String getModuleId() {
		return moduleId;
	}

	/**
	 * @param moduleId the moduleId to set
	 */
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	/**
	 * @return the autSign
	 */
	public String getAutSign() {
		return autSign;
	}

	/**
	 * @param autSign the autSign to set
	 */
	public void setAutSign(String autSign) {
		this.autSign = autSign;
	}

	/**
	 * @return the pdfZipFile
	 */
	public String getPdfZipFile() {
		return pdfZipFile;
	}

	/**
	 * @param pdfZipFile the pdfZipFile to set
	 */
	public void setPdfZipFile(String pdfZipFile) {
		this.pdfZipFile = pdfZipFile;
	}

	/**
	 * @return the billDocList
	 */
	public List<Map<String, Object>> getBillDocList() {
		return billDocList;
	}

	/**
	 * @param billDocList the billDocList to set
	 */
	public void setBillDocList(List<Map<String, Object>> billDocList) {
		this.billDocList = billDocList;
	}
}
