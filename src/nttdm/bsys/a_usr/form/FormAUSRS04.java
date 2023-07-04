package nttdm.bsys.a_usr.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import nttdm.bsys.a_usr.dto.Role;
import nttdm.bsys.a_usr.dto.RoleAccess;
import nttdm.bsys.common.util.dto.*;

import org.apache.struts.action.ActionMapping;

import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;

public class FormAUSRS04 extends ValidatorActionFormEx{
		private static final long serialVersionUID = 1L;
		private String pageEvent;
		private String mode;
		private String choosed;
		private String isSaveSuccess;
		private List<Role> listRole = new ArrayList<Role>();
		
		private List<RoleAccess> listRoleAccess = new ArrayList<RoleAccess>();

		public void reset(ActionMapping mapping, HttpServletRequest request){
			this.setPageEvent(PageEvent.LOAD);
			this.setIsSaveSuccess("0");			
		}
		
		/**
		 * special getter
		 */
		public RoleAccess getRoleAccess(int index) {
			if(index >= listRoleAccess.size()) {
				for(int i = listRoleAccess.size(); i <= index; i++) {
					listRoleAccess.add(new RoleAccess());
				}
			}
			return listRoleAccess.get(index);
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

		public String getIsSaveSuccess() {
			return isSaveSuccess;
		}

		public void setIsSaveSuccess(String isSaveSuccess) {
			this.isSaveSuccess = isSaveSuccess;
		}

		/**
		 * @return the choosed
		 */
		public String getChoosed() {
			return choosed;
		}

		/**
		 * @param choosed the choosed to set
		 */
		public void setChoosed(String choosed) {
			this.choosed = choosed;
		}

		public List<RoleAccess> getListRoleAccess() {
			return listRoleAccess;
		}

		public void setListRoleAccess(List<RoleAccess> listRoleAccess) {
			this.listRoleAccess = listRoleAccess;
		}

		public List<Role> getListRole() {
			return listRole;
		}

		public void setListRole(List<Role> listRole) {
			this.listRole = listRole;
		}
	
}
