/*
 * @(#)RP_E_MEX_CT1_2Input.java
 *
 *
 */
package nttdm.bsys.e_mex.dto;

import java.io.Serializable;

/**
 * InputDTO class.
 * 
 * @author thinhtv
 */
public class RP_E_MEX_CT1_2Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1121056099155560399L;

	/**
     * 
     */
	private Integer idBatch;

	/**
	 * idBatch を取得する
	 * 
	 * @return idBatch
	 */
	public Integer getIdBatch() {
		return idBatch;
	}

	/**
	 * idBatch を設定する
	 * 
	 * @param idBatch
	 */
	public void setIdBatch(Integer idBatch) {
		this.idBatch = idBatch;
	}

}