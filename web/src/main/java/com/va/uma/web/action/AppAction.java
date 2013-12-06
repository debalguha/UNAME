/**
 * 
 */

package com.va.uma.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.va.uma.model.Access;
import com.va.uma.model.AppAccess;
import com.va.uma.model.Team;

@Controller
public class AppAction extends BaseAction {

	@RequestMapping("/app/list.do")
	public String indexPage(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<Access> accessList = appService.getAccessList();
		Map<String, String> accessMap = new HashMap<String, String>();
		for (Access access : accessList) {
			accessMap.put(access.getName(), access.getCode());
		}
		model.addAttribute("appList", appService.getApplicationList());
		model.addAttribute("accessList", accessList);
		model.addAttribute("accessMap", accessMap);
		model.addAttribute("teamList", appService.getTeamList());
		model.addAttribute("appAccessList", appService.getAppAccessList());
		return "app/list";
	}

	@RequestMapping("/access/save.do")
	public void saveAccess(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "name") String name, @RequestParam(value = "code") String code) {
		Access obj = new Access(name, code);
		appService.saveAccess(obj);
		responseJson4Success(response);
	}

	@RequestMapping("/access/update.do")
	public void updateAccess(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "accessId") String accessId, @RequestParam(value = "name") String name,
			@RequestParam(value = "code") String code) {
		Access obj = appService.getAccess(accessId);
		obj.setName(name);
		obj.setCode(code);
		appService.updateAccess(obj);
		responseJson4Success(response);
	}

	@RequestMapping("/access/delete.do")
	public void deleteAccess(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "accessId") String accessId) {
		boolean flag = appService.isAccessUsed(accessId);
		if (flag) {
			responseJsonWithCode(response, "used");
			return;
		}
		appService.deleteAccess(accessId);
		responseJson4Success(response);
	}

	@RequestMapping("/app-access/save.do")
	public void saveAppAccess(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "appName") String appName, @RequestParam(value = "accessId") String accessId) {
		AppAccess obj = new AppAccess();
		obj.setAppName(appName);
		obj.setAccess(appService.getAccess(accessId));
		appService.saveAppAccess(obj);
		responseJson4Success(response);
	}

	@RequestMapping("/app-access/update.do")
	public void updateAppAccess(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "id") String id, @RequestParam(value = "appName") String appName,
			@RequestParam(value = "accessId") String accessId) {

		AppAccess appAccess = appService.getAppAccess(id);
		appAccess.setAppName(appName);
		appAccess.setAccess(appService.getAccess(accessId));
		appService.updateAppAccess(appAccess);
		responseJson4Success(response);
	}

	@RequestMapping("/app-access/delete.do")
	public void deleteAppAccess(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam(value = "id") String id) {

		AppAccess appAccess = appService.getAppAccess(id);
		boolean flag = userService.isAppAccessUsed(appAccess.getAppName(), appAccess.getAccess().getId());
		if (flag) {
			responseJsonWithCode(response, "used");
			return;
		}

		appService.deleteAppAccess(id);
		responseJson4Success(response);
	}

	@RequestMapping("/team/save.do")
	public void saveTeam(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam(value = "name") String name,
			@RequestParam(value = "code") String code) {
		Team t = new Team(name, code);
		appService.saveTeam(t);
		responseJson4Success(response);
	}

	@RequestMapping("/team/update.do")
	public void updateTeam(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(value = "teamId") String teamId, @RequestParam(value = "name") String name,
			@RequestParam(value = "code") String code) {
		Team team = appService.getTeam(teamId);
		team.setName(name);
		team.setCode(code);
		appService.updateTeam(team);
		responseJson4Success(response);
	}

}
