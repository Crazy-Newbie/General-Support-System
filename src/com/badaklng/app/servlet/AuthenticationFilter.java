package com.badaklng.app.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.badaklng.app.constant.Constants;
import com.badaklng.app.constant.SessionAttributeEnum;
import com.badaklng.app.hibernate.AppUser;
import com.badaklng.app.utilities.Utility;

public class AuthenticationFilter implements Filter {
	private static final Logger logger = Logger.getLogger(AuthenticationFilter.class);

	private String onFailure;
	private String[] excludedPatterns;

	public void destroy() {

	}

	public void init(FilterConfig filterConfig) throws ServletException {
		onFailure = filterConfig.getInitParameter("onFailure");
		excludedPatterns = filterConfig.getInitParameter("excludedPatterns")
				.split(filterConfig.getInitParameter("delimiter"));
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpReq = ((HttpServletRequest) request);
		String urlRequest = httpReq.getRequestURL().toString();
		String root = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/"
				+ Constants.APPL_CONTEXT.toLowerCase();
		String urlRedirect = httpReq.getContextPath() + onFailure + "?redirect=" + urlRequest.replace(root, "");

		HttpSession currentSession = ((HttpServletRequest) request).getSession();
		currentSession.setAttribute(SessionAttributeEnum.IP_ADDRESS.toString(), request.getRemoteAddr());

		if (currentSession.getAttribute(new AppUser().getModelAttrKey()) == null
				&& !Utility.isURLMatches(httpReq.getRequestURI(), excludedPatterns)) {
			logger.info(String.format("Session timed out from {} redirecting to {}", urlRequest, urlRedirect));
			((HttpServletResponse) response).sendRedirect(urlRedirect);
		} else
			chain.doFilter(request, response);

	}
}
