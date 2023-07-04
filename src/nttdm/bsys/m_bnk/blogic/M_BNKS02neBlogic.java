package nttdm.bsys.m_bnk.blogic;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts.Globals;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_bnk.bean.M_BNKFormBean;
import nttdm.bsys.m_bnk.bean.M_BNKContactInfo;
import nttdm.bsys.m_bnk.bean.M_BNK_bankbean;
import nttdm.bsys.m_bnk.dto.M_BNKS02neInput;
import nttdm.bsys.m_bnk.dto.M_BNKS02neOutput;
import nttdm.bsys.m_bnk.dto.M_BNKS02vInput;
import nttdm.bsys.m_bnk.dto.M_BNKS02vOutput;
import nttdm.bsys.m_bnk.bean.M_BNK_AdressRA;
import java.lang.*;

public class M_BNKS02neBlogic extends AbstractM_BNKS02ne{
	
	private static final String SQL_SET_BNK_BANKINFO = "INSERT.M_BNK.SQL001";
	private static final String SQL_SET_BNK_BANK_ADRA = "INSERT.M_BNK.SQL002";
	private static final String SQL_SET_BNK_BANK_ADCA = "INSERT.M_BNK.SQL003";
	private static final String SQL_SET_BNK_BANK_CTPC = "INSERT.M_BNK.SQL004";
	private static final String SQL_SET_BNK_BANK_CTBC = "INSERT.M_BNK.SQL005";
	private static final String SQL_SET_BNK_BANK_CTOC = "INSERT.M_BNK.SQL006";
	private static final String SQL_GET_BNK_BANK_GETMAXID="SELECT.M_BNK.SQL012";
	private static final String ERR2SC003 = "errors.ERR2SC003";
	private static final String ERR2SC004 = "errors.ERR2SC004";
	
	
	public BLogicResult execute(M_BNKS02neInput param) {
		BLogicResult result = new BLogicResult();
		BLogicMessages message=new BLogicMessages();
		M_BNKS02neOutput output= new M_BNKS02neOutput();
		
		try
		{

		String id_maxbank = queryDAO.executeForObject("SELECT.ID_MAX_BANK", null,String.class);
		// Audit Trail
		String idbank=param.getTbxBankCode();
		String bankfullname=param.getLblBankFullName();
		String bankbranch=param.getTbxBranchCode();
		String reference=idbank+":"+bankfullname+bankbranch;
		Integer idAudit = CommonUtils.auditTrailBegin(param.getUvo().getId_user(), BillingSystemConstants.MODULE_M, 
				BillingSystemConstants.SUB_MODULE_M_BNK, 
				reference, null, BillingSystemConstants.AUDIT_TRAIL_CREATED);
		param.setIdAudit(idAudit);
		param.setId_maxbank(id_maxbank);

		int ret=updateDAO.execute(SQL_SET_BNK_BANKINFO, param);
		if( ret >0)
		{			
        	
		String idmax = queryDAO.executeForObject(SQL_GET_BNK_BANK_GETMAXID, null,String.class);	
		param.setId_maxbank(idmax);

		updateDAO.execute(SQL_SET_BNK_BANK_ADRA, param);
		updateDAO.execute(SQL_SET_BNK_BANK_ADCA, param);
		if(param.getTbxDIDNoPC()=="")
			param.setTbxDIDNoPC(" ");
		if(param.getTbxMobileNoPc()=="")
			param.setTbxMobileNoPc(" ");
				
		updateDAO.execute(SQL_SET_BNK_BANK_CTPC, param);
		
		if(param.getTbxContactNameBC()=="")
			param.setTbxContactNameBC(" ");
		if(param.getTbxDesignationBC() == "")
			param.setTbxDesignationBC(" ");
		if(param.getTbxEmailBC() == "")
			param.setTbxEmailBC(" ");
		if(param.getTbxTelephoneNoBC()== "")
			param.setTbxTelephoneNoBC(" ");
		if(param.getTbxFaxNoBC()== "")
			param.setTbxFaxNoBC(" ");
		if(param.getTbxDIDNoBC()=="")
			param.setTbxDIDNoBC(" ");
		if(param.getTbxMobileNoBC()=="")
			param.setTbxMobileNoBC(" ");
		
		if(param.getTbxContactNameOC()=="")
			param.setTbxContactNameOC(" ");
		if(param.getTbxDesignationOC() == "")
			param.setTbxDesignationOC(" ");
		if(param.getTbxEmailOC() == "")
			param.setTbxEmailOC(" ");
		if(param.getTbxTelephoneNoOC()== "")
			param.setTbxTelephoneNoOC(" ");
		if(param.getTbxFaxNoOC()== "")
			param.setTbxFaxNoOC(" ");
		if(param.getTbxDIDNoOC()=="")
			param.setTbxDIDNoOC(" ");
		if(param.getTbxMobileNoOC()=="")
			param.setTbxMobileNoOC(" ");

		updateDAO.execute(SQL_SET_BNK_BANK_CTBC, param);
		
		updateDAO.execute(SQL_SET_BNK_BANK_CTOC, param);
		
		//End Audit Trail
		CommonUtils.auditTrailEnd(idAudit);

		String idref="";
		message.add(Globals.MESSAGE_KEY,new BLogicMessage(ERR2SC003,idref));
		output.setPage_status("1");
		}
		else
		{
			String idref="";
			message.add(Globals.MESSAGE_KEY,new BLogicMessage(ERR2SC004,idref));		
			output.setPage_status("2");
		}
		
		result.setResultString("success");
		}
		catch(Exception ex)
		{
			String idref="";
			message.add(Globals.MESSAGE_KEY,new BLogicMessage(ERR2SC004,idref));
			output.setPage_status("2");
			result.setResultString("error");
		}
		
		
		result.setResultObject(output);
		result.setMessages(message);
		
        return result;
        
	}	

}
