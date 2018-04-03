package com.nebo_spider.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nebo_spider.entity.Columnlog_day;

@Repository
@Transactional
public interface IColumnlog_dayDao {
	public List<Columnlog_day> getData(@Param(value="startDate")String startDate,@Param(value="endDate")String endDate,
			@Param(value="tvname")String name);
	public List<Columnlog_day> InitData();
	public List<Columnlog_day> InitSort1_1();
	public List<Columnlog_day> InitSort1_2();
	public List<Columnlog_day> InitSort2_1();
	public List<Columnlog_day> InitSort2_2();
	public List<Columnlog_day> InitSort3_1();
	public List<Columnlog_day> InitSort3_2();
	public List<Columnlog_day> InitSort4_1();
	public List<Columnlog_day> InitSort4_2();
	public List<Columnlog_day> InitSort5_1();
	public List<Columnlog_day> InitSort5_2();
	public List<Columnlog_day> InitSort6_1();
	public List<Columnlog_day> InitSort6_2();
}
