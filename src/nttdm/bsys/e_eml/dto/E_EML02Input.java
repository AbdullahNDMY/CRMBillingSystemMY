package nttdm.bsys.e_eml.dto;

import java.io.Serializable;

public class E_EML02Input implements Serializable{
	private static final long serialVersionUID = -3825291939065997528L;
	private String billDocuNo;
	private String batchId;
	private String noEmail;
	private Integer row;
	private Integer startIndex=0;
	/**
	 * @return the billDocuNo
	 */
	public String getBillDocuNo() {
		return billDocuNo;
	}
	/**
	 * @param billDocuNo the billDocuNo to set
	 */
	public void setBillDocuNo(String billDocuNo) {
		this.billDocuNo = billDocuNo;
	}
	/**
	 * @return the batchId
	 */
	public String getBatchId() {
		return batchId;
	}
	/**
	 * @param batchId the batchId to set
	 */
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	/**
	 * @return the noEmail
	 */
	public String getNoEmail() {
		return noEmail;
	}
	/**
	 * @param noEmail the noEmail to set
	 */
	public void setNoEmail(String noEmail) {
		this.noEmail = noEmail;
	}
	/**
	 * @return the row
	 */
	public Integer getRow() {
		return row;
	}
	/**
	 * @param row the row to set
	 */
	public void setRow(Integer row) {
		this.row = row;
	}
	/**
	 * @return the startIndex
	 */
	public Integer getStartIndex() {
		return startIndex;
	}
	/**
	 * @param startIndex the startIndex to set
	 */
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

}
