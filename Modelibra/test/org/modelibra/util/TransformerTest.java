package org.modelibra.util;

import static org.junit.Assert.assertEquals;

import java.net.URL;
import java.util.Date;
import java.util.Locale;

import org.junit.Ignore;
import org.junit.Test;
import org.modelibra.exception.TypeRuntimeException;
import org.modelibra.type.EasyDate;
import org.modelibra.type.Email;

public class TransformerTest {

	@Test
	public void integerStringToInteger() throws Exception {
		String string = "17";
		Integer integer = Transformer.integer(string);
		assertEquals(new Integer(string), integer);
	}

	@Test(expected = TypeRuntimeException.class)
	public void nullStringToInteger() throws Exception {
		String string = null;
		Transformer.integer(string);
	}

	@Test(expected = TypeRuntimeException.class)
	public void stringStringToInteger() throws Exception {
		String string = "abc";
		Transformer.integer(string);
	}

	@Test(expected = TypeRuntimeException.class)
	public void floatStringToInteger() throws Exception {
		String string = "2.5";
		Transformer.integer(string);
	}

	@Test(expected = TypeRuntimeException.class)
	public void longIntegerStringToInteger() throws Exception {
		String string = "123456789123456789";
		Transformer.integer(string);
	}

	@Test
	public void longStringToLong() throws Exception {
		String string = "123456789123456789";
		Long longInteger = Transformer.longInteger(string);
		assertEquals(new Long(string), longInteger);
	}

	@Test(expected = TypeRuntimeException.class)
	public void nullStringToLong() throws Exception {
		String string = null;
		Transformer.longInteger(string);
	}

	@Test(expected = TypeRuntimeException.class)
	public void stringStringToLong() throws Exception {
		String string = "abc";
		Transformer.longInteger(string);
	}

	@Test
	public void floatStringToFloat() throws Exception {
		String string = "9.9";
		Float floatDecimal = Transformer.floatDecimal(string);
		assertEquals(new Float(string), floatDecimal);
	}

	@Test(expected = TypeRuntimeException.class)
	public void nullFloatToFloat() throws Exception {
		String string = null;
		Transformer.floatDecimal(string);
	}

	@Test(expected = TypeRuntimeException.class)
	public void stringStringToFloat() throws Exception {
		String string = "abc";
		Transformer.floatDecimal(string);
	}

	@Test
	public void doubleStringToDouble() throws Exception {
		String string = "123456789123456789.123456789123456789";
		Double doubleDecimal = Transformer.doubleDecimal(string);
		assertEquals(new Double(string), doubleDecimal);
	}

	@Test(expected = TypeRuntimeException.class)
	public void nullDoubleToDouble() throws Exception {
		String string = null;
		Transformer.doubleDecimal(string);
	}

	@Test(expected = TypeRuntimeException.class)
	public void stringStringToDouble() throws Exception {
		String string = "abc";
		Transformer.doubleDecimal(string);
	}

	@Test
	public void trueStringToBoolean() throws Exception {
		String string = "true";
		Boolean logic = Transformer.logic(string);
		assertEquals(true, logic);
	}

	@Test
	public void falseStringToBoolean() throws Exception {
		String string = "false";
		Boolean logic = Transformer.logic(string);
		assertEquals(false, logic);
	}

	@Test(expected = TypeRuntimeException.class)
	public void nullStringToBoolean() throws Exception {
		String string = null;
		Transformer.logic(string);
	}

	@Test
	public void integerToString() throws Exception {
		Integer integer = new Integer(99);
		String string = Transformer.string(integer);
		assertEquals("99", string);
	}

	@Test(expected = TypeRuntimeException.class)
	public void nullIntegerToString() throws Exception {
		Integer integer = null;
		Transformer.string(integer);
	}

	@Test
	public void longToString() throws Exception {
		Long longInteger = new Long(123456789123456789l);
		String string = Transformer.string(longInteger);
		assertEquals("123456789123456789", string);
	}

	@Test(expected = TypeRuntimeException.class)
	public void nullLongToString() throws Exception {
		Long longInteger = null;
		Transformer.string(longInteger);
	}

	@Test
	public void floatToString() throws Exception {
		Float floatDecimal = new Float(9.9);
		String string = Transformer.string(floatDecimal);
		assertEquals("9.9", string);
	}

	@Test(expected = TypeRuntimeException.class)
	public void nullFloatToString() throws Exception {
		Float floatDecimal = null;
		Transformer.string(floatDecimal);
	}

	@Test
	public void floatLocaleToString() throws Exception {
		Float floatDecimal = new Float(9.9);
		String string = Transformer.string(floatDecimal, Locale.FRENCH);
		assertEquals("9,9", string);
	}

	@Test
	public void doubleToString() throws Exception {
		Double doubleDecimal = new Double(123456789123456789.123456789123456789);
		String string = Transformer.string(doubleDecimal);
		assertEquals("1.23456789123456784E17", string);
	}

	@Test(expected = TypeRuntimeException.class)
	public void nullDoubleToString() throws Exception {
		Double doubleDecimal = null;
		Transformer.string(doubleDecimal);
	}

	@Ignore
	@Test
	public void doubleLocaleToString() throws Exception {
		Double doubleDecimal = new Double(123456789123456789.123456789123456789);
		String string = Transformer.string(doubleDecimal, Locale.FRENCH);
		assertEquals("1,23456789123456784E17", string);
	}

	@Test
	public void dateToString() throws Exception {
		Date today = new Date();
		EasyDate easyToday = new EasyDate(today);
		String string = Transformer.string(today);
		assertEquals(easyToday.toString(), string);
	}

	@Test
	public void dateStringToDate() throws Exception {
		String string = "2008-10-30";
		Date date = Transformer.date(string);
		EasyDate easyDate = new EasyDate(date);
		assertEquals(easyDate.toString(), string);
	}

	@Test(expected = TypeRuntimeException.class)
	public void emptyStringToDate() throws Exception {
		String string = "";
		Transformer.date(string);
	}

	@Test
	public void booleanToString() throws Exception {
		String string = Transformer.string(Boolean.TRUE);
		assertEquals("true", string);
	}

	@Test
	public void urlToString() throws Exception {
		String webLink = "http://www.imeem.com/";
		URL url = new URL(webLink);
		String string = Transformer.string(url);
		assertEquals(webLink, string);
	}

	@Test
	public void stringToUrl() throws Exception {
		String string = "http://www.imeem.com/";
		URL url = Transformer.url(string);
		assertEquals(url.toString(), string);
	}

	@Test(expected = TypeRuntimeException.class)
	public void stringToInvalidUrl() throws Exception {
		String string = "www.imeem.com/";
		Transformer.url(string);
	}

	@Test
	public void emailToString() throws Exception {
		String emailAddress = "dzenanr@gmail.com";
		Email email = new Email(emailAddress);
		String string = Transformer.string(email);
		assertEquals(emailAddress, string);
	}

	@Test
	public void stringToEmail() throws Exception {
		String string = "dzenanr@gmail.com";
		Email email = Transformer.email(string);
		assertEquals(email.toString(), string);
	}

	@Test(expected = TypeRuntimeException.class)
	public void stringToInvalidEmail() throws Exception {
		String string = "dzenanr@";
		Transformer.email(string);
	}

}
