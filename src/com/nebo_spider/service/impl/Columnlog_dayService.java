package com.nebo_spider.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nebo_spider.dao.IColumnlog_dayDao;
import com.nebo_spider.entity.Columnlog_day;
import com.nebo_spider.service.IColumnlog_dayService;
@Service("columnlog_dayService")
public class Columnlog_dayService implements IColumnlog_dayService{
	@Autowired
	private IColumnlog_dayDao columnlog_dayDao;
	@Override
	public List<Columnlog_day> getData( String startDate, String endDate,String name) {
		List<Columnlog_day> columnlog_day=columnlog_dayDao.getData(startDate,endDate, name);
		return columnlog_day;
	}
	@Override
	public List<Columnlog_day> InitData(int px)
	{
		List<Columnlog_day> columnlog_day=null;//columnlog_dayDao.InitData();
		switch(px)
		{
		case 1:columnlog_day=columnlog_dayDao.InitSort1_1();break;
		case -1:columnlog_day=columnlog_dayDao.InitSort1_2();break;
		case 2:columnlog_day=columnlog_dayDao.InitSort2_1();break;
		case -2:columnlog_day=columnlog_dayDao.InitSort2_2();break;
		case 3:columnlog_day=columnlog_dayDao.InitSort3_1();break;
		case -3:columnlog_day=columnlog_dayDao.InitSort3_2();break;
		case 4:columnlog_day=columnlog_dayDao.InitSort4_1();break;
		case -4:columnlog_day=columnlog_dayDao.InitSort4_2();break;
		case 5:columnlog_day=columnlog_dayDao.InitSort5_1();break;
		case -5:columnlog_day=columnlog_dayDao.InitSort5_2();break;
		case 6:columnlog_day=columnlog_dayDao.InitSort6_1();break;
		case -6:columnlog_day=columnlog_dayDao.InitSort6_2();break;
		}
		return columnlog_day;
	}
}
