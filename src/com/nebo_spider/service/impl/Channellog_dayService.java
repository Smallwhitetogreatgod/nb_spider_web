package com.nebo_spider.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nebo_spider.dao.IChannellog_dayDao;
import com.nebo_spider.entity.Channellog_day;
import com.nebo_spider.service.IChannellog_dayService;
@Service("channellog_dayService")
public class Channellog_dayService implements IChannellog_dayService{
	@Autowired
	private IChannellog_dayDao channellog_dayDao;
	@Override
	public List<Channellog_day> getData( String startDate, String endDate,String name) {
		List<Channellog_day> channellog_day=channellog_dayDao.getData(startDate,endDate, name);
		return channellog_day;
	}
	@Override
	public List<Channellog_day> InitData(int px)
	{
		List<Channellog_day> channellog_day = null;
		switch(px)
		{
		case 1:channellog_day=channellog_dayDao.InitSort1_1();break;
		case -1:channellog_day=channellog_dayDao.InitSort1_2();break;
		case 2:channellog_day=channellog_dayDao.InitSort2_1();break;
		case -2:channellog_day=channellog_dayDao.InitSort2_2();break;
		case 3:channellog_day=channellog_dayDao.InitSort3_1();break;
		case -3:channellog_day=channellog_dayDao.InitSort3_2();break;
		case 4:channellog_day=channellog_dayDao.InitSort4_1();break;
		case -4:channellog_day=channellog_dayDao.InitSort4_2();break;
		case 5:channellog_day=channellog_dayDao.InitSort5_1();break;
		case -5:channellog_day=channellog_dayDao.InitSort5_2();break;
		case 6:channellog_day=channellog_dayDao.InitSort6_1();break;
		case -6:channellog_day=channellog_dayDao.InitSort6_2();break;
		}
		//channellog_day=channellog_dayDao.InitData();
		return channellog_day;
	}
}
