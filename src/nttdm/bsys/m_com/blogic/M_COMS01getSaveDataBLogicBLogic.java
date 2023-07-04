
/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_COM
 * SERVICE NAME   : M_COMS01
 * FUNCTION       : Save data BLogic
 * FILE NAME      : M_COMS01getSaveDataBLogicBLogic.java
 *
 * 
 * 
**********************************************************/
package nttdm.bsys.m_com.blogic;

import java.io.IOException;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.BillingSystemConstants;
import nttdm.bsys.common.util.BillingSystemSettings;
import nttdm.bsys.m_com.dto.M_COMS01getSaveDataBLogicInput;
import nttdm.bsys.m_com.dto.M_COMS01getSaveDataBLogicOutput;

import nttdm.bsys.m_com.blogic.AbstractM_COMS01getSaveDataBLogicBLogic;

import org.apache.struts.Globals;
import org.apache.struts.upload.FormFile;

/**
 * BusinessLogic class.
 * 
 * @author NTT Data Vietnam	
 */
public class M_COMS01getSaveDataBLogicBLogic extends AbstractM_COMS01getSaveDataBLogicBLogic {
	private static final String ERR_FILE_SIZE_UPLOAD = "errors.ERR1SC027";
	
	/**
	 * 
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	public BLogicResult execute(M_COMS01getSaveDataBLogicInput param) {
		BLogicResult result = new BLogicResult();
		M_COMS01getSaveDataBLogicOutput outputDTO = new M_COMS01getSaveDataBLogicOutput();
		BLogicMessages errors = new BLogicMessages();
		byte[] photo = null;
		boolean fileExceed = false;
		
		// Get system settings
		BillingSystemSettings systemSetting = new BillingSystemSettings(queryDAO);					
		//Get max file size (in MBytes)
		int maxFileSize = systemSetting.getFileSizeUpload();
		
		//get file
		try{
			FormFile f = param.getFile();
			photo = f.getFileData();
			if(photo.length == 0) photo = null;
			if(f != null && f.getFileSize() > maxFileSize) {
				fileExceed = true;
			}
		}catch(IOException ex){	
			photo=null;
		}
		
		if(fileExceed) {
			errors.add(Globals.MESSAGE_KEY, new BLogicMessage(ERR_FILE_SIZE_UPLOAD, new Object[] {maxFileSize}));
			result.setErrors(errors);
			result.setResultString(BillingSystemConstants.BLOGIC_RESULT_STR_FAILURE);
			return result;
		}
		
		outputDTO.setPhoto(photo);
		
		
		outputDTO.setId_com(param.getId_com());
		
		outputDTO.setTbxCompanyName(param.getTbxCompanyName().trim());
		outputDTO.setTbxCompanyRegNo(param.getTbxCompanyRegNo());
		outputDTO.setTbxCompanyGstRegNo(param.getTbxCompanyGstRegNo());
		outputDTO.setTbxCompanyWebsite(param.getTbxCompanyWebsite());
		outputDTO.setTbxTelephoneNo(param.getTbxTelephoneNo());
		outputDTO.setTbxFaxNo(param.getTbxFaxNo());
		outputDTO.setTbxAffiliateCode(param.getTbxAffiliateCode());
		
		//REGISTERED ADDRESS
		outputDTO.setTbxAddressLine1RA(param.getTbxAddressLine1RA());
		outputDTO.setTbxAddressLine2RA(param.getTbxAddressLine2RA());
		outputDTO.setTbxAddressLine3RA(param.getTbxAddressLine3RA());
		outputDTO.setTbxZipCodeRA(param.getTbxZipCodeRA());
		outputDTO.setCboAddressCountryRA(param.getCboAddressCountryRA());
		// CORESSPONDENCE ADDRESS
		outputDTO.setTbxAddressLine1CA(param.getTbxAddressLine1CA());
		outputDTO.setTbxAddressLine2CA(param.getTbxAddressLine2CA());
		outputDTO.setTbxAddressLine3CA(param.getTbxAddressLine3CA());
		outputDTO.setTbxZipCodeCA(param.getTbxZipCodeCA());
		outputDTO.setCboAddressCountryCA(param.getCboAddressCountryCA());
		
		//CONTACT SALES
		outputDTO.setTbxEmailSC(param.getTbxEmailSC());
		outputDTO.setTbxTelephoneNoSC(param.getTbxTelephoneNoSC());
		outputDTO.setTbxFaxNoSC(param.getTbxFaxNoSC());
		//TECHNICAL CONTACT
		outputDTO.setTbxEmailTC(param.getTbxEmailTC());
		outputDTO.setTbxTelephoneNoTC(param.getTbxTelephoneNoTC());
		outputDTO.setTbxFaxNoTC(param.getTbxFaxNoTC());
		//OTHER CONTACT
		outputDTO.setTbxEmailOC(param.getTbxEmailOC());
		outputDTO.setTbxTelephoneNoOC(param.getTbxTelephoneNoOC());
		outputDTO.setTbxFaxNoOC(param.getTbxFaxNoOC());
		
		//ADDITIONAL INFO
		outputDTO.setTbxProxyServerName(param.getTbxProxyServerName());
		outputDTO.setTbxPortNumber(param.getTbxPortNumber());
		outputDTO.setTbxPrimaryDomainNameServer(param.getTbxPrimaryDomainNameServer());
		outputDTO.setTbxSecondaryDomainNameServer(param.getTbxSecondaryDomainNameServer());
		outputDTO.setTbxDomainName(param.getTbxDomainName());
		outputDTO.setTbxPopServerName(param.getTbxPopServerName());
		outputDTO.setTbxSMTPServerName(param.getTbxSMTPServerName());
		outputDTO.setTbxWebmailURL(param.getTbxWebmailURL());
		
		outputDTO.setTbxDefaultDialupTelNo(param.getTbxDefaultDialupTelNo());
		outputDTO.setTbxDefaultPassword(param.getTbxDefaultPassword());
		
		result.setResultObject(outputDTO);
		result.setResultString("success");
		return result;
	}
}