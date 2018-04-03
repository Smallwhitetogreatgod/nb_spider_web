package com.nebo_spider.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nebo_spider.dao.IColumnlog_hourDao;
import com.nebo_spider.entity.Columnlog_hour;
import com.nebo_spider.service.IColumnlog_hourService;
@Service("columnlog_hourService")
public class Columnlog_hourService implements IColumnlog_hourService{
	@Autowired
	private IColumnlog_hourDao columnlog_hourDao;
	@Override
	public List<Columnlog_hour> getData( String startDate, String endDate,String name) {
		List<Columnlog_hour> Columnlog_hour=columnlog_hourDao.getData(startDate,endDate, name);
		return Columnlog_hour;
	}
}
