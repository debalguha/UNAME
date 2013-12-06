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

	public Team getTeam(String teamId) {
		for (Iterator iterator = SimulatorData.teamList.iterator(); iterator.hasNext();) {
			Team item = (Team) iterator.next();
			if (item.getId().equals(teamId)) {
				return item;
			}
		}
		return null;
	}

	public List<Access> getAccessList() {
		return SimulatorData.accessList;
	}

	public List<Team> getTeamList() {
		return SimulatorData.teamList;
	}

	public List<AppAccess> getAppAccessList() {
		return SimulatorData.appAccessList;
	}

	public void saveTeam(Team team) {
		SimulatorData.teamList.add(team);
	}

	public void updateTeam(Team team) {
		for (Iterator iterator = SimulatorData.teamList.iterator(); iterator.hasNext();) {
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

	public void saveAccess(Access entity) {
		SimulatorData.accessList.add(entity);
	}

	public void updateAccess(Access entity) {
		for (Iterator iterator = SimulatorData.accessList.iterator(); iterator.hasNext();) {
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

	public Access getAccess(String accessId) {
		for (Iterator iterator = SimulatorData.accessList.iterator(); iterator.hasNext();) {
			Access item = (Access) iterator.next();
			if (item.getId().equals(accessId)) {
				return item;
			}
		}
		return null;
	}

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

	public void deleteAccess(String accessId) {
		List<Access> newList = new ArrayList<Access>();
		for (Iterator iterator = SimulatorData.accessList.iterator(); iterator.hasNext();) {
			Access item = (Access) iterator.next();
			if (!item.getId().equals(accessId)) {
				newList.add(item);
			}
		}
		SimulatorData.accessList = newList;
	}

	public List<Application> getApplicationList() {
		return SimulatorData.appList;
	}

	public void saveAppAccess(AppAccess entity) {
		SimulatorData.appAccessList.add(entity);
	}

	public void updateAppAccess(AppAccess entity) {
		for (Iterator iterator = SimulatorData.appAccessList.iterator(); iterator.hasNext();) {
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

	public void deleteAppAccess(String id) {
		List<AppAccess> newList = new ArrayList<AppAccess>();
		for (Iterator iterator = SimulatorData.appAccessList.iterator(); iterator.hasNext();) {
			AppAccess item = (AppAccess) iterator.next();
			if (!item.getId().equals(id)) {
				newList.add(item);
			}
		}
		SimulatorData.appAccessList = newList;
	}

	public AppAccess getAppAccess(String id) {
		for (Iterator iterator = SimulatorData.appAccessList.iterator(); iterator.hasNext();) {
			AppAccess item = (AppAccess) iterator.next();
			if (item.getId().equals(id)) {
				return item;
			}
		}
		return null;
	}
}