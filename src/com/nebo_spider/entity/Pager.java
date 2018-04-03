package com.nebo_spider.entity;



import java.util.List;
/**
 * 分页
 * @author dajiangtai
 * @param <T> 类型
 */
public class Pager<T> {
	public int curPageNO; // 当前页
	public int pageSize; // 每页显示的记录数
	public int rowsCount; // 记录行数
	public int pageCount; // 页数
	public int prePageNO; // 前一页
	public int nextPageNO;// 后一页
    public List<T> list;
    
	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	/**
	 * @param allCount
	 *            总记录数
	 * @param offset
	 *            起始位置
	 * @param pageSize
	 *            每页显示的条数
	 */
	public Pager(int allCount, int offset, int pageSize,int curPageNO) {
		this.rowsCount = allCount;
		if(offset>allCount){
			offset=allCount;
		}
//		this.curPageNO = (offset == 0) ? 1 : (int) Math.ceil((double) offset
//				/ pageSize);
		if (curPageNO < 1)
			curPageNO = 1;
		if (curPageNO > (int) Math.ceil((double) allCount / pageSize))
			curPageNO = (int) Math.ceil((double) allCount / pageSize);
		this.curPageNO=curPageNO;
		this.pageSize = pageSize;
		
		this.pageCount = (int) Math.ceil((double) allCount / pageSize);
		this.prePageNO = previous();
		this.nextPageNO = next();
	}
	
	public Pager(int allCount, int offset, int pageSize) {
		this.rowsCount = allCount;
		if(offset>allCount){
			offset=allCount;
		}
		this.curPageNO = (offset == 0) ? 1 : (int) Math.ceil((double) offset
				/ pageSize);
		this.pageSize = pageSize;
		
		this.pageCount = (int) Math.ceil((double) allCount / pageSize);
		this.prePageNO = previous();
		this.nextPageNO = next();
	}
	

	public Pager() {
	}

	public int getCurPageNO() {
		return curPageNO;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getRowsCount() {
		return rowsCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public int first() {
		return 1;
	}

	public int last() {
		return pageCount;
	}

	public int previous() {
		return (curPageNO - 1 < 1) ? 1 : curPageNO - 1;
	}

	public int getPrePageNO() {
		return prePageNO;
	}

	public void setPrePageNO(int prePageNO) {
		this.prePageNO = prePageNO;
	}

	public int getNextPageNO() {
		return nextPageNO;
	}

	public void setNextPageNO(int nextPageNO) {
		this.nextPageNO = nextPageNO;
	}

	public int next() {
		int next=1;
		if(curPageNO + 1> pageCount){
			next=this.pageCount;
		}else{
			next=this.curPageNO+1;
		}
		return next;
	}

	public boolean isFirst() {
		return (curPageNO == 1) ? true : false;
	}

	// 针对mysql分页 起始位置
	public int getBeginOffset() {
		return (curPageNO - 1 < 1) ? 0 : (curPageNO - 1) * pageSize;
	}

	// 针对mysql分页 结束的位置
	public int getEndOffset() {
		return curPageNO * pageSize;
	}

	public boolean isLast() {
		return (curPageNO == pageCount) ? true : false;
	}

	public void setCurPageNO(int curPageNO) {
		this.curPageNO = curPageNO;
	}

	public String toString() {
		return "Pager类： " + " curPageNO = " + curPageNO + " limit = "
				+ pageSize + " rowsCount = " + rowsCount + " pageCount = "
				+ pageCount;
	}

	/**
	 * 获取工具条 不用图片的，用下拉框
	 * 
	 * @return String
	 */
	public String getToolBar(String url) {
		String temp = "";
		if (url.indexOf("?") == -1) {
			temp = "?";
		} else {
			temp = "&";
		}
		String str = "";
		str += "";
		if (isFirst())
			str += "首页 上一页&nbsp;";
		else {
			str += "<a href='" + url + temp + "curPageNO=1'>首页</a>&nbsp;";
			str += "<a href='" + url + temp + "curPageNO=" + previous()
					+ "'>上一页</a>&nbsp;";
		}
		if (isLast() || rowsCount == 0)
			str += "下一页 尾页&nbsp;";
		else {
			str += "<a href='" + url + temp + "curPageNO=" + next()
					+ "'>下一页</a>&nbsp;";
			str += "<a href='" + url + temp + "curPageNO=" + pageCount
					+ "'>尾页</a>&nbsp;";
		}
		str += "&nbsp;共<b>" + rowsCount + "</b>条记录&nbsp;";
		str += "&nbsp;转到<select name='page' onChange=\"location='"
				+ url.replaceAll("../", "") + temp
				+ "curPageNO='+this.options[this.selectedIndex].value\">";
		int begin = (curPageNO > 10) ? curPageNO - 10 : 1;
		int end = (pageCount - curPageNO > 10) ? curPageNO + 10 : pageCount;
		for (int i = begin; i <= end; i++) {
			if (i == curPageNO)
				str += "<option value='" + i + "' selected>第" + i
						+ "页</option>";
			else
				str += "<option value='" + i + "'>第" + i + "页</option>";
		}
		str += "</select>";
		return str;
	}

	/**
	 * 获取ajax工具条 function, 换页的ajax函数,其它参数为要转到的页码
	 * 
	 * @return String
	 */
	public String getAjaxToolBar(String function) {
		String str = "";
		if (isFirst())
			str += "首页 上一页&nbsp;";
		else {
			str += "<a href=\"javascript:" + function + "(1);\">首页</a>&nbsp;";
			str += "<a href=\"javascript:" + function + "(" + previous()
					+ ");\">上一页</a>&nbsp;";
		}
		if (isLast() || rowsCount == 0)
			str += "下一页 尾页&nbsp;";
		else {
			str += "<a href=\"javascript:" + function + "(" + next()
					+ ");\">下一页</a>&nbsp;";
			str += "<a href=\"javascript:" + function + "(" + pageCount
					+ ");\">尾页</a>&nbsp;";
		}
		str += "&nbsp;共<b>" + rowsCount + "</b>条记录&nbsp;";

		str += "&nbsp;转到<select onChange=\"" + function
				+ "(this.options[this.selectedIndex].value)\">";
		for (int i = 1; i <= pageCount; i++) {
			if (i == curPageNO)
				str += "<option value='" + i + "' selected>第" + i
						+ "页</option>";
			else
				str += "<option value='" + i + "'>第" + i + "页</option>";
		}
		str += "</select>";
		return str;
	}
	
	/**
	 * 获取分页工具条 function, 换页的ajax函数,其它参数为要转到的页码
	 * 
	 * @return String
	 */
	public String getPageBar2(String function) {
		String str = "";
		str += ""+
			   "共"+rowsCount+"条记录，当前第"+curPageNO+"/"+pageCount+"页，每页"+pageSize+"条记录"+
			   "<span class='float-right'>";
		if (isFirst())
			str += "<span>首页</span><span>上一页</span>";
		else {
			str += "<span><a href=\"javascript:"+function+"(1);\">首页</a></span>" +
				   "<span><a href=\"javascript:"+function+"("+previous()+");\">上一页</a></span>";
		}
		if (isLast() || rowsCount == 0)
			str += "<span>下一页</span><span>尾页</span>";
		else {
			str += "<span><a href=\"javascript:"+function+"("+next()+");\">下一页</a></span>" +
				   "<span><a href=\"javascript:" + function + "(" + pageCount
					+ ");\">尾页</a></span>";
		}
		str += "<span>转到第<input type='text' class='go-page' value=''>页&nbsp;" +
				"<a href=\"javascript:void(0);\" onclick=\""+function+"(document.getElementsByClassName('go-page')[0].value.trim())\">跳转</a></span>";
		str += "</span>";
		str += "";
		return str;
	}

	public String getPageBar(String function) {
		
		String str = "";
		str += "<span>共"+rowsCount+"条记录，当前第"+curPageNO+"/"+pageCount+"页，每页显示";
		str += "<select name=\"pageSize\">";
		str += "<option value=\"10\">10</option>";
		str += "<option value=\"20\">20</option>";
		str += "<option value=\"50\">50</option>";
		str += "<option value=\"100\">100</option>";
		str += "</select>";
		str += "条记录";
		str += "</span>";
		str += "<span class=\"float-right\">";
		if (isFirst()){
			str += "<a>	首页	</a>";
			str += "<a>	上一页	</a>";
		}else {
			str += "<a class=\"active\" href=\"javascript:"+function+"(1);\">	首页	</a>";
			str += "<a class=\"active\" href=\"javascript:"+function+"("+previous()+");\">	上一页	</a>";
		}
		if (isLast() || rowsCount == 0){
			str += "<a>	下一页	</a>";
			str += "<a>	尾页	</a>";
		}else {
			str += "<a class=\"active\" href=\"javascript:"+function+"("+next()+");\">	下一页	</a>";
			str += "<a class=\"active\" href=\"javascript:" + function + "(" + pageCount
					+ ");\">	尾页	</a>";
		}
		//str += "<a class=\"active\">下一页</a>";
		//str += "<a class=\"active\">尾页</a>";
		str += "<span>";
		str += "	跳转到第";
		str += "<input type=\"text\" maxlength=\"2\" style=\"width: 20px\">";
		str += "页	";
		str += "<a class=\"green-btn\"  href=\"javascript:void(0);\" onclick=\""+function+"(document.getElementsByClassName('go-page')[0].value.trim())\">跳转</a>";
		str += "</span>";
		str += "</span>";


		/*str += ""+
			   "共"+rowsCount+"条记录，当前第"+curPageNO+"/"+pageCount+"页，每页"+pageSize+"条记录"+
			   "<span class='float-right'>";
		if (isFirst())
			str += "<span>首页</span><span>上一页</span>";
		else {
			str += "<span><a href=\"javascript:"+function+"(1);\">首页</a></span>" +
				   "<span><a href=\"javascript:"+function+"("+previous()+");\">上一页</a></span>";
		}
		if (isLast() || rowsCount == 0)
			str += "<span>下一页</span><span>尾页</span>";
		else {
			str += "<span><a href=\"javascript:"+function+"("+next()+");\">下一页</a></span>" +
				   "<span><a href=\"javascript:" + function + "(" + pageCount
					+ ");\">尾页</a></span>";
		}
		str += "<span>转到第<input type='text' class='go-page' value=''>页&nbsp;" +
				"<a href=\"javascript:void(0);\" onclick=\""+function+"(document.getElementsByClassName('go-page')[0].value.trim())\">跳转</a></span>";
		str += "</span>";
		str += "";*/
		return str;
	}

	public static void main(String[] args) {
		Pager pa = new Pager(50, 30, 8,1);
		//System.out.println(pa);
		//System.out.println(pa.getAjaxToolBar("abc"));
		//System.out.println(pa.getToolBar("/businnes/jiu"));
	}
}
