package com.nebo_spider.util;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * 读取配置文件中的变量对应的值,如果没有对应变量返回空字符串
 * @author Bertron
 */
public final class GlobalConfig {
	public static String get(String key) {
		String value = "";
		Locale locale = Locale.getDefault();
		try {
			ResourceBundle localResource = ResourceBundle.getBundle("config/config",
					locale);
			value = localResource.getString(key);
		} catch (MissingResourceException mre) {
			value = "";
		}
		return value;
	}
	
	public static String getUpload(String key) {
		String value = "";
		Locale locale = Locale.getDefault();
		try {
			ResourceBundle localResource = ResourceBundle.getBundle("config/upload",
					locale);
			value = localResource.getString(key);
		} catch (MissingResourceException mre) {
			//System.out.println(mre.getMessage());
			value = "";
		}
		return value;
	}
	
	//读取transData.properties配置文件中的数据
	public static String getMysql(String key){
		String value = "";
		Locale locale = Locale.getDefault();
		try {
			ResourceBundle localResource = ResourceBundle.getBundle("config/mysql",
					locale);
			value = localResource.getString(key);
		} catch (MissingResourceException mre) {
			//System.out.println(mre.getMessage());
			value = "";
		}
		return value;
	}
	
	public static int getInteger(String key) {
		String value = GlobalConfig.get(key);
		return Integer.parseInt(value);
	}
	
	/**
	 * 获取页码数
	 * @param index
	 * @return
	 */
	public static int getPageSize(int index){
		String pageSizeList = GlobalConfig.get("pageSize");
		String[] pageSizes = pageSizeList.split(",");
		return Integer.parseInt(pageSizes[index]);
	}

	public static void main(String[] args) {
		//System.out.println("webRoot->" + GlobalConfig.get("webRoot"));
	}
}
