package com.nebo_spider.service;

import java.util.List;

import com.nebo_spider.entity.Channellog_count;

public interface IChannellog_countService {
	public List<Channellog_count> getData(String startDate, String endDate,String name);
}
