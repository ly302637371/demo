package com.liyuan.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	public static final String[][] regexandpattern_day_level = {
			{ "^\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}", "yyyy-MM-dd HH:mm" },
			{
					"^\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}[.]?\\d{1,2}",
					"yyyy-MM-dd HH:mm:ss" },
			{ "\\d{8}", "yyyyMMdd" },
			{ "\\d{6}", "yyMMdd" },
			{ "^\\d{4}年\\d{1,2}月\\d{1,2}日$", "yyyy年MM月dd日" },
			{ "^\\d{4}-\\d{1,2}-\\d{1,2}$", "yyyy-MM-dd" },
			{
					"^[a-zA-Z]{3} [a-zA-Z]{3} \\d{1,2} \\d{1,2}:\\d{1,2}:d{1,2}[.]?\\d{1,2} [a-zA-Z]{3} \\d{4}$",
					"EEE MMM dd hh:mm:ss zzz yyyy" },
			{ "^\\d{4}/\\d{1,2}/\\d{1,2}$", "yyyy/MM/dd" },
			{ "^\\d{1,2}/\\d{1,2}/\\d{4}$", "dd/MM/yyyy" },
			{ "^\\d{4}\\.\\d{1,2}\\.\\d{1,2}$", "yyyy.MM.dd" } };
	static Logger log = LoggerFactory.getLogger(DateUtil.class);

	public static String getDateStr(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}

	public static String getDateStrC(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		return format.format(date);
	}

	public static String getDateStrCompact(Date date) {
		if (date == null)
			return "";

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String str = format.format(date);
		return str;
	}

	public static String getDateStrYearMonth(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		String str = format.format(date);
		return str;
	}

	public static String getDateTimeStr(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (date != null)
			return format.format(date);

		return "";
	}
	
	public static String getTimeStr(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		if (date != null)
			return format.format(date);

		return "";
	}

	public static String getDateTimeStrC(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		if (date != null)
			return format.format(date);

		return "";
	}

	public static String getYearMonthStrC(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月");
		if (date != null)
			return format.format(date);

		return "";
	}

	public static String getCurDateStr(String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(new Date());
	}

	public static Date parseDate(String s) throws ParseException {
		int i;
		try {
			for (i = 0; i < regexandpattern_day_level.length; ++i)
				if (s.matches(regexandpattern_day_level[i][0]))
					return parseDate(s, regexandpattern_day_level[i][1]);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	// 获取年份信息
	public static int getDateY(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}

	// 获取当前年份前后
	public static int getDateY(Date date,int i){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.YEAR, -1);
		return c.get(Calendar.YEAR);
	}

	public static Date parseDate(String s, String f) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(f);
		return format.parse(s);
	}

	public static Date parseDateC(String s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		return format.parse(s);
	}

	public static Date parseDateTime(String s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.parse(s);
	}

	public static Date parseDateTimeC(String s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		return format.parse(s);
	}

	public static Date parseTime(String s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		return format.parse(s);
	}

	public static Date parseTimeC(String s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("HH时mm分ss秒");
		return format.parse(s);
	}

	public static int yearOfDate(Date s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String d = format.format(s);
		return Integer.parseInt(d.substring(0, 4));
	}

	public static int monthOfDate(Date s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String d = format.format(s);
		return Integer.parseInt(d.substring(5, 7));
	}

	public static int dayOfDate(Date s) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String d = format.format(s);
		return Integer.parseInt(d.substring(8, 10));
	}

	public static String getDateTimeStr(Date date, double time) {
		String format = "yyyy-MM-dd";
		SimpleDateFormat sf = new SimpleDateFormat(format);

		String dateStr = sf.format(date);
		Double d = new Double(time);
		String timeStr = String.valueOf(d.intValue()) + ":00:00";

		return dateStr + " " + timeStr;
	}

	public static int diffDateM(Date sd, Date ed) throws ParseException {
		Calendar c_sd = Calendar.getInstance();
		Calendar c_ed = Calendar.getInstance();
		c_sd.setTime(sd);
		c_ed.setTime(ed);
		return ((c_ed.get(1) - c_sd.get(1)) * 12 + c_ed.get(2) - c_sd.get(2) + 1);
	}

	public static int diffDateD(Date sd, Date ed) throws ParseException {
		return (Math.round((float) (ed.getTime() - sd.getTime()) / 86400000.0F) + 1);
	}

	public static int diffDateM(int sym, int eym) throws ParseException {
		return ((Math.round(eym / 100.0F) - Math.round(sym / 100.0F)) * 12
				+ eym % 100 - sym % 100 + 1);
	}

	public static Date getNextMonthFirstDate(Date date) throws ParseException {
		Calendar scalendar = new GregorianCalendar();
		scalendar.setTime(date);
		scalendar.add(2, 1);
		scalendar.set(5, 1);
		return new Date(scalendar.getTime().getTime());
	}

	public static Date getNextMonthDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(2, 1);
		return calendar.getTime();
	}

	public static Date getFrontMonthDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(2, -1);
		return calendar.getTime();
	}

	public static Date getFrontDateByDayCount(Date date, int dayCount)
			throws ParseException {
		Calendar scalendar = new GregorianCalendar();
		scalendar.setTime(date);
		scalendar.add(5, -dayCount);
		return new Date(scalendar.getTime().getTime());
	}

	public static Date getFirstDay(String year, String month)
			throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.parse(year + "-" + month + "-1");
	}

	public static Date getFirstDay(int year, int month) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.parse(year + "-" + month + "-1");
	}

	public static Date getFirstDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(5, 1);

		return c.getTime();
	}

	public static Date getLastDay(String year, String month)
			throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(year + "-" + month + "-1");

		Calendar scalendar = new GregorianCalendar();
		scalendar.setTime(date);
		scalendar.add(2, 1);
		scalendar.add(5, -1);
		date = scalendar.getTime();
		return date;
	}

	public static Date getLastDay(int year, int month) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(year + "-" + month + "-1");

		Calendar scalendar = new GregorianCalendar();
		scalendar.setTime(date);
		scalendar.add(2, 1);
		scalendar.add(5, -1);
		date = scalendar.getTime();
		return date;
	}

	public static Date getLastDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(5, 1);
		c.roll(5, false);
		return c.getTime();
	}

	public static String getTodayStr() throws ParseException {
		Calendar calendar = Calendar.getInstance();
		return getDateStr(calendar.getTime());
	}

	public static Date getToday() throws ParseException {
		return new Date(System.currentTimeMillis());
	}

	public static String getTodayAndTime() {
		return new Timestamp(System.currentTimeMillis()).toString();
	}

	public static String getTodayC() throws ParseException {
		Calendar calendar = Calendar.getInstance();
		return getDateStrC(calendar.getTime());
	}

	public static int getThisYearMonth() throws ParseException {
		Calendar today = Calendar.getInstance();
		return (today.get(1) * 100 + today.get(2) + 1);
	}

	public static int getYearMonth(Date date) throws ParseException {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return (c.get(1) * 100 + c.get(2) + 1);
	}

	public static int getDistinceYear(String beforedate, String afterdate)
			throws ParseException {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");

		int yearCount = 0;
		try {
			Date d1 = d.parse(beforedate);
			Date d2 = d.parse(afterdate);
			Calendar c1 = Calendar.getInstance();
			c1.setTime(d1);
			Calendar c2 = Calendar.getInstance();
			c2.setTime(d2);
			yearCount = c2.get(1) - c1.get(1);
		} catch (ParseException e) {
			log.info("Date parse error!");
		}
		return yearCount;
	}

	public static long getDistinceMonth(String beforedate, String afterdate)
			throws ParseException {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
		long monthCount = 0L;
		try {
			Date d1 = d.parse(beforedate);
			Date d2 = d.parse(afterdate);
			Calendar c1 = Calendar.getInstance();
			c1.setTime(d1);
			Calendar c2 = Calendar.getInstance();
			c2.setTime(d2);

			monthCount = (c2.get(1) - c1.get(1)) * 12 + c2.get(2) - c1.get(2);
		} catch (ParseException e) {
			log.info("Date parse error!");
		}

		return monthCount;
	}

	public static double getDistinceMonth1(Date beforedate, Date afterdate)
			throws ParseException {
		double monthCount = 0D;
		if ((beforedate != null) && (afterdate != null))
			try {
				long dayCount = (afterdate.getTime() - beforedate.getTime()) / 86400000L;

				monthCount = (float) (dayCount + 1L) / 28.0F;
			} catch (Exception e) {
				log.info("Date parse error!");
			}

		return monthCount;
	}

	public static long getDistinceDay(String beforedate, String afterdate)
			throws ParseException {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
		long dayCount = 0L;
		try {
			Date d1 = d.parse(beforedate);
			Date d2 = d.parse(afterdate);

			dayCount = (d2.getTime() - d1.getTime()) / 86400000L;
		} catch (ParseException e) {
			log.info("Date parse error!");
		}
		return dayCount;
	}

	public static long getDistinceDay(Date beforedate, Date afterdate)
			throws ParseException {
		long dayCount = 0L;
		try {
			dayCount = (afterdate.getTime() - beforedate.getTime()) / 86400000L;
		} catch (Exception e) {
			log.info("Date parse error!");
		}
		return dayCount;
	}

	public static long getDistinceDay(String beforedate) throws ParseException {
		return getDistinceDay(beforedate, getTodayStr());
	}

	public static long getDistinceTime(String beforeDateTime,
			String afterDateTime) throws ParseException {
		long timeCount = 0L;
		try {
			Date d1 = parseDate(beforeDateTime);
			Date d2 = parseDate(afterDateTime);

			timeCount = (d2.getTime() - d1.getTime()) / 3600000L;
		} catch (ParseException e) {
			log.info("Date parse error!");
			throw e;
		}
		return timeCount;
	}

	public static long getDistinceTime(String beforeDateTime)
			throws ParseException {
		return getDistinceTime(beforeDateTime, DateFormat.getDateInstance()
				.format(new Timestamp(System.currentTimeMillis())));
	}

	public static long getDistinceMinute(String beforeDateTime,
			String afterDateTime) throws ParseException {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long timeCount = 0L;
		try {
			Date d1 = d.parse(beforeDateTime);
			Date d2 = d.parse(afterDateTime);

			timeCount = (d2.getTime() - d1.getTime()) / 60000L;
		} catch (ParseException e) {
			log.info("Date parse error!");
			throw e;
		}
		return timeCount;
	}

	public static long getDistinceMinute(String afterDateTime)
			throws ParseException {
		return getDistinceMinute(
				DateFormat.getDateInstance().format(
						new Timestamp(System.currentTimeMillis())),
				afterDateTime);
	}

	public static boolean isOvertime(String beforeDateTime, String timeCount) {
		boolean exceed = false;
		try {
			long count1 = Long.parseLong(timeCount);
			long count2 = getDistinceTime(beforeDateTime);
			if (count1 < count2)
				exceed = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return exceed;
	}

	public static String getTimestamStr(Timestamp timestamp) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(timestamp);
	}

	public static String getTimeStr(Time time) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		return format.format(time);
	}

	public static boolean isBeforeCheckDate(String checkdate, Date auditDate)
			throws ParseException {
		Date cd;
		try {
			cd = new Date(parseDate(checkdate).getTime());
		} catch (ParseException ex) {
			log.info(ex.getMessage());
			return false;
		}
		return isBeforeCheckDate(cd, auditDate);
	}

	private static boolean isBeforeCheckDate(Date checkdate, Date auditDate)
			throws ParseException {
		return auditDate.before(checkdate);
	}

	public static String format(Date date, String formatText) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat(formatText);
		return format.format(date);
	}

	public static int getDaysOfMonth(Date startdate, Date enddate, String month)
			throws Exception {
		Calendar cs = Calendar.getInstance();
		cs.setTime(startdate);
		Calendar ce = Calendar.getInstance();
		ce.setTime(enddate);

		int startmonth = cs.get(2) + 1;
		int endmonth = ce.get(2) + 1;
		int m = Integer.parseInt(month);

		Date ld = getLastDay(String.valueOf(cs.get(1)), month);
		Calendar c = Calendar.getInstance();
		c.setTime(ld);
		int day = c.get(5);
		if ((startmonth < m) && (m < endmonth))
			return day;
		if (m == startmonth) {
			return (day - cs.get(5));
		}
		if (m == endmonth) {
			return ce.get(5);
		}
		return 0;
	}

	public static int diffDateH(String beforeDateTime, String afterDateTime)
			throws ParseException {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int HourCount = 0;
		try {
			Date d1 = d.parse(beforeDateTime);
			Date d2 = d.parse(afterDateTime);
			HourCount = Math
					.round((float) (d2.getTime() - d1.getTime()) / 86400000.0F * 100.0F) / 100;
		} catch (ParseException e) {
			log.info("Date parse error!");
			throw e;
		}
		return HourCount;
	}

	public static Date getNextDateByYearCount(Date date, int yearCount)
			throws ParseException {
		Calendar scalendar = Calendar.getInstance();
		scalendar.setTime(date);
		scalendar.add(1, yearCount);
		return scalendar.getTime();
	}

	public static Date getNextDateByMonthCount(Date date, int monthCount)
			throws ParseException {
		Calendar scalendar = Calendar.getInstance();
		scalendar.setTime(date);
		scalendar.add(2, monthCount);
		return scalendar.getTime();
	}

	public static Date getNextDateByDayCount(Date date, int dayCount)
			throws ParseException {
		Calendar scalendar = Calendar.getInstance();
		scalendar.setTime(date);
		scalendar.add(5, dayCount);
		return scalendar.getTime();
	}

	public static Date getNextDateByMinuteCount(Date date, int minuteCount)
			throws ParseException {
		Calendar scalendar = Calendar.getInstance();
		scalendar.setTime(date);
		scalendar.add(12, minuteCount);
		return scalendar.getTime();
	}
	
	
	public static Date getNextHour(Date date)
			throws ParseException {
		Calendar scalendar = Calendar.getInstance();
		scalendar.setTime(date);
		int minute=scalendar.get(Calendar.MINUTE);
		scalendar.add(12, 60-minute);
		return scalendar.getTime();
	}
	

	public static long getDiffTime(Date beforeTime, Date afterTime) {
		String beforeTimeStr;
		try {
			beforeTimeStr = format(beforeTime, "HH:mm:ss");
			String afterTimeStr = format(afterTime, "HH:mm:ss");
			Date bTime = parseTime(beforeTimeStr);
			Date aTime = parseTime(afterTimeStr);
			long diff = aTime.getTime() - bTime.getTime();
			return diff;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0L;
	}

	public static long getDiffDateTime(Date beforeTime, Date afterTime) {
		long diff;
		try {
			diff = afterTime.getTime() - beforeTime.getTime();
			return diff;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0L;
	}

	public static int getWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (week_index < 0) {
			week_index = 0;
		}
		return week_index;
	}
	
	/**
	 * 0是上午，1是下午
	 * @param date
	 * @return
	 */
	public static int getAmOrPm(Date date){
		GregorianCalendar ca = new GregorianCalendar();  
		ca.setTime(date);
		return  ca.get(GregorianCalendar.AM_PM);
	}
	
	public static void main(String[] args) throws ParseException {
		System.out.println(getTimeStr(new Date()));
		GregorianCalendar ca = new GregorianCalendar();  
		Date curDate=parseTime("12:01:00");
		ca.setTime(curDate);
		System.out.println(ca.get(GregorianCalendar.AM_PM));
	}
}