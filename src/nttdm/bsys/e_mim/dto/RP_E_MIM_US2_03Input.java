/*
 * @(#)RP_E_MIM_US2_03Input.java
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
public class RP_E_MIM_US2_03Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8964174909899776440L;

	/**
     * 
     */
	private Integer idClcImpBatch;

	/**
	 * idClcImpBatch を取得する
	 * 
	 * @return idClcImpBatch
	 */
	public Integer getIdClcImpBatch() {
		return idClcImpBatch;
	}

	/**
	 * idClcImpBatch を設定する
	 * 
	 * @param idClcImpBatch
	 */
	public void setIdClcImpBatch(Integer idClcImpBatch) {
		this.idClcImpBatch = idClcImpBatch;
	}

}