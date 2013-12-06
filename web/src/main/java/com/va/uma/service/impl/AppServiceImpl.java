package com.va.uma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.va.uma.dao.IAccessDao;
import com.va.uma.dao.IApplicationDao;
import com.va.uma.dao.ITeamDao;
import com.va.uma.model.Access;
import com.va.uma.model.AppAccess;
import com.va.uma.model.Application;
import com.va.uma.model.Team;
import com.va.uma.service.IAppService;

@Service("appService")
@Transactional
public class AppServiceImpl implements IAppService {

	// @Autowired
	IAccessDao accessDefDao;
	// @Autowired
	ITeamDao teamDao;
	// @Autowired
	IApplicationDao applicationDao;

	// @Transactional(propagation = Propagation.REQUIRED, rollbackFor =
	// RuntimeException.class)

	public Team getTeam(String teamId) {
		return teamDao.findById(teamId);
	}

	public List<Access> getAccessList() {
		return accessDefDao.listAll();
	}

	public List<Team> getTeamList() {
		return teamDao.listAll();
	}

	public List<AppAccess> getAppAccessList() {
		return applicationDao.listAllAppAccess();
	}

	public void saveTeam(Team team) {
		teamDao.save(team);
	}

	public void updateTeam(Team team) {
		teamDao.update(team);
	}

	public void saveAccess(Access entity) {
		accessDefDao.save(entity);
	}

	public void updateAccess(Access entity) {
		accessDefDao.update(entity);
	}

	public Access getAccess(String accessId) {
		return accessDefDao.findById(accessId);
	}

	public boolean isAccessUsed(String accessId) {
		boolean flag = false;
		List<AppAccess> list = applicationDao.listAllAppAccess();
		for (AppAccess appAccess : list) {
			if (appAccess.getAccess().getId().equals(accessId)) {
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	public void deleteAccess(String accessId) {
		accessDefDao.delete(accessDefDao.findById(accessId));
	}

	public List<Application> getApplicationList() {
		return applicationDao.listAllApp();
	}

	public void saveAppAccess(AppAccess entity) {
		applicationDao.saveAppAccess(entity);
	}

	public void updateAppAccess(AppAccess entity) {
		applicationDao.updateAppAccess(entity);
	}

	public void deleteAppAccess(String id) {
		applicationDao.delete(applicationDao.findById(id));
	}

	public AppAccess getAppAccess(String id) {
		return applicationDao.findById(id);
	}
}