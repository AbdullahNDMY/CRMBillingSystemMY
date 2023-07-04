
/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_CST
 * SERVICE NAME   : M_CST00
 * FUNCTION       : Check display Button base on Users 
 * FILE NAME      : M_CSTR00BLogicBLogic.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
**********************************************************/
package nttdm.bsys.m_cst.blogic;

import java.util.List;
import java.util.Map;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.m_cst.dto.M_CSTR00BLogicInput;
import nttdm.bsys.m_cst.dto.M_CSTR00BLogicOutput;

/**
 * BusinessLogic class.
 * 
 * @author NTT Data VietNam
 */
public class M_CSTR00BLogicBLogic extends AbstractM_CSTR00BLogicBLogic {

	private static final String SQL_ACCESS_TYPE = "SELECT.M_CST.SQL017";
	private static final String SQL_SELECT_ACCOUNTMANAGER = "SELECT.M_CST.SQL022";
	
	/**
	 * 
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	public BLogicResult execute(M_CSTR00BLogicInput param) {
		BLogicResult result = new BLogicResult();
		M_CSTR00BLogicOutput outputDTO = new M_CSTR00BLogicOutput();
		this.checkDisplayButtons(param, outputDTO);
		this.setAccountManager(param, outputDTO);
        this.setAccountManagerString(param, outputDTO);
		outputDTO.setStartIndex(0);
		outputDTO.setTotalCount(0);
		result.setResultObject(outputDTO);
		result.setResultString("success");
		return result;
	}
	
	/**
	 * Set button display flag based on user access type.
	 * 
	 * @param inputDto
	 * @param outputDto
	 */
	private void checkDisplayButtons(M_CSTR00BLogicInput inputDto, M_CSTR00BLogicOutput outputDto){
		// get ACCESS_TYPE
		String accessType = queryDAO.executeForObject(M_CSTR00BLogicBLogic.SQL_ACCESS_TYPE, inputDto.getUvoObject().getId_user(), String.class);
		
		// default of buttons are non-display
		outputDto.setDisplayConsumerCust("0");
		outputDto.setDisplayExport("0");
		outputDto.setAccessType("0");
		// check condition to display of buttons
		if ("1".endsWith(accessType) || "2".equals(accessType)) {
			outputDto.setAccessType(accessType);
			outputDto.setDisplayConsumerCust("1");
		}
	}
	
	/**
	 * Get all Account Manager List.
	 * @param inputDto
	 * @param outputDto
	 */
	private void setAccountManager(M_CSTR00BLogicInput inputDto, M_CSTR00BLogicOutput outputDto) {
	    List<Map<String, Object>> accountManagerList = queryDAO.executeForMapList(SQL_SELECT_ACCOUNTMANAGER, null);
	    outputDto.setAccountManagerList(accountManagerList);
	}
	
	/**
	 * Get all Account Manager String.
	 * @param param
	 * @param outputDTO
	 */
	private void setAccountManagerString(M_CSTR00BLogicInput inputDto,
			M_CSTR00BLogicOutput outputDto) {
	    String accountManagerString = "";
        for(Map<String, Object> map : outputDto.getAccountManagerList()){
        	accountManagerString += CommonUtils.toString(map.get("ACC_MGR_NAME"))+";";
        }
        outputDto.setAccountManagerString(accountManagerString);
	}
}
