package com.va.uma.dao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.va.uma.dao.BaseDao;
import com.va.uma.dao.IUserInfoDao;
import com.va.uma.model.UserAppAccess;
import com.va.uma.model.UserInfo;

@Repository("userInfoDao")
@Transactional
public class UserInfoDaoImpl extends BaseDao implements IUserInfoDao {
	@Override
	public void save(UserInfo entity) {
		super.save(entity);
	}
	@Override
	public void update(UserInfo entity) {
		super.update(entity);
	}
	@Override
	public void delete(UserInfo entity) {
		super.delete(entity);
	}
	@Override
	public UserInfo findById(String id) {
		return (UserInfo) super.findById(UserInfo.class, id);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<UserAppAccess> listUserAppAccess(String userId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from UserAppAccess where userInfo.id = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, userId);
		return query.list();
	}
	@SuppressWarnings("rawtypes")
	@Override
	public boolean isAppAccessUsed(String appName, String accessId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from UserAppAccess where appName = ? and access.id = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, appName);
		query.setParameter(1, accessId);
		List list = query.list();
		return !CollectionUtils.isEmpty(list);
	}
	@Override
	public UserInfo findByUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from UserInfo where username=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, username);
		return (UserInfo) query.uniqueResult();
	}
	@Override
	public UserInfo findByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from UserInfo where email=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, email);
		return (UserInfo) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfo> listAll() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from UserInfo";
		Query query = session.createQuery(hql);
		return query.list();
	}
	@Override
	public void saveUserAppAccess(UserAppAccess entity) {
		super.save(entity);
	}
	@Override
	public void deleteUserAppAccess(UserAppAccess entity) {
		super.delete(entity);
	}
	@Override
	public void updateUserAppAccess(UserAppAccess entity) {
		super.update(entity);
	}
	@Override
	public void deleteAllAppAccessByUserId(String userId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "delete from UserAppAccess where userInfo.id = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, userId);
		query.executeUpdate();
	}

}