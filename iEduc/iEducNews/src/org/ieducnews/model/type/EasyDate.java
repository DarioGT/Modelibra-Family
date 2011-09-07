package org.ieducnews.model.type;

import java.util.Calendar;
import java.util.Date;

public class EasyDate {

	private Date date;

	private int year;

	private int month;

	private int day;

	public EasyDate() {
		this(new Date());
	}

	public EasyDate(Date date) {
		this.date = date;
		if (date != null) {
			year = convertDateToYear(date);
			month = convertDateToMonth(date);
			day = convertDateToDay(date);
		}
	}

	public EasyDate(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
		date = convertYearMonthDayToDate(year, month, day);
	}
	
	protected void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	public Date getYesterday() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime();
	}

	private int convertDateToYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	private int convertDateToMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	private int convertDateToDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	private Date convertYearMonthDayToDate(int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		return calendar.getTime();
	}

	/**
	 * Checks if this easy date is equal to a given easy date.
	 * 
	 * @param easyDate
	 *            easy date
	 * @return <code>true</code> if this easy date is equal to a given easy date
	 */
	public boolean isEqualTo(EasyDate easyDate) {
		if (date == null && easyDate == null) {
			return true;
		} else if (getYear() == easyDate.getYear()
				&& getMonth() == easyDate.getMonth()
				&& getDay() == easyDate.getDay()) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if two easy dates are equal.
	 * 
	 * @param value
	 *            easy date
	 * @return <code>true</code> if two easy dates are equal
	 */
	public boolean equals(Object value) {
		if (value instanceof EasyDate) {
			EasyDate easyDate = (EasyDate) value;
			return isEqualTo(easyDate);
		} else if (value instanceof Date) {
			Date date = (Date) value;
			EasyDate easyDate = new EasyDate(date);
			return isEqualTo(easyDate);
		}
		return false;
	}

	/**
	 * Provides an easy date as a string.
	 * 
	 * @return easy date as a string
	 */
	public String toString() {
		if (date == null) {
			return "0000-00-00";
		}
		return getYear() + "-" + getMonth() + "-" + getDay();
	}

}
