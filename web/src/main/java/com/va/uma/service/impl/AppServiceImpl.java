package com.va.uma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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

	@Autowired
	IAccessDao accessDefDao;
	@Autowired
	ITeamDao teamDao;
	@Autowired
	IApplicationDao applicationDao;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)

	@Override
	public Team getTeam(String teamId) {
		return teamDao.findById(teamId);
	}

	@Override
	public List<Access> getAccessList() {
		return accessDefDao.listAll();
	}

	@Override
	public List<Team> getTeamList() {
		return teamDao.listAll();
	}

	@Override
	public List<AppAccess> getAppAccessList() {
		return applicationDao.listAllAppAccess();
	}

	@Override
	public void saveTeam(Team team) {
		teamDao.save(team);
	}

	@Override
	public void updateTeam(Team team) {
		teamDao.update(team);
	}

	@Override
	public void saveAccess(Access entity) {
		accessDefDao.save(entity);
	}

	@Override
	public void updateAccess(Access entity) {
		accessDefDao.update(entity);
	}

	@Override
	public Access getAccess(String accessId) {
		return accessDefDao.findById(accessId);
	}

	@Override
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
	
	@Override
	public void deleteAccess(String accessId) {
		accessDefDao.delete(accessDefDao.findById(accessId));
	}

	@Override
	public List<Application> getApplicationList() {
		return applicationDao.listAllApp();
	}

	@Override
	public void saveAppAccess(AppAccess entity) {
		applicationDao.saveAppAccess(entity);
	}

	@Override
	public void updateAppAccess(AppAccess entity) {
		applicationDao.updateAppAccess(entity);
	}

	@Override
	public void deleteAppAccess(String id) {
		applicationDao.delete(applicationDao.findById(id));
	}

	@Override
	public AppAccess getAppAccess(String id) {
		return applicationDao.findById(id);
	}
}