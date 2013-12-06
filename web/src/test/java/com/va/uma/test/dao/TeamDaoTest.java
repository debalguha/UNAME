package com.va.uma.test.dao;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.va.uma.model.Team;

public class TeamDaoTest extends BaseDaoTest {
	private String name = "test";

	@Test
	public void save() {
		Team obj = new Team();
		obj.setName("test");
		obj.setCode("002");
		teamDao.save(obj);
	}

	@Test
	public void findById() {
		Team obj = teamDao.findById(name);
		Assert.assertEquals(name, obj.getName());
		System.out.println(obj);
	}

	@Test
	public void listAll() {
		List<Team> list = teamDao.listAll();
		Assert.assertEquals(1, list.size());
		System.out.println(list);
	}

	@Test
	public void update() {
		Team obj = teamDao.findById(name);
		obj.setCode("003");
		teamDao.update(obj);
		Team obj1 = teamDao.findById(name);
		Assert.assertEquals("003", obj1.getCode());
		System.out.println(obj1);
	}

	@Test
	public void delete() {
		Team obj = new Team();
		obj.setName(name);
		teamDao.delete(obj);
		Team q = teamDao.findById(name);
		Assert.assertEquals(null, q);
	}
}
