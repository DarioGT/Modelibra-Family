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

import java.util.Comparator;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.exception.TypeRuntimeException;
import org.modelibra.util.Log4jConfigurator;
import org.modelibra.util.Transformer;

/**
 * An easy to use version of Java Date. An easy date does not have time and its
 * date uses the standard sequence of year, month and day. A date pattern is
 * used to separate sequence components by ".", "/" or "-". Deafult pattern is:
 * "yyyy-MM-dd".
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-04-06
 */
public class EasyDate implements Comparator<EasyDate> {

	private static Log log = LogFactory.getLog(EasyDate.class);

	private EasyCalendar easyCalendar = new EasyCalendar();

	private Date date;

	private int year;

	private int month;

	private int day;

	private String datePattern = EasyCalendar.DEFAULT_DATE_PATTERN;

	/**
	 * Constructs an easy date from a given Java Date.
	 * 
	 * @param date
	 *            Java date
	 */
	public EasyDate(Date date) {
		setDate(date);
	}

	/**
	 * Constructs an easy date from year, month and day numbers.
	 * 
	 * @param year
	 *            year number
	 * @param month
	 *            month number
	 * @param day
	 *            day number
	 */
	public EasyDate(int year, int month, int day) {
		setDate(year, month, day);
	}

	/**
	 * Gets a date pattern.
	 * 
	 * @return date pattern
	 */
	public EasyCalendar getEasyCalendar() {
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
	 * Gets a date.
	 * 
	 * @return date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Gets a date string.
	 * 
	 * @return date string
	 */
	public String getDateString() {
		if (datePattern == null) {
			datePattern = EasyCalendar.DEFAULT_DATE_PATTERN;
		}
		return Transformer.string(date, datePattern);
	}

	/**
	 * Sets a date.
	 * 
	 * @param date
	 *            date
	 */
	public void setDate(Date date) {
		this.date = date;
		if (date != null) {
			year = easyCalendar.getYear(date);
			month = easyCalendar.getMonth(date);
			day = easyCalendar.getDay(date);
		}
	}

	/**
	 * Sets a date as string.
	 * 
	 * @param dateString
	 *            date string
	 */
	public void setDate(String dateString) {
		if (datePattern == null) {
			datePattern = EasyCalendar.DEFAULT_DATE_PATTERN;
		}
		try {
			Date date = Transformer.date(dateString, datePattern);
			if (date != null) {
				setDate(date);
			}
		} catch (TypeRuntimeException e) {
			String msg = "EasyDate.setDate --date does not have a valid format: "
					+ dateString + ": " + datePattern;
			throw new TypeRuntimeException(msg, e);
		}
	}

	/**
	 * Sets a date using year, month and day numbers.
	 * 
	 * @param year
	 *            year number
	 * @param month
	 *            month number
	 * @param day
	 *            day number
	 */
	public void setDate(int year, int month, int day) {
		Date date = easyCalendar.getDate(year, month, day);
		setDate(date);
	}

	/**
	 * Sets a year.
	 * 
	 * @param year
	 *            year number
	 */
	public void setYear(int year) {
		this.year = year;
		date = easyCalendar.getDate(this.year, this.month, this.day);
	}

	/**
	 * Gets a year.
	 * 
	 * @return year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Sets a month.
	 * 
	 * @param month
	 *            month number
	 */
	public void setMonth(int month) {
		this.month = month;
		date = easyCalendar.getDate(this.year, this.month, this.day);
	}

	/**
	 * Gets a month.
	 * 
	 * @return month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * Sets a day.
	 * 
	 * @param day
	 *            day number
	 */
	public void setDay(int day) {
		this.day = day;
		date = easyCalendar.getDate(this.year, this.month, this.day);
	}

	/**
	 * Gets a day.
	 * 
	 * @return day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * Sets a year string.
	 * 
	 * @param yearString
	 *            year string
	 */
	public void setYearString(String yearString) {
		try {
			Integer yearInteger = Transformer.integer(yearString);
			setYear(yearInteger.intValue());
		} catch (TypeRuntimeException e) {
			String msg = "EasyDate.setYearString --year is not Integer: "
					+ yearString;
			throw new TypeRuntimeException(msg, e);
		}
	}

	/**
	 * Gets a year string.
	 * 
	 * @return year string
	 */
	public String getYearString() {
		return new Integer(year).toString();
	}

	/**
	 * Sets a month string.
	 * 
	 * @param monthString
	 *            month string
	 */
	public void setMonthString(String monthString) {
		try {
			Integer monthInteger = Transformer.integer(monthString);
			setMonth(monthInteger.intValue());
		} catch (TypeRuntimeException e) {
			String msg = "EasyDate.setMonthString --month is not Integer: "
					+ monthString;
			throw new TypeRuntimeException(msg, e);
		}
	}

	/**
	 * Gets a month string.
	 * 
	 * @return month string
	 */
	public String getMonthString() {
		return new Integer(month).toString();
	}

	/**
	 * Sets a day string.
	 * 
	 * @param dayString
	 *            day string
	 */
	public void setDayString(String dayString) {
		try {
			Integer dayInteger = Transformer.integer(dayString);
			setDay(dayInteger.intValue());
		} catch (TypeRuntimeException e) {
			String msg = "EasyDate.setDayString --day is not Integer: "
					+ dayString;
			throw new TypeRuntimeException(msg, e);
		}
	}

	/**
	 * Gets a day string.
	 * 
	 * @return day string
	 */
	public String getDayString() {
		return new Integer(day).toString();
	}

	/**
	 * Gets the previous year date.
	 * 
	 * @return date
	 */
	public Date getPreviousYearDate() {
		int year = this.year - 1;
		int month = this.month;
		int day = this.day;
		if (year == 0) {
			year = 1;
		}
		return easyCalendar.getDate(year, month, day);
	}

	/**
	 * Gets the previous month date.
	 * 
	 * @return date
	 */
	public Date getPreviousMonthDate() {
		int year = this.year;
		int month = this.month - 1;
		int day = this.day;
		if (month == 0) {
			month = 12;
			year = year - 1;
		}
		return easyCalendar.getDate(year, month, day);
	}

	/**
	 * Gets the previous day date.
	 * 
	 * @return date
	 */
	public Date getPreviousDayDate() {
		int year = this.year;
		int month = this.month;
		int day = this.day - 1;
		if (day == 0) {
			month = month - 1;
			if (month == 0) {
				month = 12;
				year = year - 1;
			}
			day = easyCalendar.getLastDay(year, month);
		}
		return easyCalendar.getDate(year, month, day);
	}

	/**
	 * Gets the next year date.
	 * 
	 * @return date
	 */
	public Date getNextYearDate() {
		int year = this.year + 1;
		int month = this.month;
		int day = this.day;
		return easyCalendar.getDate(year, month, day);
	}

	/**
	 * Gets the next month date.
	 * 
	 * @return date
	 */
	public Date getNextMonthDate() {
		int year = this.year;
		int month = this.month + 1;
		int day = this.day;
		if (month == 13) {
			month = 1;
			year = year + 1;
		}
		return easyCalendar.getDate(year, month, day);
	}

	/**
	 * Gets the next day date.
	 * 
	 * @return date
	 */
	public Date getNextDayDate() {
		int year = this.year;
		int month = this.month;
		int day = this.day + 1;
		int lastDay = easyCalendar.getLastDay(year, month);
		if (day > lastDay) {
			day = 1;
			month = month + 1;
			if (month == 13) {
				month = 1;
				year = year + 1;
			}
		}
		return easyCalendar.getDate(year, month, day);
	}

	/**
	 * Checks if this easy date is equal to a given easy date.
	 * 
	 * @param easyDate
	 *            easy date
	 * @return <code>true</code> if this easy date is equal to a given easy date
	 */
	public boolean isEqualTo(EasyDate easyDate) {
		if (this.getYear() == easyDate.getYear()
				&& this.getMonth() == easyDate.getMonth()
				&& this.getDay() == easyDate.getDay()) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if this easy date is greater than a given easy date.
	 * 
	 * @param easyDate
	 *            easy date
	 * @return <code>true</code> if this easy date is greater than a given easy
	 *         date
	 */
	public boolean isGreaterThan(EasyDate easyDate) {
		if (this.getYear() > easyDate.getYear()) {
			return true;
		} else if (this.getYear() == easyDate.getYear()) {
			if (this.getMonth() > easyDate.getMonth()) {
				return true;
			} else if (this.getMonth() == easyDate.getMonth()) {
				if (this.getDay() > easyDate.getDay()) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Checks if this easy date is less than a given easy date.
	 * 
	 * @param easyDate
	 *            easy date
	 * @return <code>true</code> if this easy date is less than a given easy
	 *         date
	 */
	public boolean isLessThan(EasyDate easyDate) {
		if (this.getYear() < easyDate.getYear()) {
			return true;
		} else if (this.getYear() == easyDate.getYear()) {
			if (this.getMonth() < easyDate.getMonth()) {
				return true;
			} else if (this.getMonth() == easyDate.getMonth()) {
				if (this.getDay() < easyDate.getDay()) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Compares two easy dates.
	 * 
	 * @param object1
	 *            Entity 1
	 * @param object2
	 *            Entity 2
	 * @return 0 if entities are equal, > 0 if the first easy date is greater
	 *         than the second one, < 0 if the second easy date is greater than
	 *         the first one
	 */
	public int compare(EasyDate easyDate1, EasyDate easyDate2) {
		if (easyDate1.isEqualTo(easyDate2)) {
			return 0;
		}
		if (easyDate1.isGreaterThan(easyDate2)) {
			return 1;
		}
		return -1;
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
			return this.isEqualTo(easyDate);
		} else if (value instanceof Date) {
			Date date = (Date) value;
			EasyDate easyDate = new EasyDate(date);
			return this.isEqualTo(easyDate);
		}
		return false;
	}

	/**
	 * Provides an easy date as a string.
	 * 
	 * @return easy date as a string
	 */
	public String toString() {
		return getDateString();
	}

	public static void main(String[] args) {
		Log4jConfigurator log4jConfigurator = new Log4jConfigurator();
		log4jConfigurator.configure();

		/*
		 * EasyDate easyDate = new EasyDate(new Date());
		 * log.info(easyDate.toString()); Date previousDate =
		 * easyDate.getPreviousDayDate();
		 * OutTester.outputText(Transformer.string(previousDate));
		 */

		EasyDate easyDate0 = new EasyDate(2007, 13, 07);
		Date date0 = easyDate0.getDate();

		EasyDate easyDate1 = new EasyDate(date0);
		int month1 = easyDate1.getMonth();
		log.info(month1);
	}

}