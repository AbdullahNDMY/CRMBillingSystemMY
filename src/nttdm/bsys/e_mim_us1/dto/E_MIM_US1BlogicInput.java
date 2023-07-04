/*
 * @(#)E_MIM_US1BlogicInput.java
 *
 *
 */
package nttdm.bsys.e_mim_us1.dto;

import java.io.Serializable;

/**
 * InputDTO class.
 * 
 * @author ss054
 */
public class E_MIM_US1BlogicInput implements Serializable {
	
	private Integer totalCount=0;
	private Integer row=0;
	private Integer startIndex=0;
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getRow() {
		return row;
	}
	public void setRow(Integer row) {
		this.row = row;
	}
	public Integer getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	

}