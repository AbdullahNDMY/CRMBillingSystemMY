/*
 * @(#)E_MIM_US1BlogicOutput.java
 *
 *
 */
package nttdm.bsys.e_mim_us1.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import nttdm.bsys.e_dim_gr1.dto.TGirImpHdDto;

/**
 * OutputDTO class.
 * 
 * @author ss054
 */
public class E_MIM_US1BlogicOutput implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2494954227461109958L;

	private Integer totalCount=0;
	private Integer row=0;
	private Integer startIndex=0;
	private List<TIpassImpHdDto> ipassList = new ArrayList<TIpassImpHdDto>();
	
	
	public List<TIpassImpHdDto> getIpassList() {
		return ipassList;
	}
	public void setIpassList(List<TIpassImpHdDto> ipassList) {
		this.ipassList = ipassList;
	}
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