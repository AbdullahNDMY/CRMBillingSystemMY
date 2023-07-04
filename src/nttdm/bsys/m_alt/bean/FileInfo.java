package nttdm.bsys.m_alt.bean;

public class FileInfo {
	private String id_login;
	private long id_doc;
	private String doc_type;
	private String filename;
	private String filelocation;
	private String is_deleted;
	private String date_created;
	private String date_updated;
	private long id_msg;
	
	public long getId_msg() {
		return id_msg;
	}
	public void setId_msg(long id_msg) {
		this.id_msg = id_msg;
	}
	public String getId_login() {
		return id_login;
	}
	public void setId_login(String id_login) {
		this.id_login = id_login;
	}
	public long getId_doc() {
		return id_doc;
	}
	public void setId_doc(long id_doc) {
		this.id_doc = id_doc;
	}
	public String getDoc_type() {
		return doc_type;
	}
	public void setDoc_type(String doc_type) {
		this.doc_type = doc_type;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilelocation() {
		return filelocation;
	}
	public void setFilelocation(String filelocation) {
		this.filelocation = filelocation;
	}
	public String getIs_deleted() {
		return is_deleted;
	}
	public void setIs_deleted(String is_deleted) {
		this.is_deleted = is_deleted;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	public String getDate_updated() {
		return date_updated;
	}
	public void setDate_updated(String date_updated) {
		this.date_updated = date_updated;
	}
	
}
