/*
 * @(#)RP_E_DIM_SP1_01Input.java
 *
 *
 */
package nttdm.bsys.e_dim.dto;

import java.io.Serializable;
import java.util.List;

import nttdm.bsys.common.fw.BillingSystemUserValueObject;

import org.apache.struts.util.LabelValueBean;

/**
 * InputDTO class.
 * 
 * @author khungl0ng
 */
public class RP_E_DIM_SP1_01Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5715793378829376295L;
	
    /**
     * 
     */
    private BillingSystemUserValueObject uvo;

	/**
     * 
     */
	private Integer row;

	/**
     * 
     */
	private Integer startIndex;

    /**
     * Bank Account
     */
    private String bankAcc;

    /**
     * Bank Account
     */
    private List<LabelValueBean> cbBankAcc;
    
	/**
	 * row を取得する
	 * 
	 * @return row
	 */
	public Integer getRow() {
		return row;
	}

	/**
	 * row を設定する
	 * 
	 * @param row
	 */
	public void setRow(Integer row) {
		this.row = row;
	}

	/**
	 * startIndex を取得する
	 * 
	 * @return startIndex
	 */
	public Integer getStartIndex() {
		return startIndex;
	}

	/**
	 * startIndex を設定する
	 * 
	 * @param startIndex
	 */
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

    /**
     * @return the bankAcc
     */
    public String getBankAcc() {
        return bankAcc;
    }

    /**
     * @param bankAcc
     *            the bankAcc to set
     */
    public void setBankAcc(String bankAcc) {
        this.bankAcc = bankAcc;
    }

    /**
     * @return the cbBankAcc
     */
    public List<LabelValueBean> getCbBankAcc() {
        return cbBankAcc;
    }

    /**
     * @param cbBankAcc
     *            the cbBankAcc to set
     */
    public void setCbBankAcc(List<LabelValueBean> cbBankAcc) {
        this.cbBankAcc = cbBankAcc;
    }
    
	public BillingSystemUserValueObject getUvo() {
		return uvo;
	}

	public void setUvo(BillingSystemUserValueObject uvo) {
		this.uvo = uvo;
	}
}