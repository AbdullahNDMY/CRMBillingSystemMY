/*
 * @(#)E_MIM_US2BlogicOutput.java
 *
 *
 */
package nttdm.bsys.e_mim_us1.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import nttdm.bsys.e_dim_gr1.dto.TBatchLogDto;

/**
 * OutputDTO class.
 * 
 * @author ss054
 */
public class E_MIM_US2BlogicOutput implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3581656434221757717L;

	private List<TBatchLogDto> logList = new ArrayList<TBatchLogDto>();

	public List<TBatchLogDto> getLogList() {
		return logList;
	}

	public void setLogList(List<TBatchLogDto> logList) {
		this.logList = logList;
	}
	

}