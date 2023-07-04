/*
 * @(#)RP_E_MEX_GR1_2Input.java
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
public class RP_E_MEX_GR1_2Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 888002472844432043L;

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