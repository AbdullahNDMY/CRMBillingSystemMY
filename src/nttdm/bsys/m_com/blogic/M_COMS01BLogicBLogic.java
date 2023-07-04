/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_COM
 * SERVICE NAME   : M_COMS01
 * FUNCTION       : Set data BLogic
 * FILE NAME      : M_COMS01BLogicBLogic.java
 *
 * 
 * 
**********************************************************/


package nttdm.bsys.m_com.blogic;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.validator.routines.BigDecimalValidator;
import org.apache.struts.Globals;

import jp.terasoluna.fw.service.thin.BLogicMessage;
import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.c_cmn001.bean.UserAccess;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_com.dto.M_COMS01BLogicInput;
import nttdm.bsys.m_com.dto.M_COMS01BLogicOutput;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import nttdm.bsys.m_com.blogic.AbstractM_COMS01BLogicBLogic;

/**
 * BusinessLogic class.
 * @author NTT Data Vietnam	
 */
public class M_COMS01BLogicBLogic extends AbstractM_COMS01BLogicBLogic {

	/**
	 * 
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	private static final String SQL_getHeader = "SELECT.COM.SQL001";
	private static final String SQL_getSaleContact = "SELECT.COM.SQL002i";
	private static final String SQL_getTechnicalContact= "SELECT.COM.SQL002ii";
	private static final String SQL_getOtherContact = "SELECT.COM.SQL002iii";
	private static final String SQL_getRegisteredAddress = "SELECT.COM.SQL003i";
	private static final String SQL_getCorrespondenceAddress = "SELECT.COM.SQL003ii";
	private static final String ERR2SC003 = "info.ERR2SC003";
	public BLogicResult execute(M_COMS01BLogicInput param) {
		BLogicResult result = new BLogicResult();
		M_COMS01BLogicOutput outputDTO = new M_COMS01BLogicOutput();
		BLogicMessages message=new BLogicMessages();
		// TODO
		// QueryDAO 記述例
		// SampleUVO uvo = queryDAO.executeForObject("namespace.sqlID",
		// params, SampleUVO().class);
		//
		// UpdateDAO 記述例
		// updateDAO.execute("namespace.sqlID", params);
		//
		
		//"Display if M_USER_ACCESS.ID_SUB_MODULE=""M-COM-BI"", and ACCESS_TYPE=2

		//Enable only if the records in M_COM_S01 have been  successfully saved into the database."
		String enable_bank = "";
		
		List<UserAccess> lua = null;
		lua=param.getUvo().getUser_access();
		UserAccess ua = UserAccess.findAccessFunction(lua, "M", "M-COM");
		UserAccess uaBi = UserAccess.findAccessFunction(lua, "M", "M-COM-BI");
				
		if(ua==null  || ua.getAccess_type().equals("1") ){
			outputDTO.setMode("v");
		}else{
			outputDTO.setMode("n");
		}
		String idCom = "";		
		//GET GENERAL INFORMATION
		Map<String,Object> gi = this.queryDAO.executeForMap(M_COMS01BLogicBLogic.SQL_getHeader, "");
		if (gi!=null && !gi.isEmpty()){
			//SET HEDAER INFORMATION
			if (ua!=null && ua.getAccess_type().equals("2") && uaBi!=null && uaBi.getAccess_type().equals("2")){
				enable_bank="1";
			}
			outputDTO.setGen_info(gi);
			
			idCom = gi.get("ID_COM").toString();
			//GET CONTACT
			Map<String,Object> saleContact = this.queryDAO.executeForMap(M_COMS01BLogicBLogic.SQL_getSaleContact, null);			
			Map<String,Object> technicalContact = this.queryDAO.executeForMap(M_COMS01BLogicBLogic.SQL_getTechnicalContact, null);
			Map<String,Object> otherContact = this.queryDAO.executeForMap(M_COMS01BLogicBLogic.SQL_getOtherContact, null);
			//SET CONTACT INFORMATION
			outputDTO.setSale_contact(saleContact);
			outputDTO.setTech_contact(technicalContact);
			outputDTO.setOther_contact(otherContact);
			//GET ADDRESS
			Map<String,Object> registeredAddress = this.queryDAO.executeForMap(M_COMS01BLogicBLogic.SQL_getRegisteredAddress, null);
			Map<String,Object> correspondenceAddress = this.queryDAO.executeForMap(M_COMS01BLogicBLogic.SQL_getCorrespondenceAddress, null);
			outputDTO.setCboAddressCountryRA(registeredAddress.get("COUNTRY").toString());
			outputDTO.setCboAddressCountryCA(correspondenceAddress.get("COUNTRY").toString());
			//SET OUPUT ADDRSS
			outputDTO.setReg_address(registeredAddress);
			outputDTO.setCorr_address(correspondenceAddress);
		}
		outputDTO.setId_com(idCom);
		outputDTO.setEnable_bank(enable_bank);
		String addrLine3Disp = CommonUtils.toString(queryDAO.executeForObject("SELECT.COM.MGSET", null, String.class));
		outputDTO.setAddrLine3Disp(addrLine3Disp);
		
		result.setResultObject(outputDTO);
		
		if( param.getCheckpagetype()!= null && param.getCheckpagetype().equals("1")){
		//message.add(Globals.MESSAGE_KEY,new BLogicMessage(ERR2SC003));
		
		//result.setMessages(message);
			}
		result.setResultString("success");
		return result;
	}
}