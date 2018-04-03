package com.nebo_spider.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nebo_spider.dao.IColumnlog_countDao;
import com.nebo_spider.entity.Columnlog_count;
import com.nebo_spider.service.IColumnlog_countService;

@Service("columnlog_countService")
public class Columnlog_countService implements IColumnlog_countService{
	@Autowired
	private IColumnlog_countDao Columnlog_countDao;
	@Override
	public List<Columnlog_count> getData(String startDate, String endDate,String name) {
		List<Columnlog_count> columnlog_count=Columnlog_countDao.getData(startDate,endDate, name);
		return columnlog_count;
	}
}
