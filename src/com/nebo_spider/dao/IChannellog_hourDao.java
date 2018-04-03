package com.nebo_spider.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nebo_spider.entity.Channellog_hour;
@Repository
@Transactional
public interface IChannellog_hourDao {
	public List<Channellog_hour> getData(@Param(value="startDate")String startDate,@Param(value="endDate")String endDate,
			@Param(value="tvname")String name);
}
