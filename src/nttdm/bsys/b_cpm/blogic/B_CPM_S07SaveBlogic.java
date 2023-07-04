package nttdm.bsys.b_cpm.blogic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessages;

public class B_CPM_S07SaveBlogic implements BLogic<Map<String, Object>> {

	/**
	 * queryDAO
	 */
	private QueryDAO queryDAO;

	/**
	 * updateDAO
	 */
	private UpdateDAO updateDAO;

	public BLogicResult execute(Map<String, Object> input) {
		BLogicResult result = new BLogicResult();
		HashMap<String, Object> output = new HashMap<String, Object>();
		BLogicMessages errors = new BLogicMessages();
		BillingSystemUserValueObject uvo = (BillingSystemUserValueObject)input.get("uvo");
		
		String idSubInfo = input.get("idSubInfo").toString();
        String idCustPlan = input.get("idCustPlan").toString();
        String id_mail = CommonUtils.toString(input.get("ID_MAIL").toString());
        
        Map<String, Object> mapCustomerPlanH = queryDAO.executeForMap("SELECT.B_CPM_S07.GET_CUSTOMER_PLAN_H", idCustPlan);
        String planStatus = "";
        if (mapCustomerPlanH!=null) {
            planStatus = CommonUtils.toString(mapCustomerPlanH.get("PLAN_STATUS"));
        }
        // Audit Trail
        Integer idAudit = CommonUtils.auditTrailBegin(uvo.getId_user(), BillingSystemConstants.MODULE_B,
				BillingSystemConstants.SUB_MODULE_B_CPM, idCustPlan, planStatus,
                BillingSystemConstants.AUDIT_TRAIL_EDITED);

		BigDecimal MAILACC = new BigDecimal(input.get("MAILACC").toString());
		BigDecimal MAILBOX_QTY = new BigDecimal(input.get("MAILBOX_QTY").toString());
		BigDecimal VIRUS_SCAN = new BigDecimal(input.get("VIRUS_SCAN").toString());
		BigDecimal ANTI_SPAM = new BigDecimal(input.get("ANTI_SPAM").toString());
		BigDecimal JUNK_MGT = new BigDecimal(input.get("JUNK_MGT").toString());

		String AUTO_MAIL_ACC = CommonUtils.toString(input.get("AUTO_MAIL_ACC"));
		String AUTO_MAILBOX_QTY = CommonUtils.toString(input.get("AUTO_MAILBOX_QTY"));
		String AUTO_VIRUS_SCAN = CommonUtils.toString(input.get("AUTO_VIRUS_SCAN"));
		String AUTO_ANTI_SPAM = CommonUtils.toString(input.get("AUTO_ANTI_SPAM"));
		String AUTO_JUNK_MGT = CommonUtils.toString(input.get("AUTO_JUNK_MGT"));

		String OPT_BASE_QTY = CommonUtils.toString(input.get("OPT_BASE_QTY"));
		if(!OPT_BASE_QTY.trim().equals("")){
			try {
				new BigDecimal(OPT_BASE_QTY);
			} catch (Exception e) {
				errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC088",new Object[]{"Mail Account Base Qty"}));
				output.putAll(input);
				result.setResultObject(output);
				result.setErrors(errors);
				result.setResultString("failure");
				return result;
			}
		}else{
			OPT_BASE_QTY="0";
		}
		String OPT_ADDT_OPTION = CommonUtils.toString(input.get("OPT_ADDT_OPTION"));
		String OPT_MAILBOX_QTY = CommonUtils.toString(input.get("OPT_MAILBOX_QTY"));
		String OPT_VIRUS_SCAN = CommonUtils.toString(input.get("OPT_VIRUS_SCAN"));
		String OPT_ANTI_SPAM = CommonUtils.toString(input.get("OPT_ANTI_SPAM"));
		String OPT_JUNK_MGT = CommonUtils.toString(input.get("OPT_JUNK_MGT"));
		
		boolean isError = false;
		//Mail Account Check Box checked
		if ("1".equals(AUTO_MAIL_ACC)) {
		    if (CommonUtils.isEmpty(OPT_ADDT_OPTION)) {
		        isError = true;
		        errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC038",new Object[]{"Additional"}));
		    }
		}
		//Mail Box(Qty) Check Box checked
		if ("1".equals(AUTO_MAILBOX_QTY)) {
            if (CommonUtils.isEmpty(OPT_MAILBOX_QTY)) {
                isError = true;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC038",new Object[]{"Mail Box(Qty)"}));
            }
        }
		//Virus Scan Check Box checked
		if ("1".equals(AUTO_VIRUS_SCAN)) {
            if (CommonUtils.isEmpty(OPT_VIRUS_SCAN)) {
                isError = true;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC038",new Object[]{"Virus Scan"}));
            }
        }
		//Anti Spam Check Box checked
        if ("1".equals(AUTO_ANTI_SPAM)) {
            if (CommonUtils.isEmpty(OPT_ANTI_SPAM)) {
                isError = true;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC038",new Object[]{"Anti Spam"}));
            }
        }
        //Junk Mgmt Check Box checked
        if ("1".equals(AUTO_JUNK_MGT)) {
            if (CommonUtils.isEmpty(OPT_JUNK_MGT)) {
                isError = true;
                errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC038",new Object[]{"Junk Mgmt"}));
            }
        }
        if(isError){
            output.putAll(input);
            result.setResultObject(output);
            result.setErrors(errors);
            result.setResultString("failure");
            return result;
        }
		
		// to check repeat check
		ArrayList<String> optlist = new ArrayList<String>();
		if(!OPT_ADDT_OPTION.equals("")){
			optlist.add(OPT_ADDT_OPTION);
		}
		if(!OPT_MAILBOX_QTY.equals("")){
			optlist.add(OPT_MAILBOX_QTY);
		}
		if(!OPT_VIRUS_SCAN.equals("")){
			optlist.add(OPT_VIRUS_SCAN);
		}
		if(!OPT_ANTI_SPAM.equals("")){
			optlist.add(OPT_ANTI_SPAM);
		}
		if(!OPT_JUNK_MGT.equals("")){
			optlist.add(OPT_JUNK_MGT);
		}
		
		isError = false;
		for(int i=0;i<optlist.size();i++){
			String item = optlist.get(i);
			int num=0;
			for(int j = 0;j<optlist.size() ;j++){
				if(optlist.get(j).equals(item)){
					num++;
				}
			}
			if(num>1){
				isError = true;
				break;
			}
		}
		
		if(isError){
			errors.add(Globals.ERROR_KEY, new BLogicMessage("errors.ERR1SC022",new Object[]{"only one radio button","each service"}));
			output.putAll(input);
			result.setResultObject(output);
			result.setErrors(errors);
			result.setResultString("failure");
			return result;
		}
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("idSubInfo", idSubInfo);
		param.put("logonUserID", uvo.getId_user());
		param.put("idAudit", idAudit);
		param.put("id_mail", id_mail);
		// A.OPT_ADDT_OPTION = %rdb<Additional>%
		param.put("OPT_ADDT_OPTION", OPT_ADDT_OPTION);
		// A.OPT_MAILBOX_QTY = %rdb<MailBoxQty>%
		param.put("OPT_MAILBOX_QTY", OPT_MAILBOX_QTY);
		// A.OPT_VIRUS_SCAN = %rdb<VirusScan>%
		param.put("OPT_VIRUS_SCAN", OPT_VIRUS_SCAN);
		// A.OPT_ANTI_SPAM = %rdb<AntiSpam>%
		param.put("OPT_ANTI_SPAM", OPT_ANTI_SPAM);
		// A.OPT_JUNK_MGT = %rdb<JunkMgmt>%
		param.put("OPT_JUNK_MGT", OPT_JUNK_MGT);

		// A.OPT_BASE_QTY = %tbxMailAccountB%
		param.put("OPT_BASE_QTY", OPT_BASE_QTY);

		// A.AUTO_MAIL_ACC = %chk(checkbox)MailAccount%
		param.put("AUTO_MAIL_ACC", AUTO_MAIL_ACC.equals("1") ? "1" : "0");
		// A.AUTO_MAILBOX_QTY = %chk(checkbox)MailQty%
		param.put("AUTO_MAILBOX_QTY", AUTO_MAILBOX_QTY.equals("1") ? "1" : "0");
		// A.AUTO_VIRUS_SCAN = %chk(checkbox)VirusScan%
		param.put("AUTO_VIRUS_SCAN", AUTO_VIRUS_SCAN.equals("1") ? "1" : "0");
		// A.AUTO_ANTI_SPAM = %chk(checkbox)AntiSpam%
		param.put("AUTO_ANTI_SPAM", AUTO_ANTI_SPAM.equals("1") ? "1" : "0");
		// A.AUTO_JUNK_MGT = %chk(checkbox)JunkMgmt%
		param.put("AUTO_JUNK_MGT", AUTO_JUNK_MGT.equals("1") ? "1" : "0");
		
		if(id_mail.trim().equals("")){
		    updateDAO.execute("INSERT.B_CPM_S07.INSERT.T_MAIL_ADDRESS", param);
		}else{
			updateDAO.execute("UPDATE.B_CPM_S07.UPDATE.T_MAIL_ADDRESS", param);
		}
		
		if(!OPT_ADDT_OPTION.equals("")){
			BigDecimal totalAddtMail = BigDecimal.ZERO;
			totalAddtMail = MAILACC.subtract(new BigDecimal(OPT_BASE_QTY));
			if(totalAddtMail.compareTo(BigDecimal.ZERO)<0){
			    totalAddtMail = BigDecimal.ZERO;
			}
			HashMap<String, Object> updateparam = new HashMap<String, Object>();
            updateparam.put("quantity", totalAddtMail);
            updateparam.put("id_cust_plan_link", OPT_ADDT_OPTION);
            updateparam.put("logonUserID", uvo.getId_user());
            updateparam.put("idAudit", idAudit);
            updateDAO.execute("UPDATE.B_CPM_S07.UPDATET_CUST_PLAN_LINK", updateparam);
		}
		if(!OPT_MAILBOX_QTY.equals("")){
			HashMap<String, Object> updateparam = new HashMap<String, Object>();
			updateparam.put("quantity", MAILBOX_QTY);
			updateparam.put("id_cust_plan_link", OPT_MAILBOX_QTY);
			updateparam.put("logonUserID", uvo.getId_user());
			updateparam.put("idAudit", idAudit);
			updateDAO.execute("UPDATE.B_CPM_S07.UPDATET_CUST_PLAN_LINK", updateparam);
		}
		if(!OPT_VIRUS_SCAN.equals("")){
			HashMap<String, Object> updateparam = new HashMap<String, Object>();
			updateparam.put("quantity", VIRUS_SCAN);
			updateparam.put("id_cust_plan_link", OPT_VIRUS_SCAN);
			updateparam.put("logonUserID", uvo.getId_user());
			updateparam.put("idAudit", idAudit);
			updateDAO.execute("UPDATE.B_CPM_S07.UPDATET_CUST_PLAN_LINK", updateparam);
		}
		if(!OPT_ANTI_SPAM.equals("")){
			HashMap<String, Object> updateparam = new HashMap<String, Object>();
			updateparam.put("quantity", ANTI_SPAM);
			updateparam.put("id_cust_plan_link", OPT_ANTI_SPAM);
			updateparam.put("logonUserID", uvo.getId_user());
			updateparam.put("idAudit", idAudit);
			updateDAO.execute("UPDATE.B_CPM_S07.UPDATET_CUST_PLAN_LINK", updateparam);
		}
		if(!OPT_JUNK_MGT.equals("")){
			HashMap<String, Object> updateparam = new HashMap<String, Object>();
			updateparam.put("quantity", JUNK_MGT);
			updateparam.put("id_cust_plan_link", OPT_JUNK_MGT);
			updateparam.put("logonUserID", uvo.getId_user());
			updateparam.put("idAudit", idAudit);
			updateDAO.execute("UPDATE.B_CPM_S07.UPDATET_CUST_PLAN_LINK", updateparam);
		}

		CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
		
		output.put("idSubInfo", idSubInfo);
		output.put("idCustPlan", idCustPlan);
		BLogicMessages msgs = new BLogicMessages();
        BLogicMessage msg = new BLogicMessage("info.ERR2SC003", 
               new Object[] {});
        msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
        result.setMessages(msgs);
		result.setResultObject(output);
		result.setErrors(errors);
		result.setResultString("success");
		return result;
	}

	/**
	 * queryDAO を取得すめE	 * 
	 * @return queryDAO
	 */
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	/**
	 * queryDAO を設定すめE	 * 
	 * @param queryDAO
	 *            queryDAO
	 */
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	/**
	 * updateDAO を取得すめE	 * 
	 * @return updateDAO
	 */
	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	/**
	 * updateDAO を設定すめE	 * 
	 * @param updateDAO
	 *            updateDAO
	 */
	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}
}
