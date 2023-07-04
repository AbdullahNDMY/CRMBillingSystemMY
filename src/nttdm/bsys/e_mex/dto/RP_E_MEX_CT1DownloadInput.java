/*
 * @(#)RP_E_MEX_CT1DownloadInput.java
 *
 *
 */
package nttdm.bsys.e_mex.dto;

import java.io.Serializable;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * InputDTO class.
 * 
 * @author thinhtv
 */
public class RP_E_MEX_CT1DownloadInput implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5197242116406997295L;

	/**
     * 
     */
	private BillingSystemUserValueObject uvo;

	/**
     * 
     */
	private String filename;
	
	/**
	 * idCitExpBatch
	 */
	private String idCitExpBatch;

	/**
	 * uvo を取得する
	 * 
	 * @return uvo
	 */
	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}

	/**
	 * uvo を設定する
	 * 
	 * @param uvo
	 */
	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}

	/**
	 * filename を取得する
	 * 
	 * @return filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * filename を設定する
	 * 
	 * @param filename
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

    /**
     * @return the idCitExpBatch
     */
    public String getIdCitExpBatch() {
        return idCitExpBatch;
    }

    /**
     * @param idCitExpBatch the idCitExpBatch to set
     */
    public void setIdCitExpBatch(String idCitExpBatch) {
        this.idCitExpBatch = idCitExpBatch;
    }

}