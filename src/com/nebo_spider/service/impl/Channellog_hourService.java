package com.nebo_spider.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nebo_spider.dao.IChannellog_hourDao;
import com.nebo_spider.entity.Channellog_hour;
import com.nebo_spider.service.IChannellog_hourService;
@Service("channellog_hourService")
public class Channellog_hourService implements IChannellog_hourService{
	@Autowired
	private IChannellog_hourDao channellog_hourDao;
	@Override
	public List<Channellog_hour> getData( String startDate, String endDate,String name) {
		List<Channellog_hour> channellog_hour=channellog_hourDao.getData(startDate,endDate, name);
		return channellog_hour;
	}
}
