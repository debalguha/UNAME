package com.va.uma.test.service;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.va.uma.dao.IAccessDao;
import com.va.uma.dao.IApplicationDao;
import com.va.uma.dao.ITeamDao;
import com.va.uma.dao.IUserInfoDao;
import com.va.uma.service.IAppService;
import com.va.uma.service.IUserService;

public class BaseServiceTest {

	IUserService userService;
	IAppService appService;

	@Before
	public void setUp() {

		String baseDir = System.getProperty("user.dir");
		String path = baseDir + "/src/main/resources/spring/service.xml";
		ApplicationContext applicationContext = new FileSystemXmlApplicationContext(new String[] { path });
		userService = (IUserService) applicationContext.getBean("userService");
		appService = (IAppService) applicationContext.getBean("appService");
	}
}
