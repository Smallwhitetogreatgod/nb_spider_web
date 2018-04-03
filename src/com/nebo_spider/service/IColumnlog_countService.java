package com.nebo_spider.service;

import java.util.List;

import com.nebo_spider.entity.Columnlog_count;

public interface IColumnlog_countService {
	public List<Columnlog_count> getData(String startDate, String endDate,String name);
}
