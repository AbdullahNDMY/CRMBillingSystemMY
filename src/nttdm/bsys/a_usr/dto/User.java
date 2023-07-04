package nttdm.bsys.a_usr.dto;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import nttdm.bsys.common.util.CommonUtils;

public class User {
	private String no;
	private String idUser;
	private String userName;
	private String idDivision;
	private String idDepartment;
	private String idRole;
	private String telNo;
	private String telExtNo;
	private String didNo;
	private String officeMobileNo;
	private String email;
	private String idPeopleSoftAcc;
	private String idPplSoftDept;
	private String userStatus;
	private String password;
	private String reEnterPassword;
	private String idLogin;
	private String photoPath;
	private byte[] photoByte = null;
	private Integer idAudit = null;
	private String accessRight;
	private List<User> dispEmail;
	
	public String getAccessRight() {
        return accessRight;
    }
    public void setAccessRight(String accessRight) {
        this.accessRight = accessRight;
    }
    public Integer getIdAudit() {
		return idAudit;
	}
	public void setIdAudit(Integer idAudit) {
		this.idAudit = idAudit;
	}
	public byte[] getPhotoByte() {
		return photoByte;
	}
	public void setPhotoByte(byte[] photoByte) {
		this.photoByte = photoByte;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public String getIdDivision() {
		return idDivision;
	}
	public void setIdDivision(String idDivision) {
		this.idDivision = idDivision;
	}
	public String getIdDepartment() {
		return idDepartment;
	}
	public void setIdDepartment(String idDepartment) {
		this.idDepartment = idDepartment;
	}
	public String getIdRole() {
		return idRole;
	}
	public void setIdRole(String idRole) {
		this.idRole = idRole;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public String getTelExtNo() {
		return telExtNo;
	}
	public void setTelExtNo(String telExtNo) {
		this.telExtNo = telExtNo;
	}
	public String getDidNo() {
		return didNo;
	}
	public void setDidNo(String didNo) {
		this.didNo = didNo;
	}
	public String getOfficeMobileNo() {
		return officeMobileNo;
	}
	public void setOfficeMobileNo(String officeMobileNo) {
		this.officeMobileNo = officeMobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIdPeopleSoftAcc() {
		return idPeopleSoftAcc;
	}
	public void setIdPeopleSoftAcc(String idPeopleSoftAcc) {
		this.idPeopleSoftAcc = idPeopleSoftAcc;
	}
	public String getIdPplSoftDept() {
        return idPplSoftDept;
    }
    public void setIdPplSoftDept(String idPplSoftDept) {
        this.idPplSoftDept = idPplSoftDept;
    }
    public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getReEnterPassword() {
		return reEnterPassword;
	}
	public void setReEnterPassword(String reEnterPassword) {
		this.reEnterPassword = reEnterPassword;
	}
	public String getIdLogin() {
		return idLogin;
	}
	public void setIdLogin(String idLogin) {
		this.idLogin = idLogin;
	}

	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
		if (!CommonUtils.isEmpty(photoPath)) {
    		try {
    			FileInputStream fis = new FileInputStream(photoPath);
    			byte[] data = new byte[fis.available()];
    			fis.read(data);
    			this.photoByte = data;
    			fis.close();
    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
		}
	}

	public void setDispEmail(List<User> dispEmail) {
		this.dispEmail = dispEmail;
	}
	public List<User> getDispEmail() {
		return dispEmail;
	}
}
