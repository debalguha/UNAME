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

	@Override
	public void saveUser(UserInfo entity) {
		userInfoDao.save(entity);
	}
	@Override
	public void updateUser(UserInfo entity) {
		userInfoDao.update(entity);
	}
	@Override
	public void deleteUser(String userId) {
		userInfoDao.delete(userInfoDao.findById(userId));
	}
	@Override
	public UserInfo getUserInfoByUsername(String username) {
		UserInfo userInfo = userInfoDao.findByUsername(username);
		userInfo.setUserAppAccessList(userInfoDao.listUserAppAccess(userInfo.getId()));
		return userInfo;
	}
	@Override
	public UserInfo getUserInfoById(String id) {
		UserInfo userInfo = userInfoDao.findById(id);
		userInfo.setUserAppAccessList(userInfoDao.listUserAppAccess(userInfo.getId()));
		return userInfo;
	}
	@Override
	public List<UserInfo> listUser(int pageSize, int pageIndex) {
		List<UserInfo> list = userInfoDao.listAll();
		for (UserInfo userInfo : list) {
			userInfo.setUserAppAccessList(userInfoDao.listUserAppAccess(userInfo.getId()));
		}
		return list;
	}
	@Override
	public void saveUserAppAccess(UserAppAccess entity) {
		userInfoDao.saveUserAppAccess(entity);
	}
	@Override
	public void deleteUserAppAccess(UserAppAccess entity) {
		userInfoDao.deleteUserAppAccess(entity);
	}
	@Override
	public void updateUserAppAccess(UserAppAccess entity) {
		userInfoDao.updateUserAppAccess(entity);
	}
	@Override
	public void deleteAllAppAccessByUserId(String userId) {
		userInfoDao.deleteAllAppAccessByUserId(userId);
	}
	@Override
	public boolean isAppAccessUsed(String appName, String accessId) {
		return userInfoDao.isAppAccessUsed(appName, accessId);
	}
}