package com.badaklng.app.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.LocalDate;
import org.joda.time.Minutes;
import org.joda.time.Period;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.web.multipart.MultipartFile;

import com.badaklng.app.constant.Constants;
import com.badaklng.app.constant.MailTypeEnum;
import com.badaklng.app.hibernate.AppDAO;
import com.badaklng.app.hibernate.AppException;
import com.badaklng.app.hibernate.AppMailDAO;
import com.badaklng.app.hibernate.AppUser;
import com.badaklng.app.hibernate.HibernateSessionFactory;

//import jersey.repackaged.com.google.common.net.InetAddresses;

public final class Utility {

	private static final Logger logger = Logger.getLogger(Utility.class);
	public static final String NULL_STRING = " ";
	public static final String CRLF = new String(new char[] { (char) 13, (char) 10 });
	public static final String DATE_FORMAT = "dd-MM-yyyy";
	public static final String MONTH_FORMAT = "MMMM";
	public static final String DATE_TO_TIMESTAMP_FORMAT = "yyyy-MM-dd";

	public static final String DATETIME_FORMAT = "dd-MM-yyyy HH:mm:ss";
	public static final String TIME_FORMAT = "HH:mm:ss";

	public static final String SHIFT_TIME_FORMAT = "HH:mm";
	public static final String SHIFT_DATETIME_FORMAT = "dd-MM-yyyy HH:mm";

	public static final String DB_DATE_FORMAT = "yyyyMMdd";
	public static final String DB_DATETIME_FORMAT = "yyyyMMddHHmmssSSS";

	public static final String TIMESTAMP_YY = "yyMMddHHmmssSSS";
	public static final String TIMESTAMP_DD = "ddMMyyHHmmssSSS";
	public static final String DATETIME_DD = "ddMMyyHHmmss";
	public static final String DURATION_FORMAT = "%02d:%02d:%02d";
	public static final String MINUTE = "Minute", HOUR = "Hour", DAY = "Day";

	private final static String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

	public static void notify(String message) {

		AppMailDAO mailDAO = null;

		try {
			mailDAO = new AppMailDAO();
			mailDAO.begin();
			mailDAO.queueMail(mailDAO.getSequence(), mailDAO.getDeveloperContact(),
					"[" + Constants.APPL_SHORTNAME + "] Application Notification",
					"<div class='RepTitle'>Notification : </div><div class='BoxContent'>" + message + "</div>",
					MailTypeEnum.SYSTEM_NOTIF.toString(), null);
			mailDAO.commit();
		} catch (Exception ex) {
			if (mailDAO != null)
				mailDAO.rollback();
			logger.error("Unable to notify", ex);
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	public static void logExceptionOnly(Exception e) {
		StringWriter sw = new StringWriter();

		AppDAO dao = null;

		try {
			dao = new AppDAO();

			e.printStackTrace(new PrintWriter(sw));
			sw.flush();

			dao.beginTransaction();

			AppException excp = new AppException(dao.getSequence());
			excp.setMessages(sw.getBuffer().toString());
			excp.setIsSolved(false);
			excp.setCreateDate(Utility.getNow());
			dao.save(excp);

			dao.commitTransaction();
		} catch (Exception ex) {
			if (dao != null) {
				dao.rollbackTransaction();
			}
			logger.error("Unable to log to database", ex);
		} finally {
			HibernateSessionFactory.closeSession();
			try {
				sw.close();
			} catch (IOException e1) {
			}
		}
	}

	public static void logExceptionAndNotify(Exception e) {
		logExceptionAndNotify(e, null);
	}

	public static void logExceptionAndNotify(Exception e, AppUser usr) {
		StringWriter sw = new StringWriter();

		AppDAO dao = null;
		AppMailDAO mailDAO = null;
		String newId;

		try {
			dao = new AppDAO();
			mailDAO = new AppMailDAO();

			e.printStackTrace(new PrintWriter(sw));
			sw.flush();

			dao.beginTransaction();
			newId = dao.getSequence();
			AppException excp = new AppException(newId);
			String msg = (usr != null ? "Raised by " + usr.getUserId() + " from " + usr.getIpAddress() + "\n" : "")
					+ sw.getBuffer().toString();
			excp.setMessages(msg.substring(0, 4000));
			excp.setIsSolved(false);
			excp.setCreateDate(Utility.getNow());
			dao.save(excp);

			String body = "<div class='RepTitle'>Critical Exception</div><div class='BoxContent'>A critical error has been caught with ID <b>"
					+ newId + "</b></div><br /><br />" + sw.getBuffer().toString();
			mailDAO.queueMail(newId, mailDAO.getDeveloperContact(), "Critical Exception Raised with ID " + newId,
					body.substring(0, 4000), MailTypeEnum.SYSTEM_ERROR.getMailTypeValue(), null);
			dao.commitTransaction();
		} catch (Exception ex) {
			if (dao != null) {
				dao.rollbackTransaction();
			}
			logger.error("Unable to log to database and notify", ex);
		} finally {
			HibernateSessionFactory.closeSession();
			try {
				sw.close();
			} catch (IOException e1) {
			}
		}
	}

	public static Timestamp getNow() {
		return new Timestamp(new Date().getTime());
	}

	public static Timestamp getToday() {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.HOUR_OF_DAY, 0); // anything 0 - 23
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return new Timestamp(c.getTimeInMillis());
	}

	public static String getNow(String template) {
		SimpleDateFormat sdf = new SimpleDateFormat(template);
		return sdf.format(new Date());
	}

	public static String getBeforeNow(String type, Integer duration, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Long val = null;

		if (type == MINUTE) {
			val = System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(duration);
		}
		if (type == HOUR) {
			val = System.currentTimeMillis() - TimeUnit.HOURS.toMillis(duration);
		}
		if (type == DAY) {
			val = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(duration);
		}

		return sdf.format(new Date(val));
	}

	public static Timestamp getBeforeNow(String type, Integer duration) {
		Long val = null;

		if (type == MINUTE) {
			val = System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(duration);
		}
		if (type == HOUR) {
			val = System.currentTimeMillis() - TimeUnit.HOURS.toMillis(duration);
		}
		if (type == DAY) {
			val = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(duration);
		}

		return new Timestamp(val);
	}

	public static String getAfterNow(String type, Integer duration, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Long val = null;

		if (type == MINUTE) {
			val = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(duration);
		}
		if (type == HOUR) {
			val = System.currentTimeMillis() + TimeUnit.HOURS.toMillis(duration);
		}
		if (type == DAY) {
			val = System.currentTimeMillis() + TimeUnit.DAYS.toMillis(duration);
		}

		return sdf.format(new Date(val));
	}

	public static Timestamp getAfterNow(String type, Integer duration) {
		Long val = null;

		if (type == MINUTE) {
			val = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(duration);
		}
		if (type == HOUR) {
			val = System.currentTimeMillis() + TimeUnit.HOURS.toMillis(duration);
		}
		if (type == DAY) {
			val = System.currentTimeMillis() + TimeUnit.DAYS.toMillis(duration);
		}

		return new Timestamp(val);
	}

	public static boolean isURLMatches(String url, String[] patterns) {
		boolean outcome = false;
		for (String str : patterns) {
			Pattern patrn = Pattern.compile(str, Pattern.CASE_INSENSITIVE);
			Matcher matcher = patrn.matcher(url);
			outcome = outcome || matcher.find();
		}
		return outcome;
	}

	public static Timestamp stringDBTimetoTimestamp(String strDate) {
		if (strDate == null) {
			return null;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DB_DATETIME_FORMAT);
			Date date = sdf.parse(strDate);
			return new Timestamp(date.getTime());
		} catch (ParseException pe) {
			return null;
		}

	}

	public static Timestamp stringDBDateToTimestamp(String strDate) {
		if (strDate == null) {
			return null;
		}

		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DB_DATE_FORMAT);
			Date date = sdf.parse(strDate);
			return new Timestamp(date.getTime());
		} catch (ParseException pe) {
			return null;
		}

	}

	public static String timestampToDBTimeString(Timestamp date) {
		if (date == null) {
			return "";
		}

		SimpleDateFormat sdf = new SimpleDateFormat(DB_DATETIME_FORMAT);
		return sdf.format(date);

	}

	public static String timestampToDBDateString(Timestamp date) {
		if (date == null) {
			return "";
		}

		SimpleDateFormat sdf = new SimpleDateFormat(DB_DATE_FORMAT);
		return sdf.format(date);

	}

	public static String substr(String str, int maxLength) {
		if (!Utility.isEmptyString(str)) {

			int strLen = str.length();

			if (strLen > maxLength) {
				strLen = maxLength;
			}

			str = str.substring(0, strLen);
		}

		return str;
	}

	public static String encodeURIComponent(String s) {
		if (s == null) {
			return null;
		}

		return StringEscapeUtils.escapeJavaScript(s.replaceAll("\"", "&quot;"));
	}

	public static String numberToString(Short number) {
		if (number == null) {
			return "0";
		}

		return String.valueOf(number);
	}

	public static String numberToString(Long number) {
		if (number == null) {
			return "0";
		}

		return String.valueOf(number);
	}

	public static Long stringToLong(String str) {
		try {
			return Long.valueOf(Utility.decorateString(str).equalsIgnoreCase("") ? "0" : str);
		} catch (NumberFormatException nfe) {
			return new Long(0);
		}
	}

	public static String numberToString(Integer number) {
		if (number == null) {
			return "0";
		}
		return String.valueOf(number);
	}

	public static Integer stringToInteger(String str) {
		try {
			return Integer.valueOf(Utility.decorateString(str).equalsIgnoreCase("") ? "0" : str);
		} catch (NumberFormatException nfe) {
			return new Integer(0);
		}
	}

	public static String numberToString(Double number) {
		return numberToString(number, true);
	}

	public static String numberToString(Double number, Boolean withSeparator) {
		try {
			if (number == null) {
				return "0";
			}
			if (withSeparator) {
				return String.format(Locale.US, "%1$,.2f", number);
			}
			return String.format(Locale.US, "%1$,.2f", number).replace(",", "");
		} catch (Exception e) {
			return "0";
		}
	}

	public static Double stringToDouble(String str) {
		if (str == null) {
			return new Double(0);
		}

		try {
			NumberFormat format = NumberFormat.getInstance(Locale.US);

			Number number = format.parse(str);

			return number.doubleValue();
		} catch (ParseException nfe) {
			return new Double(0);
		}
	}

	public static String getCurrentDate() {
		return new SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance().getTime());
	}

	public static String getCurrentMonth() {
		return new SimpleDateFormat(MONTH_FORMAT).format(Calendar.getInstance().getTime());
	}

	public static String getCurrentDateTime() {
		return new SimpleDateFormat(DATETIME_FORMAT).format(Calendar.getInstance().getTime());
	}

	public static String getCurrentDBDatetime() {
		return DateTimeFormat.forPattern(DB_DATETIME_FORMAT).print(DateTime.now());
	}

	public static String getCurrentShiftTime() {
		return new SimpleDateFormat(SHIFT_TIME_FORMAT).format(Calendar.getInstance().getTime());
	}

	public static String timestampToDateString(Timestamp date) {
		if (date == null) {
			return "";
		}

		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		return sdf.format(date);

	}

	public static String dateToDateString(Date date) {
		if (date == null) {
			return "";
		}

		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		return sdf.format(date);

	}

	public static String timestampToShiftDatetimeString(Timestamp date) {
		return timestampToString(date, SHIFT_DATETIME_FORMAT);
	}

	public static String timestampToString(Timestamp date, String format) {
		if (date == null) {
			return "";
		}

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);

	}

//	public static String timestampToShiftString(Timestamp date) {
//		if (date == null) {
//			return "";
//		}
//
//		SimpleDateFormat sdf = new SimpleDateFormat(SHIFT_TIME_FORMAT);
//		return sdf.format(date);
//
//	}

	public static String timestampToTimeString(Timestamp date) {
		if (date == null) {
			return "";
		}

		SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
		return sdf.format(date);

	}

	public static Timestamp stringDateToTimestamp(String strDate) {
		if (strDate == null || Utility.isEmptyString(strDate)) {
			return null;
		}

		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			Date date = sdf.parse(strDate);
			return new Timestamp(date.getTime());
		} catch (ParseException pe) {
			return null;
		}

	}

	public static Timestamp stringDatetimeToTimestamp(String strDate) {
		if (strDate == null) {
			return null;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT);
			Date date = sdf.parse(strDate);
			return new Timestamp(date.getTime());
		} catch (ParseException pe) {
			return null;
		}
	}

	public static Timestamp stringToTimestamp(String strDate, String strFormat) {
		if (strDate == null || strFormat == null || isEmptyString(strDate)) {
			return null;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
			Date date = sdf.parse(strDate);
			return new Timestamp(date.getTime());
		} catch (ParseException pe) {
			return null;
		}
	}

	public static Timestamp shiftDatetimeToTimestamp(String strDate) {
		if (strDate == null) {
			return null;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(SHIFT_DATETIME_FORMAT);
			Date date = sdf.parse(strDate);
			return new Timestamp(date.getTime());
		} catch (ParseException pe) {
			return null;
		}
	}

	public static boolean isEmptyString(String str) {
		return str == null || str.trim().length() == 0;
	}

	public static boolean isWildcardOnly(String str) {
		return !isEmptyString(str.trim().replaceAll("%", ""));
	}

	public static String decorateString(String str) {
		if (!isEmptyString(str)) {
			return str.trim();
		} else {
			return "";
		}
	}

	public static String decorateStringToWildcard(String str) {
		if (!isEmptyString(str)) {
			if (str.contains("%")) {
				return decorateStringToWildcard(str, true);
			} else {
				return decorateStringToWildcard(str, false);
			}
		} else {
			return "%";
		}
	}

	public static String decorateStringToWildcard(String str, Boolean exact) {
		if (!isEmptyString(str)) {
			return exact ? str.trim() : "%" + str.trim() + "%";
		} else {
			return "%";
		}
	}

	public static String decorateStringToStartWith(String str) {
		if (!isEmptyString(str)) {
			return str.trim().toUpperCase() + "%";
		} else {
			return "%";
		}
	}

	public static String employeeIdWithZeroPad(String emplid) {
		if (!isEmptyString(emplid)) {
			return "0000" + emplid.trim();
		} else {
			return "";
		}
	}

	public static String employeeIdWithoutZeroPad(String emplid) {
		if (!isEmptyString(emplid)) {
			return emplid.trim().replaceFirst("^0+(?!$)", "");
		} else {
			return "";
		}
	}

	public static boolean isNumeric(String str) {
		return str.replace("%", "").matches("-?\\d+(\\.\\d+)?");
	}

	public static Timestamp dateToTimestamp(Date date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_TO_TIMESTAMP_FORMAT);
			date = sdf.parse(sdf.format(date));
			return new Timestamp(date.getTime());
		} catch (ParseException pe) {
			return null;
		}
	}

	public static Timestamp modifyDay(Timestamp theDate, int modifier) {

		if (theDate == null) {
			return Utility.stringDatetimeToTimestamp(Utility.getCurrentDBDatetime());
		}

		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_FORMAT);
		LocalDate jDate = formatter.parseLocalDate(sdf.format(theDate));
		jDate = jDate.plusDays(modifier);

		return new Timestamp(jDate.toDate().getTime());
	}

	public static Timestamp modifyMonth(Timestamp theDate, int modifier) {

		if (theDate == null) {
			return Utility.stringDatetimeToTimestamp(Utility.getCurrentDBDatetime());
		}

//		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
//		DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_FORMAT);
//		LocalDate jDate = formatter.parseLocalDate(theDate);
		LocalDate jDate = new LocalDate(theDate.getTime());
		jDate = jDate.plusMonths(modifier);

		return new Timestamp(jDate.toDate().getTime());
	}

	public static int substractDate(Timestamp startDate, Timestamp endDate) {

		if (startDate == null || endDate == null) {
			return 0;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_FORMAT);
		LocalDate jStartDate = formatter.parseLocalDate(sdf.format(startDate));
		LocalDate jEndDate = formatter.parseLocalDate(sdf.format(endDate));
		Period diff = new Period(jStartDate, jEndDate);
		return diff.getDays() + 1;
	}

	public static LocalDate stringDateToJodaDate(String strDate, String FORMAT) {
		if (isEmptyString(strDate)) {
			return null;
		}
		try {
			DateTimeFormatter formatter = DateTimeFormat.forPattern(FORMAT);
			return formatter.parseLocalDate(strDate);
		} catch (Exception e) {
			return null;
		}
	}

	public static Timestamp stringToTimeStampFormat(String strDate, String format) {
		if (strDate == null)
			return null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date date = sdf.parse(strDate);
			return new Timestamp(date.getTime());
		} catch (ParseException pe) {
			return null;
		}
	}

//	public static Timestamp StringDateToTimeStamp(String strDate) {
//		if (strDate == null)
//			return null;
//		try {
//			SimpleDateFormat sdf = new SimpleDateFormat(SHIFT_DATETIME_FORMAT);
//			Date date = sdf.parse(strDate);
//			return new Timestamp(date.getTime());
//		} catch (ParseException pe) {
//			return null;
//		}
//	}
//
//	public static Timestamp StringDateToTimeStampCopy(String strDate) {
//		if (strDate == null)
//			return null;
//		try {
//			SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT);
//			Date date = sdf.parse(strDate);
//			return new Timestamp(date.getTime());
//		} catch (ParseException pe) {
//			return null;
//		}
//	}

	public static String convertTimeWithColon(String strTime) {

		if (Utility.isEmptyString(strTime)) {
			return "";
		}

		strTime = strTime.replace(":", "");

		if (strTime != null && strTime.length() < 3) {
			return "";
		} else if (strTime.length() < 4) {
			return "0" + strTime.substring(0, 1) + ":" + strTime.substring(1);
		} else {
			return strTime.substring(0, 2) + ":" + strTime.substring(2);
		}
	}

	public static String getRandomAlphanumeric(int length) {

		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < length) {
			salt.append(SALTCHARS.charAt(rnd.nextInt(SALTCHARS.length())));
		}
		return salt.toString();

	}

	public static double roundDouble(double value, int places) {
		if (places < 0) {
			return 0D;
		}

		return new BigDecimal(value).setScale(places, RoundingMode.HALF_UP).doubleValue();
	}

	public static short stringToAgeFormat(String value) {
		short age = new Double(Math.floor(stringToDouble(value).doubleValue())).shortValue();
		return age == 0 ? 1 : age;
	}

	public static String reverseDate(String date) {
		String[] arr = date.split("-");
		return arr[2] + "-" + arr[1] + "-" + arr[0];
	}

	public static String durationString(Double duration) {
		if (duration != null) {
			long temp = duration.longValue();
			long hours = TimeUnit.MILLISECONDS.toHours(temp);
			temp -= TimeUnit.HOURS.toMillis(hours);
			long minute = TimeUnit.MILLISECONDS.toMinutes(temp);
			temp -= TimeUnit.MINUTES.toMillis(minute);
			long second = TimeUnit.MILLISECONDS.toSeconds(temp);

			return String.format(DURATION_FORMAT, hours, minute, second);
		}
		return null;
	}

	/**
	 * Return time difference between two times
	 *
	 * @param start date / time that more older
	 * @param end   date / timet that more newer
	 * @return time difference with signed operator to differ end is less than start
	 *         or not
	 */
	public static String timeDiff(Timestamp start, Timestamp end) {
		return timeDiff(start, end, true);
	}

	/**
	 * Return time difference between two times
	 *
	 * @param start  date / time that more older
	 * @param end    date / timet that more newer
	 * @param signed optional operator to distinguish end is older or newer than
	 *               start
	 * @return time difference with optional signed operator
	 */
	public static String timeDiff(Timestamp start, Timestamp end, Boolean signed) {
		Integer day, hour, min, sec;
		String result = "";
		if (start != null && end != null) {
			DateTime st = new DateTime(start.getTime());
			DateTime ed = new DateTime(end.getTime());

			// mth = Months.monthsBetween(st, ed).getMonths();
			day = Days.daysBetween(st, ed).getDays();
			hour = Hours.hoursBetween(st, ed).getHours() % 24;
			min = Minutes.minutesBetween(st, ed).getMinutes() % 60;
			sec = Seconds.secondsBetween(st, ed).getSeconds() % 60;

			if (signed) {
				if (st.isAfter(ed)) {
					result += "- ";
				} else {
					result += "+ ";
				}
			}

			// if (mth != 0)
			// result += Math.abs(mth) + " Mth, ";
			if (day != 0) {
				result += Math.abs(day) + " Day(s), ";
			}
			result += String.format("%02d", Math.abs(hour)) + ":" + String.format("%02d", Math.abs(min)) + ":"
					+ String.format("%02d", Math.abs(sec));
			return result;
		}
		return null;
	}

	public static String reverse(String input) {
		String res = "";
		StringBuilder input1 = new StringBuilder();
		input1.append(input);
		input1 = input1.reverse();
		for (int i = 0; i < input1.length(); i++) {
			res += input1.charAt(i);
		}

		return res;
	}

	public static String listToString(List<String> list, String separator) {
		return listToString(list, separator, "");
	}

	public static String listToString(List<String> list, String separator, String encapsulate) {

		if (list != null && list.size() > 0) {
			StringBuilder csvBuilder = new StringBuilder();

			for (String city : list) {
				csvBuilder.append(encapsulate);
				csvBuilder.append(city);
				csvBuilder.append(encapsulate);
				csvBuilder.append(separator);
			}

			String csv = csvBuilder.toString();
			csv = csv.substring(0, csv.length() - separator.length());

			return csv;
		}
		return null;
	}

	public static String generateRandomNum(int i) {
		String res = "";
		Random randomGenerator = new Random();
		for (int idx = 0; idx < i; idx++) {
			int randomInt = randomGenerator.nextInt(10);
			res += randomInt;
		}
		return res;
	}

	public static ArrayList<String> splitString(String input, String separator) {
		String[] temp = input.split(separator);
		ArrayList<String> res = new ArrayList<String>();

		for (int i = 0; i < temp.length; i++) {
			res.add(temp[i].trim());
		}
		return res;
	}

	public static <T> List<T> removeDuplicate(List<T> items) {
		List<T> ret = new ArrayList<T>();
		Set<T> hs = new HashSet<T>();
		hs.addAll(items);
		ret.addAll(hs);
		return ret;
	}

	public static String stringListToHTMLList(List<String> list, Boolean ordered) {
		String res = "";
		if (ordered) {
			res = "<ol>";
		} else {
			res = "<ul>";
		}

		for (String s : list) {
			res += "<li>" + s + "</li>";
		}

		if (ordered) {
			res += "</ol>";
		} else {
			res += "</ul>";
		}

		return res;
	}

	public static String generateMD5(String input) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			return input;
		}
		md.update(input.getBytes());

		byte byteData[] = md.digest();

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();
	}

	public static boolean isExpired(Timestamp expiredDate) {
		return expiredDate.before(new Timestamp(new Date().getTime()));
	}

	public static void debugFormValue(Object form) {
		try {
			for (Field field : form.getClass().getDeclaredFields()) {
				if (field.isAccessible()) {
					System.out.println(field.getName() + " - " + field.get(form));
				}
			}
		} catch (Exception e) {
			System.out.println("Exception caught on debugging form value : " + e.getMessage());
		}
	}

	public static String leading(String string, char c, int i) {
		while (string.length() < i) {
			string = c + string;
		}
		return string;
	}

	public static String trailing(String string, char c, int i) {
		while (string.length() < i) {
			string = string + c;
		}
		return string;
	}

	/**
	 * First parameter must be an URL without question mark (?) Next 2 parameter
	 * must be an URL parameter with it's value
	 *
	 * @param params
	 * @return complete URL with parameter
	 */
	public static String getURL(String... params) {
		String prefix = params[0] + "?";
		for (int i = 1; i < params.length; i += 2) {
			prefix += (i != 1 ? "&" : "") + params[i] + "=" + params[i + 1];
		}
		return prefix;
	}

	public static String initial(String name) {
		Pattern p = Pattern.compile("((^| )[A-Za-z])");
		Matcher m = p.matcher(name);
		String initials = "";
		while (m.find()) {
			initials += m.group().trim();
		}
		return initials.toUpperCase();
	}

	public static String getIDfromSequence(String pattern, Integer seq, Integer len) {
		int length = String.valueOf(seq).length();
		String set = "";
		String id = "";
		for (int i = length; i < len; i++) {
			set += "0";
		}
		id = pattern + set + String.valueOf(seq);
		return id;
	}

//	public static String encrypt(String value) {
//		return encrypt(Constants.KEY, Constants.VECTOR, value);
//	}
//
//	public static String encrypt(String value, String key) {
//		return encrypt(key, Constants.VECTOR, value);
//	}
//
//	public static String encrypt(String key, String initVector, String value) {
//		try {
//			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
//			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
//
//			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
//			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
//
//			byte[] encrypted = cipher.doFinal(value.getBytes());
//
//			return Base64.encodeBase64String(encrypted);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//
//		return null;
//	}
//
//	public static String decrypt(String value) {
//		return decrypt(Constants.KEY, Constants.VECTOR, value);
//	}
//
//	public static String decrypt(String value, String key) {
//		return decrypt(key, Constants.VECTOR, value);
//	}
//
//	public static String decrypt(String key, String initVector, String encrypted) {
//		try {
//			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
//			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
//
//			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
//			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
//
//			byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));
//
//			return new String(original);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//
//		return null;
//	}

	public static String getFileExtension(MultipartFile file) {
		return FilenameUtils.getExtension(file.getOriginalFilename());
	}

	public static final void copyFile(InputStream is, OutputStream os) throws IOException {
		if (is == null)
			throw new IOException("File input is NULL");

		final int BUFFER_SIZE = 8192;
		boolean somethingToRead = false;

		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;

		while ((bytesRead = is.read(buffer)) != -1) {
			os.write(buffer, 0, bytesRead);
			somethingToRead = true;
		}

		if (!somethingToRead)
			throw new IOException("File input could not be read or empty");

	}

//	public static boolean isIPAddress(String ipAddress) {
//		return InetAddresses.isInetAddress(ipAddress);
//	}

	public static <T> T nvl(T par1, T par2) {
		if (par1 != null)
			return par1;
		return par2;
	}

	public static String join(List<String> input, String delimiter) {

		if (input == null || input.size() <= 0)
			return "";

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < input.size(); i++) {

			sb.append(input.get(i));

			// if not the last item
			if (i != input.size() - 1) {
				sb.append(delimiter);
			}

		}

		return sb.toString();

	}

	public static <T> Boolean in(T param1, T... params) {
		for (T t : params) {
			if (param1.equals(t) || param1 == t)
				return true;
		}
		return false;
	}

}
