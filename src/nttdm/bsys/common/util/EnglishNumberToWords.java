/**
 * @(#)EnglishNumberToWords.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.util;

import java.text.DecimalFormat;

/**
 * Handle conversion between numeric and string
 * 
 * @author leonzh
 */
public class EnglishNumberToWords {
	/**
	 * <div>tensNames</div>
	 */
	private static final String[] tensNames = { "", " Ten", " Twenty",
			" Thirty", " Forty", " Fifty", " Sixty", " Seventy", " Eighty",
			" Ninety" };
	/**
	 * <div>numNames</div>
	 */
	private static final String[] numNames = { "", " One", " Two", " Three",
			" Four", " Five", " Six", " Seven", " Eight", " Nine", " Ten",
			" Eleven", " Twelve", " Thirteen", " Fourteen", " Fifteen",
			" Sixteen", " Seventeen", " Eighteen", " Nineteen" };
	/**
	 * <div>MAX_NUMBER</div>
	 */
	private static final double MAX_NUMBER = 999999999999999.99;
	/**
	 * <div>CANNOT_CONVERT</div>
	 */
	private static final String CANNOT_CONVERT = "Can not read";
	/**
	 * <div>DECIMAL_PART_CONNECTOR1</div>
	 */
	private static final String DECIMAL_PART_CONNECTOR1 = " And Cents ";
	/**
	 * <div>DECIMAL_PART_CONNECTOR2</div>
	 */
	private static final String DECIMAL_PART_CONNECTOR2 = "Cents ";
	
	/**
	 * <div>convert numeric to string,but numeric must less than one thousand</div>
	 * 
	 * @param number
	 *            one number to convert string
	 * 
	 * @return String the nubmer to convert string
	 */
	private static String convertLessThanOneThousand(int number) {
		String soFar;

		if (number % 100 < 20) {
			soFar = numNames[number % 100];
			number /= 100;
		} else { 
			soFar = numNames[number % 10];
			number /= 10;

			soFar = tensNames[number % 10] + soFar;
			number /= 10;
		}
		if (number == 0)
			return soFar;
		return numNames[number] + " Hundred" + soFar;
	}
	
	/**
	 * <div>convert long numeric to string</div>
	 * 
	 * @param number
	 *            a long number
	 * 
	 * @return String the nubmer to convert string
	 */
	public static String convert(long number) {
		// 0 to 999 999 999 999 999
		if (number == 0) {
			return "Zero";
		}

		String snumber = Long.toString(number);

		// pad with "0"
		String mask = "000000000000000";
		DecimalFormat df = new DecimalFormat(mask);
		snumber = df.format(number);

		// XXXnnnnnnnnnnnn
		int trillions = Integer.parseInt(snumber.substring(0, 3));
		// nnnXXXnnnnnnnnn
		int billions = Integer.parseInt(snumber.substring(3, 6));
		// nnnnnnXXXnnnnnn
		int millions = Integer.parseInt(snumber.substring(6, 9));
		// nnnnnnnnnXXXnnn
		int hundredThousands = Integer.parseInt(snumber.substring(9, 12));
		// nnnnnnnnnnnnXXX
		int thousands = Integer.parseInt(snumber.substring(12, 15));

		String tradTrillions;
		switch (trillions) {
		case 0:
			tradTrillions = "";
			break;
		case 1:
			tradTrillions = convertLessThanOneThousand(trillions) + " Trillion ";
			break;
		default:
			tradTrillions = convertLessThanOneThousand(trillions) + " Trillion ";
		}
		String result = tradTrillions;
		
		String tradBillions;
		switch (billions) {
		case 0:
			tradBillions = "";
			break;
		case 1:
			tradBillions = convertLessThanOneThousand(billions) + " Billion ";
			break;
		default:
			tradBillions = convertLessThanOneThousand(billions) + " Billion ";
		}
		result = result + tradBillions;

		String tradMillions;
		switch (millions) {
		case 0:
			tradMillions = "";
			break;
		case 1:
			tradMillions = convertLessThanOneThousand(millions) + " Million ";
			break;
		default:
			tradMillions = convertLessThanOneThousand(millions) + " Million ";
		}
		result = result + tradMillions;

		String tradHundredThousands;
		switch (hundredThousands) {
		case 0:
			tradHundredThousands = "";
			break;
		case 1:
			tradHundredThousands = "One Thousand ";
			break;
		default:
			tradHundredThousands = convertLessThanOneThousand(hundredThousands)
					+ " Thousand ";
		}
		result = result + tradHundredThousands;

		String tradThousand;
		tradThousand = convertLessThanOneThousand(thousands);
		result = result + tradThousand;

		// remove extra spaces!
		result = result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
		if(result.length() > 1)
			return result.substring(0, 1).toUpperCase() + result.substring(1);
		else
			return result.toUpperCase();
	}
	
	/**
	 * Convert Double value to String.
	 * 
	 * @param dNumber Double value
	 * @return String value of double
	 */
	@Deprecated
	public static String convert(Double dNumber) {		
		String result = CANNOT_CONVERT;
		if( dNumber > MAX_NUMBER ){			
			return result;
		}
		long lPart = dNumber.longValue();
		int dPart = (int)((dNumber - lPart)*100);
		
		if( dPart > 0 ){
			if( lPart > 0 ){
				result = convert(lPart).trim() + DECIMAL_PART_CONNECTOR1 + convertLessThanOneThousand(dPart).trim();
			}else {
				result = DECIMAL_PART_CONNECTOR2 + convertLessThanOneThousand(dPart).trim();
			}
		} else{
			result = convert(lPart);
		}
		return result;
	}
	
	/**
	 * <div>convert string Number to string</div>
	 * 
	 * @param strNumber
	 *            a string number
	 * 
	 * @return String the strNumber to convert string
	 */
	public static String convert(String strNumber) {		
		if(strNumber == null || strNumber.trim().equals("")) strNumber = "0";
		String result = CANNOT_CONVERT;
		String strRegex = "^([0-9]+)?(\\.[0-9]+)?$"; 
		if( !strNumber.matches(strRegex) ){			
			return result;
		}
		if( (new Double(strNumber)) > MAX_NUMBER ){
			return result;
		}
		int decPos = strNumber.indexOf(".");		
		if( decPos == -1 ){
			result = convert(Long.parseLong(strNumber));
		}else {
			String strlPart = strNumber.substring(0, decPos);
			String strdPart = strNumber.substring(decPos+1);
			if( strdPart.length() > 2 ){
				strdPart = strdPart.substring(0,2);
			}
			long lPart = Long.parseLong(strlPart);
			int dPart = Integer.parseInt(strdPart);
			if( strdPart.length() < 2 ){
				dPart = dPart * 10;
			}			
			if( dPart > 0 ){
				if( lPart > 0 ){
					result = convert(lPart).trim() + DECIMAL_PART_CONNECTOR1 + convertLessThanOneThousand(dPart).trim();
				}else {
					result = DECIMAL_PART_CONNECTOR2 + convertLessThanOneThousand(dPart).trim();
				}
			} else{
				result = convert(lPart);
			}
		}
		return result;
	}
}
