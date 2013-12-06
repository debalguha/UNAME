package com.va.uma.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.va.uma.dao.IUserInfoDao;
import com.va.uma.model.UserAppAccess;
import com.va.uma.model.UserInfo;
import com.va.uma.service.IUserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

	// @Autowired
	private IUserInfoDao userInfoDao;

	public void saveUser(UserInfo entity) {
		userInfoDao.save(entity);
	}

	public void updateUser(UserInfo entity) {
		userInfoDao.update(entity);
	}

	public void deleteUser(String userId) {
		userInfoDao.delete(userInfoDao.findById(userId));
	}

	public UserInfo getUserInfoByUsername(String username) {
		UserInfo userInfo = userInfoDao.findByUsername(username);
		userInfo.setUserAppAccessList(userInfoDao.listUserAppAccess(userInfo.getId()));
		return userInfo;
	}

	public UserInfo getUserInfoById(String id) {
		UserInfo userInfo = userInfoDao.findById(id);
		userInfo.setUserAppAccessList(userInfoDao.listUserAppAccess(userInfo.getId()));
		return userInfo;
	}

	public List<UserInfo> listUser(int pageSize, int pageIndex) {
		List<UserInfo> list = userInfoDao.listAll();
		for (UserInfo userInfo : list) {
			userInfo.setUserAppAccessList(userInfoDao.listUserAppAccess(userInfo.getId()));
		}
		return list;
	}

	public void saveUserAppAccess(UserAppAccess entity) {
		userInfoDao.saveUserAppAccess(entity);
	}

	public void deleteUserAppAccess(UserAppAccess entity) {
		userInfoDao.deleteUserAppAccess(entity);
	}

	public void updateUserAppAccess(UserAppAccess entity) {
		userInfoDao.updateUserAppAccess(entity);
	}

	public void deleteAllAppAccessByUserId(String userId) {
		userInfoDao.deleteAllAppAccessByUserId(userId);
	}

	public boolean isAppAccessUsed(String appName, String accessId) {
		return userInfoDao.isAppAccessUsed(appName, accessId);
	}
}