package com.va.uma.dao;

import java.util.List;

import com.va.uma.model.Access;

public interface IAccessDao {

	void save(Access entity);

	void update(Access entity);

	void delete(Access entity);

	Access findById(String id);

	List<Access> listAll();

}