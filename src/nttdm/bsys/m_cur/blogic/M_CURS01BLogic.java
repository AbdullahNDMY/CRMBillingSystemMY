package nttdm.bsys.m_cur.blogic;

import java.util.ArrayList;
import java.util.List;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_cur.bean.M_CURBean;
import nttdm.bsys.m_cur.dto.M_CURInput;
import nttdm.bsys.m_cur.dto.M_CUROutput;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessages;

public class M_CURS01BLogic extends AbstractM_CURBLogic{
	private QueryDAO queryDAO = null;
	private static final String SELECT_CurrencySQL = "SELECT.M_CUR.SQL001";
	/**
	 * <div>SAVE_ERROR_MSG</div>
	 */
	static final String SAVE_ERROR_MSG = "info.ERR2SC004";
	
	/**
	 * <div>SQL_SEARCH_CUSTOMER</div>
	 */
	private static final String SELECT_TotalCount = "SELECT.M_CUR.SQL003";
	
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}


	public BLogicResult execute(M_CURInput arg0) {
		BLogicMessages errors = new BLogicMessages();
		BLogicResult result = new BLogicResult();		
		try{ 
		    String clickFlg = CommonUtils.toString(arg0.getClickFlg());
		    M_CUROutput out = new M_CUROutput();
		    if("search".equals(clickFlg)) {
		        //get start index for paging
	            Integer startIndex = arg0.getStartIndex();
	            //get the number of row for paging 
	            BillingSystemSettings bss = new BillingSystemSettings(queryDAO);
	            int row = bss.getResultRow();
	            //Integer row = arg0.getRow();
	            //declare blogic result
	            List <M_CURBean> arr_m_cur = new ArrayList<M_CURBean>();
	            arr_m_cur = queryDAO.executeForObjectList(SELECT_CurrencySQL, arg0,startIndex,row);
	            
	            //reindex the list
	            for(int i = 0; i < arr_m_cur.size(); i++) {
	                arr_m_cur.get(i).setIndex(""+(startIndex + i+1));
	            }
	            //
	            
	            out.setArr_m_cur(arr_m_cur);
	            out.setTotalCount(getTotalCount(arg0));
	            
	            out.setDatefrom(arg0.getDatefrom());
	            out.setDateto(arg0.getDateto());
	            out.setCur_code_search(arg0.getCur_code_search());
	            out.setRow(row);
	            out.setStartIndex(arg0.getStartIndex());
	            out.setUnsearch("1");
	            
	            if(out.getTotalCount()==0){
	                // info.ERR2SC002
	                BLogicMessages msgs = new BLogicMessages();
	                BLogicMessage msg = new BLogicMessage("info.ERR2SC002", new Object());
	                msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
	                result.setMessages(msgs);
	            }
		    } else {
		        out.setTotalCount(0);
		        out.setStartIndex(0);
		        out.setArr_m_cur(new ArrayList<M_CURBean>());
		        out.setUnsearch("1");
		        out.setClickFlg("");
		    }
			
			//return object
			result.setResultObject(out);
			//return message
			//result.setResultString("success");
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);		
			return result;  
		}
		catch(Exception ex){
			errors.add(Globals.MESSAGE_KEY,new BLogicMessage(SAVE_ERROR_MSG));
			result.setErrors(errors);
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
			return result;
		} 
	}
	private Integer getTotalCount(M_CURInput arg0){
		//count total items from searching result 
		String count = queryDAO.executeForObject(SELECT_TotalCount, arg0, String.class);
		//when count is null
		if (count == null || "".equals(count)) {
			//set count=0
			count = "0";
		}
		//return
		return Integer.parseInt(count);
	}
}
