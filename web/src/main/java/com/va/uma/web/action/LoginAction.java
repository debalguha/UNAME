/**
 * 
 */

package com.va.uma.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.va.uma.model.UserInfo;
import com.va.uma.model.UserInfo.UserStatus;
import com.va.uma.util.MD5;

@Controller
public class LoginAction extends BaseAction {

	@RequestMapping("/login.do")
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "login/index";
	}

	@RequestMapping("/system/checklogin.do")
	public void login(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "username") String username, @RequestParam(value = "password") String password,
			@RequestParam(value = "type") String type) {
		String md5 = MD5.md5(password);
		UserInfo user = userService.getUserInfoByUsername(username);
		if (user == null || !md5.equals(user.getPassword()) || !user.getType().toString().equals(type)) {
			responseJsonWithCode(response, "unvalid_user");
			return;
		}
		if (user.getStatus() == UserStatus.inactive) {
			responseJsonWithCode(response, "inactive");
			return;
		}
		request.getSession().setAttribute(BaseAction.ACCOUNT_SEESION_ID, user);
		responseJson4Success(response);
	}

	@RequestMapping("/system/logout.do")
	public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {
		request.getSession().invalidate();
		return "login/index";
	}
}
