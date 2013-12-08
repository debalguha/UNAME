package com.va.uma.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.hibernate.Session;

import com.va.uma.model.Access;
import com.va.uma.model.AppAccess;
import com.va.uma.model.Application;
import com.va.uma.model.Team;
import com.va.uma.model.UserAppAccess;
import com.va.uma.model.UserInfo;
import com.va.uma.model.UserInfo.UserStatus;
import com.va.uma.model.UserInfo.UserType;

public class SimulatorData {
	public static List<UserInfo> userList = new ArrayList<UserInfo>();
	static {
		UserInfo user1 = new UserInfo();
		user1.setUsername("creator1");
		user1.setPassword(MD5.md5("123456"));
		user1.setEmail("test@qq.com");
		user1.setType(UserType.creator);
		user1.setStatus(UserStatus.active);
		userList.add(user1);
		UserInfo user2 = new UserInfo();
		user2.setUsername("creator2");
		user2.setPassword(MD5.md5("123456"));
		user2.setEmail("test1@qq.com");
		user2.setType(UserType.creator);
		user2.setStatus(UserStatus.active);
		userList.add(user2);
	}

	public static List<Application> appList = new ArrayList<Application>();
	static {
		appList.add(new Application("app1"));
		appList.add(new Application("app2"));
		appList.add(new Application("app3"));
		appList.add(new Application("app4"));
		appList.add(new Application("app100"));
	}

	public static List<Access> accessList = new ArrayList<Access>();
	static {
		accessList.add(new Access("admin", "001"));
		accessList.add(new Access("user", "002"));
	}

	public static List<Team> teamList = new ArrayList<Team>();
	static {
		teamList.add(new Team("team1", "001"));
		teamList.add(new Team("team2", "002"));
	}

	public static List<AppAccess> appAccessList = new ArrayList<AppAccess>();
	static {
		for (Iterator<Application> iterator = appList.iterator(); iterator.hasNext();) {
			Application app = (Application) iterator.next();
			for (Iterator<Access> iterator2 = accessList.iterator(); iterator2.hasNext();) {
				Access access = (Access) iterator2.next();
				appAccessList.add(new AppAccess(app.getName(), access));
			}
		}
	}

	public static List<UserAppAccess> userAppAccessList = new ArrayList<UserAppAccess>();
	static {
		for (Iterator<UserInfo> iterator3 = userList.iterator(); iterator3.hasNext();) {
			UserInfo user = (UserInfo) iterator3.next();
			for (Iterator<Application> iterator = appList.iterator(); iterator.hasNext();) {
				Application app = (Application) iterator.next();
				userAppAccessList.add(new UserAppAccess(user, app.getName(), accessList.get(new Random().nextInt(2))));
			}
		}
	}
	public static void createDummyDataInDB(Session session) throws Exception{
		session.getTransaction().begin();
		for(UserInfo user : userList)
			session.saveOrUpdate(user);
		for(Application app : appList)
			session.saveOrUpdate(app);
		for(Access access : accessList)
			session.saveOrUpdate(access);
		for(Team team : teamList)
			session.saveOrUpdate(team);
		for(AppAccess appAccess : appAccessList)
			session.saveOrUpdate(appAccess);
		for(UserAppAccess userAppAccess : userAppAccessList)
			session.saveOrUpdate(userAppAccess);		
		session.getTransaction().commit();
	}
}
