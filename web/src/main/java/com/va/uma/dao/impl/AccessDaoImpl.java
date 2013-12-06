package com.va.uma.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.va.uma.dao.BaseDao;
import com.va.uma.dao.IAccessDao;
import com.va.uma.model.Access;

@Repository("accessDefDao")
@Transactional
public class AccessDaoImpl extends BaseDao implements IAccessDao {

	@Override
	public void save(Access entity) {
		super.save(entity);
	}
	@Override
	public void update(Access entity) {
		super.update(entity);
	}
	@Override
	public void delete(Access entity) {
		super.delete(entity);
	}

	@Override
	public Access findById(String id) {
		return (Access) super.findById(Access.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Access> listAll() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Access";
		Query query = session.createQuery(hql);
		return query.list();
	}

}