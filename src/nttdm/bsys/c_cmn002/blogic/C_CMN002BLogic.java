package nttdm.bsys.c_cmn002.blogic;
import java.util.ArrayList;
import java.util.List; 
import org.apache.struts.Globals; 
import nttdm.bsys.a_usr.bean.UserBean;
import nttdm.bsys.c_cmn002.bean.MessageBean;
import nttdm.bsys.c_cmn002.dto.C_CMN002Input;
import nttdm.bsys.c_cmn002.dto.C_CMN002Output;
import nttdm.bsys.common.util.BillingSystemConstants; 
import nttdm.bsys.common.util.BillingSystemSettings; 
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import nttdm.bsys.common.util.comman_source;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.G_CMN_P01;
import nttdm.bsys.c_cmn001.bean.UserAccess;
public class C_CMN002BLogic extends AbstractC_CMN002BLogic{
	/**
	 * <div>QueryDAO</div>
	 */
	private QueryDAO queryDAO = null; 
	protected UpdateDAO updateDAO = null;
	/**
	 * <div>SAVE_ERROR_MSG</div>
	 */
	static final String SAVE_ERROR_MSG = "info.ERR2SC004";  
	
	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}

	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	/**
	 * <div>Main function</div>
	 */
	
	public BLogicResult execute(C_CMN002Input param) {	
		BLogicMessages errors = new BLogicMessages();
		BLogicResult result = new BLogicResult();
		
		
		
		try{			
			// TODO Auto-generated method stub 
			//Declare output array object
			C_CMN002Output outputDTO = new C_CMN002Output();
			//call process
			G_CMN_P01 cmn = new G_CMN_P01(queryDAO);
			if(param.getUvo().getUser_status().equals("-1"))
			{
				result.setResultString("login");
				return result;
				
				
			}
				
			
			String acctype="";
			for(int i =0;i<param.getUvo().getUser_access().size();i++)
			{
				UserAccess usac= new UserAccess();
				usac=param.getUvo().getUser_access().get(i);
				if(usac.getId_sub_module().equals("M-ALT"))
					acctype=usac.getAccess_type();
				
			}
			
			//get the result
			outputDTO = cmn.execute(param); 
			outputDTO.setAccess_type(acctype);
			//return object
			result.setResultObject(outputDTO);
			//return message
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);
			
			return result; 
		}catch(Exception ex){
			errors.add(Globals.MESSAGE_KEY,new BLogicMessage(SAVE_ERROR_MSG));
			result.setErrors(errors);
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
			return result;
		}
	} 
}
