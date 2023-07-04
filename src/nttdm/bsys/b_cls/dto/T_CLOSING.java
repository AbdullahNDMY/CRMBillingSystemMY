package nttdm.bsys.b_cls.dto;

public final class T_CLOSING {
	
	private String idYearMonth;
	
	private String idYearMonthDisplay;

	private String moduleId;
	
	private String isClosed;
		
	private String dateCreated;
	
	private String dateUpdated;
	
	private String idLogin;
	
	private String year;
	
	private String month;
	
	private Integer totalDoc;
	
	private Integer totalOutDoc;
	
	private String index;


	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public Integer getTotalDoc() {
		return totalDoc;
	}

	public void setTotalDoc(Integer totalDoc) {
		this.totalDoc = totalDoc;
	}

	public Integer getTotalOutDoc() {
		return totalOutDoc;
	}

	public void setTotalOutDoc(Integer totalOutDoc) {
		this.totalOutDoc = totalOutDoc;
	}

	public String getIdYearMonth() {
		return idYearMonth;
	}

	public void setIdYearMonth(String idYearMonth) {
		this.idYearMonth = idYearMonth;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getIsClosed() {
		return isClosed;
	}

	public void setIsClosed(String isClosed) {
		this.isClosed = isClosed;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(String dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public String getIdLogin() {
		return idLogin;
	}

	public void setIdLogin(String idLogin) {
		this.idLogin = idLogin;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
	public String getIdYearMonthDisplay() {
		return idYearMonthDisplay;
	}

	public void setIdYearMonthDisplay(String idYearMonthDisplay) {
		this.idYearMonthDisplay = idYearMonthDisplay;
	}
	
}
