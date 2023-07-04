
/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : M_COM
 * SERVICE NAME   : M_COMS01
 * FUNCTION       : Get Date BLogic
 * FILE NAME      : M_CSTR10BLogic.java
 *
 * * Copyright © 2011 NTTDATA Corporation
 * 
**********************************************************/
package nttdm.bsys.m_cst.blogic;

import java.util.List;

import jp.terasoluna.fw.service.thin.BLogicResult;
import nttdm.bsys.common.util.CommonUtils;
import nttdm.bsys.common.util.SecurityUtil;
import nttdm.bsys.m_cst.common.Util;
import nttdm.bsys.m_cst.dto.Bank;
import nttdm.bsys.m_cst.dto.BankInfoDto;
import nttdm.bsys.m_cst.dto.M_CSTR10BLogicInput;
import nttdm.bsys.m_cst.dto.M_CSTR10BLogicOutput;

/**
 * BusinessLogic class<br>
 * <br>
 * <ul>
 * <li>
 * <li>BusinessLogic class
 * </ul>
 * <br>
* @author  NTTData Vietnam
* @version 1.1 
 */
public class M_CSTR10BLogic extends AbstractM_CSTR10BLogic {

	/**
	 * 
	 * @param param
	 * @return ビジネスロジックの実行結果、BLogicResultインスタンス。
	 */
	public BLogicResult execute(M_CSTR10BLogicInput param) {
		BLogicResult result = new BLogicResult();
		M_CSTR10BLogicOutput outputDTO = new M_CSTR10BLogicOutput();

		// get m_bank
		List bankList = queryDAO.executeForObjectList(Util.SELECT_BANK_LIST, null);
		outputDTO.setBankList(bankList);
		// get Bank Information from DB
		BankInfoDto bankInfoDto = queryDAO.executeForObject(Util.SELECT_BANK_INFO, param.getId_cust(), BankInfoDto.class);
		// set branchCode & bankCode
	    /**
	     * Method description
	     * @param CbBankCode description of CbBankCode
	     * @param CbBranchCode description of CbBranchCode
	     * 
	     */

		if (bankInfoDto != null){
			for (int i = 0; i < bankList.size(); i++) {
				Bank bank = (Bank) bankList.get(i);
				if (bankInfoDto.getBank() != null && bankInfoDto.getBank().equals(bank.getIdBank())){
					outputDTO.setBankCode(bank.getBankCode());
					outputDTO.setCbBankCode(bank.getBankCode());
					outputDTO.setBranchCode(bank.getBranchCode());
					outputDTO.setCbBranchCode(bank.getBranchCode());
					outputDTO.setBankFullName(bank.getBankFullName());
					outputDTO.setBankBicCode(bank.getBankBicCode());
					break;
				}
			}
			// split Month and Year of Expiry date
			if (bankInfoDto.getExpiredDate()!= null && !bankInfoDto.getExpiredDate().equals("")) {
				outputDTO.setExpiredDate(bankInfoDto.getExpiredDate());
				String[] split = bankInfoDto.getExpiredDate().split("/");
				outputDTO.setExpiredDateMonth(split[0]);
				outputDTO.setExpiredDateYear(split[1]);
			}
			String creditCardNumber = bankInfoDto.getCreditCardNumber();
			String securityNo = bankInfoDto.getSecurityNo();
			if(CommonUtils.notEmpty(creditCardNumber)){
			    bankInfoDto.setCreditCardNumber(SecurityUtil.aesDecrypt(creditCardNumber));
			}
			if(CommonUtils.notEmpty(securityNo)){
                bankInfoDto.setSecurityNo(SecurityUtil.aesDecrypt(securityNo));
            }
			outputDTO.setOutput(bankInfoDto);
			outputDTO.setHasBankInfo(true);
		}else{
			outputDTO.setBlankOutput(param);
			// if bankInfo not yet existed ==> bank = -1
			outputDTO.setHasBankInfo(false);
		}
		
		outputDTO.setId_cust(param.getId_cust());
		outputDTO.setMode(param.getMode());
		result.setResultString("success");
		result.setResultObject(outputDTO);
		
		
		return result;
	}
	
}