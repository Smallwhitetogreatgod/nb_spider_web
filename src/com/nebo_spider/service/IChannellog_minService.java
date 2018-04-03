package com.nebo_spider.service;

import java.util.List;

import com.nebo_spider.entity.Channellog_min;

public interface IChannellog_minService {
	public List<Channellog_min> getData(String startDate,String endDate,String name);
}
