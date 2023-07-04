/**
 * @(#)RequestHelperFilter.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.filter;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * the class of RequestHelperFilter
 */
public class RequestHelperFilter implements Filter {

	private static ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<HttpServletRequest>();
	private static ThreadLocal<HttpServletResponse> responseHolder = new ThreadLocal<HttpServletResponse>();
	private static Logger logger = Logger.getLogger(RequestHelperFilter.class);

	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * Catch request for further usage
	 * 
	 * <pre>
	 * 1. Catch request and set to ThreadLocal object
	 * 2. Call chain.doFilter to release current lock
	 * 3. Request finish, clean ThreadLocal Object.
	 * </pre>
	 * 
	 * @param request
	 *            Initial request
	 * @param response
	 *            Initial response
	 * @param chain
	 *            Filter chain
	 * @throws IOException
	 *             Default Exception throw
	 */
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		//log
		logger.debug("Catch request");
		HttpServletRequest request = (HttpServletRequest) arg0;
		requestHolder.set(request);

		HttpServletResponse response = (HttpServletResponse) arg1;
		responseHolder.set(response);
		arg2.doFilter(arg0, arg1);
		//log
		logger.debug("Release request");
		requestHolder.remove();
		responseHolder.remove();
	}

	/**
	 * init Attribute of FilterConfig
	 */
	public void init(FilterConfig arg0) throws ServletException {
		logger.debug("Initializing RequestHelper");
		arg0.getServletContext().setAttribute("shippingPlanHolder",
				new ConcurrentHashMap<String, Boolean>());
	}

	/**
	 * Support method to retrieve ServletRequest from ThreadLocal
	 * 
	 * @return current ServletRequest object inside thread
	 */
	public static HttpServletRequest getServletRequest() {
		return requestHolder.get();
	}

	/**
	 * Support method to retrieve HttpSession object from ServletRequest
	 * 
	 * @return current associated HttpSession object
	 */
	public static HttpSession getSession() {
		return requestHolder.get().getSession();
	}

	/**
	 * Support method to retrieve current ServletContext
	 * 
	 * @return current Servlet Context
	 */
	public static ServletContext getServletContext() {
		return requestHolder.get().getSession().getServletContext();
	}

	/**
	 * Support method to retrieve current HttpServletResponse
	 * 
	 * @return current Servlet Response
	 */
	public static HttpServletResponse getServletResponse() {
		return responseHolder.get();
	}
}
