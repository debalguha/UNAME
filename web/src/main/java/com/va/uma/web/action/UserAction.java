/**
 * 
 */

package com.va.uma.web.action;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.va.uma.model.Team;
import com.va.uma.model.UserAppAccess;
import com.va.uma.model.UserInfo;
import com.va.uma.model.UserInfo.UserStatus;
import com.va.uma.model.UserInfo.UserType;
import com.va.uma.util.MD5;

@Controller
public class UserAction extends BaseAction {

	@RequestMapping("/user/list.do")
	public String listPage(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("dataList", userService.listUser(0, 0));
		model.addAttribute("appList", appService.getApplicationList());
		return "user/list";
	}

	@RequestMapping("/user/add.do")
	public String addPage(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("appList", appService.getApplicationList());
		model.addAttribute("appAccessList", appService.getAppAccessList());
		model.addAttribute("teamList", appService.getTeamList());
		return "user/add";
	}

	@RequestMapping("/user/edit.do")
	public String editPage(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam(value = "id") String userId) {
		UserInfo userInfo = userService.getUserInfoById(userId);
		model.addAttribute("appList", appService.getApplicationList());
		model.addAttribute("appAccessList", appService.getAppAccessList());
		model.addAttribute("teamList", appService.getTeamList());
		model.addAttribute("userInfo", userInfo);
		return "user/edit";
	}

	@RequestMapping("/user/save.do")
	public void saveUser(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "username") String username, @RequestParam(value = "password") String password,
			@RequestParam(value = "status") String status, @RequestParam(value = "type") String type,
			@RequestParam(value = "teamId", required = false, defaultValue = "") String teamId,
			@RequestParam(value = "phone", required = false, defaultValue = "") String phone,
			@RequestParam(value = "firstName", required = false, defaultValue = "") String firstName,
			@RequestParam(value = "lastName", required = false, defaultValue = "") String lastName,
			@RequestParam(value = "approverFullname", required = false, defaultValue = "") String approverFullname,
			@RequestParam(value = "approverEmail", required = false, defaultValue = "") String approverEmail,
			@RequestParam(value = "approverPhone", required = false, defaultValue = "") String approverPhone,
			@RequestParam(value = "requestDetail", required = false, defaultValue = "") String requestDetail) {
		UserInfo user = new UserInfo();

		Map requestMap = new HashMap(request.getParameterMap());
		requestMap.remove("teamId");
		requestMap.remove("type");
		requestMap.remove("status");
		Map<String, String> appAccessMap = getUserAppAccess(request, requestMap);
		try {
			BeanUtils.populate(user, requestMap);
		} catch (Exception e) {
			responseJson4Fail(response);
		}
		user.setStatus(UserStatus.valueOf(status));
		user.setType(UserType.valueOf(type));
		Team team = appService.getTeam(teamId);
		if (team != null) {
			user.setTeam(team);
		}
		userService.saveUser(user);
		saveUserAppAccess(user, appAccessMap);
		responseJson4Success(response);
	}

	private void saveUserAppAccess(UserInfo user, Map<String, String> appAccessMap) {
		Set<String> appAccessSet = appAccessMap.keySet();
		Iterator<String> appAccessIterator = appAccessSet.iterator();
		while (appAccessIterator.hasNext()) {
			String appName = (String) appAccessIterator.next();
			UserAppAccess userAppAccess = new UserAppAccess();
			userAppAccess.setUserInfo(user);
			userAppAccess.setAppName(appName);
			userAppAccess.setAccess(appService.getAccess(appAccessMap.get(appName)));
			userService.saveUserAppAccess(userAppAccess);
		}
	}

	private Map<String, String> getUserAppAccess(HttpServletRequest request, Map copyMap) {
		Map<String, String> appAccessMap = new HashMap<String, String>();
		Set keySet = copyMap.keySet();
		Set<String> appSet = new HashSet<String>();
		Iterator iterator = keySet.iterator();
		String appPrefix = "app:";
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			if (key.startsWith(appPrefix)) {
				if (!request.getParameter(key).equals("0")) {
					appAccessMap.put(key.substring(appPrefix.length()), request.getParameter(key));
				}
				appSet.add(key.substring(appPrefix.length()));
			}
		}
		for (String appName : appSet) {
			copyMap.remove(appPrefix + appName);
		}
		return appAccessMap;
	}

	@RequestMapping("/user/update.do")
	public void updateUser(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "userId") String userId, @RequestParam(value = "status") String status,
			@RequestParam(value = "type") String type, @RequestParam(value = "teamId", required = false, defaultValue = "") String teamId,
			@RequestParam(value = "phone", required = false, defaultValue = "") String phone,
			@RequestParam(value = "firstName", required = false, defaultValue = "") String firstName,
			@RequestParam(value = "lastName", required = false, defaultValue = "") String lastName,
			@RequestParam(value = "approverFullname", required = false, defaultValue = "") String approverFullname,
			@RequestParam(value = "approverEmail", required = false, defaultValue = "") String approverEmail,
			@RequestParam(value = "approverPhone", required = false, defaultValue = "") String approverPhone,
			@RequestParam(value = "requestDetail", required = false, defaultValue = "") String requestDetail) {
		UserInfo user = userService.getUserInfoById(userId);

		Map requestMap = new HashMap(request.getParameterMap());
		requestMap.remove("teamId");
		requestMap.remove("type");
		requestMap.remove("status");
		Map<String, String> appAccessMap = getUserAppAccess(request, requestMap);
		try {
			BeanUtils.populate(user, requestMap);
		} catch (Exception e) {
			responseJson4Fail(response);
		}
		user.setStatus(UserStatus.valueOf(status));
		user.setType(UserType.valueOf(type));
		Team team = appService.getTeam(teamId);
		if (team != null) {
			user.setTeam(team);
		}
		userService.updateUser(user);
		userService.deleteAllAppAccessByUserId(user.getId());
		saveUserAppAccess(user, appAccessMap);
		responseJson4Success(response);
	}

	@RequestMapping("/user/change-password.do")
	public void changePassword(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "userId") String userId, @RequestParam(value = "password") String password) {
		UserInfo user = userService.getUserInfoById(userId);
		user.setPassword(MD5.md5(password));
		userService.updateUser(user);
		responseJson4Success(response);
	}

	@RequestMapping("/user/delete.do")
	public void deleteUser(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "userId") String userId) {
		userService.deleteUser(userId);
		responseJson4Success(response);
	}
}
