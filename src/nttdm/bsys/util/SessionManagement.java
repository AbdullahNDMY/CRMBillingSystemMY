/**
 * @(#)SessionManagement.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.util;

import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Session Management
 */
public class SessionManagement {

	static Hashtable m_login_users = new Hashtable();

	public SessionManagement() {
	}

	/**
	 * login
	 * 
	 * @param user_id
	 * @param session_id
	 * 
	 * @return boolean
	 */
	public static boolean login(String user_id, String session_id) {
		if (SessionManagement.m_login_users.get(user_id) != null) {
			SessionManagement.m_login_users.remove(user_id);
		}
		SessionManagement.m_login_users.put(user_id, session_id);
		return true;
	}

	/**
	 * isLogined
	 * 
	 * @param user_id
	 * @param req
	 * 
	 * @return boolean
	 */
	public static boolean isLogined(String user_id, HttpServletRequest req) {
		if (SessionManagement.m_login_users.get(user_id) == null) {
			return false;
		} else {
			String session_id = (String) SessionManagement.m_login_users
					.get(user_id);
			HttpSession session = req.getSession().getSessionContext()
					.getSession(session_id);
			if (session == null) {
				return false;
			} else {
				if (session.getId().equals(req.getSession().getId())) {
					return true;
				} else {
					return false;
				}
			}
		}
	}
}
