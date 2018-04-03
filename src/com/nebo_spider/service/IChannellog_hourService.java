package com.nebo_spider.service;

import java.util.List;

import com.nebo_spider.entity.Channellog_hour;

public interface IChannellog_hourService {
	public List<Channellog_hour> getData(String startDate,String endDate,String name);
	
}
