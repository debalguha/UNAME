package com.va.uma.dao;

import java.util.List;

import com.va.uma.model.UserAppAccess;
import com.va.uma.model.UserInfo;

public interface IUserInfoDao {

	void save(UserInfo entity);

	void update(UserInfo entity);

	void delete(UserInfo entity);

	UserInfo findById(String id);

	UserInfo findByUsername(String username);

	UserInfo findByEmail(String email);
	
	List<UserAppAccess> listUserAppAccess(String userId);

	List<UserInfo> listAll();

	void saveUserAppAccess(UserAppAccess entity);

	void deleteUserAppAccess(UserAppAccess entity);

	void updateUserAppAccess(UserAppAccess entity);
	
	void deleteAllAppAccessByUserId(String userId);
	
	boolean isAppAccessUsed(String appName, String accessId);

}