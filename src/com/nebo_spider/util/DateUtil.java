package com.nebo_spider.util;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 日期工具类
 * Created by  nebo
 *
 */
public class DateUtil {
	public static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");
	public static String formatDate(Date date) {
		return dateFormat.format(date);
	}
}
