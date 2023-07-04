/*
 * @(#)RP_E_MIM_US3_03Input.java
 *
 *
 */
package nttdm.bsys.e_mim.dto;

import java.io.Serializable;

/**
 * InputDTO class.
 * 
 * @author khungl0ng
 */
public class RP_E_MIM_US3_03Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2421496348423318410L;

	/**
     * 
     */
	private String idRadImpBatch;

	/**
	 * idRadImpBatch を取得する
	 * 
	 * @return idRadImpBatch
	 */
	public String getIdRadImpBatch() {
		return idRadImpBatch;
	}

	/**
	 * idRadImpBatch を設定する
	 * 
	 * @param idRadImpBatch
	 */
	public void setIdRadImpBatch(String idRadImpBatch) {
		this.idRadImpBatch = idRadImpBatch;
	}

}