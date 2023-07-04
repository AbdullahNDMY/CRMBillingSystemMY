package nttdm.bsys.bif.blogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessages;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.bif.bean.B_BIFBean;
import nttdm.bsys.bif.dto.B_BIFInput;
import nttdm.bsys.bif.dto.B_BIFOutput;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;

public class B_BIFS01BLogic extends AbstractB_BIFBLogic{
	private QueryDAO queryDAO = null;
	private static final String SELECT_BIF = "SELECT.B_BIF.SQL001";
	private static final String SELECT_COUNT_ROW = "SELECT.B_BIF.SQL002";
	private static final String SELECT_APPROVAL_NAME = "SELECT.B_BIF.SQL004";
	static final String SAVE_ERROR_MSG = "info.ERR2SC004";
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}
	
	@SuppressWarnings("unchecked")
	public BLogicResult execute(B_BIFInput arg0) {
		// TODO Auto-generated method stub
		
		BLogicMessages errors = new BLogicMessages();
		BLogicResult result = new BLogicResult();
		try{ 
			//get start index for paging
			Integer startIndex = arg0.getStartIndex();
			//get the number of row for paging 
			BillingSystemSettings bss = new BillingSystemSettings(queryDAO);
			int row = bss.getResultRow();
			List <B_BIFBean> arr_bif= new ArrayList<B_BIFBean>();
			String subscription_id = arg0.getSubscription_id();
			String job_no = arg0.getJob_no();
			String prepared_by = arg0.getPrepared_by();
			String billing_account = arg0.getBilling_account();
			//trim value for customer name and consultant name
			if (arg0.getCust_name() != null) {
				arg0.setCust_name(arg0.getCust_name().trim());
			}
			
			if (arg0.getUser_name() != null) {
				arg0.setUser_name(arg0.getUser_name().trim());
			}
			
			if (arg0.getIdRef() != null) {
				arg0.setIdRef(arg0.getIdRef().trim());
			}
			
			if (arg0.getId_qcs() != null) {
				arg0.setId_qcs(arg0.getId_qcs().trim());
			}
			if (arg0.getSubscription_id() != null) {
				arg0.setSubscription_id(CommonUtils.escapeSQL(subscription_id));
			}

			if (arg0.getJob_no() != null) {
				arg0.setJob_no(CommonUtils.escapeSQL(job_no));
			}
			if (arg0.getBilling_account() != null) {
				arg0.setBilling_account(CommonUtils.escapeSQL(billing_account));
			}
			if (arg0.getPrepared_by() != null) {
				arg0.setPrepared_by(CommonUtils.escapeSQL(prepared_by));
			}
			
			arr_bif = queryDAO.executeForObjectList(SELECT_BIF, arg0,startIndex,row);
			if (arr_bif!=null && 0<arr_bif.size()) {
			    for(B_BIFBean bifBean : arr_bif) {
			        String idApprovalName = queryDAO.executeForObject(SELECT_APPROVAL_NAME, bifBean.getId_ref(), String.class);
			        bifBean.setId_approver_name(idApprovalName);
			    }
			}
			List<String> idref = new ArrayList<String>();
			HashMap<String, Object> id_ref = new HashMap<String, Object>();
			if (arr_bif!=null && 0<arr_bif.size()) {
				for(int i = 0; i < arr_bif.size(); i++) {
					idref.add(arr_bif.get(i).getId_ref());
			    }
				id_ref.put("id_ref", idref);
				id_ref.put("billing_account", arg0.getBilling_account());
			}
			List<HashMap<String, Object>> billacount = queryDAO.executeForObjectList("SELECT.ID_BILL_ACCOUNT", id_ref);
			HashMap<String,List<String>> billacountReturn = billAccount(billacount);
			for(int i = 0; i < arr_bif.size(); i++) {
				arr_bif.get(i).setBilling_account((List<String>) billacountReturn.get(arr_bif.get(i).getId_ref()));
			}
			B_BIFOutput out = new B_BIFOutput();
			out.setArr_bif(arr_bif);
			out.setStartIndex(startIndex);
			out.setTotalCount(getTotalCount(arg0));
			if(getTotalCount(arg0) == 0){
			    // info.ERR2SC002
	            BLogicMessages msgs = new BLogicMessages();
	            BLogicMessage msg = new BLogicMessage("info.ERR2SC002", new Object());
	            msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
	            result.setMessages(msgs);
			}
			//return object
			result.setResultObject(out);
			
			out.setStart_date(arg0.getStart_date());
			out.setEnd_date(arg0.getEnd_date());
			out.setId_qcs(arg0.getId_qcs());
			out.setId_ref(arg0.getId_ref());
			out.setCust_name(arg0.getCust_name());
			out.setUser_name(arg0.getUser_name());
			out.setBif_type(arg0.getBif_type());
			out.setStatus(arg0.getStatus());
			out.setRow(row);
			out.setSubscription_id(subscription_id);
			out.setJob_no(job_no);
			out.setBilling_account(billing_account);
			out.setPrepared_by(prepared_by);
			//return message
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
	
	private Integer getTotalCount(B_BIFInput arg0){
		//count total items from searching result 
		String count = queryDAO.executeForObject(SELECT_COUNT_ROW, arg0, String.class);
		//when count is null
		if (count == null || "".equals(count)) {
			//set count=0
			count = "0";
		}
		//return
		return Integer.parseInt(count);
	}
	
	private HashMap<String,List<String>> billAccount(
	        List<HashMap<String, Object>> listBillAccount) {

		HashMap<String,List<String>> billMap = new HashMap<String,List<String>>();
		
		for (HashMap<String,Object> b : listBillAccount)
		{
			String key = (String)b.get("ID_REF");
			if (!billMap.containsKey(key)) {
				List<String> value = new ArrayList<String>();
				value.add((String)b.get("ID_BILL_ACCOUNT"));
				billMap.put(key, value);
			} else {
				List<String> billvalue = billMap.get(key);
				billvalue.add((String)b.get("ID_BILL_ACCOUNT"));
			}
			
		}
		return billMap;
	}
}
