package nttdm.bsys.e_eml.dto;

import java.io.Serializable;
import java.util.List;

import nttdm.bsys.e_eml.bean.E_EML02Bean;

public class E_EML02Output implements Serializable{
	private static final long serialVersionUID = -5538432094237818425L;
	private List<E_EML02Bean> emailInfo;
	private Integer totalCount=0;
	private Integer row;
	private Integer startIndex=0;
	/**
	 * @return the emailInfo
	 */
	public List<E_EML02Bean> getEmailInfo() {
		return emailInfo;
	}
	/**
	 * @param emailInfo the emailInfo to set
	 */
	public void setEmailInfo(List<E_EML02Bean> emailInfo) {
		this.emailInfo = emailInfo;
	}
	/**
	 * @return the totalCount
	 */
	public Integer getTotalCount() {
		return totalCount;
	}
	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
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
