/**
 * @(#)CommonUtils.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 * History
 * 2011/11/04 [Duoc Nguyen] fix bug #2865
 */
package nttdm.bsys.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Field;
import java.sql.Clob;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.web.codelist.MappedCodeListLoader;
import jp.terasoluna.fw.web.struts.ModuleUtil;
import jp.terasoluna.fw.web.struts.action.GlobalMessageResources;
import nttdm.bsys.c_cmn001.bean.UserAccess;
import nttdm.bsys.common.bean.AuditTrailHeaderBean;
import nttdm.bsys.common.filter.RequestHelperFilter;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import nttdm.bsys.util.ApplicationContextProvider;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.struts.ContextLoaderPlugIn;

/**
 * common utilization class.
 */
public class CommonUtils {
	private static Logger logger = Logger.getLogger(CommonUtils.class);
	private static Map countryMap = null;
	
	public static String MUL_CHAR_STR = "~ ` ! @ # $ % ^ & * ( ) _ -  + = { } [ ] | \\ : ; \" ' < > ? , . /";

    public static final int MAX_COUNT = 1000;
    
	/**
	 * escape SQL in order to repeat with other String
	 * 
	 * @param str
	 * @return String
	 */
	public static String escapeSQL(String str) {
		if (str == null || str.length() == 0) {
			return str;
		}
		String[] chars = new String[4], escape = new String[4];
		chars[0] = "\\";
		escape[0] = "\\\\\\\\";
		chars[1] = "\n";
		escape[1] = "\\\\n";
		chars[2] = "'";
		escape[2] = "''";
		chars[3] = "\r";
		escape[3] = "\\\\r";
		for (int i = 0; i < chars.length; ++i) {
			str = str.replace(chars[i], escape[i]);
		}
		str = str.replace("%", "\\\\%").replace("_", "\\\\_");
		return str;
	}

	/**
	 * escape object convert to other object
	 * 
	 * @param source
	 * @param dest
	 */
	public static void escapeForObject(Object source, Object dest) {
		try {
			Field[] field = source.getClass().getDeclaredFields();
			int actionSize = field.length;
			for (int i = 0; i < actionSize; i++) {
				field[i].setAccessible(true);
				Object value = field[i].get(source);
				if (field[i].get(source) instanceof String) {
					field[i].set(dest, escapeSQL(value.toString()));
				} else {
					if ("serialVersionUID".equals(field[i].getName()) == false) {
						field[i].set(dest, value);
					}
				}
			}
		} catch (SecurityException e) {
			if (logger.isDebugEnabled() == true) {
				logger.debug("Access to fields within this class is denied", e);
			}
		} catch (IllegalArgumentException e) {
			if (logger.isDebugEnabled() == true) {
				logger.debug("Argument is illegal", e);
			}
		} catch (IllegalAccessException e) {
			if (logger.isDebugEnabled() == true) {
				logger.debug("Access to this field is denied", e);
			}
		}
	}

	/**
	 * Check an input string is empty or not.
	 * 
	 * @param strInput
	 *            String class.
	 * @author tiennd created on Nov 25, 2008
	 */
	public static boolean isEmpty(String strInput) {
		if (strInput == null
				|| strInput.trim().equalsIgnoreCase(StringUtils.EMPTY))
			return true;
		else
			return false;
	}

	/**
	 * Check an input long is empty or not.
	 * 
	 * @param longInput
	 *            Long class.
	 * @author tiennd created on Nov 25, 2008
	 * @return boolean
	 */
	public static boolean isEmpty(Long longInput) {
		if (longInput == null || longInput.equals(new Long(0)))
			return true;
		else
			return false;
	}

	/**
	 * Check an input Integer is empty or not.
	 * 
	 * @param intInput
	 *            Integer
	 * @return boolean
	 */
	public static boolean isEmpty(Integer intInput) {
		if (intInput == null || intInput.equals(new Integer(0)))
			return true;
		else
			return false;
	}

	/**
	 * Check an input Float is empty or not.
	 * 
	 * @param floatInput
	 *            Float
	 * @return boolean
	 */
	public static boolean isEmpty(Float floatInput) {
		if (floatInput == null || floatInput.equals(new Float(0.0f)))
			return true;
		else
			return false;
	}

	/**
	 * Check an input object is empty or not.
	 * 
	 * @param object
	 *            Object class.
	 * @author tiennd created on Nov 25, 2008
	 */
	public static boolean isNull(Object object) {
		return (object == null);
	}

	/**
	 * Check an input Double is empty or not.
	 * 
	 * @param param
	 * @return
	 */
	public static boolean isEmpty(Double param) {
		if (param == null || param.equals(new Double(0d)))
			return true;
		else
			return false;
	}

	/**
	 * Check an input Collection is empty or not.
	 * 
	 * @param c
	 *            Collection
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	public static boolean isEmpty(Collection c) {
		if (isNull(c))
			return true;

		return c.size() == 0;
	}

	/**
	 * merge many object to a String
	 * 
	 * @param delim
	 *            String
	 * @param objects
	 *            Object
	 * @return String
	 */
	public static String join(String delim, Object... objects) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < objects.length; i++) {
			if (i > 0)
				sb.append(delim);
			sb.append(toString(objects[i]));
		}
		return sb.toString();
	}

	/**
	 * merge many object to a String
	 * 
	 * @param objects
	 *            Object
	 * @return String
	 */
	public static String join(Object... objects) {
		return CommonUtils.join("", objects);
	}

	/**
	 * overriding toString method
	 * 
	 * @param o
	 *            Object
	 * @return String
	 */
	public static String toString(Object o) {
		if (o == null)
			return "";
		return o.toString();
	}

	/**
	 * check the Objects is empty or not
	 * 
	 * @param objects
	 *            Object
	 * @return boolean
	 */
	public static boolean anyNotEmpty(Object... objects) {
		for (Object obj : objects) {
			if (!isEmpty(obj))
				return true;
		}
		return false;
	}

	/**
	 * check the Objects is empty or not
	 * 
	 * @param objects
	 *            Object
	 * @return boolean
	 */
	public static boolean isEmpty(Object... objects) {
		for (Object obj : objects)
			if (obj != null && !"".equals(obj))
				return false;
		return true;
	}

	/**
	 * check the objects is empty or not
	 * 
	 * @param objects
	 *            Object
	 * @return boolean
	 */
	public static boolean notEmpty(Object... objects) {
		for (Object obj : objects)
			if (obj == null || "".equals(obj))
				return false;
		return true;
	}

	/**
	 * Expand string as long as you defined
	 * 
	 * @param str
	 *            String
	 * @param length
	 *            int
	 * @param scalable
	 *            boolean
	 * @return
	 */
	public static String nstring(String str, int length, boolean scalable) {
		if (!scalable && str.length() > length)
			str = str.substring(0, length);

		return str + CommonUtils.str(" ", length - str.length());
	}

	/**
	 * Expand string as long as you defined
	 * 
	 * @param str
	 * @param times
	 * @return
	 */
	public static String str(String str, int times) {
		String result = "";
		for (int i = 0; i < times; i++)
			result += str;
		return result;
	}

	/**
	 * check the Object is Integer or not
	 * 
	 * @param number
	 *            Object
	 * @return boolean
	 */
	public static boolean isInteger(Object number) {
		String expression = "^[-+]?[0-9]*?[0-9]+$";
		CharSequence inputStr = toString(number);
		Pattern pattern = Pattern.compile(expression);
		Matcher matcher = pattern.matcher(inputStr);
		return matcher.matches();
	}

	/**
	 * test the object compare to other data
	 * 
	 * @param number
	 *            Object
	 * @param min
	 *            int
	 * @param max
	 *            int
	 * @return boolean
	 */
	public static boolean between(Object number, int min, int max) {
		if (CommonUtils.isInteger(number)) {
			int value = Integer.parseInt(toString(number));
			return value >= min && value <= max;
		}
		return false;
	}

	/**
	 * Use the Date convert String type according to DateFormat
	 * 
	 * @param date
	 * @param format
	 * @return String
	 */
	public static String formatDate(Object date, DateFormat format) {
		if (date == null)
			return "";
		return format.format(date);
	}

	/**
	 * Format date with given pattern.
	 * 
	 * @param date
	 *            Date
	 * @param format
	 *            format pattern
	 * @return formatted date string
	 */
	public static String formatDate(Object date, String format) {
		return formatDate(date, new SimpleDateFormat(format));
	}

	/**
	 * Format Calendar with given pattern.
	 * 
	 * @param date
	 *            Calendar
	 * @param format
	 *            format pattern
	 * @return formatted Calendar string
	 */
	public static String formatDate(Calendar date, String format) {
		return formatDate(date.getTime(), format);
	}

	/**
	 * Format date string to another given pattern.
	 * 
	 * @param strDate
	 *            Date String
	 * @param fromFormat
	 *            the current date format pattern
	 * @param toFormat
	 *            new date format pattern
	 * @return formatted Calendar string
	 */
	public static String formatDate(String strDate, String fromFormat,
			String toFormat) throws ParseException {
		SimpleDateFormat sdfSource = new SimpleDateFormat(fromFormat);
		SimpleDateFormat sdfDestination = new SimpleDateFormat(toFormat);
		return sdfDestination.format(sdfSource.parse(strDate));
	}

	/**
	 * Use the String convert Date type
	 * 
	 * @param d
	 *            Day
	 * @param M
	 *            Month
	 * @param yyyy
	 *            Year
	 * @return Date
	 */
	public static Date toDate(Object d, Object M, Object yyyy) {
		String dd = CommonUtils.toString(d);
		String MM = CommonUtils.toString(M);
		if (dd.length() < 2)
			dd = "0" + dd;
		if (MM.length() < 2)
			MM = "0" + MM;
		return toDate(dd + MM + yyyy, "ddMMyyyy");
	}

	/**
	 * Format object to Date with given format pattern, if format fails return
	 * default value.
	 * 
	 * @param date
	 *            Date
	 * @param format
	 *            format pattern
	 * @param def
	 *            default value
	 * @return Date
	 */
	public static Date toDate(Object date, DateFormat format, Date def) {
		try {
			if (date instanceof Date)
				return (Date) date;
			format.setLenient(false);
			return format.parse(toString(date));
		} catch (ParseException e) {
			// e.printStackTrace();
			return def;
		}
	}

	/**
	 * Format object to Date with given format pattern.
	 * 
	 * @param date
	 *            Date object
	 * @param format
	 *            date format pattern
	 * @return Date
	 */
	public static Date toDate(Object date, DateFormat format) {
		return toDate(date, format, null);
	}

	/**
	 * Format object to Date with default format pattern.
	 * 
	 * @param date
	 *            Date object
	 * @return Date
	 */
	public static Date toDate(Object date) {
		return toDate(date, new SimpleDateFormat());
	}

	/**
	 * Format object to Date with given format pattern.
	 * 
	 * @param date
	 *            Date object
	 * @param format
	 *            format pattern
	 * @return Date
	 */
	public static Date toDate(Object date, String format) {
		return toDate(date, new SimpleDateFormat(format));
	}

	/**
	 * use the replacement to replace subString the match pattern
	 * 
	 * @param obj
	 * @param pattern
	 * @param replacement
	 * @return String
	 */
	public static String replace(Object obj, Object pattern, Object replacement) {
		return toString(obj).replaceAll(toString(pattern),
				toString(replacement));
	}

	/**
	 * Try to cast class type to T
	 * 
	 * @param <T>
	 * @param o
	 * @param clazz
	 * @param defaultValue
	 * @return T
	 */
	public static <T> T tryToCast(Object o, Class<T> clazz, T defaultValue) {
		if (o == null)
			return defaultValue;

		return clazz.cast(o);
	}

	/**
	 * Cast object to given type.
	 * 
	 * @param <T>
	 * @param o
	 * @param clazz
	 * @return
	 */
	public static <T> T tryToCast(Object o, Class<T> clazz) {
		return tryToCast(o, clazz, null);
	}

	/**
	 * Put the Object into a Integer
	 * 
	 * @param val
	 * @param defaultValue
	 * @return Integer
	 */
	public static Integer toInteger(Object val, Integer defaultValue) {
		// without commas, decimals
		if (val != null) {
			val = replaceAll(val, ",+|\\.[0-9]+$", "");
		}

		try {
			return Integer.valueOf(toString(val));
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * Convert object to Integer
	 * 
	 * @param val
	 * @return
	 */
	public static Integer toInteger(Object val) {
		return toInteger(val, 0);
	}

	/**
	 * Put the Object into a Long
	 * 
	 * @param val
	 * @param defaultValue
	 * @return Long
	 */
	public static Long toLong(Object val, Long defaultValue) {
		// without commas, decimals
		if (val != null) {
			val = replaceAll(val, ",+|\\.[0-9]+$", "");
		}

		try {
			return Long.valueOf(toString(val));
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public static Long toLong(Object val) {
		return toLong(val, 0L);
	}

	/**
	 * Put the Object into a Double<br>
	 * 
	 * @param val
	 * @param defaultValue
	 * @return Double
	 */
	public static Double toDouble(Object val, Double defaultValue) {
		// without commas, decimals
		if (val != null) {
			val = replaceAll(val, ",+", "");
		}

		try {
			return Double.valueOf(toString(val));
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * Convert object to Double.
	 * 
	 * @param val
	 * @return
	 */
	public static Double toDouble(Object val) {
		return toDouble(val, 0D);
	}

	/**
	 * Put the Object into a Float
	 * 
	 * @param val
	 * @param defaultValue
	 * @return Float
	 */
	public static Float toFloat(Object val, Float defaultValue) {
		// without commas, decimals
		if (val != null) {
			val = replaceAll(val, ",+", "");
		}

		try {
			return Float.valueOf(toString(val));
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * Convert object to Float.
	 * 
	 * @param val
	 * @return
	 */
	public static Float toFloat(Object val) {
		return toFloat(val, 0F);
	}

	/**
	 * use you user-defined to replace the String
	 * 
	 * @param o
	 * @param pattern
	 * @param replacement
	 * @return String
	 */
	public static String replaceAll(Object o, Pattern pattern,
			String replacement) {
		String s = toString(o);
		Matcher m = pattern.matcher(s);
		return m.replaceAll(replacement);
	}

	public static String replaceAll(Object o, String regex, String replacement) {
		String s = toString(o);
		Matcher m = Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(s);
		try {
			s = m.replaceAll(replacement);
		} catch (Exception e) {
			s = m.replaceAll("%s");
			return String.format(s, replacement);
		}
		return s;
	}

	/**
	 * matching "\\{" + i + "\\}"
	 * 
	 * @param str
	 * @param holders
	 * @return String
	 */
	public static String placeHolders(Object str, Object... holders) {
		String s = toString(str);
		for (int i = 0; i < holders.length; i++) {
			s = replaceAll(s, "\\{" + i + "\\}", toString(holders[i]));
		}
		return s;
	}

	/**
	 * get the Column index
	 * 
	 * @param column
	 *            : A, B, AA, AB ...
	 * @return column index 0, 1, 26, 27
	 */
	public static int getColumnIndex(String column) {
		int x = 0;
		column = StringUtils.upperCase(column);
		for (int i = 0; i < column.length(); i++) {
			char c = column.charAt(i);
			x += (i * 26) + (c - 'A');
		}
		return x;
	}

	/**
	 * get CellContentFromExcelFile
	 * 
	 * @param currentRow
	 * @param cell
	 * @return
	 */
	public static String getCellContentFromExcelFile(HSSFRow currentRow,
			HSSFCell cell) {
		if (cell != null) {
			switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_STRING:
				return cell.getRichStringCellValue().getString();
			case HSSFCell.CELL_TYPE_NUMERIC:
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					return parseToString(cell.getDateCellValue(), "dd/MM/yyyy");
				} else {
					return cell.getNumericCellValue() + "";
				}
			case HSSFCell.CELL_TYPE_BOOLEAN:
				return cell.getBooleanCellValue() + "";
			case HSSFCell.CELL_TYPE_FORMULA:
				return cell.getCellFormula();
			default:
				return "";
			}
		} else {
			return "";
		}
	}

	/**
	 * The Date into a String type
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String parseToString(java.util.Date date, String pattern) {
		SimpleDateFormat simplDate = new SimpleDateFormat(pattern);
		String str = StringUtils.EMPTY;
		try {
			str = simplDate.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	public static java.sql.Date parseToDate(String date, String pattern) {
        if (isEmpty(date) || isEmpty(pattern)) {
            return null;
        }
	    SimpleDateFormat simplDate = new SimpleDateFormat(pattern);
		java.sql.Date retDate = null;
		try {
			Date dateUtil = simplDate.parse(date);
			retDate = new java.sql.Date(dateUtil.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (java.sql.Date) retDate;
	}

	/**
	 * Use beanId get Bean
	 * 
	 * @param beanId
	 * @return
	 */
	public static Object getBean(String beanId) {
		HttpServletRequest request = RequestHelperFilter.getServletRequest();
		String attName = ContextLoaderPlugIn.SERVLET_CONTEXT_PREFIX
				+ ModuleUtil.getPrefix(request);
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		WebApplicationContext wac = (WebApplicationContext) context
				.getAttribute(attName);
		return wac.getBean(beanId);
	}

	/**
	 * get the value of info.IDBatchLogin from application-messages.properties<br>
	 * 
	 * @return String
	 */
	public static String getBatchUser() {
		String returnValue = "";
		Object value = GlobalMessageResources.getInstance().getMessage(
				"info.IDBatchLogin");
		if (value != null) {
			returnValue = value.toString();
		}
		return returnValue;
	}

	/**
	 * get the user access rights from List
	 * 
	 * @param list
	 * @param id_sub_module
	 * @return
	 */
	public static String getAccessRight(List<UserAccess> list,
			String id_sub_module) {
		String returnValue = "";
		for (UserAccess userAccess : list) {
			if (userAccess.getId_sub_module().equals(id_sub_module)) {
				returnValue = userAccess.getAccess_type();
				break;
			}
		}
		return returnValue;
	}

	/**
	 * get the user access rights from BillingSystemUserValueObject
	 * 
	 * @param uvo
	 * @param id_sub_module
	 * @return
	 */
	public static String getAccessRight(BillingSystemUserValueObject uvo,
			String id_sub_module) {
		String returnValue = "";
		if (uvo != null && uvo.getUser_access() != null) {
			for (UserAccess userAccess : uvo.getUser_access()) {
				if (userAccess.getId_sub_module().equals(id_sub_module)) {
					returnValue = userAccess.getAccess_type();
					break;
				}
			}
		}
		return returnValue;
	}

	/**
	 * check time is in interval use for Batch Process
	 * 
	 * @param baseDate
	 *            dd/MM/yyyy HH:mm:ss
	 * @param milInterval
	 * @return
	 */
	public static boolean isTimeInterval(String baseDate, long milInterval) {
		long currentDate = Calendar.getInstance().getTimeInMillis();
		DateFormat dfm = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		try {
			Date updateDate = dfm.parse(baseDate);
			long updateDateLong = updateDate.getTime();
			if (currentDate - updateDateLong > milInterval) {
				return true;
			} else {
				return false;
			}
		} catch (ParseException e1) {
			return false;
		}
	}

	public static String getUserName(QueryDAO queryDAO, String idUser,
			String replace) {
		String userName = replace;
		try {
			userName = queryDAO.executeForObject("SELECT.BSYS.USER.SQL001",
					idUser, String.class);
			if (userName == null)
				userName = replace;
		} catch (Exception e) {
			userName = replace;
		}
		return userName;
	}

	public static String getSectionDesc(QueryDAO queryDAO, String section_no,
			String replace) {
		String section_desc = replace;
		try {
			section_desc = queryDAO.executeForObject(
					"SELECT.BSYS.UTILS.SQL002", section_no, String.class);
			if (section_desc == null)
				section_desc = replace;
		} catch (Exception e) {
			section_desc = replace;
		}
		return section_desc;
	}

	public static String getApprComment(QueryDAO queryDAO, String idRef,
			String section_no, String replace) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("idRef", idRef);
		parameter.put("section_no", section_no);
		String apprComment = replace;
		try {
			apprComment = queryDAO.executeForObject("SELECT.BSYS.UTILS.SQL003",
					parameter, String.class);
			if (apprComment == null)
				apprComment = replace;
		} catch (Exception e) {
			apprComment = replace;
		}
		return apprComment;
	}

	/**
	 * 
	 * @param queryDAO
	 * @return
	 */
	public static Integer getSystemLength(QueryDAO queryDAO) {
		Integer length = queryDAO.executeForObject("SELECT.BSYS.UTILS.SQL005",
				null, Integer.class);
		return length;
	}

	/**
	 * Format String with ... if its length is longer than given length.
	 * 
	 * @param source
	 * @param length
	 *            given length
	 * @return
	 */
	public static Object formatString(Object source, int length) {
		if (source == null) {
			return source;
		}
		if (source.toString().length() > length) {
			source = source.toString().subSequence(0, length - 1) + "...";
		}
		return source;
	}

	/**
	 * insert log record into M_AUDIT_TRAIL_H
	 * 
	 * @param idUser
	 * @param idModule
	 * @param idSubModule
	 * @param reference
	 * @param status
	 * @param action
	 * @return Integer
	 */
	public static Integer auditTrailBegin(String idUser, String idModule,
			String idSubModule, String reference, String status, String action) {
		AuditTrailHeaderBean auditHeader = new AuditTrailHeaderBean();
		auditHeader.setIdUser(idUser);
		auditHeader.setIdModule(idModule);
		auditHeader.setIdSubModule(idSubModule);
		auditHeader.setReference(reference);
		auditHeader.setStatus(status);
		auditHeader.setAction(action);
		ApplicationContext context = ApplicationContextProvider
				.getApplicationContext();
		G_ADT_P01 audit = (G_ADT_P01) context.getBean("G_ADT_P01");
		return audit.writeLog(auditHeader);
	}

	/**
	 * Update log record into M_AUDIT_TRAIL_H
	 * 
	 * @param auditHeader
	 * @return int
	 */
	public static int auditTrailUpdate(AuditTrailHeaderBean auditHeader) {
		ApplicationContext context = ApplicationContextProvider
				.getApplicationContext();
		G_ADT_P01 audit = (G_ADT_P01) context.getBean("G_ADT_P01");
		return audit.updateLog(auditHeader);
	}

	/**
	 * Delete log record into M_AUDIT_TRAIL_H
	 * 
	 * @param idAudit
	 */
	public static void auditTrailEnd(Integer idAudit) {
		auditTrailEnd(idAudit, false);
	}

	public static void auditTrailEnd(Integer idAudit, boolean emptyRollback) {
		ApplicationContext context = ApplicationContextProvider
				.getApplicationContext();
		G_ADT_P01 audit = (G_ADT_P01) context.getBean("G_ADT_P01");
		audit.endLog(idAudit, emptyRollback);
	}

	/**
	 * Get country name by country code.
	 * 
	 * @param countryCode
	 * @return country name
	 */
	public static String getCountry(Object countryCode) {
		if (countryMap == null) {
			ApplicationContext context = ApplicationContextProvider
					.getApplicationContext();
			MappedCodeListLoader currencyCodeList = (MappedCodeListLoader) context
					.getBean("LIST_COUNTRY");
			countryMap = currencyCodeList.getCodeListMap();
		}
		if (countryMap.containsKey(countryCode))
			return countryMap.get(countryCode).toString().trim();
		return "";
	}

	/**
	 * ADDRESS4 = ZIP_CODE + COUNTRY (1) <br>
	 * ADDRESS4 = ZIP_CODE + COUNTRY_CODE (2)<br>
	 * try to parse COUNTRY_CODE(2) to COUNTRY(1)
	 */
	public static void fixAddress4(Map<String, Object> info, String fieldName) {
		if (!toString(info.get(fieldName)).isEmpty()) {
			String add4 = toString(info.get(fieldName)).trim();
			if (add4.length() >= 2) {
				String country = getCountry(add4.substring(add4.length() - 2));
				if (!country.isEmpty()) {
					add4 = add4.substring(0, add4.length() - 2) + country;
					info.put(fieldName, add4);
				}
			}
		}
	}
	
	/**
	 * ADDRESS4 = ZIP_CODE + COUNTRY (1) <br>
	 * ADDRESS4 = ZIP_CODE + COUNTRY_CODE (2)<br>
	 * try to parse COUNTRY_CODE(2) to COUNTRY(1)
	 * ADDRESS4 = COUNTRY+ZIP_CODE
	 */
	public static void fixAddress4n(Map<String, Object> info, String fieldName) {
		if (!toString(info.get(fieldName)).isEmpty()) {
			String add4 = toString(info.get(fieldName)).trim();
			if (add4.length() >= 2) {
				String country = getCountry(add4.substring(add4.length() - 2));
				if (!country.isEmpty()) {
					add4 = country + " " + add4.substring(0, add4.length() - 2).trim();
					info.put(fieldName, add4);
				}
			}
		}
	}
	
	public static void fixAddress4(Map<String, Object> info) {
		fixAddress4(info, "ADDRESS4");
	}
	
	/**
     * get code name by code value from code map list
     * @param codeMapListName
     * @param codeValue
     * @return
     */
    @SuppressWarnings("unchecked")
    public static String getCodeMapListNameByValue(String codeMapListName, String codeValue) {
        String codeName = "";
        ApplicationContext context = ApplicationContextProvider.
            getApplicationContext();
        MappedCodeListLoader codeMapList = (MappedCodeListLoader) 
            context.getBean(codeMapListName);
        Map<String, Object> codeMap = codeMapList.getCodeListMap();
        if (codeMap.containsKey(codeValue)){
            codeName = codeMap.get(codeValue).toString();
        }
        return codeName;
    }
    
    /**
     * get English month short name by value
     * @param month Month value, 1 for January
     * @return
     */
    public static String getShortMonthName(int month) {
        String enMM = "";
        if (1 == month) {
            enMM = "Jan";
        } else if (2 == month) {
            enMM = "Feb";
        } else if (3 == month) {
            enMM = "Mar";
        } else if (4 == month) {
            enMM = "Apr";
        } else if (5 == month) {
            enMM = "May";
        } else if (6 == month) {
            enMM = "Jun";
        } else if (7 == month) {
            enMM = "Jul";
        } else if (8 == month) {
            enMM = "Aug";
        } else if (9 == month) {
            enMM = "Sep";
        } else if (10 == month) {
            enMM = "Oct";
        } else if (11 == month) {
            enMM = "Nov";
        } else if (12 == month) {
            enMM = "Dec";
        }
        return enMM;
    }
    /**
     * IN MAX_RESULT
     * 
     * @param condList
     *            
     * @return
     */
    public static List<List<String>> createInCondition(List<String> condList) {

        List<List<String>> retList = new ArrayList<List<String>>();
        if(condList!=null && 0<condList.size()) {
            int maxCount = (condList.size() + MAX_COUNT - 1) / MAX_COUNT;
            List<String> oneList = new ArrayList<String>();
            for (int loopCount = 0; loopCount < maxCount; loopCount++) {
                if (loopCount > 0) {
                    retList.add(oneList);
                    oneList = new ArrayList<String>();
                }

                int fromCount = MAX_COUNT * loopCount;
                int toCount =
                    (MAX_COUNT * (loopCount + 1) < condList.size() ? MAX_COUNT
                        * (loopCount + 1) : condList.size());
                for (int loopCount2 = fromCount; loopCount2 < toCount; loopCount2++) {
                    oneList.add(condList.get(loopCount2));
                }
            }
            retList.add(oneList);
        }
        
        return retList;
    }
    
    /**
     * IN MAX_RESULT
     * 
     * @param condList
     *            
     * @param condString
     *            
     * @return
     */
    public static String createInCondition(List<String> condList,
            String condString) {
        StringBuilder conditionResults = new StringBuilder();
        conditionResults.append("(");
        int maxCount = (condList.size() + MAX_COUNT - 1) / MAX_COUNT;
        for (int loopCount = 0; loopCount < maxCount; loopCount++) {
            if (loopCount > 0) {
                conditionResults.append(" OR ");
            }
            conditionResults.append(" ").append(condString).append(" IN (");

            int fromCount = MAX_COUNT * loopCount;
            int toCount =
                (MAX_COUNT * (loopCount + 1) < condList.size() ? MAX_COUNT
                    * (loopCount + 1) : condList.size());
            for (int loopCount2 = fromCount; loopCount2 < toCount; loopCount2++) {
                if (loopCount2 > fromCount) {
                    conditionResults.append(",");
                }
                conditionResults.append("'").append(
                    condList.get(loopCount2).replace("'", "''").replace(
                        "\\",
                        "\\\\")).append("'");
            }
            conditionResults.append(")");
        }
        conditionResults.append(")");
        return conditionResults.toString();
    }
    
    public static Clob stringToClob(String str) {  
        if (null == str)  
            return null;  
        else {  
            try {  
                java.sql.Clob c = new javax.sql.rowset.serial.SerialClob(str  
                        .toCharArray());  
                return c;  
            } catch (Exception e) {  
                return null;  
            }  
        }  
    }
    public static String clobReaderToString(Reader is) {
        String reString = "";
        BufferedReader br = new BufferedReader(is);
        String s = null;
        try {
            s = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuffer sb = new StringBuffer();
        while (s != null) {
            sb.append(s);
            try {
                s = br.readLine();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        reString = sb.toString();
        return reString;
    }
    
    /**
     * check is mul character,true:yes,false:no
     * @param str
     * @return
     */
    public static boolean isMulChar(String str) {
        boolean flag=false;
        if (str!=null && !"".equals(str)) {
            if(!str.matches("^[0-9a-zA-Z\r\n ~`!@#$%^&*()-_+=\\{}|;':\",./<>?]*$")){
                flag=true;
            }
        }
        return flag;
    }
    /**
     * padding right space
     * @param str
     * @param len
     * @return
     */
    public static String paddingRightSpace(String str, int len) {
        StringBuffer sb = new StringBuffer();
        str = CommonUtils.toString(str);
        sb.append(str);
        for(int i=str.length();i<len;i++) {
            sb.append(" ");
        }
        return sb.toString();
    }
}