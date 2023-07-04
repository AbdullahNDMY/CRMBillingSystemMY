package nttdm.bsys.e_dim.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RP_E_DIM_SP1_03Output implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1690265410784846197L;

	private List<Map<String, Object>> listLog;

	public List<Map<String, Object>> getListLog() {
		return listLog;
	}

	public void setListLog(List<Map<String, Object>> listLog) {
		this.listLog = listLog;
	}


}