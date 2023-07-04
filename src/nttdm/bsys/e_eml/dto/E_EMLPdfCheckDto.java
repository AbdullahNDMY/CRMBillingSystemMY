package nttdm.bsys.e_eml.dto;

import java.io.Serializable;

public class E_EMLPdfCheckDto implements Serializable{
	private static final long serialVersionUID = -3825291939065997528L;
	private String id_doc;
	private String id_ref;
	private String fileName;
	private String fileLocation;
	private String filePass;
	/**
	 * @return the id_ref
	 */
	public String getId_ref() {
		return id_ref;
	}
	/**
	 * @param id_ref the id_ref to set
	 */
	public void setId_ref(String id_ref) {
		this.id_ref = id_ref;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the fileLocation
	 */
	public String getFileLocation() {
		return fileLocation;
	}
	/**
	 * @param fileLocation the fileLocation to set
	 */
	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}
	/**
	 * @return the filePass
	 */
	public String getFilePass() {
		return filePass;
	}
	/**
	 * @param filePass the filePass to set
	 */
	public void setFilePass(String filePass) {
		this.filePass = filePass;
	}
	/**
	 * @return the id_doc
	 */
	public String getId_doc() {
		return id_doc;
	}
	/**
	 * @param id_doc the id_doc to set
	 */
	public void setId_doc(String id_doc) {
		this.id_doc = id_doc;
	}

}
