/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : B_SSM
 * SERVICE NAME   : B_SSM_S01
 * FUNCTION       : Containing utilities for BLogic processing
 * FILE NAME      : BLogicUtils.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
**********************************************************/

package nttdm.bsys.b_ssm.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nttdm.bsys.b_ssm.s01.data.B_SSM_S01_FieldSet;
import nttdm.bsys.common.util.CommonUtils;

/**
 * @author NTT Data VietNam
 * Class containing utilities for BLogic processing
 */
public class BLogicUtils {

	/**
	 * Coper properties from source to destination
	 * @param destProperties
	 * @param srcProperties
	 */
	public static void copyProperties(HashMap<String, Object> destProperties,
									    HashMap<String, Object> srcProperties) {
		if(destProperties != null && srcProperties != null) {
			for (int i=0; i < srcProperties.size(); i++) {
				String srcPropertyKey = (String) srcProperties.keySet().toArray()[i];
				Object srcPropertyValue = srcProperties.get(srcPropertyKey);
				// Add to dest
				destProperties.put(srcPropertyKey, srcPropertyValue);
			}
		}
	}
	
	/**
	 * Value of empty string
	 * @param str
	 * @param defaultValue
	 * @return String
	 */
	public static String emptyValue(String str, String defaultValue) {
		return str == null || str.trim().equals("") ?
				defaultValue:
				str;
	}
	
	/**
	 * Consider whether list contains item with specific condition
	 * @param list
	 * @param fieldName
	 * @param fieldValue
	 * @return boolean
	 */
	public static boolean isContainItemWith(List<HashMap<String, Object>> list,
											  String conditionFieldName, 
											  String conditionFieldValue) {
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, Object> item = list.get(i);
				String fieldValue = item.get(conditionFieldName) == null?
									null:
									item.get(conditionFieldName).toString();
				if (fieldValue != null && fieldValue.trim().equals(conditionFieldValue)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Consider whether list contains item with specific condition
	 * @param list
	 * @param fieldNames
	 * @param fieldValues
	 * @return boolean
	 */
	public static boolean isContainItemWith(List<HashMap<String, Object>> list,
										    String[] conditionFieldNames, 
										    String[] conditionFieldValues) {
		if (conditionFieldNames == null ||
			conditionFieldNames.length == 0 ||
			conditionFieldValues == null ||
			conditionFieldValues.length == 0) {
			return false;
		}
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, Object> item = list.get(i);
				boolean isFound = false;
				for (int j = 0; j < conditionFieldNames.length; j++ ) {
					String fieldValue = item.get(conditionFieldNames[j]) == null?
										null:
										item.get(conditionFieldNames[j]).toString();
					isFound = (fieldValue != null && fieldValue.trim().equals(conditionFieldValues[j]))?true:false;
					if (!isFound) {
						break;
					}
				}
				if(isFound){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Get item of list with specific condition
	 * @param list
	 * @param fieldName
	 * @param fieldValue
	 * @return boolean
	 */
	public static HashMap<String, Object> getItemWith(List<HashMap<String, Object>> list,
														String conditionFieldName, 
														String conditionFieldValue) {
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, Object> item = list.get(i);
				String fieldValue = item.get(conditionFieldName) == null?
									null:
									item.get(conditionFieldName).toString();
				if (fieldValue != null && fieldValue.trim().equals(conditionFieldValue)) {
					return item;
				}
			}
		}
		return null;
	}
	
	/**
	 * Get item of list with specific condition
	 * @param list
	 * @param fieldNames
	 * @param fieldValues
	 * @return boolean
	 */
	public static HashMap<String, Object> getItemWith(List<HashMap<String, Object>> list,
														String[] conditionFieldNames, 
														String[] conditionFieldValues) {
		if (conditionFieldNames == null ||
			conditionFieldNames.length == 0 ||
			conditionFieldValues == null ||
			conditionFieldValues.length == 0) {
			return null;
		}
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, Object> item = list.get(i);
				boolean isFound = false;
				for (int j = 0; j < conditionFieldNames.length; j++ ) {
					String fieldValue = item.get(conditionFieldNames[j]) == null?
										null:
										item.get(conditionFieldNames[j]).toString();
					isFound = (fieldValue != null && fieldValue.trim().equals(conditionFieldValues[j]))?true:false;
					if (!isFound) {
						break;
					}
				}
				if(isFound){
					return item;
				}
			}
		}
		return null;
	}
	
	/**
	 * Get number from string with regex pattern 
	 * @param str
	 * @param regexPattern
	 * @return int
	 */
	public static int getNumberFromRegexPattern(String str, String regexPattern) {
		int number = 0;
		if (!str.equals("")) {			
	        Pattern regexDigitPattern = Pattern.compile(regexPattern);	      
	        Matcher match = regexDigitPattern.matcher(str);
	        boolean numberSearch = match.find();	      
	        while(numberSearch) {
	        	String numberStr = match.group();
	        	if (numberStr != null && !numberStr.trim().equals("")) {
	        		number = Integer.parseInt(numberStr);
	        		break;
	        	}
	        	numberSearch = match.find();
	        }
		}
		return number;
	}


	/**
	 * Value of empty object
	 * @param objStr
	 * @param defaultValue
	 * @return String
	 */
	public static String emptyValue(Object objStr, String defaultValue) {		
		return objStr == null || objStr.toString().trim().equals("")?
				defaultValue:
				objStr.toString();
	}

	/**
	 * Get tail of string from regex pattern
	 * @param str
	 * @param patternRegexStr
	 * @return String
	 */
	public static String getTailStringFromRegexPattern(String str, String patternRegexStr) {
		String tail = "";
		if (!str.equals("")) {			
	        Pattern regexDigitPattern = Pattern.compile(patternRegexStr);	      
	        Matcher match = regexDigitPattern.matcher(str);
	        boolean groupSearch = match.find();	      
	        while(groupSearch) {	        	
	        	tail = str.substring(match.end());
	        	groupSearch = match.find();
	        }
		}
		return tail;
	}

	/**
	 * Get list of result form srcSet at startPos with quantity
	 * @param srcSet
	 * @param startPos
	 * @param quantity
	 * @return List
	 */
	public static List<HashMap<String, Object>> get(List<HashMap<String, Object>> srcSet,
													 int startPos,
													 int quantity) {
		List<HashMap<String, Object>> destSet = new ArrayList<HashMap<String,Object>>();
		if (srcSet != null && 
			srcSet.size() > 0) {
			startPos = startPos < srcSet.size() ? startPos : 0;
			int endPos = quantity < srcSet.size() - startPos ? 
						 startPos + quantity - 1 :
						 srcSet.size() - 1;
			for (int i = startPos; i <= endPos; i++) {
				destSet.add(srcSet.get(i));
			}
					 
		}
		return destSet;
	}

	/**
	 * List contains item string????
	 * @param list
	 * @param itemStr
	 * @return boolean
	 */
	public static boolean isContainItem(List<String> list, String itemStr) {
		if (list == null || list.size() == 0) {
			return false;
		}
		
		for (int i = 0; i < list.size(); i++) {
			String listItem = list.get(i).toString();
			if (itemStr.trim().equals(listItem)) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Add row no to dataset
	 * @param startIndex 
	 * @param resultSet
	 */
	public static void addRowNoToDataSet(int startIndex, 
								           List<HashMap<String, Object>> resultSet) {
		if (resultSet != null && resultSet.size() > 0) {
			for (int i = 0; i< resultSet.size(); i++) {
				resultSet.get(i).put(B_SSM_S01_FieldSet.FIELD_ROWNO, startIndex + i);
			}
		}
	}

	/**
	 * Get index of string array equaling findValue with specific start position
	 * @param strArray
	 * @param findValue
	 * @param startPos
	 * @return int
	 */
	public static int getIndex(String[] strArray, String findValue, int startPos) {
		if (strArray == null || strArray.length == 0) {
			return -1;
		}
		if (findValue == null) {
			return -1;
		}
		for (int i = startPos; i < strArray.length; i++) {
			if (strArray[i] != null && strArray[i].trim().equals(findValue)) {
				return i;
			}
		}
		return -1;
	}	
	
	/**
	 * (list map)'s property to string
	 * @param list
	 * @param name property name
	 * @return
	 */
	public static String listFirstAndLastProToStr(List<HashMap<String, Object>> list , String name) {
	    String str = "";
	    if (list != null && 0 < list.size()) {
	        if (list.size() == 1){
	            HashMap<String, Object> firstMap = list.get(0);
	            String value = CommonUtils.toString(firstMap.get(name)).trim();
	            str = str.concat(value);
	        } else {
	            HashMap<String, Object> firstMap = list.get(0);
                String firstValue = CommonUtils.toString(firstMap.get(name)).trim();
                HashMap<String, Object> lastMap = list.get(list.size()-1);
                String lastValue = CommonUtils.toString(lastMap.get(name)).trim();
                if (!CommonUtils.isEmpty(firstValue) && !CommonUtils.isEmpty(lastValue)) {
                    str = firstValue.concat(" - ").concat(lastValue);
                } else if (CommonUtils.isEmpty(firstValue) && !CommonUtils.isEmpty(lastValue)) {
                    str = lastValue;
                } else if (!CommonUtils.isEmpty(firstValue) && CommonUtils.isEmpty(lastValue)) {
                    str = firstValue;
                }
	        }
	    }
	    return str ;
	}
	
	/**
	 * remove dulpicate and change to list String
	 * @param list
	 * @param name name remove dulpicate by this name
	 * @return
	 */
	public static List<String> removeDulpicateToListStr(List<HashMap<String, Object>> list, String name) {
	    List<String> listResult = new ArrayList<String>();
	    if (list != null && 0 < list.size()) {
	        for(HashMap<String, Object> map : list) {
	            String value = CommonUtils.toString(map.get(name)).trim();
	            if (!CommonUtils.isEmpty(value)) {
	                listResult.add(value);
	            }
	        }
	    }
	    return listResult;
	}
	
	/**
     * List Object to List String by name
     * @param list
     * @param name
     * @return
     */
    public static List<String> listObjectByNameTolistStr(List<HashMap<String, Object>> list, String name) {
        List<String> listResult = new ArrayList<String>();
        if (list != null && 0 < list.size()) {
            for(HashMap<String, Object> map : list) {
                String value = CommonUtils.toString(map.get(name)).trim();
                listResult.add(value);
            }
        }
        return listResult;
    }
	
	/**
	 * remove dulpicate and change to list String
	 * @param list
	 * @param name remove dulpicate by this name
	 * @param retName return value list by this name
	 * @return
	 */
    public static List<String> removeDulpicateToListStr(List<HashMap<String, Object>> list, String name, String retName) {
        List<String> listResult = new ArrayList<String>();
        if (list != null && 0 < list.size()) {
            List<String> listCompare = new ArrayList<String>();
            for(HashMap<String, Object> map : list) {
                String value = CommonUtils.toString(map.get(name)).trim();
                if (!CommonUtils.isEmpty(value)) {
                    listCompare.add(value);
                    String RetValue = CommonUtils.toString(map.get(retName)).trim();
                    listResult.add(RetValue);
                }
            }
        }
        return listResult;
    }
}
