
/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_COM
 * SERVICE NAME   : M_COMS01
 * FUNCTION       : Save BLogic
 * FILE NAME      : M_COMS01SaveBLogicBLogic.java
 *
 * 
 * 
**********************************************************/
package nttdm.bsys.m_com.blogic;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts.Globals;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_com.dto.M_COMS01SaveBLogicInput;
import nttdm.bsys.m_com.dto.M_COMS01SaveBLogicOutput;

import nttdm.bsys.m_com.blogic.AbstractM_COMS01SaveBLogicBLogic;

/**
 * BusinessLogic class.
 * 
 * @author NTT Data Vietnam	
 */
public class M_COMS01SaveBLogicBLogic extends AbstractM_COMS01SaveBLogicBLogic {

	/**
	 * 
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	private static final String SQL_insertHeader = "INSERT.COM.SQLSave001";
	private static final String SQL_insertContact= "INSERT.COM.SQLSave002";	
	private static final String SQL_insertAddress= "INSERT.COM.SQLSave003";
	BLogicMessages message=new BLogicMessages();
	private static final String ERR2SC003 = "info.ERR2SC003";
	
	private static final String SQL_updateHeader = "UPDATE.COM.SQLEdit001";
	private static final String SQL_updateContact= "UPDATE.COM.SQLEdit002";	
	private static final String SQL_updateAddress= "UPDATE.COM.SQLEdit003";
	
	private static final String SQL_check_contact = "SELECT.COM.SQL006";	
	private static final String SQL_getHeader = "SELECT.COM.SQL001";
	
	
	public BLogicResult execute(M_COMS01SaveBLogicInput param) {
		BLogicResult result = new BLogicResult();
		M_COMS01SaveBLogicOutput outputDTO = new M_COMS01SaveBLogicOutput();

		// TODO
		// QueryDAO 記述例
		// SampleUVO uvo = queryDAO.executeForObject("namespace.sqlID",
		// params, SampleUVO().class);
		//
		// UpdateDAO 記述例
		// updateDAO.execute("namespace.sqlID", params);
		//
		//get id com
		String idCom = param.getId_com();
		
		String userId = param.getUvo().getId_user();
		byte[] photo = param.getPhoto();
		
		/*
		String regNo = param.getTbxCompanyRegNo().equals("") ? "0" : param.getTbxCompanyRegNo(); 
		String phoneNo = param.getTbxTelephoneNo().equals("") ? "0" : param.getTbxTelephoneNo();
		String faxNo = param.getTbxFaxNo().equals("") ? "0" : param.getTbxFaxNo();
		String web = param.getTbxCompanyWebsite().equals("") ? "www." : param.getTbxCompanyWebsite();
		String affCode = param.getTbxAffiliateCode().equals("") ? " " : param.getTbxAffiliateCode();
		String prox = param.getTbxPrimaryDomainNameServer().equals("") ? " " : param.getTbxPrimaryDomainNameServer();
		String port = param.getTbxPortNumber().equals("") ? "0" : param.getTbxPortNumber();
		String priD = param.getTbxPrimaryDomainNameServer().equals("") ? " " : param.getTbxPrimaryDomainNameServer();
		String secD = param.getTbxSecondaryDomainNameServer().equals("") ? " " : param.getTbxSecondaryDomainNameServer();
		*/       
		String regNo = param.getTbxCompanyRegNo();
		String gstRegNo = param.getTbxCompanyGstRegNo();
		String phoneNo = param.getTbxTelephoneNo();
		String faxNo = param.getTbxFaxNo();
		String web = param.getTbxCompanyWebsite();
		String affCode = param.getTbxAffiliateCode();
		String prox = param.getTbxProxyServerName();
		String port = param.getTbxPortNumber();
		String priD = param.getTbxPrimaryDomainNameServer();
		String secD = param.getTbxSecondaryDomainNameServer();
		String domainName = param.getTbxDomainName();
		String popServerName = param.getTbxPopServerName();
		String smtpServerName = param.getTbxSMTPServerName();
		String webMailURL = param.getTbxWebmailURL();
		
		String defD=param.getTbxDefaultDialupTelNo();
		String defP=param.getTbxDefaultPassword();
		
		if (idCom.equals("")) { 
			//NEW MODE
			//insert into table A
			java.util.HashMap<String, Object> parameter = new HashMap<String, Object>();
			//COM_NAME,COM_REGNO	,COM_TEL_NO	,COM_FAX_NO,COM_WEBSITE,AFFILIATE_CODE,LOGO,
			//PROXSERV_NAME,PORT_NO,PRIMDOMAIN_NO,SECDOMAIN_NO,IS_DELETED,DATE_CREATED,DATE_UPDATED,ID_LOGIN)
			
			/**
			 * Audit Trail
			 */
			Integer idAudit = CommonUtils.auditTrailBegin(userId, BillingSystemConstants.MODULE_M,
					BillingSystemConstants.SUB_MODULE_M_COM, param.getTbxCompanyName(), null,
					BillingSystemConstants.AUDIT_TRAIL_CREATED);
			
			parameter.put("ID_COM", "11");
			parameter.put("COM_NAME", param.getTbxCompanyName());
			parameter.put("COM_REGNO", regNo);
			parameter.put("COM_GST_REG_NO", gstRegNo);
			parameter.put("COM_TEL_NO", phoneNo);
			parameter.put("COM_FAX_NO", faxNo);
			parameter.put("COM_WEBSITE", web);
			parameter.put("AFFILIATE_CODE", affCode);

			parameter.put("PROXSERV_NAME", prox);
			parameter.put("PORT_NO", port);
			parameter.put("PRIMDOMAIN_NO", priD);
			parameter.put("SECDOMAIN_NO", secD);
			parameter.put("IS_DELETED", "0");
			parameter.put("ID_LOGIN", userId);
			parameter.put("LOGO", photo); //insert photo
			parameter.put("ID_AUDIT", idAudit);
			parameter.put("DEFAULT_DIALUPTELNO", defD);
			parameter.put("DEFAULT_ROUTERPW",defP );
			parameter.put("DOMAIN_NAME", domainName);
			parameter.put("POP_SERVER_NAME", popServerName);
			parameter.put("SMTP_SERVER_NAME", smtpServerName);
			parameter.put("WEBMAIL_URL", webMailURL);
			
			updateDAO.execute(M_COMS01SaveBLogicBLogic.SQL_insertHeader, parameter);
			//get just inserted ID_COM
			Map<String,Object> com = queryDAO.executeForMap(M_COMS01SaveBLogicBLogic.SQL_getHeader, null);
			String idComNew = com.get("ID_COM").toString();
			
			//check value in registered address tab
			if (!param.getTbxAddressLine1RA().trim().equals("") &&!param.getTbxZipCodeRA().trim().equals("")&&!param.getCboAddressCountryRA().trim().equals("") ) {
				//insert registered address 
				//#ID_COM#,#COM_ADR_TYPE#,#C_ADR_LINE1#,#C_ADR_LINE2#,#C_ADR_LINE3#,#ZIP_CODE#,#COUNTRY#,SYSDATE,SYSDATE,#ID_LOGIN#				
				parameter = new HashMap<String, Object>();
				parameter.put("ID_COM", idComNew);
				parameter.put("COM_ADR_TYPE","RA" );
				parameter.put("C_ADR_LINE1",param.getTbxAddressLine1RA() );
				parameter.put("C_ADR_LINE2", param.getTbxAddressLine2RA() );
				parameter.put("C_ADR_LINE3", param.getTbxAddressLine3RA() );
				parameter.put("ZIP_CODE", param.getTbxZipCodeRA());
				parameter.put("COUNTRY", param.getCboAddressCountryRA());
				parameter.put("ID_LOGIN", userId);
				parameter.put("ID_AUDIT", idAudit);
				updateDAO.execute(M_COMS01SaveBLogicBLogic.SQL_insertAddress, parameter);
			}

			//check value in correspondence address tab
			if (!param.getTbxAddressLine1CA().trim().equals("") &&!param.getTbxZipCodeCA().trim().equals("")&&!param.getCboAddressCountryCA().trim().equals("")){
				//insert coresspondence address 
				//#ID_COM#,#COM_ADR_TYPE#,#C_ADR_LINE1#,#C_ADR_LINE2#,#C_ADR_LINE3#,#ZIP_CODE#,#COUNTRY#,SYSDATE,SYSDATE,#ID_LOGIN#				
				parameter = new HashMap<String, Object>();
				parameter.put("ID_AUDIT", idAudit);
				parameter.put("ID_COM", idComNew);
				parameter.put("COM_ADR_TYPE","CA" );
				parameter.put("C_ADR_LINE1",param.getTbxAddressLine1CA() );
				parameter.put("C_ADR_LINE2", param.getTbxAddressLine2CA() );
				parameter.put("C_ADR_LINE3", param.getTbxAddressLine3CA() );
				parameter.put("ZIP_CODE", param.getTbxZipCodeCA());
				parameter.put("COUNTRY", param.getCboAddressCountryCA());
				parameter.put("ID_LOGIN", userId);
				updateDAO.execute(M_COMS01SaveBLogicBLogic.SQL_insertAddress, parameter);
			}
			//check sales contact tab
			if(param.getTbxEmailSC().trim().length()!=0 && param.getTbxTelephoneNoSC().trim().length()!=0 && param.getTbxFaxNoSC().trim().length()!=0){
				//VALUES(#ID_COM#,#CONTACT_TYPE#,#EMAIL#,#TEL_NO#,#FAX_NO#,SYSDATE,SYSDATE,#ID_LOGIN#)
				//insert sale contact
				parameter = new HashMap<String, Object>();
				parameter.put("ID_COM", idComNew);
				parameter.put("ID_LOGIN", userId);
				parameter.put("CONTACT_TYPE", "SC");
				parameter.put("EMAIL", param.getTbxEmailSC());
				parameter.put("TEL_NO", param.getTbxTelephoneNoSC());
				parameter.put("FAX_NO", param.getTbxFaxNoSC());
				parameter.put("ID_AUDIT", idAudit);
				updateDAO.execute(M_COMS01SaveBLogicBLogic.SQL_insertContact, parameter);
			}
			
			//check sales technical tab
			if(param.getTbxEmailTC().trim().length()!=0 || param.getTbxTelephoneNoTC().trim().length()!=0 || param.getTbxFaxNoTC().trim().length()!=0){
				//VALUES(#ID_COM#,#CONTACT_TYPE#,#EMAIL#,#TEL_NO#,#FAX_NO#,SYSDATE,SYSDATE,#ID_LOGIN#)
				//insert sale contact
				parameter = new HashMap<String, Object>();
				String email= param.getTbxEmailTC().equals("") ? " " :param.getTbxEmailTC() ;
				String tel= param.getTbxTelephoneNoTC().equals("") ? " " :param.getTbxTelephoneNoTC() ;
				String fax= param.getTbxFaxNoTC().equals("") ? " " :param.getTbxFaxNoTC() ;
				parameter.put("ID_COM", idComNew);
				parameter.put("ID_LOGIN", userId);
				parameter.put("CONTACT_TYPE", "TC");
				parameter.put("EMAIL", email);
				parameter.put("TEL_NO", tel);
				parameter.put("FAX_NO", fax);
				parameter.put("ID_AUDIT", idAudit);
				updateDAO.execute(M_COMS01SaveBLogicBLogic.SQL_insertContact, parameter);
			}
			
			//check other contact tab
			if(param.getTbxEmailOC().trim().length()!=0 || param.getTbxTelephoneNoOC().trim().length()!=0 || param.getTbxFaxNoOC().trim().length()!=0){
				//VALUES(#ID_COM#,#CONTACT_TYPE#,#EMAIL#,#TEL_NO#,#FAX_NO#,SYSDATE,SYSDATE,#ID_LOGIN#)
				//insert sale contact
				String email= param.getTbxEmailOC().equals("") ? " " :param.getTbxEmailOC() ;
				String tel= param.getTbxTelephoneNoOC().equals("") ? " " :param.getTbxTelephoneNoOC() ;
				String fax= param.getTbxFaxNoOC().equals("") ? " " :param.getTbxFaxNoOC() ;
				parameter = new HashMap<String, Object>();
				parameter.put("ID_COM", idComNew);
				parameter.put("ID_LOGIN", userId);
				parameter.put("CONTACT_TYPE", "OC");
				parameter.put("EMAIL", email);
				parameter.put("TEL_NO", tel);
				parameter.put("FAX_NO", fax);
				parameter.put("ID_AUDIT", idAudit);
				updateDAO.execute(M_COMS01SaveBLogicBLogic.SQL_insertContact, parameter);
			}
			CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
		}else{
			//EDIT MODE
			java.util.HashMap<String, Object> parameter = new HashMap<String, Object>();
			//COM_NAME,COM_REGNO	,COM_TEL_NO	,COM_FAX_NO,COM_WEBSITE,AFFILIATE_CODE,LOGO,
			//PROXSERV_NAME,PORT_NO,PRIMDOMAIN_NO,SECDOMAIN_NO,IS_DELETED,DATE_CREATED,DATE_UPDATED,ID_LOGIN)
			/**
			 * Audit Trail
			 */
			Integer idAudit = CommonUtils.auditTrailBegin(userId, BillingSystemConstants.MODULE_M,
					BillingSystemConstants.SUB_MODULE_M_COM, param.getTbxCompanyName(), null,
					BillingSystemConstants.AUDIT_TRAIL_EDITED);
			
			parameter.put("COM_NAME", param.getTbxCompanyName());
			parameter.put("COM_REGNO", regNo);
			parameter.put("COM_GST_REG_NO", gstRegNo);
			parameter.put("COM_TEL_NO",phoneNo);
			parameter.put("COM_FAX_NO", faxNo);
			parameter.put("COM_WEBSITE", web);
			parameter.put("AFFILIATE_CODE", affCode);

			parameter.put("PROXSERV_NAME", prox);
			parameter.put("PORT_NO", port);
			parameter.put("PRIMDOMAIN_NO", priD);
			parameter.put("SECDOMAIN_NO", secD);
			parameter.put("IS_DELETED", "0");
			parameter.put("ID_LOGIN", userId);
			parameter.put("LOGO", photo); //insert photo
			parameter.put("ID_COM", idCom);
			parameter.put("ID_AUDIT", idAudit);
			parameter.put("DEFAULT_DIALUPTELNO",defD);
			parameter.put("DEFAULT_ROUTERPW",defP);
			parameter.put("DOMAIN_NAME", domainName);
            parameter.put("POP_SERVER_NAME", popServerName);
            parameter.put("SMTP_SERVER_NAME", smtpServerName);
            parameter.put("WEBMAIL_URL", webMailURL);
			updateDAO.execute(M_COMS01SaveBLogicBLogic.SQL_updateHeader, parameter);			
			
			//check value in registered address tab
			if (!param.getTbxAddressLine1RA().trim().equals("") &&!param.getTbxZipCodeRA().trim().equals("")&&!param.getCboAddressCountryRA().trim().equals("")){
				//insert registered address 
				//#ID_COM#,#COM_ADR_TYPE#,#C_ADR_LINE1#,#C_ADR_LINE2#,#C_ADR_LINE3#,#ZIP_CODE#,#COUNTRY#,SYSDATE,SYSDATE,#ID_LOGIN#				
				parameter = new HashMap<String, Object>();
				String add3 = param.getTbxAddressLine3RA().equals("") ? " " :param.getTbxAddressLine3RA();
				parameter.put("ID_COM", idCom);
				parameter.put("COM_ADR_TYPE","RA" );
				parameter.put("C_ADR_LINE1",param.getTbxAddressLine1RA() );
				parameter.put("C_ADR_LINE2", param.getTbxAddressLine2RA() );
				parameter.put("C_ADR_LINE3",add3 );
				parameter.put("ZIP_CODE", param.getTbxZipCodeRA());
				parameter.put("COUNTRY", param.getCboAddressCountryRA());
				parameter.put("ID_LOGIN", userId);
				parameter.put("ID_AUDIT", idAudit);
				
				updateDAO.execute(M_COMS01SaveBLogicBLogic.SQL_updateAddress, parameter);
			}

			//check value in correspondence address tab
			if (!param.getTbxAddressLine1CA().trim().equals("")&&!param.getTbxZipCodeCA().trim().equals("")&&!param.getCboAddressCountryCA().trim().equals("")){
				//insert coresspondence address 
				//#ID_COM#,#COM_ADR_TYPE#,#C_ADR_LINE1#,#C_ADR_LINE2#,#C_ADR_LINE3#,#ZIP_CODE#,#COUNTRY#,SYSDATE,SYSDATE,#ID_LOGIN#				
				parameter = new HashMap<String, Object>();
				String add3 = param.getTbxAddressLine3CA().equals("") ? " " :param.getTbxAddressLine3CA();
				parameter.put("ID_COM", idCom);
				parameter.put("COM_ADR_TYPE","CA" );
				parameter.put("C_ADR_LINE1",param.getTbxAddressLine1CA() );
				parameter.put("C_ADR_LINE2", param.getTbxAddressLine2CA() );
				parameter.put("C_ADR_LINE3", add3 );
				parameter.put("ZIP_CODE", param.getTbxZipCodeCA());
				parameter.put("COUNTRY", param.getCboAddressCountryCA());
				parameter.put("ID_LOGIN", userId);
				parameter.put("ID_AUDIT", idAudit);
				updateDAO.execute(M_COMS01SaveBLogicBLogic.SQL_updateAddress, parameter);
			}
			//check sales contact tab
			if(param.getTbxEmailSC().trim().length()!=0 && param.getTbxTelephoneNoSC().trim().length()!=0 && param.getTbxFaxNoSC().trim().length()!=0){
				//VALUES(#ID_COM#,#CONTACT_TYPE#,#EMAIL#,#TEL_NO#,#FAX_NO#,SYSDATE,SYSDATE,#ID_LOGIN#)
				//insert sale contact
				parameter = new HashMap<String, Object>();
				parameter.put("ID_COM", idCom);
				parameter.put("ID_LOGIN", userId);
				parameter.put("CONTACT_TYPE", "SC");
				parameter.put("EMAIL", param.getTbxEmailSC());
				parameter.put("TEL_NO", param.getTbxTelephoneNoSC());
				parameter.put("FAX_NO", param.getTbxFaxNoSC());
				parameter.put("ID_AUDIT", idAudit);
                HashMap<String,Object> chkParam = new HashMap<String, Object>();
                chkParam.put("ID_COM", idCom);
                chkParam.put("COM_CTC_TYPE", "SC");
                Map<String,Object> chk = queryDAO.executeForMap(M_COMS01SaveBLogicBLogic.SQL_check_contact, chkParam);
                if(chk==null){
                    updateDAO.execute(M_COMS01SaveBLogicBLogic.SQL_insertContact, parameter);
                }else{              
                    updateDAO.execute(M_COMS01SaveBLogicBLogic.SQL_updateContact, parameter);
                }   
            }
			
			//check sales technical tab
			if(param.getTbxEmailTC().trim().length()!=0 || param.getTbxTelephoneNoTC().trim().length()!=0 || param.getTbxFaxNoTC().trim().length()!=0){
				//VALUES(#ID_COM#,#CONTACT_TYPE#,#EMAIL#,#TEL_NO#,#FAX_NO#,SYSDATE,SYSDATE,#ID_LOGIN#)
				//insert sale contact
				parameter = new HashMap<String, Object>();
				String email= param.getTbxEmailTC().equals("") ? " " :param.getTbxEmailTC() ;
				String tel= param.getTbxTelephoneNoTC().equals("") ? " " :param.getTbxTelephoneNoTC() ;
				String fax= param.getTbxFaxNoTC().equals("") ? " " :param.getTbxFaxNoTC() ;
				parameter.put("ID_COM", idCom);
				parameter.put("ID_LOGIN", userId);
				parameter.put("CONTACT_TYPE", "TC");
				parameter.put("EMAIL", email);
				parameter.put("TEL_NO", tel);
				parameter.put("FAX_NO", fax);
				parameter.put("ID_AUDIT", idAudit);
				//check if contact exist
				HashMap<String,Object> chkParam = new HashMap<String, Object>();
				chkParam.put("ID_COM", idCom);
				chkParam.put("COM_CTC_TYPE", "TC");
				Map<String,Object> chk = queryDAO.executeForMap(M_COMS01SaveBLogicBLogic.SQL_check_contact, chkParam);
				if(chk==null){
					updateDAO.execute(M_COMS01SaveBLogicBLogic.SQL_insertContact, parameter);
				}else{				
					updateDAO.execute(M_COMS01SaveBLogicBLogic.SQL_updateContact, parameter);
				}
			}
			
			//check other contact tab
			if(param.getTbxEmailOC().trim().length()!=0 || param.getTbxTelephoneNoOC().trim().length()!=0 || param.getTbxFaxNoOC().trim().length()!=0){
				//VALUES(#ID_COM#,#CONTACT_TYPE#,#EMAIL#,#TEL_NO#,#FAX_NO#,SYSDATE,SYSDATE,#ID_LOGIN#)
				//insert sale contact
				String email= param.getTbxEmailOC().equals("") ? " " :param.getTbxEmailOC() ;
				String tel= param.getTbxTelephoneNoOC().equals("") ? " " :param.getTbxTelephoneNoOC() ;
				String fax= param.getTbxFaxNoOC().equals("") ? " " :param.getTbxFaxNoOC() ;
				parameter = new HashMap<String, Object>();
				parameter.put("ID_COM", idCom);
				parameter.put("ID_LOGIN", userId);
				parameter.put("CONTACT_TYPE", "OC");
				parameter.put("EMAIL", email);
				parameter.put("TEL_NO",tel);
				parameter.put("FAX_NO", fax);
				parameter.put("ID_AUDIT", idAudit);
				
				HashMap<String,Object> chkParam = new HashMap<String, Object>();
				chkParam.put("ID_COM", idCom);
				chkParam.put("COM_CTC_TYPE", "OC");
				Map<String,Object> chk = queryDAO.executeForMap(M_COMS01SaveBLogicBLogic.SQL_check_contact, chkParam);
				if(chk==null){
					updateDAO.execute(M_COMS01SaveBLogicBLogic.SQL_insertContact, parameter);
				}else{				
					updateDAO.execute(M_COMS01SaveBLogicBLogic.SQL_updateContact, parameter);
				}				
			}
			CommonUtils.auditTrailEnd(idAudit);//End Audit Trail
		}		
		outputDTO.setId_com(idCom);
		result.setResultObject(outputDTO);
		message.clear();
		message.add(Globals.MESSAGE_KEY,new BLogicMessage(ERR2SC003));
		
		result.setMessages(message);
		result.setResultString("success");
		return result;
	}
}