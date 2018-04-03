package com.nebo_spider.service;

import java.util.List;

import com.nebo_spider.entity.Columnlog_min;

public interface IColumnlog_minService {
	public List<Columnlog_min> getData(String startDate,String endDate,String name);
}
