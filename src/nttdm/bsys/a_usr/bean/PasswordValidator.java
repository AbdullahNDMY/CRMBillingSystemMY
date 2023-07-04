package nttdm.bsys.a_usr.bean;

import jp.terasoluna.fw.web.struts.form.MultiFieldValidator;
/**
 * <div>
 * Compare password and re-enter password
 * </div>
 * @author Phu.Nguyen
 *
 */
public class PasswordValidator implements MultiFieldValidator{


	/**
	 * Override validate method 
	 * @param password
	 * @param fields
	 */
	public boolean validate(String password, String[] fields) {
		String repassword = fields[0];
		//Return true if password or re-enter password is null
		if (password == null || "".equals(password) || repassword == null || "".equals(repassword)) {
            return true;
        }	
		//Compare password and re-enter password
		return comparePassword(password,repassword);
	}
	/**
	 * Compare password and re-enter password
	 * @param password
	 * @param repassword
	 * @return boolean
	 */
	public boolean comparePassword(String password, String repassword){
		if(password.equals(repassword)){
			return true;
		}
		return false;
	}

}
