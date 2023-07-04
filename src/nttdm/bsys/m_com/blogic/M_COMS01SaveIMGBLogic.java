/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_COM
 * SERVICE NAME   : M_COMS01
 * FUNCTION       : Save Image BLogic
 * FILE NAME      : M_COMS01SaveIMGBLogic.java
 *
 * 
 * 
**********************************************************/
package nttdm.bsys.m_com.blogic;

import java.io.IOException;
import java.util.HashMap;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.m_com.dto.M_COMS01getSaveDataBLogicInput;
import nttdm.bsys.m_com.dto.M_COMS01getSaveDataBLogicOutput;

import org.apache.struts.upload.FormFile;

/**
 * BusinessLogic class.
 * 
 * @author NTT Data Vietnam	
 */
public class M_COMS01SaveIMGBLogic extends
AbstractM_COMS01getSaveDataBLogicBLogic {
	public BLogicResult execute(M_COMS01getSaveDataBLogicInput param) {
		BLogicResult result = new BLogicResult();
		M_COMS01getSaveDataBLogicOutput outputDTO = new M_COMS01getSaveDataBLogicOutput();
		byte[] photo = null;
		
			//get file
		try{

			FormFile f = param.getFile();
			photo = f.getFileData();
		}catch(IOException ex){	
			photo=null;
		}
		//outputDTO.setPhoto(photo);
		//check is M_COM table have value ?
		//HashMap<String , Object> lst_idcom= new HashMap<String, Object>();
		//lst_idcom = (HashMap<String, Object>)queryDAO.executeForMap("SELECT.COM.SQL005IDCOM", null);
		
		
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
		outputDTO.setTbxDefaultDialupTelNo(param.getTbxDefaultDialupTelNo());
		
		result.setResultObject(outputDTO);
		result.setResultString("success");
		return result;
	}

}
