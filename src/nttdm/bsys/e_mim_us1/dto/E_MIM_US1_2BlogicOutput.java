/*
 * @(#)E_MIM_US1_2BlogicOutput.java
 *
 *
 */
package nttdm.bsys.e_mim_us1.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * OutputDTO class.
 * 
 * @author ss054
 */
public class E_MIM_US1_2BlogicOutput implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8895368698577997970L;

	private Integer totalCount=0;
	private Integer row=0;
	private Integer startIndex=0;
	private HashMap<String, String>  alertMsg;
	private List<TIpassImpHdDto> ipassList = new ArrayList<TIpassImpHdDto>();
	
	public HashMap<String, String> getAlertMsg() {
		return alertMsg;
	}
	public void setAlertMsg(HashMap<String, String> alertMsg) {
		this.alertMsg = alertMsg;
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
	public List<TIpassImpHdDto> getIpassList() {
		return ipassList;
	}
	public void setIpassList(List<TIpassImpHdDto> ipassList) {
		this.ipassList = ipassList;
	}
	
}