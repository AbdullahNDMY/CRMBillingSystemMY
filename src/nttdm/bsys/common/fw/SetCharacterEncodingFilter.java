/**
 * @(#)SetCharacterEncodingFilter.java
 * 
 * Copyright (c) 2011 NTT DATA MALAYSIA SDN. BHD. All Rights Reserved.
 */
package nttdm.bsys.common.fw;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * the class of SetCharacterEncodingFilter
 * 
 * @author p-chengh
 */
public class SetCharacterEncodingFilter implements Filter {

	/**
	 * log flag
	 */
	private static Log log = LogFactory
			.getLog(SetCharacterEncodingFilter.class);

	/**
	 * filter process flag
	 */
	private static final String THRU_FILTER_KEY = "THRU_FILTER_ENCODING";

	/**
	 * filter more times flag
	 */
	private boolean moreFlag = false;

	/**
	 * default encoding
	 */
	private String encoding = null;

	/**
	 * filter ignore flag
	 */
	private boolean ignoreFlag = false;

	/**
	 * init process
	 * 
	 * @param filterConfig
	 *            set filter
	 * 
	 * @exception ServletException
	 *                error
	 */
	public void init(FilterConfig filterConfig) throws ServletException {

		// Log
		if (log.isDebugEnabled()) {
			log.debug("init() called.");
		}

		// set encoding
		this.encoding = filterConfig.getInitParameter("encoding");

		if (log.isDebugEnabled()) {
			log.debug("this.encoding = " + this.encoding);
		}

		// set ignoreFlag
		String ignoreValue = filterConfig.getInitParameter("ignore");
		if (ignoreValue != null) {
			this.ignoreFlag = this.createFlag(ignoreValue);
		}

		// set moreFlag
		String moreValue = filterConfig.getInitParameter("more");
		if (moreValue != null) {
			this.moreFlag = this.createFlag(moreValue);
		}
	}

	/**
	 * filter main process
	 * 
	 * @param request
	 * @param response
	 * @param chain
	 * 
	 * @exception IOException
	 *                error
	 * @exception ServletException
	 *                error
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		if (!this.moreFlag && request.getAttribute(THRU_FILTER_KEY) != null) {
			chain.doFilter(request, response);
			return;
		}

		if (!ignoreFlag && encoding != null) {
			// Log
			if (log.isDebugEnabled()) {
				log.debug("set encoding(" + encoding + ") to request.");
			}
			request.setCharacterEncoding(encoding);
		}

		// set process over flag
		request.setAttribute(THRU_FILTER_KEY, THRU_FILTER_KEY);

		chain.doFilter(request, response);
	}

	/**
	 * destroy process
	 */
	public void destroy() {
		this.encoding = null;
	}

	/**
	 * get flag from "true" or "yes"
	 * 
	 * @param value
	 * @return flag
	 */
	private boolean createFlag(String value) {
		if ("true".equalsIgnoreCase(value)) {
			return true;
		} else if ("yes".equalsIgnoreCase(value)) {
			return true;
		}
		return false;
	}
}