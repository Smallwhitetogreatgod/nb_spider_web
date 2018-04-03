package com.nebo_spider.service;

import java.util.List;

import com.nebo_spider.entity.Columnlog_day;

public interface IColumnlog_dayService {
	public List<Columnlog_day> getData(String startDate,String endDate,String name);
	public List<Columnlog_day> InitData(int px);
}
