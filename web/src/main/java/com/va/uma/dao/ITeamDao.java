package com.va.uma.dao;

import java.util.List;

import com.va.uma.model.Team;

public interface ITeamDao {

	void save(Team entity);

	void update(Team entity);

	void delete(Team entity);

	Team findById(String id);

	List<Team> listAll();

}