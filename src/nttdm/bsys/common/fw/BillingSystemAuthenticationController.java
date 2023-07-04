/**
 * @(#)BillingSystemAuthenticationController.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.fw;

import java.util.ArrayList;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import jp.terasoluna.fw.web.RequestUtil;
import jp.terasoluna.fw.web.UserValueObject;
import jp.terasoluna.fw.web.thin.AuthenticationController;
import jp.terasoluna.fw.util.PropertyUtil;

/**
 * the class of BillingSystemAuthenticationController
 * 
 * @author p-chengh
 */
public class BillingSystemAuthenticationController implements
		AuthenticationController {

	private List<String> listNoCheckRequiredPath = null;

	/**
	 * confirm isAuthenticated or not
	 * 
	 * @param String
	 *            pathInfo
	 * @param ServletRequest
	 *            req
	 * 
	 * @return boolean true/false
	 */
	public boolean isAuthenticated(String pathInfo, ServletRequest req) {
		HttpSession session = ((HttpServletRequest) req).getSession();

		// get BillingSystemUserValueObject info
		BillingSystemUserValueObject uvo = (BillingSystemUserValueObject) session
				.getAttribute(UserValueObject.USER_VALUE_OBJECT_KEY);

		if (uvo == null) {
			return false;
		}

		if (StringUtils.equals("0", uvo.getIs_need_change_password())) {
			return true;
		}
		return false;
	}

	/**
	 * confirm isCheckRequired or not
	 * 
	 * @param ServletRequest
	 *            req
	 * 
	 * @return boolean true/false
	 */
	public boolean isCheckRequired(ServletRequest req) {
		// get pathInfo from Request
		String pathInfo = RequestUtil.getPathInfo(req);

		// get not required path list from Property
		if (listNoCheckRequiredPath == null) {
			listNoCheckRequiredPath = new ArrayList<String>();
			for (int i = 1;; i++) {
				String path = PropertyUtil
						.getProperty("authentication.nocheckpath." + i);
				if (path == null) {
					break;
				}
				listNoCheckRequiredPath.add(path);
			}
		}

		// compare not required path list and Request path
		for (int cntX = 0; cntX < listNoCheckRequiredPath.size(); cntX++) {
			String path = (String) listNoCheckRequiredPath.get(cntX);
			if (pathInfo.startsWith(path) || "/".equals(pathInfo)) {
				return false;
			}
		}
		return true;
	}
}
