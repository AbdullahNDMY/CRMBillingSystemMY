/**
 * @(#)BSYSFieldChecksEx.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.validator;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jp.terasoluna.fw.dao.QueryDAO;
import nttdm.bsys.common.bean.T_BAC_H;
import nttdm.bsys.common.util.CommonUtils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.util.ValidatorUtils;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.validator.FieldChecks;
import org.apache.struts.validator.Resources;

/**
 * class for BSYSFieldChecksEx
 */
public class BSYSFieldChecksEx extends FieldChecks {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Validate require value for dropdown list True if element to be selected
	 * is other than first element (the label) This is an additional
	 * implementation to Tera's default require check which has not yet
	 * determined the label (first element of dropdown list) as a non-inputted
	 * value.
	 * 
	 * NOTE: The label (first element) in select box must be indexed with 0
	 */
	static final String DROPDOWN_LIST_LABEL_CODE = "";

	static final String DROPDOWN_LIST_LABEL_CODE_1 = "";

	/**
	 * Check Hour
	 * 
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @param validator
	 * @param request
	 * @return boolean
	 */
	public static boolean validateHour(Object bean, ValidatorAction va,
			Field field, ActionMessages errors, Validator validator,
			HttpServletRequest request) {
		String value = ValidatorUtils.getValueAsString(bean,
				field.getProperty());
		boolean valid = true;
		// not empty
		if (CommonUtils.notEmpty(value)) {
			try {
				int hour = Integer.parseInt(value);
				if (hour < 0 || hour > 23) {
					valid = false;
				}
			} catch (NumberFormatException nfe) {
				valid = false;
			}
			// error message
			if (!valid) {
				errors.add(field.getKey(), Resources.getActionMessage(
						validator, request, va, field));
				return false;
			}
		}
		return true;
	}

	/**
	 * Check Minute
	 * 
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @param validator
	 * @param request
	 * @return boolean
	 */
	public static boolean validateMinute(Object bean, ValidatorAction va,
			Field field, ActionMessages errors, Validator validator,
			HttpServletRequest request) {
		String value = ValidatorUtils.getValueAsString(bean,
				field.getProperty());
		boolean valid = true;
		// not empty
		if (CommonUtils.notEmpty(value)) {
			try {
				int hour = Integer.parseInt(value);
				if (hour < 0 || hour > 59) {
					valid = false;
				}
			} catch (NumberFormatException nfe) {
				valid = false;
			}
			// error message
			if (!valid) {
				errors.add(field.getKey(), Resources.getActionMessage(
						validator, request, va, field));
				return false;
			}
		}
		return true;
	}

	/**
	 * Check existed for a directory path
	 * 
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @param validator
	 * @param request
	 * @return boolean
	 */
	public static boolean validateDirectoryExist(Object bean,
			ValidatorAction va, Field field, ActionMessages errors,
			Validator validator, HttpServletRequest request) {
		String value = ValidatorUtils.getValueAsString(bean,
				field.getProperty());
		boolean valid = true;

		// not empty
		if (CommonUtils.notEmpty(value)) {
			File file = new File(value);
			valid = file.exists();

			// error message
			if (!valid) {
				errors.add(field.getKey(), Resources.getActionMessage(
						validator, request, va, field));
				return false;
			}
		}

		return true;
	}

	/**
	 * Check is Required DropdownList
	 * 
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @param validator
	 * @param request
	 * @return boolean
	 */
	public static boolean validateRequiredDropdownList(Object bean,
			ValidatorAction va, Field field, ActionMessages errors,
			Validator validator, HttpServletRequest request) {
		boolean result;

		result = BSYSFieldChecksEx.validateRequired(bean, va, field, errors,
				validator, request);

		// False means always false
		if (result == false) {
			return false;
		}

		// Evaluate some cases including label to be selected
		String value = ValidatorUtils.getValueAsString(bean,
				field.getProperty());

		if (value.equals(DROPDOWN_LIST_LABEL_CODE)
				|| value.equals(DROPDOWN_LIST_LABEL_CODE_1) || value == null) {
			errors.add(field.getKey(),
					Resources.getActionMessage(validator, request, va, field));
			return false;
		}

		return true;
	}

	/**
	 * Validate require value for Checkbox
	 * 
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @param validator
	 * @param request
	 * @return boolean
	 */
	public static boolean validateRequiredCheckbox(Object bean,
			ValidatorAction va, Field field, ActionMessages errors,
			Validator validator, HttpServletRequest request) {
		boolean result = true;
		String value = ValidatorUtils.getValueAsString(bean,
				field.getProperty());
		if (CommonUtils.isEmpty(value)) {
			errors.add(field.getKey(),
					Resources.getActionMessage(validator, request, va, field));
			result = false;
		}
		return result;
	}

	/**
	 * Use for B_BAC module
	 * 
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @param validator
	 * @param request
	 * @return boolean
	 */
	public static boolean validateBBacNewModeBC(Object bean,
			ValidatorAction va, Field field, ActionMessages errors,
			Validator validator, HttpServletRequest request) {
		boolean result = true;
		QueryDAO queryDAO = (QueryDAO) CommonUtils.getBean("queryDAO");
		DynaActionForm form = (DynaActionForm) bean;
		String[] idKeys = (String[]) form.get(field.getProperty());
		String[] billCurr = new String[idKeys.length];
		for (int i = 0; i < idKeys.length; i++) {
			String idKey = idKeys[i];
			String[] splitIdKey = idKey.split("_");
			String idBillAcc = splitIdKey[1];
			HashMap<String, Object> param = new HashMap<String, Object>();
			param.put("idBillAccount", idBillAcc);
			Map<String, Object> map = queryDAO.executeForMap(
					"B_BAC.getBillingAccountInfo", param);
			billCurr[i] = CommonUtils.toString(map.get("BILL_CURRENCY"));
		}
		// Check if the same currency
		for (int i = 0; i < idKeys.length; i++) {
			String billCurrStr = billCurr[i];
			for (int j = 0; j < idKeys.length; j++) {
				if (i != j && !billCurrStr.equals(billCurr[j])) {
					result = false;
					break;
				}
			}
		}
		if (!result) {
			errors.add(field.getKey(),
					Resources.getActionMessage(validator, request, va, field));
		}
		return result;
	}

	/**
	 * Use for B_BAC module
	 * 
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @param validator
	 * @param request
	 * @return boolean
	 */
	public static boolean validateBBacNewModeEC(Object bean,
			ValidatorAction va, Field field, ActionMessages errors,
			Validator validator, HttpServletRequest request) {
		boolean result = true;
		QueryDAO queryDAO = (QueryDAO) CommonUtils.getBean("queryDAO");
		DynaActionForm form = (DynaActionForm) bean;
		String[] idKeys = (String[]) form.get(field.getProperty());
		String[] expCurr = new String[idKeys.length];
		for (int i = 0; i < idKeys.length; i++) {
			String idKey = idKeys[i];
			String[] splitIdKey = idKey.split("_");
			String idBillAcc = splitIdKey[1];
			HashMap<String, Object> param = new HashMap<String, Object>();
			param.put("idBillAccount", idBillAcc);
			Map<String, Object> map = queryDAO.executeForMap(
					"B_BAC.getBillingAccountInfo", param);
			expCurr[i] = CommonUtils.toString(map.get("EXPORT_CURRENCY"));
		}
		// Check if the same export currency
		for (int i = 0; i < idKeys.length; i++) {
			String expCurrStr = expCurr[i];
			for (int j = 0; j < idKeys.length; j++) {
				if (i != j && !expCurrStr.equals(expCurr[j])) {
					result = false;
					break;
				}
			}
		}
		if (!result) {
			errors.add(field.getKey(),
					Resources.getActionMessage(validator, request, va, field));
		}
		return result;
	}

	/**
	 * Use for B_BAC module
	 * 
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @param validator
	 * @param request
	 * @return boolean
	 */
	public static boolean validateBBacNewModeFFR(Object bean,
			ValidatorAction va, Field field, ActionMessages errors,
			Validator validator, HttpServletRequest request) {
		boolean result = true;
		QueryDAO queryDAO = (QueryDAO) CommonUtils.getBean("queryDAO");
		DynaActionForm form = (DynaActionForm) bean;
		String[] idKeys = (String[]) form.get(field.getProperty());
		String[] fixFor = new String[idKeys.length];
		for (int i = 0; i < idKeys.length; i++) {
			String idKey = idKeys[i];
			String[] splitIdKey = idKey.split("_");
			String idBillAcc = splitIdKey[1];
			HashMap<String, Object> param = new HashMap<String, Object>();
			param.put("idBillAccount", idBillAcc);
			Map<String, Object> map = queryDAO.executeForMap(
					"B_BAC.getBillingAccountInfo", param);
			fixFor[i] = CommonUtils.toString(map.get("FIXED_FOREX"));
		}
		// Check if the same fixed forex
		for (int i = 0; i < idKeys.length; i++) {
			String fixForStr = fixFor[i];
			for (int j = 0; j < idKeys.length; j++) {
				if (i != j && !fixForStr.equals(fixFor[j])) {
					result = false;
					break;
				}
			}
		}
		if (!result) {
			errors.add(field.getKey(),
					Resources.getActionMessage(validator, request, va, field));
		}
		return result;
	}

	/**
	 * Use for B_BAC module
	 * 
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @param validator
	 * @param request
	 * @return boolean
	 */
	public static boolean validateBBacNewModeIDE(Object bean,
			ValidatorAction va, Field field, ActionMessages errors,
			Validator validator, HttpServletRequest request) {
		boolean result = true;
		QueryDAO queryDAO = (QueryDAO) CommonUtils.getBean("queryDAO");
		DynaActionForm form = (DynaActionForm) bean;
		String[] idKeys = (String[]) form.get(field.getProperty());
		String[] disExp = new String[idKeys.length];
		for (int i = 0; i < idKeys.length; i++) {
			String idKey = idKeys[i];
			String[] splitIdKey = idKey.split("_");
			String idBillAcc = splitIdKey[1];
			HashMap<String, Object> param = new HashMap<String, Object>();
			param.put("idBillAccount", idBillAcc);
			Map<String, Object> map = queryDAO.executeForMap(
					"B_BAC.getBillingAccountInfo", param);
			disExp[i] = CommonUtils.toString(map.get("IS_DISPLAY_EXP_AMT"));
		}
		// Check if the same display export
		for (int i = 0; i < idKeys.length; i++) {
			String disExpStr = disExp[i];
			for (int j = 0; j < idKeys.length; j++) {
				if (i != j && !disExpStr.equals(disExp[j])) {
					result = false;
					break;
				}
			}
		}
		if (!result) {
			errors.add(field.getKey(),
					Resources.getActionMessage(validator, request, va, field));
		}
		return result;
	}

	   /**
     * Use for B_BAC module
     * 
     * @param bean
     * @param va
     * @param field
     * @param errors
     * @param validator
     * @param request
     * @return boolean
     */
    public static boolean validateBBacNewModeES(Object bean,
            ValidatorAction va, Field field, ActionMessages errors,
            Validator validator, HttpServletRequest request) {
        boolean result = true;
        QueryDAO queryDAO = (QueryDAO) CommonUtils.getBean("queryDAO");
        DynaActionForm form = (DynaActionForm) bean;
        String[] idKeys = (String[]) form.get(field.getProperty());
        String[] espCurr = new String[idKeys.length];
        for (int i = 0; i < idKeys.length; i++) {
            String idKey = idKeys[i];
            String[] splitIdKey = idKey.split("_");
            String idBillAcc = splitIdKey[1];
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idBillAccount", idBillAcc);
            Map<String, Object> map = queryDAO.executeForMap(
                    "B_BAC.getBillingAccountInfo", param);
            espCurr[i] = CommonUtils.toString(map.get("IS_SINGPOST"));
        }
        // Check if the same export currency
        for (int i = 0; i < idKeys.length; i++) {
            String espCurrStr = espCurr[i];
            for (int j = 0; j < idKeys.length; j++) {
                if (i != j && !espCurrStr.equals(espCurr[j])) {
                    result = false;
                    break;
                }
            }
        }
        if (!result) {
            errors.add(field.getKey(),
                    Resources.getActionMessage(validator, request, va, field));
        }
        return result;
    }
	
	/**
	 * Use for B_BAC module
	 * 
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @param validator
	 * @param request
	 * @return boolean
	 */
	public static boolean validatePaymentMethod(Object bean,
			ValidatorAction va, Field field, ActionMessages errors,
			Validator validator, HttpServletRequest request) {
		boolean result = true;
		QueryDAO queryDAO = (QueryDAO) CommonUtils.getBean("queryDAO");
		DynaActionForm form = (DynaActionForm) bean;
		String idCust = (String) form.get("cboCustomerName");
		T_BAC_H headerInfo = (T_BAC_H) form.get("inputHeaderInfo");
		String paymentMethod = headerInfo.getPaymentMethod();
		HashMap<String, Object> m = new HashMap<String, Object>();
		m.put("idCust", idCust);
		HashMap<String, Object> bankInfo = (HashMap<String, Object>) queryDAO
				.executeForMap("B_BAC.getCustBankInfo", m);
		if (paymentMethod.equals("GR")) {
			if (CommonUtils.isEmpty(bankInfo.get("GIRO_ACCT_NO"))) {
				result = false;
			}
		} else if (paymentMethod.equals("CC")) {
			if (CommonUtils.isEmpty(bankInfo.get("CCARD_ACCT_NO"))) {
				result = false;
			}
		}
		if (!result) {
			errors.add(field.getKey(),
					Resources.getActionMessage(validator, request, va, field));
		}
		return result;
	}

	/**
	 * check DateOrder
	 * 
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @param validator
	 * @param request
	 * @return boolean
	 */
	public static boolean validateDateOrder(Object bean, ValidatorAction va,
			Field field, ActionMessages errors, Validator validator,
			HttpServletRequest request) {
		String toDate = ValidatorUtils.getValueAsString(bean,
				field.getProperty());
		String fromDateParam = field.getVarValue("fromDateProperty");
		String fromDate = ValidatorUtils.getValueAsString(bean, fromDateParam);
		String pattern = field.getVarValue("pattern");
		pattern = (StringUtils.isEmpty(pattern)) ? "dd/MM/yyyy" : pattern;
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		try {
			Date _toDate = formatter.parse(toDate);
			Date _fromDate = formatter.parse(fromDate);
			if (_fromDate.after(_toDate)) {
				errors.add(field.getKey(), Resources.getActionMessage(
						validator, request, va, field));
				return false;
			}
		} catch (ParseException e) {
			System.out.println("cannot convert to date properties");
		}
		return true;
	}

	/**
	 * check PairProperty
	 * 
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @param validator
	 * @param request
	 * @return boolean
	 */
	public static boolean validatePairProperty(Object bean, ValidatorAction va,
			Field field, ActionMessages errors, Validator validator,
			HttpServletRequest request) {
		String srcProperty = ValidatorUtils.getValueAsString(bean,
				field.getProperty());
		String pairProperty = field.getVarValue("pairProperty");
		String pairPropertyValue = ValidatorUtils.getValueAsString(bean,
				pairProperty);
		if (!CommonUtils.isEmpty(srcProperty)
				&& CommonUtils.isEmpty(pairPropertyValue)) {
			errors.add(field.getKey(),
					Resources.getActionMessage(validator, request, va, field));
		}
		return true;
	}

	/**
	 * check for m_bank duplicate data
	 * 
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @param validator
	 * @param request
	 * @return
	 */
	public static boolean validateBankCdAndBranchCdDup(Object bean,
			ValidatorAction va, Field field, ActionMessages errors,
			Validator validator, HttpServletRequest request) {

		boolean result = true;
		QueryDAO queryDAO = (QueryDAO) CommonUtils.getBean("queryDAO");
		nttdm.bsys.m_bnk.bean.M_BNKFormBean form = (nttdm.bsys.m_bnk.bean.M_BNKFormBean) bean;
		String bankCode = (String) form.getTbxBankCode();
		String branchCode = (String) form.getTbxBranchCode();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bankCode", bankCode);
		param.put("branchCode", branchCode);
		Integer num = queryDAO.executeForObject("M_BNK.getbankduplicate",
				param, Integer.class);
		if (num > 0) {
			result = false;
		}
		if (!result) {
			errors.add(field.getKey(),
					Resources.getActionMessage(validator, request, va, field));
		}
		return result;
	}
	
	/**
	 * check for m_bank duplicate data in edit page
	 * 
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @param validator
	 * @param request
	 * @return
	 */
	public static boolean validateBankCdAndBranchCdDupEdit(Object bean,
			ValidatorAction va, Field field, ActionMessages errors,
			Validator validator, HttpServletRequest request) {
		
		boolean result = true;
		QueryDAO queryDAO = (QueryDAO) CommonUtils.getBean("queryDAO");
		nttdm.bsys.m_bnk.bean.M_BNKFormBean form = (nttdm.bsys.m_bnk.bean.M_BNKFormBean) bean;
		String bankCode = form.getTbxBankCode();
		String branchCode = form.getTbxBranchCode();
		String idBank = form.getTbxBankID();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bankCode", bankCode);
		param.put("branchCode", branchCode);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list= queryDAO.executeForMapList("M_BNK.getbankduplicateForEdit",
				param);
		if (list.size()==1) {
			HashMap<String,Object> map = (HashMap<String,Object>)list.get(0);
			String id = String.valueOf(map.get("ID_BANK"));
			if(!id.equals(idBank)){
				result = false;
			}
		} else if(list.size()>0){
			result = false;
		}
		
		if (!result) {
			errors.add(field.getKey(),
					Resources.getActionMessage(validator, request, va, field));
		}
		return result;
	}
	
	/**
	 * Use for B_BAC module
	 * 
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @param validator
	 * @param request
	 * @return boolean
	 */
	public static boolean validateBBacNewModeMBP(Object bean,
			ValidatorAction va, Field field, ActionMessages errors,
			Validator validator, HttpServletRequest request) {
		boolean result = true;
		QueryDAO queryDAO = (QueryDAO) CommonUtils.getBean("queryDAO");
		DynaActionForm form = (DynaActionForm) bean;
		String[] idKeys = (String[]) form.get(field.getProperty());
		List<String> idBillAccountList = new ArrayList<String>();
		for (int i = 0; i < idKeys.length; i++) {
			String idKey = idKeys[i];
			String[] splitIdKey = idKey.split("_");
			String idBillAcc = splitIdKey[1];
			idBillAccountList.add(idBillAcc);
		}
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		
		param.put("idBillAccountList", idBillAccountList);
		List<Map<String, Object>> map = queryDAO.executeForMapList("B_BAC.getMultiBillPeriodInfo", param);
		
		Iterator<Map<String, Object>> iterator=map.iterator();
		String tempMultiBillPeriod=null,tempMultiBillPeriod2="";
		while(iterator.hasNext()){
			tempMultiBillPeriod2=(String) iterator.next().get("MULTIBILLPERIOD");
			if(tempMultiBillPeriod!=null&&!tempMultiBillPeriod.equals(tempMultiBillPeriod2)){
				result=false;
				break;
			}
			else{
				tempMultiBillPeriod=tempMultiBillPeriod2;
			}
		}
		if (!result) {
			errors.add(field.getKey(),
					Resources.getActionMessage(validator, request, va, field));
		}
		return result;
	}
}
