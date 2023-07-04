package nttdm.bsys.e_mim_us1.dto;

import java.io.Serializable;

public class E_MIM_US1_3BlogicInput implements Serializable {
	private String idIpassImpBatch;
	private String csvFileName;

	public String getCsvFileName() {
		return csvFileName;
	}

	public void setCsvFileName(String csvFileName) {
		this.csvFileName = csvFileName;
	}

	public String getIdIpassImpBatch() {
		return idIpassImpBatch;
	}

	public void setIdIpassImpBatch(String idIpassImpBatch) {
		this.idIpassImpBatch = idIpassImpBatch;
	}
	
	

}