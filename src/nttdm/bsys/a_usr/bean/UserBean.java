package nttdm.bsys.a_usr.bean;
/**
 * <div>
 *  Describe user information 
 * </div>
 * @author Phu.Nguyen
 */
public class UserBean {
	/**
	 * <div>id_user</div>
	 */
	private String id_user = null;
	/**
	 * <div>user_name</div>
	 */
	private String user_name = null;
	/**
	 * <div>id_div</div>
	 */
	private String id_div = null;
	/**
	 * <div>id_dept</div>
	 */
	private String id_dept = null;
	/**
	 * <div>div_name</div>
	 */
	private String div_name = null;
	/**
	 * <div>dept_name</div>
	 */
	private String dept_name = null;
	/**
	 * <div>row_num</div>
	 */	
	private String row_num = null;
	/**
	 * <div>password</div> 
	 */
	private String password;
	/**
	 * <div>id_role</div> 
	 */
	private String id_role;
	/**
	 * <div>did_no</div> 
	 */
	private String did_no;
	/**
	 * <div>ofs_mobile_no</div> 
	 */
	private String ofs_mobile_no;
	/**
	 * <div>email</div> 
	 */
	private String email;
	/**
	 * <div>pplsoft_acc_id</div> 
	 */
	private String pplsoft_acc_id;

	/**
	 * <div>tel_no</div> 
	 */
	private String tel_no;
	
	/**
	 * <div>tel_extno</div> 
	 */
	private String tel_extno;
	/**
	 * <div>user_status</div> 
	 */
	private String user_status;
	/**
	 * <div>photo</div>
	 */
	private byte[] photo;

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getUser_status() {
		return user_status;
	}

	public void setUser_status(String user_status) {
		this.user_status = user_status;
	}

	public String getTel_extno() {
		return tel_extno;
	}

	public void setTel_extno(String tel_extno) {
		this.tel_extno = tel_extno;
	}

	public String getTel_no() {
		return tel_no;
	}

	public void setTel_no(String tel_no) {
		this.tel_no = tel_no;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId_role() {
		return id_role;
	}
	
	public void setId_role(String id_role) {
		this.id_role = id_role;
	}
	
	public String getDid_no() {
		return did_no;
	}
	
	public void setDid_no(String did_no) {
		this.did_no = did_no;
	}
	
	public String getOfs_mobile_no() {
		return ofs_mobile_no;
	}
	
	public void setOfs_mobile_no(String ofs_mobile_no) {
		this.ofs_mobile_no = ofs_mobile_no;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getRow_num() {
		return row_num;
	}

	public void setRow_num(String row_num) {
		this.row_num = row_num;
	}
	
	public String getId_user() {
		return id_user;
	}
	
	public void setId_user(String id_user) {
		this.id_user = id_user;
	}
	
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getId_div() {
		return id_div;
	}

	public void setId_div(String id_div) {
		this.id_div = id_div;
	}

	public String getId_dept() {
		return id_dept;
	}

	public void setId_dept(String id_dept) {
		this.id_dept = id_dept;
	}

	public String getDiv_name() {
		return div_name;
	}

	public void setDiv_name(String div_name) {
		this.div_name = div_name;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getPplsoft_acc_id() {
		return pplsoft_acc_id;
	}

	public void setPplsoft_acc_id(String pplsoft_acc_id) {
		this.pplsoft_acc_id = pplsoft_acc_id;
	}
}
