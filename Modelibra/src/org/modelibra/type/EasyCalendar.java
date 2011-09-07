/*
 * Modelibra
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.modelibra.type;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.exception.TypeRuntimeException;
import org.modelibra.util.Log4jConfigurator;
import org.modelibra.util.Transformer;

/**
 * An easy to use version of Java Calendar.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-10-28
 */
public class EasyCalendar {

	private static Log log = LogFactory.getLog(EasyCalendar.class);

	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

	public static final String DEFAULT_DATE_TIME_PATTERN = "yyyy-MM-dd hh:mm:ss";

	protected static GregorianCalendar gregorianCalendar = new GregorianCalendar();

	private static EasyCalendar easyCalendar;

	private String datePattern = EasyCalendar.DEFAULT_DATE_PATTERN;

	private String dateTimePattern = EasyCalendar.DEFAULT_DATE_TIME_PATTERN;

	/**
	 * Constructs an easy calendar.
	 */
	public EasyCalendar() {

	}

	/**
	 * Constructs only one EasyCalendor object, but only if it does not exist
	 * already. Singleton pattern is not used.
	 * 
	 * @return easy calendar
	 */
	public static EasyCalendar getOne() {
		if (easyCalendar == null) {
			easyCalendar = new EasyCalendar();
		}
		return easyCalendar;
	}

	/**
	 * Sets a date pattern.
	 * 
	 * @param datePattern
	 *            date pattern
	 */
	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}

	/**
	 * Gets a date pattern.
	 * 
	 * @return date pattern
	 */
	public String getDatePattern() {
		return datePattern;
	}

	/**
	 * Sets a date time pattern.
	 * 
	 * @param dateTimePattern
	 *            date time pattern
	 */
	public void setDateTimePattern(String dateTimePattern) {
		this.dateTimePattern = dateTimePattern;
	}

	/**
	 * Gets a date time pattern.
	 * 
	 * @return date time pattern
	 */
	public String getDateTimePattern() {
		return dateTimePattern;
	}

	/**
	 * Gets a date from year, month and day numbers. Hours, minutes, seconds and
	 * milliseconds are set to 0.
	 * 
	 * @param year
	 *            year
	 * @param month
	 *            month
	 * @param day
	 *            day
	 * @return date
	 */
	public Date getDate(int year, int month, int day) {
		// short lived calendar, just for this conversion
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1); // months start with 0
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date date = calendar.getTime();
		return date;
	}

	/**
	 * Gets a date time from year, month, day, hour, minute, second numbers.
	 * Milliseconds are set to 0.
	 * 
	 * @param year
	 *            year
	 * @param month
	 *            month
	 * @param day
	 *            day
	 * @param hour
	 *            hour
	 * @param minute
	 *            minute
	 * @param second
	 *            second
	 * @return date time
	 */
	public Date getDateTime(int year, int month, int day, int hour, int minute,
			int second) {
		// short lived calendar, just for this conversion
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1); // months start with 0
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.HOUR, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		calendar.set(Calendar.MILLISECOND, 0);
		Date date = calendar.getTime();
		return date;
	}

	/**
	 * Gets a date from year, month and day strings.
	 * 
	 * @param yearString
	 *            year string
	 * @param monthString
	 *            month string
	 * @param dayString
	 *            day string
	 * @return date
	 */
	public Date getDate(String yearString, String monthString, String dayString) {
		Date date = null;
		try {
			Integer yearInteger = Transformer.integer(yearString);
			Integer monthInteger = Transformer.integer(monthString);
			Integer dayInteger = Transformer.integer(dayString);
			if (yearInteger != null && monthInteger != null
					&& dayInteger != null) {
				int year = yearInteger.intValue();
				int month = monthInteger.intValue();
				int day = dayInteger.intValue();
				date = getDate(year, month, day);
			}
		} catch (TypeRuntimeException e) {
			String msg = "EasyCalendar.getDate -- year, month or day is not Integer: "
					+ yearString + "-" + monthString + "-" + dayString;
			throw new TypeRuntimeException(msg, e);
		}
		return date;
	}

	/**
	 * Gets a date time from year, month, day, hour, minute, second strings.
	 * 
	 * @param yearString
	 *            year string
	 * @param monthString
	 *            month string
	 * @param dayString
	 *            day string
	 * @param hourString
	 *            hour string
	 * @param minuteString
	 *            minute string
	 * @param secondString
	 *            second string
	 * @return date time
	 */
	public Date getDateTime(String yearString, String monthString,
			String dayString, String hourString, String minuteString,
			String secondString) {
		Date date = null;
		try {
			Integer yearInteger = Transformer.integer(yearString);
			Integer monthInteger = Transformer.integer(monthString);
			Integer dayInteger = Transformer.integer(dayString);
			Integer hourInteger = Transformer.integer(hourString);
			Integer minuteInteger = Transformer.integer(minuteString);
			Integer secondInteger = Transformer.integer(secondString);
			if (yearInteger != null && monthInteger != null
					&& dayInteger != null && hourInteger != null
					&& minuteInteger != null && secondInteger != null) {
				int year = yearInteger.intValue();
				int month = monthInteger.intValue();
				int day = dayInteger.intValue();
				int hour = hourInteger.intValue();
				int minute = minuteInteger.intValue();
				int second = secondInteger.intValue();
				date = getDateTime(year, month, day, hour, minute, second);
			}
		} catch (TypeRuntimeException e) {
			String msg = "EasyCalendar.getDateTime -- year, month, day, hour, minute or second is not Integer: "
					+ yearString
					+ "-"
					+ monthString
					+ "-"
					+ dayString
					+ " "
					+ hourString + ":" + minuteString + ":" + secondString;
			throw new TypeRuntimeException(msg, e);
		}
		return date;
	}

	/**
	 * Gets a date string.
	 * 
	 * @param date
	 *            date
	 * @return date string
	 */
	public String getDateString(Date date) {
		if (datePattern == null) {
			datePattern = EasyCalendar.DEFAULT_DATE_PATTERN;
		}
		return Transformer.string(date, datePattern);
	}

	/**
	 * Gets a date time string.
	 * 
	 * @param date
	 *            date
	 * @return date time string
	 */
	public String getDateTimeString(Date date) {
		if (dateTimePattern == null) {
			dateTimePattern = EasyCalendar.DEFAULT_DATE_TIME_PATTERN;
		}
		return Transformer.string(date, dateTimePattern);
	}

	/**
	 * Gets a year string.
	 * 
	 * @param date
	 *            date
	 * @return year string
	 */
	public String getYearString(Date date) {
		String dateText = getDateString(date);
		String yearText = dateText.substring(0, 4);
		return yearText;
	}

	/**
	 * Gets a month string.
	 * 
	 * @param date
	 *            date
	 * @return month string
	 */
	public String getMonthString(Date date) {
		String dateText = getDateString(date);
		String monthText = dateText.substring(5, 7);
		return monthText;
	}

	/**
	 * Gets a day string.
	 * 
	 * @param date
	 *            date
	 * @return day string
	 */
	public String getDayString(Date date) {
		String dateText = getDateString(date);
		String dayText = dateText.substring(8, 10);
		return dayText;
	}

	/**
	 * Gets an hour string.
	 * 
	 * @param date
	 *            date
	 * @return hour string
	 */
	public String getHourString(Date date) {
		String dateTimeText = getDateTimeString(date);
		String hourText = dateTimeText.substring(11, 13);
		return hourText;
	}

	/**
	 * Gets a minute string.
	 * 
	 * @param date
	 *            date
	 * @return minute string
	 */
	public String getMinuteString(Date date) {
		String dateTimeText = getDateTimeString(date);
		String minuteText = dateTimeText.substring(14, 16);
		return minuteText;
	}

	/**
	 * Gets a second string.
	 * 
	 * @param date
	 *            date
	 * @return second string
	 */
	public String getSecondString(Date date) {
		String dateTimeText = getDateTimeString(date);
		String secondText = dateTimeText.substring(17, 19);
		return secondText;
	}

	/**
	 * Gets a year.
	 * 
	 * @param date
	 *            date
	 * @return year
	 */
	public int getYear(Date date) {
		String yearText = getYearString(date);
		Integer yearInteger = Transformer.integer(yearText);
		return yearInteger.intValue();
	}

	/**
	 * Gets a month.
	 * 
	 * @param date
	 *            date
	 * @return month
	 */
	public int getMonth(Date date) {
		String monthText = getMonthString(date);
		Integer monthInteger = Transformer.integer(monthText);
		return monthInteger.intValue();
	}

	/**
	 * Gets a day.
	 * 
	 * @param date
	 *            date
	 * @return day
	 */
	public int getDay(Date date) {
		String dayText = getDayString(date);
		Integer dayInteger = Transformer.integer(dayText);
		return dayInteger.intValue();
	}

	/**
	 * Gets an hour.
	 * 
	 * @param date
	 *            date
	 * @return hour
	 */
	public int getHour(Date date) {
		String hourText = getHourString(date);
		Integer hourInteger = Transformer.integer(hourText);
		return hourInteger.intValue();
	}

	/**
	 * Gets a minute.
	 * 
	 * @param date
	 *            date
	 * @return minute
	 */
	public int getMinute(Date date) {
		String minuteText = getMinuteString(date);
		Integer minuteInteger = Transformer.integer(minuteText);
		return minuteInteger.intValue();
	}

	/**
	 * Gets a second.
	 * 
	 * @param date
	 *            date
	 * @return second
	 */
	public int getSecond(Date date) {
		String secondText = getSecondString(date);
		Integer secondInteger = Transformer.integer(secondText);
		return secondInteger.intValue();
	}

	/**
	 * Gets the last day in year, month.
	 * 
	 * @param year
	 *            year
	 * @param month
	 *            month
	 * @return the last day in year, month
	 */
	public int getLastDay(int year, int month) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1); // months start with 0
		int day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		return day;
	}

	/**
	 * Gets the current date.
	 * 
	 * @return current date
	 */
	public Date getCurrentDate() {
		int year = this.getCurrentYear();
		int month = this.getCurrentMonth();
		int day = this.getCurrentDay();
		return getDate(year, month, day);
	}

	/**
	 * Gets the current date time.
	 * 
	 * @return current date time
	 */
	public Date getCurrentDateTime() {
		int year = this.getCurrentYear();
		int month = this.getCurrentMonth();
		int day = this.getCurrentDay();
		int hour = this.getCurrentHour();
		int minute = this.getCurrentMinute();
		int second = this.getCurrentSecond();
		return getDateTime(year, month, day, hour, minute, second);
	}

	/**
	 * Gets the current date string.
	 * 
	 * @return current date text
	 */
	public String getCurrentDateString() {
		Date date = getCurrentDate();
		return getDateString(date);
	}

	/**
	 * Gets the current date time string.
	 * 
	 * @return current date time text
	 */
	public String getCurrentDateTimeString() {
		Date date = getCurrentDateTime();
		return getDateTimeString(date);
	}

	/**
	 * Gets the current year.
	 * 
	 * @return current year
	 */
	public int getCurrentYear() {
		int year = gregorianCalendar.get(Calendar.YEAR);
		return year;
	}

	/**
	 * Gets the current year string.
	 * 
	 * @return current year string
	 */
	public String getCurrentYearString() {
		int year = getCurrentYear();
		return year + "";
	}

	/**
	 * Gets the current month.
	 * 
	 * @return current month
	 */
	public int getCurrentMonth() {
		int month = gregorianCalendar.get(Calendar.MONTH);
		return month + 1;
	}

	/**
	 * Gets the current month string.
	 * 
	 * @return current month string
	 */
	public String getCurrentMonthString() {
		int month = getCurrentMonth();
		return month + "";
	}

	/**
	 * Gets the current day.
	 * 
	 * @return current day
	 */
	public int getCurrentDay() {
		int day = gregorianCalendar.get(Calendar.DAY_OF_MONTH);
		return day;
	}

	/**
	 * Gets the current day string.
	 * 
	 * @return current day string
	 */
	public String getCurrentDayString() {
		int day = getCurrentDay();
		return day + "";
	}

	/**
	 * Gets the current hour.
	 * 
	 * @return current hour
	 */
	public int getCurrentHour() {
		int hour = gregorianCalendar.get(Calendar.HOUR);
		return hour;
	}

	/**
	 * Gets the current hour string.
	 * 
	 * @return current hour string
	 */
	public String getCurrentHourString() {
		int hour = getCurrentHour();
		return hour + "";
	}

	/**
	 * Gets the current minute.
	 * 
	 * @return current minute
	 */
	public int getCurrentMinute() {
		int minute = gregorianCalendar.get(Calendar.MINUTE);
		return minute;
	}

	/**
	 * Gets the current minute string.
	 * 
	 * @return current minute string
	 */
	public String getCurrentMinuteString() {
		int minute = getCurrentMinute();
		return minute + "";
	}

	/**
	 * Gets the current second.
	 * 
	 * @return current second
	 */
	public int getCurrentSecond() {
		int second = gregorianCalendar.get(Calendar.SECOND);
		return second;
	}

	/**
	 * Gets the current second string.
	 * 
	 * @return current second string
	 */
	public String getCurrentSecondString() {
		int second = getCurrentSecond();
		return second + "";
	}

	/**
	 * Display date values. Used to start learning about dates.
	 */
	private void displayConstants() {
		String dateString = getCurrentDateString();
		log.info("Date: " + dateString);

		String dateTimeString = getCurrentDateTimeString();
		log.info("Date Time: " + dateTimeString);

		String yearString = getCurrentYearString();
		log.info("Year: " + yearString);

		String monthString = getCurrentMonthString();
		log.info("Month: " + monthString);

		String hourString = getCurrentHourString();
		log.info("Hour: " + hourString);

		String minuteString = getCurrentMinuteString();
		log.info("Minute: " + minuteString);

		String secondString = getCurrentSecondString();
		log.info("Second: " + secondString);

		int year = gregorianCalendar.get(Calendar.YEAR);
		log.info("Calendar.YEAR: " + year);

		int month = gregorianCalendar.get(Calendar.MONTH);
		log.info("Calendar.MONTH: " + month);

		int dayOfMonth = gregorianCalendar.get(Calendar.DAY_OF_MONTH);
		log.info("Calendar.DAY_OF_MONTH: " + dayOfMonth);

		int dayOfWeek = gregorianCalendar.get(Calendar.DAY_OF_WEEK);
		log.info("Calendar.DAY_OF_WEEK: " + dayOfWeek);

		int hour = gregorianCalendar.get(Calendar.HOUR);
		log.info("Calendar.HOUR: " + hour);

		int minute = gregorianCalendar.get(Calendar.MINUTE);
		log.info("Calendar.MINUTE: " + minute);

		int second = gregorianCalendar.get(Calendar.SECOND);
		log.info("Calendar.SECOND: " + second);
	}

	public static void main(String[] args) {
		Log4jConfigurator log4jConfigurator = new Log4jConfigurator();
		log4jConfigurator.configure();

		EasyCalendar easyCalendar = new EasyCalendar();

		easyCalendar.displayConstants();

		// Date date = easyCalendar.getDate(2005, 4, 3);

		// log.info(easyCalendar.getCurrentDate());
		// log.info(easyCalendar.getCurrentDateText());
		// log.info(easyCalendar.getDateText(date));
		// log.info(easyCalendar.getYearText(date));
		// log.info(easyCalendar.getMonthText(date));
		// log.info(easyCalendar.getDayText(date));

		// log.info(new Integer(easyCalendar.getLastDay(2005, 9)));
	}

}
