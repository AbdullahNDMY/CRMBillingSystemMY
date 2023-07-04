/*
 * @(#)A_USRFormBean.java
 *
 *
 */
package nttdm.bsys.a_usr.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.struts.upload.FormFile;

import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;
/**
 * <div>
 * Formbean is extended from ValidatorActionFormEx
 * </div>
 * @author Phu.Nguyen
 * @see jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx
 */
public class A_USRFormBean extends ValidatorActionFormEx {

	/**
	 * <div>serial id</div>
	 */
	private static final long serialVersionUID = -5783748884477906325L;
	/**
	 * <div>id_user</div> 
	 */
	private String id_user;
	/**
	 * <div>user_name</div> 
	 */
	private String user_name;
	/**
	 * <div>id_div</div> 
	 */
	private String id_div;
	/**
	 * <div>div_name</div> 
	 */
	private String div_name;
	/**
	 * <div>id_dept</div> 
	 */
	private String id_dept;
	/**
	 * <div>dept_name</div> 
	 */
	private String dept_name;
	/**
	 * <div>role_name</div> 
	 */
	private String role_name;

	/**
	 * <div>row</div> 
	 */
	private String row = "0";
	/**
	 * <div>startIndex</div> 
	 */
	private String startIndex ="0";
	/**
	 * <div>totalCount</div> 
	 */
	private String totalCount = null;
	/**
	 * <div>userBeans</div> 
	 */
	private List<UserBean> userBeans=null;
	/**
	 * <div>clickEvent</div> 
	 */
	private String clickEvent = null;
	/**
	 * <div>screen_mod</div> 
	 */
	private String screen_mod;
	/**
	 * <div>password</div> 
	 */
	private String password;
	/**
	 * <div>confirm_password</div> 
	 */
	private String confirm_password;
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
	 * <div>pplsoft_acc_id_hidden</div> 
	 */
	private String pplsoft_acc_id_hidden;

	/**
	 * <div>tel_no</div> 
	 */
	private String tel_no;
	/**
	 * <div>tel_extno</div> 
	 */
	private String tel_extno;
	/**
	 * <div>id_user_s01</div> 
	 */
	private String id_user_s01;
	/**
	 * <div>userAccessBeans</div> 
	 */
	private List<UserAccessBean> userAccessBeans=null;
	/**
	 * <div>userAccessBeans</div> 
	 */
	private HashMap<String, String> mapAccessItems;
	/**
	 * <div>id_user_search</div>
	 */
	private String id_user_search = null;

	/**
	 * <div>user_name_search</div>
	 */
	private String user_name_search = null;

	/**
	 * <div>id_div_search</div>
	 */
	private String id_div_search = null;

	/**
	 * <div>id_dept_search</div>
	 */
	private String id_dept_search = null;
	/**
	 * <div>user_status</div> 
	 */
	private String user_status;
	/**
	 * <div>formFile</div>
	 */
	private FormFile formFile;
	/**
	 * <div>permission</div>
	 */
	private String permission;
	/**
	 * <div>id_user_hidden</div>
	 */	
	private String id_user_hidden;	
	/**
	 * <div>user_name_hidden</div>
	 */
	private String user_name_hidden;
	/**
	 * <div>id_div_hidden</div>
	 */
	private String id_div_hidden;
	/**
	 * <div>id_dept_hidden</div>
	 */
	private String id_dept_hidden;
	/**
	 * <div>user_status_hidden</div>
	 */
	private String user_status_hidden;	
	/**
	 * <div>id_role_hidden</div>
	 */
	private String id_role_hidden;	
	/**
	 * <div>did_no_hidden</div>
	 */
	private String did_no_hidden;	
	/**
	 * <div>ofs_mobile_no_hidden</div>
	 */
	private String ofs_mobile_no_hidden;
	/**
	 * <div>email_hidden</div>
	 */
	private String email_hidden;
	/**
	 * <div>tel_no_hidden</div>
	 */
	private String tel_no_hidden;
	/**
	 * <div>tel_extno_hidden</div>
	 */
	private String tel_extno_hidden;
	/**
	 * <div>password_hidden</div>
	 */
	private String password_hidden;
	/**
	 * <div>confirm_password_hidden</div>
	 */
	private String confirm_password_hidden;
	
	public String getId_user_hidden() {
		return id_user_hidden;
	}
	public void setId_user_hidden(String id_user_hidden) {
		this.id_user_hidden = id_user_hidden;
	}
	public String getUser_name_hidden() {
		return user_name_hidden;
	}
	public void setUser_name_hidden(String user_name_hidden) {
		this.user_name_hidden = user_name_hidden;
	}
	public String getId_div_hidden() {
		return id_div_hidden;
	}
	public void setId_div_hidden(String id_div_hidden) {
		this.id_div_hidden = id_div_hidden;
	}
	public String getId_dept_hidden() {
		return id_dept_hidden;
	}
	public void setId_dept_hidden(String id_dept_hidden) {
		this.id_dept_hidden = id_dept_hidden;
	}
	public String getUser_status_hidden() {
		return user_status_hidden;
	}
	public void setUser_status_hidden(String user_status_hidden) {
		this.user_status_hidden = user_status_hidden;
	}
	public String getId_role_hidden() {
		return id_role_hidden;
	}
	public void setId_role_hidden(String id_role_hidden) {
		this.id_role_hidden = id_role_hidden;
	}
	public String getDid_no_hidden() {
		return did_no_hidden;
	}
	public void setDid_no_hidden(String did_no_hidden) {
		this.did_no_hidden = did_no_hidden;
	}
	public String getOfs_mobile_no_hidden() {
		return ofs_mobile_no_hidden;
	}
	public void setOfs_mobile_no_hidden(String ofs_mobile_no_hidden) {
		this.ofs_mobile_no_hidden = ofs_mobile_no_hidden;
	}
	public String getEmail_hidden() {
		return email_hidden;
	}
	public void setEmail_hidden(String email_hidden) {
		this.email_hidden = email_hidden;
	}
	public String getTel_no_hidden() {
		return tel_no_hidden;
	}
	public void setTel_no_hidden(String tel_no_hidden) {
		this.tel_no_hidden = tel_no_hidden;
	}
	public String getTel_extno_hidden() {
		return tel_extno_hidden;
	}
	public void setTel_extno_hidden(String tel_extno_hidden) {
		this.tel_extno_hidden = tel_extno_hidden;
	}
	public String getPassword_hidden() {
		return password_hidden;
	}
	public void setPassword_hidden(String password_hidden) {
		this.password_hidden = password_hidden;
	}
	public String getConfirm_password_hidden() {
		return confirm_password_hidden;
	}
	public void setConfirm_password_hidden(String confirm_password_hidden) {
		this.confirm_password_hidden = confirm_password_hidden;
	}
	
	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	private byte[] photo;

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public FormFile getFormFile() {
		return formFile;
	}

	public void setFormFile(FormFile formFile) {
		this.formFile = formFile;
	}

	public String getUser_status() {
		return user_status;
	}

	public void setUser_status(String user_status) {
		this.user_status = user_status;
	}

	public String getId_user_search() {
		return id_user_search;
	}

	public void setId_user_search(String id_user_search) {
		this.id_user_search = id_user_search;
	}

	public String getUser_name_search() {
		return user_name_search;
	}

	public void setUser_name_search(String user_name_search) {
		this.user_name_search = user_name_search;
	}

	public String getId_div_search() {
		return id_div_search;
	}

	public void setId_div_search(String id_div_search) {
		this.id_div_search = id_div_search;
	}

	public String getId_dept_search() {
		return id_dept_search;
	}

	public void setId_dept_search(String id_dept_search) {
		this.id_dept_search = id_dept_search;
	}
	
	public HashMap<String, String> getMapAccessItems() {
		return mapAccessItems;
	}

	public void setMapAccessItems(HashMap<String, String> mapAccessItems) {
		this.mapAccessItems = mapAccessItems;
	}

	public List<UserAccessBean> getUserAccessBeans() {
		return userAccessBeans;
	}

	public void setUserAccessBeans(List<UserAccessBean> userAccessBeans) {
		this.userAccessBeans = userAccessBeans;
	}
	
	public String getId_user_s01() {
		return id_user_s01;
	}

	public void setId_user_s01(String id_user_s01) {
		this.id_user_s01 = id_user_s01;
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

	public String getConfirm_password() {
		return confirm_password;
	}

	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
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
	
	public String getScreen_mod() {
		return screen_mod;
	}

	public void setScreen_mod(String screen_mod) {
		this.screen_mod = screen_mod;
	}

	public String getClickEvent() {
		return clickEvent;
	}

	public void setClickEvent(String clickEvent) {
		this.clickEvent = clickEvent;
	}
	
	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public String getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(String startIndex) {
		this.startIndex = startIndex;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	
	public List<UserBean> getUserBeans() {
		if(userBeans == null){
			return new ArrayList<UserBean>();
		}
		return userBeans;
	}

	public void setUserBeans(List<UserBean> userBeans) {
		this.userBeans = userBeans;
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
	public void setDiv_name(String div_name) {
		this.div_name = div_name;
	}
	public String getDiv_name() {
		return div_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getDept_name() {
		return dept_name;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getPplsoft_acc_id() {
		return pplsoft_acc_id;
	}
	public void setPplsoft_acc_id(String pplsoft_acc_id) {
		this.pplsoft_acc_id = pplsoft_acc_id;
	}
	public String getPplsoft_acc_id_hidden() {
		return pplsoft_acc_id_hidden;
	}
	public void setPplsoft_acc_id_hidden(String pplsoft_acc_id_hidden) {
		this.pplsoft_acc_id_hidden = pplsoft_acc_id_hidden;
	}
}