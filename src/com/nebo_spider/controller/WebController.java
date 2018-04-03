package com.nebo_spider.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.nebo_spider.entity.Channellog_count;
import com.nebo_spider.entity.Channellog_day;
import com.nebo_spider.entity.Channellog_hour;
import com.nebo_spider.entity.Channellog_min;
import com.nebo_spider.entity.Columnlog_count;
import com.nebo_spider.entity.Columnlog_day;
import com.nebo_spider.entity.Columnlog_hour;
import com.nebo_spider.entity.Columnlog_min;
import com.nebo_spider.service.IChannellog_countService;
import com.nebo_spider.service.IChannellog_dayService;
import com.nebo_spider.service.IChannellog_hourService;
import com.nebo_spider.service.IChannellog_minService;
import com.nebo_spider.service.IColumnlog_countService;
import com.nebo_spider.service.IColumnlog_dayService;
import com.nebo_spider.service.IColumnlog_hourService;
import com.nebo_spider.service.IColumnlog_minService;


@Controller
@RequestMapping()
public class WebController implements ServletContextAware  {
	private final Logger log = Logger.getLogger(this.getClass().getName());
	private ServletContext config;
	@Autowired
	private IChannellog_countService channellog_countService;
	@Autowired
	private IChannellog_hourService channellog_hourService;
	@Autowired
	private IChannellog_minService channellog_minService;
	@Autowired
	private IChannellog_dayService channellog_dayService;
	@Autowired
	private IColumnlog_countService columnlog_countService;
	@Autowired
	private IColumnlog_dayService columnlog_dayService;
	@Autowired
	private IColumnlog_hourService columnlog_hourService;
	@Autowired
	private IColumnlog_minService columnlog_minService;
	@Override
	public void setServletContext(ServletContext arg0) {
		this.config = arg0;
	}
	/**
	 * 跳转到test页面
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("test")
	public ModelAndView order_list(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//System.out.println("ok");
		ModelAndView mv = new ModelAndView();
//		//mv.addObject("aaa", channellog_countService.getData(4));
//		mv.addObject("aaa",channellog_dayService.getData("2016-7-1", "2016-7-2").get(5));
//		//System.out.println(channellog_dayService.getData("2016-7-1", "2016-7-8"));
		mv.addObject("aaa", "d");
		mv.setViewName("test");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="/init", method=RequestMethod.POST,produces={"text/html;charset=UTF-8"})
	public String Init(HttpServletRequest request) throws IOException{		
		
		int name=Integer.parseInt(request.getParameter("name"));
		int page=Integer.parseInt(request.getParameter("page"));
		int px=Integer.parseInt(request.getParameter("px"));
		//System.out.println(name);
		List<Channellog_day> SchdList = new ArrayList<Channellog_day>();
		List<Columnlog_day> ScodList = new ArrayList<Columnlog_day>();
		System.out.println(page);
		page=(page-1)*10;
		int Max=0;
		//JSONObject.fromObject("").toString();
		if(name==1)
		{
			List<Channellog_day> chdList=channellog_dayService.InitData(px);
			
			if(page>=chdList.size()||(chdList.size())-page<10)
			{
				page=chdList.size()/10*10;
				Max=chdList.size();
			}else Max=page+10;
			for(int i=page;i<Max;i++)
				SchdList.add(chdList.get(i));
			return  SchdList.toString()+"&&"+chdList.size()+","+(page/10+1);
		}
		else
		{
			List<Columnlog_day> codList=columnlog_dayService.InitData(px);
			if(page>=codList.size()||(codList.size())-page<10)
			{
				page=codList.size()/10*10;
				Max=codList.size();
			}else Max=page+10;
			for(int i=page;i<Max;i++)
				ScodList.add(codList.get(i));
			return  ScodList.toString()+"&&"+codList.size()+","+(page/10+1);
		}
		//return  SchdList.toString();//m.toString();//JSONObject.fromObject(m).toString();//sqlDateToJsonObj(chdList.get(0));//JSONObject.fromObject(chdList).toString();
	}
	public String minDo(int len,String startDate,String endDate,String[] tvname,int select,int channel)
	{
		String returnStr="";
		List<Channellog_min> chmList=null;
		List<Columnlog_min> comList=null;
		for(int i=0;i<len;i++)
		{
			if(channel==1)
			{
				//System.out.println(startDate+" ,"+endDate);
				chmList=channellog_minService.getData(startDate, endDate,tvname[i]);	
				returnStr+=DateListMin(chmList,select).toString();
			}
			else
			{
				comList=columnlog_minService.getData(startDate, endDate,tvname[i]);	
				returnStr+=DateListMinColumn(comList,select).toString();
			}
			if(i<(len-1))
				returnStr+="&&";
		}
		return returnStr;
	}
	public String hourDo(int len,String startDate,String endDate,String[] tvname,int select,int channel)
	{
		String returnStr="";
		List<Channellog_hour> chhList=null;
		List<Columnlog_hour> cohList=null;
		for(int i=0;i<len;i++)
		{
			if(channel==1)
			{
				chhList=channellog_hourService.getData(startDate, endDate,tvname[i]);	
				returnStr+=DateListHour(chhList,select).toString();
			}
			else
			{
				cohList=columnlog_hourService.getData(startDate, endDate,tvname[i]);	
				returnStr+=DateListHourColumn(cohList,select).toString();
			}
			if(i<(len-1))
				returnStr+="&&";
		}
		return returnStr;
	}
	public String dayDo(int len,String startDate,String endDate,String[] tvname,int select,int channel)
	{
		String returnStr="";
		List<Channellog_day> chdList=null;
		List<Channellog_count> chcList=null;
		List<Columnlog_day> codList=null;
		List<Columnlog_count> cocList=null;
		for(int i=0;i<len;i++)
		{
			if(select==1||select==7)
			{
				if(channel==1)
				{
					chcList=channellog_countService.getData(startDate, endDate,tvname[i]);	
				  	returnStr+=DateListCount(chcList,select).toString();
				}
				else
				{
					cocList=columnlog_countService.getData(startDate, endDate,tvname[i]);	
				  	returnStr+=DateListCountColumn(cocList,select).toString();
				}
			}
			else if(select<7)
			{
				if(channel==1)
				{
					chdList=channellog_dayService.getData(startDate, endDate,tvname[i]);	
					returnStr+=DateListDay(chdList,select).toString();
				}
				else
				{
					codList=columnlog_dayService.getData(startDate, endDate,tvname[i]);	
					returnStr+=DateListDayColumn(codList,select).toString();
				}
			}
			if(i<(len-1))
				returnStr+="&&";
		}
		return returnStr;
	}
	/**
	 * 异步读取数据
	 * @return
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping(value="/date", method=RequestMethod.POST,produces={"text/html;charset=UTF-8"})
	public String read(HttpServletRequest request) throws IOException{		
		
		String startDate=request.getParameter("startDate");
		String endDate=request.getParameter("endDate");
		String name=request.getParameter("name");
		
		int channel=Integer.parseInt(request.getParameter("channel"));
		int select=Integer.parseInt(request.getParameter("select"));
		int Contrast=Integer.parseInt(request.getParameter("Contrast"));
		int DateState=Integer.parseInt(request.getParameter("DateState"));
		
		if(name==null)
			return "no";
		String tvname[]=name.split(",");
		int num=tvname.length;
		String returnStr="";
		if(DateState==1)
		{
			returnStr=dayDo(num,startDate.split(" ")[0],endDate.split(" ")[0],tvname,select,channel);
			if(Contrast!=0)
			{
				String ContrastStr=dayDo(num,startDate.split(" ")[0],endDate.split(" ")[0],tvname,Contrast,channel);
				returnStr+="&&"+ContrastStr;
			}
		}else if(DateState==2)
		{
			returnStr=hourDo(num,startDate,endDate,tvname,select,channel);
			if(Contrast!=0)
			{
				String ContrastStr=hourDo(num,startDate,endDate,tvname,Contrast,channel);
				returnStr+="&&"+ContrastStr;
			}
		}else if(DateState==3)
		{
			returnStr=minDo(num,startDate,endDate,tvname,select,channel);
			if(Contrast!=0)
			{
				String ContrastStr=minDo(num,startDate,endDate,tvname,Contrast,channel);
				returnStr+="&&"+ContrastStr;
			}
		}
		
		return returnStr;//m.toString();//JSONObject.fromObject(m).toString();//sqlDateToJsonObj(chdList.get(0));//JSONObject.fromObject(chdList).toString();
	}
	public Map<String, Object> DateListMinColumn(List<Columnlog_min> comList,int select)
	{
		Map<String,Object> m = new HashMap<String, Object>();
		for(int i=0;i<comList.size();i++)
		{
			Columnlog_min com=comList.get(i);
			String Date=com.getTvdate()+" "+com.getTvmin();
			switch(select)
			{
			case 1:m.put(Date,com.getAvgnum());break;
			case 2:m.put(Date,com.getTvrating());break;
			case 3:m.put(Date,com.getMarketshare());break;
			case 4:m.put(Date,com.getReachnum());break;
			case 5:m.put(Date,com.getReachrating());break;
			}
		}
		return m;
	}
	public Map<String, Object> DateListMin(List<Channellog_min> chmList,int select)
	{
		Map<String,Object> m = new HashMap<String, Object>();
		for(int i=0;i<chmList.size();i++)
		{
			Channellog_min chm=chmList.get(i);
			String Date=chm.getTvdate()+" "+chm.getTvmin();
			switch(select)
			{
			case 1:m.put(Date,chm.getAvgnum());break;
			case 2:m.put(Date,chm.getTvrating());break;
			case 3:m.put(Date,chm.getMarketshare());break;
			case 4:m.put(Date,chm.getReachnum());break;
			case 5:m.put(Date,chm.getReachrating());break;
			}
		}
		return m;
	}
	public Map<String, Object> DateListHourColumn(List<Columnlog_hour> cohList,int select)
	{
		Map<String,Object> m = new HashMap<String, Object>();
		for(int i=0;i<cohList.size();i++)
		{
			Columnlog_hour coh=cohList.get(i);
			String Date=coh.getTvdate()+" "+coh.getTvhour();
			switch(select)
			{
			case 1:m.put(Date,coh.getAvgnum());break;
			case 2:m.put(Date,coh.getTvrating());break;
			case 3:m.put(Date,coh.getMarketshare());break;
			case 4:m.put(Date,coh.getReachnum());break;
			case 5:m.put(Date,coh.getReachrating());break;
			}
		}
		return m;
	}
	public Map<String, Object> DateListHour(List<Channellog_hour> chhList,int select)
	{
		Map<String,Object> m = new HashMap<String, Object>();
		for(int i=0;i<chhList.size();i++)
		{
			Channellog_hour chh=chhList.get(i);
			String Date=chh.getTvdate()+" "+chh.getTvhour();
			switch(select)
			{
			case 1:m.put(Date,chh.getAvgnum());break;
			case 2:m.put(Date,chh.getTvrating());break;
			case 3:m.put(Date,chh.getMarketshare());break;
			case 4:m.put(Date,chh.getReachnum());break;
			case 5:m.put(Date,chh.getReachrating());break;
			}
		}
		return m;
	}
	public Map<String, Object> DateListCountColumn(List<Columnlog_count> codList,int select)
	{
		Map<String,Object> m = new HashMap<String, Object>();
		for(int i=0;i<codList.size();i++)
		{
			String Date=codList.get(i).getTvdate();
			if(select==1)
				m.put(Date,codList.get(i).getNum());
			else if(select==7)
				m.put(Date,(double)codList.get(i).getTimelen()/(double)codList.get(i).getNum());
		}
		return  m;
	}
	public Map<String, Object> DateListCount(List<Channellog_count> chdList,int select)
	{
		Map<String,Object> m = new HashMap<String, Object>();
		for(int i=0;i<chdList.size();i++)
		{
			String Date=chdList.get(i).getTvdate();
			if(select==1)
				m.put(Date,chdList.get(i).getNum());
			else if(select==7)
				m.put(Date,(double)chdList.get(i).getTimelen()/(double)chdList.get(i).getNum());
		}
		return  m;
	}
	public Map<String, Object> DateListDayColumn(List<Columnlog_day> codList,int select)
	{
		Map<String,Object> m = new HashMap<String, Object>();
		for(int i=0;i<codList.size();i++)
		{
			String Date=codList.get(i).getTvdate();
			switch(select)
			{
			case 2:m.put(Date,codList.get(i).getAvgnum());break;
			case 3:m.put(Date,codList.get(i).getTvrating());break;
			case 4:m.put(Date,codList.get(i).getMarketshare());break;
			case 5:m.put(Date,codList.get(i).getReachnum());break;
			case 6:m.put(Date,codList.get(i).getReachrating());break;
			}
		}
		return  m;
	}
	public Map<String, Object> DateListDay(List<Channellog_day> chdList,int select)
	{
		Map<String,Object> m = new HashMap<String, Object>();
		for(int i=0;i<chdList.size();i++)
		{
			String Date=chdList.get(i).getTvdate();
			switch(select)
			{
			case 2:m.put(Date,chdList.get(i).getAvgnum());break;
			case 3:m.put(Date,chdList.get(i).getTvrating());break;
			case 4:m.put(Date,chdList.get(i).getMarketshare());break;
			case 5:m.put(Date,chdList.get(i).getReachnum());break;
			case 6:m.put(Date,chdList.get(i).getReachrating());break;
			}
			
//			if(i==0)
//				m.put(Date,1);
//			else
//			{
//				Iterator iter = m.entrySet().iterator();
//				boolean flag=true;
//				while (iter.hasNext()) {
//					Map.Entry entry = (Map.Entry) iter.next();
//					String Datestr = (String) entry.getKey();
//					int num = (Integer) entry.getValue();
//					if(Datestr.equals(Date))
//					{
//						m.put(Datestr,num+1);
//						flag=false;
//						break;
//					}
//				}
//				if(flag)
//					m.put(Date,1);
//			}
		}
		return m;
	}
}

