package com.va.uma.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.va.uma.dao.BaseDao;
import com.va.uma.dao.IApplicationDao;
import com.va.uma.model.AppAccess;
import com.va.uma.model.Application;

@Repository("applicationDao")
@Transactional
public class ApplicationDaoImpl extends BaseDao implements IApplicationDao {

	public void saveAppAccess(AppAccess entity) {
		super.save(entity);
	}

	public void updateAppAccess(AppAccess entity) {
		super.update(entity);
	}

	public void delete(AppAccess entity) {
		super.delete(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AppAccess> listAllAppAccess() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from AppAccess";
		Query query = session.createQuery(hql);
		return query.list();
	}

	public AppAccess findById(String id) {
		return (AppAccess) super.findById(AppAccess.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Application> listAllApp() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Application";
		Query query = session.createQuery(hql);
		return query.list();
	}

}