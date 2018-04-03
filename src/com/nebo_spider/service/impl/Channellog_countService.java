package com.nebo_spider.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nebo_spider.dao.IChannellog_countDao;
import com.nebo_spider.entity.Channellog_count;
import com.nebo_spider.service.IChannellog_countService;

@Service("channellog_countService")
public class Channellog_countService implements IChannellog_countService{
	@Autowired
	private IChannellog_countDao Channellog_countDao;
	@Override
	public List<Channellog_count> getData(String startDate, String endDate,String name) {
		List<Channellog_count> channellog_count=Channellog_countDao.getData(startDate,endDate, name);
		return channellog_count;
	}

}
