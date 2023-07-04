/*
 * @(#)RP_B_BIL_S01_02Output.java
 *
 *
 */
package nttdm.bsys.b_bil.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * OutputDTO class.
 * 
 * @author khungl0ng
 */
public class RP_B_BIL_S01_02Output implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4034009817485905900L;
	
	private String accessType;

	/**
     * 
     */
	private Integer row;

	/**
     * 
     */
	private Integer startIndex;

	/**
     * 
     */
	private Integer totalRow;

	/**
     * 
     */
	private List<HashMap<String, Object>> listReport;
	
	private String fullySettled;
    
    private String partiallySettled;
    
    private String outstanding;
    
    private String initFlg;

    private String cboDeletedStatus;
    
    private String issueTypeSingpost;
    
    private String issueTypeAuto;
    
    private String issueTypeManual;
    
    private String[] deliveryEmail;
    private String deliveryEmail1;
    private String deliveryEmail2;
    
    private String[] delivery;
    private String delivery1;
    private String delivery2;
    private String delivery4;
    
	// Add #156 Start
    private String billCnAmtNegative;
	// Add #156 End
    
    /*#263 [T11] Add customer type at inquiry screen and export result CT 06062017 ST*/
    private String cboCategory;
    /*#263 [T11] Add customer type at inquiry screen and export result CT 06062017 EN*/
	
	/**
	 * row を�?�得�?�る
	 * 
	 * @return row
	 */
	public Integer getRow() {
		return row;
	}

	/**
	 * row を設定�?�る
	 * 
	 * @param row
	 */
	public void setRow(Integer row) {
		this.row = row;
	}

	/**
	 * startIndex を�?�得�?�る
	 * 
	 * @return startIndex
	 */
	public Integer getStartIndex() {
		return startIndex;
	}

	/**
	 * startIndex を設定�?�る
	 * 
	 * @param startIndex
	 */
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	/**
	 * totalRow を�?�得�?�る
	 * 
	 * @return totalRow
	 */
	public Integer getTotalRow() {
		return totalRow;
	}

	/**
	 * totalRow を設定�?�る
	 * 
	 * @param totalRow
	 */
	public void setTotalRow(Integer totalRow) {
		this.totalRow = totalRow;
	}

	/**
	 * listReport を�?�得�?�る
	 * 
	 * @return listReport
	 */
	public List<HashMap<String, Object>> getListReport() {
		return listReport;
	}

	/**
	 * listReport を設定�?�る
	 * 
	 * @param listReport
	 */
	public void setListReport(List<HashMap<String, Object>> listReport) {
		this.listReport = listReport;
	}

    /**
     * @return the fullySettled
     */
    public String getFullySettled() {
        return fullySettled;
    }

    /**
     * @param fullySettled the fullySettled to set
     */
    public void setFullySettled(String fullySettled) {
        this.fullySettled = fullySettled;
    }

    /**
     * @return the partiallySettled
     */
    public String getPartiallySettled() {
        return partiallySettled;
    }

    /**
     * @param partiallySettled the partiallySettled to set
     */
    public void setPartiallySettled(String partiallySettled) {
        this.partiallySettled = partiallySettled;
    }

    /**
     * @return the outstanding
     */
    public String getOutstanding() {
        return outstanding;
    }

    /**
     * @param outstanding the outstanding to set
     */
    public void setOutstanding(String outstanding) {
        this.outstanding = outstanding;
    }

    /**
     * @return the initFlg
     */
    public String getInitFlg() {
        return initFlg;
    }

    /**
     * @param initFlg the initFlg to set
     */
    public void setInitFlg(String initFlg) {
        this.initFlg = initFlg;
    }

    /**
     * @return the cboDeletedStatus
     */
    public String getCboDeletedStatus() {
        return cboDeletedStatus;
    }

    /**
     * @param cboDeletedStatus the cboDeletedStatus to set
     */
    public void setCboDeletedStatus(String cboDeletedStatus) {
        this.cboDeletedStatus = cboDeletedStatus;
    }

    /**
     * @return the issueTypeSingpost
     */
    public String getIssueTypeSingpost() {
        return issueTypeSingpost;
    }

    /**
     * @param issueTypeSingpost the issueTypeSingpost to set
     */
    public void setIssueTypeSingpost(String issueTypeSingpost) {
        this.issueTypeSingpost = issueTypeSingpost;
    }

    /**
     * @return the issueTypeAuto
     */
    public String getIssueTypeAuto() {
        return issueTypeAuto;
    }

    /**
     * @param issueTypeAuto the issueTypeAuto to set
     */
    public void setIssueTypeAuto(String issueTypeAuto) {
        this.issueTypeAuto = issueTypeAuto;
    }

    /**
     * @return the issueTypeManual
     */
    public String getIssueTypeManual() {
        return issueTypeManual;
    }

    /**
     * @param issueTypeManual the issueTypeManual to set
     */
    public void setIssueTypeManual(String issueTypeManual) {
        this.issueTypeManual = issueTypeManual;
    }
    
    /**
     * deliveryEmail を取得する
     * 
     * @return deliveryEmail
     */
    public String[] getDeliveryEmail() {
        return deliveryEmail;
    }

    /**
     * deliveryEmail を設定する
     * 
     * @param deliveryEmail
     */
    public void setDeliveryEmail(String[] deliveryEmail) {
        this.deliveryEmail = deliveryEmail;
    }

    /**
     * delivery を取得する
     * 
     * @return delivery
     */
    public String[] getDelivery() {
        return delivery;
    }

    /**
     * delivery を設定する
     * 
     * @param delivery
     */
    public void setDelivery(String[] delivery) {
        this.delivery = delivery;
    }

	/**
	 * @return the accessType
	 */
	public String getAccessType() {
		return accessType;
	}

	/**
	 * @param accessType the accessType to set
	 */
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	/**
	 * @return the deliveryEmail1
	 */
	public String getDeliveryEmail1() {
		return deliveryEmail1;
	}

	/**
	 * @param deliveryEmail1 the deliveryEmail1 to set
	 */
	public void setDeliveryEmail1(String deliveryEmail1) {
		this.deliveryEmail1 = deliveryEmail1;
	}

	/**
	 * @return the deliveryEmail2
	 */
	public String getDeliveryEmail2() {
		return deliveryEmail2;
	}

	/**
	 * @param deliveryEmail2 the deliveryEmail2 to set
	 */
	public void setDeliveryEmail2(String deliveryEmail2) {
		this.deliveryEmail2 = deliveryEmail2;
	}

	/**
	 * @return the delivery1
	 */
	public String getDelivery1() {
		return delivery1;
	}

	/**
	 * @param delivery1 the delivery1 to set
	 */
	public void setDelivery1(String delivery1) {
		this.delivery1 = delivery1;
	}

	/**
	 * @return the delivery2
	 */
	public String getDelivery2() {
		return delivery2;
	}

	/**
	 * @param delivery2 the delivery2 to set
	 */
	public void setDelivery2(String delivery2) {
		this.delivery2 = delivery2;
	}

	/**
	 * @return the delivery4
	 */
	public String getDelivery4() {
		return delivery4;
	}

	/**
	 * @param delivery4 the delivery4 to set
	 */
	public void setDelivery4(String delivery4) {
		this.delivery4 = delivery4;
	}

    // Add #156 Start
	/**
	 * @return the billCnAmtNegative
	 */
	public String getBillCnAmtNegative() {
		return billCnAmtNegative;
	}

	/**
	 * @param billCnAmtNegative the billCnAmtNegative to set
	 */
	public void setBillCnAmtNegative(String billCnAmtNegative) {
		this.billCnAmtNegative = billCnAmtNegative;
	}
	// Add #156 End
	/*#263 [T11] Add customer type at inquiry screen and export result CT 06062017 ST*/
	public String getCboCategory() {
		return cboCategory;
	}

	public void setCboCategory(String cboCategory) {
		this.cboCategory = cboCategory;
	}
    /*#263 [T11] Add customer type at inquiry screen and export result CT 06062017 EN*/
	
}