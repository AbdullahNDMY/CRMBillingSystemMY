/*
 * @(#)RP_B_BTH_P01_03Input.java
 *
 *
 */
package nttdm.bsys.b_bth.dto;

import java.io.Serializable;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;

/**
 * InputDTO class.
 * 
 * @author khungl0ng
 */
public class RP_B_BTH_P01_03Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -307395157194264214L;

	/**
     * 
     */
	private String[] idRefs;

	/**
     * AutSign
     */
    private String autSign="";
	
	/**
     * 
     */
	private BillingSystemUserValueObject uvo;
	
	/*#270 B-BTH-S01 Billing Document Batch Print add print option CT 28062017*/
	private int printOption = 0;
	/*#270 B-BTH-S01 Billing Document Batch Print add print option CT 28062017*/
	/**
	 * idRefs を取得する
	 * 
	 * @return idRefs
	 */
	public String[] getIdRefs() {
		return idRefs;
	}

	/**
	 * idRefs を設定する
	 * 
	 * @param idRefs
	 */
	public void setIdRefs(String[] idRefs) {
		this.idRefs = idRefs;
	}

	public String getAutSign() {
        return autSign;
    }

    public void setAutSign(String autSign) {
        this.autSign = autSign;
    }

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
	/*#270 B-BTH-S01 Billing Document Batch Print add print option CT 28062017*/
	public int getPrintOption() {
		return printOption;
	}

	public void setPrintOption(int printOption) {
		this.printOption = printOption;
	}
	/*#270 B-BTH-S01 Billing Document Batch Print add print option CT 28062017*/
}