package com.nebo_spider.service;

import java.util.List;

import com.nebo_spider.entity.Columnlog_hour;

public interface IColumnlog_hourService {
	public List<Columnlog_hour> getData(String startDate,String endDate,String name);
}
