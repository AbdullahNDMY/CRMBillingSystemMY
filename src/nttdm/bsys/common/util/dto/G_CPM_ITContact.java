package nttdm.bsys.common.util.dto;

/**
 * @author hungtm
 *
 */

public class G_CPM_ITContact {
	private String customerName;
	private Integer subcriptionId;
	private String contactType;
	private String contactName;
	private String designation;
	private String email;
	private String telNo;
	private String faxNo;
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Integer getSubcriptionId() {
		return subcriptionId;
	}
	public void setSubcriptionId(Integer subcriptionId) {
		this.subcriptionId = subcriptionId;
	}
	public String getContactType() {
		return contactType;
	}
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public String getFaxNo() {
		return faxNo;
	}
	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}
	public String toString() {
		String str = "";
		String id = customerName + ',' + String.valueOf(subcriptionId);
		String blank = ",,,,,";
		String contactInformation = ',' + contactName + ',' + designation + ',' + email + ',' + telNo + ',' + faxNo;
		if(contactType.equals("C1"))
			str += id + contactInformation + blank + blank;
		if(contactType.equals("C2"))
			str += id + blank + contactInformation + blank;
		if(contactType.equals("C3"))
			str += id + blank + blank + contactInformation ;
		return str;
	}
}
