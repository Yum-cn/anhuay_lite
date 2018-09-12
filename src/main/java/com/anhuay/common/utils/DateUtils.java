package com.anhuay.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期处理
 */
public class DateUtils {
	private final static Logger logger = LoggerFactory.getLogger(DateUtils.class);
	/**
	 * 时间格式(yyyy-MM-dd)
	 */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	/**
	 * 时间格式(yyyy-MM-dd HH:mm:ss)
	 */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	public static String format(Date date) {
		return format(date, DATE_PATTERN);
	}

	public static String format(Date date, String pattern) {
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			return df.format(date);
		}
		return null;
	}

	/**
	 * 计算距离现在多久，非精确
	 *
	 * @param date
	 * @return
	 */
	public static String getTimeBefore(Date date) {
		Date now = new Date();
		long l = now.getTime() - date.getTime();
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		String r = "";
		if (day > 0) {
			r += day + "天";
		} else if (hour > 0) {
			r += hour + "小时";
		} else if (min > 0) {
			r += min + "分";
		} else if (s > 0) {
			r += s + "秒";
		}
		r += "前";
		return r;
	}

	/**
	 * 计算距离现在多久，精确
	 *
	 * @param date
	 * @return
	 */
	public static String getTimeBeforeAccurate(Date date) {
		Date now = new Date();
		long l = now.getTime() - date.getTime();
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		String r = "";
		if (day > 0) {
			r += day + "天";
		}
		if (hour > 0) {
			r += hour + "小时";
		}
		if (min > 0) {
			r += min + "分";
		}
		if (s > 0) {
			r += s + "秒";
		}
		r += "前";
		return r;
	}

	/**
	 * 转换为Unix时间戳
	 * 
	 * @author Yum
	 */
	public static long getTimestamp(String date) {

		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
			Date dateFormat = df.parse(date);
			return dateFormat.getTime() / 1000;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}
	
	/**
	 * 转换为yyyy-MM-dd HH:mm:ss格式的时间
	 * 
	 * @author Yum
	 */
	public static String getTime(long timestamp) {

		try {

			String str = String.valueOf(timestamp);
			if (str.length() <= 10) {
				timestamp = timestamp * 1000L;
			}

			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(timestamp);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			return sdf.format(cal.getTime());

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static void getBetweenDate(String minDate, String maxDate, int field) {

		Map<String,Integer> weekMap = new HashMap<String,Integer>(); 
		weekMap.put("Sunday", 1);
		weekMap.put("Tuesday", 2);
		weekMap.put("Wednesday", 3);
		weekMap.put("Wednesday", 4);
		weekMap.put("Thursday", 5);
		weekMap.put("Friday", 6);
		weekMap.put("Saturday", 7);
		
		
		String pattern = "yyyy-MM-dd";

		SimpleDateFormat sdf = new SimpleDateFormat(pattern);// 格式化为年月

		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();

		try {
			min.setTime(sdf.parse(minDate));

			max.setTime(sdf.parse(maxDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar curr = min;
		while (curr.before(max) || curr.equals(max)) {
		}

		return;
	}

	public static void main(String[] args) {
		//System.out.println(getTimestamp("2018-01-01 02:15:03"));
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 2);
		System.out.println(cal.get(Calendar.DAY_OF_WEEK));
		
	}
}
