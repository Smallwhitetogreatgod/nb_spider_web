package com.nebo_spider.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nebo_spider.dao.IChannellog_minDao;
import com.nebo_spider.entity.Channellog_min;
import com.nebo_spider.service.IChannellog_minService;
@Service("channellog_minService")
public class Channellog_minService implements IChannellog_minService{
	@Autowired
	private IChannellog_minDao channellog_minDao;
	@Override
	public List<Channellog_min> getData( String startDate, String endDate,String name) {
		List<Channellog_min> Channellog_min=channellog_minDao.getData(startDate,endDate, name);
		return Channellog_min;
	}
}
