package org.ieducnews.model.type;

import java.util.Calendar;
import java.util.Date;

public class EasyDateTime extends EasyDate {

	private int hour;

	private int minute;

	private int second;

	private int ampm;

	public EasyDateTime() {
		this(new Date());
	}

	public EasyDateTime(Date date) {
		super(date);
		if (date != null) {
			hour = convertDateToHour(date);
			minute = convertDateToMinute(date);
			second = convertDateToSecond(date);
			ampm = convertDateToAmPm(date);
		}
	}

	public EasyDateTime(int year, int month, int day, int hour, int minute,
			int second) {
		super(year, month, day);
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		setDate(convertYearMonthDayHourMinuteSecondToDate(year, month, day,
				hour, minute, second));
	}

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}

	public int getSecond() {
		return second;
	}

	public int getAmpm() {
		return ampm;
	}

	private int convertDateToHour(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR);
	}

	private int convertDateToMinute(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MINUTE);
	}

	private int convertDateToSecond(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.SECOND);
	}

	private int convertDateToAmPm(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.AM_PM);
	}

	private Date convertYearMonthDayHourMinuteSecondToDate(int year, int month,
			int day, int hour, int minute, int second) {
		Calendar calendar = Calendar.getInstance();
		if (hour >= 12) {
			ampm = Calendar.PM;
			calendar.set(Calendar.AM_PM, Calendar.PM);
		}
		calendar.set(year, month - 1, day, hour, minute, second);
		return calendar.getTime();
	}

	/**
	 * Checks if this easy date time is equal to a given easy date time.
	 * 
	 * @param easyDateTime
	 *            easy date time
	 * @return <code>true</code> if this easy date time is equal to a given easy
	 *         date time
	 */
	public boolean isEqualTo(EasyDateTime easyDateTime) {
		if (getDate() == null && easyDateTime == null) {
			return true;
		} else if (getYear() == easyDateTime.getYear()
				&& getMonth() == easyDateTime.getMonth()
				&& getDay() == easyDateTime.getDay()
				&& getHour() == easyDateTime.getHour()
				&& getMinute() == easyDateTime.getMinute()
				&& getSecond() == easyDateTime.getSecond()) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if two easy date times are equal.
	 * 
	 * @param value
	 *            easy date time
	 * @return <code>true</code> if two easy date times are equal
	 */
	public boolean equals(Object value) {
		if (value instanceof EasyDateTime) {
			EasyDateTime easyDate = (EasyDateTime) value;
			return isEqualTo(easyDate);
		} else if (value instanceof Date) {
			Date date = (Date) value;
			EasyDateTime easyDate = new EasyDateTime(date);
			return isEqualTo(easyDate);
		}
		return false;
	}

	/**
	 * Provides an easy date time as a string.
	 * 
	 * @return easy date time as a string
	 */
	public String toString() {
		if (getDate() == null) {
			return "0000-00-00 00:00:00";
		}
		int ampm = getAmpm();
		String ampmString = "PM";
		if (ampm == Calendar.AM) {
			ampmString = "AM";
		}
		return getYear() + "-" + getMonth() + "-" + getDay() + " " + getHour()
				+ ":" + getMinute() + ":" + getSecond() + " " + ampmString;
	}

}
