package com.va.uma.web.action;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.va.uma.service.IAppService;
import com.va.uma.service.IUserService;

public class BaseAction {

	private static final Logger logger = LoggerFactory.getLogger(BaseAction.class);
	public static final String ACCOUNT_SEESION_ID = "session_user_id";

//	@Resource(name = "appService")
//	protected IAppService appService;
//	@Resource(name = "userService")
//	protected IUserService userService;
	@Resource(name = "appServiceSimulator")
	protected IAppService appService;
	@Resource(name = "userServiceSimulator")
	protected IUserService userService;
	/**
	 * response json text
	 * 
	 * @param response
	 * @param text
	 */
	protected void responseJsonText(HttpServletResponse response, String text) {

		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(text);
		} catch (IOException e) {
			logger.error("", e);
		}
	}

	protected void responseJsonWithCodeAndDataStr(HttpServletResponse response, String code, String jsonDataStr) {
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		try {
			JSONObject jo = new JSONObject();
			jo.put("code", code);
			jo.put("data", jsonDataStr);
			response.getWriter().write(jo.toString());
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	protected void responseJsonWithCodeAndMsg(HttpServletResponse response, String code, String msg) {
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		try {
			JSONObject jo = new JSONObject();
			jo.put("code", code);
			jo.put("msg", msg);
			response.getWriter().write(jo.toString());
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	protected void responseJson4Success(HttpServletResponse response) {
		responseJsonWithCodeAndDataStr(response, "ok", "{}");
	}

	protected void responseJsonWithCode(HttpServletResponse response, String code) {
		responseJsonWithCodeAndDataStr(response, code, "{}");
	}

	protected void responseJson4Fail(HttpServletResponse response) {
		responseJsonWithCode(response, "fail");
	}
}
