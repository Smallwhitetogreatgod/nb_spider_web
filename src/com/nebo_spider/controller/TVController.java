package com.nebo_spider.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nebo_spider.entity.Page;
import com.nebo_spider.entity.Pager;
import com.nebo_spider.service.IPageService;
import com.nebo_spider.util.HbaseUtil;

/**
 * 控制层
 * Created by  nebo
 *
 */
@Controller
@RequestMapping("tv")
public class TVController {
	@Autowired
	private IPageService pageService;
	/**
	 * 电视排名列表页面
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(ModelMap modelMap){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("youkuindex");
		return mv;
	}
	
	/**
	 * 各种指标曲线图
	 * @param modelMap
	 * @param tvId
	 * @return
	 */
	@RequestMapping("/detail")
	public ModelAndView detail(ModelMap modelMap,@RequestParam(value="tvId",required = false) String tvId,@RequestParam(value="tvname",required=false) String tvname,@RequestParam(value="allnumber",required=false) String allnumber){
		ModelAndView mv = new ModelAndView();	
		mv.addObject("tvId", tvId);
		mv.addObject("tvname",tvname);
		mv.addObject("allnumber",allnumber);

		mv.setViewName("youkudetail");
		return mv;
	}
	/**
	 * 展示电视剧列表页
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/list", method=RequestMethod.POST, produces = "text/html; charset=utf-8")
	public String list(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			//偏移量
			String pageIndex = request.getParameter("pageIndex");
			//每页显示数据条数
			String pageSize = request.getParameter("pageSize");
			if(pageSize.equals("")){
				pageSize = "10";
			}
			//搜索关键词
			String search = request.getParameter("search");
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("search", search);
			//分页数据组装在一起
			Pager<Page> pager = pageService.getPageByCondition(pageIndex, Integer.parseInt(pageSize), params);
			
			//列表数据
			map.put("list",pager.getList());
			//分页导航
			map.put("bar",pager.getPageBar("toPage"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.fromObject(map).toString(); 
	}

	/**
	 * 某个电视剧收视指数走势图
	 * @param modelMap
	 * @param tvId
	 * @param number
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/info", produces = "application/text; charset=utf-8")
	public String info(ModelMap modelMap,@RequestParam(value="tvId",required = false) String tvId,@RequestParam(value="number",required = false) String number){
		HbaseUtil hbaseUtil = new HbaseUtil();
		//查询历史版本数据
		List<Map<String, String>> versionList = hbaseUtil.getCellMoreVersion(HbaseUtil.TABLE_NAME, HbaseUtil.COLUMNFAMILY_1, number, tvId);
		
		//结合前端生成时间轴
		List<String> categories = new ArrayList<String>();
		
		//结合前端生成曲线图
		List<Map<String,Object>> outerList = new ArrayList<Map<String,Object>>();
		List<Long> innerList = new ArrayList<Long>();
		Map<String,Object> innerMap = new HashMap<String, Object>();
		StringBuffer xdata = new StringBuffer();
		int size = versionList.size();
		for (int i = size-1; i >=0; i--) {
			Map<String, String> map = versionList.get(i);
			innerList.add(Long.parseLong(map.get("value").replaceAll(",", "")));
			xdata.append(map.get("time"));
			categories.add(map.get("time"));
			if(i>0){
				xdata.append(",");
			}
		}
		innerMap.put("name", number);
		innerMap.put("data", innerList);
		outerList.add(innerMap);
		
		//按照highchart格式拼接数据
		Map<String,List> finalMap = new HashMap<String, List>();
		finalMap.put("categories", categories);
		finalMap.put("series", outerList);
		return JSONArray.fromObject(finalMap).toString();
	}
	
	
}
