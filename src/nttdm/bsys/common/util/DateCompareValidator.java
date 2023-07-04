/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : Common
 * SERVICE NAME   : Date Utility
 * FUNCTION       : Date Validator
 * FILE NAME      : DateCompareValidator.java
 *
 * History
 * 2011/07/27 Tuyen Fixed for error when applying TeraChecking Style
 * 
 **********************************************************/

package nttdm.bsys.common.util;

import java.io.Serializable;
import java.text.*;
import java.util.*;

import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.util.ValidatorUtils;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.Resources;

import javax.servlet.http.HttpServletRequest;

/**
 * Date Validator
 * <ul>
 * <li>Common class to validate date value inputed by user
 * </ul>
 * <br>
 */
public class DateCompareValidator implements Serializable {
	/**
	 * <div>serialVersionUID</div>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * <div>BEGIN_DATE_FIELD_NAME</div>
	 */
	private static final String BEGIN_DATE_FIELD_NAME = "beginDateField";

	/**
	 * <div>DATES_RANGE_INVALID_MSG_KEY</div>
	 */
	private static final String DATES_RANGE_INVALID_MSG_KEY = "eerrors.ERR1SC037";

	/**
	 * Compare the start time and end time are normal
	 * 
	 * @param bean
	 *            Object
	 * @param va
	 *            ValidatorAction
	 * @param field
	 *            Field
	 * @param errors
	 *            ActionMessages
	 * @param request
	 *            HttpServletRequest
	 * 
	 * @return boolean Determine the current time is correct
	 */
	public static boolean validateDates(Object bean, ValidatorAction va,
			Field field, ActionMessages errors, HttpServletRequest request) {
		boolean isValid = true;
		// Fetch the end date value.
		String endDate = ValidatorUtils.getValueAsString(bean,
				field.getProperty());
		// Fetch the start date value from the bean.
		String beginDate = ValidatorUtils.getValueAsString(bean,
				field.getVarValue(BEGIN_DATE_FIELD_NAME));
		// If either of the dates is blank pass the validation
		if (GenericValidator.isBlankOrNull(endDate)
				|| GenericValidator.isBlankOrNull(beginDate)) {
			return isValid;
		}

		int passCode = compareDates(beginDate, endDate);

		// Date range is invalid
		if (passCode == 1) {
			isValid = false;
			va.setMsg(DATES_RANGE_INVALID_MSG_KEY);
			// throw error for end date field
			errors.add(field.getKey(),
					Resources.getActionMessage(request, va, field));
		}

		return isValid;
	}

	/**
	 * Compare the start time and end time are normal
	 * 
	 * @param beginDate
	 *            begin date
	 * @param endDate
	 *            end date
	 * 
	 * @return int 0:beginDate before endDate 1:beginDate after endDate
	 */
	public static int compareDates(String beginDate, String endDate) {
		// default code if the dates are okay
		int passCodeValue = 0;
		// Convert both dates into their calendar objects.
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Calendar begin = new GregorianCalendar();
		Calendar end = new GregorianCalendar();
		try {
			begin.setTime((Date) formatter.parse(beginDate));
			end.setTime((Date) formatter.parse(endDate));
		} catch (ParseException e) {
			// Ignore parse exception
			return passCodeValue;
		}
		if (end.before(begin)) {
			passCodeValue = 1;
		}
		return passCodeValue;
	}
}