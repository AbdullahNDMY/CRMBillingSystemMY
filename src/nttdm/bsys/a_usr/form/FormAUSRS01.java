package nttdm.bsys.a_usr.form;

import javax.servlet.http.HttpServletRequest;
import jp.terasoluna.fw.web.struts.form.DynaValidatorActionFormEx;
import nttdm.bsys.a_usr.dto.*;

import org.apache.struts.action.ActionMapping;

public class FormAUSRS01 extends DynaValidatorActionFormEx{

	private static final long serialVersionUID = 1L;

	public void reset(ActionMapping mapping, HttpServletRequest request){
		InputSearch inputSearch = new InputSearch();
		inputSearch.setIdDepartment("");
		inputSearch.setIdDivision("");
		inputSearch.setIdUser("");
		inputSearch.setUserName("");
		this.set("inputSearch", inputSearch);		
	}
}
