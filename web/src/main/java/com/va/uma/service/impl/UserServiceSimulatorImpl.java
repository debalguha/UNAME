package com.va.uma.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.va.uma.model.UserAppAccess;
import com.va.uma.model.UserInfo;
import com.va.uma.service.IUserService;
import com.va.uma.util.SimulatorData;

@Service("userServiceSimulator")
@Transactional
public class UserServiceSimulatorImpl implements IUserService {

	private static Logger logger = LoggerFactory.getLogger(UserServiceSimulatorImpl.class);

	public void saveUser(UserInfo entity) {
		SimulatorData.userList.add(entity);
	}

	public void updateUser(UserInfo entity) {
		for (Iterator iterator = SimulatorData.userList.iterator(); iterator.hasNext();) {
			UserInfo user = (UserInfo) iterator.next();
			if (user.getId().equals(entity.getId())) {
				try {
					BeanUtils.copyProperties(user, entity);
				} catch (Exception e) {
					logger.error("", e);
				}
			}
		}
	}

	public void deleteUser(String userId) {
		List<UserInfo> newList = new ArrayList<UserInfo>();
		for (Iterator iterator = SimulatorData.userList.iterator(); iterator.hasNext();) {
			UserInfo user = (UserInfo) iterator.next();
			if (!user.getId().equals(userId)) {
				newList.add(user);
			}
		}
		SimulatorData.userList = newList;
	}

	public UserInfo getUserInfoByUsername(String username) {
		List<UserAppAccess> list = new ArrayList<UserAppAccess>();
		for (Iterator iterator = SimulatorData.userList.iterator(); iterator.hasNext();) {
			UserInfo user = (UserInfo) iterator.next();
			for (UserAppAccess uaa : SimulatorData.userAppAccessList) {
				if (uaa.getUserInfo().getId().equals(user.getId())) {
					list.add(uaa);
				}
			}
			user.setUserAppAccessList(list);
			if (user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}

	public UserInfo getUserInfoById(String id) {
		List<UserAppAccess> list = new ArrayList<UserAppAccess>();
		for (Iterator iterator = SimulatorData.userList.iterator(); iterator.hasNext();) {
			UserInfo user = (UserInfo) iterator.next();
			for (UserAppAccess uaa : SimulatorData.userAppAccessList) {
				if (uaa.getUserInfo().getId().equals(user.getId())) {
					list.add(uaa);
				}
			}
			user.setUserAppAccessList(list);
			if (user.getId().equals(id)) {
				return user;
			}
		}
		return null;
	}

	public List<UserInfo> listUser(int pageSize, int pageIndex) {
		for (UserInfo userInfo : SimulatorData.userList) {
			List<UserAppAccess> list = new ArrayList<UserAppAccess>();
			for (UserAppAccess uaa : SimulatorData.userAppAccessList) {
				if (uaa.getUserInfo().getId().equals(userInfo.getId())) {
					list.add(uaa);
				}
			}
			userInfo.setUserAppAccessList(list);
		}
		return SimulatorData.userList;
	}

	public void saveUserAppAccess(UserAppAccess entity) {
		SimulatorData.userAppAccessList.add(entity);
	}

	public void deleteUserAppAccess(UserAppAccess entity) {
		List<UserAppAccess> newList = new ArrayList<UserAppAccess>();
		for (Iterator iterator = SimulatorData.userAppAccessList.iterator(); iterator.hasNext();) {
			UserAppAccess uaa = (UserAppAccess) iterator.next();
			if (!uaa.getId().equals(entity.getId())) {
				newList.add(uaa);
			}
		}
		SimulatorData.userAppAccessList = newList;
	}

	public void updateUserAppAccess(UserAppAccess entity) {
		for (Iterator iterator = SimulatorData.userAppAccessList.iterator(); iterator.hasNext();) {
			UserAppAccess uaa = (UserAppAccess) iterator.next();
			if (uaa.getId().equals(entity.getId())) {
				try {
					BeanUtils.copyProperties(uaa, entity);
				} catch (Exception e) {
					logger.error("", e);
				}
			}
		}
	}

	public void deleteAllAppAccessByUserId(String userId) {
		List<UserAppAccess> newList = new ArrayList<UserAppAccess>();
		for (Iterator iterator = SimulatorData.userAppAccessList.iterator(); iterator.hasNext();) {
			UserAppAccess uaa = (UserAppAccess) iterator.next();
			if (!uaa.getUserInfo().getId().equals(userId)) {
				newList.add(uaa);
			}
		}
		SimulatorData.userAppAccessList = newList;
	}

	public boolean isAppAccessUsed(String appName, String accessId) {
		for (Iterator iterator = SimulatorData.userAppAccessList.iterator(); iterator.hasNext();) {
			UserAppAccess uaa = (UserAppAccess) iterator.next();
			if (uaa.getAppName().equals(appName) && uaa.getAccess().getId().equals(accessId)) {
				return true;
			}
		}
		return false;
	}
}