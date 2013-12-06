package com.va.uma.test.dao;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.va.uma.dao.IAccessDao;
import com.va.uma.dao.IApplicationDao;
import com.va.uma.dao.ITeamDao;
import com.va.uma.dao.IUserInfoDao;

public class BaseDaoTest {

	IAccessDao accessDefDao;
	ITeamDao teamDao;
	IApplicationDao applicationDao;
	IUserInfoDao userInfoDao;

	@Before
	public void setUp() {

		String baseDir = System.getProperty("user.dir");
		String path = baseDir + "/src/main/resources/spring/service.xml";
		ApplicationContext applicationContext = new FileSystemXmlApplicationContext(new String[] { path });
		accessDefDao = (IAccessDao) applicationContext.getBean("accessDefDao");
		teamDao = (ITeamDao) applicationContext.getBean("teamDao");
		teamDao = (ITeamDao) applicationContext.getBean("teamDao");
		applicationDao = (IApplicationDao) applicationContext.getBean("applicationDao");
		userInfoDao = (IUserInfoDao) applicationContext.getBean("userInfoDao");
	}
}
