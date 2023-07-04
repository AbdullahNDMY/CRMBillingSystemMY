package nttdm.bsys.m_alt.bean;

import java.text.SimpleDateFormat;

import jp.terasoluna.fw.web.struts.form.MultiFieldValidator;


public class DateValidator implements MultiFieldValidator{
	
	static final String DATE_PATTERN = "dd/MM/yyyy";
	
	public boolean validate(String startDate, String[] fields) {
//		if(fields.length == 1){
//			String endDate = fields[0];			
//			if (startDate == null || "".equals(startDate) || endDate == null || "".equals(endDate)) {
//	            return true;
//	        }	
//			return compareStartDateEndDate(startDate,endDate);
//		}else{
//			String endDate = fields[0];	
//			String reminderDate = fields[1];
//			if (startDate == null || "".equals(startDate) 
//				|| endDate == null || "".equals(endDate)
//				|| reminderDate == null || "".equals(reminderDate)
//				) {
//	            return true;
//	        }
//			return compareReminderDate(startDate,endDate,reminderDate);
//		}	
		// not used
		// validate by javascript
		return true;
	}
	
	public boolean compareStartDateEndDate(String startDate, String endDate){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
			if(sdf.parse(startDate).after(sdf.parse(endDate))){
				return false;
			}
		}catch(Exception ex){
			return false;
		}
		return true;
	}
	
	public boolean compareReminderDate(String startDate, String endDate, String reminderDate){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
			if(sdf.parse(reminderDate).after(sdf.parse(endDate)) || sdf.parse(reminderDate).before(sdf.parse(startDate))){
				return false;
			}
		}catch(Exception ex){
			return false;
		}
		return true;
	}
}
