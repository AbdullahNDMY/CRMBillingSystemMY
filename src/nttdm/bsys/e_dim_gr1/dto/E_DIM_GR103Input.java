package nttdm.bsys.e_dim_gr1.dto;

import java.io.Serializable;

public class E_DIM_GR103Input implements Serializable {
	
	private String idGirImpBatch;
	
	private String filename;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getIdGirImpBatch() {
		return idGirImpBatch;
	}

	public void setIdGirImpBatch(String idGirImpBatch) {
		this.idGirImpBatch = idGirImpBatch;
	}
	
	
	

}