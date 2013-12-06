package com.va.uma.web.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.va.uma.model.UserInfo;
import com.va.uma.web.action.BaseAction;

public class AccessFilter implements Filter {

	private static List<String> ignoredURI = new ArrayList<String>();

	private static final String LOGIN_URI = "/login.do";

	private static List<String> ignoredPath = new ArrayList<String>();

	static {
		ignoredPath.add("/system/checklogin.do");
		ignoredPath.add(LOGIN_URI);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		for (String path : ignoredPath) {
			ignoredURI.add(request.getContextPath() + path);
		}
		UserInfo user = (UserInfo) request.getSession().getAttribute(BaseAction.ACCOUNT_SEESION_ID);
		boolean isLogin = false;
		String uri = request.getRequestURI();
		boolean isIgnore = ignoredURI.contains(uri);
		if (!isIgnore) {
			if (user != null) {
				isLogin = true;
			}
			if (!isLogin) {
				String loginUrl = request.getContextPath() + LOGIN_URI;
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.write("<script language='javascript'>");
				writer.write("alert('Please Login');");
				writer.write("window.parent.location.href='" + loginUrl + "';");
				writer.write("</script>");
				writer.close();
				return;
			}
		}
		chain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {

	}

}
