package nttdm.bsys.b_cpm.dto;

import java.io.Serializable;

public class PlanBatchMapping implements Serializable {
	private String batchId;

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	
}
