package org.ieducnews.model.type;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

public class EasyDateTest {

	private static EasyDate today;

	@BeforeClass
	public static void createToday() {
		today = new EasyDate();
	}

	@Test
	public void outputDate() throws Exception {
		Calendar calendar = new GregorianCalendar();
		Date trialTime = new Date();
		calendar.setTime(trialTime);

		System.out.println("ERA: " + calendar.get(Calendar.ERA));
		System.out.println("YEAR: " + calendar.get(Calendar.YEAR));
		System.out.println("MONTH: " + calendar.get(Calendar.MONTH));
		System.out.println("WEEK_OF_YEAR: "
				+ calendar.get(Calendar.WEEK_OF_YEAR));
		System.out.println("WEEK_OF_MONTH: "
				+ calendar.get(Calendar.WEEK_OF_MONTH));
		System.out.println("DATE: " + calendar.get(Calendar.DATE));
		System.out.println("DAY_OF_MONTH: "
				+ calendar.get(Calendar.DAY_OF_MONTH));
		System.out
				.println("DAY_OF_YEAR: " + calendar.get(Calendar.DAY_OF_YEAR));
		System.out
				.println("DAY_OF_WEEK: " + calendar.get(Calendar.DAY_OF_WEEK));
		System.out.println("DAY_OF_WEEK_IN_MONTH: "
				+ calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
	}

	@Test
	public void convertTodayToYearMonthDay() throws Exception {
		int year = today.getYear();
		int month = today.getMonth();
		int day = today.getDay();
		String todayString = year + "-" + month + "-" + day;
		System.out.println("today: " + todayString);
		Assert.assertEquals(today.toString(), todayString);
	}

	@Test
	public void convertTodayToYesterday() throws Exception {
		Date yesterday = today.getYesterday();
		EasyDate easyYesterday = new EasyDate(yesterday);
		System.out.println("yesterday date: " + yesterday);
		System.out.println("yesterday easy date: " + easyYesterday);
	}

}
