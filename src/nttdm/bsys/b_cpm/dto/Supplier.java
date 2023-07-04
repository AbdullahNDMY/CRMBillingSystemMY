package nttdm.bsys.b_cpm.dto;

import java.io.Serializable;

public class Supplier implements Serializable {
	private String supplierId;
	private String supplierName;
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	

}
