package com.nebo_spider.service;

import java.util.List;

import com.nebo_spider.entity.Channellog_day;

public interface IChannellog_dayService {
	public List<Channellog_day> getData(String startDate,String endDate,String name);
	public List<Channellog_day> InitData(int px);
}
