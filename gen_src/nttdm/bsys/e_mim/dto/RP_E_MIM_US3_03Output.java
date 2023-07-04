/*
 * @(#)RP_E_MIM_US3_03Output.java
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
public class RP_E_MIM_US3_03Output implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7981828015309226449L;

	/**
     * 
     */
	private List<HashMap<String, Object>> listLog;

	/**
	 * listLog を取得する
	 * 
	 * @return listLog
	 */
	public List<HashMap<String, Object>> getListLog() {
		return listLog;
	}

	/**
	 * listLog を設定する
	 * 
	 * @param listLog
	 */
	public void setListLog(List<HashMap<String, Object>> listLog) {
		this.listLog = listLog;
	}

}