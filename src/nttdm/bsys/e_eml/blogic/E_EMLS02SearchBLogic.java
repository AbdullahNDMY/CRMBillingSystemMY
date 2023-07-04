package nttdm.bsys.e_eml.blogic;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessages;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.e_eml.bean.E_EML01Bean;
import nttdm.bsys.e_eml.dto.E_EMLInput;
import nttdm.bsys.e_eml.dto.E_EMLOutput;

public class E_EMLS02SearchBLogic implements BLogic<E_EMLInput> {
	private QueryDAO queryDAO;

	public BLogicResult execute(E_EMLInput input) {
		BLogicResult result = new BLogicResult();
		BLogicMessages errors = new BLogicMessages();
		try{
			E_EMLOutput out = new E_EMLOutput();
			// get start index for paging
			Integer startIndex = input.getStartIndex();
			// get the number of row for paging
			BillingSystemSettings bss = new BillingSystemSettings(queryDAO);
			int row = bss.getResultRow();
			List<E_EML01Bean> billInfo = new ArrayList<E_EML01Bean>();
			Date docuFromDate = CommonUtils.parseToDate(input.getStart_docuDate(), "dd/MM/yyyy");
			Date docuToDate = CommonUtils.parseToDate(input.getEnd_docuDate(), "dd/MM/yyyy");
			Date emailFromDate = CommonUtils.parseToDate(input.getStart_emailDate(), "dd/MM/yyyy");
			Date emailToDate = CommonUtils.parseToDate(input.getEnd_emailDate(), "dd/MM/yyyy");
			if (input.getBatchId() != null) {
				input.setBatchId(input.getBatchId().trim());
			}
			if (input.getBillAcountNo() != null) {
				input.setBillAcountNo(input.getBillAcountNo().trim());
			}
			if (input.getBillDocuNo() != null) {
				input.setBillDocuNo(input.getBillDocuNo().trim());
			}
			if (input.getCustomerName() != null) {
				input.setCustomerName(input.getCustomerName().trim());
			}
			if (input.getIdCust() != null) {
				input.setIdCust(input.getIdCust().trim());
			}
			if (input.getEnd_docuDate() != null) {
				input.setEnd_docuDate(input.getEnd_docuDate().trim());
			}
			if (input.getEnd_emailDate() != null) {
				input.setEnd_emailDate(input.getEnd_emailDate().trim());
			}
			if (input.getTransaction() != null) {
				input.setTransaction(input.getTransaction().trim());
			}
			if (input.getStart_docuDate() != null) {
				input.setStart_docuDate(input.getStart_docuDate().trim());
			}
			if (input.getStart_emailDate() != null) {
				input.setStart_emailDate(input.getStart_emailDate().trim());
			}
			if (input.getPdfGen() != null) {
				input.setPdfCheckSize(input.getPdfGen().length);
			}
			if (input.getEmailSend() != null) {
				input.setEmailCheckSize(input.getEmailSend().length);
			}
			if (input.getSameEmail() != null) {
				input.setSameEmailCheckSize(input.getSameEmail().length);
			}
			if (input.getPeopleAccNo() != null) {
				input.setPeopleCheckSize(input.getPeopleAccNo().length);
			}
			boolean allowSearch = true;
			if(docuFromDate != null && docuToDate != null )
				if(docuToDate.before(docuFromDate)) {
					allowSearch = false;
					errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC037", new Object[] {"Document Date", "Document Date From", "Document Date To"}));
				}
			if(emailFromDate != null && emailToDate != null )
				if(emailToDate.before(emailFromDate)) {
					allowSearch = false;
					errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage("errors.ERR1SC037", new Object[] {"Email Sent Date", "Email Sent Date From", "Email Sent Date To"}));
				}
			if (input.getPdfGen() != null) {
				input.setPdfCheckSize(input.getPdfGen().length);
			} else {
				input.setPdfCheckSize(0);
			}
			if (input.getEmailSend() != null) {
				input.setEmailCheckSize(input.getEmailSend().length);
			} else {
				input.setEmailCheckSize(0);
			}
			if (input.getSameEmail() != null) {
				input.setSameEmailCheckSize(input.getSameEmail().length);
			} else {
				input.setSameEmailCheckSize(0);
			}
			if (input.getPeopleAccNo() != null) {
				input.setPeopleCheckSize(input.getPeopleAccNo().length);
			} else {
				input.setPeopleCheckSize(0);
			}

			String count = "";
			if(allowSearch){
				//count
				count = queryDAO.executeForObject("SELECT.E_EML.SQL002", input, String.class);
				if(Integer.parseInt(count) > 0){
					billInfo = queryDAO.executeForObjectList("SELECT.E_EML.SQL001", input,startIndex,row);
				}else{
					 // info.ERR2SC002
		            BLogicMessages msgs = new BLogicMessages();
		            BLogicMessage msg = new BLogicMessage("info.ERR2SC002", new Object());
		            msgs.add(ActionMessages.GLOBAL_MESSAGE, msg);
		            result.setMessages(msgs);
				}
				out.setBillInfo(billInfo);
			}
			//when count is null
			if (count == null || "".equals(count)) {
				//set count=0
				count = "0";
			}
			
			try {
				BeanUtils.copyProperties(out, input);
			} catch (Exception e) {}
			String[] idRefs = input.getIdRefs();
			if (idRefs != null && idRefs.length > 0) {
				for (int i = 0; i < idRefs.length; i++) {
					E_EMLInput tmpInput = new E_EMLInput();
					BeanUtils.copyProperties(tmpInput, input);
					tmpInput.setBillDocuNo(idRefs[i]);
					String idRefCount = queryDAO.executeForObject(
							"SELECT.E_EML.SQL002", tmpInput, String.class);
					if (Integer.parseInt(idRefCount) == 0) {
						idRefs[i] = "";
					}
				}
			}
			out.setIdRefs(idRefs);
			out.setTotalCount(Integer.parseInt(count));
			out.setStartIndex(startIndex);
			out.setRow(row);
			result.setResultObject(out);
			//return message
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_SUCCESS);	
			result.setErrors(errors);
			return result;
		}catch(Exception ex){
			errors.add(Globals.MESSAGE_KEY,new BLogicMessage("info.ERR2SC004"));
			result.setErrors(errors);
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
			return result;
		}
		
	}

	/**
	 * @return the queryDAO
	 */
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	/**
	 * @param queryDAO
	 *            the queryDAO to set
	 */
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

}
