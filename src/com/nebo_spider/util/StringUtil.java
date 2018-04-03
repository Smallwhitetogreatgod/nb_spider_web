package com.nebo_spider.util;

import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;

public class StringUtil {
	// 返回正整数
	public static int getInt(String str) {
		int num = 0;
		String regex = "^[1-9]\\d*$";// 校验正整数
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		boolean b = m.matches();
		if (b == true) {
			num = Integer.parseInt(str);
		}
		return num;
	}
	
	public static int getIntValue(String str) {
		int num = 0;
		String regex = "^(-)?[1-9]\\d*$";// 校验整数
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		boolean b = m.matches();
		if (b == true) {
			num = Integer.parseInt(str);
		}
		return num;
	}

	public static String numFormat(double num, int bit) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		return nf.format(num);
	}

	/**
	 * 总金额格式转换，保留2位小数
	 * 
	 * @param num
	 * @return
	 */
	public static String amountFormat(double num) {
		return numFormat(num, 2);
	}

	/**
	 * 菜单数量格式转换，保留2位小数
	 * 
	 * @param num
	 * @return
	 */
	public static String menuFormat(float num) {
		return numFormat(num, 2);
	}

	/**
	 * 将double类型数据转换为百分比格式，并保留小数点前IntegerDigits位和小数点后FractionDigits位
	 * 
	 * @param d
	 * @param IntegerDigits
	 * @param FractionDigits
	 * @return
	 */
	public static String getPercentFormat(double d, int IntegerDigits,
			int FractionDigits) {
		NumberFormat nf = java.text.NumberFormat.getPercentInstance();
		nf.setMaximumIntegerDigits(IntegerDigits);// 小数点前保留几位
		nf.setMinimumFractionDigits(FractionDigits);// 小数点后保留几位
		String str = nf.format(d);
		return str;
	}

	/**
	 * @param str
	 *            : 字符串来源
	 * @param width
	 *            : 字符串的字节宽度
	 * @param ellipsis
	 *            : 添加字符后缀
	 * @return String Object
	 * @deprecated 这个函数是用来对输入的字符串进行截取的功能
	 */
	public static String abbreviate(String str, int width, String ellipsis) {
		if (str == null || "".equals(str)) {
			return "";
		}
		int d = 0; // byte length
		int n = 0; // char length
		for (; n < str.length(); n++) {
			d = (int) str.charAt(n) > 256 ? d + 2 : d + 1;
			if (d > width) {
				break;
			}
		}

		if (d > width) {
			n = n - ellipsis.length() / 2;
			return str.substring(0, n > 0 ? n : 0) + ellipsis;
		}

		return str = str.substring(0, n);
	}

	/**
	 * @param inputString
	 *            输入的html内容
	 * @return String Object
	 * @deprecated 这个函数是用来对输入字符的HTML代码进行过滤
	 */
	public static String Html2Text(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";

		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;

		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;

		java.util.regex.Pattern p_html1;
		java.util.regex.Matcher m_html1;

		try {
			// 定义script的正则表达式{或<script[^>]*?>[//s//S]*?<///script>}
			String regEx_script = "<[//s]*?script[^>]*?>[//s//S]*?<[//s]*?///[//s]*?script[//s]*?>";
			// 定义style的正则表达式{或<style[^>]*?>[//s//S]*?<///style>//}
			String regEx_style = "<[//s]*?style[^>]*?>[//s//S]*?<[//s]*?///[//s]*?style[//s]*?>";//
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
			String regEx_html1 = "<[^>]+";
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			p_html1 = Pattern.compile(regEx_html1, Pattern.CASE_INSENSITIVE);
			m_html1 = p_html1.matcher(htmlStr);
			htmlStr = m_html1.replaceAll(""); // 过滤html标签

			textStr = htmlStr.replaceAll("&nbsp;", "").replaceAll("\\s*", "").trim();

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}
		return textStr;// 返回文本字符串
	}
	
	public static String mod(String str) throws UnsupportedEncodingException {
		return new String(str.getBytes("ISO8859-1"),"UTF-8");
	}

	/**
	 * 替换
	 * 注：\n 回车( ) 
	 * \t 水平制表符( ) 
	 * \s 空格(\u0008) 
	 * \r 换行( )
	 * @param str 源字符串
	 * @param replaceStr 替换的字符串
	 * @return
	 */
	public static String replaceBlank(String str,String replaceStr) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s{2,}|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll(replaceStr);
		}
		return dest;
	}
	/**
	 * 从配置文件中读取key的value，将value拆分成，转成list
	 * @param key
	 * @return
	 */
	public static List<Integer> toIntegerListByKey(String key){
		String value = GlobalConfig.get(key);
		return StringUtil.toIntegerListByValue(value);
	}
	/**
	 * 将value值拆分后转成List<Integer>
	 * @param value
	 * @return List<Integer>
	 */
	public static List<Integer> toIntegerListByValue(String value){
		List<Integer> list = new ArrayList<Integer>();
		if(value != null){
			String[] arr = value.split(",");
			for(String s : arr){
				if(s.matches("\\d+")){
					list.add(Integer.valueOf(s));
				}
			}
		}
		return list;
	}
	/**
	 * 将字符串转化成数字数组
	 * @param str
	 * @return
	 */
	public static int[] toArray(String str){
		/*Pattern pattern = Pattern.compile("/d+");
		Matcher matcher = pattern.matcher("Java不是人");
		boolean b= matcher.matches();
		*/
		if(str != null){
			String[] arr = str.split(",");
			List<Integer> list = new ArrayList<Integer>();
			for(String s : arr){
				if(s.matches("\\d+")){
					list.add(Integer.valueOf(s));
				}
			}
			return toArray(list);
		}
		return null;
	}
	
	/**
	 * 将list转成数组
	 * @param list
	 * @return
	 */
	public static int[] toArray(List<Integer> list){
		if(list != null){
			int[] arr = new int[list.size()];
			for(int i = 0 ; i < list.size() ;i++){
				arr[i] = list.get(i);
			}
			return arr;
		}
		return null;
	}
	
	public static String template(String content,Map<String,String> params){
		Object[] keys = params.keySet().toArray();			
		for(Object k:keys){
			String x = k.toString();
			String t = "\\$\\{" + x + "\\}";
			content = content.replaceAll(t, params.get(x));
		}
		return content;
	}

	public static boolean isEmpty(String name) {
		if(name == null || "".equals(name)){
			return true;
		}
		return false;
	}
	
	public static String toJsonArrStr(){
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		for(int i=0;i<10;i++){
			Map<String,String> map = new HashMap<String, String>();
			map.put("name", "lily"+i);
			map.put("age", (30+i)+"+");
			map.put("addr", "上海"+i);
			list.add(map);
		}
		return JSONArray.fromObject(list).toString();
	}

	/**
	 * 生成limit个小于size的不重复的正整数
	 * @param size
	 * @param limit
	 * @return
	 */
	public static List<Integer> getRandomList(int size, int limit) {
		List<Integer> list = new ArrayList<Integer>();
		Random r = new Random();
		do {
			int num = r.nextInt(size);
			if(list.size() == 0){
				list.add(num);
			}else{
				if(!list.contains(num)){
					list.add(num);
				}
			}
		} while (list.size() < limit);
		return list;
	}
	
	public static int repeatNum(String a,String reg) {
		int index = 0;
		int count = 0;
		while (true) {
			index = a.indexOf("|", index + 1);
			if (index > 0) {
				count++;
			} else {
				break;
			}
		}
		return count;
	}
	public static void main(String[] args) {
		System.out.println("=======================");
	}
}
