/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : Customer Plan Management (Validation)
 * SERVICE NAME   : B_CPM_S02
 * FUNCTION       : define validation method for B-CPM
 * FILE NAME      : CustomValidator.java
 * 
 * Copyright c 2011 NTTDATA Corporation
 *
 * History
 * 2011/07/26 [Duong Nguyen] Created
 * 2011/09/13 [Duong Nguyen] fixed B-CPM-S02 #1.22, 2.6, 2.11, 2.13, 2.18, 3.8 & 4.6, 3.5 & 4.3
 * 
**********************************************************/
package nttdm.bsys.b_cpm_en.validator;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import nttdm.bsys.b_cpm_en.dto.CustomerPlan;
import nttdm.bsys.b_cpm_en.dto.CustomerPlanService;
import nttdm.bsys.b_cpm_en.dto.CustomerSubPlan;
import nttdm.bsys.common.util.CommonUtils;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.validator.Resources;


/**
 * CustomValidator.class<br>
 * <ul>
 * <li>Define custom validator for B-CPM</li>
 * </ul>
 * <br>
* @author  NTTData Vietnam
* @version 1.1
*/
public class CustomValidator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -848402629526515414L;
	
	/**
	 * 
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @param validator
	 * @param request
	 * @return
	 */
	public static boolean validateListItemExist(Object bean, ValidatorAction va,
			Field field, ActionMessages errors, Validator validator,
			HttpServletRequest request) {
		CustomerPlanService service = (CustomerPlanService) bean;
		if (service.getSubPlans().size() == 0 && field.getProperty().equals("subPlans")) {
			errors.add(field.getKey(), Resources.getActionMessage(validator, request, va, field));
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @param validator
	 * @param request
	 * @return
	 */
	public static boolean validateMandatory(Object bean, ValidatorAction va,
			Field field, ActionMessages errors, Validator validator,
			HttpServletRequest request) {
		CustomerPlanService service = (CustomerPlanService) bean;
		for (CustomerSubPlan subPlan : service.getSubPlans()) {
			Object value = subPlan.getPropertyByName(field.getProperty()); 
			if (value == null || value.equals("")) {
				errors.add(field.getKey(), Resources.getActionMessage(validator, request, va, field));
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @param validator
	 * @param request
	 * @return
	 */
	public static boolean validateNumber(Object bean, ValidatorAction va,
			Field field, ActionMessages errors, Validator validator,
			HttpServletRequest request) {
		CustomerPlanService service = (CustomerPlanService) bean;
		for (CustomerSubPlan subPlan : service.getSubPlans()) {
			Object value = subPlan.getPropertyByName(field.getProperty()); 
			if (value != null && !value.equals("")) {
				if (!CustomValidator.mask(value, "^\\d([.]\\d)?$")) {
					errors.add(field.getKey(), Resources.getActionMessage(validator, request, va, field));
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @param validator
	 * @param request
	 * @return
	 */
	public static boolean validateInteger(Object bean, ValidatorAction va,
			Field field, ActionMessages errors, Validator validator,
			HttpServletRequest request) {
		CustomerPlanService service = (CustomerPlanService) bean;
		for (CustomerSubPlan subPlan : service.getSubPlans()) {
			Object value = subPlan.getPropertyByName(field.getProperty()); 
			if (value != null && !value.equals("")) {
				if (!CustomValidator.mask(value, "^\\d*$")) {
					errors.add(field.getKey(), Resources.getActionMessage(validator, request, va, field));
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @param validator
	 * @param request
	 * @return
	 */
	public static boolean validateDecimal(Object bean, ValidatorAction va,
			Field field, ActionMessages errors, Validator validator,
			HttpServletRequest request) {
		CustomerPlanService service = (CustomerPlanService) bean;
		for (CustomerSubPlan subPlan : service.getSubPlans()) {
			Object value = subPlan.getPropertyByName(field.getProperty()); 
			if (value != null && !value.equals("")) {
				if (!CustomValidator.mask(value, "^\\d*([.]\\d*)?$")) {
					errors.add(field.getKey(), Resources.getActionMessage(validator, request, va, field));
					return false;
				}
			}
		}
		return true;
	}
	

    /**
     * 
     * @param bean
     * @param va
     * @param field
     * @param errors
     * @param validator
     * @param request
     * @return
     */
    public static boolean validateNegativeDecimal(Object bean, ValidatorAction va,
            Field field, ActionMessages errors, Validator validator,
            HttpServletRequest request) {
        CustomerPlanService service = (CustomerPlanService) bean;
        for (CustomerSubPlan subPlan : service.getSubPlans()) {
            Object value = subPlan.getPropertyByName(field.getProperty());
            if (value != null && !value.equals("")) {
                // Match "0" or "0.0"
                if(CustomValidator.mask(value,"^[0]([.]0*)?$")){
                    continue;
                }
                if (!CustomValidator.mask(value, "^[-]\\d*([.]\\d*)?$")) {
                    errors.add(field.getKey(), Resources.getActionMessage(validator, request, va, field));
                    return false;
                }
            }
        }
        return true;
    }
  
	/**
	 * 
	 * @param bean
	 * @param va
	 * @param field
	 * @param errors
	 * @param validator
	 * @param request
	 * @return
	 */
	public static boolean validateMask(Object bean, ValidatorAction va,
			Field field, ActionMessages errors, Validator validator,
			HttpServletRequest request) {
		CustomerPlanService service = (CustomerPlanService) bean;
		for (CustomerSubPlan subPlan : service.getSubPlans()) {
			Object value = subPlan.getPropertyByName(field.getProperty()); 
			if (value != null && !value.equals("")) {
				String mask = field.getVarValue("mask");
				if (!CustomValidator.mask(value, mask)) {
					errors.add(field.getKey(), Resources.getActionMessage(validator, request, va, field));
					return false;
				}
			}
		}
		return true;
	}
	
	private static boolean mask(Object value, String mask) {
		Pattern pattern = Pattern.compile(mask);
		Matcher matcher = pattern.matcher(value.toString());
		if (!matcher.matches()) {
			return false;
		}
		return true;
	}
	
	public static boolean validatePaymentCurrency(Object bean, ValidatorAction va,
			Field field, ActionMessages errors, Validator validator,
			HttpServletRequest request){
		DynaActionForm form = (DynaActionForm) bean;
		CustomerPlan plan = (CustomerPlan) form.get("customerPlan");
		String defCur = request.getParameter("defCurrency");
		
		if(!defCur.equals(plan.getBillCurrency())){
			if(CommonUtils.isEmpty(plan.getExpGrandTotal()))
				errors.add(field.getKey(), Resources.getActionMessage(validator, request, va, field));
				return false;
		}
		return true;
	}
}
