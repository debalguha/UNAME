package com.va.uma.service;

import java.util.List;

import com.va.uma.model.Access;
import com.va.uma.model.AppAccess;
import com.va.uma.model.Application;
import com.va.uma.model.Team;

public interface IAppService {
	List<Access> getAccessList();

	List<Application> getApplicationList();

	List<Team> getTeamList();

	List<AppAccess> getAppAccessList();

	Team getTeam(String teamId);

	Access getAccess(String accessId);

	boolean isAccessUsed(String accessId);

	void deleteAccess(String accessId);

	void saveTeam(Team team);

	void updateTeam(Team team);

	void saveAccess(Access entity);

	void updateAccess(Access entity);

	void saveAppAccess(AppAccess entity);

	void updateAppAccess(AppAccess entity);

	void deleteAppAccess(String id);

	AppAccess getAppAccess(String id);
}