package com.va.uma.test.dao;

import junit.framework.Assert;

import org.junit.Test;

import com.va.uma.model.Access;

public class AccessDaoTest extends BaseDaoTest {
	private String name = "test";

	@Test
	public void save() {
		Access obj = new Access();
		obj.setName("test");
		obj.setCode("002");
		accessDefDao.save(obj);
	}

	@Test
	public void findByName() {
		Access obj = accessDefDao.findById(name);
		Assert.assertEquals(name, obj.getName());
		System.out.println(obj);
	}

	@Test
	public void listAll() {
		System.out.println(accessDefDao.listAll());
	}

	@Test
	public void update() {
		Access obj = accessDefDao.findById(name);
		obj.setCode("003");
		accessDefDao.update(obj);
		Access obj1 = accessDefDao.findById(name);
		Assert.assertEquals("003", obj1.getCode());
		System.out.println(obj1);
	}

	@Test
	public void delete() {
		Access obj = new Access();
		obj.setName(name);
		accessDefDao.delete(obj);
		Access q = accessDefDao.findById(name);
		Assert.assertEquals(null, q);
	}
}
