package com.nebo_spider.util;

import com.nebo_spider.entity.Pager;


public class PagerUtil {
	/**
	 * @param allCount 总记录数
	 * @param offset   起始位置
	 * @param pageSize 每页显示的条数
	 */
	public static String getBar(String url, int allCounts, int curPageNO, int pageSize) {
		String rst = "";
		Pager pager = null;
		try {
			if (curPageNO < 1)
				curPageNO = 1;
			if (curPageNO > (int) Math.ceil((double) allCounts / pageSize))
				curPageNO = (int) Math.ceil((double) allCounts / pageSize);

			int offset = (curPageNO - 1) * pageSize;

			pager = new Pager(allCounts, offset, pageSize,curPageNO);
			pager.setCurPageNO(curPageNO);
		} catch (Exception e) {
			//System.out.println("getBar出错!");
		}
		
		if(url.startsWith("ajax"))//如果url是以'ajax'开头，则获取ajax工具条
			rst = pager.getAjaxToolBar(url);
		else
			rst = pager.getToolBar(url);
		return rst;
	}
	
	public static int getOffset(int rowCounts,int curPageNO, int pageSize){
		int offset=0;
		try {
			if (curPageNO < 1)
				curPageNO = 1;
			if (curPageNO > (int) Math.ceil((double) rowCounts / pageSize))
				curPageNO = (int) Math.ceil((double) rowCounts / pageSize);
			//计算offset
			offset = (curPageNO - 1) * pageSize;
		} catch (Exception e) {
			System.out.println("getOffset出错!");
		} 
	   if(offset<0){
		   offset=0;
	   }
	   return offset;
	}
	
	public static int getCurPageNO(String curPage){
			int curPageNO;
			 if (curPage==null||"".equals(curPage.trim())) {
		    	 curPageNO=1;//默认为第1页
		      } else {
		    	  try {
		    		  curPageNO = Integer.parseInt(curPage);//�õ���ǰҳ
				} catch (Exception e) {
					curPageNO=1;
				}
		      }
			 return curPageNO;
		}
}
