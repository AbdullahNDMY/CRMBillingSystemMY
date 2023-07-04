package nttdm.bsys.m_alt.bean;

import jp.terasoluna.fw.web.struts.form.MultiFieldValidator;

public class ToCCValidator implements MultiFieldValidator {

	public boolean validate(String to_msg, String[] fields) {
//		if(to_msg != null) {
//			if(fields.length == 1) {
//				String cc_msg = fields[0];
//				if(cc_msg != null) {
//					if(to_msg.trim().equals("") && cc_msg.trim().equals(""))
//						return false;
//				}
//			}
//		}
		//not used
		//change validate to javascript
		return true;
	}

}
