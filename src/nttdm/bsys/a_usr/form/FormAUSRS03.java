package nttdm.bsys.a_usr.form;

import javax.servlet.http.HttpServletRequest;

import nttdm.bsys.a_usr.dto.PlanService;
import nttdm.bsys.common.util.dto.*;
import org.apache.struts.action.ActionMapping;

import jp.terasoluna.fw.web.struts.form.DynaValidatorActionFormEx;

public class FormAUSRS03 extends DynaValidatorActionFormEx{
	
	private static final long serialVersionUID = 1L;

	public void reset(ActionMapping mapping, HttpServletRequest request){
		this.set("choosed", "");
		this.set("pageEvent", PageEvent.LOAD);
		this.set("listPlanService",new AutoScaleList<PlanService>(new PlanService()));
	}
}
