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

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelibra.exception.TypeRuntimeException;
import org.modelibra.util.Log4jConfigurator;
import org.modelibra.util.Transformer;

/**
 * An easy to use version of Java Date. An easy date does have time and its date
 * time uses the standard sequence of year, month, day, hour, minute, second. A
 * date time pattern is used to separate sequence components by ".", "/" or "-".
 * Deafult pattern is: "yyyy-MM-dd hh:mm:ss".
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-04-06
 */
public class EasyDateTime extends EasyDate {

	private static Log log = LogFactory.getLog(EasyDateTime.class);

	private int hour;

	private int minute;

	private int second;

	private String dateTimePattern = EasyCalendar.DEFAULT_DATE_TIME_PATTERN;

	/**
	 * Constructs an easy date time from a given Java Date.
	 * 
	 * @param date
	 *            Java date
	 */
	public EasyDateTime(Date date) {
		super(date);
	}

	/**
	 * Constructs an easy date time from year, month, day, hour, minute and
	 * second numbers.
	 * 
	 * @param year
	 *            year number
	 * @param month
	 *            month number
	 * @param day
	 *            day number
	 * @param hour
	 *            hour number
	 * @param minute
	 *            minute number
	 * @param second
	 *            second number
	 */
	public EasyDateTime(int year, int month, int day, int hour, int minute,
			int second) {
		super(year, month, day);
		setDateTime(year, month, day, hour, minute, second);
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
	 * Gets a date time.
	 * 
	 * @return date (time)
	 */
	public Date getDateTime() {
		return getDate();
	}

	/**
	 * Gets a date time string.
	 * 
	 * @return date time string
	 */
	public String getDateTimeString() {
		if (dateTimePattern == null) {
			dateTimePattern = EasyCalendar.DEFAULT_DATE_TIME_PATTERN;
		}
		return Transformer.string(getDateTime(), dateTimePattern);
	}

	/**
	 * Sets a date time.
	 * 
	 * @param date
	 *            date
	 */
	public void setDateTime(Date date) {
		setDate(date);
		if (date != null) {
			hour = getEasyCalendar().getHour(date);
			minute = getEasyCalendar().getMinute(date);
			second = getEasyCalendar().getSecond(date);
		}
	}

	/**
	 * Sets a date time as string.
	 * 
	 * @param dateTimeString
	 *            date time string
	 */
	public void setDateTime(String dateTimeString) {
		if (dateTimePattern == null) {
			dateTimePattern = EasyCalendar.DEFAULT_DATE_TIME_PATTERN;
		}
		try {
			Date date = Transformer.date(dateTimeString, dateTimePattern);
			if (date != null) {
				setDateTime(date);
			}
		} catch (TypeRuntimeException e) {
			String msg = "EasyDateTime.setDateTime --date with time does not have a valid format: "
					+ dateTimeString + ": " + dateTimePattern;
			throw new TypeRuntimeException(msg, e);
		}
	}

	/**
	 * Sets a date time using year, month, day, hour, minute and second numbers.
	 * 
	 * @param year
	 *            year number
	 * @param month
	 *            month number
	 * @param day
	 *            day number
	 * @param hour
	 *            hour number
	 * @param minute
	 *            minute number
	 * @param second
	 *            second number
	 */
	public void setDateTime(int year, int month, int day, int hour, int minute,
			int second) {
		Date date = getEasyCalendar().getDateTime(year, month, day, hour,
				minute, second);
		setDateTime(date);
	}

	/**
	 * Sets an hour.
	 * 
	 * @param hour
	 *            hour number
	 */
	public void setHour(int hour) {
		this.hour = hour;
		setDateTime(getEasyCalendar().getDateTime(getYear(), getMonth(),
				getDay(), hour, minute, second));
	}

	/**
	 * Gets an hour.
	 * 
	 * @return hour
	 */
	public int getHour() {
		return hour;
	}

	/**
	 * Sets a minute.
	 * 
	 * @param minute
	 *            minute number
	 */
	public void setMinute(int minute) {
		this.minute = minute;
		setDateTime(getEasyCalendar().getDateTime(getYear(), getMonth(),
				getDay(), hour, minute, second));
	}

	/**
	 * Gets a minute.
	 * 
	 * @return minute
	 */
	public int getMinute() {
		return minute;
	}

	/**
	 * Sets a second.
	 * 
	 * @param second
	 *            second number
	 */
	public void setSecond(int second) {
		this.second = second;
		setDateTime(getEasyCalendar().getDateTime(getYear(), getMonth(),
				getDay(), hour, minute, second));
	}

	/**
	 * Gets a second.
	 * 
	 * @return second
	 */
	public int getSecond() {
		return second;
	}

	/**
	 * Sets an hour string.
	 * 
	 * @param hourString
	 *            hour string
	 */
	public void setHourString(String hourString) {
		try {
			Integer hourInteger = Transformer.integer(hourString);
			setHour(hourInteger.intValue());
		} catch (TypeRuntimeException e) {
			String msg = "EasyDateTime.setHourString --hour is not Integer: "
					+ hourString;
			throw new TypeRuntimeException(msg, e);
		}
	}

	/**
	 * Gets an hour string.
	 * 
	 * @return hour string
	 */
	public String getHourString() {
		return new Integer(hour).toString();
	}

	/**
	 * Sets a minute string.
	 * 
	 * @param minuteString
	 *            minute string
	 */
	public void setMinuteString(String minuteString) {
		try {
			Integer minuteInteger = Transformer.integer(minuteString);
			setMinute(minuteInteger.intValue());
		} catch (TypeRuntimeException e) {
			String msg = "EasyDateTime.setMinuteString --minute is not Integer: "
					+ minuteString;
			throw new TypeRuntimeException(msg, e);
		}
	}

	/**
	 * Gets a minute string.
	 * 
	 * @return minute string
	 */
	public String getMinuteString() {
		return new Integer(minute).toString();
	}

	/**
	 * Sets a second string.
	 * 
	 * @param secondString
	 *            second string
	 */
	public void setSecondString(String secondString) {
		try {
			Integer secondInteger = Transformer.integer(secondString);
			setDay(secondInteger.intValue());
		} catch (TypeRuntimeException e) {
			String msg = "EasyDateTime.setSecondString --second is not Integer: "
					+ secondString;
			throw new TypeRuntimeException(msg, e);
		}
	}

	/**
	 * Gets a second string.
	 * 
	 * @return second string
	 */
	public String getSecondString() {
		return new Integer(second).toString();
	}

	/**
	 * Checks if this easy date time is equal to a given easy date time.
	 * 
	 * @param easyDateTime
	 *            easy date time
	 * @return <code>true</code> if this easy date is equal to a given easy date
	 *         time
	 */
	public boolean isEqualTo(EasyDateTime easyDateTime) {
		if (this.getYear() == easyDateTime.getYear()
				&& this.getMonth() == easyDateTime.getMonth()
				&& this.getDay() == easyDateTime.getDay()
				&& this.getHour() == easyDateTime.getHour()
				&& this.getMinute() == easyDateTime.getMinute()
				&& this.getSecond() == easyDateTime.getSecond()) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if this easy date time is greater than a given easy date time.
	 * 
	 * @param easyDateTime
	 *            easy date time
	 * @return <code>true</code> if this easy date time is greater than a given
	 *         easy date time
	 */
	public boolean isGreaterThan(EasyDateTime easyDateTime) {
		if (this.getYear() > easyDateTime.getYear()) {
			return true;
		} else if (this.getYear() == easyDateTime.getYear()) {
			if (this.getMonth() > easyDateTime.getMonth()) {
				return true;
			} else if (this.getMonth() == easyDateTime.getMonth()) {
				if (this.getDay() > easyDateTime.getDay()) {
					return true;
				} else if (this.getDay() == easyDateTime.getDay()) {
					if (this.getHour() > easyDateTime.getHour()) {
						return true;
					} else if (this.getHour() == easyDateTime.getHour()) {
						if (this.getMinute() > easyDateTime.getMinute()) {
							return true;
						} else if (this.getMinute() == easyDateTime.getMinute()) {
							if (this.getSecond() > easyDateTime.getSecond()) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * Checks if this easy date time is less than a given easy date time.
	 * 
	 * @param easyDateTime
	 *            easy date
	 * @return <code>true</code> if this easy date time is less than a given
	 *         easy date time
	 */
	public boolean isLessThan(EasyDateTime easyDateTime) {
		if (this.getYear() < easyDateTime.getYear()) {
			return true;
		} else if (this.getYear() == easyDateTime.getYear()) {
			if (this.getMonth() < easyDateTime.getMonth()) {
				return true;
			} else if (this.getMonth() == easyDateTime.getMonth()) {
				if (this.getDay() < easyDateTime.getDay()) {
					return true;
				} else if (this.getDay() == easyDateTime.getDay()) {
					if (this.getHour() < easyDateTime.getHour()) {
						return true;
					} else if (this.getHour() == easyDateTime.getHour()) {
						if (this.getMinute() < easyDateTime.getMinute()) {
							return true;
						} else if (this.getMinute() == easyDateTime.getMinute()) {
							if (this.getSecond() < easyDateTime.getSecond()) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * Compares two easy date times.
	 * 
	 * @param object1
	 *            Entity 1
	 * @param object2
	 *            Entity 2
	 * @return 0 if entities are equal, > 0 if the first easy date is greater
	 *         than the second one, < 0 if the second easy date is greater than
	 *         the first one
	 */
	public int compare(EasyDateTime easyDateTime1, EasyDateTime easyDateTime2) {
		if (easyDateTime1.isEqualTo(easyDateTime2)) {
			return 0;
		}
		if (easyDateTime1.isGreaterThan(easyDateTime2)) {
			return 1;
		}
		return -1;
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
			EasyDateTime easyDateTime = (EasyDateTime) value;
			return this.isEqualTo(easyDateTime);
		} else if (value instanceof Date) {
			Date date = (Date) value;
			EasyDateTime easyDateTime = new EasyDateTime(date);
			return this.isEqualTo(easyDateTime);
		}
		return false;
	}

	/**
	 * Provides an easy date time as a string.
	 * 
	 * @return easy date time as a string
	 */
	public String toString() {
		return getDateTimeString();
	}

	public static void main(String[] args) {
		Log4jConfigurator log4jConfigurator = new Log4jConfigurator();
		log4jConfigurator.configure();

		/*
		 * EasyDateTime easyDateTime = new EasyDateTime(new Date());
		 * log.info(easyDateTime.toString()); Date previousDate =
		 * easyDateTime.getPreviousDayDate(); EasyDateTime previousEasyDateTime
		 * = new EasyDateTime(previousDate);
		 * log.info(previousEasyDateTime.toString());
		 */

		EasyDateTime easyDateTime = new EasyDateTime(2008, 5, 6, 7, 8, 9);
		log.info(easyDateTime.toString());
	}

}