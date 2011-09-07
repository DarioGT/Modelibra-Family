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

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.mail.internet.AddressException;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.modelibra.config.Config;
import org.modelibra.exception.TypeRuntimeException;
import org.modelibra.type.Email;

/**
 * Transforms data values from one type to another.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-10-22
 */
public class Transformer {

	/**
	 * Transforms an integer text into an Integer object if the text is an
	 * integer number.
	 * 
	 * @param number
	 *            integer number text
	 * @return Integer object if the number is an integer, TypeRuntimeException
	 *         otherwise
	 */
	public static Integer integer(String number) {
		try {
			return new Integer(number);
		} catch (NumberFormatException e) {
			throw new TypeRuntimeException(e);
		}

	}

	/**
	 * Transforms a long integer text into a Long object if the text is a long
	 * integer number.
	 * 
	 * @param number
	 *            long integer number text
	 * @return Long object if the number is a long integer, TypeRuntimeException
	 *         otherwise
	 */
	public static Long longInteger(String number) {
		try {
			return new Long(number);
		} catch (NumberFormatException e) {
			throw new TypeRuntimeException(e);
		}
	}

	/**
	 * Transforms an int value into an Integer object.
	 * 
	 * @param intValue
	 *            int value
	 * @return Integer object
	 */
	public static Integer integer(int intValue) {
		return new Integer(intValue);
	}

	/**
	 * Transforms a long value into a Long object.
	 * 
	 * @param longValue
	 *            long value
	 * @return Long object
	 */
	public static Long longInteger(long longValue) {
		return new Long(longValue);
	}

	/**
	 * Transforms a float text into a Float object if the text is a float
	 * decimal.
	 * 
	 * @param number
	 *            float decimal text
	 * @return Float object if the number is a float decimal,
	 *         TypeRuntimeException otherwise
	 */
	public static Float floatDecimal(String number) {
		try {
			return new Float(number);
		} catch (NumberFormatException e) {
			throw new TypeRuntimeException(e);
		} catch (NullPointerException e) {
			throw new TypeRuntimeException(e);
		}
	}

	/**
	 * Transforms a double text into a Double object if the text is a double
	 * decimal.
	 * 
	 * @param number
	 *            double decimal text
	 * 
	 * @return Double object if the number is a double decimal,
	 *         TypeRuntimeException otherwise
	 */
	public static Double doubleDecimal(String number) {
		try {
			return new Double(number);
		} catch (NumberFormatException e) {
			throw new TypeRuntimeException(e);
		} catch (NullPointerException e) {
			throw new TypeRuntimeException(e);
		}
	}

	/**
	 * Transforms a float value into a Float object.
	 * 
	 * @param floatValue
	 *            float value
	 * @return Float object
	 */
	public static Float floatDecimal(float floatValue) {
		return new Float(floatValue);
	}

	/**
	 * Transforms a double value into a Double object.
	 * 
	 * @param doubleValue
	 *            double value
	 * @return Double object
	 */
	public static Double doubleDecimal(double doubleValue) {
		return new Double(doubleValue);
	}

	/**
	 * Transforms an integer value into a String object.
	 * 
	 * @param intValue
	 *            int value
	 * @return String object
	 */
	public static String string(int intValue) {
		return string(new Integer(intValue));
	}

	/**
	 * Transforms a long integer value into a String object.
	 * 
	 * @param longValue
	 *            long integer value
	 * @return String object
	 */
	public static String string(long longValue) {
		return string(new Long(longValue));
	}

	/**
	 * Transforms an Integer object into a String object.
	 * 
	 * @param integerObject
	 *            Integer object
	 * @return String object
	 */
	public static String string(Integer integerObject) {
		try {
			return integerObject.toString();
		} catch (NullPointerException e) {
			throw new TypeRuntimeException(e);
		}
	}

	/**
	 * Transforms a Long object into a String object.
	 * 
	 * @param longObject
	 *            Long object
	 * @return String object
	 */
	public static String string(Long longObject) {
		try {
			return longObject.toString();
		} catch (NullPointerException e) {
			throw new TypeRuntimeException(e);
		}
	}

	/**
	 * Transforms a float value into a String object.
	 * 
	 * @param floatValue
	 *            float value
	 * @return String object
	 */
	public static String string(float floatValue) {
		return string(new Float(floatValue));
	}

	/**
	 * Transforms a float value into a String object.
	 * 
	 * @param floatValue
	 *            float value
	 * @param locale
	 *            locale
	 * @return String object
	 */
	public static String string(float floatValue, Locale locale) {
		return string(new Float(floatValue), locale);
	}

	/**
	 * Transforms a double value into a String object.
	 * 
	 * @param doubleValue
	 *            double value
	 * @return String object
	 */
	public static String string(double doubleValue) {
		return string(new Double(doubleValue));
	}

	/**
	 * Transforms a double value into a String object.
	 * 
	 * @param doubleValue
	 *            double value
	 * @param locale
	 *            locale
	 * @return String object
	 */
	public static String string(double doubleValue, Locale locale) {
		return string(new Double(doubleValue), locale);
	}

	/**
	 * Transforms a Float object into a String object.
	 * 
	 * @param floatObject
	 *            Float object
	 * @return String object
	 */
	public static String string(Float floatObject) {
		try {
			return floatObject.toString();
		} catch (NullPointerException e) {
			throw new TypeRuntimeException(e);
		}
	}

	/**
	 * Transforms a Float object into a String object.
	 * 
	 * @param floatObject
	 *            Float object
	 * @param locale
	 *            locale
	 * @return String object
	 */
	public static String string(Float floatObject, Locale locale) {
		try {
			NumberFormat numberFormat = NumberFormat.getInstance(locale);
			DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
			return decimalFormat.format(floatObject);
		} catch (NullPointerException e) {
			throw new TypeRuntimeException(e);
		}
	}

	/**
	 * Transforms a Double object into a String object.
	 * 
	 * @param doubleObject
	 *            Double object
	 * @return String object
	 */
	public static String string(Double doubleObject) {
		try {
			return doubleObject.toString();
		} catch (NullPointerException e) {
			throw new TypeRuntimeException(e);
		}
	}

	/**
	 * Transforms a Double object into a String object.
	 * 
	 * @param doubleObject
	 *            Double object
	 * @param locale
	 *            locale
	 * @return String object
	 */
	public static String string(Double doubleObject, Locale locale) {
		try {
			NumberFormat numberFormat = NumberFormat.getInstance(locale);
			DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
			return decimalFormat.format(doubleObject);
		} catch (NullPointerException e) {
			throw new TypeRuntimeException(e);
		}
	}

	/**
	 * Transforms a date with a pattern into a String object.
	 * 
	 * @param date
	 *            date
	 * @return String object
	 */
	public static String string(Date date, String datePattern) {
		try {
			Locale locale = Locale.getDefault();
			SimpleDateFormat formatter = new SimpleDateFormat(datePattern,
					locale);
			return formatter.format(date);
		} catch (NullPointerException e) {
			throw new TypeRuntimeException(e);
		}
	}

	/**
	 * Transforms a date into a String object.
	 * 
	 * @param date
	 *            date
	 * @return String object
	 */
	public static String string(Date date) {
		return string(date, Config.DEFAULT_DATE_PATTERN);
	}

	/**
	 * Transforms a boolean value into a String object.
	 * 
	 * @param logic
	 *            boolean value
	 * @return String object
	 */
	public static String string(boolean logic) {
		return string(new Boolean(logic));
	}

	/**
	 * Transforms a Boolean object into a String object.
	 * 
	 * @param logic
	 *            Boolean object
	 * @return String object
	 */
	public static String string(Boolean logic) {
		try {
			return logic.toString();
		} catch (NullPointerException e) {
			throw new TypeRuntimeException(e);
		}
	}

	/**
	 * Transforms a URL object into a String object.
	 * 
	 * @param url
	 *            URL object
	 * @return String object
	 */
	public static String string(URL url) {
		try {
			return url.toString();
		} catch (NullPointerException e) {
			throw new TypeRuntimeException(e);
		}
	}

	/**
	 * Transforms an Email object into a String object.
	 * 
	 * @param email
	 *            Email object
	 * @return String object
	 */
	public static String string(Email email) {
		try {
			return email.toString();
		} catch (NullPointerException e) {
			throw new TypeRuntimeException(e);
		}
	}

	/**
	 * Transforms an object into a String object if it is one of String,
	 * Integer, Float, Boolean, Date, URL, Email, Long, or Double classes.
	 * Otherwise, it returns toString() of the value object.
	 * 
	 * Should this be simply? return transformedValue = value.toString();
	 * 
	 * @param value
	 *            value object
	 * @return String object
	 */
	public static String string(Object value) {
		try {
			String transformedValue = null;
			if (value instanceof String) {
				transformedValue = (String) value;
			} else if (value instanceof Integer) {
				Integer integerValue = (Integer) value;
				transformedValue = Transformer.string(integerValue);
			} else if (value instanceof Float) {
				Float floatValue = (Float) value;
				transformedValue = Transformer.string(floatValue);
			} else if (value instanceof Boolean) {
				Boolean booelanValue = (Boolean) value;
				transformedValue = Transformer.string(booelanValue);
			} else if (value instanceof Date) {
				Date dateValue = (Date) value;
				transformedValue = Transformer.string(dateValue);
			} else if (value instanceof URL) {
				URL urlValue = (URL) value;
				transformedValue = Transformer.string(urlValue);
			} else if (value instanceof Email) {
				Email emailValue = (Email) value;
				transformedValue = Transformer.string(emailValue);
			} else if (value instanceof Long) {
				Long longValue = (Long) value;
				transformedValue = Transformer.string(longValue);
			} else if (value instanceof Double) {
				Double doubleValue = (Double) value;
				transformedValue = Transformer.string(doubleValue);
			} else {
				transformedValue = value.toString();
			}
			return transformedValue;
		} catch (NullPointerException e) {
			throw new TypeRuntimeException(e);
		}
	}

	/**
	 * Transforms a logic text into a Boolean object if the text is boolean
	 * (true or false).
	 * 
	 * @param logic
	 *            boolean text value
	 * @return Boolean object if the logic value is boolean,
	 *         TypeRuntimeException otherwise
	 */
	public static Boolean logic(String logic) {
		try {
			String msg = "String value must be true or false.";
			if (logic.equals("true") || logic.equals("false")) {
				return new Boolean(logic);
			} else {
				throw new TypeRuntimeException(msg);
			}
		} catch (NullPointerException e) {
			throw new TypeRuntimeException(e);
		}
	}

	/**
	 * Transforms a boolean value into a Boolean object.
	 * 
	 * @param logic
	 *            boolean value
	 * @return Boolean object
	 */
	public static Boolean logic(boolean logic) {
		return new Boolean(logic);
	}

	/**
	 * Transforms a date text with a pattern into a Date object, if the text is
	 * date.
	 * 
	 * @param date
	 *            date text
	 * @param datePattern
	 *            date pattern
	 * @return Date object if date is valid, TypeRuntimeException otherwise
	 */
	public static Date date(String date, String datePattern) {
		try {
			Locale locale = Locale.getDefault();
			DateLocaleConverter converter = new DateLocaleConverter(locale,
					datePattern);
			// It does not accept dates with months greater than 12 and days
			// greater than 31.
			// It accepts years greater than the current year.
			converter.convert(date, datePattern);
			SimpleDateFormat formatter = new SimpleDateFormat(datePattern,
					locale);
			return formatter.parse(date);
		} catch (ParseException e) {
			throw new TypeRuntimeException(e);
		} catch (ConversionException e) {
			throw new TypeRuntimeException(e);
		}
	}

	/**
	 * Transforms a date textinto a Date object, if the text is date.
	 * 
	 * @param date
	 *            date text
	 * @return Date object if date is valid, null if not
	 */
	public static Date date(String date) {
		return date(date, Config.DEFAULT_DATE_PATTERN);
	}

	/**
	 * Transforms a url text into a URL object, if the text is a url.
	 * 
	 * @param url
	 *            url text
	 * @return URL object if url is valid, TypeRuntimeException otherwise
	 */
	public static URL url(String url) {
		try {
			return new URL(url);
		} catch (MalformedURLException e) {
			throw new TypeRuntimeException(e);
		}
	}

	/**
	 * Transforms an email text into an Email object, if the text is an email.
	 * 
	 * @param email
	 *            email text
	 * @return Email object if email is valid, TypeRuntimeException otherwise
	 */
	public static Email email(String email) {
		try {
			return new Email(email);
		} catch (AddressException e) {
			throw new TypeRuntimeException(e);
		}
	}

}