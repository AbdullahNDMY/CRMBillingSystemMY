/*
 * @(#)RP_E_DIM_SP1_03Input.java
 *
 *
 */
package nttdm.bsys.e_dim.dto;

import java.io.Serializable;

/**
 * InputDTO class.
 * 
 * @author khungl0ng
 */
public class RP_E_DIM_SP1_03Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2012108166514792347L;

	/**
     * 
     */
	private Integer idSgpImpBatch;

	/**
	 * idSgpImpBatch を取得する
	 * 
	 * @return idSgpImpBatch
	 */
	public Integer getIdSgpImpBatch() {
		return idSgpImpBatch;
	}

	/**
	 * idSgpImpBatch を設定する
	 * 
	 * @param idSgpImpBatch
	 */
	public void setIdSgpImpBatch(Integer idSgpImpBatch) {
		this.idSgpImpBatch = idSgpImpBatch;
	}

}