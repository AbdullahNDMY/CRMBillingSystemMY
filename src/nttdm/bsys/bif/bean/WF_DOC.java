package nttdm.bsys.bif.bean;

public class WF_DOC {
	private String id_ref;
	private String section_group;
	private String doc_type;
	private String fileName;
	private String id_doc;
	private Integer idAudit;
	
	public Integer getIdAudit() {
		return idAudit;
	}
	public void setIdAudit(Integer idAudit) {
		this.idAudit = idAudit;
	}
	public String getId_doc() {
		return id_doc;
	}
	public void setId_doc(String id_doc) {
		this.id_doc = id_doc;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getId_ref() {
		return id_ref;
	}
	public void setId_ref(String id_ref) {
		this.id_ref = id_ref;
	}
	public String getSection_group() {
		return section_group;
	}
	public void setSection_group(String section_group) {
		this.section_group = section_group;
	}
	public String getDoc_type() {
		return doc_type;
	}
	public void setDoc_type(String doc_type) {
		this.doc_type = doc_type;
	}


	
}
