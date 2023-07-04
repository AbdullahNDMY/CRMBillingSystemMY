package nttdm.bsys.m_gbs.bean;

/**
 * @author loamanma
 * 
 */
public class ResultItem {

    private String itemId = "";
    private String itemCode = "";
    private Integer itemIdInt;
    private String itemName = "";
    private String isDeleted = "";
    private String idLogin = "";
    private String idAudit = "";
    private String isUsed = "Y";
    //For M_TAX
    private String taxId;
    private Integer taxIdInt;
    private String taxCode="";
    private String taxRate="";
    private String accountCode="";
    private String taxDesr1="";
    private String taxDesr2="";
    private String isTaxUsed = "Y";
    private String endUser = "";
    private String modeType = "";
    private String category = "";
    private String abbreviation = "";
    
    public String getIsUsed() {
        if (itemId == null || itemId.equals("")) {
            return "N";
        } else {
            return isUsed;
        }
    }

    public void setIsUsed(String isUsed) {
        this.isUsed = isUsed;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Integer getItemIdInt() {
        return itemIdInt;
    }

    public void setItemIdInt(Integer itemIdInt) {
        this.itemIdInt = itemIdInt;
    }

    public String getItemName() {
        if (itemName == null) {
            return "";
        } else {
            return itemName.trim();
        }
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(String idLogin) {
        this.idLogin = idLogin;
    }

    public String getIdAudit() {
        return idAudit;
    }

    public void setIdAudit(String idAudit) {
        this.idAudit = idAudit;
    }

    /**
     * taxId get method
     * @return taxId
     */
    public String getTaxId() {
        return taxId;
    }
    
    /**
     * taxId set method
     * @param taxId
     */
    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    /**
     * taxIdInt get method
     * @return taxIdInt
     */
    public Integer getTaxIdInt() {
        return taxIdInt;
    }

    /**
     * taxIdInt set method
     * @param taxIdInt
     */
    public void setTaxIdInt(Integer taxIdInt) {
        this.taxIdInt = taxIdInt;
    }

    /**
     * taxCode get method
     * @return taxCode
     */
    public String getTaxCode() {
        return taxCode;
    }
    
    /**
     * taxCode set method
     * @param taxCode
     */
    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }
    
    /**
     * taxRate get method
     * @return taxRate
     */
    public String getTaxRate() {
        return taxRate;
    }
    
    /**
     * taxRate set method
     * @param taxRate
     */
    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }
    
    /**
     * accountCode get method
     * @return accountCode
     */
    public String getAccountCode() {
        return accountCode;
    }

    /**
     * accountCode set method
     * @param accountCode
     */
    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    /**
     * taxDesr1 get method
     * @return taxDesr1
     */
    public String getTaxDesr1() {
        return taxDesr1;
    }
    
    /**
     * taxDesr1 set method
     * @param taxDesr1
     */
    public void setTaxDesr1(String taxDesr1) {
        this.taxDesr1 = taxDesr1;
    }
    
    /**
     * taxDesr2 get method
     * @return taxDesr2
     */
    public String getTaxDesr2() {
        return taxDesr2;
    }
    
    /**
     * taxDesr2 set method
     * @param taxDesr2
     */
    public void setTaxDesr2(String taxDesr2) {
        this.taxDesr2 = taxDesr2;
    }
    
    public String getIsTaxUsed() {
        if (taxId == null || taxId.equals("")) {
            return "N";
        } else {
            return isTaxUsed;
        }
    }

    public void setIsTaxUsed(String isTaxUsed) {
        this.isTaxUsed = isTaxUsed;
    }

    public int hashCode() {
        return super.hashCode() + itemId.hashCode() + itemName.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof ResultItem) {
            ResultItem item = (ResultItem) obj;
            if (this.itemId.equals(item.getItemId())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

	/**
	 * @return the endUser
	 */
	public String getEndUser() {
		return endUser;
	}

	/**
	 * @param endUser the endUser to set
	 */
	public void setEndUser(String endUser) {
		this.endUser = endUser;
	}

	/**
	 * @return the modeType
	 */
	public String getModeType() {
		return modeType;
	}

	/**
	 * @param modeType the modeType to set
	 */
	public void setModeType(String modeType) {
		this.modeType = modeType;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the abbreviation
	 */
	public String getAbbreviation() {
		return abbreviation;
	}

	/**
	 * @param abbreviation the abbreviation to set
	 */
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
}