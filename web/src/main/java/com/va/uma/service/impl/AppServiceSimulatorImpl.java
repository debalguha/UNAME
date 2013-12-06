package com.va.uma.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.va.uma.model.Access;
import com.va.uma.model.AppAccess;
import com.va.uma.model.Application;
import com.va.uma.model.Team;
import com.va.uma.service.IAppService;
import com.va.uma.util.SimulatorData;

@Service("appServiceSimulator")
@Transactional
public class AppServiceSimulatorImpl implements IAppService {

	private static Logger logger = LoggerFactory.getLogger(AppServiceSimulatorImpl.class);
	@Override
	public Team getTeam(String teamId) {
		for (Iterator<Team> iterator = SimulatorData.teamList.iterator(); iterator.hasNext();) {
			Team item = (Team) iterator.next();
			if (item.getId().equals(teamId)) {
				return item;
			}
		}
		return null;
	}
	@Override
	public List<Access> getAccessList() {
		return SimulatorData.accessList;
	}

	@Override
	public List<Team> getTeamList() {
		return SimulatorData.teamList;
	}
	@Override
	public List<AppAccess> getAppAccessList() {
		return SimulatorData.appAccessList;
	}
	@Override
	public void saveTeam(Team team) {
		SimulatorData.teamList.add(team);
	}
	@Override
	public void updateTeam(Team team) {
		for (Iterator<Team> iterator = SimulatorData.teamList.iterator(); iterator.hasNext();) {
			Team item = (Team) iterator.next();
			if (item.getId().equals(team.getId())) {
				try {
					BeanUtils.copyProperties(item, team);
				} catch (Exception e) {
					logger.error("", e);
				}
			}
		}
	}
	@Override
	public void saveAccess(Access entity) {
		SimulatorData.accessList.add(entity);
	}
	@Override
	public void updateAccess(Access entity) {
		for (Iterator<Access> iterator = SimulatorData.accessList.iterator(); iterator.hasNext();) {
			Access item = (Access) iterator.next();
			if (item.getId().equals(entity.getId())) {
				try {
					BeanUtils.copyProperties(item, entity);
				} catch (Exception e) {
					logger.error("", e);
				}
			}
		}
	}
	@Override
	public Access getAccess(String accessId) {
		for (Iterator<Access> iterator = SimulatorData.accessList.iterator(); iterator.hasNext();) {
			Access item = (Access) iterator.next();
			if (item.getId().equals(accessId)) {
				return item;
			}
		}
		return null;
	}
	@Override
	public boolean isAccessUsed(String accessId) {
		boolean flag = false;
		List<AppAccess> list = SimulatorData.appAccessList;
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
		List<Access> newList = new ArrayList<Access>();
		for (Iterator<Access> iterator = SimulatorData.accessList.iterator(); iterator.hasNext();) {
			Access item = (Access) iterator.next();
			if (!item.getId().equals(accessId)) {
				newList.add(item);
			}
		}
		SimulatorData.accessList = newList;
	}
	@Override
	public List<Application> getApplicationList() {
		return SimulatorData.appList;
	}
	@Override
	public void saveAppAccess(AppAccess entity) {
		SimulatorData.appAccessList.add(entity);
	}
	@Override
	public void updateAppAccess(AppAccess entity) {
		for (Iterator<AppAccess> iterator = SimulatorData.appAccessList.iterator(); iterator.hasNext();) {
			AppAccess item = (AppAccess) iterator.next();
			if (item.getId().equals(entity.getId())) {
				try {
					BeanUtils.copyProperties(item, entity);
				} catch (Exception e) {
					logger.error("", e);
				}
			}
		}
	}
	@Override
	public void deleteAppAccess(String id) {
		List<AppAccess> newList = new ArrayList<AppAccess>();
		for (Iterator<AppAccess> iterator = SimulatorData.appAccessList.iterator(); iterator.hasNext();) {
			AppAccess item = (AppAccess) iterator.next();
			if (!item.getId().equals(id)) {
				newList.add(item);
			}
		}
		SimulatorData.appAccessList = newList;
	}
	@Override
	public AppAccess getAppAccess(String id) {
		for (Iterator<AppAccess> iterator = SimulatorData.appAccessList.iterator(); iterator.hasNext();) {
			AppAccess item = (AppAccess) iterator.next();
			if (item.getId().equals(id)) {
				return item;
			}
		}
		return null;
	}
}