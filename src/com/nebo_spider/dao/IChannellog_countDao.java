package com.nebo_spider.dao;



import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nebo_spider.entity.Channellog_count;

@Repository
@Transactional
public interface IChannellog_countDao {
	
	public List<Channellog_count> getData(@Param(value="startDate")String startDate,@Param(value="endDate")String endDate,
			@Param(value="tvname")String name);
	
}
