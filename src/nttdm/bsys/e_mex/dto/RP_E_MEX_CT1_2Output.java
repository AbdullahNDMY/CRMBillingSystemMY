/*
 * @(#)RP_E_MEX_CT1_2Output.java
 *
 *
 */
package nttdm.bsys.e_mex.dto;

import java.io.Serializable;
import java.util.List;

/**
 * OutputDTO class.
 * 
 * @author thinhtv
 */
public class RP_E_MEX_CT1_2Output implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8098687357141314407L;

	/**
     * 
     */
	private List<java.util.Map<String, Object>> logsList;

	/**
	 * logsList を取得する
	 * 
	 * @return logsList
	 */
	public List<java.util.Map<String, Object>> getLogsList() {
		return logsList;
	}

	/**
	 * logsList を設定する
	 * 
	 * @param logsList
	 */
	public void setLogsList(List<java.util.Map<String, Object>> logsList) {
		this.logsList = logsList;
	}

}