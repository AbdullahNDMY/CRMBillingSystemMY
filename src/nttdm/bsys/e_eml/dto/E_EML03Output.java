package nttdm.bsys.e_eml.dto;

import java.io.Serializable;

public class E_EML03Output implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -9059641224603217702L;

	/**
     * 
     */
	private String[] idRefs;

	/**
	 * @return the idRefs
	 */
	public String[] getIdRefs() {
		return idRefs;
	}

	/**
	 * @param idRefs the idRefs to set
	 */
	public void setIdRefs(String[] idRefs) {
		this.idRefs = idRefs;
	}

}
