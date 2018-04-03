package com.nebo_spider.service;

import java.util.Map;

import com.nebo_spider.entity.Page;
import com.nebo_spider.entity.Pager;
/**
 * 电视剧列表查询接口
 * @author dajiangtai
 *
 */
public interface IPageService {
	//根据关键字查询电视剧列表信息
	public Pager<Page> getPageByCondition(String pageIndex,int pageSize, Map<String, Object> map) throws Exception;
}
