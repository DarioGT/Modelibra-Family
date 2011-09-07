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
package org.modelibra.util;

import java.util.Locale;

import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Validates data entered as text.
 * 
 * @author Dzenan Ridjanovic
 * @version 2006-03-28
 */
public class Validator {

	private static Log log = LogFactory.getLog(Validator.class);

	/**
	 * Validates an integer.
	 * 
	 * @param number
	 *            integer number text
	 * @return <code>true</code> if integer is valid.
	 */
	public static boolean integer(String number) {
		boolean result = false;
		try {
			Integer idInteger = new Integer(number);
			idInteger.intValue(); // to avoid warning
			result = true;
		} catch (NumberFormatException e) {
			return false;
		}
		return result;
	}

	/**
	 * Validates if a logic value is true or false.
	 * 
	 * @param logic
	 *            logic value text
	 * @return <code>true</code> if logic is valid.
	 */
	public static boolean logic(String logic) {
		boolean result = false;
		try {
			if (logic.equals("true") || logic.equals("false")) {
				result = true;
			}
		} catch (RuntimeException e) {
			return false;
		}
		return result;
	}

	/**
	 * Validates an e-mail.
	 * 
	 * @param email
	 *            email address
	 * @return <code>true</code> if email is valid.
	 */
	public static boolean email(String email) {
		boolean result = false;
		try {
			int atIndex = email.indexOf("@");
			if (atIndex > 0) {
				int pointIndex = email.indexOf(".", atIndex);
				if ((pointIndex > atIndex + 1)
						&& (email.length() > pointIndex + 1)) {
					result = true;
				}
			}
		} catch (RuntimeException e) {
			return false;
		}
		return result;
	}

	/**
	 * Validates a date.
	 * 
	 * @param date
	 *            date
	 * @param datePattern
	 *            date pattern
	 * @return <code>true</code> if date is valid.
	 */
	public static boolean date(String date, String datePattern) {
		boolean result = false;
		try {
			// String datePattern = "yyyy-MM-dd";
			Locale locale = Locale.getDefault();
			DateLocaleConverter converter = new DateLocaleConverter(locale,
					datePattern);
			// It does not accept dates with months greater than 12 and days
			// greater
			// than 31.
			// It accepts years greater than the current year.
			converter.convert(date, datePattern);
			result = true;
		} catch (RuntimeException e) {
			return false;
		}
		return result;
	}

	/**
	 * Validates a date using a default pattern.
	 * 
	 * @param date
	 *            date
	 * @return <code>true</code> if date is valid.
	 */
	public static boolean date(String date) {
		return date(date, "yyyy-MM-dd");
	}

	/**
	 * Outputs an integer.
	 * 
	 * @param text
	 *            integer text
	 */
	private static void outInteger(String text) {
		log.info(text + " is a valid integer: " + integer(text));
	}

	/**
	 * Outputs a logic value.
	 * 
	 * @param text
	 *            logic text
	 */
	private static void outLogic(String text) {
		log.info(text + " is a valid boolean: " + logic(text));
	}

	/**
	 * Outputs an email.
	 * 
	 * @param text
	 *            email text
	 */
	private static void outEmail(String text) {
		log.info(text + " is a valid e-mail: " + email(text));
	}

	/**
	 * Outputs a date.
	 * 
	 * @param text
	 *            date text
	 */
	private static void outDate(String text) {
		log.info(text + " is a valid date: " + date(text, "yyyy-MM-dd"));
	}

	public static void main(String[] args) {
		Log4jConfigurator log4jConfigurator = new Log4jConfigurator();
		log4jConfigurator.configure();

		outInteger("99");
		outInteger("aa");
		outInteger("1.1");

		outLogic("no");
		outLogic("false");

		outEmail("johnATibm.com");
		outEmail("john@ibm");
		outEmail("john@ibm.com");

		outDate("1999.12.31");
		outDate("9999.12.31");
		outDate("1999.13.31");
		outDate("1999.12.33");
		outDate("1999/12/31");
	}

}