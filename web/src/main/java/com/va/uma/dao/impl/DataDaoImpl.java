package com.va.uma.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.va.uma.dao.BaseDao;
import com.va.uma.dao.IDataDao;
import com.va.uma.util.SimulatorData;

@Repository("dataDao")
@Transactional
public class DataDaoImpl extends BaseDao implements IDataDao {
	@Override
	public void createDummyData() throws Exception {
		SimulatorData.createDummyDataInDB(super.getSession());
	}

}
