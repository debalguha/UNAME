package com.va.uma.test.dao;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.va.uma.model.UserAppAccess;
import com.va.uma.model.UserInfo;
import com.va.uma.model.UserInfo.UserStatus;
import com.va.uma.model.UserInfo.UserType;
import com.va.uma.util.MD5;

public class UserInfoDaoTest extends BaseDaoTest {
	private String name = "test";

	@Test
	public void save() {
		UserInfo info = new UserInfo();
		info.setUsername("steven");
		info.setPassword(MD5.md5("123456"));
		info.setStatus(UserStatus.active);
		info.setType(UserType.creator);
		userInfoDao.save(info);
	}

	@Test
	public void findByUsername() {
		UserInfo obj = userInfoDao.findByUsername("xieshijian");
		Assert.assertEquals("xieshijian", obj.getUsername());
		System.out.println(obj);
	}

	@Test
	public void listAll() {
		List<UserInfo> list = userInfoDao.listAll();
		Assert.assertEquals(1, list.size());
		System.out.println(list);
	}

	@Test
	public void update() {
		String username = "xieshijian";
		UserInfo obj = userInfoDao.findByUsername(username);
		obj.setStatus(UserStatus.inactive);
		userInfoDao.update(obj);
		UserInfo q = userInfoDao.findByUsername(username);
		Assert.assertEquals(UserStatus.inactive, q.getStatus());
		System.out.println(q);
	}

	@Test
	public void delete() {
		UserInfo info = new UserInfo();
		info.setId("a2cee740-1041-480d-b4e1-e8bdac3683e9");
		userInfoDao.delete(info);
	}

	@Test
	public void saveUserAppAccess() {
		UserAppAccess entity = new UserAppAccess();
		List<UserInfo> userList = userInfoDao.listAll();
		entity.setUserInfo(userList.get(0));
		entity.setAppName(applicationDao.listAllApp().get(0).getName());
		entity.setAccess(accessDefDao.listAll().get(0));
		userInfoDao.saveUserAppAccess(entity);
	}

	@Test
	public void listUserAppAccess() {
		List<UserAppAccess> list = userInfoDao.listUserAppAccess("43fa0d78-8d83-4508-8b02-ac4cf171dbdd");
		System.out.println(list);
	}
	
	@Test
	public void deleteAllAppAccessByUserId() {
		userInfoDao.deleteAllAppAccessByUserId("43fa0d78-8d83-4508-8b02-ac4cf171dbdd");
	}
	
}
