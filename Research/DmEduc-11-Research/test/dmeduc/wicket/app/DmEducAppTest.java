package dmeduc.wicket.app;

import static org.junit.Assert.assertEquals;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.BeforeClass;
import org.junit.Test;

import dmeduc.wicket.app.home.HomePage;

/**
 * Test helper class. Just to provide single instance of DmEducApp, to be shared
 * between tests.
 * 
 * @author Vedad Kirlic
 * @version 2008-09-30
 */
public class DmEducAppTest {

	private static WicketTester tester;

	private static WebApplication app;

	@BeforeClass
	public static void prepareTester() {
		app = new DmEducApp();
		tester = new WicketTester(app);
	}

	@Test
	public void defineHomePage() throws Exception {
		assertEquals(HomePage.class, app.getHomePage());
	}

	@Test
	public void renderHomePage() {
		tester.startPage(HomePage.class);
		tester.assertRenderedPage(HomePage.class);
		tester.assertNoErrorMessage();
	}

}
