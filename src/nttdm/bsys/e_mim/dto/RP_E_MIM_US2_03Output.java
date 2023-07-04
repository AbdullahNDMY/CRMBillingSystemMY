/*
 * @(#)RP_E_MIM_US2_03Output.java
 *
 *
 */
package nttdm.bsys.e_mim.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * OutputDTO class.
 * 
 * @author khungl0ng
 */
public class RP_E_MIM_US2_03Output implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6483105000661502853L;

	/**
     * 
     */
	private List<HashMap> listLog;

	/**
	 * listLog を取得する
	 * 
	 * @return listLog
	 */
	public List<HashMap> getListLog() {
		return listLog;
	}

	/**
	 * listLog を設定する
	 * 
	 * @param listLog
	 */
	public void setListLog(List<HashMap> listLog) {
		this.listLog = listLog;
	}

}