package com.hyy.ifm.util;

import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public final class DateUtils {

	private static String datePattern = "yyyy-MM-dd";
	private static final ThreadLocal<Calendar> calendarThreadLocal = new ThreadLocal<Calendar>() {

		@Override
		protected Calendar initialValue() {
			return Calendar.getInstance();
		}

	};

	private DateUtils() {

	}

	public static Calendar getThreadLocalCalendar() {
		return calendarThreadLocal.get();
	}

	/**
	 * 将时间转换为纯粹的Date,只包含年月日,其余时间字段为0
	 * 
	 * @param date
	 *            要转换的Date对象
	 * @return
	 */
	public static Date roundDate(Date date) {
		Calendar cal = getThreadLocalCalendar();
		cal.setTime(date);
		DateTime datetime = new DateTime(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
				cal.get(Calendar.DAY_OF_MONTH), 0, 0);
		return datetime.toDate();
	}

	/**
	 * 判断时间是否在指定的范围中
	 * 
	 * @param targetDate
	 *            目标时间对象
	 * @param beginDate
	 *            起始时间对象
	 * @param endDate
	 *            结束时间对象
	 * @return
	 */
	public static boolean isBetweenIn(Date targetDate, Date beginDate, Date endDate) {
		return targetDate.compareTo(beginDate) > 0 && targetDate.compareTo(endDate) < 0;
	}

	public static Date[] roundDateAndPlusNDays(Date date, int days) {
		date = roundDate(date);
		DateTime datetime = new DateTime(date);
		datetime = datetime.plusDays(days);
		return new Date[] { date, datetime.toDate() };
	}

	public static Date[] roundDateAndPlusOneDay(Date date) {
		return roundDateAndPlusNDays(date, 1);
	}

	public static Date today() {
		Date now = new Date();
		return DateUtils.roundDate(now);
	}


	/**
	 * 日期加指定天数
	 * 
	 * @param d
	 *            需要处理的日期
	 * @param day
	 *            需要加的天数
	 * @return 加完指定天数后返回的日期
	 * @throws ParseException
	 */
	public static Date addDate(Date d, long day) {

		long time = d.getTime();
		day = day * 24 * 60 * 60 * 1000;
		time += day;
		return new Date(time);
	}

	/**
	 * 日期减指定天数
	 * 
	 * @param d
	 *            需要处理的日期
	 * @param day
	 *            需要减的天数
	 * @return 减完指定天数后返回的日期
	 * @throws ParseException
	 */
	public static Date removeDate(Date d, long day) {

		long time = d.getTime();
		day = day * 24 * 60 * 60 * 1000;
		time -= day;
		return new Date(time);
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate) {
		int countDays = 0;
		try {
			if (bdate == null) {
				bdate = new Date();
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			smdate = sdf.parse(sdf.format(smdate));
			bdate = sdf.parse(sdf.format(bdate));
			Calendar cal = Calendar.getInstance();
			cal.setTime(smdate);
			long time1 = cal.getTimeInMillis();
			cal.setTime(bdate);
			long time2 = cal.getTimeInMillis();
			long between_days = (time2 - time1) / (1000 * 3600 * 24);
			countDays = Integer.parseInt(String.valueOf(between_days));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return countDays;
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	@SuppressWarnings("deprecation")
	public static int daysBetween2(Date smdate, Date bdate) throws ParseException {
		return (int) (((bdate.getTime() + bdate.getTimezoneOffset() * 60000L) / 86400000L) - ((smdate.getTime() + smdate
				.getTimezoneOffset() * 60000L) / 86400000L));
	}

	/**
	 * 计算两个日期之间相差的天数(只计算年月日，不计算时分秒)
	 * 
	 * @Description: TODO
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return
	 * @throws ParseException
	 *             int
	 * @throws
	 * @author 吴澍(shu.wu@youyujinfu.com)
	 * @date 2016年1月6日下午2:20:34
	 */
	public static int daysBetween3(String smdate, String bdate) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		long from = df.parse(smdate).getTime();
		long to = df.parse(bdate).getTime();
		return (int) ((to - from) / (1000 * 60 * 60 * 24)) + 1;
	}

	/**
	 * 字符串的日期格式的计算
	 */
	public static int daysBetween(String smdate, String bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 计算两个日期之间相差的小时数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差小时数
	 * @throws ParseException
	 */
	public static int hoursBetween(Date smdate, Date bdate) {
		int countTimes = 0;
		try {
			if (bdate == null) {
				bdate = new Date();
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			smdate = sdf.parse(sdf.format(smdate));
			bdate = sdf.parse(sdf.format(bdate));
			Calendar cal = Calendar.getInstance();
			cal.setTime(smdate);
			long time1 = cal.getTimeInMillis();
			cal.setTime(bdate);
			long time2 = cal.getTimeInMillis();
			long between_time = (time2 - time1) / (1000 * 3600);
			countTimes = Integer.parseInt(String.valueOf(between_time));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return countTimes;
	}

	/**
	 * 计算两个日期之间相差的天、时、分、秒
	 * 
	 * @Create_by:吴澍
	 * @Create_date:2015年2月9日
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param format
	 *            格式化条件
	 * @param n
	 *            d天,h时,m分,s秒
	 * @return
	 * @Last_Edit_By:
	 * @Edit_Description:
	 * @Create_Version:order.interface 1.0
	 */
	public static Long dateDiff(String startTime, String endTime, String format, String n) {
		// 按照传入的格式生成一个simpledateformate对象
		SimpleDateFormat sd = new SimpleDateFormat(format);
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		long ns = 1000;// 一秒钟的毫秒数
		long diff;
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		// 获得两个时间的毫秒时间差异
		try {
			diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
			day = diff / nd;// 计算差多少天
			hour = diff % nd / nh + day * 24;// 计算差多少小时
			min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟
			sec = diff % nd % nh % nm / ns;// 计算差多少秒
			// 输出结果
			System.out.println("时间相差：" + day + "天" + (hour - day * 24) + "小时" + (min - day * 24 * 60) + "分钟" + sec
					+ "秒。");
			System.out.println("hour=" + hour + ",min=" + min);
			if (n.equalsIgnoreCase("h")) {
				return hour;
			}
			if (n.equalsIgnoreCase("d")) {
				return day;
			}
			if (n.equalsIgnoreCase("m")) {
				return min;
			} else {
				return sec;
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (n.equalsIgnoreCase("h")) {
			return hour;
		}
		if (n.equalsIgnoreCase("d")) {
			return day;
		}
		if (n.equalsIgnoreCase("m")) {
			return min;
		} else {
			return sec;
		}
	}

	/**
	 * 计算两个日期之间的小时或分钟差
	 * 
	 * @Create_by:吴澍
	 * @Create_date:2015年2月9日
	 * @param startTime
	 *            开始日期
	 * @param endTime
	 *            结束日期
	 * @param n
	 *            d天,h时,m分,s秒
	 * @return
	 * @Last_Edit_By:
	 * @Edit_Description:
	 * @Create_Version:order.interface 1.0
	 */
	public static Long dateDiff(Date startTime, Date endTime, String n) {
		// 按照传入的格式生成一个simpledateformate对象
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		long ns = 1000;// 一秒钟的毫秒数
		long diff;
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		// 获得两个时间的毫秒时间差异
		diff = endTime.getTime() - startTime.getTime();
		day = diff / nd;// 计算差多少天
		hour = diff % nd / nh + day * 24;// 计算差多少小时
		min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟
		sec = diff % nd % nh % nm / ns;// 计算差多少秒
		// 输出结果
		// System.out.println("时间相差：" + day + "天" + (hour - day * 24) + "小时" +
		// (min - day * 24 * 60) + "分钟" + sec + "秒。");
		// System.out.println("hour=" + hour + ",min=" + min);
		if (n.equalsIgnoreCase("h")) {
			return hour;
		}
		if (n.equalsIgnoreCase("d")) {
			return day;
		}
		if (n.equalsIgnoreCase("m")) {
			return min;
		} else {
			return sec;
		}
	}

	/**
	 * 计算两个日期之间相差的（天、小时、分钟、秒数）
	 * 
	 * @param minTime
	 *            较小的日期
	 * @param maxTime
	 *            较大的日期
	 * @param type
	 *            返回类型(1天、2小时、3分钟、4秒)
	 * @return
	 */
	public static long dateDiff(Date minTime, Date maxTime, int type) {
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		long ns = 1000;// 一秒钟的毫秒数
		long diff;
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		// 获得两个时间的毫秒时间差异
		diff = maxTime.getTime() - minTime.getTime();
		day = diff / nd;// 计算差多少天
		hour = diff % nd / nh + day * 24;// 计算差多少小时
		min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟
		sec = diff % nd % nh % nm / ns;// 计算差多少秒
		if (type == 1) {
			return day;
		} else if (type == 2) {
			return hour;
		} else if (type == 3) {
			return min;
		} else if (type == 4) {
			return sec;
		} else {
			return 0;
		}
	}

	/**
	 * 计算两个日期之间相差的（天、小时、分钟、秒数）
	 * 
	 * @param startTime
	 *            较小的日期
	 * @param endTime
	 *            较大的日期
	 * @param format
	 *            格式化
	 * @param type
	 *            返回类型(1天、2小时、3分钟、4秒)
	 * @return
	 */
	public static Long dateDiff(String startTime, String endTime, String format, int type) {
		// 按照传入的格式生成一个simpledateformate对象
		SimpleDateFormat sd = new SimpleDateFormat(format);
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		long ns = 1000;// 一秒钟的毫秒数
		long diff;
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		// 获得两个时间的毫秒时间差异
		try {
			diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
			day = diff / nd;// 计算差多少天
			hour = diff % nd / nh + day * 24;// 计算差多少小时
			min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟
			sec = diff % nd % nh % nm / ns;// 计算差多少秒
			// 输出结果
			// System.out.println("时间相差：" + day + "天" + (hour - day * 24) + "小时"
			// + (min - day * 24 * 60) + "分钟" + sec + "秒。");
			// System.out.println("hour=" + hour + ",min=" + min);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (type == 1) {
			return day;
		} else if (type == 2) {
			return hour;
		} else if (type == 3) {
			return min;
		} else if (type == 4) {
			return sec;
		} else {
			return null;
		}
	}

	/**
	 * 返回当前日期 Date
	 */
	public static Date getCurrentDate() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * 返回当前日期 Timestamp
	 */
	public static Timestamp getCurrenTimestamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timestamp;
	}

	/**
	 * 返回预设Format的当前日期字符串
	 */
	public static String getToday() {
		Date today = new Date();
		return format(today);
	}

	/**
	 * 使用预设Format格式化Date成字符串
	 */
	public static String format(Date date) {
		return format(date, getDatePattern());
	}

	/**
	 * 使用参数Format格式化Date成字符串
	 */
	public static String format(Date date, String pattern) {
		String returnValue = "";
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			returnValue = df.format(date);
		}
		return returnValue;
	}

	/**
	 * 使用参数Format格式化Timestamp成字符串
	 */
	public static String convertTimeStamp(Timestamp date, String pattern) {
		String str = null;
		try {
			SimpleDateFormat df = new SimpleDateFormat(pattern);// 定义格式，不显示毫秒
			str = df.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 使用预设格式将字符串转为Date
	 */
	public static Date parse(String strDate) {
		return parse(strDate, getDatePattern());
	}

	/**
	 * 使用参数Format将字符串转为Date
	 */
	public static Date parse(String strDate, String pattern) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		try {
			return df.parse(strDate);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 在日期上增加N个整月
	 */
	public static Date addMonth(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, n);
		return cal.getTime();
	}

	/**
	 * 获取日期的增加N个整月的第一天
	 */
	public static Date getDayFirstMonth(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
		cal.setTime(date);

		cal.add(Calendar.MONTH, n);
		Date theDate = cal.getTime();
		gcLast.setTime(theDate);
		gcLast.set(Calendar.DAY_OF_MONTH, 1);
		String dayFirstMonth = format(gcLast.getTime());
		StringBuffer str = new StringBuffer().append(dayFirstMonth).append(" 00:00:00");
		return parse(str.toString(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获取日期的增加N个整月的最后一天
	 */
	public static Date getDayEndMonth(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, n + 1);
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.DATE, -1);
		String dayEndMonth = format(cal.getTime());
		StringBuffer str = new StringBuffer().append(dayEndMonth).append(" 23:59:59");
		return parse(str.toString(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 根据日期获取年的第一天
	 */
	public static Date getCurrentYearFirst(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_YEAR, 1);
		return parse(format(cal.getTime()));
	}

	/**
	 * 在日期上增加N天
	 */
	public static Date addDay(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, n);
		return cal.getTime();
	}

	/**
	 * 获得当前日期的前N天
	 */
	public static String getDayBeforeSomeDay(int n) {
		Calendar c = Calendar.getInstance();
		Date date = new Date();
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - n);
		String dayBefore = format(c.getTime());
		return dayBefore;
	}

	/**
	 * 获得指定日期的前N天
	 */
	public static String getDayBeforeSomeDay(String theDate, int n) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(theDate);
			c.setTime(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - n);
		String dayBefore = format(c.getTime(), "yyyy-MM-dd HH:mm:ss");
		return dayBefore;
	}

	/**
	 * 当前日期减去N天
	 * 
	 * @Description: 当前日期减去N天
	 * @param date
	 * @param n
	 * @return
	 * @return Date
	 * @throws
	 * @author 吴澍(shu.wu@youyujinfu.com)
	 * @date 2015年11月25日上午10:27:43
	 */
	public static Date minusDay(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day = cal.get(Calendar.DATE);
		cal.set(Calendar.DATE, day - n);
		return cal.getTime();
	}

	/**
	 * 获得当前日期的后N天
	 */
	public static String getDayAfterSomeDay(int n) {
		Calendar c = Calendar.getInstance();
		Date date = new Date();
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + n);
		String dayBefore = format(c.getTime());
		return dayBefore;
	}

	/**
	 * 获得指定日期的后N天
	 * 
	 * @throws Exception
	 */
	public static String getDayAfterSomeDay(String theDate, int n) throws Exception {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yy-MM-dd").parse(theDate);
			c.setTime(date);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new Exception("日志转换出错");
		}
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + n);
		String dayBefore = format(c.getTime());
		return dayBefore;
	}

	/**
	 * 获得指定日期的后N天
	 */
	public static Date getDayAfterSomeDay(Date date, int n) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + n);
		return c.getTime();
	}

	/**
	 * 某个日期和当天比较在当天之后返回1，否则为-1
	 */
	public static int compareToNextDate(Date adate) throws ParseException {
		Calendar aCalendar = new GregorianCalendar();
		aCalendar.setTime(adate);
		Calendar today = getTodayCalendar();
		today.add(Calendar.DAY_OF_MONTH, 1);
		return aCalendar.compareTo(today);
	}

	/**
	 * 返回两个日期相差的天数
	 */
	public static long getDistDates(Date startDate, Date endDate) {
		String str1 = DateUtils.format(startDate, "yyyy-MM-dd HH:mm");
		String str2 = DateUtils.format(endDate, "yyyy-MM-dd HH:mm");

		startDate = DateUtils.parse(str1, "yyyy-MM-dd");
		endDate = DateUtils.parse(str2, "yyyy-MM-dd");
		long totalDate = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		long timestart = startDate.getTime();
		calendar.setTime(endDate);
		long timeend = endDate.getTime();
		long num = timestart - timeend;
		totalDate = num / (1000 * 60 * 60 * 24);
		return totalDate;
	}
	
	

	public static double getDiffDates(Date startDate, Date endDate) {
		String str1 = DateUtils.format(startDate, "yyyy-MM-dd HH:mm");
		String str2 = DateUtils.format(endDate, "yyyy-MM-dd HH:mm");

		startDate = DateUtils.parse(str1, "yyyy-MM-dd");
		endDate = DateUtils.parse(str2, "yyyy-MM-dd");
		double totalDate = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		double timestart = startDate.getTime();
		calendar.setTime(endDate);
		double timeend = endDate.getTime();
		double num = timestart - timeend;
		totalDate = num / (1000.0 * 60.0 * 60.0 * 24.0);
		return totalDate;
	}

	/**
	 * 返回两个时间相差天数 向上进位（如：2014-08-01 16:35:38 2014-08-02 17:35:38 相差2天）
	 */
	public static long getDistDays(Date startDate, Date endDate) {
		long timeStart = startDate.getTime();
		long timeEnd = endDate.getTime();
		long diff;
		if (timeStart < timeEnd) {
			diff = timeEnd - timeStart;
		} else {
			diff = timeStart - timeEnd;
		}
		BigDecimal bigDiff = new BigDecimal(diff);
		BigDecimal bigDay = new BigDecimal(1000 * 60 * 60 * 24);
		BigDecimal time = bigDiff.divide(bigDay, 0, BigDecimal.ROUND_UP);

		return time.longValue();
	}

	/**
	 * 返回两个时间相差 “XX天XX小时” 向上进位
	 */
	public static String getDistanceDayAndHour(Date startDate, Date endDate) {
		long timeStart = startDate.getTime();
		long timeEnd = endDate.getTime();
		long diff;
		if (timeStart < timeEnd) {
			diff = timeEnd - timeStart;
		} else {
			diff = timeStart - timeEnd;
		}
		BigDecimal dayMilli = new BigDecimal(24 * 60 * 60 * 1000); // 天的毫秒数
		BigDecimal hourMilli = new BigDecimal(60 * 60 * 1000); // 小时的毫秒数
		BigDecimal hour = new BigDecimal(24); // 小时

		BigDecimal bigDiff = new BigDecimal(diff);
		BigDecimal bigDay = bigDiff.divide(dayMilli, 0, BigDecimal.ROUND_DOWN);
		BigDecimal bigHour = bigDiff.divide(hourMilli, 0, BigDecimal.ROUND_UP).subtract(bigDay.multiply(hour));

		return bigDay.intValue() + "天" + bigHour.intValue() + "小时";
	}

	public static String getDiffDayAndHour(Date startDate, Date endDate) {
		long timeStart = startDate.getTime();
		long timeEnd = endDate.getTime();
		long diff;
		if (timeStart < timeEnd) {
			diff = timeEnd - timeStart;
		} else {
			diff = timeStart - timeEnd;
		}
		BigDecimal dayMilli = new BigDecimal(24 * 60 * 60 * 1000); // 天的毫秒数
		BigDecimal hourMilli = new BigDecimal(60 * 60 * 1000); // 小时的毫秒数
		BigDecimal hour = new BigDecimal(24); // 小时

		BigDecimal bigDiff = new BigDecimal(diff);
		BigDecimal bigDay = bigDiff.divide(dayMilli, 0, BigDecimal.ROUND_DOWN);
		BigDecimal bigHour = bigDiff.divide(hourMilli, 0, BigDecimal.ROUND_UP).subtract(bigDay.multiply(hour));

		return bigDay.intValue() + "-" + bigHour.intValue() + "-";
	}

	/***
	 * 将毫秒数转化成xx天xx小时
	 * 
	 * @Create_by:陈鑫
	 * @Create_date:2015-1-5
	 * @param diff
	 * @return
	 * @Last_Edit_By:
	 * @Edit_Description:
	 * @Create_Version:maxtp.framelib 1.0
	 */
	public static String converttoDayAndHour(Long diff) {

		BigDecimal dayMilli = new BigDecimal(24 * 60 * 60 * 1000); // 天的毫秒数
		BigDecimal hourMilli = new BigDecimal(60 * 60 * 1000); // 小时的毫秒数
		BigDecimal hour = new BigDecimal(24); // 小时
		BigDecimal bigDiff = new BigDecimal(diff);
		BigDecimal bigDay = bigDiff.divide(dayMilli, 0, BigDecimal.ROUND_DOWN);
		BigDecimal bigHour = bigDiff.divide(hourMilli, 0, BigDecimal.ROUND_UP).subtract(bigDay.multiply(hour));
		return bigDay.intValue() + "天" + bigHour.intValue() + "小时";

	}

	/**
	 * 得到N(N可以为负数)小时后的日期
	 */
	public static Date afterNHoursDate(Date theDate, int hous) {
		try {
			if (theDate == null) {
				return getCurrentDate();
			}
			Calendar cal = Calendar.getInstance();
			cal.setTime(theDate);
			cal.add(Calendar.HOUR_OF_DAY, hous);
			return cal.getTime();
		} catch (Exception e) {
			return getCurrentDate(); // 如果无法转化，则返回默认格式的时间。
		}
	}

	/**
	 * 得到N(N可以为负数)分钟后的日期
	 */
	public static Date afterNMinutesDate(Date theDate, int minute) {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(theDate);
			cal.add(Calendar.MINUTE, minute);
			return cal.getTime();
		} catch (Exception e) {
			return getCurrentDate(); // 如果无法转化，则返回默认格式的时间。
		}
	}

	/**
	 * 得到N(N可以为负数)天后的日期
	 */
	public static Date afterNDayDate(Date theDate, int day) {
		try {
			if (theDate == null) {
				return getCurrentDate();
			}
			Calendar cal = Calendar.getInstance();
			cal.setTime(theDate);
			cal.add(Calendar.DAY_OF_MONTH, day);
			return cal.getTime();
		} catch (Exception e) {
			return getCurrentDate(); // 如果无法转化，则返回默认格式的时间。
		}
	}

	/**
	 * 返回两个日期相差的分钟数
	 */
	public static long getDistMins(Date startDate, Date endDate) {
		long totalDate = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		long timestart = calendar.getTimeInMillis();
		calendar.setTime(endDate);
		long timeend = calendar.getTimeInMillis();
		totalDate = Math.abs((timeend - timestart)) / (1000 * 60);
		return totalDate;
	}

	/**
	 * 返回两个日期的差 ，单位秒
	 * 
	 * @Create_by:陈鑫
	 * @Create_date:2014-11-19
	 * @param startDate
	 * @param endDate
	 * @return
	 * @Last_Edit_By:
	 * @Edit_Description:
	 * @Create_Version:maxtp.framelib 1.0
	 */
	public static long getDistSecounds(Date startDate, Date endDate) {
		long totalDate = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		long timestart = calendar.getTimeInMillis();
		calendar.setTime(endDate);
		long timeend = calendar.getTimeInMillis();
		totalDate = Math.abs((timeend - timestart)) / (1000);

		return totalDate;

	}

	/**
	 * 返回指定日期所在的月份
	 */
	public static String getCurrMonth(String dateStr) {
		String[] list = dateStr.split("-");
		if (list != null && list.length != 0) {
			return list[1];
		} else {
			list = dateStr.split("/");
			return list[1];
		}

	}

	/**
	 * 根据日期获取星期几
	 */
	public static String getWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		SimpleDateFormat sd = new SimpleDateFormat("EEE");
		return sd.format(date);
	}

	/**
	 * 获取当天的日历对象
	 */
	public static Calendar getTodayCalendar() throws ParseException {
		String strDate = getToday();
		Calendar cal = new GregorianCalendar();
		cal.setTime(parse(strDate, datePattern));
		return cal;
	}

	/**
	 * 获取格林尼治时间
	 *
	 * @Create_by:yinsy
	 * @Create_date:2014-5-22
	 * @param date
	 * @param pattern
	 * @return
	 * @Last_Edit_By:
	 * @Edit_Description:
	 * @Create_Version:maxtp.framelib 1.0
	 */
	public static Date getGMTDate(Date date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		format.setTimeZone(TimeZone.getTimeZone("GMT"));
		String time = format.format(date);
		return DateUtils.parse(time, pattern);
	}

	public static String getDatePattern() {
		return datePattern;
	}

	public static void setDatePattern(String datePattern) {
		DateUtils.datePattern = datePattern;
	}

	/**
	 * 根据身份证号获取年龄
	 *
	 * @Method_Name : getAgeByIdCard
	 * @param idCard
	 * @return
	 * @return : int
	 * @Creation Date : 2014年6月19日 上午11:52:36
	 * @version : v1.00
	 * @Author : shenzhenxing
	 * @Update Date :
	 * @Update Author :
	 */
	public static int getAgeByIdCard(String idCard) {

		int age = 0;
		String birth = idCard.substring(6, 10);
		int birthday = Integer.parseInt(birth);
		int year = 0;
		year = Integer.parseInt(format(new Date(), "yyyy"));
		age = year - birthday;
		return age;
	}

	/**
	 * @Description: 当天的24点 即 第二天的0点
	 * @param @return
	 * @return long 返回时间戳 秒
	 * @throws
	 * @author 王叶
	 * @date 2015年11月24日下午2:37:49
	 */
	public static long getTimesnight() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 24);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return (int) (cal.getTimeInMillis() / 1000);
	}

	/**
	 * @Description: 距离当天24点的秒数 eg:用于设置过期时间 当天24点失效
	 * @param @return 当天24:00:00-当前时间
	 * @return long
	 * @throws
	 * @author 王叶
	 * @date 2015年11月25日下午4:29:25
	 */
	public static long getDistSecoundsTonight() {
		return DateUtils.getTimesnight() - System.currentTimeMillis() / 1000;
	}

	/**
	 * @Description: 获取六位的当前日期
	 * @param @return 比如2015-12-12 返回的格式为151212
	 * @return String
	 * @throws
	 * @author 王叶
	 * @date 2015年11月25日下午6:58:23
	 */
	public static String getDateSixLength() {
		Date dt = new Date();
		SimpleDateFormat matter1 = new SimpleDateFormat("yyMMdd");
		String middleString = matter1.format(dt);
		return middleString;
	}

	/**
	 * 获取当前日期，格式为yyyyMMdd
	 *
	 * @Author: 刘长青
	 * @Date: 2015年11月20日
	 * @return String
	 */
	public static String getStandardCurrentDate() {
		return DateUtils.getCurrentDate("yyyyMMdd");
	}

	/**
	 * 获取当前日期时间，格式为yyyyMMddHHmmss
	 *
	 * @Author: 刘长青
	 * @Date: 2015年11月20日
	 * @return String
	 */
	public static String getCurrentDateAndTime() {
		return DateUtils.getCurrentDate("yyyyMMddHHmmss");
	}

	/**
	 * 根据传入的日期格式获取相应的类型
	 *
	 * @Author: 刘长青
	 * @Date: 2015年11月20日
	 * @param aFormat
	 * @return String
	 */
	public static String getCurrentDate(String aFormat) {
		String tDate = new SimpleDateFormat(aFormat).format(new Date(System.currentTimeMillis()));
		return tDate;
	}

	/**
	 * 根据输入长度，获取随机数
	 * 
	 * @Author: 刘长青
	 * @Date: 2015年11月20日
	 * @param codeLen
	 * @return String
	 */
	public static String getNewRandomCode(int codeLen) {
		// 首先定义随机数据源
		// 根据需要得到的数据码的长度返回随机字符串
		java.util.Random randomCode = new java.util.Random();
		String strCode = "";
		while (codeLen > 0) {
			int charCode = randomCode.nextInt(9);
			strCode += charCode;
			codeLen--;
		}
		return strCode;
	}

	/**
	 * 获取二十位的不重复随机数
	 * 
	 * @Author: 刘长青
	 * @Date: 2015年11月20日
	 * @param codeLen
	 * @return String
	 */
	public static String getRandomNumber() {
		return DateUtils.getCurrentDate("yyMMddHHmmssSSS") + DateUtils.getNewRandomCode(5);
	}

	public static void main(String[] args) throws ParseException {
		String startDate = "2017-05-23";
		String endDate = "2017-04-17";
		System.out.println(daysBetween3("2017-04-17","2017-05-23"));
	}
}
