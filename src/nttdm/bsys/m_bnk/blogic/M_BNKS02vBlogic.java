package nttdm.bsys.m_bnk.blogic;

import org.apache.struts.Globals;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.m_bnk.bean.M_BNKFormBean;
import nttdm.bsys.m_bnk.bean.M_BNKContactInfo;
import nttdm.bsys.m_bnk.bean.M_BNK_bankbean;
import nttdm.bsys.m_bnk.dto.M_BNKS02vInput;
import nttdm.bsys.m_bnk.dto.M_BNKS02vOutput;
import nttdm.bsys.m_bnk.bean.M_BNK_AdressRA;

public class M_BNKS02vBlogic extends AbstractM_BNKS02v{	
	
	private static final String SQL_GET_BNK_BANKINFO = "SELECT.M_BNK.SQL002";
	private static final String SQL_GET_BNK_BANK_ADRA = "SELECT.M_BNK.SQL003";
	private static final String SQL_GET_BNK_BANK_ADCA = "SELECT.M_BNK.SQL004";
	private static final String SQL_GET_BNK_BANK_CTPC = "SELECT.M_BNK.SQL005";
	private static final String SQL_GET_BNK_BANK_CTBC = "SELECT.M_BNK.SQL006";
	private static final String SQL_GET_BNK_BANK_CTOC = "SELECT.M_BNK.SQL007";
	private static final String SQL_GET_BNK_BANK_ID = "SELECT.M_BNK.SQL017";
	
	public BLogicResult execute(M_BNKS02vInput param) {
		BLogicResult result = new BLogicResult();
		BLogicMessages message=new BLogicMessages();
		M_BNKS02vOutput output = new M_BNKS02vOutput();
		
		//clear Form,when click the exit button
		M_BNKFormBean m_bnkForm=new M_BNKFormBean();
		m_bnkForm.setLblidlogin(param.getLblidlogin());
		m_bnkForm.setHypBankReference(param.getHypBankReference());
		output.setM_bnkForm(m_bnkForm);
		
		try {
		BillingSystemUserValueObject uvo = (BillingSystemUserValueObject) param.getUvo();
		String id_bank=param.getHypBankReference();	
		String bankID = "";
		
		if (param.getLblidlogin() == null) {
			param.setLblidlogin(uvo.getId_user());
		}		
		
		try {
			bankID = queryDAO.executeForObject(SQL_GET_BNK_BANK_ID, param, String.class);
		} catch (Exception e) {
			bankID = "";
		}		
			
			if(id_bank == null || id_bank == "") {
				if(bankID != null || bankID != "") {
					param.setHypBankReference(bankID);
					
				 	M_BNK_bankbean bankbeaninfo=queryDAO.executeForObject(SQL_GET_BNK_BANKINFO, param, M_BNK_bankbean.class);
				 	M_BNK_AdressRA addressRA =queryDAO.executeForObject(SQL_GET_BNK_BANK_ADRA,param,M_BNK_AdressRA.class);
				 	M_BNK_AdressRA addressCA =queryDAO.executeForObject(SQL_GET_BNK_BANK_ADCA,param,M_BNK_AdressRA.class);
				 	M_BNKContactInfo contactPC=queryDAO.executeForObject(SQL_GET_BNK_BANK_CTPC, param, M_BNKContactInfo.class);
				 	M_BNKContactInfo contactBC=queryDAO.executeForObject(SQL_GET_BNK_BANK_CTBC, param, M_BNKContactInfo.class);
				 	M_BNKContactInfo contactOC=queryDAO.executeForObject(SQL_GET_BNK_BANK_CTOC, param, M_BNKContactInfo.class);	        
		        
					output.setBankbeaninfo(bankbeaninfo); 
					output.setAddressRA(addressRA);
					output.setAddressCA(addressCA);
					output.setContactPC(contactPC);
					output.setContactBC(contactBC);
					output.setContactOC(contactOC);
				}
//				result.setResultString("exit");
			}
			else
			{		 
				M_BNK_bankbean bankbeaninfo=queryDAO.executeForObject(SQL_GET_BNK_BANKINFO, param, M_BNK_bankbean.class);
				M_BNK_AdressRA addressRA =queryDAO.executeForObject(SQL_GET_BNK_BANK_ADRA,param,M_BNK_AdressRA.class);
				M_BNK_AdressRA addressCA =queryDAO.executeForObject(SQL_GET_BNK_BANK_ADCA,param,M_BNK_AdressRA.class);
				M_BNKContactInfo contactPC=queryDAO.executeForObject(SQL_GET_BNK_BANK_CTPC, param, M_BNKContactInfo.class);
				M_BNKContactInfo contactBC=queryDAO.executeForObject(SQL_GET_BNK_BANK_CTBC, param, M_BNKContactInfo.class);
				M_BNKContactInfo contactOC=queryDAO.executeForObject(SQL_GET_BNK_BANK_CTOC, param, M_BNKContactInfo.class);
                
				output.setBankbeaninfo(bankbeaninfo); 
				output.setAddressRA(addressRA);
				output.setAddressCA(addressCA);
				output.setContactPC(contactPC);
				output.setContactBC(contactBC);
				output.setContactOC(contactOC);		
			}
	} catch(Exception e) {}	
		result.setResultString("success");
		result.setResultObject(output);
		result.setMessages(message);
		return result;
	}	
	
}
