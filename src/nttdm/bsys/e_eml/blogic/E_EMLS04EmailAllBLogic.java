package nttdm.bsys.e_eml.blogic;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.dao.UpdateDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.thin.AuthenticationController;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.G_EML_P02;
import nttdm.bsys.common.util.GlobalProcessResult;
import nttdm.bsys.e_eml.dto.E_EML03Input;
import nttdm.bsys.e_eml.dto.E_EMLInput;
import nttdm.bsys.e_eml.dto.E_EMLOutput;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionErrors;

public class E_EMLS04EmailAllBLogic implements BLogic<E_EMLInput>{
	
	/**
	 * queryDAO
	 */
	protected QueryDAO queryDAO;

	/**
	 * updateDAO
	 */
	protected UpdateDAO updateDAO;

	/**
	 * authenticationController
	 */
	protected AuthenticationController authenticationController;

	public BLogicResult execute(E_EMLInput input) {
		BLogicResult result = new BLogicResult();
		E_EMLOutput out = new E_EMLOutput();
		BLogicMessages errors = new BLogicMessages();
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
		if(input.getPdfGen() != null){
			input.setPdfCheckSize(input.getPdfGen().length);
		}
		if(input.getEmailSend() != null){
			input.setEmailCheckSize(input.getEmailSend().length);
		}
		if(input.getSameEmail() != null){
			input.setSameEmailCheckSize(input.getSameEmail().length);
		}
		if(input.getPeopleAccNo() != null){
			input.setPeopleCheckSize(input.getPeopleAccNo().length);
		}
		if(!allowSearch){
			
			try {
                BeanUtils.copyProperties(out, input);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            result.setResultObject(out);
            result.setResultString("search");
            errors.add(ActionErrors.GLOBAL_MESSAGE, new BLogicMessage(" ",false));
            result.setErrors(errors);
            return result;
			
		}
		/*List<String> listFullPrinted = new ArrayList<String>();
        listFullPrinted = this.queryDAO.executeForObjectList("E_EML.selectIdRef", input);
        String fullIdRefs = "";
        for (String s : listFullPrinted) {
            fullIdRefs += ("," + s);
        }
        if (!fullIdRefs.equals("")){
        	fullIdRefs = fullIdRefs.substring(1);
        }  */
        E_EML03Input input03 = new E_EML03Input();
        input03.setModuleId("emailAll");
        input03.setUvo(input.getUvo());
        G_EML_P02 gemlp02 = new G_EML_P02(queryDAO, updateDAO);
		GlobalProcessResult gResult = gemlp02.contextInitialized(input03,input);
		result.setResultObject(input);
        result.setErrors(gResult.getErrors());
        result.setMessages(gResult.getMessages());
		result.setResultString("success");
		return result;
	}

	/**
	 * @return the queryDAO
	 */
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	/**
	 * @param queryDAO the queryDAO to set
	 */
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	/**
	 * @return the updateDAO
	 */
	public UpdateDAO getUpdateDAO() {
		return updateDAO;
	}

	/**
	 * @param updateDAO the updateDAO to set
	 */
	public void setUpdateDAO(UpdateDAO updateDAO) {
		this.updateDAO = updateDAO;
	}

	/**
	 * @return the authenticationController
	 */
	public AuthenticationController getAuthenticationController() {
		return authenticationController;
	}

	/**
	 * @param authenticationController the authenticationController to set
	 */
	public void setAuthenticationController(
			AuthenticationController authenticationController) {
		this.authenticationController = authenticationController;
	}

}
