package com.va.uma.test.dao;

import java.util.List;

import org.junit.Test;

import com.va.uma.model.AppAccess;

public class ApplicationDaoTest extends BaseDaoTest {

	@Test
	public void save() {

		AppAccess obj = new AppAccess();
		// obj.setAccessName("aaa");
		obj.setAppName("bbb");
		applicationDao.saveAppAccess(obj);
	}

	@Test
	public void delete() {
		AppAccess obj = new AppAccess();
		// obj.setAccessName("aaa");
		obj.setAppName("bbb");
		applicationDao.delete(obj);
	}

	@Test
	public void listAllAppAccess() {
		List<AppAccess> list = applicationDao.listAllAppAccess();
		System.out.println(list);
	}

}