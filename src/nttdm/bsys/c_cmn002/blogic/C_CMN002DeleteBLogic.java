package nttdm.bsys.c_cmn002.blogic;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.Globals;

import nttdm.bsys.c_cmn002.bean.MessageBean;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_CMN_P02;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;

public class C_CMN002DeleteBLogic implements BLogic<MessageBean>{ 
	private QueryDAO queryDAO = null;
	private UpdateDAO updateDAO = null;
	/**
	 * <div>SAVE_ERROR_MSG</div>
	 */
	static final String SAVE_ERROR_MSG = "info.ERR2SC004";
	
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}
	public void setUpdateDAO(UpdateDAO updateDAO) {
        this.updateDAO = updateDAO;
    }
	public BLogicResult execute(MessageBean params) {	
		BLogicMessages errors = new BLogicMessages();
		BLogicResult result = new BLogicResult();
		try{
			//call process
			G_CMN_P02 cmn = new G_CMN_P02(queryDAO, updateDAO);
			String mgsId=CommonUtils.toString(params.getId_msg());
			String tempStrs[]=mgsId.split("-");
			params.setIdMsgList(tempStrs);
			//execute process
			cmn.execute(params);
			//return result
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
