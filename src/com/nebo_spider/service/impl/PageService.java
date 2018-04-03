package com.nebo_spider.service.impl;

import java.util.List;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.nebo_spider.entity.Page;
import com.nebo_spider.entity.Pager;
import com.nebo_spider.service.IPageService;
import com.nebo_spider.util.IDGenerator;
import com.nebo_spider.util.PagerUtil;
import com.nebo_spider.util.SolrUtil;
/**
 * 电视剧列表查询实现类
 * @author dajiangtai
 *
 */
@Service("pageService")
public class PageService implements IPageService {

	@Override
	public Pager<Page> getPageByCondition(String pageIndex, int pageSize,
			Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		String search = (String)map.get("search");
		//当前页校验
		int curPageNO = IDGenerator.getInt(pageIndex);
		//总记录数
		int allCount = SolrUtil.getCount(search);
		//偏移量
		int offset = PagerUtil.getOffset(allCount, curPageNO, pageSize);
		
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		//电视剧列表信息
		List<Page> list = SolrUtil.search(search,offset, pageSize,"","");
		//封装分页对象
		Pager<Page> pager = new Pager<Page>(allCount, offset, pageSize, curPageNO);
		pager.setList(list);
		return pager;
	}

}
