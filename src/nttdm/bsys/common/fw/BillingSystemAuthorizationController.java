/**
 * @(#)BillingSystemAuthorizationController.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.fw;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import nttdm.bsys.common.fw.BillingSystemUserValueObject;
import jp.terasoluna.fw.web.UserValueObject;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.web.RequestUtil;
import jp.terasoluna.fw.web.thin.AuthorizationController;
import java.util.Map;
import java.util.HashMap;

/**
 * the class of BillingSystemAuthorizationController
 * 
 * @author p-chengh
 */
public class BillingSystemAuthorizationController implements
		AuthorizationController {

	/**
	 * <div>queryDAO</div>
	 */
	private QueryDAO queryDAO;

	/**
	 * <div>getQueryDAO</div>
	 */
	public QueryDAO getQueryDAO() {
		return queryDAO;
	}

	/**
	 * <div>setQueryDAO</div>
	 */
	public void setQueryDAO(QueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

	/**
	 * confirm is Authorized or not
	 * 
	 * @param String
	 *            pathInfo
	 * @param ServletRequest
	 *            req
	 * 
	 * @return boolean true/false
	 */
	public boolean isAuthorized(String pathInfo, ServletRequest req) {
		// get session
		HttpSession session = ((HttpServletRequest) req).getSession();
		// pathInfo
		String path = pathInfo;

		// pathInfo is "/",return true
		if (path.equals("/")) {
			return true;
		}

		// edit the path
		try {
			int i = 0;
			for (i = 0; i < path.length(); i++) {
				if (path.startsWith("/") && !path.equals("/")) {
					path = path.substring(1);
				}
			}
			int firstIndex = path.indexOf("/");
			if (firstIndex >= 0) {
				path = path.substring(0, path.indexOf("/"));
			}
			path = "/" + path + "%";
		} catch (Exception ex) {
			System.out.println("Exception in isCheckRequired ");
			path = pathInfo;
		}

		// get BillingSystemUserValueObject info
		BillingSystemUserValueObject uvo = (BillingSystemUserValueObject) session
				.getAttribute(UserValueObject.USER_VALUE_OBJECT_KEY);
		if (uvo == null) {
			return false;
		}
		HashMap<String, String> params = new HashMap<String, String>();
		// path
		params.put("path_module", path);
		// userId
		params.put("id_user", uvo.getId_user());

		// get access of the pathInfo of the user
		Map<String, Object>[] result = queryDAO.executeForMapArray(
				"SELECT.C_CMN001.SQL005", params);
		if (result != null && result.length > 0) {
			return true;
		}
		return false;
	}

	/**
	 * confirm Check is Required or not
	 * 
	 * @param ServletRequest
	 *            req
	 * 
	 * @return boolean true/false
	 */
	public boolean isCheckRequired(ServletRequest req) {

		// condition map
		HashMap<String, String> param = new HashMap<String, String>();

		// get path from request
		String path = RequestUtil.getPathInfo(req);
		if (path.equals("/")) {
			return false;
		}

		// edit the path
		try {
			int i = 0;
			for (i = 0; i < path.length(); i++) {
				if (path.startsWith("/") && !path.equals("/")) {
					path = path.substring(1);
				}
			}
			int firstIndex = path.indexOf("/");
			if (firstIndex >= 0) {
				path = path.substring(0, path.indexOf("/"));
			}
			path = "/" + path + "%";
		} catch (Exception ex) {
			System.out.println("Exception in isCheckRequired ");
			path = RequestUtil.getPathInfo(req);
		}

		// path module
		param.put("path_module", path);
		Map<String, Object>[] result = queryDAO.executeForMapArray(
				"SELECT.C_CMN001.SQL004", param);
		if (result != null && result.length > 0) {
			return true;
		}
		return false;
	}
}
