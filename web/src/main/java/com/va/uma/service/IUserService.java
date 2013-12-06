package com.va.uma.service;

import java.util.List;

import com.va.uma.model.UserAppAccess;
import com.va.uma.model.UserInfo;

public interface IUserService {

	void saveUser(UserInfo entity);

	void updateUser(UserInfo entity);

	void deleteUser(String userId);

	UserInfo getUserInfoByUsername(String userName);

	UserInfo getUserInfoById(String id);

	List<UserInfo> listUser(int pageSize, int pageIndex);

	void saveUserAppAccess(UserAppAccess entity);

	void deleteUserAppAccess(UserAppAccess entity);

	void updateUserAppAccess(UserAppAccess entity);

	void deleteAllAppAccessByUserId(String userId);

	boolean isAppAccessUsed(String appName, String accessId);
}