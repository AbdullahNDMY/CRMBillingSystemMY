/**
 * @(#)detail_info_e_exp_f02.java
 *
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.batch;

/**
 * Export Statement Detail
 */
public class detail_info_e_exp_f02 {
    private String inVoiceDate;
    private String inVoiceNumber;
    private String entryType;
    private double amount_due;
    private double item_activity;
    private String po = "";
    private String paymentid = "";
    private String doc_type = "";

	public detail_info_e_exp_f02(String invoice, String invoinum, String entry,
	        double amount, String postr, String payment, double item_act) {
        inVoiceDate = invoice;
        inVoiceNumber = invoinum;
        entryType = entry;
        amount_due = amount;
        po = postr;
        paymentid = payment;
        item_activity = item_act;
    }

    public detail_info_e_exp_f02() {
        inVoiceDate = "";
        inVoiceNumber = "";
        entryType = "";
        po = "";
        amount_due = 0;
        paymentid = "";
        item_activity = 0;
    }

    public String getDoc_type() {
		return doc_type;
	}

	public void setDoc_type(String doc_type) {
		this.doc_type = doc_type;
	}
	
    public String getInVoiceDate() {
        return inVoiceDate;
    }

    public void setInVoiceDate(String inVoiceDate) {
        this.inVoiceDate = inVoiceDate;
    }

    public String getInVoiceNumber() {
        return inVoiceNumber;
    }

    public void setInVoiceNumber(String inVoiceNumber) {
        this.inVoiceNumber = inVoiceNumber;
    }

    public String getPo() {
        return po;
    }

    public void setPo(String po) {
        this.po = po;
    }

    public String getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(String paymentid) {
        this.paymentid = paymentid;
    }

    public double getAmount_due() {
        return amount_due;
    }

    public void setAmount_due(double amount_due) {
        this.amount_due = amount_due;
    }

    public String getEntryType() {
        return entryType;
    }

    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }

    public double getItem_activity() {
        return item_activity;
    }

    public void setItem_activity(double item_activity) {
        this.item_activity = item_activity;
    }
}
