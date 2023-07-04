package nttdm.bsys.a_usr.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import nttdm.bsys.common.util.dto.*;
import nttdm.bsys.a_usr.dto.*;
import org.apache.struts.action.ActionMapping;

import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;

public class FormAUSRS02 extends ValidatorActionFormEx{
	private static final long serialVersionUID = 1L;
	private String pageEvent;
	private String mode;
	private String idUser;
	private String idRole;
	private String isSaveSuccess;
	private User user = new User();
	private List<UserAccess> listUserAccess = new ArrayList<UserAccess>();

	public void reset(ActionMapping mapping, HttpServletRequest request){
		this.setPageEvent(PageEvent.LOAD);
		this.setIsSaveSuccess("0");
	}
	
	/**
	 * special getter
	 */
	public UserAccess getUserAccess(int index) {
		if(index >= listUserAccess.size()) {
			for(int i = listUserAccess.size(); i <= index; i++) {
				listUserAccess.add(new UserAccess());
			}
		}
		return listUserAccess.get(index);
	}
	
	public String getIdRole() {
        return idRole;
    }

    public void setIdRole(String idRole) {
        this.idRole = idRole;
    }

    /**
	 * default getter, setter
	 */
	public String getPageEvent() {
		return pageEvent;
	}

	public void setPageEvent(String pageEvent) {
		this.pageEvent = pageEvent;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getIsSaveSuccess() {
		return isSaveSuccess;
	}

	public void setIsSaveSuccess(String isSaveSuccess) {
		this.isSaveSuccess = isSaveSuccess;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<UserAccess> getListUserAccess() {
		return listUserAccess;
	}

	public void setListUserAccess(List<UserAccess> listUserAccess) {
		this.listUserAccess = listUserAccess;
	}
}
