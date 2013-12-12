package com.va.uma.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.va.uma.model.UserInfo;
import com.va.uma.service.IDataService;
import com.va.uma.util.SimulatorData;

@Controller
public class DummyDataCreateAction extends BaseAction {
	@Autowired
	private IDataService dataService;
	@RequestMapping("/data/dummy/create.do")
	public @ResponseBody List<UserInfo> createDummyDataInDB() throws Exception{
		dataService.createDummyData();
		return SimulatorData.userList;
	}
	public void setDataService(IDataService dataService) {
		this.dataService = dataService;
	}
}
