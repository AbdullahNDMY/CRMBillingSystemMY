package nttdm.bsys.c_cmn001.blogic;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.util.FileUtil;
import jp.terasoluna.fw.web.struts.actions.AbstractBLogicAction;
import nttdm.bsys.c_cmn001.dto.C_CMN001R01Input;
import nttdm.bsys.c_cmn001.dto.C_CMN001R01Output;
import nttdm.bsys.common.util.ZipUtil;

public class CMN001R01BLogic extends AbstractBLogicAction<C_CMN001R01Input> {
	private BLogic<C_CMN001R01Input> businessLogic;
	private String sessionDirectory = null;

	public BLogic<C_CMN001R01Input> getBusinessLogic() {
		return businessLogic;
	}

	public void setBusinessLogic(BLogic<C_CMN001R01Input> businessLogic) {
		this.businessLogic = businessLogic;
	}

	@Override
	protected void preDoExecuteBLogic(HttpServletRequest request,
			HttpServletResponse response, C_CMN001R01Input params)
			throws Exception {
		super.preDoExecuteBLogic(request, response, params);
		HttpSession session = request.getSession();
		String path = session.getId();
		boolean result = FileUtil.makeSessionDirectory(path);
		if(result) {
			File file = FileUtil.getSessionDirectory(path);
			this.sessionDirectory = file.getAbsolutePath();
		}
	}

	@Override
	public BLogicResult doExecuteBLogic(C_CMN001R01Input param)
			throws Exception {
	    // Save session directory into UVO object
		if(businessLogic instanceof C_CMN001R01BLogic){
			((C_CMN001R01BLogic)businessLogic).setSessionDirectory(sessionDirectory);
		}
		BLogicResult result = businessLogic.execute(param);
		if(this.sessionDirectory != null) {
			try {
				//try get result object and add session directory to output
				C_CMN001R01Output out = (C_CMN001R01Output) result.getResultObject();
				out.setSessionDirectory(this.sessionDirectory);
				//initial value for other objects
				//ZipUtil.SESSION_DIRECTORY = this.sessionDirectory;
				System.out.println("SESSION_DIRECTORY = "+this.sessionDirectory);
				//Exception e1 = new Exception("SESSION_DIRECTORY = "+this.sessionDirectory);
				//e1.printStackTrace();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
