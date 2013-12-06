package com.va.uma.dao;

import java.util.List;

import com.va.uma.model.AppAccess;
import com.va.uma.model.Application;

public interface IApplicationDao {

	void saveAppAccess(AppAccess entity);

	void updateAppAccess(AppAccess entity);

	void delete(AppAccess entity);

	List<AppAccess> listAllAppAccess();

	List<Application> listAllApp();

	AppAccess findById(String id);

}