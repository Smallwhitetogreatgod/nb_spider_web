package com.nebo_spider.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nebo_spider.dao.IColumnlog_minDao;
import com.nebo_spider.entity.Columnlog_min;
import com.nebo_spider.service.IColumnlog_minService;

@Service("columnlog_minService")
public class Columnlog_minService implements IColumnlog_minService{
	@Autowired
	private IColumnlog_minDao columnlog_minDao;
	@Override
	public List<Columnlog_min> getData( String startDate, String endDate,String name) {
		List<Columnlog_min> Columnlog_min=columnlog_minDao.getData(startDate,endDate, name);
		return Columnlog_min;
	}
}
